---
layout: page
title: Chen Hung-Yu's Project Portfolio Page
---

### Project: Cobb

Cobb is a Contact and Property Management System that aims to help property agents and brokers manage their customer base and properties, as well as match and gain actionable insights from stored data.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=jchilling&breakdown=true)
- **Enhancements to existing features**:
  * Add `Priority` field to `Buyer` [#111](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/111)
    * What it does: Represents the priority for buyer.
    * Justification: In real life, different clients'(buyers) case have different urgency. Useful feature to let the user tag a buyer's priority. 
  * Add `LocalDateTime` field to `Buyer` and `Property` [#148](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/148)
    * What it does: The application automatically captures the entry time for `Buyer` and `Property` by a `LocalDateTime` object.
    * Justification: This enhancement enables a simpler implementation of the sort commands and preserves the original list order by allowing users to sort by entry time.
  * Add ability to filter buyers by `Priority` [#125](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/125)
    * What it does: Enable `filterbuyers` command to filter buyers' `Priority` in ascending or descending order.
- **New Features**:
  * Implementation of `seedu.address.model.property` package [#60](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/60), [#72](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/72)
    * What it does: Represents a property and its associations as classes within the Cobb codebase.
    * Justification: The package follows the Object-Oriented Programming paradigm that is used throughout Cobb.
  * Implementation of `listprops` command [#85](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/85)
    * What it does: Display all properties stored in the application on the Property List panel. 
    * Justification: Command for user to return to original property list after filter commands.
  * Implementation of `deleteprop` command [#85](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/85)
    * What it does: Delete the property at given index.
    * Justification: Command for user to modify the property list.
  * Implementation of `sortprop` command [#148](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/148)
    * What it does: Sort properties according to the specified attribute.
    * Justification: Useful feature for users to sort the property list by a specified attribute in an ascending/descending.  
  
- **Documentation**:
  * User Guide: 
    * Updated documentations for `findbuyers` and `findprops` commands
    * Added documentations for `sortbuyers` and `sortprops` commands 
    * Added documentation for `clear` command

  * Developer Guide:
    * Updated all UML diagrams to reflect Cobb's design architecture and `UI`, `Logic`, `Storage`, `Model` components
    * Updated previous outdated descriptions to reflect current state of Cobb
    * Updated all prior UML diagrams to use plantUML
    * Updated NFRs 
    * Added "Create a property" section under "Implementation"
    * Modified "Create a buyer" section under "Implementation"
    
- **Contributions to team-based tasks**:
    * Enabled assertions in Gradle
    * Managed issue tracker

- **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial comment):
    [#68](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/68),
    [#83](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/83),
    [#137](https://github.com/AY2223S1-CS2103T-F12-1/tp/pull/137)
  * Reported bugs during PE-D accepted:
    [#157](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues/157),
    [#159](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues/159),
    [#163](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues/163),
    [#172](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues/172),
    [#175](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues/175),
    [#179](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues/179),
    [#186](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues/186),
    [#192](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues/192),
    [#193](https://github.com/AY2223S1-CS2103T-T13-1/tp/issues/193)

