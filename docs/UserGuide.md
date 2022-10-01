---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * <TO_BE_IMPLEMENTED>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

*  Quotes optional for string arguments (i.e. `add member "egg"` and `add_member egg` are equivalent)
* Double Tab to autocomplete string values
* Arguments are delimited with flags (`add member "egg" --email="asd@a.com"`)
</div>

### Viewing help : `help`

[Coming Soon]


### Adding a new member to the team `add member`

Add a new team member to the user’s team. Will throw an error if the member already exists in the team.

Format: `add member “MEMBER_NAME”`

Examples:
* `add member egg`
* `add member “potato egg”`

### Delete a member from team `delete member`

Delete a team member from the user’s team. Will throw an error if no member with that name is found. Take note that names are case-sensitive.

Format:
* `delete member “MEMBER_NAME”`
* `delete member --index=INDEX`
* `delete member -i INDEX`

Examples:
* `delete member “potato”`
* `delete member --index=2`
* `delete member -i 2`


### Listing all members of the team : `list members`

View all the members currently in the team, in the form of a list.

Format: `list members`

### Add task to team : `add task`

Add a new task to the user’s team.

Format: `add task “TASK_NAME”`

Examples:
*  `add task “bake with butter”`
*  `add task fry`

### Assign task to team member: `assign task`

Assign an existing task to a team member in the user’s team. Will throw an error if either the team member or the task is not found in the user’s team.

Format: `assign task “TASK_NAME” “TEAM_MEMBER”`

Examples:
* `assign task fry potato` assigns the task "fry" to "potato"
* `assign task “bake with butter” “potato”` assigns the task "bake with butter" to "potato"

### Delete task from team : `delete task`

Delete an existing task from the team. Will throw an error if the task is not found in the user’s team.

Format: `delete task “TASK_NAME”`

Examples:
* `delete task fry`
* `delete task “bake with butter”`

### List tasks in team: `list tasks`

View all the tasks currently in the user’s team in the form of a list.

Format: `list tasks`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary


| Action | Format, Examples |
|--------|------------------ |
| TBC    | TBC |
|  TBC   | TBC |
