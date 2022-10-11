---
layout: page
title: User Guide
---

# User Guide

Sherlock is a **desktop app for law enforcement officers to easily manage the particulars of citizens optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interfac (GUI). If you can type fast, Sherlock can retrieve information for potential suspects faster than traditional GUI apps.

* Table of Contents
    * [Quick Start](#quick-start)
    * [Features](#features) `(Version 1.2)`
        * Listing all persons: [`list`](#listing-all-persons--list)
        * Adding a person: [`add`](#adding-a-person-add)
        * Editing a person: [`edit`](#editing-a-person--edit)
        * Locating persons by: [`find`](#locating-persons-by-name-find)
        * Deleting a person: [`delete`](#deleting-a-person--delete)
    * [FAQ](#faq)
    * [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `sherlock.jar` from here (Hyperlink will be added in the future).

1. Copy the file to the folder you want to use as the _home folder_ for your Sherlock.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all persons in Sherlock.

    * **`add`**`id/12345678 n/John Doe p/98765432 a/John street, block 123, #01-01` : Adds a person named `John Doe` to Sherlock.

    * **`delete`**`id/12345678` : Deletes the person with `ID number 12345678` from Sherlock.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format**<br>

* Words in `UPPER_CASE` are the inputs to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a user-defined input. An example would be `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `TAG` in `n/NAME [t/TAG]` can be excluded. The command can be run as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extra parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to Sherlock.

Format: `add id/ID_NUMBER n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

* Adds a person’s detail to Sherlock. The person must not exist in Sherlock.
* Minimal details are required to add a person’s detail at the beginning. Any other information can be added through the `edit` feature.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add id/1234 n/John Doe p/98765432 a/John street, block 123, #01-01`
* `add id/1235 n/Betsy Crowe a/Newgate Prison p/12345678 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in Sherlock.

Format: `list`

### Editing a person : `edit`

Edits an existing person in Sherlock.

Format: `edit id/ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br>
`edit index/INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `ID` or `INDEX`. The ID refers to the unique Identification number assigned to the person.
The index refers to the index of the person after using the find/list feature.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
* `edit index/1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit index/2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
* `edit id/1234 n/Charlie Crackshot` Edits the name of the person with ID 1234 to be `Charlie Crackshot`.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords or through their given ID.

Format: `find KEYWORD/ID [MORE_KEYWORDS/MORE_ID]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name/ID is searched.
* Only full words or ID will be matched e.g. `Han` will not match `Hans` / `123` will not match `1234`
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`


Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from Sherlock.

Format: `delete id/ID` <br> `delete index/INDEX`

* Deletes the person at the specified ID or index.
* The ID refers to the unique identification number assigned to the person
* The index refers to the index of the person after using the find/list feature.
* The id or index **must be a positive integer** 1, 2, 3, …​


Examples:
* `delete id/2356`
* `delete index/1`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
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

Action | Format, Examples
--------|------------------
**Add** | `add id/ID_NUMBER n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`…​` <br> e.g., add id/1234 n/John Doe p/98765432 a/John street, block 123, #01-01`
**Clear** | `clear`
**Delete** | `delete INDEX/ID`<br> e.g., `delete index/1`, `delete id/2356`
**Edit** | `edit INDEX/ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit index/2 n/James Lee e/jameslee@example.com`,`edit id/1234 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD/ID [MORE_KEYWORDS/MORE_ID]`<br> e.g., `find James Jake`, `find 1234`
**List** | `list`
**Help** | `help`
