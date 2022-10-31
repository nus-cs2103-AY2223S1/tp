---
layout: page
title: See Toh Jin Wei's Project Portfolio Page
---

### Project: TaskBook

TaskBook is a desktop address book and task assignment application for NUS students to efficiently manage their contacts and tasks.

Given below are my contributions to the project.

* **New Feature**: Added feature of undo/redo commands ([#159](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/159)).
    * What it does: `undo` allows the user to undo the action of previous commands, `redo` allows the user to redo a previously undone command.
    * Justification: This feature improves the product significantly because it allows the user to not have to check commands thoroughly before execution as the user knows that any mistakes made can be easily reversed.
    * Highlights: The feature is defensively written to limit the depth of history saved to prevent any potential memory issues. Furthermore, commits that do not cause a change in state are not added to the history, to prevent clutter.
    * Credits: Some implementation ideas were taken from the proposed implementation from AB-3's Developer Guide.

* **New Feature**: Added feature of command history navigation ([#154](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/154)).
    * What it does: When the input field is in focus, the user can press the `UP` and `DOWN` arrow keys to navigate their command history, similar to most terminals.
    * Justification: This feature supports a few common use cases which can greatly improve the efficiency of the application, for example: repeating a command multiple times, viewing command history, fixing typos in previous commands.

* **New Feature**: Completely reworked original help command ([#170](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/170)).
    * What it does: Informs the user of the commands available **and** the usage of each command.

* **New Feature**: Added feature of task edit command ([#140](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/140)).
    * What it does: Allows the user to edit tasks, which include todos, deadlines and events.

* **New Utility**: Added utility for date parsing ([#136](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/136), [#232](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/232)).

* **Enhancements to existing features**:
    * Updated parser to work with various categories, including contact, task and category-less ([#92](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/92)).
    * Updated original `edit` command to `contact edit` ([#140](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/140)).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=seetohjinwei&breakdown=true)

* **Team tasks**:
    * Set up GitHub organization and repository.
    * Set up CodeCov coverage report.
    * Pull Requests reviewed (with non-trivial comments): [#138](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/138), [#161](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/161), [#169](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/169), [#175](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/175).

* **Project management**:
    * Managed releases [v1.2](https://github.com/AY2223S1-CS2103T-T13-4/tp/releases/tag/v1.2), [v1.2.1](https://github.com/AY2223S1-CS2103T-T13-4/tp/releases/tag/v1.2.1) (2 releases) on GitHub.

* **Documentation**:
    * User Guide:
        * Updated quick start.
        * Added instructions for `help`, `contact edit`, `task edit`, `undo`, `redo` commands and command history navigation.
    * Developer Guide:
        * Added implementation details and design considerations for the command history navigation feature.
        * Updated implementation details and design considerations for the `undo` and `redo` features.
        * Added Command History Activity Diagram and Next Command History Sequence Diagram.
        * Added manual testing instructions for testing command history navigation.
        * Did minor updates to 10 diagrams initially from AB-3.
