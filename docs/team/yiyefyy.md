---
layout: page
title: Fang Yiye's Project Portfolio Page
---
NotionUS is a desktop task tracking application used for tasks, and specially tailored toward university students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

### Project: NotionUS

Given below are my contributions to the project.

* **New Feature**: Added the ability to list tasks by modules and list all unmarked tasks ( `ls -a`, `ls --module MODULE`, `ls -u`).
    * What it does: allows the user all unmarked tasks and all tasks under a specific module.
    * Justification: This feature improves the product significantly because a user might want to view all tasks under a specific module before a tutorial. 
    * Highlights: The features was challenging as many new list commands were created and also new predicates are needed for the new list commands. This requires analysis of the design of the list command and how the predicates work. 
    * Credits: The design of the code was modelled after existing AB3 list command codes, due to the existing `Parser` and `Command` associations.

* **New Feature**: Added the `archive` feature.
    * What it does: allows the user to archive the tasks when then have completed it and want it removed from the list of tasks showed in the UI.
    * Justification: This features allows users to store the done tasks in another file which may provide them a sense of accomplishment when looking at the list of archived tasks. 
    * Highlights: This feature creates a new `ArchivedTaskList` which is stored in a separate JSON file. This was challenging as it requires extra effort to edit the constructors of `ModelManager` and make sure iut cannot m=be modified.   
    * Credits: This was modelled after the existing AB3 storage code.

* **New Feature**: Added `showarchive` feature.
  * What it does: allows the user view archived tasks.
  * Justification: This feature improves the product significantly because a user might want to view all archived tasks to track his own academic progress.
  * Highlights: The features was challenging it requires the modification UI to show the archived task list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=12-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=yiyefyy&tabRepo=AY2223S1-CS2103T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
    * Edited the list command to allow it to take multiple type of list commands through a new parser and added tests for them. 
    
* **Contributions to the UG**:
  * Added documentation for the features `archive` and `showArchive` 
  * Added FAQ questions and answers. 
  * Added glossary section.
* **Contributions to the UG**:
  * Added implementation details of the `archive` feature.   
  * Modified class diagram for Storage.
  * Added sequence diagram for `archive`. 
  * Added 2 instructions to manual testing.
  * Added Non-Functional requirements. 

* **Contributions to team-based-tasks**:
    * Rename JSON file from "addressbook.json" to "taskList.json".
    * Added appendix for effort in the developer guide.
* **Community**:
    * PRs reviewed: [**#6**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/6),
  [**#24**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/24),
  [**#26**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/26),
  [**#29**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/29),
  [**#37**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/37),
  [**#44**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/44),
  [**#47**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/47),
  [**#51**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/51),
      [**#60**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/60),
      [**#61**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/61),
      [**#64**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/64),
      [**#78**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/78),
      [**#89**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/89),
      [**#101**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/101),
      [**#106**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/106),
      [**#218**](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/218)
    

