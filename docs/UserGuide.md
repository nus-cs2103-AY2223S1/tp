---
layout: page
title: User Guide
---

Welcome to the PayMeLah user guide!

Are you a student who enjoys participating in group buys? Or perhaps do you just need some way to remember how much money your friends owe you? PayMeLah is a desktop app for **keeping track of** and **managing the debts** your friends owe you. It can also help **do simple calculations** for you, such as including GST or splitting debts amongst your friends. What’s more, it is optimised for you to do everything from just your keyboard!

<div style="page-break-after: always;"></div>

### Using this guide
If you are a new user looking to get started with PayMeLah, this user guide can help you with [the installation process](#quick-start), guide you through [adding your first debt to PayMeLah](#tutorial-adding-your-first-debt), and familiarise you with how to use all of [PayMeLah’s features](#features).

If you are already familiar with the basic features of PayMeLah, this guide can also provide you with tips and tricks to get the most out of PayMeLah!

Here are some of the symbols and text formatting to look out for as you make use of this guide:

| Symbol | Meaning |
| :----: | ------- |
| :information_source: | Essential Information for All Users |
| :bulb: | Tips for Intermediate Users |
| :star: | Tips for Advanced Users |
| :exclamation: | Warning |

|          Text Formatting           | Meaning |
|:----------------------------------:| ------- |
| [Hyperlink to Glossary](#glossary) | Clickable hyperlink to navigate to another section |
|               `Text`               | Text relevant to PayMeLah user commands |
|          <kbd>Ctrl</kbd>           | A keyboard key |

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick start
### Tutorial: Installing PayMeLah

1. To run the PayMeLah application, you will need to have *Java 11* or above [installed](https://www.baeldung.com/java-check-is-installed) in your Computer ([Mac](https://www.geeksforgeeks.org/how-to-install-java-on-macos/), [Windows](https://phoenixnap.com/kb/install-java-windows)).

1. Next, you will need to prepare an [empty folder](https://www.wikihow.com/Make-a-New-Folder-on-a-Computer) to download PayMeLah to.

1. Now, you will need to visit [this link](https://github.com/AY2223S1-CS2103T-W13-3/tp/releases) to click and download the latest version of *paymelah.jar*. Make sure to download it to the folder you created in step 2! <br>
   <img src="images/howToDownload_1.png" width="500" /> <br>
   <img src="images/howToDownload_2.png" width="500" /> <br>

1. Finally, you are ready to use PayMeLah. Simply double-click the *paymelah.jar* in the folder you created to start the app. After a few seconds, you should see PayMeLah appear. Notice how we have some sample persons and debts present for you to experiment with our [features](#features).  <br>
   <img src="images/Ui.png" width="800" /> <br>

1. Now, let’s continue to learn how to [navigate the application](#tutorial-navigating-the-application-interface) before familiarising ourselves with the [features](#features) you can use in PayMeLah.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Tutorial: Navigating the application interface

* PayMeLah uses a Command Line Interface (CLI) - which means that you perform actions by entering commands for PayMeLah to carry out.
* Once a command has been successfully carried out, PayMeLah will display the results accordingly.
* Refer to the diagram below to find out about the different components of PayMeLah that are responsible for receiving commands, displaying results, and more.

| Number | Component Name | Details                                                                                                                                               |
|:------:|----------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
|   1.   | Command Box    | The box where you enter commands for PayMeLah to carry out.                                                                                           | 
|   2.   | Message Box    | The box where a success message will be displayed when PayMeLah carries out a command successfully, or an error message when PayMeLah fails to do so. |
|   3.   | Person Card    | The card containing personal details.                                                                                                                 | 
|   4.   | Debt List      | The box containing the list of debts owed by a person.                                                                                                | 
|   5.   | Person List    | The box containing the full list of person cards. Only one person card can be viewed at a time.                                                       |

<img src="images/UiDetails.png" width="800" /> <br>

| Number | Component Name  | Details                                       |
|:------:|-----------------|-----------------------------------------------|
|   1.   | Person Index    | The index of the person in the person list.   | 
|   2.   | Name            | The person’s name.                            |
|   3.   | Phone Number    | The person’s phone number.                    | 
|   4.   | Telegram Handle | The person’s Telegram handle.                 | 
|   5.   | Tags            | The tags attached to the person.              | 
|   6.   | Address         | The person’s address.                         | 
|   7.   | Total Debt      | The total amount of money owed by the person. | 

<img src="images/PersonCard.png" width="800" /> <br>

| Number | Component Name | Details                                     |
|:------:|----------------|---------------------------------------------|
|   1.   | Debt Index     | The index of the debt in the debt list.     | 
|   2.   | Description    | The debt’s description.                     |
|   3.   | Money          | The amount of money owed for the debt.      |
|   4.   | Date/Time      | The date and time of the debt.              |
|   5.   | Payment Status | The debt’s payment status (paid or unpaid). |

<img src="images/DebtList.png" width="800" /> <br>

<div style="page-break-after: always;"></div>

<div id="advanced-keyboard-shortcuts" markdown="block" class="alert alert-secondary">
**:star: Advanced keyboard shortcuts:**
Although you can make use of the mouse to perform some functions in PayMeLah, you may also use various keyboard shortcuts to do so.

</div>

|                Keyboard Key                 | Function                                                                                                                                                                                                                                                                                                                                              |
|:-------------------------------------------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|               <kbd>Tab</kbd>                | When you are in the command box, press <kbd>Tab</kbd> twice to navigate to the person list. When you are in the person list, use <kbd>Tab</kbd> to move down the list. At the end of the person list, press <kbd>Tab</kbd> once to return to the command box.                                                                                         |
|      <kbd>Shift</kbd> + <kbd>Tab</kbd>      | Same as <kbd>Tab</kbd> but moves up instead.                                                                                                                                                                                                                                                                                                          |
|              <kbd>Space</kbd>               | When you are in the person list, use <kbd>Space</kbd> to expand or close the person card you are currently on. After expanding the person card, press <kbd>Tab</kbd> followed by <kbd>Space</kbd> to navigate to the person’s debt list. Once you are done viewing the person's debt list, press <kbd>Tab</kbd> again to navigate to the person list. |
|  <kbd>PageUp</kbd> and <kbd>PageDown</kbd>  | When you are in the person list, use <kbd>PageUp</kbd> and <kbd>PageDown</kbd> to move up or down the list, automatically expanding each person card as you move. When you are in a person's debt list, use <kbd>PageUp</kbd> and <kbd>PageDown</kbd> to move up or down the debt list.                                                               |
| <kbd>UpArrow</kbd> and <kbd>DownArrow</kbd> | Similar to <kbd>PageUp</kbd> and <kbd>PageDown</kbd>, but without expanding the person cards.                                                                                                                                                                                                                                                         |
|               <kbd>Home</kbd>               | When you are in the person list, press the <kbd>Home</kbd> key to jump to the first person in the list. When you are in a person's debt list, press the <kbd>Home</kbd> key to jump to the first debt in the list.                                                                                                                                    |
|               <kbd>End</kbd>                | When you are in the person list, press the <kbd>End</kbd> key to jump to the last person in the list. When you are in a person's debt list, press the <kbd>End</kbd> key to jump to the last debt in the list.                                                                                                                                        |

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Tutorial: Adding your first debt

Once you have familiarised yourself with PayMeLah’s [user interface](#tutorial-navigating-the-application), it is time to officially start using PayMeLah! The following section provides a step-by-step guide that walks you through the process of adding a debt to PayMeLah. You can choose to follow the sample commands word-for-word, or if you are feeling adventurous, you can try replacing the sample inputs with your own inputs instead.

<div markdown="block" class="alert alert-secondary">
**:star: Using the keyboard instead of the mouse**

As this section is meant for new users, it will not cover how to navigate the application using keyboard shortcuts to avoid overloading new users with information. However, PayMeLah is still ultimately designed to be the fastest for users who prefer keyboard shortcuts - If this describes you, do refer to the section on [advanced keyboard shortcuts](#advanced-keyboard-shortcuts) to learn more.

</div>

1. If you are a first time user, your PayMeLah may still be filled with the sample data that came with the [installation process](#tutorial-installing-paymelah). You can remove all the sample data with a single [`clear` command](#clearing-debts-clear), which you can do by simply entering `clear`. Don’t worry about losing this data - they are unlikely to be useful to you! Soon, your PayMeLah
   will be filled with the information you actually want instead.

1. You should see that the person list is now empty.
   ![Empty person list](images/EmptyPersonList.png)

1. You can now add your first person to PayMeLah with the [`add` command](#adding-a-person-add). The example we will use in this section is `add n/Ryan Tan tele/ryantan123`. Entering this command will add a person named `Ryan Tan` with `ryantan123` as his Telegram handle into PayMeLah.

1. You should see that the person card for `Ryan Tan` is now visible in the person list. You can click on his name to expand his person card, as per the picture below. However, he does not have any debts associated with him just yet!
   ![Ryan Tan list](images/RyanTanList.png)

1. You are now ready to add a debt to `Ryan Tan` using the [`adddebt` command](#adding-a-debt-adddebt)! This time, the example we will use is `adddebt 1 d/mcdonalds m/9.80`. Entering this command will add a debt of `$9.80` with the description `mcdonalds` to the 1st person in the person list, who happens to be `Ryan Tan`.

1. The person card for `Ryan Tan` should now be updated. Congratulations! You have just added your first debt to PayMeLah!
   ![YourFirstDebt](images/YourFirstDebt.png)

Of course, this is not yet the end of your journey with PayMeLah - there are still several other commands you may require while using PayMeLah, including commands such as `deletedebt` and `find`. You can find out more about these commands in the [features](#features) section below.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* The first word in the command is the command phrase that specifies which command will be carried out by PayMeLah.
  e.g. in `add n/<name>`, `add` is the command phrase for PayMeLah to add a person.

* Words in diamond brackets `<>` are inputs to be supplied by you.<br>
  e.g. in `add n/<name>`, `<name>` is an input which can be used as `add n/John`.

* To separate inputs that represent different information, you should precede ambiguous inputs with their paired prefixes that end with `/`. <br>
  e.g. in `add n/<name> [t/<tag>]…`, `n/` and `t/` are prefixes preceding the inputs `<name>` and `<tag>` respectively.

* Inputs in square brackets `[]` are optional.<br>
  e.g. for `add n/<name> [t/<tag>]…`, the following usages are both acceptable: `add n/Alan Poe t/theatre kid`, `add n/Alan Poe`.

* Inputs with … can be used multiple times, but remember to separate each usage with a space character in between.<br>
  e.g. in `adddebt <person index…>`, `<person index…>` is an input which can be used as `adddebt 1` or as `adddebt 1 2`.<br>
  e.g. in `add n/<name> [t/<tag>]…`, `[t/<tag>]…` is a pair of prefix and input which can be used as `add n/Alan t/Poet` or as `add n/Alan t/Poet t/Friend`.

<div markdown="block" class="alert alert-warning">:exclamation: **Caution:**
Be very careful with how a command is formatted! If the `…` is found within the diamond brackets `<>` that correspond to an input as in `<person index…>`, then only the input itself is to be repeated. However, if the `…` is found outside the `<>` as in `[t/<tag>]…`, then both the prefix and input must be repeated.
</div>

* Inputs can be in any order.<br>
  e.g. if the command specifies `d/<description> m/<money>`, `m/<money> d/<description>` is also acceptable.

* If an input is expected only once in the command, but you specified it multiple times, only the last occurrence of the input will be taken.<br>
  e.g. if the command requires `p/<phone number>` and you specify `p/12341234 p/56785678`, only `p/56785678` will be taken. This is useful for correcting a wrong input without having to use backspace.

* Any inputs for commands that do not accept inputs will be ignored.<br>
  e.g. entering `help 123` will be equivalent to entering `help`, as it does not accept any inputs.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Avoid giving irrelevant inputs to commands that do not accept those inputs. For example, `mark` expects a `<person index>` input and a `debt/<debt index…>` input, but not a `t/<tag>` input. Giving such inputs may cause unexpected behaviour in PayMeLah!
</div>

</div>

<div style="page-break-after: always;"></div>

#### Input-specific behaviour
<div markdown="block" class="alert alert-info">

* Whenever `<date>` is specified as an input, you should input it in the format `yyyy-mm-dd` where `y` is year, `m` is month and `d` is day.<br>
  e.g. September 5 2022 should be input as `2022-09-05`.

* Whenever `<time>` is specified as an input, you should input it in the format `hh:mm` where `h` is the hour in 24h clock format, and `m` is the minute.<br>
  e.g. 5:15PM should be input as `17:15` as per 24h clock notation.

* Whenever `<money>` is specified as an input, you should input the amount in dollars and cents. You can also let PayMeLah help you with calculations by ending with '+' to add GST, or '++' to add both Service Charge and GST to the amount specified. **All calculated values are automatically rounded up to the nearest cent.**<br>
  e.g. when you input `2.00++`, PayMeLah will store a debt with a money amount that has Service Charge and GST added, i.e. `2.36`.

</div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Features for General Utility

#### Viewing help: `help`

Using `help` will provide you with a link to our online user guide.<br>
If you get lost while using PayMeLah, this is the one command to remember!

![help message](images/helpMessage.png)

Format: `help`

<div markdown="block" class="alert alert-secondary">
**:star: Advanced Tip:**
Pressing <kbd> F1 </kbd> will also open up the help message.
</div>

#### Undoing a command: `undo`

This is the command to use when you make a mistake, and want to undo previous command(s) that modified PayMeLah's data. After you undo a command, PayMeLah will automatically show the full list of persons for you to check whether the correct changes have been reverted. Be careful though, your undo history will be gone when you close the app!

Format: `undo`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This command will only undo commands that directly modified PayMeLah's data contents (e.g., `adddebt`, `delete`, `sort`). It does not undo commands that only change the display (e.g., `listdebtors`, `find`). To view all the persons in PayMeLah, use the `list` command instead.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
The undo history of PayMeLah will only save your 10 most recent commands that modified its data! Make sure to check the list of persons regularly if you are making many changes in one session!
</div>

#### Clearing all entries: `clear`

Clears all entries from PayMeLah.<br>
You can use this command to delete all info from PayMeLah and start afresh!

Format: `clear`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This is the command to use when you want to start using PayMeLah for real and delete all the sample data that exist when you first use PayMeLah!
</div>

#### Exiting the program: `exit`

Exits the program.

Format: `exit`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can also just press the 'X' button in the upper right corner. There is no difference, and both methods help safely exit PayMeLah :)
</div>

### Features for Managing Persons

#### Adding a person: `add`

Adds a person to PayMeLah. <br>
This command will let you add the people who owe you money (and some of their information, like their phone number or Telegram handle) to PayMeLah.

Format: `add n/<name> [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag>]…`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0).
</div>

* You can only have 1 person of a certain `name`. This means you *cannot* store 2 `Isaac Lee`s with different phone numbers in PayMeLah.
* However, `name` is case-sensitive. If you have 2 friends of the same name and would prefer to store both of them under their actual name, you *can* store an `Isaac Lee` and an `isaac lee`.

Examples:
* `add n/John Doe p/98765432 tele/johndoe a/John street, block 123, #01-01` will add a person named `John Doe` with `98765432` as his phone number, `johndoe` as their Telegram handle and `John street, block 123, #01-01` as their address to PayMeLah.
* `add n/Betsy Crowe t/friend a/Newgate Prison t/criminal` will add a person named `Betsy Crowe` with `Newgate Prison` as their address and both `friend` and `criminal` as their tags to PayMeLah.

#### Editing a person: `edit`

Edits an existing person in PayMeLah.<br>
You can use this command to edit information about people you have already added in PayMeLah, in case some of their particulars such as their phone number or Telegram handle have changed.

Format: `edit <index> [n/<name>] [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag>]…`

* Edits the person at the specified `<index>`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.
* Cannot be used to modify a person's debts

Examples:
*  `edit 1 p/91234567 tele/johndoe` will edit the phone number and Telegram handle of the 1st person to be `91234567` and `@johndoe` respectively.
*  `edit 2 n/Betsy Crower t/` will edit the name of the 2nd person to be `Betsy Crower` and clear all their existing tags.


#### Deleting a person: `delete`

Deletes the specified person from PayMeLah.<br>
You can use this to remove people you no longer need to track debts for from PayMeLah.

Format: `delete <index>`

* Deletes the person at the specified `<index>`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find n/Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Features for Managing Debts

#### Adding a debt: `adddebt`

Adds a debt to a person in PayMeLah for you to track. Specifying multiple people will add a copy of this debt to each person specified.<br>
This command will help you add debts to the people in PayMeLah, so that the app can help you to remember all the debts you are owed instead.

Format: `adddebt <person index…> d/<description> m/<money> [date/<date>] [time/<time>]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can tell PayMeLah to add Service Charge and GST to the amount of money specified by including `++` at the back of the amount. A single `+` will add only GST instead.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You might find it difficult to find the index of a specific person when your list gets long. In this situation, you may want to make use of the [`find` command](#locating-persons-by-fields-find) or [`finddebt` command](#locating-persons-by-debt-description-finddebt) to shorten the list and make it easier to find and figure out the index of the person that you are looking for.
</div>

* If you specify **neither date nor time**, the date and time will conveniently default to the current date and time.
* If you specify **only the time but not the date**, the date will conveniently default to the current date.
* If you specify **only the date but not the time**, the time will default to midnight.

<div markdown="block" class="alert alert-warning">:exclamation: **Caution:**
Be very careful! The default behaviour is slightly different across the different combinations of whether you provided date and time inputs.
</div>

* One person **cannot** have 2 debts with the same description, money, date and time. However, they **can** have 2 debts with 3 out of 4 of description, money, date and time being the same.

Example:
* `adddebt 3 d/McDonalds m/8.9` will add a debt with the current date and time to the 3rd person in the person list. This debt is worth `$8.90` and has the description`McDonalds`
* `adddebt 1 4 d/chicken rice m/10++ date/2022-10-12 time/13:00` will add debts with `2022-10-12` and `13:00` as the date and time respectively to **both** the 1st person and 4th person. These debts require Service Charge and GST to be added to an initial price of `$10`, and will be recorded with the description `chicken rice`. Note that PayMeLah will automatically calculate the money for both debts and display the amounts as `$11.77`.

#### Splitting a debt: `splitdebt`

Splits a debt among several people in PayMeLah for you to track. <br>  
This command will help you with the Maths of dividing shared costs equally among people in PayMeLah. Sharing is made easy as PayMeLah does the Maths for you!

Format: `splitdebt <person index…> d/<description> m/<money> [date/<date>] [time/<time>]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can split a debt among as many people as you want. You can even include yourself with index '0'. But you cannot split a debt between just yourself.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You might find it difficult to find the index of a specific person when your list gets long. In this situation, you may want to make use of the [`find` command](#locating-persons-by-fields-find) or [`finddebt` command](#locating-persons-by-debt-description-finddebt) to shorten the list and make it easier to find and figure out the index of the person that you are looking for.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Splitting a debt is just like [adding a debt](#adding-a-debt-adddebt) to multiple people; however, here we divide the money of the debt over the people who shared it (and round up to the closest cent). Thus, you can similarly tell PayMeLah to add Service Charge and GST to the amount of money specified by including `++` at the back of the amount. A single `+` will add only GST instead.
</div>


* If you specify **neither date nor time**, the date and time will conveniently default to the current date and time.
* If you specify **only the time but not the date**, the date will conveniently default to the current date.
* If you specify **only the date but not the time**, the time will default to midnight.

<div markdown="block" class="alert alert-warning">:exclamation: **Caution:**
Be very careful! The default behaviour is slightly different across the different combinations of whether you provided date and time inputs.
</div>

* One person **cannot** have 2 debts with the same description, money, date and time. However, they **can** have 2 debts with 3 out of 4 of these items being the same.

Examples:
* `splitdebt 1 2 d/Pizza m/33.99` will add debts with the current date and time to the 1st and 2nd person in the person list. This debt has the description `Pizza`and is worth `$33.99` in total before being divided by 2, over the 1st person and the 2nd person. Note that PayMeLah will automatically calculate the money for both debts and display the amounts as `$17.00`.
* `splitdebt 0 2 5 d/KFC chicken bucket m/30+ date/2022-10-12` will add debts with `2022-10-12` and with the default `00:00` as the date and time respectively to **both** the 2nd person and 5th person. These debts will be recorded with the description `KFC chicken bucket` and require GST to be added to an initial total price of `$30` before dividing the costs by 3, over yourself, the 2nd person and the 5th person. Note that PayMeLah will automatically calculate the money for both debts and display the amounts as `$10.70`.

#### Deleting a debt: `deletedebt`

Deletes the specified debts from a person in PayMeLah. Specifying multiple debts will delete those debts from the person specified. <br>
This command will help you fully remove debts from people in PayMeLah, so that their current debts can be better organised.

Format: `deletedebt <person index> debt/<debt index…>`

Example:
* `deletedebt 2 debt/2 3`

#### Clearing debts: `cleardebts`

Clears all of a debtor's debts from PayMeLah. <br>
You can use this command to delete all of a person’s debts and your relationship can start afresh!

Format: `cleardebts <person index>`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You might find it difficult to find the index of a specific person when your list gets long. In this situation, you may want to make use of the [`find` command](#locating-persons-by-fields-find) or [`finddebt` command](#locating-persons-by-debt-description-finddebt) to shorten the list and make it easier to find and figure out the index of the person that you are looking for.
</div>


Example:
* `cleardebts 3` will delete all the debts, paid or unpaid, from the 3rd person in the current person list.

#### Marking debts as paid: `mark`

Marks the debts specified from a person in PayMeLah as paid. Specifying multiple debts will mark all those debts as paid. <br>
This command will help you keep track of which debts have been paid and which debts have not been paid.

Format: `mark <person index> debt/<debt index…>`

Example:
* `mark 2 debt/2 3`

#### Marking debts as unpaid: `unmark`

Marks the debts specified from a person in PayMeLah as unpaid. Specifying multiple debts will mark all those debts as unpaid. <br>
This command will help you undo any mistake you made when marking a debt as paid.

Format: `unmark <person index> debt/<debt index…>`

Example:
* `unmark 2 debt/2 3`

#### Getting the statement: `statement`

Gets a statement of the total sum of debts you are owed from everyone in the displayed person list. <br>
This command will save you the time and effort of manually summing up debts in PayMeLah.

Format: `statement`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**

The statement will only sum up the debts that are not marked as paid from the list that is currently displayed. This means that if you have shortened the list using the [`find` command](#locating-persons-by-fields-find) or [`finddebt` command](#locating-persons-by-debt-description-finddebt), the statement will only sum up the debts that are not marked as paid from the shortened list.

</div>

Example: `statement` returns `You are owed $583.90 in total.`

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Features for Searching

#### Finding persons by fields: `find`

Finds persons who match all the given conditions.<br>
This is one of the most powerful tools available to look through your person list, as you can search by almost anything.

Format: `find [n/<name>] [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag>…]
[d/<description>…] [m/<money>…] [above/<money>] [below/<money>]
[date/<date>…] [before/<date>] [after/<date>] [time/<time>…]`

Here is a summary of the inputs this command accepts:

| Input              | Description                                                                                                                                                                                                                                                                                                                                                                                                                                       | Examples                                                                                                                                                                                                                                                                                                                        |
|--------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `n/<name>`         | Looks for people who have names containing `<name>`                                                                                                                                                                                                                                                                                                                                                                                               | `find n/rob` will find people named `Robin Jones` and `Grobert Smith`                                                                                                                                                                                                                                                           |
| `p/<phone number>` | Looks for people who have the phone number `<phone number>`                                                                                                                                                                                                                                                                                                                                                                                       | `find p/91234567` will find people with the exact phone number `91234567`, but not `891234567` or `912345678`                                                                                                                                                                                                                   |
| `tele/<telegram>`  | Looks for people by the Telegram handle `<telegram>`                                                                                                                                                                                                                                                                                                                                                                                              | Either `find tele/juliaw` or `find tele/@juliaw` will find people with the exact Telegram handle `@juliawatson`, but not `@juliawatson` or `@alicejuliaw`                                                                                                                                                                       |
| `a/<address>`      | Looks for people who have addresses containing `<address>`                                                                                                                                                                                                                                                                                                                                                                                        | `find a/main` will find people living at `123 Main St` or `456 Main Ave`                                                                                                                                                                                                                                                        |
| `t/<tag>`          | Looks for people who have the tag `<tag>`                                                                                                                                                                                                                                                                                                                                                                                                         | `find t/buddy t/roommate` will find people with _both_ the `buddy` and `roommate` tags, but not people with `best buddy` or `RoomMate` tags (unless they also have both the `buddy` and `roommate` tags)                                                                                                                        |
| `d/<description>`  | Looks for people who owe money for a debt described by `<description>`                                                                                                                                                                                                                                                                                                                                                                            | `find d/McDonald's` will find people who owe money for `McDonald's`, but not `mcdonalds` or `McDonald's burger` (unless they also owe for `McDonald's`)                                                                                                                                                                         |
| `m/<money>`        | Looks for people who owe money for a debt with the monetary value `<money>`                                                                                                                                                                                                                                                                                                                                                                       | `find m/10` will find people with a debt in their debt list worth exactly `$10.00`, but not `$10.50` or `$0.10`                                                                                                                                                                                                                 |
| `above/<money>`    | Looks for people who owe money for a debt above or equal to the monetary value `<money>`; can be used with `below/<money>` for more precise searches                                                                                                                                                                                                                                                                                              | `find above/11` will find people with a debt in their debt list worth exactly `$11.00`, `$11.01`, or `$259.23`, but not `$10.00` or `$10.99`<br>`find above/11 below/20` will find people with a debt in their debt list worth exactly `$11.00`, `$20.00`, or `$15.49`, but not `$100.00` or `$10.00`                           |
| `below/<money>`    | Looks for people who owe money for a debt below or equal to the monetary value `<money>`; can be used with `above/<money>` for more precise searches                                                                                                                                                                                                                                                                                              | `find below/9` will find people with a debt in their debt list worth exactly `$9.00` or `$0.01`, but not `$9.01`<br>For combined usage with `above/<money>`, see the above row                                                                                                                                                  |
| `date/<date>`      | Looks for people who owe money for a debt on the given `<date>`; using this with `time/<time>` does _not_ look for a debt at the given date and time, but rather looks for people with both a debt that falls on the given `<date>`, and a debt at the given `<time>` (which may or may not be the same as the previous debt; see the [details on combining inputs](#combining-inputs-to-find) below)                                             | `find d/2022-10-31` will find people with a debt in their debt list on `2022-10-31`, but not `2023-10-31`, `2022-12-31`, or `2022-10-30`                                                                                                                                                                                        |
| `before/<date>`    | Looks for people who owe money for a debt before or on the given `<date>`; can be used with `after/<date>` for more precise searches, but using this with `time/<time>` does _not_ look for a debt before the given `<date>` and `<time>` (see the above row)                                                                                                                                                                                     | `find before/2022-10-30` will find people with a debt in their debt list on `2022-10-30`, `2022-01-03`, or `1999-12-31`, but not `2022-10-31`<br>`find before/2022-10-31 after/2022-10-01` will find people with a debt in their debt list on `2022-10-01`, `2022-10-15`, or `2022-10-31`, but not `2000-10-31` or `2022-11-01` |
| `after/<date>`     | Looks for people who owe money for a debt after or on the given `<date>`; can be used with `before/<date>` for more precise searches, but using this with `time/<time>` does _not_ look for a debt after the given `<date>` and `<time>` (see 2 rows above)                                                                                                                                                                                       | `find after/2022-10-31` will find people with a debt in their debt list on `2022-10-31`, `2022-11-31`, or `2030-01-01`, but not `2022-10-30`<br>For combined usage with `before/<date>`, see the above row                                                                                                                      |
| `time/<time>`      | Looks for people who owe money for a debt at the given `<time>`; using this with `date/<date>`, `before/<date>` or `after/<date>` does _not_ look for a debt at the given date and time, but rather looks for people with both a debt at the given `<time>`, and a debt that matches the other `<date>` condition (which may or may not be the same as the previous debt; see the [details on combining inputs](#combining-inputs-to-find) below) | `find time/11:00` will find people with a debt in their debt list at `11:00`, but not `11:30` or `23:00`                                                                                                                                                                                                                        |

Note:
* Inputs `name/<name>` and `address/<address>` are case-insensitive (they ignore capitalisation) and perform partial matching (they match anything that contains them).
* All other inputs (including `d/<description>`) are case-sensitive (capitalisation is important), and perform exact matching (they only match if the parts they are matching are exactly the same).
* The order of the conditions does not matter.
* At least one input must be provided, `find` cannot work with no inputs.

#### Combining inputs to `find`

When multiple inputs are provided to `find`, only people matching all conditions will be shown.

Note that combining the inputs looking for people with a specific debt in their debt list means to look for a person with at least one debt that matches each input, but they do not need to have any debt in their debt list that matches all the inputs at once.
The input pairs `above/<money> below/<money>` and `before/<date> after/<date>` are exceptions: respectively, they mean to look for a person with a debt with a monetary value both above and below the given amounts,
and to look for a person with a debt that falls on a date both before and after the given dates.
Using these input pairs with each other or other inputs looking for people with a specific debt in their debt list behaves similarly to the other inputs; see the examples below.

Examples:
* `find n/hans p/81234567` will match `Hansel` or `Hans Gruber` if his phone number is `81234567`.
* `find d/burger n/hans` will match `Hansel` if he owes money for `burger`.
* `find d/burger m/10` will match anyone who both owes money for `burger` and owes `$10.00` for something (or someone who owes `$10.00` for `burger`).
* `find above/10 below/20 d/fries` will match anyone who both owes money for `fries` and owes between `$10.00` and `$20.00` inclusive for something (or someone who owes between `$10.00` and `$20.00` inclusive for `fries`).
* `find before/2022-11-30 after/2022-11-01 time/11:00` will match anyone who both owes money for something between `2022-11-01` and `2022-11-30` inclusive (the month of November 2022) and owes money for something at `11:00` (or someone who owes money for something at `11:00` during the month of November 2022).
* `find tele/janesmith above/10 below/20 before/2022-11-30 after/2022-11-01` will match anyone with the Telegram handle `@janesmith` who both owes between `$10.00` and `$20.00` inclusive for something and owes money for something during the month of November 2022 (or someone who owes between `$10.00` and `$20.00` inclusive for something during the month of November 2022). 

#### Finding persons by debt description: `finddebt`

Finds persons who are associated with any debts that match any of the given keywords.<br>
This is useful if you do not remember the exact description of a debt you want to look for (e.g. was it `KFC` or `kfc`?).

Format: `finddebt <keyword>…`

* The search is case-insensitive. e.g. `burger` will match `Burger`
* The order of the keywords does not matter. e.g. `Sharing Meal` will match `Meal Sharing`
* Only the descriptions of the debts are searched for the keywords.
* Only full words will be matched e.g. `Burger` will not match `Burgers`
* Persons with debts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `burger meal` will return people associated with debts that have descriptions `Chicken Burger` or `Meal Sharing`

Examples:
* `finddebt Burger` returns people associated with debts that have descriptions `burger` or `Chicken Burger`
* `finddebt burger meal` returns people associated with debts that have descriptions `Chicken Burger` or `Meal Sharing`<br>

#### Listing all persons: `list`

Shows a list of all persons in PayMeLah.<br>
You can use this command to return to displaying the full list of people you have added to PayMeLah.

Format: `list`

#### Listing all debtors: `listdebtors`

Shows a list of all persons that owe you more than or equal to a certain amount of money in PayMeLah. If no amount is provided, a list of persons who owe any amount of debt is displayed.

Format: `listdebtors [m/<money>]`

Example: `listdebtors m/10` displays the list of persons that owe more than $10.00.

#### Sorting list of persons: `sort`

Sorts and displays the list of persons using the given criterion and order. You can use the following prefixes to sort by the respective criterion:
* `n/` - Name of person
* `m/` - Total amount of money owed by person
* `date/` - Time since date of oldest debt owed by person

Do note that you can only sort by exactly one criterion at a time.

Use the following symbols after the criteria prefix to indicate the order of sorting:
* `+` - Ascending order
* `-` - Descending order

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
When you sort by time since oldest debt, all persons who do not owe any debt will be placed at the end of the list, regardless of whether ascending or descending order is specified.
</div>

Format: `sort <criterion prefix><order>`

Example: `sort n/+` sorts and displays the list of persons in ascending alphabetical order of their names.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Features for Data Management

#### Saving the data

PayMeLah data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

PayMeLah data are saved as a JSON file `[JAR file location]/data/paymelah.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, PayMeLah will discard all data and start with an empty data file at the next run.
</div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

### Planned Features

#### Editing debts: `editdebt` (Not yet implemented)

This command would allow you to edit the details of a specific debt, such as its description, amount, etc. Do look forward to it in a future update!

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Glossary

* _Command_: An instruction for PayMeLah to execute. All commands start with a _command phrase_ and any inputs the command may require.
* _Command box_: A box in which you can enter _commands_. See [Navigating the application interface](#tutorial-navigating-the-application-interface) to learn more.
* _Command Line Interface (CLI)_: The method by which you instruct PayMeLah to perform tasks, which is by entering _commands_ into the _command box_.
* _Command phrase_: The name of the instruction for PayMeLah to execute. For example, `list` is the command phrase to show the list of people added to PayMeLah.
* _Debt_: A transaction event (e.g. group purchase, shared Grab food order) where someone owes you money.
* _Debt card_: A card containing the details of a specific _debt_ that has been added to PayMeLah. See [Navigating the application interface](#tutorial-navigating-the-application-interface) to learn more.
* _Debt index_: The number that appears next to each _debt_’s description in a person’s _debt list_.
* _Debt list_: A box containing a list of _debts_ owed by a person added to PayMeLah. See [Navigating the application interface](#tutorial-navigating-the-application-interface) to learn more.
* _Debtor_: A person who owes you money.
* _Enter[ing]_ a command: Type the _command_ into the _command box_, and press the <kbd>Enter</kbd> key.
* _Index_: The number that appears next to each person’s name or each _debt_’s description.
* _Input_: Additional information supplied by you that is used to run a _command_.
* _Keyboard shortcut_: A key or sequence of keys which you may press to perform actions without your mouse.
* _Message box_: A box in which PayMeLah will inform you about the results of your _commands_. See [Navigating the application interface](#tutorial-navigating-the-application-interface) to learn more.
* _Person card_: A card containing the details and _debts_ of someone that has been added to PayMeLah. See [Navigating the application interface](#tutorial-navigating-the-application-interface) to learn more.
* _Person index_: The number that appears next to each person’s name in the _person list_.
* _Person list_: A box which contains a list of people added to PayMeLah. See [Navigating the application interface](#tutorial-navigating-the-application-interface) to learn more.
* _Prefix_: A short piece of text indicating what type of information follows. For example, `n/` is the prefix that indicates that what follows is a name.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command summary

| *Actions for* <br> [**General Utility**](#features-for-general-utility) | Format, Examples |
|-------------------------------------------------------------------------|------------------|
| [**Help**](#viewing-help-help)                                          | `help`           |
| [**Undo**](#undoing-a-command-undo)                                     | `undo`           |
| [**Clear**](#exiting-the-program-exit)                                  | `clear`          |
| [**Exit**](#exiting-the-program-exit)                                   | `exit`           |


| *Actions for* <br> [**Managing Persons**](#features-for-managing-persons) | Format, Examples                                                                                                                                                             |
|---------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Add person**](#adding-a-person-add)                                    | `add n/<name> p/<phone number> tele/<telegram> a/<address> [t/<tag>]…` <br> e.g., `add n/James Ho p/22224444 tele/James_H0 a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| [**Edit person**](#editing-a-person-edit)                                 | `edit <index> [n/<name>] [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag>]…`<br> e.g.,`edit 2 n/James Lee tele/James_L33`                                         |
| [**Delete person**](#deleting-a-person-delete)                            | `delete <index>`<br> e.g., `delete 3`                                                                                                                                        |


| *Actions for* <br> [**Managing Debts**](#features-for-managing-debts) | Format, Examples                                                                                                                                        |
|-----------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Add debt**](#adding-a-debt-adddebt)                                | `adddebt <person index…> d/<description> m/<money>` <br> e.g., `adddebt 3 d/Chicken Rice m/4`                                                           |
| [**Split debt**](#splitting-a-debt-splitdebt)                         | `splitdebt <person index…> d/<description> m/<money> [date/<date>] [time/<time>]` <br> e.g., `splitdebt 1 2 d/Pizza m/33.99 date/2022-10-12 time/13:00` |
| [**Delete debt**](#deleting-a-debt-deletedebt)                        | `deletedebt <person index> debt/<debt index…>` <br> e.g., `deletedebt 2 debt/2 3`                                                                       |
| [**Clear debts**](#clearing-debts-cleardebts)                         | `cleardebts <person index>` <br> e.g., `cleardebts 3`                                                                                                   |
| [**Mark debts**](#marking-debts-as-paid-mark)                         | `mark <person index> debt/<debt index…>` <br> e.g., `mark 2 debt/2 3`                                                                                   |
| [**Unmark debts**](#marking-debts-as-unpaid-unmark)                   | `unmark <person index> debt/<debt index…>` <br> e.g., `unmark 2 debt/2 3`                                                                               |
| [**Get statement**](#getting-the-statement-statement)                 | `statement`                                                                                                                                             |


| *Actions for* <br> [**Searching**](#features-for-searching)     | Format, Examples                                                                                                                                                                                                                                        |
|-----------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Find person**](#finding-persons-by-fields-find)              | `find [n/<name>] [p/<phone number>] [tele/<telegram>] [a/<address>] [t/<tag>]… [d/<description>]… [m/<money>]…`<br> `[above/<money>] [below/<money>] [date/<date>]… [before/<date>] [after/<date>] [time/<time>]…`<br> e.g., `find d/burger above/10.0` |
| [**Find debts**](#finding-persons-by-debt-description-finddebt) | `finddebt <keyword>…`<br> e.g., `finddebt burger bowling`                                                                                                                                                                                               |
| [**List persons**](#listing-all-persons-list)                   | `list`                                                                                                                                                                                                                                                  |
| [**List debtors**](#listing-all-debtors-listdebtors)            | `listdebtors [m/<money>]`<br> e.g., `listdebtors m/10`                                                                                                                                                                                                  |
| [**Sort**](#sorting-list-of-persons-sort)                       | `sort <criterion prefix><order>`<br> e.g., `sort n/+`, `sort m/-`, `sort date/+`                                                                                                                                                                        |

[Return to Table of Contents](#table-of-contents)

