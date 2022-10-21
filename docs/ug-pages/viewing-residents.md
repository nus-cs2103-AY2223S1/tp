---
layout: page
title: Viewing Residents
---

Oftentimes, you may find yourself overloaded with information. These commands can help to include, and exclude
fields from being seen, find specific residents, and search residents whose fields match a specific keyword.

### Listing all residents : `list`

Shows a list of all residents in the RC4HDB database. Use the specifiers `/i` for include and `/e` for exclude, followed by the field names of the columns to show or hide. All field names entered are case-insensitive.

Format:

`list` to list all residents in the database with all fields visible

`list /i [FIELD_1] [FIELD_2]...` to list all residents in the database with only `[FIELD_1] [FIELD_2]...` visible.

`list /e [FIELD_1] [FIELD_2]...` to list all residents in the database with all fields visible except `[FIELD_1] [FIELD_2]...`

---

### Locating residents by name : `find`

Finds residents whose names contain any of the given keywords.

Format: `find NAME [ADDITIONAL_NAMES]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Full and partial words will be matched e.g. `Han` will match `Hans`
* Residents matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`
* `find char li` returns `Charlotte Oliveiro`, `David Li`<br>
  <!--- ![result for 'find alex david'](images/findAlexDavidResult.png) --->

---

### Filtering residents by field : `filter`

Shows a list of residents whose fields match the input keywords.

Format: `filter KEY/VALUE [ADDITIONAL_KEYS/ADDITIONAL_VALUES]`
* The fields have to be the same (no substrings allowed) for the resident to be filtered.
* Commands with multiple fields require the resident to match all the fields to be filtered.
* Valid keys are those included [here](#format-for-resident-fields), and any additional tags.

Examples:
* `filter h/D g/M` returns residents who are in Draco house, **and** are Male.
* `filter g/M` returns residents who are male.

---
