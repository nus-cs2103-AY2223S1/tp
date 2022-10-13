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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

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
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

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

* Tech-savvy university student leading teams in software engineering modules to build software projects
* Having trouble keeping track of the team’s progress and delegating tasks effectively
* Student who prefers CLI to GUI for productivity’s sake
* Desperate for a single source of truth on who is doing what and by when

**Value proposition**:

* Users can collate different project-related information (e.g. Github project PRs, issues, links to Zoom meetings, and Google Docs)
* Users can visualise teams’ progress easily
* Users can delegate tasks to their teammates conveniently
* CLI interface to manage project tasks much more quickly than GUI based products

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a ...              | I want to ...                                                                            | So that I can...                                                                                     |
|----------|-----------------------|------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|
| `* * *`  | New User              | See usage instructions                                                                   | Refer to instructions when I forget how to use the App                                               |
| `* * *`  | Team Leader           | View completed tasks                                                                     | Track the status of the project                                                                      |
| `* * *`  | Team Leader           | Add new tasks                                                                            | Track things my team needs to do                                                                     |
| `* * *`  | Team Leader           | Add team members with their contact information                                          | Keep track of my team members and contact them with their preferred mode of communication            |
| `* * *`  | Team Leader           | View tasks based on contact’s name/email                                                 | Keep track of each person’s tasks                                                                    |
| `* * *`  | Team Leader           | Assign deadlines to tasks                                                                | Track whether we are meeting deadlines for all tasks or not                                          |
| `* * *`  | Team Leader           | Create multiple stages of completion for a task (e.g. in progress, in code review, done) | See the progress of each task at a glance                                                            |
| `* * *`  | Team Leader           | Delete tasks                                                                             | Remove tasks that are no longer required to be completed or have been added on mistake               |
| `* * *`  | Team Leader           | Delete members                                                                           | Remove information of members who are no longer working on my project                                |
| `* *`    | Team Leader           | Modify existing tasks                                                                    | Update project requirements and track things to do                                                   |
| `* *`    | Team Leader           | Assign tasks to team members                                                             | Distribute workload evenly and keep everyone accountable                                             |
| `* *`    | Team Leader           | Edit the contact information of my team members                                          | Correct it if I accidentally added the wrong number/email                                            |
| `* *`    | Team Leader           | Give priority to tasks                                                                   | Better plan which tasks are to be assigned to whom and when                                          |
| `* *`    | Engineering Team Lead | Store links that lead me to an issue on the repo                                         | Easily view the diff, progress, etc.                                                                 |
| `* *`    | Team Leader           | View links to future Zoom meetings                                                       | Avoid opening Zoom separately and can directly join the meeting from the application                 |
| `* *`    | Team Leader           | Add subtasks                                                                             | Break down tasks into manageable parts                                                               |
| `* *`    | Team Leader           | View a summary of how many tasks each member has been assigned                           | Assign tasks to the members more equally, based on how occupied they might be                        |
| `* *`    | Team Leader           | Add recurring tasks such as weekly meetings                                              | Assign a recurring tasks once instead of having to schedule it every occurrence                      |
| `*`      | Team Leader           | View past meeting minutes                                                                | Refer to what has been discussed before                                                              |
| `*`      | Team Leader           | View past meetings                                                                       | Remember which date we completed each meeting                                                        |
| `*`      | Team Leader           | View upcoming meetings                                                                   | Plan for upcoming meetings                                                                           |
| `*`      | Team Leader           | Modify upcoming meetings                                                                 | Reschedule future meetings when the need arises                                                      |
| `*`      | Team Leader           | Copy team member’s email                                                                 | Easily send an email to remind him/her to do their task                                              |
| `*`      | Team Leader           | Have 2 kinds of deadlines - soft and hard                                                | Let my team members finish the task by the soft deadline and I can review/merge by the hard deadline |
| `*`      | Team Leader           | Receive reminders when a deadline is due                                                 | Ensure tasks are completed on time                                                                   |
| `*`      | Team Leader           | Randomly assign a task to any team member                                                | Assign tasks easily if nobody has any preference                                                     |






