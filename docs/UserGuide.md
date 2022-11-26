---
layout: page
title: User Guide
---

<div class="toc-no-bullet-points">
  * Table of Contents
  {:toc}
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 1. Introducing WorkBook

Welcome to WorkBook! :wave:

WorkBook is an **internship application tracker** that helps Computing students **prepare sufficiently** for their upcoming interviews to secure that internship.
This minimalistic tool allows you to effortlessly collate and easily manage all of your internship applications' progress.
Furthermore, **tips are given for relevant stages** of your internship applications to give you an edge over other applicants!

This guide provides a step-by-step guide on how you can get started using WorkBook, gives an overview of what you can do in WorkBook and how you can best utilise it to transform your internship hunt positively.
Each documented feature introduces a potential problem you face, how it can be resolved and examples on using the feature.

No time to waste, let's start _Working_! :muscle:

--------------------------------------------------------------------------------------------------------------------

## 2. Getting started

This section helps you get started with WorkBook.

It consists of a step-by-step installation guide and an explanation of the key GUI elements.

### 2.1 Installation

1. Ensure you have [Java 11 or above](https://www.oracle.com/sg/java/technologies/downloads/#java11) installed on your computer.

2. Download the latest `WorkBook.jar` from [here](https://github.com/AY2223S1-CS2103T-T10-3/tp/releases/).

3. Copy the downloaded `WorkBook.jar` to a folder of your choice.

4. Using your command terminal:
   1. Navigate to the folder where you placed your WorkBook at.
   2. Run: `java -jar WorkBook.jar`. <br>The GUI, as shown below in one of the two layouts, should appear within seconds.<br>

<div style="page-break-after: always;"></div>

|          Wide Layout           |             Narrow Layout             | 
|:------------------------------:|:-------------------------------------:|
| ![wide layout](images/Ui2.png) | ![narrow layout](images/UiNarrow.png) |

5. Tell WorkBook what you want to do by typing your command in `Enter command here...` at the top of the application and pressing <kbd>Enter</kbd> on your keyboard to execute it.

6. Before diving right into using WorkBook, familiarise yourself with the [things to note](#4-things-to-note) to not hinder your tracking process!

<div style="page-break-after: always;"></div>

### 2.1 Understanding WorkBook's GUI

Below shows the main elements of WorkBook's GUI, in wide and narrow layouts.

For the narrow layout, everything else is the same except for the labelled light bulb icon, which displays tips.

|              Wide Layout               |
|:--------------------------------------:|
| ![wide layout](images/AnnotatedUi.png) |

<div style="page-break-after: always;"></div>

|                     Narrow Layout                      |  
|:------------------------------------------------------:|
| ![narrow layout](images/AnnotatedUiNarrowBulbOnly.png) |


--------------------------------------------------------------------------------------------------------------------
## 3. Exploring our guide


**Information Box**
<div markdown="block" class="alert alert-info">
**:information_source: Info:** Provides extra information that is useful
</div>

**Warning Box**
<div markdown="block" class="alert alert-warning">
**:exclamation: Warning: Important messages**
</div>

**Tip Box**
<div markdown="block" class="alert alert-success">
**:bulb: Tip:** Provides pointers to enhance your experience using the application
</div>

**Highlights** <br>
`commands` or `PARAMETERS`

--------------------------------------------------------------------------------------------------------------------

## 4. Things to note

This section describes important information for you to take note of before and while using WorkBook. In order to make WorkBook work best for you, do familiarise yourself with the items in this section.

### 4.1 Notes about the command format:

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add c/COMPANY`, `COMPANY` is a parameter which can be used as `add c/Meta`.

* Items in square brackets are optional.<br>
  e.g `c/COMPANY [t/TAG]` can be used as `c/Meta t/unattainable` or as `c/Meta`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be omitted, used once: `t/unattainable`, or multiple times: `t/unattainable t/AWS`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/COMPANY s/STAGE`, `s/STAGE c/COMPANY` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `r/Frontend r/Backend`, only `r/Backend` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### 4.2 Parameter-specific behaviour

* Whenever `[d/DATETIME]` is specified as a parameter, you should input it in the format `dd-MMM-yyy hh:mm` where `y` is year, `M` is month, `d` is day, `h` is hour in the 24-hour format and `m` is minutes. <br>
  e.g. October 2 2022 5:00pm should be inputted as `02-Oct-2022 17:00`.
  * Month is not case-sensitive

### 4.3 Behaviour of sorted internship applications

* The list of applications are sorted downwards from the closest upcoming application to furthest.
* Those with no `DATETIME` attached to it will be placed below those with upcoming dates.
* Applications in the past (i.e. `DATETIME` is past current time) will be placed at the bottom of the list, sorted downwards as well from the most recently passed.
* An example is shown below:
![SortExample](images/SortExample.png)

<div style="page-break-after: always;"></div>

### 4.4 Responsive UI when resizing window

* The UI will change dynamically depending on the window width.

* If the window width is too small, WorkBook will switch to a narrow layout for a better UX.

* Otherwise, WorkBook will be in the wide layout which has an extra right panel for displaying tips.

|              Wide Layout               |
|:--------------------------------------:|
| Resize the window to a wider width to get this layout |
| ![wide layout](images/AnnotatedUi.png) |

<div style="page-break-after: always;"></div>

|                     Narrow Layout                      |  
|:------------------------------------------------------:|
| Resize the window to a narrower width to get this layout |
| ![narrow layout](images/AnnotatedUiNarrowBulbOnly.png) |



## 5. What you can do

| Your action | Command format <br> e.g. Example command (If applicable)                                                                                                                                                  |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**     | `add c/COMPANY r/ROLE s/STAGE [d/DATETIME] [e/COMPANY_EMAIL] [l/LANGUAGE TAG]… [t/TAG]…​` <br> e.g., `add c/Bytedance r/Backend Engineer s/Online Assessment d/24-Sep-2022 15:00 t/high pay l/Javascript` |
| **Edit**    | `edit INDEX [c/COMPANY] [d/DATETIME] [e/COMPANY_EMAIL] [r/ROLE] [l/LANGUAGE TAG]… [t/TAG]…​​`<br> e.g.,`edit 2 c/Meta e/hr@meta.com`                                                                      |
| **Find**    | `find [c/COMPANY] [r/ROLE] [s/STAGE]`<br> e.g., `find c/Meta`                                                                                                                                             |
| **Delete**  | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                       |
| **List**    | `list`                                                                                                                                                                                                    |
| **Undo**    | `undo`                                                                                                                                                                                                    |
| **Redo**    | `redo`                                                                                                                                                                                                    |
| **Clear**   | `clear`                                                                                                                                                                                                   |
| **Help**    | `help`                                                                                                                                                                                                    |
| **Exit**    | `exit`                                                                                                                                                                                                    |

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
* Commands without examples are considered trivial and can be executed by inputting the command without additional parameters.
* Commands are **case-sensitive**!

</div>

### 5.1. Adding your internship application

Have you taken the first step to apply for that internship you've always wanted?
Or do you want to start keeping track of the different internship applications you have? <br> 

This command satisfies the above, allowing you to add your internship applications to the list and track all of them.
WorkBook also removes the hassle of sorting your internship applications manually by automating it for you!

Simply follow the command format below to add the relevant details into WorkBook!

Format: `add c/COMPANY r/ROLE s/STAGE [d/DATETIME] [e/COMPANY_EMAIL] [l/LANGUAGE TAG]…​ [t/TAG]…​`

Example: `add c/Meta r/Web Developer s/Application Sent d/20-Oct-2022 10:00 l/Java e/metaHires@meta.com` <br><br>
What you will see:

|                What you will see                 |
|:------------------------------------------------:|
| ![AddCommandResult](images/AddCommandResult.png) |


<div markdown="block" class="alert alert-success">
**:bulb: Tip:** The date and time you provide could represent multiple things:<br>
  * The date and time it happened
  * The deadline of the corresponding `Stage`
</div>

<div markdown="block" class="alert alert-warning">
**:exclamation: Caution**
* adding DateTime like `d/24-Sep-2022 24:00` will be read as `d/25-Sep-2022 00:00`
</div>

Other examples:
* `add c/Shopback r/Blockchain Developer s/Technical Interview`
* `add c/Bytedance r/Backend Engineer s/Online Assessment d/24-Sep-2022 15:00 t/high pay l/Java l/Python`

### 5.2. Editing your internship application

WorkBook allows you to seamlessly update fields of respective internship applications with this command.

A good time to use this command is to update the stage of your application so that WorkBook can give you relevant tips and reminders!

Format: `edit INDEX [c/COMPANY] [r/ROLE] [s/STAGE] [d/DATETIME] [e/COMPANY_EMAIL] [l/LANGUAGE TAG] [t/TAG]…​`

* Edits the internship at the specified `INDEX`, which **must be**:
  * A positive number (1, 2, 3, …​).
  * Within the number range of your list of internship applications.
* At least one of the optional fields **must** be provided.

Example: `edit 2 s/Technical Interview d/28-Dec-2022 08:00`

What you'll see before and after executing the command: <br>

| State      | What you will see                           |
|------------|---------------------------------------------|
| **Before** | ![Before edit](images/EditCommandInput.png) |
| **After**  | ![After edit](images/EditCommandResult.png) |


<div markdown="block" class="alert alert-warning">
**:exclamation: Caution**: Adding of tags is not cumulative: When editing tags, the existing tags of the internship will be **removed**.
</div>

<div markdown="block" class="alert alert-success">
**:bulb: Tip:** As adding of tags is not cumulative, you can remove all the internship’s tags by typing `t/` without
    specifying any tags after it.
</div>

Other examples:
* `edit 2 l/golang t/`: Adds `golang` as the only language tag for the second internship and clears all existing tags.

### 5.3. Listing all existing internship applications

Lists all of your internship applications in WorkBook in a [sorted order](#43-behaviour-of-sorted-internship-applications).

<div markdown="block" class="alert alert-success">
**:bulb: Tip:** Your most recent upcoming internship application will be at the top of the list!
</div>

Format: `list`

### 5.4. Viewing tips for your applications:

For some application stages, we have included a list of useful tips to help you prepare for and ace the deliverables.

Below are the application stages that we have included tips for.

| Application Stage      |
|------------------------|
| `Application Sent`     |
| `Online Assessment`    |
| `Technical Interview`  |
| `Behavioral Interview` |
| `Phone Interview`      |
| `Rejected`             |

If WorkBook is in the [narrow layout](#44-responsive-ui-when-resizing-window), simply click on the light bulb icon to view the tips for that application. A window will appear showing all the tips for that application.

|                Light Bulb Button                |              Tips Window              |
|:-----------------------------------------------:|:-------------------------------------:|
| ![light bulb button](images/ClickLightBulb.png) | ![tips window](images/TipsWindow.png) |

To view the tips in the [wide layout](#44-responsive-ui-when-resizing-window), simply click and select the application you would like to view the tips for. The tips will appear in the right panel.

### 5.5. Finding your internships

If you wanted to view all your internship applications corresponding to a particular company, stage or role,
then this command is for you! <br>

It can find internships whose company, role or stage names contain all the respective keywords.

Format: `find c/COMPANY | r/ROLE | s/STAGE`

* Displays a list of internships that match all the keywords.

<div markdown="block" class="alert alert-success">
**:bulb: Tip:** <br>
* Case does not matter e.g. `meta` will match `Meta`.
* Order of the keywords does not matter e.g. `Jane Street` will match `Street Jane`.
</div>

<div markdown="block" class="alert alert-warning">
**:exclamation: Caution** <br>
* Only full words will be matched e.g. `met` will not match `Meta`.
* Exactly one attribute can be searched for either Company, Role or Stage.
* If you input keywords for more than one prefix it will result in an invalid command e.g. `find c/Meta s/Interview` will not be accepted
</div>


<div style="page-break-after: always;"></div>

Examples:
* `find r/Engineer` returns `Software Engineer` and `Backend Engineer`:

|                   Before                    |                   After                   |
|:-------------------------------------------:|:-----------------------------------------:|
| ![before layout](images/BeforeFindRole.png) | ![after layout](images/AfterFindRole.png) |


### 5.6. Deleting your internship application

If you want to remove an internship application then this command
deletes the specified internship application from WorkBook.

Format: `delete INDEX`

* Deletes the internship at the specified `INDEX`, which **must be**:
  * A positive number (1, 2, 3, …​).
  * Within the number range of your list of internship applications.

Examples:
* `list` followed by `delete 2` deletes the 2nd internship application in WorkBook.
* `find Meta` followed by `delete 1` deletes the 1st internship application within the results of the `find` command.

### 5.7. Clearing your existing internship applications

Be it getting ready for a new internship application cycle or wanting to do a general spring-cleaning of your 
list of internship applications in WorkBook, this command is the one for you!

Use this command to clear all the applications you've previously saved in WorkBook.

Format: `clear`

Example:
* `clear` removes all internship applications in the WorkBook.

<div markdown="block" class="alert alert-success">
**:bulb: Tip:** If you cleared your internship applications by mistake, fret not as you can easily undo this!
</div>

### 5.8. Undoing your previous command

What if you accidentally made a mistake and performed a wrong <em>undoable</em> command?
Workbook can help you to undo your changes after performing an 
undesirable <em>undoable</em> command and restore itself to the 
previous version!

WorkBook keeps track of all your previous <em>undoable</em> commands and its current version, allowing you to `undo` as many times
to restore any desired state of your WorkBook.

Format: `undo`
* Undo the previous <em>undoable</em> command.
* You can perform the `undo` command as many times as the number of tracked Workbook versions
i.e. stacking 2 undo commands sequentially will revert the WorkBook back 2
versions ago, assuming you performed at least 2 <em>undoable</em> commands previously!
* When you perform an <em>undoable</em> command, it will be stacked on top of the
previous <em>undoable</em> command in the stack. The `undo` command will undo the first <em>undoable</em> command at the
top of the stack.

* If you have not executed an <em>undoable</em> command previously, the WorkBook will remain in its current version
and return an error message: "No previous changes to undo!". 

![UndoFailure](images/UndoFailure.png)

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>
You can only undo <em>undoable</em> commands!
* `add`
* `edit`
* `delete`
* `clear`
* `redo`
</div>

Examples:
* `add c/Meta r/Frontend Engineer s/Application Sent d/29-Oct-2022 12:00 e/hrmonkey@example.com` followed by `undo` will
display the WorkBook version without the added internship.
* `edit 1 s/Behavioural Interview e/hr@meta.com` followed by `clear` and then `undo` will undo the 
clearing of the Workbook. The edited stage and email address of the first internship will remain in the 
displayed WorkBook version.
* `delete 2` followed by `list` and then `undo` will restore the WorkBook to its version prior to the 
`delete 2` command as the `list` command is not an <em>undoable</em> command and does not create a new
version of the WorkBook.
* `edit 2 l/golang t/` followed by `delete 1` and then 2 consecutive `undo` commands will first restore the
WorkBook to its version before the `delete 1` command, and then the version before the `edit` command.

What you'll see before and after executing the command: <br>

| State                                            | What you will see                      |
|--------------------------------------------------|----------------------------------------|
| **Before**                                       | ![BeforeEdit](images/BeforeEdit.png)   |
| **After executing command `edit 2 l/golang t/`** | ![AfterEdit](images/AfterEdit.png)     |
| **After executing command `delete 1`**           | ![AfterDelete](images/AfterDelete.png) |
| **After executing command: `undo`**              | ![AfterUndo1](images/AfterUndo1.png)   |
| **After executing command: `undo`**              | ![AfterUndo2](images/AfterUndo2.png)   |

<div markdown="block" class="alert alert-success">

**:bulb: Tip:** <br>
* If you have undone your previous undoable command by mistake, fret not as you can easily redo this 
to reapply the initial changes!
* Performing multiple `undo` commands may confuse you as to which <em>undoable</em> command you are
undoing and how your WorkBook currently looks like!
* Tracked WorkBook versions will reset once you `exit` the application!

</div>

### 5.9. Redoing your previous command

Now, what if you mistakenly performed an `undo` command?
Workbook can help you to redo your changes after performing 
an undesirable `undo` command and restore itself to the version before that!

WorkBook keeps track of all your previous <em>undoable</em> commands and its current version,
allowing the `redo` command to be performed as many times to reverse the
`undo` commands you have performed!

Format: `redo`
* Redo the previous `undo` command.
* You can perform the redo command as many times as the number of undone versions in the Workbook's tracked versions
i.e. stacking 2 redo commands sequentially will revert the WorkBook back to its version 2
  `undo` commands ago, assuming that you performed at least 2 `undo` commands previously.
* When you perform an `undo` command, it will be stacked on top of the 
previous `undo` command in the stack. The `redo` command will redo the first `undo` command at the
top of the stack.
* If you have not executed a previous `undo` command, the WorkBook will remain in its current state
  and return an error message: "No more commands to redo!".

![RedoFailure](images/RedoFailure.png)

Examples:
* `add c/Meta r/Frontend Engineer s/Application Sent d/29-Oct-2022 12:00 e/hrmonkey@example.com` followed by `undo` 
and then `redo` will restore the WorkBook version with the added internship.
* `edit 1 s/Behavioural Interview e/hr@visa.com` followed by `clear` will create 2 new WorkBook versions. 
If you perform 2 consecutive `undo` commands, the WorkBook will be restored to the version before both
`edit` and `clear` commands. When you perform a `redo` command now,
the WorkBook version will contain the edited stage and email for the first internship.

<div style="page-break-after: always;"></div>


| State                                                                      | What you will see                        |
|----------------------------------------------------------------------------|------------------------------------------|
| **After executing command `edit 1 s/Behavioural Interview e/hr@visa.com`** | ![AfterEdit](images/RedoAfterEdit.png)   |
| **After executing command `clear`**                                        | ![AfterClear](images/RedoAfterClear.png) |
| **After executing command: `undo`**                                        | ![AfterUndo1](images/RedoAfterUndo1.png) |
| **After executing command: `undo`**                                        | ![AfterUndo2](images/RedoAfterUndo2.png) |
| **After executing command: `redo`**                                        | ![AfterRedo](images/RedoAfterRedo.png)   |

<div markdown="block" class="alert alert-success">

**:bulb: Tip:** <br>
* If you have redone your previous undo command by mistake, fret not as you can easily undo this again
to remove the initial changes!
* Performing multiple `redo` commands may confuse you as to which `undo` command you are
redoing and how your WorkBook currently looks like!
* Tracked WorkBook versions will reset once you `exit` the application!
</div>

### 5.10. Viewing help

This shows a summary of the commands as well as a link to this User Guide.

Format: `help`

### 5.11. Exiting the program

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## 6. Managing your data

### 6.1. Saving

Conveniently, any inputted command that changes any part of your internship application is **automatically saved**!
Hence, you need not worry about pressing <kbd>ctrl</kbd> + <kbd>s</kbd> on your keyboard everytime you update your list of internship applications.

### 6.2. Editing

All of your internship applications are saved in a JSON file under the `data` subfolder. 
You are free to update any internship application directly by editing that JSON file.

<div markdown="block" class="alert alert-info">

**:information_source: JSON?:**<br>
* JSON stands for JavaScript Object Notation, learn how to edit the file [here](https://www.softwaretestinghelp.com/how-to-open-a-json-file/)!

</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, WorkBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## 7. Frequently asked questions

**Q**: How do I transfer my data to another Computer?<br>
**A**: [Install](#2-get-started) the app in the other computer, copy and override the JSON file in the new `data` subfolder, and you are done!

**Q**: How do I change the theme of WorkBook?<br>
**A**: It is not possible as of now, but will be coming soon!

--------------------------------------------------------------------------------------------------------------------

## 8. Prefix summary

| Prefix | Symbolizes   |
|--------|--------------|
| **n/** | Company Name |
| **s/** | Stage        |
| **r/** | Role         |
| **d/** | DateTime     |
| **e/** | Email        |
| **l/** | Language Tag |
| **t/** | Tag          |

--------------------------------------------------------------------------------------------------------------------

## 9. Glossary

| Term                           | Description                                                                                                                                        |
|--------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| Graphical User Interface (GUI) | GUI allows user to interact with an application through graphics such as icons, menu, etc.                                                         |
| Command Line Interface (CLI)   | CLI allows user to use text as commands to be executed by an application.                                                                          |
| Command                        | Instruction typed by the user for WorkBook to execute.                                                                                             |
| Parameter                      | A component of a command for the user to input information. For WorkBook's context, this refers to the internship application details.             |
| Prefix                         | An abbreviation for the name of the parameter. Prefix should be entered before the actual parameter in a command and always ends with a slash (/). |
| Alphanumeric                   | Characters that are either a number or a letter.                                                                                                   |
| Subfolder                      | A folder within a folder.                                                                                                                          |

