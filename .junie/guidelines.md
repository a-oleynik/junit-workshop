# Junie Guidelines — JUnit Workshop

## Project purpose
A hands-on Java workshop demonstrating the full JUnit feature set across JUnit 4, JUnit 5, and JUnit 6. Used as companion material for a tech talk comparing JUnit 6 with TestNG 7. **This branch targets JUnit 5 (5.14.4)**; the `master` branch targets JUnit 6 (6.1.3).

## Stack
- **Java 21** — language level, no preview features
- **Maven 3.9+ / Gradle 9.6.0+** with Maven & Gradle Wrappers (`mvnw` / `mvnw.cmd`, `gradlew` / `gradlew.bat`)
- **JUnit 5.14.4** — Jupiter API, params, platform-suite (versions managed via `junit-bom` BOM)
- **JUnit Pioneer 2.3.0** — `@RetryingTest`, `@CartesianTest`
- **junit-jupiter-params-dataprovider 2.12** — TNG-style `@DataProvider` for Jupiter
- **AssertJ 3.27.7** — fluent assertions, `SoftAssertions`, `BDDSoftAssertions`
- **Hamcrest 3.0** — matcher-based assertions
- **Lombok 1.18.48** — `@Data`, `@Builder` for model classes
- **rerunner-jupiter 2.1.6** — `@RepeatedIfExceptionsTest` retry
- **org.gradle.test-retry 1.6.5** — `-PretryCount=N` test retry support in Gradle
- **opencsv 5.12.0** — CSV file parsing for data-driven tests

## Directory structure

`topics` contains examples of individual JUnit features; `scenarios` contains complete examples that combine several features and can be referenced by multiple articles. The Calculator scenario is in `scenarios/calculator` in both main and test sources.

The manual starting point is `src/main/java/…/scenarios/calculator/beforejunit/Main.java`; it uses the shared `Calculator` and leads into `CalculatorTest`.

```text
src/
├── main/java/com/oleynik/qa/workshop/junit/
│   ├── scenarios/
│   │   └── calculator/
│   │       ├── Calculator.java  # Article example: addition and integer division
│   │       └── beforejunit/
│   │           └── Main.java    # Manual comparison before introducing JUnit
│   └── topics/
│       ├── annotations/         # Custom annotation types and helpers
│       ├── dataproviders/       # ArgumentsProvider / DataProvider implementations
│       ├── extensions/          # DBResourceExtension, TestWatcherExtension, SuiteLikeLifecycleExtension
│       ├── model/               # Shared domain model (User, MyDoubleWrapper, MyServer, ShoppingCart)
│       ├── Factorial.java       # Example production class for parameterized tests
│       ├── PrimeNumber.java
│       └── Utils.java           # Helper used by timeout examples
└── test/
    ├── java/com/oleynik/qa/workshop/junit/
    │   ├── scenarios/
    │   │   └── calculator/
    │   │       ├── CalculatorTest.java
    │   │       └── CalculatorParameterizedTest.java
    │   └── topics/
    │       ├── general/         # Basic assertions, lifecycle, exceptions, display names, timeouts
    │       ├── fixtures/        # InlineFixtureTest and ImplicitFixtureTest: fresh cart per test
    │       ├── asserts/         # assertAll and AssertJ soft assertions
    │       ├── conditional/     # Assumptions (assumeTrue, assumingThat)
    │       ├── ddt/             # Parameterized and data-driven tests
    │       ├── nested/          # @Nested test classes
    │       ├── grouping/        # @Tag usage and custom tag annotations
    │       │   └── tags/        # Tags constants, @Smoke and @Regression
    │       ├── order/           # @TestMethodOrder and @Order
    │       ├── extensions/      # Tests exercising custom extensions
    │       ├── retry/           # @RetryingTest (Pioneer), @RepeatedIfExceptionsTest (Rerunner)
    │       ├── repeat/          # @RepeatedTest
    │       └── suites/          # @Suite, @BeforeSuite, @AfterSuite
    │           └── extension/  # Suite-like lifecycle via BeforeAllCallback and root store
    └── resources/
        └── numbers.csv         # CSVParameterizationTest input (number → expected factorial)
```

