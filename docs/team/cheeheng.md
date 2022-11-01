---
layout: page
title: Tan Chee Heng's Project Portfolio Page
---

### Project: Plannit

**Plannit** is an **all-in-one application** that streamlines the execution of module
deliverables by **empowering NUS students** with the ability to manage **tasks**, **links** and
**module-mates** (i.e., students in the same module) to increase their productivity.

Given below are my contributions to the project.

* **New Feature**: `add-person-to-module` command
  * What it does: Creates a link from a person to a module. 
  * Justification: This feature allows students to see which person takes a particular module 
    via the `goto` command, so that they can find suitable study buddies whenever they are 
    struggling academically.
  * Highlights: This enhancement was not easy because it involved an interaction between two 
    different kinds of `Model` objects, namely `Person` and `Module`, while require special 
    thought on the design. This was previously not in the design of AB3.

* **New Feature**: add/delete/edit module

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cheeheng&breakdown=true)

* **Project management**:
  * Managed releases v1.2.1, v1.3.0, v1.3.1 and v1.3.2

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add-module`, `delete-module`, `edit-module` and 
      `add-person-to-module` (e.g, [#47](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/47) and 
      [#108](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/108))
    * Updated Quick Start section of user guide to show Plannit instead of AddressBook.
  * Developer Guide (see [#82](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/82)):
    * Added implementation details for the `Module` section of the `Model` component.
    * Added Object-oriented domain model for the relationship between `Module` and `Person`.

* **Contributions to team-based tasks**:
  * Set up [Plannit](https://github.com/AY2223S1-CS2103T-T10-1/tp) GitHub repository
  * Renamed product from AddressBook to Plannit
  * Set up Gradle and Codecov
  * Enabled assertions (see [#85](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/85))
  * Updated `README.md` to show value proposition of Plannit.
  * Updated title of webpage to Plannit from AB3.

* **Review contributions**:
  * Reviewed numerous PRs (e.g., [#46](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/46), 
    [#61](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/61)
     and [#103](https://github.com/AY2223S1-CS2103T-T10-1/tp/pull/103)) 

* **Contributions beyond the project team**:
  * Sent 13 bug reports in [InternConnect](https://github.com/AY2223S1-CS2103-F14-2/tp) during 
    the practical exam dry run.
