---
layout: page
title: User Guide
---

EZLead is a **desktop app for tech leads to manage teams optimized for use via a Command Line Interface (CLI)**. 
As a tech lead, you will be able to easily keep track of all the teams under you as well as each team's current and 
future tasks. With our app, teams management would be easier than ever.

--------------------------------------------------------------------------------------------------------------------

## Quick start

Coming soon!

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the commands:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `create t/TEAM-NAME`, `TEAM-NAME` is a parameter which can be used as `create t/UIDevelopers`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `m/John`, `m/John m/Jane` etc.

</div>

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Viewing all team names: `view all`

Shows all the team names.

Format: `view all`

### Viewing a team's details: `view`

Shows a specific team's details, tasks, and members.

Format: `view t/TEAM-NAME`

Examples:
* `view t/UIDevelopers` Shows the UIDevelopers team details, tasks, and members.

### Adding an employee: `employee add`

Adds a new employee.

Format: `employee add n/NAME`

Examples:
* `employee add n/John Doe` Adds a new employee with the name John Doe.
* `employee add n/James` Adds a new employee with the name James.

### Editing an employee's details: `employee edit`

Edits an employee's details.

Format: `employee edit n/NAME e/EMAIL c/CONTACT-NUMBER`

Examples:
* `employee edit n/John Doe e/johndoe1@example.com c/12345678` 
Edits the employee John Doe's email to johndoe1@example.com and contact number to 12345678.

### Deleting an employee: `employee delete`

Deletes an employee.

Format: `employee delete n/NAME`

Examples:
* `employee delete n/John Doe` Deletes the employee named John Doe.

### Adding a team: `team add`

Adds a team with the given name.

Format: `team add t/TEAM-NAME`

Examples:
* `team add t/UIDevelopers` Adds a team with the name UIDevelopers.

### Changing a team's name: `team rename`

Changes a team's name to the given name.

Format: `team rename o/TEAM-NAME n/NEW-TEAM-NAME`

Examples:
* `team rename o/UIDevelopers n/UnitTesters` Changes the name of the team UIDevelopers to UnitTesters.

### Deleting a team: `team delete`

Deletes the given team.

Format: `team delete t/TEAM-NAME`

Examples:
* `team delete t/UIDevelopers` Deletes the team UIDevelopers.

### Adding team member/s: `team link`

Adds the given person/s to the team.

Format: `team link t/TEAM-NAME m/TEAM-MEMBER...`

Examples:
* `team link t/UIDevelopers m/John m/Jane` Adds John and Jane to the team UIDevelopers.

### Removing team member/s: `team delink`

Removes the given person/s from the team.

Format: `team delink t/TEAM-NAME m/TEAM-MEMBER...`

Examples:
* `team delink t/UIDevelopers m/John m/Jane` Removes John and Jane from the team UIDevelopers.

### Adding a task: `task add`

Adds a new task.

Format: `task add t/TASK-DESCRIPTION`

Examples:
* `task add t/Create GUI for the Duke app` Adds a new task with the description "Create GUI for the Duke app".

### Adding a deadline: `task deadline`

Adds a new task with a deadline.

Format: `task deadline t/TASK-DESCRIPTION d/DEADLINE`

Examples:
* `task deadline t/Implement a payment system d/12/12/2023` Adds a new task with 
the description "Implement a payment system" and the deadline of 12th December 2023.

### Updating a task description: `task update`

Updates a task's description.

Format: `task update o/OLD-TASK-DESCRIPTION n/NEW-TASK-DESCRIPTION`

Examples:
* `task update o/Implement an authentication system n/Implement an authentication system with encryption` 
Updates the task "Implement an authentication system" to "Implement an authentication system with encryption".

### Assigning a task to a team: `task assign`

Assigns a task to a specific team.

Format: `task assign t/TASK-DESCRIPTION n/TEAM-NAME`

Examples:
* `task assign t/Create GUI for the Duke app n/UIDevelopers` Assigns the task "Create GUI for the Duke app" to the
team UIDevelopers.

### Deleting a task `task delete`

Deletes a task.

Format: `task delete t/TASK-DESCRIPTION`

Examples:
* `task delete t/Create GUI for the Duke app` Deletes the task "Create GUI for the Duke app".

### Marking a task as completed `task mark`

Marks a task as completed.

Format: `task mark t/TASK-DESCRIPTION`

Examples:
* `task mark t/Create GUI for the Duke app` Marks the task "Create GUI for the Duke app" as completed.

--------------------------------------------------------------------------------------------------------------------

## FAQ

Coming Soon!

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Employee Add** | `employee add n/NAME` <br> e.g. `employee add n/John Doe`
**Employee Delete** | `employee delete n/NAME` <br> e.g. `employee delete n/John Doe`
**Employee Edit** | `employee edit n/NAME e/EMAIL c/CONTACT-NUMBER` <br> e.g.`employee edit n/John Doe e/johndoe1@example.com c/12345678`
**Help** | `help`
**Task Add** | `task add t/TASK-DESCRIPTION` <br> e.g. `task add t/Create GUI for the Duke app`
**Task Assign** | `task assign t/TASK-DESCRIPTION n/TEAM-NAME` <br> e.g. `task assign t/Create GUI for the Duke app n/UIDevelopers`
**Task Deadline** | `task deadline t/TASK-DESCRIPTION d/DEADLINE` <br> e.g. `task deadline t/Implement a payment system d/12/12/2023`
**Task Delete** | `task delete t/TASK-DESCRIPTION` <br> e.g. `task delete t/Create GUI for the Duke app`
**Task Mark** | `task mark t/TASK-DESCRIPTION` <br> e.g. `task mark t/Create GUI for the Duke app`
**Task Update** | `task update o/OLD-TASK-DESCRIPTION n/NEW-TASK-DESCRIPTION` <br> e.g. `task update o/Implement an authentication system n/Implement an authentication system with encryption`
**Team Add** | `team add t/TEAM-NAME` <br> e.g. `team add t/UIDevelopers`
**Team Delete** | `team delete t/TEAM-NAME` <br> e.g. `team delete t/UIDevelopers`
**Team Delink** | `team delink t/TEAM-NAME m/TEAM-MEMBER...` <br> e.g. `team delink t/UIDevelopers m/John m/Jane`
**Team Link** | `team link t/TEAM-NAME m/TEAM-MEMBER...` <br> e.g. `team link t/UIDevelopers m/John m/Jane`
**Team Rename** | `team rename o/TEAM-NAME n/NEW-TEAM-NAME` <br> e.g. `team rename o/UIDevelopers n/UnitTesters`
**View** | `view t/TEAM-NAME` <br> e.g.,`view t/UIDevelopers`
**View All** | `view all`
