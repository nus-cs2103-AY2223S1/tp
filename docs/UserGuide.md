---
layout: page
title: User Guide
---
<div style="text-align:center;">
<img src="images/classifyLogo.png">
</div>

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **1 Introduction**

### 1.1 What is Class-ify?

Class-ify is a **class management application** built specially for **Ministry of Education (MOE) teachers** to 
**monitor their student's academic progress easily**. Teachers can **generate exam statistics** for each class, 
and Class-ify quickly **flags out students** who require more support for contacting.

### 1.2 Who is this guide for?

Are you a teacher looking to use Class-ify to manage your classes? Well, look no further! This user guide will
get you started in no time and be your guiding light as you navigate through Class-ify's features. For a quick start
guide, head over to [Quick Start](#3-quick-start) or to learn about Class-ify's features, head over to
[Features](#4-features) for more information.

## **2 How to use this user guide**

Class-ify uses a Command Line Interface (CLI), which may be new to some users. If you are a new user, we strongly recommend you to look through
the user guide from start to end to fully understand how to use Class-ify. However, you may also choose to skip to the relevant sections described below:
* Refer to our <a href="#top">Table of Contents</a> to easily navigate between sections of the User Guide. There is also a link at the end of every section to bring you back to the Table of Contents.
* Refer to our [Quick Start](#3-quick-start) guide to learn how to set-up Class-ify.
* Refer to our [Features](#4-features) section to learn in detail the different features and commands available in Class-ify.
* Refer to our [Command Summary](#6-command-summary) to have a quick overview of the different commands and their respective formats.
* Refer to our [Glossary](#7-glossary) to learn key terms that are used in this User Guide.

--------------------------------------------------------------------------------------------------------------------

## **3 Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `classify.jar` from [here](https://github.com/AY2223S1-CS2103T-T15-2/tp/releases).

3. Copy the file to the folder you want to use as the **home folder** for *Class-ify*.

4. Double-click the file to start the app. The Graphical User Interface (GUI) similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`addStudent`**`nm/John Doe id/928C class/1A pn/Bob Doe hp/98765432 exam/CA1 90` : Adds a new student named `John Doe` with his details to *Class-ify*.

   * **`viewAll`** : Shows a list of all student records.

   * **`delete`**`n/Jonathan Tan` : Deletes the student record with name of student as 'Jonathan Tan'.

   * **`clear`** : Clears all student records.

   * **`exit`** : Exits the application.

Click <a href="#top">here</a> to return to the top.

--------------------------------------------------------------------------------------------------------------------

## **4 Features**
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
</div>

<div markdown="block" class="alert alert-info">:information_source:
**Notes on the Command Format:** <br>

* **Command Words**
  * Command words and prefixes are case-sensitive. <br>
  e.g. `eXit` will not be accepted as the `exit` command.
  * Only the last occurrence of a repeated prefix input will be taken.
  e.g. `edit 1 nm/Jonathan nm/Ethan nm/Alice` is the same as `edit 1 nm/Alice`.
* **Parameters**
  * Words in `UPPER_CASE` refers to input from the user. <br>
  e.g. For the `viewClass` command, the command format is `viewClass CLASS`<br>
  `CLASS` refers to the user input which can be `viewClass 17S68`.
  * Parameters can be written in any order
  e.g. `edit 1 nm/Jack id/111A` is the same as `edit 1 id/111A nm/Jack`.
  * Additional parameters for commands that do not require parameters will be ignored.<br>
  e.g. `exit hello123` will be accepted as the `exit` command.
  * Optional parameters are indicated by square brackets `[]`. <br>
  e.g. For the `addStudent` command, the command format is `addStudent nm/NAME id/ID class/CLASS [pn/PARENTNAME] [hp/PHONENUMBER]...`<br>
  `[pn/PARENTNAME]` and `[hp/PHONENUMBER]` refer to optional parameters that can be supplied by the user.
</div><br>

### 4.1 Managing student records

#### 4.1.1 Adding a new student record : `addStudent`

Creates a new student record with the following details:

* **Name of Student** `nm/`
* **Student's ID (last 4 digits of NRIC)** `id/`
* **Student's Class** `class/`
* Exam Grades for CA1, CA2, SA1, and SA2 `exam/`
* Name of Parent `pn/`
* Mobile Number of Parent `hp/`
* Email Address of Parent `e/`

Format: `addStudent nm/[STUDENT-NAME] id/[ID] class/[CLASS] exam/[NAME SCORE] pn/[PARENT-NAME] hp/[PHONE-NUMBER] e/[EMAIL]`

<div markdown="span" class="alert alert-primary">:bulb:
**Tip #1:** All **bolded** fields are compulsory. Optional fields can be added later using the [edit command](#414-editing-a-student-record--edit).
</div>

<div markdown="span" class="alert alert-primary">:bulb:
**Tip #2:** To add exam grades, specify the tag `exam/` followed by an exam name (CA1, CA2, SA1, or SA2) and score (a number from 0 to 100). Multiple exams can be added in a single line.<br>
Example: `exam/CA1 50 exam/SA1 60 exam/CA1 80` will add grades 80 for CA1 and 60 for SA1. Notice the first score for CA1 is overridden by the second score for CA1.
</div>

Examples:
* `addStudent nm/Peter Tan id/452B class/1F`
* `addStudent nm/Alex Yeoh id/123A class/2B exam/CA1 60 exam/CA2 70`
* `addStudent nm/John Doe id/928C class/1A pn/Bob Doe hp/98765432 e/bobdoe@gmail.com exam/CA1 50`

#### 4.1.2 Clearing all student records : `clear`

Clears all student records from local storage.

Format: `clear`

#### 4.1.3 Deleting a student record : `delete`

Deletes an existing student record from the class list, using the student’s name or the student’s ID.

Format: `delete nm/[name of student]` or `delete id/[id of student]`

Examples:
* `delete nm/Jonathan Tan` deletes student record with student name as 'Jonathan Tan'.
* `delete id/123A` deletes student record with student ID as '123A'.

#### 4.1.4 Editing a student record : `edit`

Edits the respective details of an existing student.

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the current displayed list. The index **must be a positive integer** 1, 2, 3 ...
* Existing values will be updated to the new input values.
* Refer to the complete list of tags for each field under [addStudent command](#411-adding-a-new-student-record--addstudent).

Format: `edit [INDEX] nm/[STUDENT-NAME] id/[ID] exam/[NAME SCORE] pn/[PARENT-NAME]...`

Examples:
*  `edit 1 exam/CA2 70 exam/SA1 60` Adds or updates the CA2 and SA1 exam grades of the 1st student to be `70` and `60` respectively.
*  `edit 2 nm/Jacob Teo` Edits the name of the 2nd student to `Jacob Teo`.

### 4.2 Managing display of student records
<div markdown="span" class="alert alert-info">:information_source:
   **Note:**
   The default display, when starting the application, shows all student records in Classify.
   Commands related to managing display are not saved upon exiting the application.
</div>

#### 4.2.1 Finding a student record : `find`

Shows a list of students whose name contains the specified name keywords, or whose Id matches the given Id.

Format: `find nm/[name]` or `find id/[id]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the name or the id is searched, depending on the given input.
* Only full names / full ids will be matched e.g. `Han` will not match `Hans`

Examples:
* `find nm/John` returns the record for the student named `john`
* `find nm/john alice` returns the records for the students named `John` and the student named `Alice`.

#### 4.2.2 Toggling view : `toggleView`

Toggles the display between showing and hiding the students' parent details. 

Format: `toggleView`

*To be updated: insert screenshots*

<div markdown="span" class="alert alert-primary">:bulb:
   **Tip:** The default display renders the students' parent details as a reminder that these optional fields exists.    
</div>

#### 4.2.3 Viewing all student records : `viewAll`

Shows a list of all student records in Classify.

Format: `viewAll`

#### 4.2.4 Viewing student records from a class : `viewClass`

Shows a list of all students in the specified class.

Format: `viewClass CLASS`

* Class name can only contain alphanumeric characters.
* Class name is case-insensitive.

Examples:
* `viewClass 2A` Displays the list of students with the class `2A`.
* `viewClass Loyalty1` Displays the list of students with the class `LOYALTY1`.

### 4.3 Exam statistics

#### 4.3.1 Getting exam statistics: `viewStats`

Shows a list of students in the specified class, and displays the mean of the specified exam for that class. If filter 
is set to "ON", only students whose score for the specified exam falls below the mean will be displayed.

The list of students displayed will be arranged in ascending grades, using the grade for the specified exam.

Format: `viewStats class/CLASS exam/EXAM filter/FILTER`

* Class name can only contain alphanumeric characters.
* Class name is case-insensitive.
* Exam name should be either "CA1", "CA2", "SA1" or "SA2".
* Exam name is case-insensitive
* Filter is either "ON" or "OFF", and is case-insensitive.

Examples:
* `viewStats class/4a exam/sa1 filter/off` Displays the mean obtained by class "4A" for "SA1", as well as the list of 
all the students in the class '4A', arranged in ascending grades for "SA1".
* `viewStats class/4A exam/sa1 filter/on` Displays the mean obtained by class "4A" for "SA1", as well as the list of 
students in class "4A" whose grade for "SA1" falls below the mean, arranged in ascending grades for "SA1".

### 4.4 Miscellaneous

#### 4.4.1 Exiting the application : `exit`

Exits the application.

Format: `exit`

#### 4.4.2 Viewing help : `help`

Shows a summary of all commands available.

Format: `help`

#### 4.4.3 Saving the data

Student records are saved locally after any command that changes the data. There is no need to save manually.

Click <a href="#top">here</a> to return to the top.

--------------------------------------------------------------------------------------------------------------------

## **5 FAQ**

**Q**: Where can I locate my data file?  
**A**: You can locate the JSON file in the path `[JAR file location]/data/classify.json`.
We suggest that you **do not** edit the data file.
Class-ify will discard all data and start with an empty data file at the next run if the format of the data file is invalid.

**Q**: Why is Class-ify not running?  
**A**: Ensure Java`11` or above is installed on your computer.

Click <a href="#top">here</a> to return to the top.

--------------------------------------------------------------------------------------------------------------------

## **6 Command summary**

|              Action               | Format                                                                                                                    | Example                                                                       |  
|:---------------------------------:|:--------------------------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------|
|     Add a new student record      | `addstud nm/[STUDENT-NAME] id/[STUDENT-ID] class/[CLASS] exam/[EXAM, SCORE] pn/[PARENT-NAME] hp/[PHONE-NUMBER] e/[EMAIL]` | _addstud nm/Alex Yeoh id/123A class/1A pn/Bernice Yu hp/99272758 exam/CA1 90_ |
|     View all student records      | `viewAll`                                                                                                                 | _viewAll_                                                                     |
| View student records from a class | `viewClass [CLASS]`                                                                                                       | _viewClass 1A_                                                                |
|       Edit a student record       | `edit [INDEX] nm/[STUDENT-NAME] id/[ID] exam/[NAME SCORE] pn/[PARENT-NAME]...`                                            | _edit 1 nm/Alexander Yeoh_                                                    |
|      Delete a student record      | `delete nm/[STUDENT-NAME]` or `delete id/[STUDENT-ID]`                                                                    | _delete nm/Jonathan Tan or delete id/123A_                                    |
|       Find a student record       | `find nm/[STUDENT-NAME]` or `find id/[STUDENT-ID]`                                                                        | _find nm/Jonathan Tan or find id/123A_                                        |
| View exam statistics for a class  | `viewStats class/[CLASS] exam/[EXAM] filter/[BOOLEAN]`                                                                    | _viewStats class/1A exam/CA1 filter/on_                                       |
|            Toggle view            | `toggleView`                                                                                                              | _toggleView_                                                                  |
|     Clear all student records     | `clear`                                                                                                                   | _clear_                                                                       |
|    View command summary table     | `help`                                                                                                                    | _help_                                                                        |
|         Exit application          | `exit`                                                                                                                    | _exit_                                                                        |

Click <a href="#top">here</a> to return to the top.

--------------------------------------------------------------------------------------------------------------------

## **7 Glossary**

* **CLI**: Command Line Interface (CLI) is a text-based User Interface (UI) used to run programs. Through the CLI,
users interact with the application by typing in text commands.
* **GUI**: Graphical User Interface (GUI) is an interface that allows the user to interact with through various visual
graphics.
* **Home folder**: The home folder refers to the folder on your device that stores the Class-ify application.
* **Alphanumeric characters**: Refers to characters made up of a combination of letters and/or numbers.
* **Local storage**: Local storage refers to the data that is stored on your physical device.


