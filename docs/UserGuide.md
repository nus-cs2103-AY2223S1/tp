## Features

### Listing all orders : `listo`

Shows a list of all orders in the store has. 

Format: `listo`

### Locating orders by keyword: `findo`

Find order with item names containing any of the given keywords.

Format: `findo KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `keychain` will match `Keychain`
* The order of the keywords does not matter. e.g. `apple keychain` will match `Keychain Apple`
* Only the name is searched.
* Only full words will be matched e.g. `keychains` will not match `keychain`
* Orders matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `apple keychain` will return `apple painting`, `banana keychain`

Examples:
* `findo keychain` returns `banana keychain` and `keychain`
* `findo apple keychain` returns `apple painting`, `banana keychain`<br>

## Command summary

Action | Format, Examples
--------|------------------
**List Orders**| `listo`
**Find an Order** | `findo KEYWORD [MORE_KEYWORDS]`
