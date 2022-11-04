---
layout: page
title: Nguyen Minh Tuan's Project Portfolio Page
---

### Project: CinternS

CinternS is a desktop app for managing internship applications, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Undo and redo commands
    * What it does: Allows users to restore the previous state after using a command that changes the state (e.g. add, edit, interview, etc.) and reapply the change after undoing it.
    * Justification: The rationale for this enhancement is that with quite a number of new commands that we have added, it is likely that new users may make mistakes while using the app even with the help of the user guide. One mistake can cause a significant change (e.g. clear), so this feature prevents such unfortunate mistakes, while making it much more convenient for users to use the app.
    * Highlights: Since this enhancement affected many of the existing and future commands, it required a good understanding of all components, especially the model. As new classes were added and existing commands and model had to be changed, it was important to make sure all existing features worked properly, with the help of relevant tests. 
    * Credits: The idea of adding `VersionedApplicationBook` was largely inspired by the one proposed in the developer guide of [address-book-level-3](https://github.com/se-edu/addressbook-level3).

* **New Feature**: Status field for applications
    * What it does: Allows users to add a status with a new application and edit existing status of their application as they progress through the application process.
    * Justification: The rationale for this enhancement is that when the number of applications become large, it is hard to remember the progress of every single one. Having a status field that is displayed clearly on the GUI allows the users to quickly find out the progress and update if necessary as they scroll down the list. This feature also aids the `stats` feature, as it provides the necessary statuses for a quick summary of current applications for users.
    * Highlights: We decided on this enhancement quite late compared to the other enhancements. As such, this caused significant changes in various other components, including model, command, parser and UI. Many of the tests had to be updated as they failed as status was added. The UI implementation was slightly challenging as the status tag must appear in the right place with the right colours. 
    * Credits: The design of status was inspired by the existing tags in [address-book-level-3](https://github.com/se-edu/addressbook-level3), with added colours and repositioning.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nmtuan2001&breakdown=true)

* **Project management**:
    * Managed issues and pull requests on the repository. Closed issues/PRs and linked PRs to the right issues when necessary.

* **Enhancements to existing features**:
    * Refactored the code base to support the features of an internship application management app, including updating Parser classes and tests (PR [#56](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/56), [#75](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/75)).
    * Added and updated tests for some of the existing features and those in testutil  (PR [#56](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/56)).


* **Documentation**:
    * User Guide:
        * Updated quick start and command summary sections (PR [#38](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/38))
        * Added documentation for undo and redo commands (PR [#128](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/128))
        * Added documentation for status field (PR [#128](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/128))
        * Updated documentation to fix minor issues with formatting and consistency (PR [#197](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/197))

    * Developer Guide:
        * Added the implementation of undo/redo commands (PR [#98](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/98))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#53](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/53), [#57](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/57), [#123](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/123), [#127](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/127)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/nmtuan2001/ped/issues/2), [2](https://github.com/nmtuan2001/ped/issues/3), [3](https://github.com/nmtuan2001/ped/issues/5), [4](https://github.com/nmtuan2001/ped/issues/6))


* **Tools**:
    * Integrated Codecov to the team repo

