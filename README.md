# DevPlanner

DevPlanner is a simple project management Android application. It lets you create projects, plan tasks, and track progress through a task board. The app is built with Kotlin, Jetpack Compose, Hilt, Room, and WorkManager.

## Prerequisites

- Android Studio Electric Eel or newer with the Android SDK
- JDK 17 or newer

## Building and Running

1. Open Android Studio and select **Open**. Choose the project directory `DevPlanner_v5/DevPlanner_v5`.
2. Let Android Studio download the required Gradle components. Once synced, you can build and run the `app` module on a device or emulator using the **Run** button.

If you have the Gradle wrapper installed locally you can also build from the command line:

```sh
./gradlew assembleDebug
```

## Directory Structure

```
app-desarrollo/
└── DevPlanner_v5/DevPlanner_v5/
    ├── app/               # Android application module
    │   └── src/main/java/com/example/devplanner/
    │       ├── data       # Room entities and DAO
    │       ├── domain     # Repository interfaces and use cases
    │       ├── di         # Hilt modules
    │       ├── ui         # Compose screens and navigation
    │       ├── util       # Export helpers and utilities
    │       └── work       # Background workers
    ├── build.gradle.kts   # Project level build settings
    └── settings.gradle.kts
```

Launch the application by running the **app** configuration from Android Studio. It will install the debug APK on the selected device or emulator.

## License

This repository does not currently include license information. Unless a license is added, all rights are reserved by the author.

