---
layout: page
title: Clifton Felix's Project Portfolio Page
---

### Project: InternConnect

InternConnect is a one-stop, convenient, and efficient platform to manage and empower how internship campus recruiters
who prefer CLI to GUI work with their applicants’ data. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to export displayed list to a JSON file (PR [#135](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/135)).
    * What it does: 
      
      Allows the user to export the filtered displayed list to a JSON file by just typing `export`.
      
    * Justification:
      
      This feature improves the product significantly because a user can export the filtered displayed list to a JSON file 
      and share the JSON file with other users of InternConnect or execute `checkout` on the exported JSON file 
      to focus on the filtered list only without the need to clear the other unimportant data. Together with the ability
      to import JSON file, other users can import the exported JSON file easily.

    * Highlights: 
      
      At first, I created a static method in `Storage` Interface to export the displayed list since I didn't want to 
      pass in `storage` to `Command` in `LogicManager#execute(commandText)`, but since for the implementation of `checkoutCommand` 
      we pass in `storage` to `Command`, I changed the static method to a non-static method in `Storage` Interface that needs
      to be implemented by all classes implementing `Storage` Interface (in this case `StorageManager` class).
      
* **New Fields Added**: Added two fields `Id` and `Title` which are compositions of `Job` (PR [#106](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/106)).
  
    * Now we can specify the values of `Id` and `Title` in `add`, `edit`, `find`, etc using `ji` and `jt` prefixes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cliftonfelix&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=cliftonfelix&tabRepo=AY2223S1-CS2103-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Contributed to issues creation for team members
    * Reviewed and approved team members' PRs
    * Managed milestones and deliverables (including `v1.3` release) on GitHub

* **Enhancements to existing features**:
    * Adjusted `tag` constraints and refined the values in sample data and tests to match new constraints and context (PR [#100](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/100))
    * Refined `exit` and `delete` commands (PR [#115](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/115))
    * Added length limit check for several fields to prevent misuse (PR [#147](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/147))

* **Documentation**:
    * User Guide:
        * Refined documentation for the features `delete` and `exit` (PR [#75](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/75))
        * Added documentation for `export` command (PR [#135](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/135))
    * Developer Guide:
        * Added user profile, value proposition, NFRs, Glossary (PR [#73](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/73))
        * Added user stories (PR [#74](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/74))
        * Added use cases for `delete` and `exit` commands (PR [#79](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/79))
        * Added documentation for `export` command (PR [#153](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/153))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#170](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/170), 
      [#137](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/137), [#110](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/110),
      [#101](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/101), [#99](https://github.com/AY2223S1-CS2103-F14-2/tp/pull/99)
    * Contributed to forum discussions (example: [#166](https://github.com/nus-cs2103-AY2223S1/forum/issues/166))