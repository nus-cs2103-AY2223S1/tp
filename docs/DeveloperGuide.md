---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete -m 1` to remove the module with index `1`.

Note that while our program has both tasks and modules, the flow of logic for doing operations (i.e. delete, add etc.) on them are similar. Therefore, although the following sections only illustrate the components in the context of modules, they are similar when applied in the context of tasks as well.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component. As stated above, tasks are omitted in place of modules in the following sections due to the similarity between them.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ModuleListPanel`, `TaskListPanel` , `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Module` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `ModtrektParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add or remove a module).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete -m 1")` API call.

![Interactions Inside the Logic Component for the `delete -m 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `RemoveCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ModtrektParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `ModtrektParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `RemoveCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-W10-4/tp/blob/master/src/main/java/modtrekt/model/Model.java)

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

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-W10-4/tp/blob/master/src/main/java/modtrekt/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `ModuleListStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `modtrekt.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedModuleList`. It extends `ModuleList` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedModuleList#commit()` — Saves the current address book state in its history.
* `VersionedModuleList#undo()` — Restores the previous address book state from its history.
* `VersionedModuleList#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitModuleList()`, `Model#undoModuleList()` and `Model#redoModuleList()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedModuleList` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitModuleList()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitModuleList()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitModuleList()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoModuleList()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial ModuleList state, then there are no previous ModuleList states to restore. The `undo` command uses `Model#canUndoModuleList()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoModuleList()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone ModuleList states to restore. The `redo` command uses `Model#canRedoModuleList()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitModuleList()`, `Model#undoModuleList()` or `Model#redoModuleList()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitModuleList()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
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

| Category         | Priority | As a ...                           | I want to ...                                                                             | So that I can ...                                                 |
|------------------|----------|------------------------------------|-------------------------------------------------------------------------------------------|-------------------------------------------------------------------|
| Onboarding       | `***`    | first time user learning the app   | see the basic commands in the welcome message                                             | start adding modules and tasks immediately                        |
| Onboarding       | `***`    | first time user exploring the app  | see the app working with sample data                                                      | see how the app looks in action                                   |
| UI/UX            | `**`     | impatient user                     | utilise commands to quickly be reminded of deadlines                                      | save time locating my deadlines                                   |
| UI/UX            | `***`    | advanced user                      | use shortcuts or shorthand commands to add tasks and deadlines                            | accomplish these tasks in a shorter amount of time                |
| UI/UX            | `***`    | experienced user                   | quickly add and filter out all my tasks and deadlines using the commands instead of a GUI | these actions are much faster                                     |
| UI/UX            | `***`    | novice user                        | use a help command to get a summary of the available commands and how to use them         | learn which commands to use                                       |
| UI/UX            | `***`    | beginner user                      | see clear and complete error messages when I provide invalid input                        | correct my mistakes                                               |
| Module Planning  | `***`    | NUS freshie                        | add/mark the modules that I plan to take                                                  | organize my study plan                                            |
| Module Planning  | `***`    | user                               | delete the modules that I added                                                           | fix my mistakes if needed                                         |
| Tasks / Deadline | `**`     | student overwhelmed by assignments | sort my deadlines                                                                         | prioritise the ones that are due the soonest                      |
| Tasks / Deadline | `**`     | student overwhelmed by tasks       | assign priority ratings to various tasks                                                  | prioritise the ones that I deem are more important                |
| Tasks / Deadline | `**`     | student overwhelmed by tasks       | sort tasks via priority ratings                                                           | figure out which ones to work on first                            |
| Tasks / Deadline | `***`    | second time user                   | add a task                                                                                | so that I can keep track of the things I need to do for a module  |
| Tasks / Deadline | `***`    | second time user                   | add a deadline                                                                            | so that I can keep track of tasks with a due date                 |
| Tasks / Deadline | `***`    | user                               | choose which modules to add my tasks and deadlines to                                     | organise my tasks and deadlines according to modules              |
| Tasks / Deadline | `***`    | user                               | view my tasks and deadlines per module                                                    | see my tasks and deadlines in an organised manner                 |
| Tasks / Deadline | `***`    | user                               | change the module of a specific task or deadline                                          | move my tasks and deadlines around if I make a mistake            |
| Tasks / Deadline | `***`    | student who has many commitments   | track all of my tasks from the various commitments (modules, cca, etc.)                   | do not miss out on completing any tasks                           |
| Tasks / Deadline | `***`    | clumsy user                        | delete tasks and deadlines                                                                | ensure my homepage is not cluttered with unused items             |
| Tasks / Deadline | `**`     | novice user                        | archive tasks and deadlines that are completed                                            | refer to them in future                                           |
| Tasks / Deadline | `***`    | novice user                        | change a deadline that I had created                                                      | adjust the due date accordingly in the event the deadline changes |

### Use cases

(For all use cases below, the **System** is the `ModtRekt` and the **Actor** is the `user`, unless specified otherwise)

**Use case: View all active tasks and deadlines**

**MSS**

1. User requests to view all active tasks and deadlines
2. ModtRekt shows all active tasks and deadlines, categorised into modules

    Use case ends.

**Extensions**

* 2a. The list is empty.

  * 2a.1 ModtRekt displays an empty list.

    Use case ends

**Use case: Add a task**

**MSS**

1. User requests to add a task and specifies the module for the task
2. ModtRekt adds the task
3. Task count of the module is updated, and the task is added to the displayed list

    Use case ends.

**Extensions**

* 2a. The module list is empty
  * 2a1. ModtRekt shows an error message when the command is entered
  
     Use case ends.

* 3a. The given module code is invalid

    * 3a1. ModtRekt shows an error message when the command is entered

      Use case resumes at step 1.

**Use case: Add a deadline**

**MSS**

1. User requests to add a deadline and specifies the module for the deadline
2. ModtRekt adds the deadline
3. Task count of the module is updated, and the deadline is added to the displayed list

    Use case ends.

**Extensions**

* 2a. The module list is empty
    * 2a1. ModtRekt shows an error message when the command is entered

      Use case ends.

* 3a. The given module code is invalid

    * 3a1. ModtRekt shows an error message when the command is entered

      Use case resumes at step 1.

**Use case: Remove a task**

**MSS**

1. User requests to remove a task
2. ModtRekt shows a list of tasks
3. User requests to remove a specific task via index
4. ModtRekt removes the task

    Use case ends.

**Extensions**

* 2a. The task list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ModtRekt shows an error message.

      Use case resumes at step 2.
    


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

### Removing a task

1. Removing a task while all tasks are being shown

    1. Prerequisites: All tasks (archived and active) are shown, and user is not currently cd-ed into a module.

    1. Test case: `remove -t 1`<br>
       Expected: First task is deleted from the list. Details of the deleted task shown in the status message.

    1. Test case: `remove -t 0`<br>
       Expected: No task is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect remove commands to try: `remove`, `remove -t x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
