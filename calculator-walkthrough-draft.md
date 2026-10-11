<a id="calculator-walkthrough"></a>

# Calculator — article walkthrough (JUnit 4)

Отдельный черновик для обсуждения. Не включён в README; решение о его дальнейшем размещении остаётся открытым. Ссылки ниже указаны относительно корня репозитория.

This branch adapts the Calculator example from the article to JUnit 4.13.2. The files share the
`com.oleynik.qa.workshop.junit.scenarios.calculator` package.

| Step                               | Code                                                                                                                                  | What to look for                                                                                           |
|------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| 1. The code under test             | [Calculator.java](src/main/java/com/oleynik/qa/workshop/junit/scenarios/calculator/Calculator.java)                                   | `add()` and integer `divide()`                                                                             |
| 2. Tests, assertions and lifecycle | [CalculatorTest.java](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorTest.java)                           | `org.junit.Test`, `@Before`, `assertEquals` and `assertThrows` for division by zero                        |
| 3. One scenario, multiple inputs   | [CalculatorParameterizedTest.java](src/test/java/com/oleynik/qa/workshop/junit/scenarios/calculator/CalculatorParameterizedTest.java) | `@RunWith(Parameterized.class)`, `@Parameterized.Parameters` and constructor injection for five input rows |

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
