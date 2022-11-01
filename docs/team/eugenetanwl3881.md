---
layout: page
title: Eugene Tan's Project Portfolio Page
---

### Project: AddressBook Level 3

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.

  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: _{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}_

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link]()

* **Project management**:

  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:

  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
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

* Update AboutUs ([PR#22](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/22))
* Remove AB3 Info from Developer Guide ([PR#36](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/36))
* Add NFRs to DG ([PR#57](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/57))
* Add skeletal UG ([PR#65](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/65))
* Add Commands for list, find and bye in UG ([PR#77](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/77))
* Add Acknowledgments in README ([PR#78](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/78))
* Add Use Cases 1 and 2 in DG ([PR#79](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/79))
* Add User Stories and Update Use Cases ([PR#142](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/142))
* Organize commands to subpackages based on their type. ([PR#149](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/149))
* Update UG Command Summary Table  ([PR#152](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/152))
* Update \"reset\" command ([PR#160](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/160))
* Add tag command (tagging of items) UI ([PR#171](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/171))
* Add \"tag\" and \"untag\" command (tags) to tag/untag an item ([PR#172](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/172))
* Add FAQ into UG ([PR#178](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/178))
* Add listtag command  ([PR#200](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/200))
* Add Implementation details in DG ([PR#230](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/230))
* Added Tests for Tag Commands ([PR#235](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/235))
* Enable assertions for Week 10 Team Task ([PR#241](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/241))
* Update UG  ([PR#261](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/261))
* Update DG ([PR#278](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/278))
* Small Fixes for DG Headers for Use Cases ([PR#281](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/281))
* Minor DG updates ([PR#287](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/287))
* Fix errors for UG Draft ([PR#292](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/292))
* Update Images and Admonitions in UG ([PR#306](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/306))
* DG Minor Updates ([PR#319](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/319))
* Fix minor errors for DG ([PR#334](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/334))
* Reformat DG User Stories Table  ([PR#419](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/419))
* Fix UG Bugs reported in PED ([PR#420](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/420))
* Fix bugs for renametag command
