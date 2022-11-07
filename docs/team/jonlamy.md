---
layout: page
title: Jonathan Lam's Project Portfolio Page
---

# Project: CodeConnect

## Overview
CodeConnect is a specialised task management that can help NUS CS students keep track of their tasks in any modules and
is linked to their local contacts to find students that take the same modules. This gives them an easy way to source
for help within each module.  The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.
Given below are my contributions to the project.

## Summary of Contributions

### Code Contributed
- [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jonlamy&breakdown=true)

### Enhancements Implemented
- UI Improvements
  - Create two separate tabs for contacts and tasks list
  - Tab focus will change according to previous command entered
    - additional parameter in `CommandResult` to determine which tab the command should focus upon execution
  - Create `fxml` files for `TaskListCard` and `TaskListPanel`
  - Edit `MainWindow` class to contain both a contacts list and task list
- `findc` command to search for contacts by name, module, or task
  - Create `ModuleTakenPredicate` and `CanHelpWithTaskPredicate` classes
    - allows for searching contacts by module and task, on top of regular search by name feature
  - Modify `FindContactCommandParser` class
    - to parse for user entered prefix (`n/`, `m/`, `ts/`) and create the correct predicate used to contact searching during execution
    - to handle erroneous inputs
  - Modify `FindContactCommand` class
    - to detect wrong index when executing find contact by task index
- `saveme` command as a convenient alias to `findc ts/1`
  - add switch case in CodeConnectParser for `saveme`
  - add factory method to `FindContactCommand`
- `clean` command to delete all completed tasks
  - add `deleteCompletedTasks` to the model interface and implementation

### Contributions to UG
- Add guide for the following commands' usage:
  - `findc`
  - `saveme`
  - `clean`
- Add list of prefixes to the end of UG
- UG tips, notes, and warnings about:
  - Deadline Formats
  - Flexible Module Format
  - Field Prefixes
  - `clean` VS `clear` command distinction
  - Tag and Module prefix usage
  - Warning on `clear` command

### Contributions to DG
- Add implementation guide for the `find`/`findc` feature including:
  - UI screenshot
  - Activity diagram (with PlantUML)
  - Sequence diagram (with PlantUML)
  - Design considerations regarding multiple search keywords
- Overall presentation

### Contributions to Team-Based Tasks
- Release of v1.2.1
- UI changes
  - change app name to CodeConnect

### Review/mentoring Contributions
- Primary reviewer for parnikkapore's PRs
- Occasionally review other team members PRs
