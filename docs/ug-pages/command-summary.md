---
layout: page
title: Command summary
---
#### [← Back to Menu](../UserGuide.md)

## Command summary

We understand that you might be overwhelmed by the number of commands available to you. Though we have provided a
summary table of contents in the Help Window in **RC4HDB**, that table contains only our most basic and commonly used
commands. The tables below contain the full summary of our commands and how they can be used.


### General Commands

| Action   | Format, Examples |
|----------|------------------|
| **Help** | `help`           |
| **Exit** | `exit`           |

[↑ Back to Top](#back-to-menu)

---

### Modifying Resident Commands

| Action     | Format, Examples                                                                                                                                                                  |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL r/FLOOR-UNIT g/GENDER h/HOUSE m/MATRIC_NUMBER [t/TAG]…​` <br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com r/2-1 g/M h/D m/A9876543B` |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/FLOOR-UNIT] [g/GENDER] [h/HOUSE] [m/MATRIC_NUMBER] [t/TAG]…​`<br> e.g.`edit 2 n/James Lee e/jameslee@example.com`              |
| **Delete** | `delete INDEX`<br> e.g. `delete 3`                                                                                                                                                |
| **Clear**  | `clear`                                                                                                                                                                           |

[↑ Back to Top](#back-to-menu)

---

### Viewing Resident Commands

| Action     | Format, Examples                                                                                           |
|------------|------------------------------------------------------------------------------------------------------------|
| **List**   | `list`, `list /i LETTER [MORE_LETTERS]...`, or `list /e LETTER [MORE_LETTERS]...`<br/>e.g. `list /i n p e` |
| **Show**   | `showonly LETTER [MORE_LETTERS]`<br/>e.g. `showonly n p e t`                                               |
| **Hide**   | `hideonly LETTER [MORE_LETTERS]`<br/>e.g. `hideonly i r g h m`                                             |
| **Reset**  | `reset`                                                                                                    |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find James Jake`                                                  |
| **Filter** | `filter [/specifier] KEY:VALUE [ADDITIONAL_KEYS:ADDITIONAL_VALUES]` <br> e.g. `filter /all h/D g/M`        |

[↑ Back to Top](#back-to-menu)

---

### File Management Commands

| Action          | Format, Examples                                                |
|-----------------|-----------------------------------------------------------------|
| **Create File** | `file create FOLDER_NAME` <br> e.g. `file create rc4_data_2022` |
| **Delete File** | `file delete FOLDER_NAME` <br> e.g. `file delete rc4_data_2022` |
| **Switch File** | `file switch FOLDER_NAME` <br> e.g. `file switch rc4_data_2022` |
| **Import File** | `import FOLDER_NAME` <br> e.g. `import students`                |

[↑ Back to Top](#back-to-menu)

---

### Venue Booking Commands

| Action           | Format, Examples                                                                                          |
|------------------|-----------------------------------------------------------------------------------------------------------|
| **Add Venue**    | `venue add VENUE_NAME` <br> e.g. `venue add Meeting Room 3`                                               |
| **Delete Venue** | `venue delete VENUE_NAME` <br> e.g. `venue delete Hall`                                                   |
| **View Venue**   | `venue view VENUE_NAME` <br> e.g. `venue view Hall`                                                       |
| **Book Venue**   | `venue book INDEX v/VENUE_NAME tp/TIME_PERIOD d/DAY` <br> e.g. `venue book 2 v/Meeting Room tp/8-9 d/TUE` |
| **Unbook Venue** | `venue unbook v/VENUE_NAME tp/TIME_PERIOD d/DAY` <br> e.g. `venue unbook v/Meeting Room tp/8-9 d/TUE`     |

[↑ Back to Top](#back-to-menu)

---
