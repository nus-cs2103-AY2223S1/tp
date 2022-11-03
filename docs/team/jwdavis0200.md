---
layout: page
title: Johannes Davis' Project Portfolio Page
---

### Project: Pupilist

##Overview

Pupilist is a desktop application that allows private tutors to plan and organize their student’s workflow, 
easing their workload.The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java.

##Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jwdavis0200&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enchancements implemented**:
  * Added the attendance feature and tests. (Pull request [\#54](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/54))
    * Created `AttendanceCommandParser` and `AttendanceCommand` and respective tests.
      * Created `AttendanceList`.
  * Implemented base of view feature. (Pull request [\#57](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/57))
    * Created `ViewCommand` and `ViewCommandParser` along with respective tests.
    * Created `NameIsKeywordsPredicate` to support feature along with respective tests.
    * NOTE: Final implementation of full view functionality was implemented by fellow team member.
  * Added the Session feature and tests. (Pull request [\#70](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/70))
    * Created `SessionCommand` and `SessionCommandParser` along with respective tests.
    * Created `SessionList`.
    * Implemented sorting logic of session timings for `SessionList`. Final implementation in (Pull request [\#108](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/108))
  * Implemented next session feature. (Pull request [\#108](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/108))
    * Created `NextSessionUtil`.
    * Integration of `ModelManager` with `LogicManager` and `MainWindow` to support feature.
    * Implemented logic of getting the next session timing with local machine time as reference.

* **Contributions to the UG**:
  * Added next session feature. (Pull request [\#126](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/126))
    * Updated other relevant commands where the feature is relevant.
    * Added image for next session feature.
  * Added more examples to make use cases of commands clearer to users. (Pull request [\#123](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/123))
  * Fixed bugs in UG that caused rendering issues. (Pull request [\#123](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/123), Pull request [\#187](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/187), Pull request [\#189](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/189))
  * Added updated images for Pupilist UI. (Pull request [\#123](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/123), Pull request [\#195](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/195))
  * Helped to improve overall UI to be more accurate and comprehensible, including UG bug fixes. (Pull request [\#123](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/123))
  * Changed layout of UG to make it more user-friendly. (Pull request [\#186](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/186))
  * Added introduction, glossary and prefix summaries section. (Pull request [\#186](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/186))

* **Contributions to the DG**:
  * Updated skeletal DG to match product specifications. (Pull request [\#29](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/29))
    * Added user stories matching Pupilist project.
  * Fixed layout rendering issues of table. (Pull request [\#84](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/84), Pull request [\#100](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/100))
  * Included Session feature along with diagrams and design considerations. (Pull request [\#95](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/95)) 

* **Review/mentoring contributions**:
  * PR reviews (non-trivial comments): (Pull request [\#200](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/200))
  * PR reviews (trivial comments with generally no problems with PR): (Pull request [\#95](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/95), Pull request [\#110](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/110), Pull request [\#111](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/111))
* **Contributions beyond the project team**:
  * [Bugs reported](https://github.com/jwdavis0200/ped/issues)

