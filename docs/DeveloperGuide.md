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




The UI consists of a `MainWindow` that is made up of parts e.g. `OutputPanel`,`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

Additionally, the structure of `OutputPanel` is as shown below:
![Structure of the OutputPanel Component](images/OutputPanelClassDiagram.png)

`OutputPanel` is made up of parts e.g. `TruncatedTaskListPanel`, `ScheduleListPanel`, `UpdatedPersonListPanel`, `UndoCard` etc. Similar to `MainWindow`, all these components including the `OutputPanel` inherit from the abstract `UiPart` class.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/uninurse/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-T12-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

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
1. When `Logic` is called upon to execute a command, it uses the `UninurseBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddPatientCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete -p 1")` API call.

![Interactions Inside the Logic Component for the `delete -p 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifelines for `DeleteGenericCommandParser` and `DeletePatientCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifelines reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `UninurseBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPatientCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPatientCommand`) which the `UninurseBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPatientCommandParser`, `DeletePatientCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the UniNurse book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object) and all of its saved versions.
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)
* _Diagram to be updated with new `Patient`attributes ..._
<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in `UniNurse`, which `Person` references. This allows `UniNurse` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

* each patient has a TaskList, which holds Tasks which can be NonRecurringTasks or RecurringTasks. The below Class diagram illustrates their relationship.
<img src="images/TaskClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both Uninurse book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `UninurseBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.uninurse.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Task viewing feature

#### Implementation

The viewing of tasks that are associated with patients can be achieved using the following 2 commands.

* `listTask` to list all tasks associated to all patients.
* `viewTask PATIENT_INDEX` to list task associated to the patient at the specified `PATIENT_INDEX`.

<br>

The `listTask` command is executed when the user enters the command into the UI which is handled by `ListTaskCommand`. Note that the command does not accept any arguments, hence there is no need for a `ListTaskCommandParser` to be created.

The change is reflected in the UI by calling `Model#updateFilteredPersonList()` with the predicate that returns true if a patient in the list has associated tasks. This currently only updates the patient list panel to display patients that have associated tasks and does not update the output panel. (Future implementations would update the output panel to display all tasks associated with all patients.)

The following sequence diagram illustrates the interactions between the `Logic` and `Model` component when the command is being executed.

<img src="images/ListTaskSequenceDiagram.png" width="500" />

<br>

The `viewTask` command is executed when the user enters the command into the UI which is handled by `ViewTaskCommand`. Since the command requires an argument `PATIENT_INDEX`, a `ViewTaskCommandParser` is created to determine the validity of the arguments provided. Given that valid arguments are provided, the command is then executed in the following manner.

1. The `Patient` at the specified `PATIENT_INDEX` is retrieved from the `lastShownList` obtained from the `Model` component.
1. The `Model` component updates its current list with `Model#updateFilteredPersonList()` to display only the specified `Patient`.
1. The `UI` component updates its result display by displaying the feedback message from `ViewTaskCommand`.
1. The `UI` component updates its output panel by displaying a `TaskListPanel`.

Note that `TaskListPanel` only displays the complete list of tasks of the specified patient and essential information such as the patient name and tags.

The following sequence diagrams illustrates the interactions between the `UI`, `Logic` and `Model` component when the command is being executed.

<img src="images/ViewTaskSequenceDiagram1.png" width="900" />

<img src="images/ViewTaskSequenceDiagram2.png" width="500" />

### Viewing tasks on a particular day feature

#### Implementation

The `tasksOn` command parses the user input and generates a `DateTime` object, however the time fields are default values since we only care about the particular Date, the it filters each patient by whether they have a 
task on the given Date. Each patient themselves filter their TaskList to arrive at the conclusion on whether they have a task on the given Date. Then the Model is set to show the filtered Patients, each with their own TaskList to also 
only the Tasks on the given Date.

The Sequence diagram below shows the execution of a tasksOn command

![tasksOnSequenceDiagram](images/TasksOnSequenceDiagram.png)

### \[Proposed\] Undo/redo feature
### Add/delete medical conditions from patients

Users can add a medical condition to a particular patient by providing the following details:
1. The patient's index number shown in the displayed patient list.
2. The condition to be added.

There are two ways a user can add a medical condition:
1. Add multiple medical conditions at one go when the user first creates a patient.
2. Add one condition at a time to an existing patient.

#### Implementation

A medical condition is represented by `Condition`, and multiple conditions are stored internally as a `ConditionList`.
`ConditionList` facilitates the add/delete condition mechanism, and each patient only has one associated `ConditionList`.
`ConditionList` mainly implements the following operations:
* `ConditionList#add()`: adds a condition to the patient's list of conditions.
* `ConditionList#delete()`: removes a condition from the patient's list of conditions.

Figure 1 below summarises what happens when a user executes an add condition command on a specified patient:
<figure>
    <img src="images/AddConditionActivityDiagram.png" alt="add_condition_activity_diagram"/>
    <figcaption>
        <em>Figure 1: Activity diagram showing the flow of events when a user executes an add condition command</em>
    </figcaption>
