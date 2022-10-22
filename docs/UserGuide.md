---
layout: page
title: User Guide
show-toc: true
---
## About FoodRem

FoodRem is an Inventory Management System that empowers small Food and Beverage (F&B) restaurant managers to manage inventory and obtain insights from inventory data. FoodRem provides greater clarity into your restaurant's inventory usage. As a restaurant manager, you can leverage upon FoodRem's Inventory Management System, where you can manage your inventory during your daily operations. FoodRem also offers a flexible tagging system to let you organize your inventory according to your business needs. Finally, streamline your business decisions by deriving insights from your inventory usage through FoodRem's statistics, keeping track of vital data such as food wastage. 

This User Guide provides an in-depth documentation to help integrate FoodRem into your operational workflows. It covers how to launch FoodRem, core FoodRem features and commands, common terms and definitions used in FoodRem, and troubleshooting recommendations. In addition, a quick start guide is available that gives a whirlwind tour to help get you started. 

Readers do not have to be familiar with the command line to use this guide effectively. The only expectation we have of you is to carefully read through the different sections.

{% include page-break.html %} <!-- Force a page break -->

{% include toc.md header=true show-in-toc=true ordered=true %}

## Overview of Key Features

1. Add, update and delete inventory items
1. Search and sort food items by:
   * Name
   * Quantity
   * Bought date
   * Expiry date
   * Tags
1. Tag items to group them into categories



## How to use the User Guide

Thank you for choosing FoodRem! We are delighted to have you as a user and aim to serve you well!

To gain the most out from this User Guide, start off with the [Quick Start](#quick-start) section. This will give you a brief overview about how to use this application.

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

If you are confident, you can immediately refer to the [Command Summary](#command-summary) after completing the [Quick Start](#quick-start).

Meaning of icons:

* ℹ️ : additional info
* ❗ : warning

If you are stuck, refer to [Troubleshooting](#troubleshooting) or [FAQ](#faq). There is also a [Glossary](#glossary) that contains definitions of what common words used in this application mean.

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

1. **Food expiring soon / Date food bought**

1. **Glorified search and sort**

   1. Upgrade sort and search
   1. Sort food items by quantity
   1. Sort food items by name
   1. Sort food items by expiry date
   1. Sort food items by purchase date
   {: .lower-alpha}

1. **Food buffer**

   1. Rainbow UI / Dashboard
   1. Optional : Minimum acceptable quantity
   1. Optional : Percentage of stock expiring
   {: .lower-alpha}

1. **Purchasing (Hard -> Will not see benefit immediately)**

   1. History + Statistics
   1. Inventory need a price of items
   {: .lower-alpha}

1. **(Last priority) Order management**

   1. Grouping of items
   1. Creation of menu with specific items
   1. Record menu items bought
   1. Statistics
   {: .lower-alpha}

## Acknowledgements

FoodRem is a brownfield software project based off [AddressBook3](https://se-education.org/addressbook-level3/), taken under the [CS2103T Software Engineering module](https://nus-cs2103-ay2223s1.github.io/website/index.html) held by the School of Computing at the National University of Singapore.

## Glossary

{% include glossary.md type="ug" %}
