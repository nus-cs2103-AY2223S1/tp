---
layout: page
title: Eugene Ong Wei Xiang's Project Portfolio Page
---

### Project: SoConnect

SoConnect is a desktop app for NUS SoC students to manage their contacts, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands. (remove)
    * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=eugene-ong-w-x&breakdown=true)

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub (remove)
    * Project Lead

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]()) (remove)
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]()) (remove)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]() (remove)
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]() (remove)
    * Developer Guide:
        * Added implementation details of the `delete` feature. (remove)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]() (remove)
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]()) (remove)
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]()) (remove)
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]()) (remove)

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]()) (remove)
    * Integrated a new Github plugin (CircleCI) to the team repo (remove)
