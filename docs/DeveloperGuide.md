---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* [NUSMods API](https://api.nusmods.com/v2/) for providing information on NUS modules
* [Open API](https://www.openapis.org/) for generating boiler plate API classes
* Our Developer Guide is inspired by [AB3's Developer Guide](https://se-education.org/addressbook-level3/DeveloperGuide.html)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103-F14-1/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103-F14-1/tp/blob/master/src/main/java/nus/climods/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103-F14-1/tp/blob/master/src/main/java/nus/climods/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user 
issues the command `rm CS2103`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103-F14-1/tp/blob/master/src/main/java/nus/climods/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ModuleListPanel`,  `SavedModuleListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

Modules are represented by `ModuleCard` and `SavedModuleCard` respectively. These parts encapsulate `Pill`s to capture information such as the semester that the mod is available in and the Module Credits of that mod.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the 
[`MainWindow`](https://github.com/AY2223S1-CS2103-F14-1/tp/blob/master/src/main/java/nus/climods/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103-F14-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `ModuleCard` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103-F14-1/tp/tree/master/src/main/java/nus/climods/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `CliModsParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `ListCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. list all modules offered by the CS Faculty).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("rm cs2103")` API call.

![Interactions Inside the Logic Component for the `rm CS2103` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `CliModsParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `CliModsParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103-F14-1/tp/blob/master/src/main/java/nus/climods/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the module list data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103-F14-1/tp/blob/master/src/main/java/nus/climods/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both module list data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `CliModsStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `nus.climods.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add Module with Semester Feature

#### Implementation

The add command allows the user to add for a particular `UserModule` together with `SemesterData`. 
It is facilitated by `AddCommand`.
It extends the `Command` class.

Users can add their `UserModule` with the Command:
- `add <MODULE_CODE> <SEMESTER_CODE>`
- e.g. `add CS1101S s1`

#### Parsing of commands 
`PositionalParameter` manages parsing and error handling for parameters expected to be at a certain index in the 
list of arguments. New parameter classes can extend from `PositionalParameter` to support repeated parameters across 
commands, e.g module code.

`ModuleCodeParameter` checks if the user's input fits the module code format. An exception is thrown
if the module code supplied had an incorrect format.

`SemesterParameter` will check for valid semester code input.
An exception will be thrown if semester code is invalid.

#### Design Considerations:

- Create a `SemesterParameter` class
- Modify `AddCommand` class

### Command History - `<Up>/<Down>` command

The command history feature allows user to traverse and scroll through the command history that is
recorded when he/she uses `CliMods`. The goal is to mirror the behavior of a terminal/shell
interface where user can easily access his/her previous command by using the up and down arrow keys.

#### Implementation

The command history is facilitated by `CommandSession`. The aim of `CommandSession` is to act as a
lightweight wrapper around the command execution process, handling the reading and writing to and
from the command history.

Within `CommandSession`, we make use of an index pointer (`commandHistoryPos`) to keep track of which 
position the user is currently at in the command history. The use of the index pointer allows us to 
create a generator-like method to update and retrieve the user position in the command history.

- `CommandSession::getPreviousCommand()`
    - Retrieves the previous command in the command history, and updates the internal index pointer
      to the next position (upward) in the command history.

- `CommandSession::getNextCommand()`
    - Retrieves the next command in the command history, and updates the internal index pointer to
      the next position (downwards) in the command history.

> Note that both of these operations are not pure, since the internal index pointer
> is updated after an invocation of either operations.

#### Design considerations

Depending on the shell, the behavior of how command history is being stored is different. For
example, all commands are recorded in `bash`, while in `zsh`, no consecutive duplicate commands will
be recorded.

`CLiMods` chose to emulate the `zsh` behavior instead as it reduces clutter in the command history.
We also hope that this would improve user experience as it aims to also speed up the traversal of
the command history.

### UserGuide - `help` command
The `help` command displays our User Guide in a new window, using javafx WebViewer.  It acts as a browser, so we prevent
the user from connecting to other websites that is not within our control.

It is added this way because the user guide website is automatically built from our markdown file.
This makes it easier to maintain changes in the user guide or developer guide.
We do not control other websites, and we want the user to only view the user guide and other information on our website.

We also considered just displaying the link with a `Copy URL` button.  However, the user has to copy the link into
their web browser, making the user experience not smooth.  


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
**Product scope**
* Provides updated information and data based on curriculum and module schedule based on NUSMods.

**Target user profile**:

* has a plan to map, plan and organize a timetable that has over 1000 potential modules to fill up with.
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app

### User stories

Link to Most Updated User Stories:
[Click here](https://github.com/orgs/AY2223S1-CS2103-F14-1/projects/2)

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                  | I want to …​                                                               | So that I can…​                                                           |
| -------- |----------------------------------------------------------|----------------------------------------------------------------------------|---------------------------------------------------------------------------|
| `* * *`  | student                                                  | see usage instructions                                                     | refer to instructions when I forget the commands                          |
| `* * *`  | student                                                  | add modules to my timetable                                                | edit and organize my timetable                                            |
| `* * *`  | NUS student who is not too proficient in CLI             | easily adapt and learn the functions and commands the application has      | use the application efficiently                                           |
| `* * *`  | student                                                  | delete a module                                                            | remove modules that I no longer need                                      |
| `* * *`  | student                                                  | search a module by name                                                    | locate details of the module without having to go through the entire list |
| `* * *`  | student                                                  | view pre-requisites for a class, and what class is a pre-requisite         | plan my studies appropriately                                             |
| `* * *`  | student                                                  | search for and add classes from NUSMods to my schedule                     | have the most up to date information on my schedule                       |
| `* * *`  | student interested in CLI apps                           | have most/all key features to be accessible by just the keyboard           | harness the full potential of CLI apps                                    |
| `* * *`  | forgetful student                                        | easily access my weekly/daily schedule (time, venue and details of lesson) | attend my lessons punctually                                              |
| `* * *`  | student that work in areas with poor internet connection | access the features in CLIMods                                             | still use CLIMods                                                         |
| `* * *`  | student (non freshman)                                   | track and add modules I have taken                                         | keep track of my progress in University                                   |
| `* * *`  | student                                                  | know what modules are offered in NUS                                       | find modules to do to fulfill my graduation requirement                   |
| `* *`    | potential user exploring CLIMods                         | have a tutorial or detailed documentation on features of app               | easily adapt and use the app proficiently                                 |

### Use cases

(For all use cases below, the **System** is the `CLIMods` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Find a module**

**MSS**

1.  User requests to find a module
2.  CLIMods requests for details of the module to find
3.  User enters the requested details
4.  CLIMods finds and display details of the module

    Use case ends.

**Extensions**

* 3a. The given module request is invalid.

    * 3a1. CLIMods shows an error message where module does not exist.

* 3b. The user's command is invalid.

    * 3a1. CLIMods shows an error message where command is non-existent.

      Use case resumes at step 2.

**Use case: Add a module**

**MSS**

1.  User requests to add a module
2.  CLIMods requests for details of the module to add
3.  User enters the requested details
4.  CLIMods adds the module

    Use case ends.

**Extensions**

* 3a. The given module request is invalid.

    * 3a1. CLIMods shows an error message where module does not exist.

* 3b. The user's command is invalid.

    * 3a1. CLIMods shows an error message where command is non-existent.
  
      Use case resumes at step 2.

**Use case: Delete a module**

**MSS**

1.  User requests to list modules
2.  CLIMods shows a list of modules
3.  User requests to delete a specific module in the list
4.  CLIMods deletes the module

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given name is invalid.

    * 3a1. CLIMods shows an error message.

      Use case resumes at step 2.


**Use case: Selecting Lesson Slot**

**MSS**

1.  User requests to view a specific module
2.  CLIMods shows detailed information of that module
3.  User clicks on the desired lesson type
4.  CLIMods shows all lesson slots under that lesson type for the module
5.  User picks a lesson slot using the `pick` command
6.  CLIMods adds the lesson slot under the `My Modules` list


    Use case ends.

**Extensions**

* 5a. The module has not been added

    * 5a1. CLIMods shows an error message.

* 1a. The given module code is invalid

    * 3a1. CLIMods shows an error message.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 modules without a noticeable sluggishness in performance for typical usage.
3.  Connected to internet

### Glossary
* **Student**: The person who uses the app
* **Module(s)**: The modules/class to be taken by the students

The picture below shows the terms used for different parts of the UI shown on CLIMods.
1. **Module list**: List of modules offered by NUS. Possibly filtered according to execution of various commands
2. **My modules**: List of modules the user is taking
3. **Result window**: Window showing result after execution of commands
4. **Command box**: Box where the user types commands
   <img src="images/Glossary.png" width="600" />
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown
Refer to instructions in [Quick Start](https://ay2223s1-cs2103-f14-1.github.io/tp/UserGuide.html#quick-start) for
launch.
Use the [exit](https://ay2223s1-cs2103-f14-1.github.io/tp/UserGuide.html#exiting-the-program-exit) command to
shutdown the application.


### `add`: Adding a module
0. Prerequisite: CS2103 has not been added to `My modules`.
1. Type `add CS2103 s1` in the command box to add CS2103 to your module list (under Semester 1)
2. Expected result: CS2103 is now added to `My modules`
3. Typing the same command as in Step 1 should now show an error message in the result window as CS2103 has already
   been added

### `view`: Viewing classes for a module
1. Type `view CS2103` in the command box
2. The detailed description for CS2103 is now visible in the module list

### `pick`: Picking classes for a module
0. Prerequisite: Add CS2103 to `My modules` by typing `add CS2103 s1`
1. Type `pick CS2103 tut 02` to add tutorial 2 to picked classes for CS2103
2. Expected result: Tutorial 02 is added to your class list for CS2103 under `My modules`

### `preq`: Viewing prerequisites for a module
1. Type `preq CS2103` in the command box
2. Expected result: The module list is now filtered to show some prerequisites of CS2103. As explained in the User
   Guide,
   not all prerequisites are necessarily shown in the module list due to limitations of the NUSMods API.

### `rm`: Deleting a User Module

1. Deleting a module while all User Modules are being shown

   0. Prerequisites: Open CLIMods and add CS2103 by typing `add cs2103` in the command box

   1. Test case: `rm cs2103`<br>
   Expected: CS2103 is deleted and removed from `My Modules`.
   A success message of "Deleted Module: CS2103" should be displayed in the result window

   3. Test case: `rm cs2103`<br>
      Expected: No module is deleted. Error details shown in the result window.

   4. Other incorrect delete commands to try: `rm`, `rm x`, `...` (where x is an invalid module code)<br>
      Expected: Similar to previous.

### `find`: Finding modules by keyword or regex

1. Finding modules that match the input keywords or input regex

    0. Prerequisites: Open CLIMods with stable internet connection

    1. Test case: `find operating`<br>
       Expected: CG2271, CS2106, CS2106R, CS3221, CS5250, YSC3217 are listed.
       A success message of "6 modules listed!" should be displayed.

   2. Test case: `find ^CS20\d0$`<br>
       Expected: CS2030, CS2040 are returned. <br>
       A success message of "2 modules listed!" should be displayed.

### Saving data

1. Dealing with missing/corrupted data files

   1. Edit the local json save file and erase the saved folders till it is corrupted
   2. Launch the app
   3. CLIMods will not load the save file
   4. Adding mods will overwrite the corrupted file
