# JUnit 6 Workshop Examples

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-6.1.0--M1-green.svg)](https://junit.org/)
[![JUnit Pioneer](https://img.shields.io/badge/JUnit_Pioneer-2.3.0-green.svg)](https://junit-pioneer.org/)
[![AssertJ](https://img.shields.io/badge/AssertJ-3.27.7-yellowgreen.svg)](https://assertj.github.io/doc/)
[![Hamcrest](https://img.shields.io/badge/Hamcrest-3.0-yellowgreen.svg)](https://hamcrest.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.44-red.svg)](https://projectlombok.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

> Companion code for the IT Talk **"JUnit 6 vs TestNG 7"**.
>
> Previous edition: [TestNG vs. JUnit 4 slides](https://www.slideshare.net/oleynikandrey/it-talk-testng-6-vs-junit-4/) · [TestNG vs. JUnit 4 webinar](https://youtu.be/3C-Nu5mkyOQ?t=3189)
>
> **Related projects:**
> - 🧪 [TestNG Workshop](https://github.com/a-oleynik/testng-workshop) — companion TestNG examples to compare side-by-side with this repo
> - 🌐 [Selenium Example (JUnit 6 branch)](https://github.com/a-oleynik/selenium-example/tree/junit6) — real-world Selenium WebDriver framework built on top of JUnit 6

---

## 🌿 Branches

This repository contains examples for multiple JUnit versions, each on its own branch:

| Branch                                                                          | JUnit version          | Description                                              |
|---------------------------------------------------------------------------------|------------------------|----------------------------------------------------------|
| [`master`](https://github.com/a-oleynik/junit-workshop/tree/master)             | **JUnit 6** (6.1.0-M1) | Current branch — latest JUnit 6 Milestone features       |
| [`junit-5.14.3`](https://github.com/a-oleynik/junit-workshop/tree/junit-5.14.3) | **JUnit 5** (5.x)      | Stable JUnit 5 examples — the most widely used version   |
| [`junit-4.13.2`](https://github.com/a-oleynik/junit-workshop/tree/junit-4.13.2) | **JUnit 4** (4.x)      | Legacy JUnit 4 examples — useful for migration reference |

---

## 📋 Table of Contents

- [Branches](#-branches)
- [Who Is This For?](#-who-is-this-for)
- [Prerequisites](#-prerequisites)
- [Quick Start](#-quick-start)
- [Supported Versions](#-supported-versions)
- [Feature Map](#-feature-map)
- [Learning Path — Beginners](#-learning-path--beginners)
- [Advanced Topics — Path for Senior Engineers](#-advanced-topics--path-for-senior-engineers)
- [Command Examples](#-command-examples)
- [Project Structure](#-project-structure)
- [License](#-license)
- [Additional Resources](#-additional-resources)
- [Useful Links](#-useful-links)

---

## 👥 Who Is This For?

| Audience                                            | What you will get                                                             |
|-----------------------------------------------------|-------------------------------------------------------------------------------|
| **QA engineers** new to JUnit 6                     | A guided tour of every major feature with runnable examples                   |
| **Java developers** migrating from JUnit 4 / TestNG | Side-by-side comparison of patterns and idioms                                |
| **Senior / lead engineers**                         | Deep-dives into extensions, retry strategies, parallel execution, and tagging |
| **Workshop facilitators**                           | A ready-made project you can hand to attendees                                |

---

## 📦 Prerequisites

| Tool              | Minimum version                 | Notes                                   |
|-------------------|---------------------------------|-----------------------------------------|
| **JDK**           | 21 LTS                          |                                         |
| **Maven**         | 3.9+                            |                                         |
| **IDE**           | Any (IntelliJ IDEA recommended) | Lombok plugin required for IDE support  |
| **Lombok plugin** | Latest                          | IntelliJ: *Settings → Plugins → Lombok* |

---

## 🚀 Quick Start

```bash
git clone https://github.com/a-oleynik/junit-workshop.git
cd junit-workshop
mvn clean test
```

---

## 🧩 Supported Versions

| Maven artifact                      | Version    | Purpose                                                                                                                                            |
|-------------------------------------|------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| `junit-jupiter-engine`              | `6.1.0-M1` | Test engine — discovers and runs Jupiter tests; transitively provides `junit-jupiter-api` (all `@Test`, `@BeforeEach`, `@AfterAll`, … annotations) |
| `junit-jupiter-params`              | `6.1.0-M1` | `@ParameterizedTest`, `@ValueSource`, `@CsvSource`, `@MethodSource`, `@CsvFileSource`                                                              |
| `junit-platform-suite`              | `6.1.0-M1` | `@Suite`, `@SelectClasses`, `@BeforeSuite`, `@AfterSuite`                                                                                          |
| `junit-pioneer`                     | `2.3.0`    | `@RetryingTest`, `@CartesianTest`, and other community extensions                                                                                  |
| `junit-jupiter-params-dataprovider` | `2.12`     | TNG-style `@DataProvider` integration for JUnit Jupiter                                                                                            |
| `assertj-core`                      | `3.27.7`   | Fluent assertion library; `SoftAssertions` for collecting multiple failures                                                                        |
| `hamcrest-library`                  | `3.0`      | Matcher-based assertions — `assertThat(value, matcher)`                                                                                            |
| `lombok`                            | `1.18.44`  | `@Builder`, `@Data` — compile-time code generation; reduces boilerplate in model classes                                                           |
| `rerunner-jupiter`                  | `2.1.6`    | `@RepeatedIfExceptionsTest` — auto-retry flaky tests on failure                                                                                    |
| `opencsv`                           | `5.12.0`   | CSV file parsing for data-driven tests (`CSVParameterizationTest`)                                                                                 |
| Java source / target                | `21`       | Java language level for compilation                                                                                                                |

---

## 🌍 Feature Map

| Package / folder   | Feature demonstrated                                                                | Test class(es)                                                            |
|--------------------|-------------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| `general`          | Basic assertions (`assertEquals`, `assertTrue`, `assertNull`, …)                    | `AssertTest`                                                              |
| `general`          | Exception testing (`assertThrows`)                                                  | `ExceptionTest`                                                           |
| `general`          | Test fixtures (`@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`)              | `FixturesTest`                                                            |
| `general`          | Hamcrest matchers                                                                   | `HamcrestTest`                                                            |
| `general`          | Timeouts (`@Timeout`)                                                               | `TimeoutTest`                                                             |
| `general`          | Disabling tests (`@Disabled`)                                                       | `DisabledTest`                                                            |
| `general`          | Display names & name generators                                                     | `DisplayNameTest`, `DisplayNameGenerationTest`                            |
| `group/asserts`    | Grouped / soft assertions (`assertAll`)                                             | `AssertAllTest`                                                           |
| `group/asserts`    | AssertJ soft assertions                                                             | `SoftAssertionsAssertJTest`, `SoftAssertionsAssertJBDDTest`               |
| `group/asserts`    | JUnit 5+ soft assert pattern                                                        | `SoftAssertTest`                                                          |
| `conditional`      | Assumptions (`assumeTrue`, `assumeThat`)                                            | `AssumptionsTest`, `AssumptionsBeforeAllTest`                             |
| `ddt`              | Parameterized tests — `@MethodSource`                                               | `ParameterizationTest`                                                    |
| `ddt`              | Parameterized tests — `@ValueSource` / `@CsvSource`                                 | `ValueSourceTest`                                                         |
| `ddt`              | CSV file data source                                                                | `CSVParameterizationTest`                                                 |
| `ddt`              | TNG DataProvider integration                                                        | `DataProviderTest`                                                        |
| `ddt`              | JUnit Pioneer Cartesian product                                                     | `PioneerCartesianProductTest`                                             |
| `nested`           | `@Nested` test classes                                                              | `NestedTest`                                                              |
| `grouping`         | Tagging with `@Tag` and custom tag annotations                                      | `TagsTest`                                                                |
| `execution/order`  | Test execution ordering (`@TestMethodOrder`)                                        | `ExecutionOrderWithTest`                                                  |
| `extensions`       | Custom `Extension` (`@RegisterExtension`)                                           | `DBResourceExtensionTest`                                                 |
| `extensions`       | `TestWatcher` extension                                                             | `TestWatcherExtensionTest`                                                |
| `retry`            | Retry with JUnit Pioneer (`@RetryingTest`)                                          | `RetryPioneerTest`                                                        |
| `retry`            | Retry with Rerunner Jupiter                                                         | `RetryRerunnerTest`                                                       |
| `repeat`           | `@RepeatedTest`                                                                     | `RetryRepeatedTest`                                                       |
| `suites`           | Suite lifecycle (`@Suite`, `@BeforeSuite`, `@AfterSuite`, `@SelectClasses`)         | `BeforeAfterSuite`, `SuiteLifecycleFirstCase`, `SuiteLifecycleSecondCase` |
| `suites/extension` | Suite-like global lifecycle via `BeforeAllCallback` + root `ExtensionContext` store | `SuiteExtensionFirstTest`, `SuiteExtensionSecondTest`                     |

---

## 🌱 Learning Path — Beginners

Work through these topics in order; each builds on the previous one.

1. **Basic assertions** → `AssertTest`  
   Learn `assertEquals`, `assertTrue`, `assertNull`, `assertAll`, and `fail`.

2. **Test lifecycle** → `FixturesTest`  
   Understand `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`.

3. **Exception testing** → `ExceptionTest`  
   Use `assertThrows` to assert that code throws the right exception.

4. **Disabling & display names** → `DisabledTest`, `DisplayNameTest`  
   Skip tests cleanly and make reports human-readable.

5. **Hamcrest matchers** → `HamcrestTest`  
   Write expressive assertions with `assertThat`.

6. **Grouped assertions** → `AssertAllTest`  
   Use `assertAll` so multiple failures are reported together.

7. **Assumptions** → `AssumptionsTest`  
   Skip tests dynamically when preconditions aren't met.

8. **Parameterized tests** → `ValueSourceTest`, `ParameterizationTest`  
   Drive one test method with many data rows.

9. **Nested tests** → `NestedTest`  
   Organise related scenarios using inner `@Nested` classes.

10. **Tagging** → `TagsTest`  
    Mark tests as `Smoke` or `Regression` and run subsets from the command line.

**Run the whole beginner suite:**

```bash
mvn clean test
```

---

## 🎯 "Advanced Topics" — Path for Senior Engineers

These topics assume familiarity with JUnit 5+ basics.

### 1. Custom Tag Annotations

`TagsTest` → `tags/` package  
Compose `@Tag` into reusable meta-annotations (`@Smoke`, `@Regression`).

### 2. CSV & External Data Providers

`CSVParameterizationTest`, `DataProviderTest`, `ParameterizationTest`  
Load test data from CSV files and external `ArgumentsProvider` / `@MethodSource` classes.

### 3. Cartesian Product (JUnit Pioneer)

`PioneerCartesianProductTest`  
Generate all combinations of parameter sets automatically.

### 4. Soft Assertions (AssertJ)

`SoftAssertionsAssertJTest`, `SoftAssertionsAssertJBDDTest`  
Collect all assertion failures before reporting — no early bail-out.

### 5. Custom JUnit 5+ Extensions

`DBResourceExtensionTest`, `TestWatcherExtensionTest`  
Implement `BeforeAllCallback`, `AfterAllCallback`, and `TestWatcher` to manage external resources and observe test
outcomes.

### 6. Test Execution Order

`ExecutionOrderWithTest`  
Control method execution order with `@TestMethodOrder` and `@Order`.

### 7. Retry Strategies

`RetryPioneerTest` — `@RetryingTest(maxAttempts, minSuccess)` via JUnit Pioneer  
`RetryRerunnerTest` — Rerunner Jupiter integration  
`RetryRepeatedTest` — JUnit 5+ native `@RepeatedTest`

### 8. Parallel Execution

Configured globally in `pom.xml` via Surefire:

```
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
```

Tests run concurrently by default. Use `@ResourceLock` or `@Execution(SAME_THREAD)` to serialise where needed.

### 9. Maven Profiles (Tag-based filtering)

```bash
# Run only Smoke tests
mvn clean test -P SmokeTests

# Run only Regression tests
mvn clean test -P RegressionTests
```

### 10. Suite Lifecycle (`@BeforeSuite` / `@AfterSuite`)

`BeforeAfterSuite` → `suites/` package  
Group multiple test classes under a single `@Suite` class.  
Use `@BeforeSuite` and `@AfterSuite` to run setup/teardown logic that wraps the **entire** suite — not just a single
test class.

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
> If they matched Surefire’s default discovery patterns they would execute **twice** —  
> once directly by Surefire and once again through the suite.

> **⚙️ Maven config:** `pom.xml` requires two things for the suite feature to work:
>
> 1. The `junit-platform-suite` dependency (enables `@Suite`, `@BeforeSuite`, `@AfterSuite`):
>    ```xml
>    <dependency>
>        <groupId>org.junit.platform</groupId>
>        <artifactId>junit-platform-suite</artifactId>
>        <version>6.1.0-M1</version>
>    </dependency>
>    ```
> 2. `**/*Suite.java` added to Surefire `<includes>` so `BeforeAfterSuite` is automatically discovered by `mvn clean test`.

### 11. Suite-like Lifecycle via Extension (`BeforeAllCallback` + Root Store)

`SuiteLikeLifecycleExtension` → `extensions/` package (main); tests → `suites/extension/`  
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

|                         | `@Suite` + `@BeforeSuite`           | Extension approach                   |
|-------------------------|-------------------------------------|--------------------------------------|
| Test class naming       | `*Case` / `*Scenario` (not `*Test`) | `*Test` — normal, runs independently |
| Requires suite class    | ✅ `@SelectClasses` required         | ❌ No suite class needed              |
| Tests run independently | ❌ Only via suite entry class        | ✅ Normal Surefire discovery          |
| Opt-in mechanism        | Declared in `@SelectClasses`        | `@ExtendWith` per class              |

> **⚠️ Note on `getOrComputeIfAbsent`:** the 3-argument overload used here is deprecated in JUnit 6
> but is the standard API in JUnit 5. This example targets the JUnit 5 branch.

### 12. Surefire HTML Report Generation

```bash
mvn clean site
# or
mvn clean surefire-report:report
```

---

## 💻 Command Examples

### Run all tests

```bash
mvn clean test
```

### Run a single test class

```bash
mvn clean test -Dtest=AssertTest
```

### Run a single test method

```bash
mvn clean test -Dtest=AssertTest#assert_equals_multiplication_test
```

### Run multiple test classes

```bash
mvn clean test -Dtest=AssertTest,HamcrestTest
```

### Run test methods matching a pattern

```bash
mvn clean test -Dtest=AssertTest#assert_equals*
```

### Run test methods matching multiple patterns

```bash
mvn clean test -Dtest=AssertTest#assert_equals*+assert_boolean*
```

### Rerun failing tests automatically (e.g. flaky tests)

```bash
mvn clean test -Dsurefire.rerunFailingTestsCount=2
```

### Run tests by tag

```bash
mvn clean test -Dgroups=Regression,Smoke
```

### Run only a specific suite

```bash
mvn test "-Dtest=BeforeAfterSuite"
```

> ⚠️ **Naming convention:** suite-member classes (e.g. `SuiteLifecycleFirstCase`) are named `*Case`, **not** `*Test` or `*Tests`.  
> This prevents Surefire from discovering them as standalone tests and running them **twice** —  
> once directly by Surefire and once again through the suite.

### Compile, test, package, and install to local repo

```bash
mvn clean install
```

### Skip tests (compile + package + install to local repo without running tests)

```bash
mvn clean install -DskipTests
```

### Generate Surefire HTML report (quick)

```bash
mvn clean surefire-report:report
```

### Generate full Maven site with Surefire report

```bash
mvn clean site
```

> Reports are written to `target/site/surefire-report.html`

### Enable full debug logging for troubleshooting

```bash
mvn clean test -X
```

---

## 📁 Project Structure

```
src/
├── main/java/com/oleynik/qa/workshop/junit/
│   ├── extensions/     # Extension implementations (SuiteLikeLifecycleExtension, DBResourceExtension, TestWatcherExtension)
│   └── model/          # Domain model (User, MyDoubleWrapper, MyServer)
└── test/java/com/oleynik/qa/workshop/junit/
    ├── general/         # Core assertions, fixtures, exceptions, display names
    ├── group/asserts/   # Grouped / soft assertions
    ├── conditional/     # Assumptions
    ├── ddt/             # Parameterized & data-driven tests
    ├── nested/          # @Nested test classes
    ├── grouping/        # @Tag / custom tag annotations
    ├── execution/order/ # Test execution ordering
    ├── extensions/      # Custom JUnit 5+ extensions
    ├── retry/           # Retry strategies (Pioneer, Rerunner)
    ├── repeat/          # @RepeatedTest
    └── suites/          # Suite lifecycle (@BeforeSuite, @AfterSuite)
        └── extension/   # Suite-like global lifecycle via BeforeAllCallback
```

---

## 📝 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

## 📚 Additional Resources

- [JUnit 6 User Guide](https://docs.junit.org/current/user-guide/)
- [JUnit Pioneer Documentation](https://junit-pioneer.org/docs/)
- [AssertJ Documentation](https://assertj.github.io/doc/)
- [Hamcrest Tutorial](https://hamcrest.org/JavaHamcrest/tutorial)
- [Lombok Features](https://projectlombok.org/features/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [Maven Surefire Report Plugin](https://maven.apache.org/surefire/maven-surefire-report-plugin/)
- [TestNG Workshop](https://github.com/a-oleynik/testng-workshop) — companion TestNG examples
- [Selenium Example — JUnit 6 branch](https://github.com/a-oleynik/selenium-example/tree/junit6) — real-world Selenium framework using JUnit 6

---

## 🔗 Useful Links

- **Java Download:** https://www.oracle.com/java/technologies/downloads/
- **Maven Download:** https://maven.apache.org/download.cgi
- **JUnit 6 Releases:** https://github.com/junit-team/junit5/releases
- **JUnit Pioneer Releases:** https://github.com/junit-pioneer/junit-pioneer/releases
- **Lombok Download:** https://projectlombok.org/download
- **IntelliJ Lombok Plugin:** https://plugins.jetbrains.com/plugin/6317-lombok
