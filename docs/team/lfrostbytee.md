---
layout: page
title: Ong Wee Marcus's Project Portfolio Page
---

### Project: Duke The Market

Duke The Market is a desktop application used for managing customer contacts and events in department stores. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=lfrostbytee&breakdown=true)

* **Enhancements implemented**:
    * Updated the GUI for the application and added the `listEvents` command [\#54](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/54)
        * What it does: The application can now display events concurrently with persons while the entering command displays all events in the application to the user.
        * Justification: This feature allows the user to see events in the current order in which they are stored as data and has been extended to sort events permanently.
    * Added the `Date` and `Time` attributes for Events and the `Date` attribute for Person [\#106](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/106), [\#129](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/129)
        * What it does: Events can now store a starting date and time while each person can store their date of birth.
        * Justification: This feature allows the user to view the start date and time of every event in the application as well as view the date of birth
    of all persons in the application. These attributes have been used to create and display an event's statistics about its attendees' gender distribution and age.
        * Credits: Reused code from Hong Liang (team member) and re-abstracted the code to form the date package so that it can be used by both the `Event` and `Person` class.
    * Created the GUI display for statistics and created the `makeStats` command to open the statistics GUI display [\#131](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/131)
        * What it does: Opens a new window showing a coloured pie chart of a statistic.
        * Justification: This feature allows the user to see the statistics of an event visually.
    * Added the `findEvents` command [\#128](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/128)
        * What it does: Displays all events that match the keywords that have been inputted.
        * Justification: This feature helps the user to better locate the events that they are looking for if they are already managing many events.

* **Documentation**:
    * User Guide:
        * Added documentation for the `findEvents` command.
        * Removed documentation for the `tag` feature.
        * Updated documentation for `findPersons` command.
    * Developer Guide:
        * Added documentation for V1.1 and the `listEvents` command.

* **Contributions to team-based tasks**:
    * Setting up the GitHub team org/repo.
    * Organizing and facilitating bi-weekly team meetings as the Team Lead.
    * Distributing workload amongst team members.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#57](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/57), [\#99](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/99), [\#108](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/108), [\#133](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/133).
    * Reported bugs and suggestions for other teams in the class (examples: [\#125](https://github.com/AY2223S1-CS2103T-W17-1/tp/issues/125), [\#129](https://github.com/AY2223S1-CS2103T-W17-1/tp/issues/129), [\#164](https://github.com/AY2223S1-CS2103T-W17-1/tp/issues/164), [\#165](https://github.com/AY2223S1-CS2103T-W17-1/tp/issues/165))
