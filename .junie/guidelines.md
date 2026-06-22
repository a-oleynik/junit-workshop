# Junie Guidelines — JUnit Workshop

## Project purpose
A hands-on Java workshop demonstrating the full JUnit feature set across JUnit 4, JUnit 5, and JUnit 6. Used as companion material for a tech talk comparing JUnit 6 with TestNG 7. **This branch targets JUnit 4 (4.13.2)**; the `master` branch targets JUnit 6 (6.1.0).

## Stack
- **Java 17** — language level, no preview features
- **Maven 3.9+** with Maven Wrapper (`mvnw` / `mvnw.cmd`)
- **JUnit 4.13.2** — `junit:junit` artifact; all dependency versions are explicit (no BOM)
- **JUnitParams 1.1.1** — `@JUnitParamsRunner` + `@Parameters` for parameterized tests
- **junit4-dataprovider 2.12** — TNG-style `@DataProvider` for JUnit 4
- **junit-runners 1.3** — `ExecutionListenerRunner` custom runner
- **junit-hierarchicalcontextrunner 4.12.2** — `HierarchicalContextRunner` for nested tests
- **AssertJ 3.27.7** — fluent assertions, `SoftAssertions`, `BDDSoftAssertions`
- **Hamcrest 3.0** — matcher-based assertions
- **Lombok 1.18.46** — `@Data`, `@Builder` for model classes
- **opencsv 5.12.0** — CSV file parsing for data-driven tests

## Directory structure
```
src/main/java/com/oleynik/qa/workshop/junit/
  annotations/        custom annotation types and helpers
  dataproviders/      TNG-style DataProvider implementations
  listeners/          RunListener implementations (ExecutionListener)
  rules/              @Rule / @ClassRule implementations (not in test sources)
  runners/            custom @RunWith runners (not in test sources)
  model/              domain model (User, MyDoubleWrapper, MyServer)
  Factorial.java      example production code
  PrimeNumber.java
  Utils.java

src/test/java/com/oleynik/qa/workshop/junit/
  general/            assertions, fixtures, exceptions, timeouts, ignoring
  group/asserts/      ErrorCollector rule, AssertJ soft assertions
  conditional/        Assume.assumeTrue, Assume.assumeThat
  ddt/                parameterized & data-driven tests
  nested/             nested tests via HierarchicalContextRunner
  grouping/           @Category usage; categories/ has marker interfaces
  execution/order/    @FixMethodOrder
  rules/              tests exercising custom @Rule implementations
  repeat/             retry runners and rules
  suites/
    beforeclass/      @BeforeClass / @AfterClass on suite class
    lifecycle/        @ClassRule + ExternalResource for before/after suite
    listener/         JUnitCore + RunListener approach

src/test/resources/
  numbers.csv         input data for parameterized tests
```

## Naming rules — follow exactly
| Type               | Suffix                  | Example                         |
|--------------------|-------------------------|---------------------------------|
| Regular test class | `Test`                  | `AssertTest`, `FixturesTest`    |
| Suite member class | `Case` or `Scenario`    | `SuiteLifecycleFirstCase`       |
| Category marker    | interface name (plural) | `SmokeTests`, `RegressionTests` |

> ⚠️ **Never** name suite member classes `*Test` or `*Tests`. Maven Surefire auto-discovers them and would run them twice (standalone + via suite).

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
- Rule **implementations** go in `src/main/…/rules/`
- Runner **implementations** go in `src/main/…/runners/`
- Listener **implementations** go in `src/main/…/listeners/`
- Tests that *use* rules go in `src/test/…/rules/`

## Suite lifecycle — three patterns
JUnit 4 has no native `@BeforeSuite` / `@AfterSuite`. Three approaches are demonstrated:
1. **`@ClassRule` + `ExternalResource`** (`BeforeAfterSuite`): static `@ClassRule` field on the suite class; `before()` / `after()` wrap the entire suite.
2. **`@BeforeClass` / `@AfterClass`** (`BeforeClassSuite`): static methods on the `@RunWith(Suite.class)` class run before/after the whole suite.
3. **`JUnitCore` + `RunListener`** (`JUnitCoreRunnerTest`): programmatic execution with listener callbacks for global hooks.

## Maven quick reference
```bash
mvn clean test                          # all tests
mvn clean test -Dtest=ClassName         # single class
mvn clean test -Dgroups=com.oleynik.qa.workshop.junit.grouping.categories.SmokeTests  # by category
mvn clean surefire-report:report        # HTML report
mvn clean site                          # full Maven site
```

## What NOT to do
- Do not use Jupiter annotations (`@BeforeEach`, `@AfterEach`, `@Disabled`, `@Tag`, `@ExtendWith`) — not on the classpath
- Do not add a `junit-bom` — all dependency versions are managed explicitly in `pom.xml`
- Do not rename `*Case` suite members to `*Test`
- Do not use `@RunWith(Suite.class)` member classes named `*Test` — Surefire will run them twice
