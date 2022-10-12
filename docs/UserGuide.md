---
layout: page
title: Salesy User Guide v1.2
---

Salesy is a desktop app for helping food vendors manage details of their clients and suppliers, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast and remember the commands well, Salesy can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Salesy.jar` from [here](https://github.com/AY2223S1-CS2103T-W08-4/tp/releases).

1. Copy the file to the folder you want to use as your home folder in Salesy.

1. Double-click the file to start the app. The GUI should appear in a few seconds.

1. Type in the command box and press Enter to execute it.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features


### List all : `list`

Lists all items of specified type.

- Format: `list /<item type>`

- Examples:
    - List all suppliers the Salesy's address book
        - `list`
    - List all tasks in Salesy.
        - `list /tasks`


### Add: `add`

Adds an item of a specified type with the given details

- Format: `add n/NAME p/PHONE pr/PRICE i/ITEM a/ADDRESS t/Supplier` (supplier)

- Things you can add:
    - Suppliers
    - Tasks

- Examples:
    - Add a task to Salesy
        - `add /task Pass ingredients to XXX Pte Ltd`
    - Add a supplier to Salesy’s address book
        - `add n/ABC PTE LTD p/67009000 pr/$1.10 i/Egg a/Blk 140 Woodlands Ave 3 t/Supplier`


### Delete: `delete`

Delete the specified item of the specified type from Salesy

- Format: `delete <index>` (supplier)

- Things you can delete:
    - Suppliers
    - Tasks

- Examples:
    - Delete a supplier
        - `delete 1`
    - Delete a task
        - `delete /task 2`
        - `delete /task sdjfklsdf89sd8fsd` (might have uid for every task based on timestamp)


### Mark: `mark`

Marks the specified task as done to keep task list updated

- Format: `mark <task to be marked>`

- Things you can mark:
    - Tasks

- Examples:
    - Mark a task that you have completed
        - `mark <task name>`


### Unmark: `unmark`

Unmarks a previously marked task if necessary

- Format: `unmark <task to be unmarked>`

- Things you can unmark:
    - Tasks

- Examples:
    - Unmark a task that you have completed
        - `unmark <task name>`


### Edit: `edit`

Edit a specified item's details

- Format:
    - Step 1: `edit /<index of item>`
    - Step 2: `/<what field to edit>`
    - Step 3: `<what you want to edit it to>`

- Things you can edit:
    - Supplier details (name,phone,price,item,and address)
    - Tasks(type, time)

- Examples:
    - Edit supplier details
        - `edit 3 pr/NEW PRICE`


### Find: `find`

Find for items based on name

- Format: `find <name value>`

- Examples:
    - Find suppliers with name John Cena
        - `find John Cena`
   

### Sort: `sort`

Sorts and displays items based on sorting criteria

- Format: `sort /<item type> <sort criteria> <ascending/descending>`

- Available sorting criteria:
    - Task details (name, type, time, marked status)
    - Supplier details (name, type, price, address)
    - Customer details (name, contact, order)

- Examples:
    - Sort by task time, in descending order
        - `sort /task time descending`
    - Sort by supplier address, in ascending order
        - `sort /sup address ascending`

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                | Format                                                     | Examples                                                                         |
|-----------------------|------------------------------------------------------------|----------------------------------------------------------------------------------|
| **list** (supplier)   | `list `                                                    | `list `<br>`list /task`                                                       |
| **add**  (supplier)   | `add n/NAME p/PHONE pr/PRICE i/ITEM a/ADDRESS t/Supplier`  | `add n/ABC PTE LTD p/67009000 pr/$1.10 i/Egg a/Blk 140 Woodlands Ave 3 t/Supplier` |
| **delete** (supplier) | `delete <index>`                                           | `delete 1`                                                                    |
| **mark**              | `mark <task to be marked>`                                 | `mark restock cups`                                                              |
| **unmark**            | `unmark <task to be unmarked>`                             | `unmark restock cups`                                                            |
| **edit**  (supplier)  | `edit <index of item> /<attribute name> <new value>`       | `edit 3 pr/NEW PRICE`                                                            |
| **find**  (supplier)  | `find <name value>`                                        | `find John Cena`                                                                 |
| **sort**              | `sort /<item type> <sort criteria> <ascending/descending>` | `sort /task time descending`                                                     |
