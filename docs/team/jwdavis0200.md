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
  * Added the attendance feature and tests.
    * Created `AttendanceCommandParser` and `AttendanceCommand` and respective tests.
      * Created `AttendanceList`.
  * Implemented base of view feature.
    * Created `ViewCommand` and `ViewCommandParser` along with respective tests.
    * Created `NameIsKeywordsPredicate` to support feature along with respective tests.
    * Full view functionality was implemented by fellow team member.
  * Added the Session feature and tests.
    * Created `SessionCommand` and `SessionCommandParser` along with respective tests.
    * Created `SessionList`.
    * Implemented sorting logic of session timings for `SessionList`
  * Implemented next session feature.
    * Created `NextSessionUtil`.
    * Integration of `ModelManager` with `LogicManager` and `MainWindow` to support feature.
    * Implemented logic of getting the next session timing with local machine time as reference.

* **Contributions to the UG**:
  * Added next session feature.
    * Updated other relevant commands where the feature is relevant.
    * Added image for next session feature.
  * Added more examples to make use cases of commands clearer to users.
  * Fixed bugs in UG that caused rendering issues.
  * Added updated images for Pupilist UI.
  * Helped to improve accuracy of UG for better user understanding.

* **Contributions to the DG**:
  * Updated skeletal DG to match product specifications.
    * Added user stories matching Pupilist project.
  * Fixed layout rendering issues of table.
  * Included Session feature.

* **Review/mentoring contributions**: `to be added soon`
* **Contributions beyond the project team**: `to be added soon`

