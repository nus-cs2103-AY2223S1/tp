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
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`).

### Autocomplete component
**API** : [`Autocomplete.java`](https://github.com/AY2223S1-CS2103T-W15-1/tp/blob/master/src/main/java/seedu/address/logic/autocomplete/Autocomplete.java)

**TODO: Add AutocompleteClassDiagram**

The `Autocomplete` component,
* stores the unique names in SoConnect.
* depends on some classes in the `Model` component (because the `Autocomplete` component gets a list of unique names from the objects that belong to the `Model`).

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Sorting feature

#### Implementation

The sorting mechanism is facilitated by `SortCommand` and `SortCommandParser`. 
Additionally, the mechanism utilises the following operations in `UniquePersonList`:

* `UniquePersonList#sortByName(Boolean isReverse)` — Sorts the contact list by name in alphabetical order.
* `UniquePersonList#sortByPhone(Boolean isReverse)` — Sorts the contact list by phone number in increasing order.
* `UniquePersonList#sortByEmail(Boolean isReverse)` — Sorts the contact list by email in alphabetical order.
* `UniquePersonList#sortByAddress(Boolean isReverse)` — Sorts the contact list by address in alphabetical order.
* `UniquePersonList#sortByTag(Tag tag, Boolean isReverse)` — Sorts the contact list by a specified tag.

These operations sort in reverse order when `isReverse` is true.

These operations are exposed in the `Model` interface under the same method name.

Given below is an example usage scenario and how the sorting mechanism behaves at each step.

Step 1. The user executes `sort t/!friend n/` command to perform a multi-level sort. `SortCommandParser` calls `ArgumentTokenizer#tokenizeToList()` to separate the parameters of `t/!friend` and `n/`.

Step 2. The `sort` command sorts the currently displayed list by name first, calling `Model#sortByName(Boolean isReverse)` where `isReverse = false`.

Step 3. The `sort` command sorts the currently displayed list by the `friend` tag next, calling `Model#sortByTag(Tag tag, Boolean isReverse)` where `isReverse = true`.

The following sequence diagram shows how the sort operation works:

(insert sequence diagram here)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `SortCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Step 4. The user is shown the sorted list. The sorted list contains the same contacts as the previous displayed list. It has two sections, the first section contains contacts without the `friend` tag and the second section contains contacts with the `friend` tag. Each section is sorted by name in alphabetical order.

The following activity diagram summarizes what happens when a user executes a sort command:

(insert activity diagram here)

#### Design consideration

**Aspect: How to implement multi-level sorting:**

* **Alternative 1 (current choice):** Sort the list once for each parameter entered by the user.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of time needed to sort.

* **Alternative 2:** Single complex sorting method that combines all the individual sorting for each parameter.
    * Pros: Save time as only 1 sorting operation is carried out.
    * Cons: Harder to modify when more parameters are added. Can result in more bugs due to complexity.

_{more aspects and alternatives to be added}_

### \[Proposed\] Undo/redo feature

#### Proposed implementation

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

### Tag adding feature

#### Implementation

The tag adding mechanism is facilitated by `TagAddCommand` and `TagAddCommandParser`.
Additionally, The mechanism utilises the following operations in `UniqueTagList` and `UniquePersonList`.

* `UniqueTagList#hasTag(Tag tag)` - Checks if the tagList has the tag.
* `UniquePersonList#setPerson(Person target, Person editedPerson)` - Sets the same person with the new tag.
* 

These operations are exposed in the `Model` interface under the same method name.

Given below is an example usage scenario and how the tag adding mechanism behaves at each step.

Step 1. The user executes `tag add 1 t/friend` command to add the tag, `friend`, to the person indicated by the `INDEX`, 1.
`TagAddCommandParser` calls  `ArgumentTokenizer#tokenizeToList()` to separate the parameters of `1` and `t/friend`.

Step 2. The `tag add` command collects the list of persons shown, containing them in `Model#getFilteredPersonList()`.

Step 3. The `tag add` command checks if the index is valid, calling `Index#getZeroBased()`.

Step 4. The `tag add` command receives the person indicated by the index, containing it in `List#get(int index)`.

