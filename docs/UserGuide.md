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

Thank you for choosing FoodRem!

If this is your first time using FoodRem, start off with the [Quick Start](#quick-start) section.
This gives you a brief overview about how to install and use FoodRem.

We **highly recommend** that you read through the User Guide in a **sequential order** up until the section
[Features](#features), where you can find all the information you need for each command.

Refer to the [Layout](#layout) section where you can find what you are expected to see in the different parts of FoodRem.

In the [Flags](#flags) and [Placeholders](#placeholders) sections you can find:

* Important syntax you will come across while reading the user
  guide such as `n/`, `bgt/` or `INDEX`, `ITEM_NAME`.
* The description of these syntax and how to use them

If you are an experienced user, you can refer to the [Command Summary](#command-summary) to get an overview of all the currently supported FoodRem commands.

Here are some icons you may encounter in FoodRem and what they mean:

* ℹ️ : additional info
* ❗ : warning

`Words or phrases in code blocks like this` refer to user input.

If you are stuck, refer to [Troubleshooting](#troubleshooting) or [FAQ](#faq).
You can refer to [Glossary](#glossary) that contains definitions of some commonly used words in FoodRem


## Quick Start

{% include_relative _ug/QuickStart.md %}

## Using FoodRem

{% include_relative _ug/ItemsTags.md %}

### Flags

Flags are delimiters that enable FoodRem to distinguish different parameters without ambiguity.

| Flags | Related Placeholder   |
|-------|-----------------------|
| id/   | INDEX<br>INDEX_LIST   |
| n/    | ITEM_NAME<br>TAG_NAME |
| qty/  | QUANTITY              |
| unit/ | UNIT                  |
| buy/  | BOUGHT_DATE           |
| exp/  | EXPIRY_DATE           |
| p/    | PRICE                 |
| r/    | REMARKS               |


### Placeholders

Placeholders are words in UPPER_CASE to show you what parameters you can supply to a command.

{% include_relative _ug/Placeholders.md %}

## Features

{% include_relative _ug/Features.md %}

## Command Summary

### Item Commands

{% include_relative _ug/commandSummary/ItemCommands.md %}

### Tag Commands

{% include_relative _ug/commandSummary/TagCommands.md %}

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
