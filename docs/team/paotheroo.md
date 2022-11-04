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
  * Pull requests: [\#43](), [\#44](), [#54]()

* **New Feature**: Added the ability to filter tasks by deadline.
  * What it does: allows the user to filter the task list to only display tasks that have deadlines specific to and before the input date.
  * Justification: This feature improves the product significantly because a user can now modify the task list and quickly identify specific tasks based on input deadline, without having to search through the entire task list.
  * Credits: Inspiration was taken from the 'find' feature.
  * Pull requests: [\#84]()

* **New Feature**: Added the ability to find tasks using keywords.
  * What it does: allows the user to find and list all tasks that have names or descriptions that contain any of the input keywords. Keyword matching is case-insensitive.
  * Justification: This feature improves the product significantly because a user can now modify the task list and quickly identify specific tasks based on input keywords, without having to search through the entire task list.
  * Credits: Inspiration was taken from the 'find' feature.
  * Pull requests: [\#114]()

* **Code contributed**: [RepoSense link]()

* **Enhancements to existing features**:
  * Synced the 'filterByCategory' and 'filterByDeadline' features into a single 'filter' command (Pull request [\#98]())
  * Synced the 'sortByPriority' and 'sortByDeadline' features into a single 'sort' command (Pull request [\#112]())

* **Documentation**:
  * User Guide:
    * Added documentation for the 'listTasks' feature [\#108]()
  * Developer Guide:
    * Added non-function requirements [\#21]()
    * Added implementation details of the 'filterByDeadline' feature [\#108]()

* **Community**:
  * PRs reviewed: [\#42](), [\#88]()
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
