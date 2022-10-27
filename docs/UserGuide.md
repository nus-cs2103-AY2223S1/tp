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

   * **`add`**`n/John Doe p/98765432 tele/johndoe a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

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

* Parameters with … can be used multiple times, but remember to separate each usage with a whitespace in between.<br>
  e.g. in `adddebt <person index>…`, `<person index>…` is a parameter which can be used as `adddebt 1` or as `adddebt 1 2`.

* Items in square brackets `[]` are optional.<br>
  e.g. in `add n/<name> [t/<tag>]…` can be used as `add n/Alan Poe t/shy pooper` or as `add n/Alan Poe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `d/<description> m/<money>`, `m/<money> d/<description>` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if the command requires `p/<phone number>` and you specify `p/12341234 p/56785678`, only `p/56785678` will be taken. This is useful for correcting a wrong input without having to use backspace.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`)
  will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

#### Parameter-specific behavior

* Whenever `<date>` is specified as a parameter, you should input it in the format `yyyy-mm-dd` where `y` is year, `m` is month and `d` is day.
  e.g. September 5 2022 should be input as `2022-09-05`.

* Whenever `<time>` is specified as a parameter, you should input it in the format `hh:mm` where `h` is the hour in 24h clock format, and `m` is the minute.
  e.g. 5:15PM should be input as `17:15` as per 24h clock notation.

* Whenever `<money>` is specified as a parameter, you should input the amount in dollars and cents. You can also let PayMeLah help you with calculations by ending with '+' to add GST, or '++' to add both Service Charge and GST to the amount specified. All calculated values are automatically rounded up to the nearest cent.<br>
  e.g. when you input `2.00++`, PayMeLah will store a debt with a money amount that has Service Charge and GST added, i.e. `2.36`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to PayMeLah.

Format: `add n/<name> [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag>]…`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 tele/johndoe a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend a/Newgate Prison t/criminal`

### Adding a debt: `adddebt`

Adds a debt to a person in PayMeLah for you to track. Specifying multiple people will add a copy of this debt to each person specified.

Format: `adddebt <person index>… d/<description> m/<money> [date/<date>] [time/<time>]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can tell PayMeLah to add Service Charge and GST to the amount of money specified by including '++' at the back of the amount. A single '+' will add only GST instead.
</div>

* If you do not specify date and time, they will conveniently default to the current date and time.
* If you specify the date but not the time, the time will default to midnight. Be careful that this default behaviour is different from the previous.
* One person **cannot** have 2 debts with the same description, money, date and time. However, they **can** have 2 debts with 3 out of 4 of these items being the same.

Example:
* `adddebt 3 d/McDonalds m/8.9`
* `adddebt 1 4 d/chicken rice m/10++ date/2022-10-12 time/13:00`

### Splitting a debt: `splitdebt`

Splits a debt among several people in PayMeLah such that each person owes the same amount (rounded up to the closest cent).

Format: `splitdebt <person index>… d/<description> m/<money> [date/<date>] [time/<time>]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can split a debt among as many people as you want. You can even include yourself with index '0'.
</div>

Examples:
* `splitdebt 1 2 d/Pizza m/33.99`
* `splitdebt 0 2 5 d/KFC Fish m/13`

### Clearing debts: `cleardebts`

Clears all of a debtor's debts from PayMeLah according to his index number when listed.

Format: `cleardebts <person index>`

Example:
* `cleardebts 3`

### Deleting debts: `deletedebts`

Deletes the debts specified by their index numbers from a person listed in PayMeLah.

Format: `deletedebts <person index> debt/<debt index>…`

Example:
* `deletedebts 2 debt/2 3`

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

Format: `edit <index> [n/<name>] [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag>]…`

* Edits the person at the specified `<index>`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* Cannot be used to modify a person's debts

Examples:
*  `edit 1 p/91234567 tele/johndoe` Edits the phone number and telegram handle of the 1st person to be `91234567` and `@johndoe` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons who match all the given conditions.

Format: `find [n/<name>] [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag>…]
[d/<description>…] [m/<money>…] [above/<money>] [below/<money>]
[date/<date>…] [before/<date>] [after/<date>] [time/<time>…]`

* Name and Address are case-insensitive partial matches. All other fields are exact matches.
* The above and below fields look for people with a debt that lies in the specified monetary range.
* The before and after fields look for people with a debt that lies within the specified date range.
* The order of the conditions does not matter.
  e.g. `d/burger n/hans` will match `Bo Hans` if he owes money for a burger.
* Only persons matching all conditions will be returned (i.e. `AND` search).
  e.g. `d/burger n/hans` will return `Hans Gruber` only if he owes money for a burger,
  but not `Bo Yang` even if he owes money for a burger. 

Examples:
* `find n/John` returns `john` and `John Doe`

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
**Add person** | `add n/<name> p/<phone number> tele/<telegram> a/<address> [t/<tag>]…` <br> e.g., `add n/James Ho p/22224444 tele/James_H0 a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Add debt** | `adddebt <person index> d/<description> m/<money>` <br> e.g., `adddebt 3 d/Chicken Rice m/4`
**Clear** | `clear`
**Delete** | `delete <index>`<br> e.g., `delete 3`
**Edit** | `edit <index> [n/<name>] [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag>]…`<br> e.g.,`edit 2 n/James Lee tele/James_L33`
**Find** | `find [n/<name>] [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag> ...] [d/<description> ...] [m/<money> ...] [above/<money>] [below/<money>] [date/<date> ...] [before/<date>] [after/<date>] [time/<time> ...]`<br> e.g., `find d/burger above/10.0`
**Find debts** | `finddebt <keyword> [<more keywords>]`<br> e.g., `finddebt burger`
**List** | `list`
**List debtors** | `listdebtors`
**Get statement** | `statement`
**Help** | `help`
