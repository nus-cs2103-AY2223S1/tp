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


## Section 1: Contacts

### Add a contact: `add`

Adds a contact to the address book.

Format:  `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [d/DESCRIPTION]`

* The description of a contact is optional.

Examples: 

* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

* `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 d/Weird person.`

### Listing all contacts: `listC`

Shows all contacts stored in the address book.

Format: `listC`

### Deleting a contact: `deleteC`

Removes the specified contact and all its associated information from the address book.

Format: `deleteC INDEX`

* Index of a contact is its index number on the contact list.

* INDEX must be a positive integer more than 0.

Examples:

*`listC` followed by `deleteC 1` deletes the first contact in the address book.

*`find contact -n John` followed by `deleteC 1` deletes the first result of the `find` command.

## Editing a contact: `editC`

Edits the information fields (e.g. name, mobile number, email address) of an existing contact in the address book.

Format: `editC INDEX [n/NAME] [p/PHONE] [a/ADDRESS] [d/Description]`

* Index of a contact is its index number on the contact list.

* INDEX must be a positive integer more than 0.

* At least one of the optional fields must be provided.

* Input values will replace existing values.

Example:

* `editC 1 n/John p/12345678` edits the first contact’s name to be John and phone number to be 12345678.

### Locating contacts by name: `findC`

Finds contacts whose names contain any of the given keywords.

Format: `findC KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive, e.g. `dr. doofenshmirtz` will match `Dr. Doofenshmirtz`.

* The order of the keywords does not matter, e.e. `doofenshmirtz dr.` will match `Dr. Doofenshmirtz`.

* Only the name is searched.

* Only full words will be matched. e.g. `Doofenshmirt` will not match `Doofenshmirtz`.

* Contacts matching at least one keyword will be returned. e.g. `Perry Dr.`
  will match `Perry the Platypus` and `Dr. Doofenshmirtz`

Example:

* `findC flynn` will return `Candace Flynn` and `Phineas Flynn`
* `findC Phineas Ferb` will return `Phineas Flynn` and  `Ferb Flynn`

### Filtering contacts by tag: `filterC`

Filters contacts whose tags contain any of the given keywords.
Filters applied consecutively are stacked.

Format: `filterC KEYWORD [MORE_KEYWORDS]`

* The filer is case-insensitive, e.g. `cs2103t` will match `CS2103T`.

* The order of the keywords does not matter, e.e. `software cs2103t` will match
  `CS2103T Software Engineering`.

* Only the contact's tag is filtered.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Tags matching at least one keyword will be returned. e.g. `cs2103t cs2101` will match
  `CS2103T Software Engineering` and `CS2101 Effective Communication for Computing Professionals`

Example:

* `filterT cs2103t` will return contacts with tag `CS2103T Software Engineering`

## Section 2: Tasks
### Listing all tasks: `listT`

Shows all tasks stored in the task list.

Format: `listT`

### Adding Tasks: `task d/DESCRIPTION D/DEADLINE`

Adds a task to the task list. 
The deadline of the task is allowed to be empty. Newly added tasks are marked as not done. 
Throws an exception if the description of the task is empty. 
Throws an exception if the deadline of the task is not in dd-mm-yyyy format.

Format: `task d/buy milk D/12-09-2022`

### Removing Tasks: `delete i/INDEX`

Removes the specified task from the task list. Throws an exception if task does not exist.

Format: `delete i/12`

### Marking task as done: `mark`

Marks a task in the task list as done.

Format: `mark INDEX`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

Examples:

* `listT` followed by `mark 1` marks the first task in the displayed task list as done.

* `find task -t book` followed by `mark 1` marks the first result of the find command as done.

### Marking task as undone: `unmark`

Marks a task in the task list as undone.

Format: `unmark INDEX`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

### Locating tasks by name: `findT`

Finds tags whose names contain any of the given keywords.

Format: `findT KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive, e.g. `cs2103t` will match `CS2103T`.

* The order of the keywords does not matter, e.e. `software cs2103t` will match
  `CS2103T Software Engineering`.

* Only the task's name is searched.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Tags matching at least one keyword will be returned. e.g. `cs2103t cs2101` will match
  `CS2103T Software Engineering` and `CS2101 Effective Communication for Computing Professionals`

Example:

* `findT cs2103t` will return tasks with tag `CS2103T Software Engineering`

### Filtering tasks by tag: `filterT`

Filters tasks whose tags contain any of the given keywords.
Filters applied consecutively are stacked.

Format: `findT KEYWORD [MORE_KEYWORDS]`

* The filer is case-insensitive, e.g. `cs2103t` will match `CS2103T`.

* The order of the keywords does not matter, e.e. `software cs2103t` will match
  `CS2103T Software Engineering`.

* Only the tasks's tag is filtered.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Tags matching at least one keyword will be returned. e.g. `cs2103t cs2101` will match
  `CS2103T Software Engineering` and `CS2101 Effective Communication for Computing Professionals`

Example:

* `filterT cs2103t` will return tasks with tag `CS2103T Software Engineering`

Examples:

* `listT` followed by `unmark 1` marks the first task in the displayed task list as undone.
* `find task -t book` followed by `unmark 1` marks the first result of the find command as undone.

## Section 3: Tags
### Listing all tags : `taglist`

Shows a list of all existing tags in the address book.

Format: `taglist`

### Adding a tag : `addtag i/INDEX n/TAG_NAME`

Adds a tag to an existing contact in the address book. Each contact can have multiple tags. If there is no existing tag with the same name, tag is added to the tag list. Throws an exception if contact does not exist.

Format: `addtag i/12 n/CS2103T`

### Removing a tag : `rmtag i/INDEX n/TAG_NAME`

Removes a tag from an existing contact in the address book. If contact is last remaining person with said tag, tag is removed from the tag list. Throws an exception if contact or tag does not exist.

Format: `rmtag i/14 n/CS2101`

## YellowBook data
### Saving the data

YellowBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

YellowBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, YellowBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous YellowBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                     |
|------------|------------------------------------------------------------------------------------------------------|
| **listC**   | `listC`                                                                                             |
| **deleteC** | `deleteC INDEX` <br> e.g., `deleteC 1`                                                              | 
| **editC**   | `editC INDEX [n/NAME] [p/PHONE] [a/ADDRESS] [d/Description]` <br> e.g., `editC 1 n/John p/12345678` |
| **searchC** | `findC KEYWORD [MORE_KEYWORDS]` <br> e.g., `findC Phineas Ferb`                                     |
| **filterC** | `filterC KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterT cs2103t`                                      |                                                                                                 |
| **listT**   | `listT`                                                                                             |
| **mark**    | `mark INDEX` <br> e.g., `mark 1`                                                                    |
| **unmark**  | `unmark INDEX` <br> e.g., `unmark 1`                                                                |
| **searchT** | `findT KEYWORD [MORE_KEYWORDS]` <br> e.g., `findT cs2103t`                                          |
| **filterT** | `filterT KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterT cs2103t`                                      |

