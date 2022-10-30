---
layout: page
title: User Guide
---

# ConnectNUS User Guide

ConnectNUS is a **desktop app for managing contacts, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ConnectNUS can get your contact management tasks done faster than traditional GUI apps.

## Table of Contents
- [Features](#features)
  - [Data features](#data-features)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
  - [User profile features](#user-features)
    - [Adding user](#adding-user)
    - [Editing user](#editing-user)
    - [Deleting user](#deleting-user)
  - [Address book features](#address-book-features)
    - [Adding contacts](#adding-contacts)
    - [Listing contacts](#listing-contacts)
    - [Finding contacts](#finding-contacts)
    - [Editing contacts](#editing-contacts)
    - [Deleting contacts](#deleting-contacts)
    - [Clearing all contacts](#clearing-all-contacts)
  - [Module features](#module-features)
    - [Editing user's modules](#edit-users-modules)
    - [Editing contact's modules](#edit-contacts-modules)
    - [Checking modules left](#checking-modules-left)
    - [Next Semester](#next-semester)
  - [Timetable features](#timetable-features)
    - [Adding lessons](#adding-lessons)
    - [Listing lessons / Viewing timetable](#listing-lessons--viewing-timetable)
    - [Deleting lessons](#deleting-lessons)
  - [Filtering features](#filtering-features)
    - [Filtering Tags](#filtering-tags)
    - [Filtering Current Modules](#filtering-current-modules)
    - [Filtering Planned Modules](#filtering-planned-modules)
    - [Filtering Previous Modules](#filtering-previous-modules)
  - [Other features](#other-features)
    - [Undo / Redo](#undo--redo--undo-redo)
    - [Viewing help](#viewing-help)
    - [Exiting the program](#exiting-the-program--exit)
- [Command Summary](#command-summary)

---

## Features

** Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user. \
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
* Items in square brackets are optional. \
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
* Items with `…`​ after them can be used multiple times including zero times. \
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.
* Parameters can be in any order. \
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken. \
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored. \
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

## Data features

### Saving the data

ConnectNUS data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Editing the data file

ConnectNUS data is saved as a JSON file `[JAR file location]/data/connectnus.json`. Advanced users are welcome to update data directly by editing that data file.



**Caution:** If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.

[Scroll back to top](#table-of-contents)

## User features

### Adding user

Add your own user profile into the contact list.

Format: `user n/[your name] p/[your phone number] e/[your email] a/[your address] [g/your github username] [curr/current module]… [prev/previous module]… [plan/future module]…`



* Adds your own user profile with specified details into your contact list.
* There can only be one user, adding another user profile while there is one already stored will result in an error message.
* Current user profile has to be deleted before a new one is added.
* You are able to specify the modules you are taking currently, have taken in the past, and are planning to take in the future.
* Each of these module codes should be registered modules in NUS.

Examples:


* `user n/Silas p/98765432 e/silastay@gmail.com a/Kent Ridge Drive g/SilasTSL curr/CS2100 prev/CS1101S plan/CS2109`adds your own user profile to your contact list with all the details specified.

[Scroll back to top](#table-of-contents)

### Editing user

Edit the current user profile.

Format: `edit user [n/your name] [p/your phone number] [e/your email] [a/your address] [g/your github username]`



* Edits the user profile with specified details.
* You need to edit at least one field
* You may edit multiple fields at once
* You cannot edit modules with this command, you must do so with the module commands.

Examples:


* `edit user p/92323232 a/Kent Ridge Crescent`edits your own user profile with all the details specified.

[Scroll back to top](#table-of-contents)

### Deleting user

Deletes the current user profile from the contacts list

Format: `delete user`



* Deletes the current user profile stored in the contact list.

[Scroll back to top](#table-of-contents)

## Address book features

### Adding contacts

Add a contact to your contact list.

Format: `add n/[contact name] p/[contact phone number] e/[contact email] a/[contact address] [g/contact github username] [t/contact tag]… [curr/current module]… [prev/previous module]… [plan/future module]…`



* Adds contact with specified details into your contact list.
* You can add as many tags to a contact as you want, you may also choose not add any tags
* You are able to specify the modules the contact is taking currently, taken in the past, and is planning to take in the future.
* Each of these module codes should be registered modules in NUS.

Examples:



* `add n/Ruijie p/91234567 e/kohrj@gmail.com a/Kent Ridge Avenue g/rjkoh t/Classmate curr/CS2100 prev/CS1101S plan/CS2109`adds module contact Ruijie to your contact list with all the details specified.

[Scroll back to top](#table-of-contents)

### Listing contacts

Shows a list of all persons in the address book.

Format: `list`



* The list of contacts is numbered based on the order that the contacts are added

[Scroll back to top](#table-of-contents)

### Finding contacts

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]…`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

* `find John` returns john and John Doe
* `find alex david` returns `Alex Yeoh`, `David Li`

[Scroll back to top](#table-of-contents)

### Editing contacts

Edit a specified contact from the user’s contacts list.

Format: `edit INDEX [n/contact name] [p/contact phone number] [e/contact email] [a/contact address] [g/contact github username] [t/contact tag]…`



* Edits the contact at the specified `INDEX` with specified details.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* You need to edit at least one field
* You may edit multiple fields at once
* You can add as many tags to a contact as you want, you may also choose not add any tags
* You cannot edit modules with this command, you must do so with the module commands.

Examples:


* `edit 2 n/Laura Lee e/laura@nus.sg t/Hall`edits the second contact in the list of all contacts with all the details specified.

[Scroll back to top](#table-of-contents)

### Deleting contacts

Deletes a specified contact from the user’s contacts list.

Format: `delete INDEX`



* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:



* `list `followed by  `delete 1` deletes the first contact in the list of all contacts the user has added.

[Scroll back to top](#table-of-contents)

### Clearing all contacts

Deletes all contacts from the contact list with one command

Format: `clear`

* Every contact in the address book including their information is cleared.
* User profile does not get deleted

[Scroll back to top](#table-of-contents)

## Module features

### Edit user's modules

Directly edit your own module list by adding or deleting modules from the list.

Format: `module user [curr/current module]… [prev/previous module]… [plan/future module]… [rm/module to remove]…`


* You need to edit at least one list
* You may edit multiple lists at once
* Add a module with module code [module code] to the list of mods.
* You are able to specify the modules you are taking currently, taken in the past, and is planning to take in the future.
* The module code refers to the module code of the module the user is planning to take (E.g. CS2103T)
* The module code should be a registered module in NUS.
* Using `rm/module to remove` feature will remove the module from all 3 module lists.

Examples:



* `module user curr/CS2100 prev/CS1231S plan/CS2106 rm/ST2334` adds the first 3 modules into your respective lists and removes ST2334 from all module lists

[Scroll back to top](#table-of-contents)

### Edit contact's modules

Directly edit your contact's module list by adding or deleting modules from their list.

Format: `module INDEX [curr/current module]… [prev/previous module]… [plan/future module]… [rm/module to remove]…`



* Edits the module list of the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* You need to edit at least one list
* You may edit multiple lists at once
* Add a module with module code [module code] to the list of mods.
* You are able to specify the modules you are taking currently, taken in the past, and is planning to take in the future.
* The module code refers to the module code of the module the user is planning to take (E.g. CS2103T)
* The module code should be a registered module in NUS.
* Using `rm/(module to remove)` feature will remove the module from all 3 module lists.

Examples:



* `module 1 curr/CS2101 prev/CS2030S plan/CS2102 rm/GEA1000` adds the first 3 modules into the first contact's respective lists and removes GEA1000 from all module lists

[Scroll back to top](#table-of-contents)

### Checking Modules Left

Check which modules you need to clear in order to fulfill core requirements or focus area requirements.

Format: `modsleft INDEX`



* Compares your Current Modules and Previous Modules list with the list of modules required to clear requirements and outputs the remaining modules needed to clear it.
* The index refers to the index number shown in the displayed focus area list.
* The index **must be a positive integer between 1 and 11**

The index reference list is as shown:

1. CS Core Requirements
2. Algorithms & Theory
3. Artificial Intelligence
4. Computer Graphics and Games
5. Computer Security
6. Database Systems
7. Multimedia Information Retrieval
8. Networking and Distributed Systems
9. Parallel Computing
10. Programming Languages
11. Software Engineering

Examples:

* `modsleft 1` outputs the list of remaining CS Core Requirement modules that you have yet to clear.

[Scroll back to top](#table-of-contents)

### Next Semester

Shifts the current modules in both the user and all persons in the contact list into their previous module during a new semester.

Format: `nextsem`

[Scroll back to top](#table-of-contents)

## Timetable features

### Adding lessons

Add lessons to the user profile or contacts.

Format for user: `lesson user l/[type] m/[module] day/[day number] start/[start time] end/[end time]`

Format for contact: `lesson INDEX l/[type] m/[module] day/[day number] start/[start time] end/[end time]`



* Adds the lesson to the user or specified `INDEX`.
  * The index refers to the index number shown in the displayed contact list.
  * The index **must be a positive integer** 1, 2, 3, …​
* `type` is the type of lesson
  * `tut` for Tutorial, `lec` for Lecture, `rec` for Recitation and `lab` for Lab.
* Day is a **positive integer between 1 to 7**, where 1 is for Monday and 7 is for Sunday.
* Start and End time in HH:mm format.

Examples:

`lesson user l/tut m/CS2103T d/1 start/12:00 end/13:00`

`lesson 2 l/lec m/CS2101 d/5 start/09:00 end/11:00`

[Scroll back to top](#table-of-contents)

### Listing lessons / Viewing timetable

Shows the list of lessons in a separate window, sorted by day and time.

Format for user: `timetable user`

Format for contact: `timetable INDEX`



* Shows the timetable of the user or specified `INDEX`.
  * The index refers to the index number shown in the displayed contact list.
  * The index **must be a positive integer** 1, 2, 3, …​
* Must have at least one lesson added for the week.

[Scroll back to top](#table-of-contents)

### Deleting lessons

Deletes a lesson from the user or a contact.

Format for user: `remove user l/[type] m/[module] day/[day number] start/[start time] end/[end time]`

Format for contact: `remove INDEX l/[type] m/[module] day/[day number] start/[start time] end/[end time]`



* Deletes the lesson from the user or specified `INDEX`.
  * The index refers to the index number shown in the displayed contact list.
  * The index **must be a positive integer** 1, 2, 3, …​
* `type` is the type of lesson
  * `tut` for Tutorial, `lec` for Lecture, `rec` for Recitation and `lab` for Lab.
* Day is a **positive integer between 1 to 7**, where 1 is for Monday and 7 is for Sunday.
* Start and End time in HH:mm format.
* All fields must match lesson previously added.

Examples:

`remove user l/lec m/CS2109S d/5 start/10:00 end/12:00`

`remove 1 l/rec m/CS2100 d/2 start/16:00 end/18:00`

`remove user l/lab m/CS2040S d/3 start/12:00 end/14:00`

`remove 8 l/tut m/CS2103T d/4 start/14:00 end/15:00`

[Scroll back to top](#table-of-contents)

## Filtering features

### Filtering Tags

Filters the tags of contacts to return a list of persons with any of the tags specified by the user.

Format: `filtertag [tag names]`

* Filters the contact list for person's with any of the specified `tag names`.

Examples:

`filtertag family`

`filtertag colleagues`

`filtertag friends family`

[Scroll back to top](#table-of-contents)

### Filtering Current Modules

Filters the current modules of contacts to return a list of persons with any of the current modules specified by the user.

Format: `filtercurrmod [current module names]`

* Filters the contact list for person's with any of the specified `current module names`.

Examples:

`filtercurrmod CS2103T`

`filtercurrmod CS2100`

`filtercurrmod CS2103T CS2100`

[Scroll back to top](#table-of-contents)

### Filtering Previous Modules

Filters the previous modules of contacts to return a list of persons with any of the previous modules specified by the user.

Format: `filterprevmod [previous module names]`

* Filters the contact list for person's with any of the specified `previous module names`.

Examples:

`filterprevmod CS2103T`

`filterprevmod CS2100`

`filterprevmod CS2103T CS2100`

[Scroll back to top](#table-of-contents)

### Filtering Planned Modules

Filters the planned modules of contacts to return a list of persons with any of the planned modules specified by the user.

Format: `filterplanmod [planned module names]`

* Filters the contact list for person's with any of the specified `planned module names`.

Examples:

`filterplanmod CS2103T`

`filterplanmod CS2100`

`filterplanmod CS2103T CS2100`

[Scroll back to top](#table-of-contents)

## Other features

### Undo / Redo : `Undo` `Redo`

Undo / Redo your latest action.

Format: `undo` `redo`

### Viewing help

Shows a message explaining how to access the help page.

<img src="images/HelpWindow.png" width="400" />

Format: `help`


### Exiting the program : `exit`

Exits the program.

Format: `exit`

[Scroll back to top](#table-of-contents)

## Command summary


<table>
  <tr>
   <td><strong>Action</strong>
   </td>
   <td><strong>Format, Examples</strong>
   </td>
  </tr>
  <tr>
   <td><strong>Adding User</strong>
   </td>
   <td><code>user n/[your name] p/[your phone number] e/[your email] a/[your address] [g/your github username] [curr/current module] [prev/previous module] [plan/future module]</code>
<p>
<code>Eg. user n/Silas p/98765432 e/silastay@gmail.com a/Kent Ridge Drive g/SilasTSL curr/CS2100 prev/CS1101S plan/CS2109</code>
<tr>
   <td><strong>Editing User</strong>
   </td>
   <td><code>edit user [n/your name] [p/your phone number] [e/your email] [a/your address] [g/your github username]</code>
<p>
<code>Eg. edit user p/92323232 a/Kent Ridge Crescent</code>
  <tr>
   <td><strong>Delete User</strong>
   </td>
   <td><code>delete user </code>
   
<tr>
   <td><strong>Adding Contact</strong>
   </td>
   <td><code>add n/[contact name] p/[contact phone number] e/[contact email] a/[contact address] [g/contact github username] [t/contact tag] [curr/current module] [prev/previous module] [plan/future module]</code>
<p>
<code>Eg. add n/Ruijie p/91234567 e/kohrj@gmail.com a/Kent Ridge Avenue g/rjkoh t/Classmate curr/CS2100 prev/CS1101S plan/CS2109</code>
  
<tr>
   <td><strong>Editing Contact</strong>
   </td>
   <td><code>edit INDEX [n/contact name] [p/contact phone number] [e/contact email] [a/contact address] [t/contact tag]</code>
<p>
<code>Eg. edit 2 n/Laura Lee e/laura@nus.sg t/Hall</code>
   
  <tr>
   <td><strong>Delete Contact</strong>
   </td>
   <td><code>delete contact INDEX </code>
<p>
<code>Eg. delete contact 2</code>

<tr>
   <td><strong>Clear all contacts</strong>
   </td>
   <td><code>clear</code>

  <tr>
   <td><strong>Editing User's Modules</strong>
   </td>
   <td><code>module user [curr/current module] [prev/previous module] [plan/future module] [rm/module to remove]</code>
<p>
<code>Eg. module user curr/CS2100 prev/CS1231S plan/CS2106 rm/ST2334</code>
   
  <tr>
   <td><strong>Editing Contact's Modules</strong>
   </td>
   <td><code>module INDEX [curr/current module] [prev/previous module] [plan/future module] [rm/module to remove]</code>
<p>
<code>Eg. module 1 curr/CS2101 prev/CS2030S plan/CS2102 rm/GEA1000</code>
   
  <tr>
   <td><strong>Checking Modules Left</strong>
   </td>
   <td><code>modsleft INDEX</code>
<p>
<code>Eg. modsleft 3</code>
   
<tr>
    <td><strong>Add Lesson</strong>
    </td>
    <td>
      <code>lesson user l/[type] m/[module] day/[day number] start/[start time] end/[end time]</code>
      <p>
      <code>Eg. lesson user l/tut m/CS2103T d/1 start/12:00 end/13:00</code>
      <p>
      <code>lesson INDEX l/[type] m/[module] day/[day number] start/[start time] end/[end time]</code>
      <p>
      <code>Eg. lesson 1 l/tut m/CS2103T d/1 start/12:00 end/13:00</code>
     

  <tr>
    <td><strong>View Lessons / Timetable</strong>
    </td>
    <td>
      <code>timetable user</code>
      <p>
      <code>timetable INDEX</code>
      <p>
      <code>Eg. timetable 2</code>
     

  <tr>
    <td><strong>Delete lesson</strong>
    </td>
    <td>
      <code>remove user l/[type] m/[module] day/[day number] start/[start time] end/[end time]</code>
      <p>
      <code>Eg. remove user l/tut m/CS2103T d/1 start/12:00 end/13:00</code>
      <p>
      <code>remove INDEX l/[type] m/[module] day/[day number] start/[start time] end/[end time]</code>
      <p>
      <code>Eg. remove 1 l/tut m/CS2103T d/1 start/12:00 end/13:00</code>
    
<tr>
    <td><strong>Filter Tags</strong>
    </td>
    <td>
      <code>filtertag [tag name] [more tag names]...</code>
      <p>
      <code>Eg. filtertag friends family</code>
     
  <tr>
      <td><strong>Filter Current Modules</strong>
      </td>
      <td>
        <code>filtercurrmod [current module name] [more current module names]...</code>
        <p>
        <code>Eg. filtercurrmod CS2103T</code>
        <p>
        <code>Eg. filtercurrmod CS2103T CS2100</code>
       

  <tr>
      <td><strong>Filter Previous Modules</strong>
      </td>
      <td>
        <code>filterprevmod [previous module name] [more previous module names]...</code>
        <p>
        <code>Eg. filterprevmod CS2103T</code>
        <p>
        <code>Eg. filterprevmod CS2103T CS2100</code>
       </td>
  </tr>

<tr>
      <td><strong>Filter Planned Modules</strong>
      </td>
      <td>
        <code>filterplanmod [planned module names] [more planned module names]...</code>
        <p>
        <code>Eg. filterplanmod CS2109S</code>
        <p>
        <code>Eg. filterplanmod CS2109S CS3230</code>
       

  <tr>
      <td><strong>Next Semester</strong>
      </td>
      <td>
        <code>nextsem</code>
       

  <tr>
      <td><strong>Undo / Redo</strong>
      </td>
      <td>
        <code>undo</code> <code>redo</code>
</table>

[Scroll back to top](#table-of-contents)
