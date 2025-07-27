import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:sdlc_buddy/main.dart';

void main() {
  testWidgets('App starts', (tester) async {
    await tester.pumpWidget(const MyApp());
    expect(find.text('SoftDev'), findsOneWidget);
  });
}
