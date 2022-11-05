---
layout: page
title: Ryan Guai's Project Portfolio Page
---

### Project: YellowBook

YellowBook is for university students who are involved in many projects and have to organize their project contacts and tasks. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add/delete tags from contacts/tasks.[#113](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/113) [#135](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/135) [#166](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/166) [#123](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/123)
  * What it does: allows the user to add/delete tags from contacts/tasks.
  * Justification: This feature improves the product significantly because it allows the user to label
  people/tasks with the context of which project it belongs to. This allows the user to look for contacts/tasks from a
  specific project without having to look through the entire list.
  * Highlights: This enhancement required me to set up an entirely new Tag class and a unique list of Tags that 
  responded dynamically to user input, in addition to the commands themselves. It required an in-depth analysis of 
  design alternatives. The implementation was challenging as I had to consider how to support adding tags to 
  both contacts and tasks concurrently.

* **New Feature**: Added the ability to list all existing tags. [#177](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/177)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ryanguai&breakdown=true)

* **Project management**:
  * Set up team org and team repo
  * Set up Github Issue Tracker for user stories and task allocation
  * Update build status on README [#55](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/55)
  * Update project side-wide settings [#56](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/56)

* **Enhancements to existing features**:
  * Updated GUI to reflect archival status of tasks [#194](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/194)
  * Wrote additional tests for existing features to increase coverage (Pull request [#237](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/237))
  * Added storage for tags [#172](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/172)

* **Documentation**:
  * User Guide: [#86](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/86) [#170](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/170)
    * Added documentation for the features addL, deleteL and listL
    * Updated command table with addL, deleteL and listL
    * Added short section summary for Contacts, Tasks and Tags [#236](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/236)
  * Developer Guide:
    * Added implementation details of the addL feature [#153](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/153)
    * Added use cases and glossary terms [#85](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/85)
* **Community**:
  * PRs reviewed (with non-trivial review comments): [#126](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/126) [#165](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/165)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/281))

* **Tools**:
  * Integrated CodeCov into the project
