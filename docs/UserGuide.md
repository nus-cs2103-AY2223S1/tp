---
layout: page
title: User Guide
---

# ConnectNUS User Guide

ConnectNUS is a **desktop app for managing contacts, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ConnectNUS can get your contact management tasks done faster than traditional GUI apps.

## Table of Contents
- [Features](#features)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
  - [Adding current modules](#adding-current-modules)
  - [Adding previous modules](#adding-previous-modules)
  - [Adding future modules](#adding-future-modules)
  - [Listing modules](#listing-modules)
  - [Deleting modules](#deleting-modules)
  - [Deleting plans](#deleting-plans)
  - [Adding contacts](#adding-contacts)
  - [Listing contacts](#listing-contacts)
  - [Deleting contacts](#deleting-contacts)
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


### Saving the data

ConnectNUS data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Editing the data file

ConnectNUS data is saved as a JSON file `[JAR file location]/data/connectnus.json`. Advanced users are welcome to update data directly by editing that data file.



**Caution:** If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.


### Adding current modules

Add a specified module that you are currently taking to your work plan.

Format: `add curr mod [module code]`



* Add a module with module code [module code] to the list of mods you are taking this semester.
* The module code refers to the module code of the module the user is planning to take (E.g. CS2103T)
* The module code must be a registered module in NUS.


### Adding previous modules

Add a module to the list of modules that you have taken previously in earlier semesters.

Format: `add prev mod [module code]`



* Add a module with module code [module code] to the list of mods you have taken in previous semesters.
* The module code refers to the module code of the module the user has taken (E.g. CS2103T)
* The module code must be a registered module in NUS or was registered in the semester the user was enrolled in the module.


### Adding future modules

Add a module to the list of modules you are thinking of taking next semester.

Format: `add plan mod [module code]`



* Adds module with module code `[module code]` to list of modules you are planning to take.
* The module code refers to the module code of the module the user is planning to take (E.g. CS2103T)
* The module code must be a registered module in NUS.

Examples:



* `add planning module CS2103T `adds module CS2103T to list of modules you are planning to take.


### Listing modules

Shows the list of modules your friends or yourself have taken, are taking and are planning on taking

Format: `list all mod [INDEX]/list all mod/list prev mod [INDEX]/list prev mod/list curr mod [INDEX]/list curr mod/list plan mod [INDEX]/list plan mod`



* Can list all the modules you/your friend have taken or will ever take
* The index refers to the index number shown in the displayed friend list.
* If no index is provided after the command then it will list your modules
* The index **must be a positive integer** 1, 2, 3, …​
* `list all mod` lists all the modules the user/their friend has previously taken, are currently taking and are planning to take
* `list prev mod` lists all the modules the user/their friend has previously taken
* `list curr mod` lists all the modules the user/their friend are currently taking
* `list plan mod` lists all the modules the user/their friend are planning to take


### Deleting modules

Deletes a specified module user is taking currently or has taken previously.

Format: `delete mod [INDEX]/delete prev mod [INDEX]/delete curr mod [INDEX]`



* Deletes the module at the specified `INDEX`.
* The index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:



* `list all mod `followed by  `delete mod 1` deletes the first module in the list of all modules the user has taken and is currently taking.
* `list prev mod `followed by  `delete prev mod 2` deletes the 2nd module in the list of modules have previously taken.
* `list curr mod followed by delete curr mod 3 `deletes the 3rd module in the list of modules the user is currently taking.


### Deleting plans

Deletes a specified plan of modules the user is intending to take.

Format: `delete plan INDEX`



* Deletes the plan at the specified `INDEX`.
* The index refers to the index number shown in the displayed plan list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:



* `list mod plan `followed by  `delete plan 1` deletes the first plan in the list of all plans the user has added.


### Adding contacts

Add a contact to your contact list.

Format: `add contact [contact name] /mods [current module list] /previous [previous module list] /planning [future module list]`



* Adds contact with specified details into your contact list.
* You are able to specify the modules the contact is taking currently, taken in the past, and is planning to take in the future.
* Each module list should consist of module codes separated by commas. (E.g. `CS2103T, CS2101`)
* Each of these module codes must be registered modules in NUS.

Examples:



* `add contact Ruijie /mods CS2100, CS2101, CS2100, ST2334, GESS1037 /previous CS1101S, CS1231S, MA2001, MA1521, GEC1000 /planning CS2109, CS2106 `adds module contact Ruijie to your contact list with all the module details specified.


### Listing contacts

Shows a list of all persons in the address book.

Format: `list`



* The list of contacts is numbered based on the order that the contacts are added


### Deleting contacts

Deletes a specified contact from the user’s contacts list

Format: `delete contact INDEX`



* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:



* `list `followed by  `delete contact 1` deletes the first contact in the list of all contacts the user has added.


### Exiting the program : `exit`

Exits the program.

Format: `exit`


---


## Command summary


<table>
  <tr>
   <td><strong>Action</strong>
   </td>
   <td><strong>Format, Examples</strong>
   </td>
  </tr>
  <tr>
   <td><strong>Add Current Module</strong>
   </td>
   <td><code>add curr mod [module code]</code>
<p>
<code>Eg. add curr mod CS2103t</code>
   </td>
  </tr>
  <tr>
   <td><strong>Add Previous Module</strong>
   </td>
   <td><code>add prev mod [module code]</code>
<p>
<code>Eg. add prev mod CS2103t</code>
   </td>
  </tr>
  <tr>
   <td><strong>Adding Future Module</strong>
   </td>
   <td><code>add plan mod [module code]</code>
<p>
<code>Eg. add plan mod CS2103t</code>
   </td>
  </tr>
  <tr>
   <td><strong>List Modules</strong>
   </td>
   <td><code>list all mod [INDEX]/list all mod/list prev mod [INDEX]/list prev mod/list curr mod [INDEX]/list curr mod/list plan mod [INDEX]/list plan mod</code>
<p>
<code>Eg. list all mod 1</code>
   </td>
  </tr>
  <tr>
   <td><strong>Delete Module</strong>
   </td>
   <td><code>delete mod [INDEX]/delete prev mod [INDEX]/delete curr mod [INDEX]</code>
<p>
<code>Eg. delete mod 1</code>
   </td>
  </tr>
  <tr>
   <td><strong>Delete Plan</strong>
   </td>
   <td><code>delete plan INDEX</code>
<p>
<code>Eg. delete plan 2</code>
   </td>
  </tr>
  <tr>
   <td><strong>Adding Contact</strong>
   </td>
   <td><code>add contact n/[contact name] p/[contact phone number] e/[contact email] a/[contact address] t/[contact tag] curr/[current module] prev/[previous module] plan/[future module]</code>
<p>
<code>Eg. add n/Ruijie p/91234567 e/kohrj@gmail.com a/Kent Ridge Avenue t/Classmate 123 curr/CS2100 prev/CS1101S plan/CS2109</code>
   </td>
  </tr>
  <tr>
   <td><strong>Delete Contact</strong>
   </td>
   <td><code>delete contact INDEX </code>
<p>
<code>Eg. delete contact 2</code>
   </td>
  </tr>
</table>


