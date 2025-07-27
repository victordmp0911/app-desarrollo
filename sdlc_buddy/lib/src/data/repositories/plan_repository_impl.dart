import '../datasources/ia_client.dart';
import '../../domain/models/sdlc_plan.dart';
import '../../domain/repositories/plan_repository.dart';
import 'package:hive/hive.dart';

class PlanRepositoryImpl implements PlanRepository {
  final IAClient client;
  final Box box;

  PlanRepositoryImpl(this.client, this.box);

  @override
  Future<SDLCPlan> generatePlan(String requirement) {
    return client.generatePlan(requirement);
  }

  @override
  Future<void> savePlan(SDLCPlan plan) async {
    await box.add(plan); // simplistic
  }

  @override
  Future<List<SDLCPlan>> getHistory() async {
    return box.values.cast<SDLCPlan>().toList();
  }
}
