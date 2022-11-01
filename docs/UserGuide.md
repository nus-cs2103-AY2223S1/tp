---
layout: page
title: User Guide
---
**Plannit** is a **unified desktop application** that aims to **help NUS
students manage their academic details.**  It will be the **go-to platform**
for you to access all modules links and information without needing to
tediously navigate through multiple websites.

This application is **optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## 0. How to use this guide
This guide is targeted at new users who are looking at learning more about 
our app.
1. If this is your very first time using the application, welcome! You may head 
   over to the [quick start](#1-quick-start) to begin your journey with us!
2. If you already have a specific feature in mind, you can check out the 
   [command summary](#11-command-summary) section.
3. Throughout this guide, you will encounter the icons :bulb:,
  :information_source: or :eye: icon. Their meanings are as follows:

| Icon                 | Meaning                                                                     |
|----------------------|-----------------------------------------------------------------------------|
| :bulb:               | A relevant tip which you may find helpful.                                  |
| :information_source: | Information that you should **must** note of when using a specific feature. |
| :eye:                | A feature related to the section you are currently looking at.              |


## 1. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `plannit.jar` [here](https://github.com/AY2223S1-CS2103T-T10-1/tp/releases/).

3. Copy the file to the folder you want to use as the _home folder_ for Plannit.

4. Double-click the file to start the app. The window below should appear in a 
   few seconds. <br>


    | Screenshot of Plannit                                                                     | Description of labels                                                                                            |
    |-------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
    | {::nomarkdown}<p align="center"><img src="images/home-labelled.png" width="500"/></p>{:/} | {::nomarkdown}<ol><li>Command box</li><li>Result display</li><li>Modules list</li><li>Persons list</li></ol>{:/} |


5. Type the command in the command box and press Enter to execute it. e.g.
   `add-module m/CS2103T`. For more commands, you may refer to the [Command summary](#11-command-summary).
   Alternatively, head over to [Features](#2-features) to find the details of each command.

6. After a command has been executed, a message will be displayed in the result 
   display to indicate whether the command's execution has been successful.

### 1.1. Command summary

| Action                                                        | Format                                                                  | Short Description                                                               |
|---------------------------------------------------------------|-------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| [`add-module`](#211-add-module)                               | `add-module      m/MODULE_CODE [t/MODULE_TITLE]`                        | Add module with a module code and optional module title                         |
| [`delete-module`](#212-delete-module)                         | `delete-module   m/MODULE_CODE`                                         | Delete module by module code                                                    |
| [`edit-module`](#213-edit-module)                             | `edit-module     INDEX ([m/MODULE_CODE] [t/MODULE_TITLE])`              | Edit module belonging to the specified index currently displayed on the screen  |
| *[`find-module`](#214-find-module)                            | `find-module     KEYWORD`                                               | Find module that starts with specified keyword in home page                     |
| *[`list-module`](#215-list-module)                            | `list-module`                                                           | List all modules in home page after finding                                     |
| [`add-task`](#221-add-task-add-task)                          | `add-task        m/MODULE_CODE td/TASK_DESCRIPTION`                     | Add task with specified module code and task description                        |
| [`delete-task`](#222-delete-task-delete-task)                 | `delete-task     m/MODULE_CODE tn/TASK_NUMBER`                          | Delete task corresponding to specified task number of specified module code     |
| [`swap-task`](#223-reorder-tasks-swap-swap-task)              | `swap-task       m/MODULE_CODE ts/FIRST_TASK_NUMBER SECOND_TASK_NUMBER` | Swap the order of tasks in the task list of a specified module                  |
| [`add-link`](#231-add-link)                                   | `add-link        m/MODULE_CODE l/LINK_URL la/LINK_ALIAS`                | Add link URL with an alias to a module by its specified module code             |
| [`delete-link`](#232-delete-link)                             | `delete-link     m/MODULE_CODE la/LINK_ALIAS`                           | Delete link URL from a module by its specified module code and alias            |
| [`open-link`](#233-open-link)                                 | `open-link       m/MODULE_CODE la/LINK_ALIAS`                           | Open link URL from a module by its specified module code and alias              |
| [`add-person`](#241-add-person)                               | `add-person      n/NAME    e/EMAIL    p/PHONE_NUMBER`                   | Add contact with specified name, email, and phone number                        |
| [`add-person-to-module`](#242-add-person-to-module)           | `add-person-to-module m/MODULE_CODE n/NAME`                             | Add person with specified name to the module with the specified module code     |
| [`delete-person`](#243-delete-person)                         | `delete-person   n/NAME`                                                | Delete contact belonging to the specified name                                  |
| [`delete-person-from-module`](#244-delete-person-from-module) | `delete-person-from-module m/MODULE_CODE n/NAME`                        | Delete person with specified name from a module with specified module code      |
| [`edit-person`](#245-edit-person)                             | `edit-person     INDEX ([n/NAME] [e/EMAIL]  [p/PHONE_NUMBER])`          | Edit contact belonging to the specified index currently displayed on the screen |
| *[`find-person`](#246-find-person)                            | `find-person     KEYWORD`                                               | Find contacts that starts with specified keyword                                |
| *[`list-person`](#247-list-person)                            | `list-person`                                                           | List all contacts                                                               |
| [`home`](#251-navigate-to-home)                               | `home`                                                                  | Navigate to the home page                                                       |
| [`goto`](#252-navigate-between-modules)                       | `goto MODULE_CODE`                                                      | Navigate to specified module page                                               |
| [`exit`](#26-exiting-the-program)                             | `exit`                                                                  | Exit the program                                                                |

<div markdown="span" class="alert alert-info"> :information_source: **Note:**<br/> 
Features marked with * can only be utilised when users are at the home page.
</div>

<div markdown="span" class="alert alert-info">:eye: **See also:**<br/>  
[Peeking at tasks](#224-peeking-at-tasks).
</div>

## 2. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add m/MODULE`, `MODULE` is a parameter which can be used as `add m/MODULE`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [e/EMAIL]` can be used as `n/John Doe e/john@u.nus.edu` or as `n/John Doe`.

* A round bracket surrounding multiple square brackets indicate a need for at least one of the items in square brackets
to be present.  
  e.g `([n/NAME] [e/EMAIL] [p/PHONE_NUMBER])` requires at least one of either `n/NAME`, `e/EMAIL`, or `p/PHONE_NUMBER`
to be present.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
the parameter will be taken.<br>
  e.g. if you specify `p/81234123 p/99999999`, only `p/99999999` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as
  `home`, `list-module`, `list-person` and `exit`) will be ignored.<br>
  e.g. if the command specifies `home 123`, it will be interpreted as `home`.
</div>

### 2.1. Modules

#### 2.1.1. Add module
You can add a module into Plannit.

This command will require one flag, and one flag is optional:
* `m/`: To be followed by the module code of the module to be added into Plannit.
* `t/`: (Optional flag) To be followed by the module title of the module to be added into Plannit.

Format: `add-module m/MODULE_CODE [t/MODULE_TITLE]`
* Module code will be automatically treated as uppercase. For example, `cs1231s` will be treated 
  as `CS1231S`.
* You may optionally add a module title. If provided module title is empty, then Plannit 
interprets it as that there is no module title. 
* You cannot add a duplicate module code.

Examples:
```
add-module m/CS2103T
```
OR
```
add-module m/CS2103T t/
```
In either of the above examples, we are adding a module `CS2103T` without a title.

```
add-module m/CS2103T t/Software Engineering
```
In the above example, we are adding a module `CS2103T` which has the title `Software Engineering`.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Adding a module will bring you back to the home page.
</div>

#### 2.1.2. Delete module
You can delete the module with the indicated module code from Plannit.

This command will require one flag:
* `m/`: To be followed by the module code of the module to be deleted from Plannit.

Format: `delete-module m/MODULE_CODE`
* You cannot delete a non-existent module code.

Example:

```
delete-module m/CS2103T
```
In the above example, we are deleting module `CS2103T` from Plannit.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Deleting a module will bring you back to the home page.
</div>

#### 2.1.3. Edit module
You can edit a module on Plannit using the `edit-module` command.

This command will require an index and at least one of the following flags:
* `m/`: To be followed by the new module code of the module to be edited on Plannit.
* `t/`: To be followed by the new module title of the module to be edited on Plannit.

Format: `edit-module INDEX ([m/MODULE_CODE] [t/MODULE_TITLE])`
* `INDEX` is the currently displayed index number of the module you are editing for on the screen.
* You cannot edit a module such that the new module code coincides with the module code of another 
  module on Plannit (case-insensitive).
* If `MODULE_TITLE` is empty, then the module title will be deleted from Plannit.

Examples:

```
edit-module 3 m/CS2103T
```
In the above example, we are changing the module code of the third module on Plannit to `CS2103T`.

```
edit-module 3 m/CS2103T t/
```
In the above example, we are changing the module code of the third module on Plannit to `CS2103T`, 
and at the same time, removing the module title.

```
edit-module 3 m/CS2103T t/Software Engineering
```
In the above example, we are changing the module code of the third module on Plannit to `CS2103T`,
and at the same time, changing the module title to `Software Engineering`.

```
edit-module 3 t/Software Engineering
```
In the above example, we are changing the module title of the third module on Plannit to 
`Software Engineering`.

#### 2.1.4. Find module
When you are on the home page, you can search for modules whose module codes start with the specified keywords.

Format: `find-module KEYWORD`
* The KEYWORD is case-insensitive.

Examples:
```
find-module cs
```
OR
```
find-module CS
```
In either of the above examples, we find every module whose module code starts with CS in Plannit.

<div markdown="span" class="alert alert-info"> :information_source: **Note:**<br/>You will
remain on the home page after executing the `find-module` command. This is different
from the behavior of [`goto`](#252-navigate-between-modules-goto) command.
</div>

#### 2.1.5. List module
When you are on the home page, you may obtain the list of every module in Plannit.

Format: `list-module`

Example:
```
list-module
```
In the above example, we list every module that exist in Plannit.

<br>

### 2.2. Tasks
#### 2.2.1. Add task: `add-task`
Suppose a particular module has assigned you a task to be completed. You can 
keep track of it by adding it to Plannit using the `add-task` command.

<div markdown="span" class="alert alert-info">:information_source: 
**Note:**<br/> 
Make sure you have [added a module](#211-add-module-add-module) to Plannit 
before proceeding!
</div>

This command will require two prefixes:

| Field                            | Prefix | Constraints                                             |
|----------------------------------|--------|---------------------------------------------------------|
| **Module Code**                  | `m/`   | Can only be non-empty string of alphanumeric characters |
| **Task Description of New Task** | `td/`  | Can be any string of characters                         |

Format: `add-task m/MODULE_CODE td/TASK_DESCRIPTION`
* Each task must belong to a specific module.
* You should provide a module code of an existing module in Plannit.

Example:
```
add-task m/CS2103T td/Complete week 7's weekly assignments
```
In the above example, we are adding the task `Complete week 7's weekly assignments` to the
module `CS2103T`. Here's a screenshot of Plannit before and after executing the
command:

| Before executing the command                                                                                                                             | After executing the command                                                                                                                       |
|----------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| {::nomarkdown}<p align="center"><img src="images/add-task-before.png"/></p><p>Enter the command into the command box and hit <code>ENTER</code>.</p>{:/} | {::nomarkdown}<p align="center"><img src="images/add-task-after.png"/></p><p>A message will appear indicating that a task has been added.</p>{:/} |

<div markdown="span" class="alert alert-info">:bulb: **Tip:**<br/>
You may view the tasks added to a module by navigating to a module's page 
using the [`goto`](##252-navigate-between-modules-goto) command. Alternatively, 
you may double-click on a module to ["peek"](#224-peeking-at-modules) at a 
module's tasks while on the home page.
</div>

#### 2.2.2. Delete task: `delete-task`
Suppose you have completed a task and would now like to remove it from 
Plannit. You may delete the task using the `delete-task` command.

This command will require two prefixes:

| Field                   | Prefix | Constraints                                                                                                                                                       |
|-------------------------|--------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Module Code**         | `m/`   | Can only be non-empty string of alphanumeric characters                                                                                                           |
| **Task Number of Task** | `tn/`  | {::nomarkdown}Task numbers must: <ul><li>correspond to an existing task in the specified module</li> <li>be a positive integer (i.e. 1, 2, 3, ... )</li></ul>{:/} |                 

Format: `delete-task m/MODULE_CODE tn/TASK_NUMBER`
* You should provide a module code of an existing module in Plannit.
* You should provide a task number corresponding to that of an existing task in
the module.

Example:
```
delete-task m/CS2103T tn/2
```
In the above example, we are deleting task number **2** from the module
`CS2103T`. Here's a screenshot of Plannit before and after executing the 
command: 

| Before executing the command                                                                                                                                | After executing the command                                                                                                                            |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| {::nomarkdown}<p align="center"><img src="images/delete-task-before.png"/></p><p>Enter the command into the command box and hit <code>ENTER</code>.</p>{:/} | {::nomarkdown}<p align="center"><img src="images/delete-task-after.png"/></p><p>A message will appear indicating that a task has been deleted.</p>{:/} |

#### 2.2.3. Reorder tasks (swap): `swap-task`
Suppose you have an urgent task which you like to place at the very top of 
your list. You may change the order of tasks in your module's task list  using
the `swap-task` command.

This command will require two prefixes:

| Field                                                     | Prefix | Constraints                                                                                                                                                                                                                                                 |
|-----------------------------------------------------------|--------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Module Code**                                           | `m/`   | Can only be non-empty string of alphanumeric characters                                                                                                                                                                                                     |
| **Task Numbers of Tasks whose ordering is to be swapped** | `ts/`  | {::nomarkdown}The two task numbers must: <ul><li>be separated by a <code>SPACE</code> character ("<code> </code>") </li><li>correspond to a pair of existing tasks in the specified module</li> <li>be positive integers (i.e. 1, 2, 3, ... )</li></ul>{:/} |

Format: `swap-task m/MODULE_CODE ts/FIRST_TASK_NUMBER SECOND_TASK_NUMBER`
* You should provide a module code of an existing module in Plannit.
* You should provide a pair of task numbers corresponding to those of existing 
  tasks in the module.
* You may only specify two task numbers at once. Both task numbers must be 
  different.

Example:
```
swap-task m/CS2103T ts/1 3
```
In the above example, we are swapping the position of the first and third 
task within the task list of the module `CS2103T`. Here's a screenshot of 
Plannit before and after executing the command. Note that ["peek"](#224-peeking-at-tasks)
has been enabled for clarity:

| Before executing the command                                                                                                                              | After executing the command                                                                                                 |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| {::nomarkdown}<p align="center"><img src="images/swap-task-before.png"/></p><p>Enter the command into the command box and hit <code>ENTER</code>.</p>{:/} | {::nomarkdown}<p align="center"><img src="images/swap-task-after.png"/></p><p>Tasks 1 and 3 have swapped positions!</p>{:/} |

#### 2.2.4. Peeking at tasks
You may "peek" at a module's tasks by clicking on the module while on the 
`home` page. <br/>
 **Step 1**: Let's say you wish to view the tasks of `CS2103T SOFTWARE 
ENGINEERING` (module number 1)
<p align="center">
  <img src="images/home-page-with-cs2103-circled.png" width="500"/>
</p>

**Step 2**: You will notice a blue glow when hovering your mouse over the 
module
<p align="center">
  <img src="images/home-page-with-cs2103-glowing.png" width="500"/>
</p>

**Step 3**: Double-clicking on the blue area will allow you to view the 
 tasks of `CS2103T SOFTWARE ENGINEERING`
<p align="center">
  <img src="images/home-page-with-cs2103-tasks.png" width="500"/>
</p>

**Step 4**: Clicking again on the blue area will close the dropdown and hide 
the tasks
<p align="center">
  <img src="images/home-page-with-cs2103-glowing.png" width="500"/>
</p>

<div markdown="span" class="alert alert-info">:information_source: 
**Note:**<br/> 
You remain on the home page even when you "peek" at a module. To leave the 
home page and view more details of a specific module, you may use the 
[`goto`](#252-navigate-between-modules-goto) command.
</div>

<br>

### 2.3. Links
All links in Plannit are represented by a unique (case-sensitive) alias.

Alias:
* Alphanumeric and whitespace characters
* Character limit of 15
* At least 1 alphanumeric character

Link URL (Plannit provides no guarantee of the link's existence):
* At least one top-level domain (e.g. com)
* Domain length of 1 to 256 characters (e.g. for 'www.google.com', the domain is 'google')
* Supported by 'https' or 'http'

#### 2.3.1. Add link
You may add link(s) to a specific module using the `add-link` command. 
Each link URL is to be paired with a link alias, both of which are unique within a module.

Multiple links can be added at once. Link aliases will be paired with link URLs according to their respective
order of input (left-to-right).
If there exists a link URL or alias that is detected as invalid within a chained command,
none of the links in the command will be added.

This command will require three flags:
* `m/`: To be followed by the module code of the module which is associated with the link
* `l/`: To be followed by the link URL which is associated with the link
* `la/`: To be followed by the link alias which is associated with the link

Format: `add-link m/MODULE_CODE l/LINK_URL la/LINK_ALIAS`
* You cannot add a link to a non-existent module code.
* You cannot add a link using a link alias that already exists in the module represented by the module code.
* You cannot add a link using a link URL that already exists in the module represented by the module code.

Example:
```
add-link m/CS2040 l/visualgo.net/en la/visualgo
```
In the above example, we are adding the link with the URL `visualgo.net/en`
to the module with module code `CS2040`, represented by the link alias `visualgo`.

```
add-link m/CS2040 l/https://www.nusmods.com l/open.kattis.com la/nus mods la/kattis
```
In the above example, we are adding the links with the URL `https://www.nusmods.com` and `open.kattis.com`
to the module with module code `CS2040`, represented by the link alias `nus mods` and `kattis` respectively.

#### 2.3.2. Delete link
You may delete link(s) from a specific module using the `delete-link` command. 
Links will be deleted by means of their corresponding alias.

Multiple links can be deleted at once using their corresponding alias.
If there exists a link alias that is detected as invalid within a chained command,
none of the links in the command will be deleted.

This command will require two flags:
* `m/`: To be followed by the module code of the module which is associated with the link
* `la/`: To be followed by the link alias which is associated with the link

Format: `delete-link m/MODULE_CODE la/LINK_ALIAS`
* You cannot delete a link from a non-existent module code.
* You cannot delete a link using a non-existent link alias from an existing module.

Example:
```
delete-link m/CS2040 la/visualgo
```
In the above example, we are deleting the link with the URL `visualgo.net/en`
from the module with module code `CS2040`, using its corresponding link alias `visualgo`.

```
delete-link m/CS2040 la/nus mods la/kattis
```
In the above example, we are deleting the links with the URL `https://www.nusmods.com` and `open.kattis.com`
from the module with module code `CS2040`, using their corresponding link alias `nus mods` and `kattis` respectively.

#### 2.3.3. Open link
You may open link(s) from a specific module to your default browser using the `open-link` command.
Links will be opened by means of their corresponding alias.

An alternative way to open links is by means of clicking on their aliases on the application window.
Permissions from your operating system may be required for some users to use this feature.

Multiple links can be opened at once using its corresponding alias according to their order of input
(left to right). If there exists a link alias that is detected as invalid within a chained command,
the links to its left will be opened while the links to its right will not be opened.

This command will require two flags:
* `m/`: To be followed by the module code of the module which is associated with the link
* `la/`: To be followed by the link alias which is associated with the link

Format: `open-link m/MODULE_CODE la/LINK_ALIAS`
* You cannot open a link from a non-existent module code.
* You cannot open a link using a non-existent link alias from an existing module.

Example:
```
open-link m/CS2040 la/visualgo
```
In the above example, we are opening the link with the URL `visualgo.net/en`
from the module with module code `CS2040`, using its corresponding link alias `visualgo`.

```
open-link m/CS2040 la/nus mods la/kattis
```
In the above example, we are opening the links with the URL `https://www.nusmods.com` and `open.kattis.com`
from the module with module code `CS2040`, using their corresponding link alias `nus mods` and `kattis` respectively.
<br>

### 2.4. Contacts
#### 2.4.1. Add person
You may add a contact to Plannit using the `add-person` command.

This command will require three flags:

| Field     | Flag | Constraints                                                                                                                       |
|-----------|------|-----------------------------------------------------------------------------------------------------------------------------------|
| **Name**  | `n/` | {::nomarkdown}<ul><li>Is case sensitive</li> <li>Can only be non-empty string of alphanumeric characters and spaces</li></ul>{:/} |
| **Email** | `e/` | Can only be of the format [`local-part@domain`](#5-glossary)                                                                      |
| **Phone** | `p/` | Can only be 8 digits long                                                                                                         |

Format: `add-person n/NAME e/EMAIL p/PHONE_NUMBER`
* You cannot add a duplicate name into Plannit.

Example:
```
add-person n/Dinosaur Lim e/dinosaurlim@gmail.com p/91234567
```
In the above example, we are adding a contact with name `Dinosaur Lim`, email `dinosaurlim@gmail.com`, and phone number
`91234567` into Plannit.

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Adding a person will bring you back to the home page.
</div>

#### 2.4.2. Add person to module
You can add a person to a module using the `add-person-to-module` command. In other
words, an association between a person and a module will be created.

This command will require two flags:

| Field           | Flag | Constraints                                                                                                                       |
|-----------------|------|-----------------------------------------------------------------------------------------------------------------------------------|
| **Module Code** | `m/` | {::nomarkdown}<ul><li>Is non-case sensitive</li> <li>Can only be non-empty string of alphanumeric characters</li></ul>{:/}        |                                                             |
| **Name**        | `n/` | {::nomarkdown}<ul><li>Is case sensitive</li> <li>Can only be non-empty string of alphanumeric characters and spaces</li></ul>{:/} |

Format: `add-person-to-module m/MODULE_CODE n/NAME`
* If a person has already been added to a module, the person cannot be added to the module again.
* You can only add a person to a module if both the person and the module are currently
  displayed on screen.

Example:

```
add-person-to-module m/CS2103T n/Dinosaur Lim
```
In the above example, we are adding the person `Dinosaur Lim` to module `CS2103T`.

<div markdown="span" class="alert alert-info"> :information_source: **Note:**
If you wish to view a person added to a particular module, you may do so by navigating to that
module using the [`goto`](#252-navigate-between-modules) command.
</div>

#### 2.4.3. Delete person
You may delete a contact from Plannit using the `delete-person` command.

This command will require one flag:

| Field    | Flag | Constraints                                                                                                                       |
|----------|------|-----------------------------------------------------------------------------------------------------------------------------------|
| **Name** | `n/` | {::nomarkdown}<ul><li>Is case sensitive</li> <li>Can only be non-empty string of alphanumeric characters and spaces</li></ul>{:/} |

Format: `delete-person n/NAME`
* You cannot delete a non-existent contact in Plannit.
* You can only delete a person from Plannit if the person is displayed on the current screen.

Example:
```
delete-person n/Dinosaur Lim
```
In the above example, we are deleting a contact with name `Dinosaur Lim` from Plannit.

#### 2.4.4. Delete person from module
You can remove the association between a person and a module (if it exists) using the `delete-person-from-module`
command.

This command will require two flags:

| Field           | Flag | Constraints                                                                                                                       |
|-----------------|------|-----------------------------------------------------------------------------------------------------------------------------------|
| **Module Code** | `m/` | {::nomarkdown}<ul><li>Is non-case sensitive</li> <li>Can only be non-empty string of alphanumeric characters</li></ul>{:/}        |                                                             |
| **Name**        | `n/` | {::nomarkdown}<ul><li>Is case sensitive</li> <li>Can only be non-empty string of alphanumeric characters and spaces</li></ul>{:/} |

Format: `delete-person-from-module m/MODULE_CODE n/NAME`
* You can only delete the specified person from the specified module if the person was originally associated to the
  module.
* You can only delete a person from a module if both the specified module and person are displayed on the current screen.

Example:

```
delete-person-from-module m/CS2103T n/Dinosaur Lim
```
In the above example, we are deleting the person `Dinosaur Lim` from module `CS2103T`.


#### 2.4.5. Edit person
You may edit a contact using the `edit-person` command.

This command will require an index and minimally any of the three flags:

| Field     | Flag | Constraints                                                                                                                       |
|-----------|------|-----------------------------------------------------------------------------------------------------------------------------------|
| **Name**  | `n/` | {::nomarkdown}<ul><li>Is case sensitive</li> <li>Can only be non-empty string of alphanumeric characters and spaces</li></ul>{:/} |
| **Email** | `e/` | Can only be of the format [`local-part@domain`](#5-glossary)                                                                      |
| **Phone** | `p/` | Can only be 8 digits long                                                                                                         |

Format: `edit-person INDEX ([n/NAME] [e/EMAIL] [p/PHONE_NUMBER])`
* `INDEX` is the currently displayed index number of the contact you are editing for on the screen.

Examples:
```
edit-person 1 n/Dinosaur Lim
```
In the above example, we are editing the contact with a displayed-index number of '1' on the screen to now have the name
`Dinosaur Lim` in Plannit.
```
edit-person 1 e/dinosaurlim@gmail.com
```
In the above example, we are editing the contact with a displayed-index number of '1' on the screen to now have the
email `dinosaurlim@gmail.com`in Plannit.
```
edit-person 1 n/Dinosaur Lim e/dinosaurlim@gmail.com p/91234567
```
In the above example, we are editing the contact with a displayed-index number of '1' on the screen to now have the name
`Dinosaur Lim`, email `dinosaurlim@gmail.com`, and phone number `91234567` in Plannit.


#### 2.4.6. Find person
When you are on the home page, you can search for people whose names start with the specified keywords.

Format: `find-person KEYWORD`
* The KEYWORD is case-insensitive.

Examples:
```
find-person alex
```
OR
```
find-person Alex
```
In either of the above examples, we find every person whose name starts with Alex in Plannit.

#### 2.4.7. List person
When you are on the home page, you may obtain the list of every person in Plannit.

Format: `list-person`

Example:
```
list-person
```
In the above example, we list every person that exist in Plannit.

### 2.5. Navigation
With navigation functionalities, you now have the ability to navigate between different modules in Plannit!

#### 2.5.1. Navigate to home
An overview of the modules and persons in Plannit is provided on the home page.

You may navigate to the home page using the `home` command.

Format:  `home`

<div markdown="block" class="alert alert-primary"> :bulb: **Tips:** <br>
You may click on a module to ["peek"](#224-peeking-at-modules) at a module's task while on the home page. <br>
For more detailed information regarding a specific module, you may navigate to that
module using the [`goto`](#252-navigate-between-modules) command. <br>
The following commands also navigates you back to the home page:
* [`add-module`](#211-add-module)
* [`delete-module`](#212-delete-module)
* [`add-person`](#244-add-person)
</div>

<div markdown="block" class="alert alert-info"> :information_source: **Note:**
After using the [`goto`](#252-navigate-between-modules) command, executing the `home` command will 
re-enable the following commands:
* [`find-module`](#214-find-module)
* [`list-module`](#215-list-module)
* [`find-person`](#244-find-person)
* [`list-person`](#245-list-person)
</div>

#### 2.5.2. Navigate between modules
You may navigate between modules to view tasks and contacts associated with a particular
module using the `goto` command.

Format: `goto MODULE_CODE`
* You should provide a module code of an existing module in Plannit.

Example:
```
goto CS2109S
```
In the above example, we are navigating to the module with module code `CS2109S`.

<div markdown="span" class="alert alert-primary"> :bulb: **Tips:**
You may return to home page by executing the [`home`](#251-navigate-to-home) command.
</div>

<div markdown="block" class="alert alert-info">:information_source: **Note:** <br>
You will leave the home page after executing the `goto` command. This is different from the
behavior of [`find-module`](#215-find-module) command. <br>
After using the `goto` command, usage of the following commands will be restricted:<br>
* [`find-module`](#214-find-module)
* [`list-module`](#215-list-module)
* [`find-person`](#244-find-person)
* [`list-person`](#245-list-person)
To re-enable the restricted commands, you may execute any commands that bring you back to the home page
(i.e. [`home`](#251-navigate-to-home)).
</div>

<br>

### 2.6. Exiting The Program
Exits the program.

Format: `exit`

<br>

### 2.7. Saving The Data
Your data is saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

<br>

### 2.8. Loading The Data
If saved data exists, data is automatically loaded when the program starts.
There is no need to load manually.

<br>

### 2.9. Editing The Data File
Your data is saved as a `JSON` file `[JAR file location]/data/plannit.json`. Advanced users are welcome to update
data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Plannit will discard
all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------
## 3. Landing Page Visual Guide
[coming soon]

--------------------------------------------------------------------------------------------------------------------
## 4. FAQ
**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with
the file that contains the data of your previous Plannit home folder.
<br>
<br>
[More questions coming soon]

--------------------------------------------------------------------------------------------------------------------
# 5. Glossary

| Term                  | Meaning                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **local-part@domain** | The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-).<br>The local-part may not start or end with any special characters. <br> <br>This is followed by a '@' and then a domain name. <br> <br>The domain name is made up of domain labels separated by periods. The domain name must: <br> 1) end with a domain label at least 2 characters long <br> 2) have each domain label start and end with alphanumeric characters <br> 3) have each domain label consist of alphanumeric characters, separated only by hyphens, if any. |
