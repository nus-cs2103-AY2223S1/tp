---
layout: page
title: Project Portfolio Page (@albertarielw)
---

### Project: HackAssist

HackAssist is a desktop application which aims to help manage tasks in software development hackathons. In hackathon projects, task management is often hectic because we are required to complete a large number of tasks in a short amount of time. However, with HackAssist, we aim to make your task management workflow easier, so you can focus on building your hackathon project :)

Given below are my contributions to the project.

Features:

* **Add Task**: Created a command to allow user to add a task to task list (Pull requests [\#52](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/52)).
  * What it does: Based on parsed input, this command will add a task which will then be displayed to the task list.
  * Justification: This command is essential for basic usage of the application so user can add task they need to do.


* **Delete Task**: Created a command to allow user to delete a task from the task list (Pull requests [\#52](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/52)).
  * What it does: Based on parsed input, this command will delete a task from the displayed to the task list.
  * Justification: This command is essential for basic usage of the application so user can delete a task from the task list (in case they wrongly added a task or remove tasks which are no longer relevant.)


* **Edit Task**: Created a command to allow user to edit task in the task list (Pull requests [\#52](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/52)).
  * What it does: Based on parsed input, this command will edit a task in the displayed to the task list.
  * Justification: This command is essential so that user can change a task details directly (instead of having to delete the task and add a new one with updated details).


* **Storage**: Allows storage capability for task list by facilitating conversions from and to JsonAdaptedTask (Pull requests [\#70](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/70)).
  * What it does: Read saved task data and save task data automatically upon execution of each command.
  * Justification: This feature is essential to allow for persistent storage for task (over different sessions of HackAssist).
  * Highlights: Wrote exceptions for storage functionality for task to account for invalid saved data which may occur when user edits saved data directly.

Feature Enhancements:
* **UI**:
  * Added GUI Space Grey Color Scheme (Pull requests [\#116](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/116))


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
  * Justification: To allow user to experiment with sample task data.

Project Management:

* **Managed Releases:**
  * `v1.2`
  * `v1.3` (Pull requests [\#80](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/80)).

Code Contribution:
* **[RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=albertarielw&tabRepo=AY2223S1-CS2103T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)**

Documentation:
* **Main Page:**
  * Wrote product main page (Pull requests [\#80](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/207)).


* **User Guide:**
  * Wrote introduction, quickstart and app layout (Pull requests [\#174](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/174)).
  * Improved existing documentation of features `addTask`, `editTask`, `deleteTask` to make it clearer and more organised (Pull requests [\#103](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/103/files), [\#166](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/166/), [\#167](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/167), [\#168](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/168)).
  * Fix confusion of the terms `Member` and `Person` (Pull requests [\#203](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/203)). 
  * Fix the following bugs: (Pull requests [\#164](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/164)).
    * Issue 117: edit should be editTask
    * Issue 148: standardized spacing
    * Issue 149: unclear instruction on how to mark task as done/ not done.


* **Developer Guide:**
  * Wrote design overview of `Model` based on AddressBook Level-3 (Pull requests [\#215](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/215)).
  * Wrote design overview of `Storage` based on AddressBook Level-3.
  * Updated `Storage` diagram (Pull requests [\#191](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/191)).
  * Wrote implementation details of persistent `Storage` for `Task` and `Person` (Pull requests [\#197](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/197), [\#79](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/79)).
  * Wrote target user profile, value proposition and basic user stories. (Pull requests [\#17](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/17), [\#19](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/19)).
