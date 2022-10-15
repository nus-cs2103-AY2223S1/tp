---
layout: page
title: User Guide
---

RC4HDB is a **desktop app for managing RC4 housing related information, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, RC4HDB can be a convenient and intuitive way to facilitate your day-to-day workflow as a RC4 housing management staff.

## Table of Contents

* [Quick Start](#quick-start)
* [Features](#features)
    * [Viewing help : help](#viewing-help--help)
    * [Adding a resident : add](#adding-a-resident--add)
    * [Listing all residents : list](#listing-all-residents--list)
    * [Editing an existing resident : edit](#editing-an-existing-resident--edit)
    * [Locating residents by names : find](#locating-residents-by-name--find)
    * [Filtering residents by field : filter](#filtering-residents-by-field--filter)
    * [Deleting a resident : delete](#deleting-a-resident--delete)
    * [Clearing all entries : clear](#clearing-all-entries--clear)
    * [Exiting the program : exit](#exiting-the-program--exit)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
    * [Importing from csv file : import](#importing-from-csv-file--import)
    * [Exporting to csv file : export](#exporting-to-csv-file--export)
    * [CSV file format](#csv-file-format)
    * [Format for resident fields](#format-for-resident-fields)
* [Frequently Asked Questions](#faq)
* [Command Summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `rc4hdb.jar` from [here](https://github.com/AY2223S1-CS2103T-W12-3/tp/releases/).

1. Copy the file to the folder you want to use as the _home folder_ for your RC4HDB.

1. Refer to the [Features](#features) below for details of on how to use RC4HDB.

<!--- 1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
![Ui](images/Ui.png) --->


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

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken, unless otherwise specified.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a summary of all commands, and examples of its usage, as well as a message directing the user to our user guide.

<!---
![help message](images/helpMessage.png)
--->

Format: `help`


### Adding a resident : `add`

Adds a resident to the database. The format for resident fields can be found [here](#format-for-resident-fields).

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL r/FLOOR-UNIT g/GENDER h/HOUSE m/MATRIC_NUMBER [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A resident can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnDoe@gmail.com r/5-1 g/M h/D m/A9876543B` adds a resident named John Doe, with relevant personal and student information.
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com r/2-3 p/1234567 m/A3456789B g/F h/A` adds a resident named Betsy Crowe, with relevant personal and student information.

### Listing all residents : `list`

Shows a list of all residents in the RC4HDB database. Use the specifiers `/i` for include and `/e` for exclude, followed by the field names of the columns to show or hide. All field names entered are case-insensitive.

Format:

`list` to list all residents in the database with all fields visible

`list /i [FIELD_1] [FIELD_2]...` to list all residents in the database with only `[FIELD_1] [FIELD_2]...` visible.

`list /e [FIELD_1] [FIELD_2]...` to list all residents in the database with all fields visible except `[FIELD_1] [FIELD_2]...`

### Editing an existing resident : `edit`

Edits the data of an existing resident in the RC4HDB database.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/FLOOR-UNIT] [g/GENDER] [h/HOUSE] [m/MATRIC_NUMBER] [t/TAG]…​`

* Edits the resident at the specified `INDEX`. The index refers to the index number shown in the displayed residents list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the resident will be removed i.e adding of tags is not cumulative.
* You can remove all the resident’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 r/5-8` Edits the phone number, room number of the 1st resident to be `91234567`, and `5-8` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd resident to be `Betsy Crower` and clears all existing tags.

### Locating residents by name : `find`

Finds residents whose names contain any of the given keywords.

Format: `find NAME [ADDITIONAL_NAMES]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Full and partial words will be matched e.g. `Han` will match `Hans`
* Residents matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`
* `find char li` returns `Charlotte Oliveiro`, `David Li`<br>
  <!--- ![result for 'find alex david'](images/findAlexDavidResult.png) --->

### Filtering residents by field : `filter`

Shows a list of residents whose fields match the input keywords.

Format: `filter KEY/VALUE [ADDITIONAL_KEYS/ADDITIONAL_VALUES]`
* The fields have to be the same (no substrings allowed) for the resident to be filtered.
* Commands with multiple fields require the resident to match all the fields to be filtered.
* Valid keys are those included [here](#format-for-resident-fields), and any additional tags.

Examples:
* `filter h/D g/M` returns residents who are in Draco house, **and** are Male.
* `filter g/M` returns residents who are male.

### Deleting a resident : `delete`

Deletes the specified resident from the RC4HDB database.

Format: `delete INDEX`

* Deletes the resident at the specified `INDEX`.
* The index refers to the index number shown in the displayed resident list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd resident in the database.
* `find Betsy` followed by `delete 1` deletes the 1st resident in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the database.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

RC4HDB data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

RC4HDB data are saved as a JSON file `[JAR file location]/data/FILE_NAME.json`, where `FILE_NAME` is the name of the current data file. Advanced users are welcome to update data directly by editing that data file.

:information_source: Do take note that this is not the recommended method to edit data.<br>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RC4HDB will discard all data and start with an empty data file at the next run.
</div>

### Importing from CSV file : `import`

RC4HDB has the ability to import data through .csv files. In order for RC4HDB to find your files, place them in the data directory, `[JAR file location]/data`.

Format: `import FILENAME`

:information_source: The csv file that you want to have imported must follow this [format](#csv-file-format).<br>

Examples:
* `import students.csv`
* `import residents.csv`

### Exporting to CSV file : `export`

RC4HDB has the ability to export data to .csv files. The file will be safe to remove from the directory, `[JAR file location]/data`.

Format: `export FILENAME`

:information_source: The csv file will be exported in this [format](#csv-file-format).<br>

Examples:
* `export students.csv` will export the current data file into a csv file named `students.csv`

### CSV file format

| INDEX | NAME         | PHONE_NUMBER | EMAIL                 | FLOOR-UNIT | GENDER | HOUSE  | MATRIC_NUMBER | TAGS   |
|-------|--------------|--------------|-----------------------|------------|--------|--------|---------------|--------|
| 1     | John Doe     | 91234567     | johnDoe@gmail.com     |    5-8     | M      | D      | A9876543B     | -      |
| 2     | Maggie Smith | 98765432     | maggieSmith@gmail.com |    4-1     | F      | A      | A3456789B     | Friend |

<br>


### Format for resident fields

`n/NAME`
* Must be a string
* Spaces are allowed

`p/PHONE_NUMBER`
* Must be an **8**-digit non-negative integer

`e/EMAIL`
* Can be any string, valid or invalid email

`r/FLOOR-UNIT`
* The floor number and unit number must be separated by a hyphen
* Both floor and unit number must be a non-negative integer
* e.g. `5-8`

`g/GENDER`
* `M` or `F`

`h/HOUSE`
* Represents the RC4 house that the resident is allocated to
* Must be either `D`, `U`, `L`, `A`, `N`
* `D` stands for **Draco**, `U` for **Ursa**, `L` for **Leo**, `A` for **Aquila**, `N` for **Noctua**

`m/MATRIC_NUMBER`
* Must be an uppercase `A`, followed by a **7**-digit non-negative integer and an uppercase alphabet.
* e.g. `A0123456A`

`t/TAG`
* Represents any other key that could be used to identify a resident
* Must be a string. No restrictions on formatting
* Optional. A resident can have any number of tags, including 0
* When editing tags, the existing tags of the resident will be removed i.e adding of tags is not cumulative.
* You can remove all the resident’s tags by typing `t/` without specifying any tags after it.

_More details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: First, export the data from your computer using the export command and transfer the file to the new computer. Install RC4HDB on the other computer and use the import command to import the data from the csv file.

**Q**: Can I search using fields other than the name?<br>
**A**: You can use the filter command to search for people using the other fields.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL r/FLOOR-UNIT g/GENDER h/HOUSE m/MATRIC_NUMBER [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com r/2-1 g/M h/D m/A9876543B`
**List** | `list`, `list /i [FIELD_1] [FIELD_2]...`, or `list /e [FIELD_1] [FIELD_2]...` e.g., `list /i name phone address`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/FLOOR-UNIT] [g/GENDER] [h/HOUSE] [m/MATRIC_NUMBER] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter [/specifier] KEY:VALUE [ADDITIONAL_KEYS:ADDITIONAL_VALUES]` <br> e.g., `filter /all h/D g/M`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Clear** | `clear`
**Exit** | `exit`
**Import** | `import FILENAME` <br> e.g., `import students.csv`
**Export** | `export FILENAME` <br> e.g., `export students.csv`


---



