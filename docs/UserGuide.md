---
layout: page
title: Salesy User Guide v1.3
---

Salesy is an all-in-one tool for NUS canteen vendors who want to keep track of their tasks, inventory and suppliers. 
With Salesy, you can expect a good visualization of your inventory and increased efficiency of your bookkeeping routines.
Salesy is optimized for use via a [Command Line Interface](#62-command-line-interface-cli) (CLI) while still having the benefits of 
a [Graphical User Interface](#63-graphical-user-interface-gui) (GUI).

![Ui](images/Ui.png)

--------------------------------------------------------------------------------------------------------------------

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

# 1. Quick start

1. Ensure you have Java SDK `11` or above installed in your Computer.

2. Download the latest `Salesy.jar` from [here](https://github.com/AY2223S1-CS2103T-W08-4/tp/releases).

3. Copy the file to the folder you want to use as your home folder in Salesy.

4. Double-click the file to start the app. The GUI should appear in a few seconds.

5. Type in the command box and press Enter to execute it.

6. Refer to the [Features](#4-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
# 2. Important information

- User has to input details for fields that are not in square brackets, but inputting details for fields in square brackets is optional.
  - e.g. `add n/NAME p/PHONE pr/PRICE i/ITEM a/ADDRESS [t/TAG]`
  - The `NAME`, `PHONE`, `PRICE`, `ITEM` and `ADDRESS` fields are compulsory.
  - The `TAG` field is optional.

- For commands that accept tag fields, i.e. `add`, `addTask`, `edit` and `editTask`, multiple tags are allowed but each tag can only contain a single word.
  - `t/Supplier t/Chicken t/Delivery` is allowed
  - `t/Supplier for Chicken` is not allowed

- Index numbers in commands, e.g. `delete <supplier index>` are compulsory.

- When multiple similar prefixes are input by the user, e.g. `i/Chicken i/Egg i/Cups`,
the rightmost prefix and its details, i.e. `Cups` will be taken as input.
  
# 3. Visual Guide

## 3.1 Statistics Panel

A brief display of important information for **inventory** and **tasks**.

### An overview of the information panel

![LabelledStatsPanel](images/labelledstatspanel.png)


  <details>
  <summary style='font-size: 18px'>
    More details (Click me)
  </summary>
  
  * **(A)** `Incomplete` tasks refers to tasks that are shown as `Not Done`.

  * **(B)** `Overdue` tasks refers to tasks that are both `Not Done` and has a deadline that is
  before the current date.
    * For example: Today's date is **_11 Feb 2022_** and your task's deadline is _**10 Feb 2022**_, this will be counted as `Overdue`.
  * **(C)** `Upcoming` tasks refers to tasks that have deadlines that are **in the future and inclusive of today**, that are yet to be completed i.e. `Not Done`.
    * For example: You have an incomplete task. Today's date is **_11 Feb 2022_** and your task's deadline is _**11 Feb 2022 or later**_, this will be counted as `Upcoming`.
</details>

## 3.2 Colors used for Inventory

Simple and intuitive colors are used to identify stock levels.

### 3.2.1 Examples

Inventory Status in the [Stats Panel](#31-statistics-panel).

Inventory Cards for Items

![InventoryCardRed](images/redstatuscard.png)

![InventoryCardOrange](images/orangestatuscard.png)

![InventoryCardGreen](images/greenstatuscard.png)

### 3.2.2 Simple explanation of the colors

| Color  | Meaning                                |
|--------|----------------------------------------|
| Red    | Current stock is at a `low` level      |
| Orange | Current stock is at a `moderate` level |
| Green  | Current stock is at a `healthy` level  |

<div markdown="span" class="alert alert-info">

**Note:** For a more detailed explanation on how colors are determined, refer the [appendix](#61-colors-used-in-the-inventory).

</div>


# 4. Features

## 4.1 Add supplier/task/supply item: `add`

Adds an item of a specified type with the given details

**Things you can add:**
* Suppliers
* Tasks
* Supply Item (item in inventory)

### 4.1.1 Format

`add n/NAME p/PHONE(8 digits) pr/PRICE i/ITEM a/ADDRESS [t/TAG]` **(supplier)**

`addTask d/TASKNAME dl/DEADLINE [t/TAG]` **(task)**

`addItem <supplier index> c/CURRENTSTOCK m/MINIMUMSTOCK` (supply item)

<div markdown="span" class="alert alert-info">

**Note:** Tag rules apply for `add` and `addTask`. Refer to [this section](#2-important-information).

</div>

### 4.1.2 Examples

Add a supplier to Salesy’s address book

>`add n/ABC PTE LTD p/67009000 pr/$1.10 i/Egg a/Blk 140 Woodlands Ave 3 t/Supplier`

Add a task to Salesy

>`addTask d/Pass ingredients to XXX Pte Ltd dl/2022-12-12`

Add an item to Salesy's inventory

Item from first supplier in address book added

>`addItem 1 c/10 m/3`


## 4.2 Delete: `delete`

Deletes the specified item of the specified type from Salesy

**Things you can delete:**
* Suppliers
* Tasks
* Supply items

### 4.2.1 Format

`delete <supplier index>` (supplier)

`deleteTask <task index>` (task)

`deleteItem <item index>` (supply item)

### 4.2.2 Examples

Delete a supplier

> `delete 1`

Delete a task

> `deleteTask 2`

Delete an item in inventory

> `deleteItem 1`


## 4.3 Mark: `mark`

Marks the specified task as done to keep task list updated

**Things you can mark:**
* Tasks

### 4.3.1 Format

`mark <task index>`

### 4.3.2 Examples

Mark a task that you have completed

> `mark 2`


## 4.4 Unmark: `unmark`

Unmarks a previously marked task

**Things you can unmark:**
* Tasks

### 4.4.1 Format

`unmark <task index>`

### 4.4.2 Examples

Unmark a task that you have completed

> `unmark 3`


## 4.5 Edit: `edit`

Edits a specified item's details

**Things you can edit:**
* Supplier details
* Tasks
* Item stock

### 4.5.1 Format

`edit <supplier index> [n/NAME] [p/PHONE] [pr/PRICE] [i/ITEM] [a/ADDRESS] [t/TAG]` (supplier)

`editTask <task index> [d/DESCRIPTION] [dl/DEADLINE] [t/TAG]` (task)

`editStock <item index> c/CURRENTSTOCK` (supply item)

<div markdown="span" class="alert alert-info">

**Note:** At least one field has to be edited for the command to execute successfully.

Tag rules apply for `edit` and `editTask`. Refer to [this section](#2-important-information).

</div>

### 4.5.2 Examples

* Edit supplier details

> `edit 3 pr/$2.50`

* Edit task details

> `editTask 1 dl/2022-10-10`

* Edit item stock count

> `editStock 1 c/7`


## 4.6 Find: `find`

Finds suppliers based on name or item

### 4.6.1 Format

`find n/NAMEVALUE` (supplier name)

`find i/ITEMVALUE` (supplier item)

### 4.6.2 Examples

* Find suppliers with name John Cena

> `find n/John Cena`

* Find suppliers who supply Eggs

> `find i/Egg`


## 4.7 Increment / Decrement current stock in your inventory

Increases or decreases your stock for a particular item in the inventory

### 4.7.1 Steps

1. Take a look at the inventory panel at the **middle-bottom** section of the application.
2. Decide on which item to change the stock.
3. Hover your mouse over item and the white text box with a value `1` in it. (Refer to [Figure 1](#figure-1-below-initial-state))
4. Key in your desired amount to add or decrease by. (Refer to [Figure 2](#figure-2-below-after-entering-desired-value))
5. Press the `+` icon to add your desired amount (or press the `-` icon to reduce by your desired amount). (Refer to [Figure 3](#figure-3-below-after-pressing-the--button))

#### Figure 1 (below): Initial state

![Ui](images/initialStateIncEg.png)

#### Figure 2 (below): _After entering desired value_

![Ui](images/incdeckeyin.png)

#### Figure 3 (below): After pressing the `+` button

![Ui](images/afterinc.png)

***Notice that the current stock of Ginger increased by your desired amount.***

### 4.7.2 Limitations

* Only able to key in up to a 5 digit value.
* Only able to key in positive values

## 4.8 List all data : `listAll`

Lists all items in the suppliers panel, inventory panel and tasks panel.

### 4.8.1 Format

`listAll`

### 4.8.2 Examples
- After using the `find` command for supplier contacts, the command `listAll` will list all
  contacts in Salesy's address book.

* List all entities

> `listAll`


## 4.9 List specific items in the panels

Refreshes and lists all suppliers, tasks or items.

## 4.9.1 List all suppliers: `listSuppliers`

List all suppliers

### 4.9.1.1 Format

`listSuppliers`

### 4.9.1.2 Examples

1. Run the `find n/eggs` command to find suppliers that supply eggs. (Refer to [Figure 4](#figure-4-below-after-running-find-neggs))
2. If you want to view the full list of suppliers again, run `listSuppliers` to list
all the suppliers. (Refer to [Figure 5](#figure-5-below-after-running-listsuppliers))

#### Figure 4 (below) After running `find n/eggs`

![findegg](images/findegg.png)

#### Figure 5 (below) After running `listSuppliers`

![afterlistsuppliers](images/listall.png)

## 4.9.2 List all tasks: `listTasks`

Lists all tasks

### 4.9.2.1 Format

`listTasks`

### 4.9.2.2 Examples

* List all tasks

> `listTasks`

<div markdown="span" class="alert alert-info">
**Note:** All tasks displayed in the GUI is sorted by deadline.
The task with the earliest deadline is found at the top of the list.
</div>

## 4.9.3 List everything in the inventory: `listInventory`

Lists the whole inventory.

### 4.9.3.1 Format

`listInventory`

### 4.9.3.2 Examples

* List all items

> `listInventory`

## 4.10 Clear Supplier Address Book: `clear`

Clears and deletes all suppliers in Salesy. 

<div markdown="span" class="alert alert-info">

⚠️**WARNING**: This command is used to reset only the suppliers in the Suppliers panel. Running this command will remove all
suppliers from Salesy. To regain the sample data again, delete `addressbook.json` from the source folder of the app and 
re-run Salesy.

</div>

--------------------------------------------------------------------------------------------------------------------

# 5. Command summary


| Action                       | Format                                                   | Examples                                                                           |
|------------------------------|----------------------------------------------------------|------------------------------------------------------------------------------------|
| **clear** (supplier)         | `clear`                                                  | `clear`                                                                            |
| **list** (supplier)          | `listSuppliers`                                          | `listSuppliers`                                                                    |
| **add**  (supplier)          | `add n/NAME p/PHONE pr/PRICE i/ITEM a/ADDRESS [t/TAG]`   | `add n/ABC PTE LTD p/67009000 pr/$1.10 i/Egg a/Blk 140 Woodlands Ave 3 t/Supplier` |
| **delete** (supplier)        | `delete <supplier index>`                                | `delete 1`                                                                         |
| **edit**  (supplier)         | `edit <supplier index> <attribute name>/ <new value>`    | `edit 3 pr/#1.70`                                                                  |
| **find**  (supplier)         | `find n/NAMEVALUE` <br>  `find i/ITEMVALUE`              | `find n/John Cena` <br> `find i/Egg`                                               |
| **list** (task)              | `listTasks`                                              | `listTasks`                                                                        |
| **add** (task)               | `addTask d/DETAILS dl/DEADLINE [t/TAG]`                  | `addTask d/Restock Eggs dl/2022-12-12 t/Urgent`                                    |
| **delete** (task)            | `deleteTask <task index>`                                | `deleteTask 1`                                                                     |
| **edit** (task)              | `editTask <task index> <attribute name>/ <new value>`    | `editTask 1 dl/2029-12-12`                                                         |
| **mark**  (task)             | `mark <task index>`                                      | `mark 1`                                                                           |
| **unmark** (task)            | `unmark <task index>`                                    | `unmark 2`                                                                         |
| **list** (supply item)       | `listInventory`                                          | `listInventory`                                                                    |
| **add** (supply item)        | `addItem <supplier index> c/CURRENTSTOCK m/MINIMUMSTOCK` | `addItem 2 c/10 m/3`                                                               |
| **delete** (supply item)     | `deleteItem <item index>`                                | `deleteItem 2`                                                                     |
| **edit stock** (supply item) | `editStock <item index> c/CURRENTSTOCK`                  | `editStock 2 c/12`                                                                 |
| **list** (all items)         | `listAll`                                                | `listAll`                                                                          |                                                    |

# 6. Appendix

## 6.1 Colors used in the Inventory

**Detailed Explanation**

| Color          | Meaning                                                      | How is it determined ?                                                                              |
|----------------|--------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| 🔴&nbsp;Red    | Running `low`, restock as soon as possible.                  | **Less than 120%** of minimum stock specified.                                                      |
| 🟠&nbsp;Orange | Running at a `moderate` level, can consider restocking soon. | **More than and equal to 120%** of minimum stock specified and **less than 165%** of minimum stock. |
| 🟢&nbsp;Green  | Running at a `healthy` level, no worries about restocking.   | **More than and equal to 165%** of minimum stock specified.                                         |

<details>
  <summary style='font-size: 18px'>
    Notes on how the colours are determined: (Click me)
  </summary>

**What is _minimum stock_?**
* Minimum stock refers the minimum amount of stock in which the canteen vendor feel is required for
daily operations.
* The minimum stock is a parameter supplied by the user when using the [`addItem`](#41-add-suppliertasksupply-item-add) command.
* Current stock refers to the amount of stock the canteen vendor current have in the inventory.

**Example on how the colours are determined:**

Context: a user wants to keep track of the amount of eggs in its inventory.

For example, the minimum stock required for daily operation is `100`.

When it shows **green** colour: Our current stock is **More than and equal to `165%`** of minimum stock specified.

1. For this case, `165%` of `100` eggs is `165` eggs.
2. If our current stock has `165` eggs and above it will display green.

When it shows **yellow** colour: Our current stock must be **More than and equal to `120%`** of minimum stock specified 
and **less than `165%`** of minimum stock specified.

1. For this case, `120%` of `100` eggs is `120` eggs and `165%` if `100` eggs `165` eggs.
2. If our current stock is between `120` eggs inclusive and `165` eggs exclusive it will display yellow.

When it shows **red** colour: Our current stock must be **Less than `120%`** of minimum stock specified.

1. For this case, `120%` of `100` eggs is `120` eggs.
2. If our current stock is less than `120` eggs it will display red.

</details>

[Back to Colors](#32-colors-used-for-inventory)

[//]: # (@@author hauchongtang-reused)
[//]: # (Both definitions: 6.2 and 6.3 taken from https://byjus.com/gate/difference-between-cli-and-gui/#:~:text=GUI%20lets%20a%20user%20interact,offer%20both%20CLI%20and%20GUI.)

## 6.2 Command Line Interface (CLI)

* CLI is a medium or a platform, where a user writes a command to answer a visual prompt and then receives a response from the system/device.
* In the case of Salesy, the CLI is used heavily to allow the user to perform commands.

## 6.3 Graphical User Interface (GUI)

* In a GUI, you get various menus for making things easy, such as buttons, windows, scrollbars etc. It is very intuitive yet simple to use, even for a newbie.
* In the case of Salesy, we utilize the GUI to give the user a visual view of their commands done from the CLI.

[Back to Top](#)
