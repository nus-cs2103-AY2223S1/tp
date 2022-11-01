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

2. Download the latest `yellowbook.jar` from [here](https://github.com/AY2223S1-CS2103T-F11-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your YellowBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listC`** : Lists all contacts.

   * **`addC`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to YellowBook.

   * **`deleteC`**`3` : Deletes the 3rd contact shown in the current contact list.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Sample Usage

To better understand the usage of YellowBook, we have provided a usage scenario of YellowBook below. We encourage all first-time users to follow along the to learn how each command works.

1. YellowBook comes with a list of sample contacts and tasks by default.

2. Let's start by listing all the contacts in YellowBook. Type `listC` in the command box and press Enter to execute it. You should see a list of contacts. Try using `listT` to list all the tasks.

3. More to be added

4. As you have have noticed, YellowBook's commands are mnemonically named. A [Command Summary](#command-summary) with these helpful tips can be found below.

5. Now that you are ready, feel free to add your own contacts and tasks to YellowBook!

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [r/REMARK]` can be used as `n/John Doe r/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/LABEL]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listC`, `listT`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## General Features

### Viewing help : `help`

Shows a window with a link to this user guide and latest release of YellowBook.

### Exiting YellowBook : `exit`

Closes the YellowBook program.

### Undo a command : `undo`

Undo the last command.

Format: `undo`

* For exceptionally large contact/task lists, it may take a few seconds to undo the command.

* Undo is not available for commands that do not modify the contact/task data (e.g. list, help, findC, findT, filterC, filterT etc.)

### Redo a command : `redo`

Redo the last command.

Format: `redo`

* For exceptionally large contact/task lists, it may take a few seconds to redo the command.

* Redo is not available for commands that do not modify the contact/task data (e.g. list, help, findC, findT, filterC, filterT etc.)

## Section 1: Contacts

### Add a contact: `addC`

Adds a contact to the address book.

Format:  `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]`

* The remark of a contact is optional.

* Remarks are limited to alphanumeric characters and spaces.

* You cannot add a contact that is the same as one already in the address book. Two people are the same if they have the same email or phone number.

* Contacts have no tags when first created.

Examples:

* `addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

* `addC n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 r/Weird person`

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

### Editing a contact: `editC`

Edits the information fields (e.g. name, mobile number, email address) of an existing contact in the address book.

Format: `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]`

* Index of a contact is its index number on the contact list.

* INDEX must be a positive integer more than 0.

* At least one of the optional fields must be provided.

* Input values will replace existing values.

* Remarks are limited to alphanumeric characters and spaces.

Example:

* `editC 1 n/John p/12345678` edits the first contact’s name to be John and phone number to be 12345678.

### Find a contact: `findC`

Finds a contact using one or more information fields (e.g. name, mobile number, email, and/or address)

Format: `findC [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]`

* At least one information field has to be provided.

* The search is case-insensitive, e.g. `dr. doofenshmirtz` will match `Dr. Doofenshmirtz`.

* The order of the keywords does not matter, e.g. `findC n/John p/91231234` will return the same result as `findC p/91231234 n/John`.

* Only full words will be matched. e.g. `John` will not match `Johnny`.

* Contacts matching at least one keyword will be returned. e.g. `n/Perry Dr.`
  will match contacts with name `Perry the Platypus` and `Dr. Doofenshmirtz`.

Example:

* `findC n/john p/81113210 e/john@gmail.com a/123 kent ridge road` will return a contact whose name contains the word `john`, phone number `81113210`, email `john@gmail.com`, and address `123 kent ridge road`.

* `findC n/flynn p/91231234 e/flynn@gmail.com` will return `Candace Flynn` and `Phineas Flynn`, provided they have the same phone and email.


### Filtering contacts by label: `filterC`

Filters contacts whose label(s) contain any of the given keywords.

Format: `filterC KEYWORD [MORE_KEYWORDS]`

* The filter is case-sensitive, e.g. `cs2103t` will not match `CS2103T`.

* The order of the keywords does not matter, e.e. `cs2101 cs2103t` will match
  `cs2103t` and `cs2101`.

* Only the contact's label is filtered.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

Example:

* `filterC cs2103t` will return a contact with label `cs2103t`.


### Copying contact emails by label: `copyC`

