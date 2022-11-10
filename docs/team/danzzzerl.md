---
layout: page
title: Denzel Tan's Project Portfolio Page
---

# Project: CodeConnect

## Overview
CodeConnect is a specialised task management that can help NUS CS students keep track of their tasks in any modules and
is linked to their local contacts to find students that take the same modules. This gives them an easy way to source
for help within each module.  The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.
Given below are my contributions to the project.

## Summary of Contributions

### Code Contributed
- [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=danzzzerl&breakdown=true)

### Enhancements Implemented
* New Feature: Find task
  * find tasks by either keywords or modules
  * created new classes to be able to implement this new function for task management
    * `FindTaskCommand` for the overall find task command
    * `FindTaskCommandParser` to parse user input
    * `TaskContainsKeywordsPredicate` for finding tasks by their name
    * `TaskContainsModulesPredicate` for finding tasks by module
  * integrated the new classes into the current model for it to work
  * it was hard because of the high level of abstraction of the commands used by the logic and model, and I had to make this new feature in a similar way
  * wrote new test cases and created new test case classes to test the new classes and methods
  
* Adding of Github and Telegram fields for contacts
  * updated code for `addc` and `editc` commands, which are commands for adding contacts and editing contacts
  * `addc` had to be edited to be able to add the 2 new fields (and be made optional)
  * `editc` had to be edited to be able to edit the 2 new fields of any contact

* UI improvements
  * Upgrading the Ui for tasks and contact cards to include the different fields and have a different look
  * added new labels and code for the Ui and `.fxml` files

* Fixing of bugs after iterations

* Improve Code Quality of overall source code

### Contributions to UG
* Add documentation for the features `find` and `findc`
* Update language in UG
* UG tips in `addc` command usage
* Edit examples of commands 
* Fix UG errors after review iterations

### Contributions to DG
* Help to edit the new Ui class diagram using plantuml
* Add use cases for `add` and `find` task, `clean`, `clear`, `saveme`, `list`, `list time`
* Add more NFRs
* Add documentation for finding feature
  * Update the new sequence diagram for the finding feature using plantuml
* Overall presentation

### Contributions to Team-Based Tasks
* Helped with the editing of the Ui base at the start of the project when Ui, Model, and Storage had to be changed in order for the new features to be implemented
* Fixing of bugs
* Reviewing and merging code every week
* Release of CodeConnect MVP and v1.3

### Reviewing/Mentoring contributions
* Our team set up a reviewing system where each person reviews one other person's PR and merges it
* Primary reviewer for JonLamy's PRs
* This was not a definite system as those who were more free helped to review other PRs whenever possible as well
