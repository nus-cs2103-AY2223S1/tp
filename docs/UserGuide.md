---
layout: page
title: Salesy User Guide v1.3
---

Salesy is a desktop app for helping food vendors manage details of their clients and suppliers, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast and remember the commands well, Salesy can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java SDK `11` or above installed in your Computer.

1. Download the latest `Salesy.jar` from [here](https://github.com/AY2223S1-CS2103T-W08-4/tp/releases).

1. Copy the file to the folder you want to use as your home folder in Salesy.

1. Double-click the file to start the app. The GUI should appear in a few seconds.

1. Type in the command box and press Enter to execute it.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Add supplier/supply item/task: `add`

Adds an item of a specified type with the given details

***Format:*** 

`add n/NAME p/PHONE pr/PRICE i/ITEM a/ADDRESS t/Supplier` (supplier)

`addTask d/TASKNAME dl/DEADLINE t/TAG_NAME` (task)

`addItem ...` (TODO: update this when this feature is done)

**Things you can add:**
* Suppliers
* Supply Item (Item in inventory)
* Tasks

**Examples:**
  
Add a task to Salesy

>`addTask Pass ingredients to XXX Pte Ltd`

Add a supplier to Salesy’s address book

>`add n/ABC PTE LTD p/67009000 pr/$1.10 i/Egg a/Blk 140 Woodlands Ave 3 t/Supplier`

Add a SupplyItem to Salesy's inventory
    
>`addItem 1` (TODO: Update this portion of UG)


### Delete: `delete`

Delete the specified item of the specified type from Salesy

***Format:*** 

`delete <index>` (supplier)

`deleteTask <index>` (task)

`deleteItem <index>` (supply/inventory item)

**Things you can delete:**
* Suppliers
* Supply items (Inventory items)
* Tasks

**Examples**

Delete a supplier

> `delete 1`

Delete a task

> `deleteTask 2`

Delete an item in inventory

> `deleteItem 1`

### Mark: `mark`

Marks the specified task as done to keep task list updated

***Format:*** 

`mark <task index>`

**Examples**

Mark a task that you have completed

> `mark <task name>`


### Unmark: `unmark`

Unmarks a previously marked task

***Format:*** 

`unmark <task index>`

**Examples**

Unmark a task that you have completed

> `unmark <task index>`


### Edit: `edit`

Edit a specified item's details

***Format:***

`edit INDEX [n/NAME] [p/PHONE] [pr/PRICE] [i/ITEM] [a/ADDRESS] [t/TAG]` (Suppliers)

`editTask INDEX [d/DESCRIPTION] [dl/DEADLINE] [t/TAGS]` (Tasks)

**Things you can edit:**
* Supplier details
* Tasks

**Examples**

* Edit supplier details

> `edit 3 pr/NEW PRICE`

* Edit task details

> `editTask 1 dl/2022-10-10`


### Find: `find`

Find items based on name

***Format:***

`find <name value>`

**Examples**

* Find suppliers with name John Cena

> `find John Cena`

### List all data : `listAll`

Lists all items.

***Format:*** 

`listAll`

### List all suppliers/tasks/inventory

### List all suppliers: `listSuppliers`

Lists all suppliers

***Format:*** 

`listSuppliers`

### List all tasks: `listTasks`

Lists all tasks

***Format:*** 

`listTasks`

### List everything in the inventory: `listInventory`

Lists whole inventory.

***Format:*** 

`listInventory`   

### Sort: `sort`

Sorts and displays items based on sorting criteria

***Format:*** 

`sort /<item type> <sort criteria> <ascending/descending>`

**Available sorting criteria:**
* Task details (name, type, time, marked status)
* Supplier details (name, type, price, address)
* Customer details (name, contact, order)

**Examples:**
* Sort by task deadline date, in descending order

> `sortTask /date descending`

* Sort by supplier address, in ascending order

> `sortSupplier /address ascending`

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                | Format                                                     | Examples                                                                           |
|-----------------------|------------------------------------------------------------|------------------------------------------------------------------------------------|
| **list** (supplier)   | `list `                                                    | `listSuppliers `<br>`listTasks `<br>`listInventory`                                |
| **add**  (supplier)   | `add n/NAME p/PHONE pr/PRICE i/ITEM a/ADDRESS t/Supplier`  | `add n/ABC PTE LTD p/67009000 pr/$1.10 i/Egg a/Blk 140 Woodlands Ave 3 t/Supplier` |
| **addTask**           | `addTask d/DETAILS dl/DEADLINE t/TAGS`                     | `addTask d/Restock Eggs dl/2022-12-12 t/Urgent`                                    |
| **delete** (supplier) | `delete <index>`                                           | `delete 1`                                                                         |
| **deleteTask**        | `deleteTask <index>`                                       | `deleteTask 1`                                                                     |
| **mark**              | `mark <index>`                                             | `mark 1`                                                                           |
| **unmark**            | `unmark <index>`                                           | `unmark 2`                                                                         |
| **edit**  (supplier)  | `edit <index of item> <attribute name>/ <new value>`       | `edit 3 pr/NEW PRICE`                                                              |
| **editTask**          | `editTask <index of item> <attribute name>/ <new value>`   | `editTask 1 dl/2029-12-12`                                                         |
| **find**  (supplier)  | `find <name value>`                                        | `find John Cena`                                                                   |
| **sort**              | `sort /<item type> <sort criteria> <ascending/descending>` | `sort /task time descending`                                                       |
