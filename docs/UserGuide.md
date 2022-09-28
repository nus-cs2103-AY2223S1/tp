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

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`create`**`name:John Doe num:98765432 address:John street, block 123, #01-01` : Adds a contact named `John Doe` to the FABook.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `create name:NAME`, `NAME` is a parameter which can be used as `create name:John Doe`.

* Items in square brackets are optional.<br>
  e.g `name:NAME [tag:TAG]` can be used as `name:John Doe tag:friend` or as `name:John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tag:TAG]…​` can be used as ` ` (i.e. 0 times), `tag:friend`, `tag:friend tag:family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `name:NAME num:HP_NUMBER`, `num:HP_NUMBER name:NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `num:12341234 num:56785678`, only `num:56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Creating a contact: `create`

Creates a contact that is stored in the address book and contains their contact information. Any contact information that is not available can be updated later.

Format: `create name:NAME [num:PHONE_NUMBER] [address:ADDRESS] [meeting_time:TIME] [tag:TAG]…​`

:bulb: **Note:**
Name is the only compulsory input. Parameters not provided will be left blank.

Examples:
* `create name:John Doe num:98765432 address:John street, block 123, #01-01`
* `create name:Betsy Crowe tag:friend address:Newgate Prison num:1234567 tag:criminal`

:white_check_mark: **Tip:**
Input shortcut: `c` can be used in place of `create`.
Format: `c name:NAME [num:PHONE_NUMBER] [address:ADDRESS] [meeting_time:TIME] [tag:TAG]…​`


### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Updating a person : `update`

Searches for a contact that is stored in the address book and updates its contact information.

Format: `update name:NAME [num:PHONE_NUMBER] [address:ADDRESS] [meeting_time:TIME] [tag:TAG]…​`

:bulb: **Note:**
Name is the only compulsory input. Parameters not provided will be stay unchanged.

* Edits the person with the provided name.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will not be removed i.e adding of tags is cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit name:John Doe number:91234567 address:21 Lower Kent Ridge Rd` Edits the phone number and address of the `John Doe` to be `91234567` and `21 Lower Kent Ridge Rd` respectively.
*  `edit name:Betsy Crower number:90990334 tag:` Edits the HP number of  `Betsy Crower` to be `90990334` and clears all existing tags.

:white_check_mark: **Tip:**
Input shortcut: `u` can be used in place of `update`.
Format: `u name:NAME [num:PHONE_NUMBER] [address:ADDRESS] [meeting_time:TIME] [tag:TAG]…​`

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find NAME [MORE_NAMES]`

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
  ![result for 'find alex david'](images/findAlexDavidResult.png)
  
:white_check_mark: **Tip:**
Input shortcut: `f` can be used in place of `find`.
Format: `f NAME`
  
### Locating persons by phone number: `find`

Finds persons whose stored number matches the given number.

Format: `find NUMBER`

* Only full numbers will be matched e.g. `7654` will not match `80765432`
* All persons matching the number will be returned. e.g. All contacts in the same household will be returned if they share a home number.

:heavy_exclamation_mark: **Caution:**
`NUMBER` should be a string of numbers. Letters will be searched as names instead. See [Locating persons by name](https://github.com/isanidiot/tp/edit/branch-ug-v1.1/docs/UserGuide.md#locating-persons-by-name-find).

Examples:
* `find 90333333` returns the person(s) with `90333333` stored as their number

:white_check_mark: **Tip:**
Input shortcut: `f` can be used in place of `find`.
Format: `f NUMBER`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete NAME`

* Deletes the person with the specified `NAME`.

Examples:
* `delete Aaron Judge` deletes `Aaron Judge` in the address book.

### Clearing all entries : `clear`

Clears all entries from the address book.

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

FABook data are saved as a text file `[JAR file location]/data/fabook.text`. Advanced users are welcome to update data directly by editing that data file.

:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples | Shortcut
--------|------------------|---
**Create** | `create name:NAME [num:PHONE_NUMBER] [address:ADDRESS] [meeting_time:TIME] [tag:TAG]…​`<br> e.g., `create name:Betsy Crowe tag:friend address:Newgate Prison num:1234567 tag:criminal`|c
**Clear** | `clear` |
**Delete** | `delete NAME`<br> e.g., `delete Aaron Judge` |
**Update** | `update name:NAME [num:PHONE_NUMBER] [address:ADDRESS] [meeting_time:TIME] [tag:TAG]…​`<br> e.g.,`edit name:John Doe number:91234567 address:21 Lower Kent Ridge Rd`| u
**Find** | `find NAME [MORE_NAMES]`__or__ `find NUMBER` <br> e.g., `find James Jake` __or__ `find 09122222` |f
**List** | `list` |
**Help** | `help` |
