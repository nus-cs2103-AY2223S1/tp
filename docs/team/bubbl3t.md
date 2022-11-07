---
layout: page
title: Lin Zechen's Project Portfolio Page
---

### Project: TA-Assist

TA-Assist is a desktop application targeted at NUS Teaching Assistants (TA). It helps them to keep track of their students' grades, attendance, and work submission status of relevant modules.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**:
  * Integrated the `ModuleClass` class to the project.
  * Implemented `addc`, `deletec`, `listc`, `assign`, `unassign` commands.
  * Implemented `deletes` command.
  * Implemented `export` command.
* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=Bubbl3T&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Project management**:
  * Created and maintained project milestones.
  * Authored 26 issues to keep track of current issues and progress.
  * Reviewed 65 pull requests to ensure quality and consistency across the project.
* **Enhancements to existing features**:
  * Changed `Tag` to `ModuleClass`.
  * Make `Session` and `ModuleClass` case-insensitive.
  * Integrated `Comparator` to classes so that they can be sorted correctly.
  * Integrated the `StorageAction` and `UiAction` classes to the project and modified logic flow in `LogicManager` and `MainWindow`.
  * Overloaded the `CommandResult` class to contain `UiAction` and `StorageAction` if necessary.
* **Documentation**:
  * Added implementation of add class, delete class, and assigning students to classes in the Developer Guide.
  * Added documentation in User Guide on class related commands.
  * Added documentation in User Guide for `clear` and `export ` command.
  * Added implementation of `ExportCommand` in the Developer Guide.

