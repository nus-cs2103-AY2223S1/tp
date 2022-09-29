---
layout: page
title: User Guide
---

GithubContact is **an address book integrated with Github's Public API, targeted at programmers to help them communicate and collaborate smoothly.**

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/AY2223S1-CS2103T-W08-2/tp.git).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

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

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

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

### Initialize Contact Book : `init`

Initialize contact book with Github integration, this command will fetch all of your public repositories and cache it locally.

Format: `init <github-username>`

_Please note that github username is case-sensitive._


### Adding a person : `add`

Adds user to list and shows contact information page, where attributes can be added and set. Fetches information from GitHub if username is used. The @ symbol indicates the use of a GitHub username.

Format: `add @<github-username> | add <real name>`

### Enter User Details Page : `<ENTER>`

Switches to the details page of a selected user.

### Edit User Details : `{set|add}`

Changes attribute in User contact card if it exists, else initializes a new property.

Format: `{set|add} <field-name> <user-attribute> | add <field-name> <user-attribute>`

All the `{set|add}` commands are provided below:

* Set name of contact set name `<user-real-name>`
* Set email of contact set email `<user-email>`
* Set timezone of contact set timezone `<user-timezone>`
* Set slack handle of contact set slack `<user-slack>`
* Set twitter handle of contact set twitter `<user-twitter>`
* Set telegram handle of contact set telegram `<user-telegram>`


### Delete User Details: `delete <field-name> `

Delete user attribute in User contact card if it exists, else it does nothing.

Format: `delete <field-name> <user-attribute>`

All the `delete` commands are provided below:

* Delete email of contact `delete email <user-email>`
* Delete timezone of contact `delete timezone <user-timezone>`
* Delete slack handle of contact `delete slack <user-slack>`
* Delete twitter handle of contact `delete twitter <user-twitter>`
* Delete telegram handle of contact `delete telegram <user-telegram>`


### Delete User : `delete`

Remove user from cache and from home page.

Format: `delete <@github-username/real-name>`
_Please note that github username is case-sensitive._

### Help Command : `help`

Shows a help page to the user.

Format: `help`

### Search User : `search `

Searches for and displays the users/repos that match the keyword.

Format: `search <keyword>`

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

| Action     | Format, Examples                                                             |
|------------|------------------------------------------------------------------------------|
| **init**   | `init <my-github-username>, init ted-world`                                  |
| **add**    | `add @<github-username>, add <real-name>, add @arnav-ag, add Arnav Aggarwal` |
| **delete** | `delete <attribute>, delete real-name, delete slack, delete twitter`         |
| **delete** | `delete <@github-username/real-name>, delete @sh4nH, delete Shan Hashir`     |
| **set**    | `set @<github-username>, set <real-name>, set @arnav-ag, set Arnav Aggarwal` |
| **help**   | `help`                                                                       |
| **search** | `search <keyword>, search tp`                                                |
| **ENTER**  | N/A                                                                          |