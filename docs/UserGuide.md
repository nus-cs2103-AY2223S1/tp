---
layout: page
title: User Guide
---

<p align="center">
  <img src="./images/Coydir_Logo.png" />
  <h1 align="center"><font size="7">Coydir</font><br><em>The Ultimate Company Directory</em></h1>
</p> 

Welcome to Coydir's User Guide! 

**[Insert some catchy phrase here]**

---

### Table of Contents

- [Introduction](#introduction)
- [Using this Guide](#using-this-guide)
  - [Navigating the User Guide](#navigating-the-user-guide)
  - [The User Interface](#the-user-interface)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Coydir Commands Format](#coydir-commands-format)
  - [Managing Employee Profiles](#managing-employee-profiles)
    - [Adding an employee](#adding-an-employee--add)
    - [Editing an employee](#editing-an-employee--edit)
    - [Deleting an employee](#deleting-an-employee--delete)
    - [Adding multiple employees at once](#adding-multiple-employees-at-once--batchadd)
    - [View details of an employee](#view-details-of-an-employee--view)
    - [Listing all employees](#listing-all-employees--list)
    - [Finding an employee](#finding-an-employee--find)
  - [Managing Employee Leaves](#managing-employee-leaves)
    - [Setting total leaves for an employee](#setting-total-leaves-for-an-employee)
    - [Is this employee on leave today?](#is-this-employee-on-leave-today)
    - [Adding a leave period for an employee](#adding-a-leave-period-for-an-employee--addleave)
    - [Deleting a leave period for an employee](#deleting-a-leave-period-for-an-employee--deleteleave)
  - [Managing Employee Performance](#managing-employee-performance)
    - [Rating the performance of an employee](#rating-the-performance-of-an-employee--rate)
    - [Employee Performance History](#employee-performance-history)
  - [Managing Departments](#managing-departments)
    - [View details of a department](#view-details-of-a-department--view-department)
  - [Additional Features](#additional-features)
    - [Exiting the program](#exiting-the-program--exit)
    - [Clearing the data](#clearing-the-data--clear)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command summary](#command-summary)

---

## Introduction

Coydir is a desktop app to manage the employee details within a company, optimized for use via a Command Line Interface (CLI). Coydir would not only allow you to quickly access the list of all employees and their details but also make necessary updates based on the changes of the company structure.

## Using this Guide

### Navigating the User Guide

**[To be updated]**

### The User Interface

**[To be updated]**

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

With our powerful features, it has never been more convenient to perform HR tasks in companies of all sizes.

Coydir empowers you to take more control with less hassle, through data management and analytics features targeting 4 main fields:

1. [Employee particulars and details](#managing-employee-profiles)
2. [Employee leaves](#managing-employee-leaves)
3. [Employee performance](#managing-employee-performance)
4. [Department macro-view](#managing-departments)

Coydir also has other miscellaneous features for a smooth and comfortable use of the application, that we will cover in:

- [Additional Features](#additional-features)

We will explore each of these fields in great detail as we continue along, but before we get into that, let us take a look at how you can use the commands in Coydir's CLI-based interface.

### Coydir Commands Format

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

### Managing Employee Profiles

What's Human Resources without the Human aspect? (*P.S. It's not "Resources"*)

The **core** of any management tool is the **collection and tracking of data**. 
Recognising this, Coydir offers several features for the manipulation of data on your company's employees. 

As a *HR professional*, Coydir makes it easy for you to **add and remove data**, **change past entries**, and **look for exactly the information you need**. 
All this, while keeping your data packed neatly as individual employee profiles for a clean, organised viewing.

Next, let us look at each feature in detail.

#### Adding an employee: `add`

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

#### Editing an employee: `edit`

Edits the details of the person identified by the index number used in the displayed person list. Existing values will be overwritten by the input values.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [j/POSITION] [d/DEPARTMENT] [a/ADDRESS] [l/LEAVE] [t/TAG]…​`

Examples:

- `edit 1 p/91234567 e/johndoe@example.com`
- `edit 2 p/91234567 l/20 t/colleagues`

#### Deleting an employee : `delete`

Deletes the specified employee from Coydir, given the employee ID.

This command results in one of two cases below:

**Case 1: Unique entry**

If Coydir has just 1 unique entry that matches the specified name, Coydir will delete it.

**Case 2: Multiple entries**

Otherwise, if Coydir has more than 1 entry that matches the specified name, Coydir will prompt users with a list of the matching entries, and await user confirmation for which entry to delete.

Format: `delete ID`

Example:

- `delete 1` deletes the employee with employee ID of 1.

#### Adding multiple employees at once: `batchadd`

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

#### View details of an employee: `view`

Views the details of an existing employee in the current list.

Format: `view INDEX`

Example:

- `view 2` returns the details of the second employee in the current list.

<div markdown="span" class="alert alert-primary">:bulb:
Note that an INDEX is different from an ID. More information about the difference in INDEX and ID can be found on the FAQ page.
</div>

#### Listing all employees : `list`

Shows a list of all employees in the company.

Format: `list`

#### Finding an employee : `find`

Finds employees by searching for specified keywords. You can search for a specific name, position, or department, or with any combination of the aforementioned.

The keywords for searching are case-insensitive, and need not be full words. Running the command will then display all employees whose details match the specified keywords.

**Note**: There must be _at least one_ of the employees' particulars (and keywords) in the search.

Format: `find [n/NAME_KEYWORD] [j/POSITION_KEYWORD] [d/DEPARTMENT_KEYWORD]`

Example:

- `find n/John j/engineer d/Tech` displays the employee "John Doe", who is a "Software Engineer" in the "Information Technology" department.

### Managing Employee Leaves

Employee leave management can sometimes be a surprisingly *tedious* matter to tackle.

However, with Coydir, we can help you to ensure your **company operations run smoothly**, while your **employees get adequate opportunities** to rest and attend to personal matters.

Here is how we support you in leave management.

#### Setting total leaves for an employee

**[Insert description of using `add` and `edit` to set `totalLeave`]**

<h4 id="is-this-employee-on-leave-today">Is this employee on leave today?</h4>

**[Insert description of how Coydir enables live tracking of `isOnLeave`]**

#### Adding a leave period for an employee: `addleave`

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

#### Deleting a leave period for an employee: `deleteleave`

Removes a leave period for an employee given the employee ID and index of leave in the table.

This command results in one of two cases below:

**Case 1: Valid ID and index of leave**

If the employee exists, the index given is valid, the leave period at that index of the list of leaves will be removed for the particular employee.

**Case 2: Invalid ID, or index**

If the employee ID, or the index is invalid, Coydir will prompt the users accordingly, and the command will not execute.

Format: `deleteleave id/ID i/INDEX`

Example:

- `deleteleave id/1 i/3` deletes the third leave period for an employee of ID 1 in the list of leaves.

### Managing Employee Performance

Understanding the performance of your employees is crucial for *talent development* in the company.

Key performance indicators, productivity, and progression policies may differ from company to company. 
Here, Coydir offers a **simple yet powerful** way of keeping track of and making sense of your employees' performance in the company.

**[Insert a brief overview of the 1-5 rating system]**

#### Rating the performance of an employee: `rate`

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

#### Employee Performance History

**[Insert description of the performance history chart]**

### Managing Departments

Apart from supporting the core HR functions, Coydir also supports *department-level management*, for other manpower staff and executives (*department heads, operations planning, etc.*).

With these features, we offer a view that is broader than an individual employee profile and more specific than the full directory. 
Complete with **real-time statistics and analytics**, Coydir makes department management much simpler.

Currently, our application only provides a list of default departments for the user to choose from. Future update will include customizable department name. Our current list of valid departments are:
- Administration
- Board of Directors
- Customer Service
- Finance
- General Management
- Human Resources
- Information Technology
- Legal
- Marketing
- Operations
- Product Management
- Production
- Quality Assurance
- Research and Development
- Sales
- Technology

#### View details of a department: `view-department`

View the summarized details of a department given the name of the department.

Details include the number of employee in that particular department, employees who are currently available, employees who are currently on leave, and a table of employees in that department with their corresponding performance ratings and availability.

Format: `view-department DEPARTMENT`

Example:

- `view-department Finance` displays a brief summary of the Finance department's details on the right panel.

This command results in one of the two cases below:

**Case 1: Valid department name**

If the department exists, the right panel will correctly displays the summary of the department.

**Case 2: Invalid department name**

If the department name is invalid, the program will notify you through the result box, with a list of valid department name for easier reference.

<div markdown="span" class="alert alert-primary">:bulb:
DEPARTMENT is case-insensitive.
</div>

### Additional Features

To wrap up, let us take a look at a couple of additional features that give *a little boost* to your HR management on Coydir.
These features and techniques serve to make your usage of the application **smoother, easier, and more flexible**.

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Clearing the data : `clear`

Clears all the data currently stored in the database.

Format: `clear`

#### Saving the data

Coydir data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

Coydir data are saved as a JSON file `[JAR file location]/data/database.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Coydir will discard all data and start with an empty data file at the next run.
</div>

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Coydir home folder.

**Q**: What is the difference between an INDEX and an ID?<br>
**A**: INDEX is the numbering of current showing list of employees, while ID represents the **unique** employee id of the employee. 

**Q**: How do I change the rating of an employee?<br>
**A**: You can easily change the rating of an employee by using the `rate` command and adjust the RATING accordingly. Do note that it's impossible for you to change the past rating of an employee.

---

## Command summary

| Action              | Format, Examples                                                                                                                                                                                                                                 |
| ------------------- |--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**             | `add n/NAME p/PHONE e/EMAIL j/POSITION d/DEPARTMENT a/ADDRESS l/LEAVE [t/TAG]…​` <br> e.g. `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 l/14 t/criminal`                                                      |
| **Edit**            | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​add n/NAME p/PHONE e/EMAIL j/POSITION d/DEPARTMENT a/ADDRESS l/LEAVE [t/TAG]…​` <br> e.g. `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 l/14 t/criminal` |
| **Batch Add**       | `batchadd FILENAME` <br> e.g. `batchadd newemployees.csv`                                                                                                                                                                                        |
| **View Details**    | `view INDEX` <br> e.g. `view 1`                                                                                                                                                                                                                  |
| **Delete**          | `delete INDEX`<br> e.g. `delete 3`                                                                                                                                                                                                               |
| **Find**            | `find [n/NAME] [j/POSITION] [d/DEPARTMENT]`<br> e.g. `find n/John j/engineer d/Tech`                                                                                                                                                             |
| **Add leave**       | `addleave id/ID sd/START_DATE ed/END_DATE`<br> e.g. `addleave id/1 sd/01-01-2022 ed/02-01-2022`                                                                                                                                                  |
| **Delete leave**    | `deleteleave id/ID i/INDEX`<br> e.g. `deleteleave id/1 i/3`                                                                                                                                                                                      |
| **View Department** | `view-department DEPARTMENT`<br> e.g. `view-department Finance`                                                                                                                                                                                    |
| **rate**            | `rate id/ID r/RATING`<br> e.g. `rate id/1 r/3`                                                                                                                                                                                                   |
| **List**            | `list`                                                                                                                                                                                                                                           |
| **Exit**            | `exit`                                                                                                                                                                                                                                           |
| **Clear**           | `clear`                                                                                                                                                                                                                                          |
| **Help**            | `help`                                                                                                                                                                                                                                           |
