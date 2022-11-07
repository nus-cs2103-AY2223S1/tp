---
layout: page 
title: Dilys Pang's Portfolio Page
---

### Project: SETA

SETA is a desktop application for CS2103T Teaching Assistants to track their students’ and tutorials’ details, and
questions asked by students, optimized for use via a Command Line Interface (CLI) while still having the benefits of a
Graphical User Interface (GUI). If you can type fast, SETA enables you to manage your tutorials and track your students
more effectively than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add a student.
    * What it does: Allows the user to add a student with their name, email and telegram handle.
    * Justification: This feature improves the product significantly because a user can add students into SETA to keep
      track of his or her students under him.
    * Highlights: This enhancement requires me to create new classes. It was also tricky because I added some additional
      checks within this feature, such as including an internal
      check to ensure that adding a student name will be case-insensitive.
      (E.g. if a student with the name "Alice Tan" is already in the addressbook, "alice Tan" cannot be added).


* **New Feature**: Added the ability to edit a student's details.
    * What it does: Allows the user to edit the student's name, email, telegram handle or attendance.
    * Justification: This feature improves the product significantly because a user can make any changes to a student's
      details if he or she keyed in the wrong details at the start or if the student makes any changes during the
      semester.
    * Highlights: This enhancement requires me to create new classes and make changes to many existing classes. It
      required an in-depth analysis of design alternatives.


* **New Feature**: Added the ability to find a student or multiple students.
    * What it does: Allows the user to find a specific student or students by inputting a keyword or keywords.
    * Justification: This feature improves the product significantly because a user can find the student or students of
      interest to view or check the student(s)' details.
    * Highlights: This enhancement requires me to create new classes and make changes to existing classes.


* **New Feature**: Added the ability to list all students.
    * What it does: Allows the user to view the details of all his or her students at once.
    * Justification: This feature improves the product significantly because a user can easily have a overview of all
      the students he or she has. This feature allows the user to go back to view all his students after executing a
      `findstu` command.
    * Highlights: This enhancement requires me to create new classes and make changes to existing classes.


* **New Feature**: Added test cases for all the above features and other classes related to Student.
    * What it does: Increase code coverage.
    * Justification: This feature improves the product significantly because it makes the application more bug-free.
    * Highlights: This enhancement requires me to create new classes and make changes to existing classes.

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=dilysss&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed releases v1.2 - v1.4 on Github.

* **Enhancements to existing features**:
    * Updated the GUI.
        * Changed the layout of the application.
            * Added a close button
            * Changed the look of the help button
            * Amended window size of application
        * Changed the GUI colour scheme.

* **Documentation**:
    * User Guide:
        * Added documentation and command summary for the features `addstu`, `editstu` `findstu` and `liststu`.
        * Did up table of contents.
    * Developer Guide:
        * Contributed to `UI component` section.
        * Added implementation details of the `addstu` feature.
        * Added use cases for `addstu`, `editstu`, `findstu` and `liststu` features.
        * Updated activity, sequence and class diagrams, such as `AddQActivityDiagram`, `ModelClassDiagram`,
          and `StorageClassDiagram`.

* **Team-based tasks**:
    * Maintaining issue tracker by adding issues addressed to myself or other team members.
    * Documenting parts in UG not specific to my features (E.g. Quick start, FAQ).
    * Helped to merge Pull Requests of team members.

* **Community**:
    * PRs reviewed: [\#108](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/108)
      , [\#179](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/179)
      , [\#200](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/200)
      , [\#207](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/207)
      , and more...


