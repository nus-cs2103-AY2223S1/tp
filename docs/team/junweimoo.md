---
layout: page
title: Moo Jun Wei's Project Portfolio Page
---

### Project: MyInsuRec

*MyInsuRec* is a desktop app for financial advisors to manage and organize their clients and meetings, helping
financial advisors stay connected with their clients. *MyInsuRec* is best suited for the quick typist, as it is
optimized for use via a Command Line Interface (CLI), with all the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=w16-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=junweimoo&tabRepo=AY2223S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**:
  * Command enhancements [#127](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/127), [#183](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/183)
    * Added command to filter meetings by time period with associated tests.
    * Added command to add a new meeting with associated tests.
    * Improved handling of invalid inputs by displaying more specific messages.
  * GUI enhancements [#113](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/113), [#195](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/195)
    * Added ability to switch between various GUI panels for different commands.
    * Added panels for a detailed view of meeting and client information.
    * Modified list view to better suit the needs of financial advisors.
    * Added ability to switch between light and dark themes.
  * Storage enhancements [#107](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/107), [#311](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/311), [#324](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/324)
    * Enhanced existing storage code to store meetings and products in addition to clients.
    * Added more tests to check error handling in case of invalid data.
  * Model enhancements [#298](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/298), [#149](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/149)
    * Added new methods for meetings and clients to support command operations.
    * Added field validation regexes for validating input strings.
    * Fixed bugs for conflicting meeting times
    
* **Documentation**:
  * User Guide [#286](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/286)
    * Wrote documentation for commands.
    * Wrote introduction.
    * Edited documentation and added navigation links.
  * Developer Guide [#323](https://github.com/AY2223S1-CS2103T-W16-4/tp/pull/323)
    * Described the implementation of addMeeting with accompanying sequence diagram.
    * Explained the choices made when deciding between implementations.
    * Wrote use cases for MyInsuRec.

* **Team-Based Tasks**:
  * Renamed the product to MyInsuRec and suggested ways to morph the feature set.
  * Incorporated use of SonarQube as a code quality checking tool.

* **Review/Mentoring Contributions**:
  * Discussed and agreed on target audience and user stories with teammates.
  * Suggested and discussed possible implementations of model and storage to teammates.
  * Reviewed team members' pull requests with suggestions made to improve code quality.