Displays a string of emails of contacts with a label that matches the given keyword for easier copy-pasting.

Format: `copyC KEYWORD`

* `copyC` is case-sensitive, e.g. `CS2103T` is different from `cs2103t`.

* Only full words will be matched, e.g. `cs2103t` will not match `cs2103`.

* Only one keyword is allowed. If more than one keyword is typed, e.g. `copyC CS2103T CS2101`, this will be taken as one
keyword "CS2103T CS2101" and the command will not work.

Example:

* `copyC CS2103T` returns a string of emails of contacts that contain the label `CS2103T`.


## Section 2: Tasks
### Listing all tasks: `listT`

Shows all (non-archived) tasks stored in the task list.

Format: `listT`

### Listing all tasks: `listAT`

Shows all archived tasks stored in the task list.

Format: `listAT`

### Adding a task: `addT`

Adds a task to the task list.

Format: `addT d/DESCRIPTION D/DEADLINE`

* Tasks are unique. There cannot be more than one task with the same description, deadline and tag list in the task list.

* The description and deadline of the task are not allowed to be empty.

* Newly added tasks are marked as not done by default.

* Tasks have no tags when first created.

* Tasks that are past their deadline can still be added since overdue tasks might have to be completed still.

The following scenarios should not happen for your command to run successfully:
- If the description of the task is empty.
- If the deadline of the task is empty.
- The deadline of the task is not in dd-mm-yyyy format.

Example:

* `addT d/buy milk D/12-09-2022` will add the task "buy milk" with deadline 12 September 2022.

### Removing a tasks: `deleteT`

Removes a task from the task list.

Format: `deleteT INDEX`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

Examples:

* `listT` followed by `deleteT 1` deletes the first task in the task list.

* `findT d/book` followed by `deleteT 1` deletes the first result of the `findT` command.

### Editing a task: `editT`

Edits the information fields (e.g. description, deadline) of an existing task in the address book.

Format: `editT INDEX [d/DESCRIPTION] [D/DEADLINE]`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

* At least one of the optional fields must be provided.

* Input values will replace existing values.

Example:

* `editT 1 d/sleep D/22-10-2022` edits the first task’s description to be sleep and deadline to be 22-10-2022.

### Marking task as completed: `markT`

Marks a task in the task list as completed.

Format: `markT INDEX`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

Examples:

* `listT` followed by `markT 1` marks the first task in the displayed task list as done.

* `findT d/book` followed by `markT 1` marks the first result of the `findT` command as done.

### Marking task as incomplete: `unmarkT`

Marks a task in the task list as incomplete.

Format: `unmarkT INDEX`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

Examples:

* `listT` followed by `unmarkT 1` marks the first task in the displayed task list as undone.
* `findT d/book` followed by `unmarkT 1` marks the first result of the `findT` command as undone.

### Find a task: `findT`

Finds a task using one or more information fields (e.g. description, and/or deadline)

Format: `findT [d/DESCRIPTION] [D/DEADLINE (dd-mm-yyyy)] [s/STATUS (complete / incomplete)]`

* At least one information field has to be provided.

* The search is case-insensitive, e.g. `homework` will match `HOMEWORK`.

* The order of the keywords does not matter, e.g. `math homework` will match
  `homework math`.

* Only full words will be matched. e.g. `math` will not match `mathematics`.

* Task fields matching at least one keyword will be returned. e.g. `d/cs2103t cs2101` will match
  `cs2103t tutorial` and `cs2101 reflection`.

Example:

* `findT d/cs2103t D/25-12-2022` will return task(s) with description containing `cs2103t` and deadline `25th December 2022`.

* `findT s/incomplete` will return task(s) that are not complete.

### Filtering tasks by label: `filterT`

Filters tasks whose label(s) contain any of the given keywords.

Format: `filterT KEYWORD [MORE_KEYWORDS]`

* The filter is case-sensitive, e.g. `cs2103t` will not match `CS2103T`.

* The order of the keywords does not matter, e.g. `cs2101 cs2103t` will match
  `cs2103t cs2101`.

* Only the task's label is filtered.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

Example:

* `filterT cs2103t` will return a tasks with label `cs2103t`.

### List tasks with deadlines up to and including the specified date: `remindT`

List tasks in YellowBook with deadlines up to and including the specified date.

