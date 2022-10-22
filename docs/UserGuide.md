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

TODO: Add links to e.g. name, bought date, expiry date, etc.

### Inventory Management System

 FoodRem allows you to track your inventory during your daily operations.

 1. Quickly **create, view, edit, and remove** your available inventory.
 2. **Sort** and **filter** your inventory by name, bought date, expiry date, quantity, unit and cost.
 3. **Tag** items in your inventory using an efficient tagging system where you can create, rename and delete existing tags. You can then filter and sort items according to these tags.

### Inventory Analysis

FoodRem tracks data that helps you streamline your business decisions through **statistics**.

1. Track your **most expensive items**.
2. View items that are **expiring** in the next 10 days.
3. Find out how much **cost was incurred** due to food wastage.

## How to use the User Guide

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
{% include_relative _ug/Troubleshooting.md %}

## FAQ

{% include_relative _ug/FAQ.md %}

## Acknowledgements

{% include_relative _ug/Acknowledgements.md %}

## Glossary

{% include glossary.md type="ug" %}
