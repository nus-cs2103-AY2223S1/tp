---
layout: page
title: Ashiqur Rahman's Project Portfolio Page
---

### Project: Pupilist

#### Overview

Pupilist is a desktop application that allows private tutors to plan and organize their student’s workflow, 
easing their workload.The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java.

## Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ashiqurrah&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Features & Enhancements**: 
  * Implemented `grade` field feature (Pull Request [#56](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/56) )
    * What does it do: To monitor their students' grade progress
      * Updated `AddCommand` and `AddCommandParser` to include `GradeProgress`
      * Created `GradeProgressCommand`, `GradeProgressCommandParser`, `GradeProgress` and `GradeProgressList` and the respective tests
  * Implemented `show` command feature (Pull Request [#75](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/75))
      * What does it do: To display the sessions of the specified day
          * Created `ShowCommand` and `ShowCommandParser` and the respective tests
      * Justification: This feature enables the user to view his sessions in a day
      * Note: This feature was done with Phylicia Christel
  * Implemented `mark` command feature (Pull Request [#110](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/110))
    * What does it do: To `mark` students' attendance or homework
      * Updated `HomeworkList` and `AttendanceList` to include `markAtIndex` that allows marking of specified index in respective list.
      * Created `MarkCommand`, `MarkCommandParser` and `MarkPersonDescriptor` and the respective tests
    * Justification: This feature enables the user to monitor the students' attendance and homework completion.
  * Implemented `unmark` command feature (Pull Request [#111](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/111))
    * What does it do: To `unmark` students' attendance or homework
      * Updated `HomeworkList` and `AttendanceList` to include `unmarkAtIndex` that allows unmarking of specified index in respective list.
      * Created `UnmarkCommand`, `UnmarkCommandParser` and `MarkPersonDescriptor` and the respective tests
    * Justification: This feature enables the user to unmark the students' attendance and homework completion and also provides a way for the user to undo his marking of a particular student's attendance or homework.
  
* **Contributions to the UG**: 
  * Added `mark` command
  * Added `unmark` command
  * Added `grade` command
  * Updated the prefixes summary table to include all prefixes

* **Contributions to the DG**:
    * Added grade progress feature (Pull Request [#89](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/89))
    * Added Mark and Unmark feature (Pull Request [#211](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/211))
    * Added NFRs

* **Contributions to team-based tasks**:
  * Updated Site wide Settings (Pull Request [#36](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/36))
  * Setting up of Github Repo Page 
  * Updating of Github Repo README.md (Pull Request [#37](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/37), [#38](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/38), [#39](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/39))
  * Release of Pupilist `v1.3.0`, `v1.3.1` and `v1.4` (Pull Request [#117](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/117), [#122](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/56), [#211](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/56))

* **Review/mentoring contributions**:
  * [PR reviews](https://github.com/AY2223S1-CS2103T-W09-4/tp/pulls?q=is%3Apr+reviewed-by%3AAshiqurrah+)

* **Contributions beyond the project team**:
  * [Bugs Reported](https://github.com/AshiqurRah/ped/issues)
