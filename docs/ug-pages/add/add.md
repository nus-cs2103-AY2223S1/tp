---
layout: page
title: Adding a resident
---

### Adding a resident : `add`

Adds a resident to the database. The format for resident fields can be found [here](#format-for-resident-fields).

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL r/FLOOR-UNIT g/GENDER h/HOUSE m/MATRIC_NUMBER [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A resident can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnDoe@gmail.com r/5-1 g/M h/D m/A9876543B` adds a resident named John Doe, with relevant personal and student information.
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com r/2-3 p/1234567 m/A3456789B g/F h/A` adds a resident named Betsy Crowe, with relevant personal and student information.

### Related sections:
