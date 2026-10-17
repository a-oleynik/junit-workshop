# Junie Guidelines — JUnit Workshop

## Project purpose
A hands-on Java workshop demonstrating the full JUnit feature set across JUnit 4, JUnit 5, and JUnit 6. Used as companion material for a tech talk comparing JUnit 6 with TestNG 7. **This branch targets JUnit 4 (4.13.2)**; the `master` branch targets JUnit 6 (6.1.3).

## Stack
- **Java 17** — language level, no preview features
- **Maven 3.9+** and **Gradle 9.6.0+** with bundled wrappers (`mvnw` / `mvnw.cmd`, `gradlew` / `gradlew.bat`)
- **JUnit 4.13.2** — `junit:junit` artifact; all dependency versions are explicit (no BOM)
- **JUnitParams 1.1.1** — `@JUnitParamsRunner` + `@Parameters` for parameterized tests
- **junit4-dataprovider 2.12** — TNG-style `@DataProvider` for JUnit 4
- **junit-runners 1.3** — `ExecutionListenerRunner` custom runner
- **junit-hierarchicalcontextrunner 4.12.2** — `HierarchicalContextRunner` for nested tests
- **org.gradle.test-retry 1.6.5** — Gradle plugin for test retries
- **AssertJ 3.27.7** — fluent assertions, `SoftAssertions`, `BDDSoftAssertions`
- **Hamcrest 3.0** — matcher-based assertions
- **Lombok 1.18.48** — `@Data`, `@Builder` for model classes
- **opencsv 5.12.0** — CSV file parsing for data-driven tests

## Directory structure

`topics` contains examples of individual JUnit features; `scenarios` contains complete examples that combine several features and can be referenced by multiple articles. The Calculator scenario is in `scenarios/calculator` in both main and test sources.

The manual starting point is `src/main/java/…/scenarios/calculator/beforejunit/Main.java`; it uses the shared `Calculator` and leads into `CalculatorTest`.

```text
build.gradle             # Gradle build configuration
settings.gradle          # Gradle project name
gradlew / gradlew.bat    # Gradle Wrapper scripts
pom.xml                  # Maven build configuration
mvnw / mvnw.cmd          # Maven Wrapper scripts
src/
├── main/java/com/oleynik/qa/workshop/junit/
│   ├── scenarios/
│   │   └── calculator/
│   │       ├── Calculator.java  # Addition and integer division
│   │       └── beforejunit/
│   │           └── Main.java    # Manual comparison before introducing JUnit
│   └── topics/
│       ├── annotations/         # DataSource, Retry, RetryConfiguration
│       ├── dataproviders/       # JUnitDataProvider
│       ├── listeners/           # ExecutionListener (RunListener)
│       ├── model/               # User, MyDoubleWrapper, MyServer, ShoppingCart
│       ├── rules/               # DBResourceRule, MonitorRule, retry rules, AssertAll
│       ├── runners/             # RetryRunner, ExecutionListenerRunner
│       ├── Factorial.java       # Production code for parameterized tests
│       └── Utils.java           # Helper used by timeout examples
└── test/
    ├── java/com/oleynik/qa/workshop/junit/
    │   ├── scenarios/
    │   │   └── calculator/
    │   │       ├── CalculatorTest.java
    │   │       └── CalculatorParameterizedTest.java
    │   └── topics/
    │       ├── general/         # Basic assertions, lifecycle, exceptions, timeouts, @Ignore
    │       ├── fixtures/        # InlineFixtureTest and ImplicitFixtureTest: fresh cart per test
    │       ├── asserts/         # ErrorCollector, AssertAll, AssertJ soft assertions
    │       ├── conditional/     # Assumptions via Assume
    │       ├── ddt/             # Parameterized, JUnitParams, DataProvider, Theories
    │       ├── nested/          # HierarchicalContextRunner
    │       ├── grouping/        # @Category usage
    │       │   └── categories/  # SmokeTests, RegressionTests marker interfaces
    │       ├── order/           # @FixMethodOrder, @OrderWith
    │       ├── rules/           # ExternalResource and TestWatcher examples
    │       ├── repeat/          # Retry runners, retry rules and repeat demos
    │       └── suites/
    │           ├── lifecycle/   # @ClassRule + ExternalResource
    │           ├── beforeclass/ # @BeforeClass / @AfterClass on the suite
    │           └── listener/    # JUnitCore + RunListener
    └── resources/
        └── numbers.csv         # Input data for DataProvider tests
```

## Naming rules — follow exactly
| Type               | Suffix                  | Example                         |
|--------------------|-------------------------|---------------------------------|
| Regular test class | `Test`                  | `AssertTest`, `LifecycleTest`   |
| Suite member class | `Case` or `Scenario`    | `SuiteLifecycleFirstCase`       |
| Category marker    | interface name (plural) | `SmokeTests`, `RegressionTests` |

