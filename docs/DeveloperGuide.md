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

**Target user profile**: CS1101S TA

* has to keep track of significant number of tasks
  * grade mission and quests
  * schedule mastery checks
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage students and tasks in an organised manner easily and quickly


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                           | I want to …​                                                    | So that I …​                                                                               |
|----------|-----------------------------------|-----------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| `* * *`  | potential user                    | find the installation/setup instructions                        | can install the app properly                                                               |
| `* * *`  | user ready to start using the app | see the basic commands                                          | can learn how to use the most basic features of the app                                    |
| `* * *`  | user ready to start using the app | add the students in my tutorial class into the app              | can track the students' attendance and grades                                              |
| `* * *`  | user                              | add my tasks                                                    | will not forget to do any of the tasks                                                     |
| `* * *`  | user                              | mark tasks as done                                              | can focus on the remaining tasks                                                           |
| `* * *`  | user                              | mark tasks as not done                                          | can go back and redo tasks that are incomplete                                             |
| `* * *`  | clumsy user                       | delete tasks                                                    | can remove tasks I have wrongfully added                                                   |
| `* * *`  | clumsy user                       | delete students                                                 | can remove students I have wrongfully added                                                |
| `* * *`  | user                              | list the tasks that I need to do                                | can see all my tasks                                                                       |
| `* * *`  | user                              | list students in my class                                       | can see all my students with relevant details                                              |
| `* * *`  | user                              | see my students' mastery check completion status                | know which students I have not seen for mastery check                                      |
| `* * *`  | user                              | update my students' mastery check completion status             | can keep track of which students I have already seen for mastery check                     |
| `* *`    | first time user                   | see the app being populated with sample students and tasks      | can try out the functions of the app                                                       |
| `* *`    | user ready to start using the app | clear all current data                                          | can get rid of the sample data used for exploring the app and input my own data            |
| `*`      | user                              | assign different priorites to my tasks                          | can focus on the more important tasks                                                      |
| `*`      | user ready to start using the app | import my timetable for the semester	                           | can plan my TA duties in sync with tasks from other modules                                |
| `*`      | user                              | add in mastery check timeslots                                  | can keep track of when and who I have to see for mastery check and I can make preparations | 
| `*`      | user                              | detect if there any schedule conflicts in my upcoming tasks     | resolve those conflicts and complete all my tasks                                          |
| `*`      | user                              | add in timeslots for consultations	                             | can keep track of details of consultation and students                                     |
| `*`      | user                              | get the task with the next earliest deadline	                   | can plan my schedule accordingly                                                           |
| `*`      | user                              | receive reminders about upcoming deadlines                      | am able to meet all my deadlines on time                                                   |
| `*`      | user                              | receive notifications when my students submit their assignments | know that I have assignments to grade                                                      |
| `*`      | user                              | keep track of my students' level of participation               | decide how to assign tutorial participation exp for each student                           |
| `*`      | user                              | keep track of my students' grades                               | can focus more on the weaker students                                                      |
| `*`      | user                              | send notifications to my students                               | can remind them to submit their work                                                       |
| `*`      | user                              | see performance statistics on each assignment                   | can spend more time on topics that my students are weak in                                 |
| `*`      | user                              | see attendance statistics for studios                           | can understand the needs of my students better                                             |
| `*`      | user                              | hide irrelevant data                                            | can focus on the more relevant data                                                        |



### Use cases

(For all use cases below, the **System** is `JARVIS` and the **Actor** is `AVENGER`, unless specified otherwise)

####**Use case: UC5 - Delete a student**  
Preconditions:  There are existing students in JARVIS.  

**MSS**
1. Avenger <ins>requests to list students(UC3)</ins>.
2. JARVIS displays the list of students.
3. Avenger inputs command to delete a student.
4. JARVIS displays list of student after successfully deleting the task.  
    Use case ends.

**Extensions**
* 3a. JARVIS detects an error in the format of the input command.
  * 3a1. JARVIS requests user to re-input their command.
  * 3a2. Avenger inputs command.
  Steps 3a1-3a2 are repeated until the command entered is correct.  
  Use case resumes from step 4.


####**Use case: UC6 - Delete task**  
Preconditions: There are existing tasks in JARVIS.  

**MSS:**
1. Avenger <ins>requests to list task (UC4)</ins>.
2. JARVIS displays the list of tasks.
3. Avenger inputs command to delete a task.
4. JARVIS displays list of tasks after successfully deleting the task.  
   Use case ends.

**Extensions:**
* 3a. JARVIS detects an error in the format of the input command.
  * 3a1. JARVIS requests user to re-input their command.
  * 3a2. Avenger inputs command.  
    Steps 3a1-3a2 are repeated until the command entered is correct.  
    Use case resumes from step 4.

    
####**Use case: UC7 - Clear all students and tasks**
Preconditions: There are existing tasks and/or students in JARVIS.  
Guarantees: All students and tasks will be deleted.  

**MSS:**
1. Avenger inputs command to clear all data.
2. JARVIS requests for confirmation message.
3. Avenger confirms.
4. JARVIS display success message.

**Extensions:**
* 1a. JARVIS detects an error in the format of the input command.
  * 1a1. JARVIS requests user to re-input their command.
  * 1a2. Avenger inputs command.  
  Steps 1a1-1a2 are repeated until the command entered is correct.  
  Use case resumes from step 2.
* 2a. Avenger failed to provide confirmation message.
  * 2a1. Avenger input invalid confirmation.  
  Use case ends.







### Non-Functional Requirements

1.  Should work on Windows, Linux, and OS-X platforms that has version 11 of Java (i.e. no other Java versions) installed.
2.  Should work without requiring an installer and be packaged in a single jar file
3.  All data for the system should be stored locally in a human editable text file, and not be dependent on any remote server
4.  GUI should not cause any resolution-related inconveniences for standard screen resolutions (1920 x 1080 and higher) and screen scales 100% and 125%
5.  GUI should be usable (i.e. all functionality can be used, not necessarily optimally) for screen resolutions 1280 x 720 and higher, and for screen scales 150% 
6.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands over other means of input.
7.  The product is intended only for a single user (i.e. not a multi-user product)
8.  The system is not required to handle the actual grading of student's works  

*{More to be added}*

### Glossary
* **Mastery check**: An assessment where students in pairs must present what they have learnt in the module to their TA, and the TA will assess the students’ understanding of the concepts.
* **Avenger**: A teaching assistant (TA) who is responsible for teaching a studio class.
* **Studio**: A tutorial class with up to 8 students.
* **XP**: Points that will count towards a student’s grade
* **Source Academy**: Online platform used for CS1101S.
* **Mission**: Assignment on Source Academy
* **Quest**: Optional assignment on Source Academy

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
