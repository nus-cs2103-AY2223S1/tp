---
layout: page
title: Farisa Nadia's Project Portfolio Page
---

### Project: Pupilist

### Overview
Pupilist is an all-in-one desktop application for private users to arrange their students information. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

### Summary of contributions
Given below are my contributions to the project.

* **New Feature**: Added the ability to remove specified fields.
  * What it does: allows the user to remove specified fields from lists in students information.
    * Updated `GradeProgressList`, `HomeworkList`, `SessionList`, and `AttendanceList` to include `removeAtIndex` that allows removal of specified index in respective list.
    * Created `RemoveCommand` and `RemoveCommandParser` and the respective tests
  * Justification: This feature improves the product significantly because a user can wish to remove certain information from their students lists to prevent clutter. 
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=farisanadia&breakdown=true)

* **Enhances to existing features**:
  * Redesigned Pupilist GUI, including changing color schemes and changing layout of the application for main page and Person cards (Pull requests [#71](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/71), [#78](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/78))
  * Redesigned GUI for timeslot cards (Pull request [#106](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/106))
  * Redesigned GUI for help page and added basic commands information to help page (Pull request [#45](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/45))
  * Removed `email` and `address` fields from address book [#46](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/46)

* **Documentation**:
  * User Guide:
    * Added documentation for features `remove`, `hw`, `grade`, `attendance`, `session`, `lessonplan`, `show`: [#30](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/30), [#66](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/66)
    * Did cosmetic tweaks to existing documentation of features `add`, `list`, `find`, `edit`: [#30](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/30)
  * Developer Guide:
    * Added implementation details for `remove` feature
    
* **Community**:
  * PRs reviewed (trivial comments with little to no issues with code): Pull requests [#44](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/44) [#77](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/77) [#86](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/86) [#87](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/87) [#92](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/92) [#182](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/182) [#186](https://github.com/AY2223S1-CS2103T-W09-4/tp/pull/186)
  * [Bugs reported](https://github.com/farisanadia/ped)
