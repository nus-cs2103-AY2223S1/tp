---
layout: page
title: User Guide
---

myStudents is **a desktop app for managing students of a tuition center, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, myStudents can get your student management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------


## Features

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add [student/tutor] n/[name] p/[phone_number] a/[address]`

Examples:
* `add student n/John Doe p/98765432 a/John street, block 123, #01-01`
* `add tutor n/Betsy Crowe p/1234567 a/Newgate`

### Adding a class: `add`

Adds a class to the database

Format: `add class n/[name] s/[subject] t/[time]`

Examples:
* `add class n/CS2103-F12 s/Computer Science t/Friday 12pm`

### Listing all persons : `list`

Shows a list of all specified entities in the address book.

Format: `list [student/tutor/class]`
* If no entity type is given, all entities in the database will be listed.

### Deleting an entity: `delete`

Deletes the specified entity form the database

Format: `delete [student/tutor/class] INDEX`

* The index refers to the index number shown in the displayed list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `delete student 2`
* `delete tutor 1`
* `delete class 1`

### Searching by name: `find`

Finds entities whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g. hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only the name is searched.
* Only full words will be matched e.g. Han will not match Hans
* Persons or classes matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will return `Hans Gruber, Bo Yang`

Examples:
* `find John` returns `John` and `John Doe`
* `find alex david` returns `Alex Yeoh` and `David Li`
