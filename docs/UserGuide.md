---
layout: page
title: User Guide
---

TruthTable is a **desktop app for managing software engineering teams, optimized for use via a Command Line Interface**
(CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TruthTable can get your task management done faster than traditional GUI apps.

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

    * `list_members`
    * `list_tasks`
    * `add_task My First Task`

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Quotes are optional for words (i.e. `add member "word"` and `add member word` are equivalent) when there is 
   to be passed into commands. 
  * However, for words containing spaces, they should be surrounded with quotation marks. 
     Otherwise, they will be treated as 2 separate words (i.e. `add member "two words"` indicates that you are 
    adding the member named `two words`, however, `add member two words` is adding the member named `two` with some 
    other argument `word`)
* Double <kbd>Tab</kbd> to autocomplete string values (Coming soon!)
* Arguments are delimited with flags (`add member "word" --email="sample@email.com"`)
</div>

### Viewing help : `help`

Format:
* `help`

### Creating a new team `add team`

Add a new team to the user's list of teams. Will throw an error there is already an existing team with the same name.

Format:
* `add team -n TEAM_NAME [-d TEAM_DESCRIPTION]`

Examples:
* `add team -n CS2103` will create a new team by the name of "CS2103"
* `add team -n CS2102 -d "Database Systems"` will create a new team by the name of "CS2102" and "Database Systems" 
  as description

### Set a new team `set_team`

Sets the application to an existing team, i.e., changes the current "working" team to another. Will throw an error if the team does not exist

Format:
* `set team TEAM_NAME`

Examples:
* `set team CS2103` will change the current working team to be the "CS2103" team.

### Edit current team `edit team`

Format:
* `edit team [-n TEAM_NAME] [-d TEAM_DESCRIPTION]`
* 
Examples:
* `edit team -n CS2103 -d "Software Engineering"` will edit the name of the current team to CS2103 and description 
  to "Software Engineering"


### Delete an existing team `delete team`

Delete an existing team from the user's list of teams. Throws an error under these conditions.
 - The target team does not exist.
 - The target team is the only existing team.

Format:
* `delete team TEAM_NAME`

Examples:
* `delete team CS2103` will delete the team with the name "CS2103"

### Adding a new member to the team `add member`

Add a new team member to the user’s currently selected team. Will throw an error if the member already exists in the team.

Format:
* `add_member TASK_INDEX`

Examples:
* `add member 1` will add the first person in the list of people as a member of the current team.

### Delete a member from team `delete_member`

Delete a team member from the user’s team. Will throw an error if no member with that name is found. Take note that
names are case-sensitive.

Format:
* `delete member TEAM_MEMBER_INDEX`

Examples:
* `delete member 2` will delete the second member of the current team.

### Listing all members of the team : `list members`

View all the members currently in the team, in the form of a list.

Format:
* `list members`

### Add task to team : `add_task`

Add a new task to the current team.

Format:
* `add task TASK_NAME`

Examples:
*  `add task "bake with butter"` will add a task with the name "bake with butter" to the current team's task list.
*  `add task fry` will add a task with the name "fry" to the current team's task list.

### Assign task to team member: `assign task`

Assign an existing task to a team member in the user’s team. Will display an error message if either the team member or
the task is not found in the user’s team.

Format: `assign task TASK_INDEX TEAM_MEMBER_NAME`

Examples:
* `assign task 1 Alex` will assign the first task on the task list to a member in the team named Alex.
* `assign task 2 Bernice` will assign the second task on the task list to a member in the team named Bernice.

### Assign task to random team member: `assign random`

Assign an existing task to a random team member in the user’s team. Will display an error message if either the task is
not found in the user’s team or if there are no team members to assign the task to (if the team is empty, or if the task
has already been assigned to all members of the team.

Format: `assign random TASK_INDEX`

Examples:
* `assign random 1` will assign the first task on the task list to a random team member.


### Set Deadline for task: `set deadline`

Set a deadline for an existing task. Will display an error message if task is not found in the user’s team. If
deadline has been set for the task before, a new deadline will be set if command is run again. The deadline must be specified in YYYY-MM-DD HH:MM format.

Format: `set deadline TASK_INDEX DEADLINE`

Examples:
* `set deadline 1 2023-12-25 23:59` will set the deadline for the first task on the task list as 25 December 2023 11.59pm.

### Delete task from team : `delete task`

Delete an existing task from the team given the task's index. Will display an error message if the task is not found in the user’s team, i.e., when the index exceeds the number of tasks.

Format:
* `delete task TASK_INDEX`

Examples:
* `delete task 1` will delete the first task of the current team.

### List tasks in team: `list tasks`

View all the tasks currently in the user’s team in the form of a list. List can also be filtered based on complete
or remaining tasks.

Format:
* `list tasks` will list all the tasks of the current team.
* `list tasks -i` will list all the incomplete tasks of the current team.
* `list tasks -c` will list all the completed tasks of the current team.

### Mark tasks as done: `mark`

Mark a specified task as done.

Format:
* `mark INDEX`

Examples:
* `mark 1` will mark the first task in the team as done.

### View summary of task assignments in team: `summary`

View the number of tasks assigned to each user in the team.

Format:
* `summary`

### Add a new link : `add link`

Add a new link to the user's currently selected team.

Format:
* `add link -n NAME -l URL`

Examples:
* `add link -n google -l "https://google.com`

### Edit an existing link : `edit link`

Edit an existing link in the user's currently selected team.

Format:
* `edit link LINK_INDEX [-n NAME] [-l URL]`

Examples:
* `edit link 1 -n facebook -l "https://facebook.com`

* ### Delete an existing link : `delete link`

Delete the specified link from the user's currently selected team.

Format:
* `delete link LINK_INDEX`

Examples:
* `delete link 1`

### Clearing all entries : `clear`

Deletes all the people from the application.

Format:
* `clear`

### Exiting the program : `exit`

Exits the program.

Format:
* `exit`

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
