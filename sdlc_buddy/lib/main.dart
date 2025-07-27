import 'package:flutter/foundation.dart' show kIsWeb;
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:hive_flutter/hive_flutter.dart';

import 'src/core/config/app_config.dart';
import 'src/core/storage/hive_service.dart';
import 'src/ui/pages/home_page.dart';
import 'src/ui/pages/history_page.dart';
import 'src/ui/pages/settings_page.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const AppBootstrap()); // <-- SIN ProviderScope aquí
}

/// Bootstrap para asegurarnos de que Hive (y dotenv cuando aplique) estén listos
class AppBootstrap extends StatelessWidget {
  const AppBootstrap({super.key});

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: _initialize(),
      builder: (context, snapshot) {
        if (snapshot.connectionState != ConnectionState.done) {
          return const MaterialApp(
            home: Scaffold(
              body: Center(child: CircularProgressIndicator()),
            ),
          );
        }

        // <-- ProviderScope DESPUÉS de que Hive esté listo
        return const ProviderScope(child: MyApp());
      },
    );
  }

  Future<void> _initialize() async {
    if (!kIsWeb) {
      try {
        await dotenv.load(fileName: '.env');
      } catch (_) {/* ignore */}
    }

    await Hive.initFlutter();
    await HiveService.ensureBoxes();        // abrir boxes aquí
    await HiveService.bootstrapAppConfig(); // cargar config si aplica
  }
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'SoftDev',
      theme: ThemeData.light(useMaterial3: true),
      darkTheme: ThemeData.dark(useMaterial3: true),
      localizationsDelegates: const [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: const [Locale('en'), Locale('es')],
      home: const HomePage(),
      routes: {
        '/history': (_) => const HistoryPage(),
        '/settings': (_) => const SettingsPage(),
      },
    );
  }
}
