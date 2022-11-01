---
layout: page
title: User Guide
---
<h1 align="center">:book: Welcome to CLInkedIn User Guide!</h1>

Welcome to the CLInkedIn User Guide! If you are a recruiter who is using CLInkedIn, or someone who is curious to find out more about CLInkedIn and its features, look no further!

In this user guide, you will find instructions on how to install CLInkedIn and a guide on how to use CLInkedIn's numerous features.

💻 Are you a Developer? Do check out the [CLInkedIn Developer Guide](https://ay2223s1-cs2103t-t13-3.github.io/tp/DeveloperGuide.html) for more technical information!

## Table of Contents

1. [Using this Guide](#using-this-guide)
2. [Introduction to CLInkedIn](#introduction)
3. [Useful Notation](#useful-notations)
4. [Quick start](#quick-start)
5. [Features](#features)
   1. [Viewing help `help`](#viewing-help--help)
   2. [Adding/Deleting contacts in CLInkedIn](#addingdeleting-contacts-in-clinkedin)
      1. [Adding a person `add`](#adding-a-person-add)
      2. [Deleting a person `delete`](#deleting-a-person--delete)
   3. [Adding/Editing/Deleting Tag Types in CLInkedIn](#addingdeleting-contacts-in-clinkedin)
      1. [Creating a custom tag type `createTagType`](#creating-a-custom-tag-type-createtagtype)
      2. [Editing tag type name `editTagType`](#editing-tag-type-name-edittagtype)
      3. [Deleting an existing tag type `deleteTagType`](#deleting-an-existing-tag-type-deletetagtype)
   4. [Modifying contacts in CLInkedIn](#modifying-contacts-in-clinkedin)
      1. [Editing a person `edit`](#editing-a-person--edit)
      2. [Adding an optional tag `addTag`](#adding-a-tag-to-an-existing-person--addtag)
      3. [Deleting an optional tag `deleteTag`]([#deleting-a-tag-of-an-existing-person--deletetag)
      4. [Adding optional information `addNote`](#adding-optional-information-addnote)
      5. [Deleting optional information `deleteNote`](#deleting-optional-information-deletenote)
      6. [Adding optional rating `addRate`](#adding-optional-rating-addrate)
      7. [Sorting candidates based on rating `sort`](#sorting-candidates-based-on-rating-sort)
      8. [Adding optional links `addLink`](#adding-optional-links-addlink)
      9. [Deleting links `deleteLink`](#deleting-links-deletelink)
   5. [Modifying contacts view in CLInkedIn](#modifying-contacts-view-in-clinkedin)
      1. [Viewing the details of a person `view`](#viewing-the-details-of-a-person--view)
      2. [Listing all persons `list`](#listing-all-persons--list)
      3. [Finding personal information and tags `find`](#finding-personal-information-and-tags-find)
      4. [Clearing all contacts `clear`](#clearing-all-contacts--clear)
      5. [Undoing previous command `undo`](#undoing-previous-command-undo)
      2. [Redoing previous command `redo`](#redoing-previous-command-redo)
      6. [Viewing statistics `stats`](#viewing-statistics-based-on-ratings-of-candidates-stats)
      7. [Importing contacts `import`](#importing-an-addressbook-import)
      8. [Exporting contacts `export`](#importing-an-addressbook-import)
      9. [Exiting CLInkedIn `exit`](#exiting-clinkedin--exit)
   6. [Miscellaneous Features/Commands](#miscellaneous-featurescommands)
      5. [Saving the data](#saving-the-data)
      6. [Editing the data file](#editing-the-data-file)
6. [FAQ](#faq)
7. [Command Summary](#command-summary)

## Using this guide

If this is the first time you are viewing this user guide, do read the section on [Introduction to CLInkedIn](#introduction). Otherwise,
- If you are just setting up, please take a look at [Quick start](#quick-start).
- If you are unsure of how to use CLInkedIn, the [Command Summary](#command-summary) table is a good place to start. 
- If you are a developer and want to help out, please take a look at the [CLInkedIn Developer Guide](https://ay2223s1-cs2103t-t13-3.github.io/tp/DeveloperGuide.html).
 

## Introduction
![Ui](images/Ui.png)

CLInkedIn is a **desktop address book application made for Recruiting and Hiring Managers to keep track of candidates and their job applications.** The application is optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI).

If you can type fast, CLInkedIn can get your recruitment tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------
## Useful Notations

While exploring CLInkedIn's features with this user guide, do take note of these symbols used in the user guide and what information they represent.

| Symbol | Meaning |
| :----: | ------- |
| :information_source: | Important information |
| :exclamation: | Warning or caution |
| :bulb: | Additional information such as tips or notes |
| :wrench: | Help with common technical issues |

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java [`11`](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) or above installed in your Computer.

1. Download the latest `CLInkedIn.jar` from [here](https://github.com/AY2223S1-CS2103T-T13-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your `CLInkedIn` application.

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

- Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

- The command word is case-insensitive.<br>
  e.g. `add` is the same as `ADD` or `aDd`.

</div>

## Viewing help : `help`

Shows a message explaining how to access the help page, as well as a list of commands and their descriptions.
Alternatively, you can obtain help on a specific command by typing `help` followed by the command name.

![help message](images/helpMessage.png)

Format:

- `help` will open the help window in a separate window containing a list of commands and their usages.
- `help COMMAND` will display the command usage for COMMAND in the result display.

<div markdown="span" class="alert alert-warning">:exclamation: **Warning:**
`help` will automatically parse any inputs given after the command word `help` as a command name. If the command name is invalid, an error message will be displayed in the result display.
</div>

Examples:

- `help` will open the help window.
- `help add` will display the usage of the `add` command.
- `help 123` will display an error message as `123` is not a valid command name.

## Adding/Deleting contacts in CLInkedIn

The following functions deal with adding or deleting contacts from your address book in CLInkedIn.

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/STATUS [note/NOTE] [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​ [rate/RATING] [l/LINK]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can have any number and any kind of tags (including 0).
</div>

- By default, you can add 3 types of tags - `SKILL_TAG`, `DEGREE_TAG`, `JOB_TYPE_TAG`.

- Alternatively, you can create your own custom tag type and alias for it. See [`createTagType`](#creating-a-custom-tag-type-createtagtype) command.

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/OA in Progress st/Java dt/Bachelors jt/Internship rate/4`

* `add n/Betsy Crowe st/java e/betsycrowe@example.com a/Newgate Prison p/1234567 s/Rejected`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

## Adding/Editing/Deleting Tag Types in CLInkedIn


### Creating a custom tag type: `createTagType`

Creates a custom tag type apart from the existing Skill, Degree, and Job Type tag types.

Format: `createTagType TAG_TYPE TAG_ALIAS`

- Creates a new `TAG_TYPE` tag type.
- `TAG_ALIAS` can be used to add tags to this custom tag type.

Examples:

- `createTagType GPA gpat` creates a tag type `GPA` with `gpat` as its tag alias.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can use existing tag types: Skill, or Degree, or Job Type.
</div>

### Editing tag type name: `editTagType`

Edits the name and alias of an existing tag type to `NEW_TAG_TYPE` and `NEW_TAG_ALIAS`

Format: `editTagType OLD_TAG_TYPE-NEW_TAG_TYPE OLD_TAG_ALIAS-NEW_TAG_ALIAS`

Examples: `editTagType GPA-Grade gpat-grdt`

- Edits name of the `GPA` tag type to `Grade` and its tag alias from `gpat` to `grdt`

### Deleting an existing tag type: `deleteTagType`

Deletes an existing tag type and its corresponding tag alias.

Format: `deleteTagType TAG_TYPE`

Examples: `deleteTagType GPA`

- Deletes the `GPA` tag type.

## Modifying contacts in CLInkedIn

The following functions deal with modifying the information about the contacts saved in CLInkedIn.

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/STATUS] [note/NOTE] [st/OLD_SKILL_TAG-NEW_SKILL_TAG] [dt/OLD_DEGREE_TAG-NEW_DEGREE_TAG] [jt/OLD_JOB_TYPE_TAG-NEW_JOB_TYPE_TAG] [<alias>/OLD_TAG-NEW_TAG]…​ [rate/RATING] [l/LINK]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* While editing **Skill** tags, the existing `OLD_SKILL` of the person will be renamed to `NEW_SKILL`(Same for **Degree**, **JobType** and **Custom** tags).

- While editing **Notes** of a person, their existing notes will be overwritten by the new input. Therefore, it is not possible to edit individual notes (i.e. if you input `edit 1 note/Hello` and the person already has a note `Hi`, the person's note will be changed to `Hello`).

* You can remove **all** the person’s tags by typing `t/` without specifying any tags after it.
* You can remove **all** the person’s **Skill** tags by typing `st/` without specifying any tags after it (Same for **Degree**, **JobType** and **Custom** tags).
*

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
* `edit 2 dt/Bachelors-Masters` Edits the Degree tag `Bachelors` of the 2nd person to be `Masters`.
* `edit 2 l/https://github.com/AY2223S1-CS2103T-T13-3/tp/pulls` Edits by replacing all existing links with the provided link.

### Adding a tag to an existing person : `addTag`

Adds a tag to an existing person in the address book.

Format: `addTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​`

* Adds a tag to the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.

Examples:
* `addTag 3 st/Java` Adds a **Skill** tag `Java` to the 3rd person.
* `edit 2 dt/Bachelors` Adds a **Degree** tag `Bachelors` to the 2nd person.

### Deleting a tag of an existing person : `deleteTag`

Deletes a tag of an existing person in the address book.

Format: `deleteTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​`

* Deletes an existing tag of the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.

Examples:
* `deleteTag 3 st/Java` Deletes the **Skill** tag `Java` of the 3rd person.
* `deleteTag 2 dt/Bachelors` Deletes the **Degree** tag `Bachelors` of the 2nd person.

### Adding optional information: `addNote`

Adds additional optional information (notes) to a person

Format: `addNote INDEX note/NOTE`
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​

- The note's content will be taken as the input after the `note/` prefix.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
A person can have any number of notes (including 0).
This command will add a new note to the person, and the note will be appended to the end of the list of notes.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Warning:**
You may not edit individual notes to a person. You can only add a new note to a person. Please refer to the [`edit`](#editing-a-person--edit) command for more information.
</div>

Examples:

- `addNote 4 note/Strong in Java` adds a note `Strong in Java` to the 4th person in the address book.
- Executing `addNote 4 note/Has a dog` sequentially appends a note `Has a dog` to the 4th person in the address book. The person now has two notes.

### Deleting optional information: `deleteNote`

Deletes optional information (notes) of a person.

Format: `deleteNote INDEX`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
<strong>This command will delete all the notes</strong> of the person. CLInkedIn does not support deleting individual notes. Calling this command will delete all the notes of the person. If you wish to edit the existing notes of the person, please refer to the [`edit`](#editing-a-person--edit) command for more information.
</div>

Examples:

- `deletNote 4` deletes all notes of the 4th person in the address book.

### Adding optional rating: `addRate`

Adds a numerical representation of candidates to make quick comparisons between candidates.

Format: `addRate INDEX rate/<RATING>`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​
- The rating must be an integer between 1 and 10 inclusive.

Examples:

- `addRate 2 rate/5` adds a rating of `5` to the 2nd person in the address book.

### Sorting candidates based on Rating: `sort`

Sorts candidates in CLInkedIn according to their rating given. Candidates will be sorted according to their ratings in descending order (highest rating to lowest).

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
Recall that rating is an optional field for a candidate. Thus, candidates with no ratings given will be displayed at the bottom, after candidates with a rating.
</div>

Format: `sort`

Example:

- `sort` will return a list of the candidates, sorted in order of descending rating.

### Adding optional links: `addLink`

Add links to candidate's online profiles/websites. Once added you can simply click on the icon and you will be redirected to the webpage.

Format: `addLink INDEX l/<LINK>`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​
- The link must be a valid link with a protocol.

Examples:

- `addLink 2 l/https://www.instagram.com l/https://github.com` adds links to the instagram and github page ` to the 2nd person in the address book.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
If the link is not a platform recognised by CLInkedIn (LinkedIn, GitHub, Instagram, Telegram, Twitter, Snapchat, Discord, Facebook) then a general icon for links is displayed.
</div>

### Deleting links: `deleteLink`

Deletes all links of a person.

Format: `deleteLink INDEX`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​

Examples:

- `deleteLink 4` deletes all links of the 4th person in the address book.

## Modifying contacts view in CLInkedIn

### Viewing the details of a person : `view`

Show details of a person in CLInkedIn.

Format: `view INDEX`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Alternatively to view the information of a specific person, you can also click on the person's card in the person list.
</div>

Examples:

- `view 2` views the details of the 2nd person in CLInkedIn.
- Clicking on the 2nd person's card in the person list will also show the details of the 2nd person.

### Listing all persons : `list`

Shows a list of all persons in CLInkedIn.

Format: `list`

### Finding personal information and tags: `find`

Finds candidates whose personal information and tags contain any of the given keywords.


There are 2 types of find commands: general `find` and `find` by prefix.

**General `find`:**

* Any keywords after the command will be searched for in the personal information and tags of all candidates.

**`find` by prefix:**

* Prefixes are used to specify which fields to search for the keywords in.

Format: `find KEYWORD [MORE_KEYWORDS]` or `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [s/STATUS] [rate/RATING]

[MORE_TAGGED_KEYWORDS]...`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- The personal information and tags will be searched.
- Partial words will be matched e.g. `Han` will match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
- Search can be further refined by specifying the type of tag to search for.
  e.g. `find n/John p/867` will return `John Doe` with phone number `8675309`

Examples:

- `find John` returns `john` and `John Doe`
- `find Java` returns list of candidates with Java skills

- `find alex david` returns `Alex Yeoh`, `David Li`
- `find n/John` returns `John Doe`
- `find n/alex n/david` returns `Alex Yeoh`, `David Li`

* `find s/application pending` returns list of candidates with status `application pending`

### Clearing all contacts : `clear`

Clears all entries from the address book.

Format: `clear`

### Undoing previous command: `undo`

Reverts the address book to the state before the previous command was executed.

Format: `undo`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Undo can be used multiple times to undo multiple commands.
</div>

### Redoing previous command: `redo`

Reverts the address book to the state before the previous undo command was executed.

Format: `redo`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Redo can be used multiple times to redo multiple commands.
</div>

### Viewing Statistics based on ratings of candidates: `stats`

Views rating statistics of the candidates currently saved in CLInkedIn.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
Only shows statistics of candidates in the current filtered list. 

- If the find command is used and only 5 candidates are shown,
then only the statistics of these 5 candidates will be shown when stats is used.
</div>

Format: `stats`

Example:
* `stats` displays the rating statistics of the candidates in the current filtered list.

### Exporting the addressbook: `export`

Exports the data for the displayed list of candidates to the specified location in the specified format. 

Format: `export path/<PATH WITH NAME OF FILE TO BE EXPORTED TO>`
* The format of the file must be JSON or CSV.
* Any directory in the path which does not exist will automatically be created.
* Only the displayed list of candidates will be exported.

Examples:
* `export path//Desktop/sample/data.csv` exports the filtered list of candidates to `data.csv` in the `sample` directory of the `Desktop`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Alternatively to export files using GUI, you can either go to `Export` -> `Export` in the Menu Bar or enter the command `export`.
</div>

### Importing an addressbook: `import`

Imports the data for candidates from the specified file.

Format: `import path/<PATH WITH NAME OF FILE TO BE IMPORTED FROM>`
* The format of the file must be JSON or CSV.
* Data being import will be merged with the existing data in the addressbook.
* Candidates whose name already exists in the addressbook will be ignored.

Examples:
* `import path//Desktop/sample/data.csv` imports the list of candidates from `data.csv` in the `sample` directory of the `Desktop` to the addressbook.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Alternatively to import files using GUI, you can either go to `Import` -> `Import` in the Menu Bar or enter the command `import`.
</div>

### Exiting CLInkedIn : `exit`

Exits the program.

Format: `exit`
* Alternatively, you can also go to `File' -> `Exit` in the Menu Bar.

## Miscellaneous Features/Commands

The following miscallenous features are implemented in CLInkedIn and are available to users.

### Saving the data

CLInkedIn data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CLInkedIn data are saved as a JSON file `[JAR file location]/data/clinkedin.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CLInkedIn will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CLInkedIn home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary


| Action            | Format, Examples                                                                                                                                                                                                                                                     |
|-------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**           | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 st/Java`                                                   |
| **Clear**         | `clear`                                                                                                                                                                                                                                                              |
| **Delete**        | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                  |
| **Edit**          | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [st/OLD_SKILL_TAG-NEW_SKILL_TAG] [dt/OLD_DEGREE_TAG-NEW_DEGREE_TAG] [jt/OLD_JOB_TYPE_TAG-NEW_JOB_TYPE_TAG] [<alias>/OLD_TAG-NEW_TAG]…​`<br> e.g.,`edit 1 p/91234567 e/johndoe@example.com dt/Bachelors-Masters` |
| **AddTag**        | `addTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​`<br> e.g., `addTag 3 st/Java`                                                                                                                                                         |
| **deleteTag**     | `deleteTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jt/JOB_TYPE_TAG] [<alias>/TAG]…​`<br> e.g., `deleteTag 2 dt/Bachelors`<br/>                                                                                                                                         |
| **Create**        | `create TAG_TYPE TAG_ALIAS` <br> e.g., `create GPA gpat`                                                                                                                                                                                                             |
| **EditTagType**   | `editTagType OLD_TAG_TYPE-NEW_TAG_TYPE OLD_TAG_ALIAS-NEW_TAG_ALIAS` <br> e.g., `editTagType GPA-Grade gpat-grdt`                                                                                                                                                     |
| **DeleteTagType** | `deleteTagType TAG_TYPE` <br> e.g., `deleteTagType GPA`                                                                                                                                                                                                              |
| **Undo**          | `undo`                                                                                                                                                                                                                                                               |
| **Redo**          | `redo`                                                                                                                                                                                                                                                               |
| **Find**          | `find KEYWORD [MORE_KEYWORDS]` or `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [MORE_TAGGED_KEYWORDS]`  <br> e.g., `find James Jake` , `find n/Alex p/8764321`                                                                                                          |
| **List**          | `list`                                                                                                                                                                                                                                                               |
| **Help**          | `help`                                                                                                                                                                                                                                                               |
| **Sort**          | `sort`                                                                                                                                                                                                                                                               |
| **Rate**          | `rate INDEX r/RATING` <br> e.g., `rate 3 rate/5`                                                                                                                                                                                                                     |
| **Export**        | `export path/<PATH WITH NAME OF FILE TO BE EXPORTED TO>` <br> e.g., `export path//Desktop/sample/data.csv`                                                                                                                                                                                                          |
| **Import**        | `import path/<PATH WITH NAME OF FILE TO BE IMPORTED FROM>` <br> e.g., `import path//Desktop/sample/data.csv`                                                                                                                                                                                                        |                                                                                                                                                                                                                                                                                                            |



