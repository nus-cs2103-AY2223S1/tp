---
layout: page
title: User Guide
---

**Duke The Market** is a one-stop marketing tool that allows department stores to keep track of their upcoming 
marketing plan roll-outs, monitor its impact, and to target the appropriate subsegment of its customer base for each of those plans.
Also, it is optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User 
Interface (GUI). If you can type fast, Duke The Market can help you organise your marketing events and reach out to your target customer base much faster than a traditional GUI app.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `dukethemarket.jar` from [here](https://github.com/AY2223S1-CS2103-F09-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the application.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Instantaneous launching
Users that have Java 11 or above installed in their computers can launch the Duke The Market program 
by double-clicking on the file.

### Saving the data
Duke The Market data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a contact: `add`

Adds a contact to the application.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [g/GENDER] [d/DOB] [t/TAG]`

- The compulsory parameters are: name (`n`), phone number (`p`), email (`e`), address (`a`)
- The parameters in [ ] are optional parameters, including gender (`g`), date of birth (`d`), purchase history (`ph`), tag (`t`).
- A person in the contact list can have more than 1 tag.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/m d/20 MAR 2002`
* `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 t/friend t/criminal g/f d/14.12.1998`

__Optional Parameter 1: Gender__

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [g/GENDER]`

- Adds gender to a person in the contact list. The genders accepted by the contact list are: `M`/`m` for male,  `F`/`f` for female.
- NAME can contain more than 1 word.

Examples:
* `add n/John Wang p/98765432 e/johnwang@example.com a/John street, block 123, #01-01 g/M`
* `add n/John p/92781123 e/john@example.com a/Donald street, block 248, #02-04 g/m`
* `add n/Charlotte p/81286623 e/charlotte@example.com a/Charity street, block 101, #10-82  g/F`

__Optional Parameter 2: Date of Birth__

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [d/DOB]`

- Adds date of birth to a person in the contact list. Date formats accepted are: dd.mm.yyyy ; dd MMM yyyy ; month dd, yyyy
- NAME can contain more than 1 word.


Examples:
* `add n/John Wang p/98765432 e/johnwang@example.com a/John street, block 123, #01-01 d/20.03.2000`
* `add n/John p/92781123 e/john@example.com a/Donald street, block 248, #02-04 d/20 MAR 2000`
* `add n/Charlotte p/81286623 e/charlotte@example.com a/Charity street, block 101, #10-82 d/March 20, 2000`


### Listing all persons : `list`

Shows a list of all persons in the application.

Format: `list [s/FIELD]`

* Sorts the contacts by the specified field in **ascending** order. `FIELD` must take one the following values:
  * `n` sort by name
  * `d` Sort by date of birth
  * `g` Sort by gender

* It is optional to specify the field to sort by. If no field is specified, persons are listed in the order they were inserted.
* At most one field can be specified. i.e. Cannot specify 2nd or 3rd criteria to sort by.
* Persons with an empty field that is being used to sort will be placed at the top of the list.

Examples:
* `list` Lists all persons in the order they were inserted.
* `list s/n` Lists all persons sorted by their names.

### Editing a contact : `edit`

Edits an existing contact in the application.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GENDER] [d/DOB] [t/TAG]`

- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
  The index must be **a positive integer** 1, 2, 3, …​, and it must be within the range of the contact list index.
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed (i.e. adding of tags is not cumulative).
- You can remove all the person’s tags by typing t/ without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be
   `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 3 n/Charlotte g/F d/3.4.1998` Edits the 3rd person’s contact: edits name to be `Charlotte`, 
edits gender to be `Female` and edits date of birth to be `3.4.1998`.

__Optional Parameter 1: Gender__

Format: `edit INDEX [g/GENDER]`

- Edits the gender of a person in the contact list. The genders accepted by the contact list are: `M`/`m` for male,
`F`/`f` for female, and `NA` for empty gender (if user wants to hide gender).
- `INDEX` must be **a positive integer** (i.e 1,2,3…)
- `INDEX` must be within the range of the contact list index (i.e. from 1 to size of contact list).

Examples:
* `edit 1 g/M`
* `edit 2 g/f`
* `edit 3 g/NA`

__Optional Parameter 2: Date of Birth__

Format: `edit INDEX [d/DOB]`

- Edits the date of birth of a person in the contact list. Date formats accepted are: dd.mm.yyyy; dd MMM yyyy; month dd, yyyy
- `INDEX` must be **a positive integer** (i.e 1,2,3…)
- `INDEX` must be within the range of the contact list index (i.e. from 1 to size of contact list).


Examples:
* `edit 1 d/20.03.2000`
* `edit 2 d/20 MAR 2000`
* `edit 3 d/March 20, 2000`

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the application.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the application.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Add an event: `addEvent`

Adds a new event in the application.

Format: `addEvent n/EVENT_TITLE d/DATE t/TIME p/PURPOSE`

* The compulsory parameters are: event name (`n`), date (`d`), time (`t`) and purpose (`p`)

Examples:
* `addEvent n/Shoe Sale 30% d/30-05-2022 t/11:00 p/Discount on all shoes for up to 30%`
* `addEvent n/Banana Discount 10% d/20-04-2022 t/14:00 p/10% discount on all bananas`

### Deleting an event: `deleteEvent`

Deletes an existing event in the application.

Format: `deleteEvent INDEX`

* Removes the event at the specified `INDEX`. 
* The index refers to the index number shown in the displayed event list 
* The index must be a positive integer 1, 2, 3, …, and it must be within the range of the event list index.

Examples:
* `deleteEvent 2` after listing all events with `listEvents` deletes the event at index 2
* `deleteEvent 7` after listing all events with `listEvents` deletes the event at index 7

### Display all events: `listEvents`

Shows a list of all existing events in the application.

Format: `listEvents`

Examples:
* `listEvents`

### Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Editing the data file

The application's data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, the application will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action          | Format, Examples                                                                                                                                                                       |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [g/GENDER] [d/DOB] [t/TAG]` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/m d/20 MAR 2002` |
| **Clear**       | `clear`                                                                                                                                                                                |
| **Delete**      | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                    |
| **Edit**        | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GENDER] [d/DOB] [t/TAG]`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                  |
| **Find**        | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                             |
| **List**        | `list  [s/FIELD]` <br> e.g., `list s/n`                                                                                                                                                |
| **AddEvent**    | `addEvent n/EVENT_TITLE d/DATE t/TIME p/PURPOSE`<br> e.g.,`addEvent n/Shoe Sale 30% d/30-05-2022 t/11:00 p/Discount on all shoes for up to 30%`                                        |
| **DeleteEvent** | `deleteEvent INDEX`<br> e.g., `deleteEvent 2`                                                                                                                                          |
| **ListEvents**  | `listEvents`                                                                                                                                                                           |
| **Help**        | `help`                                                                                                                                                                                 |
