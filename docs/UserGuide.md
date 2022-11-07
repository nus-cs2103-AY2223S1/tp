---
layout: page
title: User Guide
---

<p align="center">
  <img src="./images/GimLogo.png"/>
</p>

<div style="page-break-after: always;"></div>

<div id="toc"></div>

**Table of Contents**
* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 1. Introduction

### 1.1. What is Gim?
Gim is a desktop app for **managing gym exercises**. Gim allows you to **keep track of your progress** and **craft personalised workout plans**. Gim commands are inspired by those of [Vim](#8-glossary-of-terminologies). Gim is optimised for use via a [Command Line Interface (CLI)](#8-glossary-of-terminologies) while still having the benefits of a [Graphical User Interface (GUI)](#8-glossary-of-terminologies).

### 1.2. Who is this guide for?
Are you a gym-goer looking to use Gim to track your exercises? This user guide will get you started in no time and help you navigate through Gim's features. For a quick start guide, head over to [Getting Started](#3-getting-started).

--------------------------------------------------------------------------------------------------------------------

## 2. How to use this guide?
Gim uses a Command Line Interface (CLI), which may be new to some users. If you are a new user, we strongly recommend you to look through the user guide from start to end to fully understand how to use Gim. However, you may also choose to skip to the relevant sections described below:
* Refer to our <a href="#toc">Table of Contents</a> to easily navigate between sections of the User Guide.
* Refer to our [Getting Started](#3-getting-started) guide to learn how to setup Gim.
* Refer to our [GUI Orientation](#4-gui-orientation) to better orientate yourself around the GUI.
* Refer to our [Commands](#5-commands) section to learn in detail the different features and commands available in Gim.
* Refer to our [FAQ](#6-faq) to read common queries that new users may have.
* Refer to our [Command Summary](#7-command-summary) to have a quick overview of the different commands and their respective formats.
* Refer to our [Glossary of Terminologies](#8-glossary-of-terminologies) to learn key terms that are used in this User Guide.

### 2.1 Useful Notations
While exploring Gim's features with this user guide, do take note of these symbols used in the user guide and what they represent.

|        Symbol        | Meaning                 |
|:--------------------:|-------------------------|
| :information_source: | Important information   |
|    :exclamation:     | Warning or Caution      |
|      :thinking:      | When should I use this? |
|        :bulb:        | Tips                    |

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 3. Getting Started

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `gim.jar` [here](https://github.com/AY2223S1-CS2103T-T15-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Gim.

4. Double-click the file to start the app. The GUI similar to the one shown below should appear in a few seconds. Note how the app contains some sample data.<br>
   <br>![Ui](images/Ui.png)


5. Type the command in the command box and press Enter to execute it. e.g. typing **`:help`** and pressing Enter will open the help window.<br>

6. Refer to the [Commands Section](#5-commands) below for details of each command.

<a href="#toc">Back To Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 4. GUI Orientation

![GUI](images/GUIOrientation.png)

### 4.1. Command Box

The `Command Box` is where you can input your commands.

### 4.2. Exercise List

The `Exercise List` displays exercise entries. When the application is first launched, the `Exercise List` displays all exercise entries in the system, arranged by the order in which they were added. Whenever you issue commands that may truncate/reorder the `Exercise List`, they will **only act upon the entries that are currently displayed in the** `Exercise List`.

<div style="page-break-after: always;"></div>

### 4.3. Result Display

The `Result Display Window` displays feedback after executing a command. This includes feedback for both correctly and incorrectly entered commands.

### 4.4. Recognised Exercise Name List

The `Recognised Exercise Name List Window` provides you a list of all unique exercise names that are currently registered in the system.
<br><br>

Notice that there are two counts. 

* **Count #1**: Displays the number of exercise names registered in the system (i.e. does not include duplicates)   
* **Count #2**: Displays the total number of exercise entries in the system (i.e. includes duplicates)

For illustrative purposes, let us refer to the image below. There are multiple Squat entries in the system registered under the same name Squat, which is reflected by **Count #1**. On the other hand, because **Count #2** includes duplicates, it will count both "Squat" entries individually even if they are registered under the same name.

![RecognisedList](images/RecognisedExercisesOrientation.png)

<div style="page-break-after: always;"></div>

<div markdown="span" class="alert alert-primary">:bulb:
**Tip:** Use the list circled in green to identify any mispellings in your exercise entries!<br><br>

E.g. If you see an entry for Squatz in the green list when you normally name your exercise Squat, it means you may have an incorrectly spelled entry. 
</div> 

<div id="names" markdown="span" class="alert alert-danger">:exclamation: **Caution:**<br>
Exercise names are recognised as equal if, upon removing white spaces and setting the names to lowercase, the names are the same.<br><br>
i.e. Bench Press, BENCH PRESS, BenchPress will be logged as the same exercise for your convenience in adding.<br><br>
However, the first time you add an exercise with an unrecognised name, the Recognised Exercise Name List will save the form you have input. Choose wisely!
</div>

<a href="#toc">Back To Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 5. Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the [parameters](#8-glossary-of-terminologies) to be supplied by you.<br>
  E.g. In `n/NAME w/WEIGHT`, `NAME` and `WEIGHT` are parameters which can be used as `n/Squat w/100`.

* Items in square brackets are optional.<br>
  E.g. `n/NAME [d/DATE]` can be used as `n/Deadlift d/27-10-2022` or as `n/Deadlift`.

* Items with `…` after them can be used multiple times including zero times.<br>
  E.g. `[n/NAME]…` can be used as ` ` (i.e. 0 times), `n/Squat` (i.e. 1 time), `n/Squat n/Deadlift` (i.e. 2 times) etc.

* Parameters can be in any order.<br>
  E.g. If the command specifies `n/NAME w/WEIGHT`, `w/WEIGHT n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  E.g. If you specify `n/Squat n/Deadlift`, only `n/Deadlift` will be taken.

* Redundant inputs for commands that do not take in additional parameters (such as `:list`, `:sort`, `:help` `:wq`) will be ignored.<br>
  E.g. If you specify `:help 123`, it will be interpreted as `:help`.

</div>

<div style="page-break-after: always;"></div>

### 5.1. Adding an exercise: `:add`

Adds an exercise that we have done for the day.

<div markdown="block" class="alert alert-info">

**:information_source: Note about add:**<br>
If an exercise (identified by their names) is added for the first time, it is automatically registered as a new unique exercise.

</div>

Format: `:add n/NAME w/WEIGHT s/SETS r/REPS [d/DATE]`

<div markdown="span" class="alert alert-primary">:bulb:
**Tip:** If `d/DATE` field is left empty, the system will store the exercise with the current date.
</div> 

Parameter constraints:
* The name **must only contain alphanumeric** (alphabets & numbers) **characters and spaces**.
  * Examples: Squat, Bench press, Deadlift...
* The [weight](#8-glossary-of-terminologies) **must be a non-negative decimal number, up to 3 digits for the whole number and up to 2 digits for the decimal place**.
  * Examples: 0, 0.55, 35, 100.1, 200.00...
* The [sets](#8-glossary-of-terminologies) **must be a positive integer, up to 3 digits, with no leading zeroes**.
  * Examples: 1, 2, 3, 10, 100...
* The [reps](#8-glossary-of-terminologies) **must be a positive integer, up to 3 digits, with no leading zeroes**.
  * Examples: 1, 2, 3, 10, 100...
* The date **must be a valid date**.
  * Accepted formats:
    * day/month/year
    * year/month/day
    * day-month-year
    * year-month-day
    * day month year
    * year month day
  * The day **must be a positive integer, up to 2 digits**. 
  * The month **must be a positive integer, up to 2 digits**.
  * The year **must be a positive integer, with exactly 4 digits**.
  * Examples: 27/10/2022, 01-10-2022...

<div style="page-break-after: always;"></div>

Examples:
* `:add n/Squat w/30 s/3 r/5` Adds a Squat exercise of weight 30kg for 3 sets of 5 reps for today's date.
* `:add n/Deadlift w/60 s/1 r/1 d/27/01/2022` Adds a Deadlift exercise of weight 60kg for 1 set of 1 rep for 27th January 2022.

![AddCommand](images/AddCommand.png)

<div style="page-break-after: always;"></div>

### 5.2. Deleting an exercise : `:del`

Deletes a particular exercise from our list.

<div markdown="block" class="alert alert-info">

**:information_source: Note about delete:**<br>
If the deleted exercise was the last exercise with the same name, then the exercise is automatically de-registered from the list of unique exercises.

</div>

Format: `:del INDEX`

Parameter constraints:
* The [index](#8-glossary-of-terminologies) **must be a positive integer**.
  * Example: 1, 2, 3, ...

Example:
* `:del 9` Deletes an exercise at index 9 of the list.

### 5.3. Clearing all exercises : `:clear`

Clears the saved exercises and resets the data in the system.

Format: `:clear confirm/`

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:** <br>
Redundant inputs (before and after the `confirm/` flag) will be ignored. <br> E.g. If the command specifies `:clear abc confirm/ 123`, it will be interpreted as `:clear confirm/`.
</div>

Example:
* `:clear confirm/` Clears all saved exercises, resetting the data in the system.

<div style="page-break-after: always;"></div>

### 5.4. Filtering exercises by keyword(s) : `:filter`

Filters exercises, in the current [Exercise List](#42-exercise-list), with names containing any of the given keywords.

Format: `:filter KEYWORD [KEYWORD]...`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about filter:**<br>

* Only the exercise name is searched.
* The keyword is case-insensitive. e.g bench will match Bench.
* The order of the keywords does not matter. e.g. Deadlift Squat will match Squat Deadlift.
* Only full words will be matched e.g. Squat will not match Squats.
* Exercises matching at least one keyword will be returned e.g. `:filter Bench press` will return Bench press and Leg press.

</div>

<div markdown="block" class="alert alert-warning">

**:thinking: When should I use this?**<br>
I should use this when I want to find the entries of a specific exercise.

</div>

Example:
* `:filter Deadlift Squat` Shows the list of Deadlift and Squat exercises.

![FilterCommand](images/FilterCommand.png)

<div style="page-break-after: always;"></div>

### 5.5. Sorting exercises : `:sort`

Sorts the exercises, in the current [Exercise List](#42-exercise-list), according to their date of completion, with the latest exercise completed displayed at the top of the list.

Format: `:sort`

Example:
* `:sort` Shows the sorted list of exercises.

![ListAfterSortCommand](images/ListAfterSortCommand.png)


### 5.6. Viewing all exercises within a time period : `:range`

Shows all exercises, among exercises in the current [Exercise List](#42-exercise-list), within the specified date range; the latest exercise completed is displayed at the top of the list.

<div markdown="block" class="alert alert-info">

**:information_source: There are 2 formats supported for this command.**<br>

</div>

<div style="page-break-after: always;"></div>

Format (1) : `:range start/START_DATE end/END_DATE`

Parameter constraints:
* The START_DATE and END_DATE **must be a valid date**.
  * Accepted formats:
    * day/month/year
    * year/month/day
    * day-month-year
    * year-month-day
    * day month year
    * year month day
  * The day **must be a positive integer, up to 2 digits**.
  * The month **must be a positive integer, up to 2 digits**.
  * The year **must be a positive integer, with exactly 4 digits**.
* Start date should be before end date. Otherwise, no exercises will be displayed.

Example:
* `:range start/25/10/2022 end/26/10/2022` Shows the exercises done between October 25, 2022 and October 26, 2022 (both inclusive).

![RangeCommandOne](images/RangeCommandOneSample.png)

<div style="page-break-after: always;"></div>

Format (2) : `:range last/NUMBER_OF_DAYS`

Parameter constraints:
* Number of days **can only take non-negative integer values** up to 5 digits, excluding leading zeroes.

Example:
* `:range last/3` Shows the exercises done today and the last 3 days.

![RangeCommandTwo](images/RangeCommandTwoSample.png)

<div style="page-break-after: always;"></div>

### 5.7. Listing all exercises : `:list`

Shows a list of all exercises.

<div markdown="block" class="alert alert-warning">

**:thinking: When should I use this?**<br>
I should use this when I want to reset my Exercise List back to the default state (before any sorting/filtering commands were used).

</div>

Format: `:list`

Example:
* `:list` Shows the list of exercises you have completed.

<div markdown="span" class="alert alert-primary">:bulb:
**Tip: Tracking exercise progressions over time**<br>
If you want to view your squat progression over the past week, here's a nifty sequence of commands you can try!<br><br>
1. `:list` Current exercise list now shows all exercises.<br>
2. `:filter Squat` Current exercise list now shows 'Squat' exercises.<br>
3. `:range last/7` Current exercise list now shows 'Squat' exercises in the past 7 days.

</div>

<div style="page-break-after: always;"></div>

### 5.8. Listing Personal Records (PR): `:pr`

Finds the [Personal Record](#8-glossary-of-terminologies) of certain exercises in the exercise tracker.

<div markdown="block" class="alert alert-info">

**:information_source: There are 2 formats supported for this command.**<br>

</div>

Format (1): `:pr n/NAME [n/NAME]...`

Parameter constraints:
* Name **must only contain alphanumeric** (alphabets & numbers) **characters and spaces**.
  * Examples: Squat, Bench press, Deadlift...

Examples:
* `:pr n/Squat` Lists the personal record for the Squat exercise (if found).
* `:pr n/Deadlift n/Bench press n/Squat` Lists the personal records for the Deadlift, Bench press and Squat exercises (if found).

![PrCommandExample1](images/PrCommandExample1.png)

<div style="page-break-after: always;"></div>

Format (2): `:pr all/`

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:** <br>
Redundant inputs (before and after the `all/` flag) will be ignored. <br> E.g. If the command specifies `:pr abc all/ 123`, it will be interpreted as `:pr all/`.
</div>

Example:
* `:pr all/` Lists the personal records for all uniquely registered exercises in the exercise tracker.

![PrCommandExample2](images/PrCommandExample2.png)

<div style="page-break-after: always;"></div>

### 5.9. Generating a sample workout based on Personal Records: `:gen`

Generates a sample workout suggestion based on existing personal records of the exercises, according to the difficulty level specified. Exercises are indicated either by their [index](#8-glossary-of-terminologies) or their exercise names.

<div markdown="block" class="alert alert-warning">

**:thinking: When should I use this?**<br>
I should use this when I want to get a quick workout plan based on how I am feeling.

</div>

<div markdown="block" class="alert alert-info">

**:information_source: There are 2 formats supported for this command.**<br>

</div>

<div style="page-break-after: always;"></div>

Format (1): `:gen INDEX [, INDEX]... level/DIFFICULTY_LEVEL`

Parameter constraints:
* The index **must be a positive integer**.
  * Example: 1, 2, 3, ...
* The difficulty level must be supported; currently supported are: easy, medium, hard.

Example:
* `:gen 4, 5 level/easy` Generates a sample workout for Squat; this command is equivalent to `:gen 4 level/easy` since both index 4 and 5 in the displayed list are Squat exercises.
* `:gen 1, 2 level/easy` Generates a sample workout for exercises at index 1 and 2 of the list, Deadlift and Incline Bench.

![GenerateCommandExample1](images/GenerateCommandExample1.png)

<div style="page-break-after: always;"></div>

Format (2): `:gen n/NAME [n/NAME]... level/DIFFICULTY_LEVEL`

Parameter constraints:
* Name **must only contain alphanumeric** (alphabets & numbers) **characters and spaces**.
* The difficulty level must be one that is supported; currently supported are: easy, medium, hard.

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:** <br>
Redundant inputs (before the first `n/` flag) will be ignored.<br>
E.g. if the command specifies `:gen 1,2,3 n/Squat level/easy`, it will be interpreted as `:gen n/Squat level/easy`.
</div>

Example:
* `:gen n/Squat n/Squat level/easy` Generates a sample workout for Squat; this command is equivalent to `:gen n/Squat level/easy` since both exercise names are the same.
* `:gen n/Squat n/Deadlift level/easy` Generates a sample workout for exercises Squat and Deadlift (if found).

![GenerateCommandNameExample1](images/GenerateCommandNameExample1.png)

<div style="page-break-after: always;"></div>

### 5.10. Viewing help : `:help`

Accesses the help menu. The help menu contains a brief summary of the commands supported and provides a link to the user guide.

Format: `:help`

![HelpCommand](images/HelpCommand.png)

<div style="page-break-after: always;"></div>

### 5.11. Exiting the program : `:wq`

Exits the program.

Format: `:wq`

<div markdown="block" class="alert alert-info">

**:information_source: Gim data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.**<br>

</div>

<a href="#toc">Back To Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 6. FAQ

**Q**: Do I have to update the [Recognised Exercise Name List Window](#44-recognised-exercise-name-list) manually?
<br>
**A**: You do not have to update it manually as the Recognised Exercise Name List is already updated automatically whenever you add or delete an exercise entry from the system.

**Q**: Can I change the name of my uniquely registered exercise?
<br>
**A**: To change the way it is represented, you can find the exercise with the name, delete the entries and re-enter the exercises with your new name of choice.

<div markdown="block" class="alert alert-info">

**:information_source: The way you format the exercise name when you first add it will be the way it is displayed in the system. After that, all exercises added that have the <a href="#names">same name</a> will be categorised under the same exercise.**<br>

</div>

**Q**: Can I edit an exercise?
<br>
**A**: You can do so by deleting the entry and adding a new entry.

**Q**: Why is `:filter`, `:range` or `:sort` not showing the "correct" list even though I have input valid parameters?
<br>
**A**: The three commands works on the exercises in the current [Exercise List](#42-exercise-list). If your current Exercise List has been altered by list-changing commands such as `:range` or `:filter`, the commands will act on the current Exercise List rather than the full list comprising all exercises in the system.

<div markdown="span" class="alert alert-primary">:bulb:
**Tip:** If you would like to operate on the full list instead, try executing the command `:list` to display the full list before running the commands again.
</div> 

**Q**: How is data stored in Gim?
<br>
**A**: Gim data is stored in `[JAR file location]/data/exercisetracker.json`. If you are comfortable working with [JSON](#8-glossary-of-terminologies) files, you are welcome to update Gim's data by editing the data file directly.

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Gim will discard all data and start with an empty data file at the next run.
</div>

<a href="#toc">Back To Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 7. Command Summary

| Action                             | Format                                         | Examples                                  |
|------------------------------------|------------------------------------------------|-------------------------------------------|
| **Add exercise**                   | :add n/NAME w/WEIGHT s/SETS r/REPS [d/DATE]    | :add n/Deadlift w/60 s/1 r/1 d/27/10/2022 |
| **Delete exercise**                | :del INDEX                                     | :del 3                                    |
| **Clear all exercises**            | :clear confirm/                                | :clear confirm/                           |
| **Filter exercise(s)**             | :filter KEYWORD [KEYWORD]...                   | :filter Deadlift Squat                    |
| **Sort exercises**                 | :sort                                          | :sort                                     |
| **View range (1)**                 | :range start/START_DATE end/END_DATE           | :range start/25/10/2022 end/26/10/2022    |
| **View range (2)**                 | :range last/NUMBER_OF_DAYS                     | :range last/3                             |
| **List all exercises**             | :list                                          | :list                                     |
| **List Personal Record(s) (1)**    | :pr n/NAME [n/NAME]...                         | :pr n/Deadlift n/Squat                    |
| **List Personal Record(s) (2)**    | :pr all/                                       | :pr all/                                  |
| **Generate workout (1)**           | :gen INDEX [, INDEX]... level/DIFFICULTY_LEVEL | :gen 1, 2 level/easy                      |
| **Generate workout (2)**           | :gen n/NAME [n/NAME]... level/DIFFICULTY_LEVEL | :gen n/Deadlift level/easy                |
| **Help menu**                      | :help                                          | :help                                     |
| **Exit program**                   | :wq                                            | :wq                                       |

<a href="#toc">Back To Table of Contents</a>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 8. Glossary of Terminologies
* **Command**: An instruction to perform an action in Gim, preceded by a colon, i.e. `:instruction` 
* **Command Line Interface (CLI)**: Interface that relies on keyboard inputs to interact with the system.
* **Exercise**: Physical activity done in a regular gym that is structured and repetitive, usually involving some weights.
* **Graphical User Interface (GUI)**: Interface that relies on mouse inputs on visible components to interact with the system.
* **Index**: Number associated to an Exercise in the [Exercise List](#42-exercise-list).
* **JavaScript Object Notation (JSON)**: Filetype used for storing the user's data that can be edited using a text editor. 
* **Parameters**: Inputs for commands that you come up with.
* **Personal Record (PR)**: Heaviest weight recorded in the exercise tracker for a specific exercise.
* **Reps**: Number of times you perform a specific exercise.
* **Sets**: Number of cycles of reps that you complete.
* **Vim**: A text editor, known for being lightweight, fast and efficient. It can be controlled entirely with the keyboard with no need for menus or a mouse.
* **Weight**: Total weight of equipment (in kg).

<a href="#toc">Back To Table of Contents</a>
