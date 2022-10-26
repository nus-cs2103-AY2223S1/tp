# PennyWise User Guide
> :warning: Prerequisite:
> * This guide assumes that the reader is familiar with using [Java](https://www.java.com/)


# Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
   1. [Adding entries: `add t/ENTRY_TYPE d/DESCRIPTION a/AMOUNT da/DATE c/CATEGORY`]()
   2. [Deleting entries: `del INDEX_OF_ENTRY t/ENTRY_TYPE`](#deleting-spendings-del--s-index_of_spendings)
   3. [Editing entries: `edit INDEX_OF_ENTRY t/ENTRY_TYPE [d/EDITED_DESCRIPTION a/EDITED_AMOUNT da/EDITED_DATE c/EDITED_CATEGORY]`](#adding-income-add--i-number-date)
   4. [Summary of entries : `summary [mo/MONTH]`](#deleting-income-del--i-number-index_of_income)
   5. [View entries by category: `view t/ENTRY_TYPE g/GRAPH_TYPE`](#view-total-summary-of-spending-view--s-month)
   6. [View entries by month: `view t/ENTRY_TYPE g/GRAPH_TYPE [mo/MONTH]`](#view-total-summary-of-income-view--i-month)
   7. [Clearing all entries : `clear`]()
   8. [Exiting the program : `exit`]()
4. [FAQ](#faq)
5. [Command Summary](#command-summary)

## Introduction

PennyWise is a desktop application that **empowers students with the ability to make sensible financial decisions**,
helping students to manage their budget. It is **optimised for use via Command Line Interface** (CLI) while providing a
clean Graphical User Interface (GUI) for easy comprehension of expenditure and savings. _Interested?_ Head over to
“Quick Start” to get started. Enjoy!

## Quick start
1. Ensure you have Java 11 or above installed in your Computer. You can check whether Java 11 is installed by following
   the instructions below:
    1. Open your terminal window
    2. Run the following command: `java -version`
    3. If Java 11 is not installed, please download Java 11 from [Oracle](https://www.oracle.com/java/technologies/downloads/#java11).
       For Mac users, you may use the [Azul build of
       OpenJDK 11 (JDK FX) version](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).
2. Download the latest pennywise.jar from here.
3. Copy the file to the folder you want to use as the home folder for your PennyWise.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.
   ![Ui](images/Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    - **`list`** : Lists all contacts.

    - **`add`**`t/i d/TutionTeaching a/45.00 da/13-10-2022 c/Salary` : Adds an income entry
      with the description `TuitionTeaching` to the PennyWise application.

    - **`delete`**`3 t/e` : Deletes the 3rd contact shown in the expenditure.

    - **`view`**`t/e g/c` : View a PieChart of all expenses.

    - **`view`**`t/e g/m mo/2022-08` : View a LineGraph of all expenses on August 2022.

    - **`clear`** : Deletes all entries.

    - **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- All command follow this format:<br>
  `command | entry identifier | input | [optional inputs]`<br>
  Example: `add t/e d/Lunch a/15.60 da/15-08-2022 c/Meal` <br>
    - Command : add<br>
    - Identifier : t/e<br>
    - Input : d/Lunch a/15.60 da/15-08-2022 c/Meal<br>
    - Optional input : NIL<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `del INDEX_OF_ENTRY t/ENTRY_TYPE`, `INDEX_OF_ENTRY` is a parameter which can be used as `del 10 t/e`.

- Items in square brackets are optional.<br>
  e.g `view t/ENTRY_TYPE g/GRAPH_TYPE [MONTH]` can be used as `view t/e g/c [mo/2022-05]` or as `view t/e g/c`.
</div>

## Before Using

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the application:**<br>
- For **ALL** entries, categories are <ins>compulsory</ins> and every entry can only contain <ins>one</ins> category. 
  The following table shows the`Expenditure` and `Income` entries categories.

|   `Expenditure`   |    `Income`     |
|:-----------------:|:---------------:|
|     **Food**      |   **Salary**    |
|   **Groceries**   |  **Allowance**  |
| **Entertainment** |   **Profit**    |
|   **Education**   | **Investments** |
|    **Housing**    |    **Gifts**    |
|    **Others**     |   **Others**    |

- For **ALL** entries, 2 entries are considered duplicates <ins>IF</ins> both entries have exactly the same:
  `description`, `date` `amount` and `category`. We do not want PennyWise to be managing entries which are a repeat,
  of one another, as it would be easier to simply use the `edit` command to alter the original entry's specifications.
  e.g. Executing the following 2 commands one after another, PennyWise will recognise (2) as a duplicate entry. 
  <pre>
    <code>
        1. add t/e d/TehBeng a/1.50 da/22-10-2022 c/Food 
        2. add t/e d/TehBeng a/1.50 da/22-10-2022 c/Food
    </code>
  </pre>

</div>

### Adding entries: `add t/ENTRY_TYPE d/DESCRIPTION a/AMOUNT da/DATE c/CATEGORY`
Adds an entry to the specified list. <br>
1. Add expense <br>
   - Example: `add t/e d/Lunch a/15.60 da/10-10-2022 c/Food`<br>
   - An expense of 15.60 on 10/10/2022 will be added to the Expenditure list, under the Food category.

1. Add income <br>
   - Example: `add t/i d/Tuition a/40.00 da/10-10-2022 c/Salary`<br>
   - An income of 40.00 on 10/10/2022 will be added to the Income list, under the Salary category.

### Deleting entries: `del INDEX_OF_ENTRY t/ENTRY_TYPE`
Deletes an entry.
1. Example: `del 2 t/e` deletes the 2nd item on the expenditure list 
   Expenditure list:
   1. Movie 15.60 12 Sep 2022 c/Entertainment
   2. Drink 1.20 12 Sep 2022 c/Food 
   Expected: `Deleted Entry: Drink; Date: 12-09-2022; Amount: 1.20; Tag: Food`
   
1. Example: `del 2 t/i` deletes the 2nd item on the income list
   Income list:
   1. Tuition 40.00 12 Sep 2022 c/Salary
   2. Allowance 100.00 12 Sep 2022 c/Allowance
   Expected: `Deleted Entry: Allowance; Date: 12-09-2022; Amount: 100.00 Tag: Allowance`


### Editing entries: `edit INDEX_OF_ENTRY t/ENTRY_TYPE [d/EDITED_DESCRIPTION a/EDITED_AMOUNT da/EDITED_DATE c/EDITED_CATEGORY]`
Edits an entry, at least **1** of the optional fields must be present.

1. Edits expenditure with specified fields
   * Example: `edit 1 t/e d/ChickenRice`
   * The expenditure at index 1 will have its description edited
   * Expected: `Edited Entry: ChickenRice; Date: 21-10-2022; Amount: 4.20; Tag: Food`
1. Edits income with specified fields
   * Example: `edit 2 t/i a/150.00 da/22-10-2022`
   * The income at index 2 will have its amount and date edited
   * Expected: `Edited Entry: CafeSalary; Date: 22-10-2022; Amount: 150.00; Tag: Salary`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The default date is the current date on your computer!
</div>

### Summary of entries : `summary [mo/MONTH]`

1. View a summary of all entires
   * Examples: `summary`
     * Expected: <br/>
       Financials Summarized <br/>
       Total Expenditure: 154.40 <br/>
       Total Income: 150.00 <br/>
       Total Balance: -4.40 <br/>
2. View a summary of all entires the specified month
    * Examples: `summary mo/2022-09`
    * Expected: A summary of entries in September 2022: `Total amount spent in September 2022: $37.70`

* Provides a financial summary recorded by the user in a month. The month refers to the month that is displayed to the user.
* The `MONTH` field is optional, if no month is specified, the application displays the current month.

### View entries by category: `view t/ENTRY_TYPE g/GRAPH_TYPE`

1. View a PieChart of all expenditures by categories
    * Examples: `view t/e g/c`
    * Expected: `Show graphically all expenditure by category` and a PieChart on the right of the application
1. View a PieChart of all incomes by categories
   * Examples: `view t/i g/c`
   * Expected: `Show graphically all income by category` and a PieChart on the right of the application

### View entries by month: `view t/ENTRY_TYPE g/GRAPH_TYPE [mo/MONTH]`
1. View a LineGraph of all expenditures in a specified month
   * Examples: `view t/e g/m mo/2022-10`
   * Expected: `Show graphically all expenditure by month` and a LineGraph on the right of the application
1. View a LineGraph of all incomes in a specified month
   * Examples: `view t/i g/m mo/2022-10`
   * Expected: `Show graphically all income by month` and a LineGraph on the right of the application

* View all entries recorded by the user in a month. The month refers to the month that is displayed to the user.
* The `MONTH` field is required for the LineGraph, if no month is specified, the application displays an error.


### Clearing all entries : `clear`

Clears all entries in PennyWise.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

PennyWise data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

PennyWise data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, PennyWise will discard all data and start with an empty data file at the next run.
</div>

---

## FAQ

**Q**: How do I transfer my data to another Computer?
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous AddressBook home folder.

---

## Command summary


| Action              | Format, Examples                                                                                                                                        |
|---------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**             | `add t/ENTRY_TYPE d/DESCRIPTION a/AMOUNT da/DATE c/CATEGORY` <br> e.g., `add t/e d/Lunch a/15.60 da/10-10-2022 c/Food`                                  |
| **Delete**          | `del INDEX_OF_ENTRY t/ENTRY_TYPE` <br> e.g., `del 2 t/e`                                                                                                |
| **Edit**            | `edit INDEX_OF_ENTRY t/ENTRY_TYPE [d/EDITED_DESCRIPTION a/EDITED_AMOUNT da/EDITED_DATE c/EDITED_CATEGORY]`<br> e.g.,`edit 2 t/i a/150.00 da/22-10-2022` |
| **Summary**         | `summary [mo/MONTH]`<br> e.g., `summary mo/2022-09`                                                                                                     |
| **View (Category)** | `view t/ENTRY_TYPE g/GRAPH_TYPE` <br> e.g,, `view t/e g/c`                                                                                              |
| **View (Month)**    | `view t/ENTRY_TYPE g/GRAPH_TYPE [mo/MONTH]` <br> e.g., `view t/i g/m mo/2022-10`                                                                        |
| **Clear**           | `clear`                                                                                                                                                 |
| **Exit**            | `exit`                                                                                                                                                  |
