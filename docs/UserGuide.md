---
layout: page
title: User Guide
---

WorkBook (WB) is a **desktop app for CS/tech students who are applying for internships to manage their internship applications, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, WB can get your internship management tasks done faster than traditional GUI apps.


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed on your Computer.

2. Download the latest `WorkBook.v1.3.1.jar` from [here](https://github.com/AY2223S1-CS2103T-T10-3/tp/releases/tag/v1.3.1).

3. Copy the file to a folder of your choice to store data from your WorkBook.

4. Using your terminal, navigate to the folder where you placed your `jar` file and start WorkBook by running: `java -jar WorkBook.v1.3.1.jar`. <br> The GUI with sample data as shown below should appear in a few seconds.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `add c/Meta d/18-Oct-2022 12:00 e/hrmonkey@example.com r/Frontend` : Adds an internship from `Meta` to the WorkBook.

   * `delete 3` : Deletes the 3rd internship shown in the current list.

   * **`clear`** : Deletes all internships.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add c/COMPANY`, `COMPANY` is a parameter which can be used as `add c/Meta`.

* Items in square brackets are optional.<br>
  e.g `c/COMPANY [t/TAG]` can be used as `c/Meta t/unattainable` or as `c/Meta`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be omitted, used once: `t/unattainable`, or multiple times: `t/unattainable t/AWS`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/COMPANY s/STAGE`, `s/STAGE c/COMPANY` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `r/Frontend r/Backend`, only `r/Backend` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## Command summary

| Action     | Format, Examples                                                                                                                                                                                     |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add c/COMPANY r/ROLE s/STAGE [d/DATE] [e/COMPANY_EMAIL] [l/LANGUAGE TAG]… [t/TAG]…​` <br> e.g., `add c/Bytedance r/Backend Engineer s/Online Assessment d/24-Sep-2022 15:00 t/high pay l/Javascript` |
| **Edit**   | `edit INDEX [c/COMPANY] [d/DATE] [e/COMPANY_EMAIL] [r/ROLE] [l/LANGUAGE TAG]… [t/TAG]…​​`<br> e.g.,`edit 2 c/Meta e/hr@meta.com`                                                                                |
| **Find**   | `find c/COMPANY | r/ROLE | s/STAGE​​`<br> e.g.,`find c/Meta` 
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                  |
| **List**   | `list`                                                                                |
| **Undo** | `undo`                                                                                                                                                                  |
| **Redo** | `redo`                                                                                                                                                                  |
| **Clear**  | `clear`                                                                                                                                                                                              |
| **Help**   | `help`                                                                                                                                                                                               |
| **Exit**   | `exit`                                                                                                                                                                                               |

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

* Commands without examples are considered trivial and can be executed by inputting the command without additional parameters.

</div>

### Adding an internship application: `add`

Adds an internship application to WorkBook.

Format: `add c/COMPANY r/ROLE s/STAGE [d/DATE] [e/COMPANY_EMAIL] [l/LANGUAGE TAG] [t/TAG]…​`

* Adds an internship application to the list in sorted order.
  * Note: Internship applications **in the past** are placed at the bottom.
* Date can represent:
    * The date it happened
    * The deadline of the corresponding `Stage`
* Date, if included, must be properly formatted `DD-MMM-YYYY HH:mm` when inputted.
* Email domain, if included, must be properly formatted `example@domain.com`.
* Square-bracketed fields are optional.

Examples:
* `add c/Meta r/Frontend Engineer s/Application Sent d/29-Oct-2022 12:00 e/hrmonkey@example.com`
* `add c/Bytedance r/Backend Engineer s/Online Assessment d/24-Sep-2022 15:00 t/high pay l/Java l/Python`

### Editing a person : `edit`

Edits an existing person in the WorkBook.

Format: `edit INDEX [c/COMPANY] [r/ROLE] [s/STAGE] [d/DATE] [e/COMPANY_EMAIL] [l/LANGUAGE TAG] [t/TAG]…​`

* Edits the internship at the specified `INDEX`. The index refers to the index number shown in the displayed internships list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the internship will be removed i.e adding of tags is not cumulative.
* You can remove all the internship’s tags by typing `t/` without
    specifying any tags after it.

Examples:
* `edit 1 s/Behavioural Interview e/hr@meta.com` Edits the stage and email address of the first internship to be `Behavioural Interview` and `hr@meta.com` respectively.
* `edit 2 l/golang t/` Adds `golang` as the only language tag for the second internship and clears all existing tags.

### Listing all existing internship applications : `list`

Lists all internship applications in the Workbook.

Format: `list`

### Finds internships by company, stage or role name: `find`

Finds internships whose company, role or stage names contain any of the respective keywords.

Format: `find c/COMPANY | r/ROLE | s/STAGE`

* Displays a list of internships that match all the keywords.
* Case does not matter e.g. `meta` will match `Meta`.
* Only full words will be matched e.g. `met` will not match `Meta`.
* Order of the keywords does not matter e.g. `Jane Street` will match `Street Jane`.
* Exactly one attribute can be searched for either Company, Role or Stage.


Examples:
* `find r/Engineer` returns `Software Engineer` and `Backend Engineer`.
* `find s/Interview` returns `Technical Interview` and `Behavioural Interview`.

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

### Undoing previous command: `undo`

Restores the work book to the state before the previous <em>undoable</em> command was executed

Format: `undo`

Undoable commands: commands that modify the work book's content (`add`, `delete`, `edit` and `clear`)

Examples:
* `delete 1`

    `list`

    `undo` (reverses the `delete 1` command)


* `find google`

  `list`

  `undo` 

    The `undo` command fails as there are no undoable commands executed previously.

    
* `delete 1`
    
    `clear`

    `undo` (reverses the `clear` command)

    `undo` (reverses the `delete 1` command )

### Redoing previous command: `redo`

Reverses the most recent `undo` command.

Format: `redo`

Examples:
* `delete 1`

  `undo` (reverses the `delete 1` command)

  `redo` (reapplies the `delete 1` command)


* `delete 1`

  `redo`

  The `redo` command fails as there are no `undo` commands executed previously.


* `delete 1`

  `clear`

  `undo`(reverses the `clear` command)

  `undo`(reverses the `delete 1` command)

  `redo`(reapplies the `delete 1` command)

  `redo`(reapplies the `clear` command)

### Viewing help : `help`

Shows a summary of the commands as well as a link to this User Guide.

Format: `help`

Example:
* `help`

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

### Sorting internships `[coming soon]`

_Details coming soon ..._

### Daily Tips `[coming soon]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous WorkBook home folder.

--------------------------------------------------------------------------------------------------------------------


