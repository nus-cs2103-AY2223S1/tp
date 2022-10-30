---
layout: page
title: Gowri Bhat's Project Portfolio Page
---

### Project: GREWZ

GREWZ is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the class group field to each student.
  * What it does: allows the user add, edit and remove the class group field for each student.
  * Justification: This feature improves the product significantly because it is a key feature for TAs as they will need to identify their students based on class.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Modified the find command to search all fields.
    * What it does: allows the user to find any student with details that completely or partially match the search keywords.
    * Justification: This feature improves the find command significantly because a user is likely to have limited information such as partial name or only class group name. The generic search allows the user to find all students related to the keyword easily.
    * Highlights: This enhancement affects the existing find command and commands to be added in the future if we have specialised searches. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    
* **New Feature**: Added the ability to edit tasks.
  * What it does: allows the user to edit existing tasks.
  * Justification: This feature improves the product significantly because a user can make mistakes while adding a task and the app should provide a convenient way to rectify them as well as update them with the latest information.
  * Highlights: It required an in-depth analysis of design alternatives.

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.
    * What it does: allows the user to access previous commands using the up and down arrow keys.
    * Justification: This feature improves the product significantly because for users who are fast-typists, this feature allows them to revisit frequently used commands with a click of a button.
    * Credits: *{ code adapted from [seniors](https://github.com/AY2122S2-CS2103T-W13-3/tp) }*

* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.2` - `v1.4rc` (3 releases) on GitHub

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
