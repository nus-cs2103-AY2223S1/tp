---
layout: page
title: User Guide
---

Class-ify is a class management desktop application for teachers to **view and organize students’ details and class progress via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, and struggle with managing all your students' information, Class-ify is for you.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `classify.jar` from [here](https://github.com/AY2223S1-CS2103T-T15-2/tp/releases).

1. Copy the file to the folder you want to use as the **home folder** for *Class-ify*.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`viewAll`** : Lists all student records.

   * **`addstud`**`nm/John Doe id/928C pn/Bob Doe hp/98765432 ad/311, Clementi Ave 2, #02-25` : Adds a new student named `Jonathan Tan` with his details to *Class-ify*.

   * **`delete`**`n/Jonathan Tan` : Deletes the student record with name of student as 'Jonathan Tan'.

   * **`clear`** : Deletes all student records.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Creating a new student record : `addstud`

Creates a new student record with the name of the student and other relevant details such as:

* Student ID (last 4 digits of NRIC)
* Name of Parent
* Home Address
* Mobile Number of Parent
* Tags (Optional)

Format: `addstud nm/[name of student] id/[id of student] pn/[name of parent] hp/[mobile number of parent] ad/[home address] [t/[tags]]`

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
All fields are compulsory and except for the tag field. A student can have multiple tags.
</div>

Examples:
* `addstud nm/John Doe id/928C pn/Bob Doe hp/98765432 ad/311, Clementi Ave 2, #02-25 t/Peanut-Allergy t/No-Seafood`
* `addstud nm/Alex Yeoh id/123A pn/Bernice Yu hp/99272758 ad/Blk 30 Lorong 3 Serangoon Gardens, #07-18`

### Listing all students : `viewAll`

Shows a list of all students in the class.
For each student in the list, only the Student's Name and Student's ID are displayed.

:bulb: Note: The other details of a student are hidden to reduce cluttering.
To view the full record of a student, use the `view` command instead.   

Format: `viewAll`

### Editing a student : `edit`

Edits the respective details of an existing student in the class list.

Format: `edit [index] n/[name of student] id/[id of student] pn/[name of parent]…​`

:bulb: Note:
* Edits the person at the specified `index`. The index refers to the index number shown in the current displayed list. The index **must be a positive integer** 1, 2, 3, …​
* Any tag can be used to edit the respective information.
* At least one tag must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 pn/91234567 a/8 College Ave East` Edits the parent phone number and address of the 1st student to be `91234567` and `8 College Ave East` respectively.
*  `edit 2 n/Jacob Teo` Edits the name of the 2nd student to be `Jacob Teo`.

### Listing a single student: `view`

Shows a full record, including all stored details, using the input student's name or student's id. Search for multiple 
students by including more keywords

Format: `view nm/[name]` or `view id/[id]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the name or the id is searched, depending on the given input.
* Only full names / full ids will be matched e.g. `Han` will not match `Hans`

Examples:
* `view nm/John` returns the record for the student named `john`
* `view nm/john alice` returns the records for the students named `John` and the student named `Alice`.

### Deleting individual student record : `delete`

Deletes an existing student record from the class list, using the student’s name or the student’s ID.

Format: `delete nm/[name of student]` or `delete id/[id of student]`

Examples:
* `delete nm/Jonathan Tan` deletes student record with student name as 'Jonathan Tan'.
* `delete id/123A` deletes student record with student ID as '123A'.

### Clearing all student records : `clear`

Clears all student records from local storage.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Student records are saved locally after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: Where can I locate my data file?  
**A**: You can locate the JSON file in the path `[JAR file location]/data/classify.json`.
We suggest that you **do not** edit the data file.
Class-ify will discard all data and start with an empty data file at the next run if the format of the data file is invalid.

**Q**: Why is Class-ify not running?  
**A**: Ensure Java`11` or above is installed on your computer.

--------------------------------------------------------------------------------------------------------------------

## Command summary

|                 Action                | Format                                                                                                                          | Example                                                                                                |  
|:-------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------|
|         Add individual student        | `addstud nm/[name of student] id/[id of student] pn/[name of parent] hp/[mobile number of parent] ad/[home address] [t/[tags]]` | _addstud nm/Alex Yeoh id/123A pn/Bernice Yu hp/99272758 ad/Blk 30 Lorong 3 Serangoon Gardens, #07-18_  |
|      View all student information     | `viewAll`                                                                                                                       | _viewAll_                                                                                              |
|  View individual student information  | `view nm/[name]` or `view id/[id]`                                                                                              | _view nm/Jonathan Tan_                                                                                  |
| Update individual student information | `update [index] nm/[name] id/[id] pn/[name of parent] a/[home address] hp/[mobile number of parent]`                            | _update 1 a/Kent Ridge View_                                                                           |
|       Delete individual student       | `delete nm/[name]` or `delete id/[id]`                                                                                           | _delete nm/Jonathan Tan or delete id/123A_                                                            |
|                  Exit                 | `exit`                                                                                                                          | _exit_                                                                                                 |

                                                                                                                              
