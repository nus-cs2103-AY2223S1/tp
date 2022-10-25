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

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/3.50/4.00 g/male u/Nanyang Polytechnic gd/05-2024 m/Computer Science ji/173296 jt/Software Engineer Intern t/rejected t/KIV` : Adds an applicant named `Alex Yeoh` to InternConnect.

   * **`delete`**`3` : Deletes the 3rd applicant shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Fields

1. Name
   
   Constraints: 
   
   Length Limit: 
   
1. ...

--------------------------------------------------------------------------------------------------------------------

## Specifiers:
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

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* An address book cannot have multiple persons with the same `NAME`, `EMAIL`, and `JOB_ID` at once.

* Words in `UPPER_CASE` are the parameter details to be supplied by the user.  
  e.g., in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Bobby Doe`.

* Items in curly brackets are mandatory.
  e.g., `n/NAME p/PHONE e/EMAIL {specifier/SPECIFIER_DETAIL}`, all specifiers need to be listed

* Items in square brackets are optional.
  e.g., `n/NAME p/PHONE e/EMAIL [t/TAGS]` can be used as `n/Bobby p/91234567 e/bob@example.com t/KIV` or as `n/Bobby p/91234567 e/bob@example.com`

* Parameters can be in any order.
  e.g., if the command specifies `n/NAME p/PHONE email/EMAIL`, `n/NAME e/EMAIL p/PHONE` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, and `clear`) will be ignored.  
  e.g., if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`


### Adding an applicant: `add`

Adds an applicant to InternConnect.

Format: `add n/NAME e/EMAIL ji/JOB_ID {specifier/SPECIFIER_DETAIL}`

Specifiers:
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

Examples:
* `n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/3.50/4.00 g/male u/Nanyang Polytechnic gd/05-2024 m/Computer Science ji/173296 jt/Software Engineer Intern t/rejected t/KIV`


### Listing all applicants : `list`

Shows a list of all applicants in the address book.

Format: `list`

### View the detail of an applicant: `view`

Shows a detailed view of a selected applicant listed in InternConnect.

Format: `view INDEX`


### Editing an applicant : `edit`

Edits an existing applicant in InternConnect.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [specifier/SPECIFIER_DETAIL]…​`

* Edits the applicant at the specified `INDEX`. 
* The index refers to the index number shown in the displayed applicant list. 
* The index **must be a positive integer** 1, 2, 3, …​ and not exceed the total records listed.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When modifying tags, the existing tags of the applicant will be removed i.e adding of tags is not cumulative.
* You can remove all the applicant’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/bob@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `bob@example.com` respectively.
*  `edit 2 n/Betty` Edits the name of the 2nd person to be `Betty`.


### Locating person's fields by field: `find`

Finds persons whose has a field containing any of the given keywords among all specifed fields.

Format: `find specifier/KEYWORD [more_specifier/MORE_KEYWORDS]`

* The search is case-insensitive. E.g., `bobby` will match `Bobby`.
* The order of the keywords does not matter. E.g., `Bobby cortez` will match `Cortez bobby`.
* Only full words will be matched e.g., `Bobby` will not match `Bobbys`.
  
  <!--- Can consider a flag for subwords in future increments) -->

* Applicants matching at least one keyword will be returned (i.e., OR search). E.g., `Bobby Cortez` will return `Bobby Lacruz`, `Alexander Cortez`

Examples:
* `find n/Bobby ` Returns applicants with names matching `bobby` and `Bobby Cortez`
* `find g/Female c/3.5 5 ` returns female applicants with a CAP of 3.5 or 5
* `find g/Male t/offered KIV` returns male applicants who have an offered or KIV tag attached to them


<br>


### Deleting an applicant: `delete`

Deletes the specified applicant from InternConnect.

Format: `delete INDEX`

* Deletes the applicant at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​ and **not exceed the total records listed**

Examples:
* `list` followed by `delete 2` deletes the 2nd applicant in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st applicant in the results of the `find` command.


### Clearing all entries: `clear`

Clears all entries from the address book.

Format: `clear`


### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

InternConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

InternConnect data are saved as a JSON file `[JAR file location]/data/internconnect.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InternConnect will discard all data and start with an empty data file at the next run.
</div>

-----------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                        |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/3.50/4.00 g/male u/Nanyang Polytechnic gd/05-2024 m/Computer Science ji/173296 jt/Software Engineer Intern t/rejected t/KIV` |
| **Clear**  | `clear`                                                                                                                                                                                                 |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                     |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [specifier/SPECIFIER_DETAIL]…​`<br> e.g.,`edit 1 p/91234567 e/bob@example.com`                                                                                 |
| **Find**   | `find specifier/KEYWORD [more_specifier/MORE_KEYWORDS]`<br> e.g., `find g/Male t/offered KIV`                                                                                                           |
| **View**   | `view INDEX`<br> e.g., `view 2`                                                                                                                                                                         |
| **List**   | `list`                                                                                                                                                                                                  | 
| **Help**   | `help`                                                                                                                                                                                                  |
| **Exit**   | `exit`                                                                                                                                                                                                  |
