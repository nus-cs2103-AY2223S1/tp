---
layout: page
title: Lee Zi Yang's Project Portfolio Page
---

### Project: TABS

TABS is a desktop scheduler application used by students managing team projects. It allows the user who is a student to view a project's members and their workload, allocate tasks to them
and get reminders on upcoming deadlines or deliverables.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add a member to a group feature
  * What it does: adds a person currently in TABS to a group.
  * Justification: This feature improves the product significantly by allowing the user to form groups to manage task allocation.


* **New Feature**: Added the ability to delete a member from a group feature
    * What it does: deletes a person currently in TABS and in the specified group from the group.
    * Justification: This feature improves the product significantly by allowing the user to remove members that are no longer part of the group.


* **New Feature**: Added tabs to switch between assignment view and contact details (UI)
    * What it does: allows the user to toggle between the two panes displaying contact details and assignments.
    * Justification: This feature improves the product significantly by allowing the user to view either the assignments or the contact details efficiently.


* **New Feature**: Added group tag to each person in a group
    * What it does: allows the user to view at a glance the groups the person is in 
    * Justification: This feature improves the product significantly by the user to view the groups a person is in more quickly and efficiently.
    * Credits: built based of the tags code in AB3.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=zylee348&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16)


* **Project management**:
    * Assisted closure of v1.2


* **Enhancements to existing features**:
    * Updated the GUI colour scheme (Pull requests #149)
    * Updated the error messages for some existing features (Pull requests #193, #207)
    * Refactored the original AB3 `add` and `find` command to `addperson` and `findperson`
    * Added testcases for `addmember` and `deletemember`

* **Documentation**:
    * User Guide:
        * Updated the command summary
        * Added the GUI walk-through
        * Added implementation details of the add and delete member features
    * Developer Guide:
        * Added the add/delete member section


* **Community**:
  * PRs reviewed (with non-trivial review comments): #114
