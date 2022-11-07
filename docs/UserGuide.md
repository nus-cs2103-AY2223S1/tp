---
layout: page
title: User Guide
---

Teacher’s Address Book (TAB) is a **desktop app made for teachers, teaching assistants(TA) and professors for managing contacts of each other, as well as their students, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TAB can get your contact management tasks done faster than traditional GUI apps.

TAs and Professors are now taking on more roles in different modules. As such, TAB allows the creation of multiple addressbooks which can be used to store the contact information of each specific module, which leads to neater organisation of contacts.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div markdown="block" class="index">

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TAB.jar` from [here](https://github.com/AY2223S1-CS2103T-T17-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your TAB.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com pos/Student a/John street, block 123, #01-01 t/CS2103T-T17` : Adds a contact named `John Doe` to TAB.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
For teachers who are teaching more than one module, please use one TAB for each module. Refer to relevant commands to see how you can create new TAB and swap between existing TABs.
</div>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* All commands are **case sensitive**
* All tokenizers are **case sensitive**
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL]` can be used as `edit 1 n/John Doe a/Jurong` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Launches the user guide in a browser window.

![User Guide](images/userGuideWindow.png)

Format: `help`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This can also be done by clicking Help or pressing F1 on the keyboard.
</div>

### Adding a person: `add`

Adds a person to TAB.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL pos/POSITION a/ADDRESS t/MODULE-GROUP`

<div markdown="block" class="alert alert-primary">:bulb: **Tip:**
A teaching assistant or professor can have more than one tag. A student can only have one tag.
Format of parameters:
* `NAME`: Alphanumerical
* `PHONE_NUMBER`: Numerical
* `Email`: local-part@domain
  * local-part: Alphanumerical and the following special characters, excluding the parentheses, (+_.-)
  * domain: The domain name must:
    * end with a domain label at least 2 characters long
    * have each domain label start and end with alphanumeric characters
    * have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
* `Position`: `Student`, `TA`, or `Professor` (case-insensitive)
* `Address`: Any value, but cannot be blank
* `Tags`: {Alphanumeric Module}-{Alphanumeric Tutorial group}
</div>

Adds a new contact with the provided details. Required fields include name, phone number, address, email, position, and tag. Tags represent the module and tutorial group a person is associated with and must be in the format of module-tutorial group.

Examples:
* `add n/Alex Yeoh p/87438807 e/alexyeoh@example.com pos/Student a/Blk 30 Geylang Street 29, #06-40 t/CS2103T-T17`
* `add n/Betsy Crowe p/92498754 e/betsycrowe@example.com pos/TA a/Blk 30 Lorong 3 Serangoon Gardens, #07-18 t/CS2103T-T17`

### Listing all persons : `list`

Shows a list of all persons in TAB.

Format: `list`

### Displaying the details of a person : `show`

Shows the detailed information of a person.

General displayed information are:
* Name
* Position
* Phone
* Email
* Tags
* Address
* Remarks

There are also additional information unique to each role

Professors:
* Roles: `Coordinator`, `Tutor`, `Lecturer`, `Advisor` or `Unassigned`

TA:
* Availability: `Available` or `Unavailable`

Student
* Attendance: represented as a fraction _e.g. 9/10_
* Grade: represented as a fraction. This grade is the overall grade calculated from the list of already graded Assignments.
* Graph containing each Assignment and their scores(only if their grade has been updated)
Format: `show INDEX`

* Shows the detailed information of the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you cannot see all the added assignments in the graph, simply enlarge your tab!
</div>

Examples:
* `show 3` shows the detailed information of the 3rd person in TAB.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This can also be done by clicking on the person.
</div>

### Editing a person : `edit`

Edits an existing person in TAB.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL]`


Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Editing of Tags and Position are not allowed.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower` edits the name of the 2nd person to be `Betsy Crower`

### Adding a remark : `remark`

Adds or edits a remark of an existing person in TAB.

Format: `remark INDEX r/REMARK`

Modifies a remark of a person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* A person can have at most 1 remark.
* When editing remark, the existing remark of the person will be removed i.e. adding of remarks is not cumulative.
* You can remove the person's remark field by typing `r/` without
  specifying any message after it.

Examples:
* `remark 1 r/Interested to be a TA` edits the remark field of the 1st person to be `Interested to be a TA`.
* `remark 2 r/remark_one r/remark_two` edits the remark field of the 2nd person to be `remark_two` and not `remark_one`.
* `remark 3 r/` clears the remark field of the 3rd person.

### Editing the attendance of a student : `attendance`

