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

   * **`rlist 1`** : Lists all the records of the 1st patient shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/DustAllergy` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/DustAllergy`, `t/DustAllergy t/PollenAllergy` etc.

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

### Adding a patient: `add`

Adds a patient to the patient record database.

Format: `add n/NAME b/BIRTHDATE p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

* The command can only be called when the patient addressbook is displayed (after using `list`).
* Patient's name entered in is case-insensitive.
* Duplicate patients are not allowed.
  * Patients are considered duplicate when they have the same `NAME` and `ADDRESS`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A patient can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe b/08-08-1988 p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe b/16-06-1996 e/betsycrowe@example.com a/Ang Mo Kio Ave 5, block 13, #02-033 p/1234567 t/PollenAllergy`

### Adding a record: `radd`

Adds a new record to a given patient.

Format: `radd d/RECORD_DATE r/RECORD_DATA [m/MEDICATION]…` 


* The command can only be called when a patient's record list is displayed (after using `rlist PATIENT_INDEX`).
* Adds a new record to the patient with given `RECORD_DATE`, `RECORD_DATA` and `MEDICATION` information.
  * `RECORD_DATA` field cannot be empty.
  
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Dates must be given in DD-MM-YYYY HHmm format!
</div>

Examples:
* `radd d/11-09-2001 1200 r/Patient tested negative for COVID-19 m/Paracetomol 500mg m/Dextromethorphan`
* `radd d/28-02-2024 2030 r/Patient experienced vomitting`

### Deleting a patient: `delete`

Deletes the specified patient from the patient database.

Format: `delete INDEX`

* The command can only be called when the patient addressbook is displayed (after using `list`).
* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient records list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the find command.

### Deleting a record: `rdelete`

Deletes the specified record from the currently viewed patient’s records.

Format: `rdelete RECORD_INDEX`

* The command can only be called when a patient's record list is displayed (after using `rlist PATIENT_INDEX`).
* Deletes the record of the currently viewed patient at the specified `RECORD_INDEX`.
* The record index must be a positive integer 1, 2, 3, …​

Examples:
* `rlist 1` then `rdelete 2` deletes the 2nd record from the 1st patient’s listed records.

### Listing all patients: `list`

Shows a list of all patients in the patient database.
* Not to be confused with the `showall` command.

Format: `list`
* To be used only when the list of records is displayed, in order to return to the main display.

### Listing all record(s) for the specified patient : `rlist`

Shows a list of all records for the specified patient.

Format: `rlist PATIENT_INDEX`

* The command can only be called when the patient addressbook is displayed (after using `list`).
* Lists all records of the patient at the specified `PATIENT_INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `rlist 1` displays all records of the 1st patient in the displayed patient list.
* `find Betsy` followed by `rlist 1` displays all records of the 1st patient in the results of the find command.

### Editing a patient : `edit`

Edits an existing patient in the address book.

Format: `edit INDEX [n/NAME] [b/BIRTHDATE] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* * The command can only be called when the patient addressbook is displayed (after using `list`).
* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided 
  * if the same field is specified more than once, only the latest instance of the field is used.
  * eg. edit 1 n/Johnny n/Becky -> Person's name is edited to Becky.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the patient will be removed i.e adding of tags is not cumulative.
* You can remove all the patient’s tags by typing `t/` without
    specifying any tags after it.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Birthdate must be given in DD-MM-YYYY HHmm format!
</div>

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing tags.

### Editing a record : `redit`

Edits an existing record in the record list.

Format: `redit INDEX [d/RECORD_DATE] [r/RECORD_DATA] [m/MEDICATION]…​`

* The command can only be called when a patient's record list is displayed (after using `rlist PATIENT_INDEX`).
* Edits the record at the specified `INDEX`. The index refers to the index number shown in the displayed record list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
  * if the same field is specified more than once, only the latest instance of the field is used.
  * eg. redit 1 r/Cold r/Flu -> Record's data is edited to Flu.
* Existing values will be updated to the input values.
* When editing medication, the existing medication in the record will be removed i.e adding of tags is not cumulative.
* You can remove all the specific record’s medications by typing `t/` without
  specifying any tags after it.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Record date must be given in DD-MM-YYYY HHmm format!
</div>

