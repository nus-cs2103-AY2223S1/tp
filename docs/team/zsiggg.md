---
layout: page
title: Zsigmond Poh's Project Portfolio Page
---

### Project: Cobb

Cobb is a desktop address book application for property agents. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **Code contributed**: 
[RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=zsiggg&breakdown=true)
* **Enhancements implemented**:
  * Navigate command history with arrow keys in command box
  * `addprop` command
  * `filterbuyers` and `filterprops` by characteristics
  * `-all` flags for `filterbuyers` and `filterprops`
  * `-strict` flag for `matchprops` and `matchbuyer`
* **Contributions to the UG**:
  * Added links (e.g. for Buyer List and Property List) to different sections in the document for readability
  * Added explanations for the filtered view of the buyer and property lists in FAQ, List Command and Filter Command sections
  * Updated command syntax and examples throughout the document to the current implementation, making sure that all
  examples work when pasted into the application
  * Added explanation for the navigation of command history using arrow keys in the Interface Layout section
* **Contributions to the DG**:
  * Created and updated UML diagrams (sequence and class diagrams) with PlantUML
    * CommandRetriever class diagram
    * CommandRetriever sequence diagram
    * MatchBuyerCommand sequence diagram
  * Added the implementation of the navigation of command history feature
  * Added the implementation of the match commands
  * Added the introduction section
  * Small updates to UML diagrams and writeups in the design section
* **Contributions to team-based tasks**:
  * Pull requests added:
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/227
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/222
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/216
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/215
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/151
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/42
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/113
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/121
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/119
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/128
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/146
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/145
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/147
  * Refactor `ArgumentTokenizer` and test cases to require a whitespace after the flag, to allow for flags with similar
  prefixes (e.g. `-ph` and `-p`)
  * Refactoring of `personModel` to `personBook` to `buyerBook` 
  * Refactoring all occurrences of `person` to `buyer`
  * Implemented `-fuzzy` flag for filter commands and `-strict` for match commands
  * Implemented ability to use up and down arrow keys to navigate command history
  * Implemented filter by characteristics (a part of the filter commands)
  * Added `PropertyBookStorage` and its associated JSON classes in `storage` package
  * Added `PropertyBook` in `model` package
  * Implemented `addprop`
  * Updated Kanban board for user stories on GitHub Projects to match how the stories were allocated to each milestone 
  * Set up CI for the team repository
* **Review/mentoring contributions**:
  * Pull requests reviewed:
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/229
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/217
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/152
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/147
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/142
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/128
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/125
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/119
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/110
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/96
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/83
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/72
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/70
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/53
    * https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/42
