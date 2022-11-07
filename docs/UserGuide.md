---
layout: page
title: User Guide
---

InternConnect is a **desktop app for managing internship applicants, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

This one-stop, convenient, and efficient platform empowers Internship Campus Recruiters to work with their applicants’ data. Say goodbye to opening multiple windows to retrieve the information you need and focus on what matters more: matching the right people for the right job.

## Table of Contents

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
   6. [Deleting an applicant](#36-deleting-an-applicant-delete)
   7. [Locating applicants by field](#37-locating-applicants-by-field-find)
   8. [Importing applicants from an external JSON file](#38-importing-applicants-from-an-external-json-file-import)
   9. [Exporting displayed list](#39-exporting-displayed-list-export)
   10. [Checkout a new or existing list](#310-checkout-a-new-or-existing-list-checkout)
   11. [Clearing all entries](#311-clearing-all-entries-clear)
   12. [Exiting the program](#312-exiting-the-program-exit)
   13. [Saving the data](#313-saving-the-data)
   14. [Editing the data file](#314-editing-the-data-file)
4. [Command Summary](#4-command-summary)


<div style="page-break-after: always;"></div>

## 1. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `internconnect_v1.4.jar` from [here](https://github.com/AY2223S1-CS2103-F14-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your InternConnect.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.
   Some example commands you can try:

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/3.50/4.00 g/male u/Nanyang Polytechnic gd/05-2024 m/Computer Science ji/173296 jt/Software Engineer Intern t/rejected t/KIV` : Adds an applicant named `John Doe` to InternConnect.

   * **`view`**`1` : Views the 1st applicant shown in the current list.

   * **`delete`**`3` : Deletes the 3rd applicant shown in the current list.

6. Refer to [Features](#3-features) for details of each command.

<div style="page-break-after: always;"></div>

## 2. Fields

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the field format:**<br>

* Words in `UPPER_CASE` are the parameter details to be supplied by the user.
  e.g., in `CURRENT_CAP`, `CURRENT_CAP` is a parameter which can be used as `3.5`.
* None of the fields inserted into the command should be blank, except when removing `Tag` through the `edit` command

* Identity fields and Data fields are mandatory for `add` command

* Tag fields are optional

* Length of fields are in characters, after removing leading and trailing whitespaces, and replacing multiple whitespaces in between words to 1 whitespace

</div>


### 2.1 Identity Fields

| Field       | Parameter | Length Limit | Constraints                                                    |
|-------------|-----------|--------------|----------------------------------------------------------------|
| **Email**   | `e`       | 50           | Can only be of the format `LOCAL_PART@DOMAIN`                  |
| **Job ID**  | `ji`      | 10           | Can only contain upper case alphanumeric characters and spaces |

* `LOCAL_PART` can only contain alphanumeric characters and any of `+` `_` `.` `-`, but must not start or end with these special characters
* `DOMAIN` refers to the email domain that can be made of numerous `DOMAIN_LABEL`
    * Each `DOMAIN_LABEL` can be separated by `.`
    * `DOMAIN_LABEL` can only contain alphanumeric characters and hyphens `-`
    * `DOMAIN_LABEL` must start and end with alphanumeric characters
    * The last `DOMAIN_LABEL` should be at least 2 characters long
* For example, in `john_doe@workday-nus.edu.sg`, `john_doe` is the `LOCAL_PART` and `workday-nus.edu.sg` is the `DOMAIN`
which contains `workday-nus`, `edu`, and `sg` as `DOMAIN_LABEL` separated by `.`

<div style="page-break-after: always;"></div>

### 2.2 Data Fields

| Field               | Parameter | Length Limit | Constraints                                                                            |
|---------------------|-----------|--------------|----------------------------------------------------------------------------------------|
| **Name**            | `n`       | 50           | Can only contain alphanumeric characters and spaces                                    |
| **Phone**           | `p`       | 20           | Can only contain numbers, at least 3 digits long                                       |
| **Address**         | `a`       | 100          | Can take any values, but not blank                                                     |
| **CAP**             | `c`       | None         | Can only consist of 2 positive numeric values in the form of `CURRENT_CAP/MAX_CAP`     |
| **Gender**          | `g`       | None         | Can only be `male` or `female` , will be converted to lower-case                       |
| **University**      | `u`       | 100          | Can only contain alphanumeric characters and spaces                                    |
| **Graduation Date** | `gd`      | None         | Can only be a valid month in the form of `MM-yyyy`, starting from the year 0000        |                         
| **Major**           | `m`       | 50           | Can only contain alphanumeric characters and spaces                                    |
| **Job Title**       | `jt`      | 100          | Can only contain alphanumeric characters, `SPECIAL_PUNCTUATIONS` and spaces            |

* `CURRENT_CAP` value must be smaller than or equal to `MAX_CAP`
* `MAX_CAP` and `CURRENT_CAP` must be a positive value (more than `0.0`)
* `MAX_CAP` and `CURRENT_CAP` values should not exceed `100.0`
* `MAX_CAP` and `CURRENT_CAP` will be rounded to 2 decimal places, e.g. `3.99999` will be rounded to `4.00`
* `SPECIAL_PUNCTUATIONS` include `-` `#` `,` `:` `&` `(` `)` `"` `'` `/` `[` `]`


### 2.3 Tag Fields

| Field   | Parameter | Length Limit | Constraints                                     |
|---------|-----------|--------------|-------------------------------------------------|
| **Tag** | `t`       | 30           | Can only contain alphabet characters and spaces |

<div style="page-break-after: always;"></div>

## 3. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* An address book cannot have multiple applicants with the exact same identity fields (`EMAIL` and `JOB_ID`)
  e.g., `n/John e/j@example.com ji/J12332` and `n/Jason e/j@example.com ji/J12332` are considered the same applicant.

* Words in `UPPER_CASE` are the parameter details to be supplied by the user.
  e.g., in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Bobby Doe`.

* Items with `...` after them can be used multiple times including zero times.
  e.g. `[t/TAG]...` can be used as nothing (i.e. 0 times), `t/KIV`, `t/KIV t/offered` etc.

* Curly brackets represent a set of at least 1 parameter, and each of them is mandatory.
  e.g., `{mandatory_field_parameter/FIELD_DETAIL}`, all fields that belong to mandatory fields must be listed.

* Items in square brackets are optional.
  e.g., `n/NAME [t/TAG]` can be used as `n/Bobby t/KIV` or as `n/Bobby`

* Parameters can be in any order, and are separated by a space.
  e.g., if the command specifies `n/NAME p/PHONE e/EMAIL`, `n/NAME e/EMAIL p/PHONE` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `export` and `clear`) will be ignored.
  e.g., if the command specifies `help 123`, it will be interpreted as `help`.

* All leading and trailing whitespaces will be trimmed, while multiple whitespaces in between words will be replaced by a single whitespace.

* All find command keywords and duplicate checks for applicants are case-insensitive.
  e.g., `JACOB@example.com` will be considered equal to `jacob@EXAMPLE.COM`.

</div>

<div style="page-break-after: always;"></div>

### 3.1 Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`


### 3.2 Adding an applicant: `add`

Adds an applicant to InternConnect.

Format: `add {mandatory_field_parameter/MANDATORY_FIELD_DETAIL} [t/TAG]...`

* For `mandatory_field_parameter` arguments, please refer to the `parameter` of each fields in the [Identity Fields](#21-identity-fields) and [Data Fields](#22-data-fields) section above.
* There are 11 `mandatory_field_parameter` in total, and you need to specify all of them.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/3.50/4.00 g/male u/Nanyang Polytechnic gd/05-2024 m/Computer Science ji/173296 jt/Software Engineer Intern t/rejected t/KIV`


### 3.3 Listing all applicants: `list`

Shows a list of all applicants in InternConnect.

Format: `list`


### 3.4 Viewing the detail of an applicant: `view`

Shows a detailed view of a selected applicant listed in InternConnect.

Format: `view INDEX`

* Views the applicant at the specified `INDEX`.
* The index refers to the index number shown in the displayed applicants list.
* The index **must be a positive integer** 1, 2, 3, ... and not exceed the total records listed.

<div style="page-break-after: always;"></div>

### 3.5 Editing an applicant: `edit`

Edits an existing applicant in InternConnect.

Format: `edit INDEX parameter/NEW_PARAMETER_DETAIL [parameter/NEW_PARAMETER_DETAIL]...`

* For `parameter` arguments, please refer to the `parameter` of each field in the [Fields](#2-fields) section above.
* Edits the applicant at the specified `INDEX`.
* The index refers to the index number shown in the displayed applicants list.
* The index **must be a positive integer** 1, 2, 3, ... and not exceed the total records listed.
* At least one of the parameter fields must be provided.
* Existing values will be updated to the input values.
* When modifying tags, the existing tags of the applicant will be removed i.e. adding of tags is not cumulative.
* You can remove all the applicant’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/bob@example.com` Edits the phone number and email address of the 1st applicant to be `91234567` and `bob@example.com` respectively.
*  `edit 2 n/Betty` Edits the name of the 2nd applicant to be `Betty`.


### 3.6 Deleting an applicant: `delete`

Deletes the specified applicant from InternConnect.

Format: `delete INDEX`

* Deletes the applicant at the specified `INDEX`.
* The index refers to the index number shown in the displayed applicants list.
* The index **must be a positive integer** 1, 2, 3, ... and not exceed the total records listed.

Examples:
* `list` followed by `delete 2` deletes the 2nd applicant in the address book.
* `find n/Betsy` followed by `delete 1` deletes the 1st applicant in the results of the `find` command.

<div style="page-break-after: always;"></div>

### 3.7 Locating applicants by field: `find`

Finds applicants who have a field containing any of the given keywords among all specified fields.

Format: `find parameter/KEYWORD [parameter/KEYWORD]...`

* For `parameter` arguments, please refer to the `parameter` of each fields in the [Fields](#2-fields) section above.
* At least one of the parameter fields must be provided.
* If a field parameter is given more than once, the command will search only for the latest specifier. E.g., `n/Bobby n/cortez` will only search and return names with `cortez` in them.
* The search is case-insensitive. E.g., `bobby` will match `Bobby`.
* Applicants matching at least one keyword will be returned (i.e., _OR_ search). E.g., `n/Bobby Cortez` will return applicants with the name `Bobby Lacruz`, `Alexander Cortez`
* The order of the keywords does not matter. E.g., `Bobby cortez` will match `Cortez bobby`.
* Multiple field search: Across different fields, an _AND_ search is done, while an _OR_ search is still done within each field for its keywords. E.g., `n/Bobby Cortez g/male` will return male applicants with names matching `Bobby` or `Cortez`.

Search Types:
1. Matching word: Keywords will only match if there is a full matching word. E.g., `Bobby` will not match `Bobbys`.
2. Substring: Keywords will match as long as a substring match exists. E.g., `@gmail.com` will match `jason@gmail.com` and `CS2103@gmail.com`
3. Numeric: Keywords will match by its numeric value, rounded to 2 decimal places. E.g., Both `3` and `3.00000001` will match `3.00`

| Field               | Search Type               |
|---------------------|---------------------------|
| **Name**            | Matching word             |
| **Email**           | Substring                 |
| **Phone**           | Substring                 |
| **Address**         | Matching word             |
| **CAP**             | Numeric of `CURRENT_CAP`  |
| **Gender**          | Matching word             |
| **University**      | Matching word             |
| **Graduation Date** | Matching word             |                         
| **Major**           | Matching word             |
| **Job ID**          | Substring                 |
| **Job Title**       | Matching word             |
| **Tag**             | Matching word             |

Examples:
* `find n/Bobby` Returns applicants with names matching `bobby` and `Bobby Cortez`
* `find g/Female c/3.5 2` returns female applicants with a CAP value of `3.5` or `2.0`
* `find g/Male t/offered KIV` returns male applicants who have an `offered` or `KIV` tag attached to them
* `find g/Male e/gmail` returns male applicants who have a `gmail` substring in their email address


### 3.8 Importing applicants from an external JSON file: `import`

Imports applicants from an external JSON file.

Format: `import FILE_PATH`

* The file has to exist in the specified file path.
* More than 1 applicants can be listed in the file, as long as they have all the mandatory fields, satisfy the value constraints, and do not cause duplicates in the InternConnect.
* The file must be in JSON format.
* For example of valid input file, refer to this [template](templates/template.json) file.

Examples:
* `import nus_students.json` Imports applicants listed in the `nus_students.json` file


<div style="page-break-after: always;"></div>

### 3.9 Exporting displayed list: `export`

Exports the displayed list from InternConnect.

Format: `export`

* Exported JSON file will be stored in `data/export/` folder
* Current date time will be used as the JSON file name

Examples:
* `find u/NUS` followed by `export` exports the displayed list from the `find` command to a JSON file.


### 3.10 Checkout a new or existing list: `checkout`

Switches between different lists in InternConnect stored in `data/` folder.

Format: `checkout FILE_NAME`

* `FILE_NAME` should not include any extension, as it would always be a JSON file.
* The command will attempt to load the specified `FILE_NAME.json` file in the `data/` folder.
* If the file contains invalid data or have an invalid format, the contents of the file will be erased and an empty
addressbook will be loaded.
* If the file doesn't exist, a new file will be created, populated with sample data.
* If the file contains valid data, the file will be loaded.

Examples:
* `checkout 27-oct-2022` attempt to load data from `data/27-oct-2022.json`.


### 3.11 Clearing all entries: `clear`

Clears all entries from the address book.

Format: `clear`


<div style="page-break-after: always;"></div>


### 3.12 Exiting the program: `exit`

Exits the program.

Format: `exit`


### 3.13 Saving the data

InternConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### 3.14 Editing the data file

* The first InternConnect data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Subsequent `checkout` commands may change the active filename.
* Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InternConnect will discard all data and start with an empty data file at the next run.
</div>


<div style="page-break-after: always;"></div>


## 4. Command summary

| Action       | Format                                                                          | Examples                                                                                                                                                                                                    |
|--------------|---------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**     | `help`                                                                          | `help`                                                                                                                                                                                                      |
| **Add**      | `add {mandatory_field_parameter/MANDATORY_FIELD_DETAIL} [t/TAG]...`             | `add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/3.50/4.00 g/male u/Nanyang Polytechnic gd/05-2024 m/Computer Science ji/173296 jt/Software Engineer Intern t/rejected t/KIV` |
| **List**     | `list`                                                                          | `list`                                                                                                                                                                                                      |
| **View**     | `view INDEX`                                                                    | `view 2`                                                                                                                                                                                                    |
| **Edit**     | `edit INDEX parameter/NEW_PARAMETER_DETAIL [parameter/NEW_PARAMETER_DETAIL]...` | `edit 1 p/91234567 e/bob@example.com`                                                                                                                                                                       |
| **Delete**   | `delete INDEX`                                                                  | `delete 3`                                                                                                                                                                                                  |
| **Find**     | `find parameter/KEYWORD [parameter/KEYWORD]...`                                 | `find g/Male t/offered KIV`                                                                                                                                                                                 |
| **Import**   | `import FILE_PATH`                                                              | `import nus_students.json`                                                                                                                                                                                  |
| **Export**   | `export`                                                                        | `export`                                                                                                                                                                                                    |
| **Checkout** | `checkout FILE_NAME`                                                            | `checkout 27-oct-2022`                                                                                                                                                                                      |
| **Clear**    | `clear`                                                                         | `clear`                                                                                                                                                                                                     |
| **Exit**     | `exit`                                                                          | `exit`                                                                                                                                                                                                      |