> ⚠️ **Never** name suite member classes `*Test` or `*Tests`. Maven Surefire and Gradle auto-discover them and would run them twice (standalone + via suite).

## Code style
- Test method names use `snake_case`: `assert_equals_multiplication_test`
- Use `@Data` / `@Builder` (Lombok) in model classes to minimise boilerplate
- Prefer **AssertJ** for assertions in new test classes
- Use `SoftAssertions` or `BDDSoftAssertions` when all failures in a block should be collected before reporting
- Use `assertThrows` for exception testing (preferred in JUnit 4.13+); `@Test(expected = ...)` is also valid and demonstrated
- Use `@Test(timeout = ms)` for time-limited tests — there is no `@Timeout` annotation in JUnit 4
- Do **not** use JUnit 5/6 annotations — `@BeforeEach`, `@AfterEach`, `@Disabled`, `@Tag`, `@ExtendWith` are not on the classpath

## JUnit 4 annotation cheat sheet
| Purpose                     | JUnit 4                   | JUnit 5/6 equivalent                 |
|-----------------------------|---------------------------|--------------------------------------|
| Mark a test                 | `@Test` (`org.junit`)     | `@Test` (`org.junit.jupiter.api`)    |
| Before each test            | `@Before`                 | `@BeforeEach`                        |
| After each test             | `@After`                  | `@AfterEach`                         |
| Before all tests in class   | `@BeforeClass` (static)   | `@BeforeAll` (static)                |
| After all tests in class    | `@AfterClass` (static)    | `@AfterAll` (static)                 |
| Skip a test                 | `@Ignore`                 | `@Disabled`                          |
| Categorise / tag            | `@Category(Marker.class)` | `@Tag("name")`                       |
| Method-level lifecycle hook | `@Rule`                   | `@ExtendWith` / `@RegisterExtension` |
| Class-level lifecycle hook  | `@ClassRule` (static)     | `@ExtendWith` / `@RegisterExtension` |
| Execution order             | `@FixMethodOrder`         | `@TestMethodOrder`                   |
| Custom runner               | `@RunWith`                | `@ExtendWith`                        |

## Rule / runner placement
- Rule **implementations** go in `src/main/…/topics/rules/`
- Runner **implementations** go in `src/main/…/topics/runners/`
- Listener **implementations** go in `src/main/…/topics/listeners/`
- Tests that *use* rules go in `src/test/…/topics/rules/`

## Suite lifecycle — three patterns
JUnit 4 has no native `@BeforeSuite` / `@AfterSuite`. Three approaches are demonstrated:
1. **`@ClassRule` + `ExternalResource`** (`BeforeAfterSuite`): static `@ClassRule` field on the suite class; `before()` / `after()` wrap the entire suite.
2. **`@BeforeClass` / `@AfterClass`** (`BeforeClassSuite`): static methods on the `@RunWith(Suite.class)` class run before/after the whole suite.
3. **`JUnitCore` + `RunListener`** (`JUnitCoreRunnerTest`): programmatic execution with listener callbacks for global hooks.

## CI / CD
- Workflow: `.github/workflows/maven.yml` (GitHub Actions), triggered **manually only** (`workflow_dispatch`)
- Input: `groups` (optional) — fully-qualified category class name passed to `-Dgroups` (e.g. `com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests`)
- Two jobs: `regression` (always, all tests) and `by-tag` (only when `groups` input is provided)
- Both jobs run `./mvnw -B clean site` and upload artifacts with `if: always()`, 14-day retention: `surefire-report[-{tag}]` (`target/site/`) and `junit-xml-results[-{tag}]` (`target/surefire-reports/`)
- Do **not** replace `site` with `test` in the workflow — the artifact upload requires the site report

## Maven quick reference
```bash
mvn clean test                          # all tests
mvn clean test -Dtest=ClassName         # single class
mvn clean test -Dgroups=com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests  # by category
mvn clean test -P smoke                 # by Maven profile (smoke or regression)
mvn clean surefire-report:report        # HTML report
mvn clean site                          # full Maven site
```

## Gradle quick reference
```bash
gradle clean test                       # all tests
gradle clean test --tests "*ClassName"  # single class
gradle clean test -DincludeCategories=com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests # by category
gradle clean smokeTest                  # by dedicated Gradle task (smokeTest or regressionTest)
gradle clean test                       # HTML report (build/reports/tests/test/index.html)
```

## What NOT to do
- Do not mix Maven and Gradle commands in a single workflow; consistently follow the selected build tool
- Do not use Jupiter annotations (`@BeforeEach`, `@AfterEach`, `@Disabled`, `@Tag`, `@ExtendWith`) — not on the classpath
- Do not add a `junit-bom` — all dependency versions are managed explicitly in `pom.xml` and `build.gradle`
- Do not rename `*Case` suite members to `*Test`
- Do not use `@RunWith(Suite.class)` member classes named `*Test` — Surefire and Gradle will run them twice
