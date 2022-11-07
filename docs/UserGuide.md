---
layout: page
title: User Guide
---

## 1. Welcome to SoConnect's User Guide!

Here, you can find everything you need to know about **SoConnect**, the ultimate solution for your everyday contacts and tasks management headaches!

If you are an [**SoC**](#soc) **student**, you are probably stressing over lack of social interactions and mounting assignments right now. Well, look no further! SoConnect is built to help you stay better connected to school life by doing your tedious management tasks, so you can focus on **creating real friends and finishing schoolwork quickly**.

Interested to use SoConnect? Then you are in the right place! In this user guide, we will cover **step-by-step instructions** from installing the app to customising it according to your preference. Whether you are a beginner or an advanced user, this guide will solve all your problems, just like our app!

--------------------------------------------------------------------------------------------------------------------

## 2. Table of Contents

<div class="toc-remove-bullet-points">
  * Table of Contents
  {:toc}
</div>

--------------------------------------------------------------------------------------------------------------------

## 3. Introduction

SoConnect is a **2-in-1 desktop app for managing contacts and tasks**. It is originally built to help [NUS](#nus) SoC students stay better connected to their school life, in terms of social connections and school tasks.

SoConnect leverages on the simplicity of Command Line Interface ([CLI](#cli)) to optimize your productivity, while still maintaining user-friendliness through its Graphical User Interface ([GUI](#gui)). If you can type fast, SoConnect can get your contact and task management work done faster than even the popular applications such as Microsoft People and To Do!

Let's look at the overview of our main features.

### 3.1. Contact Management

SoConnect allows you to store all your contact information, so that you no longer need to stress about forgetting someone. You can:
1. Quickly **add, view, edit and delete** your contact.
2. **Sort** your displayed contact list by name, email, address, phone number and [tag](#tag).
3. **Tag** specific people in your contact list.
4. **Search** efficiently, just like using Google.
5. What's more, we even let you **customise** how you want your contact list to be shown!

### 3.2. Todo Management

SoConnect helps you keep track of your [todo](#todo) list, so that you can focus on finishing tasks quickly. You can:
1. Quickly **add, view, edit and delete** your todo.
2. **Filter** your todo according to deadline, tag and priority.
3. **Tag** specific todo in your list, so that you can leverage on our Contact Management feature to find people who might be able to help you finish the task quickly!

--------------------------------------------------------------------------------------------------------------------

## 4. Using This Guide

### 4.1. Navigate Around

If you are a first time user, we understand that you might be confused on how to even start up the application in the first place. Therefore, you can go to the [Installation](#5-installation) section to download SoConnect, if you have not done so.

Once SoConnect is installed, you can navigate to the [Quickstart](#6-quickstart) section, where we will cover the basics of using SoConnect.

If you are a returning user, you can head over to the [Command Summary](#9-command-summary) section for a quick overview of all commands in SoConnect. Alternatively, you can also read the detailed explanation of each command in the [Commands](#7-commands) section.

Have a question regarding the app? You might find the answer you are looking for in the [FAQ](#8-faq) section.

You can also refer to the [Glossary](#10-glossary) section for definitions of commonly used terms in SoConnect.

### 4.2. Icons

In order to help you pay attention, we have used some icons along with highlighted text throughout this guide.

| Icon                          | Meaning                                       |
|-------------------------------|-----------------------------------------------|
| **:memo: Note**               | You should pay attention to this.             |
| **:bulb: Tip**                | You might find this useful.                   |
| **:information_source: Info** | You can get additional information from this. |
| **:warning: Warning**         | You should be cautious about this.            |
| **:rotating_light: Danger**   | You should pay **extra** attention to this.   |

### 4.3. Formatting and Notation

In case you are wondering why certain texts are styled differently, here are the meaning behind them:

1. Text that are numbered and in purple font are headers which mark the beginning of a section or subsection of the guide.
2. **Words in bold** are phrases that we want you to pay attention to.
3. [Links in blue](#) will navigate you to place where you can find more explanation about the words.

--------------------------------------------------------------------------------------------------------------------

## 5. Installation

### 5.1. How to Install

1. Ensure you have [Java 11](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) installed on your computer.
2. Download the latest `SoConnect.jar` file from our [release page](https://github.com/AY2223S1-CS2103T-W15-1/tp/releases).
3. Copy the file to an empty folder you want to use as the _home folder_ for SoConnect. You can find more on how to do that [here](https://www.digitaltrends.com/computing/how-to-create-folder-on-desktop/#:~:text=Create%20a%20folder%20on%20a%20Windows%20desktop,-Creating%20a%20folder&text=Step%201%3A%20Right%2Dclick%20a,a%20name%20of%20your%20own.).
4. Double-click the file to start SoConnect. You can head over to the [Quickstart](#6-quickstart) section for a guided tutorial on how to use the app.

<div markdown="block" class="alert alert-primary">
**:memo: Note** <br/> <br/>
Empty folder is needed because all additional storage data and files will be saved in this folder.
</div>

### 5.2. Operating Systems

SoConnect currently can run smoothly on these operating systems:

1. Ubuntu
2. MacOS
3. Windows

You can download and use [`SoConnect.jar`](https://github.com/AY2223S1-CS2103T-W15-1/tp/releases) on any of the above operating systems.

--------------------------------------------------------------------------------------------------------------------

## 6. Quickstart

Upon completing this section, you should be able to explore SoConnect on your own. We will start by explaining [different parts of the GUI](#61-layout), [key definitions](#62-key-definitions), [command format](#63-command-format), as well as [trying out simple commands](#64-how-to-use-the-cli).

### 6.1. Layout

### 6.2. Key Definitions

### 6.2.1 Command

Command is the action you want SoConnect to perform. Most commands require [parameters](#622-parameter) after the command so that SoConnect has the required information to perform the action. 

e.g. `help`

### 6.2.2 Parameter

A parameter has a prefix and information which are written together without a space in between. The information are words in `UPPER_CASE` and are to be provided by you. The prefix, which ends with a `/`, is specified before the information and determines the information type. Do note that some commands only require the prefix as a parameter.  <br>

e.g. `n/NAME`

### 6.3. Command Format

In the command box, you need to type the [command](#621-command) along with the required [parameters](#622-parameter). You can leave the parameter field empty if it is optional, which is indicated by wrapped square brackets in the command format, e.g. `search [e/EMAIL]` <br>
The parameters required for each command can be found in [Command Summary](#9-command-summary) and [Commands](#7-commands).

<div markdown="block" class="alert alert-primary"> 

**:memo: Note**<br>

* Words in `UPPER_CASE` are the information to be supplied by the user. <br>
  e.g. if the format is `add n/NAME`, you can type `add n/John Doe` to use the command.

* Items with `…`​ after them can be used multiple times, including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family`, etc.

* Parameters can be in any order unless explicitly stated otherwise.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Irrelevant parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if you type `help 123`, it will be interpreted as `help`.

* `INDEX` is used in commands to refer to a specific contact or todo by their index number on the currently displayed list. The `INDEX` **must be a positive non-zero integer** 1, 2, 3, …​ <a id="command-format-index"></a>

### 6.4. How to Use the CLI

If you already know about [Command Line Interface (CLI)](#CLI), you can skip this part and read the [Commands](#7-commands).

To use the CLI, you can type a command, which is supposed to be typed in a certain [format](#63-command-format), in the command box and press Enter. SoConnect will perform a specific action. You could try using the CLI using some examples provided below. <br>

The command format for `help` is just `help`. You can just type **`help`** into the CLI and press Enter, SoConnect will open the help window.

The command format for `add` is `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`. As mentioned [here](#63-command-format), since `t/TAG` is wrapped with square brackets and has the `…`​ at the back, you can choose whether to provide this parameter and even choose to provide this multiple times. The parameters can be provided in any order as long as they are after the command.<br>
Valid usage e.g.: `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567` <br>
Invalid usage e.g: `add n/Joe Tan` (Other parameters such as `p/PHONE_NUMBER e/EMAIL a/ADDRESS` must be provided as they are not wrapped in square brackets)

Do not worry about typing commands wrongly as SoConnect will guide you if and when you do so.

You can refer to the [Command Summary](#9-command-summary) for the full list of commands.

You can also refer to the [Commands](#7-commands) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 7. Commands


### 7.1. General Commands

Welcome to the General Commands section! In this section, you can learn about various miscellaneous commands and features of SoConnect. For the core commands of SoConnect, we have [Contact Management Commands](#72-contact-management-commands), [Todo Management Commands](#73-todo-management-commands), [Tag Management Commands](#74-tag-management-commands), and [Customisation Commands](#75-customisation-commands).

<br>

#### 7.1.1. Viewing help : `help`

If you are ever stuck or in need of help while using SoConnect, the `help` command will be there to assist you! The `help` command will link you to this user guide, providing you with easy access to guidance on how to use SoConnect.

Format: `help`

**Example Input in Command Box**
```
help
```

**Example Result**

![help message](images/helpMessage.png)

<br>

#### 7.1.2. Exiting the program : `exit`

When you are done with your [contact management](#72-contact-management-commands) and [todo management](#73-todo-management-commands), you can exit SoConnect at any time using the `exit` command.

Format: `exit`

<br>

#### 7.1.3. Saving the data

You won't ever have to worry about losing your contacts or [todos](#todo). SoConnect automatically saves your data in the hard disk after any command that changes the data.

<br>

#### 7.1.4. Editing the data file

If you are an advanced user, SoConnect allows you to freely edit its data files and directly update your data.
* Your contact list and [tag](#tag) list are saved in a [JSON](#json) file `[JAR file location]/data/soconnect.json`.<br>
* Your todo list is saved in a separate JSON file `[JAR file location]/data/todolist.json`.<br>

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**
If your changes to a data file renders it invalid, SoConnect will discard the data file and start with an empty data file at the next run.
* Familiarise yourself with the format of the data files before attempting any changes.
* Backup your data files in a separate folder before attempting any changes.

</div>

<br>

#### 7.1.5. Archiving data files `[coming in v2.0]`

<br>

### 7.2. Contact Management Commands

Welcome to the Contact Management Commands section! In this section, you can learn how to manage your contacts using SoConnect. Contacts help you to keep track of a person's information by storing them all in 1 place. This way, you won't have to worry about forgetting someone's information and can find all of their information conveniently in 1 place.

A contact of a person consists of
1. their name
2. their phone number
3. their email address
4. their address
5. (optional) [tags](#tag) to help you categorise your contacts

<div markdown="block" class="alert alert-primary">

**:memo: Note:**<br>
* You might forget that you have already [added a contact](#721-adding-a-contact-add), but no need to worry about having duplicate contacts! We help you to detect duplicate contacts by checking existing contacts for the exact same name (case-sensitive) whenever you add a new contact or [edit an existing contact](#722-editing-a-contact--edit).
* What if you are trying to add the contacts of 2 different people with the same name? You can make use of the case-sensitivity of names and numbers to help you differentiate between the contacts. (e.g. `Alex Yeoh`,`Alex yeoh`,`Alex Yeoh 1`,`Alex Yeoh 2` can all be added as 4 different valid contacts)
* Names currently only accept [alphanumeric](#alphanum) characters and spaces to help you avoid mistakes when typing them. If a name you are trying to add has special characters like `,` or `/`, it is alright to leave the special characters out for now as SoConnect does not require you to store the exact legal names of your contacts.

</div>

<br>

#### 7.2.1. Adding a contact: `add`

You can add a contact using the `add` command as shown below. While the `NAME`, `PHONE_NUMBER`, `EMAIL`, and `ADDRESS` parameters are mandatory, you can include as many `TAG` parameters as you wish (including none).

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`

<div markdown="block" class="alert alert-primary">

**:memo: Note:**

[Tags](#tag) have to be created first before you can add them to a contact.

* Refer to [`Creating a Tag`](#741-creating-a-tag-tag-create) on how to create a tag.

</div>

**Example Input in Command Box**
```
add n/John Doe t/friend p/98765432 e/johnd@example.com a/John street, block 123, #01-01
```

**Example Result**

(insert image of a successful contact addition)

<br>

#### 7.2.2. Editing a contact : `edit`

You might have included the wrong information when [adding a contact](#721-adding-a-contact-add), or you might need to update the information of a contact. Regardless, you can accomplish both easily using the `edit` command as shown below. All you need is the `INDEX` of the contact you want to modify along with the parameters you want to update.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`

* At least 1 of the 4 optional parameters must be provided.
* Existing information will be updated with the parameters provided. Information of the parameters not provided will remain unchanged.

<div markdown="block" class="alert alert-primary">

**:memo: Note:**

To edit the tags of a contact, you can refer to [adding a tag](#744-adding-a-tag-to-a-contact-tag-add) and [removing a tag](#745-removing-a-tag-from-a-contact-tag-remove).

</div>

**Example Input in Command Box**
```
`edit 1 p/91234567 e/johndoe@example.com`
```

**Example Result: Comparing the Before and After**
    (insert image of before the edit command)
    (insert image of after the edit command)

<br>

#### 7.2.3. Listing all contacts : `list`

Whenever you need to view a list of all the contacts you have in your SoConnect, you can easily do so using the `list` command. You can directly use the `list` command without the need of any parameters!

Format: `list`

<br>

#### 7.2.4. Searching for a contact: `search`

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

Autocompleting search: `search`

Displays a list of search queries based on the current search query with the last parameter completed. The completed parameter will depend on the contacts that match the current search query. User can choose one of the search queries and perform the searching without having to type the full parameter.

Format: Refer to the [`search`](#724-searching-for-a-contact-search) command format above.

* This feature is only available when search command is entered (i.e. the command entered matches the [`search`](#724-searching-for-a-contact-search) format stated above).
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

<br>

#### 7.2.5. Sorting contacts : `sort`

Organising your contacts can make tracking and managing them easier, especially when you have lots of contacts. You can organise your contacts in the order you prefer using the `sort` command as shown below. Given below are the orders that you can choose each parameter to be sorted by.

How *names (n/)*, *emails (e/)*, *addresses (a/ )* are sorted:
* In alphabetical order. (e.g. `Al` comes before `Alfred` which comes before `Brad`)
* Case-insensitive. (e.g. `Al`, `al`, `AL`, and `aL` are identical when it comes to sorting)

How *phone numbers (p/)* are sorted:
* In increasing numerical order. (e.g. `123` comes before `125` which comes before `1234`)

How *[tags](#tag) (t/TAG)* are sorted:
* Contacts with the *TAG* you specified will come before contacts without the *TAG*.

<div markdown="block" class="alert alert-primary">

**:memo: Note:**<br>
* When sorting by tags, unlike other parameters, you have to specify a value (an existing `TAG`) to sort by.
* For other parameters (i.e. `n/ e/ a/ p/`), values provided are ignored. (e.g. sorting by `n/Alfred` is a valid command, the list will be sorted by name alphabetically, and the name given `Alfred` is ignored)

</div>

Format: `sort [n/] [p/] [e/] [a/] [t/TAG]…​`
* At least 1 of the optional parameters must be provided.
* To sort in reverse order from the orders given above, use these modified parameters: `[n/!] [p/!] [e/!] [a/!] [t/!TAG]`.

<div markdown="block" class="alert alert-primary">

**:memo: Note:**

You can use multiple parameters to sort if you want to organise your contacts even more! Your list will be sorted by the first parameter you provide as per usual. Here's how the other parameters will be used:
1. Contacts with identical values for the first parameter are identified. (e.g. same phone number, same email, same address, or contains the same tag)
2. Each group of contacts with the same identical values would appear together on your list. This is where your second parameter will be used.
3. Each group will go through another round of sorting by your second parameter to determine the order within the group. If there are still contacts with identical values for the second parameter in the group, the process repeats with the remaining parameters (if provided).

</div>

**Example Input in Command Box**
```
sort n/!
```

**Example Result**

(insert image of a successful reverse sorting by name)

**Example Input in Command Box**
```
`sort t/friend n/`
```

**Example Result**

(insert image of a successful sorting by the friend tag and names. If possible, have 2 contacts with the friend tag and 2 contacts without the friend tag)

<br>

#### 7.2.6. Deleting a contact : `delete`

If you [added a contact](#721-adding-a-contact-add) by mistake, or you no longer wish to keep a particular contact, you can delete it easily using the `delete` command. All you need is the `INDEX` of the contact and poof, it's gone!

Format: `delete INDEX`

<br>

#### 7.2.7. Clearing all contacts : `clear`

Want a fresh start? You can reset and get a clean, empty list of contacts using the `clear` command. You can directly use the `clear` command without the need of any parameters!

Format: `clear`

<br>

### 7.3. Todo Management Commands

Welcome to the Todo Management Commands section! In this section, you can learn how to manage your todos using SoConnect. A [todo](#todo) represents a task that needs completing. With the todo list in SoConnect, you won't have to worry about forgetting your school tasks and can find all your tasks conveniently in 1 place.

A todo consists of
1. a description
2. a date of the deadline of the task
3. the priority of the task
4. (optional) [tags](#tag) to help you categorise your todos

<div markdown="block" class="alert alert-primary">

**:memo: Note:**<br>
* You might forget that you have already [added a todo](#731-adding-a-todo-todo-add), but no need to worry about having duplicate todos! We help you to detect duplicate todos by checking existing todos whenever you add a new todo or [edit an existing todo](#732-editing-a-todo--todo-edit). Duplicate todos are todos with the exact same information for every parameter.
* What if you want to add 2 todos with the same description? You are still able to do so, as long as the todos have different tags, dates, or priorities.
* Priority of a todo can strictly only be `low`, `medium`, or `high`. `Coming soon in v1.5`, we will add smarter priorities (to accept other variations such as `Low`, `Medium`, `High`, `L`, `M`, `H`).

</div>

<br>

#### 7.3.1. Adding a todo: `todo add`

You can add a todo using the `todo add` command as shown below. While the `DESCRIPTION`, `DATE`, and `PRIORITY` parameters are mandatory, you can include as many `TAG` parameters as you wish (including none).

Format: `todo add d/DESCRIPTION date/DATE pr/PRIORITY [t/TAG]…​`

* `DATE` should be of the format dd-MM-yyyy (e.g. 24-03-2022).
* The todo list will always be sorted by date from earliest to latest (for todos with the same date, they will be sorted in decreasing priority order).

<div markdown="block" class="alert alert-primary">

**:memo: Note:**

[Tags](#tag) have to be created first before you can add them to a todo.

* Refer to [`Creating a Tag`](#741-creating-a-tag-tag-create) on how to create a tag.

</div>

Examples:
* `todo add d/Watched recorded videos for CS2100 date/24-10-2022 pr/low t/CS2100`
* `todo add d/Prepare slides for OP2 date/25-03-2022 pr/high t/CS2101 t/CS2103T`

<br>

#### 7.3.2. Editing a todo : `todo edit`

Edits an existing todo in your SoConnect.

Format: `todo edit INDEX [d/DESCRIPTION] [date/DATE] [pr/PRIORITY] [t/TAG]…​`

* Edits the todo at the specified [`INDEX`](#).
* At least one of the optional fields must be provided.
* Parameters given will overwrite the existing values completely.
  * For example, giving 1 or more tag(s) in the edit command will replace all existing tags with the ones given in the edit command.
  * `Coming soon in v1.5`, you can use `tag add` and `tag remove` to modify tags in a todo instead of only using `todo edit`.

Examples:
*  `todo edit 1 d/Read notes for ST2334` Edits the description of the 1st todo to be `Read notes for ST2334`.
*  `todo edit 1 pr/medium t/ST2334` Edits the priority of the 2nd contact to be `medium` and changes its tags to just `ST2334`.

<br>

#### 7.3.3. Deleting a todo : `todo delete`

Deletes the specified todo from your SoConnect.

Format: `delete INDEX`

* Deletes the todo at the specified `INDEX`.

Examples:
* `todo show` followed by `todo delete 2` deletes the 2nd todo shown in your SoConnect.

<br>

#### 7.3.4. Clearing all todos : `todo clear`

Clears all todos from your SoConnect.

Format: `todo clear`

<br>

#### 7.3.5. Filtering todos shown : `todo show`

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

<br>

### 7.4. Tag Management Commands

Welcome to the Tag Management Commands section! In this section, you can learn how to manage your tags using SoConnect. A [tag](#tag) represents a category which you can place contacts and todos in, to better organise them. You can also use tags to [sort your contacts](#725-sorting-contacts--sort), [search your contacts](#724-searching-for-a-contact-search), or [filter your todos](#735-filtering-todos-shown--todo-show).

A tag consists of a category name that can be a maximum of 10 characters.

<br>

#### 7.4.1. Creating a Tag: `tag create`

You can create a new `TAG` and add it into the tag list.

Format: `tag create t/TAG`

**Example Input in Command Box**
```
tag create t/friends
```

**Output in Command Result Box**
```
New Tag created: [friends]
```

   (insert image of a successful creation of the `friends` tag)

Great! You have successfully learnt how to add your first `TAG` you have made. Now, you can start utilising the other tag features.

<br>

#### 7.4.2. Deleting a Tag: `tag delete`

You can delete a `TAG` from the tag list.

Format: `tag delete t/TAG`

<div markdown="block" class="alert alert-info">
**:information_source: Note:** When `TAG` is deleted, `TAG` is removed from all the contacts which previously had it.
</div>

**Expected Input in Command Box**
```
tag delete t/friends
```

**Output in Command Result Box**
```
Tag deleted: [friends]
```

   (insert successful deletion of `Test2` tag)

Wonderful! You have successfully deleted a tag.

<br>

#### 7.4.3. Editing a Tag: `tag edit`

If you make a mistake or want to update your `TAG`, you can simply update it with this command.

Format: `tag edit t/TAG1 t/TAG2`

<div markdown="block" class="alert alert-info">
**:information_source: Note:** 

* The new Tag must not have the same name as any other existing tags.
* `TAG1` represents the current name of the tag and `TAG2` represents the new name of the tag.
* This command will replace `TAG1` in all contacts and todos to `TAG2`. 

</div>

**Example Input in Command Box**
```
tag edit t/friends t/bestFriends
```

**Output in Command Result Box**
```
Tag has changed from [friends] to [bestFriends]
```

* You can refer to the Before and After comparison below.

   Before:
   (insert image a contact with `friends` tag)

   After:
   (insert image of the same contact with `bestFriends` tag instead of `friend`, with the Command Result Box)

Fantastic! You have successfully learnt how to change tags.

<br>

#### 7.4.4. Adding a Tag to a Contact: `tag add`

You can add a `TAG` from the tag list to a contact.
* `Coming soon in v1.5`, we will upgrade `tag add` to add tags to todos.
* Consider using [Adding a todo](#731-adding-a-todo-todo-add) or [Editing a todo](#732-editing-a-todo--todo-edit) to add tags to todos.

Format: `tag add INDEX t/TAG`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags. Add as many as you want.
</div>

<div markdown="block" class="alert alert-info">
**:information_source: Note:** The tag has to be created first before you can add it into a contact.

Refer to [`Creating a Tag`](#creating-a-tag-tag-create) on how to create a tag.
</div>

**Example Input in Command Box**
```
tag add 1 t/friends
```

**Output in Command Result Box**
```
Tag added: [friends]
```

   (insert image of a successful addition of the `friends` tag to contact `1`)

Awesome! You have successfully learnt to add a tag to a contact.

<br>

#### 7.4.5. Removing a Tag from a Contact: `tag remove`

You can remove a `TAG` from a contact.
* `Coming soon in v1.5`, we will upgrade `tag remove` to remove tags from todos.
* Consider using [Editing a todo](#732-editing-a-todo--todo-edit) to remove tags from todos.

Format: `tag remove INDEX t/TAG`

**Example Input in Command Box**
```
tag remove 1 t/friends
```

**Output in Command Result Box**
```
Tag removed: [friends]
```

   (insert image of contact `1` without the `friend` tag with the resu )

Nice! You have successfully removed a tag from a contact.

<br>

### 7.5. Customisation Commands

Welcome to the Customisation Commands section! In this section, you can learn to customise how your contacts are displayed in SoConnect. You have your own needs and preferences that you would know best. So with our customise commands, we would like to provide you as much freedom as possible in deciding what you want to see and how you want to see it!

<div markdown="block" class="alert alert-primary">

**:memo: Note:**<br>
* You might be wondering why you can only customise contacts and not [todos](#todo). Fret not, we are working hard on making that possible for you! `Coming soon in v1.5`, we will introduce customisation commands for todos.

</div>

<br>

#### 7.5.1. Customising order of details: `customise order`

You can customise the order of information shown for all contacts.

Format: `customise order [t/] [p/] [e/] [a/]`

* You will always see the contact's name at the top of each card.
* You can change the order of the following information: Tags, Phone Number, Email, Address.
* Unspecified information will be ordered last according to the default order (Tags > Phone Number > Email > Address).

Example:

* `customise order a/ e/ p/` SoConnect will show address first, followed by email, phone number and tags.
* `customise order a/` SoConnect will show address first. The rest of the information will follow the default order. Therefore, address will be followed by tags, phone number and email.

<br>

#### 7.5.2. Hiding contact details: `customise hide`

You can hide certain information fields from all contacts.

Format: `customise hide [t/] [p/] [e/] [a/]`

* You can hide the following information: Tags, Phone Number, Email, Address.
* After you use this command, the information specified is hidden.
* If the information that you specify is already hidden, it will stay hidden.

Example:
* `customise hide e/` SoConnect no longer shows emails in the list of contacts.
* `customise hide p/ t/` SoConnect no longer shows phone numbers and tags in the list of contacts.

<br>

#### 7.5.3. Showing contact details: `customise show`

You can show certain information fields for all contacts.

Format: `customise show [t/] [p/] [e/] [a/]`

* You can show the following information: Tags, Phone Number, Email, Address.
* After you use this command, the information specified is shown.
* If the information that you specify is already shown, it will stay shown.
* `Coming soon in v1.5`, we will include `customise show all`, a shortcut to show all information.

Example:
* `customise show a/` SoConnect now shows addresses in the list of contacts.
* `customise show p/ t/` SoConnect now shows phone numbers and tags in the list of contacts.

<br>

--------------------------------------------------------------------------------------------------------------------

## 8. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Copy the home folder of your SoConnect app over to the other Computer. This folder should contain your `SoConnect.jar` file and your `data` folder.

**Q**: What if I do not have the phone number/email address/address of a person whose contact I am trying to add?
**A**: When SoConnect has more users, we plan to gather feedback for which information should be made optional. In the meantime, you can go ahead and [add the contact](#721-adding-a-contact-add) by replacing the fields you do not have with dummy information. (e.g. Using `123` for the phone number, `xyz@email.com` for the email, or `xyz` for the address)

**Q**: Do you have any plans to make the app more customisable?<br>
**A**: Of course! `Coming soon in v1.5`, we will introduce [customisation commands](#75-customisation-commands) for [todos](#todo). We won't stop there either, for we are always working on ways to make the app more customisable so that you can make your SoConnect app truly yours!

**Q**: Where can I find the data file with the tags I have created?<br>
**A**: Your [tags](#tag) are stored together with your contacts in `soconnect.json`. The tag list stored affects both your contacts and your todos.

**Q**: Why can't I include a particular tag when adding a contact/todo?
**A**: Tags have to be [created](#741-creating-a-tag-tag-create) before they can be added to a contact or todo. You may also want to double-check if you have spelled the tag correctly.

**Q**: Why is the todo I have just added missing from the todo list?
**A**: Check if the todo list is currently showing a filtered list of todos. Refer to [`Filtering todos shown`](#735-filtering-todos-shown--todo-show) on how to filter the todo list.

**Q**: Why are the `tag add` and `tag remove` commands not working on todos?
**A**: You can use the `todo edit` command to modify the tags of a todo. Refer to [`Editing a todo`](#732-editing-a-todo--todo-edit) for more information.


--------------------------------------------------------------------------------------------------------------------

## 9. Command summary

| Action                                                                    | Format and Examples                                                                                                                                                                                                  |
|---------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Help**](#711-viewing-help--help)                                       | `help`                                                                                                                                                                                                               |
| [**Exit**](#712-exiting-the-program--exit)                                | `exit`                                                                                                                                                                                                               |
| [**Add contact**](#721-adding-a-contact-add)                              | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665`                                                                                |
| [**Edit contact**](#722-editing-a-contact--edit)                          | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br> e.g. `edit 2 n/James Lee e/jameslee@example.com`                                                                                                    |
| [**List all contacts**](#723-listing-all-contacts--list)                  | `list`                                                                                                                                                                                                               |
| [**Search contact**](#724-searching-for-a-contact-search)                 | `search [CONDITION] [n/NAME] [p/PHONE_NUMBER]…​`<br> e.g. `seach or n/John Doe t/cs2103t`                                                                                                                            |
| [**Sort contacts**](#725-sorting-contacts--sort)                          | `sort [n/] [p/] [e/] [a/] [t/TAG]…​` <br> e.g. `sort t/!friend n/`                                                                                                                                                   |
| [**Delete contact**](#726-deleting-a-contact--delete)                     | `delete INDEX`<br> e.g. `delete 3`                                                                                                                                                                                   |
| [**Clear all contacts**](#727-clearing-all-contacts--clear)               | `clear`                                                                                                                                                                                                              |
| [**Add Todo**](#731-adding-a-todo-todo-add)                               | `todo add d/DESCRIPTION date/DATE pr/PRIORITY [t/TAG]…​` <br> e.g. `todo add d/Revise priority/high`                                                                                                                 |
| [**Edit Todo**](#732-editing-a-todo--todo-edit)                           | `todo edit INDEX [d/DESCRIPTION] [date/DATE] [pr/PRIORITY] [t/TAG]…​` <br> e.g. `todo edit t/CS2101`                                                                                                                 |
| [**Delete Todo**](#733-deleting-a-todo--todo-delete)                      | `todo delete INDEX` <br> e.g. `todo delete 3`                                                                                                                                                                        |
| [**Clear Todo**](#734-clearing-all-todos--todo-clear)                     | `todo clear`                                                                                                                                                                                                         |
| [**Show Todo**](#735-filtering-todos-shown--todo-show)                    | `todo show`<br> `todo show today` <br> `todo show date/DATE` <br> `todo show date/DATE to DATE` <br> `todo show t/TAG` <br> `todo show pr/Priority` <br> e.g. `todo show`, `todo show pr/high`, `todo show t/CS2100` |
| [**Create Tag**](#741-creating-a-tag-tag-create)                          | `tag create t/TAG` <br> e.g. `tag create t/friend`                                                                                                                                                                   |
| [**Add Tag**](#744-adding-a-tag-to-a-contact-tag-add)                     | `tag add INDEX t/TAG` <br> e.g. `tag add 1 t/friend`                                                                                                                                                                 |
| [**Edit Tag**](#743-editing-a-tag-tag-edit)                               | `tag edit t/TAG1 t/TAG2`  <br> e.g. `tag edit t/friend t/bestFriend`                                                                                                                                                 |
| [**Remove Tag**](#745-removing-a-tag-from-a-contact-tag-remove)           | `tag remove INDEX t/TAG` <br> e.g. `tag remove 1 t/friend`                                                                                                                                                           |
| [**Delete Tag**](#742-deleting-a-tag-tag-delete)                          | `tag delete t/TAG` <br> e.g. `tag delete t/friend`                                                                                                                                                                   |
| [**Customise order**](#751-customising-order-of-details-customise-order)  | `customise order [t/] [p/] [e/] [a/]` <br> e.g. `customise order a/ p/`                                                                                                                                              |
| [**Hide contact details**](#752-hiding-contact-details-customise-hide)    | `customise hide [t/] [p/] [e/] [a/]`  <br> e.g. `customise hide a/ e/ p/`                                                                                                                                            |
| [**Show contact details**](#753-showing-contact-details-customise-show)   | `customise show [t/] [p/] [e/] [a/]` <br> e.g. `customise show a/`                                                                                                                                                   |

--------------------------------------------------------------------------------------------------------------------

## 10. Glossary

**<a id="autocomplete">Autocomplete</a>**

A feature that shows a list of completed words or strings without the user needing to type them in full.

<br>

**<a id="cli">CLI</a>**

A text-based user interface used to run programs.

<br>

**<a id="gui">GUI</a>**

A graphical user interface (GUI) is a form of user interface that allows users to interact with programs through graphical icons and audio indicator.

<br>

**<a id="javafx">JavaFX</a>**

A Java library used to develop client applications.

<br>

**<a id="kloc">kLoC</a>**

Stands for thousands of lines of code.

<br>

**<a id="mainstream-os">Mainstream OS</a>**

Windows, Linux, Unix, OS-X.

<br>

**<a id="nus">NUS</a>**

National University of Singapore.

<br>

**<a id="contact">Private Contact Detail</a>**

A contact detail that is not meant to be shared with others.

<br>

**<a id="soc">SoC</a>**

School of Computing, a computing school in NUS.

<br>

**<a id="todo">Todo</a>**

A task that the user needs to complete.

<br>

**<a id="alphanum">Alphanumeric</a>**

Alphabet letters and numbers only.

<br>

**<a id="tag">Tag</a>**

A category which you can place contacts and todos in.

<br>

**<a id="json">JSON</a>**

JSON stands for JavaScript Object Notation. JSON is a lightweight format for storing and transporting data.
