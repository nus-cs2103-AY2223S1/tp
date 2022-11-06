---
layout: page
title: User Guide
---
<p align="center">

<img src="images/titile_big_logo.png"/>

</p>

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction
myStudent is **an easy-to-use, portable and aesthetically-pleasing desktop application for managing students of a tuition center**, optimized for use via a Command Line Interface 
(CLI) while still having the benefits of a Graphical User Interface (GUI). If you're able to type fast, myStudent can get your
work done faster than a traditional GUI apps.

---
## About
This user guide provides a detailed documentation of how to install and use myStudent. It provides explanations of how 
to use and what it does for all the features available in myStudent. If you don't have any technical knowledge, don't worry as it is not needed to be able to use this guide.

<div markdown="block" class="alert alert-info">

:information_source: Notes about the command format in this user guide:

* Prefixes are user inputs that consist of a character followed by a slash  
  e.g. `n/` or `p/`

* Fields are 


* `< >` - represent fields that are supplied by the user.  
  e.g. `add n/<name>, where <name>` is just the name of the field and users can input `add n/John Doe`. <br>


* `[ ]` - represent fields that are optional.  
        e.g `n/<name> [#/<tag>]`, users can input `add n/John Doe` or `n/John Doe #/male`.


* Users can input fields of prefixes in any order.
  e.g. `nok <index> n/<name> p/<phone>`, users can either input `nok 2 n/John Doe p/91234567` or `nok 2 n/John Doe p/91234567`.  
  However, `nok n/John Doe p/91234567 2` where the `<index>` is at the end of the input, is not a valid command.



* Extra input from the user for commands that do not take in fields will be ignored.  
  e.g. the `clear` command does not have any other fields, thus typing `clear asdfghjkl` into the command box will have the same result as if you had entered `clear` instead.


</div>

Below is a quick guide on how to get started.

---

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.


