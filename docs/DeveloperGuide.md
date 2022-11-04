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
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `EventListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` objects and `Event` objects residing in the `Model`.

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

* stores the address book data i.e., all `Person` and `Events` objects (which are contained in the `UniquePersonList` and `UniqueEventList` objects).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the currently 'selected' `Event` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Event>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
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

### Sort Persons

The sort persons feature allows the user to sort persons by their name, date of birth or gender.

The field to sort persons by is encapsulated within the `SortField` class. `SortField` stores the sort field enumeration (`SortFieldType`) as well as the `Comparator` object that defines the sorting logic.

The following class diagram shows how the `SortField` class integrates with the other components:

![SortFieldClassDiagram](images/SortFieldClassDiagram.png)
<br><br>

Sorting is performed as part of `listPersonsCommand#execute()`. The sorting operation is exposed in the `Model` interface as `Model#sortPersons()` which calls `AddressBook#sortPersons()` which in turn calls `UniquePersonList#sort()` to sort the underlying `ObservableList<Person>`.

The following sequence diagram illustrates the relevant sorting method calls for the command `listPersons s/n` with a `SortField` object `s`:

![SortPersonsSequenceDiagram](images/SortPersonsSequenceDiagram.png)



<div markdown="span" class="alert alert-info">:information_source: **Note:** Sorting is performed directly on the underlying `UniquePersonList` object, which means the sorted result is **permanent**. For example, if `listPersons s/n` and `listPersons` are executed back-to-back, the result of the second `listPersons` command will display the sorted results from the first `listPersons s/n` command because the sorted result is permanent.
</div>


#### Design Considerations:

**Aspect: How sorting is performed:**

Ideally, sorting should be a "view" level operation that doesn't change the underlying persons list. However, the way the UI is designed makes it difficult to implement sorting as such. The issue is that the UI is **hardcoded** to only display persons from the `UniquePersonList` object. As such, any changes to the persons list must be made directly to the `UniquePersonList` object. i.e. A modified copy of the persons list cannot be passed to the UI during runtime since the UI is hardcoded to show only the `UniquePersonList` object.

Therefore, given the constraints of the UI, sorting is implemented as a **permanent operation**.


* **Alternative 1 (current choice):** Sort the underlying persons list directly.
  * Pros: User can directly use the index numbers of the sorted list to interact with the persons using other commands such as `editPerson` or `deletePerson`.
  * Cons: Sorted result is permanent, instead of being a "view" level operation.

* **Alternative 2:** Sort the persons list at the UI level.
   * Pros: Sorted result is not permanent. So sorting won't interfere with the underlying persons list.
   * Cons: Requires redesigning a significant part of the `UI`, `Logic` and `Model` classes.

### Adding Events

The Add Event feature that is accessed through the `addEvent` command allows users to add new marketing campaigns of
the `event` class to the application.

The `event` added by the user will have 4 compulsory fields:
- Title of the `event`
- Date of the `event`
- Time of the `event`
- Purpose of the `event`

The `addEvent` operation is facilitated by `AddEventCommand` which extends from `Command`. If the users' input matches
the `COMMAND_WORD` of `AddEventCommand` in `AddressBookParser#parseCommand()`, `AddEventCommandParser#parse()` will
process the additional user inputs which constitute the 4 compulsory fields of the `event` class and return an
`AddEventCommand`.

Executing this Command object through the `AddEventCommand#execute()` triggers the `Model` interface's
`Model#addEvent()`. This operation subsequently calls upon the `AddressBook#addEvent()` operation which in turn calls
upon the `UniqueEventList#add()` operation and the `event` will be stored in memory.

The addEvent operation will also trigger the `StorageManager#saveAddressBook()` operation which will save the event to
a .JSON format together with all other `Person`(s) and `Event`(s) in memory.

The following sequence diagram will illustrate how the `addEvent` operation works:

