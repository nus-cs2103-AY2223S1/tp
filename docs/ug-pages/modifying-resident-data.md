---
layout: page
title: Modifying Residents
---

#### ← [Back to Menu](../UserGuide.md)

In order to maintain the database, we have provided several basic commands such as `add`, `edit`, `delete` and `clear`
to help you manipulate residents within **RC4HDB**.

---

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Before proceeding to learn more about resident commands it would be good to have a quick read about the **resident fields**, which will
be used in the commands.
</div>

The resident fields can be found [here](#format-for-resident-fields).

[↑ Back to Top](#back-to-menu)

---

### Adding a resident : `add`

Adds a resident into **RC4HDB**.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL r/FLOOR-UNIT g/GENDER h/HOUSE m/MATRIC_NUMBER [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A resident can have any number of tags (including 0).
</div>

Examples:
* `add n/John Doe p/98765432 e/johnDoe@gmail.com r/5-1 g/M h/D m/A9876543B` adds a resident named John Doe, with
  relevant personal and student information.


* `add n/Betsy Crowe t/friend e/betsycrowe@example.com r/2-3 p/1234567 m/A3456789B g/F h/A` adds a resident named
  Betsy Crowe, with relevant personal and student information.

[↑ Back to Top](#back-to-menu)

---

### Editing an existing resident : `edit`

Edits the data of an existing resident in **RC4HDB**.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/FLOOR-UNIT] [g/GENDER] [h/HOUSE] [m/MATRIC_NUMBER] [t/TAG]…​`

* Edits the resident at the specified `INDEX`.
* The index refers to the index number shown in the displayed residents list.
* The index **must be a positive integer:** 1, 2, 3, …​
* At least one of the optional parameters must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the resident will be removed i.e adding of tags is not cumulative.
* You can remove all the resident’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 r/5-8` Edits the phone number, room number of the 1st resident to be `91234567`, and `5-8`
   respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd resident to be `Betsy Crower` and clears all existing tags.

[↑ Back to Top](#back-to-menu)

---

### Deleting a resident : `delete`

Deletes the specified resident from **RC4HDB**.

Format: `delete INDEX`

* Deletes the resident at the specified `INDEX`.
* The index refers to the index number shown in the displayed resident list.
* The index **must be a positive integer:** 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The delete command can be used in conjunction with other commands such as list and find, to delete residents in different types of filtered lists.
</div>

Examples:
* `list` followed by `delete 2` deletes the 2nd resident in the database.


* `find Betsy` followed by `delete 1` deletes the 1st resident in the results of the `find` command.

[↑ Back to Top](#back-to-menu)

---

### Deleting multiple residents : `remove`

Deletes the specified resident from the RC4HDB database.

Format: `remove [/SPECIFIER] KEY/VALUE [ADDITIONAL_KEYS/ADDITIONAL_VALUES]`

* A specifier is required in order for the command to work. If not it is an invalid command format
* Currently, only two specifiers are supported:
    * `/all` returns a resident if it fulfills **all** of the specified keywords.
    * `/any` returns a resident if it fulfills **any** of the specified keywords.
* Repeated keys are not permitted for both specifiers, e.g. `remove /all h/D h/A` will not work.
* However, tags can be repeated in the command e.g. `remove /all t/exchange t/fresher`
* Valid keys are those included [here](#format-for-resident-fields), and any additional tags.

Examples:
* `remove /all h/D g/M` deletes residents who are in Draco house, **and** are Male.
* `remove /any h/D h/A` deletes residents belonging to either `Draco` **or** `Aquila` house.
* `remove g/M` deletes residents who are male.

[Back to Top](#back-to-menu)

### Clearing all entries : `clear`

---

Clears all entries from **RC4HDB**.

Format: `clear`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Deleted data can not be retrieved. Do use this command cautiously!
</div>

[↑ Back to Top](#back-to-menu)

---

### Format for resident fields

`n/NAME`
* Whitespaces are allowed *i.e. `n/Michael B. Jordan` is allowed*

`p/PHONE_NUMBER`
* Must be an **8**-digit non-negative integer

`e/EMAIL`
* Must follow the formatting for all standard emails *i.e. `e/Example@email.com` is accepted*
* Can be both valid or invalid emails

`r/FLOOR-UNIT`
* The floor number and unit number must be separated by a hyphen
* Both floor and unit number must be a non-negative integer *i.e. `5-8` is valid and `-3-8` is invalid*

`g/GENDER`
* `M` or `F`

`h/HOUSE`
* Represents the RC4 house that the resident is allocated to
* Must be either `D`, `U`, `L`, `A`, `N`
* `D` stands for **Draco**, `U` for **Ursa**, `L` for **Leo**, `A` for **Aquila**, `N` for **Noctua**

`m/MATRIC_NUMBER`
* Must be an uppercase `A`, followed by a **7**-digit non-negative integer and an uppercase alphabet. *i.e. `A0123456A`*

`t/TAG`
* Represents any other key that could be used to identify a resident
* Must be a string. No restrictions on formatting
* Optional. A resident can have any number of tags, including 0
* When editing tags, the existing tags of the resident will be removed i.e adding of tags is not cumulative.
* You can remove all the resident’s tags by typing `t/` without specifying any tags after it.

[↑ Back to Top](#back-to-menu)

---

