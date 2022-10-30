---
layout: page
title: User Guide
---

Welcome to the NotionUS user guide! 

**NotionUS** is a lightweight but powerful application that can help you to manage your assignments and tasks. It sports 
a sleek and minimalist design which allows you to clearly see your outstanding tasks at a glance. It provides a command
line user interface which aids fast typists in managing their assignments quickly, while still providing the benefits of
a graphical user interface.

|   If you are   |                                         You should                                         |
|:--------------:|:------------------------------------------------------------------------------------------:|
|   a new user   | proceed to our [quick start](#quick-start) section to set up NotionUS and try its features |
| returning user |       skip to our [command summary](#command-summary) for a summary of our features!       |

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
   1. To check if you have Java installed, please open up a terminal (search for `Command Prompt` on Windows and 
      `Terminal` on Mac OS) and type in `java -version`. This will show your java version, if you have it installed.
   2. If you do not have Java 11 or above installed, please proceed to this [link](https://www.oracle.com/java/technologies/downloads/#java11)
      and download the appropriate Java for your operating system.

2. Download the latest NotionUS.jar from [here](https://github.com/AY2223S1-CS2103T-F12-3/tp/releases/).

3. Move the file to the folder you want to use as the home folder for NotionUS. Note that a new folder will be created
   in the same location as the file, which will contain the application data.

4. Double-click the file to start the app. As seen below, some sample data has been pre-loaded for you to experiement with.

    ![Ui](images/user-guide/Ui.png)

5. Try typing some of the following commands in the “Enter command here...” box and press “ENTER” to execute the command

   Some example commands you can try:

   - `add Tutorial 3 -m CS2103T -d 2022-09-16` :
     Adds a task called `Tutorial 3` for the module `CS2103T` with the deadline `2022-09-16` into the task list.
   - `mark 1` :
     Marks the first task in the list as complete.
   - `ls --module CS2103T` :
     Lists all tasks associated with the module, `CS2103T`.
   - `delete 2` : 
     Deletes the second task in the list.
   - `edit 3 -n Assignment 2` : 
     Changes the name of the third task in the list to `Assignment 2`.
   - `find tutorial` :
     Finds anything with the keyword 'tutorial' (not case-sensitive or strictly matched words)

   You can also continue reading below to read more about how to use each command!

--------------------------------------------------------------------------------------------------------------------

## Features

Before proceeding, do make sure that you can understand our notation for command formats:

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `lower_case` are commands or flags to be typed as-is 
  * e.g. in `ls -t TAG_NAME`, `ls -t` must be typed as-is and is case-sensitive 
* Words in `UPPER_CASE` are values of parameters to be supplied by the user
  * e.g. in `ls -d DATE`, `DATE` refers to the value of the `-d` parameter supplied to the `ls` command 
* Words in `[Square brackets]` are optional parameters 
  * e.g. in `add -n TASK_NAME -m MODULE [-d DATE] [--tag TAG_NAME]*`, `[-d DATE]` and `[--tag TAG_NAME]*` can be omitted 
* Words that are followed by `*` are parameters that can be used multiple times including zero times 
  * e.g. in `tag TASK_NUMBER -t TAG_NAME*`, `TAG_NAME` can be included 0 or multiple times. 

**:information_source: Additional information about commands:**<br>
* Dates must be written in the YYYY-MM-DD format
* Command parameters (e.g. `-a`, `-m`) can be made in any order. 
  * e.g. `ls -u --module CS2103T` and `ls --module CS2103T -u` will give the same result
* If a parameter is expected only once in a command but was specified multiple times, the last occurrence of it will be taken
  * e.g. if you enter `edit 1 -d 2022-10-22 -d 2022-10-30`, this will be interpreted as `edit 1 -d 2022-10-30`
* Extraneous parameters for commands (i.e. `help`, `showarchive`, etc.) or flags (i.e. `-a`, `-u`, etc.) that do not take in parameters will be ignored
  * e.g. if you enter `showarchive 2103`, this will be interpreted as `showarchive` 

</div>

### Getting help : `help`

Displays list of commands and information about NotionUS.

Format: `help`

### Adding a task: `add`

Adds a task to the task list. However, if you try to add in a task with the same name and module as an existing task,
we will inform you that such a task already exists within the task tracker.

Format: `add -n TASK_NAME -m MODULE [-d DATE] [--tag TAG_NAME]...`
* `TASK_NAME` can contain spaces
* `MODULE`: Should be alphanumeric, ie must not contain any spaces.
* `DATE`: Must be in the format of YYYY-MM-DD.
* `TAG_NAME`: The word to tag the task with, should be alphanumeric, ie must not contain any spaces.

Examples:
* `add -n Task 1 -m CS2103T -d 2022-10-15 --tag homework`

### Marking a task as completed: `mark`

Mark a task as complete.

Format: `mark TASK_NUMBER`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Example: `mark 2`

### Unmarking a task: `unmark`

Unmark a task, ie mark a task as incomplete.

Format: `unmark TASK_NUMBER`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Example: `unmark 2`

### Tagging a task: `tag`

Allows you to tag a task.

Format : `tag TASK_NUMBER -t TAG_NAME*`
* `TASK_NUMBER`: This is the number of the task currently displayed.
* `TAG_NAME`: The word to tag the task with, should be alphanumeric, ie must not contain any spaces.

Example: `tag 2 -t optional`

### List : `ls`

`ls` commands filter the task list. There are multiple ways to filter the task list, such as
listing all tasks, unmarked tasks, all tasks under a module name, etc. You may apply multiple list flags in one
command to filter a list down to the results you are looking for. To reset the list, use the command `ls -a`.

Current filters applied will be shown in the UI at the top bar.

**Note that `find` searches globally, across all of a task's attributes**

#### Listing all tasks : `ls -a`

Shows a list of all tasks in the task list.

Format: `ls -a`

#### Listing all unmarked tasks : `ls -u`

Shows a list of all unmarked tasks in the task list, ie shows a list of uncompleted tasks.

Format: `ls -u`

#### Listing all marked tasks : `ls -m`

Shows a list of all marked tasks in the task list, ie shows a list of completed tasks.

Format: `ls -m`

#### Listing all tasks under the same module : `ls --module`

Shows a list of all tasks under the same module.

Format: `ls --module MODULE`
* `MODULE`: Should be alphanumeric, ie must not contain any spaces.

Example: `ls --module cs2103t`

#### Listing all tasks containing the same tag : `ls -t`

Shows a list of all tasks under the same module.

Format: `ls -t TAG_NAME`
* `TAG_NAME`: The word to tag the task with, should be alphanumeric, ie must not contain any spaces.

Example: `ls -t highPriority`

#### Listing all tasks with deadline on or after a date : `ls -d`

Shows a list of all tasks under the same module.

Format: `ls -d DATE`
* `DATE`: Must be in the format of YYYY-MM-DD.

Example: `ls -d 2022-11-11`

#### Listing all task names with the matching keywords: `ls -n`

Shows a list of all tasks with matching names.

Format: `ls -n KEYWORD [MORE_KEYWORDS*]`
* `MORE_KEYWORDS`: Keywords should be separated by spaces.

Example: `ls -n task1`

### Find tasks by name : `find`

The `find` command finds the task names or tags which contain the word or words given in the prefix. 
`find` is not case-sensitive and the keyword being searched does not have to match a whole word
(Example: searching `tap` in task name `tape` will be shown).

Format: `find WORD [MORE_WORDS]`

Singular word search example: `find tutorial`

Multiple word search example: `find Week tutorial`
finds **any** task name or tag with either `Week` or `tutorial`.

### Archiving data files : `archive`

Allows you to remove a task from task list and store in archived file.

Format: `archive TASK_NUMBER | DATE`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Examples:
* `archive 1`: archives first task in task list.

### View Archived Tasks : `showarchive`

Displays a list of archived tasks.

Format: `showArchive`

### Editing a task : `edit`

Edits an existing task in the task list, at least one field needs to be edited. 

Format: `edit TASK_NUMBER [-n TASK_NAME] [-m MODULE] [-d DATE] [-t TAG_NAME*]`

* `TASK_NUMBER`: This is the number of the task currently displayed.
* `MODULE`: Should be alphanumeric, ie must not contain any spaces.
* `DATE`: Must be in the format of YYYY-MM-DD.
* `TAG_NAME`: The word to tag the task with, should be alphanumeric, ie must not contain any spaces.

Examples:
*  `edit 1 -m CS2103T -n ip` Edits the taskName to ip.

### Deleting a task : `delete`

Allows user to delete a task from task list. 

Format: `delete TASK_NUMBER`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Examples: 
* `delete 1`
  * Deletes first task in the task list.
  * Remaining tasks’ `TASK_NUMBER` will be automatically updated. 
  
### Clearing all entries : `clear`

Clears all entries of tasks in the task list. 

Format: `clear`

### Exiting the program : `exit`

Terminates and exits the program.  

Format: `exit`

### Command history : <kbd>Up</kbd>/<kbd>Down</kbd> keys 
Loads previous command into the _Command Box_. 

While the _Command Box_ is in focus, use the <kbd>Up</kbd> and <kbd>Down</kbd> arrow keys to navigate through the command history that is automatically loaded in. 

### Saving the data
NotionUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
NotionUS data are saved as a JSON file `[JAR file location]/data/notionusdata.json`. Advanced users are welcome to update data directly by editing that data file.

**Caution:**
If your changes to the data file makes its format invalid, NotionUS will discard all data and start with an empty data file at the next run.

### Archiving data files : `archive`

Allows you to remove a task from task list and store in archived file. 

Format: `archive TASK_NUMBER`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Examples: 
* `archive 1`: archives first task in task list.

### Autocomplete

When the user starts to type in the start of a command, a popup menu will appear with options to complete your input.
Use the <kbd>Up</kbd> and <kbd>Down</kbd> arrow keys to navigate through the options and <kbd>Enter</kbd> to fill the command box with the command
of your choice.

--------------------------------------------------------------------------------------------------------------------

## FAQ

To be added..

--------------------------------------------------------------------------------------------------------------------

## Command summary

Format meanings:
* Words in `lower_case` are commands or flags to be typed as-is
* Words in `UPPER_CASE` are values of parameters to be supplied by the user
* Words in `[Square brackets]` are optional parameters
* Words that are followed by `*` are parameters that can be used multiple times including zero times

| Action                      | Format                                                                                                                                                                                                                                                                                                                                                | Examples                                     |
|-----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------|
| **Add** task                | `add -n TASK_NAME -m MODULE [-d YYYY-MM-DD] [--tag TAG_NAME*]`                                                                                                                                                                                                                                                                                        | `add -n Tutorial 3 -m CS2103T -d 2022-09-16` |
| **Archive** task            | `archive TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                 |                                              |
| **Clear** all tasks         | `clear`                                                                                                                                                                                                                                                                                                                                               |                                              |
| **Delete** task             | `delete TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                  | `delete 3`                                   |
| **Edit** task               | `edit TASK_NUMBER [-n NEW_NAME] [-m NEW_MODULE] [-d NEW_DEADLINE] [-t NEW_TAG*]`                                                                                                                                                                                                                                                                      | `edit 1 -n CS2103T ip`                       |
| **Exit** NotionUS           | `exit`                                                                                                                                                                                                                                                                                                                                                |                                              |
| **Find** task with name     | `find KEYWORD...`                                                                                                                                                                                                                                                                                                                                     | `find Tutorial Lab`                          |
| **Help**                    | `help`                                                                                                                                                                                                                                                                                                                                                |                                              |
| **List** specific tasks     | `ls [-a] [-u] [-m] [--module MODULE] [-t TAG] [-d YYYY-MM-DD]`<br/>`ls -a` View all tasks<br/>`ls -u` View all incomplete tasks<br/> `ls -m` View all marked tasks<br/> `ls --module MODULE` View tasks under the specific module<br/> `ls -t TAG_NAME` View tasks with a specific tag<br/> `ls -d YYYY-MM-DD` View tasks on or after a specific date | `ls -u --module CS2103T`                     |
| **Mark** tasks              | `mark TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                    | `mark 2`                                     |
| **Show Archived** tasks     | `showarchive`                                                                                                                                                                                                                                                                                                                                         |                                              |
| **Tagging** a task          | `tag TASK_NUMBER -t TAG_NAME`                                                                                                                                                                                                                                                                                                                         | `tag 1 -t highPriority`                       |
| **Unmark** tasks            | `unmark TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                  | `unmark 2`                                   |
| Accessing previous commands | Use the up and down arrow keys                                                                                                                                                                                                                                                                                                                        |                                              |
