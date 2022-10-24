---
layout: page
title: User Guide
---

Teaching Assistant Assistant (TAA) is a **desktop app for Teaching Assistants (TA) to track student progress and tasks,
optimized for use via a Command Line Interface** (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done
faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start

_Details coming soon..._

---

## Features

### Add students : `student add n/studentName p/phoneNumber e/email t/tags(optional)`

- Command `student add n/studentName p/phoneNumber e/email t/tags(optional)`
- Adds a student with the given phone number, email, and tags to the list of students

### Remove students : `student remove n/studentName`

- Command `student remove n/<studentName>`
- Removes the named student from the list of students

### Rename students : `student rename n/oldStudentName n/newStudentName`

- Command `student rename n/<oldStudentName> n/<newStudentName>`
- Updates the name of the student

### List students : `student list`

- Command `student list`
- Abbreviate with `students`
- Shows a list of all students

### Add new task : `task add n/newTaskName`

- Command `task add n/<taskName>`
- Adds a task to the list of tasks
- The task is initially assigned to no students

### Remove task : `task remove n/taskName`

- Command `task remove n/<taskName>`
- Removes the named task from the list of tasks

### Rename task : `task rename n/oldTaskName n/newTaskName`

- Command `task rename n/<oldTaskName> n/<newTaskName>`
- Updates the name of the task

### Add deadline to task : `task deadline n/taskName d/deadline`

- Command `task deadline n/<taskName> d/<deadline>`
- Adds a deadline to the task

### List tasks : `task list`

- Command `task list`
- Abbreviate with `tasks`
- Shows a list of tasks

### Mark tasks : `task mark i/taskNumber`

- Command `task mark i/<taskNumber>`
- Marks the task with the task number on the list.

### Unmark tasks : `task unmark i/taskNumber`

- Command `task unmark i/<taskNumber>`
- Unmarks the task with the task number on the list.

### Assign a task to a student : `task assign n/taskName n/studentName`

- Command `task assign n/<taskName> n/<studentName>`
- Assign the task `taskName` to the student `studentName`.

### Add new group : `group add n/newGroupName`

- Command `group add g/<newGroupName>`
- Adds a new group with the name `newGroupName`


### Remove group : `group remove g/groupName`

- Command `group remove g/<groupName>`
- Removes the group named `groupName`

### Enroll a student into a group : `student enroll i n/groupName `

- Command `group enroll i g/<groupName>`
- Enrolls the student at index i to the group named `groupName`

### Expel a student from a group `To be implemented` : `group expel g/groupName n/studentName`

- Command `group expel g/<groupName> g/<studentName>`
- Removes the student `studentName` from the group `groupName`.

### View the list of students in a group : `group roster g/groupName`

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
