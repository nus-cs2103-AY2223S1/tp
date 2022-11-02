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
    * New UI
      * Created new classes for UI elements
      * Implemented UI page change for `select` and `home` commands
    * `select` command
      * Created `SelectCommand` and `SelectCommandParser` classes
    * `home` command
      * Created `HomeCommand` class
    * `free` command
      * Created `FreeCommand` class
    * `copy` command
      * Created `CopyCommand` class
  * Created the following supporting classes:
    * `StageManager` class to keep track of the current page and selected itinerary
    * `Day` class to encapsulate a day in an itinerary
    * `StartTime` class to encapsulate a starting time
    * `Text` class to abstract text formatting related tasks
  * Created the following UI related classes:
    * `ItemCard` class for an item card
    * `ItemListPanel` class for a list of item cards
    * `ItemGroupCard` class for an item group which includes a label and list of item cards
    * `ItemGroupListPanel` class for a list of item groups
  * Completed the following miscellaneous tasks:
    * Implement logic for time conflict checks when planning items and editing itineraries/items
    * Fix `edit` command implementation to correctly copy unscheduled items to the new edited itinerary
    * Implement 2 decimal place representation of floats for budget and cost
    * 


* **Documentation**:
  * User Guide:
    * Wrote the content for the following commands
      * `add (itinerary)`, `add (item)`
    * Wrote the Notes section for the following commands
      * `add (itinerary)`, `add (item)`, `edit (itinerary)`, `edit (item)`, `plan`, `copy`
  * Developer Guide:
    * Updated the original AB3 diagrams to correctly reflect our application
    * Created the following diagrams
      * `ItineraryClassDiagram`
      * `PlanSequenceDiagram`
      * `PlanSequenceDiagram2`
    * Wrote the content for the following sections
      * `StageManager` under the Architecture section
      * Implementation of the `Plan/Unplan` feature
      * Implementation of the `Edit (item)` feature
      * Implementation of the `Edit (itinerary)` feature
      * Implementation of the `Export` feature
      * User stories
      * Part of the "Add an Itinerary" use case


* **Contributions to team-based tasks**:
  * Initiate weekly and ad-hoc team meetings
    * Plan the agenda
    * Lead discussions in implementation and design
    * Allocate work and set internal deadlines
  * Keep track of deadlines and deliverables

