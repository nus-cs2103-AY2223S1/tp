---
layout: page
title: Nicole Lee's Project Portfolio Page
---

### Project: YellowBook

YellowBook is for university students who are involved in many projects and have to organize their project contacts and tasks. The user interacts with YellowBook using a CLI, has a GUI created using JavaFX, is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented tasks and the task list.
  * What it does: Each task stores a description of the task and its deadline. The task list displays all the user's tasks.
  * Justification: Users of YellowBook are students with many projects and thus, many tasks that need to be tracked and completed. Being able to save their tasks and view their tasks clearly in a task list will help them organise and remember their tasks.
  * Highlights: Duplicate tasks cannot be added to the task list.

* **New Feature**: Added the ability to add/remove tasks.
  * What it does: Allows the user to add or delete tasks from YellowBook's task list using the add task command, `addT`, or the delete task command, `deleteT`, respectively.
  * Justification: This feature is critical to the implementation of YellowBook's task list, as the task list is not functional without the ability to minimally add and remove tasks. This feature allows users to keep track of their tasks using YellowBook, so they know exactly what work needs to be done for their projects.
  * Highlights: Tasks are added with deadlines, to help users remember when their tasks should be completed by. Tasks that are past their deadline can still be added, as oftentimes overdue tasks must still be completed.

* **New Feature**: Added command to remind users what tasks are due up till a specified date.
  * What it does: Executing the `remindT` command filters the task list to show only tasks with deadlines that fall before or on the day of the date specified.
  * Justification: Users juggling many tasks may need to be reminded of what tasks are due most urgently, thus this allows them to clearly see what tasks should be completed by their specified date.
  * Highlights: Tasks past their deadline are still shown by remindT as users may still need to complete them.

* **New Feature**: Added command to show users their task completion progress for tasks with any of the specified tags.
  * What it does:
  * Justification:
  * Highlights:

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Created issues.
  * Assigned issues.
  * Reviewed other team members' pull requests.
  * Merged other team members' pull requests.

* **Enhancements to existing features**:
  * Improved the Deadline class, so that it strictly parses for valid dates.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addT`.
      [#61](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/61)
    * Added documentation for `deleteT`.
      [#136](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/136)
    * Updated documentation for `addT` and `deleteT`. Added documentation for `remindT` and `progressT`.
      [#188](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/188)
  * Developer Guide:
    * Added use case for add task.
      [#73](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/73)
    * Added implementation details for the features `addT` and `deleteT`.
      [#161](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/161)


* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [#236](https://github.com/AY2223S1-CS2103T-F11-4/tp/pull/236)
