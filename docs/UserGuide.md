---
layout: page
title: User Guide
---

Condonery is a desktop app made for property agents primarily used for managing client contacts and property listings.
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Condonery can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `condonery.jar` from [here](https://github.com/AY2223S1-CS2103-W14-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Condonery.

1. Double-click the file to start the app. The interface will look like this.
   ![Ui](images/ug_condonery.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.
   Some example commands you can try:

   * **`list -p`** : Lists all properties.

   * **`add -p `** `n/Pinnacle@Duxton a/Cantonment Rd, #1G, 085301 p/100,000 h/CONDO t/luxury` : Adds a listing named `Pinnacle@Duxton`, with the inputted details, to the property directory.

   * **`list -p`** followed by **`delete 3`** : Deletes the 3rd property shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the Features below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## **Command Overview**

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Flags `-p` and `-c` after each command word represent interactions with the property and client directory respectively.
  * `add -p` adds a property in the property directory while `add -c` adds a client in the client directory.
* Words in `UPPER_CASE` are the arguments to be supplied by the user.<br>
  * In `add -p n/NAME`, `NAME` is an argument which can be used as `add -p n/PINNACLE@DUXTON`.
* Items in square brackets are optional.<br>
  * `n/NAME [t/TAG]` can be used as `n/PINNACLE@DUXTON t/luxury` or as `n/PINNACLE@DUXTON`.
* Items with `…` after them can be used multiple times including zero times.<br>
  * `[t/TAG]…` can be used as many times as needed (including 0 times), `t/friend`, `t/friend t/family` etc.
* Parameters can be in any order.<br>
  *  If the command specifies `n/NAME p/PRICE`, `p/PRICE n/NAME` is also acceptable.
* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  * If you specify `p/1,000,000 p/2,000,000`, only `p/2,000,000` will be taken.
* Extraneous arguments for commands that do not have any parameters (such as `help`, `exit`, `list -p`, `list -c`, `clear -p`  and `clear -c`) will be ignored.<br>
  * If the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Prefix list

| Prefix | Associated Parameter    | Definition                                                                                                                            | Examples                          | Remarks                                                                                                                                                                                                                                                                                            |
|--------|-------------------------|---------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `n/`   | `NAME`                  | Refers to the name of a property or client.<br>                                                                                       | `n/PINNACLE@DUXTON`<br>`n/Walter` | Each property or client can only have one name.                                                                                                                                                                                                                                                    |
| `a/`   | `ADDRESS`               | Refers to the address of a property or client.                                                                                        | `a/Cantonment Rd, #1G, 085301`    | Each property or client can only have one address.                                                                                                                                                                                                                                                 |
| `t/`   | `TAG`                   | Refers to a tag of a property or client.<br> Can be used for labelling important details.                                             | `t/High-end`, `t/Friend`          | Tags must be alphanumerical<br> Each property or client can have multiple tags.                                                                                                                                                                                                                    |
| `-i`   | `IMAGE`                 | Refers to the image for a property or client.<br> User will be prompted to select an image in a separate window upon invoking prefix. | NIL                               | Each property or client can only have one image.                                                                                                                                                                                                                                                   |
| `p/`   | `PRICE`                 | Refers to property price.<br>                                                                                                         | `p/1,000,000`                     | Each property can only have one price.<br> Accepts positive integers only.<br> Max input price is `2,147,483,647`.<br> Use of commas to separate digits allowed, except when commas is in front of leading digit (`,100`), comma not between digits (`100,`) or using consecutive commas (`10,,0`) |
| `h/`   | `PROPERTY_TYPE`         | Refers to type of housing<br>Must be one of CONDO, HDB, or LANDED                                                                     | `h/HDB`, `h/Condo`, `h/landed`    | Each property can only have one property type.<br> Valid property types: `HDB`, `CONDO`, `LANDED`<br> Arguments for this parameter are case-insensitive.                                                                                                                                           |
| `s/`   | `PROPERTY_STATUS`       | Refers to availability of property<br>Must be one of AVAILABLE, SOLD, or PENDING                                                      | `s/AVAILABLE`, `s/PENDING`        | Each property can only have one property status.<br> Valid property statuses: `AVAILABLE`, `SOLD`, `PENDING`<br> Arguments for this parameter are case-insensitive.                                                                                                                                |
| `ic/`  | `INTERESTED_CLIENTS`    | Refers to list of clients who are considering to purchase a particular property.                                                      | `ic/Samuel`                       | Each property can have multiple clients interested in it.<br>                                                                                                                                                                                                                                      |
| `ip/`  | `INTERESTED_PROPERTIES` | Refers to list of properties that a client is interested in.                                                                          | `ip/duxton`                       | Each client can be interested in multiple properties.<br>                                                                                                                                                                                                                                          |

### Property directory

| Command     | Definition                                                                                                           | Parameters                                                                                                                                                                                                                                                                                   | Format                                                                                                                        | Examples                                                                                                                                                                                                                                 |
|-------------|----------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `add -p`    | Adds a property to the property directory.                                                                           | Prefix required for all arguments<br><br>Mandatory arguments:<br>  `n/NAME`<br> `a/ADDRESS`<br> `p/PRICE`<br> `h/PROPERTY_TYPE`<br><br>Optional arguments:<br> `-i` \[IMAGE\]<br> `s/PROPERTY_STATUS`<br> `t/TAG`<br> `ic/INTERESTED_CLIENTS`                                                | `add -p n/NAME a/ADDRESS p/PRICE h/PROPERTY\_TYPE [-i] [s/PROPERTY_STATUS] [ic/INTERESTED_CLIENTS]…​ [t/TAG]…​`               | `add -p n/PINNACLE@DUXTON a/SG, Cantonment Rd, #1G, 085301 p/1,000,000 h/HDB t/luxury`<br><br>`add -p n/KAMPUNG@ADMIRALTY a/676 Woodlands Drive 71, Singapore 730676 p/800,000 h/HDB t/neighbourhood t/elderly ic/James Tan s/AVAILABLE` |
| `list -p`   | Lists all properties currently stored in the property directory.                                                     | No argument needed                                                                                                                                                                                                                                                                           | `list -p`                                                                                                                     | NIL                                                                                                                                                                                                                                      |
| `edit -p`   | Edits an existing property in the property directory.                                                                | Prefix required for all arguments except `INDEX`<br><br>Mandatory arguments:<br> `INDEX`<br><br>Optional arguments (at least one field):<br> `n/NAME`<br> `a/ADDRESS`<br> `p/PRICE`<br> `h/PROPERTY_TYPE`<br> `-i` \[IMAGE\]<br> `s/PROPERTY_STATUS`<br> `t/TAG`<br> `ic/INTERESTED_CLIENTS` | `edit -p INDEX [n/NAME] [a/ADDRESS] [-i] [p/PRICE] [h/PROPERTY_TYPE] [s/PROPERTY_STATUS] [ic/INTERESTED_CLIENTS]…​ [t/TAG]…​` | `edit -p 2 n/PINNACLE@DUXTON a/SG, Cantonment Rd, #1G, 085301`<br><br>`edit -p 1 t/high-end t/luxury`                                                                                                                                    |
| `find -p`   | Finds properties whose names contain any of the given keywords.                                                      | No prefix needed<br><br>`NAME`                                                                                                                                                                                                                                                               | `find -p NAME [MORE_NAMES]`                                                                                                   | `find -p PINNACLE@DUXTON BISHAN`                                                                                                                                                                                                         |
| `delete -p` | Deletes the property at the specified INDEX from the property directory.                                             | No prefix needed<br><br>`INDEX`                                                                                                                                                                                                                                                              | `delete -p INDEX`                                                                                                             | `delete -p 3`                                                                                                                                                                                                                            |
| `filter -p` | Returns a list of properties whose tags contain any of the given keywords.                                           | No prefix needed<br><br>`TAG`                                                                                                                                                                                                                                                                | `filter -p TAG [MORE_TAGS]`                                                                                                   | `filter -p high-end luxury`                                                                                                                                                                                                              |
| `range -p`  | Returns a list of properties within a specified price range.                                                         | Prefix required for all arguments except `INDEX`<br><br>Mandatory arguments:<br> `INDEX`<br> `l/LOWER_BOUND`<br> `u/UPPER_BOUND`                                                                                                                                                             | `range -p l/LOWER_BOUND u/UPPER_BOUND`                                                                                        | `range -p l/1,000,000 u/3,000,000`<br><br>`range -p l/1000000 u/3000000`                                                                                                                                                                 |
| `clear -p`  | Clears all properties from the property directory.                                                                   | No argument needed                                                                                                                                                                                                                                                                           | `clear -p`                                                                                                                    | NIL                                                                                                                                                                                                                                      |
| `select -p` | Returns the property at the specified index while displaying the list of interested clients in the client directory. | No prefix needed<br><br>`INDEX`                                                                                                                                                                                                                                                              | `select -p INDEX`                                                                                                             | `select -p 2`                                                                                                                                                                                                                            |
| `status -p` | Returns all the properties with the specified status.                                                                | No prefix needed<br><br>`PROPERTY_STATUS`                                                                                                                                                                                                                                                    | `status -p`                                                                                                                   | `status -p AVAILABLE`<br><br>`status -p SOLD`                                                                                                                                                                                            |
| `type -p`   | Returns all the properties with the specified type.                                                                  | No prefix needed<br><br>`PROPERTY_TYPE`                                                                                                                                                                                                                                                      | `type -p`                                                                                                                     | `type -p HDB`<br><br>`type -p condo`                                                                                                                                                                                                     |

### Client directory

| Command     | Definition                                                                                                              | Parameters                                                                                                                                                                                                                          | Format                                                                           | Examples                                                                                         |
|-------------|-------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| `add -c`    | Adds a client profile to the client directory.                                                                          | Prefix required for all arguments<br><br>Mandatory arguments:<br> `n/NAME`<br> `a/ADDRESS`<br><br>Optional arguments:<br> `t/TAG`<br> `-i`\[IMAGE\]<br> `ip/INTERESTED_PROPERTIES`                                                  | `add n/NAME a/ADDRESS [-i] [t/TAG]…​ [ip/INTERESTED_PROPERTIES]…​`               | `add -c n/James a/123, Clementi Rd, 1234665 t/friend t/colleague ip/PINNACLE@DUXTON`             |
| `clear -c`  | Clears all clients from the client directory.                                                                           | No argument needed                                                                                                                                                                                                                  | `clear -c`                                                                       | NIL                                                                                              |
| `delete -c` | Deletes the client profile at the specified INDEX from the client directory.                                            | No prefix needed<br><br>`INDEX`                                                                                                                                                                                                     | `delete -c INDEX`                                                                | `delete -c 3`                                                                                    |
| `edit -c`   | Edits an existing client profile in the client directory.                                                               | Prefix required for all arguments except `INDEX`<br><br>Mandatory arguments:<br> `INDEX`<br><br>Optional arguments (at least one field):<br> `n/NAME`<br> `a/ADDRESS`<br> `-i` \[IMAGE\]<br> `t/TAG`<br> `ip/INTERESTED_PROPERTIES` | `edit -c INDEX [n/NAME] [a/ADDRESS] [-i] [ip/INTERESTED_PROPERTIES]…​ [t/TAG]…​` | `edit -c 2 n/James Lee a/SG, Cantonment Rd, #1G, 085301`<br><br>`edit -c 1 t/friend t/colleague` |
| `filter -c` | Returns a list of client profiles whose tags contain any of the given keywords.                                         | No prefix needed<br><br>`TAG`                                                                                                                                                                                                       | `filter -c TAG [MORE_TAGS]`                                                      | `filter -c friend colleague`                                                                     |
| `find -c`   | Finds clients whose names contain any of the given keywords.                                                            | No prefix needed<br><br>`NAME`                                                                                                                                                                                                      | `find -c NAME [MORE_NAMES]`                                                      | `find -c James Jake`                                                                             |
| `list -c`   | Lists all client profiles currently stored in the client directory.                                                     | No argument needed                                                                                                                                                                                                                  | `list -c`                                                                        | NIL                                                                                              |
| `select -c` | Returns the client at the specified index while displaying the list of interested properties in the property directory. | No prefix needed<br><br>`INDEX`                                                                                                                                                                                                     | `select -c INDEX`                                                                | `select -c 1`                                                                                    |

### General commands

| Command     | Definition                                                                                                                                                                                                |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `exit`      | Exits the program.                                                                                                                                                                                        |
| `help`      | Shows the help guide                                                                                                                                                                                      |
| `undo`      | Reverses the effect of the previous command. Only commands that affect the state of Property/Client Directory can be undone.<br><br>For example, `help` cannot be undone, whereas `add -p` can be undone. |
| &#8593;     | Show previous command in command line                                                                                                                                                                     |
| &#8595;     | Show following command in command line                                                                                                                                                                    |

--------------------------------------------------------------------------------------------------------------------

## **Features in Condonery**

### Undoing commands : `undo`

Reverses the effect of the previous command. Only commands that affect the state of Property/Client Directory can be
undone. Commands that are executed before the application is launched cannot be undone.

For example, `help` cannot be undone, whereas `add -p` can be undone.

Note: The feature for undo-ing an image upload has yet to be implemented. User's will have to change the image
(eg. `edit -p 1 -i`) in order to 'undo' an image upload.

Format: `undo`

### Viewing help : `help`

Shows the help guide.<br><br>
Users can either read the User Guide from the Help Window, or choose to navigate to the URL in their browser to view
the full User Guide.

![help message](images/ug_help.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Retrieve previous command : &#8593;

Press the &#8595; key while focused on the Command Line to retrieve previous command in the command line.<br><br>
This feature is for convenience if you wish to re-enter previously entered commands.

### Retrieve following command : &#8595;

Press the &#8595; key while focused on the Command Line to retrieve the following command in the command line.<br><br>
This will only work if you've previously navigated to a previous command and would wish to navigate back to a newer command.

### Saving the data

Property directory and Client directory data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

* Property directory data are saved as a JSON file `[JAR file location]/data/propertyDirectory.json`.
* Client directory data are saved as a JSON file `[JAR file location]/data/clientDirectory.json`.
* Uploaded images are saved in `[JAR file location]/data/images`. Images have to follow the naming convention of
`[property/client]-[EXACT_NAME]`, with no file extension.

Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation:

**Caution:**
If your changes to the data file makes its format invalid, both the Property directory and Client directory will discard all data and start with an empty data file at the next run.

</div>

## **Property Directory Features**

### Adding a property: `add -p`

Adds a property to the property directory.

Format: `add -p n/NAME a/ADDRESS p/PRICE h/PROPERTY_TYPE [-i] [s/PROPERTY_STATUS] [ic/INTERESTED_CLIENTS]…​ [t/TAG]…​`

Prefix required for all arguments

Mandatory arguments:
* `n/NAME`
* `a/ADDRESS`
* `p/PRICE`
* `h/PROPERTY_TYPE`

Optional arguments:
* `-i` [IMAGE]
* `s/PROPERTY_STATUS`
* `t/TAG`
* `ic/INTERESTED_CLIENTS`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* A property can have any number of tags (including 0)<br>
* A property can have any number of interested clients (including 0)<br>
  * Adding interested clients to a property does not add the new property as an interested property for those clients.
  * User should manually add the new property as an interested property of those clients (using `ip/` prefix under `add -c` command).
* The `h/PROPERTY_TYPE` flag specifies the type of property. It can only be either `HDB`, `CONDO`, or `LANDED` (arguments are case-insensitive).<br>
* The `s/PROPERTY_STATUS` is optional, if not specified, will default to `AVAILABLE` status.<br>
* The `ic/INTERESTED_CLIENT` flag allows users to specify clients who are interested in the property.
  * The `INTERESTED_CLIENT` argument should be a case-sensitive substring of the Client's name.<br>
  * If more than 1 client matches the substring, an error message will be shown. Users should then use a more specific search, or input the Client's exact name (case-sensitive).
  * If no clients matches the search, an error message will be shown.
  * To add multiple clients, users should type `ic/[INTERESTED_CLIENT]` for each client. For example, `ic/Samuel ic/Alice`
* The `-i` flag allows the user to upload images of the property. A file chooser dialog will appear after running the command.<br>

![file_chooser](images/ug_add_property.png)

</div>

Examples:
* `add -p n/KAMPUNG@ADMIRALTY a/676 Woodlands Drive 71, Singapore 730676 p/800,000 h/HDB t/Neighbourhood t/Elderly ic/James ic/Jaime s/AVAILABLE`
* `add -p n/PINNACLE@DUXTON a/SG, Cantonment Rd, #1G, 085301 p/1,000,000 h/HDB t/Luxury`


### Listing all properties : `list -p`

Lists all properties currently stored in the property directory.

Format: `list -p`

### Editing a property : `edit -p`

Edits an existing property in the property directory.

Format: `edit -p INDEX [n/NAME] [a/ADDRESS] [p/PRICE] [h/PROPERTY_TYPE] [s/PROPERTY_STATUS] [ic/INTERESTED_CLIENTS]…​ [t/TAG]…​`

Prefix required for all arguments except `INDEX`.

Mandatory arguments:
* `INDEX`

Optional arguments (at least one field):
* `n/NAME`
* `a/ADDRESS`
* `p/PRICE`
* `h/PROPERTY_TYPE`
* `-i`
* `s/PROPERTY_STATUS`
* `t/TAG`
* `ic/INTERESTED_CLIENTS`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* Edits the property at the specified `INDEX`.
  * The `INDEX` refers to the index number shown in the currently displayed property list.
  * The `INDEX` must be within the range of numbers in the currently displayed property list, or else an error message will show up.
  * The `INDEX` **must be a positive integer** 1, 2, 3, …​, or else an error message will show up.
* At least one of the optional fields must be provided, or else an error message will show up.
* Existing values will be updated according to the arguments provided.
* When editing tags, all the existing tags of the specified property will be replaced by your arguments with the `t/` prefix i.e., adding of tags is not cumulative.
* You can remove all the property’s tags by typing `t/` without specifying any tags after it.
* When editing interested clients, all the existing interested clients of the specified property will be replaced by your arguments with the `ic/` prefix i.e., adding of interested clients is not cumulative.
* You can remove all the property’s interested clients by typing `ic/` without specifying any tags after it.

</div>

Examples:
*  `edit -p 1 p/1,000,000 a/11 Pulau Tekong Besar, Pulau, Tekong Camp, 508450` Edits the price and address of the property with index of 1 to be `1,000,000` and `11 Pulau Tekong Besar, Pulau, Tekong Camp, 508450` respectively.
*  `edit -p 2 n/Cinammon College t/` Edits the name of the 2nd property to be `Cinammon College` and clears all existing tags.

### Locating properties by name: `find -p`

Finds properties whose names contain any of the given keywords.

Format: `find -p NAME [MORE_NAMES]`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* The search is case-insensitive. e.g., `bishan` will match `Bishan`.
* Only the name is searched.
* Only complete strings will be matched , `PINNACLE` will not match `PINNACLE@DUXTON`.
* The order of the keywords does not matter. e.g., `PINNACLE@DUXTON Bishan` will match `Bishan PINNACLE@DUXTON`.
* Properties matching at least one keyword will be returned (i.e., `OR` search). e.g., `PINNACLE@DUXTON Bishan` will return `PINNACLE@DUXTON`, `Bishan 8`.

</div>

Examples:
* `find -p Wall Street` returns `Wall Street` and `Wall Street Prime`
* `find -p Pinnacle@Duxton Bishan` returns `Pinnacle@Duxton`, `Bishan 8`<br>

### Deleting a property : `delete -p`

Deletes the property at the specified INDEX from the property directory.

Format: `delete -p INDEX`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* The `INDEX` refers to the index number shown in the currently displayed property directory.
* The `INDEX` **must be a positive integer** 1, 2, 3, …​

</div>

Examples:
* `list -p` followed by `delete -p 2` deletes the 2nd property in the property directory (if it exists).
* `find -p Wall Street` followed by `delete -p 1` deletes the first property in the results of the `find -p` command (if it exists).

### Filtering properties by tags: `filter -p`

Returns a list of properties whose tags contain any of the given keywords.

Format: `filter -p TAG [MORE_TAGS]`


<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* Arguments provided must be an exact string match for the tag i.e., `high-end` instead of `high`.
  * Recall that tags associated with each property are singular words.
* Arguments are case-insensitive.

</div>

Examples:
* `filter -p high-end` returns all properties with the `high-end` tag.
* `filter -p available` returns all properties with the `available` tag.

### Filtering properties within price range: `range -p`

Returns a list of properties within a specified price range.

Format: `range -p l/100,000 u/1,000,000`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* Lower and upper price boundaries only accept positive integer values (commas in between digits are acceptable; see Prefix List for acceptable comma usage).
* Lower price boundary (`l/LOWER_BOUND`) cannot be higher than upper price boundary (`u/UPPER_BOUND`).

</div>

Examples:
* `range -p l/100,000 u/1,000,000` returns all properties within the price range of 100,000 and 1,000,000 inclusive.

### Filtering properties by status: `status -p`

Returns all the properties with the specified status.

Format: `status -p STATUS`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* Only valid arguments for `STATUS` are `AVAILABLE`, `PENDING` and `SOLD` (case-insensitive).
* Properties matching at least one keyword will be returned (i.e., `OR` search). e.g., `SOLD pending` will return properties with `PROPERTY_STATUS` of `SOLD` or `PENDING`.

</div>

Examples:
* `status -p available` returns all the properties with status of `AVAILABLE`.
* `status -p PENDING` returns all the properties with status of `PENDING`.
* `status -p SOLD pending` returns all the properties with status of `SOLD` or `PENDING`.

### Filtering properties by type: `type -p`

Returns all the properties with the specified type.

Format: `type -p TYPE`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* Only valid arguments for `TYPE` are `CONDO`, `HDB` and `LANDED` (case-insensitive).
* Properties matching at least one keyword will be returned (i.e., `OR` search). e.g., `HDB condo` will return properties with `PROPERTY_TYPE` of `HDB` or `CONDO`.

</div>

Examples:
* `type -p HDB condo` returns all properties that are HDBs or Condos.

### Selecting a property: `select -p`

Returns the property at the specified index while displaying the list of interested clients in the client directory.

Format: `select -p INDEX`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* Selects the property at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the currently displayed property directory.
* The `INDEX` **must be a positive integer** 1, 2, 3, …
* The GUI changes to display only the selected property in the property directory, while clients interested in that property are shown in the client directory.

![select_property](images/ug_select_property.png)

</div>

Examples:
* `select -p 3` returns the third property in the property directory, alongside the list of interested clients in the client directory.

### Clearing all entries : `clear -p`

Clears all properties from the property directory.

Format: `clear -p`

## **Client Directory Features**

### Adding a client profile: `add -c`

Adds a client profile to the client directory.

Format: `add -c n/NAME a/ADDRESS [-i] [t/TAG]… [ip/INTERESTED_PROPERTIES]…​`

Prefix required for all arguments

Mandatory arguments:
* `n/NAME`
* `a/ADDRESS`

Optional arguments:
* `t/TAG`
* `-i` [IMAGE]
* `ip/INTERESTED_PROPERTIES`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* A client can have any number of tags (including 0)<br>
* A client can have any number of interested clients (including 0)<br>
  * Adding interested properties to a client does not add the new client as an interested client for those properties.
  * User should manually add the new client as an interested client of those properties (using `ic/` prefix under `add -p` command).
* The `ip/INTERESTED_PROPERTY` flag allows users to specify which properties the client is interested in.
  * The `INTERESTED_PROPERTY` argument should be a case-sensitive substring of the Properties's name.<br>
  * If more than 1 property matches the substring, an error message will be shown. Users should then use a more specific search, or input the Property's exact name (case-sensitive).
  * If no properties matches the search, an error message will be shown.
  * To add multiple properties, users should type `ip/[INTERESTED_PROPERTY]` for each property. For example, `ip/unit1 ip/unit2`
* The `-i` flag allows the user to upload images of the client. A file chooser dialog will appear after running the command.<br>

![file_chooser](images/ug_add_client.png)

</div>

Examples:
* `add -c n/James a/123, Clementi Rd, 1234665 t/Friend t/Colleague ip/PINNACLE@DUXTON`

### Listing all client profiles: `list -c`

Lists all client profiles currently stored in the client directory.

Format: `list -c`

### Editing a client profile: `edit -c`

Edits an existing client profile in the client directory.

Format: `edit -c INDEX [n/NAME] [a/ADDRESS] [-i] [ip/INTERESTED_PROPERTIES]…​ [t/TAG]…​`

Prefix required for all arguments except `INDEX`.

Mandatory arguments:
* `INDEX`

Optional arguments:
* `n/NAME`
* `a/ADDRESS`
* `t/TAG`
* `-i` [IMAGE]
* `ip/INTERESTED_PROPERTIES`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* Edits the client profile at the specified `INDEX`.
  * The `INDEX` refers to the index number shown in the currently displayed client directory.
  * The `INDEX` must be within the range of numbers in the currently displayed client directory, or else an error message will show up.
  * The `INDEX` **must be a positive integer** 1, 2, 3, …​, or else an error message will show up.
* At least one of the optional fields must be provided, or else an error message will show up.
* Existing values will be updated according to the arguments provided.
* You can remove all the client’s tags by typing `t/` without specifying any tags after it.
* Properties the client is interested in will be added cumulatively, the `ip/INTERESTED_PROPERTY` flag will not remove previously added interested properties.
  * Users who wish to remove a client's interest in a property can either `undo` the command, or remove the client from the property's interested client list with the `edit -p` command.

</div>

Examples:
* `edit -c 2 n/James a/SG, Cantonment Rd, #1G, 085301`
* `edit -c 1 t/Friend t/Colleague`

### Locating client profiles by name: `find -c`

Finds clients whose names contain any of the given keywords.

Format: `find -c NAME [MORE_NAMES]`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* The search is case-insensitive. e.g., `janice` will match `Janice`.
* Only the name is searched.
* The order of the keywords does not matter. e.g., `Tan Sean` will match `Sean Tan`.
* Only complete strings will be matched e.g., `Sam` will not match `Samuel`.
* Client profiles matching at least one keyword will be returned (i.e., `OR` search). e.g., `Sean Lee` will return `Sean Tan`, `Bob Lee`.

![find_client](images/ug_find_client.png)

</div>

Examples:
* `find -c Janice` returns `Janice Tan` and `Janice Ong`
* `find -c Dustin Chan` returns `Dustin Ong`, `Jane Chan`<br>

### Deleting a client profile: `delete -c`

Deletes the client profile at the specified INDEX from the client directory.

Format: `delete -c INDEX`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* The `INDEX` refers to the index number shown in the currently displayed client directory.
* The `INDEX` must be a positive integer 1, 2, 3...

</div>

Examples:
* `list -c` followed by `delete 2` deletes the second client in the client directory.
* `filter -c high-end` followed by `delete 1` deletes the first client in the results of the `filter -c` command

### Filtering client profiles by tags: `filter -c`

Returns a list of client profiles whose tags contain any of the given keywords.

Format: `filter -c TAG [MORE_TAGS]`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* Arguments provided must be an exact string match for the tag i.e., 'best-friend' instead of 'friend'.
  * Recall that tags associated with each client are singular words.
* Arguments are case-insensitive.

</div>

Examples:
* `filter -c best-friend` returns all client profiles with the `Best-friend` tag.
* `filter -c thrifty` returns all client profiles with the `Thrifty` tag.

### Selecting a client profile: `select -c`

Selects the specified client profile in the client directory for its details to be displayed.

Format: `select -c INDEX`

<div markdown="block" class="alert alert-primary">

:bulb: **Tips:**

* Selects the client at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the currently displayed client directory.
* The `INDEX` **must be a positive integer** 1, 2, 3, …
* The GUI changes to display just the selected client in the client directory, while properties the client is interested in are shown in the property directory.

</div>

### Clearing all entries : `clear -c`

Clears all clients from the client directory.

Format: `clear -c`

--------------------------------------------------------------------------------------------------------------------

## **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous Property directory and Client directory home folder.

--------------------------------------------------------------------------------------------------------------------

## **Command summary**

**Property Directory**

| Action      | Format                                                                                                                   | Examples                                                                                                                                                                                                                          |
|-------------|--------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `add -p`    | `add -p n/NAME a/ADDRESS p/PRICE h/PROPERTY_TYPE [-i] [s/PROPERTY_STATUS] [ic/INTERESTED_CLIENTS]…​ [t/TAG]…​`           | `add -p n/PINNACLE@DUXTON a/SG, Cantonment Rd, #1G, 085301 p/1,000,000 h/HDB t/Luxury`<br> `add -p n/KAMPUNG@ADMIRALTY a/676 Woodlands Drive 71, Singapore 730676 p/800,000 h/HDB t/Neighbourhood t/Elderly ic/James s/AVAILABLE` |
| `list -p`   | `list -p`                                                                                                                | NIL                                                                                                                                                                                                                               |
| `edit -p`   | `edit -p INDEX [n/NAME] [a/ADDRESS] [p/PRICE] [h/PROPERTY_TYPE] [s/PROPERTY_STATUS] [ic/INTERESTED_CLIENTS]…​ [t/TAG]…​` | `edit -p 2 n/PINNACLE@DUXTON a/SG, Cantonment Rd, #1G, 085301`<br>`edit -p 1 t/high-end t/luxury`                                                                                                                                 |
| `find -p`   | `find -p NAME [MORE_NAMES]`                                                                                              | `find -p PINNACLE@DUXTON BISHAN`                                                                                                                                                                                                  |
| `delete -p` | `delete -p INDEX`                                                                                                        | `delete -p 3`                                                                                                                                                                                                                     |
| `filter -p` | `filter -p TAG [MORE_TAGS]`                                                                                              | `filter -p high-end luxury `                                                                                                                                                                                                      |
| `range -p`  | `range -p l/LOWER_BOUND u/UPPER_BOUND`                                                                                   | `range -p l/1,000,000 u/3,000,000`<br>`range -p l/1000000 u/3000000`                                                                                                                                                              |
| `clear -p`  | `clear -p`                                                                                                               | NIL                                                                                                                                                                                                                               |
| `select -p` | `select -p INDEX`                                                                                                        | `select -p 2`                                                                                                                                                                                                                     |
| `status -p` | `status -p PROPERTY_STATUS`                                                                                              | `status -p AVAILABLE`<br>`status -p SOLD`                                                                                                                                                                                         |
| `type -p`   | `type -p PROPERTY_TYPE`                                                                                                  | `type -p HDB`<br>`type -p condo`                                                                                                                                                                                                  |

**Client Directory**

| Action      | Format                                                                      | Examples                                                                                     |
|-------------|-----------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|
| `add -c`    | `add n/NAME a/ADDRESS [-i] [t/TAG]…​ [ip/INTERESTED_PROPERTIES]…​`          | `add -c n/James a/123, Clementi Rd, 1234665 t/friend t/colleague ip/PINNACLE@DUXTON`         |
| `clear -c`  | `clear -c`                                                                  | NIL                                                                                          |
| `delete -c` | `delete -c INDEX`                                                           | `delete -c 3`                                                                                |
| `edit -c`   | `edit -c INDEX [n/NAME] [a/ADDRESS] [ip/INTERESTED_PROPERTIES]…​ [t/TAG]…​` | `edit -c 2 n/James Lee a/SG, Cantonment Rd, #1G, 085301`<br>`edit -c 1 t/friend t/colleague` |
| `filter -c` | `filter -c TAG [MORE_TAGS]`                                                 | `filter -c friend colleague`                                                                 |
| `find -c`   | `find -c NAME [MORE_NAMES]`                                                 | `find -c James Jake`                                                                         |
| `list -c`   | `list -c`                                                                   | NIL                                                                                          |
| `select -c` | `select -c INDEX`                                                           | `select -c 1`                                                                                |

**General**

**Exit** | `exit`
**Help** | `help`
**Undo** | `undo`
**Navigate previous command** | &#8593;
**Navigate following command** | &#8595;
