# GitHub Copilot Agent Instructions — JUnit 4 Workshop

## Agent behaviour
- Always read `.github/copilot-instructions.md` for full project conventions before taking any action.
- This is a **workshop / demo project** — every test class exists to *demonstrate* a JUnit 4 feature. When adding new tests, make them educational: prefer clear, minimal, self-contained examples over production-style abstractions.
- Never silently skip a feature or leave a `TODO` — implement it fully or ask.
- Validate affected workshop examples with both Maven and Gradle wrappers; follow the validation policy below.

## Environment
- **OS:** any (Maven and Gradle Wrappers included — use `./mvnw` / `./gradlew` on Unix, `mvnw.cmd` / `gradlew.bat` on Windows)
- **Java:** 17 LTS (`JAVA_HOME` must point to JDK 17)
- **Build:** Maven 3.9.12 and Gradle 9.6.0 (bundled wrappers included)
- **IDE:** IntelliJ IDEA with Lombok plugin enabled
- **CI:** GitHub Actions — `.github/workflows/maven.yml`, triggered manually (`workflow_dispatch`). Two jobs: `regression` (all tests, `./mvnw -B clean site`) and `by-tag` (runs only when `groups` input is provided — must be a fully-qualified category class name, e.g. `com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests`). Both upload `surefire-report` and `junit-xml-results` artifacts. Do **not** change `site` to `test`.

## How to build and test

### Maven
```bash
# Run all tests
./mvnw clean test

# Run a single test class
./mvnw clean test -Dtest=<ClassName>

# Run a single test method
./mvnw clean test -Dtest=<ClassName>#<methodName>

# Run by category
./mvnw clean test -Dgroups=com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests
./mvnw clean test -Dgroups=com.oleynik.qa.workshop.junit.topics.grouping.categories.RegressionTests

# Run via Maven profile (smoke or regression — resolves to the fully-qualified category class internally)
./mvnw clean test -P smoke
./mvnw clean test -P regression

# Generate Surefire HTML report
./mvnw clean surefire-report:report

# Full Maven site
./mvnw clean site
```

### Gradle
```bash
# Run all tests
./gradlew clean test

# Run a single test class
./gradlew clean test --tests "*<ClassName>"

# Run a single test method
./gradlew clean test --tests "*<ClassName>.<methodName>"

# Run by category
./gradlew clean test -DincludeCategories=com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests
./gradlew clean test -DincludeCategories=com.oleynik.qa.workshop.junit.topics.grouping.categories.RegressionTests

# Run via dedicated Gradle task
./gradlew clean smokeTest
./gradlew clean regressionTest

# Generate HTML test report (written to build/reports/tests/test/index.html)
./gradlew clean test
```

## Source organization

`topics` contains examples of individual JUnit features; `scenarios` contains complete examples that combine several features and can be referenced by multiple articles. The Calculator scenario is in `scenarios/calculator` in both main and test sources.

The manual starting point is `src/main/java/…/scenarios/calculator/beforejunit/Main.java`; it uses the shared `Calculator` and leads into `CalculatorTest`.

Place feature examples under `src/test/java/…/topics/` and shared helpers under `src/main/java/…/topics/`. Place complete scenarios under `scenarios/` in the corresponding main and test source trees. Follow the package map in README.

## Common agent tasks

### Add a new test class
1. Decide which feature to demonstrate and pick the correct package under `src/test/…`.
2. Name the class `<Feature>Test` (e.g. `TimeoutTest`, `LifecycleTest`).
3. Use `@org.junit.Test` — never `@org.junit.jupiter.api.Test`.
4. Use `@Before` / `@After` / `@BeforeClass` / `@AfterClass` for lifecycle methods.
5. Name test methods in `snake_case`.
6. Use **AssertJ** for assertions unless the class is specifically demonstrating Hamcrest or JUnit 4 `Assert`.
7. Run `./mvnw clean test -Dtest=<NewClass>` and `./gradlew clean test --tests "*<NewClass>"` to verify both builds.

### Add a suite-member class
1. Name it `*Case` or `*Scenario` — **never** `*Test` (Surefire / Gradle would run it twice).
2. Place it under the appropriate `src/test/…/topics/suites/` sub-package.
3. Reference it in `@Suite.SuiteClasses` on the relevant suite class.

