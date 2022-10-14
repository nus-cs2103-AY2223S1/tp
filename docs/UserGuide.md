---
layout: page
title: User Guide
---

YellowBook is a **desktop application for university students** who are involved in many projects to organize their project contacts and tasks.
YellowBook is optimised for use via a Command Line Interface (CLI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `yellowbook.jar` from [here]().

1. Copy the file to the folder you want to use as the _home folder_ for your YellowBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listC`** : Lists all contacts.

   * **`addC`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to YellowBook.

   * **`deleteC`**`3` : Deletes the 3rd contact shown in the current contact list.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/LABEL]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/LABEL]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listC`, `listT`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## Section 1: Contacts

### Add a contact: `addC`

Adds a contact to the address book.

Format:  `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]`

* The remark of a contact is optional.

Examples: 

* `addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

* `addC n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 r/Weird person.`

### Listing all contacts: `listC`

Shows all contacts stored in the address book.

Format: `listC`

### Deleting a contact: `deleteC`

Removes the specified contact and all its associated information from the address book.

Format: `deleteC INDEX`

* Index of a contact is its index number on the contact list.

* INDEX must be a positive integer more than 0.

Examples:

* `listC` followed by `deleteC 1` deletes the first contact in the address book.

* `findC John` followed by `deleteC 1` deletes the first result of the `findC` command.

## Editing a contact: `editC`

Edits the information fields (e.g. name, mobile number, email address) of an existing contact in the address book.

Format: `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]`

* Index of a contact is its index number on the contact list.

* INDEX must be a positive integer more than 0.

* At least one of the optional fields must be provided.

* Input values will replace existing values.

Example:

* `editC 1 n/John p/12345678` edits the first contact’s name to be John and phone number to be 12345678.

### Locating contacts by name: `findC`

Finds contacts whose names contain any of the given keywords.

Format: `findC KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive, e.g. `dr. doofenshmirtz` will match `Dr. Doofenshmirtz`.

* The order of the keywords does not matter, e.e. `doofenshmirtz dr.` will match `Dr. Doofenshmirtz`.

* Only the name is searched.

* Only full words will be matched. e.g. `Doofenshmirt` will not match `Doofenshmirtz`.

* Contacts matching at least one keyword will be returned. e.g. `Perry Dr.`
  will match `Perry the Platypus` and `Dr. Doofenshmirtz`.

Example:

* `findC flynn` will return `Candace Flynn` and `Phineas Flynn`.
* `findC Phineas Ferb` will return `Phineas Flynn` and  `Ferb Flynn`.

### Filtering contacts by label: `filterC`

Filters contacts whose labels contain any of the given keywords.
Filters applied consecutively are stacked.

Format: `filterC KEYWORD [MORE_KEYWORDS]`

* The filer is case-insensitive, e.g. `cs2103t` will match `CS2103T`.

* The order of the keywords does not matter, e.e. `software cs2103t` will match
  `CS2103T Software Engineering`.

* Only the contact's label is filtered.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Labels matching at least one keyword will be returned. e.g. `cs2103t cs2101` will match
  `CS2103T Software Engineering` and `CS2101 Effective Communication for Computing Professionals`.

Example:

* `filterC cs2103t` will return contacts with label `CS2103T Software Engineering`.

## Section 2: Tasks
### Listing all tasks: `listT`

Shows all tasks stored in the task list.

Format: `listT`

### Adding Tasks: `addT`

Adds a task to the task list. 
The deadline of the task is allowed to be empty. Newly added tasks are marked as not done. 
Throws an exception if the description of the task is empty. 
Throws an exception if the deadline of the task is not in dd-mm-yyyy format.

Format: `addT d/DESCRIPTION D/DEADLINE`

Example:

* `addT d/buy milk D/12-09-2022` will add the task "buy milk" with deadline 12 September 2022.

### Removing Tasks: `deleteT`

Removes the specified task from the task list. Throws an exception if task does not exist.

Format: `deleteT i/INDEX`

Example:

* `deleteT i/12` will delete the 12th task in the task list.

### Marking task as done: `markT`

Marks a task in the task list as done.

Format: `markT INDEX`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

Examples:

* `listT` followed by `markT 1` marks the first task in the displayed task list as done.

* `findT book` followed by `markT 1` marks the first result of the `findT` command as done.

### Marking task as undone: `unmarkT`

Marks a task in the task list as undone.

Format: `unmarkT INDEX`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

Examples:

* `listT` followed by `unmarkT 1` marks the first task in the displayed task list as undone.
* `findT book` followed by `unmarkT 1` marks the first result of the `findT` command as undone.

### Locating tasks by name: `findT`

