---
layout: page
title: Charles Lim's Project Portfolio Page
---

# Project: NUSEatWhere

NUSEatWhere is a Command Line (CLI) application which helps you search for the available food options in NUS
and thus make an informed decision on where to eat.

Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=charleslimjh&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

**Enhancements to existing features**:

* Helped refactor the `Command` classes during transition from `AB3` to `foodguide`
* Implement additional `find` commands (`findTag`, `findLocation`, `findCuisine`) and
some relevant test cases for `containsKeywordsPredicate` classes
* Helped generate data for eateries in NUS
* Improved on warning messages for invalid commands in `find -r` feature

**Project management**:

Tested and reviewed 25 PRs in total. Some notable PRs are linked below.

* v1.1
    * 4 PRs on updating documentation (#7, #9, #11, #19)
* v1.2
    * 2 PRs on refactoring (#33, #36)
    * 1 PR on `help` feature [(#41)](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/41)
    * 3 PRs on `add` [(#42)](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/42),
      `tag` [(#43)](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/43),
      and `untag` [(#44)](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/44)
* v1.3
    * 2 PRs on general `foodguide` improvements (#54, #55)
    * 2 PRs on documentation (#65, #66)
    * 1 PR on default data (#75)
    * 2 PRs on `find -r` feature (#69, [#70](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/70), #71)
    * 1 PR on `fav`/`unfav` feature (#74)
* v1.4
    * 1 PR on UI changes (#150)
    * 3 PRs on documentation ([#149](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/149), #156, #166)
    * 1 PR on `find` ([#148](https://github.com/AY2223S1-CS2103T-W11-1/tp/pull/148))
    * 1 PR on `help` (#168)

**Documentation**:

**User Guide**
* Edited hyperlinks for Table of Contents
* Updated front matters, improving on:
    * Introduction,
    * Table of Contents,
    * Using the User Guide,
    * First Glance (GUI),
    * Quick Start, and
    * Introduction to Food Guide's Features

**Developer Guide**
* Updated hyperlinks to link back to team repo instead of `AB3`
* Refactored some `AB3` code in DG to fit `foodguide` (e.g. People -> Eateries)
* Furnished details on search features, i.e. `find`, `findLocation`, `findPrice`, `findCuisine`, `findTags`

