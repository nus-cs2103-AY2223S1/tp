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

   - `add -n Tutorial 3 -m CS2103T -d 2022-09-16` :
     Adds a task called `Tutorial 3` for the module `CS2103T` with the deadline `2022-09-16` into the task list.
   - `mark 1` :
     Marks the first task in the list as complete.
   - `ls --module CS2103T` :
     Lists all tasks associated with the module `CS2103T`.
   - `delete 2` : 
     Deletes the second task in the list.
   - `edit 3 -n Assignment 2` : 
     Changes the name of the third task in the list to `Assignment 2`.
   - `find tutorial` :
     Finds anything with the keyword 'tutorial' (not case-sensitive or strictly matched words)

   You can also continue reading below to read more about how to use each command!

--------------------------------------------------------------------------------------------------------------------

## Understanding the GUI

We would first like to give a walk-around of the User Interface (UI) to allow you, the user to have a smoother experience as you will 
be able to use the app to its full potential.

Here is an image of the UI of the app.

![GUIwalkaround](images/user-guide/GUIwalkaround.png)


From top to bottom:
1. `Help` and `Archived` Buttons:
    * Click `Help` to open the help menu which will display a list of example commands and a link to our user guide.
    * Click `Archived` to open the archived tasks window, which shows any previously archived tasks.

2. Filter status display:
    * This displays any filters applied on the list using commands such as `ls` or `find`, for more information
      please refer to **Feature** section below.

3. Main task list:
    * This displays all your *current* tasks.

4. Result display:
    * The result display displays any information regarding command inputs, such as success or errors.

5. Command box:
    * This is where you enter commands to control the app.

### Autocomplete

When the user starts to type in the start of a command in the _Command Box_, a pop-up menu will appear with options to complete your input.
Use the `up` and `down` arrow keys on your keyboard to navigate through the options and press the `Enter` key to fill the command box with the command
of your choice.

Here is an example of autocomplete in action:

![Autocomplete](images/user-guide/Autocomplete.png)

However, it is recommended to read through the features section to understand the syntax for commands that require additional input.

### Command history : `Up`/`Down` keys
Loads previous command into the _Command Box_.
While the _Command Box_ is in focus, use the `Up` and `Down` arrow keys on your keyboard to navigate through the command history that is automatically loaded in.

--------------------------------------------------------------------------------------------------------------------

## Features

Before proceeding, do make sure that you can understand our notation for command formats:

<div markdown="block" class="alert alert-info" >

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
* Dates must be written in the `YYYY-MM-DD` format
* Command parameters (e.g. `-a`, `-m`) can be made in any order. 
  * e.g. `ls -u --module CS2103T` and `ls --module CS2103T -u` will give the same result
* If a parameter is expected only once in a command but was specified multiple times, the last occurrence of it will be taken
  * e.g. if you enter `edit 1 -d 2022-10-22 -d 2022-10-30`, this will be interpreted as `edit 1 -d 2022-10-30`
* Extraneous parameters for commands (i.e. `help`, `showarchive`, etc.) or flags (i.e. `-a`, `-u`, etc.) that do not take in parameters will be ignored
  * e.g. if you enter `showarchive 2103`, this will be interpreted as `showarchive` 
* Commands that filter for names find names that **contain** the keyword. (`ls -n`, `find`)
* Commands that filter for tags find tags that **match** the keyword. (`ls -t`, `find`)

</div>

### Getting help : `help`

Displays list of commands and information about NotionUS.

![Screenshot of Help Window](images/user-guide/HelpDemo.png)

Format: `help`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Shortcut key: <kbd>fn</kbd> + <kbd>F1</kbd> 
</div>

### Adding a task: `add`

Adds a task to the task list. However, if you try to add in a task with the same name and module as an existing task,
we will inform you that such a task already exists within the task tracker.

Format: `add -n TASK_NAME -m MODULE [-d DATE] [-t TAG_NAME]...`
* `TASK_NAME` can contain spaces
* `MODULE`: Should be alphanumeric, ie must not contain any spaces.
* `DATE`: Must be in the format of YYYY-MM-DD.
* `TAG_NAME`: The word to tag the task with, should be alphanumeric, ie must not contain any spaces.

Examples:
* `add -n Tutorial 12 -m CS2103T -d 2022-10-28 -t tutorial`

![Before image of Add Command](images/user-guide/AddCommandBefore.png)
![After image of Add Command](images/user-guide/AddCommandAfter.png)

### Marking a task as completed: `mark`

Mark a task as complete.
Note: Using `mark` on a task already marked as complete will not change its completion status.

Format: `mark TASK_NUMBER`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Example: `mark 2`

![Example image of Mark Command](images/user-guide/MarkCommandDemo.png)

### Unmarking a task: `unmark`

Unmark a task, ie mark a task as incomplete.
Note: Using `unmark` on a task that is not complete will not change its completion status.

Format: `unmark TASK_NUMBER`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Example: `unmark 2`

### Tagging a task: `tag`

Allows you to tag a task.

