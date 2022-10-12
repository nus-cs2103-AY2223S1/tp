---
layout: page
title: User Guide
---
# uNivUSal

uNivUSal is a **desktop app for managing contacts catered to CS2103T students, TAs, and professors**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, uNivUSal can get your contact management tasks done faster than traditional GUI apps.


* Quick start
* Features
   * Viewing help : help
   * Adding a person: add
   * Listing all persons : list
   * Editing a person : edit
   * Deleting a person : delete
   * Find a person: find
   * Clearing all entries : clear
   * Undoing last command : undo
   * Sorting all entries by a specific field : sort
* Command summary

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `univusal.jar` (in progress).

3. Copy the file to the folder you want to use as the _home folder_ for your uNivUSal.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all CS2103T personnels.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to uNivUSal.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`undo`** : Undoes last modifying command.

   * **`sort`** : Sorts entries.

   * **`find`** : Finds a contact based on partial name/phone number

  * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

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

Shows a message explaining the basic features of the app and also provides a link to the website.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to uNivUSal.


Format: `add o/OCCUPATION n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the CS2103T module.

Format: `list`

### Editing a person : `edit`

Edits an existing person in uNivUSal.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/janedoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `janedoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and **clears** all existing tags.

### Deleting a person : `delete`

Deletes the specified person from uNivUSal.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in uNivUSal.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from uNivUSal.

Format: `clear`
* Deletes every person in uNivUSal.
* Only a blank uNivUSal will remain.

### Undo last modification : `undo`

Undoes the last command that modifies the address book.

Format: `undo`
* Undoes the last modifying command.
* Ignores modifying commands if they don't modify the address book.
* Will cancel out when trying to undo an undo command.

### Sort entries by field : `sort PREFIX/` e.g. `sort n/`

Sorts entries in uNivUSal by specific field prefix in an ascending manner. Current fields implemented are:
1. Name `n/`
2. Email address `e/`
3. Phone `p/`

Format: `sort PREFIX/`
* Sorts the entries by the specified `PREFIX/`.
* Displays sorted list.

Examples:
* `sort n/` displays the entries sorted by name alphabetically.
* `sort e/` displays the entries sorted by email address alphabetically.
* `sort p/` displays the entries sorted by phone number in an ascending manner.

### Find entries by keywords of fields : `find KEYWORD` e.g. `find John`

Finds entries in uNivUSal that match or contain case-insensitive keyword. Current fields searchable are:
1. Name
2. Phone Number

Format: `find KEYWORD`
* Filters the entries by the specified `KEYWORD`.
* Displays filtered list.

Examples:
* `find John` displays the entries filtered by names that contain the case-insensitive KEYWORD `John`.
* `find J` displays the entries filtered by names that contain the case-insensitive KEYWORD `J`.
* `find 123456` displays the entries filtered by phone numbers that contain the case-insensitive KEYWORD `123456`.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

uNivUSal data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

uNivUSal data are saved as a JSON file `[JAR file location]/data/univusal.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, uNivUSal will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous uNivUSal home folder.

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                            |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**   | `help`                                                                                                                                                      |
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **List**   | `list`                                                                                                                                                      |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                             |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                         |
| **Clear**  | `clear`                                                                                                                                                     |                                                                             |                                                                                                          |
| **Undo**   | `undo`                                                                                                                                                      |
| **Sort**   | `sort PREFIX/` <br> e.g., `sort n/`                                                                                                                         |
| **Find**   | `find KEYWORD` <br> e.g., `find John`                                                                                                                       |
| **Exit**   | `exit`                                                                                                                                                      |
