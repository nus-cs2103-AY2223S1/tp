---
layout: page
title: User Guide
---

Teaching Assistant Assistant (TAA) is a **desktop app for Teaching Assistants (TA) to track student progress and tasks,
optimized for use via a Command Line Interface** (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, TAA can get your students and tasks management done
faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest TAA.jar from [here](https://github.com/AY2223S1-CS2103T-T13-1/tp/releases/tag/v1.3.trial).
3. Copy the file to the folder you want to use as the home folder for your TAA.
4. Double-click the file to start the app. The GUI appear in your app should be similar as the one shown below:
   <img src="images/TAA.png" width="500">
5. Type the command in the command box and press Enter or click the Send button to execute. Some example commands you can try:
* `student add` `n/John p/96123456 e/john@example.com g/T03`: Adds a student named John to the TAA.
* `student edit` `1 g/T01`: Edits the student John to change his tutorial group from T03 to T01.
* `student delete` `1`: Removes the student John from TAA.
6. Refer to the Features below for details of each command.
---

## Features

### Add students : 

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

### Remove students : 

- Command `student delete <index>`
- Removes the student from the list of students with the given index

Notes:
- `index` must be a positive integer and must correspond to an existing student

### Edit students : 

- Command `student edit <index> n/<studentName>(optional) p/<studentPhone>(optional) e/<studentEmail>(optional) g/<tutorialGroup>(optional) t/<tag>(optional)`
- Edits the student by its given index with at least 1 variable specified to change.

Notes:
- `index` must be a positive integer and must correspond to an existing student

### List students : 

- Command `student list`
- Abbreviate with `students`
- Shows a list of all students

### Add new task : 

- Command `task add tn/<taskName> i/<taskDescription> d/<taskDeadline> s/<student(s)>(optional)`
- Adds a task to the list of tasks
- The task is initially assigned to no students if no student variable is entered

Notes:
- `taskName` should only contain alphanumeric characters and spaces, and it should not be blank
- `taskDescription` should not be blank
- `taskDeadline` should be in the format of DD/MM/YYYY with its days and months within range
- `student(s)`, referenced by their name, should exist

### Remove task : 

- Command `task delete <index>`
- Removes the indexed task from the list of tasks

Notes:
- `index` must be a positive integer and valid

### Edit task : 

- Command `task edit <index> tn/<taskName>(optional) i/<taskDescription>(optional) d/<taskDeadline>(optional) s/<student(s)>(optional)`
- Edits the task by its given index with at least 1 variable specified to change.

Notes:
- `index` must be a positive integer and valid

### List tasks : 

- Command `task list`
- Abbreviate with `tasks`
- Shows a list of tasks

### Mark tasks : 

- Command `task mark i/<taskNumber>`
- Marks the task with the task number on the list.

### Unmark tasks : 

- Command `task unmark i/<taskNumber>`
- Unmarks the task with the task number on the list.

### Assign a task to a student : 

- Command `task assign n/<taskName> n/<studentName>`
- Assign the task `taskName` to the student `studentName`.

### Add new tutorial group : 

- Command `tutorialAdd g/<tutorialGroup>`
- Adds the tutorial group with the name `tutorialGroup`


### Remove tutorial group : 

- Command `tutorial delete g/<tutorialGroup>`
- Removes the tutorial group with the name `tutorialGroup`

### Enroll a student into a group : 

- Command `student enroll i g/<groupName>`
- Enrolls the student at index i to the group named `groupName`

### Expel a student from a group `To be implemented` : 

- Command `group expel g/<groupName> g/<studentName>`
- Removes the student `studentName` from the group `groupName`.

### View the list of students in a group : 

- Command `group roster n/<groupName>`
- Displays a list of students enrolled in `groupName`.

_Details coming soon..._

---

## FAQ

#### What if I forgot the command format?
- A list of commands will be displayed after the launch of the program.
- You can also click on the help button to view the commands.

#### Why I cannot add a student?
- Check the format of your add command. You can refer to the pop-up message or the help page for the command format.
- Check if the name of the new student belongs to another student.

#### Why I cannot enroll a student to a tutorial group? 
- Check whether the specified student and tutorial group exist.
- Check if you input the correct student name.
- 
#### What if I just want to modify one field of a student? Do I need to type out the rest fields?
- The student edit command supports any number of field changes. Omit the fields you do not wish to change.

#### How to remove the student filter?

#### 

_Details coming soon..._

---

## Command summary

| Action                            | Format, Examples                                                                                     |
|-----------------------------------|------------------------------------------------------------------------------------------------------|
| **Add student**                   | `student add n/STUDENT_NAME` e.g. `student add n/James Ho`                                           |
| **Remove student**                | `student remove n/STUDENT_NAME` e.g. `student remove n/James Ho`                                     |
| **Rename student**                | `student rename n/OLD_STUDENT_NAME n/NEW_STUDENT_NAME` e.g. `student rename n/Janes Ho n/James Ho`   |
| **List students**                 | `student list`                                                                                       |
| **Add task**                      | `task add n/TASK_NAME` e.g. `task add n/Grade Mission 1`                                             |
| **Remove task**                   | `task remove n/TASK_NAME` e.g. `task remove n/Grade Mission 1`                                       |
| **Rename task**                   | `task rename n/OLD_TASK_NAME n/NEW_TASK_NAME` e.g. `task rename n/Grade Mission 1 n/Grade Mission 2` |
| **Add deadline**                  | `task deadline n/TASK_NAME d/DEADLINE` e.g. `task deadline n/Grade Mission 2 d/2020-10-10`           |
| **List tasks**                    | `task list`                                                                                          |
| **Mark task**                     | `task mark i/TASK_NUMBER` e.g. `task mark i/1`                                                       |
| **Unmark task**                   | `task unmark i/TASK_NUMBER` e.g. `task unmark i/1`                                                   |
| **Assign task**                   | `task assign n/TASK_NAME n/STUDENT_NAME` e.g. `task assign n/Grade Mission 2 n/James Ho`             |
| **Add tutorial group**            | `group add g/GROUP_NAME` e.g. `group add g/T01`                                                      |
| **Remove tutorial group**         | `group remove g/GROUP_NAME` e.g. `group remove g/T01`                                                |
| **Enrol student**                 | `group enrol n/GROUP_NAME n/STUDENT_NAME` e.g. `group enrol n/CS2103T n/James Ho`                    |
| **Expel student**                 | `group expel n/GROUP_NAME n/STUDENT_NAME` e.g. `group expel n/CS2103T n/James Ho`                    |
| **View student roster for group** | `group roster n/GROUP_NAME` e.g. `group roster n/CS2103T`                                            |
