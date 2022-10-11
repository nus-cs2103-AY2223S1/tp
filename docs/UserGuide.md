---
layout: page
title: User Guide
---

MyInsuRec is a **desktop app for financial advisors, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MyInsuRec can get your client and meeting management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `MyInsuRec.jar` from [here](https://github.com/AY2223S1-CS2103T-W16-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MyInsuRec.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   1. `viewClient`: View a particular client
   2. `addClient`: Add a client
   3. `delClient`: Delete client

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `addClient n/NAME ...`, `NAME` is a parameter which can be used as `addClient n/John Tan ...`.

* Items in square brackets are optional, while those not in square brackets are compulsory.<br>
  e.g `addClient n/NAME p/PHONE_NUMBER [e/EMAIL]` can be used as `addClient i/1 p/12345678 e/John@abc.com` or as `addClient n/John Tan p/12345678`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/28092022 d/30092022`, only `d/30092022` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit`, `listClient` and `listMeeting`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a client: `addClient`

Adds a new client to MyInsuRec.

Format: `addClient n/NAME p/PHONE_NUMBER [e/EMAIL]`

* Adds a client having name `NAME`.
* A client must have a NAME and a PHONE_NUMBER.
* Email is optional.

Examples:
* `addClient n/John Tan p/0123456789`
* `addClient n/John Tan p/0123456789 e/johntan@insurec.com`

### Listing all clients : `listClient`

List all clients in MyInsuRec.

Format: `listClient`

### Viewing a client: `viewClient`

View details associated with a client, such as client's name and phone number.

Format: `viewClient i/INDEX`

* Displays information about the client at the specific `INDEX`.
* The index refers to the index number shown in the displayed clients' list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `viewClient i/1`

### Deleting a client : `delClient`

Deletes the specified client from MyInsuRec.

Format: `delClient i/INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed clients' list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delClient i/2`

### Adding a meeting : `addMeeting`

Adds a new meeting to MyInsuRec. 
DATE should be in DD-MM-YYYY format and TIME should be in 24-hour format.

Format: `addMeeting i/INDEX d/DATE t/TIME dn/DESCRIPTION`

* Adds a meeting.
* A meeting contains the `INDEX` of the client in the clients list, the `DATE` and `TIME` for the meeting, and the `DESCRIPTION` of the meeting.

Examples:
* `addMeeting i/1 d/28092022 t/1400 dn/Team meeting`

### Listing meetings: `listMeeting`

List all meetings in MyInsuRec.

Format: `listMeeting`

### Viewing a meeting: `viewMeeting`

View details associated with a meeting, such as meeting's date and time.

Format: `viewMeeting i/INDEX`

* Displays information about the meeting at the specific `INDEX`.
* The index refers to the index number shown in the displayed meetings' list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `viewMeeting i/1`

### Deleting a Meeting : `delMeeting`

Deletes a meeting from MyInsuRec.

Format: `delMeeting i/INDEX`

* Deletes the meeting at the specified `INDEX`.
* The index refers to the index number shown in the displayed meeting list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delMeeting i/2`

### Exiting MyInsuRec : `exit`

Exits the program.

Format: `exit`

### Saving the data

MyInsuRec data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MyInsuRec data are saved as a JSON file `[JAR file location]/data/myinsurec.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MyInsuRec will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app, then overwrite the `myinsurec.json` file created by the app with the version of the file from the previous computer.

**Q**: I accidentally closed the app, will my data still be there?<br>
**A**: Yes, your data is saved automatically after every action.

**Q**: My computer does not recognise the file type jar. How do I open the app?<br>
**A**: Install Java version 11 and above from the official Java website.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                | Format, Examples                                                                                                                                                   |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add client**        | `addClient n/NAME p/PHONE_NUMBER [e/EMAIL]` <br> e.g., <br> • `addClient n/John Tan p/0123456789` <br> • `addClient n/John Tan p/0123456789 e/johntan@insurec.com` |
| **List all clients**  | `listClient`                                                                                                                                                       |
| **View client**       | `viewClient i/INDEX` <br> e.g., <br> • `viewClient i/1`                                                                                                            |
| **Delete client**     | `delClient i/INDEX` <br> e.g., <br> • `delClient i/1`                                                                                                              |
| **Add meeting**       | `addMeeting i/INDEX d/DATE t/TIME dn/DESCRIPTION` <br> e.g., <br> • `addMeeting i/1 d/28092022 t/1400 dn/Team meeting`                                             |
| **List all meetings** | `listMeeting`                                                                                                                                                      |
| **View meeting**      | `viewMeeting i/INDEX` <br> e.g., <br> • `viewMeeting i/1`                                                                                                          |
| **Delete meeting**    | `delMeeting i/INDEX` <br> e.g., <br> • `delMeeting i/1`                                                                                                            |
| **Exit**              | `exit`                                                                                                                                                             |
