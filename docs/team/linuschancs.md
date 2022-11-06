---
layout: page
title: Linus Chan's Project Portfolio Page
---

## Project: Class-ify

Class-ify is a class management application** built specially for Singapore Ministry of Education (MOE) teachers
to monitor their student's academic progress easily**. Teachers can generate exam statistics for each class, and
Class-ify quickly flags out students who require more support for contacting.

It is written in Java and the GUI is build using JavaFX.

### Summary of Contributions:

#### Code contributed:
* [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=linuschancs&breakdown=true)

#### Enhancements implemented:
* Class
    * Created a new `Class` class that represents the class of each student
      * See PR [#106](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/106)
    * Integrated as another attribute of Student
* AddStudentCommand
  * Replaced Address Field with Email Field
  * Ensure all fields stored in Class-ify are capitalised and filtering is case-insensitive
  * Changed some compulsory fields of a `Student` to optional such as the respective parent details
    * See PR [#139](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/139)
  * Fixed bug where empty flags were allowed when entering the AddStudent Command
* ViewClassCommand
  * Implemented ViewClassCommand that displays the list of students in a class
  * Created ClassPredicate to handle the class filtering logic
  * Add relevant unit test cases and integration test cases
  * See PR [#106](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/106) and [#172](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/172)

#### Contributions to UG:
* Designed Class-ify Logo
* [Notes on Command Format](https://ay2223s1-cs2103t-t15-2.github.io/tp/UserGuide.html#41-notes-on-the-command-format)
  * Added explanation regarding the command format when using Class-ify
* [ViewClassCommand](https://ay2223s1-cs2103t-t15-2.github.io/tp/UserGuide.html#432-viewing-student-records-from-a-class--viewclass)
  * Added implementation details of `ViewClassCommand`
* [Glossary](https://ay2223s1-cs2103t-t15-2.github.io/tp/UserGuide.html#7-glossary)
  * Added definitions of key terms used in User Guide

#### Contributions to DG:
* [ViewClassCommand](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#426-viewclass-command)
  * Added implementation details of `ViewClassCommand`
  * Created Sequence Diagram for `ViewClassCommand`
* [Product Scope](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#61-product-scope)
  * Updated Product Scope to accurately reflect our team's target audience
* [Non-Functional Requirements](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#64-non-functional-requirements)
  * Added non-functional requirements to accurately represent our product
  * See PR [#195](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/195)
* [Glossary](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#65-glossary)
  * Added key terms that users of the Developer Guide may be unaware of

#### Contributions to team-based tasks:
* Set up Github Issues tracker with relevant labels
* Created Release for v1.3.1 and v1.3.trial
  * See [Class-ify Releases](https://github.com/AY2223S1-CS2103T-T15-2/tp/releases)
* Created weekly agenda and tasks to be completed for team meeting

#### Review/ Mentoring Contributions:
* Total Pull Requests reviewed: 37
* Examples of contribution to reviews:
  * [#149](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/149): Provided suggestions to viewStats Command implemntation
  * [#136](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/136): Provided suggestion to better standardise and phrase display message to users
  * [#93](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/93): Provided suggestion on how to improve code quality
  * [#47](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/47): Identified a standardisation error in the Command Summary Table

#### Contributions beyond the project team:
* [#100](https://github.com/nus-cs2103-AY2223S1/forum/issues/100#issuecomment-1230489545): Provided assistance to classmate in forum
