---
layout: page
title: User Guide
---
<div align="center">
<h1> Pupilist User Guide</h1>
Welcome to Pupilist's User Guide!
</div>

<p align="center">
  <img src="images/pupilist.png" alt="Pupilist's app icon"/>
</p>

Pupilist is an **application for managing students details for private tutors**. It aims to **help you organise your students information to better manage your lessons and students in an all-in-one application**.


If you are a private tutor struggling to:
1. keep track of your students and their individual lesson plans.
2. keep track of your students grades, homework and attendance.
3. keep track of your lesson timings.
4. keep track of your students' contact information.<br>

Pupilist can get your scheduling done faster with single line CLI command inputs to manage all your tutoring needs.
<br>
* Table of Contents
{:toc}

---

## Introducing Pupilist
{to be added layout of ui and pointers to indicate where command box is etc}

## Glossary


| Term      | Description                                                                                                                                                                                                                                                                                                                                        |
| ----------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| CLI       | Command-Line-Input, refers to text-based command inputs you will enter in the CommandBox.                                                                                                                                                                                                                                                          |
| GUI       | Graphical-User-Interface, refers to the UI displayed to you.                                                                                                                                                                                                                                                                                       |
| Parameter | Refers to the information you will need to give to Pupilist along with your<br> command so that Pupilist can execute an action based on that information.<br> For example, in the [`View`](#viewing-persons-details-view) command requires a NAME parameter for Pupilist to display information of the Person ,assuming a valid name in view mode. |
| Prefixes  | A prefix indicates the type of field you are keying in. You can find the list of prefixes supported by Pupilist[here](#prefixes-summaries)                                                                                                                                                                                                         |

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Pupilist.jar` from [here](https://github.com/AY2223S1-CS2103T-W09-4/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your address book.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.
   * **`add`**`n/John Doe p/98765432 lp/Algorithms` : Adds a contact named `John Doe` to the Address Book.
   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.
   * **`clear`** : Deletes all contacts.
   * **`exit`** : Exits the app.
6. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
* Items in square brackets are optional.<br>
  e.g `n/NAME [h/HOMEWORK]` can be used as `n/John Doe h/math` or as `n/John Doe`.
* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/Tag]…` can be used as ` ` (i.e. 0 times), `t/Math`, `t/Math t/Science` etc.
* Parameters with prefixes can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
* Index has to be supplied according to format given.<br>
  e.g. if the command specifies `hw INDEX h/HOMEWORK`, `hw h/HOMEWORK INDEX` is not allowed.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page, and a list of basic commands.<br>
**Usage**: All modes

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the address book. A person is considered a duplicate only if the names are the same (non case-sensitive).<br>
**Usage**: List mode only

Format: `add n/NAME p/PHONE_NUMBER lp/LESSON_PLAN [t/TAG]...`

Examples:

* `add n/Farisa p/87159999 lp/Sec 4 Chemistry`

### Listing all persons : `list`

Shows a list of all persons in the address book. Changes the address book to list mode.
Also allows you to see the next session for you to attend along with the person you
are going to be teaching next.<br>
**Usage**: All modes

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.
For fields requiring an INDEX, existing fields have to contain a value before editing is allowed, else there will be no INDEX.<br>
**Usage**: View mode only

Format: `edit [n/NAME] [p/PHONE] [lp/LESSON_PLAN] [t/TAG]...`<br>
`edit [h/ a/ g/ s/]INDEX NEW_FIELD`

It requires at least one of the optional fields:

- n/: To be followed by updated name of student
- p/: To be followed by updated phone number of student
- lp/: To be followed by updated lesson plan of student
- h/: To be followed by INDEX of homework to be updated, then updated homework
- a/: To be followed by INDEX of attendance to be updated, then updated attendance
- g/: To be followed by INDEX of grade to be updated, then updated grade
- s/: To be followed by INDEX of session to be updated, then updated session
- t/: To be followed by the person's tags, all tags can be removed by typing `t/` without specifying any tags after it

Examples:

* `view Alex Yeoh` returns `Alex Yeoh` <br>
  `edit h/2 math not done` updates 2nd field of `Alex Yeoh`'s HOMEWORK to `math not done`

### Removing specific field in person: `remove`

Removes a specific field of a person in the address book at the specified index.<br>
**Usage**: View mode only

It requires one field:

- h/: To be followed by INDEX of homework to be removed
- a/: To be followed by INDEX of attendance to be removed
- g/: To be followed by INDEX of grade to be removed
- s/: To be followed by INDEX of session to be removed

Allows for edits of multiple fields in a single command. However, a single command should remove at most one item from each field. <br>

Format: `remove [h/ a/ g/ s/]INDEX`

Examples:

* `view Alex Yeoh` returns `Alex Yeoh` <br>
  `remove h/2` removes `Alex Yeoh`'s *homework at second index*
* `view John Doe` returns `John Doe` <br>
  `remove h/2 a/1` removes `John Doe`'s *homework at second index* and *attendance at first index*

### Marking specific field in person: `mark`

Use `mark` command to mark a specific field of a person in the address book.<br>
**Usage**: View mode only

It requires one field:

- h/: To be followed by INDEX of homework to be marked
- a/: To be followed by INDEX of attendance to be marked

Allows for marking of only one field in a single command.
Does not allow commands such as `mark h/2 a/1` or `mark a/2 a/3`.

Format: `mark [h/ a/]INDEX`

Examples:

* `view Alex Yeoh` returns `Alex Yeoh` <br>
  `mark a/1` marks `Alex Yeoh`'s *attendance at first index*

### Unmarking specific field in person: `unmark`

Use `unmark` command to unmark a specific field of a person in the address book.<br>
**Usage**: View mode only

It requires one field:

- h/: To be followed by INDEX of homework to be unmarked
- a/: To be followed by INDEX of attendance to be unmarked

Allows for unmarking of only one field in a single command.
Does not allow commands such as `unmark h/2 a/1` or `unmark a/2 a/3`.

Format: `unmark [h/ a/]INDEX`

Examples:

* `view Alex Yeoh` returns `Alex Yeoh` <br>
  `unmark h/4` unmarks `Alex Yeoh`'s *homework at fourth index*

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.<br>
**Usage**: All modes

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Viewing persons details: `view`

View details of a person. Required to `edit` or `remove` person's details.
You can only view one person's details at one time.<br>
**Usage**: All modes

Format: `view NAME`

* The command requires the full `NAME` of a person.
* The search is case-insensitive. e.g `hans` will match `Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`

![result for 'view alex yeoh'](images/viewAlexYeohResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.<br>
**Usage**: View or list mode only

Format: `delete INDEX`

* Deletes the person with the specified `INDEX`.
* In view mode, INDEX should always be 1 (as per the view list)

Examples:

* `delete 1` removes the first student in the address book.

### Clearing all entries : `clear`

Clears all entries from the address book.<br>
**Usage**: All modes

Format: `clear`

### Adding Homework to student: `hw`

Adds a description of homework to student in address book.
Does not check for duplicate homework entries.<br>
**Usage**: View or list mode only

Format: `hw INDEX h/HOMEWORK`

* Adds homework to student with the specified `INDEX`.
* In view mode, INDEX should always be 1 (as per the view list)

Examples:

* `hw 1 h/math assignment` adds math assignment to first student in address book.

### Adding Grade Progress to student: `grade`

Use `grade` command to add a description of grade progress to student in address book.
As GRADE_PROGRESS does not require any fixed format and it is up to the user to have their own formats.
Pupilist does not check for duplicate entries of grades.
**Usage**: View or list mode only

Format: `grade INDEX g/GRADE_PROGRESS`

* Adds grade progress to student with the specified `INDEX`.
* In view mode, INDEX should always be 1 (as per the view list)

Examples:

* `grade 1 g/Math: D+` adds Math: D+ grade to first student in address book.

### Adding Attendance to student : `attendance`

Adds a formatted description of dates students attended class in address book.
Does not check for duplicate entries.<br>
**Usage**: View or list mode only

Format: `attendance INDEX a/ATTENDANCE`

* Adds attendance to student with the specified `INDEX`.<br>
* Attendance must be in `YYYY-MM-DD` format
* In view mode, INDEX should always be 1 (as per the view list)

Examples:

* `attendance 1 a/2022-12-08` marks 2022-12-08 attendance for first student in address book.

### Adding Session to student: `session`

Adds a formatted session timing to student, expected to repeat weekly.
Does not check for duplicate entries.<br>
**Usage**: View or list mode only

Format: `session INDEX s/TUITION_TIME`

* Adds tuition time to student with the specified `INDEX`.<br>
* Tuition time must be in `DDD HH:MM` format where `HH:MM` ranges from 00:00 to 23:59
* In view mode, INDEX should always be 1 (as per the view list)

Examples:

* `session 1 s/MON 12:00` adds a tuition slot of 12 afternoon, Monday to first student in address book.

### Update Lesson Plan for student: `lesson`

Updates a student's lesson plan by overwriting the current one.<br>
**Usage**: View or list mode only

Format: `lesson INDEX lp/LESSON_PLAN`

* Updates lesson plan of student with the specified `INDEX`.<br>
* In view mode, INDEX should always be 1 (as per the view list)

Examples:

* `lesson 1 lp/science` changes lesson plan of first student to science.

### Viewing daily schedule: `show`

Displays all sessions scheduled on a certain day of the week. Changes address book to schedule mode.<br>
**Usage**: All modes

Format: `show [DDD]`

Examples:

* `show MON` lists all tuition timings scheduled for Monday.

### Exiting the program : `exit`

Exits the program.<br>
**Usage**: All modes

Format: `exit`

### Next session

Pupilist helps to organise your session timings automatically, no need for commands for this feature!<br>
It will show you this feature on launch of the application and when you use the [`list`](#listing-all-persons--list) command.

Pupilist takes your current device time and sees the sessions that are upcoming if you have any,<br>
then shows you the name of the person and the session timing. If you don't have any sessions with anyone, <br>
it will also tell you that there are no next session timing.

![next session feature](images/next_session.png)

### Saving the data

Pupilist data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Pupilist data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.<br>
**Q**: How do I start?<br>
**A**: Launch the app and use the help to learn all about the commands!<br>
**Q**: Where do I check for updates on the app?<br>
**A**: Check periodically on github for our latest updates on the app.

---

## Summaries

### Command summary


| Action                 | Format, Examples                                                                                                                                                             |
| ------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Add**                | `add n/NAME p/PHONE_NUMBER lp/LESSON_PLAN [t/TAG]...` <br> e.g., `add n/James Ho p/96775567 lp/english`                                                                      |
| **Add Attendance**     | `attendance INDEX a/ATTENDANCE`<br> e.g., `attendance 2 a/2022-12-08`                                                                                                        |
| **Add Grade**          | `grade INDEX g/GRADE`<br> e.g., `grade 2 g/English: B+`                                                                                                                      |
| **Add Homework**       | `hw INDEX h/HOMEWORK`<br> e.g., `hw 1 h/Science worksheet`                                                                                                                   |
| **Add Session**        | `session INDEX s/SESSION` <br> e.g., `session 1 s/mon 09:00`                                                                                                                 |
| **Clear**              | `clear`                                                                                                                                                                      |
| **Delete**             | `delete INDEX`<br> e.g., `delete 1`                                                                                                                                          |
| **Edit**               | `edit [n/NAME p/PHONE_NUMBER lp/LESSON_PLAN] [t/TAG]...`<br> `edit [s/INDEX SESSION h/INDEX HOMEWORK g/INDEX GRADE a/INDEX ATTENDANCE]`<br> e.g.,`edit n/James Lee h/1 math` |
| **Exit**               | `exit`                                                                                                                                                                       |
| **Find**               | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                   |
| **Help**               | `help`                                                                                                                                                                       |
| **List**               | `list`                                                                                                                                                                       |
| **Mark Item**          | `mark [h/INDEX HOMEWORK a/INDEX ATTENDANCE]`<br> e.g., `mark a/1`                                                                                                            |
| **Remove**             | `remove [s/INDEX SESSION h/INDEX HOMEWORK g/INDEX GRADE a/INDEX ATTENDANCE]`<br> e.g., `remove h/1`                                                                          |
| **Show Schedule**      | `show [DDD]`<br> e.g., `show MON`                                                                                                                                            |
| **Unmark Item**        | `unmark [h/INDEX HOMEWORK a/INDEX ATTENDANCE]`<br> e.g., `unmark h/2`                                                                                                        |
| **Update Lesson plan** | `lesson INDEX lp/LESSON_PLAN`<br> e.g., `lesson 1 lp/Biology`                                                                                                                |
| **View**               | `view NAME`<br> e.g., `view James Ho`                                                                                                                                        |

### Prefixes summaries


| Prefix | Definition                             | Usage                                                                                                                                                                                      | Example        |
|--------|----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------|
| n/     | Name                                   | A string of alphanumeric characters. Whitespaces are allowed. Required                                                                                                                     | n/Alex Ho      |
| p/     | Phone number                           | Numbers only. Required. Whitespaces not allowed and at least 3 digits.                                                                                                                     | p/97402341     |
| t/     | Tag that is associated to the student. | A string of characters. Optional.                                                                                                                                                          | t/Star student |
| s/     | Session timing of lessons              | A String of input in the format of`DDD HH:mm` <br>where DDD is three characters making up the day, HH are two numbers making up the hour and <br> mm are two numbers making up the minute. | s/tue 08:30    |
| g/     | Subject and its grade                  | A string of characters                                                                                                                                                                     | g/Math: A+     |
