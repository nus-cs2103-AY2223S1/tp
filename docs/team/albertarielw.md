---
layout: page
title: Project Portfolio Page (@albertarielw)
---

### Project: HackAssist

HackAssist - HackAssist is a desktop app for helping hackathon team leaders manage project tasks more easily.

It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HackAssist can get your task management done faster than traditional GUI apps.

Given below are my contributions to the project.

Features:

* **AddTaskCommand**: Created a command to allow user to add a task to task list (Pull requests [\#52](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/52)).
  * What it does: Based on parsed input, this command will add a task which will then be displayed to the task list.
  * Justification: This command is essential for basic usage of the application so user can add task they need to do.

* **DeleteTaskCommand**: Created a command to allow user to delete a task from the task list (Pull requests [\#52](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/52)).
  * What it does: Based on parsed input, this command will delete a task from the displayed to the task list.
  * Justification: This command is essential for basic usage of the application so user can delete a task from the task list (in case they wrongly added a task or remove tasks which are no longer relevant.)

* **EditTaskCommand**: Created a command to allow user to edit task in the task list (Pull requests [\#52](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/52)).
  * What it does: Based on parsed input, this command will edit a task in the displayed to the task list.
  * Justification: This command is essential so that user can change a task details directly (instead of having to delete the task and add a new one with updated details).

* **Storage**: Allows storage capability for task list by facilitating conversions from and to JsonAdaptedTask (Pull requests [\#70](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/70)).
  * What it does: Read saved task data and save task data automatically upon execution of each command.
  * Justification: This feature is essential to allow for persistent storage for task (over different sessions of HackAssist).
  * Highlights: Wrote exceptions for storage functionality for task to account for invalid saved data which may occur when user edits saved data directly.

Tests:

* **Storage Test**: Writes test for task list storage (Pull requests [\#151](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/151))
  * What it does: Performs automated testing to check for any error in the code.
  * Justification: This helps to eliminate bugs for task storage.
  * Highlights: Increased Codecov by ~7%.

Bug Fixes:

* **Person Email Address as Foreign Key in Task**: Modifies task to have a person email address as foreign key rather than a person object (Pull requests [\#69](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/69)).
  * Justification: By using foreign key rather than keeping a copy of person object in task, changes in a person object will be reflected on the associated task.

* **Wrong Feedback Message for Invalid Task Index**: Modifies parser to throw an exception with invalid index as feedback message instead of invalid command (Pull requests [\#160](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/160)).
  * Justification: To allow user to know what is wrong with their command.

* **Throw Exception when Edit Task has no field**: Modifies EditTask parser to throw an exception when it is not followed by any field (Pull requests [\#160](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/160)).
  * Justification: To make EditTask and Edit (person) behaviour the same for standardization.

* **Adds Sample Data for Task**: Adds sample data for task to HackAssist (Pull requests [\#183](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/183)).
  * Justification: To allow user to experiment with sample data.

Others:

* **Project management**:
  * Managed releases `v1.2` - `v1.3` (2 releases) on GitHub (Pull requests [\#80](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/80)).

* **Enhancements to existing features**:
  * Added GUI Space Grey Color Scheme (Pull requests [\#116](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/116))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=albertarielw&tabRepo=AY2223S1-CS2103T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Documentation**:
  * User Guide:
    * Wrote introduction, quickstart and app layout
    * Did cosmetic tweaks to existing documentation of features `addTask`, `editTask`, `deleteTask` (Pull requests [\#103](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/103/files))
  * Developer Guide:
    * Added implementation details of the `storage` feature. https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/79
    * Wrote target user profile, value proposition and basic user stories. (Pull requests [\#17](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/17), [\#19](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/19))
