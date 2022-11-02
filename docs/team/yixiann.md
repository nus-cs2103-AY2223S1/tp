---
layout: page
title: Tan Yi Xian's Project Portfolio Page
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

Error. Some PRs not found in any PPP:
* Update AboutUs page ([PR#14](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/14))
* Add skeleton for user guide ([PR#35](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/35))
* Update aboutUs page to include information about Yi Xian ([PR#89](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/89))
* Update userguide to include help command ([PR#90](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/90))
* Add skeletal PPP ([PR#91](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/91))
* Add skeletal for UC7 and UC8 in DeveloperGuide.md ([PR#92](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/92))
* Add image for aboutUs page ([PR#96](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/96))
* Remove boilerplate and update roles in About Us page ([PR#100](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/100))
* Point CI banners to own project and remove traces of AB3 in README.md ([PR#102](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/102))
* Add use case 7, use case 8 and user stories for inventory items ([PR#129](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/129))
* Update Quick Start of user guide, reorganise content page ([PR#141](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/141))
* Change TYPE to UNIT in UserGuide.md ([PR#145](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/145))
* Add value preposition ([PR#147](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/147))
* Refactoring Person to Item 2 ([PR#157](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/157))
* Convert Add command to New command ([PR#173](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/173))
* Rename command word from \"delete\" to \"del\" ([PR#174](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/174))
* Modify Find item messages and test to match FoodRem instead of AddressBook ([PR#175](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/175))
* Rename \"Clear\" to \"Reset\" ([PR#176](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/176))
* Modify increment and decrement command to have optional qty prefix ([PR#183](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/183))
* Modify help command to provide help for each individual command ([PR#201](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/201))
* Allow easier extension to help command by removing switch case ([PR#211](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/211))
* Update help, edit and delete command ([PR#212](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/212))
* Add price field in item ([PR#229](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/229))
* Move enum package to commons and remove traces of AB-3 from DG ([PR#233](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/233))
* Move enum package to commons and remove traces of AB-3 from DG ([PR#233](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/233))
* Add UML diagram for HelpCommand ([PR#234](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/234))
* Add remarks fields and remarks command ([PR#254](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/254))
* Standardise help command ([PR#255](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/255))
* Add test for Decrement, Increment, View, Sort, Help, Exit and Reset ([PR#257](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/257))
* Improve UG and add remarks and price fields ([PR#272](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/272))
* Fix \"Rename tag to same name is possible\" bug ([PR#274](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/274))
* Fix index out of bound bug and standardise naming of index across item command ([PR#282](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/282))
* Restructure expiry date and bought date to use singleton design pattern ([PR#290](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/290))
* Fix rename tag not changing tags within items ([PR#296](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/296))
* Fix storage modification causing bugs ([PR#298](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/298))
* Create welcome message for user that shows error if datafile has issue ([PR#299](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/299))
* Show empty foodrem when data file is corrupted ([PR#303](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/303))
* Add tag related images ([PR#314](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/314))
* Centralise logic for validate string regex ([PR#316](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/316))
* Add remarks to help ([PR#317](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/317))
* Ensure all commands uses a parser ([PR#329](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/329))
* Add UI for tags ([PR#340](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/340))
* Prevent special chars in find command ([PR#355](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/355))
* Modify view command to show error for negative index ([PR#357](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/357))
* Modify price validator to not accept 3.e1 ([PR#358](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/358))
* Update UG ([PR#359](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/359))
* Add storage restriction to items and tags ([PR#362](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/362))
* Apply negative index validation to all commands ([PR#365](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/365))
* Update user guide to match changes to codebase ([PR#368](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/368))
* Fix date validation ([PR#370](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/370))
* Update find to match new find ([PR#371](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/371))
* Update help command to use ITEM_NAME and TAG_NAME instead of NAME ([PR#374](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/374))
* Fix sorting by name ([PR#375](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/375))
* Add minor changes in the Userguide ([PR#429](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/429))
* Fix broken glossary term for expiry date and broken link for flags ([PR#432](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/432))
* Fix getColor for tags ([PR#434](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/434))
* Use stats parser to restrict command arguments ([PR#435](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/435))
