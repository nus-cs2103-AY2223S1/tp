---
layout: page
title: Tan Wen Cong's Project Portfolio Page
---

### Project: bobaBot

bobaBot is a desktop application for managing customers’ membership details. It is optimized for Command Line
Interface (CLI) while retaining some benefits of the Graphical User Interface (GUI). If you are a cashier working at a
bubble tea shop and can type fast, bobaBot can help you easily find and manage your customers’ membership information as
compared to other GUI applications.

bobaBot contains a database of customers’ information and supports operations to add, update, delete and even find
customers based on various inputs. Every entry in the database contains the customer's name, phone number, email
address, birthday month, reward points and membership tags.

bobaBot is written in Java and has about 13k LoC.

Given below are my contributions to the project.

* **New Feature**: Added BirthdayMonth class to model a customer's birthday month. [PR #136](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/136/files) 
  * What it does: Tracks customers' birthday months to provide them timely birthday promotions.

* **New Feature**: Added emoji to be displayed in CustomerCard in GUI [PR #144](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/144/files)
  * Credits: Reused from [stackoverflow](https://stackoverflow.com/questions/22872484/javafx-how-can-i-display-emoji)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tanwencong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=TanWenCong&tabRepo=AY2223S1-CS2103T-W09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Refactor addressbook to bobaBot [PR #155](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/155/files)

* **Enhancement to existing features**: Update Customer (formerly Person) class to be uniquely identified by phone number and email address [PR #66](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/66)
  * Justification: Allows different customers with same names to be added into database. (e.g. Alex, Benjamin...)
  * Highlights: This enhancement affects existing commands and commands to be added in the future that needs to
    identify customers. It required analysis of robustness and trade-offs with other design alternatives.
  
* **Documentation**:
  * User Guide:
    * Added documentation for the `add` feature. [PR #145](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/145/files)
  * Developer Guide:
    * Added Activity and Sequence diagrams for the `add` feature. [PR #117](https://github.com/AY2223S1-CS2103T-W09-1/tp/pull/117/files)

* **Community**:
  * Reported bugs for other teams ([#1 - #5](https://github.com/TanWenCong/ped/issues))
  
