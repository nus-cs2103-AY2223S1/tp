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

Adds a person to the database.

Format:

`add student n/<name> p/<phone> e/<email> a/<address> s/<school> l/<level> [#/<tag>]`

`add tutor n/<name> p/<phone> e/<email> a/<address> q/<qualification> i/<institution> [#/<tag>]`

Examples:
* `add student n/John Doe p/98765432 e/johndoe@example.com a/John street, block 123, #01-01 s/Example Primary School l/Primary 3 #/badBoy`
* `add tutor n/Betsy Crowe p/1234567 e/betsycrowe@example.com a/Newgate st, block 123, #01-01 q/MSc, Master of Science i/National University of Singapore #/mostLiked`

### Adding a class: `add`

Adds a class to the database.

Format:

`add class n/<name> s/<subject> l/<level> d/<day> t/<time> [#/<tag>]`

Examples:
* `add class n/P2-MATH-F12 s/Math l/Primary 2 d/Friday t/12:00 - 14:00`
* `add class n/S1-ENG-T10 s/Math l/Secondary 1 d/Thursday t/10am to 12pm`

### Editing an entity: `edit`

Edits an entity in the database. 

Format:

`edit 1 [n/<name>] [p/<phone>] [e/<email>] [a/<address>] [s/<school>] [l/<level>] [#/<tag>]`

`edit 1 [n/<name>] [p/<phone>] [e/<email>] [a/<address>] [q/<qualification>] [i/<institution>] [#/<tag>]`

`edit 1 [n/<name>] [s/<subject>] [l/<level>] [d/<day>] [t/<time>] [#/<tag>]`

* Arguments that are valid depends on which list is being displayed currently.
* Only arguments specified will overwrite the existing values.

Examples:
* edit 1 n/Tom Doe
* edit 2 l/Primary 5 d/Monday

### Listing all persons : `list`

Shows a list of the specified entities in the database.

Format: `list <entity>`

Examples:
* `list student`
* `list tutor`
* `list class`

### Deleting an entity: `delete`

Deletes the specified entity from the current displayed list.

Format: `delete <index>`

* The index refers to the index number shown in the displayed list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `delete 2`

### Clears list: `clear`

Clears the current displayed list of its contents.

Format: `clear`

* Take note of which list you are clearing.

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

### Add next of kin to a student: `nok`

Adds a next of kin to an existing student in the current list.

Format: 

`nok <index>`

`nok <index> n/<name> p/<phone> e/<email> a/<address> r/<relationship> [#/tag]`

* The index refers to the index number shown in the displayed list.
* This command only works when the displayed list is listing students.
* Not specifying any arguments after index will remove the next of kin from the student of that index.

Examples:

* `nok 1 n/Mama Doe p/87654321 e/mamadoe@example.com a/John street, block 123, #01-01 r/Mother #/bestMomAward`

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
