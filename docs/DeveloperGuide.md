---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

---

## **Acknowledgements**

- This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

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
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

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

#### Target user profile:

**Steve** is a web developer who:
* has a need to manage a significant projects and stakeholders related to said projects
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* needs to store projects and stakeholders securely

**Value proposition**: manage projects and project contacts faster than a typical mouse/GUI driven app, in a secure manner.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
|  `* * *` | student developer  |  track multiple projects spread across different websites in one place. | |
| `* * *`         | forgetful developer  |  see all the tasks for my projects on one page  |  I will remember what needs to be an experienced developer,  |
|  `* * *`        | developer  |  see the projects automatically sorted in accordance with the deadline  |  I can manage and clear those with a higher priority first |
|   `* * *`       | developer  |  have a quick overview of those collaborating on projects with me  |  I can contact collaborators or access information quickly |
| `* * *`         | user  |  add projects to the application | |
| `* * *`         | user  |  delete projects from the application  |  I can keep my data accurate if I make a mistake in entering data. |
| `* * *`         | user  |  edit projects from the application  |  I can handle changes in my projects. |
| `* * *`         | user  |  tag clients to each project  |  I can know which clients each project is under. |
|  `* * *`        | new user  |  view a guide  |  I can learn about the functionalities of the application. |
| `* * *`         | user  |  add deadlines to the projects  |  I can prioritize accordingly. |
| `* * *`         | user  |  differentiate between the types of projects  |  I can organize my workspace. |
| `* * *`         | user  |  add the contact numbers and email addresses of each client to the projects  |  I can contact them more efficiently. |
|  `* * *`        | user  |  link my projects to their repositories  |  I can easily navigate to them. |
|    `* *`       | forgetful developer | I can categorize projects into various categories |  better organize them |
|   `* *`        | developer  |  choose to ‘pin’ certain projects  |  I can quickly access them  |
|  `* *`         | developer  |  rate the importance of each client  |  I can prioritize certain clients. |
|   `* *`        | developer  |  see all the issues/room for improvements of the website that my clients have in one place,  |  I know what features/bugs to work on for them |
|  `* *`        | company developer  |  ensure my project and data are only accessible after user authentication | my data is kept secure |
|   `* *`        | developer  |  also track other aspects (such as project cost) that are not in the default implementation. | |
|   `* *`        | new user  |  view dummy data  |  I can learn how to use the application. |
|   `* *`        | new user  |  tag ongoing bugs to a project  |  I can allocate my time to bug fixes in an efficient manner. |
| `* *`         | developer  |  sort the projects  |  I can see which projects require more urgency when the number of projects becomes too long. |
|    `* *`       | user  |  create an account,  |  I do not give access to my project data if I lose my laptop. |
|     `* *`      | user  |  login to my account,  |  I can access my data. |
|    `* *`       | forgetful user  |  reset my password,  |  I can access my account if I forget my password. |
|  `* *`         | user  |  delete tags from the projects. | |
|    `* *`       | user  |  differentiate between teammate contacts and client contacts and advanced user, I can categorize issues for each project  |  I can organize the types of bugs that need to be fixed. |
|    `* *`       | user  |  change my password to prevent theft of my data. |
|      `* *`     | user  |  view the current time and date  |  I can keep track of the due date of my projects. |
|    `* *`       | developer  |  clear all data using a single command |  |
|     `* *`      | user  |  split the project tiles into different categories  |  I can organize my workspace better. |
|    `*`       | user  |  get a notification every time an issue surfaces,  |  I can respond in a timely manner. |
|    `*`       | user  |  automatically check my projects for issues  |  I can efficiently check for outstanding bug fixes. |
|    `*`       | user  |  see the bug history of each project  |  I can identify the more problematic projects. |
|    `*`       | user  |  tag team members to the projects,  |  I can communicate with other developers on my team. |
|   `*`        | user  |  customize the look of each project tile,  |  I can make my workspace more aesthetic. |
|     `*`      | head developer  |  have multiple developers use the same application on the same system. |  |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is `DevEnable` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - List all projects**

**MSS**

1.  User requests to list projects
2.  DevEnable shows a list of projects.

    Use case ends.

**Extensions**

* 1a. The list is empty.
  * 1a1. DevEnable displays a default message.
  
    Use case ends.

**Use case: UC2 - Add project**

**MSS**

1.  User requests to add a project.
2.  DevEnable adds the project to the list.

    Use case ends.

**Extensions**

* 1a. The user makes an error in writing the request.
    * 1a1. DevEnable displays an error message with the correct usage.
  
      Use case resumes at Step 1.
* 1b. DevEnable detects that the project already exists in the list.
    * 1b1. DevEnable displays an error message that the project already exists.
  
      Use case ends.

**Use case: UC3 - Delete project**

**MSS**

1. User <ins>views the list of projects (UC1).</ins>
2. User requests to delete a specific project in the list.
3. DevEnable deletes the project from the list.

    Use case ends.

**Extensions**

* 2a. The user makes an error in writing the request.
    * 2a1. DevEnable displays an error message with the correct usage.
  
      Use case resumes at Step 2.
* 2b. DevEnable detects that the project does not exist in the list.
    * 2b1. DevEnable displays an error message that the project does not exist.
  
      Use case ends.

**Use case: UC4 - Edit project**

**MSS**

1. User <ins>views the list of projects (UC1).</ins>
2. User requests to edit a specific project in the list.
3. DevEnable edits the project in the list.

    Use case ends.

**Extensions**

* 2a. The user makes an error in writing the request.
    * 2a1. DevEnable displays an error message with the correct usage.
  
      Use case resumes at Step 2.
