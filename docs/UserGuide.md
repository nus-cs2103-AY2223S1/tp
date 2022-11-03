---
layout: page
title: User Guide
---

SoConnect is a **desktop app for managing contacts and tasks**. It aims to help NUS SoC students stay better connected to their school life, in terms of social connections and school tasks. SoConnect is also **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SoConnect can **get your contact and task management done faster than traditional GUI apps.**

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

# Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `SoConnect.jar` from [here](https://github.com/AY2223S1-CS2103T-W15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SoConnect.

1. Double-click the file to start the app. A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to your SoConnect.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits SoConnect.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family`, etc.

* Parameters can be in any order unless explicitly stated otherwise.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Tasks will be referred to as Todos

* `INDEX` is used in commands to refer to a specific contact or todo by their index number on the currently displayed contact list and todo list. The `INDEX` **must be a positive non-zero integer** 1, 2, 3, …​

</div>

## Contact Management Features

### Adding a contact: `add`

Adds a contact to your SoConnect.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

* Duplicate name is not allowed. A name is considered duplicate if it has the exact same characters (case-sensitive) with an existing contact's name. For example, `Alex Yeoh` and `Alex yeoh` is considered different. This is to increase users' easiness in differentiating between contacts in the future. It is also to prevent users from creating new contacts when their actual intention is to edit a field from existing contacts.
* `NAME` currently only accepts alphanumeric values. This is to prevent users from supplying mistyped names into their contacts. However, numbers are still accepted to provide users a mean to differentiate between similar names and to account for special names that originally have numbers in it. 

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567`

### Editing a contact : `edit`

Edits an existing contact in your SoConnect.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`

* Edits the contact at the specified `INDEX`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values, provided that the input values do not violate constraints set by other features. For example, editing a name with input value that is considered duplicate in `add` feature is not allowed.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower` Edits the name of the 2nd contact to be `Betsy Crower`.

### Listing all contacts : `list`

Shows a list of all contacts in your SoConnect.

Format: `list`

### Searching for a contact: `search`

Search for contacts using partial information.

Format: `search [CONDITION] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`

* `search t/TAG1 t/TAG2…` searches for contacts that contain all the given tags.
* `search and n/NAME p/PHONE…` searches for contacts that match all the given information.
* `search n/NAME p/PHONE…` and `search and n/NAME p/PHONE…` will return the same contacts that match all the given information.
* `search or t/TAG1 t/TAG2…` searches for contacts that contain any of the given tags.
* The search using `n/NAME` is case-insensitive. (e.g. `hans` will match `Hans`).
* Displays a list of relevant contacts related to the search query if no search result available. A contact is considered relevant if there are high matches of characters between the contact information and search keyword. For example, `David Li` and `Charlotte` are relevant to `search n/al` because these names contain characters `a` and `l` in it.

Example:
* `search t/family` returns all contacts tagged with family in the contact list.
* `search and a/NUS p/86178789` returns all contacts with that address and phone number.
* `search t/cs2103t t/tp` returns all contacts tagged with both cs2103t and tp.
* `search or t/friends t/family` returns all contacts tagged with either friends or family.
* `search n/Johm` is supposed to return an empty result since there is no contact named `Johm` in the list of contacts, but now it will return contacts with names similar to that. For example, `John`.

### Autocompleting search: `search`

Displays a list of search queries based on the current search query with the last parameter completed. The completed parameter will depend on the contacts that match the current search query. User can choose one of the search queries and perform the searching without having to type the full parameter.

