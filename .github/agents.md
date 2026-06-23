# GitHub Copilot Agent Instructions — JUnit Workshop

## Agent behaviour
- Always read `copilot-instructions.md` (same `.github/` folder) for full project conventions before taking any action.
- This is a **workshop / demo project** — every test class exists to *demonstrate* a JUnit 4 feature. When adding new tests, make them educational: prefer clear, minimal, self-contained examples over production-style abstractions.
- Never silently skip a feature or leave a `TODO` — implement it fully or ask.

## Environment
- **OS:** any (Maven Wrapper included — use `./mvnw` on Unix, `mvnw.cmd` on Windows)
- **Java:** 17 LTS (`JAVA_HOME` must point to JDK 17)
- **Build:** Maven 3.9+ or the bundled wrapper
- **IDE:** IntelliJ IDEA with Lombok plugin enabled
- **CI:** GitHub Actions — `.github/workflows/maven.yml`, triggered manually (`workflow_dispatch`). Two jobs: `regression` (all tests, `./mvnw -B clean site`) and `by-tag` (runs only when `groups` input is provided — must be a fully-qualified category class name, e.g. `com.oleynik.qa.workshop.junit.grouping.categories.SmokeTests`). Both upload `surefire-report` and `junit-xml-results` artifacts. Do **not** change `site` to `test`.

## How to build and test
```bash
# Run all tests
mvn clean test

# Run a single test class
mvn clean test -Dtest=<ClassName>

# Run a single test method
mvn clean test -Dtest=<ClassName>#<methodName>

# Run by category
mvn clean test -Dgroups=com.oleynik.qa.workshop.junit.grouping.categories.SmokeTests

# Generate Surefire HTML report
mvn clean surefire-report:report

# Full Maven site
mvn clean site
```

## Common agent tasks

### Add a new test class
1. Decide which feature to demonstrate and pick the correct package under `src/test/…`.
2. Name the class `<Feature>Test` (e.g. `TimeoutTest`, `FixturesTest`).
3. Use `@org.junit.Test` — never `@org.junit.jupiter.api.Test`.
4. Use `@Before` / `@After` / `@BeforeClass` / `@AfterClass` for lifecycle methods.
5. Name test methods in `snake_case`.
6. Use **AssertJ** for assertions unless the class is specifically demonstrating Hamcrest or JUnit 4 `Assert`.
7. Run `mvn clean test -Dtest=<NewClass>` to verify.

### Add a suite-member class
1. Name it `*Case` or `*Scenario` — **never** `*Test` (Surefire would run it twice).
2. Place it under the appropriate `src/test/…/suites/` sub-package.
3. Reference it in `@Suite.SuiteClasses` on the relevant suite class.

### Add a custom rule
1. Create the implementation in `src/main/java/…/rules/` (main sources, not test sources).
2. Extend `ExternalResource`, `TestWatcher`, or implement `TestRule` / `MethodRule` as appropriate.
3. Create a test class in `src/test/java/…/rules/` that exercises it with a `@Rule` or `@ClassRule` field.

### Add a custom runner
1. Create the runner in `src/main/java/…/runners/`.
2. Apply it in the test class with `@RunWith(MyRunner.class)`.
3. Note: only one `@RunWith` can be active per class — use delegation or composition if multiple behaviours are needed.

### Add a data provider
1. Create the provider class in `src/main/java/…/dataproviders/` using the TNG-style `@DataProvider` annotation from `junit4-dataprovider`.
2. Reference it from the test class in `src/test/…/ddt/` with `@UseDataProvider`.
3. For `JUnitParams`-style tests, use `@RunWith(JUnitParamsRunner.class)` and `@Parameters`.

### Add a category / grouping
1. Create a new marker interface in `src/test/…/grouping/categories/`.
2. Apply `@Category(MyCategory.class)` to test methods or the whole class.

## Key constraints the agent must respect
| Rule                                                                      | Why                                                         |
|---------------------------------------------------------------------------|-------------------------------------------------------------|
| Suite members named `*Case`, never `*Test`                                | Surefire discovers `*Test` independently → double execution |
| Rules in `src/main/…/rules/`, runners in `src/main/…/runners/`            | Keeps shared infrastructure out of test sources             |
| Use `@org.junit.Test`, `@Before`, `@After`, `@BeforeClass`, `@AfterClass` | JUnit 4 API — Jupiter annotations are not on the classpath  |
| Use `@Ignore` to skip, not `@Disabled`                                    | `@Disabled` is JUnit 5/6 only                               |
| Use `@Category` for grouping, not `@Tag`                                  | `@Tag` is JUnit 5/6 only                                    |
| Use `@Test(timeout = ms)` for time limits                                 | No `@Timeout` annotation in JUnit 4                         |
| Use `@FixMethodOrder` for ordering                                        | No `@TestMethodOrder` in JUnit 4                            |
| No `junit-bom` — all versions explicit in `pom.xml`                       | BOM-based version management is a JUnit 5/6 pattern         |
