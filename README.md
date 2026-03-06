# EldenBuilds

Android app to create, edit, and manage **Elden Ring** builds, with Google authentication (Firebase Auth) and cloud persistence (Cloud Firestore).

## Current Status

- App version: `0.5-beta`
- Minimum Android: `API 24`
- `compileSdk/targetSdk`: `36`
- Build system: `AGP 9.1.0` + `Gradle 9.3.1`
- Language: Java
- UI: Material Components + RecyclerView

## Implemented Features

- Google login via Credential Manager.
- Build persistence per authenticated user (`authorId` in Firestore).
- Build listing sorted by name.
- Build creation/editing with:
  - Starting class.
  - Stats.
  - Equipment (weapons, armor, talismans).
  - Notes.
- Automatic level calculation based on starting class + stat distribution.
- Build details screen with edit/delete actions.
- Unit tests for:
  - Level calculation.
  - `Build` equality (prevents regressions in list diffing).

## Architecture (current)

Simple layered structure:

- `ui/*`: Activities, Adapter, ViewModel.
- `data/model/*`: serializable domain models for Firestore/Intent.
- `data/repository/*`: Firestore/Auth access.
- `domain/*`: pure business rules (`BuildLevelCalculator`).

Data flow:

1. User authenticates in `LoginActivity`.
2. `BuildRepository` listens to `builds` filtered by `authorId`.
3. `BuildViewModel` exposes `LiveData<List<Build>>`.
4. `MainActivity` observes and updates `BuildAdapter`.

## Folder Structure

```text
app/src/main/java/com/luix/eldenbuilds
├── data
│   ├── model
│   │   ├── Build.java
│   │   ├── StartingClass.java
│   │   └── Stats.java
│   └── repository
│       └── BuildRepository.java
├── domain
│   └── BuildLevelCalculator.java
└── ui
    ├── adapter
    │   └── BuildAdapter.java
    ├── detail
    │   ├── AddEditBuildActivity.java
    │   └── BuildDetailActivity.java
    ├── list
    │   └── MainActivity.java
    ├── login
    │   └── LoginActivity.java
    └── viewmodel
        └── BuildViewModel.java
```

## Main Dependencies

- AndroidX AppCompat `1.7.1`
- Material `1.13.0`
- Activity `1.12.4`
- ConstraintLayout `2.2.1`
- Firebase BOM `34.10.0`
  - Firebase Auth
  - Cloud Firestore
- Credential Manager `1.5.0`
- Google Identity (`googleid`) `1.2.0`
- JUnit `4.13.2`

## Prerequisites

- Recent Android Studio version (with AGP 9.1+ support).
- JDK 17 (practical requirement for AGP 9.x).
- Android SDK installed.
- Firebase project configured.

## Firebase Setup

1. Create/open a Firebase project.
2. Add an Android app with `applicationId`:
   - `com.luix.eldenbuilds`
3. Download `google-services.json` and place it at:
   - `app/google-services.json`
4. Enable in Firebase:
   - Authentication > Google Sign-In
   - Cloud Firestore
5. Ensure correct SHA-1/SHA-256 fingerprints in the Firebase Android app (required for Google login in some build/device scenarios).

Note:

- `google-services.json` is ignored by git for security.

## Build and Run

### Android Studio

- Open the project.
- Sync Gradle.
- Run `app` on emulator/device.

### CLI (Windows)

```powershell
.\gradlew.bat assembleDebug
.\gradlew.bat testDebugUnitTest
.\gradlew.bat lintDebug
```

### CLI (macOS/Linux)

```bash
./gradlew assembleDebug
./gradlew testDebugUnitTest
./gradlew lintDebug
```

## Quality and Checks Status

- Unit tests: passing.
- Lint: no errors; one remaining warning for optional Gradle wrapper update (`9.3.1` -> `9.4.0`).

## Relevant Technical Decisions

- `Build.equals/hashCode` now includes full content so `ListAdapter` detects real changes after edits.
- Null-safety strengthened for `Intent` payload handling to avoid crashes in create/edit flows.
- Explicit `NoCredentialException` handling in login flow.
- `MainActivity` is no longer exported in the manifest (reduced attack surface).
- Hardcoded UI text removed from main screens and moved into `strings.xml`.

## Current Limitations

- Still based on traditional Activities (no Navigation Component / Compose).
- No dependency injection abstraction yet (e.g., Hilt).
- No instrumented UI tests.
- No CI pipeline configured in the repository.
- Firestore rules are not yet documented/versioned in-project.

## Next Steps

1. **Define and version Firestore security rules**
   - Goal: prevent cross-user read/write access.
   - Deliverable: rules file in repo + deploy guide.

2. **Add test suite for Repository/ViewModel**
   - Goal: cover CRUD behavior, error states, and data mapping.
   - Deliverable: unit tests with Firebase mocks/fakes.

3. **Set up CI (GitHub Actions) with `testDebugUnitTest` + `lintDebug`**
   - Goal: block regressions before merge.
   - Deliverable: workflow running on pull requests.

4. **Refactor `Build` transport from `Serializable` to `Parcelable`**
   - Goal: reduce overhead and remove dependency on `Serializable`.
   - Deliverable: full extras migration across Activities.

5. **Improve main list UX**
   - Goal: explicit loading/empty/error states and sync feedback.
   - Deliverable: dedicated UI states for empty list and network failure.

6. **Plan architecture evolution**
   - Goal: better responsibility boundaries (use cases + DI).
   - Deliverable: incremental proposal (no big-bang), starting with Auth and Builds.