---
layout: page
title: User Guide
---


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## 1. Introduction
Financial Advisor Planner (FAP) is a **desktop app for Financial Advisors (FA) to manage their clients, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FAP can get your client management tasks done faster than traditional GUI apps.

## 2. About

### 2.1 Navigating the User Guide

This section aims to help you navigate the user guide.

1. The next subsection [Section 2.2, Tips for reading the User Guide](#22-tips-for-reading-the-user-guide), provides some tips on reading this document.
2. [Section 3, Quick start](#3-quick-start) has been provided to help you set up **Financial Advisor Planner**.
3. [Section 4, Features](#4-features) has been provided where the main features and their commands are documented. 

### 2.2 Tips for reading the User Guide

#### 2.2.1 General Symbols and Syntax

Symbol/Syntax        | Meaning 
---------------------|------------------
`command`            | indicates commands or keywords related to commands that can be executed by the application.
:bulb:               | indicates that the enclosed text is a tip
:exclamation:        | indicates that the enclosed text is a warning and usually gives details about potential errors.
:information_source: | indicates that the enclosed text are extra notes and information regarding the topic. 

#### 2.2.2 Graphical User Interface overview

The following figures show the overall GUI of **Financial Advisor Planner**

![ContactsTabGraphic](images/ContactsTabGraphic.png)
<br>
<br>
<br>
![CalendarTabGraphic](images/CalendarTabGraphic.png)

## 3. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `financialadvisorplanner.jar` from [here](https://github.com/AY2223S1-CS2103T-W09-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your FinancialAdvisorPlanner.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 i/60000 r/Low s/1000` : Adds a contact named `John Doe` to the list of clients.

   * **`aa`** `1 d/21-Jan-2023 12:30 PM l/Jurong Point, Starbucks` : Adds an appointment to the client in index 1 of the contact book with the date, time and location of the appointment.
   
   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`sort KEYWORD`** : Sorts the contacts based on the `KEYWORD`.

   * **`find`** `John`: looks for contact that matches keyword ‘John’

   * **`exit`** : Exits the app.

1. Refer to the [Features](#4-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 4. Features

This section contains all the information pertaining to the features of **Financial Advisor Planner**

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but if you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
* Parameter hints will be shown after a command action.

</div>

<div markdown="block" class="alert alert-warning">

**:warning: Warning regarding the use of the application:**<br>

Other crucial files will be created and stored in the same folder that contains **FinancialAdvisorPlanner.jar**. These files are responsible for storing your data, hence it is advised to not delete or modify these files.
</div>

### 4.1 General command features

#### 4.1.1 Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


#### 4.1.2 Adding a client: `add`

Adds a client to the list of clients.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS i/YEARLY_INCOME r/RISK_APPETITE s/MONTHLY_SAVINGS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client can have any number of tags (including 0) </div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** RISK_APPETITE can only have the values ‘High’, ‘Medium’ or ‘Low’
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 i/200000 r/Medium s/5000`
* `Add n/Betsy Crowe t/friend i/0 r/High e/betsycrow@example.com a/Newgate Prison p/1234567 s/0`

#### 4.1.3 Listing all clients : `list`

Shows a list of all clients in the client list.

Format: `list`

#### 4.1.4 Editing a client : `edit`

Edits an existing client in the list of clients.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/YEARLY_INCOME] [r/RISK_APPETITE] [s/MONTHLY_SAVINGS] [t/TAG]…​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3,…​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.

#### 4.1.5 Filtering persons by keyword: `find`

Finds clients whose names contain any of the given keywords.

Format: `find PREFIX/ KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only full words will be matched e.g. Han will not match Hans
* Clients matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang
* The search also applies to other information available such as mobile number or policy numbers.
  E.g John Doe has the mobile number 87438807
    * find 87438807 will return John Doe
* The search can be limited to certain categories by using find [CATEGORY] KEYWORD instead.
  * E.g find [Mobile] 87438807 will return John Doe

Examples:
* `find n/ John` returns `john` and `John Doe`
* `find n/ alex david` returns `Alex Yeoh`, `David Li`
* `find r/ high` returns `john` and `Alex Yeoh`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### 4.1.6 Deleting a client : `delete`

Deletes the specified client from the list of clients.

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd client in the list of clients.
* `find Betsy` followed by `delete 1` deletes the 1st client in the results of the `find` command.

#### 4.1.7 Sorting: `sort KEYWORD`/  `sort KEYWORD desc`

Sorts the contacts shown in the list of clients by alphabetical order. It is ascending by default.
You can sort the contacts in descending order by adding `desc` behind the `KEYWORD`.

Format: sort `KEYWORD` / sort `KEYWORD desc`

Types of `KEYWORD`: `name`, `appt`, `risk`, `income`

* Returns all the contacts sorted by the given `KEYWORD`

  ![result for 'sort name'](images/sortAlphabetResult.png)

#### 4.1.8 Clearing all entries : `clear`

Clears all entries from the list of clients.

Format: `clear`

#### 4.1.9 Exiting the program : `exit`

Exits the program.

Format: `exit`
 
### 4.2 Appointment management features

#### 4.2.1 Adding an appointment : `aa`

Adds an appointment with inputs DATE_AND_TIME and LOCATION for an existing client in the list of clients. This will also update the Calendar and the appointment will be shown in the matching month in the Calendar.

Format: `aa INDEX d/DATE_AND_TIME l/LOCATION`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
DATE_AND_TIME has the format `d-MMM-yyyy hh:mm a` </div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** Input DATE_AND_TIME must be valid in order for the command to be executed.
<br>Each client can have a maximum of 3 appointments, and there cannot be appointments with the same DATE_AND_TIME in the overall list of appointments.
</div>

Examples:

* `aa 1 d/21-Jan-2023 12:30 PM l/Jurong Point, Starbucks`

#### 4.2.2 Editing an appointment : `ea`
Edits an appointment for an existing client in the list of clients. This will also update the Calendar and the appointment will be shown in the matching month in the Calendar.

Format: `ea PERSON_INDEX.APPOINTMENT_INDEX [d/DATE_AND_TIME] [l/LOCATION]`
* Edits the client's appointment using specified PERSON_INDEX and APPOINTMENT_INDEX. 
* The PERSON_INDEX refers to the index number shown in the displayed client list. The index must be a positive integer 1, 2, 3,…​
* The APPOINTMENT_INDEX refers to the index number shown in the displayed client's appointment list. The index can only be 1, 2, or 3.
* At least one of the optional fields must be provided. 
* Existing values will be updated to the input values.

Examples:

* `ea 1.1 d/21-Jan-2023 12:30 PM l/West Mall, Starbucks` Edits both the date and location of the 1st client's 1st appointment to be `21-Jan-2023 12:30 PM` and `West Mall, Starbucks` respectively.
* `ea 2.3 l/NUS, TechnoEdge` Edits the location of the 2nd client's 3rd appointment to be `NUS, TechnoEdge`

#### 4.2.3 Deleting an appointment : `da`
Deletes an appointment for an existing client in the list of clients. This will also update the Calendar and the appointment will be shown in the matching month in the Calendar.

Format: `da PERSON_INDEX.APPOINTMENT_INDEX`
* Deletes the client's appointment using specified PERSON_INDEX and APPOINTMENT_INDEX.
* The PERSON_INDEX refers to the index number shown in the displayed client list. The index must be a positive integer 1, 2, 3,…​
* The APPOINTMENT_INDEX refers to the index number shown in the displayed client's appointment list. The index can only be 1, 2, or 3.

Examples:

* `da 1.1` Deletes the 1st client's 1st appointment 
 
### 4.3 Calendar features

### 4.4 Command Line tools
#### 4.4.1 Parameter hints
After typing in the command word, the command's parameters and their respective prefixes are shown. An example of a valid command will also be shown.
![result for 'parameter_hints'](images/ParameterHintsExample.png)
#### 4.4.2 Command history
Use the up and down keys to navigate through previously typed commands. Users can edit the command first or press enter to execute the command. 
* Only valid commands will be saved in the command history
* Command history will only save up to 20 previously typed valid commands

### 4.5 Command history 

### 4.5 Miscellaneous commands

#### 4.4.1 Saving the data

FinancialAdvisorPlanner data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### 4.4.2 Editing the data file

FinancialAdvisorPlanner data are saved as a JSON file `[JAR file location]/data/FinancialAdvisorPlanner.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FinancialAdvisorPlanner will discard all data and start with an empty data file at the next run.
</div>

### 4.5 Future features

#### 4.5.1 Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 5. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FinancialAdvisorPlanner home folder.

--------------------------------------------------------------------------------------------------------------------

## 6. Command summary

Action | Format, Examples
--------|------------------
**Add client** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS i/YEARLY_INCOME r/RISK_APPETITE s/MONTHLY_SAVINGS [t/TAG]…​ ` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 i/60000 r/Low s/1000 t/friend t/colleague`
**Add Appointment** | `aa d/DATE_AND_TIME l/LOCATION` <br> e.g., `aa 1 d/21-Jan-2023 12:30 PM l/Jurong Point, Starbucks`
**Edit Appointment** | `ea PERSON_INDEX.APPOINTMENT_INDEX [d/DATE_AND_TIME] [l/LOCATION]` <br> e.g., `ea 1.2 d/21-Jan-2023 12:30 PM l/NUS, TechnoEdge`
**Delete Appointment** | `da PERSON_INDEX.APPOINTMENT_INDEX` <br> e.g, `da 1.2`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [i/YEARLY_INCOME] [r/RISK_APPETITE] [s/MONTHLY_SAVINGS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Exit** | `exit`
**Find** | `find KEYWORD [MORE_KEYWORDS]` <br> `find [CATEGORY] KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**Sort** | `sort KEYWORD, sort KEYWORD desc` 

## 7. Glossary

Term | Meaning 
--------|------------------
**Command Line Interface (CLI)** | `A CLI is a text-based user interface that allows users to input commands in the form of text.`
**Graphical User Interface (GUI)** | `A GUI is a graphics-based user interface that allows users to interact with elements such as icons and buttons when running a program.`
**Command** | `An instruction given by the user that is to be executed.` 
**Parameter** | `Variables that have been typed by the user that is to be provided to the command to be executed. `
**Prefix** | `Indicator for a specific parameter to be input during the command. Prefixes end with a slash (/)`
