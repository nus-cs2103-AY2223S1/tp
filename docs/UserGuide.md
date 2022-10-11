---
layout: page
title: User Guide
---

Designed for software engineering project leads, Swift+ is a seamless **desktop app for tracking daily interactions with
contacts using a swift Command Line Interface** (CLI). If you have fast fingers, Swift+ can help you manage events with contacts
more quickly than a traditional GUI app.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `swift+.jar` from [here](https://github.com/AY2223S1-CS2103T-T12-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Swift+.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list_contact`** : Lists all contacts.

   * **`add_contact`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe`.

   * **`delete_contact`**`3` : Deletes the 3rd contact shown in the current list.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add_contact n/NAME`, `NAME` is a parameter which can be used as `add_contact n/John Doe`.

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

### Adding a contact: `add_contact`

Adds a contact.

Format: `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:
* `add_contact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add_contact n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all contacts : `list_contact`

Shows a list of all contacts.

Format: `list_contact`

### Finding contacts by name: `find_contact`

Finds contacts whose names contain any of the given keywords.

Format: `find_contact KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Contacts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find_contact John` returns `john` and `John Doe`
* `find_contact alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Editing a contact : `edit_contact`

Edits an existing contact.

Format: `edit_contact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact's tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit_contact 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `edit_contact 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

### Deleting a contact : `delete_contact`

Deletes the specified contact.

Format: `delete_contact INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list_contact` followed by `delete_contact 2` deletes the 2nd contact.
* `find_contact Betsy` followed by `delete_contact 1` deletes the 1st contact in the results of the `find_contact` command.

### Creating a new task: `add_task`

Adds a task to the task list.

Format: `add_task n/NAME  c/CONTACT_INDEX`

Examples:
* `add_task n/CS2103T iP c/1`
* `add_task n/CS2101 Assignment c/2`

### Listing tasks by contact: `list_task`

Shows a list of all tasks belonging to a given contact.

Format: `list_task c/CONTACT_INDEX`

* Lists the tasks of the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list_task c/5`

### Finding tasks by name: `find_task`

Find tasks whose names contain any of the given keywords.

Format: `find_task KEYWORD [MORE_KEYWORDS]`

* The search is **case-insensitive**. e.g `book` will match `Book`
* The order of the keywords does not matter. e.g. `read book` will match `book read`
* Only the name of the task is searched.
* Only full words will be matched e.g. `Book` will not match `Books`
* Tasks matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Read book` will return `Write book`, `Find book`

Examples:
* `find_task Book` returns `book` and `Find book`

### Editing tasks: `edit_task`

Edits an existing task in task list.

Format: `edit_task INDEX [n/NAME] [c/CONTACT_INDEX]`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* You can remove any associated contacts by typing `c/` without
    specifying any tags after it.

Examples:
* `edit_task 1 n/Gym` Edits the task name of the 1st task to Gym.
* `edit_task 3 n/Product meeting c/3` Edits the task name and associated contact of the 3rd task to be `Team meeting` and the 3rd contact respectively.

### Deleting tasks: `delete_task`

Deletes an existing task in task list.

Format: `delete_task INDEX`

* Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete_task 1` Deletes the Task at index 1.
* `delete_task 3` Deletes the Task at index 3.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Swift+ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Swift+ data are saved as a JSON file `[JAR file location]/data/swift+.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Swift+ will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Swift+ home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Format                                                                         |
|--------------------|--------------------------------------------------------------------------------|
| **Add Contact**    | `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`                |
| **List Contacts**  | `list_contact`                                                                 |
| **Find Contact**   | `find_contact KEYWORD [MORE_KEYWORDS]`                                         |
| **Edit Contact**   | `edit_contact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` |
| **Delete Contact** | `delete_contact INDEX`                                                         |
| **Add Task**       | `add_task n/NAME c/CONTACT_INDEX`                                              |
| **List Tasks**     | `list_task c/CONTACT_INDEX`                                                    |
| **Find Tasks**     | `find_task KEYWORD [MORE_KEYWORDS]`                                            |
| **Edit Task**      | `edit_task INDEX [n/NAME] [c/CONTACT_INDEX]`                                   |
| **Delete Task**    | `delete_task INDEX`                                                            |
