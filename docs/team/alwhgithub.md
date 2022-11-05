---
layout: page
title: Aaron Li's Project Portfolio Page
---

### Project: AddressBook Level 3

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added feature to display Person within Tasks, and display Task within Person
  * What it does: For each task, displays the person that the task is assigned to. For each person, displays the list of all tasks that is assigned to that person
  * Justification: Displaying the link between tasks and people assigned is one of our core features.
  * Highlights: This enhancement required the implementation of the task and person classes to be fundementally changed, as well as the way that the edit command changed the values of the task and person command. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to various classes.


* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `v1.3` - `v1.4` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Updated link for help command [\#162]()
  * Made parameters for commands case insensitive [\#162]()
  * Implemented errors for incorrect category when adding/editing task [\#162]()
  * Created test cases for editTask, addTask and deleteTask [\#59]()
  * Created classes for Deadline and Category for the Task class [\#42]()

* **Documentation**:
  * User Guide:
    * Updated add, delete and edit commands to be consistent with new features. [\#173]()
    * Updated find command image to be consistent with updated UI and command format [\#173]()
    * Updated Quickstart image to be consistent with Updated UI and added labels for each section of the interface [#\169]()
    * Documented what purpose each section of the interface serves [\#169]()
    * 
  * Developer Guide:
    * Updated appendix to be consistent with new features

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#54](), [\#90]()
  * Reported bugs and suggestions for other teams in the class
 

