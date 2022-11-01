---
layout: page
title: Joanna's Project Portfolio Page
---

### Project: Pupilist

#### Overview

Pupilist is an all-in-one app that allows teachers to plan and organize their student’s workflow, helping them ease their workload. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

#### Summary of Contributions

* **New Feature**: Added the ability to add homework. (Pull request [\#44](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/44))
    * What it does: allows the user to add a homework item to an existing list of homework belonging to a student.
    * Justification: This feature is required to help our target audience, tutors, keep track of what they have assigned to their student easily.
    * Highlights: This enhancement required an in-depth analysis of design alternatives. While there were similar existing commands, none of them used a mutable list of individual items, since this was the first list-type field to be implemented. Implementing this feature required analyzing existing commands and modifying them heavily.

* **New Feature**: Completed adding the ability to view individual person cards. (Pull request [\#87](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/87))
    * What it does: allows the user to view the full details of a person card.
    * Justification: This feature allows the list mode of the app to display truncated details of a contact, and view the full details of the contact only if needed. This prevents the list from becoming too long and prevents visual clutter.
    * Highlights: This feature was partially implemented by a groupmate, but was incomplete and not feasible to use for the final product. Completing the feature required analyzing the design of the product and understanding how the model is maintained to smoothly integrate this feature into the app while preserving the existing design and structure.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cfyjoanna&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=cfyjoanna&tabRepo=AY2223S1-CS2103T-W09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Oversaw branch protection rules and CI on GitHub

* **Enhancements to existing features**:
  * Updated edit command format to work for list-type fields and removed required person index from original implementation (Pull requests [\#68](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/68), [\#109](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/109))
  * Wrote additional tests for existing features (Pull requests [\#86](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/86), [\#184](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/184))

* **Documentation**:
  * User Guide:
    * Ensuring all commands and their usages are correct and consistent [\#114](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/114), [\#128](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/128)
  * Developer Guide:
    * Added non-functional requirements and use case for `hw` feature [\#32](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/32)
    * Added implementation details of the `hw` feature. [\#96](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/96)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#54](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/54), [\#70](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/70), [\#90](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/90), [\#101](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/101), [\#102](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/102), [\#104](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/104), [\#107](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/107), [\#110](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/110), [\#191](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/191)
  * Reported bugs and suggestions for other teams in the class ([PE-D](https://github.com/cfyjoanna/ped/issues))

* **Tools**:
  * Integrated CodeCov to the project
