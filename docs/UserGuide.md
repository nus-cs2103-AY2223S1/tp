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

4. Launch the app by double-clicking the file by using the command `java -jar InTrack.jar` in your terminal. The GUI 
   similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will 
   open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all internship applications.

   * **`add`**`c/Microsoft p/Software Engineer s/5000 e/hr@microsoft.com w/careers.microsoft.com t/Urgent` :
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

Provides a link to the User Guide and shows a summary of all commands.

![help message](images/helpMessage.png)

Format: `help`

### Listing all internship applications : `list`

Shows a list of all internship applications in InTrack.

Format: `list`

### Clearing all internship applications : `clear`

Deletes all internship applications in InTrack.

Format: `clear`

### Saving the data

InTrack data is saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Adding an internship application: `add`

Adds a new internship application to InTrack.

Format: `add c/COMPANY_NAME p/POSITION e/EMAIL w/WEBSITE s/SALARY [t/TAG]…`

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** An internship can have any number of tags (including 0).
</div>

<div markdown="block" class="alert alert-info">
**:information_source: Regarding status:**<br>

There is no option to add status when adding an internship application.

By default, an added internship will have the status of in-progress. To change this, you may refer to the 
[`status` command](https://github.com/AY2223S1-CS2103T-T11-2/tp/blob/master/docs/UserGuide.md#updating-status-of-an-internship-application--status).

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Note about duplicates:**<br>

An internship application can only be added if it does not currently exist in InTrack. Each internship application is
uniquely identified by its `COMPANY_NAME` and `POSITION` with no regards to case-sensitivity.<br>

Example: If an internship application with the parameters `c/Microsoft p/Software Engineer` already exists in InTrack, 
a new one with `c/MICROSOFT p/Software Engineer` will be treated as a duplicate and will not be added.

</div>

| Parameter      | Representation                               | Constraints |
|----------------|----------------------------------------------|-------------|
| `COMPANY_NAME` | Company that is offering the internship      | Should only contain alphanumeric characters and spaces, and should not be blank|
| `POSITION`     | Position of the internship                   | Can take any values, but should not be blank            |
| `SALARY `      | Salary of the position                       | Must be fully numeric            |
| `EMAIL`        | Email of the hiring team                     | Should be of the format local-part@domain |
| `WEBSITE`      | Website containing details of the internship | Should be a valid URL            |
| `TAG`          | Tag(s) of the internship application         | Should be alphanumeric            |

Examples of usage:

* `add c/Microsoft p/Software Engineer e/hr@microsoft.com w/https://careers.microsoft.com s/5000 t/Urgent`

* `add c/Apple p/Frontend Developer e/hr@apple.com w/https://www.apple.com/careers s/5000`

Expected outcome:

* Internship application information is added to InTrack.

### Deleting an internship application : `delete`

Deletes the internship application at the specified `INDEX` from InTrack. 
`INDEX` refers to the index number of the desired entry as shown in the displayed internship list, and **must be a 
positive unsigned integer**.

Format: `delete INDEX`

Example of usage:

* `delete 1` 

Expected outcome:

* The first internship application in InTrack is deleted.

### Editing an internship application : `edit`

Edits the details of the currently selected internship application. 

<div markdown="block" class="alert alert-info">

**:information_source: Note about `edit`:**<br>

Before the application can be edited, it must first be selected via the [`select` command](https://github.com/AY2223S1-CS2103T-T11-2/tp/blob/master/docs/UserGuide.md#selecting-an-internship-application--select).

</div>

Format: `edit [c/NEW_COMPANY] [p/NEW_POSITION] [e/NEW_EMAIL] [w/NEW_WEBSITE] [t/NEW_TAGS] [s/NEW_SALARY]`

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** An edit command only requires minimally 1 field to be edited.
</div>

<div markdown="block" class="alert alert-info">

**:information_source: Note about editing the `status` and `remark` field:**<br>

The status and remark of the application can only be edited via the [`status` command](#updating-status-of-an-internship-application--status)
and [`remark` command](#adding-a-remark-to-an-internship-application--remark) respectively.

</div>

Examples of usage:

* `edit c/GOOGLE p/Data Analyst e/google@gmail.com w/https://google.com t/URGENT s/1000`
* `edit p/SWE`

Expected outcome:

* Edits the fields of the selected entry to match that in the input, such that the name of the company changes to 
Google, the position changes to Data Analyst, the email changes to google@gmail.com, the website changes to 
https://google.com, the tags are changed to just URGENT and the salary becomes $1000.
* Edits the position field of the selected entry to become SWE. All other details of the entry remain unchanged.

### Updating status of an internship application : `status`

Updates the status of the internship application at the specified `INDEX` from InTrack with 3 possible statuses
, `p` for "Progress", `r` for "Rejected" and `o` for "Offered".
`INDEX` refers to the index number shown in the displayed internship list and **must be a positive unsigned integer**.

Format: `status INDEX STATUS`, where `STATUS` must be either `p`, `o` or `r`


| Parameter | Representation                             | Constraints                                                  |
|-----------|--------------------------------------------|--------------------------------------------------------------|
| `INDEX`   | The index of the internship                | Must be a positive unsigned integer                          |       |
| `STATUS`   | The status of the internship to be updated | Is not case sensitive but can only be either `o`, `p` or `r` |


Example of usage:

* `status 1 o`

Expected outcome:

* The status of the first internship application in InTrack will be updated to 'Offered' inside a small green box.

### Adding a remark to an internship application : `remark`

Adds a `remark` to the selected internship application from InTrack.

<div markdown="block" class="alert alert-info">

**:information_source: Note about `remark`:**<br>

Before a remark can be added to an application, the internship must first be selected via the [`select` command](https://github.com/AY2223S1-CS2103T-T11-2/tp/blob/master/docs/UserGuide.md#selecting-an-internship-application--select).

</div>

Format: `remark r/ [REMARK]`

* If the `REMARK` field in the command is empty and there is an existing remark in the internship application, the 
remark will be cleared.

| Parameter | Representation                       | Constraints                                                                                                                                  |
|-----------|--------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| `REMARK`  | The remark given for the application | No constraints, anything can be a remark, if the `REMARK` field in the command is empty, the remark field of the application will be deleted |       |


Example of usage:

* `remark r/This is a hard one`

Expected outcome:

* The input remark `This is a hard one` will appear at the bottom of the selected internship application
panel.

Example of usage:

* `remark r/`

Expected outcome:

* The remark field at the bottom of the selected internship application panel will be cleared.

### Finding internship applications by company name : `findc`

Finds internship applications which has company name containing any of the given keywords.

Format: findc KEYWORD [MORE_KEYWORDS]

* The search is case-insensitive. E.g. `google` will match `Google`.
* The order of keywords does not matter. E.g. `Bytedance Tiktok` will match `Tiktok Bytedance`.
* Only full words will be matched. E.g. `Goog` will not match `Google`.
* Internship applications with company name matching at least one keyword will be returned. E.g. `findc google tech` 
will match `Google` and `Gov tech`.

Example of usage:
* `findc Google`

Expected outcome:
* All internships containing `Google` in the name field (case-insensitive) will be filtered and displayed.

### Finding internship applications by position : `findp`

Finds internship applications which has position name containing any of the given keywords.

Format: findp KEYWORD [MORE_KEYWORDS]

* The search is case-insensitive. E.g. `developer` will match `Developer`.
* The order of keywords does not matter. E.g. `Developer Frontend` will match `Frontend Developer`.
* Only full words will be matched. E.g. `Develop` will not match `Developer`.
* Internship applications with position name matching at least one keyword will be returned. E.g. `findp analyst 
junior` will match `Data analyst` and `Junior SWE`.

Example of usage:
* `findp Frontend`

Expected outcome:
* All internships containing `Frontend` in the position field (case-insensitive) will be filtered and displayed.

### Finding internship applications by tags : `findt`

Finds internship applications which has tags containing any of the given keywords.

Format: findt KEYWORD [MORE_KEYWORDS]

* The search is case-insensitive. E.g. `urgent` will match `Urgent`.
* The order of keywords does not matter. E.g. `Urgent Remote` will match `Remote Urgent`.
* Only full words will be matched. E.g. `Remote` will not match `Remotely`.
* Internship applications with tags matching at least one keyword will be returned. E.g. `findt urgent remote` will 
match tags `urgent` and `remote`.

Example of usage: 
* `findt urgent`

Expected outcome:
* All internships with the `urgent` tag (case-insensitive) will be filtered and displayed.

### Filtering internship applications by status : `filter`

Filters the list of internship applications based on their status, using `p` for "Progress", `r` for "Rejected" and
`o` for "Offered". 

Format: `filter STATUS`, where `STATUS` must be either `p`, `o` or `r`

Example of usage:
* `filter o`

Expected outcome:
* All internships that have status "Offered" will be filtered and displayed.

### Sorting internship applications: `sort`

Sorts the current list of internship applications on the left panel via their `SORT_TYPE` which is either `time` or `salary`, 
in either ascending or descending `SORT_ORDER`.
Format: `sort SORT_TYPE SORT_ORDER`

<div markdown="block" class="alert alert-info">

**:information_source: Note about `SORT`:**<br>
For sorting by the time of their tasks, internships are also sorted with the current date and time taken
into consideration.

Internships are sorted by their earliest **upcoming tasks**, thus dates of tasks that are before the current date is **not taken into account**
when sorting is conducted.

This means that internships without any upcoming tasks (i.e. internships without any tasks or with all tasks that are past the current date), will not 
be sorted in any particular order and will be kept at the bottom of the list of internships instead no matter what SORT_ORDER is given.

</div>

| Parameter    | Representation                                   | Constraints                                                                                      |
|--------------|--------------------------------------------------|--------------------------------------------------------------------------------------------------|
| `SORT_TYPE`  | The attribute that the internships are sorted by | Is not case sensitive and can only take `time` and `salary` as input fields, and cannot be blank |
| `SORT_ORDER` | The order that the internships are sorted in     | Is not case sensitive and can only take `a` and `d` as input fields, and cannot be blank         |

Example of usage:

* `sort time a`

Expected outcome:

* The list of internships are sorted in an ascending manner,
with the internship with the task with the earliest date and time that is after the current date and time at the top.

Example of usage:

* `sort salary a`

Expected outcome:

* The list of internships are sorted in an ascending manner,
with the internship with the lowest salary at the top.


### Viewing statistics of internship applications : `stats`

Displays statistics of the current list of internship applications based on the number of offered, in-progress and 
rejected applications.

Format: `stats`

### Selecting an internship application : `select`

Selects and shows the details of the internship application at the specified `INDEX`. `INDEX` refers to the index 
number of the desired entry as shown in the displayed internship list and **must be a positive unsigned integer**.

<div markdown="block" class="alert alert-info">

**:information_source: Note about `select`:**<br>

An internship application entry must be selected via the `select` command before the `edit`, `addtask`, `deltask`, 
`mail` and `remark` commands can be used.

</div>

Format: `select INDEX`

Example of usage:

* `select 1`

Expected outcome:

* The first internship application in InTrack is selected and its details are shown on the right panel.

### Adding a task to a selected internship application : `addtask`

Adds a task to a selected internship application at the selected `Internship` from InTrack.

Format: `addtask TASK_NAME /at TASK_TIME`

<div markdown="block" class="alert alert-info">

**:information_source: Note about `addtask`:**<br>

Before a task can be added to an application, the internship must first be selected via the [`select` command](https://github.com/AY2223S1-CS2103T-T11-2/tp/blob/master/docs/UserGuide.md#selecting-an-internship-application--select).

</div>

| Parameter   | Representation                               | Constraints                                  |
|-------------|----------------------------------------------|----------------------------------------------|
| `TASK_NAME` | Name of the task to be added                 | Can take any values, but should not be blank |
| `TASK_TIME` | The time that the added task is due          | Must be in the format `dd-MM-yyyy HH:mm`     |


Example of usage:

* `addtask Technical Interview /at 12-01-2023 15:00`

Expected outcome:

* The selected `Internship` would be updated with the new task added in the list in an ascending manner, with the task that has the earliest date and time at the top of the internship application on the right.

### Deleting a task from a selected internship application : `deltask`

Deletes the task at the specified index in the task list of a selected internship application.

Format: `deltask TASK_INDEX`

* Where `TASK_INDEX` refers to the index of the task in the list of tasks of the selected internship.

Examples of usage:

* `deltask 1`

Expected outcome:

* Deletes the first task of the selected `Internship`.

### Adding a tag to an internship application : `addtag`
Adds one or more `Tag`s to the internship application at the specified `INDEX` from InTrack.
`INDEX` refers to the index number of the desired entry as shown in the displayed internship list and **must be a 
positive unsigned integer**.

Format: `addtag INDEX TAG [MORE_TAGS]`

<div markdown="block" class="alert alert-info">

**:information_source: Note about `addtag`:**<br>

`TAG` is case sensitive, for example `urgent` and `Urgent` are considered as separate tags. Duplicate tags however will not be added to the internship.

If a user inputs multiple `TAG`s in a `addtag` command, the duplicate `TAG`s will not be added while the new ones will.

</div>

| Parameter | Representation                        | Constraints                                                |
|-----------|---------------------------------------|------------------------------------------------------------|
| `INDEX`   | The index of the internship           | Must be a positive unsigned integer                        |
| `TAG`     | The tag to be added to the internship | Must not be blank and must be a singular alphanumeric word |

Example of usage:

* `addtag 1 Urgent`

Expected outcome:

* The `Urgent` tag will appear on the internship application panel with the selected `INDEX`.

### Deleting a tag from an internship application : `deltag`
Deletes one or more existing `Tag`s from the internship application at the specified `INDEX` from InTrack.
`INDEX` refers to the index number shown in the displayed internship list and **must be a positive unsigned integer**.

Format: `deltag INDEX TAG [MORE_TAGS]`

| Parameter | Representation                        | Constraints                                                |
|-----------|---------------------------------------|------------------------------------------------------------|
| `INDEX`   | The index of the internship           | Must be a positive unsigned integer                        |
| `TAG`     | The tag to be added to the internship | Must not be blank and must be a singular alphanumeric word |

Example of usage:

* `deltag 1 Urgent`

Expected outcome:

* The `Urgent` tag will be removed from the internship application at the selected `INDEX`, if it exists.

### Sending an email to a company : `mail`
Sends an email to the email address registered to a selected internship by launching the default mail app with the 
target email recipient already set.

Format: `mail`

* `mail` does not check if the email address is valid/correct and is the responsibility of the user.
* `mail` invokes the native desktop application of the default mail app.

Expected outcome:

* The default mail app is launched with the email recipient set to the email of the company in the selected internship.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: What is a positive unsigned integer?<br>
**A**: A positive unsigned integer is a whole number that ranges from 1 to 4294967295 inclusive.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action               | Format, Examples                                                                                                                                                                         |
|----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**             | `help`                                                                                                                                                                                   |
| **List**             | `list`                                                                                                                                                                                   |
| **Clear**            | `clear`                                                                                                                                                                                  |
| **Exit**             | `exit`                                                                                                                                                                                   |
| **Add**              | `add c/COMPANY_NAME p/POSITION e/EMAIL w/WEBSITE s/SALARY [t/TAG]… ` <br/> e.g. `add n/Microsoft p/Software Engineer e/hr@microsoft.com w/https://careers.microsoft.com s/5000 t/Urgent` |
| **Delete**           | `delete INDEX` <br/> e.g. `delete 1`                                                                                                                                                     |
| **Edit**             | `edit c/NEW_NAME p/NEW_POSITION e/NEW_EMAIL w/NEW_WEBSITE t/NEW_TAGS s/NEW_SALARY` <br/> e.g. `edit s/1200`                                                                              |
| **Status**           | `status INDEX STATUS`<br/> e.g. `status 1 o`                                                                                                                                             |
| **Remark**           | `remark r/[REMARK]`<br/> e.g. `remark r/Revise graphs`                                                                                                                                   |
| **Find by Company**  | `findc KEYWORD [MORE_KEYWORDS]`<br/> e.g. `findc Google`                                                                                                                                 |
| **Find by Position** | `findp KEYWORD [MORE_KEYWORDS]`<br/> e.g. `findp Frontend`                                                                                                                               |
| **Find by Tag**      | `findt KEYWORD [MORE_KEYWORDS]`<br/> e.g. `findt Urgent`                                                                                                                                 |
| **Filter**           | `filter STATUS` <br/> e.g. `filter o`                                                                                                                                                    |
| **Sort**             | `sort SORT_TYPE SORT_ORDER`<br/> e.g. `sort time a`                                                                                                                                      |
| **Stats**            | `stats`                                                                                                                                                                                  |
| **Select**           | `select INDEX` <br/> e.g. `select 1`                                                                                                                                                     |
| **Add Task**         | `addtask TASK_NAME /at TASK_TIME `<br/> e.g. `addtask Technical Interview /at 28-10-2022 17:00`                                                                                          |
| **Delete Task**      | `deltask INDEX` <br/> e.g. `deltask `                                                                                                                                                    |
| **Add Tag**          | `addtag INDEX TAG [MORE_TAGS]`<br/> e.g. `addtag 1 Urgent`                                                                                                                               |
| **Delete Tag**       | `deltag INDEX TAG [MORE_TAGS]`<br/> e.g. `deltag 1 Urgent`                                                                                                                               |
| **Mail**             | `mail`                                                                                                                                                                                   |
