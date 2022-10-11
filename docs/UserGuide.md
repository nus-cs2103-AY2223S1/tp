---
layout: page
title: User Guide
---

JeeqTracker is a **desktop application built for small businesses lacking in resources to help them keep track of
supplier contacts and previous transaction information with them, optimised for use via a Command Line Interface (CLI)**,
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, JeeqTracker can get business
interactions recorded faster and simpler than traditional GUI applications.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a company: `add`](#adding-a-company-add)
  * [Clearing all entries: `clear`](#clearing-all-entries--clear)
  * [Creating a point-of-contact: `create`](#creating-a-person-create)
  * [Deleting a company: `delete`](#deleting-a-company--delete)
  * [Editing a company: `edit`](#editing-a-company--edit)
  * [Exiting the program: `exit`](#exiting-the-program--exit)
  * [Filtering the address book display: `filter`](#filtering-the-address-book-display--filter)
  * [Listing all companies: `list`](#listing-all-companies--list)
  * [Locating companies by name: `find`](#locating-companies-by-name-find)
  * [Viewing help: `help`](#viewing-help--help)
  * [Viewing a company: `view`](#viewing-a-company--view)
  * [Getting the User Guide: `user_guide`](#getting-the-user-guide-user_guide)
  * [Sorting the address book: `sort`](#sorting-the-address-book-sort)
  * [Creating a transaction: `transaction`](#creating-a-transaction-transaction)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
  * [Archiving data files `coming in v2.0`](#archiving-data-files-coming-in-v20)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `jeeqtracker.jar` from [here](https://github.com/AY2223S1-CS2103T-T09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your JeeqTracker.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all companies.

   * **`create`**`create 2 n/James p/97814456 e/test@test.com t/MainPoc t/owesMoney` : Creates a point-of-contact named `James` linked to the company at index 2.

   * **`delete`**`delete 1` : Deletes the company at the first index together with all their contacts and transactions in the current list.

   * **`find`**`MacDonalds` : Finds MacDonalds in the list of companies, and displays all its details (point-of-contact, transactions).

  * **`clear`** : Deletes all companies with all their contacts and transactions.

  * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `create coy/COMPANY`, `COMPANY` is a parameter which can be used as `add coy/MacDonalds`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `coy/KFC coy/MacDonalds`, only `coy/MacDonalds` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Adding a company: `add`

Adds an empty company to the list without any POC (Point-Of-Contact) and transactions.

Format: `add n/NAME a/ADDRESS [t/TAG]...`

* Tags are optional.
* Multiple tags can be tagged to the company.
* Use `create` command to add POC to the company.

Examples:
* `add n/MacDonalds a/West Coast Park`
* `add n/KFC a/Yishun Street 81 t/FriedChicken`

### Clearing all entries : `clear`

Clears all entries from JeeqTracker.

Format: `clear`

* A confirmation message will appear after the command.
* Input `confirm` to proceed with the clearing of all entries.

Example:

* `clear` followed by `confirm`

### Creating a Person: `create`

Creates a new person (point-of-contact) for a specific company.

Format: `create INDEX n/NAME p/NUMBER e/EMAIL [t/TAG]...`

* Tags are optional.
* Multiple tags can be tagged to the point-of-contact.
* The index refers to the index number shown in the displayed company list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `create 1 n/James p/82692192 e/JamesSho@KFC.com` adds James as a point-of-contact for the company at index 1.
* `create 5 n/John p/95692192 e/John123@ColdStorage.com t/Manager t/MainPoc` tags can also be added.

### Deleting a company : `delete`

Deletes the specified company from JeeqTracker.

Format: `delete INDEX`

* Deletes the company at the specified `INDEX`.
* The index refers to the index number shown in the displayed company list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd company in the address book.
* `find cold` followed by `delete 1` deletes the 1st company in the results of the `find` command.

### Editing a company : `edit`

Coming Soon.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Filtering the address book display : `filter`

Coming Soon.

### Listing all companies : `list`

Lists all the companies stored in JeeqTracker.

Format: `list`

* Displays all the companies and their details in JeeqTracker.
* If address book is empty, the companies name section will be blank.

Examples:

* `list` Displays all stored companies name and details.

### Locating companies by name: `find`

Find companies whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `kfc` will match `KFC`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `MacDonald` will not match `MacDonalds`
* Companies matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Cold Storage` will return `Urban Storage`, `Cold Drink Store`

### Getting the User Guide: `user_guide`

Returns the url to our user guide.

Format: `user_guide`

* Displays a pop-up that contains the link to our user guide.

### Viewing help : `help`

`help` returns the list of all commands. `help [COMMAND]` returns the detailed description of that specified command.

[//]: # (![help message]&#40;images/helpMessage.png&#41;)

Format: `help` or `help [COMMAND]`

Examples:
* `help` Shows the list of commands.
* `help find` Shows the description of `find` command.
* `help add` Shows the description of `add` command.

Examples:
* `find Mac` returns `MacDonald`
* `find fairprice` returns `NTUC Fairprice` <br>

### Viewing a company : `view`

`view` displays the points of contact and transaction log of the specified company.

Format: `view INDEX`

* Views the company at the specified `INDEX`.
* The index refers to the index number shown in the displayed company list.
* The index **must be a positive integer** 1, 2, 3, …​

### Sorting the address book: `sort`

Coming soon.

### Creating a Transaction: `transaction`

Creates a transaction related to a company (buy/sell).

Format:
- `transaction coy/COMPANY_NAME  g/GOODS  q/QUANTITY  pr/PRICE d/BUY`
- `transaction coy/COMPANY_NAME  g/GOODS q/QUANTITY  pr/PRICE d/SELL`

Examples:
- `transaction coy/McDonalds g/apples q/100 pr/1.5 d/BUY`
- `transaction coy/KFC g/Chicken q/50 pr/5.55 d/SELL`

### Saving the data

JeeqTracker data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

JeeqTracker data are saved as a JSON file `[JAR file location]/data/jeeqtracker.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, JeeqTracker will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous JeeqTracker home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action          | Format, Examples                                                                                                                                                                                                                                                           |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `add n/COMPANY a/ADDRESS [t/TAG] `<br> e.g., `add n/KFC a/Yishun Street 81 t/FavouriteFood`                                                                                                                                                                                |
| **Clear**       | `clear` followed by `confirm`                                                                                                                                                                                                                                              |
 | **Create**      | `create INDEX n/NAME p/NUMBER e/EMAIL [t/TAG]`<br> e.g., `create 1 n/James p/82692192 e/JamesSho@example.com t/MainPoc`                                                                                                                                                    |
| **Delete**      | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                        |
| **Edit**        | Coming Soon                                                                                                                                                                                                                                                                |
| **Exit**        | `exit`                                                                                                                                                                                                                                                                     |
| **Find**        | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find MacDonalds`                                                                                                                                                                                                                 |
| **Filter**      | Coming Soon                                                                                                                                                                                                                                                                |
| **List**        | `list`                                                                                                                                                                                                                                                                     |
| **Help**        | `help` or `help [COMMAND]` <br> e.g.,`help` or `help add` or `help sort`                                                                                                                                                                                                   |
| **View**        | `view INDEX`<br> e.g., `view 3`                                                                                                                                                                                                                                            |
| **Sort**        | Coming Soon                                                                                                                                                                                                                                                                |
| **Transaction** | `transaction coy/COMPANY_NAME g/GOODS q/QUANTITY pr/PRICE d/BUY` <br/> e.g `transaction coy/McDonalds g/apples q/100 pr/1.5 d/buy` <br/> `transaction coy/COMPANY_NAME g/GOODS q/QUANTITY pr/PRICE d/SELL` <br/> e.g. `transaction coy/KFC g/Chickens q/50 pr/2.55 d/SELL` |
| **User Guide**  | `user_guide`                                                                                                                                                                                                                                                               |

