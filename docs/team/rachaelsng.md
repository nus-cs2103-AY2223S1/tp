---
layout: page
title: Sng Su May, Rachael's Project Portfolio Page
---

### Project: JARVIS

JARVIS is a desktop application that allows a CS1101S TA to manage his/her students and tasks in an organised manner. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to view the full list of students.
  * What it does: Allows the user to see the full list of students.
  * Justification: The default GUI that the user sees does not show the full list of students. Hence, this feature allows the user to view the full list to see all students in the system and their details recorded.

* **New Feature**: Added the ability to view the list of tasks.
  * What it does: Allows the user to see the full list of tasks.
  * Justification: The default GUI that the user sees does not show the full list of tasks. Hence, this feature allows the user to view the full list to see all tasks in the system and their details recorded.

* **New features**: Implement storage for lessons.
  * What it does: Allows lessons to be saved in the hard disk whenever the lesson list changes.
  * Justification: There was no existing feature allowing lessons to be saved into storage. This new feature allows the user to have their changes to the lessons they changed saved, so that the data is restored by loading from the hard disk when JARVIS starts up again.
  * Highlights: The storage component for lessons is implemented using inheritance and polymorphism, representing the IS-A relationships between the lesson types (consultations, mastery checks and studios) and the lesson.
  * Challenges faced: The implementation required careful design considerations to ensure minimal dependencies, and to avoid regressions in the code.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=rachaelsng&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Contributed to ideation and team direction.
  * Ensured timely submissions for tasks.

* **Documentation**:
  * User Guide:
    * Added tips for the user to differentiate students with the same name. [PR #153](https://github.com/AY2223S1-CS2103T-T11-3/tp/pull/153)
  * Developer Guide:
    * Added implementation details of storage component.
    * Updated UML diagrams for storage component.
    * Added implementation details of the listing of tasks, students and lessons. [PR #97](https://github.com/AY2223S1-CS2103T-T11-3/tp/pull/97)

* **Community**:
  * Helped Team CS2103T-T14-2 find bugs during PE-D.
