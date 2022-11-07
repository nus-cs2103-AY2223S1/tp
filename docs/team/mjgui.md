---
layout: page
title: Ming Jiang's Project Portfolio Page
---

### Project: TruthTable

TruthTable is a task-management software specially targeted towards tech-savvy university students leading teams in
software engineering modules to build projects. It helps track the team's progress and delegate tasks effectively.

Given below are my contributions to the project.

* **New Feature**: `assign random` - Added command to assign tasks randomly.
    * **What it does**: Assigns a particular task to a random team member that is not already assigned to the task
    * **Justification**: When team leads have to assign many tasks to people, this might lead to "decision paralysis". A
      lot of times, a task can be done by anyone. This feature allows team leads to assign tasks without needing to
      decide who specifically to assign the task to.
    * **Highlights**: The `java.util.Random` class was used to generate a random user index to be assigned. Care was
      taken to first filter users that have not been assigned that task (so as not to assign the same user twice).

* **New Feature**: `summary` - Added command to see a quick summary of the tasks each member is working on.
    * **What it does**: Displays a breakdown of the number of tasks assigned to each person.
    * **Justification**: In a large project, it's useful to have an overview on the number of tasks each person is
      working on to ensure a reasonably fair distribution of work. However, manually counting the number of tasks done
      by each user is very time-consuming.
    * **Highlights**: For the implementation of this feature, a `HashMap` was used to keep track of the counts of tasks
      for each team member. As the assignees of each task are stored within each team member, a linear pass through the
      list of tasks and each of its members was done instead of a linear pass of tasks for each member. This
      implementation reduces the runtime of the feature to linear (i.e. `O(n)`) in terms of task-assignee pairs.

* **New Feature**: Added completion percentage of tasks to the UI.
    * **What it does**: Displays the number of completed tasks, total tasks and a percentage of completion above the
      task list.
    * **Justification**: This feature allows the user to see the amount of work left at a glance, which is very useful
      for a task management application.

* **New Feature**: `tasksof` - Allow users to see tasks assigned to each user.
    * **What it does**: Given a particular user (specified by index), it shows the tasks assigned to that user.
    * **Justification**: This feature allows the user to see the tasks assigned to a particular user, which is very
      useful for a task management application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=mjgui)

* **Project management**:
    * Linked teammates' PRs to relevant issues
    * Closed repeated or out-of-scope issues
    * Assigned unassigned issues to relevant teammates for milestones 1.3 and 1.4

* **Enhancements to existing features**:
    * Update sample data to have more realistic tags
    * Allow deleted users to cascade through the model (update teams and tasks to delete the member)

* **Documentation**:
    * User Guide:
        * Add details for `tasksof`, `summary` and `assign random` commands
        * Update inaccurate command syntax in User Guide highlighted during PE Dry Run
        * Reorder commands into sections
    * Developer Guide:
        * Updated Logic component, Model component and Storage component diagrams and descriptions
        * Added description of Assign Task Randomly feature
        * Reviewed and edited section on Manual Testing
        * Reviewed and edited section on Effort
        * Improved formatting (changed ordering of contents, fixed hierarchy of elements in table of contents)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#75](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/75)
      , [\#88](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/88)
      , [\#95](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/95)
      , [\#100](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/100)
    * Contributed to forum discussions and helped peers with debugging (examples:
      [\#404](https://github.com/nus-cs2103-AY2223S1/forum/issues/404))
