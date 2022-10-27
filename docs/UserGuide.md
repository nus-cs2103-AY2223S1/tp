---
layout: page
title: User Guide
---

## About BookFace
BookFace replaces a paper-based system or manual tracking of books, providing greater speed/efficiency in identifying where a book is, or when it is due. It also adds a member-tracking system to handle an increasing number of library members.

* Table of Contents
  {:toc}
    - [Quick Start](#quickstart)
    - [Features](#features)
        - [Add book](#adding-a-book-add-book)
        - [Add user](#adding-a-user-add-user)
        - [Remove book](#removing-a-book--delete-book)
        - [Remove user](#removing-a-user-delete-user)
        - [Return book](#returning-a-book-return)
        - [Loan book](#loaning-a-book--loan)
        - [Find book](#finding-books--find-book)
        - [Find user](#finding-users--find-user)
        - [List all users](#list-all-users--list-users)
        - [List all books](#list-all-books--list-books)
        - [List all loans](#show-all-books-that-are-loaned--list-loans)
        - [Clear](#clearing-all-entries--clear-all)
        - [Exit](#exit-bookface-exit)
    - [FAQ](#faq)
    - [Command Summary](#commandsummary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

<div markdown="block" class="alert alert-info" name="quickstart">

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `BookFace.jar` from here.

1. Copy the file to the folder you want to use as the _home folder_ for your BookFace.

1. Double-click the file to start the app. The GUI is similar to the image below. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** :

    * **`add`** :

    * **`delete`**`3` :

    * **`clear`** :

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.
</div>

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list users`, `exit` and `clear all`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Note that indexes always refer to the currently displayed user/book list and not the original user/book list. <br>
  e.g. if your user list has 5 users, and you enter `find user Alex` and get 1 user displayed under the user list, `delete user 1` will always
delete the user that is currently displayed.

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

### Removing a user: `delete user`

Deletes a user from the library.

Format: `delete user <user index>`

* Deletes the user at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3,

Examples:
*  `delete user 12`

### Returning a book: `return`

Returns the book which is loaned by some user.

Format: `return <book index>`

* Returns the book which is loaned by some user at the book's specified `INDEX`.
* The index refers to the index number shown in the displayed book list respectively.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `return 2` returns book number two (which has been previously loaned).

### Loaning a book : `loan`

Loans a book to some user, which has a due date.

Format: `loan <user index> <book index>` or `loan <user index> <book index> <due date>`

* Loans the book to some user at their respective specified `INDEXES`.
* The indexes refer to the index number shown in the displayed user and book list respectively.
* The indexes **must be a positive integer** 1, 2, 3, …​
* The respective specified `INDEXES` **must be present in their lists**.
* The books that are loaned out will appear at the top of the book list.
* The first `loan` format without specifying a due date sets a default due date of 14 days from today when the book is loaned out.
* The second `loan` format allows for specification of due dates, and date formats such as
  `dd/MM/yyyy`, `yyyy-MM-dd` or even text such as `next sunday` or `tomorrow` would work. Only the
  first date entered would be set as the due date and subsequent dates entered would be ignored.
* For the second `loan` format, some invalid inputs in February may be assumed to be correct. Refer to example below.

Examples:
* `loan 3 2` loans the second book in the book list to the third user in the user list. The due date is set to
  14 days from today.
* `loan 3 2 2018-08-08` loans the second book in the book list to the third user in the user list. The due date is set to
  2018-08-08.
* `loan 3 2 tomorrow` loans the second book in the book list to the third user in the user list. The due date is set to
  tomorrow (with respect to system time).
* `loan 3 2 31/02/2022` loans the second book in the book list to the third user in the user list. The due date is set to
  2022-02-28 as it is reasonably assumed that the last day in February is meant for the due date.

### Finding books : `find book`

Finds a book using keywords. 

Format: `find book <keywords>`

* Finds books that matches the searched keywords for either title or author.
* The search is case-insensitive. <br>
e.g. `computer` will find `Computer`
* The keywords do not need to be an exact match of the title or author. <br>
e.g. `rith` will find `algorithms`
* The search will return all books that match at least one keyword. <br>
e.g. `Introduction to` will find `Introduction for dummies` and `How to Cook`   

Examples:
* `find book ss` will find `Ulysses` and `Darkness within`.
* `find book under the` will find `Undercover` and `The Grapes of Wrath`.

### Finding users : `find user`

Finds a user using keywords.

Format: `find user <keywords>`

* Finds users that matches the searched keywords for name.
* The search is case-insensitive. <br>
  e.g. `john` will find `John`
* The keywords do not need to be an exact match of the name. <br>
  e.g. `enc` will find `spencer`
* The search will return all books that match at least one keyword. <br>
  e.g. `Steven Koh` will find `Steven Low` and `Koh Yew Ying`

Examples:
* `find user wa` will find `Mohammad Rizwan` and `Wallace Andrew`.
* `find user John Sim` will find `John Goh` and `Sim Chee Ming`.

### List all users : `list users`

Shows a list of all users in BookFace.

Format: `list users`

### List all books : `list books`

Shows a list of all books in BookFace.

Format: `list books`

### Show all books that are loaned : `list loans`

Lists all the books that are currently loaned out.

Format: `list loans`

### Clearing all entries : `clear all`

Clears all book and user entries from BookFace.

Format: `clear all`

### Exit BookFace: `exit`

Exit the BookFace program.

Format: `exit`

### Saving the data

BookFace data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

BookFace data are saved as a JSON file `[JAR file location]/data/bookface.json`. Advanced users are welcome to update
data
directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, BookFace will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ


**Q**: How do I clear the sample data on BookFace?<br>
**A**: Simply use the `clear all` command.

--------------------------------------------------------------------------------------------------------------------

## Command summary

<div markdown="block" class="alert alert-info" name="commandsummary">

| Action          | Format, Examples                                                                                                          |
|-----------------|---------------------------------------------------------------------------------------------------------------------------|
| **Add book**    | `add book t/TITLE a/AUTHOR` <br> E.g: `add t/James and The Giant Peach  a/Roald Dahl`                                     |
| **Add user**    | `add user n/NAME p/PHONE_NUMBER e/EMAIL` <br> E.g: `add user n/John Doe p/91234567 e/johndoe@outlook.com`                 |
| **Clear**       | `clear all`                                                                                                               |
| **Delete book** | `delete book BOOK_INDEX`<br> E.g: `delete book 1`                                                                         |
| **Delete user** | `delete user USER_INDEX`<br> E.g: `delete user 1`                                                                         |
| **Return book** | `return BOOK_INDEX`<br> E.g: `return 1`                                                                                   |
| **Loan book**   | `loan USER_INDEX BOOK_INDEX` or `loan <user index> <book index> <due date>` <br> E.g: `loan 1 1` or `loan 1 1 2022-12-28` |
| **List users**  | `list users`                                                                                                              |
| **List books**  | `list books`                                                                                                              |
| **List loans**  | `list loans`                                                                                                              |
| **Exit**        | `exit`                                                                                                                    |
</div>