![AddEventSequenceDiagram](images/AddEventSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** `cmd` in the diagram represents the add
event command text entered by user. event represents the event class created and stored within AddEventCommand.
Additionally, saving of the updated events list has been excluded from this diagram for simplicity.
</div>


### Deleting Events

The Delete Event feature that is accessed through the `deleteEvent` command allows users to delete marketing campaigns of
the `event` class in the application.

The `deleteEvent` operation is facilitated by `DeleteEventCommand` which extends from `Command`. If the users' input matches
the `COMMAND_WORD` of `DeleteEventCommand` in `AddressBookParser#parseCommand()`, `DeleteEventCommandParser#parse()` will
process the additional user input which is the current index of the marketing event on the User Interface.

Executing this Command object through the `DeleteEventCommand#execute()` triggers the `Model` interface's
`Model#deleteEvent()`. This operation subsequently calls upon the `AddressBook#deleteEvent()` operation which in turn calls
upon the `UniqueEventList#remove()` operation and the `event` will be removed from memory.

The deleteEvent operation will also trigger the `StorageManager#saveAddressBook()` operation which will save the current
list of events which excludes the deleted event to a .JSON format together with all other `Person`(s) in memory.

The following sequence diagram will illustrate how the `deleteEvent` operation works:

![DeleteEventSequenceDiagram](images/DeleteEventSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** `cmd` in the diagram represents the delete
event command text entered by user. event represents the instance of event class created and stored within DeleteEventCommand.
Additionally, saving of the updated events list from has been excluded from this diagram for simplicity.
</div>

### Add Gender

The Add Gender feature allows users to add a gender field (Male / Female) to a person in the contact list. It is performed as a part
 of `addPersonCommand#execute()`.

These operations are exposed in the `Model` interface as the method `Model#addPerson()`, which calls
`AddressBook#addPerson()` which calls `UniquePersonList#add()` to add a new person in the person list
stored in AddressBook.

The following sequence diagram shows the methods calls related to add person operation:

![AddPersonSequenceDiagram](images/AddPersonSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** `cmd` in the diagram represents the add
person command text entered by user. The specific `UniquePersonList` operations are not shown in the diagram
for simplicity.
</div>

The following activity diagram shows what happens when a user executes a new add command:

![AddPersonActivityDiagram](images/AddPersonActivityDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Only the
activities related to gender field are considered and shown in this activity diagram.
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:**Parser exceptions are thrown and caught if
gender field is not provided in the command, or the gender is not of valid format; Duplicated person exception is
thrown if the person to add already exists in the contact list. Error message is displayed on the GUI subsequently.
</div>

#### Design considerations:

**Aspect: Whether gender field should be optional for a person:**

* **Alternative 1 (current choice):** Compulsory gender field:
    * Pros: It is a more logical implementation because gender is a common attribute for all persons,
  similar to name, address, etc, which are also compulsory field for persons in contact list.
    * Cons: It is less flexible, since only female and male genders are accepted.

* **Alternative 2:** Optional gender field:
    * Pros: It is a more flexible implementation, since user has the choice to set gender to male or female,
  as well as hide gender.
    * Cons: It is less logical since gender is usually a required binary field in most applications.

### Edit Gender

The Edit Gender feature allows users to edit a gender field (Male / Female) of a person in the contact list.
It is performed as a part of `editPersonCommand#execute()`.

These operations are exposed in the `Model` interface as the method `Model#setPerson()`, which calls
`AddressBook#setPerson()` which calls `UniquePersonList#setPerson()` to replace an existing person with a new person
object with edited fields in the person list stored in AddressBook.

The following sequence diagram shows the methods calls related to edit person operation:

![EditPersonSequenceDiagram](images/EditPersonSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** `cmd` in the diagram represents the edit
person command text entered by user; the `setPerson(P1, P2)` method replaces person P1 with person P2 in the person list
in the model. The specific `UniquePersonList` operations are not shown in the diagram for simplicity.
</div>

The following activity diagram shows what happens when a user executes a new edit command:

![EditPersonActivityDiagram](images/EditPersonActivityDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Only the
activities related to gender field are considered and shown in this activity diagram. All fields are considered optional
in edit person command, therefore, it is not compulsory that gender field must be provided.
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:**Parser exceptions are thrown and caught if
the gender is not of valid format; Invalid person exception is thrown if the person to edit doesn't exist in the
contact list. Error message is displayed on the GUI subsequently.
</div>

### Add Date Of Birth

The Add Date Of Birth feature allows users to add a date of birth field (format: dd/mm/yyyy) to a person in the addressbook. It is performed as a part of `addPersonCommand#execute()`.

These operations are exposed in the `Model` interface as the method `Model#addPerson()`, which calls
`AddressBook#addPerson()` which calls `UniquePersonList#add()` to add a new person in the person list
stored in AddressBook.

The following sequence diagram shows the methods calls related to add person operation:

![AddPersonSequenceDiagram](images/AddPersonSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** `cmd` in the diagram represents the add
person command text entered by user. The specific `UniquePersonList` operations are not shown in the diagram
for simplicity.
</div>

The following activity diagram shows what happens when a user executes a new add person command:

![AddPersonActivityDiagram](images/AddPersonActivityDiagram_DOB.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Only the
activities related to date of birth field are considered and shown in this activity diagram.
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:**Parser exceptions are thrown and caught if
date of birth field is not provided in the command, or the date of birth is not of valid format; Duplicate person exception is
thrown if the person to add already exists in the addressbook. Error message is displayed on the GUI subsequently.
</div>

#### Design considerations:

**Aspect: Whether date of birth field should be optional for a person:**

* **Alternative 1 (current choice):** Compulsory date of birth field:
    * Pros: It is a more logical implementation because date of birth is a common attribute for all persons,
  similar to name, address, etc, which are also compulsory fields for persons in the addressbook.
    * Cons: It is less flexible in cases where the user is missing the date of birth field for a contact,
    but wants to add the contact anyways, as the user is not able to leave the date of birth field blank.

* **Alternative 2:** Optional date of birth field:
    * Pros: It is a more flexible implementation, since the user has the choice to set a date of birth,
    or leave it empty.
    * Cons: It is less logical since date of birth is a common attribute for all persons in the addressbook.

### Edit Date of Birth

The Edit Date of Birth feature allows users to edit a date of birth field (format: dd/mm/yyyy) of a person in the addressbook.
It is performed as a part of `editPersonCommand#execute()`.

These operations are exposed in the `Model` interface as the method `Model#setPerson()`, which calls
`AddressBook#setPerson()` which calls `UniquePersonList#setPerson()` to replace an existing person with a new person
object with edited fields in the person list stored in AddressBook.

The following sequence diagram shows the methods calls related to edit person operation:

![EditPersonSequenceDiagram](images/EditPersonSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** `cmd` in the diagram represents the edit
person command text entered by user; the `setPerson(P1, P2)` method replaces person P1 with person P2 in the person list
in the model. The specific `UniquePersonList` operations are not shown in the diagram for simplicity.
</div>

The following activity diagram shows what happens when a user executes a new edit command:

![EditPersonActivityDiagram](images/EditPersonActivityDiagram_DOB.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Only the
activities related to date of birth field are considered and shown in this activity diagram. The edit person command only requires at least one of the optional fields to be given, all of the fields are optional, therefore, it is not compulsory that a date of birth field must be provided.
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:**Parser exceptions are thrown and caught if
the date of birth is not of valid format; Invalid person exception is thrown if the person to edit doesn't exist in the
addressbook. Error message is displayed on the GUI subsequently.
</div>

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


### Listing Events

The List Events feature allows users to enter `listEvents` and update the UI display for events, showing a list of all
events sorted in their current order inside `ModelManager`. In the future, the command will take in a parameter,
which specifies the field that the events list can be permanently sorted by.

The List Events feature is facilitated by `ListEventsCommand` which extends from `Command`. Additionally, it implements
the following operation:

* `ModelManager#updateFilteredEventList()`  — Updates the predicate inside `ModelManager`'s filtered event list
to modify and sort which and how events are shown.

This operation is exposed in the `Model` interface as the method `Model#updateFilteredEventList()`.

The following sequence diagram shows how the `listEvents` operation works.

![ListEventsSequenceDiagram](images/ListEventsSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Only the activities related to events are
considered and shown in this activity diagram.
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ListEventsCommand` should
end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of the diagram.
</div>

#### Design Considerations:

**Aspect: If `listEvents` should sort events permanently:**
* **Alternative 1 (current choice):** Sort all events permanently.
  * Pros: Easy to implement.
  * Cons: Whenever events are added, they are always added as the last event in the event list. Once sorted, the order
  cannot be returned to the order which events were added.
* **Alternative 2:** Sort all events temporarily.
  * Pros: When users sort their events, the ordering of the events in `ModelManager` stays constant.
  * Cons: Hard to implement as a new form of storage or memory has to be created to maintain the relative ordering of
    events.



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

**Target user profile**: Duke The Market aims to help marketers of department stores better manage customer profiles and keep track of target customers during market plan rollouts.

* needs to manage a significant number of customer contacts
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* needs to oversee many marketing events
* needs to track customer activity in own department store
* needs to generate statistics based of customer data

**Value proposition**: A one-stop marketing tool that allows department stores to manage and organize their customer contacts for usage in the company’s various marketing plans


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                              | I want to …​                                                                                                  | So that I can…​                                                                              |
|----------|------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|
| `* * *`  | new user                                             | see usage instructions                                                                                        | refer to instructions when I forget how to use the App                                       |
| `* * *`  | frequent user                                        | update new data immediately                                                                                   | I can exit and enter the product freely                                                      |
| `* * *`  | frequent user                                        | enter and exit the product in a short amount of time                                                          | use the product on the fly                                                                   |
| `* * *`  | marketer                                             | add a new customer contact                                                                                    | keep track of all customers and enlarge the customer base over time                          |
| `* * *`  | marketer                                             | delete a customer contact                                                                                     | remove entries that I no longer need                                                         |
| `* * *`  | marketer                                             | find a customer contact by name                                                                               | locate details of customers without having to go through the entire list                     |
| `* * *`  | pre-event marketer                                   | view all the upcoming marketing events in the system sorted in the order that they were added into the system | have a high level overview of all marketing events and be mentally prepared for each of them |
| `* * *`  | pre-event marketer who handles many marketing events | add marketing events                                                                                          | keep the system up to date with changes that happen to the marketing plans                   |
| `* * *`  | pre-event marketer who handles many marketing events | delete marketing events                                                                                       | remove marketing events that I no longer need                                                |
| `* *`    | marketer with many customer contacts                 | sort all customer contacts by name, date of birth or gender                                                    | locate people more easily and obtain contact lists of different demographic groups           |

*{More to be added}*

### Use cases

(For all use cases below, **UC** is the `Use Case`, the **System** is `Duke The Market` and the **Actor** is the `user`, unless specified otherwise)

**UC01: Delete a customer**

**MSS**

1. User requests to list customers
2. Duke The Market shows a list of customers
3. User requests to delete a specific customer in the list
4. Duke The Market deletes the customer

   Use case ends.

**Extensions**

* 2a.  The list is empty.

  Use case ends.

* 3a.  The given index is invalid.

    * 3a1. Duke The Market shows an error message

      Use case resumes at step 2.


**UC02: Add a customer**

**MSS**

1.  User requests to add a customer of specific name, phone number, email, address, gender and date of birth
2.  Duke The Market adds the customer

    Use case ends.

**Extensions**

* 1a. User enters data in an invalid format.

    * 1a1. Duke The Market requests for the correct data
    * 1a2. User enters new data

  Steps 1a1-1a2 are repeated until the data entered is correct

  Use case resumes at step 2.

* 1b. User enters data for an individual who already exists in the database.
    * 1b1. Duke The Market shows an error message that the user is a duplicate customer
    * 1b2. User enters new data

  Steps 1b1-1b2 are repeated until the data entered is correct.

  Use case resumes at step 2.


**UC03: View the overall performance of a past event**

**MSS**
1.  User requests to list events
2.  Duke The Market shows a list of events
3.  User requests to generate statistics about an ongoing/past event in the list
4.  Duke The Market shows the visual representation of the statistics about that event

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.
    * 3a1. Duke The Market shows an error message.

      Use case resumes at step 3.


**UC04: Export email addresses of customers to a file**

**MSS**
1.  User requests to export to a specified file the email address of customers who attended a specific event.
2.  Duke The Market exports the customers’ email addresses to the specified file

    Use case ends.


**UC05: Retrieve customers who may be interested in an upcoming event**

**MSS**
1. User requests to list events
2. Duke The Market shows a list of events
3. User requests to show customers who may be interested in an upcoming event in the list
4. Duke The Market shows the customers who may be interested in that upcoming event
5. User requests to <ins>export the customers' email addresses to a specified file (UC04)</ins>

   Use case ends.




*{More to be added}*

### Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. App should not require installation to work (i.e. it is portable)
3. App should work well for standard screen resolutions 1920x1080 and higher, as well as for screen scales 100% and 125%.
4. App should be usable (i.e. all functions can be used even if the user experience is not optimal) for resolutions 1280x720 and higher, as well as for screen scales 150%.
5. Should be able to hold up to 1000 customers and 200 events without a noticeable sluggishness in performance for typical usage.
6. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
7. The system should respond within 5 seconds.
8. Data should be stored locally in a human editable text file.
9. Data should not be stored using an external database management system (DBMS)
10. File size of the data should not exceed 1GB (should be stored in a space saving manner)
11. App should be an executable (Double-clicked or can be run using the `java -jar` command).


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Interested customers**: Customers who have attended similar events in the past.

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
