---
layout: page
title: Ng Jing Xue's Project Portfolio Page
---

### Project: TA-Assist

TA-Assist is a desktop application targeted at NUS Teaching Assistants (TA). It helps them to keep track of their students' grades, attendance, and work submission status of relevant modules.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Implemented `focus` and `unfocus` commands
  * Allows the user to execute module-class-specific commands (e.g. view all students in a specific class).

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=njxue&breakdown=true).

* **Project management**:
  * Created organisation and set up project repository.
  * Managed and created labels to organise issues and pull requests.
  * Authored 56 issues, including User Stories.
  * Reviewed 37 pull requests to ensure quality and consistency across the code base.
  * Handled miscellaneous administrative tasks including:
    * Setting up project website.
    * Enabling assertions in `build.gradle`.
    * Trial jar file release.

* **Enhancements to existing features**:
  * Extended the grading feature to grade multiple students at once.
  * Extended the `adds` command to add multiple sessions at once.
  * Changed `list` command to list only students in the focused class when in focus mode.
  * Standardised command result messages.
  * Reduced code duplication with utility methods in `CommandUtil`.
  * Set module names to be upper-cased and session names to be capitalised so that their names
    in the CLI and GUI match.

* **Documentation**:
  * Modified the pre-existing UML and sequence diagrams from AB3 to TA-Assist.
  * Added implementation details for focus mode in Developer Guide.
  * Extended the description for the Model component in Developer Guide.
  * Added sequence diagrams for `assign` and `focus` command in Developer Guide.
  * Added UI snapshots to User Guide.
  * Created custom call-outs for User Guide.
  * Added description of the focus mode and sessions in User Guide.