</figure>

#### Interactions 

Given below is an example usage scenario and how the add condition mechanism behaves at each step.

1. The user executes the `addCondition 1 c/Diabetes` command to add a condition to the first patient in the
displayed patient list.

2. `UninurseBookParser#parseCommand()` parses the command word `addCondition`, and then creates a corresponding `AddConditionCommandParser` object.

3. `AddConditionCommandParser#parse()` parses the patient index `1` and the condition `Diabetes` provided, and then creates an `AddConditionCommand` object.

4. The `AddConditionCommand` object interacts with the `Model` to add a condition to the specified patient's condition list.

5. `Logic` returns a `CommandResult` object, which encapsulates the result of the execution of the add condition command.

Figure 2 below shows how `Logic` executes the add condition operation:
<figure>
    <img src="images/AddConditionSequenceDiagram.png" alt="add_condition_sequence_diagram"/>
    <figcaption>
        <em>Figure 2: Sequence diagram showing interactions within the Logic component when a user executes an add condition command</em>
    </figcaption>
</figure>

_To be updated with details of delete condition feature ..._

### Undo/redo feature

The undo/redo mechanism is facilitated by `PersistentUninurseBook`. It consists of a list of `UninurseBookSnapshot`, which itself consists of a `UninurseBook` and a `CommandResult`. `PersistentUninurseBook` stores the saved versions of all `UninurseBook`s, stored internally as:
* `workingCopy`, which is the current (possibly unsaved) version of `UninurseBook`.
* `uninurseBookVersions` and `currentVersion`, which are the saved versions of `UninurseBook`s and which version we last replaced `workingCopy` with.

Additionally, it implements the following operations:

* `PersistentUninurseBook#makeSnapshot(CommandResult)` — Saves the current Uninurse book state (i.e. `workingCopy`) in its history, along with the command result message.
* `PersistentUninurseBook#undo()` — Restores the previous Uninurse book state from its history.
* `PersistentUninurseBook#redo()` — Restores a previously undone Uninurse book state from its history.

These operations are exposed in the `Model` interface as `Model#makeSnapshot(CommandResult)`, `Model#undo()` and `Model#redo()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `PersistentUninurseBook` will be initialized with the initial Uninurse book state, and the `currentVersion` pointing to that single Uninurse book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete -p 5` command to delete the 5th person in the Uninurse book. The `delete` command calls `Model#makeSnapshot(CommandResult)`, causing the modified state of the Uninurse book after the `delete -p 5` command executes to be saved in the `uninurseBookVersions` along with the `CommandResult` of the `delete` command, and the `currentVersion` is shifted to the newly inserted Uninurse book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …` to add a new person. The `add` command also calls `Model#makeSnapshot(CommandResult)`, causing another modified Uninurse book state and the `CommandResult` of the `add` command to be saved into the `uninurseBookVersions`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#makeSnapshot(CommandResult)`, so the Uninurse book state will not be saved into the `uninurseBookVersions`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undo()`, which will shift the `currentVersion` once to the left, pointing it to the previous Uninurse book state, and restores the Uninurse book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentVersion` is at index 0, pointing to the initial UninurseBook state, then there are no previous UninurseBook states to restore. The `undo` command uses `Model#canundo()` to check if this is the case.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redo()`, which shifts the `currentVersion` once to the right, pointing to the previously undone state, and restores the Uninurse book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentVersion` is at index `uninurseBookVersions.size() - 1`, pointing to the latest Uninurse book state, then there are no undone UninurseBook states to restore. The `redo` command uses `Model#canredo()` to check if this is the case.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the Uninurse book, such as `list`, will usually not call `Model#makeSnapshot(CommandResult)`, `Model#undo()` or `Model#redo()`. Thus, the `uninurseBookVersions` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#makeSnapshot(CommandResult)`. Since the `currentVersion` is not pointing at the end of the `uninurseBookVersions`, all Uninurse book states after the `currentVersion` will be purged. Reason: It no longer makes sense to redo the `add n/David …` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire Uninurse book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### Display added/edited/deleted patient feature
#### Implementation
Adding/editing/deleting a patient would have a `UpdatedPersonCard` with the patient’s details appear in the `OutputPanel` of the `UI`. The possible commands to achieve this are:
1. add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [d/TASK_DESCRIPTION]… [t/TAG]…
2. edit -p PATIENT_INDEX
3. delete -p PATIENT_INDEX 

When the user enters any of the above 3 inputs, `UninurseBookParser` parses the inputs and returns the appropriate `Command` (i.e. `AddPatientCommand`, `EditPatientCommand`, or `DeletePatientCommand`) to `LogicManager`. Once the `Command` is executed, the patient is then set as the `patientOfInterest` in the `Model` by calling the `Model#setPatientOfInterest()` method. 

Below is a sequence diagram to show the interaction between the `Logic` and `Model` components for **adding a patient** (sequence is similar for editing and deleting a patient):



<img src="images/AddPatientSequenceDiagram3.png"  />

