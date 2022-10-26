---
layout: page
title: Command summary
---

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL r/FLOOR-UNIT g/GENDER h/HOUSE m/MATRIC_NUMBER [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com r/2-1 g/M h/D m/A9876543B`
**List** | `list`, `list /i LETTER [MORE_LETTERS]...`, or `list /e LETTER [MORE_LETTERS]...` e.g., `list /i n p e`
**Show only** | `showonly LETTER [MORE_LETTERS]` e.g., `showonly n p e t`
**Hide only** | `hideonly LETTER [MORE_LETTERS]` e.g., `hideonly i r g h m`
**Reset** | `reset`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/FLOOR-UNIT] [g/GENDER] [h/HOUSE] [m/MATRIC_NUMBER] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter [/specifier] KEY:VALUE [ADDITIONAL_KEYS:ADDITIONAL_VALUES]` <br> e.g., `filter /all h/D g/M`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Clear** | `clear`
**Exit** | `exit`
**Create file** | `file create FILE_NAME` <br> e.g., `file create rc4_data_2022`
**Delete file** | `file delete FILE_NAME` <br> e.g., `file delete rc4_data_2022`
**Switch file** | `file switch FILE_NAME` <br> e.g., `file switch rc4_data_2022`
**Import** | `import FILE_NAME` <br> e.g., `import students.csv`
**Export** | `export FILE_NAME` <br> e.g., `export students.csv`
