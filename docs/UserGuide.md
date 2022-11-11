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
   &emsp; 4.5.1. [Finding persons : `find -p`](#451-finding-persons--find--p)<br>
   &emsp; 4.5.2. [Finding internships : `find -i`](#452-finding-internships--find--i)<br>
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
6. [Planned updates](#6-planned-updates)
7. [Command summary](#7-command-summary) 

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

# 1. Introduction

## 1.1. What is InterNUS?

Welcome to the **InterNUS** user guide!

InterNUS is a convenient and powerful desktop app, created to help **NUS CS students manage their internship applications.**
It is optimized for use via a Command Line Interface (CLI),
and complemented with a simple yet intuitive Graphical User Interface (GUI)
designed to help you keep track of all your applications at a glance.

Having trouble remembering who to contact for your various applications? Don’t worry! 
InterNUS can also help you keep track of all the contacts you meet along the way, 
from the start of the application process to the end of the internship programme and beyond!

## 1.2. Person List

Store the information of key contacts in InterNUS. During the internship-hunting period, add the contacts of hiring managers and link them to your bookmarked internship positions. What about the colleagues you'll meet during your internship stints? InterNUS makes it easy to manage these contacts and lets you see at a glance where you worked at together.

<div markdown="block" class="alert alert-info">
**:information_source: Note:** Each person can be linked to a maximum of one internship, and vice  versa.
</div>

## 1.3. Internship List

Track the internships you're interested in and all the relevant information -- the company, role, contact person and interview date. Awaiting a reply, or have you been accepted? With InterNUS, you can monitor and update the statuses of your internship applications effortlessly.

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

# 2. How to use this User Guide?

We created this user manual for new users who want to use our app and for returning users who need a reference to the command summary. 
For new users, you can proceed to our [Quick start](#3-quick-start) section while returning users can skip to our [Command summary](#7-command-summary).


## 2.1. Icons and symbols

| Symbol               | Meaning                                                                        |
|----------------------|--------------------------------------------------------------------------------|
| :information_source: | Important information about specific behaviour of key features to take note of |
| :bulb:               | Tips that can make certain features of InterNUS quicker and easier to use      |
| :exclamation:        | Cautionary information to avoid unintended behaviour during usage of InterNUS  | 

<div style="page-break-after: always;"></div>

## 2.2. Graphical User Interface (GUI) of InterNUS

|    ![Ui](images/interNUS.png)     |
|:---------------------------------:|
| InterNUS GUI component annotation |



The UI components description:

| Components             | Purpose                                                  |
|------------------------|----------------------------------------------------------|
| Menu Bar               | Consists of File, Help and Color Theme                   |
| Color Theme Button     | To switch between light mode and dark mode               | 
| Command Input          | To enter commands to be executed                         |
| System Message Display | Displays results of executed commands and error messages |
| Person List            | Displays contact information of persons                  |
| Internship List        | Displays information of internships                      |

<div style="page-break-after: always;"></div>

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list -p`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

# 3. Quick start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `InterNUS.jar` from [here](https://github.com/AY2223S1-CS2103T-F11-1/tp/releases).

3. Copy the file to the folder which you want to use as the _home folder_ for your app.

4. Double-click the jar file to open the app. If the app does not respond, open your terminal in the current folder where `InterNUS.jar` is located and enter `java -jar InterNUS.jar` to open the app.

5. The screen should appear in a few seconds when the app first launches as shown in the figure below. Note how the app contains some sample data.

    |  ![Ui](images/darktheme.png)  |
    |:-----------------------------:|
    | InterNUS GUI with sample data |

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.

7. Refer to the [Features](#4-features) below for details of each command.

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

# 4. Features

## 4.1. Add Command

### 4.1.1. Adding a person: `add -p`

Saves a contact person into InterNUS, from the hiring manager you liaise with 
during the application process to the senior engineer you work with during the internship.

Format: `add -p n/NAME [e/EMAIL] [p/PHONE_NUMBER] [t/TAG]…​ [l/LINK_INDEX] [c/COMPANY]`
* `PHONE_NUMBER` can only be numeric and consists of at least 3 numbers.
* `LINK_INDEX` refers to the index number shown in the internship list and is optional.
    * Specifying this parameter will define the internship at the specified index in the internship list as the internship of the new person.
    * An internship is assumed to have at most one contact person, and a person is assumed to be in charge of at most one internship position.
    * If the internship at the specified index is already an internship for another person,
      the linking between the person and the internship will fail with a warning, but the person will still be added.
* `TAG` can only be alphanumeric and be one word.
* `COMPANY` refers to the company the contact person is working at.
* `EMAIL`,`PHONE`,`TAG`,`COMPANY` are optional as user might not have all the information of contact person.

<div markdown="block" class="alert alert-info">
:information_source: **Note:** 

* Duplicate persons are not allowed.
  A person is considered to be a duplicate if there already exists a person in the list with the exact same name (case-sensitive).
* Adding a person maintains the current sorted order of the display list (as opposed to adding to the back of the list).
  By default, the list is sorted by date of creation. Refer to [this note under section 4.7.1 for how the sorting works](#471-sorting-persons-in-the-list-sort--p).
* A person is assumed to be in charge of at most one internship position.
* A person can have any number of tags (including 0).
</div>

Examples:
* `add -p n/John Doe e/johnd@example.com p/98765432 l/1 c/Meta` adds a person with `NAME` **John Doe**, `EMAIL` **johnd@example.com**, `PHONE` **98765432** and `COMPANY` **Meta**.
  If the internship at index **1** of the internship list is not linked to any other person, this internship will be linked as the internship for this person. To unlink this person and internship, see [section 4.4.2 for the Unlink command](#442-unlinking-a-person-and-an-internship--unlink).
* `add -p n/Betsy Crowe t/HR e/betsycrowe@example.com` adds a person with `NAME` **Betsy Crowe**, `TAG` **HR**, `EMAIL` **betsycrowe@example.com**
  Here, since the `LINK_INDEX` field is omitted, the person will be added with **No internship linked**. To link an internship to this person, see [section 4.4.1 for the Link command](#441-linking-a-person-and-an-internship--link).

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### 4.1.2. Adding an internship: `add -i`

Saves an internship into InterNUS. InterNUS will keep track of important details such as
the internship's contact person, application status and interview date (if any).

Format: `add -i c/COMPANY_NAME r/ROLE s/STATUS [d/INTERVIEW_DATE] [l/LINK_INDEX]`

* `LINK_INDEX` refers to the index number shown in the person list and is optional. 
    * Specifying this parameter will define the person at the specified index in the person list as the contact person of the new internship. 
    * An internship is assumed to have at most one contact person, and a person is assumed to be in charge of at most one internship position. 
    * If the person at the specified index is already a contact person for another internship, 
      the linking between the person and the internship will fail with a warning, but the internship will still be added. 
* `STATUS` can only be `BOOKMARKED`, `PENDING`, `ACCEPTED`, `COMPLETED` or `REJECTED` (case-insensitive). 
* `DATE_OF_INTERVIEW` is optional as interviews might not be scheduled yet. 


<div markdown="block" class="alert alert-info">
:information_source: **Note:** 

* Duplicate internships are not allowed. 
  An internship is considered to be duplicate if there already exists an internship in the list with the exact same company name and role (case-sensitive).
* Adding internships maintains the current sorted order of the display list (as opposed to adding to the back of the list). 
  By default, the list is sorted by date of creation. Refer to [this note under section 4.7.2 for how the sorting works](#472-sorting-internships-in-the-list-sort--i).
</div>

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** Instead of typing the full `STATUS` name, just enter the first letter of the intended `STATUS` (e.g. `s/b` is a shortcut for `s/BOOKMARKED`).
</div>

Examples:
* `add -i c/TikTok r/Data Engineer s/rejected l/1` adds an internship with `COMPANY_NAME` **TikTok**, `ROLE` **Data Engineer**, and `STATUS` **REJECTED**. 
  If the person at index **1** of the person list is not linked to any other internship, this person will be linked as the contact person for this internship. To unlink this person and internship, see [section 4.4.2 for the Unlink command](#442-unlinking-a-person-and-an-internship--unlink).
* `add -i c/Grab r/Full Stack Developer s/PENDING d/2020-12-20 12:00` adds an internship with `COMPANY_NAME` **Grab**, `ROLE` **Full Stack Developer**, `STATUS` **PENDING** and `INTERVIEW_DATE` **2020-12-20 12:00**.
  Here, since the `LINK_INDEX` field is omitted, the internship will be added with **No contact person**. To link a person to this internship, see [section 4.4.1 for the Link command](#441-linking-a-person-and-an-internship--link).

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 4.2. List Command

### 4.2.1. Listing all persons : `list -p`

Removes all filters on the person list and lists all persons in InterNUS in the current sorted order.

Format: `list -p`

### 4.2.2. Listing all internships : `list -i`

Removes all filters on the internship list and lists all internships in InterNUS in the current sorted order.

Format: `list -i`

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 4.3. Edit Command

### 4.3.1. Editing a person : `edit -p`

Edits an existing person in InterNUS.

Format: `edit -p INDEX [n/NAME] [p/PHONE] [e/EMAIL] [c/COMPANY] [t/TAG]…​`
- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
- You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:
- `list -p` followed by `edit -p 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
- `list -p` followed by `edit -p 2 n/Betsy Crower t/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### 4.3.2. Editing an internship : `edit -i`

Edits an existing internship in InterNUS.

Format: `edit -i INDEX [c/COMPANY] [r/ROLE] [s/STATUS] [d/INTERVIEW_DATE]`
- Edits the internship at the specified `INDEX`. The `INDEX` refers to the index number shown in the displayed internship list. The `INDEX` must be a positive integer 1, 2, 3, …
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- Valid statuses are `BOOKMARKED`, `PENDING`, `ACCEPTED`, `COMPLETED` or `REJECTED` (case-insensitive). Similar to the `add -i` command, the shortcuts can be used here.

Examples:
- `list -i` followed by `edit -i 1 s/ACCEPTED` edits the status of the 1st internship to be `ACCEPTED`.
- `list -i` followed by `edit -i 2 s/REJECTED` edits the status of the 2nd internship to `REJECTED`.

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 4.4. Link and Unlink Command

### 4.4.1. Linking a person and an internship : `link`

Links an existing person and internship together in InterNUS.

Format: `link p/PERSON_INDEX i/INTERNSHIP_INDEX`
- The person at the specified `PERSON_INDEX` and the internship at the specified `INTERNSHIP_INDEX` must be initially not linked to any person/internship.
- The specified person will be displayed as the contact person of the specified internship. Likewise, the specified internship will be displayed as the internship of the specified person.
- `PERSON_INDEX` and `INTERNSHIP_INDEX` must be positive integers 1, 2, 3, …

Examples:
- `link p/1 i/1` links the person at index **1** to the internship at index **1**.

### 4.4.2. Unlinking a person and an internship : `unlink`

Unlinks an existing person and internship together in InterNUS.

Format: `unlink [p/PERSON_INDEX] [i/INTERNSHIP_INDEX]`
- At least 1 of the optional fields must be provided.
- The person/internship at the specified `PERSON_INDEX`/`INTERNSHIP_INDEX` will be unlinked.
- If both `PERSON_INDEX` and `INTERNSHIP_INDEX` are provided, the specified person and internship must be linked to each other.
- `PERSON_INDEX` and `INTERNSHIP_INDEX` must be a positive integer 1, 2, 3, …

Examples:
- `unlink p/1 i/1` unlinks person at index **1** and internship at index **1**.
- `unlink p/1` unlinks person at index **1** and its linked internship.
- `unlink i/1` unlinks internship at index **1** and its linked person.

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## 4.5. Find Command

### 4.5.1. Finding persons : `find -p`

Finds persons whose fields contain any of the given keywords.

Format: `find -p [n/ NAME_KEYWORDS...] [p/ PHONE_KEYWORDS...] [e/ EMAIL_KEYWORDS...] [t/ TAG_KEYWORDS...] [c/ COMPANY_KEYWORDS...]`
- At least one of the above prefixes (`n/`, `p/`, `e/`, `t/`, `c/`) should be present to perform the command.
- The search is case-insensitive. e.g **hans** will match **Hans**.
- The order of the keywords does not matter. e.g. **Hans Bo** will match **Bo Hans**.
- Only the fields corresponding to the specified prefixes will be searched,
  and all the specified fields must contain at least one of the specified keywords to be added to the search result.
- Absent fields will not be searched. e.g. **No company** will not find persons with blank company fields.
- Partial words will be matched. e.g. **Han** will match **Hans**.

<div markdown="block" class="alert alert-info">
**:information_source: Note:** The command input is flexible in that if a prefix is present but no keywords are supplied, it will simply not search that particular field. 
This means you can do things like `find -p n/`, i.e. find persons with any name, to find all persons. 
</div>

Examples:
- `find -p n/john jon` finds persons with `NAME` that contains **john** or **jon**.
- `find -p e/@u.nus.edu @gmail.com` finds persons with `EMAIL` that contains **@u.nus.edu** or **@gmail.com**.
- `find -p t/supervisor HR` finds persons with `TAG` that contains **supervisor** or **HR**.
- `find -p n/john jon e/@u.nus.edu @gmail.com t/supervisor HR` finds persons with all 3 of the above.
  e.g. A person with `NAME` **Jonathan**, `EMAIL` **jonathan@gmail.com**, and `TAG` **HR** can be found with this command.

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### 4.5.2. Finding internships : `find -i`

Finds internships whose fields contain any of the given keywords.

Format: `find -i [c/ COMPANY_NAME_KEYWORDS...] [r/ ROLE_KEYWORDS...] [s/ STATUS_KEYWORDS...] [d/ INTERVIEW_DATE_KEYWORDS...]`
- At least one of the above prefixes (`c/`, `r/`, `s/`, `d/`) should be present to perform the command.
- The search is case-insensitive. e.g **abc pte ltd** will match **ABC Pte Ltd**.
- The order of the keywords does not matter. e.g. **Ltd ABC Pte Constructions** will match **ABC Constructions Pte Ltd**.
- Only the fields corresponding to the specified prefixes will be searched,
  and all the specified fields must contain at least one of the specified keywords to be added to the search result.
- Absent fields will not be searched. e.g. **No interviews scheduled** will not find internships with blank interview dates.
- Partial words will be matched e.g. **app** will match **Apple** and **applications**. 

<div markdown="block" class="alert alert-info">
**:information_source: Note:** The command input is flexible in that if a prefix is present but no keywords are supplied, it will simply not search that particular field. 
This means you can do things like `find -i c/`, i.e. find internships with any company name, to find all internships. 
</div>

Examples:
- `find -i c/pte ltd` finds internships with `COMPANY_NAME` that contains **pte** or **ltd**. 
- `find -i r/engineer analyst` finds internships with `ROLE` that contains **engineer** or **analyst**. 
- `find -i s/pending bookmarked` finds internships with `STATUS` that starts with **pending** or **bookmarked**. 
- `find -i c/pte ltd r/engineer analyst s/pending bookmarked` finds internships with all 3 of the above. 
  e.g. An internship with `COMPANY_NAME` **ABC LTD**, `ROLE` **Data Analyst**, and `STATUS` **BOOKMARKED** can be found with this command. 

<div markdown="span" class="alert alert-warning">
:exclamation: **Caution:** For `STATUS` searching in particular, the `STATUS` must also start with the keyword. 
i.e. `find -i s/a` will only find internships with `STATUS` **ACCEPTED** even though both **ACCEPTED** and **BOOKMARKED** 
contain the letter **a**. This is to match the behaviour of `STATUS` shortcuts as mentioned in [this tip under section 4.1.2](#412-adding-an-internship-add--i).
</div>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 4.6. Delete Command

### 4.6.1. Deleting a person by index : `delete -p`

Deletes the specified person from InterNUS.

Format: `delete -p INDEX`

* Deletes the person with the specific `INDEX` in the person list.
* The `INDEX` refers to the index number shown in the currently displayed person list.
* The `INDEX` must be a positive integer 1, 2, 3, …

Examples:
* `list -p` followed by `delete -p 2` deletes the 2nd person in InterNUS.
* `find -p n/Betsy` followed by `delete -p 1` deletes the 1st person in the results of the `find` command.

### 4.6.2. Deleting an internship by index : `delete -i`

Deletes the specified internship from InterNUS.

Format: `delete -i INDEX`

* Deletes the internship with the specific `INDEX` in the internship list.
* The `INDEX` refers to the index number shown in the currently displayed internship list.
* The `INDEX` must be a positive integer 1, 2, 3, …

Examples:
* `list -i` followed by `delete -i 2` deletes the 2nd internship in InterNUS.
* `find -i c/Meta` followed by `delete -i 1` deletes the 1st internship in the results of the `find` command.

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 4.7. Sort Command

### 4.7.1. Sorting persons in the list: `sort -p`

Sorts the person list given a sorting criterion.

Format: `sort -p [n/] [c/]`
- Exactly one of the optional fields must be provided.
- `n/` sorts persons by their names in alphabetical order, ignoring upper and lower cases.
- `c/` sorts persons by the attached company name (not the company tied to the internship linked) in alphabetical order, ignoring upper and lower cases.

Examples:
- `sort -p n/` sorts the person list by their names in alphabetical order, ignoring upper and lower cases.
- `sort -p c/` sorts the person list by their attached company names in alphabetical order, ignoring upper and lower cases. 

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
- The person list will remain sorted by the last given criterion until InterNUS is closed.
- The sorted order persists during additions, deletions and editing of persons.
- When sorted by the attached company name, persons without an attached company name are listed at the bottom of the list, and they will be sorted in alphabetical order of their own names, ignoring upper and lower cases.
</div>

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### 4.7.2. Sorting internships in the list: `sort -i`

Sorts the internship list given a sorting criterion.

Format: `sort -i [c/] [d/] [s/]`
- Exactly one of the optional fields must be provided.
- `c/` sorts internships by company name (in alphabetical order).
- `d/` sorts internships by interview date.
- `s/` sorts internships by status in the given order: `BOOKMARKED`, `PENDING`, `ACCEPTED`, `COMPLETED`, `REJECTED`.

Examples:
* `list -i` followed by `sort -i c/` sorts all internships in InterNUS by company name.
* `find -i c/Meta` followed by `sort -i s/` sorts all internships at **Meta** by status.

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
- The internship list will remain sorted by the last given criterion until InterNUS is closed.
- The sorted order persists during additions, deletions and editing of internships.
- When sorted by interview date, internships with no interview dates appear at the bottom of the list.
</div>

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

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

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 4.9. User Interface

Click on the Color Theme button in the Menu Bar to display a dropdown list to select between Light/Dark mode.

### 4.9.1 Dark Mode

|     ![Ui](images/darktheme.png)     |
|:-----------------------------------:|
| InterNUS dark mode with sample data |

<div style="page-break-after: always;"></div>

### 4.9.2 Light Mode

|     ![Ui](images/lightheme.png)      |
|:------------------------------------:|
| InterNUS light mode with sample data |

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 4.10. Data files

### 4.10.1. Saving the data

InterNUS data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 4.10.2. Editing the data file

InterNUS data is saved as a JSON file. `[JAR file location]`/data/addressbook.json.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file make its format invalid, InterNUS will discard all data and start with an empty data file at the next run.
</div>

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 5. FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app on the other computer and overwrite the data file it creates with your existing InterNUS data file. The data file can be found in the `data` directory of your home folder, and has the name `addressbook.json`.

**Q**: How do I find a contact person's linked internship?<br>
**A**: On the contact person's display, look for the linked internship's display name (shown as **Internship: COMPANY_NAME ROLE**), then use 
`find -i c/COMPANY_NAME r/ROLE` to find the linked internship.

**Q**: How do I find an internship's linked contact person?<br>
**A**: On the internship's display, look for the linked contact person's display name (shown as **Contact Person: NAME**), then use 
`find -p n/NAME` to find the linked contact person.

## 6. Planned updates

1. Improve the GUI to display the link between internships and their contact persons more clearly.
2. Improve the search functionalities across both lists. In particular, the finding of an internship's contact person or a contact person's internship could be made simpler.
3. Modify the checks for duplicate persons and internships to be smarter, as the current implementation does not consider the same name in a different case to be a duplicate name.
4. Modify certain fields to have a more flexible range of accepted input. In particular:
   1. Names currently do not allow special characters to be included, so inputs that contain strings like "s/o" will be rejected. 
      This applies to not just person names, but also company names and internship role names, where it is reasonable to allow other special characters as well.
   2. Phone numbers, similarly, do not allow special characters to be included, so extensions like "+65" or dashes in between digits will not be accepted as valid input.
   3. Tags are currently restricted to one word, but it is reasonable to allow tags consisting of more words.
   4. Interview dates are currently restricted to the format "yyyy-MM-dd HH:mm", so this could be improved to allow a greater variety of date and time formats. 
      Additionally, the time component of the input could be made optional.

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 7. Command summary

| Action                | Format, Examples                                                                                                                                        |
|-----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add person**        | `add -p n/NAME [e/EMAIL] [p/PHONE] [l/LINK_INDEX] [c/COMPANY] [t/TAG]… ` <br> e.g., `add -p n/James Ho e/jamesho@example.com p/22224444 l/3 c/Meta`     |
| **Add internship**    | `add -i c/COMPANY_NAME r/ROLE s/STATUS [d/INTERVIEW_DATE] [l/LINK_INDEX]` <br> e.g., `add -i n/TikTok r/Data Engineer s/rejected l/1`                   |
| **Clear**             | `clear`                                                                                                                                                 |
| **Delete person**     | `delete -p INDEX`<br> e.g., `delete -p 3`                                                                                                               |
| **Delete internship** | `delete -i INDEX`<br> e.g., `delete -i 1`                                                                                                               |
| **Edit person**       | `edit -p INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [c/COMPANY] [t/TAG]…`<br> e.g.,`edit -p 2 n/James Lee e/jameslee@example.com`                        |
| **Edit internship**   | `edit -i INDEX [c/COMPANY] [r/ROLE] [s/STATUS] [d/INTERVIEW_DATE]`<br> e.g.,`edit -i 2 s/REJECTED`, `edit -i 3 d/2023-01-01 13:30`                      |
| **Find person**       | `find -p [n/ NAME_KEYWORDS...] [p/ PHONE_KEYWORDS...] [e/ EMAIL_KEYWORDS...] [t/ TAG_KEYWORDS...] [c/ COMPANY_KEYWORDS...]` <br> e.g., `find -p n/John` |
| **Find internship**   | `find -i [c/ COMPANY_NAME_KEYWORDS...] [r/ ROLE_KEYWORDS...] [s/ STATUS_KEYWORDS...] [d/ INTERVIEW_DATE_KEYWORDS...]` <br> e.g., `find -i c/inc ltd`    |
| **List persons**      | `list -p`                                                                                                                                               |
| **List internships**  | `list -i`                                                                                                                                               |
| **Link**              | `link p/PERSON_INDEX i/INTERNSHIP_INDEX`<br> e.g., `link p/1 i/1`                                                                                       |
| **Unlink**            | `unlink [p/PERSON_INDEX] [i/INTERNSHIP_INDEX]`<br> e.g., `unlink p/1`                                                                                   |
| **Sort persons**      | `sort -p [n/] [c/]` <br> e.g., `sort -p c/`                                                                                                             |
| **Sort internships**  | `sort -i [c/] [d/] [s/]` <br> e.g., `sort -i d/`                                                                                                        |
| **Help**              | `help`                                                                                                                                                  |

<br>

> [Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
