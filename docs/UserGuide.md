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
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/nose` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/ear`, `t/nose t/throat` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
* Tag names must only be from the following: `ear`, `nose`, `throat` (not case-sensitive). <br>
  e.g. `t/sick` will cause an error message.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a patient: `add`

Adds a patient into idENTify.

Format: `Format: add n/NAME p/PHONE_NUMBER [a/ADDRESS] [e/EMAIL] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A patient can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 a/John street, block 123, #01-01`
* `add n/Betsy Crowe p/88888888 a/Newgate Prison t/ear`

### Listing all patients/appointments : `list`

Shows a list of all patients or appointments, depending on the parameter given. \
If it is a patient list, then patients will be sorted by their names first; if there are people with the same name, they will be sorted by their 
phone numbers. \
If it is an appointment list, then appointments will be sorted by their datetime first; if there are appointments 
with the same datetime, they will be sorted by their attached patients' information.

Format:
* `list patients` - Refreshes only the patient list to show all patients, leaving the appointment list unchanged.
* `list appts` - Refreshes only the appointment list to show all appointments, leaving the patient list unchanged.
* `list all` - Refreshes both the patient and appointment lists to show all patients and appointments.

### Grouping all patients : `group patients`

Shows a list of all patients grouped by their tags.

Format:
* `group patients`

### Grouping all appointments : `group appts`

Shows a list of all appointments grouped by their tags or attached patients, depending on the parameter given.

Format:
* `group appts k/[KEY]`

Examples:
* `group appts k/tag`
* `group appts k/patient`
* `group appts k/mark`

### Ungrouping all patients/ appointments : `ungroup`

Shows a list of all patients or appointments ungrouped 

Format:
* `ungroup patients`
* `ungroup appts`

### Editing a patient : `edit patients`

Edits an existing patient in idENTify.

Format: `edit patients INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the patient at the specified `INDEX`. The index refers to the index number shown in the displayed patient list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the patient will be removed i.e. adding of tags is not cumulative.
* You can remove all the patient’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit patients 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
*  `edit patients 2 n/Betsy Crower t/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing tags.

### Editing an appointment: `edit appts`

Edits an existing patient's appointment in idENTify.

Format: `edit appts INDEX [r/REASON] [d/DATE] [pe/TIMEPERIOD] [t/TAG]…​`

* Edits the appointment at the specified `INDEX`. The index refers to the index number shown in the displayed appointment list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the appointment will be removed i.e. adding of tags is not cumulative.
* You can remove all the appointment’s tags by typing `t/` without
  specifying any tags after it. <br>
  e.g. `edit appts  1 t/`

Examples:
* `edit appts 1 r/Cough d/2022-12-10 16:30` Edits the reason and date of the first appointment to be `Cough` and `2022-12-10 16:30`
respectively. Existing time period will not be edited.
* `edit appts 1 pe/1Y2M` Edits the time period of the first appointment to be recurring every 1 year 2 months. Existing reason and date will not be edited.


### Hiding patients by name or tag: `hide patients`

By name:
Filters out (hides) patients whose names contain any of the given keywords.

Format: `hide patients n/NAME [MORE_NAMES]`

Examples:
* `hide patients n/John` hides `john` and `John Doe`
* `hide patients n/alex david` hides `Alex Yeoh`, `David Li`<br>

By tag:

Hides patients whose names contain any of the given tags.

Format: `hide patients t/TAG [MORE_TAGS]`

* The search is case-insensitive. e.g. `EAR` will match `ear`
* All tags of a patient are searched.

Examples:
* `hide patients t/ear nose` hides all patients with a ear OR nose tag.

### Unhiding patients by name or tag: `unhide patients`

By name:
Shows (unhides) patients that were previously hidden whose names contain any of the given keywords.

Format: `unhide patients n/NAME [MORE_NAMES]`

Examples:
* `unhide patients n/John` unhides `john` and `John Doe`
* `unhide patients n/alex david` unhides `Alex Yeoh`, `David Li`<br>

By tag:

Unhides patients that were previously hidden whose names contain any of the given tags.

Format: `unhide patients t/TAG [MORE_TAGS]`

* The search is case-insensitive. e.g `EAR` will match `ear`
* All tags of a patient are searched.

