---
layout: page
title: Chong Chee Kai Clarence's Project Portfolio Page
---

### Project: SoConnect

SoConnect is a **contact management and task management desktop app** that aims to help NUS SoC students stay better connected to their school life, both in terms of social connections and school tasks. To better cater to NUS SoC students, SoConnect is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). SoConnect utilises JavaFx for its user interface and most of the codes are written in Java, which takes up 42kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=chongcheekaiclarence&breakdown=true)
* **Enhancements implemented**:
  * **Tag Create Feature**: Added the ability to create a tag to the tagList.
      * What it does: allows the user to create a tag in the tagList, so it can be used to tag contacts and todos.
      * Justification: This feature allows categorisation of contacts and tasks, so it is easier to search for things related to the tag.
      * Highlights: This enhancement affects `tag add`, `tag delete` and `tag edit` commands. It required an in-depth analysis of design alternatives. The implementation was challenging as it required changes to `add` and `edit` commands.
  * **Tag Add Feature**: Added the ability to add a tag to a contact.
      * What it does: allows the user to add a tag to a contact.
      * Justification: This feature allows linking between the tag and the contact.
      * Highlights: This enhancement affects `tag edit`, `tag delete` and `tag remove` commands. It required an in-depth analysis of design alternatives. The implementation was challenging as it required changes to `add` and `edit` commands.
  * **Tag Edit Feature**: Added the ability to edit a tag.
      * What it does: allows the user to edit a tag in the tagList so that we can change the tag in the contacts.
      * Justification: This feature allows tag-naming mistakes to be fixed.
      * Highlights: This enhancement affects `tag create` and `tag delete` commands. It required an in-depth analysis of design alternatives. The implementation was challenging as it required changes to `edit` command.
  * **Tag Remove Feature**: Added the ability to remove a tag from a contact.
      * What it does: allows the user to remove a tag from a contact.
      * Justification: This feature allows de-linking of a tag from a contact.
      * Highlights: Highlights: This enhancement affects `tag edit` command. It required an in-depth analysis of design alternatives. The implementation was challenging as it required changes to `edit` command.
  * **Tag Delete Feature**: Added the ability to delete a tag.
      * What it does: allows the user to delete a tag from the tagList.
      * Justification: This feature allows removal of the tag to prevent accidental access of unused tags.
      * Highlights: This enhancement affects `tag edit` and `tag remove` commands. It required an in-depth analysis of design alternatives. The implementation was challenging as it required changes to `edit` command.
* **Contributions to documentation**:
    * User Guide:
        * Added documentation for the features `tag create`, `tag edit`, `tag add`, `tag remove` and `tag delete`.
    * Developer Guide:
        * Added documentation of implementation of `tag add` and `tag edit` features.
        * Added UML diagram for `tag add` and `tag edit` features.
* **Contributions to team-based tasks**:
    * In charge of integration.
    * Set up Github project board.
* **Review/mentoring contributions**:
    * Link to PRs reviewed: [PRs reviewed link](https://github.com/AY2223S1-CS2103T-W15-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3AChongCheeKaiClarence)
* **Contributions beyond the project team**:
    * Evidence of helping others:
        * Bug reports submitted during dry run of practical exam: [PED link](https://github.com/ChongCheeKaiClarence/ped/issues)
