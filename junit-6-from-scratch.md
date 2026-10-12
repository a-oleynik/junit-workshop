# JUnit 6 from Scratch — Article Learning Path

Companion learning path for **JUnit 6 с нуля: что делает тестовый фреймворк**
(`JUnit_6_in_one_evening`). The article describes JUnit 6; the examples in this
branch run on **JUnit 5.14.4**. Follow the same chapter order, starting with manual
checks and the problem of test discovery.

The article uses Calculator as its running example. The mapping below connects
each chapter to the corresponding workshop files. Revisit `CalculatorTest` as you
read: it contains the completed example with assertions, lifecycle, and exception
handling together.

## Article-to-code mapping

| Article chapter                         | Read or run                                                                                                                                                                                                                           | What to learn                                                                                                                 |
|-----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|
| 1. Before JUnit                         | [Main](src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/beforejunit/Main.java) / [Calculator](src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/Calculator.java)                                       | Compare expected and actual values in `main()`, then consider how to find and run more checks automatically.                  |
| 2. Test discovery and the first `@Test` | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java)                                                                                                                                | Start with `shouldAddTwoPositiveNumbers`; let JUnit discover and execute test methods instead of calling each check manually. |
| 3. Assertions                           | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [AssertTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/AssertTest.java)                                     | Turn an expected result into an assertion that can fail the test.                                                             |
| 3. Grouped assertions                   | [AssertAllTest](src/test/java/com/oleynik/qa/workshop/junit/topics/asserts/AssertAllTest.java)                                                                                                                                        | Use JUnit `assertAll()` to execute a group of related assertions and report their collected failures together.                |
| 4. Lifecycle and fixtures               | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [FixturesTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/FixturesTest.java)                                 | Create a fresh Calculator in `@BeforeEach`; explore setup and cleanup around tests and classes.                               |
| 5. Reporting                            | [Reporting commands](#reporting) / [README report commands](README.md#generate-the-full-maven-site-or-standard-gradle-test-report)                                                                                                    | Inspect results beyond console output, including HTML reports.                                                                |
| 6. We have just invented xUnit          | Revisit [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) and the report from chapter 5                                                                                          | Connect discovery, execution, assertions, fixtures, and reporting before moving to parameterized tests.                       |
| 7. Parameterized tests                  | [CalculatorParameterizedTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorParameterizedTest.java)                                                                                                      | Run one addition scenario with the five `@CsvSource` rows.                                                                    |
| 8.1. Tags                               | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [article tag commands](#tags)                                                                                                | Select tests with plain lowercase `@Tag("smoke")` and `@Tag("regression")`, as in the article.                                |
| 8.2. Disabled tests                     | [DisabledTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/DisabledTest.java)                                                                                                                                          | Temporarily disable a test with `@Disabled`.                                                                                  |
| 8.3. Assumptions                        | [AssumptionsTest](src/test/java/com/oleynik/qa/workshop/junit/topics/conditional/AssumptionsTest.java)                                                                                                                                | Execute a test only when its preconditions hold.                                                                              |
| 9.1. Exceptions                         | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [ExceptionTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/ExceptionTest.java)                               | Use `assertThrows`, starting with division by zero.                                                                           |
| 9.2. Timeouts                           | [TimeoutTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/TimeoutTest.java)                                                                                                                                            | Explore `assertTimeout` and `@Timeout`.                                                                                       |
| 10. Execution order                     | [ExecutionOrderWithTest](src/test/java/com/oleynik/qa/workshop/junit/topics/order/ExecutionOrderWithTest.java)                                                                                                                        | Inspect `@TestMethodOrder` and `@Order`; keep independent tests as the default design.                                        |
| 11. Parallel execution                  | [pom.xml](pom.xml) / [build.gradle](build.gradle) / [parallel execution notes](README.md#9-parallel-execution)                                                                                                                        | Find `junit.jupiter.execution.parallel.*`; this workshop already enables concurrent execution.                                |
| 12. Running JUnit with build tools      | [Quick Start](README.md#-quick-start) / [Command Examples](README.md#-command-examples)                                                                                                                                               | Run classes, methods, or tags with your chosen build tool; inspect how the build discovers tests.                             |
| 13. Extensions                          | [DBResourceExtensionTest](src/test/java/com/oleynik/qa/workshop/junit/topics/extensions/DBResourceExtensionTest.java) / [DBResourceExtension](src/main/java/com/oleynik/qa/workshop/junit/topics/extensions/DBResourceExtension.java) | Explore reusable callbacks through the workshop's resource example registered with `@RegisterExtension`.                      |
| 14. Recap: JUnit and xUnit              | Revisit the Calculator walkthrough below and the mapping above                                                                                                                                                                        | Explain which responsibilities belong to your test code and which belong to the framework.                                    |

Chapter 13 introduces `LoggingExtension` with `@ExtendWith` in the article.
The linked workshop example demonstrates the same extension model using resource
callbacks and `@RegisterExtension`; it is a related example, not a copy of that snippet.

For Appendix A, use [Quick Start](README.md#-quick-start), [pom.xml](pom.xml), and
[build.gradle](build.gradle). For Appendix B, return to the parallel configuration
in those build files and the [parallel execution notes](README.md#9-parallel-execution).

This route follows the article. The broader [Beginner Learning Path](README.md#-beginner-learning-path)
remains available for the workshop, including `@Nested`. Explore Hamcrest and AssertJ separately in [Advanced Learning Path](README.md#-advanced-learning-path).

## Reporting

Run commands from the repository root. Choose Maven or Gradle and follow that
build tool's commands throughout the walkthrough; each pair below is an alternative.
At chapter 5, run `CalculatorTest` and inspect its report.

**Maven — HTML test report**

```bash
# Linux / macOS
./mvnw clean site -Dtest=CalculatorTest
```

```powershell
# Windows PowerShell
.\mvnw.cmd clean site "-Dtest=CalculatorTest"
```

Open `target/site/surefire-report.html` after a successful build.

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
run `./mvnw clean site` or `./gradlew clean test`.
See the [README report commands and output paths](README.md#generate-the-full-maven-site-or-standard-gradle-test-report).

## Tags

Chapter 8.1 uses plain lowercase tags. In
[CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java),
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
[TagsTest](src/test/java/com/oleynik/qa/workshop/junit/topics/grouping/TagsTest.java):
it uses constants and custom meta-annotations `@Smoke` / `@Regression` for the
same lowercase `smoke` / `regression` tags. Maven profiles `smoke` / `regression`
and Gradle tasks `smokeTest` / `regressionTest` select these tags across the
workshop, including the Calculator example.

<a id="calculator-walkthrough"></a>

## Calculator walkthrough

Source links are relative to the repository root.

Follow the Calculator example from a manual `main()` check to JUnit tests.
The Calculator and test classes use the `com.oleynik.qa.workshop.junit.scenarios.calculator`
package; the manual entry point uses its `beforejunit` subpackage and the same Calculator.

| Step                               | Code                                                                                                                                  | What to look for                                                               |
|------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| 1. The code under test             | [Calculator.java](src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/Calculator.java)                                   | `add()` and integer `divide()`                                                 |
| 2. Start with a manual check       | [beforejunit/Main.java](src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/beforejunit/Main.java)                       | Expected and actual values, then `expected == actual`                          |
| 3. Tests, assertions and lifecycle | [CalculatorTest.java](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java)                           | `@Test`, `assertEquals`, `@BeforeEach` and `assertThrows` for division by zero |
| 4. One scenario, multiple inputs   | [CalculatorParameterizedTest.java](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorParameterizedTest.java) | `@ParameterizedTest` and the five `@CsvSource` rows from the article           |

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
the addition check with five input rows.

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

Expect eight successful test invocations: three regular tests and five parameterized
invocations. For individual JUnit features, continue with the [Feature Map](README.md#-feature-map)
and the [beginner learning path](README.md#-beginner-learning-path).

[⬆ Back to Table of Contents](README.md#-table-of-contents)

---
