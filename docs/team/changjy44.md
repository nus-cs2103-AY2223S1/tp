---
layout: page
title: Chang Jing Yan's Project Portfolio Page
---

### Project: TruthTable

TruthTable is a task-management software specially targeted towards tech-savvy university students leading teams in
software engineering modules to build projects. It helps track the team's progress and delegate tasks effectively.

Given below are my contributions to the project.

* **New Feature**: Add, delete, edit and set teams. ([PR #28](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/28))
   * **What it does**: Add, delete, edit team objects. Set current team that user is working on. 
   * **Justification**: A user may belong in multiple teams, and the implementation of team classes allows users to manage multiple teams.
   * **Highlights**: Implementing this feature required an in-depth understanding of the execution pipeline for commands and making the right design decisions for associating teams with a team. It also required knowledge on how the `Storage` component works.


* **New Feature**: Add, delete edit link in teams. ([PR #76](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/76)) 
  * **What it does**: Add, delete, edit link objects.
  * **Justification**: A student team leader needs to keep track of the all the links related to the project.
  * **Highlights**: Implementing this feature required an in-depth understanding of the execution pipeline for commands and making the right design decisions for associating links with a team. It also required knowledge on how the `Storage` component works. Regex was also used and designed to ensure that the URL being entered by the user is a valid link.


* **New Feature**: Find members and tasks in teams. ([PR #100](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/100))
  * **What it does**: Find members and tasks in teams.
  * **Justification**: A student team leader needs to locate his team members and tasks easily.
  * **Highlights**: Implementing this feature required an in-depth understanding of how lists are displayed in JavaFX. New classes were implemented based on the existing `NameContainsKeywordsPredicate` class in AB3.


* **New Feature**: Sort members and tasks in teams. ([PR #116](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/116))
  * **What it does**: Sort members and tasks in teams
  * **Justification**: A student team leader needs to view his team members in a more organised way.
  * **Highlights**: Implementing this feature required an in-depth understanding of how lists are displayed in JavaFX. As the original AB3 did not allow for sorting, a new `DisplayList` class was implemented which leveraged the `SortedList` class from JavaFX.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=changjy44&tabRepo=AY2223S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Testing**:
  * Add extensive unit tests for commands, parser and model classes. ([PR #206](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/206))
    * **Highlights**: As the picocli library was used for our parsing, all existing tests for commands and parsers were removed and had to be reimplemented from scratch. New testing infrastructure also had to be added to facilitate the tests.


* **Documentation**:
    * User Guide:
        * Documented commands for teams, links, find and sort functions. 
    * Developer Guide:
        * Documented implementation of add and set teams.


* **Community**:
    * PRs reviewed (with non-trivial review comments): Reviewed 18 PRS.
