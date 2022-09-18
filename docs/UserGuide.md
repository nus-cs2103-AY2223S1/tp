---
layout: page
title: User Guide
---

RC4HDB is a **desktop app for managing RC4 housing related information, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, RC4HDB can be a convenient and intuitive way to facilitate your day-to-day workflow as a RC4 housing management staff.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Viewing help : help](#viewing-help--help)
    * [Adding a resident : add](#adding-a-resident-add)
    * [Listing all residents : list](#listing-all-residents--list)
    * [Editing an existing resident : edit](#editing-an-existing-resident--edit)
    * [Locating residents by names : find](#locating-residents-by-name-find)
    * [Filtering residents by field : filter](#filtering-residents-by-field--filter)
    * [Deleting a resident : delete](#deleting-a-resident--delete)
    * [Clearing all entries : clear](#clearing-all-entries--clear)
    * [Exiting the program : exit](#exiting-the-program--exit)
    * [Saving data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
    * [Importing from csv file : import](#importing-from-csv-file--import)
    * [Exporting to csv file](#exporting-to-csv-file-export)
    * [CSV file format](#csv-file-format)
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

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message directing the user to our user guide.

<!---
![help message](images/helpMessage.png)
--->

Format: `help`


### Adding a resident: `add`

Adds a resident to the database.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL f/FLOOR u/UNIT [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 f/5 u/88` adds a resident named John Doe, with a phone number, floor and unit number.
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com f/12 u/10 p/1234567 t/criminal` adds a resident named Betsy Crowe, with a phone number, floor and unit number, and 2 tags, *friend* and *criminal*.

### Listing all residents : `list`

Shows a list of all residents in the RC4HDB database.

Format: `list`

### Editing an existing resident : `edit`

Edits the data of an existing resident in the RC4HDB database.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [f/FLOOR] [u/UNIT] [t/TAG]…​`

* Edits the resident at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 f/5 u/8` Edits the phone number, floor and unit number of the 1st person to be `91234567`, floor `5` and unit `8` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating residents by name: `find`

Finds residents whose names contain any of the given keywords.

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
  <!--- ![result for 'find alex david'](images/findAlexDavidResult.png) --->

### Filtering residents by field : filter

Shows a list of residents whose fields match the input keywords.

Format: `filter [/specifier] KEY:VALUE [ADDITIONAL_KEYS:ADDITIONAL VALUES]`

* The specifier dictates whether any or all fields must match, e.g. `filter /any house:Draco house:Aquila` will return
all residents belonging to both `Draco` and `Aquila`.
* On the other hand, `filter /all house:Draco gender:Male` will return all `male` residents belonging to `Draco`.
* Repeated keys are not permitted for `/all` specifier, e.g. `filter /all house:Draco house:Aquila` will not work.

Examples:
* `filter /all house:Draco gender:Male` returns Kelvin Lim, Brianna Toh, Vanessa  Sim, …
* `filter major:ComputerScience` returns Nicholas Seah, …

### Deleting a resident : `delete`

Deletes the specified resident from the RC4HDB database.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
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

RC4HDB data are saved as a JSON file `[JAR file location]/data/RC4data.json`. Advanced users are welcome to update data directly by editing that data file. Please do take note that this is not the recommended method to edit data.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RC4HDB will discard all data and start with an empty data file at the next run.
</div>

### Importing from CSV file : `import`

RC4HDB has the ability to import data through .csv files. In order for RC4HDB to find your files, place them in the data directory, `[JAR file location]/data`.

:information_source: The csv file that you want to have imported must follow this [format]().<br>

Format: `import FILENAME`

Examples:
* `import students.csv`
* `import residents.csv`

### Exporting to CSV file: `export`

RC4HDB has the ability to export data to .csv files. The file will be safe to remove from the directory, `[JAR file location]/data`.

:information_source: The csv file will be exported in this [format]().<br>

Format: `export FILENAME`

Examples:
* `export students.csv` will export the current data file into a csv file named `students.csv`

### CSV file format

| INDEX 	| NAME 	| PHONE NUMBER 	|    EMAIL      | FLOOR 	| UNIT NUMBER 	| TAGS 	|
|-------	|------	|--------------	| ------        | -------	|-------------	|------	|
| 1      	| John Doe | 91234567  	| johnDoe@gmail.com       | 05      	|  08         	| Male 	|
| 2      	| Maggie Smith	| 98765432	| maggieSmith@gmail.com        | 04   	|  01         	| Female|

<br>

<!---
### Archiving data files `[coming in v2.0]`

_Details coming soon ..._ --->

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: First, export the data from your computer using the export command and transfer the file to the new computer. Install RC4HDB on the other computer and use the import command to import the data from the csv file.

**Q**: Can I search using fields other than the name?
**A**: You can use the filter command to search for people using the other fields.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**List** | `list`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter [/specifier] KEY:VALUE [ADDITIONAL_KEYS:ADDITIONAL_VALUES]` <br> e.g., `filter /all house:Draco gender:Male`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Clear** | `clear`
**Exit** | `exit`
**Import** | `import FILENAME` <br> e.g., `import students.csv`
**Export** | `export FILENAME` <br> e.g., `export students.csv`





