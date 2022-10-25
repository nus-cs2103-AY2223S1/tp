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
* `employee edit n/John Doe e/johndoe1@example.com c/12345678` Edits the employee John Doe's email to 
johndoe1@example.com and contact number to 12345678.

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
* `task deadline t/Implement a payment system d/12/12/2023` Adds a new task with the description "Implement a payment 
system" and the deadline of 12th December 2023.

### Updating a task description: `task update`

Updates a task's description.

Format: `task update o/OLD-TASK-DESCRIPTION n/NEW-TASK-DESCRIPTION`

Examples:
* `task update o/Implement an authentication system n/Implement an authentication system with encryption` Updates the 
task "Implement an authentication system" to "Implement an authentication system with encryption".

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

| Action              | Format, Examples                                                                                                                                            |
|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Member Add**      | `add n/NAME p/PHONE e/EMAIL a/ADDRESS t/TAG` <br> e.g. `add n/John Doe p/99853657 e/john@gmail.com a/414, North Bridge Ave 5, #09-86 t/friends t/owesMoney` |
| **Member Delete**   | `delete p/GLOBAL-PERSON-INDEX` <br> e.g. `delete p/1`                                                                                                       |
| **Member Edit**     | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]` <br> e.g.`edit 1 n/Johny p/91234567 e/johndoe@example.com`                                    |
| **Member assign**   | `assign m/MEMBER-INDEX t/TEAM-INDEX` <br> e.g.`assign m/1 t/1`                                                                                              |
| **Member unAssign** | `unassign m/MEMBER-INDEX t/TEAM-INDEX` <br> e.g.`unassign m/1 t/1`                                                                                          |
| **Member List**     | `userlist`                                                                                                                                                  |
| **Help**            | `help`                                                                                                                                                      |
| **Task Add**        | `taskadd t/TEAM-INDEX n/TASK-NAME [d/YYYY-MM-DD]` <br> e.g. `taskadd t/1 n/Finish project d/2023-12-24`                                                     |
| **Task Delete**     | `taskdelete t/TEAM-INDEX task/TASK-INDEX` <br> e.g. `taskdelete t/1 task/1`                                                                                 |
| **Task Mark**       | `taskmark t/TEAM-INDEX task/TASK-INDEX` <br> e.g. `taskmark t/1 task/1`                                                                                     |
| **Task unMark**     | `taskunmark t/TEAM-INDEX task/TASK-INDEX` <br> e.g. `taskunmark t/1 task/1`                                                                                 |
| **Task Edit**       | `taskedit t/TEAM-INDEX task/TASK-INDEX n/NEW-TASK-NAME` <br> e.g. `taskedit t/1 task/1 n/Finish assignment`                                                 |
| **Team Add**        | `createteam n/TEAM-NAME` <br> e.g. `createteam n/TEAM1`                                                                                                     |
| **Team Delete**     | `deleteteam t/TEAM-INDEX` <br> e.g. `deleteteam t/1`                                                                                                        |
| **Team Edit**       | `editteam t/TEAM-INDEX n/NEW-TEAM-NAME` <br> e.g. `editteam t/1 n/TEAMNEW`                                                                                  |
