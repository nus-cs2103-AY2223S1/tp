---
layout: page
title: User Guide
---

Arrow will help software project managers keep track of their members’ tasks and deliverables. By associating tasks to specific team members, users can keep track of what needs to be done and have quick access to contact information should they wish to reach out to the member.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `arrow.jar` from [here](https://github.com/AY2223S1-CS2103T-T08-2/tp).

3. Copy the file to the folder you want to use as the _home folder_ for Arrow.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`task add`** `Task 1` : Adds a task titled `Task 1` to the Task Panel.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`


### Adding a task: `task add`

Adds a task to the task panel.

Format: `task add DESC`

* DESC refers to the description of the task.
* DESC cannot be empty.

Examples:
* `task add Task 1`
* `task add Another task`


### Viewing tasks of a person: `tasks`

Lists all the task(s) that have been assigned to the specified person.

Format: `tasks INDEX`
* Views the tasks of the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `tasks 2` shows the task assigned to the 2nd person in the address book.
* `find Betsy` followed by `tasks 1` shows the tasks assigned to the 1st person in the result of the `find` command.


### Marking tasks of a person: `task mark`

Marks a task of a person as complete

Format: `task mark TASK_INDEX`
* Marks the task at the specified TASK_INDEX in `task list`.

Examples:
* `task list` followed by `task mark 1` marks the 1st task in the task list as complete.

### Unmarking tasks of a person: `task unmark`

Marks a task of a person as incomplete

Format: `task unmark TASK_INDEX`
* Unmarks the task at the specified TASK_INDEX in `task list`.

Examples:
* `task list` followed by `task unmark 1` marks the 1st task in the task list as incomplete.

### Deleting tasks from Task Panel: `task delete`

Deletes the specified task from overall tasks list.

Format: `task delete TASK_INDEX`
* Deletes the task at the specified TASK_INDEX from `task list`.

Examples:
* `task list` followed by `task delete 1` delete the 1st task from task list.

### Setting deadlines to a task: `task do ... by/`

You can use the `task do ... by/` command to set (or remove) a deadline for some task.

Format: `task do TASK_INDEX [by/DATE]...`
- The `task do` command sets the deadline specified by `DATE` to the task at the specified `TASK_INDEX` from the task list.
- You can use plain English to describe the intended deadline, such as `today`, `tomorrow`, `next Thursday`, `14 November`, and so on.
- If the application is unable to determine a date from your input, an error message will be displayed and you will be prompted to try a different input.
- To **remove** the deadline from a task, you can use the special character `?`.

Examples:
* `task do 1 by/tomorrow` sets the deadline for the 1st task in the list to tomorrow.
* `task do 1 by/?` **removes** the deadline from the 1st task in the list.

### Listing all tasks : `task list`

You can use the `task list` command to focus only on tasks that match your specified filter requirements.

Format: `task list [ti/KEYWORD] [c/PERSON_INDEX]...`
- The `task list` command accepts **optional** parameters that can filter tasks by their title or assigned contacts.
- If you do not specify any filters (i.e. `task list`), the command returns **all** tasks. You may find this useful to reset the task list after performing some filtering.

Examples:
* `task list ti/fix` filters the task list to only display tasks that contain the keyword `fix`.
* `task list c/1 c/2` filters the task list to only display tasks that are assigned to **both** the 1st and 2nd persons from the address book.
* `task list ti/fix c/1 c/2` filters the task list to only display tasks that contain the keyword `fix` **and** that are assigned to **both** the 1st and 2nd persons from the address book.

### Assigning contacts to a task: `task assign`

Assigns or unassigns the specified persons from address book to a task from task panel.

Format: `task assign TASK_INDEX [ca/PERSON_INDEX]…​ [ca/PERSON_NAME]…​ [cd/PERSON_INDEX]…​ [cd/PERSON_NAME]…​`
* ca/: Assigns the persons at the specified PERSON_INDEXs, or with the PERSON_NAME, from `address book` to task at the specified TASK_INDEX from `task list`.
* cd/: Unassigns the persons at the specified PERSON_INDEXs, or with the PERSON_NAME, from `address book` from task at the specified TASK_INDEX from `task list`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of assigned contacts (including 0)
</div>

Examples:
* `task assign 1 ca/1 ca/Alex Yeoh cd/Bernice Yu` assigns the 1st person and "Alex Yeoh" from the address book to the 1st task from task list, and unassigns "Bernice Yu" from the same task.


### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Editing a task: `task edit`

Edits an existing task in the task list.

Format: `task edit INDEX [ti/TITLE]`

* Edits the task at the specified `INDEX`.  The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* Replacement for previous title should be inputted.

Examples:
* `task edit 1 ti/go back home` Edits the title to `go back home`

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Arrow data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Arrow data are saved as a JSON file `[JAR file location]/data/arrow.json`. Advanced users are welcome to update data directly by editing that data file.

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

| Action          | Format, Examples                                                                                                                                                      |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Task add**    | `task add DESC`<br> e.g., `task add Title of task`                                                                                                                    |
| **Tasks**       | `tasks INDEX`<br> e.g., `tasks 2`                                                                                                                                     |
| **Task mark**   | `task mark TASK_INDEX`<br> e.g., `task mark 1`                                                                                                                        |
| **Task unmark** | `task unmark TASK_INDEX`<br> e.g., `task unmark 1`                                                                                                                    |
| **Task delete** | `task delete TASK_INDEX`<br> e.g., `task delete 2`                                                                                                                    |
| **Task assign** | `task assign TASK_INDEX [ca/PERSON_INDEX]…​ [ca/PERSON_NAME]…​ [cd/PERSON_INDEX]…​ [cd/PERSON_NAME]…​`<br> e.g., `task assign 3 ca/1 ca/Alex Yeoh cd/2`                                                                                         |
| **Clear**       | `clear`                                                                                                                                                               |
| **Delete**      | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**        | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**        | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**        | `list`                                                                                                                                                                |
| **Help**        | `help`                                                                                                                                                                |
