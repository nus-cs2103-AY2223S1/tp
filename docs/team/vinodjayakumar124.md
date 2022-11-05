---
layout: page
title: Vinod s/o Jaya Kumar's Project Portfolio Page
---

### Project: Duke The Market

Duke The Market is a desktop application used for managing customer contacts and events in department stores.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**:
    * [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=Vinodjayakumar124&breakdown=true)

* **Enhancements implemented**:
    * **New Class** Added the `Event` class (Pull request [#57](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/57))
  and `EventTitle` and `Purpose` attributes (Pull request [#124](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/124)).
      * What is does: This class allows users to store information about marketing events within the application.
      * Justification: Since the application aims to help department store marketers manage department store events,
        having an event class that stores key information regarding each marketing event (such as event title, date, time
        and purpose) is crucial. The classes built for the event attributes allow for additional validation checks that are
        unique to each class.
      * Highlights: Jackson was used to save the `Event` into JSON format.

    * **New Features** Added the `addEvent` (Pull request [#57](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/57)), 
  `deleteEvent` (Pull request [#57](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/57)) and `editEvent` features 
  (Pull request [#99](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/99)).
      * What is does:  
      1. Allows users to add marketing events to the application with `addEvent`
      2. Allows users to edit marketing events in the application with `editEvent`
      3. Allows users to delete marketing events from the application with `deleteEvent`
      * Justification: Marketers who use the application would need to add events in order to keep track of their 
      upcoming events on it. Also, when changes to marketing events occur such as a change in time, they need to be 
      able to edit those details. Lastly, should the entire event be cancelled, they need to able to delete the entire 
      event. 

    * **Refactoring** Refactored the original `edit` command into the `editPerson` command to distinguish from 
  `editEvent` command. (Pull request [#125](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/125)).

* **Documentation**:
    * Contributions to the User Guide:
        * Added documentation for `addEvent` (Pull request [#25](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/25)), 
      `editEvent` (Pull request [#133](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/133)) 
      and `deleteEvent` (Pull request [#25](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/25)).
        * Modified `edit` to `editPerson` (Pull request [#133](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/133)).
    * Contributions to the Developer Guide:
        * Added implementation details for `addEvent` and `deleteEvent` commands (Pull request [#99](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/99)).

* **Contributions to team-based tasks**:
    * Did smoke test for product releases v1.2.
    * Created README.md for our product (Pull request [#108](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/108)).
    * Created screenshots for demo of product in V1.3.

* **Review/Mentoring contributions**:
  * PRs reviewed (with non-trivial review comments):
    [#33](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/33),
    [#132](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/132),
    [#36](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/36#pullrequestreview-1125497674),
    [#113](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/113#pullrequestreview-1154359283),
    [#128](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/128)

* **Contributions beyond the project team**:
  * Reported bugs and suggestions for other teams in the class:
    [#151](https://github.com/AY2223S1-CS2103T-T09-3/tp/issues/151),
    [#156](https://github.com/AY2223S1-CS2103T-T09-3/tp/issues/156),
    [#171](https://github.com/AY2223S1-CS2103T-T09-3/tp/issues/171),
    [#160](https://github.com/AY2223S1-CS2103T-T09-3/tp/issues/160),
    [#178](https://github.com/AY2223S1-CS2103T-T09-3/tp/issues/178)
