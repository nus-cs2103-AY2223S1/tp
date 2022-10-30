---
layout: page
title: User Guide
---

# Welcome to RC4HDB User Guide!

If you are a **Residential College 4 (RC4)** Housing Management staff, or someone who wants to find out more
about what **Residential College 4 Housing Database (RC4HDB)** can do for you, you are at the right place!

In this user guide, you will find step-by-step instructions on how you can install **RC4HDB**, as well as how you
can use **RC4HDB** to its fullest potential.

If you are looking to expand on our project, check out our [Developer Guide](DeveloperGuide.md) too!

## Table of Contents

* [**Introduction to RC4HDB**](#introduction-to-rc4hdb)
* [**Using this guide**](#using-this-guide)
* [**Quick start**](#quick-start)
* [**Command guide**](#command-guide)
  * [**Getting help**](ug-pages/getting-help.md#viewing-help--help)
  * [**Modifying resident data**](ug-pages/modifying-resident-data.md)
    * [Adding a resident `add`](ug-pages/modifying-resident-data.md#adding-a-resident--add)
    * [Editing an existing resident `edit`](ug-pages/modifying-resident-data.md#editing-an-existing-resident--edit)
    * [Deleting an existing resident `delete`](ug-pages/modifying-resident-data.md#deleting-a-resident--delete)
    * [Clearing all residents `clear`](ug-pages/modifying-resident-data.md#clearing-all-entries--clear)
    * [Resident field format](ug-pages/modifying-resident-data.md#format-for-resident-fields)
  * [**Viewing residents**](ug-pages/viewing-residents.md)
    * [Listing all residents `list`](ug-pages/viewing-residents.md#listing-all-residents--list)
    * [Showing resident fields `showonly`](ug-pages/viewing-residents.md#showing-only-some-columns--showonly)
    * [Hiding resident fields `hideonly`](ug-pages/viewing-residents.md#hiding-only-some-columns--hideonly)
    * [Resetting hidden resident fields `reset`](ug-pages/viewing-residents.md#resetting-hidden-columns--reset)
    * [Finding residents `find`](ug-pages/viewing-residents.md#locating-residents-by-name--find)
    * [Filtering residents `filter`](ug-pages/viewing-residents.md#filtering-residents-by-field--filter)
  * [**File management**](ug-pages/file-management.md)
    * [Creating a new data folder `file create`](ug-pages/file-management.md#creating-a-new-data-folder--file-create)
    * [Deleting an existing data folder `file delete`](ug-pages/file-management.md#deleting-an-existing-data-folder--file-delete)
    * [Switching to a different data folder `file switch`](ug-pages/file-management.md#switching-to-a-different-data-folder--file-switch)
    * [Importing resident data from CSV file `file import`](ug-pages/file-management.md#importing-resident-data-from-csv-file--file-import)
    * [CSV file format](ug-pages/file-management.md#csv-file-format)
  * [**Venue management**](ug-pages/venue-management.md)
    * [Viewing the bookings](ug-pages/venue-management.md#viewing-the-bookings)
    * [Adding a venue `venue add`](ug-pages/venue-management.md#adding-a-venue--venue-add)
    * [Deleting a venue `venue delete`](ug-pages/venue-management.md#deleting-a-venue--venue-delete)
    * [Viewing a venue `venue view`](ug-pages/venue-management.md#viewing-a-venue--venue-view)
    * [Adding a booking `venue book`](ug-pages/venue-management.md#adding-a-booking-venue-book)
    * [Deleting a booking `venue unbook`](ug-pages/venue-management.md#deleting-a-booking-venue-unbook)
    * [Format for venue fields](ug-pages/venue-management.md#format-for-venue-fields)
  * [**Data management**](ug-pages/data-management.md)
    * [Saving data](ug-pages/data-management.md#saving-the-data)
    * [Editing data file](ug-pages/data-management.md#editing-the-data-file)
  * [**Quality-of-life**](ug-pages/quality-of-life.md)
    * [Keyboard shortcuts](ug-pages/quality-of-life.md#keyboard-shortcuts)
    * [Accessing command history](ug-pages/quality-of-life.md#accessing-command-history)
  * [**Exiting RC4HDB `exit`**](ug-pages/exiting-the-program.md#exiting-the-program--exit)
  * [**Upcoming features**](ug-pages/upcoming-features.md)
* [**FAQ**](ug-pages/faq.md#faq)
* [**Glossary**](ug-pages/glossary.md)
* [**Authors**](ug-pages/authors.md)
* [**Command Summary**](ug-pages/command-summary.md#command-summary)

---

## Introduction to RC4HDB

**RC4HDB** is a desktop application which streamlines the daily workflow of **RC4 housing management staff**, by providing specialised features which solve their resident and venue management needs.

Broadly, **RC4HDB** provides users with the ability to:
* View and manage resident data
* View and manage venues and any bookings
* Keep and manage multiple data files
* Easily switch over to RC4HDB by importing old data from CSV files

<div markdown="span" class="alert alert-info">
If you can type fast, RC4HDB can be a convenient and intuitive way to facilitate your day-to-day
workflow as a RC4 housing management staff member.
</div>

[Back to Top](#welcome-to-rc4hdb-user-guide)

## Using this guide

This user guide contains all the information that you will need to know as a user of **RC4HDB**. We hope that it
will serve you well in mastering RC4HDB!

If you are a **new user**, the necessary knowledge for you to get started can be found [here](#quick-start).
As you read on, you will find plenty of examples to help you familiarise with the features.

If you are an **experienced user**, a [Command Summary](ug-pages/command-summary.md) is also provided, so you don't have to memorise our commands.

Before exploring the user guide, you should familiarise yourself with the following symbols.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Used to highlight information you should
pay attention to. </div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** used to highlight tips. </div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:** used to highlight dangers. </div>

[Back to Top](#welcome-to-rc4hdb-user-guide)

## Quick start

Here is a quick guide on how to get a working copy of RC4HDB on your computer.

### 1. Setup

Ensure you have [*Java 11*](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) or
above installed on your Computer.

### 2. Install

Visit this [link](https://github.com/AY2223S1-CS2103T-W12-3/tp/releases/). Click on the underlined `rc4hdb.jar` button to download the latest `rc4hdb.jar`. Move the `rc4hdb.jar` file to the folder you want to use as the _home folder_ for RC4HDB. The home folder is the folder from which the application will run, save data to, and retrieve data from.

### 3. Running RC4HDB

Double-click the file to start the app. The **Graphical User Interface (GUI)** similar to the one shown
below should appear in a few seconds.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
You may have noticed that upon double-clicking `rc4hdb.jar` for the first time, several files were created. **Do not
delete the folder called `data` as this would cause all your data to be deleted!**
</div>

<br>

![Ui](images/Ui.png)

### 4. Try it out!

Try typing `help` into the command box where it says "Enter Command here..." and pressing the Enter/Return key on your keyboard to execute it! This command will open a help window which shows you a quick summary of our available commands.

For more details on commands, refer to our [Command Guide](#command-guide) below.

[Back to Top](#welcome-to-rc4hdb-user-guide)

---

## Command Guide

Below shows a breakdown of how the commands in **RC4HDB** are structured. Don't be afraid that you
may have typed a command incorrectly as **RC4HDB** will warn you in the event that this happens!

<div markdown="block" class="alert alert-info">

* Words in `UPPER_CASE` are compulsory parameters and must be entered by you.<br>
  Parameters are texts you enter to tell **RC4HDB** the information necessary for the command to work.

  e.g. when using the `add` command, `NAME` is a parameter which you must enter. Thus, the command format is as such:`add n/NAME`. To add a resident named "John Doe", you can then enter `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times. This also includes 0 times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken,
  unless otherwise specified.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Irrelevant parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

[Back to Top](#welcome-to-rc4hdb-user-guide)

---
