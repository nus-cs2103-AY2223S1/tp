---
layout: page
title: User Guide
---
**Plannit** is a **unified desktop application** that aims to **help NUS CS
students manage their academic details.**  It will be the **go-to platform**
for you to access all modules links and information without needing to
tediously navigate through multiple websites.

This application is **optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents 
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. Quick start [coming soon in v1.2]

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `plannit.jar` from [coming soon]().

3. Copy the file to the folder you want to use as the _home folder_ for Plannit.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
contains some sample data.<br>
   [image coming soon]

5. Type the command in the command box and press Enter to execute it. e.g. 
   `add-module`. For more commands, you may refer to the [command summary](#11-command-summary)

6. Refer to the [Features](#2-features) for details of each command.

### 1.1. Command summary

| Action                                              | Format                                                         | Short Description                                                               |
|-----------------------------------------------------|----------------------------------------------------------------|---------------------------------------------------------------------------------|
| [`add-module`](#211-add-module)                     | `add-module      m/MODULE_CODE [t/MODULE_TITLE]`               | Add module with a module code and optional module title                         |
| [`delete-module`](#212-delete-module)               | `delete-module   m/MODULE_CODE`                                | Delete module by module code                                                    |
| [`add task`](#221-add-task)                         | `add    task     m/MODULE_CODE d/TASK_DESCRIPTION`             | Add task with specified module code and task description                        |
| [`delete task`](#222-delete-task)                   | `delete task     m/MODULE_CODE n/TASK_NUMBER`                  | Delete task corresponding to specified task number of specified module code     |
| [`add-link`](#231-add-link)                         | `add-link        INDEX l/LINK_URL`                             | Add link URL to a module by its displayed index                                 |
| [`delete-link`](#232-delete-link)                   | `delete-link     INDEX l/LINK_URL`                             | Delete link URL from a module by its displayed index                            |
| [`add-contact`](#241-add-contact)                   | `add-contact     n/NAME    e/EMAIL    p/PHONE_NUMBER`          | Add contact with specified name, email, and phone number                        |
| [`delete-contact`](#242-delete-contact-coming-soon) | `delete-contact  n/NAME`                                       | Delete contact belonging to the specified name                                  |
| [`edit-contact`](#243-edit-contact)                 | `edit-contact    INDEX ([n/NAME] [e/EMAIL]  [p/PHONE_NUMBER])` | Edit contact belonging to the specified index currently displayed on the screen |
| [`home`](#251-navigate-to-home)                     | `home`                                                         | Navigate to the home page                                                       |
| [`goto`](#252-navigate-between-modules)             | `goto m/MODULE_CODE`                                           | Navigate to specified module page                                               |
| [`exit`](#26-exiting-the-program)                   | `exit`                                                         | Exit the program                                                                |

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
  e.g. if you specify `p/81234123 p/999`, only `p/999` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as
  `home`, `goto` and `exit`) will be ignored.<br>
  e.g. if the command specifies `home 123`, it will be interpreted as `home`.

</div>

### 2.1. Adding and deleting modules
#### 2.1.1. Add module
You can add a module into Plannit.

This command will require one flag, and one flag is optional:
* `m/`: To be followed by the module code of the module to be added into Plannit.
* `t/`: (Optional flag) To be followed by the module title of the module to be added into Plannit.

Format: `add-module m/MODULE_CODE [t/MODULE_TITLE]`
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
In the above example, we are deleting module CS2103T from Plannit.

#### 2.1.3. Find module: `find module` [coming soon]
[coming soon]

<br>

### 2.2. Adding and deleting tasks
#### 2.2.1. Add task
You may add a task using the `add-task` command.

This command will require two flags:
* `m/`: To be followed by the module code of the
  module which the task is associated with.
* `d/`: To be followed by the task description.

Format: `add-task m/MODULE_CODE d/TASK_DESCRIPTION`
* Each task **must** belong to a specific module.
* You should provide a module code of an existing module in Plannit.

Example:
```
add-task m/CS2103T d/Complete tP tasks for W07
```
In the above example, we are adding the task `Complete tP tasks for W07` to the 
module with module code `CS2103T`.

#### 2.2.2. Delete task
You may delete a task belonging to a particular module using the 
`delete-task` command.

This command will require two flags:
* `m/`: To be followed by the module code of the module which assigned the 
  task.
* `n/`: To be followed by the task number in the module.

Format: `delete-task m/MODULE_CODE n/TASK_NUMBER`
* You should provide a module code of an existing module in Plannit.
* You should provide a task number corresponding to that of an existing task in 
  the module.

Example:
```
delete-task m/CS2103T n/3
```
In the above example, we are deleting task number **3** from the module 
with the module code `CS2103T`.

#### 2.2.3. Finding tasks [coming soon]
[coming soon]

<br>

### 2.3. Adding and deleting links
#### 2.3.1. Add link
You may add a link to a specific module using the `add-link` command.

This command will require one flag:
* `l/`: To be followed by the link URL.

Format: `add-link INDEX l/LINK_URL`
* `INDEX`: To be replaced with the displayed index of the module which is associated with the link
* You cannot add a link URL to a non-existent module code.
* You cannot add a duplicate link URL for a single module code.

Example:
```
add-link 1 l/visualgo.net/en
```
In the above example, we are adding the link with the URL `visualgo.net/en` 
to the module with the displayed index of `1`.

```
add-link 2 l/https://www.nusmods.com l/kattis.com
```
In the above example, we are adding the links with the URL `https://www.nusmods.com` and `l/kattis.com`
to the module with the displayed index of `2`.

#### 2.3.2. Delete link
You may delete a link from a specific module using the `delete-link` command.

This command will require one flag:
* `l/`: To be followed by the link URL.

Format: `delete-link INDEX l/LINK_URL`
* `INDEX`: To be replaced with the displayed index of the module which is associated with the link
* You cannot delete a link URL from a non-existent module code.
* You cannot delete a non-existent link URL from a valid module code.

Example:
```
delete-link 1 l/visualgo.net/en
```
In the above example, we are deleting the link with the URL `visualgo.net/en`
from the module with the displayed index of `1`.

```
delete-link 2 l/https://www.nusmods.com l/kattis.com
```
In the above example, we are deleting the links with the URL `https://www.nusmods.com` and `l/kattis.com`
from the module with the displayed index of `2`.
<br>

### 2.4. Adding and deleting contacts
#### 2.4.1. Add contact
You may add a contact using the `add-contact` command.

This command will require two flags, and one optional flag:
* `n/`: To be followed by the to-be-added contact name.
* `e/`: To be followed by the email of the new contact.
* `p/`: To be followed by the phone number of the new contact.

Format: `add-contact n/NAME e/EMAIL p/PHONE_NUMBER`
* You cannot add a duplicate name into Plannit.
* You cannot specify any country code for phone number.
* You cannot specify a non-8-digit phone number.

Example:
```
add-contact n/Dinosaur Lim e/dinosaurlim@gmail.com p/91234567
```
In the above example, we are adding a contact with name `Dinosaur Lim`, email `dinosaurlim@gmail.com`, and phone number
`91234567` into Plannit.

#### 2.4.2. Delete contact (coming soon)
You may delete a contact using the `delete-contact` command.

This command will require one flag:
* `n/`: To be followed by the to-be-deleted contact name.

Format: `delete-contact n/NAME`
* You cannot delete a non-existent contact.

Example:  
```
delete-contact n/Dinosaur Lim  
```
In the above example, we are deleting a contact with name `Dinosaur Lim` from Plannit.

#### 2.4.3. Edit contact
You may edit a contact using the `edit-contact` command.

This command will require an index and minimally any of the three flags:
* `n/`: To be followed by the new contact name.
* `e/`: To be followed by the new email of the contact.
* `p/`: To be followed by the new phone number of the contact.

Format: `edit-contact INDEX ([n/NAME] [e/EMAIL] [p/PHONE_NUMBER])`
* `INDEX` is the currently displayed index number of the contact you are editing for on the screen.
* You cannot specify any country code for phone number.
* You cannot specify a non-8-digit phone number.

Examples:
```
edit-contact 1 n/Dinosaur Lim
```
In the above example, we are editing the contact with a displayed-index number of '1' on the screen to now have the name
`Dinosaur Lim` in Plannit.
```
edit-contact 1 e/dinosaurlim@gmail.com 
```
In the above example, we are editing the contact with a displayed-index number of '1' on the screen to now have the
email `dinosaurlim@gmail.com`in Plannit.
```
edit-contact 1 n/Dinosaur Lim e/dinosaurlim@gmail.com p/91234567
```
In the above example, we are editing the contact with a displayed-index number of '1' on the screen to now have the name
`Dinosaur Lim`, email `dinosaurlim@gmail.com`, and phone number `91234567` in Plannit.

### 2.5. Navigation
With navigation functionalities, you now have the ability to navigate between different tabs in Plannit!

#### 2.5.1 Navigate to home
You may navigate back to the home page using the `home` command.

Format:  `home`

#### 2.5.2 Navigate between modules
You may navigate between modules to view information belonging to a particular
module using the `goto` command.

This command will require one flag:
* `m/`: To be followed by the module code of the module you are navigating to.

Format: `goto m/MODULE_CODE`
* You should provide a module code of an existing module in Plannit.

Example:
```
goto m/CS2109S
```
In the above example, we are navigating to the module with module code `CS2109S`.

<br>

### 2.6. Exiting The Program
Exits the program.

Format: `exit`

<br>

### 2.7. Saving The Data
Plannit data is saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

<br>

### 2.8. Loading The Data
If saved data exists, data is automatically loaded when the program starts. There is no need to 
load manually.

<br>

### 2.9. Editing The Data File
Plannit data is saved as a `JSON` file `[JAR file location]/data/plannit.json`. Advanced users are welcome to update 
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
