---
layout: page
title: User Guide
---

TrackO is a **desktop app built for small business owners to help them manage orders and their inventory, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you prefer and are fast at typing, TrackO can get your order and inventory management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TrackO.jar` from [here](https://github.com/AY2223S1-CS2103T-W15-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TrackO.

1. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`listo`** and pressing Enter will show a list of existing orders.<br>
   Some example commands you can try:

    * **`listo`** : Lists all orders.

    * **`addi`**`n/Keychain q/200 d/This is a metal keychain sp/15.00 cp/10.00` : Adds an inventory item named `Keychain`, which has quantity `200`, description `This is a metal keychain`, selling price `$15.00` and cost price `$10.00` to the inventory list.

    * **`deleteo`**`3` : Deletes the 3rd order shown in the current list.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addo n/NAME`, `NAME` is a parameter which can be used as `addo n/John Doe`.

* Parameters in square brackets `[]` are optional. <br>
  e.g. in `findo KEYWORD [MORE_KEYWORDS]`, only the first `KEYWORD` is compulsory. The rest are optional. 

* Parameters can be in any order.<br>
  e.g. if the command specifies `i/ITEM_NAME q/QUANTITY`, `q/QUANTITY i/ITEM_NAME` is also acceptable.

* All command keywords (e.g. `addo`, `marko`, `editi`, etc.), prefixes (e.g.`p/`, `i/`, etc.) 
and flags(e.g. `-p`, `-D`, etc.) are **case-sensitive**. 

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listi`, `listo`,`clear` and `exit`) will be ignored.<br>
  e.g. if the command specifies `listi 123`, it will be interpreted as `listi`.

</div>

### Adding an inventory item: `addi`

Adds an item to the list of tracked inventory.

Format: `addi i/ITEM_NAME q/QUANTITY d/DESCRIPTION [t/TAG]…​ sp/SELL_PRICE cp/COST_PRICE`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An inventory item's name must be more than 1 character long.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An inventory item can have any number of tags (including 0). A tag should only consist of 1 word. A tag **cannot** have more than 30 characters.
</div>

Examples:
* `addi i/Keychain q/20 d/Silicone keychain with a metal buckle sp/3.50 cp/1`
* `addi i/Chair q/10 d/This is a wooden dining chair t/Furniture sp/50 cp/20`

### Listing all inventory items: `listi`

Lists all the existing items in the store’s inventory.

Format: `listi`

### Finding an inventory item: `findi`

Finds an inventory item whose name fits any of the given keywords.

Format: `findi KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g. `keychain` will match `Keychain`
- The order of the keywords does not matter. e.g. `pants long` will match `long pants`
- Only the name of the item is searched.
- Only full words will be matched. e.g. `key` will not match `Keychain`
- Items matching at least one keyword will be returned (i.e. OR search).
  e.g. `shirt` will return `dress shirt`, `collared shirt`

Examples:
- `findi oil` returns `Olive Oil` and `Vegetable Oil`
- `findi blue` returns `Blue Shirt`, `Blue Pants`

### Deleting an inventory item : `deletei`

Deletes the specified item from the list of tracked inventory.

Format: `deletei INDEX`

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed inventory list.
* The index **must be a positive integer** 1, 2, 3, …​
* Deleting items with active orders is not permitted.

Examples:
* `listi` followed by `deletei 2` deletes the 2nd item in the list of tracked inventory.
* `findi Paper` followed by `deletei 1` deletes the 1st item in the results of the `findi` command.

### Editing an inventory item: `editi`

Edits an existing item in the inventory list.

Format: `editi INDEX [i/ITEM_NAME] [q/QUANTITY] [d/DESCRIPTION] [t/TAG]…​ [sp/SELL_PRICE] [cp/COST_PRICE]`

* Edits the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed inventory list.
* The index **must be a positive integer** 1, 2, 3, …​
* You can remove all the item’s tags by typing `t/` without
  specifying any tags after it.
* Editing items with active orders is not permitted.

Examples:
* `editi 1 i/Table q/200 d/Metal Table t/Fragile`
  Edits the item name, quantity, description and tag of the 1st item to be
  `Table`, `200`, `Metal Table` and `Fragile` respectively.
