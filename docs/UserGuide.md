---
layout: page
title: User Guide
---

**TA-Assist** is a desktop app for teaching assistants (TA) to keep track of students’ attendance and module results, optimized for use via a Command Line Interface (CLI) while having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

## Features

{% include note.html content="

Notes about the command format:<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
* Items in square brackets are optional.<br>
  e.g `n/NAME [c/CLASS_NAME]` can be used as `n/John Doe c/CS1231S` or as `n/John Doe`.
* ​Items with `...` after them can be used multiple times including zero times.<br>
  e.g. `[c/CLASS_NAME]...` can be used as ` ` (i.e. 0 times), `c/CS1101S`, `c/CS2030 c/ST2334` etc.
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

" %}

| Command    | Format                                   |
| ---------- | ---------------------------------------- |
| `help`     | `help`                                   |
| `add`      | `add n/NAME [p/PHONE_NUMBER][e/EMAIL] [a/ADDRESS][c/CLASS_NAME...]` |
| `edit`     | `edit INDEX [n/NAME][p/PHONE_NUMBER] [e/EMAIL][a/ADDRESS] [c/CLASS_NAME...]` |
| `delete`   | `delete INDEX`                           |
| `find`     | `find KEYWORD [MORE_KEYWORDS...]`        |
| `list`     | `list`                                   |
| `addc`     | `addc c/CLASS_NAME`                      |
| `deletec`  | `deletec c/CLASS_NAME`                   |
| `assign`   | `assign INDEX... c/CLASS_NAME`           |
| `unassign` | `unassign INDEX... c/CLASS_NAME`         |
| `listc`    | `listc`                                  |
| `exit`     | `exit`                                   |
| `focus`    | `focus c/CLASS_NAME`                     |

### Viewing help : `help`

<aside>
ℹ️ Shows a message explaining how to access the help page.

</aside>

![help message](images/helpMessage.png)

Format: `help`


### Adding a student: `add`

<aside>
ℹ️ Add a student to TA Assist.

</aside>

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS_NAME...]`

- Adds a student named `NAME` into TA Assist.

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/CS2103T`
- `add n/Betsy Crowe a/Betsy street p/62353535 c/CS1231S c/CS1101S`

### Edit a student: `edit`

<aside>
ℹ️ Edits an existing student in TA Assist.

</aside>

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS_NAME...]`

- Edits student data at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3,…
- Only fields that are specified will be modified.
- Multiple `c/CLASS_NAME` fields can be specified at once. `c/CLASS_NAME` is cumulative and **will not** replace classes currently taken by the student.

Examples:

- `edit 2 n/John Doe` will change the 2nd student’s name to **John Doe.**
- `edit 4 e/john.doe@example.com a/38 College Avenue East, 138601 c/CS2103T c/CS2109S` will change the 4th student’s:
  - E-mail to **john.doe@example.com**
  - Address to **38 College Avenue East**
  - Add **CS2103T** and **CS2109S** to their list of participating classes.

### Delete a student: `delete`

<aside>
ℹ️ Deletes the specified student from TA Assist.

</aside>

Format: `delete INDEX`

- Deletes the student at the specified `INDEX`
- The index refers to the index number shown in the displayed student list.
- The index **must be a positive integer** 1, 2, 3, …

Examples:

- `list` followed by `delete 2` deletes the 2nd student in TA Assist.
- `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.
- `class c/CS2103T` followed by `delete 3` deletes the 3rd student in the CS2103T class.

### Locate student by name: `find`

<aside>
ℹ️ Finds students whose names contain any of the given keywords.

</aside>

Format: `find KEYWORD [MORE_KEYWORDS...]`

- The search is case-insensitive, i.e. `John` will match with `john` and `JOHN`.
- Only full words are matched, i.e. the search `Joh` will not match with a student named `John Doe`.

Examples:

