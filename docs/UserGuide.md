---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. **Introduction**

### 1.1 What is Class-ify?

Class-ify is a class management application built specially for Ministry of Education (MOE) teachers to easily monitor 
their students’ academic progress. Teachers can generate exam statistics for each class, and Class-ify quickly flags out
students who require more support for contacting.

### 1.2 Who is this guide for?

Are you a teacher who is looking to use Class-ify to manage your classes? Well, look no further! This user guide will
get you started in no time and be your guiding light as you navigate through Class-ify's features. For a quick start
guide, head over to [Quick Start](#2-quick-start) or to learn about Class-ify's features, head over to
[Features](#3-features) for more information.

## 2 How to use this user guide

Class-ify uses a command line interface, which may be new to some users. We strongly recommend new users to look through
the user guide thoroughly to understand how to use Class-ify. We have also included some links to navigate around the
user guide more easily for related information. This guide also contains some tips and cautions for you to take note of
when using our application. Additionally, we have provided a link back to the table of contents at the end of each
section. We suggest using our table of contents to jump around and access information that you need quickly.

### 2.1 Symbols
Before you begin reading, here are some special notations to help you along the way!

**Tips**

Tips are useful bits of information that will help you have a better experience with Class-ify.

<div markdown="span" class="alert alert-primary">:bulb:
**Tip:** Tips are useful!
</div><br>

**Notes**

Notes are here to provide you with extra information that you may find helpful when using Class-ify.

<div markdown="span" class="alert alert-info">:information_source:
**Note:** Take notes when you see this icon!
</div><br>

**Caution**

Cautions are around to warn you of potential pitfalls that new users may encounter. For example, commands like `clear`
will delete all data stored locally and this action is irreversible. You will lose your data permanently.

<div markdown="span" class="alert alert-warning">
:exclamation: Stop and read carefully when you see this!
</div><br>

### 2.2 Command format

*To be updated*

### 2.3 Command parameters

*To be updated*

--------------------------------------------------------------------------------------------------------------------

## 3. **Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `classify.jar` from [here](https://github.com/AY2223S1-CS2103T-T15-2/tp/releases).

3. Copy the file to the folder you want to use as the **home folder** for *Class-ify*.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`addstud`**`nm/John Doe id/928C class/1A pn/Bob Doe hp/98765432 exam/CA1 90` : Adds a new student named `Bob Doe` with his details to *Class-ify*.

   * **`viewAll`** : Shows a list of all student records.

   * **`delete`**`n/Jonathan Tan` : Deletes the student record with name of student as 'Jonathan Tan'.

   * **`clear`** : Clears all student records.

   * **`exit`** : Exits the application.

Click <a href="#top">here</a> to return to the top.

--------------------------------------------------------------------------------------------------------------------

## 4. **Features**

## 4.1 Managing student records

### 4.1.1 Adding a new student record : `addstud`

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

### 4.1.2 Clearing all student records : `clear`

Clears all student records from local storage.

Format: `clear`

### 4.1.3 Deleting a student record : `delete`

Deletes an existing student record from the class list, using the student’s name or the student’s ID.

Format: `delete nm/[name of student]` or `delete id/[id of student]`

Examples:
* `delete nm/Jonathan Tan` deletes student record with student name as 'Jonathan Tan'.
* `delete id/123A` deletes student record with student ID as '123A'.

### 3.4 Editing a student record : `edit`

Edits the respective details of an existing student in the class list.

Format: `edit [index] n/[name of student] id/[id of student] pn/[name of parent]…​`

<div markdown="span" class="alert alert-info">:information_source:
**Note:** 
   <ul>
      <li> Edits the person at the specified `index`. The index refers to the index number shown in the current displayed list. The index **must be a positive integer** 1, 2, 3, …​ </li>
      <li> Any tag can be used to edit the respective information. </li>
      <li> At least one tag must be provided. </li>
      <li> Existing values will be updated to the input values. </li>
   </ul>
</div><br>

Examples:
* `edit 1 pn/91234567 a/8 College Ave East` Edits the parent phone number and address of the 1st student to be `91234567` and `8 College Ave East` respectively.
* `edit 2 n/Jacob Teo` Edits the name of the 2nd student to be `Jacob Teo`.

## 4.2 Managing display of student records
<div markdown="span" class="alert alert-info">:information_source:
   **Note:**
   The default display, when starting the application, shows all student records in Classify.
   Commands related to managing display are not saved upon exiting the application.
</div>

### 4.2.1 Finding a student record : `find`

Shows a full record, including all stored details, using the input student's name or student's id. Search for multiple
students by including more keywords

Format: `view nm/[name]` or `view id/[id]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the name or the id is searched, depending on the given input.
* Only full names / full ids will be matched e.g. `Han` will not match `Hans`

Examples:
* `view nm/John` returns the record for the student named `john`
* `view nm/john alice` returns the records for the students named `John` and the student named `Alice`.

### 4.2.2 Toggling view : `toggleView`

Toggles the display between showing and hiding the students' parent details. 

Format: `toggleView`

*To be updated: insert screenshots*

<div markdown="span" class="alert alert-primary">:bulb:
   **Tip:** The default display renders the students' parent details as a reminder that these optional fields exists.    
</div>

### 4.2.3 Viewing all student records : `viewAll`

Shows a list of all student records in Classify.

Format: `viewAll`

### 4.2.4 Viewing student records from a class : `viewClass`

Shows a list of all students in the specified class.

Format: `viewClass [class name]`

<div markdown="span" class="alert alert-info">:information_source:
   **Note:** Class name is case-insensitive.
</div><br>

Examples:
* `viewClass 2A`
* `viewClass Loyalty1`

## 4.3 Exam statistics

### 4.3.1 Getting exam statistics: `statistics`

*To be updated* 

## 4.4 Miscellaneous

### 4.4.1 Exiting the application : `exit`

Exits the application.

Format: `exit`

### 4.4.2 Viewing help : `help`

Shows a summary of all commands available.

Format: `help`

### 4.4.3 Saving the data

Student records are saved locally after any command that changes the data. There is no need to save manually.

Click <a href="#top">here</a> to return to the top.

--------------------------------------------------------------------------------------------------------------------

## 5. **FAQ**

**Q**: Where can I locate my data file?  
**A**: You can locate the JSON file in the path `[JAR file location]/data/classify.json`.
We suggest that you **do not** edit the data file.
Class-ify will discard all data and start with an empty data file at the next run if the format of the data file is invalid.

**Q**: Why is Class-ify not running?  
**A**: Ensure Java`11` or above is installed on your computer.

Click <a href="#top">here</a> to return to the top.

--------------------------------------------------------------------------------------------------------------------

## 6. **Command summary**

|              Action               | Format                                                                                                                                                    | Example                                                                      |  
|:---------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------|
|     Add a new student record      | `addstud nm/[name of student] id/[id of student] class/[class of student] pn/[name of parent] hp/[mobile number of parent] exam/[name of exam and score]` | _addstud nm/Alex Yeoh id/123A class/1A pn/Bernice Yu hp/99272758 exam/CA1 90 |
|     View all student records      | `viewAll`                                                                                                                                                 | _viewAll_                                                                    |
| View student records from a class | `viewClass [class name]`                                                                                                                                  | _viewClass 1A_                                                               |
|       Edit a student record       | `edit [index] nm/[name] id/[id] class/[class] pn/[name of parent] hp/[mobile number of parent] exam/[name of exam and score]`                             | _edit 1 nm/Alexander Yeoh_                                                   |
|      Delete a student record      | `delete nm/[name]` or `delete id/[id]`                                                                                                                    | _delete nm/Jonathan Tan or delete id/123A_                                   |
|       Find a student record       | `find nm/[name]` or `find id/[id]`                                                                                                                        | _find nm/Jonathan Tan or find id/123A_                                       |
|            Toggle view            | `toggleView`                                                                                                                                              | _toggleView_                                                                 |
|     Clear all student records     | `clear`                                                                                                                                                   | _clear_                                                                      |
|    View command summary table     | `help`                                                                                                                                                    | _help_                                                                       |
|         Exit application          | `exit`                                                                                                                                                    | _exit_                                                                       |

Click <a href="#top">here</a> to return to the top.

--------------------------------------------------------------------------------------------------------------------

## 7. **Glossary**

* **CLI**: Command Line Interface (CLI) is a text-based User Interface (UI) used to run programs. Through the CLI,
users interact with the application by typing in text commands.
* **Student**: a person studying in a secondary school. A student typically takes up to 8 subjects and thus, may be
taught by up to 8 teachers.
* **MOE Teacher**: a person teaching a subject in a secondary school. A teacher usually teaches 3 to 5 classes. Each
class has roughly 20 students.

