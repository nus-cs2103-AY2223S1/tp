---
layout: page
title: User Guide
---

TaskBook is a **desktop app for managing contacts and tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TaskBook can get your contact and task management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `taskbook.jar` from [here](https://github.com/AY2223S1-CS2103T-T13-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TaskBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:

   * **`contact list`** : Lists all contacts.

   * **`contact add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the contact list.

   * **`contact delete`**`i/3` : Deletes the 3rd contact shown in the current contact list.
 
   * **`task list`** : Lists all tasks.

   * **`task todo`**`m/John t/Finish user guide` : Adds a todo named `Finish user guide` to the task list.

   * **`task delete`**`i/3` : Deletes the 3rd task shown in the current task list.

   * **`bye`** : Exits the app.

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `task list`, `contact list` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` [coming soon]

### Listing all Contacts : `contact list`

Shows a list of all contacts in the taskbook in alphabetical order.<br> 
[coming soon!]: Methods for specific forms of listing.

Format: `contact list`

### Listing all Tasks : `task list`

Shows a list of all tasks assigned by the user to contacts in the taskbook, and vice versa, in alphabetical order.<br>
[coming soon!]: Methods to list the tasks separately.

Format: `task list`

### Adding a contact: `contact add`

Adds a contact to the taskbook.

Format: `contact add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`

Examples:
* `contact add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `contact add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`
 
### Adding a todo: `task todo`

Adds a task of type todo into the task list.

Format:  `task todo m/ASSIGNOR d/DESCRIPTION`

Format:  `task todo o/ASSIGNEE d/DESCRIPTION`

* Adds a todo with an assignor (m) or assignee (o) and a description.

Examples:
* `task todo m/John d/Finish user guide` adds a todo called “Finish user guide” assigned by John to the user.
* `task todo o/Sam d/Finish the assignment` adds a todo called “Finish the assignment” which is assigned to Sam.

### Adding a deadline: `task deadline`

Adds a task of type deadline into the task list.

Format: `task deadline m/ASSIGNOR d/DESCRIPTION t/DATE`

Format: `task deadline o/ASSIGNEE d/DESCRIPTION t/DATE`

* Adds a deadline with an assignor (m) or assignee (o), a description and a date.

Examples:
* `task deadline m/John d/Finish user guide t/2022-12-31` adds a deadline called “Finish user guide” assigned by John to the user.
* `task deadline o/Sam d/Finish the assignment t/Jan 31 2022` adds a deadline called “Finish the assignment” which is assigned to Sam.

### Adding an event: `task event`

Adds a task of type event into the task list.

Format: `task event m/ASSIGNOR d/DESCRIPTION t/DATE`

Format: `task event o/ASSIGNEE d/DESCRIPTION t/DATE`

* Adds an event with an assignor (m) or assignee (o), a description and a date.

Examples:
* `task event m/John d/Finish user guide t/2022-12-31` adds an event called “Finish user guide” assigned by John to the user.
* `task event o/Sam d/Finish the assignment t/Jan 31 2022` adds an event called “Finish the assignment” which is assigned to Sam.
    
### Deleting a contact : `contact delete`

Deletes the specified contact from the contact list.

Format: `contact delete i/INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `contact list` followed by `contact delete i/2` deletes the 2nd contact in the contact list.

### Deleting a task : `task delete`

Deletes the specified task from the task list.

Format: `task delete i/INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `task list` followed by `task delete i/2` deletes the 2nd task in the task list.

### Sorting tasks : `task sort`

Sorts the task list based on the input flag.

Format: `task sort s/SORT`

SORT flags:
* ca: Chronologically sort tasks by date and time added
* a: Alphabetically sort task descriptions

Examples:
* `task sort s/a` will change the task list in the GUI to show a task list sorted by description alphabetical order

### Exiting the program : `bye`

Ends the application.

Format: `bye`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Editing a person : `edit` [coming soon]

### Locating persons by name: `find` [coming soon]

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

## Miscellaneous Information

### Accepted Date Formats

The following date formats are accepted:

* yyyy-MM-dd (2022-10-31)
* MMM dd yyyy (Oct 31 2022)
* MM dd yyyy (10 31 2022)
* dd MMM yyyy (31 Oct 2022)

--------------------------------------------------------------------------------------------------------------------

## FAQ
**Q**: How do I save my progress?<br>
**A**: Your progress is automatically saved after each command.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                     | Format, Examples                                                                                                                                                   |
|----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **View All Tasks**         | `task list`                                                                                                                                                        |
| **View Contacts**          | `contact list`                                                                                                                                                     |
| **Add Contact**            | `contact add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…` <br> e.g., `contact add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **Add Todo: Assignor**     | `task todo m/ASSIGNOR d/DESCRIPTION` <br> e.g., `task todo m/John d/Finish user guide`                                                                             |
| **Add Todo: Assignee**     | `task todo o/ASSIGNEE d/DESCRIPTION` <br> e.g., `task todo o/Sam d/Finish the assignment`                                                                          |
| **Add Deadline: Assignor** | `task deadline m/ASSIGNOR d/DESCRIPTION t/DATE` <br> e.g., `task deadline m/John d/Finish user guide t/2022-12-31`                                                 |
| **Add Deadline: Assignee** | `task deadline o/ASSIGNEE d/DESCRIPTION t/DATE` <br> e.g., `task deadline o/Sam d/Finish the assignment t/Jan 31 2022`                                             |
| **Add Event: Assignor**    | `task event m/ASSIGNOR d/DESCRIPTION t/DATE` <br> e.g., `task event m/John d/Finish user guide t/2022-12-31`                                                       |
| **Add Event: Assignee**    | `task event o/ASSIGNEE d/DESCRIPTION t/DATE` <br> e.g., `task event o/Sam d/Finish the assignment t/Jan 31 2022`                                                   |
| **Delete Contact**         | `contact delete i/INDEX`<br> e.g., `contact delete i/3`                                                                                                            |
| **Delete Task**            | `task delete i/INDEX`<br> e.g., `task delete i/3`                                                                                                                  |
| **Sort Tasks**             | `task sort s/SORT`<br> e.g., `task sort s/a`                                                                                                                       |
| **Quit**                   | `bye`                                                                                                                                                              |
