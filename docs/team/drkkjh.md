---
layout: page
title: Khoo Jing Hong, Derrick's Project Portfolio Page
---

### Project: ProfNUS

ProfNUS is a **desktop application which helps SOC Professors who have many modules with many students/TAs to manage.** It is optimized for users who prefer CLI over GUI so that frequent tasks can be done faster by typing in commands.

Given below are my contributions to the project.

* **New Feature**:
  * Implemented the `madd` command to add modules to the list of modules. [#73](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/73)
  * Implemented the `mdel` command to delete modules in the list of modules. [#78](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/78)
  * Implemented the `JsonAdaptedModule` for storing information about a module. [#64](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/64)
  * Implemented the `medit` command to edit details about a module that exists in the list of modules.[#117](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/117)
  * Implemented the `vtarget` command to display more details about a module in `mlist`. [#181](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/181)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=drkkjh&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Authored [15 issues](https://github.com/AY2223S1-CS2103T-W11-2/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Adrkkjh) to help keep track of current issues and progress.
  * Reviewed [35 pull requests](https://github.com/AY2223S1-CS2103T-W11-2/tp/pulls?q=is%3Apr+reviewed-by%3Adrkkjh) to ensure quality and consistency across the database.

* **Enhancements to existing features**:
    * Updated the UI of `mlist` to display the list of modules, alongside with the target module and the
  schedule of the target module when `vtarget` command is executed. [#136](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/136)
    * Updated `ModuleListPanel` to be able to display the target module and the schedules of the target module.
    * Added test cases for `Module` class. [#91](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/91)
    * Added test cases for `madd`, `mdel`, `medit` alongside during implementation.
    * Added test cases for `vtarget`. [#296](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/296)

* **Documentation**:
    * User guide:
      * Updated the module section specifically on `madd`, `mdel`, `medit` and `vtarget`.
      * Updated the command summary section specifically on `madd`, `mdel`, `medit` and `vtarget`.
    * Developer guide:
      * Added implementation details, along with sequence diagrams and activity diagrams for `medit` command. [#140](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/140)
      * Added implementation details, along with sequence diagrams and activity diagrams for `vtarget` command. [#319](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/319)

* **Community**:
    * Enabled assertions in tP [#120](https://github.com/AY2223S1-CS2103T-W11-2/tp/pull/120)

* **Tools**:

