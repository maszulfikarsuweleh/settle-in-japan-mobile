# AGENT.MD - SettleInJapan Project Configuration

## Task: Kotlin Style Enforcement (Ktlint)

**Description**:
This task ensures Kotlin code adheres to the project's style guide using Ktlint.

**Triggers**:
  - `on_pre_commit`: For staged files matching the scope.
  - `user_command`: "check kotlin style", "lint kotlin files"
  - `user_command`: "format kotlin style" (for auto-formatting)

**Scope**:
  - `target_modules`: ["composeApp", "shared", "server"]
  - `file_patterns`: ["*.kt", "*.kts"]
  - `exclude_patterns`: ["**/build/**", "**/generated/**"]

**Execution**:
  - **Check Action** (triggered by "check kotlin style" or `on_pre_commit`):
    - `tool`: "gradle"
    - `task`: "ktlintCheck"
    - `success_message`: "Ktlint: All files are correctly formatted."
    - `failure_message_template`: "Ktlint: Found style issues in {count} files. Please review or run 'format kotlin style'. Issues:\n{details}"
    - `block_commit_on_failure` (for `on_pre_commit` trigger): true

  - **Format Action** (triggered by "format kotlin style"):
    - `tool`: "gradle"
    - `task`: "ktlintFormat"
    - `success_message`: "Ktlint: Files have been auto-formatted."
    - `failure_message`: "Ktlint: Auto-formatting failed or some issues require manual review."

---

## Project Guideline: HttpClient Usage

**Description**:
Promotes the use of the centrally configured `HttpClient` provided by Koin for consistency and testability.

**AppliesTo**:
  - `target_modules`: ["composeApp"]
  - `file_patterns`: ["*.kt"]

**CodePatternDetection**:
  - `id`: "manual_http_client_creation"
  - `severity`: "warning"
  - `regex_pattern`: "HttpClient\\s*\\{\\s*\\}"  // Detects `HttpClient { ... }`
  - `message_template`: "Detected manual `HttpClient` instantiation in `{file_path}` at line `{line_number}`. Consider injecting the Koin-provided `HttpClient`. See `di/KoinModule.kt` for DI setup."
  - `suggestion_actions`: ["Show KoinModule.kt", "Explain HttpClient injection"]

**Triggers**:
  - `on_file_save`
  - `on_code_analysis_request`: "analyze http client usage"

---

## AI Context: SettleInJapan Project Details

**Purpose**: Provides essential project information to the AI for better contextual understanding and code generation.

**KeyInformation**:
  - `project_name`: "SettleInJapan"
  - `primary_language`: "Kotlin"
  - `architecture_style`: "Clean Architecture (Data, Domain, Presentation layers within KMP modules)"
  - `dependency_injection`: "Koin (see `di/KoinModule.kt`)"
  - `state_management_presentation`: "Kotlin Flow with StateFlow in ViewModels"
  - `api_details`:
    - `base_url`: "https://settle-in-japan.onrender.com"
    - `auth_endpoint`: "/auth/login"
    - `dto_location`: "composeApp/src/commonMain/kotlin/com/zulfikar/suweleh/settleinjapan/data/remote/dto/"
  - `ui_framework`: "Jetpack Compose for UI (shared in commonMain)"
  - `important_modules`:
    - `composeApp`: Main application UI and KMP shared logic.
    - `shared`: Pure Kotlin shared business logic (if distinct from composeApp's common).
    - `server`: Ktor backend (if managed within the same project).
