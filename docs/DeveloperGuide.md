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

    :bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103T-W11-3/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-W11-3/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-W11-3/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-W11-3/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `CustomerListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-W11-3/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-W11-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Customer` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-W11-3/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a customer).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCustomerCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCustomerCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-W11-3/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the art buddy data i.e., all `Customer` objects (which are contained in a `UniqueCustomerList` object).
* stores all `Commission` objects within the active `Customer` (which are contained in a `UniqueCommissionList` object).
* stores the currently 'selected' `Customer` as an `ObservableObject<Customer>` which lets us listen to changes to the selected customer for performing UI updates.
* stores the current 'filtered' `Customer` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Customer>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the current list of `Commission` objects belonging to the selected `Customer` (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Commission>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list changes.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Customer` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Customer` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-W11-3/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

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

Step 2. The user executes `delete 5` command to delete the 5th customer in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new customer. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the customer was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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
  * Pros: Will use less memory (e.g. for `delete`, just save the customer being deleted).
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

* An artist who works through commissions
* has a need to manage clients and commissions
* wants to review past commission works and feedbacks
* wants to market himself to the right clients
* prefers desktop apps over other types
* is reasonably comfortable using CLI apps
* prefers typing to mouse interactions

**Value proposition**:
Allows artists to hone their craft systematically and tune their artworks to fit the taste of their clients which ultimately builds their reputation.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                 | I want to …​                                                              | So that I can…​                                               |
|----------|---------------------------------------------------------|---------------------------------------------------------------------------|---------------------------------------------------------------|
| `* * *`  | artist                                                  | exit from the program                                                     |                                                               |
| `* * *`  | artist                                                  | get a list of all my commissions of one specific customer                 |                                                               |
| `* * *`  | new user                                                | get help                                                                  | know how to use the product                                   |
| `* * *`  | artist eager to improve his craft                       | review past feedback given by customers                                   | better the quality of my subsequent works                     |
| `* * *`  | artist                                                  | delete commissions                                                        |                                                               |
| `* * *`  | artist                                                  | view uploaded images of my previous commissions                           |                                                               |
| `* * *`  | artist                                                  | upload images of art                                                      |                                                               |
| `* * *`  | artist                                                  | create a customer entry                                                   |                                                               |
| `* * *`  | artist with loyal customers                             | see customer's repeated payment / commissions                             | start a loyalty program to encourage their purchases          |
| `* *`    | artist                                                  | prioritize my commissions                                                 | keep track of the most urgent commissions                     |
| `* *`    | artist                                                  | categorize the artstyle of my commissions                                 |                                                               |
| `* *`    | artist                                                  | sort my commissions by due date                                           | easily access all commissions with pressing due dates         |
| `* *`    | artist                                                  | search for commissions by date                                            |                                                               |
| `* *`    | artist                                                  | search for commissions by type                                            |                                                               |
| `* *`    | artist                                                  | search for commissions by name                                            |                                                               |
| `* *`    | artist who can offer multiple kinds of commissions      | categorize the commissions                                                | better analyse the commissions.                               |
| `* *`    | artist who has similar customers                        | categorize them into groups                                               | systematically review their feedback and apply the takeaways. |
| `* *`    | artist who works traditionally                          | retain iterations of my work from the sketch phase to the final rendering |                                                               |
| `* *`    | artist who creates non-commissioned works               | determine potential customers who might be interested to purchase them    |                                                               |
| `* *`    | busy artist with several commissions ongoing            | track the deadlines of each commission                                    | ensure my work is delivered on time                           |
| `* *`    | busy artist with several commissions ongoing            | track my progress in each of them                                         |                                                               |
| `* *`    | artist keen to make my hobby a side hustle              | determine the type of commissions that have proven lucrative              | focus my efforts into these genres                            |
| `* *`    | artist                                                  | update images of art                                                      | re-upload the image if I uploaded the wrong file              |
| `* *`    | artist                                                  | calculate my monthly income                                               |                                                               |
| `*`      | artist who may receive multiples of the same commission | duplicate my commissions                                                  | not have to manually enter the same information every time    |
| `*`      | long-time user                                          | hide/archive old commissions                                              | not be distracted by too much information                     |
| `*`      | artist                                                  | store image references used when drawing the commission                   |                                                               |
| `*`      | artist                                                  | clear all existing commissions                                            |                                                               |
| `*`      | artist                                                  | search for commissions by customers                                       |                                                               |
| `*`      | artist                                                  | search for commissions by multiple types(intersect)                       |                                                               |
| `*`      | artist                                                  | search for commissions by multiple types(union)                           |                                                               |
| `*`      | artist who works on multiple devices                    | transfer my application data from one device to the other                 |                                                               |

### Use cases

(For all use cases below, the **System** is `ArtBuddy`
and the **Actor** is the `user`, unless specified otherwise)

**Use case: View a Customer**

**MSS**

1. User requests to list customers
2. ArtBuddy shows a list of customers
3. User requests to open customer in the list
4. ArtBuddy shows the list of commissions the customer made and the details of the customer

    Use case ends.


**Extensions**

* 1a. The customer list is empty.

    Use case ends.
* 2a. The given index is invalid.

    * 2a1. ArtBuddy shows an error message.

      Use case ends.

**Use case: Add a customer**

**MSS**

1. ArtBuddy shows a list of customers
2. User requests to add a customer and his or her details to the list
3. ArtBuddy shows the updated list of customers

    Use case ends.

**Extensions**

* 2a. Necessary customer details not given.

    * 2a1. ArtBuddy shows an error message.

      Use case ends.

**Use case: Delete a commission**

**MSS**

1. ArtBuddy shows the list of commissions from the opened customer
2. User requests to delete a specific commission in the list
3. ArtBuddy deletes the commission and updates the list displayed

   Use case ends.

**Extensions**

* 1a. The commission list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. ArtBuddy shows an error message.

      Use case ends.

**Use case: Add a commission**

**MSS**

1. ArtBuddy shows the list of commissions from the opened customer
2. User requests to add a commission and its related details to the list
3. ArtBuddy adds the commission and updates the list displayed

   Use case ends.

**Extensions**

* 2a. Necessary commission details not given.

    * 2a1. ArtBuddy shows an error message.

      Use case ends.

**Use case: Delete a Customer**

**MSS**

1.  User requests to list customers
2.  ArtBuddy shows a list of customers
3.  User requests to delete a customer in the list
4.  ArtBuddy warns the user that all commissions under the customer will be deleted, and confirms whether the user still wants to proceed
5.  User confirms.
6.  ArtBuddy deletes the customer

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ArtBuddy shows an error message.

      Use case resumes at step 2.

* 4a. The user chooses not to proceed with the deletion.

  Use case ends.

**Use case: Open a commission**

**Preconditions: AB currently has a customer open.**

**MSS**
1. User enters a command to open a specific commission.
2. AB shows the commission’s details.

    Use case ends.

**Extensions**

* 1a. AB detects an invalid command format.
  * 1a1. AB displays an error message informing User of the command format.

    Use case ends.

* 1b. AB detects the selected commission does not exist.
  * 1b1. AB displays an error message.

    Use case ends.

**Use case: Add iteration to a commission**<br>
**Guarantees:**
* An iteration, with an associated image, will be added to the specified commission only if its creation is successful
* The new iteration will not be created if the image provided is not valid (e.g. it does not exist, or it is not
  of the correct image format)

**MSS:**
1. User <u>opens a commission they want to edit (Open Commission)</u>.
2. User enters the command to add an iteration to the commission selected, specifying the image to use for the
   iteration using an absolute file path.
3. AB creates a new iteration for the commission, associated with the image specified.
4. AB updates the GUI to also show the new iteration.

   Use case ends.

**Extensions:**
* 2a. The user enters a command that AB does not recognise.
    * 2a1. AB shows an error message, and asks the user to enter a correct command.
    * 2a2. User enters a new command.

      Steps 2a1 and 2a2 are repeated until the command is correctly entered.

      Use case resumes from step 3.

* 2b. The specified file path the user specifies is invalid or does not exist.
    * 2b1. AB shows an error message, and asks the user for a new command with a valid file path that exists.
    * 2b2. User enters a new command.

      Steps 2b1 and 2b2 are repeated until the command is correctly entered.

      Use case resumes from step 3.

* 2c. The specified file under the file path is not a valid file type.
    * 2c1. AB lets the user know that the file is not supported, and requests for a new command
      with a file of support file types.
    * 2c2. User enters a new command.

      Steps 2c1 and 2c2 are repeated until the command is correctly entered.

      Use case resumes from step 3.

**Use case: Delete an iteration of a commission<br>
Preconditions: AB currently has a commission opened.<br>**

**MSS:**
1. Artist enters a command to delete a specified iteration of the commission.
2. AB deletes the specified iteration and updates the GUI.
   Use case ends.

**Extensions:**
* 1a. The user enters a command that AB does not recognise.
    * 1a1. AB shows an error message, and asks the user to enter a correct command.
    * 1a2. User enters a new command.<br>
      Steps 1a1 and 1a2 are repeated until the command is correctly entered.<br>
      Use case resumes from step 2.

* 1b. The user tries to delete an iteration that does not exist.
    * 1b1. AB shows an error message and asks the user to specify an iteration that exists.
    * 1b2. User enters a new command.<br>
      Steps 1b1 and 1b2 are repeated until the command is valid.<br>
      Use case resumes from step 2.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 500 customers, 2000 commissions and 8000 iterations without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. The app should be designed for and used by a single user.
5. The app data should be stored in a human-editable text file and not a DBMS.
6. Images and other app assets should be stored locally and not in a remote server.
7. The app should be well tested with a coverage of above 65% to allow bugs to be found more easily.
8. The product should be usable on all standard screen resolutions above 1280x720.
9. The product should be packaged within a single JAR file with a filesize of up to 100MB.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Customer**: A contact detail. Contains information about the customer, and a list of commissions.
* **Commission**: An art piece requested by a customer that has been delivered or is in progress. Contains specifics about the commission and a list of iterations.
* **Iteration**: A single version of a commission. Contains an image and a text comment on the image.

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

### Deleting a customer

1. Deleting a customer while all customers are being shown

   1. Prerequisites: List all customers using the `list` command. Multiple customers in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No customer is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
