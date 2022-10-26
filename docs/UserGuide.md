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

   * **`delete-b 1`** : Deletes the first contact from the buyer contacts list.

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
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

Shows a list of all contacts, based on their role as suppliers, buyers, deliverer, order or pet.

Format: `list [LIST_PARAMETER]`

#### List Parameter Table

| List type | Acceptable Parameters            |
|-----------|----------------------------------|
| Buyer     | buyers, buyer, b, -b, /b         |
| Supplier  | suppliers, supplier, s, -s, /s   |
| Deliverer | deliverers, deliverer, d, -d, /d |
| Order     | orders, order, o, -o, /o         |
| Pet       | pets, pet, p, -p, /p             |

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

### Filter orders : `filter-o`

Filter display only Orders based on the given attribute(s). There are three possible attributes to filter: Additional requests, Order status, Price range.

| Attribute           | Prefix | Format                     | Example         |
|---------------------|--------|----------------------------|-----------------|
| Additional requests | ar | ar/KEYWORD                 | ar/non-shedding |
| Order Status | os | os/KEYWORD                 | os/Negotating   |
| Price Range | pr | pr/LOWER_PRICE-UPPER_PRICE | pr/100-456      |


Format: `filter-o [PREFIX]/INPUT`

Examples:
* `filter-o os/Pending`
* `filter-o as/Negotiating p/90-900`
* `filter-o ar/good with children os/Delivering p/80-100`

### Filter Pets : `filter-p`

Filter display only Pets based on the given attributes. There are five possible attributes to filter: Color, Name, Price, Species, Vaccination status.

| Attribute | Prefix | Format             | Example         |
|-----------|--------|--------------------|-----------------|
| Color | c | c/KEYWORD          | c/pink          |
| Name | n | n/KEYWORD          | n/nyanko-sensei |
| Price | p | p/PRICE            | p/209           |
| Species | s | s/KEYWORD          | s/ostrich       |
| Vaccination Status | v | v/KEYWORD |  v/false        |

Format: `filter-p [PREFIX]/INPUT`

Examples:
* `filter-p c/white`
* `filter-p c/black v/true`
* `filter-p c/black n/doraemon p/50 s/cat v/true`

### Find Persons : `find`

Find displays all persons who match ONE on specific attribute. There are five possible attributes to filter: Address, Email, Location, Name, Phone.

| Attribute | Prefix | Format    | Example                |
|-----------|--------|-----------|------------------------|
| Address | a      | a/KEYWORD | a/Wall Street          |
| Email | e      | e/KEYWORD | e/whereisamy@gmail.com |
|Location | l      | l/KEYWORD | l/Nova Scotia          |
| Name | n      | n/KEYWORD | n/Amy Toh              |
| Phone | p      | p/NUMBER  | p/999                  |

Format: `find [PREFIX]/INPUT`

Examples:
* `find a/6th College Ave. West`
* `find e/blackball@furry.com`
* `find p/98986668`

### Find Buyers : `find-b`

Find displays all buyers who match ONE on specific attribute. There are five possible attributes to filter: Address, Email, Location, Name, Phone.

To find corresponding prefixes to each attribute (ie. Address), please refer to the table in the "Find" Command user guide above.

Format: `find-b [PREFIX]/INPUT`

Examples:
* `find-b a/6th College Ave. West`
* `find-b e/blackball@furry.com`
* `find-b p/98986668`

### Find Deliverers : `find-d`

Find displays all deliverers who match ONE on specific attribute. There are five possible attributes to filter: Address, Email, Location, Name, Phone.

To find corresponding prefixes to each attribute (ie. Address), please refer to the table in the "Find" Command user guide above.

Format: `find-d [PREFIX]/INPUT`

Examples:
* `find-d a/6th College Ave. West`
* `find-d e/blackball@furry.com`
* `find-d p/98986668`

### Find Suppliers : `find-s`

Find displays all suppliers who match ONE on specific attribute. There are five possible attributes to filter: Address, Email, Location, Name, Phone.

To find corresponding prefixes to each attribute (ie. Address), please refer to the table in the "Find" Command user guide above.

Format: `find-s [PREFIX]/INPUT`

Examples:
* `find-s a/6th College Ave. West`
* `find-s e/blackball@furry.com`
* `find-s p/98986668`

### Sort contacts : `sort`

Sort the contacts based on given list type and attributes in ascending order.

Format: `sort LIST_PARAMETER, [ATTRIBUTES...]`

**Note Different list could have different supported sort attributes.**

