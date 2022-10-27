---
layout: page
title: User Guide
---

InternConnect is a **desktop app for managing internship applicants, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, InternConnect can get your application management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `internconnect.jar` from [here](https://github.com/AY2223S1-CS2103-F14-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your InternConnect.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all applicants.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/3.50/4.00 g/male u/Nanyang Polytechnic gd/05-2024 m/Computer Science ji/173296 jt/Software Engineer Intern t/rejected t/KIV` : Adds an applicant named `John Doe` to InternConnect.

   * **`view`**`1` : Views the 1st applicant shown in the current list   

   * **`delete`**`3` : Deletes the 3rd applicant shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#3-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 2. Fields

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the fields:**<br>

* Identity fields and Data fields are mandatory

* Tag fields are optional

</div>


### 2.1 Identity Fields
1. Email
   Parameter: 
   Constraints:
   Length Limit: 
   
1. ...


### 2.2 Data Fields
1. Name
   ...


### 2.3 Tag Fields
1. Tags
   ...

--------------------------------------------------------------------------------------------------------------------

## 2.4 Specifiers:
Each field of an applicant is specified by a specifier as follows:
* `n/NAME`
* `p/PHONE`
* `e/EMAIL`
* `a/ADDRESS`
* `c/CAP`
* `g/GENDER`
* `u/UNIVERSITY`
* `gd/GRADUATION_DATE`
* `m/MAJOR`
* `ji/JOB_ID`
* `jt/JOB_TITLE`
* `t/TAG`

--------------------------------------------------------------------------------------------------------------------

## 3. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* An address book cannot have multiple persons with the exact same identity fields (`EMAIL`, and `JOB_ID`)
  e.g., `n/John e/j@example.com ji/J12332` and `n/Jason e/j@example.com ji/J12332` is considered the same applicant.

* Words in `UPPER_CASE` are the parameter details to be supplied by the user.  
  e.g., in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Bobby Doe`.

* Items with `...` after them can be used multiple times including zero times.
  e.g. `[t/TAG]...` can be used as ` ` (i.e. 0 times), `t/KIV`, `t/KIV t/offered` etc.

* Items in curly brackets are mandatory.
  e.g., `{mandatory_field_parameter/FIELD_DETAIL}`, all fields must be listed.

* Items in square brackets are optional.
  e.g., `n/NAME p/PHONE e/EMAIL [t/TAG]` can be used as `n/Bobby p/91234567 e/bob@example.com t/KIV` or as `n/Bobby p/91234567 e/bob@example.com`

* Parameters can be in any order, and are separated by a space.
  e.g., if the command specifies `n/NAME p/PHONE e/EMAIL`, `n/NAME e/EMAIL p/PHONE` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken. 
  e.g. if you specify p/12341234 p/56785678, only p/56785678 will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, and `clear`) will be ignored.  
  e.g., if the command specifies `help 123`, it will be interpreted as `help`.

* All leading and trailing whitespaces will be trimmed, while multiple whitespaces in between words will be replaced by a single whitespace.

* All duplicate checks and find command keywords are case insensitive.
  e.g., `JACOB@example.com` will be considered equal to `jacob@EXAMPLE.COM`.

</div>


### 3.1 Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`


### 3.2 Adding an applicant: `add`

Adds an applicant to InternConnect.

Format: `add {mandatory_field_parameter/IDENTITY_FIELD} [t/TAG]...`

* For `mandatory_field_parameter` arguments, please refer to the `specifier` of each fields in the [Specifier](#2.4 Specifiers) section above.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/3.50/4.00 g/male u/Nanyang Polytechnic gd/05-2024 m/Computer Science ji/173296 jt/Software Engineer Intern t/rejected t/KIV`


### 3.3 Listing all applicants: `list`

Shows a list of all applicants in InternConnect.

Format: `list`


### 3.4 Viewing the detail of an applicant: `view`

Shows a detailed view of a selected applicant listed in InternConnect.

Format: `view INDEX`


### 3.5 Editing an applicant: `edit`

Edits an existing applicant in InternConnect.

Format: `edit [parameter/NEW_PARAMETER_DETAIL]...`

* Edits the applicant at the specified `INDEX`. 
* The index refers to the index number shown in the displayed applicant list. 
* The index **must be a positive integer** 1, 2, 3, ... and not exceed the total records listed.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When modifying tags, the existing tags of the applicant will be removed i.e adding of tags is not cumulative.
* You can remove all the applicant’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/bob@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `bob@example.com` respectively.
*  `edit 2 n/Betty` Edits the name of the 2nd person to be `Betty`.


### 3.6 Locating applicants by field: `find`

Finds applicants whose has a field containing any of the given keywords among all specifed fields.

Format: `find specifier/KEYWORD [more_specifier/MORE_KEYWORDS]`

* At least one of the field specifier must be provided.
* The search is case-insensitive. E.g., `bobby` will match `Bobby`.
* The order of the keywords does not matter. E.g., `Bobby cortez` will match `Cortez bobby`.
* If a field specifier is given more than once, the command will search only for the latest specifier. E.g., `n/Bobby n/cortez` will only search and return names with `cortez` in them.
* Applicants matching at least one keyword will be returned (i.e., OR search). E.g., `n/Bobby Cortez` will return applicants with the name `Bobby Lacruz`, `Alexander Cortez`

Search Types:
1. Matching word search: Keywords will only match if there is a full matching word. E.g., `Bobby` will not match `Bobbys`.
2. Substring Search: Keywords will match as long as a substring match exists. E.g., `Bob` will match `Bobby` and `Robobson`
3. Numeric Search: Search will search by its numeric value. E.g., Both `3` and `3.0` will match `3.0`

Each field has its own specific search type. The search types are given below:

Fields using _Matching word search_:
* `n/NAME` searches _names_ by _Matching word search_
* `p/PHONE` searches _phones_ by _Matching word search_
* `a/ADDRESS` searches _addresses_ by _Matching word search_
* `g/GENDER` searches _genders_ by _Matching word search_
* `u/UNIVERSITY` searches _universities_ by _Matching word search_
* `gd/GRADUATION_DATE` searches _graduation dates_ by _Matching word search_
* `m/MAJOR` searches _majors_ by _Matching word search_
* `ji/JOB_ID` searches _job Ids_ by _Matching word search_
* `jt/JOB_TITLE` searches _job titles_ by _Matching word search_
* `t/TAG` searches for all _tags_ by _Matching word search_

Fields using _Other searches_:
* `e/EMAIL` searches _emails_ by _Substring Search_
* `c/CAP` searches _cap's values_ by _Numeric Search_

Examples:
* `find n/Bobby ` Returns applicants with names matching `bobby` and `Bobby Cortez`
* `find g/Female c/3.5 2 ` returns female applicants with a CAP value of 3.5 or 2.0
* `find g/Male t/offered KIV` returns male applicants who have an offered or KIV tag attached to them
* `find g/Male e/gmail` returns male applicants who have a `gmail` substring in their email address


### 3.7 Importing applicants from an external text file: `import`

Imports applicants from the specified file path.

Format: `import FILE_PATH`

* The file has to exist in the specified file path.
* More than 1 applicants can be listed in the file, as long as they have all the mandatory fields, satisfy the value constraints, and do not cause duplicates in the InternConnect.
* The file must be in json format.
* For example of valid input file, refer to this [template](templates/template.json) file.

Examples:
* `import nus_students.json` Imports NUS students listed in the `nus_students.json` file


### 3.8 Exporting displayed list: `export`

Exports the displayed list from InternConnect.

Format: `export`

* Exported JSON file will be stored in `data/export/` folder

Examples:
* `find university/NUS` followed by `export` exports the result of the `find` command to a JSON file.


### 3.9 Deleting an applicant: `delete`

Deletes the specified applicant from InternConnect.

Format: `delete INDEX`

* Deletes the applicant at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, ... and **not exceed the total records listed**

Examples:
* `list` followed by `delete 2` deletes the 2nd applicant in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st applicant in the results of the `find` command.


### 3.10 Clearing all entries: `clear`

Clears all entries from the address book.

Format: `clear`


### 3.11 Exiting the program: `exit`

Exits the program.

Format: `exit`

### 3.12 Saving the data

InternConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 3.13 Editing the data file

InternConnect data are saved as a JSON file `[JAR file location]/data/internconnect.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InternConnect will discard all data and start with an empty data file at the next run.
</div>

-----------------------------------------------------------------------------------------------------

## 4. Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                                  |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add {mandatory_field_parameter/FIELD_DETAIL} [t/TAGS]...` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/3.50/4.00 g/male u/Nanyang Polytechnic gd/05-2024 m/Computer Science ji/173296 jt/Software Engineer Intern t/rejected t/KIV` |
| **Clear**  | `clear`                                                                                                                                                                                                                                                                           |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                               |
| **Edit**   | `edit INDEX [parameter/NEW_PARAMETER_DETAIL]...`<br> e.g.,`edit 1 p/91234567 e/bob@example.com`                                                                                                                                                                                   |
| **Find**   | `find specifier/KEYWORD [more_specifier/MORE_KEYWORDS]`<br> e.g., `find g/Male t/offered KIV`                                                                                                                                                                                     |
| **Import** | `import FILE_PATH`<br> e.g., `import nus_students.json`                                                                                                                                                                                                                           | 
| **Export** | `export`                                                                                                                                                                                                                                                                          |
| **View**   | `view INDEX`<br> e.g., `view 2`                                                                                                                                                                                                                                                   |
| **List**   | `list`                                                                                                                                                                                                                                                                            | 
| **Help**   | `help`                                                                                                                                                                                                                                                                            |
| **Exit**   | `exit`                                                                                                                                                                                                                                                                            |
