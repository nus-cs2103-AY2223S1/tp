---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* [NUSMods API](https://api.nusmods.com/v2/) 
* [Open API](https://www.openapis.org/)

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

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `CliModsParser` class to parse the user command.
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
* When called upon to parse a user command, the `CliModsParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `CliModsParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the module list data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `CliMods`, which `Person` references. This allows `CliMods` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both module list data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `CliModsStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Add Module with Semester Feature

#### Proposed Implementation

The proposed add command allows the user to add for a particular `UserModule` together with `SemesterData`. 
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

// Keep this part for future reference

### Command History - `<Up>/<Down>` command

The command history feature allows user to traverse and scroll through the command history that is
recorded when he/she uses `CliMods`. The goal is to mirror the behavior of a terminal/shell
interface where user can easily access his/her previous command by using the up and down arrow keys.

#### Implementation

The command history is facilitated by `CommandSession`. The aim of `CommandSession` is to act as a
lightweight wrapper around the command execution process, handling the reading and writing to and
from the command history.

Within `CommandSession`, we make use of a `ListIterator` to keep track of which position the user is
currently at in the command history. Additionally, the use of `ListIterator` allows us to create a
generator-like method to update and retrieve the user position in the command history.

- `CommandSession::getPreviousCommand()`
    - Retrieves the previous command in the command history, and updates the internal `ListIterator`
      to the next position (upward) in the command history.

- `CommandSession::getNextCommand()`
    - Retrieves the next command in the command history, and updates the internal `ListIterator` to
      the next position (downwards) in the command history.

> Note that both of these operations are not pure, since the internal `ListIterator`
> is updated after an invocation of either operations.t p

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

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedCliMods`. It extends `CliMods` with an undo/redo history, stored internally as an `CliModsStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedCliMods#commit()` — Saves the current module list state in its history.
* `VersionedCliMods#undo()` — Restores the previous module list state from its history.
* `VersionedCliMods#redo()` — Restores a previously undone module list state from its history.

These operations are exposed in the `Model` interface as `Model#commitCliMods()`, `Model#undoCliMods()` and `Model#redoCliMods()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedCliMods` will be initialized with the initial module list state, and the `currentStatePointer` pointing to that single module list state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the module list. The `delete` command calls `Model#commitCliMods()`, causing the modified state of the module list after the `delete 5` command executes to be saved in the `CliModsStateList`, and the `currentStatePointer` is shifted to the newly inserted module list state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitCliMods()`, causing another modified module list state to be saved into the `CliModsStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitCliMods()`, so the module list state will not be saved into the `CliModsStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoCliMods()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous module list state, and restores the module list to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial CliMods state, then there are no previous CliMods states to restore. The `undo` command uses `Model#canUndoCliMods()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoCliMods()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the module list to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `CliModsStateList.size() - 1`, pointing to the latest module list state, then there are no undone CliMods states to restore. The `redo` command uses `Model#canRedoCliMods()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the module list, such as `list`, will usually not call `Model#commitCliMods()`, `Model#undoCliMods()` or `Model#redoCliMods()`. Thus, the `CliModsStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitCliMods()`. Since the `currentStatePointer` is not pointing at the end of the `CliModsStateList`, all module list states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire module list.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


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

*{More to be added}*

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

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 modules without a noticeable sluggishness in performance for typical usage.

*{More to be added}*

### Glossary
* **Student**: The person who uses the app
* **Module(s)**: The modules/class to be taken by the students

*{More to be added}*

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

### Deleting a User Module

1. Deleting a module while all User Modules are being shown

   0. Prerequisites: Open CLImods and add CS2103 using `add cs2103`. 

   1. Test case: `rm cs2103`<br>
   Expected: CS2103 is deleted and removed from `My Modules`.
   A success message of "Deleted Module: CS2103" should be displayed.

   3. Test case: `rm cs2103`<br>
      Expected: No module is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `rm`, `rm x`, `...` (where x is an invalid module code)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