Finds labels whose names contain any of the given keywords.

Format: `findT KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive, e.g. `cs2103t` will match `CS2103T`.

* The order of the keywords does not matter, e.e. `software cs2103t` will match
  `CS2103T Software Engineering`.

* Only the task's name is searched.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Labels matching at least one keyword will be returned. e.g. `cs2103t cs2101` will match
  `CS2103T Software Engineering` and `CS2101 Effective Communication for Computing Professionals`.

Example:

* `findT cs2103t` will return tasks with label `CS2103T Software Engineering`.

### Filtering tasks by label: `filterT`

Filters tasks whose labels contain any of the given keywords.
Filters applied consecutively are stacked.

Format: `filterT KEYWORD [MORE_KEYWORDS]`

* The filter is case-insensitive, e.g. `cs2103t` will match `CS2103T`.

* The order of the keywords does not matter, e.e. `software cs2103t` will match
  `CS2103T Software Engineering`.

* Only the tasks's label is filtered.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Labels matching at least one keyword will be returned. e.g. `cs2103t cs2101` will match
  `CS2103T Software Engineering` and `CS2101 Effective Communication for Computing Professionals`.

Example:

* `filterT cs2103t` will return tasks with label `CS2103T Software Engineering`.

## Section 3: Labels
### Listing all labels: `listL`

Shows a list of all existing labels in the address book.

Format: `listL`

### Adding a label to a contact: `addL`

Adds a label to an existing contact in the address book. Each contact can have multiple labels. If there is no existing label with the same name, label is added to the label list. Throws an exception if contact does not exist.

Format: `addL c/INDEX l/label_NAME`

Example:

* `addL c/12 l/CS2103T` will add the label "CS2103T" to the 12th contact on the contact list.

### Removing a label from a contact: `deleteL`

Removes a label from an existing contact in the address book. If contact is last remaining person with said label, label is removed from the label list. Throws an exception if contact or label does not exist.

Format: `deleteL c/INDEX l/label_NAME`

Example:

* `deleteL c/14 l/CS2101` will remove the label "CS2101" from the 14th contact on the contact list.

### Adding a label to a task: `addL`

Adds a label to an existing task in the address book. Each task can have multiple labels. If there is no existing label with the same name, label is added to the label list. Throws an exception if task does not exist.

Format: `addL t/INDEX l/label_NAME`

Example:

* `addL t/12 l/CS2103T` will add the label "CS2103T" to the 12th task on the task list.

### Removing a label from a task: `deleteL`

Removes a label from an existing task in the address book. If contact is last remaining person with said label, label is removed from the label list. Throws an exception if task or label does not exist.

Format: `deleteL t/INDEX l/label_NAME`

Example: 

* `deleteL t/14 l/CS2101` will remove the label "CS2101" from the 14th task on the task list.

## Automatic tab switching

Depending on the command you enter, you will see the open tab in the GUI switch automatically. 
For example, when using a task-related command, the tab switches to "Task" and the task list is displayed. 

The result of the entered command is displayed.
For example, after adding a new contact, the list shown on the GUI is the updated list with your new contact included.

## YellowBook data
### Saving the data

YellowBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

YellowBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, YellowBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous YellowBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                       |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| **listC**   | `listC`                                                                                                                                                |
| **addC**   | `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]` <br> e.g., `addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **deleteC** | `deleteC INDEX` <br> e.g., `deleteC 1`                                                                                                                 | 
| **editC**   | `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]` <br> e.g., `editC 1 n/John p/12345678`                                               |
| **findC** | `findC KEYWORD [MORE_KEYWORDS]` <br> e.g., `findC Phineas Ferb`                                                                                        |
| **filterC** | `filterC KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterT cs2103t`                                                                                         |                                                                                                 |
| **listT**   | `listT`                                                                                                                                                |
| **addT**   | `addT d/DESCRIPTION D/DEADLINE`                                                                                                                        |
| **markT**    | `markT INDEX` <br> e.g., `mark 1`                                                                                                                      |
| **unmarkT**  | `unmarkT INDEX` <br> e.g., `unmark 1`                                                                                                                  |
| **searchT** | `findT KEYWORD [MORE_KEYWORDS]` <br> e.g., `findT cs2103t`                                                                                             |
| **filterT** | `filterT KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterT cs2103t`                                                                                         |
| **listL**   | `listL`                                                                                                                                                |
| **addL**   | `addL c/INDEX n/LABEL_NAME` OR  `addL t/INDEX n/LABEL_NAME`                                                                                            |
| **deleteL**   | `deleteL c/INDEX n/LABEL_NAME` OR `deleteL t/INDEX n/LABEL_NAME`                                                                                       |
