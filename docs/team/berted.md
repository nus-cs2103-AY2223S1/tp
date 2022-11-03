---
layout: page
title: Edbert Geraldy Cangdinata's Project Portfolio Page
---

### Project: TA-Assist

TA Assist is a desktop application targeted at NUS Teaching Assistants (TA). It helps them to keep track of their students' grades, attendance, and work submission status of relevant modules.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.
Given below are my contributions to the project.

* **New Feature**:
    * Implemented `Session`-related behaviour, including:
        * a `Session` class to encapsulate data regarding a `Session` and its associated methods
        * a `JsonAdaptedSession` class to handle storing `Session` data.
        * Integration between the new `Session` class with the TA-Assist architecture.
        * added `session` and `lists` command, allowing users to add sessions and see a list of sessions.
    * Implemented `scores` command that displays student's grade for a session through the UI, this includes:
      * a `StudentView` class to encapsulate `Student` data to be displayed to `UI`.
      * a `MappedStudentViewList` class to map an `ObservableList<Student>` to `ObservableList<StudentView>`
      * UI modifications to display grades within the StudentCard.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=berted&breakdown=true).

* **Project management**:
    * Authored [45 issues](https://github.com/AY2223S1-CS2103T-T12-1/tp/issues?q=is%3Aissue+author%3ABerted) to help keep track of current issues and progress.
    * Reviewed [45 pull requests](https://github.com/AY2223S1-CS2103T-T12-1/tp/pulls?q=is%3Apr+reviewed-by%3ABerted) to ensure quality and consistency across the database.

* **Enhancements to existing features**:
    * Fields such as `Address`,  `Phone Number` and `e-mail` are made optional to better support the requirements of TA-Assist.
    * Added a date field to `Session`
    * Fixed UI alignment issues being off-center, 1-2 pixels off, etc.
    * Proposed using the [Lato font](https://www.latofonts.com/) as our UI's font.

* **Documentation**:
    * Added details on parameter constraints
    * Updated acknowledgements page
    * Added implementation details on immutability of certain objects
    * Added implementation details on Identity interface
    * Added implementation details on managing sessions within a class
    * Added implementation details on querying student grades within a session
    * Added diagrams for a generic parsing execution flow to reduce duplication
    * Added tests to manual testing in the DG appendix
