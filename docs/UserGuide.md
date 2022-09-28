---
👾 MineFriends User Guide
---

MineFriends is a **desktop app for MineCraft players to manage information about their online friends, 
optimized for use via a Command Line Interface** (CLI), while still having the 
benefits of a Graphical User Interface (GUI).

###**_Table of Contents_**
* Quick Start
* Features
* FAQ
* Command Summary

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `MineFriends.jar` from [here](\to be added\).

1. Copy the file to the folder you want to use as the _home folder_ for your MineFriends.

1. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all of your friends.

   * **`add`**`n/Amy Bee m/AmyBee123 p/85355255 e/amy@gmail.com a/123, Jurong West Ave 6, #08-111` : 
   * Adds a friend named `Amy Bee` to your friend list.

   * **`delete`**`3` : Deletes the 3rd friend shown in your current friend list.
   

Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Amy Bee`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Amy Bee t/friend` or as `n/Amy Bee`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

#
### Viewing help : `help`

Shows a message explaining how to access the help page.

Example: - image to be added -

Format: `help`


### Adding a friend: `add`

Adds a person to your friend list.

Format: `add n/NAME m/MINECRAFT_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [s/SOCIAL_HANDLES] [t/TAG]`


* A person can have any number of social handles (including 0).
* A person can have any number of tags (including 0)


Examples: - image to be added -
* `add n/Amy Bee m/AmyBee123 p/85355255 e/amy@gmail.com a/123, Jurong West Ave 6, #08-111`


* `add n/Benson m/benson01 p/92881083 e/bensontan@hotmail.com a/ 4 Leith road s/ig@bensontan01 t/bff`


### Listing all friends : `list`

Shows a list of all of your MineCraft friends.

Example: - image to be added -

Format: `list`



### Editing a friend's information : `edit`

Edits information about an existing friend in your friend list.

Format: `edit INDEX [n/NAME] [a/ADDRESS] [t/TAG] …`


* Edits the person at the specified `INDEX`. The index refers to the index number shown in your displayed friend list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing social handles and tags, the existing information of your friend will be removed (i.e adding of social handles and tags are not cumulative.)
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples: - image to be added -
* `edit 1 p/91234567 e/amybee123@gmail.com` Edits the phone number and email address of the 1st person to be `91234567` and `amybee123@gmail.com` respectively.
*  
* `edit 2 n/Amy Bee t/` Edits the name of the 2nd person to be `Amy Bee` and clears all existing tags.


### Locating friends by name: `find`

Find friends whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. (e.g. `amy` will match `Amy`)
* The order of the keywords does not matter. e.g. `Amy Bee` will match `Bee Amy`
* Only the name is searched.
* Only full words will be matched (e.g. `Am` will not match `Amy`)
* Persons matching at least one keyword will be returned
  (e.g. `Amy Tan` will return `Amy Bee`, `Benson Tan`)

Examples: - image to be added -
* `find Amy` returns `amy` and `Amy Bee`


* `find amy benson` returns `Amy Bee`, `Benson Tan`<br> - image to be added -


### Deleting a friend : `delete`

Deletes the specified friend from your friend list.

Format: `delete INDEX`

* Deletes the friend at the specified `INDEX` of your friend list.
* The index **must be a positive integer** 1, 2, 3, …

Examples: - image to be added -
* `list` followed by `delete 2` deletes the 2nd friend in your friend list.

### Clearing all entries : `clear`

Clears all entries from your friend list.

Example: - image to be added -

Format: `clear`


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MineFriends data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q: How do I transfer my MineFriends data to another Computer?** <br>
**A**: 
Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MineFriends home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                               |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME m/MINECRAFT_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [s/SOCIAL_HANDLES] [t/TAG]` <br/> <br/> e.g. `add n/Benson m/benson01 p/92881083 e/bensontan@hotmail.com a/ 4 Leith road s/ig@bensontan01 t/bff` |
| **List**   | `list`                                                                                                                                                                                                         | `list`     
| **Edit**   | `edit INDEX [n/NAME] [a/ADDRESS] [t/TAG] …`<br/> <br/> e.g.`edit 2 n/Amy Bee e/amybee123@gmail.com`                                                                                                            |    |                                                                                                                                                                       |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> <br> e.g., `find Amy Benson`                                                                                                                                                |
| **Delete** | `delete INDEX`<br><br> e.g., `delete 3`                                                                                                                                                                        |
| **Clear**  | `clear`                                                                                                                                                                                                        ||
| **Help**   | `help`                                                                                                                                                                                                         |
