# SDLC Buddy

This is a sample Flutter app that generates a software development life cycle (SDLC) plan using an AI API.

## Setup

1. Install Flutter 3.x following the [Flutter documentation](https://docs.flutter.dev/get-started/install).
2. Clone this repository.
3. Create a `.env` file at the project root with your API key:

```
OPENAI_API_KEY=your_key_here
```

## Development

Run in debug mode:

```
flutter pub get
flutter run
```

### Testing

```
flutter test
```

### Build release APK

```
flutter build apk --release
```

To sign the APK, configure `key.properties` and update `android/app/build.gradle` as per the Flutter docs.

## Architecture

The project follows a simple Clean Architecture using Riverpod.

```
lib/
  src/
    domain/ - Entities, repositories, use cases
    data/ - Datasources and repository implementations
    ui/ - Presentation widgets and pages
```

## Wireframes

```
[ Home ]
+------------------------------+
| textarea                     |
|                              |
+------------------------------+
| [Generate SDLC Plan]         |
+------------------------------+

[ History ]
List of saved plans
```
