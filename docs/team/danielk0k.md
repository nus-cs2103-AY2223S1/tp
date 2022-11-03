---
layout: page
title: Daniel Kok's Project Portfolio Page
---

## Project Class-ify

Class-ify is a class management application built specially for Ministry of Education (MOE) teachers to easily monitor their students’ academic progress.
Teachers can generate exam statistics for each class, and Class-ify quickly flags out students who require more support for contacting.

It is written in Java and the GUI is built using JavaFX.

### Summary of Contributions

#### Code Contributed
- [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=danielk0k&breakdown=true)

#### Enhancements Implemented
- [AddStudentCommand](https://github.com/AY2223S1-CS2103T-T15-2/tp/blob/master/src/main/java/seedu/classify/logic/commands/AddStudentCommand.java)
  - Adapted `Person` class to `Student` class to fit into our user story.
  - Created an `Id` class to support the ID field of a student.
  - Added an additional `Name` field to support the name of the student's parent.
  - See PR [#90](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/90) and [#99](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/99)
- [EditCommand](https://github.com/AY2223S1-CS2103T-T15-2/tp/blob/master/src/main/java/seedu/classify/logic/commands/EditCommand.java)
  - `EditCommand` is closely related to the `AddStudentCommand`.
  - Added new parser for exam such that instead of overriding the set, it updates the exam scores.
- [Exam](https://github.com/AY2223S1-CS2103T-T15-2/tp/blob/master/src/main/java/seedu/classify/model/exam/Exam.java)
  - Adapted `Tag` class to `Exam` class to fit into our user story.
  - Contains the name and score of the exam.
  - See PR [#121](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/121)
- User Interface (UI)
  - Updated student card to display exam grades.
  - Improved layout of components and scaling of application window.
  - See PR [#146](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/146) and [#170](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/170)

#### Contributions to the UG
- [Adding a new student record](https://ay2223s1-cs2103t-t15-2.github.io/tp/UserGuide.html#421-adding-a-new-student-record--addstudent)
  - Compiled a table of the fields and restrictions on the values.
  - See PR [#104](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/104) and [#239](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/239)

#### Contributions to the DG
- [User Story](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#62-user-stories)
  - See PR [#15](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/15)
- [Model Component](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#413-model-component)
  - Updated UML diagram and rephrased content.
  - See PR [#132](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/132)
- [AddStudentCommand](https://ay2223s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#421-addstudent-command)
  - Added implementation details of the `AddStudentCommand`.
  - See PR [#162](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/162)

#### Contributions to Team-based Tasks
_To be updated_

#### Review/mentoring contributions
- [#243](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/243): Highlighted an inconsistent wording in the user guide.
- [#170](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/170): Assisted teammate in resolving difficulties in UI layout and scaling.
- [#156](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/156): Informed teammate of the incorrect use of HTML list tags for the user guide.
- [#139](https://github.com/AY2223S1-CS2103T-T15-2/tp/pull/139): Brought up a suggestion on how to make the code more concise.

#### Contributions Beyond the Project Team
_To be updated_
