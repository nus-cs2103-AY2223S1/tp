---
layout: page
title: Nam Minh Quan's Project Portfolio Page
---

### Project: JeeqTracQer

JeeqTracQer is a desktop app that allows small business owners to keep track of their business partners as well
as their transactions.

JeeqTracQer is designed to be used with CLI (Command Line Interface), and it has GUI created using JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Net Transaction**:
  * This feature displays the net amount transacted with all clients displayed in the left client list panel in JeeqTracker
  * It allows users to see how much they have transacted with all clients displayed on the left
* **Edit Client/Transaction/Remark**:
  * This feature allows users edit the details of an existing client/transaction/remark in the address book.
  * This helps users to change details of the contact list without deleting and adding again.
  * I implemented 3 separate commands for client, transaction, and remark respectively. These commands inherit from an abstract EditCommand class
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=quannam0124&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other')

* **Project management**:
  * To be added soon

* **Enhancements to existing features**:
  * Added additional test cases for existing model (calculate total transactions in Transaction model)

* **Documentation**:
  * User Guide:
    * Rearranged order of commands to improve the logic flow for readers
    * Added documentation for Editing and Deleting a client from Jeeq Tracker
    * Reformat parameters and tags used in commands into a table format to increase readability
  * Developer Guide:
    * Added sequence diagram and explanation for EditClientCommand
    * Added activity diagram for EditClientCommand

* **Community**:
  * PRs reviewed (with non-trivial review comments):
    * [Teammate's PR](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/184)
    * [Teammate's PR](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/205)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/115), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other classmates ([1](), [2]())

* _{you can add/remove categories in the list above}_

