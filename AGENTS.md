# AGENTS.md

SendMessage is a small single-module Android app in Kotlin that passes a serializable `Message`/`Person` between two Activities via `Intent`+`Bundle`. This file captures concrete, non-obvious facts needed to avoid mistakes.

## Key sources of truth

- **README**: `/home/lourdes/pCloudDrive/AndroidStudioProjects_26_27/SendMessage/README.md` — features, architecture (LinearLayout layouts, Material Components, AppCompatActivity), dependencies, Dokka flow.
- **Build config**: `app/build.gradle.kts`, `build.gradle.kts`, `gradle/libs.versions.toml`, `gradle.properties`, `settings.gradle.kts`.
- **CI**: `.github/workflows/desplegar-dokka.yml` (deploys Dokka to GitHub Pages).
- **Dokka v2**: `app/build.gradle.kts` (lines ~51-65). `gradle.properties` has `org.jetbrains.dokka.experimental.gradle.pluginMode=V2EnabledWithHelpers` (required for Dokka 2.0.0; safe to keep until upgrading to 2.1.0+).

## Commands (exact)

### Build / run

```bash
# Wrapper may not be executable on clone (100644). Use sh if needed.
sh gradlew :app:assembleDebug

# Install debug build
sh gradlew :app:installDebug

# Run
adb shell am start -n com.example.sendmessage/.SendMessageActivity
```

### Tests

```bash
# Unit tests (JUnit 4) only. No custom test tasks configured beyond defaults.
sh gradlew :app:testDebugUnitTest
sh gradlew :app:testReleaseUnitTest

# Instrumented tests (if present)
sh gradlew :app:connectedDebugAndroidTest
```

### Documentation (Dokka 2.0)

```bash
# Generate HTML (V2). Output directory set in app/build.gradle.kts to:
#   rootProject.file("documentation")  -> documentation/index.html
sh gradlew :app:dokkaGenerate

# Regenerate clean
sh gradlew :app:dokkaGenerate --rerun-tasks
```

## Non-obvious quirks

- **No Kotlin plugin declared explicitly**. `app/build.gradle.kts` only applies `alias(libs.plugins.android.application)` and `alias(libs.plugins.kotlin.dokka)`. AGP 9.3.0 brings Kotlin support; resolved `kotlin-stdlib` is 2.2.10. Don't add `org.jetbrains.kotlin.android` unless you have a specific reason.
- **Dokka V2 experimental flag** (`gradle.properties`): `org.jetbrains.dokka.experimental.gradle.pluginMode=V2EnabledWithHelpers` is required for Dokka 2.0.0. If upgrading to ≥2.1.0, this flag can be removed (v2 becomes default). The build also uses `dokka {}` DSL with `dokkaPublications.html.outputDirectory` and `dokkaSourceSets.register("main")`.
- **Source roots**: in `app/build.gradle.kts`, `dokkaSourceSets.main.sourceRoots.from(file("src/main/java"))` is commented out in the current config (line ~61). Leaving it commented is intentional; explicit source roots are sometimes unnecessary with Android source sets in this setup.
- **Java version split**: `compileOptions.sourceCompatibility/targetCompatibility = JavaVersion.VERSION_11` in `app/build.gradle.kts`. The project declares Gradle toolchain `toolchainVersion=21` (`gradle/gradle-daemon-jvm.properties`) and CI uses JDK 17 (Temurin) to run `dokkaGenerate`. All are compatible; prefer JDK 21 when running locally (`JAVA_HOME=/home/lourdes/.jdks/jbr-21.0.5`).
- **Layouts are LinearLayout** (not ConstraintLayout). Both activities use `LinearLayout`; one wraps a `ScrollView`. `constraintlayout` is declared but unused.
- **Navigation/ktx deps unused**: `androidx.navigation-fragment-ktx`, `androidx.navigation-ui-ktx`, `androidx.core-ktx`, `androidx.activity-ktx` are declared but have no imports in code. Only `androidx.appcompat` and `com.google.android.material` are actually used. `viewBinding = true` is enabled but unused (code uses `findViewById`).
- **INTERNET permission** is declared in the manifest but not used in code (template artifact).
- **Gradle wrapper permissions**: `gradlew` is committed as mode `100644` (not executable). If `./gradlew` fails with "Permission denied", run `sh gradlew ...` or `chmod +x gradlew`.
- **Theme**: effective app theme is `Theme.MaterialComponents.DayNight.DarkActionBar` (bound in AndroidManifest). A `Base.Theme.SendMessage` with Material3 exists but is unused.

## Structural notes

- **Single module**: `:app` only (`settings.gradle.kts`). `rootProject.name = "SendMessage"`.
- **No lint/typecheck/ktlint/detekt configured**. There are no explicit lint/typecheck tasks in Gradle files. Do not assume them exist; check README/build files before running such commands.
- **CI path mismatch**: `.github/workflows/desplegar-dokka.yml` uploads `documentation/html` to Pages, but the Dokka V2 HTML output is configured to `documentation/` (root). If the upload path is wrong in practice, the deployed site will be empty. Verify locally by inspecting `documentation/` after `:app:dokkaGenerate`.
- **Validation script**: `python3 .opencode/skills/personalice-docs-generator/scripts/validate_readme.py README.md` validates H1 count and key section headings. It requires `python3` (not `python`).

## Sources of existing instruction files

No other AGENTS/CLAUDE/Cursor/Copilot instruction files exist in this repo. The only local OpenCode config is the skills under `.opencode/skills/`.