---
layout: page
title: User Guide
---

### Show all books that are loaned : `list loans`

Lists all the books that are currently loaned out.

Format: `list loans`

### Clearing all entries : `clear all`

Clears all book and user entries from BookFace.

Format: `clear all`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

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
=======
