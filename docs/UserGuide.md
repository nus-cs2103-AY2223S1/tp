---
layout: page
title: User Guide
---

MyInsuRec is a **desktop app for financial advisors, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MyInsuRec can get your client and meeting management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `MyInsuRec.jar` from [here](https://github.com/AY2223S1-CS2103T-W16-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MyInsuRec.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   1. `viewClient`: View a particular client
   2. `addClient`: Add a client
   3. `deleteClient`: Delete client

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `addClient n/NAME ...`, `NAME` is a parameter which can be used as `addClient n/John Tan ...`.

* Items in square brackets are optional, while those not in square brackets are compulsory.<br>
  e.g `addClient n/NAME p/PHONE_NUMBER [e/EMAIL]` can be used as `addClient n/John Tan p/12345678 e/John@abc.com` or as `addClient n/John Tan p/12345678`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/28092022 d/30092022`, only `d/30092022` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit`, `listClient` and `listMeeting`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a client: `addClient`

Adds a new client to MyInsuRec.

Format: `addClient n/NAME p/PHONE_NUMBER [e/EMAIL]`
* Adds a client having name `NAME`.
* A client must have a NAME and a PHONE_NUMBER.
* Email is optional.

Examples:
* `addClient n/John Tan p/0123456789`
* `addClient n/John Tan p/0123456789 e/johntan@insurec.com`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Listing meetings: `listMeeting`

List all meetings in MyInsuRec.

Format: `listMeeting`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

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

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding a meeting : `addMeeting`

Adds a new meeting to MyInsuRec. 
DATE should be in DDMMYYYY format and TIME should be in 24-hour format.

Format: `addMeeting n/NAME d/DATE t/TIME`

* Adds a meeting.
* A meeting contains NAME, DATE and TIME.

Examples:
* `addMeeting n/John Tan d/28092022 t/1400`
* `addMeeting n/John Tan t/1400 d/28092022`

### Deleting a Meeting : `delMeeting`

Deletes a meeting from MyInsuRec.

Format: `delMeeting i/INDEX`

* Deletes the meeting at the specified `INDEX`.
* The index refers to the index number shown in the displayed meeting list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listMeeting` followed by `delMeeting 2` deletes the 2nd Meeting in the meeting list.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting MyInsuRec : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                | Format, Examples                                                                                                                                                   |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add client**        | `addClient n/NAME p/PHONE_NUMBER [e/EMAIL]` <br> e.g., <br> • `addClient n/John Tan p/0123456789` <br> • `addClient n/John Tan p/0123456789 e/johntan@insurec.com` |
| **List all clients**  | `listClient`                                                                                                                                                       |
| **View client**       | `viewClient i/INDEX` <br> e.g., <br> • `viewClient i/1`                                                                                                            |
| **Delete client**     | `delMeeting i/INDEX` <br> e.g., <br> • `delClient i/1`                                                                                                             |
| **Add meeting**       | `addMeeting n/NAME d/DATE t/TIME` <br> e.g., <br> • `addMeeting n/Thomas d/28092022 t/1400`                                                                        |
| **List all meetings** | `listMeeting`                                                                                                                                                      |
| **View meeting**      | `viewMeeting i/INDEX` <br> e.g., <br> • `viewMeeting i/1`                                                                                                          |
| **Delete meeting**    | `delMeeting i/INDEX` <br> e.g., <br> • `delMeeting i/1`                                                                                                            |
| **Exit**              | `exit`                                                                                                                                                             |
