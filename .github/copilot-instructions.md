# GitHub Copilot Instructions — JUnit Workshop

## Project overview
This is a **Java test-automation workshop** that demonstrates every major JUnit feature across JUnit 4, JUnit 5, and JUnit 6. It is a companion project to a tech talk comparing JUnit 6 with TestNG 7. **This branch targets JUnit 5 (5.14.4)**; the `master` branch targets JUnit 6 (6.1.3).

## Tech stack
| Concern               | Library / Tool                                                                    |
|-----------------------|-----------------------------------------------------------------------------------|
| Language              | Java 21 (LTS)                                                                     |
| Build                 | Maven 3.9+ / Gradle 9.6.0+ (Maven and Gradle Wrappers included)                   |
| Test framework        | JUnit 5.14.4 (junit-jupiter-engine, junit-jupiter-params, junit-platform-suite)   |
| Community extensions  | JUnit Pioneer 2.3.0 (`@RetryingTest`, `@CartesianTest`)                           |
| Data-provider bridge  | junit-jupiter-params-dataprovider 2.12                                            |
| Fluent assertions     | AssertJ 3.27.7 (`SoftAssertions`, BDD style)                                      |
| Matcher assertions    | Hamcrest 3.0                                                                      |
| Retry on failure      | rerunner-jupiter 2.1.6 (`@RepeatedIfExceptionsTest`), org.gradle.test-retry 1.6.5 |
| CSV parsing           | opencsv 5.12.0                                                                    |
| Boilerplate reduction | Lombok 1.18.48 (`@Builder`, `@Data`)                                              |

## Source layout

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
│       ├── model/               # Shared domain model (User, MyDoubleWrapper, MyServer)
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
    │       ├── general/         # Basic assertions, fixtures, exceptions, display names, timeouts
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

## Naming conventions
- **Test classes** end with `Test` (e.g. `AssertTest`, `FixturesTest`).
- **Suite-member classes** end with `Case` or `Scenario` — **never** `Test` or `Tests`. This prevents Maven Surefire from discovering them independently and running them twice (once standalone, once via the suite).
  - ✅ `SuiteLifecycleFirstCase`, `SuiteLifecycleSecondCase`
  - ❌ `SuiteLifecycleFirstTest`
- **Tag constants** live in `Tags.java`; meta-annotation types (`@Smoke`, `@Regression`) live alongside it in the `topics/grouping/tags/` package.
- **Extension classes** live in `src/main/…/topics/extensions/` — not in the test source tree.
- **DataProvider classes** live in `src/main/…/topics/dataproviders/`.

## Coding conventions
- Java 21 language level; no preview features.
- Lombok is available — use `@Data`, `@Builder`, `@AllArgsConstructor`, `@NoArgsConstructor` to reduce boilerplate in model classes.
- Prefer **AssertJ** for fluent assertions in new tests; use `SoftAssertions` or `BDDSoftAssertions` when multiple assertions should all be evaluated before reporting failures.
- Use **Hamcrest** (`assertThat` + matchers) only in `HamcrestTest` or where matcher-based style is explicitly being demonstrated.
- Use `assertThrows` (JUnit Jupiter) for exception testing — never `@Test(expected = ...)` (that is JUnit 4 style).
- Use `@Timeout` annotation for time-bound tests — not `assertTimeout` lambda style, unless demonstrating that specific API.
- Parallel execution is enabled globally via `src/test/resources/junit-platform.properties` and build configuration. Mark tests that must not run concurrently with `@Execution(ExecutionMode.SAME_THREAD)` or `@ResourceLock`.
- Keep test method names in `snake_case` (e.g. `assert_equals_multiplication_test`) — this is the established style throughout the project.
- Do **not** use JUnit 4 annotations (`@org.junit.Test`, `@RunWith`, `@Rule`) in JUnit 5/6 test classes.

## Suite / lifecycle patterns
- **`@Suite` approach:** use `@Suite` + `@SelectClasses` + `@BeforeSuite` / `@AfterSuite` on a dedicated suite class (see `BeforeAfterSuite`). Member classes must be named `*Case`.
- **Extension approach:** implement `BeforeAllCallback`, access the root `ExtensionContext`, use `getOrComputeIfAbsent` with an `AutoCloseable` for global setup/teardown (see `SuiteLikeLifecycleExtension`). In JUnit 5, `getOrComputeIfAbsent` is the correct API — `computeIfAbsent` is the JUnit 6 replacement (where `getOrComputeIfAbsent` is deprecated).

## CI / CD
- Workflow file: `.github/workflows/maven.yml`
- Triggered **manually only** via `workflow_dispatch` (Actions → Run workflow on GitHub)
- Input: `groups` (optional) — tag filter for the by-tag job (e.g. `smoke`, `regression`)
- Two jobs:
  - `regression` — always runs all tests: `./mvnw -B clean site`
  - `by-tag` — runs only when `groups` is provided: `./mvnw -B clean site -Dgroups={groups}`
- Each job uploads `target/site/` as `surefire-report[-{tag}]` and `target/surefire-reports/` as `junit-xml-results[-{tag}]`, both with 14-day retention and `if: always()`
- Do **not** change the build command from `site` to `test` — the artifact upload depends on the site report being generated

## Maven commands (quick reference)
```bash
mvn clean test                          # run all tests
mvn clean test -Dtest=AssertTest        # run one class
mvn clean test -Dgroups=smoke           # run by tag
mvn clean test -P smoke                 # run via Maven profile (smoke or regression)
mvn clean surefire-report:report        # generate HTML report
mvn clean site                          # full Maven site
```

## Important constraints
- When generating commands, test runs, or build actions, consistently follow the user's chosen build tool (Maven or Gradle) rather than mixing both.
- Do **not** rename `*Case` classes to `*Test` — it will cause double-execution.
- Do **not** add `<groupId>org.junit.vintage</groupId>` (JUnit 4 vintage engine) unless specifically creating a JUnit 4 example branch.
- The `junit-bom` BOM is declared in `<dependencyManagement>` — do not specify explicit versions for JUnit artifacts that are covered by the BOM.

