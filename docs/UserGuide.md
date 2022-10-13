---
layout: page
title: User Guide
---

# ModtRekt User Guide

ModtRekt is a **desktop app for managing modules and tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

# Table of Contents
- [UI Mockups](#ui-mockups)
- [Quick Start](#quick-start)
- [Features](#features)
- [Command Summary](#command-summary)

## UI Mockups
![Ui](images/Ui.png)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.

1. Download the latest `ModtRekt.jar` from [here](https://github.com/AY2223S1-CS2103T-W10-4/tp/releases).

1. Copy the file to the folder you want to use as the home folder for your module tracker.

1. Double-click the file to start the app. The GUI similar to the screenshot above should appear in a few seconds. Note how the app contains some sample data.

1. Type the command in the command box and press `Enter` to execute it. e.g. typing `help` and pressing `Enter` will open the help window.

1.  Here are some example commands you can try:

    1. `list`: Lists all the active tasks.

    1. `add module CS2103T`: Adds a module called CS2103T to the module tracker.

    1. `cd CS2103T`: Sets the current module page to CS2103T.

    1. `add task ip week 6 -d 16/09/2022`: Adds a task called ip week 6, with a deadline of 16/09/2022, to the module tracker.

    1. `remove task 1`: Removes the first task of CS2103T.

    1. `exit` : Exits the app.

    1. Refer to the Features below for details of each command.


--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

### General
`help`

Shows a message which explains the basic commands and links to the user guide.

### Modules

### Adding a module: `add module`

Adds a module to the program.

Format: `add module <module code>`

Shorthand: `add -m <module code>`
- The module code is case insensitive e.g. `cs2103t` will match **CS2103T**

Examples:
- `add module CS2103T`
- `add -m CS2103T`

### Removing a module: `remove module`

Deletes a module from the program.

Format: `remove module <module code>`

Shorthand: `rm -m <module code>`
- The module code is case insensitive e.g. `cs2103t` will match **CS2103T**
- Module code must match an existing module.

Examples:
- `remove module CS2103T`
- `rm -m CS2103T`

### Entering a module: `cd`

Sets the current module page to the specified module.

Format: `cd <module code>`

Allows user to view information relating to the specified module.
- Scopes the user’s actions to the specified module.
- The module code is case insensitive e.g. `cs2103t` will match **CS2103T**
- Module code must match an existing module.

Examples:
- `cd CS2103T`

### Listing all modules: `list modules`

Lists all the modules added by the user.

Format: `list modules`

Shorthand: `ls -m`

Examples:
- `list modules`

### Tasks

### Adding a task: `add task`

Adds a task under specific module.
- User must be within a module page.
- User may optionally include a deadline for the task by specifying the -d flag along with the deadline in DD/MM/YYYY format.

Format: `add task <task> [-d <deadline>]`

Shorthand: `add -t <task> [-d <deadline>]`

Examples:
- `add task do ip tasks -d 15/09/2022`
- `add -t do ip tasks`

### Removing a task: `remove task`

Removes a task under a specific module.
- User must be within a module page.
- Index must be a valid integer. Users may use the list tasks command to find the indexes of their tasks.

Format: `remove task <task index>`

Shorthand: `rm -t <task index>`

Examples:
- `remove task 1`
- `rm -t 1`

### Listing all tasks: `list tasks`

Lists all tasks under either all modules or a specific module.
- Module code is inferred from the current module page if the user is currently within a module page.
- The module code is case insensitive e.g. cs2103t will match CS2103T
- Module code must match an existing module.


Format: `list tasks [-m <module code>]`

Shorthand: `ls -t [-m <module code>]`

Examples:
- `list tasks -m cs2103t`
- `ls -t -m CS2103T`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ModuleList home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

```< >``` for mandatory arguments

```[ ]``` for optional arguments

### General

|       Action        | Format          | Examples        |
|:-------------------:|-----------------|-----------------|
| **List Everything** | `list`<br/>`ls` | `list`<br/>`ls` |
|      **Help**       | `help`<br/>`h`  | `help`<br/>`h`  |
|      **Exit**       | `exit`          | `exit`          |

### Modules

|        Action        | Format                                              | Examples                                    |
|:--------------------:|-----------------------------------------------------|---------------------------------------------|
|   **Add a Module**   | `add module <mod. code>`<br/>`add -m <mod. code>`   | `add module CS2103T`<br/>`add -m CS2103T`   |
| **Remove a Module**  | `remove module <mod. code>`<br/>`rm -m <mod, code>` | `remove module CS2103T`<br/>`rm -m cS2103t` |
|  **Go to a Module**  | `cd <mod, code>`                                    | `cd CS2103T`                                |
| **List All Modules** | `list modules` <br/> `ls -m`                        | `list modules`<br/>`ls -m`                  |

### Tasks

|       Action       | Format                                                                              | Examples                                                                          |
|:------------------:|-------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
|   **Add a Task**   | `add task <description> [-d <deadline>]`<br/>`add -t <description> [-d <deadline>]` | `add task do ip tasks deadline 15/09/2022`<br/>`add -t do ip tasks -d 15/09/2022` |
| **Remove a Task**  | `remove task <index> `<br/>`rm -t <index>`                                          | `remove task 1`<br/>`rm -t 1`                                                     |
| **List All Tasks** | `list tasks` <br/> `ls -t`                                                          | `list tasks`<br/>`ls -t`                                                          |