Examples:
* `unhide patients t/nose ear` unhides all patients with a nose OR ear tag.

### Hiding appointments by reason, tag, or marked status: `hide appts`

By reason:
Filters out (hides) appointments that has a reason that matches the given keyword.

Format: `hide appts r/REASON [MORE_REASONS]`

Examples:
* `hide appts r/nose` hides all appointments with "nose" in the reason.

By tag:

Hides appointments which contain any of the given tags.

Format: `hide appts t/TAG [MORE_TAGS]`

* The search is case-insensitive. e.g `EAR` will match `ear`
* All tags of an appointment are searched.

Examples:
* `hide appts t/ear nose` hides all appointments with a ear OR nose tag.

By marked/unmarked status:
Hides appointments which are marked/unmarked.

Format: `hide appts s/STATUS` <br>
Shortform: `marked` status can be shortened to `m`, `unmarked` can be shortened to `um`.

Examples:
* `hide appts s/marked` hides all appointments that has been marked.
* `hide appts s/um` hides all appointments that has been ummarked.

### Unhiding appointments by reason, tag or status: `unhide appts`

By reason:
Shows (unhides) appointments that were previously hidden whose reason contain any of the given keywords.

Format: `unhide appts r/reason [MORE_REASONS]`

Examples:
* `unhide appts r/pain` unhides all appointments with "pain" as part of the reason.

By tag:

Unhides appointments that were previously hidden which contain any of the given tags.

Format: `unhide appts t/TAG [MORE_TAGS]`

* The search is case-insensitive. e.g `EAR` will match `ear`
* All tags of an appointment are searched.

Examples:
* `unhide appts t/nose ear` unhides all appointments with a nose OR ear tag.

By marked/unmarked status:
Unhides appointments that were previously hidden which are marked/unmarked.

Format: `unhide appts s/marked` or `unhide appts s/m` <br>
Alternative: `unhide appts s/unmarked` or `unhide appts s/um`

Examples:
* `unhide appts s/marked` unhides all appointments that has been marked.

### Find results that satisify an input criteria: `find`
Finds patients and appointments that matches all the given criteria specified.

Format: `find [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/PATIENT_TAG]…​ [r/REASON] [ds/DATE_START] [de/DATE_END] [ta/APPOINTMENT_TAG]…​`

* At least 1 of the optional fields must be provided.
* The search is case-insensitive. e.g `hans` will match `Hans`
* `[n/NAME]`, `[p/PHONE]`, `[e/EMAIL]`, `[a/ADDRESS]` and `[t/PATIENT_TAG]` are fields to find information about the patient (patient criteria).
* `[r/REASON]`, `[ds/DATE_START]`, `[de/DATE_END]` and `[ta/APPOINTMENT_TAG]` are fields to find information about appointments (appointment criteria).
  * `[ds/DATE_START]` searches for appointments occurring at or after `DATE_START`.
  * `[ds/DATE_END]` searches for appointments occurring at or before `DATE_END`.
* Only patients and appointments that satisifies all criteria will be displayed.
  * A patient must satisify all patient criteria and have at least 1 appointment that satisifies all the appointment criteria to be displayed.
  * An appointment must satisify all appointment criteria and belong to a patient that satisifes all the patient criteria to be displayed.
* All fields except `[ds/DATE_START]`, `[de/DATE_END]`, `[t/PATIENT_TAG]` and `[ta/APPOINTMENT_TAG]` supports partial match.
  * e.g When finding names, searching `John Do` will match someone named `John Doe`.
* For `[t/PATIENT_TAG]` and `[ta/APPOINTMENT_TAG]` fields, only tags with a full match will be matched
  * e.g Finding a tag `Ea` will not match a tag labelled `Ear`.
* `[ds/DATE_START]` must be a date equal to or before `[ds/DATE_END]`.

Examples:
* `find n/John p/12345` displays all patients with `John` in their names and `12345` in their phone numbers, as well as all their appointments.
* `find ds/2020-01-01 00:00` displays all appointments occuring at or after 1st of January 2020. It will also display all patients with at least one of said appointments.
* `find a/Clementi r/Sleep Apena` displays all patients whose address contains `Clementi` and has at least 1 appointment containing `Sleep Apena` as its reason. It will also only display appointments containing `Sleep Apena` of said patients.
* `find ta/Throat ta/Nose` displays all appointments with both `Throat` and `Nose` tags, and all patients with at least one of said appointments.
* `find t/throat` returns `Bernice Yu` and `David Li`, both of which contains the `Throat` tag. <br>
  ![result for 'find t/throat'](images/FindThroatTagResult.png)

