---
layout: page
title: Hong Ker Yen Elizabeth's Project Portfolio Page
---

### Project: PetCode

PetCode is a software app that aims to facilitate better working experience and boost business management efficiency 
for pet sale coordinators. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written 
in Java, and has about 30 kLoC.

Given below are my contributions to the project.

* **New Feature**: Extended the Delete Command to three categories: Delete Buyer Command, Delete Supplier Command and Delete Deliverer Command.
    * What it does: allows the user to delete different types of contacts at the specified index.
    * Justification: This allows the user to delete contacts that have become outdated.
    * Credits: The code was inspired by the original code given in AB3.

* **New Feature**: Extended the Delete Command for two more categories: Delete Order Command and Delete Pet Command.
    * What it does: allows the user to delete an order or pet at the specified index.
    * Justification: This allows the user to delete orders that have been completed and pets that are no longer available for sale.
    * Credits: The code was inspired by the original code given in AB3.

* **Functionality**: Added the classes `UniqueOrderIdPredicate` and `UniquePetIdPredicate`.
  * What it does: these classes increases testability when testing for unique Orders and unique Pets.
  * Justification: Orders and Pets can easily be distinguished by their UniqueId. Hence, when testing for a unique Order or Pet, their UniqueId can be used.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=elizabethhky&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Kept track and notified teammates of important deadlines. 
    * Updated demo screenshots for each version release.

* **Enhancements to existing features**:
    * Wrote additional tests for existing features and json files to increase coverage by 6.10% (Pull requests [#140](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/140)).

* **Documentation**:
    * User Guide:
        * Added an introduction section to provide an overview of our application to new users: [#282](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/282/files), [#315](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/315)
        * Added documentation for the feature `delete`: [#191](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/191/files)
        * Added a table of contents for easier navigability: [#191](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/191/files)
        * Did cosmetic tweaks and proofread the entire user guide to check for typos and consistent tone: [#191](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/191/files), [#285](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/285/files)
    * Developer Guide:
        * Updated the `Model` Component: [#215](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/215/files)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#137](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/137)
    * Contributed to forum discussions (examples: [#157](https://github.com/nus-cs2103-AY2223S1/forum/issues/157).

* **Tools**:
    * Used PlantUML to add more UML diagrams in the developer guide.
