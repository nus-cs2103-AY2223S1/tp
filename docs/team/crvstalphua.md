---
layout: page
title: Crystal's Project Portfolio Page
---

### Project: DevEnable

DevEnable is a product for developers who have to manage different projects spread across multiple GitHub repositories.
It helps developers organize information about different projects they are working on in one place, making it easier 
for them to prioritize and have an overview of their projects, issues and clients. It helps them avoid the hassle of 
having to manually navigate to different Github repositories to check which tasks require immediate attention.

Given below are my contributions to the project.

* **Code Contributed**: The link to the tP dashboard can be found [here](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=crvstalphua&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=false).

* **New features implemented**: A summary of the enhancements you implemented.
  * **New Feature**: `Issue` class with `add`, `delete` and `edit` commands
    * What it does: The `Issue` class represents an issue belonging to a project. Each issue consists of a title, 
      deadline, status, urgency, pin and a project which it belongs to. Title, deadline, status and urgency fields each
      have their own classes. The user can create an issue, adding it to the `IssueList`and the list of issues of the 
      relevant project. The title, deadline, and urgency of an existing issue can also be edited. The user can remove
      an issue, deleting it from the `IssueList` and the list of issues of the project. A list of all issues can be 
      viewed through `issue -l` command as well.
    * Justification: It enables developers to easily add or delete issues to or from each of their projects. Each 
      issue has its own deadline, status and urgency so that the developer can decide how to prioritize and choose 
      which issues to work on. The developer can also modify fields if there are changes to the issue without having to 
      delete and add another issue. 
    * Highlights: Similar to the `Project` and `Client` classes, issues can be sorted based on various keys or filtered
      based on specific search terms, as well as pinned.

  * **New Feature**: `mark` and `unmark` commands in `Issue` class
    * What it does: When an issue is create, the default status is `incomplete`. The `mark` command changes the status
      of an issue to `completed` while the `unmark` command changes the status of an issue to `incomplete`.
    * Justification: Upon completing an issue, the developer can mark it as completed, making it easier to see 
      which issues still need to be addressed. If the developer realises that a completed issue requires more work, 
      they can unmark it, changing its status to incomplete.
    * Highlights: Projects are displayed with the count for number of complete and incomplete issues so that the 
      developer can see which project has more issues that require attention.

* **Enhancements to existing features**: 
  * E.g. changes to GUI, wrote additional tests, etc.

* **Documentation**:
  * Contributions to the UG
    * Added documentation and images for project command features: `add`, `delete`, `edit`, `list`, `sort`and `set default view`
    * Added documentation and images for issue command features: `add`, `delete`, `edit`, `list`, `sort`, `mark`, `unmark`, and 
      `set default view`
    * Added documentation and images for client command features: `list` and `sort` 
  * Contrbution to the DG
    * Write-up for Edit Command Feature consisting of Edit Project Command, Edit Issue Command and Edit Client Command
    * Design considerations for Edit Command Feature

* **Contributions to the team-based tasks**: 
  * Reviewed some team pull requests