---
layout: page
title: Timothy Koei's Project Portfolio Page
---
## Overview of TutHub
Tuthub is a desktop app for NUS professors who wish to track and choose their next batch of teaching assistants/tutors based on their past performance and records but have little time to spare for tedious administrative work. Tuthub helps profs who can type fast find the best TAs faster than traditional Graphical User Interface (GUI) apps.

## Summary of Contributions
### Code Contributed
About 2.4k lines contributed.

[RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=t1mzzz&breakdown=true)

### Project Management
- Managed Tuthub's Github releases `v1.2`, `v1.2.1`, and `v1.3`. This can be viewed [here](https://github.com/AY2223S1-CS2103T-T15-3/tp/releases).

### Enhancements implemented

**New Feature: `sort`**
- Added the feature to sort the tutor list based on rating and teaching nominations ([#127](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/127)).
- Fixed bugs regarding the `sort` command ([#201](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/201)).

**New Feature: `mail`**
- Added the feature to mail tutors ([#162](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/162)).

**Enhancements**
- Modified and fixed bugs in `Comment`, `CommentCommand`, and `DeleteCommentCommand` ([#185](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/185)).
  - Fixed bugs found in:
    - [#178](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/178)
    - [#182](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/182)
    - [#183](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/183)
  - Added test cases for these logic classes.
- Updated the `Tutor` class (was `Person`) to store additional fields `StudentId` ([#76](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/76)) and `Rating` ([#114](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/114)). 
  - Implemented the fields into the add command and `Tutor` class.
  - Added test cases for `StudentId` and `Rating` models.

### Contributions to the UG
- Added documentation for the features `sort` ([#127](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/127)) and `mail` ([#162](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/162)).
- Added documentation to additional model details `StudentId` and `Rating` ([#259](https://github.com/AY2223S1-CS2103T-T15-3/tp/issues/259))

### Contributions to the DG
- Added implementation details for the `sort` command ([#156](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/156)).
- Provided the base for user stories, use cases, glossary, and NFRs ([#59](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/59))

### Reviewing contributions
- PRs reviewed:
  - [#52](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/52)
  - [#89](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/89)
  - [#112](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/112)
- Reported 14 bugs for other teams in [PE-D](https://github.com/t1mzzz/ped/issues).
- Participated in the module forum
  - [#374](https://github.com/nus-cs2103-AY2223S1/forum/issues/374)
