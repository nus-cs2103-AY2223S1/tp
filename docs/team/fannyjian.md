---
layout: page
title: Fanny Jian's Project Portfolio Page
---
## Overview of TutHub
Tuthub is a desktop app for NUS professors who wish to track and choose their next batch of teaching assistants/tutors based on their past performance and records but have little time to spare for tedious administrative work. Tuthub helps profs who can type fast find the best TAs faster than traditional Graphical User Interface (GUI) apps.

## Summary of Contributions
### Code Contributed
Around 4.5k lines of code according to the [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=fannyjian&breakdown=true)

### Enhancements implemented
- **New Feature: Added `Tutor Details Panel` to UI**
  - Purpose: Allows full tutor information to be displayed.
  - Justification: Choosing to display the tutor information on a `Tutor Details Panel` instead of directly on the `Tutor List Card` helps with the visual organisation of information on the GUI as it conceals irrelevant information when browsing tutors.

- **New Feature: Added `view` command**
  - Purpose: Allows information in the list to be condensed, and users to select specific tutors to view full information if interested.
  - Highlights: 
    1. This involves careful modification to `CommandResult` to act as means of communication between the UI and Logic components.
    2. This command can be executed in two ways - either by entering the command in the `Command Box` or clicking on the `Tutor List Card`. The latter required extracting out the `CommandExecutor` class for relevant UI Parts to execute commands.

- **Enhancement: Created a simpler and more pleasing GUI**
  - Purpose: Improve the user experience to make the app more professional and appealing to professors.
  - Contribution: Made use of Figma to plan the app layout as well as Canvas to create the app icons, modified `JavaFx` and `css` files to update styles.

- **Enhancement: Update `Tutor` model**
  - Change: Updated definition of the same `Tutor` to those with the same email or student ID and altered test cases accordingly.

- **Enhancement: Refactor `AddressBook` to `Tuthub`**
  - Change: Replaced all instances of `AddressBook` and `Person` to `Tuthub` and `Tutor` and conducted checks to ensure that all remaining traces are replaced.


### Contributions to the UG
- Added write-ups, screenshots and diagrams for **Introduction**, **About this Document** and **Glossary** sections, and added documentation for `add`, `view` and `list` commands.
- Tweaked the sequence of sections to enhance the overall flow of the document.

### Contributions to the DG
- Added implementation details for UI component and `view` command.
- Updated use cases, user stories, glossary, product scope and instructions for manual testing.

### Reviewing contributions
- PRs reviewed: [#77](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/77), [#129](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/129), [#134](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/134), [#139](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/139), [#276](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/276), [#297](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/297), [#311](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/311).
- Reported 14 bugs for another team in [PED](https://github.com/fannyjian/ped).
- Participated in the module forum ([#405](https://github.com/nus-cs2103-AY2223S1/forum/issues/405) and [#406](https://github.com/nus-cs2103-AY2223S1/forum/issues/406)).
