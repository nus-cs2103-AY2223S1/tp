---
layout: page
title: Fanny Jian's Project Portfolio Page
---
## Overview of Tuthub
Tuthub is a desktop app for NUS professors who wish to track and choose their next batch of teaching assistants/tutors based on their past performance and records but have little time to spare for tedious administrative work. Tuthub helps profs who can type fast find the best TAs faster than traditional Graphical User Interface (GUI) apps.

## Summary of Contributions
### Code Contributed
Around 4.6k lines of code according to the [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=fannyjian&breakdown=true)

### Enhancements implemented
- **New Feature: Added `Tutor Details Panel` to UI** [#119](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/119)
  - Purpose: Allows full tutor information to be displayed.
  - Justification: Choosing to display the tutor information on a `Tutor Details Panel` instead of directly on the `Tutor List Card` helps with the visual organisation of information on the GUI as it conceals irrelevant information when browsing tutors.

- **New Feature: Added `view` command** [#119](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/119), [#125](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/125)
  - Purpose: Allows information in the list to be condensed, and users to select specific tutors to view full information if interested.
  - Highlights: 
    1. This involves careful modification to `CommandResult` to act as means of communication between the UI and Logic components.
    2. This command can be executed in two ways - either by entering the command in the `Command Box` or clicking on the `Tutor List Card`. The latter required extracting out the `CommandExecutor` class for relevant UI Parts to execute commands.

- **Enhancement: Created a simpler and more pleasing GUI** 
  - Purpose: Improve the user experience to make the app more professional and appealing to professors.
  - Highlights: 
    1. Made use of Figma to plan the app layout as well as Canvas to create the app icons. [#87](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/87)
    2. Modified `JavaFx` and `css` files to update color scheme of app and design of `UI` components. [#86](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/86) 
    3. Enhanced `HelpWindow` by adding a concise table of command formats. [#64](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/64)

- **Enhancement: Update `Tutor` model** [#257](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/257)
  - Change: Updated definition of the same `Tutor` to those with the same email or student ID and created corresponding test cases.

- **Enhancement: Refactor `AddressBook` to `Tuthub`** [#92](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/92)
  - Change: Replaced all instances of `AddressBook` and `Person` to `Tuthub` and `Tutor` and conducted checks to ensure that all remaining traces are replaced.

### Contributions to the UG
- Added write-ups for **Introduction**, **About this Document** and **Glossary** sections
- Added documentation for `add`, `view` and `list` commands.
- Tweaked the sequence of sections to enhance the overall flow of the document.
- Added all screenshots and labels.

### Contributions to the DG
- Added implementation details and UML diagrams for UI component as well as the `view` and `edit` commands.
- Updated use cases, user stories, glossary, product scope and instructions for manual testing.

### Reviewing contributions
- PRs reviewed: [#77](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/77), [#129](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/129), [#134](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/134), [#139](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/139), [#276](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/276), [#297](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/297), [#311](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/311), [#331](https://github.com/AY2223S1-CS2103T-T15-3/tp/pull/331).
- Reported 14 bugs for another team in [PED](https://github.com/fannyjian/ped).
- Participated in the module forum ([#405](https://github.com/nus-cs2103-AY2223S1/forum/issues/405) and [#406](https://github.com/nus-cs2103-AY2223S1/forum/issues/406)).
