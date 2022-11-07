---
layout: page
title: Asher's Project Portfolio Page
---

### Project: NotionUS

NotionUS is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=12-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=doimoiboi&tabRepo=AY2223S1-CS2103T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Feature**: Added the ability to mark and unmark tasks.
  * What it does: allows the user to mark a task as done. Tasks marked as done can be marked as not done.
  * Justification: This feature is necessary for our application as it helps to keep track of what tasks are done and not done.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of the implementation of the system in order to prevent unintended effects.
  
* **New Feature**: Added an individual command to add additional tags.
  * What it does: allows the user to add on tags to existing tasks. Multiple tags can be added at one go.
  * Justification: This feature improves the product significantly because the user can add on tags to tasks without having to edit the entire task.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of the system and serious consideration of design alternatives. The implementation too was challenging when done in a way so as to not interfere with the functionality of existing commands.
  
* **Enhancements to existing features**:
  * Wrote additional tests for new features added to increase code coverage.

* **Documentation**:
  * User Guide:
    * Refined "Introduction" section
    * Created "Quick Start" section
    * Added documentation for the features `mark` and `unmark`
    * Added documentation for the features `tag`
    * Standardised command formats throughout the User Guide
    * Updated existing "Command Summary"
  * Developer Guide:
    * Created "Introduction" section
    * Added implementation details of the `mark` and `unmark` feature including a sequence diagram
    * Updated use cases for v1.4 including a use case diagram

* **Reviewing/Mentoring**:
  * PRs reviewed (with non-trivial review comments): [\#30](), [\#37](), [\#49]()
