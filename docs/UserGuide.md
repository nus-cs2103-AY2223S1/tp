---
layout: page
title: User Guide
---

MODPRO is a desktop application which helps NUS students in tracking the progress of their modules. It is highly optimised for students who prefer Command Line Interface (CLI) by allowing those who type fast to key in commands to track their modules. It also provides a Graphic User interface(GUI) for those who prefer it.

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Modules-Related Features](#modules-related-features)
    - [Adding a module](#adding-a-module)
    - [Listing modules](#listing-modules)
    - [Finding modules](#finding-modules)
    - [Deleting a module](#deleting-a-module)
    - [Editing a module](#editing-a-module)
  - [Tasks-Related Features](#tasks-related-features)
    - [Adding a task](#adding-a-task)
    - [Deleting a task](#deleting-a-task)
    - [Editing a task](#editing-a-task)
    - [Marking a task](#marking-a-task)
    - [Unmarking a task](#unmarking-a-task)
    - [Listing tasks](#listing-tasks)
    - [Filtering the task list](#filtering-the-task-list)
    - [Finding tasks](#finding-tasks)
    - [Sorting the task list](#sorting-the-task-list)
    - [Adding a tag to a task](#adding-a-tag-to-a-task)
    - [Editing the tag of a task](#editing-the-tags-of-a-task)
    - [Deleting the tags of a task](#deleting-the-tags-of-a-task)
    - [Clearing the task list](#clearing-the-task-list)
  - [Exams-Related Features](#exams-related-features)
    - [Adding an exam](#adding-an-exam)
    - [Editing an exam](#editing-an-exam)
    - [Deleting an exam](#deleting-an-exam)
    - [Linking an exam](#linking-an-exam)
    - [Unlinking an exam](#unlinking-an-exam)
    - [Showing the tasks of an exam](#showing-the-tasks-of-an-exam)
  - [Other Features](#other-features)
    - [Clearing all the lists](#clearing-all-the-lists)
    - [Help](#help)
    - [Exiting the program](#exiting-the-program)
- [Summary Of Commands](#summary-of-commands)


--------------------------------------------------------------------------------------------------------------------

## Quick start 

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `modpro.jar` from [here](https://github.com/AY2223S1-CS2103T-F11-2/tp/releases/download/v1.3.1/modpro.jar).

3. Copy the file to the folder you want to use as the _home folder_ for MODPRO.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. <br>
   ![Ui](images/Ui.png)

5. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="span" class="alert alert-info">
**:information_source: How to read the command format:**<br>
  
* Words in UPPER_CASE are the parameters to be supplied by the user.<br>
  e.g. in `t add m/MODULE`, `MODULE` is a parameter which can be used as `t add m/CS2030`.
  
* Items in square brackets are optional.<br>
  e.g `t/KEYWORD [SECOND_KEYWORD]` can be used as `t/priority deadline` or as `t/priority`.
  
* Items in square brackets with * are optional, but at least one of them is required.<br>
  e.g `[m/MODULE]* [d/DESCRIPTION]*` can be used as `m/cs2030` or `m/cs2030 d/assignment` but not ` `(none provided).
  
* Parameters can be in any order.<br>
  e.g. if the command specifies `m/MODULE d/DESCRIPTION`, `d/DESCRIPTION m/MODULE` is also acceptable.
  
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `m/cs2030 m/cs2040`, only `m/cs2040` will be taken.
  
* Extraneous parameters for commands that do not take in parameters (such as help, list, exit and clear) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
</div>

## Modules-related Features

### Adding a module
Adds the module into the module list.

Format: `m add c/MODULE_CODE m/MODULE_NAME mc/MODULAR_CREDIT`
* Adds a module with its module code, module name and modular credit into
the module list.
* `MODULE_CODE` refers to the module code of the module.
* `MODULE_NAME` refers to the name of the module.
* `MODULAR_CREDIT` refers to the number of modular credits that the module has.
* `MODULE_CODE` must be at least 6 characters long and the first two characters
of the module code must be an alphabetical character.
* `MODULAR_CREDIT` must be at least 0 and not more than 45.
* If the module name is empty, an error message will be displayed.

Examples:

`m add c/cs2100 m/computer organisation mc/4` adds a module with the module
code `cs2100`, the module name `computer organisation` and the modular credit `4`

### Listing Modules
Lists modules stored in module list.

Format: `m list`
* Displays modules that are stored in the module list.

Example:

`m list` displays modules that are stored in the module list.

### Finding module(s)
Finds modules stored in the module list by the module code.

Format: `m find KEYWORD`
* Finds all modules whose module code contains the `KEYWORD` inputted by the user partially or fully and displays them as a list.
* The `KEYWORD` is case-insensitive. For example, one can find a module with the module code of 'cs2030s' even if the `KEYWORD` inputted is ‘CS2030S’

Examples:

`m find cs` finds modules whose module code contains the `KEYWORD` 'cs' such as 'cs2030s', 'cs2040s'

`m find 30` finds modules whose module code contains the `KEYWORD` '30' such as 'cs2030s'

### Deleting a module
Deletes the specified module according to the index given.

Format: `m del INDEX`
* Deletes the module at the specified `INDEX` from the module list.
* `INDEX` refers to the index number shown in the module list.
* `INDEX` must be a positive integer 1, 2, 3, …​
* If `INDEX` is non-positive or more than the number of modules in the module list, an error message will be displayed.

Example:

`m del 1` deletes the first module in the module list.

`m del 3` deletes the third module in the module list.

<div markdown="span" class="alert alert-info">
:information_source: **Note:** All tasks and exams related to the module will be deleted after the module is deleted.
</div>

### Editing a module
Edits the specified module by updating the existing values to the input values.

Format: `m edit INDEX (must be a positive integer) [c/MODULE_CODE]* [m/MODULE_NAME]* [mc/MODULE_CREDIT]*`
* Edits the module at the specified `INDEX` in the exam list.
* `MODULE_CODE` refers to the module code of the module
* `MODULE_NAME` refers to the name of the module
* `MODULE_CREDIT` refers to the number of modular credits that the module has
* `INDEX` must be a positive integer 1, 2, 3, …
* If `INDEX` is a non-positive or more than the number of modules in the module list, an error message will be displayed.
* The module cannot be edited if its module code is similar to an existing module in the module list. An error message will be displayed to inform the user that the module already exists in the module list.
* At least one optional field of the module to edit must be provided. Otherwise, an error message will be shown.
* `MODULE_CODE` must be at least 6 characters long and the first two characters of the module code must be an alphabetical character.
* `MODULE_NAME` should not be empty. Otherwise, an error message will be displayed to the user.
* `MODULE_CREDIT` should not be empty and must be an integer between 0 and 20 inclusive.

Examples:

`m edit 1 m/Programming Methodology I mc/4` changes the module name of the first module in the module list to ‘Programming Methodology I` and the module credit to ‘4’.

`m edit 2 c/cs2040 m/Data Structures and Algorithms mc/4` changes the module name of the second module in the module list to ‘Data Structures and Algorithms’, the module code to ‘cs2040’ and the module credit to ‘4’.

<div markdown="span" class="alert alert-info">
:information_source: **Note:** If the module code of the module is edited, and the module is linked to some tasks and exams, the module of these tasks and exams will be changed to this edited module.
</div>


## Tasks-related Features

### Adding a task
Adds task into task list.

Format: `t add m/MODULE d/DESCRIPTION`
* Adds a task to the task list.
* `MODULE` refers to the module code of the module which the task belongs to.
* `DESCRIPTION` refers to the task description to be shown.
* `MODULE` has to exist in the module list and be a valid module code with at least 6 characters long with the first two being alphabetic characters. Otherwise, an error message will be displayed.
* If `DESCRIPTION` is empty, an error message will be displayed.

Example:
`t add m/CS2105 d/Assignment 1` adds a task with the module as 'CS2105' and description as 'Assignment 1' into the task list.

### Deleting a task 
Deletes the specified task according to the index given

Format: `t del INDEX`
* Deletes the task at the specified `INDEX` from the task list. 
* The `INDEX` refers to the index number shown in the task list.
* The `INDEX` must be a positive integer 1, 2, 3, …
* If the task list is empty and the user inputs any number, an error message will be displayed.
* If the index is non-positive or more than the number of tasks in the list, an error message will be displayed.

Examples:

`t del 1` deletes the first task in the task list

`t del 3` deletes the third task in the task list

<div markdown="span" class="alert alert-info">
:information_source: **Note:** When the task is deleted, if it has a link to an exam, the task will be unlinked from the exam.
</div>

### Editing a task
Edits the specified task, by updating the existing values to the input values.

Format: `t edit INDEX [m/MODULE]* [d/DESCRIPTION]*`
* Edits the task at the specified `INDEX` in the task list. 
* At least one of the optional fields must be provided.
* The input values should not be the same as existing values.
* If `INDEX` is non-positive or more than the number of tasks in the list, an error message will be displayed.

Examples:

`t edit 1 d/Assignment 2` changes the description of the first task in the task list to 'Assignment 2'.

`t edit 2 m/CS2040 d/tutorial 2` changes the module and description of the second task in the task list to 'CS2040' and 'tutorial 2' respectively.
<div markdown="span" class="alert alert-info">
:information_source: **Note:** If a task is linked to an exam, and its module is changed, the task will be unlinked from the exam.
</div>

### Marking a task
Indicates the specified task is completed

Format: `t mark INDEX`
* Indicates the task at the specified `INDEX` in the task list is completed. 
* The `INDEX` refers to the index number shown in the task list. 
* The `INDEX` must be a positive integer 1, 2, 3, …​
* If the `INDEX` is non-positive or more than the number of tasks in the list, an error message will be displayed.

Examples:

`t mark 1` indicates the first task in the task list is completed.

`t mark 3` indicates the third task in the task list is completed.

### Unmarking a task
Indicates the specified task is not completed

Format: `t unmark INDEX`
* Indicates the task at the specified `INDEX` in the task list is not completed. 
* The `INDEX` refers to the index number shown in the task list. 
* The `INDEX` must be a positive integer 1, 2, 3, …​
* If the index is non-positive or more than the number of tasks in the list, an error message will be displayed.

Examples:

`t unmark 1` indicates the first task in the task list is not completed.

`t unmark 3` indicates the third task in the task list is not completed.
<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** You can sort and filter tasks based on their completion status. The percentage of completed tasks are also shown for each exam and module. 
</div>

### Listing Tasks
Lists tasks stored in task list

Format: `t list`
* Displays tasks that are stored in the task list

Example:

`t list` displays tasks that are stored in the task list

### Filtering the task list
Filters the task list based on module code, completion status, and/or link status.

Format: `t filter [m/MODULE/]* [c/COMPLETED]* [l/LINKED]*`
* Filters the task list to show only tasks that fulfill all the stated conditions.
* `MODULE` refers to the module code to be filtered out.
* `COMPLETED` should be y to filter tasks that are complete or n to filter tasks that are incomplete.
* `LINKED` should be y to filter tasks that are linked to an exam or n to filter tasks that are not linked to any exam.
* At least one optional condition has to be specified, otherwise an error message will be displayed.

Examples:

`t filter m/cs2030 c/y` filters out all completed tasks that are under the module cs2030.

`t filter l/n` filters out all tasks that are currently not linked to any exam.

### Finding task(s)
Finds tasks stored in the task list by the task's description.

Format: `t find KEYWORD`
* Finds all tasks whose task description contains the `KEYWORD` inputted by the user partially or fully and displays them as a list.
* The `KEYWORD` is case-insensitive. For example, one can find a task with the task description of 'homework1' even if the `KEYWORD` inputted is ‘HOMEWORK1’

Examples:

`t find work` finds tasks that contain the `KEYWORD` 'work' such as 'homework1', 'homework2', 'worktodo'

`t find do paper` finds tasks that contain the `KEYWORD` 'do paper', such as 'do paper 1', 'do paper 2'

### Sorting the task list
Sorts the tasks in the task list based on the criteria specified

Format: `t sort c/CRITERIA`
* Sorts all the tasks in the task list based on the criteria specified
* `CRITERIA` can be either `priority`, `deadline`, `module` or `description`
* When sorting by `priority`, all tasks marked with priority status `HIGH` will appear
at the top of the displayed task list, followed by `MEDIUM`, `LOW` and lastly all tasks
with no priority status being at the bottom
* When sorting by `deadline`, tasks with the earliest deadline will appear at the top of 
the displayed list and tasks with no deadlines will appear at the bottom of the displayed task list
* When sorting by `module`, all tasks will be sorted by module code in alphanumeric order
* When sorting by `description`, all tasks will be sorted by the task description in alphanumeric order
* The criteria is not case-sensitive so `PRIORITY` is the same as `priority`

Examples:

`t sort c/priority` sorts all the tasks in the task list by priority status

`t sort c/deadline` sorts all the tasks in the task list by deadline

`t sort c/module` sorts all the tasks in the task list by module code

`t sort c/description` sorts all the tasks in the task list by task description


### Adding a tag to a task
Tags the priority status and the deadline to complete task 

Format: `t tagadd INDEX [p/PRIORITY_STATUS]* [dl/DEADLINE]*`
* A task can be tagged with either the priority status, the deadline or both 
* The task is tagged at the specified `INDEX`. 
* `INDEX` refers to the index number shown on the displayed task list. 
* `INDEX` must be a positive number 1, 2, 3, …​
* If `INDEX` is non-positive or greater than the number of tasks in the list, an error message
will be displayed.
* `PRIORITY STATUS` must be one of three priorities("high", "medium", "low")
* `DEADLINE` must be in the format `DD-MM-YYYY`
* `DEADLINE` must not be earlier than the current date

Examples:

`t tagadd 1 p/high` tags the first task in the displayed task list with the priority status of `HIGH`

`t tagadd 1 dl/31-12-2022` tags the first task in the displayed task list with the deadline of `31-12-2022`

`t tagadd 2 p/low dl/31-12-2022` tags the second task in the displayed task list with a priority status of 
`HIGH` and a deadline of `31-12-2022`

### Editing the tags of a task
Edits the tags associated with the task

Format: `t tagedit INDEX [p/PRIORITY_STATUS]* [dl/DEADLINE]*`
* Edits the tags that are currently tagged to the task
* To edit the priority status of the task, the task must already be tagged with the priority status
* To edit the deadline of the task, the task must already be tagged with the deadline
* The tags of the task at the specified `INDEX` is edited.
* `INDEX` refers to the index number shown on the displayed task list.
* The index must be a positive number 1, 2, 3, …​
* If the index is non-positive or greater than the number of tasks in the list, an error message
will be displayed.
* `PRIORITY STATUS` must be one of three priorities("high", "medium", "low")
* `DEADLINE` must be in the format `DD-MM-YYYY`
* `DEADLINE` must not be earlier than the current date

Examples:

`t tagadd 1 p/high` followed by `t tagedit 1 p/low` update priority status of the task
from `HIGH` to `LOW`

`t tagadd 1 dl/31-12-2022` followed by `t tagedit 1 dl/31-11-2022` updates the deadline of the task
from `31-12-2022` to `31-11-2022`

### Deleting the tags of a task
Deletes the tags associated with the task

Format: `t tagdel INDEX t/KEYWORD [SECOND_KEYWORD]`
* Deletes the tags that are currently tagged to the task
* `KEYWORD` and `SECOND_KEYWORD` indicate the kind of tags to be removed from
the task
* The list of keywords which can be used for `KEYWORD` and `SECOND_KEYWORD` are
priority and deadline
* The number of keywords which can be used must be at least 1 and not more
than 2
* The tag of the task at the specified `INDEX` is deleted
* `INDEX` refers to the index number shown on the displayed task list.
* `INDEX` must be a positive number 1, 2, 3, …​
* If `INDEX` is non-positive or greater than the number of tasks in the list, an error message 
will be displayed.
* If duplicate keywords are used, the duplicate keyword will be ignored
* The keywords are not case-sensitive so `PRIORITY` would be the same `priority`

Examples:

`t tagadd 1 p/high` followed by `t tagdel 1 t/priority` would delete the priority status
of the first task in the displayed task list

`t tagadd 1 dl/24-11-2022` followed by `t tagdel 1 t/deadline` would delete the deadline
of the first task in the displayed task list

`t tagadd 2 p/low dl/31-12-2022` followed by `t tagdel 2 t/priority deadline` would delete 
the priority status and deadline of the second task in the displayed task list

### Clearing the task list
Clears the entire task list.

Format: `t clear`
* Clears all tasks currently in the task list.

Example:
`t clear` clears all tasks currently in the task list.

--------------------------------------------------------------------------------------------------------------------

## Exams-related Features

### Adding an exam
Adds exam into exam list. 

Format: `e add m/MODULE ex/EXAM_DESCRIPTION ed/EXAM_DATE`

* Adds an exam to the exam list.
* `MODULE` refers to the module code of the module of the exam
* `EXAM_DESCRIPTION` refers to the description of the exam
* `EXAM_DATE` refers to the date of the exam
* The exam cannot be added if it is the same exam as an existing exam in the exam list. Otherwise, an error message will be displayed.
* `MODULE` has to exist in the module list and be a valid module code with at least 6 characters long with the first two being alphabetic characters. Otherwise, an error message will be displayed. 
* `EXAM_DESCRIPTION` should not be empty. Otherwise, an error message will be displayed to the user. 
* `EXAM_DATE` must be in the format `DD-MM-YYYY`, otherwise an error message will be shown. For example, 2022-12-28, 20-13-2022 are not in `DD-MM-YYYY` format
* `EXAM_DATE` must not be earlier than today's date, otherwise an error message will be shown. For example, 20-08-2022 is an invalid input if the current date is 26-10-2022.

Examples:

`e add m/cs2030s ex/midterms ed/20-11-2022` adds the exam with the exam module as 'cs2030s',
exam description as 'midterms', exam date as '20-11-2022' into the exam list. 


### Editing an exam
Edits the specified exam by updating the existing values to the input values.
   
Format: `e edit INDEX (must be a positive integer) [m/MODULE]* [ex/EXAM_DESCRIPTION]* [ed/EXAM_DATE]*`
* Edits the exam at the specified INDEX in the exam list.
* `MODULE` refers to the module code of the module of the exam 
* `EXAM_DESCRIPTION` refers to the description of the exam
* `EXAM_DATE` refers to the date of the exam
* `INDEX` must be a positive integer 1, 2, 3, … 
* If `INDEX` is a non-positive or more than the number of exams in the exam list, an error message will be displayed. 
* The exam cannot be edited if it is the same exam as an existing exam in the exam list. An error message will be displayed to inform the user that the exam already exists in the exam list. 
* At least one optional field of the exam to edit must be provided. Otherwise, an error message will be shown.
* `MODULE` has to exist in the module list and be a valid module code with at least 6 characters long with the first two being alphabetic characters. Otherwise, an error message will be displayed.
* `EXAM_DESCRIPTION` should not be empty. Otherwise, an error message will be displayed to the user. 
* `EXAM_DATE` must be in the format `DD-MM-YYYY`, otherwise an error message will be shown. For example, 2022-12-28, 20-13-2022 are not in `DD-MM-YYYY` format
* `EXAM_DATE` must not be earlier than today's date, otherwise an error message will be shown. For example, 20-08-2022 is an invalid input if the current date is 26-10-2022.

Examples:

`e edit 1 ex/finals ed/20-12-2022` changes the exam description of the first exam in the exam list to ‘finals’ and the exam date to ‘20-12-2022’.

`e edit 2 m/cs2030s ex/midterms ed/22-12-2022` changes the exam description of the second exam in the exam list to ‘midterms’, the exam module to ‘cs2030s’ and the exam date as ‘22-12-2022’.
<div markdown="span" class="alert alert-info">
:information_source: **Note:** If the module of the exam is edited, and the exam is linked to some tasks, the tasks would be unlinked to the exam.
</div>


### Deleting an exam
Deletes the specified exam according to the index given.

Format: `e del INDEX`
* Deletes the exam at the specified index from the exam list.
* `INDEX` refers to the index number shown in the exam list.
* `INDEX` must be a positive integer 1, 2, 3, …​
* If `INDEX` is non-positive or more than the number of exams in the exam list, an error message will be displayed.

Example:

`e del 1` deletes the first exam in the exam list.
<div markdown="span" class="alert alert-info">

:information_source:**Note:** All tasks currently linked to the exam will be unlinked after the exam is deleted.
</div>

### Linking an exam
Links the task in displayed task list to the exam in the exam list based on the indexes given.

Format `e link e/EXAM_INDEX t/TASK_INDEX`
* Links the exam at the specified index in the exam list to a task at the specified index
in the task list
* `EXAM_INDEX` refers to the index number displayed in the exam list
* `TASK_INDEX` refers to the index number displayed in the task list
* `EXAM_INDEX` and `TASK_INDEX` must be a positive integer 1, 2, 3, …​
* If `EXAM_INDEX` is a non-positive integer or is greater than the number of exams in the exam list,
an error message will be shown
* If `TASK_INDEX` is a non-positive integer or is greater than the number of tasks in the task list,
an error message will be shown

Examples:

`e link e/1 t/1` links the first task in the displayed task list to the first exam in the exam list

`e link e/2 t/3` links the third task in the displayed task list to the second exam in the exam list

### Unlinking an exam
Unlinks the exam from the specified task according to the index given.

Format: `e unlink INDEX`
* Unlinks the exam from the task at the specific `INDEX` from the task list.
* `INDEX` refers to the index number shown in the task list of the task to be unlinked.
* `INDEX` must be a positive integer 1, 2, 3, …​
* If `INDEX` is non-positive or more than the number of tasks in the task list, an error message will be displayed.

Example:

`e unlink 1` unlinks the first task in the task list from its current exam.

### Showing the tasks of an exam
Shows all tasks linked to the specified exam according to the index given.

Format: `e showt INDEX`
* Shows all tasks that are linked to the exam at the specified `INDEX` from the exam list.
* `INDEX` refers to the index number of the exam shown in the exam list.
* `INDEX` must be a positive integer 1, 2, 3, …​
* If `INDEX` is non-positive or more than the number of exams in the exam list, an error message will be displayed.

Example:

`e showt 1` shows a list of all tasks linked to the first exam in the exam list.

## Other Features

### Clearing all the lists
Clears the task, exam and module lists.

Format: `clearall`
* Clears all tasks, exams and modules currently in the respective lists.

Example:

`clearall` clears all tasks, exams and modules currently in the respective lists.

### Help
Views the help window which displays the list of commands

Format: `help`
* Opens up the help window which shows the list of commands

Example:

`help` opens up the help window to display the list of commands

### Exiting the program
Exits the program

Format: `exit`

--------------------------------------------------------------------------
## General

### Saving data to the data file
* All MODPRO data will be manually saved to the hard disk after the execution of each command.
There is no need to manually save the data.

### Editing the data file
* MODPRO data is saved as a JSON file at `[JAR FILE LOCATION]/data/modpro.json`. Advanced users are allowed
to modify the data at the JSON file

<div markdown="span" class="warning-for-modpro-data-change">:exclamation: **Warning:**
If changes made to the modpro.json makes the format invalid or invalid data is used, 
MODPRO will discard all data stored and start with an empty data file.
</div>

-----------------------------------------------------------------------------------------------------------------------------------------------------

## Summary of Commands

| Command       | Format and Examples                                                                                                               |
|---------------|-----------------------------------------------------------------------------------------------------------------------------------|
| **Module**    |
| **m add**     | **Format**: `m add c/MODULE_CODE m/MODULE_NAME mc/MODULAR_CREDIT`<br/> **Example**: `m add c/cs2103t m/software engineering mc/4` |
| **m del**     | **Format**: `m del INDEX`<br/> **Example**: `m del 1`                                                                             |
| **m edit**    | **Format**: `m edit INDEX [c/MODULE_CODE]* [m/MODULE_NAME]* [mc/MODULE_CREDIT]*`<br/> **Example**: `m edit 1 c/cs2040 mc/4`       |
| **m list**    | **Format**: `m list`<br/> **Example**: `m list`                                                                                   |
| **m find**    | **Format**: `m find KEYWORD`<br/> **Example**: `m find cs`                                                                        |
| **Task**      |                                                                                                                                   |
| **t add**     | **Format**: `t add m/MODULE d/DESCRIPTION`<br/> **Example**: `t add m/CS2105 d/Assignment 1`                                      |
| **t del**     | **Format**: `t del INDEX`<br/> **Example**: `t del 1`                                                                             |
| **t edit**    | **Format**: `t edit INDEX [m/MODULE]* [d/DESCRIPTION]*`<br/> **Example**: `t edit 1 d/Assignment 2`                               |
| **t mark**    | **Format**: `t mark INDEX`<br/> **Example**: `t mark 1`                                                                           |
| **t unmark**  | **Format**: `t unmark INDEX`<br/> **Example**: `t unmark 1`                                                                       |
| **t list**    | **Format**: `t list`<br/> **Example**: `t list`                                                                                   |
| **t sort**    | **Format**: `t sort c/CRITERIA`<br/> **Example**: `t sort c/priority`                                                             |
| **t filter**  | **Format**: `t filter [m/MODULE]* [c/COMPLETED]* [l/LINKED]*`<br/> **Example**: `t filter m/cs2030 c/y`                           |
| **t find**    | **Format**: `t find KEYWORD`<br/> **Example**: `t find watch lecture rec`                                                         |
| **t tagadd**  | **Format**: `t tagadd INDEX [p/PRIORITY_STATUS]* [dl/DEADLINE]*`<br/> **Example**: `t tagadd 1 p/high dl/29-12-2022`              |
| **t tagdel**  | **Format**: `t tagdel INDEX t/KEYWORD [SECOND_KEYWORD]`<br/> **Example**:`t tagdel 1 t/priority`                                  |
| **t tagedit** | **Format**: `t tagedit INDEX [p/PRIORITY_STATUS]* [dl/DEADLINE]*`<br/> **Example**: `t tagedit 1 p/medium`                        |
| **t clear**   | **Format**: `t clear`<br/> **Example**: `t clear`                                                                                 |
| **Exam**      |                                                                                                                                   |
| **e add**     | **Format**: `e add m/MODULE ex/EXAM_DESCRIPTION ed/EXAM_DATE`<br/> **Example**: `e add m/cs2013t ex/practical ed/29-10-2022`      |
| **e edit**    | **Format**: `e edit INDEX [m/MODULE]* [ex/EXAM_DESCRIPTION]* [ed/EXAM_DATE]*`<br/> **Example**: `e edit 1 m/cs2040`               |
| **e del**     | **Format**: `e del INDEX`<br/> **Example**: `e del 1`                                                                             |
| **e link**    | **Format**: `e link e/EXAM_INDEX t/TASK_INDEX`<br/> **Example**: `e link e/1 t/2`                                                 |
| **e unlink**  | **Format**: `e unlink INDEX`<br/> **Example**: `e unlink 1`                                                                       |
| **e showt**   | **Format**: `e showt INDEX`<br/> **Example**: `e showt 1`                                                                         |
| **Others**    |                                                                                                                                   |
| **clearall**  | **Format**: `clearall`<br/> **Example**: `clearall`                                                                               |
| **help**      | **Format**: `help`<br/> **Example**: `help`                                                                                       |
| **exit**      | **Format**: `exit`<br/> **Example**: `exit`                                                                                       |
