---
layout: page
title: Richa Motwani's Project Portfolio Page
---

EZLead is a **desktop app for tech leads to manage teams optimized for use via a Command Line Interface (CLI),
with GUI created with JavaFX**. It is written in Java

Given below are my contributions to the project:

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=richavm14&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added AssignMember command to add a person to a team. (pull requests: [\#106](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/106) & [\#122](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/122) & [\#182](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/182))
  * What it does: Allows user to add a person as a member in a team by specifying person and team index.
  * Justification: This feature allows tech leads to add member into one or more teams and help team management. Even if a member is added mid-project, they can still be added to team.

* **New Feature**: Added UnAssignMember command to remove a person from a team. (pull requests: [\#112](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/112) & [\#123](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/123) & [\#182](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/182))
    * What it does: Allows user to remove a person as a member from a team by specifying person index (from the userlist) and team index.
    * Justification: This feature allows tech leads to remove member from one or more teams and help team management. If a member leaves the company or changes department then they could be removed from the team whenever needed.

* **New Feature**: Added EditTeam command to edit team name. (pull request: [\#152](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/152))
    * What it does: Allows user to change the name of a pre-existing team by specifying its index.
    * Justification: This feature allows tech leads to change the name of a team in case they added the wrong name or for any other reason.

* **Enhancements to existing features**:
  * Add functionality to unassign member from a team on deleting that team (pull request: [\#130](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/130))
  * Wrote tests for create team command (pull requests: [\#250](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/250))

* **Documentation**:
    * User Guide:
        * Updated command formats and descriptions in user guide. (pull requests: [\#243](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/243) & [\#181](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/181))
        * Added link to GUI image, quickstart description and index to user guide. (pull requests: [\#181](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/181) & [\#187](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/187))
    * Developer Guide:
        * Add use case (pull request: [\#89](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/89))
        * Update user stories in developer guide. (pull request: [\#80](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/80))
        * Added implementation details of the `EditTeam`, `AssignMember` and `UnAssignMember` commands to developer guide. (pull request: [\#165](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/165))
        * Added UML sequence diagrams for `EditTeam`, `AssignMember` and `UnAssignMember` commands to developer guide. (pull request: [\#177](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/177))

* **Project management**:
    * Managed release `v1.3.2` on GitHub
