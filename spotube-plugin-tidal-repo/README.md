# Spotube Tidal Plugin (Standalone Repo)

This is a standalone repository layout for the Spotube Tidal Hetu plugin.

## Build with GitHub Actions

The workflow at `.github/workflows/build.yml` builds and packages the plugin automatically:

- Trigger: push to `main/master`, pull request, manual dispatch, or `v*` tag.
- Output: zipped artifact in `dist/` uploaded through Actions artifacts.
- Release: tag builds (`v*`) create a GitHub release with the zip attached.

## Local build (optional)

```bash
make deps
make build
make zip
```

## Files

- `src/main.ht` — plugin entrypoint
- `src/segments/*.ht` — plugin features
- `plugin.json` — plugin metadata/version
- `Makefile` — local build helpers
- `.github/workflows/build.yml` — CI build and packaging
