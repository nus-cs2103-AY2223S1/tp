---
layout: page
title: Tze Kin's Project Portfolio Page
---

### Project: CinternS

CinternS is a desktop app for managing internship applications, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort the applications list. [#99](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/99) & [#117](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/117)
    * What it does: Allows the user to sort the applications list by company, position, application date or interview date, both in forward and reverse orders.
    * Justification: This feature allows the user to organise the applications in various useful orders based on their needs. It allows the user to find applications based on the different fields more easily.

* **New Feature**: Added the ability to save the sort order. [#129](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/129)
    * What it does: Allows the sort order last used by the user to be saved when the app is closed so that it can automatically be used the next time the app is opened.
    * Justification: A user may find that one particular order of sorting is most useful to them. Preserving this sort order across sessions saves the user the trouble of having to enter the sort command every time. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=teekaytai&breakdown=true)

* **Project management**
    * Drove discussions during team meetings on Zoom.
    * Started a GitHub Project to aid in tracking the work to be done.
    * Reviewed pull requests of teammates and provided suggestions for improvement. (examples: [1](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/98#pullrequestreview-1148545590), [2](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/112#pullrequestreview-1153542428)) 
    * Managed release `v1.3.1` on GitHub.
    * Collated and categorised the issues raised during testing for easier triaging.

* **Enhancements to existing features**:
    * Implemented a separate error message for when a user attempts to use an index number that overflows an int. [#188](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/188)
    * Tracked down and fixed a bug causing the `remind` feature to display incorrect/empty/duplicate interviews. The bug rarely and inconsistently appeared making it difficult to identify. Resolving this issue required careful use of IntelliJ's debugger and about 10 hours. [#191](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/191)

* **Documentation**:
    * User Guide:
        * Added documentation for the `sort` feature.
        * Proofread the whole user guide to fix errors and improve the overall clarity. [#137](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/137)
    * Developer Guide:
        * Added implementation details of the `sort` feature.
        * Added implementation details for the saving of sort setting.

* **Community**:
    * Made 11 contributions to help others on the module forum (examples: [1](https://github.com/nus-cs2103-AY2223S1/forum/issues/50#issuecomment-1222283204), [2](https://github.com/nus-cs2103-AY2223S1/forum/issues/112#issuecomment-1232696756), [3](https://github.com/nus-cs2103-AY2223S1/forum/issues/172#issuecomment-1242997909))
    * Reported bugs and suggestions for other teams in the class ([examples](https://github.com/teekaytai/ped/issues))
