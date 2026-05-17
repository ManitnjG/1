# Android App Blueprint: Spotify-like UI with TIDAL Backend (FLAC + Hi-Res)

## Goal
Build a **standalone Android app** (not a web wrapper) that provides:
- Spotify-like navigation and discovery UX.
- TIDAL account login and playback integration.
- FLAC and Hi-Res playback support where stream rights and device output allow.

---

## 1) Product Scope (MVP)

### Core features
1. Email/social login through TIDAL auth.
2. Home screen with personalized sections (new releases, mixes, favorites).
3. Search (tracks, artists, albums, playlists).
4. Album/playlist details + queue management.
5. Background playback with media notification controls.
6. Favorites (like/unlike) and playlist add/remove.
7. Streaming quality selector:
   - AAC (data saving)
   - Lossless (FLAC)
   - Hi-Res/Lossless+ when available
8. Offline mode (optional post-MVP due to DRM/licensing complexity).

### Non-functional
- Android 9+ minimum recommended.
- Smooth startup and scrolling on mid-range devices.
- Crash-free playback transitions and robust reconnect behavior.

---

## 2) Technical Architecture

### Recommended stack
- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** Clean Architecture + MVVM
- **DI:** Hilt
- **Async:** Coroutines + Flow
- **Playback:** Media3 ExoPlayer
- **Local storage:** Room + DataStore
- **Networking:** Retrofit + OkHttp + Kotlinx Serialization/Moshi

### Modules
- `app`: entrypoint, navigation, DI wiring.
- `core-ui`: theme, reusable composables.
- `core-network`: API clients, auth interceptors.
- `core-player`: Media3 player manager, queue, audio focus.
- `feature-home`, `feature-search`, `feature-library`, `feature-player`.
- `data`: repositories and mappers.
- `domain`: use cases + entities.

---

## 3) TIDAL Integration Notes

1. Join official TIDAL developer program and obtain API credentials.
2. Implement OAuth2 / PKCE authorization flow.
3. Store tokens securely (EncryptedSharedPreferences or encrypted DataStore).
4. Fetch playback URLs from TIDAL APIs (do not hardcode stream URLs).
5. Respect entitlement flags and territory restrictions for Hi-Res/Lossless playback.

> Important: Verify terms of service, branding, and licensing for third-party playback apps before release.

---

## 4) FLAC + Hi-Res Playback Strategy

### Player configuration
- Use Media3 ExoPlayer default extractors for FLAC streams.
- Configure adaptive/quality policy based on network and user setting.
- Add user option to force lossless on Wi-Fi only.

### Output path considerations
- Android device DAC and audio HAL affect true Hi-Res output.
- For bit-perfect style output, validate:
  - hardware capability,
  - USB DAC paths,
  - Android resampling behavior.
- Show capability badges in UI (e.g., `HIRES`, `LOSSLESS`, `AAC`).

### Recommended quality policy
- Mobile data: AAC by default.
- Wi-Fi: Lossless default.
- Hi-Res: opt-in and only when supported.

---

## 5) Security + Reliability

- Never log access tokens or raw playback URLs.
- Implement token refresh + retry policy.
- Certificate pinning (optional but recommended for production).
- Add playback watchdog for stuck buffering and auto-recovery.
- Add analytics events for buffering ratio, startup time, and playback errors.

---

## 6) Suggested Folder Skeleton

```text
app/
core/
  core-ui/
  core-network/
  core-player/
feature/
  feature-home/
  feature-search/
  feature-library/
  feature-player/
data/
  remote/
  local/
  repository/
domain/
  model/
  usecase/
```

---

## 7) MVP Delivery Plan (6 Weeks)

1. **Week 1:** Project setup, auth flow, base navigation.
2. **Week 2:** Home/search APIs and list rendering.
3. **Week 3:** Player service + queue + notification controls.
4. **Week 4:** Library features (likes/playlists), quality settings.
5. **Week 5:** Error handling, caching, performance pass.
6. **Week 6:** QA, beta rollout, crash/perf fixes.

---

## 8) Minimal Playback Service Example (Kotlin)

```kotlin
@AndroidEntryPoint
class PlaybackService : MediaSessionService() {
    @Inject lateinit var player: ExoPlayer
    private lateinit var mediaSession: MediaSession

    override fun onCreate() {
        super.onCreate()
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession {
        return mediaSession
    }

    override fun onDestroy() {
        mediaSession.release()
        player.release()
        super.onDestroy()
    }
}
```

---

## 9) Next Step to Start Implementation

If you want, next iteration can generate a ready-to-open Android Studio starter with:
- Compose + Hilt + Media3 setup,
- OAuth PKCE scaffolding,
- home/search/player screens,
- repository interfaces for TIDAL APIs.
