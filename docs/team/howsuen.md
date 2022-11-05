---
layout: page
title: Tan How Suen's Project Portfolio Page
--

### Project: FRIDAY

FRIDAY is a desktop application built upon AddressBook Level 3, used for managing CS1101S students.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added functionality to edit the grades of students.
  * What it does: Allows users to edit the grades of the listed assessments and examinations for each student.
  * Justification: This feature is significant in allowing users to keep track of their students' grades to get a better picture of how well their students are doing academically. It also allows users to check for improvement in their students' grades, thus checking whether their teaching methods are effective or not, and whether the students are able to keep up with the content.
  * Highlights: Since the format of assessments has remained the same in CS1101S over the years, the current list of assessments and examinations are fixed to avoid confusion. We fixed the prefixes associated to the `grade` command, "ra1/", "ra2/", "mt/", "pa/", and "ft". 
  In addition, users are to input the scores in the form of percentages between 0% to 100%, both inclusive and up to 2
  decimal places. This provides a more comprehensive and standardised view of their students' grades. We also limited
  to 2 decimal places for a more simplistic view in our GUI.

* **New Feature**: Added functionality to add remarks to students.
  * What it does: Allows users to add comments and notes on their students in addition to their related information.
  * Justification: This feature is significant in improving users' experience with the product because it acts as a centralised hub for users to easily write notes about their students to remind them of their individual circumstances.
  * Highlights: Initial implementation requires users to type prefix `r/` to indicate the start of the remark comment.
  * Credits: This feature was adapted from **[Tutorial 2 - Adding a new Command](https://nus-cs2103-ay2223s1.github.io/tp/tutorials/AddRemark.html)** for **AB3**.

* **New Feature**: Edit a particular student's details.
  * What it does: Allows users to edit a student's information specified by the student's index in the list.
  * Justification: The user has to update any outdated or missing information for the student quickly. Hence, this
  feature is crucial.
  * Highlights: There are many prefixes to be parsed and checked for their presence and validity in the `edit` command.
  * Credits: This feature was adapted from AB3.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=howsuen&breakdown=true)

* **Project management**:
  * Created Milestones and Issue Labels
  * Added user stories and tasks in Github Issues
  * Assigned Github Issues for fixing of bugs reported from PE-D

* **Enhancements to existing features**:
  * Refactored code to improve maintainability and readability, and fixed ordering and naming of imports to pass checkstyle [\#80](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/80), [\#101](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/101)
  * Added and updated `edit` command for FRIDAY to edit a student's details, including name, Telegram Handle, Mastery Check assessment, consultation date, and tags. Additionally, added and updated tests for `edit` command. [\#80](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/89)
  * Updated and adapted `remark` command for FRIDAY to add remark comments for each student. [\#2](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/2)
  * Designed and updated GUI's logo and title [\#101](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/101)
  * Designed and implemented new GUI for FRIDAY, including adding new details for each student card, adding new colours 
  and adjusting layout the GUI.

* **Documentation**:
  * User Guide:
    * Added documentation for the `edit` feature
    * Added documentation for the `remark` feature [\#106](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/106)
    * Added documentation for the `grade` feature [\#116](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/116)
    * Reformatted Command Summary
  * Developer Guide:
    * Added Non-functional requirements
    * Refactored names to update for FRIDAY [\#106](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/106)
    * Updated UML Diagrams to fit FRIDAY
    * Added documentation for the `grade` feature, including the implementation details, design considerations and UML
    sequence diagram.
  * README:
    * Designed and added UI Mockup for FRIDAY

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#97](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/97), [\#118](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/118), [\#122](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/122), [\#178](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/178)
