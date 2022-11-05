---
layout: page
title: Po Hsien's Project Portfolio Page
---

### Project: StudMap

StudMap is a lightweight desktop application that addresses your needs of managing the student records across multiple spreadsheets. Using a CLI (Command Line Interface) approach, we mitigates the need for teaching assistants to navigate throught convoluted menus and buttons when keeping track of the administrative details for the students. It is written in Java with the GUI implemented using JavaFX. <br>

Given below are my contributions to the project.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=lpohsien)

* **New Feature**: `tag` and `untag`
  - _What it does_: The `tag` features allow users to append tags to the student, while the `untag` commands allows the user to remove specific tags from the students
  - _Justification_: The user (teaching assistants) might be required to keep track of the students, as such, he or she might want to differentiate the students using tags. However, some might require multiple tags to track different aspects of the student. For example, one might want to tag a student as required to attend 2 separate remedial classes. In this case, the two tags might not be added or remove at the same time. With the `tag` and `untag` commands, this provide the user with more fine-grained control over the taggin process of the student.
  - _Highlights_: In the original addressbook, the tagging features is combined with the `edit` feature for modifying the records of a student. Appending tags was troublesome as the `edit` command overwrites pre-existing tags. Hence, I have built upon the existing `tag` attribute to create the `tag` features that allows cumulative addition for the tags. I have also introduced the mass operation feature for adding the tags. This mass operations was further improved by Sheyuan to be more general and extensible to other features as wel.

* **New Feature**: `tag` and `untag`
  - _What it does_: By using the `grade`, the user (teaching assistants) can create records of  assignment for the student and toggle between the supported status for assignment grading. The user can also use the `ungrade` command to remove records of the assignments completely.
  - _Justification_: The user may have several assignments to mark from various students which he or she needs to keep track of. By introducing this feature, the user can record down the grading status for the assignments which serves to remind the user on which assignments he or she has not yet graded, allowing them to have a clearer view for their assignment grading process.
  - _Highlights_: To implement the feature, I have also created the `Assignment` attribute for students. By using the `grade`, the teaching assistants can create records of assignment for the student and toggle between the supported status for assignment grading. The teaching assitant can also remove entries of the assignment using the `ungrade` command.

* **Documentation**:
  - Provided the necessary documentation for the `tag`, `untag`, `grade` and `ungrade` feaatures for both the User Guide and
  Developer Guide.[#165](https://github.com/AY2223S1-CS2103T-W13-1/tp/pull/165)
  - Refactored the Developer Guide for the `edit`, `tag`, `mark`, `grade` such that they are grouped under the `EditStudent` features. These commands all servers similar purpose of editing specific attributes of the student. They also have very similar code structure as they are inheriting from the `EditStudentCommand` interface. By grouping these features together, it makes the documentation of implementation details for these commands to be more concise and readible. [#102](https://github.com/AY2223S1-CS2103T-W13-1/tp/pull/102)
  -  Refactored develop guide to reflect the change from addressbook to studmap. Update the various UML diagrams to reference StudMap and modify class diagrams for the `model` component based on current implementation. [#99](https://github.com/AY2223S1-CS2103T-W13-1/tp/pull/99)

* **Project management**:
  * Review functional PRs for `v1.3`
  * Updating demo screenshot on project notes for `v1.3`
  * Review and merge various documentation PRs for `v1.4`



