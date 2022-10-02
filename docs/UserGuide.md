---
layout: page
title: User Guide
---

# GithubContact

GithubContact is **an address book integrated with Github's Public API, targeted at programmers to help them communicate and collaborate smoothly.**

--------------------------------------------------------------------------------------------------------------------

## Features

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
