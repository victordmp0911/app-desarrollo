import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';

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
    _controller = TextEditingController(text: dotenv.env['OPENAI_API_KEY']);
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
              onPressed: () async {
                await dotenv.env['OPENAI_API_KEY'] = _controller.text;
              },
              child: const Text('Save'),
            )
          ],
        ),
      ),
    );
  }
}
