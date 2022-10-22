---
layout: page
title: User Guide
show-toc: true
---
## About FoodRem

FoodRem is an Inventory Management System that empowers small food and beverage (F&B) restaurant managers to manage inventory and obtain insights from inventory data. As a restaurant manager, leverage upon FoodRem's Inventory Management System, where you can manage your inventory during your daily operations. Utilize FoodRem's flexible tagging system to help you organize your inventory according to your business needs. Finally, streamline your business decisions by deriving insights from your inventory usage through FoodRem's statistics, keeping track of vital data such as food wastage.

Using a Command-Line interface, efficiently interact with FoodRem without needing to even touch your mouse! With a focus on user-friendliness, FoodRem is easy to learn!

This User Guide provides an in-depth documentation to help integrate FoodRem into your operational workflows. It covers how to launch FoodRem, core FoodRem features and commands, common terms and definitions used in FoodRem, and some troubleshooting recommendations. In addition, a quick start guide is available that gives a whirlwind tour to help get you started.

{% include page-break.html %} <!-- Force a page break -->

{% include toc.md header=true show-in-toc=true ordered=true %}

## Features

There are two core features that FoodRem provides: The Inventory Management System, and Inventory Analysis.

<!-- TODO: Add links to e.g. name, bought date, expiry date, etc. -->

### Inventory Management System

FoodRem allows you to track your inventory during your daily operations.

1. Quickly **create, view, edit, and remove** your available inventory.
1. **Sort** and **filter** your inventory by name, bought date, expiry date, quantity, unit and cost.
1. **Tag** items in your inventory using an efficient tagging system where you can create, rename and delete existing tags. You can then filter and sort items according to these tags.

### Inventory Analysis

FoodRem tracks data that helps you streamline your business decisions through **statistics**.

1. Track your **most expensive items**.
1. View items that are **expiring** in the next 10 days.
1. Find out how much **cost was incurred** due to food wastage.

## How to use the User Guide

Thank you for choosing FoodRem! We are delighted to have you as a user and aim to serve you well.

We **highly recommend** that you read through the User Guide in a **sequential order** up until the section [Features](#features), where you can find all the information you need before using all commands.

If this is your first time using FoodRem, start off with the [Quick Start](#quick-start) section.
This guides you on how to install FoodRem.

If you are a new user to FoodRem, you can head over to the section about [Using FoodRem](#using-foodrem) which will teach you the basics of FoodRem. This includes: 

* Introducing you to the general [command format](#command-format).
* [Flags](#flags) and [Placeholders](#placeholders) where you can find:
  * Important syntax you will come across while reading the User
    Guide such as `n/`, `bgt/` or `INDEX`, `ITEM_NAME`.
  *  The description of these syntax and how to use them
* [Trying out](#trying-your-first-command) a command.

If you are an experienced user, you can refer to the [Command Summary](#command-summary) to get an overview of all the currently supported FoodRem commands.

Here are some icons you may encounter in FoodRem and what they mean:

* ℹ️ : additional info
* ❗ : warning


If you are stuck, refer to [Troubleshooting](#troubleshooting) or [FAQ](#faq).
You can refer to [Glossary](#glossary) that contains definitions of some commonly used words in FoodRem


## Quick Start

{% include_relative _ug/QuickStart.md %}

## Using FoodRem
Here is an overview of what FoodRem consists of and how you can perform a command.
### Items and Tags

{% include_relative _ug/ItemsTags.md %}

### Command Format

You will encounter FoodRem commands throughout this User Guide.
Before you delve into the different commands in [Features](#features), let’s learn what a command consists of.

Here is an example:
![CommandExample](images/CommandExample.png)
A command consists of:
1. Command Word to tell FoodRem what action you wish to execute. These actions are covered in [Commands](#commands)
2. [Flags](#flags) to distinguish parameters
3. [Placeholders](#placeholders) that you can replace with your parameter inputs


### Flags

Flags are delimiters that enable FoodRem to distinguish different parameters without ambiguity.

| Flags | Related Placeholder   |
|-------|-----------------------|
| id/   | INDEX                 |
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

### Trying your First Command

{% include_relative _ug/TryingFirstCommand.md %}

## Commands

{% include_relative _ug/Commands.md %}

## Command Summary

### Item Commands

{% include_relative _ug/commandSummary/ItemCommands.md %}

### Tag Commands

{% include_relative _ug/commandSummary/TagCommands.md %}

### Other Commands

{% include_relative _ug/commandSummary/OtherCommands.md %}

## Troubleshooting

<!-- TODO: Consider removing this section -->
<!-- Added one issue that is referred to by Quick Start -->
{% include_relative _ug/Troubleshooting.md %}

## FAQ

{% include_relative _ug/FAQ.md %}

## Acknowledgements

{% include_relative _ug/Acknowledgements.md %}

## Glossary

{% include glossary.md type="ug" %}