Examples:
*  `redit 1 d/12-12-2012 1200` Edits the date of the 1st record to be `12-12-2012 1200`.
*  `redit 2 r/Fever m/` Edits the data of the 2nd record to be `Fever` and clears all existing medication.

### Locating patients by name: `find`

Finds patients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The command can only be called when the patient addressbook is displayed (after using `list`).
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


### Locating records by keyword: `rfind`

Find records with a description that matches the keyword(s) specified in the records database. This command is only valid after using the rlist command to list out the records for a specified patient.

Format: `rfind [d/DATA] [r/DATA] [m/MEDICATION]`
* 
* * The command can only be called when a patient's record list is displayed (after using `rlist PATIENT_INDEX`).
* The search is case-insensitive. e.g `h1n1` will match `H1N1`
* The order of the keywords does not matter. e.g. `r/Has SARS` will match `SARS Has` stored in record data
* Only full words will be matched e.g. `Covid` will not match `Covid-19`.
* The order that the fields are specified does not matter. eg. `r/SARS m/Panadol` is equivalent to `m/Panadol r/SARS`
* Records with fields matching at least one keyword will be returned (i.e. OR search **within** the specified prefix). 
  * e.g. `r/Hello World` will return records with data of `Hello Hello`, `World World`.
* Records with that matches at least one keyword from each field specified will be returned (i.e. AND search **between** all specified prefixes)
  * eg. `r/Hello` `m/cold`will return only records that match both `Hello` in record data and `cold` in record medication.

Examples:
* `rfind r/hello` returns a record with `hello` and `Hello World` stored in its record data field.
* `rfind r/cold d/10-2022` returns a record that matches both `cold` in record data field, and has a date within Oct 2022.
### Displaying the full list : `showall`

Displays the full list of patients when the patient list is displayed, or
the full list of records when the record list is displayed.
* Not to be confused with `list` command.

Format: `showall`

### Clearing all entries : `clear`

Clears all patients from Omnihealth.

* The command can only be called when the patient addressbook is displayed (after using `list`).

Format: `clear`

### Clearing all patient records : `rclear`

Clears all records of a specific patient from Omnihealth.

* The command can only be called when a patient's record list is displayed (after using `rlist PATIENT_INDEX`).

Format: `rclear`

### Adding an appointment : `appt`

Adds an appointment for a specified patient. This command is only valid in the patient list view.

Format: `appt INDEX d/DATE`

* The command can only be called when the patient addressbook is displayed (after using `list`).
* Adds a new appointment for the patient under the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​
* The appointment is created with the given date.
* If a pre-existing appointment is present, it will be overridden by the new appointment.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Appointment dates must be given in DD-MM-YYYY HHmm format!
</div>

### Clearing an appointment: `apptcl`

Clears the appointment for a specified patient. This command is only valid in the patient list view.

Format: `apptcl INDEX`

* The command can only be called when the patient addressbook is displayed (after using `list`).
* Clears any appointment under the patient with the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

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

| Action                | Format, Examples                                                                                                                                               |
|-----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Patient**       | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/DustAllergy` |
| **Add Record**        | `radd INDEX d/DATE r/DESCRIPTION` <br> e.g., `e.g., radd 1 d/2022-09-11 r/Patient tested negative for COVID-19`                                                |
| **Clear**             | `clear`                                                                                                                                                        |
| **Clear Records**     | `rclear`                                                                                                                                                       |
| **Delete**            | `delete INDEX`<br> e.g., `delete 3`                                                                                                                            |
| **Delete Record**     | `rdelete INDEX`                                                                                                                                                |
| **Edit**              | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                    |
| **Edit Record**       | `redit INDEX [d/DATE] [r/DATA] [m/MEDICATION]…​`<br> e.g.,`redit 2 d/12-12-2012 1200 r/Fever`                                                                  |
| **Find**              | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                     |
| **Find Record**       | `rfind KEYWORD [MORE_KEYWORDS]`<br> e.g., `rfind foo bar`                                                                                                      |
| **List**              | `list`                                                                                                                                                         |
| **List Records**      | `rlist`                                                                                                                                                        |
| **Show Unfiltered List** | `showall`                                                                                                                                                   |
| **Add Appointment**   | `appt INDEX [d/DATE]` <br> e.g., `appt 1 d/01-01-2000 1230`                                                                                                    |
| **Clear Appointment** | `apptcl`                                                                                                                                                       |
| **Help**              | `help`                                                                                                                                                         |

