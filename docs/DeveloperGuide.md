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

<img src="images/dg/ArchitectureDiagram.png" width="280" />

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

<img src="images/dg/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/dg/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/dg/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Patient` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/dg/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `HealthContactParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a patient).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/dg/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/dg/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `HealthContactParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `HealthContactParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

#### Model

<img src="images/dg/ModelClassDiagram.png" width="450" />

The `Model` component,

* stores the HealthContact data i.e.,
    * all `Patient` objects (which are contained in a `UniquePatientList` object)
    * all `Appointment` objects (which are contained in a `UniqueAppointmentList` object)
    * all `Bill` objects (which are contained in a `UniqueBillList` object)
* stores the currently 'selected' `Patient`, `Appointment` or `Bill` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Patient>`, `ObservableList<Appointment>` or `ObservableList<Bill>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` object.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

#### Patient

<img src="images/dg/UniquePatientListClassDiagram.png" width="450">

* The `Patient` object stored in the `Model`, stores the personal information of a patient. Take note that every `Patient` must have a unique `Name` because the application differentiates `Patient` by `Name`.
* Take note that,
    * the application differentiates patients by `Name`
    * same letters of `Name` in different cases are considered as the same `Name`
#### Appointment

<img src="images/dg/UniqueAppointmentListClassDiagram.png" width="450">

* The `Appointment` object stored in the `Model`, stores the information of an appointment.
* Take note that,
    * the `Name` must be a `Name` from an existing `Patient`
    * the application differentiates appointments by all four attributes
    * same letters of `MedicalTest`, `Doctor` and `Name` in different cases are considered as same `MedicalTest`, `Doctor` and `Name`.

#### Bill

<img src="images/dg/UniqueBillListClassDiagram.png" width="450">

* The `Bill` object stored in the `Model`, stores the information of a bill.
* Take note that,
    * the `Appointment` must be the same as an existing `Appointment`
    * one `Appointment` can attach at most one bill
    * the application differentiates bills by all four attributes.


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/dg/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both HealthContact data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `HealthContactStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.healthcontact.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedHealthContact`. It extends `HealthContact` with an undo/redo history, stored internally as an `healthContactStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedHealthContact#commit()` — Saves the current HealthContact state in its history.
* `VersionedHealthContact#undo()` — Restores the previous HealthContact state from its history.
* `VersionedHealthContact#redo()` — Restores a previously undone HealthContact state from its history.

These operations are exposed in the `Model` interface as `Model#commitHealthContact()`, `Model#undoHealthContact()` and `Model#redoHealthContact()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedHealthContact` will be initialized with the initial HealthContact state, and the `currentStatePointer` pointing to that single HealthContact state.

