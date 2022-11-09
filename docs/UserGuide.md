---
layout: page
title: User Guide
---

![App Logo](images/guest_book_160.png)

## Introduction

GuestBook is a **hotel management application** that aims to revolutionise the small
hotel and backpacker's inn industry.
As a **hotel manager**, capitalise on
GuestBook's **efficient management tools** to save you time. You can use GuestBook to manage your guests and
their details with just your keyboard, literally all in the palm of your hands!
By using our features, your essential hotel management needs are taken care of.
With GuestBook, managing guests have never been easier.

This user guide will ease you in using GuestBook and guide you along to incorporate GuestBook
into your management workflow. It provides an in-depth description of GuestBook's core features
and how to best use them to suit your needs. With our intuitive design, you will become
a master at using GuestBook in no time!

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Table of Contents
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## How to use the User Guide

* For a quick and easy way to **get started** with GuestBook, you can visit the [Quick Start](#quick-start) section.
* To **understand the terms** we use in GuestBook, you can visit the [Glossary](#glossary) section.
* To see the **amazing features** that GuestBook provides and how to use them, you can visit the [Features](#features) section.
* Having **issues**? Check out the [FAQ](#faq) section and see if you can find your solutions there!
* Have a **question** for us or require our **assistance**? Feel free to [contact us](#contact-us), and we will do our best to assist you!

<div markdown="block" class="alert alert-success">

**:bulb: Tip:**<br>
* As there is a lot of content covered in this guide, it would be good to browse through
the contents once first. This will give you a clearer idea of how GuestBook functions. After which,
go in-depth into the sections you are interested to learn more about.
* Some information (e.g., constraints of fields) are placed in separate sections to prevent information overload.
By following this incremental approach, we hope to make the content more digestible for you.
</div>

### Style Guide

These styles below are consistently used throughout this user guide. This section hopes to provide
you with a better understanding on how to interpret these formats.
* [Phrases in blue](#style-guide) mean that they are hyperlinked to a
section of this user guide or to an external website.
* `Coded phrases` surrounded by a block indicate that they are technical terms such as GuestBook commands.
* **Bolded phrases** are key terms that you should pay attention to.
* _Italicised phrases_ are used to highlight phrases that are part of an example.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Components of the User Interface

### Locations of the components
![UiComponentLabels](images/UiComponentLabels.png)

<div markdown="block" class="alert alert-success">

**:bulb: Tip:**<br>
* By using your mouse to hover over the icons in the application itself, the names of the 
components will be visible.
</div>

<div style="page-break-after: always;"></div>

### Use of the components

| Component                              | Use                                                                                                                                                               |
|----------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Command Box**                        | This is where you **enter commands**.                                                                                                                             |
| **Result Display**                     | This is where the detailed **results** of your commands will be shown.                                                                                            |
| **Guest Card**                         | This is where all the **guest information** of a guest will be shown.                                                                                             |
| **Status Bar**                         | This shows the location of your **save file**.                                                                                                                    |
| ![Phone](images/phone.png)             | This is the **phone number** of the guest.                                                                                                                        |
| ![Email](images/email.png)             | This is the **email address** of the guest.                                                                                                                       |
| ![Room](images/room.png)               | This is the **room** the guest is staying in.                                                                                                                     |
| ![DateRange](images/dateRange.png)     | These are the **dates of stay** of the guest.                                                                                                                     |
| ![NumberOfGuests](images/group.png)    | This is the total **number of guests** in the guest's room.                                                                                                       |
| ![IsRoomClean](images/isRoomClean.png) | This shows whether the guest's **room has been cleaned**. **Green** means that the room has been cleaned while **red** means that the room has yet to be cleaned. |
| ![Bill](images/bill.png)               | This is the current **bill** of the guest.                                                                                                                        |
| ![Request](images/request.png)         | This shows any **requests** made by the guest.                                                                                                                    |

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick start

1. Ensure you have `Java 11` or above installed in your Computer.
   * If you are using Mac, open the `Terminal` and enter `java --version`.
     * To open the `Terminal`, press `command` and `space bar` together to launch Spotlight.
     * Following, enter `terminal` in the search bar.
   * If you are using Windows, open the `Command Prompt` and enter `java -version`.
     * To open the `Command Prompt`, press `Windows` and `R` together to launch the "Run" box.
     * Following, enter `cmd` and click `OK`.
   * If `Java 11` is installed, you would get a similar output as shown below. You can continue to step 2.
   (Do not worry about what the message says, it is not necessary to understand it when learning how to use GuestBook.)
   ```
    openjdk 11.0.16.1 2022-07-19 LTS
    OpenJDK Runtime Environment Zulu11.58+23-CA (build 11.0.16.1+1-LTS)
    OpenJDK 64-Bit Server VM Zulu11.58+23-CA 
    (build 11.0.16.1+1-LTS, mixed mode)
   ```
   * Else, if `Java 11` is not installed, an error message will appear as shown below.
   ```
   'java' is not recognized as an internal or external command, operable
    program or batch file.
   ```
   * Follow these [instructions](https://www.oracle.com/sg/java/technologies/downloads/#java11) to
   download `Java 11` and return to step 1 once you have completed them to confirm if `Java 11` has been installed. <br><br>

2. Download the latest `guestbook.jar` from [here](https://github.com/AY2223S1-CS2103T-W16-1/tp/releases). <br> <br>
   ![Ui](images/DownloadInstruction.png) <br><br>
    <div style="page-break-after: always;"></div>

3. Copy the file to the folder you want to use as the **home folder** for your GuestBook. <br> <br>
   ![Ui](images/GuestBookIcon.png) <br><br>

4. Double-click on `guestbook.jar` to start the app.
   * Alternatively, if you wish to use the `Terminal` or `Command Prompt`, navigate to the **home folder** and enter
   `java -jar guestbook.jar` to start the app.

   The GUI, similar to the image below, should pop up in a few seconds. Note that the GuestBook
   comes prepackaged with **sample data**.<br><br>
   ![Ui](images/Ui.png) <br><br>

5. Type your commands in the command box and press Enter to execute it. E.g., typing **`help`** and pressing Enter will open the help window.<br><br>

6. To kickstart your journey, you can add a guest, _John Doe_, to GuestBook by using the **`add`** command.
   * `add n/John Doe p/98765432 e/johnd@example.com rm/05-73 dr/13/09/22 - 15/09/22 ng/3 rq/Extra towels`: Adds a guest named _John Doe_ to GuestBook along with the necessary information, such as his particulars.
<br><br>

7. To find _John Doe_, you can use the **`find`** command, and search through **any of _John Doe's_ fields**,
   such as his `name` or `room`. The command helps you find guests that match **any of the keywords** you entered. For example,
   * `find John Doe` : Finds all the guests who have at least one field containing _John_ or _Doe_ (case-insensitive).
     <br><br>

8. To make changes to _John Doe_, you can use the **`edit`** command. This changes the guest's fields based on the new values you provide. The other values will remain unchanged. <br>
For example, assume _John Doe_ is the second guest in the list. If you want to change his room number to _05-55_, you can execute the following command.
    * `edit 2 rm/05-55`: Changes the `Room` of _John Doe_ to _05-55_ in GuestBook.
      <br><br>

9. After executing a **`find`** command, you might realise that some of your guests are no longer visible, as they are not in the current list.<br>
To see a list of all your guests, you can use the **`list`** command.
      * `list`: List all the guests in GuestBook.
        <br><br>

10. You might want to charge a guest in some scenarios, such as when a guest orders room service. You can add charges to the guest's bill using the **`bill`** command.<br>
For example, you can charge the third guest _30.35_ by running the command below.
    * `bill 3 b/+30.35`: Adds _30.35_ to the `bill` of the third guest shown in the current list.
      <br><br>

11. When a guest checks out of your hotel, you can remove the guest using the **`delete`** command.
For example, you can check out the third guest by executing the command below.
    * `delete 3` : Deletes the third guest shown in the current list.
          <br><br>
    
12. Should you wish to clear your GuestBook of **all** entries, you can use the **`clear`** command.<br>
    * `clear` : Deletes all guests.
    <br><br>
    <div markdown="block" class="alert alert-danger">

    :bangbang: **Warning:**<br>
    * Do note that this command is **irreversible**. Once cleared, all the
      guest data cannot be retrieved. Please use this command
      with utmost caution.
    </div>
13. Finally, to quit GuestBook, you can run the **`exit`** command.
    * `exit` : Exits the app.
      <br><br>

14. We have come to the end of our Quick Start. For more details, you can refer to the [Features](#features) below for the exact specifications of each command. Thank you!
    <br><br>
[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Glossary

From this glossary section onwards, we will go into a deep dive of the commands of GuestBook. As there are
some technical terms present in the descriptions below, we have provided their definitions (contextualised
to GuestBook) for you to get acquainted with.

| Term             | Definition                                                                                                                                                      |
|------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Alphanumeric** | Refers to characters that are numbers **0-9** and alphabets **A-Z**, both uppercase and lowercase.                                                              |
| **dd/MM/yy**     | Refers to the date format, where dd stands for 2 digit days, 2 digit months and the last 2 digits for the year. They must be separated by a forward slash, "/". |
| **Guest**        | A guest staying at the hotel.                                                                                                                                   |
| **GUI**          | Stands for Graphical User Interface, it refers to an interface that allows users to interact with the system through friendly visuals.                          |
| **Hard disk**    | A data storage device used to store and retrieve data.                                                                                                          |
| **Index**        | The number that corresponds to the position of the Guest in the list. The index **must be a positive integer**, such as 1, 2 or 3.                              |
| **Java**         | A programming language used to run GuestBook.                                                                                                                   |
| **JSON file**    | Stands for JavaScript Object Notation. It refers to a file format to store data.                                                                                |

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Features

### Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

There are some conventions the commands follow. We have laid them out here in hopes that it will
aid you in understanding their significance.

* Words in `UPPER_CASE` are the **fields** to be supplied by you, the user.<br>
  E.g., In `add n/NAME`, `NAME` is a field which can be used as `add n/John Doe`.


* Items in square brackets are **optional**.<br>
  E.g., `n/NAME [rq/REQUEST]` can be used as `n/John Doe rq/Extra towels` or as `n/John Doe`.


* Fields can be supplied in **any order**.<br>
  E.g., If the command specifies `n/NAME p/PHONE`, `p/PHONE n/NAME` is also acceptable.


* If a field is expected only once but is specified multiple times in the command, only the **last occurrence** of the field will be considered.<br>
  E.g., If you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.


* Extraneous fields for commands that do not take in fields (such as `help`, `list`, `exit` and `clear`) will be **ignored**.<br>
  E.g., If the command specifies `help 123`, it will be interpreted as `help`.

</div>

#### Adding a guest: `add`

This command adds a guest to GuestBook.

This command is typically used to **check a guest in** to your hotel with their personal and room details.

**Format**: `add n/NAME p/PHONE e/EMAIL rm/ROOM dr/DATE_RANGE ng/NUMBER_OF_GUESTS [rq/REQUEST]`

<div style="page-break-after: always;"></div>

<div markdown="block" class="alert alert-info">

**:information_source: Additional information:**

* Refer to the [Field summary](#field-summary) for more information about the limitations
  of each field.
</div>

**Examples**:
* `add n/John Doe p/98765432 e/johnd@example.com rm/05-73 dr/19/05/22 - 24/05/22 ng/3` Adds the guest _John Doe_, with his details, to GuestBook.
* `add n/Betsy Crowe p/82297553 e/betsycrowe@example.com rm/Aloha5 dr/10/01/12 - 11/01/12 ng/1 rq/Extra towels`
Adds the guest _Betsy Crowe_ to GuestBook, with the `request` to have _extra towels in her room_.


[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

#### Editing a guest: `edit`

This command edits an existing guest in GuestBook.

This command is typically used to **amend a mistake** when keying in the details of the guest. Another common use of this
command is to **change the room clean status** of a guest's room's after it has been cleaned.

The guest specified at the `INDEX` is the guest you wish to edit in the current list. The existing values will be **updated** to the new values
you provide. The values that you **did not specify** to edit will remain **unchanged**.

**Format**: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [rm/ROOM] [dr/DATE_RANGE] [ng/NUMBER_OF_GUESTS] [rc/IS_ROOM_CLEAN] [rq/REQUEST]`

<div markdown="block" class="alert alert-info">

**:information_source: Additional information:**<br>

* Refer to the [Field summary](#field-summary) for more information about the limitations
  of each field.
</div>

<div style="page-break-after: always;"></div>

**Examples**:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the `phone number` and `email address` of the first guest to _91234567_ and _johndoe@example.com_ respectively.
*  `edit 2 n/Betsy Crower` Edits the `name` of the second guest to _Betsy Crower_.

<div markdown="block" class="alert alert-success">

**:bulb: Tip:**<br>

* You can efficiently locate the guests you want to edit by first searching for them
using the [find command](#locating-guests), and then editing them accordingly!
</div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

#### Billing a guest: `bill`

This command updates the bill of a guest in GuestBook. This command is typically used to **charge a guest** for services, such as room service.

This command updates the bill of the guest at the specified `INDEX` of the current list.
Depending on the sign (`+` or `-`) of your input, the existing bill value will be **increased**
or **decreased** respectively by the value you provide.

**Format**: `bill INDEX b/BILL`

<div markdown="block" class="alert alert-info">

**:information_source: Additional information:**<br>

* Note that the bill here refers to the **total additional charges incurred**
  by the guest during their stay at your hotel and should **not include the cost of the room booking**.
* Refer to the [Field summary](#field-summary) for more information about the limitations
  of each field.
</div>

**Examples**:
* `bill 1 b/+99.99` or `bill 1 b/99.99` Adds _99.99_ to the `bill` of the first guest.
* `bill 2 b/-10` Subtracts _10_ from the `bill` of the second guest.

<div markdown="block" class="alert alert-warning">

:triangular_flag_on_post: **Common mistakes:**<br>

* Missing out the `b/` prefix in the bill command.
* Editing the bill using the `edit` command instead of the `bill` command.
</div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

#### Locating guests: `find`

This command locates guests whose details contain any of the given keywords.
This allows you to search through all guests' fields.

This command is typically used when searching for guest(s) of **specific characteristic(s)**.
For example, the command `find no` will display the guests that have room clean statuses set to `no`. Likewise, the command `find yes`
will display the guests that already have their rooms cleaned.

**Format**: `find KEYWORD [MORE_KEYWORDS]`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about using `find`:**<br>
* The search is **case-insensitive**. E.g., _hans_ will match _Hans_.
* The order of the keywords does not matter. E.g., _Hans Bo_ will match _Bo Hans_.
* **Only full words** will be matched E.g., _Han_ will not match _Hans_.
* Guests matching **at least one** keyword will be returned (i.e., `OR` search).
  E.g., _Hans Bo_ will return _Hans Gruber_ and _Bo Yang_
</div>

**Examples**:
* `find John` returns _john_ and _John Doe_
* `find 01/01/23 - 07/01/23` returns _Alex Yeoh_ and _Bernice Yu_ (_Alex Yeoh_ was returned as the find command searches every individual keyword. As such, **"-" is also a keyword** and hence guests with no requests will be returned as well).
* `find alex 99272758 03-68` returns _Alex Yeoh_, _Bernice Yu_, _Charlotte Oliveiro_
(refer to the image below for the details of the guests)<br>
<br>

  ![result for 'find alex 99272758 03-68'](images/findAlex9927275803-68.png)

<div markdown="block" class="alert alert-warning">

:triangular_flag_on_post: **Common mistake:**<br>
* Searching for guests using incomplete keywords e.g. `170` will **not match** `170.00`.
</div>

<div markdown="block" class="alert alert-success">

**:bulb: Tip:**<br>

* To find guests based on their dates of stay, you should simply use the **start or end dates**, without the hyphen ("-").
</div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

#### Listing all guests: `list`

This command displays a list of all guests in GuestBook, which is akin to going back to the homepage of GuestBook.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:**<br>

* This command is especially useful if you would like to view the original guest list after
using the `find` command as a filter.
</div>

**Format**: `list`

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

#### Deleting a guest: `delete`

This command deletes an existing guest in GuestBook from the current list.

The guest specified at the `INDEX` is the guest you wish to delete.

This command is typically used to **check a guest out** of the hotel.

<div markdown="block" class="alert alert-danger">

:bangbang: **Warning:**<br>

* Do note that the `delete` command is **irreversible**. Once deleted, the specific
guest's data cannot be retrieved. Please use this
command with caution.
</div>

**Format**: `delete INDEX`

**Examples**:
* `list` followed by `delete 2` deletes the second guest in GuestBook.

* `find Betsy` followed by `delete 1` deletes the first guest based on the results of the `find` command.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

#### Marking all rooms as unclean: `markroomsunclean`

This command functions to mark the room clean statuses of all guests as `no`.

This command is typically used at the **end or the start** of the day when the hotel manager wishes to
update **all the room clean statuses** to `no` efficiently. This clean slate would allow the hotel
managers to keep track of the rooms that are cleaned or uncleaned for the day.

**Format**: `markroomsunclean`

<div markdown="block" class="alert alert-warning">

:triangular_flag_on_post: **Common mistakes:**<br>

* Missing out the `s`: `markroomunclean`.
* Capitalising the `R` or `U`: `markRoomsUnclean`.
</div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

#### Clearing all entries: `clear`

This command clears **all entries** from GuestBook.

This command is typically used as a **hard reset** of all guest data in GuestBook. You **should not** be using
this command unless you want to start the GuestBook on a **clean slate**.

<div markdown="block" class="alert alert-danger">

:bangbang: **Warning:**<br>

* Do note that this command is **irreversible**. Once cleared, all the
guest data cannot be retrieved. Please use this command
with utmost caution.
</div>

**Format**: `clear`

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

#### Viewing help: `help`

This command shows a message explaining how to access the **help page**, which is this user guide you are reading.

While using GuestBook, there may be instances where you would like to refer to this user guide. As such, to help
save time, we have provided quick access to this user guide by entering the `help` command.

![help message](images/helpMessage.png)

**Format**: `help`

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

#### Exiting the program: `exit`

This command exits the program.

This command is used once you have finished with the administrative tasks on GuestBook. Do not worry,
**all the data have already been saved by GuestBook** and there is no further action needed.

**Format**: `exit`

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Saving the data

GuestBook data is saved in the hard disk **automatically** after any command that changes the data. There is no need to save manually.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Editing the data file

GuestBook data is saved as a _JSON file_ `[JAR file location]/data/guestbook.json`.<br>
Advanced users may change data directly by editing this data file.

<div markdown="block" class="alert alert-warning">

:exclamation: **Caution:**<br>

* If your changes to the data file makes its format invalid, GuestBook will **discard all data** and start with an empty data file at the next run.
</div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## FAQ

Listed here are some frequently asked questions that we hope will be helpful for you should you encounter any problems. Should you require any other assistance, feel free to [contact us](#contact-us).

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and **overwrite the empty data file** it creates with the file that contains the data of your previous GuestBook home folder.

**Q**: Will my data be automatically saved?<br>
**A**: **Yes**, GuestBook automatically saves your data after every command entered.

**Q**: Where is my data saved?<br>
**A**: Go to where you have downloaded your `guestbook.jar` file, there a `/data` folder will be created and the data will be saved under `guestbook.json`.

**Q**: Can I rename my saved data file?<br>
**A**: **No**, GuestBook currently only supports the use of `guestbook.json` as the name of the saved data file.

**Q**: Why are there no guests in my GuestBook?<br>
**A**: If you didn't enter the `clear` command, it is likely that your `guestbook.json` file has been **corrupted**, causing GuestBook to reset it to prevent any malicious files from being used.

**Q**: Why am I getting error messages for fields that are correct, or that I'm not editing?<br>
**A**: If you are using the `add` or `edit` commands and inputting the `name` or `request` fields, ensure that they **do not contain any field prefixes** after whitespace in their content (e.g., `rq/Extra bed & p/c`), as GuestBook may mistake part of your input as another field. **Consider using capital letters** as this check is case-sensitive (e.g., `rq/Extra bed & P/C` will be handled correctly as part of a `request`). You may refer to [Field summary](#field-summary) for more information about field constraints.

**Q**: I cannot find a guest staying on a particular date even though it should be within the dates of stay. Why is that so?<br>
**A**: You may have entered a date **between** the start and end dates of the guest's stay (e.g, searching for `13/01/22 - 15/01/22` by entering `14/01/22`). Currently, this feature is **not supported** by GuestBook, and we are looking into implementing it in future iterations of GuestBook!

**Q**: Why am I not able to add guests to the same room even though they have different dates of stay?<br>
**A**: For this iteration of GuestBook, guests are added when they **check in** at the hotel and deleted when they **check out**. As such, there would not be different guests with the same room at any time. This feature is a safety check provided by GuestBook to ensure different guests are not allocated the same room during their stay. To ensure GuestBook remains simple and easy for you to use, each guest must have a **unique room** in GuestBook.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Field summary

The format and constraints of fields in GuestBook are listed in the table below.

In the case where GuestBook rejects your field inputs, you can refer to this section to use the right format.

<div markdown="block" class="alert alert-warning">

:exclamation: **Caution:**<br>

* If you are specifying the `name` or `request` fields, ensure that they do not contain any field prefixes after whitespace in their content
(e.g., `rq/Extra bed & p/c`), as GuestBook may mistake part of your input as another field.
**Consider using capital letters** as this check is case-sensitive (`rq/Extra bed & P/C` will be handled correctly as part of a `request`).
</div>

| Field                  | Corresponding prefix | Format/Constraints                                                                                                                                                                                                                                                                                                                                                                                      |
|------------------------|----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **`NAME`**             | `n/`                 | Should contain only **alphabets, spaces, and special characters (`'`, `-`, `.`, `,`, `/`)**.<br/><br/> :exclamation: **Caution:** <br> Names are case-sensitive and multiple consecutive spaces in-between them are allowed. GuestBook will not detect these names as the same.<br/>E.g. _John Doe_, _John&nbsp;&nbsp;&nbsp;Doe_ and _john doe_ will all be detected as different guests.               |
| **`PHONE`**            | `p/`                 | Should contain only **numbers**, and it should be **at least 3** digits and **at most 15** digits long.                                                                                                                                                                                                                                                                                                 |
| **`EMAIL`**            | `e/`                 | Should contain only **alphanumeric characters, 1 `@` character, and the following special characters (`+`, `_`, `.`, `-`)**.<br/>The local segment (before `@`) should be at most 64 characters long, and the domain segment (after `@`) should be at most 255 characters long.<br/>Segments cannot start or end with special characters, and special characters cannot be used adjacent to each other. |
| **`ROOM`**             | `rm/`                | Should contain only **alphanumeric characters and hyphens (`-`)**.<br/>A hyphen can only be added if it is in between 2 alphanumeric characters (This also means that it **should not start or end with a hyphen**).                                                                                                                                                                                    |
| **`DATE_RANGE`**       | `dr/`                | Should follow the format `dd/MM/yy - dd/MM/yy` with 2 valid dates and the second date should be later than the first date. There should only be **one spacing** before and after the hyphen(`-`).                                                                                                                                                                                                       |
| **`NUMBER_OF_GUESTS`** | `ng/`                | Should contain only **whole numbers**, and **only** contain values 1, 2, 3, or 4.                                                                                                                                                                                                                                                                                                                       |
| **`REQUEST`**          | `rq/`                | Is an **optional field**, and should be **at most 500** characters long.                                                                                                                                                                                                                                                                                                                                |
| **`IS_ROOM_CLEAN`**    | `rc/`                | Should be only `yes`, `no`, `y` or `n` (case-insensitive).                                                                                                                                                                                                                                                                                                                                              |
| **`BILL`**             | `b/`                 | Should contain only **numbers, optionally one decimal point (`.`), and optionally one leading sign (`+`/`-`)**.<br/>Can have up to 12 leading digits and 2 decimal places.<br/>Is **assumed to be positive if no leading sign** is used.                                                                                                                                                                |

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command summary

The format of commands in GuestBook are listed in the table below.

In the case where GuestBook rejects your command (`Invalid command format!`), you can refer to this section to find out the right command format.

| Action                 | Format, Examples                                                                                                                                                                               |
|------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                | `add n/NAME p/PHONE e/EMAIL rm/ROOM dr/DATE_RANGE ng/NUMBER_OF_GUESTS [rq/REQUEST]` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com rm/05-73 dr/19/05/20 - 24/05/22 ng/3`            |
| **Bill**               | `bill INDEX b/BILL`<br> e.g., `bill 2 b/99.99`                                                                                                                                                 |
| **Clear**              | `clear`                                                                                                                                                                                        |
| **Delete**             | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                            |
| **Edit**               | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [rm/ROOM] [dr/DATE_RANGE] [ng/NUMBER_OF_GUESTS] [rc/IS_ROOM_CLEAN] [rq/REQUEST]`<br> e.g.,`edit 2 e/jameslee@example.com dr/13/09/22 - 15/09/22 ng/4` |
| **Exit**               | `exit`                                                                                                                                                                                         |
| **Find**               | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                     |
| **Help**               | `help`                                                                                                                                                                                         |
| **List**               | `list`                                                                                                                                                                                         |
| **Mark Rooms Unclean** | `markroomsunclean`                                                                                                                                                                             | 

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Contact Us
For enquiries or feedback, feel free to reach us at [guestbook.enquiries@gmail.com](mailto:guestbook.enquiries@gmail.com), and we will get back to you as soon as possible!

[Return to Table of Contents](#table-of-contents)

