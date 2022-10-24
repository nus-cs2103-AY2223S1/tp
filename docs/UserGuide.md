---
layout: page
title: User Guide
---


<p align="center">
    <img src="images/LTNS_logo.png" width="200" height="200">
</p>


Long Time No See (LTNS) is a  **desktop app for managing clients, policies and events optimized for use via a Command Line Interface** (CLI) while still having the benefits of an **intuitive and minimalistic Graphical User Interface (GUI)**. Made simpler with an intuitive and minimalistic graphical user interface (GUI) and customised functionalities to pinpoint your needs, LTNS will enable you to stay close to your dearest clients!

# Table of Contents
1. [Quickstart](#quick-start)
2. [Features](fFeatures)<br>
    a. [Common Features](#common-features)<br>
    b. [Person Features](#person-features)<br>
    c. [Policy Features](#policy-features)<br>
    d. [Event Features](#event-features)
3. [Frequently Asked Questions](#faq)
4. [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start
1. Ensure you have Java `11` or above installed in your Computer.


1. Download the latest `LTNS.jar` from [here](https://github.com/AY2223S1-CS2103T-W13-2/tp/releases).


1. Copy the file to the folder you want to use as the _home folder_ for your LTNS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

**LongTimeNoSee** allows you to track your clients, policies and events.
As such, the features available to you are split into 4 main features:
1. [Common Features](#common-features): Here you can find basic Application features
2. [Client Features](#client-features): Here you can find all features related to keeping track of all your clients 
3. [Policy Features](#policy-features): Here you can find all features related to keeping track of all your policies 
4. [Event Features](#event-features): Here you can find all features related to keeping track of all your important appointments


<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Words in round brackets and separated by a `/` are options for parameters to be supplied by the user. <br>
Only one parameter can be specified. <br>
e.g `(n/NAME)/(p/PHONE)` can be used as `n/John Doe` or as `p/12341234`

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

## Common Features

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

## Client Features

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Search for contacts: `find`

Search for contacts based on certain metrics\

Format: `find (n/NAME)/(p/PHONE)/(e/EMAIL)/(a/ADDRESS)/(t/TAG)…​ `

For `NAME`:
* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

For `TAG`:
* The search is case-insensitive. e.g `friends` will match `Friends`
* Only full words will be matched e.g. `colleagues` will not match `colleague`
* Note: Only one tag can be specified

For non-textual information i.e `PHONE`, `EMAIL`, `ADDRESS`:
* The search is case-sensitive. e.g `hans@email.com` will not match `Hans@email.com`
* Only full searches will be matched e.g `12341234` will not match `1234`

Examples:

* `find n/John` returns `john` and `John Doe`
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* `find p/12341234` returns contacts with `12341234` as their phone number
* `find t/friends` returns contacts with a `friends` tag


### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.



### Sorting all Clients : `sort`

This allows you to sort your clients in the address book based on a specified metric.

Format: `sort KEYWORD`

* Sorts the list of clients based on specified keyword
* Keyword must be from client details
* (v1.2) current supported sort metrics include `name, phone number, address`

Examples:
* `sort name` will display your address book in alphabetical order based on their name
* `sort phone` will display your addresss book in numerical order based on their phone numbers

Sorting **KEYWORDS** include: 
* `default` (the default order is based on when a client is added, from oldest to newest)
* `name` (by alphabetical order)
* `email` (clients using the same email platforms are grouped together, thereafter, alphabetical order is used within each group)
* `phone` (by numerical order of phone number)
* `birthday` (from oldest to youngest)
* `income` (from highest to lowest income)
* `risk appetite` (from highest to lowest)


### Pin a user by name: `pin`
* Format: `pin [CLIENT_NAME]`
* Description:
* Example Usage:


### Search for Contacts based on user's metrics: `find`
* Format: `find [*KEYWORDS]`
* Description:
* Example Usage: `find n/Jim a/53`



### Assigning a Client to an Event/Policy 
An existing client in your Client Book can be assigned to an Event or Policy.<br>
To find out how to do so, you can refer to the [Adding an Event](#adding-an-event) guide to assign a Client to an Event or refer to the [Assigning a Policy to a Client](#assigning-a-policy-to-a-client) guide to find out how you can assign a Client to a Policy.


## Policy Features


### Adding a policy 


### Viewing all policies: `policies` [Coming soon]
* Format: `policies`
* Description: In v1.2, we will allow the user to view all available policies in a list format.
* Example Usage: `policies`
* Example Result: Shows a list of all policies stored on the page


### Deleting a policy


### Search for a policy 


### Assigning a Policy to a Client 

If you have successfully sealed a deal with a client, you can keep track of this by assigning the policy to your client! 

If you have yet to add either your Client or Policy to the Application, you can refer to the [Adding a Person](#adding-a-person-add) or [Adding a Policy](#adding-a-policy) guides.


### Tracking your Income 



## Event Features 


### Adding an Event 






(for Colin: can put this where you deem fit)<br>
You may want to indicate that a particular Event involves a Client of yours. If you have not added this Client to your Client Book, you can refer to the [Adding a Person](#adding-a-person-add) guide to add your Client first.


### Deleting an Event 


### Viewing all Events : `listEvents`

This allows you to view all Events that have been previously added.

Format: `listEvents`


### Searching for an Event


### Viewing all Events in the next 7 days : `calendar`

This allows you to view all Events that you have in the next 7 days, allowing you to keep track of any upcoming important appointments you have scheduled. 

Format: `calendar`


### 


## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: Why am I seeing `(insert error)` when trying to add an event?<br>
**A**: When adding an Event involving a client, this client must exist in your client book first.

**Q**: Why am I seeing a `Sorting metric does not exist` error when trying to sort my client list?<br>
**A**: You may be typing a wrong **keyword** or calling a sorting metric which is not supported. You may refer to [this list](#sorting-all-clients--sort) for a list of supported **keywords**. If you would like to suggest more sorting **keywords**, do feel free to contact us! 

**Q**: Why is a blank screen shown when I use the `calendar` feature?
**A**: You may not have added any upcoming events in the next 7 days! You may want to [Add an Event](#adding-an-event) first. Alternatively, you can choose to [view all Events](#viewing-all-events--listevents) instead.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action       | Format, Examples                                                                                                                                                      |
|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**      | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**    | `clear`                                                                                                                                                               |
| **Delete**   | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**     | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**     | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**     | `list`                                                                                                                                                                |
| **Help**     | `help`                                                                                                                                                                |
| **Find**     | `find [n/NAME] [p/PHONE] [e/EMAIL]...` <br> e.g: ` find n/Jim p/98765432`                                                                                             |
| **Sort**     | `sort [COMPARABLE_METRIC]` <br> `e.g: sort AGE`                                                                                                                       |
| **Pin**      | `pin [n/Name]` <br> e.g: `pin n/Jim`                                                                                                                                  |
| **policies** | `policies`                                                                                                                                                            |
