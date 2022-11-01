---
layout: page
title: User Guide
---

Condonery is a desktop app made for property agents primarily used for managing client contacts and condo listings.
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Condonery can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents 
- [`Quick start`](#quick-start)
- [`Features in Condonery`](#features-in-condonery)
  - [Undoing commands : `undo`](#undoing-commands--undo)
  - [Viewing help : `help`](#viewing-help--help)
  - [Exiting the program : `exit`](#exiting-the-program--exit)
- [`Property Directory Features`](#property-directory-features)
    - [Adding a property: add -p](#adding-a-property-add--p)
    - [Listing all properties : list -p](#listing-all-properties--list--p)
    - [Editing a property : edit -p](#editing-a-property--edit--p)
    - [Locating properties by name: `find -p`](#locating-properties-by-name-find--p)
    - [Deleting a property : `delete -p`](#deleting-a-property--delete--p)
    - [Filtering properties: `filter -p`](#filtering-properties-filter--p)
    - [Filtering properties within price range: range -p](#filtering-properties-within-price-range-range--p)
    - [Filter properties by status: status -p](#filter-properties-by-status-status--p)
    - [Filtering properties by type: type -p](#filtering-properties-by-type-type--p)
    - [Selecting a property: select -p INDEX](#selecing-a-property-select--p-index)
    - [Clearing all entries : clear](#clearing-all-entries--clear)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [`Client Directory Features`](#client-directory-features)
    - [Adding a client profile: add -c](#adding-a-client-profile-add--c)
    - [Editing a client profile: edit -c](#editing-a-client-profile-edit--c)
    - [Listing all client profiles: list -c](#listing-all-client-profiles-list--c)
    - [Filtering client profiles: filter -c](#filtering-client-profiles-filter--c)
    - [Deleting a client profile: delete -c](#deleting-a-client-profile-delete--c-index)
    - [Selecting a client profile: select -pc](#selecting-a-client-profile-select--pc-index)
- [`Command summary`](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `condonery.jar` from [here](https://github.com/AY2223S1-CS2103-W14-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Condonery.

1. Double-click the file to start the app. The interface will look like this.
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list -p`** : Lists all properties.

    * **`add -p`**`n/Pinnacle@Duxton a/Cantonment Rd, #1G, 085301 p/100,000 t/luxury` : Adds a listing named `Pinnacle@Duxton` to the property directory.

    * **`list -p delete`**`3` : Deletes the 3rd property shown in the current list.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features-syntax) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features syntax

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Demarcaters `-p` and `-c` after each command word represent interactions with the property and client directory respectively.<br>
  e.g. `add -p` is used to add properties in the property directory while `add -c` is used to add clients in the client directory.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -p n/NAME`, `NAME` is a parameter which can be used as `add -p n/PINNACLE@DUXTON`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/PINNACLE@DUXTON t/luxury` or as `n/PINNACLE@DUXTON`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PRICE`, `p/PRICE n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/1,000,000 p/2,000,000`, only `p/2,000,000` will be taken.


* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

--------------------------------------------------------------------------------------------------------------------

## Features in Condonery 

### Undoing commands : `undo`

Reverses the effect of the previous command. Only commands that affect the state of Property/Client Directory can be
undone.

For example, `help` cannot be undone, whereas `add -p` can be undone.

Format: `undo`

### Viewing help : `help`

Shows the help guide

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Property Directory Features

### Adding a property: `add -p`

Adds a property to the property directory.

Format: `add -p n/NAME a/ADDRESS p/PRICE [-i] [t/TAG]…​`

Required parameters: `NAME`, `ADDRESS`, `PRICE`, `PROPERTY_TYPE`
Optional Parameters: `TAG`, `IMAGE`, `INTERESTED_CLIENTS`, `PROPERTY_STATUS`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A property can have any number of tags (including 0)
</div>

Examples:
* `add -p n/PINNACLE@DUXTON a/11 Pulau Tekong Besar, Pulau, Tekong Camp, 508450 p/1,000,000 h/HDB t/cheap`
* `add -p n/SEMAKAU LANDFILL a/Straits Boulevard p/1,000 h/CONDO t/high-end`

The `-i` flag allows the user to upload images of the property. A file chooser dialog will appear after running the
command.

The `h/PROPERTY_TYPE` flag specifies the type of property. It can only be either HDB, CONDO, or LANDED.

![file_chooser](images/fileChooser.png)

### Listing all properties : `list -p`

Shows a list of all properties in the address book.

Format: `list -p`

### Editing a property : `edit -p`

Edits an existing person in the address book.

Format: `edit -p INDEX [n/NAME] [a/ADDRESS] [p/PRICE] [h/PROPERTY_TYPE] [t/TAG]…​`

* Edits the property at the specified `INDEX`. The index refers to the index number shown in the displayed property list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the property’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit -p 1 p/1,000,000 a/11 Pulau Tekong Besar, Pulau, Tekong Camp, 508450` Edits the price and address of the 1st person to be `1,000,000` and `11 Pulau Tekong Besar, Pulau, Tekong Camp, 508450` respectively.
*  `edit -p 2 n/Cinammon College t/` Edits the name of the 2nd property to be `Cinammon College` and clears all existing tags.

### Locating properties by name: `find -p`

Finds properties whose names contain any of the given keywords.

Format: `find -p KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `bishan` will match `Bishan`
* The order of the keywords does not matter. e.g. `PINNACLE@DUXTON Bishan` will match `Bishan PINNACLE@DUXTON`
* Only the name is searched.
* Only full words will be matched e.g. `PINNACLE` will not match `PINNACLE@DUXTON`
* Properties matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `PINNACLE@DUXTON Bishan` will return `PINNACLE@DUXTON`, `Bishan 8`

Examples:
* `find -p Wall Street` returns `Wall Street` and `Wall Street Prime`
* `find -p Pinnacle@Duxton Sesame Street` returns `Pinnacle@Duxton`, `Sesame Street`<br>

[//]: # (Image to be added later)
[//]: # (  ![result for 'find alex david']&#40;images/findAlexDavidResult.png&#41;)

### Deleting a property : `delete -p`

Deletes the specified property from the address book.

Format: `delete -p INDEX`

* Deletes the property at the specified `INDEX`.
* The index refers to the index number shown in the displayed property list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list -p` followed by `delete -p 2` deletes the 2nd property in the address book.
* `find -p Wall Street` followed by `delete -p 1` deletes the 1st property in the results of the `find -p` command.

### Filtering profiles: `filter -p`

Returns a filtered list of properties that fulfil the filter conditions for tags only.
Format: `filter -p TAG...`
Examples:
* `filter -p high-end` returns all properties with the `high-end` tag.
* `filter -p available` returns all properties with the `available` tag.

### Filtering properties within price range: `range -p`

Returns a filtered list of clients that fulfil the filter conditions.
Format: `range -p l/100,000 u/1,000,000`
Examples:
* `range -p l/100,000 u/1,000,000` returns all properties within the price range of 100,000 and 1,000,000 inclusive.
Notes:
* lower and upper price boundaries only accept integer values (commas acceptable).

### Filter properties by status: `status -p`

Returns all the properties with the specified `PropertyStatusEnum`
Format: `status -p STATUS`

Examples:
* `status -p AVAILABLE` returns all the properties with status of `AVAILABLE`
* `status -p PENDING` returns all the properties with status of `PENDING`
* `status -p SOLD` returns all the properties with status of `SOLD`

### Filtering properties by type: `type -p`

Returns a filtered list of clients that fulfil the filter conditions.
Format: `type -p HDB`
Examples:
* `type -p HDB condo` returns all properties that are HDBs or Condos

### Selecting a property: `select -p INDEX`

Selects the specified property in the address book, for its details to be displayed.

Format: `select -p INDEX`

* Selects the property at the specified `INDEX`.
* The index refers to the index number shown in the displayed property list.
* The index **must be a positive integer** 1, 2, 3, …​
* The GUI changes to display just the selected property in the property directory,
while clients interested in that property are shown in the client directory.

### Clearing all entries : `clear`

Clears all properties from the property directory.

Format: `clear -p`

### Saving the data

Property directory and Client directory data are saved in the hard disk automatically after any command that changes 
the data. There is no need to save manually.

### Editing the data file

Property directory data are saved as a JSON file `[JAR file location]/data/propertyDirectory.json`.
Client directory data are saved as a JSON file `[JAR file location]/data/clientDirectory.json`.
Uploaded images are saved in `[JAR file location]/data/images`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, both the Property directory and Client directory will discard all data and start with an empty data file at the next run.
</div>

## Client Directory Features

### Adding a client profile: `add -c`

Adds a client profile to the client directory.
Format: `add -c n/NAME a/ADDRESS [t/TAG]...`
Tips:
* At least one of the optional fields must be provided.
  Examples:
* `add -c n/Bob a/Cinammon College`

### Editing a client profile: `edit -c`

Edits a client profile already existing in the client directory.
Format: `edit -c INDEX [n/NAME] [a/ADDRESS] [t/TAG]...`
Tips:
* Edits the client profile at the specified INDEX. The index refers to the index numebr shown
  in the displayed person list. The index must be a positive integer 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the listing will be removed i.e. adding of tags is not cumulative.
* You can remove all the listing's tags b
  Examples:
* `edit -c 5 n/Bob a/RC4 t/thrifty`

### Listing all client profiles: `list -c`

Lists all client profiles currently stored in the client directory.
Format: `list -c`

### Filtering client profiles: `filter -c`

Returns a filtered list of clients according to specified tags.

TAG have to be fully specified (i.e. no partial tags like 'high' instead of 'high-end')

Format: `filter -c TAG...`

Examples:
* `filter -c high-end`
* `filter -c thrifty`

### Deleting a client profile: `delete -c INDEX`

Deletes the unique client profile at the specified INDEX.
Format: `delete -c INDEX`
Tips:
* The INDEX must be a positive integer 1, 2, 3...
  Examples:
* `list -c` followed by `delete 2` deletes the second client in the address book
* `filter -c high-end` followed by `delete 1` deletes the first client in the results of the
  `filter -c` command

### Selecting a client profile: `select -c INDEX`

Selects the specified client in the address book, for its details to be displayed.

Format: `select -c INDEX`

* Selects the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The GUI changes to display just the selected client in the client directory,
while properties the client is interested in are shown in the property directory.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous Property directory and Client directory home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

**Property Directory**

| Action        | Format, Examples                                                                                                                                 |
|---------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add -p**    | `add -p n/NAME a/ADDRESS p/PRICE h/PROPERTY_TYPE [-i] [t/TAG]…​` <br> e.g., `add -p n/PINNACLE@DUXTON a/SG, Cantonment Rd, #1G, 085301 t/luxury` |
| **Clear -p**  | `clear -p`                                                                                                                                       |
| **Delete -p** | `delete -p INDEX`<br> e.g., `delete -p 3`                                                                                                        |
| **Edit -p**   | `edit INDEX [n/NAME] [a/ADDRESS] [h/PROPERTY_TYPE] [t/TAG]…​`<br> e.g.,`edit -p 2 n/PINNACLE@DUXTON a/SG, Cantonment Rd, #1G, 085301 t/luxury`   |
| **Filter -p** | `filter -p TAG [MORE_TAGS]`<br> e.g., `filter -p high-end thrifty`                                                                               |
| **Find -p**   | `find -p NAME [MORE_NAMES]`<br> e.g., `find -p PINNACLE@DUXTON BISHAN`                                                                           |
| **Range -p**  | `range -p l/LOWER_BOUND u/UPPER_BOUND`<br> e.g., `range -p l/1,000,000 u/3,000,000`                                                              |
| **List -p**   | `list -p`                                                                                                                                        |
| **Select -p** | `select -p INDEX` <br> e.g., `select -p 2`                                                                                                       |
| **Status -p** | `status -p` <br> e.g., `status -p AVAILABLE`                                                                                                     |
| **Type -p**   | `type -p PROPERTY_TYPE` <br> e.g., `type -p HDB`                                                                                                 |

**Client Directory**

| Action        | Format, Examples                                                                                                                      |
|---------------|---------------------------------------------------------------------------------------------------------------------------------------|
| **Add -c**    | `add n/NAME a/ADDRESS [-i] [t/TAG]…​` <br> e.g., `add n/James a/123, Clementi Rd, 1234665 t/friend t/colleague`                       |
| **Clear -c**  | `clear -c`                                                                                                                            |
| **Delete -c** | `delete -c INDEX`<br> e.g., `delete -c 3`                                                                                             |
| **Edit -c**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee a/SG, Cantonment Rd, #1G, 085301` |
| **Filter -c** | `filter -c TAG [MORE_TAGS]`<br> e.g., `filter -c friend colleague`                                                                    |
| **Find -c**   | `find -c NAME [MORE_NAMES]`<br> e.g., `find -c James Jake`                                                                            |
| **List -c**   | `list -c`                                                                                                                             |
| **Select -c** | `select -c INDEX` <br> e.g., `select -p 1`                                                                                            |

**General**

**Exit** | `exit`
**Help** | `help`
**Undo** | `undo`

