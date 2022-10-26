---
layout: page
title: User Guide
---

HobbyList is a **desktop app for managing hobby activities, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HobbyList can get your activity management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `hobbylist.jar` from [here](https://github.com/AY2223S1-CS2103T-T12-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your HobbyList.

4. Double-click the file to start the app. The default GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/default_gui.jpg)

5. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:

    * **`list`** : Lists all hobby activities in HobbyList.

    * **`add`**`n/42km run d/NUS Run event [t/sport] [date/2022-09-30]` : Adds a hobby activity named `42km run` to the list.


    * **`delete`**`3` : Deletes the 3rd hobby activity shown in the current list.

    * **`exit`** : Exits the app.

6. Click the button located in the top left corner to execute certain task.<br>
   Some example buttons you can try:
    * **`File`** : To find the exit button.
    * **`Help`** : To look at a pop up window about more helping information.
    * **`Theme`** : To change different appearance of the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/42km run`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG] [date/DATE] ` can be used as `n/42km run t/sport` or as `n/42km run`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/sport`, `t/sport t/cardio` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME d/DESCRIPTION`, `d/DESCRIPTION n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/NUS Run d/NUS Biathlon`, only `d/NUS Biathlon` will be taken.

* The date format for `[date/DATE]` should be `yyyy-mm-dd`. e.g. `date/2022-12-25`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Getting help : `help`

Shows a message explaining how to access the help page.

Format: `help`.

### Adding a hobby activity : `add`

Adds a hobby activity to HobbyList.

Format: `add n/NAME d/DESCRIPTION [t/TAG]... [date/DATE]`

Examples:
* `add n/42km run d/NUS Run event t/sport`
* `add n/Badminton d/play badminton t/sport date/2022-10-19`

### Listing all hobby activities : `list`

Shows a list of all hobby activities in HobbyList.

Format: `list`

Example:
* `list`

### Finding a command with keywords: `find`

Shows a list of all hobby activities whose names or descriptions contain any of the specified keywords.

Format: `find KEYWORD`

* The keywords are case-insensitive.

Example:
* `find run`
* `find mystery novel`

### Finding activities by tags : `filter`

Shows a list of all hobby activities whose tags match the one specified in the command.

Format: `filter KEYWORD`

* The keywords are case-insensitive.

Example:
* `filter book`
* `filter anime`

### Editing an activity : `edit`

Edits a specified activity from HobbyList.

Format: `edit INDEX [n/NAME] [d/DESCRIPTION] [t/TAGS]... [date/DATE]`

* Edits the person at the specified INDEX. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the activity will be removed i.e. adding of tags is not cumulative.
* You can remove all the activity's tags by typing `t/` without specifying any tags after it.

Examples:

* `edit 1 n/A Clash of Kings d/Epic fantasy novel by George R. R. Martin.` Edits the name and description of the 1st activity to be `A Clash of Kings` and `Epic fantasy novel by George R. R. Martin.` respectively.
* `edit 2 date/2022-10-21 t/` Removes the tags of the second activity and sets the date to `2022-10-21`.

### Deleting an activity : `delete`

Deletes a specified activity from HobbyList.

Format: `delete INDEX`

* Deletes the activity at the specified `INDEX`.
* The index refers to the index number shown in the displayed activity list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:

* `delete 1`

### Deleting all activities: `clear`

Deletes all activities from HobbyList

Format: `clear`

Example:

* `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

Example:

* `exit`

### Renaming the commands

If you do not like the names of the command, you can select `preferences` in the menu bar and `edit aliases` to rename any of the commands.

### Saving the data

HobbyList data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HobbyList data are saved as a JSON file `[JAR file location]/data/hobbylist.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HobbyList will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HobbyList home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format                                                                                                                                             |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME d/DESCRIPTION [t/TAG]... [date/DATE]` <br/>e.g., `add n/poutine d/at some place t/food date/2022-09-25`                                | 
| **Clear**  | `clear`                                                                                                                                            |
| **Delete** | `delete INDEX`                                                                                                                                     |
| **Edit**   | `edit INDEX [n/NAME] [d/DESCRIPTION] [t/TAGS]... [date/DATE]`<br/>e.g., `edit 1 n/Bukit Timah Nature Reserve d/3 hour hike t/hike date/2022-08-17` |
| **Exit**   | `exit`                                                                                                                                             |
| **Find**   | `find KEYWORDS`                                                                                                                                    |
| **Filter** | `filter KEYWORD`                                                                                                                                   |
| **List**   | `list`                                                                                                                                             |
| **Help**   | `help`                                                                                                                                             |
