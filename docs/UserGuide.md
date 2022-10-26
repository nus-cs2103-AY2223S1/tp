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
* `lower_case` text mean that they should be typed as-is
* `UPPER-CASE` text denote text that are to be replaced with the actual input
* `[Square brackets]` surround optional inputs
* `...` denotes that the prior input can be made any number of times

And a few more details about commands
* Dates must be written in the YYYY-MM-DD format
* Command arguments (eg `-a`, `-m`) can be made in any order. For instance, `ls -u --module CS2103T` and `ls --module CS2103T -u` are the same command

### Getting help : `help`

Displays list of commands and information about NotionUS.

Format: `help`

### Adding a task: `add`

Adds a task to the task list.

Format: `add TASK_NAME -m MODULE [-d DATE] [--tag TAG_NAME]...`
* `TASK_NAME` can contain spaces
* `MODULE`: Should be alphanumeric, ie must not contain any spaces.
* `DATE`: Must be in the format of YYYY-MM-DD.
* `TAG_NAME`: The word to tag the task with, should be alphanumeric, ie must not contain any spaces.

Examples:
* `add Task 1 -m CS2103T -d 2022-10-15 --tag homework`

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

Format : `tag TASK_NUMBER TAG_NAME`
* `TASK_NUMBER`: This is the number of the task currently displayed.
* `TAG_NAME`: The word to tag the task with, should be alphanumeric, ie must not contain any spaces.

Example: `tag 2 optional`

## List and Find

`list` and `find` are commands that filter the list. There are multiple ways to filter the tasklist, such as
listing all tasks, unmarked tasks, all tasks under a module name etc. You may apply multiple list commands in one command
to filter a list down to the results you are looking for. To reset the list, use the command `ls -a`.

Current filters applied will be shown in the UI at the top bar.

**Note that `find` searches globally, across all of a task's attributes**

### List : `ls`

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

### Find tasks by name : `find`

The `find` command finds the task names which contain the word or words given in the prefix. 
`find` is not case-sensitive and the keyword being searched does not have to match a whole word
(Example: searching `tap` in task name `tape` will be shown).

Format: `find WORD [MORE_WORDS]`

Singular word search example: `find tutorial`

Multiple word search example: `find Week tutorial`
finds **any** task name with either `Week` or `tutorial`.

*Note that this is **NOT** the same as:*

`find Week` followed by `find tutorial`

as it filters the task names with `Week` then filters task names with `tutorial`.

#### Listing all archived tasks : `ls -archive`

Shows a list of all tasks that are archived. 

Format: `ls -archive`

### Editing a task : `edit`

Edits an existing task in the task list, at least one field needs to be edited. 

Format: `edit TASK_NUMBER [-n TASK_NAME] [-m MODULE] [-d DATE] [-t TAG_NAME]...`

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

### Returning to a previous command : `up`/`down` keys 
Loads previous command into the text input box. 

### Saving the data
NotionUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
NotionUS data are saved as a JSON file `[JAR file location]/data/notionusdata.json`. Advanced users are welcome to update data directly by editing that data file.

**Caution:**
If your changes to the data file makes its format invalid, NotionUS will discard all data and start with an empty data file at the next run.

#### Archiving data files : `archive`

Allows you to remove a task from task list and store in archived file. 

Format: `archive TASK_NUMBER | DATE`
* `TASK_NUMBER`: This is the number of the task currently displayed.
* `DATE`: Must be in the format of YYYY-MM-DD.

Examples: 
* `archive 1`: archives first task in task list.
* `archive 2022-10-17`: archives tasks with deadline on and before `DATE`.

--------------------------------------------------------------------------------------------------------------------

## FAQ

To be added..

--------------------------------------------------------------------------------------------------------------------

## Command summary


Format meanings:
* `lower_case` text mean that they should be typed as-is
* `UPPER_CASE` text denote text that are to be replaced with the actual input
* `[Square brackets]` surround optional inputs
* `...` denotes that the prior input can be made any number of times

| Action                      | Format                                                                                                                                                                                                                                                                                                                                                | Examples                                  |
|-----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------|
| **Add** task                | `add TASK_NAME -m MODULE [-d YYYY-MM-DD] [--tag TAG_NAME]...`                                                                                                                                                                                                                                                                                         | `add Tutorial 3 -m CS2103T -d 2022-09-16` |
| **Clear** all tasks         | `clear`                                                                                                                                                                                                                                                                                                                                               |                                           |
| **Delete** task             | `delete TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                  | `delete 3`                                |
| **Edit** task               | `edit TASK_NUMBER [-n NEW_NAME] [-m NEW_MODULE] [-d NEW_DEADLINE]`                                                                                                                                                                                                                                                                                    | `edit 1 -n CS2103T ip`                    |
| **Exit** NotionUS           | `exit`                                                                                                                                                                                                                                                                                                                                                |                                           |
| **Find** task with name     | `find KEYWORD...`                                                                                                                                                                                                                                                                                                                                     | `find Tutorial Lab`                       |
| **Help**                    | `help`                                                                                                                                                                                                                                                                                                                                                |                                           |
| **List** specific tasks     | `ls [-a] [-u] [-m] [--module MODULE] [-t TAG] [-d YYYY-MM-DD]`<br/>`ls -a` View all tasks<br/>`ls -u` View all incomplete tasks<br/> `ls -m` View all marked tasks<br/> `ls --module MODULE` View tasks under the specific module<br/> `ls -t TAG_NAME` View tasks with a specific tag<br/> `ls -d YYYY-MM-DD` View tasks on or after a specific date | `ls -u --module CS2103T`                  |
| **Mark** tasks              | `mark TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                    | `mark 2`                                  |
| **Tagging** a task          | `tag TASK_NUMBER TAG_NAME`                                                                                                                                                                                                                                                                                                                            |                                           |
| **Unmark** tasks            | `unmark TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                  | `unmark 2`                                |
| Accessing previous commands | Use the up and down arrow keys                                                                                                                                                                                                                                                                                                                        |                                           |
