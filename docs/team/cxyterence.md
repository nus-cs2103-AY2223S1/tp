---
layout: page
title: Terence Chong's Project Portfolio Page
 ---

### Project: Teacher’s Address Book (TAB)

Teacher’s Address Book (TAB) is a **desktop app made for teachers, teaching assistants(TA) and professors for managing
contacts of each other, as well as their students, optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, TAB can get your contact management tasks
done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Implemented the `Position` class
  * What it does: Abstract class to facilitate implementation of `Student`, `TeachingAssistant` and `Professor` classes,
    which serve to encapsulate relevant details of students, teaching assistants and professors respectively.
  * Highlights: The `Student`, `TeachingAssistant` and `Professor` classes extend the abstract `Position` class.
    Being the first subclass of `Position` to be implemented, the design of the `TeachingAssistant` class provided a
    guideline for the implementation of the other two subclasses (i.e. `Student` class and `Professor` class).
* **New Feature**: Added `ShowCommand` class
  * What it does: Allows the user to display additional details of a specific `Person`. For instance, `grades` and
    `attendance` will be displayed for `Student`, `availability` will be displayed for `TeachingAssistant`, and `roles`
    will be displayed for `Professor`.
  * Highlights: Displays the aforementioned details in a secondary panel beside the primary panel that displays the list
    of `Persons`, thus keeping the primary panel compact.
* **New Feature**: Changed the UI of TAB
  * Highlights: The primary panel now displays key information like name, position and tag. On the other hand, the theme
    is now more aesthetically pleasing and simple to the eye.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cxyterence&breakdown=true)

* **Project management**:
  * Owner/Manager of the team's repository

* **Enhancements to existing features**:
  * Refined the appearance of the graph for `Student` to be more aesthetically pleasing:
    [#91](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/91)

* **Documentation**:
  * Developer Guide:
    * Added and modified UML class and sequence diagrams for existing classes:
      [#163](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/163)
    * Added documentation for implementation of:
      1. `AddCommand`
      2. `EditCommand`
      3. `AttendanceCommand`
      4. `AvailabilityCommand`
      5. `AddAssignmentCommand`
      6. `RolesCommand`
      7. `ShowCommand`
      [#163](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/163)
    * Added documentation for use cases from UC01-UC11:
      [#162](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/162)
* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [#148](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/148)
  * Reported bugs and suggestions for other teams during [ped](https://github.com/cxyterence/ped) (example:
    [#1](https://github.com/cxyterence/ped/issues/1))
