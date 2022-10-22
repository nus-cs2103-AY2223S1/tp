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
The Add-Related commands include `student`, `prof` and `ta`. These are the commands related to adding new person to the database.

### 3.1.1 Adding a new Student Contact: `student`<a id="311-add-student"></a>
[coming soon]
### 3.1.2 Add a new Prof Contact: `prof`<a id="312-add-prof"></a>
[coming soon]
### 3.1.3 Add a new TA Contact: `ta`<a id="313-add-ta"></a>
[coming soon]
--------------------------------------------------------------------------------------------------------------------
## 3.2 Editing-Related Commands
### 3.2.1 Delete a person<a id="321-delete-person"></a>
[coming soon]
### 3.2.2 Edit contact<a id="322-edit-contact"></a>
[coming soon]

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
## 3.3 Searching-Related Commands
### 3.3.1 Locate contact<a id="331-locate-contact"></a>
[coming soon]
### 3.3.2 List contact<a id="332-list-contact"></a>
[coming soon]
<div style="page-break-after: always;"></div>

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