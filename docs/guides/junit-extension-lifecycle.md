# JUnit extension lifecycle

The README introduces two examples that share a resource between test classes:
[`SuiteLikeLifecycleExtension`](../../src/main/java/com/oleynik/qa/workshop/junit/topics/extensions/SuiteLikeLifecycleExtension.java),
registered with `@ExtendWith`, and
[`AutoDetectedRootResourceExtension`](../../src/main/java/com/oleynik/qa/workshop/junit/topics/extensions/AutoDetectedRootResourceExtension.java),
registered automatically through ServiceLoader.
Both use the root `ExtensionContext` store to manage that resource.

## What does "once" mean?

Jupiter is the engine that executes these JUnit tests. Its root `ExtensionContext`
holds a store that extensions can use to share objects across test classes in
one engine execution.

For example, when `SuiteExtensionFirstTest` and `SuiteExtensionSecondTest` run
in the same Jupiter execution:

1. Jupiter calls the extension's `beforeAll()` callback before each class.
2. The first call to `computeIfAbsent` creates the resource in the root store.
3. The other class reuses that resource, including when classes run in parallel.
4. When the root context closes, JUnit calls `close()` on the stored
   `AutoCloseable` resource.

The callback runs for each class; the store makes resource creation happen once.
See [keeping state and closing resources](https://docs.junit.org/current/extensions/keeping-state-in-extensions.html)
and the [Store API](https://docs.junit.org/current/api/org.junit.jupiter.api/org/junit/jupiter/api/extension/ExtensionContext.Store.html).

## Implementation walkthrough

**How it works:**

1. Jupiter, the engine that runs these JUnit tests, calls `beforeAll()` before each
   participating class.
2. `context.getRoot()` gives access to the context shared by test classes in this
   Jupiter engine execution. Its **store** holds objects for the extension to reuse.
3. `getStore(NAMESPACE)` selects this extension's storage area. `computeIfAbsent`
   creates the resource only if its key is missing. The next class gets the same
   stored object, even if classes start in parallel.
4. When the root context closes, JUnit calls `close()` on the stored `AutoCloseable`
   resource. Test classes do not need to close it themselves.

See [keeping state and closing resources in extensions](https://docs.junit.org/current/extensions/keeping-state-in-extensions.html)
and the [Store API](https://docs.junit.org/current/api/org.junit.jupiter.api/org/junit/jupiter/api/extension/ExtensionContext.Store.html).

```java
public class SuiteLikeLifecycleExtension implements BeforeAllCallback {
    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(SuiteLikeLifecycleExtension.class);

    @Override
    public void beforeAll(ExtensionContext context) {
        context.getRoot()
                .getStore(NAMESPACE)
                .computeIfAbsent("suite-like-resource", key -> {
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

Apply `@ExtendWith` to every test class that participates in the shared lifecycle:

```java
@ExtendWith(SuiteLikeLifecycleExtension.class)
public class SuiteExtensionFirstTest {
    @Test
    void first() { ...}
}
```

> **ℹ️ `computeIfAbsent` vs `getOrComputeIfAbsent`:** In JUnit 6, all `getOrComputeIfAbsent` overloads
> are deprecated and replaced by identically-signatured `computeIfAbsent` methods — this example uses
> `computeIfAbsent(K, Function, Class<V>)`.
> On the JUnit 5 branch, use `getOrComputeIfAbsent(K, Function, Class<V>)` instead (same signature, different name).

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

See the [LauncherSession API](https://docs.junit.org/current/api/org.junit.platform.launcher/org/junit/platform/launcher/LauncherSession.html)
and [LauncherSessionListener API](https://docs.junit.org/current/api/org.junit.platform.launcher/org/junit/platform/launcher/LauncherSessionListener.html).

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

See [automatic extension registration](https://docs.junit.org/current/extensions/registering-extensions.html#automatic-extension-registration).
For the provider file and activation steps, see the [automatic registration walkthrough](automatic-extension-registration.md).
Return to the README's [suite lifecycle example](../../README.md#11-suite-lifecycle-beforesuite--aftersuite)
or [automatic registration demo](../../README.md#13-automatic-extension-registration-serviceloader).
