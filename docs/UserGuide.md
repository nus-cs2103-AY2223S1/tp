---
layout: page
title: InterNUS User Guide
---

# Table of Contents

1. [Introduction](#1-introduction)<br>
   1.1. [What is InterNUS?](#11-what-is-internus)<br>
   1.2. [Person List](#12-person-list)<br>
   1.3. [Internship List](#13-internship-list)<br>
2. [How to use this User Guide?](#2-how-to-use-this-user-guide)<br>
   2.1. [Icons and symbols](#21-icons-and-symbols)<br>
   2.2. [Graphical User Interface (GUI) of InterNUS](#22-graphical-user-interface-gui-of-internus)<br>
   2.3. [Command format](#23-command-format)<br>
3. [Quick Start](#3-quick-start)<br>
4. [Features](#4-features)<br>
   4.1. [Add Command](#41-add-command)<br>
   &emsp; 4.1.1. [Adding a person: `add -p`](#411-adding-a-person-add--p)<br>
   &emsp; 4.1.2. [Adding an internship: `add -i`](#412-adding-an-internship-add--i)<br>
   4.2. [List Command](#42-list-command)<br>
   &emsp; 4.2.1. [Listing all persons : `list -p`](#421-listing-all-persons--list--p)<br>
   &emsp; 4.2.2. [Listing all internships : `list -i`](#422-listing-all-internships--list--i)<br>
   4.3. [Edit Command](#43-edit-command)<br>
   &emsp; 4.3.1. [Editing a person : `edit -p`](#431-editing-a-person--edit--p)<br>
   &emsp; 4.3.2. [Editing an internship : `edit -i`](#432-editing-an-internship--edit--i)<br>
   4.4. [Link and Unlink Command](#44-link-and-unlink-command)<br>
   &emsp; 4.4.1. [Linking a person and an internship : `link`](#441-linking-a-person-and-an-internship--link)<br>
   &emsp; 4.4.2. [Unlinking a person and an internship : `unlink`](#442-unlinking-a-person-and-an-internship--unlink)<br>
   4.5. [Find Command](#45-find-command)<br>
   &emsp; 4.5.1. [Locating persons by name : `find -p`](#451-locating-persons-by-name--find--p)<br>
   &emsp; 4.5.2. [Locating internships by name : `find -i`](#452-locating-internships-by-name--find--i)<br>
   4.6. [Delete Command](#46-delete-command)<br>
   &emsp; 4.6.1. [Deleting a person by index : `delete -p`](#461-deleting-a-person-by-index--delete--p)<br>
   &emsp; 4.6.2. [Deleting an internship by index : `delete -i`](#462-deleting-an-internship-by-index--delete--i)<br>
   4.7. [Sort Command](#47-sort-command)<br>
   &emsp; 4.7.1. [Sorting persons in the list: `sort -p`](#471-sorting-persons-in-the-list-sort--p)<br>
   &emsp; 4.7.2. [Sorting internships in the list: `sort -i`](#472-sorting-internships-in-the-list-sort--i)<br>
   4.8. [General](#48-general)<br>
   &emsp; 4.8.1. [Viewing help : `help`](#481-viewing-help--help)<br>
   &emsp; 4.8.2. [Clearing all entries : `clear`](#482-clearing-all-entries--clear)<br>
   &emsp; 4.8.3. [Exiting the program : `exit`](#483-exiting-the-program--exit)<br>
   4.9. [User Interface](#49-user-interface)<br>
   &emsp; 4.9.1. [Dark Mode](#491-dark-mode)<br>
   &emsp; 4.9.2. [Light Mode](#492-light-mode)<br>
   4.10. [Data files](#410-data-files)<br>
   &emsp; 4.10.1. [Saving the data](#4101-saving-the-data)<br>
   &emsp; 4.10.2. [Editing the data file](#4102-editing-the-data-file)<br>
5. [FAQ](#5-faq)
6. [Command summary](#6-command-summary) 

--------------------------------------------------------------------------------------------------------------------
# 1. Introduction

## 1.1. What is InterNUS?

Welcome to the InterNUS user guide!

InterNUS is a convenient and powerful desktop app for NUS CS students to **manage internship applications, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It has a sleek design to track your internship status at one glance. 

Having troubles finding who to contact for your various internship applications? Don’t worry! InterNUS also allows you to manage contact person during your applications. After your internship, InterNUS can still be used to manage your work contact list!

## 1.2. Person List

Store the information of key contacts in InterNUS. During the internship-hunting period, add the contacts of hiring managers and link them to your bookmarked internship positions. What about the multiple colleagues you'll meet during your internship stints? InterNUS makes it easy to manage these contacts and lets you see at a glance where you worked at together.

<div markdown="block" class="alert alert-info">
**:information_source: Note:** Each person can be linked to a maximum of one internship, and vice  versa.
</div>

## 1.3. Internship List

Track the internships you're interested in and all the relevant information -- the company, role, contact person and interview date. Awaiting a reply, or have you been accepted? With InterNUS, you can monitor and update the statuses of your internship applications effortlessly.

--------------------------------------------------------------------------------------------------------------------
# 2. How to use this User Guide?

We have created this user manual for new users who want to use our app and returning users who need reference to the command summary. 
For new users you can proceed to our [Quick start](#3-quick-start) while returning users can skip to our [Command summary](#6-command-summary).


## 2.1. Icons and symbols

| Symbol               | Meaning                                                                            |
|----------------------|------------------------------------------------------------------------------------|
| :information_source: | Additional information that may be useful to know when using InterNUS              |
| :exclamation:        | Important information or warnings that you should take note of when using InterNUS | 

## 2.2. Graphical User Interface (GUI) of InterNUS

|    ![Ui](images/interNUS.png)     |
|:---------------------------------:|
| InterNUS GUI component annotation |



The UI component description:

| Components             | Purpose                                |
|------------------------|----------------------------------------|
| Menu Bar               | Consists of File, Help and Color Theme |
| Color Theme Button     | To switch between Light/Dark mode      | 
| Command Input          | To enter commands to be executed       |
| System Message Display | Displays results of executed command   |
| Person List            | Displays contact information of person |
| Internship List        | Displays information of internship     |

## 2.3. Command format

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

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `listp`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

--------------------------------------------------------------------------------------------------------------------
# 3. Quick start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `InterNUS.jar` from [here](https://github.com/AY2223S1-CS2103T-F11-1/tp/releases).

3. Copy the file to the folder which you want to use as the _home folder_ for your app.

4. Double-click the jar file to open the app. If the app does not respond, open your terminal in the current folder where `InterNUS.jar` is located and enter `java -jar InterNUS.jar` to open the app.

5. The screen should appear in a few seconds when the app first launches as shown in the figure below. Note how the app contains some sample data.

|     ![Ui](images/Ui.png)      |
|:-----------------------------:|
| InterNUS GUI with sample data |

   

7. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

8. Refer to the [Features](#4-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# 4. Features

## 4.1. Add Command

### 4.1.1. Adding a person: `add -p`

Saves a contact person into InterNUS, from the hiring manager you liase with during the application process to the senior engineer you work with during the internship.

Format: `add -p n/NAME [e/EMAIL] [p/PHONE_NUMBER] [t/TAG]…​ [l/LINK_INDEX] c/[COMPANY]`
* The link index (in add -p) refers to the index number shown in the internship list.
* The company refers to the company the contact person is working at.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0). 
New added person will be added in current sorted order.
Only one contact person can be in-charge of one internship position.
Phone number allows more than 2 digits without the need of any specific pattern.
A person is only considered duplicated if the name is exactly the same including casing. 
</div>

Examples:
* `add -p` followed by `n/John Doe e/johnd@example.com p/98765432 l/1 c/Meta` 
adds the name, email, phone number and company and link to the internship with index **1**.
* `add -p` followed `n/Betsy Crowe t/friend e/betsycrowe@example.com` 
adds the name, tags and email.

### 4.1.2. Adding an internship: `add -i`

Adds an Internship to InterNUS.

Format: `add -i c/COMPANY_NAME r/ROLE s/STATUS [d/DATE_OF_INTERVIEW] [l/LINK_INDEX]`

* The link index (in add -i) refers to the index number shown in the person list.
* Valid statuses are `BOOKMARKED`, `PENDING`, `ACCEPTED`, `COMPLETED` or `REJECTED` (case insensitive).
* Date of interview is optional as interviews might not be scheduled yet.
* `LINK_INDEX` refers to the index number shown in the person list and is optional. Specifying this parameter will define the current person at the specified index in the person list as the contact person of the newly added internship. 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** Instead of typing the full status name, just enter the first letter of the intended status (e.g. `s/b` is a shortcut for `s/BOOKMARKED`)**
</div>

Examples:
* `add -i c/TikTok r/Data Engineer s/rejected l/1`
* `add -i c/Grab r/Full Stack Developer s/PENDING d/2020-12-20 12:00`

## 4.2. List Command

### 4.2.1. Listing all persons : `list -p`

Removes all filters on the person list and lists all persons in InterNUS in the current sorted order.

Format: `list -p`

### 4.2.2. Listing all internships : `list -i`

Removes all filters on the internship list and lists all internships in InterNUS in the current sorted order.

Format: `list -i`

## 4.3. Edit Command

### 4.3.1. Editing a person : `edit -p`

Edits an existing person in InterNUS.

Format: `edit -p INDEX [n/NAME] [p/PHONE] [e/EMAIL] [c/COMPANY] [t/TAG]…​`
- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
- You can remove all the person’s tags by typing `t/` without specifying any tags after it.
- Does not allow duplicate name when editing name of person

Examples:
- `list -p` followed by `edit -p 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
- `list -p` followed by `edit -p 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### 4.3.2. Editing an internship : `edit -i`

Edits an existing internship in InterNUS.

Format: `edit -i INDEX [c/COMPANY] [r/ROLE] [s/STATUS] [d/INTERVIEW_DATE]`
- Edits the internship at the specified `INDEX`. The index refers to the index number shown in the displayed internship list. The index must be a positive integer 1, 2, 3, …
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- Valid statuses are `BOOKMARKED`, `PENDING`, `ACCEPTED`, `COMPLETED` or `REJECTED` (case insensitive). Similar to the `add  -i` command, the shortcuts can be used here.

Examples:
- `list -i` followed by `edit -i 1 s/ACCEPTED` Edits the status of the 1st internship to be `ACCEPTED`.
- `list -i` followed by `edit -i 2 s/REJECTED` Edits the status of the 2nd internship to `REJECTED`.

## 4.4. Link and Unlink Command

### 4.4.1. Linking a person and an internship : `link`

Links an existing person and internship together in InterNUS.

Format: `link p/PERSON_INDEX i/INTERNSHIP_INDEX`
- Person at specified `PERSON_INDEX` and internship at specified `INTERNSHIP_INDEX` must be initially not linked to any person/internship.
- Specified person will be displayed as contact person of specified internship and specified internship will be displayed as internship of specified person.
- `PERSON_INDEX` and `INTERNSHIP_INDEX` **must be a positive integer** 1, 2, 3, …

Examples:
- `link p/1 i/1` will link the person at index **1** to the internship at index **1**

### 4.4.2. Unlinking a person and an internship : `unlink`

Unlinks an existing person and internship together in InterNUS.

Format: `unlink [p/PERSON_INDEX] [i/INTERNSHIP_INDEX]`
- At least 1 of the optional fields must be provided
- Person and/or internship at the specified `PERSON_INDEX` and/or `INTERNSHIP_INDEX` will be unlinked.
- If both `PERSON_INDEX` and `INTERNSHIP_INDEX` are provided, the person at `PERSON_INDEX` and the internship at `INTERNSHIP_INDEX` must be linked to each other.
- `PERSON_INDEX` and `INTERNSHIP_INDEX` **must be a positive integer** 1, 2, 3, …

Examples:
- `unlink p/1 i/1` will unlink person at index **1** and internship at index **1**
- `unlink p/1` will unlink person at index **1** and its linked internship
- `unlink i/1` will unlink internship at index **1** and its linked person

## 4.5. Find Command

### 4.5.1. Locating persons by name : `find -p`

Finds persons whose fields contain any of the given keywords.

Format: `find -p [n/ NAME_KEYWORD [MORE_KEYWORDS]...] [p/ PHONE_KEYWORD [MORE_KEYWORDS]...] [e/ EMAIL_KEYWORD [MORE_KEYWORDS]...] [t/ TAG_KEYWORD [MORE_KEYWORDS]...] [c/ COMPANY_KEYWORD [MORE_KEYWORDS]...]`
- The search is case-insensitive. e.g **hans** will match **Hans**
- The order of the keywords does not matter. e.g. **Hans Bo** will match **Bo Hans**
- Only the fields corresponding to the specified prefixes will be searched,
  and all the specified fields must contain at least one of the specified keywords to be considered in the search result.
- Partial words will be matched. e.g. **Han** will match **Hans**

Examples:
- `find -p n/John` returns **john**, **John Doe** and **Johnny**


### 4.5.2. Locating internships by name : `find -i`

Finds internships whose fields contain any of the given keywords.

Format: `find -i [c/ COMPANY_NAME_KEYWORD [MORE_KEYWORDS]...] [r/ INTERNSHIP_ROLE_KEYWORD [MORE_KEYWORDS]...] [s/ INTERNSHIP_STATUS_KEYWORD [MORE_KEYWORDS]...] [d/ INTERVIEW_DATE_KEYWORD [MORE_KEYWORDS]...]`
- The search is case-insensitive. e.g **abc pte ltd** will match **ABC Pte Ltd**.
- The order of the keywords does not matter. e.g. **Ltd ABC Pte Constructions** will match **ABC Constructions Pte Ltd**.
- Only the fields corresponding to the specified prefixes will be searched,
  and all the specified fields must contain at least one of the specified keywords to be considered in the search result.
- Partial words will be matched e.g. **app** will match **Apple** and **applications**.

Example of usage:
The app contains the following company names in the internship list.
- Apple Inc
- Alphabet Inc
- Shopee Pte Ltd
- Sea Ltd
- Google Inc
- Garena

Then,
- `find -i c/ltd` returns **Shopee Pte Ltd** and **Sea Ltd**
- `find -i c/inc ltd` returns **Apple Inc**, **Alphabet Inc**, **Google Inc**, **Shopee Pte Ltd** and **Sea Ltd**
- `find -i c/g` returns **Google Inc** and **Garena**

<div markdown="block" class="alert alert-info">
**:information_source: Note:** The shortcuts for internship statuses don't work here.
</div>

## 4.6. Delete Command

### 4.6.1. Deleting a person by index : `delete -p`

Deletes the specified person from InterNUS.

Format: `delete -p INDEX`

* Deletes the person with the specific `INDEX` in the person list.
* The index refers to the index number shown in the currently displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list -p` followed by `delete -p 2` deletes the 2nd person in InterNUS.
* `find -p Betsy` followed by `delete -p 1` deletes the 1st person in the results of the `find` command.

### 4.6.2. Deleting an internship by index : `delete -i`

Deletes the specified internship from InterNUS.

Format: `delete -i INDEX`

* Deletes the internship with the specific `INDEX` in the internship list.
* The index refers to the index number shown in the currently displayed internship list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list -i` followed by `delete -i 2` deletes the 2nd internship in InterNUS.
* `find -i Meta` followed by `delete -i 1` deletes the 1st internship in the results of the `find` command.

## 4.7. Sort Command

### 4.7.1. Sorting persons in the list: `sort -p`

Sorts the persons list given a sorting criterion.

Format: `sort -p [n/] [c/]`
- Exactly one of the optional fields must be provided.
- `n/` sorts persons by their names in alphabetical order, ignoring upper and lower cases.
- `c/` sorts persons by the attached company name (not the company tied to the internship linked) in alphabetical order, ignoring upper and lower cases.
- When sorted by the attached company name, persons without an attached company name are listed at the bottom of the list, and they will be sorted in alphabetical order of their own names, ignoring upper and lower cases.
- The list will remain sorted the same way until InterNUS is closed or until the sort condition for the persons list is changed.

Example:
- `sort -p n/` would sort the persons list by their names in alphabetical order, ignoring upper and lower cases.
- `sort -p c/` would sort the persons list by their attached company names in alphabetical order, ignoring upper and lower cases. Persons without an attached company name would be listed at the bottom of the list, and they will be sorted in alphabetical order of their own names, ignoring upper and lower cases.

### 4.7.2. Sorting internships in the list: `sort -i`

Sorts the internship list given a sorting criterion.

Format: `sort -i [c/] [d/] [s/]`
- Exactly one of the optional fields must be provided.
- `c/` sorts internships by company name (in alphabetical order).
- `d/` sorts internships by interview date.
- `s/` sorts internships by status in the given order: `BOOKMARKED`, `PENDING`, `ACCEPTED`, `COMPLETED`, `REJECTED`.
- When sorted by interview date, internships with no interview dates are listed at the bottom of the list.

<div markdown="block" class="alert alert-info">
**:information_source: Note: ** <br>
Both the person list and internship list will remain sorted by the last given criterion until InterNUS is closed. The sorted order persists during additions, deletions and editing of persons and internships.
</div>

## 4.8. General

### 4.8.1. Viewing help : `help`

Displays a list of commands and a link to the user guide.

Format: `help`

### 4.8.2. Clearing all entries : `clear`

Clears all person and internship entries from InterNUS.

Format: `clear`

### 4.8.3. Exiting the program : `exit`

Exits the program.

Format: `exit`

## 4.9. User Interface

Click on the Color Theme button in the Menu Bar to display a dropdown list to select between Light/Dark mode.

### 4.9.1 Dark Mode

|     ![Ui](images/darktheme.png)     |
|:-----------------------------------:|
| InterNUS dark mode with sample data |


### 4.9.2 Light Mode

|     ![Ui](images/lightheme.png)      |
|:------------------------------------:|
| InterNUS light mode with sample data |

## 4.10. Data files

### 4.10.1. Saving the data

InterNUS data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 4.10.2. Editing the data file

InterNUS data is saved as a JSON file. `[JAR file location]`/data/addressbook.json.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InterNUS will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## 5. FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InterNUS home folder.

--------------------------------------------------------------------------------------------------------------------

## 6. Command summary

| Action                | Format, Examples                                                                                                                                                                                                                       |
|-----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add person**        | `add -p n/NAME [e/EMAIL] [p/PHONE] [l/LINK_INDEX] [c/COMPANY] [t/TAG]… ` <br> e.g., `add -p n/James Ho e/jamesho@example.com p/22224444 l/3 c/Meta`                                                                                    |
| **Add internship**    | `add -i c/COMPANY_NAME r/ROLE s/STATUS [d/DATE_OF_INTERVIEW] [l/LINK_INDEX]` <br> e.g., `add -i n/TikTok r/Data Engineer s/rejected l/1`                                                                                               |
| **Clear**             | `clear`                                                                                                                                                                                                                                |
| **Delete person**     | `delete -p INDEX`<br> e.g., `delete -p 3`                                                                                                                                                                                              |
| **Delete internship** | `delete -i INDEX`<br> e.g., `delete -i 1`                                                                                                                                                                                              |
| **Edit person**       | `edit -p INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [c/COMPANY] [t/TAG]…`<br> e.g.,`edit -p 2 n/James Lee e/jameslee@example.com`                                                                                                       |
| **Edit internship**   | `edit -i INDEX [c/COMPANY] [r/ROLE] [s/STATUS] [d/INTERVIEW_DATE]`<br> e.g.,`edit -i 2 s/REJECTED`, `edit -i 3 d/2023-01-01 13:30`                                                                                                     |
| **Find person**       | `find -p [n/ NAME_KEYWORD [MORE_KEYWORDS]...] [p/ PHONE_KEYWORD [MORE_KEYWORDS]...] [e/ EMAIL_KEYWORD [MORE_KEYWORDS]...] [t/ TAG_KEYWORD [MORE_KEYWORDS]...] [c/ COMPANY_KEYWORD [MORE_KEYWORDS]...]` <br> e.g., `find -p n/John`     |
| **Find internship**   | `find -i [c/ COMPANY_NAME_KEYWORD [MORE_KEYWORDS]...] [r/ INTERNSHIP_ROLE_KEYWORD [MORE_KEYWORDS]...] [s/ INTERNSHIP_STATUS_KEYWORD [MORE_KEYWORDS]...] [d/ INTERVIEW_DATE_KEYWORD [MORE_KEYWORDS]...]` <br> e.g., `find -i c/inc ltd` |
| **List persons**      | `list -p`                                                                                                                                                                                                                              |
| **List internships**  | `list -i`                                                                                                                                                                                                                              |
| **Link**              | `link p/PERSON_INDEX i/INTERNSHIP_INDEX`<br> e.g., `link p/1 i/1`                                                                                                                                                                      |
| **Unlink**            | `unlink [p/PERSON_INDEX] [i/INTERNSHIP_INDEX]`<br> e.g., `unlink p/1`                                                                                                                                                                  |
| **Sort persons**      | `sort -p [n/] [c/]` <br> e.g., `sort -p c/`                                                                                                                                                                                            |
| **Sort internships**  | `sort -i [c/] [d/] [s/]` <br> e.g., `sort -i d/`                                                                                                                                                                                       |
| **Help**              | `help`                                                                                                                                                                                                                                 |
