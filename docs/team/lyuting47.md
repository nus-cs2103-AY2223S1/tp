---
layout: page
title: Chen Lyuting's Project Portfolio Page
---

### Project: PayMeLah

PayMeLah is a desktop application that helps users track the debts that they are owed. It uses a Command Line Interface (CLI), and executes commands input by the user accordingly to perform actions such as adding a person/debt to track, sorting the data etc.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=lyuting47&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=lyuting47&tabRepo=AY2223S1-CS2103T-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **New Features**:
  * **List Debtors** [#67](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/67) [#100](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/100)
    * What it does: Allows user to list persons who owe more than or equal to a specified amount.
    * Justification: This is a key feature which helps the user to track debts, as it allows the user to filter the list and see the "high priority persons" who owes them a significant amount of money.
    * Highlights: It is optional for user to specify the amount, if no amount is specified, PayMeLah will list all the persons that owe any amount of money. Somewhat challenging to implement as it requires the same command to behave differently depending on user input.
  * **Sort** [#127](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/127) [#148](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/148)
    * What it does: allows user to sort persons by name, amount owed, or time since the person's oldest debt. Also allows users to specify whether to sort in ascending or descending order.
    * Justification: This is a key feature which allows the user to organise their data in PayMeLah in a neat and logical manner, with a degree of customisation.
    * Highlights: It was challenging to implement as we wished to keep the internal PersonList immutable. Hence each sort will require a new copy of PersonList to be created carefully with the same contents but in sorted order. Required thorough understanding of the Model component.
  * **Undo** [#152](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/152) [#157](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/157)
    * What it does: allows user to undo the last command that modified PayMeLah's data.
    * Justification: This is a very useful Quality-of-Life feature as it allows the user to easily correct a mistake in a previous command.
    * Highlights: It was challenging to implement as there are no similar existing commands in the AB3 base project. Major modifications had to be made to the Model class.


* **Project management**:
  * **Bug fixes for general features and other team members' features**
    * [#164](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/164)
    * [#172](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/172)
    * [#187](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/187)
  * **Issue creation for team tasks**
    * [#4](https://github.com/AY2223S1-CS2103T-W13-3/tp/issues/4)
    * [#76](https://github.com/AY2223S1-CS2103T-W13-3/tp/issues/76)


* **Documentation**:
  * User Guide:
    * Added documentation for the following commands:
      * `listdebtors` [185](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/185)
      * `sort` [185](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/185)
      * `undo` [#208](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/208)
    * Added section on usage of keyboard shortcuts to navigate UI [#285](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/285)
  * Developer Guide:
    * Added documentation for following features:
      * List Debtors (with activity and sequence diagrams) [#196](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/196)
      * Undo [#307](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/307)


* **Community**:
  * PRs reviewed with non-trivial comments:
    * [#151](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/151#discussion_r1005771134)
    * [#129](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/129#discussion_r1002510391)
  * Higher than average number of bugs reported for other team (almost all accepted), some listed below:
    * [#137](https://github.com/AY2223S1-CS2103T-W17-3/tp/issues/137)
    * [#184](https://github.com/AY2223S1-CS2103T-W17-3/tp/issues/184)
    * [#173](https://github.com/AY2223S1-CS2103T-W17-3/tp/issues/173)
    * [#145](https://github.com/AY2223S1-CS2103T-W17-3/tp/issues/145)
