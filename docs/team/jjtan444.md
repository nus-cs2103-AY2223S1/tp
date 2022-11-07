---
layout: page
title: Jing Jie's Project Portfolio Page
---

### Project: HR Pro Max++

HR Pro Max++ is a desktop team management application for SME company to manage their team members and project details.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added `sortTask` functionality.
  * What it does: It allows user to sort tasks by deadline
  * Justification: This feature improves the product significantly because originally any new task added to the program will
  be automatically appended to the end of the list, there were no ways to reorder the items in the list. If the user wants
  to prioritize the tasks that are more urgent, he/she has to scroll through the entire list to manually compare and choose.
  Therefore, the application should provide a convenient way to save the hassle.

* **New Feature**: Added `findTask` functionality.
  * What it does: It allows user to find tasks by keywords
  * Justification: This feature further complements the `sortTask` functionality, if the user wants to get a specific task
  he/she has to scroll through the entire list to manually find the task. Therefore, the application should provide a
  convenient way to save the hassle.

* **New Model**: Created `StaffList` model.
  * What it does: It allows the application to use the model to contain multiple `Staff` by the user in a list.
  * Justification: This model is necessary for the creation of `Staff` commands such as `addStaff`, `editStaff`, `delStaff`,
  and `findStaff` commands.

* **New Model**: Created `TaskList` model which includes `Task`,`TaskDescription` and relevant `Storage` classes.
  * What it does: It allows the application to use the model to store multiple `Task` by the user in a list.
  * Justification: This model is necessary for the creation of `Task` commands such as `addTask`, `markTask`, `delTask`,
  and `findTask` commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jjtan444&breakdown=true)
<div style="page-break-after: always;"></div>
* **Enhancements to existing features**:
* Improved `findProj` command to be able to find `Project` with keywords that matches part of the `Project` name.
* Improved `findTask` command to be able to find `Task` with keywords that matches part of the `TaskDescription`.
* Improved `findStaff` command to be able to find `Staff` with multiple keywords.
* Checked for code quality.

* **Project management**:
  * Project demonstration for submission.
  * Increased overall test quality [\#124](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/124), 
  [\#230](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/230), [\#234](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/234).

* **Documentation**:
  * User Guide:
    * Added documentation for the `findTask`, `sortTask`, `filterTask`, `markTask` and `unmarkTask` commands [\#134](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/134).
    * Update User Guide for `findProj`, `findTask`, `findStaff` commands [\#191](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/191).
    * Language and formatting edits for User Guide [\#188](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/188), [\#191](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/191),
      [\#201](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/201).
    
  * Developer Guide:
    * Update use case for `Project` [\#83](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/83).
    * Added use cases for `Staff` [\#83](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/83), [\#218](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/218).
    * Added `findTask` section and UML Diagram for `findTask` command [\#218](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/218).
    * Added user stories for `Project`, `Task` and `Staff` commands [\#218](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/218).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#140](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/140),
  [\#190](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/190),
  [\#193](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/193).
  * Reported bugs and suggestions for other teams: [\#1](https://github.com/jjtan444/ped/issues/1),
  [\#2](https://github.com/jjtan444/ped/issues/2), [\#3](https://github.com/jjtan444/ped/issues/3),
  [\#4](https://github.com/jjtan444/ped/issues/4), [\#5](https://github.com/jjtan444/ped/issues/5),
  [\#6](https://github.com/jjtan444/ped/issues/6), [\#7](https://github.com/jjtan444/ped/issues/7),
  [\#8](https://github.com/jjtan444/ped/issues/8).

