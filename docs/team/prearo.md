---
layout: page
title: prearo's Project Portfolio Page
---

### Project: NUSEatWhere

NUSEatWhere is a Command Line (CLI) application which helps you search for the available food options in NUS and thus make an informed decision on where to eat.

Given below are my contributions to the project.

**New Feature**: Added the ability to view individual command help ([#53](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/53))
* What it does: allows the user to view how to use the commands by means of the flag `-h`.
* Justification: This quality-of-life feature adds yet another way to obtain help within the application.
The user need not always open the help window or go to the user guide, both of which are more invasive and cumbersome to work with.
* Highlights: This change affects existing and future commands. The implementation involved a custom exception to handle 
the short-cutting of command execution. This was done to avoid the need to create multiple constructors for every single command,
which would burden developers greatly.

**Refactoring**:
* Changed the `json` format to suit the food guide.
* Overhauled sample test data used by different `Command` tests.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=Prearo&tabRepo=AY2223S1-CS2103T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Project management**:
* Managed releases for `v1.2`, `v1.2.1`, and `v1.3`

**Enhancements to existing features**:
* Miscellaneous initial refactoring of files under `ui` and `AddCommand`.
* Updated the help window to be more useful, containing a summary of commands and their syntax in addition to a link to the user guide.
* Added tests for `FindCommand`, `FindLocationCommand`, `FindTagCommand`, `FindPriceCommand`
([#157](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/157)).
* Added tests for existing commands for the individual command help feature.

**Documentation**:
* User Guide: 
  * Added implementation details for `exit`, `edit`, and `clear`.
  * Grouped commands into different groups based on their type and purpose.
  * Expanded upon the FAQ section.
  * Miscellaneous grammatical and expression improvements for clarity.

* Developer Guide:
  * Added implementation details for handling displaying individual command help, and a diagram for
  the processing of commands.
  * Initial update of PlantUML diagrams to change class names.

**Community and Team Help**:
* As owner of the GitHub team organisation and repo, handled project administration and set-up.
* PRs reviewed/commented on (non-exhaustive):
[#42](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/42),
[#147](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/147),
[#148](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/148)
* Assisted the team for issues with setting up Git and project workflows.

