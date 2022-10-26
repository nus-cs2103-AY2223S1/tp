---
layout: page
title: User Guide
---

#User Guide

EZLead is a **desktop app for tech leads to manage teams optimized for use via a Command Line Interface (CLI)**.
As a tech lead, you will be able to easily keep track of all the teams under you as well as each team's current and
future tasks. With our app, teams management would be easier than ever.

--------------------------------------------------------------------------------------------------------------------

## Index

- [Quick Start](#Quick Start)
- [Features](#Features)
- [Command Summary](#Command Summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure that you have Java `11` installed in your computer.
2. Download the latest EZLead.jar from [here](https://github.com/AY2223S1-CS2103T-W09-3/tp/releases/download/v1.3.1/EzLead.jar).
3. Double-click the file to start the app.
4. Type the command in the command box and press Enter to execute it.
5. Refer to the `Features` section in this user guide for more details on commands

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

### Adding a member: `add`

Adds a new person to EZLead.

Format: `add n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]…`

Examples:
* `add n/John Doe p/99853657 e/john@gmail.com a/414, North Bridge Ave 5, #09-86 t/friends t/owesMoney` Adds a new 
person with the following details.

### Editing a member's details: `edit`

Edits a member's details.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`

Examples:
* `edit 1 n/Johny p/91234567 e/johndoe@example.com` Edits the first member's name to Johny, email to 
johndoe1@example.com and contact number to 91234567.

### Deleting a member: `delete`

Deletes a member.

Format: `delete p/GLOBAL-PERSON-INDEX`

Examples:
* `delete p/1` Deletes the first member.

### Assigning a member to a team: `assign`

assigns a member to a team.

Format: `assign m/MEMBER-INDEX t/TEAM-INDEX`

Examples:
* `assign m/1 t/1` Assigns first member to first team.

### UnAssigning a member to a team: `unassign`

removes a member from a team.

Format: `unassign m/MEMBER-INDEX t/TEAM-INDEX`

Examples:
* `unassign m/1 t/1` removes first member from first team.

### Viewing all members: `userlist`

Shows a list of all members and their details.

Format: `userlist`

### Adding a task: `taskadd`

Adds a new task to a team. Additionally, you may opt to set a deadline for the task (optional).

Format: `taskadd t/TEAM-INDEX n/TASK-NAME [d/YYYY-MM-DD]`

Examples:
* `taskadd t/1 n/Finish project d/2023-12-24` Adds a new task to team with index 1 with the description 
"Finish project". The deadline set here is 24 December 2023.

### Deleting a task `taskdelete`

Deletes a task in a specific team.

Format: `taskdelete t/TEAM-INDEX task/TASK-INDEX`

Examples:
* `taskdelete t/1 task/1` Deletes the first task in the first team.

### Marking a task as completed `taskmark`

Marks a task as completed.

Format: `taskmark t/TEAM-INDEX task/TASK-INDEX`

Examples:
* `taskmark t/1 task/1` Marks the first task in the first team as completed.

### UnMarking a task as pending `taskunmark`

UnMarks a task as pending.

Format: `taskunmark t/TEAM-INDEX task/TASK-INDEX`

Examples:
* `taskunmark t/1 task/1` UnMarks the first task in the first team as pending.

### Updating a task description: `taskedit`

Updates a task's description.

Format: `taskedit t/TEAM-INDEX task/TASK-INDEX n/NEW-TASK-NAME`

Examples:
* `taskedit t/1 task/1 n/Finish assignment` Updates the first task in the first team with new description 
'Finish assignment'.

### Adding a team: `createteam`

Adds a team with the given name to EZLead.

Format: `createteam n/TEAM-NAME`

Examples:
* `createteam n/Team1` Adds a team with the name Team1.

### Deleting a team: `delteam`

Deletes the given team from EZLead.

Format: `deleteteam t/TEAM-INDEX`

Examples:
* `deleteteam t/1` Deletes the first team.

### Changing a team's name: `editteam`

Changes a team's name (specified by index) to the given name.

Format: `editteam t/TEAM-INDEX n/NEW-TEAM-NAME`

Examples:
* `editteam t/1 n/TEAMNEW` Changes the first team's name to 'TEAMNEW'.

--------------------------------------------------------------------------------------------------------------------

## FAQ

Coming Soon!

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action              | Format, Examples                                                                                                                                               |
|---------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Member Add**      | `add n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]…` <br> e.g. `add n/John Doe p/99853657 e/john@gmail.com a/414, North Bridge Ave 5, #09-86 t/friends t/owesMoney` |
| **Member Delete**   | `delete p/GLOBAL-PERSON-INDEX` <br> e.g. `delete p/1`                                                                                                          |
| **Member Edit**     | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…` <br> e.g.`edit 1 n/Johny p/91234567 e/johndoe@example.com`                                      |
| **Member assign**   | `assign m/MEMBER-INDEX t/TEAM-INDEX` <br> e.g.`assign m/1 t/1`                                                                                                 |
| **Member unAssign** | `unassign m/MEMBER-INDEX t/TEAM-INDEX` <br> e.g.`unassign m/1 t/1`                                                                                             |
| **Member List**     | `userlist`                                                                                                                                                     |
| **Help**            | `help`                                                                                                                                                         |
| **Task Add**        | `taskadd t/TEAM-INDEX n/TASK-NAME [d/YYYY-MM-DD]` <br> e.g. `taskadd t/1 n/Finish project d/2023-12-24`                                                        |
| **Task Delete**     | `taskdelete t/TEAM-INDEX task/TASK-INDEX` <br> e.g. `taskdelete t/1 task/1`                                                                                    |
| **Task Mark**       | `taskmark t/TEAM-INDEX task/TASK-INDEX` <br> e.g. `taskmark t/1 task/1`                                                                                        |
| **Task unMark**     | `taskunmark t/TEAM-INDEX task/TASK-INDEX` <br> e.g. `taskunmark t/1 task/1`                                                                                    |
| **Task Edit**       | `taskedit t/TEAM-INDEX task/TASK-INDEX n/NEW-TASK-NAME` <br> e.g. `taskedit t/1 task/1 n/Finish assignment`                                                    |
| **Team Add**        | `createteam n/TEAM-NAME` <br> e.g. `createteam n/TEAM1`                                                                                                        |
| **Team Delete**     | `delteam t/TEAM-INDEX` <br> e.g. `deleteteam t/1`                                                                                                              |
| **Team Edit**       | `editteam t/TEAM-INDEX n/NEW-TEAM-NAME` <br> e.g. `editteam t/1 n/TEAMNEW`                                                                                     |
