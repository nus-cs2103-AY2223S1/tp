## Features

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

--------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Tagi** | `tagi INDEX [t/TAG]…​` <br> e.g, `tagi 1 t/Perishable t/Premium`
**Exit** | `exit`
**listi** | `listi`  
**findi** | `findi KEYWORD [MORE_KEYWORDS]` <br/> e.g., `find blue shirt` 
