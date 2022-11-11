---
layout: page
title: Shawn Kok's Project Portfolio Page
---

### Project: Coydir

**Coydir is a desktop application used by Human Resource Executives to streamline processes in managing their staffing.**
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
Given below are my contributions to the project.
### Summary of Contributions

---

#### Code Contributed

>[RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Kok-je&tabRepo=AY2223S1-CS2103T-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Enhancements Implemented
* **New Features**
  * Implemented `Rating` class
    * Created the `Rating` class, one of the key components we are tracking with Coydir.
    * Use Case: Building blocks to track Employee Performance over time.
    * Justification: Tracking performance (talent development) is one important component of HRM.
  * Implemented `rate` feature
    * Created a `RateCommand` class to handle the logic (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/logic/commands/RateCommand.java))
    and `RateCommandParser` class to handle the parsing of the user input (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/logic/parser/RateCommandParser.java)).
    * Use Case: User can add ratings for employees.
    * Justification: Rate the employee performance (1-5 integer rating scale).
    * Highlights: Multiple considerations such as ensuring that ratings are always sorted by the local date format (user modifies the JSON database).
  * Implemented `Performance history graph` feature
    * Created a `performanceHistory` Arraylist within the `Person` class to store all the past ratings of a particular employee (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/model/person/Person.java))
    * Created a `Performance history graph` line chart within the `Personinfo` class to chart the performance rating values of employees against time (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/ui/PersonInfo.java))
    * Use Case: User can view the performance history of employees.
    * Justification: Observe the overall trend of employee performance and make talent development decisions.
    * Highlights: Display individual rating values beside each node, prevent multiple ratings per day, sort ratings by date.
* **Existing Features**
  * Made improvements to the `Add` class - variable arguments for non-compulsory fields.
  * Fixed multiple bugs (Refer to #84, #200, #227, #242).

#### Contributions to the UG
* Added documentation for following parts:
  * Product Overview and Description
  * `add` command
  * `rate` command
  * Employee Performance History section
  * Overall aesthetics of UG 

#### Contributions to the DG
* Added implementation details for:
  * `add` function
  * `rate` function
  * Wrote Use Cases

#### Contributions to the team-based tasks
* Helped to create and distribute issues.
* Contributed to manual testing of application to find bugs.
* Influenced the overall aesthetic of the project through mockups and design direction
* Was in-charge of testing and Integration

#### Reviewing Contributions
* Pull Requests reviewed:
  [#92](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/92),
  [#113](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/113),
  [#116](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/116),
  [#144](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/144),
  [#150](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/150),
  [#224](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/224),
  [#225](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/225),
  [#241](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/241),
  [#243](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/243),
  [#252](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/252),
  [#253](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/253),
  [#255](https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/255)

