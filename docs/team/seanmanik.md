# Sean Manik's Project Portfolio Page

## Project: Travelr
Travelr allows you to plan trips around the activities in your bucket list. You will be able to keep track of your bucket list, travel dates, locations, and itineraries, all within the same app!


## Contributions
Given below are my contributions to the project.

### Features
- Refactor: Refactored codebase to support Event commands
- **New Feature:** Modified the way events were being tracked, which allows for predicates to be easily applied.
   - **What it does:** Predicates can now be properly applied to the event lists, which allows for events to be easily filtered and displayed according to the requests of the user.
   - **Justification:** Previously, the way events were tracked did not support the application of predicates. This change allowed features such the `selected` and `completed` commands to be implemented in an efficient manner.
   - **Highlights:** The way that events are being tracked now allows for more flexibility, whereby future commands will be able to take advantage of the efficiency provided by the ability to use predicates to generate FilteredLists.

- **New Feature:** Added `completed` command to view all completed trips and events
   - **What it does:** Displays all completed trips and events to users.
   - **Justification:** This allows users to see what trips and events they have completed, which is information that users will request on a reasonably frequent basis.
- **New Feature:** Added the `select` command to display a selected trip's itinerary
   - **What it does:** Selects a trip, and then displays the events contained in the trip's itinerary to users.
   - **Justification:** This allows users to see what events they have added to the itinerary of a trip, which gives them an overview of what their trip will look like.
   - **Highlights:** Select is a key feature that will be used when users are planning trips.
- **New Feature:** Added `view` command to view all events and trips 
   - **What it does:** Displays all events and trips to users.
   - **Justification:** This allows users to see what events and trips they have added to Travelr thus far. Having an overview of every event and trip can help users in their planning of future trips, as well as evaluation of past trips.
- **New Feature:** Modified the UI to display icons for Events, Trips, Dates, Locations, and Completion Status.
   - **What it does:** Enhances the overall look of the application, which makes Travelr much more visually appealing to use.
   - **Justification:** UI is key when it comes to keeping users engaged, and thus the addition of these icons helps to make the navigation of the app more fun.
   - **Highlights:** Icons are all selectively picked to ensure that they match each other in terms of art style, aesthetics, and colour scheme.

- New Feature: Modified the UI to display both events and trips
   - **What it does:** Events and trips can now be both displayed simulaneously, side by side.
   - **Justification:** This is highly necessary, as the manipulation of events and trips are both equally important features of our application.
   - **Highlights:** UI is designed in the most user friendly way possible, to provide users with the most information at a single glance.
- New Feature: Modified the UI to display an Events and Trips Label
   - **What it does:** Above the events and trips list, there is now a label which tells users which list they are looking at.
   - **Justification:** This enhances the user experience, as they are now able to differentiate between the two lists shown due to the provision of a visual indicator.
- New Feature: Modified the GUI colour scheme to coordinated blue colour scheme
   - **What it does:** The new colour scheme gives Travelr a unique identity, and allows users to navigate an aesthetically pleasing application.
   - **Justification:** Having a unique colour scheme enhances the user experience as it gives a completely different visual experience as compared to using addressbook.
   - **Highlights:** Different colour schemes were trialed and tested, before we settled on blue.

### Documentation
#### User Guide
- Changed product summary for application
- Added documentation and relevant examples for `add`, `list-e`, `delete`, `select`, `view`, and `completed` commands
- Added screenshots commands `mark`, `select`, `add-et`, `display`, `display-e`, add`, and `add-e
- Reformatted User Guide to accommodate more components such as Use Cases, Notes, and Info
- Recategorised and reorganised content page to make user guide more reader-friendly

#### Developer Guide
- Added implementation details, sequence diagram, and activity diagram for the `select` command
- Added implementation details for the `completed` command
- Updated the architecture diagram
- Updated the UI diagram
