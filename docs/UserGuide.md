---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

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

### Adding a book: `add book`

Adds a book to the library.

Format: `add book t/<title> a/<author>`

* The title itself cannot contain “a/”, as “a/” marks the start of the author field

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0).
</div>

Examples:
* `add book t/The Life of John a/Emily Dunce`

### Adding a user: `add user`

Adds a user to the library.

Format: `add user n/<name> p/<phone number> e/<email>`

* The name itself cannot have a “p/” and a "e/".

Examples:
* `add user n/Jenny Brown p/12345678 e/foo@gmail.com`

### Removing a book : `delete book`

Deletes a book from the library.

Format: `delete book <book index>`

* Deletes the book at the specified `INDEX`. The index refers to the index number shown in the displayed book list. The index **must be a positive integer** 1, 2, 3,

Examples:
* `delete book 99`

### Remove a user: `delete user`

Deletes a user from the library.

Format: `delete user <user index>`

* Deletes the user at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3,

Examples:
*  `delete user 12`

### Returning a book: `return`

Returns the book which is loaned by some user.

Format: `return <user index> <book index>`

* Returns the book which is loaned by some user at their respective specified `INDEXES`.
* The indexes refer to the index number shown in the displayed user and book list respectively.
* The indexes **must be a positive integer** 1, 2, 3, …​

Examples:
* `return 3 2` returns book number two which is loaned by user number three.

### Loaning a book : `loan`

Loans a book to some user

Format: `loan <user index> <book index>`

* Loans the book to some user at their respective specified `INDEXES`.
* The indexes refer to the index number shown in the displayed user and book list respectively.
* The indexes **must be a positive integer** 1, 2, 3, …​

Examples:
* `loan 3 2` loans the second book in the book list to the third user in the user list.

### List all users : `list users`

Shows a list of all users in the address book.

Format: `list users`

### Show all books that are loaned : `list loans`

Lists all the books that are currently loaned out.

Format: `list loans`

### Clearing all entries : `clear all`

Clears all book and user entries from BookFace.

Format: `clear all` 

### List all books : `list books`

Shows a list of all books in the address book.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I clear the sample data on BookFace?<br>
**A**: Simply use the `clear all` command.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action          | Format, Examples                                                                                                |
|-----------------|-----------------------------------------------------------------------------------------------------------------|
| **Add book**    | `add book [t/TITLE] [a/AUTHOR]` <br> E.g: `add n/James and The Giant Peach  a/Roald Dahl`                       |
| **Add user**    | `add user [n/NAME] [p/PHONE_NUMBER] [e/EMAIL]` <br> E.g: `add user n/John Doe p/91234567 e/johndoe@outlook.com` |
| **Clear**       | `clear all`                                                                                                     |
| **Delete book** | `delete book BOOK_INDEX`<br> E.g: `delete book 1`                                                               |
| **Delete user** | `delete user USER_INDEX`<br> E.g: `delete user 1`                                                               |
| **Return book** | `return USER_INDEX BOOK_INDEX`<br> E.g: `return 1 1`                                                            |
| **Loan book**   | `loan USER_INDEX BOOK_INDEX`<br> E.g: `loan 1 1`                                                                |
| **List users**  | `list users`                                                                                                    |
| **List books**  | `list books`                                                                                                    |
| **List loans**  | `list loans`                                                                                                    |
| **Exit**        | `exit`                                                                                                          |
