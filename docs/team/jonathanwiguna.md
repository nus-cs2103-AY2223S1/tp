---
layout: page
title: Jonathan Wiguna's Project Portfolio Page
---

### Project: EZLead

EZLead is a **desktop app for tech leads to manage teams optimized for use via a Command Line Interface (CLI)**, and it has a GUI created with JavaFX. It is written in Java, and has about 7k LoC.

Given below are my contributions to the project.

* **New Feature**: Added in a Task class and implemented the ability to add tasks into a team (Pull requests [\#88](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/88), [\#100](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/100)).
  * What it does: the Task class models a task to be done by a team and the command allows the user to add tasks to a team.
  * Justification: This feature enables users to keep track of which team is doing which tasks.

* **Enhancements Done**: Disallow the tasks list of a team from having duplicate tasks (Pull request [\#119](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/119)).
  * What it does: it prevents a team from having two of the same task.
  * Justification: the existence of duplicate tasks may result in unwanted consequences, e.g. the user added the same task twice without realizing. After a task is marked as done and is then deleted, the user would expect the task to be removed from the team's tasks list. However, the task list would still have another copy of the task since the user added the task twice but only deleted one of them.

* **Enhancements Done**: added the ability for tasks to have an optional deadline (Pull request [\#147](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/147)).
  * What it does: allows the user to specify a deadline to a task if they want.
  * Justification: it is important for tech leads to keep track of tasks' deadlines, so they can see if any team is falling behind in deliverables.
  * Highlights: implementing an optional deadline required the use of the Optional class as the deadline can be a LocalDate or null.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=JonathanWiguna&tabRepo=AY2223S1-CS2103T-W09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Documentation**:
  * User Guide:
    * Added feature documentations for early iteration of the product (Pull requests [\#46](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/46), [\#78](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/78)).
    * Fixed hyperlink issues, made a more detailed quick start guide, and added screenshots for visual aid to make following the guide easier (Pull request [\#233](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/233)).
  * Developer Guide:
    * Added some use cases (Pull request [\#114](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/114)).
    * Added implementation of `taskadd` and `deleteteam` feature and UML diagrams to show the executions of these commands (Pull requests [\#157](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/157), [\#162](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/162)).
    * Updated the Model diagram (Pull request [#\162](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/162)).
  
* **Review/mentoring contributions**
  * Reviewed and suggested changes of other member's PRs (Pull requests [\#159](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/159), [\#181](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/181)).
  * Helped in checking other members' typos and grammatical errors in their PRs (Pull request [\#166](https://github.com/AY2223S1-CS2103T-W09-3/tp/pull/181)).