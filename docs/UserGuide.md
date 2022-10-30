# Contactmation User Guide

## Table of contents

1. [Introduction](#introduction)
2. [About](#about)
3. [Prerequisites](#prerequisites)
4. [Quick start](#quick-start)
5. [Features](#features)
    1. [Definitions](#definitions)
    2. [Scoping](#scoping)
    3. [Basic features](#basic-features)
       1. [General commands](#general-commands)
          1. [Clear command](#clear-command-clear)
          2. [Find command](#find-command-find)
          3. [Exit command](#exits-the-program-exit)
       3. [Contact commands](#contact-commands)
          1. [Add a contact](#add-a-contact-person-new)
          2. [Delete a contact](#delete-a-contact-person-delete)
          3. [Searching for a contact](#searching-for-a-contact-find)
          4. [Listing all contacts](#listing-all-contacts-list)
       4. [Group commands](#group-commands)
          1. [Creating a group](#creating-a-group-team-new)
          2. [Deleting a group](#deleting-a-group-team-delete)
          3. [Adding people to a group](#adding-contacts-to-a-group-assign)
          4. [Removing people from group](#removing-contacts-from-group-team-remove)
       5. [Task commands](#task-commands)
          1. [Adding a task to a group](#adding-a-task-to-a-group-task-add)
          2. [Deleting a task from group](#deleting-a-task-from-group-task-delete)
          3. [Set progress for tasks](#set-progress-for-tasks-task-progress)
    4. [Advanced features](#advanced-features)
       1. [Sequence command](#sequence-command-seq)
       2. [Iterate command](#iterate-command-foreach)
       3. [Select command](#select-command-select)
6. [FAQ](#faq)
7. [Future plans](#future-plans)
8. [Glossary](#glossary)
9. [Commands summary](#commands-summary)
   1. [General commands](#general-commands-summary)
   2. [Contact commands](#contact-commands-summary)
   3. [Group commands](#group-commands-summary)
   4. [Task commands](#task-commands-summary)
   5. [Advanced commands](#advanced-commands-summary)
--------------------------------------------------------------------------------------------------------------------
## Introduction

>**What is Contactmation?**

Contactmation is a powerful **desktop based team management solution** that **helps its users efficiently and
effectively manage many projects and groups at once.**

Contactmation will be able to help you save all your contact details, keep track of
each projects' group and subgroup, and delegate tasks to each group.


>**Who is Contactmation for?**

Contactmation is for **project managers and supervisors** that want to maintain an organised view of their
projects and streamline the management of their projects.
--------------------------------------------------------------------------------------------------------------------
## About

### Graphic User Interface

The following figure shows the different graphical components of our application.
We will be referring to these terminologies throughout the user guide:

![Contactmation ui elements](images/ContactmationUi.png)

---

## Prerequisites

- Ensure that `Java 11` and above is installed on your device. If you do not have `Java 11` installed, please refer to
  this [Java 11 installation guide](https://www.codejava.net/java-se/download-and-install-java-11-openjdk-and-oracle-jdk).

---

## Quick start

1. Ensure that the [prerequisites](#prerequisites) are met before installing Contactmation.


2. Download the latest version of `contactmation.jar` from
   [here](https://github.com/AY2223S1-CS2103T-T11-1/tp/releases).


3. Save `contactmation.jar` into a Desktop folder on your computer. This folder will now be the home folder
   for Contactmation.


4. Double-click on `contactmation.jar` to start up the application. You will be greeted with the current window
   if everything goes well:

![Contactmation ui main window](images/ContactmationUiClean.png)

> **Note**
> If you wish to clear all default entries, use the [clear](#clear-command-clear) command.

5. You may begin by referring to the [basic features](#basic-features) section to get started on using
   Contactmation. For additional, more powerful commands, refer to our [advanced features](#advanced-features),
   especially if you are comfortable with the CLI or have prior programming experience.

---

# Features

## Definitions

In this user guide, we define any parameters within **square brackets** e.g. `[t/new_tag]` as
**optional parameters**, and commands within **angled brackets** e.g. `<command>` are **placeholder parameters**.

**Ellipses** `...` indicate that more than 1 argument of a certain type can be given to a single command.
For example, `[t/tags...]` means that an optional number (0 or more) of `tags` can be added to a single
user command, but `t/tags...` means that 1 or more `tags` can be given to a single user command.

## Scoping

Since Contactmation is a multi group management contact application, it supports scoping to facilitate
easy access between groups and subgroups of a group project.

Scoping defines the relationship between different groups. For example, a subgroup can be contained within
another group, similar to how a folder on your desktop can be contained within another folder.

There may also be many subgroups under a group, similar to how there may be many subfolders under a folder.

Adding to the analogy, your contacts can be thought of as files on your desktop. Contacts can thus be
added to a group or a subgroup, similar to how files can be added into folders.

## Basic features

## General commands

### Clear command: `clear`

Clears all group, contact and task entries from the application.

> **Warning**
> **THIS ACTION IS IRREVERSIBLE! RUN THIS COMMAND AT YOUR OWN DISCRETION!**

**Format:** `clear`

### Find command: `find`

**CURRENTLY A WORK IN PROGRESS DUE TO BUGS**

Searches for a contact, group or task that matches the given `KEYWORD`. Searches may also include `MORE_KEYWORDS`
to further narrow the search for a contact, subgroup or task within the current [scope](#scoping).

**Format:** `find <KEYWORD> [<MORE_KEYWORDS>]`

**Examples:**

- `find John Doe`
- `find task1 task2`

### Exits the program: `exit`

Exits the current session and closes the application.

**Format:** `exit`

## Contact commands

### Constraints on contact information

The following contact commands comply with these placeholder constraints:

- The `NAME` of the contact must be alphanumeric and can contain whitespace.
- The `PHONE_NUMBER` of the contact must be at least 3 digits long.
- The `EMAIL` of the contact must be in the format `local-part@domain`.

  - `Local-part`: Only contain alphanumeric characters and these special characters, excluding
    the parentheses, (+\_.-). The local-part may not start or end with any special characters.
  - `Domain`:

    - Ends with a domain label at least 2 characters long.
    - Have each domain label start and end with alphanumeric characters.
    - Have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

- The `ADDRESS` can take any values, but it should not be blank.
- The `TAG` must be alphanumeric.
- The `INDEX` must be a positive integer which cannot exceed the number of contacts currently displayed in the
  application.
- The `KEYWORD` and `MORE_KEYWORDS` must be alphanumeric.

### Add a contact: `person new`

Adds a new contact with a name within the current [scope](#scoping). Additionally, other contact details such
as the phone number, email, address and multiple tags may be included during the creation of the contact.

**Format:** `person new n/<NAME> [p/<PHONE_NUMBER>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>...]`

**Examples:**

- `person new n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney`
- `person new n/Betty White`

### Delete a contact: `person delete`

Delete a contact from the contact list by their listed `INDEX` within the current [scope](#scoping).

**Format:** `person delete <INDEX>`

**Example:**

- `person delete 1`

### Searching for a contact: `find`

Refer to the [find](#find-command-find) command for more information.

### Iterate command

Refer to the [foreach](#iterate-command-foreach) command for more information.

### Listing all contacts: `list`

Lists all current contacts in your contact list within the current [scope](#scoping).

**Format:** `list`

### Select command

Refer to the [select](#select-command-select) command for more information.

## Group commands

### Constraints on group information

The following group commands comply with these placeholder constraints:

- The `GROUP NAME` must be alphanumeric.
- The `INDEX` must be a positive integer which cannot exceed the number of contacts / groups currently displayed
  in the application.

### Creating a group: `team new`

Creates a new group with the specified group name within the current [scope](#scoping). This new group
will thus be a subgroup of the group you are currently scoped in.

**Format:** `team new <GROUP NAME>`

**Example:**

- `team new namingIsHard`

### Deleting a group: `team delete`

Removes an existing group from the current [scope](#scoping). All subgroups of the group you are currently
deleting will also be deleted.

**Format:** `team delete <INDEX>`

**Example:**

- `team delete 1`

### Adding contacts to a group: `assign`

Adds a contact to a group.

**Format:** `assign g/<INDEX> u/<INDEX>`

**Example:**

- `assign g/3 u/7`

### Iterate command

Refer to the [foreach](#iterate-command-foreach) command for more information.

### Select command

Refer to the [select](#select-command-select) command for more information.

### Removing contacts from group: `team remove`

Removes the contact from the current group by their currently specified `INDEX` as shown in
the application window.

**Format:** `team remove <INDEX>`

**Example:**

- `team remove 3`

### Group scope commands

> **Note**
> Please look at how [scoping](#scoping) works before continuing.

### Changing the group scope: `cg`

Updates the group scope that is currently being displayed in the application. This command is similar to going
into a folder on your desktop, or stepping out of it.

**Formats:**

- `cg <INDEX>`
- `cg ..` changes the group scope to its parent group. This is similar to stepping out of a folder once.
- `cg /` changes the group scope to the root group. This is similar to moving your current context to the
  root folder.

## Task commands

### Adding a task to a group: `task add`

Adds a new task to an existing group scope. This group cannot be the root group.

**Format:** `task add t/<TITLE> d/<DESCRIPTION>`

**Example:**

- `task add t/Complete all CS2103T homework d/Give description here`

### Deleting a task from group: `task delete`

Deletes an existing task from a group by their `INDEX` within the current [scope](#scoping).

Format: `task delete <INDEX>`

Example:

- `task delete 1`

### Iterate command

Refer to the [foreach](#iterate-command-foreach) command for more information.

### Select command

Refer to the [select](#select-command-select) command for more information.

### Set progress for tasks: `task progress`

**THIS FEATURE IS CURRENTLY IN PROGRESS**

Sets the progress level for each task that has been listed.

Format: `task progress i/<INDEX> <LEVEL>`

- Index indicates the index of the task in the list.
- Level indicates the progress level, and can only be set to 25%, 50%, 75%, 100%.

Example:

- `task progress 1 25%`

This sets the progress of the first task in the list as 25%.

## Advanced features

### Sequence command: `seq`

Adds

### Iterate command: `foreach`

Iterates through each task, contact or group within the current [scope](#scoping), and
applies the command to each of the currently listed task, contact or group.

**Format:** `<item> foreach <command>`

**Example:**

- `task foreach rename`

### Select command: `select`

Selects the current task, contact or group within the current [scope](#scoping) and
run a command on that task, contact or group.

**Format:** `<item> select <INDEX> <command>`

**Example:**

- `task select 3 isComplete`

## FAQ

> How can I install `Java 11`?

Follow the guide for installing `Java 11` [here](#prerequisites).

> Will this application also apply to a general, non-professional user?

This depends on what you will be using Contactmation for. It still can be used simply as an application
for simply saving and organizing contacts.

## Future plans

Our future plans for Contactmation includes:

- The ability to delegate tasks to individuals.
- Contacting any person through the application simply by clicking their email, phone number
etc.
- Releasing a version of Contactmation on the mobile platform.
- The ability to synchronize data between multiple copies of Contactmation on your mobile and desktop.
- A pop-up window that shows the detailed form of descriptions to the user.
- A for loop command to iterate through and count groups, contacts or tasks by their attribute.

## Glossary

| Vocabulary  | Description |
| ----------- | ----------- |
| Command sequence | What you, the user, would write in the command box for the execution of a command. |
| Contact   | A contact with contact information.  |
| Group   | A container that contains people that work on a similar project.  |
| Index    | The numerical placing of a group, contact or task in the current application display. |
| Item    | An item can refer to a group, contact or task. |
| Pipe | The output of the previous section of commands will be used as input for the next set of commands.|
| Root group   | Refers to the application not being in any scope. |
| Scope  | A constraint on the groups, people and tasks you are able to view at one time on the display. |
| Task  | Assigned to people or groups |

## Commands summary

### General commands summary

| Command  | Format |
| ----------- | ----------- |
| Group   | A container that contains people that work on a similar project.  |
| Item    | An item can refer to a group, contact or task. |
| Contact   | A contact with contact information.  |
| Task  | Assigned to people or groups |

### Group commands summary

| Command  | Format |
| ----------- | ----------- |
| Group   | A container that contains people that work on a similar project.  |
| Item    | An item can refer to a group, contact or task. |
| Contact   | A contact with contact information.  |
| Task  | Assigned to people or groups |


### Contact commands summary

| Command  | Format |
| ----------- | ----------- |
| Group   | A container that contains people that work on a similar project.  |
| Item    | An item can refer to a group, contact or task. |
| Contact   | A contact with contact information.  |
| Task  | Assigned to people or groups |


### Task commands summary

| Command  | Format |
| ----------- | ----------- |
| Group   | A container that contains people that work on a similar project.  |
| Item    | An item can refer to a group, contact or task. |
| Contact   | A contact with contact information.  |
| Task  | Assigned to people or groups |

### Advanced commands summary

| Command  | Format |
| ----------- | ----------- |
| Group   | A container that contains people that work on a similar project.  |
| Item    | An item can refer to a group, contact or task. |
| Contact   | A contact with contact information.  |
| Task  | Assigned to people or groups |

[Back to top](#contactmation-user-guide)