## Naming rules — follow exactly
| Type                | Suffix               | Example                      |
|---------------------|----------------------|------------------------------|
| Regular test class  | `Test`               | `AssertTest`, `LifecycleTest` |
| Suite member class  | `Case` or `Scenario` | `SuiteLifecycleFirstCase`    |
| Tag meta-annotation | none (short noun)    | `@Smoke`, `@Regression`      |

> ⚠️ **Never** name suite member classes with `*Test` or `*Tests`. Maven Surefire and Gradle auto-discover `*Test` classes and would run them twice (standalone + via suite).

## Code style
- Test method names use `snake_case`: `assert_equals_multiplication_test`
- Use `@Data` / `@Builder` (Lombok) in model classes to minimise boilerplate
- Prefer **AssertJ** for assertions in new test classes
- Use `SoftAssertions` or `BDDSoftAssertions` when all failures in a block should be collected before reporting
- Use `assertThrows` for exception testing (JUnit Jupiter style)
- Use `@Timeout` annotation for time-limited tests
- Do **not** use JUnit 4 annotations (`@org.junit.Test`, `@RunWith`, `@Rule`) in JUnit 5/6 classes

## Parallel execution
Parallel execution is enabled globally in `src/test/resources/junit-platform.properties`; `pom.xml` and `build.gradle` contain commented alternatives. For tests that must not run concurrently, annotate with:
```java
@Execution(ExecutionMode.SAME_THREAD)   // serialize the whole class
@ResourceLock("resource-name")          // mutual exclusion on a named resource
```

## Extension placement
- Extension **implementations** go in `src/main/…/topics/extensions/` (production source tree)
- Tests that *use* extensions go in `src/test/…/topics/extensions/`

## Suite lifecycle — two patterns
1. **`@Suite` + `@BeforeSuite` / `@AfterSuite`** (see `BeforeAfterSuite`): explicit suite class with `@SelectClasses`. Member classes must be `*Case`.
2. **`BeforeAllCallback` + root `ExtensionContext` store** (see `SuiteLikeLifecycleExtension`): no suite class needed; `getOrComputeIfAbsent` with `AutoCloseable` provides global setup/teardown. In JUnit 5, use `getOrComputeIfAbsent` — `computeIfAbsent` is the JUnit 6 replacement (where `getOrComputeIfAbsent` is deprecated).

## CI / CD
- Workflow: `.github/workflows/maven.yml` (GitHub Actions), triggered **manually only** (`workflow_dispatch`)
- Input: `groups` (optional) — tag filter passed to `-Dgroups`
- Two jobs: `regression` (always, all tests) and `by-tag` (only when `groups` input is provided)
- Both jobs run `./mvnw -B clean site` (equivalent Gradle: `./gradlew --no-daemon clean test`) and upload artifacts with `if: always()`, 14-day retention: `surefire-report[-{tag}]` (`target/site/`) and `junit-xml-results[-{tag}]` (`target/surefire-reports/`)
- Do **not** replace `site` with `test` in the workflow — the artifact upload requires the site report

## Build and test quick reference

### Maven
```bash
mvn clean test                          # all tests
mvn clean test -Dtest=ClassName         # single class
mvn clean test -Dgroups=smoke           # by tag
mvn clean test -P smoke                 # by Maven profile (smoke or regression)
mvn clean surefire-report:report        # HTML report (target/reports/surefire.html)
mvn clean site                          # full Maven site
```

### Gradle
```bash
gradle clean test                       # all tests (generates HTML report in build/reports/tests/test/index.html)
gradle clean test --tests "*ClassName"  # single class
gradle clean test -DincludeTags=smoke   # by tag
gradle clean smokeTest                  # dedicated Gradle task (smokeTest / regressionTest)
```

## What NOT to do
- Do not mix Maven and Gradle commands in a single workflow; consistently follow the selected build tool
- Do not add the JUnit Vintage engine unless working on the JUnit 4 branch
- Do not specify explicit versions for JUnit artifacts covered by `junit-bom` in `<dependencyManagement>` (Maven) or `platform()` (Gradle)
- Do not rename `*Case` suite members to `*Test`

