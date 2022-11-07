---
layout: page
title: Clarence Chew Xuan Da's Project Portfolio Page
---

### Project: FoodWhere

**FoodWhere is a desktop application for managing your food stall addresses and reviews.** While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

FoodWhere is written in Java, with about 10 kLoC.

Given below are my contributions to the project.

* **Features:**
  * Implemented date and date parsing for reviews
  * Implemented storage for stalls and reviews so that multiple objects can be easily tracked
  * Planned and handled the potential bug that came from reviews being stored in both Stall and AddressBook
    * This affected the stall edit command and was somewhat related to the review edit command
    * The reviews are now primarily stored in the `Stall` class for each stall
    * The planning of this was challenging as many classes had to be affected
      * A [draft PR](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/201) was made and discarded to assess the issue
      * This was (roughly) broken into 5 PRs:
        * Preparation: [#204](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/204), [#206](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/206)
        * Debugging the main bug: [#207](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/207)
        * Debugging aftereffects: [#211](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/211)
        * Nice-to-have relaxation of uniqueness: [#221](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/221)

* **Documentation:**
  * Improved User Guide
    * Fixed issues raised in PE-D: [#324](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/324), [#335](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/335)
    * Added documentation about JSON file: [#324](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/324)
  * Improved Developer Guide
    * Wrote on non-functional requirements in the Developer Guide: [#39](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/39)
    * Added documentation about JSON file: [#351](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/351/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)

* **Code contributed:**
  * [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=clarence-chew&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Community**
  * Gave nontrivial comments on PRs
  * Improved test coverage: [#258](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/258), [#363](https://github.com/AY2223S1-CS2103-W14-2/tp/pull/363)
  * Found bugs in projects of other teams in CS2103/T:
    * Unexpected behaviours related to uniqueness invariance violation in PE-D: [#204](https://github.com/AY2223S1-CS2103-F13-1/tp/issues/204), [#205](https://github.com/AY2223S1-CS2103-F13-1/tp/issues/205)

* **Tools**
  * IntelliJ IDEA
