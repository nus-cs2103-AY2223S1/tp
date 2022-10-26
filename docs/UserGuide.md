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

   * **`project -d`**`3` : Deletes the project with project id `3`.

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
  e.g. if the command specifies `client -a PROJECT_ID n/CLIENT_NAME [m/CLIENT_CONTACT_NUMBER] [e/CLIENT_EMAIL]`, `client -a PROJECT_ID n/CLIENT_NAME [e/CLIENT_EMAIL] [m/CLIENT_CONTACT_NUMBER]` are both acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `m/12341234 m/56785678`, only `m/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Displays a list of commands and functionalities.

Format: `help`

### Adding a project: `project -a`

Adds a project to the list of projects. A unique project ID will be automatically generated.

Format: `project -a n/PROJECT_NAME [r/USERNAME/REPO_NAME] [c/CLIENT_ID] [d/DEADLINE]…​`

* Adds the project to the `ProjectList`.
* * `DEADLINE` must be in the format yyyy-mm-dd

Examples:
* `project -a n/Coding101` Adds a project with `PROJECT_NAME` Coding101 to the `ProjectList`.
* `project -a n/MyFavProject r/Jeff/MyFavProject` Adds a project with `PROJECT_NAME` MyFavProject and 
`USERNAME/REPO_NAME` Jeff/MyFavProject to the `ProjectList`.
* `project -a n/AnotherProject c/1` Adds a project with `PROJECT_NAME` AnotherProject to the `ProjectList` and adds a
client with `CLIENT_ID` 1 to the project.
* `project -a n/OneMoreProject c/1 d/2022-03-07` Adds a project with `PROJECT_NAME` OneMoreProject and `PROJECT_DEADLINE`
2022-03-07 to the `ProjectList` and adds a client with `CLIENT_ID` 1 to the project.
* `project -a n/LastProject d/2023-10-01 r/Dave/LastProject c/2` Adds a project with `PROJECT_NAME` LastProject and 
`DEADLINE` 2023-10-01 and `USERNAME/REPO` Dave/LastProject to the `ProjectList` and adds a client with `CLIENT_ID` 2 
to the project.

### Editing a project : `project -e`

Edits a specified existing project.

Format: `project -e p/PROJECT_ID [n/PROJECT_NAME] [r/USERNAME/REPO] [c/CLIENT_ID] [d/DEADLINE]…​`

* Edits the project with the specified `PROJECT_ID`. The ID refers to the unique ID generated upon adding a project.
  The ID **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* * `DEADLINE` must be in the format yyyy-mm-dd

Examples:
* `project -e p/1 n/FirstProject` Edits the project with `PROJECT_ID` 1 to have the new `PROJECT_NAME` FirstProject.
* `project -e p/2 r/James/SecondProject` Edits the project with `PROJECT_ID` 2 to have the new `USERNAME/REPO` 
James/SecondProject.
* `project -e p/3 c/1 d/2021-12-03` Edits the project with `PROJECT_ID` 3 to have the new client with `CLIENT_ID` 1 
and new `DEADLINE` 2021-12-03.
* `project -e p/4 n/ThirdProject d/2022-02-02 r/Jackson/ThirdProject c/2` Edits the project with `PROJECT_ID` 4 to 
have the new `PROJECT_NAME` ThirdProject, new `DEADLINE` 2022-02-02, new `USERNAME/REPO` Jackson/ThirdProject and new 
client with `CLIENT_ID` 2.

### Deleting a project : `project -d`

Removes the specified existing project.

Format: `project -d PROJECT_ID`

* Deletes the project with the specified `PROJECT_ID`.
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `project -d 1` Deletes project with `PROJECT_ID` 1.
* `project -d 4` Deletes project with `PROJECT_ID` 4.

### Listing all projects : `project -l`

Shows a list of all projects.

Format: `project -l`

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

Adds a client to the list of clients and to the specified existing project. A unique client ID will be automatically 
generated.

Format: `client -a n/CLIENT_NAME p/PROJECT_ID [m/CLIENT_PHONE] [e/CLIENT_EMAIL]…​`

* Adds the client to `ClientList` and to the project with the specified `PROJECT_ID`.

Examples:
*  `client -a n/Amy p/1` Adds a client with `CLIENT_NAME` Amy to the `ClientList` and to the project with 
   `PROJECT_ID` 1.
*  `client -a n/Bob p/2 m/12345678` Adds a client with `CLIENT_NAME` Bob and `CLIENT_PHONE` 12345678 to the 
   `ClientList` and to the project with `PROJECT_ID` 2.
*  `client -a n/Charlie e/charlie@gmail.com p/3` Adds a client with `CLIENT_NAME` Charlie and `CLIENT_EMAIL` 
   charlie@gmail.com to the `ClientList` and to the project with `PROJECT_ID` 3.
*  `client -a n/Dave m/12345678 e/dave@gmail.com p/4` Adds a client with `CLIENT_NAME` Dave, `CLIENT_PHONE` 
   12345678 and `CLIENT_EMAIL` dave@gmail.com to the `ClientList` and to the project with `PROJECT_ID` 4.

### Editing a client : `client -e`

Edits the specified existing client.

Format: `client -e c/CLIENT_ID [n/CLIENT_NAME] [m/CLIENT_PHONE] [e/CLIENT_EMAIL]…​`

* Edits the client with the specified `CLIENT_ID`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `client -e c/1 n/Amy` Edits the client with `CLIENT_ID` 1 to have the new `CLIENT_NAME` Amy.
*  `client -e c/2 n/Bob m/12345678` Edits the client with `CLIENT_ID` 2 to have the new `CLIENT_NAME` Bob 
   and `CLIENT_PHONE` 91234567.
*  `client -e c/3 n/Charlie e/charlie@gmail.com` Edits the client with `CLIENT_ID` 3 to have the new 
   `CLIENT_NAME` Charlie and `CLIENT_EMAIL` charlie@gmail.com.
*  `client -e c/4 n/Dave m/12345678 e/dave@gmail.com` Edits the client with `CLIENT``_ID` 4 to have the new 
   `CLIENT_NAME` Dave, `CLIENT_PHONE` 12345678 and `CLIENT_EMAIL` dave@gmail.com.

### Deleting a client : `client -d`

Removes the specified existing client.

Format: `client -d CLIENT_ID`

* Deletes the client with the specified `CLIENT_ID`.
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `client -d 1` Deletes client with `CLIENT_ID` 1.
* `client -d 6` Deletes client with `CLIENT_ID` 6.

### Listing all clients : `client -l`

Shows a list of all clients.

Format: `client -l`

### Finding a client : `client -f`

Finds and lists all the clients matching the search criteria.

Format: `client -f [n/CLIENT_NAME] [m/CLIENT_PHONE] [e/CLIENT_EMAIL]…​`

* Finds all the clients with the specified `CLIENT_NAME`, `CLIENT_PHONE` and `CLIENT_EMAIL`.
* Finds all the clients such that the fields under the client contain at least one word from the keywords provided 
  after each search criteria.
* The keywords provided must be valid arguments for their respective search criteria.

Examples:
* `client -f n/Amy` Finds and lists all the clients whose `CLIENT_NAME` contains the word `Amy`.
* `client -f n/Amy Bob` Finds and lists all the clients with the `CLIENT_NAME` contains the word `Amy` or `Bob`.
* `client -f n/Amy e/amy@gmail.com` Finds and lists all the clients whose `CLIENT_NAME` contains the word `Amy` and 
  with `CLIENT_EMAIL` amy@gmail.com.
* `client -f n/Amy e/amy@gmail.com m/12345678` Finds and lists all the clients whose `CLIENT_NAME` contains the word 
  `Amy` and with `CLIENT_EMAIL` amy@gmail.com and with `CLIENT_PHONE` 12345678.
* `client -f n/Amy Bob e/amy@gmail.com bobamy@gmail.com m/12345678` Finds and lists all the clients whose 
  `CLIENT_NAME` contains the word `Amy` or `Bob` and with `CLIENT_EMAIL` amy@gmail.com or bobamy@gmail.com and with 
  `CLIENT_PHONE` 12345678.

### Adding an issue : `issue -a`

Adds an issue to the list of issues and to the issue list in the specified existing project. A unique issue ID will be 
automatically generated.

Format: `issue -a p/PROJECT_ID t/TITLE [d/DEADLINE] [u/URGENCY] …​`

* Adds the issue to the overall `IssueList` and to the `IssueList`of the project with the specified `PROJECT_ID`.
* `DEADLINE` must be in the format yyyy-mm-dd
* `URGENCY` must be an integer from 0 to 3, 0 for NONE, 1 for LOW, 2 for MEDIUM and 3 for HIGH

Examples:
* `issue -a p/1 t/Fix Ui` Adds an issue with `TITLE` Fix Ui to the overall `IssueList` and to the `IssueList` of the 
project with `PROJECT_ID` 1.
* `issue -a p/2 t/Add tests u/2`Adds an issue with `TITLE` Add tests and `URGENCY` MEDIUM(2) to the overall `IssueList`
and to the `IssueList` of the project with `PROJECT_ID` 2.
* `issue -a t/Design GUI u/0 d/2022-09-12 p/3` Adds an issue with `TITLE` Design GUI, `URGENCY` NONE(0) and `DEADLINE` 
2022-09-12 to the overall `IssueList` and to the `IssueList` of the project with `PROJECT_ID` 3.

### Editing an issue : `issue -e`

Edits the specified existing issue.

Format: `issue -e i/ISSUE_ID [t/TITLE] [d/DEADLINE] [u/URGENCY]…​`

* Edits the issue with the specified `ISSUE_ID`
* At least one of the optional fields must be provided.
* Existing values will be updated to the input value.
* `PROJECT_ID` cannot be edited as each issue is created for a specific project
* `DEADLINE` must be in the format yyyy-mm-dd
* `URGENCY` must be an integer from 0 to 3, 0 for NONE, 1 for LOW, 2 for MEDIUM and 3 for HIGH

Examples:
* `issue -e i/1 t/Do DG` Edits the issue with `ISSUE_ID` 1 to have the new `TITLE` Do DG.
* `issue -e i/2 d/2021-12-20 u/1` Edits the issue with `ISSUE_ID` 2 to have the new `DEADLINE` 2021-12-20 and `URGENCY`
LOW(1).
* `issue -e 1/3 u/3 t/Do UG d/2022-01-12` Edits the issue with `ISSUE_ID` 3 to have the new `URGENCY` HIGH(3) and 
`TITLE` Do UG and `DEADLINE` 2022-01-12.

### Deleting an issue : `issue -d`

Removes the specified existing issue.

Format: `issue -d ISSUE_ID`

* Deletes the issue with the specified `ISSUE_ID`.
* The ID **must be a positive integer** 1, 2, 3, …​

Examples:
* `issue -d 1` Deletes issue with `ISSUE_ID` 1.
* `issue -d 3` Deletes issue with `ISSUE_ID` 3.

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
| **List Projects**  | `project -l`     |
| **Find Project**   | `project -f`     |
| **Add Client**     | `client -a`      |
| **Edit Client**    | `client -e`      |
| **Delete Client**  | `client -d`      |
| **List Clients**   | `client -l`      |
| **Find Client**    | `client -f`      |
| **Add Issue**      | `issue -a`       |
| **Edit Issue**     | `issue -e`       |
| **Delete Issue**   | `issue -d`       |
| **List Issues**    | `issue -l`       |
| **Find Issue**     | `issue -f`       |
| **Clear**          | `clear`          |
| **Help**           | `help`           |

