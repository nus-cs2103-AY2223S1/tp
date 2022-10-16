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

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103T-T13-4/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-T13-4/tp/tree/master/src/main/java/taskbook/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-T13-4/tp/tree/master/src/main/java/taskbook/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-T13-4/tp/tree/master/src/main/java/taskbook/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-T13-4/tp/tree/master/src/main/java/taskbook/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-T13-4/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-T13-4/tp/tree/master/src/main/java/taskbook/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `TaskBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ContactDeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `TaskBookParser` class detects the category that the command belongs to and calls upon the appropriate Category Parser (`ContactCategoryParser`, `TaskCategoryParser`, `CategorylessParser`).
* The Category Parser creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `ContactAddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `ContactAddCommand`) which results in the `TaskBookParser` returning a `Command` object.
* All `XYZCommandParser` classes (e.g., `ContactAddCommandParser`, `ContactDeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-T13-4/tp/tree/master/src/main/java/taskbook/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the task book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `TaskBook`, which `Person` references. This allows `TaskBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-T13-4/tp/tree/master/src/main/java/taskbook/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both task book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `TaskBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `taskbookbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedTaskBook`. It extends `TaskBook` with an undo/redo history, stored internally as an `taskBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedTaskBook#commit()` — Saves the current task book state in its history.
* `VersionedTaskBook#undo()` — Restores the previous task book state from its history.
* `VersionedTaskBook#redo()` — Restores a previously undone task book state from its history.

These operations are exposed in the `Model` interface as `Model#commitTaskBook()`, `Model#undoTaskBook()` and `Model#redoTaskBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedTaskBook` will be initialized with the initial task book state, and the `currentStatePointer` pointing to that single task book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the task book. The `delete` command calls `Model#commitTaskBook()`, causing the modified state of the task book after the `delete 5` command executes to be saved in the `taskBookStateList`, and the `currentStatePointer` is shifted to the newly inserted task book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitTaskBook()`, causing another modified task book state to be saved into the `taskBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitTaskBook()`, so the task book state will not be saved into the `taskBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoTaskBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous task book state, and restores the task book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial TaskBook state, then there are no previous TaskBook states to restore. The `undo` command uses `Model#canUndoTaskBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoTaskBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the task book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `taskBookStateList.size() - 1`, pointing to the latest task book state, then there are no undone TaskBook states to restore. The `redo` command uses `Model#canRedoTaskBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the task book, such as `list`, will usually not call `Model#commitTaskBook()`, `Model#undoTaskBook()` or `Model#redoTaskBook()`. Thus, the `taskBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitTaskBook()`. Since the `currentStatePointer` is not pointing at the end of the `taskBookStateList`, all task book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire task book.
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

* has a need to manage a significant number of contacts and task assignments to and from these contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts and task assignments to and from contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                  | I want to …​                                                   | So that I can…​                                                           |
|----------|--------------------------|----------------------------------------------------------------|---------------------------------------------------------------------------|
| `* *`    | new user                 | see usage instructions                                         | refer to instructions when I forget how to use the App                    |
| `*`      | new user                 | view a tutorial for how to use the app                         | understand how to use the app and get started quickly                     |
| `* * *`  | user                     | add a new contact using a single command                       | add contacts without using a GUI                                          |
| `* * *`  | user                     | delete a contact                                               | remove entries that I no longer need without using a GUI                  |
| `* * *`  | user                     | view all contacts                                              | view all details of added contacts in 1 place                             |
| `* * *`  | user                     | add my contact's phone number                                  | find their contact number without having to remember it                   |
| `* * *`  | user                     | add my contact's email                                         | find their contact email without having to remember it                    |
| `* * *`  | user                     | add my contact's address                                       | find their address without having to remember it                          |
| `* *`    | user                     | add tags to my contacts                                        | categorise my contacts                                                    |
| `* *`    | user with many contacts  | sort my contacts by different parameters                       | view my contacts in my desired organisation order                         |
| `* *`    | user                     | find a person by name                                          | locate details of persons without having to go through the entire list    |
| `* *`    | user                     | hide private contact details                                   | minimize chance of someone else seeing them by accident                   |
| `* *`    | user                     | edit my contact's information                                  | keep their information up to date                                         |
| `* *`    | forgetful user           | know if I accidentally add duplicate contacts                  | avoid adding the same contact again                                       |
| `* *`    | user with many contacts  | search within contact groups                                   | find related contacts easily                                              |
| `* *`    | user                     | search contacts using keywords                                 | easily find a specific contact                                            |
| `*`      | user                     | add a profile picture for each of my contacts                  | easily distinguish all my contacts, especially those with similar names   |
| `* * *`  | user                     | add a task                                                     | remember what to do (assign myself)                                       |
| `* * *`  | user                     | view all tasks                                                 | see all my task assignments in 1 place                                    |
| `* * *`  | user                     | delete a task                                                  | remove tasks I no longer want to record                                   |
| `* *`    | user                     | add a deadline to a task                                       | record when a task needs to be completed by                               |
| `* *`    | user                     | assign a task to a contact                                     | remember what I told someone else to do                                   |
| `* *`    | user                     | use a contact to assign myself a task                          | remember what I was told by someone else to do                            |
| `* *`    | user                     | tag my tasks                                                   | remember what the task is associated with                                 |
| `* *`    | user                     | see the task with the earliest pending deadline                | know what task is the most important to prioritise                        |
| `* *`    | forgetful user           | know if I accidentally add duplicate tasks                     | avoid adding the same task again                                          |
| `* *`    | expert user              | create shortcuts for tasks                                     | save time on creating frequent tasks                                      |
| `* *`    | adept user               | view all tasks assigned to other contacts with 1 command       | easily see what I told others to do                                       |
| `* *`    | adept user               | view all tasks assigned to me by other contacts with 1 command | easily see what others told me to do                                      |
| `* *`    | adept user               | create custom lists of tasks and/or contacts                   | fine-tune task priorities for myself                                      |
| `* *`    | expert user              | tag tasks                                                      | easily find tasks associated with the tag                                 |
| `* *`    | expert user              | delete multiple completed tasks with 1 command                 | easily keep my tasks organised and reduce clutter                         |
| `*`      | user                     | sort the order of tasks by date, size, etc                     | view tasks in whatever prioriity is most important to me at the time      |
| `* * *`  | user                     | close the app with a command                                   | close the app safely                                                      |
| `* * *`  | forgetful user           | automatically save my progress after every command             | stop worrying about potentially closing the app without saving            |
| `* *`    | careless user            | undo commands I have keyed in wrongly                          | effortlessly correct any blunders in commands that I notice               |
| `* *`    | expert user              | redo commands to repeat commands                               | quickly perform bulk actions                                              |
| `* *`    | forgetful user           | navigate my command history to look at previous commands       | remember what actions I had taken recently                                |
| `* *`    | lazy user                | handle all my tasks and contacts in 1 place                    |                                                                           |
| `* *`    | user                     | use my preferred date format in commands                       | use the date format I am used to and not memorise a specific other format |
| `*`      | user unfamiliar with CLI | use GUI components to enter commands instead                   | use the app without needing to be fast at typing                          |


### Use cases

(For all use cases below, the **System** is the `TaskBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a contact**

**MSS**

1. User requests to add a person in the list
2. TaskBook adds the person

    Use case ends.

**Use case: Delete a contact**

**MSS**

1. User requests to list persons
2. TaskBook shows a list of persons
3. User requests to delete a specific person in the list
4. TaskBook deletes the person

    Use case ends.
   
**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. TaskBook shows an error message.

      Use case resumes at step 2.

**Use case: Add a task**

**MSS**

1. User requests to list persons
2. TaskBook shows a list of persons
3. User requests to add a task to a person in the list
4. TaskBook adds the task

   Use case ends.

**Use case: Delete a task**

**MSS**

1.  User requests to list tasks
2.  TaskBook shows a list of tasks
3.  User requests to delete a specific task in the list
4.  TaskBook deletes the task

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. TaskBook shows an error message.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_, on both 32-bit and 64-bit systems as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons and 5000 tasks without a _noticeable sluggishness_ in performance for typical usage.
3. Should be responsive to any command within 2 seconds.
4. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
5. The product’s stored data should be backwards compatible with all previous versions.
6. The product’s data storage file should be human-editable.
7. The product should not cause data corruption on unexpected crashes.
8. The product should be reliable and work as expected most of the time.
9. The user interface should be intuitive enough to be usable by a novice with little experience but works more efficiently for an experienced user.
10. The product is offered as a free downloadable offline application without requiring the use of an installer.
11. The product should work offline.
12. The product’s GUI should work well for most standard screen resolutions or higher.
13. The product should be packaged into a single JAR file with a maximum size of 100MB.
14. The system’s data should be portable to allow the user to export their data.
15. The product should be easy to test, with appropriate manual testing instructions and automated tests.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

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
