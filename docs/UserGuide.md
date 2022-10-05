---
layout: page
title: User Guide
---
#Waddle User Guide 🦆
Waddle is a **simple, no-frills travel planning application catered to people who love doing everything on their keyboards**. Waddle allows users to plan their travels in **3 simple steps**.
1. Create a trip
2. Add activities 
3. Schedule 

**That simple**.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Waddle.jar` from [here](https://github.com/AY2223S1-CS2103T-W11-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Waddle.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all itineraries.

   * **`new`**`new n/My Japan Trip` : Adds an itinerary named "My Japan Trip".

   * **`delete`**`1` : Deletes the 1st itinerary shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `new n/NAME`, `NAME` is a parameter which can be used as `add n/My Japan Trip`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [c/COUNTRY]` can be used as `n/My Japan Trip c/Japan` or as `n/My Japan Trip`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[w/WADDLERS]…​` can be used as ` ` (i.e. 0 times), `w/me`, `w/friend 1 w/friend 2` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/CATEGORY d/DESCRIPTION`, `d/DESCRIPTION c/CATEGORY` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/Eat Ramen d/Aquarium`, only `p/Aquarium` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Creating a new itinerary: `new`

Adds an itinerary to Waddle.

Format: `new n/NAME [c/COUNTRY] d/DURATION [s/START DATE] [p/NUMBER OF WADDLERS]`

Examples:
* `new n/My Japan Trip d/6`
* `new n/Germanyyyy c/Germany d/14 s/05/10/22 e/19/10/22 p/4`

### Listing all itineraries : `list`

Shows a list of all itineraries in Waddle.

Format: `list`

### Selecting an itinerary: `plan`

Enters the planning page for the selected itinerary.

Format: `plan n/NAME`

Examples:
* `plan n/My Japan Trip`
* `plan n/Germanyyyy`

### Editing the details of an itinerary: `edit`

Edits an existing itinerary in Waddle.

Format: `edit INDEX [n/NAME] [c/COUNTRY] [d/DURATION] [s/START DATE] [p/NUMBER OF WADDLERS]`

* Edits the itinerary at the specified `INDEX`. The index refers to the index number shown in the displayed itinerary list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `edit 1 d/15 s/04/10/22` Edits the duration and start date of the first itinerary to be `15` and `04/10/22` respectively.
* `edit 2 c/India` Edits the country of the second itinerary to be `India`.

### Locating persons by name: `find`

Finds itineraries with names containing any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `india` will match `india`
* The order of the keywords does not matter. e.g. `Trip Japan My` will match `My Japan Trip`
* Only the name is searched.
* Only full words will be matched e.g. `Jap` will not match `Japan`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Trip` will return `My Japan Trip`, `My Germany Trip`

Examples:
* `find India` returns `My India Trip` and `India Expedition`
* `find India Trip` returns `My Japan Trip`, `My India Trip`, `India Expedition`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting an itinerary : `delete`

Deletes the specified itinerary from Waddle.

Format: `delete INDEX`

* Deletes the itinerary at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd itinerary in Waddle.
* `find Japan` followed by `delete 1` deletes the 1st itinerary in the results of the `find` command.

### Clearing itineraries : `clear`

Deletes all itineraries in Waddle.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Waddle data are saved in the hard disk automatically after any command that changes the data is used. There is no need to save manually.

### Editing the data file

Waddle data are saved as a JSON file `[JAR file location]/data/waddle.json`. Advanced users are welcome to update data directly by editing that data file.

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

Action | Format, Examples
--------|------------------
**New** | `new n/NAME [c/COUNTRY] d/DURATION [s/START DATE] [p/NUMBER OF WADDLERS]`<br> e.g., `new n/Germanyyyy c/Germany d/14 s/05/10/22 e/19/10/22 p/4`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [c/COUNTRY] [d/DURATION] [s/START DATE] [p/NUMBER OF WADDLERS]`<br> e.g.,`edit 1 d/15 s/04/10/22`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find India Trip`
**List** | `list`
**Help** | `help`
