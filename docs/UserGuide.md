# Contactmation User Guide

Contactmation is a **desktop, contact management application** that is **optimized for team management and
delegation of tasks through the Command Line Interface** (CLI). Contactmation efficiently tracks progress of your
team projects.

## Table of contents

- [Prerequisites](#prerequisites)
- [Quick start](#quick-start)
- [Features](#features)
    - [Definitions](#definitions)
    - [Scoping](#scoping)
    - [Basic features](#basic-features)
        - [Add a contact](#add-a-contact-add)
        - [Delete a contact](#delete-a-contact-delete)
        - [Edit a contact](#edit-a-contact-edit)
        - [Searching for a contact](#searching-for-a-contact-find)
        - [Listing all contacts](#listing-all-contacts-list)
        - [Exits the program](#exits-the-program-exit)
    - [Teams](#teams)
        - [Creating a team](#creating-a-team-newteam)
        - [Deleting a team](#deleting-a-team-rmteam)
        - [Adding people to a team](#adding-people-to-a-team-add-or-team-team-id-add)
        - [Removing people from team](#removing-people-from-team-delete-or-delete-gteam-id)
        - [Adding team wide tasks](#adding-team-wide-tasks-task-add)
    - [Tasks](#tasks)
        - [Adding a task to a team](#adding-a-task-to-a-team-task-add)
        - [Deleting a task from team](#deleting-a-task-from-team-task-delete)
        - [Change task completion status](#change-task-completion-status-task-set-status)
        - [Set task completion time](#set-task-completion-time-task-set-completed_time)
- [FAQ](#faq)

--------------------------------------------------------------------------------------------------------------------
## Prerequisites

- Ensure that `Java 11` and above is installed on your device.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Download the latest `contactmation.jar` from [here](https://github.com/AY2223S1-CS2103T-T11-1/tp/releases).

2. Save `contactmation.jar` into a folder that you want to use as your home folder on your desktop.

3. Double-click the file `contactmation.jar` to start up the application.

4. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

## Definitions

In this user guide, we define any parameters within **square brackets** e.g. `[g/TEAMID]` as
**optional parameters**, and commands within **angled brackets** e.g. `<command>` are **placeholder parameters**.

**Ellipses** `...` indicate that more than 1 argument of a certain type can be given to a single command.
For example, `[USER ID...]` means that an optional number (0 or more) of `USER IDs` can be added to a single
user command, but `USER ID...` means that 1 or more `USER IDs` can be given to a single user command.

**Team ID** refers to a specific ID automatically given by the application during the creation of a team.
Any user commands involving `g/<TEAM ID>` will reference that particular team.

**User ID** refers to a specific ID automatically given by the application during the creation of a contact.

Any user commands involving `u/<USER ID>` will reference that particular contact.

## Scoping

Since Contactmation is a multi team management contact application, the app supports scoping.

- General Scope
    - Commands used within the main menu scope:
        - `<command> [parameters]` regular general scope command.
        - `<command> u/<USER ID>` command targeting a user of UID.
    - Team scope commands from general scope:
        - `<command> [parameters] g/<TEAM ID>`
        - `<command> [parameters] g/<TEAM ID> u/<USER ID>`


- Team Scope
    - Commands used within a team scope:
        - `<TEAM ID> <command> [parameters]`
    - Commands used to target individuals within a team:
        - `<TEAM ID>/<USER ID> <command> [parameters]`


## Basic features

### Add a contact: `add`

Adds a new contact with a name, phone number, email, address and optional tags.

Format: `add n/<NAME> [p/<PHONE_NUMBER>] [e/<EMAIL>] [a/<ADDRESS>] [t/TAG]`


### Delete a contact: `delete`

Delete a contact from the contact list by its `USER ID`.

- `delete <USER ID>`

### Edit a contact: `edit`

Edits a contact based on its `USER ID`.

Format: `edit <USER ID> [n/name] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]`

### Searching for a contact: `find`

Searches for a contact that matches the given keyword.

Format: `find <KEYWORD> [MORE_KEYWORDS]`

Examples:
- `find John Doe`
- `find 8881 2345`

### Listing all contacts: `list`

Lists all current contacts in your contact list, based on alphabetical order of contact names.

Format: `list`

### Exits the program: `exit`

Exits the current session.

Format: `exit`

## Teams

### Creating a team: `newteam`

Creates a new team with the specified team name along with optional parameters. These optional parameters are
`USER IDs` that are to be added to the team.

- Format: `newteam n/<team name> [USER ID...]`
    - `<team name>` is the name of the team to be created.
    - `[USER ID]` are optional parameters to add people with corresponding User ID to the team.

### Deleting a team: `rmteam`

Removes an existing team.

- Format: `rmteam <TEAM ID>`
    - `<TEAM ID>` is the ID of the team to be deleted.

### Adding people to a team: `add OR team <TEAM ID> add`

Adding members to an existing team.

- Adding a user under team scope:
    - `team <TEAM ID> add <USER ID>...`
- Adding a user under global scope:
    - `add g/<TEAM ID> <USER ID>...`

### Removing people from team: `delete OR delete g/<TEAM ID>`

- Delete user from team under team scope:
    - `team <TEAM ID> delete <USER ID>...`
- Delete user from team under global scope
    - `delete g/<TEAM ID> <USER ID>...`

### Adding team wide tasks: `task add`

- Adding task to team under team scope:
    - `team <TEAM ID> task add t/<title> [i/INFO]`
- Adding task to team under global scope:
    - `task add g/<TEAM ID> t/<title> [i/INFO]`

See more task related commands under [tasks](#tasks).

## Tasks

You can avoid typing `g/<TEAM ID>` by accessing the task via team scoping!

i.e. `team TEAMID task <related command> [task related details]`

### Adding a task to a team: `task add`

Adds a new task to an existing team.

Format: `task add t/<TITLE> g/<TEAM ID> [s/STATUS]`

- Adds a new task with the specified `TITLE` to the specified `TEAM ID`.
- The status of the new task can be optionally specified with `STATUS`.
- If a task with the same title already exists in the group, no changes will be made.
- The specified group must already exist.

Example:
- `task add t/Complete all CS2103T homework g/Students`

This creates a new task in the team `Students` with the title `Complete all CS2103T homework`.

### Deleting a task from team: `task delete`

Deletes an existing task from a team.

Format: `task delete t/<TITLE> g/<TEAM ID>`
- Delete an existing task with the specified `TITLE` to from specified `TEAM ID`.

Example:
- `task delete t/Complete all CS2103T homework g/Students`

This deletes a task in the team `Students` with the title `Complete all CS2103T homework`.

### Change task completion status: `task set status`

Sets the status of an existing task in a team.

Format: `task set status t/<TITLE> g/<TEAM ID> v/<STATUS>`
- Sets the status of the task with the specified `TITLE` and `TEAM ID` to `STATUS`.
- The existing status of the task will be overwritten by the new status.
- The specified task must already exist in the group.

Example:
- `task set status t/Clean beakers g/Lab v/Done`

This sets the status of the `Clean beakers` task in the `Lab` team to `Done`.

### Set task completion time: `task set completed_time`

Sets the time that a task has been completed.

Format: `task set completed_time t/<TITLE> g/<TEAM ID> v/<VALUE>`

- Sets the completed time of the task with the specified `TITLE` in `TEAM ID` to `VALUE`.
- The `VALUE` for the time should be in UTC format with a precision in minutes: `yyyy-MM-ddThh:mmZ`.
- The existing completed time of the task will be overwritten.
- The specified task must already exist in the group.

Example:
- `task set completed_time t/Generate report g/Accountants v/2022-09-13T10:20Z`

This sets the status of the `Generate report` task in the `Accountants` team to Sep 13, 2022 at 10:20am UTC time.

## FAQ

> How can I install `Java 11`?

Follow the guide for installing `Java 11` [here](https://www.codejava.net/java-se/download-and-install-java-11-openjdk-and-oracle-jdk).

[Back to top](#contactmation-user-guide)