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

* **Enhancements to existing features**:

Bug Fixes:

* **Display Email in person assigned taskcard**: The person's email is displayed after the person name, reflected in the taskcard component. (Pull requests [\#69](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/69)).
  * Justification: The contact list allows for a two contacts to have the same name but no duplicate emails. Thus, to be able to tell two contacts with the same name apart, we provide additional information such as the email.

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
    * Added documentation for the features `Add Task`, `Delete Task`, `View Tasks`, `Glossary`, `FAQ`, `filter`, `Edit Task` (Pull requests [\#13](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/13), [\#106](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/106)), [\#178](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/178))
    * Fix the following bugs: (Pull requests [\#165](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/165)).
    * Issue 136: Inaccurate documentation for listTasks command
    * Issue 130: Clear command clears the address book, not the task list.
 
  * Developer Guide:
    * Added implementation details of the `filter`, `addTask`, `deleteTask` feature (Pull requests [\#110](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/110), [\#196](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/196))
    * Added sequence and activity diagrams for addTask and deleteTask.

* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull requests [\#55](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/55), [\#63](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/63), [\#72](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/72), [\#75](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/75), [\#84](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/84), [\#98](https://github.com/AY2223S1-CS2103T-F12-2/tp/pull/98)).

* **Tools**:


* _{to be updated soon}_

