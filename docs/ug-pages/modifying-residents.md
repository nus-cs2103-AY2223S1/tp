---
layout: page
title: Modifying Residents
---

In order to maintain the database, we have provided crucial commands such as `add`, `edit`, `delete` and `clear`.

### Adding a resident : `add`

Adds a resident to the database. The format for resident fields can be found [here](#format-for-resident-fields).

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL r/FLOOR-UNIT g/GENDER h/HOUSE m/MATRIC_NUMBER [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A resident can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnDoe@gmail.com r/5-1 g/M h/D m/A9876543B` adds a resident named John Doe, with relevant personal and student information.
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com r/2-3 p/1234567 m/A3456789B g/F h/A` adds a resident named Betsy Crowe, with relevant personal and student information.

---

### Editing an existing resident : `edit`

Edits the data of an existing resident in the RC4HDB database.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/FLOOR-UNIT] [g/GENDER] [h/HOUSE] [m/MATRIC_NUMBER] [t/TAG]…​`

* Edits the resident at the specified `INDEX`. The index refers to the index number shown in the displayed residents list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the resident will be removed i.e adding of tags is not cumulative.
* You can remove all the resident’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 r/5-8` Edits the phone number, room number of the 1st resident to be `91234567`, and `5-8` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd resident to be `Betsy Crower` and clears all existing tags.

---

### Deleting a resident : `delete`

Deletes the specified resident from the RC4HDB database.

Format: `delete INDEX`

* Deletes the resident at the specified `INDEX`.
* The index refers to the index number shown in the displayed resident list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd resident in the database.
* `find Betsy` followed by `delete 1` deletes the 1st resident in the results of the `find` command.

---

### Clearing all entries : `clear`

Clears all entries from the database.

Format: `clear`

---

### Format for resident fields

`n/NAME`
* Must be a string
* Spaces are allowed

`p/PHONE_NUMBER`
* Must be an **8**-digit non-negative integer

`e/EMAIL`
* Can be any string, valid or invalid email

`r/FLOOR-UNIT`
* The floor number and unit number must be separated by a hyphen
* Both floor and unit number must be a non-negative integer
* e.g. `5-8`

`g/GENDER`
* `M` or `F`

`h/HOUSE`
* Represents the RC4 house that the resident is allocated to
* Must be either `D`, `U`, `L`, `A`, `N`
* `D` stands for **Draco**, `U` for **Ursa**, `L` for **Leo**, `A` for **Aquila**, `N` for **Noctua**

`m/MATRIC_NUMBER`
* Must be an uppercase `A`, followed by a **7**-digit non-negative integer and an uppercase alphabet.
* e.g. `A0123456A`

`t/TAG`
* Represents any other key that could be used to identify a resident
* Must be a string. No restrictions on formatting
* Optional. A resident can have any number of tags, including 0
* When editing tags, the existing tags of the resident will be removed i.e adding of tags is not cumulative.
* You can remove all the resident’s tags by typing `t/` without specifying any tags after it.

_More details coming soon ..._

---

