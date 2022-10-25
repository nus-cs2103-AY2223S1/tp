---
layout: page
title: User Guide
---

InternConnect is a **desktop app for managing internship applicants, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, InternConnect can get your application management tasks done faster than traditional GUI apps.

# Table of Contents
1. [Quick Start](#1-quick-start)
2. [Fields](#2-fields)
   1. [Identity Fields](#21-identity-fields)
   2. [Data Fields](#22-data-fields)
   3. [Tag Fields](#23-tag-fields)
3. [Features](#3-features)
   1. [Viewing help](#31-viewing-help-help)
   2. [Adding an applicant](#32-adding-an-applicant-add)
   3. [Listing all applicants](#33-listing-all-applicants-list)
   4. [Viewing the detail of an applicant](#34-viewing-the-detail-of-an-applicant-view)
   5. [Editing an applicant](#35-editing-an-applicant-edit)
   6. [Locating applicants by field](#36-locating-applicants-by-field-find)
   7. [Importing applicants from an external text file](#37-importing-applicants-from-an-external-text-file-import)
   8. [Deleting an applicant](#38-deleting-an-applicant-delete)
   9. [Clearing all entries](#39-clearing-all-entries-clear)
   10. [Exiting the program](#310-exiting-the-program-exit)
   11. [Saving the data](#311-saving-the-data)
   12. [Editing the data file](#312-editing-the-data-file)
4. [Command Summary](#4-command-summary)

--------------------------------------------------------------------------------------------------------------------

## 1. Quick start

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

* For `mandatory_field_parameter` arguments, please refer to the `parameter` of each fields in the [Fields](#2-fields) section above.

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
*  `edit 1 phone/91234567 email/bob@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `bob@example.com` respectively.
*  `edit 2 name/Betty` Edits the name of the 2nd person to be `Betty`.


### 3.6 Locating applicants by field: `find`

Finds applicants whose names contain any of the given keywords.

Format: `find specifier/KEYWORD [more_specifier/MORE_KEYWORDS]`

* The search is case-insensitive. E.g., `bobby` will match `Bobby`.
* The order of the keywords does not matter. E.g., `Bobby cortez` will match `Cortez bobby`.
* Only full words will be matched e.g., `Bobby` will not match `Bobbys`.
  
  <!--- Can consider a flag for subwords in future increments) -->

* Applicants matching at least one keyword will be returned (i.e., OR search). E.g., `Bobby Cortez` will return `Bobby Lacruz`, `Alexander Cortez`

Examples:
* `find name/Bobby ` Returns applicants with names matching `bobby` and `Bobby Cortez`
* `find gender/F cap/5 ` returns female applicants with a CAP of 5


### 3.7 Importing applicants from an external text file: `import`

Imports applicants from the specified file path.

Format: `import FILE_PATH`

* The file has to exist in the specified file path.
* More than 1 applicants can be listed in the file, as long as they have all the mandatory fields, satisfy the value constraints, and do not cause duplicates in the InternConnect.
* The file must be in json format.
* For example of valid input file, refer to this [template](templates/template.json) file.

Examples:
* `import nus_students.json` Imports NUS students listed in the `nus_students.json` file


### 3.8 Exporting the displayed list: `export`

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
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                                                        |
| **Import** | `import FILE_PATH`<br> e.g., `import nus_students.json`                                                                                                                                                                                                                           | 
| **Export** | `export`
| **View**   | `view INDEX`<br> e.g., `view 2`                                                                                                                                                                                                                                                   |
| **List**   | `list`                                                                                                                                                                                                                                                                            | 
| **Help**   | `help`                                                                                                                                                                                                                                                                            |
| **Exit**   | `exit`                                                                                                                                                                                                                                                                            |
