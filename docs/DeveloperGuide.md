---
layout: page
title: Developer Guide
---

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
  original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes
called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It
is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**
API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is
   executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API
call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**
API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/UpdatedModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### Storage component

**
API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

* can save both address book data and user preference data in json format, and read them back into corresponding
  objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()`— Saves the current address book state in its history.
* `VersionedAddressBook#undo()`— Restores the previous address book state from its history.
* `VersionedAddressBook#redo()`— Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`
, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

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

* private financial advisors
* require strong privacy
* has a need to store contact information of customers or associates
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

* make client data privacy more accessible to private Financial Advisors (FAs)
* FAs can assure their clients that their private information is being stored securely
* should only be used to access and store sensitive information
* targeted at FAs and does not include features that involve communication with their clients
* manage contacts faster than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …                                     | I can …                                                                          | So that …                                                                           |
|----------|--------------------------------------------|----------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|
| `* * *`  | relatively new user                        | add a new contact                                                                | I wont forget my new client's details                                               |
| `* * *`  | relatively new user                        | delete meeting dates for a contact                                               | I can maintain an updated list of my clients’ details                               |
| `* * *`  | relatively new user                        | click a button                                                                   | I can exit the program easily                                                       |
| `* * *`  | relatively new user                        | save my clients' data                                                            | the data will not be lost after I close the application                             |
| `* * *`  | relatively new user                        | update meeting dates for a contact                                               | I can maintain an updated list of my clients’ details                               |
| `* * *`  | relatively new user                        | read updated meeting dates for a contact                                         | I can always know an updated list of my clients’ details                            |
| `* * *`  | relatively new user                        | list all contacts                                                                | I will be able to find and view all my clients easily                               |
| `* * *`  | relatively new user                        | view email addresses as part of contact information                              | it would be easier for me to contact the respective client                          |
| `* *`    | user ready to start using the app          | import a set of data from another source                                         | I do not have to insert each client’s data one by one                               |
| `* *`    | user who is a little familiar with the app | search for contacts                                                              | I do not have to scroll through all of my clients’ details to find a certain client |
| `*`      | user who is a little familiar with the app | sort meetings                                                                    | I can plan for the upcoming days                                                    |
| `*`      | user ready to start using the app          | purge all current data                                                           | I can get rid of sample/experimental data I used for exploring the app              |
| `*`      | user ready to start using the app          | view a list of instructions or commands built in by the app for easier reference | it would be easier for me to adapt to these commands                                |
| `*`      | user ready to start using the app          | be asked to set up a password                                                    | I don’t forget it later                                                             |
| `*`      | user who is familiar with the app          | sort my contacts into groups                                                     | I can keep them organised                                                           |
| `*`      | user who is familiar with the app          | copy data to the clipboard with the click of a button or the use of a shortcut   | it is more convenient for me                                                        |
| `*`      | new user                                   | choose to use a generated password                                               | I can avoid using an insecure password                                              |
| `*`      | new user                                   | lock the application                                                             | unauthorised parties cannot access my data                                          |
| `*`      | long-time user                             | view the dashboard as an overview for all contacts                               | I can view the overall details of all my clients                                    |
| `*`      | long-time user                             | archive/hide unused data                                                         | I am not distracted by irrelevant data                                              |
| `*`      | long-time user                             | archive/hide unused data                                                         | I am not distracted by irrelevant data                                              |
| `*`      | long-time user                             | delete multiple “old” clients                                                    | I can easily identify clients I am still working with                               |
| `*`      | expert user                                | create shortcuts for tasks                                                       | I can save time on frequently performed tasks                                       |
| `*`      | expert user                                | set up 2-factor authentication                                                   | my data is not compromised even when my password is leaked                          |
| `*`      | expert user                                | change settings within the application                                           | I can tweak it to exactly how I like it                                             |
| `*`      | returning user                             | import data from an existing save                                                | I can move between devices                                                          |
| `*`      | returning user                             | delete all data quickly and securely when facing an emergency situation          | my data is not leaked                                                               |
| `*`      | potential user exploring the app           | be greeted with a brief overview of the privacy features available               | I am aware of them                                                                  |

### Use cases

(For all use cases below, the **System** is `FinBook` and the **Actor** is the `FA`, unless specified otherwise)

---

**Use case: UC 1 - Add a client**

**Precondition:** System is unlocked by actor

**MSS:**

1. User requests to add a client
2. System adds the client
3. Use case ends

---

**Use case: UC 2 - Modify client details**

**Precondition:** The list of clients is not empty

**MSS**

1. Actor requests to list clients
2. System shows a list of clients
3. Actor requests to modify a specific client in the list
4. System modifies the client’s details

**Extensions**

* 3a. The given index is invalid
    * 3a1. System shows an error message

  Use case resumes at step 2

---

**Use case: UC 3 - Search for client**

**Precondition:** There is/are client(s) saved

**MSS:**

1. Actor enters search keyword(s)
2. System shows a list of client(s)
3. Use case ends

**Extensions:**

* 2a. There is no client name that matches keyword(s)
    * 2a1. System shows empty list of clients

  Use case ends

---

**Use case:  UC 4 - Delete a client**

**Precondition:** There is/are client(s) saved

**MSS:**

1. Actor searches for client (**UC 3 - Search for client**)
2. System shows a list of client(s)
3. Actor requests to delete a specific client in the list
4. System deletes client’s details
5. Use case ends

**Extensions:**

* 2a. The list is empty.

Use case ends.

* 3a. The given index is invalid.

    * 3a1. System shows an error message.

  Use case resumes at step 2.
* 3b. The system asks for confirmation from actor
    * If actor gives confirmation, use case resumes at step 4
    * If actor does not confirm, use case resumes at step 5

---

**Use case: UC 5 - View private client details**

**Precondition:** System is unlocked by actor

**MSS:**

1. System shows a list of clients
2. Actor requests to view private client details of a client
3. System shows private client details of the client
4. Use case ends

**Extensions:**

* 2a. The system asks for confirmation from actor
    * If actor gives confirmation, use case resumes at step 3
    * If actor does not confirm, use case resumes at step 4

---

**Use case: UC 6 - Exit the application**

**MSS:**

1. Actor requests to exit the application
2. System saves the latest client details and terminates the program
3. Use case ends

**Extensions:**

* 1a. The system asks for confirmation from actor
    * If actor gives confirmation, use case resumes at step 2
    * If actor does not confirm, use case resumes at step 3

---

**Use case: UC 7 - Import external data**

**Precondition:** Actor has a valid file

**MSS:**

1. Actor requests to import data from file
2. System imports client data from file
3. Use case ends

**Extensions:**

* 1a. The given file is invalid
    * 1a.1 System shows an error message

  Use case resumes at step 1

---

**Use case : UC 8 - View a client**

**Precondition:** There is/are client(s) saved

**MSS:**

1. Actor finds the client they wish to view (**UC3: Search for Client**)
2. System shows a list of client(s)
3. Actor selects the client entry they wish to view
4. Actor is shown a page of details about the client
5. Use case ends

**Extensions:**

* 2a. The list is empty.

  Use case ends.
* 3b. The system asks for confirmation from actor
    * If actor gives confirmation, use case resumes at step 4
    * If actor does not confirm, use case resumes at step 5

---

**Use case: UC 9 - Import existing save file**

**Precondition:** Actor has used system previously and has a save file

**MSS:**

1. Actor requests to import data from save file
2. System imports client data from save file
3. Use case ends

**Extensions:**

* 1a. The given save file is invalid
    * 1a1. System shows an error message

  Use case resumes at step 1

---

*{More to be added}*

### Non-Functional Requirements

* Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
* Should be able to hold up to 1000 clients without a noticeable sluggishness (delay of < 0.3 seconds) in performance
  for typical usage.
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
  able to accomplish most of the tasks faster using commands than using the mouse.
* Capacity / scalability: Able to store 1000 clients in 100 milliseconds.

*{More to be added}*

Notes about project scope:
FinBook is not required to handle interaction between client and FA.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **FA**: Financial advisor
* **Client**: A client is a person whose financial data and details are managed by an FA
* **Private client detail**: A client detail that is sensitive and not meant to be shared with others
* **CSV**: A comma-separated values (CSV) file is a delimited text file that uses a comma to separate values
* **System**: FinBook

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
