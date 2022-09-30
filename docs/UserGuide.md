---
layout: page
title: User Guide
---
UniNurse is a **desktop app** designed for **private nurses to manage their patient contacts, optimized for use via a
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type
fast, UniNurse can get your patient management tasks done faster than traditional GUI apps.

### Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start
_To be updated ..._

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a patient: `addPatient`

Adds a patient to the patient list.

Format: `addPatient n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/TASK_DESCRIPTION…`

Examples:
* `addPatient n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/Administer 3ml of example medicine`
* `addPatient n/Betsy Crowe p/87901234 e/betsy@example.com a/Jane street blk 420 #01-69 d/Change dressing on left arm`

### Editing a patient’s details : `editPatient`

Edits an existing patient in the patient list.

Format: `editPatient INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]`

* Edits the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index ***must be a positive integer*** 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the patient will be removed i.e adding of tags is not cumulative.
* You can remove all the patient’s tags by typing t/ without specifying any tags after it.

Example:

* `editPatient 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
* `editPatient 2 n/Betsy Crower t/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing tags.

### Listing all patients: `list`

Shows a list of all patients.

Format: `list`

### Finding patient by name: `find`

Finds patients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Patients matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:
* `find jo` returns `Joe` and `John`.
* `find alex david` returns `Alex Tan` & `David Ho`.
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a patient: `deletePatient`

Deletes the specified patient from the patient list.

Format: `deletePatient INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the patient book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding a task: `addTask`

Edits the specified task associated with a patient.

Format: `addTask PATIENT_INDEX [d/TASK_DESCRIPTION]`

* Adds a task to a patient at the specified `PATIENT_INDEX`.
* The patient index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `addTask 1 d/Administer 3ml of example medicine` adds a task to the 1st person in the patient list.
* `find Betsy` followed by `addTask 2 d/Change dressing on left arm` adds a task to the 2nd person in results of the `find` command.

### Editing a task: `editTask`

Edits the specified task associated with a patient.

Format: `editTask PATIENT_INDEX TASK_INDEX [d/TASK_DESCRIPTION]`

* Edits the task at the specified `TASK_INDEX` of the patient at the specified `PATIENT_INDEX`.
* The task index refers to the index number shown in the task list of a patient.
* The patient index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `editTask 1 1 d/Administer 3ml of example medicine` edits the description of the 1st task of the 1st person in the patient list.
* `find Betsy` followed by `editTask 2 3 d/Change dressing on left arm` edits the description of the 3rd task of the 2nd person in results of the `find` command.

### Deleting a task: `deleteTask`

Deletes the specified task associated with a patient.

Format: `deleteTask PATIENT_INDEX TASK_INDEX`

* Deletes the task at the specified `TASK_INDEX` of the patient at the specified `PATIENT_INDEX`.
* The task index refers to the index number shown in the task list of a patient.
* The patient index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `deleteTask 2 3` deletes the 3rd task of the 2nd person in the patient list.
* `find Betsy` followed by `deleteTask 1 2` deletes the 2nd task of the 1st person in results of the find command.

### Listing all tasks: `listTask`

Shows a list of all tasks to be completed.

Format: `listTask`

Examples:

Suppose the following patients were added.

`addPatient n/John Doe d/Administer 3ml of example medicine`

`addPatient n/Betsy Crowe d/Change dressing on left arm`
* `listTask` will display:
  * `Administer 3ml of example medicine FOR John Doe`
  * `Change dressing on left arm FOR Betsy Crowe`
  
### View all tasks associated with a patient: `viewTask`

Shows all the tasks that are associated with the specified patient.

Format: `viewTask INDEX`

Examples:

Suppose the following patients were added.

`addPatient n/John Doe d/Administer 3ml of example medicine`

`addPatient n/Betsy Crowe d/Change dressing on left arm`
* `viewTask 1` will display:
  * `Administer 3ml of example medicine`
* `viewTask 2` will display:
  * `Change dressing on left arm`

### Clearing all entries: `clear`

Clears all patient entries.

Format: `clear`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

UniNurse data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Editing the data file

_[To update name of json file]_

UniNurse data are saved as a JSON file `[JAR file location]/data/addressbook.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, UniNurse will discard all data and start with an empty data file at the next run.
</div>

### Adding recurring tasks `[coming in v1.3]`
_Details coming soon ..._

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                          | Format                                                                           |
|---------------------------------|----------------------------------------------------------------------------------|
| **Help**                        | `help`                                                                           |
| **Add patient**                 | `addPatient n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/TASK_DESCRIPTION [t/TAG]…` |
| **Edit patient**                | `editPatient INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…`     |
| **Delete patient**              | `deletePatient INDEX`                                                            |
| **List all patients**           | `list`                                                                           |
| **Find patient**                | `find KEYWORD [MORE_KEYWORDS]`                                                   |
| **Add task**                    | `addTask PATIENT_INDEX [d/TASK_DESCRIPTION]`                                     |
| **Edit task**                   | `editTask PATIENT_INDEX TASK_INDEX [d/TASK_DESCRIPTION]`                         |
| **Delete task**                 | `deleteTask PATIENT_INDEX TASK_INDEX`                                            |
| **List all tasks**              | `listTask`                                                                       |
| **View all tasks of a patient** | `viewTask INDEX`                                                                 |
| **Clear all patients**          | `clear`                                                                          |
| **Exit**                        | `exit`                                                                           |

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous UniNurse home folder.
