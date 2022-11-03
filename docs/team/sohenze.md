---
layout: page
title: Soh Enze's Project Portfolio Page
---

## Overview

**TrackO** is a desktop application that helps small home-based business owners manage their orders and inventory efficiently
in the form of an integrated solution built using Java with around 15 kLOC. The user interacts with the application via a
CLI, and the application responds with its GUI, created with JavaFX.

## Summary of Contributions

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=sohenze&breakdown=true)

* **Enhancements implemented**:
  
  * Feature: **Delete Order Command**
    * Function: Allows the user to delete an existing order in the application.
    * Purpose: Core feature of an application that manages orders.
    * Relevant pull request(s): [#57](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/57)
  
  * Feature: **Delete Item Command**
    * Function: Allows the user to delete an existing inventory item in the application.
    * Purpose: Core feature of an application that manages inventory.
    * Relevant pull request(s): [#64](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/64)
  
  * Feature: **Edit Item Command**
    * Function: Allows the user to edit an existing inventory item in the application.
    * Purpose: Core feature of an application that manages inventory.
    * Notable Implications:
      * This feature safeguards users from mistakenly editing an inventory item into one that already exists in
      the application and creating a duplicate in the process.
      * Implementing this feature required deep consideration on the equivalency of inventory items from the perspective
      of the user.
    * Relevant pull request(s): [#84](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/84)
  
  * Enhancement: **Item Tag**
    * Context: Tag(s) are a property of inventory items and was previously only partially implemented by a teammate.
    * Changes Made: Built upon a teammate's enhancement by implementing tag(s) in the UI and Storage component
    to allow tag(s) to be displayed to the user in the GUI, and to be saved to and read from the data file respectively.
    * Relevant pull request(s): [#101](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/101)
  
  * Enhancement: **Order's timeCreated** (time of creation)
    * Context: timeCreated is the time at which an order is created and was previously only partially 
    implemented by a teammate.
    * Changes Made: Built upon a teammate's enhancement by implementing timeCreated in the UI and Storage component
    to allow timeCreated to be displayed to the user in the GUI, and to be saved to and read from 
    the data file respectively.
    * Notable Implications:
      * Implementing this feature required deep consideration on the equivalency of orders from the perspective
      of the user.
      * It was decided that timeCreated would not be checked during order equivalency checks as it had no practical
      purpose for the user.
    * Relevant pull request(s): [#108](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/108)
    
    * Feature: **Sort Order Command**
    * Function: Allows the user to sort the displayed order list in the application based on the orders' time
    of creation.
    * Purpose: Supplementary feature of an application that manages orders which provides users further ease of use.
    * Notable Implications:
      * The application previously displayed the order list to the user in the form of a FilteredList under JavaFX
      collections.
      * This is because the application supports filtering the displayed order list according to keywords provided
      by the user in another feature implemented by a teammate. FilteredList does not support any modifications to it.
      * Wrapped the existing FilteredList within a SortedList under JavaFX collections and displayed the SortedList
      to the user instead. This supported both features and allowed the user to sort filtered orders as well.
    * Relevant pull request(s): [#108](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/108)

* **Contributions to the UG**: to be added soon
  * Features Section:
    * Deleting an order
    * Deleting an item
    * Editing an item
    * Sorting orders
  * Relevant pull request(s): [#101](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/101),
  [#108](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/108),
  [#203](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/203)

* **Contributions to the DG**:
  * Design Section:
    * Architecture
    * UI
    * Logic
    * Model
    * Storage
    * Implementation Section:
      * Delete Item Feature
      * Edit Item Feature
      * Sort Orders Feature
  * Relevant pull request(s): [#114](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/114),
  [#137](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/137),
  [#203](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/203)
  
* **Contributions to team-based tasks**:
  * Facilitated team discussions on design implementations
  * Provided insights and proposed solutions to problems during team discussions

* **Review/mentoring contributions:**:
  * Provided alternative opinions to teammates' contributions
    * Relevant pull request(s): [#105](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/105)
  * Provided explanations to teammates regarding personal contributions
    * Relevant pull request(s): [#123](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/123),
    [#128](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/128)
  * Assisted teammates in fixing bugs introduced after merging different pull requests
    * Relevant pull request(s): [#128](https://github.com/AY2223S1-CS2103T-W15-3/tp/pull/128)
  
* **Contributions beyond the project team:**:
  * Assisted in detecting bugs and offered solutions and insights to another project team:
  [Practical Exam Dry Run](https://github.com/sohenze/ped.git)
