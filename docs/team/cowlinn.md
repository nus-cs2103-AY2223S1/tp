---
layout: page
title: Colin's Project Portfolio Page
---

### Project: LongTimeNoSee(LTNS)

Long Time No See (LTNS) is a desktop task tracking app made for financial advisors to manage their contact base and stay connected with their clients. Made simpler with an intuitive and minimalistic graphical user interface (GUI) and customised functionalities to pinpoint your needs, LTNS will enable you to stay close to your dearest clients!

Given below are my contributions to the project.

* **Extension to previous feature**: Added the main representation of a financial Advisor's Clients. [#54](https://github.com/AY2223S1-CS2103T-W13-2/tp/pull/54)
  * Justification: This feature allows the user to store information specific to financial advisors.
  * Highlights:  It encapsulates extensions from the original AB3's person model. 
    * The user can store a `Birthday`, update `RiskAppetite` levels and add a client's yearly `Income`.
    * Updated Commands to add/edit/delete the new person class. 


* **New Feature**: Added the main representation of a meeting with the user and his `Client` i.e: The `Event` class. [Relevant commits here!](https://github.com/AY2223S1-CS2103T-W13-2/tp/pull/67/commits/0c65c47f6bc2545f4fd544a774ac2eb4e1bcbf6a)
  * Justification: This feature allows the user to keep track of his meetings with his clients.
  * Highlights: Meetings with a client are recording as an `Event`. Includes a `Description` and `Date` with start and end times, encapsulated as a `Duration`.
    * Used by Reuben to make the `calendar` feature of the app.
    * Added `EventListCard` and `EventListPanel` [here](https://github.com/AY2223S1-CS2103T-W13-2/tp/pull/67/commits/2c48ae531b0c107d97c02bfd54a395808254458b)
  * Credits: Similar model implementation style to AB3's `Person` class


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
  * Updated original `Person` test cases to fit updated attributes, as well as `Event` class tests. [#190](https://github.com/AY2223S1-CS2103T-W13-2/tp/pull/190)

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
    * Some PR examples: [#193](https://github.com/AY2223S1-CS2103T-W13-2/tp/pull/193), [#81](https://github.com/AY2223S1-CS2103T-W13-2/tp/pull/81), 
    [#203](https://github.com/AY2223S1-CS2103T-W13-2/tp/pull/203), [#172](https://github.com/AY2223S1-CS2103T-W13-2/tp/pull/172)
  * Bugs reported: Mainly for UserGuide / Developer Guide / Testing SUTs
  * Gave more than average comments for all peer-review related activities: E.g: PE-D, iP feedback etc.

* **Tools**:
  * Git / Github
  * Intelli-J
  * SourceTree