After that, `MainWindow` would call `LogicManager#getPatientOfInterest()` to retrieve the required patient. Thereafter, assuming a patient is added, `MainWindow` calls `OutputPanel#handleAddPatient()` with the patient as a parameter, which would then create a `UpdatedPersonCard` with the patient’s details which appears in the `OutputPanel`.

Below is the sequence diagram which shows the entire interaction between the `UI`, `Logic`, and `Model` components for **adding a patient** (sequence is similar for editing and deleting a patient):

<img src="images/AddPatientSequenceDiagram2.png"  />

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

This product is for private nurses to help manage the details and needs of their patients.

* has a need to manage a significant number of patient contacts
* wants to be able to view patient's needs at a glance
* prefer to have quick access to details of patient's contacts and their needs
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Allows private nurses to manage different detail-sensitive tasks for specific patients in a more organized manner.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …                                     | I want to …                                                      | So that I can…                                                         |
| -------- | ------------------------------------------ | ---------------------------------------------------------------- | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | add a person                                                               |                                                                                |
| `* * *`  | user                                       | delete a person                                                            | remove entries that I no longer need                                           |
| `* * *`  | private nurse                              | be able to edit a patient's details                                        | update their information if there are any changes                              |
| `* * *`  | private nurse with many patients           | search a patient by name                                                   | locate a patient easily                                                        |
| `* * *`  | user                                       | add a task to a patient                                                    | know what task I need to do for the patient                                    |
| `* * *`  | private nurse                              | delete a task associated with a patient                                    | remove tasks that I no longer need                                             |
| `* * *`  | private nurse                              | edit a task associated with a patient                                      | update the task if there are any changes                                       |
| `* * *`  | private nurse                              | know what tasks I need to do                                               | prepare for them beforehand                                                    |
| `* * *`  | private nurse                              | view all tasks associated with a patient                                   |                                                                                |
| `* *`    | user                                       | see my list of patients for the day                                        | know my schedule                                                               |
| `* *`    | private nurse with many patients and tasks | search a patient by name and task                                          | locate a patient with a specific task or name easily                           |
| `* *`    | private nurse with many patients           | view all tasks for a particular day                                        | easily see the more urgent tasks I have for the day                            |
| `* *`    | private nurse with many patients and tasks | search patients or tasks by a characteristic                               | locate patients or tasks more easily                                           |
| `* *`    | busy nurse with short attention span       | customise what information I can see at a glance                           | waste less time looking through long chunks of text to find what I want        |
| `* *`    | private nurse                              | know the exact location of my patient (i.e floor no and room no)           | quickly navigate to their side when needed                                     |
| `* *`    | private nurse                              | know the type of ward (i.e. contagious, non-contagious)                    | know if I need to don PPE before attending to them                             |
| `* *`    | member of the hospital                     | update the room location of the patient                                    | know if the patient is moved for any reasons, everyone involved will be aware of the change|
| `* *`    | private nurse                              | add a recurring task associated with a patient                             | keep track of tasks that I have to do repeatedly (e.g. weekly visits)          |
| `* *`    | private nurse                              | delete a recurring task associated with a patient                          | remove any tasks that I no longer need                                         |
| `* *`    | private nurse                              | edit a recurring task associated with a patient                            | update the task to reflect any changes                                         |
| `* *`    | private nurse                              | know the specific requirements for my patients (if any)                    | take special precaution                                                        |
| `* *`    | doctor                                     | key in additional notes about the patients                                 | let anyone attending to them know more about the patients                      |
| `* *`    | private nurse                              | know additional notes the doctor made about the patient                    | better care for the patients                                                   |
| `* *`    | private nurse                              | delete a note about a patient                                              | remove notes that I no longer need                                             |
| `* *`    | private nurse                              | edit a note about a patient                                                | update it to reflect any changes                                               |
| `* *`    | private nurse                              | view all notes about a patient                                             |                                                                                |
| `* *`    | private nurse                              | add a medical condition to a patient                                       | take note of the conditions they have                                          |
| `* *`    | private nurse                              | delete a medical condition from a patient                                  | remove conditions a patient may have recovered from                            |
| `* *`    | private nurse                              | edit a medical condition of a patient                                      | update any changes (e.g. severity of condition, etc.)                          |
| `* *`    | private nurse                              | view all medical conditions of a patient                                   | have better overview of the needs and type of care the patient needs           |
| `* *`    | doctor                                     | update the medication type and dosage                                      | progressively monitor the patient and update the info accordingly              |
| `* *`    | doctor                                     | key in medication types and dosage                                         | let nurses administer the appropriate type and amount                          |
| `* *`    | doctor                                     | delete the medication type and dosage                                      | remove medication that the patient no longer needs                             |
| `* *`    | private nurse                              | know what medication my patient needs                                      | prepare the dosages accordingly                                                |
| `* *`    | private nurse                              | know what type of medication my patient is allergic to                     | avoid any potential mistake administering medication                           |
| `* *`    | private nurse                              | be able to undo/redo recent commands I made in case of a mistake           |                                                                                |
| `* *`    | user                                       | navigate through my command history                                        | easily reuse a past command                                                    |
| `* *`    | private nurse with many patients and tasks | search patients or tasks by name and delete them                           | easily clear patients I am no longer assigned to                               |
| `*`      | private nurse                              | know the patient's family member's details                                 | contact them in case of emergency                                              |
| `*`      | private nurse                              | know which doctors are assigned to my patients                             | report any irregularities to them                                              |
| `*`      | private nurse                              | know the doctors (and their contact) assigned to the patients              | know who to contact in case of emergency                                       |
| `*`      | private nurse                              | archive former patient details (e.g., patient is cured)                    | more easily keep track of only active patients and also still able to recall a patient's details if needed in the future (e.g., patient gets another disease, don't have to ask them for contact details again)|
| `*`      | doctor in charge of the patient,           | control who has edit or read access                                        | ensure no unqualified person can change the patients medicine requirements     |
| `*`      | user                                       | know what apparatus I need for my tasks                                    | retrieve them beforehand                                                       |
| `*`      | user                                       | be able to add customised command syntax to tailor to my typing preferences| manage my patients even faster                                                 |

