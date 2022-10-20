---
layout: page
title: Tan How Suen's Project Portfolio Page
--

### Project: FRIDAY

FRIDAY is a desktop application built upon AddressBook Level 3, used for managing CS1101S students.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added functionality to add remarks to students.
  * What it does: Allows users to add comments and notes on their students in addition to their related information.
  * Justification: This feature is significant in improving users' experience with the product because it acts as a centralised hub for users to easily write notes about their students to remind them of their individual circumstances.
  * Highlights: Initial implementation requires users to type prefix `r/` to indicate the start of the remark comment. 
  * Credits: This feature was adapted from **[Tutorial 2 - Adding a new Command](https://nus-cs2103-ay2223s1.github.io/tp/tutorials/AddRemark.html)** for **AB3**.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=howsuen&breakdown=true)

* **Project management**:
  * Created Milestones and Issue Labels
  * Added user stories and tasks in Github Issues

* **Enhancements to existing features**:
  * Refactored code to improve maintainability and readability, and fixed ordering and naming of imports to pass checkstyle [\#80](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/80), [\#101](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/101)
  * Added and updated `edit` command for FRIDAY to edit a student's details, including name, Telegram Handle, Mastery Check assessment, consultation date, and tags. Additionally, added and updated tests for `edit` command. [\#80](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/89)
  * Updated and adapted `remark` command for FRIDAY to add remark comments for each student. [\#2](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/2)
  * Updated GUI's logo and title [\#101](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/101)
  * Designed and implemented new GUI for FRIDAY, including adding new colours and adjusting layout.

* **Documentation**:
  * User Guide:
    * Added documentation for the `edit` feature
    * Added documentation for the `remark` feature [\#106](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/106)
    * Reformatted Command Summary
  * Developer Guide:
    * Added Non-functional requirements
    * Refactored names to be updated for Friday [\#106](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/106)
    * Updated UML Diagrams
  * README:
    * Designed and added UI Mockup for FRIDAY

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#97](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/97)
