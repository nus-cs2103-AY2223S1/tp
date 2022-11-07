---
layout: page
title: User Guide
---

## Introduction
_Are you a new NUS student that's not sure what food options there are in NUS? 
Or perhaps are you stuck on what to eat for lunch?_ <br>

Presenting to you, NUSEatWhere's `Food Guide`! Our `Food Guide` is a desktop application that stores
information on NUS canteens and eateries, as well as supporting search functions and the ability to randomly generate
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
  * [Find](#finding-eateries-by-name--find) / [FindTag](#finding-eateries-by-tag--findtag) /
    [FindLocation](#finding-eateries-by-location--findlocation) / [FindCuisine](#finding-eateries-by-cuisine--findcuisine)
  * [Tag](#add-tag-to-eatery--tag) / [Untag](#remove-tag-from-eatery--untag)
  * [Add](#add-eatery--add) / [Delete](#delete-eatery--delete) / [Edit](#edit-eatery--edit)
  * [Fav](#favourite-eatery--fav) / [Unfav](#unfavourite-eatery--unfav)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Using the User Guide
This user guide aims to orientate users (such as yourself!) on how to install, use, and troubleshoot `Food Guide`.

For all new users, we highly recommend you start at the [Quick Start](#Quick-start) section, which covers
installation instructions and the basic features of our application.

After getting acquainted with `Food Guide`, you should take a look at our [Features](#features) section which
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

4. Double-click the file to start the app. <br> 
It should like the picture above in the [First Glance](#first-glance) section. 

5. You're done setting up the `Food Guide`! <br> 
Go ahead and type some commands into the command box and press Enter to execute it. <br>
e.g. typing **`help`** and pressing Enter will open the help window. <br>

   Here are some example commands you can try:

   * **`help`** : Lists all commands
   * **`list`**: Lists all eateries
   * **`find mala`**: List all eateries with 'mala' in its name
   * **`exit`**: Closes the application

6. You can refer to [Features](#features) below for the details of each command.

--------------------------------------------------------------------------------------------------------------------

## Introduction to Features

This section summarizes all the commands supported in the `Food Guide`. 
We highly recommend you read this section before jumping into all the features below!

1. When in doubt, you can always fall back on the `help` command.
You can also add `-h` to the back of any command to get `Food Guide` to generate 
a help message on how to use the command (e.g. `find -h`)!

2. To reset the eateries in the list to its default state, use `list`.

3. Make use of the various search commands (`find`, `findTag`, `findLocation`, `findCuisine`, `findPrice`) 
to search for a specific eatery!

4. Make use of our random generator by adding `-r NUMBER` after any `find` command. For example, run
`find -r 1` to generate 1 random eatery to eat at.

5. Customize `Food Guide` to suit your needs by adding tags to your eateries.
You can use the `tag` and `untag` commands to manipulate tags attached to each eatery.

6. Additionally, you can use the `fav` and `unfav` commands to mark some eateries as favourites!

7. Lastly, we empower you to `add`, `edit` and `delete` eateries from `Food Guide`. 
This may be useful to include eateries near NUS (such as Supper Stretch!).

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:** <br>

* Inputs in `UPPER_CASE` are needed for the command to work. <br>
  e.g. in `add -n NAME`, `NAME` is an input which can be used as `add -n Pasta Express`. <br><br>

* Inputs in square brackets are **optional**. <br>
  e.g `-n NAME [-p PRICE]` can be used as `-n Pasta Shop -p $` or as `-n Pasta Shop`. <br><br>

* Items with `…`​ after them can be used **one or more times**. <br>
  e.g. `-t TAGNAME…​` can be used as `-t Foodcourt`, `-t Tea -t Coffee` etc.  <br>
  Note:  you can only use 0 times if there are square brackets, e.g. `[-t TAGNAME]` <br><br>

* **Prefixed inputs** _(e.g. -n, -p, -t, etc.)_ can be in **any order**. <br>
  e.g. if the command specifies `-n NAME -t TAGNAME`, you can also use `-t TAGNAME -n NAME`. <br><br>

* If a certain input is expected only once in the command, but you specified it multiple times,
  only the last occurrence will be taken. <br>
  e.g. if you specify `-n nameA -n nameB`, only `-n nameB` will be taken. <br><br>

* Extra inputs for commands that do not take inputs (such as `help`) will be ignored. <br>
  e.g. `help 123` will be interpreted as `help`.

<br>

</div>

### Help command : `help`

_Displays a pop-out window listing out all the available commands & their functions. <br>
The window also contains a link to this User Guide._

**Format:** `help`

<br>

### Listing all eateries : `list`

_Lists out all the eateries in your database. <br>
If a command such as `find` has hidden eateries from you, you can simply type `list` to view all your eateries again._

**Format:** `list [-h]`

**Inputs:** <br>
`-h`: displays help message (specific to list)

<br>

### Finding eateries by name : `find`

_Search for eateries with names matching your input. <br>
You can also quickly narrow down your choices with the randomizer feature._

**Format:** `find KEYWORD…​ [-r NUMBER] [-h]`

<div markdown="span" class="alert alert-primary"> :bulb:  **Tip:**
You can leave NAME empty if you are using the randomizer (-r) feature
</div>

**Inputs:** <br>
`KEYWORD` : The keyword(s) that must be included in the eatery name <br>
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

### Finding eateries by tag : `findTag`

_Search for eateries labelled with the specified tag(s).<br>
You can also quickly narrow down your choices with the randomizer feature._

**Format:** `findTag TAGNAME…​ [-r NUMBER] [-h]`

<div markdown="span" class="alert alert-primary"> :bulb: **Tip:**
You can search for any number of tags by typing them all after findTag
</div>

**Inputs:** <br>
`TAGNAME`: The tag(s) the eatery must have <br>
`NUMBER`: how many randomly selected eateries to show (more than 0) <br>
`-h`: displays help message (specific to findTag)
<br>

Note:
* The search is case-insensitive. e.g `foodcourt` will match `Foodcourt`
* Eateries matching at least one tag will be returned.
  e.g. `findTag foodcourt cafe` will return eateries with either tag.

Example: `findTag restaurant`

<br>

### Finding eateries by location : `findLocation`

_Search for eateries that match the specified location(s). <br>
You can also quickly narrow down your choices with the randomizer feature._

**Format:** `findLocation LOCATIONNAME…​ [-r NUMBER] [-h]`

**Inputs:** <br>
`LOCATIONNAME`: The location(s) the eatery must have <br>
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

### Finding eateries by cuisine : `findCuisine`

_Search for eateries that match the specified cuisine(s). <br>
You can also quickly narrow down your choices with the randomizer feature._

**Format:** `findCuisine CUISINENAME…​ [-r NUMBER] [-h]`

**Inputs:** <br>
`CUISINENAME`: The cuisine(s) the eatery must have <br>
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


### Finding eateries by price : `findPrice`

_Search for eateries that match the specified price(s)._

**Format:** `findPrice PRICE…​ [-r NUMBER] [-h]`

**Inputs:** <br>
`PRICE`: The price(s) the eatery must have <br>
`NUMBER`: how many randomly selected eateries to show (more than 0) <br>
`-h`: displays help message (specific to findCuisine)
<br>

Note:
* Eateries matching at least one price will be returned.
  e.g. `findPrice $ $$` will return eateries that have either of the prices.

Example: `findPrice $ -r 2` <br>
Example: `findPrice $`

<br>

### Add tag to eatery : `tag`

_Label an eatery with custom tag(s) to facilitate searching._

**Format:** `tag INDEX -t TAGNAME…​ [-h]`

**Inputs:** <br>
`INDEX`: index of eatery to place tag on (must be found in the current list) <br>
`TAGNAME`: name of tag to assign to eatery <br>
`-h`: displays help message (specific to tag) <br><br>
Example: `tag 1 -t coffee -t tea`

<br>

### Remove tag from eatery : `untag`

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

### Add eatery : `add`

_Adds a new eatery to your database. Eatery will be added to the end of the
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

### Delete eatery : `delete`

_Deletes an eatery from your database_

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

### Edit eatery : `edit`

_... Details coming soon ..._

<br>

### Favourite Eatery : `fav`

_Favourites an eatery from your database. <br>
The favourite tag is standardized to be "<3" when using this command._

**Format:** `fav INDEX [-h]`

**Inputs:** <br>

`INDEX`: index of the eatery to favourite (must be found in the current list) <br>
`-h`: displays help message (specific to fav) <br><br>
Example: `fav 3`

<br>

### Unfavourite Eatery : `unfav`

_Unfavourites an eatery from your database. <br>
The tag to be removed is standardized to be "<3" when using this command._

**Format:** `unfav INDEX [-h]`

**Inputs:** <br>

`INDEX`: index of the eatery to unfavourite (must be found in the current list) <br>
`-h`: displays help message (specific to unfav) <br><br>
Example: `unfav 3`

<br>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer? <br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous NUSEatWhere home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action           | Format                                                              |
|:-----------------|:--------------------------------------------------------------------|
| **Help**         | `help`                                                              |
| **List**         | `list [-h]`                                                         |
| **Find**         | `find NAME…​ [-r NUMBER] [-h]`                                      |
| **FindTag**      | `findTag TAGNAME…​ [-r NUMBER] [-h]`                                |
| **FindLocation** | `findLocation LOCATIONNAME…​ [-r NUMBER] [-h]`                      |
| **FindCuisine**  | `findCuisine CUISINENAME…​ [-r NUMBER] [-h]`                        |
| **FindPrice**    | `findPrice PRICE…​ [-r NUMBER] [-h]`									                       |
| **Tag**          | `tag INDEX -t TAGNAME…​ [-h]`                                       |
| **Untag**        | `untag INDEX -t TAGNAME…​ [-h]`                                     |
| **Add**          | `add -n NAME -l LOCATION -c CUISINE [-p PRICE] [-t TAGNAME…​] [-h]` |
| **Delete**       | `delete INDEX [-h]`                                                 |
| **fav**          | `fav INDEX [-h]`                                                    |
| **unfav**        | `unfav INDEX [-h]`                                                  |
