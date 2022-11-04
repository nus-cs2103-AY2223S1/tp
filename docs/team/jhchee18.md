---
layout: page
title: JerHong's Project Portfolio Page
---

### Project: CinternS

CinternS is a desktop app for managing internship applications, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Include `Interview` as one of the fields of `Application`.
    * What it does: `Interview` contains its own fields, including `Round`, `InterviewDate`, `InterviewTime` and `Location`, all of which are in turn included into a separated list to be displayed on the GUI.
    * Justification:  This allows the user to have a quick and clear glance at his / her past and future interviews so that the user can better manage his / her internship applications.
    * Highlights: This enhancement provides two additional commands:
      1. `AddInterviewCommand`: Allows user to add new interview to existing application.
      2. `RemoveInterviewCommand`: Allows user to remove existing interview from application.
      <br>This enhancement acts as the backbone of other newly implemented features, e.g. `Sort`, `Remind`, `Archive`, etc. It also involves every component of the app to implement this feature, hence complete understanding of the components is necessary.
    * Credits: N.A.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jhchee18&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed milestones to ensure issues and deliverables are completed on time.

* **Enhancements to existing features**:
    * Updated the GUI color scheme. (PR [#135](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/135))
    * Improved `help` command by directing the user to the User Guide with one click. (PR [#123](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/123))

* **Documentation**:
    * User Guide:
        * Added documentation for `Interview` which includes features `interview` (add interview) and `remove-i` (remove interview). (PR [#123](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/123))
        * Updated the snapshots of the GUIs. (PR [#186](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/186))
    * Developer Guide:
        * Added implementation details for `interview` and `remove-i` features. (PR [#96](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/96))
        * Included UML diagrams for `interview` and the proposed `find-i` (find interview) feature. (PR [#96](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/96))
    * Other:
        * Updated README.md and index.md to ensure [GitHub](https://github.com/AY2223S1-CS2103-F14-3/tp) and the [CinternS Product Website](https://ay2223s1-cs2103-f14-3.github.io/tp/) are displaying correctly.

* **Community**:
    * Reported bugs and suggestions for other teams in the class (Issues: [here](https://github.com/jhchee18/ped/issues)).
