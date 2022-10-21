---
layout: page
title: Developer Guide
---
1. Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_here_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

**API** : [`Ui.java`](https://github.com/AY2223S1-CS2103T-W10-4/tp/blob/master/src/main/java/modtrekt/ui/Ui.java)

Section by : [Marciano](https://github.com/midnightfeverrr)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ModuleListPanel`, `StatusBarFooter` etc.
All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between
classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework.
The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder.
For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-W10-4/tp/blob/master/src/main/java/modtrekt/ui/MainWindow.java)
is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-W10-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.
* Keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* Depends on some classes in the `Model` component, as it displays `Module` object residing in the `Model`.

#### MainWindow

The `MainWindow` class is the hub for all UI components which are listed below:
* `CommandBox` - Command box to get user input.
* `ResultDisplay` - Displays the message returned by the command entered. (Soon to contain avatar image)
* `ModuleListPanel` - Panel displaying the modules input to the system.
* `TaskListPanel` - Panel displaying the task input to the system (According to the respective module).
* `ProfileSidePanel` - Panel displaying the user's profile and fundamental details.

The `MainWindow` coordinates the backend of the system to the frontend of the system to make visible changes to the interface.

This is done through the `executeCommand(String commandText)` method. Upon user input, the logic class executes the
respective command and the model is updated to reflect the changes.
Subsequently, after the model has been updated, the following UI classes `ResultDisplay` is refreshed as a result.

#### CommandBox

The `CommandBox` class contains an editable JavaFX Component `TextArea`, which allows user to input user commands.

#### ResultDisplay

The `ResultDisplay` class shows the message returned from the user's input.

![Structure of the UI Component](images/ResultDisplayHandleCommand.png)

1. Command is executed on `MainWindow`.
2. `MainWindow` calls the method `executeCommand`, which refreshes the resultant message displayed in `ResultDisplay`.
3. `ResultDisplay` updates its JavaFX `TextArea` according to the `CommandResult` of the command given.
In this case, the `TextArea` will display the message returned as a result of the command.
4. ResultDisplay displays visible change on the interface.
5. `executeCommand` ends execution.

#### ModuleListPanel

The `ModuleListPanel` class contains `ListView<Module>` JavaFX component displaying a list view of the component inside.
In this case, the list inside is a list of `ModuleCard` class.

The contents of the list are dependent on the `modules` that the user's data. Each module input by the user will be displayed
as a `ModuleCard` object, presented in the form of `ListView`.

#### TaskListPanel

The `TaskListPanel` class contains `ListView<Task>` JavaFX component displaying a list view of the component inside.
In this case, the list inside is a list of `TaskCard` class.

The contents of the list are dependent on the `tasks` that the user's data. Each module input by the user will be displayed
as a `TaskCard` object, presented in the form of `ListView`.

#### ProfileSidePanel

The `ProfileSidePanel` class contains the user's profile and (proposed) CAP. The following information is displayed in on the object:
* `Course` name
* Inspiring quote
* (Proposed) Modular Credit completed by user
* (Proposed) Current Cumulative Average Point (CAP)

#### Other Components
In addition to the main UI components in the `MainWindow` class, these are other UI Components:
* `ModuleCard` - Individual card containing the relevant information of the module.
List of ModuleCard contained in the ModuleListPanel.
* `TaskCard` - Individual card containing the relevant information of the task.
List of TaskCards contained in the TaskListPanel.
* `HelpWindow` - Pop-up window containing the link the User Guide, as well as a (proposed) list of all the commands in the application.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `ModuleListParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ModuleListParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `ModuleListParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Module` objects (which are contained in a `UniqueModuleList` object).
* stores the currently 'selected' `Module` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Module>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `ModuleList`, which `Module` references. This allows `ModuleList` to only require one `Tag` object per unique tag, instead of each `Module` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `ModuleListStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Module Features

### Add module

In this section, the functionality of `add` module feature, expected execution path, and the interactions between
`AddCommand`, `AddCommandParser`, and other objects will be discussed.

### What is the add module feature

The `add` module features allows users to add a module that they have taken or are currently taking into the
`ModuleList`.

In order to add tasks or deadlines related to the module, a module would have to be created.

Information regarding module can be recognised in the CLI using tags

These tags are:

- `-m <module_name>`
- `-c <module_code>`
- `-cr <module_credit>`

### Design considerations

**Aspect 1: How many modules are added:**

* **Alternative 1 (current choice):** Add 1 module added per AddCommand.
  * Pros: Easy to implement.
  * Cons: May have to type more to add multiple modules.

* **Alternative 2:** Add multiple modules per AddCommand.
  * Pros: Convenient for user.
  * Cons: More complicated, may require much more parsing.

We decided to go with the alternative 1 to keep the logic simple and easier to work with. To tackle the cons we tried to
reduce the compulsory AddCommand parameters.

**Aspect 2: What parameters do we need:**

* **Alternative 1:** Manually add all the required parameters.
    * Pros: Able to add modules that are not on NUSMods.
    * Cons: Troublesome to add multiple parameters.

* **Alternative 2:** Only require module code and fetch the relevant information from NUSMods API.
    * Pros: Convenient for user as the only module code is required for the parameter.
    * Cons: If the NUSMods API server is down, users would not be able to add their modules.

We decided to go implement both alternatives as we wanted to give users greater flexibility.

### Current implementation

The diagram below showcases the path execution for when adding a module

<img src="images/ModulePUMLs/AddModule/ModuleAddPathExecution.png" width="800" />

The diagram below shows how the add command work with input `add -m -c CS2103T`

<img src="images/ModulePUMLs/AddModule/ModuleAddSequenceDiagram.png" width="1200" />

The arguments are first parsed through `ModtrektParser` to identify the command word. The command word will help
identify the type of `Parser` needed to parse the rest of the arguments. In this case it is `AddCommandParser`. After
obtaining the module code, the argument would be passed to static method `ModuleParser:fetchModule()`. This would fetch
the module details from NUSMods and return a `Module`. The module would be used to instantiate an `AddCommand`. When the
`AddCommand` is executed, the `Model` would add the module to the `ModuleList`.

### Remove Module

In this section, the functionality of `remove` module feature, expected execution path, and the interactions between the
`RemoveCommand`, `RemoveCommandParser`, and other objects will be discussed.

### What is the remove module feature

The `remove` module features allows users to remove a module that they have taken or mistakenly inputted into
`ModuleList`.

Removal of a `Module` would remove all `Task` and `Deadline` associated with it.

### Design considerations

**Aspect: How are modules removed:**

* **Alternative 1 (current choice):** Using their index in the list.
    * Pros: Easier for user to select.
    * Cons: May be hard to find module especially if module list is long. More complicated as index may change
    especially after modifying the module list

* **Alternative 2:** Using the module code.
    * Pros: User need not list the modules to find exact module to remove.
    * Cons: User needs to type more.

We decided to go with the alternative 1 as it would be faster for users to type inputs. Furthermore, at any point, it is
unlikely that users will take so many modules such that the GUI is unable to display all the modules. We also have an
archive feature that would remove previously taken modules so that it would not clutter up the GUI.

### Current implementation

The diagram below showcases the path execution for when removing a module

<img src="images/ModulePUMLs/RemoveModule/ModuleRemovePathExecution.png" width="800" />

The diagram below shows how the remove command work with input `remove -m 1`

<img src="images/ModulePUMLs/RemoveModule/ModuleRemoveSequenceDiagram.png" width="1200" />

The arguments are first parsed through `ModtrektParser` to identify the command word. The command word will help
identify the type of `Parser` needed to parse the rest of the arguments. In this case it is `RemoveCommandParser`. After
obtaining the index, it would be used to instantiate a `RemoveCommand`. When the `RemoveCommand` is executed, it would
first obtain the `Module` using the index. Then it would remove the `Module` from the `ModuleList`. Using the saved
`Module` it would then remove all `Task` in the `TaskBook` with the `Module`.

### Navigation

#### Change Current Module

The `cd` command allows users to set a module as the **current module**, which accomplishes two things:
* Firstly, it filters the current task list in the UI to only show tasks from the corresponding module
* Additionally, all further commands are parsed in the context of the corresponding module. For instance, commands that require a task index, such as `remove -t 1`, will now base the index on the *updated filtered* task list.

The relevant commands are:
* **`cd <module code>`** sets the current module to the module with the specified module code
* **`cd ..`** sets the current module to `null`, if it is not already `null`

#### Design Considerations

The syntax and command word were aspects that we took into consideration in the design process.

1. **Using the shorthand word `cd` and similar syntax e.g. `cd ..` (Current Implementation)**
  * Pro: Users who are familiar with CLI applications will be able to use similar syntax for navigation inside the application
  * Pro: Users will be able to navigate through the application faster, due to the shorter command word and syntax
  * Con: Users who are not familiar with CLI applications would have to remember a specific command word that may not be very intuitive

2. **Using a longer command word e.g. `enter` and `exit`**
  * Pro: The command word is more universally intuitive, especially for users who are not familiar with CLI applications
  * Con: Users who are already familiar with CLI applications will have to relearn navigation using a different command
  * Con: Users will have to type out a longer command word and syntax, which will reduce the speed at which a user can navigate through the application

Taking into account that our [target user profile](#product-scope) is one that is familiar with using CLI apps, we chose option 1 as it provided the most benefit for such a user. In particular, it contributes to the goal of allowing the user to accomplish tasks (in this case navigation) in a shorter time using the CLI.

#### Current implementation

The current module is a `ModCode` stored as a variable in [ModelManager](https://github.com/AY2223S1-CS2103T-W10-4/tp/blob/master/src/main/java/modtrekt/model/ModelManager.java). Its value is used as part of a custom predicate that is used to call `Model::updateFilteredTaskList`, in order to filter the tasks to only display those belonging to the corresponding module.

The following activity diagram shows the execution and control flow for the `cd` command.

<img src="images/CdActivityDiagram.png" width="1000" />

As seen from the diagram, when the user enters the command `cd ..` to exit from the currently selected module, a check is performed to determine whether there *is* a current module.

In the case where there is no current module, i.e. the user is already at the 'root' (and all modules/tasks are listed), an error message is shown to the user alerting them of this fact. This improves the user experience as it prevents the case of a user repeatedly entering the `cd ..` command and wondering why the display never changes.

The sequence diagram below shows the flow of the interactions between the different components upon execution of the command `cd CS2106`.

In the diagram, the predicates `modulePredicate` and `taskPredicate` are the custom predicates used to filter the module and task lists, respectively. They are within the `setCurrentModule` method in `Model`.

<img src="images/CdSequenceDiagram.png" width="1000" />

### Tasks

#### Task archival

Task archival allows users to selectively hide tasks that they have completed.

Every task in the task book will be in either the archived or unarchived state.
New tasks will be created in the unarchived state.

In this section, we will discuss the management of archived/unarchived state, as well as the
interactions between the commands, their parsers, and the UI.

The relevant commands for this section are:
* **`archive -t <task index>`**  archives the task visible in the UI with the specified index.
* **`unarchive -t <task index>`** unarchives the task visible in the UI with the specified index.

##### Design considerations

There was an alternative we considered for users to select the task to archive:

* **Alternative 1:** Using the task name:
    * Pro: Users do not have to search for a task and its index.
    * Pro: Users can archive tasks that aren't visible in the UI.
    * Con: Users have to type a significant amount to disambiguate tasks by their name.
    * Con: Users have to remember the task names which may be difficult if there are many tasks.

* **Alternative 2:** Using the task index of the current module (current implementation):
    * Pro: Users can archive tasks by their index easily without much typing.
    * Con: Users now have to use `cd` to change the current module tied to the task they want to archive.
    * Con: Users now have to use `ls` and `ls -A` to view the tasks to archive or unarchive respectively.

Seeing as we prioritize a CLI, we chose the second option as it would be simpler for users,
even though the `cd` and `ls` commands add a bit of overhead.

##### Current implementation

Archival state is handled in the `Task` class via a boolean flag `isArchived`.
Because `Task` is immutable, the methods `Task::archive` and `Task::unarchive` return a new `Task`
with the archival state changed instead of mutating the `isArchived` variable directly.

The following activity diagram shows the execution and control flow of the `archive` command.

<img src="images/tasks/ArchivalActivityDiagram.png" width="1000" />

Notice how we explicitly prevent an archived task from being archived again. Even though archiving an archived task
is inconsequential from a data perspective (nothing in a `Task` changes other than the creation of a new instance),
it is still a user error that should be handled:

> Suppose that a user intended to _unarchive_ a task, but accidentally entered the `archive` command instead.
By displaying an error instead of silently accepting the erroneous command, the user is notified and
can enter the correct command next—this results in better UX!

The classes directly involved in setting the archival state from user input are:
* `ArchiveTaskCommand` and `UnarchiveTaskCommand` which are the commands that when executed, archive and unarchive tasks respectively.
* `ArchiveTaskCommandParser` and `UnarchiveTaskCommandParser` which parse user input for their respective commands.
* `ModtrektParser` which parses the command word and delegates the parsing to the correct parser.
* `LogicManager` which executes the commands.

For brevity, we omit the diagrams and explanations for task unarchival—it is the direct inverse of archival,
such that the control flow is exactly the same: just replace "archive" and its derivatives
with "unarchive", and vice versa.

#### Task listing

Task listing allows users to view the tasks they have created which belong to a module.

The relevant commands for this section are:
* **`cd`** sets the current module to view tasks for.
* **`ls`** displays only the unarchived tasks for the current module in the UI.
* **`ls -a`** displays all the tasks for the current module, including the ones archived, in the UI.

##### Current implementation

We check for the presence of the `-a` flag to decide whether to display archived tasks.

The predicates defined by `Model.SHOW_ALL_TASKS` and `Model.HIDE_ARCHIVED_TASKS` are used to filter
the tasks displayed in the UI via the `updateFilteredTaskList` method in the `Model` interface.

The sequence diagram below details the interactions between the command, parser, and the model
for the`ls` and `ls -a` commands:

<img src="images/tasks/ListingSequenceDiagram.png" width="1000" />

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* is an undergraduate studying at NUS
* has a need to track their modules
* has a need to track tasks and deadlines from their modules
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* familiar and reasonably comfortable using CLI apps

**Value proposition**: manage modules and tasks/deadlines for each module faster than a typical GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Category         | Priority | As a ...                         | I want to ...                                 | So that I can ...                                                                                    |
|------------------|----------|----------------------------------|-----------------------------------------------|------------------------------------------------------------------------------------------------------|
| Onboarding       | `***`    | first time user learning the app | see the basic commands in the welcome message | start adding modules and tasks immediately                                                           |
| Module Planning  | `***`    | user                             | view the modules I need to take               | figure out my study plan ahead of time                                                               |
| Module Planning  | `***`    | user                             | add/mark the modules that I plan to take      | organize my study plan                                                                               |
| Module Planning  | `***`    | user                             | delete the modules that I added               | fix my mistakes if needed                                                                            |
| Tasks / Deadline | `***`    | user                             | add a task                                    | keep track of the things I need to do (without any deadlines) for a module I am taking this semester |
| Tasks / Deadline | `***`    | user                             | add a deadline                                | keep track of the assignments I have to submit soon                                                  |
| Tasks / Deadline | `***`    | user                             | view my tasks and deadlines per module        | see my tasks and deadlines in an organized manner                                                    |
| Tasks / Deadline | `***`    | clumsy user                      | delete tasks and deadlines                    | ensure my homepage is not cluttered with unused items                                                |                                              |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `ModtRekt` and the **Actor** is the `user`, unless specified otherwise)

**Use case: View all active tasks and deadlines**

**MSS**

1. User requests to view all active tasks and deadlines
2. ModtRekt shows all active tasks and deadlines, categorised into modules

    Use case ends.

**Extensions**

* 2a. The list is empty.

  * 2a.1 ModtRekt displays "No active tasks or deadlines".

    Use case ends

**Use case: Add a task**

**MSS**

1.  User requests to add a task
2.  ModtRekt shows a list of modules
3.  User requests to add a task to specific module in the list
4.  ModtRekt adds the task

    Use case ends.

**Extensions**

* 2a. The module list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ModtRekt shows an error message.

      Use case resumes at step 2.

**Use case: Add a deadline**

**MSS**

1.  User requests to add a deadline
2.  ModtRekt shows a list of modules
3.  User requests to add a deadline to specific module in the list
4.  ModtRekt adds the deadline

    Use case ends.

**Extensions**

* 2a. The module list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ModtRekt shows an error message.

      Use case resumes at step 2.

**Use case: Remove a task**

**MSS**

1. User requests to remove a task
2. ModtRekt shows a list of modules
3. User requests to remove a task from specific module in the list
4. ModtRekt shows a list of tasks in that module
5. User requests to remove a specific task of the module in the list
6. ModtRekt removes the task

    Use case ends.

**Extensions**

* 2a. The module list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ModtRekt shows an error message.

      Use case resumes at step 2.

* 4a. The task list is empty.

  Use case ends.

* 5a. The given index is invalid.

    * 5a1. ModtRekt shows an error message.

      Use case resumes at step 4.

**Use case: View modules remaining for graduation**

**MSS**

1.  User requests to view what modules he has left for graduation
2.  ModtRekt shows a list of modules, categorised into each requirement

    Use case ends.

**Extensions**

* 2a. All the requirements are met.

    * 2a1. ModtRekt congratulates user.

      Use case ends.


*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 100 modules without a noticeable sluggishness in performance (i.e. should take less than 1 second to load)
3. A user with above 80 wpm typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. The interface should be user-friendly enough such that a user who has never seen the user guide should be able to use it.

*{More to be added}*

### Glossary

_**Mainstream OS**_

Windows, Linux, Unix, OS-X

_**Private contact detail**_

A contact detail that is not meant to be shared with others


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
