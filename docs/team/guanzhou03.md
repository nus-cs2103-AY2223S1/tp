---
layout: page
title: Chen Guanzhou's Project Portfolio Page
---

## Project: idENTify
### Overview
idENTify - ENT doctors treat patients who often have chronic conditions and our product will make it easier for the staff to keep track of these patients, such as their appointment dates, the severity of their condition, their diagnosis, and allow admin staff to filter different types of patients

### Summary of Contributions
Given below are my contributions to the project.

Code contributed: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=guanzhou03&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
Enhancements implemented:
* `delete` command enhancement: Allows deletion of patients in a range, and not just a single deletion at an index.
* `Singleton` pattern: Made use of singleton pattern to keep track of the current predicate shown in the UI (there should only be 1 "global" predicate)
* `cancel` command: Allows removal of a person's appointment from the appointment list.
* `hide patients` command: Allows the model to hide patients based on conditions specified e.g hide by patient names or tags, makes use of the singleton.
* `hide appts` command: Allows the model to hide appointments based on conditions specified e.g hide by appointment tags, status or reason, makes use of the singleton.
* `unhide patients` command: Allows the mode to unhide previously hidden patients based on conditions specified e.g unhide by tags.
* `unhide appts` command: Allows the model to unhide previously hidden appointments based on conditions specified e.g unhide by marked status.
The global predicate to show/hide which appointments and patients was difficult to implement, because hide/unhide has to work with other commands such as find and edit,
and a global predicate for the model must be maintained at all times. e.g hide/find/exit/unhide commands work on the current shown list instead of the full list of appointments and patients, and so the predicate has to be constantly combined, leading to many bugs.

Contributions to the UG:
* Added `delete`, `cancel`, `hide patients`, `hide appts`, `unhide patients`, `unhide appts` feature description and examples.
* Updated screenshot for `help` command.
* Updated command summary.

Contributions to the DG:
Added explanations and UML diagrams for `cancel` command.

Contributions to team-based tasks:
* Created and set up the organisation and repository in the initial stages of the project.
* Released v1.2 and v1.3 jar files

Review/mentoring contributions:
- [PR review 1](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/81)
- [PR review 2](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/112)
- [PR review 3](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/141)
- [PR review 4](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/142)

Contributions beyond the project team:
- [Bugs reported during PE-D](https://github.com/guanzhou03/ped/issues)
