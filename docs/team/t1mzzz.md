---
layout: page
title: Timothy Koei's Project Portfolio Page
---
## Overview of Tuthub
Tuthub is a desktop app for NUS professors who wish to track and choose their next batch of teaching assistants/tutors based on their past performance and records but have little time to spare for tedious administrative work. Tuthub helps profs who can type fast find the best TAs faster than traditional Graphical User Interface (GUI) apps.

## Summary of Contributions
### Code Contributed
[RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=t1mzzz&breakdown=true)

### Project Management
- Set up the Github team [org](https://github.com/AY2223S1-CS2103T-T15-3/), team [repo](https://github.com/AY2223S1-CS2103T-T15-3/tp/).
- Set up Tuthub's external tools, Codecov and Gradle.
- Managed and delegated work and issues in the issue tracker (authored 96 issues).
- Managed Tuthub's Github releases `v1.2`, `v1.2.1`, and `v1.3`. This can be viewed [here](https://github.com/AY2223S1-CS2103T-T15-3/tp/releases).

### Enhancements implemented

**New Feature: `sort`**, added the feature to sort the tutor list based on rating and teaching nominations ([#127](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/127)).
- What it does:
  - Allows the user to sort the currently displayed tutor list. The user can choose to sort based on the `Rating` and `TeachingNomination` of the tutor in ascending or descending order. Any list, whether it be a filtered list (after `find`) or the original list can be sorted.
- Justification:
  - This feature serves as one of Tuthub's main features. To find the best and most suitable TAs, this feature enables profs to search based on the available quantitative measures.
- Highlights:
  - This enhancement involves the ui, logic, and model of Tuthub. It heavily relies on the Java class `javafx.collections.transformation.SortedList` which allows easy switching of the Comparator. 
- Fixed bugs regarding the `sort` command ([#201](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/201)).

**New Feature: `mail`**, added the feature to mail tutors ([#162](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/162)).
- What it does:
  - Allows the user to contact the targeted tutors with their default mail client. This is done by specifying the "to" in an email. The user is able to choose to mail a single tutor or all the tutors currently displayed in the list.
- Justification:
  - This feature simplifies the process of contacting tutors. Rather than copying and pasting one by one, `mail` provides profs with a simple and fast medium for contacting desired tutors.
- Highlights:
  - This enhancement involves the logic and model part of Tuthub. The command and parser of `mail` lies in the logic part. Model is used in accessing the currently displayed tutor list. This feature utilizes the Java classes `java.awt.Desktop` and `java.net.URI` to open the user's default mail client.

**Enhancements**
- Modified and fixed bugs in `Comment`, `CommentCommand`, and `DeleteCommentCommand` and their parsers ([#185](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/185)).
  - Fixed bugs found in:
    - [#178](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/178)
    - [#182](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/182)
    - [#183](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/183)
  - Added test cases for these logic classes.
- Updated the `Tutor` class (was `Person`) to store additional fields `StudentId` ([#76](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/76)) and `Rating` ([#114](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/114)).
  - Implemented the fields into the add command and `Tutor` class.
  - Added test cases for `StudentId` and `Rating` models.

### Contributions to the UG
- Added documentation for the features `sort` ([#127](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/127)) and `mail` ([#162](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/162)).
- Added documentation to additional model details `StudentId` and `Rating` ([#259](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/259))
- Added some parts in the [FAQ](https://ay2223s1-cs2103t-t15-3.github.io/tp/UserGuide.html#faq) section ([#184](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/184)).

### Contributions to the DG
- Added implementation details, use case, and manual testing instructions for the `sort` command ([#156](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/156)).
- Added implementation details, use case, and manual testing instructions for the `mail` command ([#299](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/299))
- Provided the base for user stories, use cases, glossary, and NFRs ([#59](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/59))

### Reviewing contributions
- PRs reviewed:
  - [#52](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/52)
  - [#89](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/89)
  - [#112](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/112)
- Reported 14 bugs for other teams in [PE-D](https://github.com/t1mzzz/ped/pull).
- Participated in the module forum
  - [#374](https://github.com/nus-cs2103-AY2223S1/forum/pull/374)
