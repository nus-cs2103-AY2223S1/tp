---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div class="page-new"></div>

## Introduction

CodeConnect is a **desktop app** specially designed for **Computer Science students from NUS**. With this app, not only can you manage your **tasks and contacts** effectively in one unified place, you can also conveniently search for peers to seek help or collaboration on a particular task.

This app is optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

## How to use this guide?

For new users, it is strongly recommended that you look through this User Guide to gain a comprehensive understanding of CodeConnect.

However, you may also choose to head over to the relevant sections as described below:

- [Quick start](#quick-start) - To learn how to set up CodeConnect on your computer
- [Features](#features) - To learn the different features and commands used in CodeConnect
- [Command Summary](#command-summary) - To have a complete overview of all the features and their respective command.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**

Boxes like this indicate gotchas that you might wish to watch out for.

</div>

<div markdown="block" class="alert alert-info">

:question: **Extra information**

Boxes like this indicate extra information you might be curious about.

</div>

<div markdown="block" class="alert alert-info">

:bulb: **Additional tips**

Boxes like this indicate more tips to help you make the most out of CodeConnect.

</div>
<div class="page-new"></div>

## Quick start

CodeConnect works on all major OSes (Windows, Mac, Linux) and only requires Java 11 or above installed on your computer.

1. Ensure you have Java `11` or above installed in your computer.
   * [Instructions for doing this](https://superuser.com/questions/1221096/) are available on the internet.
   * If not, [install it](https://docs.oracle.com/en/java/javase/11/install/).

2. Download the latest `CodeConnect.jar` from [here](https://github.com/AY2223S1-CS2103T-T14-2/tp/releases).

3. Copy the file to the folder you want to store CodeConnect's data in.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

    ![UiOnStartup](images/UiOnStartup.png)

<div class="page-new"></div>

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listc`** : Lists all contacts.

   * **`add`** `Finish homework by/tomorrow 5pm m/CS2040S` : Adds a task named `Finish homework` to the Task Manager.

   * **`del`** `1` : Deletes the 1<sup>st</sup> task shown in the current list.

   * **`clear`** : Deletes all contacts. Good for removing the sample data before adding your own. 

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of all available commands.

--------------------------------------------------------------------------------------------------------------------
<div class="page-new"></div>

## Navigating CodeConnect

This section explains the various graphical components of CodeConnect's interface.

![UiAnnotated](images/UiAnnotated.png)

| No. | Component         | Description                                                                       |
|-----|-------------------|-----------------------------------------------------------------------------------|
| 1   | Menu Bar          | Quick access to utility commands.                                                 |
| 2   | Command Box       | Where you'll enter commands to tell CodeConnect what to do.                       |
| 3   | Result Box        | Result (success, error, etc.) of the command executed.                            |
| 4   | Tabs              | Toggle between contact list or task list.                                         |
| 5   | Contact/Task List | Display current contacts/tasks, depending on command executed.                    |
| 6   | Data File Path    | Location of the file in your computer where your contact and task data is stored. |

<div class="page-new"></div>

## Features

CodeConnect has 2 main sets of commands, one set to manage your contacts and one set to manage your tasks.

### Notes about the command format:

* Words in `{curly_braces}` are the parameters to be supplied by the user.<br>
  e.g. In `addc n/{name}`, `{name}` is a parameter which can be used as `addc n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. When [adding a contact](#adding-a-contact-addc), it is optional to tag them. Hence, the tag parameter is presented as `[t/{tag}]`

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/{tag}]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. If the command specifies `n/{name} p/{phone_number}`, `p/{phone_number} n/{name}` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the **last occurrence** of the parameter will be taken.<br>
  e.g. If you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. If the command specifies `help 123`, it will be interpreted as `help`.

<div markdown="block" class="alert alert-info">

:bulb: A Note on Indexes

Several commands below require an `index` to be specified, as they operate on a task/contact which is to be specified by you. For example, the deletion of a particular task.
* Indexes are either `task_index` or `contact_index`
* The index always refers to the index number of the task/contact in the **currently displayed list**
* This value ranges from 1, 2, 3...

</div>
<div class="page-new"></div>

### Managing tasks

This section contains all the commands for the Task features. 

#### Adding a task: `add`

Adds a task and its deadline to the task list. Specifying the module the task belongs to is optional.

Format:
`add {task_name} by/{deadline} [m/{module_code}]`

Examples:
* `add finish problem set 5 by/tomorrow 5pm m/CS2040S` - adds a new task "finish problem set 5" 
with the given deadline and module.
* `add finish SEP application by/2022-12-25 23:59` - adds a new task "finish SEP application" with the given deadline.

<div markdown="block" class="alert alert-info">

:bulb: Deadline Formats

CodeConnect uses the [jchronic](https://github.com/samtingleff/jchronic) natural language date parser, ported from [Ruby's Chronic](https://github.com/deepin-community/ruby-chronic). This means that natural language inputs such as "tomorrow" or "next sunday" will be understood by CodeConnect!
The default time of a task will be set to 11.59pm if it is not specified in the input.

Here are some examples of what deadline formats are accepted:
* "tomorrow" will format the deadline to the next day, 11.59pm
* "tomorrow 3pm" will format it to the next day, 3.00pm
* "this tuesday 05:00" or "this tuesday 0500" will format it to the coming tuesday, 5.00am
* "may 27th" will format it to the 27th of may of the current year, 11.59pm

The following dates and times are known **not to work**:
* "6/4/2022" will be read as 4 June.
* "11.30pm" will not be understood. ("11:30pm" is understood)
* "2022-02-31" will be read as 3 March.

We will attempt to resolve these issues in a future version.

</div>

<div class="page-new"></div>

<div markdown="block" class="alert alert-info">

:bulb: Flexible Module Format

As a user, you might find yourself adding tasks that don't exactly belong to a module. For example, if you're a student applying for the Student Exchange Programme, you might have tasks like "Write Personal Statement" and "Submit Application". In this case, you could set the module field of both these tasks as `SEP` to increase searchability.

However, only alphanumeric characters are allowed! (i.e. no spaces, special characters, etc.)

</div>

<div markdown="block" class="alert alert-info">

:bulb: Adding past deadlines

CodeConnect will happily accept deadlines that are in the past, in case you want to use it to track start dates or track an assignment due a few hours ago you just found out about!

</div>

#### Editing a task: `edit`

Edits the specified task.

Format:
`edit {task_index} {field_prefix + field_description}`

Examples:
* `edit 2 n/Rewatch lecture 6` - edits the task at index 2 to "Rewatch lecture 6".
* `edit 3 m/CS2040S by/2022-12-12 23:59` - edits the module and deadline of the task at index 3.

<div markdown="block" class="alert alert-info">

:question: What are Field Prefixes?

`field_prefix` can be any task field used in the [add task command](#adding-a-task-add).

</div>
<div class="page-new"></div>

#### Deleting a task: `del`

Deletes the specified task.

Format:
`del {task_index}`

Example:
`del 5` - deletes the task at index 5 from the task list.

#### Deleting all completed tasks: `clean`

Deletes all completed tasks.

Format: `clean`


<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**

This command has similar spelling to the [clear command](#clearing-all-contacts--clear), which **deletes all contacts**.

</div>
<div class="page-new"></div>

#### Marking a task: `mark`

Marks the specified task as complete.

Format:
`mark {task_index}`

Example:
`mark 2` - marks the task at index 2 as done shown by a tick beside the task description.

![marktask](images/markTask.png)

#### Unmarking a task: `unmark`

Marks the specified task as incomplete.

Format:
`unmark {task_index}`

Example:
`unmark 3` - undo the marking of the task at index 2 shown by a cross beside the task description.

<div class="page-new"></div>

#### Finding tasks: `find`

Find tasks by their name or module. <br>
Finding tasks by module makes it convenient for you to see what tasks you have for a particular module at one look, while finding them by name helps you to look for a specific task quickly rather than scrolling through the entire task list.

<div markdown="block" class="alert alert-info">

:bulb: Note
* The search is case-insensitive. e.g. `find n/tutorial` will match `Tutorial`
* The order of the keywords does not matter. e.g. `find n/Set Problem` will match `Problem Set`
* Partial and full words will be matched. e.g. `find m/2030S` and `find m/CS2030S` will match tasks whose module is `CS2030S`
* Tasks that match at least one keyword will be returned (i.e. OR search). e.g. `find n/tut set` will match `Tutorial 1`
  and `Problem Set 2`

</div>

Format:
`find n/{keyword}` `find m/{module}`

Examples:
* `find n/home` - returns all tasks with name that matches "home". E.g. `Science homework`, `Math homework`
* `find n/lab assignment` - returns all tasks with name that matches "lab" or "assignment". E.g. `lab 1`, `assignment 2`
* `find m/CS2030S CS2040S` - returns all tasks with module that matches "CS2030S" or "CS2040S".
* `find m/20` - returns all tasks with module that matches "20".

<div class="page-new"></div>

`find m/CS2103T` returns this:

![findTasks](images/findTask.png)

#### Listing all tasks : `list`

Shows a list of all tasks.

Format: `list`  `list time`

* `list` - displays a list of all tasks in the order of most recent task added.
* `list time` - displays a list of all tasks in the order of the earliest deadline.

<div markdown="block" class="alert alert-info">

:bulb: About extraneous parameters in `list`

`list time` is the only exception to the list command ignoring extraneous parameters.
* `list abc` and `list time abc` will be both interpreted as `list`.

</div>

Click [here](#introduction) to return to the top of the page.
<div class="page-new"></div>

### Managing contacts

This section contains all the commands for the Contacts features.

#### Adding a contact: `addc`

Adds a contact to the contact list.

Format: `addc n/{name} p/{phone_number} [e/{email}] [a/{address}] [t/{tag_1}]... [m/{module}...] [gh/{github}] [tele/{telegram}]`

Examples:
* `addc n/Bob Martin p/98765432 e/bobbymartini@gmail.com m/CS1101S CS1231S gh/bobby tele/bmartin` - adds a contact with the given name, phone number, email, modules, Github username and Telegram handle.
* `addc n/Betsy Crowe p/89985432 tele/croweybetty` - adds a contact with the given name, phone number and Github username.

<div markdown="block" class="alert alert-info">

:bulb: Tags and Modules

Every tag must begin with a tag prefix `t/`. If you would like to add multiple tags, enter `t/tag_1 t/tag2 ...`.

Modules, however, may be entered as **space separated**. For example, `m/MOD1 MOD2 MOD3` will correctly assign 3 modules to the contact.

</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**

Due to the limitations of the code, the adding of same names is currently not supported. To distinguish between two contacts with the same name, you may add a descriptor after the name to differentiate the contacts. Additionally, only alphanumeric characters are allowed for names. <br> 

E.g. `addc n/John Lim p/80009123` will not work if there is already a contact with name `John Lim`. To add this contact, you can try adding a descriptor such as (school) by doing `addc n/John Lim (school) p/80009123`, or simply make the name of the contact to be added different from `John Lim`.

</div>
<div class="page-new"></div>

#### Listing all contacts : `listc`

Shows a list of all contacts.

Format: `listc`

#### Deleting a contact : `delc`

Deletes the specified contact.

Format: `delc {contact_index}`

Examples:
* `delc 2` - deletes the contact at index 2 from the contact list.

<div class="page-new"></div>

#### Editing a contact : `editc`

Edits the specified contact.

Format: `editc {contact_index} {field prefix}{field description}`

Examples:
* `editc 4 gh/alicey` - edits the Github username of the contact at index 4 in the contact list to "alicey".

![editContact](images/editContact.png)

<div markdown="block" class="alert alert-info">

:question: Field Prefixes

`field_prefix` can be any contact field used in the [add contact command](#adding-a-contact-addc).

</div>
<div class="page-new"></div>

#### Finding contacts: `findc`

Finds contacts by their name, modules they are taking, or a specified task. <br>
Contacts can be found by their names, or by the modules that they take. Finding contacts via module makes it very convenient to see who are your options if you need help for a that module.


<div markdown="block" class="alert alert-info">

:bulb: Note on searching by name or module

Refer to the note at [find tasks command](#finding-tasks-find) for more information on how
CodeConnect searches by name or module.

</div>

Format:
`findc n/{name}` `findc m/{module}` `findc ts/{task_index}`

Examples:
* `findc n/John` - returns all contacts with name that matches "John". E.g. `john`, `John Doe`
* `findc n/jo` - returns all contacts with name that matches "jo". E.g. `john`, `John Doe`
* `findc n/jo ja` - returns all contacts with name that matches "jo" or "ja". E.g. `john`, `james`
* `findc m/CS1231S CS1101S` - returns all contacts that are taking at least one module that matches "CS1231S" or "CS1101S".
* `findc m/1S` - returns all contacts that are taking at least one module that matches "1S".
* `findc ts/2` -returns contacts that are taking the module that the task at index 2 belongs to

#### Quick-search for contacts: `saveme`

Finds contacts that can help with the task at the first index of the task list.

Example:
1. The task at **index 1** belongs to the `CS1101S` module.
2. Entering `saveme` will display all contacts that take the `CS1101S` module.

<div markdown="block" class="alert alert-info">

:question: What if I need help with a task that isn't index 1?

There's a command for that! See [find contacts command](#finding-contacts-findc).

</div>
<div class="page-new"></div>

#### Clearing all contacts : `clear`

Deletes all contacts.

Format: `clear`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**

Deleted contacts are **unrecoverable**!

</div>

Click [here](#introduction) to return to the top of the page.

### Getting help

#### Viewing help : `help`

Provides the link to this User Guide.

Format: `help`

<div class="page-new"></div>

### Finishing up

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

CodeConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CodeConnect data are saved as a JSON file either in  `[JAR file location]/data/contacts.json` for your contacts or `[JAR file location]/data/tasks.json` for your tasks. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CodeConnect will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CodeConnect home folder.

**Q**: Why does CodeConnect switch between the task list and contact list?<br>
**A**: For a smoother user experience, CodeConnect automatically switches to the list that is associated with the previous command type (task or contact command). You can also switch list views manually by clicking the tabs above the lists.

--------------------------------------------------------------------------------------------------------------------

<div class="page-new"></div>

## Command summary

| Action                     | Format, Examples                                                                                                                                                                                             |
|----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**                   | `help`                                                                                                                                                                                                       |
| **Add task**               | `add {task_name} by/{deadline} [m/{module_code}]` <br> e.g. `add finish problem set 5 by/next week sunday m/CS2040S`                                                                                         |
| **Edit task**              | `edit {task_index} {field prefix + field description}` <br> e.g. `edit 2 by/2022-12-12 23:59`                                                                                                                |
| **Delete task**            | `del {task_index}` <br> e.g. `del 5`                                                                                                                                                                         |
| **Delete completed tasks** | `clean`                                                                                                                                                                                                      |
| **Mark task**              | `mark {task_index}` <br> e.g. `mark 3`                                                                                                                                                                       |
| **Unmark task**            | `unmark {task_index}` <br> e.g. `unmark 3`                                                                                                                                                                   |
| **Find tasks**             | `find n/{task}` <br> `find m/{module}`<br> e.g. `find n/homework`, <br> `find m/CS1101S`                                                                                                                     |
| **List tasks**             | `list` / `list time`                                                                                                                                                                                         |
| **Add contact**            | `addc n/{name} p/{phone_number} [e/{email}] [a/{address}] [t/{tag}]... [m/{module}...] [gh/{github}] [tele/{telegram}]` <br> e.g. `addc n/Bob Martin p/98765432 tele/bobmartin00 m/CS1101S CS1231S t/friend` |
| **List contacts**          | `listc`                                                                                                                                                                                                      |
| **Delete contact**         | `delc {contact_index}`<br> e.g. `delc 3`                                                                                                                                                                     |
| **Edit contact**           | `editc {contact_index} {field_prefix + field_description}` <br> e.g. `editc 2 p/91919100`                                                                                                                    |
| **Find contacts**          | `findc n/{name}` <br> `findc m/{module}`<br> `findc ts/{task_index}` <br> e.g. `findc n/John`, `findc m/CS1231S`, `findc ts/3`                                                                               |
| **Quick contact search**   | `saveme`                                                                                                                                                                                                     |
| **Clear contacts**         | `clear`                                                                                                                                                                                                      |

<div class="page-new"></div>

## List of Prefixes

| Field    | Prefix  |
|----------|---------|
| Name     | `n/`    |
| Phone    | `p/`    |
| Email    | `e/`    |
| Address  | `a/`    |
| Tag      | `t/`    |
| Module   | `m/`    |
| GitHub   | `gh/`   |
| Telegram | `tele/` |
| Task     | `ts/`   |
| Deadline | `by/`   |

Click [here](#introduction) to return to the top of the page.
