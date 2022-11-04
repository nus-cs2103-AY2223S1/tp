---
layout: page
title: Xing Yu's Project Portfolio Page
---

### Project: ArtBuddy

ArtBuddy (AB) a desktop application that helps commission-based artists manage their customers, commissions, and artworks easily. Written in Java, ArtBuddy is primarily optimised for use via a Command Line Interface (CLI), but also comes with a GUI created using JavaFX.

Given below are my contributions to the project.

* **Key Contribution**: Implemented the `addcus` [#67](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/67), `sortcus` [#106](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/106) and `allcom` [#140](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/140) command
  * *What it does:* 
    * `addcus` allows the user to create a new customer entry.
    * `sortcus` allows the user to sort the customer list by several comparators.
    * `allcom` allows the user to view all commissions across all customer entries.
  * Highlights:
    * Implementing the `addcus` command involved implementing the `Customer` model and adapting `AddCustomerCommandParser` to accept optional arguments.
    * Implementing `sortcus` involved wrapping the `FilteredList` of all customers in a `SortedList` and accepting one of several `Comparator` comparators.
    * Implementing `allcom` required careful consideration of the design for allowing us to switch between one of several commission lists when the selected customer changes, ensuring that the GUI is updated appropriately. 
* **Key Contribution**: Improved the design of various components
  * Implemented global commissions list to support commission-level operations across different customers [#140](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/140)
  * Improved the image loading and storing implementation [#122](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/122)
  * Improved implementation of `Commission` [#82](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/82) [#83](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/83).
    * Stored the customer's reference within each `Commission` so we do not need to update the customer field for every commission each time the customer changes.  

* **Documentation**:
  * User Guide:
    * Added the `addcus`, `sortcus` and `allcom` commands.
  * Developer Guide:
    * Updated the UML diagrams based on updates to the code. [#82](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/82) [#83](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/83) [#131](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/131)
    * Added Non-functional requirements. [#8](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/8)

* **Contributions to team-based tasks**:
  * Enable assertions [#112](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/112)
  * Implementing basic customer and commissions tab pane [#69](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/69)
  * Implemented basic Observer pattern that is used by various UI elements [#69](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/69)
  * Helped to rename product from `AddressBook` to `ArtBuddy` across the codebase.
  * Added tests for various commands e.g. `ListCommissionCommand`, `AddCustomerCommand`, `SortCustomerCommand`, `AllCommissionCommand`
* **Review/mentoring contributions**:
  * Reviewed and provided useful feedback and suggestions in various PRs:
    - [#68](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/68) proposed builder pattern for simpler handling of different combinations of arguments for `Customer` constructor.
    - [#92](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/92) suggested changes to the implementation of `storeImage` to the `Storage` layer to avoid violating the dependency inversion principle.
    - [#75](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/75), [#77](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/77), [#116](https://github.com/AY2223S1-CS2103T-W11-3/tp/pull/116)
  * [Reported bugs and suggestions for other teams in the class](https://github.com/ngxingyu/ped/issues)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ngxingyu&breakdown=true)
