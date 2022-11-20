---
layout: page
title: User Guide
---

## About BookFace
BookFace replaces a paper-based system or manual tracking of books, providing greater speed/efficiency in identifying where a book is, or when it is due. It also adds a member-tracking system to handle an increasing number of library members.

* Table of Contents
  {:toc}
    - [Quick Start](#quick-start)
    - [Features](#features)
        - [Add book](#adding-a-book--add-book)
        - [Add user](#adding-a-user--add-user)
        - [Delete book](#deleting-a-book--delete-book)
        - [Delete user](#deleting-a-user-delete-user)
        - [Loan book](#loaning-a-book--loan)
        - [Return book](#returning-a-book--return)
        - [Find book](#finding-books--find-book)
        - [Find user](#finding-users--find-user)
        - [Edit book](#editing-a-book--edit-book)
        - [Edit user](#editing-a-user--edit-user)
        - [List all books](#list-all-books--list-books)
        - [List all users](#list-all-users--list-users)
        - [List all books and users](#list-all-books-and-users--list-all)
        - [List loans](#show-all-books-that-are-loaned--list-loans)
        - [List overdue](#show-all-books-that-are-overdue--list-overdue)
        - [Clear](#clearing-all-entries--clear-all)
        - [Changing the Theme](#changing-the-theme)
        - [Exit](#exit-bookface--exit)
    - [FAQ](#faq)
    - [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick Start

<div markdown="block" class="alert alert-info" name="quickstart">

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `BookFace.jar` from [here](https://github.com/AY2223S1-CS2103-F14-4/tp/releases/tag/v1.4).

3. Copy the file to the folder you want to use as the _home folder_ for your BookFace.

4. Open command prompt/terminal within the same directory of the folder and type `java -jar BookFace.jar` to start the app. The GUI will appear similar to the image below. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list users`**

    * **`add user n/John Smith p/87006163 e/student123@gmail.com`**

    * **`delete user 1`**

    * **`clear all`**

    * **`exit`**

6. Refer to the [Features](#features) below for details of each command.
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add user n/NAME`, `NAME` is a parameter which can be used as `add user n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be ignored (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If parameter prefixes such as `n/` or `a/` appear at the start of a word, they will be interpreted as parameters even if they were intended to be a part of the person's name. v1.5 will include the ability to escape prefixes.<br>
  e.g `a/John Doe t/The Wide a/iger` will be interpreted as "iger" for the author's name and "The Wide" for the book's title, instead of "John Doe" for the author's name and "The Wide a/iger" for the book title.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list users`, `exit` and `clear all`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Note that indexes always refer to the currently displayed user/book list and not the original user/book list. <br>
  e.g. if your user list has 5 users, and you enter `find user Alex` and get 1 user displayed under the user list, `delete user 1` will always
delete the user that is currently displayed.

* Any whitespace between the start of a parameter prefix such as `n/` and the end of the preceding parameter will be ignored.<br>e.g. the parameter `p/` in `p/999999      n/John` will be interpreted as `999999` and not `999999      `.
</div>

<div style="page-break-after: always;"></div>

### Adding a book : `add book`

Adds a book to the library.

Format: `add book t/TITLE a/AUTHOR`

* The title **must only contain alphanumeric characters, punctuations and spaces**, while the author **must only contain alphanumeric characters and spaces**.
* If the specified title and author match an existing book (ignoring the title's case), the book will be flagged as a duplicate of an existing book.

Examples:
* `add book t/The Life of John a/Emily Dunce`

### Adding a user : `add user`

Adds a user to the library.

Format: `add user n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]...`

Examples:
* `add user n/Jenny Brown p/12345678 e/foo@gmail.com`

### Deleting a book : `delete book`

Deletes a book from the library. If the book is on loan, it must be returned before deletion.

Format: `delete book INDEX`

* Deletes the book at the specified `INDEX`. The index refers to the index number shown in the displayed book list. The index **must be a positive integer** 1, 2, 3,

Examples:
* `delete book 99`

<div style="page-break-after: always;"></div>

### Deleting a user: `delete user`

Deletes a user from the library. If the user has any loans, they must be returned before deletion.

Format: `delete user INDEX`

* Deletes the user at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3,

Examples:
*  `delete user 12`

### Loaning a book : `loan`

Loans a book to some user, which has a due date.

Format: `loan USER_INDEX BOOK_INDEX [DUE_DATE]`

* Loans the book to some user at their respective specified `INDEXES`.
* The indexes refer to the index number shown in the displayed user and book list respectively.
* The indexes **must be a positive integer** 1, 2, 3, …​
* The respective specified `INDEXES` **must be present in their lists**.
* The books that are loaned out will appear at the top of the book list after relaunching the app.
* If due date is not specified, a default due date of 14 days from today is set when the book is loaned out.
* Due date formats such as `dd/MM/yyyy`, `yyyy-MM-dd` or even text such as `next sunday`, `tomorrow` or `two mondays ago` would work.
* Compound/complex statements or other date formats not stated below may not work properly.  (Fix coming in v1.5)
Eg. `one month and two weeks later` or `2022-10-31 next sunday` will not work properly.
* Some invalid due date inputs in February may be assumed to be correct. Refer to example below.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
{DAY} refers to Sunday, Monday...Saturday or day(s)/week(s)/month(s)/year(s)

Date formats that will work:
dd/MM/yyyy, yyyy-MM-dd, next {DAY}, following {DAY}, last {DAY}, past {DAY}, yesterday, today, tomorrow, (any positive integer) {DAY} later,
(any positive integer) {DAY} ago.
</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Next strictly refers to next week while following refers to the upcoming weekday/weekend.
Eg. On Monday, "next wednesday" refers to nine days later while "following wednesday" refers to two days later.

* Last strictly refers to last week while past refers to the last occurrence of weekday/weekend.
Eg. On Wednesday, "last monday" refers to nine days ago while "past monday" refers to two days ago.

* Later and ago when used for Sunday, Monday...Saturday refers to the number of Sunday, Monday...Saturday that has passed/yet to pass.
Eg. On Monday, "two sundays ago" refers to 8 days ago while "two tuesdays ago" refers to 13 days ago.

</div>

Examples:
* `loan 3 2` loans the second book in the book list to the third user in the user list. The due date is set to
  14 days from today.
* `loan 3 2 2018-08-08` loans the second book in the book list to the third user in the user list. The due date is set to
  2018-08-08.
* `loan 3 2 tomorrow` loans the second book in the book list to the third user in the user list. The due date is set to
  tomorrow (with respect to system time).
* `loan 3 2 31/02/2022` loans the second book in the book list to the third user in the user list. The due date is set to
  2022-02-28 as it is reasonably assumed that the last day in February is meant for the due date.

### Returning a book : `return`

Returns the book which is loaned by some user.

Format: `return INDEX`

* Returns the book which is loaned by some user at the book's specified `INDEX`.
* The index refers to the index number shown in the displayed book list respectively.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `return 2` returns book number two (which has been previously loaned).

<div style="page-break-after: always;"></div>

### Finding books : `find book`

Finds a book using keywords.

Format: `find book KEYWORD [KEYWORD]...`

* Find books that matches the searched keywords for either title or author.
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

Format: `find user KEYWORD [KEYWORD]...`

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

<div style="page-break-after: always;"></div>

### Editing a book : `edit book`

Edits a book in the library.

Format: `edit book INDEX (must be a positive integer) [t/TITLE] [a/AUTHOR]`

Examples:
* `edit book 7 t/The Broken House`

### Editing a user : `edit user`

Edits a user who is registered with the library.

Format: `edit user INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]...`
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the Edit command:**<br>
* Although the format specifies that the fields are optional, the command still requires 1 field to be provided in order for it to work
* If you are inserting additional tags to a user, you must list all the present tags first since the command overrides the current tags with the ones in the Edit command
</div>

Examples:
* `edit user 1 p/91234567 e/johndoe@example.com`

### List all books : `list books`

Shows a list of all books in BookFace.

Format: `list books`

### List all users : `list users`

Shows a list of all users in BookFace.

Format: `list users`

### List all books and users : `list all`

Shows all users and books

Format: `list all`

### Show all books that are loaned : `list loans`

Lists all the books that are currently loaned out and the people who loaned them.

Format: `list loans`

### Show all books that are overdue : `list overdue`

Lists all the books that are overdue and the people who loaned them.

Format: `list overdue`

### Clearing all entries : `clear all`

Clears all book and user entries from BookFace.

Format: `clear all`

### Changing the Theme

Changes the theme of BookFace.

Select either the `Light` or `Dark` theme by clicking on `Theme` in the menu bar.

### Exit BookFace : `exit`

Exit the BookFace program.

Format: `exit`

### Saving the data

BookFace data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

BookFace data are saved as a JSON file `[JAR file location]/data/bookface.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, BookFace will discard all data, log a warning about the invalid format and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ


**Q**: How do I clear the sample data on BookFace?<br>
**A**: Simply use the `clear all` command.

**Q**: Is it normal for the order of the books to change upon restarting the application?<br>
**A**: Yes; it is a perfectly normal behaviour for the application **if there are loaned books in the book list**, as the intention is to show books that have been loaned as a priority in the list upon starting the app. Following this answer, the order of the book list does not immediately change after a `loan` or `return` command in order to prevent confusion in the sudden reordering of books while the app is in use.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

| Action          | Format, Examples                                                                                                              |
|-----------------|-------------------------------------------------------------------------------------------------------------------------------|
| **Add book**    | `add book t/TITLE a/AUTHOR` <br> E.g: `add book t/James and The Giant Peach  a/Roald Dahl`                                    |
| **Add user**    | `add user n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]...` <br> E.g: `add user n/John Doe p/91234567 e/johndoe@outlook.com t/friend` |
| **Delete book** | `delete book BOOK_INDEX`<br> E.g: `delete book 1`                                                                             |
| **Delete user** | `delete user USER_INDEX`<br> E.g: `delete user 1`                                                                             |
| **Loan book**   | `loan USER_INDEX BOOK_INDEX [DUE_DATE]` <br> E.g: `loan 1 1` or `loan 1 1 2022-12-28`                                         |
| **Return book** | `return BOOK_INDEX`<br> E.g: `return 1`                                                                                       |
| **Find book**   | `find book KEYWORD [KEYWORD]...` <br> E.g: `find book Peach`                                                                  |
| **Find user**   | `find user KEYWORD [KEYWORD]...` <br> E.g: `find user John Sim`                                                               |
| **Edit book**   | `edit book BOOK_INDEX [t/TITLE] [a/AUTHOR]` <br> E.g: `edit book 7 t/The Broken House`                                        |
| **Edit user**   | `edit user USER_INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]...` <br> E.g: `edit user 1 p/91234567 e/johndoe@example.com`       |
| **List books**  | `list books`                                                                                                                  |
| **List users**  | `list users`                                                                                                                  |
| **List all**    | `list all`                                                                                                                    |
| **List loans**  | `list loans`                                                                                                                  |
| **List overdue**| `list overdue`                                                                                                                |
| **Clear**       | `clear all`                                                                                                                   |
| **Exit**        | `exit`                                                                                                                        |
