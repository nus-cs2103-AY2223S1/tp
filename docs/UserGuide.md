# PennyWise User Guide
> :warning: Prerequisite:
> * This guide assumes that the reader is familiar with using [Java](https://www.java.com/)


# Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
   1. [Adding spendings: `add -s  number [DATE]`](#adding-spendings-add--s--number-date)
   2. [Deleting spendings: `del -s index_of_spendings`](#deleting-spendings-del--s-index_of_spendings)
   3. [Adding income: `add -i number [DATE]`](#adding-income-add--i-number-date)
   4. [Deleting income: `del -i number index_of_income`](#deleting-income-del--i-number-index_of_income)
   5. [View total summary of spending: `view -s [MONTH]`](#view-total-summary-of-spending-view--s-month)
   6. [View total summary of income: `view -i [MONTH]`](#view-total-summary-of-income-view--i-month)
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

    - **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact
      named `John Doe` to the Address Book.

    - **`delete`**`3` : Deletes the 3rd contact shown in the current list.

    - **`clear`** : Deletes all contacts.

    - **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- All command follow this format:<br>
  `command | identifier | input | [optional inputs]`<br>
  Example: `add -s 15.60 / 15-08-2022`<br>
    - Command : add<br>
    - Identifier : -s<br>
    - Input : 15.60<br>
    - Optional input : 15-08-2022<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `del -s INDEX_OF_SPENDING`, `INDEX_OF_SPENDING` is a parameter which can be used as `del -s 10`.

- Items in square brackets are optional.<br>
  e.g `add -s NUMBER [DATE]` can be used as `add -s 100 [31-08-2022]` or as `add -s 100`.
</div>


### Adding spendings: `add -s  NUMBER [DATE]`
Adds a spending entry. <br>
1. Add spending with default date <br>
   - Example: `add -s 15.60`<br>
   - A spending of 15.60 will be added with the current date


2. Add spending with specific date<br>
   - Example: add `-s 15.60 / 12-09-2022`<br>
   - A spending of 15.60 will be added with 12 September 2022 as the date

### Deleting spendings: `del -s INDEX_OF_SPENDING`

Deletes a spending entry.

Example: `del -s 2` deletes the 2nd item on the list

Spending list:
1. Spent 15.60 12 Sep 2022
2. Spent 1.20 12 Sep 2022

Expected: `A spending of 1.20 on 12 Sep 2022 has been deleted.`


### Adding income: `add -i NUMBER [DATE]`

Adds an income entry.

1. Add income with default current date
   * Example: `add -i 15.60`
   * An income of $15.60 will be added with the current date
   * Expected: `An income of 15.60 on 27 Sep 2022 has been recorded`
2. Add income with specific date
   * Example: `add -i 15.60 / 12-09-2022`
   * An income of $15.60 will be added with 12 September 2022 as the date
   * Expected: `An income of 15.60 on 12 Sep 2022 has been recorded`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The default date is the current date on your computer!
</div>

### Deleting income: `del -i NUMBER INDEX_OF_INCOME`

Deletes an income entry

Example: `del -i 2` deletes the 2nd item on the list

Spending list:
1. Earned 5.00 12 Sep 2022
2. Earned 30.00 12 Sep 2022

Expected: `An income of 30.00 on 12 Sep 2022 has been deleted.`


### Viewing total summary of spendings : `view -s [MONTH]`

1. View a summary of all spending
   * Examples: `view -s`
   * Expected: A summary of total spending: `Total amount spent: $125.30`
2. View a summary of all spending the specified month
    * Examples: `view -s 09-2022`
    * Expected: A summary of spendings in September 2022: `Total amount spent in September 2022: $37.70`

* View all spending recorded by the user in a month. The month refers to the month that is displayed to the user.
* The `MONTH` field is optional, if no month is specified, the application displays the current month.

### Viewing total summary of income : `view -i [MONTH]`

1. View a summary of all income
    * Examples: `view -i`
    * Expected: A summary of total income earned: `Total amount earned: $125.30`
2. View a summary of all income the specified month
    * Examples: `view -i 09-2022`
    * Expected: A summary of all income in September 2022: `Total amount spent in September 2022: $37.70`

* View all income recorded by the user in a month. The month refers to the month that is displayed to the user.
* The `MONTH` field is optional, if no month is specified, the application displays the current month.


### Summarise financials : `summary [MONTH]`

1. Summarise all financial records
    * Examples: `summary`
    * Expected: A summary of all financials: 
2. Summarise financial records for a specific month
    * Examples: `summary 08-2022`
    * Expected: A summary of all financials for August 2022

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

### Editing of entries `[coming in v1.3]`
### Tagging of Income `[coming in v1.3]`
### Tagging of Spending `[coming in v1.3]`
### Filtering of Income/Spendings `[coming in v1.3]`
### Filtering by Dates `[coming in v1.3]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous AddressBook home folder.

---

## Command summary


| Action     | Format, Examples                                                                                                                                                      |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                               |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**   | `list`                                                                                                                                                                |
| **Help**   | `help`                                                                                                                                                                |
