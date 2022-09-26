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

## Command summary

| Action     | Format, Examples                                             |
|------------|--------------------------------------------------------------|
| **listi** | `listi`                                                      |
| **findi** | `findi KEYWORD [MORE_KEYWORDS]` <br/> e.g., `find blue shirt` |
