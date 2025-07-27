import '../repositories/plan_repository.dart';
import '../models/sdlc_plan.dart';

class GeneratePlan {
  final PlanRepository repository;
  GeneratePlan(this.repository);

  Future<SDLCPlan> call(String requirement) {
    return repository.generatePlan(requirement);
  }
}
