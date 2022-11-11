---
layout: page
title: Nguyen Doan Phuong Anh's Project Portfolio Page
---

# Overview
ModQuik is a desktop app for NUS Teaching Assistants, optimised for use via a Command Line Interface (CLI). It allows TAs to keep track of their responsibilities, students’ progress and schedules for the ongoing semester.

The ModQuik codebase was initially adapted from AddressBook Level 3, a desktop address book application written in Java. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

## Summary of contributions
Given below are my contributions to the project.

### Code contributed: 
  * [RepoSense](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=april-anh&breakdown=true)
  * [Pull requests](https://github.com/AY2223S1-CS2103T-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Aapril-anh)

### Enhancements implemented: 
  * **New feature** `add consultation`
    * What is does: add a consultation slot at the given name, time, date, place and description
    * Justification: This feature is highly relevant to the product because it supports an important aspect of being a teaching assistant—managing consultations with students. 
    Users are able to add in new consultations, which can be used for organizing schedule and providing overview of students' doubts. 
    For instance, this feature prevents user from forgetting their scheduled consultation with their student. 
    Also, by seeing all students' question at one glance, user can easily spot the common questions among all students. 
    * Highlight: `add consultation` requires a unique list for storage, separate classes for its fields and parsers for all these fields.

  * **New feature** `delete consultation`
    * What is does: deletes a specified consultation with given index
    * Justification: The user should be able to remove consultations in case they made mistakes, the consultations are cancelled or no longer relevant.

  * **New feature** `edit consultation`
    * What is does: edit a specified consultation with given index and field
    * Justification: This feature improves the user experience because users can edit an incorrect consultation, instead of having to delete and create a new consultation. 
      Moreover, consultation have 5 different fields, hence, deleting and creating a new one just to correct a minor typo is very annoying.

  * **New feature** `clear`
    * What is does: allow user to clear all existing data in a specific field (including student, tutorial, reminder and consultation) or the entire app.
    * Justification: This feature improves the user experience because users can clear a large amount of data in a specific fields, instead of having to delete one by one.
      For instance, after a long semester, user may want to restart their app and input new entries as the existing data are no longer useful. In such cases, using `clear` command to delete all at one shot is simple and quick. 
    * Highlight: `clear` command is a cross-component feature as it requires access to the lists of students, tutorials, consultations, reminders or all. 
    Implementing `clear` to compile with OOP principle, i.e. preventing exposing internal structures, was quite challenging. 


### Documentation:
  * User Guide:
    * Completed documentation for consultation features: [283](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/283),...
  * Developer Guide:
    * Completed user stories and value proposition: [248](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/248)
    * Add test cases for consultation and student features in manual testing section: [263](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/263), [289](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/289)
    * Update storage UML diagram: [266](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/266)
    * Update architecture UML diagram: [279](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/279)
    * Add `add student` activity diagram [285](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/285), [912](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/912)

### Project management:
  * Responsible for admin tasks (wrap up milestone, release jar file, submit documentation,...)
  * Contributions to team-based tasks:
    * Upload draft UG, jar file, UG, DG, demo video
    * Release v1.3.trial, v1.3.0, v1.4
    * Wrap up v1.2, v1.3, 1.4

### Review/mentoring contributions: [About 45 pull requests](https://github.com/AY2223S1-CS2103T-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
  * PRs reviewed (with non-trivial review comments): [229](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/229), 
  [102](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/102), 
  [103](https://github.com/AY2223S1-CS2103T-W17-3/tp/pull/103),...

### Contributions beyond the project team: 
  * Contributed to forum discussions [7 issues](https://github.com/nus-cs2103-AY2223S1/forum/issues?q=is%3Aissue+author%3Aapril-anh)
  * Reported bugs and suggestions for other teams in the class [8 issues](https://github.com/AY2223S1-CS2103T-F11-2/tp/issues?q=is%3Aissue+april-anh)
