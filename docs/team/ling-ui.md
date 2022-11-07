---
layout: page
title: Ling Ling's Project Portfolio Page
---

### Project: TruthTable

TruthTable is a task-management software specially targeted towards tech-savvy university students leading
teams in software engineering modules to build projects. It helps track the team's progress and delegate tasks
effectively.

Given below are my contributions to the project.

* **New Feature**: `assign task` - Added command to assign tasks to specific member(s).
    * **What it does**: Assigns a particular task to specified team member(s)
    * **Justification**: When a task is first created, the assignees for the task might not be decided on yet. This
      command allows team leads to assign the task to assignees after creating the task, or add more assignees to a
      single task if the team lead decides that a task requires more manpower.
  
* **New Feature**: `set deadline` - Added command to set deadlines to existing tasks.
    * **What it does**: Sets a deadline to an existing task
    * **Justification**: When a task is first created, the deadline for the task might not be decided on yet. This
      command allows team leads to set the deadline of the task after creating the task, or edit the existing deadline.

* **New Feature**: `edit task` - Added command to edit fields of existing tasks.
    * **What it does**: Edits fields of an existing task
    * **Justification**: The team lead might want to edit the name of a particular task, or edit the assignees and
      deadline of a task at the same time. All these can be done in the same command which makes it more efficient.

* **New Feature**: `mark task` - Added command to mark a task as complete.
    * **What it does**: Marks a task as complete
    * **Justification**: This is a necessary command for team leads to track the progress of their team.

* **New Feature**: `unmark task` - Added command to undo the command of marking a task as complete.
    * **What it does**: Undo marking a task as done
    * **Justification**: This is a necessary command for team leads to track the progress of their team.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=ling-ui&tabRepo=AY2223S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Released JAR file for V1.3.trial

* **Enhancements to existing features**:
    * Added filter feature for list tasks to show only complete or incomplete tasks

* **Documentation**:
    * User Guide:
        * Add details for `assign task`, `set deadline`, `mark task` and `unmark task` commands
    * Developer Guide:
        * Documented implementation and created diagrams for `assign task` and `add link` commands
        * Documented use cases and NFRs
        * Documented test cases for all commands under manual testing

* **Community**:
    * PRs reviewed (with non-trivial review comments): Reviewed 11 PRs. (examples:
      [PR #76](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/76),
      [PR #174](https://github.com/AY2223S1-CS2103T-W13-4/tp/pull/174))
    * Reported bugs and suggestions for team W11-4 during PE dry run.
