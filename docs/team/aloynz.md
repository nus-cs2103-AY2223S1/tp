---
layout: page
title: Aloysius Neo Ze En's Project Portfolio Page
---

### Project: TABS

TABS is a desktop scheduler application used by students managing team projects. It allows the user who is a student to view a project's members and their workload, allocate tasks to them
and get reminders on upcoming deadlines or deliverables.

Given below are my contributions to the project.

* **New Feature**: `DisplayGroupCommand`.
  * This command class fetches the group as specified by the user.
  * This helps the user to locate a particular group and have a quick glance at its corresponding members in the group
  * The `FullGroupNamePredicate` class is created to facilitate the checking the equality of the group specified and every group stored on TABS.

*  **New Feature** : `ListGroupsCommand` .
   * This command class shows all the groups that are stored on TABS.
   * This helps the user to have a quick glance on all the existing groups created.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=aloynz&breakdown=true)

* **Project management**:
    * Handled/closed issues promptly
    * Helped to ensure that milestones are met and closed on time
    * Built and released `TABS.jar` file
    * Pull requests and reviews are done properly

* **Contribution to team-based Tasks**
    * Contributed to the brainstorming of user stories and use cases of TABS

* **Enhancements to existing features**:
    * `DeletePersonCommand`
        * Allows user to delete an existing contact by specifying the name instead of the index in the person list.

    * UI
        * Added a SplitPane to display the list of groups
        * Added icons beside contact details of persons
        * Coming out with a colour scheme display to represent the different levels of workload of tasks assigned



* **Documentation**:
    * User Guide:
        * Add the Introduction and `Quick Start` section
    * Developer Guide:
        * Add the uses cases for TABS
        * Add `DisplayGroupCommand` implementation
        * Update the `UI Component`

* **Community**:
    * Reviewed teammates' PRs
