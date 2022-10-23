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

   * **`task todo`**`m/John d/Finish user guide` : Adds a todo with the description `Finish user guide` to the task list.

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
  e.g. `n/NAME [#TAG]` can be used as `n/John Doe #friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[#TAG]…​` can be used as ` ` (i.e. 0 times), `#friend`, `#friend #family` etc.

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

### Adding a contact : `contact add`

Adds a contact to the taskbook.

Format: `contact add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [#TAG]…`

Examples:
* `contact add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `contact add n/Betsy Crowe #friend e/betsycrowe@example.com a/Newgate Prison p/1234567 #criminal`
 
### Adding a todo : `task todo`

Adds a task of type todo into the task list.

Format:  `task todo m/ASSIGNOR d/DESCRIPTION [#TAG]…`

Format:  `task todo o/ASSIGNEE d/DESCRIPTION [#TAG]…`

* Adds a todo with an assignor (m) or assignee (o) and a description. Optional to include tags.

Examples:
* `task todo m/John d/Finish user guide` adds a todo called “Finish user guide” assigned by John to the user.
* `task todo o/Sam d/Finish the assignment #cs2103` adds a todo called “Finish the assignment” tagged as "cs2103" and assigned to Sam.

### Adding a deadline : `task deadline`

Adds a task of type deadline into the task list.

Format: `task deadline m/ASSIGNOR d/DESCRIPTION t/DATE [#TAG]…`

Format: `task deadline o/ASSIGNEE d/DESCRIPTION t/DATE [#TAG]…`

* Adds a deadline with an assignor (m) or assignee (o), a description and a date. Optional to include tags.

Examples:
* `task deadline m/John d/Finish user guide t/2022-12-31` adds a deadline called “Finish user guide” assigned by John to the user.
* `task deadline o/Sam d/Finish the assignment t/Jan 31 2022 #cs2103` adds a deadline called “Finish the assignment” and tagged as "cs2103" which is assigned to Sam.

### Adding an event : `task event`

Adds a task of type event into the task list.

Format: `task event m/ASSIGNOR d/DESCRIPTION t/DATE [#TAG]…`

Format: `task event o/ASSIGNEE d/DESCRIPTION t/DATE [#TAG]…`

* Adds an event with an assignor (m) or assignee (o), a description and a date. Optional to include tags.

Examples:
* `task event m/John d/Finish user guide t/2022-12-31` adds an event called “Finish user guide” assigned by John to the user.
* `task event o/Sam d/Finish the assignment t/Jan 31 2022 #cs2103` adds an event called “Finish the assignment” tagged as "cs2103" which is assigned to Sam.

### Editing a contact : `contact edit`

Edits the specified contact in the contact list.

Format: `contact edit i/INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [#TAG]…`

* Edits the contact at the specified `INDEX` with the parameters provided.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* **At least 1 field** must be edited.

Examples:
* `contact edit i/1 n/James Lee e/jameslee@example.com`
* `contact edit i/2 p/98765433 #friend`

### Editing a task : `task edit`

Edits the specified task in the task list.

Format: `task edit i/INDEX [m/NAME] [o/NAME] [d/DESCRIPTION] [t/DATE] [#TAG]…`

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

### Sorting tasks : `task sort`

Sorts the task list based on the input flag.

Format: `task sort s/SORT`

SORT flags:
* ca: Chronologically sort tasks by date and time added
* a: Alphabetically sort task descriptions

Examples:
* `task sort s/a` will change the task list in the GUI to show a task list sorted by description alphabetical order

### Marking a task : `task mark`

Marks the specified task from the task list as done.

Format: `task mark i/INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `task list` followed by `task mark i/2` marks the 2nd task in the task list.

### Unmarking a task : `task unmark`

Marks the specified task from the task list as not done.

Format: `task unmark i/INDEX`

* Marks the task at the specified `INDEX` as not done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `task list` followed by `task unmark i/2` marks the 2nd task in the task list.


### Exiting the program : `bye`

Ends the application.

Format: `bye`

### Navigating Command History

#### History: Previous Command

Gets the previous command, if any.

Steps:

1. Ensure that the text field is selected and in focus.

1. Press `UP` arrow key.

#### History: Next Command

Gets the next command, if any.

Steps:

1. Ensure that the text field is selected and in focus.

1. Press `DOWN` arrow key.

### Saving the data

TaskBook data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TaskBook data is saved as a JSON file `[JAR file location]/data/taskbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TaskBook will discard all data and start with an empty data file at the next run.
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
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TaskBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                   | Format, Examples                                                                                                                                                  |
|--------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **View All Tasks**       | `task list`                                                                                                                                                       |
| **View Contacts**        | `contact list`                                                                                                                                                    |
| **Add Contact**          | `contact add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [#TAG]…` <br> e.g., `contact add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **Add Todo: Assignor**   | `task todo m/ASSIGNOR d/DESCRIPTION [#TAG]…` <br> e.g., `task todo m/John d/Finish user guide #cs2103 #homework`                                                  |
| **Add Todo: Assignee**   | `task todo o/ASSIGNEE d/DESCRIPTION [#TAG]…` <br> e.g., `task todo o/Sam d/Finish the assignment #compulsory`                                                     |
| **Add Deadline: Assignor** | `task deadline m/ASSIGNOR d/DESCRIPTION t/DATE [#TAG]…` <br> e.g., `task deadline m/John d/Finish user guide t/2022-12-31 #cs2103 #homework`                      |
| **Add Deadline: Assignee** | `task deadline o/ASSIGNEE d/DESCRIPTION t/DATE [#TAG]…` <br> e.g., `task deadline o/Sam d/Finish the assignment t/Jan 31 2022 #compulsory`                        |
| **Add Event: Assignor**  | `task event m/ASSIGNOR d/DESCRIPTION t/DATE [#TAG]…` <br> e.g., `task event m/John d/Finish user guide t/2022-12-31 #cs2103 #homework`                                             |
| **Add Event: Assignee**  | `task event o/ASSIGNEE d/DESCRIPTION t/DATE [#TAG]…` <br> e.g., `task event o/Sam d/Finish the assignment t/Jan 31 2022 #compulsory`                              |
| **Edit Contact**         | `contact edit i/INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [#TAG]…`<br> e.g.,`contact edit i/2 n/James Lee e/jameslee@example.com #friend`             |
| **Edit Task**            | `task edit i/INDEX [m/NAME] [o/NAME] [d/DESCRIPTION] [t/DATE] [#TAG]…`<br> e.g.,`task edit i/2 o/James Lee #classmate`                                            |
| **Delete Contact**       | `contact delete i/INDEX`<br> e.g., `contact delete i/3`                                                                                                           |
| **Delete Task**          | `task delete i/INDEX`<br> e.g., `task delete i/3`                                                                                                                 |
| **Sort Tasks**           | `task sort s/SORT`<br> e.g., `task sort s/a`                                                                                                                      |
| **Mark Task**            | `task mark i/INDEX`<br> e.g., `task mark i/3`                                                                                                                     |
| **Unmark Task**          | `task unmark i/INDEX`<br> e.g., `task unmark i/3`                                                                                                                 |
| **Quit**                 | `bye`                                                                                                                                                             |
| **History: Previous Command** | `UP` arrow key                                                                                                                                                    |
| **History: Next Command** | `DOWN` arrow key                                                                                                                                                  |
