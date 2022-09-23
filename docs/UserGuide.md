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

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   [image coming soon]

5. Type the command in the command box and press Enter to execute it. e.g.
   `add module`. For more commands, you may refer to the [command summary](#11-command-summary)

6. Refer to the [Features](#2-features) for details of each command.

### 1.1. Command summary

Action | Format, Examples (if any)
--------|------------------
**CommandInBold** | `command examples here`
**CommandInBold** | `command examples here`
`add task`    | `add    task     m/MODULE_CODE d/TASK_DESCRIPTION`,<br>e.g.: `add task m/CS2103T d/Complete tP tasks for W07`
`delete task` | `delete task     m/MODULE_CODE n/TASK_NUMBER`,<br>e.g.: `delete task m/CS2103T n/3`
**CommandInBold** | `command examples here`
**CommandInBold** | `command examples here`
**home** | `home`
**goto** | `goto m/MODULE_CODE`,<br>e.g.: `goto m/CS2103T`
**exit** | `exit`
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
  e.g. if you specify `p/81234123 p/999`, only `p/81234123` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as
  `home`, `goto` and `exit`) will be ignored.<br>
  e.g. if the command specifies `home 123`, it will be interpreted as `home`.

</div>

### 2.1. Adding and deleting modules
#### 2.1.1. Add module: `add module`
#### 2.1.2. Delete Module: `delete module`

<br>

### 2.2. Adding and deleting tasks
#### 2.2.1. Add task: `add task`
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
* In the above example, we add the task `Complete tP tasks for W07` to the
  module with module code `CS2103T`.

#### 2.2.2 Delete task: `delete task`
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
* In the above example, we are deleting task number **3** from the module
  with the module code `CS2103T`.

#### 2.4.3. Finding tasks: `find task` [coming soon]
[coming soon]

<br>

### 2.3. Adding and deleting links
#### 2.3.1. Add link
#### 2.3.2. Delete link

<br>

### 2.4. Adding and deleting contacts
#### 2.4.1. Adding contact
#### 2.4.2. Delete contact

<br>

### 2.5. Navigation
Provides users with the ability to navigate between different tabs in the program.

#### 2.5.1 Navigate to Home Page: `home`
You may navigate back to the home page using the `home` command.

Format:  `home`

#### 2.5.2 Navigate between Modules: `goto`
You may navigate between modules to view information belonging to a particular
module using the `goto` command.

This command will require one flag:

* `m/`: This flag is to be followed immediately by the module code of the module you are navigating to.

Format: `goto m/MODULE_CODE`

* The given module code should be that of an existing module in Plannit.

Example:
```
goto m/CS2109S
```
* In the above example, we are navigating to the module with module code `CS2109S`.

<br>

### 2.6. Exiting The Program : `exit`

Exits the program.

Format: `exit`

<br>

### 2.7. Saving The Data
Plannit data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<br>

### 2.8. Loading The Data
If save data exists, data is automatically loaded when the program starts. There is no need to load manually.

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

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.
<br>
<br>
[More questions coming soon]

--------------------------------------------------------------------------------------------------------------------
