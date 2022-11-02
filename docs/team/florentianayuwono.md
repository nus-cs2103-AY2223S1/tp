---
layout: page
title: Florentiana Yuwono's Project Portfolio Page
---

# Project: SoConnect

SoConnect is a **desktop app for managing contacts and tasks**. It aims to help NUS SoC students stay better connected to their school life, in terms of social connections and school tasks. SoConnect is also **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 42 kLoC.

## Contribution

### New Feature: Advanced Search Functionality

**What it does**: Allows user to have a mini Google inside SoConnect. The search command can generate relevant search result, even in the event of mistyping or only partial information is available.

**Justification**: This feature improves the user productivity significantly because a user will not need to provide full information upon searching and the app could still return accurate and relevant result based on the query. 

**Highlight**: This enhancement replaces the previous `find` functionality. The search feature itself requires careful design on how the algorithm works, especially on the relevancy reduction level.

**Credit**: This feature is adapted from the `find` feature, which only allows full word match result to be returned.

### Code Contribution

Up until 2 November 2022, I have contributed 1781 LoC (roughly 4,185 additions and 2,481 deletions), changed 36 files and pushed 132 commits. For most recent update, refer to this [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=florentianayuwono&breakdown=true).

### Project Management

**Release**: Contributed in release of `v1.2` and `v1.3` (2 releases) on GitHub.

**Enhancements to existing features**:
  * Revamped the GUI theme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

  * **Documentation**:
    * User Guide:
      * Added documentation for the features `delete` and `find` [\#72]()
      * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
      * Added implementation details of the `delete` feature.

  * **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

  * **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

  * _{you can add/remove categories in the list above}_
