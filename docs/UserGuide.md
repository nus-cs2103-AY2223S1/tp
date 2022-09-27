---
layout: page
title: User Guide
---

NUSEatWhere is a **Command Line (CLI) application** which helps you search for the available food options in NUS and thus make an informed decision on where to eat.

* Table of Contents
  * Quick Start
  * Features
    * Help
    * List
    * Find
    * Tag
    * Untag
    * Add
    * Delete


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Install Java 11

1. Download the latest `NUSEatWhere.jar` from [here](https://github.com/AY2223S1-CS2103T-W11-1/tp/releases).

1. Move the file to the intended _home folder_.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. <br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`help`** : Lists all commands.

   * **`list`**`: Lists all food stalls.

1. Refer to [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -n NAME`, `NAME` is a parameter which can be used as `add -n Pasta Express`.

* Items in square brackets are optional.<br>
  e.g `-n NAME [-t TAG]` can be used as `-n Pasta Express -t Western` or as `n Pasta Express`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[-t TAG]…​` can be used as ` ` (i.e. 0 times), `-t Western`, `-t Western -t TD` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n NAME -t TAG`, `-t TAG -n NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-name A -name B`, only `-name B` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Help command : `help`

Lists out all the available commands & their functions.

Format: `help`

### Listing all food stalls : `list`

Lists out all food stalls in NUSEatWhere database.

Format: `list [-h]`

Arguments <br>
-h: displays this help

### Finding stalls : `find`

Search for stalls that meet the search criteria.

Format: `find [-n NAME] [-t TAG] [-l LOCATION] [-c CUSINE] [-h]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can search for any number of tags (including 0 if other search fields are used)
</div>

Arguments<br>
-n NAME : returns stores that match the name<br>
-t TAGNAME : returns stores with that tag<br>
-l LOCATION : returns stores with location matching a location name<br>
-c CUSINE : returns stores with matching cuisine name<br>
-h : displays this help
<br><br>

Note:
* The search is case-insensitive. e.g `hans` will match `Hans`
* Persons matching at least one tag will be returned (i.e. `OR` search).
  e.g. `-t Western -t Japenese` will return food stalls with either tag.

Examples:
* `find -t Western`
* `find -l TD`

### Add tag to stall : `tag`

Create a custom tag for stall(s) to facilitate searching.

Format: `tag ID…​ -t TAGNAME…​ [-h]`

Arguments<br>
ID: ID of store to place tag on<br>
TAGNAME : name of tag to assign to food stall<br>
-h : displays this help

### Remove tag from stall : `untag`

Remove a custom tag from stall(s).

Format: `untag ID…​ -t TAGNAME…​ [-h]`

Arguments<br>
ID: ID of store to place tag on<br>
TAGNAME : name of tag to assign to food stall<br>
-h : displays this help

### Add stall: `add`

Adds a new stall to NUSEatWhere database.

Format: `add -n NAME -l LOCATION -c CUSINE [-t TAG]…​`

Arguments<br>
NAME : name of the store to be added<br>
LOCATION : location of the store to be added<br>
CUSINE : cuisine type of the store to be added<br>
TAG : extra tags to add to the store

### Delete stall : `delete`

Delete the stall from NUSEatWhere database

Format: `delete STOREID`

Arguments<br>
storeID: ID of store to remove from NUSEatWhere

### Favourite/Unfavourite `[coming in later versions]`

_Details coming soon ..._

### Manage ratings & personal comments `[coming in later versions]`

_Details coming soon ..._

### Edit tag `[coming in later versions]`

_Details coming soon ..._

### Delete tag `[coming in later versions]`

_Details coming soon ..._

### List all tags `[coming in later versions]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NUSEatWhere home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**List** | `list [-h]`
**Find** | `find [-n NAME] [-t TAG] [-l LOCATION] [-c CUSINE] [-h]`
**Tag** | `tag ID…​ -t TAGNAME…​ [-h]`
**Untag** | `untag ID…​ -t TAGNAME…​ [-h]`
**Add** | `add -n NAME -l LOCATION -c CUSINE [-t TAG]…​`
**Delete** | `delete STOREID`
