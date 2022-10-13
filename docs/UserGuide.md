---
layout: page
title: User Guide
---

Coydir is a desktop app to manage the employee details within a company, optimized for use via a Command Line Interface (CLI). Coydir would not only allow you to quickly access the list of all employees and their details but also make necessary updates based on the changes of the company structure.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `coydir.jar` from [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Coydir.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>


5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all employees in the company.

   - **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a employee named `John Doe` to Coydir.

   - **`delete`**` 3` : Deletes the 3rd employee shown in the current list.

   - **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

Coydir v1.2 supports 3 user functions that allow for complete control of your company’s employee data.

**Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Adding an employee: `add`

Adds an employee to Coydir.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: 
An employee can have any number of tags (including 0)
</div>

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
- `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Adding multiple employees at once: `batchadd`

Adds multiple employees to Coydir all at once.

:warning: **Make sure to have uploaded CSV file to make use of this command, and that employees' fields are compatible with Coydir**: CSV file can be uploaded under the 'data' folder of Coydir.

This command results in one of two cases below:

**Case 1: CSV file exists**

if a CSV file of employees exists in the 'data' folder of Coydir, Coydir will read from the CSV file to add the employees.

**Case 2: CSV file does not exist**

if a CSV file does not exist in the 'data' folder of Coydir, Coydir will
prompt the user to upload it.

Format: `batchadd FILENAME`

Example:

- `batchadd employees.csv`

### Listing all employees : `list`

Shows a list of all employees in the company.

Format: `list`

### View details of an employee: `view`

Views the details of an existing employee in the current list.

Format: `view INDEX`

Example:

- `view 2` returns the details of the second employee in the current list.

### Deleting an employee : `delete`

Deletes the specified employee from Coydir, given the employee ID.

This command results in one of two cases below:

**Case 1: Unique entry**

If Coydir has just 1 unique entry that matches the specified name, Coydir will delete it.

**Case 2: Multiple entries**

Otherwise, if Coydir has more than 1 entry that matches the specified name, Coydir will prompt users with a list of the matching entries, and await user confirmation for which entry to delete.

Format: `delete ID`

Example:

- `delete 1` deletes the employee with employee ID of 1.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Coydir data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Coydir data are saved as a JSON file `[JAR file location]/data/coydir.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Coydir will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Coydir home folder.

---

## Command summary

| Action           | Format, Examples                                                                                                                                                      |
|------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**          | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Batch Add** | `batchadd FILENAME` <br> e.g. `batchadd newemployees.csv`                                                                                                                                                         |
| **View Details** | `view ID` <br> e.g. `view 1`                                                                                                                                                    |
| **Delete**       | `delete INDEX`<br> e.g. `delete 3`                                                                                                                                   |
| **List**         | `list`                                                                                                                                                                |
| **Help**         | `help`                                                                                                                                                                |
