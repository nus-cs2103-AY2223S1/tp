---
layout: page
title: Nicholas Patrick's Project Portfolio Page
---

### Project: AddressBook Level 3

uNivUSal is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the undo command that lets users undo mistakes in their last action.
  * What it does: undoes the last modifying command
  * Justification: This feature forgives the user for making mistakes that otherwise may delete important information.
  * Command: `undo`

* **New Feature**: Quick edit by clicking the person card.
  * What it does: Creates a complete edit command that does effectively nothing so modifying person details can be done quickly.
  * Justification: This feature allows the use of the cursor where it's likely to be faster.

* **New Enhancement**: Not all details required.
  * What it does: Does not ask for the user the know every detail about a contact.
  * Justification: A person might not have all the details of a newly created contact.

* **New Enhancement**: Tighter detail checks.
  * What it does: Prevents the user from entering unreasonably long details (such as 50-digit phone numbers)
  * Justification: It is not reasonable to have extremely long addresses, email addresses, or phone numbers.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=NicholasPatrick)

* **Project management**:
  * Co-managed releases `v1.1` - `v1.4` (4 releases) on GitHub with team

* **Documentation**:
  * User Guide: Added documentation for the following features and enhancements:
    * The undo command.
    * Possibility of adding a person with missing information.
    * Quick edit by clicking a person card.
    * The tighter checks on email, phone number, and address.
  * Developer Guide:
    * Quick edit manual check procedure.

* **Community**:
  * PR reviewed: [\#61](https://github.com/AY2223S1-CS2103T-T08-3/tp/pull/61#pullrequestreview-1143297379)

<!-- * _{you can add/remove categories in the list above}_ -->
