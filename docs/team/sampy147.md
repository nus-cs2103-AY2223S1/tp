---
layout: page
title: Samuel Pang's Project Portfolio Page
---

### Project: MODPRO

MODPRO is a desktop application which helps NUS students in tracking the progress of their modules. It is highly optimised for students who prefer Command Line Interface (CLI) by allowing those who type fast to key in commands to track their modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete a task / module
  * What it does: allows the user to delete a task / module from the task / module list.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them. It gives the user the freedom to delete any task / module that the user deems unnecessary.
  * Highlights: This enhancement required the development of non-trivial new methods across different classes. It required an in depth understanding of user requirements, as certain design decisions such as deleting exams and tasks related to a module when the module was deleted were based of a user-centric approach.
  * Credits: Referenced AB3 implementation for delete command.

* **New Feature**: Added the ability to edit a module
  * What it does: allows the user to edit a module in the module list.
  * Justification: This feature improves the product greatly because a user can make mistakes in commands and the app should provide a convenient way to rectify them. It gives the user the freedom to edit any module that the user deems unnecessary.
  * Highlights: This enhancement affects existing methods across different classes and required the creation of methods that required extensive understanding of the existing code base. It required complex implementation using multiple classes, as certain design decisions such as editing exams and tasks related to a module when the module was edited were non-trivial.
  * Credits: Referenced AB3 implementation for edit command.

  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sampy147&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


* **Documentation**:
    * User Guide:
        * Added documentation for the features `deleteTask`, `deleteModule` and `editModule` (links: [#43](https://github.com/AY2223S1-CS2103T-F11-2/tp/pull/43), [#201](https://github.com/AY2223S1-CS2103T-F11-2/tp/pull/201)).
    * Developer Guide:
        * Added details of the `deleteTask`, `deleteModule` and `editModule` feature (links: [#44](https://github.com/AY2223S1-CS2103T-F11-2/tp/pull/44), [#106](https://github.com/AY2223S1-CS2103T-F11-2/tp/pull/106)). 


* **Community**:
  * Numerous PRs reviewed (with non-trivial review comments).
  * Clarified questions in forum regarding feature bugs ([example](https://github.com/nus-cs2103-AY2223S1/forum/issues/407)).
  * Reported bugs and suggestions for other teams (examples: [1](https://github.com/Sampy147/ped/issues/1), [2](https://github.com/Sampy147/ped/issues/2), [3](https://github.com/Sampy147/ped/issues/9), [4](https://github.com/Sampy147/ped/issues/10)).
  
