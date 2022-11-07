---
layout: page
title: Samuel Cheong's Project Portfolio Page
---

### Project: TrackAScholar

TrackAScholar is a desktop application used for managing scholarship applications.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added an `ImportCommand` to quickly import data from another json file. [#112](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/112)

  * What it does: Allows a user to copy out the trackAScholar storage file (previously addressbook), change the name to trackAScholarImport file and merge it with another trackAScholar file.
  * Justification: This feature improves the product significantly because a user can add all new scholarship application together with one command without having to tediously add them one by one.
  * Highlights: This feature also allows 2 administrators to be working on 2 different trackAScholar files at the same time and merging the files at a later time. This allows for increased efficiency as users can work simultaneously.

* **Code contributed**: Refer to my [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w10-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=zoom&zA=samuelcheongws&zR=AY2223S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&zACS=134.73076923076923&zS=2022-09-16&zFS=w10-3&zU=2022-11-04&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
  * Managed releases `v1.4` on GitHub
  * Ensure good version control, based on the repo

* **Enhancements to existing features**: 
* Added `ApplicationStatusTest`, `SortCommandTest`, `ImportCommandTest`, and `ImportCommandParserTest` to increase code coverage by 5% and ensure that programs work as expected. [#66](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/66), [#70](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/70), and [#208](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/208)

* **Documentation**: 
  * User Guide: 
    * Added documentation for the features import and edit
  * Developer Guide: 
    * Added documentation, sequence diagrams, and activity diagrams for `import` feature [#127](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/127)
    * Added documentation, sequence diagrams, and activity diagrams for `edit` feature [#108](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/108), [#180](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/180)

* **Community**:
* Reviewed several PRs, including [#92](https://github.com/AY2223S1-CS2103T-W10-3/tp/pull/92)
* Found several bugs for CS2103T-W12-3 and suggested improvements.
* Hosted multiple discussion sessions with team members on PR issues and weekly tasks.
* Reported bugs for team F-11-4 [here](https://github.com/samuelcheongws/ped/issues)
