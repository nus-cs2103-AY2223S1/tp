
---
layout: page
title: Song Ivan's Project Portfolio Page
---

### Project: CodeConnect

CodeConnect is a specialised task management that can help NUS CS students keep track of their tasks in any modules and is linked to their local contacts to find students that take the same modules. This gives them an easy way to source for help within each module.  The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=songivan00&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements implemented**:
  * Updated the UI 
    * Changed the UI to support the TaskListPanel
    * Added the module tag to a task card
  * Changed naming convention for all Contacts features
    * Existing Contact classes and commands were changed to differentiate them from Tasks classes 
    * Error messages were also edited accordingly
  * Added a Modules field to Person
    * Relevant command such as adding contact and editing contact were updated to take in this new field
    * Wrote relevant tests cases to ensure code coverage
  * Added new feature edit task
    * Allowed users to edit tasks easily
    * This feature improves the product significantly because previously, to edit a task users had to delete the task and add the task back with the changes.
    This command provide a convenient way to edit their tasks
    * Wrote test cases to ensure code coverage 
  * Changed some fields in contacts to be optional
    * Made name and phone number to be the only compulsory fields in Person
    * Other fields were all represented by a "-" if nothing was keyed in 
    * This enhancement meant that users have the choice to add only the fields that they want to include.
    * Edited existing test cases 
  * Fixed any bugs detected

* **Contributions to Documentation**:
  * User Guide:
    * Added documentation for the features `list` and `listc`
    * Added documentation for the feature `edit`
    * Added the introduction and a "How to use this guide" section
    * Added explanations for the notations used in the Features section
    * Edited the fomatting and improved the Front Matters and Features sections of the UG
  * Developer Guide:
    * Added Non-Functional Requirements
    * Worked on the implementation of the proposed edit task feature in v1.3
    
  

* **Contributions to team-based tasks**:
  * Submitted product name, target user profile and value proposition via TEAMMATES
  * Made the Demo video for v1.3
  * Constant bug testing and debugging

* **Review/mentoring Contributions**
  * Primary reviewer of danzzzerl's PR 
  * Occasionally review other team members PR when needed