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

   * **`contact delete`**`3` : Deletes the 3rd contact shown in the current contact list.
 
   * **`task list`** : Lists all tasks.

   * **`task add`**`m/John t/Finish user guide` : Adds a task named `Finish user guide` to the task list.

   * **`task delete`**`3` : Deletes the 3rd task shown in the current task list.

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

Shows a list of all contacts in the address book in alphabetical order.<br> 
[coming soon!]: Methods for specific forms of listing.

Format: `contact list`

### Listing all Tasks : `task list`

Shows a list of all tasks assigned by the user to contacts in the addressbook, and vice versa, in alphabetical order.<br>
[coming soon!]: Methods to list the tasks separately.

Format: `task list`

### Adding a contact : `contact add`

Adds a contact to the taskbook.

Format: `contact add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`

Examples:
* `contact add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `contact add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`
 
### Adding a task : `task add`

Adds a task into the task list.

Format:  `task add m/ASSIGNOR d/DESCRIPTION [MODIFIER]`

Format:  `task add o/ASSIGNEE d/DESCRIPTION [MODIFIER]`

* Adds a task with an assignor (m) or assignee (o), a description and an optional task modifier.

Examples:
* `task add m/John d/Finish user guide` adds a task called “Finish user guide” assigned by John to the user.
* `task add o/Sam d/Finish the assignment` adds a task called “Finish the assignment” which is assigned to Sam.

### Task Modifiers [coming soon]

#### Adding a deadline: `task add ... t/DUE_DATE` [coming soon]

Adds a task of type Deadline into the task list.

Format: `task add m/ASSIGNOR d/DESCRIPTION t/DUE_DATE`

Format: `task add o/ASSIGNEE d/DESCRIPTION t/DUE_DATE`

* `DUE_DATE` supports the following Date formats:
    * `yyyy-MM-dd` e.g. "2022-09-21"
    * `dd-MM-yyyy` e.g. "09-21-2022"
    * `MMM dd yyyy` e.g. "Sep 21 2022"

Examples:
* `task add m/John d/Finish user guide t/ 2022-09-21` adds a task called “Finish user guide”
  which is assigned by John to the user, and due by 2022-09-21.
* `task add o/Sam d/Finish the assignment t/ 09-21-2022` adds a task called “Finish the assignment”
  which the user has assigned to Sam, and due by 2022-09-21.

### Editing a contact : `contact edit`

Edits the specified contact in the contact list.

Format: `contact edit i/INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…`

* Edits the contact at the specified `INDEX` with the parameters provided.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* **At least 1 field** must be edited.

Examples:
* `contact edit i/1 n/James Lee e/jameslee@example.com`
* `contact edit i/2 p/98765433`

### Editing a task : `task edit`

Edits the specified task in the task list.

Format: `task edit i/INDEX [m/NAME] [o/NAME] [d/DESCRIPTION] [t/DATE]`

* Edits the contact at the specified `INDEX` with the parameters provided.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* **At least 1 field** must be edited.
* Only one of assignor `m/` or assignee `o/` can be specified.
  * If the task currently has an **assignor** of "Person X", it can be changed to have an **assignee** of "Person Y" by providing parameter `o/Person Y`.
  * If the task currently has an **assignee** of "Person Y", it can be changed to have an **assignor** of "Person X" by providing parameter `m/Person X`.
  * If neither `m/` or `o/` is specified, the current **assignor** or **assignee** will not be changed.

Examples:
* `task edit i/1 m/Jackie Chan d/Practice kick 10000 times`
* `task edit i/2 o/James Lee`
* `task edit i/3 t/2023-12-31`

### Deleting a contact : `contact delete`

Deletes the specified contact from the contact list.

Format: `contact delete i/INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* Deletion of contact is blocked if the contact has tasks associated to it.

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

| Action                 | Format, Examples                                                                                                                                                   |
|------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **View All Tasks**     | `task list`                                                                                                                                                        |
| **View Contacts**      | `contact list`                                                                                                                                                     |
| **Add Contact**        | `contact add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…` <br> e.g., `contact add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **Add Task: Assignor** | `task add m/ASSIGNOR d/DESCRIPTION` <br> e.g., `task add m/John d/Finish user guide`                                                                               |
| **Add Task: Assignee** | `task add o/ASSIGNEE d/DESCRIPTION` <br> e.g., `task add o/Sam d/Finish the assignment`                                                                            |
| **Edit Contact**       | `contact edit i/INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…`<br> e.g.,`contact edit i/2 n/James Lee e/jameslee@example.com`                     |
| **Edit Task**          | `task edit i/INDEX [m/NAME] [o/NAME] [d/DESCRIPTION] [t/DATE]`<br> e.g.,`task edit i/2 o/James Lee`                                                                |
| **Delete Contact**     | `contact delete i/INDEX`<br> e.g., `contact delete i/3`                                                                                                            |
| **Delete Task**        | `task delete i/INDEX`<br> e.g., `task delete i/3`                                                                                                                  |
| **Quit**               | `bye`                                                                                                                                                              |