### Use cases

(For all use cases below, the **System** is the `UniNurse` application and the **Actor** is the `user`,
unless specified otherwise)

---

**Use case: UC01 - View the list of all patients**

**MSS**

1. User requests to list patients.
2. UniNurse shows a list of patients.

    Use case ends.

**Extensions**

* 2a. The list is empty.

    Use case ends.

---

**Use case: UC02 - Add a patient**

**MSS**

1. User adds a patient to patient list with patient details.
2. UniNurse shows the list of patients with the newly added patient.

    Use case ends.

**Extensions**

* 1a. The given details are invalid.

    * 1a1. UniNurse shows an error message.

      Use case resumes at step 1.

---

**Use case: UC03 - Edit a patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to edit a patient profile with the information that they want to change.
3. UniNurse edits the patient.

    Use case ends.

**Extensions**

* 2a. The given index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given details to edit are invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2c. The new details to be updated are invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC04 - Delete a patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to delete a specific patient in the list.
3. UniNurse deletes the patient.

    Use case ends.

**Extensions**

* 2a. The given index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC05 - Find patients with keywords**

**MSS**

1. User requests to find patients whose details contain specified keywords.
2. UniNurse shows a list of patients whose details contain the specified keywords.
   
    Use case ends.

**Extensions**

* 1a. No keywords are given.

   * 1a1. UniNurse shows an error message.
  
     Use case ends. 
   
---

**Use case: UC06 - Add a task to a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to add a task with its description to a specific patient.
3. UniNurse adds the task to a patient.
   
    Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given task details is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC07 - Edit a task associated with a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to edit a task with its description they want to change, by specifying the specific patient and specific task.
3. UniNurse edits the task description of the chosen task of the specified patient.

    Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given task index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2c. The given task details is invalid.
  
    * 2c1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC08 - Delete a task associated with a specified patient**

**MSS**
1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to delete a task by specifying the specific patient and specific task.
3. UniNurse deletes the task of the specified patient.

    Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

  * 2a1. UniNurse shows an error message.
    
    Use case resumes at step 2.

* 2b. The given task index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.
    
---

**Use case: UC09 - See the list of tasks to be completed**

**MSS**

1.  User requests to list tasks.
2.  UniNurse shows a list of tasks together with patient name.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

---

**Use case: UC10 - See the list of tasks associated with a specific patient**

**MSS**

1.  User requests to list tasks for patient at a specified index.
2.  UniNurse shows a list of tasks for the specified patient.

    Use case ends.

**Extensions**

* 1a. The given index is invalid or out of range.

    * 1a1. UniNurse shows an error message.

      Use case ends.

* 2a. The list is empty.

  Use case ends.

---

**Use case: UC11 - See the list of patients with tasks on current day**

**MSS**

1. User requests to list patients with tasks for the current day.
2. UniNurse shows a list of patients with tasks for that day.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

---

**Use case: UC12 - See the list of tasks on a particular day**

**MSS**

1. User requests to list tasks on a particular day.
2. UniNurse shows the list of tasks on that particular day.

   Use case ends.

**Extensions**

* 1a. The given date is invalid or wrong format.

    * 1a1. UniNurse shows an error message.

      Use case ends.

* 2a. The list is empty.

  Use case ends.

---

**Use case: UC13 - Undo a modification command**

**MSS**

1. User requests to undo the last command which modifies the patient or task list, excluding undo or redo commands.
2. UniNurse reverts the patient and task list to the version before the last modification command.

   Use case ends.

**Extensions**

* 1a. There are no more commands to undo.

    * 1a1. UniNurse shows an error message.

      Use case ends.

* 1b. The undo limit has been reached.
    * 1b1. UniNurse shows an error message.

      Use case ends.


---

**Use case: UC14 - Reverse an undo command**

**MSS**

1. User requests to reverse the last undo command.
2. UniNurse reverts the patient and task list to the version before the last undo command.

   Use case ends.

**Extensions**

