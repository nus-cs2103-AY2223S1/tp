---
layout: page
title: User Guide
---

This document contains everything you need to know about TruthTable.

## About TruthTable

TruthTable is a **desktop application** that enables team leaders to **manage their software
engineering teams** in one application.

TruthTable is **optimised for use via a [Command Line Interface (CLI)](#command-line-interface)** while still having 
the benefits of a [Graphical User Interface (GUI)](#graphical-user-interface). 

<div markdown="block" class="alert alert-info">**Unsure what CLI means?**
Head over [to this section](#understanding-truthtables-cli) to learn more
</div>

If you can type fast, TruthTable can get your task management done _blazingly fast_, faster than traditional GUI
applications.

Click [here](#getting-started) to get started with TruthTable.

## Key Features of TruthTable

### Too many cooks spoil the Truth

Managing project documents and information has always been an annoying task to do. Besides the multitude of
links and slides created by the team, you may be using task management applications to track your team's
progress as well. This is where TruthTable comes in.

TruthTable allows you to **collate and organise all your tasks**, links, and member information all in one spot.

### Seeing is believing

It is often difficult to visualise and communicate your team's progress to stakeholders. TruthTable solves this
through its ergonomic user interface.

At a glance, you can see the number of tasks completed by your team, and **determine the team's overall progress**.

TruthTable allows you to see which members are assigned to the tasks, enabling you to **delegate work in a fair and
equitable manner**.

### Built for convenience and speed

Context switching is a productivity killer. Having to switch from project management to product development usually
means switching from GUI-based applications to CLI code editors.

Hence, TruthTable allows you to use a **CLI for all your project management needs**, reducing the impact of context
switching.

<div markdown="span" class="alert alert-primary">
**:star: For experienced CLI users:**

TruthTable's command syntax is actually very similar to other command line applications like
[git](https://git-scm.com/) and [docker](https://docs.docker.com/engine/reference/commandline/cli/)!
</div>

## Using This Guide

If you are a software engineering team leader looking to get started with TruthTable, make sure that you have the 
[pre-requisites installed](#getting-started).

This document will also guide you on how to 
[migrate your existing project information onto TruthTable](#migrating-to-truthtable), and familiarise you with all 
of TruthTable's [features](#key-features-of-truthtable). 

If you are an experienced user of TruthTable, this guide also contains valuable tips on how you can maximise your 
productivity while using TruthTable.

Here are the symbols and text styles to look out for while using this guide:

| Symbol               | Meaning                                                                        |
|----------------------|--------------------------------------------------------------------------------|
| :information_source: | Important information for all users to take note                               |
| :bulb:               | Tip for all users                                                              |
| :star:               | Tip for advanced users                                                         |
| :exclamation:        | Critical information to be wary of                                             |

| Text Style                 | Meaning                                              |
|----------------------------|------------------------------------------------------|
| [Glossary link](#glossary) | Clickable link to navigate to a section in the guide |
| `text with background`     | Text relevant to the commands or name of a file      |
| <kbd>Esc</kbd>             | Keyboard button                                      |

Throughout the guide, there will also be alerts that look the following.

<div markdown="block" class="alert alert-dark">**Miscellaneous alert**:
This alert contains miscellaneous information
</div>

<div markdown="block" class="alert alert-info">**Important alert**:
This alert contains important information
</div>

<div markdown="block" class="alert alert-primary">**Helpful alert**:
This alert contains helpful information
</div>

<div markdown="block" class="alert alert-warning">**Warning alert**:
This alert contains important information to be wary of.
</div>

## Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Getting Started

1. Ensure you have **Java 11** or above installed in your computer. If not, don't worry! Click
<a href="https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html" target="_blank">here</a> 
   for further instructions on how to download and install **Java 11**.

2. Download the latest version of `truthtable.jar` from [here](https://github.com/AY2223S1-CS2103T-W13-4/tp/releases).
The file can be located under the "Assets" section of the page as seen in the image below.
![TruthTable Download Location](images/DownloadLocation.png)

3. Double-click on `truthtable.jar` to start the application. A window which looks similar to the screenshot below 
   should 
appear in a few seconds. We have also added some sample data for you to play around and familiarise yourself with 
   TruthTable's [features](#commands)! ![Ui](images/Ui.png)

4. Before we dive into TruthTable's features, make sure you understand how to 
[navigate the application](#navigating-truthtables-application-interface) first.

[Back to Table of Contents](#table-of-contents)

---

## Navigating TruthTable's Application Interface

After opening TruthTable for the first time, you should see the following screen.

![Application Interface](images/ApplicationInterface.png)

Each section contains several components, which will be labelled in their respective sections below. These 
components are numbered, and their names and descriptions will be displayed in a tabular format.

### Teams Section

![Teams Section](images/TeamsSection.png)

[//]: # (TODO: reference to team explanation / why at least 1 will always appear)
This section displays all the teams that you have created in TruthTable. Only the names of each team is listed.  

| Number | Component | Description                                    |
|--------|-----------|:-----------------------------------------------|
| 1      | Index     | The index of the team in the list of all teams |  
| 2      | Name      | The name of the team at the specified index    |


### Current Team Section

![Current Team Section](images/CurrentTeamSection.png)

This section contains information about your current team. It displays the currently selected team, team description, 
links, tasks, and members related to said team.

| Number | Component                 | Description                                                                                                   |
|--------|---------------------------|:--------------------------------------------------------------------------------------------------------------|
| 1      | Selected Team Name        | The name of the team currently being selected                                                                 |  
| 2      | Selected Team Description | The description of the team at the specified index                                                            |
| 3      | Link Name                 | The name of a link related to the selected team                                                               |
| 4      | Member Index              | The index of a member in the team's list of members                                                           |
| 5      | Member Name               | The name of a member at the specified member index                                                            |
| 6      | Task Index                | The index of a task in the team's list of tasks                                                               |
| 7      | Task Name                 | The name of a task at the specified task index                                                                |
| 8      | Task Assignee             | The name of the member assigned to the task                                                                   |
| 9      | Task Completion Status    | The completion status of a task, `[X]` is displayed if task is completed, else `[ ]` is displayed             |
| 10     | Task Completion Rate      | The number of tasks completed is displayed, along with its relative <br/>percentage to total number of tasks. |

### Persons Section

![Persons Section](images/PersonsSection.png)

This section displays the names, phone number, email, address, and tags of all persons that you have created in 
TruthTable. This section is also known as the "Contacts" section.

| Number | Component    | Description                                            |
|--------|--------------|--------------------------------------------------------|
| 1      | Index        | The index of a person in all the persons in TruthTable |
| 2      | Name         | The name of a person at a specific index               |
| 3      | Tags         | The tags associated with a person                      |
| 4      | Phone Number | The phone number of a person                           |
| 5      | Email        | The email address of a person                          |
| 6      | Address      | The address of a person                                |


### Command Section

![Command Section](images/CommandSection.png)

This section contains an input box where you can enter the commands for the actions that you wish to perform. Try 
typing `help` in the input box and press <kbd>Enter</kbd> to see what happens!

| Number | Component         | Description                                                                                       |
|--------|-------------------|---------------------------------------------------------------------------------------------------|
| 1      | Command Result    | The result of executing a command in the Command Input Box                                        |
| 2      | Command Input Box | The input box for commands to be entered, which can be executed by pressing <br/><kbd>Enter</kbd> |


[Back to Table of Contents](#table-of-contents)

---

## Understanding TruthTable's CLI

CLI applications like TruthTable requires you to execute instructions by typing specific words to make use of the 
application's features. However, if you are not an experienced user, terms like "commands", "flags", and "parameters" 
may seem daunting.

But fret not! This section will cover what all these terms actually mean. 

While you're going through this section, don't worry too much about memorising the details for every "command", 
as they all have their own help messages for your reference at any time. Furthermore, the "commands" in TruthTable are 
designed to be **easy to remember** and **natural**, so you will get up to speed in no time! 

### Commands, flags, and parameters

All instructions are executed through **commands**. Each command can have **flags specified after the command**, 
which modifies how the instruction should be carried out.

For each of these flags, there may be **parameters** specified after it. But, not all commands
require flags to **appear before parameters**.

Note that flags and parameters always **come after the command**.

Let's use an example to break down what each term means.

#### Example Command - `add person`

![CLI Syntax](images/CliSyntax.png)

Here's a summary of what each highlighted section represents.

| Number | Name               | Meaning                                                                                 |
|--------|--------------------|:----------------------------------------------------------------------------------------|
| 1      | Command            | The name of an instruction                                                              |
| 2      | Flag/Option        | Text that is used to modify the operation of a command, often starting with `-` or `--` |
| 3      | Parameter/Argument | Information specified for a command to operate, often used after flags                  |

### Understanding the help message

Every command in TruthTable has their own flags and parameters, but it is not feasible for you to remember them all. 

To address this, each command allows you to specify the `-h` and `--help` flag, which brings up the available flags 
and parameters for the command. Alternatively, you can also refer to this user guide to find out the specifications for 
any command.

#### Example Command Help Message: `add person --help`

![Command Help Message](images/CommandHelpMessage.png)

Here's a summary of what each highlighted section represents.

| Number | Name                            | Special Syntax         | Remarks                                                                        |
|--------|---------------------------------|------------------------|:-------------------------------------------------------------------------------|
| 1      | Command                         | -                      | Strictly alphabets only (all commands can be found [here](#command-summary))   |
| 2      | Optional Flag with no Parameter | `[-flag]`              | -                                                                              |
| 3      | Optional Flag with Parameters   | `[-flag=<parameters>]` | An ellipsis `...` is present only if more than 1 parameter is **allowed**      |
| 4      | Required Flag with Parameters   | `flag=<parameters>`    | An ellipsis `...` is present only if more than 1 parameter is **required**     |
| 5      | Required Parameter              | `<parameter>`          | All parameters are required by default                                         |
| 6      | Flag/Parameter Name and Alias   | -                      | All aliases of a flag/parameter will be listed and separated with commas (`,`) |
| 7      | Flag/Parameter Description      | -                      | Brief description of flag/parameter                                            |

<div markdown="block" class="alert alert-info">
**:information_source: Important note for flags**

- Specifying an "equal" sign (`=`) after the flag is optional, and it has no effect on the command.
- Optional flags are wrapped with square brackets (`[]`)
- Flags can be specified in any order
  - For e.g. `-n name -p 98765432` is the same as `-p 98765432 -n name`

</div>

<div markdown="block" class="alert alert-info">
**:information_source: Important note for parameters**

Use of quotation marks around parameters (`""` and `''`) is optional.

**However**, if you are specifying a parameter with multiple words, you will need to wrap the words in a quotes.
Otherwise, TruthTable will not carry out the command properly.

For instance,
- `add person -n "full name" ...` will recognise "full name" as the name being specified for the new person
- `add person -n full name ...` will recognise "full" as the name being specified for the new person, however, this
  command will not execute successfully as `name` will be treated as a **flag** (and this flag does not exist for
  the `add person` command)

Also, if your quotes are not closed properly (i.e. some open quotation mark does not have a corresponding close
quotation mark), the command will not be executed. If the parameter specified contains quotation marks, they will be
rejected as well.

For instance,
- `'word` is invalid as the open quotation mark `'` does not have a corresponding close quotation mark.
- `"'"` is invalid as `'` will be passed as a parameter, which is invalid.
</div>

<div markdown="block" class="alert alert-primary">
**:bulb: Flags with multiple parameters**

If a flag is displayed with `...` behind it, this means that the flag can take in more than 1 parameter.

For instance, in the `assign task` command, we can specify more than 1 assignee (provided they are valid).
A valid command is `assign task 1 -a 1 2 3` where `1 2 3` are recognised as the assignees to the task with index `1`.
</div>


<div markdown="block" class="alert alert-primary">
**:star: POSIX clustered short options**: TruthTable supports clustered options as specified 
<a href="https://pubs.opengroup.org/onlinepubs/9699919799/basedefs/V1_chap12.html#tag_12_02" target="_blank">here</a>
</div>

### Aliases

TruthTable allows for aliases (i.e. alternative names) to represent the same commands, similar to nicknames. 
Consequently, commands and flags may have alternative names to execute the same instruction.

For instance, the `add person` command has the following command aliases,
- `add p`
- `a person`
- `a p`

Refer to the [command summary section](#command-aliases) to view the commands and their respective aliases.

[Back to Table of Contents](#table-of-contents)

---

## Migrating to TruthTable

If you're looking to migrate your team information onto TruthTable, here is a step-by-step guide on how to do so.

### Creating the team

### Adding your members

### Adding your links

### Adding your tasks

### Delegating work

### Visualising progress

[Back to Table of Contents](#table-of-contents)

---
## Commands

<div markdown="block" class="alert alert-info">**:information_source: Make sure you have 
[read this section](#understanding-truthtables-cli) before continuing!**
</div>

TruthTable allows you to manage members, tasks, links, and members. For member management, you will need to create the 
person's record in TruthTable first, before assigning them to different teams.

Consequently, the commands are split into **5** main sections:

1. [Person commands](#commands-to-manage-persons) and [Member commands](#commands-to-manage-members)
2. [Team commands](#commands-to-manage-teams)
3. [Task commands](#commands-to-manage-tasks)
4. [Link commands](#commands-to-manage-links--urls)
5. [General commands](#general-commands)

<div markdown="block" class="alert alert-info">**:information_source: Notes about command format**

- Format for each command adheres to the structure laid out in the 
[help command section](#example-command-help-message-add-person---help)
- Every command has a `-h` and `--help` flag available to see their help message
- Parameters are in uppercase and wrapped with angle brackets, e.g. `<PARAMETER>`
- Flags wrapped with brackets (`()`) indicates that at least 1 flag inside the brackets
  must be specified
  - For `([-n <NAME>] [-p <PHONE>] [-e <EMAIL>] [-t [<TAGS>]]...)`, the following examples are valid
    1. `-n name`
    2. `-p 98765432`
    3. `-e new@email.com`
    4. `-t developer`
- Flags wrapped with brackets (`()`) and separated with `|` indicates that you must specify only 1 flag inside the 
  brackets
  - For `(-n=<NAME_KEYWORDS> | -e=<EMAIL_KEYWORDS>)`, the following examples are valid
    1. `-n one two`
    2. `-e three four`
  - However, the following are invalid
    1. `-n one two -e three four`
    2. when both flags are empty
- Flags/Parameters with ellipsis (`...`) behind them means that more than 1 parameter can be specified
  - For `[-t [TAGS...]]...`, the following examples are valid
    1. `-t`
    2. `-t one`
    3. `-t one two`
    4. `-t one -t two`
- Flags can be combined if they all begin with `-`, where the flags are clustered. Find out more
  <a href="https://pubs.opengroup.org/onlinepubs/9699919799/basedefs/V1_chap12.html#tag_12_02" target="_blank">here</a>
  - For `[-h] [-c] [-i]`, the following examples are valid
    1. `-h`
    2. `-hc`
    3. `-ci`
    4. `-hci`
- If an extra parameter is specified, the command will not execute and an error message will be displayed
  - For e.g. `help with extra words` will display an error message
</div>

<div markdown="block" class="alert alert-info">**:information_source: Parameter constraints can be found 
[here](#flag-and-parameter-constraints)**
</div>

<div markdown="span" class="alert alert-primary">
**:bulb: Letter casing for parameters**

Note that the parameters in this user guide are all upper-cased, which differs from the lower-cased parameters in 
the help message that TruthTable displays whenever you run a command with `-h`.
</div>


### Commands to Manage Persons

Summary of commands can be found [here](#summary-of-person-commands)

#### Creating a new person: `add person`

Adds a person to TruthTable.

**Format:** `add person [-h] -e=<EMAIL> -n=<NAME> -p=<PHONE> [-t[=<TAGS>...]]...`

| Flags           | Required           | Remarks                                 |
|-----------------|:-------------------|-----------------------------------------|
| `-h`, `--help`  |                    | Shows help message for this command     |
| `-e`, `--email` | :heavy_check_mark: | Email of person (e.g. truth@table.com)  |
| `-n`, `--name`  | :heavy_check_mark: | Name of person (e.g. "Truth Table")     |
| `-p`,`--phone`  | :heavy_check_mark: | Phone of person (e.g. 98765432)         |
| `-t`,`--tags`   |                    | Tags of person (e.g. Frontend, Backend) |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

**Command Aliases:**
- `a person`
- `add p`
- `a p`

**Examples:**
* `add person -n "John Doe" -p 98765432 -e johnd@example.com`
* `a p -n "Betsy Crowe" -e betsycrowe@example.com -p 1234567 -t criminal friend`

#### Editing a person: `edit person`

Edits an existing person in TruthTable.

**Format:** `edit person [-h] ([-n=<NAME>] [-p=<PHONE>] [-e=<EMAIL>] [-t[=<TAGS>...]]...) <PERSON_INDEX>`

| Flags           | Required   | Remarks                                 |
|-----------------|:-----------|-----------------------------------------|
| `-h`, `--help`  |            | Shows help message for this command     |
| `-e`, `--email` | :asterisk: | Email of person (e.g. truth@table.com)  |
| `-n`, `--name`  | :asterisk: | Name of person (e.g. "Truth Table")     |
| `-p`,`--phone`  | :asterisk: | Phone of person (e.g. 98765432)         |
| `-t`,`--tags`   | :asterisk: | Tags of person (e.g. Frontend, Backend) |

:asterisk: - at least one of the flags must be specified

* Edits the person at the specified `PERSON_INDEX`, which refers to the index number shown in the
  [persons section](#persons-section)
* `PERSON_INDEX` **must be a positive integer**: 1, 2, 3,...
* Each field only updates if the flag for that field is specified.
* When editing tags, the existing tags of the person will be **completely replaced** by the new tags specified.
* So, you can remove all the person’s tags by typing `-t` without specifying any tags after it.

**Command Aliases:**
- `edit p`
- `e person`
- `e p`

**Examples:**
*  `edit person 1 -p 91234567 -e johndoe@example.com` Edits the phone number and email address of the 1st person to be
   `91234567` and `johndoe@example.com` respectively.
*  `edit person 2 -n Betsy Crower -t` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing
   tags.

#### Deleting a person: `delete person`

Deletes the specified person from TruthTable.

**Format:** `delete person [-h] <PERSON_INDEX>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* Deletes the person at the specified `PERSON_INDEX`, which refers to the index number shown in the
  [persons section](#persons-section)
* `PERSON_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `delete p`
- `d person`
- `d p`

**Examples:**
* `list persons` followed by `delete person 2` deletes the 2nd person in TruthTable.
* `find person Betsy` followed by `delete person 1` deletes the 1st person in the results of the `find person` command.

#### Locating persons by name: `find person`

Finds person whose names contain any of the given keywords.

**Format:** `find person [-h] <KEYWORDS>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* Only the name is searched.
* The search is case-insensitive, e.g. `hans` will match `Hans`.
* The order of the keywords does not matter, e.g. `Hans Bo` will match `Bo Hans`.
* Persons with names partially matching the keywords will be returned, e.g. `han` will match `Hans`.
* Persons with names matching at least one keyword will be returned, e.g. `hans bo` will return `Hans Gruber`, `Bo
  Yang`.

**Command Aliases:**
- `find p`
- `f person`
- `f p`

**Examples:**
* `find person John` returns `john` and `John Doe`
* `find person alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find person alex david'](images/findAlexDavidResult.png)

#### Listing all persons: `list persons`

Shows a list of all persons in TruthTable.

**Format:** `list persons [-h]`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

**Command Aliases:**
- `list p`
- `l persons`
- `l p`

### Commands to Manage Members

Summary of commands can be found [here](#summary-of-member-commands)

#### Adding a new member to the team: `add member`

Adds a new team member to the user’s currently selected team. Will throw an error if the member already exists in 
the team.

**Format:** `add member [-h] <PERSON_INDEX>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* `PERSON_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `add m`
- `a member`
- `a m`

**Examples:** 

- `add member 1` will add the first person in the [persons sections](#persons-section) as a new member to the 
  [currently selected team](#current-team-section).

#### Delete a member from team: `delete member`

Delete a team member from the user’s team.

**Format:** `delete member [-h] <MEMBER_INDEX>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* `MEMBER_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `delete m`
- `d member`
- `d m`

**Examples:** 

- `delete member 2` will delete the second member of the current team.

#### Find members: `find member`

Finds all members in the current team whose names or emails contain any of the given keywords.

<div markdown="span" class="alert alert-info">
:information_source: **Note:** You can only find members using **either** emails or names. This means that you 
should not use both `-n` and `-e` in the `find member` command. 
</div>

**Format:** `find member [-h] (-n=<NAME_KEYWORDS> | -e=<EMAIL_KEYWORDS>)`

| Flags           | Required | Remarks                             |
|-----------------|:---------|-------------------------------------|
| `-h`, `--help`  |          | Shows help message for this command |
| `-n`, `--name`  | :hash:   | Keywords to filter by name          |
| `-e`, `--email` | :hash:   | Keywords to filter by email         |

:hash: - exactly one flag must be specified

* Only the name or email is searched.
* The search is case-insensitive, e.g. `hans` will match `Hans`.
* The order of the keywords does not matter, e.g. `Hans Bo` will match `Bo Hans`.
* Persons with names/emails partially matching the keywords will be returned, e.g. `han` will match `Hans`.
* Persons matching at least one keyword will be returned, e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

**Command Aliases:**
- `find m`
- `f member`
- `f m`

**Examples:**
* `find member -n Alex` finds team members with **names** containing the word "Alex".
* `find member -n Alex Beatrice` finds team members with **names** containing **either** "Alex" or "Beatrice".
* `find member -e alex@gmail.com`  finds team members with **emails** containing "alex@gmail.com".

#### Listing all members of the team: `list members`

View all the members currently in the team, in the form of a list.

**Format:** `list members [-h]`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

**Command Aliases:**
- `list m`
- `l members`
- `l m`

#### Sort members: `sort members`

Sorts all members in the current team by name and displays them in the member list.

**Format:** `sort members [-h] <ORDER>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

| Order Value | Description                                                                  |
|-------------|------------------------------------------------------------------------------|
| `asc`       | Sorts team members in **alphabetical** order (based on their names).         |
| `dsc`       | Sorts team members in **reverse alphabetical** order (based on their names). |
| `res`       | **Resets** the order of the team members shown                               |

**Command Aliases:**
- `sort m`
- `so members`
- `so m`

**Examples:**
* `sort members asc` sorts team members in **ascending** order.
* `sort members dsc` sorts team members in **descending** order.
* `sort members res` **resets** the order of team members shown.

### Commands to Manage Teams

Summary of commands can be found [here](#summary-of-team-commands)

#### Creating a new team: `add team`

Add a new team to your list of teams, an error will be displayed if the team name is already in use.

<div markdown="span" class="alert alert-info">:information_source: **Note:** 
Team name must consist only of alphanumeric characters (i.e., **spaces are NOT allowed**).
</div>

**Format:** `add team [-h] [-d=TEAM_DESCRIPTION] <TEAM_NAME>`

| Flags                 | Required | Remarks                                               |
|-----------------------|:---------|-------------------------------------------------------|
| `-h`, `--help`        |          | Shows help message for this command                   |
| `-d`, `--description` |          | Description of team (e.g. "A team to manage CS2103T") | 

**Command Aliases:**
- `add te`
- `a team`
- `a te`

**Examples:** 

- `add team CS2103T` will create a new team by the name of "CS2103T"
- `add team CS2102 -d "Database Systems"` will create a new team by the name of "CS2102" and "Database Systems"
  as description

#### Edit current team: `edit team`

**Format:** `edit team [-h] ([-n=<TEAM_NAME>] [-d=<DESCRIPTION>])`

| Flags                 | Required   | Remarks                                               |
|-----------------------|:-----------|-------------------------------------------------------|
| `-h`, `--help`        |            | Shows help message for this command                   |
| `-n`, `--name`        | :asterisk: | Name of team (e.g. "CS2103T")                         |
| `-d`, `--description` | :asterisk: | Description of team (e.g. "A team to manage CS2103T") | 

:asterisk: - at least one of the flags must be specified

**Command Aliases:**
- `edit te`
- `e team`
- `e te`

**Examples:**
- `edit team -n CS2103T -d "Software Engineering"` will edit the name of the
  [currently selected team](#current-team-section) to CS2103T and description to "Software Engineering"

#### Delete an existing team: `delete team`

Delete an existing team, and an error is displayed if

- The target team does not exist, or
- The target team is the only existing team.

**Format:** `delete team [-h] <TEAM_NAME>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

**Command Aliases:**
- `delete te`
- `d team`
- `d te`

**Examples:**

- `delete team CS2103T` will delete the team with the name "CS2103T"

#### Set a new team: `set team`

Updates the [selected team](#current-team-section) and changes the current "working" team to another. An error is 
displayed if team does not exist.

**Format:** `set team [-h] <TEAM_NAME>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

**Command Aliases:**
- `set te`
- `s team`
- `s te`

**Examples:** 

- `set team CS2103T` will change the currently selected team to be the "CS2103T" team.

### Commands to Manage Tasks

Summary of commands can be found [here](#summary-of-task-commands)

#### Add task to team: `add task`

Add a new task to your current team. Each task can have multiple assignees and a deadline.

**Format:** `add task [-h] [-d=<DEADLINE>] [-a[=<ASSIGNEES>...]]... <TASK_NAME>`

| Flags              | Required | Remarks                                                      |
|--------------------|:---------|--------------------------------------------------------------|
| `-h`, `--help`     |          | Shows help message for this command                          |
| `-d`, `--deadline` |          | Deadline of task (e.g. 2023-02-25 23:59)                     |  
| `-a`, `--assignee` |          | Index of members in [members section](#current-team-section) |

* `DEADLINE` contains both the date and time in `YYYY-MM-DD HH:MM` format, and it **does not have quotation marks (`""`)
  around the parameter**.
* `ASSIGNEES` **must be positive integers**: 1, 2, 3,...

**Command Aliases:**
- `add ta`
- `a task`
- `a ta`

**Examples:**

* `add task "Create PR"` will add a task with the name "Create PR", to the current team's task list.
* `add task "Merge PR" -a 1 -d 2022-12-02 23:59` will add a task with the name "Merge PR", assigned to the first 
member in the members list with a deadline of 2nd Dec 2022 23:59, to the current team's task list.
* `add task "Review PR" -a 1 3 -d 2022-12-02 23:59` will add a task with the name "Review PR", assigned to the first 
and third member in your members list with a deadline of 2nd Dec 2022 23:59, to the current team's task list.

#### Edit task in team: `edit task`

Edits a specified task in the current team. 

**Format:** `edit task [-h] ([-n=<NAME>] [-d=<DEADLINE>] [-a[=<ASSIGNEES>...]]...) <TASK_INDEX>`

| Flags              | Required   | Remarks                                                      |
|--------------------|:-----------|--------------------------------------------------------------|
| `-h`, `--help`     |            | Shows help message for this command                          |
| `-n`, `--name`     | :asterisk: | Name of task (e.g. "merge PR#12")                            |
| `-d`, `--deadline` | :asterisk: | Deadline of task (e.g. 2023-02-25 23:59)                     |
| `-a`, `--assignee` | :asterisk: | Index of members in [members section](#current-team-section) |

:asterisk: - at least one of the flags must be specified

* Edits the person at the specified `TASK_INDEX`, which refers to the index number shown in the
  [team members section](#current-team-section)
* `DEADLINE` contains both the date and time in `YYYY-MM-DD HH:MM` format, and it **does not have quotation marks (`""`)
  around the parameter**.
* `TASK_INDEX` and `ASSIGNEES` **must be positive integers**: 1, 2, 3,...
* Each field only updates if the flag for that field is specified.
* When editing assignees, the existing assignees of the task will be **completely replaced** by the new assignees 
  specified.
* So, you can remove all the assignees of the task by typing `-a` without specifying any assignees after it.

**Command Aliases:**
- `edit ta`
- `e task`
- `e ta`

**Examples:** 

* `edit task 1 "Merge PR" -a 1 -d 2022-12-02 23:59` will edit the first task in the current team's task
list, setting the name as "Merge PR", assignees as the first member in the team list, and deadline
as 2nd Dec 2022 23:59.
* `edit task 1 "Update UG" -d 2022-12-02 23:59` will edit the first task in the current team's task
list, setting the name as "Update UG" and deadline as 2nd Dec 2022 23:59. The assignees are not modified
in this example.
* `edit task 1 -a` will edit the first task in the current team's task list, removing all assignees from the task. 
The name and deadline are not modified in this example.

#### Delete task from team: `delete task`

Delete an existing task from the team at the given task index.

**Format:** `delete task [-h] <TASK_INDEX>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* `TASK_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `delete ta`
- `d task`
- `d ta`

**Examples:**

- `delete task 1` will delete the first task of the current team.

#### Find tasks: `find task`

Find all tasks in the current team whose names matches any of the given keywords.

To reset the task list, see the [list tasks command](#list-tasks-in-team-list-tasks).

**Format:** `find task [-h] <KEYWORDS>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* Only the task name is searched.
* The search is case-insensitive. e.g. `user guide` will match `User Guide`
* The order of the keywords does not matter. e.g. `User Guide` will match `guide user`
* Tasks with names partially matching the keywords will be returned, e.g. `user` will match `userguide`.
* Tasks with names matching at least one keyword will be returned, e.g. `guide case` will return `use case`, `user
  guide`.

**Command Aliases:**
- `find ta`
- `f task`
- `f ta`

**Examples:**
* `find task -n User Guide` finds tasks with **names** containing **either** the word "User" or "Guide".

#### Mark tasks as done: `mark`

Mark a specified task as done. To undo this command, see the [unmark command](#unmark-tasks-as-done-unmark)

**Format:** `mark [-h] <TASK_INDEX>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* `TASK_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `m`

**Examples:**

- `mark 1` will mark the first task in the team as done.

#### Unmark tasks as done: `unmark`

Mark a specified task as incomplete. This will undo the [mark command](#mark-tasks-as-done-mark).

**Format:** `unmark [-h] <TASK_INDEX>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* `TASK_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `u`

**Examples:**

- `unmark 1` will mark the first task in the team as incomplete.

#### Set Deadline for task: `set deadline`

Set a deadline for an existing task, and the deadline must be in `YYYY-MM-DD HH:MM` format.

**Format:** `set deadline [-h] <TASK_INDEX> <DEADLINE>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* `TASK_INDEX` **must be a positive integer**: 1, 2, 3,...
* `DEADLINE` contains both the date and time in `YYYY-MM-DD HH:MM` format, and it **does not have quotation marks (`""`)
  around the parameter**.

**Command Aliases:**
- `set d`
- `s deadline`
- `s d`

**Examples:**

- `set deadline 1 2023-12-25 23:59` will set the deadline for the first task on the task list as 25 Dec 2023 23:59

#### Assign task to team member: `assign task`

Assign an existing task to a team member in the user’s team.

**Format:** `assign task [-h] -a[=<ASSIGNEES>...] [-a[=<ASSIGNEES>...]]... <TASK_INDEX>`

| Flags              | Required           | Remarks                                                      |
|--------------------|:-------------------|--------------------------------------------------------------|
| `-h`, `--help`     |                    | Shows help message for this command                          |
| `-a`, `--assignee` | :heavy_check_mark: | Index of members in [members section](#current-team-section) |

* `TASK_INDEX` and `ASSIGNEES` **must be positive integers**: 1, 2, 3,...
* The original assignees of the task will not be replaced with this command, instead, only new assignees can be added.

**Command Aliases:**
- `assign ta`
- `as task`
- `as ta`

**Examples:** 

- `assign task 1 -a` will not do anything.
- `assign task 1 -a 1` will assign the first task on the task list to the first member in the team.
- `assign task 2 -a 1 2` will assign the second task on the task list to the first and second member in the team.

#### Assign task to random team member: `assign random`

Assign an existing task to a random team member in the user’s team. This command will not work if the team is empty
or if the task has already been assigned to all members of the team.

**Format:** `assign random [-h] <TASK_INDEX>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* `TASK_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `assign r`
- `as random`
- `as r`

**Examples:** 

- `assign random 1` will assign the first task on the task list to a random team member.

#### Filter tasks by team member: `tasksof`

Find all tasks that have been assigned to a particular member in the currently selected team.

**Format:** `tasksof [-h] <MEMBER_INDEX>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* `MEMBER_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `to`

**Examples:**
* `tasksof 1` will show all tasks assigned to the first member in your current team's member list.

#### List tasks in team: `list tasks`

View all the tasks in the [currently selected team](#current-team-section). These tasks can also be filtered based on 
whether they are complete or incomplete tasks.

This command can also be used to view all tasks again after using the [`find task` command](#find-tasks-find-task) 
as the `find task` command filters the current team’s tasks based on some keyword(s).

**Format:** `list tasks [-h] [-c] [-i]`

| Flags                | Required | Remarks                             |
|----------------------|:---------|-------------------------------------|
| `-h`, `--help`       |          | Shows help message for this command |
| `-c`, `--complete`   |          | Filter for completed tasks          |
| `-i`, `--incomplete` |          | Filter for incomplete tasks         |

**Command Aliases:**
- `list ta`
- `l tasks`
- `l ta`

**Examples:** 
- `list tasks` will list all the tasks of the current team.
- `list tasks -i` will list all the incomplete tasks of the current team.
- `list tasks -c` will list all the completed tasks of the current team.
- `list tasks -ic` will list all tasks of the current team.

#### Sort tasks: `sort tasks`

Sorts all tasks in the current team by name and displays them in the task list.

**Format:** `sort tasks [-h] <ORDER>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

| Order Value | Description                                                           |
|-------------|-----------------------------------------------------------------------|
| `asc`       | Sorts tasks in **alphabetical** order (based on their names).         |
| `dsc`       | Sorts tasks in **reverse alphabetical** order (based on their names). |
| `res`       | **Resets** the order of the tasks shown                               |

**Command Aliases:**
- `sort ta`
- `so tasks`
- `so ta`

**Examples:**
* `sort tasks asc` sorts tasks in **ascending** order.
* `sort tasks dsc` sorts tasks in **descending** order.
* `sort tasks res` **resets** the order of the tasks shown.

#### View summary of task assignments in team: `summary`

View the number of tasks assigned to each member in the team.

**Format:** `summary [-h]`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

**Command Aliases:**
- `sum`
- `su`

### Commands to Manage Links / URLs

Summary of commands can be found [here](#summary-of-link-commands)

#### Add a new link: `add link`

Add a new link to the currently selected team.

**Format:** `add link [-h] -l=<URL> -n=<NAME>`

| Flags          | Required           | Remarks                               |
|----------------|:-------------------|---------------------------------------|
| `-h`, `--help` |                    | Shows help message for this command   |
| `-l`, `--link` | :heavy_check_mark: | URL of link (e.g. https://google.com) |
| `-n`, `--name` | :heavy_check_mark: | Name of link (e.g. "Meeting #1")      |

**Command Aliases:**
- `add l`
- `a link`
- `a l`

**Examples:**
- `add link -n google -l https://google.com` will add a link named "google" with the URL "https://google.com".

#### Edit an existing link: `edit link`

Edit an existing link in the currently selected team.

**Format:** `edit link [-h] ([-n=<NAME>] [-l=<URL>]) <LINK_INDEX>`

| Flags          | Required   | Remarks                               |
|----------------|:-----------|---------------------------------------|
| `-h`, `--help` |            | Shows help message for this command   |
| `-l`, `--link` | :asterisk: | URL of link (e.g. https://google.com) |
| `-n`, `--name` | :asterisk: | Name of link (e.g. "Meeting #1")      |

:asterisk: - at least one of the flags must be specified

* `LINK_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `edit l`
- `e link`
- `e l`

**Examples:**
- `edit link 1 -n facebook -l https://facebook.com` will update the 1st link to have the name "facebook" with the 
  URL of "https://facebook.com".
- `edit link 1 -n google` will update the 1st link to have the name "google".

#### Delete an existing link: `delete link`

Delete the specified link from the currently selected team.

**Format:** `delete link [-h] <LINK_INDEX>`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

* `LINK_INDEX` **must be a positive integer**: 1, 2, 3,...

**Command Aliases:**
- `delete l`
- `d link`
- `d l`

**Examples:**
- `delete link 1` will delete the 1st link

### General Commands

Summary of commands can be found [here](#summary-of-general-commands)

#### Clearing all entries: `clear`

Deletes all the people from the application.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution: This action is irreversible!**
</div>

**Format:** `clear [-h]`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

#### Switching between light and dark theme: `theme`

Toggles between light theme and dark theme.

**Format:** `theme [-h]`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

**Command Aliases:**
- `th`

#### Exiting the program: `exit`

Closes the program after 3 seconds.

**Format:** `exit [-h]`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

**Command Aliases:**
- `quit`
- `bye`
- `ex`

#### Viewing help: `help`

Shows the URL to this help page, which will list all available commands.

**Format:** `help [-h]`

| Flags          | Required | Remarks                             |
|----------------|:---------|-------------------------------------|
| `-h`, `--help` |          | Shows help message for this command |

**Command Aliases:**
- `h`

---

## Saving the data

TruthTable's data is saved in the hard disk automatically after running any command that changes data. There is no 
need to save manually.

[Back to Table of Contents](#table-of-contents)

## Editing the data file

TruthTable data is saved as a [JSON file](#json-file), which can be found in the location where `truthtable.jar` 
is opened from (let's call it `TRUTHTABLE_LOCATION`) and going to `TRUTHTABLE_LOCATION/data/truthtable.json`. 

Advanced users are welcomed to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TruthTable will discard all data and start with an empty
data file on the next run.
</div>

[Back to Table of Contents](#table-of-contents)

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous TruthTable home folder.

[Back to Table of Contents](#table-of-contents)

---

## Command Summary

### Command Aliases

| Action                                   | Command         | Aliases                         |
|------------------------------------------|:----------------|---------------------------------|
| Creating a new person                    | `add person`    | `a p`, `add p`, `a person`      |
| Editing a person                         | `edit person`   | `e p`, `edit p`, `e person`     |
| Deleting a person                        | `delete person` | `d p`, `delete p`, `d person`   |
| Locating persons by name                 | `find person`   | `f p`, `find p`, `f person`     |
| Listing all persons                      | `list persons`  | `l p`, `list p`, `l persons`    |
| Adding a new member to the team          | `add member`    | `a m`, `add m`, `a member`      |
| Delete a member from team                | `delete member` | `d m`, `delete m`, `d member`   |
| Find members                             | `find member`   | `f m`, `find m`, `f member`     |
| Listing all members of the team          | `list members`  | `l m`, `list m`, `l members`    |
| Sort members                             | `sort members`  | `so m`, `sort m`, `so members`  |
| Creating a new team                      | `add team`      | `a te`, `add te`, `a team`      |
| Edit current team                        | `edit team`     | `e te`, `edit te`, `e team`     |
| Delete an existing team                  | `delete team`   | `d te`, `delete te`, `d team`   |
| Set a new team                           | `set team`      | `s te`, `set te`, `s team`      |
| Add task to team                         | `add task`      | `a ta`, `add ta`, `a task`      |
| Edit task in team                        | `edit task`     | `e ta`, `edit ta`, `e task`     |
| Delete task from team                    | `delete task`   | `d ta`, `delete ta`, `d task`   |
| Find tasks                               | `find task`     | `f ta`, `find ta`, `f task`     |
| Mark tasks as done                       | `mark`          | `m`                             |
| Unmark tasks as done                     | `unmark`        | `u`                             |
| Set Deadline for task                    | `set deadline`  | `s d`, `set d`, `s deadline`    |
| Assign task to team member               | `assign task`   | `as ta`, `assign ta`, `as task` |
| Assign task to random team member        | `assign random` | `as r`, `assign r`, `as random` |
| Filter tasks by team member              | `tasksof`       | `to`                            |
| List tasks in team                       | `list tasks`    | `l ta`, `list ta`, `l tasks`    |
| Sort tasks                               | `sort tasks`    | `so ta`, `sort ta`, `so tasks`  |
| View summary of task assignments in team | `summary`       | `su`, `sum`                     |
| Add a new link                           | `add link`      | `a l`, `add l`, `a link`        |
| Edit an existing link                    | `edit link`     | `e l`, `edit l`, `e link`       |
| Delete an existing link                  | `delete link`   | `d l`, `delete l`, `d link`     |
| Clearing all entries                     | `clear`         |                                 |
| Switching between light and dark theme   | `theme`         | `th`                            |
| Exiting the program                      | `exit`          | `ex`, `bye`, `quit`             |
| Viewing help                             | `help`          | `h`                             |

[Back to Table of Contents](#table-of-contents)

### Flag And Parameter Constraints

| Parameter Name          | Flags                 | Constraints |
|-------------------------|-----------------------|-------------|
| `PERSON_EMAIL`          | `-e`, `--email`       |             |
| `PERSON_NAME`           | `-n`,`--name`         |             |
| `PERSON_PHONE`          | `-p`, `--phone`       |             |
| `PERSON_TAGS`           | `-t`, `--tags`        |             |
| `PERSON_INDEX`          |                       |             |
| `MEMBER_INDEX`          |                       |             |
| `MEMBER_NAME_KEYWORDS`  | `-n`,`--name`         |             |
| `MEMBER_EMAIL_KEYWORDS` | `-e`,`--email`        |             |
| `MEMBER_DESCRIPTION`    | `-d`, `--description` |             |
| `TEAM_NAME`             | `-n`,`--name`         |             |
| `TASK_NAME`             | `-n`, `--name`        |             |
| `TASK_INDEX`            |                       |             |
| `TASK_ASSIGNEES`        | `-a`, `--assignee`    |             |
| `TASK_DEADLINE`         | `-d`, `--deadline`    |             |
| `TASK_NAME_KEYWORDS`    |                       |             |
| `LINK_URL`              | `-l`, `--link`        |             |
| `LINK_NAME`             | `-n`, `--name`        |             |
| `LINK_INDEX`            |                       |             |
| `ORDER`                 |                       |             |



[Back to Table of Contents](#table-of-contents)


### Summary of Person Commands 

| Action | Format, Examples |
|--------|------------------|
| TBC    | TBC              |
| TBC    | TBC              |

[Back to Table of Contents](#table-of-contents)


### Summary of Member Commands

| Action | Format, Examples |
|--------|------------------|
| TBC    | TBC              |
| TBC    | TBC              |

[Back to Table of Contents](#table-of-contents)

### Summary of Team Commands

| Action | Format, Examples |
|--------|------------------|
| TBC    | TBC              |
| TBC    | TBC              |

[Back to Table of Contents](#table-of-contents)

### Summary of Task Commands

| Action | Format, Examples |
|--------|------------------|
| TBC    | TBC              |
| TBC    | TBC              |

[Back to Table of Contents](#table-of-contents)

### Summary of Link Commands

| Action | Format, Examples |
|--------|------------------|
| TBC    | TBC              |
| TBC    | TBC              |

[Back to Table of Contents](#table-of-contents)

### Summary of General Commands

| Action | Format, Examples |
|--------|------------------|
| TBC    | TBC              |
| TBC    | TBC              |

[Back to Table of Contents](#table-of-contents)

## Glossary

### Command

An instruction that is used to perform some feature.

Find out more in the [understanding TruthTable's CLI section](#understanding-truthtables-cli).

### Parameter

Information that is used to modify [commands](#command), often used with [flags](#flag).

For instance, in the `edit person 1 -n Name` example command, 
- `1` is the parameter for the `edit person` command (in this case, `1` refers to the index of a person).
- `Name` is the parameter for the flag `-n` (in this case, `Name` is the new name to be assigned to the person at 
  index `1`).

Find out more in the [understanding TruthTable's CLI section](#understanding-truthtables-cli).

### Flag

Specific characters that are used to modify [commands](#command), often used with [flags](#flag).

For instance, in the `edit person 1 -n Name` example command,
- `-n` is a flag for the `edit person` command (in this case, `-n` is used to update the person's name)

Find out more in the [understanding TruthTable's CLI section](#understanding-truthtables-cli).

### Positional Parameter

### Index

Refers to the position of an item in some collection.

Indexes must be positive integers, so they can only be 1, 2, 3, ...

For instance, in a list of 3 alphabets containing ("a", "b", "c") in that order,
- index 1 would refer to "a"
- index 2 would refer to "b"
- index 3 would refer to "c"

### Alias

An alias is used to indicate that a named item is also known as another specified name.

For instance,
- `e` is an alias for the `edit` command in TruthTable.

### Command Line Interface

A text-based [user interface](#user-interface) which allows the user to perform some action through the use of 
[commands](#command).

Find out more in the [understanding TruthTable's CLI section](#understanding-truthtables-cli).

Examples include
- <a href="https://en.wikipedia.org/wiki/Unix_shell" target="_blank">Unix shells</a> like (sh, zsh, Bash)
- <a href="https://en.wikipedia.org/wiki/Cmd.exe" target="_blank">Command Prompt</a> in the Windows Operating System

### Graphical User Interface 

A visual [user interface](#user-interface) which allows the user to interact with a program using visual elements 
such as shapes, diagrams, and buttons. 

Examples include
- Operating systems like (Microsoft Windows, Apple's macOS, Apple's IOS, Linux)

### User Interface

The user interface is some interface of which we interact with computers and communicate with the device in.

Examples include
- Monitors
- Keyboards

### JSON File

A file which is used to store data in, which adheres to a format called 
<a href="https://en.wikipedia.org/wiki/JSON" target="_blank">JavaScript Object Notation (JSON)</a>.

[Back to Table of Contents](#table-of-contents)

