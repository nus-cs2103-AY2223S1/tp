---
layout: page 
title: Zheng Yilin's Portfolio Page
---

## Project: `Arrow`

**Arrow** is a **desktop app** that **help software project managers keep track of their members’ tasks and deliverables**.
By associating tasks to specific team members, users can **keep track of what needs to be done and have quick access to
contact information should they wish to reach out to the member**.

Given below are my contributions to the project.

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=yilinzyl&breakdown=true)

* **New Feature**: Added the ability to assign contacts to tasks.
    * What it does: Allows the user to assign or unassign a contact to a task.
    * Justification: This feature helps the user, a manager with a key responsibility: work delegation.
    * Highlights: This feature requires me to create new classes. Also, it not only allows addition of a person by index, but also by their full name which was more complicated to parse.


* **New Feature**: Added the ability to tag a task with a project.
    * What it does: Allows users to tag a project under a task.
    * Justification: This feature helps the user to group tasks under relevant projects, like in real world.
    * Highlights: This feature requires me to create new classes and update Storage of tasks.


* **New Feature**: Added the ability to find teammates by tags.
    * What it does: Allows users to find teammates by tag.
    * Justification: This feature allows the user to filter for all persons matching a specified tag, which is useful for managers who want to find teammates that can fulfill a particular role.
    * Highlights: This feature requires me to create a new predicate class for filtering using tags.

* **Enhancements to existing features**:
    * Edited EditCommand and DeleteCommand to iterate through all Tasks upon editting/deleting a Teammate. This is to update the assigned Contacts of these Tasks to reflect the change/deletion of the Teammate.

* **Documentation**:
    * User Guide: 
      * Added documentation and command summary of `task assign`, updated `find` to reflect search for tasks
    * Developer Guide:
      * Added implemention of  `task assign`
      * Updated UI, Model, Storage to reflect new fields in Task
      * Added uses cases

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#73](https://github.com/AY2223S1-CS2103T-T08-2/tp/pull/73), [#75](https://github.com/AY2223S1-CS2103T-T08-2/tp/pull/75), [#108](https://github.com/AY2223S1-CS2103T-T08-2/tp/pull/108), [#118](https://github.com/AY2223S1-CS2103T-T08-2/tp/pull/118)
