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

### Searching by multiple fields: `find`

Finds entities from the current list based on multiple fields at a time such that the fields of the entity specified contains the respective keywords.

Format: `find PREFIX/KEYWORD [MORE PREFIX/KEYWORD]...`
* The input `PREFIXES` is case-sensitive and must be in lowercase. e.g. `n/hans e/notgmail.com`
* The input `KEYWORDS` is case-insensitive. e.g. `n/hans` will match a student named “Hans”
* Partial `KEYWORDS` will be matched e.g. `n/Ha` will match a student named “Hans Jones”
* The order of the `PREFIX/KEYWORD` pair does not matter. e.g. `n/Alice p/12345678` vs `p/12345678 n/Alice`
* All fields are optional, but at least one pair of `PREFIX/KEYWORD` must be specified.
* `KEYWORDS` must not contain the `/` character.
* If there are repeated `PREFIXES`, only the latest one will be taken.


Examples:
(insert screenshot of the current list being the student list)
* `find n/john s/evergarden` returns the students, `Johnny` and `John Doe`, from “Evergarden Secondary School” in the student list.

(insert screenshot of the current list being the class list)
* `find a/clementi e/example.com i/nus` returns the tutors who live in Clementi, have emails with domain name “example.com” and graduated from NUS.

(insert screenshot of the current list being the class list)
* `find l/p1math d/sunday #/difficult` returns all classes whose names contain “p1math”, conducted on Sundays and have the tag “difficult”.

### Assign class to a person: `assign`

Assign an existing tuition class to a specified student/tutor.

Format: `assign INDEX n/[class name]`

* `assign` command only works when the displayed list is a student or tutor list.
* The index refers to the index number shown in the displayed list.
* The index must be a positive integer 1, 2, 3, …
* Class name must be the name of a tuition class that already exists in the tuition class list.
* The tuition class to be assigned to the specified student/tutor must not have been assigned beforehand.

Examples:
* `assign 1 n/P3 maths`

### Unassign class from a person: `unassign`

Unassign an existing tuition class from a specified student/tutor.

Format: `unassign INDEX n/[class name]`

* `unassign` command only works when the displayed list is a student or tutor list.
* The index refers to the index number shown in the displayed list.
* The index must be a positive integer 1, 2, 3, …
* Class name must be the name of a tuition class that already exists in the tuition class list.
* The tuition class to be unassigned from the specified student/tutor must have been assigned to the 
student/tutor beforehand.

Examples:
* `unassign 1 n/P3 maths`

### Exporting address books to csv

Export Students, Tutors, and TuitionClasses into their own .csv files to be used in other programs.

Format: Click on the "*File*" tab at the top left hand corner of `myStudents` and click on "*Export*" in the dropdown menu.

* The csv files will be saved in the same location as the json files specified in `preference.json`.

###FAQs
**Q:** Does myStudent need an Internet connection?

**A:** No, myStudent is an offline software that does not need Internet connection to use.

**Q:** Can I move myStudent from one computer to another without loss of data?
**A:** Yes, by copying the home folder containing myStudent.jar to another computer, you could resume your work on another computer without loss of data.

### Troubleshooting

Warnings issued when Mac users are trying to open the software by double-clicking the icon.
“myStudents.jar” cannot be opened because it is from an unidentified developer.
Instead of double-clicking the software icon, you may want to right-click the icon and choose `Open`, then click `Open` in the pop-up window. Note that you only need to do this for the first time. For future usage, simply double-click the icon to launch the software.

Still unable to launch the software?
Make sure that you have installed `Java 11` or above by doing the following checking:

For Mac Users:
Open your terminal and type `java -version` and press `enter`. Information returned should show the current version of Java installed on your computer.

For Windows Users:
Open the command prompt and type `java -version` and press `enter`. Information returned should show the current version of Java installed on your computer.

If you do not have `Java 11` or above installed, please install from [here](https://www.oracle.com/sg/java/technologies/downloads/).
