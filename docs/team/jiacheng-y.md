---
layout: page
title: Yang Jiacheng's Project Portfolio Page
---

### Project: TrackO 

**TrackO** is a desktop application that helps small home-based business owners manage their orders and inventory efficiently
in the form of an integrated solution built using Java with around 15 kLOC. The user interacts with the application via a
CLI, and the application responds with its GUI, created with JavaFX.

### Summary of Contributions
* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jiacheng-y&breakdown=true)

* **Enhancements implemented**:

  * Notable Feature: **Mark Order Command**
    * Function: Allow the user to mark orders as paid and/or delivered. This feature also includes reducing inventory 
    item quantities accordingly when and item is marked as delivered, and checking for sufficient stock when a 
    user wants to mark an order as delivered. 
    * Purpose: Unique selling point of our application over traditional Excel sheets. By automating 
    the depletion of quantities in the inventory with every fulfilled order, TrackO reduces the risk of
    human error in the inventory tracking process. 
    * Relevant pull request(s): [#118](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/118),
    [#121](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/121), 
    [#127](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/127)

  * Notable Feature/Enhancement: **Add Item Command**
    * Function: Allows the user to add inventory items to the inventory list. 
    * Purpose: Core feature of an application that manages inventory. 
    * Relevant pull request(s): [#61](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/61)

  * Notable Enhancement: **Implement initial Storage and UI support for inventory items**
    * Function: Sets the foundation for saving and displaying inventory items in TrackO. 
    * Purpose: Foundation for future developments of an inventory management application. 
    * Relevant pull request(s): [#67](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/67),
    [#69](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/69)

  * Notable Enhancement: **Clear Command**
    * Function: Allows the user to clear all data in TrackO. 
    * Purpose: This is to allow users to clear all sample data when they are ready to use the application proper.
    * Notable implementation
      * Clear command is implemented as a multilevel command which means that users will be required to respond to a 
      confirmation / cancellation prompt before the command is executed by TrackO. This serves to reduce the risk of 
      users accidentally deleting all their data. 
    * Relevant pull request(s): [#140](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/140), 
    [#213](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/213)

* **Contributions to the UG**: 
  * Features added in documentation: 
    * Add item
    * Mark item as paid and/or delivered
    * Clear
  * Fix bugs in the UG reported by other teams
  * Relevant pull requests: [#46](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/46), 
  [#113](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/113), 
  [#144](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/144), 
  [#201](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/201)

* **Contributions to the DG**: 
  * Added implementation details for following features
    * Inventory management overview (with InventoryItem class diagrams)
    * Add item (with relevant state, sequence and activity diagrams)
  * Added glossary section
  * Relevant pull request(s): [#47](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/47), 
  [#113](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/113), 
  [#211](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/211), 
  [#218](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/218)  

* **Contributions to team-based tasks**: 
  * Managed release of final `.jar` file for 
  [`v1.3.1`](https://github.com/AY2223S1-CS2103T-W15-3/tp/releases/tag/v0.3) 
  * Offered insights on implementation and design alternatives during team discussions

* **Review/mentoring contributions:**: 
  * Offered suggestions to teammates' pull requests
  * Caught and fixed mission critical bugs: [#145](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/145)

* **Contributions beyond the project team:**: 
  * Helped another project team detect bugs and offered potential solutions to these bugs: 
  [Practical Exam Dry Run](https://github.com/Jiacheng-y/ped/issues)
