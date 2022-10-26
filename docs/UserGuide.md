---
layout: page
title: User Guide
---

DevEnable is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, DevEnable can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `devenable.jar`.

3. Copy the file to the folder you want to use as the _home folder_ for your DevEnable.

4. Double-click the file to start the app.

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all projects.

   * **`project -a`**`n/Orbital` : Adds a project named `Orbital` to the Address Book.

   * **`project -d`**`3` : Deletes the 3rd project shown in the current list.

   * **`clear`** : Deletes all projects.

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
  e.g. if the command specifies `client -t PROJECT_ID n/CLIENT_NAME [p/CLIENT_CONTACT_NUMBER] [e/CLIENT_EMAIL]`, `client -t PROJECT_ID n/CLIENT_NAME [e/CLIENT_EMAIL] [p/CLIENT_CONTACT_NUMBER]` are both acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
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

### Listing all projects : `project -l`

Shows a list of all projects.

Format: `project -l`

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

### Finding a project : `project -f`

Finds and lists all the projects matching the search criteria.

Format: `project -f [n/PROJECT_NAME] [r/REPOSITORY]…​`

* Finds all the projects with the specified `PROJECT_NAME` and `REPOSITORY`.
* Finds all the projects such that the fields under the project contain at least one word from the keywords provided
  after each search criteria.
* The keywords provided must be valid arguments for their respective search criteria.

Examples:
* `project -f n/DevEnable` Finds and lists all the projects whose `PROJECT_NAME` contains the word DevEnable.
* `project -f n/DevEnable AB3` Finds and lists all the projects whose `PROJECT_NAME` contains the word DevEnable or AB3.
* `project -f r/tp/F13` Finds and lists all the projects with `REPOSITORY` tp/F13.
* `project -f n/AB4 AB3 r/tp/F13` Finds and lists all the projects whose `PROJECT_NAME` contains the word AB4 or AB3 
  and with `REPOSITORY` tp/F13.

### Adding a client : `client -a`

Adds a client to the list of clients and to the specified project.

Format: `client -a n/CLIENT_NAME pid/PROJECT_ID [p/CLIENT_PHONE] [e/CLIENT_EMAIL]…​`

* Adds the client to `ClientList` and to the project with the specified `PROJECT_ID`.

Examples:
*  `client -a n/Amy pid/1` Adds a client with `CLIENT_NAME` Amy to the `ClientList` and to the project with 
   `PROJECT_ID` 1.
*  `client -a n/Bob pid/2 p/12345678` Adds a client with `CLIENT_NAME` Bob and `CLIENT_PHONE` 12345678 to the 
   `ClientList` and to the project with `PROJECT_ID` 2.
*  `client -a n/Charlie e/charlie@gmail.com pid/3` Adds a client with `CLIENT_NAME` Charlie and `CLIENT_EMAIL` 
   charlie@gmail.com to the `ClientList` and to the project with `PROJECT_ID` 3.
*  `client -a n/Dave p/12345678 e/dave@gmail.com pid/4` Adds a client with `CLIENT_NAME` Dave, `CLIENT_PHONE` 
   12345678 and `CLIENT_EMAIL` dave@gmail.com to the `ClientList` and to the project with `PROJECT_ID` 4.

### Editing a client : `client -e`

Edits the specified client.

Format: `client -e cid/CLIENT_ID [n/CLIENT_NAME] [p/CLIENT_PHONE] [e/CLIENT_EMAIL]…​`

* Edits the client with the specified `CLIENT_ID`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `client -e cid/1 n/Amy` Edits the client tagged to project with `CLIENT_ID` 1 to have the new `CLIENT_NAME` Amy.
*  `client -e cid/2 n/Bob p/12345678` Edits the client tagged with `CLIENT_ID` 2 to have the new `CLIENT_NAME` Bob 
   and `CLIENT_PHONE` 91234567.
*  `client -e cid/3 n/Charlie e/charlie@gmail.com` Edits the client with `PROJECT_ID` 3 to have the new 
   `CLIENT_NAME` Charlie and `CLIENT_EMAIL` charlie@gmail.com.
*  `client -e cid/4 n/Dave p/12345678 e/dave@gmail.com` Edits the client with `PROJECT_ID` 4 to have the new 
   `CLIENT_NAME` Dave, `CLIENT_PHONE` 12345678 and `CLIENT_EMAIL` dave@gmail.com.

### Deleting a client : `client -d`

Removes the specified client.

Format: `client -d CLIENT_ID`

* Deletes the client with the specified `CLIENT_ID`.
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `client -d 1` Deletes client with `CLIENT_ID` 1.
* `client -d 6` Deletes client with `CLIENT_ID` 6.

### Finding a client : `client -f`

Finds and lists all the clients matching the search criteria.

