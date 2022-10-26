---
layout: page
title: User Guide
---

Petcode is a **desktop app for coordinating pet sale business, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PetCode can get your contact
management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `petcode.jar` from [here]().

1. Copy the file to the folder you want to use as the _home folder_ for your PetCode.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * **`list buyer/supplier/delivery`** : Lists buyer/supplier/delivery.

    * **`add`**`add buyer n/Hongyi b/ragdoll p/11223344 e/email@u.nus.edu a/UTR 138600 i/colour:blue t/Singapore` : Adds
      a buyer named `Hong Yi` who is looking for a ragdoll to the PetCode.

    * **`delete-b 1`** : Deletes the first contact from the buyer contacts list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

Note: unless otherwise specified, the order of prefixes does not matter.


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

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person : `add`

A person can be of three categories: Buyer, Deliverer, and Supplier.

### Adding a buyer: `add-b`

Adds a buyer to the contacts. A buyer is a person who would like to buy a pet and places an order describing what kind
of pet he/she would like.

Format: `add-b n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">

**:bulb: Tip:** A person can have any number of tags (including 0)

You might just meet a new buyer and would like to add her/him to your list of contacts, and this buyer might have placed
an order for you already! For your convenience, we have a nice feature for you to add a buyer and his/her orders in one
shot! See this:

Format: `add-b n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ o/add-o(order1 prefixes and fields) o/add-o(order2 prefixes and fields)…​`

Basically, you can input as many `o/add-o` prefixes as you need. After each `add-o`, just enter whatever you need to
enter when adding a single order, but you don't have to specify the index of the associated buyer this time. For more
information, you can refer to the Add Order section.

</div>

Examples:

* To add a single buyer: `add-b n/Hongyi p/11223344 e/email@u.nus.edu a/UTR 138600 t/Singapore`
* To add a buyer with one
  order: `add-b n/Hongyi p/11223344 e/email@u.nus.edu a/UTR 138600 t/Singapore o/add-o o_s/Pending o_r/add-r o_a/1 o_s/German Shepherd o_c/black o_cp/black and brown o_p/30 o_pr/20, 50 o_d/2022-10-26 o_ar/vaccinated o_ar/free delivery`
* To add a buyer with two
  orders: `add-b n/Hongyi p/11223344 e/email@u.nus.edu a/UTR 138600 t/Singapore o/add-o o_s/Pending o_r/add-r o_a/1 o_s/German Shepherd o_c/black o_cp/black and brown o_p/30 o_pr/20, 50 o_d/2022-10-26 o_ar/vaccinated o_ar/free delivery o/add-o  o_s/Negotiating o_r/add-r o_a/3 o_s/Chihuahua o_c/white o_cp/dotted white o_p/44.1 o_pr/10.6, -1 o_d/2022-09-20 o_ar/noble blood o_ar/not naughty `

### Adding a deliverer: `add-d`

Adds a deliverer to your contact list. A deliverer delivers pets from suppliers to buyers.

Format: `add-d n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

Examples:

* To add a single deliverer: `add-d n/Hongyi p/11223344 e/email@u.nus.edu a/UTR 138600 t/Singapore`

### Adding a supplier: `add-s`

Adds a supplier to address book. A supplier feeds, trains, and takes care of pets for sale.

