---
layout: page
title: User Guide
---

LinkedOn is a **desktop app for managing teams optimized for use via a Command Line Interface (CLI)**. As an employee,
you will be able to easily view which members are in the teams you are on. As an employer, you can edit the team
information and team members in the team. With our app, team management would be easier than ever.

--------------------------------------------------------------------------------------------------------------------

## Quick start

Coming soon!

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the commands:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `create t/TEAM-NAME`, `TEAM-NAME` is a parameter which can be used as `create t/UIDevelopers`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `m/John`, `m/John m/Jane` etc.

* Some commands are available for any user, while some are only available for employers.

</div>

## Commands for any user:

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Viewing your basic information: `myinfo`

Shows your name, email, and contact number.

Format: `myinfo`

### Changing your basic information: `updateinfo`

Changes your name, email, and/or contact number.

Format: `updateinfo n/NAME e/EMAIL c/CONTACT-NUMBER`

Examples:
* `updateinfo n/John Doe e/johndoe@example.com c/12345678` Updates your name to John Doe, 
your email to johndoe@example.com, and your contact number to 12345678.

### Viewing basic information of team members: `view`

Shows the name, email, and contact number of the given person.

Format: `view m/TEAM-MEMBER`

Examples:
*  `view m/Jane Doe` Shows the basic information of Jane Doe.

### Viewing the team members of a certain team: `viewmember`

Shows the team members of the given team.

Format: `viewmember t/TEAM-NAME`

Examples:
* `viewmember t/UIDevelopers` returns the name of the members of UIDevelopers team.

--------------------------------------------------------------------------------------------------------------------

### Commands for employers only:

### Creating a team: `create`

Creates a team with the given name.

Format: `create t/TEAM-NAME`

Examples:
* `create t/UIDevelopers` creates a team with the name UIDevelopers.

### Changing a team's name: `rename`

Changes a team's name to the given name.

Format: `rename o/TEAM-NAME n/NEW-TEAM-NAME`

Examples:
* `rename o/UIDevelopers n/UnitTesters` changes the name of the team UIDevelopers to UnitTesters.

### Deleting a team: `delete`

Deletes the given team.

Format: `delete t/TEAM-NAME`

### Adding team member/s: `add`

Adds the given person/s to the team.

Format: `add t/TEAM-NAME m/TEAM-MEMBER...`

Examples:
* `add t/UIDevelopers m/John m/Jane` Adds John and Jane to the team UIDevelopers.

### Removing team member/s:

Removes the given person/s from the team.

Format: `remove t/TEAM-NAME m/TEAM-MEMBER...`

Examples:
* `remove t/UIDevelopers m/John m/Jane` Removes John and Jane from the team UIDevelopers.

--------------------------------------------------------------------------------------------------------------------

## FAQ

Coming Soon!

--------------------------------------------------------------------------------------------------------------------

## Command summary

### Any User

Action | Format, Examples
--------|------------------
**Help** | `help`
**MyInfo** | `myinfo`
**UpdateInfo** | `updateinfo n/NAME e/EMAIL c/CONTACT-NUMBER`<br> e.g., `updateinfo n/John Doe e/johndoe@example.com c/12345678`
**View** | `view m/TEAM-MEMBER`<br> e.g.,`view m/Jane Doe`
**ViewMember** | `viewmember t/TEAM-NAME`<br> e.g., `viewmember t/UIDevelopers`

### Employer Only

Action | Format, Examples
--------|------------------
**Add** | `add t/TEAM-NAME m/TEAM-MEMBER...`<br> e.g., `add t/UIDevelopers m/John m/Jane`
**Create** | `create t/TEAM-NAME`<br> e.g., `create t/UIDevelopers`
**Delete** | `delete t/TEAM-NAME`
**Remove** | `remove t/TEAM-NAME m/TEAM-MEMBER...`<br> e.g.,`remove t/UIDevelopers m/John m/Jane`
**Rename** | `rename o/TEAM-NAME n/NEW-TEAM-NAME`<br> e.g., `rename o/UIDevelopers n/UnitTesters`
