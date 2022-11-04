---
layout: page
title: Zan Hao's Project Portfolio Page
---

### Project: HackAssist

HackAssist is a desktop address book and task management application used for managing tasks and team formations in Hackathons. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Created models for task and task list, and integrated them into the application.
  * What it does: allows tasks and task commands to be created and implemented respectively.
  * Justification: This feature provides a baseline foundation for the team to build on for future task commands.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of all possible class designs, without breaking the usability of existing features related to person.
  * Credits: Inspiration was taken from how a person is modelled in the application.
  * Pull requests: [\#43](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/43), [\#44](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/44), [#54](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/54)

* **New Feature**: Added the ability to filter tasks by deadline.
  * What it does: allows the user to filter the task list to only display tasks that have deadlines specific to and before the input date.
  * Justification: This feature improves the product significantly because a user can now modify the task list and quickly identify specific tasks based on input deadline, without having to search through the entire task list.
  * Credits: Inspiration was taken from the 'find' feature.
  * Pull request: [\#84](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/84)

* **New Feature**: Added the ability to find tasks using keywords.
  * What it does: allows the user to find and list all tasks that have names or descriptions that contain any of the input keywords. Keyword matching is case-insensitive.
  * Justification: This feature improves the product significantly because a user can now modify the task list and quickly identify specific tasks based on input keywords, without having to search through the entire task list.
  * Credits: Inspiration was taken from the 'find' feature.
  * Pull request: [\#114](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/114)

* **Code contributed**: [RepoSense link]()

* **Enhancements to existing features**:
  * Synced the 'filterByCategory' and 'filterByDeadline' features into a single 'filter' command (Pull request [\#98](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/98))
  * Synced the 'sortByPriority' and 'sortByDeadline' features into a single 'sort' command (Pull request [\#112](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/112))

* **Documentation**:
  * User Guide:
    * Added documentation for the 'listTasks' feature (Pull request [\#108](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/108))
  * Developer Guide:
    * Added non-function requirements (Pull request [\#21](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/21))
    * Added implementation details of the 'filterByDeadline' feature (Pull request [\#108](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/108))

* **Community**:
  * PRs reviewed: [\#42](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/42), [\#88](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/88), [\#184](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/183)
  * Reported bugs and suggestions for other teams in the class (Total issues: [14](https://github.com/paotheroo/ped/issues))
