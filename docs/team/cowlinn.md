---
layout: page
title: Colin's Project Portfolio Page
---

### Project: LongTimeNoSee(LTNS)

Long Time No See (LTNS) is a desktop task tracking app made for student financial advisors to manage their contact base and stay connected with their clients. Made simpler with an intuitive and minimalistic graphical user interface (GUI) and customised functionalities to pinpoint your needs, LTNS will enable you to stay close to your dearest clients!

Given below are my contributions to the project.

* **New Feature**: Added the main representation of our user's patrons, i.e: The `client` class.
  * Justification: This feature is required to use our application.
  * Highlights:  It encapsulates extensions from the original AB3's person model. You can store birthdays, add notes, tag people etc.
  * Credits: `LocalDate` Library of Java: https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html


* **New Feature**: Added a new `Event` model to encapsulate meetings a  Financial Advisor wll have with clients.
  * Justification: This feature is one of the new models of the application
  * Highlights: Meetings with a client are recording as an `Event`. Includes a `Description` and a day with start and end time.
    * Used by Reuben to make the `calendar` feature of the app.
  * Credits: `LocalTime` library of Java: https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html

* **Code contributed**: [https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cowlinn&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other]()

* **Project management**:
  * Helped to maintain issue tracker
  * Helped to organize group meetings, with agenda (together with Marcus)

* **Enhancements to existing features**:
  * `Add/Delete/Edit` a person
  * Saving both `Person` and `Event` into the addressBook

* **Documentation**:
  * User Guide:
    * Ported over original UG from Google Docs to Markdown, including cosmetic tweaks
    * Updated segments on adding a person into an AddressBook 
    * Updated documentation clarifying
    * Edited command summary
  * Developer Guide:
    * Added Activity diagrams for an execution of adding an event 
    * Added Extensions for existing class diagram of a person.

* **Community**:
  * PRs reviewed (with non-trivial review comments): 5
  * Reported bugs and suggestions for other teams in the class: Mainly for UserGuide
  * Gave more than average comments for all peer-review related activites: E.g: PE-D, iP feedback etc.

* **Tools**:
  * Git / Github
  * Intelli-J


