---
layout: page
title: Huzaifa Raghav's Project Portfolio Page
---

### Project: `CLIMods`

`CLIMods` is a native desktop application for NUS students to explore, manage and plan academic
modules that they are interested in taking. Power users of the command line/terminal will be able to
plan and manage their modules much faster than doing it on [nusmods.com](https://nusmods.com).

Given below are my contributions to the project.

* **Infrastructure**: Added code to facilitate parsing of positional parameters (Pull request [\#55](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/55))
  * Used throughout application code for almost all commands

* **New Feature**: Added a `view` command that allows the user to see details for a specific module
    * What it does: Allows user to view details (description, prerequisites, preclusions) for a specific module
    * Justification: This feature is necessary to facilitate adding modules to the user's list
    * Highlights: The implementation required introduction of state management to track the module being viewed
    * Credits: I set up the basic functionality for view, but the rest of the team added the UI for viewing classes

* **New Feature**: Added a `preq` command that allows the user to see the list of prerequisites for a module
  * What it does: Allows user to see list of prerequisite modules
  * Justification: This feature helps the user to see the list of prerequisites for a module at a glance
  * Highlights: The implementation used regex to extract module codes from the prerequisite description.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=huzaifa1712&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**:
  * Wrote additional tests for existing features (Pull request [\#154](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/154))
  * Contributed functionality and tests to initial `add` and `rm` command implementations (Pull request [\#59]())

* **Documentation**:
    * User Guide:
        * Added documentation for `preq` command
    * Developer Guide:
        * Added architecture sequence diagram
        * Added sequence diagram for Logic component
        * Updated with details about parameter classes

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#57](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/57), [\#85](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/85), [\#154](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/154)
    * Contributed to forum discussions: among the top 10 forum [contributors](https://nus-cs2103-ay2223s1.github.io/dashboards/contents/forum-activities.html#10-huza-ghav-huzaifa1712-25-posts)
