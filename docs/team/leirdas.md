---
layout: page
title: Adriel Soh's Project Portfolio Page
---
## Overview of TutHub
Tuthub is a desktop app for NUS professors who wish to track and choose their next batch of teaching assistants/tutors based on their past performance and records but have little time to spare for tedious administrative work. Tuthub helps profs who can type fast find the best TAs faster than traditional Graphical User Interface (GUI) apps.

## Summary of Contributions
### Code contributed
Around 2.8k lines of code according to the [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=leirdas&breakdown=true)

### Enhancements implemented
- **Enhancement**: Updates to the `Tutor` model including[#107](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/107):
  - Added `TeachingNomination` field and associated test code.
  - Purpose:
    - Allows for relevant and valid tutor information to be stored.
    - To include some form of metric for professors to assess the performance of a tutor.
  - Highlights:
    - Adding an attribute to the `Tutor` object was quite a tedious process since it was linked to many components of the codebase. It required proper tracing of the codebase to figure out which part of the codebase would require cahnges while implementing this enhancement.
- **Modified Feature: `find`[#129](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/129)**
  - Modified the find feature to be able to search by the attribute of the tutor specified.
  - Added test cases.
  - Purpose:
    - Allow for greater filtering of the list of tutors based on different attributes instead of just name.
  - Highlights:
    - Modifying the original `find` feature required tracing through how the entire `find` command worked. e.g. The different components that interact together to run the command.

### Contributions to the UG
- Add documentation for modified `find` feature. ([#139](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/139) and [#165](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/165))

### Contributions to the DG
- Add implementation details for `find` feature. ([#157](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/157))

### Review/mentoring contributions
- PRs reviewed:
  - [#249](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/249)
  - [#119](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/119)
  - [#75](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/75)
- Reported 12 bugs for other teams in [PE-D](https://github.com/leirdas/ped/issues).
- Participated in the module forum:
  - [#72](https://github.com/nus-cs2103-AY2223S1/forum/issues/72)
