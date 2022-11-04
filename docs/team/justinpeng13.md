---
layout: page
title: Justin Peng's Project Portfolio Page
---

### Project: GuestBook

GuestBook is a **desktop app for managing guests in a hotel,
optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).
If you are a hotel manager who can type fast, GuestBook can get your
guest management tasks done faster than traditional GUI apps.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 13 kLoC.
This project is based off AddressBook - Level 3.

Given below are my contributions to the project.

* **New Feature**: Added `DateRange` field.
  * This field represents the start and end date of a guest's stay.

* **New Feature**: Added `Bill` field.
  * This field represents the total additional charges incurred by a guest during their stay.
  
* **New Feature**: Added `bill` command.
  * Allows the user to cumulatively update the bill of a guest in GuestBook (specify the amount to be added).
  * This command is typically used to charge a guest, such as for room service.
  * This command ensures that the total bill of a guest is not negative and does not exceed the maximum possible value.
  * This feature greatly aids with GuestBook's efficiency, as GuestBook calculates the new value of the total bill
  automatically.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=JustinPeng13&tabRepo=AY2223S1-CS2103T-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Revised all field constraints to make GuestBook more flexible/strict in accepting input data as appropriate.
  * Revised all error messages to be clearer and more specific, making GuestBook more user-friendly.

* **Project management**:
  * Used GitHub actions, projects, issues, tags, milestones, and assignees to complete tasks in an orderly manner.
  * Used Scrum methodology with weekly sprints/stand-ups.
  * Used a forking workflow.
  * Contributed to meetings by sharing screen and facilitating discussions.

* **Documentation**:
  * User Guide:
    * Added field constraints for `Bill` and `DateRange`.
    * Added FAQ and caution message for prefixes in field contents.
    * Rearranged sections for better flow.
    * Formatted text for emphasis and revised language for better readability.
  * Developer Guide:
    * Added `Bill` field section.
    * Added `DateRange` field section.
    * Added `bill` command section with activity diagram.
    * Updated `Model` class diagram.
    * Added Acknowledgements section.

* **Contributions to team-based tasks**:
  * Refactored documentation.
  * Added and reviewed test cases to improve code coverage.
  * Maintained issue trackers.

* **Review/mentoring contributions**:
  * Reviewed team members' PRs meticulously. Reviews with non-trivial comments:
    * Documentation: [#213](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/213), [#199](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/199), [#198](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/198), [#188](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/188), [#118](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/118), [#116](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/116), [#67](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/67)
    * Code: [#121](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/121), [#112](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/112), [#78](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/78), [#65](https://github.com/AY2223S1-CS2103T-W16-1/tp/pull/65)
  * Guided team members with using SourceTree.
  * [Reported bugs](https://github.com/JustinPeng13/ped) for other teams.