* 1a. The previous command is not an undo command.

    * 1a1. UniNurse shows an error message.

      Use case ends.


---

**Use case: UC15 - Add a medical condition to a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to add a medical condition with its details to a specific patient.
3. UniNurse adds the medical condition to a patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given medical condition details are invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC16 - Edit a medical condition associated with a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to edit a medical condition with the details they want to change, by specifying the patient and medical condition.
3. UniNurse edits the details of the chosen condition of the specified patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given medical condition index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2c. The given medical condition details are invalid.

    * 2c1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC17 - Delete a medical condition associated with a specified patient**

**MSS**
1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to delete a medical condition by specifying the patient and medical condition.
3. UniNurse deletes the medical condition of the specified patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given medical condition index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.


---

**Use case: UC18 - Add a tag to a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to add a tag with its details to a specific patient.
3. UniNurse adds the tag to a patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given tag details are invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC19 - Edit a tag associated with a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to edit a tag with the details they want to change, by specifying the patient and tag.
3. UniNurse edits the details of the chosen tag of the specified patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given tag index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2c. The given tag details are invalid.

    * 2c1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC20 - Delete a tag associated with a specified patient**

**MSS**
1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to delete a tag by specifying the patient and tag.
3. UniNurse deletes the tag of the specified patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given tag index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.


---

**Use case: UC21 - Add a medication to a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to add a medication with its details to a specific patient.
3. UniNurse adds the medication to a patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given medication details are invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC22 - Edit a medication associated with a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to edit a medication with the details they want to change, by specifying the patient and medication.
3. UniNurse edits the details of the chosen medication of the specified patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given medication index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2c. The given medication details are invalid.

    * 2c1. UniNurse shows an error message.

      Use case resumes at step 2.


---

**Use case: UC23 - Delete a medication associated with a specified patient**

**MSS**
1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to delete a medication by specifying the patient and medication.
3. UniNurse deletes the medication of the specified patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given medication index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.


---

**Use case: UC24 - Add a remark to a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to add a remark with its details to a specific patient.
3. UniNurse adds the remark to a patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given remark details are invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

---

**Use case: UC25 - Edit a remark associated with a specified patient**

**MSS**

1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to edit a remark with the details they want to change, by specifying the patient and remark.
3. UniNurse edits the details of the chosen remark of the specified patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given remark index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2c. The given remark details are invalid.

    * 2c1. UniNurse shows an error message.

      Use case resumes at step 2.


---

**Use case: UC26 - Delete a remark associated with a specified patient**

**MSS**
1. User requests to <u>view the list of patients (UC01)</u>.
2. User requests to delete a remark by specifying the patient and remark.
3. UniNurse deletes the remark of the specified patient.

   Use case ends.

**Extensions**

* 2a. The given patient index is invalid.

    * 2a1. UniNurse shows an error message.

      Use case resumes at step 2.

* 2b. The given remark index of the specified patient is invalid.

    * 2b1. UniNurse shows an error message.

      Use case resumes at step 2.


*{More to be added}*

---

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Any user action should produce a result within 1 second.
5. Each person should be able to hold up to 50 tasks without a noticeable sluggishness in performance for typical usage.
6. A user should be able to easily access tasks associated with a patient.
7. The product is not required to handle data between multiple users.

*{More to be added}*

### Glossary

* **Private Nurse**: A private duty nurse who is self-employed or works for a home health care organization and provides nursing care to patients with restricted mobility or ability for self-care
* **PPE**: Personal Protective Equipment is equipment worn to minimize exposure to hazards that cause serious workplace injuries and illnesses
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a
starting point for testers to work on; testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample patients. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Modifying patient contact details

1. Adding a new patient to the patient list.

   1. Prerequisites: The new patient does not already exist in the patient list. 
   
   2. Test case: `add n/John Doe p/91235678 e/johnd@gmail.com a/312 Rivervale Street SG 416875 t/elderly r/Allergic to paracetemol m/Cough syrup | 5ml a day c/Cough` <br>
      Expected: The patient John Doe is added to the patient list. Details of the added patient shown in the status message and output panel.
   
   3. Test case: `add n/John Doe p/91235678 e/johnd@gmail.com a/312 Rivervale Street SG 416875 t/elderly r/Allergic to paracetemol m/Cough syrup | 5ml a day c/Cough` (duplicate patient) <br>
      Expected: No patient is added to the list. Error details shown in the status message. 
   
   4. Other incorrect add commands to try: `add`, `add n/Mike Ho p/91283045` (missing field) <br>
      Expected: Similar to previous.

2. Editing a patient's contact details in the patient list. 

   1. Prerequisites: There is at least 1 patient in the displayed patient list.
   
   2. Test case: `edit -p 1 n/Michelle Ho` <br>
      Expected: The name of the first patient displayed in the patient list is changed to `Michelle Ho`. Details of the edited patient shown in the status message and output panel.
   
   3. Test case: `edit -p 0 p/98776566` <br>
      Expected: No patient is edited. Error details shown in the status message. 
   
   4. Other incorrect edit commands to try: `edit`, `edit -p 1` (missing field), `edit -p x n/Edna`, `...` (where x is larger than the displayed patient list size)). <br>
      Expected: Similar to previous.


