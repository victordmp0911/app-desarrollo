class SDLCPlan {
  final String requirement;
  final List<Phase> phases;
  final List<Risk> risks;
  final List<Milestone> milestones;
  final List<Role> roles;

  SDLCPlan({
    required this.requirement,
    required this.phases,
    required this.risks,
    required this.milestones,
    required this.roles,
  });
}

class Phase {
  final String name;
  final String description;

  Phase({required this.name, required this.description});
}

class Risk {
  final String title;
  final String impact;

  Risk({required this.title, required this.impact});
}

class Milestone {
  final String title;
  final DateTime date;

  Milestone({required this.title, required this.date});
}

class Role {
  final String name;
  final String responsibility;

  Role({required this.name, required this.responsibility});
}
