---
layout: page
title: Foo Shi Yu's Project Portfolio Page
---

### Project: Waddle

Waddle is an app for easy-to-use travel itinerary creation and management.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=cfsy&breakdown=true)


* **Enhancements Implemented**:
  * Implemented the following features:
    * New UI — Brand new UI for Waddle
      * Created new classes for UI elements
      * Implemented UI page change for `select` and `home` commands
      * (Pull requests [\#45](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/45), [\#83](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/83), [\#94](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/94), [\#96](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/96), [\#98](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/98), [\#124](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/124), [\#132](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/132))
    * `select` command — Allows the user to select an itinerary and enter the itinerary planning page
      * Created `SelectCommand` and `SelectCommandParser` classes
      * (Pull requests [\#32](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/32), [\#80](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/80))
    * `home` command — Allows the user to return to the home page
      * Created `HomeCommand` class
      * (Pull request [\#32](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/32))
    * `free` command — Lists the vacant time slots in an itinerary
      * Created `FreeCommand` class
      * (Pull request [\#103](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/103))
    * `copy` command — Copies the itinerary in a text format into the user's clipboard
      * Created `CopyCommand` class
      * (Pull request [\#127](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/127))
  * Created the following supporting classes:
    * `StageManager` — class to keep track of the current page and selected itinerary (Pull request [\#32](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/32))
    * `Day` — class to encapsulate a day in an itinerary (Pull requests [\#75](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/75), [\#78](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/78))
    * `StartTime` — class to encapsulate a starting time (Pull request [\#79](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/79))
    * `Text` — class to abstract text formatting related tasks (Pull request [\#127](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/127))
  * Created the following UI related classes:
    * `ItemCard` — class for an item card
    * `ItemListPanel` — class for a list of item cards
    * `ItemGroupCard` — class for an item group which includes a label and list of item cards
    * `ItemGroupListPanel` — class for a list of item groups 
    * (Pull requests [\#83](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/83), [\#94](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/94), [\#96](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/96))
  * Completed the following miscellaneous tasks:
    * Implement logic for time conflict checks when planning items and editing itineraries/items (Pull request [\#134](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/134))
    * Implement 2 decimal place representation of floats for budget and cost (Pull request [\#195](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/195))
    * PED bug fixes (Pull request [\#194](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/194))
  * Wrote tests in the following pull requests
    * Pull requests [\#203](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/203), [\#220](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/220), [\#224](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/224), [\#233](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/233), [\#236](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/236), [\#243](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/243)


* **Documentation**:
  * User Guide:
    * Wrote the content for the following commands
      * `add (itinerary)`, `add (item)`
    * Wrote the Notes section for the following commands
      * `add (itinerary)`, `add (item)`, `edit (itinerary)`, `edit (item)`, `plan`, `copy`
    * (Pull requests [\#16](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/16), [\#132](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/132))
  * Developer Guide:
    * Updated the original AB3 diagrams to correctly reflect our application
    * Created the following diagrams
      * `ItineraryClassDiagram`, `PlanSequenceDiagram`, `PlanSequenceDiagram2`
    * Wrote the content for the following sections
      * `StageManager` under the Architecture section
      * Implementation of the `Plan/Unplan`, `Edit (item)`, and `Edit (itinerary)` features
    * (Pull requests [\#17](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/17), [\#62](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/62), [\#107](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/107))


* **Contributions to team-based tasks**:
  * Initiate weekly and ad-hoc team meetings
    * Plan the agenda
    * Lead discussions in implementation and design
    * Allocate work and set internal deadlines
  * Keep track of deadlines and deliverables
  * Managed releases `v1.3.1` and `v1.3.2` (2 releases) on GitHub


* **Tools**:
  * Used Java AWT Clipboard for copying itineraries to the user's clipboard (Pull request [\#127](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/127))
  * Integrated a third party library (commons-lang3) to the project to check for OS type before saving the PDF file (Pull request [\#131](https://github.com/AY2223S1-CS2103T-W11-4/tp/pull/131))

