---
layout: page
title: Lim Ai Lin's Project Portfolio Page
---

# Overview
ModQuik is a desktop app for NUS CS Professors, optimised for use via a Command Line Interface (CLI).
It allows the professors to keep track of their students, teaching assistants and timetable for their courses.

## Summary of contributions
Given below are my contributions to the project.

* **Code contributed**: [LimAiLin's tP Dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=LimAiLin&breakdown=true)

* **Enhancements implemented**:
  * Added the ability to add, edit and delete students.
      * What it does: Allows the user to add, edit and delete any students from the student list.
      * Highlights: This enhancement affects existing command as it was similar to the `add`, `edit` and `delete` commands that AddressBook already has.
        Thus, there was a need to abstract out similar components and adhere to the DRY principle.
  * Added and enhanced student fields.
     * Attendance
     * Grade
     * Participation
     * Phone
     * StudentId
     * TelegramHandle
  * Added the ability to mark and unmark reminders.
      * What it does: Allows the user to mark their reminder as done when they complete a task, or unmark their reminder as undone if they have not completed the task.
      * Justification: This feature makes our product more attractive as users keep track of the tasks they have or have not done. This acts like a ToDo list in ModQuik.
  * Enhanced ModQuik's parser.
      * Highlights: This enhancement parses commands with no parameters such as `exit exit` or `list list` instead of throwing an error.

* **Documentation**:
    * User Guide:
        * Organised and formatted the structure and outline.
        * Added documentation for the following sections:
          * `Navigating the User Guide`
          * `Navigating the GUI`
          * `Adding a student`
          * `Editing a student`
          * `Deleting a student`
          * `Extracting student's emails`
          * `Mark a reminder`
          * `Unmark a reminder`
        * Added documentation for the following features:
          * `Adding a student`
          * `Editing a student`
          * `Deleting a student`
        * Added visuals for:
          * `Student Features`
          * `Tutorial Features`
          * `Consultation Features`
          * `Reminder Features`
          * `GUI Overview`
    * Developer Guide:
        * Organised and formatted the structure and outline.
        * Added documentation for the following sections:
          * `Add Student Feature`
          * `Edit Student Feature`
          * `Delete Student Feature`
        * Added diagrams:
          * Model Class Diagram
          * Add Student Command Sequence Diagram
          * Edit Student Command Sequence Diagram
          * Delete Student Command Sequence Diagram

* **Contribution to team-based tasks**:
    * Assigned issues to teammates.
    * Assigned tags to issues.
