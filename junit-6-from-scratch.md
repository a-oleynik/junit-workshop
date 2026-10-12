# JUnit 6 from Scratch — Article Learning Path (JUnit 4 adaptation)

Companion learning path for **JUnit 6 с нуля: что делает тестовый фреймворк**
(`JUnit_6_in_one_evening`). The article uses JUnit 6 on the `master` branch.
This branch adapts its examples to **JUnit 4.13.2**, keeping the article's chapter
order from manual checks and test discovery onward. The mapping identifies the
JUnit 4 APIs to use at each step.

The article uses Calculator as its running example. Revisit `CalculatorTest` as
you read: this branch's completed example uses `org.junit.Test`, `@Before`, and
`org.junit.Assert`. JUnit 4 has no Jupiter `assertAll()`; for grouped failures,
see the [ErrorCollector example](src/test/java/com/oleynik/qa/workshop/junit/topics/asserts/SoftAssertErrorCollectorTest.java)
or the optional assertion libraries in [Advanced Topics](README.md#-advanced-topics--path-for-senior-engineers).

## Article-to-code mapping

| Article chapter                         | JUnit 4 code or configuration                                                                                                                                                                                           | What to learn                                                                                                                                                                                     |
|-----------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1. Before JUnit                         | [Main](src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/beforejunit/Main.java) / [Calculator](src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/Calculator.java)                         | Compare expected and actual values in `main()`, then consider how to find and run more checks automatically.                                                                                      |
| 2. Test discovery and the first `@Test` | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java)                                                                                                                  | Start with `should_add_two_positive_numbers`; JUnit discovers public methods annotated with `org.junit.Test`.                                                                                     |
| 3. Assertions                           | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [AssertTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/AssertTest.java)                       | Use `org.junit.Assert` assertions to turn an expected result into a test outcome.                                                                                                                 |
| 4. Lifecycle and fixtures               | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [FixturesTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/FixturesTest.java)                   | Create a fresh Calculator in `@Before`; explore `@After`, `@BeforeClass`, and `@AfterClass`.                                                                                                      |
| 5. Reporting                            | [Reporting commands](#reporting) / [README report commands](README.md#generate-the-full-maven-site-or-standard-gradle-test-report)                                                                                      | Inspect results beyond console output, including HTML reports.                                                                                                                                    |
| 6. We have just invented xUnit          | Revisit [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) and the report from chapter 5                                                                            | Connect discovery, execution, assertions, fixtures, and reporting before moving to parameterized tests.                                                                                           |
| 7. Parameterized tests                  | [CalculatorParameterizedTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorParameterizedTest.java)                                                                                        | Run five input rows with `@RunWith(Parameterized.class)`, `@Parameterized.Parameters`, and constructor injection.                                                                                 |
| 8.1. Tags → categories                  | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [article category commands](#categories)                                                                       | Select tests with `@Category(SmokeTests.class)` and `@Category(RegressionTests.class)`, adapting the article's tags.                                                                              |
| 8.2. Disabled tests → ignored tests     | [IgnoringTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/IgnoringTest.java)                                                                                                                            | Temporarily skip a test with `@Ignore`.                                                                                                                                                           |
| 8.3. Assumptions                        | [AssumeTest](src/test/java/com/oleynik/qa/workshop/junit/topics/conditional/AssumeTest.java)                                                                                                                            | Use `org.junit.Assume` to run tests only when their preconditions hold.                                                                                                                           |
| 9.1. Exceptions                         | [CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java) / [ExceptionTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/ExceptionTest.java)                 | Use `org.junit.Assert.assertThrows` (JUnit 4.13+), starting with division by zero; compare with `@Test(expected = ...)`.                                                                          |
| 9.2. Timeouts                           | [TimeoutTest](src/test/java/com/oleynik/qa/workshop/junit/topics/general/TimeoutTest.java)                                                                                                                              | Set a time limit in milliseconds with `@Test(timeout = ...)`.                                                                                                                                     |
| 10. Execution order                     | [FixMethodOrderTest](src/test/java/com/oleynik/qa/workshop/junit/topics/order/FixMethodOrderTest.java) / [ExecutionOrderWithTest](src/test/java/com/oleynik/qa/workshop/junit/topics/order/ExecutionOrderWithTest.java) | Inspect `@FixMethodOrder` and JUnit 4.13's `@OrderWith`; keep independent tests as the default design.                                                                                            |
| 11. Parallel execution                  | [pom.xml](pom.xml) / [build.gradle](build.gradle) / [parallel execution notes](README.md#10-parallel-test-execution)                                                                                                    | Parallel execution is opt-in: the Surefire `parallel` and `threadCount` settings are commented out; Gradle does not configure multiple test forks. Jupiter parallel properties do not apply here. |
| 12. Running JUnit with build tools      | [Quick Start](README.md#-quick-start) / [Command Examples](README.md#-command-examples)                                                                                                                                 | Run classes, methods, or categories with your chosen build tool; inspect how the build discovers tests.                                                                                           |
| 13. Extensions → Rules                  | [ExternalResourceRuleTest](src/test/java/com/oleynik/qa/workshop/junit/topics/rules/ExternalResourceRuleTest.java) / [DBResourceRule](src/main/java/com/oleynik/qa/workshop/junit/topics/rules/DBResourceRule.java)     | Reuse resource setup and cleanup with `@Rule` and `ExternalResource`.                                                                                                                             |
| 14. Recap: JUnit and xUnit              | Revisit the Calculator walkthrough below and the mapping above                                                                                                                                                          | Explain which responsibilities belong to your test code and which belong to the framework.                                                                                                        |

Chapter 13 introduces `LoggingExtension` with `@ExtendWith` in the JUnit 6 article.
This JUnit 4 branch uses Rules and runners instead of Jupiter Extensions. The linked
resource example illustrates reusable lifecycle behavior through a public `@Rule`
field and `ExternalResource.before()` / `after()`; it is a related example rather
than a copy of the article's snippet.

For Appendix A, use this branch's [Quick Start](README.md#-quick-start),
[pom.xml](pom.xml), and [build.gradle](build.gradle). For Appendix B, follow the
JUnit 4 [parallel execution notes](README.md#10-parallel-test-execution).

The broader [Beginner Learning Path](README.md#-beginner-learning-path)
remains available for the workshop, including nested contexts through
`HierarchicalContextRunner`. Explore Hamcrest and AssertJ separately in
[Advanced Learning Path](README.md#-advanced-learning-path).

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

## Categories

Chapter 8.1 uses tags in JUnit 6. JUnit 4 adapts tags using `@Category` with marker
interfaces. In
[CalculatorTest](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java),
`should_add_two_positive_numbers` matches the article's example:

```java
@Test
@Category(SmokeTests.class)
public void should_add_two_positive_numbers() {
    assertEquals(5, calculator.add(2, 3));
}
```

`should_add_zero` uses `@Category(RegressionTests.class)`. Categories label tests for
filtering; they do not disable them when running without a category filter.

Run the article's smoke example from the repository root with your chosen build tool:

**Maven**

```bash
# Linux / macOS
./mvnw test -Dtest=CalculatorTest -Dgroups=com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests
```

```powershell
# Windows PowerShell
.\mvnw.cmd test "-Dtest=CalculatorTest" "-Dgroups=com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests"
```

**Gradle**

```bash
# Linux / macOS
./gradlew test --tests "*CalculatorTest" -DincludeCategories=com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests
```

```powershell
# Windows PowerShell
.\gradlew.bat test --tests "*CalculatorTest" "-DincludeCategories=com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests"
```

Expect one test: `should_add_two_positive_numbers`. Replace `SmokeTests` with `RegressionTests`
to run only `should_add_zero`. The class filter keeps these commands focused on the
article example.

For the workshop's grouping approach across multiple tests, see
[CategoriesTest](src/test/java/com/oleynik/qa/workshop/junit/topics/grouping/CategoriesTest.java):
it uses `@Category` with the `SmokeTests` and `RegressionTests` marker interfaces, including
combining multiple categories on a single test method. You can also run categories
via Maven profiles (`-P Smoke` / `-P Regression`) or dedicated Gradle tasks
(`smokeTest` / `regressionTest`).

<a id="calculator-walkthrough"></a>

## Calculator walkthrough

Source links are relative to the repository root.

This branch adapts the Calculator example from the article to JUnit 4.13.2. Follow it from a manual `main()` check to JUnit tests.
The Calculator and test classes use the `com.oleynik.qa.workshop.junit.scenarios.calculator`
package; the manual entry point uses its `beforejunit` subpackage and the same Calculator.

| Step                               | Code                                                                                                                                  | What to look for                                                                                           |
|------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| 1. The code under test             | [Calculator.java](src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/Calculator.java)                                   | `add()` and integer `divide()`                                                                             |
| 2. Start with a manual check       | [beforejunit/Main.java](src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/beforejunit/Main.java)                       | Expected and actual values, then `expected == actual`                                                      |
| 3. Tests, assertions and lifecycle | [CalculatorTest.java](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java)                           | `org.junit.Test`, `@Before`, `assertEquals` and `assertThrows` for division by zero                        |
| 4. One scenario, multiple inputs   | [CalculatorParameterizedTest.java](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorParameterizedTest.java) | `@RunWith(Parameterized.class)`, `@Parameterized.Parameters` and constructor injection for five input rows |

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
the `@Test` methods automatically, and `@Before` creates a fresh Calculator for
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
