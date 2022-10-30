---
layout: page
title: User Guide
---

FindMyIntern helps students who are applying for internships keep track of their applications. It allows students to consolidate all these applications into a single place, manage these applications, and visualise their application statuses.
## Table of Contents

The user guide is divided into different sections. Click on any of the sections to jump right into that section!
<div>
<div markdown="1">
<h6 markdown="1">[Quick Start](#quick-start)</h6>
This section teaches you how to install and **get started** with FindMyIntern.
</div>
</div>

<div>
<div markdown="1">
<h6 markdown="1">[Features](#features)</h6>
This section contains **everything you need to know** about the features in FindMyIntern.
It includes:
  - [Commands](#commands)
  - [Saving and Editing](#saving-and-editing)
  - [UI Elements](#ui-elements)
</div>
</div>

<div>
<div markdown="1">
<h6 markdown="1">[FAQ](#faq)</h6>
This section includes all frequently asked questions about FindMyIntern.
</div>
</div>

<div>
<div markdown="1">
<h6 markdown="1">[Appendix](#appendix)</h6>
This section specifies **the input details** that you have to be aware of in FindMyIntern.
</div>
</div>

<div>
<div markdown="1">
<h6 markdown="1">[Command Summary](#command-summary)</h6>
This section contains the formats of all commands in FindMyIntern.
</div>
</div>

--------------------------------------------------------------------------------------------------------------------

## Quick Start

### Setting up

1. Ensure you have [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) or above installed in your Computer.

2. Download the latest `findmyintern.jar` from [here](https://github.com/AY2223S1-CS2103T-T14-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your FindMyIntern.

4. Double-click the file to start the app. 

### Adding your first internship application

Now that you've downloaded FindMyIntern, you can start managing your internship applications. Let's start by adding your first internship application.

1. Open FindMyIntern by double-clicking `findmyintern.jar` if you have not already done so.

2. The GUI similar to the below should appear in a few seconds. FindMyIntern will load some sample data.<br>

   <p align="center">
       <img src="images/Ui.png" width="600" />
   </p> 

3. Let's add your first internship application with the [`add` command](#adding-an-internship-application-add)!

4. Type `add c/Shopee l/careers.shopee.sg d/My first internship application a/27 Oct` and press Enter to execute it.

5. Congratulations! You have just added your first internship application to FindMyIntern! A message like the one below showing the result of your command should appear.

  <p align="center">
       <img src="images/firstInternshipApplication.png" />
  </p>

6. FindMyIntern offers so much more in managing your internship applications. Check out the [commands section](#commands) for more commands you can use.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Command format**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user<br>
  * e.g. in `add c/COMPANY`, `COMPANY` is a parameter which can be used as `add c/Google`

* Items in square brackets are optional<br>
  * e.g. `c/COMPANY [t/TAG]` can be used as `c/Google t/backend` or as `c/Google`

* Items with `…` after them can be used multiple times<br>
  * e.g. `[t/TAG]…` can be used as `t/backend`, `t/backend t/summer`, etc.

* Parameters can be in any order<br>
  * e.g. if the command specifies `d/DESCRIPTION l/LINK`, `l/LINK d/DESCRIPTION` is also acceptable

* If a parameter is expected only once in the command but is specified multiple times, only the last occurrence of the parameter will be taken<br>
  * e.g. if `c/Google c/TikTok` is entered, only `c/TikTok` will be taken

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`) will be ignored<br>
  * e.g. if `help 123` is entered, it will be interpreted as `help`

</div>

### Commands

This section contains **everything you need to know** about the commands used in FindMyIntern. Click on any of the commands to jump straight into it.

- [Viewing help: `help`](#viewing-help-help)
- [Adding an internship application: `add`](#adding-an-internship-application-add)
- [Editing an internship application: `edit`](#editing-an-internship-application-edit)
- [Marking an internship application status: `mark`](#marking-an-internship-application-status-mark)
- [Listing all internship applications: `list`](#listing-all-internship-applications-list)
- [Deleting an internship application: `delete`](#deleting-an-internship-application-delete)
- [Filtering for internship applications of a specific status: `filter`](#filtering-for-internship-applications-of-a-specific-status-filter)
- [Finding for an internship application: `find`](#finding-for-internship-applications-or-tags-find)
- [Sorting the internship applications: `sort`](#sorting-the-list-of-internship-applications-sort)
- [Clearing all internship applications: `clear`](#clearing-all-internship-applications-clear)
- [Exiting the program: `exit`](#exiting-the-program-exit)

#### Viewing help: `help`

Shows a message explaining how to access the user guide, and the data file location.

<p align="center">
    <img src="images/helpMessage.png" width="500"/>
</p>

Format: `help`

#### Adding an internship application: `add`

Adds an internship application to the tracker.

Format: `add c/COMPANY l/LINK d/DESCRIPTION a/APPLIED_DATE [i/INTERVIEW_DATE_TIME] [t/TAG]…`

* Default [application status](#application-status) is "Applied".
* `INTERVIEW_DATE_TIME` is optional, but if included, will automatically change [application status](#application-status) to "Shortlisted".
* `TAG` is optional, but can be used multiple times.
* `TAG` can be used to attach keywords to an internship application which may help in your internship application tracking.
  * Such keywords may include `backend` or `frontend` for the internship role, or `summer` or `off season` for the internship period.
* See [Appendix: Field constraints](#field-constraints) for constraints on fields such as `LINK` and `TAG`.

<div class="d-flex alert alert-info pb-0">
<div class="me-2">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**Changing application status**

To change [application status](#application-status), see [`mark` command](#marking-an-internship-application-status-mark).
</div>
</div>

<div class="d-flex alert alert-warning pb-0">
<div class="me-2">
  <span class="badge text-bg-warning">WARNING</span>
</div>
<div markdown="span">
**Date/time formats**
See [Appendix: Date/time formats](#datetime-formats) for the date/time formats allowed.
</div>
</div>

Examples:
* `add c/Apple l/https://jobs.apple.com/en-sg d/Software engineering internship a/11/10/2022`
* `add c/Grab l/https://grab.careers/teams/campus d/Marketing internship a/11 Oct i/23 Nov, 3:00 pm`
* `add c/Shopee l/careers.shopee.sg/students d/Data analyst internship a/20 Oct 2022 i/28 Nov 16:00 t/data analyst t/summer`

#### Editing an internship application: `edit`

Edits an existing internship application in the tracker.

Format: `edit INDEX [c/COMPANY] [l/LINK] [d/DESCRIPTION] [a/APPLIED_DATE] [i/INTERVIEW_DATE_TIME] [t/TAG]…`

* Edits the internship application at the specified `INDEX`. The index refers to the index number shown in the displayed
  internship application list. The index **must be a positive integer** 1, 2, 3…

* At least one of the optional fields must be provided.

* When editing `INTERVIEW_DATE_TIME`, the [application status](#application-status) will change to "Shortlisted".

* When editing tags, the existing tags will be removed and replaced with the input tags.

* Tags can be removed by typing `t/` without specifying any tags after it.

* See [Appendix: Field constraints](#field-constraints) for constraints on fields such as `LINK` and `TAG`.


<div class="d-flex alert alert-info pb-0">
<div class="me-2">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**Changing application status**

To change [application status](#application-status), see [`mark` command](#marking-an-internship-application-status-mark).
</div>
</div>

<div class="d-flex alert alert-warning pb-0">
<div class="me-2">
  <span class="badge text-bg-warning">WARNING</span>
</div>
<div markdown="span">
**Date/time formats**
See [Appendix: Date/time formats](#datetime-formats) for the date/time formats allowed.
</div>
</div>

Examples:
* `edit 1 d/Data analyst intern` - Edits the description of the 1st internship application
  in the list to `Data analyst intern`.
* `edit 2 i/23/11/2022 15:00` - Edits the interview date/time of the 2nd internship application
  to `23 Nov 2022, 3:00 PM`, and changes the [application status](#application-status) to "Shortlisted".
* `edit 3 t/frontend t/summer` - Edits the tags of the 3rd internship application, removing existing tags and
  adding `frontend` and `summer` tags

#### Marking an internship application status: `mark` 

Marks an internship [application status](#application-status).

Format: `mark INDEX s/APPLICATION_STATUS`

* Updates the internship application at the specified `INDEX`. The index refers to the index number shown in the displayed 
  internship application list. The index **must be a positive integer** 1, 2, 3…

* Updates the internship application to the specified [`APPLICATION_STATUS`](#application-status).

* [`APPLICATION_STATUS`](#application-status) is case-insensitive.


Examples:
* `mark 3 s/interviewed` - Marks the 3rd internship [application status](#application-status) as `interviewed`
* `mark 2 s/Accepted` - Marks the 2nd internship [application status](#application-status) as `accepted`
* `mark 4 s/rejecteD` - Marks the 4th internship [application status](#application-status) as `rejected`

#### Listing all internship applications: `list`

Shows a list of all internship applications.

Format: `list`

#### Deleting an internship application: `delete`

Deletes an internship application.

Format: `delete INDEX`

* Deletes the internship application at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed internship application list.
* The index **must be a positive integer** 1, 2, 3…

Examples:
*  `delete 4` - Deletes the 4th internship application in the list.

#### Filtering for internship applications of a specific status: `filter`

Format: `filter APPLICATION_STATUS`

* Filters for internship applications of the specified [`APPLICATION_STATUS`](#application-status).

* [`APPLICATION_STATUS`](#application-status) is case-insensitive.

Examples:
* `filter accepted` - Shows a list of internship applications marked as `accepted`
* `filter Rejected` - Shows a list of internship applications marked as `rejected`

#### Finding for internship applications or tags: `find`

Finds internship applications where the company's name and tags contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]…`

* The search is case-insensitive e.g `google` will match `Google`
* Partial words will be matched e.g. `goo` will match an application where the company's name or tag contains the word `goo` 

<div class="d-flex alert alert-info pb-0">
<div class="me-2">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**`filter` vs `find`**

`filter` and `find` commands may seem similar, here are some differences to take note of:

| `filter`                                                                                  | `find`                                                    |
|-------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| Used to filter **internship application statuses**                                        | Used to search for **keywords in company names and tags** |
| Can only accept as input **one of 5 allowed [application statuses](#application-status)** | Can accept **any input**                                  |

</div>
</div>

Examples:
* `find tiktok` returns internship application(s) and tag(s) containing `tiktok`
* `find google backend` returns internship application(s) and tag(s) containing `google` and `backend`

<p align="center">
    <img src="images/findGoogleBackendResult.png" width="600" />
</p>

#### Sorting the list of internship applications: `sort`

Sorts the internship applications with the latest date at top to earliest at the bottom.

Format: `sort CRITERIA`

* Sort criteria **must be one of the following**:
    * `applied`
    * `interview`

Examples:
* `sort applied` will sort the internship applications according to applied date
* `sort interview` will sort the internship applications according to interview date

<div class="d-flex alert alert-info pb-0">
<div class="me-2">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**:information_source: Sorting by interview date**

Applications without interview date will remain in the original order at the bottom.
</div>
</div>

#### Clearing all internship applications: `clear`

Clears all internship applications from the tracker.

Format: `clear`

<div class="d-flex alert alert-danger pb-0">
<div class="me-2">
  <span class="badge text-bg-danger">DANGER</span>
</div>
<div markdown="span">
This command will clear the entire list of internships without warning.
</div>
</div>

#### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving and Editing

#### Saving the data

FindMyIntern's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data

FindMyIntern's data is saved as a JSON file `[JAR file location]/data/findmyintern.json`.
The data file location can also be found in the help message: see [`help` command](#viewing-help-help).

Advanced users are welcome to update data directly by editing that data file.

<div class="d-flex alert alert-warning pb-0">
<div class="me-2">
  <span class="badge text-bg-warning">WARNING</span>
</div>
<div markdown="span">
If your changes to the data file makes its format invalid, FindMyIntern will discard all data and start with an empty data file at the next run.
</div>
</div>

### UI elements

#### Links

Clicking on links will copy the URL of the link to the clipboard. The URL can then be pasted into any browser. 

A tooltip is shown when the link is hovered.

<p align="center">
    <img src="images/linkTooltip.png" />
</p>

#### Tags

A tooltip containing the full tag name is shown when a tag is hovered.

The maximum number of tags that will be displayed is 5. Additional tags that are not displayed will be shown as a count.
A tooltip containing the additional tags is shown when the count is hovered.

<p align="center">
    <img src="images/additionalTagsTooltip.png" />
</p>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FindMyIntern home folder.

--------------------------------------------------------------------------------------------------------------------

## Appendix

This section specifies **the input details** that you have to be aware of in FindMyIntern.

- [Field Constraints](#field-constraints)
- [Application Status](#application-status)
- [Date/Time Formats](#datetime-formats)

### Field constraints

Commands like `add` and `edit` accept fields, their constraints are specified below.

There are no constraints for a field if the constraints box is empty for that field.

| Field                   | Constraints                                                                                    |
|-------------------------|------------------------------------------------------------------------------------------------|
| `c/COMPANY`             |                                                                                                |
| `l/LINK`                | Links must be of the format `HOSTNAME.PATH`, where both `HOSTNAME` and `PATH` are alphanumeric |
| `d/DESCRIPTION`         |                                                                                                |
| `a/APPLIED_DATE`        | See [date/time formats](#datetime-formats)                                                     |
| `i/INTERVIEW_DATE_TIME` | See [date/time formats](#datetime-formats)                                                     |
| `t/TAG`                 | Must only contain alphabets and spaces                                                         |

### Application status

Application status refers to the current status of an internship application. 
Each internship application is tagged with an application status.

Application status as an input field **must be one of the following**:

| Application status | Explanation                                               | Remark                                                                                                              |
|--------------------|-----------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| `applied`          | You have applied for this internship                      | Default application status                                                                                          |
| `shortlisted`      | Company has shortlisted you for interview                 | Adding/editing the interview date/time of an internship application will change application status to "Shortlisted" |
| `interviewed`      | You have been interviewed for this internship application |                                                                                                                     |                                      
| `accepted`         | You have accepted this internship                         |                                                                                                                     |
| `rejected`         | You have rejected this internship                         |                                                                                                                     |

### Date/time formats

The year can be omitted from `APPLIED_DATE` and `INTERVIEW_DATE_TIME` to default to current year.

For `APPLIED_DATE`, the formats allowed:

| Format       | Input      |
|--------------|------------|
| `d MMM yyyy` | 7 Oct 2022 |
| `d/M/yyyy`   | 7/10/2022  |

For `INTERVIEW_DATE_TIME`, the formats allowed:

| Format               | Input               | Remark                                                                                 |
|----------------------|---------------------|----------------------------------------------------------------------------------------|
| `d MMM yyyy HH:mm`   | 7 Oct 2022 15:00    |                                                                                        |
| `d/M/yyyy HH:mm`     | 7/10/2022 15:00     |                                                                                        |
| `d MMM yyyy, h:mm a` | 7 Oct 2022, 3:00 pm | Take note of the **comma after the date** and the **space between the time and AM/PM** |
| `d/M/yyyy, h:mm a`   | 7/10/2022, 3:00 pm  | Take note of the **comma after the date** and the **space between the time and AM/PM** |

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add c/COMPANY l/LINK d/DESCRIPTION a/APPLIED_DATE [i/INTERVIEW_DATE_TIME] [t/TAG]…` <br> e.g., `add c/Apple l/https://jobs.apple.com/en-sg d/Software engineering internship a/11/10/2022`
**Edit** | `edit INDEX [c/COMPANY] [l/LINK] [d/DESCRIPTION] [a/APPLIED_DATE] [i/INTERVIEW_DATE_TIME] [t/TAG]…` <br> e.g., `edit 1 d/Data analyst intern`
**Mark** | `mark INDEX s/APPLICATION_STATUS` <br> e.g., `mark 3 s/interviewed`
**List** | `list`
**Delete** | `delete INDEX` <br> e.g., `delete 4`
**Filter** | `filter APPLICATION_STATUS` <br> e.g., `filter accepted`
**Find** | `find KEYWORD [MORE_KEYWORDS]…`<br> e.g., `find google`
**Sort** | `sort CRITERIA`<br> e.g., `sort applied`
**Clear** | `clear`
**Exit** | `exit`
