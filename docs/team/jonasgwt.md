---
layout: page
title: Jonas Goh's Project Portfolio Page
---

### Project: Mass Linkers

Mass Linkers is a powerful Desktop application tool that provides a centralised platform for Computer Science (CS) students to find study support from batchmates with common modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

#### New Feature: Add modules to a batchmate
* What it does: Add modules inputted by the user to a batchmate.
* Justification: It is a crucial core feature.
* Highlights: 
  * Add a `ModCommandParser` class to parse all mod commands, an abstract class `ModCommand` and a class `ModAddCommand` that extends `ModCommand`. 
  * Modify the `add` command to also accept mods with the prefix `m/`. 
  * Modify the `edit` command to throw an exception when a user attempts to edit the mods. 
  * Add and update tests.

#### New Feature: Module Categorisation
* What it does: Categorises a mod using the first 2 characters of the module name.
* Justification: It allows users to quickly identify the type of mod when looking at the mod panel.
* Highlights:
    * Add a method to categorise mods in `ParserUtil`.
    * Wrote tests for mod categorisation in `ParserUtil`. 

#### New Enhancement: Sorting of modules
* What it does: Sorts mods by their completion (taking in front) and then by their `modName` in lexicographic order.
* Justification: Bring greater convenience for the user when looking for mods via the mod panel.
* Highlights:
    * Add a new `ModComparator` class to compare 2 mods.
    * Change `Person` to return a `SortedList`.
    * Add tests for `ModComparator`.

#### New Enhancement: Input validation for GitHub and Telegram fields
* What it does: Ensure that the GitHub username and Telegram handle provided by the user is valid.
* Justification: Having an invalid GitHub username or Telegram handle would result in the user not being able to contact that person.
* Highlights:
    * Used regex to check for input validity for GitHub and Telegram.
    * Edit warning message for invalid input.
    * Add tests to `isValidGitHub` and `isValidTelegram`.

#### Code contributed: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jonasgwt&breakdown=true)

#### Project management:
* Added several issues for the team on GitHub.
* Set up Codecov in team repository.
* Update release of v1.3.1.


#### Documentation:
* User Guide:
    * Worked on Module Categorisation section.
    * Proofread user guide for bugs.
* Developer Guide:
    * Worked on Instructions on Manual Testing section.
    * Design diagrams and writeup for Mod Categorisation.
    * Worked on NFR section.
    * Proofread developer guide for bugs.

#### Community:
* PRs reviewed (with non-trivial review comments): [\#64](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/64), [\#100](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/100), [\#141](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/141)
* Contributed in weekly team meeting.
* Reported [bugs and suggestions](https://github.com/jonasgwt/ped/issues) for team Friendnancial, another team in the module.

#### Tools:
* PlantUML: Software to create UML diagrams
* Git workflow

