---
layout: page
title: User Guide
---

TutHub is a **desktop app for tech-savvy tuition agents who want to keep track of hundreds of tutors’ profiles in a more organised and systematic way compared to excel sheets, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tuthub.jar` from [here](https://github.com/AY2223S1-CS2103T-T15-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Tuthub.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 g/M e/johnd@example.com s/40` : Adds a contact named `John Doe` to Tuthub.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.
   
   * **`save`** : Saves the data to your local device. 

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the TutHub app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/s2math` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/s2math`, `t/s2math t/s2chem` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `exit`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Viewing help : `help`

[coming soon]

### Adding a tutor: `add`

Adds a tutor to the Tuthub.

Format: `add n/NAME p/PHONE_NUMBER g/GENDER e/EMAIL s/SALARY [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 g/M e/johnd@example.com s/40`
* `add n/Betsy Crowe t/s2chem g/F e/betsycrowe@example.com s/30 p/1234567 t/s2math`

### Listing all tutors : `list`

Shows a list of all tutors registered in TutHub.

Format: `list`

### Editing a tutor : `edit`

[coming soon]

### Locating tutor by name: `find`

[coming soon]

### Deleting a tutor : `delete`

Deletes the specified tutor from Tuthub.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in Tuthub.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

[coming soon]

### Saving the data : `save`

Saves the data to the local hard disk.

### Exiting the program : `exit`

Exits TutHub app.

Format: `exit`

### Editing the data file

[coming soon]

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Tuthub home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/Betsy Crowe t/s2chem g/F e/betsycrowe@example.com s/30 p/1234567 t/s2math`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**List** | `list`
**Save** | `save`
**Exit** | `exit`
