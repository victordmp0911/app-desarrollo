import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../providers/providers.dart';
import '../../domain/models/sdlc_plan.dart';

class HomePage extends ConsumerStatefulWidget {
  const HomePage({super.key});
  @override
  ConsumerState<HomePage> createState() => _HomePageState();
}

class _HomePageState extends ConsumerState<HomePage> {
  final TextEditingController _controller = TextEditingController();
  SDLCPlan? _plan;
  bool _loading = false;
  String? _error;

  Future<void> _generate() async {
    setState(() {
      _loading = true;
      _error = null;
    });
    try {
      final plan = await ref.read(generatePlanProvider)(
        _controller.text,
      );
      setState(() => _plan = plan);
    } catch (e) {
      setState(() => _error = e.toString());
    } finally {
      setState(() => _loading = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('SDLC Buddy')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            TextField(
              controller: _controller,
              decoration: const InputDecoration(
                labelText: 'Requirement',
              ),
              minLines: 3,
              maxLines: 5,
            ),
            const SizedBox(height: 16),
            ElevatedButton(
              onPressed: _loading ? null : _generate,
              child: Text(_loading ? 'Loading...' : 'Generate SDLC Plan'),
            ),
            if (_error != null) Text(_error!, style: const TextStyle(color: Colors.red)),
            if (_plan != null) Expanded(child: ListView(children: [
              ..._plan!.phases.map((p) => ListTile(title: Text(p.name), subtitle: Text(p.description))),
            ]))
          ],
        ),
      ),
    );
  }
}
