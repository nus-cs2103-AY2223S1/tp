---
layout: page
title: User Guide
---

Teaching Assistant Assistant (TAA) is a **desktop app for Teaching Assistants (TA) to track student progress and tasks,
optimized for use via a Command Line Interface** (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done
faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
[comment]: <> (TODO: Add our release here)
1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`task list`** : Lists all tasks.

   * **`task add NAME`** : Adds a new task with the specified `NAME`.

   * **`task remove NAME`** : Deletes the specified task.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

[comment]: <> (TODO: Change this)
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Add students : `student add studentName`
* Command `student add <studentName>`
* Adds a student to the list of students

### Remove students : `student remove studentName`
* Command `student remove <studentName>`
* Removes the named student from the list of students

### Rename students : `student rename oldStudentName newStudentName`
* Command `student rename <oldStudentName> <newStudentName>`
* Updates the name of the student

### List students : `student list`
* Command `student list`
* Abbreviate with `students`
* Shows a list of all students

### Add new task : `task add newTaskName`
* Command `task add <studentName>`
* Adds a task to the list of tasks
* The task is initially assigned to no students

### Remove task : `task remove taskName`
* Command `task remove <taskName>`
* Removes the named task from the list of tasks

### Rename students : `task rename oldStudentName newStudentName`
* Command `task rename <oldTaskName> <newTaskName>`
* Updates the name of the task

### Add deadline to task : `task deadline taskName deadline`
* Command `task deadline <taskName> <deadline>`
* Adds a deadline to the task

### List tasks : `task list`
* Command `task list`
* Abbreviate with `tasks`
* Shows a list of tasks

### Mark tasks : `task mark taskNumber`
* Command `task mark <taskNumber>`
* Marks the task with the task number on the list.

### Unmark tasks : `task unmark taskNumber`
* Command `task unmark <taskNumber>`
* Unmarks the task with the task number on the list.

### Assign a task to a student : `task assign taskName studentName`
* Command `task assign <taskName> <studentName>`
* Assign the task `taskName` to the student `studentName`.


### Add new group : `group add newGroupName`
* Command `group add <newGroupName>`
* Adds a new group with the name `newGroupName`

### Rename group : `group rename oldGroupName newGroupName`
* Command `group rename <oldGroupName> <newGroupName>`
* Renames the group from `oldGroupName` to `newGroupName`

### Remove group : `group remove groupName`
* Command `group remove <groupName>`
* Removes the group named `groupName`

### Enrol a student into a group : `group enrol groupName studentName`
* Command `group enrol <groupName> <studentName>`
* Enrols a student to the group

### Expel a student from a group : `group expel groupName studentName`
* Command `group expel <groupName> <studentName>`
* Removes the student `studentName` from the group `groupName`.

### View the list of students in a group : `group roster groupName`
* Command `group roster <groupName>`
* Displays a list of students enrolled in `groupName`.


_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

[comment]: <> (TODO: Update this)
Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
