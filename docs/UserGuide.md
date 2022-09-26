## Features

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
