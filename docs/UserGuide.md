# 👾 MineFriends User Guide

MineFriends is an address book for you to find friends to play Minecraft multiplayer with,
at the right time, with the right game modes and on the right servers.

This user guide will help you get started with Minefriends and get to know what it can do for you. 
The guide is meant for Minecraft players who are familiar with the game and how multiplayer in Minecraft works.
No other technical knowledge is required.


### Table of Contents
* Getting started
* User interface breakdown
* Managing your Minecraft friends
* Summary of commands

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `MineFriends.jar` from [here](\to be added\).

3. Copy the file to the folder you want to use as the _home folder_ for your MineFriends.

4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   


Some sample commands you can try out:

   * **`list`** : Lists all of your friends.


   * **`add`**`n/Amy Bee m/AmyBee123 p/85355255 e/amy@gmail.com a/123, Jurong West Ave 6, #08-111` : <br>
   Adds a friend named `Amy Bee` to your friend list.


   * **`delete`**`3` : Deletes the 3rd friend shown in your current friend list.
   

Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Amy Bee`.


* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Amy Bee t/friend` or as `n/Amy Bee`.


* Parameters can be in any order.<br>
  e.g. if the command specifies the fields to be in the order`n/NAME p/PHONE_NUMBER`, 
  arranging them in an alternative order such as `p/PHONE_NUMBER n/NAME` is also acceptable.


* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

#
### Viewing help : `help`

Format: `help`

In the `Help` window, upon choosing a specific command, a Description,
and Example will be shown for that specific command along with the
specific Parameters for that command.

Example:<br>
Upon typing `help` or clicking on the `help` option, a help window
as such will appear:


![HelpWindow](images/HelpWindow.png)






### Adding a friend: `add`
Format (fields in [ ] are optional): `add n/NAME m/MINECRAFT_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS
[c/COUNTRY] [ms/MINECRAFT_SERVER] [s/SOCIAL_HANDLES] [t/TAG] [gt/GAME_TYPE] [ti/TIME_INTERVAL]`

Adds a person to your friend list.

#### Details of each field:
Name (prefix:n):<br/>
Each person can only have one name.

Minecraft name (prefix:m):<br/>
Each person can only have one minecraft name.

Phone Number (prefix:p):<br/>
Each person can only have one phone number.

Email (prefix:e):<br> 
Each person can only have one email.

Address (prefix:a): <br> 
Each person can only have one address.

Country (prefix:c) [**Optional**] <br>
Each person can only have one country.

Minecraft Server (prefix:ms) [**Optional**] <br>
Each person can have multiple Minecraft servers.<br>
Minecraft servers are in the format of `ServerName@IpAddress`.

#### Examples:

##### Adding with only compulsory fields
* Before adding `add n/Amy Bee m/AmyBee123 p/85355255 e/amy@gmail.com a/123, Jurong West Ave 6, #08-111`
 ![BeforeAdding](images/BeforeAddingAmy.png)

<br>
<br>
<br>

* After adding `add n/Amy Bee m/AmyBee123 p/85355255 e/amy@gmail.com a/123, Jurong West Ave 6, #08-111`
  ![AfterAdding](images/AfterAddingAmy.png)
  <br>
  <br>
  <br>
##### Adding with several optional fields

* Before adding `add n/john lee p/92990123 m/johnissmart a/20 colorado drive 
e/johnisgood@gmail.com c/China t/hot t/nice t/smart s/insta@HotJohn s/fb@JohnIsHot`
  ![BeforeAdding](images/BeforeAddingJohn.png)

<br>
<br>
<br>

* After adding `add n/john lee p/92990123 m/johnissmart a/20 colorado drive
  e/johnisgood@gmail.com c/China t/hot t/nice t/smart s/insta@HotJohn s/fb@JohnIsHot`
  ![AfterAdding](images/AfterAddingJohn.png)
<br>
<br>
<br>

### Listing all friends : `list`
Format: `list`<br>

Shows a list of all of your MineCraft friends.

Example: 

![ListExample](images/ListExample.png)





### Editing a friend's information : `edit`
Format: `edit INDEX [n/NAME] [a/ADDRESS] [t/TAG] …`<br>

Edits information about an existing friend in your friend list.


* Edits the person at the specified `INDEX`. The index refers to the index number shown in your displayed friend list. The index **must be a positive integer** 1, 2, 3, …​
* At least one field must be provided for editing.
* Existing values will be updated to the input values.
* For optional fields, you can remove all the person’s information in that field by typing the prefix (eg: `t/`) without
  including any information after the prefix.

#### Examples:

##### Editing only compulsory fields
Command: `edit 2 p/91234567 e/amybee123@gmail.com`<br>

Edits the phone number and email address of the 2nd person in the list 
to be `91234567` and `amybee123@gmail.com` respectively.<br>

Before editing
![BeforeEditAmyCompulsory](images/BeforeEditAmyCompulsory.png)
<br>
<br>
<br>
After editing
![AfterEditAmyCompulsory](images/AfterEditAmyCompulsory.png)


##### Editing compulsory and optional fields
Command: `edit 2 n/Amy Bee t/`<br>

