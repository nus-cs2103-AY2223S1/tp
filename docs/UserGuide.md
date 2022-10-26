---
layout: page
title: User Guide
---

InTrack is a **desktop app for managing your internship applications, optimized for use via a Command Line Interface**
(CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, InTrack can get your
internship management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `InTrack.jar` from [here](https://github.com/AY2223S1-CS2103T-T11-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for InTrack.

4. Launch the app by double-clicking the file by using the command java -jar InTrack.jar. The GUI similar to the below 
   should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will 
   open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all internship applications.

   * **`add`**`n/Microsoft p/Software Engineer s/5000 e/hr@microsoft.com w/careers.microsoft.com t/Urgent` :
     Adds an internship application for `Software Engineer` at `Microsoft` to InTrack.

   * **`delete`**`1` : Deletes the first internship application displayed in InTrack.

   * **`clear`** : Deletes all entries in InTrack.

   * **`exit`** : Exits InTrack.
   
6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add p/POSITION`, `POSITION` is a parameter which can be used as `add p/Software Engineer`.

* Items in square brackets are optional.<br>
  e.g `w/WEBSITE [t/TAG]` can be used as `w/careers.microsoft.com t/Urgent` or as `w/careers.microsoft.com`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Urgent`, `t/Urgent t/Remote` etc.

* Extraneous parameters for commands that do not take in parameters (such as `help` and `list`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help`

Shows a message explaining how to access the help page and shows the format of all commands.

![help message](images/helpMessage.png)

Format: `help`

### Listing all internship applications : `list`

Shows a list of all internship applications in InTrack.

Format: `list`

### Clearing all internship applications : `clear`

Deletes all internship applications in InTrack.

Format: `clear`

### Saving the data

InTrack data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Adding an internship application: `add`

Adds a new internship application to InTrack.

Format: `add n/COMPANY_NAME p/POSITION s/SALARY e/EMAIL w/WEBSITE [t/TAG]…`

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** An internship can have any number of tags (including 0).
</div>

<div markdown="block" class="alert alert-info">

**:information_source: Note about duplicates:**<br>

An internship application can only be added if it does not currently exist in InTrack. Each internship application is
uniquely identified by its `COMPANY` and `POSITION` with no regards to case-sensitivity.<br>

Example: If an internship application with the parameters `n/Microsoft p/Software Engineer` already exists in InTrack, 
a new one with `n/MICROSOFT p/Software Engineer` will be treated as a duplicate and will not be added.

</div>

| Parameter      | Representation                               | Constraints |
|----------------|----------------------------------------------|-------------|
| `COMPANY_NAME` | Company that is offering the internship      |             |
| `POSITION`     | Position of the internship                   |             |
| `SALARY `      | Salary of the position                       |             |
| `EMAIL`        | Email of the hiring team                     |             |
| `WEBSITE`      | Website containing details of the internship |             |
| `TAG`          | Tag(s) of the internship application         |             |

Examples of usage:

* `add n/Microsoft p/Software Engineer s/5000 e/hr@microsoft.com w/https://careers.microsoft.com t/Urgent`

* `add n/Apple p/Frontend Developer s/5000 e/hr@apple.com w/https://www.apple.com/careers`

Expected outcome:

* Internship application information is added to InTrack.

### Deleting an internship application : `delete`

Deletes the internship application at the specified `INDEX` from InTrack. 
`INDEX` refers to the index number shown in the displayed internship list and **must be a positive unsigned integer**.

Format: `delete INDEX`

Example of usage:

* `delete 1` 

Expected outcome:

* The first internship application in InTrack is deleted.

### Editing an internship application : `edit`

### Updating status of an internship application : `status`

### Adding a remark to an internship application : `remark`

### Finding internship applications by company name : `findn`

### Finding internship applications by position : `findp`

### Filtering internship applications by status : `filter`

### Sorting internship applications: `sort`

### Viewing statistics of all internship applications : `stats`

### Selecting an internship application : `select`

Selects and shows the details of the internship application at the specified `INDEX`
`INDEX` refers to the index number shown in the displayed internship list and **must be a positive unsigned integer**.

Format: `select INDEX`

Example of usage:

* `select 1`

Expected outcome:

* The first internship application in InTrack is selected and its details are shown on the right panel. 

### Adding a task to a selected internship application : `addtask`

### Deleting a task from a selected internship application : `deltask`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: What is a positive unsigned integer?<br>
**A**: A positive unsigned integer is a whole number that ranges from 1 to 4294967295 inclusive.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action               | Format, Examples |
|----------------------|------------------|
| **Help**             | `help`           |
| **List**             | `list`           |
| **Clear**            | `clear`          |
| **Exit**             | `exit`           |
| **Add**              |                  |
| **Delete**           |                  |
| **Edit**             |                  |
| **Status**           |                  |
| **Remark**           |                  |
| **Find by Company**  |                  |
| **Find by Position** |                  |
| **Filter**           |                  |
| **Sort**             |                  |
| **Stats**            |                  |
| **Select**           |                  |
| **Add Task**         |                  |
| **Delete Task**      |                  |

