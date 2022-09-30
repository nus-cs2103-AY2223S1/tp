---
layout: page
title: User Guide
---

WorkBook (WB) is a **desktop app for CS/tech students who are applying for internships to manage their internship applications, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, WB can get your internship management tasks done faster than traditional GUI apps.


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `workbook.jar` from [here](https://github.com/AY2223S1-CS2103T-T10-3/tp).

3. Copy the file to the folder you want to use as the _home folder_ for your WorkBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`add`**`c/Meta d/2022-09-15 e/hrmonkey@example.com r/Frontend` : Adds an internship from `Meta` to the WorkBook.

   * **`delete`**`3` : Deletes the 3rd internship shown in the current list.

   * **`clear`** : Deletes all internships.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add c/COMPANY`, `COMPANY` is a parameter which can be used as `add c/Meta`.

* Items in square brackets are optional.<br>
  e.g `c/COMPANY [t/TAG]` can be used as `c/Meta t/unattainable` or as `c/Meta`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/unattainable`, `t/unattainable t/AWS` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/COMPANY p/PHONE_NUMBER`, `p/PHONE_NUMBER c/COMPANY` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an internship application: `add`

Adds an internship application to WorkBook.

Format: `add c/COMPANY d/DATE_APPLIED e/COMPANY_EMAIL r/ROLE [t/TAG]…​`

* Adds an internship application to the next index.
* Date must be properly formatted (YYYY-MM-DD).
* Email domain must be properly formatted (example@domain.com).
* An application can have any number of tags (including 0).

Examples:
* `add c/Meta d/2022-09-15 e/hrmonkey@example.com r/Frontend`
* `add c/Bytedance d/2022-04-20 e/bytehr@dance.com r/Backend t/high pay`

### Editing a person : `edit`

Edits an existing person in the WorkBook.

Format: `edit INDEX [c/COMPANY] [p/PHONE] [e/EMAIL] [r/ROLE] [t/TAG]…​`

* Edits the internship at the specified `INDEX`. The index refers to the index number shown in the displayed internships list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the internship will be removed i.e adding of tags is not cumulative.
* You can remove all the internship’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/hrmonkey@example.com` Edits the phone number and email address of the first internship to be `91234567` and `hrmonkey@example.com` respectively.
*  `edit 2 c/Google t/` Edits the company of the 2nd internship to be `Google` and clears all existing tags.

### Deleting an internship application : `delete`

Deletes the specified internship application from WorkBook.

Format: `delete INDEX`

* Deletes the internship application at the specified `INDEX`.
* The index refers to the index number shown in the displayed internship application list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd internship application in WorkBook.
* `find Meta` followed by `delete 1` deletes the 1st internship application within the results of the `find` command.

### Clearing all existing internship applications : `clear`

Clears all internship applications in the Workbook.

Format: `clear`

Example:
* `clear` removes all internship applications in the workbook.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

WorkBook data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

WorkBook data is saved as a JSON file [JAR file location]/data/workbook.json. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, WorkBook will discard all data and start with an empty data file at the next run.
</div>


### Ranking internships `[coming soon]`

_Details coming soon ..._

### Filtering internships `[coming soon]`

_Details coming soon ..._

### Sorting internships `[coming soon]`

_Details coming soon ..._

### Daily Tips `[coming soon]`

_Details coming soon ..._

### Toggling Light and Dark mode `[coming soon]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous WorkBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add c/COMPANY d/YYYY-MM-DD e/EMAIL r/ROLE [t/TAG]…​` <br> e.g., `add c/Meta d/2022-09-15 e/hrmonkey@example.com r/Frontend`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [c/COMPANY] [d/YYYY-MM-DD] [e/EMAIL] [r/ROLE] [t/TAG]…​​`<br> e.g.,`edit 2 c/META e/hrmonkey@example.com`
**Help** | `help`
**Exit** | `exit`
