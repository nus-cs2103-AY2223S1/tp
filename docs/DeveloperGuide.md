---
layout: page
title: Developer Guide
---

<p align="center" width="100%">
    <img width="250" src="images/OmniHealth_logo.png" alt="">
</p>

## About OmniHealth

OmniHealth is a **Patient Management System** designed specifically for private clinicians to manage their patients' information, records, and scheduled appointments.
OmniHealth allows its users to manage and monitor their patient database from a single location.
Furthermore, users can use OmniHealth's **sorting and filtering tool** to conveniently manage and find their patients' details and records of previous visits.
Additionally, OmniHealth's **appointment tracker** allows users to tag upcoming appointments for each patient.

This Developer Guide explains in detail how OmniHealth is developed and implemented. It begins with an overview of OmniHealth's **system architecture**, then breaks it down into *smaller components* and provides detailed descriptions for each component, and ultimately explains the reasoning behind how key functionalities of this application are implemented. As a developer, you can utilise this guide to maintain and update OmniHealth.

<div style="page-break-after: always;"></div>

## Table of Contents
- [**Acknowledgements**](#acknowledgements)
- [**Setting up, getting started**](#setting-up-getting-started)
- [**Design**](#design)
  - [Architecture](#architecture)
  - [UI component](#ui-component)
  - [Logic component](#logic-component)
  - [Model component](#model-component)
  - [Storage component](#storage-component)
  - [Common classes](#common-classes)
- [**Implementation**](#implementation)
  - [Add record feature](#add-record-feature)
  - [List records feature](#list-records-feature)
  - [Delete record feature](#delete-record-feature)
  - [Edit record feature](#edit-record-feature)
  - [Find records feature](#find-records-feature)
  - [Clear all records feature](#clear-all-records-feature)
  - [Add appointment feature](#add-appointment-feature)
  - [Clear appointment feature](#clear-appointment-feature)
  - [[Proposed] Upcoming appointment tracker feature](#proposed-upcoming-appointment-tracker-feature)
- [**Documentation, logging, testing, configuration, dev-ops**](#documentation-logging-testing-configuration-dev-ops)
- [**Appendix: Requirements**](#appendix-requirements)
  - [Product scope](#product-scope)
  - [User stories](#user-stories)
  - [Use cases](#use-cases)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
- [**Appendix: Instructions for manual testing**](#appendix-instructions-for-manual-testing)
  - [Launch and shutdown](#launch-and-shutdown)
  - [Displaying a patient's record list](#displaying-a-patients-record-list)
  - [Adding a record](#adding-a-record)
  - [Editing a record](#editing-a-record)
  - [Find a record in a patient's record list](#find-a-record-in-a-patients-record-list)
  - [Undo a find command](#undo-a-find-command)
  - [Deleting a record](#deleting-a-record)
  - [Clearing all records from a patient's record list](#clearing-all-records-from-a-patients-record-list)
  - [Adding an appointment to a patient](#adding-an-appointment-to-a-patient)
  - [Removing an appointment from a patient](#removing-an-appointment-from-a-patient)
  - [Saving data](#saving-data)
- [**Appendix: Effort**](#appendix-effort)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Acknowledgements**

* Adapted from AB3 https://github.com/nus-cs2103-AY2223S1/tp
* Icon was adapted from Eatlogos https://www.eatlogos.com/download-logo/vector-medical-logo/96

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

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

<div style="page-break-after: always;"></div>
For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

[<*Back to ToC*>](#table-of-contents)
<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<img src="images/UiClassDiagram.png" width="650"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `RecordListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data and display the correct panel.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` and `Record` object residing in the `Model`.

[<*Back to ToC*>](#table-of-contents)

<div style="page-break-after: always;"></div>

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

<img src="images/DeleteSequenceDiagram.png" width="650"/>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

[<*Back to ToC*>](#table-of-contents)

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="750" />


The `Model` component,

* stores the address book data.
  * all `Person` objects (which are contained in a `UniquePersonList` object)
  * all `Record` objects (which are contained in a `RecordList` object)
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* likewise, the currently 'selected' `Record` objects are stored as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Record>` that can be 'observed'.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

[<*Back to ToC*>](#table-of-contents)

<div style="page-break-after: always;"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="650" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

[<*Back to ToC*>](#table-of-contents)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes the details on how certain noteworthy features are implemented.

### About Patient Records

>OmniHealth manages patient records generated during patient consultations. Each `Patient` holds a list of 
medical records that the user is able to modify and edit. The `Record` class encapsulates a medical
record that comprises a date, medical information and medicine prescribed (if any).

### Add record feature

#### Implementation

Following the command execution pathway, the implementation of adding records uses the exposed `Model#addRecord(Record)`
operation in the `Model` API which operates through a `DisplayedPerson` which encapsulates the current patient whose list of records is being 
displayed to the user. Record operations are performed through the displayed person, for example,`DisplayedPerson#addRecord(Record)`.
The current patient can be set by `DisplayedPerson#setPerson(Person, AddressBook)`.

The parsing of user input is facilitated by `AddRecordCommandParser`. `AddRecordCommandParser#parse()` parses user 
input string to return a `AddRecordCommand` object with a `Record` derived from the given inputs.

Given below is an example usage scenario.

Precondition: User should be currently viewing a specific patient's record list using the `rlist` command. The current
patient is set using `DisplayedPerson#setPerson(Person, AddressBook)`.

Execution: User executes `radd d/01-02-2013 1230 r/Patient developed fever. m/Paracetamol` to add a new record containing 
the date/time of `01-02-2013 1230`, medication remark of `Patient developed fever.` and medication prescription of
`Paracetamol` into the current displayed person's record list. The `radd` command calls `Model#addRecord(Record)` 
which performs the adding of records to the `DisplayedPerson` held by the `Model`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The medication field is an optional input
</div>

The following sequence diagram shows how the add record operation works:

<img src="images/AddRecordCommandSequenceDiagram.png" width="650" />


#### Design considerations:

**Aspect: Model-Person Interaction:**

* **Alternative 1 (current choice):** Use DisplayedPerson as a wrapper class.
    * Pros: Maintain immutability within Person and Model classes, Easy to set current person whose record list is being
displayed
    * Cons: Longer command execution pathway as DisplayedPerson acts as an intermediary class between Model and Person.
  
* **Alternative 2:** Allow model to directly interact with Person's record list.
  * Pros: Easy to implement, simpler command execution pathway.
  * Cons: Potentially violates OOP.
  
[<*Back to ToC*>](#table-of-contents)

<div style="page-break-after: always;"></div>

### List records feature

#### Implementation:

The implemented list records mechanism is facilitated by `ListRecordCommandParser`, which extends `AddressBookParser`. `ListRecordCommandParser` implements the following operations:
- `ListRecordCommandParser#parse()` - Parses the input argument into an `Index` and creates a new `ListRecordCommand` object with the parsed `Index`.

The `ListRecordCommand` object then communicates with the `Model` API when it is executed, more specifically, by calling the following methods that are implemented in `ModelManager`:
- `Model#setPersonWithRecords(Person)` - Sets the person whose record list is being displayed (`ModelManager#personWithRecords`) to the given `Person`, so that the displayed record list can be manipulated by calling methods such as `Model#addRecord()` and `Model#deleteRecord()`.
- `Model#setRecordListDisplayed(boolean)` - Setter for a flag to determine if a record list is being displayed; set to `true`.
- `Model#setFilteredRecordList(Person)` - Sets the *filtered list* of records (`ModelManager#filteredRecords`) to the record list of the given Person.
- `Model#updateFilteredRecordList(Predicate)` - Updates the filter of the *filtered list* of records to the filter given by the predicate; an `always true` predicate is given so that all records will be included in the *filtered list*.

At the final step of the execution of the `ListRecordCommand` object, a `CommandResult` object is returned.

<div style="page-break-after: always;"></div>

The Sequence Diagram below shows how the list record operation works:

<img src="images/ListRecordSequenceDiagram.png" width="650" />

Example usage scenario:
- Precondition: The user should be viewing the patient list by using the `list` command.
- Execution: The user executes `rlist 1` to list the records of the 1st patient in the displayed patient list.

[<*Back to ToC*>](#table-of-contents)

### Delete record feature

#### Implementation:
The delete record mechanism is facilitated by `DeleteRecordCommandParser` which extends `AddressbookParser`.

`DeleteRecordCommandParser` implements the following operations:
* `DeleteRecordCommandParser#parse()` - Parses the input argument into an `Index` and 
creates a new `DeleteRecordCommand` object with the parsed `Index`.

The `DeleteRecordCommand` object then communicates with the `Model` API when it is executed, more specifically, by calling the following methods that are implemented in `ModelManager`:
* `Model#getFilteredRecordList()` - Returns the record list which is displayed.
* `Model#isRecordListDisplayed()` - Returns a boolean result if a record list is being displayed.
* `Model#deleteRecord(Record)` - Deletes the input record from the DisplayedPerson (`Model#personWithRecords`).

Given below is an example usage scenario for the command.

* Precondition: 
  * User should be currently viewing a specific patient's record list using the `rlist` command. 
  * The current patient is set using `DisplayedPerson#setPerson(Person, AddressBook)`.

* Execution: 
  * User executes `rdelete 1` to delete the 1st record in the displayed record list. The `rdelete` command calls `Model#deleteRecord(Record)` which performs the deletion of records from the `DisplayedPerson` held by the `Model`.

[<*Back to ToC*>](#table-of-contents)
<div style="page-break-after: always;"></div>

### Edit record feature

#### Implementation:
The edit record mechanism is facilitated by `EditRecordCommandParser` which extends `AddressbookParser`.

`EditRecordCommandParser` implements the following operations:
* `EditRecordCommandParser#parse()` - Parses the input argument by storing the index and respective prefix as in `ArgumentMultimap`

The `EditRecordCommand` object then calls `createEditedRecord` and communicates with the `EditRecordDescriptor` to create 
a new `editedRecord` to replace the `Record` that is to be edited.

The `EditRecordCommand` object then communicates with the `Model` API when it is executed, more specifically,
by calling the following methods that are implemented in `ModelManager`:
* `Model#setRecord(Record, Record)` - Sets the target record of `Model#personWithRecords` to the new edited record.
* `Model#updateFilteredRecordList(Predicate<Record>)` - Updates the filter of the filtered record list to filter by the given predicate;
in this case, show all the records.

Given below is an example usage scenario for the command.

* Precondition:
    * User should be currently viewing a specific patient's record list using the `rlist` command.
    * The current patient is set using `DisplayedPerson#setPerson(Person, AddressBook)`.

* Execution:
    * User executes `redit 1 r/Fever d/12-12-2012 1200 m/` to set the 1st record in the displayed record list to a new
  record containing the date/time of `12-12-2012 1200`, record data of `Fever` and sets the medications to empty.

[<*Back to ToC*>](#table-of-contents)
<div style="page-break-after: always;"></div>

### Find records feature

#### Implementation:
While the find patient function only searches the name field, the find record function searches for `Record` objects
based on multiple specified search parameters namely the `recorddata`, `medications` and `recorddate` field. This is done using 
the `rFind` command implemented in the `FindRecordCommand` class which is facilitated by helper classes
`RecordContainsKeywordPredicate` and `FindRecordCommandParser`which extends `Predicate` and `AddressbookParser` 
respectively. The following operations are implemented for the `rfind` command:

* `FindRecordCommandParser#parse()` - Parses the input arguments by extracting the required arguments and creates a new
`FindRecordCommand` with the correct inputs.

* `RecordContainsKeywordPredicate#test()` - Tests whether the record under testing contains any of the keywords
in the correct fields.

Only `FindRecordCommandParser#parse` is exposed in the Parser interface as Parser#parse().

Given below is an example usage and how the find record mechanism works at each step.

* Precondition:
  * User should be currently viewing a specific patient's record list using the `rlist` command.
  * The current patient is set using `DisplayedPerson#setPerson(Person, AddressBook)`.

* Execution:
  1. User executes `rfind m/Paracetamol d/10-2022`.
  2. The input is parsed by the `AddressBookParser#Parse()` which calls `FindRecordCommandParser#parse()`. Here, the input <br>
     is checked for which are the fields that have been specified. A `FindRecordCommand` object is then created containing
     a `RecordContainsKeywordPredicate` that represents the search parameters to match a `Record` object to.
  3. The record list that is being displayed is implemented using an `FilteredList`. This means that the 
     `RecordContainsKeywordPredicate` object can be passed into the `FilteredList#setPredicate()` method. This tests each `record`
     object stored inside the record list.
  4. The record list that is displayed is updated to only show `record` objects that pass the test.

The following sequence diagram demonstrates how the find record mechanism works:

<img src="images/FindRecordSequenceDiagram.png" width="650" />

<div style="page-break-after: always;"></div>

The following activity diagram demonstrates what happens when a find record command is used:

![FindRecordActivityDiagram](images/FindRecordActivityDiagram.png)


#### Design Considerations:
**Aspect: The effect of multiple search parameters**
* **Alternative 1 (current choice)**: More search parameters tightens the search constraints
  * Pros: The user is able to narrow down the search to easily find a specific record. This is especially useful
    when the record list becomes very large. For example, searching by medication alone may not be very effective since
    medications like Paracetamol are commonly prescribed. Instead, it will be more useful to specify which month was a particular medication prescribed.
  * Cons: A record may become more difficult to find if the user does not remember the correct details regarding what is stored in the record.
  
* **Alternative 2**: More search parameters loosens the search constraints
  * Pros: Searching for multiple records that may not share commonalities in the data stored becomes easier. 
  * Cons: Scenerios where this would be useful may not occur frequently. 

**Aspect: How an unspecified field is represented**

Since the search parameters are optional, there is a problem during parsing of differentiating between a parameter that is not specified
and a parameter that is not specified but is left blank. 

* **Alternative 1 (current choice)**: A specific String is used to replace the value of a parameter that is not specified 
  * Pros: Since this string acts as an identifier that the parameter is valid even though it was empty, it is easy to implement.

* **Alternative 2**: Use a boolean value to represent if a parameter is specified by the user input
  * Pros: This is relatively simple to implement.
  * Cons: This requires constant checks by multiple classes to test if the parameter has been specified and thus breaks the
          abstraction barrier.
  
[<*Back to ToC*>](#table-of-contents)

### Clear all records feature

The clear record command `rclear` allows the application to clear all the existing records in the current active record
list.

#### Implementation:

Given below is an example usage scenario for the command.

**Step 1:** The user launches the application.

**Step 2:** The user executes the `rlist INDEX` command to show the record list of a specified patient. 

**Step 3:** The user executes the `rclear` command to clear all existing records in the record list.

[<*Back to ToC*>](#table-of-contents)

<div style="page-break-after: always;"></div>

### About Appointment Features

> Each `Patient` holds an appointment reference for record keeping that the user is able to edit and clear.
There is currently no support for the automatic removal of appointments which dates have passed. <br>
Past appointments needs to either be overwritten by a new appointment or be cleared with the `apptcl` command.

### Add appointment feature

The add appointment feature allows OmniHealth to manage future appointments for patients. 

#### Implementation:
The add appointment command mechanism is facilitated by the `AddAppointmentCommandParser` class which extends the `AddressbookParser`.

`AddAppointmentParser#parse()` is exposed in the Parser interface as Parser#parse().

`AddAppointmentParser` implements the following operations:
* `AddAppointmentParser#parse()` - Parses the input arguments by storing the index and the prefix of its respective values as 
an `ArgumentMultimap`, and creates a new `AddAppointmentCommand` object with the parsed time and index. 

The `AddAppoinmentCommand` object then communicates with the `Model` API by calling the following methods:
* `Model#setPerson(Person, Person)` - Sets the person in the existing patient list to the new `Person` object which has
been edited by `AddAppointmentCommand#execute()`.
* `Model#updateFilteredPersonList(Predicate)` - Updates the view of the application to show all patients.

The method `AddAppointmentCommand#execute()` returns a `CommandResult` object, which stores information about the completion 
of the command.

<div style="page-break-after: always;"></div>

The diagram below details how the operation of adding an appointment works.

![AddAppointmentSequenceDiagram](images/AddAppointmentSequenceDiagram.png)

Given below is an example usage scenario for the command.

**Step 1:** The user launches the application.

**Step 2:** The user executes the `appt INDEX d/ dd-MM-yyyy` command in the CLI.

**Step 3:** An appointment will be assigned to the patient specified with the index input.

#### Design considerations:

**Aspect: Model-Person Interaction:**

* **Alternative 1 (current choice):** Utilise `model#setPerson` to add the edited person into the model, doing the direct editing in `AddAppointmentCommand#execute()`.
  * Pros: Maintain immutability within Person and Model classes.
  * Cons: Potentially violates the Single Responsibility Principle.

* **Alternative 2:** Create methods in model specifically to edit the appointment fields of the patients.
  * Pros: More OOP, follows the Single Responsibility Principle by not having `AddAppointmentCommand#execute()` perform the editing directly.
  * Cons: Longer command execution, requiring more parts to work together.

[<*Back to ToC*>](#table-of-contents)

<div style="page-break-after: always;"></div>

### Clear appointment feature

#### Implementation:
The add appointment mechanism is facilitated by `ClearAppointmentCommandParser` which extends `AddressbookParser`.

`ClearAppointmentParser` implements the following operations:
* `ClearAppointmentParser#parse()` - Parses the input arguments by storing the index and the prefix of its respective 
values as an `ArgumentMultimap.`

`ClearAppointmentParser#parse()` is exposed in the Parser interface as Parser#parse().

Given below is an example usage scenario for the command.

**Step 1:** The user launches the application.

**Step 2:** The user executes the `apptcl INDEX ` command in the CLI.

**Step 3:** The patient's appointment(if any) will be cleared. 

<div style="page-break-after: always;"></div>

Below is an activity diagram illustrating an example process of how the `Appointment` commands can be utilised.

![AppointmentActivityDiagram](images/AppointmentActivityDiagram.png)

[<*Back to ToC*>](#table-of-contents)

<div style="page-break-after: always;"></div>

### [Proposed] Upcoming appointment tracker feature

The proposed upcoming appointment feature will be a display to show upcoming appointments for the user upon application start.

#### Proposed Implementation
The proposed implementation is facilitated by `AppointmentWindow`. It extends `UiPart<Stage>` with a new window.

Given below is an example usage scenario for the command.

**Step 1:** The user launches the application.

**Step 2:** A additional window appears, showing the current upcoming appointments.

[<*Back to ToC*>](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

[<*Back to ToC*>](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Appendix: Requirements

### Product scope

**Target user profile**:

* private clinic practitioners who do not have access to general health service management applications like Healthhub
* private clinic practitioners of clinics who still utilise physical paper records to store patient information
* is reasonably comfortable typing 
* has a need to manage a significant number of records
* has a need to find a particular record quickly

**Value proposition**: OmniHealth is a patient database management tool that centralises and digitalises medical record 
storage. This makes finding specific details of a patient as well as inputting new details easy and fast.

[<*Back to ToC*>](#table-of-contents)

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                | I want to …​                                                  | So that I can…​                                                                           |
|----------|------------------------|---------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| `* * *`  | user                   | view the entire list of patients                              | see all my patients I have added.                                                         |
| `* * *`  | user                   | add a new patient and their details                           | save them for later viewing                                                               |
| `* * *`  | user                   | delete an existing patient and their details                  | remove an inactive patient                                                                |
| `* * *`  | user                   | edit a new patient and their details                          | change the personal particulars of that patient                                           |
| `* *`    | user                   | find an existing patient's details                            | know more about the patient                                                               |
| `* *`    | user                   | clear the patient list                                        | save time by not deleting one by one                                                      |
| `* * *`  | user                   | view a specific patient’s records                             | remember past visits recorded                                                             |
| `* * *`  | user                   | add a patient's record                                        | store details of the patient's visit for the future                                       |
| `* * *`  | user                   | edit a patient's record                                       | change details of a record in the future                                                  |
| `* * *`  | user                   | delete a patient's records                                    | remove a particular patient's medical records upon request. (E.g. due to privacy reasons) |
| `* *`    | user                   | find a patient's record                                       | avoid manually scrolling through multiple records to get to the record that I want        |
| `* *`    | user                   | clear all of a patient's records                              | save time by not deleting one by one                                                      |
| `* *`    | user                   | clear the search parameters                                   | view the whole list of patients or records                                                |
| `* * *`  | new user               | view the user guide easily                                    | learn more about the product usage                                                        |
| `* *`    | schedule-oriented user | add appointments scheduled for a patient                      | keep track of my appointments                                                             |
| `* *`    | schedule-oriented user | clear an appointment of an existing patient                   | stop seeing reminders of a past appointment                                               |
| `*`      | schedule-oriented user | see upcoming appointments as a popup upon starting Omnihealth | better plan my schedule for the day                                                       |
| `*`      | schedule-oriented user | receive notifications about upcoming appointments             | be reminded of my schedule during a busy day                                              |
| `* * *`  | new user               | view the user guide easily                                    | learn more about the product usage                                                        |
| `* * *`  | user                   | exit the program                                              |                                                                                           |

[<*Back to ToC*>](#table-of-contents)

<div style="page-break-after: always;"></div>

### Use cases

(For all use cases below, the **System** is the `OmniHealth` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a patient** 

**Precondition:** Patient List View is displayed <br>
**MSS**

1. User requests to add a patient with given input fields.
2. OmniHealth adds patients with given fields into the list of patients.

    Use case ends.

**Extensions**

* 1a. The given input fields are invalid.

    * 1a1. OmniHealth shows an error message.

      Use case ends.

**Use case: UC02 - Add a record**

**Precondition:** Patient List View is displayed <br>
**MSS**

1. User <u>requests to display record list of specific patient (UC06)</u>
2. User requests to add record with given fields into to the patient.
3. OmniHealth adds a record with given fields to the record list of the patient.

    Use case ends.

**Extensions**

* 2a. The given input fields are invalid.

    * 2a1. OmniHealth shows an error message.

      Use case resumes at step 2.

**Use case: UC03 - Delete a patient**

**Precondition: Patient List View is displayed** <br>
**MSS**

1. User requests to delete a specific patient in the list
2. OmniHealth deletes the person

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. OmniHealth shows an error message.

        Use case ends.

* 1b. The given index is invalid.

    * 1b1. OmniHealth shows an error message.

      Use case ends.


**Use case: UC04 - Delete a record**

**Precondition:** Patient List View is displayed <br>
**MSS**

1. User <u>requests to display record list of specific patient (UC06).</u>
2. User requests to delete a specific record in the list.
3. OmniHealth deletes the record.

    Use case ends.

**Extensions**

* 2a. The record list is empty.

    * 2a1. OmniHealth shows an error message.

        Use case ends.

* 2b. The given index is invalid.

    * 2b1. OmniHealth shows an error message.

      Use case ends.

**Use case: UC05 - List all patients**

**Precondition:** Record List View is displayed <br>
**MSS**

1. User requests to list all patients.
2. OmniHealth shows a list of all patients.

    Use case ends.

**Use case: UC06 - List all records for a specified patient**

**Precondition:** Patient List View is displayed <br>
**MSS**

1. User requests to display all records for the specified patient.
2. OmniHealth shows a list of all records for the specified patient.

   Use case ends.

**Extensions**

* 1a. The given index is invalid.

    * 1a1. OmniHealth shows an error message.
    
        Use case ends.

**Use case: UC07 - Clear patient list**

**Precondition: Patient List View is displayed** <br>
**MSS**

1. User requests to clear all patients from list
2. OmniHealth deletes patient list

   Use case ends

**Extensions**

* 1a. Patient list is empty

    Use case ends

**Use case: UC08 - Clear patient records**

**Precondition: Patient List View is displayed** <br>
**MSS**

1. User <u>requests to display record list of specific patient (UC06)</u>
2. User requests to clear all records from a patient
3. OmniHealth deletes all patient records

   Use case ends

**Extensions**
* 1a. Patient does not exist
    * 1a1. OmniHealth displays error message

  Use case ends

* 2a. Patient record list is empty

  Use case ends

**Use case: UC09 - Find patient**

**Precondition: Patient List View is displayed** <br>
**MSS**

1. User requests to find a patient by name.
2. OmniHealth shows a list of all patients matching the input by the user.

**Use case: UC10 - Find patient records**

**Precondition: Patient List View is displayed** <br>
**MSS**

1. User <u>requests to display record list of specific patient (UC06)</u>
2. User requests to find a record that matches the input search parameters.
3. OmniHealth shows all the relevant records of the specified patient.

**Extensions**
* 1a. Patient does not exist
    * 1a1. OmniHealth displays error message

  Use case ends

* 2a. Search parameters are in an invalid format
    * 2a1. OmniHealth displays an error message

  Use case ends

**Use case: UC11 - Add patient appointment**

**Precondition: Patient List View is displayed** <br>
**MSS**
1. User requests to add an appointment with a specified date.
2. An appointment is created for the patient.

**Extensions**
* 1a. Patient list is empty
    * 1a1. OmniHealth displays an error message
  
    Use case ends

* 1b. Patient index does not exist
    * 1b1. OmniHealth displays an error message

    Use case ends

* 1c. Date is specified in wrong formatting
    * 1c1. OmniHealth displays an error message
    
    Use case ends

* 1d. Appointment already exists for the patient
    * 1d1. Old appointment is overriden by the new appointment

    Use case ends

**Use Case: UC12 - Clear patient appointment**

**Precondition: Patient List View is displayed** <br>
**MSS**

1. User requests to remove the existing appointment of the patient.
2. The specified appointment is removed.

**Extensions**
* 1a. Patient list is empty
    * 1a1. OmniHealth displays an error message

    Use case ends

* 1b. Patient index does not exist
    * 1b1. OmniHealth displays an error message.
    
    Use Case ends

**Use Case: UC13 - Edit a patient's record**

**Precondition: Patient List View is displayed** <br>
**MSS**

1. User <u>requests to display the record list of specific patient (UC06).</u>
2. User requests to edit a record
3. OmniHealth replaces the record in the record list with a new edited one

**Extensions**
* 2a. The record list is empty.

    * 2a1. OmniHealth shows an error message.

      Use case ends.

* 2b. The given index is invalid.

    * 2b1. OmniHealth shows an error message.

      Use case ends.

**Use Case: UC14 - Edit a patient's details**

**Precondition: Patient List View is displayed** <br>
**MSS**

1. User requests to edit a patient's details
2. OmniHealth replaces the patient in the patient database with a new edited one

**Extensions**
* 1a. The patient list is empty.

    * 1a1. OmniHealth shows an error message.

      Use case ends.

* 1b. The given index is invalid.

    * 1b1. OmniHealth shows an error message.

      Use case ends.

[<*Back to ToC*>](#table-of-contents)

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

1. Should work as long as Java `11` or above is installed.
2. Should store personal particulars of patients in a way that it is not at risk of being leaked.
3. Should be able to handle large amounts of data without feeling sluggish.
   * Patient list should be able to store up to 500 patients (Target users are small clinics)
   * Likewise, Patient Record List should be able to store up to 500 records
4. A typist with average typing speed should be able to perform their desired task faster than 
when using a mouse. (Designed with CLI in mind)
5. Should be simple to use for a user that is not proficient with technology. (Target users are used to pen and paper records)
   * Should have simple and intuitive commands that can be learned within a few minutes
6. Should be more efficient than using paper records. 
   * Finding a patients information using Omnihealth is faster than manaully searching for it.

[<*Back to ToC*>](#table-of-contents)

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **MSS**: Main success scenerio
* **GUI**: Graphical user interface
* **CLI**: Command line interface
* **Address book**: The component of Omnihealth that stores all patients and information related to a patient
* **Patient**: A patient of the clinic using Omnihealth
* **Person**: A model representing a patient
* **Doctor**: A doctor working at the clinic using Omnihealth
* **(Patient) Record**: Digital copy of a patient's record stored by Omnihealth
* **Paper Records**: Physical copy of a patient's record
* **Patient List**: A list of patients stored by Omnihealth
* **Patient List View**: A viewing mode that shows the patient list
* **Record List View**: A viewing mode that shows the record list
* **(Patient) Record List**: A list of a patient's records stored by Omnihealth
* **Appointment**: Date of the next visit of the patient

[<*Back to ToC*>](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Appendix: Instructions for manual testing

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more exploratory testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

[<*Back to ToC*>](#table-of-contents)

### Displaying a patient's record list
1. View a particular patient's record list while all persons are being shown

   1. Prerequisites: Patient List is currently displayed. At least 1 patient in the list. If record list is displayed instead, use the `list` command.
   
   2. Test case: `rlist 1` <br>
      Expected: Record list of the patient at index 1 will be displayed instead of the patient list.

   3. Test case: `rlist 0`<br>
      Expected: Patient list remains displayed. Error details shown in the status message. 

   4. Other incorrect delete commands to try: `rlist`, `rlist x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

[<*Back to ToC*>](#table-of-contents)

### Adding a record
1. Add a record to a particular patient's record list

    1. Prerequisites: Record List is currently displayed. If the patient list is displayed instead, use the `rlist` command.

    2. Test case: `radd d/31-10-2022 1430 r/suffers from common cold m/Paracetamol 500mg`, 
       `radd d/31-10-2022 1430 r/suffers from common cold`<br>   
       Expected: Details of the record added will be shown in the status message. Record list will be updated to 
       include the added record

    3. Test case: `radd` <br>
       Expected: No record is added. Error details shown in the status message. Record list remains the same.

    4. Other incorrect delete commands to try: `radd d/x r/suffers from common cold`, `...` (where x is in an invalid datetime format)<br>
       Expected: Similar to previous.

[<*Back to ToC*>](#table-of-contents)

### Editing a record
1. Edit an existing record in a particular patient's record list

    1. Prerequisites: Record List is currently displayed. At least 1 record in the list. If the patient list is displayed instead, use the `rlist` command. 

    2. Test case: `redit 1`, `redit 1 d/31-10-2022 1430 r/suffers from common cold m/Paracetamol 500mg`<br>   
       Expected: Details of the selected record in the record list will be edited and displayed. The updated details of the selected 
       record is then shown in the status message. 

    3. Test case: `redit` <br>
       Expected: No record is edited. Error details shown in the status message. Record list remains the same.

    4. Other incorrect delete commands to try: `redit d/x r/suffers from common cold`, `...` (where x is in an invalid datetime format)<br>
       Expected: Similar to previous.

[<*Back to ToC*>](#table-of-contents)

### Find a record in a patient's record list
1. Find an existing record in a particular patient's record list

    1. Prerequisites: Record List is currently displayed. If the patient list is displayed instead, use the `rlist` command. 

    2. Test case: `rfind d/10-2022`, `rfind r/suffers from common cold` `rfind m/Paracetamol 500mg`<br>   
       Expected: The record list will display all records matching the search parameters. The number of matching
       records will be shown in the status message.

    3. Test case: `rfind` <br>
       Expected: No record is edited. Error details shown in the status message. Record list remains the same.

    4. Other incorrect delete commands to try: `rfind d/x r/suffers from common cold`, `...` (where x is in an invalid datetime format)<br>
       Expected: Similar to previous.

[<*Back to ToC*>](#table-of-contents)

### Undo a find command
1. Undo a find command (either `find` or `rfind`)
   
    1. Test case: `showall`, `showall 123`, `showall abc`<br>
       Expected: If a record list is displayed, a record list containing all records is shown. 
       If a patient list is displayed, a patient list containing all patients is shown instead. 
       A Status message indicating a successful command execution will be displayed.

[<*Back to ToC*>](#table-of-contents)

### Deleting a record

1. Delete a record from a particular patient's record list

    1. Prerequisites: Record List is currently displayed. At least 1 record in the list. If the patient list is displayed instead, use the `rlist` command. 

    2. Test case: `rdelete 1`<br>   
       Expected: Details of the deleted record will be shown in the status message. Record list will be updated to
       exclude the deleted record. 
   
    3. Test case: `rdelete 0`<br>
       Expected: No record is deleted. Error details shown in the status message. Record list remains the same.

    4. Other incorrect delete commands to try: `rdelete`, `rdelete x` `rdelete 1 r/record`, `...` (where x is in an out of bound/invalid index)<br>
       Expected: Similar to previous.

[<*Back to ToC*>](#table-of-contents)

### Clearing all records from a patient's record list

1. Clear all records from a particular patient's record list

    1. Prerequisites: Record List is currently displayed. If the patient list is displayed instead, use the `rlist` command.

    2. Test case: `rclear`, `rclear 1`<br>   
       Expected: A Status message indicating a successful command execution will be displayed. 
       The record list is now empty and there are no records displayed.

[<*Back to ToC*>](#table-of-contents)

### Adding an appointment to a patient

1. Add an upcoming appointment to a particular patient

   1. Prerequisites: Patient List is currently displayed. At least 1 patient in the list. If the record list is displayed instead, use the `list` command. 

   2. Test case: `appt 1 d/01-01-2023 1200`<br>   
      Expected: Details of the appointment set will be shown in the status message. The specified patient in the patient list will be updated to
      include the upcoming appointment.

   3. Test case: `appt 0` <br>
      Expected: No appointment is added. Error details shown in the status message. Patient list remains the same.

   4. Other incorrect delete commands to try: `appt`, `appt 1 d/x`, `...` (where x is in an invalid datetime format or before the current date)<br>
      Expected: Similar to previous.

[<*Back to ToC*>](#table-of-contents)

### Removing an appointment from a patient

1. Remove an appointment from a particular patient

    1. Prerequisites: Patient List is currently displayed. At least 1 patient in the list. If the record list is displayed instead, use the `list` command.

    2. Test case: `apptcl 1` <br>   
       Expected: A Status message indicating a successful command execution will be displayed. The specified patient in the patient list will be updated to
       not show any upcoming appointment.

    3. Test case: `apptcl 0` <br>
       Expected: No appointment is removed. Error details shown in the status message. Patient list remains the same.

    4. Other incorrect delete commands to try: `apptcl`, `apptcl 1 hello`, `...` <br>
       Expected: Similar to previous.
   
[<*Back to ToC*>](#table-of-contents)

### Saving data

1. Dealing with corrupted data files

    1. Prerequisites: The file addressbook.json exists.
    2. Test case: Edit the data in the file manually from outside Omnihealth.<br>
       Expected: Omnihealth starts up as usual. If the data in the file is readable, Omnihealth displays the data.
       Else, Omnihealth starts up with no data. 

1. Dealing with missing data files

   1. Test case: Delete the adressbook.json file if it exists. <br>
       Expected: Omnihealth starts up as usual but displays a blank list since there is no data found.

[<*Back to ToC*>](#table-of-contents)

## Appendix: Effort

- Difficulty Level:
  - Overall, we thought the project's difficulty level was Moderate.
  - We implemented a wide range of features with differing degrees of difficulty and complexity. 
  - We needed a thorough understanding of the system architecture, as this was necessary in order to extend AB-3 in a way that integrates well with the existing design and features of AB-3.

- Challenges Faced:
  - Because each member worked on many issues and features, the list below is not exhaustive.
    - Having to integrate the list of medical records of a specific patient into the UI, so that the application's main window can switch between a patient list and a medical record list.
    - The records subsystem was constructed on top of the existing AB-3 architecture, which required extensive modification and extensions to obtain the desired final result.
    - Due to the large number of possible test cases, the Record and Appointment commands were difficult to test, necessitating further testing and rectification.
    - The inconsistency of JavaFX UI elements between platforms necessitates additional manual testing.

- Effort Required:
  - The current code base has to be heavily modified for our application. 
  - A large number of test cases were written in order to cover as many possibilities as practical. 
  - We held comprehensive weekly meetings early in the week to ensure that every developer is well-informed about what is planned for the rest of the week.

- Achievements of the Project:
  - Our team created a software product that we believe meets our intended goals. 
  - All of the Must-have User Stories were met by our software application. 
  - Our team has met all set milestones and deadlines.

[<*Back to ToC*>](#table-of-contents)
