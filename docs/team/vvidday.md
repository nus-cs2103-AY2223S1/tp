---
layout: page
title: David Gareth Ong's Project Portfolio Page
---

## Project: ModtRekt

ModtRekt is a desktop app which helps undergraduate students plan their modules over their course
of study, and manage their tasks and deadlines for each of them.

ModtRekt is optimized for use via a CLI while still having the benefits of a GUI.

### Summary of Contributions

* **New Feature**: Added and designed the `cd` command
  * What it does: Allows user to navigate through the app by setting their current module to one of the previously added modules.
  * Justification: This feature allows the user to filter tasks by the selected module, as well as enabling shorthand commands such as `add task` without having to specify the module code, significantly speeding up the adding process.
  * Highlights: This feature follows the familiar unix `cd` command in both syntax and functionality.
* **Major Enhancement**: Added the ability to add/remove deadlines as an extension of tasks.
  * What it does: Allows user to add/remove deadlines, which are tasks that have a date associated with it.
  * Justification: Our target user profile will want to keep track of the due dates for their tasks.
  * Highlights: This feature utilizes polymorphism in its implementation, as it extends the `Task` class.
* **Minor Enhancement**: Migrated the parsing of several commands to utilize JCommander.
  * Migrated the `cd` and `remove` commands.
  * Credits: [Jonathan](https://github.com/jontmy) helped with setting up JCommander in the main parser.
* **Minor Enhancement**: Added several JUnit tests.
  * Tested the execution of `AddTaskCommand`, `CdModuleCommand`
  * Tested several other aspects such as `DeadlineConverter`
* **Minor Enhancement**: Fixed several bugs as discovered by peers in the PED.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=vvidday&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Other contributions**: Project presentation.
  * Part of the team that did the product pitch.
  * Did several parts of User Guide as shown in [here](UserGuide.md)
  * Did several parts of Developer Guide as shown in [here](DeveloperGuide.md)
