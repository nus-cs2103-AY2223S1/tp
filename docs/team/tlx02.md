---
layout: page
title: Tan Li Xin's Project Portfolio Page
---

### Project: MODPRO

MODPRO is a desktop application which helps NUS students in tracking the progress of their modules. It is highly optimised for students who prefer Command Line Interface (CLI) by allowing those who type fast to key in commands to track their modules. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * Added the progress bar for modules
    * What it does: Calculates the percentage of tasks linked to the module, that are completed and shows the percentage on a progress bar. The percentage bar will update itself with each command from the user input.
    * Justification: This feature improves the product significantly because it is a key feature of our product as a module tracker.
    * Highlights: This enhancement is closely linked to existing commands since the percentage is affected by changes such as the editing and marking of tasks. The progress bar has to update itself to reflect these changes. Hence, it required analysis to find the most appropriate design structure and the implementation was difficult as it had to integrate well with other commands.
  * Added the progress bar for exams 
    * What it does: Calculates the percentage of tasks linked to the exam, that are completed and shows the percentage on a progress bar. The percentage bar will update itself with each command from the user input.
    * Justifications: This feature allows students to track their revision progress for their exams conveniently.
  * Added the MarkTaskCommand
    * What it does: Marks a task as completed. This is reflected on the GUI by adding a tick to the checkbox.
    * Justifications: This features allows students to focus on tasks they have not completed. It is also key to the implementation of the progress bar.
  * Added the UnmarkTaskCommand
    * What it does: Unmarks (labels as not completed) a task. This is reflected on the GUI by removing the tick from the checkbox.
  * Added the EditTaskCommand
    * What it does: Edits the details of a task with the new values provided by the user.
    * Justification: This feature allows students to correct their mistakes and make changes to the module and description of their tasks.
    * Credits: Referenced AB3 implementation for editing persons.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tlx02&breakdown=true)

* **Documentation**:
  * User Guide:
    * Added documentation for the Mark Task, Unmark Task, and Edit Task features
    * Added the command summary
  * Developer Guide:
    * Added implementation details of the Mark Task and Edit Task features
    * Added manual testing for Mark Task and Edit Task features
    * Added user stories and use cases for the features I implemented
  * **Community**:
    * Reviewed PRs
    * Reported bugs and suggestions for other teams in the class during the PE dry run ([1](https://github.com/tlx02/ped/issues/3), [2](https://github.com/tlx02/ped/issues/6), [3](https://github.com/tlx02/ped/issues/7))
  * **Team based**:
    * Contributed to the UI design of the app
  
