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
  * Justification:
    * The application treats expenditure and income entries similarly by making use of an `Entry` class (by [@jasonyapzx](https://github.com/jasonyapzx)) to take advantage of polymorphism.
    * In order to simplify the set of commands for the application and reduce code duplication, we introduced an `EntryType` so that users can indicate an entry type prefix in their command arguments to distinguish an action on an expenditure entry between an action on an income entry.
    * The `EntryType` class supports encapsulation by bundling the validation of entry types for user command inputs, and provides an enumeration to handle entry types throughout the application.
* 
* **New Feature**: Created the `GraphConfiguration` class (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/81) to encapsulate the configurations required for the graph coupled with an executing command in the application.
    * What it does: Improves code re-usability and organisation by allowing configurations required for the graphical display in the application to be encapsulated in a single object.
    * Justification:
      * Graph visualisations are a central part of the application, however controlling graph state can be complex, requiring us to manage several variables such as the entry type of the data in the graph, the type of graph and whether the graph should be updated or not.
      * By encapsulating such properties related to the configuration of the graph, it is easier to pass the graph configuration around the application and ensure that we improve coupling.

* **Feature**: Added the ability to view entries in the application (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/33, https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/81)
  * What it does: `view` allows the user to list the expenditure or income entries in the application, and additionally supports filtering of the entries by month.
  * Highlights:
    * The `view` command has a side effect of updating the data shown in the graph panel, requiring us to take additional work to synchronise the data and maintain a single source of truth for the data in both the list panel and graph panel.
    * The `view` command is complementary to [@sharmaine1028](https://github.com/sharmaine1028) and [@yuanxi1](https://github.com/yuanxi1)'s work in producing visualisations of the users' expenditure and income entries in the application, which is a key feature of PennyWise.
  * Justification:
    * The `view` command is a standard feature that addresses a high priority user story of being able to view the list of expenditure and income entries in the application.
    * By additionally incorporating the display of graphs using the command, it is more convenient for users to obtain an overview of their expenditure and income entries with a one-shot command, without having to add extra clutter by introducing an additional command to display graphs, which can complicate the state management of the data in the list panel and graph panel.

* **Feature**: Improved the styling of the application (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/88)
  * What it does: Enhanced the look-and-feel of the application by applying color themes suggested from [@sharmaine1028](https://github.com/sharmaine1028) and applied modern design techniques, such as shadowing and font-typing.
  * Highlights:
    * The CSS files inherited from [AddressBook Level-3](https://github.com/se-edu/addressbook-level3) was almost completely overhauled.
    * To improve code readability and re-usability, we defined CSS variables in the CSS files, allowing us to easily customise the theme of the application.
  * Justification:
    * User interface is an important part of the application as it is often one of the first judging points from users.

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
        * Improved documentation with more user-friendly texts (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/171)
        * Added an "Effort" section for the team (PRs: https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/173)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#105](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/105), [\#90](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/90), [\#42](https://github.com/AY2223S1-CS2103T-W17-2/tp/pull/42)
    * Contributed to forum discussions (examples: [\#1](https://github.com/nus-cs2103-AY2223S1/forum/issues/57#issuecomment-1225512329))
    * Reported bugs and suggestions for other teams in the class: [PE-D Issues Link](https://github.com/EmilyOng/ped/issues)
