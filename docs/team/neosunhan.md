---
layout: page
title: Sun Han's Project Portfolio Page
---

### Project: JARVIS

JARVIS is a desktop application that allows a CS1101S TA to manage their students, tasks and lessons in an organised manner. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Mark tasks as completed / not completed. 
  * What it does: Allows the user to mark a task as completed / not completed.
  * Justification: 
    * Allows the user to designate which tasks have already been completed so that they can focus on tasks that have not been completed yet.
    * After marking tasks as completed, users may subsequently realise that there is more work to be done for the task. Users need a way to mark tasks as not completed so they know that they have to continue working on the task.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=neosunhan&breakdown=true)

* **Project management**:
  * Set up GitHub team organization + project repo
    * Set up CodeCov
  * Refactored project from `AddressBook` to `JARVIS`
  * Triaged PE-D bugs
    * Closed duplicate bugs and invalid bug reports
  * Managed v1.2.1 release on GitHub

* **Enhancements to existing features**:
  * Added matriculation number to `Student` objects to differentiate between students with the same name. Used matriculation to test for Student equality in classes where `Student` is used as a `HashMap` key.
  * Added `Lesson` subclasses in `Model` component. `Lesson` is an abstract class that is implemented by concrete classes `Studio`, `MasteryCheck` and `Consult`, each of which is composed of individual components such as `LessonAttendance` and `TimePeriod`. These classes are used by various Lesson commands.

* **Documentation**:
  * User Guide:
    * [to be added soon]
  * Developer Guide:
    * Updated UML diagrams for `Logic` and `Model` components
    * Added implementation details for `marktask` and `unmarktask` feature

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#89](https://github.com/AY2223S1-CS2103T-T11-3/tp/pull/89), [#92](https://github.com/AY2223S1-CS2103T-T11-3/tp/pull/92), [#93](https://github.com/AY2223S1-CS2103T-T11-3/tp/pull/93), [#100](https://github.com/AY2223S1-CS2103T-T11-3/tp/pull/100)
  * Reported bugs for team CS2103T-T09-4: [#2](https://github.com/neosunhan/ped/issues/2)