Step 5. The `tag add` command creates the same person with the new tag included, containing it in `TagAddCommand#createEditedPerson(Person personToEdit, Tag tag)`.

Step 6. The `tag add` command replaces the old person with the new, updated person, calling `Model#setPerson(Person target, Person editedPerson)`.

The following activity diagram summarizes what happens when a user executes a tag add command:

(insert activity diagram here)

#### Design consideration

**Aspect: How to implement tag add:**

* **Alternative 1 (current choice):** Creates a new `Person` with the tag included.
    * Pros: Prevents direct access into the tags of a `Person`.
    * Cons: Potential error occurs if some form of duplication is allowed.

* **Alternative 2:** Directly add the tag into the `Person` .
    * Pros: Easy to implement.
    * Cons: Easy to access into the tags of a `Person`. Could cause accidental bugs.

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### Customise Order Feature

#### Implementation

This feature is mainly implemented by the `CustomiseCommand` and `PersonCard` classes. The following methods are the main ones:

* `CustomiseCommand#changeAttributeOrder(Model model)` - saves the new attribute order in the `preferences.json` file.
* `PersonCard#setAttributes()` - reads the order from the `preferences.json` file and builds the `PersonCard` in the order specified.

_{diagrams and more in depth explanation to be added}_

#### Design consideration
* **Alternative 1 (current choice):** Sets the order in 4 placeholder JavaFX `FlowPane`.
  * Pros: Easy to implement.
  * Cons: Unable to have different styling for different attributes.

* **Alternative 2:** Have 24 different FXML files and choose the one with the required order.
  * Pros: Easy to implement and can have different styling for different attributes.
  * Cons: Hard to maintain and make changes.

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

### Product scope

**Target user profile**: NUS SoC Student

* has a need to manage a significant number of contacts (from NUS modules and co-curricular activities)
* prefer desktop apps over other types (easy access to laptop for NUS/SoC modules)
* can type fast (from SoC coding modules)
* prefers typing to mouse interactions (from SoC coding modules)
* is reasonably comfortable using CLI apps (from SoC coding modules)

**Value proposition**:

* manage contacts faster than a typical mouse/GUI driven app
* organise and separate their school contacts from personal contacts
* practice and train their typing speed
* increase their familiarity with using CLI tools


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …                  | I want to …                                            | So that I can…                                                             |
| -------- | ----------------------- | ------------------------------------------------------ | -------------------------------------------------------------------------- |
| `* * *`  | new user                | see usage instructions                                 | refer to instructions when I forget how to use the App                     |
| `* * *`  | user                    | add a new contact                                      |                                                                            |
| `* * *`  | user                    | delete a contact                                       | remove entries that I no longer need                                       |
| `* * *`  | user                    | view all my contacts                                   | keep track of all my contacts                                              |
| `* *`    | user                    | edit a contact                                         | update it or correct mistakes                                              |
| `* *`    | user                    | clear all my contacts                                  | save time deleting them one by one                                         |
| `* *`    | user                    | create a new tag                                       | categorise my contacts                                                     |
| `* *`    | user                    | delete a tag                                           | remove redundant tags                                                      |
| `* *`    | user                    | edit a tag                                             | provide better naming                                                      |
| `* *`    | user                    | tag a contact                                          | add it to a category                                                       |
| `* *`    | user                    | untag a contact                                        | remove it from a category                                                  |
| `* *`    | user                    | search using a name, phone number, email or address    | find a contact easily without reading through the list                     |
| `* *`    | user                    | search with a tag                                      | find groups of contacts that share a common tag                            |
| `* *`    | user                    | search with multiple tags                              | narrow my search results to only contacts that have all the specified tags |
| `* *`    | user                    | search with multiple tags                              | broaden my result results to contacts that have any of the specified tags  |
| `* *`    | user                    | view contacts related to my search query               | find contacts even when I mistype their name                               |
| `* *`    | user                    | hide private contact details                           | minimize chance of someone else seeing them by accident                    |
| `* *`    | user                    | show private contact details                           | view them when I need to                                                   |
| `* *`    | user                    | have an autocomplete for the names that I am searching | search faster by names and minimize the chance of an unsuccessful search   |
| `* *`    | user                    | have an autocomplete for the tags that I am searching  | search faster by tags and minimize the chance of an unsuccessful search    |
| `* *`    | user with many contacts | specify the default order of my contacts               | avoid re-sorting the list everytime                                        |
| `* *`    | user with many contacts | sort contacts by name, email, phone number, or address | organise my contacts list                                                  |
| `* *`    | user with many contacts | sort contacts according to tags                        | view contacts with a specified tag before other contacts                   |
| `* *`    | user with many contacts | sort contacts by multiple fields                       | organise my contacts list with greater degree                              |
| `*`      | user with many contacts | mark some contacts as my favourites                    | spot them easily among other contacts                                      |
| `*`      | user with many contacts | sort favourite contacts before others                  | see them before other contacts                                             |
| `*`      | user                    | change the order of information for contacts           | view more important information before others                              |
| `*`      | user                    | customise the theme of the app                         | adjust it to my comfort and liking                                         |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `SoConnect` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  SoConnect shows a list of persons
3.  User requests to delete a specific person in the list
4.  SoConnect deletes the person

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. SoConnect shows an error message.

      Use case resumes at step 2.

