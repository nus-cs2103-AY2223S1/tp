---
layout: page
title: User Guide
---

Mass Linkers is a powerful Desktop application tool that provides a centralised platform for Computer Science (CS) students to find study support from batchmates with common modules. It allows students to save their CS batchmates’ contact details in one place, find common modules and form study groups. It is optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `masslinkers.jar` from [here](https://github.com/AY2223S1-CS2103T-T11-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Mass Linkers.

4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type a command in the command box and press Enter to execute it. Refer to the section on [Features](#features) below for details and usage of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME t/TELEGRAM [tag/TAG]` can be used as `n/John Doe t/johnxyz tag/friend` or as `n/John Doe t/johnxyz`.

* Items with `...` after them can be used multiple times.<br>
  e.g. `[tag/TAG]...` can be used as `tag/friend`, `tag/friend tag/family` etc.<br>
  e.g. `[MORE_MODULES]...` can be used as `cs2100`, `cs2103t cs2101 cs2105` etc.

* Parameters can be in any order.<br>
  e.g. If the command specifies `n/NAME t/TELEGRAM [g/GITHUB] [p/PHONE] [e/EMAIL] [tag/TAG]`, then `[tag/TAG] [e/EMAIL] [p/PHONE] n/NAME [g/GITHUB] t/TELEGRAM` is also acceptable.

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about parameter:**<br>

* For all commands involving `INDEX`, `INDEX` refers to the index number shown in the currently displayed list.<br>
  * ___Beware!!!___ This may not be the full list of batchmates you have in Mass Linkers! For example, you may have entered the [find command](#finding-a-batchmate-by-name-find), so the currently displayed list will be the result of `find` which is not the full list.
  * The index __must be a positive integer__, e.g. 1, 2, 3 … and be smaller than the number of batchmates in the currently displayed list.



</div>

### Viewing help: `help`

Shows a brief summary of commands with their syntax and a link to the user guide. You can also click the `Open User Guide` button which will redirect you to the user guide in your browser.

Format: `help`


### Adding a batchmate: `add`

Adds a batchmate to the application.

Format: `add n/NAME t/TELEGRAM [g/GITHUB] [p/PHONE] [e/EMAIL] [tag/TAG]...`

Examples:
* `add n/John Doe t/johnxyz`
* `add n/John Doe t/johnxyz g/john_doe p/98765432 e/johnd@example.com tag/friends tag/owesMoney`

### Listing all batchmates: `list`

Shows a list of all batchmates.

Format: `list`

### Editing a batchmate: `edit`

Edits the information of a specified batchmate.

Format: `edit INDEX [n/NAME] [t/TELEGRAM] [g/GITHUB] [p/PHONE] [e/EMAIL] [tag/TAG]...`

* Edits the batchmate at the specific `INDEX` in the __currently displayed list__. Refer to the section on _Notes about parameter_ at the start of [Features](#features) for more details.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the batchmate will be removed i.e adding of tags is not cumulative.
* You can remove all the batchmate’s tags by typing `tag/` without specifying any tags after it.

Examples:
*  `edit 1 g/john_doe p/91234567 e/johndoe@example.com` Edits the github username, phone number and email address of the 1st batchmate in the currently displayed list to be `john_doe`, `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Bob Tan t/` Edits the name of the 2nd batchmate in the currently displayed list to be `Bob Tan` and clears all existing tags.

### Finding a batchmate by name: `find`

Finds batchmates whose names contain __any__ of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]...`

* The search is case-insensitive. e.g `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Persons matching at least one keyword will be returned. e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Tan`, `David Chua`

### Finding batchmates by tags: `findTag`

Finds batchmates whose tags match __all__ the specified tags.

Format: `findTag TAG [MORE_TAGS]...`

Examples:
* `findTag friend` returns all batchmates tagged with `friend`.
* `findTag friend classmate` returns all batchmates tagged with both `friend` and `classmate`.

### Deleting a batchmate: `delete`

Deletes a batchmate of a specific index.

Format: `delete INDEX`

* Deletes the batchmate at the specific `INDEX` in the __currently displayed list__. Refer to the section on _Notes about parameter_ at the start of [Features](#features) for more details.

Examples:
* `list` followed by `delete 2` deletes the 2nd batchmate in the full list of batchmates you have in Mass Linkers.
* `find Betsy` followed by `delete 1` deletes the 1st batchmate in the currently displayed list of the `find` command.

### Adding module to a batchmate: `mod add`

Adds module(s) to a batchmate.

Format: `mod add INDEX MODULE [MORE_MODULES]...`

* Adds module(s) to the batchmate at the specific `INDEX` in the __currently displayed list__. Refer to the section on _Notes about parameter_ at the start of [Features](#features) for more details.

Examples:
* `mod add 1 cs2103t` adds the module `cs2103t` to the 1st batchmate in the currently displayed list.
* `mod add 3 cs2100 cs2103t cs2101 cs2105` adds the modules `cs2100`, `cs2103t`, `cs2101` and `cs2105` to the 3rd batchmate in the currently displayed list.

### Deleting module from a batchmate: `mod delete`

Deletes module(s) from a batchmate.

Format: `mod delete INDEX MODULE [MORE_MODULES]...`

* Deletes module(s) from the batchmate at the specific `INDEX` in the __currently displayed list__. Refer to the section on _Notes about parameter_ at the start of [Features](#features) for more details.

Examples:
* `mod delete 1 cs2103t` deletes the module `cs2103t` from the 1st batchmate in the currently displayed list.
* `mod delete 3 cs2100 cs2103t cs2101 cs2105` deletes the modules `cs2100`, `cs2103t`, `cs2101` and `cs2105` from the 3rd batchmate in the currently displayed list.

### Marking module as taken: `mod mark`

Marks module(s) of a batchmate as `taken`, which means the batchmate has taken or is currently taking the module(s).

Format: `mod mark INDEX MODULE [MORE_MODULES]...`

* Marks module(s) of the batchmate at the specific `INDEX` in the __currently displayed list__. Refer to the section on _Notes about parameter_ at the start of [Features](#features) for more details.

Examples:
* `mod mark 1 cs2103t` marks the module `cs2103t` of the 1st batchmate in the currently displayed list as `taken`.
* `mod mark 3 cs2100 cs2103t cs2101 cs2105` marks the modules `cs2100`, `cs2103t`, `cs2101` and `cs2105` of the 3rd batchmate in the currently displayed list as `taken`.

### Unmarking module as not taken: `mod unmark`

Unmarks module(s) of a batchmate as not taken, which means the batchmate has not taken and is not currently taking the module(s).

Format: `mod unmark INDEX MODULE [MORE_MODULES]...`

* Unmarks module(s) of the batchmate at the specific `INDEX` in the __currently displayed list__. Refer to the section on _Notes about parameter_ at the start of [Features](#features) for more details.

Examples:
* `mod unmark 1 cs2103t` unmarks the module `cs2103t` of the 1st batchmate in the currently displayed list as `not taken`.
* `mod unmark 3 cs2100 cs2103t cs2101 cs2105` unmarks the modules `cs2100`, `cs2103t`, `cs2101` and `cs2105` of the 3rd batchmate in the currently displayed list as `not taken`.

### Exiting the program : `exit`

Exits the application.

Format: `exit`

### Saving the data

Data in Mass Linkers is saved in the hard disk automatically after executing any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Mass Linkers home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action            | Format, Examples                                                                                                                                                                 |
|-------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**          | `help`                                                                                                                                                                           |
| **Add**           | `add n/NAME t/TELEGRAM [g/GITHUB] [p/PHONE] [e/EMAIL] [tag/TAG]...` <br> Example: `add n/John Doe t/johnxyz g/john_doe p/98765432 e/johnd@example.com tag/friends tag/owesMoney` |
| **List**          | `list`                                                                                                                                                                           |
| **Edit**          | `edit INDEX [n/NAME] [t/TELEGRAM] [g/GITHUB] [p/PHONE] [e/EMAIL] [tag/TAG]...`<br> Example: `edit 1 g/john_doe p/91234567 e/johndoe@example.com`                                 |
| **Find by name**  | `find KEYWORD [MORE_KEYWORDS]...` <br> Example: `find alex david`                                                                                                                |
| **Find by tag**   | `findTag TAG [MORE_TAGS]...` <br> Example: `findTag friend classmate`                                                                                                            |
| **Delete**        | `delete INDEX` <br> Example: `delete 2`                                                                                                                                          |
| **Add module**    | `mod add INDEX MODULE [MORE_MODULES]...` <br> Example: `mod add 3 cs2100 cs2103t cs2101 cs2105`                                                                                  |
| **Delete module** | `mod delete INDEX MODULE [MORE_MODULES]...` <br> Example: `mod delete 3 cs2100 cs2103t cs2101 cs2105`                                                                            |
| **Mark module**   | `mod mark INDEX MODULE [MORE_MODULES]...` <br> Example: `mod mark 3 cs2100 cs2103t cs2101 cs2105`                                                                                |
| **Unmark module** | `mod unmark INDEX MODULE [MORE_MODULES]...` <br> Example: `mod unmark 3 cs2100 cs2103t cs2101 cs2105`                                                                            |
| **Exit**          | `exit`                                                                                                                                                                           |
