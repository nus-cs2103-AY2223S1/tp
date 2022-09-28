# HR Pro Max++

HR Pro Max++ is a **desktop app for team leads in SME to manage projects and staff members under them. It is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
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

   * **`add`**`n/Duke /b 20k /d 2022-09-27 /c Darmith : Adds a project named `Duke` to HR Pro Max++.

   * **`delete`**`3` : Deletes the 3rd project shown in the current list.

   * **`clear`** : Deletes all projects currently saved on HR Pro Max++.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

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

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a Project: `add`

Adds a project to Project list.

Format: `add n/NAME b/Project Budget c/Client d/Deadline [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A project can have any number of tags (including 0)
</div>

Examples:
* `add n/Duke b/100000 c/Max d/2022-11-10`
* `add n/2103T_TP t/fun time c/Darren t/Tiring b/100 d/2022-09-29`

### Adding members to projects : `addstaff`

Adds staff members to a project in Project list.

Format: `addStaff INDEX sn/NAME si/INSURANCE_STATUS sd/DEPARTMENT st/TITLE 
sc/CONTACT NUMBER` 

* Adds a staff member at the specified `INDEX`. The index refers to the index number shown in the displayed Project list. The index **must be a positive integer** 1, 2, 3, …​
* All fields for staff member are required
* 

Examples:
* `addStaff 1 sn/John Doe sp/98765432 si/true sd/Accounting st/Accountant` Adds staff member named `John Doe` to the project 1 in the current Project list.
* `addStaff 3 sn/Betsy Crowe sp/1234567 st/Admin Staff sd/Admin si/false` Adds staff member named `Betsy Crown` to the project 3 in the current Project list.


### Listing all projects : `list`

Shows a list of all projects in HR Pro Max++.

Format: `list`

### Editing a Project : `edit`

Edits an existing Project in the Project list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a Project : `delete`

Deletes the specified Project from Project list.

Format: `delete INDEX`

* Deletes the project at the specified `INDEX`.
* The index refers to the index number shown in the displayed Project list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd project in Project list.
* `find Betsy` followed by `delete 1` deletes the 1st project in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HR Pro Max++ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action        | Format, Examples                                                                                                                                                     |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**       | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Add staff** | `addstaff`                                                                                                                                                             |
| **Clear**     | `clear`                                                                                                                                                              |
| **Delete**    | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                  |
| **Edit**      | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                          |
| **Find**      | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                           |
| **List**      | `list`                                                                                                                                                               |
| **Help**      | `help`                                                                                                                                                               |

## Prefix Summary
Prefixes with singular letter are meant for project details. 

Prefixes that are starting with `s` and not singular letter is for project member details.

| Prefix  |  Meaning   | Format/ Examples     |
|---------|------------|----------------------|
| **n/**  |            |                      |
| **b/**  |            |                      |
| **d/**  |            |                      |
| **c/**  |            |                      |
| **t/**  |            |                      |
| **sn/** |            |                      |
| **si/** |            |                      |
| **sd/** |            |                      |
| **st/** |            |                      |
| **sc/** |            |                      |
