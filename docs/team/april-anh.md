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

* **Project management**:
  * Responsible for admin tasks (wrap up milestone, release jar file, submit documentation,...)

* **Documentation**:
  * User Guide:
    * Product introduction
    * Add feature: add & delete consultation to the feature list
  * Developer Guide:
    * to be added soon

* **Contributions to team-based tasks**:
  * Upload draft UG
  * Release v1.3.trial
  * Wrap up v1.2

* **Review/mentoring contributions**: to be added soon
  * PR #25
  * PR #39
  * PR #53
  * PR #54
  * PR #56

* **Contributions beyond the project team**: to be added soon
