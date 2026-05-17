# Spotube Tidal Plugin

A full-featured Tidal metadata & audio source plugin for [Spotube](https://spotube.krtirtho.dev/) — requires your own Tidal account.

## Requirements

| What | Minimum |
|---|---|
| Spotube | 5.1.0 |
| Dart SDK | 3.0 |
| Tidal account | Free (HiFi for lossless) |

## Features

- 🔐 Login with your Tidal email + password (credentials stored locally)
- 🔍 Search tracks, albums, artists, playlists
- 📚 Browse your library (saved tracks, albums, artists, playlists)
- 📻 Track radio / mix
- 🎵 Audio quality follows your subscription (AAC → FLAC → Hi-Res MQA)
- 🏠 Home page with featured / new releases

## Build locally

```bash
# 1. Install tooling
make deps

# 2. Compile
make build          # → build/plugin.smplug

# 3. Package as zip
make zip            # → dist/spotube-plugin-tidal-1.0.0.zip
```

## Install in Spotube (Android / Desktop)

1. Download the latest `spotube-plugin-tidal-*.zip` from [GitHub Releases](../../releases)
2. Extract — you need `plugin.smplug` and `plugin.json`
3. Open Spotube → Settings → Plugins → **Upload from local file**
4. Select `plugin.smplug`
5. Log in with your Tidal email and password

## Audio quality mapping

| Tidal plan | Quality delivered |
|---|---|
| Free | AAC 96 kbps |
| HiFi | FLAC lossless (CD quality) |
| HiFi Plus | MQA / 24-bit Hi-Res |

Quality is determined entirely by your account tier — the plugin cannot upgrade or downgrade it.

## Build via GitHub Actions

Push to `main` or open a PR — the **Build Spotube Tidal Plugin** workflow compiles the plugin and uploads a `.zip` artifact automatically. Tag a release (`git tag v1.0.1 && git push --tags`) to also create a GitHub Release with the zip attached.

## Project structure

```
spotube-plugin-tidal/
├── plugin.json                  # Plugin metadata (entryPoint, version …)
├── pubspec.yaml
├── Makefile
├── src/
│   ├── main.ht                  # Entry point — TidalPlugin class
│   └── segments/
│       ├── auth.ht              # Login / logout / session recovery
│       ├── user.ht              # Profile, saved library
│       ├── track.ht             # Track info, save/unsave, radio, stream URL
│       ├── album.ht             # Album info, tracks, new releases
│       ├── artist.ht            # Artist info, top tracks, albums, related
│       ├── playlist.ht          # Playlist CRUD, add/remove tracks
│       ├── search.ht            # Full-text search (all types)
│       └── browse.ht            # Home page sections
└── .github/
    └── workflows/
        └── build.yml            # CI: compile → zip → release
```

## Legal

This plugin uses Tidal's undocumented internal API (same approach as [python-tidal](https://github.com/tamland/python-tidal) and [mopidy-tidal](https://github.com/tehkillerbee/mopidy-tidal)). You must own a valid Tidal account. Usage is subject to Tidal's [Terms of Service](https://tidal.com/terms).

## License

MIT
