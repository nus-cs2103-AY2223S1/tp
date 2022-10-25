---
layout: page
title: User Guide
---

SectresBook helps **secretaries** to maintain all the information of the members of their club by **collating a list of identifiable information, past records, loan amounts and future tasks.** As a **treasurer**, use SectresBook **manage the finances of your club better** by keeping track of the funds loaned to each member. This all-in-one tool eliminates the trouble of having to search through multiple notebooks/apps to find information regarding a club member, saving you time and effort, so that you can focus on other tasks at hand using the notes feature!

Focusing on speed and efficiency, interact with SectresBook using the **Command Line Interface (CLI)**, while still having the benefits of a visually appealing **Graphical User Interface (GUI)**.

This User Guide is an in-depth guide to help you start managing your contacts, notes and finances. It includes the **installation, features and how to use them, and FAQ for troubleshooting**, ensuring a smooth pickup of the SectresBook.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `SectresBook.jar` from [here](https://github.com/AY2223S1-CS2103T-W12-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SectresBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`name/John Doe phone/98765432 email/johnd@example.com home/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete 3`** : Deletes the 3rd contact shown in the current list.
   
   * **`help`**: Open the UserGuide in your default web browser.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Properties
### Person Properties
- N
  - 
- P
  - 
- A
  - 
- Email
  - 
- Tags
  - 

### Notes Properties
- Title
  - 
- Content
  - 
- Tags
  - 

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add name/NAME`, `NAME` is a parameter which can be used as `add name/John Doe`.

* Items in square brackets are optional.<br>
  e.g `name/NAME [tag/TAG]` can be used as `name/John Doe tag/friend` or as `name/John Doe`.

* `<OR>` signifies an exclusive-or parameter that is to be input.<br>
  e.g `INDEX <OR> NAME` allows either the parameter `INDEX` or the parameter `NAME`, but not both

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/friend`, `tag/friend tag/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `name/NAME phone/PHONE_NUMBER`, `phone/PHONE_NUMBER name/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `phone/12341234 phone/56785678`, only `phone/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the SectresBook.

Format: `add name/NAME phone/PHONE_NUMBER email/EMAIL home/ADDRESS [tag/TAG]...​ [l/LOAN]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add name/John Doe phone/98765432 email/johnd@example.com home/John street, block 123, #01-01 tag/Member`
* `add name/Jane Doe phone/98876542 email/jane@example.com home/That Street, block 133, #11-10 tag/Member l/10`
* `add name/Neethesh tag/VicePresident email/neethesh@example.com home/Happy Avenue phone/91234567`

### Listing all persons : `list`

Shows a list of all persons in the SectresBook.

Format: `list`

### Editing a club member : `edit`

Edits an existing club member’s information in the SectresBook

Format: `edit INDEX <OR> NAME [name/NAME] [phone/PHONE] [email/EMAIL] [home/ADDRESS] [tag/TAG]…​`

Example of usage:

`edit 1 phone/99999999` can be used to easily update the first person's contact information.

`edit John phone/91235555` can be used to update a person’s contact information if there exists only one person whose name contains John.<br>
  If no person is named `John`, no operations are performed. If more than one person has `John` in their name, then the operation is equivalent to `find John`.

### Locating persons by name: `find`

Finds persons whose names match any of the given keywords, or phone numbers contain any of the given keywords(in digits).

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* Phone numbers starting with any of the given keywords(in digits) will be returned.

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* `find 86` returns `Theodore`
  ![result for `find 86`](images/find86result.png)

### Locating persons by tag: `findTag`

Finds persons that have the given tag.

Format: `findTag TAG [MORE_TAGS]`

* The tag search is case-insensitive. e.g `finance` will match `Finance`
* Only the tag is searched.
* Only full words will be matched e.g. `Tech` will not match `Technology`
* Persons matching at least one tag will be returned (i.e. `OR` search).
  e.g. `Finance` will return 
  * `John (tag: Finance)`,
  * `Caroline (tag: Finance) (tag: Tech)`,
  * `Bob (tag:Finance) (tag:HumanResources)`

Examples:
* `findTag Finance` returns `John`, `Caroline` and `Bob`
  ![result for 'findTag Finance'](images/findTagFinance.png)

### Editing loan of a person: `editLoan`

Edits an existing club member's loan amount in the SectresBook.

Format: `editLoan INDEX VALUE REASON`

* Edits the loan value of the existing person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1,2,3 …​
* The `VALUE` can be a positive or negative value with up to 2 decimal places.
* The loan value will be changed by the value given i.e current loan + VALUE.

Examples:

* `editLoan 2 30 bought logistics`
* `editLoan 3 -30 return money from logisitics`
* `list` followed by `editLoan 1 -20 return money` will edit the 1st person in the SectresBook,
reducing their loan by $20 and saving the `REASON` as `return money`.

### Deleting a person : `delete`

Deletes the specified person from the SectresBook.

Format: `delete INDEX`

Otherwise, the specified person may be removed by name.

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Format: `delete <name>`

* Delete the entry of the person who has the given name.
* Will not perform any operation if the name of the person does not exist.
* The `<name>` portion has to be the persons First or Last name in complete.
* If the SectresBook contains persons with the same first or last name that has been input,
the delete command will not execute but will return a list of all people with the given name.

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the SectresBook.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
* `delete Betsy` deletes the entry belonging to Betsy in the SectresBook
* `delete Lynette` does not perform any operation, as Lynette does not exist in the SectresBook.
* `delete all` will clear the entire SectresBook. Please refer to the [`clear` command](#clearing-all-entries--clear).

### Clearing all entries : `clear`

Clears all entries of people from the SectresBook.

Format: `clear`

### Adding Notes `addNote`

Adds a note to the SectresBook.

Format: `addNote title/TITLE content/CONTENT [tag/TAG]... `

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
TITLE must be unique and not longer than 100 characters. Tags are also optional.
</div>

Examples:
* `add title/Club meeting soon! content/Remind club members to attend meeting.`
* `add title/T-Shirt payment due content/Collect money tag/Juniors`

### Listing Notes `listNote`

Shows a list of all notes in the SectresBook.

Format: `listNote`

### Editing Notes `editNote`

Edits an existing specified note in the SectresBook.

Format: `editNote INDEX [title/TITLE] [content/CONTENT] [tag/TAG]...`

Example of usage:

* `editNote 1 content/Second club meeting` can be used to easily update the first note's contents.
* `editeNote 2 title/2020 alumni meeting` can be used to easily update the second note's title.

### Locating a note by title: `findNote`

Finds the notes whose titles match any of the given keywords.

Format: `findNote KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `meeting` will match `Meeting`
* The order of the keywords does not matter. e.g. `Meeting Club` will match `Club Meeting`
* Only the title is searched.
* Only the full words will be matched. e.g. `Meetings` will not match `Meeting`
* Keyword cannot contain special characters. However, numbers will be allowed.
  * e.g. The keywords `?!` and `t-shirt` will not be allowed.
  * e.g. `2` will match `shirt 2` but will not match `shirt2`
* Keywords will ignore special characters.
  * e.g. `Meetings` will match `Meetings!!!` and `Meetings 1`
  * e.g. `shirt` will match `t-shirt`

Examples:
* findNote `Meeting` returns `Club Meeting`, `Meeting!` and `Meeting 2`
* findNote `Soon` returns `Payment (soon)`

### Deleting Notes `deleteNote`

Deletes the specified note from the SectresBook.

Format: `deleteNote INDEX`

* Deletes the note at the specified `INDEX`.
* The index refers to the index number shown in the displayed note list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listNote` followed by `deleteNote 2` deletes the 2nd note in the SectresBook.


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SectresBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SectresBook data are saved as a JSON file `[JAR file location]/data/sectresbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SectresBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SectresBook home folder.

**Q**: Double-clicking the jar file does not open the jar file!<br>
**A**: Make sure that Java 11 is installed on your computer. You may also open the terminal or command prompt and type `java -jar SectresBook.jar`.

**Q** Do I need to have Java installed to run SectresBook?<br>
**A** Yes, SectresBook runs on Java and would require it to work. For more information on how to install Java 11, visit this [website](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html).

**Q** What can I can do if the window size is too small?<br>
**A** Drag the window of the application with your mouse to enlarge it, or simply click the top right maximise icon of the window.

**Q** Do I require an internet connection to use SectresBook?<br>
**A** No, SectreBook fully works without the need for internet connection.

--------------------------------------------------------------------------------------------------------------------

## Command summary

###AddressBook Commands

Action | Format | Examples
--------|-------------------------------------|--------
**Add** | `add name/NAME phone/PHONE_NUMBER email/EMAIL home/ADDRESS [tag/TAG]…​` | `add name/James Ho phone/22224444 email/jamesho@example.com home/123, Clementi Rd, 1234665 tag/friend tag/colleague`
**Clear** | `clear` | `clear`
**Delete** | `delete INDEX`<br>`delete NAME`     | `delete 3` <br> `delete Jane`
**Edit** | `edit INDEX [name/NAME] [phone/PHONE_NUMBER] [email/EMAIL] [home/ADDRESS] [tag/TAG]…​` | `edit 2 name/James Lee email/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]` <br> `find NUMBER` | `find James Jake` <br> `find 8651`
**Find Tag** | `findTag TAG [MORE_TAGS]` | `findTag Operations Outreach`
**Edit Loan** | `editLoan INDEX AMOUNT REASON` | `editLoan 1 -20 Buy Logistics`
**List** | `list` | `list`
**Help** | `help` | `help`
**Exit** | `exit` | `exit`

###Note Commands

Action | Format | Examples
--------|---------------------------|-------------
**Add Note** | `addNote title/TITLE content/CONTENT [tag/TAG]...` | `addNote title/Create Excel Sheet content/Create sheet for blockchain department`
**Edit Note** | `editNote INDEX [title/TITLE] [content/CONTENT] [tag/TAG]...` | `editNote 1 title/Check meeting availability tag/president`
**Delete Note** | `deleteNote INDEX` | `deleteNote 1`
**List Notes** | `listNote` | `listNote`
**Find Note** | `findNote KEYWORD [MORE_KEYWORDS]` | `findNote meeting`
