---
layout: page
title: Khor Jun Wei's Project Portfolio Page
---

### Project: Teacher’s Address Book (TAB)

Teacher’s Address Book (TAB) is a **desktop app made for teachers, teaching assistants(TA) and professors for managing
contacts of each other, as well as their students, optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, TAB can get your contact management tasks
done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Implemented the `Student` class
  * What it does: encapsulates the relevant details of a student (i.e. their attendance and grades).
  * Justification: the product can be used for managing the contact information of persons in three positions,
    with student being one of them.
  * Highlights: the `Student` class extends the abstract `Position` class.
* **New Feature**: Added the `AttendanceCommand` to edit the attendance of a student in the address book.
  * What it does: allows the user to edit the attendance of a student in the address book.
  * Justification: As a teaching assistant, one has to keep track of the attendance of a student, to know how frequent he/she attends lessons and present the information to the professors. The attendance command allows the user to update the attendance of a student to their
    current status.
  * Highlights: the implementation of the command execution took inspiration from the existing commands' execution,
    after an in-depth analysis of the code base and design alternatives.
* **New Feature**: Added the `GradeCommand` to edit the grade of a student in the address book.
  * What it does: allows the user to edit the grade of a student in the address book.
  * Justification: As a teaching assistant, one has to keep track of the grade of a student, to know how well they are performing and provide help when necessary. The grade command allows the user to update the grade of a student to their
    current status.
  * Highlights: the implementation of the command execution took inspiration from the existing commands' execution,
    after an in-depth analysis of the code base and design alternatives.
* **New Feature**: Added the `FilterCommand` to filter students with similar tags in the address book.
  * What it does: allows the user to filter students with similar tags in the address book.
  * Justification: As a teaching assistant, one may want to search the address book for contacts who are part of the same tutorial class. The filter command allows the user to do so efficiently, and the address book will now display the filtered list of students.
  * Highlights: the implementation of the command execution took inspiration from the existing commands' execution,
    after an in-depth analysis of the code base and design alternatives. For instance, the command requires the use of `TagContainsKeywordPredicate`, which took inspiration from `NameContainsKeywordsPredicate`.
* **New Feature**: Implemented the `ChartUtil` class
  * What it does: Constructs a bar chart to visually display the grades of a student.
  * Justification: this will be helpful to teaching assistants, as they can now easily compare the performance between students visually.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=kjunwei&breakdown=true)

* **Project management**:
  * Authored 9 issues to help keep track of current issues and progress.
  * Managed testing and writing test code: [#149](https://app.codecov.io/gh/AY2223S1-CS2103T-T17-1/tp/commit/1ffa6db3ee281ebc53304e816129d84dd8017f3d)

* **Enhancements to existing features**:
  * Updated `addAssignmentsCommand` to raise more issues with invalid user inputs: [#143](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/143)

* **Documentation**:
  * User Guide:
    * Added documentation for the `remark` feature: [#70](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/70)

  * Developer Guide:
    * Added implementation details for the filter feature: [#70](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/70)

* **Community**:
  * Reviewed 14 pull requests (with non-trivial comments) to ensure quality and consistency across the database (#19, #25, #28, #31, #47, #49, [#55](https://github.com/AY2223S1-CS2103T-T17-1/tp/pull/55), #56, #89, #91, #94, #96, #98, #153).
  * Reported bugs and suggestions for other teams in the class (examples: [#4](https://github.com/KJunWei/ped/issues/4))

* **Tools**:
