---
layout: page
title: User Guide
---

**JerylFypManager** is a desktop application catered to professors and final year project (FYP) students to manage and track the progress for students’ FYP projects, as well as serving as a platform for professors to provide feedback on their students’ progress. The application’s simple design provides a nifty platform to navigate through and present the FYP projects in a concise manner.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `JerylFypManager.jar` from [here](https://github.com/AY2223S1-CS2103-F09-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your FypManager.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:

   * **`list`** - lists all FYP projects the professor is supervising

   * **`add -s id/A0123456G n/John Doe p/Automation of Selenium e/johndoe@gmail.com`** - adds the FYP project of the student named John Doe with ID A0123456G, email `johndoe@gmail.com`, and project titled _Automation of Selenium_

   * **`delete -s id/A0123456G`** - deletes the FYP project of the student with ID A0123456G

   * **`mark id/A0123456G s/IP`** - marks the FYP project of the student with ID A0123456G as _In Progress_

   * **`find machine`** - searches any FYP project names that has “machine” in its name, for example it will output the FYP project with “Machine Learning” in its name

   * **`help add`** - shows a message on how to use the `add` command

   * **`help`** - shows the list of all commands available

   * **`exit`** - exits the application

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/FYP_NAME`, `FYP_NAME` is a parameter which can be used as `add n/Neural Network`.

* Items in square brackets are optional.<br>
  e.g `n/FYP_NAME [t/TAG]` can be used as `n/Neural Network t/SOC` or as `n/Data Caching`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/SOC`, `t/SOC t/ML` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/FYP_NAME t/TAG`, `t/TAG n/FYP_NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `exit`) will be ignored.<br>
  e.g. if the command specifies `list 456`, it will be interpreted as `list`.

* `STUDENT_ID` should be in the following format: **"A" + (7 digits) + (1 letter)**, e.g. `A0123456G`

* `FYP_NAME` and `DEADLINE_NAME` should only include alphanumeric characters and space but **cannot start with a space**, e.g. `Support vector machine: some improvements` is invalid

* `DEADLINE_DATETIME` should be in the format of "DD-MM-YYYY HH:mm"

</div>

### Adding students FYP: `add`

There are 2 types of Add Commands. 
1. `add -s`: Adds a new FYP of a student to the FYP manager.

    Format: `add -s id/STUDENT_ID n/STUDENT_NAME p/FYP_NAME e/EMAIL [t/TAG]…​`
2. `add -d`: Adds a new deadline task to a student specified by ID.

    Format: `add -d id/STUDENT_ID nDEADLINE_NAME d/DEADLINE_DATETIME`


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

Examples:
* `add -s id/A0123456G n/Jane Doe p/Neural Network e/e0123456@u.nus.edu t/NN`
* `add -s id/A0987654X n/Alex Yeoh p/Data Caching e/e09876567@u.nus.edu`
* `add -d id/A0123456G nRandom Task d/23-10-2022 23:59`

### Removing students FYP: `delete`

There are 2 types of Delete Commands.
1. `delete -s`: Removes a FYP from the FYP manager. A FYP could be deleted for the following reasons:
    * Student dropped the FYP
    * Student finished the FYP

    Format: `delete -s id/STUDENT_ID`
    * `STUDENT_ID` should be in the following format: "A" + (7 digits) + (1 letter), e.g. `A0123456G`

2. `delete -d`: Removes a deadline assigned to a student specified by ID & a priority rank.

    Format: `delete -d id/STUDENT_ID r/DEADLINE_RANK`

Example:
* `delete -s id/A0123456G`
* `delete -d id/A0123456G r/1`

### Marking project status: `mark`

Marks a FYP as "Done"/"In Progress"/"Yet to Start", etc.

Format: `mark id/STUDENT_ID s/STATUS`
* Current supported statuses are as follows:
  * **"DONE"** - Done
  * **"IP"** - In Progress
  * **"YTS"** - Yet To Start

Examples:
* `mark id/A0123456G s/DONE`
* `mark id/A0234567H s/YTS`

### Searching keyword: `find`

Finds for projects whose field (to be specified by user) contains any of the given keyword(s).
The four fields that the user can search by are:
1) `StudentId`
2) `StudentName`
3)  `Tags` (accorded to a student)
4) `ProjectName`

Format for:
1) `StudentId`: `find -id KEYWORD/[KEYWORD2/KEYWORD3/…]`
2) `StudentName`: `find -n KEYWORD/[KEYWORD2/KEYWORD3/…]`
3) `Tags`: `find -t KEYWORD/[KEYWORD2/KEYWORD3/…]`
4) `ProjectName`: `find -p KEYWORD/[KEYWORD2/KEYWORD3/…]`

* Only the four specified fields above could be searched, and only one field can be searched at any one time.
* The keyword is case-insensitive, e.g. `Neural NetWORK` will match `neural network`
* The keyword could contain space, e.g. `practical guide of machine learning` is allowed
* Partial keywords will also be matched, e.g. `Ne` will match `neural network` and `Genetic Algorithm`
* Leading and trailing spaces are ignored, e.g. ` neural network  ` will match `neural network`
* Projects matching at least one keyword will be returned (i.e. `OR` search), 
  e.g. `find -t neural network/tree` will match project tags with `neural network` or `decision tree`

Examples:
* `find -t Neural Network` searches for all projects with the tag `Neural Network`.
* `find -p Neural/Network  /    Data` searches for all projects with `Neural` or `Network` or `Data` in their titles.

### Edit student details: `edit`

Edit student details according to the input.

Format: `edit STUDENT_ID id/STUDENT_ID n/STUDENT_NAME proj/FYP_NAME e/EMAIL`


Examples:
* `edit A0123456G id/A1234567B`
* `edit A0234567H n/John Hoe`


### List of commands: `help`

Shows a list of valid commands or a help page on a particular command.

Format: `help [COMMAND]`

Examples:
* `help add -s` - This shows a detailed help message on the `add -s` command
* `help` - This shows only the list of commands

### List of FYPs: `list`

Shows a list of final year projects with the student IDs.

Format: `list`

### Exiting the application: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FypManager home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action              | Format, Examples                                                                                                                                 |
|---------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Student**     | `add -s id/STUDENT_ID n/STUDENT_NAME p/FYP_NAME e/EMAIL [t/TAG]…​` <br> e.g., `add id/A0987654X n/John Doe p/Data Caching e/e09876567@u.nus.edu` |
| **Add Deadline**    | `add -d id/STUDENT_ID nDEADLINE_NAME d/DEADLINE_DATETIME` <br> e.g., `add -d id/A0123456G nRandom Task d/23-10-2022 23:59`                       |
| **Delete Student**  | `delete -s id` <br> e.g., `delete -s id/A0987654X`                                                                                               |
| **Delete Deadline** | `delete -d id/STUDENT_ID r/DEADLINE_RANK` <br> e.g., `delete -d id/A0123456G r/1`                                                                |
| **Mark**            | `mark id/STUDENT_ID s/STATUS`<br> e.g.,`mark id/A0123456G s/IP`                                                                                  |
| **Find**            | `find KEYWORD/[KEYWORD2/KEYWORD3/…]`<br> e.g., `find neural network/tree`                                                                        |
| **Edit**            | `edit STUDENT_ID id/STUDENT_ID n/STUDENT_NAME proj/FYP_NAME e/EMAIL`<br> e.g., `edit A0234567H n/John Hoe`                                       |
| **Help**            | `help [COMMAND]`<br> e.g., `help add`, `help`                                                                                                    |
| **List**            | `list`                                                                                                                                           |
| **Exit**            | `exit`                                                                                                                                           |

