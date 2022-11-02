---
layout: page
title: Max Ng's Project Portfolio Page
---

### Project: HR Pro Max++

HR Pro Max++ is a desktop team management application for SME company to manage their team members and project details.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Task list command- Add, delete

* **New Feature**: Task list command- sortComplete
  * What it does: Sorts the tasks by whether it is completed or not, placing the uncompleted Tasks at the top and the rest at the bottom.
  * Highlights: Have to understand how the model works so that you can sort Tasks based on a field.
  * Justification: Users might want to sort to see what Tasks they have not completed.

* **New Feature**: Task list command- mark, unmark 
  * What it does: It allows the users to mark tasks as completed or not and the UI reflects it as so.
  * Highlights: Need to get the `TaskMark` field of Task object and update them based on the command. From there, the UI is changed to reflect if the Task is completed or not.
  * Justification: Users might want to mark Tasks as completed or not so they know their remaining workload.
  * Credits: 2103T IP idea of marking and unmarking Tasks
  
* **New Feature**: Staff list command- delstaff
  * What it does: It allows the users to mark tasks as completed or not and the UI reflects it as so.
  * Highlights: Different from the orignal Person class in AB3, as now there is a need to remove from a UniqueStaffList within a Project. Also to align to AB3, there is a need to limit the functionality where you can only delete from Staff and Projects currently displayed that increased the difficulty.
  * Justification: Users might want to remove a Staff from a Project.

* **Enhancement to existing features**:
  * `Tag` to allow for more than 1 word and a limit of 50 characters, update error message
  * `Deadline` field to use localDate parsing to allow for more accurate date inputs
  * Add new fields to the Staff class - `Staff department`, `Staff Contact`, `Staff Name`
  * Bug fixes (E.g. Pull Request [#78](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/78), [#140](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/140))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=maxng17&breakdown=true)

* **Project management**:
  * Set up issues for Issue Tracker
  * Manage the Jar file release for V1.2
  * Project demo for tutorial for V1.2 and V1.3
  * Review PR (e.g. Pull Request [#71](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/71), [#124](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/124))
  * Increase code coverage for test case (e.g. Pull Request [#84](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/84))

* **Documentation**:

  * User Guide
    * Command and prefix summary [#35](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/35)
  * Developer Guide
    * Add mark and unmark task implementation plans together with Diagrams (e.g. Pull request [#110](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/110))
    * Add delstaff command implementation

* **Community**:
  * PE dry run: Found a total of 15 bugs and suggestions [within this Repo](https://github.com/maxng17/ped/issues)

