import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:sdlc_buddy/src/core/app_config.dart';

class SettingsPage extends StatefulWidget {
  const SettingsPage({super.key});

  @override
  State<SettingsPage> createState() => _SettingsPageState();
}

class _SettingsPageState extends State<SettingsPage> {
  late TextEditingController _controller;

  @override
  void initState() {
    super.initState();
    _controller = TextEditingController(text: AppConfig.openAiKey);
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  void _save() {
    AppConfig.setOpenAiKey(_controller.text);
    ScaffoldMessenger.of(context).showSnackBar(
      const SnackBar(content: Text('API Key actualizada')),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Settings')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            TextField(
              controller: _controller,
              decoration: const InputDecoration(labelText: 'API Key'),
            ),
            const SizedBox(height: 16),
            ElevatedButton(
              onPressed: _save,
              child: const Text('Save'),
            )
          ],
        ),
      ),
    );
  }
}
