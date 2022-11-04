---
layout: page
title: Rachel Chua's Project Portfolio Page
---

### Project: MODPRO

MODPRO is a desktop application which helps NUS students in tracking the progress of their modules. It is highly optimised for students who prefer Command Line Interface (CLI) by allowing those who type fast to key in commands to track their modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: To be added soon
  * Added an exam class and its following fields classes to store the exams in an exam list.
    * What it does: Implemented an exam column so that user can see their exam in one column, link the revision tasks to the exam and thus, track their progress for the exam.
    * Justification: Allows students to track their revision progress for the exam so that they can stay on track for their revision despite their busy schedule and finish their revision on time.
  * Added the AddExamCommand class together with other exam related fields classes.
    * What it does: Add an exam to the exam list with the following fields of Exam Description, Exam Date, and Module
    * Justification: Allows you to add an exam in exam list to track your exams.
    * Credits: Referenced AB3 implementation for add person. 
  * Added the EditExamCommand class 
    * What it does: Edit an exam in the exam list
    * Justification: If the user type a wrong exam into the exam list, he or she can easily change it using our edit exam command
    * Credits: Referenced AB3 implementation for edit person. 
  * Added the FindTasksCommand class 
    * What it does: Find the tasks that contain the keyword inputted partially or fully by the user 
    * Justification: User can now easily find the tasks by keyword inputted from the long list of tasks to do as it is difficult to find a task manually by scrolling.
    * Credits: Referenced AB3 implementation for find person.
  * Added the ListTasksCommand
    * What it does: List all the tasks in the task list
    * Justification: Allows user to go back to the main list of tasks after keying commands such as find tasks or filter tasks command.
    * Credits: Referenced AB3 implementation for list persons. 
  * Added the FindModulesCommand class
    * What it does: Find the modules that contain the keyword inputted partially or fully by the user
    * Justification: User can now easily find the modules by keyword inputted from the long list of modules that they have taken as it is difficult to find a module manually by scrolling.
    * Credits: Referenced AB3 implementation for find person.
  * Added the ListModulesCommand
    * What it does: List all the modules in the task list
    * Justification: Allows user to go back to the main list of modules after keying commands such as find module command.
    * Credits: Referenced AB3 implementation for list persons.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=rachelchua)

* **Documentation**:
    * User Guide:
  Wrote documentation for 
      * FindTasks command, 
      * FindModules command, 
      * ListTasks command,  
      * ListModules command, 
      * AddExamCommand, 
      * EditExamCommand. 
    * Developer Guide:
        * AddExamCommand. Included the sequence diagram. 

* **Community**:
    * PRs reviewed.
    * Reported bugs and suggestions for other teams in the class when reviewing their DG, and UG, as well as in PE dry run.
* **Team Based**:
    * Contributed to the UI design of the app.

