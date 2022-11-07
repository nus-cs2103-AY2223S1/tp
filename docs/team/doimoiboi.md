---
layout: page
title: Asher's Project Portfolio Page
---

### Project: NotionUS

NotionUS is a desktop address book application used for teaching Software Engineering principles. The user interacts 
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=12-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=doimoiboi&tabRepo=AY2223S1-CS2103T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**:
  * Added the `mark` and `unmark` command
    * What it does: allows the user to mark a task as done or not done.
    * Highlights:
      * This required an in-depth analysis of the system in order to implement it in a way that would not interfere with existing requirements, i.e. keeping `Task` objects immutable.
  * Added the `tag` command
    * What it does: allows the user to add on tags to existing tasks. Multiple tags can be added at one go.
    * Highlights:
      * Before this command, changing the tags of a task required the user to edit the entire task, including re-adding tags that were already present.
      * This required an in-depth analysis of the system and consideration of design alternatives. The implementation too was challenging when done in a way so as not to interfere with the functionality of existing commands.
      * Writing the test cases for this command also proved to be non-trivial as many of the test utilities had to be edited to accomodate the new command.

* **Contributions to the UG**:
  * Refined the Introduction
  * Added the Quick Start section
  * Added documentation for the features `mark` and `unmark`
  * Added documentation for the features `tag`
  * Standardised command formats throughout the User Guide
  * Contributed to the Command Summary

* **Contributions to the DG**:
  * Added the Introduction
  * Added implementation details of the `mark` and `unmark` feature 
    * Created UML sequence diagram to detail the mechanism of the feature
  * Updated use cases
    * Added the UML use case diagram to summarise the use cases and their possible extension.

* **Reviewing/Mentoring**:
  * List of all PR reviews for team members can be viewed [here](https://github.com/AY2223S1-CS2103T-F12-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Adoimoiboi+)
    * Includes reviewing the [initial morph PR](https://github.com/AY2223S1-CS2103T-F12-3/tp/pull/37) which had the most changes and set the foundation of our project
  * List of issues raised while bug-checking other teams can be viewed [here](https://github.com/doimoiboi/ped/issues)
