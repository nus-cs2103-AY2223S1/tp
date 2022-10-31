---
layout: page
title: Project Portfolio Page for Neale Tham
---

## Overview

**RC4 Housing Database** offers a convenient and intuitive interface for RC4 housing management staff to streamline their daily operations.
## Summary of Contributions

### Code contributed:

I contributed code to the following modules/classes:
1. Ui components (MainWindow, CommandBox, ResidentTableView, HelpWindow)
2. Quality-of-life components (CommandHistory, ForwardHistory, BackwardHistory)
3. Substring search (NameContainsKeywordsPredicate, FindCommandTest)
4. Storage unit tests (DataStorageManager, StorageManager, JsonAdaptedVenue etc.)
5. Sample residents, venues and bookings (SampleData)

You may view these contributions in more detail at [this link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nealetham&breakdown=true).

### Enhancements implemented:

I made the following enhancements to our product:
1. Reworked the layout of the Ui
2. Reworked the Ui to display results in a Table
3. Reworked `find` to perform substring search
5. Implemented all quality-of-life functionalities. These include:
   6. Keyboard shortcuts `Esc`, `F1`, `F3`.
   7. Command history  `↑ Up` and `↓ Down`.

### Contributions to the UG:

I contributed to the following sections of the user guide:
1. Ported the UG from `AB3` to `RC4HDB`. These include modifying the:
    - Description and formatting of all features to include `RC4HDB` specifications.
2. Re-organized UG into their respective sections i.e. file management, venue management etc.
3. Re-organized the command summary table, updated the table-of-contents.
4. Included all [Quality-of-life](../ug-pages/quality-of-life.md) functionalities.
5. Updated `find` command section to comply with our new implementation.

Other contributions include, adding a back-to-top hyperlink following each section.

### Contributions to the DG:

I contributed to the following sections of the developer guide:
1. Ported the DG from `AB3` to `RC4HDB`. Primarily in the `Appendix: Requirements` section.
2. Added more elaboration on our non-functional-requirements.
2. Added section on the implementation of our GUI.
3. Added section on the implementation of our command history functionality.
4. Added section on manual testing for:
   * `Quality-of-life` functionalities
   * `help`, `exit` commands

In particular, I added/updated the following UML diagrams:
1. [Ui Component Diagram](../images/UiClassDiagram.png)
2. [Command History Class Diagram](../images/CommandHistoryClassDiagram.png)
3. [Command History Activity Diagram](../images/CommandHistoryActivityDiagram.png)

<!-- Provide links to the diagrams in the appendix at the bottom of the page -->

### Contributions to team-based tasks:

I contributed to the following team-based tasks:
1. Setting up the GitHub team organization and repository
2. Necessary general code enhancements e.g.,
   3. Renaming product to RC4HDB
   4. Changing the product icon
2. Setting up Codecov and Gradle
1. Meeting minutes in the weekly scrum meeting
2. Work delegation during scrum meeting

### Review/mentoring contributions:

I reviewed the following pull requests:
1. [Groundwork for Venue feature](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/110)
2. [Update List command](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/76)
3. [Venue booking feature](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/114)

To view all of my reviews, refer to [here](https://github.com/AY2223S1-CS2103T-W12-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)

I also gave guidance to my team on the following matters:
1. *NIL*

### Contributions beyond the project team:

Beyond the project team, I also participated actively in the forum.

These are some threads in which I offered help to my classmates:
1. [Discussion on association, composition, aggregation](https://github.com/nus-cs2103-AY2223S1/forum/issues/86#issuecomment-1229400456)
2. [Failing codecov/patch check](https://github.com/nus-cs2103-AY2223S1/forum/issues/330)
3. [Bug: Wrong activity diagram](https://github.com/nus-cs2103-AY2223S1/forum/issues/338)


For the practical examination, I also surfaced critical bugs in the other team's product.

Some examples of these are:
1. [Functionality Bugs](https://github.com/nealetham/ped/issues?q=is%3Aopen+is%3Aissue+label%3Atype.FunctionalityBug)
2. [Feature Flaws](https://github.com/nealetham/ped/issues?q=is%3Aopen+is%3Aissue+label%3Atype.FeatureFlaw)
3. [Documentation Bugs](https://github.com/nealetham/ped/issues?q=is%3Aopen+is%3Aissue+label%3Atype.DocumentationBug)

## Appendix

### Contributions to the Developer Guide:

Updated the Ui class diagram to include `ResidentTabView`, `ResidentTableView`, `VenueTabView`, `BookingTableView`, `VenueListView`, `VenueCard`.
Removed Ui classes such as `PersonListCard`, and `PersonCard` that were existent in AB3.

![Ui Class Diagram](../images/UiClassDiagram.png)

<br>

Designed the command history class diagram, and command history activity diagram.

![Command History Class Diagram](../images/CommandHistoryClassDiagram.png)

![Command History Activity Diagram](../images/CommandHistoryActivityDiagram.png)

<!-- Embed the diagrams here -->

### Contributions to the User Guide:

1. ![]()
2. ![]()
3. ![]()

<!-- Embed the diagrams here -->
