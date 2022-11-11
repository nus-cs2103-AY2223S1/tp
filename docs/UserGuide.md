---
layout: page
title: PennyWise User Guide
---

![PennyWise Logo](images/PennyWiseLogo.png)

Well, hello there! Welcome to your personal budgeting assistant, PennyWise. After this guide, you will understand how to
use PennyWise, and spend every penny wisely. 🤑

## Introduction

PennyWise is a desktop application that **empowers students with the ability to make informed financial decisions**, by
providing a **graphical analysis of their financial activities**. It provides a clean Graphical User Interface (GUI) for
easy comprehension of expenditure and savings.
_Interested?_ Head over to [Quick Start](#quick-start) to get started. Enjoy!

<div markdown="span" class="alert alert-info">:information_source: **Info:**
Already done with the set-up? Jump straight to the [features](#features) section to see what features are available!
</div>

# Table of Contents

<div id="top">
</div>

<!-- TOC -->

* [Purpose of Guide](#purpose-of-guide)
* [How to use this User Guide](#how-to-use-this-user-guide)
    * [Information Box](#information-box)
    * [Tip Box](#tip-box)
    * [Danger Box](#danger-box)
    * [Formatting](#formatting)
* [Quick Start](#quick-start)
* [Before Using PennyWise](#before-using-pennywise)
    * [What is an Entry](#what-is-an-entry)
    * [Familiarising yourself with the interface](#familiarising-yourself-with-the-interface)
* [Understanding the command formats](#understanding-the-command-formats)
    * [Categorising your expenses and income](#categorising-your-expenses-and-income)
    * [Guidelines on organising your expenses and income](#guidelines-on-organising-your-expenses-and-income)
* [Features](#features)
    * [Adding entries:](#adding-entries)
        * [Add an expense entry to the expenditure list](#add-an-expense-entry-to-the-expenditure-list)
        * [Add an income entry to the income list](#add-an-income-entry-to-the-income-list)
    * [Editing entries:](#editing-entries)
        * [Edits expenditure with specified fields](#edits-expenditure-with-specified-fields)
        * [Edits income with specified fields](#edits-income-with-specified-fields)
    * [Deleting entries:](#deleting-entries)
        * [Delete an expense from the expenditure list](#delete-an-expense-from-the-expenditure-list)
        * [Delete an income from the income list](#delete-an-income-from-the-income-list)
    * [Summary of entries:](#summary-of-entries)
        * [Summary of all entries in the currently shown list](#summary-of-all-entries-in-the-currently-shown-list)
        * [Summary of all entries in the specified month](#summary-of-all-entries-in-the-specified-month)
    * [View entries by category](#view-entries-by-category)
    * [View entries by month](#view-entries-by-month)
    * [Command summary table](#command-summary-table)
    * [Clearing all entries](#clearing-all-entries)
    * [Exiting the program](#exiting-the-program)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Command summary](#command-summary)

<!-- TOC -->

---

## Purpose of Guide

This guide aims to teach those new to PennyWise how to navigate and utilise the application. It also acts as 
a refresher for any experienced or returning users, allowing you to make the most out of PennyWise.

## How to use this User Guide

These are some icons you may see throughout our user guide.

### Information Box

<div markdown="span" class="alert alert-info">:information_source: **Info:**
This provides some additional information that you are recommended to know.
</div>

### Tip Box

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**
This provides some quick and convenient hacks that you can use to optimize your experience with PennyWise.
</div>

### Danger Box

<div markdown="block" class="alert alert-danger">:exclamation: **Warning**
Danger zone! Do pay attention to the information here carefully.
</div>

### Formatting

- `Highlights` are used to denote commands or output from the application.

### Definitions

| Term                               | Definition                                                                                                                                                          |
|------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Entry**                          | An entry refers to either an expenditure or income                                                                                                                  |
| **Field**                          | Represents the Description, Amount, Date, Category and Type of an entry in the application.                                                                         |
| **Identifier**                     | The field code that are entered during user input, so that PennyWise knows what your inputs are e.g. `t/`, `d/`, `a/`, `da/` `c/`.                                  |
| **JSON File**                      | JavaScript Object Notation File. Data interchange format file that uses human-readable text to store and transmit data objects consisting of attribute–value pairs. |
| **Graphical User Interface (GUI)** | The graphical user interface is a form of user interface that allows users to interact with electronic devices through graphical icons.                             |

<p align="right">
    <a href="#top">Back to Top </a>
</p>

---

## Quick Start

If you are unfamiliar with using command prompt, head to
this [link](https://www.freecodecamp.org/news/command-line-for-beginners/) to learn more.

1. Ensure you have Java 11 or above installed in your Computer. You can check whether Java 11 is installed by following
   the instructions below:
    1. Open your terminal / command prompt window in your computer.
    2. Run the following command: `java -version`
    3. If Java 11 is not installed, please download Java 11
       from [Oracle](https://www.oracle.com/java/technologies/downloads/#java11). For Mac users, you may use
       the [Azul build of OpenJDK 11 (JDK FX) version](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).
2. Download the latest [pennywise.jar](https://github.com/AY2223S1-CS2103T-W17-2/tp/releases) application file.
3. Copy the file to the folder you want to use as the home folder for PennyWise.
4. Double-click the file to start the app. You should see a user interface similar to what is shown below in a few
   seconds.
    - The application contains some sample data that provides some examples on how you can use the application.

   ![Ui](images/Ui.png)
5. Type the command in the [command box](#familiarising-yourself-with-the-interface) and press Enter to execute it. e.g.
   typing [**`help`**](#command-summary-table) and pressing Enter will open the help window.<br>
   Some example commands you can try:

    - **`add`**`t/i d/Tution Teaching a/45.00 da/13-10-2022 c/Salary`: Adds an income entry with the
      description `Tuition Teaching` to the PennyWise application.

    - **`delete`**`3 t/e`: Deletes the 3rd entry shown in the expenditure list.

    - **`view`**`t/e`: View a Pie Chart of all expenses.

    - **`view`**`t/e mo/2022-08`: View a Line Graph of all expenses on August 2022.

    - **`clear`**: :exclamation: Deletes all entries.

    - **`exit`** : Exits the app.

6. Congrats! You are now ready to get started on supercharging your financial management flow!

<div markdown="span" class="alert alert-info">:information_source: **For Mac users**

When you first open up the application, you may encounter a warning that the application is from an unidentified developer.
To resolve this issue, please [open up the "System Preferences" application](https://support.apple.com/en-is/guide/mac-help/mh15217/12.0/mac/12.0) in Mac and select "Security & Privacy".
Then, allow the system to use PennyWise by clicking on the "Open Anyway" button as shown below.

![Mac Unidentified Developer](images/ug/MacUnidentifiedDeveloper.png)

</div>

<div markdown="span" class="alert alert-info">:information_source: **Info:**
Curious about the various commands? Refer to the [Features](#features) below to find out more about each command.
</div>

<p align="right">
    <a href="#top">Back to Top </a>
</p>

---

## Before Using PennyWise

### What is an Entry

Think of PennyWise as a helpful personal budgeting assistant. Whenever you spend or earn money, all you need to do is
tell PennyWise and PennyWise will log it and save it! An Entry is what PennyWise terms as a
particular expenditure or income logged into the application. PennyWise defines an expenditure and income as things that
you spend money on, and things that make you money respectively! Not only that, PennyWise will help you analyse your
data, so you can easily get an overview of your overall expenditure or income.


However, in order to do this, PennyWise will need some data from you! Whenever you log an entry, whether expenditures or
incomes, you need to tell PennyWise the following:

* Type: whether it is an expenditure or income
* Description: what is this particular entry about
* Amount: what was the amount associated with this particular entry
* Date: when was this entry logged in PennyWise
* Category: how this entry is classified

<div markdown="span" class="alert alert-info">:information_source: **Info:**
In PennyWise, there is an upper limit of $1 000 000 for amounts.
</div>

### Familiarising yourself with the interface

Let's get acquainted with the application with the help of the following diagram!

When you first open up the application, this is the *default view*, which shows the list of expenditures:

![UserInterfaceExplanation](images/ug/UserInterfaceExplanation.png)

|                                        Component                                         |                                                                       Explanation                                                                       |
|:----------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------:|
|     ![](https://img.shields.io/static/v1?label=&message=List%20Display&color=862e9c)     |          Where you view your entry lists (Expenditure or Income) <br> This can be filtered by the [`view`](#view-entries-by-category) command           |
|    ![](https://img.shields.io/static/v1?label=&message=Graph%20Display&color=5c940d)     | Where you view your entries in a graphical overview (Pie Chart or Line Graph) <br> This can be modified by the [`view`](#view-entries-by-month) command |
| ![](https://img.shields.io/static/v1?label=&message=Command%20Input%20Box&color=e67700)  |                                                     Where you enter your commands to use PennyWise                                                      |
| ![](https://img.shields.io/static/v1?label=&message=Command%20Output%20Box&color=d9480f) |                                                Where you see the output of your commands from PennyWise                                                 |

<div markdown="block" class="alert alert-danger">:exclamation: **Warning**
If the application is resized to a smaller screen, certain graphical components will be hidden. This is to ensure that
you still see the most important information about your budgeting. If details of your budgeting are important, use PennyWise in fullscreen instead!

1. Pie Chart Legend <br/>

   - The Pie Chart Legend will be hidden should the application be of a smaller size. This ensures your categorical
     overview can still be seen at a glance!
     ![ChartLegend](images/ug/ChartLegend.png)

2. Line Graph Dates

   - Certain Line Graph dates will be hidden should the application be of a smaller size. This ensures you can still view
     the trend of your entries easily.
     ![LineGraphDates](images/ug/LineGraphDates.png)

</div>

---

## Understanding the command formats

All commands follow this format except: [`delete`](#deleting-entries),
[`clear`](#clearing-all-entries), [`exit`](#exiting-the-program) and [`help`](#command-summary-table).<br>
<pre>
    command | entry identifier | input | [optional inputs]
</pre>

For example, given the command `add t/e d/Lunch a/15.60 da/15-08-2022 c/Food`, let us deconstruct the command format
step-by-step!

|      Format      |                Example                 |
|:----------------:|:--------------------------------------:|
|     Command      |                 `add`                  |
| Entry Identifier |                 `t/e`                  |
|      Input       | `d/Lunch a/15.60 da/15-08-2022 c/Food` |
|  Optional Input  |                  NIL                   |

- Letters followed by a forward slash is called an identifier and will be used to specify a specific parameter<br>
  e.g. `t/` refers to the entry type parameter.

- PennyWise will always take the **LAST OCCURRENCE** of a command identifier should there be more than 1 of the same command
  identifier in a given command. <br/>
  e.g. PennyWise will add the following **Income** with the description: "October Allowance" upon running the following command.
  
  ```
  add t/i d/Monthly Allowance a/200.00 da/01-10-2022 c/Allowance
  d/Oct Allowance d/October Allowance
  ```
  
  You should expect to see this output:

    ```
    New entry added: October Allowance; Date: 01-10-2022; Amount: 200.00;
    Tag: Allowance
    ```

- PennyWise will **IGNORE** any extra words or characters present after the following commands:

  [`summary`](#summary-of-entries) (except [`summary [mo/MONTH]`](#summary-of-all-entries-in-the-specified-month)),
  [`help`](#command-summary-table), [`clear`](#clearing-all-entries) and [`exit`](#exiting-the-program).
  e.g. `summary abcdefg` will be interpreted as `summary` and `help 123456` will also be interpreted as `help`.

- Words in `UPPER_CASE` are the parameters that you must supply<br>
  e.g. In `delete INDEX_OF_ENTRY t/ENTRY_TYPE`, `INDEX_OF_ENTRY` is a parameter which can be used as `delete 10 t/e`.

- Items in square brackets are optional.<br>
  e.g. `view t/ENTRY_TYPE [MONTH]` can be used as `view t/e mo/2022-05` or as `view t/e`.

- **ALL** identifiers are <ins>case-sensitive</ins>.
  e.g. `d/Lunch` as a descriptor for "Lunch" is accepted by PennyWise, however `D/Lunch` would not be accepted.

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
Adding identifiers that are not used in the command will result in an error.
</div>

### Categorising your expenses and income

For **ALL** entries, categories are <ins>COMPULSORY</ins> and every entry can only contain <ins>one</ins> category. The
following table lists the **Expenditure** and **Income** entries categories. The category names are <ins>
case-insensitive</ins>. E.g. `Food` is same as `food`.

|    Expenditure    |     Income      |
|:-----------------:|:---------------:|
|     **Food**      |   **Salary**    |
|   **Groceries**   |  **Allowance**  |
| **Entertainment** |   **Profit**    |
|   **Education**   | **Investments** |
|    **Housing**    |    **Gifts**    |
|    **Others**     |   **Others**    |

For example, an expense entry can be tagged with `Food`, but not `Salary`.

### Guidelines on organising your expenses and income

For **ALL** entries, 2 entries are considered duplicates <ins>IF</ins> both entries have exactly the same:
`description`, `date` `amount` and `category`.

<div markdown="span" class="alert alert-info">:information_source: **Info:**
Having multiple duplicate entries will make it difficult for you to track your expenses later on, and could clutter the list.
Having them differentiated will make it easier for you to recall what you spent your money on!
</div>

If you want to log similar entries, you can simply use the [edit](#editing-entries)
command to alter the original entry's specifications.

For example, when executing the following [add commands](#adding-entries) one after another, PennyWise will recognise (2), which is the second `Teh Beng` added as a duplicate entry.

<pre>
    <code>
        1. add t/e d/Teh Beng a/1.50 da/22-10-2022 c/food
        2. add t/e d/teh beng a/1.50 da/22-10-2022 c/Food
    </code>
</pre>

To add similar entries, vary the description/amount/date/category to let PennyWise know they are not duplicates!

<pre>
    <code>
        1. add t/e d/Teh Beng 11am a/1.50 da/22-10-2022 c/Food
        2. add t/e d/Teh Beng 4pm a/1.50 da/22-10-2022 c/Food
    </code>
</pre>

OR we could even [edit](#editing-entries) the original entry directly to reflect 2 cups of `Teh Beng` consumed.

<pre>
    <code>
        1. edit 1 t/e d/2 Teh Beng 11am a/3.00 da/22-10-2022 c/Food
    </code>
</pre>

<div markdown="span" class="alert alert-info">:information_source: **Info:**
Descriptions are case-insensitive as well! PennyWise will interpret `Teh Beng` as the same as `teh beng`.
</div>


<p align="right">
    <a href="#top">Back to Top </a>
</p>

---

## Features

### Adding entries

Perhaps you just bought a cup of coffee, or a paycheck comes in. Add these entries to PennyWise to keep track of them.

Format: `add t/ENTRY_TYPE d/DESCRIPTION a/AMOUNT da/DATE c/CATEGORY` <br/>
Adds an entry to the specified list.

| Input               | Explanation                                                                                  |
|---------------------|----------------------------------------------------------------------------------------------|
| **`t/ENTRY_TYPE`**  | An entry type refers to either `e` for "Expenditure" or `i` for "Income"                     |
| **`d/DESCRIPTION`** | Description for the entry that you are adding                                                |
| **`a/AMOUNT`**      | Amount of the entry, formatted up to 2 decimal places                                        |
| **`da/DATE`**       | Date where the entry is added in `dd-mm-YYYY` format, e.g. `01-01-2022` for 1st January 2022 |
| **`c/CATEGORY`**    | [Category](#categorising-your-expenses-and-income) that the entry belongs to                 |

#### Add an expense entry to the expenditure list

- Example: `add t/e d/Lunch a/15.60 da/10-10-2022 c/Food`
    - Here, we record our $15.60 `Lunch` expenses on 10th October 2022, and tag the entry under `Food`.
- Expected: `New entry added: Lunch; Date: 10-10-2022; Amount: 15.60; Tag: Food`

![AddCommandExpenditure](images/ug/AddCommandExpenditure.png)

#### Add an income entry to the income list

- Example: `add t/i d/Tuition a/40.00 da/10-10-2022 c/Salary`
    - Here, we record our $40.00 income from `Tuition` on 10th October 2022 and tag the entry under `Salary`.
- Expected: `New entry added: Tuition; Date: 10-10-2022; Amount: 40.00; Tag: Salary`

![AddCommandIncome](images/ug/AddCommandIncome.png)

<div markdown="span" class="alert alert-danger">:exclamation: **Can't see the entry you just added?:**
If you did a [view by month](#view-entries-by-month) command before adding new entries, and you cannot find the entry you just added in the
List Display, don't worry! This is because List Display is still filtered by the month specified.
If the entry you just added does not fall within the month, it will not be shown. Simply use the [view by category](#view-entries-by-category)
command to get the entire entry list, and you will find your newly added entry!
Refer to the sections on [view](#view-entries-by-category) command to find out more.
</div>

### Editing entries

Whoops! You made an error in your previous entry. Fret not, you can easily edit it.

Format: `edit INDEX_OF_ENTRY t/ENTRY_TYPE [d/EDITED_DESCRIPTION a/EDITED_AMOUNT da/EDITED_DATE c/EDITED_CATEGORY]` <br/>
Edits an entry, where at least **1** of the optional fields description/amount/date/category must be present.

| Input                        | Explanation                                                                                          |
|------------------------------|------------------------------------------------------------------------------------------------------|
| **`t/ENTRY_TYPE`**           | An entry type refers to either `e` for "Expenditure" or `i` for "Income"                             |
| **`[d/EDITED_DESCRIPTION]`** | Updated description for the entry that you are editing                                               |
| **`[a/EDITED_AMOUNT]`**      | Updated amount of the entry, formatted up to 2 decimal places                                        |
| **`[da/EDITED_DATE]`**       | Updated date where the entry is added in `dd-mm-YYYY` format, e.g. `01-01-2022` for 1st January 2022 |
| **`[c/EDITED_CATEGORY]`**    | Updated [category](#categorising-your-expenses-and-income) that the entry belongs to                 |

#### Edits expenditure with specified fields

- Example: `edit 1 t/e d/Chicken Soup`
    - The expenditure at the 1st position will have its description edited from `Chicken Rice` to `Chicken Soup`.
- Expected: `Edited Entry: Chicken Soup; Date: 20-01-2022; Amount: 20.00; Tag: Food`

![EditCommandExpenditure](images/ug/EditCommandExpenditure.png)

#### Edits income with specified fields

- Example: `edit 2 t/i a/150.00 da/22-10-2022`
    - The income at the 2nd position will have its amount and date edited.
- Expected: `Edited Entry: Monthly Allowance; Date: 02-01-2022; Amount: 150.00; Tag: Allowance`

![EditCommandIncome](images/ug/EditCommandIncome.png)

### Deleting entries

You were going to watch the latest Kungfu Panda movie, but your friend bailed on you 😔. Or you were going to sell your
old textbooks only to find the buyer cancelled his order. You already logged the entry, so now you need to delete it.

Format:  `delete INDEX_OF_ENTRY t/ENTRY_TYPE` <br/>
Deletes an entry from the specified list.

| Input                | Explanation                                                                                                   |
|----------------------|---------------------------------------------------------------------------------------------------------------|
| **`INDEX_OF_ENTRY`** | Position of the entry that you wish to delete from the specified list, where the first entry is at position 1 |
| **`t/ENTRY_TYPE`**   | An entry type refers to either `e` for "Expenditure" or `i` for "Income"                                      |

#### Delete an expense from the expenditure list

- Example: `delete 2 t/e` deletes the 2nd item on the expenditure list.
    - Here, we want to delete the `Kungfu Panda Movie` expenditure entry, which is the 2nd entry in the expenditure
      list.
- Expected: `Deleted Entry: Kungfu Panda Movie; Date: 18-02-2022; Amount: 15.00; Tag: Entertainment`

![DeleteCommandExpenditure](images/ug/DeleteCommandExpenditure.png)

#### Delete an income from the income list

- Example: `delete 2 t/i` deletes the 2nd item on the income list.
    - Here, we want to delete the `SpaceX Stocks` income entry, which is the 2nd entry in the income list.
- Expected: `Deleted Entry: Allowance; Date: 12-09-2022; Amount: 100.00 Tag: Allowance`

![DeleteCommandIncome](images/ug/DeleteCommandIncome.png)

### Summary of entries

It's been a long month with its ups and down. You wonder how your finances did this month. Use the summary command to
find out!

Format: `summary [mo/MONTH]` <br/>
To summarise the entries in PennyWise, we compute 3 simple statistics to let you have a quick overview of your
expenditure and income.

| Statistic             | Explanation                                                 |
|-----------------------|-------------------------------------------------------------|
| **Total Expenditure** | Sums up all the expenditure amounts                         |
| **Total Income**      | Sums up all the income amounts                              |
| **Total Balance**     | Amount of income left after deducting the total expenditure |

The command format is provided below:

| Input            | Explanation                                                                                                                     |
|------------------|---------------------------------------------------------------------------------------------------------------------------------|
| **`[mo/MONTH]`** | Allows you to customize the summary statistic to only consider entries in a specified month in `YYYY-mm` format, e.g. `2022-01` |

#### Summary of all entries in the currently shown list

- Example: `summary`
- Expected:

    <pre>
       Total Expenditure: $154.49
       Total Income: $250.00
       Total Balance: $95.51
    </pre>

![SummaryCommand](images/ug/SummaryCommand.png)

#### Summary of all entries in the specified month

- Examples: `summary mo/2022-10`
- Expected:

    <pre>
          Financials Summarized
          Total Expenditure: $4.20
          Total Income: $250.00
          Total Balance: $245.80
    </pre>

![SummaryCommandMonth](images/ug/SummaryCommandMonth.png)

- Provides a financial summary recorded by the user in a month. The month refers to the month that is displayed to the
  user.
- The `MONTH` field is optional, if no month is specified, the application displays the summary for all entries.

### View entries by category

You've always been a foodie, but how much of your total expenditure is spent on food?

You have multiple income streams, but which helped you earn the most money? View your entries in a pie chart to find
out.

Format: `view t/ENTRY_TYPE`

| Input              | Explanation                                                              |
|--------------------|--------------------------------------------------------------------------|
| **`t/ENTRY_TYPE`** | An entry type refers to either `e` for "Expenditure" or `i` for "Income" |

1. View a Pie Chart of all expenditures by categories
    - Examples: `view t/e`
    - Expected: `Show graphically all expenditure by category` and a Pie Chart on the right of the application.

   ![ViewCommandExpenditureCategory](images/ug/ViewCommandExpenditureCategory.png)

2. View a Pie Chart of all incomes by categories
    - Examples: `view t/i`
    - Expected: `Show graphically all income by category` and a Pie Chart on the right of the application

### View entries by month

You went on a shopping spree today. Find out how today's expenditure compared to the rest of the month.

Or, were you tipped for your exceptional service as a part-time server today? Find out how today's income weighs up to the
rest of the month.

Format: `view t/ENTRY_TYPE mo/MONTH`

| Input              | Explanation                                                                              |
|--------------------|------------------------------------------------------------------------------------------|
| **`t/ENTRY_TYPE`** | An entry type refers to either `e` for "Expenditure" or `i` for "Income"                 |
| **`mo/MONTH`**     | Allows you to view only entries in a specified month in `YYYY-mm` format, e.g. `2022-01` |

1. View a Line Graph of all expenditures in a specified month
    - Examples: `view t/e mo/2022-01` where we specify the month to be January 2022.
    - Expected: `Show graphically all expenditure by month` and a Line Graph on the right of the application.
   The List Display will also be filtered to show only expenditures in Jan 2022.

   ![ViewCommandExpenditureMonth](images/ug/ViewCommandExpenditureMonth.png)

2. View a Line Graph of all incomes in a specified month
    - Examples: `view t/i mo/2022-01`
    - Expected: `Show graphically all income by month` and a Line Graph on the right of the application
    The List Display will also be filtered to show only income in Jan 2022.

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**
You can think of the view entries by month command as applying a month filter to the entry list,
and view entries by category command as clearing any filters on the entry list. <br/>
To switch back to the [default view](#familiarising-yourself-with-the-interface) of the list of expenditures, 
simply use the [`view t/e` command](#view-entries-by-category). If you would like to switch back to the 
[default view](#familiarising-yourself-with-the-interface) of the list of income, you can use the 
[`view t/i` command](#view-entries-by-category).
</div>

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
Note that if you manually switch between the income list display and expenditure list display, all filters (if any)
will be cleared and a pie chart will be drawn. Manual toggling is equivalent to typing the command 
[`view t/e`](#view-entries-by-category) or [`view t/i`](#view-entries-by-category).
</div>

### Command summary table

You forgot the command formats and do not have internet to access the online user guide. 
You just need to refresh your memory on the available command and command formats.

Format: `help` <br/>
Opens up the command summary table

### Clearing all entries

You're giving your laptop to your younger brother and already backed up your PennyWise data. Clear all your entries so
your brother doesn't see what you spent on.

Format: `clear` <br/>
Clears all entries in PennyWise.

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
Danger zone! This command is irreversible, it is not possible to retrieve entries that are cleared.
</div>

### Exiting the program

You're done logging your expenses and income for the day. Let's exit the application.

Format: `exit` <br/>
Exits the program.

### Saving the data

PennyWise data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

PennyWise data are saved as a file `[JAR file location]/data/pennywise.json`. Advanced users with knowledge of JSON
file format are welcomed to update data directly by editing that data file.

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
If your changes to the data file violate the format, PennyWise will discard all data and start with an empty data file at the next run.
</div>

<p align="right">
    <a href="#top">Back to Top </a>
</p>

---

## FAQ

**Q**: Can I use PennyWise without internet? <br>
**A**: Absolutely! In fact, PennyWise is meant to be used as an offline application. This means that your personal data
will be even more secure as well!

**Q**: How do I transfer my data to another Computer? <br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous PennyWise home folder.

**Q**: Why is my edit and delete command invalid? I am sure I included all the required parameters. <br>
**A**: Make sure that your `INDEX_OF_ENTRY` is the first parameter after the command word.

**Q**: I want to have a `Medical` category to log my expenses. Is there anyway I can do it? <br>
**A**: The categories for income and expenses entries are fixed. However, we are exploring the possibilities of [user
added categories](#categorising-your-expenses-and-income) so do look out for our software updates! 
For now, kindly categorise them under `Others`.

**Q**: Why are there missing dates on my Line Graph? <br>
**A**: PennyWise automatically removes certain elements to ensure you can still get an overview of your finances!
If you want to view all the dates, enlarge your application!

**Q**: I prefer a date format like 4 Apr 2022. Why can't I pick the date format I want to use ? <br>
**A**: Unfortunately PennyWise currently only accepts one type of format which is `04-04-2022`. However, we are exploring
the possibilities of allowing more types of date formats without compromising on quality and user experience so do look
out for our future software updates!

<p align="right">
    <a href="#top">Back to Top </a>
</p>

---

## Command summary

| Action              | Format, Examples                                                                                                                                        |
|---------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**             | `add t/ENTRY_TYPE d/DESCRIPTION a/AMOUNT da/DATE c/CATEGORY` <br> e.g. `add t/e d/Lunch a/15.60 da/10-10-2022 c/Food`                                   |
| **Delete**          | `delete INDEX_OF_ENTRY t/ENTRY_TYPE` <br> e.g. `delete 2 t/e`                                                                                           |
| **Edit**            | `edit INDEX_OF_ENTRY t/ENTRY_TYPE [d/EDITED_DESCRIPTION a/EDITED_AMOUNT da/EDITED_DATE c/EDITED_CATEGORY]`<br> e.g. `edit 2 t/i a/150.00 da/22-10-2022` |
| **Summary**         | `summary [mo/MONTH]`<br> e.g. `summary mo/2022-09`                                                                                                      |
| **View (Category)** | `view t/ENTRY_TYPE` <br> e.g. `view t/e`                                                                                                                |
| **View (Month)**    | `view t/ENTRY_TYPE mo/MONTH` <br> e.g. `view t/i mo/2022-10`                                                                                            |
| **Clear**           | `clear`                                                                                                                                                 |
| **Exit**            | `exit`                                                                                                                                                  |
| **Help**            | `help`                                                                                                                                                  |


<p align="right">
    <a href="#top">Back to Top </a>
</p>
