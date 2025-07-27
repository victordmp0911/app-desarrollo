import '../../domain/models/sdlc_plan.dart';

abstract class IAClient {
  Future<SDLCPlan> generatePlan(String requirement, {Map<String, dynamic>? options});
}
