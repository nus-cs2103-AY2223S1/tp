---
layout: page
title: User Guide
---

ArtBuddy (AB) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while
still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB can get your contact management
tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `artbuddy.jar` from [here](https://github.com/AY2223S1-CS2103T-W11-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ArtBuddy.

4. Double-click the file to start the app. The GUI should display a list of customers. This view will be referred to as Customer View. <br> ![Ui](images/Ui.png)
5. The following commands are available in Customer View:

   * **`addcus`**`n/John Doe e/johnd@example.com a/John t/animal cartoons t/vip` :  Creates a new customer named John Doe.
   * **`delcus`**`4` :  Deletes the 4th customer displayed on the list.
   * **`opencus`**`2` : Opens the 2nd customer (Simon) on the list and brings you to the Commission View for Simon.

6. In the Commission View, the list of all commissions from the opened customer (Simon) is displayed. <br> ![Ui](images/Ui.png)
7. The following commands are available in Commission View:
    * **`addcom`**` n/Tokyo Ghoul Fan art f/50 d/2022-10-10` : Creates a new commission titled Tokyo Ghoul Fan art under Simon.
    * **`delcom`**`5` : Deletes the 5th commission from Simon on the list.
    * **`opencom`**`1` : Opens the 1st commission titled Digital half-body portrait and brings you to the Commission Details View.
8. In the Commission Details View, the title, description, image thumbnails and deadline of the opened commission (Digital half-body portrait) is displayed. ![Ui](images/UiCommissionDetails.png)
9. The following commands are available in Commission Details View:
   * **`additer`**`/Users/Joseph/CSP/half-body-portrait.png` : Attaches the image at the specified file path to the opened commission.
   * **`openimg`** : Expands the attached image for the commission for better view
10. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a customer: `addcus`

Adds a customer to ArtBuddy.

Format: `addcus n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A customer can have any number of tags (including 0)
</div>

Examples:
* `addcus n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 t/animal cartoons t/vip`
Creates the customer entry for John Doe with his details including multiple tags. 
* `addcus n/Betsy Crowe e/betsycrowe@example.com p/12345867`
Creates the customer entry for Betsy Crowe with her email and phone number.

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Adding a commission: `addcom`

Adds a commission to the currently opened customer.

Format: `addcom n/TITLE f/FEE d/DEADLINE [t/TAG]…​`

Examples:
* `addcom n/Rimuru f/40 d/2022-11-01 t/traditional t/chibi` creates the commission entry titled "Rimuru" with the given fee, due date and tags.
* `addcom n/Tokyo Ghoul Kaneki f/50 d/2022-10-15` creates a commission entry titled with the given fee and due date.

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Viewing a commission: `opencom` [coming soon]

Opens a commission and shows its relevant details and image.

Format: `opencom INDEX`

* This index is one-indexed, based on the customer’s commission list.

Example:
* `opencom 14`

### Deleting a commission: `delcom` [coming soon]

Deletes a commission and images related to the commission.

Format: `delcom INDEX`

* This index is one-indexed, based on the customer’s commission list.

Example:
* `delcom 14`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ArtBuddy data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ArtBuddy data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ArtBuddy will discard all data and start with an empty data file at the next run.
</div>

### Listing all customers `[coming in v2.0]`

_Details coming soon ..._

### Editing a customer `[coming in v2.0]`

_Details coming soon ..._

### Locating customer by name `[coming in v2.0]`

_Details coming soon ..._

### Clearing all customers `[coming in v2.0]`

_Details coming soon ..._

### Clearing all commissions `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add customer** | `addcus n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]…​`  <br> e.g., `addcus n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 t/animal cartoons`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Open Commission** | `opencom INDEX`<br> e.g., `opencom 14`
**Delete Commission** | `delcom INDEX`<br> e.g., `delcom 14`
**Help** | `help`
