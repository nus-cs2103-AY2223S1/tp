---
layout: page
title: User Guide
---

## Introduction
_Are you a new NUS student that's not sure what food options there are in NUS? 
Or perhaps are you stuck on what to eat for lunch?_ <br>

Presenting to you, NUSEatWhere's `Food Guide`! Our `Food Guide` is a desktop application that stores
information on NUS canteens and eateries.
It also supports a variety of search functions and the ability to randomly generate
eateries to eat at! 

`Food Guide` is designed for all NUS Students who have a hard time remembering 
all the food options in NUS and/or cannot decide on a place to eat. 
Additionally, `Food Guide`'s **Command-line Interface (CLI)** is optimized for fast-typists to 
quickly execute complicated commands in a simple manner, and is simple to pick up for all users!

With `Food Guide`, you can search for the available food options in NUS and make an informed decision on where to eat!

## Table of Contents
* [Introduction](#introduction)
* [Table of Contents](#table-of-contents)
* [Quick Start](#quick-start)
* [Features](#features)
  * [Help](#help-command--help)
  * [List](#listing-all-eateries--list)
  * [Exit](#exit-command--exit)
  * [Find](#find-eateries-by-name--find) / [FindLocation](#find-eateries-by-location--findlocation) /
    [FindCuisine](#find-eateries-by-cuisine--findcuisine) / [FindPrice](#find-eateries-by-price--findprice) /
    [FindTag](#find-eateries-by-tag--findtag)
  * [Tag](#add-tag-to-eatery--tag) / [Untag](#remove-tag-from-eatery--untag)
  * [Fav](#favourite-eatery--fav) / [Unfav](#unfavourite-eatery--unfav)
  * [Add](#add-eatery--add) / [Delete](#delete-eatery--delete) / [Edit](#edit-eatery--edit)
  * [Clear](#clear-food-guide--clear)

* [FAQ](#faq)
* [Command Summary](#command-summary)

## Using the User Guide
This user guide aims to orientate users (such as _you!_) on how to install, use, and troubleshoot `Food Guide`.

For all new users, we highly recommend you check out the [Quick Start](#Quick-start) section, which covers the
installation instructions and the basic features of our application.

After getting acquainted with `Food Guide`, you should take a look at our [Features](#features) section, which
lists down all of our features in detail and how they work!

Are you an experienced user that just needs a summary on how to use our application? <br>
You might want to head on over to the [Command Summary](#command-summary) section
instead for a quick overview of all our supported commands.

Got any other burning questions? Try looking in our [FAQ](#faq) section! <br>
If your queries cannot be answered, you can try contacting the development team
by referring to our [About Us](AboutUs.md) page or
raising an issue on our [GitHub](https://github.com/AY2223S1-CS2103T-W11-1/tp)!

--------------------------------------------------------------------------------------------------------------------

## First Glance
The following image describes how `Food Guide` looks like upon opening the application.

![Ui](images/user-guide/UgGuiGuide.png)

## Quick start

1. First, start by installing `Java 11` from 
[here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).

2. Next, download the latest `foodguide.jar` from [here](https://github.com/AY2223S1-CS2103T-W11-1/tp/releases).

3. Make sure to move the file to your intended **home folder** for the NUSEatWhere application.

4. Double-click the file to start `Food Guide`. <br> 
The application should like the picture above in the [First Glance](#first-glance) section. 

5. You're done setting up the `Food Guide`! <br> 
Go ahead and type some commands into the command box and press Enter to execute it. <br>
e.g. typing `help` and pressing Enter will open the help window. <br>

   Here are some example commands you can try:

   * `help`: Lists all commands
   * `list`: Lists all eateries
   * `find mala`: List all eateries with 'mala' in its name
   * `exit`: Closes the application

6. You can refer to [Features](#features) below for the details of each command.

--------------------------------------------------------------------------------------------------------------------

## Introduction to Features

This section summarizes all the commands supported in the `Food Guide`. 
We highly recommend you read this section before jumping into all the features below!

1. When in doubt, you can always fall back on the `help` command.
For most commands, you can also add `-h` to the back to get `Food Guide` to generate 
a help message on how to use the command (e.g. `find -h`)!

2. To show all eateries, or reset the eateries in the list to its default state, use `list`.

3. Make use of the various search commands (`find`, `findTag`, `findLocation`, `findCuisine`, `findPrice`) 
to search for a specific eatery!

4. Make use of our random generator by adding `-r NUMBER` after any `find` command. For example, run
`find -r 1` to generate 1 random eatery to eat at.

5. Customize `Food Guide` to suit your needs by adding tags to your eateries.
You can use the `tag` and `untag` commands to change the tags attached to each eatery.

6. Additionally, you can use the `fav` and `unfav` commands to mark some eateries as favourites!

7. Lastly, we empower you to `add`, `edit` and `delete` eateries to and from `Food Guide`. 
This may be useful to include eateries near NUS (such as Supper Stretch!).

<div markdown="block" class="alert alert-info">

### Important! Before Reading This Guide

* Inputs in `UPPER_CASE` are needed for the command to work. <br>
  e.g. in `add -n NAME`, `NAME` is an input which can be used as `add -n Pasta Express`. <br><br>

* Inputs in square brackets are **optional**. <br>
  e.g `-n NAME [-p PRICE]` can be used as `-n Pasta Shop -p $` or as `-n Pasta Shop`. <br><br>

* Items with `…​` after them can be used **one or more times**. <br>
  e.g. `find NAME…​` can be used as `find Frontier`, `find Frontier Deck` etc.  <br>
  Note: For `-t TAGNAME1 [-t TAGNAME2]…​`, the correct input format is `-t mala -t vegetarian`.
  Multiple `-t`s are required.<br><br>

* **Prefixed inputs** _(e.g. -n, -p, -t, etc.)_ can be in **any order**. <br>
  e.g. if the command specifies `-n NAME -t TAGNAME`, you can also use `-t TAGNAME -n NAME`. 
  Both will produce the same outcome. <br><br>

* If a certain input is expected only once in the command, but you specified it multiple times,
  only the last occurrence will be taken. <br>
  e.g. if you specify `-n nameA -n nameB`, only `-n nameB` will be taken. <br><br>

* Extra inputs for commands that do not take inputs (such as `help`) will be ignored. <br>
  e.g. `help 123` will be interpreted as `help`.

* The special `-h` parameter will provide a useful help message for how to use the command. 
  To avoid any unforeseen behaviour, remove your other inputs and just call the command.
  e.g. `find -h` or `add -h`. 

<br>
</div>

### Utility Commands

#### Help command : `help`

_Displays a pop-out window listing out all the available commands & their functions. <br>
The window also contains a link to this User Guide._

**Format**: `help`

<br>

#### Listing all eateries : `list`

_Lists out all the eateries in your `Food Guide`. <br>
If a command such as `find` has hidden eateries from you, you can simply type `list` to view all your eateries again._

**Format:** `list [-h]`

**Inputs:** <br>
`-h`: displays help message (specific to list)

<br>

#### Exit command : `exit`

_Closes the `Food Guide`. All information is saved beforehand._

**Format:** `exit`

### Commands for Finding
These commands search the list of eateries for ones that match your criteria. All find commands support
a randomizer functionality to pick out a number of eateries at random.

<div markdown="block" class="alert alert-info">
If there are fewer eateries matching the criteria than the number specified to the randomizer,
they will all be displayed.
</div>

#### Find eateries by name : `find`

_Search for eateries with names matching your input. <br>
You can also quickly narrow down your choices with the randomizer feature._

**Format:** `find KEYWORD…​ [-r NUMBER] [-h]`

<div markdown="span" class="alert alert-primary"> :bulb:  **Tip:**
You can leave KEYWORD empty if you are using the randomizer (-r) feature
</div>

**Inputs:** <br>
`KEYWORD` : the keyword(s) that must be included in the eatery name <br>
`NUMBER`: how many randomly selected eateries to show (more than 0) <br>
`-h`: displays help message (specific to find)
<br>

Note:
* The search is case-insensitive. e.g `koi` will match `KOI`
* Eateries matching at least one keyword will be returned.
  e.g. `find coffee cafe` will return eateries with either `coffee` or `cafe` in their name.
* You may give a shorter version of the name as long as the spelling is correct e.g. `find veg` will return eateries
  with `vegetarian` in their name.

Example: `find veg -r 2`

Below is an example of what the list would look like when using the randomizer `-r` command.
The command used is shown on the command line. <br>
Note how only 2 random eateries with "veg" in their names are shown.
![Ui](images/user-guide/UgFindWildcard.png)

<br>

#### Find eateries by location : `findLocation`

_Search for eateries that match the specified location(s). <br>
You can also quickly narrow down your choices with the randomizer feature._

**Format:** `findLocation LOCATIONNAME…​ [-r NUMBER] [-h]`

**Inputs:** <br>
`LOCATIONNAME`: the location(s) the eatery must have <br>
`NUMBER`: how many randomly selected eateries to show (more than 0) <br>
`-h`: displays help message (specific to findLocation) <br>

Note:
* The search is case-insensitive. e.g `arts` will match `Arts`
* Eateries matching at least one location will be returned.
  e.g. `findLocation engineering science` will list eateries at either location.
* You may give a shorter version of the location as long as the spelling is correct e.g. `find deck` will return eateries
  with `The Deck` in their name.

Example: `findLocation University Town -r 2` <br>
Example: `findLocation University Town`

<br>

#### Find eateries by cuisine : `findCuisine`

_Search for eateries that match the specified cuisine(s). <br>
You can also quickly narrow down your choices with the randomizer feature._

**Format:** `findCuisine CUISINENAME…​ [-r NUMBER] [-h]`

**Inputs:** <br>
`CUISINENAME`: the cuisine(s) the eatery must have <br>
`NUMBER`: how many randomly selected eateries to show (more than 0) <br>
`-h`: displays help message (specific to findCuisine)
<br>

Note:
* The search is case-insensitive. e.g `korean` will match `Korean`
* Eateries matching at least one cuisine will be returned.
  e.g. `findCuisine Western Japanese` will return eateries that sell either cuisine.

Example: `findCuisine Chinese -r 2` <br>
Example: `findCuisine Chinese`

<br>

#### Find eateries by price : `findPrice`

_Search for eateries that match the specified price(s).
You can also quickly narrow down your choices with the randomizer feature._

**Format:** `findPrice PRICE…​ [-r NUMBER] [-h]`

**Inputs:** <br>
`PRICE`: the price(s) the eatery must have <br>
`NUMBER`: how many randomly selected eateries to show (more than 0) <br>
`-h`: displays help message (specific to findCuisine)
<br>

Note:
* `PRICE` can be either `$`, `$$`, or `$$$`.
* Eateries matching at least one price will be returned.
  e.g. `findPrice $ $$` will return eateries that have either of the prices.

Example: `findPrice $ -r 2` <br>
Example: `findPrice $`

<br>

#### Find eateries by tag : `findTag`

_Search for eateries labelled with the specified tag(s).<br>
You can also quickly narrow down your choices with the randomizer feature._

**Format:** `findTag TAGNAME…​ [-r NUMBER] [-h]`

<div markdown="span" class="alert alert-primary"> :bulb: **Tip:**
You can search for any number of tags by typing them all after findTag
</div>

**Inputs:** <br>
`TAGNAME`: the tag(s) the eatery must have <br>
`NUMBER`: how many randomly selected eateries to show (more than 0) <br>
`-h`: displays help message (specific to findTag)
<br>

Note:
* The search is case-insensitive. e.g `foodcourt` will match `Foodcourt`
* Eateries matching at least one tag will be returned (i.e. `OR` search).
  e.g. `findTag foodcourt cafe` will return eateries with either tag.

Example: `findTag restaurant`

<br>

### Commands for Adding, Editing and Removing Eateries
These commands modify the list of eateries in `Food Guide`, or change existing eateries.

#### Add tag to eatery : `tag`

_Labels an eatery with custom tag(s) to facilitate searching._

**Format:** `tag INDEX -t TAGNAME…​ [-h]`

**Inputs:** <br>
`INDEX`: index of eatery to place tag on (must be found in the current list) <br>
`TAGNAME`: name of tag to assign to eatery <br>
`-h`: displays help message (specific to tag) <br><br>
Example: `tag 1 -t coffee -t tea`

<br>

#### Remove tag from eatery : `untag`

_Remove tag(s) from an eatery._

**Format:** `untag INDEX -t TAGNAME…​ [-h]`

**Inputs:** <br>
`INDEX`: index of eatery to remove tag from (must be found in the current list) <br>
`TAGNAME`: name of tag to remove from eatery <br>
`-h`: displays help message (specific to untag) <br><br>
Example: `untag 1 -t coffee -t tea`

Below is a comparison between when the store at index 50 is `tag[ged]` _(left)_ then `untag[ged]` _(right)_.
The commands used are shown on the command line. <br>
Note how the blue "cafe" tag on store 50 disappears after the untag command.
![Ui](images/user-guide/UgTagUntagComparison.png)
<br><br>

#### Favourite Eatery : `fav`

_Favourites an eatery in the `Food Guide`. <br>
The favourite tag is standardized to be "<3" when using this command._

**Format:** `fav INDEX [-h]`

**Inputs:** <br>

`INDEX`: index of the eatery to favourite <br>
`-h`: displays help message (specific to fav) <br><br>
Example: `fav 3`

<br>

#### Unfavourite Eatery : `unfav`

_Unfavourites an eatery in the `Food Guide`. <br>
The tag to be removed is standardized to be "<3" when using this command._

**Format:** `unfav INDEX [-h]`

**Inputs:** <br>

`INDEX`: index of the eatery to unfavourite <br>
`-h`: displays help message (specific to unfav) <br><br>
Example: `unfav 3`

<br>

#### Add eatery : `add`

_Adds a new eatery to the `Food Guide`. The eatery will be added to the end of the
current list <br>
(i.e. if the current list pre-addition has 5 eateries, the newly added eatery will be of index 6)._

**Format:** `add -n NAME -l LOCATION -c CUISINE -p PRICE [-t TAG]…​ [-h]`

**Inputs:** <br>
`NAME`: name of the eatery to be added <br>
`LOCATION`: location of the eatery <br>
`CUISINE`: cuisine type of the eatery <br>
`PRICE`: price of the eatery <br>
`TAGNAME`: extra tags to add to the eatery <br>
`-h`: displays help message (specific to add) <br><br>
Example: `add -n KOI -l Central Square -c Drinks` <br>
Example: `add -n KOI -l Central Square -c Drinks -p $$`

<br>

#### Delete eatery : `delete`

_Deletes an eatery from the `Food Guide`._

**Format:** `delete INDEX [-h]`

**Inputs:** <br>
`INDEX`: index of eatery to remove from NUSEatWhere (must be found in the current list) <br>
`-h`: displays help message (specific to delete) <br><br>
Example: `delete 3`

Below is a comparison between when the store at index 70 is `add[ed]` _(left)_ then `delete[d]` _(right)_.
The commands used are shown on the command line. <br>
Note how the eatery at index 70 disappears after the delete command.
![Ui](images/user-guide/UgAddDeleteComparison.png)
<br><br>

#### Edit Eatery : `edit`

_Edits the details of an eatery in the `Food Guide`._

**Format:** `edit INDEX [-n NAME] [-l LOCATION] [-c CUISINE] [-p PRICE] [-t TAG]…​ [-h]`

**Inputs:** <br>
`INDEX`: index of the eatery to edit <br>
`NAME`: new name of the eatery <br>
`LOCATION`: new location of the eatery <br>
`CUISINE`: new cuisine type of the eatery <br>
`PRICE`: new price of the eatery <br>
`TAGNAME`: new tags of the eatery <br>
`-h`: displays help message (specific to edit) <br><br>
Example: `edit 1 -n KOI -l Central Square -c Drinks` <br>
Example: `edit 3 -n KOI -l Central Square -c Drinks -p $$` <br>

<div markdown="block" class="alert alert-info">
When editing the tags of an eatery, all existing tags will be overwritten.
</div>

<br>

#### Clear Food Guide : `clear`

_Clears all eateries from the `Food Guide`._

**Format:** `clear`

<div markdown="block" class="alert alert-info">
This command removes ALL eateries.
This action is irreversible.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Where is my data saved?**
NUSEatWhere's `Food Guide` automatically saves your data (including your tags and any new eateries) into a file
`foodguide.json` in the `data` folder.

**Transferring data to friends or other devices**:
Make a copy of the data file (`foodguide.json` in the `data` folder) and transfer it to your friend or the other device
by any means of file sharing (thumbdrive, email, Google Drive, etc.). In the other installation of
`Food Guide`, replace the default data file with your data.

**Manually editing `Food Guide` data**:
If you are familiar with the `json` file format, you may edit the data file `foodguide.json` directly.
Do make a backup as an invalid data file will cause `Food Guide` to override it with an empty file.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Command Word    | Action                                  | Format                                                                                |
|:----------------|:----------------------------------------|:--------------------------------------------------------------------------------------|
| **help**        | View help window                        | `help`                                                                                |
| **list**        | List all eateries                       | `list [-h]`                                                                           |
| **exit**        | Exits the application                   | `exit`                                                                                |
| **find**        | Search for eateries by name             | `find NAME…​ [-r NUMBER] [-h]`                                                     |
| **findLocation**| Search for eateries by location         | `findLocation LOCATIONNAME…​ [-r NUMBER] [-h]`                                     |
| **findCuisine** | Search for eateries by cuisine          | `findCuisine CUISINENAME…​ [-r NUMBER] [-h]`                                       |
| **findPrice**   | Search for eateries by price            | `findPrice PRICE…​ [-r NUMBER] [-h]`						                        |
| **findTag**     | Search for eateries by tag              | `findTag TAGNAME…​ [-r NUMBER] [-h]`                                               |
| **tag**         | Adds tag(s) to an eatery                | `tag INDEX -t TAGNAME1 [-t TAGNAME2]…​ [-h]`                                       |
| **untag**       | Remove tag(s) from an eatery            | `untag INDEX -t TAGNAME1 [-t TAGNAME2]…​ [-h]`                                     |
| **add**         | Adds an eatery to the `Food Guide`      | `add -n NAME -l LOCATION -c CUISINE [-p PRICE] [-t TAGNAME]…​ [-h]`                |
| **delete**      | Removes an eatery from the `Food Guide` | `delete INDEX [-h]`                                                                   |
| **fav**         | Adds an eatery to your favourites       | `fav INDEX [-h]`                                                                      |
| **unfav**       | Removes and eatery from your favourites | `unfav INDEX [-h]`                                                                    |
| **edit**        | Edits an eatery in the `Food Guide`     | `edit INDEX [-n NAME] [-l LOCATION] [-c CUISINE] [-p PRICE] [-t TAGNAME]…​ [-h]`   |
| **clear**       | Clears the `Food Guide`                 | `clear`                                                                               |
