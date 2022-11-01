---
layout: page
title: Ashiqur Rahman's Project Portfolio Page
---

### Project: Pupilist

##Overview

Pupilist is a desktop application that allows private tutors to plan and organize their student’s workflow, 
easing their workload.The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java.

##Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ashiqurrah&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Features & Enhancements**: 
  * Implemented `grade` field feature 
    * What does it do: To monitor their students' grade progress
      * Updated `AddCommand` and `AddCommandParser` to include `GradeProgress`
      * Created `GradeProgressCommand`, `GradeProgressCommandParser`, `GradeProgress` and `GradeProgressList` and the respective tests
  * Implemented `show` command feature
      * What does it do: To display the sessions of the specified day
          * Created `ShowCommand` and `ShowCommandParser` and the respective tests
      * Justification: This feature enables the user to view his sessions in a day
      * Note: This feature was done with Phylicia Christel
  * Implemented `mark` command feature
    * What does it do: To `mark` students' attendance or homework
      * Updated `HomeworkList` and `AttendanceList` to include `markAtIndex` that allows marking of specified index in respective list.
      * Created `MarkCommand`, `MarkCommandParser` and `MarkPersonDescriptor` and the respective tests
    * Justification: This feature enables the user to monitor the students' attendance and homework completion.
  * Implemented `unmark` command feature
    * What does it do: To `unmark` students' attendance or homework
      * Updated `HomeworkList` and `AttendanceList` to include `unmarkAtIndex` that allows marking of specified index in respective list.
      * Created `UnmarkCommand`, `UnmarkCommandParser` and `MarkPersonDescriptor` and the respective tests
    * Justification: This feature enables the user to unmark the students' attendance and homework completion and also provides a way for the user to undo his marking of a particulars' attendance or homework.
  
* **Contributions to the UG**: 
  * Added `mark` command
  * Added `unmark` command
  * Added `grade` command
  * Updated the prefixes summary table to include all prefixes

* **Contributions to the DG**:
    * Added grade progress feature
    * Added NFRs

* **Contributions to team-based tasks**:
  * Setting up Github Repo Page
  * Updated the [AboutUs](https://ay2223s1-cs2103t-w09-4.github.io/tp/AboutUs.html) page 
  * Release of v1.3.0 and v1.3.1

* **Review/mentoring contributions**:
  * PR reviews: Pull Requests [#107](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/107), [#70](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/70)

* **Contributions beyond the project team**:
  * [Bugs Reported](https://github.com/AshiqurRah/ped)