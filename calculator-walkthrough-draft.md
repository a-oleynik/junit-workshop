<a id="calculator-walkthrough"></a>

# Calculator — article walkthrough

Отдельный черновик для обсуждения. Не включён в README; решение о его дальнейшем размещении остаётся открытым. Ссылки ниже указаны относительно корня репозитория.

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

Expected output:

```text
Expected: 5
Actual:   5
true
```

Continue with `CalculatorTest`: `assertEquals` checks the expected result, JUnit runs
the `@Test` methods automatically, and `@BeforeEach` creates a fresh Calculator for
each test. `assertThrows` verifies division by zero. The parameterized test then runs
the addition check with five input rows.

Run these two test classes from the repository root with Maven:

```bash
# Linux / macOS
./mvnw test -Dtest=CalculatorTest,CalculatorParameterizedTest
```

```powershell
# Windows PowerShell
.\mvnw.cmd test "-Dtest=CalculatorTest,CalculatorParameterizedTest"
```

Expect eight successful test invocations: three regular tests and five parameterized
invocations. For individual JUnit features, continue with the [Feature Map](README.md#-feature-map)
and the [beginner learning path](README.md#-learning-path--beginners).

[⬆ Back to Table of Contents](README.md#-table-of-contents)

---
