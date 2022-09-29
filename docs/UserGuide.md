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

   * **`listusers`** : Lists all users in the database regardless of their roles.

   * **`adduser -n Tim Cook -roles s -p 91234567 -e timcook@gmail.com -a 10 Singapore Avenue`** : Adds a seller named `Tim Cook` to the database.

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


* Parameters that contain angled braces `<>` means that the valid values that a parameter can take must be some (possibly more than one) of the values within the braces.<br>
  e.g. `-r <s, b>` means that the `r` parameter can take values `s`, `b`, or `s,b`.


* Parameters can be in any order.<br>
  e.g. if the command specifies `[-n NAME] [-p PHONE_NUMBER]`, `[-p PHONE_NUMBER] [-n NAME]` is also acceptable.


* Parameters can be specified either through their *full* names or their *shortened* names. A *shortened* parameter name is usually preceded with a single hyphen `-`, while a *full* name is preceded with two hyphens `--`.<br>
  e.g. `-n` is the short form for the `name` parameter, while `--name` is its full name.<br>
  **We call these names *flags*.**


* To specify a parameter and the value that it will take, type the parameter's flag followed by a space, and then its value.<br>
  e.g `-n John Doe` or `--name John Doe` will define the parameter `name` to store the value `John Doe`.


* If commands are missing specific parameters required for it to execute, an error message will be displayed that contains information about the syntax of the command and its required parameters.


<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Note that Cobb's `update` and `find` queries searches for entries that have a specified name. In the event of multiple matches, a secondary dialog will appear for you to choose which entry you are referring to.
</div>


</div>

### Viewing help : `help`

Displays a helpful list of commands and their related syntax for the user to refer to.

Format: `help`


### Adding a person to the database: `addperson`

Adds a user to the database, with a tag to specify whether the user is a buyer or seller, or both.<br>
Syntax: `addperson [-n NAME] [-r <s, b>] [-p PHONE] [-e EMAIL] [-a address]`

The `-n` flag indicates the name of the .<br>
The `-r` flag indicates the roles that the user belongs to.<br>
The `-p` flag indicates the user’s phone number.<br>
The `-e` flag indicates the user’s email.<br>
The `-a` flag indicates the user’s house address.<br>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Notes:
The email, and address fields are optional.
A user can have more than 1 role, aka they can be both a buyer and a seller.
</div>

Examples:<br>
`addperson -n Tim -roles s,b -p 87321237 -e tim@gmail.com -a S648234` : Adds a user named Tim, who is both a seller and buyer. In the database, Tim will have a phone number and an email.<br>
`addperson -n Jane -roles b -p 89991237` : Adds a user named Jane, who is a buyer. The database will only store Jane's phone number *(no email)*.

### Adding a property to the database: `addprop`

Adds a property to the database along with relevant information.<br>
Syntax: `addprop [-n NAME] [-a ADDRESS] [-p PRICE] [-d DESCRIPTION]`

The `-n` flag indicates the name of the property.<br>
The `-a` flag indicates the property’s address.<br>
The `-p` flag indicates the property’s price.<br>
The `-d` flag indicates the property’s description (characteristics).<br>

Examples: <br>
`addprop -n Peak Residences -a 333 Thompson Road -p 1,100,000 -d long long long property description`: Adds a property called "Peak Residences" to the database along with its relavant characteristics.

### Deleting entries from the database

Delete entry with specific keyword or name<br>
Syntax: `delete [-s] [-b] [-p] [-n NAME] [-k KEYWORD]`

The `-s` flag indicates that we will be deleting a seller.<br>
The `-b` flag indicates that we will be deleting a buyer.<br>
The `-p` flag indicates that we will be deleting a property.<br>
The `-n` flag indicates the name of the buyer, seller, or property.<br>

