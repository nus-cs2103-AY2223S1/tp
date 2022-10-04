---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

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

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

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

### Instantaneous launching
Users that have Java 11 or above installed in their computers can launch the Duke The Market program 
by double clicking on the file.

### Saving the data
Duke The Market data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a contact: `add`

Adds a contact to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [g/GENDER] [d/DOB] [ph/NEW_PURCHASE_DETAIL] [t/TAG]`

- The compulsory parameters are: name (`n`), phone number (`p`), email (`e`), address (`a`)
- The parameters in [ ] are optional parameters, including gender (`g`), date of birth (`d`), purchase history (`ph`), tag (`t`).
- A person in the contact list can have more than 1 tag.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/m d/20 MAR 2002
  ph/[12.04.2022, 101.25, 3, Orange, Umbrella, Soap]`
* `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 t/friend t/criminal g/f g/14.12.1998
  ph/[13.05.2021 12.25, 3, Sweets, Banana, Battery]`

__Optional Parameter 1: Gender__

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [g/GENDER]`

- Adds gender to a person in the contact list. The genders accepted by the contact list are: `M`/`m` for male,  `F`/`f` for female.
- NAME can contain more than 1 word.

Examples:
* `add n/John Wang p/98765432 e/johnwang@example.com a/John street, block 123, #01-01 g/M`
* `add n/John p/92781123 e/john@example.com a/Donald street, block 248, #02-04 g/m`
* `add n/Charlotte p/81286623 e/charlotte@example.com a/Charity street, block 101, #10-82  g/F`


### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list [s/FIELD]`

* Sorts the contacts by the specified field in **ascending** order. `FIELD` must take one the following values:
  * `n` sort by name
  * `d` Sort by date of birth
  * `g` Sort by gender

* It is optional to specify the field to sort by. If no field is specified, persons are listed in the order they were inserted.
* At most one field can be specified. i.e. Cannot specify 2nd or 3rd criteria to sort by.
* Persons with an empty field that is being used to sort will be placed at the top of the list.

Examples:
* `list` Lists all persons without sorting them.
* `list s/n` Lists all persons sorted by their names.

### Editing a contact : `edit`

Edits an existing contact in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GENDER] [d/DOB] [ph/PURCHASE_INDEX,UPDATED_PURCHASE_DETAIL] [t/TAG]`

- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
  The index must be **a positive integer** 1, 2, 3, …​, and it must be within the range of the contact list index.
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed (i.e adding of tags is not cumulative).
- You can remove all the person’s tags by typing t/ without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be
   `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 3 n/Charlotte g/F d/3.4.1998 ph/3,[20.03.2022, 101.25, Chair, Table, Banana]` Edits the 3rd person’s contact:
   edits name to be `Charlotte`, edits gender to be `Female`, edits date of birth to be `3.4.1998`,
   edits the purchase history to be `3,[20.03.2022, 101.25, Chair, Table, Banana]`.

__Optional Parameter 1: Gender__

Format: `edit INDEX [g/GENDER]`

- Edits the gender of a person in the contact list. The genders accepted by the contact list are: `M`/`m` for male,  `F`/`f` for female.
- `INDEX` must be **a positive integer** (i.e 1,2,3…)
- `INDEX` must be within the range of the contact list index (i.e from 1 to size of contact list).

Examples:
* `edit 1 g/M`
* `edit 2 g/f`
* `edit 3 g/F`


### Locating persons by name: `find`

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
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Creating an event: `create`

Creates a new event in the address book.

Format: `create t/EVENT_TITLE d/DATE t/TIME p/PURPOSE`

* The compulsory parameters are: event title (`t`), date (`d`), time (`t`) and purpose (`p`)

Examples:
* `create t/Shoe Sale 30% d/30-05-2022 t/11:00 p/Discount on all shoes for up to 30%`
* `create t/Banana Discount 10% d/20-04-2022 t/14:00 p/10% discount on all bananas`

### Removing an event: `remove`

Removes an existing event in the address book.

Format: `remove INDEX`

* Removes the event at the specified `INDEX`. 
* The index refers to the index number shown in the displayed event list 
* The index must be a positive integer 1, 2, 3, …, and it must be within the range of the event list index.

Examples:
* `delete 2` after displaying all events with display removes event at index 2
* `delete 7` after displaying all events with display removes event at index 7

### Display all events: `display`

Shows a list of all existing events in the address book.

Format: `display`

Examples:
* `display`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

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

| Action     | Format, Examples                                                                                                                                                      |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                               |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**   | `list`                                                                                                                                                                |
| **Help**   | `help`                                                                                                                                                                |
