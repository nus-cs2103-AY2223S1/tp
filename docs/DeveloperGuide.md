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

### \[Proposed\] Personalising the UI

#### Proposed Implementation

#### Design considerations:

### \[Proposed\] Remark field for entries

#### Proposed Implementation

#### Design considerations:

### \[Proposed\] Tag command

#### Proposed Implementation

#### Design considerations:

### \[Proposed\] Filter command

#### Proposed Implementation

#### Design considerations:

### \[Proposed\] Show command

#### Proposed Implementation

#### Design considerations:

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

* new financial advisor
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* does not like repetitive work
* prefers to have minimal traveling expenses
* likes motivational quotes

**Value proposition**: 

* manage contacts faster than a typical mouse/GUI driven app
* keep track of the user's contacts and meeting schedule easily, but communication with contacts is not covered.
* stay motivated to engage with his clients

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​  | I want to …​                                            | So that I can…​                                                        |
|----------|----------|---------------------------------------------------------|------------------------------------------------------------------------|
| `* * *`  | new user | see usage instructions                                  | refer to instructions when I forget how to use the App                 |
| `* * *`  | user     | add client information                                  |                                                                        |
| `* * *`  | user     | delete client information                               | remove entries that I no longer need                                   |
| `* * *`  | user     | modify a client entry easily                            | ensure that client information is up to date                           |
| `* * *`  | user     | filter my clients by name                               | locate details of clients without having to go through the entire list |
| `* *`    | user     | classify my clients with different tags                 | know who to prioritise                                                 |
| `* *`    | user     | filter my clients based on tags assigned to them        | access relevant client data easily                                     |
| `* *`    | user     | view clients from a certain demographic                 | get a better overview of what my client base is like                   |
| `* *`    | user     | get things done fast with minimal typing                | save time                                                              |
| `* *`    | user     | remember details that clients tell me about their lives | use them to form an emotional connection with clients                  |
| `* *`    | user     | keep track of my client's phone number and address      | contact them easily                                                    |
| `*`      | user     | sort clients by name                                    | locate a client easily                                                 |


### Use cases
(For all use cases below, the **System** is the `Rapportbook` and the **Actor** is the `user`, unless specified otherwise)
#### Use case: List contacts
1. User requests to list contacts.
2. Rapportbook shows a list of contacts.  
   Use case ends.

#### Use case: Add a contact

**MSS**

1. User requests to add a contact and provides details of contacts.
2. Rapportbook indicates that contact has been added.  
   Use case ends.

**Extensions**

* 1a. User does not input the required details for the contact.
    * Use case resumes at step 1.

#### Use case: Delete a contact

**MSS**