### Add a custom rule
1. Create the implementation in `src/main/java/…/topics/rules/` (main sources, not test sources).
2. Extend `ExternalResource`, `TestWatcher`, or implement `TestRule` / `MethodRule` as appropriate.
3. Create a test class in `src/test/java/…/topics/rules/` that exercises it with a `@Rule` or `@ClassRule` field.

### Add a custom runner
1. Create the runner in `src/main/java/…/topics/runners/`.
2. Apply it in the test class with `@RunWith(MyRunner.class)`.
3. Note: only one `@RunWith` can be active per class — use delegation or composition if multiple behaviours are needed.

### Add a data provider
1. Create the provider class in `src/main/java/…/topics/dataproviders/` using the TNG-style `@DataProvider` annotation from `junit4-dataprovider`.
2. Reference it from the test class in `src/test/…/topics/ddt/` with `@UseDataProvider`.
3. For `JUnitParams`-style tests, use `@RunWith(JUnitParamsRunner.class)` and `@Parameters`.

### Add a category / grouping
1. Create a new marker interface in `src/test/…/topics/grouping/categories/`.
2. Apply `@Category(MyCategory.class)` to test methods or the whole class.

## Key constraints the agent must respect
| Rule                                                                         | Why                                                                                  |
|------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| Suite members named `*Case`, never `*Test`                                   | Surefire and Gradle discover `*Test` independently → double execution                |
| Rules in `src/main/…/topics/rules/`, runners in `src/main/…/topics/runners/` | Keeps shared infrastructure out of test sources                                      |
| Use `@org.junit.Test`, `@Before`, `@After`, `@BeforeClass`, `@AfterClass`    | JUnit 4 API — Jupiter annotations are not on the classpath                           |
| Use `@Ignore` to skip, not `@Disabled`                                       | `@Disabled` is JUnit 5/6 only                                                        |
| Use `@Category` for grouping, not `@Tag`                                     | `@Tag` is JUnit 5/6 only                                                             |
| Use `@Test(timeout = ms)` for time limits                                    | No `@Timeout` annotation in JUnit 4                                                  |
| Use `@FixMethodOrder` for ordering                                           | No `@TestMethodOrder` in JUnit 4                                                     |
| No `junit-bom` — all versions explicit in `pom.xml` / `build.gradle`         | BOM-based version management is a JUnit 5/6 pattern                                  |
| Validate with both Maven and Gradle | Both supported builds must run the affected examples successfully |

## Documentation organization

- Article companions belong in `docs/articles/`; the JUnit 4 adaptation is `docs/articles/junit-6-from-scratch.md`.
- Detailed explanations of README topics belong in `docs/guides/`.
- Keep README learning paths focused on the goal, source examples, and commands; link guides for implementation details.
- In these nested documents, use `../../` for links to README, source files, and build files at repository root.
- `junit-lifecycle.md` covers suite-scoped resources and explicit JUnitCore runs; `extension-and-listener-registration.md` distinguishes Surefire RunListener events from Gradle TestListener events.
- Do not describe externally registered listener callbacks as a single resource setup/teardown pair for the entire build or JVM.

## Validation with Maven and Gradle

- Every workshop example must work with both Maven and Gradle. Keep corresponding commands in README and guides synchronized.
- Use the pinned wrappers: Maven 3.9.12 and Gradle 9.6.0. On Unix use `./mvnw` and `./gradlew`; on Windows use `mvnw.cmd` and `gradlew.bat`.
- For Java or test changes, run the affected examples with both wrappers. A successful Maven run alone does not establish Gradle compatibility, or vice versa.
- For dependency changes or shared build/test configuration changes, run the full test suite with both wrappers.
- For documentation-only changes, check relative links, heading anchors, and consistency with the actual source/build files. If runnable commands are added or changed, execute the corresponding commands for both build tools.
- Check actual test counts, callback output, and reports when these are part of the example; build success alone is not sufficient.
- Report Maven and Gradle validation separately, including commands run, results, and any verification that could not be completed.
- The current GitHub Actions workflow runs Maven only. It does not replace local Gradle validation.
