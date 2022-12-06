---
layout: page
title: User Guide
---

## Introduction

JeeqTracker is a desktop application created for **home-based business owners**. It helps 
you keep track of your clients’ contact details, transaction records, and remarks about the client. 

If this is your first time using our User Guide, you might want to read this section on [how to use this User Guide](#how-to-use-the-user-guide)

## Table of Contents

* [About JeeqTracker](#about-jeeqtracker)
* [Quick Start](#quick-start)
* [User Interface Overview](#user-interface-overview)
* [How to use the User Guide](#how-to-use-the-user-guide)
* [Features](#features)
  * [Client Commands](#client-commands)
    * [Adding a client: `add`](#adding-a-client-add)
    * [Listing all clients: `list`](#listing-all-clients-list)
    * [Editing a client: `edit`](#editing-a-client-edit)
    * [Deleting a client: `delete`](#deleting-a-client-delete)
    * [Locating clients by name: `find`](#locating-clients-by-name-find)
    * [Viewing a client: `view`](#viewing-a-client-view)
  * [Transaction Commands](#transaction-commands)
    * [Creating a transaction: `buy` or `sell` ](#creating-a-transaction-buy-or-sell)
    * [Editing a transaction: `edit`](#editing-a-transaction-edit)
    * [Deleting a transaction: `delete`](#deleting-a-transaction-delete)
    * [Filtering buy or sell transactions: `filter`](#filtering-buy-or-sell-transactions-filter)
    * [Sorting the transactions: `sort`](#sorting-the-transactions-sort)
  * [Remark Commands](#remark-commands)
    * [Creating a remark: `remark`](#creating-a-remark-remark)
    * [Editing a remark: `edit`](#editing-a-remark-edit)
    * [Deleting a remark: `remark`](#deleting-a-remark-delete)
  * [Miscellaneous Commands](#miscellaneous-commands)
    * [Clearing all entries: `clear`](#clearing-all-entries-clear)
    * [Exiting the program: `exit`](#exiting-the-program-exit)
    * [Viewing help: `help`](#viewing-help-help)
    * [Getting the User Guide: `user_guide`](#getting-the-user-guide-user_guide)
  * [Data Storage](#data-storage)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
    * [Archiving data files `coming in v2.0`](#archiving-data-files-coming-in-v20)
* [FAQ](#faq)
* [Command Summary](#command-summary)
* [Prefix Summary](#prefix-summary)

--------------------------------------------------------------------------------------------------------------------

## About JeeqTracker

JeeqTracker is an **extremely simple and easy-to-use application** that will help you to keep track of your clients’ 
**contact details**, **transaction records**, and **remarks** about the client. 

Due to the nature of a home-based business, you will probably be thinking of how you can keep your clients engaged
and increase customers satisfaction. With JeeqTracker, you can analyze and understand the demands of each client and provide a more personalized 
service that would meet the needs of your client, increasing customer engagement.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have **Java 11** installed in your Computer. If you do not have Java, you can download it from 
[here for Window Users](https://www.oracle.com/java/technologies/downloads/#java11) and [here for Mac Users](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).

2. Download the latest **jeeqtracker.jar** from [here](https://github.com/AY2223S1-CS2103T-T09-1/tp/releases).

3. Copy the file to the folder you want to use as the **home folder** for your JeeqTracker.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the application contains some sample data.<br><br>
   ![Ui](images/QuickStart.png)

5. Type the command in the command box and press Enter to execute it.<br> e.g. typing **`help`** and pressing Enter will show the list of commands.<br>

   Some example commands you can try:

   * **`list`** : Lists all clients.

   * **`add`**`n/Alice a/311 Clementi Ave 2 p/9191919 e/alice@gmail.com t/friends` : Adds a client named **Alice** into JeeqTracker.

   * **`delete`**`1 m/client` : Deletes the client at the first index together with all their transactions and remarks in the current list.

   * **`find`**`Bernice` : Finds **Bernice** from the list of clients and display all clients with name containing **Bernice**. If there is only one find result, you will be able to see the transactions and remarks of **Bernice** as well.

   * **`view`**`2` : Views the second client in the list and shows your transactions and remarks with him.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## User Interface Overview

![UiOverview](images/UiOverview.png)

This application UI is split into **5 sections**.
* ***Input Command***: This is where you should key in your commands, and press enter to execute
* ***Application's Reply***: Errors or success messages will appear here after you execute any commands
* ***List Of Clients***: Every client that you have will appear here
* ***Transaction Details***: A list of transactions with a specific client will appear here if you use the [view command](#viewing-a-client-view), or it will show all transactions with every client if you use the [filter command](#filtering-buy-or-sell-transactions-filter)
* ***Remarks Of Specified Client***: Remarks of specified client will appear here
* ***Net amount*** : This section displays the net amount transacted with all clients currently displayed in the *List Of Clients* panel on the left. Currently, only **dollars** is supported in our application. More currency will be added in the future.

> <div markdown="span" class="alert alert-primary">:bulb: Tip: These user interface sections will be referred to several times in the User Guide, it will be in italics!</div>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## How to use the User Guide

This user guide contains detailed explanation on how to use the application. It provides information like **what each command does**, and **how data is saved in the application**.

The [table of contents](#table-of-contents) provides links to every command. But before you head there, below are some notes on how to interpret each command format.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in **UPPER_CASE** are the parameters to be supplied by you.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Alice`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/Alice n/Bob`, only `n/Bob` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

* A list of prefixes that you will encounter can be found [here](#prefix-summary).

</div>

### Symbols and Annotations

Below are some symbols and annotations that you may encounter in the User Guide.

| Symbol / Annotation                                                                   | Meaning                                                                               |
|---------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| <div markdown="span" class="alert alert-danger">:exclamation: Danger</div>            | Something that could cause **irreversible damage** when done incorrectly              |
| <div markdown="span" class="alert alert-warning">:warning: Warning</div>              | A **potential source of error**, and should be noted                                  |
| <div markdown="span" class="alert alert-primary">:bulb: Tip</div>                     | Tips that can aid you to **optimally utilise** JeeqTracker                            |
| <div markdown="block" class="alert alert-info">:information_source: Information</div> | **Important information** to take note of                                             |
| **Bolded Text**                                                                       | Emphasizes **important terms** to look out for                                        |
| `Text wrapped in a box`                                                               | Denotes terms related to a **command** or **field**                                   |
| *Italic Text*                                                                         | Denotes a **user interface component** name, located [here](#user-interface-overview) | 


> <div markdown="span" class="alert alert-primary">:bulb: Tip: It is recommended to understand the [User Interface](#user-interface-overview) as it will help you to navigate the application easily!</div>

> <div markdown="span" class="alert alert-primary">:bulb: Tip: The term **INDEX** will appear in several command formats.<br/> **Red circle** in the diagram below refers to the **INDEX** for **clients**.<br/>**Blue circles** refer to the **INDEX** for **transactions**.<br/>**Green circles** refer to the **INDEX** For **remarks**.</div>

![IndexMeaning](images/IndexMeaning.PNG)

You are now ready to use the application! Head over to [Quick Start](#quick-start) to start using the application.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features

This section showcases **all** commands in JeeqTracker that you can use. It is split into five sections, namely **Client Commands**, **Transaction Commands**, **Remark Commands**, **Miscellaneous Commands**, and **Data Storage**.

### **Client Commands**

The following section highlights **all** commands related to **clients**.

### Adding a client: `add`

Adds a new client to your *List Of Clients* panel.

Format: `add n/NAME a/ADDRESS p/PHONE e/EMAIL [t/TAG]...`

* `TAG` is optional.
* Multiple `TAG` can be tagged to the client.
* No restrictions on the `PHONE` input and `EMAIL` input. You can enter anything that you want.<br/>However, a warning will be given if it deviates from the standard convention.
    * This facilitates more freedom to input phone numbers like `+606 89987755 (HOME)` and emails like `alice@company.com (WORK)`

| Field    | Standard Convention                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `PHONE`  | Contains only numbers, and at least 3 digits long                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| `EMAIL`  | Format: `local-part@domain`<br/>1. **Local-part** should only contain **alphanumeric characters** and these special characters `+`, `_`, `.`, `-`. The **local-part** may not start or end with any special character.<br/>2. This is followed by a **@** and then a **domain name**. The **domain name** is made up of domain labels separated by periods.<br/> The **domain name** must:<br/> - end with a domain label at least 2 characters long<br/>- have each domain label start and end with **alphanumeric characters**<br/>- have each domain label consist of **alphanumeric characters**, separated only by hyphens, if any |

> <div markdown="span" class="alert alert-warning">:warning: **Warning: You cannot add a client with a name that already exists in JeeqTracker**<br/> Names are considered duplicates even if they differ by case sensitivity or whitespaces!

Examples:
* `add n/Alice a/West Coast Park p/9876542 e/alice@gmail.com` adds a new client, **Alice** with the specified **address**, **phone** and **email** to the *List Of Clients* panel, as depicted by the diagram below.
* `add n/John a/Yishun Street 81 p/9876543 e/john@yahoo.com t/friends t/supplier` adds a new *client*, **John** with the specified **address**, **phone**, **email**, and **tags** to the *List Of Clients* panel.

![Ui](images/SampleAddCommand.png)

[Back to Table of Contents](#table-of-contents)

### Listing all clients: `list`

Lists all the clients stored in JeeqTracker. 
This command is required for you because some other commands like `find`, `sort`, `view` will filter the *List Of Clients* panel.

Format: `list`

* Displays all the clients and their details (`Name`, `Address`, `Phone`, `Remarks`, `Total`) in JeeqTracker.
* If JeeqTracker is empty, the *List Of Clients* panel will be blank.

[Back to Table of Contents](#table-of-contents)

### Editing a client: `edit`

Edits the details of a client.

Format: `edit INDEX m/client FIELDS [MORE_FIELDS]...`

| Parameter | Description                                                                                                                                                                                                                                                           |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`   | Refers to the index number shown in the *List Of Clients* panel. <br/> - **Must be positive integer** e.g 1, 2, 3, …​ <br/> - **Must not contain any signs** e.g +1, -3, …​                                                                                           |
| `FIELDS`  | Refers to the parameters to be changed for the client <br/> - `[n/NAME]` <br/> - `[a/ADDRESS]` <br/> - `[p/PHONE]` <br/> - `[e/EMAIL]`<br/> - `[t/TAG]`: Multiple tags are allowed in this command as the entire existing tag list will be replaced with the new tags |


Examples:
* `edit 1 m/client a/Blk 221 Yishun St 81` replaces the 1<sup>st</sup> client's **address** with the new input.
* `edit 2 m/client a/Blk 333 Clementi Ave 1 p/8765432` replaces the 2<sup>nd</sup> client's **address** and **phone number** with the new inputs.
* `edit 3 m/client n/John tan` replaces the 3<sup>rd</sup> client's **name** with the new input.

[Back to Table of Contents](#table-of-contents)

### Deleting a client: `delete`

Deletes a specified client from JeeqTracker.

Format: `delete INDEX m/client`

* The `INDEX` refers to the index number shown in the *List Of Clients* panel.
* `INDEX` **must be a positive integer** e.g 1, 2, 3, …​

Examples:
* `delete 1 m/client` deletes the 1<sup>st</sup> client in the displayed *List Of Clients* panel.
* `delete 2 m/client` deletes the 2<sup>nd</sup> client in the displayed *List Of Clients* panel.

> <div markdown="span" class="alert alert-danger">❗ **Danger: Deleting a client is an irreversible process**<br/> Be very careful here!

[Back to Table of Contents](#table-of-contents)

### Locating clients by name: `find`

Finds clients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]...`

* The search is case-insensitive. e.g. **alice** will match **Alice**
* The order of the keywords does not matter. e.g. **Hans Bo** will match **Bo Hans**
* Only the name is searched.
* Only full words will be matched e.g. **Jame** will not match **James**
* Clients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `John Bob Lim` will return **John Koh**, **Bob Tan**

Examples:
* `find John` returns clients **John**, **John Lim**, **John Koh**
* `find Tan` returns clients **John Tan**, **Bob Tan**, **Alice Tan**

[Back to Table of Contents](#table-of-contents)

### Viewing a client: `view`

Displays the **remarks** and **transactions** of the specified client.
The *List Of Clients* panel will be filtered to display only the specified client.

Format: `view INDEX`

* Views the client at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed *List Of Clients* panel. 
* The `INDEX` **must be a positive integer** 1, 2, 3, …​
* The `INDEX` **must not contain any signs** e.g +1, -3, …​

Example:
* `view 5` displays the **remarks** and **transactions** of the client at the 5<sup>th</sup> index.

![Sample View Image](images/SampleViewCommand.PNG)

> <div markdown="span" class="alert alert-primary">:bulb: Tip: Use the [list](#listing-all-clients-list) command if you want to get back the full list of clients</div>

[Back to Table of Contents](#table-of-contents)

### **Transaction Commands**

The following section highlights **all** commands related to transactions.

### Creating a transaction: `buy` or `sell`

Adds a `buy` or `sell` transaction to the *Transaction Details* panel of a specified client.

Formats:

`buy INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]`

`sell INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]`

Parameter constraints:

| Parameter  | Constraints                                                                                                                                                                                          |
|:-----------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`    | - Refers to the index number shown in the *List Of Clients* panel. <br/> - **Must be a positive integer** e.g 1, 2, 3, …​ <br/> - **Must not contain any signs** e.g +1, -3, …​                      |
| `QUANTITY` | - Refers to the amount of the goods transacted. <br/> - **Must be a positive integer**<br/> - **Must not contain any signs**                                                                         |
| `GOODS`    | - Refers to the name of the goods transacted. <br/> - **Must contain alphanumeric characters**<br/>- **Must not be blank**.                                                                          |
| `PRICE`    | - Refers to the price of the goods transacted. <br/> - **Must be a positive number** e.g. 1.5, 2.3, 5<br/>- **Must not contain any signs** <br/>- **Prices will be rounded up to 2 decimal places.** |
| `DATE`     | - Refers to the date of the transaction. <br/> - **Must be in the format of DD/MM/YYYY.** <br/> - If date is left empty, the **current date** will be used.                                          |

Examples (refer to diagram below as well):
* `buy 3 q/100 g/apples price/1.5` creates a buy transaction for the 3<sup>rd</sup> client, **Charlie** in the *List Of Client* panel.
* `sell 1 q/50 g/Chicken price/5.55 d/07/11/2000` creates a sell transaction for the 1<sup>st</sup> client, **Alice** in
the *List Of Client* panel on the 07/11/2000.

![Ui](images/SampleBuySellCommand2.PNG)

> <div markdown="span" class="alert alert-primary">:bulb: Tip: If you use the "buy" or "sell" command while the List Of Clients panel has more than one client, you will not be able to see the addition.<br/>Use the [view](#viewing-a-client-view) command to see it.</div>

[Back to Table of Contents](#table-of-contents)

### Editing a transaction: `edit`

Edits a transaction specified by the index number.

Format: `edit INDEX m/transaction FIELDS [MORE_FIELDS]...`

| Parameter | Constraints                                                                                                                                                                                    |
|:----------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`   | - Refers to the index number shown in the *Transaction Details* panel. <br/> - **Must be a positive integer within the range displayed**<br/>  **- Must not contain any signs** e.g +1, -3, …​ |
| `FIELDS`  | - Refers to the parameters to be changed for the transaction.<br/> - `[q/QUANTITY]`<br/> - `[g/GOODS]`<br/> - `[price/PRICE]`<br/> - `[d/DATE]`                                                |

Example (refer to diagram below):

`edit 1 m/transaction q/100 g/Apples price/1.5` edits the transaction at index 1 in the *Transaction Details* panel, to have a **new name: Apples**, **quantity: 100**, **price: 1.5**.

> <div markdown="span" class="alert alert-warning">:warning: Warning: You must use [view](#viewing-a-client-view) command first before you can edit a transaction. If not, there will be an error within the application!


![EditTransactionExample](images/EditTransactionExample.PNG)

[Back to Table of Contents](#table-of-contents)

### Deleting a transaction: `delete`

Deletes a transaction specified by the index number.

Format: `delete INDEX m/transaction`

* `INDEX` refers to the index number shown in the *Transaction Details* panel. 
* `INDEX` must be a **positive integer**, and must not contain any signs e.g. +1, -3.

Example:

`delete 1 m/transaction` deletes the transaction at index 1 in the *Transaction Details* panel.

> <div markdown="span" class="alert alert-warning">:warning: Warning: You must use [view](#viewing-a-client-view) command first before you can delete a transaction. If not, there will be an error within the application!

> <div markdown="span" class="alert alert-danger">❗ Danger: **Deleting a transaction is an irreversible process! It will be gone forever**: Be very careful here!

[Back to Table of Contents](#table-of-contents)

### Filtering buy or sell transactions: `filter`

Filters the buy or sell transactions of all the clients. After executing this command, the filtered transactions will be 
displayed in the *Transaction Details* panel while the *List Of Clients* panel will display all the clients.

Format: `filter TYPE`

* The `TYPE` refers to the type of transactions to be displayed.
  It can only be `buy` or `sell`.
* If no clients made any transactions, the *Transaction Details* panel will be blank.
* After calling the `filter` command, the *List Of Clients* panel will display all clients.

Examples:
* `filter buy` displays all buy transactions.
* `filter sell` displays all sell transactions.

> <div markdown="span" class="alert alert-primary">:bulb: Tip: The transactions listed in List of Transactions panel does not belong to anyone, it is all transactions with all your clients</div>

[Back to Table of Contents](#table-of-contents)

### Sorting the transactions: `sort`

Sorts the specified client's transaction by either the **latest transaction** or **oldest transaction** (defined by date).

Format: `sort INDEX ORDER`
* The *Transaction Details* panel will be empty if the client does not have any transactions.

| Parameter | Constraints                                                                                                                                                                               |
|:----------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`   | - Refers to the index number shown in the *List Of Clients* panel.<br/> - **Must be a positive integer within the range displayed**<br/>  **- Must not contain any signs** e.g +1, -3, …​ |
| `ORDER`   | - Refers to how the transaction will be sorted. <br/> - Order of sorting can only be `oldest` or `latest`.<br/>                                                                           |


Examples:
* `sort 1 latest` displays the 1<sup>st</sup> client transactions sorted by the **latest transaction** in the *Transaction Details* panel.

![Sample Sort Command](images/SampleSortCommand.PNG)

> <div markdown="span" class="alert alert-primary">:bulb: Tip: The sort command will filter the List Of Clients panel to display only the specified client.<br/>Use the [list](#listing-all-clients-list) command to get back the full list of clients.</div>

[Back to Table of Contents](#table-of-contents)

### **Remark Commands**

The following section highlights **all** commands related to remarks.

### Creating a remark: `remark`

Creates a new remark for the specified client.

Format: `remark INDEX REMARK`

| Parameter | Constraints                                                                                                                                                                               |
|:----------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`   | - Refers to the index number shown in the *List Of Clients* panel.<br/>- **Must be a positive integer within the range displayed.**<br/>  **- Must not contain any signs** e.g +1, -3, …​ |
| `REMARK`  | - Refers to the new remark.<br/>- **Must be filled**                                                                                                                                      |                                                                                                      |


Examples:
* `remark 1 punctual buyer` adds the remark **punctual buyer** to the client at index **1** of the *List Of Clients* panel.


> <div markdown="span" class="alert alert-primary">:bulb: Tip: If you use the `remark` command while the List of Clients Panel has more than one client, you will not be able to see the addition.<br/>Use the [view](#viewing-a-client-view) command to see it.</div>


[Back to Table of Contents](#table-of-contents)

### Editing a remark: `edit`

Edits a remark for a specified client.

Format: `edit INDEX m/remark REMARK`

| Parameter    | Constraints                                                                                                                                                                                            |
|:-------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`      | - Refers to the index number shown in the *Remarks Of Specified Client* panel. <br/> - **Must be a positive integer within the range displayed**<br/>  **- Must not contain any signs** e.g +1, -3, …​ |
| `REMARK`     | - Refers to the new remark.<br/>- It must be filled.                                                                                                                                                   |

Example:

`edit 1 m/remark Client replies very fast` edits the remark at index **1** to **Client replies very fast** in the *Remarks of Specified Client* Panel. 

> <div markdown="span" class="alert alert-warning">:warning: Warning: You must use [view](#viewing-a-client-view) command first before you can edit a remark. If not, there will be an error within the application!

[Back to Table of Contents](#table-of-contents)

### Deleting a remark: `delete`

Deletes a remark for a client.

Format: `delete INDEX m/remark`

* `INDEX` refers to the index number shown in the *Remarks Of Specified Client* panel.
* **Must be a positive integer within the range display, and must not contain any signs** e.g. +1, -3.

> <div markdown="span" class="alert alert-warning">:warning: Warning: You must use [view](#viewing-a-client-view) command first before you can delete a remark. If not, there will be an error within the application!

> <div markdown="span" class="alert alert-danger">❗ Danger: **Deleting a remark is an irreversible process**: Be very careful here!

Example:

`delete 1 m/remark` deletes the remark at index 1 in the *Remarks Of Specified Client* panel.

[Back to Table of Contents](#table-of-contents)

### **Miscellaneous Commands**

This section contains **all** miscellaneous commands.

### Clearing all entries: `clear`

Clears all entries which include **clients**, **remarks** and **transactions** from JeeqTracker.

Format: `clear`

> <div markdown="span" class="alert alert-danger">❗ Danger: Information cleared by clear command cannot be retrieved. Only use this if you want to clear **all** data of **clients**, **remarks** and **transactions**.

Example:

* `clear` deletes all client's entries together with the remarks and transactions.

![Ui](images/SampleClearCommand.png)

[Back to Table of Contents](#table-of-contents)

### Exiting the program: `exit`

Exits the program.

Format: `exit`

* All entries will not be deleted.
* All information edited will be saved.

Example:
* `exit` closes the program.

[Back to Table of Contents](#table-of-contents)

### Viewing help: `help`

`help` returns the list of all commands in the *Application's Reply* panel. 

`help [COMMAND]` returns the detailed description of that specified command in the *Application's Reply* panel.

[//]: # (![help message]&#40;images/helpMessage.png&#41;)

Format: `help` or `help [COMMAND]`

* The `COMMAND` refers to any valid command that is implemented.

Examples:
* `help` shows the list of valid commands.
* `help find` shows the description of `find` command.
* `help add` shows the description of `add` command.

[Back to Table of Contents](#table-of-contents)

### Getting the User Guide: `user_guide`

Displays the link to this user guide in a pop-up window.

Format: `user_guide`

* Displays a pop-up that contains the url to this user guide.
* You can click on the `Copy URL` button to copy this url to your clipboard and 
paste it in your browser if you need to refer to this user guide.

![Ui](images/SampleUserguide.png)

[Back to Table of Contents](#table-of-contents)

## **Data Storage**

This section highlights how your data is stored with JeeqTracker.

### Saving the data

JeeqTracker data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

JeeqTracker data are saved as a JSON file "[JAR file location]/data/jeeqtracker.json". If you are an advanced user, you are welcome to update data directly by editing that data file.

> <div markdown="span" class="alert alert-danger">:exclamation: Danger: If your changes to the data file makes its format invalid, **JeeqTracker will discard all data** and start with an empty data file at the next run.

### Archiving data files [coming in v2.0]

_Details coming soon ..._

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous JeeqTracker home folder.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action         | Format, Examples                                                                                                                                                |
|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**        | `add n/CLIENT a/ADDRESS p/PHONE e/EMAIL [t/TAG]...`<br> e.g., `add n/Alice a/Yishun Street 81 p/9876543 e/alice@gmail.com`                                      |
| **Buy**        | `buy INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]` <br/> e.g., `buy 2 q/100 g/apples price/1.5`                                                                |
| **Clear**      | `clear`                                                                                                                                                         |
| **Delete**     | `delete INDEX m/MODE`<br> e.g., `delete 3 m/client` or `view 1` followed by `delete 3 m/remark`                                                                 |
| **Edit**       | `edit INDEX m/MODE FIELDS [MORE_FIELDS]...` <br> e.g.,`edit 1 m/client a/Blk 221 Yishun St 81 p/818181` or `view 1` followed by `edit 3 m/transaction g/mango ` |
| **Exit**       | `exit`                                                                                                                                                          |
| **Filter**     | `filter TYPE` <br> e.g., `filter sell` or `filter buy`                                                                                                          |
| **Find**       | `find KEYWORD [MORE_KEYWORDS]...`<br> e.g., `find John`                                                                                                         |
| **Help**       | `help [COMMAND]` <br> e.g.,`help` or `help add` or `help sort`                                                                                                  |
| **List**       | `list`                                                                                                                                                          |
| **Remark**     | `remark INDEX REMARK [t/TAG]...`<br> e.g., `remark 3 Punctual Buyer` or `remark 5 Fast and Decisive Buyer t/favourite`                                          |
| **Sell**       | `sell INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]` <br/> e.g., `sell 2 q/100 g/apples price/1.5 d/07/11/2022`                                                 |
| **Sort**       | `sort INDEX ORDER` <br> e.g.,`sort 1 latest` or `sort 3 oldest`                                                                                                 | 
| **User Guide** | `user_guide`                                                                                                                                                    |
| **View**       | `view INDEX` <br> e.g., `view 5`                                                                                                                                |

[Back to Table of Contents](#table-of-contents)

## Prefix Summary

| Prefix     | Meaning                                        | Restrictions                                              | Example                       |
|------------|------------------------------------------------|-----------------------------------------------------------|-------------------------------|
| **n/**     | Name of the client                             | Alphanumeric characters and spaces, required              | `n/Alice`                     |
| **a/**     | Address of the client                          | Required                                                  | `a/321 Clementi Road, #02-22` |
| **p/**     | Phone number of the client                     | Required                                                  | `p/98765432`                  |
| **e/**     | Email of the client                            | Required                                                  | `e/alice@gmail.com`           |
| **g/**     | Goods name involved in the transaction         | Alphanumeric characters and spaces, required              | `g/Apples`                    |
| **q/**     | Quantity of goods involved in the transaction  | Positive integer, required                                | `q/10`                        |
| **price/** | Price per quantity of goods in the transaction | Positive number, required                                 | `price/1.50`                  |
| **m/**     | Mode of the command                            | Must be either **client**, **transaction**, or **remark** | `m/client`                    |
| **d/**     | Date of transaction                            | In the format **dd/mm/yyyy**                              | `d/07/11/2022`                |
| **t/**     | Tag applied on clients                         | Alphanumeric, single word                                 | `t/friends`                   |

[Back to Table of Contents](#table-of-contents)

