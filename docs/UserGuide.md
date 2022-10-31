---
layout: page
title: User Guide
---

InterNUS is a desktop app for **managing internship applications, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, InterNUS can get your internship management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `InterNUS.jar` from [here](https://github.com/AY2223S1-CS2103T-F11-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your app.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

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

### Viewing help : `help`

Displays a list of commands and a link to the user guide.

![](../src/main/resources/images/help_summary.png)

Format: `help`


### Adding a person: `add -p`

Adds a person to InterNUS.

Format: `add -p n/NAME [e/EMAIL] [p/PHONE_NUMBER] [t/TAG]…​ [l/LINK_INDEX] c/[COMPANY]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0). Link index is optional.
The link index refers to the index number shown in the internship list. Company is optional.
The company refers to the company the contact person is working at. 
</div>

Examples:
* `add -p n/John Doe e/johnd@example.com p/98765432 l/1 c/Meta`
* `add -p n/Betsy Crowe t/friend e/betsycrowe@example.com`

### Adding an Internship: `add -i`

Adds an Internship to InterNUS.

Format: `add -i c/COMPANY_NAME r/ROLE s/STATUS [d/DATE_OF_INTERVIEW] [l/LINK_INDEX]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Date of interview can be left blank, since it is possible that an interview is not scheduled yet.
The link index refers to the index number shown in the person list and is optional. 
</div>

Examples:
* `add -i c/TikTok r/Data Engineer s/rejected l/1`
* `add -i c/Grab r/Full Stack Developer s/PENDING d/2020-12-20 12:00`

### Listing all persons : `list -p`

Shows a list of all persons in InterNUS.

Format: `list -p`

### Listing all internships : `list -i`

Shows a list of all internships in InterNUS.

Format: `list -i`

### Editing a person : `edit -p`

Edits an existing person in InterNUS.

Format: `edit -p INDEX [n/NAME] [p/PHONE] [e/EMAIL] [c/COMPANY] [t/TAG]…​`
- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
- You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:
- `list -p` followed by `edit -p 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
- `list -p` followed by `edit -p 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Editing an internship : `edit -i`

Edits an existing internship in InterNUS.

Format: `edit -i INDEX [c/COMPANY] [r/ROLE] [s/STATUS] [d/INTERVIEW_DATE]`
- Edits the internship at the specified `INDEX`. The index refers to the index number shown in the displayed internship list. The index must be a positive integer 1, 2, 3, …
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.

Examples:
- `list -i` followed by `edit -i 1 s/ACCEPTED` Edits the status of the 1st internship to be `ACCEPTED`.
- `list -i` followed by `edit -i 2 s/REJECTED` Edits the status of the 2nd internship to `REJECTED`.

### Linking a person and an internship : `link`

Links an existing person and internship together in InterNUS.

Format: `link p/PERSON_INDEX i/INTERNSHIP_INDEX`
- person at specified `PERSON_INDEX` and internship at specified `INTERNSHIP_INDEX` will be linked together such that 
- person will be displayed as contact person of internship and internship will be displayed as internship of person
- `PERSON_INDEX` and `INTERNSHIP_INDEX` **must be a positive integer** 1, 2, 3, …

Examples:
- `link p/1 i/1` will link person at index **1** to internship at index **1**

### Unlinking a person and an internship : `unlink`

Unlinks an existing person and internship together in InterNUS.

Format: `unlink [p/PERSON_INDEX] [i/INTERNSHIP_INDEX`]
- At least 1 of the optional fields must be provided
- Person and/or internship at the specified `PERSON_INDEX` and/or `INTERNSHIP_INDEX` will be unlinked
- `PERSON_INDEX` and `INTERNSHIP_INDEX` **must be a positive integer** 1, 2, 3, …

Examples:
- `unlink p/1 i/1` will unlink person at index **1** and internship at index **1**


### Locating persons by name : `find -p`

Finds persons whose fields contain any of the given keywords.

Format: `find -p [n/ NAME_KEYWORD [MORE_KEYWORDS]...] [p/ PHONE_KEYWORD [MORE_KEYWORDS]...] [e/ EMAIL_KEYWORD [MORE_KEYWORDS]...] [t/ TAG_KEYWORD [MORE_KEYWORDS]...] [c/ COMPANY_KEYWORD [MORE_KEYWORDS]...]`
- The search is case-insensitive. e.g **hans** will match **Hans**
- The order of the keywords does not matter. e.g. **Hans Bo** will match **Bo Hans**
- Only the fields corresponding to the specified prefixes will be searched,
  and all the specified fields must contain at least one of the specified keywords to be considered in the search result.
- Partial words will be matched. e.g. **Han** will match **Hans**

Examples:
- `find -p n/John` returns **john**, **John Doe** and **Johnny**


### Locating internships by name : `find -i`

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
- `find -i c/inc ltd` returns **Apple Inc**, **Alphabet Inc**, **Shopee Pte Ltd** and **Sea Ltd**
- `find -i c/g` returns **Google Inc** and **Garena**


### Deleting a person by index : `delete -p`

Deletes the specified person from InterNUS.

Format: `delete -p INDEX`

* Deletes the person with the specific `INDEX` in the person list.
* The index refers to the index number shown in the currently displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list -p` followed by `delete -p 2` deletes the 2nd person in InterNUS.
* `find -p Betsy` followed by `delete -p 1` deletes the 1st person in the results of the `find` command.

### Deleting an internship by index : `delete -i`

Deletes the specified internship from InterNUS.

Format: `delete -i INDEX`

* Deletes the internship with the specific `INDEX` in the internship list.
* The index refers to the index number shown in the currently displayed internship list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list -i` followed by `delete -i 2` deletes the 2nd internship in InterNUS.
* `find -i ABC Pte Ltd` followed by `delete -i 1` deletes the 1st internship in the results of the `find` command.

### Sorting persons in the list: `sort -p`

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

### Sorting internships in the list: `sort -i`

Sorts the internship list given a sorting criterion.

Format: `sort -i [c/] [d/] [s/]`
- Exactly one of the optional fields must be provided.
- `c/` sorts internships by company name (in lexicographic order).
- `d/` sorts internships by interview date.
- `s/` sorts internships by status in the given order: `BOOKMARKED`, `PENDING`, `ACCEPTED`, `COMPLETED`, `REJECTED`.
- When sorted by interview date, internships with no interview dates are listed at the bottom of the list.
- The list will remain sorted by the last given criterion until InterNUS is closed.

### Clearing all entries : `clear`

Clears all person and internship entries from InterNUS.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

InterNUS data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

InterNUS data is saved as a JSON file. `[JAR file location]`/data/addressbook.json.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InterNUS will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InterNUS home folder.
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InterNUS home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                | Format, Examples                                                                                                                                                                                                                       |
|-----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add person**        | `add -p n/NAME [e/EMAIL] [p/PHONE] [t/TAG]… [l/LINK_INDEX] [c/COMPANY]` <br> e.g., `add -p n/James Ho e/jamesho@example.com p/22224444 l/3 c/Meta`                                                                                     |
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
| **Sort persons**      | `sort -p [n/] [c/]` <br> e.g., `sort -p c/`                                                                                                                                                                                            |
| **Sort internships**  | `sort -i [c/] [d/] [s/]` <br> e.g., `sort -i d/`                                                                                                                                                                                       |
| **Help**              | `help`                                                                                                                                                                                                                                 |
