# OpenMusic Pro

Production-ready scaffold for a premium Spotify-style music player built with Kotlin + Jetpack Compose + Media3.

## Included
- Clean architecture folders (data/domain/presentation)
- MVVM-ready Compose app shell
- Media service placeholder for Media3 session integration
- Node.js backend scaffold with JWT auth and trending endpoint
- CI workflow for Android lint + unit tests

## Tech stack
Kotlin, Compose, Material 3, Hilt, Room, Retrofit, Coroutines, Paging 3, Coil, WorkManager, Media3/ExoPlayer.

## Setup
1. Open `OpenMusicPro` in Android Studio (latest stable).
2. Install Android SDK 35 and JDK 17.
3. Run `./gradlew assembleDebug`.
4. Backend: `cd backend/nodejs && npm install && npm run dev`.

## Notes
- This repository intentionally uses only legal/open audio sources. Add connectors for MusicBrainz, Last.fm, Genius, Jamendo, Archive.org, radio, WebDAV, SMB/NAS.
- Add secrets via `local.properties` / CI secrets.

## Build from GitHub (Bash)
Use the provided script to clone and compile from a GitHub repository:

```bash
cd scripts
./build_from_github.sh <github_repo_url> [branch] [project_subdir]
```

Notes:
- Yes, this script is specifically for building the Android app module (`:app`) from a GitHub repository.
- If the repo root is not the Android project root, pass `project_subdir` (for this layout, use `OpenMusicPro`).

Example (this repo layout):

```bash
cd scripts
./build_from_github.sh https://github.com/your-org/your-repo.git main OpenMusicPro
```


### GitHub Actions build
The workflow `.github/workflows/android-ci.yml` now uses `scripts/build_from_github.sh` to compile from GitHub, then runs a local fallback build and uploads the debug APK artifact.
