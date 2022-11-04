---
layout: page
title: Cedric Chia's Project Portfolio Page
---

### Project: TABS

TABS is a desktop scheduler application used by students managing team projects. It allows the user who is a student to view a project's members and their workload, allocate tasks to them
and get reminders on upcoming deadlines or deliverables.

## Contributions
Given below are my contributions to the project.

* **New Feature**: `EditPersonCommand`.
    * This command class fetches the person to be edited by the user.
    * This helps the user search a person by his full name and edit his contact details directly.
    * The `FullNamePredicate` class is created to facilitate the checking the equality of the person's full name specified and every person stored on TABS.

* **New Feature** : `DeleteGroupCommand` .
    * This command class allows the deletion of a group, removing its corresponding members as well as allocated tasks.
    * This helps the user to delete a group instantly.

* **New Feature** : `Workload` .
    * This enum class allows a workload to be tracked for each task assigned to each member.
    * A workload of low, medium or high can be tagged to each task.
    * This allows the user to view how overloaded a person is when assigning tasks.

* **New Feature** : `Deadline` .
    * This class allows TABS to store deadlines into tasks.
    * Along with this class, the `DateTimeParser` class is implemented to recognise inputs by the user, to parse as dates.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cedricchia123&breakdown=true)

* **Contribution to team-based Tasks**

* **Enhancements implemented**:

* **Documentation**:
    * User Guide:
    * Developer Guide:

* **Review/mentoring contributions**:

* **Review/mentoring contributions**:
