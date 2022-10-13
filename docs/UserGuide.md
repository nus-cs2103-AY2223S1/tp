---
layout: page
title: User Guide
---

NotioNUS is a **desktop app for managing module tasks, which can be used via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NotioNUS can get your task management done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest NotioNUS.jar from [here](https://github.com/AY2223S1-CS2103T-F12-3/tp).

3. Move the file to the folder you want to use as the home folder for NotioNUS

4. Double-click the file to start the application. A GUI as shown below should appear.
	Note: The application will contain some sample data when launched.

    ![Ui](images/user-guide/Ui.png)

5. Type the command in the “COMMAND INPUT” box and press “ENTER” to execute the command<br>
   Some example commands you can try:

   - `ls -a` :
    Lists all tasks.
   - `add <module> <taskName> [--tag <tag>]` : 
    Adds a task called `taskName` from the module `module` into the task list.
   - `delete <taskId>` : 
   	Deletes the task in the task list with task id `taskId`.
   - `edit <taskId> <module> <taskName>` : 
   	Changes the module and task name of the task with `taskId` to `module` and `taskName` respectively.


--------------------------------------------------------------------------------------------------------------------

## Features

### Getting help : `help`

Displays list of commands and information about NotioNUS.

Format: `help`

### Adding a task: `add`

Adds a person to the task list.

Format: `add <module> <taskName> [--tag <tag>]`
* `module` must not contain spaces 
* `taskName` can contain spaces 
* After a task has been added, it will be assigned to a taskId as represented in the task list.

Examples:
* `add CS2103T Task 1 --tag homework`

### Marking a task as completed: `mark`

Mark a task as complete.

Format: `mark <taskId>`
* `taskId`: This refers to the taskId shown in the displayed full task list. This value should be a non-zero positive integer.

Example: `mark 2`

### Tagging a task: `tag`

Allows you to tag a task.

Format : `tag <taskId> <tag>`
* `taskId`: This refers to the taskId shown in the displayed full task list. This value should be a non-zero positive integer.
* `tag`: The word to tag the task with

Example: `tag 2 optional`

### Listing all tasks : `li -a`

Shows a list of all tasks in the task list.

Format: `ls -a`

### Listing all unmarked tasks : `li -u`

Shows a list of all unmarked tasks in the task list.

Format: `ls -u`

### Listing all tasks under the same module : `li --module <module>`

Shows a list of all tasks under the same module.

Format: `ls --module <module>`

### Editing a task : `edit`

Edits an existing task in the task list.

Format: `edit <taskId> <module> <taskname>`

* Edits the task at the specified `taskId`. The taskId refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* All fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 CS2103T ip` Edits the taskName to ip.

### Deleting a task : `delete`

Deletes a task from the task list.

Format: `delete <taskId>`

* `taskId` refers to the index number shown in the displayed task list. This value should be a non-zero positive integer.

Examples: 
* `delete 1`
  * Deletes 1st task in the task list.
  * Remaining tasks’ taskId will be automatically updated. 
  
### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

NotioNUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

NotioNUS data are saved as a JSON file `[JAR file location]/data/notionusdata.json`. Advanced users are welcome to update data directly by editing that data file.


**Caution:**
If your changes to the data file makes its format invalid, NotioNUS will discard all data and start with an empty data file at the next run.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

### Find tasks by name `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

To be added..

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                    |
|------------|---------------------------------------------------------------------|
| **Add**    | `add m/MODULE n/taskName [--tag <tag>]`                             |
| **Clear**  | `clear`                                                             |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                 |
| **Edit**   | `edit <taskId> <module> <taskname>…​`<br> e.g.,`edit 1 CS2103T ip`  |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`          |
| **List**   | `li -a` `li -u` `li --module <module>`                              |
| **Help**   | `help`                                                              |
