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

   * **`list student m/CS2101`** : Lists all students in CS2101 module.

   * **`add`**`n/John Doe r/student m/CS2105 p/98765432 e/johnd@example.com` : Adds a student named `John Doe` to CS2103T module.

   * **`delete person`**`3` : Deletes the 3rd person shown in the current list.

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
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Add module : `add mod`

Adds a module into list of modules.

Format: `add module m/MODULE`

### Listing all modules : `list mod`

Shows the list of modules that the user is in charge of.

Format: `list mod m/MODULE`

### Delete module : `delete mod`

Deletes a module from the list of modules.

Format: `delete mod m/MODULE`

### Adding a person: `add person`

Adds a person to the specified module.

Format: `add m/MODULE r/ROLE n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

* The `ROLE` of the person can only be either student or TA.

Examples:
* `add m/CS2103T n/John Doe r/TA p/98765432 e/johnd@example.com`
* `add m/CS2105 n/Betsy Crowe t/friend e/betsycrowe@example.com p/1234567 t/criminal`

### Listing all persons : `list person`

Shows a list of all persons in a specified module.

Format: `list person m/MODULE [r/ROLE]`

### Editing a person : `edit person`

Edits an existing person in a specified module.

Format: `edit INDEX [n/NAME] [m/MODULE] [p/PHONE] [e/EMAIL] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find person`

Finds students or TAs whose names contain any of the given keywords.

Format: `find KEYWORD [m/MODULE] [r/ROLE] [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a person : `delete person`

Deletes the specified person from the list of people.

Format: `delete person INDEX [m/MODULE] [r/ROLE]`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the list of people.
* `list m/CS2103T` followed by `delete 3` deletes the 3rd person in the CS2103T module.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding a tutorial : `add tut`

Adds a tutorial to the tutorial database.

Format: `add tutorial m/MODULE t/TIMESLOT v/VENUE n/NAME`

### Deleting a tutorial : `delete tut`

Deletes a specified tutorial from the tutorial database.

Format: `delete tutorial INDEX`

* Deletes the tutorial at the specified `INDEX`.
* The index refers to the index number shown in the displayed tutorial list.
* The index **must be a positive integer** 1, 2, 3, …​

### Clearing all tutorials : `clear tut`

Clears all tutorials in a specified module.

Format: `clear tut [m/MODULE]`

### Adding a consultation : `add consult`

Adds a consultation to the consultation database.

Format: `add consult t/TIMESLOT v/VENUE [m/MODULE] [n/NAME] [d/DESCRIPTION]`

### Deleting a consultation : `delete consult`

Deletes a specified consultation from the consultation database.

Format: `delete consult INDEX`

* Deletes the consultation at the specified `INDEX`.
* The index refers to the index number shown in the displayed consultation list.
* The index **must be a positive integer** 1, 2, 3, …​

### Clearing all consultations : `clear consult`

Clears all consultations.

Format: `clear consult`

### Adding a reminder : `add rem`

Adds a reminder to the list of reminders.

Format: `add reminder r/DESCRIPTION [d/DEADLINE]`

### Deleting a reminder : `delete rem`

Deletes the specified reminder from the list of reminders.

Format: `delete reminder INDEX`

* Deletes the reminder at the specified `INDEX`.
* The index refers to the index number shown in the displayed reminder list.
* The index **must be a positive integer** 1, 2, 3, …​

### Clearing all reminders : `clear rem`

Clears all reminders.

Format: `clear reminder`

### Exiting the program : `exit`

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
**Add Person** | `add m/MODULE r/ROLE n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​` <br> e.g., `add n/James Ho m/CS2102 r/student p/22224444 e/jamesho@example.com t/friend t/colleague`
**List Person** | `list person m/MODULE [r/ROLE]`<br> e.g., `list person m/CS2103T`
**Edit Person** | `edit INDEX [n/NAME] [m/MODULE] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find Person** | `find person KEYWORD [m/MODULE] [r/ROLE] [MORE_KEYWORDS]`<br> e.g., `find person john m/CS2103T`
**Delete Person** | `delete person INDEX [m/MODULE] [r/ROLE]`<br> e.g., `delete person 2 m/CS2103T r/ta`
**Add Tutorial** | `add tut m/MODULE t/TIMESLOT v/VENUE n/NAME` <br> e.g., `add tut m/CS2103T t/1800-2000 v/COM1-0205 n/JohnFoo`
**Delete Tutorial** | `delete tut INDEX` <br> e.g., `delete tut 3`
**Clear Tutorial** | `clear tut [m/MODULE]` <br> e.g., `clear tut m/CS2105`
**Add Consultations** | `add consult t/TIMESLOT v/VENUE [m/MODULE] [n/NAME] [d/DESCRIPTION]` <br> e.g., `add consult t/18:00-20:00 v/COM1-0205 m/CS2103T n/JakeKim d/testing`
**Delete Consultations** | `delete consult INDEX` <br> e.g., `delete consult 3`
**Clear Consultations** | `clear consult` <br> e.g., `clear consult`
**Add Reminders** | `add rem r/DESCRIPTION [d/DEADLINE]` <br> e.g., `add reminder r/mark assignment 1`
**Delete Reminders** | `delete rem INDEX` <br> e.g., `delete rem 3`
**Clear Reminders** | `clear rem` <br> e.g., `clear rem`
**Help** | `help`
