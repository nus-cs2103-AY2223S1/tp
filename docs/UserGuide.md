---
layout: page
title: User Guide
---

JeeqTracker is a **desktop application created for home-based business owners / resellers that helps them manage the clients
that they interact with by keeping track of their `BUY / SELL` transactions, and `REMARKS` of clients.**
While it has a GUI (Graphical User Interface), most of the user interactions happen using a CLI (Command Line Interface).
If you can type fast, JeeqTracker can get business
interactions recorded faster and simpler than traditional GUI applications.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a client: `add`](#adding-a-client-add)
  * [Creating a transaction: `buy` or `sell` ](#creating-a-transaction-buy-or-sell)
  * [Clearing all entries: `clear`](#clearing-all-entries--clear)
  * [Creating a remark: `remark`](#creating-a-remark-remark)
  * [Deleting a client/transaction/remark: `delete`](#deleting-a-client--transaction--remark--delete)
  * [Editing a client/transaction/remark: `edit`](#editing-a-client--transaction--remark--edit)
  * [Exiting the program: `exit`](#exiting-the-program--exit)
  * [Filtering buy or sell transactions: `filter`](#filtering-the-transaction-display--filter)
  * [Getting the User Guide: `user_guide`](#getting-the-user-guide-user_guide)
  * [Listing all clients: `list`](#listing-all-clients--list)
  * [Locating clients by name: `find`](#locating-clients-by-name-find)
  * [Sorting the address book: `sort`](#sorting-the-address-book-sort)
  * [Viewing help: `help`](#viewing-help--help)
  * [Viewing a client: `view`](#viewing-a-client--view)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
  * [Archiving data files `coming in v2.0`](#archiving-data-files-coming-in-v20)
* [FAQ](#faq)
* [Command Summary](#command-summary)
* [Prefix Summary](#prefix-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `jeeqtracker.jar` from [here](https://github.com/AY2223S1-CS2103T-T09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your JeeqTracker.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will show the list of commands.<br>
   Some example commands you can try:

   * **`list`** : Lists all clients.

   * **`add`**`n/Alice a/311 Clementi Ave 2 p/9191919 e/alice@gmail.com t/friends` : Adds a client named `Alice` into the address book.

   * **`delete`**`1 m/client` : Deletes the client at the first index together with all their contacts and transactions in the current list.

   * **`find`**`James` : Finds `James` from the list of clients and display all clients with name containing `James`.

   * **`clear`** : Deletes all clients with all their remarks and transactions.

   * **`view`**`2` : Views the second client in the list and shows your transactions and remarks with him.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Alice`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/Alice n/Bob`, only `n/Bob` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Adding a client: `add`

Adds a client to the list.

Format: `add n/NAME a/ADDRESS p/PHONE e/EMAIL [t/TAG]...`

* `TAG` is optional.
* Multiple `TAG` can be tagged to the client.
* No restrictions on the phone input field and email input field, but a warning will be given if it deviates from the standard convention.
    * This facilitates more freedom to input phone numbers like `+606 89987755 (HOME)` and emails like `alice@company.com (WORK)`

> :warning: **You cannot add a client with a name that already exists in JeeqTracker**: Names are considered duplicates even if they differ by case sensitivity or white spaces!

Examples:
* `add n/Alice a/West Coast Park p/9876542 e/alice@gmail.com`
* `add n/John a/Yishun Street 81 p/9876543 e/john@yahoo.com t/friends t/supplier`

### Creating a Transaction: `buy` or `sell`

Creates a  `buy` or `sell` transaction linked to a client.

Formats:

`buy INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]`

`sell INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]`

* The `INDEX` refers to the index number shown in the displayed client list.
* The `INDEX` **must be a positive integer** e.g 1, 2, 3, …​
* The `INDEX` **must not contain any signs** e.g +1, -3, …​
* The `QUANTITY` refers to the amount of the goods transacted.
* The `QUANTITY` should only contain non-negative integers and be at least 1 digit long.
* The `GOODS` refers to the name of the goods transacted.
* The `GOODS` should only contain alphanumeric characters, and it should not be blank.
* The `PRICE` refers to the price of the goods transacted.
* The `PRICE` should be a positive number, and it should be at least 1 digit long.
* The `DATE` refers to the date of the transaction.
* The `DATE` should only be in the format of DD/MM/YYYY. If no `DATE` is entered,
  the default date will be the current date that the user enters the transaction.

Examples:
* `buy 3 q/100 g/apples price/1.5` creates a buy transaction from the 3rd client in the list.
* `sell 1 q/50 g/Chicken price/5.55 d/07/11/2000` creates a sell transaction to the 1st client in
the list on the 07/11/2000.

### Clearing all entries : `clear`

Clear all entries which include `clients`, `remarks` and `transactions` from JeeqTracker.

Format: `clear`

* All entries that are cleared cannot be retrieved.

Example:

* `clear` clears all Client's entries together with the Remarks and Transactions.

### Creating a Remark: `remark`

Creates a new remark for the specified client.

Format: `remark INDEX REMARK [t/TAG]...`

* The `INDEX` refers to the index number shown in the displayed client list.
* The `INDEX` **must be a positive integer** e.g 1, 2, 3, …​
* `TAG` is optional.
* Multiple `TAG` can be tagged to the client.
* The parameter `REMARK` cannot be empty.

Examples:
* `remark 1 punctual buyer` adds the remark `punctual buyer` to the client at index 1.
* `remark 5 fast and decisive t/favourite` adds the remark `fast and decisive` to the client at index 5. The remark 
also has a tag `favourite`. 

### Deleting a client / transaction / remark : `delete`
> :warning: **If you delete a client/transaction/remark, it will be gone forever**: Be very careful here!

Deletes the specified `client`, `transaction` or `remark` from JeeqTracker.

Format: `delete INDEX m/MODE`

* The `INDEX` refers to the index number shown in the displayed list.
* The `INDEX` **must be a positive integer** e.g 1, 2, 3, …​
* The `MODE` refers to which entity is being deleted. It must be `client`, `transaction` or `remark`.
* `view` command must be used before the deletion of `remark` or `transaction` as the deletion can only happen when they are visible in the application.

Examples:
* `delete 1 m/client` deletes the 1st client in the JeeqTracker.
* `list` followed by `delete 2 m/client` deletes the 2nd client in the JeeqTracker.
* `find koh` followed by `delete 1 m/client` deletes the 1st client in the results of the `find` command.
* `list` followed by `view 2` displays the remarks and transactions of the 2nd client in the JeeqTracker. 
Applying `delete 3 m/transaction` deletes the 3rd transaction of the client.
* `find john` followed by `view 1` displays the remarks and transactions of the 1st client of the `find`
result. Applying `delete 2 m/remark` deletes the 2nd remark of the client.

### Editing a client / transaction / remark : `edit`

Edits the details of the `client`, `transaction` or `remark` specified by the index number.

Format: `edit INDEX m/MODE FIELDS [MORE_FIELDS]...`

* The `INDEX` refers to the index number shown in the displayed list.
* The `INDEX` **must be a positive integer** e.g 1, 2, 3, …​
* The `INDEX` **must not contain any signs** e.g +1, -3, …​
* The `MODE` refers to which entity is being deleted. It must be `client`, `transaction` or `remark`.
* The `FIELDS` refers to the parameters to be changed for the entity. E.g:
  * client has `[n/NAME] [a/ADDRESS] [p/PHONE] [e/EMAIL] [t/TAG]`
  * transaction has `[q/QUANTITY] [g/GOODS] [price/PRICE] [d/DATE]`.
* `view` command must be used before editing `remark` or `transaction` as the edit can only happen when they are visible in the application.

Examples:
* `edit 1 m/client a/Blk 221 Yishun St 81` replaces the 1st client's address with the new input.
* `list` followed by `edit 5 m/client a/Blk 333 Clementi Ave 1 p/8765432` replaces the 5th index client's address and phone number with the new inputs.
* `find lee` followed by `edit 2 m/client e/lee123@gmail.com` replaces the 2nd client's email of the `find` result with the new inputs.
* `list` followed by `view 2` displays the remarks and transactions of the 2nd client in the JeeqTracker.
Applying `edit 3 m/transaction price/1.9` edits the price of the 3rd transaction of the client.
* `find lim` followed by `view 3` displays the remarks and transactions of the 3rd client of the `find` 
result. Applying `edit 3 m/remark supplier` edits the 3rd remark of the client.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

* All entries will not be deleted.
* All information edited will be saved.

Examples:
* `exit` closes the program.

### Filtering the transaction display : `filter`

Filters the buy or sell transactions of all the clients.

Format: `filter TYPE`

* The `TYPE` refers to the type of transactions to be displayed. 
It can only be `buy` or `sell`.
* If no clients made any transactions, the transaction section will be blank.
* After calling the `filter` command, the client list panel will display all clients.

Examples:
* `filter buy` displays all buy transactions.
* `filter sell` displays all sell transactions.

### Getting the User Guide: `user_guide`

Returns the url to this user guide.

Format: `user_guide`

* Displays a pop-up that contains the url to this user guide.
* You can click on the `Copy URL` button to copy this url to your clipboard.

Examples:
* `user_guide` returns the url of this user guide.

### Listing all clients : `list`

Lists all the clients stored in JeeqTracker.

Format: `list`

* Displays all the clients and their details in JeeqTracker.
* If JeeqTracker is empty, the clients name section will be blank.

Examples:

* `list` displays all stored clients name and details.

### Locating clients by name: `find`

Find clients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]...`

* The search is case-insensitive. e.g `alice` will match `Alice`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Jame` will not match `James`
* Clients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `John Bob Lim` will return `John Koh`, `Bob Tan`

Examples:
* `find John` return clients `John`, `John Lim`, `John Koh`
* `find Tan` return clients `John Tan`, `Bob Tan`, `Alice Tan`

### Sorting the address book: `sort`

Sorts the specified client's transaction by either the latest transaction or oldest transaction.

Format: `sort INDEX ORDER`

* The `INDEX` refers to the index number shown in the displayed client list.
* The `INDEX` **must be a positive integer** e.g 1, 2, 3, …​
* The `INDEX` **must not contain any signs** e.g +1, -3, …​
* The `ORDER` refers to how the transaction will be sorted. It can only be sorted by
  `oldest` or `latest`.
* If no clients made any transactions, the transaction section will be blank.

Examples:
* `sort 1 latest` displays the 1st client transactions sorted by the latest transaction on top.
* `sort 5 oldest` displays the 5th client transactions sorted by the oldest transaction on top.

### Viewing help : `help`

`help` returns the list of all commands. `help [COMMAND]` returns the detailed description of that specified command.

[//]: # (![help message]&#40;images/helpMessage.png&#41;)

Format: `help` or `help [COMMAND]`

* The `COMMAND` refers to any valid command that is implemented.

Examples:
* `help` shows the list of valid commands.
* `help find` shows the description of `find` command.
* `help add` shows the description of `add` command.

### Viewing a client : `view`

Displays the remarks and transactions of the specified client. 
The client list will be only display the specified client.

Format: `view INDEX`

* Views the client at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed client list.
* The `INDEX` **must be a positive integer** 1, 2, 3, …​
* The `INDEX` **must not contain any signs** e.g +1, -3, …​

Examples:
* `view 5` displays the remarks and transactions of the client at the 5th index.

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

| Action         | Format, Examples                                                                                                                                       |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**        | `add n/CLIENT a/ADDRESS p/PHONE e/EMAIL [t/TAG]...`<br> e.g., `add n/Alice a/Yishun Street 81 p/9876543 e/alice@gmail.com`                             |
| **Buy**        | `buy INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]` <br/> e.g., `buy 2 q/100 g/apples price/1.5`                                                       |
| **Clear**      | `clear`                                                                                                                                                |
| **Delete**     | `delete INDEX m/MODE`<br> e.g., `delete 3 m/client` or `view 1` followed by `delete 3 m/remark`                                                        |
| **Edit**       | `edit INDEX m/MODE FIELDS [MORE_FIELDS]...` <br> e.g.,`edit 1 a/Blk 221 Yishun St 81 p/818181` or `view 1` followed by `edit 3 m/transaction g/mango ` |
| **Exit**       | `exit`                                                                                                                                                 |
| **Filter**     | `filter TYPE` <br> e.g., `filter sell` or `filter buy`                                                                                                 |
| **Find**       | `find KEYWORD [MORE_KEYWORDS]...`<br> e.g., `find John`                                                                                                |
| **Help**       | `help [COMMAND]` <br> e.g.,`help` or `help add` or `help sort`                                                                                         |
| **List**       | `list`                                                                                                                                                 |
| **Remark**     | `remark INDEX REMARK [t/TAG]...`<br> e.g., `remark 3 Punctual Buyer` or `remark 5 Fast and Decisive Buyer t/favourite`                                 |                                                                     |
| **Sell**       | `sell INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]` <br/> e.g., `sell 2 q/100 g/apples price/1.5 d/07/11/2022`                                        |
| **Sort**       | `sort INDEX ORDER` <br> e.g.,`sort 1 latest` or `sort 3 oldest`                                                                                        | 
| **User Guide** | `user_guide`                                                                                                                                           |
| **View**       | `view INDEX` <br> e.g., `view 5`                                                                                                                       |

## Prefix Summary

| Prefix     | Meaning                                        | Restrictions                                        | Example                       |
|------------|------------------------------------------------|-----------------------------------------------------|-------------------------------|
| **n/**     | Name of the client                             | Alphanumeric characters and spaces, required        | `n/Alice`                     |
| **a/**     | Address of the client                          | Required                                            | `a/321 Clementi Road, #02-22` |
| **p/**     | Phone number of the client                     | Required                                            | `p/98765432`                  |
| **e/**     | Email of the client                            | Required                                            | `e/alice@gmail.com`           |
| **g/**     | Goods name involved in the transaction         | Alphanumeric characters and spaces, required        | `g/Apples`                    |
| **q/**     | Quantity of goods involved in the transaction  | Positive integer, required                          | `q/10`                        |
| **price/** | Price per quantity of goods in the transaction | Positive number, required                           | `price/1.50`                  |
| **m/**     | Mode of the command                            | Must be either `client`, `transaction`, or `remark` | `m/client`                    |
| **d/**     | Date of transaction                            | In the format `dd/mm/yyyy`                          | `d/07/11/2022`                |
| **t/**     | Tag applied on clients                         | Alphanumeric, single word                           | `t/friends`                   |
