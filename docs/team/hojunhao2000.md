---
layout: page title: Ho Jun Hao's Project Portfolio Page
---

### Project: ModtRekt

ModtRekt - is a desktop app for undergraduates of NUS to manage their modules and tasks. It is optimized for use via a
Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: added the ability to Add, Remove, and Edit Modules
    * What it does: allows the user to add, edit and delete modules.
    * Justification: This feature is crucial in allowing the application to run as intended. A user can add a module
      with a module code, module name, and module credits.
    * Highlights: This feature was done mainly by refactoring existing code
    * Credits: The original AB3 for laying down most of the code.

* **New Feature**: added ability to edit Tasks
    * What it does: allows the user to edit tasks.
    * Justification: This feature gives users the flexibility to change details of their tasks such as description,
      module code, priority, and deadline.
    * Highlights: Useful if they erroneously created the task and provides a faster alternative to deleting and 
      re-entering the task.

* **Major Enhancement**: Added Module, ModName, ModCredit, ModCode classes
    * What it does: Allows the application to recognise and work with modules.
    * Justification: This feature is crucial in allowing the application to run as intended.
    * Highlights: This feature was done mainly by refactoring existing code
    * Credits: The original AB3 for laying down most of the code.

* **Major Enhancement**: Separated the task list from modules
    * What it does: In our initial iteration, each module contained its own list of tasks associated with it. I removed
      the task list inside the module and made it such that any modification will be made directly to the actual task book.
    * Justification: By removing the module's task list, this meant that there would only be 1 list to update making it
      much easier to manage.
    * Highlights: Instead of having a task list, the modules now has a counter of tasks' associated with it.
    * Credits: [Dominic](https://github.com/domoberzin) for creating the initial module task list.

* **Major Enhancement**: Global refactor of addressbook and AB3 to tasks and modules
    * What it does: Changed all the old methods names, parameter names, class names, file names, and javadocs containing
      the old addressbook or person class to the appropriate names.
    * Justification: Our code base is based upon the old AB3. As we have created and renamed the old classes, these names
      are no longer relevant and have to be refactored to avoid confusion.
    * Highlights: Instead of addressbook, we now have correct names of taskbook and modulelist

* **Major Enhancement**: Overhauled the User Guide (UG) to meet requirements and have better clarity
    * What it does: Added diagrams, added table of contents, parameter constraints summary, value proposition of application
      and much more.
    * Justification: Our initial UG was lacking in several aspects causing confusion to users when reading. Through these
      additions, it can help the user better understand our UG and utilize our application.
    * Highlights: Created a parameter constraints summary so that users can see all the constraints at a glance. Added value
      proposition so users will now what they can do and how they can benefit using modtrekt.

* **Major Enhancement**: 
    * What it does: Added diagrams, added table of contents, parameter constraints summary, value proposition of application
      and much more.
    * Justification: Our initial UG was lacking in several aspects causing confusion to users when reading. Through these
      additions, it can help the user better understand our UG and utilize our application.
    * Highlights: Created a parameter constraints summary so that users can see all the constraints at a glance. Added value
      proposition so users will now what they can do and how they can benefit using modtrekt.

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=HoJunHao2000&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Reviewed pull requests made by other team members and gave feedback, requesting changes where necessary

* **Documentation**:
    * User Guide:
        * added how to use `AddModuleCommand`, `RemoveModuleCommand`, `EditModuleCommand`, and `EditTaskCommnad`
    * Developer Guide:
        * `added development process on AddModuleCommand`, `RemoveModuleCommand`, `EditModuleCommand`, and `EditTaskCommnad`.
        * Created several UML and sequence diagram to illustrate command execution process

* **Community**:
    * Descriptively reported bugs, with images and steps to replicate where possible,
      for another team and gave suggestions for improvements. Bug reports can be found [here](https://github.com/HoJunHao2000/ped/issues)

* **Other contributions**:
    * Part of the team that conducted the product pitch.
