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

| Name           | Description |
|----------------|-------------|
| Command Input  | {image}     |
| Command result | {image}     |
| List Component | {image}     |
| View Component | {image}     |

## Flags

Flags are delimiters that enable FoodRem to distinguish different parameters without ambiguity.

| Flags | Related Placeholder   |
|-------|-----------------------|
| id/   | INDEX<br>INDEX_LIST   |
| n/    | ITEM_NAME<br>TAG_NAME |
| qty/  | QUANTITY              |
| unit/ | UNIT                  |
| buy/  | BOUGHT_DATE           |
| exp/  | EXPIRY DATE           |

## Placeholders

Placeholders are words in UPPER_CASE to show you what parameters you can supply to a command.

{% include_relative _ug/Placeholders.md %}

## Features

{% include_relative _ug/Features.md %}

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

{% include_relative _ug/commandSummary/OtherCommands.md %}

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
