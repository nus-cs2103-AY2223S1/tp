---
layout: page
title: User Guide
---

NUScheduler is a desktop app for **managing contacts, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUScheduler can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `NUScheduler.jar` from [here](https://github.com/AY2223S1-CS2103T-T17-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your NUScheduler.

4. Double-click the file to start the app.

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`profile -a`** : Adds profile.

   * **`profile -d `**`2` : Deletes the 2nd profile shown in the current list.

   * **`profile -v`** : Lists profiles or shows a single profile.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>



### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a profile: `profile -a`

Adds a profile to NUScheduler.

Format: `profile -a n/NAME p/PHONE_NUMBER e/EMAIL`

Examples:
* `profile -a n/John Doe p/98765432 e/johnd@example.com`
* `profile -a n/Betsy Crowe e/betsycrowe@example.com p/1234567`

### Viewing profile: `profile -v`

Shows either a list of profiles or a single profile.

Format: `profile -v [INDEX]`

Tip: `INDEX` is optional, specify to view single profile.

### Deleting a profile: `profile -d`

Deletes a specified profile from NUScheduler.

Format: `profile -d INDEX`

* Deletes the profile at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `profile -d 1` deletes the first profile listed.

### Adding event: `event -a`

Adds an event with a name and a start timing and end timing.

Format: `event -a n/NAME s/START e/END [p/PROFILE]…`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An event can have any number of profiles (including 0)
</div>


### Viewing upcoming events: `event -u`

Displays a list of upcoming events, ordered by the date, for the next specified number of days.

Format: `event -u DAYS`

* The days refer to the number of days from the current date. All events within this time frame will be displayed.
* The days **must be a positive integer** 1, 2, 3, ...

Example:
* `event -u 5` displays all events taking place in the next 5 days ordered by date.

### Viewing events: `event -v`

Displays either a list of events or a single event.

Format: `event -v INDEX`

Tip: `INDEX` is optional, specify to view a single profile.

* The index refers to the index number shown in the full displayed event list after using `event -v`.
* The index **must be a positive integer** 1, 2, 3, ...

Example:
* `event -v` displays all events in NUScheduler.
* `event -v 2` displays the second event listed in `event -v`.

### Deleting an event: `event -d`

Deletes a specified event from NUScheduler.

Format: `event -d INDEX`

* Deletes the events at the specified `INDEX`.
* The index refers to the index number shown in the displayed event list.
* The index **must be a positive integer** 1, 2, 3, ...

Example:
* `event -v` followed by `event -d 2` deletes the 2nd event displayed.

### Exiting the program: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

No FAQ Yet.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Profile** | `profile -a n/NAME p/PHONE_NUMBER e/EMAIL`
**Delete Profile** | `profile -d INDEX`
**View Profile** | `profile -v [INDEX]`
**Add Event** | `event -a n/NAME s/START e/END [p/PROFILE]…`
**Delete Event** | `event -d INDEX`
**View Event(s)** | `event -v [INDEX]`
**View Upcoming Event(s)** | `event -u DAYS`
