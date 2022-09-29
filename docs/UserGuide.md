---
layout: page
title: User guide
---

TABS helps project team leaders **overview tasks assigned to group members**. It is targeted at individuals with less programming background i.e. **less CLI-intensive** in nature.

- [`Quick start`](#quick-start)
- [`Features`](#features)
  - Adding a person: [`adduser`](#adding-a-person-adduser)
  - Listing all persons: [`list`](#listing-all-persons--list)
  - Edit a person: [`edituser`](#editing-a-person--edituser)
  - Locating persons by name: [`finduser`](#locating-persons-by-name-finduser)
  - Deleting a person : [`deleteuser`](#deleting-a-person--deleteuser)
  - Adding a group: [`addgroup`](#creating-a-group--addgroup)
  - Add member to  group: [`addmember`](#adding-a-member--addmember)
  - Assign member a task: [`assigntask`](#assigning-a-task-to-a-user-assigntask)
  - Locate group by name: [`display`](#display-a-group--display)
  - Delete a group: [`deletegroup`](#deleting-a-group--deletegroup)
  - Exiting the program : [`exit`](#exiting-the-program--exit)
- [`Command summary`](#command-summary)


---
## Quick start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest TABS.jar.
3. Copy the file to the folder you want to use as the home folder for your TABS.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
![TABS example](Ui.png)
5. Refer to the [Features](#features) below for details of each command.


--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `adduser n/NAME`, `NAME` is a parameter which can be used as `adduser n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Adding a person: `adduser`

Adds a person to the address book.

Format: `adduser n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: 
<b>Tip:</b> A person can have any number of tags (including 0)
</div>

Examples:
* `adduser n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `adduser n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edituser`

Edits an existing person in the address book.

Format: `edituser NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person with the specified `NAME`. The person with this `NAME` must already exist in the app.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edituser John Doe p/91234567 e/johndoe@example.com` Edits the phone number and email address of `John Doe` to be `91234567` and `johndoe@example.com` respectively.
*  `edituser BetsyCrower n/Betsy Crower t/` Edits the name of `BetsyCrower` to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `finduser`

Finds persons whose names contain any of the given keywords.

Format: `finduser KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `finduser John` returns `john` and `John Doe`
* `finduser alex david` returns `Alex Yeoh`, `David Li`
<br>
<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `deleteuser`

Deletes the specified person from the address book.

Format: `deleteuser NAME`

* Deletes the person with the specified `NAME`. The person with this `NAME` must already exist in the app.

Examples:
* `deleteuser Betsy` deletes the person with the username `Betsy`.
* `deleteuser Betsy Hoover` deletes the person with the username `Betsy Hoover`.

### Creating a group : `addgroup`

Creates a new group with no members.

Format: `addgroup  GROUP`

* Creates a group with the specified GROUP

Examples:

Adds an existing contact to an existing group in TABS.

* addgroup CS2103T

### Adding a member : `addmember`

Format: `addmember GROUP NAME`

Examples:

* addmember CS2103T UserName
* addmember CS2101 DefaultUser

### Assigning a task to a user: `assigntask`

Assigns a task to a user in TABS.

Format: `assigntask NAME t/TASK`

* Assigns a TASK to a user with the specified NAME.
* TASK is of type String.

Examples:

* assigntask n/John t/TeamProject assigns TeamProject task to user John.
* assigntask n/Billy Tom t/Team Delta Project assigns Team Delta Project to user Billy Tom.

### Display a group : `display`

Displays the group members allocated to the specified group. Instead of details, assigned tasks will be listed.

![TABS example](Ui.png)

Format: `display GROUP`

Lists the members associated with the GROUP and their tasks.

Examples:

* display CS2103T displays the users under group CS2103T

### Deleting a group : `deletegroup`

Deletes the specified group from the address book.

Format: `deletegroup GROUP`

Deletes the group with the group name GROUP.

Examples:

* deletegroup CS2103T deletes the group CS2103T

### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Command summary

 Action            | Format, Examples                                                                                                                                                                                     
-------------------|------------------
**Add User**      | `adduser [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br> e.g., `adduser n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete User** | `deleteuser NAME`<br> e.g., `deleteuser James Ho`                                                                                                                                     
**Edit User**     | `edituser NAME [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edituser 2 n/James Lee e/jameslee@example.com`                                                                
**Find User**     | `finduser KEYWORD [MORE_KEYWORDS]`<br> e.g., `finduser James Jake`                                                                                                                                   
**List**          | `list`                                                                                                                                                                                               
**Add Group**     | `addgroup GROUP` <br> e.g., addgroup CS2103T                                                                                                                                                         
**Add Member**    | `addmember [g/GROUP] [n/NAME]` <br> e.g., addmember g/CS2103T n/James Lee, addmember g/CS2101 n/DefaultUser                                                                                          
**Assign Task**   | `assigntask [n/NAME] [t/TASK]` <br> e.g., assigntask n/John t/TeamProject                                                                                                                            
**Display Group** | `display GROUP` <br> e.g., display CS2103T                                                                                                                                                           
**Delete Group**  | `deletegroup GROUP` <br> e.g., deletegroup CS2103T                                                                                                                                                   
**Exit**          | `exit`                                                                                                                                                                                               
