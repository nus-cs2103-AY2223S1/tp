---
layout: page
title: User Guide
---
SOConnect is a **desktop application for SOC students to easily find their peers, TAs and profs**.
Since it is designed for SOC students, it is **optimized for use via a Command Line Interface (CLI)**
but it also has a Graphical User Interface for simpler and quicker task.


## Table of Contents
1. [About the User Guide](#1-about-the-user-guide)
    - [1.1. Parameters](#11-parameters)
    - [1.2. Format](#12-format)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)
    - [3.1 Add-Related Commands](#31-add-related-commands)
        * [3.1.1 Add new Student Contact](#311-add-student)
        * [3.1.2 Add new Prof Contact](#312-add-prof)
        * [3.1.3 Add new Teaching Assistant (TA)](#313-add-ta)
    - [3.2 Editing-Related Commands](#32-editing-related-commands)
        * [3.2.1 Delete a person](#321-delete-person)
        * [3.2.2 Editing contact](#322-edit-contact)
    - [3.3 Searching-Related Commands](#33-searching-related-commands)
        * [3.3.1 Locate contact](#331-locate-contact)
        * [3.3.2 List contact](#332-list-contact)
    - [3.4 Github-Related Commands](#34-github-related-commands)
        * [3.4.1 Open a person's github profile page](#341-opening-a-persons-github-profile-page-github)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
# 1. About the User Guide

This section will explain the parameters and the format of commands.

## 1.1 Parameters

| Prefix | Parameter    | Meaning                                         |
|--------|--------------|-------------------------------------------------|
| n/     | NAME         | Name of person                                  |
| p/     | PHONE NUMBER | Phone number of person                          |
| g/     | GENDER       | Gender of person (STRICTLY FEMALE OR MALE)      |
| e/     | EMAIL        | Email address of person                         |
| m/     | MODULE       | Module that person is participating in          |
| t/     | TAG          | Tag person (i.e tag as friend)                  |
| r/     | RATING       | Rate person (on a scale of 0 to 5)              |
| []()   | INDEX        | Index of item in the recent displayed item list |

## 1.2 Format
<div markdown="block" class="alert alert-info show-whitespaces">

* All prefixes must be preceded by a space.<br>
  e.g <code> t/</code>, <code> g/</code>

* All commands are **lower-sensitive**.<br>
  e.g. `Add` will **not** be recognised as a valid syntax.

* Words in `UPPER_CASE` are the parameters to be **supplied by you**.<br>
  e.g. in `student e/EMAIL`, `EMAIL` is a parameter which can be used as `student e/e123@u.nus.edu` to create a student with email e123@u.nus.edu

* Items in **square brackets are optional**.<br>
  e.g `student n/NAME [t/TAG]` can be used as `student n/John` or as `student n/John t/friend`.

* `INDEX` **must be a positive integer** e.g. 1, 2, 3...<br>
  e.g. the `INDEX` in `delete INDEX` and `edit INDEX` must be a positive integer that is present in the
  corresponding lists e.g. `delete 1` `edit 2`.

* Parameters can be in **any order**. The only exception is if one of the parameter is an INDEX, in this case
  , **INDEX must be the second parameter**. <br>
  e.g. if the command specifies `student n/NAME e/EMAIL`, then `student e/EMAIL n/NAME` is also acceptable.
  e.g. if the command specifies `edit INDEX n/NAME`, then `edit n/NAME INDEX` is not acceptable.
</div>
<br><br>

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
# 2. Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `SOConnect.jar` from [here](https://github.com/AY2223S1-CS2103T-W08-3/tp).

    1. **Double-click** the file to start the app or  using **CLI** and type `java -jar SOConnect.jar`. The GUI similar to the below should appear in a few seconds. Below is an UI mockup.
       ![Ui](images/Ui.png)

1. Refer to the [Features](#2-features) below for details of each command.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
# 3. Features

## 3.1 Add-Related Commands
The Add-Related commands include `student`, `prof` and `ta`. These are the commands related to adding a new person to the database.

### 3.1.1 Adding a new Student Contact: `student`<a id="311-add-student"></a>

Adds a new Student contact to your contacts list.

Format: `student n/NAME [y/YEAR] m/MODULE_CODE... p/PHONE e/EMAIL g/GENDER [t/TAG]... [l/LOCATION] git/GITHUB_USERNAME`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have multiple module codes and tags.
</div>

Examples:
* `student n/John Doe y/1 m/CS4226 m/CS5242 p/98765432 e/JohnD@example.com g/M t/friends t/owesMoney l/UTown Residences git/johnnyd`
* `add n/Betsy Crowe t/friend m/CS2100 e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### 3.1.2 Add a new Professor Contact: `prof`<a id="312-add-prof"></a>

Adds a new Professor contact to your contacts list.

Format: `prof n/NAME m/MODULE_CODE [s/SPECIALISATION] p/PHONE e/EMAIL g/GENDER [t/TAG]... l/LOCATION git/GITHUB_USERNAME [r/RATING]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
When adding a Professor, you can enter multiple module codes in the same command, but only the last module code will be taken.
</div>

Examples:
* `prof n/Hartin Menz m/CS1101s s/Discrete Math p/98765432 e/HMenz@example.com g/M t/friends l/COM2 LT4 git/hartinmenz r/5 o/2-12:00-2`
* `prof n/Koro Sensei m/CS3230 p/98663357 e/KoroSensei@gmail.com g/M t/wanted git/senseikoro`

### 3.1.3 Add a new TA Contact: `ta`<a id="313-add-ta"></a>

Adds a new Teaching Assistant contact to your contacts list.

Format: `ta n/NAME m/MODULE_CODE p/PHONE e/EMAIL g/GENDER [t/TAG]... l/LOCATION git/GITHUB_USERNAME [r/RATING]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Similar to professor, when adding a Teaching Assistant, you can enter multiple module codes in the same command, but only the last module code will be taken.
</div>

Examples:
* `ta n/Alice Doe m/CS2100 p/98765432 e/AliceD@example.com g/F t/friends t/owesMoney l/COM1-0203 git/alicyD r/5`
* `ta n/Pablo Escobar m/CS2040 p/99982344 e/Pablo@hotmail.com g/M t/entrepreneur git/pabbyescobar` 

--------------------------------------------------------------------------------------------------------------------
## 3.2 Editing-Related Commands
### 3.2.1 Delete a person<a id="321-delete-person"></a>

Deletes one or more contacts from the contacts list.

Format: `delete INDEX1 INDEX2...`

* Deletes the person(s) at the specified `INDEX`.
* Multiple contacts can be deleted at once by entering multiple indexes.
* The indexes **must be a positive integer** 1, 2, 3...
* The order of the indexes does not matter as long as they are valid i.e. they are not out of bounds.

Example:
* `Delete 1 2 3`

### 3.2.2 Edit contact<a id="322-edit-contact"></a>
[coming soon]

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
## 3.3 Searching-Related Commands
### Finding a contact: `find`

Finds all contacts based on the fields provided.

A list of keywords can be entered under each field

Format: `find [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Find can be used with any number of fields as long at least one field is provided.
* The search is case-insensitive. e.g. `bob` will match `Bob`
* Keywords for all fields (except specialisation) are separated by spaces. e.g. `find n/bob alex joe`
* For fields `name` and `location`, only one of the keywords need to fully match. e.g. `find n/alex bob` can return contacts `Alex Hunter` and `Bob Jones`.
* Keywords for specialisation must be a full match and are separated by `,`. e.g. `find s/discrete math, networks` will return contacts with specialisation `Discrete Math` AND/OR `Networks`, but NOT `Discrete`.
* The order of the keywords for any field does not matter. e.g. `find n/Hans Bo` will match `find n/Bo Hans`
* For all fields, only full keywords will be matched e.g. `Bob` will not match `Bobby`
* Persons matching at least one of the fields (as well one of the keywords in the field) will be returned (i.e. `OR` search).
  e.g. `find n/alex bob t/friends` can return `Alex James`, `Bob Hunt` AND/OR all contacts with the tag `friends` (case-insensitive).

Examples:
*  `find m/CS2103T t/friends goodCoder` Returns contacts who take module `CS2103T` OR have the tags `friends` OR `goodCoder`
*  `find n/wong leong m/CS1231S l/COM3` Returns all contacts whose names have `wong` OR `leong` in them, OR take the module `CS1231S`, OR have the location `COM3` 

#### `ALL` search for `module` and `tag` fields

Finds all contacts who have ALL the module codes provided or ALL the tags provided.

Format: `find [m/all/LIST OF MODULES] [t/all/LIST OF TAGS]`

* Other fields can still be provided with this search mode, and they will still be OR search (but module/ tag will be ALL).
* This is not case-sensitive.
* `OR` search is still supported for modules and tags.

Examples 
*  `find n/wong m/all/CS2100 CS2103T CS2109S` Returns all contacts who have `wong` in their name OR (take the modules: `CS2100` AND `CS2103T` and `CS2109S`).
*  `find l/NUS t/all/friends owesMoney smart` Returns all contacts who have the location `NUS` OR (have the tags: `friends` AND `owesMoney` AND `smart`).

#### Type search

Find contacts by type: `Student` `Professor` `Teaching Assistant`

Format: `find [typ/TYPE]`

Only the following are accepted:
* `stu` for Student
* `prof` for Professor
* `ta` for Teaching Assisstant
* All are case-insensitive
* More than one type can be provided

Example:
*  `find typ/stu ta` will return contacts who are students or teaching assistants.

#### Office Hour search

Find contacts by their office hours.

Format: `find [o/OFFICE HOURS]`

* keywords must be in the same format as office hour input. e.g. To find office hour `MONDAY, 03:00 PM - 05:00 PM` enter `find o/1-15:00-2`.
* More than office hour can be provided.
* Office hours must be separated by spaces.
* A full match to each office hour is required.

Example:
* `find o/1-15:00-2 2-12:00-2` Returns all contacts with office time `MONDAY, 03:00 PM - 05:00 PM` OR `TUESDAY, 12:00 PM - 02:00 PM`.
<div style="page-break-after: always;"></div>



Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
--------------------------------------------------------------------------------------------------------------------
## 3.4 Github-Related Commands
### 3.4.1 Opening a person's github profile page: `github`

Opens the github profile page associated to the specified person in the address book.

Format: `github INDEX`
* Opens the github profile page associated to the person at the specified `INDEX'
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Example command: `github 1`

--------------------------------------------------------------------------------------------------------------------