Edits the name of the 2nd person in the list to be `Amy Bee` 
and clears all existing tags.

Before editing
![BeforeEditAmyOptional](images/BeforeEditAmyOptional.png)
<br>
<br>
<br>
After editing
![AfterEditAmyOptional](images/AfterEditAmyOptional.png)

### Locating friends by name: `find`
Format: `find KEYWORD [MORE_KEYWORDS]`<br>

Find friends whose names contain any of the given keywords.



* The search is case-insensitive. (e.g. `amy` will match `Amy`)<br>
<br>
Example: <br>
`find Amy` returns `amy` and `Amy Bee`
![FindAmy](images/FindAmy.png)


* The order of the keywords does not matter. e.g. `Amy Bee` will match `Bee Amy`.


* Only the name is searched.


* Only full words will be matched. (e.g. `Am` will not match `Amy`)


* Persons matching at least one keyword will be returned
  (e.g. `Amy Tan` will return `Amy Bee`, `Benson Tan`)
<br>        
Example:<br>
`find amy benson` returns `Amy Bee` and `Benson Tan`<br>
![FindAmyBenson](images/FindAmyBenson.png)

### Suggest me a friend: `suggest`

Suggest friends to play with given a set of constraints.

Format: `suggest [dt/DAY_TIME_IN_WEEK]* [k/KEYWORD]*`

* The search is case-insensitive (e.g. `amy` will match `Amy`)
* The order of the keywords does not matter
* The `Keyword` will be matched against **all** attributes of a friend (eg. Name, Minecraft Name, Address etc.)
* As long as some attribute of a friend contains the `Keyword`, the `Keyword` is considered to have a valid match
* All `Keyword` must be matched, but only 1 `DayTimeInWeek` needs to be matched

Examples:

`suggest dt/tue@2125 dt/sat@1200 k/Victoria k/Vicky` 
will return friends subjected to **all** of the following conditions:
1. Is available at either Tuesday 9:25pm or Saturday 12:00pm
2. Attributes contain the `Keyword` "Victoria" (ignore case)
3. Attributes contain the `Keyword` "Vicky" (ignore case)

* A friend with name "Victoria Tan" and Minecraft name "vicky12345", who is available from Tuesday 7pm to 11pm
will be matched
* A friend with name "Victoria Tan" but no other attributes containing "Vicky" will not be matched
* A friend with name "Victoria Tan" and Minecraft name "vicky12345", who is only available from Sunday 1pm to 6pm
will also not be matched

`suggest k/Victoria`
will return friends subjected to the only condition:
1. Attributes contain the `Keyword` "Victoria" (ignore case)

`suggest dt/tue@2125`
will return friends subjected to the only condition:
1. Is available at Tuesday 9:25pm

### Deleting a friend : `delete`
Format: `delete INDEX`<br>

Deletes the specified friend from your friend list.


* Deletes the friend at the specified `INDEX` of your friend list.
* The index **must be a positive integer** 1, 2, 3, …

Example:
* `list` followed by `delete 2` deletes the 2nd friend in your friend list.<br>

Before delete
  ![BeforeDelete](images/BeforeDelete.png)

After delete
![AfterDelete](images/AfterDelete.png)
### Clearing all entries : `clear`

Clears all entries from your friend list.

Example: - image to be added -

Format: `clear`


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MineFriends data are saved in the hard disk automatically after any 
command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q: How do I transfer my MineFriends data to another Computer?** <br>

**A**: 
Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MineFriends home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action      | Format, Examples                                                                                                                                                                                                                                                                                               |
|-------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**     | `add n/NAME m/MINECRAFT_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [s/SOCIAL_HANDLES] [t/TAG] [c/COUNTRY] [ms/MINECRAFT_SERVER]``[s/SOCIAL_HANDLES] [t/TAG] [gt/GAME_TYPE] [ti/TIME_INTERVAL]` <br/> <br/> e.g. `add n/Benson m/benson01 p/92881083 e/bensontan@hotmail.com a/ 4 Leith road s/ig@bensontan01 t/bff` |
| **List**    | `list`                                                                                                                                                                                                                                                                                                         | `list`     
| **Edit**    | `edit INDEX [n/NAME] [a/ADDRESS] [t/TAG] …`<br/> <br/> e.g.`edit 2 n/Amy Bee e/amybee123@gmail.com`                                                                                                                                                                                                            |    |                                                                                                                                                                       |
| **Find**    | `find KEYWORD [MORE_KEYWORDS]`<br> <br> e.g., `find Amy Benson`                                                                                                                                                                                                                                                |
| **Delete**  | `delete INDEX`<br><br> e.g., `delete 3`                                                                                                                                                                                                                                                                        |
| **Suggest** | `suggest [dt/DAY_TIME_IN_WEEK]* [k/KEYWORD]*`  <br/>  <br/> e.g. `suggest dt/tue@2125 dt/sat@1200 k/Victoria k/Vicky`                                                                                                                                                                                          ||
| **Clear**   | `clear`                                                                                                                                                                                                                                                                                                        ||
| **Exit**    | `exit`                                                                                                                                                                                                                                                                                                         |
| **Help**    | `help`                                                                                                                                                                                                                                                                                                         |
