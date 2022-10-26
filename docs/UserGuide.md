---
layout: page
title: User Guide
---
**Teacher’s Pet** is a desktop application for managing contacts of students and classes, optimised for use via a
Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast,
Teacher’s Pet can get your contact and class management tasks done faster than traditional GUI apps.

**Teacher’s Pet** allows you to manage your schedule and keep track of your students.
The app stores basic information about your students, such as `Name`, `Contact Number`, `Money Owed`, `Class Date` and more.

To get started with using our application, jump straight to the [Quick Start](#quick-start) section.
For a full list of commands and detailed instructions, head to the [Features](#features) section.

## Table of contents
* [Quick Start](#quick-start)
* [UI Overview](#ui-overview)
* [Features](#features)
    * [Viewing help: `help`](#viewing-help-help)
    * [Adding a student: `add`](#adding-a-student-add)
    * [Editing student details: `edit`](#editing-student-details-edit)
    * [Marking a student: `mark`](#marking-a-student-mark)
    * [Receiving money from a student: `pay`](#receiving-money-from-a-student-pay)
    * [Viewing all students: `list`](#viewing-all-students-list)
    * [Finding a student: `find`](#finding-a-student-find)
      * [Find by name](#find-by-name)
      * [Find by email](#)
      * [Find by address](#)
      * [Find by student's contact number](#)
      * [Find by Next of Kin's contact number](#)
      * [Find by class date](#find-by-class-date)
      * [Find by tag](#)
    * [Deleting a student: `delete`](#deleting-students-delete)
    * [Clearing all student: `clear`](#clearing-all-student-clear)
    * [Exiting the program: `exit`](#exiting-the-program-exit)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `teacherpet.jar` from https://github.com/AY2223S1-CS2103T-T09-4/tp/releases when made available.
3. Copy the file to the folder you want to use as the *home folder* for your application.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   Note how the app contains some sample data.

![UI introduction](images/UG-screenshots/UiIntro.png)


### UI Overview

![Partition](images/UG-screenshots/UiPartition.png)

Our application is divided into 5 areas to maximise productivity, the specific uses are:

- Input Command - The dialog box where all user interaction are held.
- Application’s Reply - A short answer whether the application has executed the command, or an error message if the
  application did not understand the command.
- Student's Details - A window that will display the details of the student(s).
- Statistics Window - A window that shows all the statistics of the tutor, such as the number of students and
the money collected/owed.
- Day’s Schedule List - A scroll window which shows the schedule for the day, sorted by time.

Basic Instructions:
1. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open
   the help window. Some example commands you can try:
    - `list`: Lists all students.
    - `add n/John Doe p/98765432 np/81239090 e/johnd@example.com a/John street, block 123, #01-01`: Adds a student named
      `John Doe` to the student list.
    - `delete 3`: Deletes the 3rd student shown in the current list.
    - `clear`: Deletes all students.
    - `exit`: Exits the app.
2. Refer to the Features below for details of each command.

[Back to top](#table-of-contents)

---

## Features

**Notes about the command format:**

- Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `add n/NAME`, `NAME` is a parameter
  which can be used as `add n/John Doe`.
- Items in square brackets are optional. e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/python` or as `n/John Doe`.
- Items with `…` after them can be used multiple times including zero times. e.g. `[t/TAG]…` can be used as ` ` (e.g.
  0 times), `t/python`, `t/javascript t/react` etc.
- Parameters can be in any order. e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME`
  is also acceptable.
- If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence
  of the parameter will be taken. e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will
  be ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.
- Commands that require the use of index from the Schedule list (right side) will be represented as `INDEX-s`,
  while index from the Student's Details list (left side) will be represented as `INDEX`.

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`

![Help](images/UG-screenshots/UiHelp.png)

[Back to top](#table-of-contents)

---

### Adding a student: `add`

Adds a student to the Teacher’s Pet.

1. Student’s Name:
    - Student’s Name must not be empty.
    - Student’s Name must only contain alphanumeric characters.
```yaml
Note: Multiple students may share the same name.
```

2. Student’s Contact Number:
   - Student’s contact number must not be empty. 
   - Student’s contact number must only contain numerical digits between `0` and `9`.
   - Student's contact number must begin with `6`, `8` or `9`. 
```yaml
Note: Contact number must contain exactly 8 digits. Contact number must be unique.
```

3. Next of Kin’s Contact Number:
    - Next of Kin’s contact number must not be empty.
    - Next of Kin’s contact number must only contain numerical digits between `0` and `9`.
    - Next of Kin’s contact number must begin with `6`, `8` or `9`. 
```yaml
Note: Next of Kin’s contact number must contain exactly 8 digits.
```

4. Address:
    - Address must not be empty.
    - Address may contain any kinds of character.
```yaml
Note: Address cannot be empty. It must contain at least 1 character.
```

5. Email:
    - Email must not be empty. 
    - Email should be in the format of `local@domain`, where:
      - Local address should only contain alphanumeric characters and these special characters `+_.-`.
      - Consecutive special characters are not supported.
      - The domain name must:
        1. End with a domain label at least 2 characters long.
        2. Have each domain label start and end with alphanumeric characters.
        3. Have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

4. Tags:
    - Tags are optional.
    - A student can have any number of tags (including 0).
    - Tags must only contain alphanumeric characters.
```yaml
Note: Tags must contain at least 1 alphanumeric character and cannot contain spacings.
```

Format: `add n/NAME p/CONTACT_NUMBER np/NEXT_OF_KIN_CONTACT_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`

Example:

- `add n/Ben Tan p/87201223 np/90125012 e/BenTan@gmail.com a/Avenue 712`

![Add](images/UG-screenshots/UiAdd.png)

```yaml
Note: Amount paid, Amount owed, Additional notes fields are to be updated via `edit` command.
```

[Back to top](#table-of-contents)

---

### Editing student details: `edit`

Edits an existing student in the list.

- Student’s Name
- Student's Contact Number
- Next of Kin’s Contact Number
- Address
- Email
- Class Date
- Amount Paid
- Amount Owed
- Additional Notes
- Tag

1. Student's Name, Student's Contact Number, Next of Kin’s Contact Number, Email, Address and Tag follow
the same convention as [adding a student](#adding-a-student-add).

2. Class Date:
    - Class date can be left empty.
    - Formats: `dt/` must be followed by either one of the below options.
        1. `yyyy-MM-dd 0000-2359`
        2. `Day-of-Week 0000-2359`
    - End time must be after the start time.
    - If the Day-of-Week is today, executing `edit INDEX dt/Day-of-Week 0000-2359` will set the date to today only
      if the start time of the class is later than the current time. Else, the date will be set to the specified Day-of-Week
      in the following week.
    - Examples: `2022-10-09 1100-1230`, `MON 1100-1230`, `Mon 1100-1230`
    - Invalid inputs: `2022-10-9 1100-1230`, `2022-10-09 1100-1000`

```yaml
❗Caution: If a chosen date is occupied by another student, a class conflict error will arise.
```

4. Amount Paid:
    - Amount paid can be an integer or a double.
    - Amount paid must be non-negative.

5. Amount Owed:
    - Amount owed can be an integer or a double.
    - Amount owed must be non-negative.
```yaml
Note: Amount Owed and Amount Paid can only be between $0 and $2147483647.
      They are modified independent of each other.
```

6. Additional Notes:
    - Additional notes can be left empty.
    - Additional notes can take in any types of character.
    - Use `nt/` to set the additional notes.
    - Use `nt-a/` to append the additional notes.
```yaml
Note: Using both nt/ and nt-a/ in a single command will set the content
      of Additional Notes to the content behind both nt/ and nt-a/ parameters.
```

```yaml
Important: Note **at least one** of these fields must exist in order to make the `edit` command valid.
```

Format: `edit INDEX [n/NAME] [p/CONTACT_NUMBER] [np/NEXT_OF_KIN_CONTACT_NUMBER] [e/EMAIL] [dt/CLASS_DATE] [a/ADDRESS]
[paid/AMOUNT_PAID] [owed/AMOUNT_OWED] [nt/ADDITIONAL_NOTES] [nt-a/ADDITIONAL_NOTES_APPEND] [t/TAG]…`

Examples:

- `edit 1 e/Ben2022@gmail.com`

![UiEdit](images/UG-screenshots/UiEdit.png)

- `edit 1 dt/tue 1100-1200`

[Back to top](#table-of-contents)

---

### Marking a student: `mark`

Allows the user to mark a student as present for a class.

The application will increase the student's owed amount by the rates per class.
A cross will be displayed beside the student's name indicating that the student has attended the class.
The student's next class will be set a week later at the same time, provided if there is a free time slot.

Format: `mark INDEX-s`

- Marks the person as present at the specified `INDEX-s`.
- The index refers to the index number shown in the Schedule panel (bottom right).
- The index must be a positive integer. e.g. `1, 2, 3, ...`.

Example:
- `mark 2` marks the 2nd person in the Schedule panel.

![UiMark](images/UG-screenshots/UiMark.png)

```yaml
Tip: If you want to charge the student for missing the class, you can do so by the `mark` command. This increases the amount owed but frees up that time slot for another student.
```

[Back to top](#table-of-contents)

---

### Receiving money from a student: `pay`

Allows the user to indicate that a student has paid a certain amount of money.

The application will reduce the student's owed amount by the amount paid.

Format: `pay INDEX-s AMOUNT_PAID`

- Marks the person as present at the specified `INDEX-s`.
- The index refers to the index number shown in the Schedule panel (bottom right).
- The index must be a positive integer. e.g. `1, 2, 3, ...`.
- The amount paid must be an integer and cannot be negative. e.g. `0, 1, 2, ...`.

Example:
- `pay 2 40` indicates that the 2nd person in the Schedule panel has paid $40.

![UiPay](images/UG-screenshots/UiPay.png)

```yaml
Note: The student cannot pay more than what he/she owes. There is also a maximum cap of $2147483647 for every payment.
```

[Back to top](#table-of-contents)

---

### Viewing all students: `list`

Allows the user to view students and their information which includes:

- Contact Number
- Next of Kin’s Number
- Address
- Email
- Class Date
- Amount Paid
- Amount Owed
- Additional Notes
- Tag

Format: `list`

![UiUList](images/UG-screenshots/UiList.png)

[Back to top](#table-of-contents)

---

### Finding a student: `find`

Finds an existing student in the list. You can only find by one field at a time. Fields supported in `find`:

- Name `n/`
- Email `e/`
- Address `a/`
- Student's Contact Number `p/`
- Next of Kin's Contact number `np/`
- Class Date `dt/`
- Tag `t/`

```yaml
Note: Only one field can be searched at once.
```

#### Find by Name:

Finds all students with names matching the keywords.

Format: `find n/KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g. `alex` will match `Alex`.
- The order of the keywords does not matter. e.g. `Yeoh Alex` will match `Alex Yeoh`.
- Only full words will be matched e.g. `Han` will not match `Hans`.
- Students matching at least one keyword will be returned. e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Example:

`find n/tan` returns `Tan Xiao Ming` and `John Tan`.

![UiFindName](images/UG-screenshots/UiFindName.png)

#### Find by Student's Contact Number:

Finds student with the matching phone number.

Format: `find p/PHONE_NUMBER`

- Only full numbers will be matched e.g. `8123` will not match `81234567`.

Example:

`find p/81234567` returns the student with the phone number set as `81234567`.

#### Find by Class Date:

Finds all students with classes on a particular date.

Formats:
    1. `find dt/yyyy-MM-dd`
    2. `find dt/Day-of-Week`

- Only the date is searched.

```yaml
❗ Caution: Do not include class timing.
```

Examples:

`find dt/2022-10-15` returns all students with classes on 15 October 2022.
`find dt/Mon` returns all students with classes on the coming monday.

[Back to top](#table-of-contents)

---

### Deleting students: 'delete'

Deletes the specified student(s) from the student list.

Format: `delete INDEX [MORE_INDEXES]`

- Deletes the student(s) at the specified `INDEX(ES)`.
- The index(es) refers to the index numbers shown in the Student's Details panel (bottom left section of the display).
- The index(es) must be a positive integer within the size of the displayed student list. e.g. `1, 2, 3, ...`.

Examples:
- `list` followed by `delete 1 2` deletes the 1st and 2nd person in the Student's Details panel.
- `find Betsy` followed by `delete 1` deletes the 1st person in the Student's Details panel.

```yaml
❗ Caution: Deleting a student is irreversible! Please input the correct index number(s).
```

[Back to top](#table-of-contents)

---

### Clearing all student: 'clear'

Clears all students and their details from the list.

Format: `clear`

```yaml
❗ Caution: Clearing all students is irreversible!
```

[Back to top](#table-of-contents)

---

### Exiting the program: `exit`

Exits the program.

Format: `exit`

[Back to top](#table-of-contents)

---

### Saving the data

Teacher’s Pet data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Back to top](#table-of-contents)

---

### Editing the data file

Students' data is saved as a JSON file `[JAR file location]/data/teachersPet.json`. Advanced users are welcome to edit the data file.

```yaml
❗ Caution: If your changes to the data file makes its format invalid, Teacher’s Pet will discard all data and start with an empty data file at the next run.
```

[Back to top](#table-of-contents)

---

## FAQ

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Teacher's Pet home folder.

[Back to top](#table-of-contents)

---
## Glossary

| Terms       | Definition                                                              |
|-------------|-------------------------------------------------------------------------|
| Class Date  | The 1-1 tutoring time slot of a student                                 |
| Day-of-Week | 3-letter Abbreviation; case-insensitive e.g., Mon, MON                  |
| INDEX       | The number beside the student's name inthe Student's Details panel list |

## Command Summary

| Action                       | Format, Examples                                                                                                                                                                                                                  |
|------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add a student                | add n/NAME p/CONTACT_NUMBER np/NEXT_OF_KIN_CONTACT_NUMBER a/ADDRESS e/EMAIL [t/TAG]... `e.g., add n/John Doe p/98765432 np/90123291 a/Street ABC e/johnd@example.com t/python t/beginner`                                         |
| Edit a student               | edit INDEX [n/NAME] [p/CONTACT_NUMBER] [np/NEXT_OF_KIN_CONTACT_NUMBER] [e/EMAIL] [dt/CLASS_DATE] [a/ADDRESS] [paid/AMOUNT_PAID] [owed/AMOUNT_OWED] [nt/ADDITIONAL_NOTES] [nt-a/ADDITIONAL_NOTES_APPEND] `e.g., edit 2 p/98765431` |
| Get help                     | `help`                                                                                                                                                                                                                            |
| List all students            | `list`                                                                                                                                                                                                                            |
| Find a student               | find n/NAME `e.g., find n/John Doe` or other supported fields                                                                                                                                                                     |
| Mark a student               | mark INDEX-s `e.g., mark 2`                                                                                                                                                                                                       |
| Receive money from a student | pay INDEX-s AMOUNT_PAID `e.g., pay 2 300`                                                                                                                                                                                         |
| Delete a student             | delete INDEX `e.g., delete 2`                                                                                                                                                                                                     |
| Clear all students           | `clear`                                                                                                                                                                                                                           |
| Exit the application         | `exit`                                                                                                                                                                                                                            |


[Back to top](#table-of-contents)
