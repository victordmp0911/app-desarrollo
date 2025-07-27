import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../data/datasources/openai_client.dart';
import '../../data/repositories/plan_repository_impl.dart';
import '../../domain/usecases/generate_plan.dart';
import 'package:hive/hive.dart';

final hiveBoxProvider = Provider<Box>((ref) => Hive.box('plans'));

final iaClientProvider = Provider((ref) => OpenAIClient());

final repositoryProvider = Provider((ref) =>
    PlanRepositoryImpl(ref.read(iaClientProvider), ref.read(hiveBoxProvider)));

final generatePlanProvider = Provider((ref) =>
    GeneratePlan(ref.read(repositoryProvider)));
