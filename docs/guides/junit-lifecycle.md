<a id="top"></a>

# JUnit extension lifecycle

[⬅ Back to README](../../README.md)


The README introduces two examples that share a resource between test classes:
[`SuiteLikeLifecycleExtension`](../../src/main/java/com/oleynik/qa/workshop/junit/topics/extensions/SuiteLikeLifecycleExtension.java),
registered with `@ExtendWith`, and
[`AutoDetectedRootResourceExtension`](../../src/main/java/com/oleynik/qa/workshop/junit/topics/extensions/AutoDetectedRootResourceExtension.java),
registered automatically through ServiceLoader.
Both use the root `ExtensionContext` store to manage that resource.

## What does "once" mean?

Jupiter is the engine that executes these JUnit tests.
Here, "once" means one shared resource for the participating test classes within
a single Jupiter engine execution.
The root `ExtensionContext` marks that boundary; the resource does not belong
to the whole JVM or Maven build.

## Implementation walkthrough

```java
public class SuiteLikeLifecycleExtension implements BeforeAllCallback {
    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(SuiteLikeLifecycleExtension.class);

    @Override
    public void beforeAll(ExtensionContext context) {
        context.getRoot()
                .getStore(NAMESPACE)
                .getOrComputeIfAbsent("suite-like-resource", key -> {
                    System.out.println("Created shared resource in the Jupiter root context");
                    return new SuiteCleanupResource();
                }, SuiteCleanupResource.class);
    }

    static class SuiteCleanupResource implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("Closed shared resource from the Jupiter root context");
        }
    }
}
```

**How the code works:**

1. Jupiter calls `beforeAll()` before each class that registers this extension.
   The callback itself is not limited to a single invocation.
2. `context.getRoot()` selects the context shared by test classes in this
   Jupiter engine execution, instead of the current class's context.
3. `getStore(NAMESPACE)` selects this extension's storage area.
   Using the extension class as the namespace keeps its entries separate
   from entries belonging to other extensions.
4. `getOrComputeIfAbsent("suite-like-resource", ...)` looks up the resource by key.
   If it is missing, JUnit invokes the factory and stores its result.
   Later calls return that same object. Concurrent calls for this key also
   create only one resource.
5. The stored `SuiteCleanupResource` implements `AutoCloseable`.
   JUnit calls its `close()` method when the root context closes,
   so participating test classes do not need to close it themselves.

Apply `@ExtendWith` to every test class that participates in the shared lifecycle:

```java
@ExtendWith(SuiteLikeLifecycleExtension.class)
public class SuiteExtensionFirstTest {
    @Test
    void first() { ...}
}
```

> **ℹ️ JUnit 5 API:** This branch uses `getOrComputeIfAbsent(K, Function, Class<V>)`,
> which is available in JUnit 5.14.4.
> In JUnit 6, the replacement is `computeIfAbsent(K, Function, Class<V>)`;
> that method is not available in JUnit 5.

See [keeping state and closing resources in extensions](https://docs.junit.org/5.14.4/extensions/keeping-state-in-extensions.html)
and the [Store API](https://docs.junit.org/5.14.4/api/org.junit.jupiter.api/org/junit/jupiter/api/extension/ExtensionContext.Store.html).

## Several executions in one JVM

A Java process can execute tests more than once. An IDE or another tool can
run one selection and then another without starting a new JVM.
Each separate Jupiter engine execution has its own root context and store,
so it creates and closes its own resource.

Separate suites can also start separate Jupiter executions. Their resources
are not automatically shared just because the suites run in the same JVM
or Maven build.

## How a launcher session differs

The JUnit Platform coordinates test engines through a launcher.
An IDE or another tool can open a **launcher session**, discover tests,
execute them, execute another selection, and then close the session.
One session can therefore contain multiple discovery and execution requests.

A `LauncherSessionListener` receives events when the session opens and closes.
Its lifetime is different from that of a Jupiter root context, and the session
can involve engines other than Jupiter.
This workshop does not implement that listener; it is included here to explain
the difference in scope.

See the [LauncherSession API](https://docs.junit.org/5.14.4/api/org.junit.platform.launcher/org/junit/platform/launcher/LauncherSession.html)
and [LauncherSessionListener API](https://docs.junit.org/5.14.4/api/org.junit.platform.launcher/org/junit/platform/launcher/LauncherSessionListener.html).

## Registration does not define resource lifetime

With automatic registration enabled, Jupiter uses Java's ServiceLoader to
find and register the extension listed in the service descriptor.
ServiceLoader does not make `beforeAll()` run only once or manage this resource.
In this example, the root store determines when the resource is created and closed.

| Mechanism                                     | Lifecycle boundary                                                                            |
|-----------------------------------------------|-----------------------------------------------------------------------------------------------|
| `@BeforeSuite` / `@AfterSuite`                | Setup and teardown around the selected suite's execution.                                     |
| Resource in the root `ExtensionContext` store | Created when first requested by this extension; closed when that Jupiter root context closes. |
| `LauncherSessionListener`                     | Opening and closing the launcher session, which can contain several test executions.          |
| ServiceLoader                                 | Extension discovery and registration; no separate lifecycle for the shared resource.          |

See [automatic extension registration](https://docs.junit.org/5.14.4/extensions/registering-extensions.html#automatic-extension-registration).
For the provider file and activation steps, see the [automatic registration walkthrough](extension-and-listener-registration.md).
Return to the README's [suite lifecycle example](../../README.md#11-suite-lifecycle-beforesuite--aftersuite)
or [automatic registration demo](../../README.md#13-automatic-extension-registration-serviceloader).

[⬆ Back to top](#top) · [⬅ Back to README](../../README.md)
