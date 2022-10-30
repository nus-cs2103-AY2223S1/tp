---
layout: page
title: User Guide
---

Teaching Assistant Assistant (TAA) is a **desktop app for Teaching Assistants (TA) to track student progress and tasks,
optimized for use via a Command Line Interface** (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, TAA can get your students and tasks management done
faster than traditional GUI apps.

* Table of Contents
{:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest TAA.jar from [here](https://github.com/AY2223S1-CS2103T-T13-1/tp/releases/tag/v1.3.trial).

3. Copy the file to the folder you want to use as the home folder for your TAA.

4. Double-click the file to start the app. The GUI appear in your app should be similar as the one shown below:<br>
   <img src="images/TAA.png" width="500">

5. Type the command in the command box and press Enter or click the Send button to execute. Some example commands you can try:
   * `student add` `n/John p/96123456 e/john@example.com g/T03`: Adds a student named John to the TAA.
   * `student edit` `1 g/T01`: Edits the student John to change his tutorial group from T03 to T01.
   * `student delete` `1`: Removes the student John from TAA.

6. Refer to the Features below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words surrounded in angled brackets (`< >`), e.g. `<taskName>` are the parameters to be supplied by the user.<br>
  e.g. in `tutorial add g/<tutorialGroup>`, `<tutorialGroup>` is a parameter which can be used as `tutorial add g/T03`.

* Optional parameters are indicated with `(optional)`.<br>
  e.g. `task add tn/<taskName> i/<taskDescription> d/<taskDeadline> s/<student(s)>(optional)`
  can be used as
  * `task add tn/Assignment 6 i/Recursion d/31/12/2023` or
  * `task add tn/Assignment 6 i/Recursion d/31/12/2021 s/Thomas Edison` or
  * `task add tn/Assignment 6 i/Recursion d/31/12/2021 s/Thomas Edison s/George Washington`

* Arguments with `(s)` after them can be used multiple times including zero times.<br>
  e.g. See the above example regarding `s/<student(s)>`.

* Parameters can be specified in any order.<br>
  e.g. if the command specifies `group expel g/<groupName> s/<studentName>`, both of the following commands are equivalent:
  * `group expel g/T03 s/Billy Boy`
  * `group expel s/Billy Boy g/T03`

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/80000000 p/88888888`, only `p/88888888` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help me`, `bye bye`) will be ignored.<br>
  e.g. if you enter `help me pretty p/lease`, it will be interpreted as `help me`.

</div>

### Add students: `student add`

- Command `student add n/studentName p/phoneNumber e/email g/tutorialGroup(optional) t/tags(optional)`
- Adds a student with the given phone number, email, tutorial group, and tags to the list of students

Notes:
- `<studentName>` should be alphanumeric and should not be blank
- `<phoneNumber>` should only contain numbers, and it should be at least 3 digits long
- `<email>` should contain in the format: local-part@domain
  - local-part should only contain alphanumerics and these special characters, excluding the parentheses, (+_.-)
  - local-part should not start or end with any special characters
  - domain should be at least 2 characters long
- `<tutorialGroup>` should follow the format Txx, where x is a numeric value, and it should not be blank
- `<tags>` should be alphanumeric with no white space

### Remove students: `student delete`

- Command `student delete <indices>`
- Removes the indexed students(s) from the list of students

Notes:
- Each `index` must be a positive integer which corresponds to an existing student
- You can delete multiple students at once by separating the indices with a space. e.g. `student delete 1 2 3`

### Edit students: `student edit`

- Command `student edit <index> n/<studentName>(optional) p/<studentPhone>(optional) e/<studentEmail>(optional) g/<tutorialGroup>(optional) t/<tag>(optional)`
- Edits the student by its given index with at least 1 variable specified to change.

Notes:
- `index` must be a positive integer and must correspond to an existing student.
- You may remove all of a student's tags by typing `t/` without specifying any tags after it.


### List students: `student list`

- Command `student list`
- Shows a list of all students

### Add new task: `task add`

- Command `task add tn/<taskName> i/<taskDescription> d/<taskDeadline> s/<student(s)>(optional)`
- Adds a task to the list of tasks
- The task is initially assigned to no students if no student variable is entered

Notes:
- `taskName` should only contain alphanumeric characters and spaces, and it should not be blank
- `taskDescription` should not be blank
- `taskDeadline` should be in the format of DD/MM/YYYY with its days and months within range
- `student(s)`, referenced by their name, should exist

### Remove task: `task delete`

- Command `task delete <indices>`
- Removes the indexed task(s) from the list of tasks

Notes:
- Each `index` must be a positive integer which corresponds to an existing task
- You can delete multiple tasks at once by separating the indices with a space. e.g. `task delete 1 2 3`

### Edit task: `task edit`

- Command `task edit <index> tn/<taskName>(optional) i/<taskDescription>(optional) d/<taskDeadline>(optional) s/<student(s)>(optional)`
- Edits the task by its given index with at least 1 variable specified to change.

Notes:
- `index` must be a positive integer and valid

### List tasks: `task list`

- Command `task list`
- Shows a list of tasks

### Add new tutorial group: `tutorial add`

- Command `tutorial add g/<tutorialGroup>`
- Adds the tutorial group with the name `tutorialGroup`

### Remove tutorial group: `tutorial delete`

- Command `tutorial delete g/<tutorialGroup>`
- Removes the tutorial group with the name `tutorialGroup`

### Enroll a student into a group: `student enroll`

- Command `student enroll <index> g/<groupName>`
- Enrolls the student at index i to the group named `groupName`

### Expel a student from a group: `student expel`

- Command `student expel <index> g/<groupName>`
- Removes the student at index i from the group `groupName`.

### View all students in a tutorial group: `tutorial filter`

- Command `tutorial filter g/<groupName>`
- Displays only students from the group `groupName` in the GUI.

### Reset filters and show all students `student unfilter`

- Command `student unfilter`
- Undoes the `tutorial filter` command and displays all students in the GUI.

### Mark assignment as graded or ungraded: `grade edit`

- Command `grade edit <studentIndex> <taskIndex> gr/<T or F>`
- Marks the specified student's assignment as graded (`T`) or ungraded (`F`)

### Display assignment grade status: `grade view`

- Command `grade view <studentIndex> <taskIndex>`
- Shows the grade status of the specified student's assignment

### Display user guide url: `help me`

- Command `help me`
- Shows a popup with the user guide url in it

### Exit the app: `bye bye`

- Command `bye bye`
- Exit and close the app

---

## FAQ

#### What if I forget the command format?
- A list of commands will be displayed after the launch of the program.
- You can also click on the help button to view the commands.

#### Why can't I add a student?
- Check the format of your add command. You can refer to the pop-up message or the help page for the command format.
- Check if the name of the new student belongs to another student.

#### Why can't I enroll a student to a tutorial group?
- Check whether the specified student and tutorial group exists.
- Check if you have entered the correct student name.

---

## Command summary

| Action                             | Format, Examples                                                                                                                                                                 |
|------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add student**                    | `student add n/studentName p/phoneNumber e/email g/tutorialGroup(optional) t/tags(optional)` e.g. `student add n/James Ho p/98765432 e/a@gmail.com g/T03 t/yearTwo`              |
| **Remove student(s)**              | `student delete <indices>` e.g. `student delete 1 3`                                                                                                                             |
| **Edit student**                   | `student edit <index> n/<studentName>(optional) p/<studentPhone>(optional) e/<studentEmail>(optional) g/<tutorialGroup>(optional) t/<tag>(optional)` e.g. `student edit 1 g/T05` |
| **List students**                  | `student list`                                                                                                                                                                   |
| **Add task**                       | `task add tn/<taskName> i/<taskDescription> d/<taskDeadline> s/<student(s)>(optional)` e.g. `task add tn/Grade Mission 1 i/Due Tomorrow d/10/12/2022 s/James Ho`                 |
| **Remove task(s)**                 | `task delete <indices>` e.g. `task delete 2 4`                                                                                                                                   |
| **Edit task**                      | `task edit <index> tn/<taskName>(optional) i/<taskDescription>(optional) d/<taskDeadline>(optional) s/<student(s)>(optional)` `e.g. task edit 1 d/11/12/2020`                    |
| **List tasks**                     | `task list`                                                                                                                                                                      |
| **Add tutorial group**             | `tutorial add g/<tutorialGroup>` e.g. `tutorial add g/T01`                                                                                                                       |
| **Remove tutorial group**          | `tutorial delete g/<tutorialGroup>` e.g. `tutorial delete g/T01`                                                                                                                 |
| **Enrol student**                  | `student enroll <index> g/<groupName>` e.g. `student enrol 1 g/T03`                                                                                                              |
| **Expel student**                  | `student expel <index> g/<groupName>` e.g. `student expel 1 g/T03`                                                                                                               |
| **Mark assignment as graded**      | `grade edit <studentIndex> <taskIndex> gr/T` e.g. `grade edit 1 1 gr/T`                                                                                                          |
| **Mark assignment as ungraded**    | `grade edit <studentIndex> <taskIndex> gr/F` e.g. `grade edit 2 1 gr/F`                                                                                                          |
| **View assignment grading status** | `grade view <studentIndex> <taskIndex>` e.g. `grade view 3 7`                                                                                                                    |
| **Display the user guide URL**     | `help me`                                                                                                                                                                        |
| **Exit the app**                   | `bye bye`                                                                                                                                                                        |