Format : `tag TASK_NUMBER -t TAG_NAME*`
* `TASK_NUMBER`: This is the number of the task currently displayed.
* `TAG_NAME`: The word to tag the task with, should be alphanumeric, i.e. must not contain any spaces.

Example: `tag 1 -t optional`

![Example image of Tag Command](images/user-guide/TagCommandDemo.png)

### List : `ls`

`ls` commands filter the task list. There are multiple ways to filter the task list, such as
listing all tasks, unmarked tasks, all tasks under a module name, etc. You may apply multiple list flags in one
command to filter a list down to the results you are looking for. To reset the list, use the command `ls -a`.

<div markdown="span" class="alert alert-warning">

**:exclamation: Caution:**<br>
* Any command that searches for names finds all task names that contain the keyword and does not have to be an exact match.
* It is also case-insensitive. 
* Any command that searches for tags finds all tags that exactly match, but is also case-insensitive.

</div>

Current filters applied will be shown in the UI at the top bar.

![Before image of List Command](images/user-guide/ListCommandBefore.png)
![After image of List Command](images/user-guide/ListCommandAfter.png)

**Note that `find` searches globally, across all of a task's attributes**

#### Listing all tasks : `ls -a`

Shows a list of all tasks in the task list.

Format: `ls -a`

#### Listing all unmarked tasks : `ls -u`

Shows a list of all unmarked tasks in the task list, i.e. shows a list of uncompleted tasks.

Format: `ls -u`

#### Listing all marked tasks : `ls -m`

Shows a list of all marked tasks in the task list, i.e. shows a list of completed tasks.

Format: `ls -m`

#### Listing all tasks under the same module : `ls --module`

Shows a list of all tasks under the same module.

Format: `ls --module MODULE`
* `MODULE`: Should be alphanumeric, i.e. must not contain any spaces.

Example: `ls --module cs2103t`

#### Listing all tasks containing the same tag : `ls -t`

Shows a list of tasks whose tags match **exactly** with the tag_name. (Case-insensitive)

Format: `ls -t TAG_NAME`

* `TAG_NAME`: The tag you are looking for should be an exact match and alphanumeric, ie must not contain any spaces.

Example: `ls -t highPriority` will find tags with `highpriority` (Case-insensitive)

#### Listing all tasks with deadline on or after a date : `ls -d`

Shows a list of all tasks with deadline on or after the inputted date.

Format: `ls -d DATE`
* `DATE`: Must be in the format of YYYY-MM-DD.

Example: `ls -d 2022-11-11`

#### Listing all task names with the matching keywords: `ls -n`

Shows a list of all tasks with matching names.

Format: `ls -n KEYWORD*`
* `KEYWORD`: One keyword or multiple, which should be separated by spaces.

Example: `ls -n task1`

### Find tasks by name : `find`

The `find` command finds the task names that **contain** the keywords and tags which **exactly match** with the 
keywords. Meaning that names does not have to be an exact match (Example: searching `tap` with return a task with a 
task name `tape`). `find` is not case-sensitive.

Format: `find KEYWORD/TAG_NAME*`

Singular word search example: `find tut`
finds names which contain `tut` and any tag that exactly match `tut`.

Example singular word search: ![FindCommandSingular](images/user-guide/FindCommandSingular.png)

Multiple word search example: `find Week tuT`
finds **any** task name that contain keywords `week` or `tut` or **any** tag that match them.

Example multiple word search: ![FindCommandMultiple](images/user-guide/FindCommandMultiple.png)
### Archiving data files : `archive`

Allows you to remove a task from task list and store in archived file.

Format: `archive TASK_NUMBER`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Examples:
* `archive 1`: archives first task in task list.

<div markdown="span" class="alert alert-warning">

:warning: **Warning:**

This command is irreversible!

</div>


### View Archived Tasks : `showarchive`

Displays a list of archived tasks.

![Screenshot of Archived Window](images/user-guide/ArchivedDemo.png)

Format: `showarchive`

### Editing a task : `edit`

Edits an existing task in the task list, at least one field needs to be edited. 

Format: `edit TASK_NUMBER [-n TASK_NAME] [-m MODULE] [-d DATE] [-t TAG_NAME*]`

* `TASK_NUMBER`: This is the number of the task currently displayed.

* `MODULE`: Should be alphanumeric, ie must not contain any spaces.
* (Optional)`DATE`: Must be in the format of YYYY-MM-DD.
* (Optional)`TAG_NAME`: The word to tag the task with, should be alphanumeric, ie must not contain any spaces.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>
The edit command accepts optional date and optional tags similar to the add command, where any original dates or tags
will be removed if no date or tag is given. 
</div>

Examples:
* `edit 1 -t revision -n Recitation` Edits the tag to "revision" and taskName to "Recitation".

![Example image of Edot Command](images/user-guide/EditCommandDemo.png)

### Deleting a task : `delete`

Allows user to delete a task from task list. 

