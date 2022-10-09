---
layout: page
title: User Guide
---

Healthcare Xpress is a **desktop app for managing patients that require home-visits and nurses, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Healthcare Xpress can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `healthcarexpress.jar` `[Coming soon]`.

1. Copy the file to the folder you want to use as the _home folder_ for your Healthcare Xpress Book.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.


   * **`add`**`add c/N n/Jason p/98723432 e/jason@example.com g/M t/Asthma` : Adds a nurse named `Jason` to the Healthcare Xpress book.

   * **`add`** **`c/P n/John p/98765432 e/john@example.com g/M a/Bishan street, block
     123, #01-01 t/Asthma d/2022-12-12 1350`** : Adds a patient named **`John`** to Healthcare Xpress book.


   * **`delete`**`-id 3` : Deletes the nurse of patient with an id of 3.

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

![help message](images/helpMessageUpdated.png)

Format: `help`


### Adding a nurse or patient: `add`

1. Adds a patient to the Healthcare Xpress book.

Format: `add c/P n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS d/DATE_AND_TIME [t/TAG]…​`

<div markdown="span" class="alert alert-primary"> 

:bulb:**Tips:**
* A patient can have any number of tags (including 0).
* Date and time need to be in the format of yyyy-MM-dd HHmm.
* To add a patient, type c/P specifically.

</div>

Examples: 
* `add c/P n/John p/98765432 e/john@example.com g/M a/Bishan street, block 123, #01-01 t/Asthma d/2022-12-12 1350`
* `add c/P n/Betsy t/VIP e/betsy@example.com g/F a/Bugis street d/2022-12-02 1400 p/98345432 t/Heart disease t/children`

2. Adds a nurse to the Healthcare Xpress book

Format:`add c/N n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary"> 

:bulb:**Tips**: 
* A nurse can have any number of tags (including 0).
* You may type it in any order.
* To add a nurse, type c/N specifically.

</div>

Examples:
* `add c/N n/Jason p/98723432 e/jason@example.com g/M a/Blk 855 Woodlands Street 83, Singapore 730855, block 123, #01-01 t/Asthma`
* `add c/N n/Betsy t/Pediatric nurse e/betsy@example.com g/F p/98345432 a/Blk 431 Ang Mo Kio Ave 10, Singapore 560431 #01-01 t/Heart disease specialist`
  

### Listing nurses or patients : `list`

Shows a list of specified nurses or patients, or all nurses and patients if no specifications were provided.

Format: `list [c/CATEGORY] [t/TAG] [g/GENDER] [a/ADDRESS]`

Examples:
* `list c/NURSE` - Lists all nurses enrolled in the database.
* `list c/PATIENT t/DIABETIC g/M` - Lists all male diabetic patients enrolled in the database.
* `list c/PATIENT a/Bugis t/Heart Disease` - List all patients tagged with heart disease in the Bugis region.

### Editing a person : `edit` `[Coming soon]`

Edits an existing person in the Healthcare Xpress book.

Format: `edit -id ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person with the specified `ID`. 
* The ID refers to the unique ID number shown in the displayed person list.
* The ID **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit -id 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit -id 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find` `[Coming Soon]`

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

### Deleting a nurse or a patient : `delete`

Deletes the specified nurse or patient from the records system.

Format: `delete -id ID`

* Deletes the nurse or patient with the specified `ID`.
* The ID refers to the unique ID number shown in the displayed person list.
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete -id 2` deletes the nurse of patient with an id of 2.
* `find -n Betsy` followed by `delete -id 1` deletes the nurse of patient with an id of 1.

### Marking a patient as visited : `mark`

Marks a specific patient in the records system as having been visited.

Format: `mark -id ID`

* Marks the patient with the specified 'ID' as having been visited.
* The ID refers to the unique ID shown in the displayed person list.
* The ID **must be a positive integer** 1, 2, 3, ...
* `list` or `find` operations can be performed first to get the ID of the desired patient.

Examples:
* `mark -id 1` marks the patient with ID of 1 as having been visited.
* `list c/P` followed by `mark -id 1` marks the patient with ID of 1 as having been visited.
* `find -n John` followed by `mark -id 1` marks the patient with ID of 1 as having been visited.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HealthcareXpress data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HealthcareXpress data are saved as a JSON file `[JAR file location]/data/healthcarexpress.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Healthcare Xpress book will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------

**Add** | `add c/N n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER [t/TAG]…​` <br> e.g., `add c/N n/Jason p/98723432 e/jason@example.com g/M t/Asthma ` 
**Add** | `add c/P n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS d/DATE_AND_TIME [t/TAG]…​` <br> e.g., `add c/P n/John p/98765432 e/john@example.com g/M a/Bishan street, block 123, #01-01 t/Asthma d/2022-12-12 1350`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit -id ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list [c/CATEGORY] [t/TAG] [g/GENDER] [a/ADDRESS]`<br> e.g., `list c/NURSE`
**Mark** | `mark -id ID` <br> e.g., `mark -id 1`
**Help** | `help`


