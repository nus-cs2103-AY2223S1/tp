---
layout: page
title: **Teacher's Pet** User Guide
subtitle: Everything you need to know about **Teacher's Pet**
---
**Teacher’s Pet** is an all-in-one desktop application for private 1-to-1 tutors to manage the details of students,
payment and scheduling of classes. While optimised for use via a Command Line Interface (CLI),
Teacher’s Pet still retains the benefits of a Graphical User Interface (GUI).
Whether you can type fast or prefer to use a mouse, Teacher’s Pet caters to your needs. It minimizes the time needed for
tedious administrative work, leaving more space for you to do what is important.


**Teacher’s Pet** allows you to manage your schedule and keep track of your students.
The app stores basic information about your students, such as `Name`, `Contact Number`, `Class Date` and more.

Before you get started, you may want to know [how to navigate this User Guide](#reading-the-user-guide).

To get started with using the application, jump straight to the [Quick Start](#quick-start) section.

For a full list of commands and detailed instructions, head to the [Features](#features) section.

## Table of contents
* [Quick Start](#quick-start)
* [UI Overview](#ui-overview)
* [Reading the User Guide](#reading-the-user-guide)
    * [Command format](#notes-about-the-command-format)
    * [Callouts](#callouts)
* [Features](#features)
    * [Viewing help: `help`](#viewing-help-help)
    * [Adding a student: `add`](#adding-a-student-add)
    * [Editing student details: `edit`](#editing-student-details-edit)
    * [Marking a student: `mark`](#marking-a-student-mark)
    * [Receiving money from a student: `pay`](#receiving-money-from-a-student-pay)
    * [Viewing all students: `list`](#viewing-all-students-list)
    * [Finding a student: `find`](#finding-a-student-find)
      * [Find by Name](#find-by-name)
      * [Find by Email](#find-by-email)
      * [Find by Address](#find-by-address)
      * [Find by Student's Contact Number](#find-by-students-contact-number)
      * [Find by Next of Kin's Contact Number](#find-by-next-of-kins-contact-number)
      * [Find by Class Date](#find-by-class-date)
      * [Find by Tag](#find-by-tag)
    * [Next available class: `avail`](#next-available-class-avail)
    * [Sort displayed students: `sort`](#sort-the-displayed-students-sort)
      * [Sort by Name](#sort-by-name)
      * [Sort by Class](#sort-by-class-date)
      * [Sort by Money Owed](#sort-by-money-owed)
    * [Deleting a student: `delete`](#deleting-students-delete)
    * [Clearing all student: `clear`](#clearing-all-student-clear)
    * [Undo a command: `undo`](#undo-the-last-command-undo)
    * [Exiting the program : `exit`](#exiting-the-program-exit)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Command Summary](#command-summary)
* [Prefix Summary](#prefix-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

There are a couple of things that need to be set up first before you start using **Teacher's Pet** for the first time. This will
ensure that the application works as expected and runs smoothly on your computer.

### Step 1: Ensure you have Java `11` or above installed in your Computer
To check if you have Java `11` or above installed, follow the steps for your operating system
##### If you are using Windows:
1. Press the Start button, type *cmd* and press Enter on your keyboard.
2. Once you have Command Prompt running, type `java --version` and if you have Java installed properly, you should
  see an output like the following.
  ![UI introduction](images/UG-screenshots/Java11CheckWindows.png)
##### If you are using Mac:
1. Open up terminal by hitting Command + Space bar on your keyboard and typing *terminal* then press the return 
  button on your keyboard. 
2. Once you have terminal running, type `java --version` and if you have Java installed properly, you should see
  an output like the following.
  ![UI introduction](images/UG-screenshots/Java11CheckMac.png)
#### If you do not see an output like the one above, it means that you do not have Java `11` installed 
  Follow the instructions in the video link below for your operating system for the installation of Java `11`
  - [Windows Link](https://www.youtube.com/watch?v=1ZbHHLobt8A)
  - [Mac Link](https://www.youtube.com/watch?v=pxi3iIy4F5A)

### Step 2: Install `teacherspet.jar`
- Once you have completed step 1, download the latest `teacherspet.jar` from 
https://github.com/AY2223S1-CS2103T-T09-4/tp/releases.
- Copy the file to the folder you want to use which will become the *home folder* for your application.
- Double-click the file to start the app. The GUI as seen below should appear in a few seconds.
   In this case, **Teacher's Pet** contains some sample data.

![UI introduction](images/UG-screenshots/UiIntro.png)

---

## UI Overview

**Teacher's Pet** is divided into 5 areas to maximise productivity:

1. Input Command - The dialog box where you will input your desired commands.
2. Application’s Reply - A short answer whether **Teacher's Pet** has executed the command, or an error message if the
  **Teacher's Pet** did not understand the command.
3. Student's Details - A window that will display the details of the student(s).
4. Statistics Window - A window that shows all the statistics of the tutor, such as the number of students and
the money collected/owed.
5. Day’s Schedule List - A scroll window which shows the schedule for the day, sorted by time.

![Partition](images/UG-screenshots/UiPartition.png)

Basic Instructions:
1. Type the following command in the [Input Command](#ui-overview) section, and press Enter on your keyboard to execute it. For 
   example, typing `help` and pressing Enter will open the help window. Below are some example commands you can start with:
    - `list`: Lists all students.
    - `add n/John Doe p/98765432 np/81239090 e/johnd@example.com a/John street, block 123, #01-01`: Adds a student named
      `John Doe` to the student list.
    - `delete 3`: Deletes the 3rd student shown in the current list.
    - `clear`: Deletes all students.
    - `exit`: Exits the app.
2. Refer to the [Features](#features) section below for details of each command.

[↑ Back to top](#table-of-contents)

---

## Reading the User Guide
<div markdown="span" class="alert alert-danger">❗ **Caution:** We recommend reading the features section of our 
application from top to bottom as the illustrations added follow a sequential order.  
</div>

### Notes about the command format:

| Format                                                                                                                                                                                            | Example                                                                                                                                                                                                                                                          |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Words in `UPPER_CASE` are parameters supplied by you                                                                                                                                              | Given the following format, `add n/NAME`. In this case, `NAME` is a parameter which can be replaced by `John Doe` as `add n/John Doe`                                                                                                                            |
| Items in square brackets are optional                                                                                                                                                             | Given `n/NAME [t/TAG]`, since `t/TAG` is in square brackets, you can type either `n/John Doe t/python` or `n/John Doe` if no tag is required                                                                                                                     |
| Items with `…` after them can be used 0 or more times                                                                                                                                             | Given `[t/TAG]…`, you can choose to not type anything, `t/python` for one tag, `t/javascript t/react` for two tags and etc.                                                                                                                                      |
| Parameters with a prefix can be in any order                                                                                                                                                      | `n/NAME p/CONTACT_NUMBER` or `p/CONTACT_NUMBER n/NAME` are acceptable                                                                                                                                                                                            |
| A parameter expected once will have only it's last occurrence taken despite being specified multiple times                                                                                        | If you specify `p/12341234 p/56785678`, only `p/56785678` will be accepted by **Teacher's Pet**.                                                                                                                                                                     |
| Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored                                                                    | If the you type `help 123`, the command will be interpreted as `help`.                                                                                                                                                                                           |
| An index will be `INDEX-S` if the command requries information from the [Day's Schedule List](#ui-overview) and will be `INDEX` if it requires information from [Student's Details](#ui-overview) | Under [mark command](#marking-a-student-mark), `mark 1` will mark the 1st student in the [Day's Schedule List](#ui-overview). Under [delete command](#deleting-students-delete), `delete 1` will remove the first student from [Student's Details](#ui-overview) |

### Callouts:

The callouts icons below will be useful when you are reading this user guide.

| Icon | Definition                                                                                                                |
|------|---------------------------------------------------------------------------------------------------------------------------|
| ℹ️   | Indicates additional information that can help you to gain a better understanding. They are good to know but not crucial. |
| 💡   | Indicates tips and solutions for potential problems you may encounter.                                                    |
| ❗    | Indicates information that are important to know so as to minimise confusion.                                             |
| ⚠️   | Indicates a warning to ensure you will proceed with care.                                                                 |

[↑ Back to top](#table-of-contents)

---

## Features

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`

![Help](images/UG-screenshots/UiHelp.png)

[↑ Back to top](#table-of-contents)

---

### Adding a student: `add`

The `add` command adds a student to Teacher’s Pet.

The following are the fields accepted by the `add` command and their relevant conditions which need to be met for the
command to work as expected.

##### Student’s Name:
    - Student’s Name must not be empty.
    - Student’s Name must only contain alphanumeric characters.
    
<div markdown="span" class="alert alert-info">ℹ **Note:** Multiple students may share the same name.
</div>

##### Student’s Contact Number:
   - Student’s contact number must not be empty. 
   - Student’s contact number must only contain numerical digits between `0` and `9`.
   - Student's contact number must begin with `6`, `8` or `9`.

<div markdown="span" class="alert alert-info">ℹ **Note:** Contact number must contain exactly 8 digits. Contact number must be unique.
</div>

##### Next of Kin’s Contact Number:
    - Next of Kin’s contact number must not be empty.
    - Next of Kin’s contact number must only contain numerical digits between `0` and `9`.
    - Next of Kin’s contact number must begin with `6`, `8` or `9`.

<div markdown="span" class="alert alert-info">ℹ **Note:** Next of Kin’s contact number must contain exactly 8 digits.
</div>


##### Address:
    - Address must not be empty.
    - Address may contain any kinds of character.

<div markdown="span" class="alert alert-info">ℹ **Note:** Address cannot be empty. It must contain at least 1 character.
</div>

##### Email:
    - Email must not be empty. 
    - Email should be in the format of `local@domain`, where:
      - Local address should only contain alphanumeric characters and these special characters `+_.-`.
      - Consecutive special characters are not supported.
      - The domain name must:
        1. End with a domain label at least 2 characters long.
        2. Have each domain label start and end with alphanumeric characters.
        3. Have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

##### Tags:
    - Tags are optional.
    - A student can have any number of tags (including 0).
    - Tags must only contain alphanumeric characters.
    
<div markdown="span" class="alert alert-info">ℹ **Note:** Tags must contain at least 1 alphanumeric character, cannot contain spacings and limited to `40` characters long.
</div>

Format: `add n/NAME p/CONTACT_NUMBER np/NEXT_OF_KIN_CONTACT_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`

Example:
Type `add n/Ben Tan p/87201223 np/90125012 e/BenTan@gmail.com a/Avenue 712` in the [Input Command](#ui-overview),
a student named Ben Tan will be added to the bottom of the [Student's Details](#ui-overview). 

![Add](images/UG-screenshots/UiAdd.png)

<div markdown="span" class="alert alert-info">ℹ **Note:** Amount paid, Amount owed, Rates per Class, and Additional notes fields are to be updated via `edit` command.
</div>

[↑ Back to top](#table-of-contents)

---

### Editing student details: `edit`

The `edit` command edits an existing student under [Student's Details](#ui-overview). You can edit the following fields 
under a student.

- Student’s Name
- Student's Contact Number
- Next of Kin’s Contact Number
- Address
- Email
- Class Date
- Amount Paid
- Amount Owed
- Rates per Class
- Additional Notes
- Tag

The following are the fields accepted by the `edit` command and their relevant conditions which need to be met for the
command to work as expected.

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

<div markdown="span" class="alert alert-danger">❗ **Caution:** If a chosen date is occupied by another student, a class conflict error will arise.
</div>

3. Amount Paid:
    - Amount Paid stands for the amount that has been paid by the Student.
    - Format: `paid/` must be followed by an integer.
    - Amount paid can only be an integer.
    - Amount paid must be non-negative.

4. Amount Owed:
    - Amount Owed stands for the amount that has been owed by the Student.
    - Format: `owed/` must be followed by an integer.
    - Amount owed can only be an integer.
    - Amount owed must be non-negative.

5. Rates per Class:
    - Rates per Class stands for the amount of charge per Class for the Student.
    - Format: `rate/` must be followed by an integer.
    - Rates per Class can only be an integer.
    - Rates per Class must be non-negative.

<div markdown="span" class="alert alert-info">ℹ **Note:** Amount Owed, Amount Paid, and Rates per Class must be between $0 and $2147483647. They are modified
  independent of each other.</div>


6. Additional Notes:
    - Additional notes can be left empty.
    - Additional notes can take in any types of character.
    - Use `nt/` to set the additional notes.
    - Use `nt-a/` to append the additional notes.
    
<div markdown="span" class="alert alert-danger">❗ **Caution:** Using both nt/ and nt-a/ in a single command will reset the content of Additional Notes
to the content behind both nt/ and nt-a/ prefixes.
</div>

<div markdown="span" class="alert alert-danger">❗ **Caution:** At least one of these fields must exist in order to make the edit command valid.
</div>

Format: `edit INDEX [n/NAME] [p/CONTACT_NUMBER] [np/NEXT_OF_KIN_CONTACT_NUMBER] [e/EMAIL] [dt/CLASS_DATE] [a/ADDRESS]
[paid/AMOUNT_PAID] [owed/AMOUNT_OWED] [rate/RATES_PER_CLASS] [nt/ADDITIONAL_NOTES] [nt-a/ADDITIONAL_NOTES_APPEND] [t/TAG]…`

Examples:

- Type `edit 1 e/Ben2022@gmail.com` in the [Input Command](#ui-overview). The index 1 student of the
  [Student's Details](#ui-overview) will have his or her email updated to `Ben2022@gmail.com`.

![UiEdit](images/UG-screenshots/UiEdit.png)

- Type `edit 1 dt/2022-10-30 1100-1200` in the [Input Command](#ui-overview). The index 1 student of the
  [Student's Details](#ui-overview) will have his or her class date updated to `2022-10-30 1100-1200` 
  in this case. Notice that the [Day's Schedule List](#ui-overview) has now been updated.

![UiEdit2](images/UG-screenshots/UiEdit2.png)

[↑ Back to top](#table-of-contents)

---

### Marking a student: `mark`

The `mark` command marks an existing student under [Day's Schedule List](#ui-overview) as present for a class.

When a student has been `mark`ed, a couple of things will happen.
1. **Teacher's Pet** will increase the student's owed amount by the rates per class. 
2. A cross will be displayed beside the student's name indicating that the student has attended the class. 
3. The student's next class will be set a week later at the same time, provided there is an available time slot.

Format: `mark INDEX-s`

- Marks the student as present at the specified `INDEX-s`.
- The index refers to the index number shown in the Schedule panel (bottom right).
- The index must be a positive integer. e.g., `1, 2, 3, ...`.

Example:
- Type `mark 1` in the [Input Command](#ui-overview). The index 1 student of the 
  [Day's Schedule List](#ui-overview) will have his or her attendance marked. Notice that an X is now present between 
  the [ ] and his name is highlighted red since he has yet to pay $40.

![UiMark](images/UG-screenshots/UiMark.png)

<div markdown="span" class="alert alert-success">💡 **Tip:** If you want to charge the student for missing the class, you can do so by executing
the mark command. This increases the amount owed but frees up that time slot for another student.
</div>

[↑ Back to top](#table-of-contents)

---

### Receiving money from a student: `pay`

The `pay` command indicates that a student has paid a certain amount of money.

**Teacher's Pet** will reduce the student's owed amount by the amount paid.

Format: `pay INDEX-s AMOUNT_PAID`

- Marks the student as present at the specified `INDEX-s`.
- The index refers to the index number shown in the Schedule panel (bottom right).
- The index must be a positive integer. e.g., `1, 2, 3, ...`.
- The amount paid must be an integer and cannot be negative. e.g., `0, 1, 2, ...`.

Example:
- Type `pay 1 40` in the [Input Command](#ui-overview). The index 1 student of the
  [Day's Schedule List](#ui-overview) now has paid his money and therefore his name is no longer highlighted in red as he no longer owes $40.


![UiPay](images/UG-screenshots/UiPay.png)

<div markdown="span" class="alert alert-info">ℹ **Note:** The student cannot pay more than what he/she owes. There is also a maximum cap of
  $2147483647 for every payment.
</div>

[↑ Back to top](#table-of-contents)

---

### Viewing all students: `list`

The `list` command allows you to view your students and their information under [Student's Details](#ui-overview) which
includes the following:

- Contact Number
- Next of Kin’s Number
- Address
- Email
- Class Date
- Amount Paid
- Amount Owed
- Rates Per Class
- Additional Notes
- Tag

Format: `list`

![UiUList](images/UG-screenshots/UiList.png)

[↑ Back to top](#table-of-contents)

---

### Finding a student: `find`

The `find` command finds an existing student in the [Student's Details](#ui-overview)  based on a certain condition. 
The following are fields supported in `find` and their corresponding flags:

- Name `n/`
- Email `e/`
- Address `a/`
- Student's Contact Number `p/`
- Next of Kin's Contact number `np/`
- Class Date `dt/`
- Tag `t/`

<div markdown="span" class="alert alert-danger">❗ **Caution:** Only one field can be searched at once.
</div>

#### Find by Name:

Finds all students with names matching the keywords.

Format: `find n/KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g., `alex` will match `Alex`.
- The order of the keywords does not matter. e.g. `Yeoh Alex` will match `Alex Yeoh`.
- Only full words will be matched e.g., `Han` will not match `Hans`.
- Students matching at least one keyword will be returned. e.g., `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Example:

`find n/Yeoh` returns `Alex Yeoh`.

![UiFindName](images/UG-screenshots/UiFindName.png)

#### Find by Email:

Finds all students with a particular email.

Format: `find e/EMAIL`

- The search is case-insensitive. e.g., `ghost@woods.com` will match `ghoSt@woOds.com`.
- Only one email can be searched at each time.
- Full email must be used for corresponding student to be found e.g., `ghost` will not match `ghost@woods.com`.

<div markdown="span" class="alert alert-danger">❗ **Caution:** Do not include more than one email such as find e/jonsnow@winterfell.com ghost@woods.com.
</div>

Example:

`find e/jonsnow@winterfell.com` returns all students with email set as `jonsnow@winterfell.com`.

#### Find by Address:

Finds all students with addresses matching the keywords.

Format: `find a/KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g., `Labrador` will match `LABRADOR`.
- The order of the keywords does not matter. e.g., `Bukit Timah` will match `Timah Bukit`.
- Students matching at least one keyword will be returned. e.g., `Bukit Timah` will return `Bukit Batok`, `Timah Hill`.
- Only full words will be matched e.g., `Labra` will not match `Labrador`.

Example:

`find a/street` returns `Bukit Timah Street 3` and `Labrador Street 27`.

#### Find by Student's Contact Number:

Finds student with the matching contact number.

Format: `find p/CONTACT_NUMBER`

- Only full numbers will be matched e.g., `8123` will not match `81234567`.

Example:

`find p/81234567` returns the student with the contact number set as `81234567`.

#### Find by Next of Kin's Contact Number:

Finds all students with a particular next of kin's contact number.

Formats: `find np/NEXT_OF_KIN_CONTACT_NUMBER`

- Only one contact number can be searched at each time.

<div markdown="span" class="alert alert-danger">❗ **Caution:** Do not include more than one contact number such as find np/91232323 81231232.
</div>

Examples:

`find np/91232323` returns all students with the next of kin's contact number set as `91232323`.

[↑ Back to top](#table-of-contents)


#### Find by Class Date:

Finds all students with classes on a particular date.

Formats:
    1. `find dt/yyyy-MM-dd`
    2. `find dt/Day-of-Week`

- Only the date is searched.

<div markdown="span" class="alert alert-danger">❗ **Caution:** Do not include class timing.
</div>

Examples:

`find dt/2022-10-15` returns all students with classes on 15 October 2022.
`find dt/Mon` returns all students with classes on the coming monday.

[↑ Back to top](#table-of-contents)

#### Find by Tag:

Finds all students with a particular tag.

Formats: `find t/tagName`

- Only one tag can be searched at each time.

<div markdown="span" class="alert alert-danger">❗ **Caution:** Do not include more than one tag such as find t/python java.
</div>

Examples:

`find t/python` returns all students who have python as a tag, including students who have other tags on top of the
python tag.
`find dt/javascript` returns all students with javascript as a tag and other tags besides javascript.

[↑ Back to top](#table-of-contents)

---
### Next available class: `avail`

The `avail` command finds the next available class given a time range and duration and returns the next available class 
within the time range and with the specified class duration.

Format: `avail TIME_RANGE DURATION`

- The `TIME_RANGE` would follow a 24-hour format of 0000-2359.
- The `DURATION` is in minutes.

<div markdown="span" class="alert alert-danger">❗ **Caution:** The duration should not exceed the time range. e.g., If the time range is 1000-1100
and the duration is 70, this would be invalid.
</div>

Examples:
- Given that there is 1 student on the current day of 2022-10-27 from 0900-1000, `avail 1100-1200 60` would return
  `2022-10-27 1100-1200` since there is no student at that slot.
- Given that there are 2 students on the current day of 2022-10-27 from 0900-1000 and at 1030-1130, 
`avail 0830-1300 60` would return `2022-10-27 1130-1230` as the next slot since there is no sufficient duration
between 1000-1030 for a 60 minutes class.

[↑ Back to top](#table-of-contents)

---

### Sort the displayed students: `sort`

The `sort` command sorts the list of students in the Student's Details panel by the specified `TYPE` and `ORDER`.

Format: `sort TYPE [ORDER]`

- `TYPE` must be followed by either one of the below options:
  - `NAME`
  - `CLASS`
  - `OWED`
- `ORDER`, if present, must be followed by either one of the below options:
  - `ASC`
  - `DESC`
  - If it is left as blank, it will follow the default order based on the specified `TYPE`.
- The default order for `NAME` and `CLASS` is `ASC` while for `OWED`, the default order is `DESC`.
- The parameters `TYPE` and `ORDER` are case-insensitive.
- When two students are **in a tie** for the compared `TYPE`, they will be sorted according to their **names** in **ascending** order by default.

[↑ Back to top](#table-of-contents)

#### Sort by Name

Sorts the list of students in the Students' Details panel by `name` and given `ORDER`.

If `ORDER` is left blank, it will be `asc` by default.

Examples:

- `list` followed by `sort name` will list all the students according to their names in ascending order.
- `list` followed by `sort name asc` will list all the students according to their names in ascending order.
- `list` followed by `sort name desc` will list all the students according to their names in descending order.

[↑ Back to top](#table-of-contents)

#### Sort by Class Date

Sorts the list of students in the Students' Details panel by `CLASS` and given `ORDER`.

If `ORDER` is left blank, it will be `ASC` by default.

Examples:

- `list` followed by `sort class` will list all the students according to their upcoming classes in ascending order.
- `list` followed by `sort class asc` will list all the students according to their upcoming classes in ascending order.
- `list` followed by `sort class desc` will list all the students according to their upcoming classes in descending order.

[↑ Back to top](#table-of-contents)

#### Sort by Money Owed

Sorts the list of students in the Students' Details panel by `OWED`(Amount of Money Owed) and given `ORDER`.

If `ORDER` is left blank, it will be `DESC` by default.

Examples:

- `list` followed by `sort OWED` will list all the students according to how much money they owe in **descending** order.
- `list` followed by `sort OWED ASC` will list all the students according to how much money they owe in ascending order.
- `list` followed by `sort OWED DESC` will list all the students according to how much money they owe in descending order.

[↑ Back to top](#table-of-contents)

---

### Deleting student(s): `delete`

Deletes the specified student(s) from the student list.

Format: `delete INDEX [MORE_INDEXES]`

- Deletes the student(s) at the specified `INDEX(ES)`.
- The index(es) refers to the index numbers shown in the Student's Details panel (bottom left section of the display).
- The index(es) must be a positive integer within the size of the displayed student list. e.g., `1, 2, 3, ...`.

Examples:
- `list` followed by `delete 1 2` deletes the 1st and 2nd student in the Student's Details panel.
- `find Betsy` followed by `delete 1` deletes the 1st student in the Student's Details panel.

<div markdown="span" class="alert alert-success">💡 **Tip:** Deleting a student by mistake can be reversed by 
<a href="#undo-the-last-command-undo">undo</a> command!
</div>

[↑ Back to top](#table-of-contents)

---

### Clearing all student: `clear`

The `clear` command clears all students and their details from [Student's Details](#ui-overview).

Format: `clear`

<div markdown="span" class="alert alert-success">💡 **Tip:** Clearing all students by mistake can be reversed by 
<a href="#undo-the-last-command-undo">undo</a> command!
</div>

[↑ Back to top](#table-of-contents)

---

### Undo the last command: `undo`

The `undo` command undoes the last command executed and restores the **Teacher's Pet** to the previous state.

Format: `undo`

[↑ Back to top](#table-of-contents)

---

### Exiting the program: `exit`

The `exit` command exits the program.

Format: `exit`

[↑ Back to top](#table-of-contents)

---

### Saving the data

Teacher’s Pet data is saved in the hard disk automatically after any command which changes the data is executed. There 
is no need to save manually.

[↑ Back to top](#table-of-contents)

---

### Editing the data file

Students' data is saved as a JSON file `[JAR file location]/data/teachersPet.json`. Advanced users are welcome to edit the data file.

<div markdown="span" class="alert alert-warning">⚠️ **Warning:** Proceed with care! If your changes to the data file makes its format invalid,
Teacher’s Pet will discard all data and start with an empty data file at the next run!
</div>

[↑ Back to top](#table-of-contents)

---

## FAQ

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous **Teacher's Pet** home folder.

[↑ Back to top](#table-of-contents)

---
## Glossary

| Terms       | Definition                                                 |
|-------------|------------------------------------------------------------|
| Class Date  | The 1-1 tutoring time slot of a student                    |
| Day-of-Week | 3-letter Abbreviation; case-insensitive e.g., Mon, MON     |
| INDEX       | The index number shown in the Student's Details panel list |
| INDEX-s     | The index number shown in the Schedule panel list          |
| Prefix      | e.g., `n/`, `p/`, `np/`                                    |
| Parameter   | e.g., `NAME`, `EMAIL`, `ADDRESS`                           |

[↑ Back to top](#table-of-contents)

## Command Summary

| Action                            | Format, Examples                                                                                                                                                                                                                                         |
|-----------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Get help                          | `help`                                                                                                                                                                                                                                                   |
| Add a student                     | add n/NAME p/CONTACT_NUMBER np/NEXT_OF_KIN_CONTACT_NUMBER a/ADDRESS e/EMAIL [t/TAG]... e.g., `add n/John Doe p/98765432 np/90123291 a/Street ABC e/johnd@example.com t/python t/beginner`                                                                |
| Edit a student                    | edit INDEX [n/NAME] [p/CONTACT_NUMBER] [np/NEXT_OF_KIN_CONTACT_NUMBER] [a/ADDRESS] [e/EMAIL] [dt/CLASS_DATE] [paid/AMOUNT_PAID] [owed/AMOUNT_OWED] [rate/RATES_PER_CLASS] [nt/ADDITIONAL_NOTES] [nt-a/ADDITIONAL_NOTES_APPEND] e.g., `edit 2 p/98765431` |
| Mark a student                    | mark INDEX-s e.g., `mark 2`                                                                                                                                                                                                                              |
| Receive money from a student      | pay INDEX-s AMOUNT_PAID e.g., `pay 2 300`                                                                                                                                                                                                                |
| List all students                 | `list`                                                                                                                                                                                                                                                   |
| Find a student                    | find n/NAME e.g., `find n/John Doe` or other supported fields                                                                                                                                                                                            |
| Find available time slots         | avail TIME_RANGE DURATION e.g., `avail 1000-1200 30`                                                                                                                                                                                                     |
| Sort students in particular order | sort TYPE [ORDER] e.g., `sort NAME ASC`                                                                                                                                                                                                                  |
| Delete a student                  | delete INDEX e.g., `delete 2`                                                                                                                                                                                                                            |
| Clear all students                | `clear`                                                                                                                                                                                                                                                  |
| Undo a command                    | `undo`                                                                                                                                                                                                                                                   |
| Exit **Teacher's Pet**            | `exit`                                                                                                                                                                                                                                                   |

[↑ Back to top](#table-of-contents)

## Prefix Summary

| Prefix                                              | Meaning | Examples                                             |
|-----------------------------------------------------|:--------|------------------------------------------------------|
| Name of student                                     | n/      | `n/Alice`                                            |
| Phone number of student                             | p/      | `p/81234567`                                         |
| Next of Kin Phone number                            | np/     | `np/65432109`                                        |
| Email of student                                    | e/      | `e/alice@email.com`                                  |
| Date and time of student's class                    | dt/     | `dt/2022-10-27 1300-1400`                            |
| Address of student                                  | a/      | `a/15 Bukit Timah Road, Singapore 155203`            |
| Amount paid by student                              | paid/   | `paid/300`                                           |
| Amount owed by student                              | owed/   | `owed/200`                                           |
| Rates per Class                                     | rate/   | `rate/100`                                           |
| Additional notes of student (create new notes)      | nt/     | `nt/I have created new notes for Alice`              |
| Additional notes of student (append existing notes) | nt-a/   | `nt-a/I have added more notes to the existing notes` |
| Tags of student                                     | t/      | `t/python`                                           |

[↑ Back to top](#table-of-contents)
