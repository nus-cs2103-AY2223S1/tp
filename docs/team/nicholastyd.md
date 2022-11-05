---
layout: page
title: Nicholas Tan's Project Portfolio Page
---

## Project: idENTify
### Overview
idENTify - ENT doctors treat patients who often have chronic conditions and our product will make it easier for the staff to keep track of these patients, such as their appointment dates, the severity of their condition, their diagnosis, and allow admin staff to filter different types of patients

### Summary of Contributions

Given below are my contributions to the project.


Code contributed:
- [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nicholastyd&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


Enhancements implemented:
- `mark` command: Allows users to mark appointments as completed.
- `unmark` command: Allows users to unmark appointments as incomplete.
- Improved `list` command to list both patients and appointments simutaneously.
- Improved `find` command:
    - Instead of being only able to search using the patient's name, the user is now able to find results using a patient's phones, email, address, tags and/or an appointment's reason, date or tags.
    - It is possible to find results using several fields simutaneously. (E.g It is possible to find patients with a certain name and a certain tag with a single command input.)
    - Only returns relevant search results. For example, if finding by patient name and appointment reason simutaneously:
        - Only patients that matches the inputted name and has at least 1 appointment that matches the inputted appointment reason will displayed.
        - Only appointments that matches the inputted reason and belongs to a patient that satisfies the critera above will be displayed.


Contributions to the UG:
- Added sections for `mark`, `unmark` and `find` commands.


Contributions to the DG:
- Added UML diagrams for `mark`, `unmark`, and `find` commands as well as the explaination text.


Contributions to team-based tasks:
- Updated v1.3 .jar release file

Review/mentoring contributions:
- [PR review 1](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/138)
- [PR review 2](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/81)
- [PR review 3](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/76)
- [PR review 4](https://github.com/AY2223S1-CS2103T-T17-4/tp/pull/68)


Contributions beyond the project team:
- [Bugs reported during PE-D](https://github.com/NicholasTYD/ped/issues)
