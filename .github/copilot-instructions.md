# GitHub Copilot Instructions — JUnit Workshop

## Project overview
This is a **Java test-automation workshop** that demonstrates every major JUnit feature across JUnit 4, JUnit 5, and JUnit 6. It is a companion project to a tech talk comparing JUnit 6 with TestNG 7. **This branch targets JUnit 4 (4.13.2)**; the `master` branch targets JUnit 6 (6.1.0).

## Tech stack
| Concern               | Library / Tool                                                                 |
|-----------------------|--------------------------------------------------------------------------------|
| Language              | Java 17 (LTS)                                                                  |
| Build                 | Maven 3.9+ (Maven Wrapper included)                                            |
| Test framework        | JUnit 4.13.2 (`junit:junit`)                                                   |
| Parameterized tests   | JUnitParams 1.1.1 (`@JUnitParamsRunner`)                                       |
| Data-provider bridge  | junit4-dataprovider 2.12 (TNG-style `@DataProvider`)                           |
| Fluent assertions     | AssertJ 3.27.7 (`SoftAssertions`, BDD style)                                   |
| Matcher assertions    | Hamcrest 3.0                                                                   |
| Custom runners        | junit-runners 1.3 (`ExecutionListenerRunner`)                                  |
| Nested test support   | junit-hierarchicalcontextrunner 4.12.2 (`HierarchicalContextRunner`)           |
| CSV parsing           | opencsv 5.12.0                                                                 |
| Boilerplate reduction | Lombok 1.18.46 (`@Builder`, `@Data`)                                           |

## Source layout
```
src/
├── main/java/com/oleynik/qa/workshop/junit/
│   ├── annotations/       # Custom annotation types and helpers
│   ├── dataproviders/     # TNG-style DataProvider implementations
│   ├── listeners/         # RunListener implementations (ExecutionListener)
│   ├── rules/             # @Rule / @ClassRule implementations (DBResourceRule, RetryTestRule, etc.)
│   ├── runners/           # Custom @RunWith runners (ExecutionListenerRunner, RetryRunner)
│   ├── model/             # Domain model classes (User, MyDoubleWrapper, MyServer)
│   ├── Factorial.java     # Example production class
│   ├── PrimeNumber.java
│   └── Utils.java
└── test/java/com/oleynik/qa/workshop/junit/
    ├── general/           # Basic assertions, fixtures, exceptions, display names, timeouts
    ├── group/asserts/     # assertAll (ErrorCollector rule), AssertJ soft assertions
    ├── conditional/       # Assumptions (Assume.assumeTrue, Assume.assumeThat)
    ├── ddt/               # Parameterized & data-driven tests
    ├── nested/            # Nested tests via HierarchicalContextRunner
    ├── grouping/          # @Category usage
    │   └── categories/    # Category marker interfaces (SmokeTests, RegressionTests)
    ├── execution/order/   # @FixMethodOrder
    ├── rules/             # Tests that use custom @Rule implementations
    ├── repeat/            # Retry runners and rules
    └── suites/            # Suite lifecycle
        ├── beforeclass/   # Suite with @BeforeClass / @AfterClass pattern
        ├── lifecycle/     # Suite lifecycle via @ClassRule + ExternalResource
        └── listener/      # Suite with RunListener via JUnitCore
```

## Naming conventions
- **Test classes** end with `Test` (e.g. `AssertTest`, `FixturesTest`).
- **Suite-member classes** end with `Case` or `Scenario` — **never** `Test` or `Tests`. This prevents Maven Surefire from discovering them independently and running them twice.
  - ✅ `SuiteLifecycleFirstCase`, `SuiteLifecycleSecondCase`
  - ❌ `SuiteLifecycleFirstTest`
- **Category marker interfaces** live in the `categories/` package (`SmokeTests`, `RegressionTests`).
- **Rule classes** live in `src/main/…/rules/` — not in the test source tree.
- **Runner classes** live in `src/main/…/runners/`.
- **Listener classes** live in `src/main/…/listeners/`.

