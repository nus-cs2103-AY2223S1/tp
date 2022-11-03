---
layout: page
title: Jia Han's Project Portfolio Page
---

## Overview of TutHub
Tuthub is a desktop app for NUS professors who wish to track and choose their next batch of teaching assistants/tutors based on their past performance and records but have little time to spare for tedious administrative work. Tuthub helps profs who can type fast find the best TAs faster than traditional Graphical User Interface (GUI) apps.

## Summary of Contributions

### Code contributed
Around 900 lines of code according to the [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jia-han&breakdown=true)

### Enhancements implemented

- **Enhancement**: Updates to the `Tutor` model including:
  - Added `Module` and `Year` fields and associated test code.
  - Changed `Module` to accept 1 or more modules.
  - Changed regex for `Name`, `Email`, `Module` and `Year` and added/updated test code accordingly.
  - Updated `Parser`, `AddCommand` and `EditCommand` classes to fit updated `Tutor` model.
  - Updated storage utils and test data to fit updated `Tutor` model.
  - Purpose:
    - Allows for relevant and valid tutor information to be stored.
  - Highlights:
    - `Email` automatically appends NUS email domain if not given.


### Contributions to the UG

- Changed sample email in UG to fit updated email format.
- Added explanation on restrictions for each field of `Tutor`.

### Contributions to the DG

- Added implementation details for `add` command.
- Refactored UML diagrams to remove traces of AddressBook.

### Contributions to team-based tasks

- Triaged PED bugs.

### Review/mentoring contributions
- PRs reviewed: [#51](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/51), [#132](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/132), [#162](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/162), [#256](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/256), [#259](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/259), [#260](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/260)
- Reported 9 bugs for another team in [PED](https://github.com/jia-han/ped)
