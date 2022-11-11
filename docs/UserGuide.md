---
layout: page
title: User Guide
---
<h1 align="center">:book: Welcome to CLInkedIn User Guide!</h1>

Welcome to the CLInkedIn User Guide! 

CLInkedIn is a **desktop address book application made for Recruiting and Hiring Managers to keep track of candidates and their job applications.**

The application is optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI).

If you can type fast, CLInkedIn can get your recruitment tasks done faster than traditional GUI apps.

If you are a recruiter who is using CLInkedIn, or someone who is curious to find out more about CLInkedIn and its features, here is a table summarising what we offer!

|                Features                 | 
|:---------------------------------------:|
|            Adding candidates            | 
|      Editing candidate information      | 
|     Use tags to organise candidates     | 
|        Searching for candidates         | 
|      Comparing between candidates       |
|      Get statistics of candidates       |
| Sharing candidates' data between devices | 

![Ui](images/Ui.png)
The photo above is a screenshot of what you will see when you launch CLInkedIn. 

In this user guide, you will find instructions on how to install CLInkedIn and a guide on how to use CLInkedIn's numerous features.

## Table of Contents

- [Using this Guide](#using-this-guide)
- [Useful Notations and Glossary](#useful-notations-and-glossary)
- [Quick start](#quick-start)
- [Features](#features)
  - [**Viewing help `help`**](#viewing-help--help)
  - [**Adding/Deleting candidates in CLInkedIn**](#addingdeleting-candidates-in-clinkedin)
    - [Adding a candidate `add`](#adding-a-candidate-add)
    - [Deleting a candidate `delete`](#deleting-a-candidate--delete)
  - [**Adding/Editing/Deleting Tag Types in CLInkedIn**](#addingeditingdeleting-tag-types-in-clinkedin)
    - [Creating a custom tag type `createTagType`](#creating-a-custom-tag-type-createtagtype)
    - [Editing tag type name `editTagType`](#editing-tag-type-name-edittagtype)
    - [Deleting an existing tag type `deleteTagType`](#deleting-an-existing-tag-type-deletetagtype)
  - [**Modifying entries in CLInkedIn**](#modifying-entries-in-clinkedin)
    - [Editing a candidate `edit`](#editing-a-candidate--edit)
    - [Adding an optional tag `addTag`](#adding-a-tag-to-an-existing-candidate--addtag)
    - [Deleting an optional tag `deleteTag`]([#deleting-a-tag-of-an-existing-candidate--deletetag)
    - [Adding optional information `addNote`](#adding-optional-information-addnote)
    - [Deleting optional information `deleteNote`](#deleting-optional-information-deletenote)
    - [Adding optional rating `addRate`](#adding-optional-rating-addrate)
    - [Deleting optional rating `deleteRate`](#deleting-optional-rating-deleterate)
    - [Adding optional links `addLink`](#adding-optional-links-addlink)
    - [Deleting optional links `deleteLink`](#deleting-optional-links-deletelink)
    - [Adding multiple attributes to candidates at once `addTo`](#adding-multiple-attributes-to-candidates-at-once-addto)
  - [**Modifying contacts view in CLInkedIn**](#modifying-contacts-view-in-clinkedin)
    - [Viewing the details of a person `view`](#viewing-the-details-of-a-person--view)
    - [Listing all persons `list`](#listing-all-persons--list)
    - [Sorting candidates based on rating `sort`](#sorting-candidates-based-on-rating-sort)
    - [Finding personal information and tags `find`](#finding-personal-information-and-tags-find)
    - [Clearing all contacts `clear`](#clearing-all-contacts--clear)
    - [Undoing previous command `undo`](#undoing-previous-command-undo)
    - [Redoing previous command `redo`](#redoing-previous-command-redo)
    - [Viewing statistics `stats`](#viewing-statistics-based-on-ratings-of-candidates-stats)
    - [Exporting the data in CLInkedIn `export`](#exporting-the-data-in-clinkedin-export)
    - [Importing data into CLInkedIn `import`](#importing-data-into-clinkedin-import)
    - [Exiting CLInkedIn `exit`](#exiting-clinkedin--exit)
  - [**Miscellaneous Features/Commands**](#miscellaneous-featurescommands)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Using this guide

- If you are just setting up, please take a look at [Quick start](#quick-start).
- If you are unsure of how to use CLInkedIn, the [Command Summary](#command-summary) table is a good place to start. 
- If you are a developer and want to help out, please take a look at the [CLInkedIn Developer Guide](https://ay2223s1-cs2103t-t13-3.github.io/tp/DeveloperGuide.html).

--------------------------------------------------------------------------------------------------------------------
## Useful Notations and Glossary 

While exploring CLInkedIn's features with this user guide, do take note of these symbols used in the user guide and what information they represent.

| Symbol | Meaning |
| :----: | ------- |
| :information_source: | Important information |
| :exclamation: | Warning or caution |
| :bulb: | Additional information such as tips or notes |
| :wrench: | Help with common technical issues |

The following glossary table provides clarification of the terms commonly used in CLInkedIn:

|  Symbol   | Meaning                                                                                                                                             |
|:---------:|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| Candidate | An entry in CLInkedIn. You can add a candidate with the `add` command.                                                                              |
|  Status   | Application stage the candidate is in. Example: `Application Received`, `OA in Progress`, `Shortlisted for Interview`, `Accepted`, `Rejected`, etc. |
|   Note    | Optional description of candidate related to their information.                                                                                     |
|  Rating   | Numerical representation of candidate, 1 means that candidate is least desirable, 10 means that the candidate is most desirable.                    |
|   Links   | Webpages to find more information about the candidate.                                                                                              |
| TAG_TYPE  | Description of tag, eg: GPA, Skill, Degree, Job Type, etc. Only used in the following commands `createTagType`, `editTagType`, `deleteTagType`.     |
| TAG_ALIAS | Prefix for tag type. Used in `add` and `edit` commands to specify prefix of attribute.                                                              |


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java [`11`](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) or above installed in your Computer.

2. Download the latest `CLInkedIn.jar` from [here](https://github.com/AY2223S1-CS2103T-T13-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your `CLInkedIn` application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/Application Received` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [st/SKILL_TAG]` can be used as `n/John Doe st/Java` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[st/SKILL_TAG]…​` can be used as ` ` (i.e. 0 times), `st/Java`, `st/Java st/Python` etc.

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

You can use this command to view a message explaining how to access the help page, as well as a list of commands and their descriptions.
Alternatively, you can obtain help on a specific command by typing `help` followed by the command name.

![help message](images/helpMessage.png)
Above is a screenshot of the help pop up window when the `help` command is input.

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

## Adding/Deleting candidates in CLInkedIn

You can add or delete candidates from your address book in CLInkedIn.

### Adding a candidate: `add`

Adds a candidate to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/STATUS [note/NOTE] [st/SKILL_TAG] [dt/DEGREE_TAG] [jtt/JOB_TYPE_TAG] [<alias>/TAG]…​ [rate/RATING] [l/LINK]…​`

- By default, you can add 3 types of tags - `SKILL_TAG`, `DEGREE_TAG`, `JOB_TYPE_TAG`.

- Alternatively, you can create your own custom tag type and alias for it. See [`createTagType`](#creating-a-custom-tag-type-createtagtype) command.

Examples:

* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/OA in Progress st/Java dt/Bachelors jt/Internship rate/4` adds a candidate of name `John Doe`, phone number `98765432`, email `johnd@example.com`, address `John street, block 123, #01-01`, status `OA`, skill tag `Java`, degree tag `Bachelors`, job tag of `Internship` and rating of `4`.
* `add n/Betsy Crowe st/java e/betsycrowe@example.com a/Newgate Prison p/1234567 s/Rejected` adds a candidate of name `Betsy Crowe`, skill tag of `java`, email of `betsycrowe@example.com`, address of `Newgate Prison` and status of `Rejected`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can have any number and any kind of tags (including 0).
</div>

### Deleting a candidate : `delete`

Deletes the candidate of that index from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

## Adding/Editing/Deleting Tag Types in CLInkedIn

You can create, edit and delete tag types in CLInkedIn. 

### Creating a custom tag type: `createTagType`

Creates a custom tag type apart from the existing Skill, Degree, and Job Type tag types.

Format: `createTagType TAG_TYPE TAG_ALIAS`

- Creates a new `TAG_TYPE` tag type.
- `TAG_ALIAS` can be used to add tags to this custom tag type.

Example:

- `createTagType GPA gpat` creates a tag type `GPA` with `gpat` as its tag alias.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can use existing tag types: Skill `st/` , or Degree `dt/`, or Job Type `jtt/`.
</div>

### Editing tag type name: `editTagType`

Edits the name and alias of an existing tag type to `NEW_TAG_TYPE` and `NEW_TAG_ALIAS`

Format: `editTagType OLD_TAG_TYPE-NEW_TAG_TYPE OLD_TAG_ALIAS-NEW_TAG_ALIAS`

Example: `editTagType GPA-Grade gpat-grdt` edits name of the `GPA` tag type to `Grade` and its tag alias from `gpat` to `grdt`

### Deleting an existing tag type: `deleteTagType`

Deletes an existing tag type and its corresponding tag alias.

Format: `deleteTagType TAG_TYPE`

Example: `deleteTagType GPA` deletes the `GPA` tag type.

## Modifying entries in CLInkedIn

You can modify the information about the candidates saved in CLInkedIn using the following functions.

### Editing a candidate : `edit`

Edits an existing candidate in the address book.

Format: `edit INDEX [n/NEW_NAME] [p/NEW_PHONE] [e/NEW_EMAIL] [a/NEW_ADDRESS] [s/NEW_STATUS] [st/OLD_SKILL_TAG-NEW_SKILL_TAG] [dt/OLD_DEGREE_TAG-NEW_DEGREE_TAG] [jtt/OLD_JOB_TYPE_TAG-NEW_JOB_TYPE_TAG] [<custom_tag_prefix>/OLD_TAG-NEW_TAG] [note/NEW_NOTE] [rate/NEW_RATING] [l/NEW_LINK]…​`

* Edits the person at the specified `INDEX`. 
* The index refers to the index number shown in the displayed person list. 
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 dt/Bachelors-Masters` edits the Degree tag `Bachelors` of the 2nd person to be `Masters`.
* `edit 3 n/PersonA n/PersonB` edits the name of the 3rd person first to `PersonA` and then to `PersonB`. Hence the final name of 3rd person is set to `PersonB`.
* `edit 2 l/https://github.com/JohnDoe l/https://instagram.com/JohnDoe` edits by replacing all existing links with the provided links.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**>

* While editing the **Skill** tags, you can rename the existing `OLD_SKILL` of the person to `NEW_SKILL` provided the Skill Tag Type has not been deleted (Same for **Degree**, **JobType** and **Custom** tags).
* While editing the **Notes** of a person, their existing notes will be overwritten by the new input. Therefore, it is not possible to edit individual notes (i.e. if you input `edit 1 note/Good at Java` and the person already has a note `Good at Python`, the person's note(s) will be changed to `Good at Java`).
* While editing the **Links** of a person, their existing links will be overwritten by the new input. Therefore, it is not possible to edit individual links (i.e. if you input `edit 1 l/https://github.com/JohnDoe` and the person already has a link `https://instagram.com/JohnDoe`, the person's link(s) will be changed to `https://github.com/JohnDoe`). The `l/` prefix can be used multiple times in the command to add multiple links.

</div> 

### Adding a tag to an existing candidate : `addTag`

Adds a tag to an existing person in the address book.

Format: `addTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jtt/JOB_TYPE_TAG] [<alias>/TAG]…​`

* Adds a tag to the person at the specified `INDEX`. 
* The index refers to the index number shown in the displayed person list. 
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.

Examples:
* `addTag 3 st/Java` Adds a Skill tag `Java` to the 3rd candidate.
* `addTag 2 dt/Bachelors` Adds a Degree tag `Bachelors` to the 2nd candidate.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To add multiple attributes to a candidate at once, you may use the [`addTo`](#adding-multiple-attributes-to-an-existing-candidate--addto) command. The `addTo` command parameters for adding tags are the same as the `addTag` command.
</div>

### Deleting a tag of an existing candidate : `deleteTag`

Deletes a tag of an existing person in the address book.

Format: `deleteTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jtt/JOB_TYPE_TAG] [<alias>/TAG]…​`

* Deletes an existing tag of the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.

Examples:
* `deleteTag 3 st/Java` Deletes the Skill tag `Java` of the 3rd candidate.
* `deleteTag 2 dt/Bachelors` Deletes the Degree tag `Bachelors` of the 2nd candidate.

### Adding optional information: `addNote`

Adds additional optional information (notes) to a person

Format: `addNote INDEX note/NOTE`
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​
* The note's content will be taken as the input after the `note/` prefix.
* Adding multiple notes to a person at once is possible by including multiple `note/` prefixes in the command. Eg. `addNote 1 note/Good at Java note/Good at Python` will add boths notes `Good at Java` and `Good at Python` to the 1st person in the list.

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

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To add multiple attributes to a candidate at once, you may use the [`addTo`](#adding-multiple-attributes-to-an-existing-candidate--addto) command. The `addTo` command parameters for adding notes are the same as the `addNote` command.
</div>

### Deleting optional information: `deleteNote`

Deletes optional information (notes) of a person.

Format: `deleteNote INDEX`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​

Example:

- `deleteNote 4` deletes all notes of the 4th person in the address book.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
<strong>This command will delete all the notes</strong> of the person. CLInkedIn does not support deleting individual notes. Calling this command will delete all the notes of the person. If you wish to edit the existing notes of the person, please refer to the [`edit`](#editing-a-person--edit) command for more information.
</div>

### Adding optional rating: `addRate`

Adds rating, a numerical representation of candidates, to make quick comparisons between candidates.

Format: `addRate INDEX rate/<RATING>`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​
- The rating must be an integer between 1 and 10 inclusive.

Example:

- `addRate 2 rate/5` adds a rating of `5` to the 2nd person in the address book.

<div markdown="span" class="alert alert-warning">:exclamation: **Warning:**
`rate/0` is **not** a valid rating. If you input `addRate 1 rate/0`, there will be no rating shown. If you input `edit 1 rate/0`, you will remove the current rating of the candidate.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To add multiple attributes to a candidate at once, you may use the [`addTo`](#adding-multiple-attributes-to-an-existing-candidate--addto) command. The `addTo` command parameters for adding a rating are the same as the `addRate` command.
</div>

### Deleting optional rating: `deleteRate`

Deletes rating of candidates. 

Format: `deleteRate INDEX`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​

Example:

- `deleteRate 4` deletes the rating of the 4th candidate in the address book.

### Adding optional links: `addLink`

Add links to candidate's online profiles/websites. Once added you can simply click on the icon and you will be redirected to the webpage.

Format: `addLink INDEX l/<LINK>`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​
- The link must be a valid link with a protocol.

Example:

- `addLink 2 l/https://www.instagram.com l/https://github.com` adds links to the instagram and github page ` to the 2nd candidate in the address book.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
If the link is not a platform recognised by CLInkedIn (LinkedIn, GitHub, Instagram, Telegram, Twitter, Snapchat, Discord, Facebook) then a general icon for links is displayed.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To add multiple attributes to a candidate at once, you may use the [`addTo`](#adding-multiple-attributes-to-an-existing-candidate--addto) command. The `addTo` command parameters for adding links are the same as the `addLink` command.
</div>

### Deleting optional links: `deleteLink`

Deletes all links of a candidate.

Format: `deleteLink INDEX`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​

Examples:

- `deleteLink 4` deletes all links of the 4th candidate in the address book.

### Adding multiple attributes to candidates at once: `addTo`

Adds multiple attributes to a candidate at once. This incorporates the `addTag`, `addNote`, `addRate` and `addLink` commands. This command is useful when you want to add multiple attributes to a candidate at once, using the prefixes of the aforementioned commands.

Format: `addTo INDEX [<alias>/TAG]…​ [note/NOTE] [rate/RATING] [l/LINK]…​`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​
- The prefixes are optional, **but at least one of them must be provided.**
- The prefixes of the attributes to be added must be used.
- The attributes to be added must be valid.

Examples:
- `addTo 1 st/Java st/Python` adds the skill tags `Java` and `Python` to the 1st candidate.
- `addTo 2 note/Strong in Java` adds a note `Strong in Java` to the 2nd candidate.
- `addTo 3 rate/5` adds a rating of `5` to the 3rd candidate.
- `addTo 4 l/https://www.instagram.com l/https://github.com` adds links to instagram (`https://www.instagram.com`) and github (`https://github.com`) pages to the 4th candidate.
- `addTo 5 st/Java rate/5 note/Strong in Java` adds the skill tag `Java`, a rating of `5` and a note `Strong in Java` to the 5th candidate.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
The `addTo` command requires all parameters to follow the correct format as specified in the [`addTag`](#adding-tags--addtag), [`addNote`](#adding-optional-information--addnote), [`addRate`](#adding-optional-rating--addrate) and [`addLink`](#adding-optional-links--addlink) commands.
</div>

## Modifying contacts view in CLInkedIn

You can use the following commands to modify how the contacts are displayed. 

### Viewing the details of a person : `view`

Show details of a candidate in CLInkedIn.

Format: `view INDEX`

- The index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, …​

Examples:

- `view 2` views the details of the 2nd person in CLInkedIn.
- Clicking on the 2nd person's card in the person list will also show the details of the 2nd person.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Alternatively to view the information of a specific person, you can also click on the person's card in the person list.
</div>

### Listing all persons : `list`

Shows a list of all persons in CLInkedIn.

Format: `list`

### Sorting candidates based on Rating: `sort`

Sorts candidates in CLInkedIn according to their rating given. Candidates will be sorted according to their ratings in descending order (highest rating to lowest).

Format: `sort`

Example:

- `sort` will return a list of the candidates, sorted in order of descending rating.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
Recall that rating is an optional field for a candidate. Thus, candidates with no ratings given will be displayed at the bottom, after candidates with a rating.
</div>

Below is a picture of the interface before the sort command is executed. 

![img.png](images/img.png)

Below is a picture of the interface after the sort command is executed, where candidates are listed from the highest rating to the lowest rating.

![img_1.png](images/img_1.png)

### Finding personal information and tags: `find`

Finds candidates whose personal information and tags contain any of the given keywords.


There are 2 types of find commands: general `find` and `find` by prefix.

**General `find`:**

* Any keywords after the command will be searched for in the personal information and tags of all candidates.

**`find` by prefix:**

* Prefixes are used to specify which fields to search for the keywords in.

Format: `find KEYWORD [MORE_KEYWORDS]` or `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [s/STATUS] [rate/RATING] [MORE_TAGGED_KEYWORDS]...`

<div markdown="span" class="alert alert-primary">:bulb: **Note**
- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- The personal information and tags will be searched.
- Partial words will be matched e.g. `Han` will match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
- Search can be further refined by specifying the type of tag to search for.
  
</div>

Examples:
- `find John` returns `john` and `John Doe`
- `find Java` returns list of candidates with Java skills
- `find Hans Bo` will return `Hans Gruber`, `Bo Yang`
- `find alex david` returns `Alex Yeoh`, `David Li`
- `find n/John` returns `John Doe`
- `find n/alex n/david` returns `Alex Yeoh`, `David Li`
- `find s/application pending` returns list of candidates with status `application pending`
- `find n/John p/867` returns `John Doe` with phone number `8675309`

### Clearing all contacts : `clear`

Clears all entries from the address book.

Format: `clear`

### Undoing previous command: `undo`

Reverts the address book to the state before the previous command was executed.

Format: `undo`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
- Undo can be used multiple times to undo multiple commands.
- Features that modify contacts view (`sort`, `view`) cannot be undone.
</div>

### Redoing previous command: `redo`

Reverts the address book to the state before the previous undo command was executed.

Format: `redo`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
- Redo can be used multiple times to redo multiple commands.
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

### Exporting the data in CLInkedIn: `export`

Exports the data for the displayed list of candidates to the specified location in the specified format. 

Format: `export path/PATH_WITH_NAME_OF_FILE_TO_BE_EXPORTED_TO`
* The path specified must be an absolute path.
* The format of the file must be JSON or CSV.
* Any directory in the path which does not exist will automatically be created.
* Only the displayed list of candidates will be exported.

Examples:
* `export path//Desktop/sample/data.csv` exports the displayed list of candidates to `data.csv` in the `sample` directory of the `Desktop` (Sample path for MACOS).
* `export path/C:\Users\John\Desktop\data.json` exports the displayed list of candidates to `data.json` in the `Desktop` (Sample path for WINDOWS).

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Alternatively to export files using GUI, you can either go to `Export` -> `Export` in the Menu Bar or enter the command `export`. The following window will open on doing so:<br><br>
<center><img src="images/ExportWindow.png"></center><br>
Now you can easily export the displayed list of candidates by selecting a location and providing a name for the resultant file.
</div>

### Importing data into CLInkedIn: `import`

Imports the data for candidates from the specified file.

Format: `import path/PATH_WITH_NAME_OF_FILE_TO_BE_IMPORTED_FROM`
* The path specified must be an absolute path.
* The format of the file must be JSON or CSV.
* The data being imported will be merged with the existing data in CLInkedIn.
* Candidates whose name already exists in CLInkedIn will be ignored.

Examples:
* `import path//Desktop/sample/data.csv` imports the list of candidates from `data.csv` in the `sample` directory of the `Desktop` to the address book (Sample path for MACOS).
* `import path/C:\Users\John\Desktop\data.json` imports the list of candidates from `data.json` in the `Desktop` to the address book (Sample path for WINDOWS).

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Alternatively to import files using GUI, you can either go to `Import` -> `Import` in the Menu Bar or enter the command `import`. The following window will open on doing so:<br><br>
<center><img src="images/ImportWindow.png"></center><br>
Now you can easily select a file to import into the addressbook.
</div>

### Exiting CLInkedIn : `exit`

Exits the program.

Format: `exit`
* Alternatively, you can also go to `File` -> `Exit` in the Menu Bar.

## Miscellaneous Features/Commands

The following miscellaneous features are implemented in CLInkedIn and are available to users.

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

**Q**: What are the available commands?<br>
**A**: Refer to the [Command Summary](#command-summary) for a list of available commands. Alternatively, from the main window of CLInkedIn, you can type `help` to view the list of available commands.

**Q**: How do I save my data?<br>
**A**: CLInkedIn data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

**Q**: How do I import data from a file?<br>
**A**: Refer to the [Importing data into CLInkedIn](#importing-data-into-clinkedin-import) section for more details.

**Q**: How do I export data to a file?<br>
**A**: Refer to the [Exporting the data in CLInkedIn](#exporting-the-data-in-clinkedin-export) section for more details.

**Q**: What are the available formats for importing and exporting data?<br>
**A**: CLInkedIn supports importing and exporting data in CSV and JSON formats.

--------------------------------------------------------------------------------------------------------------------

## Command summary


| Action            | Format, Examples                                                                                                                                                                                                                                                                                                       |
|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**           | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/STATUS [note/NOTE] [rate/RATING] [st/SKILL_TAG] [dt/DEGREE_TAG] [jtt/JOB_TYPE_TAG] [<alias>/TAG]…​ [l/LINK]...` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 st/Java s/Application Received jtt/Internship`               |
| **Clear**         | `clear`                                                                                                                                                                                                                                                                                                                |
| **Delete**        | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                                                                    |
| **Edit**          | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/STATUS] [note/NOTE] [rate/RATING] [l/LINK]... [st/OLD_SKILL_TAG-NEW_SKILL_TAG] [dt/OLD_DEGREE_TAG-NEW_DEGREE_TAG] [jtt/OLD_JOB_TYPE_TAG-NEW_JOB_TYPE_TAG] [<alias>/OLD_TAG-NEW_TAG]…​`<br> e.g.,`edit 1 p/91234567 e/johndoe@example.com dt/Bachelors-Masters` |
| **AddTag**        | `addTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jtt/JOB_TYPE_TAG] [<alias>/TAG]…​`<br> e.g., `addTag 3 st/Java`                                                                                                                                                                                                          |
| **deleteTag**     | `deleteTag INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jtt/JOB_TYPE_TAG] [<alias>/TAG]…​`<br> e.g., `deleteTag 2 dt/Bachelors`<br/>                                                                                                                                                                                          |
| **addTo**         | `addTo INDEX [st/SKILL_TAG] [dt/DEGREE_TAG] [jtt/JOB_TYPE_TAG] [<alias>/TAG]…​ [note/NOTE] [l/LINK]... [rate/RATING]`<br> e.g., `addTo 1 st/Java note/Has 5 years work expereince`</br>                                                                                                                                |
| **CreateTagType** | `createTagType TAG_TYPE TAG_ALIAS` <br> e.g., `create GPA gpat`                                                                                                                                                                                                                                                        |
| **EditTagType**   | `editTagType OLD_TAG_TYPE-NEW_TAG_TYPE OLD_TAG_ALIAS-NEW_TAG_ALIAS` <br> e.g., `editTagType GPA-Grade gpat-grdt`                                                                                                                                                                                                       |
| **DeleteTagType** | `deleteTagType TAG_TYPE` <br> e.g., `deleteTagType GPA`                                                                                                                                                                                                                                                                |
| **Undo**          | `undo`                                                                                                                                                                                                                                                                                                                 |
| **Redo**          | `redo`                                                                                                                                                                                                                                                                                                                 |
| **Statistics**    | `stats`                                                                                                                                                                                                                                                                                                                |
| **Find**          | `find KEYWORD [MORE_KEYWORDS]` or `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [MORE_TAGGED_KEYWORDS]`  <br> e.g., `find James Jake` , `find n/Alex p/8764321`                                                                                                                                                            |
| **List**          | `list`                                                                                                                                                                                                                                                                                                                 |
| **Help**          | `help`                                                                                                                                                                                                                                                                                                                 |
| **Sort**          | `sort`                                                                                                                                                                                                                                                                                                                 |
| **AddRate**       | `addRate INDEX rate/RATING` <br> e.g., `addRate 3 rate/5`                                                                                                                                                                                                                                                              |
| **DeleteRate**    | `deleteRate INDEX` <br> e.g., `deleteRate 2`                                                                                                                                                                                                                                                                           |
| **AddNote**       | `addNote INDEX note/NOTE` <br> e.g., `addNote 2 note/In Dean's list`                                                                                                                                                                                                                                                   |
| **DeleteNote**    | `deleteNote INDEX` <br> e.g., `deleteNote 2`                                                                                                                                                                                                                                                                           |
| **AddLink**       | `addLink INDEX l/LINK...` <br> e.g., `addLink 2 l/https://github.com`                                                                                                                                                                                                                                                  |
| **DeleteLink**    | `deleteLink INDEX` <br> e.g., `deleteLink 2`                                                                                                                                                                                                                                                                           |
| **Export**        | `export path/<PATH WITH NAME OF FILE TO BE EXPORTED TO>` <br> e.g., `export path//Desktop/sample/data.csv`                                                                                                                                                                                                             |
| **Import**        | `import path/<PATH WITH NAME OF FILE TO BE IMPORTED FROM>` <br> e.g., `import path//Desktop/sample/data.csv`                                                                                                                                                                                                           |                                                                                                                                                                                                                                                                                                            |



