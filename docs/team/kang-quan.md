---
layout: page
title: Kang Quan's Project Portfolio Page
---

### Project: Salesy

Salesy is a desktop program that helps food vendors in NUS manage details of their tasks, inventory and suppliers. 
It is optimised for CLI users so that tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: 
1. `addTaskCommand` (Pull Request [#92](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/92))
   * This command add a `Task` to Salesy. Each task contains the `TASKNAME`, `DEADLINE` and an optional `TAG`.
   * Helper methods and classes are also created such as `addTaskCommandParser` class to help us parse the user input
   before creating an `addTaskCommand`.
   * Added testcase for AddTaskCommand class and AddTaskCommandParser
     (Pull Request [#122](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/122))

2. `DeleteItemCommand`  (Pull Request [#146](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/146))
   * This command removes a `SupplyItem` from the inventory in Salesy.
   * Helper methods and classes are also created such as `DeleteItemCommandParser` class to help us parse the user input
     before creating an `DeleteItemCommand`.
   * Added testcase for DeleteItemCommand class and DeleteItemCommandParser


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kang-quan&breakdown=true)

* **Project management**:
  * Ensure project milestones are delivered on time.
  * Ensure issues created are linked and closed properly with the pull request.
  * Thoroughly test and review pull request to ensure new code added works and does not affect other features
  in the codebase.
  

* **Contribution to team-based Tasks**
  * Fix bugs found in PE-dry run
    1. Fix bug where ellipsis is shown due to long text 
    (Pull Request [#250](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/250))
    2. Fix bug where deleteTask does not work as intended
    (Pull Request [#248](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/248))
  * Contribute to think and create user stories for Salesy.
  * Discuss and sort which bugs found in PE-dry run are not feature flaws, so we can fix the bug.

* **Enhancements to existing features**:

1. Refactor code for `TAG` to be optional in addTask
(Pull Request [#143](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/143))
   * Our team realised having `TAG` for task should be optional as it is redundant to tie each task to a `TAG` 
   for classification.

2. Sort list of tasks and display them by earlier deadline first.
   (Pull Request [#156](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/156))
    * Previously, order of `Task` being displayed in the GUI is first come first serve order,
meaning `Task` added first will be displayed at the top.
    * However, our team feels that the order we display should be according to earlier deadline 
   first to make it more organised to our user.
   

* **Documentation**:
  * User Guide:
    * Update UG to show task can have multiple `TAG`
      (Pull Request [#171](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/171))
    * Fix numbering issue in UG
      (Pull Request [#171](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/171))
    * Add examples and explanation on how colors in our SupplyItem panel is determined
      (Pull Request [#171](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/171))
    * Added links at the bottom of the page back to the top.
      (Pull Request [#171](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/171))
  * Developer Guide:
    * Add AddTaskCommand implementation into Developer's guide
      (Pull Request [#144](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/144))
    * Add AddTaskActivityDiagram to Developer's guide
      (Pull Request [#144](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/144))
    * Add implementation details on how the sorted taskList works.
      (Pull Request [#170](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/170))
    * Add two SortedTaskListActivityDiagram to Developer's guide.
      (Pull Request [#170](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/170))
    
* **Review/mentoring contributions**:
  * Review pull request and comment on teammates pull request as per our forking workflow
  [reviewed pull request](https://github.com/AY2223S1-CS2103T-W08-4/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+is%3Aclosed)
