---
layout: page
title: Yeo Kai Jiun's Project Portfolio Page
---

### Project: FRIDAY

FRIDAY is a desktop application used for managing CS1101S students, that is written in Java and built upon AddressBook
Level 3. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has
about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added functionality to Mark the Mastery Checks of students as passed.
  * What it does: Allows users to mark the Mastery Check of a specified student as passed.
  * Justification: This feature allows users to update and keep track of which of their students have already passed their Mastery Checks. This is important as it helps the user to know if there are any important upcoming dates on which students have scheduled to have their Mastery Checks, or if these students have already completed their Mastery Checks.
  * Highlights: The Mastery Check of the specified student has to meet all 3 of the following conditions in order to be able to be marked as passed:
    1. The student's Mastery Check has not already been marked as passed.
    2. The student actually has a scheduled Mastery Check date (i.e. the student's Mastery Check field is not empty).
    3. The student's scheduled Mastery Check date is not beyond the current date.

* **New Feature**: Added functionality to Unmark the Mastery Checks of students.
  * What it does: Allows users to unmark the Mastery Checks of students, removing the "passed" status of this student's Mastery Check.
  * Justification: This feature is an important counterpart to the `mark` feature, as it allows the user to unmark the Mastery Check of a student which they may have mistakenly marked as passed, even though the student has in fact not passed their Mastery Check.
  * Highlights: The Mastery Check of the specified student has to meet both of the following conditions in order to be able to be unmarked:
    1. The student's Mastery Check has already been marked as passed.
    2. The student actually has a scheduled Mastery Check date (i.e. the student's Mastery Check field is not empty).

* **New Feature**: Get the link to the User Guide for FRIDAY.
  * What it does: Allows users to get a link to the User Guide, which they can then copy and paste into a browser to access the document.
  * Justification: The user may need to refer to the User Guide while using FRIDAY in case they forget about command formats and other details of FRIDAY.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kaij77&breakdown=true)

* **Project management**:
  * Added user stories and tasks in Github Issues
  * Assigned Github Issues for fixing of bugs reported from PE-D

* **Enhancements to existing features**:
  * Refactored various Java classes in the existing code to match the purpose of FRIDAY, for example refactoring `Person` to `Student`. [\#84](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/84)
  * Created the `TelegramHandle`, `MasteryCheck` and `Consultation` classes. [\#84](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/84)
  * Edited sample data to fit the context of FRIDAY. [\#96](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/96)
  * Added and updated `guide` command for FRIDAY to allow the user to obtain a link to the User Guide for FRIDAY. [\#98](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/98)
  * Added and updated `mark` command for FRIDAY to allow the user to mark a specified student's Mastery Check as passed. [\#111](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/111)
  * Added and updated `unmark` command for FRIDAY to allow the user to unmark a specified student's Mastery Check. [\#128](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/128)
  * Added tests for `mark` and `unmark` commands. [\#190](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/190)

* **Documentation**:
  * User Guide:
    * Added documentation for the `mark` feature, including use cases and guide on how to use the command. [\#111](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/111/commits/954ed8f964916a1fa1a720a5a3a0449b59def886)
    * Added documentation for the `unmark` feature, including use cases and guide on how to use the command. [\#128](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/128/commits/d28b9a107b6d67c2ef92883fbb06b54aac979a76)
    * Added documentation for the `guide` feature [\#98](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/98/commits/8a1570b817e5735a7c1a0b805ab79afa0efe72c0)
    * Edited documentation for `mark`, `unmark` and `guide` features to make User Guide more user-friendly. [\#195](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/195)
    * Added explanations of each section of the User Guide to make the guide more user-friendly. [\#204](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/204)
  * Developer Guide:
    * Added documentation for the `mark` feature, including the implementation details, design considerations and UML
      sequence diagram.
    * Added documentation for the `unmark` feature, including the implementation details, design considerations and UML
      sequence diagram.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#116](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/116), [\#199](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/199), [\#206](https://github.com/AY2223S1-CS2103T-W15-4/tp/pull/206)
