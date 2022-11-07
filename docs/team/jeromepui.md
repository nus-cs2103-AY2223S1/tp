---
layout: page
title: Jerome Pui's Project Portfolio Page
---

## Project: Class-ify

[Class-ify](https://github.com/AY2223S1-CS2103T-T15-2/tp) is a **class management application** built specially for **Singapore Ministry of Education (MOE) teachers** to **monitor their student's academic progress easily**. Teachers can **generate exam statistics** for each class, and Class-ify quickly **flags out students** who require more support for contacting.

It is written in Java and the GUI is built using JavaFX.

My contributions to the project are documented below.

### Code contributed

[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jeromepui&breakdown=true)

### Enhancements implemented

- Refactor AB3 `DeleteCommand` to delete student records by the student's name or student ID in [\#91](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/91), [\#179](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/179)
  - AB3's  `DeleteCommand` deletes each item in the list by its index. By changing the command to delete each item in the list by a more unique detail such as a student's name or student ID, it reduces errors in deletion.
- Updated test cases for `DeleteCommand` in [\#91](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/91)
- Refactor AB3 `HelpCommand` to show command summary table in [\#150](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/150)
  - AB3's `HelpCommand` only shows a link to its user guide. By adding a reference to a command summary table in the help window, it becomes more useful for the user.

<div style="page-break-after: always;"></div>

### Contributions to the User Guide

- Update documentation for `DeleteCommand` in [\#30](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/30), [\#97](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/97), [\#241](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/241), [\#256](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/256)
- Update command summary table in [\#97](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/97), [\#120](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/120), [\#148](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/148), [\#181](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/181)
- Add introduction and glossary section in [\#141](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/141)
- Update documentation for `HelpCommand` in [\#241](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/241)
- Revise structure of user guide in [\#250](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/250)

### Contributions to the Developer Guide

- Add introduction section in [\#126](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/126), [\#247](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/247)
- Add implementation details and design considerations for `DeleteCommand` in [\#157](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/157), [\#252](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/252)
- Update UML diagrams in architecture section in [\#160](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/160), [\#178](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/178)
- Update instructions for manual testing in [\#163](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/163)
- Add use cases in [\#252](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/252)

### Review/mentoring contributions

- Reviewed my team members' pull requests and provided suggestions to improve code quality
- Total pull requests reviewed: 27

### Contributions beyond the project team

- Provided useful feedback on user guides and developer guides from other teams in our tutorial group
- Reported [bugs](https://github.com/jeromepui/ped/issues) for team [Salesy](https://github.com/AY2223S1-CS2103T-W08-4/tp)