### Use cases

(For all use cases below, the **System** is the `TruthTable` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a member to a team**

Preconditions: The current working team is set to the team that the member should be added to.

**MSS**

1. User requests to add member and provides member name
2. TruthTable adds the member

    Use case ends.

**Extensions**

* 1a. There is no name provided.

    * 1a1. TruthTable shows an error message.

      Use case resumes at step 1.

**Use case: UC02 - Delete a member from a team**

Preconditions: The current working team is set to the team that the member should be deleted from.

**MSS**

1.  User requests to list members
2.  TruthTable shows a list of members
3.  User requests to delete a specific member in the list
4.  TruthTable deletes the member

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. TruthTable shows an error message.

      Use case resumes at step 2.

**Use case: UC03 - List all members of a team**

Preconditions: The current working team is set to the team that the member should be deleted from.

**MSS**

1.  User requests to list members
2.  TruthTable shows a list of members belonging to the team

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case: UC04 - Add a task to a team**

Preconditions: The current working team is set to the team that the task should be added to.

**MSS**

1.  User requests to add task member and provides task name and task deadline
2.  TruthTable adds the task to the list of tasks

    Use case ends.

**Extensions**

* 1a. There is no task name provided.

    * 1a1. TruthTable shows an error message.

      Use case resumes at step 1.

* 1b. There is no task deadline provided.

    * 1b1. TruthTable shows an error message.

      Use case resumes at step 1.

* 1c. The task deadline is badly formatted.

    * 1c1. TruthTable shows an error message.

      Use case resumes at step 1.

**Use case: UC05 - Delete a task from a team**

Preconditions: The current working team is set to the team that the task should be deleted from.

**MSS**

1.  User requests to list tasks
2.  TruthTable shows a list of tasks
3.  User requests to delete a specific task in the list
4.  TruthTable deletes the task

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. TruthTable shows an error message.

      Use case resumes at step 2.

**Use case: UC06 - List all tasks of a team**

Preconditions: The current working team is set to the team that the member should be deleted from.

**MSS**

1.  User requests to list tasks
2.  TruthTable shows a list of tasks belonging to the team

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case: UC07 - Add deadline to existing task**

Preconditions: The current working team is set to the team that has the existing task.

**MSS**

1.  User requests to list tasks
2.  TruthTable shows a list of tasks
3.  User requests to add deadline to specific task in the list
4.  TruthTable adds deadline to task

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. TruthTable shows an error message.

      Use case resumes at step 2.

* 3b. The given deadline is invalid.

    * 3b1. TruthTable shows an error message.

      Use case resumes at step 2.

**Use case: UC08 - Create new team**

**MSS**

1.  User requests create new team and provides new team name
2.  TruthTable creates a new team and sets current working team to new team

    Use case ends.

**Extensions**

* 1a. The given team name is used for an existing team already.

    * 1a1. TruthTable shows an error message.

      Use case resumes at step 1.

* 1b. There is no team name given.

    * 1b1. TruthTable shows an error message.

      Use case resumes at step 1.

**Use case: UC09 - Change current working team**

**MSS**

1.  User requests to change current working team
2.  TruthTable sets current working team to specified team

    Use case ends.

**Extensions**

* 1a. There is no team name given.

    * 1a1. TruthTable shows an error message.

      Use case resumes at step 1.

**Use case: UC10 - List all teams**

**MSS**

1. User requests to list all teams managed by user
2. TruthTable shows a list of teams

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Does not require internet connection.
5. Any changes to the data should be saved permanently and automatically.

### Glossary
* **Issue**: Generally refers to an issue created on GitHub that is used to track the progress of a software development project.
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Member**: A person in the team, working on a project.
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Repo**: A short-form for "repository" (usually on a platform such as GitHub or GitLab)
* **Task**: Anything that needs to be completed for the project to move forward.
* **Team Leader**: The person in-charge of a project, typically a software engineering project.

*{More to be added along the way}*

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
