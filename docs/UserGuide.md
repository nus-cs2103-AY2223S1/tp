---
layout: page
title: User Guide
---

FinBook is a desktop app for managing clients, optimized for use via a Command Line Interface (CLI) while still having
the benefits of a Graphical User Interface (GUI). If you can type fast, FinBook can get your contact management tasks
done faster and more securely than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

- [Quick Start](#quick-start)
- [Features](#features)
    * [Adding a client: `add`](#adding-a-client-add)
    * [Editing a client: `edit`](#editing-a-client--edit)
    * [Deleting a client: `delete`](#deleting-a-client--delete)
    * [Listing all clients: `list`](#listing-all-clients-list)
    * [Exiting the application: `exit`](#exiting-the-application--exit)
    * [Saving the data](#saving-the-data)
    * [Importing data: `import`](#importing-data--import)
    * [Exporting data: `export`](#exporting-data--export)
    * [Locking the application: `lock`](#locking-the-application--lock)
    * [Changing Light/Dark mode](#changing-lightdark-mode)

- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `finbook.jar` from [here]().

1. Copy the file to the folder you want to use as the home folder for your FinBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact
      named `John Doe` to the FinBook.

    * **`delete`**` 3` : Deletes the 3rd contact shown in the current list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

---

### Adding a client: `add`

Adds a client to the FinBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS i/MONTHLY_INCOME m/UPCOMING_MEETING_DATES
t/TAGS r/RISK_LEVEL pl/CURRENT_PLANS​`
​`

Examples:

* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 i/$100000 m/12-Jan-2022 t/VIPClient
  r/high pl/Prudential Health`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/ABC street p/1234567 i/$10 m/23-Feb-2022 r/low
  pl/NTUC Income Plan`

---

### Listing all client : `list`

Shows a list of all clients in the FinBook.

Format: `list`

---

### Editing a client : `edit`

Edits an existing client in the FinBook.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/MONTHLY_INCOME] [m/UPCOMING_MEETING_DATES]
[t/TAGS] [r/RISK_LEVEL] [pl/CURRENT_PLANS]​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list.
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567`
  and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower` Edits the name of the 2nd client to be `Betsy Crower`.

---

### Deleting a client : `delete`

Four formats of deleting a client.

1. `delete INDEX`
2. `delete INDEX1, INDEX2, …`
3. `delete STARTINDEX - ENDINDEX`
4. `delete all`

* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

#### Deleting a single client

Deletes the specified client from the FinBook.

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.

Examples:

* `list` followed by `delete 2` deletes the 2nd client in the FinBook.
* `find Betsy` followed by `delete 1` deletes the 1st client in the results of the `find` command.

#### Deleting multiple clients

Deletes multiple specified clients from the FinBook.

Format: `delete INDEX1, INDEX2, …`

* Deletes the client at the specified `INDEX1`, `INDEX2`, and so on.
* Value of `INDEX1`, `INDEX2`, … cannot contain any repeated values.

Examples:

* `list` followed by `delete 1, 2, 5` deletes the 1st, 2nd and 5th client in the FinBook.

Deletes a range of clients from the FinBook.

Format: `delete STARTINDEDX - ENDINDEX`

* Deletes the client from `STARTINDEX` to `ENDINDEX` inclusive.
* Value of`STARTINDEX` must be smaller than `ENDINDEX`.

Examples:

* `list` followed by `delete 1-3` deletes the 1st, 2nd and 3rd client in the FinBook.

Deletes all clients from the FinBook.

Format: `delete all`

* Deletes all clients in FinBook.

Examples:

* `list` followed by `delete all` deletes the all clients in the FinBook.

---

### Listing all clients: `list`

Show a list of all clients in the FinBook

Format: `list`

---

### Exiting the application : `exit`

Exits the application.

Format: `exit`

---

### Saving the data

Financial book data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

---

### Editing the data file

Financial book data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to
update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FinBook will discard all data and start with an empty data file at the next run.
</div>

---

### Importing data : `import`

Imports data from a `JSON` or `CSV` file

* `JSON` files must be saved by the latest version of FinBook
* `CSV` files must be formatted correctly as follows:
    * The first line of the file must contain these headers in any order:
        * `name`
        * `phone`
        * `email`
        * `address`
        * `income`
        * `meeting date`
        * `tags`
        * `risk`
        * `plans`

        * The data in each corresponding column must be valid
            * `tags` must be separated by commas `,` without spaces (e.g. `colleagues,friends,neighbours`)

Format: `import PATH`

* Imports data from the file at the specified `PATH`
* `PATH` can be a relative or full path
* `PATH` must end in `.json` or `.csv`

Examples:

* `import ./data.json` imports data from the file `data.json` which is located in the same directory as the FinBook executable
* `import ../data.csv` imports data from the file `data.csv` which is located one level outside the directory of the FinBook executable

---

### Exporting data : `export`

Exports data to a `CSV` file

Format: `export PATH`

* Exports data to the file at the specified `PATH`
* `PATH` can be a relative or full path
* `PATH` must end in `.csv`

Examples:

* `export ./data.csv` exports data to the file `data.csv` which is located in the same directory as the FinBook executable
* `export ../data.csv` exports data to the file `data.csv` which is located one level outside the directory of the FinBook executable

---

### Locking the application : `lock`

Locks the application. If a password has not been set, leave the password field empty to unlock the application.

Format: `lock`

---

### Changing Light/Dark mode

Toggle the UI of FinBook by clicking on the `sun` or `moon` icon on the top right. 
* If icon is a `sun` FinBook is in Light mode. 
* If icon is a `moon` FinBook is in Dark mode.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous FinBook home folder. Alternatively, you may use the `import` command.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action               | Format, Examples                                                                                                                                                                                                                                                                          |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**              | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS i/MONTHLY_INCOME m/UPCOMING_MEETING_DATES t/TAGS r/RISK_LEVEL pl/CURRENT_PLANS​` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 i/$100000 m/12-Jan-2022 t/VIPClient r/high pl/Prudential Health` |
| **Delete**           | `delete INDEX` `delete INDEX1, INDEX2, …` `delete STARTINDEX - ENDINDEX` `delete all` <br> e.g., `delete 3` `delete 1, 2, 5` `delete 2-5`                                                                                                                                                 |
| **Edit**             | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG] [r/RISK_LEVEL] [pl/CURRENT_PLANS]​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                                                              |
| **List**             | `list`                                                                                                                                                                                                                                                                                    |
| **Exit application** | `exit`                                                                                                                                                                                                                                                                                    |
| **Import**           | `import PATH`<br> e.g., `import ./data.json`                                                                                                                                                                                                                                              |
| **Export**           | `export PATH`<br> e.g., `export ./data.csv`                                                                                                                                                                                                                                               |
| **Lock application** | `lock`                                                                                                                                                                                                                                                                                    |
