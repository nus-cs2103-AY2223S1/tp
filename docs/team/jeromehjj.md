---
layout: page
title: Jerome Hoo Jun Jie's Project Portfolio Page
---

## Overview

**REal-Time** is a desktop application made for **Real-Estate agents** to **manage client information,
schedule meetings, and track client offers and listings.**

It is written in Java and uses JavaFX to build the GUI for the application.

Given below are my contributions to the project.

## Summary of Contributions

* **Code contributed:** [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jeromehjj&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


* **Enhancements implemented:**
  * **New Feature:** `AddOfferCommand` and `AddOfferCommandParser`(Pull request [#46](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/46))
    * What it does: 
      * Allows users to add their offers made for a listing in the application. 
    * Highlights: 
      * Implemented the `Offer` class with new `Price` field so that prices can be added into an offer by
      the user. Added a `AddOfferCommandParser` to parse the input by the user when adding offers. 
      * Added `parsePrice` in `ParserUtil` class to parse prices that are valid
      * Implemented an auto-sorting feature when adding offers so that it is sorted by the `listingId` field.
      * Added necessary tests in `AddOfferCommandTest`, `AddOfferCommandParserTest`, `ParserUtilTest` and `PriceTest`(Pull request [#163](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/163))

  * **New Feature:** `EditOfferCommand` and `EditOfferCommandParser`(Pull request [#81](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/81))
    * What it does:
      * Allows users to modify their offers when needed
    * Highlights:
    * Implemented the `EditOfferDescriptor` class to create the `Offer` that is being edited to replace the targeted `Offer`
    * Added necessary tests in `EditOfferCommandTest`, `EditOfferCommandParserTest`, and `EditOfferDescriptorTest`(Pull request [#163](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/163))

  * **New Feature:** `DeleteOfferCommand` and `DeleteOfferCommandParser`(Pull request [#81](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/81))
    * What it does:
    * Allows users to delete their offers in the application
    * Highlights:
    * Added necessary tests in `DeleteOfferCommandTest` and `DeleteOfferCommandParserTest`(Pull request [#163](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/163))


* **Contributions to the User Guide:**
  * Added documentation for features: Adding an Offer, Editing an Offer, Deleting an Offer
  * Implemented the Glossary table
  * Implemented the Prefix Summary table with hyperlinks and details
  * Updated the Command Summary table with hyperlinks and details
  * Relevant pull request(s): [#163](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/163),
  [#155](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/155),
  [#87](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/87),
  [#74](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/74)



* **Contributions to the Developer Guide:**
    * Added **Implementation** details for features with UML sequence diagrams: **Add Offer, Edit Offer, Delete Offer**
    * Added **Use cases** for Offer related features: **Add an Offer, Edit an Offer, Delete an Offer**
    * Relevant pull request(s): [#163](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/163),
    [#74](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/74)


* **Contributions to team based tasks:**
  * Maintaining issue tracker on Github
  * Release management for `v1.3`
  * Updated UML Class and Sequence Diagrams in **Design** section to match current architecture of product([#163](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/163))
  * Enabled assertions
  * Renamed User Guide to match product([#42](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/42))


* **Review/mentoring contributions:**
  * Pull requests reviewed with comments: [#161](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/161),
  [#69](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/69),
  [#60](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/60),
  [#45](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/45)


* **Contributions beyond the project team:**
  * Bugs reported in other team's product: [Link to PED](https://github.com/jeromehjj/ped/issues)
