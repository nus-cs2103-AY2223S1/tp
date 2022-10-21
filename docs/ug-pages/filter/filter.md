---
layout: page
title: Filtering residents by field
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