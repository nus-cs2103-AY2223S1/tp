---
layout: page
title: Project Portfolio Page for Neale Tham
---

## Overview

**RC4 Housing Database** offers a convenient and intuitive interface for RC4 housing management staff to streamline their daily operations.
## Summary of Contributions

### Code contributed:

I contributed code to the following modules/classes:
1. *Enter contributions here*
2. *Enter contributions here*
3. *Enter contributions here*

You may view these contributions in more detail at [this link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=nealetham&breakdown=true).

<!-- Please replace the placeholder in the above URL with your github username. -->

### Enhancements implemented:

I made the following enhancements to our product:
1. *Enter contributions here*
2. *Enter contributions here*
3. *Enter contributions here*

### Graphical User Interface (GUI)

In `AB3` the GUI was implemented using `PersonListPanel` and `PersonCard`. Each `Person` is graphically represented as
a `PersonCard` which contains all fields of a `Person` i.e. `Name`,`Phone`, `Email`, `Address`, `Tags`

Results from a command would be displayed in the `PersonListPanel`, which graphically is a single column list,
with each row corresponding to a `PersonCard`.

Insert Old Ui Image Here

At any given time in RC4, there are approximately 300 individuals staying in RC4. With our goal of providing an
efficient and productive means of handling administrative tasks, we thought that the current interface was unsuitable
for visualizing large numbers of entries.

As a consequence, we adapted the `PersonListPanel` and converted it to `ResidentTableView`. A `Resident` entry will no
longer be represented graphically as a `PersonCard`, but as a row in our table. The fields for each respective
`Resident` i.e. `Name`, `Phone Number`, `Email`, `Gender`, `House`, `Matric Number`, and `Tags`are now represented as
columns in each row.

Insert New Ui Image here

Users will now be able to see more entries on the window as compared to before. Fields of a `Resident` are also
structured and more easily identifiable. These two together can potentially increase productivity of our user.

The implementation of the `ResidentTableView` is relatively simple and done so using `JavaFx TableView`. For our usage,
there are no performance issues, but it is well known that `JavaFX TableView` is known to be memory hungry, and
potentially laggy when the number of columns exceed 50. To see a discussion on this click
[here](https://github.com/javafxports/openjdk-jfx/issues/409).


Other minor updates I made to the GUI include:
* Updating the application icon to a suitable image for our use
* Updating the application name from `AddressBook` to `RC4HDB`
* Removed the File Tab in the status bar header
* Rearranged the layout of the interface. `CommandBox` is now at the bottom of the window as opposed to the top.


### Keyboard Shortcut

As `RC4HDB` is designed to be optimized for use via a Command Line Interface (CLI). We have introduced several
keyboard shortcut commands to improve the quality-of-life of our users such that there is little need for them to lift
their hands to grab the mouse.

Below is a table that lists all of our keyboard shortcuts:

INSERT TABLE HERE FROM UPDATED UG

The implementation of the `F3` keyboard shortcut was relatively simple. It involved adding a listener to scene, which
would refocus on the `TextField` within the `CommandBox` class during the event that the key
is pressed.


### Contributions to the UG:

I contributed to the following sections of the user guide:
1. *Enter contributions here*
2. *Enter contributions here*
3. *Enter contributions here*

### Contributions to the DG:

I contributed to the following sections of the developer guide:
1. *Enter contributions here*
2. *Enter contributions here*
3. *Enter contributions here*

In particular, I added/updated the following UML diagrams:
1. []()
2. []()
3. []()

<!-- Provide links to the diagrams in the appendix at the bottom of the page -->

### Contributions to team-based tasks:

I contributed to the following team-based tasks:
1. *Enter contributions here*
2. *Enter contributions here*
3. *Enter contributions here*

### Review/mentoring contributions:

I reviewed the following pull requests:
1. [Add profile photo for Naren #23](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/23)
2. [Create skeletal project portfolio page #22](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/22)
3. [Edit README #28](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/28)
4. [Update index.md #29](https://github.com/AY2223S1-CS2103T-W12-3/tp/pull/29)

I also gave guidance to my team on the following matters:
1. *Enter contributions here*
2. *Enter contributions here*
3. *Enter contributions here*

### Contributions beyond the project team:

Beyond the project team, I also participated actively in the forum.

These are some threads in which I offered help to my classmates:
1. [Discussion on association, composition, aggregation](https://github.com/nus-cs2103-AY2223S1/forum/issues/86#issuecomment-1229400456)
2. [Failing codecov/patch check](https://github.com/nus-cs2103-AY2223S1/forum/issues/330)
3. [Bug: Wrong activity diagram](https://github.com/nus-cs2103-AY2223S1/forum/issues/338)

<!-- Provide links to the threads here -->

For the practical examination, I also surfaced critical bugs in the other team's product.

Some examples of these are:
1. []()
2. []()
3. []()

## Appendix

### Contributions to the Developer Guide:

1. ![]()
2. ![]()
3. ![]()

<!-- Embed the diagrams here -->

### Contributions to the User Guide:

1. ![]()
2. ![]()
3. ![]()

<!-- Embed the diagrams here -->
