---
layout: page
title: User Guide
---

## Welcome to FindMyIntern!

The purpose of this user guide is to help both new and experienced users with using FindMyIntern.

- If you are a new user, we recommend checking out the [introduction](#introduction) to get a better idea of what FindMyIntern can do.
We also recommend going through the [special formats](#special-formats) used in this user guide to understand what they mean.
If you have no prior experience with command line interfaces, we recommend reading this [guide](#command-line-guide).

- If you are an experienced user, you can use the [table of contents](#table-of-contents) to jump straight to what you are looking for.

--------------------------------------------------------------------------------------------------------------------

## Introduction
Are you a student applying for internships? Do you have trouble remembering what you have applied? 
Or is using spreadsheets too troublesome for you? FindMyIntern is the perfect solution for you!

FindMyIntern is a desktop application that helps you easily keep track of
your internship applications.

FindMyIntern helps you to
- Consolidate all applications into a single place
- Find particular internship applications
- Easily view applications with different statuses

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Table of Contents

The user guide is divided into different sections. Click on any of the sections to jump right into that section!

<div class="card mb-2">
<div markdown="1" class="card-body py-0">
<h6 markdown="1" class="card-title">[Special formats](#special-formats)</h6>
This section explains the special formats that are used in this user guide.
</div>
</div>

<div class="card mb-2">
<div markdown="1" class="card-body py-0">
<h6 markdown="1" class="card-title">[Command line guide](#command-line-guide)</h6>
This section teaches you how to use the command line.
</div>
</div>

<div class="card mb-2">
<div markdown="1" class="card-body py-0">
<h6 markdown="1" class="card-title">[Quick Start](#quick-start)</h6>
This section teaches you how to install and **get started** with FindMyIntern.
</div>
</div>

<div class="card mb-2">
<div markdown="1" class="card-body py-0">
<h6 markdown="1" class="card-title">[Features](#features)</h6>
This section contains **everything you need to know** about the features in FindMyIntern.

It includes:
- [Commands](#commands)
- [Saving and Editing](#saving-and-editing)
- [UI Elements](#ui-elements)
</div>
</div>

<div class="card mb-2">
<div markdown="1" class="card-body py-0">
<h6 markdown="1" class="card-title">[FAQ](#faq)</h6>
This section includes all frequently asked questions about FindMyIntern.
</div>
</div>

<div class="card mb-2">
<div markdown="1" class="card-body py-0">
<h6 markdown="1" class="card-title">[Appendix](#appendix)</h6>
This section specifies **the input details** that you have to be aware of in FindMyIntern.
</div>
</div>

<div class="card mb-2">
<div markdown="1" class="card-body py-0">
<h6 markdown="1" class="card-title">[Command Summary](#command-summary)</h6>
This section contains the formats of all commands in FindMyIntern.
</div>
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Special formats

Here are some special formats to take note of when reading this user guide:

<div class="d-flex alert alert-info">
    <div class="me-3">
      <span class="badge text-bg-info">INFO</span>
    </div>
    <div markdown="span">
        Additional information that is useful to know.
    </div>
</div>

<div class="d-flex alert alert-warning">
    <div class="me-3">
      <span class="badge text-bg-warning">WARNING</span>
    </div>
    <div markdown="span">
        Important information to ensure that fields are added correctly.
    </div>
</div>

<div class="d-flex alert alert-danger">
    <div class="me-3">
      <span class="badge text-bg-danger">DANGER</span>
    </div>
    <div markdown="span">
        Critical information to take note of to avoid any loss of data.
    </div>
</div>


<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        Represents the format for each command.
    </div>
</div>

`This code block denotes commands, parameters, user inputs or file names.`

--------------------------------------------------------------------------------------------------------------------

## Command Line Guide

   <p align="center">
       <img src="images/CommandLineGuide.png" width="750" />
   </p>

Simply type in:
1. The command. 
    * Example: `add` represents the `add` command.
2. The prefix indicating a field.
    * Example: The prefix `c/` indicates the `company` field.
3. The field to be added or updated.
    * Enter directly after the prefix.
    * Example: For `c/TikTok`, `Tiktok` represents the actual `company` field to be added.
4. Repeat steps 2 and 3 to add or update more fields in a single command.

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick Start

### Setting up

1. Ensure you have **Java 11** or above installed in your Computer: 
   1. Open up the command prompt/Terminal in your respective OS:
      * For <i class="bi bi-microsoft ms-1 me-2" style="font-size: .9rem"></i>**Windows**: <br>
         Open up the command prompt by pressing the **Windows** key <code><i class="bi bi-microsoft"></i></code> and searching **Command Prompt**.
      * For <i class="bi bi-apple ms-1 me-2"></i>**Mac**: <br>
        Click on **Finder**, then open the `/Application/Utilities` folder by pressing <br>**Command + Shift + U** <code><i class="bi bi-command"></i> + <i class="bi bi-shift"></i> + U</code>, and finally open the **Terminal** application.
      * For <i class="bi bi-ubuntu ms-1 me-2"></i>**Linux**: <br>
         Open up the command prompt by pressing **Ctrl + Alt + T**.
   2. Once the command prompt/Terminal is opened, type in `java -version`.
   3. It should look similar to this:

        <p align="center">
            <img src="images/javaVersion.png" />
        </p>

   4. If your version starts with `"11.*.*.*"` where `*` represents any number, you are good to go!
   5. Otherwise, you can download Java 11 [here](https://www.oracle.com/java/technologies/downloads/#java11).<br><br> 

2. Download the latest `findmyintern.jar` from [here](https://github.com/AY2223S1-CS2103T-T14-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your FindMyIntern.

4. Double-click the file to start the application.

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

### Adding your first internship application

Now that you've downloaded FindMyIntern, you can start managing your internship applications. Let's start by adding your first internship application.

1. Open FindMyIntern by double-clicking `findmyintern.jar` if you have not already done so.

2. The GUI (Graphical User Interface) similar to the below should appear in a few seconds. FindMyIntern will load some sample data.<br>
   <p align="center">
       <img id="annotatedUi" src="images/AnnotatedUi.png" />
   </p>

3. Let's add your first internship application with the [`add` command](#adding-an-internship-application-add)!

4. Type `add c/Shopee l/careers.shopee.sg d/My first internship application a/27 Oct` and press Enter to execute it.
   <div style="page-break-after: always;"></div><br>

5. Congratulations! You have just added your first internship application to FindMyIntern! A message like the one below showing the result of your command should appear in the result box.
   
   <p align="center">
      <img src="images/firstInternshipApplication.png" />
   </p>
   
   If you would like to clear the sample data, use the [`clear` command](#clearing-all-internship-applications-clear).

6. FindMyIntern offers so much more in managing your internship applications. Check out the [commands section](#commands) for more commands you can use.

<div class="btn-group btn-group-sm mt-2 mb-4">
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features

FindMyIntern has many features and commands available. Before using the commands, here are some things to take note of.

<div class="d-flex alert alert-info">
<div class="me-3">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="1">
**Command format**<br>
This section contains **everything you need to know** about the commands used in FindMyIntern.

Below shows the command format used in FindMyIntern.

* Words in `UPPER_CASE` are the parameters to be supplied by you<br>
  * e.g. in `add c/COMPANY`, `COMPANY` is a parameter which can be used as `add c/Google`

* Items in square brackets are optional<br>
  * e.g. `c/COMPANY [t/TAG]` can be used as `c/Google t/backend` or as `c/Google`

* Items with `…` after them can be used multiple times<br>
  * e.g. `[t/TAG]…` can be used as `t/backend`, `t/backend t/summer`, etc.

* Parameters can be in any order except for `INDEX`<br>
  * e.g. if the command specifies `d/DESCRIPTION l/LINK`, `l/LINK d/DESCRIPTION` is also acceptable

* If a parameter is expected only once in the command but is specified multiple times, only the last occurrence of the parameter will be taken<br>
  * e.g. if `c/Google c/TikTok` is entered, only `c/TikTok` will be taken
  * However, this will not work for `INDEX`. e.g. `delete 1 2` will show an error instead

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`) will be ignored<br>
  * e.g. if `help 123` is entered, it will be interpreted as `help`
</div>
</div>
<div style="page-break-after: always;"></div>

### Commands

To navigate to a specific command, you can just click on any of the commands to jump straight into it.

- [Adding an internship application: `add`](#adding-an-internship-application-add)
- [Editing an internship application: `edit`](#editing-an-internship-application-edit)
- [Marking an internship application status: `mark`](#marking-an-internship-application-status-mark)
- [Listing all internship applications: `list`](#listing-all-internship-applications-list)
- [Deleting an internship application: `delete`](#deleting-an-internship-application-delete)
- [Finding for an internship application: `find`](#finding-for-internship-applications-or-tags-find)
- [Filtering for internship applications of a specific status: `filter`](#filtering-for-internship-applications-of-a-specific-status-filter)
- [Sorting the internship applications: `sort`](#sorting-the-list-of-internship-applications-sort)
- [Clearing all internship applications: `clear`](#clearing-all-internship-applications-clear)
- [Viewing help: `help`](#viewing-help-help)
- [Exiting the program: `exit`](#exiting-the-program-exit)

<div style="page-break-after: always;"></div>

#### Adding an internship application: `add`

Adds an internship application to FindMyIntern. Using this command, you can easily add and view the details of your application in the [displayed internship application list](#annotatedUi).

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        add c/COMPANY l/LINK d/DESCRIPTION a/APPLIED_DATE [i/INTERVIEW_DATE_TIME] [t/TAG]…
        ```
    </div>
</div>

* Default [application status](#application-status) is "Applied".
* `INTERVIEW_DATE_TIME` is optional, but if included, will automatically change application status to "Shortlisted".
* `TAG` is optional, but can be used multiple times.
* `TAG` can be used to attach keywords to an internship application which may help in your internship application tracking.
  * There are no restrictions on what can be a `TAG`, other than containing only alphabets and spaces.
* See [Appendix: Field constraints](#field-constraints) for constraints on fields such as `LINK` and `TAG`.

<div class="d-flex alert alert-info mb-1">
<div class="me-3">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**Multiple internship applications under the same company**<br>
To add multiple internship applications under the same company, simply provide a different `TAG` to it.<br>

Two internship applications are considered as **duplicates** if both their `COMPANY` and `TAG` are identical. 
</div>
</div>

<div class="d-flex alert alert-info">
<div class="me-3">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**Changing application status**<br>
To change [application status](#application-status), see [`mark` command](#marking-an-internship-application-status-mark).
</div>
</div>

<div class="d-flex alert alert-warning mb-1">
<div class="me-3">
  <span class="badge text-bg-warning">WARNING</span>
</div>
<div markdown="span">
**Link formats**<br>
See [Appendix: Link formats](#link-formats) for the link formats allowed. Not following these formats will result in an error.
</div>
</div>

<div class="d-flex alert alert-warning">
<div class="me-3">
  <span class="badge text-bg-warning">WARNING</span>
</div>
<div markdown="span">
**Date/time formats**<br>
See [Appendix: Date/time formats](#datetime-formats) for the date/time formats allowed. Not following these formats will result in an error.
</div>
</div>
<div style="page-break-after: always;"></div>
Examples:
* `add c/Apple l/https://jobs.apple.com/en-sg d/Software engineering internship a/11/10/2022` - Adds an internship application for `Apple` into the list 
* `add c/Grab l/https://grab.careers/teams/campus d/Marketing internship a/11 Oct i/23 Nov, 3:00 pm` - Adds an internship application for `Grab` into the list
* `add c/Shopee l/careers.shopee.sg/students d/Data analyst internship a/20 Oct 2022 i/28 Nov 16:00 t/data analyst t/summer` - Adds an internship application for `Shopee` into the list

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

#### Editing an internship application: `edit`

Edits an existing internship application in FindMyIntern. Using this command, you can easily change any details if you have accidentally mistyped some information.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        edit INDEX [c/COMPANY] [l/LINK] [d/DESCRIPTION] [a/APPLIED_DATE] [i/INTERVIEW_DATE_TIME] [t/TAG]…
        ```
    </div>
</div>

* Edits the internship application at the specified `INDEX`. The index **must be a positive integer** 1, 2, 3…
  *  The index refers to the index number shown in the current [displayed internship application list](#annotatedUi).
* At least one of the optional fields must be provided.
* When editing `INTERVIEW_DATE_TIME`, the [application status](#application-status) will change to "Shortlisted".
* When editing tags, the existing tags will be removed and replaced with the input tags.
* Tags can be removed by typing `t/` without specifying any tags after it.
* Editing an internship application is only allowed if it does not result in duplicates. Two internship applications are considered as **duplicates** if both their `COMPANY` and `TAG` are identical.
* See [Appendix: Field constraints](#field-constraints) for constraints on fields such as `LINK` and `TAG`.


<div class="d-flex alert alert-info">
<div class="me-3">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**Changing application status**<br>
To change [application status](#application-status), see [`mark` command](#marking-an-internship-application-status-mark).
</div>
</div>

<div class="d-flex alert alert-warning mb-1">
<div class="me-3">
  <span class="badge text-bg-warning">WARNING</span>
</div>
<div markdown="span">
**Link formats**<br>
See [Appendix: Link formats](#link-formats) for the link formats allowed. Not following these formats will result in an error.
</div>
</div>

<div class="d-flex alert alert-warning">
<div class="me-3">
  <span class="badge text-bg-warning">WARNING</span>
</div>
<div markdown="span">
**Date/time formats**<br>
See [Appendix: Date/time formats](#datetime-formats) for the date/time formats allowed. Not following these formats will result in an error.
</div>
</div>

Examples:
* `edit 1 d/Data analyst intern` - Edits the description of the 1st internship application
  in the list to `Data analyst intern`.
* `edit 2 i/23/11/2022 15:00` - Edits the interview date/time of the 2nd internship application
  to `23 Nov 2022, 3:00 PM`, and changes the application status to "Shortlisted".
* `edit 3 t/frontend t/summer` - Edits the tags of the 3rd internship application, removing existing tags and
  adding `frontend` and `summer` tags

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

#### Marking an internship application status: `mark`

Marks an internship [application status](#application-status). Using this command, you can easily change the application status if the company has gotten back to you.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        mark INDEX s/APPLICATION_STATUS
        ```
    </div>
</div>

* Updates the internship application at the specified `INDEX`. The index **must be a positive integer** 1, 2, 3…
    *  The index refers to the index number shown in the current [displayed internship application list](#annotatedUi).
* Updates the internship application to the specified `APPLICATION_STATUS`.
* [`APPLICATION_STATUS`](#application-status) is case-insensitive.


Examples:
* `mark 3 s/interviewed` - Marks the 3rd internship application status as `interviewed`
* `mark 2 s/Accepted` - Marks the 2nd internship application status as `accepted`
* `mark 4 s/REJECTED` - Marks the 4th internship application status as `rejected`

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

#### Listing all internship applications: `list`
 
Shows a list of all internship applications. Using this command, you can easily update the [displayed internship application list](#annotatedUi) to show you all internship applications, if it has been filtered by `find` or `filter`.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        list
        ```
    </div>
</div>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

#### Deleting an internship application: `delete`

Deletes an internship application. Using this command, you can easily delete an internship application that you do not want to keep track of.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        delete INDEX
        ```
    </div>
</div>

* Deletes the internship application at the specified `INDEX`. The index **must be a positive integer** 1, 2, 3…
    *  The index refers to the index number shown in the current [displayed internship application list](#annotatedUi).

Examples:
*  `delete 4` - Deletes the 4th internship application in the list.

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

#### Finding for internship applications or tags: `find`

Finds internship applications where the company's name and tags contain any of the given keywords. Using this command, you can easily find an internship application that you are looking for.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        find KEYWORD [MORE_KEYWORDS]…
        ```
    </div>
</div>

* The search is case-insensitive e.g `google` will match `Google`
* Partial words will be matched e.g. `goo` will match an application where the company's name or tag contains the word `goo`

<div class="d-flex alert alert-info">
<div class="me-3">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**Usage of <code>find</code> command**<br>
<code>find</code> command finds internship applications from the entire list of internship applications instead of the [displayed internship application list](#annotatedUi).
</div>
</div>
<div style="page-break-after: always;"></div>

Examples:
* `find tiktok` returns internship application(s) and tag(s) containing `tiktok`
* `find google backend` returns internship application(s) and tag(s) containing `google` and `backend`

<p align="center">
    <img src="images/findGoogleBackendResult.png" width="600" />
</p>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

#### Filtering for internship applications of a specific status: `filter`

Filters for internship applications of the specified [application status](#application-status). Using this command, you can easily filter for the internship applications with the application status that you are interested in.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        filter APPLICATION_STATUS
        ```
    </div>
</div>

* `APPLICATION_STATUS` is case-insensitive.

<div class="d-flex alert alert-info mb-1">
<div class="me-3">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**Usage of <code>filter</code> command**<br>
<code>filter</code> command filters internship applications from the entire list of internship applications instead of the [displayed internship application list](#annotatedUi).
</div>
</div>

<div class="d-flex alert alert-info">
<div class="me-3">
  <span class="badge text-bg-info">INFO</span>
</div>
<div>
<b><code>filter</code> vs <code>find</code></b><br>
<code>filter</code> and <code>find</code> commands may seem similar, here are some differences to take note of:<br>
<table class="table table-light table-hover">
  <thead>
    <tr>
      <th scope="col"><code>filter</code></th>
      <th scope="col"><code>find</code></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Used to filter <b>internship application statuses</b></td>
      <td>Used to search for <b>keywords in company names and tags</b></td>
    </tr>
    <tr>
      <td>Can only accept as input <b>one of 5 allowed <a href="#application-status">application statuses</a></b></td>
      <td>Can accept <b>any input</b></td>
    </tr>
  </tbody>
</table>
</div>
</div>

Examples:
* `filter accepted` - Shows a list of internship applications marked as `accepted`
* `filter Rejected` - Shows a list of internship applications marked as `rejected`

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

#### Sorting the list of internship applications: `sort`

Sorts the internship applications with the latest date at top to earliest at the bottom. Using this command, you can easily view the internship applications that have interviews coming up soon.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        sort CRITERIA
        ```
    </div>
</div>

* Sort criteria **must be one of the following**:
    * `applied`
    * `interview`

Examples:
* `sort applied` will sort the internship applications according to applied date
* `sort interview` will sort the internship applications according to interview date

<div class="d-flex alert alert-info">
<div class="me-3">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**Sorting by interview date**<br>
Applications without interview date will remain at the bottom in the original order that it was added.
</div>
</div>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

#### Clearing all internship applications: `clear`

Clears all internship applications from FindMyIntern. Using this command, you can easily clear all internship applications at once and start afresh.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        clear
        ```
    </div>
</div>

<div class="d-flex alert alert-danger">
<div class="me-3">
  <span class="badge text-bg-danger">DANGER</span>
</div>
<div markdown="span">

This command will clear the entire list of internships without warning. This command is **irreversible**!
</div>
</div>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

#### Viewing help: `help`

Shows a message with a link to the user guide, and the data file location. Using this command, you can easily navigate to our user guide in case you have any difficulties in using FindMyIntern.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        help
        ```
    </div>
</div>

* Click on the link to copy the user guide link.

<p align="center">
    <img src="images/helpMessage.png" width="500"/>
</p>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

#### Exiting the program: `exit`

Exits the program. Using this command, you can quit FindMyIntern safely.

<div class="d-flex alert alert-secondary">
    <div class="me-3">
        <span class="badge text-bg-dark">FORMAT</span>
    </div>
    <div markdown="span" class="w-100">
        ```
        exit
        ```
    </div>
</div>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#commands" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to commands</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

### Saving and Editing

#### Saving the data

FindMyIntern's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data

FindMyIntern's data is saved as a JSON file `[JAR file location]/data/findmyintern.json`.
The data file location can also be found in the help message: see [`help` command](#viewing-help-help).

Advanced users are welcome to update data directly by editing that data file.

<div class="d-flex alert alert-danger">
<div class="me-3">
  <span class="badge text-bg-danger">DANGER</span>
</div>
<div markdown="span">
If your changes to the data file makes its format invalid, FindMyIntern will **discard all data** and start with an empty data file at the next run. Your data will **not be recoverable**!
</div>
</div>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

### UI Elements

This section explains how to use certain elements in the user interface which can enhance your FindMyIntern experience. 

#### Links

Clicking on links will copy the URL of the link to the clipboard. The URL can then be pasted into any browser.

A tooltip is shown when the link is hovered.

<p align="center">
    <img src="images/linkTooltip.png" />
</p>

#### Tags

Tags that are added are automatically converted to lowercase. They are displayed in lexicographical order.

A tooltip containing the full tag name is shown when a tag is hovered.

The maximum number of tags that will be displayed is 5. Additional tags that are not displayed will be shown as a count.
A tooltip containing the additional tags is shown when the count is hovered.

<p align="center">
    <img src="images/additionalTagsTooltip.png" />
</p>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>


--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FindMyIntern home folder.

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

--------------------------------------------------------------------------------------------------------------------

## Appendix

This section specifies **the input details** that you have to be aware of in FindMyIntern.

- [Field Constraints](#field-constraints)
- [Application Status](#application-status)
- [Link Formats](#link-formats)
- [Date/Time Formats](#datetime-formats)

### Field Constraints

Commands like `add` and `edit` accept fields, their constraints are specified below.

There are no constraints for a field if the constraints box is empty for that field.

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Field</th>
      <th scope="col">Constraints</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>c/COMPANY</code></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><code>l/LINK</code></td>
      <td>See <a href="#link-formats">link formats</a></td>
    </tr>
    <tr>
      <td><code>d/DESCRIPTION</code></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><code>a/APPLIED_DATE</code></td>
      <td>See <a href="#datetime-formats">date/time formats</a></td>
    </tr>
    <tr>
      <td><code>i/INTERVIEW_DATE_TIME</code></td>
      <td>See <a href="#datetime-formats">date/time formats</a></td>
    </tr>
    <tr>
      <td><code>t/TAG</code></td>
      <td>Must only contain alphabets and spaces</td>
    </tr>
  </tbody>
</table>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#appendix" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to appendix</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

### Application Status

Application status refers to the current status of an internship application.
Each internship application is tagged with an application status.

Application status as an input field **must be one of the following**:

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Application status</th>
      <th scope="col">Explanation</th>
      <th scope="col">Remark</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>applied</code></td>
      <td>You have applied for this internship</td>
      <td>Default application status</td>
    </tr>
    <tr>
      <td><code>shortlisted</code></td>
      <td>Company has shortlisted you for interview</td>
      <td>Adding/editing the interview date/time of an internship application will change application status to "Shortlisted"</td>
    </tr>
    <tr>
      <td><code>interviewed</code></td>
      <td>You have been interviewed for this internship application</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><code>accepted</code></td>
      <td>You have accepted this internship</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><code>rejected</code></td>
      <td>You have rejected or been rejected for this internship</td>
      <td>&nbsp;</td>
    </tr>
  </tbody>
</table>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#appendix" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to appendix</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

### Link Formats

For `Link`, the format allowed is `[SCHEME][HOSTNAME]SLD.TLD[MORE_TLDS][PATH]`.

Example:
* `[https://][careers.]google.com[.sg][/students]`

The constraints are:

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Field</th>
      <th scope="col">Constraints</th>
      <th scope="col">Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>SCHEME</code></td>
      <td><ul><li>Only <code>http://</code> and <code>https://</code> are allowed</li></ul></td>
      <td>https://</td>
    </tr>
    <tr>
      <td><code>HOSTNAME</code></td>
      <td><ul><li>Must only contain alphabets and numbers</li><li>Must end with the character '.'</li><li>Must be at least 2 characters and at most 8 characters long excluding the '.' character at the end</li></ul></td>
      <td>careers.</td>
    </tr>
    <tr>
      <td><code>SLD</code></td>
      <td><ul><li>Must only contain alphabets, numbers and the character '-'</li><li>Must end with the character '.'</li><li>Must be at least 1 character and at most 63 characters long excluding the '.' character at the end</li></ul></td>
      <td>google.</td>
    </tr>
    <tr>
      <td><code>TLD</code></td>
      <td><ul><li>Must only contain alphabets and numbers</li><li>Must end with the character '.' if there is another <code>TLD</code> after the current one</li><li>Must be at least 2 characters and at most 8 characters long</li></ul></td>
      <td>com.sg</td>
    </tr>
    <tr>
      <td><code>PATH</code></td>
      <td><ul><li>Must only contain alphabets, numbers and the characters '+&@#/%=~$.?'</li><li>Must start with the character '/'</li></ul></td>
      <td>/students</td>
    </tr>
  </tbody>
</table>

<div class="btn-group btn-group-sm mt-2 mb-4">
<a href="#appendix" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to appendix</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

<div style="page-break-after: always;"></div>

### Date/Time Formats

For `APPLIED_DATE`, the formats allowed:

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Format</th>
      <th scope="col">Input</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>d MMM yyyy</code></td>
      <td>7 Oct 2022</td>
    </tr>
    <tr>
      <td><code>d/M/yyyy</code></td>
      <td>7/10/2022</td>
    </tr>
  </tbody>
</table>

For `INTERVIEW_DATE_TIME`, the formats allowed:

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Format</th>
      <th scope="col">Input</th>
      <th scope="col">Remark</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>d MMM yyyy HH:mm</code></td>
      <td>7 Oct 2022 15:00</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><code>d/M/yyyy HH:mm</code></td>
      <td>7/10/2022 15:00</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><code>d MMM yyyy, h:mm a</code></td>
      <td>7 Oct 2022, 3:00 pm</td>
      <td>Take note of the <ul><li>comma after the date</li><li>space between the time and AM/PM</li></ul></td>
    </tr>
    <tr>
      <td><code>d/M/yyyy, h:mm a</code></td>
      <td>7/10/2022, 3:00 pm</td>
      <td>Take note of the <ul><li>comma after the date</li><li>space between the time and AM/PM</li></ul></td>
    </tr>
  </tbody>
</table>

<div class="d-flex alert alert-info">
<div class="me-3">
  <span class="badge text-bg-info">INFO</span>
</div>
<div markdown="span">
**Omission of year**<br>
The year `yyyy` can be omitted when entering `APPLIED_DATE` or `INTERVIEW_DATE_TIME` to default to the current year.
</div>
</div>

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#appendix" class="btn btn-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-up me-2"></i>Back to appendix</a>
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command Summary

Action | Format, Examples
--------|------------------
**Add** | `add c/COMPANY l/LINK d/DESCRIPTION a/APPLIED_DATE [i/INTERVIEW_DATE_TIME] [t/TAG]…` <br> e.g., `add c/Apple l/https://jobs.apple.com/en-sg d/Software engineering internship a/11/10/2022`
**Edit** | `edit INDEX [c/COMPANY] [l/LINK] [d/DESCRIPTION] [a/APPLIED_DATE] [i/INTERVIEW_DATE_TIME] [t/TAG]…` <br> e.g., `edit 1 d/Data analyst intern`
**Mark** | `mark INDEX s/APPLICATION_STATUS` <br> e.g., `mark 3 s/interviewed`
**List** | `list`
**Delete** | `delete INDEX` <br> e.g., `delete 4`
**Find** | `find KEYWORD [MORE_KEYWORDS]…`<br> e.g., `find google`
**Filter** | `filter APPLICATION_STATUS` <br> e.g., `filter accepted`
**Sort** | `sort CRITERIA`<br> e.g., `sort applied`
**Clear** | `clear`
**Help** | `help`
**Exit** | `exit`

<div class="btn-group btn-group-sm mt-2 mb-3">
<a href="#table-of-contents" class="btn btn-outline-light link-primary" style="--bs-btn-font-size: .8rem;"><i class="bi bi-chevron-bar-up me-2"></i>Back to table of contents</a>
</div>
