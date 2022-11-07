---
layout: page
title: Chun Yu's Project Portfolio Page
---

### Project: Teaching Assistant Assistant

Teaching Assistant Assistant (TAA) is a desktop app for Teaching Assistants (TAs). It keeps track of TAs’ students,
tutorial groups, and tasks.


Given below are my contributions to the project.

* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=gunbux&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
  * [Created Issues/PRs](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues?q=author%3Agunbux+)

* **Enhancement Added**:
  * **New Feature**: Added the tasks functionality and commands
    * What it does: allows the user to add, delete, edit, and view tasks
    * Using the current AB3 workflow was not possible as we needed to assign students to tasks without interacting 
with the model. Instead, we had to create a new design for Tasks, where we delay the creation of the Task in the Parser
till we are able to search through the student list in model
  * **New Feature**: Added mass actions for index based commands
    * What it does: allows the user to perform an action on multiple students at once
    * This was done by modifying how index-based commands were implemented, alongside some changes to the parser as well.
  * Modified parser and commands to enable two word commands
  * Modified list commands to have sensible outputs

* **Documentation**:
  * User Guide:
    * Revamped User Guide to be more user friendly and intuitive [#212](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/212)
    * Fixed Formatting issues [#136](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/136), 
  [#132](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/132), [#35](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/35)

  * Developer Guide:
    * Added User Stories, User Profile and Value Proposition [#20](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/20)
    * Update UML Diagrams [#54](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/54)
    * Added DG Implementation details for Tasks and Mass Actions [#106](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/106), 
    * Overall formatting and delivery of DG [#257](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/257), [#246](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/246)
    * Added Appendix for Manual Testing and Effort [#246](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/246)

* **Contribution to team-based tasks**
  * Helped set up git workflow and PRs: Github Projects, Issues, Squash commit workflows
  * Manage release for 1.3

* **Reviewing/mentoring contributions**:
  * Helped guide team members on Git workflows, resolving merge conflicts, and PRs
  * Helped review numerous PRs and issues. [#64](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/64), [#102](https://github.com/AY2223S1-CS2103T-T13-1/tp/pull/102)

* **Contributions beyond the project team**:
  * [Reported bugs for PED](https://github.com/gunbux/ped/issues)