Format: `delete TASK_NUMBER`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Examples: 
* `delete 3`
  * Deletes third task in the task list.
  * Remaining tasks’ `TASK_NUMBER` will be automatically updated.

![Before image of Delete Command](images/user-guide/DeleteCommandBefore.png)
![After image of Delete Command](images/user-guide/DeleteCommandAfter.png)
  
### Clearing all entries : `clear`

Clears all entries of tasks in the task list. 

Format: `clear`

### Exiting the program : `exit`

Terminates and exits the program.  

Format: `exit`

### Saving the data
NotionUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
NotionUS data are saved as a JSON file `[JAR file location]/data/notionusdata.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**<br>
If your changes to the data file makes its format invalid, NotionUS will discard all data and start with an empty data file at the next run.

</div>

### Autocomplete

When the user starts to type in the start of a command, a popup menu will appear with options to complete your input.
Use the`Up` and `Down` arrow keys to navigate through the options and <kbd>`Enter`</kbd> to fill the command box with the command
of your choice.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data in NotionUS to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NotionUS home folder.

**Q**: What is the date file fails to load into NotionUS?<br>
**A**: It is likely that your storage data file is in the wrong format. Do check the log files to see what had happened when launching NotionUS. 

**Q**: What if double-clicking fails to open the jar file?<br>
**A**: Check that the correct version of java is installed (Java 11) locally on your computer. You may open the terminal and type java -version to check. If it is the wrong version, visit this [page](https://nus-cs2103-ay2223s1.github.io/website/admin/programmingLanguages.html#programming-language) for a guide to install the correct version of java. If you have the correct version of java installed, you may try to launch the app using terminal, open terminal or command prompt and type java -jar NotionUs.jar.  

**Q**: Is internet needed for NotionUS to work?<br>
**A**: No, NotionUS functions fully without internet connection.

**Q**: What if the application crashes?<br>
**A**: Please contact us via  GitHub issues [here](https://github.com/AY2223S1-CS2103T-F12-3/tp/issues?q=is%3Aissue+is%3Aopen), do provide screenshots of the error or state the error message. We will assist you as soon as possible. 

--------------------------------------------------------------------------------------------------------------------

## Command summary

Format meanings:
* Words in `lower_case` are commands or flags to be typed as-is
* Words in `UPPER_CASE` are values of parameters to be supplied by the user
* Words in `[Square brackets]` are optional parameters
* Words that are followed by `*` are parameters that can be used multiple times including zero times

| Action                      | Format                                                                                                                                                                                                                                                                                                                                                                                                                                      | Examples                                     |
|-----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------|
| **Add** task                | `add -n TASK_NAME -m MODULE [-d YYYY-MM-DD] [-t TAG_NAME]...`                                                                                                                                                                                                                                                                                                                                                                               | `add -n Tutorial 3 -m CS2103T -d 2022-09-16` |
| **Archive** task            | `archive TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                                                                                                       |                                              |
| **Clear** all tasks         | `clear`                                                                                                                                                                                                                                                                                                                                                                                                                                     |                                              |
| **Delete** task             | `delete TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                                                                                                        | `delete 3`                                   |
| **Edit** task               | `edit TASK_NUMBER [-n NEW_NAME] [-m NEW_MODULE] [-d NEW_DEADLINE]`                                                                                                                                                                                                                                                                                                                                                                          | `edit 1 -n CS2103T ip`                       |
| **Exit** NotionUS           | `exit`                                                                                                                                                                                                                                                                                                                                                                                                                                      |                                              |
| **Find** task/tag with name | `find KEYWORD/TAG_NAME...`                                                                                                                                                                                                                                                                                                                                                                                                                  | `find Tutorial Lab`                          |
| **Help**                    | `help`                                                                                                                                                                                                                                                                                                                                                                                                                                      |                                              |
| **List** specific tasks     | `ls [-a] [-u] [-m] [--module MODULE] [-t TAG_NAME] [-d YYYY-MM-DD] [-n KEYWORD...]`<br/>`ls -a` View all tasks<br/>`ls -u` View all incomplete tasks<br/> `ls -m` View all marked tasks<br/> `ls --module MODULE` View tasks under the specific module<br/> `ls -t TAG_NAME` View tasks with a specific tag<br/> `ls -d YYYY-MM-DD` View tasks on or after a specific date <br/> `ls -n KEYWORD ...` View task names with matching keywords | `ls -u --module CS2103T`                     |
| **Mark** tasks              | `mark TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                                                                                                          | `mark 2`                                     |
| **Show Archived** tasks     | `showarchive`                                                                                                                                                                                                                                                                                                                                                                                                                               |                                              |
| **Tagging** a task          | `tag TASK_NUMBER -t TAG_NAME`                                                                                                                                                                                                                                                                                                                                                                                                               | `tag 1 -t highPriority`                      |
| **Unmark** tasks            | `unmark TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                                                                                                        | `unmark 2`                                   |
| Accessing previous commands | Use the `Up` and `Down` arrow keys                                                                                                                                                                                                                                                                                                                                                                                                              |                                              |
