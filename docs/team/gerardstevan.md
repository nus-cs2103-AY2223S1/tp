# Gerard Stevan's Project Portfolio Page

## Project: Travelr
Travelr allows you to plan trips around the activities in your bucket list. You will be able to keep track of your bucket list, travel dates, locations, and itineraries, all within the same app!

- **Code contributed:** https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=gerardstevan&breakdown=true

## Contributions
- **New Feature:** Implement the package main.java.seedu.travelr.model.list 
    - **What it does:** It contains the interface EventList and the classes BucketList and Itineraries which holds list of events
    - **Justification:** Trips needed an itinerary field to hold all its events and same goes for BucketList.
    
- **New Feature:** Added `AddEventToTrip` command and parser to move an event from bucket list to a trip.
    - **What it does:** Remove the specified event from bucket list and add it to the specified trip's itinerary.
    - **Justification:** This allows users to add events to their trip to make trip planning possible.
- **New Feature:** Added the `DeleteEventFromTrip` command and parse to move an event from a trip to the bucket list.
    - **What it does:** Remove the specified event from the specified trip's itinerary and add it to the bucket list.
    - **Justification:** This is implemented to complement the `AddEventToTrip` command.
- **New Feature:** Added the `Mark` command and parser to mark a trip and its itineraries as done.
    - **What it does:** Mark a trip and its itineraries as done. 
    - **Justification:** The trip would be marked as done to avoid deleting it when its done.
- **New Feature:** Added the `Unmark` command and parser to mark a done trip as not done, 
    - **What it does:** Mark a done trip as not done.
    - **Justification:** This is implemented to complement and  to provide some kind of undo for the `Mark` command.

* **Enhancements to existing features**:
    * Refactored the storage component to save isDone value of Trips .
    * Changed the help window to show a table of commands.
  
### Documentation
#### User Guide
- Added documentation and relevant examples for `add-et`, `delete-et`, `mark`, and  `unmark` commands

#### Developer Guide
- Added implementation details and sequence diagram for the `add-et` command
- Added Use Cases and User Stories.
- Added Product scope
