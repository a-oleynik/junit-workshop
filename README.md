# JUnit 4 Workshop Examples

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-4.13.2-green.svg)](https://junit.org/junit4/)
[![AssertJ](https://img.shields.io/badge/AssertJ-3.27.7-yellowgreen.svg)](https://assertj.github.io/doc/)
[![Hamcrest](https://img.shields.io/badge/Hamcrest-3.0-yellowgreen.svg)](https://hamcrest.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.44-red.svg)](https://projectlombok.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

> Companion code for the IT Talk **"JUnit 4 vs TestNG 6"**.
> Previous edition: [TestNG vs. JUnit 4 slides](https://www.slideshare.net/oleynikandrey/it-talk-testng-6-vs-junit-4/) · [TestNG vs. JUnit 4 webinar](https://youtu.be/3C-Nu5mkyOQ?t=3189)
>
> **Related projects:**
> - 🔄 [JUnit 6 Workshop](https://github.com/a-oleynik/junit-workshop/tree/master) — same workshop upgraded to JUnit 6
> - 🧪 [TestNG Workshop](https://github.com/a-oleynik/testng-workshop) — companion TestNG examples to compare side-by-side with this repo
> - 🌐 [Selenium Example (JUnit 6 branch)](https://github.com/a-oleynik/selenium-example/tree/junit6) — real-world Selenium WebDriver framework built on top of JUnit 6

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

| Audience                                                | What you will get                                                   |
|---------------------------------------------------------|---------------------------------------------------------------------|
| **QA engineers** new to JUnit 4                         | A guided tour of every major JUnit 4 feature with runnable examples |
| **Java developers** migrating from JUnit 4 to JUnit 5/6 | Clear view of what changes and what stays the same                  |
| **Developers** comparing JUnit 4 with TestNG            | Side-by-side comparison of patterns and idioms                      |
| **Workshop facilitators**                               | A ready-made project you can hand to attendees                      |

---

## 📦 Prerequisites

| Tool              | Minimum version                 | Notes                                   |
|-------------------|---------------------------------|-----------------------------------------|
| **JDK**           | 17 LTS                          |                                         |
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

| Maven artifact                    | Version   | Purpose                                                                                                                        |
|-----------------------------------|-----------|--------------------------------------------------------------------------------------------------------------------------------|
| `junit:junit`                     | `4.13.2`  | JUnit 4 core — `@Test`, `@Before`, `@After`, `@BeforeClass`, `@AfterClass`, `@Ignore`, `@Rule`, `@ClassRule`, `@RunWith`       |
| `JUnitParams`                     | `1.1.1`   | `@RunWith(JUnitParamsRunner.class)` + `@Parameters` — cleaner parameterized test syntax than the built-in Parameterized runner |
| `junit4-dataprovider`             | `2.12`    | TNG-style `@DataProvider` integration for JUnit 4                                                                              |
| `junit-runners`                   | `1.3`     | NitorCreations concurrent test runner and other utilities                                                                      |
| `junit-hierarchicalcontextrunner` | `4.12.2`  | Enables nested test classes like JUnit 5+ `@Nested` via `HierarchicalContextRunner`                                            |
| `assertj-core`                    | `3.27.7`  | Fluent assertion library; `SoftAssertions` for collecting multiple failures                                                    |
| `hamcrest-library`                | `3.0`     | Matcher-based assertions — `assertThat(value, matcher)`                                                                        |
| `lombok`                          | `1.18.44` | `@Builder`, `@Data` — compile-time code generation; reduces boilerplate in model classes                                       |
| `opencsv`                         | `5.12.0`  | CSV file parsing for data-driven tests                                                                                         |
| Java source / target              | `17`      | Java language level for compilation                                                                                            |

> **Note on Hamcrest:** `junit:junit:4.13.2` bundles `hamcrest-core:1.3`, which is excluded in `pom.xml`.
> `hamcrest-library` is declared explicitly to get the full matcher library instead of the minimal core.

---

## 🌍 Feature Map

| Package / folder  | Feature demonstrated                                                | Test class(es)                                                  |
|-------------------|---------------------------------------------------------------------|-----------------------------------------------------------------|
| `general`         | Basic assertions (`assertEquals`, `assertThat`, `assertNull`, …)    | `AssertTest`                                                    |
| `general`         | Exception testing (`@Test(expected=…)`, `assertThrows`)             | `ExceptionTest`                                                 |
| `general`         | Test lifecycle (`@Before`, `@After`, `@BeforeClass`, `@AfterClass`) | `FixturesTest`                                                  |
| `general`         | Hamcrest matchers                                                   | `HamcrestTest`                                                  |
| `general`         | Timeouts (`@Test(timeout=…)`, `Timeout` rule)                       | `TimeoutTest`                                                   |
| `general`         | Ignoring / disabling tests (`@Ignore`)                              | `IgnoringTest`, `DisabledTest`                                  |
| `general`         | Custom `RunListener` + `ExecutionListenerRunner`                    | `ExecutionListenerRunnerTest`                                   |
| `group/asserts`   | Soft assertions via `ErrorCollector` rule                           | `SoftAssertErrorCollectorTest`, `SoftAssertErrorCollector2Test` |
| `group/asserts`   | AssertJ soft assertions                                             | `SoftAssertionsAssertJTest`, `SoftAssertionsAssertJBDDTest`     |
| `group/asserts`   | JUnit 4 soft assert pattern (`AssertAll` rule)                      | `SoftAssertTest`                                                |
| `conditional`     | Assumptions (`Assume.assumeTrue`, `Assume.assumeThat`)              | `AssumeTest`, `AssumptionsTest`                                 |
| `conditional`     | Assumptions in `@BeforeClass`                                       | `AssumeBeforeClassTest`, `AssumptionsBeforeAllTest`             |
| `ddt`             | Parameterized tests — `@RunWith(Parameterized.class)`               | `ParameterizationTest`, `Parameterization2Test`                 |
| `ddt`             | Lifecycle in parameterized tests (`@Before` / `@After`)             | `BeforeAfterParameterizationTest`                               |
| `ddt`             | JUnitParams (`@JUnitParamsRunner`, `@Parameters`)                   | `JUnitParamsTest`                                               |
| `ddt`             | TNG DataProvider integration                                        | `DataProviderTest`, `DataProvider2Test`                         |
| `ddt`             | Theories — `@Theory` + `@DataPoints` (Cartesian-style)              | `TheoryTest`                                                    |
| `nested`          | Nested test classes (`HierarchicalContextRunner`)                   | `NestedTest`                                                    |
| `grouping`        | `@Category` with custom marker interfaces                           | `CategoriesTest`                                                |
| `grouping`        | Tagging via `@Category` annotations                                 | `TagsTest`                                                      |
| `execution/order` | Test execution ordering (`@FixMethodOrder`, `@OrderWith`)           | `FixMethodOrderTest`, `ExecutionOrderWithTest`                  |
| `rules`           | External resource rule (`@Rule ExternalResource`)                   | `ExternalResourceRuleTest`                                      |
| `rules`           | `TestWatcher` rule (`MonitorRule`)                                  | `TestWatcherRuleTest`                                           |
| `repeat`          | Retry via custom runner (`@RunWith(RetryRunner.class)`)             | `RetryRunnerTest`                                               |
| `repeat`          | Retry via test-level rule (`RetryTestRule`)                         | `RetriedTestRuleTest`                                           |
| `repeat`          | Retry via method-level rule (`RetryMethodRule`)                     | `RetriedMethodRuleTest`                                         |
| `repeat`          | Repeat demos                                                        | `RetryRepeatedTest`                                             |
| `suites`          | Test suite (`@RunWith(Suite.class)`, `@Suite.SuiteClasses`)         | `MySuite`                                                       |

---

## 🌱 Learning Path — Beginners

Work through these topics in order; each builds on the previous one.

1. **Basic assertions** → `AssertTest`  
   Learn `assertEquals`, `assertThat`, `assertNull`, and `fail`.

2. **Test lifecycle** → `FixturesTest`  
   Understand `@Before`, `@After`, `@BeforeClass`, `@AfterClass`.

3. **Exception testing** → `ExceptionTest`  
   Use `@Test(expected=…)` and the modern `assertThrows` (available since JUnit 4.13).

4. **Ignoring tests** → `IgnoringTest`  
   Skip tests cleanly with `@Ignore` and understand the difference from failing tests.

5. **Hamcrest matchers** → `HamcrestTest`  
   Write expressive assertions with `assertThat`.

6. **Soft assertions** → `SoftAssertErrorCollectorTest`  
   Use `ErrorCollector` rule so multiple failures are collected before reporting.

7. **Assumptions** → `AssumeTest`  
   Skip tests dynamically when preconditions are not met.

8. **Parameterized tests** → `ParameterizationTest`  
   Drive one test method with many data rows using `@RunWith(Parameterized.class)`.

9. **Nested tests** → `NestedTest`  
   Organise related scenarios using inner context classes with `HierarchicalContextRunner`.

10. **Categories** → `CategoriesTest`  
    Mark tests as `SmokeTests` or `RegressionTests` and run subsets from the command line.

**Run the whole beginner suite:**

```bash
mvn clean test
```

---

## 🎯 "Advanced Topics" — Path for Senior Engineers

These topics assume familiarity with JUnit 4 basics.

### 1. Custom Category Interfaces

`CategoriesTest` → `grouping/categories/` package  
Define empty marker interfaces (`SmokeTests`, `RegressionTests`) and apply them with `@Category`.
Combine multiple categories on one test method and filter runs with Maven profiles.

### 2. CSV & External Data Providers

`DataProviderTest`, `DataProvider2Test`  
Load test data from `@DataProvider` methods. The `JUnitDataProvider` class in `main/dataproviders/`
shows how to integrate a custom data provider with the TNG `junit4-dataprovider` library.

### 3. JUnitParams

`JUnitParamsTest`  
Use `@RunWith(JUnitParamsRunner.class)` with `@Parameters` for cleaner, more readable parameterized
tests that keep parameters close to the test method.

### 4. Theories (`@Theory` + `@DataPoints`)

`TheoryTest`  
Use `@RunWith(Theories.class)` with `@DataPoints` and `@FromDataPoints` to generate all combinations
of parameter sets automatically — the JUnit 4 native approach to Cartesian-style parameterization.

### 5. Soft Assertions (AssertJ)

`SoftAssertionsAssertJTest`, `SoftAssertionsAssertJBDDTest`  
Collect all assertion failures before reporting — no early bail-out.

### 6. Rules: `ExternalResource` & `TestWatcher`

`ExternalResourceRuleTest` → `@Rule ExternalResource` (`DBResourceRule`)  
`TestWatcherRuleTest` → `@Rule TestWatcher` (`MonitorRule`)  
JUnit 4 `@Rule` and `@ClassRule` are the equivalent of JUnit 5+ `@ExtendWith`.
`ExternalResource` manages external resources (databases, servers); `TestWatcher` observes test outcomes.

### 7. Test Execution Order

`FixMethodOrderTest` — `@FixMethodOrder(MethodSorters.NAME_ASCENDING)`  
`ExecutionOrderWithTest` — `@OrderWith(Alphanumeric.class)` (JUnit 4.13+)  
Two ways to control method execution order in JUnit 4.

### 8. Retry Strategies

`RetryRunnerTest` — `@RunWith(RetryRunner.class)` + `@RetryConfiguration(maxTries=…)` (custom runner)  
`RetriedTestRuleTest` — `RetryTestRule` via `@Rule` (test-level retry)  
`RetriedMethodRuleTest` — `RetryMethodRule` via `@Rule` (method-level rule applied per method)  
Three progressively more granular approaches to retrying flaky tests.

### 9. Test Suite

`MySuite` → `suites/` package  
Group multiple test classes under a single entry point using `@RunWith(Suite.class)` and `@Suite.SuiteClasses`.

> **⚠️ Discovery:** `MySuite` does not match Surefire’s default `*Test` patterns.
> Run it explicitly: `mvn test "-Dtest=MySuite"`

### 10. Custom RunListener

`ExecutionListenerRunnerTest`  
Attach a custom `RunListener` (via `ExecutionListenerRunner`) to observe test lifecycle events:
test started, finished, failed, assumed, ignored.

### 11. Maven Profiles (Category-based filtering)

```bash
# Run only Smoke tests
mvn clean test -P SmokeTests

# Run only Regression tests
mvn clean test -P RegressionTests
```

### 12. Surefire HTML Report Generation

```bash
mvn clean site
# or
mvn clean surefire-report:report
```

> Reports are written to `target/site/surefire-report.html`

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

### Run tests by category

```bash
# Full category class name is required in JUnit 4
mvn clean test -Dgroups=com.oleynik.qa.workshop.junit.grouping.categories.SmokeTests
mvn clean test -Dgroups=com.oleynik.qa.workshop.junit.grouping.categories.RegressionTests
```

> ⚠️ In JUnit 4, `-Dgroups` requires the **fully-qualified class name** of the category interface,
> unlike JUnit 5+ where short tag strings are used (e.g. `-Dgroups=Smoke`).

### Run tests via Maven profile

```bash
mvn clean test -P SmokeTests
mvn clean test -P RegressionTests
```

### Run only a specific suite

```bash
mvn test "-Dtest=MySuite"
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
│   ├── annotations/     # Custom annotations (Retry, DataSource, RetryConfiguration)
│   ├── dataproviders/   # JUnitDataProvider (external data provider implementation)
│   ├── listeners/       # ExecutionListener (RunListener implementation)
│   ├── model/           # Domain model (User, MyDoubleWrapper, MyServer)
│   ├── rules/           # Custom rules (DBResourceRule, MonitorRule, RetryTestRule, RetryMethodRule, AssertAll)
│   └── runners/         # Custom runners (RetryRunner, ExecutionListenerRunner)
└── test/java/com/oleynik/qa/workshop/junit/
    ├── general/          # Core assertions, lifecycle, exceptions, timeouts, @Ignore
    ├── group/asserts/    # Soft assertions (ErrorCollector, AssertJ)
    ├── conditional/      # Assumptions (Assume)
    ├── ddt/              # Parameterized, JUnitParams, DataProvider, Theories
    ├── nested/           # Nested tests (HierarchicalContextRunner)
    ├── grouping/         # @Category and custom category interfaces
    ├── execution/order/  # Test execution ordering (@FixMethodOrder, @OrderWith)
    ├── rules/            # Rule examples (ExternalResource, TestWatcher)
    ├── repeat/           # Retry strategies (runner, test rule, method rule)
    └── suites/           # Test suites (@RunWith(Suite.class))
```

---

## 📝 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

## 📚 Additional Resources

- [JUnit 4 Documentation](https://junit.org/junit4/)
- [JUnit 4 JavaDoc](https://junit.org/junit4/javadoc/latest/)
- [JUnitParams](https://github.com/Pragmatists/JUnitParams)
- [TNG DataProvider for JUnit 4](https://github.com/TNG/junit-dataprovider)
- [HierarchicalContextRunner](https://github.com/bechte/junit-hierarchical-context-runner)
- [AssertJ Documentation](https://assertj.github.io/doc/)
- [Hamcrest Tutorial](https://hamcrest.org/JavaHamcrest/tutorial)
- [Lombok Features](https://projectlombok.org/features/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [Maven Surefire Report Plugin](https://maven.apache.org/surefire/maven-surefire-report-plugin/)
- [TestNG Workshop](https://github.com/a-oleynik/testng-workshop) — companion TestNG examples
- [JUnit 6 Workshop](https://github.com/a-oleynik/junit-workshop/tree/master) — same workshop using JUnit 6
- [Selenium Example — JUnit 6 branch](https://github.com/a-oleynik/selenium-example/tree/junit6) — real-world Selenium framework using JUnit 6

---

## 🔗 Useful Links

- **Java Download:** https://www.oracle.com/java/technologies/downloads/
- **Maven Download:** https://maven.apache.org/download.cgi
- **JUnit 4 Releases:** https://github.com/junit-team/junit4/releases
- **Lombok Download:** https://projectlombok.org/download
- **IntelliJ Lombok Plugin:** https://plugins.jetbrains.com/plugin/6317-lombok

