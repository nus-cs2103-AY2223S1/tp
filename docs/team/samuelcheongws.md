---
layout: page
title: Samuel Cheong's Project Portfolio Page
---

### Project: TrackAScholar

TrackAScholar is a desktop application used for managing scholarship applications.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added an `ImportCommand` to quickly import data from another json file.

  * What it does: Allows a user to copy out the trackAScholar storage file (previously addressbook), change the name to trackAScholarImport file and merge it with another trackAScholar file.
  * Justification: This feature improves the product significantly because a user can add all new scholarship application together with one command without having to tediously add the them one by one.
  * Highlights: This feature also allows 2 administrators to be working 2 different computers and 2 different trackAScholar files at the same time and merge the files at a later time. This allows for increased efficiency as 2 users can work on scholarship applications at the same time.

* **Code contributed**: Refer to my [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w10-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=zoom&zA=samuelcheongws&zR=AY2223S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&zACS=134.73076923076923&zS=2022-09-16&zFS=w10-3&zU=2022-11-04&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Enhancements to existing features**: 
* Added `ApplicationStatusTest` and `SortCommandTest` and other Import command tests to increase code coverage and ensure that programs work as expected.

* **Documentation**: `to be added soon`
  * User Guide: Added documentation for the features import and edit
  * Developer Guide: Added documentation, sequence diagrams, and activity diagrams for the features import and edit

* **Community**:
* * Reviewed several PRs, including https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/92.
* Found several bugs for CS2103T-W12-3 and suggested improvements.
