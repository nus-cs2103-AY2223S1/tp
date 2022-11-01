---
layout: page
title: Sim Choon Hong Dexter's Project Portfolio Page
---

### Project: TaskBook

TaskBook is a desktop address book and task assignment application for NUS students to efficiently manage their contacts and tasks.

Given below are my contributions to the project.

* **New Feature**: Added feature of mark and unmark tasks ([#148](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/148)).
  * What it does: `task mark` allows the user to mark a task in the TaskBook as complete and `task unmark` allows the user to unmark a previously marked task.
  * Justification: This feature is core to the product. A fundamental feature of a task management software is to allow the user to be able to organize their tasks by marking and unmarking tasks.
  * Highlights: The feature is written with an immutable field to prevent any potential regression that may arise from adding new features such as undo, redo and edit. This is opposed to implementing it with a mutable field, similar to the one implemented in our individual projects, which could cause other commands, storage or GUI to misbehave.
  * Credits: Some implementation ideas were taken from Jin Wei's task edit implementation in TaskBook.

* **New Feature**: Added feature of todo, deadline and event tasks ([#138](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/138)).
  * What it does: `task todo`, `task deadline` and `task event` allows the user to add different types of task in the TaskBook.
  * Justification: This feature is a step-up to the product. The user can use different types of task that contains different fields that best suit the situation. For example, the deadline type contains an additional date field that allows the user to track the deadline of the task.
  * Highlights: The feature is written with each task type extending from an abstract class `Task`. This improves maintainability of the code. It also allows for ease of future extension if new task types were to be added in the future.
  * Credits: Some implementation ideas were taken from the task implementation in my individual project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=dexter-sim&breakdown=true)

* **Enhancements to existing features**:
  * Fix bug regarding adding of duplicate tasks ([#160](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/160)). 
  * Abstract out description field of task into its own class ([#97](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/97)).
  * Update parse and command mechanism for contact and task add and delete ([#95](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/95)).

* **Documentation**:
  * User Guide:
    * Updated instruction for `contact add` command ([#83](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/83)).
    * Added instructions for `task mark` and `task unmark` commands ([#148](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/148)).
    * Added instructions for `task todo`, `task deadline` and `task event` commands ([#138](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/138)).
  * Developer Guide:
    * Added implementation details and design considerations for the mark and unmark command feature ([#155](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/155)).
    * Added Mark Command Activity Diagram to illustrate the feature implemented ([#155](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/155)).

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
  ([#98](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/98)) 
  ([#140](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/140)) 
  ([#150](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/150)) 
  ([#174](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/174)) 
  ([#247](https://github.com/AY2223S1-CS2103T-T13-4/tp/pull/247)).