* `editi 3 t/` removes the tags of the item at index 3.

### Adding an order: `addo`

Creates an order to be added to the list of orders tracked by TrackO.

* The added orders will track the time that it was created.

The first step is to initiate the command with the customer's data.

Format: `addo n/NAME p/PHONE e/EMAIL a/ADDRESS`

Examples:
* `addo n/John Doe p/91234567 e/johndoe@example.com a/48 Westwood Terrace` creates an order for customer `John Doe`, who is contactable at `91234567` and `johndoe@example.com` and wants the order delivered to `48 Westwood Terrace`
* `addo n/Betty White p/92345678 e/bettywhite@example.com a/39 Ocean Drive` creates an order for customer `Betty White`, who is contactable at `92345678` and `bettywhite@example.com` and wants the order delivered to `39 Ocean Drive`

The next step is to add the order items and their order quantities to the created order.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can repeat this step until all desired item data has been added to the created order.
</div>

Format: `i/ITEM_NAME q/QUANTITY`

* You must input an item name that matches an existing item in your inventory list.
* The name matching is case-insensitive, e.g. `i/pEn q/3` and `i/pen q/3` will both add `3` quantities (or units) of the inventory item `Pen` to the created order.
* You must input a quantity of integer value more than 0, e.g. `q/1` or `q/3` but NOT `q/0` or `q/-1`.
* If you input an item name that matches previously entered item, the quantity of the added item will be updated instead, e.g., `i/Box q/3` followed by `i/Box q/4` will only add a total of `4` quantities (or units) of `Box` to the created order

Lastly, to end the command, you can enter `done` to tell TrackO to track the order or `cancel` to completely abort the command.

### Listing all orders : `listo`

Lists all the orders a store has.

Format: `listo`

### Locating orders by keyword: `findo`

Finds an order with item names containing any of the given keywords.

Format: `findo [-d OR -D] [-p OR -P] [i/ITEM_KEYWORD [MORE_ITEM_KEYWORDS]] [a/ADDRESS_KEYWORD [MORE_ADDRESS_KEYWORDS]] [n/NAME_KEYWORD [MORE_NAME_KEYWORDS]]`

* All 4 flags (`-d`, `-D`, `-p`, `-P`) are optional
  * `-d`: search for orders which are delivered
  * `-D`: search for orders which are not delivered
  * `-p`: search for orders which are paid
  * `-P`: search for orders which are not paid
* There are 3 prefixes (`a/`, `n/`, `i/`). At least one of the 3 prefixes must be used in the `findo` command
  * `a/`: searches by address
  * `n/`: searches by name
  * `i/`: searches by order item
* A prefix is only expected once in the command but if specified multiple times, only the last occurrence of 
the parameter will be taken in. Eg. `findo a/Clementi a/Geylang` will search for orders with `Geylang` 
in their address.
<br><br/>
* The search keywords used are case-insensitive. e.g. `keychain` will match `Keychain`
* The order of the keywords does not matter. e.g. `apple keychain` will match `Keychain Apple`
* Only full words will be matched e.g. `keychains` will not match `keychain`
* Orders matching at least one keyword will be returned (i.e. `OR` search).<br>
  e.g. `findo apple keychain` will return `apple painting`, `banana keychain`

Examples:
* `findo n/Alex` returns all orders with the name Alex 
* `findo n/Alex a/Clementi` returns all orders with the name Alex and an address including the word Clementi
* `findo n/Alex Barbara Clyde a/Clementi` returns all orders with the name Alex, Barbara or Clyde and an address including the word Clementi
* `findo n/Alex Barbara a/Clementi Geylang` returns all orders with the name Alex or Barbara and an address including the word Clementi or Geylang
* `findo -d n/Alex` returns all orders with the name Alex which have been delivered
* `findo -d -p n/Alex` returns all orders with Alex which have been paid and delivered

### Sorting orders by time created: `sorto`

Sorts the displayed list of orders by the time at which they were created.

Format: `sorto new` or `sorto old`

* The keywords `new` and `old` are case-insensitive.
* `sorto new` sorts the order list such that the newest orders are at the top.
* `sorto old` sorts the order list such that the oldest orders are at the top.

