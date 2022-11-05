---
layout: page
title: Lim Jun Hong Don's Project Portfolio Page
---

## Project: TrackO

**TrackO** is a desktop application that helps small home-based business owners manage their orders and inventory efficiently
in the form of an integrated solution built using Java with around 15 kLOC. The user interacts with the application via a 
CLI, and the application responds with its GUI, created with JavaFX.

Given below are my contributions to the project.

## Summary of Contributions

  * **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=donljh&breakdown=true)

  * **Major Enhancement**: Heavily refactored the code from AB3 during the initial stages of development.
    * What was done: Renamed packages and methods, adapted large chunks of code to new use cases and fixing test cases.
    * Justification: This initial refactoring was crucial to allow further extensions on the project by other members to minimize conflict from previously established code.
    * Highlights: 
      * The refactoring process required me to deeply understand and be able to navigate the moderately large structure and architecture of the brownfield project.
      * Being in touch with a large portion of the codebase prior to further developments by the project team gave me the confidence to suggest more thought-out feature implementations during team discussions.
    * Relevant pull request(s): [#56](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/56)

  * **Major Feature**: Added the ability for users to add orders to the application.
    * What it does: Allows the user to add orders using customer data and ordered items' details.
    * Justification: This feature is a core component of the application as part of an order management application, and is absolutely essential to every potential user.
    * Highlights: 
      * This implementation of this feature and the related model classes affects commands to be added in the future. 
      * The decision to implement the "add order" command as a multi-level command was considered after in-depth analysis of other design alternatives and was adopted in favor of a better user experience. 
      * The implementation itself was also challenging as it required updating of the previously strictly single-level command structure to accommodate a new command execution structure.
    * Relevant pull request(s): [#56](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/56), [#105](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/105)
    
  * **Major Enhancement**: Built on top of a team member's implementation of marking orders to freeze order details on completion.
    * What it does: Implement measures such that an order's details become immutable when it has been marked paid and completed.
    * Justification: This enhancement builds on top of the feature to mark an order as paid/completed and was necessary to keep order data consistent, such that a completed order's data is not affected by any subsequent edits to the items in the inventory.
    * Highlights: 
      * The implementation of this enhancement was challenging as the architecture of the model had to be updated to accommodate different representations of items (active items part of the user's inventory VS inactive recorded items part of completed orders).
      * The implementation to handle conversion from the storage file to the application's model state and vice versa also required significant design considerations (e.g. what data to preserve, etc) in order to preserve the data integrity between separate loads of the application while keeping the data loading process smooth and error-free.
    * Relevant pull request(s): [#133](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/133)

  * **Documentation**:
    * User Guide:
      * Added documentation for the `addo` feature
      * Added the glossary section
      * Updated front-matters to be more user-friendly (introduction, table of contents structure)
      * Relevant pull request(s): [#193](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/193)
    * Developer Guide:
      * Added implementation details of the `Order` class
      * Added implementation details of the `addo` feature
    * Relevant pull request(s): [#141](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/141)

  * **Review/Mentoring Contributions:**:
    * Pull requests reviewed (with non-trivial review comments): [#199](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/199), [#136](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/136), [#125](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/125), [#111](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/111)  
    * Offered several crucial implementation and design alternatives during team discussions 
    * Assisted team members in debugging their code in several instances over the course of development

  * **Project Management**:
    * Managed release [`v1.2.1`](https://github.com/AY2223S1-CS2103T-W15-3/tp/releases/tag/v0.1) (1 release)

  * **Contributions beyond the project team**:
    * Reported bugs and suggestions for other teams in the class: [Reported bugs during the Practical Exam Dry Run](https://github.com/donljh/ped/issues)
