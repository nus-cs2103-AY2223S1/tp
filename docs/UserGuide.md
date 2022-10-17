---
layout: page
title: User Guide
---

Teacher’s Address Book (TAB) is a **desktop app made for teachers for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TAB can get your contact management tasks done faster than traditional GUI apps.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TAB.jar` from [here](https://github.com/AY2223S1-CS2103T-T17-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAB.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com pos/Student a/John street, block 123, #01-01 t/CS2103T-T17` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

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
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/CS2103T-T12`, `t/CS2103T-T12 t/CS2103T-15` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Launches the user guide in a browser window.

![User Guide](images/userGuideWindow.png)

Format: `help`


### Adding a person: `add`

Adds a person to TAB.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL pos/POSITION a/ADDRESS [t/MODULE-GROUP]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Adds a new contact with the provided details. Required fields include name, phone number, email, and position. Tags representing the module and tutorial group a person is associated with are optional and must be in the format of module-tutorial group.

Examples:
* `add n/Alex Yeoh p/87438807 e/alexyeoh@example.com pos/Student a/Blk 30 Geylang Street 29, #06-40 t/CS2103T-T17`
* `add n/Betsy Crowe p/92498754 e/betsycrowe@example.com pos/TA a/Blk 30 Lorong 3 Serangoon Gardens, #07-18`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in TAB.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [pos/POSITION] [t/TAGS]`

Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Editing the attendance of a student : `attendance`

Edits the attendance of an existing student in TAB.

Format: `attendance INDEX attendance/ATTENDANCE`

Edits the attendance of the person (whose position must be student) at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
*  `attendance 1 attendance/10/13` edits the attendance of the 1st person to be `10/13`.

### Editing the grade of a student : `grade`

Edits the grade of an existing student in TAB.

Format: `grade INDEX grade/GRADE`

Edits the grade of the person (whose position must be student) at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
*  `grade 2 grade/68/80` edits the grade of the 2nd person to be `68/80`.

### Editing the availability of a TA : `avail`

Edits the availability of an existing TA in TAB.

Format: `avail INDEX avail/AVAILABILITY`

Edits the availability of the person (whose position must be TA) at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
*  `avail 1 avail/Available` edits the availability of the 1st person to be `Available`.

### Editing the roles of a Professor : `roles`

Edits the roles of an existing professor in TAB.

Format: `roles INDEX roles/ROLE1, ROLE2,...`

Edits the roles of the person (whose position must be professor) at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
*  Multiple roles may be added and must be separated by a comma.

Examples:
*  `roles 1 roles/Coordinator, Lecturer, Advisor` edits the roles of the 1st person to be `Coordinator, Lecturer, Advisor`.


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

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TAB data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAB data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TAB will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAB home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS pos/ POSITION [t/TAG]…​` <br> e.g., `add n/Alex Yeoh p/87438807 e/alexyeoh@example.com pos/Student a/Blk 30 Geylang Street 29, #06-40 t/CS2103T-T17`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [pos/POSITION] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Attendance** | `attendance INDEX attendance/ATTENDANCE` <br> e.g., `attendance 1 attendance/10/13`
**Grade** | `grade INDEX grade/GRADE` <br> e.g., `grade 2 grade/68/80`
**Availability** | `avail INDEX avail/AVAILABILITY` <br> e.g., `avail 1 avail/Available`
**Roles** | `roles INDEX roles/ROLE1, ROLE2,...` <br> e.g., `roles 1 roles/Coordinator, Lecturer, Advisor`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
