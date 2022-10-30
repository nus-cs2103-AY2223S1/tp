---
layout: page
title: User Guide
---

bobaBot is a **desktop application** for managing customers’ membership details. It is **optimized for Command Line Interface (CLI) while retaining some benefits of the Graphical User Interface (GUI)**. If you are a cashier working at a bubble tea shop and can type fast, bobaBot can help you easily find and manage your customers’ membership information as compared to other GUI applications.

---

## Table of Contents

1. [Quick start](#quick-start) <br>
1. <details><summary><a href="#4-features">Features</a></summary>
      2.1. <a href="#viewing-help--help">Help</a><br>
      2.2. <a href="#adding-a-customer-add">Add</a><br>
      2.3. <a href="#editing-a-customers-details-edit">Edit</a><br>
      2.4. <a href="#increasing-a-customers-reward-points-incr">Increase</a><br>
      2.5. <a href="#decreasing-a-customers-reward-points-decr">Decrease</a><br>
      2.6. <a href="#listing-all-customers--list">List</a><br>
      2.7. <a href="#locating-customers-by-name-find">Find</a><br>
      2.8. <a href="#deleting-a-customer--delete">Delete</a><br>
      2.9. <a href="#undo-a-command--undo">Undo</a><br>
      2.10. <a href="#redo-an-undocommand--redo">Redo</a><br>
      2.11. <a href="#clearing-all-entries--clear">Clear</a><br>
      2.12. <a href="#calculate-calc">Calculate</a><br>
      2.13. <a href="#gui-calculator-calc-gui">Calculator</a><br>
      2.14. <a href="#exiting-the-program--exit">Exit</a><br>
   </details>
1. [FAQ](#faq) <br>
1. [Command summary](#command-summary) <br>

--------------------------------------------------------------------------------------------------------------------

## 1. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `bobaBot.jar` from [here](https://github.com/AY2223S1-CS2103T-W09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your bobaBot.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all customers.

    * **`add n/John Doe p/98765432 e/johnd@example.com m/1 r/0`** : Adds a customer named `John Doe` to bobaBot.

    * **`delete p/98765432`** : Deletes the customer with the corresponding phone number (aka John Doe).

    * **`clear`** : Deletes all customers.

    * **`exit`** : Exits the app.

1. Scroll down on the Customer list and Promotion list to see all the customers and on-going promotions.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 2. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Command words are CASE_SENSITIVE and should all be in `lower-case`.<br>
  e.g. `add` instead of `ADD`, `Add`, `aDd`,..., using `non lower-case` command words will result in command being not recognised.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/member` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/member`, `t/member t/gold` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### 2.1 Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/screenshots/HelpCommand/HelpCommand.png)

Format: `help`

### 2.2 Adding a Customer: `add`

Adds a Customer to bobaBot.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL m/BIRTHDAY_MONTH r/REWARD [t/TAG]…`



<div markdown="span" class="alert alert-primary">:bulb: Tip:
A customer can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com m/1 r/0 `
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the AddCommand:**

    ![AddCommand1](images/screenshots/AddCommand/AddCommand1.png)

    **After executing the AddCommand:**

    ![AddCommand1Result](images/screenshots/AddCommand/AddCommand1Result.png)
    </details>


* `add n/Charlie Puth p/81234567 e/charlie@puth.com r/3000 t/silver m/12`
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the AddCommand:**

    ![AddCommand](images/screenshots/AddCommand/AddCommand.png)

    **After executing the AddCommand:**

    ![AddCommandResult](images/screenshots/AddCommand/AddCommandResult.png)
    </details>

### 2.3 Editing a Customer’s details: `edit`

Edits an existing Customer in bobaBot.

Format: `edit p/PHONE_NUMBER OR edit e/EMAIL
[n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [r/REWARD] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: Tip:
At least one of the optional fields must be provided
</div>

Examples:
* `edit p/81234567 p/88888888`
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the EditCommand via `PHONE_NUMBER`:**

    ![EditCommandViaPhone](images/screenshots/EditCommand/EditCommand_Phone.png)

    **After executing the EditCommand via `PHONE_NUMBER`:**

    ![EditCommandViaPhoneResult](images/screenshots/EditCommand/EditCommand_PhoneResult.png)
    </details>


* `edit e/charlie@puth.com e/taylor@swift.com`
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the EditCommand via `EMAIL`:**

    ![EditCommandViaEmail](images/screenshots/EditCommand/EditCommand_Email.png)

    **After executing the EditCommand via `EMAIL`:**

    ![EditCommandViaEmail](images/screenshots/EditCommand/EditCommand_EmailResult.png)
    </details>

### 2.4 Increasing a Customer’s Reward points: `incr`

Increases the Reward points of existing Customer in bobaBot.

Format: `incr INCREMENT_VALUE p/PHONE_NUMBER OR incr INCREMENT_VALUE e/EMAIL`

Examples:
* `incr 100 p/88888888`
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the IncreaseCommand via `PHONE_NUMBER`:**

    ![IncreaseCommandViaPhone](images/screenshots/IncreaseAndDecreaseCommand/IncreaseCommandPhone.png)

    **After executing the IncreaseCommand via `PHONE_NUMBER`:**

    ![IncreaseCommandViaPhoneResult](images/screenshots/IncreaseAndDecreaseCommand/IncreaseCommandPhoneResult.png)
    </details>


* `incr 1000 e/alexyeoh@example.com`
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the IncreaseCommand via `EMAIL`:**

    ![IncreaseCommandViaEmail](images/screenshots/IncreaseAndDecreaseCommand/IncreaseCommandEmail.png)

    **After executing the IncreaseCommand via `EMAIL`:**

    ![IncreaseCommandViaEmailResult](images/screenshots/IncreaseAndDecreaseCommand/IncreaseCommandEmailResult.png)
    </details>

### 2.5 Decreasing a Customer’s Reward points: `decr`

Decreases the Reward points of existing Customer in bobaBot.

Format: `decr DECREMENT_VALUE p/PHONE_NUMBER OR decr DECREMENT_VALUE e/EMAIL`

Examples:
* `decr 200 p/87438807`
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the IncreaseCommand via `PHONE_NUMBER`:**

    ![DecreaseCommandViaPhone](images/screenshots/IncreaseAndDecreaseCommand/DecreaseCommandPhone.png)

    **After executing the IncreaseCommand via `PHONE_NUMBER`:**

    ![DecreaseCommandViaPhoneResult](images/screenshots/IncreaseAndDecreaseCommand/DecreaseCommandPhoneResult.png)
    </details>


* `decr 500 e/taylor@swift.com`

    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the IncreaseCommand via `EMAIL`:**

    ![DecreaseCommandViaEmail](images/screenshots/IncreaseAndDecreaseCommand/DecreaseCommandEmail.png)

    **After executing the IncreaseCommand via `EMAIL`:**

    ![DecreaseCommandViaEmailResult](images/screenshots/IncreaseAndDecreaseCommand/DecreaseCommandEmailResult.png)
    </details>

### 2.6 Listing all customers : `list`

Shows a list of all Customers in bobaBot.

Format: `list`

<details>
<summary>
Walk-through with Images
</summary>

**Before executing the ListCommand:**

![ListCommand](images/screenshots/ListCommand/ListCommand.png)

**After executing the ListCommand**

![ListCommandResult](images/screenshots/ListCommand/ListCommandResult.png)
</details>

### 2.7 Locating customers by name: `find`

Finds Customers whose information (including name, phone, email, address) contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Customers matching at least one keyword will be returned (i.e. `OR` search)
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* When searching for names, fuzzy search based on Soundex will be used
  e.g. `Aschcroft` will match `Aschcraft`, similarly `Bob` will match `Bop`
* No need to type in the whole word. e.g `9927` will match `27859927`
* For precise searching, specify the corresponding attribute (phone number or email)
  e.g. `find p/88888888` will only match the customer with phone number `88888888`

Examples:
* `find John` returns `john` and `John Doe`
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the FindCommand:**

    ![FindCommandMultiple](images/screenshots/FindCommand/FindCommandNormal.png)

    **After executing the FindCommand:**

    ![FindCommandMultipleResult](images/screenshots/FindCommand/FindCommandNormalResult.png)
    </details>


* `find alex david` returns `Alex Yeoh`, `David Li`
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the FindCommand (with at least one keyword):**

    ![FindCommandMultiple](images/screenshots/FindCommand/FindCommandMultiple.png)

    **After executing the FindCommand (with at least one keyword):**

    ![FindCommandMultipleResult](images/screenshots/FindCommand/FindCommandMultipleResult.png)
    </details>


* `find Bob` returns `Bob` and `Bop`

    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the FindCommand (with FuzzySearch):**

    ![FindCommandFuzzy](images/screenshots/FindCommand/FindCommand_Fuzzy.png)

    **After executing the FindCommand (with FuzzySearch):**

    ![FindCommandFuzzyResult](images/screenshots/FindCommand/FindCommand_FuzzyResult.png)
    </details>


* `find 8000` returns both `Roy Balakrishnan` and `Bob` 

    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the FindCommand (with matching keywords `8000`):**

    ![FindCommandGeneral](images/screenshots/FindCommand/FindCommand_General.png)

    **After executing the FindCommand (with matching keywords `8000`):**
    * Both `Roy` and `Bob` have `8000` reward points hence they show up in the search.

    ![FindCommandGeneralResult](images/screenshots/FindCommand/FindCommand_GeneralResult.png)
    </details>


* `find p/87438807` returns only `Alex Yeoh` <br>
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the FindCommand via `PHONE_NUMBER`:**

    ![FindCommandPhone](images/screenshots/FindCommand/FindCommand_Phone.png)

    **After executing the FindCommand via `PHONE_NUMBER`:**

    ![FindCommandPhoneResult](images/screenshots/FindCommand/FindCommand_PhoneResult.png)
    </details>


* `find e/charlotte@example.com` returns only `Charlotte Oliveiro` <br>
    <details>
    <summary>
    Walk-through with Images
    </summary>

    **Before executing the FindCommand via `EMAIL`:**

    ![FindCommandEmail](images/screenshots/FindCommand/FindCommand_Email.png)

    **After executing the FindCommand via `EMAIL`:**

    ![FindCommandEmailResult](images/screenshots/FindCommand/FindCommand_EmailResult.png)
    </details>

### 2.8 Deleting a Customer : `delete`

Removes a Customer from bobaBot.

Format:

`delete p/PHONE_NUMBER` or

`delete e/EMAIL`

* Deletes the Customer with the following `PHONE_NUMBER` when `p/` specified.
* Deletes the Customer with the following `EMAIL` when `e/` specified.

Examples:
* `delete p/88888888` removes the Customer with the phone number `88888888`.

    <details>
    <summary>
    Walk-through with Images
    </summary>
  
    **Before executing the DeleteCommand via `PHONE_NUMBER`:**
  
    ![DeleteCommandViaPhone](images/screenshots/DeleteCommand/DeleteCommand_Phone.png)

    **After executing the DeleteCommand via `PHONE_NUMBER`:**

    ![DeleteCommandViaPhoneResult](images/screenshots/DeleteCommand/DeleteCommand_PhoneResult.png)
    </details>


* `delete e/royb@example.com` removes the Customer with the email `royb@example.com`.
  
    <details>
    <summary>
    Walk-through with Images
    </summary>
    
    **Before executing the DeleteCommand via `EMAIL`:**

    ![DeleteCommandViaEmail](images/screenshots/DeleteCommand/DeleteCommand_Email.png)

    **After executing the DeleteCommand via `EMAIL`:**

    ![DeleteCommandViaEmailResult](images/screenshots/DeleteCommand/DeleteCommand_EmailResult.png)
    </details>

### 2.9 Undo a Command : `undo`

Reverts a command that has been executed. Returns bobaBot to the previous state before executing the command.

Format: `undo`

<details>
<summary>
Walk-through with Images
</summary>
    
**Before executing the UndoCommand (after a DeleteCommand):**

![UndoCommand](images/screenshots/UndoAndRedoCommand/UndoCommand.png)

**After executing the UndoCommand (Deleted Customer `Alex Yeoh` is back into bobaBot):**

![UndoCommandResult](images/screenshots/UndoAndRedoCommand/UndoCommandResult.png)
</details>

:exclamation: Note: bobaBot only preserves the 20 most recent state changes (Commands such as `list`, `find`, `help`, `calc` and `exit` will not result in a state change).

### 2.10 Redo an UndoCommand : `redo`

Reverts the UndoCommand. Returns bobaBot to the state before executing the UndoCommand.

Format: `redo`

<details>
<summary>
Walk-through with Images
</summary>

**Before executing the RedoCommand:**

* This continues from the above example in UndoCommand (where we perform an UndoCommand on a DeleteCommand)

![RedoCommand](images/screenshots/UndoAndRedoCommand/RedoCommand.png)

**After executing the RedoCommand (The Customer `Alex Yeoh` is removed from bobaBot again):**

![RedoCommandResult](images/screenshots/UndoAndRedoCommand/RedoCommandResult.png)
</details>

:exclamation: Note: bobaBot only preserves the 20 most recent state changes (Commands such as `list`, `find`, `help`, `calc` and `exit` will not result in a state change).

### 2.11 Clearing all entries : `clear`

Clears all Customers from the bobaBot.

Format: `clear`

<details>
<summary>
Walk-through with Images
</summary>

**Before executing the ClearCommand:**

![ClearCommand](images/screenshots/ClearCommand/ClearCommand.png)

**After executing the ClearCommand:**

![ClearCommandResult](images/screenshots/ClearCommand/ClearCommandResult.png)
</details>

### 2.12 Calculate: `calc`

Do basic arithmetic calculation including +, -, *, /. Allow multiple operators and precedence

Format: `calc {expression}`

Example: `calc 5+2*(4-2)`
<details>
<summary>
Walk-through with Images
</summary>

**Before executing the CalculateCommand:**

![CalculateCommand](images/screenshots/CalculateCommand/NewCalculateCommand.png)

**After executing the CalculateCommand:**

![CalculateCommandResult](images/screenshots/CalculateCommand/NewCalculateCommandResult.png)
</details>

### 2.13 GUI Calculator: `calc-gui`

Launch a GUI calculator. A calculator window will pop-up

Format: `calc-gui`

<details>
<summary>
Walk-through with Images
</summary>

**Before executing the CalculatorGuiCommand:**

![CalculatorGuiCommand](images/screenshots/CalculateCommand/NewCalculatorGui.png)

**After executing the CalculatorGuiCommand:**

![CalculatorGuiCommandResult](images/screenshots/CalculateCommand/NewCalculatorGuiResult.png)
</details>

### 2.14 Exiting the program : `exit`

Exits the program.

Format: `exit`

<details>
<summary>
Walk-through with Images
</summary>

**Before executing the ExitCommand:**

![ExitCommand](images/screenshots/ExitCommand/ExitCommand.png)

**After executing the ExitCommand:**
* bobaBot Application Closed
</details>

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

## 3. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## 4. Command summary

| Action         | Format, Examples                                                                                                                                                                                                                                                                     |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**        | `add n/NAME p/PHONE_NUMBER e/EMAIL m/BIRTHDAY_MONTH r/REWARD [t/TAG]…` <br> e.g., `add n/Betsy Crowe p/1234567 e/betsycrowe@example.com m/1 r/5000 t/gold`                                                                                                                           |
| **Edit**       | `edit p/PHONE_NUMBER [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [m/BIRTHDAY] [r/REWARD] [t/TAG]…` or <br> `edit e/EMAIL [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [m/BIRTHDAY] [r/REWARD] [t/TAG]…` <br> e.g., `edit p/98765432 n/Miles Morales`, `edit e/alexyeoh@example.com r/1000 p/11111111` |
| **Increase**   | `incr 100 p/PHONE_NUMBER` or `incr 100 e/EMAIL` <br> e.g., `incr 100 p/87438807`, `incr 100 e/alexyeoh@example.com`                                                                                                                                                                  |
| **Decrease**   | `decr 100 p/PHONE_NUMBER` or `decr 100 e/EMAIL` <br> e.g., `decr 100 p/87438807`, `decr 100 e/alexyeoh@example.com`                                                                                                                                                                  |
| **Delete**     | `delete p/PHONE_NUMBER` or `delete e/EMAIL` <br> e.g., `delete p/87438807`, `delete e/alexyeoh@example.com`                                                                                                                                                                          |
| **Find**       | `find KEYWORD [MORE_KEYWORDS]` <br> e.g., `find alex david`                                                                                                                                                                                                                          |
| **Undo**       | `undo`                                                                                                                                                                                                                                                                               |
| **Redo**       | `redo`                                                                                                                                                                                                                                                                               |
| **Calculate**  | `calc`                                                                                                                                                                                                                                                                               |
| **Calculator** | `calc-gui`                                                                                                                                                                                                                                                                           |
| **Clear**      | `clear`                                                                                                                                                                                                                                                                              |
| **List**       | `list`                                                                                                                                                                                                                                                                               |
| **Help**       | `help`                                                                                                                                                                                                                                                                               |
| **Exit**       | `exit`                                                                                                                                                                                                                                                                               |
