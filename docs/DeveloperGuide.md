---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements** (OUTDATED)

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started** (OUTDATED)

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

RC4HDB aims to provide a complex set of features which are simple to use. Keeping this in mind, 
we are pursuing an iterative approach, adding new features and functionalities amidst the evolving requirements.
This gives rise to the following main guiding principles for RC4HDB: 

**Maintainability**

This project was adapted from an application called [`AddressBook Level 3 (AB3)`](https://se-education.org/addressbook-level3/).
`AB3` was developed in a manner that facilitates easy modification of components. This design allows the functionalities
implemented to be easily changed depending on the goals of the developers. Building upon the existing components in `AB3`, we are to
add additional classes to the major components which include [**`UI`**](#ui-component), [**`Logic`**](#logic-component) , [**`Model`**](#model-component), [**`Storage`**](#storage-component).

**Command Line Interface (CLI) Oriented**

[**CLI**](#glossary) gives the user an easy way to type commands. This is especially useful for a target audience which is familiar with the process of performing admin tasks
using a computer. For users who type fast, RC4HDB will be highly efficient and quick to respond, improving their existing processes of managing their housing database.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture (OUTDATED)

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

### UI component (OUTDATED)

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component (OUTDATED)

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
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


### Storage component (OUTDATED)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation** (OUTDATED)

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

## **Documentation, logging, testing, configuration, dev-ops** (OUTDATED)

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* works in the housing management team for [**RC4**](#glossary) with several other co-workers
* has a need to manage a significant number of residents in [**RC4**](#glossary)
* is responsible for performing a wide variety of tasks including liasing with students/staff
* requires quick access to contact details and other relevant resident information
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using [**CLI**](#glossary) apps

**Value proposition**:

* manage contacts faster than a typical mouse/Graphic User Interface (GUI) driven app
* requires less technical knowledge to perform complex tasks
* easier on the eyes, as compared to compressed rows of data on Excel


### User stories

Our user stories have been packaged with the relevant functionalities that we will implement/have implemented.

They have been extensively documented [here](https://github.com/AY2223S1-CS2103T-W12-3/tp/issues?q=is%3Aissue+label%3Atype.Story), and have been prioritized accordingly:
1. High `* * *` - must have
2. Moderate `* *` - nice to have
3. Low `*` - unlikely to have

| Priority | As a ...      | I want to ...                                                                      | So that ...                                      | Story Type |
|----------|---------------|------------------------------------------------------------------------------------|--------------------------------------------------|------------|
| `***`    | user          | view relevant information about [**RC4**](#glossary) residents                     |                                                  | Story      |
| `***`    | user          | specify which fields I want to see when listing data                               | my screen is less cluttered                      | Story      |
| `***`    | user          | import my old data into the application                                            |                                                  | Story      |
| `***`    | user          | view a smaller list of [**RC4**](#glossary) residents that pass certain conditions |                                                  | Story      |
| `**`     | advanced user | give residents roles                                                               | I can further categorize them                    | Epic       |
| `**`     | user          | search for residents using a portion of their names                                | I do not have to remember their exact names      | Story      |
| `**`     | user          | export residents' data in a familiar format                                        |                                                  | Story      |
| `**`     | new user      | see sample data                                                                    | I can see how the app will look like when in use | Story      |
| `**`     | user          | delete multiple residents' data from the app quickly                               | I can save time                                  | Story      |
| `**`     | user          | use the system without referring to the user guide                                 |                                                  | Story      |
| `**`     | user          | switch between different data files                                                |                                                  | Story      |
| `*`      | advanced user | toggle input commands without repeating the command word                           | I can increase the efficiency of operations      | Epic       |
| `*`      | user          | update settings                                                                    | I can customize the app for my use               | Epic       |

*{More to be added}*

<!-- keep in case needed
Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​         | I want to …​                               | So that I can…​                                                        |
| -------- |--------------------------------------------|-----------------------------------------------|------------------------------------------------------------------------|
| `* * *`  | user                                       | view relevant information about RC4 residents | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new person                              |                                                                        |
| `* * *`  | user                                       | delete a person                               | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a person by name                         | locate details of persons without having to go through the entire list |
| `* *`    | user                                       | hide private contact details                  | minimize chance of someone else seeing them by accident                |
| `*`      | user with many persons in the address book | sort persons by name                          | locate a person easily                                                 |
-->

### Use cases

(For all use cases below, the **System** is the `RC4HDB` and the **Actor** is the `user`, unless specified otherwise)


**Use case: UC1. Getting help with the application**

**MSS**
1. User needs help related to RC4HDB.
2. User requests for help in RC4HDB.
3. RC4HDB displays a message that directs the user to our user guide.

    Use case ends.

<br>

**Use case: UC2. Add a single resident**

**MSS**
1. New resident moves into [**RC4**](#glossary).
2. User has the personal details of a resident they wish to add.
3. User adds the resident to RC4HDB.
4. RC4HDB adds the resident to the data file.
5. RC4HDB displays the name and other information of the resident.

    Use case ends.

**Extensions**

* 3a. User enters resident information in an invalid format.

    * 3a1. RC4HDB shows an error message.

    Use case resumes at step 3.
  
  <br>

**Use case: UC3. Listing out information of all residents**

**MSS**
1. User wants to see the full list of residents in [**RC4**](#glossary).
2. User requests for the list of residents from RC4HDB.
3. RC4HDB displays the details of all residents in [**RC4**](#glossary).

    Use case ends.

**Extensions**

* 1a. The user wants to view only certain fields in the list.
    
  * 1a1. The user specifies which fields he wants to see or hide.
    
  Use case resumes at step 2.


* 2a. The list is empty.

    Use case ends.

<br>

**Use case: UC4. Editing a single resident’s information**

**MSS**

1. Resident has a specific change in personal information.
2. User edits the resident's information in RC4HDB.
3. RC4HDB updates the information of the specified resident.
4. RC4HDB displays a message detailing the changes made.

    Use case ends.

**Extensions**

* 2a. There is no relevant category for that information.
  * 2a1. RC4HDB shows an error message.
  
  Use case ends.


* 2b. User enters resident information in an invalid format.

    * 2b1. RC4HDB shows an error message.

    Use case resumes at step 2.

<br>

**Use case: UC5. Finding a resident’s information by their name**

**MSS**
1. User wants to search for a resident’s information.
2. User makes a request to RC4HDB to find a resident by their name.
3. RC4HDB searches the database for the given name.
4. RC4HDB displays the resident's information.

    Use case ends.

**Extensions**

* 3a. RC4HDB cannot find any resident matching the user input.

    * 3a1. RC4HDB shows an error message.

    Use case resumes at step 2.


* 4b. RC4HDB finds multiple residents matching the user input.

    * 4b1. RC4HDB shows a list of all matching residents.

    Use case ends.

<br>

**Use case: UC6. Filtering the list of all residents by specific fields**

**MSS**
1. User wants to see a list of residents that fall under a certain category.
2. User requests for a filtered list from RC4HDB based on the relevant categories.
3. RC4HDB shows the filtered list.

    Use case ends.

**Extensions**

* 2a. User enters an invalid specifier i.e. one that is not `/all` or `/any`.

    * 2a1. RC4HDB shows an error message.

    Use case resumes at step 2.


* 2b. User enters multiple specifiers i.e. both `/all` and `/any`.

    * 2b1. RC4HDB shows an error message.

    Use case resumes at step 2.


* 2c. User enters a category that does not exist.

    * 2c1. RC4HDB shows an error message.

    Use case resumes at step 2.


* 2d. User enters a value that does not exist in the category.

    * 2d1. RC4HDB shows an error message.

    Use case resumes at step 2.

<br>

**Use case: UC7. Deleting a single resident**

**MSS**
1. Resident moves out of [**RC4**](#glossary).
2. User deletes the resident from RC4HDB.
3. RC4HDB removes the corresponding resident from the database.
4. RC4HDB displays the details of that resident that has been deleted.

    Use case ends.

**Extensions**

* 2a. User enters an invalid input.

    * 2a1. RC4HDB shows an error message.

    Use case resumes at step 2.

<br>

**Use case: UC8. Clearing all data**

**MSS**
1. User wants to clear all data from the current working file.
2. RC4HDB clears all data from the current working file.
3. RC4HDB shows a success message.

    Use case ends.

<br>

**Use case: UC9. Exiting the application**

**MSS**
1. User has completed his/her tasks and wants to exit the application.
2. User exits the application.
3. RC4HDB application closes.

    Use case ends.

**Extensions**

* 2a. User clicks on the exit button.

    * 2a1. RC4HDB application closes.

    Use case ends.

<br>

**Use case: UC10. Importing data from [CSV](#glossary) file**

**MSS**
1. User has a data file with resident’s information, and wants to view it in RC4HDB.
2. User imports the file.
3. RC4HDB reads the file.
4. RC4HDB displays the name of the [**CSV**](#glossary) file after the file has been read.
5. RC4HDB displays all the information stored in the file.

    Use case ends.

**Extensions**
* 2a. Information in the [**CSV**](#glossary) file has not been stored in the proper format.

    * 2a1. RC4HDB shows an error message.

    Use case resumes at step 2.


* 3a. No file could be found at the specified file path.

    * 3a1. RC4HDB shows an error message.

    Use case resumes at step 2.

<br>

**Use case: UC11. Exporting data to [CSV](#glossary) file**

**MSS**
1. User wants the data in a [**CSV**](#glossary) file.
2. User exports the file.
3. RC4HDB creates a new [**CSV**](#glossary) file using user input.
4. RC4HDB shows a success message.

    Use case ends.

*{More to be added}*

### Non-Functional Requirements

#### Accessibility
* The application must not require an internet connection to work
* Inputs must be done through the [`Command Line Interface (CLI)`](#glossary)
* The application must be sufficiently light-weight to be run by older computer systems
* Should work on any [`Mainstream OS`](#glossary) as long as it has Java 11 or above installed
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse

#### Quality
* The application must be able to be easily learnt within a week by administrative staff with limited technical knowledge

#### Technical
* The system must be able to handle approximately 300 to 500 entries without a noticeable sluggishness in performance for typical usage
* The system must be flexible and extensible for potential overhaul or changes to the [**RC4**](#glossary) housing management system

*{More to be added}*

### Glossary
* **Command Line Interface (CLI)**: An area in the application interface for users to input commands
* **Comma-Separated Values (CSV)**: A delimited text file that uses a comma to separate values and each line of the file is a data record 
* **Display Window**: An area in the application interface for users to view the output of their commands
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **NUS**: The National University of Singapore
* **RC4**: Residential College 4 which resides in NUS
* **Resident**: A NUS student who lives in RC4

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
