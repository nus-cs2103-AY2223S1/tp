---
layout: page
title: Ho Cheng En Bryans's Project Portfolio Page
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

* **New Feature**: Added isRoomClean field.
  * This field indicates if a room has been cleaned or not.


* **New Feature**: Added markroomsunclean command.
  * Allows the user to set the room clean status of all the guest in the hotel to `no` in a single command.
  * This feature greatly aids with GuestBook's efficiency. Typing in one command to change all guests' room
clean statuses saves the user an incredible amount of time compared to individually editing each guest's 
details.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=bryanhce&tabRepo=AY2223S1-CS2103T-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Enhancements to existing features**:
  * Refactored entire AddressBook 3 to fit the GuestBook specificity.
    * This includes editing user guide, developer guide, person class to guest class (pull request #58).
  * Refactored Commons and Logic packages' test cases (pull request #66).


* **Documentation**:
  * User Guide:
    * Did final checks to ensure consistency of UG (pull request #142).
    * Added the glossary section (pull request #129).
    * Updated introduction and subheadings (pull request #148).
    * Updated `add` guest in user guide (pull request #122).
  * Developer Guide:
    * Did final checks to ensure consistency of DG (pull request #142).
    * Added implementation of `markroomsunclean` command (pull request #113).
    * Updated user profile, value proposition, use cases, NFR (pull request #47).
    * Added UML diagrams (pull request #113 and #112).


* **Contributions to team-based tasks**:
  * Filmed and submitted demo v1.2.
  * Filmed and submitted demo v1.3.
  * Released v1.2 of GuestBook.


* **Project management**:
  * Added Github tags, milestones, assignee.
  * Used Github issue tracker.
  * Followed the branching workflow.
  * Contributed to meetings by sharing screen and facilitating discussions.


* **Review/mentoring contributions**:
  * Team members' PR reviews (with non-trivial review comments): pull request #148, #147, #139, #134, #133, #132
,#126, #119, #117, #116, #109, #102, #100, #70, #68, #65 
  * Reported bugs for other teams [ped](https://github.com/bryanhce/ped).


