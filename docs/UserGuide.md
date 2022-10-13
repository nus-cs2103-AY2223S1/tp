---
layout: page
title: User Guide
---

Survin is a desktop application for surveyors to use to keep track of people they have surveyed. The surveyor can easily follow up with people they have surveyed for additional information or for confirmation. The user interacts with the application using a CLI, and it has a GUI created with JavaFX. It is written in Java.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Survin.jar` from [here](https://github.com/AY2223S1-CS2103-F13-2/tp).

1. Copy the file to the folder you want to use as the _home folder_ for the app.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all contacts.

   - **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to Survin.

   - **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   - **`clear`** : Deletes all contacts.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the app.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS g/GENDER b/BIRTHDATE ra/RACE re/RELIGION s/SURVEY [t/TAG]…`

- Adds a surveyee with the all the descriptions listed.
- All descriptors have to be specified and follow their own specified format.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/male b/1989-10-1 ra/White American re/Christian s/Environment Survey` Adds a person with the descriptions as stated.
- `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal g/female b/1991-11-31 ra/Chinese re/Buddhist s/Prison Survey` 

### Listing all persons : `list`

Shows a list of all persons in the app.

Format: `list`

### Editing a person : `edit`

Edits an existing person in Survin.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GENDER] [b/BIRTHDATE] [ra/RACE] [re/RELIGION] [s/SURVEY] [t/TAG]…`

- Edits the surveyee at the specified INDEX. The index refers to the index number of the surveyee you wish to edit, as shown in the display list. The index must be a **positive integer**.
- At least one of the optional fields has to be provided.
- Existing value in specified field will be updated to the new value.
- When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
- You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Delete an existing person in Survin by index or delete all persons satisfying the specified attributes.

Format: `delete INDEX`

Format: delete INDEX, delete [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GENDER] [b/BIRTHDATE] [ra/RACE] [re/RELIGION] [s/NAME OF SURVEY]

- Delete surveyee(s) satisfying the conditions specified.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …
- Fields must be non-empty if deleting by attributes

Example:

- `list` followed by `delete 2` deletes the 2nd person in the app.
- `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
- `delete ra/Chinese re/christian` Deletes all surveyees that are Chinese and Christian.

### Clone a person : `clone`

Clones a specified person in the app.

Format: `clone INDEX`

- Clones the person at the specified `INDEX`.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

* Use `find Alex` to list all the persons whose name contains Alex, followed by `clone 1` to clone the first person in the result and finally use edit command to edit the data of the cloned person.

### Viewing a person: `view`

Views the attributes of all surveyees with some specified attributes.

Format: `[n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GENDER] [b/BIRTHDATE] [ra/RACE] [re/RELIGION] [s/NAME OF SURVEY]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Fields must be non-empty.
</div>

Examples:

```
view g/female ra/chinese re/christian
> Index: 15 Jane Doe 91234567 jane_doe@example.com, …
> Index: 19 Jenette Doe 81234567 jenette_doe@example.com, …
```



### Clearing all entries : `clear`

Clears all entries from the app.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Survin are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Survin data are saved as a JSON file `[JAR file location]/data/survin.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Survin will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Survin home folder.

---

## Command summary

| Action                  | Format, Examples                                                                                                                                                    |
| ----------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add**                 | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**               | `clear`                                                                                                                                                             |
| **Delete**              | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                 |
| **Delete by attribute** | `delete [ra/RACE] [re/RELIGION]`<br> e.g. `delete ra/Chinese`,                                                                                                      |
| **Edit**                | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**                | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                          |
| **List**                | `list`                                                                                                                                                              |
| **Help**                | `help`                                                                                                                                                              |
