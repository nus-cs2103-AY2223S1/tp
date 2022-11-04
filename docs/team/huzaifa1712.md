---
layout: page
title: Huzaifa Raghav's Project Portfolio Page
---

### Project: `CLIMods`

`CLIMods` is a native desktop application for NUS students to explore, manage and plan academic
modules that they are interested in taking. Power users of the command line/terminal will be able to
plan and manage their modules much faster than doing it on [nusmods.com](https://nusmods.com).

Given below are my contributions to the project.

* **New Feature**: Added a `view` command that allows the user to see details for a specific module
    * What it does: Allows user to view details (description, prerequisites, preclusions) for a specific module
    * Justification: This feature is necessary to facilitate adding modules to the user's list
    * Highlights: The implementation required introduction of state management to track the module being viewed
    * Credits: I set up the basic functionality for view, but the rest of the team added the UI for viewing classes

* **New Feature**: Added a `preq` command that allows the user to see the list of prerequisites for a module
  * What it does: Allows user to see list of prerequisite modules
  * Justification: This feature helps the user to see the list of prerequisites for a module at a glance
  * Highlights: The implementation used regex to extract module codes from the prerequisite description.

* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo
