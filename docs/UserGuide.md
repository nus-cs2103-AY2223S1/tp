---
layout: page
title: User Guide
---

bobaBot is a **desktop application** for managing customers’ membership details. It is **optimized for Command Line Interface (CLI) while retaining some benefits of the Graphical User Interface (GUI)**. If you are a cashier working at a bubble tea shop and can type fast, bobaBot can help you easily find and manage your customers’ membership information as compared to other GUI applications.
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `bobaBot.jar` from [here](https://github.com/AY2223S1-CS2103T-W09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your bobaBot.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all customers.

    * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a customer named `John Doe` to bobaBot.

    * **`delete`**`p/98765432` : Deletes the customer with the corresponding phone number (aka John Doe).

    * **`clear`** : Deletes all customers.

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
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a Customer: `add`

Adds a Customer to bobaBot.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL [r/REWARD] [t/TAG]…`



<div markdown="span" class="alert alert-primary">:bulb: Tip:
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com r/0 t/new `
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com p/1234567 r/5000 t/gold`

### Editing a Customer’s details: `edit`

Edits an existing Customer in bobaBot.

Format: `edit n/NAME OR edit p/PHONE_NUMBER OR edit e/EMAIL
[n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/REWARD] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: Tip:
At least one of the optional fields must be provided
</div>

Examples:
* `edit n/John Doe p/91234567 e/johndoe@example.com r/1000`
* `edit n/Peter Parker r/420`

### Listing all customers : `list`

Shows a list of all Customers in bobaBot.

Format: `list`

### Locating persons by name: `find`

Finds Customers whose information (including name, phone, email, address) contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Customers matching at least one keyword will be returned (i.e. `OR` search)
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* No need to type in the whole word. e.g `9927` will match `27859927`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a Customer : `delete`

Removes a Customer from bobaBot.

Format:

`delete p/PHONE_NUMBER` or

`delete e/EMAIL`

* Deletes the Customer with the following `PHONE_NUMBER` when `p/` specified.
* Deletes the Customer with the following `EMAIL` when `e/` specified.

Examples:
* `delete p/87438807` removes the Customer with the phone number `87438807`.
* `delete e/alexyeoh@example.com` removes the Customer with the email `alexyeoh@example.com`.

### Clearing all entries : `clear`

Clears all Customers from the bobaBot.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

bobaBot data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

bobaBot data are saved as a JSON file `[JAR file location]/data/bobaBot.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, bobaBot will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                     |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL [r/REWARD] [t/TAG]…` <br> e.g., ` add n/Betsy Crowe t/friend e/betsycrowe@example.com p/1234567 r/5000 t/gold`                                    |
| **Edit**   | `edit n/NAME` or `edit p/PHONE_NUMBER` or `edit e/EMAIL [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/REWARD] [t/TAG]…​`<br> e.g.,`edit n/John Doe p/91234567 e/johndoe@example.com r/1000` |
| **Delete** | `delete p/PHONE_NUMBER` or `delete e/EMAIL` <br> e.g., `delete p/87438807`, `delete e/alexyeoh@example.com`                                                                          |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find alex david`                                                                                                                           |
| **Clear**  | `clear`                                                                                                                                                                              |
| **List**   | `list`                                                                                                                                                                               |
| **Help**   | `help`                                                                                                                                                                               |
| **Exit**   | `exit`                                                                                                                                                                               |

