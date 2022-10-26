---
layout: page
title: User Guide
---

ModQuik is a desktop app for NUS CS Professors, optimised for use via a Command Line Interface(CLI). It allows the professors to keep track of their students, teaching assistants and timetable for their courses.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `modquik.jar` from [here](https://github.com/AY2223S1-CS2103T-W17-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ModQuik.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all students.

   * **`add student`**`n/John Doe i/A0232123X p/98765432 e/johnd@example.com h/john_fu m/CS2103 l/T23` : Adds a student named `John Doe` to CS2103T module.

   * **`delete student`**`3` : Deletes the 3rd student shown in the current list.

   * **`clear reminders`** : Deletes all reminders.

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
  e.g. if the command specifies `n/NAME p/PHONE`, `p/PHONE n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>
### Adding a student: `add student`

Adds a student to the specified module.

Format: `add student n/NAME i/STUDENT_ID p/PHONE e/EMAIL h/TELEGRAM_HANDLE m/MODULE l/TUTORIAL [g/GRADE] [a/ATTENDANCE] [c/PARTICIPATION] [t/TAG]…​`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

* The `ROLE` of the person can only be either student or TA.

Examples:
* `add student n/John Doe i/A0000000J p/98765432 e/johnd@example.com h/johnDoe m/CS2103T l/W17`
* `add student n/Betsy Crowe i/A0000000B t/friend e/betsycrowe@example.com p/91234567 h/betsy_crowe m/CS2105 l/G03 g/C a/3 c/1 t/criminal`

### Listing all students : `list student`

Shows a list of all students.

Format: `list`

### Editing a student : `edit student`

Edits an existing student in a specified module.

Format: `edit student INDEX [n/NAME] [m/MODULE] [p/PHONE] [e/EMAIL] [t/TAG]…`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit student 1 p/91234567 e/jameslee@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `jameslee@example.com` respectively.
*  `edit student 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.

### Locating students by name and other attributes : `find student`

Finds students by names, student ID, module or tutorial. by checking if respective attribute contains any of the given keywords.

Format: `find [n/NAME] [i/STUDENT_ID] [m/MODULE] [l/TUTORIAL]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a student : `delete student`

Deletes the specified student from the list of people.

Format: `delete student INDEX [m/MODULE]`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the list of people.
* `list m/CS2103T` followed by `delete 3` deletes the 3rd student in the CS2103T module.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Adding a tutorial : `add tutorial`

Adds a tutorial to the tutorial database.

Format: `add tutorial n/NAME m/MODULE v/VENUE T/TIMESLOT D/DAY`

Examples:
* `add tutorial n/T23 m/CS2103T v/COM1-0205 T/1800-2000 D/1`

### Deleting a tutorial : `delete tutorial`

Deletes a specified tutorial from the tutorial database.

Format: `delete tutorial INDEX`

* Deletes the tutorial at the specified `INDEX`.
* The index refers to the index number shown in the displayed tutorial list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete tutorial 3`

### Adding a consultation : `add consultation`

Adds a consultation to the consultation database.

Format: `add consultation n/NAME m/MODULE v/VENUE D/DATE T/TIMESLOT d/DESCRIPTION`

Examples:
* `add consultation n/JakeKim m/CS2103T D/2022-10-24 T/18:00-20:00 v/COM1-0205 d/past year papers`

### Deleting a consultation : `delete consultation`

Deletes a specified consultation from the consultation database.

Format: `delete consultation INDEX`

* Deletes the consultation at the specified `INDEX`.
* The index refers to the index number shown in the displayed consultation list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete consultation 3`

### Adding a reminder : `add reminder`

Adds a reminder to the list of reminders.

Format: `add reminder n/NAME T/DEADLINE p/PRIORITY d/DESCRIPTION`

### Deleting a reminder : `delete reminder`

Deletes the specified reminder from the list of reminders.

Format: `delete reminder INDEX`

* Deletes the reminder at the specified `INDEX`.
* The index refers to the index number shown in the displayed reminder list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete reminder 3`

### Mark a reminder : `mark reminder`

Marks a reminder as completed.

Format: `mark reminder INDEX`

Examples:
* `mark reminder 3`

### Unmark a reminder : `unmark reminder`

Unmarks a reminder as not completed.

Format: `unmark reminder INDEX`

Examples:
* `unmark reminder 3`
 
### Sort reminders by priority : `sort reminder`

Sort reminders by their priority.

Format: `sort reminder`

### Clearing all data: `clear`

Clears all data in a specific fields or the entire app.

Format: `clear f/FIELD`
* `FIELD` including `all`, `student`, `tutorial`, `consultation`, `reminder`

Examples:
* `clear all`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

ModQuik data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ModQuik data are saved as a JSON file `[JAR file location]/data/modquik.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ModQuik will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ModQuik home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Student** | `add student n/NAME i/STUDENT_ID p/PHONE e/EMAIL h/TELEGRAM_HANDLE m/MODULE l/TUTORIAL [t/TAG]…` <br> e.g., `add student n/John Doe i/A0000000J p/98765432 e/johnd@example.com h/johnDoe m/CS2103T l/W17`
**List All Students** | `list`<br> e.g., `list`
**Edit Student** | `edit student INDEX [n/NAME] [m/MODULE] [p/PHONE] [e/EMAIL] [t/TAG]…`<br> e.g.,`edit student 1 p/91234567 e/jameslee@example.com`
**Find Student** | `find [n/NAME] [i/STUDENT_ID] [m/MODULE] [l/TUTORIAL]`<br> e.g., `find student n/john m/CS2103T`
**Delete Student** | `delete student INDEX [m/MODULE]`<br> e.g., `delete student 2 m/CS2103T`
**Add Tutorial** | `add tutorial n/NAME m/MODULE v/VENUE T/TIMESLOT D/DAY` <br> e.g., `add tutorial n/T23 m/CS2103T v/COM1-0205 T/1800-2000 D/1`
**Delete Tutorial** | `delete tutorial INDEX` <br> e.g., `delete tutorial 3`
**Add Consultation** | `add consultation n/NAME m/MODULE v/VENUE D/DATE T/TIMESLOT d/DESCRIPTION` <br> e.g., `add consultation D/2022-10-24 t/18:00-20:00 v/COM1-0205 m/CS2103T n/JakeKim d/past year papers`
**Delete Consultation** | `delete consultation INDEX` <br> e.g., `delete consultation 3`
**Add Reminder** | `add reminder n/NAME T/DEADLINE p/PRIORITY d/DESCRIPTION` <br> e.g., `add reminder n/mark T/2022-03-21 p/HIGH d/300 papers to mark`
**Delete Reminder** | `delete reminder INDEX` <br> e.g., `delete reminder 3`
**Mark Reminder** | `mark reminder INDEX` <br> e.g., `mark reminder 3`
**Unmark Reminder** | `unmark reminder INDEX` <br> e.g., `unmark reminder 3`
**Clear** | `clear f/FIELD` <br> e.g., `clear student`
**Exit** | `exit`
**Help** | `help`
