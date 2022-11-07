---
layout: page
title: User Guide
---

YellowBook is a **desktop application for university students** who are involved in many projects to organize their project contacts and tasks.
As a user of YellowBook, you will be able to: 
* add contacts
* update contact details
* add tasks
* update task details 
* track your task completion progress
* get task reminders
* sort tasks by deadlines 
* and more!

As YellowBook is optimised for use via a Command Line Interface (CLI), in which all functionality is achieved through typing commands,
you will benefit greatly from the use of this application if you are a fast typer.

Read on to find out more about YellowBook's features!

<div style="page-break-after: always;"></div>

## Table of Contents
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Purpose

Welcome to YellowBook's user guide.

If you are a first-time user, this user guide will walk you through [setting up](#quick-start) YellowBook. The [sample usage section](#sample-usage) will also provide a brief
tutorial for you to get familiar with the commands and command formats.

This user guide also provides comprehensive explanations so that you know how to use all the [features](#features) in YellowBook.

[[Back to Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. [(Installation Guide)](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

2. Download the latest `yellowbook.jar` from [here](https://github.com/AY2223S1-CS2103T-F11-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your YellowBook.

4. Double-click the file to start the app. The GUI, as shown in the image below, should appear in a few seconds. Note how the app contains some sample data.<br>

    <figure>
    <img src="images/QuickStart.png" alt="QuickStart" style="width:100%">
    <figcaption align = "center"><i>Fig.1 - App UI</i></figcaption>
    </figure>

5. Try out some commands from the [Sample Usage](#sample-usage) section.

6. Refer to the [Features](#features) below for details of each command.

[[Back to Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Sample Usage

To better understand the usage of YellowBook, we have provided a usage scenario of YellowBook below. We encourage all first-time users to follow along to gain a general understanding of the commands available.

1. YellowBook comes with a list of sample contacts and tasks by default.

2. Let's start by listing all the contacts in YellowBook. Type `listC` in the command box and press Enter to execute it. You should see a list of contacts. Try using `listT` to list all the tasks.

    <figure>
    <img src="images/listC.png" alt="listC" style="width:100%">
    <figcaption align = "center"><i>Fig.2 - Result of listC</i></figcaption>
    </figure>
  
    <figure>
    <img src="images/listT.png" alt="listT" style="width:100%">
    <figcaption align = "center"><i>Fig.3 - Result of listT</i></figcaption>
    </figure>

3. Now that you can navigate between the contact and task lists, let's add a new contact. Type `addC n/Elmo p/91238888 e/elmo@sesamestreet.com a/sesame street` in the command box and press Enter to execute it. You should see a message indicating that the contact has been added successfully and Elmo will appear in your contact list.

    <figure>
    <img src="images/step3.png" alt="step3" style="width:100%">
    <figcaption align = "center"><i>Fig.4 - Result of addC</i></figcaption>
    </figure>

4. YellowBook also allows you to add tasks, to add homework task with deadline of 25 December 2022. Type `addT d/complete homework D/25-12-2022` in the command box and press Enter to execute it. You should see a message indicating that the task has been added successfully and the task will appear in your task list.

    <figure>
    <img src="images/step4.png" alt="step4" style="width:100%">
    <figcaption align = "center"><i>Fig.5 - Result of addT</i></figcaption>
    </figure>

5. Now that you know the basic commands, try following the guide for the label command to tag the newly added `Elmo` contact as `friend`.

6. When you feel ready to use YellowBook for your own contacts and tasks, type `clear` to remove all the sample data from YellowBook.

    <figure>
    <img src="images/clear.png" alt="clear" style="width:100%">
    <figcaption align = "center"><i>Fig.6 - Result of clear command</i></figcaption>
    </figure>

7. YellowBook's commands are mnemonically named. A [Command Summary](#command-summary) with these helpful tips can be found below.

[[Back to Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Icons

<div markdown="block" class="alert alert-info">

**Meaning of the icons used:**

:information_source:: Useful information

:bulb:: Tip

:warning:: Warning on incorrect usage

:exclamation:: Caution

</div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [r/REMARK]` can be used as `n/John Doe r/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/LABEL]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listC`, `listT`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

<div style="page-break-after: always;"></div>
  
## Section 1: Contacts

These commands allow you to maintain a list of business contacts that you meet for collaborative projects.
Whether it be for a school project or an internship, store all their details in one place for easy look-up.
Contact management has never been this simple.

<div markdown="block" class="alert alert-info">

:information_source: Notes on contacts<br>

* Contacts have no labels by default.<br>

* Contact names are limited to alphanumeric characters and spaces. There must be at least one alphanumeric character.<br>

* Contact phone numbers are limited to numeric characters and must be at least three digits long.<br>

* Contact emails should be of the format local-part@domain.<br> 
The local-part is limited to alphanumeric characters and four special characters "+", "_", ".", and "-". However, the local-part may not start or end with any special characters.<br>
The domain name consists of domain labels separated by periods, and should end with a domain label at least two characters long. Each domain label should consist of alphanumeric characters, separated only by hyphens, if any. Domain labels must start and end with alphanumeric characters.<br>

* Contact addresses can take any values, but must consist of at least one non-space character.<br>

* Contact remarks are limited to alphanumeric characters and spaces.<br>

</div>

<div style="page-break-after: always;"></div>

### Adding a contact: `addC`

Adds a contact to the contact list.

Format:  `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]`

<div markdown="block" class="alert alert-warning">:warning: There will be an error if you:<br>

* Do not adhere to [field constraints](#section-1-contacts).<br>

* Add a contact that is the same as one already in the address book. Two contacts are the same if they have the same email or phone number.<br>

</div>
   
Example:

* `addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

* `addC n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 r/Weird person`

### Listing all contacts: `listC`

Shows all contacts stored in the contact list.

Format: `listC`

<div style="page-break-after: always;"></div>

### Deleting a contact: `deleteC`

Deletes a contact from the contact list.

Format: `deleteC INDEX`

* Index of a contact is its index number on the currently shown contact list.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Enter 0 or a negative number as INDEX.<br>

* Enter a number greater than the currently shown list size as INDEX.<br>

</div>

Examples:

* `listC` followed by `deleteC 1` deletes the first contact in the address book.

* `findC n/John` followed by `deleteC 1` deletes the first result of the `findC` command.

<div style="page-break-after: always;"></div>

### Editing a contact: `editC`

Edits the information fields (e.g. name, mobile number, email address) of an existing contact in the contact list.

Format: `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]`

* Index of a contact is its index number on the currently shown contact list.

* Input values will replace existing values.

<div markdown="block" class="alert alert-warning">:warning: There will be an error if you:<br>

* Do not adhere to [field constraints](#section-1-contacts).<br>

* Edit the contact such that it becomes a duplicate contact to one already in the contact list. Two contacts are the same if they have the same email or phone number.<br>

* Enter 0 or a negative number as INDEX.<br>

* Enter a number greater than the currently shown list size as INDEX.<br>

* Do not provide at least one of the optional fields.<br>

</div>

Example:

* `editC 1 n/John p/12345678` edits the first contact’s name to be John and phone number to be 12345678.

<div style="page-break-after: always;"></div>

### Finding a contact: `findC`

Finds a contact using one or more information fields (e.g. name, phone number, email, address, and/or remark).

Format: `findC [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]`

* The search is case-insensitive, e.g. `dr doofenshmirtz` will match `Dr Doofenshmirtz`.

* Only full words will be matched, e.g. `John` will not match `Johnny`.

* Contacts matching at least one keyword will be returned, e.g. `n/Perry Dr`
  will match contacts with name `Perry the Platypus` and `Dr Doofenshmirtz`.

* Successive `findC` commands are not cumulative, e.g. `findC n/John` followed by `findC n/Doe` will return the same result as `findC n/Doe`.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Do not adhere to the [field constraints](#section-1-contacts).<br>

* Do not provide at least one optional field.<br>

</div>

Examples:

* `findC n/flynn` will return `Candace Flynn` and `Phineas Flynn`.

* `findC n/john p/12345678 e/john@gmail.com a/123` will return contacts with name containing the word `john`, phone number `12345678`, email `john@gmail.com`, and address containing `123`.

<div style="page-break-after: always;"></div>

### Filtering contacts by label: `filterC`

Filters contacts whose label(s) contain any of the given keywords.

Format: `filterC KEYWORD [MORE_KEYWORDS]...`

* The filter is case-sensitive, e.g. `cs2103t` will not match `CS2103T`.

* Only full words will be matched, e.g. `math` will not match `mathematics`.

* Successive `filterC` commands are not cumulative, e.g. `filterC cs2101` followed by `filterC cs2103t` will return the same result as `filterC cs2103t`.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Do not adhere to label name constraints. Label names must be alphanumeric with no spaces.

</div>

Examples:

* `filterC cs2103t` will return contacts with label `cs2103t`.

* `filterC cs2103t cs2101` will return contacts with labels `cs2103t` or `cs2101`.

<div style="page-break-after: always;"></div>

### Copying contact emails by label: `copyC`

Displays a string of emails of contacts with a label that matches the given keyword for easier copy-pasting.

Format: `copyC KEYWORD`

* `copyC` is case-sensitive, e.g. `CS2103T` will not match `cs2103t`.

* Only full words will be matched, e.g. `cs2103t` will not match `cs2103`.

<div markdown="block" class="alert alert-warning">:warning: There will be an error if you:<br>

* Do not adhere to label name constraints. Label names must be alphanumeric with no spaces.<br>

</div>

Example:

* `copyC CS2103T` returns a string of emails of contacts that contain the label `CS2103T`.

<figure>
<img src="images/copyC.png" alt="copyC" style="width:100%">
<figcaption align = "center"><i>Fig.7 - Result of copyC when applied to contacts with the label "CS2103T"</i></figcaption>
</figure>

[[Back to Table of Contents](#table-of-contents)]

<div style="page-break-after: always;"></div>

## Section 2: Tasks

These commands allow you to maintain a handy to-do list so you can prioritise what needs to be done first.
Tasks can be archived once they are completed, so you can keep track of your progress.
Monitor your progress, track deadlines and archive tasks with a few simple commands.
Leave your task management to YellowBook, so you can do your best work.

<div markdown="block" class="alert alert-info">

:information_source: Notes on tasks:<br>

* Tasks have no labels by default.<br>

* Task description should only contain alphanumeric characters and spaces, and it should not be blank.<br>

* Task deadline must be in the format dd-mm-yyyy, e.g. `25-12-2022`.<br>

</div>


### Adding a task: `addT`

Adds a task to the task list.

Format: `addT d/DESCRIPTION D/DEADLINE`

* Newly added tasks are marked as not done by default.

* Tasks that are past their deadline can still be added since overdue tasks might have to be completed still.

<div markdown="block" class="alert alert-warning">:warning: There will be an error if you:<br>

* Add a task that is the same as one already in the address book. Two tasks are the same if they have the same description, deadline and labels.<br>

* Do not adhere to [field constraints](#section-2-tasks).<br>

</div>
 
Example:

* `addT d/buy milk D/12-09-2022` will add the task "buy milk" with deadline 12 September 2022.

<div style="page-break-after: always;"></div>

### Listing all non-archived tasks: `listT`

Shows all (non-archived) tasks stored in the task list.

Format: `listT`

* Newly added tasks are marked as unarchived by default.

<div markdown="block" class="alert alert-info">

:bulb:<br>

* Archived tasks can be viewed using the [`listAT`](#listing-all-archived-tasks-listat) command.<br>

* Task can be archived using the [`archiveT`](#archiving-a-task-archivet) command.

</div>

<div style="page-break-after: always;"></div>

### Listing all archived tasks: `listAT`

Shows all archived tasks stored in the task list.

Format: `listAT`

<div markdown="block" class="alert alert-info">

:bulb:<br>

* Unarchived tasks can be viewed using the [`listT`](#listing-all-non-archived-tasks-listt) command.

* Task can be unarchived using the [`unarchiveT`](#unarchiving-a-task-unarchivet) command.

</div>

<figure>
<img src="images/listAT.png" alt="listAT" style="width:100%">
<figcaption align = "center"><i>Fig.8 - Result of listAT</i></figcaption>
</figure>

<div style="page-break-after: always;"></div>

### Deleting a task: `deleteT`

Deletes a task from the task list.

Format: `deleteT INDEX`

* Index of a task is its index number on the currently shown task list.

* INDEX must be a positive integer more than 0.

<div markdown="block" class="alert alert-warning">:warning: There will be an error if you:<br>

* Enter 0 or a negative number as INDEX.<br>

* Enter a number greater than the currently shown list size as INDEX.<br>

</div>

Examples:

* `listT` followed by `deleteT 1` deletes the first task in the task list.

* `findT d/book` followed by `deleteT 1` deletes the first result of the `findT` command.

<div style="page-break-after: always;"></div>

### Editing a task: `editT`

Edits the information fields (e.g. description, deadline) of an existing task in the task list.

Format: `editT INDEX [d/DESCRIPTION] [D/DEADLINE]`

* Index of a task is its index number on the currently shown task list.
 
* Input values will replace existing values.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Do not adhere to [field constraints](#section-2-tasks).<br> 

* Enter 0 or a negative number as INDEX.<br>

* Enter a number greater than the currently shown list size as INDEX.<br>

* Do not provide at least one of the optional fields.<br>

* Edit the task such that it becomes a duplicate task to one already in the task list. Two tasks are the same if they have the same description, deadline and labels.<br>

</div>

Example:

* `editT 1 d/sleep D/22-10-2022` edits the first task’s description to be "sleep" and deadline to be 22-10-2022.

<div style="page-break-after: always;"></div>

### Finding a task: `findT`

Finds tasks using one or more information fields (e.g. description, deadline, and/or completion status).

Format: `findT [d/DESCRIPTION] [D/DEADLINE] [s/STATUS]`

* Task status must be either `complete` or `incomplete`.

* Both archived and unarchived tasks containing the search terms will be displayed.

* The search is case-insensitive, e.g. `homework` will match `HOMEWORK`.

* Only full words will be matched, e.g. `math` will not match `mathematics`.

* Task descriptions matching at least one keyword will be returned, e.g. `d/cs2103t cs2101` will match `cs2103t tutorial` and `cs2101 reflection`.

* Successive `findT` commands are not cumulative, e.g. `findT d/math` followed by `findT d/homework` will return the same result as `findT n/homework`.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Do not adhere to the [field constraints](#section-2-tasks).<br>

* Do not provide at least one optional field.<br>

</div>

Examples:

* `findT s/incomplete` will return tasks that are not complete.

* `findT d/cs2103t D/25-12-2022 s/complete` will return tasks with descriptions containing `cs2103t`, deadline `25th December 2022`, and completion status `complete`.

<div style="page-break-after: always;"></div>

### Filtering tasks by label: `filterT`

Filters tasks whose label(s) contain any of the given keywords.

Format: `filterT KEYWORD [MORE_KEYWORDS]...`

* Both archived and unarchived tasks containing specified labels will be displayed.

* The filter is case-sensitive, e.g. `cs2103t` will not match `CS2103T`.

* Only full words will be matched, e.g. `math` will not match `mathematics`.

* Successive `filterT` commands are not cumulative, e.g. `filterT cs2103t` followed by `filterT math` will return the same result as `filterT math`.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Do not adhere to label name constraints. Label names must be alphanumeric with no spaces.

</div>

Examples:

* `filterT cs2103t` will return tasks with label `cs2103t`.

* `filterT cs2103t cs2101` will return tasks with labels `cs2103t` or `cs2101`.

<div style="page-break-after: always;"></div>

### Marking a task as complete: `markT`

Marks a task in the task list as complete.

Format: `markT INDEX`

* Index of a task is its index number on the currently shown task list.

<div markdown="block" class="alert alert-warning">:warning: There will be an error if you:<br>

* Enter 0 or a negative number as INDEX.<br>

* Enter a number greater than the currently shown list size as INDEX.<br>

</div>

Examples:

* `listT` followed by `markT 1` marks the first task in the displayed task list as done.

* `findT d/book` followed by `markT 1` marks the first result of the `findT` command as done.

<figure>
<img src="images/markT.png" alt="markT" style="width:100%">
<figcaption align = "center"><i>Fig.9 - Result of markT when applied to the first task in the list</i></figcaption>
</figure>

<div style="page-break-after: always;"></div>

### Marking a task as incomplete: `unmarkT`

Marks a task in the task list as incomplete.

Format: `unmarkT INDEX`

* Index of a task is its index number on the currently shown task list.

<div markdown="block" class="alert alert-warning">:warning: There will be an error if you:<br>

* Enter 0 or a negative number as INDEX.<br>

* Enter a number greater than the currently shown list size as INDEX.<br>

</div>

Examples:

* `listT` followed by `unmarkT 1` marks the first task in the displayed task list as undone.

* `findT d/book` followed by `unmarkT 1` marks the first result of the `findT` command as undone.

<div style="page-break-after: always;"></div>

### Archiving a task: `archiveT`

Archives a task in the displayed task list, removing it from main (unarchived) task list.

Format: `archiveT INDEX`

* Archived task list will be displayed after executing this command.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Enter 0 or a negative number as INDEX.<br>

* Enter a number greater than the size of displayed task list as INDEX.<br>

</div>

Examples:

* `listT` followed by `archiveT 1` archives the first task in the displayed task list.

* `findT d/book` followed by `archiveT 1` archives the first result of the `findT` command.

<div style="page-break-after: always;"></div>

### Unarchiving a task: `unarchiveT`

Unarchives a task in the displayed task list, adding it to the main (unarchived) task list.

Format: `unarchiveT INDEX`

* Main (unarchived) task list will be displayed after executing this command.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Enter 0 or a negative number as INDEX.<br>

* Enter a number greater than the size of displayed task list as INDEX.<br>

</div>

Examples:

* `listAT` followed by `unarchiveT 1` unarchives the first task in the displayed task list.

* `findT d/book` followed by `unarchiveT 1` unarchives the first result of the `findT` command.

<div style="page-break-after: always;"></div>

### Listing tasks with deadlines up to and including the specified date: `remindT`

Lists tasks in task list with deadlines up to and including the specified date.

Format: `remindT DEADLINE`

* Both completed and incomplete tasks are listed.

* Task with deadlines that are already past are also listed.

<div markdown="span" class="alert alert-info">:bulb:Tasks that are complete are also listed so users are reminded to delete or archive them.
</div>

<div markdown="block" class="alert alert-warning">:warning: There will be an error if you:<br>

* Do not adhere to deadline constraints. Deadline must be in dd-mm-yyyy format.<br>

</div>

Example:

* `remindT 12-09-2022` will list all tasks with deadlines up to and including 12-09-2022.

<figure>
<img src="images/remindT.png" alt="remindT" style="width:100%">
<figcaption align = "center"><i>Fig.10 - Result of remindT when only one task is on 12-09-2022 or earlier</i></figcaption>
</figure>

<div style="page-break-after: always;"></div>

### Showing the percentage of tasks with the specified tags that are completed: `progressT`

Shows the percentage of tasks whose label(s) contain any of the given keywords that are complete to one decimal place of accuracy.

Format: `progressT KEYWORD [MORE_KEYWORDS]…`

* The filter is case-sensitive, e.g. `cs2103t` will not match `CS2103T`.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Tasks with labels matching at least one keyword will be returned.

* Both complete and incomplete tasks are listed.

* Both archived and unarchived tasks are listed.

* Tasks with deadlines that are already past are also listed.

<div markdown="block" class="alert alert-warning">:warning: There will be an error if you:<br>

* Do not adhere to label name constraints. Label names must be alphanumeric with no spaces.<br>

</div>

Example:

* `progressT cs2103t` will show the percentage of tasks with label `cs2103t` that are completed, then list all tasks with labels matching at least one keyword.

<figure>
<img src="images/progressT.png" alt="progressT" style="width:100%">
<figcaption align = "center"><i>Fig.11 - Result of progressT where only one of two GEA1000 tasks has been completed</i></figcaption>
</figure>

<div style="page-break-after: always;"></div>

### Sorting all tasks by deadline: `sortD`

Sorts all tasks in the task list by deadline.

Format: `sortD`

* Adding or editing a task will not affect the sorted order of the task list.

* The list remains in this sorted order until a different sort command is used.

<figure>
<img src="images/sortD.png" alt="sortD" style="width:100%">
<figcaption align = "center"><i>Fig.12 - Result of sortD</i></figcaption>
</figure>

<div style="page-break-after: always;"></div>

### Sorting all tasks by id: `sortI`

Sorts all tasks in the task list by id.

Format: `sortI`

* Id is the order in which the tasks were added.

* Adding or editing a task will not affect the sorted order of the task list.

* The list remains in this sorted order until a different sort command is used.

<figure>
<img src="images/sortI.png" alt="sortI" style="width:100%">
<figcaption align = "center"><i>Fig.13 - Result of sortI</i></figcaption>
</figure>

[[Back to Table of Contents](#table-of-contents)]

<div style="page-break-after: always;"></div>

## Section 3: Labels

These commands allow you to further organise people and tasks into subgroups depending on the nature of the project you
are working on together.
Whether it be a software engineering module or a business pitch, you can customise every person and task.
With our label feature, managing your numerous projects on the go has just gotten a lot easier.

<div markdown="block" class="alert alert-info">

:information_source: Notes on labels:<br>

* Labels must be alphanumeric and one word long.<br>

* Labels used in commands are case-sensitive. e.g. `CS2103T` is different from `cs2103t`.<br>

</div>

<div style="page-break-after: always;"></div>

### Adding a label to a contact/task: `addL`

Adds a label to an existing contact/task in YellowBook. 
At the same time, the label is added to the label list, shown under the "tags" tab of the app.
This list is unique, meaning each label with a distinct name is only shown once, even if more than one contact/task has the same label.

Format: `addL c/INDEX t/INDEX l/label_NAME`

* Index of a contact is its index number on the currently shown contact list.

* Index of a task is its index number on the currently shown task list.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Do not adhere to the [field constraints](#section-3-labels).

* Specify more than one contact or more than one task.

* Try to delete a label from a contact or task that does not have it.

* Enter 0 or a negative number as INDEX.

* Enter a number greater than the currently shown list size as INDEX.

</div>

Example:

* `addL c/3 t/12 l/CS2103T` will add the label "CS2103T" to the 3rd contact on the contact list and 12th task on the task list.

<div style="page-break-after: always;"></div>

### Listing all labels: `listL`

Shows all labels stored in the label list.

Format: `listL`

### Deleting a label from a contact/task: `deleteL`

Deletes a label from an existing contact/task in YellowBook.

If contact/task is last remaining contact/task with said label, label is deleted from the label list.
Otherwise, it is only deleted from the specified contact/task label list.

Format: `deleteL c/INDEX t/INDEX l/label_NAME`

* Index of a contact is its index number on the currently shown contact list.

* Index of a task is its index number on the currently shown task list.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Do not adhere to the [field constraints](#section-3-labels).

* Specify more than one contact or more than one task.

* Try to add a label to a contact or task that already has it.

* Enter 0 or a negative number as INDEX.

* Enter a number greater than the currently shown list size as INDEX.

</div>

Example:

* `deleteL c/12 t/14 l/CS2101` will remove the label "CS2101" from the 12th contact on the contact list and 14th task on the task list.

<div style="page-break-after: always;"></div>

### Deleting all contacts and tasks by label: `deleteA`

Deletes all contacts and tasks that contain the label(s) specified. The label is also deleted.

Format: `deleteA LABEL_NAME [MORE_LABEL_NAMES]...`

* If a contact/task has multiple labels, it will not be deleted as long as it has at least one label.
  Instead, the labels will be removed from the contact/task.

<div markdown="block" class="alert alert-warning">

:warning: There will be an error if you:<br>

* Do not adhere to the [field constraints](#section-3-labels).<br>

* Try to delete a label that does not exist.<br>

</div>

<figure>
<img src="images/deleteA.png" alt="deleteA" style="width:100%">
<figcaption align = "center"><i>Fig.14 - Result of deleteA when applied to all contacts/tasks from GEA1000</i></figcaption>
</figure>


[[Back to Table of Contents](#table-of-contents)]

<div style="page-break-after: always;"></div>

## Section 4: Other Useful Features

### Viewing help: `help`

Shows a window with a link to this user guide and latest release of YellowBook.

Format: `help`

### Undoing a command: `undo`

Undoes the last command.

Format: `undo`

* For exceptionally large contact/task lists, it may take a few seconds to undo the command.

* Undo is not available for commands that do not modify the contact/task data (e.g. listC, listT, help, findC, findT, filterC, filterT etc.)

### Redoing a command: `redo`

Redoes the last command.

Format: `redo`

* For exceptionally large contact/task lists, it may take a few seconds to redo the command.

* Redo is not available for commands that do not modify the contact/task data (e.g. listC, listT, help, findC, findT, filterC, filterT etc.)

### Clearing YellowBook data: `clear`

Clears all data from YellowBook.

Format: `clear`

### Exiting YellowBook: `exit`

Closes the YellowBook program.

Format: `exit`

[[Back to Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Automatic tab switching

Depending on the command you enter, you will see the open tab in the GUI switch automatically.
For example, when using a task-related command, the tab switches to "Task" and the task list is displayed.

The result of the entered command is displayed.
For example, after adding a new contact, the list shown on the GUI is the updated list with your new contact included.

[[Back to Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## YellowBook data
### Saving the data

YellowBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

YellowBook data are saved as a JSON file `[JAR file location]/data/yellowbook.json`. You are strongly encouraged to use the YellowBook application's commands rather than edit the data file directly as there may be invalid data entered.

Advanced users who wish to edit the data file should note the following:

- `Id` field of a `Contact` is unique and should comply with the string representation of [Java UUID](https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html#toString--)
- `Id` field of a `Task` is unique and should be an number greater than zero
- Fields for [`Contacts`](#section-1-contacts), [`Tasks`](#section-2-tasks) and [`Labels`](#section-3-labels) should comply with the respective field constraints. 

<div markdown="block" class="alert alert-danger">:exclamation:
If your changes to the data file makes its format invalid, YellowBook will discard all data and start with an empty data file at the next run.
</div>

[[Back to Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous YellowBook home folder.

**Q**: My contact's full name includes special characters (e.g. s/o). Why can't I add it?<br>
**A**: YellowBook does not currently support special characters in names as it is not a critical feature for students managing their project contacts. We will be adding support for special characters in future versions.

[[Back to Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

| Command        | Mnemonic                                        | Format, Examples                                                                                                                                                                                   |
|----------------|-------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **help**       | -                                               | `help`                                                                                                                                                                                             |
| **undo**       | -                                               | `undo`                                                                                                                                                                                             |
| **redo**       | -                                               | `redo`                                                                                                                                                                                             |
| **clear**      | -                                               | `clear`                                                                                                                                                                                            |
| **exit**       | -                                               | `exit`                                                                                                                                                                                             |
| **listC**      | **list** **C**ontact                            | `listC`                                                                                                                                                                                            |
| **addC**       | **add** **C**ontact                             | `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]` <br> e.g., `addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`                                             |
| **deleteC**    | **delete** **C**ontact                          | `deleteC INDEX` <br> e.g., `deleteC 1`                                                                                                                                                             |
| **editC**      | **edit** **C**ontact                            | `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]` <br> e.g., `editC 1 n/John p/12345678`                                                                                           |
| **findC**      | **find** **C**ontact                            | `findC [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]` <br/> e.g., `findC n/Ferb`                                                                                                             |
| **filterC**    | **filter** **C**ontact                          | `filterC KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterC cs2103t`                                                                                                                                     |
| **copyC**      | **copy** **C**ontact emails                     | `copyC KEYWORD` <br> e.g. `copyC CS2103T`                                                                                                                                                          |
| **listT**      | **list** **T**asks                              | `listT`                                                                                                                                                                                            |
| **listAT**     | **list** **A**rchived **T**asks                 | `listAT`                                                                                                                                                                                           |
| **addT**       | **add** **T**ask                                | `addT d/DESCRIPTION D/DEADLINE` <br> e.g., `addT d/buy milk D/12-09-2022`                                                                                                                          |
| **deleteT**    | **delete** **T**asks                            | `deleteT INDEX` <br/> e.g., `deleteT 12`                                                                                                                                                           |
| **editT**      | **edit** **T**asks                              | `editT INDEX [d/DESCRIPTION] [D/DEADLINE]` <br> e.g., `editT 1 d/sleep D/22-10-2022`                                                                                                               |
| **markT**      | **mark** **T**ask as completed                  | `markT INDEX` <br> e.g., `markT 1`                                                                                                                                                                 |
| **unmarkT**    | **unmark** **T**ask as not completed            | `unmarkT INDEX` <br> e.g., `unmarkT 1`                                                                                                                                                             |
| **archiveT**   | **archive** **T**ask                            | `archiveT INDEX` <br> e.g., `archiveT 1`                                                                                                                                                           |
| **unarchiveT** | **unarchive** **T**ask                          | `unarchiveT INDEX` <br> e.g., `unarchiveT 1`                                                                                                                                                       |
| **findT**      | **find** **T**asks                              | `findT [d/DESCRIPTION] [D/DEADLINE] [s/STATUS]` <br> e.g., `findT d/homework`                                                                                                                      |
| **filterT**    | **filter** **T**asks                            | `filterT KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterT cs2103t`                                                                                                                                     |
| **remindT**    | **remind** **T**asks due on/before certain date | `remindT DEADLINE` <br/> e.g., `remindT 12-09-2022`                                                                                                                                                |
| **progressT**  | **progress** of **T**ask with label             | `progressT KEYWORD [MORE_KEYWORDS]` <br/> e.g., `progressT cs2103t`                                                                                                                                |
| **sortD**      | **sort** by **D**eadline                        | `sortD`                                                                                                                                                                                            |
| **sortI**      | **sort** by **I**d                              | `sortI`                                                                                                                                                                                            |
| **listL**      | **list** **L**abels                             | `listL`                                                                                                                                                                                            |
| **addL**       | **add** **L**abel to contact or task            | `addL c/INDEX l/LABEL_NAME [l/MORE_LABELS]` OR  `addL t/INDEX l/LABEL_NAME [l/MORE_LABELS]` OR `addL c/INDEX t/INDEX l/LABEL_NAME [l/MORE_LABELS]` <br> e.g., `addL c/3 t/12 l/CS2103T`            |
| **deleteL**    | **delete** **L**abel from contact or task       | `deleteL c/INDEX l/LABEL_NAME [l/MORE_LABELS]` OR `deleteL t/INDEX l/LABEL_NAME [l/MORE_LABELS]` OR `deleteL c/INDEX t/INDEX l/LABEL_NAME [l/MORE_LABELS]` <br> e.g., `deleteL c/12 t/14 l/CS2101` |
| **deleteA**    | **delete** **A**ll contact(s)/task(s) with tag  | `deleteA LABEL_NAME [MORE_LABEL_NAMES]` <br> e.g., `deleteA cs2103t`                                                                                                                               |

[[Back to Table of Contents](#table-of-contents)]
