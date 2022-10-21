---
layout: page
title: User Guide
---

# Welcome to RC4HDB User Guide!

If you are a Residential College 4 (RC4) Housing Management staff, or someone who wants to find out more
about what RC4HDB can do for you, you are at the right place!

In this user guide, you will find step-by-step instructions on how you can install RC4HDB, as well as how you can use
RC4HDB to its fullest potential.

If you are someone who is looking to expand on our project, check out our [Developer Guide](DeveloperGuide.md) too!

<!--
<details close>
<summary> test </summary>
<br>
Test
</details>
-->

## Table of Contents

* [Introduction to RC4HDB](#introduction-to-rc4hdb)
* [Using this guide](#using-this-guide)
* [Quick start](#quick-start)
* [Command Guide](#command-guide)
  * [Getting help](ug-pages/getting-help.md#viewing-help--help)
  * [Modifying residents](ug-pages/modifying-residents.md)
    * [Adding a resident `add`](ug-pages/modifying-residents.md#adding-a-resident--add)
    * [Editing an existing resident `edit`](ug-pages/modifying-residents.md#editing-an-existing-resident--edit)
    * [Deleting an existing resident `delete`](ug-pages/modifying-residents.md#deleting-a-resident--delete)
    * [Clearing all residents `clear`](ug-pages/modifying-residents.md#clearing-all-entries--clear)
    * [Resident field format](ug-pages/modifying-residents.md#format-for-resident-fields)
  * [Viewing residents](ug-pages/viewing-residents.md)
    * [Listing all residents `list`](ug-pages/viewing-residents.md#listing-all-residents--list)
    * [Finding residents `find`](ug-pages/viewing-residents.md#locating-residents-by-name--find)
    * [Filtering residents `filter`](ug-pages/viewing-residents.md#filtering-residents-by-field--filter)
    * [Showing resident fields `show`](ug-pages/viewing-residents.md) <!-- to update -->
    * [Hiding resident fields `hide`](ug-pages/viewing-residents.md) <!-- to update -->
  * [File management](ug-pages/file-management.md)
    * [Creating a new data file `file create`](ug-pages/file-management.md#creating-a-new-data-file--file-create)
    * [Deleting an existing data file `file delete`](ug-pages/file-management.md#deleting-an-existing-data-file--file-delete)
    * [Switching data file `file switch`](ug-pages/file-management.md#switching-to-a-different-data-file--file-switch)
    * [Importing from CSV file `import`](ug-pages/file-management.md#importing-from-csv-file--import)
    * [Exporting to CSV file `export`](ug-pages/file-management.md#exporting-to-csv-file--export)
    * [CSV file format](ug-pages/file-management.md#csv-file-format)
  * [Data management](ug-pages/data-management.md)
    * [Saving data](ug-pages/data-management.md#saving-the-data)
    * [Editing data file](ug-pages/data-management.md#editing-the-data-file)
  * [Quality-of-life](ug-pages/quality-of-life.md)  
    * [Keyboard shortcuts](ug-pages/quality-of-life.md#keyboard-shortcuts)
    * [Accessing command history](ug-pages/quality-of-life.md#accessing-command-history)
  * [Exiting RC4HDB `exit`](ug-pages/exiting-the-program.md#exiting-the-program--exit)
* [FAQ](ug-pages/faq.md#faq)
* [Glossary]()
* [Authors]()
* [Command Summary](ug-pages/command-summary.md#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Introduction to RC4HDB

RC4HDB is a desktop application that helps you, as a RC4 housing management staff, to tend to the all housing 
management related work, policies, and issues.

RC4HDB provides these main features:
* Add incoming residents.
* Access, modify details of residents.
* Filter through residents via house, gender, and more.
* Book venues available in RC4. 
* Export a filtered list of residents.

<div markdown="block" class="alert alert-info">
RC4HDB is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User 
Interface (GUI). If you can type fast, RC4HDB can be a convenient and intuitive way to facilitate your day-to-day 
workflow as a RC4 housing management staff.
</div>

---

## Using this guide

This user guide encompasses all the information that you will need to know as a user of RC4HDB. We hope that this user
guide will serve you well in mastering RC4HDB!

If you are a **new user**, this guide provides you with the necessary knowledge for you to [get started](#quick-start). 
As you read the user guide, you will find plenty of examples to help you familiarise with the features.

If you are an **experienced user**, a [Command Summary](ug-pages/command-summary.md) is also provided for your reference.

Before exploring the user guide, you should familiarise yourself with the following symbols.

<div markdown="block" class="a;ert alert-info">:information_source: **Note:** Used to highlight information you should 
pay attention to. </div>

<br>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** used to highlight tips. </div>

<br>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:** used to highlight dangers. </div>

---

## Quick start

Here is a quick start on how to get RC4HDB onto your computer.

### 1. Setup
Ensure you have [*Java 11*](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) or 
above installed on your Computer.

### 2. Install
Download the latest `rc4hdb.jar` from [here](https://github.com/AY2223S1-CS2103T-W12-3/tp/releases/). 
Move the `rc4hdb.jar` file to the folder you want to use as the _home folder_ for RC4HDB.

### 3. Running RC4HDB
Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
You may have noticed that upon double-clicking `rc4hdb.jar` for the first time, several files were created. **Do not 
delete the folder called `data` as this would cause all your data to be deleted**
</div>

<br>

![Ui](images/Ui.png)

### 4. Try it out!
Try typing `help` into the command box and pressing Enter to execute it! This command will open a help window which
shows you a quick summary of the available commands.

For more details on commands, refer to the [Command Guide](#command-guide) below.

--------------------------------------------------------------------------------------------------------------------

## Command Guide

Below shows a detailed breakdown of all rules that are applicable to the commands in RC4HDB. Don't be afraid that you
may have typed a command incorrectly as RC4HDB will step in as and when needed!

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken, unless otherwise specified.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  
---
