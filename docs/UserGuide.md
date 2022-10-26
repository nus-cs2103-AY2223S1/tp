---
layout: page
title: User Guide
---
SOConnect is a **desktop application for SOC students to easily find their peers, TAs and profs**.
Since it is designed for SOC students, it is **optimized for use via a Command Line Interface (CLI)**
but it also has a Graphical User Interface for simpler and quicker task.


* Table of Contents
  {:toc}

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
# 1. About the User Guide

This section will explain the parameters and the format of commands.

## 1.1 Parameters

| Prefix | Parameter       | Meaning                                                  |
|--------|-----------------|----------------------------------------------------------|
| n/     | NAME            | Name of person                                           |
| p/     | PHONE NUMBER    | Phone number of person                                   |
| g/     | GENDER          | Gender of person (STRICTLY FEMALE OR MALE)               |
| git/   | GITHUB USERNAME | GitHub Username of person                                |
| e/     | EMAIL           | Email address of person                                  |
| m/     | MODULE          | Module that person is participating in                   |
| t/     | TAG             | Tag person (i.e tag as friend)                           |
| r/     | RATING          | Rate Professor/Teaching Assistant (on a scale of 0 to 5) |
| y/     | YEAR            | Year of Undergraduate Study (from 1 - 4)                 |
| l/     | LOCATION        | Location of Professor/Teaching Assistant/Student in NUS  |
| s/     | SPECIALISATION  | Professor's Specialisation                               |
| []()   | INDEX           | Index of item in the recent displayed item list          |

## 1.2 Format
<div markdown="block" class="alert alert-info show-whitespaces">

* All prefixes must be preceded by a space.<br>
  e.g <code> t/</code>, <code> g/</code>

* All commands are **lower-sensitive**.<br>
  e.g. `Find` will **not** be recognised as a valid syntax.

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

1. Refer to the [Features](#3-features) below for details of each command.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
# 3. Features

## 3.1 Add-Related Commands
The Add-Related commands include `student`, `prof` and `ta`. These are the commands related to adding a new person to the application.

### 3.1.1 Adding a new Student Contact: `student`

Adds a new Student contact to your contacts list.

Format: `student n/NAME [y/YEAR] m/MODULE_CODE... p/PHONE e/EMAIL g/GENDER [t/TAG]... [l/LOCATION] git/GITHUB_USERNAME`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have multiple module codes and tags.
</div>

Examples:
* `student n/John Doe y/1 m/CS4226 m/CS5242 p/98765432 e/JohnD@example.com g/M t/friends t/owesMoney l/UTown Residences git/johnnyd`
* `add n/Betsy Crowe t/friend m/CS2100 e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### 3.1.2 Add a new Professor Contact: `prof`

Adds a new Professor contact to your contacts list.

Format: `prof n/NAME m/MODULE_CODE [s/SPECIALISATION] p/PHONE e/EMAIL g/GENDER [t/TAG]... [l/LOCATION] git/GITHUB_USERNAME [r/RATING]`

<div markdown="span" class="alert alert-info">:information_source: **Note:**
When adding a Professor, you can enter multiple module codes in the same command, but only the last module code will be taken.
</div>

Examples:
* `prof n/Hartin Menz m/CS1101s s/Discrete Math p/98765432 e/HMenz@example.com g/M t/friends l/COM2 LT4 git/hartinmenz r/5 o/2-12:00-2`
* `prof n/Koro Sensei m/CS3230 p/98663357 e/KoroSensei@gmail.com g/M t/wanted git/senseikoro`

### 3.1.3 Add a new TA Contact: `ta`

Adds a new Teaching Assistant contact to your contacts list.

Format: `ta n/NAME m/MODULE_CODE p/PHONE e/EMAIL g/GENDER [t/TAG]... l/LOCATION git/GITHUB_USERNAME [r/RATING]`

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Similar to professor, when adding a Teaching Assistant, you can enter multiple module codes in the same command, but only the last module code will be taken.
</div>

Examples:
* `ta n/Alice Doe m/CS2100 p/98765432 e/AliceD@example.com g/F t/friends t/owesMoney l/COM1-0203 git/alicyD r/5`
* `ta n/Pablo Escobar m/CS2040 p/99982344 e/Pablo@hotmail.com g/M t/entrepreneur git/pabbyescobar`

--------------------------------------------------------------------------------------------------------------------
## 3.2 Editing-Related Commands
### 3.2.1 Delete person/s: `delete`

Deletes one or more contacts from the contacts list.

Format: `delete INDEX1 INDEX2...`

* Deletes the person(s) at the specified `INDEX`.
* Multiple contacts can be deleted at once by entering multiple indexes.
* The indexes **must be a positive integer** 1, 2, 3...
* The order of the indexes does not matter as long as they are valid i.e. they are not out of bounds.

Example:
* `Delete 1 2 3`

### 3.2.2 Edit contact
[coming soon]

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
## 3.3 Searching-Related Commands
### 3.3.1 Locate contact
[coming soon]
### 3.3.2 List contact
[coming soon]
<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
## 3.4 GitHub Command: `github`

Opens the GitHub profile page associated with the specified person in the address book.

Format: `github INDEX`
* Opens the GitHub profile page associated with the person at the specified `INDEX'
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `github 1`
* `github 3`

<div markdown="span" class="alert alert-info">:information_source: **Note:**
If the GitHub username there is no GitHub username associated with the person at the specified `INDEX', an error will be thrown
</div>

--------------------------------------------------------------------------------------------------------------------
## 3.5 Pie Chart Feature

The contact list will be displayed as a pie chart. The pie chart is located to the right of the contact list.

![piechart](images/UiPiechart.png)

This pie chart shows how many of each type of contact you have, and updates itself whenever the list changes. You can use this to have a quick overview of your social network.

--------------------------------------------------------------------------------------------------------------------
