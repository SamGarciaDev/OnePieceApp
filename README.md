# OnePieceApp
This project is an Android App that communicates with [a back-end server](https://github.com/SamGarciaIGF/OnePieceServer) to display different One Piece characters. It's roughly based on [this course](https://www.udemy.com/course/build-modern-android-app-with-rest-api-and-ktor-server).

## Tech stack
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
  - [Navigation](https://developer.android.com/jetpack/compose/navigation) - navigate between components
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - paging library
  - [Coil](https://coil-kt.github.io/coil/) - image loading library
  - [Accompanist](https://github.com/google/accompanist) - extension libraries for Jetpack Compose
  - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - key-value storage
- [Room](https://developer.android.com/training/data-storage/room) - Android local database
- [Hilt](https://dagger.dev/hilt/) - Dagger dependency injection for Android
- [Retrofit](https://square.github.io/retrofit/) - HTTP client for Android
- [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization) - serialization
- Palette KTX - generate palettes from images
