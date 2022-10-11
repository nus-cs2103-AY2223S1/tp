---
layout: page
title: User Guide
---

Petcode is a **desktop app for coordinating pet sale business, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PetCode can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `petcode.jar` from [here]().

1. Copy the file to the folder you want to use as the _home folder_ for your PetCode.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list buyer/supplier/delivery`** : Lists buyer/supplier/delivery.

   * **`add`**`add buyer n/Hongyi b/ragdoll p/11223344 e/email@u.nus.edu a/UTR 138600 i/colou:blue t/Singapore` : Adds a buyer named `Hong Yi` who is looking for a ragdoll to the PetCode.

   * **`delete c/Buyer i/2`** : Deletes the second contact from the Buyer contacts list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a buyer, supplier, or delivery service to the contacts.

Format: `add r/ROLE n/NAME b/BREED p/PHONE_NUMBER e/EMAIL a/ADDRESS i/ADDITIONAL_INFORMATION [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add buyer n/Hongyi b/ragdoll p/11223344 e/email@u.nus.edu a/UTR 138600 i/colou:blue t/Singapore`
* `add supplier n/Carol Pet House b/persian cat, pomeranian, ragdoll p/11223344 e/carolpethouse@gmail.com a/Marina Bay Sands 138600 i/discount for more than one purchase`


### Listing all contacts : `list`

Shows a list of all contacts, based on their role as suppliers, buyers, or delivery services.

Format: `list buyers`, `list suppliers`, `list delivery`

### Editing a person : `edit`

Edits an existing person in the PetCode.

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

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deleting a person from contacts.

Format: `delete c/PERSON_CATEGORY i/INDEX (must be a positive integer)`

Examples:
* `delete c/Buyer i/1` will delete the contact at index 1 of the Buyer contacts list, if index is found.
* `delete c/Deliverer i/2` will delete the contact at index 2 of the Deliverer contacts list, if index is found.
* `delete c/Supplier i/3` will delete the contact at index 3 of the Supplier contacts list, if index is found.

### Filter contacts : `filter`

Filter display only buyers or suppliers based on the given tag(s).

Format: `filter t/INPUT`

Examples:
* `filter t/cat`
* `filter t/dog t/second-hand`

### Sort contacts : `sort`

Sort the contacts based on given tag(s) and order(s).

Format: `Sort t/TAG Asc/Desc, [t/TAG] [Asc/Desc]…​`

* The order can be either ascending `Asc` or descending `Desc`.
* The provided category can be one or more than one.

Examples:
* `Sort t/country Asc`
* `Sort t/priority Desc, t/country Desc`

### Clearing all entries : `clear`

Clears all entries from the PetCode.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

PetCode data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

PetCode data are saved as a JSON file `[JAR file location]/data/PetCode.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, PetCode will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous PetCode home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                             |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add r/ROLE n/NAME b/BREED p/PHONE_NUMBER e/EMAIL a/ADDRESS i/ADDITIONAL_INFORMATION [t/TAG]…​` <br> e.g., `add buyer n/Hongyi b/ragdoll p/11223344 e/email@u.nus.edu a/UTR 138600 i/colou:blue t/Singapore` |
| **Delete** | `delete c/PERSON_CATEGORY i/INDEX` e.g., `delete c/Buyer i/1`                                                                                                                                                |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                         |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                   |
| **Filter** | `filter t/INPUT`<br> e.g., `filter t/dog t/second-hand`                                                                                                                                                      |
| **Sort**   | `Sort t/TAG Asc/Desc, [t/TAG] [Asc/Desc]…​`<br> e.g., `Sort t/priority Desc, t/country Desc`                                                                                                                 |
| **List**   | `list buyers`, `list suppliers`, `list delivery`                                                                                                                                                             |
| **Clear**  | `clear`                                                                                                                                                                                                      |
| **Help**   | `help`                                                                                                                                                                                                       |
