---
layout: page
title: User Guide
---

Cobb is an optimised object-oriented command-line entry management application that aims to make database management for Real Estate Agents easier and more accessible.

It is optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a **Graphical User Interface (GUI)**. If you can type fast, Cobb can carry out functionality much faster than other traditional database management software can!

Cobb can also run on any machine, *without the need for an internet connection*.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `cobb.jar` from [here](https://github.com/AY2223S1-CS2103T-F12-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Cobb.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will display some help text.<br>
   Some example commands you can try:

    * **`listbuyers`** : Lists all buyers in the database, that is, clears any buyer filters currently in place..

    * **`addbuyer -n Tim Cook -ph 91234567 -r 1000000-2500000 -c bright; sunny`**: Adds a buyer named "Tim Cook" with phone number "91234567" to the database.
      This buyer has a specified price range, and desired characteristics for the property he wants to buy.

* **`delete -s -n Tim Cook`** : Deletes a seller named Tim Cook from the database.

* **`help`** : Displays some help text.

* **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* The first word in the command string actually specifies which command we are invoking. For example, `help` specifies that we are invoking the `help` command, while `adduser -n John Doe` specifies that we are using the `adduser` command.


* Items in square brackets are parameters that must be passed into the command.<br>
  e.g. `[-n NAME]` indicates that the command requires a `NAME` parameter to be passed in.


* Parameters that contain angled braces `<>` means that the valid values that a parameter can take must be one of the values within the braces.<br>
  e.g. `-r <s, b>` means that the `r` parameter can take values `s` or `b`.


* Parameters that contain curly braces `{}` means that the parameters are optional, and can be excluded from the command if not required.<br>
  For an example, `{-c CHARACTERISTICS}` means that the `CHARACTERISTICS` parameter is optional.


* Parameters can be in any order.<br>
  e.g. if the command specifies `[-n NAME] [-p PHONE_NUMBER]`, `[-p PHONE_NUMBER] [-n NAME]` is also acceptable.


* To specify a parameter and the value that it will take, type the parameter's flag followed by a space, and then its value.<br>
  e.g `-n John Doe` will define the parameter `name` to store the value `John Doe`.


* If commands are missing specific parameters required for it to execute, an error message will be displayed that contains information about the syntax of the command and its required parameters.


<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Note that Cobb's `update` and `find` queries searches for entries that have a specified name. In the event of multiple matches, all matching entries will be displayed on the screen for you to view.
</div>


</div>

### Viewing help : `help`

Displays a helpful list of commands and their related syntax for the user to refer to.

Format: `help`


### Adding a person to the database: `addbuyer`

Adds a user to the database, with a tag to specify whether the user is a buyer or seller, or both.<br>
Syntax: `addbuyer [-n NAME] [-ph PHONE]] [-e EMAIL] [-a address] {-r PRICE RANGE} {-c CHARACTERISTICS}}`

The `-n` flag indicates the name of the buyer.<br>
The `-ph` flag indicates the buyer’s phone number.<br>
The `-e` flag indicates the buyer’s email.<br>
The `-a` flag indicates the buyer’s house address.<br>
The `-r` flag indicates the price range of properties that the buyer can accept.<br>
The `-c` flag indicates the characteristics that the buyer is looking for in a property.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Notes:
The range field and the characteristics field are optional.
That is, it is perfectly okay to initialise a buyer that does not have these fields.
</div>

Examples:<br>
`addbuyer -n Tim -ph 87321237 -e tim@gmail.com -a S648234` : Adds a buyer named Tim to the database.<br>
`addbuyer -n Jane -ph 89991237 -e jane@gmail.com -a S123456 -r 10000-500000 -c bright; sunny` : Adds a buyer named Jane to the database.
Ideally, Jane would like a property that costs between $10000 - $500000, and has characteristics "bright" and "sunny".

### Adding a property to the database: `addprop`

Adds a property to the database along with relevant information.<br>
Syntax: `addprop [-n NAME] [-price PRICE] [-a ADDRESS] [-d DESCRIPTION] {-c CHARACTERISTICS} [-owner OWNER NAME] [-p PHONE]`

The `-n` flag indicates the name of the property.<br>
The `-price` flag indicates the property’s price.<br>
The `-a` flag indicates the property’s address.<br>
The `-d` flag indicates the property’s description (characteristics).<br>
The `-c` flag indicates the characteristics associated with the property.<br>
The `-owner` flag indicates the name of the owner of the property.<br>
The `-p` flag indicates the phone number of the owner of the property.<br>

Examples: <br>
`addprop -n Peak Residences -a 333 Thompson Road -price 1000000 -d long long long property description -owner Bob -p 91234567`: Adds a property called "Peak Residences" to the database along with its relevant description.
The property is owned by Bob with a phone number of 91234567.

### Deleting buyers from the database: `deletebuyer`

Syntax: `deletebuyer INDEX`

<div markdown="span" class="alert alert-primary">:Exclamation: **Note:**
Note that this command has a slightly different syntax from the usual commands, as it takes a number representing the index of the object that you would like to delete
instead of flags like other commands.
</div>

The`INDEX` specified in this command refers to the index of the entry that is currently visible on the list.
Note that if an entry has been filtered out / is not currently on the list, then a `deletebuyer` command cannot be executed on it.

Examples:<br>
`deletebuyer 5`: Deletes the fifth buyer currently visible on the buyer list.

### Deleting properties from the database: `deleteprop`

Syntax: `deleteprop INDEX`

The`INDEX` specified in this command refers to the index of the entry that is currently visible on the list.
Note that if an entry has been filtered out / is not currently on the list, then a `deleteprop` command cannot be executed on it.

Examples:<br>
`deleteprop 5`: Deletes the fifth property currently visible on the property list.

### Update a property entry in database: `editprop`

Syntax: `editprop INDEX {-n NAME} {-price PRICE} {-a ADDRESS} {-d DESCRIPTION} {-c CHARACTERISTICS} {-owner OWNERNAME} {-p PHONE}`

Updates a property’s details with specified information in specified categories.

The `INDEX` indicates which property in the list we are choosing to update.
The `-n` flag indicates the property's new name.<br>
The `-p` flag indicates the property’s new price.<br>
The `-a` flag indicates the property’s new address.<br>
The `-d` flag indicates the property’s new description.<br>
The `-c` flag indicates the property's new characteristics.<br>
The `-owner` flag indicates the property's owner's new name.<br>
The `-p` flag indicates the property's owner's new phone number.<br>

Note that we are updating existing entries in the database, hence all parameters above are optional,
as we can choose to update a parameter without changing the others. Also, the `INDEX` field specifies the index of the property that we want to update
on the current visible property list, so if the property is not on this list, then we cannot execute this command on the property.

Examples:<br>
`editprop 3 -n Hill Residence -a Block 225 -p 750000`: Updates property at index 3 of the list to have a new name Hill Residence, a new address Block 225 and price 750000.

### Update a buyer entry in the database: `editbuyer`

Syntax: `editbuyer INDEX {-n NAME} {-p PHONE} {-e EMAIL} {-a ADDRESS} {-r PRICERANGE} {-c CHARACTERISTICS} {-priority PRIORITY<HIGH, NORMAL, LOW>}`

The `-n` flag indicates the buyer's new name.<br>
The `-p` flag indicates the buyer's new phone number.<br>
The `-e` flag indicates the buyer's new email address.<br>
The `-a` flag indicates the buyer's new address.<br>
The `-r` flag indicates the buyer's new acceptable price range.<br>
The `-c` flag indicates the buyer's new desired property characteristics.<br>
The `-priority` flag indicates the buyer's new priority - Priority values must be one of `HIGH, NORMAL, LOW`.

Note that we are updating existing entries in the database, hence all parameters above are optional,
as we can choose to update a parameter without changing the others. Also, the `INDEX` field specifies the index of the buyer that we want to update
on the current visible buyer list, so if the buyer is not on this list, then we cannot execute this command on the buyer.

Examples:<br>
`editbuyer 3 -n John Doe -e johndoe@yahoo.com -r 40000-50000 -priority HIGH`: Updates buyer at index 3 to have a new name "John Doe", new email "johndoe@yahoo.com", new acceptable price range of $40000 - $500000, and a high priority.
`editbuyer 1 -c bright; sunny`: Updates buyer at index 1 to have new desired characteristics of bright and sunny.

### Find entry in database: `find`
Syntax: `find [-k KEYWORDS]`

Searches through the database and returns all buyers whose names contain any keywords in KEYWORDS _(case-insensitive)_.

The `-k` flag indicates the keywords that will be searching through. Each space-separated keyword will be taken as its own search term.

Examples:<br>
`find -k John`: Looks for all buyers that have “John” in their name.<br>
`find -k John Alice Bob`: Looks for all buyers that have "John", "Alice" or "Bob" in their name.

### Filter buyers in database: `filterbuyers`

Filters buyers in the database according to a given condition, and updates the visible buyer list.

Syntax: `filterbuyers {-p PRICE} {-c CHARACTERISTICS} {-priority PRIORITY<HIGH, NORMAL, LOW>} `

The `-p` flag indicates that we will be filtering buyers that have a price range containing the specified price.
The `-c` flag indicates that we will be filtering buyers that want properties with some (or all) of the specified ";"-separated characteristics.
The `-priority` flag indicates that we will be filtering buyers according to the specified priority level.

Note that a user must only provide one of the three optional flags listed above, or else the command will not run.

Examples:<br>
`filterbuyers -p 500000`: Filters all buyers that have a price range containing $500000.
`filterbuyers -c bright; sunny`: Filters all buyers that have "bright" and/or "sunny" in their desired characteristics.
`filterbuyers -priority HIGH`: Filters all buyers that are of `HIGH` priority.

### Filter property in database: `filterprops`

Filters properties in the database according to a given condition, and updates the visible property list.

Syntax: `filterprops {-r PRICE RANGE} {-c CHARACTERISTICS} {-seller}`

The `-r` flag indicates that we will be filtering properties that have a price within the specified price range.
The `-c` flag indicates that we will be filtering properties with some (or all) of the specified ";"-separated characteristics.
The `-seller` flag indicates that we will be filtering properties that have the specified seller.

Note that a user must only provide one of the three optional flags listed above, or else the command will not run.

Examples:<br>
`filterprops -r 500000-1000000`: Filters all properties that have a price in the range $500000 - $1000000.
`filterprops -c bright; sunny`: Filters all properties that have "bright" and/or "sunny" in their characteristics.

### List buyers in database: `listbuyers`

Lists all buyers in the database in the visible list, that is, removes all filters.

Syntax: `listbuyers`

### List properties in database: `listproperties`

Lists all buyers in the database in the visible list, that is, removes all filters.

Syntax: `listbuyers`

### Exiting the program : `exit`

Exits Cobb with exit code 0.

Format: `exit`

### Saving the data

Cobb's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Cobb's data are saved as a JSON file `[JAR file location]/data/cobb.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Cobb will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Cobb home folder.
Alternatively, copy and paste the data file with the *same name* from your old computer.

**Q**: Help! I can't seem to get a command to work...<br>
**A**: Take a look at the command info above! Make sure that you have supplied all necessary parameters and specified parameter flags in a correct manner (`-` for short, `--` for full).

**Q**: How do I run the app if double-clicking the jar file does nothing? <br>
**A**: First, make sure that you have Java `11` or above installed. For the more technically inclined, run this command in the jar file's home directory: `java -jar cobb.jar`<br>
If the problem persists, report the bug to us!

**Q**: I deleted my data file! Is there any way to recover the data that I lost?<br>
**A**: Unfortunately, there is no way for you to recover your data after you have deleted it. However, we are working on a way to make data persist in the future, so stay tuned!

**Q**: How do I uninstall Cobb? <br>
**A**: We are sad to see you go :( Cobb is not installed onto your hard drive, so you only need to delete the `cobb.jar` file as well as any associated data files.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                | Format, Examples                                                                                                                                                                                                                                             |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add buyer**         | `addbuyer [-n NAME] [-ph PHONE]] [-e EMAIL] [-a address] {-r PRICE RANGE} {-c CHARACTERISTICS}}` <br> e.g., `addbuyer -n Tim -ph 87321237 -e tim@gmail.com -a S648234`                                                                                       |
| **Add property**      | `addprop [-n NAME] [-price PRICE] [-a ADDRESS] [-d DESCRIPTION] {-c CHARACTERISTICS} [-owner OWNER NAME] [-p PHONE]` <br> e.g.`addprop -n Peak Residences -a 333 Thompson Road -price 1000000 -d long long long property description -owner Bob -p 91234567` |
| **Delete buyer**      | `deletebuyer INDEX` <br> e.g. `deletebuyer 5`                                                                                                                                                                                                                |
| **Delete property**   | `deleteprop INDEX` <br> e.g. `deleteprop 5`                                                                                                                                                                                                                  | 
| **Update property**   | `editprop INDEX {-n NAME} {-price PRICE} {-a ADDRESS} {-d DESCRIPTION} {-c CHARACTERISTICS} {-owner OWNERNAME} {-p PHONE}`<br> e.g., `editprop 3 -n Hill Residence -a Block 225 -p 750000`                                                                   |
| **Update buyer**      | `editbuyer INDEX {-n NAME} {-p PHONE} {-e EMAIL} {-a ADDRESS} {-r PRICERANGE} {-c CHARACTERISTICS} {-priority PRIORITY<HIGH, NORMAL, LOW>}`<br> e.g.,`editbuyer 3 -n John Doe -e johndoe@yahoo.com -r 40000-50000 -priority HIGH`                            |
| **Find entries**      | `find [-k KEYWORDS]` <br> e.g. `find -k John Alice Bob`                                                                                                                                                                                                      |
| **Filter buyers**     | `filterbuyers {-p PRICE} {-c CHARACTERISTICS} {-priority PRIORITY<HIGH, NORMAL, LOW>}` <br> e.g. `filterbuyers -c bright; sunny`                                                                                                                             |
| **Filter properties** | `filterprops {-r PRICE RANGE} {-c CHARACTERISTICS} {-seller}` <br> e.g. `filterprops -r 500000-1000000`                                                                                                                                                      |
| **List users**        | `listbuyers`                                                                                                                                                                                                                                                 |
| **List Properties**   | `listprops`                                                                                                                                                                                                                                                  |
| **Exit cobb**         | `exit`                                                                                                                                                                                                                                                       |
| **Get help**          | `help`                                                                                                                                                                                                                                                       |