Examples:<br>
`delete -s Tim Cook` Deletes seller named Tim Cook.<br>
`delete -b Tim Cook` Deletes buyer named Tim Cook.<br>
`delete -p Peak Residences -k Thompson Road`: Deletes property named Peak Residences with extra keyword Thompson Road.<br>

### Update a property entry in database

Syntax: `editprop [-n NAME] [-a ADDRESS] [-p PRICE] [-d DESCRIPTION]`

Updates a property’s details with specified information in specified categories.

The `-n` flag indicates the name of the property that we want to edit.<br>
The `-a` flag indicates the property’s address to be updated.<br>
The `-p` flag indicates the property’s price to be updated.<br>
The `-d` flag indicates the property’s description to be updated.<br>

Examples:<br>
`editprop -n Hill Residence -a Block 225 -p 750000`: Updates property with name Hill Residence to have a new address Block 225 and price 750000.

### Update a buyer entry in the database

Syntax: `editbuyer [-en EXISTINGNAME] [-nn NEWNAME] [-m BUDGET] [-r REQUIREMENTS] [-c NUMBER] [-e EMAIL]`

The `-en` flag is used to specify the existing name of the buyer.<br>
The `-nn` flag is used to specify the buyer's new name.<br>
The `-m` flag indicates the buyer’s budget to be updated.<br>
The `-r` flag indicates the buyer’s requirements on the property type to be updated.<br>
The `-c` flag is used to update the contact number.<br>
The `-e` flag is used to update the buyer’s email.<br>

Examples:<br>
`editbuyer -en Mary -m 20500 -r Facing North -c 92348712`: Updates buyer Mary’s budget to $20500, her property requirements to Facing North and her contact number to 92348712.<br>
`editbuyer -en John -nn John Doe -e john_doe@gmail.com`: Updates buyer John to have a new name John Doe and an updated email.

### Update seller entry in database
Syntax: `editseller [-en EXISTINGNAME] [-nn NEWNAME] [-c NUMBER] [-e EMAIL] [-p PROPERTY]`

The `-en` flag is used to specify the existing name of the seller.<br>
The `-nn` flag is used to specify the seller’s new name.<br>
The `-c` flag is used to update the contact number.<br>
The `-e` flag is used to update the seller’s email.<br>
The `-p` flag is used to link a new property to the seller. To update a property, use editprop command instead.<br>

Examples:<br>
`editseller -en Mary -c 92348712`: Updates seller Mary’s contact number to 92348712.<br>
`editseller -en John -nn John Doe -e john_doe@gmail.com`: Updates seller John to have a new name John Doe and an updated email.

### Find entry in database
Syntax: `find [-s] [-b] [-p] [-k KEYWORDS]`

Searches through the database and returns all entries whose names contain any keywords in KEYWORDS.

The `-s` flag indicates that we will be searching within seller entries only.<br>
The `-b` flag indicates that we will be searching within buyer entries only.<br>
The `-p` flag indicates that we will be searching within property entries only.<br>
The `-k` flag indicates the keywords that will be searching through. Each space-separated keyword will be taken as its own search term.

A potential extension would be to abstract finding functionalities into individual commands for users and properties.

This method will throw an error if there are multiple indicator flags in the command statement.

Examples:<br>
`find -s -k John`: Looks for all sellers that have “John” in their name.<br>
`find -p -k`: Clementi Looks for all properties that have “Clementi” in their name.<br>
`find -b -k Keyword1, Keyword2`: This will return all buyers that have either “Keyword1” or “Keyword2” in their names.

### List people in database

Lists all sellers and/or buyers, sorting by date added or alphabetical order, in ascending or descending order.

Syntax: `listusers [-s] [-b] [-sort <dateadded, alpha> <asc, dsc>]`

The `-s` flag indicates that we will be listing only sellers.<br>
The `-b` flag indicates that we will be listing only buyers.<br>
The `-sort` flag indicates that we will sort the entries in the specified order. By default, entries will be listed in descending order of date added.<br>

