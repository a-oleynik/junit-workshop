# AI Agent Instructions — JUnit Workshop

## Agent behaviour
- Always read `.github/copilot-instructions.md` for full project conventions before taking any action.
- This is a **workshop / demo project** — every test class exists to *demonstrate* a JUnit feature. When adding new tests, make them educational: prefer clear, minimal, self-contained examples over production-style abstractions.
- Never silently skip a feature or leave a `TODO` — implement it fully or ask.
- Validate affected workshop examples with both Maven and Gradle wrappers; follow the validation policy below.

## Environment
- **OS:** any (Maven and Gradle Wrappers included — use `./mvnw` / `./gradlew` on Unix, `mvnw.cmd` / `gradlew.bat` on Windows)
- **Java:** 21 LTS (`JAVA_HOME` must point to JDK 21)
- **Build:** Maven 3.9.12 and Gradle 9.6.0 (bundled wrappers included)
- **IDE:** IntelliJ IDEA with Lombok plugin enabled
- **CI:** GitHub Actions — `.github/workflows/maven.yml`, triggered manually (`workflow_dispatch`). Two jobs: `regression` (all tests, `./mvnw -B clean site`) and `by-tag` (runs only when `groups` input is provided, `./mvnw -B clean site -Dgroups={groups}`). Both upload `surefire-report` and `junit-xml-results` artifacts. Do **not** change `site` to `test`.

## How to build and test

### Maven
```bash
# Run all tests
./mvnw clean test

# Run a single test class
./mvnw clean test -Dtest=<ClassName>

# Run a single test method
./mvnw clean test -Dtest=<ClassName>#<methodName>

# Run by tag
./mvnw clean test -Dgroups=smoke
./mvnw clean test -Dgroups=regression

# Run via Maven profile
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

# Run by tag
./gradlew clean test -DincludeTags=smoke
./gradlew clean test -DincludeTags=regression

# Run via dedicated Gradle task
./gradlew clean smokeTest
./gradlew clean regressionTest

# Generate HTML test report (written to build/reports/tests/test/index.html)
./gradlew clean test
```

## Source organization

`topics` contains examples of individual JUnit features; `scenarios` contains complete examples that combine several features and can be referenced by multiple articles. The Calculator scenario is in `scenarios/calculator` in both main and test sources.

The manual starting point is `src/main/java/…/scenarios/calculator/beforejunit/Main.java`; it uses the shared `Calculator` and leads into `CalculatorTest`.

Place new feature examples under `src/test/java/…/topics/`; place complete article scenarios under `src/main/java/…/scenarios/` and `src/test/java/…/scenarios/`. Follow the current package map in README.

## Common agent tasks

### Add a new test class
1. Decide which feature to demonstrate and pick the correct package under `src/test/…`.
2. Name the class `<Feature>Test` (e.g. `TimeoutTest`, `LifecycleTest`).
3. Use `@Test` from `org.junit.jupiter.api` — never `org.junit.Test`.
4. Name test methods in `snake_case`.
5. Use **AssertJ** for assertions unless the class is specifically demonstrating Hamcrest.
6. Run `./mvnw clean test -Dtest=<NewClass>` and `./gradlew clean test --tests "*<NewClass>"` to verify both builds.

### Add a suite-member class
1. Name it `*Case` or `*Scenario` — **never** `*Test` (Surefire / Gradle would run it twice).
2. Place it under `src/test/…/topics/suites/`.
3. Reference it in `@SelectClasses` on the relevant `*Suite` class.

### Add a custom JUnit extension
1. Create the implementation in `src/main/java/…/topics/extensions/` (main sources, not test sources).
2. Create a test class in `src/test/java/…/topics/extensions/` that exercises it with `@ExtendWith` or `@RegisterExtension`.

### Add a data provider
1. Create the provider class in `src/main/java/…/topics/dataproviders/`.
2. Implement `ArgumentsProvider` (for `@ArgumentsSource`) or use the TNG-style `@DataProvider` bridge.
3. Reference it from the test class in `src/test/…/topics/ddt/`.

### Add a parameterized test
- Prefer `@MethodSource` pointing to a `static Stream<Arguments>` method in the same class, or to a `topics/dataproviders/` class for reuse.
- Use `@CsvSource` / `@CsvFileSource` for simple tabular data.
- Use `@CartesianTest` (JUnit Pioneer) for full cross-product scenarios.

### Add a tag / meta-annotation
1. Add the string constant to `Tags.java` in `src/test/…/topics/grouping/tags/`.
2. Create the meta-annotation type (e.g. `@Smoke`) in the same `topics/grouping/tags/` package.
3. Apply `@Tag(Tags.YOUR_TAG)` or the meta-annotation directly to test methods or classes.

## Key constraints the agent must respect
| Rule                                                           | Why                                                                                                                                               |
|----------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| Suite members named `*Case`, never `*Test`                     | Surefire and Gradle discover `*Test` independently → double execution                                                                             |
| Extensions in `src/main/…/topics/extensions/`                  | Keeps extension code reusable and out of test classpath only                                                                                      |
| Use `getOrComputeIfAbsent` in the `ExtensionContext` store     | Reuses a stored resource or creates it on first access                                                                                            |
| No explicit versions on JUnit artifacts covered by `junit-bom` | BOM in `<dependencyManagement>` (Maven) / `platform()` (Gradle) manages them                                                                      |
| No JUnit Vintage engine                                        | Only add it on the dedicated `junit-4.*` branch                                                                                                   |
| No JUnit 4 annotations in JUnit 5/6 classes                    | `@org.junit.Test`, `@RunWith`, `@Rule` are JUnit 4 only                                                                                           |
| Parallel execution is on by default                            | Configured in `junit-platform.properties`; build files contain commented alternatives; annotate with `@Execution(SAME_THREAD)` or `@ResourceLock` |
| Validate with both Maven and Gradle                            | Both supported builds must run the affected examples successfully                                                                                 |

## Validation with Maven and Gradle

- Every workshop example must work with both Maven and Gradle. Keep corresponding commands in README and guides synchronized.
- Use the pinned wrappers: Maven 3.9.12 and Gradle 9.6.0. On Unix use `./mvnw` and `./gradlew`; on Windows use `mvnw.cmd` and `gradlew.bat`.
- For Java or test changes, run the affected examples with both wrappers. A successful Maven run alone does not establish Gradle compatibility, or vice versa.
- For dependency changes or shared build/test configuration changes, run the full test suite with both wrappers.
- For documentation-only changes, check relative links, heading anchors, and consistency with the actual source/build files. If runnable commands are added or changed, execute the corresponding commands for both build tools.
- Check actual test counts, callback output, and reports when these are part of the example; build success alone is not sufficient.
- Report Maven and Gradle validation separately, including commands run, results, and any verification that could not be completed.
- The current GitHub Actions workflow runs Maven only. It does not replace local Gradle validation.
