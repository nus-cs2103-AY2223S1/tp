---
layout: page
title: Joan Sim's Project Portfolio Page
---

### Project: Teacher's Pet

Teacher’s Pet is a desktop application for managing contacts of students and classes, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Teacher’s Pet can get your contact and class management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the `find class date` command.

  * What it does: Allows teacher to find all the students attending classes on a particular date.
  * Justification: A core feature to allow teacher to view upcoming schedule.
  * Highlights:
    Added a new prefix `nt-a` to allow appending of notes. This allows teacher to add on to the current notes.
  * Pull Requests: [\#168](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/168)

* **New Feature**: Added the `edit notes` command.

  * What it does: Allows teacher to add and edit teaching notes under a student.
  * Justification: A core feature to allow teacher to take note of important information.
  * Highlights:
    Added a new prefix `nt-a` to allow appending of notes. This allows teacher to add on to the current notes.
  * Pull Requests: [\#94](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/94), [\#171](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/171)
  
* **New Feature**: Added the `edit class` command.

  * What it does: Adds a new class under a student.
  * Justification:  A core feature to allow teacher to add their upcoming classes to manage their schedule.
  * Highlights:
    The implementation of this command was challenging due to the class conflict aspect.
    After detecting there is a class conflict between 2 classes, a further check is required to ensure that the 2 classes are not under the same student since this
    does not mean there is a conflict but just an update of timing. Another challenge faced was to also free up class slots when we are deleting students or clearing the data.
  * Pull Requests: [\#96](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/96), [\#109](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/109), [\#115](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/115),
  [\#127](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/127), [\#156](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/156)

* **New Feature**: Updated the `edit class` and `find class` command to support more natural use cases.

  * What it does: Allows inputs such as Mon instead of yyyy-MM-dd.
  * Justification:  A good-to-have feature to allow shorter and more flexible inputs.
  * Highlights:
    Implemented a temporal adjuster to adjust the date to the targeted date.
  * Pull Requests: [\#147](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/147), [\#184](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/184)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=sjoann&tabRepo=AY2223S1-CS2103T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Updated parser for phone to ignore white-spaces ([\#178](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/178))
  * Updated parser for add command to throw exceptions([\#144](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/144))

* **Documentation**:
  * User Guide:
    * Updated callouts. Added callouts table
    * Added glossary and sections of `edit` and `find`. Updated introduction and table of content
    * Pull Requests: [\#213](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/213),
      [\#195](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/195),
      [\#227](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/227)
  * Developer Guide:
    * Updated user stories
    * Added activity diagram and sequence diagram for `edit class`
    * Added use cases and updated sections of table of content and `find`
    * Pull Requests: [\#287](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/287),
      [\#157](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/157),
      [\#195](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/195),
      [\#213](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/213),
      [\#227](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/227)
    
* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [\#93](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/93),
    [\#194](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/194),
    [\#203](https://github.com/AY2223S1-CS2103T-T09-4/tp/pull/203)
  * Included v1.3 feature demo inside project notes document. Added descriptions and screenshots for the features:
    [\#document](https://docs.google.com/document/d/18XgQeugctKcNy1_1Fay5zRtmfvYsSSdNzT-hTWjukho/edit#bookmark=id.6807tavlcx8a)
  * Reported bugs for other teams during PE-D: [\#ped issues github](https://github.com/sjoann/ped)