Both completed and incomplete tasks are listed.
Task with deadline that are already past are also listed.

The following scenarios should not happen for your command to run successfully:
- The deadline of the task is not in dd-mm-yyyy format.

Format: `remindT DEADLINE`

Example:

* `remindT 12-09-2022` will list all tasks with deadlines up to and including 12-09-2022.

### Show the percentage of tasks with the specified tags that are completed: `progressT`

Show the percentage of tasks whose label(s) contain any of the given keywords that are complete to one decimal place of accuracy.

Format: `progressT KEYWORD [MORE_KEYWORDS]`

* The filter is case-sensitive, e.g. `cs2103t` will not match `CS2103T`.

* Both complete and incomplete tasks are listed.

* Tasks with deadlines that are already past are also listed.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Tasks with labels matching at least one keyword will be returned.

The following scenarios should not happen for your command to run successfully:
- The deadline of the task is not in dd-mm-yyyy format.

Example:

* `progressT cs2103t` will show the percentage of tasks with label `cs2103t` that are completed, then list all tasks with labels matching at least one keyword.

### Sorting all tasks by deadline: `sortD`

Sorts all tasks in the task list by deadline.

* Adding or editing a task will not affect the sorted order of the task list.

* The list remains in this sorted order until a different sort command is used.

Format: `sortD`

### Sorting all tasks by id: `sortI`

Sorts all tasks in the task list by id.

* Id is the order in which the tasks were added.

* Adding or editing a task will not affect the sorted order of the task list.

* The list remains in this sorted order until a different sort command is used.

Format: `sortI`

### Archiving a task: `archiveT`

Archives a task in the task list, removing it from main task list.

* Archived task list will be displayed after executing this command.

Format: `archiveT INDEX`

### Archiving a task: `unarchiveT`

Unarchives a task in the task list, adding it to the main task list.

* Main (unarchived) task list will be displayed after executing this command.

Format: `unarchiveT INDEX`

## Section 3: Labels

### Listing all labels: `listL`

Shows a list of all existing labels in the address book.

### Adding a label to a contact/task: `addL`

Adds a label to an existing contact/task in YellowBook. Each contact/task can have multiple labels.
At the same time, the label is added to the label list, shown under the "tags" tab of the app.
This list is unique, meaning each label with a distinct name is only shown once, even if more than one contact/task has the same label.

Multiple labels can be added in the same command.
However, only a maximum of one contact and one task can be labelled within the same command.

The following scenarios should not happen for your command to run successfully:
- Contact/task does not exist
- Contact/task already has the required label
- No label is provided
- No contact/task is provided
- More than one contact or more than one task is specified

Format: `addL c/INDEX t/INDEX l/label_NAME`

Example:

* `addL c/3 t/12 l/CS2103T` will add the label "CS2103T" to the 3rd contact on the contact list and 12th task on the task list.

### Removing a label from a contact/task: `deleteL`

Removes a label from an existing contact/task in YellowBook.

If contact/task is last remaining contact/task with said label, label is removed from the label list.
Otherwise, it is only removed from the specified contact/task label list.

Multiple labels can be deleted in the same command.
However, only a maximum of one contact and one task can be edited within the same command.

The following scenarios should not happen for your command to run successfully:
- Contact/task does not exist
- Label does not exist on specified contact/task
- No contact/task is provided
- No label is provided
- More than one contact or more than one task is specified

Format: `deleteL t/INDEX l/label_NAME`

Example:

* `deleteL t/14 l/CS2101` will remove the label "CS2101" from the 14th task on the task list.

### Delete all contacts and tasks by label: `deleteA`

Delete all contacts and tasks who contain the label(s) specified.

Format: `deleteA LABEL_NAME [MORE_LABEL_NAMES]`

* The label is also deleted.

* The label is case-sensitive, e.g. `cs2103t` will not delete labels named `CS2103T`.

* Multiple labels can be specified.

* If a contact/task has multiple labels, it will not be deleted as long as it has at least one label.
  Instead, the labels will be removed from the contact/task.

The following scenarios should not happen for your command to run successfully:
- Label does not exist
- No label is provided

## Automatic tab switching

Depending on the command you enter, you will see the open tab in the GUI switch automatically.
For example, when using a task-related command, the tab switches to "Task" and the task list is displayed.

