# JUnit 4 Workshop Examples

[![Java CI with Maven](https://github.com/a-oleynik/junit-workshop/actions/workflows/maven.yml/badge.svg)](https://github.com/a-oleynik/junit-workshop/actions/workflows/maven.yml)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-4.13.2-green.svg)](https://junit.org/junit4/)
[![AssertJ](https://img.shields.io/badge/AssertJ-3.27.7-yellowgreen.svg)](https://assertj.github.io/doc/)
[![Hamcrest](https://img.shields.io/badge/Hamcrest-3.0-yellowgreen.svg)](https://hamcrest.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.46-red.svg)](https://projectlombok.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

> Companion code for the IT Talk **"JUnit 4 vs TestNG 6"**.
> 
> Previous edition: [TestNG vs. JUnit 4 slides](https://www.slideshare.net/oleynikandrey/it-talk-testng-6-vs-junit-4/) · [TestNG vs. JUnit 4 webinar](https://youtu.be/3C-Nu5mkyOQ?t=3207)
>
> **Related projects:**
> - 🧪 [TestNG Workshop](https://github.com/a-oleynik/testng-workshop) — companion TestNG examples to compare side-by-side with this repo
> - 🌐 [Selenium Example (JUnit 6 branch)](https://github.com/a-oleynik/selenium-example/tree/junit6) — real-world Selenium WebDriver framework built on top of JUnit 6

Hands-on examples for **JUnit 4** in Java — covering every major feature from basic assertions to rules, custom runners, parameterized tests, retry strategies, parallel execution, and suite orchestration.  
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
- [Learning Path — Beginners](#-learning-path--beginners)
- [Advanced Topics — Path for Senior Engineers](#-advanced-topics--path-for-senior-engineers)
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

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🤔 Why this repository?

Most JUnit 4 tutorials stop at `@Test` and `@Before`.  
This project goes further — it covers the **full JUnit 4 feature set** with real, runnable code you can clone, execute, and adapt immediately.

Use this repository if you want to:

- **learn JUnit from scratch** with a structured, step-by-step learning path
- **understand the differences between JUnit 4, JUnit 5, and JUnit 6** — what changed and why
- **see real runnable examples** instead of reading documentation only
- **explore advanced features** — rules (`@Rule`, `@ClassRule`), custom runners (`@RunWith`), retry strategies, parallel execution, categories, and parameterized tests
- **study automated testing patterns in Java** — soft assertions, data-driven tests, conditional execution, and more
- **understand suite orchestration** — three approaches to `@BeforeSuite` / `@AfterSuite` emulation: `@ClassRule ExternalResource`, `@BeforeClass`/`@AfterClass`, and `JUnitCore` + `RunListener`
- **compare JUnit and TestNG** patterns and idioms side-by-side with the companion [TestNG Workshop](https://github.com/a-oleynik/testng-workshop)
- **use it as material** for workshops, tech talks, onboarding sessions, and self-study

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 👥 Who Is This For?

| Audience                                                | What you will get                                                   |
|---------------------------------------------------------|---------------------------------------------------------------------|
| **QA engineers** new to JUnit 4                         | A guided tour of every major JUnit 4 feature with runnable examples |
| **Java developers** migrating from JUnit 4 to JUnit 5/6 | Clear view of what changes and what stays the same                  |
| **Developers** comparing JUnit 4 with TestNG            | Side-by-side comparison of patterns and idioms                      |
| **Workshop facilitators**                               | A ready-made project you can hand to attendees                      |

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🌿 Branches

This repository contains examples for multiple JUnit versions, each on its own branch:

| Branch                                                                          | JUnit version        | Description                                                               |
|---------------------------------------------------------------------------------|----------------------|---------------------------------------------------------------------------|
| [`master`](https://github.com/a-oleynik/junit-workshop/tree/master)             | **JUnit 6** (6.1.0)  | Latest JUnit 6 features                                                   |
| [`junit-5.14.4`](https://github.com/a-oleynik/junit-workshop/tree/junit-5.14.4) | **JUnit 5** (5.14.4) | Stable JUnit 5 examples — the most widely used version                    |
| [`junit-4.13.2`](https://github.com/a-oleynik/junit-workshop/tree/junit-4.13.2) | **JUnit 4** (4.13.2) | Current branch — legacy JUnit 4 examples — useful for migration reference |

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🚀 Quick Start

```bash
git clone https://github.com/a-oleynik/junit-workshop.git
cd junit-workshop
mvn clean test
```

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 📦 Prerequisites

| Tool              | Minimum version                   | Notes                                                     |
|-------------------|-----------------------------------|-----------------------------------------------------------|
| **JDK**           | 17 LTS                            |                                                           |
| **Maven**         | 3.9+ *(optional but recommended)* | Not required if using the included Maven Wrapper (`mvnw`) |
| **IDE**           | Any (IntelliJ IDEA recommended)   | Lombok plugin required for IDE support                    |
| **Lombok plugin** | Latest                            | IntelliJ: *Settings → Plugins → Lombok*                   |

> 💡 **Maven Wrapper included** — this project ships with `mvnw` (Linux/macOS) and `mvnw.cmd` (Windows).  
> You can use it instead of a locally installed Maven. The wrapper automatically downloads the correct Maven version on first run.
>
> ```bash
> # Linux / macOS
> ./mvnw clean test
>
> # Windows (Command Prompt / PowerShell)
> mvnw.cmd clean test
>
> # Windows with Git Bash
> ./mvnw clean test
> ```

[⬆ Back to Table of Contents](#-table-of-contents)

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
| `lombok`                          | `1.18.46` | `@Builder`, `@Data` — compile-time code generation; reduces boilerplate in model classes                                       |
| `opencsv`                         | `5.12.0`  | CSV file parsing for data-driven tests                                                                                         |
| Java source / target              | `17`      | Java language level for compilation                                                                                            |

> **Note on Hamcrest:** `junit:junit:4.13.2` bundles `hamcrest-core:1.3`, which is excluded in `pom.xml`.
> `hamcrest-library` is declared explicitly to get the full matcher library instead of the minimal core.

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🌍 Feature Map

| Package / folder     | Feature demonstrated                                                                         | Test class(es)                                                                       |
|----------------------|----------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| `general`            | Basic assertions (`assertEquals`, `assertThat`, `assertNull`, …)                             | `AssertTest`                                                                         |
| `general`            | Exception testing (`@Test(expected=…)`, `assertThrows`)                                      | `ExceptionTest`                                                                      |
| `general`            | Test lifecycle (`@Before`, `@After`, `@BeforeClass`, `@AfterClass`)                          | `FixturesTest`                                                                       |
| `general`            | Hamcrest matchers                                                                            | `HamcrestTest`                                                                       |
| `general`            | Timeouts (`@Test(timeout=…)`, `Timeout` rule)                                                | `TimeoutTest`                                                                        |
| `general`            | Ignoring / disabling tests (`@Ignore`)                                                       | `IgnoringTest`, `DisabledTest`                                                       |
| `general`            | Custom `RunListener` + `ExecutionListenerRunner`                                             | `ExecutionListenerRunnerTest`                                                        |
| `group/asserts`      | Soft assertions via `ErrorCollector` rule                                                    | `SoftAssertErrorCollectorTest`, `SoftAssertErrorCollector2Test`                      |
| `group/asserts`      | AssertJ soft assertions                                                                      | `SoftAssertionsAssertJTest`, `SoftAssertionsAssertJBDDTest`                          |
| `group/asserts`      | JUnit 4 soft assert pattern (`AssertAll` rule)                                               | `SoftAssertTest`                                                                     |
| `conditional`        | Assumptions (`Assume.assumeTrue`, `Assume.assumeThat`)                                       | `AssumeTest`, `AssumptionsTest`                                                      |
| `conditional`        | Assumptions in `@BeforeClass`                                                                | `AssumeBeforeClassTest`, `AssumptionsBeforeAllTest`                                  |
| `ddt`                | Parameterized tests — `@RunWith(Parameterized.class)`                                        | `ParameterizationTest`, `Parameterization2Test`                                      |
| `ddt`                | Lifecycle in parameterized tests (`@Before` / `@After`)                                      | `BeforeAfterParameterizationTest`                                                    |
| `ddt`                | JUnitParams (`@JUnitParamsRunner`, `@Parameters`)                                            | `JUnitParamsTest`                                                                    |
| `ddt`                | TNG DataProvider integration (`src/test/resources/numbers.csv`)                              | `DataProviderTest`, `DataProvider2Test`                                              |
| `ddt`                | Theories — `@Theory` + `@DataPoints` (Cartesian-style)                                       | `TheoryTest`                                                                         |
| `nested`             | Nested test classes (`HierarchicalContextRunner`)                                            | `NestedTest`                                                                         |
| `grouping`           | `@Category` with custom marker interfaces                                                    | `CategoriesTest`                                                                     |
| `grouping`           | Tagging via `@Category` annotations                                                          | `TagsTest`                                                                           |
| `execution/order`    | Test execution ordering (`@FixMethodOrder`, `@OrderWith`)                                    | `FixMethodOrderTest`, `ExecutionOrderWithTest`                                       |
| `rules`              | External resource rule (`@Rule ExternalResource`)                                            | `ExternalResourceRuleTest`                                                           |
| `rules`              | `TestWatcher` rule (`MonitorRule`)                                                           | `TestWatcherRuleTest`                                                                |
| `repeat`             | Retry via custom runner (`@RunWith(RetryRunner.class)`)                                      | `RetryRunnerTest`                                                                    |
| `repeat`             | Retry via test-level rule (`RetryTestRule`)                                                  | `RetriedTestRuleTest`                                                                |
| `repeat`             | Retry via method-level rule (`RetryMethodRule`)                                              | `RetriedMethodRuleTest`                                                              |
| `repeat`             | Repeat demos                                                                                 | `RetryRepeatedTest`                                                                  |
| `suites/lifecycle`   | Suite lifecycle via `@ClassRule ExternalResource` — `@BeforeSuite` / `@AfterSuite` emulation | `BeforeAfterSuite`, `SuiteLifecycleFirstCase`, `SuiteLifecycleSecondCase`            |
| `suites/beforeclass` | Suite lifecycle via `@BeforeClass` / `@AfterClass` directly on the suite class               | `BeforeClassSuite`, `BeforeClassFirstCase`, `BeforeClassSecondCase`                  |
| `suites/listener`    | Suite lifecycle via `JUnitCore` + `RunListener` (`testRunStarted` / `testRunFinished`)       | `JUnitCoreRunnerTest`, `SuiteRunListener`, `ListenerFirstCase`, `ListenerSecondCase` |

[⬆ Back to Table of Contents](#-table-of-contents)

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

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🎯 Advanced Topics — Path for Senior Engineers

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

### 9. Parallel Test Execution

Surefire can run tests in parallel without any additional dependencies.
`pom.xml` already contains the parallel config — just uncomment it:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.5.5</version>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>3</threadCount>
    </configuration>
</plugin>
```

**Available `parallel` modes:**

| Mode      | What runs in parallel                                  |
|-----------|--------------------------------------------------------|
| `methods` | Test methods within each class (safest starting point) |
| `classes` | Whole test classes                                     |
| `both`    | Classes and methods simultaneously                     |
| `suites`  | Test suites                                            |
| `all`     | Suites, classes, and methods — maximum parallelism     |

**Enable from the command line without editing `pom.xml`:**

```bash
mvn clean test -Dparallel=methods -DthreadCount=3
```

> ⚠️ **Thread safety:** tests running in parallel must not share mutable static state.
> Using `@FixMethodOrder` together with parallel methods can produce unpredictable results.

**Alternative — `ConcurrentTestRunner`** (`junit-runners` is already in `pom.xml`):

```java
@RunWith(ConcurrentTestRunner.class)
public class MyParallelTest { ... }
```

Runs methods within a single class concurrently, independent of Surefire config.

### 10. Maven Profiles (Category-based filtering)

Categories used in this project: `SmokeTests` and `RegressionTests` (marker interfaces in `grouping/categories/`).

**Direct category filtering (works out of the box):**

```bash
# Run only Smoke tests
mvn clean test -Dgroups=com.oleynik.qa.workshop.junit.grouping.categories.SmokeTests

# Run only Regression tests
mvn clean test -Dgroups=com.oleynik.qa.workshop.junit.grouping.categories.RegressionTests
```

**Via Maven profiles:**

```bash
mvn clean test -P SmokeTests
mvn clean test -P RegressionTests
```

> ⚠️ **Note:** The `SmokeTests` and `RegressionTests` profiles in `pom.xml` currently have the `<groups>` line commented out.
> To enable profile-based category filtering, uncomment `<groups>${testcase.groups}</groups>` in the Surefire plugin configuration
> and update the profile property values to the fully-qualified category class names
> (e.g. `com.oleynik.qa.workshop.junit.grouping.categories.SmokeTests`).

### 11. Test Suite (`@BeforeSuite` / `@AfterSuite` emulation)

JUnit 4 has **no native `@BeforeSuite` / `@AfterSuite`**.  
Two approaches work correctly — both are demonstrated in this project.

#### Approach A — `@ClassRule ExternalResource` (`suites/lifecycle/`)

`BeforeAfterSuite` wraps suite execution using a `@ClassRule` backed by `ExternalResource`.  
`before()` / `after()` are guaranteed to run even if a test throws, because `ExternalResource`
uses try-finally internally.

```java
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SuiteLifecycleFirstCase.class,
        SuiteLifecycleSecondCase.class
})
public class BeforeAfterSuite {

    @ClassRule
    public static ExternalResource suiteSetup = new ExternalResource() {
        @Override
        protected void before() {
            System.out.println("[BeforeSuite] runs once before all suite tests");
        }

        @Override
        protected void after() {
            System.out.println("[AfterSuite] runs once after all suite tests");
        }
    };
}
```

#### Approach B — `@BeforeClass` / `@AfterClass` directly (`suites/beforeclass/`)

`@BeforeClass` and `@AfterClass` placed on a `@RunWith(Suite.class)` class **are** called by
the `Suite` runner — they fire once before and after all member classes, equivalent to
`@BeforeSuite` / `@AfterSuite` behaviour.

```java
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeforeClassFirstCase.class,
        BeforeClassSecondCase.class
})
public class BeforeClassSuite {

    @BeforeClass
    public static void beforeSuite() {
        System.out.println("[BeforeSuite] runs once before all suite tests");
    }

    @AfterClass
    public static void afterSuite() {
        System.out.println("[AfterSuite] runs once after all suite tests");
    }
}
```

**Execution lifecycle (identical for both approaches):**

```
[BeforeSuite]  before() / @BeforeClass on suite class
  [BeforeClass] FirstCase
    first_test_in_first_case
    second_test_in_first_case
  [AfterClass]  FirstCase
  [BeforeClass] SecondCase
    first_test_in_second_case
    second_test_in_second_case
  [AfterClass]  SecondCase
[AfterSuite]   after() / @AfterClass on suite class
```

> **⚠️ Naming convention:** classes selected by a suite must **not** be named `*Test` or `*Tests`.  
> Use `*Case` or `*Scenario` instead.  
> If they matched Surefire's default discovery patterns they would execute **twice** —  
> once directly by Surefire and once again through the suite.

> **⚙️ Maven config:** `pom.xml` adds `**/*Suite.java` to Surefire `<includes>` so both suite
> classes are automatically discovered by `mvn clean test`:

```xml
<includes>
    <include>**/Test*.java</include>
    <include>**/*Test.java</include>
    <include>**/*Tests.java</include>
    <include>**/*TestCase.java</include>
    <include>**/*Suite.java</include>
</includes>
```

**Comparing the three JUnit 4 suite-lifecycle approaches:**

|                             | Approach A — `@ClassRule ExternalResource` | Approach B — `@BeforeClass`/`@AfterClass` | Approach C — `JUnitCore` + `RunListener`  |
|-----------------------------|--------------------------------------------|-------------------------------------------|-------------------------------------------|
| Requires a suite class      | ✅ `@RunWith(Suite.class)` needed           | ✅ `@RunWith(Suite.class)` needed          | ❌ No suite class needed                   |
| Member class naming         | `*Case` (not `*Test`)                      | `*Case` (not `*Test`)                     | `*Case` (not `*Test`)                     |
| Syntax complexity           | Verbose (anonymous class)                  | Simple — same as regular test class       | Moderate — wrapper `*Test` needed         |
| Teardown guarantee          | ✅ try-finally inside `ExternalResource`    | ⚠️ No guarantee if setup throws           | ⚠️ No guarantee if setup throws           |
| Reusable across suites      | ✅ Extract rule to a shared field           | ❌ Must duplicate the methods              | ✅ Attach same listener to any `JUnitCore` |
| Result inspection available | ❌                                          | ❌                                         | ✅ `Result` object in `testRunFinished`    |

#### Approach C — `JUnitCore` + `RunListener` (`suites/listener/`)

`JUnitCoreRunnerTest` is a regular `*Test` class (discovered by Surefire).  
Inside its `@Test` method it creates a `JUnitCore`, attaches a `SuiteRunListener`, and runs
the `*Case` classes programmatically.

```java
public class JUnitCoreRunnerTest {

    @Test
    public void run_suite_via_junit_core_with_listener() {
        JUnitCore core = new JUnitCore();
        core.addListener(new SuiteRunListener());   // registers before/after callbacks

        Result result = core.run(ListenerFirstCase.class, ListenerSecondCase.class);

        Assert.assertEquals(4, result.getRunCount());
        Assert.assertEquals(0, result.getFailureCount());
    }
}
```

`SuiteRunListener` overrides the two lifecycle methods:

```java
public class SuiteRunListener extends RunListener {
    @Override
    public void testRunStarted(Description description) {
        // fires ONCE before the first class → @BeforeSuite
    }

    @Override
    public void testRunFinished(Result result) {
        // fires ONCE after the last class → @AfterSuite
        // result.getRunCount() / getFailureCount() available here
    }
}
```

**Execution lifecycle:**

```
[BeforeSuite]  SuiteRunListener.testRunStarted          ← fires once
  [BeforeClass] ListenerFirstCase
    first_test_in_first_case
    second_test_in_first_case
  [AfterClass]  ListenerFirstCase
  [BeforeClass] ListenerSecondCase
    first_test_in_second_case
    second_test_in_second_case
  [AfterClass]  ListenerSecondCase
[AfterSuite]   SuiteRunListener.testRunFinished         ← fires once
```

> **Note on Surefire `<listener>` property (global variant):**  
> The commented-out `<listener>` block in `pom.xml` registers a `RunListener` for the **entire**
> Maven test run (all test classes in the project), not just a specific group.  
> Use this when you need a global hook for the whole build — e.g., starting a Docker container
> before any test in the project runs.

**JUnit 4 vs JUnit 6 suite lifecycle:**

|                         | JUnit 4 (all three approaches)      | JUnit 6 (`@Suite` + `@BeforeSuite`) |
|-------------------------|-------------------------------------|-------------------------------------|
| Native annotation       | ❌ No `@BeforeSuite` / `@AfterSuite` | ✅ `@BeforeSuite` / `@AfterSuite`    |
| Test class naming       | `*Case` / `*Scenario` (not `*Test`) | `*Case` / `*Scenario` (not `*Test`) |
| Requires suite class    | A & B: ✅ required / C: ❌ not needed | ✅ `@SelectClasses` required         |
| Tests run independently | ❌ Only via suite entry point        | ❌ Only via suite entry class        |

### 12. Custom RunListener

`ExecutionListenerRunnerTest`  
Attach a custom `RunListener` (via `ExecutionListenerRunner`) to observe test lifecycle events:
test started, finished, failed, assumed, ignored.

### 13. Surefire HTML Report Generation

```bash
mvn clean site
# or
mvn clean surefire-report:report
```

> Reports are written to `target/site/surefire-report.html`

[⬆ Back to Table of Contents](#-table-of-contents)

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

### Run tests in parallel

```bash
mvn clean test -Dparallel=methods -DthreadCount=3
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
# @ClassRule ExternalResource approach
mvn test "-Dtest=BeforeAfterSuite"

# @BeforeClass / @AfterClass approach
mvn test "-Dtest=BeforeClassSuite"
```

> Both suites are also discovered automatically by `mvn clean test` because `pom.xml` includes
> `**/*Suite.java` in Surefire `<includes>`.  
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

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🚦 CI / CD

This project uses **GitHub Actions** with a manually triggered workflow.

### Workflow — `maven.yml`

Triggered manually from **Actions → Run workflow** on GitHub.

| Input    | Required | Description                                                                                                                                                              |
|----------|----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `groups` | No       | Category filter for the by-category job (fully-qualified class name, e.g. `com.oleynik.qa.workshop.junit.grouping.categories.SmokeTests`). Leave empty to skip that job. |

### Jobs

| Job          | Name                          | Runs when                             | Command                                  |
|--------------|-------------------------------|---------------------------------------|------------------------------------------|
| `regression` | Regression — all tests        | Always                                | `./mvnw -B clean site`                   |
| `by-tag`     | By category — `{groups}`      | Only when `groups` input is filled in | `./mvnw -B clean site -Dgroups={groups}` |

Each job uploads two artifacts after completion — including on failure (`if: always()`):

| Artifact                                        | Source path                | Contents                                                               |
|-------------------------------------------------|----------------------------|------------------------------------------------------------------------|
| `surefire-report` / `surefire-report-{tag}`     | `target/site/`             | Full HTML report — open `surefire-report.html` in a browser            |
| `junit-xml-results` / `junit-xml-results-{tag}` | `target/surefire-reports/` | Raw JUnit XML files — compatible with CI dashboards and report parsers |

### How to trigger

1. Go to the **Actions** tab on GitHub
2. Select **Java CI with Maven** in the left panel
3. Click **Run workflow**
4. Optionally fill in the **`groups`** field with a fully-qualified category class name (e.g. `com.oleynik.qa.workshop.junit.grouping.categories.SmokeTests`) to also run the by-category job
5. Click **Run workflow**

### Downloading the reports

1. Click the completed workflow run
2. Scroll to **Artifacts** → download **`surefire-report`** (HTML) or **`junit-xml-results`** (XML)
3. Open `surefire-report.html` in a browser

### Generating the report locally

```bash
# All tests
mvn clean site

# By category (JUnit 4 requires the fully-qualified category class name)
mvn clean site -Dgroups=com.oleynik.qa.workshop.junit.grouping.categories.SmokeTests
mvn clean site -Dgroups=com.oleynik.qa.workshop.junit.grouping.categories.RegressionTests
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
- rule/runner placement (`src/main/…/rules/` and `src/main/…/runners/`, not test sources)
- JUnit 4 annotations only (`@Before`, `@After`, `@Ignore`, `@Category`, `@FixMethodOrder`) — no Jupiter annotations on the classpath
- suite lifecycle via `@ClassRule ExternalResource`, `@BeforeClass`/`@AfterClass`, or `JUnitCore` + `RunListener`

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 📁 Project Structure

```
AGENTS.md                    # AI Agent task recipes and conventions
README.md                    # This file
LICENSE
pom.xml                      # Maven build — dependencies, Surefire config, profiles
mvnw / mvnw.cmd              # Maven Wrapper scripts (no local Maven installation required)
.github/
└── copilot-instructions.md  # GitHub Copilot workspace instructions (always-on)
.junie/
└── guidelines.md            # JetBrains Junie project guidelines
src/
├── main/java/com/oleynik/qa/workshop/junit/
│   ├── annotations/     # Custom annotations (Retry, DataSource, RetryConfiguration)
│   ├── dataproviders/   # JUnitDataProvider (external data provider implementation)
│   ├── listeners/       # ExecutionListener (RunListener implementation)
│   ├── model/           # Domain model (User, MyDoubleWrapper, MyServer)
│   ├── rules/           # Custom rules (DBResourceRule, MonitorRule, RetryTestRule, RetryMethodRule, AssertAll)
│   ├── runners/         # Custom runners (RetryRunner, ExecutionListenerRunner)
│   └── Utils.java
└── test/
    ├── java/com/oleynik/qa/workshop/junit/
    │   ├── general/          # Core assertions, lifecycle, exceptions, timeouts, @Ignore
    │   ├── group/asserts/    # Soft assertions (ErrorCollector, AssertJ)
    │   ├── conditional/      # Assumptions (Assume)
    │   ├── ddt/              # Parameterized, JUnitParams, DataProvider, Theories
    │   ├── nested/           # Nested tests (HierarchicalContextRunner)
    │   ├── grouping/         # @Category usage
    │   │   └── categories/  # SmokeTests, RegressionTests marker interfaces
    │   ├── execution/order/  # Test execution ordering (@FixMethodOrder, @OrderWith)
    │   ├── rules/            # Rule examples (ExternalResource, TestWatcher)
    │   ├── repeat/           # Retry strategies (runner, test rule, method rule)
    │   └── suites/           # Test suites (@RunWith(Suite.class))
    │       ├── lifecycle/    # Approach A: @ClassRule ExternalResource (BeforeAfterSuite + case classes)
    │       ├── beforeclass/  # Approach B: @BeforeClass / @AfterClass (BeforeClassSuite + case classes)
    │       └── listener/     # Approach C: JUnitCore + RunListener (JUnitCoreRunnerTest + case classes)
    └── resources/
        └── numbers.csv       # Input data for DataProviderTest / DataProvider2Test (number → expected factorial)
target/                      # Generated by Maven — not committed to git
├── surefire-reports/        # Raw JUnit XML results (TEST-*.xml) + console output (*.txt)
└── site/
    └── surefire-report.html # HTML test report — open in a browser after `mvn site`
```

[⬆ Back to Table of Contents](#-table-of-contents)

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
- [Maven Wrapper](https://maven.apache.org/wrapper/) — run Maven without a local installation
- [TestNG Workshop](https://github.com/a-oleynik/testng-workshop) — companion TestNG examples
- [Selenium Example — JUnit 6 branch](https://github.com/a-oleynik/selenium-example/tree/junit6) — real-world Selenium framework using JUnit 6

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 🔗 Useful Links

- [**Java Download**](https://www.oracle.com/java/technologies/downloads/)
- [**Maven Download**](https://maven.apache.org/download.cgi)
- [**JUnit 5 & 6 Releases**](https://github.com/junit-team/junit5/releases)
- [**JUnit 4 Releases**](https://github.com/junit-team/junit4/releases)
- [**JUnit 6 Released — Clean-Up, Modernization & Minimal Disruption**](https://medium.com/@andrei.oleynik/junit-6-released-clean-up-modernization-minimal-disruption-d3ecf11b64ad)
- [**Lombok Download**](https://projectlombok.org/download)
- [**IntelliJ Lombok Plugin**](https://plugins.jetbrains.com/plugin/6317-lombok)

[⬆ Back to Table of Contents](#-table-of-contents)

---

## 📝 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

[⬆ Back to Table of Contents](#-table-of-contents)
