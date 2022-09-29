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
3. View all students
4. View individual student details

--------------------------------------------------------------------------------------------------------------------

## Commands

[comment]: <> (<div markdown="block" class="alert alert-info">)

[comment]: <> (**:information_source: Notes about the command format:**<br>)

[comment]: <> (* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>)

[comment]: <> (  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.)

[comment]: <> (* Items in square brackets are optional.<br>)

[comment]: <> (  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.)

[comment]: <> (* Items with `…`​ after them can be used multiple times including zero times.<br>)

[comment]: <> (  e.g. `[t/TAG]…​` can be used as ` ` &#40;i.e. 0 times&#41;, `t/friend`, `t/friend t/family` etc.)

[comment]: <> (* Parameters can be in any order.<br>)

[comment]: <> (  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.)

[comment]: <> (* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>)

[comment]: <> (  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.)

[comment]: <> (* Extraneous parameters for commands that do not take in parameters &#40;such as `help`, `list`, `exit` and `clear`&#41; will be ignored.<br>)

[comment]: <> (  e.g. if the command specifies `help 123`, it will be interpreted as `help`.)

[comment]: <> (</div>)

### Adding a student: `add`

Adds a student to FRIDAY.

Format: `add n/NAME [t/TELEGRAM_HANDLE] [s/STUDENT_NUMBER]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The Telegram handle and student number are optional
</div>

### View induvidual student details: `view`

View a particular students details.

Format: `view s/STUDENT_NUMBER`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Use student number to view student due to name similarity.

### Deleting a student: `delete`

Deletes a student from FRIDAY.

Format: `delete n/NAME [t/TELEGRAM_HANDLE] [s/STUDENT_NUMBER]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The Telegram handle and student number are optional

</div>

### Editing a student: `edit`

Edits a student's details in FRIDAY.

Format: `edit i/INDEX [t/TELEGRAM_HANDLE] [s/STUDENT_NUMBER] [m/MASTERY_CHECK] [c/CONSULTATION] [g/GRADE] [r/REMARK]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The Telegram handle, student number, mastery check, consultation, grade, and remark are optional
</div>

### Viewing all students: `list`

Lists all students in FRIDAY

Format: `list`

--------------------------------------------------------------------------------------------------------------------

## FAQ

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                       | Format                                                                                                        |
|------------------------------|---------------------------------------------------------------------------------------------------------------|
| **Add a student**            | `add n/NAME [t/TELEGRAM_HANDLE] [s/STUDENT_NUMBER]`                                                           |
| **Delete a student**         | `delete n/NAME`                                                                                               |
| **Edit a student's details** | `edit i/INDEX [t/TELEGRAM_HANDLE] [s/STUDENT_NUMBER] [m/MASTERY_CHECK] [c/CONSULTATION] [g/GRADE] [r/REMARK]` |
| **View all students**        | `list`                                                                                                        |
| **View a student's details** | `view s/STUDENT_NUMBER`                                                                                       |
