---
layout: page
title: Zheng Jiarui's Project Portfolio Page
---

### Project: Mass Linkers

Mass Linkers is a powerful Desktop application tool that provides a centralised platform for Computer Science (CS) students to find study support from batchmates with common modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

#### New Enhancement: Upgrade help window
* What it does: Shows a brief summary of commands with their syntax and a link to the user guide. Opens the user guide directly in the browser with a single button click.
* Justification: It provides flexibility for students to choose between shortcuts or the long detailed user guide based on their needs.
* Highlights: Updated the user guide link, added a command summary, replaced the original `Copy URL` button with an `Open User Guide` button in `HelpWindow`. Updated the `.fxml` file of `HelpWindow` to reflect design changes. (Pull request [#60](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/60)) 

#### New Feature: Mark module as taken
* What it does: Marks the specified module(s) of a batchmate as `taken`, with the flexibility of marking one or multiple modules in a single command.
* Justification: It is a crucial core feature to track batchmates' module status.
* Highlights: Modified `ModCommandParser` and implemented `ModMarkCommand` to parse and handle the marking of module(s). Modified `Mod` and `Student` to include new methods to facilitate the marking of module(s). Wrote tests in `ModMarkCommandTest` and `ModCommandParserTest` for `mod mark` and `mod delete`. (Pull request [#85](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/85))

#### New Feature: Unmark module as not taken
* What it does: Unmarks the specified module(s) of a batchmate and updates the status as `taking` (i.e. currently taking), with the flexibility of unmarking one or multiple modules in a single command.
* Justification: It allows students to rectify mistakes made in recording the module status of a batchmate.
* Highlights: Modified `ModCommandParser` and implemented `ModUnmarkCommand` to parse and handle the unmarking of module(s). Modified `Mod` and `Student` to include new methods to facilitate the unmarking of module(s). Wrote tests in `ModUnmarkCommandTest` and `ModCommandParserTest` for `mod unmark`. (Pull request [#96](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/96))

#### New Feature: Mark all modules as taken
* What it does: Marks all modules of every batchmate as taken.
* Justification: It is useful for updating the module status of all modules of every batchmate as taken after each semester.
* Highlights: Modified `ModCommandParser` and implemented `ModMarkAllCommand` to parse and handle the marking of all modules of every batchmate. Modified `Student` to include a new method to facilitate the marking of all modules. Wrote tests in `ModMarkAllCommandTest` for `mod mark all`. (Pull request [#145](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/145), [#147](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/147), [#148](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/148), [#152](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/152))

#### Code contributed: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=carriezhengjr&breakdown=true)

#### Project management
* Added issues for the team on GitHub. 
* Facilitated the workflow on Git.

#### Documentation
* **User Guide**:
  * Wrote and formatted most of the content in user guide. (Pull request [#30](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/30), [#71](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/71), [#80](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/80), [#83](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/83), [#115](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/115))
  * Proofread user guide for bugs. (Pull request [#161](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/161), [#169](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/169), [#189](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/189), [#205](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/205), [#208](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/208), [#212](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/212), [#215](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/215), [#242](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/242), [#252](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/252), [#253](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/253), [#256](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/256))
* **Developer Guide**:
  * Designed diagrams and added writeup for [`Mod Mark` Feature](https://ay2223s1-cs2103t-t11-4.github.io/tp/DeveloperGuide.html#2-mod-mark-feature). (Pull request [#99](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/99), [#121](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/121))
  * Proofread developer guide for bugs. (Pull request [#163](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/163), [#169](https://github.com/AY2223S1-CS2103T-T11-4/tp/pull/169))

#### Community:
* Reviewed and approved other teammates' PRs.
* Contributed in weekly team meeting.
* Submitted [bugs and feedback](https://github.com/carriezhengjr/ped/issues) for [ArtBuddy](https://ay2223s1-cs2103t-w11-3.github.io/tp/), another team in the module.

#### Tools:
* JavaFX: Platform which Mass Linkers' help window uses
* PlantUML: Software to create UML diagrams
* Git workflow
