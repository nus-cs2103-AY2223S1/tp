---
layout: page
title: User Guide
---

# Introduction

Welcome to the REal-Time User Guide!


REal-Time is a desktop application for _Real-Estate agents_ to manage client information, schedule meetings,
and track client offers and listings.

_[More to be added]_

## About REal-Time

It is optimized for use via a Command Line Interface ([CLI](#Glossary)) while still having the
benefits of a Graphical User Interface ([GUI](#Glossary)).
If you can type fast, REal-Time can get your contact management tasks done faster than traditional GUI apps.

## Fun fact
The **"REa"** in **REal-Time** stands for _Real-Estate agents_, which is the intended target user of our application!

## Using this guide

Now that you have read the introduction and learnt about what our product does, get started in using REal-Time by
following the guide provided in the [Quick Start](#quick-start) section. Otherwise,
* If you are still unsure of the commands used in REal-Time, the [Command Summary](#command-summary) is a good place to
start.
* The [Prefix Summary](#prefix-summary) and [Glossary](#glossary) are also great places to understand REal-Time
better.
* If you are a developer and would like to help improve our product, take a look at our [Developer Guide](https://ay2223s1-cs2103t-w15-2.github.io/tp/DeveloperGuide.html).

# Table of Contents
{: .no_toc}

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------


# Quick start

1. Ensure you have `Java 11` or above installed in your Computer. To install `Java 11`,
click [here](https://www.oracle.com/sg/java/technologies/downloads/#java11) and download the appropriate file depending
on your Operating System ([OS](#Glossary)) (_e.g, Linux, Windows, macOS_).

2. Download the latest `REal-Time.jar` file from [here](https://github.com/AY2223S1-CS2103T-W15-2/tp/releases). The
`REal-Time.jar` file is located in the "Assets" section as shown below.
![downloadRelease](./images/downloadRelease.png)

3. Copy the file to the folder you want to use as the _home folder_ for REal-Time.

4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds.
Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Refer to the [Layout](#layout) if you are still unsure in navigating REal-Time's interface.

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and
pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`addO`**`l/John street, block 123, #01-01 n/John Doe o/700000` : Adds an offer by `John Doe` to the list of offers.

   * **`delC`**`3` : Deletes the 3rd contact shown in the current list of clients.

   * **`clear`** : Deletes all clients, meetings, listings and offers.

   * **`exit`** : Exits the app.

8. Refer to the [Features](#features) below for details of each command.

[Back to Table of Contents](#table-of-contents)

___

# Layout

![Layout](images/layout.png)

`Command Box` - You can enter commands here.<br>

`Feedback Box` - Real-Time feedbacks to your commands will appear here.<br>

`Clients` - All clients in Real-Time will appear here.<br>

`Offers` - All offers in Real-Time will appear here.<br>

`Listings` - All listings in Real-Time will appear here.<br>

`Meetings` - All meetings in Real-Time will appear here.


[Back to Table of Contents](#table-of-contents)

___

--------------------------------------------------------------------------------------------------------------------

# Command format

Real-Time functions are based on commands that you enter, let's learn how to write a command!

This is an example:
```text
addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…
```

This command adds a client into Real-Time.<br>
1. `addC` is the **Command Word**
2. `n/` `p/` `e/` `a/` are prefixes. Prefixes help Real-Time distinguish between different inputs.
3. `NAME` `PHONE_NUMBER` `EMAIL` `ADDRESS` are the data that you can input. For example, `NAME` in `n/NAME` can be replaced with `n/John Doe`.

Let's say you want to add a client named `John Doe`, his phone number is `12345678`, his email is `john@gmail.com` and his address is `123 John St`.<br>

You can enter the following command:<br>
```text
addC n/John Doe p/12345678 e/john@gmail.com a/123 John St
```
###Note:
1. You may notice square brackets [] around some parameters. This indicates that the field is **optional**. For the
example above, the `t/TAG` parameter can be left empty if you do not wish to tag the client.

2. You may also notice `…` after the `t/TAG` field. This indicates that you may enter this field as many times as you
want to (0 or more times). For example, if you want to tag `John Doe` as `Friend` and `Neighbor` you can add 
`t/Friend t/Neighbor` to the command above.

3. The order of every field does not matter. If the command specifies 
`n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

4. If a field is expected only once in the command, but you specified it multiple times, only the last occurrence 
will be taken. If you specify `n/John Dough n/John Doe`, only `n/John Doe` will be taken.

[Back to Table of Contents](#table-of-contents)

___

Let's try another example for another type of command:
```text
delL INDEX
```

This command edits a listing in Real-Time at the specified `INDEX`.
###Note:
1. `INDEX` **must be a positive integer** 1, 2, 3, …​

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format - `help`

![help message](images/helpMessage.png)

[Back to Table of Contents](#table-of-contents)

___

### Clearing all entries : `clear`

Clears all entries in REal-Time.

Format - `clear`

[Back to Table of Contents](#table-of-contents)

___

### Exiting the program : `exit`

Exits the program.

Format - `exit`

[Back to Table of Contents](#table-of-contents)

___

### Saving the data

REal-Time data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Back to Table of Contents](#table-of-contents)

___

### Editing the data file

REal-Time data are saved as a JSON file `[JAR file location]/data/realtime.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid,
REal-Time will discard all data and start with an empty data file at the next run.
</div>

[Back to Table of Contents](#table-of-contents)

___

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

[Back to Table of Contents](#table-of-contents)

___

## Managing Clients

### Adding a client: `addC`

> Adds a client to REal-Time.

**Format:** `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`<br>

**Example input:**<br>
```text
addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01
addC n/Betsy Crowe t/friend e/betsy@example.com a/Newgate Prison p/1234567 t/criminal
```

**Expected output:**<br>
A new client is added to RealTime.<br>


Feedback Box:
```text
New client added: John Doe; Phone: 98765432; Email: johnd@example.com; Address: John street, block 123, #01-01
```
  
[Back to Table of Contents](#table-of-contents)

___

### Deleting a client : `delC`

> Deletes the client at the specified index in REal-Time.

**Format** - `delC INDEX`

**Example input:**<br>
```text
delC 1
```

**Expected output:**<br>
The client at the expected index is deleted from Real-Time.<br>

Feedback Box:
```text
Deleted Client: [details of the deleted client]
```
###Note
1. `listC` followed by `delC 2` deletes the 2nd client in the Real-Time.
2. `findC Betsy` followed by `delC 1` deletes the 1st client in the results of the `findC` command.

[Back to Table of Contents](#table-of-contents)

___

### Editing a client : `editC`

> Edits an existing client in REal-Time

**Format:** `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

**Example input:**<br>
```text
editC 1 p/91234567 e/johndoe@example.com
editC 2 n/Betsy Crower t/
```
**Expected Output:**<br>
The client at the specified index is edited according to the fields provided.<br>

FeedBack Box:
```text
Edited Client: [newly updated details of client]
```
###NOTE
1. Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list.
   The index **must be a positive integer** 1, 2, 3, …​
2. At least one of the optional fields must be provided.
3. Existing values will be updated to the input values.
4. When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
5. You can remove all the client’s tags by typing `t/` without
   specifying any tags after it.

[Back to Table of Contents](#table-of-contents)

___

### Finding clients by name: `findC`

Finds clients whose names contain any of the given keywords.

**Format:** `findC KEYWORD [MORE_KEYWORDS]`

**Example Input:**<br>
```text
findC John
```
**Expected Output:**<br>
The list of clients in the Client Box is updated to a list of all matches from the findC command.

FeedBack Box:
```text
[number of matches] clients listed!
```

![result for 'find alex david'](images/findAlexDavidResult.png)

###NOTE
1. The search is case-insensitive. e.g `hans` will match `Hans`
2. The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
3. Only the name is searched.
4. Only full words will be matched e.g. `Han` will not match `Hans`
5. Clients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

[Back to Table of Contents](#table-of-contents)

___

### Listing Clients: `listC`

> Shows the full list of clients.

**Format:** `listC`

**Expected Output:**<br>
The full list of clients appears in the Client Box.<br>

Feedback Box:
```text
Listed all clients
```
[Screenshots to be added]

[Back to Table of Contents](#table-of-contents)

___

## Managing Offers

In this section, we provide you the basic steps needed to [add](#adding-an-offer-addo), [delete](#deleting-an-offer--delo),
and [edit](#editing-an-offer--edito) offers.

We are also planning to introduce 2 new features in the future, namely [finding](#finding-an-offer--coming-soon) and
[listing](#listing-offers-coming-soon), so stay tuned!

**If this is not the section you are looking for**, click [here](#table-of-contents) to go back to the **Table of Contents**.

___

### Adding an offer: `addO`

Adds an offer in REal-Time, with the given **_Name_** of the Client, **_Listing ID_** of the Listing and the
**_Offer_** made by the client.


**Format:** `addO l/LISTING_ID n/NAME o/OFFER_PRICE`

If you are still unsure of the **prefixes**, click [here](#prefix-summary) to find out more.

**Example input:**<br>
```text
addO l/BEDOK_NORTH_BLK123_12 n/John Doe o/2000000
addO l/BUKIT_PANJANG_RD_BLK456_10 n/Betsy Crowe o/2500000
```

**Expected output:**<br>
A new offer is added to RealTime

Feedback Box:
```text
Offer added: [Details of offer]
```

[Back to Table of Contents](#table-of-contents)

[Back to Managing Offers](#managing-offers)

___

### Deleting an offer : `delO`

Deletes the specified offer in REal-Time.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the `INDEX`:**<br>
* `INDEX` refers to the index number shown in the Offers panel as shown in this example below.

![offerIndex](images/OfferIndex.png)
</div>

**Format:** `delO INDEX`


**Example input:**<br>
```text
delO 2
```

**Expected output:**<br>
The offer at the expected index is deleted from Real-Time.<br>

Feedback Box:
```text
Deleted Offer: [details of the deleted offer]
```

[Back to Table of Contents](#table-of-contents)

[Back to Managing Offers](#managing-offers)

___

### Editing an offer : `editO`

Edits an existing offer in REal-Time.

**Format:** `editO INDEX [n/NAME] [o/OFFER_PRICE] [l/LISTING_ID]`

If you are still unsure of the **prefixes**, click [here](#prefix-summary) to find out more.

**Example input:**<br>
```text
editO 1 o/600000
editO 2 n/Betsy Crower o/123456
```
**Expected Output:**<br>
The offer at the specified index is edited according to the fields provided.<br>

FeedBack Box:
```text
Edited Offer: [newly updated details of offer]
```

[Back to Table of Contents](#table-of-contents)

[Back to Managing Offers](#managing-offers)

___


### Finding an offer : `[Coming soon]`

[Back to Table of Contents](#table-of-contents)

[Back to Managing Offers](#managing-offers)

___

### Listing Offers: `[Coming Soon]`

[Back to Table of Contents](#table-of-contents)

[Back to Managing Offers](#managing-offers)

___

## Managing Listings

### Adding a listing: `addL`

Adds a listing to the address book.

**Format:** `addL l/LISTING_ID a/ADDRESS n/OWNER_NAME ap/ASKING_PRICE [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A listing can have any number of tags (including 0).
However, all other fields must be present.
</div>

    `l/LISTING_ID` refers to the ListingId you wish to assign to this Listing.
    `a/ADDRESS` refers to the address of this Listing.
    `n/NAME` refers to the name of the owner of this Listing.
    `ap/ASKING_PRICE` refers to the asking price that the owner is asking for this Listing.

Examples:

_Success_
* `addL l/007 a/100 Charming Ave n/Joke Peralta ap/10000000`

![addL example](images/addL.png)

_Failure_
* `addL l/007 a/100 Charming Ave n/Joke Peralta` Fails as there is no asking price.

![addL Missing Parameter example](images/addLMissingParam.png)

[Back to Table of Contents](#table-of-contents)

___

### Deleting a listing : `delL`

**Format:** `delL [id/INDEX]`
* Deletes the listing with the given Index.
* `INDEX` refers to the Index of the listing you wish to delete.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Note that you refer to the Index of the Listing in the displayed list.
</div>

Examples:

_Success_
* `delL id/1` Deletes the first Listing in the list.

![deleteL example](images/deleteL.png)

_Failure_
* `delL l/2356739457` Fails as the Index provided does not exist.

![deleteL Invalid ListingId example](images/deleteLInvalidId.png)

[Back to Table of Contents](#table-of-contents)

___

### Editing a listing : `editL`

Edits an existing listing in the address book.

**Format:** `editL INDEX [l/LISTING_ID] [a/ADDRESS] [n/OWNER_NAME] [ap/ASKING_PRICE] [t/TAG]…​`

    `INDEX` refers to the index of the Listing you wish to edit
    Any number (more than 1) of fields may be edited.

Examples:

_Success_
* `editL 1 n/Joke Peralta`

![editL example](images/editL.png)
![editedL example](images/editedL.png)

_Failure_
* `editL 200 n/Joke Peralta` Fails as the index `200` does not exist.

![editL IndexOutOfBounds example](images/editLIndexOutOfBounds.png)

* `editL 1` Fails as no fields were being edited.

![editL No Change example](images/editLNoChange.png)

[Back to Table of Contents](#table-of-contents)

___

### Finding a listing : `[Coming soon]`

[Back to Table of Contents](#table-of-contents)

___

### Listing listings: `listL`

Shows the full list of listings.

**Format:** `listL`

[Screenshots to be added]

[Back to Table of Contents](#table-of-contents)

___

## Managing Meetings

### Adding a meeting: `addM`

Adds a meeting to the address book.

**Format:** `addM id/Listing_ID n/CLIENT_NAME d/DATE_TIME`

**Example input:**<br>
```text
addM id/1 a/John street, block 123, #01-01 d/2022-10-20 12:00
addM id/abc a/Changi Prison d/2022-10-21 14:00
```

**Expected output:**<br>
A new meeting is added to RealTime

Feedback Box:
```text
Meeting added: [Details of meeting]
```
Note:
* DATE_TIME must be in this format, yyyy-MM-dd HH:mm


[Back to Table of Contents](#table-of-contents)

___

### Deleting a meeting : `delM`

Deletes the specified meeting from the address book.

**Format:** `delM INDEX`

**Example input:**<br>
```text
delM 2
```

**Expected output:**<br>
Meeting 2 is deleted from RealTime

Feedback Box:
```text
Meeting deleted: [Details of meeting]
```

[Back to Table of Contents](#table-of-contents)

___

### Editing a meeting : `editM`

Edits an existing meeting in the address book.

**Format:** `editM INDEX [n/OWNER_NAME] [d/DATE_TIME]`

**Example input:**<br>
```text
editM 1 n/Betsy Crowe d/2022-10-20 17:00
editM 2 n/Johnny Sins d/2022-10-22 12:00
```

**Expected output:**<br>
Meeting 1 was edited
Meeting 2 was edited

Feedback Box:
```text
Meeting edited: [Details of meeting]
```


[Back to Table of Contents](#table-of-contents)

___


### Finding a meeting : `[Coming soon]`

[Back to Table of Contents](#table-of-contents)

[Back to Managing Meetings](#managing-meetings)


___


### Listing meetings : `[Coming soon]`

[Back to Table of Contents](#table-of-contents)

[Back to Managing Meetings](#managing-meetings)


___
## General


### Viewing help : `help`

> Show a help window for Real-Time

**Example Input:**<br>
```text
help
```

**Expected output:**<br>

A window displaying help similar to below will appear.

![help message](images/helpMessage.png)

### Clearing all entries : `clear`

> Clears all entries in REal-Time.

**Example Input:**<br>
```text
clear
```
**Expected output:**<br>
Feedback Box:
```text
Real-Time has cleared all data!
```
___

### Exiting the program : `exit`

> Exits the program.

**Example Input:**<br>
```text
exit
```
**Expected output:**<br>

The Real-Time window closes.

[Back to Table of Contents](#table-of-contents)

___

### Saving the data

REal-Time data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Back to Table of Contents](#table-of-contents)

___

### Editing the data file

REal-Time data are saved as a JSON file `[JAR file location]/data/realtime.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid,
REal-Time will discard all data and start with an empty data file at the next run.
</div>


# Glossary

|  Term   |                                Description                                 |
|:-------:|:--------------------------------------------------------------------------:|
| **OS**  | The operating system is the software that is used to run in your computer. |
| **CLI** |                                To be added                                 |
| **GUI** |                                To be added                                 |

[Back to Introduction](#introduction)

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

# Command Summary

|                      Action                       |                                       Format                                       |                                              Examples                                               |
|:-------------------------------------------------:|:----------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------:|
|      [**Add Client**](#adding-a-client-addc)      |              `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`              | `addC n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
|     [**Add Listing**](#adding-a-listing-addl)     |        `addL l/LISTING_ID a/ADDRESS n/OWNER_NAME ap/ASKING_PRICE [t/TAG]…​`        |                     `addL l/007 a/100 Charming Ave n/Joke Peralta ap/10000000`                      |
|     [**Add Meeting**](#adding-a-meeting-addm)     |                   `addM id/Listing_ID n/CLIENT_NAME d/DATE_TIME`                   |                   `addM id/1 a/John street, block 123, #01-01 d/2022-10-20 12:00`                   |
|      [**Add Offer**](#adding-an-offer-addo)       |                      `addO l/LISTING_ID n/NAME o/OFFER_PRICE`                      |                          `addO l/30_SERGARDENS_LOR23_0718 n/Bob o/600000`                           |
|     [**Clear**](#clearing-all-entries--clear)     |                                      `clear`                                       |                                               `clear`                                               |
|   [**Delete Client**](#deleting-a-client--delc)   |                                    `delC INDEX`                                    |                                              `delC 3`                                               |
|  [**Delete Listing**](#deleting-a-listing--dell)  |                                    `delL INDEX`                                    |                                              `delL 1`                                               |
| [**Delete Meeting**](#deleting-an-meeting--delm)  |                                    `delM INDEX`                                    |                                              `delM 4`                                               |
|   [**Delete Offer**](#deleting-an-offer--delo)    |                                    `delO INDEX`                                    |                                              `delO 2`                                               |
|    [**Edit Client**](#editing-a-client--editc)    |      `editC INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`       |                            `editC 2 n/James Lee e/jameslee@example.com`                             |
|   [**Edit Listing**](#editing-a-listing--editl)   | `editL INDEX [l/LISTING_ID][a/ADDRESS] [n/OWNER_NAME] [ap/ASKING_PRICE] [t/TAG]…​` |                                        `editL 4 ap/1234567`                                         |
|   [**Edit Meeting**](#editing-a-meeting--editm)   |                     `editM INDEX [n/OWNER_NAME] [d/DATE_TIME]`                     |                                      `editM 2 n/Roza Santiago`                                      |
|    [**Edit Offer**](#editing-an-offer--edito)     |               `editO INDEX [n/NAME] [o/OFFER_PRICE] [l/LISTING_ID]`                |                                  `editO 2 n/Betsy Crower o/123456`                                  |
| [**Find Client**](#finding-clients-by-name-findc) |                          `findC KEYWORD [MORE_KEYWORDS]`                           |                                         `findC James Jake`                                          |
|     [**List Client**](#listing-clients-listc)     |                                      `listC`                                       |                                               `listC`                                               |
|    [**List Listing**](#listing-listings-listl)    |                                      `listL`                                       |                                               `listL`                                               |
|          [**Help**](#viewing-help--help)          |                                       `help`                                       |                                               `help`                                                |


[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

# Prefix Summary

| Prefix | Description                                                             | Used in                                                                                                                                                                                                                                                    | Example                       |
|--------|-------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------|
| `a/`   | **Address** of the Client or the Listing.                               | [Add Client](#adding-a-client-addc)<br/> [Add Listing](#adding-a-listing-addl) <br/> [Edit Client](#editing-a-client--editc)<br/> [Edit Listing](#editing-a-listing--editl)                                                                                | `a/123, Clementi Rd, 1234665` |
| `ap/`  | **Asking Price** of the Owner in a Listing.                             | [Add Listing](#adding-a-listing-addl) <br/> [Edit Listing](#editing-a-listing--editl)                                                                                                                                                                      | `ap/500000`                   |
| `d/`   | **Date and time** of a Meeting with a Client.                           | [Add Meeting](#adding-a-meeting-addm)<br/>[Edit Meeting](#editing-a-meeting--editm)                                                                                                                                                                        | `d/2022-10-20 12:00`          |
| `e/`   | **Email** of the Client.                                                | [Add Client](#adding-a-client-addc)<br/>[Edit Client](#editing-a-client--editc)                                                                                                                                                                            | `e/johndoe@example.com`       |
| `l/`   | **Listing ID** by the user for a Listing. Can be specified by the user. | [Add Listing](#adding-a-listing-addl)<br/>[Add Offer](#adding-an-offer-addo)<br/> [Edit Listing](#editing-a-listing--editl)<br/>[Edit Offer](#editing-an-offer--edito)                                                                                     | `l/BEDOK_NORTH_RD_BLK123`     |
| `n/`   | **Name** of Client or Owner of a Listing                                | [Add Client](#adding-a-client-addc)<br/>[Add Listing](#adding-a-listing-addl) <br/>[Add Offer](#adding-an-offer-addo)<br/>[Edit Client](#editing-a-client--editc)<br/>[Edit Offer](#editing-an-offer--edito)<br/>[Edit Listing](#editing-a-listing--editl) | `n/John Doe`                  |
| `o/`   | **Offer price** by a Client                                             | [Add Offer](#adding-an-offer-addo)<br/>[Edit Offer](#editing-an-offer--edito)                                                                                                                                                                              | `o/700000`                    |
| `p/`   | **Phone number** of a Client                                            | [Add Client](#adding-a-client-addc)<br/>[Edit Client](#editing-a-client--editc)                                                                                                                                                                            | `p/12345678`                  |
| `t/`   | **Tag** to specify a unique trait of a Listing or Client                | [Add Client](#adding-a-client-addc)<br/> [Add Listing](#adding-a-listing-addl) <br/> [Edit Client](#editing-a-client--editc)<br/> [Edit Listing](#editing-a-listing--editl)                                                                                | `t/4room`                     |

[Back to Table of Contents](#table-of-contents)