![UndoRedoState0](images/dg/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th patient in the HealthContact. The `delete` command calls `Model#commitHealthContact()`, causing the modified state of the HealthContact after the `delete 5` command executes to be saved in the `healthContactStateList`, and the `currentStatePointer` is shifted to the newly inserted HealthContact state.

![UndoRedoState1](images/dg/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new patient. The `add` command also calls `Model#commitHealthContact()`, causing another modified HealthContact state to be saved into the `healthContactStateList`.

![UndoRedoState2](images/dg/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitHealthContact()`, so the HealthContact state will not be saved into the `healthContactStateList`.

</div>

Step 4. The user now decides that adding the patient was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoHealthContact()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous HealthContact state, and restores the HealthContact to that state.

![UndoRedoState3](images/dg/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial HealthContact state, then there are no previous HealthContact states to restore. The `undo` command uses `Model#canUndoHealthContact()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/dg/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoHealthContact()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the HealthContact to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `healthContactStateList.size() - 1`, pointing to the latest HealthContact state, then there are no undone HealthContact states to restore. The `redo` command uses `Model#canRedoHealthContact()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the HealthContact, such as `list`, will usually not call `Model#commitHealthContact()`, `Model#undoHealthContact()` or `Model#redoHealthContact()`. Thus, the `healthContactStateList` remains unchanged.

![UndoRedoState4](images/dg/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitHealthContact()`. Since the `currentStatePointer` is not pointing at the end of the `healthContactStateList`, all HealthContact states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/dg/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/dg/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire HealthContact.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the patient being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### Find Feature

#### Current Implementation

The find mechanism makes use of a predicate to filter the list of patients through `updateFilteredPatientList` method in the `ModelManager` class.
The `FindCommandParser` gets the user input to set it as the predicate. The user can find all fields of patients, appointments and bills.

Given below is an example usage scenario and how the find mechanism behaves at each step.

Step 1. The user launches the application. All patients, appointments and bills are shown on different sections
of the application as indexed lists.

Step 2. The user executes `find n/John` command to find all patients with the name "John". 
The `find` command calls `Model#updateFilteredPatientList(predicate)` to update the list of patients in the application.

Step 3. The application displays the list of patients with the name "John" on the patient list panel. 

The find feature is now seperated for the patients, appointments and bills sections.

Design considerations:
1. Length of command word
2. Whether to use a prefix for the search term
3. Number of keywords used for the search term

Alternatives:

1. Use a shorter command word (e.g. find instead of findpatient)
    - Pros: Easy to type
    - Cons: May be confused with the find command for appointments and bills
    - Cons: May be confused with the find command for patients
2. Use a prefix for the search term (e.g. find n/John)
    - Pros: Easy to type
    - Pros: Easy to remember
    - Cons: May be confused with the find command for appointments and bills
3. Combine find feature for patients, appointments and bills into one command
    - Pros: Easy to type
    - Pros: Easy to remember
    - Cons: May be confusing to the user

###Delete Feature

####Current Implementation

The delete mechanism deletes a patient, appointment or bill identified by their index in the list. The deletion is done
through the `deletePatient`, `deleteAppointment` and `deleteBill` functions in `ModelManager`. 

Given below is an example usage scenario and how the delete mechanism behaves at each step.

Step 1. The user launches the application. All patients, appointments and bills are shown on different sections
of the application as indexed lists.

Step 2. The user executes `deletePatient 2` command to delete the patient at index 2 in the list.
The `delete` command calls `Model#deletePatient` to delete the patient from the list of patients.

The delete feature is now seperated for the patients, appointments and bills sections. Deleting a patient also deletes 
related appointments.


### Select Feature

#### Current Implementation

The select commands simulates a click on the 'PatientCard' or 'AppointmentCard' in the UI.

The select methods are separated for patients and appointments, with command word 'selectpatient'
and 'selectappointment' respectively. 

The select commands make use of the index of a patient or an appointment in the 'FilteredList's
to identify whose appointments and bills to show.

The 'SelectPatientCommandParser' and 'SelectAppointmentCommandParser' convert
input String containing target index to the SelectCommand objects.

On execution, the SelectPatientCommand will invoke the selectPatient() and selectAppointment() in the Model to
update the FilteredAppointmentList and FilteredBillList to contain selected patient's information only.

Given below is an example usage scenario and how the find mechanism behaves at each step.

Step 1. The user executes `selectpatient 1` command to show all appointments and bills 
tied to the first listed patient.
The `SelectPatient` command calls `Model#selectPatient(index)` to update the list of appointments
and bills in the application.

Step 2. The application displays the list of appointments and bills with the name equals to
the first patient on the patient list panel.

The select feature is now seperated for the patients and appointments sections.

Design considerations:
1. Length of command word

Alternatives:

1. Use a shorter command word (e.g. slp instead of selectpatient, sla instead of selectappointment)
    - Pros: Easy to type
    - Cons: Easier to type the wrong short-form command as they differ by 1 letter



### Sort Feature

#### Current Implementation

The sort feature allows the user to sort the list of patients, appointments and bills in the application. 

The sort feature is now separated for the patients, appointments and bills sections.

#### Design considerations:

**Aspect: How sort executes:**

* **Alternative 1 (current choice):** Saves the entire HealthContact.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.
* **Alternative 2:** Individual command knows how to sort by itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the patient being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

**Product scope**
* Only provides necessary information that we want to retrieve for patients, except sensitive medical information 
like health problems
* Does not execute any of the real-world tasks except to remind the admin staff

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`


| Priority | As a …​                                    | I want to …​                  | So that I can…​                                                          |
|----------|--------------------------------------------|-------------------------------|--------------------------------------------------------------------------|
| `* * *`  | new user                                   | see usage instructions        | refer to instructions when I forget how to use the App                   |
| `* * *`  | user                                       | add a new patient             | I can keep track of patient details                                      |
| `* * *`  | user                                       | export to excel file          | share the information with others when necessary                         |
| `* * *`  | user                                       | delete a patient              | remove patient entries that I no longer need                             |
| `* * *`  | user                                       | find a patient by name        | look up a patient's details without having to go through the entire list |
| `*`      | user                                       | hide private contact details  | minimize chance of someone else seeing them by accident                  |
| `* *`    | user with many patients | sort patients by medical test | find out which patients are doing a particular medical test              |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `HealthContact` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Editing a patient**

**MSS**

1.  User requests to edit a patient
2.  HealthContact shows the detailed information about the patient
3.  User requests to edit specific information about the patient
4.  HealthContact edits the patient

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given name is invalid.

    * 3a1. HealthContact shows an error message.

      Use case resumes at step 2.

**Use case: Adding a patient**

**MSS**

1.  User requests to add a patient
2.  HealthContact requests for details of the patient to add
3.  User enters the requested details.
4.  HealthContact adds the patient

    Use case ends.



**Extensions**


* 3a. The format for add command is not followed.

    * 3a1. HealthContact shows an error message.

      Use case resumes at step 2.



**Use case: Adding a bill to an appointment**

**MSS**

1. User requests to add a bill to an appointment.
2. HealthContact requests for details of the bill to add to the appointment.
3. User enters the requested details.
4. HealthContact adds the bill to the appointment.

    Use case ends.


**Extensions**


* 3a. The format for AddBillCommand is not followed.

    * 3a.1 HealthContact shows an error message.

      Use case resumes at step 2.

* 3b. The bill for an appointment already exists.

    * 3b.1 HealthContact shows an error message.

      Use case resumes at step 2.



**Use case: Editing a bill of an appointment**

**MSS**

1. User requests to edit a bill of an appointment.
2. HealthContact requests for details of the bill to be edited.
3. User enters the requested details.
4. HealthContact edits the bill.

    Use case ends.


**Extensions**

* 3a. The format for EditBillCommand is not followed.
    
    * 3a.1 HealthContact shows an error message.

      Use case resumes at step 2.

* 3b. Index of the edited bill is not allowed.

    * 3b.1 HealthContact shows an error message.

      Use case resumes at step 2.


### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 patients without a noticeable sluggishness in performance for typical usage.

### Glossary

* **Doctor**: A patient who uses the app
* **Patient**: A customer who goes to see a doctor

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

### Deleting a patient

1. Deleting a patient while all patients are being shown

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No patient is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
