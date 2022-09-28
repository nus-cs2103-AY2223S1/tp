---
layout: page
title: User Guide
---

MODPRO is a desktop application which helps NUS students in tracking the progress of their modules. It is highly optimised for students who prefer Command Line Interface (CLI) by allowing those who type fast to key in commands to track their modules. It also provides a Graphic User interface(GUI) for those who prefer it.

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding task functions](#tagging-task-order-coming-soon-in-v12)
  - [Tagging task order](#tagging-task-order-coming-soon-in-v12)
  - [List](#list-coming-soon-in-v2)

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

### Adding task functions [Coming Soon in v1.2]
Adds task into task list

Format: `add TASK`
* Adds a task to the end of the task list
* Task refers to the task description to be shown
* If the task description is empty, an error message will be displayed to inform the user to enter a description

Examples:

`add CS2105 Assignment 1` adds CS2105 Assignment 1 task into task list

`add CS2103T Quiz` adds CS2103T Quiz task into task list

### Tagging task order [Coming Soon in v1.2]
Tags the order that the task is scheduled to be completed in

Format: `tag task (number)`
* If the number chosen is not currently in used, the task will be assigned this tag
* If the number chosen is already in used, an error message will inform the user to select another number
* If no number is given, an error message will appear to inform the user to key in a number

Examples:

`tag CS2105 Quiz (1)` tags CS2105 Quiz as 1st task to complete

`tag CS2103T Quiz (2)` tags CS2103T Quiz as 2nd task to complete


### List [Coming Soon in v1.2]
List tasks stored in task list

Format: `list`
* Display tasks that are stored in the task list

Examples:

`list` displays tasks that are stored in the task list



--------------------------------------------------------------------------------------------------------------------

## Summary of Commands

| Command | Format and Examples                                                     |
|---------|-------------------------------------------------------------------------|
| **Add** | **Format**: `add TASK`<br/> **Example**: `add CS2105 Assignment 1`      |
| **Tag**  | **Format**: `tag task (number)`<br/> **Example**: `tag CS2105 Quiz (1)` |
| **List** | **Format**: `list`<br/> **Example**:`list`                               |

