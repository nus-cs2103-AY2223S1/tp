---
layout: page
title: User Guide
---

# SectresBook

<p align="center">
<img src="images/icons/address_book_32.png" width="128">
</p>

SectresBook helps secretaries to **maintain all the information of the members of their club** by collating a list of identifiable information, past records, loan amounts and future tasks. This all-in-one tool eliminates the trouble of having to search through multiple notebooks or apps to find information regarding a club member, saving you time and effort so that you can focus on other tasks at hand.

With speed and efficiency, you can interact with SectresBook using the **Command Line Interface (CLI)**<sup>[3](#glossary)</sup>, while still having the benefits of a visually appealing **Graphical User Interface (GUI)**<sup>[4](#glossary)</sup>.

The intended audience of this User Guide are secretaries who are planning to use SectresBook, or secretaries who are already using SectresBook.

This User Guide is an in-depth guide to help you start managing your contacts, notes and finances. It includes **installation instructions, features and how to use them, and [Frequently Asked Questions (FAQ)](#faq) for troubleshooting**, ensuring a smooth pickup of SectresBook.

---

## Table of Contents
  * [Introduction to SectresBook](#introduction-to-sectresbook)
  * [Using this guide](#using-this-guide)
  * [Quick start](#quick-start)
  * [User Interface](#user-interface)
  * [Properties](#properties)
    + [Person Properties](#person-properties)
    + [Notes Properties](#notes-properties)
    + [Tag Properties](#tag-properties)
  * [Features](#features)
    + [Person Features](#person-features)
      - [Adding a new person : `add`](#adding-a-new-person--add)
      - [Editing a club member : `edit`](#editing-a-club-member--edit)
      - [Editing loan of a person : `editLoan`](#editing-loan-of-a-person--editloan)
      - [Deleting a person : `delete`](#deleting-a-person--delete)
      - [Locating persons by name or contact number : `find`](#locating-persons-by-name-or-contact-number--find)
      - [Listing all persons : `list`](#listing-all-persons--list)
      - [Sorting by property : `sort` `[coming in v2.0]`](#sorting-by-property--sort-coming-in-v20)
    + [Note Features](#note-features)
      - [Adding Notes : `addNote`](#adding-notes--addnote)
      - [Editing Notes : `editNote`](#editing-notes--editnote)
      - [Deleting Notes : `deleteNote`](#deleting-notes--deletenote)
      - [Locating a note by title : `findNote`](#locating-a-note-by-title--findnote)
      - [Listing Notes : `listNote`](#listing-notes--listnote)
      - [Sorting Notes : `sortNotes` `[coming in v2.0]`](#sorting-notes--sortnotes-coming-in-v20)
      - [Hiding notes panel : `hideNotes`](#hiding-notes-panel--hidenotes)
      - [Showing notes panel : `showNotes`](#showing-notes-panel--shownotes)
    + [General Features](#general-features)
      - [Locating persons and notes by tag : `findTag`](#locating-persons-and-notes-by-tag--findtag)
      - [Inspecting a person : `inspect`](#inspecting-a-person--inspect)
      - [Viewing help : `help`](#viewing-help--help)
      - [Clearing all entries : `clear`](#clearing-all-entries--clear)
      - [Exiting the program : `exit`](#exiting-the-program--exit)
      - [Saving the data](#saving-the-data)
      - [Editing the data file](#editing-the-data-file)
      - [Archiving data files `[coming in v2.0]`](#archiving-data-files-coming-in-v20)
      - [Undo `[coming in v2.0]`](#undo-coming-in-v20)
      - [Redo `[coming in v2.0]`](#redo-coming-in-v20)
  * [FAQ](#faq)
  * [Glossary](#glossary)
  * [Summary](#summary)

---

## Introduction to SectresBook

**SectresBook** is a desktop application that helps you to manage information about your club members (such as loans and birthdays) and keep track of your tasks using notes.

**SectresBook** provides these main features:
* Adding a club member.
* Accessing, modifying members' information.
* Adding a note.
* Accessing, modifying contents of a note.
* Tagging a club member or a note (or both) for organisation

<div markdown="span" class="alert alert-info">
SectresBook is already a convenient way to keep track of information you need to manage a club. However, if you can type fast, using SectresBook will be quicker and more efficient.
</div>

[Back to Table of Contents](#table-of-contents)

---

## Using this guide

This user guide contains all the information that you will need to use and learn **SectresBook**.

If you are a **new user**, the necessary knowledge for you to get started can be found [here](#quick-start).

If you are an **experienced user**, a [Summary](#summary) is also provided, so you can quickly refer to the command formats.

Before you delve into the guide, do take note of the following highlighted information panels.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Used to highlight and display information you should
pay attention to. </div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** used to highlight tips which you might find useful </div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:** used to highlight dangers and things to look out for. </div>

In addition, for better readability, icons in this guide have been colored black. In the actual application, colors may be inverted, but their shape will remain the same.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** This User Guide contains many clickable links. Use the keyboard shortcuts <code>Alt + Left arrow</code> and <code>Alt + Right arrow</code> to navigate back and forth between links quickly. (<code>Command + Left arrow</code> and <code>Command + Right arrow</code> for Mac)</div>

[Back to Table of Contents](#table-of-contents)

---

## Quick start

1. Ensure you have Java `11`<sup>[7](#glossary)</sup> or above installed in your Computer.

1. Download the latest `SectresBook.jar` from [here](https://github.com/AY2223S1-CS2103T-W12-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_<sup>[5](#glossary)</sup> for your SectresBook.

1. Double-click the file to start the app. A GUI<sup>[4](#glossary)</sup> similar to the one below should appear in a few seconds. Note how the app contains some sample data. (Don't worry about the layout of the GUI<sup>[4](#glossary)</sup> yet! It will be explained in the next section.)
  ![Ui](images/Ui.png)

1. We **strongly recommend** you to use this app at a resolution of 1024x640 or greater to experience the greatest level of comfort. You may also click the fullscreen icon at the top right hand corner of the window next to the close icon to maximise the window.

1. Press your spacebar (recommended), or bring your cursor to the [command box](#command-box) area and click onto it, to access the typing space. The results display will come into view below the command box.

1. Type commands in the [command box](#command-box) and press Enter to execute them. For example, typing **`help`** into the command box and pressing Enter will open the [help window](#viewing-help--help).
 - Some example commands you can try:
  - **`list`** : Lists all contacts.
  - <code><b>add</b> name/John Doe phone/98765432 email/johnd@example.com home/John street, block 123, #01-01 bday/01/01/2000</code> : Adds a contact named `John Doe` to the SectresBook.
  - **`delete 3`** : Deletes the 3rd contact shown in the current list.
  - **`help`** : Opens a [help window](#viewing-help--help).
  - **`clear`** : Deletes all contacts.
  - **`exit`** : Exits the app.
  
1. Once you are done executing commands and want to see the GUI, press the `ESC` key (recommended), or click on anywhere that is not the command box, to leave the typing space and hide the results display.

Refer to [Features](#features) below for details of each command.

[Back to Table of Contents](#table-of-contents)

---

## User Interface

Here is an overview of the User Interface (UI) components.

The UI comprises four sections:

![UILabeled](images/UiLabeled.png)

### Command Box

The Command Box is where you type in your command inputs. For more information on command inputs, refer to [Features](#features) below.

Once the command box is selected, a results display will appear to report the status of the program to you. Error messages and success messages will be shown in this box. Click anywhere else on the screen, or press the `ESC` key to exit the command box and hide the results display.

<p align="center">
  <img src="images/ResultsDisplay.png" width="400">
</p>
<center style="font-size:3mm;">Results Display.</center>

<br/>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You may activate the command box by simply pressing the spacebar on your keyboard. There is no need to use your mouse to click on the bar.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Similarly, you may press the `ESC` key on your keyboard to exit out of the command box and hide the results display.
</div>

[Back to Table of Contents](#table-of-contents)

### People Panel

The People Panel contains all the club and organisation members you have registered in this book. They are laid out horizontally. You can scroll the list by hovering your mouse over the People Panel and scrolling the mouse-wheel, or by clicking on and dragging the horizontal scroll bar to scroll.

Each card represents a person and displays their name, phone number and total present loan amount. The loan amount may be positive to indicate an amount owed by the person, or negative to indicate an amount due to be paid to the person.

<p align="center">
  <img src="images/TypicalPersonCard.png" width="125">
</p>
<center style="font-size:3mm;">A person card.</center>

The index of the person only applies to the currently displayed list, it is **not** tied to the person itself.

Check [Person Features](#person-features) to learn more about the commands you can execute related to people.

[Back to Table of Contents](#table-of-contents)

### Inspect Panel

The Inspect Panel is related to the People Panel and shows the basic information of the currently inspected person. A person can be inspected by either clicking on his or her card, or by using the `inspect` command. More details on the `inspect` command can be found [here](#inspecting-a-person--inspect).

The left side of the Inspect Panel shows the basic information, while the right side shows the history of loan transactions.

Note that the transaction record next to the icon of the hand holding coins is the most recent, and the earlier transactions are listed below.

The total amount of the loans is also stated in the right of this panel, describing in fuller detail if the sum is owed by or to be paid to the person.

[Back to Table of Contents](#table-of-contents)

### Notes Panel

This Notes Panel stores all the information related to notes and tasks that the user may want to keep track of.

Each note contains an index, a title, contents and tags.

<p align="center">
  <img src="images/TypicalNoteCard.png" width="500">
</p>
<center style="font-size:3mm;">A note card.</center>

The index of the notes only applies to the currently displayed list, it is **not** tied to the note itself.

Both the People Panel and Notes Panel share a pool of tags to more easily relate a group of people to a specific note.

Check [Notes Features](#note-features) to learn more about the commands you can execute related to notes.

[Back to Table of Contents](#table-of-contents)

---

## Properties

### Person Properties

<p align="center">
  <img src="images/TypicalPersonCard.png">
</p>
<center style="font-size:3mm;">A typical person card.</center>

#### Name

This is the name of the person to be recorded in the SectresBook. Two persons cannot have exactly the same name, but slight deviations are acceptable. Our team recognizes that many people may share the same name, so it is alright to use close aliases. 

This property<sup>[12](#glossary)</sup> is compulsory to declare during initialisation<sup>[6](#glossary)</sup>.

Only alphanumeric characters<sup>[1](#glossary)</sup> are accepted in this property<sup>[12](#glossary)</sup>. Numbers are accepted in use as disambiguation, but may not exist as a standalone word without alphabets immediately before or following it. See examples below for a more specific overview.

This property<sup>[12](#glossary)</sup> can be identified in the GUI<sup>[4](#glossary)</sup> by the icon of the silhouette of a person.

<img src="images/icons/person.png" width="50" height="50">

- Identified by the prefix `name`.
- This is a valid property<sup>[12](#glossary)</sup> to find a person by using the [`find` command](#locating-persons-by-name-or-contact-number--find).

<table>
  <tr>
    <th>Valid Examples</th>
    <td>
     <code>Samuel West</code><br>
     <code>Jonathan Lee Wen Xin</code><br>
     <code>Jack Robert the 3rd</code><br>
     <code>3Lite M1k0ch1</code><br>
     <code>Elizabeth Wong n11</code>
    </td>
  </tr>
  <tr>
    <th>Invalid Examples</th>
    <td>
     <code>@*)^%</code> (Non-alphanumeric characters are not accepted)<br>
     <code>Jack Robert the 3</code> (Number may not exist as a standalone word)<br>
     <code>Elizabeth Wong 11</code> (Number may not exist as a standalone word)<br>
     <code>Jonathan 25 Chin</code> (Number may not exist as a standalone word)<br>
     <code>(25) Jonathan Chin</code> (Non-alphanumeric characters cannot be used adjacent to numbers)<br>
    </td>
  </tr>
</table>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
It is recommended to include the full name of the person instead of aliases for easier searching.
</div>

[Back to Table of Contents](#table-of-contents)

#### Phone

This is the phone number of the person to be recorded in the SectresBook. It is compulsory to declare this during initialisation<sup>[6](#glossary)</sup>.

This property<sup>[12](#glossary)</sup> can be identified in the GUI<sup>[4](#glossary)</sup> by the icon of a mobile phone.

<img src="images/icons/phone.png" width="50" height="50">

- Identified by the prefix `phone`.
- A phone number should contain only numbers and be at least 3 digits long.
- This is a valid property<sup>[12](#glossary)</sup> to find a person by using the [`find` command](#locating-persons-by-name-or-contact-number--find).

Please record the phone number by which the member is most easily contacted. You are not allowed to enter multiple phone numbers in this field.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Ensure that no two persons have the same phone number! This is allowed in the program, but you may have difficulties contacting the person you want in the future.
</div>

[Back to Table of Contents](#table-of-contents)

#### Email

This is the email address of the person to be recorded in the SectresBook. It serves mainly as a point of information regarding the person, and has no additional features tied to it. This property<sup>[12](#glossary)</sup> is compulsory to declare during initialisation<sup>[6](#glossary)</sup>.

This property<sup>[12](#glossary)</sup> can be identified in the GUI<sup>[4](#glossary)</sup> by the icon of an envelope.

<img src="images/icons/mail.png" width="50" height="50">

- Identified by the prefix `email`.
- Emails should be of the format `local-part@domain` and adhere to the following constraints:

| Part | Constraint  |
|------|-------------|
| Local part | The local-part should only contain alphanumeric characters<sup>[1](#glossary)</sup> and these special characters `_`, `.`, `+` and `-`. <br><br>The local-part may not start or end with any special characters and special characters may not be adjacent to each other.                                                                              
 Domain name | The domain name is made up of domain labels separated by periods. The domain name must:<br>- end with a domain label at least 2 characters long <br>- have each domain label start and end with alphanumeric characters<sup>[1](#glossary)</sup> <br> - have each domain label consist of alphanumeric characters<sup>[1](#glossary)</sup>, separated only by hyphens, if any.  

The local part and domain part **must** be connected by an `@` symbol.

[Back to Table of Contents](#table-of-contents)

#### Address
This is the residing address of the person. It serves mainly as a point of information regarding the person, and has no additional features tied to it. This property<sup>[12](#glossary)</sup> is compulsory to declare during initialisation<sup>[6](#glossary)</sup>.

This property<sup>[12](#glossary)</sup> can be identified in the GUI<sup>[4](#glossary)</sup> by the icon of a house.

<img src="images/icons/home.png" width="50" height="50">

- Identified by the prefix `home`.
- There is no constraint on how the home address of a person should be written, as long as it is sufficiently understandable.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Please ensure that your SectresBook window can accommodate the length of the text. If you find the text being cut off and see ellipses `...` showing, please resize the SectresBook window to fit the text.
</div>

[Back to Table of Contents](#table-of-contents)

#### Loan
This is amount of money that is owed by a person, or is to be paid to that person. This is a property<sup>[12](#glossary)</sup> that cannot be manipulated directly, but can only be edited with the [`editLoan` command](#editing-loan-of-a-person--editloan).

This property<sup>[12](#glossary)</sup> can be identified in the GUI<sup>[4](#glossary)</sup> by the icon of the hand holding two stacks of coins.

<img src="images/icons/loan.png" width="50" height="50">

- A loan amount can be either negative, positive or zero.
    + A positive value indicates an amount that the person has yet to pay to the organisation.
    + A zero value indicates no outstanding loan.
    + A negative value indicates an amount that should be paid back to the person.
- Loans can only take up numerical values.
- The maximum value this property<sup>[12](#glossary)</sup> can take is `$1,000,000,000,000.00` (1 trillion dollars ). Similarly, the minimum value is `-$1,000,000,000,000.00` (negative 1 trillion dollars). This should be more than sufficient for general purpose use.

[Back to Table of Contents](#table-of-contents)

#### Loan History
A loan history is linked to the loans properties and describes the changes to the numeric values of the loans in detail. There is no way to declare this during initialisation<sup>[6](#glossary)</sup>, but it can be modified by the [`editLoan` command](#editing-loan-of-a-person--editloan).

This property<sup>[12](#glossary)</sup> is represented as a descending list on the right side of the inspection panel.

![img.png](images/LoanHistoryPanel.png)

The most recent transaction is recorded at the top, on the row next to the loans icon.

The upward pointing red arrow signifies an amount loaned to this member in a transaction.

<img src="images/icons/increase_arrow.png" width="50" height="50">

The downward pointing green arrow signifies an amount that this member has paid back in a transaction.

<img src="images/icons/decrease_arrow.png" width="50" height="50">

- Consists of the following sub-properties:
    - Current loan value
    - Change in amount from last value
    - Reason for change

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Including a reason for every change to a person's loan value reduces the risk of accidentally adding an incorrect amount to someone. It keeps a detailed tab of every increment and decrement in value.
</div>

[Back to Table of Contents](#table-of-contents)

#### Birthday
The birthday date of a person. This property<sup>[12](#glossary)</sup> is compulsory to declare during initialisation<sup>[6](#glossary)</sup>.

This property<sup>[12](#glossary)</sup> can be identified in the GUI<sup>[4](#glossary)</sup> by the icon of a birthday cake.

<img src="images/icons/birthday.png" width="50" height="50">

- Identified by the prefix `bday`.
- Serves as a point of information for a person.
- This must be a valid date in the form `dd/MM/yyyy`.

[Back to Table of Contents](#table-of-contents)

#### Tags
Persons can be linked to tag objects, which serve as markers that draw connections between different people as well as associated notes. This property<sup>[12](#glossary)</sup> is completely optional.

This property<sup>[12](#glossary)</sup> can be identified in the GUI<sup>[4](#glossary)</sup> by tag shaped labels with text inside them. They are usually located at the bottom right of each person card. If there is no such label, then it means this person has no associated tags.

![img.png](images/PersonTags.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Choose short, identifiable tag names.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:** 
There is no limit to how many tags you can apply to an individual person, but applying too many tags increases confusion and decreases user experience. Please choose the most relevant tags to apply only.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Please refer to the [`Tag Properties`](#tag-properties) below for more information regarding tags.
</div>

[Back to Table of Contents](#table-of-contents)

### Notes Properties

<p align="center">
  <img src="images/TypicalNoteCard.png">
</p>
<center style="font-size:3mm;">A typical note card.</center>

#### Title
The title serves as the main marker for notes and summarises the important details of this specific note. This property<sup>[12](#glossary)</sup> is compulsory to declare during initialisation<sup>[6](#glossary)</sup>.

It is the first line of a note card, which is bigger than the rest of the text.

Special characters are allowed in the title, but they will always be treated as a whitespace<sup>[17](#glossary)</sup> during operations. This is because punctuation is not normally a part of a word. This feature is relevant to [`findNote`](#locating-a-note-by-title--findnote).

- This property<sup>[12](#glossary)</sup> is identified by the prefix `title`.
- Notes can be filtered through with the [`findNote` command](#locating-a-note-by-title--findnote) using the title property<sup>[12](#glossary)</sup>.
- Titles must be within 100 characters and can contain any ASCII characters<sup>[2](#glossary)</sup>.
- This property<sup>[12](#glossary)</sup> cannot be left empty.

[Back to Table of Contents](#table-of-contents)

#### Content
The content serves as the description for notes. This property<sup>[12](#glossary)</sup> is compulsory to declare during initialisation<sup>[6](#glossary)</sup>.

This is immediately below the title and is in a smaller font size.

- This property<sup>[12](#glossary)</sup> is identified by `content`.
- This property<sup>[12](#glossary)</sup> cannot be left empty.

[Back to Table of Contents](#table-of-contents)

#### Tags

Notes can be linked to tag objects, which serve as markers that draw connections between different people as well as associated notes. This is an optional property<sup>[12](#glossary)</sup> for all notes.

This is located at the bottom right of the notes card. If there is no such label, then it means this note has no associated tags.

![img.png](images/NoteTags.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Choose short, identifiable tag names.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Please refer to the [`Tag Properties`](#tag-properties) section below for more information regarding tags.
</div>

[Back to Table of Contents](#table-of-contents)

### Tag Properties
A tag is used to group together specific People and Notes.

This way, searching for a tag brings up all the People and Notes that have the tag for easier classification of related information.

- Identified by the prefix `tag`.
- Tags can only consist of alphanumeric characters<sup>[1](#glossary)</sup>.
- Persons and Notes can hold tags.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

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
  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/friend`, `tag/friend tag/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `name/NAME phone/PHONE_NUMBER`, `phone/PHONE_NUMBER name/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `phone/12341234 phone/56785678`, only `phone/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Person Features

#### Adding a new person : `add`

Adds a person to the SectresBook. The new member will appear at the end of the persons' list residing in the People Panel.

Format: `add name/NAME phone/PHONE_NUMBER email/EMAIL home/ADDRESS bday/BIRTHDAY [tag/TAG]...​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0).
</div>

Examples:
* `add name/John Doe phone/98765432 email/johnd@example.com home/John street, block 123, #01-01 bday/01/04/2010 tag/Member`
* `add name/Jane Doe phone/98876542 email/jane@example.com home/That Street, block 133, #11-10 bday/05/11/1986 tag/Member`
* `add name/Neethesh tag/VicePresident email/neethesh@example.com home/Happy Avenue phone/91234567 bday/24/05/1998`

[Back to Table of Contents](#table-of-contents)

#### Editing a club member : `edit`

Edits an existing club member’s information in the SectresBook.

Format: `edit INDEX <OR> NAME [name/NAME] [phone/PHONE] [email/EMAIL] [home/ADDRESS] [bday/BIRTHDAY] [tag/TAG]…​`

Remember that all terms are optional for edit commands, but it must include at least one property<sup>[12](#glossary)</sup> to edit the person by.

If ambiguities by the keywords given exist, the persons' list will auto-filter to display all matching persons that cause the ambiguity to arise. Please fine tune your search requirements, or use an index from here, to edit the person again.

Example of usage:

`edit 1 phone/99999999` can be used to easily update the first person's contact information.

`edit John phone/91235555` can be used to update a person’s contact information if there exists only one person whose name contains John.<br>

<div markdown="span" class="alert alert-info">:information_source: **Note:**
If no person is named `John`, or if more than one person has `John` in their name, then the operation is equivalent to `find John`.
</div>

[Back to Table of Contents](#table-of-contents)

#### Editing loan of a person : `editLoan`

Edits an existing club member's loan amount in the SectresBook. Please note that the value must be either positive or negative values with up to 2 decimal places.

The absolute maximum amount for a loan is `$1,000,000,000,000.00` (positive or negative 1 trillion). If you are intending to file more than a trillion dollars in total transactions, this application may not be a suitable one for you, as our expected clients do not normally transfer this much money.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If the total amount after the `editLoan` command adds up to more than a trillion, the program will block the command from going through. You will be notified by the results display should such a command be attempted.
</div>

Format: `editLoan INDEX <OR> NAME amt/VALUE reason/REASON`

* Edits the loan value of the existing person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1,2,3 …​
* The `VALUE` can be a positive or negative value with up to 2 decimal places.
* The `VALUE` may not contain delimiters to separate digits, i.e. `$1,000,000` must be expressed as `$1000000` in the program.
* The loan value will be changed by the value given i.e current loan + `VALUE`.

Examples of usage:

* `editLoan 2 amt/30 reason/bought logistics`
* `editLoan alex amt/-30 reason/return money from logistics`
* `list` followed by `editLoan 1 amt/-20 reason/return money` will edit the 1st person in the SectresBook, reducing their loan by $20 and saving the `REASON` as `return money`.
  
<div markdown="span" class="alert alert-info">:information_source: **Note:**
In the second example, if no person is named `Alex`, or if more than one person has `Alex` in their name, then the operation is equivalent to `find alex`.
</div>

[Back to Table of Contents](#table-of-contents)

#### Deleting a person : `delete`

Deletes the specified person from the SectresBook. Delete commands are irreversible, so please ensure that you are deleting the correct person.

Format: `delete INDEX <OR> NAME`

<table>
 <tr>
  <th>
   Deleting by <b>INDEX</b>
  </th>
  <td>
   <ul>
    <li>Deletes the person at the specified <code>INDEX</code>.</li>
    <li>The index refers to the index number shown in the displayed person list.</li>
    <li>The index <b>must be a positive integer</b> 1, 2, 3, …​</li>
   </ul>
  </td>
 </tr>
 <tr>
  <th>
   Deleting by <b>NAME</b>
  </th>
  <td>
   <ul>
    <li>Delete the entry of the person by name with the given keyword, only if the keywords specified are unique to that person's name.</li>
    <li>Will not perform any operation if the name of the person does not exist, but instead execute <code>find NAME</code></li>
    <li>If the SectresBook contains more than one person that can be found by the keyword specified, the delete command will not execute but will return a list of all people with the given name. From here, you may choose to delete by index.</li>
   </ul>
  </td>
 </tr>
</table>

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the SectresBook.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
* `delete Betsy` deletes the entry belonging to Betsy in the SectresBook.
* `delete Lynette` does not perform any operation, if Lynette does not exist in the SectresBook.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To delete everyone at the same time, please refer to the <a href="#clearing-all-entries--clear"><code>clear</code> command</a>.
</div>

[Back to Table of Contents](#table-of-contents)

#### Locating persons by name or contact number : `find`

Finds persons whose names match any of the given keywords, or persons whose phone numbers start with any of the given keywords (in digits).

<div markdown="span" class="alert alert-info">:information_source: **Note:**
The People Panel will show a `FILTERED` indicator to inform you that the list has been filtered.
</div>

<img src="images/PeopleFilteredIcon.png" width="256">

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is not case-sensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* Phone numbers starting with any of the given keywords (in digits), of at least 2 digits, will be returned.

Examples:

* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  <img src="images/findAlexDavidResult.png" width="400">


* `find 86` returns `Theodore`<br>
  <img src="images/find86result.png" width="400">
  

* `find 8` will not be accepted as a command, as searching by phone numbers must be of at least 2 digits.

[Back to Table of Contents](#table-of-contents)

#### Listing all persons : `list`

List of all persons in the SectresBook. 

<div markdown="span" class="alert alert-info">:information_source: **Note:**
If the list was formerly filtered, the filter icon to the right of the person's label will disappear.
</div>

Format: `list`

[Back to Table of Contents](#table-of-contents)

#### Sorting by property : `sort` `[coming in v2.0]`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command does not exist in the present version of the program that this User Guide is written for. It **will not work** if entered.
</div>

Sorts the members in the persons' list by properties in either ascending or descending order.

[Back to Table of Contents](#table-of-contents)


### Note Features

#### Adding Notes : `addNote`

Adds a note to the SectresBook.

Format: `addNote title/TITLE content/CONTENT [tag/TAG]... `

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
TITLE must be unique and not longer than 100 characters. Tags are also optional.
</div>

Examples:
* `addNote title/Club meeting soon! content/Remind club members to attend meeting.`
* `addNote title/T-Shirt payment due content/Collect money tag/Juniors`

[Back to Table of Contents](#table-of-contents)

#### Editing Notes : `editNote`

Edits an existing specified note in the SectresBook.

Format: `editNote INDEX <OR> TITLE [title/TITLE] [content/CONTENT] [tag/TAG]...`

Example of usage:

* `editNote 1 content/Second club meeting` can be used to easily update the first note's contents.
* `editNote alumni title/2020 alumni meeting` can be used to amend a note with the title "2020 alumni meeting", only if it is the only note containing "alumni" in its title.

[Back to Table of Contents](#table-of-contents)

#### Deleting Notes : `deleteNote`

Deletes the specified note from the SectresBook. This operation is irreversible, so please ensure that the note you are about to delete is the correct one.

Format: `deleteNote INDEX`

* Deletes the note at the specified `INDEX`.
* The index refers to the index number shown in the displayed note list.
* The index **must be a positive integer** 1, 2, 3, …​

Unlike other entities, you may not delete a note by name. This is to minimise errors in deletion as many notes may have similar names.

Examples:
* `listNotes` followed by `deleteNote 2` deletes the second note in the SectresBook.

[Back to Table of Contents](#table-of-contents)

#### Locating a note by title : `findNote`

Finds the notes whose titles match any of the given keywords.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
The Notes Panel will show a `FILTERED` indicator to inform you that the list has been filtered.
</div>

<img src="images/NotesFilteredIcon.png" width="256">

The search is not case-sensitive. For example, `meeting` will match any notes containing the word `Meeting`. As in find for persons, the order of the keywords also does not matter, and `Meeting Club` will also match a note called `Club Meeting`.

Please be informed that only the title will be search. Contents will not be searched through this command. 

Although the title may contain special characters such as `,`, `.`, `?` or `!`, these special characters are not accepted as part of a keyword. This is because punctuation are not normally associated as part of a word. During the parsing of the [title](#title), special characters are treated as spaces, so adjacent segments of the word containing it are treated as separate words.
  * The keywords `?!` and `t-shirt` will not be allowed, as they contain special characters.
  * `2` will match `shirt 2` but will not match `shirt2`, as `shirt2` is an entire word by itself.
  * To search for `Upcoming club meeting!`, `meeting!` is not allowed as a keyword as it contains a special character, but `meeting` is allowed.
  * `tshirt` will not match `t–shirt` as `t-shirt` is now treated as two words, `t` and `shirt` with the special character `-` being treated as a spacing.

Format: `findNote KEYWORD [MORE_KEYWORDS]`

Examples:
* `findNote Meeting` returns `Club Meeting`, `Meeting!` and `Meeting 2`
* `findNote Soon` returns `Payment (soon)`

[Back to Table of Contents](#table-of-contents)

#### Listing Notes : `listNote`

Shows a list of all notes in the SectresBook. 

<div markdown="span" class="alert alert-info">:information_source: **Note:**
If the notes list was previously filtered, the filter icon will disappear to indicate that you are now looking at the full list.
</div>

This command, unlike many others, does not take in any parameters<sup>[10](#glossary)</sup>.

Format: `listNote`

Examples:
* `listNote`

[Back to Table of Contents](#table-of-contents)

#### Sorting Notes : `sortNotes` `[coming in v2.0]`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command does not exist in the present version of the program that this User Guide is written for. It **will not work** if entered.
</div>

Sorts the notes in either ascending or descending order by title.

[Back to Table of Contents](#table-of-contents)

#### Hiding notes panel : `hideNotes`

Hides the notes panel to the right side of the screen if visible, otherwise, no operation is performed. Use this command if you do not wish to see the notes view while you are working with information related to the members only.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Even though the notes panel may be hidden, operations on notes will still work as normal. This is purely a visual feature.
</div>

Format: `hideNotes`

Example:

Initial view:
![before hiding note](images/hideNotes-before.png)

After executing `hideNotes`:
![after hiding notes](images/hideNotes-after.png)

[Back to Table of Contents](#table-of-contents)

#### Showing notes panel : `showNotes`

Slides the notes panel into view if hidden, otherwise, no operation is performed.

Format: `showNotes`

Example :

Initial view:
![before showing notes](images/showNotes-before.png)

After executing `showNotes`:
![after showing notes](images/showNotes-after.png)

[Back to Table of Contents](#table-of-contents)

<div markdown="span" class="alert alert-info">:information_source: **Note:**<br>
**Q** Why are `hideNotes` and `showNotes` in plural, but the rest of the operations in singular nouns?<br><br>
**A** `hideNotes` and `showNotes` are a separate class of commands that are _UI-Centric_, meaning that they only operate on the user interface, as they only shift a panel in the UI, and do not modify any underlying data of the application. The pluarity of the command disambiguates its usage from the other commands that mutates data. For more information, please read our developer guide.

<p>To ease interpretation, you may read `showNotes` as _"show the **notes panel**"_ or `hideNotes` as _"hide the **notes panel**"_, while `listNote` may be read as _"list each individual **note**"_, `findNote` as _"find each **note** matching"_ and `editNote` as _"edit this **note**"_.
</div>

### General Features

#### Locating persons and notes by tag : `findTag`

Finds People and Notes that have the given tags. Both the People Panel and Notes Panel will be updated synchronously with all entities that match the specifiers.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Both the People Panel and Notes Panel will show a `FILTERED` indicator to inform you that both lists have been filtered.
</div>

Format: `findTag TAG [MORE_TAGS]`

* The tag search is not case-sensitive. e.g `finance` will match `Finance`
* Only tags are searched.
* Only full words will be matched e.g. `Tech` will not match `Technology`
* Persons and Notes matching at least one tag will be returned (i.e. `OR` search).
  e.g. `Operations Finance` will return
  * Person `Alex Yeoh` (tag: Friends) (tag: Operations),
  * Person `Charlotte Oliveiro` (tag: Colleagues) (tag: Finance),
  * Note `Collect funds from operations team` (tag: Operations)

Examples:
* `findTag Operations Finance` returns Person `Alex Yeoh`, Person `Charlotte Oliveiro` and Note `Collect funds from operations team`
  ![result for 'findTag Operations Finance'](images/findTagOperationsFinance.png)

[Back to Table of Contents](#table-of-contents)

#### Inspecting a person : `inspect`

Updates the Inspect Panel with the basic information and loan history of the person inspected.

Inspection is a _UI-centric_ command that operates on the currently viewed person’s list, so you may only inspect those that are presently listed.

If you wish to view the properties of anyone in the full list, please remember to specify `list` to clear the filter.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
If there are multiple people in the list satisfying the keywords given, it will, by default, inspect the first person that matches the keywords.

You may wish to use more unique keywords to reduce ambiguity, or inspect by an index.
</div>

Format: `inspect INDEX <OR> NAME`

Examples:
* `inspect 2` inspects the second person in the list of people
* `inspect Lynette` will attempt to find the first person called `Lynette` in the currently **filtered** persons' list and update the Inspect Panel with her information.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If you are trying to inspect a person by name, who does not exist in the currently viewed person list, which may be filtered, it will not be successful. This is because `inspect` only works on the currently viewed list.
</div>

[Back to Table of Contents](#table-of-contents)

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Copy the link to your clipboard by clicking the `Copy URL` button and paste it in your favourite browser to come back to this page anytime.

Format: `help`

[Back to Table of Contents](#table-of-contents)

#### Clearing all entries : `clear`

Clears all entries of people and notes from the SectresBook. This command is irreversible. This effectively restarts the app from a blank slate.

Format: `clear`

[Back to Table of Contents](#table-of-contents)

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Your data is saved automatically.
</div>

[Back to Table of Contents](#table-of-contents)

#### Saving the data

SectresBook data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Back to Table of Contents](#table-of-contents)

#### Editing the data file

SectresBook data is saved as a JSON file<sup>[8](#glossary)</sup> at `[JAR file location]/data/sectresbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SectresBook will discard all data and start with an empty data file at the next run.
</div>

[Back to Table of Contents](#table-of-contents)

#### Archiving data files `[coming in v2.0]`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command does not exist in the present version of the program that this User Guide is written for. It **will not work** if entered.
</div>

Copies the current data file as a new file in the `HOME_FOLDER_LOCATION/archives` folder. This file may be used as a saved checkpoint to load past information into the SectresBook.

[Back to Table of Contents](#table-of-contents)

#### Undo `[coming in v2.0]`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command does not exist in the present version of the program that this User Guide is written for. It **will not work** if entered.
</div>

Undoes the last command that the user has inputted, setting the state of the saved data to an earlier state.

[Back to Table of Contents](#table-of-contents)

#### Redo `[coming in v2.0]`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command does not exist in the present version of the program that this User Guide is written for. It **will not work** if entered.
</div>

Pushes the state of the program to the later state if it was set back to an earlier state by the `undo` command. If the present state is the most recent state, no operation is performed.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Note that performing undo commands and inputting a new command after will set the present state to the most recent state. No redo commands may be performed subsequently.
</div>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## FAQ

[//]: # (**Q** I work in a highly classified/important organisation and we have large volumes of monetary transactions and tasks we need to keep track of. Can we use this version of the program to manage our operations?<br>)

[//]: # (**A** No, the current version of _SectresBook_ is not designed for such intensive and critical operations. It does not support any form of encryption or security and its performance has not been tested rigorously against larger loads. We ***do not suggest*** using this program in places where information loss or leaks may result in catastrophic consequences.)

**Q** I cannot see any images in this document, what is wrong?<br>
**A** You are likely viewing the document in dark mode. The images are in black as their main color, please view this document in light mode instead.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the previous SectresBook data file.

**Q**: Double-clicking the jar file does not open the jar file, why does this happen?<br>
**A**: Make sure that Java 11<sup>[7](#glossary)</sup> is installed on your computer. You may also open the terminal<sup>[15](#glossary)</sup> and type in `java -jar SectresBook.jar`.

**Q** Do I need to have Java installed to run SectresBook?<br>
**A** Yes, SectresBook runs on Java and would require it to work. For more information on how to install Java 11<sup>[7](#glossary)</sup>, visit this [website](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html).

**Q** What can I can do if the window size is too small?<br>
**A** Drag the window of the application with your mouse to enlarge it, or simply click the top right maximise icon of the window.

**Q** Why can't I see the full address of the person I am inspecting?<br>
**A** Your window is too small to accommodate the full label text, please resize your window either wider or taller so that the full text can be displayed.

**Q** I have entered a command, but there is no response from the program, why did this happen?<br>
**A** A critical error may have occurred. You may likely be able to continue your usage of the program. Please review your terminal<sup>[15](#glossary)</sup> output and file a bug report on our [issues page](https://github.com/AY2223S1-CS2103T-W12-2/tp/issues), detailing a description of the bug as well as the steps to reproduce, attaching screenshots if any.

**Q** Do I require an internet connection to use SectresBook?<br>
**A** No, SectresBook fully works without the need for internet connection.

**Q** I have an existing data file but the app does not show any of the information, what is wrong?<br>
**A** Your data file is corrupted. If you know how to, please open the JSON data file<sup>[8](#glossary)</sup> in a text editor and remove the offending data. Otherwise, please refer to someone who knows how to remedy a corrupted data file.

**Q** Who can I contact for questions regarding this version of the program if the information I need cannot be found in this guide?<br>
**A** Please contact the person from whom you obtained this present version from, or contact any of our developers (links to Github):

<table>
  <tr>
    <td> <a href="https://github.com/rui-han-crh">Rui Han</a> </td>
    <td> <a href="https://github.com/Pinran-J">Pinran</a> </td>
    <td> <a href="https://github.com/ryanczx">Ryan</a> </td>
    <td> <a href="https://github.com/Neethesh26">Neethesh</a> </td>
    <td> <a href="https://github.com/czhongwei">Zhong Wei</a> </td>
  </tr>
</table>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Glossary
The definitions in this glossary are context-specific to this application.

No. | Word                             | Definition
---|----------------------------------|------------------
1 | **Alphanumeric Characters**      | A combination of alphabetical and numerical characters
2 | **ASCII Characters**             | Characters from the American Standard Code for Information Interchange character encoding standard
3| **Command Line Interface (CLI)** | A text-based interface that receives typed commands as input and returns textual feedback as output.
4| **Graphical User Interface (GUI)** | An image-based interface that is more visually appealing than a command-line interface and encapsulates information through the use of icons and images.
5| **Home Folder**                  | The folder that SectresBook is located in. It is also where SectresBook will store its data
6|**Initialisation** | The moment when a Person or Note is created and added into SectresBook.
7| **Java 11**                      | Eleventh version of the Java Platform, Standard Edition Development Kit (JDK). SectresBook requires this to be installed to run.
8| **JSON data file**               | A data file that uses the JavaScript Object Notation format.
9| **Loan**                         | An amount of money that is borrowed by or owed to a person. A positive value signifies an amount owed by the person and a negative value signifies an amount to be paid to that person.
10| **Parameter**                    | A value passed as a section of a command, typically following a prefix.
11| **Prefix**                       | A signposting word that indicates the kind of property (i.e. name, email, address, etc), which typically follows immediately after the prefix, that is to be passed as a parameter.
12| **Property**                     | An identifiable feature a person or object has that sufficiently distinguishes it from other objects of the same kind.
13| **Secretary**                    | A person that manages the tasks and events related to the operations of an organisation.
14| **Tag**                          | A label that groups related people together, such that they can be referred to as a single encapsulated entity specified by the tag.
15| **Terminal**                     |  A text-based interface to the computer
16| **Treasurer**                    | A person that manages the finances and monetary transactions related to the operations of an organisation.
17| **Whitespace**                   | A space character <code> </code>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Summary

### Person Properties

 <table>
   <tr>
    <th colspan="3">Person Properties</th>
   </tr>
   <tr>
    <th></th>
    <th>Properties</th>
    <th>Examples</th>
   </tr>
   <tr>
     <th rowspan="5">Compulsory Properties</th>
     <td>Name</td>
     <td><code>Captain Daniel Patrick the 5th</code></td>
   </tr>
   <tr>
     <td>Phone</td>
     <td><code>99123510</code></td>
   </tr>
   <tr>
     <td>Address</td>
     <td><code>Canopy Street 11 Blk 709, #20-112</code></td>
   </tr>
   <tr>
     <td>Email</td>
     <td><code>capt_daniel.patrick-the+5th@example.com</code></td>
   </tr>
   <tr>
     <td>Birthday</td>
     <td><code>09/12/2000</code></td>
   </tr>
   <tr>
     <th rowspan="3">Optional Properties</th>
     <td>Loan</td>
     <td>Cannot be declared, used by loan history</td>
   </tr>
   <tr>
     <td>Loan History</td>
     <td>An amount of <code>$55</code> with a reason of <code>"To buy a porcelain vase"</code></td>
   </tr>
   <tr>
     <td>Tags</td>
     <td><code>Coworkers</code>; <code>Friends</code>; <code>Operations</code></td>
   </tr>
 </table>

[Back to Table of Contents](#table-of-contents)

### Person Commands

Action | Format | Examples
--------|----------------------|--------
**Add a person** | `add name/NAME phone/PHONE_NUMBER email/EMAIL home/ADDRESS bday/BIRTHDAY [tag/TAG]…​`                | `add name/James Ho phone/22224444 email/jamesho@example.com home/123, Clementi Rd, 1234665 bday/01/01/2000 tag/friend tag/colleague`
**Edit a person** | `edit INDEX <OR> NAME [name/NAME] [phone/PHONE] [email/EMAIL] [home/ADDRESS] [bday/BIRTHDAY] [tag/TAG]…​` | `edit 2 name/James Lee email/jameslee@example.com`
**Edit the loan of a person** | `editLoan INDEX <OR> NAME amt/VALUE reason/REASON`                                                            | `editLoan 1 amt/-20 reason/Buy Logistics`
**Delete a person** | `delete INDEX <OR> NAME`                                                                      | `delete 3` <br> `delete Jane`
**Find a person** | `find KEYWORD [MORE_KEYWORDS]`                                                    | `find James Jake` <br> `find 8651`
**List every person** | `list`                                                                                               | `list`

[Back to Table of Contents](#table-of-contents)

### Notes Properties

 <table>
   <tr>
    <th colspan="3">Notes Properties</th>
   </tr>
   <tr>
    <th></th>
    <th>Properties</th>
    <th>Examples</th>
   </tr>
   <tr>
     <th rowspan="2">Compulsory Properties</th>
     <td>Title</td>
     <td><code>Organise a charity to raise money for the foundling hospital</code></td>
   </tr>
   <tr>
     <td>Content</td>
     <td><code>Target $50,000 - by 23rd of December. We're aiming to attract at least 1000 contributors.</code></td>
   </tr>
   <tr>
     <th>Optional Properties</th>
     <td>Tags</td>
     <td><code>Coworkers</code>; <code>Friends</code>; <code>Operations</code></td>
   </tr>
 </table>
 
[Back to Table of Contents](#table-of-contents)

### Notes Commands

Action | Format | Examples
--------|-------------------|-------------
**Add a Note** | `addNote title/TITLE content/CONTENT [tag/TAG]...`                      | `addNote title/Create Excel Sheet content/Create sheet for blockchain department`
**Edit a Note** | `editNote INDEX <OR> TITLE [title/TITLE] [content/CONTENT] [tag/TAG]...` | `editNote 1 title/Check meeting availability tag/president`
**Delete a Note** | `deleteNote INDEX`                                                      | `deleteNote 1`
**Find a Note** | `findNote KEYWORD [MORE_KEYWORDS]`                                      | `findNote meeting`
**List Every Note** | `listNote`                                                              | `listNote`
**Hide Notes Panel** | `hideNotes`              | `hideNotes`
**Show Notes Panel** | `showNotes`              | `showNotes`

[Back to Table of Contents](#table-of-contents)

### General Commands

Action | Format                    | Examples
-------|---------------------------|----------|
**Find Tag** | `findTag TAG [MORE_TAGS]` | `findTag Operations Outreach`
**Inspect** | `inspect INDEX <OR> NAME` | `inspect 1` or `inspect Alex`
**Help** | `help`                    | `help`
**Clear all data** | `clear`                   | `clear`
**Exit** | `exit`                    | `exit`

[Back to Table of Contents](#table-of-contents)
