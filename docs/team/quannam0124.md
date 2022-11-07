---
layout: page
title: Nam Minh Quan's Project Portfolio Page
---

### Project: JeeqTracker

JeeqTracker is a desktop app that allows small business owners to keep track of their business partners as well
as their transactions.

JeeqTracker is designed to be used with CLI (Command Line Interface), and it has GUI created using JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Net Transaction**:
  * This feature calculates the net amount transacted with all clients displayed in the left client list panel in JeeqTracker
  * It allows users to see how much they have transacted with all clients displayed on the left
* **UI Net Transaction**:
  * This feature calculates the net amount transacted with all clients displayed in the left client list panel in JeeqTracker
* **Edit Client/Transaction/Remark**:
  * This feature allows users edit the details of an existing client/transaction/remark in the address book.
  * This helps users to change details of the contact list without deleting and adding again.
  * Implemented 3 separate commands for client, transaction, and remark respectively. These commands inherit from an abstract EditCommand class
* **Parsers for EditClient/Transaction/Remark commands**:
  * Added functions to parse parameters for editing clients, transactions, and remark respectively
  * Increase code readability
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=quannam0124&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other')

* **Project management**:
  * To be added soon

* **Enhancements to existing features**:
  * Added additional test cases for existing model (calculate total transactions in Transaction model)

* **Documentation**:
  * User Guide:
    * Rearranged order of commands to improve the logic flow for readers [#266](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/266)
    * Added documentation for Editing and Deleting a client from JeeqTracker [#262](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/262)
    * Reformat parameters and tags used in commands into a table format to increase readability [#287](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/287/files)
    
  * Developer Guide: [#309](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/309)
    * Added explanation for implementation and sequence diagrams for EditClientCommand, EditTransactionCommand, and EditRemarkCommand 
    * Added activity diagram for edit client command
    * Added use cases UC11 to UC13
    * Added test instructions for edit client, edit transaction, and edit remark features

* **Community**:
  * PRs reviewed (with non-trivial review comments):
    * [#184](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/184)
    * [#205](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/205)
    * [#282](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/282)
    * [#286](https://github.com/AY2223S1-CS2103T-T09-1/tp/pull/286)
  * Contributed to forum discussions: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/115)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2223S1-CS2103T-W13-1/tp/issues/146), [2](https://github.com/AY2223S1-CS2103T-W13-1/tp/issues/138), [3](https://github.com/AY2223S1-CS2103T-W13-1/tp/issues/130))

