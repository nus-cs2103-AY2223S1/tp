---
layout: page
title: Jolyn's Project Portfolio Page
---

### Project: CinternS

CinternS is a desktop app for managing internship applications, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Reminders on upcoming interviews
    * What it does: Allows user to view a list of upcoming interviews within the next 1 week, sorted by interview date and time.
    * Justification: The rationale for this enhancement is that the interview list on the main GUI window shows all non-archived interviews, including interviews that have passed and interviews scheduled weeks to months later. This feature provides user convenience by enabling a focused view of only approaching interviews within the next week in chronological order.
    * Highlights: This feature required an all-rounded understanding of the various components involved in the application. This includes the UI which is involved in launching the window displaying upcoming interviews, the logic where the `remind` command is implemented, and the model where the relevant interviews are retrieved from. 
    * Credits: The idea of a pop-up window for displaying upcoming interviews was inspired by the implementation of the help window in [address-book-level-3](https://github.com/se-edu/addressbook-level3)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jolynloh&breakdown=true)

* **Enhancements to existing features**:
    * Refactored code base to suit the purpose of the software as an internship application management app, including updating GUI elements and selecting appropriate icons (Pull request [#58](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/58))
    * Update tests for various commands to fit internship applications (Pull request [#77](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/77))

* **Documentation**:
    * User Guide:
        * Added documentation for the `remind` feature (Pull request [#127](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/127/))
        * Updated documentation for better clarity (Pull requests [#133](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/133) and [#196](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/196))
    * Developer Guide:
        * Added implementation details of the `find` and `remind` features (Pull requests [#100](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/100) and [#127](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/127))

* **Project management**:
    * Managed issues, pull requests and milestones on GitHub

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#91](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/91), [#96](https://github.com/AY2223S1-CS2103-F14-3/tp/pull/96)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/jolynloh/ped/issues/1), [2](https://github.com/jolynloh/ped/issues/2), [3](https://github.com/jolynloh/ped/issues/6), [4](https://github.com/jolynloh/ped/issues/12))
