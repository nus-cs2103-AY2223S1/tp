---
layout: page
title: User Guide
---

InTrack is a **desktop app for managing your internship applications, optimized for use via a Command Line Interface**
(CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, InTrack can get your
internship management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `InTrack.jar` from [here](https://github.com/AY2223S1-CS2103T-T11-2/tp).

1. Copy the file to the folder you want to use as the _home folder_ for InTrack.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all internships.

   * **`add`**`p/Software Engineer Intern c/Google e/email@gmail.com a/1600 Amphitheatre Parkway t/Urgent` : Adds a `Software Engineer Intern` internship application to `Google` with respective email, location and tag to InTrack.

   * **`mark`** `1` : Marks the 1st internship shown in the current list as completed

   * **`delete`**`1` : Deletes the 1st internship shown in the current list.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add p/POSITION`, `POSITION` is a parameter which can be used as `add p/Software Engineer Intern`.

* Items in square brackets are optional.<br>
  e.g `a/ADDRESS [t/TAG]` can be used as `a/1600 Amphitheatre Parkway t/virtual` or as `a/1600 Amphitheatre Parkway`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/urgent`, `t/urgent t/virtual` etc.

* Extraneous parameters for commands that do not take in parameters (such as `help` and `list`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an internship application: `add`

Adds a new internship application to the tracker.

Format: `add p/POSITION c/COMPANY e/EMAIL a/ADDRESS [t/TAG]…`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An internship can have any number of tags (including 0)
</div>

Examples:
* `add p/Software Engineer Intern c/Google e/email@gmail.com a/1600 Amphitheatre Parkway t/Urgent`
* `add p/Data Analyst c/TikTok e/tiktok@bytedance.com a/1 Raffles Quay`

### Listing all internship applications : `list`

Shows a list of all internship applications in the tracker.

Format: `list`

### Deleting an internship application : `delete`

Deletes the specified internship application from the tracker.

Format: `delete INDEX`

* Deletes the internship application at the specified INDEX.
* The index refers to the index number shown in the displayed internship list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 1` deletes the 1st internship application in InTrack.

### Marking completion status of an internship application : `mark`

Marks the specified internship application as completed.

Format: `mark INDEX`

* Marks the internship application at the specified INDEX.
* The index refers to the index number shown in the displayed internship list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `mark 1` marks the 1st internship application in InTrack as completed.

### Saving the data

InTrack data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

- Work in progress

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                  |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**   | `help`                                                                                                                                                            |
| **Add**    | `add p/POSITION c/COMPANY e/EMAIL a/ADDRESS [t/TAG]…` <br> e.g., `add p/Software Engineer Intern c/Google e/email@gmail.com a/1600 Amphitheatre Parkway t/Urgent` |
| **Delete** | `delete INDEX`<br> e.g., `delete 1`                                                                                                                               |
| **Mark**   | `mark INDEX`<br> e.g., `mark 1`                                                                                                                                   |
| **List**   | `list`                                                                                                                                                            |

