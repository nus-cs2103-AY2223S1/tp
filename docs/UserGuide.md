---
layout: page
title: FoodRem User Guide
---
## About

We want you to spend less time keeping track of perishable goods in your daily operations.
FoodRem is a command line application that enables you to efficiently record, update and categorise food items.
It is a convenient administrative tool that can answer the following questions in a flash:

* What food items are about to expire?
* How much of each condiment do I have?
* Which food items are newly purchases?
* ...and many more!

With a few quick commands, you can have complete control of your perishable goods, so you can focus on what
is important: serving your customers, improving menu recipes, and transforming your business into the next
success story.

## Key Features

1. Add, update and delete inventory items
1. Search and sort food items by:
   * Name
   * Quantity
   * Bought date
   * Expiry date
   * Tags
1. Tag items to group them into categories

## Purpose, Scope and Audience

### Purpose

FoodRem helps small businesses to easily manage consumables and perishable goods within a single inventory to reduce time spent on managing and restocking inventory.

### Scope

This app is targeted at small F&B businesses which may struggle in inventory management due to a lack of streamlined process and manpower.

### Audience

This guide is targeted at users using FoodRem who wants to find out more about the different commands to manage their inventory in a quicker and more efficient manner.

Readers do not have to be familiar with the command line to use this guide effectively.
The only expectation we have of you is to carefully read through the different sections.

{% include page-break.html %} <!-- Force a page break -->

{% include toc.md header=true show-in-toc=true ordered=true %}

## How to use the User Guide

Thank you for choosing FoodRem! We are delighted to have you as a user and aim to serve you well!

