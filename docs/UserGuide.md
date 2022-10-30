---
layout: page
title: User Guide
---

Designed for software engineering project leads, Swift+ is a seamless **desktop app for tracking daily interactions with
contacts using a swift Command Line Interface** (CLI). If you have fast fingers, Swift+ can help you manage events with contacts
more quickly than a traditional GUI app.

- Table of Contents
{:toc}

---

## **Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `swift+.jar` from [here](https://github.com/AY2223S1-CS2103T-T12-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Swift+.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

    <div markdown="span" class="alert alert-primary">:bulb: **Tip** <br>
    The app comes with sample contacts and tasks by default. To delete the sample data quickly, you can use the [`clear`](#clearing-all-data-clear) command.
    </div>

5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list_contact` : Lists all contacts.
    
   - `add_contact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe`.
    
   - `delete_contact 3` : Deletes the 3rd contact shown in the displayed contact list.
    
   - `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

---

## **Features**

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add_contact n/NAME`, `NAME` is a parameter which can be used as `add_contact n/John Doe`.

- Items in square brackets are **optional**.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used **multiple times** including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family`, etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list_contact`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help`

Shows a message explaining how to access the user guide.

- Alternatively, you can also click on the Help button in the top toolbar.

Format: `help`

### Adding a contact: `add_contact`

Adds a contact.

Format: `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip** <br>
A contact can have any number of tags (including 0)
</div>

Examples:

- `add_contact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
- `add_contact n/Betsy Crowe t/developer e/betsycrowe@example.com a/Newgate office p/1234567 t/client`

### Listing all contacts: `list_contact`

Shows a list of all contacts.

Format: `list_contact`

### Finding contacts by name: `find_contact`

Find contacts whose names contain any of the given keywords.

Format: `find_contact KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g. `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Contacts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find_contact John` returns `john` and `John Doe`.
- `find_contact alex david` returns `Alex Yeoh` and `David Li`.
    ![result for 'find alex david'](images/findAlexDavidResult.png)

### Editing a contact: `edit_contact`

Edits an existing contact.

Format: `edit_contact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

- Edits the contact at the specified `INDEX`.
- The index refers to the index number shown in the **displayed contact list**.
- The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the contact will be removed i.e. adding of tags is not cumulative.
- You can remove all the contact's tags by typing `t/` without specifying any tags after it.

Examples:

- `edit_contact 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
- `edit_contact 2 n/Betsy Crower t/` edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

### Deleting a contact: `delete_contact`

Deletes the specified contact.

Format: `delete_contact INDEX`

- Deletes the contact at the specified `INDEX`.
- The index refers to the index number shown in the **displayed contact list**.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list_contact` followed by `delete_contact 2` deletes the 2nd contact in the entire contact list.
- `find_contact Betsy` followed by `delete_contact 1` deletes the 1st contact in the results of the `find_contact Betsy` command.

### Selecting a contact: `select_contact`

Selects the specified contact and displays the contact's assigned tasks.

Format: `select_contact INDEX`

- Selects the contact at the specified `INDEX`.
- The index refers to the index number shown in the **displayed contact list**.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list_contact` followed by `select_contact 1` selects the 1st contact in the entire contact list and shows all tasks assigned to that contact.
- `find_contact Hermione` followed by `select_contact 1` selects the 1st contact in the results of the `find_contact Hermione` command and shows all task assigned to that person.

### Adding a task: `add_task`

Adds a task.

Format: `add_task n/NAME [d/DESCRIPTION] [dl/DEADLINE] [c/CONTACT_INDEX]…​`

- Deadline must be in the format of **`dd-MM-yyyy HHmm`**.

Examples:

- `add_task n/CS2103T iP d/Finish milestones dl/12-12-2022 2359 c/1`
- `add_task n/CS2101 Assignment dl/12-12-2022 2359 c/2 c/3`

### Listing all tasks: `list_task`

Shows a list of all tasks. Tasks are sorted by deadline; a task with an earlier deadline will be listed above a task with a later deadline. Tasks without a deadline are sorted in ascending alphabetical order and listed below those with a deadline. 

Format: `list_task`

### Finding tasks by name: `find_task`

Find tasks whose names contain any of the given keywords.

Format: `find_task KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g. `book` will match `Book`
- The order of the keywords does not matter. e.g. `read book` will match `book read`
- Only the name of the task is searched.
- Only full words will be matched e.g. `Book` will not match `Books`
- Tasks matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Read book` will return `Write book`, `Find book`

Examples:

- `find_task Book` returns `book` and `Book`.
- `find_task read book` returns `read novel` and `sell book`.

### Editing tasks: `edit_task`

Edits an existing task.

Format: `edit_task INDEX [n/NAME] [d/DESCRIPTION] [dl/DEADLINE]`

- Edits the task at the specified `INDEX`. 
- The index refers to the index number shown in the **displayed task list**. 
- The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- Deadline must be in the format of **`dd-MM-yyyy HHmm`**.

Examples:

- `edit_task 1 n/Client meeting d/Gather user stories` edits the task name and description of the 1st task to `Client meeting` and `Gather user stories` respectively.
- `edit_task 2 dl/06-12-2022 1200` edits the deadline of the 2nd task to be `06-12-2022 1200`.

### Deleting tasks: `delete_task`

Deletes an existing task in task list.

Format: `delete_task INDEX`

- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `delete_task 1` Deletes the Task at index 1.
- `delete_task 3` Deletes the Task at index 3.

### Selecting a task: `select_task`

Selects the specified task and displays the contacts assigned to the task.

Format: `select_task INDEX`

- Selects the task at the specified `INDEX`.
- The index refers to the index number shown in the **displayed task list**.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list_task` followed by `select_task 1` selects the 1st task in the entire task list and shows all contacts assigned to that task.
- `find_task sleep` followed by `select_task 1` selects the 1st task in the results of the `find_task sleep` command and shows all contacts assigned to that task.

### Marking a task as complete: `mark`

Marks the specified task as completed.

Format: `mark INDEX`

- The index refers to index number shown in the **displayed task list**.
- The index **must be a positive integer** 1, 2, 3, …​
- Specified task must be currently incomplete for command to succeed.

Examples:

- `list_task` followed by `mark 1` marks the 1st task in the entire task list as completed.
- `find_task sleep` followed by `mark 1` marks the 1st task in the results of the `find_task sleep` command as completed.

### Marking a task as incomplete: `unmark`

Marks the specified task as incomplete.

Format: `unmark INDEX`

- The index refers to index number shown in the **displayed task list**.
- The index **must be a positive integer** 1, 2, 3, …​
- Specified task must be currently completed for command to succeed.

Examples:

- `list_task` followed by `unmark 1` marks the 1st task in the entire task list as incomplete.
- `find_task sleep` followed by `unmark 1` marks the 1st task in the results of the `find_task sleep` command as incomplete.

### Assigning a task to a contact: `assign`

Assigns a task to a contact.

Format: `assign c/CONTACT_INDEX t/TASK_INDEX`

- Assigns the task at the specified `TASK_INDEX` to the contact at the specified `CONTACT_INDEX`. 
- The indices refer to the index number shown in the **displayed task list and contact list** respectively. 
- The indices **must be a positive integer** 1, 2, 3, …​

Examples:

- `assign c/1 t/1` assigns the task at index 1 to the contact at index 1.
- `assign c/3 t/2` assigns the task at index 2 to the contact at index 3.

### Unassign a task from a contact: `unassign`

Removes a contact from a task.

Format: `unassign c/CONTACT_INDEX t/TASK_INDEX`

- Removes the contact at the specified `CONTACT_INDEX` from the task at the specified `TASK_INDEX`. 
- The indices refer to the index number shown in the **displayed task list and contact list** respectively. 
- The indices **must be a positive integer** 1, 2, 3, …​

Examples:

- `unassign c/1 t/1` removes the contact at index 1 from the task at index 1.
- `unassign c/3 t/2` removes the contact at index 3 from the task at index 2.

### Toggling between contacts and tasks tabs: `Ctrl + Tab`

Toggles the view between the contacts and tasks tabs.

Format: `Ctrl + Tab`

- Alternatively, you can also click on the Contacts and Tasks button in the top toolbar.

### Clearing all data: `clear`

Deletes all data in the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

- Alternatively, you can also click on the Exit button in the top toolbar.
 
### Command Suggestion and Autocomplete

To help you familiarize with the commands, Swift+ prompts you with command suggestions as you type and can autocomplete your commands until the next user-required input.

1. Type the first few letters of a command you hope to use and Swift+ will display a suggested command. e.g. `lis`.<br>
   ![autocomplete](images/autocomplete.png)

2. Press Tab to autocomplete your command with the prompted suggestion. If there are multiple commands that have the user inputs as the prefix, this action will autocomplete up to the longest common prefix of all such commands.<br>
e.g. pressing Tab after `lis` will autocomplete the command to `list_` as there are two commands (`list_contact` and `list_task`) that start with `lis`.

<div markdown="block" class="alert alert-info">
:information_source: **Note** <br>
- Command suggestions will not be shown if current input is invalid and the command text will turn red to alert you.<br>
- Autocomplete does not guarantee a successful/valid command, unless the given syntax is followed.<br>
- Autocomplete only completes up to the longest matching prefix if multiple commands are possible with the current user input. However, command suggestions always shows only one of the possible commands.
</div>

### Saving the data

Swift+ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Swift+ data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution** <br>
If your changes to the data file makes its format invalid, Swift+ will discard all data and start with an empty data file at the next run.
</div>

---

## **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Swift+ home folder.

---

## **Command summary**

| Action             | Format                                                                         |
|--------------------|--------------------------------------------------------------------------------|
| **Add Contact**    | `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`                |
| **Add Task**       | `add_task n/NAME [d/DESCRIPTION] [dl/DEADLINE] [c/CONTACT_INDEX]…​`            |
| **Assign Task**    | `assign c/CONTACT_INDEX t/TASK_INDEX`                                          |
| **Clear Data**     | `clear`                                                                        |
| **Delete Contact** | `delete_contact INDEX`                                                         |
| **Delete Task**    | `delete_task INDEX`                                                            |
| **Edit Contact**   | `edit_contact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` |
| **Edit Task**      | `edit_task INDEX [n/NAME] [d/DESCRIPTION] [dl/DEADLINE]`                       |
| **Find Contacts**  | `find_contact KEYWORD [MORE_KEYWORDS]`                                         |
| **Find Tasks**     | `find_task KEYWORD [MORE_KEYWORDS]`                                            |
| **Help**           | `help`                                                                         |
| **List Contacts**  | `list_contact`                                                                 |
| **List Tasks**     | `list_task`                                                                    |
| **Mark Task**      | `mark INDEX`                                                                   |
| **Select Contact** | `select_contact INDEX`                                                         |
| **Select Task**    | `select_task INDEX`                                                            |
| **Unassign Task**  | `unassign c/CONTACT_INDEX t/TASK_INDEX`                                        |
| **Unmark Task**    | `unmark INDEX`                                                                 |
