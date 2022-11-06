---
layout: page 
title: CeereeC's Project Portfolio Page
---

## Project: Travelr

Travelr allows you to plan trips around the activities in your bucket list. You will be able to keep track of your
bucket list, travel dates, locations, and itineraries, all within the same app!

## Contributions

Given below are my contributions to the project.

* **New Feature**: Added the ability to display an event's details in the command box.
    * What it does: allows the user to view the full title and description of an event.
    * Justification: Event details that are too long are truncated by the UI. This feature allows users to see the full details.
    
* **New Feature**: Added the ability to display a trip's details in the command box.
    * What it does: allows the user to view the full title, description, location and date of a trip.
    * Justification: Trip details that are too long are truncated by the UI. This feature allows users to see the full details.

* **New Feature**: Added a resetView function to reset to default view after running certain commands
    * What it does: resets the view to show all trips and all events in the bucket list in the UI
    
* **New Feature**: Added the location and date fields to Trip
    * Incorporated the location and date fields in the storage and in the UI
    
* **New Feature**: Implemented the delete event command    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=ceereec&breakdown=true)

* **Fix** : Ensured DuplicateEventExceptions and DuplicateTripExceptions are caught when reading from data file

* **Fix** : Fix add-et commands referencing wrong lists


* **Enhancements to existing features**:
    * Refactored AB3 People class to Travelr Trip class
    * Refactored AddressBook and AB3 references to Travelr
    * Changed data file from addressbook.json to travelr.json
    * Added new Sample data
    * Modified command outputs to be more specific

* **Documentation**:
    * User Guide:
        * Added notes about UI
        * Added "Duplicates" and "Multiple inputs" to notes about command format
        * Added documentation for the features `display` and `display-e`
        * Modified the table of contents to use {:toc}, so it builds automatically
        * Added Glossary
    * Developer Guide:
        * Updated the Model, Logic, Common Classes Segment [\#218]()
        * Added sequence diagram for implementation of the `completed` feature.
        * Added usecase for completed and summary commands [\#219]()
        * Updated the appendix for manual testing
        * Added usecase for delete event
        * Updated Glossary and Non-Functional Requirements
        * Added user stories
    * Updated index.md

* **Tools**:
    * Integrated codecov to the project
