# JUnit 6, JUnit 5 & JUnit 4 — Java Tutorials, Examples & Workshops

[![Java CI with Maven](https://github.com/a-oleynik/junit-workshop/actions/workflows/maven.yml/badge.svg)](https://github.com/a-oleynik/junit-workshop/actions/workflows/maven.yml)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![Gradle](https://img.shields.io/badge/Gradle-9.6.0-02303A.svg)](https://gradle.org/)
[![JUnit](https://img.shields.io/badge/JUnit-5.14.4-green.svg)](https://junit.org/)
[![JUnit Pioneer](https://img.shields.io/badge/JUnit_Pioneer-2.3.0-green.svg)](https://junit-pioneer.org/)
[![AssertJ](https://img.shields.io/badge/AssertJ-3.27.7-yellowgreen.svg)](https://assertj.github.io/doc/)
[![Hamcrest](https://img.shields.io/badge/Hamcrest-3.0-yellowgreen.svg)](https://hamcrest.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.48-red.svg)](https://projectlombok.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

> **Current branch: JUnit 5.14.4 (`junit-5.14.4`)**. For other versions, see [Branches](#-branches).

A hands-on Java testing workshop with runnable examples for **JUnit 6, JUnit 5, and JUnit 4.**

Learn JUnit from scratch, explore advanced features, and compare JUnit with TestNG. Includes Maven and Gradle projects, step-by-step learning paths, and practical examples for articles, workshops, tech talks, and self-study.

> Originally created as companion code for the IT Talk **"JUnit 5 vs TestNG 7"**.
>
> Previous edition: [TestNG vs. JUnit 4 slides](https://www.slideshare.net/oleynikandrey/it-talk-testng-6-vs-junit-4/) · [TestNG vs. JUnit 4 webinar](https://youtu.be/3C-Nu5mkyOQ?t=3207)
>
> **Related projects:**
> - 🧪 [TestNG Workshop](https://github.com/a-oleynik/testng-workshop) — companion TestNG examples to compare side-by-side with this repo
> - 🌐 [Selenium Example (JUnit 6 branch)](https://github.com/a-oleynik/selenium-example/tree/junit6) — real-world Selenium WebDriver framework built on top of JUnit 6

Hands-on examples for **JUnit 5** and **JUnit 4** in Java — covering every major feature from basic assertions to advanced extensions, parameterized tests, retry strategies, parallel execution, and suite orchestration.  
Use it as a practical reference, a workshop starter kit, or a side-by-side comparison with the companion [TestNG Workshop](https://github.com/a-oleynik/testng-workshop).

---

## 📋 Table of Contents

- [Found it useful?](#-found-it-useful)
- [Why this repository?](#-why-this-repository)
- [Who Is This For?](#-who-is-this-for)
- [Branches](#-branches)
- [Quick Start](#-quick-start)
- [Prerequisites](#-prerequisites)
- [Supported Versions](#-supported-versions)
- [Feature Map](#-feature-map)
- [Article Companions](#-article-companions)
- [Beginner Learning Path](#-beginner-learning-path)
- [Advanced Learning Path](#-advanced-learning-path)
- [Command Examples](#-command-examples)
- [CI / CD](#-ci--cd)
- [AI Assistant Support](#-ai-assistant-support)
- [Project Structure](#-project-structure)
- [Additional Resources](#-additional-resources)
- [Useful Links](#-useful-links)
- [License](#-license)

---

## ⭐ Found it useful?

If you found useful examples or information in this repository, please give it a ⭐  
Your support helps the project reach more Java and QA engineers.

---

## 🤔 Why this repository?

JUnit is more than `@Test`, assertions, and lifecycle annotations.

This project covers fundamental and advanced JUnit features with practical, runnable Java examples you can clone, execute, and adapt to your own projects.

Use this repository if you want to:

- **learn JUnit from scratch** with a structured, step-by-step learning path
- **understand the differences between JUnit 4, JUnit 5, and JUnit 6** — what changed and why
- **explore runnable examples** that demonstrate JUnit features in practice
- **master advanced features** — extensions (`BeforeAllCallback`, `AfterAllCallback`, `TestWatcher`), retry strategies, parallel execution, tagging, and parameterized tests
- **study automated testing patterns in Java** — soft assertions, data-driven tests, conditional execution, and more
- **understand suite orchestration** — JUnit Platform's `@Suite`, `@BeforeSuite`, `@AfterSuite`, and suite-like lifecycle management via custom extensions
- **compare JUnit and TestNG** patterns and idioms side-by-side with the companion [TestNG Workshop](https://github.com/a-oleynik/testng-workshop)
- **use it as material** for workshops, tech talks, onboarding sessions, and self-study

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 👥 Who Is This For?

| Audience                                            | What you will get                                                             |
|-----------------------------------------------------|-------------------------------------------------------------------------------|
| **QA engineers** new to JUnit 5                     | A guided tour of every major feature with runnable examples                   |
| **Java developers** migrating from JUnit 4 / TestNG | Side-by-side comparison of patterns and idioms                                |
| **Senior / lead engineers**                         | Deep-dives into extensions, retry strategies, parallel execution, and tagging |
| **Workshop facilitators**                           | A ready-made project you can hand to attendees                                |

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🌿 Branches

This repository contains examples for multiple JUnit versions, each on its own branch:

| Branch                                                                          | JUnit version        | Description                                                             |
|---------------------------------------------------------------------------------|----------------------|-------------------------------------------------------------------------|
| [`master`](https://github.com/a-oleynik/junit-workshop/tree/master)             | **JUnit 6** (6.1.3)  | Latest JUnit 6 features                                                 |
| [`junit-5.14.4`](https://github.com/a-oleynik/junit-workshop/tree/junit-5.14.4) | **JUnit 5** (5.14.4) | Current branch — stable JUnit 5 examples (the most widely used version) |
| [`junit-4.13.2`](https://github.com/a-oleynik/junit-workshop/tree/junit-4.13.2) | **JUnit 4** (4.13.2) | Legacy JUnit 4 examples — useful for migration reference                |

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🚀 Quick Start

> ℹ️ **Choose Your Build Tool:**  
> This project supports both **Maven** and **Gradle** independently. Choose the tool you prefer (or use the included wrappers) and consistently use its corresponding commands:
> - **Maven:** use `./mvnw` (Linux/macOS) or `mvnw.cmd` (Windows)
> - **Gradle:** use `./gradlew` (Linux/macOS) or `gradlew.bat` (Windows)

```bash
git clone https://github.com/a-oleynik/junit-workshop.git
cd junit-workshop

# Maven
./mvnw clean test

# Gradle
./gradlew clean test
```

### Import into an IDE

The same checkout can be imported as either build system:

- **Maven project:** open or import `pom.xml`
- **Gradle project:** open or import `settings.gradle` / `build.gradle`

In IntelliJ IDEA, open the repository folder and choose the build model you want when the IDE detects both Maven and
Gradle. Do not link both models at the same time: select one build system for the current IDE project.

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 📦 Prerequisites

> 💡 **Need help setting up your environment?**  
> Check the comprehensive [Java, Maven & Gradle Setup Guide (Windows, macOS, Ubuntu)](https://medium.com/@andrei.oleynik/how-to-install-and-set-up-java-on-windows-macos-and-ubuntu-jdk-maven-and-gradle-728a732dd123) covering JDK installation, `JAVA_HOME` configuration, build tools setup, and troubleshooting.

| Tool              | Minimum version                   | Notes                                                     |
|-------------------|-----------------------------------|-----------------------------------------------------------|
| **JDK**           | 21 LTS                            |                                                           |
| **Maven**         | 3.9+ *(optional but recommended)* | Not required if using the included Maven Wrapper (`mvnw`) |
| **Gradle**        | 9.6.0 *(optional)*                | The included Gradle Wrapper can be used instead           |
| **Git**           | 2.x+ *(optional)*                 | For cloning and managing repository revisions             |
| **IDE**           | Any (IntelliJ IDEA recommended)   | Lombok plugin required for IDE support                    |
| **Lombok plugin** | Latest                            | IntelliJ: *Settings → Plugins → Lombok*                   |

> 💡 **Maven and Gradle Wrappers included** — no local Maven or Gradle installation is required. Each wrapper
> automatically downloads its pinned build-tool version on first run.
>
> ```bash
> # Linux / macOS (or Windows with Git Bash)
> ./mvnw clean test
> ./gradlew clean test
>
> # Windows (Command Prompt / PowerShell)
> mvnw.cmd clean test
> gradlew.bat clean test
> ```

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🧩 Supported Versions

| Maven artifact                      | Version          | Purpose                                                                                                                                            |
|-------------------------------------|------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| `junit-jupiter-engine`              | `5.14.4` *(BOM)* | Test engine — discovers and runs Jupiter tests; transitively provides `junit-jupiter-api` (all `@Test`, `@BeforeEach`, `@AfterAll`, … annotations) |
| `junit-jupiter-params`              | `5.14.4` *(BOM)* | `@ParameterizedTest`, `@ValueSource`, `@CsvSource`, `@MethodSource`, `@CsvFileSource`                                                              |
| `junit-platform-suite`              | `1.14.4` *(BOM)* | `@Suite`, `@SelectClasses`, `@BeforeSuite`, `@AfterSuite`                                                                                          |
| `junit-pioneer`                     | `2.3.0`          | `@RetryingTest`, `@CartesianTest`, and other community extensions                                                                                  |
| `junit-jupiter-params-dataprovider` | `2.12`           | TNG-style `@DataProvider` integration for JUnit Jupiter                                                                                            |
| `assertj-core`                      | `3.27.7`         | Fluent assertion library; `SoftAssertions` for collecting multiple failures                                                                        |
| `hamcrest-library`                  | `3.0`            | Matcher-based assertions — `assertThat(value, matcher)`                                                                                            |
| `lombok`                            | `1.18.48`        | `@Builder`, `@Data` — compile-time code generation; reduces boilerplate in model classes                                                           |
| `rerunner-jupiter`                  | `2.1.6`          | `@RepeatedIfExceptionsTest` — auto-retry flaky tests on failure                                                                                    |
| `opencsv`                           | `5.12.0`         | CSV file parsing for data-driven tests (`CSVParameterizationTest`)                                                                                 |
| `org.gradle.test-retry`             | `1.6.5`          | Gradle plugin behind the `-PretryCount=N` command                                                                                                  |
| Java `--release`                    | `21`             | Java language level for compilation — enforces JDK 21 API surface (`<release>` is stricter than `<source>`+`<target>`)                             |

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🌍 Feature Map

`topics` contains examples of individual JUnit features; `scenarios` contains complete examples that combine several features and can be referenced by multiple articles. The Calculator scenario is in `scenarios/calculator` in both main and test sources.

The manual starting point is `src/main/java/…/scenarios/calculator/beforejunit/Main.java`; it uses the shared `Calculator` and leads into `CalculatorTest`.

| Package / folder          | Feature demonstrated                                                                | Test class(es)                                                                                                   |
|---------------------------|-------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| `topics/general`          | Basic assertions (`assertEquals`, `assertTrue`, `assertNull`, …)                    | `AssertTest`                                                                                                     |
| `topics/general`          | Exception testing (`assertThrows`)                                                  | `ExceptionTest`                                                                                                  |
| `topics/general`          | Test lifecycle (`@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`)             | `LifecycleTest`                                                                                                  |
| `topics/fixtures`         | Fresh fixtures with In-line Setup and Implicit Setup                                | `InlineFixtureTest`, `ImplicitFixtureTest`                                                                       |
| `topics/general`          | Hamcrest matchers                                                                   | `HamcrestTest`                                                                                                   |
| `topics/general`          | Timeouts (`assertTimeout()`, `@Timeout`)                                            | `TimeoutTest`                                                                                                    |
| `topics/general`          | Timeout thread modes and `ThreadLocal` context                                      | [`TimeoutThreadModeTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/general/TimeoutThreadModeTest.java) |
| `topics/general`          | Disabling tests (`@Disabled`)                                                       | `DisabledTest`                                                                                                   |
| `topics/general`          | Display names & name generators                                                     | `DisplayNameTest`, `DisplayNameGenerationTest`                                                                   |
| `topics/asserts`          | JUnit grouped assertions (`assertAll`)                                              | `AssertAllTest`                                                                                                  |
| `topics/asserts`          | AssertJ soft assertions                                                             | `SoftAssertionsAssertJTest`, `SoftAssertionsAssertJBDDTest`                                                      |
| `topics/asserts`          | JUnit 5+ soft assert pattern                                                        | `SoftAssertTest`                                                                                                 |
| `topics/conditional`      | Assumptions (`assumeTrue`, `assumeThat`)                                            | `AssumptionsTest`, `AssumptionsBeforeAllTest`                                                                    |
| `topics/ddt`              | Parameterized tests — `@MethodSource`                                               | `ParameterizationTest`                                                                                           |
| `topics/ddt`              | Parameterized tests — `@ValueSource` / `@CsvSource`                                 | `ValueSourceTest`                                                                                                |
| `topics/ddt`              | CSV file data source (`src/test/resources/numbers.csv`)                             | `CSVParameterizationTest`                                                                                        |
| `topics/ddt`              | TNG DataProvider integration                                                        | `DataProviderTest`                                                                                               |
| `topics/ddt`              | JUnit Pioneer Cartesian product                                                     | `PioneerCartesianProductTest`                                                                                    |
| `topics/nested`           | `@Nested` test classes                                                              | `NestedTest`                                                                                                     |
| `topics/grouping`         | Tagging with `@Tag` and custom tag annotations                                      | `TagsTest`                                                                                                       |
| `topics/order`            | Test execution ordering (`@TestMethodOrder`)                                        | `ExecutionOrderWithTest`                                                                                         |
| `topics/extensions/basic` | First extension (`BeforeEachCallback`, `@ExtendWith`)                               | `LoggingExtensionTest`                                                                                           |
| `topics/extensions`       | Custom `Extension` (`@RegisterExtension`)                                           | `DBResourceExtensionTest`                                                                                        |
| `topics/extensions`       | `TestWatcher` extension                                                             | `TestWatcherExtensionTest`                                                                                       |
| `topics/retry`            | Retry with JUnit Pioneer (`@RetryingTest`)                                          | `RetryPioneerTest`                                                                                               |
| `topics/retry`            | Retry with Rerunner Jupiter                                                         | `RetryRerunnerTest`                                                                                              |
| `topics/repeat`           | `@RepeatedTest`                                                                     | `RetryRepeatedTest`                                                                                              |
| `topics/suites`           | Suite lifecycle (`@Suite`, `@BeforeSuite`, `@AfterSuite`, `@SelectClasses`)         | `BeforeAfterSuite`, `SuiteLifecycleFirstCase`, `SuiteLifecycleSecondCase`                                        |
| `topics/suites/extension` | Suite-like global lifecycle via `BeforeAllCallback` + root `ExtensionContext` store | `SuiteExtensionFirstTest`, `SuiteExtensionSecondTest`                                                            |

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 📖 Article Companions

This repository is also used as companion code for articles and tutorials.

### JUnit 6 from Scratch: What a Test Framework Actually Does

A beginner-friendly introduction that starts with manual checks in `main()` and
gradually introduces test discovery, assertions, lifecycle, fixtures,
parameterized tests, tags, assumptions, exceptions, parallel execution,
build tools, and extensions.

The article describes JUnit 6; this branch provides the corresponding examples
adapted for **JUnit 5.14.4**.

➡️ [Follow the article learning path](docs/junit-6-from-scratch.md)

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🌱 Beginner Learning Path

Work through these topics in order; each builds on the previous one.

### 1. Basic assertions

`AssertTest`  
Learn `assertEquals`, `assertTrue`, `assertNull`, and `fail`.

### 2. Test lifecycle

[`LifecycleTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/general/LifecycleTest.java)
Understand `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`.

### 3. Fixtures (in-line vs. implicit setup)

[`InlineFixtureTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/fixtures/InlineFixtureTest.java),
[`ImplicitFixtureTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/fixtures/ImplicitFixtureTest.java)  
Learn the difference between **fixture** (the test state/data) and **lifecycle methods**
(the setup mechanism). Compare creating an empty `ShoppingCart` directly in each test
versus using `@BeforeEach`.

The empty cart and its state form the fixture; creating it is fixture setup.
Both examples use a **Fresh Fixture**: each test gets a new cart.
In-line/Implicit Setup describes where preparation happens; Fresh/Shared Fixture
describes whether fixture state is recreated or reused between tests.
Prices and quantities stay in each test so its expected total is easy to understand.
These in-memory objects need no explicit teardown; external resources may need cleanup.

### 4. Exception testing

`ExceptionTest`  
Use `assertThrows` to assert that code throws the right exception.

### 5. Disabling & display names

`DisabledTest`, `DisplayNameTest`, `DisplayNameGenerationTest`  
Skip tests cleanly and make reports human-readable.  
`DisplayNameGenerationTest` demonstrates class-level name generators with `@DisplayNameGeneration`.

### 6. Grouped assertions

`AssertAllTest`  
Use `assertAll` so multiple failures are reported together.

### 7. Assumptions

`AssumptionsTest`  
Skip tests dynamically when preconditions aren't met.

### 8. Parameterized tests

`ValueSourceTest`, `ParameterizationTest`  
Drive one test method with many data rows.

Use `@ParameterizedTest(name = "...")` to give each invocation a readable name in
IDE results and test reports. `{0}`, `{1}`, and `{2}` refer to source arguments by
zero-based position; `{index}` is the invocation number, starting at 1.
For example, [`CalculatorParameterizedTest`](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorParameterizedTest.java)
uses `name = "{0} + {1} = {2}"` to show `2 + 3 = 5`. Factorial examples use
`{0}! = {1}` with a source label. The expected value comes from the data source;
formatting a name does not calculate or assert the result. `ValueSourceTest`
keeps JUnit's default names: the argument values already identify each case.

JUnit 5.14.4 displays these numeric CSV arguments without adding quotes, so the
`name` pattern is sufficient. The `quoteTextArguments` option belongs to JUnit 6
and is not available in this branch.
See [parameterized display names](https://docs.junit.org/5.14.4/writing-tests/parameterized-classes-and-tests.html#customizing-display-names).

### 9. Nested tests

`NestedTest`  
Organise related scenarios using inner `@Nested` classes.

### 10. Tagging

`TagsTest`  
Mark tests as `smoke` or `regression` and run subsets from the command line.

**Run the whole beginner suite:**

```bash
# Maven
./mvnw clean test

# Gradle
./gradlew clean test
```

### 11. HTML Report Generation

```bash
# Maven
./mvnw clean site

# Gradle
./gradlew clean test
```

Maven writes the report to `target/site/surefire-report.html`; Gradle writes it to
`build/reports/tests/test/index.html` automatically as part of the standard `test` task.

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🎯 Advanced Learning Path

These topics assume familiarity with JUnit 5+ basics.

### 1. Custom Tag Annotations

`TagsTest` → `topics/grouping/tags/` package<br>
Compose `@Tag` into reusable meta-annotations (`@Smoke`, `@Regression`).

### 2. CSV & External Data Providers

`CSVParameterizationTest`, `DataProviderTest`, `ParameterizationTest`  
Load test data from CSV files and external `ArgumentsProvider` / `@MethodSource` classes.

### 3. Cartesian Product (JUnit Pioneer)

`PioneerCartesianProductTest`  
Generate all combinations of parameter sets automatically.

### 4. AssertJ fluent and soft assertions

[`SoftAssertionsAssertJTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/asserts/SoftAssertionsAssertJTest.java), [`SoftAssertionsAssertJBDDTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/asserts/SoftAssertionsAssertJBDDTest.java)

Use fluent assertions and collect multiple assertion failures with soft assertions.
These JUnit Jupiter examples register `SoftAssertionsExtension` with
`@ExtendWith(SoftAssertionsExtension.class)`; the extension reports collected
failures automatically after each test. `SoftAssertionsAssertJTest` receives
`SoftAssertions` as a test method parameter. The BDD example injects a
`BDDSoftAssertions` field with `@InjectSoftAssertions` and uses `then(...)`.
For explicit collection with `SoftAssertions.assertAll()`, see
[`SoftAssertTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/asserts/SoftAssertTest.java).

### 5. Hamcrest matchers

[`HamcrestTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/general/HamcrestTest.java)

Write expressive assertions with `org.hamcrest.MatcherAssert.assertThat` and
matchers in ordinary `org.junit.jupiter.api.Test` methods.

### 6. Custom JUnit 5+ Extensions

**First extension:** [`LoggingExtensionTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/extensions/basic/LoggingExtensionTest.java)
registers [`LoggingExtension`](src/main/java/com/oleynik/qa/workshop/junit/topics/extensions/basic/LoggingExtension.java)
with `@ExtendWith`. Its `BeforeEachCallback` prints the test's display name before
the test body. Run the example and observe `Starting test: adds_two_numbers()`
followed by `Running the test body`.

**Advanced extensions:**
[`DBResourceExtensionTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/extensions/DBResourceExtensionTest.java)
uses `@RegisterExtension` with `BeforeTestExecutionCallback` and
`AfterTestExecutionCallback` to connect and disconnect a resource around the test method.
[`TestWatcherExtensionTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/extensions/TestWatcherExtensionTest.java)
uses `TestWatcher` to observe outcomes and `AfterAllCallback` to print a summary.

### 7. Test Execution Order

`ExecutionOrderWithTest`  
Control method execution order with `@TestMethodOrder` and `@Order`.

This is a demonstration of the ordering API. Keep tests independent: do not use
ordering to make one test depend on state or results produced by another.
Each test should prepare its own fixture and pass when run on its own.

### 8. Retry Strategies 

`RetryPioneerTest` — `@RetryingTest(maxAttempts, minSuccess)` via JUnit Pioneer  
`RetryRerunnerTest` — Rerunner Jupiter integration  
`RetryRepeatedTest` — JUnit 5+ native `@RepeatedTest`

### 9. Parallel Execution

The shared defaults are configured in
[`src/test/resources/junit-platform.properties`](src/test/resources/junit-platform.properties).
JUnit reads this file from the test classpath when tests run in the IDE, Maven,
or Gradle:

```properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=same_thread
junit.jupiter.execution.parallel.mode.classes.default=concurrent
junit.jupiter.execution.parallel.config.strategy=dynamic
```

As in the [article learning path](docs/junit-6-from-scratch.md#parallel-execution),
top-level test classes may run in parallel, while methods within each class run
sequentially by default. The first three settings select that execution model.
The fourth setting, `dynamic`, derives parallelism from the available processors.
JUnit 5 uses a `ForkJoinPool`; the JUnit 6 `executor-service=worker_thread_pool`
option is not supported in this branch.

The same four settings are also provided as commented alternatives in
[`pom.xml`](pom.xml) (Surefire's `configurationParameters`) and
[`build.gradle`](build.gradle) (`testTask.systemProperty` calls).
To configure parallel execution through your build tool, uncomment its example
block. Those values override matching settings in `junit-platform.properties`
for that build tool's runs; direct IDE runs continue to use the properties file
unless the IDE supplies its own overrides. Keep the build-tool examples commented
when using the shared defaults. See [JUnit configuration precedence](https://docs.junit.org/5.14.4/running-tests/configuration-parameters.html).

#### Advanced: parallel classes and methods

To allow methods within a class to run concurrently as well, replace the execution
mode settings in the properties file (or in your build-tool override) with:

```properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.mode.classes.default=concurrent
```

This is an optional workshop configuration beyond the article's defaults.
Methods in classes using `@TestInstance(PER_CLASS)` or a `MethodOrderer` still
require an explicit `@Execution(CONCURRENT)` to run concurrently.
See [JUnit parallel execution](https://docs.junit.org/5.14.4/writing-tests/parallel-execution.html).

Classes running in parallel can still share mutable resources. Use the same
`@ResourceLock` name on tests accessing a shared resource; `@Execution(SAME_THREAD)`
keeps a class's methods sequential but does not isolate it from other classes.

```java
// Keep this class's methods sequential, even with the advanced defaults:
@Execution(ExecutionMode.SAME_THREAD)
class MyOrderSensitiveTest { ... }

// Coordinate tests that declare the same shared resource:
@ResourceLock("shared-db-connection")
@Test
void test_that_uses_shared_resource() { ... }
```

> 💡 See `ExecutionOrderWithTest` in the `topics/order/` package for ordering examples used alongside parallel execution.

### 10. Tag-based filtering

Tags used in this project are `smoke` and `regression` (defined in `Tags.java`; also available as meta-annotations `@Smoke` and `@Regression`).

**Direct tag filtering (works out of the box):**

```bash
# Run only smoke tests
./mvnw clean test -Dgroups=smoke
./gradlew clean test -DincludeTags=smoke

# Run only regression tests
./mvnw clean test -Dgroups=regression
./gradlew clean test -DincludeTags=regression

# Run both tags
./mvnw clean test -Dgroups=smoke,regression
./gradlew clean test -DincludeTags=smoke,regression
```

Gradle also supports `-DexcludeTags=TagName`; both properties may be supplied together.

**Via Maven profiles or dedicated Gradle tasks:**

```bash
./mvnw clean test -P smoke
./gradlew clean smokeTest

./mvnw clean test -P regression
./gradlew clean regressionTest
```

> Each Maven profile or Gradle task activates the matching tag filter and automatically
> excludes `@Suite` classes, which have no tagged members and would otherwise fail with `NoTestsDiscoveredException`.
> Profile IDs intentionally match the `@Tag` values — `smoke` and `regression` — so the naming is consistent
> throughout the project.

### 11. Suite Lifecycle (`@BeforeSuite` / `@AfterSuite`)

`BeforeAfterSuite` → `topics/suites/` package<br>
Group multiple test classes under a single `@Suite` class.  
Use `@BeforeSuite` and `@AfterSuite` to run setup/teardown logic that wraps the **entire** suite — not just a single
test class.

> **ℹ️ Availability:** `@BeforeSuite` and `@AfterSuite` were introduced in **JUnit Platform 1.11.0** (bundled with JUnit Jupiter 5.11). They are fully supported on this JUnit 5.14.4 branch.

```java
@Suite
@SelectClasses({
        SuiteLifecycleFirstCase.class,
        SuiteLifecycleSecondCase.class
})
public class BeforeAfterSuite {
    @BeforeSuite
    static void beforeSuite() { ... }

    @AfterSuite
    static void afterSuite() { ... }
}
```

**Execution lifecycle:**

```
[BeforeSuite]  @BeforeSuite
  [BeforeAll]  SuiteLifecycleFirstCase
    first_test_in_first_case
    second_test_in_first_case
  [AfterAll]   SuiteLifecycleFirstCase
  [BeforeAll]  SuiteLifecycleSecondCase
    first_test_in_second_case
    second_test_in_second_case
  [AfterAll]   SuiteLifecycleSecondCase
[AfterSuite]   @AfterSuite
```

> **⚠️ Naming convention:** classes selected by a suite must **not** be named `*Test` or `*Tests`.  
> Use `*Case` or `*Scenario` instead.  
> If they matched Surefire’s or Gradle’s default discovery patterns they would execute **twice** —  
> once directly by Surefire / Gradle and once again through the suite.

> **⚙️ Build config:** `pom.xml` and `build.gradle` both declare the JUnit Platform Suite dependency and include
> `*Suite` classes in test discovery. In Maven that is expressed through Surefire `<includes>`; in Gradle it is
> configured on every `Test` task.
>
> 1. The `junit-platform-suite` dependency (enables `@Suite`, `@BeforeSuite`, `@AfterSuite`):
>    ```xml
>    <dependency>
>        <groupId>org.junit.platform</groupId>
>        <artifactId>junit-platform-suite</artifactId>
>        <!-- version managed by junit-bom in dependencyManagement -->
>    </dependency>
>    ```
> 2. `**/*Suite.java` added to Surefire `<includes>` so `BeforeAfterSuite` is automatically discovered by
>    `./mvnw clean test`; the equivalent Gradle include is `**/*Suite.class`.

### 12. Suite-like Lifecycle via Extension (`BeforeAllCallback` + Root Store)

`SuiteLikeLifecycleExtension` → `topics/extensions/` package (main); tests → `topics/suites/extension/`<br>
An **alternative to `@Suite`** that provides global setup/teardown without grouping tests under a suite class.

**How it works:**

1. Implement `BeforeAllCallback`
2. Access the **root** `ExtensionContext` — shared across the entire JVM test run
3. `getOrComputeIfAbsent` ensures the factory runs **only once**, on the first class whose `beforeAll` fires
4. Return an `AutoCloseable` — JUnit calls `close()` when the root context tears down (end of run = “AfterSuite”)

```java
public class SuiteLikeLifecycleExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        context.getRoot()
                .getStore(NAMESPACE)
                .getOrComputeIfAbsent("suite-like-resource", key -> {
                    System.out.println("Before entire run"); // runs only once
                    return new SuiteCleanupResource();
                }, SuiteCleanupResource.class);
    }

    static class SuiteCleanupResource implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("After entire run"); // called once at end of all tests
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

**Comparing the two suite-lifecycle approaches:**

|                         | `@Suite` + `@BeforeSuite`           | Extension approach                    |
|-------------------------|-------------------------------------|---------------------------------------|
| Test class naming       | `*Case` / `*Scenario` (not `*Test`) | `*Test` — normal, runs independently  |
| Requires suite class    | ✅ `@SelectClasses` required        | ❌ No suite class needed              |
| Tests run independently | ❌ Only via suite entry class       | ✅ Normal Surefire / Gradle discovery |
| Opt-in mechanism        | Declared in `@SelectClasses`        | `@ExtendWith` per class               |

> **⚠️ Note on `getOrComputeIfAbsent`:** the 3-argument overload used here is deprecated in JUnit 6
> but is the standard API in JUnit 5. This example targets the JUnit 5 branch.

### 13. Timeout Thread Modes

[`TimeoutThreadModeTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/general/TimeoutThreadModeTest.java)
compares the two explicit thread modes of Jupiter's `@Timeout`:

```java
@Timeout(value = 2, threadMode = SAME_THREAD)
// or
@Timeout(value = 2, threadMode = SEPARATE_THREAD)
```

The example imports both constants from `org.junit.jupiter.api.Timeout.ThreadMode`.
Each test method has its own two-second limit. Its `@BeforeEach` and `@AfterEach`
methods are outside that limit; they can be given their own `@Timeout` annotations.
A class-level `@Timeout` likewise applies to each test method, not automatically
to lifecycle methods. This differs from the JUnit 4 `Timeout` rule example,
where the limit includes setup and cleanup.

`@BeforeEach` records its thread and sets an ordinary `ThreadLocal` value.
The tests assert the following behavior:

| Mode              | Test thread                       | Value prepared in `@BeforeEach` |
|-------------------|-----------------------------------|---------------------------------|
| `SAME_THREAD`     | Same as the fixture thread        | Available                       |
| `SEPARATE_THREAD` | Different from the fixture thread | Absent                          |

`@AfterEach` verifies that it runs on the original fixture thread with the original
value, then removes that value in a `finally` block. The separate-thread test
also removes its own thread-local entry. Each test has its own instance and
context, so these examples work with the workshop's parallel execution enabled.

Compare with [`TimeoutTest`](src/test/java/com/oleynik/qa/workshop/junit/topics/general/TimeoutTest.java):
`assertTimeout()` runs its block on the calling thread and checks elapsed time
after completion. `@Timeout` with `SAME_THREAD` interrupts the executing thread
if its limit is exceeded. `SEPARATE_THREAD` demonstrates the context boundary
that also matters with `assertTimeoutPreemptively()`, for example when transaction
state is stored in a `ThreadLocal`. The tests verify the difference without
intentionally timing out. See the [JUnit timeout documentation](https://docs.junit.org/5.14.4/writing-tests/timeouts.html).

Run this example with your chosen build tool:

```bash
# Maven
./mvnw test -Dtest=TimeoutThreadModeTest

# Gradle
./gradlew test --tests "*TimeoutThreadModeTest"
```

Both tests should pass.

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 💻 Command Examples

> 💡 Commands below are provided in pairs for **Maven** and **Gradle**. Select the commands corresponding to your chosen build system.

### Run all tests

```bash
./mvnw clean test
./gradlew clean test
```

### Run a single test class

```bash
./mvnw clean test -Dtest=AssertTest
./gradlew clean test --tests "*AssertTest"
```

### Run a single test method

```bash
./mvnw clean test -Dtest=AssertTest#assert_equals_multiplication_test
./gradlew clean test --tests "*AssertTest.assert_equals_multiplication_test"
```

### Run multiple test classes

```bash
./mvnw clean test -Dtest=AssertTest,HamcrestTest
./gradlew clean test --tests "*AssertTest" --tests "*HamcrestTest"
```

### Run test methods matching a pattern

```bash
./mvnw clean test -Dtest=AssertTest#assert_equals*
./gradlew clean test --tests "*AssertTest.assert_equals*"
```

### Run test methods matching multiple patterns

```bash
./mvnw clean test -Dtest=AssertTest#assert_equals*+assert_boolean*
./gradlew clean test --tests "*AssertTest.assert_equals*" --tests "*AssertTest.assert_boolean*"
```

### Rerun failing tests automatically (e.g. flaky tests)

```bash
./mvnw clean test -Dsurefire.rerunFailingTestsCount=2
./gradlew clean test -PretryCount=2
```

### Run tests by tag

```bash
./mvnw clean test -Dgroups=regression,smoke
./gradlew clean test -DincludeTags=regression,smoke
```

### Run tests via Maven profile or dedicated Gradle task

```bash
./mvnw clean test -P smoke
./gradlew clean smokeTest

./mvnw clean test -P regression
./gradlew clean regressionTest
```

### Run only a specific suite

```bash
./mvnw test "-Dtest=BeforeAfterSuite"
./gradlew test --tests "*BeforeAfterSuite"
```

> ⚠️ **Naming convention:** suite-member classes (e.g. `SuiteLifecycleFirstCase`) are named `*Case`, **not** `*Test` or `*Tests`.  
> This prevents Surefire and Gradle from discovering them as standalone tests and running them **twice** —  
> once directly by Surefire / Gradle and once again through the suite.

### Compile, test, and package

```bash
./mvnw clean package
./gradlew clean build
```

### Compile and package without running tests

```bash
./mvnw clean package -DskipTests
./gradlew clean assemble
```

### Generate an HTML test report

```bash
./mvnw clean surefire-report:report
./gradlew clean test
```

> Maven report: `target/reports/surefire.html` — this is the **standalone** goal ([mojo](https://maven.apache.org/plugin-developers/index.html)) execution, which uses its own default output directory.<br>
> Gradle report: `build/reports/tests/test/index.html`

### Generate the full Maven site or standard Gradle test report

```bash
./mvnw clean site
./gradlew clean test
```

> Maven report: `target/site/surefire-report.html`<br>
> Gradle report: `build/reports/tests/test/index.html`

### Enable full debug logging for troubleshooting

```bash
./mvnw clean test -X
./gradlew clean test --debug
```

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🚦 CI / CD

This project uses **GitHub Actions** with a manually triggered workflow.

### Workflow — `maven.yml`

Triggered manually from **Actions → Run workflow** on GitHub.

| Input    | Required | Description                                                                               |
|----------|----------|-------------------------------------------------------------------------------------------|
| `groups` | No       | Tag filter for the by-tag job (e.g. `smoke`, `regression`). Leave empty to skip that job. |

### Jobs

| Job          | Name                   | Runs when                             | Maven command                            | Equivalent Gradle command                                  |
|--------------|------------------------|---------------------------------------|------------------------------------------|------------------------------------------------------------|
| `regression` | Regression — all tests | Always                                | `./mvnw -B clean site`                   | `./gradlew --no-daemon clean test`                         |
| `by-tag`     | By tag — `{groups}`    | Only when `groups` input is filled in | `./mvnw -B clean site -Dgroups={groups}` | `./gradlew --no-daemon clean test -DincludeTags={groups}`  |

The current GitHub Actions workflow executes the Maven commands; the Gradle column shows the equivalent commands for
local use or for adding a Gradle-based workflow later.

Each job uploads two artifacts after completion — including on failure (`if: always()`):

| Artifact                                        | Source path                | Contents                                                               |
|-------------------------------------------------|----------------------------|------------------------------------------------------------------------|
| `surefire-report` / `surefire-report-{tag}`     | `target/site/`             | Full HTML report — open `surefire-report.html` in a browser            |
| `junit-xml-results` / `junit-xml-results-{tag}` | `target/surefire-reports/` | Raw JUnit XML files — compatible with CI dashboards and report parsers |

### How to trigger

1. Go to the **Actions** tab on GitHub
2. Select **Java CI with Maven** in the left panel
3. Click **Run workflow**
4. Optionally fill in the **`groups`** field (e.g. `smoke`) to also run the by-tag job
5. Click **Run workflow**

### Downloading the reports

1. Click the completed workflow run
2. Scroll to **Artifacts** → download **`surefire-report`** (HTML) or **`junit-xml-results`** (XML)
3. Open `surefire-report.html` in a browser

### Generating the report locally

```bash
# All tests
./mvnw clean site
./gradlew clean test

# By tag
./mvnw clean site -Dgroups=smoke
./gradlew clean test -DincludeTags=smoke

./mvnw clean site -Dgroups=regression
./gradlew clean test -DincludeTags=regression
```

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🤖 AI Assistant Support

This project ships instruction files for AI coding assistants so they automatically follow the project's conventions when generating or editing code.

| File                              | Tool            | Purpose                                                                                                        |
|-----------------------------------|-----------------|----------------------------------------------------------------------------------------------------------------|
| `AGENTS.md`                       | Any AI Agent    | Task recipes and conventions for AI coding agents — add a test class, extension, data provider, tag, etc.      |
| `.github/copilot-instructions.md` | GitHub Copilot  | Always-on workspace instructions — injected into every Copilot suggestion and chat turn                        |
| `.junie/guidelines.md`            | JetBrains Junie | Project guidelines Junie reads before generating code                                                          |

All files encode the same critical conventions:
- naming rules (`*Case` / `*Scenario` for suite members, never `*Test`)
- assertion style (AssertJ first, Hamcrest only in `HamcrestTest`)
- extension placement (`src/main/…/topics/extensions/`, not test sources)
- JUnit 5 API preferences (`getOrComputeIfAbsent`, not `computeIfAbsent` — that is the JUnit 6 replacement)
- parallel execution awareness (`@Execution(SAME_THREAD)` / `@ResourceLock` where needed)

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 📁 Project Structure

```text
.github/
└── copilot-instructions.md  # GitHub Copilot workspace instructions (always-on)
.junie/
└── guidelines.md            # JetBrains Junie project guidelines
.mvn/
└── wrapper/
    └── maven-wrapper.properties
build/                       # Generated by Gradle — not committed to git
├── reports/tests/test/
│   └── index.html           # Gradle HTML test report
└── test-results/test/       # Raw JUnit XML results
docs/
└── junit-6-from-scratch.md   # Article learning path and chapter-to-code mapping
gradle/wrapper/              # Pinned Gradle distribution and wrapper bootstrap JAR
src/
├── main/java/com/oleynik/qa/workshop/junit/
│   ├── scenarios/
│   │   └── calculator/
│   │       ├── beforejunit/
│   │       │   └── Main.java    # Manual comparison before introducing JUnit
│   │       └── Calculator.java  # Article example: addition and integer division
│   └── topics/
│       ├── annotations/         # Custom annotation types and helpers
│       ├── dataproviders/       # ArgumentsProvider / DataProvider implementations
│       ├── extensions/          # DBResourceExtension, TestWatcherExtension, SuiteLikeLifecycleExtension
│       │   └── basic/           # LoggingExtension: first BeforeEachCallback example
│       ├── model/               # Shared domain model (MyDoubleWrapper, MyServer, ShoppingCart, User)
│       ├── Factorial.java       # Example production class for parameterized tests
│       ├── PrimeNumber.java
│       └── Utils.java           # Helper used by timeout examples
└── test/
    ├── java/com/oleynik/qa/workshop/junit/
    │   ├── scenarios/
    │   │   └── calculator/
    │   │       ├── CalculatorParameterizedTest.java
    │   │       └── CalculatorTest.java
    │   └── topics/
    │       ├── asserts/         # assertAll and AssertJ soft assertions
    │       ├── conditional/     # Assumptions (assumeTrue, assumingThat)
    │       ├── ddt/             # Parameterized and data-driven tests
    │       ├── extensions/      # Tests exercising custom extensions
    │       │   └── basic/       # LoggingExtensionTest: registration with @ExtendWith
    │       ├── fixtures/        # ImplicitFixtureTest and InlineFixtureTest: fresh cart per test
    │       ├── general/         # Basic assertions, lifecycle, exceptions, display names, timeouts
    │       ├── grouping/        # @Tag usage and custom tag annotations
    │       │   └── tags/        # Tags constants, @Smoke and @Regression
    │       ├── nested/          # @Nested test classes
    │       ├── order/           # @TestMethodOrder and @Order
    │       ├── repeat/          # @RepeatedTest
    │       ├── retry/           # @RetryingTest (Pioneer), @RepeatedIfExceptionsTest (Rerunner)
    │       └── suites/          # @Suite, @BeforeSuite, @AfterSuite
    │           └── extension/  # Suite-like lifecycle via BeforeAllCallback and root store
    └── resources/
        ├── junit-platform.properties # Shared JUnit configuration, including parallel execution
        └── numbers.csv         # CSVParameterizationTest input (number → expected factorial)
target/                      # Generated by Maven — not committed to git
├── site/
│   └── surefire-report.html # HTML test report — open in a browser after `mvn site`
└── surefire-reports/        # Raw JUnit XML results (TEST-*.xml) + console output (*.txt)
.gitignore
AGENTS.md                    # AI Agent task recipes and conventions
build.gradle                 # Gradle build — equivalent dependencies and test configuration
gradlew                      # Gradle Wrapper for Unix
gradlew.bat                  # Gradle Wrapper for Windows
LICENSE
mvnw                         # Maven Wrapper for Unix
mvnw.cmd                     # Maven Wrapper for Windows
pom.xml                      # Maven build — dependencies, Surefire config, profiles
README.md                    # This file
settings.gradle              # Gradle project name
```

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 📚 Additional Resources

- [JUnit 5 User Guide](https://docs.junit.org/5.14.4/overview.html)
- [JUnit Pioneer Documentation](https://junit-pioneer.org/docs/)
- [AssertJ Documentation](https://assertj.github.io/doc/)
- [Hamcrest Tutorial](https://hamcrest.org/JavaHamcrest/tutorial)
- [Lombok Features](https://projectlombok.org/features/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [Maven Surefire Report Plugin](https://maven.apache.org/surefire/maven-surefire-report-plugin/)
- [Maven Wrapper](https://maven.apache.org/wrapper/) — run Maven without a local installation
- [Gradle Java Plugin](https://docs.gradle.org/current/userguide/java_plugin.html)
- [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) — run Gradle without a local installation
- [Gradle Test Retry Plugin](https://github.com/gradle/test-retry-gradle-plugin)
- [TestNG Workshop](https://github.com/a-oleynik/testng-workshop) — companion TestNG examples
- [Selenium Example — JUnit 6 branch](https://github.com/a-oleynik/selenium-example/tree/junit6) — real-world Selenium framework using JUnit 6

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🔗 Useful Links

- [**Java Download**](https://www.oracle.com/java/technologies/downloads/)
- [**Maven Download**](https://maven.apache.org/download.cgi)
- [**Gradle Releases**](https://gradle.org/releases/)
- [**JUnit 5 & 6 Releases**](https://github.com/junit-team/junit5/releases)
- [**JUnit 4 Releases**](https://github.com/junit-team/junit4/releases)
- [**How to Install and Set Up Java on Windows, macOS, and Ubuntu (JDK, Maven, Gradle)**](https://medium.com/@andrei.oleynik/how-to-install-and-set-up-java-on-windows-macos-and-ubuntu-jdk-maven-and-gradle-728a732dd123)
- [**JUnit 6 Released — Clean-Up, Modernization & Minimal Disruption**](https://medium.com/@andrei.oleynik/junit-6-released-clean-up-modernization-minimal-disruption-d3ecf11b64ad)
- [**What Is a Test Fixture in JUnit, and Why Is @BeforeEach Not a Fixture?**](https://medium.com/@andrei.oleynik/what-is-a-test-fixture-in-junit-and-why-is-beforeeach-not-the-fixture-53f5c750270e)
- [**Maven Wrapper: Why You Need It and How to Use It**](https://medium.com/@andrei.oleynik/maven-wrapper-why-you-need-it-and-how-to-use-it-bfa7619078b6)
- [**JUnit Pioneer Releases**](https://github.com/junit-pioneer/junit-pioneer/releases)
- [**Lombok Download**](https://projectlombok.org/download)
- [**IntelliJ Lombok Plugin**](https://plugins.jetbrains.com/plugin/6317-lombok)

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 📝 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

[⬆ Back to Table of Contents](#-table-of-contents)

---
