---
layout: page
title: Listing all residents
---

### Listing all residents : `list`

Shows a list of all residents in the RC4HDB database. Use the specifiers `/i` for include and `/e` for exclude, followed by the field names of the columns to show or hide. All field names entered are case-insensitive.

Format:

`list` to list all residents in the database with all fields visible

`list /i [FIELD_1] [FIELD_2]...` to list all residents in the database with only `[FIELD_1] [FIELD_2]...` visible.

`list /e [FIELD_1] [FIELD_2]...` to list all residents in the database with all fields visible except `[FIELD_1] [FIELD_2]...`