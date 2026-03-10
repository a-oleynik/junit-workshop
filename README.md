# JUnit 5 Workshop Examples

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-5.14.3-green.svg)](https://junit.org/junit5/)
[![JUnit Pioneer](https://img.shields.io/badge/JUnit_Pioneer-2.3.0-green.svg)](https://junit-pioneer.org/)
[![AssertJ](https://img.shields.io/badge/AssertJ-3.27.7-yellowgreen.svg)](https://assertj.github.io/doc/)
[![Hamcrest](https://img.shields.io/badge/Hamcrest-3.0-yellowgreen.svg)](https://hamcrest.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.42-red.svg)](https://projectlombok.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

> Companion code for the IT Talk **"JUnit 5 vs TestNG 7"**.  
> Previous edition: [TestNG vs. JUnit 4 slides](https://www.slideshare.net/oleynikandrey/it-talk-testng-6-vs-junit-4/) · [TestNG vs. JUnit 4 webinar](https://youtu.be/3C-Nu5mkyOQ?t=3189)

---

## 📋 Table of Contents

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
| **QA engineers** new to JUnit 5                     | A guided tour of every major feature with runnable examples                   |
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

| Library                      | Version used |
|------------------------------|--------------|
| JUnit Jupiter (JUnit 5)      | `5.14.3`     |
| JUnit Platform               | `1.14.3`     |
| JUnit Pioneer                | `2.3.0`      |
| TNG DataProvider for JUnit 5 | `2.12`       |
| AssertJ                      | `3.27.7`     |
| Hamcrest                     | `3.0`        |
| Lombok                       | `1.18.42`    |
| Rerunner Jupiter             | `2.1.6`      |
| OpenCSV                      | `5.12.0`     |
| Java source / target         | `21`         |

---

## 🌍 Feature Map

| Package / folder  | Feature demonstrated                                                   | Test class(es)                                              |
|-------------------|------------------------------------------------------------------------|-------------------------------------------------------------|
| `general`         | Basic assertions (`assertEquals`, `assertTrue`, `assertNull`, …)       | `AssertTest`                                                |
| `general`         | Exception testing (`assertThrows`)                                     | `ExceptionTest`                                             |
| `general`         | Test fixtures (`@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`) | `FixturesTest`                                              |
| `general`         | Hamcrest matchers                                                      | `HamcrestTest`                                              |
| `general`         | Timeouts (`@Timeout`)                                                  | `TimeoutTest`                                               |
| `general`         | Disabling tests (`@Disabled`)                                          | `DisabledTest`                                              |
| `general`         | Display names & name generators                                        | `DisplayNameTest`, `DisplayNameGenerationTest`              |
| `group/asserts`   | Grouped / soft assertions (`assertAll`)                                | `AssertAllTest`                                             |
| `group/asserts`   | AssertJ soft assertions                                                | `SoftAssertionsAssertJTest`, `SoftAssertionsAssertJBDDTest` |
| `group/asserts`   | JUnit 5 soft assert pattern                                            | `SoftAssertTest`                                            |
| `conditional`     | Assumptions (`assumeTrue`, `assumeThat`)                               | `AssumptionsTest`, `AssumptionsBeforeAllTest`               |
| `ddt`             | Parameterized tests — `@MethodSource`                                  | `ParameterizationTest`                                      |
| `ddt`             | Parameterized tests — `@ValueSource` / `@CsvSource`                    | `ValueSourceTest`                                           |
| `ddt`             | CSV file data source                                                   | `CSVParameterizationTest`                                   |
| `ddt`             | TNG DataProvider integration                                           | `DataProviderTest`                                          |
| `ddt`             | JUnit Pioneer Cartesian product                                        | `PioneerCartesianProductTest`                               |
| `nested`          | `@Nested` test classes                                                 | `NestedTest`                                                |
| `grouping`        | Tagging with `@Tag` and custom tag annotations                         | `TagsTest`                                                  |
| `execution/order` | Test execution ordering (`@TestMethodOrder`)                           | `ExecutionOrderWithTest`                                    |
| `extensions`      | Custom `Extension` (`@RegisterExtension`)                              | `DBResourceExtensionTest`                                   |
| `extensions`      | `TestWatcher` extension                                                | `TestWatcherExtensionTest`                                  |
| `retry`           | Retry with JUnit Pioneer (`@RetryingTest`)                             | `RetryPioneerTest`                                          |
| `retry`           | Retry with Rerunner Jupiter                                            | `RetryRerunnerTest`                                         |
| `repeat`          | `@RepeatedTest`                                                        | `RetryRepeatedTest`                                         |

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

### 5. Custom JUnit 5 Extensions

`DBResourceExtensionTest`, `TestWatcherExtensionTest`  
Implement `BeforeAllCallback`, `AfterAllCallback`, and `TestWatcher` to manage external resources and observe test
outcomes.

### 6. Test Execution Order

`ExecutionOrderWithTest`  
Control method execution order with `@TestMethodOrder` and `@Order`.

### 7. Retry Strategies

`RetryPioneerTest` — `@RetryingTest(maxAttempts, minSuccess)` via JUnit Pioneer  
`RetryRerunnerTest` — Rerunner Jupiter integration  
`RetryRepeatedTest` — JUnit 5 native `@RepeatedTest`

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

### 10. Surefire HTML Report Generation

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
│   └── model/          # Domain model (User, MyDoubleWrapper, MyServer)
└── test/java/com/oleynik/qa/workshop/junit/
    ├── general/         # Core assertions, fixtures, exceptions, display names
    ├── group/asserts/   # Grouped / soft assertions
    ├── conditional/     # Assumptions
    ├── ddt/             # Parameterized & data-driven tests
    ├── nested/          # @Nested test classes
    ├── grouping/        # @Tag / custom tag annotations
    ├── execution/order/ # Test execution ordering
    ├── extensions/      # Custom JUnit 5 extensions
    ├── retry/           # Retry strategies (Pioneer, Rerunner)
    └── repeat/          # @RepeatedTest
```

---

## 📝 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

## 📚 Additional Resources

- [JUnit 5 User Guide](https://docs.junit.org/5.14.3/overview.html)
- [JUnit Pioneer Documentation](https://junit-pioneer.org/docs/)
- [AssertJ Documentation](https://assertj.github.io/doc/)
- [Hamcrest Tutorial](https://hamcrest.org/JavaHamcrest/tutorial)
- [Lombok Features](https://projectlombok.org/features/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [Maven Surefire Report Plugin](https://maven.apache.org/surefire/maven-surefire-report-plugin/)

---

## 🔗 Useful Links

- **Java Download:** https://www.oracle.com/java/technologies/downloads/
- **Maven Download:** https://maven.apache.org/download.cgi
- **JUnit 5 Releases:** https://github.com/junit-team/junit5/releases
- **JUnit Pioneer Releases:** https://github.com/junit-pioneer/junit-pioneer/releases
- **Lombok Download:** https://projectlombok.org/download
- **IntelliJ Lombok Plugin:** https://plugins.jetbrains.com/plugin/6317-lombok
