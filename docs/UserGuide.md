# User Guide
Travelr is a desktop app for managing trips and events, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Travelr can get your trip management tasks done faster than traditional GUI apps.
- [Quick start](#quick-start)
- [Features](#features)
   * [Adding events: `add -e`](#adding-events-add--e)
   * [Deleting events: `delete -e`](#deleting-events-delete--e)
   * [Viewing events list: `list -e`](#viewing-events-list-list--e)
   * [Adding trips: `add -t`](#adding-trips-add--t)
   * [Viewing trips list: `list -t`](#viewing-trips-list-list--t)
   * [Adding events to trips: `add -et`](#adding-events-to-trips-add--et)
   * [Saving data](#saving-data)
   * [Exiting the program: `bye`](#exiting-the-program-bye)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `travelr.jar` from [here](https://github.com/AY2223S1-CS2103T-W17-1/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Travelr.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list -e`** : Lists all events.

   * **`add`**`-e Sightseeing/20-06-2030/Portugal/Visit Mountains` : Adds an event with the respective date, location, and activity into your event list.

   * **`delete -e`**`3` : Deletes the 3rd event shown in the current event list.

   * **`bye`** : Exits the app.
6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features
**Notes about the command format:**
Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `add -t TITLE`, TITLE is a parameter which can be used as `add -t Switzerland Trip`.

Items in square brackets are optional.
e.g NAME [TAG] can be used as John Doe/Friend or as John Doe.

The ‘/’ character is used to separate parameters supplied by the user.
E.g. in `add -e TITLE DATE LOCATION DESCRIPTION`, ‘/’ is used to separate the four parameters supplied which can be used as `add -e Sightseeing/20-06-2030/Taiwan/Visit mountains`.

Extraneous parameters for commands that do not take in parameters (such as bye) will be ignored.
e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.

### Adding events: `add -e`
Adds an event to the events list.

Format: `add -e TITLE DATE LOCATION DESCRIPTION`

Examples:
- `add -e Skydiving/23-04-2024/Spain/Skydiving with crew`
- `add -e Sailing/20-02-2025/Hungary/Sail in the Danube River`

### Deleting events: `delete -e`
Deletes the specified person from the events list.

Format: `delete -e INDEX`
- Deletes the person at the specified INDEX.
- The index refers to the index number shown in the events list.
- The index must be a positive integer 1, 2, 3, …

Examples:
- `list -e` followed by `delete 2` deletes the 2nd event in the events list.

### Viewing events list: `list -e`
Shows a list of all events added.

Format: `list -e`

### Adding trips: `add -t`
Adds a trip to the trip list.

Format: `add -t TITLE`

Examples:
- `add -t Trip to Iceland`

### Viewing trips list: `list -t`
Shows a list of all trips added.

Format: `list -t`

### Adding events to trips: `add -et`
Adds the specified event to the specified trip.

Format: `add -et EVENT_INDEX TRIP_INDEX`
- Adds the event at the specified EVENT_INDEX
- Event is added to the trip at the specified TRIP_INDEX
- The index refers to the index number shown in the events and trips list.
- The index must be a positive integer 1, 2, 3…

Examples:
- `add -et 2 3` adds the 2nd event in the events list to the 3rd trip in the trips list.

### Saving data
Travelr data are saved locally automatically after any command that changes the data. There is no need to save manually.

### Exiting the program: `bye`
Exits the program.
Format: `bye`

