---
layout: page
title: User Guide
---

TutHub is a **desktop app for NUS School of Computing professors who have to keep track of hundreds of teaching assistant/tutor profiles and monitor their performance and track records but have little time to spare for tedious administrative work. It is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

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

   * **`list`** : Lists all tutors.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com m/CS2103T y/3 s/A1234567X` : Adds a tutor named `John Doe` to Tuthub.

   * **`delete`**`3` : Deletes the 3rd tutor shown in the current list.
   
   * **`save`** : Saves the data to your local device. 

   * **`clear`** : Deletes all tutors.

   * **`exit`** : Exits the TutHub app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/senior`, `t/senior t/great` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `exit`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Viewing help : `help`

Displays a table consisting of the summary of commands available in Tuthub.

Format: `help`

### Adding a tutor: `add`

Adds a tutor to the Tuthub.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL m/MODULE y/YEAR [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com m/CS2100 y/3`
* `add n/Betsy Crowe t/senior ta e/betsycrowe@example.com m/CS1101S y/3 p/1234567`

### Listing all tutors : `list`

Shows a list of all tutors registered in TutHub.

Format: `list`

### Commenting on a tutor : `comment`

Adds a comment on the specified tutor.

Format: `comment INDEX c/COMMENT`

* Comments on the person at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* Only 1 comment can be made on the tutor.
* To edit an existing comment, doing the comment command on the INDEX of the specified tutor updates the comment on the tutor.
* You can remove the tutor's comments by typing c/ without specifying any tags after it.

Examples:
* `comment 1 c/Tasks not Finished` adds a comment of "Tasks not Finished" on the 1st tutor.
* `comment 2 c/` clears the comments of the 2nd tutor.

### Editing a tutor : `edit`

Edits an existing tutor in Tuthub.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [m/MODULE] [y/YEAR] [s/STUDENTID] [t/TAG]…​`

* Edits the person at the specified INDEX. The index refers to the index number shown in the displayed tutor list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the tutor will be removed i.e adding of tags is not cumulative.
* You can remove all the tutor’s tags by typing t/ without specifying any tags after it.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st tutor to be 91234567 and johndoe@example.com respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd tutor to be Betsy Crower and clears all existing tags.
* `edit 3 m/CS2100 y/3 s/A0654729L` Edits the module code, year, and student id of the 3rd tutor to be CS2100, 3, and A0654729L respectively.

### Locating tutor by name: `find`

Finds tutor whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only the name is searched.
* Only full words will be matched e.g. Han will not match Hans
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang

Examples:
* `find John` returns all tutors with names that contains `John`.
* `find alex david` returns all tutors with names that contain `Alex` or `David`.

### Locating tutors of modules: `findbymodule`

Finds tutor whose teaches the given keyword.

Format: `findbymodule KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g cs2103 will match CS2103
* Only the module code is searched.
* Only full words will be matched e.g. CS210 will not match CS2103

Examples:
* `findbymodule CS2103` returns all tutors teaching `CS2103`.
* `findbymodule CS2103 CS2100` returns all tutors teaching `CS2103` or `CS2100`.

### Deleting a tutor : `delete`

Deletes the specified tutors from Tuthub.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in Tuthub.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

[coming soon]

### Saving the data

Tuthub data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

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

| Action             | Format, Examples                                                                                                                                            |
|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**           | `help`                                                                                                                                                      |
| **Add**            | `add n/NAME p/PHONE_NUMBER e/EMAIL y/YEAR m/MODULE [t/TAG]…​` <br> e.g., `add n/Betsy Crowe t/  e/betsycrowe@example.com  p/1234567 m/CS1101S y/3 t/senior` |
| **Comment**        | `comment INDEX c/COMMENT` <br> e.g., `comment 1 c/Tasks not Finished`                                                                                       |
| **Edit**           | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [m/MODULE] [y/YEAR] [s/STUDENTID] [t/TAG]…​` <br> e.g., `edit 3 m/CS2100 y/3 s/A0654729L`                          |
| **Find**           | `find KEYWORD [MORE_KEYWORDS]` <br> e.g., `find Alex`                                                                                                       |
| **Find by Module** | `findbymodule KEYWORD [MORE_KEYWORDS]` <br> e.g., `findbymodule CS2100`                                                                                     |
| **Delete**         | `delete INDEX`<br> e.g., `delete 3`                                                                                                                         |
| **List**           | `list`                                                                                                                                                      |
| **Exit**           | `exit`                                                                                                                                                      |
