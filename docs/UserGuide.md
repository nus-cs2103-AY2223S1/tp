## **Table of contents**

1. [Introduction](#introduction)
2. [About](#about)
3. [Prerequisites](#prerequisites)
4. [Quick start](#quick-start)
5. [Features](#features)
    1. [Definitions](#definitions)
    2. [Scoping](#scoping)
    3. [Basic features](#basic-features)
       1. [Contact commands](#contact-commands)
          1. [Add a contact](#add-a-contact-person-new)
          2. [Delete a contact](#delete-a-contact-person-delete)
          3. [Listing all contacts](#listing-all-contacts-list)
       2. [General commands](#general-commands)
          1. [Clear command](#clear-command-clear)
          2. [Find command](#find-command-find)
          3. [Exit command](#exits-the-program-exit)
       3. [Field commands]()
       4. [Group commands](#group-commands)
          1. [Creating a group](#creating-a-group-team-new)
          2. [Deleting a group](#deleting-a-group-team-delete)
          3. [Navigating to a group](#navigating-to-a-group-cg)
          4. [Adding people to a group](#adding-contacts-to-a-group-assign)
          5. [Removing people from group](#removing-contacts-from-group-team-remove)
       5. [Task commands](#task-commands)
          1. [Adding a task to a group](#adding-a-task-to-a-group-task-add)
          2. [Deleting a task from group](#deleting-a-task-from-group-task-delete)
          3. [Set progress for tasks](#set-progress-for-tasks-task-progress)
    4. [Advanced features](#advanced-features)
        1. [Chaining](#advanced-features-overview-chaining)
        2. [Feature constraints](#advanced-feature-constraints)
        3. [Select command](#select-command)
        4. [Contains command](#contains-command)
        5. [Execute command](#execute-command)
        6. [Replace command](#replace-command)
        7. [Foreach command](#foreach-command)
        8. [If else command](#if--else-command)
        9. [Aliasing](#aliasing)
        10. [Custom command / macro](#custom-command--macro)
6. [FAQ](#faq)
7. [Future plans](#future-plans)
8. [Glossary](#glossary)
9. [Commands summary](#command-summary)
   1. [General commands](#general-commands-summary)
   2. [Contact commands](#contact-commands-summary)
   3. [Group commands](#group-commands-summary)
   4. [Task commands](#task-commands-summary)
   5. [Advanced commands](#advanced-commands-summary)

---

## **Introduction**

> **What is Contactmation?**

Contactmation is a powerful **desktop based project and task management solution** that **helps you efficiently and
effectively manage many projects at once** through the [Command Line Interface (CLI)](#glossary).

Contactmation will be able to help you save all your project member details, keep track of
each project, and delegate tasks to each project.

> **Who is Contactmation for?**

Contactmation is for **project managers and supervisors** who want to maintain an organised view of their
projects and streamline the management of their projects.

---

## **About**

### Purpose

This guide will elaborate on all the features available in Contactmation that will help make your experience of using 
Contactmation pain-free.

Examples with real-world applications are present to give you a clearer idea of how the features 
can be utilised.

### User Guide Navigation

This guide is broken into different sections that will aid you in better understanding our application.

Refer to:
- The [contactmation window guide](#contactmation-window-guide) for a guide on the application window.
- The [basic feature](#basic-features) section to get started on using the application.
- The [advanced feature](#advanced-features) section for powerful tools that will help make your experience of
using Contactmation much more streamlined.
- The [glossary](#glossary) for explanations to the different terms used throughout this guide.
- The [command summary](#command-summary) for a quick overview on how to use all our features.

For a more detailed view of all the features present, please visit the [table of contents](#table-of-contents).

### Contactmation Window Guide

The following figure shows how our application would appear on your screen upon opening Contactmation.
Each part of our application will be labelled as such:

![Contactmation ui elements](images/ContactmationUi.png)

We will be referring to these terminologies throughout the user guide.

### **Prerequisites**

Before you start up Contactmation on your computer, 

- Ensure that `Java version 11` or above is installed on your device. Do refer to the [FAQ](#faq) if you need help with 
checking whether `Java 11` is installed on your computer, or if you need help with installing `Java 11`.


- The current version of Contactmation can only be used in a desktop, but should work on all operating systems (such as 
Windows, macOS and Linux etc.) as long as `Java 11` is installed.

--- 

## **Before you begin**

Before you begin, you will need to know how to interact with Contactmation.

You may only interact with Contactmation by typing [commands](#glossary) into the 
[command box](#contactmation-window-guide). Upon hitting the `Enter` key, you will be able to execute the command
currently residing in the command box.

> How do I properly write these commands? 
 
We will go through the standardised formats for each [basic](#basic-features) and [advanced](#advanced-features) 
feature in their respective sections. 

> How do I clear the sample data in my newly downloaded version of Contactmation?

Simply type in the word `clear` into the command box and press the `Enter` key on your desktop. All the sample
data will be wiped. Do note that this action is **irreversible**.

> How do I read the standardised formats for each feature?

These formats may look cryptic at first glance. Do visit the [Standardised Format Style](#standardised-format-style)
section to better understand how to read the formats. 

### Standardised Format Style

This section aims to help you better understand the different terminologies used in the format section of each
feature description.

| Format                               | Explanation                                                                          |
| ------------------------------------- | ------------------------------------------------------------------------------- |
| **Square brackets** ([ ]) | Anything that is within the square brackets are entirely **optional**. You may skip anything wrapped in the square bracket and move on to the next [word](#glossary) in the [command sequence](#glossary). |
| **Angled brackets** (< >) | Anything within angled brackets are placeholder words. These words will be replaced by other words for the command to run. The replacing words will be specified in the format section for each feature. |
| **Ellipses** (`...`)      | The word which these ellipses are attached to can be repeated multiple times in a single command. |

#### Combining the format styles

The **innermost** format in a word has the **highest** priority, while the **outermost** format in a word
has a **lower** priority than any format within it. However, since an outer format is considered after the inner format,
the outer format has a **stronger connotation** to it.

Here are additional examples to solidify your understanding of how these formats make sense when combined. 

If the command format in the format section of a feature has `[t/tags...]`, that means that the **ellipses**
will have a higher priority than the **square brackets**. This means that `t/tags` can be repeated multiple
times, but `t/tags` can be entirely optional. Therefore, it is possible to **skip** `t/tags` altogether, or have
1 or more `t/tags` in the command sequence.

Other keywords utilised in the guide are defined in the [glossary](#glossary).

### Making groups within groups

This section aims to help you understand the concept of creating groups under other groups.

> What is "making groups within groups"?
 
Here is a scenario. Let us say you are a boss of a company called **Just_Incorporated**, and you would like to track
the different departments in your company. Therefore, the departments in the company
(e.g. Marketing and Research and development department) could act as groups within Contactmation.

However, let us also say that you are closely monitoring a particular group in the Research and development
department, and let us call this group is **Vero_Ltd**. Then this group, **Vero_Ltd**, would
fall under the **Research and development** group in Contactmation.

Here is how the grouping for this scenario would look like in theory:

![Group within group illustration](images/Group_within_group_illustration.png)

This will be how the grouping looks like in Contactmation:

![Group within group illustration in contactmation](images/Group_within_group_illustration_contactmation.png)

Note that **Vero_Ltd** is listed in group 3 as `/Research_And_Development/Vero_Ltd`.
This means that **Vero_Ltd** is within the group `Research_And_Development`.

> How do I create groups within groups?

You can create groups within groups by using the [cg](#navigate-to-a-team) command.
For more information on how to type the [command](#glossary), please visit the section on
[standardised format styling](#standardised-format-style) that is used to type all commands.

---

## **Quick start**

1. Ensure that the [prerequisites](#prerequisites) are met before installing Contactmation.

2. Download the latest version of `contactmation.jar` from
   [here](https://github.com/AY2223S1-CS2103T-T11-1/tp/releases). The file `contactmation.jar`
    can be found under the `Assets` for each version of Contactmation.

![Find the Contactmation jar file](images/QuickStartFindJar.png)

3. Save `contactmation.jar` into a Desktop folder on your computer. This folder will now be the home folder
    for Contactmation. Do look at the [FAQ](#faq) section for errors related to opening files used in Contactmation.

4. Double-click on `contactmation.jar` to start up the application. You will be greeted with the current window
   if everything goes well:

![Contactmation ui main window](images/ContactmationUiClean.png)

> **Note:** If you wish to clear all default entries, use the [clear](#clear-command-clear) command.


5. You may begin by referring to the [basic features](#basic-features) section to get started on using
   Contactmation. For additional, more powerful commands, refer to our [advanced features](#advanced-features),
   especially if you are comfortable with the CLI or have prior programming experience.

---

## **Features**

With Contactmation, you can not only use the **basic features** to manage your group project, but also use 
**advanced features** to customise your experience to fit your personal needs.

## Basic features

Now that you have finished setting up Contactmation, let’s start performing basic tasks with Contactmation.
As Contactmation aims to help you manage your contacts, project groups and tasks, we will start off by performing
a range of basic commands varying from adding a person to manipulating tasks and teams.

The basic features are categorised as the following:
1. General Commands
2. Contact Commands
3. Team Commands
4. Task Commands

## Contact commands

### Constraints on contact information

The following contact commands comply with these placeholder constraints:

- The `NAME` of the contact must be alphanumeric and can contain whitespace.
- The `PHONE_NUMBER` of the contact must be at least 3 digits long.
- The `EMAIL` of the contact must be in the format `local-part@domain`.

  - `Local-part`: Only contain alphanumeric characters and these special characters, excluding
    the parentheses, (+\_.-). The local-part may not start or end with any special characters.
  - `Domain`:

    - Ends with a domain label at least 2 characters long.
    - Have each domain label start and end with alphanumeric characters.
    - Have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

- The `ADDRESS` can take any values, but it should not be blank.
- The `TAG` must be alphanumeric.
- The `INDEX` must be a positive integer which cannot exceed the number of contacts currently displayed in the
  application.
- The `KEYWORD` and `MORE_KEYWORDS` must be alphanumeric.

### Create a Contact

Let us start off by adding a person to Contactmation. To add a contact, you can use the command `person new`, followed by the name of the person. You can also choose to provide the phone number, email and address to each person, or add a tag to identify each person.

**Format**: `person new n/<NAME> [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

**Examples**:

- `person new n/Spongebob`
- `person new n/Squidward Tentacles p/01234567 e/squidward@gmail.com a/Bikini Bottom Krusty Krab t/Employee`

![Add Person Screenshot](images/user-guide-img/PersonNewScreenshot.PNG)

### Delete a contact

You can use the `person delete` command to delete a contact from the list of persons in the current scope.

**Format**: `person delete <INDEX>`

**Example**:

- `person delete 1`

### Listing all contacts

Contactmation supports the `list` command that displays all of your contacts into a list of persons.

**Format**: `list`

---

## General commands
You can use these commands used on all 3 categories of commands, namely **command**, **team**, and **task** commands.
These commands are also irrespective of the [scope](#scoping) you are in.


### Clear command: `clear`

This command clears all group, contact and task entries from the application. You can do this to commemorate the end of 
a project, and kickstart a new one.


<div markdown="span" class="alert alert-primary">❗ **WARNING** <br>
THIS ACTION IS IRREVERSIBLE! RUN THIS COMMAND AT YOUR OWN DISCRETION**
</div>


**Format:** `clear`

### Find command: `find`

Searches for a contact, group or task that matches the given `KEYWORD`. Searches may also include `MORE_KEYWORDS`
to further narrow the search for a contact, subgroup or task within the current [scope](#scoping).

**Format:** `<ITEM> find <KEYWORD> [<MORE_KEYWORDS>]`

**Examples:**

- `person find John Doe`
- `task find task1 task2`
- `team find task1 task2`

### Exits the program: `exit`

Exits the current session and closes the application.

**Format:** `exit`

---

## Contact commands

Now, you should use these set of commands to add the members of your project.
The commands will allow you to add the necessary information on your members with ease as well.
Moreover, our software ensures that the information of the member you added, as a contact, is valid.
Contact-related commands precede with the `person` keyword.

#### Things to note when using contact commands

The following contact commands comply with these placeholder constraints:

- The `NAME` of the contact must be alphanumeric and can contain whitespace.
- The `PHONE_NUMBER` of the contact must be at least 3 digits long.
- The `EMAIL` of the contact must be in the format `local-part@domain`.

    - `local-part`: Only contain alphanumeric characters and these special characters, excluding
      the parentheses, (+\_.-). The local-part may not start or end with any special characters.
    - `domain`:

        - Ends with a domain label at least 2 characters long.
        - Have each domain label start and end with alphanumeric characters.
        - Have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

- The `ADDRESS` can take any values, but it should not be blank.
- The `TAG` must be alphanumeric.
- The `INDEX` (the number of the person in the list) must be a positive integer which cannot exceed the number of contacts currently displayed in the
  application.
- The `KEYWORD` and `MORE_KEYWORDS` must be alphanumeric.

### Create a Contact

Let us start off by adding a person to Contactmation. To add a contact, you can use the command `person new`, followed by the name of the person. You can also choose to provide the phone number, email and address to each person, or add a tag to identify each person.

**Format**: `person new n/<NAME> [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

**Examples**:

- `person new n/Spongebob`
- `person new n/Squidward Tentacles p/01234567 e/squidward@gmail.com a/Bikini Bottom Krusty Krab t/Employee`

![Add Person Screenshot](images/user-guide-img/PersonNewScreenshot.PNG)


### Delete a contact


You can use the `person delete` command to delete a contact from the list of persons in the current scope.


**Format**: `person delete <INDEX>`

**Example**:

- `person delete 1`
  - this deletes the first person on the person list shown.
  
### Listing all contacts

Contactmation supports the `list` command that displays all of your contacts into a list of persons.


**Format**: `list`

---

## Team commands

Contactmation allows you to group your contacts into teams, which allows you to issue and assign tasks to members of
specific teams. Team-related commands in Contactmation begin with the `team` keyword.


#### Things to note when using team commands

The following team commands comply with these placeholder constraints:

- The `TEAM NAME` must be alphanumeric.
- The `INDEX`(the number of the team in the list) must be a positive integer which cannot exceed the number of contacts / teams currently displayed
  in the application.

### Create a Team

Contactmation provides the `team new` command to create a new team with a specified team name.

**Format**: `team new <TEAM_NAME>`

The above command creates a new team with a specified team name. The team name should not contain any spaces, and should
consist of alphanumeric characters with hyphens or underscores only.

**Examples**:

- `team new CS1101S`
- `team new Krusty_Krab_Employees`

![Create Team Screenshot](images/user-guide-img/CreateTeamScreenshot.PNG)

### Delete a Team

To delete a team from Contactmation, you can use the `team delete` command followed with the team number as specified in
the team list.

**Format**: `team delete <INDEX>`

**Examples**:

- `team delete 1`
  - The above command deletes team number 1 in the list of teams.

### Removing contacts from team: `team remove`

Removes the contact from the current group by their currently specified `INDEX` as shown in
the application window.

**Format:** `team remove <INDEX>`

**Example:**

- `team remove 3`
  - This command removes contact number 3 in the specified team.

### Navigate to a Team

To perform commands specific to a team, you will have to navigate first to that specific team. You can use the `cg`
command to navigate to a specified team. This command updates the group scope that is currently being displayed in
the application.

This command is similar to going into a folder on your desktop, or stepping
out of it.


> **Note:**
> Please look at how [scoping](#scoping) works before continuing.

**Formats**:

- `cg <INDEX>`
- `cg ..` navigates to its parent group. This is similar to stepping out of a folder once.
- `cg /` navigates to its root group. This is similar to moving your current context to the root folder.

**Examples**:

- `cg 3`
  - The above command allows you to navigate to team number 3 in the list of teams.
  - Before:
    ![Create Team Screenshot](images/user-guide-img/NavigateTeamBeforeScreenshot.PNG)
  - After:
    ![Create Team Screenshot](images/user-guide-img/NavigateTeamAfterScreenshot.PNG)
- `cg ..`

<aside>

💡 If you are familiar with UNIX-based operating systems such as Linux or familiar with using terminal, the navigation 
command (`cg`) in Contactmation follows a similar syntax to the change directory command (`cd`).

</aside>

### Add New Contacts within a Team

Once you have navigated to a team, you can add a new contact within that team, which is done through the same command
as adding a contact to Contactmation.


**Format**: `person new n/<NAME> [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

**Examples**:

- `person new n/Spongebob`
- `person new n/Squidward Tentacles p/01234567 e/squidward@gmail.com a/Bikini Bottom Krusty Krab t/Employee`

### Removing a Contact from a Team

To remove a contact from a team, first ensure that you have navigated to that specific team. Afterwards, you can remove
the person by using the `team remove` command.

**Format**: `team remove <INDEX>`

**Examples**:

- `team remove 1`
  - The above command removes person number 1 from the list of persons within the team.

### Creating and Deleting a Subteam

Contactmation allows the creation and deletion of a subteam within a team using the same command as
[creating a team](#create-a-team) and [deleting a team](#delete-a-team).


## Task commands
After adding your **contacts**, and allocating them into **teams**, you can give them **tasks**!
Task-related commands precede with the `task` keyword.

#### Things to note when using contact commands
- The `INDEX`(the number of the team in the list) must be a positive integer which cannot exceed the number of contacts / teams currently displayed
in the application.

### Adding a task to a team: `task add`

Adds a new task to an existing group scope. This group **cannot be the root group**.

![Create Team Screenshot](images/user-guide-img/TaskAddScreenshot.PNG)

**Format:** `task add t/<TITLE> d/<DESCRIPTION>`

**Example:**

- `task add t/Complete all CS2103T homework d/Give description here`

### Deleting a task from team: `task delete`

Deletes an existing task from a group by their `INDEX` within the current [scope](#scoping).

Format: `task delete <INDEX>`

Example:

- `task delete 1`
  - This command deletes the first task in the task list.

### Setting progress for tasks: `task progress`

**THIS FEATURE IS CURRENTLY IN PROGRESS**

Sets the progress level for each task that has been listed.

Format: `task progress <INDEX> <LEVEL>`

- Index indicates the index of the task in the list.
- Level indicates the progress level, and can only be set to 25%, 50%, 75%, 100%.

Example:
- `task progress 1 25%`
  - This sets the progress of the first task in the list as 25%.

## **Advanced features**

Now, there might be many things that you wish to do with managing your tasks and groups. However, it feels really,
really tedious to perform multiple functions one after the other.
Are you a power user? Are you good with logic? Well this section is for you! Supercharge your user experience by
adding and customizing your own commands and features!

Firstly, let’s understand what these commands are and how these commands work in Contactmation.

### Advanced features overview: Chaining

Most of the commands in Contactmation can take in an input and give an output. This is similar to how your functions
work in programming and mathematics.

For instance, take the command `ops`. This command can take in a value, perform some operators on it and returns
the value. Another command is the command `float`. This command allows you to create a floating point value and return
it.

Many commands in Contactmation have this functionality, and you can in turn **chain multiple commands together to
perform complicated tasks** that suits your needs.

So, how do we chain multiple commands together? We can use the `|` and `;` and the `seq` command to do so. The way
this commands work is extremely similar to how `|` and `;` works on a UNIX operating system. You can chain multiple
commands together like such:

- `seq <command 1> [| command 3]...`
- `seq <command 1> [; command 3]...`


Whenever a pipe symbol (`|`) is encountered, the output of the previous commands is then passed to the next command.

Whenever (`;`) is used, the output of the previous commands are not passed on.

All commands that produce an output supports the use of `|` to “pipe” their output to the subsequent commands.

#### Advanced feature constraints

While these advanced features can make your Contactmation experience a lot smoother, it is also subject to certain
limitations. These are the following constraints for each keyword in the format section of each advanced feature
command:

- The `MACRO WORD` is alphanumeric but hyphens and underscores are allowed. It must also begin with a letter.
- `INPUT` is a string of any length.

Here are some commands that will aid you in gaining better control over Contactmation:

### Select command

This command allows you to select a specific group, contact or task by their `INDEX`. While this command does nothing
by itself, it is useful as a precursor to chaining other commands after it.

**Format:** `<ITEM> select <INDEX> <COMMAND> [...]`

**Example:**

- `task select 3 mark`

![Select command ui](images/SelectCommandUi)

### Contains command

You can use the `contains` command which takes in an item and checks if it contains a certain attribute. If it does,
then the attribute description will be shown in the result display if there is no further piping.

**Format:** `<ITEM> contains <ATTRIBUTE>`

**Example:**

- `task select 1 contains bug`

![Contains command ui](images/ContainsCommandUi)

Here, we see that there are no `bug` attribute in the task `New Burger Recipes`.

### Execute command

This command allows for the running of a `command` on a piped string.

**Format:** `<INPUT> | e`

**Example:**

- `Who lives in a pineapple under the sea | e`

### Replace command

This command replaces a piece of text with another piece of text.

**Format:** `r <TEXT TO REPLACE> <TEXT TO BE REPLACED>`

**Example:**

- `r tetss te%ssts`

### Foreach command

Iterations can increase our workflow efficiency several fold, and through the `foreach` command, we can now cycle
through all entries of an item type in the current scope and apply a command to them. This can be especially powerful
when combined with piping to do complex executions with a single command!

**Format:** `<ITEM> foreach <COMMAND>`

**Example:**

- `task foreach unmark`

![Foreach command ui](images/ForeachCommandUi)

### If / else command

This command behaves exactly like if else statements in programming languages. If the `CRITERIA` specified is met,
then the command sequence will execute `COMMAND IF`, else it will execute `COMMAND ELSE` instead. The command
ensures that the application cannot run `COMMAND IF` and `COMMAND ELSE` in the same command sequence.

>**Note:** If else commands cannot be nested in other if else commands directly.


**Format:** `if [[CRITERIA]] ;; [[COMMAND IF]] ;; [[COMMAND ELSE]]`

**Example:**

- `task select 1 if [[contains bug]] ;; [[mark]] ;; [[task delete]]`

### Aliasing

Aliasing is very useful to have in case you do not agree with the default naming scheme in Contactmation! Here’s
how it works:

**Format:** `alias <NEW COMMAND NAME> <COMMAND>`

**Example:**

- `alias group team`

After running `alias group team`, you are now able to use the command `group` as if it was a `team`!

![Aliasing command ui](images/AliasingCommandUi)

### Custom command / Macro

Do you ever feel tired from typing the same commands over and over again? Do you find yourself highlighting your
commands and copying and pasting them? Macros are available in our application to solve this problem of yours.

All you have to do is assign the command sequence to a single word, or multiple words separated by hyphens and
underscores only. After that, when the word is typed into the command box, the command sequence it is used to
represent will run!

**Format:** `macro <MACRO WORD> <COMMAND SEQUENCE>`

**Example:**

- `macro markeverytask task foreach mark`

This will produce the following output:

![Custom command ui 1](images/CustomCommandUi1)

When `markeverytask` is typed into the command box, all tasks become marked!

![Custom command ui 2](images/CustomCommandUi2)

### Piecing multiple commands together

Here’s another example. You have just completed fixed a bunch of bugs you would like to mark off all tasks that
was bugged as complete.

![Multiple commands ui](images/MultipleCommandsUi)

Well, you know that you defined your custom field’s type as `bug` and you can see that task 1, 2 and 3 are bug
related tasks with the `Severity` labelled as a custom field in the bugs.

Well, you could of course just do `mark` commands 3 times and mark all the tasks, but what if there are a few
hundred of those pesky bug tasks that you and your team fixed?

Luckily for you, Contactmation supports the automation of commands!

Here is an example of a command sequence to search through all tasks and mark all tasks which have bug severity
ratings:

`task foreach if [[contains bug]] ;; [[mark]]`

Using just 1 command sequence, you are able to do the work that many normal commands would similarly achieve and
mark hundreds of tasks in a matter of seconds!

## **FAQ**

> How do I check whether `Java 11` is installed on my computer?

This depends on the type of computer you are using.

#### If you are using a Windows device:

1. Click the search icon in the task bar on your desktop.

![Desktop taskbar](images/Desktop-taskbar.png)

2. Search for `Command Prompt` and open the application.
3. Type in `java -version` in the `Command Prompt` application. You should see something similar to this:

![Windows desktop terminal](images/WindowsCommandPromptCheckJavaVersion.png)

4. When you press the `Enter` key on your computer, you should be greeted with something similar to this:

![Windows desktop terminal executed](images/WindowsCommandPromptCheckJavaVersionExecute.png)

5. The `Java` version in this example is listed as `java version 11.0.10`.

#### If you are using a Macbook:

1. Open the terminal by clicking on `Launchpad` and searching for `terminal` in the search bar.

![Macbook taskbar](images/MacbookDesktopTaskbar.png)

2. Follow steps 3 onwards from [here](#if-you-are-using-a-windows-device).

#### If you are using a Linux machine:

Due to the wide variety of Linux distributions out there, you will need to search online
on how you can check for `Java 11` for your respective distribution.

Here are some helpful [guides](https://phoenixnap.com/kb/check-java-version-linux) 
for popular Linux distributions such as Ubuntu.

> How can I install `Java 11`?

Follow the guide for installing `Java 11` [here](https://www.javatpoint.com/javafx-how-to-install-java).

> Why is there an error in my result display stating: `Unable to save your information!`?

This is due to an error in saving your Contactmation details in a separate file.
To combat this, use the following steps:

1. Your home folder should look something like this at first.

![Save file folder](images/FaqSaveFileError.png)

2. There are several files of note, which consists of the `data` folder, `config` file and `preferences`
file. If the `data` folder is not present, then the following steps can be ignored for the
`data` folder. If the `data` folder is present, then you should also find an `addressbook` file
within the `data` folder. The following steps are for `config`, `preferences` and the `addressbook` file.

3. Right-click on the file, and select `Properties`. You will be greeted a pop-up similar to this:

![Save file property pop up](images/FaqSaveFileError2.png)

4. Under `Attributes` in the `General` section, ensure that `Read-only` is **not ticked**.

5. Click `Apply` and `OK` to save your changes.

> Will this application also apply to a general, non-professional user?

This depends on what you will be using Contactmation for. It still can be used simply as an application
for simply saving and organizing contacts.

## **Future plans**

Our future plans for Contactmation includes:

- The ability to delegate tasks to individuals.
- Contacting any person through the application simply by clicking their email, phone number
  etc.
- Releasing a version of Contactmation on the mobile platform.
- The ability to synchronize data between multiple copies of Contactmation on your mobile and desktop.
- A pop-up window that shows the detailed form of descriptions to the user.
- A for loop command to iterate through and count groups, contacts or tasks by their attribute.

## **Glossary**

| Vocabulary       | Description                                                                                        |
|------------------| -------------------------------------------------------------------------------------------------- |
| CLI / Command Line Interface | You can only interact with the application through text, which is typed in the [command box](#contactmation-window-guide).|
| Command / Command sequence | What you would write in the command box to interact with the application.                |
| Contact          | A contact with contact information.                                                                |
| Team             | A container that contains people that work on a similar project.                                   |
| Index            | The numerical placing of a group, contact or task in the current application display.              |
| Item             | An item can refer to a group, contact or task.                                                     |
| Pipe             | The output of the previous section of commands will be used as input for the next set of commands. |
| Root group       | Refers to the application not being in any scope.                                                  |
| Scope            | A constraint on the groups, people and tasks you are able to view at one time on the display.      |
| Task             | Assigned to people or groups.                                                                       |
| Word             | Text in a command sequence that is separated from other words by a white space.                    |

## **Command summary**

### General commands summary

| Command                                                       | Format                                                                         |
| ------------------------------------------------------------- | ------------------------------------------------------------------------------ |
| Clear all items                                               | clear                                                                          |
| Exit Contacmation                                             | exit                                                                           |
| Resets filters and list all contents within the current scope | list                                                                           |
| Rename the name of items on the screen                        | rename (g, u, t)/<INDEX> <new name> or <type> select <INDEX> rename <new name> |
| Assigning user to a existing group                            | assign u/<INDEX> g/<INDEX>                                                     |

### Group commands summary

| Command                            | Format                                                     |
| ---------------------------------- | ---------------------------------------------------------- |
| Adding new team                    | `team new <NAME>`                                          |
| Delete team                        | `team delete <INDEX>` or `team select <INDEX> team delete` |
| Removing contact from current team | `team remove <Contact INDEX>`                              |
| Changing team scope                | `cg <INDEX>` or `cg ..` or `cg /`                          |
| Finding/filtering team             | `team find <keywords>`                                     |

### Contact commands summary

| Command                               | Format                                                                          |
| ------------------------------------- | ------------------------------------------------------------------------------- |
| Adding new contact to current context | `person new n/<NAME> [p/<PHONE_NUMBER>] [e/<EMAIL>] [a/<ADDRESS>] [t/<TAG>...]` |
| Delete contact                        | `person delete <INDEX>` or `person select <INDEX> person delete`                |
| Finding/filtering contacts            | `person find <keywords>`                                                        |

### Task commands summary

| Command                            | Format                                                                   |
| ---------------------------------- | ------------------------------------------------------------------------ |
| Adding new task to current context | `team new t/<title> d/<description>`                                     |
| Delete a task                      | `task delete <INDEX>` or `task select <INDEX> task delete`               |
| Marking a task as complete         | `task mark <INDEX>` or `mark <INDEX>` or task select <INDEX> mark`       |
| Marking a task as incomplete       | `task unmark <INDEX>` or `unmark <INDEX>` or task select <INDEX> unmark` |
| Finding/filtering tasks            | `task find <keywords>`                                                   |

### Advanced commands summary

| Command                  | Format                                                                  |
| ------------------------ | ----------------------------------------------------------------------- |
| Aliasing                 | `alias <NEW COMMAND NAME> <COMMAND>`                                    |
| Saving macros            | `macro <NEW COMMAND NAME> <COMMANDS TO CHAIN>`                          |
| Deleting Custom Commands | `rmMacro <COMMAND NAME>`                                                |
| Chaining/seq             | `seq <command 1> [\| command 3]... OR seq <command 1> [; command 3]...` |
| Contains                 | `<ITEM> contains <ATTRIBUTE>`                                           |
| Execute                  | `<INPUT> \| e`                                                          |
| Foreach                  | `<ITEM> foreach <COMMAND>`                                              |
| If else                  | `if [[CRITERIA]] ;; [[COMMAND IF]] ;; [[COMMAND ELSE]]`                 |
| Macro                    | `macro <MACRO WORD> <COMMAND SEQUENCE>`                                 |
| Replace                  | `r <TEXT TO REPLACE> <TEXT TO BE REPLACED>`                             |
| Select                   | `<ITEM> select <INDEX> <COMMAND> [...]`                                 |
| Create/convert int       | `int <integer>`                                                         |
| Create/convert float     | `float <float>`                                                         |
| Create/convert String    | `str <String>`                                                          |
| Print                    | `<...> \| print`                                                        |

[Back to top](#contactmation-user-guide)
