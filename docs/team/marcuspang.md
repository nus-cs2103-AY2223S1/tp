---
layout: page
title: Marcus Pang's Project Portfolio Page
---

### Project: TruthTable

TruthTable is a task-management software specially targeted towards tech-savvy university students leading teams in
software engineering modules to build projects. It helps track the team's progress and delegate tasks effectively.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=marcuspang&tabRepo=AY2223S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Feature**: Convert command syntax to use flags with dashes (`-`, `--`) and empty spaces instead of underscores (`_`). 
(PR [#75](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/75))
    * What it does: Simplifies command syntax and makes the commands more familiar to CLI users, as it is designed after common CLI
    applications like [git](https://git-scm.com/) and [docker](https://docs.docker.com/engine/reference/commandline/cli/).
    * Justification: This feature is critical as this greatly improves the user experience for CLI users, especially those 
    who are not familiar with the previous syntax where underscores were used.
    * Highlights: Implementing this feature required deep understanding of libraries available that support POSIX-like 
    command syntax. This necessitated time dedicated to research which library could support our implementations, and we 
    settled on using [picocli](https://picocli.info/).
* **New Feature**: Add, delete, list members of a team. (PR [#21](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/21),
[#33](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/33), [#34](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/34),
[#88](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/88))
    * What it does: Allows the user to add, delete, list members to their currently selected team. It also stores the member
    data in the team automatically in a local file.
    * Justification: This feature is vital to TruthTable, as it is a core part of the product - creating and managing
    members in their teams.
    * Highlights: Implementing this feature required thorough understanding of how commands are executed in the program. 
    It also required knowledge on how the `Storage` class works.
* **New Feature**: Command and flag aliasing, i.e. alternate names for commands and flags. (PR 
[#107](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/107))
    * What it does: Allows the user to specify alternative names to carry out the same command. This allows experienced 
      users to enter the commands blazingly fast. For flags, we created short and long versions of flags (represented
      with `-` and `--` respectively), removing the need for users to type out the long version of flags every single time.
    * Justification: This feature is important for users as it allows them to enter commands and flags extremely quickly, 
      as the full commands become extremely tedious to type out very quickly. This feature is also important in maximising 
      productivity for users of varying experience level with CLI programs.
    * Highlights: This feature required a deep understanding of the [picocli](https://picocli.info/) documentation and 
      methods, as there were many areas that needed to be changed: help message, standardising flags, flag and parameter 
      descriptions, aliases for commands and subcommands, etc.
  
* **Project management**:
    * Converted user stories to GitHub issues for `v1.3`.
    * Consolidated UG bugs after PE dry run on GitHub.
    * Managed the release of `v1.2` on GitHub.
    * Delegated tasks for the team after finalising on changes to implement for `v1.3`.

* **Enhancements to existing features**:
    * Standardised usage message and error messages for all commands (PR [#108](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/108))
    * Added `-h` and `--help` flags to all commands (PR [#108](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/108))

* **Documentation**:
    * User Guide (PR [#175](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/108), [#207](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/207)):
        * Added product introduction and key features
        * Added a section on how to use the user guide
        * Added sections detailing the different parts of the user interface
        * Added a section to explain TruthTable's CLI and command syntax
        * Standardised command, parameter, and flag syntax for all commands
        * Added command summary sections for each group of commands
        * Added parameter constraints section
        * Updated glossary section
        * Improved navigability by adding "Back to Table of Contents" links in sections
    * Developer Guide:
        * Added explanations for `list_members` and `add_member` commands [PR #85](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/85)

* **Testing**
    * Bugs reported
      * [Repeated options not working as intended](https://github.com/AY2223S1-CS2103T-W13-4/tp/issues/194)
      * [Deletion of `Person` does not remove references of the deleted person](https://github.com/AY2223S1-CS2103T-W13-4/tp/issues/104)
    * Unit tests for features implemented
      * [`add_member` command](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/21)
      * [`list_members` command](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/33)
      * [`delete_member` command](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/34)

* **Contributions Beyond Project Team**:
    * PRs reviewed (with non-trivial review comments): Reviewed 42 PRs.
    * Reported 5 bugs and suggestions for other teams in the class (examples can be found 
[here](https://github.com/marcuspang/ped/issues)).

