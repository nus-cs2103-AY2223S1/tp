---
layout: page
title: User Guide
---

TruthTable is a **desktop app for managing software engineering teams, optimized for use via a Command Line Interface**
(CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TruthTable can get your
contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `truthtable.jar` from [here](https://github.com/AY2223S1-CS2103T-W13-4/tp).

3. Copy the file to the folder you want to use as the _home folder_ for your TruthTable.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the
app contains some sample data.<br>
![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
open the help window.<br>
Some example commands you can try:

    * <TO_BE_IMPLEMENTED>

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

*  Quotes optional for string arguments (i.e. `add member "egg"` and `add_member egg` are equivalent)
* Double Tab to autocomplete string values
* Arguments are delimited with flags (`add member "egg" --email="asd@a.com"`)
</div>

### Viewing help : `help`

[Coming Soon]
### Creating a new team `add_team`

Add a new team to the user's list of teams. Will throw an error there is already an existing team with the same name.

Format:
* `add_team TEAM_NAME`

Examples:
* `add_team CS2103`

### Set a new team `set_team`

Sets the application to an existing team. Will throw an error if the team does not exist

Format:
* `set_team TEAM_NAME`

Examples:
* `set_team CS2103`

### Delete an existing team `delete_team`

Delete an existing team from the user's list of teams. Throws an error under these conditions.
 - The target team does not exist.
 - The target team is the only existing team.

Format:
* `delete_team TEAM_NAME`

Examples:
* `delete_team CS2103`

### Adding a new member to the team `add_member`

Add a new team member to the user’s team. Will throw an error if the member already exists in the team.

Format:
* `add_member TASK_INDEX`

Examples:
* `add_member 1`

### Delete a member from team `delete member`

Delete a team member from the user’s team. Will throw an error if no member with that name is found. Take note that
names are case-sensitive.

Format:
* `delete member “MEMBER_NAME”`
* `delete member --index=INDEX`
* `delete member -i INDEX`

Examples:
* `delete member “potato”`
* `delete member --index=2`
* `delete member -i 2`


### Listing all members of the team : `list_members`

View all the members currently in the team, in the form of a list.

Format: `list_members`

### Add task to team : `add_task`

Add a new task to the current team.

Format: `add_task "TASK_NAME"`

Examples:
*  `add task “bake with butter”`
*  `add task fry`

### Assign task to team member: `assign task`

Assign an existing task to a team member in the user’s team. Will display an error message if either the team member or
the task is not found in the user’s team.

Format: `assign_task TASK_INDEX "TEAM_MEMBER_NAME"`

Examples:
* `assign task 1 Alex` assigns the first task on the task list to a member in the team named Alex
* `assign task 2 Bernice` assigns the second task on the task list to a member in the team named Bernice

### Set Deadline for task: `set_deadline`

Set a deadline for an existing task. Will display an error message if task is not found in the user’s team. If 
deadline has been set for the task before, a new deadline will be set if command is run again.

Format: `set_deadline TASK_INDEX DEADLINE`

Examples:
* `set_deadline 1 2023-12-25 23:59`sets the deadline for the first task on the task list as 25 December 2023 11.59pm

### Delete task from team : `delete_task`

Delete an existing task from the team given the task's index. Will display an error message if the task is not found in the user’s team, i.e., when the index exceeds the number of tasks.

Format: `delete_task TASK_INDEX`

Examples:
* `delete task 1`

### List tasks in team: `list_tasks`

View all the tasks currently in the user’s team in the form of a list.

Format: `list_tasks`

### Clearing all entries : `clear`

Clears all entries from the list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TruthTable data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

TruthTable data are saved as a JSON file `[JAR file location]/data/truthtable.json`. Advanced users are welcome to
update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TruthTable will discard all data and start with an empty
data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous TruthTable home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary


| Action | Format, Examples |
|--------|------------------|
| TBC    | TBC              |
| TBC    | TBC              |