[Check all list parameters](#list-parameter-table)

#### List Attribute Table

| List type | Attributes                                                                                                                       |
|-----------|----------------------------------------------------------------------------------------------------------------------------------|
| Buyer     | <u>Number of Order</u>, Name, Phone, Email, Location, Address                                                                    |
| Supplier  | <u>Number of Pet On sale</u>, Name, Phone, Email, Location, Address                                                              |
| Deliverer | <u>Number of Order</u>, Name, Phone, Email, Location, Address                                                                    |
| Order     | <u>Due Date</u>, Price Range, Price, Status                                                                                      |
| Pet       | <u>Price</u>, Name, Color, Color Pattern, Birth Date, Species, Height, Weight, Vaccination Status, Characteristics, Certificates |

> The underlined attribute represents the default sorting attribute for each list. <br>
> For Example: `sort pet` will sort the pet list in default by the price attribute. 

#### Attribute Parameter Table

| Attribute             | Acceptable Parameters                        |
|-----------------------|----------------------------------------------|
| Name                  | name, na, n, -n, /n                          |
| Phone                 | phone, ph, p, -p, /p                         |
| Email                 | email, ema, em, e, -e, /e                    |
| Location              | location, loc, l, -l, /l                     |
| Address               | address, addr, a, -a, /a                     |
| Number of Order       | orders, order                                |
| Number of Pet On Sale | pets, pet                                    |
| Due Date              | duedate, due, by, date, bydate, d, -d, /d    |
| Price Range           | pricerange, prange, pricer, pr, -pr, /pr     |
| Price                 | price, p, -p, /p                             |
| Status                | orderstatus, status, os, s, -s, /s           |
| Color                 | color, c, -c, /c                             |
| Color Pattern         | colorpattern, cpattern, colorp, cp, -cp, /cp |
| Birth Date            | birthdate, bdate, birthd, date, bd, -bd, /bd |
| Species               | species, s, -s, /s                           |
| Height                | height, h, -h, /h                            |
| Weight                | weight, w, -w, /w                            |
| Vaccination Status    | vaccinationstatus, vstatus, vs, -vs, /vs     |
| Characteristics       | characteristics, chara, cha, ch, -ch, /ch    |
| Certificates          | certificate, cert, -cert, /cert              |

Examples:
* `sort buyer`
* `sort pet price height weight`
* `sort s n /loc`
* `sort -o pr s p`

### Check : `check`

Checks a contact at specific index, the Ui will display different windows for list input.

Format: `check LIST_TYPE INDEX`

**Note that this check command does not support deliverer list.**

**Note that the input index has to be a valid index**

[Check all list parameters](#list-parameter-table)

#### UI behaviour for the check command

| List type | UI behaviour                                                              |
|-----------|---------------------------------------------------------------------------|
| Buyer     | shows only the list of orders from the buyer at specified index.          |
| Supplier  | shows only the list of pets on sale from the supplier at specified index. |
| Order     | shows the Buyer of the order at specified index.                          |
| Pet       | shows the Supplier of the pet at specified index.                         |

Examples: (Assuming all are valid indexes)
* `check buyer 1`
* `check -s 3`
* `sort o 2`
* `sort /p 4`


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

| Action             | Format, Examples                                                                                                                                                                                             |
|--------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**            | `add r/ROLE n/NAME b/BREED p/PHONE_NUMBER e/EMAIL a/ADDRESS i/ADDITIONAL_INFORMATION [t/TAG]…​` <br> e.g., `add buyer n/Hongyi b/ragdoll p/11223344 e/email@u.nus.edu a/UTR 138600 i/colou:blue t/Singapore` |
| **Delete**         | `delete c/PERSON_CATEGORY i/INDEX` e.g., `delete c/Buyer i/1`                                                                                                                                                |
| **Edit**           | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                         |
| **Find**           | `find PREFIX/KEYWORD` <br> e.g., `find n/James Jake`                                                                                                                                                         |
| **Find Buyer**     | `find-b PREFIX/KEYWORD` <br> e.g., `find-b n/James Jake`                                                                                                                                                     |
| **Find Deliverer** | `find-d PREFIX/KEYWORD` <br> e.g., `find-d n/James Jake`                                                                                                                                                     |
| **Find Supplier**  | `find-s PREFIX/KEYWORD` <br> e.g., `find-s n/James Jake`                                                                                                                                                     |
| **Filter**         | `filter t/INPUT`<br> e.g., `filter t/dog t/second-hand`                                                                                                                                                      |
| **Filter Orders**  | `filter-o PREFIX/KEYWORD`<br> e.g., `filter-o ar/good with children pr/10-100`                                                                                                                               |
| **Filter Pets**    | `filter-p PREFIX/KEYWORD`<br> e.g., `filter-p c/white s/capybara`                                                                                                                                            |
| **Sort**           | `sort LIST_PARAMETER, [ATTRIBUTES...]`<br> e.g., `sort pet price height weight`                                                                                                                              |
| **List**           | `list [LIST_PARAMETER]` <br> e.g., `list buyer`                                                                                                                                                              |
| **Check**          | `check LIST_TYPE INDEX` <br> e.g., `check buyer 1`                                                                                                                                                           |
| **Clear**          | `clear`                                                                                                                                                                                                      |
| **Help**           | `help`                                                                                                                                                                                                       |
