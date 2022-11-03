---
layout: page
title: Emily Ong's Project Portfolio Page
---

### Project: PennyWise

PennyWise is a desktop application that **empowers students with the ability to make informed financial decisions**, by providing a graphical analysis of their financial activities.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

The following are my contributions to the project:

* **New Feature**: Created the `EntryType` class (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/33) to encapsulate the `Expenditure` and `Income` entry types in the application.
  * What it does: Supports validation of the entry types for user command inputs, and creates an immutable type to specify the type property of an entry in the application.
* 
* **New Feature**: Created the `GraphConfiguration` class (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/81) to encapsulate the configurations required for the graph coupled with an executing command in the application.
    * What it does: Improves code re-usability and organisation by allowing configurations required for the graphical display in the application to be encapsulated in a single object.

* **Feature**: Added the ability to view entries in the application (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/33, https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/81)
  * What it does: `view` allows the user to list the expenditure or income entries in the application, and additionally supports filtering of the entries by month.

* **Feature**: Improved the styling of the application (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/88)
  * What it does: Enhanced the look-and-feel of the application by applying color themes suggested from [@sharmaine1028](https://github.com/sharmaine1028) and applied modern design techniques, such as shadowing and font-typing.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=EmilyOng&breakdown=true)

* **Project management**:
    * [PRs Reviewed by me](https://github.com/AY2223S1-CS2103T-W17-2/tp/pulls?page=1&q=is%3Apr+reviewed-by%3AEmilyOng)

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage (examples: [by 1.38%](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/55), [by 0.67%](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/161))
    * Ensured code quality throughout the codebase by standardising JavaDocs and removing dead code (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/95)

* **Documentation**:
    * User Guide:
        * Improved documentation with more user-friendly texts (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/104)
        * Added screenshots for all commands and user interface (after an initial work by [@JasonYapzx](https://github.com/JasonYapzx))  (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/104)
    * Developer Guide:
        * Added sequence diagram for `edit` command (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/82)
        * Added activity diagram for `view` command (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/82)
        * Added non-functional requirements of the application (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/23)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#105](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/105), [\#90](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/90), [\#42](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/42)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/57#issuecomment-1225512329))
    * Reported bugs and suggestions for other teams in the class: [PE-D Issues Link](https://github.com/EmilyOng/ped/issues)
