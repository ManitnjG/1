#!/usr/bin/env bash
set -euo pipefail

# Usage:
#   ./build_from_github.sh <github_repo_url> [branch] [project_subdir]
# Example:
#   ./build_from_github.sh https://github.com/your-org/OpenMusicPro.git main OpenMusicPro

REPO_URL="${1:-}"
BRANCH="${2:-main}"
PROJECT_SUBDIR="${3:-}"

if [[ -z "$REPO_URL" ]]; then
  echo "Usage: $0 <github_repo_url> [branch] [project_subdir]"
  exit 1
fi

WORKDIR="$(mktemp -d)"
cleanup() {
  rm -rf "$WORKDIR"
}
trap cleanup EXIT

echo "[1/6] Cloning $REPO_URL (branch: $BRANCH)"
git clone --depth 1 --branch "$BRANCH" "$REPO_URL" "$WORKDIR/repo"

cd "$WORKDIR/repo"
if [[ -n "$PROJECT_SUBDIR" ]]; then
  cd "$PROJECT_SUBDIR"
fi

if [[ ! -f "gradlew" ]]; then
  echo "Gradle wrapper not found. Generating wrapper with local gradle..."
  if ! command -v gradle >/dev/null 2>&1; then
    echo "ERROR: Neither gradlew nor local gradle found. Install Gradle 8.7+ and retry."
    exit 2
  fi
  gradle wrapper --gradle-version 8.7
fi

echo "[2/6] Making wrapper executable"
chmod +x ./gradlew

echo "[3/6] Printing Java/Gradle versions"
java -version || true
./gradlew --version

echo "[4/6] Running clean"
./gradlew clean

echo "[5/6] Building debug APK"
./gradlew :app:assembleDebug

echo "[6/6] Running unit tests"
./gradlew :app:testDebugUnitTest

APK_PATH="app/build/outputs/apk/debug/app-debug.apk"
if [[ -f "$APK_PATH" ]]; then
  echo "Build success: $PWD/$APK_PATH"
else
  echo "Build completed, but APK path not found at expected location: $APK_PATH"
fi
