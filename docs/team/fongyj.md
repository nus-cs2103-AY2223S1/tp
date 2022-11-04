---
layout: page
title: Yih Jie's Project Portfolio Page
---

### Project: JARVIS

JARVIS is a student, task and lesson management desktop application for a CS1101S Teaching Assistant. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Add and delete notes to lesson / student in a lesson
  * What it does: Allows the user to add notes to a `Lesson` or to a specific `Student` in the `Lesson`. The user can also choose to delete the note(s) added.
  * Justification: Adding notes will allow the tutor to note down important issues and ideas, helping the tutor to remember things and plan lessons better. Deleting notes will allow the tutor to delete notes that are wrongly added or no longer relevant.

* **New Feature**: Mark and unmark lesson / student
    * What it does: Allows the user to mark a lesson as done or mark a student as present in a lesson. The tutor can also unmark the lesson or student.
    * Justification: Marking a lesson or student as done or present will allow the tutor to more easily keep track of lessons and student attendance.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=fongyj&breakdown=true)

* **Project management**:
  * [to be added]

* **Enhancements to existing features**:
  * Added `Task` class to the `Model` component. Also added other classes relevant to `Task` and added storage functionality for `Task` as well as a basic GUI for displaying the list of tasks.

* **Documentation**:
    * User Guide:
      * Formatted and added rough content and screenshots for all the commands in JARVIS.
    * Developer Guide:
      * Added implementation details for adding notes to a lesson or to a student in a lesson.

* **Community**:
  * PRs reviewed: [#89](https://github.com/AY2223S1-CS2103T-T11-3/tp/pull/89), [#109](https://github.com/AY2223S1-CS2103T-T11-3/tp/pull/109)
  * Helped Team CS2103T-F11-3 find bugs during PE-D.