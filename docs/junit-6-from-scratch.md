# JUnit 6 from Scratch — Article Learning Path

Companion learning path for **JUnit 6 from Scratch: What a Test Framework Actually Does**
(`JUnit_6_from_scratch`). Follow the article's chapter order on the JUnit 6
`master` branch, starting with manual checks and the problem of test discovery.

The article uses Calculator as its running example. The mapping below connects
each chapter to the corresponding workshop files. Revisit `CalculatorTest` as you
read: it contains the completed example with assertions, lifecycle, tags,
assumptions, and exception handling together.

## Article-to-code mapping

| Article chapter                         | Read or run                                                                                                                                                                                                                                 | What to learn                                                                                                                                                  |
|-----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1. Before JUnit                         | [Main](../src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/beforejunit/Main.java) / [Calculator](../src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/Calculator.java)                                       | Compare expected and actual values in `main()`, then consider how to find and run more checks automatically.                                                   |
| 2. Test discovery and the first `@Test` | [CalculatorTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java)                                                                                                                                   | Start with `shouldAddTwoPositiveNumbers`; let JUnit discover and execute test methods instead of calling each check manually.                                  |
| 3. Assertions                           | [CalculatorTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [AssertTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/general/AssertTest.java)                                     | Turn an expected result into an assertion that can fail the test.                                                                                              |
| 3. Grouped assertions                   | [AssertAllTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/asserts/AssertAllTest.java)                                                                                                                                           | Use JUnit `assertAll()` to execute a group of related assertions and report their collected failures together.                                                 |
| 4. Lifecycle and fixtures               | [CalculatorTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [LifecycleTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/general/LifecycleTest.java)                               | Create a fresh Calculator in `@BeforeEach`; explore setup and cleanup around tests and classes.                                                                |
| 4. Fixture setup examples               | [InlineFixtureTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/fixtures/InlineFixtureTest.java) / [ImplicitFixtureTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/fixtures/ImplicitFixtureTest.java)                 | Compare the same shopping-cart scenarios with setup inside each test or in `@BeforeEach`. Both use a fresh fixture; only the setup location differs.           |
| 5. Reporting                            | [Reporting commands](#reporting) / [README report commands](../README.md#generate-an-html-test-report)                                                                                                                                      | Inspect results beyond console output, including HTML reports.                                                                                                 |
| 6. We have just invented xUnit          | Revisit [CalculatorTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) and the report from chapter 5                                                                                             | Connect discovery, execution, assertions, fixtures, and reporting before moving to parameterized tests.                                                        |
| 7. Parameterized tests                  | [CalculatorParameterizedTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorParameterizedTest.java)                                                                                                         | Run one addition scenario with the seven `@CsvSource` rows.                                                                                                    |
| 8.1. Tags                               | [CalculatorTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [article tag commands](#tags)                                                                                                   | Select tests with plain lowercase `@Tag("smoke")` and `@Tag("regression")`, as in the article.                                                                 |
| 8.2. Disabled tests                     | [DisabledTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/general/DisabledTest.java)                                                                                                                                             | Temporarily disable a test with `@Disabled`.                                                                                                                   |
| 8.3. Assumptions                        | [CalculatorTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [assumptions example](#assumptions)                                                                                             | Check a runtime precondition with `assumeTrue()` before an assertion.                                                                                          |
| 9.1. Exceptions                         | [CalculatorTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [ExceptionTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/general/ExceptionTest.java)                               | Use `assertThrows`, starting with division by zero.                                                                                                            |
| 9.2. Timeouts                           | [TimeoutTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/general/TimeoutTest.java)                                                                                                                                               | Explore `assertTimeout()` and `@Timeout` examples; read the comment on `assertTimeoutPreemptively()` and `ThreadLocal` risks.                                  |
| 10. Execution order                     | [ExecutionOrderWithTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/order/ExecutionOrderWithTest.java)                                                                                                                           | Inspect `@TestMethodOrder` and `@Order`; keep independent tests as the default design.                                                                         |
| 11. Parallel execution                  | [junit-platform.properties](../src/test/resources/junit-platform.properties) / [pom.xml](../pom.xml) / [build.gradle](../build.gradle) / [parallel execution notes](../README.md#9-parallel-execution)                                      | Find `junit.jupiter.execution.parallel.*` in `junit-platform.properties`; classes run in parallel while methods within each class run sequentially by default. |
| 12. Running JUnit with build tools      | [Quick Start](../README.md#-quick-start) / [Command Examples](../README.md#-command-examples)                                                                                                                                               | Run classes, methods, or tags with your chosen build tool; inspect how the build discovers tests.                                                              |
| 13. Extensions                          | [LoggingExtensionTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/extensions/basic/LoggingExtensionTest.java) / [LoggingExtension](../src/main/java/com/oleynik/qa/workshop/junit/topics/extensions/basic/LoggingExtension.java) | Register a simple `BeforeEachCallback` with `@ExtendWith` and observe the log message before the test body.                                                    |
| 14. Recap: JUnit and xUnit              | Revisit the Calculator walkthrough below and the mapping above                                                                                                                                                                              | Explain which responsibilities belong to your test code and which belong to the framework.                                                                     |

Chapter 13 starts with `LoggingExtension`: a `BeforeEachCallback` registered via
`@ExtendWith`, matching the article's introductory example. For additional resource
management and outcome observation examples, continue with
[advanced extensions](../README.md#6-custom-junit-5-extensions).

For the appendix on a minimal JUnit 6 project, use [Quick Start](../README.md#-quick-start), [pom.xml](../pom.xml), and
[build.gradle](../build.gradle). For chapter 11 on parallel execution, return to [junit-platform.properties](../src/test/resources/junit-platform.properties),
the commented alternative configuration in those build files, and the [parallel execution notes](../README.md#9-parallel-execution).

This route follows the article. The broader [Beginner Learning Path](../README.md#-beginner-learning-path)
remains available for the workshop, including `@Nested`. Explore Hamcrest and AssertJ separately in [Advanced Learning Path](../README.md#-advanced-learning-path).

## Parallel execution

Chapter 11 and this workshop use the same execution modes in
[junit-platform.properties](../src/test/resources/junit-platform.properties):

```properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=same_thread
junit.jupiter.execution.parallel.mode.classes.default=concurrent
```

Top-level test classes may run in parallel; methods within each class run
sequentially by default. Explicit `@Execution` annotations can override these modes.
The workshop also configures the thread pool; those settings do not enable
parallel methods. For the optional configuration that allows both classes and
methods to run concurrently, see
[Advanced: parallel classes and methods](../README.md#advanced-parallel-classes-and-methods).

## Reporting

Run commands from the repository root. Choose Maven or Gradle and follow that
build tool's commands throughout the walkthrough; each pair below is an alternative.
At chapter 5, run `CalculatorTest` and inspect its report.

**Maven — HTML test report**

```bash
# Linux / macOS
./mvnw clean surefire-report:report -Dtest=CalculatorTest
```

```powershell
# Windows PowerShell
.\mvnw.cmd clean surefire-report:report "-Dtest=CalculatorTest"
```

Open `target/reports/surefire.html` after a successful build.

**Gradle — test report**

```bash
# Linux / macOS
./gradlew clean test --tests "*CalculatorTest"
```

```powershell
# Windows PowerShell
.\gradlew.bat clean test --tests "*CalculatorTest"
```

Open `build/reports/tests/test/index.html` after a successful build. Gradle's `test`
task generates the HTML report automatically. The XML results are in
`build/test-results/test/`.

Read the test names, counts, and outcomes, then continue to the xUnit recap and
parameterized tests.

To generate a report for the full workshop instead of only `CalculatorTest`,
run `./mvnw clean surefire-report:report` or `./gradlew clean test`.
See the [README report commands and output paths](../README.md#generate-an-html-test-report).

## Tags

Chapter 8.1 uses plain lowercase tags. In
[CalculatorTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java),
`shouldAddTwoPositiveNumbers` matches the article's example:

```java
@Test
@Tag("smoke")
void shouldAddTwoPositiveNumbers() {
    assertEquals(5, calculator.add(2, 3));
}
```

`shouldAddZero` uses `@Tag("regression")`. Tags label tests for filtering; they do
not disable them when running without a tag filter.

Run the article's smoke example from the repository root with your chosen build tool:

**Maven**

```bash
# Linux / macOS
./mvnw test -Dtest=CalculatorTest -Dgroups=smoke
```

```powershell
# Windows PowerShell
.\mvnw.cmd test "-Dtest=CalculatorTest" "-Dgroups=smoke"
```

**Gradle**

```bash
# Linux / macOS
./gradlew test --tests "*CalculatorTest" -DincludeTags=smoke
```

```powershell
# Windows PowerShell
.\gradlew.bat test --tests "*CalculatorTest" "-DincludeTags=smoke"
```

Expect one test: `shouldAddTwoPositiveNumbers`. Replace `smoke` with `regression`
to run only `shouldAddZero`. The class filter keeps these commands focused on the
article example.

For the workshop's advanced approach, see
[TagsTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/grouping/TagsTest.java):
it uses constants and custom meta-annotations `@Smoke` / `@Regression` for the
same lowercase `smoke` / `regression` tags. Maven profiles `smoke` / `regression`
and Gradle tasks `smokeTest` / `regressionTest` select these tags across the
workshop, including the Calculator example.

## Assumptions

Chapter 8.3 starts with one runtime precondition. In
[CalculatorTest](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java):

```java
@Test
void shouldAddWhenExternalServiceIsConfigured() {
    assumeTrue(System.getenv("EXTERNAL_SERVICE_URL") != null);

    assertEquals(5, calculator.add(2, 3));
}
```

`assumeTrue` is imported from `org.junit.jupiter.api.Assumptions`. The environment
check matches the article; the local Calculator assertion makes the example
runnable without contacting an external service.

When `EXTERNAL_SERVICE_URL` is absent, JUnit aborts this test before the assertion;
build reports show it as skipped, not failed. When the variable is set, execution
continues to the assertion. To try both outcomes, run `CalculatorTest` with the
variable unset, then set it to an example value such as `https://example.invalid`
and rerun. With Gradle, add `--rerun-tasks` after changing the environment so the
test executes again even if its compiled sources are unchanged.

Additional workshop examples:

- [AssumptionsTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/conditional/AssumptionsTest.java) — assumptions in `@BeforeEach` and test methods, plus `assumingThat()`.
- [AssumptionsBeforeAllTest](../src/test/java/com/oleynik/qa/workshop/junit/topics/conditional/AssumptionsBeforeAllTest.java) — a more advanced example with an assumption in `@BeforeAll`.

<a id="calculator-walkthrough"></a>

## Calculator walkthrough

Source links are relative to this document; run all commands from the repository root.

Follow the Calculator example from a manual `main()` check to JUnit tests.
The Calculator and test classes use the `com.oleynik.qa.workshop.junit.scenarios.calculator`
package; the manual entry point uses its `beforejunit` subpackage and the same Calculator.

| Step                               | Code                                                                                                                                     | What to look for                                                               |
|------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| 1. The code under test             | [Calculator.java](../src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/Calculator.java)                                   | `add()` and integer `divide()`                                                 |
| 2. Start with a manual check       | [beforejunit/Main.java](../src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/beforejunit/Main.java)                       | Expected and actual values, then `expected == actual`                          |
| 3. Tests, assertions and lifecycle | [CalculatorTest.java](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java)                           | `@Test`, `assertEquals`, `@BeforeEach` and `assertThrows` for division by zero |
| 4. One scenario, multiple inputs   | [CalculatorParameterizedTest.java](../src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorParameterizedTest.java) | `@ParameterizedTest` and the seven `@CsvSource` rows from the article          |

Run the manual example from the repository root:

**Maven**

```bash
# Linux / macOS
./mvnw compile
java -cp target/classes com.oleynik.qa.workshop.junit.scenarios.calculator.beforejunit.Main
```

```powershell
# Windows PowerShell
.\mvnw.cmd compile
java -cp target/classes com.oleynik.qa.workshop.junit.scenarios.calculator.beforejunit.Main
```

**Gradle**

```bash
# Linux / macOS
./gradlew classes
java -cp build/classes/java/main com.oleynik.qa.workshop.junit.scenarios.calculator.beforejunit.Main
```

```powershell
# Windows PowerShell
.\gradlew.bat classes
java -cp build/classes/java/main com.oleynik.qa.workshop.junit.scenarios.calculator.beforejunit.Main
```

Expected output (either build tool):

```text
Expected: 5
Actual:   5
true
```

Continue with `CalculatorTest`: `assertEquals` checks the expected result, JUnit runs
the `@Test` methods automatically, and `@BeforeEach` creates a fresh Calculator for
each test. `assertThrows` verifies division by zero. The parameterized test then runs
the addition check with seven input rows. Its `name = "{0} + {1} = {2}"` pattern
shows each pair of inputs and the expected sum, for example `2 + 3 = 5`.
`quoteTextArguments = false` keeps these numeric CSV values unquoted in JUnit 6.

Run these two test classes from the repository root:

**Maven**

```bash
# Linux / macOS
./mvnw test -Dtest=CalculatorTest,CalculatorParameterizedTest
```

```powershell
# Windows PowerShell
.\mvnw.cmd test "-Dtest=CalculatorTest,CalculatorParameterizedTest"
```

**Gradle**

```bash
# Linux / macOS
./gradlew test --tests "*CalculatorTest" --tests "*CalculatorParameterizedTest"
```

```powershell
# Windows PowerShell
.\gradlew.bat test --tests "*CalculatorTest" --tests "*CalculatorParameterizedTest"
```

The Gradle HTML report at `build/reports/tests/test/index.html` now covers both
selected classes.

Expect eleven test invocations: four regular tests and seven parameterized invocations.
With `EXTERNAL_SERVICE_URL` unset, ten pass and the assumptions example is skipped.
With it set, all eleven pass. For individual JUnit features, continue with the [Feature Map](../README.md#-feature-map)
and the [beginner learning path](../README.md#-beginner-learning-path).

[⬆ Back to Table of Contents](../README.md#-table-of-contents)

---
