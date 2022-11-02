---
layout: page
title: User Guide
---

FABook is your **dependable assistant** who **reminds you of meetings** and **consolidates crucial information** like financial plans and client information right at your fingertips! You can now focus on giving your full attention to your clients without having to worry about things slipping your mind.

**FABook is optimized for a financial adviser to use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FABook can get your contact management tasks done faster than traditional GUI apps.

This user guide will help you get started and understand how FABook can **seamlessly streamline your daily lives**.

# Table of Contents

* TOC 
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `FABook.jar` [here](https://github.com/AY2223S1-CS2103T-T10-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your FABook.

4. Double-click the file to start the app. The GUI similar to below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command line and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all clients.

   * **`create`**`n/John Doe p/98765432 a/John street, block 123, #01-01` : Adds a client named `John Doe` to your FABook.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.
7. Refer to the [Command Summary](#command-summary) for a quick summary of all commands.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Getting Familiar With Your User Interface

![Ui Breakdown](images/UIBreakDown.png)

1. **Menu Bar**: Access the file, help or upcoming meetings here.
2. **Command Line**: Type in your commands here.
3. **Command Display**: The execution of your commands appear here.
4. **Result Display**: The result of your command execution appears here.
5. **Contact Information**: The full contact information of a client appears here.
6. **Data Storage**: This is where the data is stored and loaded from.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Legend
1. :white_check_mark: : **Input Shortcut**
2. :bulb: :  **Note**
3. :heavy_exclamation_mark: : **Caution**

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Overview of commands:**<br>

* Words in `UPPER_CASE` are the information supplied by you.
  e.g `NAME` is a parameter in `create n/NAME`, where you can input `create n/John Doe`.
  <br>

* Parameters in `[]` are optional.
  e.g `HOME_ADDRESS` is an optional parameter in `create n/NAME [a/HOME_ADDRESS]`, where you can input `create n/John Doe a/Blk 30 Geylang Street 29` or just `create n/John Doe`.
  <br>

* Items with `…`​ after them can be used multiple times.
  e.g `NAME…​` can be used as `Jon`, `Jon Jack` etc.
  <br>

* `INDEX` represents the index of a specific client in your FABook.
  e.g You can use the command `delete INDEX` as `delete 2`.
<br>

* Parameters can be in **any order**.
  e.g You can input either `create n/NAME p/HP_NUMBER` or ` create p/HP_NUMBER n/NAME`.
  <br>

* Only the last occurrence of a parameter that is expected once will be taken **if you specify it more than once.**
  e.g Given `p/12341234 p/56785678`, only `p/56785678` will be taken.
  <br>

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`,`clear`, `redo` and `undo`) will be ignored.
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  <br>

* Names are case-sensitive. e.g. John Doe and john doe are treated as the different names.

 </div>

[Return to Table of Contents](#table-of-contents)

### General

#### Viewing help : `help`

Shows a message explaining how you can access our user guide.

Format: `help` or Press `F1`

![help](images/UserGuide/help.png)

#### Listing your clients : `list`

Shows a list of all clients in your FABook.

Format: `list`

![list](images/UserGuide/list.png)

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `list` with `l` for convenience.

</div>

### Creation

#### Creating a client contact : `create`

Creates a client contact with their information in your FABook. Any contact information you don't have on hand can be updated later.
<br>

**Rationale**
* If you have multiple meeting times with your client, simply repeat the field `mt/TIME`.
* The `[nw/NETWORTH]` field only accepts inputs that starts with the dollar sign, are numeric,and have more than 4 digits. We want to standardise the currency and the minimum amount net worth of a client.
eg. `nw/$1234`.
* The `[mt/TIME...]` field accepts inputs in the `DD-MM-YYYY-HH:MM` format. Note that `YYYY` ranges from `2000` to `2099`.
* Does not allow you to create a person with the same name and phone number as a current person in the FABook.
* Allows you to create a person with same name but different phone number or same number and different name as a current person in the FABook.


Format: `create n/NAME p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [ds/DESCRIPTION] [nw/NETWORTH] [mt/TIME...] [t/TAG]`

##### Common Data Field Formats

| Data Field           | Format                          | Remarks                                                                                         |
|----------------------|---------------------------------|-------------------------------------------------------------------------------------------------|
| **`n/NAME`**         | `n/[name]`                      | Name should only contain alphanumeric characters and spaces, and it should not be blank         |
| **`p/PHONE_NUMBER`** | `p/[phone_number]`              | Phone Number should only contain numbers and should be at least 3 digits long                   |
| **`e/EMAIL`**        | `e/[local_part]@[domain]`       | Local Part should only contain alphanumeric characters and these special characters +_.-        |
| **`a/ADDRESS`**      | `a/[address]`                   |                                                                                                 |
| **`d/DESCRIPTION`**  | `d/[description]`               |                                                                                                 |
| **`nw/NETWORTH`**    | `nw/$[net_worth]`               | Net worth should start with a $ sign, only contain numbers and should be at least 4 digits long |
| **`mt/TIME`**        | `mt/[DD]-[MM]-[YYYY]-[HH]:[MM]` | DD: 01-31<br/>MM: 01-12<br/>YYYY: 2000-2099<br/>HH:MM: 00:00 - 23:59                            |
| **`t/TAG`**          | `t/[tag]`                       | Tag should only be `SECURED` or `POTENTIAL`                                                     |

![create](images/UserGuide/create.png)
Example given: `create n/The Rock p/98765432 a/Hollywood ds/An actor and entrepreneur looking for stable wealth growth nw/$800000000 mt/15-12-2022-19:00 t/Potential`

<div markdown="block" class="alert alert-block alert-success">

:bulb: **Note:**
`NAME` and `PHONE_NUMBER` are the only compulsory inputs. Other parameters can be left blank.
<br>

:bulb: **Note:**
It is recommended to only have 1 `TAG` per client.

</div>

Other examples:
* `create n/John Doe p/98765432 a/John street, block 123, #01-01`
* `create n/Betsy Crowe a/Bugis MRT p/1234567 mt/10-11-2022-18:00 mt/01-02-2022-16:00`
* `create n/Benedict Lee p/91281329 e/benedict@gmail.com a/Redhill Ave 3 ds/Risk averse nw/$20000 mt/10-11-2022-16:00 t/SECURED`

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `create` with `c` for convenience.<br>
Format: `c n/NAME p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [ds/DESCRIPTION] [nw/NETWORTH] [mt/TIME...] [t/TAG]`

</div>

#### Assigning PDF file to a client : `filepath`

Assigns a PDF file to a client in your FABook.
<br>

**Constraints**
* `INDEX` is the index of the client in the currently displayed list.
* The filepath is the absolute path of the PDF on the local disk of your computer. e.g. `C:/Users/Ryzen/Downloads/Sample Wealth Accumulation Plan.pdf`
* Only file paths that lead to a PDF is allowed. e.g. `C:/Users/Ryzen/Downloads/Sample Wealth Accumulation Plan.docx` is an invalid filepath.
* Moving or renaming the PDF file in your local disk will not change the client's assigned file path, so you would have to assign it manually.

Format: `filepath INDEX f/FILEPATH`

![filepath](images/UserGuide/filepath.png)
Example given: `filepath 2 f/C:\Users\Eugene Tay\Downloads\Tutorial_8_qns.pdf`

Other example:
* `filepath 2 f/C:/Users/Ryzen/Downloads/CS2103T-T08-3.pdf`

### Retrieving

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `find` with `f` for convenience for all retrieving commands.

</div>

#### Finding your client by name : `find`

Find your clients whose name contain any of the given keywords.
<br>

**Constraints**
* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched. e.g. `find n/Kent` won’t return clients living on Kent Ridge Road
* Partial words will not be matched. e.g. `Jack` will not match `Jackson`.
* Clients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Format: `find n/NAME`

![findname](images/UserGuide/findname.png)
Example given: `find n/Bernice`

Other examples:
* `find n/John` returns `john` and `John Doe`
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
Format: `f n/NAME`

</div>

#### Finding your client by phone number : `find`

Find your clients whose phone number matches the input number.

You can use this command when:
1. You need to check if an unsaved phone number belongs to any one of your clients.
<br>

**Constraints**
* Only full numbers will be matched e.g. `7654` will not match `80765432`
* All clients matching the number will be returned. e.g. All clients in the same household will be returned if they share the same home number.

Format: `find p/NUMBER`

![findphone](images/UserGuide/findphone.png)
Example given: `find p/98765432`

Other example:
* `find p/90333333` returns the client(s) with `90333333` stored as their number

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
Format: `f p/NUMBER`

</div>

#### Finding your client by address : `find`

Find your clients whose addresses matches the input address.

You can use this command when:
1. You are around the area and you want to meet up with clients near you.
<br>

**Constraints**
* The search is case-insensitive. e.g `serangoon` will match `Serangoon`
* The order of the keywords does not matter. e.g. `Kio Mo Ang` will match `Ang Mo Kio`
* Only the address is searched. e.g. `find a/Kent` won’t return clients with the name 'Kent'
* Words can be matched only if the whole address is included. e.g. `Tamp` won’t match `Tampines`
* Clients with address matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Buona Clementi` will return `Buona Vista Drive`, `6 Clementi Ave`
* Address can contain numbers. Find results will return all clients with that address keyword.
  e.g. `find a/30` will return `Blk 30 Geylang Street 29`, `Blk 30 Lorong 3 Serangoon Gardens`

Format: `find a/ADDRESS`

![findaddress](images/UserGuide/findaddress.png)
Example given: `find a/hollywood`

Other example:
* `find a/Bedok` returns the client(s) with `Bedok` stored as their address

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
Format: `f a/ADDRESS`

</div>

#### Finding your client by tag : `find`

You can find your clients whose tag matches the input tag.

You can use this command when:
1. You need to consolidate `SECURED` clients to share new perks your company has.
2. You need to consolidate `POTENTIAL` clients so that you can make a targeted effort to secure these clients.
<br>

**Constraints**
* TAG can only be either `SECURED` or `POTENTIAL`
* If multiple tags are given, only the last one is used.

Format: `find t/TAG`

![findtag](images/findTag.png)
Example given: `find t/potential`

Examples:
* `find t/SECURED` displays the list of all `SECURED` clients.

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
Format: `f t/TAG`

</div>

#### Opening PDF file of your client : `file`

This will open a client's assigned PDF file in your PDF file viewer.

You can use this command when:
1. You forgot the details of a client's financial plans during the meeting. Simply pull up the PDF that you stored for this client.
<br>

**Constraints**
* `INDEX` is the index of the client in the currently displayed list.
* Moving or renaming the PDF file in your local disk will cause the command to not work, which will require you to reassign a file path to the client again.

Format: `file INDEX`

![file](images/UserGuide/file.png)
Example given: `file 2`

Examples:
* `file 2`

#### Get upcoming meetings : `Upcoming Meetings`

Returns a list of clients who has a scheduled meeting in the next 7 days.

You can use this command when:
1. You need to check which upcoming client meetings you need to prepare for.
<br>

**Constraints**
* This uses your device's present local time as reference.
* As long as a client has a meeting in the next 7 days, it will be shown.
* Upcoming Meetings does not auto refresh, so meetings that pass after you have opened
  `Upcoming Meetings` will only be refreshed when a new window is opened.

Format: Menu bar on the top of the application or press `F2`.

![Meetings](images/upcomingMeeting.png)

<div markdown="block" class="alert alert-block alert-danger">

:heavy_exclamation_mark: **Caution:**
As this command syncs with your device's system clock, please make sure the current date, time, and timezone are correct before using this command.

</div>

### Updating

#### Updating your client's information : `update`

Updates the information of a client stored in your FABook.

You can use this command when:
1. A client changes address, phone number, email
2. You want to edit the description of a client.
3. A client's net worth changes.
4. You have secured a `POTENTIAL` client.
<br>

**Constraints**
* Edits the client with the provided index.
* `INDEX` is the index of the client in the currently displayed list.<br>
* You must provide **at least one** of the optional fields .
* You can also update the description of a client through the [`description` command](#updating-your-clients-description--description).
* You must update the meeting time of a client through the [`meeting` command](#updating-meetings--meeting), [`deletemeeting` command](#delete-meetings--deletemeeting) and [`sync` commands](#remove-past-meetings--sync).
* Does not allow you to update a person to have the same name and phone number as a current person in the FABook.
* Allows you to update a person to have same name but different phone number or same number and different name as a current person in the FABook.
* Person profiles and do not refresh when person is updated, they are only updated when we re-click the person card

Format: `update INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [ds/DESCRIPTION] [nw/NETWORTH] [t/TAG]`

![update](images/UserGuide/update.png)
Example given: `update 1 p/12345678`

<div markdown="block" class="alert alert-block alert-success">

:bulb: **Note:**
Only parameters you provide will be changed.

</div>

Other example:
* `update 2 n/John Doe p/91234567 a/21 Lower Kent Ridge Rd` Updates the second listed client's
  name, phone number and address to be `John Doe`, `91234567` and `21 Lower Kent Ridge Rd` respectively.

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `update` with `u` for convenience.<br>
Format: `u INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [ds/DESCRIPTION] [nw/NETWORTH] [t/TAG]`

</div>

#### Updating your client's description : `description`

Updates your client's description data field.

You can use this command when:
1. You have a verbose change in client description.
<br>

**Constraints**
* `INDEX` is the index of the client in the currently displayed list.
* Updating description of your client can also be done through the 'update' command. See [Update command](#updating-your-clients-information--update)

Format: `description INDEX ds/DESCRIPTION`

![description](images/UserGuide/description.png)
Example given: `description 5 ds/High Financial Literacy`

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `description` with `desc` for convenience.
Format: `desc INDEX ds/DESCRIPTION`

</div>

#### Updating meetings : `meeting`

Updates one or more meeting times to your client in your FABook.

You can use this command when:
1. You have scheduled a new meeting with your client.
<br>

**Constraints**
* `INDEX` is the index of the client in the currently displayed list.
* `MEETINGTIME` should be in the format `DD-MM-YYYY-HH:MM`.
* mt/ should be put before each separate meeting time.
* The client's existing meeting times remain unchanged.

Format: `meeting INDEX mt/MEETINGTIME...`

Examples:
* `meeting 2 mt/09-10-2023-23:50 mt/28-02-2020-15:16` adds two meetings to the second client in the displayed list,
   one at 9 October 2023 23:50, the other at 28 February 2020 15:16.

### Deletion

#### Deleting a client : `delete`

Deletes the specified client from your FABook.

You can use this command when:
1. You drop a client.
<br>

**Constraints**
* `INDEX` is the index of the client in the currently displayed list.
* Deletes the client with the specified index in your FABook.

Format: `delete INDEX`

![delete](images/UserGuide/delete.png)

Examples:
* `delete 2` deletes the second client in the currently displayed client list in your FABook.

#### Delete meetings : `deletemeeting`

Deletes a meeting from your client in your FABook.

You can use this command when:
1. Your client or you cancels a meeting.
<br>

**Constraints**
* `INDEX` is the index of the client in the currently displayed list.
* `MEETINGTIME` should be in the format `DD-MM-YYYY-HH:MM`.
* If the given meeting time is not on the list, the client's meetings remain unchanged.

Format: `deletemeeting INDEX mt/MEETINGTIME`

Examples:
* `deletemeeting 2 mt/09-10-2023-23:50` deletes the meeting at 9 October 2023 23:50 from the second client in the displayed list,
   if such a meeting was scheduled.

#### Remove past meetings : `sync`

Removes every scheduled meeting time that has already passed.

You can use this command when:
1. You want an up to date list of meetings.
<br>

**Constraints**
* This uses your device's present local time as reference. All meetings scheduled to be earlier than the present time will removed.
* Sync command does not refresh the meetings displayed on the person profile, you need to re-click on person card.

Format: `sync`

![sync](images/UserGuide/sync.png)

<div markdown="block" class="alert alert-block alert-danger">

:heavy_exclamation_mark: **Caution:**
As this command syncs with your device's system clock, please make sure the current date, time, and timezone are correct before using this command.
Please note that undo cannot undo this command!

</div>

#### Clearing all entries : `clear`

Clears all entries from your FABook.

Format: `clear`

![clear](images/UserGuide/clear.png)

<div markdown="block" class="alert alert-block alert-warning">

:heavy_exclamation_mark: **Important**
If you run this command by accident, you can [undo](#undoing-a-previous-command--undo) the command to restore all previously cleared entries.

</div>

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `clear` with `cl` for convenience.

</div>

### Command Flow
#### Undoing a previous command : `undo`

Undos your last command.

You can use this command when:
1. When you make a mistake editing the contact book.
<br>

**Constraints**
* The command intended to be undone should be an undoable command.
* Undoable commands are: `create`, `delete`, `update`, `clear`, `description`, `meeting`, `deletemeeting` and `redo`
* Non-undoable commands are: `exit`, `find`, `help`, `list`, `file`, `sync` and `filepath`

Format: `undo`

![undo](images/UserGuide/undo.png)

<div markdown="block" class="alert alert-block alert-warning">

:heavy_exclamation_mark: **Important**
You can undo a [`redo` command](#redoing-a-previous-command--redo).

</div>

#### Redoing a previous command : `redo`

Redos your last undone command.

Format: `redo`

![redo](images/UserGuide/redo.png)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

<div markdown="block" class="alert alert-block alert-info">

:white_check_mark: **Input Shortcut:**
You can replace `exit` with `e` for convenience.

</div>

### Saving the data

Your FABook data are saved in the hard disk automatically after any command that changes the data. This means hassle free saving.

### Editing the data file

FABook data are saved as a text file `[JAR file location]/data/addressbook.json`. If you are an advanced user, you are welcome to update data directly by editing that data file.

<div markdown="block" class="alert alert-block alert-danger">

:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FABook will discard all data and start with an empty data file at the next run.

</div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**:    1. Find the save file on the current computer. The save file can be found as `FABook.json` in the `data` folder created by the app.

   2. Transfer the save file to your new computer.

   3. Install FABook on the new computer.

   4. Launch FABook and exit.

   5. You should see a new `data` folder containing a new `FABook.json`. Replace this file with the save file copied over from your old computer.

   6. Launch FABook again. Your data should be there!

**Q**: Why can I not undo the `sync` command?<br>
**A**: Meetings that have passed should not need to be undone and be shown again.

**Q**: Person profile does not show when I click person card once.<br>
**A**: You need to click the person card twice for the person profile to load.

**Q**: Why does the person profile GUI not refresh after an edit or clear command?<br>
**A**: In order to refresh the person profile, you will have the re-click on the person card again.

**Q**: Why can two clients have the same meeting time?<br>
**A**: We are accounting for cases where perhaps two clients know each other, and they request to listen to a new product together.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Glossary

| Term                     | Definition                                                                                                                                                                                                                                                                           |
|--------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Alphanumeric             | Refers to characters that are the combined set of the 26 alphabetic characters, a to Z, both lowercase and uppercase, and the 10 Arabic numerals, 0 to 9.                                                                                                                            |
| Client                   | Any person who may be seeking financial products.                                                                                                                                                                                                                                    |
| Command Line Interface   | A command-line interface (CLI) is a text-based user interface (UI) used to run programs, manage computer files and interact with the computer.                                                                                                                                       |
| dd-MM-yyyy-hh:mm         | Date format whereby `dd` refers to the 2 digit days, `MM` refers to the 2 digit months, `yyyy` refers to the 4 digits years, `hh` refers to the 2 digits hours, `mm` refers to the 2 digits minutes. They are each separated by a hyphen: `-` character.                             |
| Financial adviser        | A financial adviser or financial advisor is a professional who provides financial services to clients based on their financial situation. In many countries, financial advisors must complete specific training and be registered with a regulatory body in order to provide advice. |
| Graphical User Interface | A graphical user interface (GUI) is an interface through which a user interacts with electronic devices such as computers and smartphones through the use of icons, menus and other visual indicators or representations (graphics).                                                 |
| Index                    | The number that corresponds to the position of the client in the list. The index must be a numeral above 0.                                                                                                                                                                          |
| Java                     | The programming language used for this application. Java is a general-purpose computer programming language designed to produce programs that will run on any computer system.                                                                                                       |
| JAR                      | JAR stands for Java ARchive. FABook uses JAR to deliver its distribution. JAR is a file format based on the popular ZIP file format and is used for aggregating many files into one.                                                                                                 |
| JSON                     | JSON stands for JavaScript Object Notation. JSON is the format used to store your FABook's data. JSON is a lightweight format for storing and transporting data.                                                                                                                     |

[Return to Table of Contents](#table-of-contents)


## Command summary

| Action                    | Format, Examples                                                                                                                                                   | Shortcut |
|---------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| **Help**                  | `help`                                                                                                                                                             | f1       |
| **List**                  | `list`                                                                                                                                                             | l        |
| **Create**                | `create n/NAME p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [ds/DESCRIPTION] [nw/NETWORTH] [mt/TIME] [t/TAG] `<br> e.g., `create n/Betsy Crowe a/Newgate Prison p/1234567` | c        |
| **Add a File**            | `filepath INDEX f/FILEPATH`<br/> e.g. `filepath 2 f/C:/Users/Ryzen/Downloads/CS2103T-T08-3.pdf`                                                                    |          |
| **Add Meeting**           | `meeting INDEX mt/TIME` <br/> e.g. `meeting 5 mt/19-11-2022-19:00`                                                                                                 |          |
| **Find**                  | `find n/NAME…` or `find p/NUMBER` or `find a/ADDRESS` <br> e.g., `find n/James Jake` or `find p/09122222` or `find a/Jurong`                                       | f        |
| **Find**                  | `find t/TAG` <br> e.g., `find t/POTENTIAL`                                                                                                                         | f        |
| **Open File**             | `file INDEX`<br/> e.g. `file 2`                                                                                                                                    |          |
| **Get Upcoming Meetings** |                                                                                                                                                                    | f2       |
| **Update**                | `update INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [ds/DESCRIPTION] [nw/NETWORTH] [t/TAG]`<br> e.g.,`update 2 p/91234567 a/21 Lower Kent Ridge Rd`      | u        |
| **Description**           | `description INDEX ds/DESCRIPTION` <br> e.g., `description 3 ds/Accident prone`                                                                                    | desc     |
| **Delete**                | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                | d        |
| **Delete Meeting**        | `deletemeeting INDEX mt/TIME` <br/> e.g. `deletemeeting 4 mt/15-12-2022-13:00`                                                                                     |          |
| **Remove past meetings**  | `sync`                                                                                                                                                             |          |
| **Clear**                 | `clear`                                                                                                                                                            | cl       |
| **Undo**                  | `undo`                                                                                                                                                             |          |
| **Redo**                  | `redo`                                                                                                                                                             |          |
| **Exit**                  | `exit`                                                                                                                                                             | e        |

[Return to Table of Contents](#table-of-contents)
