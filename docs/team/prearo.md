---
layout: page
title: prearo's Project Portfolio Page
---

### Project: NUSEatWhere

NUSEatWhere is a Command Line (CLI) application which helps you search for the available food options in NUS and thus make an informed decision on where to eat.

Given below are my contributions to the project.

**New Feature**: Added the ability to view individual command help
* What it does: allows the user to view how to use the commands by means of the flag `-h`.
* Justification: This adds another way to obtain help within the application, which is arguably less troublesome than viewing the user guide.
* Highlights: This change affects existing and future commands. The implementation was a compromise to not burden developers when adding new commands.

**Refactoring**:
* Changed the `json` format to suit the food guide.
* Overhauled sample test data used by different `Command` tests.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=Prearo&tabRepo=AY2223S1-CS2103T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Project management**:
* Owner of the team GitHub repo
* Managed releases for `v1.2`, `v1.2.1`, and `v1.3`

**Enhancements to existing features**:
* Misc. initial refactoring of files under `ui` and `AddCommand`.
* Updated the help window to be more useful, containing a summary of commands and their syntax in addition to a link to the user guide.
* Added tests for `FindCommand`, `FindLocationCommand`, `FindTagCommand`, `FindPriceCommand`
([#157](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/157)).

**Documentation**:
* User Guide: 

* Developer Guide:
  * Added implementation details for handling displaying individual command help.

**Community**:
* PRs reviewed/commented on:
[#42](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/42),
[#147](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/147),
[#148](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/148)
* Assisted the team for issues with Git and project workflows.

