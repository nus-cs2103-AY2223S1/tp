---
layout: page
title: User Guide
---

<div align=center><img src="images/userguide/ProfNUS.png" alt="img" width=400 height=250 /></div>


Made for SoC (School of Computing) Professors, **ProfNUS** is the **easiest way to keep track of your teaching schedule and organize information regarding the students and modules you teach.** It is optimized for users who prefer Command Line Interface (CLI) so that frequent tasks can be done faster by typing in commands which is perfect for SoC Professors! Interested? :wink: Continue reading to find out more!

* Table of Contents
{:toc}

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

Welcome to **ProfNUS**! :blush:

Are you a SoC professor who is busy with heavy research work, teaches many modules and manages hundreds of students and TAs at the same time? Do you feel dizzy changing from one platform to another such as Luminus or Canvas? Have you ever sent an email to the wrong group of students? Life is hard, but luckily, **ProfNUS** can help you out! **ProfNUS** is a comprehensive teaching management desktop application that assists SoC professor in terms of modules, students, TAs, and schedules. It is designed based on Command Line Interface (CLI), which perfectly fits SoC professors' habits and preferences. We also provide Graphic User Interface (GUI) for users to interact with our product.

If you want to know more about how **ProfNUS** is able to ease your worries, go to the [Quick Start](#2-quick-start) and take your first step in **ProfNUS**!

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## 2. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ProfNUS.jar` from [here](https://github.com/AY2223S1-CS2103T-W11-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ProfNUS application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all students and teaching assistants.

    * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 i/A0837092R t/@john_doe c/CS2030S cg/CS2030S:Tut07`: Adds a student named `John Doe` to ProfNUS.

    * **`delstu`**`3` : Deletes the 3rd student shown in the current list of students.

    * **`clear`** : Deletes all students, teaching assistants, modules and schedules.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#4-features) below for details of each command.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
## 3. About

This section introduces you to some technical terms, syntax and symbols that are used throughout the guide. You may want to familiarize yourself with them before moving on to the next section.

Else, if you are already familiar with the syntax, you can skip this section and head on to [Features](#4-features) below for details of each command!

### 3.1. Structure of this document

We have structured the User Guide in a way to help you find what you want and what you need easily. The [features](#4-features) are separated into 4 main categories namely:
* User Interface
* Modules
* Students
* Schedules

In the next [subsection](#32-reading-this-document), you can find several useful tips on how to read this guide.

### 3.2. Reading this document

This section introduces to you some terminologies, symbols and syntax that are used throughout the user guide. You may find it useful to familiarize yourself with them before moving on to the next section.

#### 3.2.1. Symbols

The table below shows the general symbols and syntax used throughout the user guide.

| Syntax                                                                      | Definition                                                                                               |
|-----------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| `command`                                                                   | A grey highlighted block specifies an executable command that can be entered into the command box.       |
| <div markdown="block" class="alert alert-info"> :information_source: </div> | An information sign indicates that the following text provides additional information that you may need. |
| <div markdown="block" class="alert alert-warning"> :exclamation: </div>     | An exclamation mark indicates that the following text is important.                                      |

#### 3.2.2. Command Syntax
Notes about the command format:

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

  * Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `sview`, `exit` and `clear`) will be ignored.<br>
    e.g. if the command specifies `help 123`, it will be interpreted as `help`.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## 4. Features

This section contains all the information on the main features of **ProfNUS**. You may enter the commands here into the Command Box to use our features.

### 4.1. User Interface

#### 4.1.1. Switch to light mode: `light`

You can use this command to switch to light mode.

**Format:** `light`

#### 4.1.2. Switch to dark mode: `dark`

You can use this command to switch to dark mode.

**Format:** `dark`

#### 4.1.3. Viewing help: `help`

You can use this command to access the help page, where you can find our User Guide link.

**Format:** `help`

### 4.2. Module Manager

As SoC Professors, you would be teaching at least one or more modules.
In ProfNUS, you are able to add modules into our module list.
We are able to save the module's name, unique module code, description
and other module related information such as your teaching schedule for the module.

<div style="page-break-after: always;"></div>

#### 4.2.1. Module Manager Command Parameters

Before you get started with this feature, have a look at the common parameters we have used. Take a look before you
continue!

| Parameter            | Description & Restriction                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Example                                                         |
|----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------|
| `MODULE_NAME`        | This parameter represents the name of the module you are adding into ProfNUS.<br/>The name should be alphanumeric.<br/>Modules in ProfNUS are allowed to have the same names.                                                                                                                                                                                                                                                                                                  | `Software Engineering`                                          |
| `MODULE_CODE`        | This parameter represents the module code of a module in ProfNUS. Using module code can uniquely determine a module. The format should abide by the Module Code Format of NUS. Each unique module code consists of a two‐letter or three‐letter prefix that denotes the discipline, four digits the first of which indicates the level of the module, and one or zero letter postfix. Module codes are not case sensitive, this means that `CS2030S` is the same as `cs2030s`. | `CS2030S`                                                       |
| `MODULE_DESCRIPTION` | This parameter represents the description of the module you are adding into ProfNUS.<br/>The description should be alphanumeric.<br/>Modules in ProfNUS are allowed to have the same description.                                                                                                                                                                                                                                                                              | `This module teaches you about software engineering principles.` |
| `TAG`                | This parameter represents the tag you want to attach to the module.<br/>It must be a single word consisting of alphanumeric characters.                                                                                                                                                                                                                                                                                                                                        | `ModuleCoordinator`                                             |

<div style="page-break-after: always;"></div>

#### 4.2.2. Viewing list of modules: `mlist`

You can use this command to view the list of modules that you have added into ProfNUS.

**Format:** `mlist`

**Example:**

![mlist](images/userguide/mlist.png)

#### 4.2.3. Viewing more details of a module in the list of modules: `vtarget`

You can use this command to get more information about a module in ProfNUS.

You will be able to see more information, such as the name of the module and the module description,
as well as any tags given to the module upon using this command!

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>
- You can view more specific information about a module at your specified index!
- The `INDEX` refers to the index number shown in the displayed module list.
- The `INDEX` **must be a positive integer** 1, 2, 3, …​

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- Please make sure the `INDEX` you type is **valid**, i.e. it appears in the displayed list.
  Otherwise, **no** information about a module will be display by ProfNUS.

</div>

**Format:** `vtarget INDEX`

**Example:**

Now, let's use the `vtarget` command to see how you can view more information about a module.

**Step 1**: Type the command `vtarget 1` in the command box.

![vtarget_step1](images/userguide/vtarget_step1.png)

**Step 2**: Press enter, and you will be able to see more information about a module now!

![vtarget_step2](images/userguide/vtarget_step2.png)

#### 4.2.4. Viewing module details: `mview`

You can use this command to view a list of students and tutors in the module.

**Format:** `mview c/MODULE_CODE`

**Example:**

Let's use the `mview` command to view all the students and tutors in a module.

**Step 1**: Type the command `mview c/CS1101S` in the command box.

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- Module codes are not case-sensitive, this means that `CS2030S` is the same as `cs2030s`.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- Please ensure that the `MODULE_CODE` exists in ProfNUS before you run this command! Otherwise, ProfNUS will consider the command to be invalid.

</div>

![mview_step1](images/userguide/mview_step1.png)

**Step 2**: Press enter, and you will be able to see the list of students and teaching assistants for that module.

![mview](images/userguide/mview_step2.png)

#### 4.2.5. Adding a module: `madd`

You can use this command to add your desired module into ProfNUS.

**Format:** `madd n/MODULE_NAME c/MODULE_CODE d/MODULE_DESCRIPTION [t/TAG]…​`


<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Module Manager Command Parameters](#421-module-manager-command-parameters) for more information about the parameters.
- A module can have any number of tags(including 0), of your choice!

</div>

**Example:**

Now, let's try using the `madd` command to add a module to ProfNUS!

**Step 1**: Type the command `madd n/Communcation skills c/CS2101 d/Teach IT communication skills t/ModuleCoordinator`
into the command box.

![madd_step1](images/userguide/madd_step1.png)

**Step 2**: Press enter, and the module will be added to ProfNUS!

![madd_step2](images/userguide/madd_step2.png)

#### 4.2.6. Editing a module: `medit`

You can use this command to edit details of a module in ProfNUS!

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Module Manager Command Parameters](#421-module-manager-command-parameters) for more information about the parameters.
- You have to specify **at least 1** parameter out of all the optional parameters listed below!
- A module can have any number of tags(including 0), of your choice!

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- Please make sure the module with `MODULE_CODE` exists in ProfNUS before you run this command! Otherwise, ProfNUS will consider the command to be invalid.

</div>

**Format:** `medit MODULE_CODE [n/MODULE_NAME] [c/MODULE_CODE] [d/MODULE_DESCRIPTION] [t/TAG]…​`

**Example:**

Now, let's try to edit a module in ProfNUS!

**Step 1**: Type the command `medit cs2103 c/cs2103t` in the command box.

![medit_step1](images/userguide/medit_step1.png)

**Step 2**: Press enter, and the module `CS2103` will be updated to the new module code `CS2103T`.

![medit_step2](images/userguide/medit_step2.png)

#### 4.2.7. Deleting a module: `mdel`

You can use this command to delete a module stored already in ProfNUS.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- Please make sure the module with `MODULE_CODE` exists in ProfNUS before you run this command! Otherwise, ProfNUS will consider the command to be invalid.

</div>

**Format:** `mdel c/MODULE_CODE`

**Example:**

Now, let's use `mdel` to delete a module in ProfNUS!

**Step 1**: Type the command `mdel c/CS1101S` in the command box.

![mdel_step1](images/userguide/mdel_step1.png)

**Step 2**: Press enter, and the module will be deleted from ProfNUS!

![mdel_step2](images/userguide/mdel_step2.png)

<div style="page-break-after: always;"></div>

### 4.3. Student Manager

As SoC Professors, you certainly have many students and teaching assistants to keep track of.
In ProfNUS, you are able to add students / teaching assistants into our contact list.
We are able to save the individual's name, unique student id, phone number, email, telegram handle,
address and other school related information such as modules the student is taking, modules which
the student is a teaching assistant for and the class groups that the student is in.
Teaching assistants in ProfNUS are considered students as well and as such, an individual
can be both a student and a teaching assistant at the same time.

![studentandta.png](./images/userguide/studentandta.png)

As we can see, the person `Bernice Yu` is a student of the module `CS2040S` as seen by the red label and a
teaching assistant of the module `CS2030S` as seen by the green label. If you edit or delete `Bernice Yu`, the changes will
be saved on both sides.

<div style="page-break-after: always;"></div>

#### 4.3.1. Student Manager Command Parameters

Before you get started with this feature, have a look at the common parameters we have used. Take a look before you
continue!

| Parameter     | Description & Restriction                                                                                                                                                                                                                                                                                                                                                                                                                    | Example                             |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------|
| `NAME`        | This parameter represents the name of the student you are adding into ProfNUS.<br/>The name should be alphanumeric.<br/>Students in ProfNUS are allowed to have the same names.                                                                                                                                                                                                                                                              | `Alex Yeoh`                         |
| `EMAIL`       | This parameter represents the email of the student you are adding into ProfNUS.<br/>The email should follow the format `front@domain`.<br/>The `front` can only contain alphanumeric characters and special characters. The `domain` should at least be 2 characters long, start with alphanumeric characters and can contain A period or hyphen for the characters in between.<br/>Students in ProfNUS are allowed to have the same emails. | `AlexYeoh123@gmail2.com`            |
| `PHONE`       | This parameter represents the phone number of the student you are adding into ProfNUS.<br/>The phone number should only consist of numbers and be at least 3 digits long.<br/>Students in ProfNUS are allowed to have the same number.                                                                                                                                                                                                       | `995` or `918414732`                |
| `ADDRESS`     | This parameter represents the address of the student you are adding into ProfNUS.<br/>The address can take any value, but should not be blank.<br/>Students in ProfNUS are allowed to have the same address.                                                                                                                                                                                                                                 | `Blk 47 Tampines Street 20, #17-35` |
| `STUDENT_ID`  | This parameter represents the student id of the student you are adding into ProfNUS.<br/>The student id should be at most 9 characters, where the starting and ending characters are letters, while the middle characters are numbers.<br/>The student id of each student in ProfNUS should be unique.                                                                                                                                       | `A0257734W`                         |
| `TELEGRAM`    | This parameter represents the telegram username of the student you are adding into ProfNUS.<br/>The telegram username of the student should start with `@` and only consist of alphanumeric characters and underscores, and it should be at least 6 characters long inclusive of `@`.<br/>Students in ProfNUS are allowed to have the same telegram username.                                                                                | `@good_student`                     |
| `TAG`         | This parameter represents the tag you want to attach to the student.<br/>It must be a single word consisting of alphanumeric characters.                                                                                                                                                                                                                                                                                                     | `attentive`                         |
| `MODULE_CODE` | This parameter represents the module code of a module in ProfNUS. Using module code can uniquely determine a module. The format should abide by the Module Code Format of NUS.                                                                                                                                                                                                                                                               | `CS2030S`                           |
| `CLASS_GROUP` | This parameter represents the group label if there are multiple lecture, tutorial, lab, or reflection groups.<br/>There are no restrictions on the information added to class groups. When a module is deleted, the class group will still remain.                                                                                                                                                                                           | `CS2030S:L1`                        |
| `INDEX`       | This parameter represents the index of the student / teaching assistant in thier respective lists.<br/>The index must be in the range of the numbers provided.                                                                                                                                                                                                                                                                               | `1`                                 |

<div style="page-break-after: always;"></div>

#### 4.3.2. Listing all students: `list`

You can use this command to view the list of all the students and teaching assistants.

**Format:** `list`

**Example:**

![list](images/userguide/list.png)

#### 4.3.3. Adding a student or teaching assistant: `add`

You can add a student / teaching assistant by using this command.

**Format**: `add n/NAME p/PHONE e/EMAIL a/ADDRESS i/STUDENT_ID h/TELEGRAM [t/TAG]…​ [c/MODULE_CODE]…​ [ta/MODULE_CODE]…​ [cg/CLASS_GROUP]…​`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Student Manager Command Parameters](#431-student-manager-command-parameters) for more information about the parameters.
- A student can have any number of tags, modules, ta modules and class groups (including 0).
- A student's Student Id has to be unique!
- Successfully adding a student will redirect you to the all student's and teaching assistant's page.
- `c/` refers to the modules the student is taking, while `ta/` refers to the modules the student is teaching.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- Please make sure you have added the module with `MODULE_CODE` before you add any students with `MODULE_CODE`. Otherwise, ProfNUS will consider the command to be invalid.<br>

- A student should not be a teaching assistant and a student of the same module!<br>

- If a student name is too long, only the first 50 characters of the name will be shown!

</div>

**Example**:

Now, let's use `add` to add a student with the following information.

`n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends t/good i/A0123456G h/@good_student c/CS2030S ta/CS1101S cg/CS2030S:Tut07`

**Step 1**: type command `add  n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney i/A0123456G h/@good_student c/CS2030S ta/CS1101S cg/CS2030S:Tut07` in the command text box.

![add_step1](images/userguide/student/add_step1.png)

**Step 2**: Press enter, then you can see the student John Doe has been added to the student list. Additionally, John Doe will appear in the teaching assistant list since he is a ta of the module
`CS1101S` because of the `ta/CS1101S` parameter.

![add_step2](images/userguide/student/add_step2.png)


#### 4.3.4. Editing a student: `editstu`

You can use this command to edit a student in the Student Manager.

**Format:** `editstu INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/STUDENT_ID] [h/TELEGRAM] [t/TAG]…​ [c/MODULE_CODE]…​ [ta/MODULE_CODE]…​ [cg/CLASSGROUPS]…​`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Student Manager Command Parameters](#431-student-manager-command-parameters) for more information about the parameters.
- You must enter at least on optional parameter to edit the student.
- When editing tags, modules, ta modules and class groups, the existing information of the student will be removed i.e adding of tags, modules, ta modules and class groups is not cumulative.
- You can remove all the student’s tags by typing `t/` without specifying any tags after it.
Likewise, this can be done for modules, ta modules and class groups as well by typing `c/`, `ta/` and `cg/` respectively.
- Editing a student that is a teaching assistant will change the details of both of them displayed in the list.
- Successfully editing a student will redirect you to the all student's and teaching assistant's page.

</div>

**Example:**

For instance, you have accidentally indicated that the student `John Doe` is a teaching assistant of
the module `CS2040S` and a student of the module `CS2030S` when instead it should be the other way round.

You can follow the steps shown below to amend this error.

**Step 1**:

Type the command `editstu 6 c/CS2040S ta/CS2030S`

![edit_step1](images/userguide/student/edit_step1.png)

**Step 2**:

Press `Enter` to execute the command.

![edit_step2](images/userguide/student/edit_step2.png)


#### 4.3.5. Editing a teaching assistant: `editta`

You can use this command to edit a teaching assistant from the teaching assistant list.

**Format:** `editta INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/STUDENT_ID] [h/TELEGRAM] [t/TAG]…​ [c/MODULE_CODE]…​ [ta/MODULE_CODE]…​ [cg/CLASSGROUPS]…​`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Student Manager Command Parameters](#431-student-manager-command-parameters) for more information about the parameters.
- You must enter at least on optional parameter to edit the teaching assistant.
- When editing tags, modules, ta modules and class groups, the existing information of the teaching assistant will be removed i.e adding of tags, modules, ta modules and class groups is not cumulative.
- You can remove all the teaching assistant’s tags by typing `t/` without specifying any tags after it.
  Likewise, this can be done for modules, ta modules and class groups as well by typing `c/`, `ta/` and `cg/` respectively.
- Editing a teaching assistant that is a student will change the details of both of them displayed in the list.
- Editing a teaching assistant is very similar to editing a student. Do refer to the section [Editing a Student](#434-editing-a-student-editstu)
- Successfully editing a teaching assistant will redirect you to the all student's and teaching assistant's page.

</div>

**Examples:**
*  `editta 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st teaching assistant to be `91234567` and `johndoe@example.com` respectively.
*  `editta 2 n/Betsy Crower ta/` Edits the name of the 2nd teaching assistant to be `Betsy Crower` and clears all ta modules.

#### 4.3.6. Deleting a student: `delstu`

You can use this command to delete a student from the student list.

**Format:** `delstu INDEX`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Student Manager Command Parameters](#431-student-manager-command-parameters) for more information about the parameters.
- Deletes the student at the specified index.
- The index refers to the index number shown in the displayed student list.
- The index **must be a positive integer** 1, 2, 3, …​
- Deleting a student from the student list will also delete the teaching assistant from the teaching assistant list if the student is a teaching assistant.
- Successfully deleting a student will redirect you to the all student's and teaching assistant's page.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- Please make sure the `INDEX` you type is **valid**, i.e. it appears in the displayed list.
Otherwise, **no** student will be deleted by ProfNUS.

</div>

**Example:**

Now, let's use the `delstu` command to delete the student 'John Doe'.

**Step 1**: Type the command `delstu 6` in the command box.

![delstu_step1](images/userguide/student/delstu_step1.png)

**Step 2**: Press `Enter` to execute the command.

![delstu_step2](images/userguide/student/delstu_step2.png)


#### 4.3.7. Deleting a teaching assistant: `delta`

You can use this command to delete a teaching assistant from the teaching assistant list.

**Format:** `delta INDEX`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Student Manager Command Parameters](#431-student-manager-command-parameters) for more information about the parameters.
- Deletes the teaching assistant at the specified index.
- The index refers to the index number shown in the displayed student list.
- The index **must be a positive integer** 1, 2, 3, …​
- Deleting a teaching assistant from the teaching assistant list will also delete the student from the student list.
- Deleting a teaching assistant is very similar to deleting a student. Do refer to [Deleting a student](#436-deleting-a-student-delstu) if you are unsure.
- Successfully deleting a teaching assistant will redirect you to the all student's and teaching assistant's page.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- Please make sure the `INDEX` you type is **valid**, i.e. it appears in the displayed list.
  Otherwise, **no** teaching assistant will be deleted by ProfNUS.

</div>

**Examples:**
* Executing the command `delta 2` deletes the second teaching assistant in the list.


#### 4.3.8. Locating students and teaching assistants by name: `find`

You can find your students / teaching assistants by searching for keywords in their name.

**Format:** `find KEYWORD [MORE_KEYWORDS]`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Student Manager Command Parameters](#431-student-manager-command-parameters) for more information about the parameters.
- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

</div>

**Example:** `find alex david`

Now, let's use the `find` command to find students / teaching assistants with the name `alex` or `david` in them.

**Step 1**: Type the command `find alex david` in the command box.

![find_step1](images/userguide/student/find_step1.png)

**Step 2**: Press `Enter` to executre the command, then you will see students with the name `alex` or `david`.

![find_step2](images/userguide/student/find_step2.png)

<div style="page-break-after: always;"></div>

### 4.4. Schedule Manager

As a professor in SoC, you must be busy with teaching. ProfNUS is here to help you out! We provide you with a variety of efficient commands to help you keep track of your teaching schedules easily. For each schedule, you can indicate the module it belongs to, the weekday it happens on, the class type, the class group, the time period, and the class venue. If you are interested, please continue to see how our schedule features can help you.

#### 4.4.1. Schedule Manager Command Parameters

In this section, we list all the parameters you will encounter of following commands. Take a look before you continue.

| Parameter     | Description & Restriction                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         | Example       |
|---------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------|
| `MODULE_CODE` | This parameter represents the code of a module. Using module code can uniquely determine a module. The format should abide by the  [Module Code Format of NUS](https://www.nus.edu.sg/registrar/docs/info/nusbulletin/AY201213_GeneralInformation.pdf).                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | `CS2103T`     |
| `WEEKDAY`     | This parameter represents the day in a week. We provide seven options for you: `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`. <br/>Please take note that this parameter is **case sensitive**. Weekdays in lower case will not be accepted.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | `Monday`      |
| `PERIOD`      | This parameter represents the time slot of your schedule. Your `PERIOD` should follow the format of `HH:MM-HH:MM`. To be consistent with NUS regular class duration, we  have set the following **restrictions** to this parameter:                                                                                                                  <br/>1. No class should start before `07:00` or end after `10:00`.                                <br/>2. The time should be on the whole or half hour.                                                   <br/>3. The duration should be longer than or equal to one hour, and shorter than or equal to three hours.                                                                                                             <br/>Please take note that ProfNUS adopts 24-hour clock, so `02:00` will be treated as 2am. | `10:00-12:00` |
| `CLASS_TYPE`  | This parameter represents the class category. We provide four types of classes for you: `lec`, `tut`, `lab`, `rec`, representing Lecture, Tutoria, Lab, and Reflection, respectively. <br/> Please take note that this parameter is **case sensitive**. Any uppercase character will not be accepted.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | `lec`         |
| `CLASS_GROUP` | This parameter represents the group label if there are multiple lecture, tutorial, lab, or reflection groups.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | `L1`          |
| `VENUE`       | This parameter represents the venue of a class.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | `LT16`        |
| `INDEX`       | This parameter represents the index of a schedule in the displayed list. Indices are **one-based**.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | `1`           |
| `MODE`        | This parameter represents the mode of the timetable. `MODE` can only be `v` or `h`.   <br/> Please take note that this parameter is **case sensitive**. Any uppercase character will not be accepted.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | `v`           |
| `...`         | This symbol means the parameter before it can appear multiple times. For example, `param...` means you can type `param1 param2 param3 ...`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | `...`         |


#### 4.4.2. View your schedule: `sview`
 
You can use this command to view the list of all of your schedules.

**Format:** `sview`

**Example:** `sview`

#### 4.4.3. View your timetable: `tview`

You can use this command to view your timetable, consisting of all of your schedules.

**Format:** `tview [MODE]`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- `MODE` can only be `v`(for vertical mode) or `h`(for horizontal mode).
- The default value of `MODE` is `h`.
- You can view the vertical schedules by typing `tview v`.
- You can view the horizontal schedules by typing `tview h` or `tview`.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- If there are other parameters/words after `tview` instead of `v` or `h`, ProfNUS will report an error.

</div>

**Example:** `tview h`

Now, let's view our timetable in horizontal mode.

**Step 1:** Type the command `tview h` in the command box.

![tview_h_step1](images/userguide/tview_h_step1.png)

**Step 2:** Press enter, you will see the horizontal timetable with all schedules.

![tview_h_step2](images/userguide/tview_h_step2.png)

**Example:** `tview v`

If the command is `tview v` in previous example step 1, the vertical timetable will be display.

![tview_v_step2](images/userguide/tview_v_step2.png)

<div style="page-break-after: always;"></div>

#### 4.4.4. Add your schedule: `sadd`

You can add a schedule by using this command.

**Format:** `sadd c/MODULE_CODE w/WEEKDAY ct/PERIOD cc/CLASS_TYPE cg/CLASS_GROUP cv/VENUE`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Schedule Manager Command Parameters](#441-schedule-manager-command-parameters) for more information about the parameters.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- Please make sure you have added the module with `MODULE_CODE` before you add any schedules with `MODULE_CODE`. Otherwise, ProfNUS will consider the command to be invalid and report an error.<br>
- If the schedule to be added conflicts with any existing schedule, ProfNUS will report an error and **will not** perform any operation.

</div>

**Example:**

Now, let's use `sadd` to add a schedule with following information:

`c/CS1101S w/Tuesday ct/10:00-12:00 cc/lec cg/L1 cv/I3-AUD`

**Step 1:** type command `sadd c/CS1101S w/Tuesday ct/10:00-12:00 cc/lec cg/L1 cv/I3-AUD` in the command text box.

![sadd](images/userguide/sadd_step1.png)

**Step 2:** Press enter, then you can see the new schedule has been added to the list.

![sadd_step2](images/userguide/sadd_step2.png)

<div style="page-break-after: always;"></div>

#### 4.4.5. Edit your schedule: `sedit`

You can use this command to edit a schedule in ProfNUS.

**Format:** `sedit INDEX [c/MODULE_CODE] [w/WEEKDAY] [ct/PERIOD] [cc/CLASS_TYPE] [cg/CLASS_GROUP] [cv/VENUE] `

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Schedule Manager Command Parameters](#441-schedule-manager-command-parameters) for more information about the parameters.
- You are expected to provide at least one optional parameter
- Your newly typed parameters will overwrite corresponding existing information.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**

- Please make sure the `INDEX` you type is **valid**, i.e. it appears in the displayed list. Otherwise, there will be an error message and **no** schedule will be edited by ProfNUS.

</div>

**Example:**

Now, let's use `sedit` command to edit a schedule with the following information:

`1 w/Monday ct/09:00-10:00 cc/tut cv/COM2-0218`

**Step 1:** Type the command `sedit 1 w/Monday ct/09:00-10:00 cc/tut cv/COM1-B103` in the command text box.

![sedit](images/userguide/sedit_step1.png)

**Step 2:** Press enter, then you will see the first schedule in the list has been edited.

![sedit_step2](images/userguide/sedit_step2.png)

<div style="page-break-after: always;"></div>

#### 4.4.6. Delete your schedule: `sdelete`

You can use this command to delete a schedule from ProfNUS.

**Format:** `sdelete INDEX`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Schedule Manager Command Parameters](#441-schedule-manager-command-parameters) for more information about the parameters.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- Please make sure the `INDEX` you type is **valid**, i.e. it appears in the displayed list. Otherwise, there will be an error message and **no** schedule will be deleted by ProfNUS.

</div>

**Example:** `sdelete 1`

Now, let's use `sdelete` command and delete the first schedule in your schedule list.

**Step 1:**  Type the command `sdelete 1` in the command box.

![sdelete_step1](images/userguide/sdelete_step1.png)

**Step 2:** Press enter, then you will see the first schedule is deleted.

![sdelete_step2](images/userguide/sdelete_step2.png)


#### 4.4.7. Clear your schedule: `sclear`

You can use this command to clear all the schedules or all schedules of a selected module in ProfNUS.

**Format:** `sclear [c/MODULE_CODE]...`

<div markdown="block" class="alert alert-info">

**:information_source: Note**<br>

- You can refer to the section [Schedule Manager Command Parameters](#441-schedule-manager-command-parameters) for more information about the parameters
- If no optional `MODULE_CODE` is given, all schedules will be cleared.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>

- If any one of `MODULE_CODE` you type in doesn't exist, ProfNUS will report an error and  **will not** clear any schedule you have.

</div>

**Example:** `sclear c/CS2101 c/CS2103T`

Now, let's use `sclear` to clear all schedules of CS2101 and CS2103T in the schedule lists.

`c/CS2101 c/CS2103T`

**Step 1:** type command `sclear c/CS2101 c/CS2103T` in the command text box.

![sclear_step1](images/userguide/sclear_step1.png)

**Step 2:** Press enter, then you can see the remaining schedules without those cleared.

![sclear_step2](images/userguide/sclear_step2.png)

<div style="page-break-after: always;"></div>

### 4.5. Clearing all entries: `clear`

You can use this command to clear all entries from ProfNUS.

**Format:** `clear`

### 4.6. Exiting the program: `exit`

You can use this command to exit ProfNUS.

**Format:** `exit`

### 4.7. Saving the data

Your data that you entered into ProfNUS is saved in the hard disk automatically after any command that changes the data. There is no need for you to save it manually.

### 4.8. Editing the data
Your data in ProfNUS is saved as a JSON file `[JAR file location]/data/profnus.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ProfNUS will discard all of your current data and start with an empty data file at the next run.
</div>

<div style="page-break-after: always;"></div>
--------------------------------------------------------------------------------------------------------------------

## 5. FAQ

**Q:** How do I transfer my data to another Computer?<br>
**A:** You can install ProfNUS in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ProfNUS home folder.

--------------------------------------------------------------------------------------------------------------------


## 6. Command summary

### 6.1. User Interface Command Summary

| Action                                                  | Format                                                       | Example                                                      |
|---------------------------------------------------------| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [Switch to light mode](#411-switch-to-light-mode-light) | `light`                                                                                                            | `light`                                                                                                                                                                   |
| [Switch to dark mode](#412-switch-to-dark-mode-dark)    | `dark`                                                                                                             | `dark`                                                                                                                                                                    |
| [View help](#413-viewing-help-help)                     | `help`                                                                                                             | `help`                                                                                                                                                                     |

<div style="page-break-after: always;"></div>

### 6.2. Module Manager Command Summary

| Action                                                                                                       | Format                                                                         | Example                                                                              |
|--------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| [List all modules](#422-viewing-list-of-modules-mlist)                                                       | `mlist`                                                                        | `mlist`                                                                              |
| [View more information about a module](#423-viewing-more-details-of-a-module-in-the-list-of-modules-vtarget) | `vtarget INDEX`                                                                | `vtarget 3`                                                                          |
| [View students and teaching assistants of a module](#424-viewing-module-details-mview)                       | `mview c/MODULE_CODE`                                                          | `mview c/CS1101S`                                                                    |
| [Add a module](#425-adding-a-module-madd)                                                                    | `madd n/MODULE_NAME c/MODULE_CODE d/MODULE_DESCRIPTION [t/TAG]…​`              | `madd n/Computer Organisation c/CS2100 d/Teaches you more on computer hardware t/Y2` |
| [Editing a module](#426-editing-a-module-medit)                                                              | `medit MODULE_CODE n/MODULE_NAME c/MODULE_CODE d/MODULE_DESCRIPTION [t/TAG]…​` | `medit cs2100 n/Database systems c/cs2102 d/SQL t/important`                         |
| [Delete a module](#427-deleting-a-module-mdel)                                                               | `mdel c/MODULE_CODE`                                                           | `mdel c/CS1101S`                                                                     |

<div style="page-break-after: always;"></div>

### 6.3. Student Manager Command Summary

| Action                                                                                           | Format                                                                                                                                         | Example                                                                                                                                                                |
|--------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [List all students/teaching assistants](#432-listing-all-students-list)                          | `list`                                                                                                                                         | `list`                                                                                                                                                                 |
| [Add a student/teaching assistant](#433-adding-a-student-or-teaching-assistant-add)              | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS i/STUDENT_ID h/TELEGRAM [t/TAG]…​ [c/MODULE_CODE]…​ [ta/MODULE_CODE]…​ [cg/CLASS_GROUP]…​`        | `add n/Peter Lim p/98413235 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney i/A0243456G h/@good_student c/CS2030S ta/CS1101S cg/CS2030S:Tut07` |
| [Edit a student](#434-editing-a-student-editstu)                                                 | `editstu INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/STUDENT_ID] [h/TELEGRAM] [t/TAG]…​ [c/MODULE]…​ [ta/TAMODULE]…​ [cg/CLASS_GROUP]…​` | `editstu 1 p/91234567 e/johndoe@example.com` <br> `editstu 2 n/Betsy Crower c/`                                                                                        |
| [Edit a teaching assistant](#435-editing-a-teaching-assistant-editta)                            | `editta INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/STUDENT_ID] [h/TELEGRAM] [t/TAG]…​ [c/MODULE]…​ [ta/TAMODULE]…​ [cg/CLASS_GROUP]…​`  | `editta 1 p/97384723 e/davidlim@example.com` <br> `editta 2 n/Beth ta/CS2030S`                                                                                         |
| [Delete a student](#436-deleting-a-student-delstu)                                               | `delstu INDEX`                                                                                                                                 | `delstu 1`                                                                                                                                                             |
| [Delete a teaching assistant](#437-deleting-a-teaching-assistant-delta)                          | `delta INDEX`                                                                                                                                  | `delta 1`                                                                                                                                                              |
| [Find a student/teaching assistant](#438-locating-students-and-teaching-assistants-by-name-find) | `find KEYWORD [MORE_KEYWORD]`                                                                                                                  | `find Adam Do`                                                                                                                                                         |

<div style="page-break-after: always;"></div>

### 6.4. Schedule Manager Command Summary

| Action                                                    | Format                                                                                            | Example                                                          |
|-----------------------------------------------------------|---------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| [View your schedule list](#442-view-your-schedule-sview)  | `sview`                                                                                           | `sview`                                                          |
| [View your timetable](#443-view-your-timetable-tview)     | `tview [MODE]`                                                                                    | `tview v`                                                        |
| [Add your schedule](#444-add-your-schedule-sadd)          | `sadd c/MODULE_CODE w/WEEKDAY ct/PERIOD cc/CLASS_TYPE cg/CLASS_GROUP cv/VENUE`                    | `sadd c/CS1101S w/Tuesday ct/10:00-12:00 cc/lec cg/L1 cv/I3-AUD` |
| [Edit your schedule](#445-edit-your-schedule-sedit)       | `sedit INDEX [c/MODULE_CODE] [w/WEEKDAY] [ct/PERIOD] [cc/CLASS_TYPE] [cg/CLASS_GROUP] [cv/VENUE]` | `sedit 1 w/Monday ct/09:00-10:00 cc/tut cv/COM1-B103`            |
| [Delete your schedule](#446-delete-your-schedule-sdelete) | `sdelete INDEX`                                                                                   | `sdelete 1`                                                      |
| [Clear your schedule](#447-clear-your-schedule-sclear)    | `sclear [c/MODULE_CODE]...`                                                                       | `sclear c/CS2103T c/CS1101S`                                     |

<div style="page-break-after: always;"></div>

### 6.5. Miscellaneous Command Summary

| Action                                       | Format  | Example |
|----------------------------------------------|---------|---------|
| [Clear data](#45-clearing-all-entries-clear) | `clear` | `clear` |
| [Exit ProfNUS](#46-exiting-the-program-exit) | `exit`  | `exit`  |
