---
layout: page
title: Colin's Project Portfolio Page
---

### Project: LongTimeNoSee(LTNS)

Long Time No See (LTNS) is a desktop task tracking app made for financial advisors to manage their contact base and stay connected with their clients. Made simpler with an intuitive and minimalistic graphical user interface (GUI) and customised functionalities to pinpoint your needs, LTNS will enable you to stay close to your dearest clients!

Given below are my contributions to the project.

* **New Feature**: Added the main representation of our user's patrons, i.e: The `Client` class. This is an extension from `Person`.
  * Justification: This feature allows the user to store information specific to financial advisors.
  * Highlights:  It encapsulates extensions from the original AB3's person model. You can store birthdays, add income, RiskAppetite levels etc.
      * Updated Commands to add/edit/delete a new client


* **New Feature**: Added the main representation of a meeting with the user and his `Client` i.e: The `Event` class.
  * Justification: This feature allows the user to keep track of his meetings with his clients.
  * Highlights: Meetings with a client are recording as an `Event`. Includes a `Description` and `Date` with start and end times.
    * Used by Reuben to make the `calendar` feature of the app.
  * Credits: Similar model implementation to AB3's `Person` class

* **Code contributed**: [Link here!](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cowlinn&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

<div style="page-break-after: always;"></div>

* **Project management**:
  * Number of PRs reviewed and merged: 18
  * Main reviewer for Marcus
  * Helped to maintain issue tracker
  * Helped to organize group meetings, with agenda

* **Enhancements to existing features**:
  * `Add/Delete/Edit` a Client
  * `Add/Delete` an Event
  * Updated save functionality for `Event` 
  * Updates to Parser to parse inputs for both the updated `Person` and `Event` classes.

* **Documentation**:
  * User Guide:
    * Ported over original UG from Google Docs to Markdown, including cosmetic tweaks
    * Updated segments on adding/deleting/editting a person.
    * Added segments on adding/deleting events
    * Updated segments on viewing a complete list/filtered list for both event and client
    * Edited command summary
    * Updated FAQs for exceptions / conditions when adding an `Event`.
    
  * Developer Guide:
    * Added Activity diagrams for an execution of adding an event 
    * Added Extensions for existing class diagram of a person to include new attributes.
    * Added Use Cases for Person/Event
    * Updated Feature Summary for `Add/Delete` for Client / Event

* **Community**:
  * PRs reviewed (with non-trivial review comments): 6
    * Specific PR examples: #193, #81, #203, #172
  * Bugs reported: Mainly for UserGuide / Developer Guide / Testing SUTs
  * Gave more than average comments for all peer-review related activities: E.g: PE-D, iP feedback etc.

* **Tools**:
  * Git / Github
  * Intelli-J
  * SourceTree


