---
layout: page
title: User Guide
---

# GithubContact

![Ui](images/Ui.png)

GithubContact is an address book **integrated with Github's Public API**, targeted at programmers to help them communicate and collaborate smoothly.

--------------------------------------------------------------------------------------------------------------------

## Getting Started

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest GithubContact.jar from [here](https://github.com/AY2223S1-CS2103T-W08-2/tp/releases).

3. Copy the file to the folder you want to use as the home folder for your GithubContact.

4. Double-click the file to start the app. The GUI similar to the above should appear in a few seconds. Note how the app contains some sample data.

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`name/John Doe phone/98765432 email/johnd@example.com address/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add name/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `name/NAME [tag/TAG]` can be used as `name/John Doe tag/friend` or as `name/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/friend`, `tag/friend tag/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `name/NAME phone/PHONE_NUMBER`, `phone/PHONE_NUMBER name/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `phone/12341234 phone/56785678`, only `phone/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a person : `add`

Adds person to list and shows contact information page, where attributes can be added and set.

Format: `add name/NAME address/ADDRESS [role/ROLE] [timezone/TIMEZONE] [phone/PHONE] [email/EMAIL] [slack/SLACK] [telegram/TELEGRAM] [tag/TAG] `

Example:
- `add name/John Doe address/27 Clementi`
- `add name/Alex address/22 Clementi phone/86609830 email/alex@gmail.com`
- `add name/Mike address/21 Clementi phone/86609831 email/mike@gmail.com slack/mike123 telegram/@mike123`

### Show Person Details : `<ENTER>` or double click

Shows person details in another page.

There are two ways to show person details

1. Keyboard-friendly way

   1. Use `<TAB>` to navigate to the persons' list.
   
   2. Press `<ENTER>` to show person details.
   
2. General usage

   1. Double-click on the person card in persons' list.

### Edit Person Details : `set`

Set attribute of a person.

Format `set [name/NAME] [address/ADDRESS] [role/ROLE] [timezone/TIMEZONE] [phone/PHONE] [email/EMAIL] [slack/SLACK] [telegram/TELEGRAM] [tag/TAG] `

- You can only run this command in person details page. Please refer to "Show Person Details" to enter person details page.
- At least one optional attribute must be provided.
- Existing values will be updated to provided values.

### Delete Person Details: `delete`

Delete attribute of a person.

Format: `delete ATTRIBUTE_NAME`

- You can only run this command in person details page. Please refer to "Show Person Details" to enter person details page.
- `name` and `address` are not able to delete, as they are required attributes.
- You only can delete one attribute at one time.

Attributes (`ATTRIBUTE_NAME`) that are able to delete:
- `role`
- `timezone`
- `email`
- `phone`
- `slack`
- `telegram`

### Delete Person : `delete`

Delete the specified person from the address book.

Format: `delete INDEX`

- You can only run this command in person listing page.
- The index refers to the index number shown in the person list.
- The index must be **positive integer** 1, 2, 3...

### Find Person : `find`

Find person and displays the persons that match the keyword.

Format: `find KEYWORD`

### Back to previous page : `back` or `<ESC>`

Back to previous page

Format: `back` or `<ESC>`

### Show help : `help`

Shows help page.

Format: `help`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action              | Format, Examples                                                                                                                                                                    |
|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **add**             | `add name/NAME address/ADDRESS [role/ROLE] [timezone/TIMEZONE] [phone/PHONE] [email/EMAIL] [slack/SLACK] [telegram/TELEGRAM] [tag/TAG]`<br> `add name/John Doe address/27 Clementi` |
| **delete person**   | `delete INDEX` <br> `delete 1, delete 2`                                                                                                                                            |
| **delete attribute** | `delete ATTRIBUTE` <br> `delete name, delete slack, delete twitter`                                                                                                                 |
| **set**             | `set [name/NAME] [address/ADDRESS] [role/ROLE] [timezone/TIMEZONE] [phone/PHONE] [email/EMAIL] [slack/SLACK] [telegram/TELEGRAM] [tag/TAG]`<br> `set name/Tex address/Clementi`     |
| **find**            | `find` <br> `find Tex, find Engineer`                                                                                                                                               |
| **back**            | `back` or `<ESC>` key                                                                                                                                                               |
| **help**            | `help`                                                                                                                                                                              |