The result of the entered command is displayed.
For example, after adding a new contact, the list shown on the GUI is the updated list with your new contact included.

## YellowBook data
### Saving the data

YellowBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

YellowBook data are saved as a JSON file `[JAR file location]/data/yellowbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, YellowBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous YellowBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Command        | Mnemonic                                        | Format, Examples                                                                                                                                       |
|----------------|-------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| **help**       | -                                               | `help`                                                                                                                                                 |
| **exit**       | -                                               | `exit`                                                                                                                                                 |
| **undo**       | -                                               | `undo`                                                                                                                                                 |
| **redo**       | -                                               | `redo`                                                                                                                                                 |
| **listC**      | **list** **C**ontact                            | `listC`                                                                                                                                                |
| **addC**       | **add** **C**ontact                             | `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]` <br> e.g., `addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **deleteC**    | **delete** **C**ontact                          | `deleteC INDEX` <br> e.g., `deleteC 1`                                                                                                                 |
| **editC**      | **edit** **C**ontact                            | `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]` <br> e.g., `editC 1 n/John p/12345678`                                               |
| **findC**      | **find** **C**ontact                            | `findC [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]` <br> e.g., `findC n/Ferb`                                                                  |
| **filterC**    | **filter** **C**ontact                          | `filterC KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterC cs2103t`                                                                                         |                                                                                                 |
| **copyC**      | **copy** **C**ontact                            | `copyC KEYWORD` <br> e.g. `copyC CS2103T`                                                                                                              |
| **listT**      | **list** **T**asks                              | `listT`                                                                                                                                                |
| **listAT**     | **list** **A**rchived **T**asks                 | `listAT`                                                                                                                                               |
| **addT**       | **add** **T**ask                                | `addT d/DESCRIPTION D/DEADLINE`                                                                                                                        |
| **deleteT**    | **delete** **T**asks                            | `deleteT INDEX` <br/> e.g., `deleteT 12`                                                                                                               |
| **editT**      | **edit** **T**asks                              | `editT INDEX [d/DESCRIPTION] [D/DEADLINE]` <br> e.g., `editT 1 d/sleep D/22-10-2022`                                                                   |
| **markT**      | **mark** **T**ask as completed                  | `markT INDEX` <br> e.g., `markT 1`                                                                                                                     |
| **unmarkT**    | **unmark** **T**ask as not completed            | `unmarkT INDEX` <br> e.g., `unmarkT 1`                                                                                                                 |
| **archiveT**   | **archive** **T**ask                            | `archiveT INDEX` <br> e.g., `archiveT 1`                                                                                                               |
| **unarchiveT** | **unarchive** **T**ask                          | `unarchiveT INDEX` <br> e.g., `unarchiveT 1`                                                                                                           |
| **findT**      | **find** **T**asks                              | `findT [d/DESCRIPTION] [D/DEADLINE] [s/STATUS]` <br> e.g., `findT d/homework`                                                                          |
| **filterT**    | **filter** **T**asks                            | `filterT KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterT cs2103t`                                                                                         |
| **remindT**    | **remind** **T**asks due on/before certain date | `remindT DEADLINE` <br/> e.g., `remindT 12-09-2022`                                                                                                    |
| **progressT**  | **progress** of **T**ask with label             | `progressT KEYWORD [MORE_KEYWORDS]` <br/> e.g., `progressT cs2103t`                                                                                    |
| **sortD**      | **sort** **T**asks                              | `sortD`                                                                                                                                                |
| **sortI**      | **sort** **T**asks                              | `sortI`                                                                                                                                                |
| **listL**      | **list** **L**abels                             | `listL`                                                                                                                                                |
| **addL**       | **add** **l**abel to contact or task            | `addL c/INDEX n/LABEL_NAME` OR  `addL t/INDEX n/LABEL_NAME`                                                                                            |
| **deleteL**    | **delete** **l**abel from contact or task       | `deleteL c/INDEX n/LABEL_NAME` OR `deleteL t/INDEX n/LABEL_NAME`                                                                                       |
| **deleteA**    | **delete** **A**ll contact(s)/task(s) with tag  | `deleteA LABEL_NAME [MORE_LABEL_NAMES]` <br> e.g., `deleteA cs2103t`                                                                                   |