Format: `client -f [n/CLIENT_NAME] [p/CLIENT_PHONE] [e/CLIENT_EMAIL]…​`

* Finds all the clients with the specified `CLIENT_NAME`, `CLIENT_PHONE` and `CLIENT_EMAIL`.
* Finds all the clients such that the fields under the client contain at least one word from the keywords provided 
  after each search criteria.
* The keywords provided must be valid arguments for their respective search criteria.

Examples:
* `client -f n/Amy` Finds and lists all the clients whose `CLIENT_NAME` contains the word `Amy`.
* `client -f n/Amy Bob` Finds and lists all the clients with the `CLIENT_NAME` contains the word `Amy` or `Bob`.
* `client -f n/Amy e/amy@gmail.com` Finds and lists all the clients whose `CLIENT_NAME` contains the word `Amy` and 
  with `CLIENT_EMAIL` amy@gmail.com.
* `client -f n/Amy e/amy@gmail.com p/12345678` Finds and lists all the clients whose `CLIENT_NAME` contains the word 
  `Amy` and with `CLIENT_EMAIL` amy@gmail.com and with `CLIENT_PHONE` 12345678.
* `client -f n/Amy Bob e/amy@gmail.com bobamy@gmail.com p/12345678` Finds and lists all the clients whose 
  `CLIENT_NAME` contains the word `Amy` or `Bob` and with `CLIENT_EMAIL` amy@gmail.com or bobamy@gmail.com and with 
  `CLIENT_PHONE` 12345678.

### Adding an issue : `issue -a`

Adds an issue to a project.

Format: `issue -a i/PROJECT_ID …​`

* Tags the issue to the project with the specified `PROJECT_ID`.

Examples:
*  `issue -t i/1 ` Tags the project with `PROJECT_ID` 1.

### Editing an issue : `issue -e`

Edits the issue for a specific project.

Format: `issue -e i/PROJECT_ID…​`

* Edits the issue of the project with the specified `PROJECT_ID`.
<!-- TODO: clarify * `DATETIME` must be in the following format: yyyy-mm-dd. -->

Examples:
*  `issue -e 1 2022-09-16` Edits the issue of project with `PROJECT_ID` to be 2022-09-16.

### Finding an issue : `issue -f`

Finds and lists all the issues matching the search criteria.

Format: `issue -f [desc/TITLE] [pn/PROJECT_NAME] [p/PRIORITY] [s/STATUS]…​`

* Finds all the issues with the specified `TITLE`, `PROJECT_NAME`, `PRIORITY` and `STATUS`.
* Finds all the issues such that the fields under the issue contain at least one word from the keywords provided
  after each search criteria.
* The keywords provided must be valid arguments for their respective search criteria.

Examples:
* `issue -f pn/DevEnable` Finds and lists all the issues tied to the project whose `PROJECT_NAME` contains DevEnable.
* `issue -f pn/DevEnable AB3` Finds and lists all the issues tied to the project whose `PROJECT_NAME` contains 
  DevEnable or AB3.
* `issue -f pn/DevEnable AB3 p/LOW` Finds and lists all the issues with `PRIORITY` as LOW and tied to the project whose 
  `PROJECT_NAME` contains DevEnable or AB3.
* `issue -f desc/enhancement pn/DevEnable AB3 p/HIGH LOW` Finds and lists all the issues with `TITLE` 
  enhancement and `PRIORITY` HIGH or LOW and tied to project with `PROJECT_NAME` DevEnable or AB3.
* `issue -f s/Incomplete` Finds and lists all the issues with `STATUS` Incomplete. 


### Deleting an issue : `issue -d`

Removes an issue.

Format: `issue -d i/ISSUE_ID p/PROJECT_ID`

* Deletes the issue tagged to the specified project with given `ISSUE_ID` and `PROJECT_ID`.
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `issue -d i/1 p/1` Deletes issue tagged to project with `PROJECT_ID` 1 and `ISSUE_ID` 1.

### Clearing all entries : `clear`

Clears all entries from the list.

Format: `clear`

### Saving the data

DevEnable data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

DevEnable data are saved as a txt file `[JAR file location]/data/devenable.txt`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, DevEnable will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous DevEnable home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Format, Examples |
|--------------------|------------------|
| **Add Project**    | `project -a`     |
| **Edit Project**   | `project -e`     |
| **Delete Project** | `project -d`     |
| **Find Project**   | `project -f`     |
| **Add Client**     | `client -a`      |
| **Edit Client**    | `client -e`      |
| **Delete Client**  | `client -d`      |
| **Find Client**    | `client -f`      |
| **Tag Issue**      | `issue -a`       |
| **Edit Issue**     | `issue -e`       |
| **Delete Issue**   | `issue -d`       |
| **Find Issue**     | `issue -f`       |
| **Clear**          | `clear`          |
| **List**           | `list`           |
| **Help**           | `help`           |

