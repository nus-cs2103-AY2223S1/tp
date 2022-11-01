---
layout: page
title: User Guide
---

**TA-Assist** is a desktop app for Teaching Assistants (TA) to keep track of students’ particulars and allocate marks 
for attendance and assignments. It is optimized for use via a Command Line Interface (CLI) while having the benefits of 
a Graphical User Interface (GUI).

This user guide provides a brief documentation on how you can install the application and describes how each feature 
should be used. Start by looking at the [quick start](#quick-start) guide to get you started.

* Table of Contents
{:toc}


## Quick Start
1. Ensure you have **Java `11`** or above installed in your computer.
2. Download the latest `taassist.jar` from [here](https://github.com/AY2223S1-CS2103T-T12-1/tp/releases/tag/v1.3).
3. **Copy** the file to the folder you want to use as the _home folder_ for your TA-Assist.
4. **Double-click** the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the
   app contains sample data.

![sample gui](images/sampleGui.png)

{% include important.html content="

If you encounter any issues in launching and using the app, feel free refer to the [FAQ section](#frequently-asked-questions-faq)!

" %}

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

:information_source: Before diving into the features, the examples in this guide are formatted with the following conventions:
* Words in `UPPER_CASE` are the parameters to be supplied by you.
  * e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
* Items in square brackets are optional.
  * e.g `n/NAME [c/CLASS_NAME]` can be used as `n/John Doe c/CS1231S` or as `n/John Doe`.
* Items with `...` after them can be used multiple times.
  * e.g. `[c/CLASS_NAME...]` can be used as ` ` (i.e. 0 times since it is also optional), `c/CS1101S`, `c/CS2030 c/ST2334` etc.
* Parameters can be in any order.
  * e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.
  * e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
  * e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Modes
In TA-Assist, you can switch into a mode called the **focus** mode, which lets you run tasks that are specific to (module) class. Therefore,
[some commands](#features-available-in-focus-mode) can only be executed when you are in focus mode. Commands that you can run in the default (unfocused) mode can also
be run in focus mode. On the other hand, commands that are available only in focus mode cannot be executed in the default (unfocused) mode.

Let's first begin with the commands available in the default mode.

</div>

| Command    | Format                                                             |
| ---------- |--------------------------------------------------------------------|
| `help`     | `help`                                                             |
| `add`      | `add n/NAME [p/PHONE_NUMBER][e/EMAIL][a/ADDRESS][c/CLASS_NAME...]` |
| `edit`     | `edit INDEX [n/NAME][p/PHONE_NUMBER][e/EMAIL][a/ADDRESS]`          |
| `delete`   | `delete INDEX`                                                     |
| `find`     | `find KEYWORD...`                                                  |
| `list`     | `list`                                                             |
| `addc`     | `addc c/CLASS_NAME...`                                             |
| `deletec`  | `deletec c/CLASS_NAME...`                                          |
| `assign`   | `assign INDEX... c/CLASS_NAME`                                     |
| `unassign` | `unassign INDEX... c/CLASS_NAME`                                   |
| `listc`    | `listc`                                                            |
| `exit`     | `exit`                                                             |
| `focus`    | `focus c/CLASS_NAME`                                               |
| `clear`    | `clear`                                                            |

### Viewing help : `help`

{% include note.html content="

Redirects you to this User Guide page. If the attempt was unsuccessful, the following help window appears.
" %}

![help message](images/helpMessage.png)

Format: `help`

### Adding a student: `add`

{% include note.html content="

Adds a student to TA-Assist.

" %}

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS_NAME...]`
* Adds a student named `NAME` into TA-Assist.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/CS2103T`
* `add n/Betsy Crowe a/Betsy street p/62353535 c/CS1231S c/CS1101S`

### Edit a student: `edit`

{% include note.html content="

Edits an existing student in TA-Assist.

" %}

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`
* Edits student data at the specified `INDEX`.
* Only fields that are specified will be modified.

Examples:
* `edit 2 n/John Doe` will change the 2nd student’s name to **John Doe**.
* `edit 4 e/john.doe@example.com a/38 College Avenue East, 138601` will change the 4th student’s:
  * E-mail to **john.doe@example.com**
  * Address to **38 College Avenue East, 138601**

### Delete a student: `delete`

{% include note.html content="

Deletes the specified student from TA-Assist.

" %}

Format: `delete INDEX`
* Deletes the student at the specified `INDEX`.

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the student list.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.
* `focus c/CS2103T` followed by`delete 3` deletes the 3rd student in the CS2103T class.

### Locate student by name: `find`

{% include note.html content="

Finds students whose names contain any of the given keywords.

" %}

Format: `find KEYWORD...
* The search is **case-insensitive**, i.e. `Joh` will match with `john` and `JOHN`.
* The search is performed on the current displayed student list.
* To clear the current search, use the `list` command.

Examples:
* `find bert` will find students with **bert** in their name (case-insensitive), i.e. **Edbert Geraldy**, **Bert Hendrick**, **Gerard Albert**, etc.
* `find ben chris` will find students with either **ben** or a **chris** in their name, i.e. **chris ben**, **wonders ChRIs**, **bEn ten**, etc.
* `find alex david` returns `Alex Yeoh`, `David Li`<br>

  ![result for 'find alex david'](images/findAlexDavidResult.png)

{% include tip.html content="

The `find` command performs its search over the currently displayed student list and replaces the displayed list with
its search result.  If you need to revert to the original list of all students, you may use the [`list` command](#list-all-students-list).

" %}

### List all students: `list`

{% include note.html content="

Shows a list of all the students.

" %}

Format: `list`
* The list of students you will see contains all the students in TA-Assist.

{% include important.html content="

Note that `list` has different [behavior in focus mode](#list-all-students-in-the-class-list) and outside focus mode.

" %}

### Add classes: `addc`

{% include note.html content="

Adds one or more classes to TA-Assist.

" %}

Format: `addc c/CLASS_NAME...`
* Add classes with specified names. 
* The class names are **case-insensitive** (i.e. if a class with a name **CS1101S** already exists, `addc c/cs1101s` will not add a
new class `cs1101s`).

Examples:
* `addc c/CS2103T c/CS2100` adds the classes named **CS2103T** and **CS2100**.

### Delete classes: `deletec`

{% include note.html content="

Deletes one or more classes from TA-Assist.

" %}

Format: `deletec c/CLASS_NAME...`
* Deletes the classes with the specified names.
* The class names are **case-insensitive**.

Examples:
* `deletec c/CS2103T c/CS2100` deletes the classes named **CS2103T** and **CS2100**.

### Assign students to class: `assign`

{% include note.html content="

Assigns students to a class.

" %}

Format: `assign INDEX... c/CLASS_NAME`
* Assigns students specified by the given indices to an existing `CLASS_NAME` class.
* The class names are **case-insensitive**.

Example:
* `list` followed by `assign 1 3 5 6 c/CS1231S` assigns the **1st**, **3rd**, **5th** and **6th** student in the displayed student list to the **CS1231S** class.

### Unassign students from class: `unassign`

{% include note.html content="

Unassigns students from a class.

" %}

Format: `unassign INDEX... c/CLASS_NAME`
* Unassigns students specified by the given indices from an existing `CLASS_NAME` class.
* The class names are **case-insensitive**.

Example:
* `list` followed by `unassign 1 3 5 6 c/CS1231S` unassigns the **1st**, **3rd**, **5th** and **6th** student in the displayed student list from the **CS1231S** class.

### List classes: `listc`

{% include note.html content="

Lists the classes that have been created.

" %}

Format: `listc`
* Lists the classes that have been created.

### Exit the program: `exit`

{% include note.html content="

Exits the program.

" %}

Format: `exit`

### Enter focus mode: `focus`

{% include note.html content="

Enters focus mode to manage the specified class, enabling features that are only available in focus mode.

" %}

Format: `focus c/CLASS_NAME`
* Enters focus mode for the class named `CLASS_NAME`.
* The class name is **case-insensitive**.
* If successful, you will see that the GUI changes to one that is similar to the one below:

![Example Excel file](images/sampleFocusedGui.png)

Example:
* `focus c/CS1231S` will enter focus mode for the **CS1231S** class, allowing you to manage data relating to **CS1231S**.

### Clear all existing data: `clear`

{% include note.html content="

Clears all existing data in TA-Assist.

" %}

Format: `clear`
* Clears all existing data in TA-Assist.

## Features Available in Focus Mode

The following commands are only available in [**focus mode**](#modes).

| Command   | Format                                        |
|-----------|-----------------------------------------------|
| `list`    | `list`                                        |
| `adds`    | `adds s/SESSION_NAME... [d/DATE]`             |
| `deletes` | `deletes s/SESSION_NAME...`                   |
| `grade`   | `grade INDEX... s/SESSION_NAME g/GRADE_VALUE` |
| `scores`  | `scores s/SESSION_NAME`                       |
| `view`    | `view INDEX`                                  |
| `lists`   | `lists`                                       |
| `export`  | `export`                                      |
| `unfocus` | `unfocus`                                     |


### List all students in the class: `list`

{% include note.html content="

Shows a list of all students in the class.

" %}

Format: `list`
* The list of students only shows the students that were assigned to the currently focused class.

{% include important.html content="

Note that `list` in focused mode has different [behavior outside focus mode](#list-all-students-list).

" %}

### Create session: `adds`

{% include note.html content="

Creates one or more sessions in the class.

" %}

{% include tip.html content="

A session can be treated as a task a student has to complete in the class. These tasks usually have an associated
grade. For example, **Tutorial 3 Participation** of **CS2100** is a task because they contribute to the
overall grade for the module CS2100.

" %}

Format: `adds s/SESSION_NAME... [d/DATE]`
* Creates new sessions with name `SESSION_NAME` on the same `DATE`. If the `DATE` field is empty, the current date will be used.
* `DATE` field should follow the format `YYYY-MM-DD`.
* The session names are **case-insensitive** (i.e. if a session **Lab 1** already exists, `adds s/lab 1` will not create a new session **lab 1**). 

Example:
- `adds s/Lab1 s/Tutorial1 d/2022-08-11` will create sessions `Lab1` and `Tutorial1` on 11  August 2022.

### Delete sessions: `deletes`

{% include note.html content="

Deletes one or more sessions from the focused class.

" %}

Format: `deletes s/SESSION_NAME...`
* Deletes the sessions with the specified names.
* The session names are **case-insensitive**.

Examples:
* `deletes s/Lab1 s/Assignment3` deletes the session named **Lab1** and **Assignment3**.

### Grade session: `grade`

{% include note.html content="

Grades one or multiple students for the session.

" %}

Format: `grade INDEX... s/SESSION_NAME g/GRADE_VALUE`
* Grades the students specified by the given indices on the session `SESSION_NAME` with a grade of `GRADE_VALUE`.
* `GRADE_VALUE` must be a number (decimal points are allowed).
* The session name is **case-insensitive**.

Example:
* `grade 1 2 s/Lab 1 g/93` will give the students at index 1 and 2 a grade of **93** for the session **Lab 1**.

### Show students' grades for a session: `scores`

{% include note.html content="

Shows the grades of all students for a session.

" %}

Format: `scores s/SESSION_NAME`
* Shows the grades of all students for the session `SESSION_NAME`.
* The session name is **case-insensitive**.

Example:

* `scores s/tutorial 1` will show the grades of all students for the session **tutorial 1**, as shown below.
  <br/>   

  <img src="images/scoresAssignment1Result.png" width="600"/>

  In the above example,
  * **Bernice Yu** has been allocated a score of **10.0** for **tutorial 1** 
  * **tutorial 1** for **Alex Yeoh**, **Charlotte Oliveiro** and **Irfan Ibrahim** have not been graded, hence their cells have been marked red.

### View session grades of student: `view`

{% include note.html content="

Views all session grades of a student within the focused class.

" %}

Format: `view INDEX`
* Views the grade of the student at index `INDEX` for the currently focused class.

Example:
* `grade 2 s/Lab 1 g/93` then `view 2` will return `1. Lab 1: 93`, which is the grade of the student at index 2 for the session **Lab 1**.

### List all sessions: `lists`

{% include note.html content="

Lists the sessions that have been created for the focused class.

" %}

Format: `lists`
* Lists the sessions that have been created for the focused class.

### Export class data: `export`

{% include note.html content="

Exports the class data as a CSV file.

" %}

{% include tip.html content="

Comma-Separated Values (CSV) files can be opened with **Microsoft Excel**, where you can get an organised view
of all the data.

" %}

Format: `export`

- The exported class data includes:
  * Student names of the class.
  * All session names of the class.
  * The grades of the students corresponding to each session.
- An example generated csv file when opened in Microsoft Excel:
![Example Excel file](images/exampleExcelFile.png)
  - First column shows all the student names.
  - Subsequent columns show the grade for a student for a session (e.g. **David Li** scores **0** for **Tutorial 1**).

### Exit focus mode: `unfocus`

{% include note.html content="

Exits focus mode.

" %}

Format: `unfocus`
* Alternatively, you can exit focus mode by clicking on the button shown [here](#enter-focus-mode-focus).

## Frequently Asked Questions (FAQ)

**Q**: How do I install Java?

**A**: You may refer to [Oracle's JDK Installation Guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A).

**Q**: I double-clicked the JAR file but was unable to start the app. What do I do?

**A**: Open your preferred terminal and navigate to the directory the JAR file is located in. Then type in `java -jar TaAssist.jar` to run the application.

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TA-Assist home folder.
