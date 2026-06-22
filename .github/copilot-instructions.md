# GitHub Copilot Instructions — JUnit Workshop

## Project overview
This is a **Java test-automation workshop** that demonstrates every major JUnit feature across JUnit 4, JUnit 5, and JUnit 6. It is a companion project to a tech talk comparing JUnit 6 with TestNG 7. The default branch targets **JUnit 6 (6.1.0)**.

## Tech stack
| Concern               | Library / Tool                                                                 |
|-----------------------|--------------------------------------------------------------------------------|
| Language              | Java 21 (LTS)                                                                  |
| Build                 | Maven 3.9+ (Maven Wrapper included)                                            |
| Test framework        | JUnit 6.1.0 (junit-jupiter-engine, junit-jupiter-params, junit-platform-suite) |
| Community extensions  | JUnit Pioneer 2.3.0 (`@RetryingTest`, `@CartesianTest`)                        |
| Data-provider bridge  | junit-jupiter-params-dataprovider 2.12                                         |
| Fluent assertions     | AssertJ 3.27.7 (`SoftAssertions`, BDD style)                                   |
| Matcher assertions    | Hamcrest 3.0                                                                   |
| Retry on failure      | rerunner-jupiter 2.1.6 (`@RepeatedIfExceptionsTest`)                           |
| CSV parsing           | opencsv 5.12.0                                                                 |
| Boilerplate reduction | Lombok 1.18.46 (`@Builder`, `@Data`)                                           |

## Source layout
```
src/
├── main/java/com/oleynik/qa/workshop/junit/
│   ├── annotations/       # Custom annotation types and helpers
│   ├── dataproviders/     # ArgumentsProvider / DataProvider implementations
│   ├── extensions/        # JUnit extension implementations (DBResourceExtension, TestWatcherExtension, SuiteLikeLifecycleExtension)
│   ├── model/             # Domain model classes (User, MyDoubleWrapper, MyServer)
│   ├── Factorial.java     # Example production class
│   ├── PrimeNumber.java
│   └── Utils.java
└── test/java/com/oleynik/qa/workshop/junit/
    ├── general/           # Basic assertions, fixtures, exceptions, display names, timeouts
    ├── group/asserts/     # assertAll, AssertJ soft assertions
    ├── conditional/       # Assumptions (assumeTrue, assumeThat)
    ├── ddt/               # Parameterized & data-driven tests
    ├── nested/            # @Nested test classes
    ├── grouping/          # @Tag, @Smoke, @Regression meta-annotations
    │   └── tags/          # Tags constants + meta-annotation types
    ├── execution/order/   # @TestMethodOrder, @Order
    ├── extensions/        # Tests that use custom extensions
    ├── retry/             # @RetryingTest (Pioneer), @RepeatedIfExceptionsTest (Rerunner)
    ├── repeat/            # @RepeatedTest
    └── suites/            # @Suite, @BeforeSuite, @AfterSuite lifecycle
        └── extension/     # Suite-like lifecycle via BeforeAllCallback + root store
```

## Naming conventions
- **Test classes** end with `Test` (e.g. `AssertTest`, `FixturesTest`).
- **Suite-member classes** end with `Case` or `Scenario` — **never** `Test` or `Tests`. This prevents Maven Surefire from discovering them independently and running them twice (once standalone, once via the suite).
  - ✅ `SuiteLifecycleFirstCase`, `SuiteLifecycleSecondCase`
  - ❌ `SuiteLifecycleFirstTest`
- **Tag constants** live in `Tags.java`; meta-annotation types (`@Smoke`, `@Regression`) live alongside it in the `tags/` package.
- **Extension classes** live in `src/main/…/extensions/` — not in the test source tree.
- **DataProvider classes** live in `src/main/…/dataproviders/`.

## Coding conventions
- Java 21 language level; no preview features.
- Lombok is available — use `@Data`, `@Builder`, `@AllArgsConstructor`, `@NoArgsConstructor` to reduce boilerplate in model classes.
- Prefer **AssertJ** for fluent assertions in new tests; use `SoftAssertions` or `BDDSoftAssertions` when multiple assertions should all be evaluated before reporting failures.
- Use **Hamcrest** (`assertThat` + matchers) only in `HamcrestTest` or where matcher-based style is explicitly being demonstrated.
- Use `assertThrows` (JUnit Jupiter) for exception testing — never `@Test(expected = ...)` (that is JUnit 4 style).
- Use `@Timeout` annotation for time-bound tests — not `assertTimeout` lambda style, unless demonstrating that specific API.
- Parallel execution is enabled globally via Surefire config in `pom.xml`. Mark tests that must not run concurrently with `@Execution(ExecutionMode.SAME_THREAD)` or `@ResourceLock`.
- Keep test method names in `snake_case` (e.g. `assert_equals_multiplication_test`) — this is the established style throughout the project.
- Do **not** use JUnit 4 annotations (`@org.junit.Test`, `@RunWith`, `@Rule`) in JUnit 5/6 test classes.

## Suite / lifecycle patterns
- **`@Suite` approach:** use `@Suite` + `@SelectClasses` + `@BeforeSuite` / `@AfterSuite` on a dedicated suite class (see `BeforeAfterSuite`). Member classes must be named `*Case`.
- **Extension approach:** implement `BeforeAllCallback`, access the root `ExtensionContext`, use `computeIfAbsent` with an `AutoCloseable` for global setup/teardown (see `SuiteLikeLifecycleExtension`). This is the JUnit 6 idiomatic way — `getOrComputeIfAbsent` is deprecated in JUnit 6; use `computeIfAbsent` instead.

## CI / CD
- Workflow file: `.github/workflows/maven.yml`
- Triggered **manually only** via `workflow_dispatch` (Actions → Run workflow on GitHub)
- Input: `groups` (optional) — tag filter for the by-tag job (e.g. `Smoke`, `Regression`)
- Two jobs:
  - `regression` — always runs all tests: `./mvnw -B clean site`
  - `by-tag` — runs only when `groups` is provided: `./mvnw -B clean site -Dgroups={groups}`
- Each job uploads `target/site/` as `surefire-report[-{tag}]` and `target/surefire-reports/` as `junit-xml-results[-{tag}]`, both with 14-day retention and `if: always()`
- Do **not** change the build command from `site` to `test` — the artifact upload depends on the site report being generated

## Maven commands (quick reference)
```bash
mvn clean test                          # run all tests
mvn clean test -Dtest=AssertTest        # run one class
mvn clean test -Dgroups=Smoke           # run by tag
mvn clean test -P SmokeTests            # run via Maven profile
mvn clean surefire-report:report        # generate HTML report
mvn clean site                          # full Maven site
```

## Important constraints
- Do **not** rename `*Case` classes to `*Test` — it will cause double-execution.
- Do **not** add `<groupId>org.junit.vintage</groupId>` (JUnit 4 vintage engine) unless specifically creating a JUnit 4 example branch.
- The `junit-bom` BOM is declared in `<dependencyManagement>` — do not specify explicit versions for JUnit artifacts that are covered by the BOM.

