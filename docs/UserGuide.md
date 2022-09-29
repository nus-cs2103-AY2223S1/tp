---
layout: page
title: User Guide
---

JARVIS is a **desktop app for a CS1101S Teaching Assistant to manage students and tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, JARVIS can help manage your class efficiently.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `JARVIS.jar` from [here](https://github.com/AY2223S1-CS2103T-T11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for JARVIS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`s/John Tan` : Adds a student named `John Tan` to JARVIS.

   * **`delete`**`s/3` : Deletes the 3rd student shown in the student list.

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


### Adding a student/task: `add`

Adds a student or task to JARVIS.

- Add a student
  - Format: `add s/NAME`
- Add a task with a deadline
  - Format: `add t/TASK_DESC [d/DATE]`
  - d/DATE is optional


Examples:
* `add s/John Tan`
* `add t/Consultation d/12-09-2023`

### Listing all students/tasks : `list`

Shows a list of all students or tasks stored.

- List students
    - Format: `list s`
- List tasks
    - Format: `list t`

### Editing a person : `edit`

Edits an existing person in the address book.

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

### Deleting a student / task : `delete`

Deletes the specified student or task from their respective lists.

* Delete a student at specified index
  - Format: `delete s/STUDENT_INDEX`
* Delete a task at specified index
  - Format: `delete t/TASK_INDEX`

**Things to Note:**
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list s` followed by `delete s/2` deletes the 2nd student in JARVIS.
* `list t` followed by `delete t/1` deletes the 1st task in JARVIS.

### Recording a student's mastery check result: `mc`

Records the mastery check result for the specified student.

* Recording a student's mastery check result
  - Format: `mc MC_NUM s/STUDENT_INDEX r/RESULT`

**Things to Note:**
* `MC_NUM` refers to the mastery check number (either 1 or 2).
* `STUDENT_INDEX` refers to the index number of the student shown in the displayed list.
* `RESULT` is either "PASS" or "FAIL"

Examples:
* `mc 1 s/1 r/PASS` marks the 1st student's result for MC1 as a "PASS".
* `mc 2 s/2 r/FAIL` marks the 2nd student's result for MC2 as a "FAIL".

### Clearing all entries : `clear`

Clears all student and task from JARVIS.

Format: `clear`

#### **Warning! This action is irreversible! Please think twice before you use this**

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

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
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous JARVIS home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add s/NAME`, `add t/TASK_DESC [d/DATE]` <br> e.g., `add s/John Tan`, `add t/Consultation d/12-09-2023`
**Clear** | `clear`
**Delete** | `delete s/STUDENT_INDEX` or `delete t/TASK_INDEX` <br> e.g., `delete s/3` or `delete t/2`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