To gain the most out from this User Guide, start off with the [Quick Start](#quick-start) section.
This will give you a brief overview about how to use this application.

It is **highly recommended** that you read through the User Guide in a **sequential order** up until the section
[Features](#features) where you can find all the information you need for each command.

[Items and Tags](#items-and-tags) :

* What FoodRem is capable of storing

[Navigating around the application](#navigating-around-the-application):

* Terminologies of different parts of the application
* What you are expected to see.

[Flags](#flags) and [Placeholders](#placeholders):

* Important syntax you will come across while reading the user
  guide such as `n/`, `bgt/` or `INDEX`, `ITEM_NAME`.

If you are confident, you can immediately refer to the [Command Summary](#command-summary)
after completing the [Quick Start](#quick-start).

Meaning of icons:

* ℹ️ : additional info
* ❗ : warning

If you are stuck, refer to [Troubleshooting](#troubleshooting) or [FAQ](#faq).
There is also a [Glossary](#glossary) that contains definitions of what common words
used in this application mean.

It is time for you to unleash the potential of a command line application!

## Quick Start

{% include_relative _ug/QuickStart.md %}

## Items and Tags

{% include_relative _ug/ItemsTags.md %}

## Navigating around the application

{Image of entire application}

<!-- markdownlint-disable no-inline-html -->
<table>
<thead>
  <tr>
    <th>Name</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Command Input</td>
    <td>{image}</td>
  </tr>
  <tr>
    <td>Command result</td>
    <td>{image}</td>
  </tr>
  <tr>
    <td>List Component</td>
    <td>{image}</td>
  </tr>
  <tr>
    <td>View Component</td>
    <td>{image}</td>
  </tr>
</tbody>
</table>
<!-- markdownlint-enable no-inline-html -->

## Flags

Flags are delimiters that enable FoodRem to distinguish different parameters without ambiguity.

<!-- markdownlint-disable no-inline-html -->
<table>
<thead>
  <tr>
    <th>Flags</th>
    <th>Related Placeholder</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>id/</td>
    <td>INDEX<br>INDEX_LIST</td>
  </tr>
  <tr>
    <td>n/</td>
    <td>ITEM_NAME<br>TAG_NAME</td>
  </tr>
  <tr>
    <td>qty/</td>
    <td>QUANTITY</td>
  </tr>
  <tr>
    <td>unit/</td>
    <td>UNIT</td>
  </tr>
  <tr>
    <td>buy/</td>
    <td>BOUGHT_DATE</td>
  </tr>
  <tr>
    <td>exp/</td>
    <td>EXPIRY DATE</td>
  </tr>
</tbody>
</table>
<!-- markdownlint-enable no-inline-html -->

## Placeholders

Placeholders are words in UPPER_CASE to show you what parameters you can supply to a command.

{% include_relative _ug/Placeholders.html %}

## Features

This section covers how to use each command in detail.
Before continuing, ensure you have read the section on [Flags](#flags) and [Placeholders](#placeholders).

What you should expect to find:

* A description of the command
* The expected behaviour for the command
* A few valid and invalid examples of the command
* Important points to note

**IMPORTANT:**

* Square brackets indicate an optional parameter.
* For each command, "Format" indicates the syntax of the command.

### Item Features

{Insert an image of items}

#### Create a new item

<!--- TODO: emember to warn users if expiry date < bought date-->

Command: `item new ITEM_NAME`

> Description: Creates a new item with the provided item_name.

---

Example:

Input

```text
item new potato
```

Output

```text
Item  “potato” successfully created
```

#### List all items

Command: `list`

> Description: Lists all the items in the inventory.

---

Example:

Input

```text
list
```

Output

```text
Here are the items in your inventory:
Onions
Details about onions
Tomatoes
Details about tomatoes
Chicken wings
Details about chicken wings
```

### Search for an item

<!--- Remember to implement find by tags-->

Command: `find NAME`

> Description: Find an inventory item based on the given keywords
> The search is case-insensitive. (e.g. apples will match Apples)
> The order of the keyword does not matter e.g (rose apple will match apple rose)
> Only the item name is searched

---

Example:

Input

```text
find apple
```

Output

```text
Here are the results matching your search
Green apple
Rose apple
```

#### Sort all items by an attribute

#### View the information of an item

#### Increase the quantity of an item

#### Decrease the quantity of an item

#### Update the information of an item

#### Delete an item

Command: `[item] delete ITEM_INDEX`

> Description: Deletes a specified item. Returns a warning if the item does not exist.

---

Example:

Input

```text
delete 1
```

Output

```text
(Item exists): Item “potato” successfully deleted!
(Item does not exist): No item to be found at index 1. Use “list items” or “find NAME” to find the index of the item to be deleted.
```

### Tag Features

{Insert an image of tags}

#### Create a new tag

#### List all tags

Command: `list tags`

> Description: Lists all the tags that the user has created.

---

Example:

Input

```text
list tags
```

Output

```text
Here are the tags that are available:
Fruits
Vegetables
Spices
```

#### Tag an item

#### Untag an item

#### Rename a tag

#### Delete a tag

### Receive help during usage

Command: `help`

> Description: Displays a list of commands that can be used.

---

Example:

Input

```text
help
```

Output:

```text
list:
    Lists all the items/tags that the user has created.

    Usage:
        List items:  "list items"
        List tags:   "list tags"

item:
    Create / Delete / Increment quantity / Decrement quantity /
    Set quantity / Set expiry date / Set bought date, of an item.

    Flags:
        Name:        n/
        Quantity:    qty/
        Expiry Date: exp/
        Bought Date: bgt/

    Usage:
        Create:      "item new n/Potatoes"
        Delete:      "item del 1"
        Increment:   "item inc 1 10"
        Decrement:   "item dec 1 10"
        Set:         "item set 1 n/Potatoes qty/10"

find:
    Find an inventory item based on the given keywords.

    Usage:
        Find:        "find potato carrots"

tag:
    Create / Rename / Set item tied to / Delete, a tag.

    Flags:
        Name:        n/

    Usage:
        Create:      "tag create food"
        Rename:      "tag rename food n/foodie"
        Set item:    "tag 1 2 7 71 food"
        Delete:      "tag delete food"

bye:
    Exits FoodRem program.

    Usage:
        Exit:       "bye"
```

### Reset the application

### Exit the application

Command: `bye`

> Description: Exits FoodRem program.

---

Example:

Input

```text
bye
```

## Command Summary

### Item Commands

<!-- markdownlint-disable no-inline-html -->
<table>
<thead>
  <tr>
    <th>Action</th>
    <th>Format</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Create a new item</td>
    <td><b>new n/ITEM_NAME [qty/QUANTITY] [unit/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]</b><br><br><u>Valid Example:</u><br>new n/Potato qty/70 unit/kg bgt/22-02-11 exp/22-03/11</td>
  </tr>
  <tr>
    <td>List all items</td>
    <td><b>list</b><br><br><u>Valid Example:</u><br>list</td>
  </tr>
  <tr>
    <td>Search for an item</td>
    <td><b>find n/ITEM_NAME</b><br><br><u>Valid Example:</u><br>find n/Potato</td>
  </tr>
  <tr>
    <td>Sort an item by name, quantity, type, bought date or expiry date.</td>
    <td><b>sort [n/] [qty/] [unit/] [bgt/] [exp/]</b><br><br><u>Valid Example:</u><br>sort n/<br>sort qty/<br>sort qty/ bgt/<br><br><u>Invalid Example:</u><br>sort</td>
  </tr>
  <tr>
    <td>View information about an item</td>
    <td><b>view id/INDEX</b><br><br><u>Valid Examples:</u><br>view id/1</td>
  </tr>
  <tr>
    <td>Increase the quantity of an item</td>
    <td><b>inc id/INDEX_LIST [qty/QUANTITY]</b><br><br><u>Valid Examples:</u><br>inc id/1 qty/100<br>inc id/1,2,3 qty/100</td>
  </tr>
  <tr>
    <td>Decrease the quantity of an item</td>
    <td><b>dec id/INDEX_LIST [qty/QUANTITY]</b><br><br><u>Valid Examples:</u><br>dec id/1 qty/100<br>dec id/1,2,3 qty/100</td>
  </tr>
  <tr>
    <td>Update the information of an item</td>
    <td><b>set id/INDEX_LIST [n/ITEM_NAME] [qty/QUANTITY] [unit/UNIT] [bgt/BOUGHT_DATE] [exp/EXPIRY_DATE]</b><br><br>❗ IMPORTANT:<br>Do not update multiple items to have the same name<br><br><u>Valid Examples:</u><br>set id/1 n/Potatoes qty/60 unit/kg<br>set id/1,2,3 qty/60</td>
  </tr>
  <tr>
    <td>Delete an item</td>
    <td><b>del id/INDEX_LIST</b><br><br><u>Valid Examples:</u><br>del id/1<br>del id/1,2,3</td>
  </tr>
</tbody>
</table>
<!-- markdownlint-enable no-inline-html -->

### Tag Commands

<!-- markdownlint-disable no-inline-html -->
<table>
<thead>
  <tr>
    <th>Action</th>
    <th>Format</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Create a new tag</td>
    <td><b>newtag n/TAG_NAME</b><br><br><u>Valid Examples:</u><br>newtag n/Food<br>newtag n/Condiments<br><br><u>Invalid Examples:</u><br>newtag Food<br>newtag Condiments</td>
  </tr>
  <tr>
    <td>List all tags available<br></td>
    <td><b>listtag</b><br><br><u>Valid Example:</u><br>listtag<br><br><br><u>Invalid Example:</u><br>listtags</td>
  </tr>
  <tr>
    <td>Tag items with a specific tag<br></td>
    <td><b>tag n/TAG_NAME id/INDEX_LIST</b><br><br><u>Valid Examples:</u><br>tag n/Condiments id/1<br>tag n/Condiments id/1,2,4,8<br><br><u>Invalid Example:</u><br>tag Condiments 1</td>
  </tr>
  <tr>
    <td>Untag items with a specific tag</td>
    <td><b>untag n/TAG_NAME id/INDEX_LIST</b><br><br><u>Valid Examples:</u><br>untag n/Condiments id/1<br>untag n/Condiments id/1,2,4,8<br><br><u>Invalid Example:</u><br>untag Condiments 1,2</td>
  </tr>
  <tr>
    <td>Rename a tag</td>
    <td><b>renametag n/TAG_NAME n/TAG_NAME</b><br><br><u>Valid Example:</u><br>renametag n/Condiments n/Condiment<br><br><u>Invalid Example:</u><br>renametag Condiments Condiment</u><br></td>
  </tr>
  <tr>
    <td>Delete a tag</td>
    <td><b>delete n/TAG_NAME</b><br><br><u>Valid Examples:</u><br>deletetag n/Food<br><br><br><u>Invalid Examples:</u><br>deletetag n/<br>deletetag Food<br>deletetag Condiments</td>
  </tr>
</tbody>
</table>
<!-- markdownlint-enable no-inline-html -->

### Other Commands

<!-- markdownlint-disable no-inline-html -->
<table>
<thead>
  <tr>
    <th>Action</th>
    <th>Format</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Shows a help dialog with a list of available commands</td>
    <td><b>help</b><br><br><u>Valid Example:</u><br>help</td>
  </tr>
  <tr>
    <td>Reset the application</td>
    <td><b>reset</b><br><u><br>Valid Example:</u><br>reset</td>
  </tr>
  <tr>
    <td>Exit the application</td>
    <td><b>exit</b><br><u><br>Valid Examples:</u><br>exit</td>
  </tr>
</tbody>
</table>
<!-- markdownlint-enable no-inline-html -->

## Troubleshooting

Something goes here...

## FAQ

{% include_relative _ug/FAQ.md %}

## Future Extensions

(NOT COMPLETED)

1. Food expiring soon / Date food bought
   **Glorified search and sort**
   a. Upgrade sort and search b. Sort food items by quantity c. Sort food items by name d. Sort food items by expiry
   date e. Sort food items by purchase date
1. Food buffer a. Rainbow UI / Dashboard b. Optional : Minimum acceptable quantity c. Optional : Percentage of stock
   expiring
1. Purchasing (Hard -> Will not see benefit immediately)
   a. History + Statistics b. Inventory need a price of items
1. (Last priority) Order management a. Grouping of items b. Creation of menu with specific items c. Record menu items
   bought d. Statistics

## Acknowledgements

FoodRem is a brownfield software project based off [AddressBook3](https://se-education.org/addressbook-level3/), taken under the [CS2103T Software Engineering module](https://nus-cs2103-ay2223s1.github.io/website/index.html) held by the School of Computing at the National University of Singapore.

## Glossary

{% include glossary.md type="ug" %}
