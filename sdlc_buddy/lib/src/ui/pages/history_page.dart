import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../providers/providers.dart';
import '../../domain/models/sdlc_plan.dart';

class HistoryPage extends ConsumerWidget {
  const HistoryPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final box = ref.watch(hiveBoxProvider);
    final plans = box.values.cast<SDLCPlan>().toList();
    return Scaffold(
      appBar: AppBar(title: const Text('History')),
      body: ListView.builder(
        itemCount: plans.length,
        itemBuilder: (_, i) {
          final plan = plans[i];
          return ListTile(
            title: Text(plan.requirement),
          );
        },
      ),
    );
  }
}
