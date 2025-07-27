import 'dart:convert';
import 'package:dio/dio.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import '../../domain/models/sdlc_plan.dart';
import 'ia_client.dart';
import 'package:sdlc_buddy/src/core/app_config.dart';

class OpenAIClient implements IAClient {
  final Dio _dio = Dio();

  @override
  Future<SDLCPlan> generatePlan(String requirement, {Map<String, dynamic>? options}) async {
  final apiKey = AppConfig.openAiKey;
  final response = await _dio.post(
      'https://api.openai.com/v1/chat/completions',
      options: Options(headers: {
        'Authorization': 'Bearer ' + (apiKey ?? ''),
        'Content-Type': 'application/json',
      }),
      data: jsonEncode({
        'model': 'gpt-3.5-turbo',
        'messages': [
          {
            'role': 'system',
            'content': 'You are an assistant that creates detailed SDLC plans in JSON.'
          },
          {
            'role': 'user',
            'content': _buildPrompt(requirement)
          }
        ]
      }),
    );

    final data = response.data['choices'][0]['message']['content'];
    final Map<String, dynamic> json = jsonDecode(data);
    return _parsePlan(requirement, json);
  }

  String _buildPrompt(String requirement) {
    return 'Generate a JSON SDLC plan for: ' + requirement;
  }

  SDLCPlan _parsePlan(String requirement, Map<String, dynamic> json) {
    final phases = (json['phases'] as List)
        .map((e) => Phase(name: e['name'], description: e['description']))
        .toList();
    final risks = (json['risks'] as List)
        .map((e) => Risk(title: e['title'], impact: e['impact']))
        .toList();
    final milestones = (json['milestones'] as List)
        .map((e) => Milestone(title: e['title'], date: DateTime.parse(e['date'])))
        .toList();
    final roles = (json['roles'] as List)
        .map((e) => Role(name: e['name'], responsibility: e['responsibility']))
        .toList();
    return SDLCPlan(
      requirement: requirement,
      phases: phases,
      risks: risks,
      milestones: milestones,
      roles: roles,
    );
  }
}