Examples:
* `listo` followed by `sorto old` sorts all orders such that oldest orders are at the top
* `findo i/Chair` followed by `sorto new` sorts all orders found using `findo i/Chair` such that newest orders are at the 
top


### Deleting an order: `deleteo`

Deletes an order from the list of tracked orders.

Format: `deleteo INDEX`

* Deletes the order at the specified INDEX.
* The index refers to the index number shown in the displayed order list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `listo` followed by `deleteo 2` deletes the 2nd order from the order list.
* `findo i/Paper` followed by `deleteo 1` deletes the 1st item in the results of the `findo i/Paper` command.
* `sorto new` followed by `deleteo 1` deletes the most recently created order

### Editing details of an order: `edito`

Edits an existing order in the order list.

Format: `edito INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/ITEM_NAME q/QUANTITY]`

* Edits the order at the specified `INDEX`.
* This feature is case-insensitive.
* The index refers to the index number shown in the displayed order list.
* The index **must be a positive integer** 1, 2, 3, …​
* Every field is optional, but if you were to include `i/ITEM_NAME`, you must also include 
  `q/QUANTITY`. Both fields need to be present to update an order's list of ordered items.
* You can only edit an order's list of ordered items to consist of items that exists in your inventory. <br> 
  e.g. If your inventory does not have `Apples`, then you cannot edit any of your order's list of ordered items to `Apples`.
* Editing an item that does not exist in your order's list of ordered items, but exists in your inventory 
  will add the item to the order's list of ordered items. <br> e.g. Your inventory has `Apples` and `Bananas`. Right now, the third order's list of ordered items only has `Apples`.
  `edito 3 i/Bananas q/3` will add `3` `Bananas` to the third order's list of ordered items.
* Setting `q/0` to any item in the order's list of ordered items will remove the item from the list. <br> e.g. The fourth order's list of ordered items has
`2` `Apples` and `3` `Bananas`. Inputting `edito 4 i/Bananas q/0` will remove the `Bananas` from the fourth order's list of ordered items, leaving only
the `2` `Apples`.

