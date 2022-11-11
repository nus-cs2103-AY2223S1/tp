---
layout: page
title: Chao Yung's Project Portfolio Page
---

### Project: Coydir

**Coydir is a desktop application used by Human Resource Executives to streamline processes in managing their staffing.** The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

## Summary of Contributions

---

#### Code Contributed

[RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=chao890&tabRepo=AY2223S1-CS2103T-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Enhancements Implemented
* **New Features**
  * Implemented `add-leave` feature
    * Created a `AddLeaveCommand` class to handle the logic (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/logic/commands/AddLeaveCommand.java))
    and `AddLeaveCommandParser` class to handle the parsing of the user input (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/logic/parser/AddLeaveCommandParser.java)).
    * Use Case: User can add leave periods for employees.
    * Justification: With this command, users of Coydir will be able to keep track of employee leave periods and their availability at all times.
    * Highlights: This command was slightly tricky to implement, as there were many cases to consider, such as overlapping leaves, or insufficient leaves.
  * Implemented `delete-leave` feature
    * Created a `DeleteLeaveCommand` class to handle the logic (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/logic/commands/DeleteLeaveCommand.java))
    and `DeleteLeaveCommandParser` class to handle the parsing of the user input (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/logic/parser/DeleteLeaveCommandParser.java)).
    * Use Case: User can delete leave periods for employees.
    * Justification: With this command, users of Coydir will be able to make changes easily when employees change their leave periods.
    * Highlights: Smoother to implement, though just like `add-leave`, there were many dependencies and many files to be edited.

* **Existing Features**
  * Made improvements to the `Leave` class by storing `Leave` objects in a priority queue, and add methods to check for overlapping or equal leaves.
  * Refactored `Delete` command.
  * Fixed multiple bugs (Refer to #109, #130, #140, #235).

#### Contributions to the UG
* Added documentation for following parts:
  * `add-leave` command
  * `delete-leave` command
  * `delete` command
  * Quickstart
  * Controlling total leave for an employee section
  * Checking if an employee is on leave section
  * Links to table of content

#### Contributions to the DG
* Edited implementation details for:
  * `delete`
* Added implementation details for:
  * `add-leave` command
  * `delete-leave` command
* Refactored diagrams and command names 

#### Contributions to the team-based tasks
* Helped to create and distribute issues.
* Wrote test code for features that I upgraded (delete, add-leave, delete-leave).
* Contributed to manual testing of application to find bugs.
* Oversaw documentation work.

#### Reviewing Contributions
* Pull Requests reviewed:
  [#63](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/63),
  [#111](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/111),
  [#119](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/119),
  [#143](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/143),
  [#225](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/225),
  [#226](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/226)
  [#244](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/244)
