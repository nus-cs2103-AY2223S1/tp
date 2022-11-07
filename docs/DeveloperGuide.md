---
layout: page
title: Developer Guide
---

## Table of Contents
1. [Overview](#overview)
2. [Acknowledgements](#acknowledgements)
3. [Setting up, getting started](#setting-up-getting-started)
4. [Design](#design)
   * [Architecture](#architecture)
   * [UI Component](#ui-component)
   * [Logic Component](#logic-component)
   * [Model component](#model-component)
   * [Storage component](#storage-component)
   * [Common classes](#common-classes)
5. [Implementation](#implementation)
   * [Edit Feature](#edit-feature)
   * [Sort Feature](#sort-feature)
   * [Grade Feature](#grade-feature)
   * [(Proposed) Undo/redo Feature](#proposed-undoredo-feature)
6. [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
7. [Appendix: Requirements](#appendix-requirements)
   * [Product Scope](#product-scope)
   * [User Stories](#user-stories)
   * [Use Cases](#use-cases)
   * [Non-Functional Requirements](#non-functional-requirements)
   * [Glossary](#glossary)
8. [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing) 

---

## **Overview**

Watson is a powerful **desktop database application** that reduces the administrative workload of **primary/secondary school teachers** greatly. 

It provides a **centralized** platform for teachers to handle and retrieve student data **conveniently**, namely: 
* student particulars 
* student grades
* student attendance

It is optimised for use via a **Command Line Interface (CLI)** while still having the benefits of a
Graphical User Interface (GUI).

This Developer Guide provides general information for developers interested in understanding
the design implementation of Watson for further development.

---

## **Acknowledgements**

| Code Author | Code Used    | Reason               |
|-------------|--------------|----------------------|
| e1010101    | [DateUtil](https://github.com/e1010101/ip/blob/master/src/main/java/util/DateUtils.java) | Ease of date parsing |

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [Main](https://github.com/AY2223S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/Main.java)
and [MainApp](https://github.com/AY2223S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,

- At app launch: Initializes the components in the correct sequence, and connects them up with each other.
- At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

- [**`UI`**](#ui-component): The UI of the App.
- [**`Logic`**](#logic-component): The command executor.
- [**`Model`**](#model-component): Holds the data of the App in memory.
- [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

- defines its _API_ in an `interface` with the same name as the Component.
- implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [UiManager.java](https://github.com/AY2223S1-CS2103T-T08-1/tp/blob/master/src/main/java/seedu/address/ui/UiManager.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

- executes user commands using the `Logic` component.
- listens for changes to `Model` data so that the UI can be updated with the modified data.
- keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
- depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

We added a new UI component in our implementation: the `LoginWindow`.
The `LoginWindow` is a separate window that is displayed when the user first starts the application.
The `LoginWindow` is responsible for authenticating the user and retrieving the user's data from the `Storage` component.
The `LoginWindow` will then pass the user's data to the `MainWindow` so that the `MainWindow` can display the user's data.

`LoginWindow` inherits from the abstract `UiPart` class, just like the `MainWindow`.
As of 20/10/2022, it consists of 2 `TextBox` FXML components and a "submit" `Button` FXML component.

We plan to add a new UI component in our implementation: the `ImportCSVButton`.
The `ImportCSVButton` is a button that is displayed on the `MainWindow` after login
The `ImportCSVButton` is allows users to import student's data from a CSV file into the `storage` component

`ImportCSVButton` inherits from the abstract `UiPart` class, just like the `MainWindow`.
As of 21/10/2022, it has yet to be implemented
### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

- When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
- All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The `Model` component,

- stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
- stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
- stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
- does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

- can save both address book data and user preference data in json format, and read them back into corresponding objects.
- inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
- depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

---

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Edit feature

#### Current Implementation

The edit command consists of these following classes:

- `EditCommand` which extends `Command`
- `EditCommandParser` which extends `Parser<EditCommand>`

As with all other commands, the edit command has a `Parser` subclass that goes through the `AddressBookParser` and a `Command` subclass that returns an appropriate new `CommandResult` Object.
It allows the editing of a student's name, phone, email, address, class, remarks and tags.

The command will be used as such:
- `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/CLASS] [rem/REMARK] [t/TAG]`
- Words in `UPPER_CASE` are the inputs to be supplied by the user.
- Words in square brackets are optional, but at least one of them must be present.

### Sort feature

#### current Implementation

The sort command consists of these following classes:

- `SortCommand` which extends `Command`
- `SortCommandParser` which extends `Parser<SortCommand>`

As with all other commands, the sort command has a `Parser` subclass that goes through the `AddressBookParser` and a `Command` subclass that returns an appropriate new `CommandResult` Object. It sorts the list of students by their grades in ascending or descending order as specified by the user.

The command will be used as such:
- sort by grade in ascending order - e.g.`sort asc`
- sort by grade in descending order - e.g. `sort desc`

### Grade feature

#### current Implementation

The Grade command consists of these following classes:

- `GradeCommand` which extends `Command`
- `GradeCommandParser` which extends `Parser<SortCommand>`

As with all other commands, the sort command has a `Parser` subclass that goes through the `AddressBookParser` and a `Command` subclass that returns an appropriate new `CommandResult` Object. It opens a new GUI window to start the grading process

The command will be used as such:
- entering grades for an `Assignment` using the follow command format - grade <subject_assignment_totalScore_weightage_difficulty>
e.g.`grade MATH_CA1_100_0.4_0.1`


### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

- `VersionedAddressBook#commit()` — Saves the current address book state in its history.
- `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
- `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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

- **Alternative 1 (current choice):** Saves the entire address book.

  - Pros: Easy to implement.
  - Cons: May have performance issues in terms of memory usage.

- **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  - Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  - Cons: We must ensure that the implementation of each individual command are correct.

---

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

## **Appendix: Requirements**

### Product scope

**Target user profile**:

- are primary/secondary school teachers
- has a need to manage a significant number of students and their details
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps
- needs to retrieve information of students quickly

**Value proposition**: manage and retrieve information of students faster than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a ... | I want to ...                                           | so that I can ...                                     |
|----------|----------|---------------------------------------------------------|-------------------------------------------------------|
| `* * *`  | teacher  | add students and their particulars to the app           | keep track of the student's details                   |
| `* * *`  | teacher  | edit student's details                                  | update or make changes with new information if needed |
| `* * *`  | teacher  | search for certain students based on name/class/subject | retrieve student's information quickly                |
| `* * *`  | teacher  | view the list of all students and their details         | see all information stored clearly                    |
| `* * *`  | teacher  | grade my students                                       | keep track of their grades for each assessment        |
| `* * *`  | teacher  | mark students attendance                                | keep track of their daily attendance easily           |
| `* *`    | teacher  | allow only specific users into the system               | the information stored in Watson remains confidential |
| `* *`    | teacher  | add remarks to students                                 | remember important details about my students          |
| `* *`    | teacher  | sort my students by grade                               | see student's performance at a glance                 |
| `*`      | teacher  | import information from existing databases              | I can set up Watson quickly                           |

### Use cases

(For all use cases below, the **System** is the `Watson` and the **Actor** is the `user`, unless specified otherwise)

**Use case: View all students**

**Preconditions: User is logged in**

**MSS**

1.  User requests to list all students.
2.  Watson shows a list of all students.

    Use case ends.

**Extensions**

- 2a. The list is empty.

    - 2a1. Watson displays an empty list.

      Use case ends.

**Use case: Add a student**

**Preconditions: User is logged in**

**MSS**

1.  User requests to add a student and specifies details of the student.
2.  Watson adds a student.
3.  Student is added to the displayed list.

    Use case ends.

**Extensions**

- 2a. Missing required details of the student to be added.

    - 2a1. Watson shows an error message.

      Use case resumes at step 1.

**Use case: Edit a student**

**Preconditions: User is logged in**

**MSS**

1.  User requests to edit a student and specifies the index and parameters to edit.
2.  Watson edits a student and overwrites the parameters with the updated information.
3.  Student is updated in the displayed list.

    Use case ends.

**Extensions**

- 2a. Missing required parameters of the student to be edited.

    - 2a1. Watson shows an error message.

      Use case resumes at step 1.

- 2b. The given index is invalid.

    - 2b1. Watson shows an error message.

      Use case resumes at step 1.

**Use case: Delete a student**

**Preconditions: User is logged in**

**MSS**

1.  User requests to list students.
2.  Watson shows a list of students.
3.  User requests to delete a specific student in the list.
4.  Watson deletes the student.

    Use case ends.

**Extensions**

- 2a. The list is empty.

  Use case ends.

- 3a. The given index is invalid.

  - 3a1. Watson shows an error message.

    Use case resumes at step 2.

**Use case: Find a student**

**Preconditions: User is logged in**

**MSS**

1.  User enters a student's name and/or class and/or subject.
2.  Watson shows a list of students with name and/or class and/or subject corresponding to what was entered.
3.  User selects the student that he/she is looking for.
4.  Watson displays the full details of the student.

    Use case ends.

**Extensions**

- 2a. The list is empty.

  Use case ends.

**Use case: Add remarks to a student**

**Preconditions: User is logged in**

**MSS**

1.  User requests to add a remark to a student.
2.  Watson adds a remark to the specified student.
3.  Student is updated in the displayed list.

    Use case ends.

**Extensions**

- 2a. Missing remark.

    - 2a1. Watson shows an error message.

      Use case resumes at step 1.

- 2b. The given index is invalid.

    - 2b1. Watson shows an error message.

      Use case resumes at step 1.

**Use case: Grade students**

**Preconditions: User is logged in**

**MSS**

1. User requests to add grade of an assessment to students.
2. Watson shows window for user to enter grade for the specified assessment.
3. User enters grade for each student.
4. Students' grades are updated in the displayed list.

    Use case ends.

**Extensions**

- 2a. Invalid command given by user.

    - 2a1. Watson shows an error message.

      Use case resumes at step 1.

**Use case: Predicting a student's grade**

**Preconditions: User is logged in**

**MSS**

1. User requests to predict a student's grade.
2. Watson shows prediction of student's grade.

   Use case ends.

**Extensions**

- 2a. Missing/incorrect name/subject/difficulty entered by user.

    - 2a1. Watson shows an error message.

      Use case resumes at step 1.

**Use case: Sorting students by grades**

**Preconditions: User is logged in**

**MSS**

1. User requests to sort students by grade in ascending/descending order.
2. Watson shows list of students sorted by grades in ascending/descending order.

   Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Non-authenticated users should not be able to access the system.
5. The data should be saved everytime a command alters the data. 
6. Should work without an internet connection.
7. The User Guide and Developer Guide should be PDF-friendly.

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X

---

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

### Finding students

1. Finding students that shares the same subject.

    1. Prerequisites: List all students using the `list` command. Multiple students in the list.

    2. Test case: `findSubject math`<br>
       Expected: All students that have the subject `math` in the list will be shown in a new list. Timestamp in the status bar is updated.

    3. Test case: `findSubject engrish`<br>
       Expected: No student will be shown in a new list. Status bar remains the same.

    4. Other incorrect delete commands to try: `findSubject`, `findSubject x`, `...` (where x is a misspelled subject)<br>
       Expected: Similar to previous.
2. Finding students that belongs in the same class.

    1. Prerequisites: List all students using the `list` command. Multiple students in the list.

    2. Test case 1: `find c/1A`<br>
        Expected: All students that are in class 1A will be shown in a new list. Timestamp in the status bar is updated.

    3. Test case 2: `find c/1a`<br>
       Expected: Same result as Test case 1. Keywords given by user after `c/` are not case sensitive.

    4. Test case 3: `find c/2b`<br>
       Expected: No student will be shown in a new list as no one is in class 2B. Status bar remains the same.

    5. Other incorrect find commands to try: `find c/`, `find c/ `, `find c/x` (where x is a non-existent class)<br>
       Expected: Similar to Test case 3.

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
