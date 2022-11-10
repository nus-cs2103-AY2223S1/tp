---
layout: page 
title: Ling Guan Ming's Project Portfolio Page
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

* **Fix**: Ensured DuplicateEventExceptions and DuplicateTripExceptions are caught when reading from data file

* **Fix**: Fix add-et commands referencing wrong lists


* **Enhancements to existing features**:
    * Refactored AB3 People class to Travelr Trip class [\#27](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/27)
    * Refactored AddressBook and AB3 references to Travelr [\#173](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/173)
    * Changed data file from addressbook.json to travelr.json [\#173](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/173)
    * Added new Sample data [\#190](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/190)
    * Modified command outputs to be more specific 
    * Wrote additional tests for existing features to increase coverage by 10% 

* **Documentation**:
    * User Guide:
        * Added documentation for the commands `display`, `display-e`, `help` [\#99](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/99), [\#172](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/172)
        * Modified the table of contents to use {:toc}, so it builds automatically [\#175](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/175)
        * Added notes about UI [\#176](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/176)
        * Added "Duplicates" and "Multiple inputs" to notes about command format
        * Added Glossary
    * Developer Guide:
        * Added usecase for delete event [\#68](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/68)
        * Updated the Model, Logic, Common Classes Segment [\#218](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/218), [\#221](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/221)
        * Added usecase for completed and summary commands [\#219](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/219)
        * Added sequence diagram for implementation of the `completed` feature. [\#223](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/223)
        * Updated the appendix for manual testing [\#225](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/225)
        * Updated Glossary and Non-Functional Requirements 
        * Added user stories
    * Updated index.md [\#220](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/220)

* **Tools**:
    * Integrated codecov to the project
    * Used PlantUML to add UML diagrams in DG
