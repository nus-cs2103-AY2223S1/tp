---
layout: page
title: User Guide
---

MODPRO is a desktop application which helps NUS students in tracking the progress of their modules. It is highly optimised for students who prefer Command Line Interface (CLI) by allowing those who type fast to key in commands to track their modules. It also provides a Graphic User interface(GUI) for those who prefer it.

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding task functions](#adding-task-functions-coming-soon-in-v12)
  - [Tagging priority of task](#tagging-priority-of-task-coming-soon-in-v12)
  - [List](#list-coming-soon-in-v12)
  - [Marking a task as complete](#marking-a-task-as-complete-coming-soon-in-v12)
  - [Deleting task function](#deleting-task-function-coming-soon-in-v12)

--------------------------------------------------------------------------------------------------------------------

## Quick start 

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `modpro.jar` from [here](https://github.com/AY2223S1-CS2103T-F11-2/tp).

3. Copy the file to the folder you want to use as the _home folder_ for MODPRO.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. <br>
   ![Ui](images/Ui.png)

5. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

## Modules-related Features

## Tasks-related Features

### Adding task functions [Coming Soon in v1.2]
Adds task into task list

Format: `add TASK`
* Adds a task to the end of the task list
* Task refers to the task description to be shown
* If the task description is empty, an error message will be displayed to inform the user to enter a description

Examples:

`add CS2105 Assignment 1` adds CS2105 Assignment 1 task into task list

`add CS2103T Quiz` adds CS2103T Quiz task into task list

### Tagging priority of task [Coming Soon in v1.2]
Tags the priority to complete task 

Format: `tag TASK /p PRIORITY_STATUS`
* Tags one of three priorities("high", "medium", "low") to the task
* If the priority status is not "high", "medium" or "low", an error message will appear to inform the user to key in a valid priority status

Examples:

`tag CS2105 Quiz /p high` tags CS2105 Quiz as high priority task to complete

`tag CS2103T Quiz /p low` tags CS2103T Quiz as low priority task to complete


### List [Coming Soon in v1.2]
List tasks stored in task list

Format: `list`
* Display tasks that are stored in the task list

Examples:

`list` displays tasks that are stored in the task list

### Editing a task
Edits the specified task with the given fields

Format: `edit INDEX [m/MODULE] [d/DESCRIPTION]`
* Edits the task at the specified INDEX in the task list. 
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* If a task is linked to an exam, and its module is changed, the task will be unlinked from the exam.
* If the index is non-positive or more than the number of tasks in the list, an error message will be displayed.

Examples:

`t edit 1 d/Assignment 2` changes the description of the first task in the task list to 'Assignment 2'.

`t edit 2 m/cs2040 d/tutorial 2` changes the module and description of the second task in the task list to 'CS2040' and 'tutorial 2' respectively.

### Marking a task
Indicates the specified task is completed

Format: `mark INDEX`
* Indicates the task at the specified INDEX in the task list is completed. 
* The index refers to the index number shown in the task list. 
* The index must be a positive integer 1, 2, 3, …​
* If the index is non-positive or more than the number of tasks in the list, an error message will be displayed.

Examples:

`mark 1` indicates the first task in the task list is completed.

`mark 3` indicates the third task in the task list is completed.

### Unmarking a task
Indicates the specified task is not completed

Format: `unmark INDEX`
* Indicates the task at the specified INDEX in the task list is not completed. 
* The index refers to the index number shown in the task list. 
* The index must be a positive integer 1, 2, 3, …​
* If the index is non-positive or more than the number of tasks in the list, an error message will be displayed.

Examples:

`unmark 1` indicates the first task in the task list is not completed.

`unmark 3` indicates the third task in the task list is not completed.

### Deleting task function [Coming Soon in v1.2]
Deletes the specified task according to the index given

Format: `delete INDEX`
* Deletes the task at the specified index from the storage
* The index refers to the index number shown in the task list.
* The index must be a positive integer 1, 2, 3, …​
* If the task list is empty and the user inputs any number, an error message will be displayed.
* If the index is non-positive or more than the number of tasks in the list, an error message will be displayed.

Examples:

`delete 1` deletes the first task in the task list

`delete 3` deletes the first task in the task list

## Exams-related Features

## Other Features

### Exiting the program
Exits the program

Format: `exit`


--------------------------------------------------------------------------------------------------------------------

## Summary of Commands

| Command       | Format and Examples                                                                                                               |
|---------------|-----------------------------------------------------------------------------------------------------------------------------------|
| **Module**                                                                                                                                        |
| **M add**     | **Format**: `m add c/MODULE_CODE m/MODULE_NAME mc/MODULAR_CREDIT`<br/> **Example**:`m add c/cs2103t m/software engineering mc/4`  |
| **M del**     | **Format**: `m del INDEX`<br/> **Example**:`m del 1`                                                                              |
| **M edit**    | **Format**: `m edit INDEX [c/MODULE CODE] [m/MODULE NAME] [mc/MODULE CREDIT]`<br/> **Example**:`edit 1 c/cs2040 mc/4`             |
| **M list**    | **Format**: `m list`<br/> **Example**:`m list`                                                                                    |
| **M find**    | **Format**: `m find KEYWORD`                                                                                                      |
| **Task**      |                                                                                                                                   |
| **T add**     | **Format**: `t add m/MODULE d/DESCRIPTION`<br/> **Example**: `t add m/CS2105 d/Assignment 1`                                      |
| **T del**     | **Format**: `t del INDEX`<br/> **Example**: `t del 1`                                                                             |
| **T edit**    | **Format**: `t edit INDEX [m/MODULE] [d/DESCRIPTION]`<br/> **Example**: `t edit 1 d/Assignment 2`                                 |
| **T mark**    | **Format**: `t mark INDEX`<br/> **Example**: `t mark 1`                                                                           |
| **T unmark**  | **Format**: `t unmark INDEX`<br/> **Example**: `t unmark 1`                                                                       |
| **T list**    | **Format**: `t list`<br/> **Example**:`t list`                                                                                    |
| **T sort**    | **Format**: `t sort c/CRITERIA`<br/> **Example**:`sort c/priority`                                                                |
| **T filter**  | **Format**: `t filter m/MODULE c/COMPLETE l/LINKED`<br/> **Example**:`filter m/cs2030 c/y`                                        |
| **T find**    | **Format**: `t find KEYWORD`<br/> **Example**:`t find watch lecture rec`                                                          |
| **T tagadd**  | **Format**: `t tagadd INDEX p/PRIORITY_STATUS dl/DEADLINE`<br/> **Example**:`t tagadd 1 p/high dl/29-12-2022`                     |
| **T tagdel**  | **Format**: `t tagdel INDEX t/KEYWORD [SECOND_KEYWORD]`<br/> **Example**:`t tagdel 1 t/priority`                                  |
| **T tagedit** | **Format**: `t tagedit INDEX p/PRIORITY_STATUS dl/DEADLINE`<br/> **Example**:`t tagedit 1 p/medium`                               |
| **T clear**   | **Format**: `t clear`<br/> **Example**:`t clear`                                                                                  |
| **Exam**      |                                                                                                                                   |
| **E add**     | **Format**: `e add m/MODULE ex/EXAM_DESCRIPTION ed/EXAM_DATE`<br/> **Example**:`e add m/cs2013t ex/practical ed/29-10-2022`       |
| **E edit**    | **Format**: `e edit INDEX [c/MODULE CODE] [ex/EXAM DESCRIPTION] [ed/EXAM DATE]`<br/> **Example**:`e edit 1 c/cs2040`              |
| **E del**     | **Format**: `e del INDEX`<br/> **Example**:`e del 1`                                                                              |
| **E link**    | **Format**: `e link e/EXAM_INDEX t/TASK_INDEX`<br/> **Example**:`e link e/1 t/2`                                                  |
| **E unlink**  | **Format**: `e unlink INDEX`<br/> **Example**:`e unlink 1`                                                                        |
| **E showT**   | **Format**: `e showT INDEX`<br/> **Example**:`e showT 1`                                                                          |
| **Others**    |                                                                                                                                   |
| **clearAll**  |                                                                                                                                   |
| **Help**      |                                                                                                                                   |
| **Exit**      |                                                                                                                                   |
