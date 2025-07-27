import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:sdlc_buddy/src/domain/usecases/generate_plan.dart';
import 'package:sdlc_buddy/src/domain/models/sdlc_plan.dart';
import 'package:sdlc_buddy/src/domain/repositories/plan_repository.dart';

class MockRepo extends Mock implements PlanRepository {}

void main() {
  test('GeneratePlan returns plan', () async {
    final repo = MockRepo();
    final usecase = GeneratePlan(repo);
    final plan = SDLCPlan(requirement: 'req', phases: [], risks: [], milestones: [], roles: []);
    when(repo.generatePlan('req')).thenAnswer((_) async => plan);
    final result = await usecase('req');
    expect(result, plan);
  });
}
