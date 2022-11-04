---
layout: page
title: Mohamed Hamas Project Portfolio Page
---

### Project: Salesy

Salesy is a desktop Salesy helps food vendors in NUS manage details of their tasks, inventory and suppliers. It is optimised for CLI users so that tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: 
* Implemented `Item` and `Price` field in person class (Pull Request [#93](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/93))
    * The `Item` field contains the item that is being supplied by the supplier.The `Price` field contains the price of item that is being supplied by the supplier.
  * Updated `AddCommand`,`AddCommandParser`,`EditCommand` and `EditCommandParser` to include Price and Item fields.
  * Updated `SampleDataUtil` to include `Item` and `Price` fields.
* Implemented `ItemTest` and `PriceTest` (Pull Request [#103](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/103))
    * Added new test files for both `Item` and `Price`.
* Implemented `SupplyItem` class (Pull Request [#121](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/121))
  * The `SupplyItem` class represents the item that a supplier is supplying.
  * It contains fields such as `name`,`currentStock`,`minimumStock` and `supplier` for that item.
  * Update `SampleDataUtil` to contain sample supply items.
* Implemented `Inventory` class (Pull Request [#121](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/121))
  * Inventory class is a model used to store supply items
* Implemented `SupplyItemTest` and `InventoryTest` (Pull Request [#126](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/126))
    * Added new test files for both `SupplyItem` and `Inventory`.
* Implemented `AddItem` command and `AddItemParser` (Pull Request [#147](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/147)) 
  * Feature helps to add new `SupplyItem` to `Inventory`
  * Also checks for duplicates before adding `SupplyItem`
* Implemented `AddItemCommandTest` and `AddItemCommandParserTest` (Pull Request [#153](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/153))
    * Added new test files for both `AddItemCommand` and `AddItemCommandParser`.


  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=Bahamas20&breakdown=true)

* **Project management**:
    * Helped to review and merge pull requests on time.
    * Added issues in Project so that it can be delegated to individuals.


* **Contribution to team-based Tasks**
    * Fixed and reviewed bugs from PE Dry run

* **Enhancements to existing features**:
    * `Person`  test cases adapted to cater to new fields `Price` and `Item`.
    * Added `Inventory` to `Model`.

* **Documentation**:
    * User Guide (Pull Request [#160](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/160)):
        * Added `addItem` command.
        * Standardised formats for all commands.
        * Enhance `find` command by providing more details and examples.
        * Reformat Command Summary.
    * Developer Guide (Pull Request [#121](https://github.com/AY2223S1-CS2103T-W08-4/tp/pull/121)):
        * Feature Implementation for `addItem` command.
        * Added `addItem` activity diagram.
        * Added state diagram for `addItem`.

* **Review/Mentoring contributions**:
  * [Reviews in PRs](https://github.com/AY2223S1-CS2103T-W08-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3ABahamas20)

* **Community**:
    * [Reported bugs for PE dry run](https://github.com/Bahamas20/ped/issues)
