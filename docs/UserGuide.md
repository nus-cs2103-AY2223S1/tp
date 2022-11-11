---
layout: page
title: User Guide
---

## Introduction

Welcome to the User Guide of MODPRO!

MODPRO is a desktop application which helps NUS students track the progress of their modules.

MODPRO helps you…
* organise your tasks by modules and exams
* track your progress for each module and exam with a progress bar
* easily navigate through your tasks by tagging them, and filtering and sorting your task list

It is highly optimised for students who prefer Command Line Interface (CLI) by allowing those who type fast to key in commands to track their modules. If you struggle to type fast, we also provide a Graphical User interface (GUI) to assist you in using MODPRO.

------------------
<div style="page-break-after: always;"></div>


### The Graphical User Interface (GUI)

To give you a quick overview of MODPRO, the following image shows you the main components of the GUI.

| ![Ui](images/GUI.png) |
|:---------------------:|
|     GUI of MODPRO     |

The table below summarises the function of each component.

| **Component**             | **Function**                                                 |
|---------------------------|--------------------------------------------------------------|
| Menu Bar                  | To view the help window and to exit the application          |
| Command Input             | To key in commands which will be executed                    |
| Feedback Message Display  | To view the feedback given after the execution of a command  |
| Task List                 | To view your tasks                                           |
| Module List               | To view your modules                                         |
| Exam List                 | To view your exams                                           |


### Icons and formatting used in the guide


This guide uses icons and formatting to differentiate between the different types of information so that it is comprehensible. The following table summarises the icons and formatting used, along with their meaning.

| **Icon/Formatting**      | **Meaning**                                                  |
|--------------------------|--------------------------------------------------------------|
| :information_source:     | Extra information that is good to know                       |
| :exclamation:            | Warnings regarding the use of specific commands and features |
| :bulb:                   | Tips on using MODPRO                                         |
| `WORDS HIGHLIGHTED GREY` | Words that you can type into the Command Input               |


### Purpose of the guide
This document is to assist you in using MODPRO smoothly and effectively to track your tasks and progress.

