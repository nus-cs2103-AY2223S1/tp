---
layout: page
title: User Guide
---

Mass Linkers is a powerful Desktop application tool that helps **Computer Science (CS) students** find study support from batchmates, making it easier to form study groups and look for module-related guidance. 

It provides a **centralised** platform for CS students to 
- save their batchmates’ contact and module details  
- search for batchmates with common interests or who are taking similar modules **conveniently** 

It is optimised for use via a **Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI).

In this *User Guide*, we will take you through the many useful features and functions of Mass Linkers, and provide you crucial information on how the different commands are used.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `MassLinkers.jar` from [here](https://github.com/AY2223S1-CS2103T-T11-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Mass Linkers.

4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type a command in the command box and press Enter to execute it. Refer to the section on [Features](#features) below for details and usage of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about command format:**<br>

* Words in `UPPER_CASE` are parameters to be supplied by the user.<br>
  Example:
  * In `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  Example:
  * `n/NAME t/TELEGRAM [i/INTEREST]` can be used as `n/John Doe t/johnxyz i/ai` or as `n/John Doe t/johnxyz` without using `i/INTEREST`.

* Items with `...` after them can be used multiple times.<br>
  Examples:
  * `[i/INTEREST]...` can be used as `i/ai`, `i/algo i/swe` etc.<br>
  * `[MORE_MODULES]...` can be used as `cs2100`, `cs2103t cs2101 cs2105` etc.

* Parameters can be in any order.<br>
  Example:
  * If the command specifies `n/NAME t/TELEGRAM [g/GITHUB] [p/PHONE] [e/EMAIL] [i/INTEREST]`, then `[i/INTEREST] [e/EMAIL] [p/PHONE] n/NAME [g/GITHUB] t/TELEGRAM` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.
  Example:
  * If you specify `n/John Doe n/Bob Tan`, only `n/Bob Tan` will be saved.
  * If you specify `p/12341234` `p/56785678`, only `p/56785678` will be saved.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
  Example:
  * If the command specifies `help 123`, it will be interpreted as `help`.
  * If the command specifies `exit 345`, it will be interpreted as `exit`.

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about parameters:**<br>

* For all commands involving `INDEX`, `INDEX` refers to the index number shown in the currently displayed list.<br>
  * ___Beware!!!___ This may not be the full list of batchmates you have in Mass Linkers! For example, you may have entered the [find command](#find-a-batchmate-find), so the currently displayed list will be the result of `find` which is not the full list.
  * The index __must be a positive integer__, e.g. 1, 2, 3 … and be smaller than or equal to the number of batchmates in the currently displayed list.
  
</div>

### Batchmate commands
#### Add a batchmate: `add`

Adds a batchmate to the list of batchmates in the Students panel.

Format: `add n/NAME t/TELEGRAM [g/GITHUB] [p/PHONE] [e/EMAIL] [i/INTEREST]... [m/MODULE]...`

* A summary of the requirements of each parameter can be found under [Parameter Requirements](#parameter-requirements).
* Only unique batchmate can be added. It is considered a duplicate if an existing batchmate and the current batchmate to be added have identical Telegram handle, GitHub username or email address.
* Modules added to a batchmate will be automatically categorised according to their prefixes. e.g. `cs2103t` will be tagged as `Computer Science`.  `ma1521` will be tagged as `Mathematics`. For modules that are not identified by Mass Linkers, they will be tagged as `Unrestricted Elective`. More information can be found under [Module Categorisation](#module-categorisation).

Examples:
* `add n/John Doe t/johnxyz` adds a batchmate named `John Doe` with telegram handle `johnxyz` to the list.
* `add n/John Doe t/johnxyz g/johndoe p/98765432 e/johnd@example.com i/ai i/swe` adds a batchmate named `John Doe` with telegram handle `johnxyz`, github username `johndoe`, phone number `98765432`, email address `johnd@example.com` and interests in `ai` and `swe` to the list.
* `add n/John Doe t/johnxyz m/cs2103t m/cs2101` adds a batchmate named `John Doe` with telegram handle `johnxyz` and modules `cs2103t` and `cs2101` to the list.

#### Edit a batchmate: `edit`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To edit a module, simply delete that module using [__mod delete__](#delete-module-from-a-batchmate-mod-delete) and add the new module using [__mod add__](#add-module-to-a-batchmate-mod-add).
</div>

Edits the information of a specified batchmate in the Students panel.

Format: `edit INDEX [n/NAME] [t/TELEGRAM] [g/GITHUB] [p/PHONE] [e/EMAIL] [i/INTEREST]...`

* Edits the batchmate at the specific `INDEX` in the __currently displayed list__ in the Students panel. Refer to the section on _Notes about parameters_ at the start of [Features](#features) for more details.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing interests, the existing interests of the batchmate will be removed i.e adding of interests is not cumulative.
* You can remove all the batchmate’s interests by typing `i/` without specifying any interests after it.

Examples:
* `edit 1 g/johndoe p/91234567 e/johndoe@example.com` edits the github username, phone number and email address of the 1st batchmate in the currently displayed list to be `johndoe`, `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Bob Tan i/` edits the name of the 2nd batchmate in the currently displayed list to be `Bob Tan` and clears all existing interests.

#### Delete a batchmate: `delete`

Deletes a specified batchmate from the Students panel.

Format: `delete INDEX`

* Deletes the batchmate at the specific `INDEX` in the __currently displayed list__ in the Students panel. Refer to the section on _Notes about parameters_ at the start of [Features](#features) for more details.

Examples:
* `list` followed by `delete 2` deletes the 2nd batchmate in the full list of batchmates you have in Mass Linkers.
* `find Betsy` followed by `delete 1` deletes the 1st batchmate in the currently displayed list of the `find` command.

#### Find a batchmate: `find`

Finds batchmates whose details contain __any__ of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]...`

* `NAME`, `TELEGRAM`, `GITHUB`, `PHONE` and `EMAIL` are searched.
* The search is case-insensitive. e.g `Hans` will return `hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will return both `Hans Bo` and `Bo Hans`.
* To search using `NAME`, only __full words__ will be matched. e.g. `Ha` will _not_ return a batchmate with the name `Hans`.
* To search using `TELEGRAM`, `GITHUB`, `PHONE` and `EMAIL`, partial words are accepted. E.g. `boh` will return `bohans`.
* Batchmates matching at least one keyword will be returned. e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:
* When you want to search using `NAME`:
  * `find John` returns `john` and `John Doe`.
  * `find alex david` returns `Alex Tan`, `David Chua`.
* When you want to search using `TELEGRAM`, `GITHUB` or `EMAIL`:
  * `find boh` returns the batchmates with telegram handle, github username or email address containing `boh`, e.g. `bohans`.
* When you want to search using `PHONE`:
  * `find 999` returns the batchmates with phone number `69998888`, `89991234` or `99912345`.

#### List all batchmates: `list`

Shows a list of all batchmates in the Students panel.

Format: `list`

### Interest commands

#### Add interests: `addInt`

Adds interest(s) to a specified batchmate in the Students panel.

Format: `addInt INDEX INTEREST [MORE_INTERESTS]...`

* Adds interest(s) to the batchmate at the specific INDEX in the __currently displayed list__ in the Students panel. Refer to the section on _Notes about parameters_ at the start of [Features](#features) for more details.
* Interests added are case insensitive, they will be displayed in lower casing. 

Examples:
* `addInt 1 algo` adds the interest `algo` to the 1st batchmate in the currently displayed list.
* `addInt 3 database swe machinelearning` adds the interests `database`, `swe` and `machinelearning` to the 3rd batchmate in the currently displayed list.

#### Delete interests: `deleteInt`

Delete interest(s) from a specified batchmate in the Students panel.

Format: `deleteInt INDEX INTEREST [MORE_INTERESTS]...`

* Deletes interest(s) from the batchmate at the specific INDEX in the __currently displayed list__ in the Students panel. Refer to the section on _Notes about parameters_ at the start of [Features](#features) for more details.

Examples:
* `deleteInt 1 ai` deletes the interest `ai` from the 1st batchmate in the currently displayed list.
* `deleteInt 3 ai swe` deletes the interests `ai` and `swe` from the 3rd batchmate in the currently displayed list.

#### Find batchmates by interests: `findInt`

Finds batchmates whose interests contain __all__ the specified interests.

Format: `findInt INTEREST [MORE_INTERESTS]...`

* The search is case-insensitive. e.g. `machinelearning` will match `machinelearning`.
* Only exact words will be matched. e.g. `sw` will not match `swe`.

Examples:
* `findInt ai` returns all batchmates whose interests contain `ai`.
* `findInt swe security` returns all batchmates whose interests contain both `swe` and `security`.

### Module commands

#### Add module to a batchmate: `mod add`

Adds module(s) to a specified batchmate in the Modules panel.

Format: `mod add INDEX MODULE [MORE_MODULES]...`

* Adds module(s) to the batchmate at the specific `INDEX` in the __currently displayed list__ in the Modules panel. Refer to the section on _Notes about parameters_ at the start of [Features](#features) for more details.
* * Modules added to a batchmate will be automatically categorised according to their prefixes. e.g. `cs2103t` will be tagged as `Computer Science`.  `ma1521` will be tagged as `Mathematics`. For modules that are not identified by Mass Linkers, they will be tagged as `Unrestricted Elective`. More information can be found under [Module Categorisation](#module-categorisation).

Examples:
* `mod add 1 cs2103t` adds the module `CS2103T` to the 1st batchmate in the currently displayed list.
* `mod add 3 cs2100 cs2103t cs2101 cs2105` adds the modules `CS2100`, `CS2103T`, `CS2101` and `CS2105` to the 3rd batchmate in the currently displayed list.

#### Delete module from a batchmate: `mod delete`

Deletes module(s) from a specified batchmate in the Modules panel.

Format: `mod delete INDEX MODULE [MORE_MODULES]...`

* Deletes module(s) from the batchmate at the specific `INDEX` in the __currently displayed list__ in the Modules panel. Refer to the section on _Notes about parameters_ at the start of [Features](#features) for more details.

Examples:
* `mod delete 1 cs2103t` deletes the module `CS2103T` from the 1st batchmate in the currently displayed list.
* `mod delete 3 cs2100 cs2103t cs2101 cs2105` deletes the modules `CS2100`, `CS2103T`, `CS2101` and `CS2105` from the 3rd batchmate in the currently displayed list.

#### View a batchmate's modules
Views the list of modules taken by a batchmate in the Modules panel.

Left-click the row with the batchmate's name in the Students panel.

- The selected row would turn blue and the Modules panel would display all the modules taken by the batchmate.

#### Mark module as taken: `mod mark`

Marks module(s) of a specified batchmate as `taken` in the Modules panel, which means the batchmate has taken the module(s) before.

Format: `mod mark INDEX MODULE [MORE_MODULES]...`

* Marks module(s) of the batchmate at the specific `INDEX` in the __currently displayed list__ in the Modules panel. Refer to the section on _Notes about parameters_ at the start of [Features](#features) for more details.

Examples:
* `mod mark 1 cs2103t` marks the module `CS2103T` of the 1st batchmate in the currently displayed list as `taken`.
* `mod mark 3 cs2100 cs2103t cs2101 cs2105` marks the modules `CS2100`, `CS2103T`, `CS2101` and `CS2105` of the 3rd batchmate in the currently displayed list as `taken`.

#### Unmark module as not taken: `mod unmark`

Unmarks module(s) of a specified batchmate and updates the status as `taking` in the Modules panel, which means the batchmate is currently taking the module(s).

Format: `mod unmark INDEX MODULE [MORE_MODULES]...`

* Unmarks module(s) of the batchmate at the specific `INDEX` in the __currently displayed list__ in the Modules panel. Refer to the section on _Notes about parameters_ at the start of [Features](#features) for more details.

Examples:
* `mod unmark 1 cs2103t` unmarks the module `CS2103T` of the 1st batchmate in the currently displayed list as `not taken`.
* `mod unmark 3 cs2100 cs2103t cs2101 cs2105` unmarks the modules `CS2100`, `CS2103T`, `CS2101` and `CS2105` of the 3rd batchmate in the currently displayed list as `not taken`.

#### Mark all modules as taken: `mod mark all`
<div markdown="span" class="alert alert-warning">:warning: **Warning:** The mod mark all command is irreversible. Only execute it if you intend to mark all existing modules for all batchmates as taken. 
 </div>
Marks all current modules of every batchmate in Mass Linkers as `taken` in the Modules panel. This makes it convenient to update the module status of all existing modules of every batchmate as taken after each semester.

Format: `mod mark all`

#### Find batchmates taking specified modules: `mod find`

Finds batchmates with modules matching __all__ the specified modules.

Format: `mod find MODULE [MORE_MODULES]...`

* The search is case-insensitive. e.g `cs2100` will match `CS2100`.
* Only __full words__ will be matched. e.g. `cs21` will _not_ return batchmates with the mods `CS2100` and `CS2101`.

Examples:
* `mod find cs2100` returns batchmates with the module `CS2100`.
* `mod find cs2101 cs2103t` returns batchmates with both modules `CS2101` and `CS2103T`.

#### Find modules taken or taking: `mod find taken` or `mod find taking`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This is an <i>extension</i> of [__mod find__](#find-batchmates-taking-specified-modules-mod-find). The rules listed above for [__mod find__](#find-batchmates-taking-specified-modules-mod-find) apply to this feature too.
</div>

Finds batchmates who have taken or are taking __all__ the specified modules.

Format: `mod find taken MODULE [MORE_MODULES]...` or `mod find taking MODULE [MORE_MODULES]...`

Examples:
* `mod find taken cs2100` returns batchmates who have taken `CS2100`.
* `mod find taken cs2101 cs2103t` returns batchmates who have taken both `CS2101` and `CS2103T`.
* `mod find taking cs2100` returns batchmates who are taking `CS2100`.
* `mod find taking cs2101 cs2103t` returns batchmates who are taking both `CS2101` and `CS2103T`.


### General commands

#### Clear all data: `clear`
<div markdown="span" class="alert alert-warning">:warning: **Warning:** The clear command is irreversible. Only execute it if you intend to clear all existing data. 
 </div>
Clears all existing data in Mass Linkers. 

Format: `clear`

#### View help: `help`

Shows a brief summary of commands with their syntax and a link to the user guide. You can also click the `Open User Guide` button which will redirect you to the user guide in your browser.

Format: `help`

#### Exit the program : `exit`

Exits Mass Linkers.

Format: `exit`

#### Save the data

Data in Mass Linkers is saved in the hard disk automatically after executing any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

### Parameter Requirements

Below is the summary of requirements of each parameter for the various commands. 

| Parameter       | Requirements                                                                                                                                                                                                   |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Name            | Only alphabetical with spaces allowed.                                                                                                                                                                         |
| Telegram handle | Case insensitive. Only contain `a-z`, `0-9`, underscores and have a minimum length of 5 characters.<br> Consecutive underscores are not allowed. <br> Starting or/and ending with underscores are not allowed. |
| Phone number    | Only numerical characters of at least length 3.                                                                                                                                                                |
| GitHub username | Case insensitive. Follows the requirements as stated [here](https://github.com/shinnn/github-username-regex#:~:text=Github%20username%20may%20only%20contain,Maximum%20is%2039%20characters.).                 |
| Interest        | Only alphanumerical characters allowed.                                                                                                                                                                        |

--------------------------------------------------------------------------------------------------------------------

### Module Categorisation

Modules are automatically categorised upon creation.

Below is the categorisation:

| Category              | Module Prefix      |
|-----------------------|--------------------|
| Computer Science      | CS, IS, CP         |
| Mathematics           | ST, MA             |
| Science               | LS, CM, PC         |
| General Education     | GE, UT             |
| Unrestricted Elective | All other prefixes |

_Module prefix refers to the first two characters of every module name._

--------------------------------------------------------------------------------------------------------------------

## FAQ
#### Technical Support
**Q**: How do I transfer my data to another Computer?<br>
**A**: Install Mass Linkers in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Mass Linkers home folder.

**Q**: Do I need internet connection to use Mass Linkers?<br>
**A**: No, internet connection is not required. 

**Q**: I am using Mac and I tried opening the `MassLinkers.jar` file by double-clicking it in Finder. It says *"MassLinkers.jar" cannot be opened because it is from an unidentified developer.*<br>
**A**: Right click the `MassLinkers.jar file` and select `Open`. When a warning message that says _macOS cannot verify the developer of "MassLinkers.jar". Are you sure you want to open it?_ appears, select `Open`. Alternatively, you can right click the `MassLinkers.jar` file and select `Open with JavaLauncher(default)`. 

**Q**: Will the data of batchmates be saved if the program was not closed via the ```exit``` command?<br>
**A**: Yes, the data will still be saved if the program is closed by closing the application window directly. However, we advise using the ```exit``` command for a better user experience.

**Q**: I have accidentally cleared all the data. Is there any way to undo the changes?<br>
**A**: Currently, there is no ```undo``` feature and the ```clear``` command is irreversible. Hence, it is extremely important to only use it when you want an empty Student and Module table. However, we are looking into adding ```undo``` and ```redo``` commands for future developments.

**Q**: I have added a batchmate and its row exceeds the current displayed list of batchmates in the Students panel. I have to scroll below to view the newly added batchmate. Is this expected?<br>
**A**: Yes, adding a new batchmate would not auto scroll the Student panel to the bottom of the list.

#### Privacy Issues
**Q**: What if I do not want to share some of my personal data like my phone number and email address?<br>
**A**: While it is every student's responsibility to exercise discretion in sharing their batchmates' contacts, Mass Linkers has made more sensitive data fields such as GitHub, Phone and Email optional. In this way, the only mandatory fields are your name and telegram handle. If you are uncomfortable sharing your name, you can use a pseudo-name for identification purposes. 

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                           | Format                                                                                    | Examples                                                                                          |
|----------------------------------|-------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| **Help**                         | `help`                                                                                    | `help`                                                                                            |
| **Add**                          | `add n/NAME t/TELEGRAM [g/GITHUB] [p/PHONE] [e/EMAIL] [i/INTEREST]... [m/MODULE]...`      | `add n/John Doe t/johnxyz g/johndoe p/98765432 e/johnd@example.com i/AI i/swe m/cs2103t m/cs2101` |
| **List**                         | `list`                                                                                    | `list`                                                                                            |
| **Edit**                         | `edit INDEX [n/NAME] [t/TELEGRAM] [g/GITHUB] [p/PHONE] [e/EMAIL] [i/INTEREST]...`         | `edit 1 g/johndoe p/91234567 e/johndoe@example.com`                                               |
| **Find**                         | `find KEYWORD [MORE_KEYWORDS]...`                                                         | `find Alex david`                                                                                 |
| **Add interest**                 | `addInt INDEX INTEREST [MORE_INTERESTS]...`                                               | `addInt 3 algo ai swe`                                                                            |
| **Delete interest**              | `deleteInt INDEX INTEREST [MORE_INTERESTS]...`                                            | `deleteInt 3 ai swe`                                                                              |
| **Find by interest**             | `findInt INTEREST [MORE_INTEREST]...`                                                     | `findInt ai swe`                                                                                  |
| **Delete**                       | `delete INDEX`                                                                            | `delete 2`                                                                                        |
| **Add module**                   | `mod add INDEX MODULE [MORE_MODULES]...`                                                  | `mod add 3 cs2100 cs2103t cs2101 cs2105`                                                          |
| **Delete module**                | `mod delete INDEX MODULE [MORE_MODULES]...`                                               | `mod delete 3 cs2100 cs2103t cs2101 cs2105`                                                       |
| **Mark module**                  | `mod mark INDEX MODULE [MORE_MODULES]...`                                                 | `mod mark 3 cs2100 cs2103t cs2101 cs2105`                                                         |
| **Unmark module**                | `mod unmark INDEX MODULE [MORE_MODULES]...`                                               | `mod unmark 3 cs2100 cs2103t cs2101 cs2105`                                                       |
| **Mark all modules**             | `mod mark all`                                                                            | `mod mark all`                                                                                    |
| **Find module**                  | `mod find MODULE [MORE_MODULES]...`                                                       | `mod find cs2101 cs2103t`                                                                         |
| **Find modules taken or taking** | `mod find taken MODULE [MORE_MODULES]...` <br> `mod find taking MODULE [MORE_MODULES]...` | `mod find taken cs2100` or <br> `mod find taking cs2101 cs2103t`                                  |
| **Clear all data**               | `clear`                                                                                   | `clear`                                                                                           |
| **Exit**                         | `exit`                                                                                    | `exit`                                                                                            |
