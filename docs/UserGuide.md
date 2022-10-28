---
layout: page
title: User Guide
---

Petcode is a **desktop app for coordinating pet sale business, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PetCode can get your contact
management tasks done faster than traditional GUI apps.

## Table of Contents

- [Quick Start](#quick-start)
- [Commands](#commands)
    * [Viewing help](#viewing-help--help)
    * [Adding a contact or item](#adding-a-contact-or-item-add)
        + [Adding a buyer](#adding-a-buyer-add-b)
        + [Adding a deliverer](#adding-a-deliverer-add-d)
        + [Adding a supplier](#adding-a-supplier-add-s)
        + [Adding an order to a buyer](#adding-an-order-to-a-buyer-add-o)
        + [Adding a pet to a supplier](#adding-a-pet-to-a-supplier--add-pt)
        + [Adding a person with a popup window](#adding-a-person-with-a-popup-window--add) [RECOMMENDED FOR BEGINNERS]
    * [Listing contacts or items](#listing-contacts-or-items--list)
    * [Deleting a contact or item](#deleting-a-contact-or-item--delete)
    * [Editing attributes of a contact](#editing-attributes-of-a-contact--edit)
    * [Finding a contact using keywords](#finding-a-contact-using-keywords--find)
        + [Finding a buyer](#finding-a-buyer--find-b)
        + [Finding a supplier](#finding-a-supplier--find-s)
        + [Finding a deliverer](#finding-a-supplier--find-s)
    * [Filtering lists by tags](#filtering-lists-by-tags--filter)
        + [Filtering contacts](#filtering-contacts--filter)
        + [Filtering orders](#filtering-orders--filter-o)
        + [Filtering pets](#filtering-pets--filter-p)
    * [Sorting contacts](#sorting-contacts--sort)
    * [Checking which item belongs to which contact](#checking-which-item-belongs-to-which-contact--check)
    * [Clearing all contacts](#clearing-all-entries--clear)
    * [Exiting the program](#exiting-the-program--exit)
- [How data is stored](#how-data-is-stored)
    * [Saving contacts and items](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
    * [Archiving data files](#archiving-data-files-coming-in-v20)
- [FAQ](#faq)
- [Summaries](#summaries)
    * [List of prefixes](#list-of-prefixes)
    * [Command summary](#command-summary)

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

    * **`add buyer n/Hongyi b/ragdoll p/11223344 e/email@u.nus.edu a/UTR 138600 i/colour:blue t/Singapore`** : Adds
      a buyer named `Hong Yi` who is looking for a ragdoll to the PetCode.

    * **`delete-b 1`** : Deletes the first contact from the buyer contacts list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

1. Refer to the [Commands](#commands) Section below for details of each command.

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

## Commands

<div markdown="block" class="alert alert-info">

:information_source: **How to interpret the Command format:**<br>

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

**Additional Information:**

* Unless otherwise specified, the order of prefixes does not matter.
* Contact refers to a person added to the address book. It can be of the following three categories: Buyer, Supplier,
  and Deliverer.
* Item refers to an Order or Pet. An Order refers to the order placed by Buyer. A Pet refers to the pet sold by a
  Supplier.

[Go back to [Table of Contents](#table-of-contents)]

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Adding a contact or item: `add`

Adds a contact or item to the address book.

* A contact can be of three categories: Buyer, Deliverer, and Supplier.
* An item can be either an Order or Pet.

General Format for add command: `add-KEY prefix/PARAMETERS...`, where:

* `KEY` specifies what type of contact or item you want to add.
* `prefix` indicates the kind of information you are adding.
* `PARAMETER` is the information you are adding.

Kindly refer to the [Summaries](#summaries) section for more information.

:exclamation:If you are a beginner, we highly recommend you to use
the [Add Command using the popup window](#adding-a-person-with-a-popup-window--add)
instead of the usual CLI interface.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a BUYER: `add-b`

Adds a buyer to the contacts. A buyer is a person who would like to buy a pet and places an order describing what kind
of pet he/she would like.

Format: `add-b n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** A person can have any number of tags (including 0)

After meeting a new customer with an order and would like to add her/him to your list of contacts, we have a
nice feature for you to add a buyer and his/her orders in one shot! Check it out below :point_down:

Format: `add-b n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ o/add-o(order1 prefixes and fields) o/add-o(order2 prefixes and fields)…​`

:exclamation: Note that you can input as many `o/add-o` prefixes as you need. After each `add-o`, simply enter the
details for
the order, and you don't have to specify the index of the associated buyer this time. For more
information, you can refer to the [Add Order](#adding-an-order-to-a-buyer-add-o) section.

</div>

Examples:

* To add a single buyer: `add-b n/Hongyi p/11223344 e/email@u.nus.edu a/UTR 138600 t/Singapore`
* To add a buyer with one
  order: `add-b n/Hongyi p/11223344 e/email@u.nus.edu a/UTR 138600 t/Singapore o/add-o o_s/Pending o_r/add-r o_a/1 o_s/German Shepherd o_c/black o_cp/black and brown o_p/30 o_pr/20, 50 o_d/2022-10-26 o_ar/vaccinated o_ar/free delivery`
* To add a buyer with two
  orders: `add-b n/Hongyi p/11223344 e/email@u.nus.edu a/UTR 138600 t/Singapore o/add-o o_s/Pending o_r/add-r o_a/1 o_s/German Shepherd o_c/black o_cp/black and brown o_p/30 o_pr/20, 50 o_d/2022-10-26 o_ar/vaccinated o_ar/free delivery o/add-o  o_s/Negotiating o_r/add-r o_a/3 o_s/Chihuahua o_c/white o_cp/dotted white o_p/44.1 o_pr/10.6, -1 o_d/2022-09-20 o_ar/noble blood o_ar/not naughty `

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a DELIVERER: `add-d`

Adds a deliverer to your contact list. A deliverer delivers pets from suppliers to buyers.

Format: `add-d n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

Examples:

* To add a single deliverer: `add-d n/Hongyi p/11223344 e/email@u.nus.edu a/UTR 138600 t/Singapore`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a SUPPLIER: `add-s`

Adds a supplier to address book. A supplier feeds, trains, and takes care of pets for sale.

Format: `add-s n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

Similar to the [Add Buyer](#adding-a-buyer-add-b) command, you may feel that adding a supplier followed by adding
her/his pets for sale is too troublesome. For your convenience, we have added a feature where you can add a pet to the
supplier in one shot! Check it out below :point_down:

Format: `add-s n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ pt/add-pt(pet1 prefixeds and fields) pt/add-pt(pet2 prefixeds and fields)…​`

:exclamation: Note that you can input as many `pt/add-pt` prefixes as you need. After each `add-pt`, simply enter the
details
for the pet, and you don't have to specify the index of the associated supplier this time. For more information,
you can refer to the [Add Pet](#adding-a-pet-to-supplier--add-pt) section.

Examples:

* To add a single
  supplier: `add-s n/Carol Pet House p/11223344 e/carolpethouse@gmail.com a/Marina Bay Sands 138600 t/discount for more than one purchase`
* To add a supplier with one pet for
  sale: `add-s n/Carol Pet House p/11223344 e/carolpethouse@gmail.com a/Marina Bay Sands 138600 t/discount for more than one purchase pt/add-pt pt_n/Page pt_d/2022-1-1 pt_c/pink pt_cp/pure pink pt_h/41 pt_s/pig pt_cert/US certified pt_v/true pt_w/102 pt_p/270 pt_t/good pt_t/young`
* To add a supplier with two pets for
  sale: `add-s n/Carol Pet House p/11223344 e/carolpethouse@gmail.com a/Marina Bay Sands 138600 t/discount for more than one purchase pt/add-pt pt_n/Page pt_d/2022-1-1 pt_c/pink pt_cp/pure pink pt_h/41 pt_s/pig pt_cert/US certified pt_v/true pt_w/102 pt_p/270 pt_t/good pt_t/young pt/add-pt pt_n/Snupy pt_d/2021-05-31 pt_c/white pt_cp/dotted pt_h/89.3 pt_cert/US certified pt_s/rabbit pt_v/false pt_w/32 pt_p/330 pt_t/short-sighted`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding an ORDER TO A BUYER: `add-o`

Every time a new order is placed, you need to add it to the customer placed it, don't you? Hence, you can use the
following format to
add a new order :point_down:

Format: `add-o i/INDEX_OF_BUYER o_s/STATUS o_r/add-r o_a/AGE o_s/SPECIES o_c/COLOR o_cp/COLOR_PATTERN o_p/PRICE o_pr/PRICE_RANGE o_d/DATE [o_ar/ADDITIONAL_REQUEST]…​`

`i/INDEX_OF_BUYER` is the one-based index of the buyer you would like to add this order to. You can find out the index
in the displayed buyer list. You may want to use the [List Command](#listing-contacts-or-items--list) to find the buyer,
if you have filtered the list.

:exclamation: Please ensure that `o_r/` is followed by `add-r` immediately and that there are no other prefixes
between `o_r/`, `o_a/`, `o_c/`, `o_cp/`, and `o_s/`. This is because they as a whole specify how the requested pet
should be
like. In the future, you may be able to define your own requests as templates for generating orders.

For more information about the prefixes, kindly navigate to the summary table [here](#list-of-prefixes).

Examples:

* `add-o o_s/Pending o_r/add-r o_a/1 o_s/German Shepherd o_c/black o_cp/black and brown o_p/30 o_pr/20, 50 o_d/2022-10-26 o_ar/vaccinated o_ar/free delivery`
* `add-o  o_s/Negotiating o_r/add-r o_a/3 o_s/Chihuahua o_c/white o_cp/dotted white o_p/44.1 o_pr/10.6, -1 o_d/2022-09-20 o_ar/noble blood o_ar/not naughty`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a PET TO A SUPPLIER : `add-pt`

When a new pet comes up to stock, you may want to add it to its supplier. Now is the time!

Format: `add-pt i/INDEX_OF_SUPPLIER pt_n/PET_NAME pt_d/DATE_OF_BIRTH pt_c/COLOR pt_cp/COLOR_PATTERN pt_h/HEIGHT pt_w/WEIGHT pt_s/SPECIES pt_v/VACCINATION_STATUS pt_p/PRICE [pt_cert/CERTIFICATE]…​ [pt_t/TAG]…​`

`i/INDEX_OF_SUPPLIER` is the one-based index of the supplier you would like to add this pet to. You can find out the
index just in the display list. You may want to use the [List Command](#listing-contacts-or-items--list) to find the
supplier,
if you have filtered the list.

For more information about the prefixes, kindly navigate to the summary table [here](#list-of-prefixes).

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Adding a person WITH A POPUP WINDOW : `add`

Adds a person to the contacts with a pop-up window that has prompt texts for what to input without the need to enter any
prefixes. This reduces the need to memorise prefixes.
Given below is the pop-up window for adding a supplier.

![pop up window for adding a supplier](images/AddSupplierWithPopup.png)

The followings are two ways to use this command:

* Adds a buyer to the contacts with or without any number of orders.

  Format: `add buyer`

* Adds a supplier to the contacts with or without any number of pets.

  Format: `add supplier`

:information_source: **Useful keyboard shortcuts for the pop-up window:**

| Keyboard shortcut | Associated action                                                                      |
|:-----------------:|:---------------------------------------------------------------------------------------|
|      ESCAPE       | Closes the pop-up window **without saving**                                            |
|       ENTER       | Goes to the next input text field                                                      |
|     CTRL + A      | Adds an order/pet to the buyer/supplier                                                |
|     CTRL + D      | Deletes the last order/pet under the buyer/supplier in the pop-up window               |
|     CTRL + S      | Saves the inputs, adds the buyer/supplier to the contacts, and closes the pop-p window |

<div markdown="span" class="alert alert-primary">

**:bulb: Tip:** If a compulsory text field is ***empty*** during saving, the cursor will be brought to that text field,
which will be highlighted in red.

</div>

<div markdown="span" class="alert alert-primary">

**:bulb: Tip:** If the input of a text field has the ***wrong format*** during saving, the person will not be added to
the contacts and the pop-up window will not close.
The error message and the correct format of the input will be shown in the **main window**.

</div>

<div markdown="span" class="alert alert-warning">

:exclamation: This command is only available for **adding a buyer or supplier** for the current version.

</div>

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Matching pets to an order : `match`

At times, you may want to find out which pet waiting for sale is the best fit when you receive a new order. Now it is
the time for you to check this command out.

Format: `match i/INDEX`

`i/INDEX` is the one-based index of the order to which you would like to find the best fitting pet. This command will
sort all pets currently being displayed on your screen (so the pets filtered out will not count) based on our score
evaluation algorithm in descending order. The top few pets are probably what you want. And now, time to contact the
suppliers who own these pets for further negotiation. If you are interested in the algorithm, check out our developer
guide.
At this stage, the score is calculated using a default set of weights. In the future, you may be able to define your own
weights for different fields, such as price, age, species and so on.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Listing contacts or items : `list`

Displays the specified type of contacts or items. This command is especially useful when you want to find the index of a
contact / item.

Format: `list KEY`

#### KEY Types Table

| Contact / Item to List  |    KEY    |
|:-----------------------:|:---------:|
|          Buyer          |   buyer   |
|        Supplier         | supplier  |
|        Deliverer        | deliverer |
|          Order          |   order   |
|           Pet           |    pet    |

Examples:
* `list buyer`, lists all Buyer contacts.
* `list deliverer`, lists all Deliverer contacts.
* `list supplier`, lists all Supplier contacts.
* `list all`, lists all Buyer, Deliverer, and Supplier contacts.
* `list order`, lists all Orders.
* `list pet`, lists all Pets.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Deleting a contact or item : `delete`

Deletes a contact / item at the specified index of the respective contact / item list.

Format: `delete-KEY INDEX`

#### KEY Types Table

| Contact / Item to Delete | KEY |
|:------------------------:|:---:|
|          Buyer           |  b  |
|         Supplier         |  s  |
|        Deliverer         |  d  |
|          Order           |  o  |
|           Pet            |  p  |

Examples:
* `delete-b 1`, deletes `Buyer` contact at index 1 of Buyer List, if index is found.
* `delete-s 1`, deletes `Supplier` contact at index 1 of Supplier List, if index is found.
* `delete-d 1`, deletes `Deliverer` contact at index 1 of Deliverer List, if index is found.
* `delete-o 1`, deletes `Order` at index 1 of Orders List, if index is found.
* `delete-p 1`, deletes `Pet` at index 1 of Pets List, if index is found.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Editing attribute(s) of a contact : `edit`

Edits one or more attributes of `Person` by the index number used in the displayed person list.
Existing values of that attribute will be overwritten by the input values.

<div markdown="span" class="alert alert-warning">

:exclamation: This command is only available for **editing the basic contact information** of the `Person` for the current version. In other words, **information regarding `Order`/`Pet`** that the `Person` possess **cannot be modified**.

</div>

Format: `edit-ROLE INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`

#### ROLE Types Table

| Contact to Edit | Role |
|:---------------:|:----:|
|      Buyer      |  b   |
|    Supplier     |  s   |
|    Deliverer    |  d   |

Examples: 
* `edit-b 1 n/Alex`, modifies the name of the `Buyer` contact at index 1 of Buyer List to Alex, if index is found.
* `edit-s 3 n/Bobby p//884321` modifies the name to Bobby and phone to 884321, of the `Supplier` contact at index 3 of Supplier List to Alex, if index is found.

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Finding contact(s) using keywords : `find`

Displays all contacts which match ONE specific attribute. This command is especially useful when you want to quickly
find contacts based on a keyword.

There are five possible attributes for finding contact(s):
Address, Email, Location, Name, Phone.

Format: `find [PREFIX]/INPUT`

#### Attributes and Their Corresponding Prefixes Table

| Attribute | Prefix | Format    | Example                |
|-----------|--------|-----------|------------------------|
| Address   | a      | a/KEYWORD | a/Wall Street          |
| Email     | e      | e/KEYWORD | e/whereisamy@gmail.com |
| Location  | l      | l/KEYWORD | l/Nova Scotia          |
| Name      | n      | n/KEYWORD | n/Amy Toh              |
| Phone     | p      | p/NUMBER  | p/81234567             |

Examples:

* `find a/6th College Ave West`
* `find e/blackball@furry.com`
* `find p/98986668`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Finding a BUYER : `find-b`

Displays all buyers who match ONE specific attribute. Check out
the [Attributes and Their Corresponding Prefixes Table](#attributes-and-their-corresponding-prefixes-table)
for more information.

Format: `find-b [PREFIX]/INPUT`

Examples:

* `find-b a/6th College Ave West`
* `find-b e/blackball@furry.com`
* `find-b p/98986668`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Finding a DELIVERER : `find-d`

Displays all deliverers who match ONE specific attribute. Check out
the [Attributes and Their Corresponding Prefixes Table](#attributes-and-their-corresponding-prefixes-table)
for more information.

Format: `find-d [PREFIX]/INPUT`

Examples:

* `find-d a/6th College Ave West`
* `find-d e/blackball@furry.com`
* `find-d p/98986668`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Finding a SUPPLIER : `find-s`

Displays all suppliers who match ONE specific attribute. Check out
the [Attributes and Their Corresponding Prefixes Table](#attributes-and-their-corresponding-prefixes-table)
for more information.

Format: `find-s [PREFIX]/INPUT`

Examples:

* `find-s a/6th College Ave West`
* `find-s e/blackball@furry.com`
* `find-s p/98986668`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Filtering items by attributes : `filter`

Displays items based on the specified attribute(s). This command is especially useful when you want to coordinate
sales between a Buyer and Supplier.

#### Filtering ORDERS : `filter-o`

Displays only Orders based on the given attribute(s). There are three possible attributes to filter: Additional
requests, Order status, Price range.

| Attribute           | Prefix | Format                     | Example         |
|---------------------|--------|----------------------------|-----------------|
| Additional requests | ar     | ar/KEYWORD                 | ar/non-shedding |
| Order Status        | os     | os/KEYWORD                 | os/Negotating   |
| Price Range         | pr     | pr/LOWER_PRICE-UPPER_PRICE | pr/100-456      |

Format: `filter-o [PREFIX]/INPUT`

Examples:

* `filter-o os/Pending`
* `filter-o as/Negotiating p/90-900`
* `filter-o ar/good with children os/Delivering p/80-100`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

#### Filtering PETS : `filter-p`

Displays only Pets based on the given attributes. There are five possible attributes to filter: Color, Name,
Price, Species, Vaccination status.

| Attribute          | Prefix | Format    | Example         |
|--------------------|--------|-----------|-----------------|
| Color              | c      | c/KEYWORD | c/pink          |
| Name               | n      | n/KEYWORD | n/nyanko-sensei |
| Price              | p      | p/PRICE   | p/209           |
| Species            | s      | s/KEYWORD | s/ostrich       |
| Vaccination Status | v      | v/KEYWORD | v/false         |

Format: `filter-p [PREFIX]/INPUT`

Examples:

* `filter-p c/white`
* `filter-p c/black v/true`
* `filter-p c/black n/doraemon p/50 s/cat v/true`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Sorting contacts : `sort`

Format: `sort LIST_PARAMETER [ATTRIBUTES...]`

:exclamation:**Note that different lists could have different supported sort attributes.**

Check out the acceptable List Parameters for different types of contacts /
items [here](#acceptable-list-parameters-for-contacts--items-table).

#### List Attribute Table

| List type | Attributes                                                                                                                  |
|-----------|-----------------------------------------------------------------------------------------------------------------------------|
| Buyer     | *Number of Order*, Name, Phone, Email, Location, Address                                                                    |
| Supplier  | *Number of Pet On sale*, Name, Phone, Email, Location, Address                                                              |
| Deliverer | *Number of Order*, Name, Phone, Email, Location, Address                                                                    |
| Order     | *Due Date*, Price Range, Price, Status                                                                                      |
| Pet       | *Price*, Name, Color, Color Pattern, Birth Date, Species, Height, Weight, Vaccination Status, Characteristics, Certificates |

> The italicised text attribute represents the default sorting attribute for each list. <br>
> For example, `sort pet` will sort the Pets list in default by the price attribute.

#### Acceptable Attribute Parameters Table

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

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Checking which item belongs to which contact : `check`

Checks a contact at specified index, the application will display different windows for each list input.
This command is especially useful for checking which Order belongs to which Buyer, and which Pet belongs to which
Supplier.

Format: `check LIST_PARAMETER INDEX`

:exclamation: **Please take note of the following:**

* This command does not support the Deliverers list.
* The input index has to be a valid index.

Check out the acceptable List Parameters for different types of contacts /
items [here](#acceptable-list-parameters-for-contacts--items-table).

#### Application Behaviour for the Check command

| List type | Application behaviour                                                     |
|-----------|---------------------------------------------------------------------------|
| Buyer     | shows only the list of orders from the buyer at specified index.          |
| Supplier  | shows only the list of pets on sale from the supplier at specified index. |
| Order     | shows the Buyer of the order at specified index.                          |
| Pet       | shows the Supplier of the pet at specified index.                         |

Examples: (Assuming all are valid indexes)

* `check buyer 1`
* `check -s 3`
* `check o 2`
* `check /p 4`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Clearing all entries : `clear`

Clears all entries from the PetCode.

Format: `clear`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

### Exiting the program : `exit`

Exits the program.

Format: `exit`

[Go back to [Table of Contents](#table-of-contents)]
[Go back to [Commands](#commands)]

## How data is stored

### Saving the data

PetCode data is saved into your computer's hard disk automatically after any command that changes the data.
There is no need to save manually.

### Editing the data file

PetCode data is saved as a JSON file `[JAR file location]/data/PetCode.json`.
Advanced users are welcome to update the data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:** If your changes to the data file makes its format invalid, PetCode will discard all data and
start with an empty data file at the next run.

</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous PetCode home folder.

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

## Summaries

### List of Prefixes

These prefixes are for you to indicate different fields when you add a new person, a new order, or a new pet.

| Prefix     | Category        | Meaning                          | Usage                                                                                                                                                                                                                                                                                                                              | Example                                          |
|------------|-----------------|----------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------|
| `n/`       | General Person  | Name                             | A string of any characters. Required.                                                                                                                                                                                                                                                                                              | `n/John Halstead`                                |
| `p/`       | General Person  | Phone number                     | Numbers only. Required.                                                                                                                                                                                                                                                                                                            | `p/80334455`                                     |
| `e/`       | General Person  | Email address                    | A string of any characters. Must contain `@` and follow email format. Required.                                                                                                                                                                                                                                                    | `e/1324@sample.com`                              |
| `a/`       | General Person  | Address                          | A string of any characters. Required.                                                                                                                                                                                                                                                                                              | `a/36 College Ave East, Singapore 138600`        |
| `i/`       | General Person  | Index                            | A positive integer counting from 1. Required.                                                                                                                                                                                                                                                                                      | `i/4`                                            |
| `t/`       | General Person  | Tag that describes a person      | A string of any characters. Optional. Can have multiple.                                                                                                                                                                                                                                                                           | `t/old buyer`, `t/good partner`                  |
| `o/`       | Order           | Order                            | Always followed by `add-o`. Optional, if no orders to add when adding a buyer. Can have multiple.                                                                                                                                                                                                                                  | `o/add-o`                                        |
| `o_s/`     | Order           | Order status                     | `Pending`, `Negotiating`, or `Delivering`                                                                                                                                                                                                                                                                                          | `o_s/Pending`                                    |
| `o_p/`     | Order           | Price                            | A non-negative decimal number. Use `-1` to indicate not settled price. Required.                                                                                                                                                                                                                                                   | `o_p/38.6`                                       |
| `o_pr/`    | Order           | Price range                      | This is for you to use when negotiating with buyer -- the range the price is expected  to fall within. Two non-negative decimal numbers, separated by a comma `,`. The first must not be greater than the second. If you haven't settled down one or two of the bounds, use `-1` to indicate not applicable price bound. Required. | `o_pr/-1, -1`, `o_pr/2.9, -1`, `o_pr/4.3, 19.5`  |
| `o_d/`     | Order           | Transaction (scheduled) date     | In the format `yyyy-MM-dd`.                                                                                                                                                                                                                                                                                                        | `o_d/2022-10-28`, `o_d/2022-9-2`                 |
| `o_r/`     | Order (Request) | Order request                    | Always followed by `add-r`. The Request group of prefixes describe what kind of pet this order seeks. Required.                                                                                                                                                                                                                    | `o_r/add-r`                                      |
| `o_a/`     | Order (Request) | Age                              | A non-negative integer. Required.                                                                                                                                                                                                                                                                                                  | `o_a/5`                                          |
| `o_s/`     | Order (Request) | Species                          | A string of any characters. Required.                                                                                                                                                                                                                                                                                              | `o_s/Chihuahua`, `o_s/German shepherd`           |
| `o_c/`     | Order (Request) | Color                            | A string of any characters. Required.                                                                                                                                                                                                                                                                                              | `o_c/red`                                        |
| `o_cp/`    | Order (Request) | Color pattern                    | A string of any characters. This describes the appearance of the pet in more detail. Required.                                                                                                                                                                                                                                     | `o_cp/white stripped`, `o_cp/black dotted`       |
| `o_ar/`    | Order           | Additional request of the order. | A string of any characters. Optional. Can have multiple.                                                                                                                                                                                                                                                                           | `o_ar/free delivery`, `o_ar/arrive in 10 days`   |
| `pt/`      | Pet             | Pet                              | Always followed by `add-pt`. Optional, if no orders to add when adding a supplier. Can have multiple.                                                                                                                                                                                                                              | `pt/add-pt`                                      |
| `pt_n/`    | Pet             | Pet name                         | A string of any characters. Required.                                                                                                                                                                                                                                                                                              | `pt_n/Page`                                      |
| `pt_s/`    | Pet             | Species                          | A string of any characters. Required.                                                                                                                                                                                                                                                                                              | `pt_s/Chihuahua`, `pt_s/German shepherd`         |
| `pt_d/`    | Pet             | Date of birth of the pet         | In the format `yyyy-MM-dd`.                                                                                                                                                                                                                                                                                                        | `pt_d/2020-3-29`                                 |
| `pt_c/`    | Pet             | Color                            | A string of any characters. Required.                                                                                                                                                                                                                                                                                              | `pt_c/blue`                                      |
| `pt_cp/`   | Pet             | Color pattern                    | A string of any characters. This describes the appearance of the pet in more detail. Required.                                                                                                                                                                                                                                     | `pt_cp/blue grids`                               |
| `pt_h/`    | Pet             | Height                           | A non-negative decimal number. The unit is cm. Required.                                                                                                                                                                                                                                                                           | `pt_h/33.2`                                      |
| `pt_w/`    | Pet             | Weight                           | A non-negative decimal number. The unit is kg. Required.                                                                                                                                                                                                                                                                           | `pt_w/58.2`                                      |
| `pt_p/`    | Pet             | Price                            | A non-negative decimal number. This is the price the pet is to be sold at. Use `-1` to indicate not settled price. Required.                                                                                                                                                                                                       | `pt_p/55.5`                                      |
| `pt_v/`    | Pet             | Vaccination status               | `true` if the pet is vaccinated, otherwise `false`. Required.                                                                                                                                                                                                                                                                      | `pt_v/false`                                     |
| `pt_cert/` | Pet             | Certificate                      | A string of any characters. Other certificates this pet holds. Optional. Can have multiple.                                                                                                                                                                                                                                        | `pt_cert/US certified`, `pt_cert/noble blood`    |
| `pt_t/`    | Pet             | Tag                              | A string of any characters. Optional. Can have multiple.                                                                                                                                                                                                                                                                           | `pt_t/good`, `pt_t/naughty`, `pt_t/fast growing` |

### Command Summary


|            Action            | Format                                                                                        | Examples                                                                                      |
|:----------------------------:|-----------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
|           **Add**            | `add-ROLE n/NAME b/BREED p/PHONE_NUMBER e/EMAIL a/ADDRESS i/ADDITIONAL_INFORMATION [t/TAG]…`  | `add-b n/Hongyi b/ragdoll p/11223344 e/email@u.nus.edu a/UTR 138600 i/colou:blue t/Singapore` |
| **Add** (using popup window) | `add buyer`, `add supplier`                                                                   |                                                                                               |
|          **Clear**           | `clear`                                                                                       |                                                                                               |
|          **Delete**          | `delete-KEY INDEX`                                                                            | `delete-b 1`, `delete-d 2`, `delete-s 3`, `delete-o 1`, `delete-p 2`                          |
|           **Edit**           | `edit-ROLE INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`                                    | `edit-b 1 n/Alex`, `edit-s 3 n/Bobby p//884321`                                               |
|           **Find**           | `find PREFIX/KEYWORD`                                                                         | `find n/James Jake`                                                                           |
|        **Find Buyer**        | `find-b PREFIX/KEYWORD`                                                                       | `find-b n/James Jake`                                                                         |
|      **Find Deliverer**      | `find-d PREFIX/KEYWORD`                                                                       | `find-d n/James Jake`                                                                         |
|      **Find Supplier**       | `find-s PREFIX/KEYWORD`                                                                       | `find-s n/James Jake`                                                                         |
|          **Filter**          | `filter t/INPUT`                                                                              | `filter t/dog t/second-hand`                                                                  |
|      **Filter Orders**       | `filter-o PREFIX/KEYWORD`                                                                     | `filter-o ar/good with children pr/10-100`                                                    |
|       **Filter Pets**        | `filter-p PREFIX/KEYWORD`                                                                     | `filter-p c/white s/capybara`                                                                 |
|           **Help**           | `help`                                                                                        |                                                                                               |
|           **List**           | `list all`, `list buyer`, `list supplier`, <br>`list delivery`, `list order`, `list pet`      |                                                                                               |
|           **Sort**           | `sort LIST_PARAMETER [ATTRIBUTES...]`                                                         | `sort pet price height weight`                                                                |
|          **Check**           | `check LIST_TYPE INDEX`                                                                       | `check buyer 1`                                                                               |

[Go back to [Table of Contents](#table-of-contents)]
