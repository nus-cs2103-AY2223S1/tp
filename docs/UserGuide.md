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

Updates the status of the internship application at the specified `INDEX` from InTrack with 3 possible statuses
, `p` for "Progress", `r` for "Rejected" and `o` for "Offered".
`INDEX` refers to the index number shown in the displayed internship list and **must be a positive unsigned integer**.

Format: `status INDEX STATUS`, where `STATUS` must be either `p`, `o` or `r`

Example of usage:

* `status 1 o`

Expected outcome:

* The status of the first internship application in InTrack will be updated to 'Offered' inside a small green box.

### Adding a remark to an internship application : `remark`

Adds a `remark` to the internship application at the specified `INDEX` from InTrack.
`INDEX` refers to the index number shown in the displayed internship list and **must be a positive unsigned integer**.

Format: `remark INDEX [r/REMARK]`

* If the `REMARK` field in the command is empty and there is an existing remark in the internship application, the 
remark will be cleared.

Example of usage:

* `remark 1 r/This is a hard one`

Expected outcome:

* The input remark `This is a hard one` will appear at the bottom of the internship application
panel with the selected input `INDEX`.

Example of usage:

* `remark 1 r/`

Expected outcome:

* The remark field at the bottom of the internship application panel with the selected input `INDEX` will be cleared.

### Finding internship applications by company name : `findn`

### Finding internship applications by position : `findp`

### Filtering internship applications by status : `filter`

### Sorting internship applications: `sort`

Sorts the list of internship applications on the left panel by the date and time of their tasks by `ORDER`, where 
`ORDER` is either `a` for ascending or `d` for descending.

Format: `sort ORDER`

* The internships are also sorted with the current date and time taken into consideration.
* The date of the task that is the earliest and is on or after the current date(if any) in the internship will be used
as the factor in sorting, if there aren't any, the date of the task with  the
earliest date will be used instead for that internship.
* `sort a`  will sort all internships in an ascending manner, with the
internship with the task that has the earliest date and time that is on or after the current time at the top. 
* If an internship only have one task however, only that task's date and time is taken into consideration, so even if 
that task is before the current date but has the earliest date and time, it will still be at the top.
* `sort d`  will sort all internships in a descending manner, with the
    internship with the task that has the earliest date and time that is on or after the current time at the bottom.
* If an internship only have one task however, only that task's date and time is taken into consideration, so even if
  that task is before the current date but has the earliest date and time, it will still be at the bottom.


Example of usage:

* `sort a`

Expected outcome:

* The list of internships are sorted in an ascending manner,
with the internship with the task with the earliest date and time that is after the current date and time at the top.

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

### Adding a tag to an internship application : `addtag`
Adds one or more `Tag`s to the internship application at the specified `INDEX` from InTrack.
`INDEX` refers to the index number shown in the displayed internship list and **must be a positive unsigned integer**.

Format: `addtag INDEX TAG [MORE_TAGS]`

Example of usage:

* `addtag 1 Urgent`

Expected outcome:

* The `Urgent` tag will appear on the internship application panel with the selected `INDEX`.

### Deleting a tag from an internship application : `deltag`
Deletes one or more existing `Tag`s from the internship application at the specified `INDEX` from InTrack.
`INDEX` refers to the index number shown in the displayed internship list and **must be a positive unsigned integer**.

Format: `deltag INDEX TAG [MORE_TAGS]` 

* Apart from the standard format, the user can also input `deltag clear` to clear all tags in the internship application
with the selected `INDEX`.

Example of usage:

* `deltag 1 Urgent`

Expected outcome:

* The `Urgent` tag will disappear on the internship application panel with the selected `INDEX`, if it exists.

Example of usage:

* `deltag clear`

Expected outcome:

* All tags from that internship with the selected `INDEX` will disappear.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: What is a positive unsigned integer?<br>
**A**: A positive unsigned integer is a whole number that ranges from 1 to 4294967295 inclusive.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action               | Format, Examples              |
|----------------------|-------------------------------|
| **Help**             | `help`                        |
| **List**             | `list`                        |
| **Clear**            | `clear`                       |
| **Exit**             | `exit`                        |
| **Add**              |                               |
| **Delete**           |                               |
| **Edit**             |                               |
| **Status**           | `status`, `status 1 o`        |
| **Remark**           | `remark`, `remark 1 r/ hello` |
| **Find by Company**  |                               |
| **Find by Position** |                               |
| **Filter**           |                               |
| **Sort**             | `sort`, `sort a`. `sort b`    |
| **Stats**            |                               |
| **Select**           |                               |
| **Add Task**         |                               |
| **Delete Task**      |                               |
| **Add Tag**          | `addtag`, `addtag 1 Urgent`   |
| **Delete Tag**       | `deltag`, `deltag 1 Urgent`   |

