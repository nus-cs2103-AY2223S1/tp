---
layout: page
title: Zheng Jiarui's Project Portfolio Page
---

### Project: Mass Linkers

Mass Linkers is a powerful Desktop application tool that provides a centralised platform for Computer Science (CS) students to find study support from batchmates with common modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

#### New Enhancement: Upgrade help window
* What it does:
  * Shows a brief summary of commands with their syntax and a link to the user guide. 
  * Opens the user guide directly in the browser with a single button click.
* Justification:
  * Provides flexibility for students to choose between shortcuts or the long detailed user guide based on their needs.
  * The short summary allows students to refer to all available commands more quickly and easily.
  * Provides a hassle-free process when students seek help and request for user guide. Upon clicking on the `Open User Guide` button, students will be automatically redirected to the user guide in the browser without the need to manually copy the URL and open a browser window to paste it.
* Highlights:
  * Updated the link to Mass Linkers' user guide in `HelpWindow`.
  * Added a command summary as shortcuts for easier reference in `HelpWindow`.
  * Replaced the original `Copy URL` button in `HelpWindow` with an `Open User Guide` button.
  * Updated the `.fxml` file of `HelpWindow` to reflect design changes.

#### New Feature: Mark module as taken
* What it does: Marks the specified module(s) of a batchmate as taken, which means the batchmate has taken the module(s) before.
* Justification: 
  * It is a crucial core feature of Mass Linkers to allow students to track the module status of their batchmates.
  * Provides the flexibility of marking one or multiple modules as taken in a single command.
* Highlights:
  * Implemented `ModMarkCommand` which handles marking module(s) of the specified batchmate as taken.
  * Modified `ModCommandParser` to parse `mod mark` command arguments.
  * Modified `Mod` to include a new parameter `boolean hasTaken` using method overloading to track module status, and methods like `markMod` and `getModStatus` to facilitate the marking of a module.
  * Modified `Student` to include a new method `markMods` to facilitate the marking of one or multiple specified modules of a batchmate.
  * Wrote tests in `ModMarkCommandTest` for `ModMarkCommand`. 
  * Added `mod delete` and `mod mark` parser tests in `ModCommandParserTest`.

#### New Feature: Unmark module as not taken
* What it does: Unmarks the specified module(s) of a batchmate as not taken, which means the batchmate is currently taking the module(s).
* Justification:
  * Allows students to rectify mistakes made in recording the module status of a batchmate.
  * Provides the flexibility of unmarking one or multiple modules as not taken in a single command.
* Highlights:
  * Implemented `ModUnmarkCommand` which handles marking module(s) of the specified batchmate as taken.
  * Modified `ModCommandParser` to parse `mod unmark` command arguments.
  * Modified `Mod` to include `unmarkMod` method to facilitate the unmarking of a module.
  * Modified `Student` to include a new method `unmarkMods` to facilitate the unmarking of one or multiple specified modules of a batchmate.
  * Wrote tests in `ModUnmarkCommandTest` for `ModUnmarkCommand`.
  * Added `mod unmark` parser tests in `ModCommandParserTest`.

#### New Feature: Mark all modules as taken
* What it does: Marks all modules of every batchmate as taken.
* Justification:
  * It is useful for updating the module status of all modules of every batchmate after each semester.
* Highlights:
  * Implemented `ModMarkAllCommand` which handles marking all modules of every batchmate as taken.
  * Modified `ModCommandParser` to parse `mod mark all` command arguments.
  * Modified `Student` to include a new method `markAllMods` to facilitate the marking of all modules of a batchmate.
  * Wrote tests in `ModMarkAllCommandTest` for `ModMarkAllCommand`.

#### Code contributed: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=carriezhengjr&breakdown=true)

#### Project management
* Added weekly Issues for the team on GitHub.
* Facilitated the workflow on Git (PR and Issues categorisation).

#### Documentation
* **User Guide**:
  * Wrote and formatted most of the content in user guide.
  * Proofread user guide for bugs.
* **Developer Guide**:
  * Design diagrams and writeup for `Mod Mark` feature.
  * Proofread developer guide for bugs.

#### Community:
* Reviewed other teammate's PRs.
* Contributed in weekly team meeting.
* Submitted [bugs and feedback](https://github.com/carriezhengjr/ped/issues) for [ArtBuddy](https://github.com/AY2223S1-CS2103T-W11-3/tp), a project done by another team in the CS2103T module.

#### Tools:
* JavaFX: Platform which Mass Linkers' UI uses
* PlantUML: Software to create UML diagrams
* Git workflow
