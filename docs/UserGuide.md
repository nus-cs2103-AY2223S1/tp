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
        - `list /sup`
    - List all tasks in Salesy.
        - `list /tasks`


### Add: `add`

Adds an item of a specified type with the given details

- Format: `add /<item type> <item to be added>`

- Things you can add:
    - Suppliers
    - Tasks

- Examples:
    - Add a task to Salesy
        - `add /task Pass ingredients to XXX Pte Ltd`
    - Add a supplier to Salesy’s address book
        - `add /sup XXX Pte Ltd`


### Delete: `delete`

Delete the specified item of the specified type from Salesy

- Format: `delete /<item type> <index or unique_id>`

- Things you can delete:
    - Suppliers
    - Tasks

- Examples:
    - Delete a supplier
        - `delete /sup 1`
        - `delete /sup alsdjfu80s0f0ssd90f` (might have uid based on timestamp for every supplier on top of just numbered lists)
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
    - Step 1: `edit /<item type to be edited> <item name>`
    - Step 2: `/<what field to edit>`
    - Step 3: `<what you want to edit it to>`

- Things you can edit:
    - Supplier details (price, address and contact)
    - Tasks(type, time)

- Examples:
    - Edit supplier details
        - `edit /sup Mike Wazowski /address Monster, Inc.`


### Search: `search`

Search for items based on attribute name:value pair

- Format: `search /<attribute name> <attribute value>`

- Examples:
    - Search suppliers with name John Cena
        - `search /name John Cena`
    - Search suppliers with address on Singapore
        - `search /address Singapore` (will return all suppliers whose address contains “Singapore”)


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

| Action     | Format                                                                         | Examples                                                                              |
|------------|--------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| **list**   | `list /<item type>`                                                            | `list /sup`<br>`list /task`                                                           |
| **add**    | `add /<item type> <index or unique_id>`                                        | `add /task Pass ingredients to XXX Pte Ltd`                                           |
| **delete** | `delete /<item type> <index or unique_id>`                                     | `delete /sup 1`<br>`delete /task sdjfklsdf89sd8fsd` (uid for task based on timestamp) |
| **mark**   | `mark <task to be marked>`                                                     | `mark restock cups`                                                                   |
| **unmark** | `unmark <task to be unmarked>`                                                 | `unmark restock cups`                                                                 |
| **edit**   | `edit /<item type to be edited> <item name> <br>/<attribute name> <new value>` | `edit /sup Mike Wazowski /address Monster, Inc.`                                      |
| **search** | `search /<attribute name> <attribute value>`                                   | `search /name John Cena`                                                              |
| **sort**   | `sort /<item type> <sort criteria> <ascending/descending>`                     | `sort /task time descending`                                                          |