Note:
* Only orders which are not marked as `paid` or `delivered` can be edited.
* The order's created time cannot be edited.
* An order's paid status and delivery status also cannot be edited through this command. To modify those, 
  see [`marko`](#marking-an-order-as-paid-or-delivered-marko) instead.

Examples:
* `edito 2 n/Peter p/98765432 e/peter@email.com a/123 Apartment Unit, #05-11`
  Edits the name, phone, email, and address of the second order in the list to `Peter`,`98765432`, `peter@email.com`,
  and `123 Apartment Unit, #05-11` respectively.
* When the third order in the list has `Chairs` in quantity `5`, `edito 3 i/chairs q/0` 
  will remove the item from the order list. 
* When the fifth order in the list has `Tables` in quantity `3` in its order list, and you have `Chairs` in your inventory,
`edito 5 i/chairs q/15` will add `15 Chairs` to the order list.

### Marking an order as paid or delivered: `marko`

Marks an existing order in the order list as paid and/or delivered. 

<div markdown="block" class="alert alert-info">
:information_source: **Warning:** `marko` is irreversible. This means that you cannot unmark an order that is marked as 
paid and/or delivered. 
</div>

Format: `marko INDEX [-p] [-d]`

* Marks the order at the specified `INDEX` as paid and/or delivered. 
* The index refers to the index number shown in the currently displayed list. 
* The index **must be a positive integer** 1, 2, 3, …​ 
* Flag `-p` marks the order as paid. 
* Flag `-d` marks the order as delivered. 
* Flags are case-sensitive and specific to the character. 
<br> e.g. `- p` is not a valid flag because there is a space between the `-` and `p`. 
* At least one of `-p` or `-d` must be present in your input. 
* Both `-p` and `-d` may be present in your input to mark an order as both paid and delivered.
* When an order is completed (marked as both `paid` and `delivered`), 
the colour of the particular order's card will be in a darker shade than an uncompleted order. 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can mark an order with insufficient stock as paid (to record pre-payments) but you **cannot** 
mark an order with **insufficient stock** as **delivered**. 
</div>

Examples:
* `marko 1 -p` Marks the order at index `1` in the currently displayed list as `paid`. 
* `marko 1 -d` Marks the order at index `1` in the currently displayed list as `delivered`.
* `marko 1 -p -d` Marks the order at index `1` in the currently displayed list as both `paid` and `delivered`.

### Clearing all existing data in TrackO : `clear`

Clears all data (in both `Order List` and `Inventory List`) from TrackO.

1. Initiate the command to clear all data from TrackO. <br>
    Format: `clear`
2. Confirm the decision to clear all data. <br>
    Format: `confirm` or `cancel`
- `clear` will trigger a message which prompts for user confirmation to clear the data in TrackO.
- Keyword `confirm` : confirms the deletion of all data in TrackO. All data in TrackO will be removed after this input. 
- Keyword `cancel` : aborts the command to clear all data in TrackO. No changes to data in TrackO after this input. 
- `clear` ignores any input after. (e.g. `clear chair` will have the same effect as `clear`)

:bulb: The keywords `confirm` and `done` are case-sensitive. Thus, only the keywords in lower case are accepted.

### Getting help: `help`

Shows a window with a link to the user guide.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Command summary

| Action                       | Format, Examples                                                                                                                                                                                                                                           |
|------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add An Inventory Item**    | `addi n/NAME q/QUANTITY d/DESCRIPTION [t/TAG]…​ sp/SELL_PRICE cp/COST_PRICE` <br> e.g., `addi n/Chair q/20 d/Swedish Wooden chair t/Furniture sp/79.99 cp/50.00`                                                                                           |
| **Delete An Inventory Item** | `deletei INDEX`<br> e.g., `deletei 3`                                                                                                                                                                                                                      |                                                                                                                                                        
| **List All Inventory Items** | `listi`                                                                                                                                                                                                                                                    |
| **Find Inventory Item(s)**   | `findi KEYWORD [MORE_KEYWORDS]` <br/> e.g., `findi blue shirt`                                                                                                                                                                                             |
| **Edit An Inventory Item**   | `editi INDEX [i/ITEM_NAME] [q/QUANTITY] [d/DESCRIPTION] [t/TAG]…​ [sp/SELL_PRICE] [cp/COST_PRICE]`<br> e.g., `editi 2 i/Table q/200 d/Metal Table t/Fragile`                                                                                               |
| **Add An Order**             | `addo n/NAME p/PHONE e/EMAIL a/ADDRESS` <br> e.g., `addo n/John Doe p/91234567 e/johndoe@example.com a/48 Westwood Terrace` <br> then, `i/ITEM_NAME q/QUANTITY` as many times as required <br>e.g. `i/Pillow q/2` <br>followed by `done` or `cancel`       |
| **List All Orders**          | `listo`                                                                                                                                                                                                                                                    |
| **Find Order(s)**            | `findo [-d OR -D] [-p OR -P] [i/ITEM_KEYWORD [MORE_ITEM_KEYWORDS]] [a/ADDRESS_KEYWORD [MORE_ADDRESS_KEYWORDS]] [n/NAME_KEYWORD [MORE_NAME_KEYWORDS]]`, where all flags are optional and only 1 prefix is compulsory <br> e.g. `findo -d i/keychain n/Alex` |
| **Delete An Order**          | `deleteo INDEX` <br> e.g., `deleteo 2`                                                                                                                                                                                                                     |
| **Edit An Order**            | `edito INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/ITEM_NAME q/QUANTITY]` <br> e.g., `edito 2 n/Peter p/98765432 e/peter@email.com a/123 Apartment Unit, #05-11`                                                                                     |
| **Mark An Order**            | `marko INDEX [-p] [-d]` <br> e.g., `marko 2 -d`, `marko 3 -p -d`                                                                                                                                                                                           |
| **Sort Orders**              | `sorto new` or `sorto old`                                                                                                                                                                                                                                 |
| **Clear**                    | `clear`<br/> followed by `confirm` or `cancel` when prompted                                                                                                                                                                                               |
| **Getting Help**             | `help`                                                                                                                                                                                                                                                     |
| **Exit**                     | `exit`                                                                                                                                                                                                                                                     |