Edits the attendance of an existing student in TAB.

Format: `attendance INDEX attendance/ATTENDANCE`

Edits the attendance of the person (whose position must be student) at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
*  `attendance 1 attendance/10/13` edits the attendance of the 1st person to be `10/13`.

### Adding assignments to all students : `assignments`

Adds assignments to all students in the TAB.

Format: `assignments assignments/ ASSIGNMENT_1 w/ASSIGNMENT_1_WEIGHTAGE, ASSIGNMENT_2 w/ASSIGNMENT_2_WEIGHTAGE…​`

* The assignments are added to all students in the TAB. Students that are added to the TAB afterwards will automatically be instantiated with the assignments.
* There must at least be one student inside TAB when running this command. The command will fail otherwise.
* The weightage of the assignments must be integers that add up to 100%. The command will fail otherwise.
* At most 10 assignments can be added.

Examples:
* `assignments assignments/ Assignment 1 w/15, Assignment 2 w/15, Midterms w/30, Finals w/40` adds Assignment 1: 15% weightage, Assignment 2: 15% weightage, Midterms: 30% weightage, and Finals: 40% weightage to all students in the TAB.
* `assignments assignments/ Assignment 1 w/20 Finals w/15` will fail as the weightage does not add up to 100%.

### Editing the grade of a student : `grade`

Edits a student's grade of an assignment of in TAB.

Format: `grade INDEX assignment/INDEX grade/GRADE`

Edits the grade of an assignment of the person (whose position must be student) at the specified `INDEX`. The first index refers to the index number shown in the displayed person list. The second index refers to the index of the assignment to be edited as shown in the assignment list of the specified student. Grade must follow the format [integer]/[integer]

The indices **must be positive integers** 1, 2, 3, …​
* The grade shown on the detail page of a student represent the overall grade that the student has achieved, calculated based on the grade and weightage of each assignment.

Examples:
* `grade 2 assignment/1 grade/68/80` edits the grade of the 1st assignment of the 2nd person (whose position is student) to be `68/80`.
* `grade 1 assignment/1 grade/68/80` will fail if the position of the 1st person in the displayed person list is not a student.

### Editing the availability of a TA : `avail`

Edits the availability of an existing TA in TAB.
Availabilities are case-insensitive.

Format: `avail INDEX avail/AVAILABILITY`

Edits the availability of the person (whose position must be TA) at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
*  `avail 1 avail/Available` edits the availability of the 1st person to be `Available`.

### Editing the roles of a Professor : `roles`

Edits the roles of an existing professor in TAB.

Format: `roles INDEX roles/ROLE`

Edits the roles of the person (whose position must be professor) at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
Roles are case-insensitive
*  Each professor may only have 1 role


Examples:
*  `roles 1 roles/Coordinator` edits the roles of the 1st person(must be a professor) to be `Coordinator`.


### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Filtering persons by tag: `filter`

Filters persons with the specified tag.

Format: `filter TAG`

* The search is case-insensitive. e.g. `cs2103t-t17` will match `CS2103T-T17`
* Only full word will be matched. e.g. `CS2103T` will not match `CS2103T-T17`

Examples:
* `filter CS2103T-T17` returns persons with the tag `CS2103T-T17`
* `filter CS2103T` returns no result.

### Deleting a person : `delete`

Deletes the specified person from the TAB.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the TAB.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the TAB.

Format: `clear`

### Creating new TAB : `new`

Creates a new TAB and automatically swaps to the newly created data file.<br>
There can be a maximum of 5 books.
Format: `new`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This can also be done by clicking `File` - `New Book`
</div>

### Swapping between TABs : `swap`

Swaps between existing TABs.

Format: `swap`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This can also be done by clicking `File` - `Swap Book`
</div>

### Renaming the data file : `rename`

Renames the current data file.

Format: `rename {NEW_NAME}`

Renames the current data file as the `NEW_NAME` in lowercase
* `NEW_NAME` only accepts alphanumeric, '-' and '_' characters.

Example:
* `rename cs2103t` renames the current data file as `cs2103t.json`

### Saving the data

TAB data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAB data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TAB will discard all data and start with an empty data file at the next run.
</div>

### Exiting the program : `exit`

Exits the program.

Format: `exit`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This can also be done by clicking File - Exit.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAB home folder.

**Q**: Why is editing of Position restricted?<br>
**A**: TAB depicts the addressbook of a certain module at a certain period of time. Positions within TAB should be consistent in that time frame. For example, it would not make sense for a Student to become a Professor.

