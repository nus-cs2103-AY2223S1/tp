## Overview
### Summary of Contributions

**Code contributed:** [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ama-chi&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

**Enhancements implemented:**

* New Feature: `AddListingCommand` and `AddListingCommandParser` ([#39](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/39),
  [#44](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/44), [#60](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/60))
  * What it does:
    * Allows users to add listings to Real-Time.
  * Highlight:
    * Implemented `Listing` Class ([#39](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/39))
    with a new field `ListingId`([#60](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/60)) so that the user can add id to their listings.
    * Added `parseListingId`([#60](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/60)) in `ParserUtil` class to parse ListingId.

* New Feature: Auto-sorting mechanism for all lists ([#60](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/60), [#84](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/84))
  * What it does:
    * Automatically sort the lists when a new entry is added.
  * Highlight:
    * Implemented the sorting mechanism in `add` method in `UniqueClientList`, `UniqueListingList`,
    `UniqueMeetingList` and `UniqueOfferList` ([#60](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/60), [#84](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/84))
    * Added `compareTo` to `Client`, `Listing`, `Meeting` and `Offer` classes so
    `UniqueClientList`, `UniqueListingList`, `UniqueMeetingList` and `UniqueOfferList` can
    sort the list everytime a new entry is added. ([#60](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/60), [#84](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/84))

* New Feature: Cascading-delete mechanism for lists. ([#89](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/89))
  * What it does:
    * Deleting entries that have related entries from other lists will have a cascading effect.
  * Highlight:
    * `DeleteClientCommand` will also delete the listings, meetings and offers related to the `Client` that is deleted.
    * `DeleteListingCommand` will also delete the offers and meetings related to the `Listing` that is deleted.

**Contributions to the UG:**
* Added documentation for features:
  * Client-related
    * Adding, Editing, Finding and Listing
  * Listing-related
    * Adding, Editing and Listing
  * Meeting-related
    * Adding
* Enchance the visual aspect and readability for entire UG.
* Added Command Format section to help user learn the commands.
* Relevant pull request(s): [#94](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/94),
  [#165](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/165), [#169](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/169), [#173](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/173)

**Contributions to the DG:**
* Added Implementation details for features with UML sequence diagrams:
  * Client-related
    * Add, Edit, Delete
  * Listing-related
    * Add, Edit, Delete
  * Meeting-related
    * Add, Edit, Delete
* Added Use cases for Offer related features:
  * Client-related
    * Add, Edit, Delete
  * Listing-related
    * Add, Edit, Delete
  * Meeting-related
    * Add, Edit, Delete
* Relevant pull request(s): [#176](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/176), [#180](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/180)


**Contributions to team based tasks:**
* Write DG implementation for Client, Listing and Meeting related command.
* Write use cases and user stories for DG.
* Create app's Logo.
* Facilitate meetings.
* Assigning work among the group.
* Refactoring the project to match the product name ([#161](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/161))
* Format the UG to increase readability. ([#169](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/169),
[#173](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/173))
* Added Test Cases ([#150](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/150), [#161](https://github.com/AY2223S1-CS2103T-W15-2/tp/pull/161)):
  * Client-Related
    * DeleteClientCommandTest
    * EditClientCommandTest
    * AddClientCommandTest
    * FindClientCommandTest
    * ClientTest
    * ViewClientListCommandTest
    * JsonAdaptedClientTest
  * Listing Related
    * DeleteListingCommandTest
    * EditListingCommandTest
    * ViewListingsCommandTest
    * EditListingDescriptorTest
    * JsonAdaptedListingTest
    * AddListingCommandTest
  * Offer-Related
    * JsonAdaptedOfferTest

**Review/mentoring contributions:**
* Merging and Review PRs
* Discussion providing feedback to teammates on implementation of app architecture.

**Contributions beyond the project team:**
* Bugs reported in other team's product: [Link to PED](https://github.com/hoang227/ped)