* 2b. DevEnable detects that the project does not exist in the list.
    * 2b1. DevEnable displays an error message that the project does not exist.
  
      Use case ends.

**Use case: UC5 - Tag client to project**

**MSS**

1. User <ins>views the list of projects (UC1).</ins>
2. User requests to tag a client to a specific project in the list.
3. DevEnable tags the client to the project in the list.

    Use case ends.

**Extensions**

* 2a. The user makes an error in writing the request.
    * 2a1. DevEnable displays an error message with the correct usage.
  
      Use case resumes at Step 2.
* 2b. DevEnable detects that the project does not exist in the list.
    * 2b1. DevEnable displays an error message that the project does not exist.
  
      Use case ends.

**Use case: UC6 - Delete client from project**

**MSS**

1. User <ins>views the list of projects (UC1).</ins>
2. User requests to delete a client from a specific project in the list.
3. DevEnable deletes the client from the project in the list.

    Use case ends.

**Extensions**

* 2a. The user makes an error in writing the request.
    * 2a1. DevEnable displays an error message with the correct usage.
  
      Use case resumes at Step 2.
* 2b. DevEnable detects that the project does not exist in the list.
    * 2b1. DevEnable displays an error message that the project does not exist.
  
      Use case ends.
* 2c. DevEnable detects that the client is not assigned to the project.
    * 2c1. DevEnable displays an error message that the client does not exist.
  
      Use case ends.

**Use case: UC7 - Edit client of project**

**MSS**

1. User <ins>views the list of projects (UC1).</ins>
2. User requests to edit a client of a specific project in the list.
3. DevEnable edits the client of the project in the list.

    Use case ends.

**Extensions**

* 2a. The user makes an error in writing the request.
    * 2a1. DevEnable displays an error message with the correct usage.
  
      Use case resumes at Step 2.
* 2b. DevEnable detects that the project does not exist in the list.
    * 2b1. DevEnable displays an error message that the project does not exist.
  
      Use case ends.
* 2c. DevEnable detects that the client is not assigned to the project.
    * 2c1. DevEnable displays an error message that the client does not exist.
  
      Use case ends.

**Use case: UC8 - Add deadline to project**

**MSS**

1. User <ins>views the list of projects (UC1).</ins>
2. User requests to add a deadline to a specific project in the list.
3. DevEnable adds the deadline to the project in the list.

    Use case ends.

**Extensions**

* 2a. The user makes an error in writing the request.
    * 2a1. DevEnable displays an error message with the correct usage.
  
      Use case resumes at Step 2.
* 2b. DevEnable detects that the project does not exist in the list.
    * 2b1. DevEnable displays an error message that the project does not exist.
  
      Use case ends.
* 2c. DevEnable detects that the deadline is not in the correct format.
    * 2c1. DevEnable displays an error message with the required format.
  
      Use case ends.

**Use case: UC9 - Delete deadline from project**

**MSS**

1. User <ins>views the list of projects (UC1).</ins>
2. User requests to delete the deadline from a specific project in the list.
3. DevEnable deletes the deadline from the project in the list.

    Use case ends.

**Extensions**

* 2a. The user makes an error in writing the request.
    * 2a1. DevEnable displays an error message with the correct usage.
  
      Use case resumes at Step 2.
* 2b. DevEnable detects that the project does not exist in the list.
    * 2b1. DevEnable displays an error message that the project does not exist.
  
      Use case ends.
* 2c. DevEnable detects that the project does not have a deadline.
    * 2c1. DevEnable displays an error message that there is no deadline.
  
      Use case ends.

**Use case: UC10 - Edit deadline of project**

**MSS**

1. User <ins>views the list of projects (UC1).</ins>
2. User requests to edit the deadline of a specific project in the list.
3. DevEnable edits the deadline of the project in the list.

    Use case ends.

**Extensions**

* 2a. The user makes an error in writing the request.
    * 2a1. DevEnable displays an error message with the correct usage.
  
      Use case resumes at Step 2.
* 2b. DevEnable detects that the project does not exist in the list.
    * 2b1. DevEnable displays an error message that the project does not exist.
  
      Use case ends.
* 2c. DevEnable detects that the project does not have a deadline.
    * 2c1. DevEnable displays an error message that there is no deadline.
  
      Use case ends.
* 2d. DevEnable detects that the deadline is not in the correct format.
    * 2d1. DevEnable displays an error message with the required format.
  
      Use case ends.

**Use case: UC11 - View list of commands**

**MSS**

1.  User requests to list all commands.
2.  DevEnable shows the list of commands.

    Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  The product should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  The product should be able to hold up to 200 projects without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse. 
4.  The product should work only for a single user. 
5.  The data should be stored locally and should be in a human editable text file. 
6.  The GUI should work well for standard screen resolutions 1920x1080 and higher and for screen scales 100% and 125%. 
7.  The GUI should be usable for resolutions 1280x720 and higher and for screen scales 150%. 
8.  The product file size should not exceed 100MB. 
9.  The document file size should not exceed 15MB. 
10.  The DG and UG should be PDF-friendly. 
11.  The product needs to be developed in a breadth-first incremental manner. 
12.  The product should not use a DBMS to store data. 
13.  The data should be saved every time a command alters the data. 
14.  The testing strategy should cover over 90% of the code. 
15.  The data should be encrypted and secured for the user.


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Client**: A contact detail that is attached to a project.
* **Project**: A project that has many clients, which typically has deliverables with deadlines.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   3. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

2. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

2. _{ more test cases …​ }_
