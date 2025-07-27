import '../models/sdlc_plan.dart';

abstract class PlanRepository {
  Future<SDLCPlan> generatePlan(String requirement);
  Future<void> savePlan(SDLCPlan plan);
  Future<List<SDLCPlan>> getHistory();
}
