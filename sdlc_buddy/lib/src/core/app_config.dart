import 'package:flutter_dotenv/flutter_dotenv.dart';

class AppConfig {
  // Valor inicial: lo que tengas en tu archivo .env
  static String _openAiKey = dotenv.env['OPENAI_API_KEY'] ?? '';

  // Getter (para leer la clave)
  static String get openAiKey => _openAiKey;

  // Setter (para cambiar la clave en tiempo de ejecución)
  static void setOpenAiKey(String value) {
    _openAiKey = value.trim();
  }
}