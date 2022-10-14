---
layout: page
title: User Guide
---

CodeConnect is a **desktop app for managing your daily task and your contacts optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, CodeConnect can get your tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `CodeConnect.jar` from [here]().

1. Copy the file to the folder you want to use as the _home folder_.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listc`** : Lists all contacts.

   * **`add`**`add Finish homework /by 2022-12-12 /mod CS2040S` : Adds a task named `Finish homework` to the Task Manager.
   
   * **`delete`**`1` : Deletes the 1st task shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Getting help

#### Viewing help : `help`

Format: `help`

### Managing tasks

#### Adding a task: `add`

Adds a task to the task manager.

Format:
`add {task_name} /by {deadline} /mod {module_code}`

Examples:
* `add finish problem set 5 /by Week 6 Sunday /mod CS2040S`
* `add finish SEP application /by 2359 today`

#### Deleting a task: `del`

Deletes a task from the task manager list.

Format:
`del {task_index}`

Example:
`del 5`

#### Marking a task: `mark`

Marks a task as completed in the task manager list.

Format:
`mark {task_index}`

Example:
`mark 3`

#### Unmarking a task: `unmark`

Marks a task as incomplete in the task manager list.

Format:
`unmark {task_index}`

Example:
`unmark 3`

#### Searching for tasks: `find`

Find tasks whose names contain any of the given keywords, or find them by their tagged module.

Format:
`find n/ {task}` `find m/ {module}`

Examples:
* `find n/ homework` returns `Science homework`, `Math homework`
* `find m/ CS1101S` returns `Problem set 4`, `Reading assignment 2`

#### Listing all tasks : `list`

Shows a list of all tasks.

Format: `list` /  `list time`

* List displays a list of all tasks in the order of most recent task added
* List time displays a list of all tasks in the order of nearest deadline

### Managing contacts

#### Adding a contact: `addc`

Adds a contact to the contact list.

Format: `addc {name} /email {email} /hp {phone number} /mods {module1} {module2}...`

Examples:
* `addc Bob Martin /email bobbymartini@gmail.com /hp 98765432 /mods CS1101S CS1231S`
* `addc Betsy Crowe /email betsycrowe@gmail.com /hp 89985432`

#### Listing all contact : `listc`

Shows a list of all persons in the address book.

Format: `listc`

#### Deleting a contact : `delc`

Deletes the specified contact from the contact list.

Format: `delc {contact_index}`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delc 2` deletes the contact at index 2 in the contact list.

#### Editing a contact : `editc`

Edits the specified contact from the contact list.

Format: `editc {contact_index} {field prefix + field description}`

* Edits the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `editc 2 n/ John Wong` edits the name of the contact at index 2 in the contact list to `John Wong`.

#### Searching for contacts: `findc`

Finds persons whose names contain any of the given keywords, or find persons who take a particular module.

Format: 
`findc n/ {name}` `findc m/ {module}`

Examples:
* `findc n/ John` returns `john`, `John Doe`
* `findc m/ CS1231S` returns `Alex Yeoh`, `David Li`

#### Clearing all contacts : `clear`

Clears all entries from the address book.

Format: `clear`

### Finishing up

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

CodeConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CodeConnect data are saved as a JSON file `[JAR file location]/data/codeconnect.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CodeConnect will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CodeConnect home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add task** | `add {taskname} /by {deadline} /mod {module_code}` <br> e.g. `add finish problem set 5 /by Week 6 Sunday /mod CS2040S`
**Delete task** | `del {task_index}` <br> e.g. `delete 5`
**Mark task** | `mark {task_index}` <br> e.g. `mark 3`
**Unmark task** | `unmark {task_index}` <br> e.g. `unmark 3`
**Find tasks** | `find /t {task}` <br> `find /m {module}`<br> e.g., `find /t homework`, <br> `find /m CS1101S`
**List tasks** | `list` / `list time`
**Add contact** | `addc {name} /email {email} /hp {phone number} /mods {module1} {module2}...` <br> e.g., `addc Bob Martin /email bobbymartini@gmail.com /hp 98765432 /mods CS1101S CS1231S`
**Clear contacts** | `clear`
**Delete contact** | `delc {contact_index}`<br> e.g., `delete 3`
**Edit contact** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find contacts** | `findc /n {name}` <br> `findc /m {module}`<br> e.g., `findc /n John`, <br> `findc /m CS1231S`
**List contacts** | `listc`
**Help** | `help`
