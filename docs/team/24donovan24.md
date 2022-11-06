---
layout: page
title: Donovan Lee's Project Portfolio Page
---

### Project: Gim

Gim is a **desktop app for managing gym exercises, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Gim allows you to keep track your exercises and Personal Records in an efficient way.

Example usages:
* as a tracking tool to keep track of completed exercises
* as a tracking tool to keep track of your personal records
* as a workout generator to generate exercises based on your personal records

Given below are my contributions to the project.

* **New Feature**: Sort command ([PR #100](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/100))
    * What it does: Allows users to sort any displayed list of exercises by date of completion.
    * Justification: This feature is essential as sorting the exercises by date of completion allows users to better track their workout progression.
    * Highlights:
        * A defensive copy of the default ordering of exercises based off entry date has to be maintained to allow users to toggle between a sorted ordering and the default ordering.
        * Utilise Open-Closed Principle to accommodate other sorting orders should sorting criteria change in future.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=24donovan24&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**:
    * Updated the GUI colour scheme ([PR #111](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/111))
    * Added welcome message when application is launched ([PR #113](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/113))
    * Added Sets as a field of an Exercise ([PR #53](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/53), [PR #62](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/62), [PR #72](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/72))

* **Documentation**:
    * User Guide:
        * Added documentation for `:sort` command ([PR #125](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/125))
        * Added documentation for `:filter` command with some edits to existing documentation as per discussed by team ([PR #133](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/133))
        * FAQ ([PR #133](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/133))

    * Developer Guide:
        * Added implementation details for `:sort` command ([PR #101](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/101))
        * Added user stories, use cases and instructions for manual testing as per discussed by team ([PR #211](https://github.com/AY2223S1-CS2103T-T15-4/tp/pull/211))

* **Community**:
    * Reviewed Team Members' PRs.
    * Participate and contribute to weekly team meetings.

* **Tools**:
    * PlantUML: Creating UML diagrams.
    