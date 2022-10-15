---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* Adapted from AB3 https://github.com/nus-cs2103-AY2223S1/tp

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

* has a need to manage a significant number of contacts
* private clinic practitioners who do not have access to general health service management applications like Healthhub
* private clinic practitioners of clinics who still utilise physical paper records to store patient information

**Value proposition**:
* provide doctors with a place to **store and centrally manage their patients’ information**
* allows doctors to schedule appointments, send appointment reminders and other notifications (eg. medication, payment
  information) to patients using their stored contact information.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​   | I want to …​                            | So that I can…​                                                                           |
| -------- |-----------|-----------------------------------------|-------------------------------------------------------------------------------------------|
| `* * *`  | new user  | view the user guide easily              | learn more about the product usage                                                        |
| `* * *`  | user      | add a new patient and their details     | save them for later viewing                                                               |
| `* * *`  | user      | view an existing patient's full details | know more about the patient                                                               |
| `* * *`  | user      | delete a patient's records              | clear a particular patient's medical records upon request. (E.g. due to privacy reasons)  |
| `* * *`  | user      | clear the all of a patient's records    | save time by not deleting one by one                                                      |
| `*`      | user      | view the entire list of patients        | see all my patients I have added.                                                         |
| `* * *`  | user      | search for a specific patient’s records | view one patient's record                                                                 |
| `* * *`  | user      | exit the program                        |                                                                                           |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `OmniHealth` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a patient**
**Precondition: Addressbook is displayed**

**MSS**

1. User requests to add a patient with given input fields.
2. OmniHealth adds patients with given fields into the list of patients.

    Use case ends.

**Extensions**

* 1a. The given input fields are invalid.

    * 1a1. OmniHealth shows an error message.

      Use case ends.

**Use case: UC02 - Add a record**

**MSS**

1. User !!requests to list patients (UC05)!!
2. User !!requests to display record list of specific patient (UC06)!!
3. User requests to add record with given fields into to the patient.
4. OmniHealth adds a record with given fields to the record list of the patient.

    Use case ends.

**Extensions**

* 3a. The given input fields are invalid.

    * 3a1. OmniHealth shows an error message.

      Use case resumes at step 2.

**Use case: UC03 - Delete a patient**
**Precondition: Addressbook is displayed**

**MSS**

1.  User requests to list patients
2.  OmniHealth shows a list of patients
3.  User requests to delete a specific patient in the list
4.  OmniHealth deletes the person

    Use case ends.

**Extensions**

* 3a. The list is empty.
    * 3a1. OmniHealth shows an error message.

        Use case ends.

* 3b. The given index is invalid.

    * 3b1. OmniHealth shows an error message.

      Use case ends.

* 2a. The user searches for a specific patient before deleting.

    * 2a1. User requests to find a patient
    * 2a2. OmniHealth shows a list of patient with given keyword
    * 2a3. User requests to delete a specific patient in the list
    * 2a4. OmniHealth deletes the patient

      Use case ends.

**Use case: UC04 - Delete a record**

**MSS**

1.  User !!requests to list patients (UC05).!!
2.  User !!requests to display record list of specific patient (UC06).!!
3.  User requests to delete a specific record in the list.
4.  OmniHealth deletes the record.

    Use case ends.

**Extensions**

* 3a. The list is empty.

    * 3a1. OmniHealth shows an error message.

        Use case ends.

* 3b. The given index is invalid.

    * 3b1. OmniHealth shows an error message.

      Use case ends.

* *a. Patient attempts to delete a record before listing records.

    * *a1. OmniHealth shows an error message.

      Use case ends.

**Use case: UC05 - List all patients**

**MSS**

1. User requests to list all patients.
2. OmniHealth shows a list of all patients.

    Use case ends.

**Extensions**

* 1a. The patient list is empty.

    * 1a1. OmniHealth shows an error message.
    
        Use case ends.

**Use case: UC06 - List all records for a specified patient**

**MSS**

1. User requests to display all records for the specified patient.
2. OmniHealth shows a list of all records for the specified patient.

   Use case ends.

**Extensions**

* 1a. The record list is empty.

    * 1a1. OmniHealth shows an error message.
    
        Use case ends.

* 1b. The given index is invalid.

    * 1b1. OmniHealth shows an error message.
    
        Use case ends.

**Use case: UC07 - Clear patient list**
**Precondition: Addressbook is displayed**

**MSS**

1. User requests to clear all patients from list
2. Omnihealth deletes patient list

   Use case ends

**Extensions**

* 1a. Patient list is empty

    Use case ends

**Use case: UC08 - Clear patient records**

**MSS**

1. User !!requests to list patients (UC05)!!
2. User !!requests to display record list of specific patient (UC06)!!
3. User requests to clear all records from a patient
4. Omnihealth deletes all patient records

   Use case ends

**Extensions**
* 1a. Patient record list is empty

  Use case ends

* 1b. Patient does not exist
  * 1b1. Omnihealth displays error message

  Use case ends

**Use case: UC09 - Get Help**

**MSS**

1. User requests to view help
2. Omnihealth shows list of commands

    Use case ends

**Use case: UC10 - Find patient**
**Precondition: Addressbook is displayed**

**MSS**

1. User requests to find a patient by name.
2. OmniHealth shows a list of all patients matching the input by the user.

**Extensions**
* 1a. Patient record list is empty

  Use case ends

* 1b. Patient does not exist
    * 1b1. Omnihealth displays error message

  Use case ends

**Use case: UC11 - Find patient records**

**MSS**

1. User !!requests to list patients (UC05)!!
2. User !!requests to display record list of specific patient (UC06)!!
3. User inputs a keyword to search the record list.
4. OmniHealth shows all the relevant records of the specified patient.

**Extensions**
* 1a. Patient record list is empty

  Use case ends

* 1b. Patient does not exist
    * 1b1. Omnihealth displays error message

  Use case ends

* 3a. No keywords matching can be found
    * 3a1. Omnihealth displays error message

  Use case ends

**Use case: UC12 - Add patient appointment**

**MSS**
1. User requests to add an appointment with a specified date.
2. An appointment is created for the patient.

**Extensions**
* 1a. Patient list is empty
    
    Use case ends

* 1b. Patient index does not exist
    * 1b1. Omnihealth displays an error message

    Use case ends

* 1c. Date is specified in wrong formatting
    * 1c1. Omnihealth displays an error message
    
    Use case ends

* 1d. Appointment already exists for the patient
    * 1d1. Old appointment is overriden by the new appointment

    Use case ends

*{More to be added}*

### Non-Functional Requirements

1. Should work as long as Java `11` or above is installed.
2. Should be secure to use so that patient records are not easily leaked.
3. Should be able to handle large amounts of data eg. Patient record list, Patient list

*{More to be added}*

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
