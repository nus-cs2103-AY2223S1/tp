---
layout: page
title: Kang Zong Xian's Project Portfolio Page
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

* **New Feature**: Added Room Field

* **New Feature**: Made Room Field unique to each Guest
  * Ensures that there are no guests staying in the same room
  * This feature greatly aids in the precision of GuestBook, as unrelated guests are unlikely to
  stay in the same room. This also prevents the user from accidentally entering a room that is occupied.


* **Code contributed**: [[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=kangzongxian&tabRepo=AY2223S1-CS2103T-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)]


* **Enhancements to existing features**:
    * Removed Address Field and Tag Field to fit the GuestBook specificity.
      * The Address Field and Tag Field are not necessary in GuestBook,
      and removing them significantly improves the clarity of our codebase.

* **Documentation**:
    * User Guide:
        * Did final checks to ensure consistency of UG (pull request #140).
        * Added How to use the User Guide in user guide (pull request #132).

    * Developer Guide:
        * Did final checks to ensure consistency of DG (pull request #140).
        * Added implementation of find command in DG (pull request #114).
        * Added UML Diagrams (pull request #114)

* **Contributions to team-based tasks**:
    * Facilitated and tidied up contents of postmortem v1.2

* **Project management**:
  * Added Github tags, milestones, assignee.
  * Used Github issue tracker.
  * Followed the branching workflow.
  * Contributed to meetings by taking down minutes and facilitating discussions.

* **Review/mentoring contributions**:
  * Team members' PR reviews (with non-trivial review comments): pull request #191, #142, #138, #131, #119, #111
    , #109, #73, #68, #65, #58
  * Reported bugs for other teams ([ped](https://github.com/kangzongxian/ped/issues)).
