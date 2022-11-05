---
layout: page title: Dilys' Project Portfolio Page
---

### Project: SETA

SETA is a desktop tutorial management application used to help CS2103T TA's to track their students’ and tutorials’
details, and questions asked by students. The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add a student.
    * What it does: Allows the user to add in the name, email and telegram handle of a student.
    * Justification: This feature improves the product significantly because a user can add students into SETA in order
      to keep track of his or her students under him.
    * Highlights: This enhancement requires me to create new classes. 
      It was also tricky because I added some additional checks within this feature, such as including an internal 
      check to ensure that adding a student name will be case-insensitive. 
      (E.g. if a student with the name "Alice Tan" is already in the addressbook, "alice   Tan" cannot be added).


* **New Feature**: Added the ability to edit a student's details.
    * What it does: Allows the user to edit the student's name, email or telegram handle.
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


* **New Feature**: Added test cases for all the above features.
    * What it does: Increase code coverage.
    * Justification: This feature improves the product significantly because it makes the application more bug-free.
    * Highlights: This enhancement requires me to create new classes and make changes to existing classes.

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=T08&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=zoom&zA=Dilysss&zR=AY2223S1-CS2103T-T08-4%2Ftp%5Bmaster%5D&zACS=181.21900826446281&zS=2022-09-16&zFS=T08&zU=2022-10-31&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
    * Managed releases v1.2 - v1.4 on Github
    
* **Enhancements to existing features**:
    * Updated the GUI
        * Changed the layout of the application
          * Added a close button
          * Added header
          * Fixed window size of application
        * Changed the GUI colour scheme

* **Documentation**:
    * User Guide:
        * Added documentation for the features `addstu`, `editstu` `findstu` and `liststu`
        * Did cosmetic tweaks to existing documentation of features `exit`
    * Developer Guide:
        * Added implementation details of the `addstu` feature.
        * Added use cases for `addstu`, `editstu`, `findstu` and `liststu` features.
        * Edited numerous activity, sequence and class diagrams, such as `AddQActivityDiagram`, `ModelClassDiagram`,
          and `StorageClassDiagram`.


* **Community**:
    * PRs reviewed: [\#108](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/108)
      , [\#179](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/179)
      , [\#200](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/200)
      , [\#207](https://github.com/AY2223S1-CS2103T-T08-4/tp/pull/207)


