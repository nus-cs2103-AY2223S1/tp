---
layout: page
title: Sun Ruoxin's Project Portfolio Page
---

### Project: Teacher’s Address Book (TAB)

Teacher’s Address Book (TAB) is a **desktop app made for teachers, teaching assistants(TA) and professors for managing 
contacts of each other, as well as their students, optimized for use via a Command Line Interface** (CLI) while still 
having the benefits of a Graphical User Interface (GUI). If you can type fast, TAB can get your contact management tasks 
done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Implemented the `TeachingAssistant` class
  * What it does: encapsulates the relevant details of a teaching assistant (i.e. their availability).
  * Justification: the product can be used for managing the contact information of persons in three positions, 
    with teaching assistants being one of them.
  * Highlights: the `TeachingAssistant` class extends the abstract `Position` class. 
    Being the first subclass of `Position` to be implemented, the design of the `TeachingAssistant` class provided a 
    guideline for the implementation of the other two subclasses (i.e. `Student` class and `Professor` class). 
* **New Feature**: Added the `AvailabilityCommand` to edit the availability of a teaching assistant in the address book.
  * What it does: allows the user to edit the availability of a teaching assistant in the address book.
  * Justification: when a teaching assistant needs to find a relief teacher when they are unavailable for a class, the 
    professor or the teaching assistant themselves can check the availability of other teaching assistants and know who 
    they can contact. The availability command allows the user to update the availability of a teaching assistant to their 
    current status. 
  * Highlights: the implementation of the command execution took inspiration from the existing commands' execution, 
    after an in-depth analysis of the code base and design alternatives. Being the first new command related to the 
    subclasses of `Position` to be implemented, the design of the `AvailabilityCommand` and `AvailabilityCommandParser` 
    provided a guidance for the implementation of other new commands related to the other two subclasses 
    (i.e. `AttendanceCommand`, `GradeCommand` and `RolesCommand`). 
* **New Feature**: Modified the `GradeCommand` to accommodate product design changes.
  * What it does: with `Assignment` class added in release `v1.3`, the modified `GradeCommand` adds grade to an 
    assignment of a student in the address book. The overall grade of the student, which is displayed on the detail page 
    of the student, is automatically calculated and stored in the address book when the grade of an assignment is updated.
  * Justification: the modified grade command improves the usability of the product, as in a school's context the grades 
    are associated with assignments instead of directly with a student on their own.
  * Highlights: the implementation was challenging as it required changes to the existing `GradeCommand` and other 
    related methods in the `Student` class.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=vantemoon&breakdown=true)

* **Project management**:
    * _{to be added soon}_

* **Enhancements to existing features**:
    * _{to be added soon}_

* **Documentation**:
    * User Guide:
        * Added documentation for features `help`, `show`, `attendance`, `assignments`, `grade`, 
          `avail`, `roles`, `filter`, `new`, `swap`, and `rename`
          [#49](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/49), [#96](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/96)
        * Did cosmetic tweaks to existing documentation of features `add`, `list`, `edit`, `delete`, `clear`, and `exit`
          [#49](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/49), 
      * Developer Guide:
        * Added implementation details of the grade feature [#75](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/75)

* **Community**:
    * _{to be added soon}_

* **Tools**:
    * _{to be added soon}_
