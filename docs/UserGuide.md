---
layout: page
title: User Guide
---

Mass Linkers is a powerful Desktop application tool that enables CS2103T students in a tutorial group to search for their classmates, view their teammates’ repos and view each other’s code reviews. It is optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `masslinkers.jar` from [here](https://github.com/AY2223S1-CS2103T-T11-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Mass Linkers.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`help`** : Shows a brief summary of commands with their syntax and a link to the user guide.

   * **`add`**`n/John Doe g/https://github.com/johndoe` : Adds a contact named `John Doe` to the application.

   * **`list`** : Lists all classmates.

   * **`delete`**`3` : Deletes the 3rd classmate shown in the current list.

   * **`exit`** : Exits the application.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/team4` or as `n/John Doe`.

</div>

### Viewing help: `help`

Shows a brief summary of commands with their syntax and a link to the user guide.

Format: `help`


### Adding a classmate: `add`

Adds a classmate to the application.

Format: `add n/NAME g/GITHUB_REPO_LINK [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]`

Examples:
* `add n/John Doe g/https://github.com/johndoe`
* `add n/John Doe g/https://github.com/johndoe t/team1 p/98765432 e/johnd@example.com`

### Listing all classmates: `list`

Shows a list of all classmates.

Format: `list`

### Editing a classmate: `edit`

Edits the information of a specified classmate.

Format: `edit INDEX [n/NAME] [g/GITHUB_REPO_LINK] [p/PHONE] [e/EMAIL] [t/TAG]`

* Edits the classmate at the specific `INDEX`, which refers to the index number shown in the displayed contact list. The index **must be a positive integer**, e.g. 1, 2, 3 … and be smaller than the number of classmates in the list.
* At least one of the optional fields must be provided.

Examples:
*  `edit 1 n/Bob Tan` Edits the name of the 1st contact to be `Bob Tan`.
*  `edit 2 g/https://github.com/bobtan p/91234567 t/team1` Edits the github repo link, phone number and tag of the 2nd person to be `https://github.com/bobtan`, `91234567` and `team1` respectively.

### Sorting by tags: `sort`

Displays all classmates (arranged in alphabetical order) with a specified tag.

Format: `sort TAG`

Examples:
* `sort team4`

### Finding a classmate: `find`

Finds classmates whose names contain any of the given keywords.

Format: `find KEYWORD`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Tan`, `David Chua`<br>

### Deleting a classmate : `delete`

Deletes a classmate of a specific index.

Format: `delete INDEX`

* Deletes the classmate at the specific `INDEX`, which refers to the index number shown in the displayed contact list. The index **must be a positive integer**, e.g. 1, 2, 3 … and be smaller than the number of classmates in the list.

Examples:
* `delete 2` deletes the 2nd person in the list.

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

| Action     | Format, Examples                                                                                                                                                      |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**   | `help`                                                                                                                                                                |
| **Add**    | `add n/NAME g/GITHUB_REPO_LINK [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]` <br> Example: `add n/John Doe g/https://github.com/johndoe t/team1 p/98765432 e/johnd@example.com` |
| **List**   | `list`                                                                                                                                                                |
| **Edit**   | `edit INDEX [n/NAME] [g/GITHUB_REPO_LINK] [p/PHONE] [e/EMAIL] [t/TAG]`<br> Example: `edit 2 g/https://github.com/bobtan p/91234567 t/team1`                           |
| **Sort**   | `sort TAG` <br> Example: `sort team4`                                                                                                                                 |
| **Find**   | `find KEYWORD`<br> Example: `find alex David`                                                                                                                         |
| **Delete** | `delete INDEX`<br> Example: `delete 2`                                                                                                                                |
| **Exit**   | `exit`                                                                                                                                                                |
