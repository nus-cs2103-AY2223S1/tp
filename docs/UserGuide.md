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

In this section, we will cover the features of ModtRekt, and introduce the syntax of the various commands.

<div markdown="block" class="alert alert-info">

### General
`help`

Shows a message which provides a link to this user guide.

Sample execution of command `help`:
![help-message](images/help-message.png)

`exit`

Exits the application.

### Modules

#### Adding a module: `add module`

Adds a module to the application.

To allow our users to easily add modules via their module code, our module information is fetched from 
[NUSMods](https://nusmods.com/modules?sem[0]=1&sem[1]=2&sem[2]=3&sem[3]=4),
hence the module code you specify must be a valid NUS module code. 
If the module code is invalid, the module will not be added. Additionally, since an integration with NUSMods 
is a collaboration we would like to seek in future revisions of our app, we have decided to use the AY2022-2023 module 
list, hence beyond this AY, the module information and codes may have changed.

Format: `add module <module code>`
Shorthand: `add mod <module code>`

- The module code is case-insensitive e.g. `cs2103t` will match **CS2103T**

Examples:
- `add module CS2103T`
- `add mod CS2103T`

The app also supports the addition of customised modules, which may or may not be in NUSMods.
This is useful for users with other commitments, such as internships, that they would like to track in the app.
Note that if your customised module is not in NUSMods, you would still need to specify the module credits, for example, 0.

Format: `add module <module code> -n <custom module name> -cr <module credits>`
Shorthand: `add mod <module code> -n <custom module name> -cr <module credits>`

Sample execution of command `add mod CS2100`:
![add-mod-sample](images/add-mod-sample.png)


#### Removing a module: `remove module`

Deletes a module from the program. Users specify a module code to delete.

Format: `remove module <module code>`
Shorthand: `rm mod <module code>`

- The module code is case-insensitive e.g. `cs2103t` will match **CS2103T**
- Module code must match an existing module.

Examples:
- `remove module CS2103T`
- `rm mod CS2103T`

Sample execution of command `remove mod CS2100`:
![remove-mod-sample](images/remove-mod-sample.png)


#### Entering a module: `cd`

Sets the current module page to the specified module.

Format: `cd <module code>`

Allows user to view information relating to the specified module.
- Scopes the user’s actions to the specified module.
- The module code is case-insensitive e.g. `cs2103t` will match **CS2103T**
- Module code must match an existing module.

Examples:
- `cd CS2103T`

Sample execution of command `cd CS2100`:
![cd-module-sample](images/cd-module-sample.png)

### Exiting a module: `cd`

Exits the current module page to revert to showing all modules.

Format: `cd ..`

Allows user to view all active tasks instead of tasks belonging to a specific module.

Examples:
- `cd ..`

Sample execution of command `cd ..`:
![cd-default-sample](images/cd-default-sample.png)

#### Marks done a module: `done`

Marks a module as done. 
- After you marked a module as done, this will increment
the total MCs you completed displayed on the left side of the application.
- When you mark a module as done, this will mark all active tasks 
corresponding to the module as done.

Format: `done module <module code>`, `done mod <module code>`

Examples:
- `done module CS1101S`

Sample execution of command `done module CS2100`:
![done-module-sample](images/done-module-sample.png)

#### Marks undone a module: `undone`

Marks a module as undone. 
- After you unmarked a module, this will decrement
the total MCs you completed displayed on the left side of the application.
- Unmarking a module will not affect its tasks. (It will not mark its original tasks as undone)

Sample execution of command `done module CS2105`, with CS2105 marked as done initially:
![undone-module-sample](images/undone-module-sample.png)

#### Listing all modules: `list module`

Lists all the modules added by the user.
- Using the command without `-a` tag will show you all active modules.
- Using the command with `-a` tag will show you all modules, including those marked as done.

Format: `list module [-a]`, `list mod [-a]`

Shorthand: `ls module [-a]`, `ls mod [-a]`

Examples:
- `list module -a`

Sample execution of command `list module -a`:
![list-module-sample](images/list-module-sample.png)

### Tasks

#### Adding a task: `add task`

Adds a task under specific module.
- User can optionally be in a module page (i.e. Executing the [`cd` command](#entering-a-module-cd) to enter a module).
- User may optionally include a deadline for the task by specifying the -d flag along with the deadline in DD/MM/YYYY format.
- Description of a task can **only** contain American Standard Code for Information Interchange 
  [(ASCII)](https://en.wikipedia.org/wiki/ASCII) characters

Format(if CDed): `add task <description> [-d <deadline>]`
Format(otherwise): `add task <description> [-d <deadline>] -c <module code>`


Examples (CDed into a module):
- `add task do ip tasks -d 15/09/2022`
- `add task -d 15/09/2022 do ip tasks`

Examples (Not CDed into a module):
- `add task -c CS2103T do ip tasks -d 15/09/2022`
- `add task -d 15/09/2022 do ip tasks -c CS2103T`

Sample execution of command `add "Assignment 1" -c CS2100 -d 2022-10-29`:
![add-task-sample](images/add-task-sample.png)

#### Removing a task: `remove task`

Removes a task under a specific module.
- User must be within a module page.
- Index must be a valid integer. Users may use the list tasks command to find the indexes of their tasks.

Format: `remove task <task index>`

Shorthand: `rm -t <task index>`

Examples:
- `remove task 1`
- `rm -t 1`

Sample execution of command `remove task 1` with 2 active tasks displayed:
![remove-task-sample](images/remove-task-sample.png)

#### Marking a task as done: `done task`

Marks a task as done, specified via its index on the displayed list.
- Index must be a valid integer. Users may use the list tasks command to find the indexes of their tasks.

Format: `done task <task index>`

Examples:
- `done task 1`

Sample execution of command `done task 1` with 6 active tasks displayed:
![done-task-sample](images/done-task-sample.png)

#### Marking a task as undone: `undone task`

Marks a task as undone, specified via its index on the displayed list.
- Index must be a valid integer. Users may use the list tasks command to find the indexes of their tasks.

Format: `undone task <task index>`

Examples:
- `undone task 1`

Sample execution of command `undone task 6` with 6 active tasks displayed:
![undone-task-sample](images/undone-task-sample.png)

#### Listing all tasks: `list tasks`

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
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous ModuleList home folder.
**Q**: Is my data saved when I close the app?<br>
**A**: Yes! We keep track of your modules and tasks, and they will automatically be shown when you re-open the application.
**Q**: Do I need a working internet connection to use this application?<br>
**A**: No, it is not required. We have a backup data file of all NUS modules for AY2022-2023, so no internet is required.


--------------------------------------------------------------------------------------------------------------------

## Command summary

```< >``` for mandatory arguments

```[ ]``` for optional arguments

### General

|       Action        | Format          | Examples        |
|:-------------------:|-----------------|-----------------|
|      **Help**       | `help`          | `help`          |
|      **Exit**       | `exit`          | `exit`          |

### Modules

|          Action           | Format                                                                              | Examples                                                           |
|:-------------------------:|-------------------------------------------------------------------------------------|--------------------------------------------------------------------|
|     **Add a Module**      | `add module <module code>`<br/>`add mod <module code>`                              | `add module CS2103T`<br/>`add mod CS2103T`                         |
|    **Remove a Module**    | `remove module <module code>`<br/>`rm mod <module code>`                            | `remove module CS2103T`<br/>`rm mod cS2103t`                       |
|    **Go to a Module**     | `cd <mod, code>`                                                                    | `cd CS2103T`                                                       |
|  **Marks done a Module**  | `done module <module code>` <br/> `done mod <module code>`                          | `done module CS1101S` <br/> `done mod CS1101S`                     |
| **Marks undone a Module** | `undone module <module code>` <br/> `undone mod <module code>`                      | `undone module CS1101S` <br/> `undone mod CS1101S`                 |
|   **List All Modules**    | `list module [-a]` <br/> `list mod [-a]` <br/> `ls module [-a]` <br/> `ls mod [-a]` | `list module`<br/> `list mod -a`<br/> `ls module`<br/> `ls mod -a` |

### Tasks

|       Action       | Format                                                    | Examples                                              |
|:------------------:|-----------------------------------------------------------|-------------------------------------------------------|
|   **Add a Task**   | `add task <description> [-d <deadline>] -c <module code>` | `add task do Assignment 1 -d 15/09/2022 -c CS2100`    |
| **Remove a Task**  | `remove task <index> `<br/>`rm -t <index>`                | `remove task 1`<br/>`rm -t 1`                         |
| **List All Tasks** | `list tasks` <br/> `ls -t`                                | `list tasks`<br/>`ls -t`                              |


### Glossary

|       Terms        | Description                                                                                             |
|:------------------:|---------------------------------------------------------------------------------------------------------|
|   **AY**           | Academic Year                                                                                           |
| **GUI**            | Graphical User Interface                                                                                |
| **CLI**            | Command Line Interface                                                                                  |
| **Syntax**         | Structure of statements                                                                                 |
| **NUSMods**        | A timetabling application created for NUS students, containing information about all modules in NUS.    |
| **cd**             | Shorthand for `change directory`, in the context of our application, modules can be seen as directories |
| **MCs**            | Modular credits                                                                                         |
| **ASCII**          | American Standard Code for Information Interchange                                                      |
| **NUS**            | National University of Singapore                                                                        |