1. User [lists contacts](#use-case-list-contacts).
2. User requests to delete a specific contact in the list.
3. Rapportbook deletes the contact.  
   Use case ends.

**Extensions**

* 1a. The list is empty.
  Use case ends.

* 2a. The given index is invalid.
    * 2a1. Rapportbook shows an error message.
    * Use case resumes at step 2.

#### Use case: Edit a contact

**MSS**

1. User [lists contacts](#use-case-list-contacts).
2. User requests to edit a specific contact in the list by specifying fields and their updated values.
3. Rapportbook edits the contact based on what the user specified.  
   Use case ends.

**Extensions**

* 1a. The list is empty.  
  Use case ends.

* 2a. The given index is invalid.
    * 2a1. Rapportbook shows an error message. 
    * Use case resumes at step 2.
* 2b. No fields were specified
    * 2b1. Rapportbook shows an error message. 
    * Use case resumes at step 2.
* 2c. Updated values is not in the right format.
    * 2c1. Rapportbook shows an error message. 
    * Use case resumes at step 2.
* 2d. No index was specified but there is a [contact shown](#use-case-show-contact).
    * Use case resumes at step 3.
* 2e. No index was specified and there is no [contact shown](#use-case-show-contact).
    * Rapportbook shows an error message.
    * Use case resumes at step 2.

#### Use case: Filter contacts

**MSS**

1. User requests to filter contacts of a certain tag and/or name.
2. Rapportbook shows a list of contacts that matches his filter query.  
   Use case ends.

**Extensions**

* 1a. The tag specified does not exist.
	* 1a1. Rapportbook shows an error message.
	* Use case resumes at step 1.
* 1b. The name specified does not exist.
	* 1a1. Rapportbook shows an error message.
	* Use case resumes at step 1.

	Use case ends.

#### Use case: Clearing filters

**MSS**

1. User requests to clear a filter that was applied.
2. Rapportbook shows a list of contacts that without the filter applied.  
   Use case ends.

**Extensions**

* 1a. The tag specified does not exist.
	* 1a1. Rapportbook shows an error message.
	* Use case resumes at step 1.
* 1b. The name specified does not exist.
	* 1a1. Rapportbook shows an error message.
	* Use case resumes at step 1.

	Use case ends.

#### Use case: Show contact

**MSS**

1. User [lists contacts](#use-case-list-contacts).
2. User requests to show a specific contact in the list.
3. Rapportbook displays the contact in a separate panel for the user.  
   Use case ends. 

**Extensions**

* 1a. The list is empty.
  Use case ends.

* 2a. The given index is invalid.
    * 2a1. Rapportbook shows an error message.
    * Use case resumes at step 2.

#### Use case: Create tag(s)

**MSS**

1. User requests to create a certain tag or multiple tags.
2. Rapportbook creates the corresponding tag(s).
3. Rapportbook shows success message.
   Use case ends.

**Extensions**

* 2a. At least one of the specified tag(s) already exist.
    * 2a1. Rapportbook shows a message indicating that the tag(s) already exists.
    * 2a2. Rapportbook creates any tags that does not exist.
    * Use case resumes at step 3.

#### Use case: Add tag(s) to contact

**MSS**

1.  User [lists contacts](#use-case-list-contacts).
2.  User requests to tag a specific contact in the list with certain tag(s).
3.  Rapportbook shows success message.  
    Use case ends.

**Extensions**

* 1a. The list is empty.  
  Use case ends.

* 2a. The given index is invalid.
    * 2a1. Rapportbook shows an error message.
    * Use case resumes at step 2.
* 2b. At least one of the specified tag(s) do not exist.
    * 2b1. Rapportbook shows an error message.
    * Use case resumes at step 2.
* 2c. Contact already tagged with certain tag(s) specified but not all the tag(s) specified.
    * 2c1. Rapportbook tags the contact with the missing tag(s).
    * 2c2. Rapportbook shows a message indicating which tag(s) the contact already has and which tag(s) were added.  
      Use case ends.
* 2d. Contact already tagged with all tag(s) specified.
    * 2d1. Rapportbook shows a message indicating that no tags were added.  
      Use case ends.

#### Use case: Remove tag(s) from contact

**MSS**

1.  User [lists contacts](#use-case-list-contacts).
2.  User requests to remove certain tag(s) from a specific contact in the list.
3.  Rapportbook shows success message.  
    Use case ends.

**Extensions**

* 1a. The list is empty.  
  Use case ends.

* 2a. The given index is invalid.
    * 2a1. Rapportbook shows an error message.
    * Use case resumes at step 2.
* 2b. At least one of the specified tag(s) do not exist.
    * 2b1. Rapportbook shows an error message.
    * Use case resumes at step 2.
* 2c. Contact already has at least one of the tag(s) specified.
    * 2c1. Rapportbook shows a message indicating which specified tag(s) the contact already has.  
    * Use case resumes at step 2.

#### Use case: Request help manual
1. User requests help for application usage.
2. Rapportbook opens another window that contains a link to the help manual.  
   Use case ends.

#### Use case: Exit application
1. User requests to exit the application.
2. Rapportbook closes all windows and exits gracefully.  
   Use case ends.


### Non-Functional Requirements

**Constraints**
1. Rapportbook must be able to be packaged into a single executable JAR file that is less than 100MB.

**Data requirements**
1. Data should be stored locally and in a human editable text file.
2. No database managed system should be used.

**Technical requirements**
1. Rapportbook should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Rapportbook should work on 64-bit environments.
3. Rapportbook should work in an offline environment.
4. Rapportbook should work without requiring an installer.

**Performance requirements**
1. Rapportbook should be able to hold up to 1000 contacts without a noticeable delay in performance for typical usage.
2. Rapportbook should be able to load and store up to 1000 contacts without a noticeable delay in performance.
3. Rapportbook should be able to respond to user requests in less than 1 second when there are less than 1000 contacts.

**Process requirements**
1. Rapportbook development schedule:
    * **v1.1** by 29 September 2022, Thursday, Week 7
    * **v1.2** by 13 October 2022, Thursday, Week 9
    * **v1.3** by 27 October 2022, Thursday, Week 11
    * **v1.4** by 3 November 2022, Thursday, Week 12
2. Pull requests should be approved by at least one other team member before merging into the master branch on GitHub.
3. Rapportbook code should follow the Object-oriented paradigm primarily.

**Quality requirements**
1. A user with above average typing speed should be able to accomplish most tasks faster using commands than using the 
mouse. 
2. Rapportbook GUI should work well for standard screen resolutions 1920x1080 and higher, and for screen scales 100% and 
125%.
3. Rapportbook GUI should be usable for screen resolutions 1280x720 and higher, and, for screen scales 150%.

**Project scope**
1. Rapportbook is a single user product.
2. Rapportbook does not handle communication with clients.
3. Rapportbook does not send notifications to the user.

**Noteworthy points**
1. Rapportbook features should be easy to test.
2. Rapportbook should not rely on external APIs to work.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **Client**: A person that is using the services of our target user.
* **Contact**: A client profile containing their name, phone number, and other relevant information.
* **Field**: A piece of information in a contact (e.g. name, phone number).
* **Entry**: A contact.
* **Private contact detail**: A contact detail that is not meant to be shared with others.

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
