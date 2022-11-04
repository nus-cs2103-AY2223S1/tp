---
layout: page
title: Shawn Kok's Project Portfolio Page
---

### Project: Coydir

**Coydir is a desktop application used by Human Resource Executives to streamline processes in managing their staffing.**
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

## Contributions
---
>[RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Kok-je&tabRepo=AY2223S1-CS2103T-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Enhancements Implemented
* **New Features**
  * Implemented `Rating` class
    * Created the `Rating` class, one of the key components we are tracking in our application.
    * Use Case: Building blocks to track Employee Performance over time in the form of rating values.
    * Justification: Tracking performance for talent development is one of the more important components of HRM.
    * Highlights: Was not too challenging other than the part about ensuring the format for values and timestamps, and writing into `JSON Adapted Person`.

  * Implemented `rate` feature
    * Created a `RateCommand` class to handle the logic (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/logic/commands/RateCommand.java)) 
    and `RateCommandParser` class to handle the parsing of the user input (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/logic/parser/RateCommandParser.java)).
    * Use Case: User can add ratings for employees.
    * Justification: With this command, users of Coydir will be able to rate the performance of an employee based on a 1-5 integer rating scale: 

        [5: Outstanding | 4: Exceeds Expectations | 3: Satisfactory | 2: Needs Improvement | 1: Unsatisfactory]
    * Highlights: This command was slightly tricky to implement, as there were many cases to consider, such as ensuring that ratings are always sorted by the local date format (if the user modifies the JSON database).

  * Implemented `Performance history graph` feature
    * Created a `performanceHistory` Arraylist within the `Person` class to store all the past ratings of a particular employee (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/model/person/Person.java))
    * Created a `Performance history graph` line chart within the `Personinfo` class to chart the performance rating values of employees against time (Refer to code [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/blob/master/src/main/java/coydir/ui/PersonInfo.java))
    * Use Case: User can view the performance history of employees.
    * Justification: With this command, users of Coydir will be able to observe the overall trend of a particular employee's performance, and make talent development decisions based on that insight.
    * Highlights: Was slightly challenging to present the JavaFX line chart data in a presentable format, displaying individual rating values beside each node, implementing logic to prevent multiple ratings in a day (prevent weird straight vertical line graph), and ensuring that the rating values are sorted by ascending order in the graph.

* **Existing Features**
  * Made improvements to the `Add` class by implementing variable arguments (`varags`) for particular non-compulsory fields.
  * Refactored `Add` command.
  * Fixed multiple bugs (Refer to #84, #200, #227, #242).

#### Contributions to the UG 
* Added documentation for following parts:
  * Introduction catchy hook
  * Product Overview and Description
  * `add` command
  * `rate` command
  * Employee Performance History section
  * Checking if an employee is on leave section
  * Overall aesthetics of UG page

#### Contributions to the DG
* Edited implementation details for:
  * `add`
* Added implementation details for:
  * `rate`
  * [To Be Updated]

#### Contributions to the team-based tasks
* Helped to create and distribute issues. 
* Contributed to manual testing of application to find bugs.
* Influenced the overall aesthetic of the project through mockups and design direction
* Was in-charge of testing and Integration

#### Reviewing Contributions
* Pull Requests reviewed:
  * [#92] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/92)
  * [#113] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/113)
  * [#116] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/116)
  * [#144] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/144)
  * [#150] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/150)
  * [#224] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/224)
  * [#225] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/225)
  * [#241] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/241)
  * [#243] (https://github.com/AY2223S1-CS2103T-T15-1/tp/pull/243)