**Use case: Search contacts**

**MSS**

1.  User requests to search a specific word in the list
2.  SoConnect shows a list of persons related to the word.

    Use case ends.

**Extension**
* 1a. The list is empty.

  Use case ends

* 3a. There is no input after search.

    * 3a1. SoConnect shows the same list of persons.

      Use case ends.

**Use case: Edit a tag**

**MSS**

1.  User requests for list of tags.
2.  SoConnect shows the list of tags.
3.  User changes a tag.
4.  SoConnect updates the tag.

    Use case ends.

**Extensions**

* 1a. There are no tags.

  Use case ends.

* 3a. There is no such current tag.

    * 3a1. SoConnect shows an error message.

      Use case resumes at step 2

* 3b. The new tag already exist.

    * 3b1. SoConnect shows an error message.

      Use case resumes at step 2.

**Use case: Add tag to Contact**

**MSS**

1.  User requests for the contact.
2.  SoConnect gives the contact.
3.  User requests to add tag to the contact.
4.  SoConnect adds the tag to the contact.

    Use case ends.

**Extensions**

*  1a. There is no such name in the contacts.

    * 1a1. SoConnect shows an error message.

      Use case resumes at step 1.

*  3a. There is no such tag in the taglist.

    * 3a1. SoConnect shows aan error message.

      Use case resumes at step 2.

**Use case: Autocomplete a word**

**MSS**

1.  User inputs word
2.  SoConnect gives a selection of possible words.
3.  User chooses the right word.
4.  SoConnect changes inputted word to the chosen word.

    Use case ends.

**Extension**
*  1a. The word changes.

    * 1a1. SoConnect updates the selection of possible words.

      Use case resumes at step 3.

**Use case: Sort by name**

**MSS**

1.  User enters command to sort the contact list by name.
2.  SoConnect sorts the list by name and displays the new list.

    Use case ends.

**Use case: Hide all contact's phone number**

**MSS**

1.  User requests phone number to be hidden.
2.  SoConnect removes phone number from displaying in the contact.

    Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be for a single user i.e. (not a multi-user product).
5.  Should have its data stored locally and the data should be in a human editable text file.
6.  Should not use a Database Management System for data storage.
7.  Should work without requiring an installer.
8.  Should not depend on a remote server.
9.  Should not cause any resolution-related inconveniences to the user.
10. Should be packaged in a single JAR file and its size should not exceed 100MB.
11. Should not have hard-to-test features or features that make the product hard-to-test i.e. (Features that require creating user accounts, login, logout etc., audio-related features and Features that depend heavily on remote APIs)

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: A text-based user interface used to run programs
* **GUI**: A graphical user interface (GUI) is a form of user interface that allows users to interact with programs through graphical icons and audio indicator
* **JavaFX**: A Java library used to develop client applications
* **kLoC**: Stands for thousands of lines of code
* **NUS**: National University of Singapore
* **SoC**: School of Computing, a computing school in NUS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Autocomplete**: A feature that shows a list of completed words or strings without the user needing to type them in full

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
