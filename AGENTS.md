# GitHub Copilot Agent Instructions — JUnit Workshop

## Agent behaviour
- Always read `copilot-instructions.md` (same `.github/` folder) for full project conventions before taking any action.
- This is a **workshop / demo project** — every test class exists to *demonstrate* a JUnit feature. When adding new tests, make them educational: prefer clear, minimal, self-contained examples over production-style abstractions.
- Never silently skip a feature or leave a `TODO` — implement it fully or ask.

## Environment
- **OS:** any (Maven Wrapper included — use `./mvnw` on Unix, `mvnw.cmd` on Windows)
- **Java:** 21 LTS (`JAVA_HOME` must point to JDK 21)
- **Build:** Maven 3.9+ or the bundled wrapper
- **IDE:** IntelliJ IDEA with Lombok plugin enabled
- **CI:** GitHub Actions — `.github/workflows/maven.yml`, triggered manually (`workflow_dispatch`). Two jobs: `regression` (all tests, `./mvnw -B clean site`) and `by-tag` (runs only when `groups` input is provided, `./mvnw -B clean site -Dgroups={groups}`). Both upload `surefire-report` and `junit-xml-results` artifacts. Do **not** change `site` to `test`.

## How to build and test
```bash
# Run all tests
mvn clean test

# Run a single test class
mvn clean test -Dtest=<ClassName>

# Run a single test method
mvn clean test -Dtest=<ClassName>#<methodName>

# Run by tag
mvn clean test -Dgroups=Smoke
mvn clean test -Dgroups=Regression

# Run via Maven profile
mvn clean test -P SmokeTests
mvn clean test -P RegressionTests

# Generate Surefire HTML report
mvn clean surefire-report:report

# Full Maven site
mvn clean site
```

## Common agent tasks

### Add a new test class
1. Decide which feature to demonstrate and pick the correct package under `src/test/…`.
2. Name the class `<Feature>Test` (e.g. `TimeoutTest`, `NestedTest`).
3. Use `@Test` from `org.junit.jupiter.api` — never `org.junit.Test`.
4. Name test methods in `snake_case`.
5. Use **AssertJ** for assertions unless the class is specifically demonstrating Hamcrest.
6. Run `mvn clean test -Dtest=<NewClass>` to verify.

### Add a suite-member class
1. Name it `*Case` or `*Scenario` — **never** `*Test` (Surefire would run it twice).
2. Place it under `src/test/…/suites/`.
3. Reference it in `@SelectClasses` on the relevant `*Suite` class.

### Add a custom JUnit extension
1. Create the implementation in `src/main/java/…/extensions/` (main sources, not test sources).
2. Create a test class in `src/test/java/…/extensions/` that exercises it with `@ExtendWith` or `@RegisterExtension`.

### Add a data provider
1. Create the provider class in `src/main/java/…/dataproviders/`.
2. Implement `ArgumentsProvider` (for `@ArgumentsSource`) or use the TNG-style `@DataProvider` bridge.
3. Reference it from the test class in `src/test/…/ddt/`.

### Add a parameterized test
- Prefer `@MethodSource` pointing to a `static Stream<Arguments>` method in the same class, or to a `dataproviders/` class for reuse.
- Use `@CsvSource` / `@CsvFileSource` for simple tabular data.
- Use `@CartesianTest` (JUnit Pioneer) for full cross-product scenarios.

### Add a tag / meta-annotation
1. Add the string constant to `Tags.java` in `src/test/…/grouping/tags/`.
2. Create the meta-annotation type (e.g. `@Smoke`) in the same `tags/` package.
3. Apply `@Tag(Tags.YOUR_TAG)` or the meta-annotation directly to test methods or classes.

## Key constraints the agent must respect
| Rule                                                                           | Why                                                                     |
|--------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| Suite members named `*Case`, never `*Test`                                     | Surefire discovers `*Test` independently → double execution             |
| Extensions in `src/main/…/extensions/`                                         | Keeps extension code reusable and out of test classpath only            |
| Use `computeIfAbsent` (not `getOrComputeIfAbsent`) in `ExtensionContext` store | `getOrComputeIfAbsent` is deprecated in JUnit 6                         |
| No explicit versions on JUnit artifacts covered by `junit-bom`                 | BOM in `<dependencyManagement>` manages them                            |
| No JUnit Vintage engine                                                        | Only add it on the dedicated `junit-4.*` branch                         |
| No JUnit 4 annotations in JUnit 5/6 classes                                    | `@org.junit.Test`, `@RunWith`, `@Rule` are JUnit 4 only                 |
| Parallel execution is on by default                                            | Annotate with `@Execution(SAME_THREAD)` or `@ResourceLock` where needed |
