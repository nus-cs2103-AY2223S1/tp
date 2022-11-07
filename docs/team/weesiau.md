---
layout: page
title: Siau Wee's Project Portfolio Page
---

### Project: Salesy

Salesy is a desktop program that helps food vendors in NUS manage details of their tasks, inventory and suppliers. 
It is optimised for CLI users so that tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: `EditItemSupplierCommand`. ([#142](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/142))
  * This command class executes the updating of supply items when the supplier that is supplying it has their details updated.
  * This is to ensure that all details of supply items are up to date and are automatically updated whenever the supplier supplying it is updated, to reflect the real life scenario of fluctuating prices, changing names etc.
  * Several additional helper methods were created to facilitate this process, most substantially to verify of whether a supplier is supplying an item.
    * More specifically, methods in the `Inventory` class are crucial to this process

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=weesiau&breakdown=true)

* **Project management**:
    * Helped to ensure that milestones are met on time, issues properly closed/handled and pull request and reviews are done properly.

* **Contribution to team-based Tasks**
    * Contributed to the creation of user stories
    * Reviewed and discussed bugs identified during PE Dry Run 
    * Closed multiple duplicate bug reports
    * Debugged and fixed bugs from PE Dry Run ([Bugs fixed](https://github.com/AY2223S1-CS2103T-W08-4/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Aweesiau))

* **Enhancements to existing features**:
    * `EditCommand` ([#101](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/101))
      * Executes the additional check of duplicate phone numbers (on top of name) when editing a supplier's details.
      * Rationale is that it is unlikely for different suppliers to have the same phone number.
      * This is done by implementing new methods in associated classes such as `Person`, `Model`, `ModelManager` and `AddressBook`.
      * A specified `Person` can now be excluded from duplicate checks within the `AddressBook`
        * Done with a new method, `UniquePersonList#containsExcluding`
    * `FindCommand` ([#127](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/127))
      * Can be used to search suppliers by their supplied item as well
      * Rationale is that this is the most likely search criteria, after name, to look for specific suppliers
    * Test cases
      * Created new and updated existing test cases for `Person`, `EditCommand`, `EditItemSupplierCommand`, `FindCommand` as well as the various associated parser classes

* **Documentation**:
    * User Guide:
        * Imported initial plain text draft to markdown format used now
        * Added `Important information` section to address important details crucial to using the user guide
        * Updated commands in the `add` and `edit` family with notes that address how it is used
        * Improved formatting and syntax used in example commands and command summary
        * Fix grammar, spelling and standardised tone of delivery
    * Developer Guide:
        * Added several use cases detailing the supplier related commands
          * Specifically, regarding the `add`, `delete`, `list, `find` and `edit` commands. ([#109](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/109))
        * Added the implementation details for `EditCommand` and all UML diagrams used in the `EditCommand` section. ([#134](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/134))
          * Detailed the implementation summary, rationale, execution process
          * Further updated to reflect interaction with supplier's supplied `SupplyItem` ([#159](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/159))
          * Diagrams created are `EditCommandObjectDiagram`, `EditCommandParserSequenceDiagram`,`EditCommandSummaryActivityDiagram`,`EditCommandVerifyActivityDiagram`, `PersonStorageDataExistsState`, `PersonStorageDataNotExistState` and `SupplierAndSupplyItemClassDiagram`.

* **Review/mentoring contributions**:
    * [Reviewed PRs](https://github.com/AY2223S1-CS2103T-W08-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aweesiau)
    * Commented on teammates' PRs and direct commit small changes
