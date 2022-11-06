---
layout: page
title: Ong Wen Pin's Project Portfolio Page
---

### Project: myStudents

myStudent is **an easy-to-use, portable and aesthetically-pleasing desktop application for tuition centre admins
for managing the students, tutors and tuition classes of a tuition center**.
It is optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to assign tuition class to student and tutors. (Pull request [#66](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/66))
    * What it does: allow user to assign tuition class to student and tutors.
    * Justification: This feature allows user to be able to assign tuition class to student if the student is enrolled to the class
  and assign tuition class to tutor if the tutor is currently teaching the class. This allows the users to keep track of tuition classes 
  the student and tutor are currently enrolled or teaching respectively.
    * Highlights: Requires to modify the `Student` and `Tutor` class in model component and in-depth consideration of design to ensure
  that the `Student` and `Tutor` data can be saved in Json format and read back.

* **New Feature**: Added the ability to unassign tuition classes from student and tutors. [#66](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/66))
    * What it does: allow user to unassign tuition class from student and tutors.
        * Justification: This feature allows user to be able to unassign tuition class from student if the student is no longer enrolled to the tuition class
          and unassign tuition class from tutor if the tutor is no longer teaching the class. This allows the users to keep track of tuition classes
          the student and tutor are currently enrolled or teaching respectively.
        * Highlights: Requires to modify the `Student` and `Tutor` class in model component and in-depth consideration of design to ensure
          that the `Student` and `Tutor` data can be saved in Json format and read back.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ongwenpin&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Coordinated team meetings every week.
    * Reported PE-D bug fixing status

* **Enhancements to existing features**:
    * Modified methods in `AddressBook`, `Model` and `ModelManager` to ensure it works for `Student`, `Tutor` and `TuitionClass`. (Pull request [#42](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/42))
    * Made `Person` class abstract and fix affected methods in different components and tests to ensure it works. (Pull request [#113](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/113))
    * Enhanced error message for all the commands. (Pull request [#125](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/125))

* **Documentation**:
    * User Guide:
        * Added sections for `assign` and `unassign`. (Pull request [#113](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/113))
        * Added a Quick Start section for the user guide. (Pull request [#204](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/204))
        * Reorganised sections of user guide into a more user-friendly flow. (Pull request [#225](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/225))
        * Helped to fix bugs in the user guide. (Pull requests [#204](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/204), [#225](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/225))
    * Developer Guide:
        * Added implementation details and designed Sequences Diagram for `Assign` and `Unassign` features. (Pull request [#96](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/96))
        * Update the UML class diagram for `model` component. (Pull request [#230](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/230))
        * Responsible for user stories, use cases, glossary and non-functional requirements for developer guide. (Pull request [#230](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/230))

* **Community**:
    * Reported bugs and suggestions for other team during PE-D ([Here](https://github.com/ongwenpin/ped/issues)).

* **Team-based tasks**
    * PR reviewed and merged. [#231](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/231)
  [#85](https://github.com/AY2223S1-CS2103T-F12-4/tp/pull/85)
        * [Full list](https://github.com/AY2223S1-CS2103T-F12-4/tp/pulls?q=is%3Apr+commenter%3Aongwenpin) can be found here  
