---
layout: page
title: User Guide
---

![NotionUS](images/NotionUS_FullLogo.png)

## Welcome to NotionUS

**NotionUS** is a lightweight but powerful application that can help you manage your assignments and tasks. It sports 
a sleek and minimalist design which allows you to clearly see your outstanding tasks at a glance.

**NotionUS** operates via a [command line interface](#glossary) which aids fast typists in managing their assignments quickly, 
while still providing the benefits of a graphical user interface.

**NotionUS** excels in its simplicity and optimisation for the university student's task management needs. Upon 
download, it supports management via deadlines and university modules. Having a gentle learning curve and requiring 
minimal setup, it is perfect for the contemporary university student.

The following is a short overview of the features and capabilities of NotionUS:
* Creating tasks with specified modules, deadlines and tags.
* Marking and unmarking a task as done.
* Filtering tasks by name, module, deadline, tags and completion status.
* Finding specific tasks by name or tag.

|   If you are   |                                         You should                                         |
|:--------------:|:------------------------------------------------------------------------------------------:|
|   a new user   | proceed to our [quick start](#quick-start) section to set up NotionUS and try its features |
| returning user |       skip to our [command summary](#command-summary) for a summary of our features!       |

**Table of Contents**

* TOC
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
   1. To check if you have Java installed, please open up a [terminal](#glossary) (search for `Command Prompt` on Windows and 
      `Terminal` on Mac OS) and type in `java -version`. This will show your java version, if you have it installed.
   2. If you do not have Java 11 or above installed, please proceed to this [link](https://www.oracle.com/java/technologies/downloads/#java11)
      and download the appropriate Java for your operating system.


2. Download the latest NotionUS.jar from [here](https://github.com/AY2223S1-CS2103T-F12-3/tp/releases/).

3. Move the file to the folder you want to use as the home folder for NotionUS. Note that a new folder will be created
   in the same location as the file, which will contain the application data.

4. Double-click the file to start the app. The app should look like the image below, and have some sample data has been pre-loaded to experiment with.

    ![Ui](images/user-guide/Ui.png)

<div style="page-break-after: always;"></div>

### Understanding the Graphical User Interface (GUI)

Understanding the [GUI](#glossary) is essential to making the most out of your experience in the app.

![GUIwalkaround](images/user-guide/GUIwalkaround.png)


From top to bottom:
1. `Help` and `Archived` Buttons:
    * Click `Help` to open the help menu which will display a list of example commands and a link to our user guide.
    * Click `Archived` to open the archived tasks window, which shows any previously archived tasks.

2. Filter status display:
    * This displays any filters applied on the list using commands such as `ls` or `find`, for more information
      please refer to the [**Features**](#command-features) section below.

3. Main task list:
    * This displays all your *current* tasks.

4. Result display:
    * The result displays any information regarding command inputs, such as success or errors.

5. Command box:
    * This is where you enter commands to control the app.

To learn more about other [GUI](#glossary) Features, click [**here**](#gui-features).

Now that you understand the [GUI](#glossary), lets try inputting a command into the app!

Try typing the command `ls --module CS2103T` in the “Enter command here...” box and press “ENTER” to execute the command.

<div markdown="span" class="alert alert-primary">:bulb: **What does this command do?**
Lists all tasks associated with the module `CS2103T`.
</div>

The [GUI](#glossary) should be updated as shown below:

![GUIwalkaround2](images/user-guide/GUIwalkaround2.png)

   Some other example commands you can try:
   - `add -n Tutorial 3 -m CS2103T -d 2022-10-10`:
     Adds a task called `Tutorial 3` for the module `CS2103T` with the deadline `2022-09-16` into the task list.
   - `mark 1` :
     Marks the first task in the list as complete.
   - `delete 2` : 
     Deletes the second task in the list.
   - `edit 3 -n Assignment 2` : 
     Changes the name of the third task in the list to `Assignment 2`.
   - `find tutorial` :
     Finds anything with the keyword 'tutorial' (not case-sensitive or strictly matched words)

   You can also continue reading the [**command features**](#command-features) section to read more about how to use each command!

Click to return to: [Quick Start](#quick-start), [Back to Top](#)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## GUI Features

#### Autocomplete

When the user starts to type in the start of a command in the _Command Box_, a pop-up menu will appear with options to complete your input.
Use the `Up` and `Down` arrow keys on your keyboard to navigate through the options and press the `Enter` key to fill the command box with the command
of your choice.

Here is an example of autocomplete in action:

![Autocomplete](images/user-guide/Autocomplete.png)

However, it is recommended to read through the [Features](#command-features) section to understand the syntax for commands that require additional input.

<div style="page-break-after: always;"></div>

#### Returning to a previous command : `Up`/`Down` keys

Loads previous command into the _Command Box_.
While the _Command Box_ is in focus, use the `Up` and `Down` arrow keys on your keyboard to navigate through the command history that is automatically loaded in.

Click to return to: [GUI Features](#gui-features), [Back to Top](#)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command Features

You can jump to the following sections:

* [Command Structure](#command-structure)
* [Getting Help](#getting-help--help)
* [Task Management](#task-management) - For actions like adding, editing and deleting tasks
* [Viewing Specific Tasks](#viewing-specific-tasks)
* [Archive Management](#archive-management)
* [Exiting The Program](#exiting-the-program--exit)

Click to return to: [Back to Top](#)

### Command Structure

In this section, we will be explaining our commands. Before proceeding, we will need to explain how our commands are
structured!

As seen in the image below, a command begins with a command word. If the command requires more information, it can be 
provided through flags and parameters. All flags begin with a hyphen, and any information for that flag is provided 
directly after, as a parameter.

![Command Structure](images/user-guide/AnnotatedCommand.png)

As you are reading through this section, you will find that each command will have a "Format" section. This is the text
that you need to enter into the command box in order to execute that command. To understand our format notation, read
on!

<div markdown="block" class="alert alert-info" >

**:information_source: Notes about the command format:**

* Words in `lower_case` are commands or flags to be typed as-is
* Words in `UPPER_CASE` are values of parameters to be supplied by the user
* Words in `[Square brackets]` are optional parameters
* Words that are followed by `*` are parameters that can be used multiple times

Add command format: `add -n TASK_NAME -m MODULE [-d DATE] [-t TAG_NAME]*`

Using the add command format above as an example, the following are a few good and bad examples of what a command can be:

* Since square brackets are optional, `add -n Project -m GEA1000` is valid
* Since there is a `*`, we can put multiple tags at one, e.g. `add -n Project -m GEA1000 -t Optional -t Group`
* Modules **are not** surrounded by square brackets, and so they are compulsory. Hence, `add -n Project` is invalid
* `add`, `-n` and `-m` are in lower case, and so must be kept that way. Thus, `ADD -N Project -M GEA1000` is invalid

And a few more notes about command structure,

* Command flags (e.g. `-a`, `-m`) can be made in any order
* If a parameter is expected only once in a command but was specified multiple times, the last occurrence of it will be taken
* Irrelevant flags and parameters provided to commands will simply be ignored

For example, `add -m GEA1000 -m CS2100 -p -n Project` will be interpreted as `add -n Project -m CS2100`, since

* Flag order does not matter
* Last occurrence of the duplicate flag `-m` is taken
* The `-p` flag is not expected by the add command and is thus ignored

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Additional information about task constraints:**

* Task names can only contain letters, numbers and spaces. For example, `Assignment-5` is not a valid name as it
  contains a hyphen.
* Dates must be written in the format YYYY-MM-DD, eg `2022-10-30`.
* Module names and tags can only contain letters and numbers, no spaces allowed.

</div>

<div style="page-break-after: always;"></div>

### Getting help : `help`

Displays list of commands and information about NotionUS.

![Screenshot of Help Window](images/user-guide/HelpDemo.png)

Format: `help`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Shortcut key: <kbd>F1</kbd> 
</div>

Click to return to: [Command Features](#command-features), [Back to Top](#)

<div style="page-break-after: always;"></div>

### Task Management

We have the following commands in this section:

* [Adding a task](#adding-a-task-add)
* [Editing a task](#editing-a-task--edit)
* [Deleting a task](#deleting-a-task--delete)
* [Marking a task as complete](#marking-a-task-as-complete-mark)
* [Unmarking a task](#unmarking-a-task-unmark)
* [Tagging a task](#tagging-a-task-tag)
* [Clearing all entries](#clearing-all-entries--clear)

Click to return to: [Command Features](#command-features), [Back to Top](#)

#### Adding a task: `add`

Adds a task to the task list.

Format: `add -n TASK_NAME -m MODULE [-d DATE] [-t TAG_NAME]*`

**Reminder**: Params in `[]` are **optional**, and `-t TAG_NAME` can be used multiple times.

<div markdown="block" class="alert alert-info">
**:information_source: Notes about usage of the add command:**
* **Duplicate detection** - If you try to add in a task with the same name and module as an existing task,
  we will inform you that such a task already exists within the task list and reject your command
* **Optional deadlines** - Tasks with no deadlines are treated as being due "far in the future", meaning they
  will be placed at the end of the task list. You can have a task with no deadline by either not providing the `-d`
  flag, or providing the `-d` flag with no parameter.
* **Ordering of tasks** - Tasks are first ordered by their deadlines, followed by the module name and then the task
  name, in alphabetical order.
</div>


Examples:
* `add -n Task 1 -m CS2103T -d 2022-10-15 -t homework`
* `add -n Tutorial 3 -m CS2103T`
* `add -n Tutorial 12 -m CS2103T -d 2022-10-28 -t tutorial`

![Before image of Add Command](images/user-guide/AddCommandBefore.png)
![After image of Add Command](images/user-guide/AddCommandAfter.png)

#### Editing a task : `edit`

Edits an existing task in the task list, at least one field needs to be edited.

Format: `edit TASK_NUMBER [-n TASK_NAME] [-m MODULE] [-d DATE] [-t TAG_NAME]*`

**Reminder**: Parameters in `[]` are **optional**, and `-t TAG_NAME` can be used multiple times.

<div markdown="block" class="alert alert-info">
**:information_source: Notes about usage of the edit command:**
* **Duplicate detection** - If you try to edit the task such that it will have the same name and module as another task,
  we will inform you that such a task already exists within the task list and reject your command
* **Deadline removal** - Run the edit command with `-d` without specifying a date, eg `edit 1 -d`.
* **Tags deletion** - Similar to above, run the edit command with `-t` without specifying a tag, eg `edit 3 -t`
</div>

Examples:
* `edit 1 -t revision -n Recitation` Edits the tag to "revision" and taskName to "Recitation".

![Example image of Edit Command](images/user-guide/EditCommandDemo.png)

#### Deleting a task : `delete`

Allows user to delete a task from task list.

Format: `delete TASK_NUMBER`

Examples:
* `delete 3`
    * Deletes third task in the task list.
    * Remaining tasks’ `TASK_NUMBER` will be automatically updated.

![Before image of Delete Command](images/user-guide/DeleteCommandBefore.png)
![After image of Delete Command](images/user-guide/DeleteCommandAfter.png)

<div style="page-break-after: always;"></div>

#### Marking a task as complete: `mark`

Mark a task as complete.

Note: Using `mark` on a task already marked as complete will not change its completion status.

Format: `mark TASK_NUMBER`

Example: `mark 2`

![Example image of Mark Command](images/user-guide/MarkCommandDemo.png)

#### Unmarking a task: `unmark`

Unmark a task, i.e. mark a task as incomplete.

Note: Using `unmark` on a task that is not complete will not change its completion status.

Format: `unmark TASK_NUMBER`

Example: `unmark 2`

<div style="page-break-after: always;"></div>

#### Tagging a task: `tag`

Allows you to tag a task.

Format : `tag TASK_NUMBER -t TAG_NAME [-t TAG_NAME]*`
* `TASK_NUMBER`: This is the number of the task currently displayed.
* `TAG_NAME`: The word to tag the task with, should be [alphanumeric](#glossary), i.e. must not contain any spaces.
* `[-t TAG_NAME]*`: You may add more than one tag by adding `-t TAG_NAME` after your first tag.

Example: `tag 1 -t optional`

![Example image of Tag Command](images/user-guide/TagCommandDemo.png)

#### Clearing all entries : `clear`

Clears all entries of tasks in the task list.

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
Clear command does not clear archived task list.  
</div>

Format: `clear`

<div markdown="span" class="alert alert-warning">

:warning: **Warning:**

This command is irreversible!

</div>

<div style="page-break-after: always;"></div>

### Viewing Specific Tasks

`ls` commands filter the task list. There are multiple ways to filter the task list, such as
listing all tasks, unmarked tasks, all tasks under a module name, etc. You may apply multiple list flags in one
command to filter a list down to the results you are looking for. To reset the list, use the command `ls -a`.

Current filters applied will be shown in the UI at the top bar.

![Before image of List Command](images/user-guide/ListCommandBefore.png)


![After image of List Command](images/user-guide/ListCommandAfter.png)

We have the following commands in this section:

* [Listing all tasks](#listing-all-tasks--ls--a)
* [Listing all unmarked tasks](#listing-all-unmarked-tasks--ls--u)
* [Listing all marked tasks](#listing-all-marked-tasks--ls--m)
* [Listing all tasks under the same module](#listing-all-tasks-under-the-same-module--ls---module)
* [Listing all tasks containing the same tag](#listing-all-tasks-containing-the-same-tag--ls--t)
* [Listing all tasks with deadline on or after a date](#listing-all-tasks-with-deadline-on-or-after-a-date--ls--d)
* [Listing all task names with the matching keywords](#listing-all-task-names-with-the-matching-keywords-ls--n)

Click to return to: [Command Features](#command-features), [Back to Top](#)

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
* `MODULE`: Should be [alphanumeric](#glossary), i.e. must not contain any spaces.

Example: `ls --module cs2103t`

#### Listing all tasks containing the same tag : `ls -t`

Shows a list of tasks whose tags match **exactly** with the tag_name. (Case-insensitive)

Format: `ls -t TAG_NAME`

* `TAG_NAME`: The tag you are looking for should be an exact match and [alphanumeric](#glossary), ie must not contain any spaces.

Example: `ls -t highPriority` will find tags with `highpriority` (Case-insensitive)

<div markdown="block" class="alert alert-info">

**:information_source: Tip:**<br>
Any command that searches for tags finds all tags that exactly match, but is case-insensitive.
</div>

#### Listing all tasks with deadline on or after a date : `ls -d`

Shows a list of all tasks with deadline on or after the inputted date.

As tasks with no deadline can be considered to be due "far in the future", this command will always list tasks with no
deadlines.

Format: `ls -d DATE`
* `DATE`: Must be in the format of YYYY-MM-DD.

Example: `ls -d 2022-11-11`

#### Listing all task names with the matching keywords: `ls -n`

Shows a list of all tasks with matching names.

Format: `ls -n KEYWORD*`
* `KEYWORD`: One or more keywords can be provided. Each keyword should consist of only letters and numbers, and are separated by spaces.

Example: `ls -n task1`

<div markdown="block" class="alert alert-info">
**:information_source: Tip:**<br>
Any command that searches for names finds all task names that contain the keyword and does not have to be an exact match. It is also case-insensitive.
</div>

#### Find task names or tags: `find`

The `find` command finds the task names that **contain** the keywords and tags which **exactly match** with the 
keywords. Meaning that names does not have to be an exact match (Example: searching `tap` with return a task with a 
task name `tape`). `find` is not case-sensitive.

Format: `find KEYWORD/TAG_NAME*`
* `KEYWORD/TAG_NAME`: One keyword/tag name or multiple, which should be [alphanumeric](#glossary) and separated by spaces.

Singular word search example: `find tut`
finds names which contain `tut` and any tag that exactly match `tut`.

Example singular word search: ![FindCommandSingular](images/user-guide/FindCommandSingular.png)

Multiple word search example: `find Week tut`
finds **any** task name that contain keywords `week` or `tut` or **any** tag that match them.
<br>(Note that it is not limited to only 2 keywords)

Example multiple word search: ![FindCommandMultiple](images/user-guide/FindCommandMultiple.png)

### Archive Management

We have the following commands in this section:

* [Archiving tasks](#archiving-tasks--archive)
* [View archived tasks](#view-archived-tasks--showarchive)

Click to return to: [Command Features](#command-features), [Back to Top](#)

#### Archiving tasks : `archive`

Allows you to remove a task from task list and store in archived file.

Format: `archive TASK_NUMBER`
* `TASK_NUMBER`: This is the number of the task currently displayed.

Examples:
* `archive 1`: archives first task in task list.

<div markdown="span" class="alert alert-warning">

:warning: **Warning:**

This command is irreversible!

</div>

<div style="page-break-after: always;"></div>

#### View archived tasks : `showarchive`

Displays a list of archived tasks.

![Screenshot of Archived Window](images/user-guide/ArchivedDemo.png)

Format: `showarchive`

### Exiting the program : `exit`

Terminates and exits the program.  

Format: `exit`

Click to return to: [Command Features](#command-features), [Back to Top](#)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Other Features

### Saving the data
NotionUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
NotionUS data are saved as a JSON file `[JAR file location]/data/taskList.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">
If your changes to the data file makes its format invalid, NotionUS will discard all data and start with an empty data file at the next run.
</div>

Click to return to: [Back to Top](#)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data in NotionUS to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NotionUS home folder.

**Q**: What if double-clicking fails to open the jar file?<br>
**A**: Check that the correct version of java is installed (Java 11) locally on your computer. You may open the [terminal](#glossary) and type java -version to check. If it is the wrong version, visit this [page](https://nus-cs2103-ay2223s1.github.io/website/admin/programmingLanguages.html#programming-language) for a guide to install the correct version of java. If you have the correct version of java installed, you may try to launch the app using [terminal](#glossary), open terminal or command prompt and type java -jar NotionUs.jar.  

**Q**: Is internet needed for NotionUS to work?<br>
**A**: No, NotionUS functions fully without internet connection.

**Q**: I need help! How can I contact the team?<br>
**A**: You may contact us via GitHub issues [here](https://github.com/AY2223S1-CS2103T-F12-3/tp/issues?q=is%3Aissue+is%3Aopen). If possible, do provide screenshots demonstrating the problem you are facing. We will assist you as soon as possible! 

Click to return to: [Back to Top](#)


--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command summary

Format meanings:
* Words in `lower_case` are commands or flags to be typed as-is
* Words in `UPPER_CASE` are values of parameters to be supplied by the user
* Words in `[Square brackets]` are optional parameters (Note:`ls` can stack filters)
* Words that are followed by `*` are parameters that can be used multiple times

| Action                      | Format                                                                                                                                                                                                                                                                                                                                                                                                                                 | Examples                                     |
|-----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------|
| **Add** task                | `add -n TASK_NAME -m MODULE [-d YYYY-MM-DD] [-t TAG_NAME]*`                                                                                                                                                                                                                                                                                                                                                                            | `add -n Tutorial 3 -m CS2103T -d 2022-09-16` |
| **Archive** task            | `archive TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                                                                                                  | `archive 1`                                  |
| **Clear** all tasks         | `clear`                                                                                                                                                                                                                                                                                                                                                                                                                                |                                              |
| **Delete** task             | `delete TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                                                                                                   | `delete 3`                                   |
| **Edit** task               | `edit TASK_NUMBER [-n NEW_NAME] [-m NEW_MODULE] [-d NEW_DEADLINE]`                                                                                                                                                                                                                                                                                                                                                                     | `edit 1 -n CS2103T ip`                       |
| **Exit** NotionUS           | `exit`                                                                                                                                                                                                                                                                                                                                                                                                                                 |                                              |
| **Find** task/tag with name | `find KEYWORD/TAG_NAME*`                                                                                                                                                                                                                                                                                                                                                                                                               | `find Tutorial Lab`                          |
| **Help**                    | `help`                                                                                                                                                                                                                                                                                                                                                                                                                                 |                                              |
| **List** specific tasks     | `ls [-a] [-u] [-m] [--module MODULE] [-t TAG_NAME] [-d YYYY-MM-DD] [-n KEYWORD*]`<br/>`ls -a` View all tasks<br/>`ls -u` View all incomplete tasks<br/> `ls -m` View all marked tasks<br/> `ls --module MODULE` View tasks under the specific module<br/> `ls -t TAG_NAME` View tasks with a specific tag<br/> `ls -d YYYY-MM-DD` View tasks on or after a specific date <br/> `ls -n KEYWORD*` View task names with matching keywords | `ls -n task test --module CS2103T `          |
| **Mark** tasks              | `mark TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                                                                                                     | `mark 2`                                     |
| **Show Archived** tasks     | `showarchive`                                                                                                                                                                                                                                                                                                                                                                                                                          |                                              |
| **Tagging** a task          | `tag TASK_NUMBER -t TAG_NAME [-t TAG_NAME]*`                                                                                                                                                                                                                                                                                                                                                                                           | `tag 1 -t highPriority -t homework`          |
| **Unmark** tasks            | `unmark TASK_NUMBER`                                                                                                                                                                                                                                                                                                                                                                                                                   | `unmark 2`                                   |
| Accessing previous commands | Use the `Up` and `Down` arrow keys                                                                                                                                                                                                                                                                                                                                                                                                     |                                              |

Click to return to: [Back to Top](#)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Glossary

| Word                               | Definition                                                                 |
|------------------------------------|----------------------------------------------------------------------------|
| **Command Line Interface (CLI)**   | A program that mainly uses typed commands to interact with the app.        |
| **Graphical User Interface (GUI)** | What the user sees when interacting with the app.                          |
| **Alphanumeric**                   | A combination of both letters and numbers without space.                   |
| **Terminal**                       | A text-based interface for the computer.                                   |

Click to return to: [Back to Top](#)
