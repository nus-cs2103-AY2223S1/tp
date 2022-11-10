---
layout: page
title: James's Project Portfolio Page
---

### Project: ArtBuddy

ArtBuddy is a desktop client management system built to help freelance artists manage
their clients and commissions easily. It is written in Java, with a GUI created with
JavaFx.

Given below are my contributions to the project.

* **New Feature**:
  * Implemented `addcom` - Adds commission to selected customer. [#75](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/75)
  * Implemented `findcom` - Filters current commission list for those that contains the specified keyword and tags. The task requires careful consideration about the set of possible inputs, as they can include only keywords, only must-have tags, only optional tags or any combination of the above. [#141](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/141) [#129](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/129)
  * Implemented `listcom` - Returns the original unfiltered commission list. [#141](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/75)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=zlimez)


* **Project management**: None


* **Enhancements to existing features**:
  * Polished image retrieval for iterations. Some digging on the web was required to understand how JavaFX processes path to a local file. [#92](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/92)
  * Merged keyword and tag filtering of customers into one `find` command. [#141](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/75)
  * Added tests for `addcom`, `find`, `findcom`. The task required extensive number of stubs and considerations on the enumeration paths during runtime code execution. [#211](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/211)


* **Documentation**:
  * User Guide: Quickstart and `addcom` under Features. [#58](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/58)
  * Developer Guide: Target User Profile, Value Proposition and Use Cases. [#58](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/58)


* **Community**:
  * Suggested a bug in the original customer replacement pattern when updating a customer's commission list. [#74](https://github.com/AY2223S1-CS2103T-W11-3/tp/issues/74)
  * Proposed the need of a universal commission list that keeps the commissions across all customers. (Implemented by [@ngxingyu](https://github.com/ngxingyu) [#140](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/140))
  * PRs reviewed (with non-trivial review comments): [#84](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/84) [#107](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/107)
  * Reported [bugs and suggestions](https://github.com/zlimez/ped/issues) for other teams in the class.
