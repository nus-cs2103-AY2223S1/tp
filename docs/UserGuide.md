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

1. Download the latest `plannit.jar` from [coming soon]().

1. Copy the file to the folder you want to use as the _home folder_ for Plannit.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   [image coming soon]

1. Type the command in the command box and press Enter to execute it. e.g. 
   `add module`. For more commands, you may refer to the [command summary](#11-command-summary)
   
1. Refer to the [Features](#2-features) for details of each command.

### 1.1. Command summary

| Action                                | Format                                             | Short Description                                                           |
|---------------------------------------|----------------------------------------------------|-----------------------------------------------------------------------------|
| [`add module`](#211-add-module)       | `add    module   m/MODULE_CODE [t/MODULE_TITLE]`   | Add module with a module code and optional module title                     |
| [`delete module`](#212-delete-module) | `delete module   m/MODULE_CODE`                    | Delete module by module code                                                |
| [`add task`](#221-add-task)           | `add    task     m/MODULE_CODE d/TASK_DESCRIPTION` | Add task with specified module code and task description                    |
| [`delete task`](#222-delete-task)     | `delete task     m/MODULE_CODE n/TASK_NUMBER`      | Delete task corresponding to specified task number of specified module code |
| **CommandInBold**                     | `command format here`                              |                                                                             |
| **CommandInBold**                     | `command format here`                              |                                                                             |
--------------------------------------------------------------------------------------------------------------------

## 2. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add m/MODULE`, `MODULE` is a parameter which can be used as `add m/MODULE`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [e/EMAIL]` can be used as `n/John Doe e/john@u.nus.edu` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
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

Format: `add module m/MODULE_CODE [t/MODULE_TITLE]`
* A module has a module code and an optional module title.

Examples:
```
add module m/CS2103T
```
In the above example, we are adding a module `CS2103T` without a title.

```
add module m/CS2103T t/Software Engineering
```
In the above example, we are adding a module `CS2103T` which has the title `Software Engineering`.

#### 2.1.2. Delete module
You can delete the module with the indicated module code from Plannit.

This command will require one flag:
* `m/`: To be followed by the module code of the module to be deleted from Plannit.

Format: `delete module m/MODULE_CODE`
* The module code must correspond to an existing module in Plannit.

Example:

```
delete module m/CS2103T
```
In the above example, we are deleting module CS2103T from Plannit.

#### 2.1.3. Find module: `find module` [coming soon]
[coming soon]
<br>

### 2.2. Adding and deleting tasks
#### 2.2.1. Add task
You may add a task using the `add task` command.

This command will require two flags:
* `m/`: This flag is to be followed immediately by the module code of the 
  module which the task is associated with.
* `d/`: This flag is to be followed immediately by the task description.

Format: `add task m/MODULE_CODE d/TASK_DESCRIPTION`
* Each task **must** belong to a specific module.
* The given module code should be that of an existing module in Plannit.

Example:
```
add task m/CS2103T d/Complete tP tasks for W07
```
In the above example, we are adding the task `Complete tP tasks for W07` to the 
module with module code `CS2103T`.

#### 2.2.2. Delete task
You may delete a task belonging to a particular module using the `delete 
task` command.

This command will require two flags:
* `m/`: This flag is to be followed immediately by the module code of the 
  module which assigned the task. 
* `n/`: This flag is to be followed immediately by the task number in the module.

Format: `delete task m/MODULE_CODE n/TASK_NUMBER`
* The given module code should be that of an existing module in Plannit. 
* The given task number has to be that of an existing task in the module.

Example:
```
delete task m/CS2103T n/3
```
In the above example, we are deleting task number **3** from the module 
with the module code `CS2103T`.

#### 2.4.3. Finding tasks [coming soon]
[coming soon]
<br>

### 2.3. Adding and deleting links
#### 2.3.1. Add link
#### 2.3.2. Delete link

<br>

### 2.4. Adding and deleting contacts
#### 2.4.1. Add contact
#### 2.4.2. Delete contact

<br>

### 2.5. Navigation
#### 2.5.1. Navigate to home
#### 2.5.2. Navigate between modules
<br>

### 2.6. Exiting The Program : `exit`

Exits the program.

Format: `exit`

<br>

### 2.7. Saving The Data
Plannit data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<br>

### 2.8. Loading The Data
If saved data exists, data is automatically loaded when the program starts. There is no need to 
load manually.

<br>

### 2.9. Editing The Data File

Plannit data is saved as a `JSON` file `[JAR file location]/data/plannit.json`. Advanced users are welcome to update data directly by editing that data file.

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


