---
layout: page
title: User Guide
---

![Coydir Logo](./images/Coydir_Logo.png)

Coydir is a desktop app to manage the employee details within a company, optimized for use via a Command Line Interface (CLI). Coydir would not only allow you to quickly access the list of all employees and their details but also make necessary updates based on the changes of the company structure.

**Table of Contents**

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding an employee](#adding-an-employee-add)
  - [Adding multiple employees at once](#adding-multiple-employees-at-once-batchadd)
  - [Listing all employees](#listing-all-employees--list)
  - [View details of an employee](#view-details-of-an-employee-view)
  - [Deleting an employee](#deleting-an-employee--delete)
  - [Finding an employee](#finding-an-employee--find)
  - [Adding a leave period for an employee](#adding-a-leave-period-for-an-employee-addleave)
  - [Deleting a leave period for an employee](#deleting-a-leave-period-for-an-employee-deleteleave)
  - [View details of a department](#view-details-of-a-department-viewdepartment)
  - [Exiting the program](#exiting-the-program--exit)
  - [Clearing the data](#clearing-the-data--clear)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command summary](#command-summary)

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `coydir.jar` from [here](https://github.com/AY2223S1-CS2103T-T15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Coydir.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all employees in the company.

   - **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds an employee named `John Doe` to Coydir.

   - **`delete`**` 3` : Deletes employee with ID 3.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

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

:warning: **Integer input values cannot be too large**: any values greater than $2^{32}-1$ is not supported.

</div>

### Adding an employee: `add`

Adds an employee to Coydir.

Format: `add n/NAME p/PHONE e/EMAIL j/POSITION d/DEPARTMENT a/ADDRESS l/LEAVE [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb:
An employee can have any number of tags (including 0).
</div>

<div markdown="span" class="alert alert-primary">:bulb:
The only compulsory fields are name, position and department.
Leaves will be default 14 while the other fields will be initialised as N/A.
</div>

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com j/Recruiter d/Human Resources a/311, Clementi Ave 2, #02-25 l/20 t/friends t/owesMoney`
- `add n/Peter Mars j/Chief Operating Officer d/General Management`

### Editing an employee: `edit`

Edits the details of the person identified by the index number used in the displayed person list. Existing values will be overwritten by the input values.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [j/POSITION] [d/DEPARTMENT] [a/ADDRESS] [l/LEAVE] [t/TAG]…​`

Examples:

- `edit 1 p/91234567 e/johndoe@example.com`
- `edit 2 p/91234567 l/20 t/colleagues`

### Adding multiple employees at once: `batchadd`

Adds multiple employees to Coydir all at once.

:warning: **Make sure to have uploaded CSV file to make use of this command, and that employees' fields are compatible with Coydir**: CSV file can be uploaded under the 'data' folder of Coydir.

:warning: **Fields to be added in batchadd would be name, phone, email, position, department, address, tags, and total number of leaves**

:warning: **Do not have commas between each field in the CSV file.**

This command results in one of two cases below:

**Case 1: CSV file exists**

if a CSV file of employees exists in the 'data' folder of Coydir, Coydir will read from the CSV file to add the employees.

**Case 2: CSV file does not exist**

if a CSV file does not exist in the 'data' folder of Coydir, Coydir will
throw an error.

Format: `batchadd FILENAME`

Example:

- `batchadd employees.csv`

Sample CSV file as such:
![](./images/Sample_CSV.png)

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

### Finding an employee : `find`

Finds employees by searching for specified keywords. You can search for a specific name, position, or department, or with any combination of the aforementioned.

The keywords for searching are case-insensitive, and need not be full words. Running the command will then display all employees whose details match the specified keywords.

**Note**: There must be _at least one_ of the employees' particulars (and keywords) in the search.

Format: `find [n/NAME_KEYWORD] [j/POSITION_KEYWORD] [d/DEPARTMENT_KEYWORD]`

Example:

- `find n/John j/engineer d/Tech` displays the employee "John Doe", who is a "Software Engineer" in the "Information Technology" department.

### Adding a leave period for an employee: `addleave`

Adds a leave period to an employee given the employee ID and a start and end date.

This command results in one of two cases below:

**Case 1: Valid ID, date and sufficient leaves**

If the employee exists, the leave date given is valid,
and the employee has sufficient leaves, the leave period will be added and the total leaves remaining will be deducted accordingly.

**Case 2: Invalid ID, date or insufficient leaves**

If any of employee ID, date is invalid, or the employee does not possess enough leaves, Coydir will prompt the users accordingly, and the command will not execute.

<div markdown="span" class="alert alert-primary">:bulb:
Leaves are ordered by reverse chronological order.
</div>

Format: `addleave id/ID sd/START_DATE ed/END_DATE`

Example:

- `addleave id/1 sd/01-01-2022 ed/02-01-2022` adds a leave period to an employee of ID 1 with a leave period from 01-01-2022 to 02-01-2022 of 2 days.

### Deleting a leave period for an employee: `deleteleave`

Removes a leave period for an employee given the employee ID and index of leave in the table.

This command results in one of two cases below:

**Case 1: Valid ID and index of leave**

If the employee exists, the index given is valid, the leave period at that index of the list of leaves will be removed for the particular employee.

**Case 2: Invalid ID, or index**

If the employee ID, or the index is invalid, Coydir will prompt the users accordingly, and the command will not execute.

Format: `deleteleave id/ID i/INDEX`

Example:

- `deleteleave id/1 i/3` deletes the third leave period for an employee of ID 1 in the list of leaves.

### View details of a department: `viewdepartment`

View the summarized details of a department given the name of the department.

Details include the number of employee in that particular department, employees who are currently available, employees who are currently on leave, and a table of employees in that department and their corresponding performance ratings.

Format: `viewdepartment DEPARTMENT`

Example:

- `viewdepartment Finance` displays a brief summary of the Finance department's details on the right panel.

### Rating the performance of an employee: `rate`

Rate the performance of an employee given the employee ID and a numeric rating.
Ratings can take any values from 1 - 5, and it should not be blank.

The numeric representation of the ratings follows as such:
5: Outstanding | 4: Exceeds Expectations | 3: Satisfactory | 2: Needs Improvement | 1: Unsatisfactory

This command results in one of two cases below:

**Case 1: Valid ID and rating**

If the employee exists, the rating given is valid (is a number from 1-5 inclusive), the performance rating will be added and the performance field of the employee will be updated accordingly.

**Case 2: Invalid ID or rating**

If any of employee ID, the rating given is invalid (is not a number from 1-5 inclusive), Coydir will prompt the users accordingly, and the command will not execute.

Format: `rate id/ID r/RATING`

Example:

- `rate id/1 r/3` adds a performance rating to an employee of ID 1 with a rating of 3: Satisfactory.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Clearing the data : `clear`

Clears all the data currently stored in the database.

Format: `clear`

### Saving the data

Coydir data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Coydir data are saved as a JSON file `[JAR file location]/data/coydir.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Coydir will discard all data and start with an empty data file at the next run.
</div>

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Coydir home folder.

---

## Command summary

| Action              | Format, Examples                                                                                                                                                                                                                                 |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Add**             | `add n/NAME p/PHONE e/EMAIL j/POSITION d/DEPARTMENT a/ADDRESS l/LEAVE [t/TAG]…​` <br> e.g. `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 l/14 t/criminal`                                                      |
| **Edit**            | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​add n/NAME p/PHONE e/EMAIL j/POSITION d/DEPARTMENT a/ADDRESS l/LEAVE [t/TAG]…​` <br> e.g. `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 l/14 t/criminal` |
| **Batch Add**       | `batchadd FILENAME` <br> e.g. `batchadd newemployees.csv`                                                                                                                                                                                        |
| **View Details**    | `view ID` <br> e.g. `view 1`                                                                                                                                                                                                                     |
| **Delete**          | `delete INDEX`<br> e.g. `delete 3`                                                                                                                                                                                                               |
| **Find**            | `find [n/NAME] [j/POSITION] [d/DEPARTMENT]`<br> e.g. `find n/John j/engineer d/Tech`                                                                                                                                                             |
| **Add leave**       | `addleave id/ID sd/START_DATE ed/END_DATE`<br> e.g. `addleave id/1 sd/01-01-2022 ed/02-01-2022`                                                                                                                                                  |
| **Delete leave**    | `deleteleave id/ID i/INDEX`<br> e.g. `deleteleave id/1 i/3`                                                                                                                                                                                      |
| **View Department** | `viewdepartment DEPARTMENT`<br> e.g. `viewdepartment Finance`                                                                                                                                                                                    |
| **rate**            | `rate id/ID r/RATING`<br> e.g. `rate id/1 r/3`                                                                                                                                                                                                   |
| **List**            | `list`                                                                                                                                                                                                                                           |
| **Exit**            | `exit`                                                                                                                                                                                                                                           |
| **Clear**           | `clear`                                                                                                                                                                                                                                          |
| **Help**            | `help`                                                                                                                                                                                                                                           |