3. Deleting a patient from the patient list.

   1. Prerequisites: There is at least 1 patient in the displayed patient list.

   2. Test case: `delete- p 1`<br>
      Expected: First patient in the displayed list is deleted. Details of the deleted patient is shown in the status message and the output panel.

   3. Test case: `delete -p 0`<br>
      Expected: No patient is deleted. Error details shown in the status message.

   4. Other incorrect delete commands to try: `delete` (missing field), `delete -p x`, `...` (where x is larger than the displayed list size)<br>
      Expected: Similar to previous.
   
### Modifying tags

1. Adding a tag to a patient in the patient list. 
   
   1. Prerequisites: There is at least 1 patient in the displayed patient list. The new tag is not already in the patient's list of tags.
   
   2. Test case: `add -p 1 t/elderly` <br>
      Expected: The `elderly` tag is added to the first patient in the displayed list. Details of the added tag is shown in the status message and the output panel.
   
   3. Test case: `add -p 1 t/elderly` (duplicate tag) <br>
      Expected: No tag is added. Error details shown in the status message.
   
   4. Other incorrect adding tags commands to try: `add -p 1 t/` (missing field), `add -p 0 t/critical`, `add -p x t/wheelchair`, `...` (where x is larger than the displayed list size) <br>
      Expected: Similar to previous.

