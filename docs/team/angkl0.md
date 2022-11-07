---
layout: page
title: Ang Kuang Long's Project Portfolio Page
---

### Project: CodeConnect

CodeConnect is a specialised task management that can help NUS CS students keep track of their tasks in any modules and is linked to their local contacts to find students that take the same modules. This gives them an easy way to source for help within each module.  The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * `mark` / `unmark` task commands
  * `list` / `list time` task commands

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=angkl0&breakdown=true)

* **Enhancements to existing features**:
  * Extended storage to store tasks
  * Updated UI to include a task's status in a taskcard
  * Enhanced `find` and `findc` command to return entries which match keyword partially

* **Documentation**:
  * User Guide:
    * Added guide for the usage of the following features:
      * `addc` 
      * `delc`
    * Added note on extraneous parameters for `list`
  * Developer Guide:
    * Added documentation for the implementation of the following features:
      * `mark` `unmark` 
        * Added sequence and activity diagrams for `mark` 
      * `list` `list time` 
        * Added sequence diagram for `list` 
    * Added details on testing the saving of data under Appendix: Instructions for manual testing

* **Community**: 
  * Primary reviewer of songivan00's PRs
