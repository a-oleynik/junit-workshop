<a id="top"></a>

# External listener registration in JUnit 4

[⬅ Back to README](../../README.md)

Observe test execution without adding registration code to each test class.
Maven and Gradle use different mechanisms. Neither demo manages a shared fixture.

## Maven: register a JUnit RunListener

[`AutoDetectedRunListener`](../../src/main/java/com/oleynik/qa/workshop/junit/topics/listeners/AutoDetectedRunListener.java)
extends JUnit 4's `RunListener`. The isolated [Maven profile](../../pom.xml)
`autodetect-listener-demo` selects `AutoDetected*Test` and configures Surefire:

```xml
<properties>
    <property>
        <name>listener</name>
        <value>com.oleynik.qa.workshop.junit.topics.listeners.AutoDetectedRunListener</value>
    </property>
</properties>
```

The [two demo tests](../../src/test/java/com/oleynik/qa/workshop/junit/topics/suites/autodetect)
need no `@RunWith`, `@Rule`, or shared base class to register the listener.
Surefire 3.6.1 runs JUnit 4 tests through its Platform provider and Vintage.

The listener prints the actual events:

```java
@Override
public void testRunStarted(Description description) {
    String runName = description == null ? "<no description from provider>" : description.getDisplayName();
    System.out.println("RunListener.testRunStarted: " + runName);
}

@Override
public void testRunFinished(Result result) {
    System.out.println("RunListener.testRunFinished: tests=" + result.getRunCount()
            + ", failures=" + result.getFailureCount());
}
```

`Description` and `Result` are supplied by the runner or provider.
Surefire 3.6.1's adapter can supply a null description; the example prints a
placeholder instead of throwing an exception. In the verified demo its `Result`
objects also reported zero tests, while the Surefire summary correctly reported
two successful tests. Use the Surefire summary or XML/HTML report for build totals.

Several callback pairs can arrive in one Maven execution. A finish callback does
not necessarily mean all tests in the build have finished. A directly managed
`JUnitCore` run provides its own combined result, as shown in the
[suite lifecycle guide](junit-lifecycle.md).

## Gradle: register a Gradle TestListener

The [`autodetectListenerDemoTest` task](../../build.gradle) selects the same two classes
and registers a Gradle `TestListener` with `addTestListener`.
It does not register the Java `AutoDetectedRunListener`.

Gradle describes execution as a tree of test descriptors. The demo handles only
`suite.parent == null`, the root descriptor. Worker and class events therefore
do not produce extra messages. Root events bracket the aggregate test execution
of that task, including its parallel worker JVMs.

The finish message uses Gradle `TestResult.testCount`, `failedTestCount`, and
`skippedTestCount`, not JUnit's `Result`. These callbacks observe test execution,
not all preparation and completion actions of the Gradle task.

Gradle 9.6.0 deprecates the `beforeSuite(Closure)` and `afterSuite(Closure)`
registration methods. The demo uses `addTestListener(TestListener)` instead.
The interface methods are still named `beforeSuite` and `afterSuite`;
implementing those methods is supported.

## Run the example

Choose one build tool:

```bash
# Maven
./mvnw clean test -P autodetect-listener-demo

# Gradle
./gradlew clean autodetectListenerDemoTest
```

Maven prints JUnit start/finish events and their details; repeated pairs are expected.
Gradle prints one root start message and a finish message with
`tests=2, failures=0, skipped=0`. The two test messages may appear in either order.

The profile `autodetect-extension-demo` and task `autodetectExtensionDemoTest`
are command aliases retained for comparison with master. They still use JUnit 4
and build-tool listeners, not Jupiter extensions.

Normal `./mvnw clean test` and `./gradlew clean test` also execute the two classes,
but do not enable either external demo listener.

## Observed callback counts

Observed on Windows with Surefire 3.6.1, JUnit 4.13.2, Gradle 9.6.0,
and JDK 21 compiling for Java 17:

| Execution                                       | Start/finish pairs                           |
|-------------------------------------------------|----------------------------------------------|
| Maven demo, default `parallel=classes`          | 3                                            |
| Maven demo, `parallel=none`                     | 4                                            |
| Maven demo, `parallel=methods`, `threadCount=3` | 4                                            |
| Maven demo, `forkCount=3`, `reuseForks=false`   | 4                                            |
| Gradle demo, `maxParallelForks=3` or `1`        | 1 root pair; 2 tests in the aggregate result |

These observations are not fixed counts guaranteed by JUnit or a contract
for future Surefire versions.

## Why these events are not global fixtures

Callback counts do not prove one setup/teardown pair for the whole Maven build.
A static flag suppressing repeated setup would not identify the final teardown
event or share a resource across forked JVMs.

For resource setup and cleanup, start with the
[suite-scoped ClassRule example](junit-lifecycle.md).
In Jupiter, ServiceLoader controls extension registration; the root store
separately controls the shared resource's lifetime.
JUnit 4 has no Jupiter extension autodetection API.

## Documentation

- [JUnit 4.13.2 RunListener](https://junit.org/junit4/javadoc/4.13.2/org/junit/runner/notification/RunListener.html)
- [Surefire JUnit support and listener configuration](https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit.html)
- [Gradle 9.6 TestListener](https://docs.gradle.org/9.6.0/javadoc/org/gradle/api/tasks/testing/TestListener.html)
- [Gradle 9.6 Test task](https://docs.gradle.org/9.6.0/dsl/org.gradle.api.tasks.testing.Test.html)

[Back to the README listener example](../../README.md#13-external-listener-registration-maven-and-gradle)

[⬆ Back to top](#top) · [⬅ Back to README](../../README.md)
