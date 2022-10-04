---
layout: page

title: Developer Guide

---
* Table of Contents
  * Acknowledgements
  * Setting up, getting started
  * Design
    * Architecture
    * UI component
    * Logic component
    * Model component
    * Storage component
    * Common classes
  * Implementation (kiv)
  * Documentation, logging, testing, configuration, dev-ops
  * Appendix: Requirements
    * Product scope
    * User stories
    * Use cases
    * Non-Functional Requirements
    * Glossary
  * Appendix: Instructions for manual testing
    * Launch and shutdown
    * Deleting a guest
    * Saving data


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

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `GuestListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Guest` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `GuestBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a guest).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `GuestBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `GuestBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the guest book data i.e., all `Guest` objects (which are contained in a `UniqueGuestList` object).
* stores the currently 'selected' `Guest` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Guest>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `GuestBook`, which `Guest` references. This allows `GuestBook` to only require one `Tag` object per unique tag, instead of each `Guest` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both guest book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `GuestBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.guest.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedGuestBook`. It extends `GuestBook` with an undo/redo history, stored internally as an `guestBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedGuestBook#commit()` — Saves the current guest book state in its history.
* `VersionedGuestBook#undo()` — Restores the previous guest book state from its history.
* `VersionedGuestBook#redo()` — Restores a previously undone guest book state from its history.

These operations are exposed in the `Model` interface as `Model#commitGuestBook()`, `Model#undoGuestBook()` and `Model#redoGuestBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedGuestBook` will be initialized with the initial guest book state, and the `currentStatePointer` pointing to that single guest book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th guest in the guest book. The `delete` command calls `Model#commitGuestBook()`, causing the modified state of the guest book after the `delete 5` command executes to be saved in the `guestBookStateList`, and the `currentStatePointer` is shifted to the newly inserted guest book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new guest. The `add` command also calls `Model#commitGuestBook()`, causing another modified guest book state to be saved into the `guestBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitGuestBook()`, so the guest book state will not be saved into the `guestBookStateList`.

</div>

Step 4. The user now decides that adding the guest was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoGuestBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous guest book state, and restores the guest book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial GuestBook state, then there are no previous GuestBook states to restore. The `undo` command uses `Model#canUndoGuestBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoGuestBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the guest book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `guestBookStateList.size() - 1`, pointing to the latest guest book state, then there are no undone GuestBook states to restore. The `redo` command uses `Model#canRedoGuestBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the guest book, such as `list`, will usually not call `Model#commitGuestBook()`, `Model#undoGuestBook()` or `Model#redoGuestBook()`. Thus, the `guestBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitGuestBook()`. Since the `currentStatePointer` is not pointing at the end of the `guestBookStateList`, all guest book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire guest book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the guest being deleted).
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

* hotel manager who want to keep track of guests
* has a need to manage a significant number of guests
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: 
* To know when the guests checkin and checkout
* To know where the guests book the hotel from
* To note down all the requirements/ requests that the guests asked for
* To note down the status of the room, if the room is cleaned or not cleaned



### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                  | I want to …​                                                     | So that I can…​                                                            |
|----------|--------------------------|------------------------------------------------------------------|----------------------------------------------------------------------------|
| `* * *`  | first time hotel manager | know the available commands                                      | use the application fully                                                  |
| `* * *`  | hotel manager            | view a list of all the guests                                    | browse all my guests' data at once                                         |
| `* * *`  | hotel manager            | view the contact number of the guest                             | contact them in an emergency                                               |
| `* * *`  | hotel manager            | view the name of the guest                                       | verify the guest                                                           |
| `* * *`  | hotel manager            | view the email address of the guest                              | contact the guest via email                                                |
| `* * *`  | hotel manager            | be able to exit the program                                      | close it when I don't need to use it                                       |
| `* * *`  | first time hotel manager | be able to delete data of all guests at one go                   | have a clean slate to work on should I need to                             |
| `* * *`  | hotel manager            | view the checkin date and time of a guest                        | prepare the room for the incoming guests                                   |
| `* * *`  | hotel manager            | view the checkout date and time of a guest                       | prepare when to clean the room                                             |
| `* * *`  | hotel manager            | delete a guest after they check out of my hotel                  | do not keep unneeded data on my guests                                     |
| `* * *`  | hotel manager            | be able to save the details of the guests                        | the data is not lost between sessions                                      |
| `* * *`  | hotel manager            | search guests by keyword                                         | see details of specific guests                                             |
| `* * *`  | hotel manager            | view the number of guests in each room                           | prepare proper bed suites                                                  |
| `* * *`  | hotel manager            | add the details of a new guest when they are staying at my hotel | know who is staying at my hotel                                            |
| `* *`    | hotel manager            | be able to edit the name of the guest                            | update it if there are any changes                                         |
| `* *`    | hotel manager            | be able to edit the email address of the guest                   | update it if there are any changes                                         |
| `* *`    | hotel manager            | be able to edit the number of guests                             | update it if there are any changes                                         |
| `* *`    | hotel manager            | be able to edit when a guest is checking in                      | update it if there are any changes                                         |
| `* *`    | hotel manager            | be able to edit when a guest is checking out                     | update it if there are any changes                                         |
| `* *`    | hotel manager            | be able to edit the contact number of the guest                  | update it if there are any changes                                         |
| `* `     | hotel manager            | get the details of what the IC number of guests is               | ensure our hotel safety                                                    |
| `* `     | hotel manager            | get information about how many rooms are cleaned                 | decide to let new guests in                                                |
| `* `     | hotel manager            | get the details of where the guests booked their hotel stay from | decide which booking platform I need to pay more attention to              |
| `* `     | hotel manager            | get the details of the bill of the hotel guest                   | easily report the tax bill to the government with less chances of mistakes |
| `* `     | hotel manager            | be able to add to the bill of the hotel guest                    | monitor the hotel incoming easily.                                         |
| `* `     | hotel manager            | be able to deduct from the bill of the hotel guest               | monitor the hotel incoming easily as well                                  |
| `* `     | breakfast manager        | get the details of how many guests are eating breakfast          | prepare the appropriate amount of food                                     |
| `* `     | lunch manager            | get the details of how many guests are eating lunch              | prepare the appropriate amount of food                                     |
| `* `     | dinner manager           | get the details of how many guests are eating dinner             | prepare the appropriate amount of food                                     |
| `* `     | hotel cleaner            | get the details of if a room has been cleaned                    | do not waste time cleaning the room again                                  |
| `* `     | hotel cleaner            | mark a room as clean                                             | other cleaners will not need to waste time cleaning the room again         |
| `* `     | butler                   | get the details of any special requests from the guests          | serve every guest properly                                                 |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `GuestList` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a guest**

**MSS**

1. User searches for guest 
2. Guest is not found in list of guests 
3. User requests to add a guest to the list
4. GuestList adds the guest
    
    Use case ends.

**Extensions**
* 1a. The guest is found in the list of guests.

    Use case ends.

* 3a. The inputted fields are invalid

  * 3a1. GuestList shows an error message.
  
    Use case resumes at step 2.
  
**Use case: Delete a guest**

**MSS**

1.  User requests to list guests
2.  GuestList shows a list of guests
3.  User requests to delete a specific guest in the list
4.  GuestBook deletes the guest

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. GuestList shows an error message.

      Use case resumes at step 2.

**Use case: Get list of all guests**

**MSS**

1.  User requests to list guests
2.  GuestList shows a list of guests

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case: Exit the program**

**MSS**

1. User requests exit the program
2. GuestList shows an exit message
3. GuestList exits the program

    Use case ends.






### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 guests without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. The response to any use action should become visible within 5 seconds.
5. The number of guests a user enters should not exceed 4.
6. The guests should have a check-out date.
7. The guest cannot stay for a period longer than 1 year.
8. Data should be saved into a JSON file before exiting the program.
9. The project is expected to adhere to a schedule that delivers a feature set every two weeks.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Number of guests**: Refers to the total number of people staying in the hotel room
* **Guest**: Refers to the guest who booked the hotel room.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample guests. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a guest

1. Deleting a guest while all guests are being shown

   1. Prerequisites: List all guests using the `list` command. Multiple guests in the list.

   1. Test case: `delete 1`<br>
      Expected: First guest is deleted from the list. Details of the deleted guest shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No guest is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
