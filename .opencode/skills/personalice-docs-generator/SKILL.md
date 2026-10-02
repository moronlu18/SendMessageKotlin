---
name: personalice-docs-generator
description: Generate technical documentation for Kotlin files, classes, methods, or API endpoints, and update Android project README.md files using best practices. Trigger when the user asks to document code, generate README files, write Kotlin KDoc, document APIs, or compile technical specifications for modules/functions.
---

# Docs Generator Skill

Use this skill to generate high-quality technical documentation for Android Kotlin codebases and project-level README.md files.

## Workflow

### 1. Documenting Kotlin Code (KDoc)
When requested to document classes, functions, or interfaces:
- Write standard Kotlin KDoc comments.
- **Classes/Interfaces Template**:
  ```kotlin
  /**
   * [Brief description of the class purpose and its role in the architecture].
   *
   * @property [propertyName] [description of the property]
   */
  ```
- **Functions Template**:
  ```kotlin
  /**
   * [Action verb describing what the function does].
   *
   * @param [paramName] [description of parameter]
   * @return [description of return value]
   * @throws [ExceptionType] [when this exception is thrown]
   */
  ```
- Make the documentation concise, explaining *why* the code behaves as it does, not just *what* it does.

### 2. Updating or Creating Project README.md
When requested to document the project:
- Refer to the guidelines in [android_readme_guidelines.md](file://references/android_readme_guidelines.md) for structural and SEO suggestions.
- Ensure only a single `#` heading is present for the title.
- Integrate details about the project setup, tech stack (e.g., Jetpack Compose, Kotlin), and features.
- **Validation**: After creating or modifying a README.md, run the validation script:
  ```bash
  python .opencode/skills/personalice-docs-generator/scripts/validate_readme.py <path_to_readme>
  ```

### 3. API / Endpoint Documentation
When documenting API endpoints or service modules:
- List request parameters, headers, and body structures.
- Detail responses, including status codes (2xx, 4xx, 5xx) and JSON examples.
- Include error cases and fallback mechanisms.
