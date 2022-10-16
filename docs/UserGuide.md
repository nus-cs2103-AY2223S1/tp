---
layout: page
title: User Guide
---
                                                                                                                                                               |
TrackO is a **desktop app built for small business owners to help them manage orders and their inventory, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you prefer and are fast at typing, TrackO can get your order and inventory management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TrackO.jar` from [here](https://github.com/AY2223S1-CS2103T-W15-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TrackO.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`listo`** and pressing Enter will show a list of existing orders.<br>
   Some example commands you can try:

    * **`listo`** : Lists all orders.

    * **`addi`**`n/Keychain q/200 d/This is a metal keychain` : Adds an inventory item named `Keychain`, which has quantity `200` and description `This is a metal keychain` to the inventory list.

    * **`deleteo`**`3` : Deletes the 3rd order shown in the current list.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listi`, `listo` and `exit`) will be ignored.<br>
  e.g. if the command specifies `listi 123`, it will be interpreted as `list`.

</div>

### Adding an order: `addo`

Adds an order to the list of tracked orders.

Format: `addo i/ITEM_NAME q/ORDER_QUANTITY cn/CUSTOMER_NAME ca/CUSTOMER_ADDRESS ce/CUSTOMER_EMAIL cc/CUSTOMER_CONTACT`

* Adds an order to be tracked in the application.
* The added orders will track the time that it was created.

Examples:
* `addo i/Fountain Pen q/3 cn/John Doe ca/48 Westwood Terrace ce/johndoe@example.com cc/91234567`
* `addo i/White Socks q/2 cn/Betty White ca/39 Ocean Drive ce/bettywhite@example.com cc/92345678`

### Deleting an order: `deleteo`

Deletes an order from the list of tracked orders.

Format: `deleteo INDEX`

* Deletes the order at the specified INDEX.
* The index refers to the index number shown in the displayed order list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `listo` followed by `deleteo 2` deletes the 2nd order from the order list.

### Listing all orders : `listo`

Lists all the orders a store has.

Format: `listo`

### Locating orders by keyword: `findo`

Finds order with item names containing any of the given keywords.

Format: `findo KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `keychain` will match `Keychain`
* The order of the keywords does not matter. e.g. `apple keychain` will match `Keychain Apple`
* Only the name is searched.
* Only full words will be matched e.g. `keychains` will not match `keychain`
* Orders matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `apple keychain` will return `apple painting`, `banana keychain`

Examples:
* `findo keychain` returns `banana keychain` and `keychain`
* `findo apple keychain` returns `apple painting`, `banana keychain`<br>

### Adding an inventory item: `addi`

Adds an item to the list of tracked inventory.

Format: `addi n/ITEM_NAME q/QUANTITY d/DESCRIPTION [t/TAG]…​ cp/COST_PRICE sp/SELL_PRICE`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An inventory item can have any number of tags (including 0)
</div>

Examples:
* `addi n/Keychain q/20 d/Silicone keychain with a metal buckle sp/3.50 cp/1`
* `addi n/Chair q/10 d/This is a wooden dining chair t/Furniture sp/50 cp/20`

### Deleting an inventory item : `deletei`

Deletes the specified item from the list of tracked inventory.

Format: `deletei INDEX`

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed inventory list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listi` followed by `deletei 2` deletes the 2nd item in the list of tracked inventory.

### List all inventory items: `listi`

Lists all the existing items in the store’s inventory.

Format: `listi`

### Find an inventory item: `findi`

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

### Tagging an inventory item: `tagi`

Adds tag(s) to an inventory item.

Format: `tagi INDEX [t/TAG]…​`

* Adds tag(s) to the inventory item at the specified `INDEX`.
  The index refers to the index number shown in the displayed inventory list. The index **must be a positive integer** 1, 2, 3, …​.
* You can remove all the item’s tags by typing `t/` without
  specifying any tags after it.

Examples:
* `tagi 1 t/Perishable t/Premium` adds the tags `Perishable` and `Premium` to
  the item at index 1.
* `tagi 3 t/` removes the tags of the item at index 3.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Command summary

| Action                       | Format, Examples                                                                                                                                                                                                          |
|------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add An Order**             | `addo i/ITEM_NAME q/ORDER_QUANTITY cn/CUSTOMER_NAME ca/CUSTOMER_ADDRESS ce/CUSTOMER_EMAIL cc/CUSTOMER_CONTACT` <br> e.g., `addo i/Fountain Pen q/3 cn/John Doe ca/48 Westwood Terrace ce/johndoe@example.com cc/91234567` |
| **Delete An Order**          | `deleteo INDEX` <br> e.g., `deleteo 2`                                                                                                                                                                                    |
| **List All Orders**          | `listo`                                                                                                                                                                                                                   |
| **Find Order(s)**            | `findo KEYWORD [MORE_KEYWORDS]`                                                                                                                                                                                           |
| **Add An Inventory Item**    | `addi n/NAME q/QUANTITY d/DESCRIPTION [t/TAG]…​` <br> e.g., `addi n/Chair q/20 d/Swedish Wooden chair t/Furniture sp/SELL_PRICE cp/COST_PRICE`                                                                            |
| **Delete An Inventory Item** | `deletei INDEX`<br> e.g., `deletei 3`                                                                                                                                                                                     |
| **Tag An Inventory Item**    | `tagi INDEX [t/TAG]…​` <br> e.g, `tagi 1 t/Perishable t/Premium`                                                                                                                                                          |
| **List All Inventory Items** | `listi`                                                                                                                                                                                                                   |
| **Find Inventory Item(s)**   | `findi KEYWORD [MORE_KEYWORDS]` <br/> e.g., `findi blue shirt`                                                                                                                                                            |
| **Exit**                     | `exit`                                                                                                                                                                                                                    |
