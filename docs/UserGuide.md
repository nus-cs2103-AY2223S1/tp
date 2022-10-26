---
layout: page
title: User Guide
---

FABook is a **desktop app for managing contacts, optimized for a financial adviser to use via a Command Line Interface (CLI)** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FABook can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `FABook.jar` from [here](https://github.com/AY2223S1-CS2103T-T10-2/tp/releases).

4. Copy the file to the folder you want to use as the _home folder_ for your FABook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`create`**`n/John Doe p/98765432 a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the FABook.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `create n/NAME`, `NAME` is a parameter which can be used as `create n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [p/HP_NUMBER]` can be used as `n/John Doe p/90338099` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times.<br>
  e.g. `NAME…​` can be used as `Jon`, `Jon Jack` etc.

* `INDEX` represents the index of a specific person in the FABook
  e.g. `delete INDEX` can be used as `delete 2`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/HP_NUMBER`, `p/HP_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Viewing help : `help`

Shows a message explaning how to access the help page.

Format: `help`


### Creating a contact: `create`

Creates a contact that is stored in the FABook and contains their contact information. Any contact information that is not available can be updated later.

Format: `create n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [nw/NETWORTH] [mt/TIME] [d/description]...`

:bulb: **Note:**
Name and Phone number are the only compulsory inputs. Parameters not provided will be left blank.

Examples:
* `create n/John Doe p/98765432 a/John street, block 123, #01-01`
* `create n/Betsy Crowe a/Newgate Prison p/1234567`

:white_check_mark: **Tip:**
Input shortcut: `c` can be used in place of `create`.
Format: `c n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [nw/NETWORTH] [mt/TIME] [d/description]...`


### Listing all persons : `list`

Shows a list of all persons in the FABook.

Format: `list`

### Updating a person : `update`

Searches for a contact that is stored in the FABook and updates its contact information.

Format: `update INDEX n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [nw/NETWORTH] [mt/TIME] [d/description]...`

:bulb: **Note:**
Parameters not provided will stay unchanged.

* Edits the person with the provided name.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* INDEX needs to be a current index in the person list

Examples:
*  `update 2 n/John Doe p/91234567 a/21 Lower Kent Ridge Rd` Updates the phone number and address of the `John Doe` to be `91234567` and `21 Lower Kent Ridge Rd` respectively.

:white_check_mark: **Tip:**
Input shortcut: `u` can be used in place of `update`.
Format: `u INDEX n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [mt/TIME]`

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find n/NAME…`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched. e.g. `find Kent` won’t return contacts living on Kent Ridge Road
* Partial words can be matched only if the whole name is included. e.g. `Jack` will match `Jackson`, but `Jackson` won’t match `Jack`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

:heavy_exclamation_mark: **Important**
`NAME` should be a word. Numbers will be searched as phone numbers instead. See [Locating persons by phone number](https://github.com/isanidiot/tp/edit/branch-ug-v1.1/docs/UserGuide.md#locating-persons-by-phone-number-find).

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  
:white_check_mark: **Tip:**
Input shortcut: `f` can be used in place of `find`.
Format: `f NAME…`
  
### Locating persons by phone number: `find`

Finds persons whose stored number matches the given number.

Format: `find p/NUMBER`

* Only full numbers will be matched e.g. `7654` will not match `80765432`
* All persons matching the number will be returned. e.g. All contacts in the same household will be returned if they share a home number.

:heavy_exclamation_mark: **Caution:**
`NUMBER` should be a string of numbers. Letters will be searched as names instead. See [Locating persons by name](https://github.com/isanidiot/tp/edit/branch-ug-v1.1/docs/UserGuide.md#locating-persons-by-name-find).

Examples:
* `find 90333333` returns the person(s) with `90333333` stored as their number

:white_check_mark: **Tip:**
Input shortcut: `f` can be used in place of `find`.
Format: `f NUMBER`

### Locating persons by address: `find`

Format: `find a/ADDRESS`

* The search is case-insensitive. e.g `serangoon` will match `Serangoon`
* The order of the keywords does not matter. e.g. `Kio Mo Ang` will match `Ang Mo Kio`
* Only the address is searched. e.g. `find Kent` won’t return contacts with the name 'Kent'
* Words can be matched only if the whole address is included. e.g. `Tamp` won’t match `Tampines`
* Persons with address matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Buona Clementi` will return `Buona Vista Drive`, `6 Clementi Ave`
* Address can contain numbers. Find results will return all persons with that address keyword. 
  e.g. '30' will return 'Blk 30 Geylang Street 29', 'Blk 30 Lorong 3 Serangoon Gardens'

Examples:
* `find Bedok` returns the person(s) with 'Bedok' stored as their address

:white_check_mark: **Tip:**
Input shortcut: `f` can be used in place of `find`.
Format: `f a/ADDRESS…`

### Assigning PDF file to a person: `filepath`

Assigns a PDF file to a person in the FABook.

Format: `filepath INDEX f/FILEPATH`

* The filepath is the absolute path of the PDF on the local disk of the computer. e.g. `C:/Users/Ryzen/Downloads/CS2103T-T08-3.pdf`
* Only file paths that lead to a PDF is allowed. e.g. `C:/Users/Ryzen/Downloads/CS2103T-T08-3.docx`
* Moving or renaming the PDF file in the local disk does not change the person's assigned file path, so you would have to assign it manually.

Examples:
* `filepath 2 f/C:/Users/Ryzen/Downloads/CS2103T-T08-3.pdf` assigns second person in displayed list with the PDF file located at the absolute path `C:/Users/Ryzen/Downloads/CS2103T-T08-3.pdf`

### Opening PDF file of a person: `file`

Opens a person's assigned PDF file in user's PDF file viewer.

Format: `file INDEX`

* The index is the index of the person in the currently displayed list
* Moving or renaming the PDF file in the local disk will cause the command to not work, will require you to reassign a file path to the person again.

Examples:
* `file 2` opens the PDF file assigned to the second person in the displayed list.

### Deleting a person : `delete`

Deletes the specified person from the FABook.

Format: `delete INDEX`

* Deletes the person with the specified index in the FABook.

Examples:
* `delete 2` deletes the second person in the FABook.

### Clearing all entries : `clear`

Clears all entries from the FABook.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

:white_check_mark: **Tip:**
Input shortcut: `e` can be used in place of `exit`.
Format: `e`

### Saving the data

FABook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FABook data are saved as a text file `[JAR file location]/data/FABook.text`. Advanced users are welcome to update data directly by editing that data file.

:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FABook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FABook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action          | Format, Examples                                                                                                                                         | Shortcut |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| **Create**      | `create n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [mt/TIME] `<br> e.g., `create n/Betsy Crowe a/Newgate Prison p/1234567`      | c        |
| **Clear**       | `clear`                                                                                                                                                  | cl       |
| **Delete**      | `delete NAME`<br> e.g., `delete Aaron Judge`                                                                                                             | d        |
| **Update**      | `update n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [mt/TIME]`<br> e.g.,`edit n/John Doe p/91234567 a/21 Lower Kent Ridge Rd` | u        |
| **Find**        | `find NAME…`__or__ `find NUMBER` <br> e.g., `find James Jake` __or__ `find 09122222`                                                                     | f        |
| **List**        | `list`                                                                                                                                                   | l        |
| **Open File**   | `file [INDEX]`<br/> e.g. `file 2`                                                                                                                        |          |
| **Assign File** | `filepath INDEX f/FILEPATH`<br/> e.g. `find 2 f/C:/Users/Ryzen/Downloads/CS2103T-T08-3.pdf`                                                              |          |
| **Help**        | `help`                                                                                                                                                   |          |
| **Exit**        | `exit`                                                                                                                                                   | e        |
