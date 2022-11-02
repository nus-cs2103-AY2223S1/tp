---
layout: page
title: Farrel Dwireswara Salim's Project Portfolio Page
---

### Project: PleaseHireUs

PleaseHireUs (PHU) is a **desktop app for managing internships, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Undo command
  * What it does: Allow users to undo the command that modified the internship book. (Pull Request [\#76](https://github.com/AY2223S1-CS2103T-W17-4/tp/pull/76))
  * Justification: This feature improves the app significantly because user can conveniently undo any unintended command.
  * Credits: `VersionedInternshipBook` class is adapted from [Addressbook Level 4](https://github.com/se-edu/addressbook-level4/blob/master/src/main/java/seedu/address/model/VersionedAddressBook.java)
  
* **New Feature**: Redo command
  * What it does: Allow users to redo the command that was previously undone. (Pull Request [\#76](https://github.com/AY2223S1-CS2103T-W17-4/tp/pull/76))
  * Justification: This feature improves the app significantly because user can conveniently redo any command that has been undone. 
  * Credits: `VersionedInternshipBook` class is adapted from [Addressbook Level 4](https://github.com/se-edu/addressbook-level4/blob/master/src/main/java/seedu/address/model/VersionedAddressBook.java)

* **New Feature**: Added keyboard shortcut to get previous command.
  * What it does: Allow users to traverse through the command that has been executed previously upon pressing the `up` and `down` key. (Pull Request [\#93](https://github.com/AY2223S1-CS2103T-W17-4/tp/pull/93))
  * Justification: This is one of the primary functionalities that exists in the usual CLI program such as command prompt in windows.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sugiyem&breakdown=true)

* **Enhancements to existing features**:
  * Enabled multiple deletion in one `delete` command (Pull Request [\#25](https://github.com/AY2223S1-CS2103T-W17-4/tp/pull/25))
  * Updated `edit` command so that it can edit new parameters (Pull Request [\#37](https://github.com/AY2223S1-CS2103T-W17-4/tp/pull/37))
  * Wrote additional tests for new and existing features to increase coverage (Pull Request [\#196](https://github.com/AY2223S1-CS2103T-W17-4/tp/pull/196))

* **Documentation**:
  * User Guide:
    * Added documentations for the features `delete`, `edit`, `undo`, and `redo`.
  * Developer Guide:
    * Added documentations for the features `delete`, `edit`, `undo`, and `redo`.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#214](https://github.com/AY2223S1-CS2103T-W17-4/tp/pull/214)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/sugiyem/ped/issues/3), [2](https://github.com/sugiyem/ped/issues/6)) 

* **Tools**:
  * Integrated a third party library (TestFX) to the project