This method will throw an error if `-s` and `-b` are both present.

A possible extension would be to add a flag to filter users based on their fields.

Examples:<br>
`listusers`: Lists all users, both sellers and buyers, in descending order of date added.<br>
`listusers -s`: Lists only sellers in descending order of date added.<br>
`listusers -b -sort dateadded asc`: Lists only buyers, sorting in ascending order of date added.

### List properties in database

Lists all properties, sorting by date added or alphabetical order, in ascending or descending order.

Syntax: `listprops [-sort <dateadded, alpha> <asc, dsc>]`

The `-sort` flag indicates that we will sort the entries in the specified order. By default, entries will be listed in descending order of date added.

A possible extension would be to add a flag to filter properties based on their fields.

Examples:<br>
`listprops`: Lists all properties in descending order of date added.<br>
`listprops -sort alpha dsc`: Lists properties in descending alphabetical order.

### Exiting the program : `exit`

Exits the program with exit code 0.

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

**Q**: How do I run the app if double clicking the jar file does nothing? <br>
**A**: First, make sure that you have Java `11` or above installed. For the more technically inclined, run this command in the jar file's home directory: `java -jar cobb.jar`<br>
If the problem persists, report the bug to us!

**Q**: I deleted my data file! Is there any way to recover the data that I lost?<br>
**A**: Unfortunately, there is no way for you to recover your data after you have deleted it. However, we are working on a way to make data persist in the future, so stay tuned!

**Q**: How do I uninstall Cobb? <br>
**A**: We are sad to see you go :( Cobb is not installed onto your hard drive, so you only need to delete the `cobb.jar` file as well as any associated data files.

--------------------------------------------------------------------------------------------------------------------

## Command summary

 | Action                       | Format, Examples                                                                                                                                                     |
|------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
 | **Add person**               | `addperson [-n NAME] [-r <s, b>] [-p PHONE] [-e EMAIL] [-a address] ` <br> e.g., `addperson -n Tim -roles s,b -p 87321237 -e tim@gmail.com -a S648234`               |
 | **Add property**             | `addprop [-n NAME] [-a ADDRESS] [-p PRICE] [-d DESCRIPTION]` <br> e.g.`addprop -n Peak Residences -a 333 Thompson Road -p 1,100,000 -d Lorem Ipsum`                  |
 | **Delete person / property** | `delete [-s] [-b] [-p] [-n NAME] [-k KEYWORD]` <br> e.g. `delete -p Peak Residences -k Thompson Road`                                                                |
 | **Update property**          | `editprop [-n NAME] [-a ADDRESS] [-p PRICE] [-d DESCRIPTION]`<br> e.g., `editprop -n Hill Residence -a Block 225 -p 750000`                                          |
 | **Update buyer**             | `editbuyer [-en EXISTINGNAME] [-nn NEWNAME] [-m BUDGET] [-r REQUIREMENTS] [-c NUMBER] [-e EMAIL]`<br> e.g.,`editbuyer -en Mary -m 20500 -r Facing North -c 92348712` |
 | **Update seller**            | `editseller [-en EXISTINGNAME] [-nn NEWNAME] [-c NUMBER] [-e EMAIL] [-p PROPERTY]`<br> e.g., `editseller -en Mary -c 92348712`                                       |
 | **Find entries**             | `find [-s] [-b] [-p] [-k KEYWORDS]` <br> e.g. `find -b -k Keyword1, Keyword2`                                                                                        |
 | **List users**               | `listusers [-s] [-b] [-sort <dateadded, alpha> <asc, dsc>]` <br> e.g. `listusers -b -sort dateadded asc`                                                             |
| **List Properties**          | `listprops [-sort <dateadded, alpha> <asc, dsc>]` <br> e.g. `listprops -sort alpha dsc`                                                                              |
| **Exit cobb**                | `exit`                                                                                                                                                               |
| **Get help**                 | `help`                                                                                                                                                               |
