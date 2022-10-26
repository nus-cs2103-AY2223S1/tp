---
layout: page
title: User Guide
---
# Waddle User Guide 🦆
Waddle is a **simple, no-frills travel planning application catered to people who love doing everything on their keyboards**. Waddle allows users to plan their travels in **3 simple steps**.
1. Create a trip
2. Add activities
3. Schedule

**That simple**.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents
* #### [Quick start](#Quick start)
* #### [Features](#Features)
* #### [FAQ](#FAQ)
* #### [Command summary](#Command summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `Waddle.jar` from [here](https://github.com/AY2223S1-CS2103T-W11-4/tp/releases/tag/v1.3.1).

3. Copy the file to the folder you want to use as the _home folder_ for Waddle.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all itineraries.

   * **`add`**`n/My Japan Trip` : Adds an itinerary named "My Japan Trip".

   * **`delete`**`1` : Deletes the 1st itinerary shown in the current list.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  - e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/My Japan Trip`.

* Items in square brackets are optional.<br>
  - e.g. `n/NAME [c/COUNTRY]` can be used as `n/My Japan Trip c/Japan` or as `n/My Japan Trip`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  - e.g. `[w/WADDLERS]…​` can be used as ` ` (i.e. 0 times), `w/me`, `w/friend 1 w/friend 2` etc.

* Parameters can be in any order.<br>
  - e.g. if the command specifies `c/CATEGORY d/DESCRIPTION`, `d/DESCRIPTION c/CATEGORY` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  - e.g. if you specify `d/Eat Ramen d/Aquarium`, only `d/Aquarium` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  - e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Commands on main page

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Creating a new itinerary: `add`

Adds an itinerary to Waddle.

Format: `add d/DESCRIPTION [c/COUNTRY] sd/START DATE du/DURATION [p/NUMBER OF PEOPLE] [b/BUDGET]`

* Adds a new itinerary with `DESCRIPTION` to the itinerary list.
* `START DATE` should be given in the format `yyyy-mm-dd`, and `DURATION` is the number of nights.
  - e.g. `sd/2022-12-10 du/10` would mean that the trip is from 10 Dec 2022 to 20 Dec 2022.
* `BUDGET` is in dollars ($) and can include cents.
  - e.g. `b/1000.50` is $1000 and 50¢.
* You cannot add an itinerary with the same description as an existing itinerary in the list.

Examples:
* `add d/My Japan Trip sd/2022-12-12 du/6`
* `add d/Germanyyyy c/Germany du/14 s/05/10/22 b/1000 p/4`

### Listing all itineraries : `list`

Shows a list of all itineraries in Waddle.

Format: `list`

### Editing the details of an itinerary: `edit`

Edits an existing itinerary in Waddle.

Format: `edit INDEX [d/NAME] [c/COUNTRY] [sd/START DATE] [du/DURATION] [p/NUMBER OF PEOPLE] [b/BUDGET]`

* Edits the itinerary at the specified `INDEX`. The index refers to the index number shown in the displayed itinerary list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `edit 1 du/15 sd/2022-10-04` Edits the duration and start date of the first itinerary to be `15` and `2022-10-04` respectively.
* `edit 2 c/India` Edits the country of the second itinerary to be `India`.

### Locating itineraries by name: `find`

Finds itineraries with names containing any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `india` will match `India`
* The order of the keywords does not matter. e.g. `Trip Japan My` will match `My Japan Trip`
* Only the name is searched.
* Only full words will be matched e.g. `Jap` will not match `Japan`
* Itineraries matching at least one of the provided keywords will be returned (i.e. `OR` search).
  - e.g. `find Japan Trip` will return `My Germany Trip`, since there is a match for the keyword  `Trip`.
* Use the [`list`](#listing-all-itineraries--list) command to see all itineraries again.

Examples:
* `find India` returns `My India Trip` and `India Expedition`
* `find India Trip` returns `My Japan Trip`, `My India Trip`, `India Expedition`<br><br>
  ![result for 'find trip'](images/findTripResult.png)

### Deleting an itinerary : `delete`

Deletes the specified itinerary from Waddle.

Format: `delete INDEX`

* Deletes the itinerary at the specified `INDEX`. The index refers to the index number shown in the displayed list of itineraries.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd itinerary in Waddle.
* `find Japan` followed by `delete 1` deletes the 1st itinerary in the results of the `find` command.

### Clearing itineraries : `clear`

Deletes all itineraries in Waddle.

Format: `clear`

### Selecting an itinerary: `select`

Enters the [item planning stage](#commands-in-item-planning-stage) for the selected itinerary.

Format: `select INDEX`

* Selects the itinerary at the specified `INDEX`. The index refers to the index number shown in the displayed list of itineraries.
* The index **must be a positive integer** 1, 2, 3, ...​

Examples:
* `select 1`

### Commands in item planning stage

### Adding an item: `add`

Adds an item to the list of items.

Format: `add d/DESCRIPTION [p/PRIORITY] [c/COST] [du/DURATION]`

* Adds a new item with `DESCRIPTION` to the unscheduled item list.
* The default `PRIORITY` is `1`, while default `COST` and `DURATION` are both 0.
* `COST` is in dollars ($) and can include cents.
  - e.g. `b/100.20` is $100 and 20¢.
* `DURATION` is in _minutes_.
  - e.g. `du/100` is 100 minutes (or 1 hour and 40 minutes).
* You cannot add items with the same description as an existing item in the item list.

Examples:
* `add d/Go to the Louvre s/2022-12-12 du/1`
* `add d/Skiing c/Germany du/14 s/05/10/22 b/1000 p/4`

### Editing the details of an item: `edit`

Edits an existing item in the item list.

Format: `edit INDEX [d/DESCRIPTION] [p/PRIORITY] [c/COST] [du/DURATION]`

* Edits the item at the specified `INDEX`. The index refers to the index number displayed in either the unscheduled item list, or the day list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples: 
* `edit 1 d/Go skiing` would edit the description of the 1st item in the unscheduled item list to be `Go skiing`.
* `edit 2.2 p/3 c/100` would edit the priority and cost of the 2nd item in the Day 2 item list to be `3` and `100` respectively.

### Deleting an item: `delete`

Deletes an existing item in the item list.

Format: `delete INDEX`

* Deletes the item at the specified `INDEX`. The index refers to the index number displayed in either the unscheduled item list, or the day list.

Examples:
* `delete 1` would delete the 1st item in the unscheduled item list.
* `delete 2.1` would delete the 1st item in the Day 2 item list.

### Scheduling an item: `plan`

Schedules an item in the unscheduled item list.

Format: `plan INDEX d/DAY NUMBER st/START TIME`

* Schedules the item at the specified `INDEX`. The index refers to the index number displayed in the unscheduled item list.
* The index **must be a positive integer** 1, 2, 3, ...​
* `DAY NUMBER` **must be a positive integer** 1, 2, 3, ...​ referring to a day in the list of days displayed.
* `START TIME` should be given in the format `hh:mm`, or `hh:mm:ss` where `hh` is the hour in 24-hour format, `mm` is the minute, and `ss` is the seconds.
* The end time of the item is calculated by adding the `DURATION` of the item to the `START TIME`.
* You can only add an item if there is no clash in timing between the start and end time of the new item, and the start and end time of any existing scheduled item.

Examples:
* `plan 2 d/3 st/12:00` would schedule the 2nd item in the unscheduled item list on Day 3, starting at 12pm.
* `plan 1 d/1 st/14:50:10` would schedule the 1st item in the unscheduled item list on Day 1, starting at 14:50pm, 10 seconds in.

### Unscheduling an item: `unplan`

Unschedules an item in the scheduled item lists in the list of days.

Format: `unplan INDEX`

* Unschedules the item at the specified `INDEX`. The index refers to the index number displayed in the list of scheduled items in the list of days.

Examples:
* `unplan 2.1` would unschedule the 1st item in the Day 2 item list.
* `unplan 4.5` would unschedule the 5th item in the Day 4 item list.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

* This command can be used both in the home page and the item planning page.

### Saving the data

Waddle data are saved in the hard disk automatically after any command that changes the data is used. There is no need to save manually.

### Editing the data file

Waddle data is saved as a JSON file `[JAR file location]/data/waddle.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Waddle will discard all data and start with an empty data file at the next run. Please perform a backup before manually editing data.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Waddle home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                 |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME [c/COUNTRY] du/DURATION s/START DATE [p/NUMBER OF WADDLERS] [b/BUDGET]`<br> e.g., `new n/Germanyyyy c/Germany d/14 s/05/10/22 e/19/10/22 p/4 b/7000` |
| **Clear**  | `clear`                                                                                                                                                          |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                              |
| **Edit**   | `edit INDEX [n/NAME] [c/COUNTRY] [du/DURATION] [s/START DATE] [p/NUMBER OF WADDLERS] [b/BUDGET]`<br> e.g.,`edit 1 d/15 s/04/10/22`                               |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find India Trip`                                                                                                       |
| **List**   | `list`                                                                                                                                                           |
| **Help**   | `help`                                                                                                                                                           |
| --------   | ------------------                                                                                                                                               |
| **Add**    | `add d/DESCRIPTION [p/PRIORITY] [c/COST] [du/DURATION]`                                                                                                          |
| **Delete** | `delete INDEX`                                                                                                                                                   |
| **Edit**   | `edit INDEX [d/DESCRIPTION] [p/PRIORITY] [c/COST] [du/DURATION]`                                                                                                 |
