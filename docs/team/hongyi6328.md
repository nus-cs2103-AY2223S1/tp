---
layout: page
title: Huang Hongyi's Project Portfolio Page
---

### Project: PetCode

PetCode is a software app that aims to facilitate better working experience and boost business management efficiency for pet sale coordinators.

Given below are my contributions to the project.

* **New Feature**: Extended the add person command to three categories: add buyer command, add deliverer command and add
  supplier command.
  * What it does: allows the user to add persons according to their roles.
  * Justification: This feature is the core feature of this app since all other features are based on it. The user
    cannot do further manipulation of data without adding it.
  * Highlights: This enhancement defines the paradigm of operations on contacts. That is, every person category has its
    own command word extension for every command, such as `add-s` and `delete-b`. It guides subsequent implementations
    of other commands.

* **New Feature**: Added the add order command and add pet command.
  * What it does: allows the user to add an order to a buyer and add a pet to a supplier. Even more convenient, the user
    can choose to add as many orders as possible when adding a buyer, or add as many pets as possible when adding a
    supplier.
  * Justification: This helps the user organise his/her business by keeping track of orders placed and pets available
    for sale.
  * Highlights: There are a tremendous number of prefixes and attributes to handle, which was not easy.

* **New Feature**: Added the match command.
  * What it does: allow user to find out which pet for sale is the best fit for an order placed by a buyer.
  * Justification: To maximise utility and profit, the user needs to find out the most suitable pet to sell to a buyer
    according to the buyer's demand.
  * Highlights: The algorithm is a score evaluation algorithm that involves some complex calculations. The weight of
    each field is carefully chosen.

* **Functionality**: Implemented many parser utility methods.
  * What it does: provides convenient utility methods to parse strings to other objects and validate data by a variety
    of rules.
  * Justification: Many commands rely on and make use of these methods for convenience, such as the add commands, the
    edit commands, the find commands and the filter commands.
  * Highlights: There are many constraints to consider. For example, some data cannot be negative, some can only be
    alphanumeric, and some cannot be blank. Multiplicity should be well considered too.

* **Functionality**: Wrote data models.
  * What it does: these data models, such as `Pet`, `Age`, `UniqueOrderList`, and `DateOfBirth` abstract real-world
    objects and provide computational paradigms to mimic their interactions.
  * Justification: Without these data models, it would be extremely troublesome to manipulate data.
  * Highlights: The SOLID principles and OOP were employed thoroughly.

* **Functionality**: Added the unique ID system.
  * What it does: it gives each `Order` and `Pet` object a unique ID, and it is not bounded by the max value of integer.
  * Justification: Initially, `Buyer` has reference to `Order` and `Order` also has reference to `Buyer`. The same
    for `Supplier` and `Pet`. This kind of bidirectional navigation makes it difficult to implement some JSON-related
    classes and methods, since the JSON-adapted date models will infinitely recursively write the references into
    the `.json` file.
  * Highlights: This implementation solves the storage problem, making it for objects to mutually reference each other.

* **Functionality**: Discovered a variety of bugs and fixed them.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=zoom&zA=Hongyi6328&zR=AY2223S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&zACS=215.92310030395137&zS=2022-09-16&zFS=&zU=2022-11-01&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.3(trial)` (2 releases) on GitHub
    * Assigned teammates to different issues and kept track of their progress
    * Maintained and updated the official and unofficial meeting minutes after every meeting
    * Managed milestones, changed their due dates, and closed them to wrap-up.

* **Enhancements to existing features**:
    * Let the GUI window fit different screen sizes (Pull requests [\#296](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/296))
    * Added test cases (the coverage increment is hard to calculate as functionality code was added along with test code.)
    * Deleted useless classes like AddCommandParser and decoupled some classes for higher robustness.

* **Documentation**:
    * User Guide:
        * Adding a buyer
        * Adding a deliverer
        * Adding a supplier
        * Adding a pet to a supplier
        * Adding an order to a buyer
        * Prefix Summary
    * Developer Guide:
        * Target user profile
        * Value proposition
        * User stories
        * Use cases
        * Match command implementation
        * Unique ID implementation

* **Community**:
    * Created the team's organization and team repo
    * Managed issues and allocated tasks to members
    * Set up CodeCov repo

    * PRs reviewed (with non-trivial review comments): [\#145](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/145), [\#174](https://github.com/AY2223S1-CS2103T-T09-2/tp/pull/115)
    * Contributed to forum discussions (examples: [\#153](https://github.com/nus-cs2103-AY2223S1/forum/issues/153), [\#73](https://github.com/nus-cs2103-AY2223S1/forum/issues/73))
    * Reported bugs and suggestions for other teams in the class (examples: [\#183](https://github.com/AY2223S1-CS2103T-W17-3/tp/issues/183), [\#187](https://github.com/AY2223S1-CS2103T-W17-3/tp/issues/187), [\#176](https://github.com/AY2223S1-CS2103T-W17-3/tp/issues/176), [\#172](https://github.com/AY2223S1-CS2103T-W17-3/tp/issues/172))

* **Tools**:
    * Used JavaFX to tweak the UI.

