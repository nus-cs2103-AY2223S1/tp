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

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`listo`** and pressing Enter will show a lit of existing orders.<br>
   Some example commands you can try:

    * **`listo`** : Lists all orders.

    * **`addi`**`n/Keychain` : Adds an inventory item named `Keychain` to the inventory list.

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

* Extraneous parameters for commands that do not take in parameters (such as `list` and `exit`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Adding an inventory item: `addi`

Adds an item to the list of tracked inventory.

Format: `addi n/ITEM_NAME [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An inventory item can have any number of tags (including 0)
</div>

Examples:
* `addi n/Keychain`
* `addi n/Chair t/Furniture`

### Deleting an inventory item : `deletei`

Deletes the specified item from the list of tracked inventory.

Format: `deletei INDEX`

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed inventory list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listi` followed by `deletei 2` deletes the 2nd item in the list of tracked inventory.

### List all inventory items: `listi`

Shows all the existing items in the store’s inventory.

Format: `listi`

### Find an inventory item: `findi`
Finds an inventory item whose name fits any of the given keywords.

Format: `findi KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive.
- `keychain` will match `Keychain`
- The order of the keywords does not matter. <br> e.g. `pants long` will match `long pants`
- Only the name of the item is searched.
- Only full words will be matched. <br> e.g. `key` will not match `Keychain`
- Items matching at least one keyword will be returned (i.e. OR search). <br>
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
  the item at index 1
* `tagi 3 t/` removes the tags of the item at index 3

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Command summary

Action | Format, Examples
--------|------------------
**Add Inventory** | `addi n/NAME [t/TAG]…​` <br> e.g., `addi n/Chair t/Furniture`
**Delete Inventory** | `deletei INDEX`<br> e.g., `deletei 3`
**Tagi** | `tagi INDEX [t/TAG]…​` <br> e.g, `tagi 1 t/Perishable t/Premium`
**listi** | `listi`  
**findi** | `findi KEYWORD [MORE_KEYWORDS]` <br/> e.g., `find blue shirt`
**Exit** | `exit`
