---
layout: page
title: Yap Zher Xiang Jason's Project Portfolio Page
---

### Project: PennyWise

PennyWise is a desktop application that **empowers students with the ability to make sensible financial decisions**,helping students to manage their budget. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

The following are my contributions to the project:

* **New Feature**: Created the class `Entry` to manage objects being added to the application: PRs [\#30](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/30), [\#35](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/35)
  * What it does: Allows both the `Expenditure` and `Income` classes to inherit from this class as they have similar properties.
  * Justification: This feature enhances our code reusability since they have similar properties, functions can largely be
  created for `Entry` type classes by taking advantage of polymorphism.
  * Highlights: The implementation required many rounds of alteration because it was the initial feature to be added to the application,
  and the parent `Entry` class formed the foundation for our application to be built on.

* **Feature**: Added the ability to add entries to the application: PRs [\#30](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/30)
  * What it does: `add` allows the users to create entries in either the `ExpenditureList` or `IncomeList` in PennyWise.
  * Justification: This feature is an essential component of PennyWise as the entries already added to the application
  can then be further used for other PennyWise features.

* **Feature**: Added the ability to edit entries to the application: PRs [\#43](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/43), [\#64](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/64)
  * What it does: `edit` allows the users to edit entries in either the `ExpenditureList` or `IncomeList` in PennyWise.
  * Justification: This feature is an essential component of PennyWise as the entries already added to the application
    can be edited should the users make any mistakes in typing the details of their entries.

* **Feature**: Added the ability to delete entries to the application: PRs [\#35](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/35)
  * What it does: `delete` allows the users to delete entries in either the `ExpenditureList` or `IncomeList` in PennyWise.
  * Justification: This feature is an essential component of PennyWise as the entries already added to the application
    can be deleted should the users make any mistakes in entering an expected entry that did not happen, or should the
    user want to completely remove any erroneous entry that they have made.

* **Feature**: Added the ability for users to find out the commands available in PennyWise: PRs [\#106](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/106)
  * What it does: `help` opens a new window with a table of PennyWise's commands and a link to our user guide.
  * Justification: This feature provides an easy way to view the available commands that can be used in the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=JasonYapzx&breakdown=true)

* **Project management**:
  * Co-managed releases `v1.2` and `v1.3` (2 releases) on GitHub together with @ruihan00.
  * Logging of weekly tasks, deadlines and responsibilities on our [project notes](https://docs.google.com/document/d/1Mrw3zaZRBr7LHu4YoR7R4CeEvzR8Gmlznl0NBMUvOWA/edit?usp=sharing).
  * [PRs Reviewed by me](https://github.com/AY2223S1-CS2103T-W17-2/tp/pulls?page=1&q=is%3Apr+reviewed-by%3AJasonYapzx)

* **Enhancements to existing features**:
  * Removed traces of AB3 code (`Person`, `addressbook`) in the documentation and code base. PRs: [\#35](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/35), [\#40](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/40), [\70](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/70) 
  * Wrote additional tests for existing features to increase coverage from 64.86% to 67.35% PRs: [\#86](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/86)
  * Refactoring of JavaFX CSS to allow for a responsive application if the window size of the application was changed. [\152](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/152)

* **Documentation**:
  * User Guide:
    * Added documentation for all features, command format and command summary. PRs: [\#90](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/90)
    * Added screenshots for all features for User Guide Peer review (before cleanup by @EmilyOng). PRs: [\97](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/97)
  * Developer Guide:
    * Added implementation details of the `add` feature. PRs: [\70](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/70)
    * Added all use cases for PennyWise into the DG. PRs: [\70](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/70)
    * Added test cases for `add`, `delete`, `edit`, `view`, `summary` into the DG. PRs: [\70](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/70), [\76](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/76) 

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#34](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/34), [\#71](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/71), [\#81](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/81), [\#104](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/104)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/212#issuecomment-1248864789), [2](https://github.com/nus-cs2103-AY2223S1/forum/issues/227#issuecomment-1249389582))
  * Reported bugs and suggestions for other teams in the class: [PE-D Issues Link](https://github.com/JasonYapzx/ped/issues)
