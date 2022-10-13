---
layout: page title: User Guide
---

# SOCompiler - User Guide

> The sole app that university students will need to streamline their everyday routines.

SOCompiler is a **desktop app for managing contacts and module details, optimized for use via a Command Line
Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SOCompiler
can get your contact and module management tasks done faster than traditional GUI apps.

* Table of Contents

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `socompiler.jar` from [here](https://github.com/AY2223S1-CS2103T-W12-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your SOCompiler.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it.

6. Refer to the [Command Summary](#command-summary) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. for add `c/NAME`, NAME is the user parameter and can be replaced with John Doe

* Items in square brackets are optional.<br>
  e.g. `c/NAME [/t TAG]` can be used as `c/John Doe t/friend` or as `c/John Doe`.

* Parameters after the identifier c/ and m/ can be in any order. e.g. `add c n/NAME p/NUMBER e/EMAIL` is similar
  to `add c n/NAME e/EMAIL p/NUMBER`.

* If a parameter is expected only once in the command but is specified multiple times, only the last occurrence of the
  parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

</div>

--------------------------------------------------------------------------------------------------------------------

## Contacts

### Listing all contacts : `list c`

Shows a list of all contacts in the contact list.

Format: `list c`

### Adding a contact: `add c`

Adds a contact to the contact list.

Format: `add c n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:

* `add c n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add c n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Editing a contact : `edit`

Edits an existing contact in the contact list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list.
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact’s tags by typing `t/` without specifying any tags after it.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567`
  and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

### Locating contact by name: `find`

Finds contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Contacts matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`
  , `Bo Yang`

Examples:

* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a contact : `delete c`

Deletes the specified contact from the contact list.

Format: `delete c CONTACT_INDEX`

* Deletes the contact at the specified `CONTACT_INDEX`.
* The contact index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete c 2` deletes the 2nd contact in the address book.
* `find Betsy` followed by `delete c 1` deletes the 1st contact in the results of the `find` command.

### Clearing all contacts : `clear`

Clears all entries from the contact list.

Format: `clear`

--------------------------------------------------------------------------------------------------------------------

## Modules

### Listing all modules : `list m`

Shows a list of all modules in the module list.

Format: `list m`

### Adding a module: `add m`

Adds a contact to the module list.

Format: `add m m/MODULE_CODE l/LECTURE_VENUE_AND_TIMING t/TUTORIAL_VENUE_AND_TIMING a/ASSIGNMENT_NAME_AND_DEADLINE z/ZOOM_LINK [t/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A module can have any number of tags (including 0)
</div>

Examples:

* `add m/CS1101S l/I3-AUDI Monday 12:00 t/COM1 B1-103 Tuesday 14:00 a/Functional Expressionism ONLY ONE WEEK z/https://www.zoom.sg/12891`

### Deleting a module : `delete m`

Deletes the specified module from the module list.

Format: `delete m MODULE_INDEX`

* Deletes the contact at the specified `MODULE_INDEX`.
* The module index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `delete m 1`

--------------------------------------------------------------------------------------------------------------------

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SOCompiler data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous SOCompiler home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format                                                                                                                          |
|------------|---------------------------------------------------------------------------------------------------------------------------------|
| **Add      |                                                                                                                                 |
| Contacts** | `add c n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`                                                                       |
| **Add      |                                                                                                                                 |
| Modules**  | `add m m/MODULE_CODE l/LECTURE_VENUE_AND_TIMING t/TUTORIAL_VENUE_AND_TIMING a/ASSIGNMENT_NAME_AND_DEADLINE z/ZOOM_LINK [t/TAG]` |
| **Delete   |                                                                                                                                 |
| Contacts** | `delete c INDEX`                                                                                                                |
| **Delete   |                                                                                                                                 |
| Modules**  | `delete m INDEX`                                                                                                                |
| **List     |                                                                                                                                 |
| Contacts** | `list c`                                                                                                                        |
| **List     |                                                                                                                                 |
| Modules**  | `list m`                                                                                                                        |
| **         |                                                                                                                                 |
| Clear**    | `clear`                                                                                                                         |
| **         |                                                                                                                                 |
| Edit**     | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`                                                          |
| **         |                                                                                                                                 |
| Find**     | `find KEYWORD [MORE_KEYWORDS]`                                                                                                  |
| **         |                                                                                                                                 |
| Help**     | `help`                                                                                                                          |
