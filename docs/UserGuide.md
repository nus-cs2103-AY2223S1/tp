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

   * **`add`**`name/Bobby phone/91234567 email/bob@example.com job/420` : Adds an applicant named `Bobby` to InternConnect.

   * **`delete`**`3` : Deletes the 3rd applicant shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameter details to be supplied by the user.  
  e.g., in `add name/NAME`, `NAME` is a parameter which can be used as `add name/Bobby Doe`.

* Parameter `specifier/` can be any combination of the parameters listed under each command’s section, e.g. for add command, `specifier/SPECIFIER_DETAIL` can be used as `job/420 gender/M country/Singapore`, or as `country/Singapore`

* Items in square brackets are optional.
  e.g., `name/NAME phone/PHONE email/EMAIL [specifier/SPECIFIER_DETAIL]` can be used as `name/Bobby phone/91234567 email/bob@example.com job/420` or as `name/Bobby phone/91234567 email/bob@example.com`.

* Parameters can be in any order.
  e.g., if the command specifies `name/NAME phone/PHONE email/EMAIL`, `name/NAME email/EMAIL phone/PHONE` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, and `clear`) will be ignored.  
  e.g., if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`


### Adding an applicant: `add`

Adds an applicant to InternConnect.

Format: `add name/NAME phone/PHONE email/EMAIL [specifier/SPECIFIER_DETAIL]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of specifiers (including 0)
</div>

Specifiers:
* `job/JOB_ID`
* `nationality/NATIONALITY`
* `address/ADDRESS`
* `postal/POSTAL_CODE`
* `country/COUNTRY_OF_RESIDENCE`
* `cap/CAP`
* `gender/GENDER`
* `faculty/FACULTY`
* `major/MAJOR`
* `dob/DATE_OF_BIRTH`
* `admission/ADMISSION_DATE`
* `graduation/GRADUATION_DATE`
* `available/START_DATE_END_DATE`

Examples:
* `add name/Bobby phone/91234567 email/bob@example.com job/420`


### Listing all persons: `list`

Shows a list of all persons in the address book.

Format: `list`

### View the detail of an applicant: `view`

Shows a detailed view of a selected applicant listed in InternConnect.

Format: `view INDEX`


### Editing a person: `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.


### Locating person's fields by field: `find`

Finds persons whose names contain any of the given keywords.

Format: `find specifier/KEYWORD [more_specifier/MORE_KEYWORDS]`

* The search is case-insensitive. E.g., `bobby` will match `Bobby`.
* The order of the keywords does not matter. E.g., `Bobby cortez` will match `Cortez bobby`.
* Only full words will be matched e.g., `Bobby` will not match `Bobbys`.
  
  <!--- Can consider a flag for subwords in future increments) -->

* Applicants matching at least one keyword will be returned (i.e., OR search). E.g., `Bobby Cortez` will return `Bobby Lacruz`, `Alexander Cortez`

Examples:
* `find name/Bobby ` Returns applicants with names matching `bobby` and `Bobby Cortez`
* `find gender/F cap/5 ` returns female applicants with a CAP of 5

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

| Action     | Format, Examples                                                                                                                              |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add name/NAME phone/PHONE email/EMAIL [specifier/SPECIFIER_DETAIL]` <br> e.g., `add name/Bobby phone/91234567 email/bob@example.com job/420` |
| **Clear**  | `clear`                                                                                                                                       |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                           |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                   |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                    |
| **View**   | `view INDEX`<br> e.g., `view 2`                                                                                                                |
| **List**   | `list`                                                                                                                                        | 
| **Help**   | `help`                                                                                                                                        |
| **Exit**   | `exit`                                                                                                                                        |