* For first-time users, you can proceed to [Quick Start](#quick-start) for a guide on how to set up MODPRO and a short tutorial on the basic commands. Once you are familiar with the interface, you can start exploring our extensive list of features [here](#features)
* For the experienced users, you can learn more about these unique features that can help you manage your tasks better: [adding tags to your tasks](#adding-the-tags-to-a-task), [sorting your list](#sorting-the-task-list), [filtering your list](#filtering-the-task-list), [linking an exam to a task](#linking-an-exam)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>



## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Modules-Related Features](#modules-related-features)
    - [Adding a module](#adding-a-module)
    - [Listing the modules](#listing-the-modules)
    - [Finding a module](#finding-a-module)
    - [Deleting a module](#deleting-a-module)
    - [Editing a module](#editing-a-module)
  - [Tasks-Related Features](#tasks-related-features)
    - [Adding a task](#adding-a-task)
    - [Deleting a task](#deleting-a-task)
    - [Editing a task](#editing-a-task)
    - [Marking a task](#marking-a-task)
    - [Unmarking a task](#unmarking-a-task)
    - [Listing the tasks](#listing-the-tasks)
    - [Filtering the task list](#filtering-the-task-list)
    - [Finding a task](#finding-a-task)
    - [Sorting the task list](#sorting-the-task-list)
    - [Adding the tags to a task](#adding-the-tags-to-a-task)
    - [Editing the tags of a task](#editing-the-tags-of-a-task)
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
    - [Opening the help window](#opening-the-help-window)
    - [Exiting the program](#exiting-the-program)
- [Future Features](#1.1)
- [General](#general)
  - [Saving data to the data file](#saving-data-to-the-data-file)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Summary Of Commands](#summary-of-commands)


--------------------------------------------------------------------------------------------------------------------

## Quick start 

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `modpro.jar` from [here](https://github.com/AY2223S1-CS2103T-F11-2/tp/releases/download/v1.4/modpro.jar).

3. Copy the file to the folder you want to use as the _home folder_ for MODPRO.

4. Double-click the file to start the app. The GUI similar to the image below should appear in a few seconds. <br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. <br>
   e.g. typing help and pressing Enter will open the help window. <br>
   Here are some commands you can try:

   * `m add c/CS2100 m/Computer Organisation mc/4` : Adds the module called Computer Organisation with the module code CS2100 into the module list.
   * `m list` : Lists all modules stored in the module list.
   * `m delete 1` : Deletes the 1st module shown in the displayed module list.
   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>



## Features

<div markdown="block" class="alert alert-info">
**:information_source: How to read the command format:**<br>
  
* Command words are case-insensitive.<br>
  e.g. `t add` is the same as `T add` for command words.

* Prefixes are case-sensitive.<br>
  e.g. `t add m/CS2030 d/Programming` is not the same as `t add M/CS2030 D/Programming` and the latter command will throw an error message.

* Words in UPPER_CASE are the parameters to be supplied by the user.<br>
  e.g. in `t add m/MODULE`, `MODULE` is a parameter which can be used as `t add m/CS2030`.
  
* Items in square brackets are optional.<br>
  e.g `t/KEYWORD [SECOND_KEYWORD]` can be used as `t/priority deadline` or as `t/priority`.
  
* Items in square brackets with * are optional, but at least one of them is required.<br>
  e.g `[m/MODULE]* [d/DESCRIPTION]*` can be used as `m/cs2030` or `m/cs2030 d/assignment` but not ` ` (none provided).
  
* Parameters can be in any order.<br>
  e.g. if the command specifies `m/MODULE d/DESCRIPTION`, `d/DESCRIPTION m/MODULE` is also acceptable.
  
* If a parameter is expected only once in the command and it is specified multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `m/cs2030 m/cs2040`, only `m/cs2040` will be taken.
  
* Extraneous parameters for commands that do not take in parameters (such as help, exit and clearall) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
</div>


--------------------------
<div style="page-break-after: always;"></div>


## Modules-related Features

### Adding a module
Adds a module into the stored module list.

Format: `m add c/MODULE_CODE m/MODULE_NAME mc/MODULE_CREDIT`

Parameters:
* `MODULE_CODE` refers to the module code of the module being added.
* `MODULE_NAME` refers to the name of the module.
* `MODULE_CREDIT` refers to the number of module credits that the module has.

Restrictions:
* `MODULE_CODE` 
  * `MODULE_CODE` should be at least 6 characters long.
  * The first two characters of `MODULE_CODE` should be alphabetical and the remaining characters should be alphanumeric.
* `MODULE_NAME` should not be empty.
* `MODULE_CREDIT` should be an integer between 0 and 45 inclusive.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `MODULE_CODE` is case-insensitive.
</div>

Examples:

`m add c/cs2100 m/computer organisation mc/4` adds a module with the module code 'cs2100', the module name 'computer organisation' and the module credit '4'.

`m add c/cs2105 m/networking mc/3` adds a module with the module code 'cs2105', the module name 'networking' and the module credit '3'.

|         ![AddModuleCommand](images/AddModuleCommandDemo.png)            |
|:-----------------------------------------------------------------------:|
| Demonstration of Command: `m add c/cs2100 m/computer organisation mc/4` |


### Listing the modules
Lists all modules in the stored module list.

Format: `m list`

### Finding a module
Finds modules in the stored module list whose module code matches the `KEYWORD` partially or fully.

Format: `m find KEYWORD`

Parameter:
* `KEYWORD` refers to the keyword inputted by the user.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `KEYWORD` is case-insensitive.
</div>

------------------
<div style="page-break-after: always;"></div>

Examples:

`m find CS` finds modules whose module code contains the `KEYWORD` 'CS' such as 'CS2030S', 'CS2040S'.

`m find 30` finds modules whose module code contains the `KEYWORD` '30' such as 'CS2030S'.

### Deleting a module
Deletes the specified module from the stored module list.

Format: `m del INDEX`

Parameter:
* `INDEX` refers to the index number (shown in the displayed module list) of the module to be deleted.

Restrictions:
* `INDEX`
  * `INDEX` should be an integer greater than 0 and less than 2147483648.
  * `INDEX` should not be greater than the number of modules in the displayed module list.

Examples:

`m del 1` deletes the first module in the displayed module list.

`m del 3` deletes the third module in the displayed module list.

<div markdown="span" class="alert alert-warning">
:exclamation: **Warning:** All tasks and exams related to the module will be deleted after the specified module is deleted.
</div>

------------------
<div style="page-break-after: always;"></div>


### Editing a module
Edits the specified module by updating the existing values to the input values.

Format: `m edit INDEX [c/MODULE_CODE]* [m/MODULE_NAME]* [mc/MODULE_CREDIT]*`

Parameters:
* `INDEX` refers to the index number (shown in the displayed module list) of the module to be edited.
* `MODULE_CODE` refers to the module code that will replace the existing module code of the module specified.
* `MODULE_NAME` refers to the module name that will replace the existing module name of the module specified.
* `MODULE_CREDIT` refers to the module credit that will replace the existing module credit of the module specified.

<div markdown="span" class="alert alert-info">
:information_source: **Note:** `MODULE_CODE` is case-insensitive.
</div>  

Restrictions:
* `INDEX`
  * `INDEX` should be an integer greater than 0 and less than 2147483648.
  * `INDEX` should not be greater than the number of modules in the displayed module list.
* `MODULE_CODE`
  * `MODULE_CODE` should be at least 6 characters long.
  * The first two characters of `MODULE_CODE` should be alphabetical and the remaining characters should be alphanumeric.
  * `MODULE_CODE` should not be the module code of an existing module in the stored module list.
* `MODULE_NAME` should not be empty.
* `MODULE_CREDIT`
  * `MODULE_CREDIT` should not be empty.
  * `MODULE_CREDIT` should be an integer between 0 and 45 inclusive.
* The input values should not be the same as existing values.
* The edited module should not be the same as any existing module in the stored module list.

<div markdown="span" class="alert alert-warning">
:exclamation: **Warning:** If the module code of the module is edited, and the module is related to some tasks or exams, the module of these tasks and exams will be changed to this edited module.
</div>

Examples:

`m edit 1 m/Programming Methodology I mc/4` changes the module name of the first module in the displayed module list to 'Programming Methodology I' and the module credit to '4'.

`m edit 2 c/cs2040 m/Data Structures and Algorithms mc/4` changes the module name of the second module in the displayed module list to ‘Data Structures and Algorithms’, the module code to ‘cs2040’ and the module credit to ‘4’.

## Tasks-related Features

### Adding a task
Adds a task into the stored task list.

Format: `t add m/MODULE d/DESCRIPTION`

Parameters:
* `MODULE` refers to the module code of the module which the task belongs to.
* `DESCRIPTION` refers to the task description to be shown.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `MODULE` is case-insensitive.
</div>

Restrictions:
* `MODULE`
  * `MODULE` should be at least 6 characters long.
  * The first two characters of `MODULE` should be alphabetical and the remaining characters should be alphanumeric.
  * `MODULE` should be the module code of an existing module in the stored module list.
* `DESCRIPTION` should not be empty.

Example:
`t add m/CS2105 d/Assignment 1` adds a task with the module as 'CS2105' and description as 'Assignment 1' into the stored task list.

|     ![AddTaskCommand](images/AddTaskCommandDemo.png)      |
|:---------------------------------------------------------:|
| Demonstration of Command: `t add m/CS2105 d/Assignment 1` |

### Deleting a task 
Deletes the specified task from the stored task list.

Format: `t del INDEX`

Parameter:
* `INDEX` refers to the index number (shown in the displayed task list) of the task to be deleted.

Restrictions:
* `INDEX`
  * `INDEX` should be an integer greater than 0 and less than 2147483648.
  * `INDEX` should not be greater than the number of tasks in the displayed task list.

Examples:

`t del 1` deletes the first task in the displayed task list. 

`t del 3` deletes the third task in the displayed task list.

<div markdown="span" class="alert alert-warning">
:exclamation: **Warning:** When the task is deleted, if it has a link to an exam, the task will be unlinked from the exam.
</div>

### Editing a task
Edits the specified task by updating the existing values to the input values.

Format: `t edit INDEX [m/MODULE]* [d/DESCRIPTION]*`

Parameters:
* `INDEX` refers to the index number (shown in the displayed task list) of the task to be edited.
* `MODULE` refers to the module code of the module that will replace the existing module of the task specified.
* `DESCRIPTION` refers to the description that will replace the existing description of the task specified.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `MODULE` is case-insensitive.
</div>  

Restrictions:
* `INDEX`
  * `INDEX` should be an integer greater than 0 and less than 2147483648.
  * `INDEX` should not be greater than the number of tasks in the displayed task list.
* `MODULE`
  * `MODULE` should be at least 6 characters long. 
  * The first two characters of `MODULE` should be alphabetical and the remaining characters should be alphanumeric.
  * `MODULE` should be the module code of an existing module in the stored module list. 
* `DESCRIPTION` should not be empty.
* The input values should not be the same as existing values. 
* The edited task should not be the same as any existing task in the stored task list.

<div markdown="span" class="alert alert-warning">

:exclamation: **Warning:** If a task is linked to an exam, and its module is changed, the task will be unlinked from the exam.
</div>

Examples:

`t edit 1 d/Assignment 2` changes the description of the first task in the displayed task list to 'Assignment 2'.

`t edit 2 m/CS2040 d/tutorial 2` changes the module and description of the second task in the displayed task list to 'CS2040' and 'tutorial 2' respectively.

### Marking a task
Indicates the specified task is completed.

Format: `t mark INDEX`

Parameter:
* `INDEX` refers to the index number (shown in the displayed task list) of the task to be marked.

Restrictions:
* `INDEX`
  * `INDEX` should be an integer greater than 0 and less than 2147483648.
  * `INDEX` should not be greater than the number of tasks in the displayed task list.
* The task specified should not be already marked.
  
Examples:

`t mark 1` indicates the first task in the displayed task list is completed.

`t mark 3` indicates the third task in the displayed task list is completed.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** You can sort and filter tasks based on their completion status. The percentage of completed tasks are also shown for each exam and module.
</div>

------------------
<div style="page-break-after: always;"></div>

| ![MarkTaskCommand](images/MarkTask.png)   |
|:-----------------------------------------:|
|   Demonstration of Command: `t mark 1`    |

### Unmarking a task
Indicates the specified task is not completed.
  
Format: `t unmark INDEX`
  
Parameter:
* `INDEX` refers to the index number (shown in the displayed task list) of the task to be unmarked.

Restrictions:
* `INDEX` 
  * `INDEX` should be an integer greater than 0 and less than 2147483648.
  * `INDEX` should not be greater than the number of tasks in the displayed task list.
* The task specified should not be already unmarked.
    
Examples:

`t unmark 1` indicates the first task in the displayed task list is not completed.

`t unmark 3` indicates the third task in the displayed task list is not completed.


### Listing the tasks
Lists all tasks in the stored task list.

Format: `t list`


### Filtering the task list
Filters the displayed task list to show only tasks that fulfil the module code, completion status, and/or link status conditions.

Format: `t filter [m/MODULE/]* [c/COMPLETED]* [l/LINKED]*`

Parameters:
* `MODULE` refers to the module code of the module to be filtered out.
* `COMPLETED` should be `y` to filter tasks that are complete or `n` to filter tasks that are incomplete.
* `LINKED` should be `y` to filter tasks that are linked to an exam or `n` to filter tasks that are not linked to any exam.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `MODULE` is case-insensitive.
</div> 

Restrictions:
* `MODULE`
  * `MODULE` should be at least 6 characters long.
  * The first two characters of `MODULE` should be alphabetical and the remaining characters should be alphanumeric.
  * `MODULE` should be the module code of an existing module in the stored module list.
* `COMPLETED` should be `y` or `n`.
* `LINKED` should be `y` or `n`.

Examples:

`t filter l/n` filters out all tasks that are currently not linked to any exam.

`t filter m/cs2030 c/y` filters out all completed tasks that are under the module 'cs2030'.

| ![FilterTasksCommand](images/FilterTasksCommandDemo.png)   |
|:----------------------------------------------------------:|
|          Demonstration of Command: `t filter l/n`          |

### Finding a task
Finds tasks in the stored task list whose task description matches the `KEYWORD` partially or fully.

Format: `t find KEYWORD`

Parameter:
* `KEYWORD` refers to the keyword inputted by the user.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `KEYWORD` is case-insensitive.
</div>

Examples:

`t find work` finds tasks that contain the `KEYWORD` 'work' such as 'homework', 'work to do'.

`t find do paper` finds tasks that contain the `KEYWORD` 'do paper', such as 'do paper one', 'do paper two'.

| ![FindCommand](images/FindCommandImage.png)   |
|:---------------------------------------------:|
|    Demonstration of Command: `t find work`    |

### Sorting the task list
Sorts tasks in the task list based on the criteria specified.

Format: `t sort c/CRITERIA`

Parameter:
* `CRITERIA` refers to the criteria that is used for sorting the task list.

Restrictions:
* `CRITERIA` can be either `priority`, `deadline`, `module` or `description`.
* When sorting by priority, tasks marked with priority status `HIGH` will appear at the top of the displayed task list, followed by tasks with priority status`MEDIUM`, and lastly tasks with priority status `LOW`.
* When sorting by deadline, tasks with the earlier deadlines will appear at the top while tasks with no deadlines will appear at the bottom of the displayed task list.
* When sorting by module, all tasks will be sorted by the module code in alphanumeric order.
* When sorting by description, all tasks will be sorted by the task description in alphanumeric order.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `CRITERIA` is case-insensitive.
</div>

Examples:

`t sort c/description` sorts all the tasks in the task list by task description.

`t sort c/priority` sorts all the tasks in the task list by priority status.

`t sort c/deadline` sorts all the tasks in the task list by deadline.

`t sort c/module` sorts all the tasks in the task list by module code.

| ![SortTaskCommand](images/SortTaskCommandDemo.png)  |
|:---------------------------------------------------:|
|  Demonstration of Command: `t sort c/description`   |

### Adding the tags to a task
Tags the specified task with a priority status and/or the deadline to complete the task. 

Format: `t tagadd INDEX [p/PRIORITY_STATUS]* [dl/DEADLINE]*`

Parameters:
* `INDEX` refers to the index number (shown in the displayed task list) of the task to be tagged.
* `PRIORITY_STATUS` refers to the priority status which is tagged to the task in the displayed task list.
* `DEADLINE` refers to the deadline which is tagged to the task in the displayed task list.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `PRIORITY_STATUS` is case-insensitive.
</div>

Restrictions:
* `INDEX`
  * `INDEX` should be an integer greater than 0 and less than 2147483648.
  * `INDEX` should not be greater than the number of tasks in the displayed task list.
* `PRIORITY STATUS` should be one of these three priorities: `HIGH`, `MEDIUM`, `LOW`.
* `DEADLINE` should be in the format DD-MM-YYYY and not earlier than the current date.

Examples:

`t tagadd 1 p/HIGH` tags the first task in the displayed task list with the priority status of 'high'.

`t tagadd 1 dl/31-12-2022` tags the first task in the displayed task list with the deadline of '31-12-2022'.

`t tagadd 2 p/low dl/31-12-2022` tags the second task in the displayed task list with a priority status of
'low' and a deadline of '31-12-2022'.

| ![AddTagCommand](images/AddTagCommandDemo.png)  |
|:-----------------------------------------------:|
|  Demonstration of Command: `t tagadd 1 p/HIGH`  |


### Editing the tags of a task
Edits the tags associated with the specified task.

Format: `t tagedit INDEX [p/PRIORITY_STATUS]* [dl/DEADLINE]*`

Parameters:
* `INDEX` refers to the index number (shown in the displayed task list) of the task to edit the tags.
* `PRIORITY_STATUS` refers to the priority status which is tagged to the task in the displayed task list.
* `DEADLINE` refers to the deadline which is tagged to the task in the displayed task list.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `PRIORITY_STATUS` is case-insensitive.
</div>

Restrictions:
* `INDEX`
  * `INDEX` should be an integer greater than 0 and less than 2147483648.
  * `INDEX` should not be greater than the number of tasks in the displayed task list.
* `PRIORITY STATUS`
  * `PRIORITY STATUS` should be one of these three priorities: `HIGH`, `MEDIUM`, `LOW`.
  * `PRIORITY_STATUS` of the task should not be the same as the current priority status of the task.
* `DEADLINE`
  * `DEADLINE` should be in the format DD-MM-YYYY and not earlier than the current date.
  * `DEADLINE` should not be the same as the current deadline of the task.
* To edit the `PRIORITY_STATUS` of the task, the task should already have a priority status tagged to it.
* To edit the `DEADLINE` of the task, the task should already have a deadline tagged to it.

Examples:

`t tagadd 1 p/HIGH` followed by `t tagedit 1 p/LOW` updates priority status of the task
from 'high' to 'low'.

`t tagadd 1 dl/31-12-2022` followed by `t tagedit 1 dl/31-11-2022` updates the deadline of the task
from '31-12-2022' to '31-11-2022'.

### Deleting the tags of a task
Deletes the tags associated with the specified task.

Format: `t tagdel INDEX t/KEYWORD [SECOND_KEYWORD]`

Parameters:
* `INDEX` refers to the index number (shown in the displayed task list) of the task to remove the tags.
* `KEYWORD` refers to the first keyword which indicates the type of tag that can be removed.
* `SECOND_KEYWORD` refers to second keyword which indicates the type of tag that can be removed.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `KEYWORD` and `SECOND_KEYWORD` are case-insensitive.
</div>

Restrictions:
* `INDEX` should be an integer greater than 0 and less than 2147483648.
* `INDEX` should not be greater than the number of tasks in the displayed task list.
* The list of keywords which can be used for `KEYWORD` and `SECOND_KEYWORD` are `priority` and `deadline`.
* If duplicate keywords are used, the duplicate keyword will be ignored.

Examples:

`t tagadd 1 p/HIGH` followed by `t tagdel 1 t/priority` deletes the priority status
of the first task in the displayed task list.

`t tagadd 1 dl/24-11-2022` followed by `t tagdel 1 t/deadline` deletes the deadline
of the first task in the displayed task list.

`t tagadd 2 p/LOW dl/31-12-2022` followed by `t tagdel 2 t/priority deadline` deletes 
the priority status and deadline of the second task in the displayed task list.

### Clearing the task list
Clears all tasks currently in the stored task list.

Format: `t clear`

--------------------------------------------------------------------------------------------------------------------

## Exams-related Features

### Adding an exam
Adds an exam into the stored exam list.

Format: `e add m/MODULE ex/EXAM_DESCRIPTION ed/EXAM_DATE`

Parameters:
* `MODULE` refers to the module code of the module of the exam to be added.
* `EXAM_DESCRIPTION` refers to the description of the exam to be added.
* `EXAM_DATE` refers to the date of the exam to be added.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `MODULE` is case-insensitive.
</div>

Restrictions:
* `MODULE`
  * `MODULE` should be at least 6 characters long.  
  * The first two characters of `MODULE` should be alphabetical and the remaining characters should be alphanumeric. 
  * `MODULE` should be the module code of an existing module in the stored module list.
* `EXAM_DESCRIPTION` should not be empty.
* `EXAM_DATE` should be in the format DD-MM-YYYY and not earlier than the current date.
* The exam to be added should not be the same as any existing exam in the stored exam list.

Example:

`e add m/CS2100 ex/midterms ed/20-08-2023` adds the exam with the exam module as 'CS2100',
exam description as 'midterms', exam date as '20-08-2023' into the stored exam list. 

|              ![AddExamCommand](images/AddExamImage.png)              |
|:--------------------------------------------------------------------:|
| Demonstration of Command: `e add m/CS2100 ex/midterms ed/20-08-2023` |

### Editing an exam
Edits the specified exam by updating the existing values to the input values.
   
Format: `e edit INDEX [m/MODULE]* [ex/EXAM_DESCRIPTION]* [ed/EXAM_DATE]*`

Parameters:
* `INDEX` refers to the index number (shown in the displayed exam list) of the exam to be edited.
* `MODULE` refers to the module code of the module that will replace the existing module of the exam specified.
* `EXAM_DESCRIPTION` refers to the exam description that will replace the existing exam description of the exam specified.
* `EXAM_DATE` refers to the exam date that will replace the existing exam date of the exam specified.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** `MODULE` is case-insensitive.
</div>

Restrictions:
* `INDEX`
   * `INDEX` should be an integer greater than 0 and less than 2147483648. 
   * `INDEX` should not be greater than the number of exams in the displayed exam list.
* `MODULE`
   * `MODULE` should be at least 6 characters long. 
   * The first two characters of `MODULE` should be alphabetical and the remaining characters should be alphanumeric.
   * `MODULE` should be the module code of an existing module in the stored module list.
* `EXAM_DESCRIPTION` should not be empty.
* `EXAM_DATE` should be in the format DD-MM-YYYY and not earlier than the current date. 
* The input values should not be the same as existing values. 
* The edited exam should not be the same as any existing exam in the stored exam list.

<div markdown="span" class="alert alert-warning">

:exclamation: **Warning:** If the exam is linked to some tasks, and the module of the exam is changed, the tasks will be unlinked from the exam.
</div>

Examples:

`e edit 1 ex/finals ed/20-12-2022` changes the exam description of the first exam in the displayed exam list to ‘finals’ and the exam date to ‘20-12-2022’.

`e edit 2 m/CS2030S ex/midterms ed/22-12-2022` changes the exam description of the second exam in the displayed exam list to ‘midterms’, the exam module to ‘CS2030S’ and the exam date to ‘22-12-2022’.


### Deleting an exam
Deletes the specified exam from the stored exam list.

Format: `e del INDEX`

Parameter:
* `INDEX` refers to the index number (shown in the displayed exam list) of the exam to be deleted.

Restrictions:
* `INDEX` should be an integer greater than 0 and less than 2147483648.
* `INDEX` should not be greater than the number of exams in the displayed exam list.

Example:

`e del 1` deletes the first exam in the displayed exam list.
<div markdown="span" class="alert alert-warning">

:exclamation: **Warning:** All tasks currently linked to the exam will be unlinked after the exam is deleted.
</div>

### Linking an exam
Links the specified task to the specified exam.

Format `e link e/EXAM_INDEX t/TASK_INDEX`

Parameters:
* `EXAM_INDEX` refers to the index number (shown in the displayed exam list) of the exam to be linked.
* `TASK_INDEX` refers to the index number (shown in the displayed task list) of the task to be linked.

Restrictions:
* `EXAM_INDEX` and `TASK_INDEX` should be an integer greater than 0 and less than 2147483648.
* `EXAM_INDEX` should not be greater than the number of exams in the displayed exam list.
* `TASK_INDEX` should not be greater than the number of tasks in the displayed task list.

Examples:

`e link e/1 t/2` links the second task in the displayed task list to the first exam in the displayed exam list.

`e link e/2 t/3` links the third task in the displayed task list to the second exam in the displayed exam list.

| ![LinkExamCommand](images/LinkExamCommandDemo.png) |
|:--------------------------------------------------:|
|     Demonstration of Command: `e link e/1 t/2`     |


### Unlinking an exam
Unlinks the exam from the specified task.

Format: `e unlink INDEX`

Parameter:
* `INDEX` refers to the index number (shown in the displayed task list) of the task to be unlinked.

Restrictions:
* `INDEX` should be an integer greater than 0 and less than 2147483648.
* `INDEX` should not be greater than the number of tasks in the displayed task list.

Example:

`e unlink 1` unlinks the first task in the displayed task list from its current exam.

### Showing the tasks of an exam
Shows all tasks linked to the specified exam.

Format: `e showt INDEX`

Parameter:
* `INDEX` refers to the index number (shown in the displayed exam list) of the exam.

Restrictions:
* `INDEX` should be an integer greater than 0 and less than 2147483648.
* `INDEX` should not be greater than the number of exams in the displayed exam list.

Example:

`e showt 1` shows a list of all tasks linked to the first exam in the displayed exam list.

| ![ListExamTasksCommand](images/ListExamTasksCommandDemo.png) |
|:------------------------------------------------------------:|
|            Demonstration of Command: `e showt 1`             |

## Other Features

### Clearing all the lists
Clears all tasks, exams and modules currently in the respective stored lists.

Format: `clearall`

### Opening the help window
Opens the help window which displays the list of commands.

Format: `help`

### Exiting the program
Exits the program.

Format: `exit`

--------------------------------------------------------------
## Future Features to be added (Coming Soon!!)<a id='1.1'></a>

To optimize your tracking process, MODPRO will let you:
* Edit the case sensitivity of module code in tasks, modules and exams.
* Tick checkboxes of tasks using the mouse.
* Add time and venue for exams. 
* Undo commands.

To provide you with a better user experience, MODPRO will:
* Improve the handling of duplicate prefixes in commands, by recognizing and rejecting them.

--------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## General 

### Saving data to the data file
* All MODPRO data will be manually saved to the hard disk after the execution of each command.
There is no need to manually save the data.

### Editing the data file
* MODPRO data is saved as a JSON file at `[JAR FILE LOCATION]/data/modpro.json`. Advanced users are allowed
to modify the data at the JSON file.

<div markdown="span" class="alert alert-warning">
:exclamation: **Warning:**
If changes made to the modpro.json makes the format invalid or invalid data is used, 
MODPRO will discard all data stored and start with an empty data file.
</div>

--------------------------------------------------------------------------
## FAQ

1. What is the difference between the stored list and a displayed list? (e.g. stored task list vs displayed task list)
   * The stored task list is the list containing all the tasks you created, whereas the displayed task list is the one shown on your screen. 
   * The stored task list and displayed task list could be different after the filter or find command.

-----------------------------------------------------------------------------------------------------------------------------------------------------

## Summary of Commands

| Command       | Format and Examples                                                                                                              |
|---------------|----------------------------------------------------------------------------------------------------------------------------------|
| **Module**    |                                                                                                                                  |
| **m add**     | **Format**: `m add c/MODULE_CODE m/MODULE_NAME mc/MODULE_CREDIT`<br/> **Example**: `m add c/cs2103t m/software engineering mc/4` |
| **m del**     | **Format**: `m del INDEX`<br/> **Example**: `m del 1`                                                                            |
| **m edit**    | **Format**: `m edit INDEX [c/MODULE_CODE]* [m/MODULE_NAME]* [mc/MODULE_CREDIT]*`<br/> **Example**: `m edit 1 c/cs2040 mc/4`      |
| **m list**    | **Format**: `m list`<br/> **Example**: `m list`                                                                                  |
| **m find**    | **Format**: `m find KEYWORD`<br/> **Example**: `m find cs`                                                                       |
| **Task**      |                                                                                                                                  |
| **t add**     | **Format**: `t add m/MODULE d/DESCRIPTION`<br/> **Example**: `t add m/CS2105 d/Assignment 1`                                     |
| **t del**     | **Format**: `t del INDEX`<br/> **Example**: `t del 1`                                                                            |
| **t edit**    | **Format**: `t edit INDEX [m/MODULE]* [d/DESCRIPTION]*`<br/> **Example**: `t edit 1 d/Assignment 2`                              |
| **t mark**    | **Format**: `t mark INDEX`<br/> **Example**: `t mark 1`                                                                          |
| **t unmark**  | **Format**: `t unmark INDEX`<br/> **Example**: `t unmark 1`                                                                      |
| **t list**    | **Format**: `t list`<br/> **Example**: `t list`                                                                                  |
| **t sort**    | **Format**: `t sort c/CRITERIA`<br/> **Example**: `t sort c/priority`                                                            |
| **t filter**  | **Format**: `t filter [m/MODULE]* [c/COMPLETED]* [l/LINKED]*`<br/> **Example**: `t filter m/cs2030 c/y`                          |
| **t find**    | **Format**: `t find KEYWORD`<br/> **Example**: `t find watch lecture rec`                                                        |
| **t tagadd**  | **Format**: `t tagadd INDEX [p/PRIORITY_STATUS]* [dl/DEADLINE]*`<br/> **Example**: `t tagadd 1 p/high dl/29-12-2022`             |
| **t tagdel**  | **Format**: `t tagdel INDEX t/KEYWORD [SECOND_KEYWORD]`<br/> **Example**:`t tagdel 1 t/priority`                                 |
| **t tagedit** | **Format**: `t tagedit INDEX [p/PRIORITY_STATUS]* [dl/DEADLINE]*`<br/> **Example**: `t tagedit 1 p/medium`                       |
| **t clear**   | **Format**: `t clear`<br/> **Example**: `t clear`                                                                                |
| **Exam**      |                                                                                                                                  |
| **e add**     | **Format**: `e add m/MODULE ex/EXAM_DESCRIPTION ed/EXAM_DATE`<br/> **Example**: `e add m/cs2013t ex/practical ed/29-10-2022`     |
| **e edit**    | **Format**: `e edit INDEX [m/MODULE]* [ex/EXAM_DESCRIPTION]* [ed/EXAM_DATE]*`<br/> **Example**: `e edit 1 m/cs2040`              |
| **e del**     | **Format**: `e del INDEX`<br/> **Example**: `e del 1`                                                                            |
| **e link**    | **Format**: `e link e/EXAM_INDEX t/TASK_INDEX`<br/> **Example**: `e link e/1 t/2`                                                |
| **e unlink**  | **Format**: `e unlink INDEX`<br/> **Example**: `e unlink 1`                                                                      |
| **e showt**   | **Format**: `e showt INDEX`<br/> **Example**: `e showt 1`                                                                        |
| **Others**    |                                                                                                                                  |
| **clearall**  | **Format**: `clearall`<br/> **Example**: `clearall`                                                                              |
| **help**      | **Format**: `help`<br/> **Example**: `help`                                                                                      |
| **exit**      | **Format**: `exit`<br/> **Example**: `exit`                                                                                      |