**Q**: Why is editing of Tags restricted?<br>
**A**: AB depicts the addressbook of a certain module at a certain period of time. Tags represent the module and tutorial group of a students. If a student were to drop the module halfway, he should be removed from TAB instead using the delete feature.

**Q**: Why should there be only Persons from one module in each addressbook? This is a big limitation!<br>
**A**: Not really. By only allowing Persons from one module in each addressbook, it ensures that your TAB is neat and streamlined. If you want to keep contacts across modules, please create a new TAB by using our `new` and `swap` features.

**Q**: I just created a new book and nothing changed?<br>
**A**: The new TAB's name appears at the bottom left corner.

![FAQ_Identify_New_Book](images/FAQ_Identify_New_Book.png)

**Q**: How do I properly delete a TAB once I created it?<br>
**A**: To delete a created TAB here are the steps to follow:<br>
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If there is only one TAB, do not follow the steps below! Use the `clear` command instead!
</div>

- Step 1:
    - From the `data` directory, delete the `.json` file you like to remove
- Step 2:
    - From the `preferences.json`, delete the **same** `.json` file from the `allAddressBookFilePath` parameter.

**Case By Case Basis:**
- Step 3: (**Only if** `"addressBookFilePath"` has the same name as the TAB you want to delete)
    - If the `.json` file is also `addressBookFilePath`, swap it for the first TAB under `allAddressBookFilePath`
      and set `addressBookIndex` to 0

<div markdown="block" class="alert alert-success">:information_source: **Example:**<br>

Here I would like to delete a TAB named `addressbook`<br>

* Step 1:<br>
Go into `data` folder and delete `addressbook.json`<br>
&emsp; Before:<br>
&emsp;![delete_step_1a.png](images/delete_step_1a.png)<br><br>
&emsp;After:<br>
&emsp;![delete_step_1b.png](images/delete_step_1b.png)<br><br>

Step 2:<br>
* Open `preferences.json`<br>
* Remove `, "data\\addressbook.json"` from `"allAddressBookFilePath"`<br>
  &emsp;Before:
  &emsp;![delete_step_2a.png](images/delete_step_2a.png)<br><br>
  &emsp;After:
  &emsp;![delete_step_2b.png](images/delete_step_2b.png)<br>
  **Save the file and you are done!**<br><br>

**Case By Case Basis:**<br>
Step 3: (if `"addressBookFilePath"` has the same name as the TAB you want to delete):<br>
* Remove both `addressbook.json`<br>
  &emsp;Before:
  &emsp;![delete_step_3a.png](images/delete_step_3a.png)<br><br>
  &emsp;After:
  &emsp;![delete_step_3b.png](images/delete_step_3b.png)<br><br>
* Copy the first TAB name from `"allAddressBookFilePath"`, paste into `"addressBookFilePath"` and change `"addressBookIndex"` to 0.<br>
  &emsp;It should look something like this. (Note the changes in green)
  &emsp;![delete_step_3c.png](images/delete_step_3c.png)<br><br>
  **Save the file and you are done!**<br>

</div>



--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS pos/ POSITION [t/TAG]…​` <br> e.g., `add n/Alex Yeoh p/87438807 e/alexyeoh@example.com pos/Student a/Blk 30 Geylang Street 29, #06-40 t/CS2103T-T17`
**List** | `list`
**Show** | `show INDEX`<br> e.g., `show 2`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Remark** | `remark INDEX r/REMARK`<br> e.g., `remark 1 r/Interested to be a TA`
**Attendance** | `attendance INDEX attendance/ATTENDANCE` <br> e.g., `attendance 1 attendance/10/13`
**Assignments** | `assignments assignments/ASSIGNMENT_1 w/ASSIGNMENT_1_WEIGHTAGE, ASSIGNMENT_2 w/ASSIGNMENT_2_WEIGHTAGE…​`<br> e.g., `assignments assignments/Assignment 1 w/15, Assignment 2 w/15, Midterms w/30, Finals w/40`
**Grade** | `grade INDEX assignment/INDEX grade/GRADE` <br> e.g., `grade 2 assignment/1 grade/68/80`
**Availability** | `avail INDEX avail/AVAILABILITY` <br> e.g., `avail 1 avail/Available`
**Roles** | `roles INDEX roles/ROLE` <br> e.g., `roles 1 roles/Coordinator`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter TAG`<br> e.g., `filter CS2103T-T17`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Clear** | `clear`
**New** | `new`
**Swap** | `swap`
**Rename** | `rename {NEW_NAME}`<br> e.g., `rename CS2103T`
**Exit** | `exit`

</div>
