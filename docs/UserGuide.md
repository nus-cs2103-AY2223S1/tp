## Features

### Listing all orders : `listo`

Shows a list of all orders in the store has. 

Format: `listo`

### Locating persons by name: `findo`

Find order with item names containing any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `keychain` will match `Kans`
* The order of the keywords does not matter. e.g. `apple keychain` will match `Keychain Apple`
* Only the name is searched.
* Only full words will be matched e.g. `keychains` will not match `keychain`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `apple keychain` will return `apple painting`, `banana keychain`

Examples:
* `find keychain` returns `banana keychain` and `keychain`
* `find apple keychain` returns `apple painting`, `banana keychain`<br>

## Command summary

Action | Format, Examples
--------|------------------
**listo**| `listo`
**findo** | `find KEYWORD [MORE_KEYWORDS]`
