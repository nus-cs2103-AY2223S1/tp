---
layout: page
title: User Guide
---

StudMap (SM) is a desktop app for managing your students, optimized for use via a Command Line Interface (CLI) while
still having the benefits of a Graphical User Interface (GUI). If you can type fast, SM can get your student management
tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `studmap.jar` [Coming soon].

3. Copy the file to the folder you want to use as the _home folder_ for your StudMap.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all students.

    * **`add`**`n/John Doe m/CS2103T s/E1234567` : <br> Adds a student
      named `John Doe` to the StudMap.

    * **`delete`**`3` : Deletes the 3rd student shown in the current list.

    * **`clear`** : Deletes all students.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

<!-- TODO: ![help message](images/helpMessage.png) -->
[Sample UI To be added]

Format: `help`

### Adding a student: `add`

Adds a student to the StudMap.

Format: `add n/NAME m/MODULE s/ID [p/PHONE] [e/EMAIL] [g/GITNAME] [h/HANDLE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: <b>Tip:</b>
A student can have any number of tags (including 0)
</div>

Examples:

* `add n/John Doe m/CS2103T s/E1234567`
* `add n/Betsy Crowe t/PotentialTA e/betsycrowe@example.com s/E3141592 m/CS2101 p/1234567`
* `add n/Silos Yao t/StrongStudent g/silosyao s/E1234567 m/MA5203`

### Listing all students : `list`

Shows a list of all students in the StudMap.

Format: `list`

### Editing a student : `edit`

Edits an existing student in the StudMap.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [m/MODULE] [s/ID] [g/GITUSER] [h/TELEHANDLE] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list.
  The index **must be a positive integer** 1, 2, 3, …​ or use `all` to edit all students currently displayed.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student's tags by typing `t/` without specifying any tags after it.
* You can remove the student's phone, email, GitName, TeleHandle by typing `p/`, `e/`, `g/`, `h/` respectively.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567`
  and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.

### Locating students by name: `find`

