# Junie Guidelines — JUnit Workshop

## Project purpose
A hands-on Java workshop demonstrating the full JUnit feature set across JUnit 4, JUnit 5, and JUnit 6. Used as companion material for a tech talk comparing JUnit 6 with TestNG 7. **This branch targets JUnit 5 (5.14.4)**; the `master` branch targets JUnit 6 (6.1.0).

## Stack
- **Java 21** — language level, no preview features
- **Maven 3.9+** with Maven Wrapper (`mvnw` / `mvnw.cmd`)
- **JUnit 5.14.4** — Jupiter API, params, platform-suite (versions managed via `junit-bom` BOM)
- **JUnit Pioneer 2.3.0** — `@RetryingTest`, `@CartesianTest`
- **junit-jupiter-params-dataprovider 2.12** — TNG-style `@DataProvider` for Jupiter
- **AssertJ 3.27.7** — fluent assertions, `SoftAssertions`, `BDDSoftAssertions`
- **Hamcrest 3.0** — matcher-based assertions
- **Lombok 1.18.46** — `@Data`, `@Builder` for model classes
- **rerunner-jupiter 2.1.6** — `@RepeatedIfExceptionsTest` retry
- **opencsv 5.12.0** — CSV file parsing for data-driven tests

## Directory structure
```
src/main/java/com/oleynik/qa/workshop/junit/
  annotations/        custom annotation types and helpers
  dataproviders/      ArgumentsProvider / DataProvider implementations
  extensions/         JUnit extension classes (not in test sources)
  model/              domain model (User, MyDoubleWrapper, MyServer)
  Factorial.java      example production code
  PrimeNumber.java
  Utils.java

src/test/java/com/oleynik/qa/workshop/junit/
  general/            assertions, fixtures, exceptions, display names, timeouts
  group/asserts/      assertAll, AssertJ soft assertions
  conditional/        assumptions
  ddt/                parameterized & data-driven tests
  nested/             @Nested test classes
  grouping/           @Tag usage; tags/ sub-package has Tags.java + @Smoke / @Regression
  execution/order/    @TestMethodOrder, @Order
  extensions/         tests exercising custom extensions
  retry/              @RetryingTest, @RepeatedIfExceptionsTest
  repeat/             @RepeatedTest
  suites/             @Suite lifecycle; extension/ for BeforeAllCallback approach

src/test/resources/
  numbers.csv         input data for CSVParameterizationTest
```

## Naming rules — follow exactly
| Type                | Suffix               | Example                      |
|---------------------|----------------------|------------------------------|
| Regular test class  | `Test`               | `AssertTest`, `FixturesTest` |
| Suite member class  | `Case` or `Scenario` | `SuiteLifecycleFirstCase`    |
| Tag meta-annotation | none (short noun)    | `@Smoke`, `@Regression`      |

> ⚠️ **Never** name suite member classes with `*Test` or `*Tests`. Maven Surefire auto-discovers `*Test` classes and would run them twice (standalone + via suite).

## Code style
- Test method names use `snake_case`: `assert_equals_multiplication_test`
- Use `@Data` / `@Builder` (Lombok) in model classes to minimise boilerplate
- Prefer **AssertJ** for assertions in new test classes
- Use `SoftAssertions` or `BDDSoftAssertions` when all failures in a block should be collected before reporting
- Use `assertThrows` for exception testing (JUnit Jupiter style)
- Use `@Timeout` annotation for time-limited tests
- Do **not** use JUnit 4 annotations (`@org.junit.Test`, `@RunWith`, `@Rule`) in JUnit 5/6 classes

## Parallel execution
Parallel execution is enabled globally in `pom.xml`. For tests that must not run concurrently, annotate with:
```java
@Execution(ExecutionMode.SAME_THREAD)   // serialize the whole class
@ResourceLock("resource-name")          // mutual exclusion on a named resource
```

## Extension placement
- Extension **implementations** go in `src/main/…/extensions/` (production source tree)
- Tests that *use* extensions go in `src/test/…/extensions/`

## Suite lifecycle — two patterns
1. **`@Suite` + `@BeforeSuite` / `@AfterSuite`** (see `BeforeAfterSuite`): explicit suite class with `@SelectClasses`. Member classes must be `*Case`.
2. **`BeforeAllCallback` + root `ExtensionContext` store** (see `SuiteLikeLifecycleExtension`): no suite class needed; `getOrComputeIfAbsent` with `AutoCloseable` provides global setup/teardown. In JUnit 5, use `getOrComputeIfAbsent` — `computeIfAbsent` is the JUnit 6 replacement (where `getOrComputeIfAbsent` is deprecated).

## CI / CD
- Workflow: `.github/workflows/maven.yml` (GitHub Actions)
- Triggers on push and PR to `master` and `junit-*` branches
- Build command: `./mvnw -B clean site` — runs all tests and generates the Surefire HTML report in `target/site/`
- Artifacts uploaded with `if: always()`, 14-day retention: `surefire-report` (`target/site/`) and `junit-xml-results` (`target/surefire-reports/`)
- Do **not** replace `site` with `test` in the workflow — the artifact upload requires the site report

## Maven quick reference
```bash
mvn clean test                          # all tests
mvn clean test -Dtest=ClassName         # single class
mvn clean test -Dgroups=Smoke           # by tag
mvn clean test -P SmokeTests            # by Maven profile
mvn clean surefire-report:report        # HTML report
mvn clean site                          # full Maven site
```

## What NOT to do
- Do not add the JUnit Vintage engine unless working on the JUnit 4 branch
- Do not specify explicit versions for JUnit artifacts covered by `junit-bom` in `<dependencyManagement>`
- Do not rename `*Case` suite members to `*Test`