## Coding conventions
- Java 17 language level; no preview features.
- Lombok is available — use `@Data`, `@Builder`, `@AllArgsConstructor`, `@NoArgsConstructor` to reduce boilerplate in model classes.
- Use `@org.junit.Test` — this is JUnit 4; `@org.junit.jupiter.api.Test` does not exist here.
- Use `@Before` / `@After` / `@BeforeClass` / `@AfterClass` for lifecycle methods.
- Use `@Ignore` to skip tests (not `@Disabled` — that is JUnit 5/6).
- Use `@Category(SmokeTests.class)` for grouping (not `@Tag` — that is JUnit 5/6).
- Use `@FixMethodOrder(MethodSorters.NAME_ASCENDING)` for execution order (not `@TestMethodOrder`).
- For exception testing, prefer `assertThrows` (added in JUnit 4.13) — `@Test(expected = ...)` is also valid here and demonstrated in `ExceptionTest`.
- For timeouts, use `@Test(timeout = ms)` — there is no `@Timeout` annotation in JUnit 4.
- Prefer **AssertJ** for fluent assertions in new tests; use `SoftAssertions` or `BDDSoftAssertions` when multiple assertions should all be evaluated before reporting failures.
- Use **Hamcrest** (`assertThat` + matchers) only in `HamcrestTest` or where matcher-based style is explicitly being demonstrated.
- Keep test method names in `snake_case` (e.g. `assert_equals_multiplication_test`).

## Rule / runner patterns
- **`@Rule`** — method-level lifecycle; apply to a `public` non-static field annotated `@Rule`.
- **`@ClassRule`** — class-level lifecycle (like `@BeforeClass`/`@AfterClass`); apply to a `public static` field.
- **Custom runners** — use `@RunWith(MyRunner.class)` to swap the test runner. Cannot combine multiple runners without a wrapper.
- Rule implementations extend `ExternalResource`, `TestWatcher`, `TestRule`, etc. Place them in `src/main/…/rules/`.

## Suite lifecycle patterns
JUnit 4 has **no native `@BeforeSuite` / `@AfterSuite`** annotations. Three approaches are demonstrated:

1. **`@ClassRule` + `ExternalResource`** (see `BeforeAfterSuite`): apply a static `@ClassRule` of type `ExternalResource` on the suite class — `before()` runs once before the suite, `after()` once after.
2. **`@BeforeClass` / `@AfterClass` on suite class** (see `BeforeClassSuite`): static methods on the `@RunWith(Suite.class)` class are called before/after the entire suite.
3. **`JUnitCore` + `RunListener`** (see `JUnitCoreRunnerTest`): programmatic suite execution with a custom `RunListener` for global setup/teardown hooks.

## CI / CD
- Workflow file: `.github/workflows/maven.yml`
- Runs on every push and PR to `master` and `junit-*` branches
- Build command: `./mvnw -B clean site` — runs all tests and generates the Surefire HTML report
- The `target/site/` directory is uploaded as artifact `surefire-report` and `target/surefire-reports/` as artifact `junit-xml-results`, both with 14-day retention and `if: always()` so they are saved even on test failures
- Do **not** change the build command from `site` to `test` — the artifact upload depends on the site report being generated

## Maven commands (quick reference)
```bash
mvn clean test                          # run all tests
mvn clean test -Dtest=AssertTest        # run one class
mvn clean test -Dgroups=SmokeTests      # run by category
mvn clean surefire-report:report        # generate HTML report
mvn clean site                          # full Maven site
```

## Important constraints
- Do **not** rename `*Case` classes to `*Test` — it will cause double-execution via Surefire.
- Do **not** use JUnit 5/6 annotations (`@ExtendWith`, `@BeforeEach`, `@AfterEach`, `@Disabled`, `@Tag`) — they are not on the classpath in this branch.
- Dependency versions are declared explicitly in `pom.xml` — there is no `junit-bom` in this branch.
