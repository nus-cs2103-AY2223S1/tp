---
layout: page
title: User Guide
---

FRIDAY is a **desktop app for CS1101S TAs to organize and track their students’ progress, optimized for use via a
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type
fast, FRIDAY can get your contact management tasks done faster than traditional GUI apps.

### Table of Contents
1. Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Features

1. Add and delete students
2. Edit details of students
   1. Name
   2. Telegram handle
   3. Consultation dates
   4. Dates of Mastery Checks
   5. Grades
   6. Remarks
3. Edit grades of students
4. Find individual student details
5. View all students
6. Sort students


--------------------------------------------------------------------------------------------------------------------

## Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>

  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>

  e.g `n/NAME [t/TELEGRAM_HANDLE]` can be used as `n/John Doe t/johndoe` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>

  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/fast learner`, `tag/fast learner tag/good at recursion` etc.

* Parameters can be in any order.<br>

  e.g. if the command specifies `n/NAME t/TELEGRAM_HANDLE`, `p/TELEGRAM_HANDLE n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>

  e.g. if you specify `t/johndoe t/johndoe123`, only `t/johndoe123` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>

  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a student: `add`

Adds a student to FRIDAY, with the given name, Telegram handle, consultation date, Mastery Check date, and tags.

Format: `add n/NAME [t/TELEGRAM_HANDLE] [c/CONSULTATION_DATE] [m/MASTERY_CHECK_DATE] [tag/TAG]...`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The Telegram handle, consultation date, and Mastery Check date are optional. 
A student can have any number of tags (including 0).
</div>

### Deleting a student: `delete`

Deletes the student at the given index from FRIDAY.

Format: `delete INDEX`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The index of the student can be seen from the student list.
</div>

### Editing a student: `edit`

Edits a student's details in FRIDAY.

Format: `edit INDEX [n/NAME] [t/TELEGRAM_HANDLE] [c/CONSULTATION] [m/MASTERY_CHECK] [tag/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The index of the student can be seen from the student list.<br>
The name, Telegram handle, consultation, mastery check, and tag(s) are optional, but there should be at least one parameter.<br>
A student can have any number of tags (including 0).
</div>

### Editing a remark for a student: `remark`

Adds a remark for a specified student.

Format: `remark INDEX [r/REMARK]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The index of the student can be seen from the student list.<br>
The remark is optional. Not including the remark (i.e. `remark INDEX`) will remove any existing remark from the student.<br>
</div>

### Editing grades for a student: `grade`

Edits the grades of the assessments and examinations for a specified student.

Format: `grade INDEX [ra1/RA1_SCORE] [ra2/RA2_SCORE] [pa/PRACTICAL_SCORE] [mt/MID_TERM_SCORE] [ft/FINALS_SCORE]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The index of the student can be seen from the student list.<br>
The scores of the assessments, Reading Assessment 1 (RA1), Reading Assessment 2 (RA2), Practical Assessment (PA), Midterm Test, and Final Examination, are in percentages from 0% to 100% inclusive, with decimals allowed.<br>
The scores are optional, but there should be at least one parameter.
</div>


### Finding individual student details: `find`

View a particular student's details.

Format: `find k/Keywords`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Use student name/telegram handle/consultation/mastery check date/remark to search up a particular student.
Note: Multiple keywords can be entered.
Note: when searching for exam grade use format `find [ra1:RA1_SCORE] [ra2:RA2_SCORE]`
</div>

### Viewing all students: `list`

Lists all students in FRIDAY.

Format: `list`

### Marking a student's Mastery Check as passed: `mark`

Marks the Mastery Check of a specified student as passed.

Format: `mark INDEX`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The index of the student can be seen from the student list.<br>
</div>

### Sorting students: `sort`

Sorts all students in FRIDAY with the given criteria, in ascending or descending order.

Format: `sort CRITERIA/ORDER`

`CRITERIA` can be `n` (name), `t` (Telegram handle), `c` (Consultation), or `m` (Mastery Check).
`ORDER` can be `asc` (ascending) or `desc` (descending). 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The names and Telegram handles are sorted in alphabetical order, while the consultations and Mastery Checks are sorted
by time. 
</div>

### Getting User Guide link: `guide`

Returns a link to FRIDAY's User Guide.

Format: `guide`

### Getting help: `help`

Shows a summary of commands along with their command word. Also includes a link to FRIDAY's user guide.

Format: `help`

--------------------------------------------------------------------------------------------------------------------

## FAQ

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                                       | Format                                                                                                   |
|----------------------------------------------|----------------------------------------------------------------------------------------------------------|
| **Add a student**                            | `add n/NAME [t/TELEGRAM_HANDLE] [c/CONSULTATION_DATE] [m/MASTERY_CHECK_DATE] [tag/TAG]...`               |
| **Delete a student**                         | `delete i/INDEX`                                                                                         |
| **Edit a student's details**                 | `edit INDEX [n/NAME] [t/TELEGRAM_HANDLE] [c/CONSULTATION] [m/MASTERY_CHECK] [tag/TAG]...`                |
| **Add remarks for a student**                | `remark INDEX [r/REMARK]`                                                                                |
| **Edit the grades for a student**            | `grade INDEX [ra1/RA1_SCORE] [ra2/RA2_SCORE] [pa/PRACTICAL_SCORE] [mt/MID_TERM_SCORE] [ft/FINALS_SCORE]` |
| **Find a student's details**                 | `find k/keyword`                                                                                         |
| **Mark a student's Mastery Check as passed** | `mark INDEX`                                                                                             |
| **View all students**                        | `list`                                                                                                   |
| **Sort students**                            | `sort CRITERIA/ORDER`                                                                                    |
| **Get a link to the User Guide**             | `guide`                                                                                                  |
| **Getting Help**                             | `help`                                                                                                   |
