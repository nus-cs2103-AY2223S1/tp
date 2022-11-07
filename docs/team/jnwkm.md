---
layout: page
title: Julian Wong's Project Portfolio Page
---

### Project: uNivUSal

uNivUSal is a desktop application used to link CS2103T users to all the modes of communication of other users to make their lives easier. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.
  * What it does: allows the user to see up to their last five previous commands typed.
  * Justification: This feature improves the product significantly because a user may want to see what they have typed beforehand if they do not remember.
  * Highlights: This enhancement uses a First-In-First-Out (FIFO) data structure in the form of a queue to present the command history.

* **New Feature**: Added a profile picture that changes accordingly to whatever the contact's occupation is.
  * What it does: allows the user to see easily what the occupation is for each contact in a pictorial form.
  * Justification: This feature improves the product because a user may want to know what roles all their contacts hold at a glance.
  * Highlights: This enhancement uses new images saved into the resources folder to be bundled into the JAR file and a function to determine the right image to display.

* **New Enhancement**: Enhanced the delete command to be multi-functional.
  * What it does: allows the user to not just delete contacts but also delete specific fields of people.
  * Justification: This feature improves the product significantly because a user may want to update contacts that no longer use those fields.
  * Highlights: This enhancement affects existing commands. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and validators. Required changes to GUI also to display the deleted data field.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jnwkm&breakdown=true)

* **Project management**:
  * Co-managed releases `v1.1` - `v1.4` (4 releases) on GitHub with team

* **Enhancements to existing features**:
  * Wrote additional tests for existing features to increase coverage from 59% to 64% (Pull requests [\#205](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/205), [\#219](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/219))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete`, `find` & `history` [\#122](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/122) [\#62](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/62)

  * Developer Guide:
    * Added implementation details of the `history` feature. [\#87](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/87)
    * Updated implementation details of the `logic` & `ui` components. [\#218](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/218)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#61](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/61), [\#89](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/89), [\#98](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/98), [\#186](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/186)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Jnwkm/ped/issues/2), [2](https://github.com/Jnwkm/ped/issues/5), [3](https://github.com/Jnwkm/ped/issues/1))
  
