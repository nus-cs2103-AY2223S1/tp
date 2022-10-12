---
layout: page
title: TrackAScholar User Guide (v1.2)
---

TrackAScholar (TAS) is a **desktop app for managing scholarship applications,
optimised for use via a Command Line Interface (CLI)** while still having
the benefits of a Graphical User Interface (GUI). If you can type fast,
TAS can get your scholarship applicant management tasks done faster than traditional GUI apps.

* [Quick start](#quick-start)
* [Features](#features)
  * [Viewing help : help](#viewing-help--help)
  * [Adding a scholarship application: add](#adding-a-scholarship-application-add)
  * [Listing all scholarship application : list](#listing-all-scholarship-application--list)
  * [Editing an existing scholarship application : edit](#editing-an-existing-scholarship-application--edit)
  * [Locating application status by name: find](#locating-application-status-by-name--find)
  * [Deleting a scholarship application : delete](#deleting-a-scholarship-application--delete)
  * [Clearing all entries : clear](#clearing-all-entries--clear)
  * [Exiting the program : exit](#exiting-the-program--exit)
* [Further details](#further-details)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
  * [Archiving data files](#archiving-data-files-coming-in-v20)
* [Command summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Install Java `11` or above. Check [here](https://nus-cs2103-ay2223s1.github.io/website/admin/programmingLanguages.html#programming-language).

1. Download the latest `TrackAScholar.jar`. Check [here](https://github.com/AY2223S1-CS2103T-W10-3/tp/releases).

1. Move the jar file to an empty folder

1.  Double-click the jar file to open the app. If the app does not respond, open your terminal in the current folder and enter `java -jar TrackAScholar.jar` to open the app.

1. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   ![Ui](images/Ui.png)

1. Refer to the [Features](#features) below for details of each command.

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

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a scholarship application: `add`

Adds a scholarship application to the TrackAScholar.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An applicant can have any number of tags (including 0)
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
It is necessary for a scholarship application to have both an applicant’s name and email, while the other fields are optional.
</div>

Examples:
* `add n/John Cena e/johnCena@yahoo.com`
* `add n/Samuel Cheong t/international student e/samuelcheong1234@gmail.com p/65782310 s/Merit as/pending`

### Listing all scholarship application : `list`

Shows a list of all scholarship applications stored in TrackAScholar

Format: `list`

### Editing an existing scholarship application : `edit`

Edits an existing scholarship application stored in TrackAScholar

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]…​`

* Edits the scholarship application at the specified `INDEX`. The index refers to the index number shown in the displayed scholarship application list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the scholarship application will be removed i.e adding of tags is not cumulative.
* You can remove all the scholarship application’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 n/John Cena e/johnCena@yahoo.com` Edits the name and email address of the 1st scholarship application to be `John Cena` and `johnCena@yahoo.com` respectively.

*  `edit 2 n/Samuel Cheong t/` Edits the name of the 2nd applicant to be `Samuel Cheong` and clears all existing tags.

### Locating applicants by name : `find`

Finds scholarship applicants whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Applicant matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`

### Filtering scholarship applicants by application status: `filter`

Filters scholarship applications based on the given keywords.

Format: `filter KEYWORD`

* The search is case-insensitive. e.g `pEnDiNg` will match `pending`
* Only full words will be matched e.g. `pen` will not match `pending`
* Only 3 keywords are accepted i.e. `pending`, `accepted`, `rejected`

Examples:
* `filter pending` displays all applicants with the application status `pending`


### Deleting a scholarship application : `delete`

Deletes the specified scholarship application from TrackAScholar

Format: `delete INDEX`

* Deletes the scholarship application at the specified `INDEX`.
* The index refers to the index number shown in the displayed applicant list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd applicant in TrackAScholar.

### Clearing all entries : `clear`

Clears all entries from data stored in TrackAScholar

Format: `clear`

### Exiting the program : `exit`

Exits TrackAScholar.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------
## Further details

### Saving the data

TrackAScholar data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TrackAScholar data are saved as a JSON file `[JAR file location]/data/trackAScholar.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TrackAScholar will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------


## Command summary

| Action     | Format, Examples                                                |
|------------|-----------------------------------------------------------------|
| **Add**    | `add n/NAME e/EMAIL [p/PHONE_NUMBER] [s/SCHOLARSHIP] [t/TAG] …` |
| **Clear**  | `clear`                                                         |
| **Delete** | `delete INDEX`                                                  |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]…​`      |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`                                  |
| **filter** | `filter KEYWORD`                                                |
| **List**   | `list`                                                          |
| **Help**   | `help`                                                          |
| **Exit**   | `exit`                                                          |
