---
layout: page
title: Ng Jun Sheng's Project Portfolio Page
---

### Project: uNivUSal

uNivUSal is a desktop application used to link CS2103T users to all the modes of communication of other users to make their lives easier. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to group contacts.
  * What it does: Allows the user to categorize their contacts into groups.
  * Commands: `addtogroup` `ungroup` `group`
  * Justification: This feature significantly improves the usability of the product as it allows users to more effectively manage their contacts.
  * Highlights: This enhancement also adds a popup window for each group, along with an `Email All` GUI button which should be useful for TAs and Professors.


* **New Feature**: Added the ability to email all the contacts in a group via the CLI.
  * What it does: Allows the user to contact all members of a group by email with a single command.
  * Command: `emailall`
  * Justification: As our application is targeted towards TAs and Professors, this feature significantly enhances the application as they often have to message all students in a tutorial/module.
  * Highlights: This command works with any web browsers and email clients, whichever is set as the default on the user's computer.


* **New Enhancement**: Enhanced the `help` command UI and examples.
    * What it does: Allows the user to get useful information about the usage of commands through the application directly.
    * Command: `help`
    * Justification: As our application is targeted towards efficiency, allowing users to access help directly from the application instead of having to open the web browser is ideal.
    * Highlights: This enhancement displays the information in a table format using monospace fonts which is easier to read for the users.



* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jsincorporated&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16)


* **Project management**:
    * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub


* **Enhancements to existing features**:
    * Revamped the GUI help window to provide more useful information to the users. [\#40](https://github.com/AY2223S1-CS2103T-T08-3/tp/commit/c0fe7be0830c36a60cc07b0b9eed5d51cd6b209d), [\#128](https://github.com/AY2223S1-CS2103T-T08-3/tp/commit/66130d0f6dd3d8a5e76627438ac9be789e7f3620)
    * Wrote additional tests for existing features to increase coverage from 49% to 53%. [\#192](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/192), [\#193](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/193)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `addtogroup`,`ungroup` and `group` [\#102](https://github.com/AY2223S1-CS2103T-T08-3/tp/commit/ddf759c5a2e4a9709110bf0bb8bddbe4174cd30a), [\#106](https://github.com/AY2223S1-CS2103T-T08-3/tp/commit/01a5337c2aaa3e007f57a1e55c07fce93ecd7360)
        * Add documentation for the feature `emailall` [\#106](https://github.com/AY2223S1-CS2103T-T08-3/tp/commit/01a5337c2aaa3e007f57a1e55c07fce93ecd7360)
        * Did cosmetic tweaks to existing documentation of feature `help`: [\#201](https://github.com/AY2223S1-CS2103T-T08-3/tp/commit/b614e70904035b6fbe0bacf30bc61749d6d2ca87)
    * Developer Guide:
        * Added implementation details of the `addtogroup` feature. [\#211](https://github.com/AY2223S1-CS2103T-T08-3/tp/commit/ebb354e65fcc3c2da935e6f51d568ed1fc9a3ede)
        * Added use cases for grouping features. [\#223](https://github.com/AY2223S1-CS2103T-T08-3/tp/commit/09be4d415ca7f39d2a1d342cced4b35ec8ab3815)
        * Added manual testing examples for grouping features [\#223](https://github.com/AY2223S1-CS2103T-T08-3/tp/commit/09be4d415ca7f39d2a1d342cced4b35ec8ab3815)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#93](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/93#pullrequestreview-1169403633), [\#89](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/89#pullrequestreview-1169402735), [\#107](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/107#pullrequestreview-1169400983)