Format: Refer to the [`search`](#Search for a Contact) command format above.

* This feature is only available when search command is entered (i.e. the command entered matches the [`search`](#Search for a Contact) format stated above).
* Only the last parameter will be completed (e.g. `search and n/NAME p/PHONE`, only the last parameter `PHONE` will be completed).
* The list of search queries will include the current search query.
* No result will be displayed if there is no contact matches the current search query.
* No result will be displayed if the last parameter is empty.

Example:
* `search or n/John p/` displays nothing as the last parameter `PHONE` is empty.
* `search and n/John a/N`, displays a list of search queries containing `search and n/John a/N` and `search and n/John a/NUS` if SoConnect has contacts with name `John` and address `NUS`
* `search and n/John a/N` displays nothing if SoConnect does not have any contact with name `John` or has contact with name `John` but does not start with `N`.
* `search or n/John a/N` displays a list of search queries containing `search and n/John a/N`, `search and n/John a/NUS`, `search and n/John a/NYC` if SoConnect has contacts with address `NTU` and `NYC`, does not have to care about the name in the contact since it is `or` condition.
* `search or n/John a/N` displays nothing if SoConnect does not have contacts with address starts with `N`.

### Sorting contacts : `sort`

Sorts the list of contacts displayed by 1 or more parameter(s) chosen by you.

How *names (n/)*, *emails (e/)*, *addresses (a/ )* are sorted:
* In alphabetical order. (e.g. `Al` comes before `Alfred` which comes before `Brad`)
* Case insensitive. (e.g. `Al`, `al`, `AL`, and `aL` are identical when it comes to sorting)

How *phone numbers (p/)* are sorted: 
* In increasing numerical order. (e.g. `123` comes before `125` which comes before `1234`)

How *tags (t/TAG)* are sorted:
* Contacts with the *TAG* you specified will come before contacts without the *TAG*.

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>
* When sorting by tags, unlike other parameters, you have to specify a value (an existing `TAG`) to sort by.
* For other parameters (i.e. `n/ e/ a/ p/`), values provided are ignored. (e.g. sorting by `n/Alfred` is a valid command, the list will be sorted by name and the given value `Alfred` is ignored)

</div>

Format: `sort [n/] [p/] [e/] [a/] [t/TAG]…​`
* To sort in reverse order from the orders given above, use these modified parameters: `[n/!] [p/!] [e/!] [a/!] [t/!TAG]`.
* To sort with multiple parameters, arrange the parameters in order of decreasing priority.
  * The list will be sorted by the first parameter.
  * If ties occur (e.g. both contacts have the exact same name), the second parameter will be used to sort the tied contacts.
  * If the tie still occurs, the third parameter will be used.
  * Repeat until the tie is resolved or there are no more parameters.

Example:
* `sort n/` sorts by names. (e.g. `David` appears before `Mike`)
* `sort t/!friend` sorts by the `friend` tag in reverse. (e.g. `Mike` appears before `David` who has the `friend` tag)
* `sort t/friend n/` sorts by the `friend` tag first, followed by names. (e.g. `David` and `Fred` who have the `friend` tag appear before `Mike`, `David` appears before `Fred`)

### Deleting a contact : `delete`

Deletes the specified contact from your SoConnect.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in your SoConnect.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

### Clearing all contacts : `clear`

Clears all contacts from your SoConnect.

Format: `clear`

## Tag Management Features

### Creating a Tag: `tag create`

Creates a new tag

Format: `tag create t/TAG`

Example:
* `tag create t/family` creates a `family` tag.

### Deleting a Tag: `tag delete`

Deletes a tag.

Format: `tag delete t/TAG`

<div markdown="block" class="alert alert-info">
**:information_source: Note:** When `TAG` is deleted, `TAG` is removed from all the contacts which previously had it.
</div>

Example:
* `tag delete t/family` deletes the `family` tag.

### Editing a Tag: `tag edit`

Renames an existing tag.

Format: `tag edit t/TAG1 t/ TAG2`

* `TAG1` is the current name of the tag and `TAG2` is the new name of the tag.

Example:
* `tag edit t/friend t/bestFriend` changes the friend tag to a bestFriend tag.

### Adding a Tag to a Contact: `tag add`

Adds an existing tag to an existing contact.
* `Coming soon in v1.5`, we will upgrade `tag add` to add tags to todos.

Format: `tag add INDEX t/TAG`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags. Add as many as you want.
</div>

<div markdown="block" class="alert alert-info">
**:information_source: Note:** The tag has to be made first before you can add it into a contact.
</div>

* Adds a `TAG` to the contact at the specified `INDEX`.

Example:
* `tag add 1 t/friend` adds the friend tag to the first contact shown in the list.

### Removing a Tag from a Contact: `tag remove`

Removes an existing tag from an existing contact.
* `Coming soon in v1.5`, we will upgrade `tag remove` to remove tags from todos.

Format: `tag remove INDEX t/TAG`

* Removes a `TAG` from the contact at the specified `INDEX`.

Example:
* `tag remove 1 t/friend` removes the friend tag from the first contact shown in the list.

## Customisation Features

### Customising order of details: `customise order`

Customise the order of information shown in all contacts shown.

Format: `customise order [t/] [p/] [e/] [a/]`

* Name of contact will always be at the top of each contact and cannot have its order changed.
* Information that can have its order changed: Tags, Phone Number, Email, Address.
* Information that are not specified will be ordered last and follow the default order. (Tags > Phone Number > Email > Address)

Example:

* `customise order a/ e/ p/` The application will show address first, followed by email, phone number, then tags.
* `customise order a/` The application will show address first. The rest of the information will follow the default order. Therefore, address will be followed by tags, phone number and then email.

### Hiding contact details: `customise hide`

Hide certain information of all contacts displayed.

Format: `customise hide [t/] [p/] [e/] [a/]`

* Information that can be hidden: Tags, Phone Number, Email, Address.
* After using the command, the information specified is hidden.
* If the information specified is already hidden, it will stay hidden.

Example:
* `customise hide e/` The application no longer shows emails in the list of contacts.
* `customise hide p/ t/` The application no longer shows phone numbers and tags in the list of contacts.

### Showing contact details: `customise show`

Show certain information of all contacts displayed.

Format: `customise show [t/] [p/] [e/] [a/]`

* Information that can be changed from being hidden to being shown: Tags, Phone Number, Email, Address.
* After using the command, the information specified is shown.
* If the information specified is already shown, it will stay shown.
* `Coming soon in v1.5`, we will include `customise show all`, a shortcut to show all information.

Example:
* `customise show a/` The application now shows addresses in the list of contacts.
* `customise show p/ t/` The application now shows phone numbers and tags in the list of contacts.

## Todo Management Features

A todo is a task that needs completing. A todo consists of 
1. a description
2. a date for the deadline of the task
3. the priority of the task 
4. (optional) tags to help you categorise your todos

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>
* Duplicate todos are not allowed (to help you avoid adding a todo that you forgot you already added). If you need to have todos with the same description, you are still able to do so, as long as the todos have different tags, dates, or priorities.
* Priority of a todo can strictly only be `low`, `medium`, or `high`. `Coming soon in v1.5`, we will add smarter priorities (to accept other variations such as `Low`, `Medium`, `High`, `L`, `M`, `H`).

</div>

### Adding a todo: `todo add`

Adds a todo to your SoConnect.

Format: `todo add d/DESCRIPTION date/DATE pr/PRIORITY [t/TAG]…​`

* `DATE` should be of the format dd-MM-yyyy (e.g. 24-03-2022).
* The todo list will always be sorted by date from earliest to latest (for todos with the same date, they will be sorted in decreasing priority order).

Examples:
* `todo add d/Watched recorded videos for CS2100 date/24-10-2022 pr/low t/CS2100`
* `todo add d/Prepare slides for OP2 date/25-03-2022 pr/high t/CS2101 t/CS2103T`

### Editing a todo : `todo edit`

Edits an existing todo in your SoConnect.

Format: `todo edit INDEX [d/DESCRIPTION] [date/DATE] [pr/PRIORITY] [t/TAG]…​`

* Edits the todo at the specified `INDEX`.
* At least one of the optional fields must be provided.
* Parameters given will overwrite the existing values completely.
  * For example, giving 1 or more tag(s) in the edit command will replace all existing tags with the ones given in the edit command.
  * `Coming soon in v1.5`, you can use `tag add` and `tag remove` to modify tags in a todo instead of only using `todo edit`.

Examples:
*  `todo edit 1 d/Read notes for ST2334` Edits the description of the 1st todo to be `Read notes for ST2334`.
*  `todo edit 1 pr/medium t/ST2334` Edits the priority of the 2nd contact to be `medium` and changes its tags to just `ST2334`.

### Deleting a todo : `todo delete`

Deletes the specified todo from your SoConnect.

Format: `delete INDEX`

* Deletes the todo at the specified `INDEX`.

Examples:
* `todo show` followed by `todo delete 2` deletes the 2nd todo shown in your SoConnect.

### Clearing all todos : `todo clear`

Clears all todos from your SoConnect.

Format: `todo clear`

### Filtering todos shown : `todo show`

Shows a filtered list of todos in your SoConnect.

Format: `todo show`, `todo show today`, `todo show date/DATE`, `todo show date/DATE to DATE`, `todo show t/TAG`, `todo show pr/Priority`

* `todo show`: Shows all todos.
* `todo show today`: Shows all todos with the date same as the current date.
* `todo show date/DATE`: show all todos with the specified date.
* `todo show date/DATE1 to DATE2`: shows all todos with the date from `DATE1` to `DATE2`.
* `todo show pr/PRIORITY`: Shows all todos with the specified priority.
* `todo show t/TAG`: Shows all todos with the specified tag.

Examples:
* `todo show date/25-10-2022`: show all todos with the date `25-10-2022`.
* `todo show date/24-10-2022 to 26-10-2022`: shows all todos with the date from `24-10-2022` to `26-10-2022`.
* `todo show pr/high`: Shows all todos with the priority `high`.
* `todo show t/friends`: Shows all todos with the tag `friends`.

## General Features

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SoConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SoConnect contacts data are saved as a JSON file `[JAR file location]/data/soconnect.json`.<br>
SoConnect todos data are saved as a JSON file `[JAR file location]/data/todolist.json`.<br>
Advanced users are welcome to update data directly by editing these data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SoConnect will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

--------------------------------------------------------------------------------------------------------------------

# FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SoConnect home folder.

--------------------------------------------------------------------------------------------------------------------

# Command summary

| Action          | Format, Examples                                                                                                                                                                                                     |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665`                                                                                |
| **Clear**       | `clear`                                                                                                                                                                                                              |
| **Customise**   | `customise order [t/] [p/] [e/] [a/]` <br> `customise hide [t/] [p/] [e/] [a/]` <br> `customise show [t/] [p/] [e/] [a/]` <br> e.g. `customise order a/ p/` `customise hide a/ e/ p/` `customise show a/`            |
| **Delete**      | `delete INDEX`<br> e.g. `delete 3`                                                                                                                                                                                   |
| **Edit**        | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br> e.g. `edit 2 n/James Lee e/jameslee@example.com`                                                                                                    |
| **Search**      | `search [CONDITION] [n/NAME] [p/PHONE_NUMBER]…​`<br> e.g. `seach or n/John Doe t/cs2103t`                                                                                                                         |
| **List**        | `list`                                                                                                                                                                                                               |
| **Sort**        | `sort [n/] [p/] [e/] [a/] [t/TAG]…​` <br> e.g. `sort t/!friend n/`                                                                                                                                                |
| **Help**        | `help`                                                                                                                                                                                                               |
| **Create Tag**  | `tag create t/TAG` <br> e.g. `tag create t/friend`                                                                                                                                                                   |
| **Edit Tag**    | `tag edit t/TAG1 t/TAG2`  <br> e.g. `tag edit t/friend t/bestFriend`                                                                                                                                                 |
| **Add Tag**     | `tag add INDEX t/TAG` <br> e.g. `tag add 1 t/friend`                                                                                                                                                                 |
| **Delete Tag**  | `tag delete t/TAG` <br> e.g. `tag delete t/friend`                                                                                                                                                                   |
| **Remove Tag**  | `tag remove INDEX t/TAG` <br> e.g. `tag remove 1 t/friend`                                                                                                                                                           |
| **Add Todo**    | `todo add d/DESCRIPTION date/DATE pr/PRIORITY [t/TAG]…​` <br> e.g. `todo add d/Revise priority/high`                                                                                                              |
| **Edit Todo**   | `todo edit INDEX [d/DESCRIPTION] [date/DATE] [pr/PRIORITY] [t/TAG]…​` <br> e.g. `todo edit t/CS2101`                                                                                                              |
| **Delete Todo** | `todo delete INDEX` <br> e.g. `todo delete 3`                                                                                                                                                                        |
| **Clear Todo**  | `todo clear`                                                                                                                                                                                                         |
| **Show Todo**   | `todo show`<br> `todo show today` <br> `todo show date/DATE` <br> `todo show date/DATE to DATE` <br> `todo show t/TAG` <br> `todo show pr/Priority` <br> e.g. `todo show`, `todo show pr/high`, `todo show t/CS2100` |
