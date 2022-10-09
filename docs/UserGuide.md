---
layout: page
title: User Guide
---

PayMeLah is a **desktop app for managing the debts your friends owe you, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PayMeLah can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `paymelah.jar` from [here](https://github.com/AY2223S1-CS2103T-W13-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for PayMeLah.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in diamond brackets `<>` are parameters to be supplied by the user.<br>
  e.g. in `add n/<name>`, `<name>` is a parameter which can be used as `add n/John`.

* Parameters with ... can be used multiple times.<br>
  e.g. in  `add n/<name> [t/<tag>]…`, `[t/<tag>]…` is a parameter which can be used as `add n/John t/forgetful t/loser`

* Items in square brackets `[]` are optional.<br>
  e.g. in `add n/<name> [t/<tag>]…` can be used as `add n/Alan Poe t/shy pooper` or as `add n/Alan Poe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/<name> p/<phone number>`, `p/<phone number> n/<name>` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`)
  will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to PayMeLah.

Format: `add n/<name> p/<phone number> e/<email> a/<address> [t/<tag>]…`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Adding a debt: `adddebt`

Adds a debt to PayMeLah for you to track.

Format: `adddebt <person index> d/<description> m/<money>`

Example:
* `adddebt 3 d/McDonalds m/8.9`

### Clearing debts: `cleardebts`

Clears all of a debtor's debts from PayMeLah according to his index number when listed.

Format: `cleardebts <person index>`

Example:
* `cleardebts 3`

### Getting the statement

Retrieves a statement of the total sum of debts you are owed.

Format: `statement`

Example: `statement` returns `You are owed $583.90 in total.`

### Listing all persons : `list`

Shows a list of all persons in PayMeLah.

Format: `list`

### Listing all debtors : `listdebtors`

Shows a list of all persons that owe you money in PayMeLah.

Format: `listdebtors`

### Editing a person : `edit`

Edits an existing person in PayMeLah.

Format: `edit <index> [n/<name>] [p/<phone number>] [e/<email>] [a/<address>] [t/<tag>]…`

* Edits the person at the specified `<index>`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* Cannot be used to modify a person's debts

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find <keyword> [<more keywords>]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Locating persons by debt description: `finddebt`

Finds persons who are associated with any debts that match any of the given keywords.

Format: `finddebt <keyword> [<more keywords>]`

* The search is case-insensitive. e.g `burger` will match `Burger`
* The order of the keywords does not matter. e.g. `Sharing Meal` will match `Meal Sharing`
* Only the name is searched.
* Only full words will be matched e.g. `Burger` will not match `Burgers`
* Persons with debts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `burger meal` will return people associated with debts that have descriptions `Chicken Burger` or `Meal Sharing`

Examples:
* `finddebt Burger` returns people associated with debts that have descriptions `burger` or `Chicken Burger`
* `finddebt burger meal` returns people associated with debts that have descriptions `Chicken Burger` or `Meal Sharing`<br>

### Deleting a person : `delete`

Deletes the specified person from PayMeLah.

Format: `delete <index>`

* Deletes the person at the specified `<index>`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from PayMeLah.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

PayMeLah data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

PayMeLah data are saved as a JSON file `[JAR file location]/data/paymelah.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, PayMeLah will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add person** | `add n/<name> p/<phone number> e/<email> a/<address> [t/<tag>]…` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Add debt** | `adddebt <person index> d/<description> m/<money>` <br> e.g., `adddebt 3 d/Chicken Rice m/4`
**Clear** | `clear`
**Delete** | `delete <index>`<br> e.g., `delete 3`
**Edit** | `edit <index> [n/<name>] [p/<phone number>] [e/<email>] [a/<address>] [t/<tag>]…`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find <keyword> [<more keywords>]`<br> e.g., `find James Jake`
**Find debts** | `finddebt <keyword> [<more keywords>]`<br> e.g., `finddebt burger`
**List** | `list`
**List debtors** | `listdebtors`
**Get statement** | `statement`
**Help** | `help`
