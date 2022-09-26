---
layout: page
title: User Guide
---

DevEnable is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while 
still having the benefits of a Graphical User Interface (GUI). If you can type fast, DevEnable can get your contact 
management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `deveneable.jar`.

3. Copy the file to the folder you want to use as the _home folder_ for your DevEnable.

4. Double-click the file to start the app. 

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will 
open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all project.

   * **`project -a`**`n/Orbital` : Adds a project named `Orbital` to the Address Book.

   * **`project -d`**`3` : Deletes the 3rd project shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `project -a n/PROJECT_NAME`, `PROJECT_NAME` is a parameter which can be used as `project -a n/PROJECT_NAME`.

* Items in square brackets are optional.<br>
  e.g. `n/PROJECT_NAME [r/USERNAME/REPO_NAME]` can be used as `project -a n/ProjectY r/AgentX/ProjectY`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/client`, `t/client t/deadline` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `client -t PROJECT_ID n/CLIENT_NAME [p/CLIENT_CONTACT_NUMBER] [e/CLIENT_EMAIL]`, 
  `client -t PROJECT_ID n/CLIENT_NAME [e/CLIENT_EMAIL] [p/CLIENT_CONTACT_NUMBER]` are both acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence 
  of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) 
  will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Displays a list of commands and functionalities.

Format: `help`

### Adding a project: `project -a`

Adds a project to the application. A unique project ID will be automatically generated.

Format: `project -a n/PROJECT_NAME [r/USERNAME/REPO_NAME]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A project can have any number of tags (including 0)
</div>

Examples:
* `project -a n/ProjectX`
* `project -a n/ProjectY r/AgentX/ProjectY`

### Listing all projects : `list`

Shows a list of all projects.

Format: `list`

### Editing a project : `project -e`

Edits an existing project.

Format: `project -e PROJECT_ID [n/PROJECT_NAME] [r/REPO_URL]…​`

* Edits the project with the specified `PROJECT_ID`. The ID refers to the unique ID generated upon adding a project.
  The ID **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the project will be removed i.e. adding of tags is not cumulative.
* You can remove all the project’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `project -e 1 n/ProjectX` Edits the project with `PROJECT_ID` 1 to be renamed to ProjectX.
*  `project -e 2 r/AgentY/ProjectY` Edits the project with `PROJECT_ID` 2 to be associated with new repository link.
*  `project -e 3 n/ProjectZ r/AgentZ/ProjectZ` Edits the project with `PROJECT_ID` 3.

### Deleting a project : `project -d`

Deletes a project from the application.

Format: `project -d PROJECT_ID`

* Deletes the project with the specified `PROJECT_ID`.
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `project -d 1` Deletes project with `PROJECT_ID` 1.

### Tagging a client : `client -t`

Tags a client to a project.

Format: `client -t PROJECT_ID n/CLIENT_NAME [p/CLIENT_CONTACT_NUMBER] [e/CLIENT_EMAIL]…​`

* Adds the client to the project with the specified `PROJECT_ID`.

Examples:
*  `client -t 1 n/Amy` Tags the project with `PROJECT_ID` 1 with a client with `CLIENT_NAME` Amy.
*  `client -t 2 n/Bob p/91234567` Tags the project with `PROJECT_ID` 2 with a client with `CLIENT_NAME` Bob and 
   `CLIENT_CONTACT_NUMBER` 91234567.
*  `client -t 3 n/Charlie e/charlie@gmail.com` Tags the project with `PROJECT_ID` 2 with a client with `CLIENT_NAME` 
    Charlie and `CLIENT_EMAIL` charlie@gmail.com.
*  `client -t 2 n/Dave p/91111111 e/dave@gmail.com` 

### Editing a client : `client -d`

Edits the client for a specific project.

Format: `client -e i/PROJECT_ID n/CLIENT_NAME [p/CLIENT_CONTACT_NUMBER] [e/CLIENT_EMAIL]…​`

* Edits the client of the project with the specified `PROJECT_ID`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `client -e 1 n/Amy` Edits the client tagged to project with `PROJECT_ID` 1 with new `CLIENT_NAME` Amy.
*  `client -e 2 n/Bob p/91234567` Edits the client tagged to project with `PROJECT_ID` 2 with new `CLIENT_NAME` Amy 
   and `CLIENT_CONTACT_NUMBER` 91234567.
*  `client -e 3 n/Charlie e/charlie@gmail.com` Edits the client tagged to project with `PROJECT_ID` 3 with new 
   `CLIENT_NAME` Charlie and `CLIENT_EMAIL` charlie@gmail.com.
*  `client -e 2 n/Dave p/91111111 e/dave@gmail.com` Edits the client tagged to project with `PROJECT_ID` 2 with new `CLIENT_NAME` Dave, `CLIENT_CONTACT_NUMBER` 91111111 and `CLIENT_EMAIL` dave@gmail.com.

### Deleting a client : `client -d`

Removes the client from a project.

Format: `client -d i/PROJECT_ID n/CLIENT_NAME`

* Deletes the client with `CLIENT_NAME` tagged to the specified project with given `PROJECT_ID`.
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `client -d 1 n/Amy` Deletes client with `CLIENT_NAME` Amy tagged to project with `PROJECT_ID` 1.

### Tagging a deadline : `deadline -t`

Tags a deadline to a project.

Format: `deadline -t i/PROJECT_ID d/DATETIME …​`

* Tags the deadline to the project with the specified `PROJECT_ID`.
* `DATETIME` must be in the following format: yyyy-mm-dd.

Examples:
*  `deadline -t 1 2022-09-16` Tags the project with `PROJECT_ID` 1 with the specified deadline.

### Editing a deadline : `deadline -e`

Edits the deadline for a specific project.

Format: `deadline -e i/PROJECT_ID d/NEW_DATETIME…​`

* Edits the deadline of the project with the specified `PROJECT_ID`.
* `DATETIME` must be in the following format: yyyy-mm-dd.

Examples:
*  `deadline -e 1 2022-09-16` Edits the deadline of project with `PROJECT_ID` to be 2022-09-16.

### Deleting a deadline : `deadline -d`

Removes a deadline to a project.

Format: `deadline -d i/PROJECT_ID`

* Deletes the deadline tagged to the specified project with given `PROJECT_ID`.
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `deadline -d 1` Deletes deadline tagged to project with `PROJECT_ID` 1.

### Clearing all entries : `clear`

Clears all entries from the list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

DevEnable data are saved in the hard disk automatically after any command that changes the data. There is no need to 
save manually.

### Editing the data file

DevEnable data are saved as a txt file `[JAR file location]/data/devenable.txt`. Advanced users are welcome to 
update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, DevEnable will discard all data and start with an empty data 
file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Format, Examples |
|--------------------|------------------|
| **Add Project**    | `project -a`     |
| **Edit Project**   | `project -e`     |
| **Delete Project** | `project -d`     |
| **Tag Client**     | `client -a`      |
| **Edit Client**    | `client -e`      |
| **Delete Client**  | `client -d`      |
| **Tag Deadline**   | `deadline -a`    |
| **Edit Deadline**   | `deadline -e`    |
| **Delete Deadline** | `deadline -d`    |
| **Clear**          | `clear`          |
| **List**           | `list`           |
| **Help**           | `help`           |

