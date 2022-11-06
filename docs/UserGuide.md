---
layout: page
title: User Guide
---

**UniNurse** is a desktop app tailored for you, a **private duty nurse**, to:
- Manage your patients' contact details.
- Organize your patient-related tasks.
- Keep track of your patients' medical conditions.

<div markdown="block" class="alert alert-info">

**Fastest fingers first!**

If your fingers are as quick as your wit, UniNurse helps you to get your patient management tasks done in no time!
It leverages on a no-frills _Command Line Interface (CLI)_ to give fast typists such as yourself a painless user
experience.

</div>

UniNurse offers the following features:
- Add and edit patient details such as name, contact number, medical conditions, etc.
- Find and filter patients by their details.
- Add tasks to a patient.
- Show the tasks to be done on a specific day.
- Categorize patients using tags.

--------------------------------------------------------------------------------------------------------------------

### Using this guide

This guide walks you through all the features of UniNurse, as well as tips, so you can reap all the benefits of
UniNurse!

If you are a **new user**, this guide equips you with the necessary know-how to [get started](#quick-start)
with UniNurse.

If you are an **experienced user**, you can refer to the [Command Summary](#command-summary) at the end of this guide.

Here are some symbols used throughout this user guide:

| Symbol               | Meaning                                  |
|----------------------|------------------------------------------|
| :information_source: | Important things you should take note of |
| :bulb:               | Useful information                       |
| :exclamation:        | Warning                                  |
| :wrench:             | Help with common issues                  |

--------------------------------------------------------------------------------------------------------------------

### Table of Contents

1. [Quick start](#quick-start)
2. [Glossary](#glossary)
3. [Command format](#command-format)
4. [Patient parameter constraints](#patient-parameter-constraints)
   1. [Single-valued attributes](#single-valued-attributes)
   2. [Multi-valued attributes](#multi-valued-attributes)
   3. [Patient parameter summary](#patient-parameter-summary)
5. [Features](#features)
   1. [Getting help: `help`](#getting-help-help)
   2. [Modifying patient contact details](#adding-a-patient-add)
      1. [Adding a patient: `add`](#adding-a-patient-add)
      2. [Editing a patient's contact details: `edit` `-p`](#editing-a-patients-contact-details-edit--p)
      3. [Deleting a patient: `delete` `-p`](#deleting-a-patient-delete--p)
   3. [Modifying tags](#adding-a-tag-add--p)
      1. [Adding a tag: `add` `-p`](#adding-a-tag-add--p)
      2. [Editing a tag: `edit` `-p` `-t`](#editing-a-tag-edit--p--t)
      3. [Deleting a tag: `delete` `-p` `-t`](#deleting-a-tag-delete--p--t)
   4. [Modifying tasks](#adding-a-task-add--p)
      1. [Adding a task: `add` `-p`](#adding-a-task-add--p)
      2. [Editing a task: `edit` `-p` `-d`](#editing-a-task-edit--p--d)
      3. [Deleting a task: `delete` `-p` `-d`](#deleting-a-task-delete--p--d)
   5. [Modifying medical conditions](#adding-a-medical-condition-add--p)
      1. [Adding a medical condition: `add` `-p`](#adding-a-medical-condition-add--p)
      2. [Editing a medical condition: `edit` `-p` `-c`](#editing-a-medical-condition-edit--p--c)
      3. [Deleting a medical condition: `delete` `-p` `-c`](#deleting-a-medical-condition-delete--p--c)
   6. [Modifying medications](#adding-a-medication-add--p)
      1. [Adding a medication: `add` `-p`](#adding-a-medication-add--p)
      2. [Editing a medication: `edit` `-p` `-m`](#editing-a-medication-edit--p--m)
      3. [Deleting a medication: `delete` `-p` `-m`](#deleting-a-medication-delete--p--m)
   7. [Modifying remarks](#adding-a-remark-add--p)
      1. [Adding a remark: `add` `-p`](#adding-a-remark-add--p)
      2. [Editing a remark: `edit` `-p` `-r`](#editing-a-remark-edit--p--r)
      3. [Deleting a remark: `delete` `-p` `-r`](#deleting-a-remark-delete--p--r)
   8. [Viewing patients](#listing-all-patients-list)
      1. [Listing all patients: `list`](#listing-all-patients-list)
      2. [Viewing all details of a patient: `focus` `-p`](#viewing-all-details-of-a-patient-focus--p)
      3. [Finding patients: `find`](#finding-patients-find)
   9. [Viewing tasks](#viewing-all-tasks-for-a-particular-day-view)
      1. [Viewing all tasks for a particular day: `view`](#viewing-all-tasks-for-a-particular-day-view) 
      2. [Viewing all tasks for today: `view` `--today`](#viewing-all-tasks-for-today-view---today)
      3. [Viewing all tasks of a patient: `view` `-p`](#viewing-all-tasks-of-a-patient-view--p)
      4. [Viewing all tasks of all patients](#viewing-all-tasks-of-all-patients-view--p---all)
   10. [Undoing last command: `undo`](#undoing-previous-command-undo)
   11. [Reversing undo command: `redo`](#reversing-undo-command-redo)
   12. [Clearing all entries: `clear`](#clearing-all-entries-clear)
   13. [Exiting UniNurse](#exiting-uninurse-exit)
   14. [Saving the data](#saving-the-data)
   15. [Editing the data file](#editing-the-data-file)
6. [FAQ](#faq)
7. [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) or above
   installed on your computer. To check your Java version, open a Command Prompt or Terminal window and type:

```
java -version
```

2. Download the latest `uninurse.jar` [here](https://github.com/AY2223S1-CS2103T-T12-4/tp/releases).
3. Move `uninurse.jar` to the folder you want to use as the home folder for UniNurse.
4. Double-click the file to start UniNurse. A user interface similar to the one below should appear in a few seconds.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** The app comes with some sample patients by default. Type `clear` in the command box to remove them.

</div>

{:refdef: style="text-align: center;"}
![Ui](images/Ui.png)
{: refdef}

5. Type your command in the command box and hit `Enter` to execute the command. Here are some example commands
   you can try:
    * **`help`**: Opens the help window.
    * **`add`**`n/Jane Doe p/91234567 e/janed@example.com a/20 Anderson Road, block 123, #01-01`: Adds a
      patient named `Jane Doe` to your patient list.
    * **`delete`**`-p 3`: Deletes the 3rd patient shown in the current list.
    * **`list`**: Lists all patients.
    * **`clear`**: Deletes all patients shown in the current list.
    * **`exit`**: Exits from UniNurse.
6. Refer to the [Features](#features) below for details of each command. Alternatively, you may refer to the
   [Command Summary](#command-summary) at the end of this guide.

--------------------------------------------------------------------------------------------------------------------

## Glossary

**Here are some of the technical terminologies used:**

| Word                    | Meaning                                                                                                                                                                                                     |
|-------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Command                 | A sentence which the user inputs to perform a function.                                                                                                                                                     |
| Command word            | The first word of every command.                                                                                                                                                                            |
| Option                  | Part of the user input specifying the options for a command, which is preceded by a flag.                                                                                                                   |
| Flag                    | Part of the user input that allows the user to specify the specific options for a command, consisting of a letter preceded by a hyphen. <br> Type of flags: `-p`, `-t`, `-d`, `-c`, `-m`, `-r`.             |
| Parameter               | Part of the user input consisting of information supplied by the user to UniNurse, which is preceded by a prefix.                                                                                           |
| Prefix                  | Part of the user input that allows the user to specify information for a patient, consisting of a letter preceded by a hyphen. <br> Type of prefixes: `n/`, `p/`, `e/`, `a/`, `t/`, `d/`, `c/`, `m/`, `r/`. |
| Single-valued attribute | A patient's detail that consist of a single value. A patient's single-valued attributes are also called the patient's contact details. <br> Single-valued attributes: `NAME`, `PHONE`, `EMAIL`, `ADDRESS`.  |
| Multi-valued attribute  | A patient's detail that consist of a list of values. <br> Multi-valued attributes: `TAG`, `TASK`, `CONDITION`, `MEDICATION`, `REMARK`.                                                                      |

--------------------------------------------------------------------------------------------------------------------

## Command format

_To be cleaned up_

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* All flags and options must be specified before any prefix or parameter.

* Flags can be in any order.<br>
  e.g. if the command specifies `-p PATIENT_INDEX -t TASK_INDEX`, `-t TASK_INDEX -p PATIENT_INDEX` is also acceptable.

* There must be a space separating a flag and an option.

* All indices ***must be a positive integer*** 1, 2, 3, …

* `PATIENT_INDEX` refers to the index number shown in the displayed patient list.

* Indices for patient attributes (e.g. `TASK_INDEX`, `CONDITION_INDEX`, `TAG_INDEX`, etc.) refers to the index number shown in the attribute list of a patient.<br>
  e.g. `TASK_INDEX` refers to the index number show in the task list of a patient.

* Parameters in square brackets and angle brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
  e.g. `d/TASK_DESCRIPTION | <DATE TIME> | <INTERVAL TIME_PERIOD>` can be used as `d/task 1` or `d/task 1 | 22-3-22 1800`.

* Parameters with `…` after them can be used multiple times, including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

Things to add:
- explain pipe characters

--------------------------------------------------------------------------------------------------------------------

## Patient parameter constraints

A patient's attributes can be categorized into two: *single-valued attributes* and *multi-valued attributes*. A patient's single-valued attributes consist of their name, phone, email, and address, and their multi-valued attributes consist of their tags, tasks, conditions, medications, and remarks.

### Single-valued attributes

A patient's single-valued attributes are also referred to as the patient's contact details, which consist of the following.

#### `n/NAME`

`NAME` should only contain alphanumeric characters and spaces. 

`NAME` is specified by a prefix `n/`.

Example: `n/John Doe`

#### `p/PHONE`

`PHONE` should only contain numbers, and it should be at least 3 digits long. 

`PHONE` is specified by a prefix `p/`.

Example: `p/91234567`

#### `e/EMAIL`

`EMAIL` should be in the form of `local-part@domain`, where:
* `local-part` contains only alphanumeric characters and the special characters `+`, `_`, `.`, `-`, and may not start or end with any special characters.
* `domain` is made up domain labels separated by periods, where `domain` must:
  - end with a domain label at least 2 characters long.
  - have each domain label start and end with alphanumeric characters.
  - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

`EMAIL` is specified by a prefix `e/`.

Example: `johndoe@gmail.com`

#### `a/ADDRESS`

`ADDRESS` accepts any values.

`ADDRESS` is specified by a prefix `a/`.

Example:`a/John street, block 123, #01-01`

### Multi-valued attributes

#### `t/TAG`

`TAG` accepts any values.

`TAG` is specified by a prefix `t/`, and its index by a flag `-t`.

Example:`t/12-A nursing home`

#### `d/TASK_DESCRIPTION | DATE TIME | INTERVAL TIME_PERIOD`

The task parameter has the following constraints:

* `TASK_DESCRIPTION` accepts any values.
* `DATE TIME` should be in the format of `DD-MM-YY` or `DD-MM-YY HHMM`. <br>
   e.g. `2-7-22 1345`, `09-4-22`, `08-06-22 0900`, `7-06-22 2130` and `28-10-22` are all valid `DATE TIME`.
* `INTERVAL TIME_PERIOD` should be in the format of `X day(s)/week(s)/month(s)/year(s)` where `X` is a positive integer.

`TASK` is specified by a prefix `d/`, and its index by a flag `-d`.

Example: `d/Take CT scan | 23-11-22 1530 | 2 months`

#### `c/CONDITION`

`CONDITION` accepts any values.

`CONDITION` is specified by a prefix `c/`, and its index by a flag `-c`.

Example:`c/Parkinson's disease`

#### `m/MEDICATION_TYPE | DOSAGE`

`MEDICATION_TYPE` accepts any values.
`DOSAGE` should only contain alphanumeric characters, decimal points and spaces.

`MEDICATION` is specified by a prefix `m/`, and its index by a flag `-m`.

Example:`m/Amoxicillin | 0.5g every 8 hours`

#### `r/REMARK`

`REMARK` accepts any values.

`REMARK` is specified by a prefix `r/`, and its index by a flag `-r`.

Example:`r/Allergic to peanuts`

### Patient parameter summary

| Parameter    | Type                    | Flag | Prefix |
|--------------|-------------------------|------|--------|
| `NAME`       | Single-Valued Attribute | -    | `n/`   |
| `PHONE`      | Single-Valued Attribute | -    | `p/`   |
| `EMAIL`      | Single-Valued Attribute | -    | `e/`   |
| `ADDRESS`    | Single-Valued Attribute | -    | `a/`   |
| `TAG`        | Multi-Valued Attribute  | `-t` | `t/`   |
| `TASK`       | Multi-Valued Attribute  | `-d` | `d/`   |
| `CONDITION`  | Multi-Valued Attribute  | `-c` | `c/`   |
| `MEDICATION` | Multi-Valued Attribute  | `-m` | `m/`   |
| `REMARK`     | Multi-Valued Attribute  | `-r` | `r/`   |
| `PATIENT`    | -                       | `-p` | -      |

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* The value of an attribute is prefixed by its prefix.
* The index of a patient's multi-valued attribute is specified with its flag.
* The flag and the prefix of a multi-valued attribute are coded with the same letter.
* The patient index flag `-p` is an exception, as it has no corresponding prefix.

</div>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can navigate through your command history by using the `↑` and `↓` arrow keys.
* Two or more consecutive identical commands are saved once.
* Only successful commands are saved in the command history.
* The command history saves up to 100 previous commands.

</div>

<br>

### Getting help: `help`

You can access the help page with the `help` command.

Format: **`help`**

{:refdef: style="text-align: center;"}
![help message](images/helpMessage.png)
{: refdef}
<div align="center"><i> Help window displayed after running the </i><code>help</code><i> command </i></div>

<br>

### Adding a patient: `add`

You can add a patient to the patient list with the `add` command.

Format: **`add`**`n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]… [d/TASK_DESCRIPTION | <DATE TIME> | <INTERVAL TIME_PERIOD>]… [c/CONDITION]… [m/MEDICATION | DOSAGE]… [r/REMARK]…`

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can view the constraints for each parameter in the [Parameter constraints](#patient-parameter-constraints) section.

</div>

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You cannot add duplicate patients.
* Patients with the exact same name, phone, email, and address are considered duplicates; otherwise, they are considered to be distinct.

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can add any number of tags, tasks, medical conditions, medications and remarks to a patient.

</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/Administer 3ml of example medicine | 16-10-22 t/severe t/elderly` adds a patient with the following details:
  * Name: `John Doe`
  * Phone number: `98765432`
  * Email: `johnd@example.com`
  * Address: `John street, block 123, #01-01`
  * Tasks: `Administer 3ml of example medicine | 16-10-22`
  * Tags: `elderly`, `severe`
* `add n/Betsy Crowe c/Dementia c/High blood pressure e/betsy@example.com a/Jane street blk 420 #01-69 p/87901234` adds a patient with the following details:
  * Name: `Betsy Crowe`
  * Phone number: `87901234`
  * Email: `betsy@example.com`
  * Address: `Jane street blk 420 #01-69`
  * Medical conditions: `Dementia`, `High blood pressure`

<br>

### Editing a patient’s contact details: `edit` `-p`

You can edit the contact details of an existing patient in the patient list with the `edit` command.

Format: `edit -p PATIENT_INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can view the constraints for each parameter in the [Patient parameter constraints](#patient-parameter-constraints) section.

</div>

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You cannot edit tags, tasks, medical conditions, medications and remarks with this command.
* If there are duplicate patients after editing a patient, it will not be edited.

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to the [Table of contents](#table-of-contents) to find the section with the parameter you want to edit.

</div>

Example:

* `edit -p 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.

<br>

### Deleting a patient: `delete` `-p`

You can delete a patient from the patient list with the `delete` command.

Format: `delete -p PATIENT_INDEX`

Examples:
* `list` followed by `delete -p 2` deletes the 2nd patient in the patient list.
* `find Betsy` followed by `delete -p 1` deletes the 1st patient in the results of the `find` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can use the `undo` command to undo an accidental `delete` command.

</div>

<br>

### Adding a tag: `add` `-p`

You can add a tag to a patient with the `add` command.

Format: **`add`** `-p PATIENT_INDEX t/TAG`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You can only add one tag at a time.
* You cannot add duplicate tags.
* Tags are case-sensitive e.g. `high-risk` is distinct from `High-risk`.
* Tags are always sorted in lexicographical order.

</div>

Examples:
* `list` followed by `add -p 1 t/high-risk` adds the `high-risk` tag to the 1st patient in the patient list.
* `find Betsy` followed by `add -p 2 t/high-risk` adds the `high-risk` tag to the 2nd patient in the results of the `find Betsy` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can add multiple tags at once when you first [add a patient](#adding-a-patient-add).

</div>

<a name="edit-multi-valued-attributes"></a>
<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can understand the commands about multi-valued attributes as follows.

#### Adding a value to an attribute: `add` `-p`

You can add a value to a patient's attribute with the `add` command.

Format: **`add`**`-p PATIENT_INDEX xyz/XYZ_VALUE`

Example:
* `list` followed by `add -p 1 t/high-risk` adds the `high-risk` tag (attribute `t`) to the 1st patient in the patient list.

#### Editing a value of an attribute: `edit` `-p` `-xyz`

Format: **`edit`**`-p PATIENT_INDEX -xyz XYZ_INDEX xyz/XYZ_VALUE`

Example:
* `list` followed by `edit -p 2 -t 3 t/high-risk` edits the 3rd tag (attribute `t`) of the 2nd patient in the patient list to `high-risk`.


#### Deleting a value from an attribute: `edit` `-p` `-xyz`

Format: **`delete`**`-p PATIENT_INDEX -xyz XYZ_INDEX`

Example:
* `list` followed by `delete -p 2 -t 3` deletes the 3rd tag (attribute `t`) of the 2nd patient in the patient list.

</div>

<br>

### Editing a tag: `edit` `-p` `-t`

You can edit a tag of a patient with the `edit` command.

Format: **`edit`** `-p PATIENT_INDEX -t TAG_INDEX t/TAG`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You can only edit one tag at a time.
* If there are duplicate tags after editing a tag, it will not be edited.

</div>

Examples:
* `list` followed by `edit -p 2 -t 3 t/high-risk` edits the 3rd tag of the 2nd patient in the patient list to `high-risk`.
* `find Betsy` followed by `edit -p 1 -t 2 t/high-risk` edits the 2nd tag of the 1st patient in the results of the `find Betsy` command to `high-risk`.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Deleting a tag: `delete` `-p` `-t`

You can delete a tag of a patient with the `delete` command.

Format: **`delete`** `-p PATIENT_INDEX -t TAG_INDEX`

Examples:
* `list` followed by `delete -p 2 -t 3` deletes the 3rd tag of the 2nd patient in the patient list.
* `find Betsy` followed by `delete -p 1 -t 2` deletes the 2nd tag of the 1st patient in the results of the `find Betsy` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Adding a task: `add` `-p`

You can add a task or recurring task to a patient with the `add` command.

Format: **`add`** `-p PATIENT_INDEX d/TASK_DESCRIPTION | <DATE TIME> | <INTERVAL TIME_PERIOD>`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* `DATE TIME` and `INTERVAL TIME_PERIOD` must follow the criteria defined in [Task parameters](#patient-parameter-constraints).
* If `TIME` is omitted, the task will be created with a default time of `0000` hours.
* `If `DATE TIME` is omitted, the task will be created with a date and time 24 hours from the moment of creation.
* If the patient already contains tasks on `24-10-22` and `27-10-22`, the new task will be the 2nd task for the patient after the one on `24-10-22`.
* If `INTERVAL TIME_PERIOD` is omitted, then the task created will be a non-recurring task, i.e. one off task.
* Note that tasks are automatically sorted in chronological order upon being added. <br> 
  e.g. If the patient already contains tasks on `24-10-22` and `27-10-22`, the new task will be the 2nd task for the patient after the one on `24-10-22`.
* If the day portion of the date exceeds the last day of that calendar month, it would default to the last day of the month. <br>
  e.g `31-4-22` will be automatically converted to `30-4-22` or `30-2-20` will be converted to `29-2-20` since 2020 is a leap year.

</div>

<div markdown="block" class="alert alert-warning">

:exclamation: **Caution:**
If you enter a `TIME` of `2400`, then the date and time will be set to `0000` hours of the next day.

</div>

Examples:
* `list` followed by `add -p 1 d/Administer 3ml of example medicine` adds a task to the 1st patient in the patient list.
* `find Betsy` followed by `add -p 2 d/Change dressing on left arm | 12-7-22` adds a task to the 2nd patient in results of the `find` command, on 12th July 2022 0000 hours.
* `add -p 3 d/Take X-rays | 23-4-22 1345 | 3 weeks` adds a recurring task to the 3rd patient for every 3 weeks starting from 23rd April 2022 1345 hours.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can add multiple tasks at once when you first [add a patient](#adding-a-patient-add).

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Editing a task: `edit` `-p` `-d`

You can edit a task of a patient with the `edit` command.

Format: **`edit`** `-p PATIENT_INDEX -d TASK_INDEX d/<TASK_DESCRIPTION> | <DATE TIME> | <INTERVAL TIME_PERIOD>`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* `DATE TIME` and `INTERVAL TIME_PERIOD` must follow the criteria defined in [Task parameters](#patient-parameter-constraints).
* If a `INTERVAL TIME_PERIOD` is provided for what was originally a non-recurring task, the edit will transform it into a recurring one based on the given frequency
* Tasks are automatically sorted in chronological order upon modification, i.e. if a task on `25-10-22` is edited to be `30-10-22`, its new `TASK INDEX` would be based on the displayed order in the patient's task list.

</div>

Examples:
* `list` followed by `edit -p 1 -d 1 d/Administer 3ml of example medicine` edits the description of the 1st task of the 1st patient in the patient list to `Administer 3ml of example medicine`, while retaining the original date and time for the task.
* `find Betsy` followed by `edit -p 2 -d 3 d/| 23-10-22 0800` edits the date and time of the 3rd task of the 2nd patient in results of the `find` command to 23rd October 2022 0800 hours, while retaining the original description for the task.
* `list` followed by `edit -p 1 -d 1 d/| | 3 days` edits the recurrence of the 1st task of the 1st patient if the task was a recurring task to every 3 days, while keeping the original description, date and time. If the task was a non-recurring task, then this edit transforms the task into a recurring task with a recurrence of every 3 days.
* `list` followed by `edit -p 2 -d 3 d/| 25-10-22 | 2 weeks` edits the date and recurrence of the 3rd task fo the 2nd patient in the patient list to 25th october 2022 and every 2 weeks, while keeping the original description and time. If the task was a non-recurring task, then this edit transforms the task into a recurring task with a recurrence of every 2 weeks.
* `find David` followed by `edit -p 1 -d 2 d/Change bandage | | 4 days` edits the description and recurrence of the 2nd task of the 1st patient in the results of `find` command to `Change bandage` and every 4 days, while keeping the original date and time. If the task was a non-recurring task, then this edit transforms the task into a recurring task with a recurrence of every 4 days.

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You must always provide a `DATE` if you want to change the `TIME`. <br>
  e.g. to change from `25-10-22 0800` to `25-10-22 0900`, the edit would be `d/| 25-10-22 0900`.

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Deleting a task: `delete` `-p` `-d`

You can delete a task of a patient with the `delete` command.

Format: **`delete`** `-p PATIENT_INDEX -d TASK_INDEX`

Examples:
* `list` followed by `delete -p 2 -d 3` deletes the 3rd task of the 2nd patient in the patient list.
* `find Betsy` followed by `delete -p 1 -d 2` deletes the 2nd task of the 1st patient in results of the `find` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Adding a medical condition: `add` `-p`

You can add a medical condition to a patient with the `add` command.

Format: **`add`** `-p PATIENT_INDEX c/CONDITION`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You can only add one medical condition at a time.
* You cannot add duplicate medical conditions.
* Medical conditions are case-sensitive e.g. `diabetes` is distinct from `Diabetes`.

</div>

Examples:
* `list` followed by `add -p 1 c/Diabetes` adds the `Diabetes` condition to the 1st patient in the patient list.
* `find Betsy` followed by `add -p 2 c/Alzheimer's disease` adds the `Diabetes` condition to the 2nd patient in the results of the `find Betsy` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can add multiple medical conditions at once when you first [add a patient](#adding-a-patient-add).

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Editing a medical condition: `edit` `-p` `-c`

You can edit a medical condition of a patient with the `edit` command.

Format: **`edit`** `-p PATIENT_INDEX -c CONDITION_INDEX c/CONDITION`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You can only edit one medical condition at a time.
* If there are duplicate medical conditions after editing a medical condition, it will not be edited.

</div>

Examples:
* `list` followed by `edit -p 2 -c 3 c/Diabetes` edits the 3rd condition of the 2nd patient in the patient list to `Diabetes`.
* `find Betsy` followed by `edit -p 1 -c 2 c/Diabetes` edits the 2nd condition of the 1st patient in the results of the `find Betsy` command to `Diabetes`.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Deleting a medical condition: `delete` `-p` `-c`

You can delete a medical condition of a patient with the `delete` command.

Format: **`delete`** `-p PATIENT_INDEX -c CONDITION_INDEX`

Examples:
* `list` followed by `delete -p 2 -c 3` deletes the 3rd condition of the 2nd patient in the patient list.
* `find Betsy` followed by `delete -p 1 -c 2` deletes the 2nd condition of the 1st patient in the results of the `find Betsy` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Adding a medication: `add` `-p`

You can add a medication to a patient with the `add` command.

Format: **`add`** `-p PATIENT_INDEX m/MEDICATION_TYPE | DOSAGE`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You can only add one medication at a time.
* You cannot add duplicate medications.
* Medications are considered duplicates only when both `MEDICATION_TYPE` and `DOSAGE` are the same e.g. `Paracetamol | 1 tab every 6 hours` is different from `Paracetamol | 2 tabs every 6 hours`.
* Medications are case-sensitive e.g. `Paracetamol` is distinct from `paracetamol`.

</div>

Examples:
* `list` followed by `add -p 1 m/Paracetamol | 2 tabs every 6 hours` adds the `Paracetamol` medication with the dosage `2 tabs every 6 hours` to the 1st patient in the patient list.
* `find Alice` followed by `add -p 2 m/Amoxicillin | 0.5g every 8 hours` adds the `Amoxicillin` medication with the dosage `0.5g every 8 hours` to the 2nd patient in the results of the `find Alice` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can add multiple medications at once when you first [add a patient](#adding-a-patient-add).

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Editing a medication: `edit` `-p` `-m`

You can edit a medication of a patient with the `edit` command.

Format: **`edit`** `-p PATIENT_INDEX -m MEDICATION_INDEX m/<MEDICATION_TYPE> | <DOSAGE>`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You can only edit one medication at a time.
* If no new `MEDICATION_TYPE` or `DOSAGE` are provided, then original values will be used.
* At least one of `MEDICATION_TYPE` or `DOSAGE` must be present, or else the medication will not be edited.
* If there are duplicate medications after editing a medication, it will not be edited.

</div>

Examples:
* `list` followed by `edit -p 1 -m 1 d/Amoxicillin` edits the medication type of the 1st medication of the 1st patient in the patient list to `Amoxicillin`, while retaining the original dosage.
* `find Alice` followed by `edit -p 2 -m 3 d/| 2 tabs every 6 hours` edits the dosage of the 3rd medication of the 2nd patient in results of the `find Alice` command, while retaining the original medication type.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Deleting a medication: `delete` `-p` `-m`

You can delete a medication of a patient with the `delete` command.

Format: **`delete`** `-p PATIENT_INDEX -m MEDICATION_INDEX`

Examples:
* `list` followed by `delete -p 2 -m 3` deletes the 3rd medication of the 2nd patient in the patient list.
* `find Alice` followed by `delete -p 1 -m 2` deletes the 2nd medication of the 1st patient in the results of the `find Alice` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Adding a remark: `add` `-p`

You can add a remark to a patient with the `add` command.

Format: **`add`** `-p PATIENT_INDEX r/REMARK`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You can only add one remark at a time.
* You cannot add duplicate remarks.
* Remarks are case-sensitive e.g. `allergic to peanuts` is distinct from `Allergic to Peanuts`.

</div>

Examples:
* `list` followed by `add -p 1 r/Requires wheelchair to move around` adds the `Requires wheelchair to move around` remark to the 1st patient in the patient list.
* `find Rachel` followed by `add -p 2 r/Allergic to Peanuts` adds the `Allergic to Peanuts` remark to the 2nd patient in the results of the `find Rachel` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can add multiple remarks at once when you first [add a patient](#adding-a-patient-add).

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Editing a remark: `edit` `-p` `-r`

You can edit a remark of a patient with the `edit` command.

Format: **`edit`** `-p PATIENT_INDEX -r REMARK_INDEX r/REMARK`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* You can only edit one remark at a time.
* If there are duplicate remarks after editing a remark, it will not be edited.

</div>

Examples:
* `list` followed by `edit -p 2 -r 3 r/Allergic to Amoxicillin` edits the 3rd remark of the 2nd patient in the patient list to `Allergic to Amoxicillin`.
* `find Rachel` followed by `edit -p 1 -r 2 r/Allergic to Amoxicillin` edits the 2nd remark of the 1st patient in the results of the `find Rachel` command to `Allergic to Amoxicillin`.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Deleting a remark: `delete` `-p` `-r`

You can delete a medical condition of a patient with the `delete` command.

Format: **`delete`** `-p PATIENT_INDEX -r REMARK_INDEX`

Examples:
* `list` followed by `delete -p 2 -r 3` deletes the 3rd remark of the 2nd patient in the patient list.
* `find Rachel` followed by `delete -p 1 -r 2` deletes the 2nd remark of the 1st patient in the results of the `find Rachel` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can refer to [this tip](#edit-multi-valued-attributes) to better understand the commands about muti-valued attributes.

</div>

<br>

### Listing all patients: `list`

You can view a list of all patients using the `list` command.

Format: `list`

<br>

### Viewing all details of a patient: `focus` `-p`

You can view all details of a specified patient using the `focus` command.

Format: `focus -p PATIENT_INDEX`

Examples:
* `list` followed by `focus -p 2` shows all details of the 2nd patient in the patient book.
* `find Betsy` followed by `focus -p 1` shows all details of the 1st patient in the results of the `find` command.

_Add screenshot here_

<br>

### Finding patients: `find`

You can find specific patients using the `find` command.

Format: **`find`**`[KEYWORD]… [xyz/XYZ_KEYWORD]…`

* `xyz/XYZ_KEYWORD` refers to a keyword for a specific attribute with prefix `xyz`.
* There should be at least one parameter for the command.
* The search is case-insensitive. e.g `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Partial words can be matched e.g. `Han` will match `Hans`.
* Patients matching at least one keyword in every attribute will be returned (i.e. AND search for different attributes, OR search for same attribute). In more details,
    * At least one of the patient's attributes (name, phone, email, address, tag, task description, condition, medication, or remark) must match with at least one `KEYWORD` (unless it is empty).
    * For every different attribute with prefix `xyz`, it must match at least one `XYZ_KEYWORD` (unless it is empty).

Examples:
* `find key n/John n/Betsy n/Charlie e/@example.com e/@u.nus.edu` displays all patients who fulfill all conditions below:
    * The patient's name must contain either `John` or `Betsy` or `Charlie`.
    * The patient's email address must contain either `@example.com` or `@u.nus.edu`.
    * At least one of the patient's details must contain `key` (e.g., one of their tags contains `key`).
* `find jo` displays patients with names `Joe` and `John`, patients with emails `jo@example.com`, and patients with tag `joints`.
* `find n/alice n/meier` displays patients `Alice Tan` and `Benson Meier`.

{:refdef: style="text-align: center;"}
![result for 'find alice meier'](images/findAliceMeierResult.png)
{: refdef}
<div align="center"><i> Patient list displayed after running the </i><code>find alice meier</code><i> command </i></div>

<br>

### Viewing all tasks for a particular day: `view`

You can view the list of tasks for a particular day using the `view` command.

Format: **`view`** `DATE`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:**
* The DATE **must be of the specified format** dd-MM-yy
</div>

Examples:
* `view 5-9-22` lists the tasks on 5th September 2022
* `view 25-11-22` lists the tasks on 25th November 2022

{:refdef: style="text-align: center;"}
![result for `view 25-11-22`](images/viewSpecificDayResult.png)
{: refdef}
<div align="center"><i> Patient and task list displayed after running the </i><code>view 25-11-22</code><i> command </i></div>

<br>

### Viewing all tasks for today: `view` `--today`

You can view the list of tasks that are due today using the `view` command with the special flag `--today`.

Format: **`view`** `--today`

Examples:

Let's say you added the following patients and their tasks:
* **Physiotherapy appointment** for **Alex Yeoh** at 12:00pm on 2022-11-04
* **Administer insulin dose** for **Charlotte Oliveiro** at 11:45am on 2022-11-04

If today's date is 2022-11-04, `view --today` will display those 2 tasks.

{:refdef: style="text-align: center;"}
![result for `view --today`](images/viewTodayResult.png)
{: refdef}
<div align="center"><i> Patient and task list displayed after running the </i><code>view --today</code><i> command </i></div>

<br>

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** This command replaces `DATE` in the [previous command](#viewing-all-tasks-for-a-particular-day-view) with the special flag `--today`.

</div>

<br>

### Viewing all tasks of a patient: `view` `-p`

You can view the list of tasks for a particular patient using the `view` command.

Format: **`view`** `-p PATIENT_INDEX`

Examples:

* `list` followed by `view -p 2` will show the list of tasks for the 2nd patient in the patient list.

{:refdef: style="text-align: center;"}
![result for `view -p 2`](images/viewBerniceTaskResult.png)
{: refdef}
<div align="center"><i> Patient and task list displayed after running the </i><code>view -p 2</code><i> command </i></div>

<br>

### Viewing all tasks of all patients: `view` `-p` `--all`

You can view the list of tasks for all patients using the `view` command with the special flag `--all`.

Format: **`view`** `-p --all`

Examples:

* `view -p --all` will show a list of all tasks belonging to patients with tasks.

{:refdef: style="text-align: center;"}
![result for `view -p --all`](images/viewAllTaskResult.png)
{: refdef}
<div align="center"><i> Patient and task list displayed after running the </i><code>view -p --all</code><i> command </i></div>

<br>

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** This command replaces `PATIENT_INDEX` in the [previous command](#viewing-all-tasks-of-a-patient-view--p) with the special flag `--all`.

</div>

<br>

### Undoing previous command: `undo`

You can undo the last command which modifies the application data with the `undo` command.

Format: `undo`

<div markdown="block" class="alert alert-info">

:information_source: **Notes:** 
* Commands which modifies the application data are the `add`, `edit`, `delete`, and `clear` commands.

</div>

Examples:
* `delete -p 2` followed by `undo` has the same effect as not doing the `delete` command.
* `delete -p 2` followed by `list`, then followed by `undo` will undo the `delete` command.

<div markdown="span" class="alert alert-warning">
:exclamation: **Caution:**
Only the last 100 commands which modifies the application data can be undone.
data file at the next run.
</div>

<br>

### Reversing undo command: `redo`

You can undo the last `undo` command with the `redo` command.

Format: `redo`

Example:
* `undo` followed by `redo` has the same effect as not using the `undo` command.
* Two consecutive `undo` commands followed by `redo` has the same effect as using the `undo` command once.

<br>

### Clearing all entries: `clear`

You can clear all patient entries in the displayed patient list with the `clear` command.

Format: **`clear`**

Examples:
* `list` followed by `clear` will delete all patients.
* `find Betsy` followed by `clear` deletes all patients in the results of the `find Betsy` command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:** You can use the `undo` command to undo an accidental `clear` command.

</div>

<br>

### Exiting UniNurse: `exit`

You can exit UniNurse with the `exit` command.

Format: **`exit`**

<br>

### Saving the data

UniNurse data are saved in the hard disk automatically after any command that changes the data.
You do not need to save manually.

<br>

### Editing the data file

UniNurse data are saved as a JSON file `[JAR file location]/data/uninurse.json`.
If you are an advanced user, feel free to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">
:exclamation: **Caution:**
If your changes to the data file makes its format invalid, UniNurse will discard all data and start with an empty
data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous UniNurse home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                                  | Format                                                                                                      |
|-----------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Help**                                | `help`                                                                                                      |
| **Add patient**                         | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]… [d/TASK]… [c/CONDITION]… [m/MEDICATION]… [r/REMARK]…` |
| **Edit patient**                        | `edit -p PATIENT_INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`                                     |
| **Delete patient**                      | `delete -p PATIENT_INDEX`                                                                                   |
| **Add tag**                             | `add -p PATIENT_INDEX t/TAG`                                                                                |
| **Edit tag**                            | `edit -p PATIENT_INDEX -t TAG_INDEX t/TAG`                                                                  |
| **Delete tag**                          | `delete -p PATIENT_INDEX -t TAG_INDEX`                                                                      |
| **Add task**                            | `add -p PATIENT_INDEX d/TASK`                                                                               |
| **Edit task**                           | `edit -p PATIENT_INDEX -d TASK_INDEX d/TASK`                                                                |
| **Delete task**                         | `delete -p PATIENT_INDEX -d TASK_INDEX`                                                                     |
| **Add condition**                       | `add -p PATIENT_INDEX c/CONDITION`                                                                          |
| **Edit condition**                      | `edit -p PATIENT_INDEX -c CONDITION_INDEX c/CONDITION`                                                      |
| **Delete condition**                    | `delete -p PATIENT_INDEX -c CONDITION_INDEX`                                                                |
| **Add medication**                      | `add -p PATIENT_INDEX m/MEDICATION`                                                                         |
| **Edit medication**                     | `edit -p PATIENT_INDEX -m MEDICATION_INDEX m/MEDICATION`                                                    |
| **Delete medication**                   | `delete -p PATIENT_INDEX -m MEDICATION_INDEX`                                                               |
| **Add remark**                          | `add -p PATIENT_INDEX r/REMARK`                                                                             |
| **Edit remark**                         | `edit -p PATIENT_INDEX -r REMARK_INDEX r/REMARK`                                                            |
| **Delete remark**                       | `delete -p PATIENT_INDEX -r REMARK_INDEX`                                                                   |
| **List all patients**                   | `list`                                                                                                      |
| **View all details of a patient**       | `focus -p PATIENT_INDEX`                                                                                    |
| **Find patients**                       | `find [KEYWORD]… [xyz/XYZ_KEYWORD]…`                                                                        |
| **View all tasks for a particular day** | `view DATE`                                                                                                 |
| **View all tasks for today**            | `view --today`                                                                                              |
| **View all tasks of a patient**         | `view -p PATIENT_INDEX`                                                                                     |
| **View all tasks of all patients**      | `view -p --all`                                                                                             |                                                                                                                                           
| **Undo previous command**               | `undo`                                                                                                      |
| **Reverse undo command**                | `redo`                                                                                                      |
| **Clear all entries**                   | `clear`                                                                                                     |
| **Exit**                                | `exit`                                                                                                      |
