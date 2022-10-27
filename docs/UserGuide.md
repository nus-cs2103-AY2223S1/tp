---
layout: page
title: User guide
---

TABS helps project team leaders **overview tasks assigned to group members**. It is targeted at individuals with less programming background i.e. **less CLI-intensive** in nature.

- [`Quick start`](#quick-start)
- [`Features`](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a person: `addperson`](#adding-a-person-addperson)
  - [Deleting a person : `deleteperson`](#deleting-a-person-deleteperson)
  - [Editing a person: `editperson`](#editing-a-person-editperson)
  - [Locating persons by name: `findperson`](#locating-persons-by-name-findperson)
  - [Listing all persons: `listpersons`](#listing-all-persons-listpersons)
  - [Adding a group: `addgroup`](#adding-a-group-addgroup)
  - [Deleting a group: `deletegroup`](#deleting-a-group-deletegroup)
  - [Adding a member to a group: `addmember`](#adding-a-member-to-a-group-addmember)
  - [Deleting a member from a group: `deletemember`](#deleting-a-member-from-a-group-deletemember)
  - [Display a group: `displaygroup`](#display-a-group-displaygroup)
  - [Listing all groups: `listgroups`](#listing-all-groups-listgroups)
  - [Assigning a task to a member: `assigntask`](#assigning-a-task-to-a-member-assigntask)
  - [Deleting a task from a member: `deletetask`](#deleting-a-task-from-a-member-deletetask)
  - [Assigning a task to all members: `assigntaskall`](#assigning-a-task-to-all-members-assigntaskall)
  - [Deleting a task from all members: `deletetaskall`](#deleting-a-task-from-all-members-deletetaskall)
  - [Clearing existing data: `clear`](#clearing-all-entries-clear)
  - [Exiting the program : `exit`](#exiting-the-program-exit)
- [`Command summary`](#command-summary)

---
## Quick start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest TABS.jar.
3. Copy the file to the folder you want to use as the home folder for your TABS.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
Note how the app contains some sample data.
<br><br>
![TABS example](images/user-guide/Ui.png)
<br><br>
5. Refer to the [Features](#features) below for details of each command.


--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Shortcut keys to use the application:**<br>

* Use the `TAB` key to navigate between command box, person pane, and group pane in TABS.

* Use arrow keys to switch between *Contact details* and *Assignments*.

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addperson n/NAME`, `NAME` is a parameter which can be used as `addperson n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listpersons`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `listpersons 123`, it will be interpreted as `listpersons`.

</div>

### Viewing help: help

Shows a list of commands explaining how to use TABS and a message to the TABS' user guide.

![Help Example](images/user-guide/Help.png)

Format: help

### Adding a person: `addperson`

Adds a person to TABS.

Format: `addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb:
<b>Tip:</b> A person can have any number of tags (including 0)
</div>

Examples:
* `addperson n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addperson n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Deleting a person: `deleteperson`

Deletes the specified person from TABS.

Format: `deleteperson NAME`
* Deletes the person with the specified `NAME`. The person with this `NAME` must already exist in the app.

Examples:
* `deleteperson Betsy` deletes the person with the username `Betsy`.
* `deleteperson Betsy Hoover` deletes the person with the username `Betsy Hoover`.

### Editing a person: `editperson`

Edits an existing person in TABS.

Format: `editperson NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`
* Edits the person with the specified `NAME`. The person with this `NAME` must already exist in the app.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `editperson John Doe p/91234567 e/johndoe@example.com` Edits the phone number and email address of `John Doe` to be `91234567` and `johndoe@example.com` respectively.
*  `editperson BetsyCrower n/Betsy Crower t/` Edits the name of `BetsyCrower` to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `findperson`

Finds persons whose names contain any of the given keywords.

Format: `findperson KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findperson John` returns `john` and `John Doe`
* `findperson alex david` returns `Alex Yeoh`, `David Li`
<br>
<br>
  ![result for 'find alex david'](images/user-guide/findAlexDavidResult.png)

### Listing all persons: `listpersons`

Shows a list of all persons in TABS.

Format: `listpersons`

### Adding a group: `addgroup`

Adds an empty group with the specified `GROUP` to TABS.

Format: `addgroup GROUP`

Examples:
* `addgroup CS2103T` will create an empty group with the group name `CS2103T`.

### Deleting a group: `deletegroup`

Deletes the specified group with the group name `GROUP` from TABS.

Format: `deletegroup GROUP`

Examples:
* `deletegroup CS2103T` deletes the group `CS2103T` from TABS.

### Adding a member to a group: `addmember`

Adds a member to a specified group.

Format: `addmember g/GROUP n/NAME`

Examples:
* `addmember g/CS2103T n/John Doe` will add `John Doe` to the group `CS2103T`.
* `addmember g/CS2101 n/Alice` will add `Alice` to the group `CS2101`.

### Deleting a member from a group: `deletemember`

Deletes an existing person from an existing group in TABS.

Format: `deletemember g/GROUP n/NAME`

Examples:
* `deletemember g/CS2103T n/John Doe` will delete member `John Doe` from the group `CS2103T`.
* `deletemember g/CS2101 n/Alice` will delete member `Alice` from the group `CS2103T`.

### Display a group: `displaygroup`

Displays the group members allocated to the specified `GROUP`. Instead of details, assigned tasks will be listed.

![TABS example](images/user-guide/displaygroup_example.png)

Format: `displaygroup GROUP`
* Displays the members associated with the `GROUP` and their tasks.

Examples:
* `displaygroup CS2103T` displays the members associated with `CS2103T` and their tasks.

### Listing all groups: `listgroups`

Lists all existing groups in TABS.

Format: `listgroups`

### Assigning a task to a member: `assigntask`

Assigns a task to a member with the given name in a group in TABS.

Format: `assigntask NAME g/GROUP task/TASK w/WORKLOAD [d/DEADLINE]`
* Assigns a `TASK` to a person with the specified `NAME`, `GROUP`, `WORKLOAD` and `DEADLINE`.
* `WORKLOAD` specified must be `low`, `medium` or `high`.
* `DEADLINE` must be in `yyyy-MM-dd` or `yyyy-MM-dd HH:mm` format.

Examples:
* `assigntask John g/CS2103T task/TeamProject w/High`
assigns `John` in group `CS2103T` with a `High` workload task `TeamProject`.
* `assigntask Alice g/Group Alpha task/Coursework 0 w/Low d/2022-01-01 23:59`
assigns `Alice` in group `Group Alpha` with a `Low` workload task `Coursework 0` and the deadline of the task is `2022-01-01 23:59`.

<div markdown="span" class="alert alert-primary">
  :information_source: **Extra note:** 

A workload indicator is shown beside each person in TABS.
When the workload of the person increase, the workload indicator will gradually change from green to red.

![Workload_Indicator](images/user-guide/workload_indicator.png)
</div>

### Deleting a task from a member: `deletetask`

Deletes a task from a member with the given name in a group.

Format: `deletetask NAME g/GROUP task/TASK`
* Deletes a `TASK` from a person with the specified `NAME` and specified `GROUP`.

Examples:
* `deletetask John g/CS2103T task/TeamProject` deletes task `TeamProject` from person `John`in group `CS2103T`.
* `deletetask Billy g/Team Delta task/Delta Project` deletes `Delta Project` from person `Billy` with in group `Team Delta`.

### Assigning a task to all members: `assigntaskall`

Assigns a task to all members in a group. Members with the same task are ignored.

Format: `assigntaskall g/GROUP task/TASK w/WORKLOAD d/DEADLINE`
* Assigns a `TASK` to all members with the specified `GROUP`, `WORKLOAD` and `DEADLINE`.
* `WORKLOAD` specified must be `low`, `medium` or `high`.
* `DEADLINE` must be in `yyyy-MM-dd` or `yyyy-MM-dd HH:mm` format.

Examples:
* `assigntaskall g/CS2103T task/TeamProject w/High`
assigns all members in group `CS2103T` with a `High` workload task `TeamProject`.
* `assigntaskall g/Group Alpha task/Coursework 0 w/Low d/2022-01-01 23:59`
assigns all members in group `Group Alpha` with a `Low` workload task `Coursework 0` and the deadline of the task is `2022-01-01 23:59`.

### Deleting a task from all members: `deletetaskall`

Deletes a task from all members in a group. Members without this task are ignored.

Format: `deletetaskall g/GROUP task/TASK`
* Deletes a `TASK` from all members with the specified `GROUP`.

Examples:
* `deletetaskall John g/CS2103T task/TeamProject` deletes task `TeamProject` from all members in group `CS2103T`.
* `deletetaskall Billy g/Team Delta task/Delta Project` deletes `Delta Project` from all members with in `Team Delta`.

### Clearing all entries: `clear`

Clears all entries from the address book.

Format: clear

### Exiting the program: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                    | Format, Examples                                                                                                                                            |
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**                  | `help`                                                                                                                                                      |
| **Add Person**            | `addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]`<br> e.g. `addperson n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567` |
| **Delete Person**         | `deleteperson NAME`<br> e.g. `deleteperson Betsy`                                                                                                           |
| **Edit Person**           | `editperson NAME [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]`<br> e.g. `edituser 2 n/James Lee e/jameslee@example.com`                          |
| **Find Person**           | `findperson KEYWORD [MORE_KEYWORDS]`<br> e.g. `finduser James Jake`                                                                                         |
| **List Persons**          | `listpersons`                                                                                                                                               |
| **Add Group**             | `addgroup GROUP` <br> e.g. `addgroup CS2103T`                                                                                                               |
| **Delete Group**          | `deletegroup GROUP` <br/> e.g. `deletegroup CS2103T`                                                                                                        |
| **Add Member**            | `addmember g/GROUP n/NAME` <br/> e.g. `addmember g/CS2101 n/Alice`                                                                                          |
| **Delete Member**         | `deletemember g/GROUP n/NAME` <br/> e.g. `deletemember g/CS2101 n/Alice`                                                                                    |
| **Display Group**         | `displaygroup GROUP` <br/> e.g. `displaygroup CS2103T`                                                                                                      |
| **List Groups**           | `listgroups`                                                                                                                                                |
| **Assign Task**           | `assigntask NAME g/GROUP task/TASK w/WORKLOAD [d/DEADLINE]` <br/> e.g. `assigntask Alice g/Group Alpha task/Coursework 0 w/Low  d/2022-01-01 23:59`         |
| **Delete Task**           | `deletetask NAME g/GROUP task/TASK` <br/> e.g. `deletetask John g/CS2103T task/TeamProject`                                                                 |
| **Assign Task <br/> All** | `assigntaskall g/GROUP task/TASKw/WORKLOAD d/DEADLINE` <br/> e.g. `assigntaskall g/Group Alpha task/Coursework 0 w/Low  d/2022-01-01 23:59`                 |
| **Delete Task <br/> All** | `deletetaskall g/GROUP task/TASK` <br/> e.g. `deletetaskall John g/CS2103T task/TeamProject`                                                                |
| **Clear**                 | `clear`                                                                                                                                                     |
| **Exit**                  | `exit`                                                                                                                                                      |
