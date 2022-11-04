---
layout: page
title: laxus2308's Project Portfolio Page
---

### Project: JARVIS

JARVIS is a desktop application that allows a CS1101S TA to manage his/her students and tasks in an organised manner.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java,
and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete task from JARVIS
  * What it does: Allow JARVIS to parse user input and allow user to delete tasks from task list
  * Justification: Completed tasks that are deemed redundant by the user can be deleted to avoid cluttering of tasks. This also allow users to delete tasks that are wrongfully added because they might make a mistake in either description or deadline.
    
* **New Feature**: Added lesson list to JARVIS, the ability to add the 3 subtypes of lessons and remove a lesson from JARVIS
  * What it does: Allow JARVIS to parse user input and allow user to add MasteryCheck, Studio and Consult to lesson list, and to delete any lesson from the lesson list. Lessons to be added will be checked against current list of lessons for any conflicting schedules.
  * Justification: This helps the TA to keep track of his/her schedule, remembering who to meet for what lesson on which day and time. JARVIS also helps to check for timing conflicts to ensure there will be no 2 lessons occurring at the same time as the TA may accidentally forget about prior scheduled lessons.

* **New Feature**: Ui design for Lesson and Task card in default view with all lists displayed.
  * What it does: Display the description, timing and students involved for lessons and description and deadline for tasks. Using of tick and cross to show whether the task/lesson has been completed.
  * Justification: Original design was simply a checkbox like `[X]` or `[ ]`, which is rather small and unintuitive. 
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=laxus2308&breakdown=true)

* **Project management**:
    * Creating and assigning issues to members.
    
* **Documentation**:
    * User Guide:
        * Update documentation for features' usage examples #155.
    * Developer Guide:
        * Added user stories #108, #37.
        * Added use cases #49, #62, #114.
        * Added implementation details for adding lessons commands and parsers to JARVIS #94.

* **Community**:
    * PRs reviewed #106, #72, #66.
    * Helped Team CS2103-F13-1 find bugs during PE-D.
    

