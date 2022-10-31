---
layout: page
title: Elijah Seah's Project Portfolio Page
---

### Project: Mass Linkers

Mass Linkers is a powerful Desktop application tool that provides a centralised platform for Computer Science (CS) students to find study support from batchmates with common modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

#### New Feature: Find Interest
* What it does: Allows students to find others by their interests.
* Justification: Provides a way for students find others with the same interests as them.
* Highlights:
  * Implemented `StudentContainsInterestPredicate` to test if the entered interests can be found in a student's interest.
  * Implemented `FindInterestCommand` which handles the testing of the predicate on every student.
  * Implemented `FindInterestCommandParser` to parse `findInt` command arguments.
  * Wrote tests for `FindInterestCommandTest`.

#### New Feature: Delete Interest
* What it does: Allows students to delete interests from a specified student.
* Justification: It is an easier alternative to editing the set of interests instead.
* Highlights:
  * Implemented `DeleteInterestCommand` which handles the deletion of the entered interests.
  * Implemented `DeleteInterestCommandParser` to parse `deleteInt` command arguments.
  * Wrote tests for `DeleteInterestCommandTest`.

#### New Feature: Delete Mod
* What it does: Allows students to delete modules from a specified student.
* Justification: Allows students to remove erroneous entries from their list of modules.
* Highlights:
  * Implemented `ModDeleteCommand` which handles the deletion of the entered modules.
  * Wrote tests for `ModDeleteCommandTest`.

#### New Feature: Input checking for phone numbers
* What it does: Warns the student if the entered phone number is of an incorrect format.
* Justification: Alerts the student if they have typed it wrong by accident, rather than blocking it outright.
* Highlights:
  * Used regex to handle the checking.
  * Added a warning when an incorrect format is entered.
  * Wrote tests to `PhoneTest`.


#### Code contributed: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=elijahs67&breakdown=true)

#### Project management:
* Review and approve PRs on Github.
* Published v1.3.trial and the initial v1.3.1 releases.

#### Enhancements to existing features:
* Improved phone input checking.
* Refactored `tag` to `interest`.

#### Documentation:
* User Guide:
    * Added the `findInt`, `deleteInt` and `mod delete` sections.
    * Worked on the Parameter Requirements section.
    * Proofread user guide for bugs.
* Developer Guide:
    * Designed sequence diagram and activity diagram for the command `findInt`.
    * Updated Product Scope, Value Proposition, User Stories as well as some use cases.
    * Worked on NFR section.
    * Proofread developer guide for bugs.

#### Community:
* Reviewed and approved PRs on Github.
* Contributed in weekly team meeting.
* Reported [bugs](https://github.com/ElijahS67/ped/issues) for team Waddle, another team in this module.

#### Tools:
* PlantUML: Software to create UML diagrams
* Git workflow