- `find John` will find people with a **John** in their name (case-insensitive), i.e. **John Doe**, **John Brown**, **harry jOHn**, ****etc.
- `find ben chris` will find people with either a **ben** or a **chris** in their name, i.e. **chris ben**, **ChRIs wonders**, **bEn ten**, etc.
- `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Delete a class: `deletec`

<aside>
ℹ️ Deletes a class.

</aside>

Format: `deletec c/CLASS_NAME`

- Deletes the class named `CLASS_NAME`

Examples:

- `deletec c/CS2103T` deletes the class named **CS2103T.**

### Assign students to class: `assign`

<aside>
ℹ️ Assigns students to a class.

</aside>

Format: `assign INDEX... c/CLASS_NAME`

- Assigns students at the given indices to an existing `CLASS_NAME` class

Example:

- `list` followed by `assign 1 3 5 6 c/CS1231S` assigns **1st**, **3rd**, **5th** and **6th** student in TA Assist to the **CS1231S** class.

### Unassign students from class: `unassign`

<aside>
ℹ️ Unassigns students from a class.

</aside>

Format: `unassign INDEX... c/CLASS_NAME`

- Unassigns students at the given indices from an existing `CLASS_NAME` class

Example:

- `list` followed by `unassign 1 3 5 6 c/CS1231S` unassigns **1st**, **3rd**, **5th** and **6th** student in TA Assist from the **CS1231S** class.

### List classes: `listc`

<aside>
ℹ️ List the classes that have been created.

</aside>

Format: `listc`

- List the classes that have been created.

### Exit the program: `exit`

<aside>
ℹ️ Exits the program.

</aside>

Format: `exit`

### Enter focus mode: `focus`

<aside>
ℹ️ Enter focus mode to manage a given class, enabling features that are only available under focus mode.

</aside>

Format: `focus CLASS_NAME`

- Enters focus mode for the class named `CLASS_NAME`

Example:

- `class CS1231S` will enter focus mode to the **CS1231S** class, allowing you to manage data relating to **CS1231S**.

## Features Available in Focus Mode

The following commands are only available in **focus mode.**

| Command   | Format                               |
| --------- | ------------------------------------ |
| `list`    | `list`                               |
| `session` | `session s/SESSION_NAME [d/DATE]`    |
| `grade`   | `grade INDEX v/VALUE s/SESSION_NAME` |
| `view`    | `view INDEX s/SESSION_NAME`          |
| `lists`   | `lists`                              |
| `unfocus` | `unfocus`                            |

### List all students in the class: `list`

<aside>
ℹ️ Shows a list of all students in the class.

</aside>

Format: `list`

- Note that `list` has different behavior in focus mode and outside focus mode.
- In the focus mode, only students within the class will be listed.

### Create session: `session`

<aside>
ℹ️ Creates a new session.

</aside>

Format: `session s/SESSION_NAME [d/DATE]`

- Creates a new session with name `SESSION_NAME` on `DATE`. If the `DATE` field is empty, the current date will be used.
- `DATE` field should follow the format `dd-MM-yyyy`

Example:

- `session s/Lab1 d/11-08-2022` will create a session `Lab1` on 11  August 2022.

### Grade session: `grade`

<aside>
ℹ️ Grades the student for the session.

</aside>

Format: `grade INDEX v/VALUE s/SESSION_NAME`

- Grades the student with index `INDEX` on the session `SESSION_NAME` with a grade of `VALUE`.

Example:

- `session s/Lab1 d/06-10-2022` followed by `grade 2 v/93 s/Lab1` will give the student at index 2 a grade of 93 for the session `Lab1`.

### View session grade of student: `view`

<aside>
ℹ️ View the grade for a student on a given session.

</aside>

Format: `view INDEX s/SESSION_NAME`

- View the grade of the student at index `INDEX` for the session `SESSION_NAME`.

Example:

- `session s/Lab1 d/06-10-2022` followed by `grade 2 v/93 s/Lab1` then `view 2 s/Lab1` will return 93, which is the grade of the student at index 2 for the session Lab1.

### List all sessions: `lists`

<aside>
ℹ️ List the sessions that have been created for the class.

</aside>

Format: `lists`

- List the sessions that have been created for the class.

### Exit focus mode: `unfocus`

<aside>
ℹ️ Exits focus mode on a class.

</aside>

Format: `unfocus`

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TA Assist home folder.

