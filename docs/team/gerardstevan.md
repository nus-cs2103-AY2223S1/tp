---
layout: page 
title: Stevan Gerard Gunawan's Project Portfolio Page
---

## Project: Travelr
Travelr allows you to plan trips around the activities in your bucket list. You will be able to keep track of your bucket list, travel dates, locations, and itineraries, all within the same app!

- **Code contributed:** [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=gerardstevan&breakdown=true)

## Contributions
- **New Feature:** Implement the package main.java.seedu.travelr.model.list 
    - **What it does:** It contains the interface EventList and the classes BucketList and Itineraries which holds list of events
    - **Justification:** Trips needed an itinerary field to hold all its events and same goes for BucketList.
    
- **New Feature:** Added `AddEventToTrip` command and parser to move an event from bucket list to a trip [\#39](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/39).
    - **What it does:** Remove the specified event from bucket list and add it to the specified trip's itinerary.
    - **Justification:** This allows users to add events to their trip to make trip planning possible.
- **New Feature:** Added the `DeleteEventFromTrip` command and parse to move an event from a trip to the bucket list [\#39](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/39).
    - **What it does:** Remove the specified event from the specified trip's itinerary and add it to the bucket list.
    - **Justification:** This is implemented to complement the `AddEventToTrip` command.
- **New Feature:** Added the `Mark` command and parser to mark a trip and its itineraries as done [\#66](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/66).
    - **What it does:** Mark a trip and its itineraries as done. 
    - **Justification:** The trip would be marked as done to avoid deleting it when its done.
- **New Feature:** Added the `Unmark` command and parser to mark a done trip as not done [\#66](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/66). 
    - **What it does:** Mark a done trip as not done.
    - **Justification:** This is implemented to complement and  to provide some kind of undo for the `Mark` command.

* **Enhancements to existing features**:
    * Refactored the storage component to save isDone value of Trips [\#66](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/66).
    * Changed the help window to show a table of commands [\#102](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/102), [\#111](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/111).

### Testing
- Add test cases for AddEventToTripCommand, DeleteEventFromTripCommand, MarkTripDoneCommand, and UnmarkDoneTripCommand [\#230](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/230).

### Documentation
#### User Guide
- Added documentation and relevant examples for `add-et`, `delete-et`, `mark`, and  `unmark` commands [\#92](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/92).

#### Developer Guide
- Added implementation details and sequence diagram for the `add-et` command [\#224](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/224).
- Added Use Cases, User Stories, and target user profile [\#23](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/23), [\#16](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/16).
- Added Product scope [\#23](https://github.com/AY2223S1-CS2103T-W17-1/tp/pull/23).
