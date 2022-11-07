---
layout: page
title: Tan Hong Liang's Project Portfolio Page
---

### Project: Duke The Market

Duke The Market is a desktop application
used for managing customer contacts and events in department stores. The user interacts with it using a CLI,
and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=tanhl2000&breakdown=true)

* **Enhancements implemented**:
  * **New Feature** Added date of birth field to Person, also created the date of birth field type. (later to be evolved into date field type) (Pull requests [#55](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/55), [#62](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/62), [#101](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/101))
          * What it does:
          1. Allows the user to add a person with a date of birth field to the person list.
          2. Allows the user to edit the date of birth of an existing person in the person list.
          3. Date of birth field type is later evolved to serve as the date field type of the date field in both persons and events as well (Related `addEvent` and `editEvent` commands implemented by another team member).
        * Justification: Date of birth is a common field in personal profile, and it can be used in demographic
      analysis for marketing event attendees (related command `makeStats` see below).

  * **New Feature** Implemented a statistics generation system for events, based off the age and gender of the people tagged to the events. (Pull requests [#132](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/132), [#142](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/142))
        * What it does:
          1. Allows the user to generate a piechart that shows the statistics of the age or gender of the people tagged to an event.
          2. The piechart appears in a seperate window, allowing users to multi-task and view both the statistics as well as the main
          application window.
        * Justification: In order for marketing departments to manage their marketing events, they need a way to view the statistics of events so as to evaluate their successes.
        * Highlights: This feature requires using the charts package of the JavaFX library, which is otherwise not used in this application.

  * **Testing**
    * Added unit tests for date, added date related tests for add and edit persons' features.
    * Added unit tests for StatisticsData and StatisticsDataList, added test suite for makeStats feature
  * **Refactoring** Refactored the old `add` command into the `addPerson` command to distinguish from `addEvent` command.

* **Documentation**:
  * Contributions to the User Guide:
    * Added documentation for the `makeStats` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#generating-pie-charts-of-statistics-of-the-people-tagged-to-an-event-in-the-event-list--makestats)) feature.
    * Modified `addPerson` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#adding-a-contact-addperson))
      and `editPerson` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#editing-a-contact--editperson)) feature slightly to include new date of birth field.
  * Contributions to the Developer Guide:
    * Added implementation details of date of birth field in `addPerson` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/DeveloperGuide.html#add-date-of-birth))
      and `editPerson` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/DeveloperGuide.html#edit-date-of-birth)) feature.
    * Did overall final v1.4 reformat of diagrams and features section of developer guide (Pull request [#254](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/254))

* **Contributions to team-based tasks**:
  * Created the demo of product in V1.2.
  * Edited the landing page for V1.3.
  * Facilitated weekly meetings when Team Lead was not available.
  * Edited the landing page for V1.4.

* **Community**:
    * Review/Mentoring contributions:
      * PRs reviewed (with non-trivial review comments): [\#57](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/57),
      [\#93](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/93), [\#103](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/103).
    * Contributions beyond the project team:
      * Reported bugs and suggestions for other teams in the class: (examples: [\#385](https://github.com/AY2223S1-CS2103T-W16-2/tp/issues/385), [\#396](https://github.com/AY2223S1-CS2103T-W16-2/tp/issues/396),
      [\#404](https://github.com/AY2223S1-CS2103T-W16-2/tp/issues/404), [\#410](https://github.com/AY2223S1-CS2103T-W16-2/tp/issues/410))

