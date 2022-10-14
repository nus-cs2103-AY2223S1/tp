---
layout: page
title: User Guide
---

HR Pro Max++ is a **desktop app for team leads in SMEs to manage projects and staff members under them. It is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, HR Pro Max++ can get your project management tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `HR_Pro_Max++.jar` from [here](https://github.com/AY2223S1-CS2103T-T09-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your project management application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all projects.

   * **`add`**`pn/Duke pb/ 20k pd/ 2022-09-27` : Adds a project named Duke` to HR Pro Max++.

   * **`delete`**`3` : Deletes the 3rd project shown in the current list.

   * **`clear`** : Deletes all projects currently saved on HR Pro Max++.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add pn/PROJECT_NAME`, `PROJECT_NAME` is a parameter which can be used as `add pn/CS2103T`.

* Items in square brackets are optional.<br>
  e.g `pn/PROJECT_NAME [t/TAG]` can be used as `pn/2101 t/fun` or as `pn/2101`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/fun`, `t/fun t/expensive` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `pn/PROJECT_NAME pb/PROJECT_BUDGET`, `pb/PROJECT_BUDGET pn/PROJECT_NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `sp/12341234 sp/56785678`, only `sp/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a Project: `add`

Adds a project to Project list.

Format: `add pn/PROJECT_NAME pb/PROJECT_BUDGET pd/PROJECt_DEADLINE [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A project can have any number of tags (including 0)
</div>

Examples:
* `add pn/2103T_TP pb/100000 pd/2022-01-01`
* `add pn/CS2100 t/Tiring pb/1000 pd/2022-01-01 t/Fun time`

### Deleting a Project : `delete`

Deletes the specified Project from Project list.

Format: `delete INDEX`

* Deletes the project at the specified `INDEX`.
* The index refers to the index number shown in the displayed Project list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd project in Project list.
* `find 2103T_TP` followed by `delete 1` deletes the 1st project in the results of the `find` command.

### Listing all projects : `list`

Show all the Projects in the Projects list.

Format: `list`

### Adding a staff member to Project : `addstaff`

Adds a staff member info to Project in project list.

Format: `addstaff pn/PROJECT_NAME sn/STAFF_NAME si/INSURANCE_STATUS sd/STAFF_DEPARTMENT st/STAFF_TITLE sc/CONTACT NUMBER`

* All fields for staff member are required

Examples:
* `addStaff pn/DUKE sn/John Doe sp/98765432 si/true sd/Accounting st/Accountant` Adds staff member named `John Doe` to the project DUKE.
* `addStaff pn/ROOFUS sn/Betsy Crowe sp/1234567 st/Admin Staff sd/Admin si/false` Adds staff member named `Betsy Crown` to the project ROOFUS.

### Deleting a staff member from Project : `delstaff`

Deletes a staff member and all its info inside a project in the project list.

Format: `delstaff pn/PROJECT_NAME sn/STAFF_NAME`

Examples:

* `delstaff pn/2103 sn/John` Delete staff member named `John` from project `2103`.
* `delstaff pn/DUKE sn/Betty` Delete staff member named `Betty` from project `DUKE`.

### View the staff list within a project: `view`

Views the staff list of a specified project in Project list.

Format: `view INDEX`

* View the staff list of the project at the specified `INDEX`.
* The index refers to the index number shown in the displayed Project list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `view 2` displays the staff list of the 2nd project in Project list on the bottom right.
* `find 2103T_TP` followed by `view 1` display the staff list of the 1st project from the result of the `find` command.

### Locating Project by Name: `find`

Finds a specified Project in Project list.

Format: `find pn/PROJECT_NAME`

* The search is case-insensitive. e.g `hans` will match `Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`

Examples:
* `find CS2103` returns `CS2103` and `CS2103 TP`

### Editing a Project : `edit`

Edits an existing Project in the Project list.

Format: `edit INDEX [pn/PROJECT_NAME] [pb/PROJECT_BUDGET] [pd/PROJECT_DEADLINES] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
* `edit 1 pb/5000` Edits the project budget of the 1st project to be `5000`.
* `edit 2 pn/CS2103 t/` Edits the name of the 2nd person to be `CS2103` and clears all existing tags.

### Clearing all Projects : `clear`

Clears all Projects from the Project list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data
Project data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action              | Format, Examples                                                                                 |
|---------------------|--------------------------------------------------------------------------------------------------|
| **Add project**     | `add pn/PROJECT_NAME  [t/TAG]…​` <br> e.g., `add pn/DUKE pd/2022-10-25 pb/10000 t/funz`          |
| **Add staff**       | `addstaff pn/PROJECT_NAME sn/STAFF_NAME…​` <br> e.g., `addstaff pn/DUKE sn/DEXTER si/true sd/HR` |
| **Delete staff**    | `delstaff pn/PROJECT_NAME sn/STAFF_NAME` <br> e.g., `delstaff pn/DUKE sn/DEXTER`                 |
| **Clear**           | `clear`                                                                                          |
| **Delete**          | `delete INDEX`<br> e.g., `delete 3`                                                              |
| **Edit**            | `edit INDEX [pn/PROJECT_NAME] [pd/PROJECT_DEADLINE]…​`<br> e.g.,`edit 2 pn/ROOFUS pb/1350000`    |
| **Find**            | `find pn/PROJECT_NAME`<br> e.g., `find DUKE`                                                     |
| **List**            | `list`                                                                                           |
| **View staff list** | `view INDEX` <br> e.g., `view 1`                                                                 |
| **Help**            | `help`                                                                                           |

## Prefix Summary
Prefixes that start with `s` are for project staff member details.
Prefixes without the prefix `s` are meant for project details.

| Prefix  | Meaning                | Format/ Examples        |
|---------|------------------------|-------------------------|
| **pb/** | Project budget         | pb/PROJECT_BUDGET       |
| **pd/** | Project deadline       | pd/PROJECT_DEADLINE     |
| **pn/** | Project name           | pn/PROJECT_NAME         |
| **sc/** | Staff Contact          | sc/STAFF_CONTACT        |
| **sd/** | Staff department       | sd/STAFF_DEPARTMENT     |
| **si/** | Staff insurance status | si/INSURANCE_STATUS     |
| **sn/** | Staff name             | sn/STAFF_NAME           |
| **st/** | Staff title            | st/STAFF_TITLE          | 