Finds students whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`
  , `Bo Yang`

Examples:

* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  <!-- TODO: ![result for 'find alex david'](images/findAlexDavidResult.png) -->
  [Sample UI To be added]

### Deleting a student : `delete`

Deletes the specified student from the StudMap.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd student in the StudMap.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the StudMap.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

StudMap data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Editing the data file

StudMap data are saved as a JSON file `[JAR file location]/data/studmap.json`. Advanced users are welcome to update data
directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: <b>Caution:</b>
If your changes to the data file makes its format invalid, StudMap will discard all data and start with an empty data file at the next run.
</div>

### Sorting the students: `sort`

Sorts the list by the specified attribute in the specified order.

Format: `sort ORDER a/ATTRIBUTE`

- `ORDER` should be `asc` for ascending and `dsc` for descending order
- `ATTRIBUTE` currently supported are
  - `NAME`
  - `MODULE`
  - `PHONE`
  - `ID`
  - `GIT`
  - `HANDLE`
  - `EMAIL`
  - `ATTENDANCE`
  - `ASSIGNMENT`
  - `PARTICIPATION`

Examples:

* `sort asc a/name` sorts list by `NAME` in ascending order
* `sort dsc a/phone` sorts list by `PHONE` in descending order
* `sort asc a/attendance` sorts list by `ATTENDANCE` in ascending order

### Filtering the students: `filter`

Filtering students from the StudMap based on different
categories.

Format: `filter t/Keyword [MORE_KEYWORDS]`
* Filter students specific to the category "tags" represented
with the prefix `t/`
* There should be spaces between `filter` and `t/friends`
* Currently, filter works for three specific
categories - tags `t/`, module `m/` and assignments `a/`.

Example:
* `filter t/ friends` will return a filtered list of students
that are tagged as friends
* `filter m/ cs2103t` will return a filtered list of students
that enrolls in the module cs2103t.
* `filter a/ a01` will return a filtered list of students
  that are working on assignment A01.
* `filter t/friends family` will return a filtered list of
students that are tagged as friends family or both.


### Mark attendance of student: `mark`

Mark students as present or absent for a specified class.

Format: `mark INDEX/ALL ATTENDANCE c/CLASS`

* Attendance accepts two values only: `present` and `absent`
* :warning: Class names should only consist of alphanumerics, spaces, dashes and underscores! Using any other
character will lead to your class name being **rejected**
* Marking an existing class as either `present` or `absent` will **overwrite** the existing record

Examples:

* `mark 1 present c/T01` marks the 1st student as present for class `T01`
* `mark all absent c/T04` marks all students in the list as absent for class `T04`

### Unmark attendance of a student: `unmark`

Removes the attendance record of a specific class from students.

Format: `unmark INDEX/ALL c/CLASS`

* StudMap allows for attendances to be removed even if the student never had any record for that class
(i.e. no error will be thrown)

Examples:

* `unmark 1 c/T01` removes the attendance record for class `T01` from the 1st student
* `unmark all c/T04` marks all students as absent for class `T04` from all students in the list

### Add tag to student: `tag`

You can tag the student(s) with one or more text labels. This can help you to better identify and keep track of them.

<div markdown="block" class="alert alert-info">

  **:information_source: Note 1:** The tag should be short and limited to only alphabets and/or numbers (i.e. no spaces). <br>

  **:information_source: Note 2:** The tags are case-sensisitve. For example, `goodStudent` and `goodstudent` will
  be recognised as different labels.

  **:information_source: Note 3:** The tagging command is cumulative, that is, new tags are simply added to the student(s) and
  do not replace their previous tags. To remove tags from the student(s), see [untag](#remove-tag-from-student-untag) below. <br>

</div>

Format: `tag INDEX/ALL t/TAG [t/OTHER]`

- `INDEX`: You can specify the index of the student you want to add the tag to. Alternatively, you can use `ALL` to add the new tags to everyone in the currently display list.
- `TAG`: You can specify the label to be added to the student. At least one label should be specified when you use this command
- `OTHER`: You can also optional tags that you might also want to add to your students.

Examples:

* `tag 1 t/goodStudent` adds the tag of "goodStudent" to the first student in the current list.
* `tag all t/goodstudent t/potentialTA` add both "goodStudent" and "potentialTA" tag to the all student in the current list.

### Remove tag from student: `untag`

Remove the specified tag(s) from the student(s).

Format: `untag INDEX/ALL t/TAG [t/OTHER]`

- `INDEX`: You can specify index of the student to remove the tag from or use `ALL` to remove the tag for all students in the currently displayed list.
- `TAG`: You can specify the `TAG` to remove.There should be at least one label to be removed when you use this command. For the restrictions on the format of a `TAG`, see Note 1 for [Add tag to student](#add-tag-to-student-tag).
- `OTHER`: You can also include multiple tags to be removed from your student(s).


Examples:

* `untag 1 t/needMoreTime`
* `untag all t/needMoreTime t/late`


### Grade assignment for student: `grade`

You can change the grading status for the assignments using this command. If the record of the assignment does not yet exist for the specified student, a new entry for the assignment will be automatically created for the student. This can help you to better keep track of assignments that you have graded or received.

<div markdown="block" class="alert alert-info">

  **:information_source: Note 1:** The name of assignments should contain only numbers and alphabets (all upper case). If you include any lower case in the assignment name, it will be automatically converted to upper case.

</div>

Format: `grade INDEX/ALL STATUS a/ASSIGNMENT`

- `INDEX`: You can specify the index of the student you want to change the assignment grading status for. Alternatively, you can use `ALL` to modify the assignment grading status for every students in the currently displayed list.
- `STATUS`: You can specify the grading status for the assignment. Currently, following three statuses are supported:
  - `new`: The assignment has just been assigned to the student and hence it has not been submitted nor marked yet.
  - `received`: You have received the assignment submission from the student but you have not graded it yet.
  - `marked`: You have received and graded the assignment.
- `ASSIGNEMNT`: You can specify the assignment which you want to change the grading status for.


Examples:

* `grade 1 new a/A01` changes the assignment grading status for assignment "A01" of the first student in the list to `new`. Add an entry of A01 into the first student's record if it does not exist yet.
* `grade all marked a/A02` changes the assignment grading status for assignment "A02" of every students in the list to `marked`.

### Remove assignment from student: `ungrade`

You can remove the specified assignment from the student's record.

Format: `ungrade INDEX/ALL a/ASSIGNMENT`

- `INDEX`: You can specify index of the student remove the assignment from or use `ALL` to remove the assignment for all students in the currently displayed list.
- `ASSIGNMENT`: You can specify the record of the assignment to be removed.

Examples:

* `ungrade 1 a/A01`
* `ungrade all a/A01`


### Recording participation of a student: `participate`

Record participation of student(s)

Format: `participate INDEX/ALL STATUS p/COMPONENT`

- `INDEX` could be specified or use `ALL` to record participation component for all students in the list
- `STATUS` is either `yes` for participated or `no` for not participated
- `COMPONENT` is the participation component

Examples:

* `participate 1 yes p/P01`
* `participate 1 no p/P02`
* `participate all yes p/P03`

### Removing participation of a student: `unparticipate`

Remove participation of student(s)

Format: `unparticipate INDEX/ALL p/COMPONENT`

- `INDEX` could be specified or use `ALL` to remove specified participation component for all students in the list
- `COMPONENT` is the participation component

Examples:

* `unparticipate 1 p/P01`
* `unparticipate all p/P03 `

### Import students from CSV file: `import`

Imports student data from a CSV file stored on your computer.

Format: `import`

* Importing student data will add students to the existing student list, and will not clear any existing students 
* Running the command will open a file browser for you to select the CSV file to import
* The CSV format accepted by StudMap is strict! Please use the template provided in the link below

<div markdown="span" class="alert alert-warning">
:exclamation: <b>Caution:</b>
StudMap currently does not support any commas in any data field (cell) when importing a CSV file. Please avoid inputting any data
that has commas in the CSV.
</div>

For your convenience, please download the import template here: [template csv](files/import_template.csv)

Example Usage: Importing a fresh batch of students as a new StudMap user

1. Remove the default list of students by typing `clear`
2. Modify the import template using the CSV editor of your choice (e.g. Excel, Notepad)
   ![example CSV](images/exampleCSV.png)
   *Example of a properly edited import template*
3. Type the `import` command and select the CSV file you have modified 
4. If done correctly, StudMap will create the new students using the data from the CSV file uploaded

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous StudMap home folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action | Format, Examples
-------|------------------
**[Add](#adding-a-student-add)** | `add n/NAME m/MODULE s/ID [p/PHONE] [e/EMAIL] [g/GITNAME] [h/HANDLE] [t/TAG]…​` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com m/CS2103T s/E1234567 g/user1 h/@user1 t/friends t/owesMoney`
**[Clear](#clearing-all-entries--clear)** | `clear`
**[Delete](#deleting-a-student--delete)** | `delete INDEX`<br> e.g., `delete 3`
**[Edit](#editing-a-student--edit)** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [m/MODULE] [s/ID] [g/GITUSER] [h/TELEHANDLE] [t/TAG]…​` <br> e.g.,`edit 1 p/91234567 e/johndoe@example.com`
**[Find](#locating-students-by-name-find)** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**[List](#listing-all-students--list)** | `list`
**[Help](#viewing-help--help)** | `help`
**[Sort](#sorting-the-students-sort)** | `sort ORDER a/ATTRIBUTE` <br> e.g., `sort asc a/name`
**[Filter](#filtering-the-students-filter)** | `filter`
**[Mark](#mark-attendance-of-student-mark)** | `mark INDEX/ALL ATTENDANCE c/CLASS` <br> e.g., `mark 1 present c/T01`
**[Unmark](#unmark-attendance-of-a-student-unmark)** | `unmark INDEX/ALL c/CLASS` <br> e.g., `mark 1 c/T01`
**[Add tag](#tag-student-with-label-tag)** | `tag INDEX/ALL t/TAG [t/OTHER]` <br> e.g., `tag 2 t/goodStudent`
**[Remove tag](#remove-tag-from-student-untag)** | `untag INDEX t/TAG [t/OTHER]` <br> e.g., `untag 2 t/goodStudent`
**[Record participation](#recording-participation-of-a-student-participate)** | `participate INDEX/ALL STATUS p/COMPONENT` <br> e.g., `participate 2 yes p/C01`
**[Remove participation](#removing-participation-of-a-student-unparticipate)** | `unparticipate INDEX/ALL p/COMPONENT` <br> e.g., `unparticipate 2 c/C01`
**[Import CSV](#import-students-from-csv-file-import)** | `import`