### Deleting a patient : `delete`

Deletes a patient or a range of patients from idENTify.

Format: `delete INDEX [END_INDEX]`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list patients` followed by `delete 2` deletes the 2nd patient in idENTify.
* `delete 1 3` deletes the first 3 patients (index 1 to 3 inclusive) in idENTify
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the `find` command.

### Add an appointment:  `book`

Books an appointment for the specified patient at INDEX with a given REASON, DATE and an optional TIME PERIOD.

Appointments added are sorted according to their date.

Format: `book INDEX r/REASON d/DATE [pe/TIME PERIOD] [t/TAG]…​`

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

Marks a specified appointment in the appointment list as complete.

If the specified appointment was set to be recurring, automatically books a new appointment in the future as given by the recurring time period of the appointment.

Format: `mark APPOINTMENT_INDEX`

* `APPOINTMENT_INDEX` refers to the index number of the appointment to be marked, as shown in the appointment list.
* `APPOINTMENT_INDEX` **must be a positive integer** 1, 2, 3, …​

Examples:
* `mark 3`

### Unmark an appointment as incomplete:  `unmark`

Unmarks a specified appointment in the appointment list as incomplete.

Format: `unmark APPOINTMENT_INDEX`

* `APPOINTMENT_INDEX` refers to the index number of the appointment to be unmarked, as shown in the appointment list.
* `APPOINTMENT_INDEX` **must be a positive integer** 1, 2, 3, …​

Examples:
* `unmark 1`

### Cancel an appointment: `cancel`
Cancels a specified appointment in the appointment list. <br>
Format: ```cancel APPOINTMENT_INDEX```
- Deletes the appointment with `APPOINTMENT_INDEX` in the appointment list.
- The index refers to the index number shown in the displayed appointment list.
- The index must be a positive integer 1, 2, 3, …​

Examples:
* `cancel 1`

### View History/Cycling of Commands

Shows the most recent 10 commands that was inputted.
Allows cycling through those commands to reduce the need of retyping similar commands fully.

Controls: 
* `Up Arrow` : Cycle to next command
* `Down Arrow`: Cycle to previous command
* `Left Arrow`: Display History of commands
* `Right Arrow`: Close History

### Clearing all entries : `clear`

Clears all entries from idENTify.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

idENTify data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, idENTify will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER a/ADDRESS [e/EMAIL] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 a/123, Clementi Rd, 1234665`
**Clear** | `clear`
**Delete** | `delete INDEX [END_INDEX]`<br> e.g., `delete 3`
**Find** | `find [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/PATIENT_TAG]…​ [r/REASON] [ds/DATE_START] [de/DATE_END] [ta/APPOINTMENT_TAG]…​`<br> e.g., `find n/Joshua e/Josh@example.com r/Tinnitus`
**Book** | `book INDEX r/REASON d/DATE [pe/TIME PERIOD] [t/TAG]…​` <br> e.g., `book 2 r/Ear Infection d/2022-12-31 18:00 pe/1Y`
**Group Patients** | `group patients`
**Group Appointents** | `group appts [k/KEY]` <br> e.g., `group appts k/mark`
**Ungroup** | `ungroup patients` <br> `ungroup appts`
**Mark** | `mark APPOINTMENT_INDEX` <br> e.g. `mark 3`
**Unmark** | `unmark APPOINTMENT_INDEX` <br> e.g. `unmark 1`
**Cancel** | `cancel APPOINTMENT_INDEX` <br> e.g., `cancel 2`
**Edit Patient** | `edit patients INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br> e.g., `edit patients 1 n/Bernice Yu`
**Edit Appointment** | `edit appts INDEX [r/REASON] [d/DATE] [pe/TIME PERIOD] [t/TAG]…​` <br> e.g., `edit appts 1 r/Cough`
**List** | `list patients` <br> `list appts` <br> `list all`
**Help** | `help`
**Exit** | `exit`
