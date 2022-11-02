---
layout: page
title: Donovan's Project Portfolio Page
---

### Project Overview: DevEnable
DevEnable is a product for developers who have to manage different projects spread across multiple GitHub
repositories. It helps developers organize information about different projects they are working on in one place so
that they may prioritize and have an overview. It removes the hassle of having to navigate to our/organization’s
GitHub repo every time and manually check different pages to see which tasks require immediate attention.

Given below are my contributions to the project.

* **Code Contributed**: The link to the tP dashboard can be found [here](https://nus-cs2103-ay2223s1.github.
  io/tp-dashboard/?search=Donovan9617&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16
  &timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New features implemented**: A summary of the enhancements you implemented.
    * **New Feature**: `Project` class with `add`, `delete` and `edit` commands
        * What it does: The `Project` class represents a project consisting of a name, repository, deadline, client, pin
          and a list of issues. The name, repository and deadline fields are classes on their own. The developer may add
          a new project to the project list, delete an existing project from the project list, or edit the fields of an 
          existing project in the project list. The developer may view these projects through the `project -l` command.
        * Justification: Objects that instantiate the `Project` class represent projects that the developer wants to 
          keep track of. They are stored in `projectList` and may be viewed through DevEnable user interface. The 
          developer may add, delete or edit these projects in order to manage them.

        * **New Feature**: `sort` command
          * What it does: Sorts projects, issues, and clients according to various keys and orders as specified.
          * Justification: The developer may want to sort the projects, issues, and clients according to various keys such 
            as urgency, deadline, name, issue count in order to view important entities easily.
      
    * **New Feature**: Graphical User Interface (GUI)
        * What it does: Makes the user interface for DevEnable aesthetic and pretty.
        * Justification: Improves the user experience when using DevEnable for project managements.

* **Documentation**:
    * Contributions to the UG
        * Commands and validation related to adding a project, deleting a project and editing a project
        * Commands related to sorting projects, issues and clients
    * Contributions to the DG
        * Write-up for Add Command Feature consisting of Add Project Command, Add Issue Command, and Add Client Command
        * UML Sequence Diagram for adding a project to the project book
        * Design considerations for Add Command Feature

* **Contributions to team-based tasks**:
    * Helped to take meeting notes for weekly project meetings online
    * Reviewed some team pull requests
    * Offered ideas for how commands may be structured to integrate
    * Fixed bugs arising from integration of project client and issue classes
