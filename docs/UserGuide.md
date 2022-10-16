---
layout: page
title: User Guide
---

IdENTify is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, idENTify can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

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


### Adding a person: `add`

Adds a patient into idENTify.

Format: `Format: add n/NAME p/PHONE_NUMBER a/ADDRESS [e/EMAIL] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 a/John street, block 123, #01-01`
* `add n/Betsy Crowe p/88888888 a/Newgate Prison t/e`

### Listing all patients/appointments : `list`

Shows a list of all patients or appointments, depending on the parameter given.

Format:
* `list patients`
* `list appts`


### Editing a patient : `edit patients`

Edits an existing patient in idENTify.

Format: `edit patients INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the patient at the specified `INDEX`. The index refers to the index number shown in the displayed patient list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the patient’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit patients 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
*  `edit patients 2 n/Betsy Crower t/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing tags.

### Editing an appointment: `edit appts`

Edits an existing patient's appointment in idENTify.

Format: `edit appts INDEX [r/REASON] [d/DATE] [pe/TIMEPERIOD]`

* Edits the appointment at the specified `INDEX`. The index refers to the index number shown in the displayed appointment list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples: 
* `edit appts 1 r/Cough d/2022-12-10 16:30` Edits the reason and date of the first appointment to be `Cough` and `2022-12-10 16:30` 
respectively. Existing time period will not be edited.
* `edit appts 1 pe/1Y2M` Edits the time period of the first appointment to be recurring every 1 year 2 months. Existing reason and date will not be edited.

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

Deletes the specified person from idENTify.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in idENTify.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Add an appointment:  `book`

Books an appointment for the specified patient at INDEX with a given REASON, DATE and an optional TIME PERIOD. 

Appointments added are sorted according to their date.

Format: `book INDEX r/REASON d/DATE [pe/TIME PERIOD]`

* The index refers to the index number shown in the displayed patient list.
* The index must be a positive integer 1, 2, 3, …​
* Dates should be inputted in a YYYY-MM-DD HH:MM format or HH:MM YYYY-MM-DD format.
* Input a time period for the appointment to be recurring, default time period is set to 0Y0M0D otherwise.
* Input at least a Y, M or D value for the time period. Values **must be in the range of** 0-10Y, 0-12M or 0-31D to be considered as valid.

Examples:
* `book 2 r/Ear Infection d/2022-12-31 18:00`
* `book 2 r/Ear Infection d/16:30 2022-12-31 pe/1M2D`
* `book 2 r/Ear Infection d/2022-12-31 13:00 pe/1Y`

### Mark an appointment as completed:  `mark`

Marks a specified appointment `APPOINTMENT_INDEX` for a specified patient at `PATIENT_INDEX` as completed.

If the specified appointment was set to be recurring, automatically books a new appointment that will occur after the time period specified.

Format: `mark PATIENT_INDEX APPOINTMENT_INDEX`

* `PATIENT_INDEX` refers to the index number of the patient shown in the displayed patient list.
* `APPOINTMENT_INDEX` refers to the index number of the appointment to be marked for the desired patient.
* Values of `PATIENT_INDEX` and `APPOINTMENT_INDEX` **must be a positive integer** 1, 2, 3, …​

Examples:
* `mark 3 1`

### Unmark an appointment as incomplete:  `unmark`

Unmarks a specified appointment `APPOINTMENT_INDEX` for a specified patient at `PATIENT_INDEX` as incomplete.

Format: `unmark PATIENT_INDEX APPOINTMENT_INDEX`

* `PATIENT_INDEX` refers to the index number of the patient shown in the displayed patient list.
* `APPOINTMENT_INDEX` refers to the index number of the appointment to be unmarked for the desired patient.
* Values of `PATIENT_INDEX` and `APPOINTMENT_INDEX` **must be a positive integer** 1, 2, 3, …​

Examples:
* `unmark 4 2`

### Clearing all entries : `clear`

Clears all entries from idENTify.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Cancel
Cancels a specified appointment from the specified patient’s appointment list. <br>
Format: ```cancel PATIENT_INDEX APPOINTMENT_INDEX```
- Deletes the appointment with `APPOINTMENT_INDEX` for the patient at the specified `PATIENT_INDEX`.
- The index refers to the index number shown in the displayed appointment list.
- The indexes must be a positive integer 1, 2, 3, …​
### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

IdENTify data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, idENTify will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER a/ADDRESS [e/EMAIL] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 a/123, Clementi Rd, 1234665`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Book** | `book INDEX r/REASON d/DATE [pe/TIME PERIOD]` <br> e.g., `book 2 r/Ear Infection d/2022-12-31 18:00 pe/1Y`
**Cancel** | `cancel PATIENT_INDEX APPOINTMENT_INDEX` <br> e.g., `cancel 3 2`
**Edit Patient** | `edit patients INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br> e.g., `edit patients 1 n/Bernice Yu`
**Edit Appointment** | `edit appts INDEX [r/REASON] [d/DATE] [pe/TIME PERIOD]` <br> e.g., `edit appts 1 r/Cough` 
**List** | `list patients` <br> `list appointments`
**Help** | `help`
**Exit** | `exit`
