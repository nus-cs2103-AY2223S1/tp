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

* **Code Contributed**: The link to the tP dashboard can be found [here](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=Donovan9617&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: `Project` class with `add`, `delete` and `edit` commands [\#62](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/62) [\#70](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/70) [\#93](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/93)
    * What it does: The `Project` class represents a project consisting of a name, repository, deadline, client, pin
      and a list of issues. The name, repository and deadline fields are classes on their own. The developer may add
      a new project to the project list, delete an existing project from the project list, or edit the fields of an 
      existing project in the project list. The developer may view these projects through the `project -l` command.
    * Justification: Objects that instantiate the `Project` class represent projects that the developer wants to 
      keep track of. They are stored in `projectList` and may be viewed through DevEnable user interface. The 
      developer may add, delete or edit these projects in order to manage them.

* **New Feature**: `sort` command [\#108](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/108) [\#126](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/126) [\#132](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/132)
    * What it does: Sorts projects, issues, and clients according to various keys and orders as specified.
    * Justification: The developer may want to sort the projects, issues, and clients according to various keys such 
    as urgency, deadline, name, issue count in order to view important entities easily.
      
* **New Feature**: Graphical User Interface (GUI) [\#127](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/127) [\#135](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/135)
    * What it does: Makes the user interface for DevEnable aesthetic and pretty.
    * Justification: Improves the user experience when using DevEnable for project managements.

* **Documentation**:
    * User Guide
        * Commands and validation related to adding a project, deleting a project, editing a project and sorting a project [\#143](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/143)
    * Developer Guide
        * Write-up and UML Diagrams for UI Component, Add Command Feature and Sort Command Feature [\#239](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/239) [\#108](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/108) [\#241](https://github.com/AY2223S1-CS2103-F13-1/tp/pull/241)

* **Contributions to team-based tasks**:
    * Helped to take meeting notes for weekly project meetings online
    * Reviewed some team pull requests
    * Created demo video for v1.3
    * Fixed bugs arising from integration of project client and issue classes

* **Community**
    * Reported bugs and suggestions for other teams in the class during PED
