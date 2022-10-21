---
layout: page
title: Editing an existing resident
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