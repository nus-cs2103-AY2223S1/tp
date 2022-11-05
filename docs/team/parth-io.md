---
layout: page
title: Parth's Project Portfolio Page
---

### Project: BookFace

BookFace replaces a paper-based system or manual tracking of books and patrons, providing greater speed/efficiency to librarians. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 12.6 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to extend commands more easily and handle parsing better (including subcommand handling) [\#97](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/97)
  * What it does: Improves command parsing by creating more separate parsing of commands and arguments.
  * Justification: The parsing of commands was through a three-step process - processing the first command word, then the arguments, and constructing the `Command` instance. The distinction between commands and arguments was not clear. There was a lot of code duplication in the parsers as well, which was handled by using enums to associate each parse function with each possible command from the user and by making `CommandParser` abstract with a shared parse function.
  * Highlights: The command structure is recursive, allowing for easy extensibility with the idea of a subcommand contained within the user's inputted command.
  * Credits: *The idea of using enums was taken from the iP project, where the students were encouraged to use enums. The idea of forcing all subclasses to use the parse method implemented in the abstract class was inspired by an answer on StackOverflow (see [Acknowledgements](https://ay2223s1-cs2103-f14-4.github.io/tp/DeveloperGuide.html#acknowledgements)).*

* **New Feature**: Added `find book` feature. [\#118](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/118)
  * Added helper util methods to improve search for keywords, ignoring case.

* **New Feature**: Added `edit book` feature. [\#154](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/154)

* **New Feature**: Added `return` feature. [\#119](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/119)

* **Enhancements to existing features**:
  * Converted `add user`, `list user`, and `delete user` to the new parsing mechanism. [\#97](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/97)
  * Converted `find user` to the new parsing mechanism. [\#118](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/118)
  * Converted `edit user` to the new parsing mechanism. [\#154](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/154)
    * Highlights: As an edited book can be currently on loan, edits to either `Person` or `Book` had to be reflected in the other
  * Improved `loan` and `return` commands' logic [\#119](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/119)
    * What it does: Improved `Book` fields and methods, and enhances serialisation `Persons` with loaned `Books` to the JSON format
  * Improved error messages by generating them using information from the command itself [\#156](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/156/commits/cdc96cba547bf657fa29ad1c151e220d11a37e8f)
    * Justification: The error messages were written by hand, leading to incorrect error messages if the logic for the command was updated. The error message generation also tied in with the subcommand handling, with error messages for subcommands nested within the main command.
  * Updated `clear all` command, updated the `help` window with a copy button, fixed `help` window display issue ([\#156](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/156/commits/d3f0d44d28a30ff291d9f84297e9dc859495a53f))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=parth-io&breakdown=true)

* **Project management**:
  * Created the team repo, set up CodeCov, add user stories as issues.
  * Helped review and merge PRs on a timely basis (even after they have been merged, for example [\#160](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/160))
  * Proposed design suggestions in PR reviews
  * Created v1.3, v1.4, v1.4b milestones and the project board

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add user`, `delete user`, `delete book`, `edit book`, and `edit user` [\#64](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/64), [\#166](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/166)
  * Developer Guide:
    * Added the Non-Functional requirements [\#85](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/85)
    * Did cosmetic tweaks for the Glossary table [\#92](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/92)
    * Added an explanation on the design of command parsing, the implementation details of the `delete` feature, and a code-reuse table [\#143](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/143)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#94](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/94), [\#115](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/115), [\#152](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/152), [\#159](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/159), [\#164](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/164), [\#123](https://github.com/AY2223S1-CS2103-F14-4/tp/pull/123)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/179#issuecomment-1244117101), [2](https://github.com/nus-cs2103-AY2223S1/forum/issues/217#issuecomment-1249031907), [3](https://github.com/nus-cs2103-AY2223S1/forum/issues/217#issuecomment-1249045744), [4](https://github.com/nus-cs2103-AY2223S1/forum/issues/217#issuecomment-1249086809))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2223S1-CS2103T-W16-3/tp/issues/173), [2](https://github.com/parth-io/ped/issues/2), [3](https://github.com/AY2223S1-CS2103T-W16-3/tp/issues/158), [4](https://github.com/AY2223S1-CS2103T-W16-3/tp/issues/161))
