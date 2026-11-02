<a id="top"></a>

# JUnit 4 suite lifecycle

[⬅ Back to README](../../README.md)

JUnit 4 has no native `@BeforeSuite` or `@AfterSuite`.
Use a suite-scoped rule or class lifecycle methods to prepare and clean up resources.
A listener attached to an explicit `JUnitCore` run demonstrates a different execution boundary.

## What does "once" mean?

"Once" means once for the selected suite execution or one explicit `JUnitCore.run(...)` call.
A second suite or another call has its own lifecycle. These examples do not create a resource
shared by every test in a JVM or build.

## Suite resource with ClassRule

[`BeforeAfterSuite`](../../src/test/java/com/oleynik/qa/workshop/junit/topics/suites/lifecycle/BeforeAfterSuite.java)
uses `@RunWith(Suite.class)` and `@Suite.SuiteClasses` to select two member classes.
Its public static `@ClassRule` of type `ExternalResource` wraps the suite:

```java
@ClassRule
public static ExternalResource suiteSetup = new ExternalResource() {
    @Override
    protected void before() {
        System.out.println("Suite setup");
    }

    @Override
    protected void after() {
        System.out.println("Suite cleanup");
    }
};
```

The rule prepares the resource before the member classes and cleans up after they finish,
including when a test fails. If `before()` itself throws, `after()` is not called.
Setup must clean up any resource it partially acquired.
See [ExternalResource](https://junit.org/junit4/javadoc/4.13.2/org/junit/rules/ExternalResource.html).

This demo prints messages instead of starting an external service.
Members are named `*Case` to avoid also matching the workshop's default test discovery
patterns; this is a workshop convention, not a JUnit restriction.

## Alternative: BeforeClass and AfterClass on the suite

[`BeforeClassSuite`](../../src/test/java/com/oleynik/qa/workshop/junit/topics/suites/beforeclass/BeforeClassSuite.java)
places static `@BeforeClass` and `@AfterClass` methods on the `Suite` runner class.
They wrap all its selected member classes.

JUnit calls `@AfterClass` even if `@BeforeClass` throws, so cleanup should tolerate
resources that were never fully initialized.
See [AfterClass](https://junit.org/junit4/javadoc/4.13.2/org/junit/AfterClass.html).

For both approaches, run the suite entry class when its setup and cleanup are required.
Running a member directly bypasses the suite hooks.
Both build tools include `*Suite` in normal discovery.

## Programmatic execution with JUnitCore

[`JUnitCoreRunnerTest`](../../src/test/java/com/oleynik/qa/workshop/junit/topics/suites/listener/JUnitCoreRunnerTest.java)
creates a `JUnitCore` and attaches
[`SuiteRunListener`](../../src/test/java/com/oleynik/qa/workshop/junit/topics/suites/listener/SuiteRunListener.java):

```java
JUnitCore core = new JUnitCore();
core.addListener(new SuiteRunListener());
Result result = core.run(ListenerFirstCase.class, ListenerSecondCase.class);
```

One call reports a start event before those classes and a finish event with their combined result.
The wrapper asserts four tests ran with no failures or ignored tests.
Build reports count the wrapper as one test; its assertions check the four nested tests.
The listener does not observe unrelated tests outside that call.

See [JUnitCore](https://junit.org/junit4/javadoc/4.13.2/org/junit/runner/JUnitCore.html)
and [RunListener](https://junit.org/junit4/javadoc/4.13.2/org/junit/runner/notification/RunListener.html).

## Run the examples

Choose one build tool:

```bash
# Maven
./mvnw test -Dtest=BeforeAfterSuite
./mvnw test -Dtest=BeforeClassSuite
./mvnw test -Dtest=JUnitCoreRunnerTest

# Gradle
./gradlew test --tests "*BeforeAfterSuite"
./gradlew test --tests "*BeforeClassSuite"
./gradlew test --tests "*JUnitCoreRunnerTest"
```

Each suite executes four tests and prints setup and cleanup once.
The programmatic example prints one listener start/finish pair around its four nested tests.

## Compare the scopes

| Mechanism                                             | What it wraps                                                         |
|-------------------------------------------------------|-----------------------------------------------------------------------|
| Suite `@ClassRule` + `ExternalResource`               | One execution of the selected suite.                                  |
| Suite `@BeforeClass` / `@AfterClass`                  | One execution of that suite class.                                    |
| `JUnitCore` + `RunListener`                           | The classes passed to the explicit `run(...)` call.                   |
| Surefire-configured JUnit listener                    | Runs reported by the configured provider; several pairs are possible. |
| Gradle `TestListener` filtered to the root descriptor | Aggregate test execution of one Gradle `Test` task.                   |

JUnit 5/6 can share resources through a Jupiter root-context store.
JUnit 4's suite rules address a similar setup/cleanup task with different boundaries.
See [external listener registration](extension-and-listener-registration.md) for build-tool observation.

[Back to the README suite example](../../README.md#12-test-suite-beforesuite--aftersuite-emulation)

[⬆ Back to top](#top) · [⬅ Back to README](../../README.md)
