---
layout: page
title: User Guide
---

CLInkedIn is a **desktop address book application made for Recruiting and Hiring Managers to keep track of candidates and their job applications.** The application is optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CLInkedIn can get your Recruitment tasks done faster than traditional GUI apps.


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

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number and any kind of tags (including 0).
</div>

* By default, you can add 3 types of tags - `SKILL_TAG`, `DEGREE_TAG`, `JOB_TYPE_TAG`.
* Alternatively, you can create your own custom tag type and alias for it. See `create` command.


Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 st/Java dt/Bachelors jt/Internship`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [st/OLD_SKILL_TAG-NEW_SKILL_TAG] [dt/OLD_DEGREE_TAG-NEW_DEGREE_TAG] [jt/OLD_JOB_TYPE_TAG-NEW_JOB_TYPE_TAG] [<alias>/OLD_TAG-NEW_TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* While editing **Skill** tags, the existing `OLD_SKILL` of the person will be renamed to `NEW_SKILL`(Same for **Degree**, **JobType** and **Custom** tags).
* You can remove **all** the person’s tags by typing `t/` without specifying any tags after it.
* You can remove **all** the person’s **Skill** tags by typing `st/` without specifying any tags after it (Same for **Degree**, **JobType** and **Custom** tags).

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
* `edit 2 dt/Bachelors-Masters` Edits the Degree tag `Bachelors` of the 2nd person to be `Masters`.

### Adding a tag to an exiting person : `addTag`

Adds a tag to an existing person in the address book.

Format: `addTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​`

* Adds a tag to the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.

Examples:
* `addTag 3 st/Java` Adds a **Skill** tag `Java` to the 3rd person.
* `edit 2 dt/Bachelors` Adds a **Degree** tag `Bachelors` to the 2nd person.

### Deleting a tag of an exiting person : `deleteTag`

Deletes a tag of an existing person in the address book.

Format: `deleteTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​`

* Deletes an existing tag of the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.

Examples:
* `deleteTag 3 st/Java` Deletes the **Skill** tag `Java` of the 3rd person.
* `deleteTag 2 dt/Bachelors` Deletes the **Degree** tag `Bachelors` of the 2nd person.

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

| Action        | Format, Examples                                                                                                                                                                                                                                                       |
|---------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**       | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 st/Java`                                                     |
| **Clear**     | `clear`                                                                                                                                                                                                                                                                |
| **Delete**    | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                    |
| **Edit**      | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [st/OLD_SKILL_TAG-NEW_SKILL_TAG] [dt/OLD_DEGREE_TAG-NEW_DEGREE_TAG] [jt/OLD_JOB_TYPE_TAG-NEW_JOB_TYPE_TAG] [<alias>/OLD_TAG-NEW_TAG]…​`<br> e.g.,`edit 1 p/91234567 e/johndoe@example.com dt/Bachelors-Masters`   |
| **AddTag**    | `addTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​`<br> e.g., `addTag 3 st/Java`                                                                                                                                                           |
| **deleteTag** | `deleteTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​`<br> e.g., `deleteTag 2 dt/Bachelors`                                                                                                                                                |
| **Find**      | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                                             |
| **List**      | `list`                                                                                                                                                                                                                                                                 |
| **Help**      | `help`                                                                                                                                                                                                                                                                 |
