---
layout: page
title: User Guide
---

OmniHealth is a **desktop app for private clinic practitioners to quickly store, edit and search for patients and their records**. It is optimised for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 

If you can type fast, OmniHealth can get your patient management tasks done faster than traditional GUI apps! 💊✨
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `OmniHealth.jar` from [here](https://github.com/AY2223S1-CS2103T-T14-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for OmniHealth.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all patients.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a patient named `John Doe` to OmniHealth.

   * **`delete`**`3` : Deletes the 3rd patient shown in the current list.

   * **`clear`** : Deletes all patients.

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


### Adding a patient: `add`

Adds a patient to the patient record database.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Adding a record: `addR`

Adds a new record to a given patient.

Format: `addR INDEX d/DATE r/DESCRIPTION`

* Adds a new record to the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient records list.
* The index must be a positive integer 1, 2, 3, …​
* The record is created with given `DATE` and `DESCRIPTION` information.

Examples:
* `addR 1 d/2022-09-11 r/Patient tested negative for COVID-19`

### Deleting a patient: `delete`

Deletes the specified patient from the patient database.

Format: `delete INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient records list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the find command.

### Deleting a record: `deleteR`

Deletes the specified record from the currently viewed patient’s records.

Format: `deleteR RECORD_INDEX`

* The command is only valid after using `listR PATIENT_INDEX`.
* Deletes the record of the currently viewed patient at the specified `RECORD_INDEX`.
* The record index must be a positive integer 1, 2, 3, …​

Examples:
* `listR 1` then `deleteR 2` deletes the 2nd record from the 1st patient’s listed records.

### Listing all patients: `list`

Shows a list of all patients in the patient database.

Format: `list`

### Listing all record(s) for the specified patient : `listR`

Shows a list of all records for the specified patient.

Format: `listR PATIENT_INDEX`

* The command is only valid after using `list`.
* Lists all records of the patient at the specified `PATIENT_INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listR 1` displays all records of the 1st patient in the displayed patient list.
* `find Betsy` followed by `listR 1` displays all records of the 1st patient in the results of the find command.

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


### Locating records by keyword: `findR`

Finds records with a description that matches the keyword(s) specified in the records database. This command is only valid after using the listR command to list out the records for a specified patient.

Format: `findR KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g `hello` will `match HELLO`
* The order of the keywords does not matter. e.g. `Hello World` will match `World Hello`
* Only the description of the record will be searched.
* Only full words will be matched e.g. `Hell` will not match `Hello`.
* Records with a description matching at least one keyword will be returned (i.e. OR search). e.g. `Hello World` will return `Hello Hello`, `World World`.

Examples:
* `findR hello` returns `hello` and `Hello World`
* `findR foo bar` returns `foo bar`, `bar null`


### Clearing all entries : `clear`

Clears all patients from Omnihealth.

Format: `clear`

### Clearing all patient records : `clearR`

Clears all records of a specific patient from Omnihealth.

Format: `clearR`

### Adding an appointment : `appt`

Adds an appointment for a specified patient. This command is only valid in the patient list view.

Format: `appt INDEX d/DATE`
* Adds a new appointment for the patient under the specified `INDEX`.
* The index refers to the index number in the list of patients.
* The index must be positive and available in the list.
* The appointment is created with the given date.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Patient's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

OmniHealth's data are saved as a JSON file `[JAR file location]/data/patientlist.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, OmniHealth will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous OmniHealth home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action            | Format, Examples                                                                                                                                                      |
|-------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Patient**   | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Add Record**    | `addR INDEX d/DATE r/DESCRIPTION` <br> e.g., `e.g., addR 1 d/2022-09-11 r/Patient tested negative for COVID-19`                                                       |
| **Clear**         | `clear`                                                                                                                                                               |
| **Clear Record**  | `clearR`                                                                                                                                                              |
| **Delete**        | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Delete Record** | `deleteR INDEX`                                                                                                                                                       |
| **Edit**          | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**          | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**          | `list`                                                                                                                                                                |
| **Help**          | `help`                                                                                                                                                                |
