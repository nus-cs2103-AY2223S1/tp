---
layout: page
title: Jamie's Project Portfolio Page
---

### Project: FoodWhere

**FoodWhere is a desktop application for managing your food stall addresses and reviews.** While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

Given below are my contributions to the project.

* **New Features**
  * Implemented `sfind` and `rfind` functions, to allow critics to search for existing stalls and reviews by name or tag
    * find stall by name ([#156] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/156))
    * find review by name ([#174] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/174))
    * find stall and review by tag ([#213] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/213))
* **Enhancements to logic component**
  * Implemented parser and command classes to parse FoodWhere commands
    * Modified AddCommandParser to SAddCommandParser to parse add stall commands ([#100] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/100))
    * Modified DeleteCommandParser to SDeleteCommandParser to parse delete stall commands ([#101] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/101))
    * Implemented RAddCommand and RAddCommandParser to parse add review commands ([#127] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/127)
    * Implemented RDeleteCommand and RDeleteCommandParser to parse delete review commands ([#128] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/128))
    * Modified AddressBookParser to parse FoodWhere commands ([#131] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/131))

* **Enhancements to new features**
  * Added error messages for any missing mandatory fields in RDeleteCommand and SDeleteCommand ([#220] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/220))
  * Handled bug caused by case insensitivity and additional user inputs by modifying all relevant classes such as `Address`, `Name`, `Tag` to handle different user inputs
* **Code Contributions**
*[Link to RepoSense] (https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jamietan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16)

* **Project Management**
  * Assisted in approving and merging Prs of implementations done by other teammates.

* **Documentation**
  * User Guide
    * Added RAddCommand and SAddCommand sections ([#31] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/31/files))
    * Updated glossary section of User Guide ([#40] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/40/files))
    * Added RFindCommand and SFindCommand sections ([#230] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/230))
  * Developer Guide
    * Wrote on implementation of Logic component ([#176] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/176))
    * Added use cases for sfind and rfind commands ([#261] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/261)
    * Wrote on implementation of find functions for stall and review ([#315] (https://github.com/AY2223S1-CS2103-W14-2/tp/pull/315))
