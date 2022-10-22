---
layout: page
title: User Guide
---

# GithubContact

GithubContact is an address book **integrated with Github's Public API**, targeted at programmers to help them communicate and collaborate smoothly.

--------------------------------------------------------------------------------------------------------------------

## Features

### Adding a person : `add`

Adds person to list and shows contact information page, where attributes can be added and set.

Format: `add name/NAME address/ADDRESS [role/ROLE] [timezone/TIMEZONE] [phone/PHONE] [email/EMAIL] [slack/SLACK] [telegram/TELEGRAM] [tag/TAG] `

Example:
- `add name/John Doe address/27 Clementi`
- `add name/Alex address/22 Clementi phone/86609830 email/alex@gmail.com`
- `add name/Mike address/21 Clementi phone/86609831 email/mike@gmail.com slack/mike123 telegram/@mike123`

### Show Person Details: `<ENTER>` or double click

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

### Help Command : `help`

Shows help page.

Format: `help`

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action               | Format, Examples                                                                                                                                                                    |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **add**              | `add name/NAME address/ADDRESS [role/ROLE] [timezone/TIMEZONE] [phone/PHONE] [email/EMAIL] [slack/SLACK] [telegram/TELEGRAM] [tag/TAG]`<br> `add name/John Doe address/27 Clementi` |
| **delete person**    | `delete INDEX` <br> `delete 1, delete 2`                                                                                                                                            |
| **delete attribute** | `delete ATTRIBUTE` <br> `delete name, delete slack, delete twitter`                                                                                                                 |
| **set**              | `set [name/NAME] [address/ADDRESS] [role/ROLE] [timezone/TIMEZONE] [phone/PHONE] [email/EMAIL] [slack/SLACK] [telegram/TELEGRAM] [tag/TAG]`<br> `set name/Tex address/Clementi`     |
| **help**             | `find` <br> `find Tex, find Engineer`                                                                                                                                               |
| **help**             | `help`                                                                                                                                                                              |
