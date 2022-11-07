---
layout: page
title: Lau Rui Han's Project Portfolio Page
---


### Project: PennyWise

PennyWise is a desktop application that **empowers students with the ability to make sensible financial decisions**,helping students to manage their budget. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

The following are my contributions to the project:

* **New Feature**: Added the ability to summarise financial entries. PRs: [\36](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/36), [\#89](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/89), [\#137](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/137)
    * What it does: `summary` allow users to summarise all financial entries or monthly entries and obtain total expenses, income and balance
    * Justification: This feature allows users to view their current budget balance and the amount they have spent/earned in the time period
  
* **Feature**: Edited the `Tag` class to have a set of valid tags and limit the number of tags assigned to each entry: PRs [\#63](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/63), [\#105](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/105)
    * What it does: Allow users to a maximum of one tag to each entry and entry tags must match one of the valid tags with income and expenditure having different valid tags
    * Justification: Having one tag to each entry makes simplifies the categorical analysis as it is reliant on the tags to create the pie chart. Having a set of valid tags ensures that users do not add too many tags and diminish the utility that a categorical analysis would provide.
    * Highlights : The implementation made use of Enums and ensure that any additional tag can be added in one step.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ruihan00&breakdown=true)

* **Project management**:
    * Co-managed releases `v1.2` - `v1.4` (3 releases) on GitHub with @jasonyapzx
    * Track weekly task and ensure that the tasks are done on time
    * [PRs Reviewed by me](https://github.com/AY2223S1-CS2103T-W17-2/tp/pulls?page=1&q=is%3Apr+reviewed-by%3ARuihan00)

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 42.32% to 50.25% (+7.93%), 56.66% to 58.92% (+2.25%), 58.28% to 62.80% (+4.52%), 62.80% to 64.62% (+1.81%) and 67.35% to 68.60% (+1.24%). PRs: [\#42](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/42), [\#63](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/63),[\#78](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/78), [\#85](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/85), [\#91](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/91)
    * Removed traces of AB3 code that are not used. PRs: [\#77](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/77/)
    * Standardise naming conventions across the codebase. PRs: [\#94](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/94), [\#96](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/96), [\#109](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/109)

* **Documentation**:
    * User Guide:
        * Added documentation for summary command. PRs: [\#46](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/46)
        * Refined language use in the user guide
    * Developer Guide:
        * Added implementation details of the `summary` feature. PRs: [\#74](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/74), [\#167](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/167)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#33](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/33),[\#45](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/45),[\#70](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/70), [\#90](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/90)
    * Reported bugs and suggestions for other teams in the class [PE-D Issues Link](https://github.com/Ruihan00/ped/issues)