2. Editing a patient's tag.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 2 tags.
   
   2. Test case: `edit -p 1 -t 1 t/infant` <br>
      Expected: The first tag of the first patient in the displayed list is changed to `infant`. Details of the new tag is shown in the status message and the output panel.
   
   3. Test case: `edit -p 1 -t 2 t/infant` (duplicate tag) <br>
      Expected: No tag is edited. Error details shown in the status message.
   
   4. Other incorrect editing tag commands to try: `edit -p 1 -t` (missing field), `edit -p 1 -t 0`, `edit -p 1 -t x t/bedridden`, `...` (where x is larger than the number of patient's tags) <br>
      Expected: Similar to previous.

3. Deleting a patient's tag.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 1 tag.
   
   2. Test case: `delete -p 1 -t 1` <br>
      Expected: The first tag from the first patient in the displayed list is deleted. Details of the deleted tag is shown in the status message and the patient's details is shown in the output panel.
   
   3. Test case: `delete -p 1 -t 0` <br>
      Expected: No tag is deleted. Error details are shown in the status message.
   
   4. Other incorrect deleting tag commands to try: `delete -p 1 -t` (missing field), `delete -p 1 -t x`, `...` (where x is larger than the number of patient's tags) <br>
      Expected: Similar to previous.
   
### Modifying tasks

1. Adding a new non-recurring task to a patient's task list.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The new non-recurring task is not already in the patient's task list.
   
   2. Test case: `add -p 1 d/Change dressing | 6-10-23 0900` <br>
      Expected: The non-recurring task `Change dressing`  is added to the first patient in the displayed list. Details of the new task is shown in the status message and the output panel.
   
   3. Test case: `add -p 1 d/Change dressing | 6-10-23 0900` (duplicate task) <br>
      Expected: No task is added. Error details are shown in the status message.
   
   4. Other incorrect adding task commands to try: `add -p 1 d/` (missing field), `add -p 1 d/Take blood sample | 14-14-2022` (invalid date) <br>
      Expected: Similar to previous.

2. Adding a new recurring task to a patient's task list.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The new recurring task is not already in the patient's task list.
   
   2. Test case: `add -p 1 d/Take urine sample | 6-10-23 0900 | 1 week`<br>
      Expected: The recurring task `Take urine sample` is added to the first patient in the displayed list. Details of the new task is shown in the status message and the output panel.
   
   3. Test case: `add -p 1 d/Take hair sample | 7-11-23 0900 | 10 hours` (invalid recurrence) <br>
      Expected: No task is added. Error details are shown in the status message.
   
   4. Other incorrect adding task commands to try: `add -p 1 d/Escort to X-ray | 8-10-23 0900 | 10` (incomplete recurrence)
      Expected: Similar to previous.

3. Editing a task in a patient's task list.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 2 tasks in his/her task list.
   
   2. Test case: `edit -p 1 -d 1 d/|5-10-22 0800` <br>
      Expected: The date and time of the first task of the first patient of the displayed list is changed to `5-10-22 0800`. Details of the new task is shown in the status message and output panel.

   3. Test case: `edit -p 1 -d 1 d/Conduct physiotherapy | 6-10-22 0900` <br>
      Expected: The first task of the first patient of the displayed list is changed to `Conduct physiotherapy` with the date and time of `6-10-22 0900`. Details of the new task is shown in the status message and output panel.
   
   4. Test case: `edit -p 1 -d 2 d/Conduct physiotherapy | 6-10-22 0900` (duplicate task) <br>
      Expected: No task is edited. Error details are shown in the status message.
   
   5. Other incorrect editing task commands to try: `edit -p 1 -d 1 d/` (missing field), `edit -p 1 -d 1`
   

4. Deleting a task in a patient's task list.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 1 task in his/her task list.
   
   2. Test case: `delete -p 1 -d 1` <br>
      Expected: The first task of the first patient of the displayed list is deleted. Details of the deleted task is shown in the status message. The patient's new task list is shown in the output panel.
   
   3. Test case: `delete -p 1 -d 0` <br>
      Expected: No task is deleted. Error details are shown in the status message.
   
   4. Other incorrect deleting task commands to try: `delete -p 1 -d` (missing field), `delete -p 1 -d x`, `...` (where x is larger than the number of patient's tasks) <br>
      Expected: Similar to previous.

### Modifying medical conidtions

1. Adding a new medical condition to a patient.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The new medical condition is not already in the patient's list of conditions. 
   
   2. Test case: `add -p 1 c/Asthma` <br>
      Expected: The medical condition `Asthma` is added to the first patient of the displayed list. Details of the new condition is shown in the status message and the output panel.
   
   3. Test case: `add -p 1 c/Asthma` (duplicate condition) <br>
      Expected: No condition is added. Error details are shown in the status message.
   
   4. Other incorrect adding medical condition commands to try: `add -p 1 c/` (missing field) <br>
      Expected: Similar to previous.

      
2. Editing a medical condition in a patient's list of conditions.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 2 conditions added.
   
   2. Test case: `edit -p 1 -c 1 c/Cough` <br>
      Expected: The first condition of the first patient in the displayed list is changed to `Cough`. Details of the new condition is shown in the status message and the output panel.
   
   3. Test case: `edit -p 1 -c 2 c/Cough` (duplicate condition) <br>
      Expected: No condition is edited. Error details are shown in the status message.
   
   4. Other incorrect editing medical condition commands to try: `edit -p 1 -c 0 c/Covid` (invalid index), `edit -p 1 -c 1 c/` (missing field) <br>
      Expected: Similar to previous.

      
3. Deleting a medical condition from a patient's list of conditions.

    1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 1 medical condition.

    2. Test case: `delete -p 1 -c 1` <br>
       Expected: The first condition of the first patient in the displayed list is deleted. Details of the deleted task is shown in the status message. The patient's new details is shown in the output panel.

    3. Test case: `delete -p 1 -c 0` <br>
       Expected: No task is deleted. Error details are shown in the status message.

    4. Other incorrect deleting task commands to try: `delete -p 1 -c` (missing field), `delete -p 1 -c x`, `...` (where x is larger than the number of patient's conditions) <br>
       Expected: Similar to previous.

### Modifying medications

1. Adding a new medication to a patient's list of medication.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The new medication is not already in the patient's list of medication.
   
   2. Test case: `add -p 1 m/Cough syrup | 5ml a day` <br>
      Expected: The medication `Cough syrup` with dosage `5ml a day` is added to the first patient in the displayed list. Details of the new medication is shown in the status message and the output panel.
   
   3. Test case: `add -p 1 m/Cough syrup | 5ml a day` <br>
      Expected: No medication is added. Error details are shown in the status message.
   
   4. Other incorrect adding medication commands to try: `add -p 1 m/` (missing fields), `add -p 1 m/pills` (missing dosage) <br>
      Expected: Similar to previous.
   

2. Editing a medication in a patient's list of medications.

   1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 2 medications.
   
   2. Test case: `edit -p 1 -m 1 m/Sleeping pills | 1 before sleeping` <br>
      Expected: The first medication of the first patient in the displayed list is changed to `Sleeping pills` with dosage `1 before sleeping`. Details of the new medication is shown in the status message and the output panel.
   
   3. Test case: `edit -p 1 -m 2 m/Sleeping pills | 1 before sleeping` (duplicate medication) <br>
      Expected: No medication is edited. Error details are shown in the status message.
   
   4. Other incorrect editing medication commands to try: `edit -p 1 -m 0 m/pills`, `edit -p 1 -m 1 m/` (missing fields) <br>
      Expected: Similar to previous.

   
3. Deleting a medication from a patient's list of medications.

    1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 1 medication.

    2. Test case: `delete -p 1 -m 1` <br>
       Expected: The first medication of the first patient in the displayed list is deleted. Details of the deleted medication is shown in the status message. The patient's new details is shown in the output panel.

    3. Test case: `delete -p 1 -m 0` <br>
       Expected: No task is deleted. Error details are shown in the status message.

    4. Other incorrect deleting task commands to try: `delete -p 1 -m` (missing field), `delete -p 1 -m x`, `...` (where x is larger than the number of patient's medications) <br>
       Expected: Similar to previous.

### Modifying remarks

1. Adding a new remark to a patient.

    1. Prerequisites: There is at least 1 patient in the displayed patient list. The new remark is not already in the patient's list of remark.

    2. Test case: `add -p 1 r/Allergic to cough syrup` <br>
       Expected: The remark `Allergic to cough syrup` is added to the first patient in the displayed list. Details of the new remark is shown in the status message and the output panel.

    3. Test case: `add -p 1 r/Allergic to cough syrup` (duplicate remarks) <br>
       Expected: No remark is added. Error details are shown in the status message.

    4. Other incorrect adding remark commands to try: `add -p 1 r/` (missing field) <br>
       Expected: Similar to previous.


2. Editing a remark in a patient's list of remarks.

    1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 2 remarks.

    2. Test case: `edit -p 1 -r 1 r/Allergic to dust` <br>
       Expected: The first remark of the first patient in the displayed list is changed to `Allergic to dust`. Details of the new remark is shown in the status message and the output panel.

    3. Test case: `edit -p 1 -r 2 r/Allergic to dust` (duplicate remark) <br>
       Expected: No remark is edited. Error details are shown in the status message.

    4. Other incorrect editing remark commands to try: `edit -p 1 -r 0`, `edit -p 1 -r 1 r/` (missing fields) <br>
       Expected: Similar to previous.


3. Deleting a remark from a patient's list of remarks.

    1. Prerequisites: There is at least 1 patient in the displayed patient list. The patient has at least 1 remark.

    2. Test case: `delete -p 1 -r 1` <br>
       Expected: The first remark of the first patient in the displayed list is deleted. Details of the deleted remark is shown in the status message. The patient's new details is shown in the output panel.

    3. Test case: `delete -p 1 -r 0` <br>
       Expected: No remark is deleted. Error details are shown in the status message.

    4. Other incorrect deleting remark commands to try: `delete -p 1 -r` (missing field), `delete -p 1 -r x`, `...` (where x is larger than the number of patient's remarks) <br>
       Expected: Similar to previous.


### Viewing details of a patient

1. View details of a specific patient.

   1. Prerequisites: There is at least 1 patient in the displayed patient list.
   
   2. Test case: `focus -p 1` <br>
      Expected: The details of the first patient in the displayed list is shown in the output panel.
   
   3. Test case: `focus -p 0` (invalid index) <br>
      Expected: No patient is displayed in the output panel. Error details are shown in the status message.
   
   4. Other incorrect viewing commands: `focus -p `, `focus 1` (missing fields) <br>
      Expected: Similar to previous.

### Finding patients

 1. Finding patients with specific keywords.

    1. Prerequisites: None.
    
    2. Test case: `find Alex Yeoh` <br>
       Expected: A list of patients whose details contain the keywords `Alex Yeoh` are shown in the displayed patient list and output panel.
    
    3. Test case: `find cough syrup` <br>
       Expected: A list of patients whose details contain the keywords `cough syrup` are shown in the displayed patient list and output panel.
    
    4. Test case: `find` (missing keyword) <br>
       Expected: No patients are shown in the output panel. Error details are shown in the status message.
   
### Viewing all tasks for a particular day

1. Viewing all tasks for today.

    1. Prerequisites: None.

    2. Test case: `view --today` <br>
       Expected: All tasks whose date is the current day are shown in the output panel.
   

2. Viewing all tasks for a particular day.

   1. Prerequisites: None.
   
   2. Test case: `view 10-10-22` <br>
      Expected: All tasks with the date `10-10-22` are shown in the output panel.
   
   3. Test case: `view 10-10-2022` (invalid date format) <br>
      Expected: No tasks are shown in the output panel. Error details are shown in the status message.
   
   4. Other incorrect viewing commands: `view 15-15-22` (invalid date), `view ` (missing date) <br>
      Expected: Similar to previous.


### Viewing all tasks of a patient

 1. Viewing all tasks of a patient.

    1. Prerequisites: There is at least 1 patient in the displayed patient list.
    
    2. Test case: `view -p 1` <br>
       Expected: The task list of the first patient in the displayed list is shown in the output panel.
    
    3. Test case: `view -p 0` (invalid index) <br>
       Expected: No task list is shown in the output panel. Error details are shown in the status message.
    
    4. Other incorrect task viewing commands to try: `view -p ` (missing field), `view -p x` , `...` (where x is larger than the size of the patient list) <br>
       Expected: Similar to previous.

### Viewing all tasks of all patients

1. Viewing all tasks of all patients.

   1. Prerequisites: None.
   
   2. Test case: `view -p --all` <br>
      Expected: All tasks of all patients in the patient list are shown in the output panel.

### Undoing previous command

 1. Undoing a previous command entered.
 
    1. Prerequisites: At least 1 add/edit/delete command was executed.
    
    2. Test case: `undo` <br>
       Expected: Previous command is undone. Details of the undone command is shown in the status message and output panel.

### Reversing undo command

1. Reversing an undo command.

   1. Prerequisites: An undo command was executed successfully.
   
   2. Test case: `redo` <br>
      Expected: Previous undo command is reversed. Details of the redone command is shown in the status message and output panel.

   
### Clearing all patients

1. Delete all patients in the displayed patient list.

   1. Prerequisites: There is at least 1 patient in the displayed patient list.
   
   2. Test case: `clear` <br>
      Expected: All patients in the displayed list are deleted. Details of the clear command is shown in the status message.

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases … }_
