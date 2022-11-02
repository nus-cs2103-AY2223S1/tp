---
layout: page
title: User Guide
---

TruthTable is a **desktop app for managing software engineering teams, optimized for use via a Command Line Interface**
(CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TruthTable can get your task management done faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

# Quick start

1. Ensure you have Java `11` or above installed in your computer. If not, don't worry! Click
   [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) for further instructions on 
   how to download and install Java `11`.

3. Download the latest `truthtable.jar` from [here](https://github.com/AY2223S1-CS2103T-W13-4/tp).

4. Copy the file to the folder you want to use as the _home folder_ for your TruthTable.

5. Double-click the file to start the app. A window which looks similar to the screenshot below should appear in a few 
   seconds. We've added some sample data for you to play around with and get familiar with the commands!.<br>
   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window, which contains a link to this document.<br>
   Some example commands you can try:
   - `add member 1`
   - `add task "My First Task"`

7. Refer to the [Features](#features) section below for details of each command.

---

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Important notes on how to use the commands:**<br>

- Quotes are optional for words (i.e. `add member "word"` and `add member word` are equivalent) when there is
  to be passed into commands.
  - However, if you want an argument to use multiple words, they should be surrounded with quotation marks.
  - Otherwise, they will be treated as 2 separate words.
  - For instance, `add member "two words"` indicates that you are
    adding the member named `two words`. 
  - However, `add member two words` is adding the member named `two` with some
    other argument `word`.
- Arguments are separated with "flags" (`add member "word" --email="sample@email.com"`)
</div>

<div markdown="span" class="alert alert-info">**For experienced command line users**
You will find that the syntax is very similar to other command line interfaces like `git` and `docker`!
</div>

## Commands to Manage Persons

### Creating a new person `add person`

Adds a person to TruthTable.

Format: `add person -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS [-t TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add person -n "John Doe" -p 98765432 -e johnd@example.com -a "John street, block 123, #01-01"`
* `add person -n "Betsy Crowe" -e betsycrowe@example.com -a "Newgate Prison" -p 1234567 -t criminal friend`

### Listing all persons : `list persons`

Shows a list of all persons in TruthTable.

Format: `list persons`

### Editing a person : `edit person`

Edits an existing person in TruthTable.

Format: `edit person PERSON_INDEX [-n NAME] [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]`

* Edits the person at the specified `PERSON_INDEX`. The index refers to the index number shown in the displayed person 
  list. The index **must be a positive integer** 1, 2, 3,...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `-t` without
  specifying any tags after it.

Examples:
*  `edit person 1 -p 91234567 -e johndoe@example.com` Edits the phone number and email address of the 1st person to be 
   `91234567` and `johndoe@example.com` respectively.
*  `edit person 2 -n Betsy Crower -t` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing 
   tags.

### Locating persons by name: `find person`

Finds person whose names contain any of the given keywords.

Format: `find person KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`)

Examples:
* `find person John` returns `john` and `John Doe`
* `find person alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find person alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete person`

Deletes the specified person from TruthTable.

Format: `delete person PERSON_INDEX`

* Deletes the person at the specified `PERSON_INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3,...

Examples:
* `list persons` followed by `delete person 2` deletes the 2nd person in the TruthTable.
* `find person Betsy` followed by `delete person 1` deletes the 1st person in the results of the `find person` command.

## Commands to Manage Teams

### Creating a new team `add team`

Add a new team to your list of teams. Will show an error there is already an existing team with the same name. 

<div markdown="span" class="alert alert-info">:information_source: **Note:** Team name must consist only of alphanumeric characters (i.e., **Spaces are NOT allowed**).
</div>

Format: `add team TEAM_NAME [-d TEAM_DESCRIPTION]`

Examples:

- `add team CS2103` will create a new team by the name of "CS2103"
- `add team CS2102 -d "Database Systems"` will create a new team by the name of "CS2102" and "Database Systems"
  as description

### Set a new team `set team`

Sets the application to an existing team, i.e., changes the current "working" team to another. Will throw an error if the team does not exist

Format: `set team TEAM_NAME`

Examples:

- `set team CS2103` will change the current working team to be the "CS2103" team.

### Edit current team `edit team`

Format: `edit team [-n TEAM_NAME] [-d TEAM_DESCRIPTION]`

Examples:
- `edit team -n CS2103 -d "Software Engineering"` will edit the name of the current team to CS2103 and description
  to "Software Engineering"

### Delete an existing team `delete team`

Delete an existing team from the user's list of teams. Throws an error under these conditions.

- The target team does not exist.
- The target team is the only existing team.

Format: `delete team TEAM_NAME`

Examples:

- `delete team CS2103` will delete the team with the name "CS2103"

## Commands to Manage Members

### Adding a new member to the team `add member`

Add a new team member to the user’s currently selected team. Will throw an error if the member already exists in the team.

Format: `add member MEMBER_INDEX`

Examples:

- `add member 1` will add the first person in the list of people as a member of the current team.

### Delete a member from team `delete member`

Delete a team member from the user’s team.

Format: `delete member TEAM_MEMBER_INDEX`

Examples:

- `delete member 2` will delete the second member of the current team.

### Find members : `find member`

Finds all members in the current team whose names or emails contain any of the given keywords.

<div markdown="span" class="alert alert-info">
:information_source: **Note:** You can only find members using **either** emails or names. This means that you 
should not use both `-n` and `-e` in the `find member` command. 
</div>


Format:`find member [-n MEMBER_NAME] [-e MEMBER_EMAIL]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name or email is searched.
* Only full words will be matched e.g. `marcus` will not match `marcus@gmail.com`
* Persons matching at least one keyword will be returned (e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`)


Examples
* `find member -n Alex` finds team members with **names** containing the word "Alex".
* `find member -n Alex Beatrice` finds team members with **names** containing **either** "Alex" or "Beatrice".
* `find member -e alex@gmail.com`  finds team members with **emails** containing "alex@gmail.com".


### Listing all members of the team : `list members`

View all the members currently in the team, in the form of a list.

Format: `list members`

### Sort members : `sort members`
Sorts all members in the current team by name and displays them in the member list.

Format: `sort members ORDER`

Examples
* `sort members asc` sorts your team members in **alphabetical** order (as per their names).
* `sort members dsc` sorts the team members in **reverse alphabetical** order (as per their names).
* `sort members res` **resets** the order of the team members shown.

## Commands to Manage Tasks

### Add task to team : `add task`

Add a new task to your current team. The task name is compulsory, while other fields such as assignee index and
deadline are optional.

Multiple assignees can be assigned to the same task directly, by specifying the indices of each assignee in the member list, separated by spaces.
e.g. `add task "Merge PR" -a 1 3 4` will assign members 1, 3 and 4 to the task "Merge PR".

Format: `add task "TASK_NAME" [-a ASSIGNEE_INDEX] [-d DEADLINE]`

Examples:

* `add task "Merge PR" -a 1 -d 2022-12-02 23:59` will add a task with the name "Merge PR", assign the task to the
first member in the team list and set the deadline "02-Dec-2022 23:59", to the current team's task list.
* `add task "Review PR" -a 1 3 -d 2022-12-02 23:59` will add a task to the current team's
task list, with the name "Review PR", assign the task
to the first and third member in your team list and set the deadline of the task to be 02-Dec-2022 23:59.

### Edit task in team : `edit task`

Edits a specified task in the current team. The task index is compulsory, while the other fields are optional and
will be overwritten accordingly.

Multiple assignees can be assigned at one time, by specifying the indices of each of the members, separated by spaces.
e.g. `edit task 1 -a 1 3 4` will assign members 1, 3 and 4.

Format: `edit task TASK_INDEX -n "TASK_NAME" [-a ASSIGNEE_INDEX] [-d DEADLINE]`

Examples:

* `edit task 1 "Merge PR" -a 1 -d 2022-12-02 23:59` will edit the first task in the current team's task
list, setting the name as "Merge PR", setting the assignee as the first member in the team list and deadline
as "02-Dec-2022 23:59".
* `edit task 1 "Update UG" -d 2022-12-02 23:59` will edit the first task in the current team's task
list, setting the name as "Update UG" and changing the deadline as "02-Dec-2022 23:59". The assignees are not changed
in this case.

### Assign task to team member: `assign task`

Assign an existing task to one or more team members in the user’s team. Will display an error message if either the team
member(s) or the task is not found in the user’s team.

If the command is used without any member indices, all assignees will be removed from the task.

Format: `assign task TASK_INDEX -a MEMBER_INDICES` or `assign task TASK_INDEX -a` (to clear all assignees).

Examples:

- `assign task 1 -a 1` will assign the first task on the task list to the first member in the team.
- `assign task 2 -a 1 2` will assign the second task on the task list to both the first and second member in the team.
- `assign task 2 -a` will **remove all assignees** from the second task on the task list.

### Assign task to random team member: `assign random`

Assign an existing task to a random team member in the user’s team. Will display an error message if either the task is
not found in the user’s team or if there are no team members to assign the task to (if the team is empty,
or if the task has already been assigned to all members of the team.

Format: `assign random TASK_INDEX`

Examples:

- `assign random 1` will assign the first task on the task list to a random team member.

### Set Deadline for task: `set deadline`

Set a deadline for an existing task. Will display an error message if task is not found in the user’s team.
If a deadline has been set for the task before, a new deadline will be set if command is run again.
The deadline must be specified in `YYYY-MM-DD HH:MM` format.

Format: `set deadline TASK_INDEX DEADLINE`

Examples:

- `set deadline 1 2023-12-25 23:59` will set the deadline for the first task on the task list as 25 December 2023
11.59pm.

### Delete task from team : `delete task`

Delete an existing task from the team given the task's index. Will display an error message if the task is not found in
the user’s team, i.e., when the index exceeds the number of tasks.

Format: `delete task TASK_INDEX`

Examples:

- `delete task 1` will delete the first task of the current team.

### Mark tasks as done: `mark`

Mark a specified task as done. To undo this command, see the `unmark` command below.

Format: `mark TASK_INDEX`

Examples:

- `mark 1` will mark the first task in the team as done.

### Unmark tasks as done: `unmark`

Mark a specified task as incomplete. This will undo the `mark` command.

Format: `unmark TASK_INDEX`

Examples:

- `unmark 1` will mark the first task in the team as incomplete.

### Find tasks : `find task`

Find all tasks in the current team whose names contain any of the given keywords.

To reset the task list, see `list tasks` command below.

Format: `find task -n TASK_NAME`

* The search is case-insensitive. e.g. `user guide` will match `User Guide`
* The order of the keywords does not matter. e.g. `User Guide` will match `guide user`
* Only the name is searched.
* Only full words will be matched e.g. `user` will not match `userguide`
* Persons matching at least one keyword will be returned (e.g. `user` will return `user guide`, `user stories`)

Examples
* `find task -n User Guide` finds tasks with **names** containing **either** the word "User" or "Guide".

### List tasks in team: `list tasks`

View all the tasks currently in the user’s team in the form of a list. List can also be filtered based on complete or
remaining tasks.

The command `list tasks` can also be used to view all tasks again after the `find task` command has been run since 
`find task` command filters the current team’s tasks with some keyword(s).

Format: `list tasks [-i] [-c]`

Examples:
- `list tasks` will list all the tasks of the current team.
- `list tasks -i` will list all the incomplete tasks of the current team.
- `list tasks -c` will list all the completed tasks of the current team.

### View summary of task assignments in team: `summary`

View the number of tasks assigned to each user in the team.

Format: `summary`

### Sort tasks : `sort tasks`
Sorts all tasks in the current team by name and displays them in the task list

Format: `sort tasks ORDER`

Examples
* `sort tasks asc` sorts the tasks in **ascending** order.
* `sort tasks dsc` sorts the tasks in **descending** order.
* `sort tasks res` **resets** the order of the tasks shown.

### Filter tasks by team member : `tasksof`
Find all tasks in your current team that have been assigned to a particular team member.

Format: `tasksof MEMBER_INDEX`

Examples
* `tasksof 1` will show all tasks assigned to the first member in your current team's member list.

## Commands to Manage Links / URLs

### Add a new link : `add link`

Add a new link to the user's currently selected team.

Format: `add link -n NAME -l URL`

Examples:

- `add link -n google -l https://google.com` will add a link named "google" with the URL "https://google.com"

### Edit an existing link : `edit link`

Edit an existing link in the user's currently selected team.

Format: `edit link LINK_INDEX [-n NAME] [-l URL]`

Examples:

- `edit link 1 -n facebook -l https://facebook.com` will update the 1st link to have the name "facebook" with the 
  URL of "https://facebook.com"

- ### Delete an existing link : `delete link`

Delete the specified link from the user's currently selected team.

Format: `delete link LINK_INDEX`

Examples:

- `delete link 1`

## Miscellaneous Commands

### Clearing all entries : `clear`

Deletes all the people from the application.

Format: `clear`

### Switching between light and dark theme: `theme`

Toggles between light theme and dark theme.

Format: `theme`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Viewing help : `help`

Shows the URL to this help page, which will list all available commands.

Format: `help`

### Saving the data

TruthTable data is saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

TruthTable data is saved as a JSON file `[JAR file location]/data/truthtable.json`. Advanced users are welcome to
update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TruthTable will discard all data and start with an empty
data file on the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

# FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous TruthTable home folder.

---

# Command summary

| Action | Format, Examples |
| ------ | ---------------- |
| TBC    | TBC              |
| TBC    | TBC              |
