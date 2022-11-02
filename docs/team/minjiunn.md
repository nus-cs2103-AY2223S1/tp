---
layout: page
title: Chia Min Jiun's Project Portfolio Page
---

## Project: Class-ify

[Class-ify](https://github.com/AY2223S1-CS2103T-T15-2/tp) is a class management application built specially for Singapore Ministry of Education (MOE) teachers
to monitor their student's academic progress easily. Teachers can generate exam statistics for each class, and
Class-ify quickly flags out students who require more support for contacting.

It is written in Java and the GUI is build using JavaFX.

### Summary of contributions
Given below are my contributions to the project:

#### Code Contributed
* Check out this [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=minjiunn)

#### Enhancements Implemented
* [FindCommand](https://github.com/AY2223S1-CS2103T-T15-2/tp/blob/master/src/main/java/seedu/classify/logic/commands/FindCommand.java)
  * Adapted `FindCommand` to search for a student using their `Id`, on top of finding by `Name`.
  
* [ViewStatsCommand](https://github.com/AY2223S1-CS2103T-T15-2/tp/blob/master/src/main/java/seedu/classify/logic/commands/ViewStatsCommand.java)
  * Implemented new command to generate statistics for a particular `Exam`.
  * Created new `GradeLessThanMeanPredicate` and `GradeComparator` classes as part of the ViewStatsCommand features.
  * See PR [#149](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/149)

#### Contributions to the UG
* [Finding a student record](https://ay2223s1-cs2103t-t15-2.github.io/tp/UserGuide.html#433-finding-a-student-record--find)
* [Calculating exam statistics](https://ay2223s1-cs2103t-t15-2.github.io/tp/UserGuide.html#441-calculating-exam-statistics-viewstats)
* [Command Summary Table](https://ay2223s1-cs2103t-t15-2.github.io/tp/UserGuide.html#6-command-summary)
  * Added the first draft of the Command Summary table

#### Contributions to the DG
* [FindCommand](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#424-find-command)
  * Rephrased implementation details for FindCommand, to including finding by `Id`.
  * Added a UML Diagram.
  * See PR [#145](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/145) and [#177](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/177) 
  

* [ViewStatsCommand](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#428-viewstats-command)
  * Added implementation details for `ViewStatsCommand`
  * Added a UML Diagram.` 
  * See PR [#177](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/177)
  
#### Contributions to Team-based Tasks
*To be updated*

#### Reviewing / Mentoring contributions
* [#199](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/199)

#### Contributions beyond the project team
*To be updated*


