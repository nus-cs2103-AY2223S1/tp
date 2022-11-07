---
layout: page
title: User Guide
---

FRIDAY is a **desktop app for CS1101S Teaching Assistants to organize and track their students’ information and progress, 
optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, FRIDAY can get your student management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java 11 or above installed in your computer.

2. Download the latest `friday.jar` from [here](https://github.com/AY2223S1-CS2103T-W15-4/tp/releases).

3. Copy the file to the folder you want to use as the *home folder* for your FRIDAY.

4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Startup.png](images/Startup.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
    * **`add n/Alex Yeoh t/al3xx c/2022-10-25`** : Adds a student named `Alex Yeoh` to FRIDAY.

    * **`list`** : Lists all students.
    
    * **`delete 2`** : Deletes the 2nd student shown in the current list.

    * **`clear`** : Deletes all students.

    * **`exit`** : Exits FRIDAY.
   
6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## A guide to using FRIDAY's CLI

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>

  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>

  e.g. `n/NAME [t/TELEGRAM_HANDLE]` can be used as `n/John Doe t/johndoe` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>

  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/fast learner`, `tag/fast learner tag/good at recursion` etc.

* Parameters can be in any order.<br>

  e.g. if the command specifies `n/NAME t/TELEGRAM_HANDLE`, `t/TELEGRAM_HANDLE n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken. Note that this does not apply to the `INDEX` parameter in commands that have it, namely the `delete`, `edit`, `remark`, `grade`, `mark` and `unmark` commands, as they expect exactly one `INDEX` parameter.<br>

  e.g. if you specify `t/johndoe t/johndoe123`, only `t/johndoe123` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>

  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* `INDEX` is used in commands to refer to a specific student by their index number on the currently displayed list,
  so it ` **must be a positive integer** 1, 2, 3, …​

</div>


## Features


## Student management

Overview: These features allow you to add, delete and edit your students' details.

### Adding a student: `add`

Adds a student to FRIDAY, with the given name, Telegram handle, consultation date, Mastery Check date, and tags.

Format: `add n/NAME [t/TELEGRAM_HANDLE] [c/CONSULTATION_DATE] [m/MASTERY_CHECK_DATE] [tag/TAG]...`

* The student details and their associated prefixes are:
    * Name - `n`
    * Telegram Handle - `t`
    * Consultation date - `c`
    * Mastery Check date - `m`
    * Tags - `tag`

<div markdown="block" class="alert alert-info">
**:information_source: Note:** <br>
* All student names and Telegram handles in FRIDAY must be unique.
* Names and Telegram handles are case-insensitive.
* Dates for consultation and Mastery Check must be in the format YYYY-MM-DD.
</div>

<div markdown="block" class="alert alert-primary">
**:bulb: Tip:** <br>
* A student can have any number of tags (including 0).
</div>


Example: `add n/Alex Yeoh t/al3xx c/2022-10-25 m/2022-08-16 tag/cool guy tag/quiet`

Outcome: a student named Alex Yeoh is added.

![AddCommandOutcome.png](images/AddCommandOutcome.png)

### Deleting a student: `delete`

Deletes the student at the given index from FRIDAY.

Format: `delete INDEX`

<div markdown="block" class="alert alert-info">
**:information_source: Note:** <br>
* The index of the student must be specified and there should be exactly one INDEX parameter.
</div>

<div markdown="block" class="alert alert-primary">
**:bulb: Tip:** <br>
* The index of the student can be seen from the student list.
</div>

### Editing a student: `edit`

Edits a student's details in FRIDAY.

Format: `edit INDEX [n/NAME] [t/TELEGRAM_HANDLE] [c/CONSULTATION] [m/MASTERY_CHECK] [tag/TAG]...`

<div markdown="block" class="alert alert-info">
**:information_source: Note:** <br>
* The index of the student must be specified and there should be exactly one INDEX parameter.
* You can choose which field to edit for the student. Name, Telegram handle, consultation, Mastery Check, and tag(s) are 
optional fields, but there should be at least one field specified for the `edit` command to be valid.<br>
</div>

<div markdown="block" class="alert alert-primary">
**:bulb: Tip:** <br>
* The index of the student can be seen from the student list.
* A student can have any number of tags (including 0).
</div>

Example: `edit 1 n/Alex Yap t/AlexYap tag/Experienced coder tag/Intern m/2022-11-06 c/2022-10-10`

Initial: A student with name "Alex Yeoh", with the following details: Telegram handle as "@al3xx", Mastery Check date
on 2022-08-16, consultation date on 2022-11-11, and a tag "Colour blind".

![EditCommandInitial.png](images/EditCommandInitial.png)

Outcome: Student's name changed to "Alex Yap", along with the following changed details: Telegram handle as "@AlexYap",
Mastery Check date as 2022-11-06, consultation date as 2022-10-10, and tags as "Experienced coder" and "Intern".

![EditCommandOutcome.png](images/EditCommandOutcome.png)

### Editing a remark for a student: `remark`

Adds a remark for a specified student.

Format: `remark INDEX [r/REMARK]`

<div markdown="block" class="alert alert-info">
**:information_source: Note:** <br>
* The index of the student must be specified and there should be exactly one INDEX parameter.
</div>

<div markdown="block" class="alert alert-primary">
**:bulb: Tip:** <br>
* The index of the student can be seen from the student list.<br>
* The remark is optional. If you do not include the remark (i.e. input `remark INDEX` as your command), FRIDAY will 
remove any existing remark for the specified student.<br>
</div>

Example: `remark 1 r/Aspiring to be a CS1101S TA for next year`

Outcome: The student at the 1st index (Alex Yap) will have the remark "Aspiring to be a CS1101S TA for next year".

![RemarkCommandOutcome.png](images/RemarkCommandOutcome.png)

## Organizing students

Overview: These features allow you to organize your students to suit your needs.

### Sorting students: `sort`

Sorts all students in FRIDAY with the given criteria, in ascending or descending order.

Format: `sort CRITERIA/ORDER`

* `CRITERIA` can be
    * `n` (name)
    * `t` (Telegram handle)
    * `c` (consultation)
    * `m` (Mastery Check)
    * `ra1` (Reading Assessment 1)
    * `ra2` (Reading Assessment 2)
    * `pa` (Practical Assessment)
    * `mt` (Midterm Test)
    * `ft` (Final Examination)
* `ORDER` can be
    * `a` (ascending)
    * `d` (descending)

How criteria are sorted:
* Names and Telegram handles - alphabetical order
* Consultations and Mastery Checks - chronological order
* Grades - numerical order


<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
* If the `find` command was run before `sort`, using `sort` will undo the result of `find` and all students will be sorted.
* Students with missing information will be sorted first in descending order, e.g. students with no Telegram handles
  will be shown before students with Telegram handles.
</div>

Example: enter `sort m/a` with an unsorted list of students.

![SortCommand.png](images/SortCommand.png)

Outcome: students are sorted by Mastery Check date, from earliest to latest.

![SortCommandOutcome.png](images/SortCommandOutcome.png)

### Finding individual student details: `find`

View a particular student's details.

Format: `find KEYWORDS`

<div markdown="span" class="alert alert-primary">
**:bulb: Tip:** <br>
Use student name/telegram handle/consultation/mastery check date/remark to search up a particular student.
Note: Multiple keywords can be entered and each keyword is separated by a space.
Note: when searching for exam grade use format `find [exam_name:EXAM_SCORE]`
</div>

Example:

![FindCommand.png](images/FindCommand.png)

Outcome:

![FindCommandOutcome.png](images/FindCommandOutcome.png)

### Viewing all students: `list`

Lists all students in FRIDAY. This command helps you to reset the sorting and finding done by the `sort` and `find` commands respectively.

Format: `list`

## Grading students

Overview: These features allow you to record your students grades.

### Recording grades for a student: `grade`

Records the grades of the assessments and examinations for a specified student.

Format: `grade INDEX [ra1/RA1_SCORE] [ra2/RA2_SCORE] [pa/PRACTICAL_SCORE] [mt/MID_TERM_SCORE] [ft/FINALS_SCORE]`

* The examinations and their associated prefixes are:
  * Reading Assessment 1 - `ra1`
  * Reading Assessment 2 - `ra2`
  * Practical Assessment - `pa`
  * Midterm Test - `mt`
  * Final Test `ft`

<div markdown="block" class="alert alert-info">
**:information_source: Note:** <br>
* The index of the student must be specified and there should be exactly one INDEX parameter.
* The scores of the assessments must be numerical as percentages, i.e. between 0% and 100% inclusive, with up to 2
decimal places allowed. `0`, `100.00` and `69.1` are examples of valid scores.
</div>

<div markdown="span" class="alert alert-primary">
**:bulb: Tip:** <br>
* The index of the student can be seen from the student list.
* The scores are optional, but there should be at least one score specified for `grade` command to be valid.
</div>

Example:`grade 1 ra1/90 ra2/80.1 pa/100.00 mt/85.23 ft/78`

Outcome: The student at the 1st index (Alex Yap) will have their grades updated.

![GradeCommand.png](images/GradeCommand.png)


### Marking a student's Mastery Check as passed: `mark`

After a student has passed their Mastery Check, you can use the `mark` command to mark their Mastery Check as passed. This will update the list of students with the status of the specified student's Mastery Check.

Format: `mark INDEX`

<div markdown="block" class="alert alert-primary">
**:bulb: Tip:** <br>
* The index of the student must be specified and there should be exactly one INDEX parameter.<br>
* The index of the student can be seen from the student list.<br>
</div>

Example before entering `mark 1`:

![MarkCommandBefore.png](images/MarkCommandBefore.png)


Outcome after entering `mark 1`:

![MarkCommandOutcome.png](images/MarkCommandOutcome.png)

As you can see, a "(passed)" status is added to student 1's Mastery Check after the `mark` command is run.

### Unmarking a student's Mastery Check: `unmark`

Unmarks the Mastery Check of a specified student and removes its "(passed)" status. This will come in handy if you accidentally mark the Mastery Check of a student as passed, even though that is not the case.

Format: `unmark INDEX`

<div markdown="span" class="alert alert-primary">
**:bulb: Tip:** <br>
The index of the student must be specified and there should be exactly one INDEX parameter.<br>
The index of the student can be seen from the student list.<br>
</div>

Example before entering `unmark 1` (Assuming student 1's Mastery Check has previously been marked as passed):

![UnmarkCommandBefore.png](images/UnmarkCommandBefore.png)

Example after entering `unmark 1`:

![UnmarkCommandOutcome.png](images/UnmarkCommandOutcome.png)

As you can see, the "(passed)" status has been removed from student 1's Mastery Check after the `unmark` command is run.

## Miscellaneous features

Overview: Other features that aid you in using FRIDAY.

### Getting User Guide link: `guide`

If you ever need to refer to our User Guide while using FRIDAY, use this command to obtain the link to FRIDAY's User Guide.

Format: `guide`

### Getting help: `help`

Shows a summary of commands along with their command word used in FRIDAY. This allows you to have an easily accessible summary when using FRIDAY.<br>
It also includes a link to this User Guide if needed.

Format: `help`

### Clearing all existing data: `clear`

On your first launch of FRIDAY, a sample list of students is displayed. The `clear` command allows you to clear this and any other existing data in FRIDAY, resulting in an empty Student list.

Format: `clear`

### Exiting FRIDAY : `exit`

Exits FRIDAY. See you again soon!

Format: `exit`

## Features for advanced users

Overview: These features help you personalize your FRIDAY experience and improve your productivity when using FRIDAY.

### Adding aliases: `alias`

Adds an alias for a command into FRIDAY, which you can use in place of the default command keywords.<br>
This allows you to be more efficient in using FRIDAY.

Format: `alias a/ALIAS k/COMMAND_KEYWORD`

* `COMMAND_KEYWORD` must be a default command keyword (e.g `add` in Adding students)
* `ALIAS` must not be a default command keyword and contain exactly one word

Example:
* `alias a/ls k/list` adds an alias `ls` for the viewing all students command `list`.<br>
Now, typing `ls` into the command box will execute the `list` command.
* `alias a/a k/add` adds an alias `a` for the adding a student command `add`.<br>
Now, typing `a n/John Doe` will add a student named John Doe into FRIDAY.


### Deleting aliases: `unalias`

Deletes an existing alias, which is no longer needed, in FRIDAY.

Format: `unalias a/ALIAS`

Example:
* `unalias a/ls` will delete the alias `ls` if it exists in FRIDAY.

### Viewing aliases: `aliaslist`

Views all aliases in FRIDAY, so that you know what aliases you have created and the commands they are for.

Format: `aliaslist`

### Getting User Guide link: `guide`

If you ever need to refer to our User Guide while using FRIDAY, use this command to obtain the link to FRIDAY's User Guide.

Format: `guide`

### Getting help: `help`

Shows a summary of commands along with their command word used in FRIDAY. This allows you to have an easily accessible summary when using FRIDAY.<br>
It also includes a link to this User Guide if needed.

Format: `help`

### Exiting FRIDAY : `exit`

Exits FRIDAY. See you again soon!

Format: `exit`

### Saving the data

FRIDAY's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FRIDAY's student and alias data is saved as a JSON file at `[JAR file location]/data/friday.json`.<br>
Advanced users are welcome to update data directly by editing the data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FRIDAY will ignore all data and start with an empty file in the next run.<br>
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install FRIDAY in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FRIDAY home folder.

**Q**: I accidentally made the data file invalid and now my old data does not show in FRIDAY. How do I fix this?<br>
**A**: To retrieve the old data, revert all invalid changes in the data file **before running any commands** in FRIDAY.

**Q**: Which computers can run this software?<br>
**A**: All computers can run this software as it not limited by hardware. However on the software side Windows computers <br>
       minimally must be of version 7. All versions above Windows 7 should be compatible. All MacOS versions above Version 10.10 (Yosemite) will be compatible with FRIDAY.

**Q**: What if I am not good at typing. Is FRIDAY for me?<br>
**A**: Yes Friday is still for you. Why you may ask? The commands are still very short and even if you are a slow typist, 
       you will be saving time as compared to pen and paper or an Excel sheet. <br>
       Furthermore, FRIDAY is more aesthetically pleasing and has all the necessary features easily accessible at your fingertips.

**Q**: What are the additional features coming to FRIDAY?<br>
**A**: FRIDAY is always expanding to serve you better. In the near future you can expect to see:

1. Shared databases
2. Larger storage limits
3. Cloud compatibility
4. Easy import and export of data
5. Undoing previous actions


--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                                       | Format                                                                                                   |
|----------------------------------------------|----------------------------------------------------------------------------------------------------------|
| **Add a student**                            | `add n/NAME [t/TELEGRAM_HANDLE] [c/CONSULTATION_DATE] [m/MASTERY_CHECK_DATE] [tag/TAG]...`               |
| **Delete a student**                         | `delete INDEX`                                                                                           |
| **Edit a student's details**                 | `edit INDEX [n/NAME] [t/TELEGRAM_HANDLE] [c/CONSULTATION] [m/MASTERY_CHECK] [tag/TAG]...`                |
| **Add remarks for a student**                | `remark INDEX [r/REMARK]`                                                                                |
| **Record the grades for a student**          | `grade INDEX [ra1/RA1_SCORE] [ra2/RA2_SCORE] [pa/PRACTICAL_SCORE] [mt/MID_TERM_SCORE] [ft/FINALS_SCORE]` |
| **Find a student's details**                 | `find KEYWORDS`                                                                                          |
| **Mark a student's Mastery Check as passed** | `mark INDEX`                                                                                             |
| **Unmark a student's Mastery Check**         | `unmark INDEX`                                                                                           |
| **View all students**                        | `list`                                                                                                   |
| **Sort students**                            | `sort CRITERIA/ORDER`                                                                                    |
| **Add Alias**                                | `alias a/ALIAS k/COMMAND_KEYWORD`                                                                        |
| **Delete Alias**                             | `unalias a/ALIAS`                                                                                        |
| **View all alias**                           | `aliaslist`                                                                                              |
| **Get a link to the User Guide**             | `guide`                                                                                                  |
| **Getting Help**                             | `help`                                                                                                   |
| **Exiting FRIDAY**                           | `exit`                                                                                                   |

---------------------------------------------------------------------------------------------------------------------

## Glossary

1. **CLI:** CLI stands for command line interface. It is a system wherein the user enters textual one line inputs into an input box and the computer responds with a textual or graphical output.

2. **GUI:** GUI stands for graphical user interface. It is the opposite of CLI wherein the user interacts with icons and items on the screen to communicate with the computer. Outputs are also displayed in graphical form.

3. **Java:** Java is a programming language introduced in the 90's. It is used to create many applications that you use today. Including this one.

4. **.jar:** Specifies the file format of the file.

5. **Command:** The text that you enter in the input box is a command.

6. **Parameter:** The text that follows the first word of your input in the input box.

7. **Prefix:** A word or letter used in commands to specify the field you are adding, editing or sorting by.

8. **RA1:** Reading assessment 1 is a minor assessment in the CS1101S module that tests the content of the first half of the module.

9. **RA2:** Reading assessment 2 is a minor assessment in the CS1101S module that tests the content of the second half of the module.

10. **Midterm:** Midterm examination is a major assessment that occurs halfway through the CS1101S module.

11. **Practical:** Practical examination is a major non-paper assessment that occurs at the end of the CS1101S module.

12. **Final:** Final examination is a major paper assessment that occurs at the end of the CS1101S module.

13. **Mastery Check:** A pass/fail assessment on students to assess their understanding of the concepts taught. There are 2 Mastery checks per semester.

14. **Alias:** A nickname or an alternate name that you can set for a command.
