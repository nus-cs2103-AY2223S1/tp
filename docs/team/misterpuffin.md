---
layout: page
title: Haoren's Project Portfolio Page
---

### Project: Condonery

Condonery is a desktop app made for property agents primarily used for managing client contacts and condo listings.
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Condonery can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.
  * What it does: Allows users to quickly access previously typed commands to amend or execute again.
  * Justification: This feature makes the product more convenient to use, and couples well together with the undo command. Users may sometimes key in the wrong information and may wish to amend their previous command instead of retyping the whole command.
  * Attribution: This feature is inspired by CLI terminals which allow you to view previous commands.

* **New Feature**: Added the ability to upload images.
  * What it does: allows the user to upload images for both Property and Client models. 
  * Justification: This feature makes the product more visually appealing, and helps property agents easier identify the Property they are looking for.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=misterpuffin&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Added Undo Command [\#87](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/87)
  * Added convenient reuse of commands using arrow keys [\#106](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/106)
  * Added image uploads for Properties and Clients [\#92](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/92)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `undo` [\#114](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/114)
    * Added documentation for Image Upload [\#114](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/114)
     
  * Developer Guide:
    * Added implementation details of the `undo` feature. [\#243](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/243)
    * Added implementation details of the image upload feature. [\#243](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/243)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#97](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/97), [\#190](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/190), [\#206](https://github.com/AY2223S1-CS2103-W14-1/tp/pull/206)
  * Contributed to forum discussions (examples:  [97](https://github.com/nus-cs2103-AY2223S1/forum/issues/97#issuecomment-1229889608), [150](https://github.com/nus-cs2103-AY2223S1/forum/issues/150), [255](https://github.com/nus-cs2103-AY2223S1/forum/issues/255), [299](https://github.com/nus-cs2103-AY2223S1/forum/issues/299#issuecomment-1255823878))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2223S1-CS2103T-T11-1/tp/issues/117), [2](https://github.com/AY2223S1-CS2103T-T11-1/tp/issues/96), [3](https://github.com/AY2223S1-CS2103T-T11-1/tp/issues/82))