Format: `add-s n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

Similarly, you may feel that adding a supplier followed by adding her/his pets one by one is too troublesome. Here is
the convenience. You can also add pets when adding a supplier.

Format: `add-s n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ pt/add-pt(pet1 prefixeds and fields) pt/add-pt(pet2 prefixeds and fields)…​`

you can input as many `pt/add-pt` prefixes as you need. After each `add-pt`, just enter whatever you need to enter when
adding a single pet, but you don't have to specify the index of the associated supplier this time. For more information,
you can refer to the Add Pet section.

Examples:

* To add a single
  supplier: `add-s n/Carol Pet House p/11223344 e/carolpethouse@gmail.com a/Marina Bay Sands 138600 t/discount for more than one purchase`
* To add a supplier with two pets for
  sale: `add-s n/Carol Pet House p/11223344 e/carolpethouse@gmail.com a/Marina Bay Sands 138600 t/discount for more than one purchase pt/add-pt pt_n/Page pt_d/2022-1-1 pt_c/pink pt_cp/pure pink pt_h/41 pt_s/pig pt_cert/US certified pt_v/true pt_w/102 pt_p/270 pt_t/good pt_t/young pt/add-pt pt_n/Snupy pt_d/2021-05-31 pt_c/white pt_cp/dotted pt_h/89.3 pt_cert/US certified pt_s/rabbit pt_v/false pt_w/32 pt_p/330 pt_t/short-sighted`

### Adding an order to a buyer: `add-o`

Every time a new order is placed, you need to add it to who placed it, don't you? So you can use the following format to
add a new order.

Format: `add-o i/INDEX_OF_BUYER o_s/STATUS o_r/add-r o_a/AGE o_s/SPECIES o_c/COLOR o_cp/COLOR_PATTERN o_p/PRICE o_pr/PRICE_RANGE o_d/DATE [o_ar/ADDITIONAL_REQUEST]…​`

`i/INDEX_OF_BUYER` is the one-based index of the buyer you would like to add this order to. You can find out the index
just in the display list. You may want to "reset" the list to find out the buyer you cannot see, if you filtered the
list. Please also make sure that `o_r/` is followed by `add-r` immediately and that there are no other prefixes
between `o_r/`, `o_a/`, `o_c/`, `o_cp/`, and `o_s/`, because they as a whole specify how the requested pet should be
like. In the future, you might be able to define your own requests as templates for generating orders.

For more information about the prefixes, kindly navigate to the summary table here.

Examples:

* `add-o o_s/Pending o_r/add-r o_a/1 o_s/German Shepherd o_c/black o_cp/black and brown o_p/30 o_pr/20, 50 o_d/2022-10-26 o_ar/vaccinated o_ar/free delivery`
* `add-o  o_s/Negotiating o_r/add-r o_a/3 o_s/Chihuahua o_c/white o_cp/dotted white o_p/44.1 o_pr/10.6, -1 o_d/2022-09-20 o_ar/noble blood o_ar/not naughty`

### Adding a pet to a supplier: `add-pt`

When a new pet comes up to stock, you may also want to add it to its supplier/owner. Now it is the time!

Format: `add-pt i/INDEX_OF_SUPPLIER pt_n/PET_NAME pt_d/DATE_OF_BIRTH pt_c/COLOR pt_cp/COLOR_PATTERN pt_h/HEIGHT pt_w/WEIGHT pt_s/SPECIES pt_v/VACCINATION_STATUS pt_p/PRICE [pt_cert/CERTIFICATE]…​ [pt_t/TAG]…​`

`i/INDEX_OF_SUPPLIER` is the one-based index of the supplier you would like to add this pet to. You can find out the
index just in the display list. You may want to "reset" the list to find out the supplier you cannot see, if you
filtered the list.

For more information about the prefixes, kindly navigate to the summary table here.

### Adding a person with a pop-up window : `add`

Adds a person to the contacts with a pop-up window that has prompt texts for what to input without the need to enter any
prefixes to reduce memorisation work.
Given below is the pop-up window for adding a supplier.

![pop up window for adding a supplier](images/AddSupplierWithPopup.png)

The followings are two ways to use this command:

* Adds a buyer to the contacts with or without any number of orders.

  Format: `Add buyer`

* Adds a supplier to the contacts with or without any number of pets.

  Format: `Add supplier`

Useful keyboard shortcuts for the pop-up window:

| Keyboard shortcut |                                   Associated action                                    |
|:-----------------:|:--------------------------------------------------------------------------------------:|
|      ESCAPE       |                      Closes the pop-up window **without saving**                       |
|       ENTER       |                           Goes to the next input text field                            |
|     CTRL + A      |                        Adds an order/pet to the buyer/supplier                         |
|     CTRL + D      |        Deletes the last order/pet under the buyer/supplier in the pop-up window        |
|     CTRL + S      | Saves the inputs, adds the buyer/supplier to the contacts, and closes the pop-p window |

<div markdown="span" class="alert alert-primary">

**:bulb: Tip:** If a compulsory text field is ***empty*** during saving, the cursor will be brought be the text field,
which will be highlighted in red.

</div>

<div markdown="span" class="alert alert-primary">

**:bulb: Tip:** If the input of a text field is in the ***wrong format*** during saving, the person will not be added to
the contacts and the pop-up window will not close.
The error message and the correct format of the input will be shown in the **main window**.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: This command is only available for **adding a buyer or supplier** for the current version.

</div>

### Listing contacts or items : `list`

Shows a list of all contacts.

Format: `list all`

Shows a list of persons based on their role as suppliers, buyers, or delivery services.

Format: `list buyer`, `list supplier`, `list deliverer`

Shows a list of items.

Format: `list order`, `list pet`

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

### Locating persons by name : `find`

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

### Deleting a contact or item : `delete`

Deletes a contact / item at the specified index of the respective contact / item list.

Format: `delete-KEY INDEX`

| Contact / Item to Delete | Key | Example                                                                                           |
|--------------------------|----|---------------------------------------------------------------------------------------------------|
| Buyer                    | b  | `delete-b 1`, Deletes Buyer contact at index 1 of Buyer Contacts List, if index is found.         |
| Supplier                 | s  | `delete-s 1`, Deletes Supplier contact at index 1 of Supplier Contacts List, if index is found.   |
| Deliverer                | d  | `delete-d 1`, Deletes Deliverer contact at index 1 of Deliverer Contacts List, if index is found. |
| Order                    | o  | `delete-o 1`, Deletes Order at index 1 of Orders List, if index is found.                         |
| Pet                      | p  | `delete-p 1`, Deletes Pet at index 1 of Pets List, if index is found.                             |

### Filter contacts : `filter`

Filter display only buyers or suppliers based on the given tag(s).

Format: `filter t/INPUT`

Examples:

* `filter t/cat`
* `filter t/dog t/second-hand`

### Filter orders : `filter-o`

Filter display only Orders based on the given attribute(s). There are three possible attributes to filter: Additional
requests, Order status, Price range.

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

Filter display only Pets based on the given attributes. There are five possible attributes to filter: Color, Name,
Price, Species, Vaccination status.

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

Find displays all persons who match ONE on specific attribute. There are five possible attributes for finding person(s):
Address, Email, Location, Name, Phone.

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

Find displays all buyers who match ONE on specific attribute. There are five possible attributes for finding buyer(s):
Address, Email, Location, Name, Phone.

To find corresponding prefixes to each attribute (ie. Address), please refer to the table in the "Find" Command user
guide above.

Format: `find-b [PREFIX]/INPUT`

Examples:

* `find-b a/6th College Ave. West`
* `find-b e/blackball@furry.com`
* `find-b p/98986668`

### Find Deliverers : `find-d`

Find displays all deliverers who match ONE on specific attribute. There are five possible attributes for finding
deliverer(s): Address, Email, Location, Name, Phone.

To find corresponding prefixes to each attribute (ie. Address), please refer to the table in the "Find" Command user
guide above.

Format: `find-d [PREFIX]/INPUT`

Examples:

* `find-d a/6th College Ave. West`
* `find-d e/blackball@furry.com`
* `find-d p/98986668`

### Find Suppliers : `find-s`

Find displays all suppliers who match ONE on specific attribute. There are five possible attributes for finding
supplier(s): Address, Email, Location, Name, Phone.

To find corresponding prefixes to each attribute (ie. Address), please refer to the table in the "Find" Command user
guide above.

Format: `find-s [PREFIX]/INPUT`

Examples:

* `find-s a/6th College Ave. West`
* `find-s e/blackball@furry.com`
* `find-s p/98986668`

### Sort contacts : `sort`

Sort the contacts based on given list type and attributes in ascending order.

Format: `sort LIST_PARAMETER [ATTRIBUTES...]`

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

PetCode data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Editing the data file

PetCode data are saved as a JSON file `[JAR file location]/data/PetCode.json`. Advanced users are welcome to update data
directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** If your changes to the data file makes its format invalid, PetCode will discard all data and
start with an empty data file at the next run.

</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous PetCode home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

## List of Prefixes

These prefixes are for you to indicate different fields when you add a new person, a new order, or a new pet.

| Prefix | Category | Meaning |
|--------|----------|---------|
|


| Action             | Format, Examples                                                                                                                                                                                             |
|--------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**            | `add r/ROLE n/NAME b/BREED p/PHONE_NUMBER e/EMAIL a/ADDRESS i/ADDITIONAL_INFORMATION [t/TAG]…​` <br> e.g., `add buyer n/Hongyi b/ragdoll p/11223344 e/email@u.nus.edu a/UTR 138600 i/colou:blue t/Singapore` |
| **Add**            | `add buyer`, `add supplier`                                                                                                                                                                                  |
| **Clear**          | `clear`                                                                                                                                                                                                      |
| **Delete**         | `delete-KEY INDEX` e.g., `delete-b 1`, `delete-d 2`, `delete-s 3`, `delete-o 1`, `delete-p 2`                                                                                                                |
| **Find**           | `find PREFIX/KEYWORD` <br> e.g., `find n/James Jake`                                                                                                                                                         |
| **Find Buyer**     | `find-b PREFIX/KEYWORD` <br> e.g., `find-b n/James Jake`                                                                                                                                                     |
| **Find Deliverer** | `find-d PREFIX/KEYWORD` <br> e.g., `find-d n/James Jake`                                                                                                                                                     |
| **Find Supplier**  | `find-s PREFIX/KEYWORD` <br> e.g., `find-s n/James Jake`                                                                                                                                                     |
| **Filter**         | `filter t/INPUT`<br> e.g., `filter t/dog t/second-hand`                                                                                                                                                      |
| **Filter Orders**  | `filter-o PREFIX/KEYWORD`<br> e.g., `filter-o ar/good with children pr/10-100`                                                                                                                               |
| **Filter Pets**    | `filter-p PREFIX/KEYWORD`<br> e.g., `filter-p c/white s/capybara`                                                                                                                                            |
| **Help**           | `help`                                                                                                                                                                                                       |
| **List**           | `list all`, `list buyer`, `list supplier`, `list delivery`, `list order`, `list pet`                                                                                                                         |
| **Sort**           | `Sort t/TAG Asc/Desc, [t/TAG] [Asc/Desc]…​`<br> e.g., `Sort t/priority Desc, t/country Desc`                                                                                                                 |

