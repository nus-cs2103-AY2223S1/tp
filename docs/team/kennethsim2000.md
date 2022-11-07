---
layout: page
title: Kenneth's Project Portfolio Page(@Kennethsim2000)
---

### Project: HackAssist

HackAssist is a desktop address book and task management application used to better manage tasks allocation in Hackathons. HackAssist provides allocation, sorting and filtering of tasks features to allow users to better manage the task allocation and progress tracking in a hackathon team.

The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is optimized for use via a Command Line Interface(CLI) while still having the benefits of a Graphical User Interface(GUI). If you can type fast, HackAssist can get your task management done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the Description, Name, Priority and PriorityEnum attributes of a Task (Pull requests [\#40](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/40)).
  * What it does: Creates the basic format of how a task is created.
  * Justification: A task can be represented by its taskname, a quick description of what the taks is about, and the priority of the task(HIGH,MEDIUM,LOW).

* **New Feature**: Added the ability to filter a task by task category. (Pull requests [\#88](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/88)).
  * What it does: Allows the user to only display tasks of a particular task category.
  * Justification: This feature improves the product significantly because a user can choose to display only tasks related to frontend, allowing the user to better focus on the task.
  
* **New Feature**: Added the tabpane and allowed tasklist to be displayed on the GUI. (Pull requests [\#62](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/62)).
  * What it does: Allows the user to toggle between both the tasklist and contactlist.
  * Justification: This feature improves the product significantly because a user can have access to both the contactlist as well as the tasklist.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=Kennethsim2000&tabRepo=AY2223S1-CS2103T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Successfully integrated code from other team members by reviewing pull requests of other team members(Pull requests [\#55](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/55), [\#63](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/63), [\#72](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/72), [\#75](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/75), [\#84](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/84), [\#98](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/98)).

* **Enhancements to existing features**:


Tests:

* **Commands Test**: Writes test for FilterTaskCommand, FindTaskCommand, ListTaskCommand (Pull requests [\#159](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/159))
  * What it does: Performs automated testing to check if the commands work as expected.
  * Justification: This helps to eliminate bugs for task storage.
  * Highlights: Increased code coverage by 6%.

* **Sort Test**: Writes test for SortByCategory, SortByDeadline, SortByPriority, SortByStatus (Pull requests [\#159](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/159))
  * What it does: Performs automated testing to check if the sorting predicate is accurate.
  * Justification: This helps to eliminate bugs for commands that uses this predicates.
  * Highlights: Increased code coverage by 6%.

* **Attributes Test**: Writes test for Priority, TaskCategory, TaskDate, TaskDeadline,Task (Pull requests [\#177](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/177))
  * What it does: Performs automated testing to check if the attributes are valid.
  * Justification: This helps to ensure that invalid inputs for these attributes are handled appropriately, and that there will not be a case where a valid input is treated as an invalid input.
  * Highlights: Increased code coverage by 6%.
 

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Add Task`, `Delete Task`, `View Tasks`, `Glossary`, `FAQ`, `filter`, `Edit Task`[\#13](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/13)), [\#106](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/106)), [\#178](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/178))
    
  * Developer Guide:
    * Added implementation details of the `filter`, `addTask`, `deleteTask` feature [\#110](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/110)), [\#196](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/196))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#55](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/55), [\#63](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/63), [\#72](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/72), [\#75](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/75), [\#84](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/84), [\#98](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/98)).

* **Tools**:


* _{to be updated soon}_

