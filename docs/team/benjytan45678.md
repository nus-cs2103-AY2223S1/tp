---
layout: page
title: benjytan45678's Project Portfolio Page
---

### Project: TrackAScholar

TrackAScholar is a desktop application used for managing scholarship applications.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added `FilterCommand` and `ApplicationStatusPredicate` class under `applicant` package with Jie Hui: [#57](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/57) and [#67](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/67).
    * Justification: Allows administrative staff to manage scholarship applicants more easily by organising them based on their specific application status.

* **New Feature**: Added `PinCommand` and `UnPinCommand` class under `applicant` package with its respective parsers: [#105](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/105), [#115](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/115) and [#116](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/116)
    * What it does: Allows the user to pin scholarship applicants, which will be shown on the right-side panel.
    Moreover, the user will be able to unpin a pinned applicant, such that the applicant will be removed from the right-side panel.
    * Justification: This feature allows a user to pin certain applicants with special circumstances (such as possessing any form of disability or currently still undergoing National Service), 
    so that the user will be able to refer to these set of applicants at a later stage of time after receiving some form of clearance from relevant authorities.

* **Code contributed**: Refer to my [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=benjy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=benjytan45678&tabRepo=AY2223S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Setting up the GitHub team organisation/repository [#25](https://github.com/nus-cs2103-AY2223S1/tp/pull/25)
  * Setting up tools e.g., GitHub, Gradle and CodeCov
  * Helped to handle the creation of milestone v1.2 and creation of team issues.

* **Enhancements to existing features**: 
  * Enhanced the `remove` and `clear` commands with an alert dialog box. [#83](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/83/files) and [#90](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/90)
    * What it does: Receives user's confirmation before proceeding with the removal or clearing of data from TrackAScholar.json
    * Justification: Since the above commands are irreversible in nature, the alert dialog box serves as a deterrence against unintentional usage of the commands, thus improving the overall experience of the product for the user
    * Highlights: Still optimized for fast typists as the prompt can be answered using keyboard commands
  * Enhanced the GUI of TrackAScholar
    * Implemented `PinCard`, `PinList` and its css files to improve the overall user interface

* **Documentation**: 
  * User Guide: Added unpin command in features.
  * Developer Guide: Added the filter command implementation, filter command
    sequence diagram, filter command activity diagram and Use Case 3. [#96](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/96/files)

* **Community**: 
  * Hosted multiple discussion sessions with team members on PR issues and several bugs.
  * Reviewed several PRs, including [#97](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/97)
    and [#109](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/109).
  * Found several bugs for CS2103T-W12-3 and suggested improvements [here](https://github.com/benjytan45678/ped/issues).

