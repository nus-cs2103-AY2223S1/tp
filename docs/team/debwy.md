---
layout: page
title: debwy's Project Portfolio Page
---

### Project: NUSEatWhere

NUSEatWhere is a Command Line (CLI) application which helps you search for the available food options in 
NUS and thus make an informed decision on where to eat.

Given below are my contributions to the project.

* **New Features**: 
    * Favourite/Unfavourite commands (a simpler, standardized, tag command)
    * (Now removed, v1.2 - v1.3) Optional fields: Allowed eateries to exist without a Phone

* **Code contributed**: 
    * Can be found on the [team project code dashboard](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=debwy&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Team Tasks**:
    * Refactored all classes under `Model` and part of related classes (e.g. part of Storage, UI, etc.) to remove references to address book 3
    * Changed fields under `Eatery` (used to be `Person`) to better suit the new product, examples being:
      * Eateries are now "the same" if they share the same name _and_ location (criteria differs from a person)
      * Updated regex checks (`Email` was changed to `Cuisine`, hence a '@' is not mandated)
    * v1.3: Changed Phone to Price as Price was deemed as more important
    * Semi-regularly maintained the issue tracker (e.g. added & assigned most of the v1.3 issues)
    * Managed the v1.3 jar release

* **Enhancements to existing features**:
    * Changed add to suit the new fields (add can either take in a Phone or not)
    * Changed storage (save/load) to suit the new fields (and the new optional field)
    * Updated test cases for add (and related tests, e.g. Storage) to target the new regex checks

* **Documentation**:
    * User Guide (UG)
        * v1.1: Formatted (headers, command format) and added descriptions to all commands (transferred and added onto the UG draft made on Google Docs)
        * v1.3: Updated whole UG to match features implemented/changed in v1.2 & v1.3
          * (Now removed due to outdated UI) Took screenshots of all the features 
          * (Now removed due to outdated UI) Made before/after diagrams for add/delete & tag/untag
        * v1.4: Fixed part of the issues with the UG pointed out during the PE dry run
    * Developer Guide (DG)
        * v1.4: Wrote an introduction (user profile, value proposition, etc.), did up the table of contents, standardized image & caption formatting, 
      tweaked formatting (added/changed headers, bold/italics to make some text easier to read)
        * v1.4: Added implementations for fav/unfav and an optional field (did up their diagrams as well)

* **Review/Mentoring**:
    * Authored 11 user-story related issues
    * Reviewed 15 pull requests 