2. Download the latest `myStudent.jar` from [here](https://github.com/AY2223S1-CS2103T-F12-4/tp/releases).


3. Copy the file to the folder you want to use as the home folder for the application.

    <div markdown="block" class="alert alert-info">
    
    :information_source: **Home Folder**  
    Home folder refers to the particular folder that will be containing the `myStudent.jar` file and its relevant files.
    Since myStudent will need to load and save files, it is recommended that you to put `myStudent.jar` in a new folder 
    to ensure a smooth experience when using myStudent.
    
    </div>

4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. 

<img src="images/StartingUI.png"/>  

<div markdown="block" class="alert alert-info">

:pencil2: **Configuring the app**  
You can change the default configurations of the app in the `preference.json` file which is located in the home folder.  
A `preference.json` file will be created when you first run the application, if it doesn't exist initially.  You can then edit the file to customize mystudent to your needs.  

**What you can change:**
* The size of the window such as its height and width (in pixels).


* At which part of the screen the app will be displayed.


* The default theme.


* Where data will be loaded from and stored into.

| Settings 	                            | Default Value 	                       |
|---------------------------------------|---------------------------------------|
| Window Width 	                        | 670px 	                               |
| Window Height 	                       | 950px 	                               |
| Window Coordinates 	                  | null 	                                |
| Theme 	                               | Light Theme 	                         |
| Student Address Book Location 	       | data\\\studentaddressbook.json 	      |
| Tutor Address Book Location 	         | data\\\tutoraddressbook.json 	        |
| Tuition Class Address Book Location 	 | data\\\tuitionclassaddressbook.json 	 |

</div>

---

## Layout

<p align="center">

<img src="images/Ui_annotated.png"/>

</p>

### 1. Command Box

This is where you key in commands. After typing the commands, simply press <kbd>enter</kbd> on your keyboard to execute them. 

### 2. Feedback Box

This is where the feedback information is displayed after you execute a command. For example, if you keyed in an invalid command, the feedback “Unknown command” would be shown in the box for your reference.

<p align="center">

<img src="images/unknownCommand.png"/>

</p>

### 3. List Display Panel

This is where the current list of entities is shown. Only one of the three entities, student, tutor, or class will be shown at one time.

In the student and tutor lists, clicking on the individual student or tutor cards to show their description in the description panel.

<p align="left">

<img src="images/listDisplayPanel.png"/>
*Note that the card for the person being displayed will be highlighted as well.

</p>

### 4. List Tabs

There are three list tabs: Student, Tutor and Class.  
There will be an indication for which list is currently being displayed.


<p align="center">

<img src="images/list_tabs_2.png"/>

</p>


The above shows that the student list is the one that is currently being displayed.

### 5. Description Panel

<p align="center">

<img src="images/personDescription.png"/>

</p>

This is an example of a description being displayed in the description panel. When there is no person being display, the line “No Person Displayed” would be shown.


### 6. Toolbar 

<p align="center">

<img src="images/toolsBar.png"/>

</p>

#### File
The `File`  menu contains `Export` and `Exit`.

* `Export` allows you to export your data into a `.csv` file.  
For more information regarding `Export`, you can click [here](#exporting-address-books-to-csv).


* `Exit` allows the software to exit after three seconds of pause.

#### Help
`Help` allows you to view the help information regarding our software.

#### Theme
 `Theme` allows you to change the color scheme of the software.  
 There are four themes provided: `Light Theme`, `Dark Theme`,  `Green Theme` and `Pink Theme`.

---

## Features

### Viewing help: `help`

Shows help information that will be useful to you.

Format: `help [<command>]`

* To view all the commands, type `help` and press <kbd>Enter</kbd>.


* To view information regarding a specific command, type `help` followed by said command and press <kbd>Enter</kbd>.

Examples:
* `help add`


* `help edit`

<p align="center">

<img src="images/helpCommand.png"/>

</p>

### Exiting the software: `exit`

Plays a neat animation before exiting myStudent.

Format: `exit`

## Adding and Removing Entries

### Adding a person: `add`

Adds a person to the myStudent database.

Formats:

`add student n/<name> p/<phone> e/<email> a/<address> s/<school> l/<level> [#/<tag>]…`

`add tutor n/<name> p/<phone> e/<email> a/<address> q/<qualification> i/<institution> [#/<tag>]…`


* All fields of a person being added must be present, except for the optional `<tag>` field.


* If a specific field is repeated, the last occurrence in the input is taken. The `<tag>` field is an exception as multiple tags are allowed.


* Generally,
  * `<name>` field should only contain alphanumeric characters and spaces, and should not be left blank.
  * `<phone>` field should only contain numbers, and it should be between 7 and 15 digits long.
  * `<email>` field should be of the format local-part@domain and adhere to the following constraints:
    1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
    2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.
       The domain name must:
       - end with a domain label at least 2 characters long
       - have each domain label start and end with alphanumeric characters
       - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
  * `<address>` field can take any values but should not be left blank.
  * `<tag>` fields should only contain alphanumeric characters. No spaces are allowed.
  <br><br>
  * For students,
    * `<school>` field should only contain alphanumeric characters and spaces, and should not be left blank.
    * `<level>` field should be one of the valid academic levels.
      The valid academic levels are:
        1. Primary 1 to 6
        2. Secondary 1 to 4
    
      (Abbreviations are also allowed, e.g., "P1" or "Sec 3".)
  <br><br>
  * For tutors,
    * `<quallification>` field should only contain alphanumeric characters, commas and spaces, and should not be left blank.
    * `<institution>` field should only contain alphanumeric characters and spaces, and should not be left blank.
  
Examples:
* `add student n/John Doe p/98765432 e/johndoe@example.com a/John street, block 123, #01-01 s/Example Primary School l/Primary 3 #/badBoy`


* `add tutor n/Betsy Crowe p/1234567 e/betsycrowe@example.com a/Newgate st, block 123, #01-01 q/MSc, Master of Science i/National University of Singapore #/mostLiked`

<p align="center">

<img src="images/addPersonCommand_before.png"/>

</p>

<p align="center">

<img src="images/addPersonCommand_after.png"/>

</p>

This adds the student into the database. Note that the Person Description updates to the new student added.

### Adding a class: `add`

Adds a class to the myStudent database.

Format: `add class n/<name> s/<subject> l/<level> d/<day> t/<time> [#/<tag>]…`

* All fields must be present, except for the optional `<tag>` field.


* If a specific field is repeated, the last occurrence in the input is taken. The `<tag>` field is an exception as multiple tags are allowed.


* Generally,
  * `<name>` field should only contain alphanumeric characters and spaces, and should not be left blank. Also, there should not be an existing class with the same name.
  * `<subject>` field should only contain alphabetical characters, and should be one of the valid subjects spelt out in full.
    The valid subjects are:
    1. English
    2. Mathematics
    3. Physics
    4. Chemistry
    5. Biology
    6. Elementary Mathematics
    7. Additional Mathematics

    (Abbreviations are also allowed, e.g., "Eng" or "Amath".)
  * `<level>` field should be one of the valid academic levels.
    The valid academic levels are:
      1. Primary 1 to 6
      2. Secondary 1 to 4

    (Abbreviations are also allowed, e.g., "P1" or "Sec 3".)
  * `<day>` field should be a valid day of the week. (Abbreviations are also allowed, e.g., "Mon" or "Thurs".)
  * `<time>` field should be separated by a dash, a space or "to", and adhere to the following constraints:
    1. Timings must be in either 12-hour or 24-hour formats. When using the 12-hour format, AM/PM must be specified while minutes can be omitted. For both, the colon and initial zero may be omitted.
    2. Start and end timings specified must respect chronology. The end time cannot occur before the start time.
       Note that all timings are considered to be on the same day (**including** midnight, i.e., 12am or 00:00, if specified as the end time, symbolises the end of the same day).
       
       Some valid examples are:
        - 12pm - 3pm
        - 1:00pm 2:00pm
        - 2200 to 2400
        - 23:00 - 00:00
  * `<tag>` fields should only contain alphanumeric characters. No spaces are allowed.


Examples:
* `add class n/S1ENGT10 s/Math l/Secondary 1 d/Thursday t/10am to 12pm`


* `add class n/P2MATHF12 s/Math l/Primary 2 d/Friday t/12:00 - 14:00`

<p align="center">

<img src="images/addClassCommand_before.png"/>

</p>

<p align="center">

<img src="images/addClassCommand_after.png"/>

</p>

This adds the class into the database. Note that the Person Description Panel is not updated.

### Deleting an entry: `delete`

Deletes an entry from the myStudent database.

Format: `delete <index>`

* The index refers to the index number shown in the displayed list. For example, when displaying a student list, the command `delete 2` will delete the 2nd student on that list from the entire database.


* The index must be a positive integer 1, 2, 3, …

Examples:
* `delete 2`

<p align="center">

<img src="images/deleteCommand_before.png"/>

</p>

<p align="center">

<img src="images/deleteCommand_after.png"/>

</p>

This deletes the person from the database. Note that if the deleted person is displayed in the Person Description Panel, the panel will update to display the previous person.

## Manipulation of data

### Editing an entity: `edit`

Edits an entity in the myStudent database. 

Formats:

To edit a student, <br>
`edit <index> [n/<name>] [p/<phone>] [e/<email>] [a/<address>] [s/<school>] [l/<level>] [#/<tag>]…`

To edit a tutor, <br>
`edit <index> [n/<name>] [p/<phone>] [e/<email>] [a/<address>] [q/<qualification>] [i/<institution>] [#/<tag>]…`

To edit a class, <br>
`edit <index> [n/<name>] [s/<subject>] [l/<level>] [d/<day>] [t/<time>] [#/<tag>]…`

* The index refers to the index number shown in the displayed list.


* The index must be a positive integer 1, 2, 3, …


* All fields are optional except for the `<index>` field; however, at least one optional field must be present at all times. Fields present will overwrite the existing values.


* Fields must be valid in correspondence to the entity list being displayed currently. For example, when a student list is displayed, specifying a qualification when executing the edit command is disallowed.


* If a specific optional field is repeated, the last occurrence in the input is taken. The `<tag>` field is an exception as multiple tags are allowed.


* The constraints of each optional field must be followed. They are:
  * `<name>` field should only contain alphanumeric characters and spaces, and should not be left blank.
  * `<phone>` field should only contain numbers, and it should be between 7 and 15 digits long.
  * `<email>` field should be of the format local-part@domain and adhere to the following constraints:
      1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
      2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.
         The domain name must:
          - end with a domain label at least 2 characters long
          - have each domain label start and end with alphanumeric characters
          - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
  * `<address>` field can take any values but should not be left blank.
  * `<tag>` fields should only contain alphanumeric characters. No spaces are allowed.
  * `<school>` field should only contain alphanumeric characters and spaces, and should not be left blank.
  * `<level>` field should be one of the valid academic levels.
    The valid academic levels are:
      1. Primary 1 to 6
      2. Secondary 1 to 4

    (Abbreviations are also allowed, e.g., "P1" or "Sec 3".)
  * `<quallification>` field should only contain alphanumeric characters, commas and spaces, and should not be left blank.
  * `<institution>` field should only contain alphanumeric characters and spaces, and should not be left blank.
  * `<subject>` field should only contain alphabetical characters, and should be one of the valid subjects spelt out in full.
    The valid subjects are:
      1. English
      2. Mathematics
      3. Physics
      4. Chemistry
      5. Biology
      6. Elementary Mathematics
      7. Additional Mathematics

    (Abbreviations are also allowed, e.g., "Eng" or "Amath".)
  * `<day>` field should be a valid day of the week. (Abbreviations are also allowed, e.g., "Mon" or "Thurs".)
  * `<time>` field should be separated by a dash, a space or "to", and adhere to the following constraints:
      1. Timings must be in either 12-hour or 24-hour formats. When using the 12-hour format, AM/PM must be specified while minutes can be omitted. For both, the colon and initial zero may be omitted.
      2. Start and end timings specified must respect chronology. The end time cannot occur before the start time.
         Note that all timings are considered to be on the same day (**including** midnight, i.e., 12am or 00:00, if specified as the end time, symbolises the end of the same day).
         
         Some valid examples are:
          - 12pm - 3pm
          - 1:00pm 2:00pm
          - 2200 to 2400
          - 23:00 - 00:00
  * `<tag>` fields should only contain alphanumeric characters. No spaces are allowed.

Examples:
* `edit 2 l/Primary 5 d/Monday`


* `edit 1 n/Tom Doe`

<p align="center">

<img src="images/editCommand_before.png"/>

</p>

<p align="center">

<img src="images/editCommand_after.png"/>

</p>

This edits the specified person. Note that the Person Description Panel and the List are updated.

### Assign class to a person: `assign`

Assign an existing tuition class to a specified student/tutor.

Format: `assign <index> n/<name>`

* `assign` command only works when the displayed list is a student or tutor list.


* The index refers to the index number shown in the displayed list.


* The index must be a positive integer 1, 2, 3, …


* `<name>` field refers to name of the class being assigned and should only contain alphanumeric characters and spaces, and should not be left blank. The specified tuition class (case-sensitive) must already exist in the tuition class list.


* The tuition class to be assigned to the specified student/tutor must not have been assigned beforehand.


Examples:
* `assign 5 n/p1math`

<p align="center">

<img src="images/assignCommand_before.png"/>

</p>

<p align="center">

<img src="images/assignCommand_after.png"/>

</p>

### Unassign class from a person: `unassign`

Unassign an existing tuition class from a specified student/tutor.

Format: `unassign <index> n/<name>`

* `unassign` command only works when the displayed list is a student or tutor list.


* The index refers to the index number shown in the displayed list.


* The index must be a positive integer 1, 2, 3, …


* `<name>` field refers to name of the class being assigned and should only contain alphanumeric characters and spaces, and should not be left blank. The specified tuition class (case-sensitive) must already exist in the tuition class list.


* The tuition class to be unassigned from the specified student/tutor must have been assigned to the
  student/tutor beforehand.

Examples:
* `unassign 5 n/p1math`

<p align="center">

<img src="images/unassignCommand_before.png"/>

</p>


<p align="center">

<img src="images/unassignCommand_after.png"/>

</p>

### Add next of kin to a student: `nok`

Adds the next of kin to an existing student. If there is an existing next of kin, it will be overwritten.

Formats:

`nok <index>`

`nok <index> n/<name> p/<phone> e/<email> a/<address> r/<relationship> [#/tag]…`

* The current displayed list when executing this command must be a list of students.


* The index refers to the index number shown in the displayed list.


* The index must be a positive integer 1, 2, 3, …


* When adding a next of kin, all fields must be present, except for the optional `<tag>` field.


* If a specific field is repeated, the last occurrence in the input is taken. The `<tag>` field is an exception as multiple tags are allowed.


* If no fields are present after the required `index` field, the next of kin from the student of that index will be removed.


* The constraints of each field must be followed. They are:
    * `<name>` field should only contain alphanumeric characters and spaces, and should not be left blank.
    * `<phone>` field should only contain numbers, and it should be between 7 and 15 digits long.
    * `<email>` field should be of the format local-part@domain and adhere to the following constraints:
        1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
        2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.
           The domain name must:
            - end with a domain label at least 2 characters long
            - have each domain label start and end with alphanumeric characters
            - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
    * `<address>` field can take any values but should not be left blank.
    * `<relationship>` should only contain letters, and should be spelt out in full. The valid relationships are:
        1. Father
        2. Mother
        3. Brother
        4. Sister
        5. Guardian
    * `<tag>` fields should only contain alphanumeric characters. No spaces are allowed.

Examples:

* `nok 5 n/Eddy Doe p/86758594 e/eddydoe@example.com a/John street, block 123, #01-01 r/Father`
  This adds a next of kin to John Doe.

<p align="center">

<img src="images/addNok_before.png"/>

</p>

<p align="center">

<img src="images/addNok_after.png"/>

</p>

* `nok 5`
  This deletes the next of kin of John Doe.

<p align="center">

<img src="images/deleteNok_before.png"/>

</p>

<p align="center">

<img src="images/deleteNok_after.png"/>

</p>

## Presentation and Organisation of data

### Showing the details of a person: `show`

In the student and tutor list, the details of the persons are not displayed. You could access the person’s details by executing the show command.

Format: `show <index>`

* In the student list, `show 1` shows the description of the first student in the list.


* In the tutor list, `show 1` shows the description of the first tutor in the list.


* In the class list, `show 1` is invalid as `show` command can only be executed for the student and tutor list.

Examples:
* `show 3` in the student list

<p align="center">

<img src="images/showCommand_before.png"/>

</p>

<p align="center">

<img src="images/showCommand_after.png"/>

</p>

* `show 1` in the class list

<p align="center">

<img src="images/showClassCommand_before.png"/>

</p>

<p align="center">

<img src="images/showClassCommand_after.png"/>

</p>

Note that `show` command is invalid for the class list.

### Listing all entities : `list`

Shows a list of the specified entity type in the myStudent database.

Format: `list <entity>`
* The valid entity types are:
  1. student
  2. tutor
  3. class


* The lists of each entity type can be displayed by clicking any of the tabs above the current displayed list.

Examples:
* `list student`


* `list class`

<p align="center">

<img src="images/listCommand_before.png"/>

</p>

<p align="center">

<img src="images/listCommand_after.png"/>

</p>

This changes the current displayed list to the tutor list.

### Clears list: `clear`

Clears either the student, the tutor or the class list.

Format: `clear`

* Please note the list you are clearing as this action is **irreversible**. This can be identified by the tabs above the displayed list.

<p align="center">

<img src="images/clearCommand_before.png"/>

</p>

<p align="center">

<img src="images/clearCommand_after.png"/>

</p>

Note that the clear command clears the current displayed list only, which in this case is the tutor list. The student list and the class list will remain unchanged.

### Sort list: `sort`

Sorts the current list chronologically, alphabetically, or in reverse order.

Format: `sort <order>`

* `<order>` field should be a valid sort order. The valid orders are as follows:
  * **default**: Sorts the list in order of entries updated from oldest to newest. Editing an entry is considered as updating it.
  * **alpha**: Sorts the list alphabetically with reference to the name.
  * **reverse**: Sorts the list in reverse order.

Examples:
* `sort alpha`

<p align="center">

<img src="images/sortCommand_before.png"/>

</p>

<p align="center">

<img src="images/sortCommand_after.png"/>

</p>

### Searching by multiple fields: `find`

Finds entities from the current list based on multiple fields such that the fields of the entity specified contains the respective keywords.

Format: `find PREFIX/KEYWORD [MORE PREFIX/KEYWORD]...`

**Find students**<br>
Format: `find [n/<name>] [p/<phone>] [e/<email>] [a/<address>] [s/<school>] [l/<level>] [#/<tag>]`

**Find tutors**<br>
Format: `find [n/<name>] [p/<phone>] [e/<email>] [a/<address>] [q/<qualification>] [i/<institution>] [#/<tag>]`

**Find classes**<br>
Format: `find [n/<name>] [s/<subject>] [l/<level>] [d/day] [t/time] [#/<tag>]`

* All fields are optional, but at least one pair of `PREFIX/KEYWORD` must be specified.


* The input `PREFIX` is case-sensitive and must be in lowercase. e.g. `n/hans e/notgmail.com`


* The input `KEYWORD` is case-insensitive. e.g. `n/hans` will match a student named “Hans”


* Partial `KEYWORDS` will be matched e.g. `n/Ha` will match a student named “Hans Jones”


* The order of the `PREFIX/KEYWORD` pair does not matter. e.g. `n/Alice p/12345678` vs `p/12345678 n/Alice`


* `KEYWORDS` must not contain the `/` character.


* If there are repeated `PREFIXES`, only the latest one will be taken.


* The `<time>` field should be searched in the `HH:MM-HH:MM` format. e.g. `find t/18:00` or `find t/09:00-11:00`


* When searching the `<level>` field, a space must be included between the level and number if the number is to be specified. e.g. `find l/secondary 1` instead of `find l/secondary1`

Examples:
* `find n/john` 

<p align="center">

<img src="images/findStudent_before.png"/>

</p>

<p align="center">

<img src="images/findStudent_after.png"/>

</p>

Note that the current list is the student list , so `find n/john` returns the students, `John Doe` and `Johnny Tay` in the student list.

* `find i/NUS q/Bachelor of Computing`

<p align="center">

<img src="images/findTutor_before.png"/>

</p>

<p align="center">

<img src="images/findTutor_after.png"/>

</p>

Note that the current list is the tutor lsit, so `find i/NUS q/Bachelor of Computing` returns the tutors graduated from NUS with a Bachelor of Computing qualification.

* `find d/monday #/hard`

<p align="center">

<img src="images/findClass_before.png"/>

</p>

<p align="center">

<img src="images/findClass_after.png"/>

</p>

Note that the current list is the class list, so `find d/monday #/tag` returns all classes conducted on Monday and have the tag “hard”.

## Advanced

### Exporting address books to csv

Export Students, Tutors, and TuitionClasses address books into their own .csv files to be used in other programs.  
They are named `students.csv`, `tutors.csv`, and `tuitionClasses.csv` respectively.

<p align="center">

<img src="images/exportButton.png"/>

</p>

Format: Click on the "*File*" tab located at the top-left corner of `myStudent` and click on "*Export*" in the dropdown menu, as shown in the image above.

* The csv files will be saved in the same location as the .json files as specified in `preferences.json`.


* The default location is in a folder named `data` in the [home folder](#quick-start) where you downloaded the `myStudent.jar` file.


* Exporting will replace any .csv files that were exported previously, if they exist.

<div markdown="block" class="alert alert-warning">

:warning: **Warning**  
The export function will **NOT** work if any of the .csv files in the save folder is opened during the exporting.  
Please close any .csv files that are currently open before clicking on the "*Export*" button.

</div>


---

### FAQs
**Q:** Does myStudent need an Internet connection?  
**A:** No, myStudent is an offline software that does not need Internet connection to use.

**Q:** Can I move myStudent from one computer to another without loss of data?  
**A:** Yes, by copying the home folder containing myStudent.jar to another computer, you could resume your work on another computer without loss of data.

**Q:** I can export my data to .csv files, but can I import .csv files into myStudent?  
**A:** No, currently the import feature is still a work in progress. Stay tuned for it!

**Q:** I can't seem to open myStudent when I tried to double-click it like a normal application. What do I do?
**A:** You can refer to our troubleshooting guide [here](#troubleshooting).

**Q:** What if I'm still having trouble using myStudent?  
**A:** You can tell us about the problem you're having and give more details [here](https://github.com/AY2223S1-CS2103T-F12-4/tp/issues/new). We'll be glad to help!

**Q:** Can I request for new features or themes?  
**A:** Of course you can! We're open to new suggestions. You can leave your suggestions [here](https://github.com/AY2223S1-CS2103T-F12-4/tp/issues/new). (Do note that implementation of any suggestion will be at our own discretion) 

---

### Troubleshooting

Warnings issued when Mac users are trying to open the software by double-clicking the icon.
“myStudent.jar” cannot be opened because it is from an unidentified developer.
Instead of double-clicking the software icon, you may want to right-click the icon and choose `Open`, then click `Open` in the pop-up window. Note that you only need to do this for the first time. For future usage, simply double-click the icon to launch the software.

Still unable to launch the software?
Make sure that you have installed `Java 11` or above by doing the following checking:

For Mac Users:
Open your terminal and type `java -version` and press <kbd>enter</kbd>. Information returned should show the current version of Java installed on your computer.

For Windows Users:
Open the command prompt and type `java -version` and press <kbd>enter</kbd>. Information returned should show the current version of Java installed on your computer.

If you do not have `Java 11` or above installed, please it install from [here](https://www.oracle.com/sg/java/technologies/downloads/).
