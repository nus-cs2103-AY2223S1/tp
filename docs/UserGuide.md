---
layout: page
title: User Guide
---

SectresBook helps **secretaries** to maintain all the information of the members of their club by **collating a list of identifiable information, past records and future tasks.**

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

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete 3`** : Deletes the 3rd contact shown in the current list.
   
   * **`help`**: Open the UserGuide in your default web browser.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Properties

### Person Properties

#### Name
  - The name of person to be recorded in the SectresBook.
  - Identified by prefix `name`.
  - This is a valid property to find a person by using the `find` command.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
It is recommended to include the full name of the person to avoid ambiguities during operations.
</div>

#### Phone
  - The phone number of the person to be recorded in the SectresBook.
  - Identified by the prefix `phone`.
  - This is a valid property to find a person by using the `find` command.
  - A phone number should contain only numbers and be at least 3 digits long.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Ensure that no two persons have the same phone number!
</div>

#### Email
  - The email address of the person to be recorded in the SectresBook.
  - Identified by the prefix `email`.
  - Serves mainly as a point of information relating the person.
  - Emails should be of the format `local-part@domain` and adhere to the following constraints:
    1. The local-part should only contain alphanumeric characters and these special characters `_` and `.`. The local-part may not start or end with any special characters
    2. This is followed by a `@` and then a domain name. The domain name is made up of domain labels separated by periods. The domain name must:
       + end with a domain label at least 2 characters long
       + have each domain label start and end with alphanumeric characters
       + have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

#### Address
  - The residing address of the person.
  - Identified by the prefix `home`.
  - Serves mainly as a point of information relating to the person.
  - There is no constraint on how the home address of a person should be written, as long as it is sufficiently understandable.

#### Loan
  - The amount of money that is owed by a person, or is to be paid to that person.
  - Identified by the prefix `loan`.
  - A loan amount can be either negative, positive or zero.
    + A positive value indicates an amount that the person has yet to pay to the organisation.
    + A zero value indicates no outstanding loan.
    + A negative value indicates an amount that should be paid back to the person.
  - Loans can only take up numerical values.

#### Loan History
  - A loan history is a subset of the loans properties that describes the changes to the numeric values of the loans in detail.
  - As this property is a descriptor of the prefix `loan`, it does not have its own prefix, but it can be visually seen from the inspection panel.
  - Consists of the following:
    - Current loan value
    - Change in amount from last value
    - Reason for change

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Including a reason for every change to a person's loan value reduces the risk of accidentally adding an incorrect amount to someone. It keeps a detail tab of every increment and decrement in value.
</div>

#### Birthday
  - The birthday date of the person.
  - Identified by the prefix `bday`.
  - Serves as a point of information for a person.
  - This must be a valid date form `DD/MM/YYYY`.
       
#### Tags
  - Persons can be linked to tag objects, which serve as markers that draw connections between different people as well as associated notes.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Please refer to the `Tags` section below for more information regarding tags.
</div>

### Notes Properties
#### Title
  - The title serves as the main marker for notes and summarises the important details of this specific note.
  - This property is identified by the prefix `title`.
  - Notes can be filtered through with the `find` command using the title property.
  - Titles must be within 100 characters and can contain any ASCII characters.
  - This property cannot be left empty.
  
#### Content
  - The content serves as the description for notes.
  - This property is identified by `content`.
  - This property cannot be left empty.

#### Tags
- Persons can be linked to tag objects, which serve as markers that draw connections between different people as well as associated notes.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Please refer to the `Tags` section below for more information regarding tags.
</div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* `<OR>` signifies an exclusive-or parameter that is to be input.<br>
  e.g `INDEX <OR> NAME` allows either the parameter `INDEX` or the parameter `NAME`, but not both

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

### Adding a person: `add`

Adds a person to the SectresBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...​ [l/LOAN]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 t/Member`
* `add n/Jane Doe p/98876542 e/jane@example.com a/That Street, block 133, #11-10 t/Member l/10`
* `add n/Neethesh t/VicePresident e/neethesh@example.com a/Happy Avenue p/91234567`

### Listing all persons : `list`

Shows a list of all persons in the SectresBook.

Format: `list`

### Editing a club member : `edit`

Edits an existing club member’s information in the SectresBook

Format: `edit INDEX <OR> NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

Example of usage:

`edit 1 p/99999999` can be used to easily update the first person's contact information.

`edit John p/91235555` can be used to update a person’s contact information if there exists only one person whose name contains John.<br>
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

Format: `addNote n_t/TITLE n_c/CONTENT `

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
TITLE must be unique and not longer than 100 characters.
</div>

Examples:
* `add n_t/Club meeting soon! n_c/Remind club members to attend meeting.`
* `add n_t/T-Shirt payment due n_c/Collect money`

### Listing Notes `listNote`

Shows a list of all notes in the SectresBook.

Format: `listNote`

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

--------------------------------------------------------------------------------------------------------------------

## Glossary
The definitions in this glossary are context-specific to this application.

Word | Definition
--------|------------------
**Command Line Interface (CLI)** | A text-based interface that recieves typed commands as input and returns textual feedback as output.
**Graphical User Interface (GUI)** | An image-based interface that is more visually appealing than a command-line interface and encapsulates information through the use icons and images.
**Loan** | An amount of money that is borrowed by or owed to a person. A positive value signifies an amount owed by the person and a negative value signifies an amount to be paid to that person.
**Parameter** | A value passed as a section of a command, typically following a prefix.
**Prefix** | A signposting word that indicates the kind of property (i.e. name, email, address, etc), which typically follows immediately after the prefix, that is to be passed as a parameter.
**Property** | An identifiable feature a person or object has that sufficiently distinguishes it from other objects of the same kind.
**Secretary** | A person that manages the tasks and events related to the operations of an organisation.
**Tag** | A label that connects two or more related people together, such that they can be referred to as a group specified by the tag.
**Treasurer** | A person that manages the finances and monetary transactions related to the operations of an organisation.

--------------------------------------------------------------------------------------------------------------------
## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**FindTag** | `findTag TAG [MORE_TAGS]`<br> e.g., `findTag Operations Outreach`
**List** | `list`
**Help** | `help`
