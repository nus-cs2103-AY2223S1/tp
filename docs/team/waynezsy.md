---
layout: page
title: Wayne Zhang's Project Portfolio Page
---

### Project: PayMeLah

PayMeLah is a desktop app for **keeping track of** and **managing the debts** your friends owe you. It can also help **do simple calculations** for you, such as including GST or splitting debts amongst your friends. What’s more, it is optimised for you to do everything from just your keyboard!

Given below are my contributions to the project.

* **New Feature**: Implemented majority of the `paymelah.model.debt` package
  * What it does: Represents a debt and its associations as classes within the PayMeLah code base.
  * Justification: The package follows the Object-Oriented
  Programming paradigm that is used throughout PayMeLah.
  * Highlights: This package is the core of what makes PayMeLah different from AB-3. `testutil` classes including `DebtBuilder` and `TypicalDebts` were also implemented in conjunction with the `debt` package to improve testability of code. `DebtDate` and `DebtTime` were enhancements added to `Debt` at a later stage, requiring extensive refactoring throughout the PayMeLah code base for both functional code and test code to prevent regressions.

* **New Feature**: Implemented the `adddebt` command
  * What it does: allows the user to add debts to the persons in PayMeLah, enabling them to track information related to the debts such as a description, money amount, date and time.
  * Justification: This is the core feature of PayMeLah that enables users to keep track of debts and associate them with the persons who own the debts.
  * Highlights: Use of OOP ensures that changes made to the validity of user inputs relating to `debt` classes such as the `<money>` input will not affect the functionality of the code related to `adddebt`.
  
* **New Feature**: Enable calculation of GST/Service Charge when adding debts
  * What it does: allows the user to (optionally) specify to include Service Charge and GST in money amounts for debts added to PayMeLah.
  * Justification: This feature helps simplify debt-adding processes for users who are confused about how to calculate GST and Service Charge by doing the calculation for them.
  * Highlights: The final implementation of this feature made use of an elegant design where target users (NUS students) simply have to make use of common Singaporean lingo, a money amount followed `++`, to indicate a need for PayMeLah to calculate GST and Service Charge. Through the use of OOP design and encapsulation, modifications done to the `Money` class to implement this feature did not cause regressions in any other classes that have dependencies with `Money`, enabling the usage of this feature across commands such as `adddebt` and `splitdebt` and ensuring extensibility to future commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=waynezsy&tabRepo=AY2223S1-CS2103T-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed deadlines and milestones, including `v1.1`, `v1.2`, `v1.2b`, `v1.3`, `v1.3b` and `v1.4`.

* **Enhancements implemented**:
  * Refactored the code for multi-index command parsing, including both functional code for commands such as `splitdebt` and `deletedebt` and test code to prevent regressions. [#153](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/153)

* **Documentation**:
  * User Guide:
    * Introduction: Welcome message and [Using this guide](https://ay2223s1-cs2103t-w13-3.github.io/tp/UserGuide.html#using-this-guide) [#267](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/267)
    * [Adding your first debt](https://ay2223s1-cs2103t-w13-3.github.io/tp/UserGuide.html#adding-your-first-debt) [#273](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/273)
    * [Input-specific behaviour](https://ay2223s1-cs2103t-w13-3.github.io/tp/UserGuide.html#input-specific-behavior) [#184](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/184)
    * [Adding a debt: `adddebt`](https://ay2223s1-cs2103t-w13-3.github.io/tp/UserGuide.html#adding-a-debt-adddebt) [#281](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/281)
    * Tweaks to existing feature documentation, including `help` and `add` [#201](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/201)

  * Developer Guide:
    * [Implementation for `adddebt` feature](https://ay2223s1-cs2103t-w13-3.github.io/tp/DeveloperGuide.html#add-debt-feature-adddebt) [#198](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/198)
    * Updating the [`Model` component](https://ay2223s1-cs2103t-w13-3.github.io/tp/DeveloperGuide.html#model-component) [#132](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/198)
    * Adapt [NFRs](https://ay2223s1-cs2103t-w13-3.github.io/tp/DeveloperGuide.html#non-functional-requirements) from CS2103T tP Constraints [#25](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/25)

* Contributions to team-based tasks
  * Setting up the [Project Notes](https://drive.google.com/drive/folders/1a8jihoOt0452rXVICKvYSJxma2WtFeSp) Google Drive
  * Enabling assertions in Gradle
  * Maintaining issue tracker

* **Community**:
  * Some PRs reviewed (with non-trivial review comments): [#83](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/83), [#196](https://github.com/AY2223S1-CS2103T-W13-3/tp/pull/196)
  * Reported bugs and suggestions for other teams: [1](https://github.com/AY2223S1-CS2103-F14-3/tp/issues/170), [2](https://github.com/AY2223S1-CS2103-F14-3/tp/issues/182)

* **Tools**:
  * Made use of PlantUML
