---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

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

### Removing a module: `module remove`

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

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
