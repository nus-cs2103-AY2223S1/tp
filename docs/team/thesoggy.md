---
layout: page
title: Bryan Yang's Project Portfolio Page
---

### Project: PayMeLah

PayMeLah is a desktop app for **keeping track of** and **managing the debts** your friends owe you. It can also help **do simple calculations** for you, such as including GST or splitting debts amongst your friends. What’s more, it is optimised for you to do everything from just your keyboard!

Given below are my contributions to the project.

* **New Features**:
    * **Mark/Unmark Command** [#151](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/151)
        * What it does: Allows the user to mark a debt as paid or unpaid.
        * Justification: This feature improves the product significantly because a user can easily keep track of which debts are paid and which are not.
        * Highlights: This feature allows users to specify more than one index for debts-to-mark, allowing users to mark multiple debts at once.
        Implementation of this feature was not too difficult as there was code of similar functionality (`DeleteDebt`) being pushed out at the same time. There was also implementation of automated testing to prevent regressions.
    * **Statement Command** [#81](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/81)
        * What it does: Allows the user to view a statement of all the debts owed to them.
        * Justification: This feature improves the product because a user can easily get a count of the total amount of a filtered list of debts.
        * Highlights: Implementation of this was challenging as it required thorough understanding of Model component as well as Person model to implement methods to getDebts() and then getTotalDebt() to get total debt for 1 person. There was also implementation of automated testing to prevent regressions.
    * **UI enhancements** [#191](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/191), [#206](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/206)
        * What it does: Allows the user to view a more visually appealing and effective UI by having an `Accordion` for list of persons and an inner `ListView` for debts.
        * Justification: This feature improves the product significantly because a more visually appealing UI improves the product experience. The UI is more effective as it allows users to view the list of persons as a whole to quickly navigate to the person they want and then open the pane up to view their debts.
        * Highlights: Implementation of this was challenging as it required thorough understanding of the UI component and how to implement the changes.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=TheSoggy&tabRepo=AY2223S1-CS2103T-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Set up 21 issues and managed 30 issues for the project
    * Clarified whether certain issues were bugs or enhancements in the forum [#410](https://github.com/nus-cs2103-AY2223S1/forum/issues/410), [#421](https://github.com/nus-cs2103-AY2223S1/forum/issues/421)

* **Documentation**:
    * User Guide:
        * Added documentation for the following commands:
            * `mark`/`unmark` [#195](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/195)
            * `statement` [#48](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/48)
        * Added section on usage of keyboard shortcuts to navigate UI [#285](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/285)
        * Added screenshots for everything related to the application [#367](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/367)
    * Developer Guide:
        * Added documentation for following features:
            * `mark`/`unmark` [#136](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/136), [#207](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/207)
            * `statement` [#49](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/49)
        * Updated Acknowledgements section [#344](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/344)
        * Updated UiClassDiagram [#359](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/359)

* **Community**:
    * PRs reviewed (with non-trivial review comments):
        * [#61](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/61)
        * [#129](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/129)
    * Top 10% of number of bugs reported for other team, some listed below:
        * [#191](https://github.com/AY2223S1-CS2103T-T15-1/tp/issues/191)
        * [#193](https://github.com/AY2223S1-CS2103T-T15-1/tp/issues/193)
        * [#198](https://github.com/AY2223S1-CS2103T-T15-1/tp/issues/198)
        * [#205](https://github.com/AY2223S1-CS2103T-T15-1/tp/issues/205)
        * [#209](https://github.com/AY2223S1-CS2103T-T15-1/tp/issues/209)
        * [#210](https://github.com/AY2223S1-CS2103T-T15-1/tp/issues/210)

* **Tools**:
    * Made use of PlantUML to create diagrams for the Developer Guide
