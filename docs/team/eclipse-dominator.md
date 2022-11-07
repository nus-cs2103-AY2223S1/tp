---
layout: page
title: Zhaoqi's Project Portfolio Page
---

### Project: Contactmation

Contactmation is a powerful **desktop-based project and task management solution** that **helps you efficiently and
effectively manage many projects at once** through the CLI.

## Summary of Contributions

[Code contributed](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=Eclipse-Dominator&breakdown=true)

[Pull requests](https://github.com/AY2223S1-CS2103T-T11-1/tp/pulls?q=is%3Apr+author%3Aeclipse-dominator)

### Enhancements Implemented

- Design of Model
  - Designed how the 3 items task, person, and group are functioned together as well as each of the individual abstractions and implementations ([#33](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/33))
  - Made all items (person, task, group) extends AbstractDisplayItem which uses DisplayItem Inteface. ([#33](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/33))
  - Designed how nested groups functions (This is implemented by mohamedsaf1)
  - Added an ability to force model to refresh the UI view of all elements
  - Designed and implemented how attributes are being displayed as cards to the UI. ([#57](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/57))
  - Designed and added bit flags to denote attribute styles and properties as well as displayitem styles and properties ([#57](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/57))
  - Proposed the use of the above to create custom, detailed view page of each elements (not implemented in time)
- Design of UI

  - Updated UI to create 3 additional views to contain Task and Group ([#40](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/40))
  - Added UI Component to display current group scope. ([#40](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/40))

- Design of Logic

  - Designed and implemented macros, command compilation, logical command, aliases in logic
  - Revamped command logic to allow logic to parse data between each other ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))
  - Designed the use of PIPING to allow command to transfer information to one another ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))
  - Implemented foreach commands to batch apply commands to all list views
  - Updated addressbook parser to use a Singleton design ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))
  - Swapped addressbook parser to use a HashMap to map commands to actual commands instead of switch case ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))
  - Implemented Seq commands ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))
  - Implemented commands to create new terms (int, float, str) ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))
  - Implemented if else commands ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))
  - Enabled user to chain and save complex commands ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))
  - Enabled user to use alias to change the default command text ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))
  - Implemented the renaming of DisplayItem ([#72](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/72))

- Bug fixes and refactorings
  - Improved on robustness of validation of phone number to handle unexpected user phone numbers in response to Issue [#105](https://github.com/AY2223S1-CS2103T-T11-1/tp/issues/105) ([#170](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/170))
  - Fixed field add ignoring duplicate types ([#170](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/170))
  - Fixed uncaught exceptions in field edit ([#170](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/170))
  - Fixed aliasing not working for custom command
  - Fixed Phone attribute being read as Address after reopening Contactmation ([#161](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/161))
  - Refactored commands with similar codes (delete, select, into singular commands) ([#153](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/153))
  - Fixed incorrect regex when creating new groups Issue [#99](https://github.com/AY2223S1-CS2103T-T11-1/tp/issues/99) and related duplicates
  - Fixes bug where unmark and mark commands resets custom fields (from internal testing) ([#147](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/147))
  - Fixed missing find command (due to not added to the parser) Issue [#77](https://github.com/AY2223S1-CS2103T-T11-1/tp/issues/77) and related duplicates ([#143](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/143))
  - Fixed task foreach commands (due to not added to the parser) issue [#103](https://github.com/AY2223S1-CS2103T-T11-1/tp/issues/103) and related duplicates ([#143](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/143))
  - Updated exception handling to allow users to know when the save file is write protected based on issue [#75](https://github.com/AY2223S1-CS2103T-T11-1/tp/issues/75) and related duplicates ([#170](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/170))

### Contributions to the UG

- Wrote section in advanced user guide section
- Partially updated the command summary table ([#148](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/148))
- Fixed the formatting and screenshots ([#177](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/177))
- Created the default dummy save data to be used as basis for other screenshots. ([#145](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/145))

### Contributions to the DG

Added the following:

- Updated model UML ([#48](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/48))
- Updated ui UML ([#48](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/48))
- Added sequence diagrams for how DisplayItems are being generated as visible UI ([#48](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/48))
- Updated logic UML
- Added explanation to how the model and logic system works. ([#181](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/181))
- Added Detailed View to future implementations. ([#181](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/181))

### Contributions to team-based tasks

- Created the team repository
- Actively participated in weekly group discussions.
- Created v1.2 Features Demo
- Created v1.3 Features Demo
- Created PED temporary report
- Created `documentation bug` and `formatting bug` tags to differentiate between the different bugs reported by others

### Review/mentoring contributions

- Reviewed numerous
  PRs ([list of reviewed PRs](https://github.com/AY2223S1-CS2103T-T11-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aeclipse-dominator))
- Reviewed the major user guide
  changes. ([#172](https://github.com/AY2223S1-CS2103T-T11-1/tp/pull/172#pullrequestreview-1170043487))
- Assisted team in suggesting ideas and fixes

### Contributions beyond the project team

- Reported bugs for other teams during the PE-D ([list of reports](https://github.com/Eclipse-Dominator/ped/issues)).
