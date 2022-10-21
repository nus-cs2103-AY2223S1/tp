---
layout: page
title: Deleting a resident
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