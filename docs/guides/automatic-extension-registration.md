# Automatic extension registration (ServiceLoader)

Use automatic registration when an extension should apply to all Jupiter test classes
in an execution, without adding `@ExtendWith` to every class.
With `@ExtendWith`, a test class explicitly chooses the extension.
With automatic registration, the service descriptor and the execution configuration
make that choice for the selected Jupiter tests.

The workshop demonstrates this with
[`AutoDetectedRootResourceExtension`](../../src/main/java/com/oleynik/qa/workshop/junit/topics/extensions/AutoDetectedRootResourceExtension.java),
[`AutoDetectedFirstTest`](../../src/test/java/com/oleynik/qa/workshop/junit/topics/suites/autodetect/AutoDetectedFirstTest.java),
and [`AutoDetectedSecondTest`](../../src/test/java/com/oleynik/qa/workshop/junit/topics/suites/autodetect/AutoDetectedSecondTest.java).
Neither test class has `@ExtendWith` or a shared base class.

## Register the provider

Java's ServiceLoader reads provider names from files under `META-INF/services`
on the classpath. The filename is the fully qualified name of the service interface;
each provider is identified by its fully qualified class name.

This project's [descriptor](../../src/test/resources/META-INF/services/org.junit.jupiter.api.extension.Extension)
is `src/test/resources/META-INF/services/org.junit.jupiter.api.extension.Extension`.
It contains:

```text
com.oleynik.qa.workshop.junit.topics.extensions.AutoDetectedRootResourceExtension
```

The build copies test resources onto the test classpath, making the descriptor
available to Jupiter. This file alone does not enable automatic registration.

## Enable autodetection

Set the JUnit configuration parameter:

```properties
junit.jupiter.extensions.autodetection.enabled=true
```

When enabled, Jupiter uses ServiceLoader to find and register extensions.
This extension then applies to every Jupiter test class in that execution,
not only the two demo classes.

For that reason, the workshop leaves autodetection off in the shared
[`junit-platform.properties`](../../src/test/resources/junit-platform.properties).
The [Maven profile](../../pom.xml) and [Gradle task](../../build.gradle) enable it
only for a selection containing the two demo classes.

## Run the example

Choose one build tool:

```bash
# Maven
./mvnw test -P autodetect-extension-demo

# Gradle
./gradlew autodetectExtensionDemoTest
```

Look for these messages among the build output:

```text
Created shared resource in the Jupiter root context
autodetect case one
autodetect case two
Closed shared resource from the Jupiter root context
```

The two test messages may appear in the opposite order because classes can run
in parallel. For this demo, creation and cleanup each appear once.

A normal `./mvnw clean test` or `./gradlew clean test` also runs these two test
classes, but without registering this extension. Their test messages still
appear; this extension's resource messages do not.

## Registration and resource lifetime

ServiceLoader changes how the extension is registered. The resource is managed
by the same root-store pattern as the explicitly registered extension.
See [the root-store example and lifecycle boundaries](junit-extension-lifecycle.md)
for `computeIfAbsent`, automatic cleanup, and the scope of a shared resource.

## Documentation

- [JUnit automatic extension registration](https://docs.junit.org/current/extensions/registering-extensions.html#automatic-extension-registration)
- [Java ServiceLoader](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ServiceLoader.html)

[Back to the README learning path](../../README.md#13-automatic-extension-registration-serviceloader)
