---
layout: page
title: Yee Thern's Project Portfolio Page
---

### Project: HR Pro Max++

HR Pro Max++ is a desktop team management application for SME company to manage their team members, project details and their tasks.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Enables users to add staffs to the staff list of any project in HR Pro Max++.
    * What it does: Allows users to add staff members.
    * Justification: Users need to be able to add staffs to the staff list of any project they want. We didn't follow the model
    of operating on the current active Project's staff list because there might be situations where users are working on Project A,
    but suddenly have to onboard a new team member onto Project B, so forcing users to swap to Project B just to perform this task
    seems counter-intuitive.
    * Credits: Took inspiration from AB3's `add` command.

* **New Feature**: Enables users to find any staff in  a staff list in the current active project in HR Pro Max++.
  * What it does: Allows users to key in a list of keywords and returns a list of all staff members whose names contain
  any of the keywords.
  * Justification: The initial design consideration was to allow for precise matching, i.e., if the user were to run `findstaff 1 John Doe`,
  only `John Doe` would be listed. The team decided that it would be better for users to match by keywords, similar to AB3's design
  to allow users who aren't sure about `John Doe`'s full name to be able to find him, i.e., by checking the list of `John`.
  * Credits: Took inspiration from AB3's `find` command.

* **New Feature**:  Enables users to edit staff details of a given staff in the current active project in HR Pro Max++.
  * What it does: Allows users to edit the staff details of a staff, specified by the index of the staff in the displayed staff list.
  * Justification: Edit staff operates on the current active Project's staff list because it makes sense for users to first view
  which staff they want to edit before editing said staff's details.
  * Credits: Took inspiration from AB3's `edit` command.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=augustdespair&breakdown=true)

* **Project management**:
  * Reviewed the teams' PR: Pull Request [#128](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/128), [#199](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/199)

* **Enhancements to existing features**:
  * `containsNameIgnoreCase` in `StringUtil` to handle the checking of the instance of a substring being present inside a String.
    * Credits: Took inspiration from `Dave L.`'s response in https://stackoverflow.com/questions/86780/how-to-check-if-a-string-contains-another-string-in-a-case-insensitive-manner-in
  * Added methods to `Model` to support adding, deleting and editing staff, such as `Model.getStaffFromProjectAtIndex()` and 
  `Model.addStaffToProject()`. These methods were added to improve code quality and better adherence to SOLID principles, since it 
  ensured that all commands involving the manipulation of `StaffList` communicated via the `Model` interface, instead of allowing 
  commands to directly call methods from classes in the `Model` package. 

* **Documentation**:
  * Developer Guide:
    * Added Developer Guide for `AddStaffCommand`:  [#113](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/113), [#221](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/221/) 
    * Added Developer Guide for `EditStaffCommand` and `FindStaffCommand`: [#221](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/221/)
  * User Guide:
    * Added User Guide for `EditStaffCommand` and `FindStaffCommand`: [#136](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/136/), [#194](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/194/)
    * Added User Guide for `AddTaskCommand`, `DeleteTaskCommand` and `FindTaskCommand`: [#138](https://github.com/AY2223S1-CS2103T-T09-3/tp/pull/138/)
* **Community**:
  * PE Dry Run: Found a total of 4 bugs and suggestions [within this Repo](https://github.com/AugustDespair/ped).
