---
layout: page
title: Denzel Tan's Project Portfolio Page
---

### Project: CodeConnect

CodeConnect is a specialised task management that can help NUS CS students keep track of their tasks in any modules and
is linked to their local contacts to find students that take the same modules. This gives them an easy way to source
for help within each module.  The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * Implemented find feature for tasks:
    * find tasks by either keywords or modules
    * created new classes to be able to implement this new function for task management
    * integrated the new classes into the current model for it to work
    * it was hard because of the high level of abstraction of the commands used by the logic and model, and I had to make this new feature in a similar way
    * wrote new test cases and created new test case classes to test the new classes and methods

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=danzzzerl&breakdown=true)

* **Enhancements implemented**:
  * Find feature as mentioned above
  * Adding of Github and Telegram fields for contacts
    * updated code for `addc` and `editc` commands, which are commands for adding contacts and editing contacts
    * `addc` had to be edited to be able to add the 2 new fields (and be made optional)
    * `editc` had to be edited to be able to edit the 2 new fields of any contact
  * Upgrading the Ui for tasks and contact cards to include the different fields and have a different look
    * added new labels and code for the Ui and `.fxml` files
  * Fixing of bugs after iterations

* **Documentation**:
  * User Guide:
    * Added documentation for the features `find` and `findc`
    * Updated language in UG
    * Fixed UG errors after review iterations
  * Developer Guide:
    * Helped to edit the new Ui class diagram using plantuml
    * Added use case for finding tasks
    * Added documentation for finding feature
      * Updated the new sequence diagram for the finding feature using plantuml

* **Contributions to team-based tasks**
  * Helped with the editing of the Ui base at the start of the project when Ui, Model, and Storage had to be changed in order for the new features to be implemented
  * Fixing of bugs
  * Reviewing and merging code every week

* **Reviewing/Mentoring contributions**
  * Our team set up a reviewing system where each person reviews one other person's PR and merges it
  * For myself, I reviewed and merged JonLamy's PRs
  * This was not a definite system as those who were more free helped to review other PRs whenever possible as well
