---
layout: page
title: Benjamin Sim's Project Portfolio Page
---

### Project: Duke The Market

Duke The Market is a desktop application used for managing customer contacts and events in department stores. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=benjamin-sim&breakdown=true)
<br>

* **Enhancements Implemented**:

  * **New Feature**: Added the ability to sort persons when listing them (Pull requests [#53](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/53), [#83](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/83))
    * What it does: Allows the user to sort all persons in the application by their name, gender or date of birth.

    * Justification: Sorting persons improves the user's productivity as he can easily locate people in the contact list.

    * Highlights: This feature required redesigning the original `list` command. Furthermore, designing how the sorting operation would function was challenging as it required detailed analysis of how data was handled and presented on the GUI.

  * **New Feature**: Added the ability to sort events when listing them (Pull request [#113](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/113))
    * What it does: Allows the user to sort all events in the application by their event title or date.

    * Justification: Sorting events improves the user's productivity as he can easily locate events in the event list.

    * Highlights: This feature required redesigning the `listEvents` command created by another team member.


  * **Testing**: Added substantial unit tests and integration tests for the sort persons feature, increasing test coverage from 48% to 50%. (Pull request [#192](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/192))

  * **Testing**: Added some unit tests for the sort events feature. (Pull request [#192](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/192))

  * **Refactoring**: Refactored the original `list` command to `listPersons` to distinguish it from the `listEvents` command.

  * **Misc**: Updated the help URL in the application to the product's user guide URL. (Pull request [#75](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/75))
<br><br>

* **Documentation**:
  * User Guide:
    * Added documentation for the `listPersons` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#listing-all-persons--listpersons)) and `listEvents` ([Link](https://ay2223s1-cs2103-f09-2.github.io/tp/UserGuide.html#listing-all-events-listevents)) features.

  * Developer Guide:
    * Added implementation details of the sort persons feature. [Link](https://ay2223s1-cs2103-f09-2.github.io/tp/DeveloperGuide.html#sort-persons)
    * Added use cases UC02, UC03, UC04, UC05 [Link](https://ay2223s1-cs2103-f09-2.github.io/tp/DeveloperGuide.html#use-cases)
<br><br>

* **Team-Based Tasks**:
  * Updated product name on product website. (Pull request [#14](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/14))
  * Managed milestones v1.1, v1.2, v1.3, v1.4 (Created and closed milestones, created issue tracker labels, etc.)
  * Handled product releases for v1.2 and v1.3 (including uploading and testing of JAR files) ([Link](https://github.com/AY2223S1-CS2103-F09-2/tp/releases))
  * Perfomed various miscellaneous team tasks such as updating the JAR filename, enabling assertions, updating team's GitHub repo URL on the product website. (Pull request [#75](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/75))
<br><br>

* **Community**:
  * PRs reviewed (with non-trivial review comments):
  [#205](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/205),
  [#103](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/103),
  [#74](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/74#pullrequestreview-1143187992),
  [#55](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/55#pullrequestreview-1139049629),
  [#52](https://github.com/AY2223S1-CS2103-F09-2/tp/pull/52#pullrequestreview-1136739682)

