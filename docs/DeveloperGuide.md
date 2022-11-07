---
layout: page
title: Developer Guide
---

## **Welcome to FABook!**

**FABook** is a dependable assistant for financial advisers by **reminding** them of meetings and consolidating **crucial information** like financial plans and client information right at their fingertips!

Financial advisers now focus on giving their full attention to their clients without having to worry about things slipping their mind.

**FABook** is optimized for a **one-stop storage solution** to store and remind them on **everything** they need to know about their client.

### What is this guide for?

This developer guide is intended for readers to understand the inner workings of how **FABook** works and the design process behind different implementations of features.
This guide will go into detail about the architecture used to build FABook as well as several notable features.

### Who is this guide for?

The intended target audience of this guide is for
* Developers looking to morph or evolve **FABook** for different or improved uses
* Developers looking to understand the architecture and follow the design to build the system
* Maintainers who are looking to understand how the system was built in order to be able to perform any enhancement or reengineering work.

<div style="page-break-after: always;"></div>

### How to use this guide?

#### Recommended prerequisites
To fully utilise and understand this guide, you should have knowledge and expertise in the following:
* Java
* UML diagrams

#### Organization
The guide provides a top-down view of the system by first providing an overview of the entire system before looking in greater detail of each component of the architecture.
Afterwards, it talks about notable implementations within the components. Details mentioned may include:
* Implementation
* Example Usage
* Design
* Design Considerations
* Related Features

You may refer to the [table of contents](#table-of-contents) below to quick jump to any particular section.

#### Legend
* Text in [blue](#legend) are hyperlinks that direct you to the relevant section of the page or to other websites
* Text in **bold** are used to emphasize important details to look out for or to distinguish headers from the rest of the text
* Text in `code snippets such as this` are used to show program or code related or used in FABook

<div markdown="span" class="alert alert-primary">

:bulb: **Tips**

</div>

<div markdown="span" class="alert alert-info">

:information_source: **Notes**

</div>

--------------------------------------------------------------------------------------------------------------------

## **Table of Contents**

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* **FABook** is built upon [AddressBook Level-3](https://nus-cs2103-ay2223s1.github.io/tp/) as a foundation
* The feature Undo and Redo was reused with minimal changes from a Tutorial called [Implementing Undo and Redo With The Command Design Pattern by ArjanCode](https://youtu.be/FM71_a3txTo).
* The feature open and add PDF file was reused with minimal changes from a code from stackoverflow [Open PDF file on the fly](https://stackoverflow.com/questions/2546968/open-pdf-file-on-the-fly-from-a-java-application)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

<div style="page-break-after: always;"></div>

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
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `CreateCommand`) which is executed by the `LogicManager`.
   1. `UndoableCommand` is another interface that extends from the `Command` interface which represents commands that have undo/redo logic.
   2. Commands that implements `UndoableCommand` are managed in the `CommandManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command (an undoable command)](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:
 f
<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `CreateCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `CreateCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `CreateCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagramV2.png" width="450" />

The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

#### Person Enhancements

##### Enhancement 1 : Net Worth
The person model now contains a `Net Worth` field.
`Net Worth` is implemented as a class where it contains a `final string value`, `final static string`
`MESSAGE_CONSTRAINTS` and `final static string` `VALIDATION_REGEX`.

- Net Worth accepts a string that has an immutable value.
- Net Worth is a non-compulsory field. A user will only engage with a client if they know their net worth.
- Net Worth `VALIDATION_REGEX` ensures that only Net Worth inputs that begins with a $ sign and has more than 4 digits are accepted.

**Design Considerations**
* **Choice 1 (current choice) : Compulsory Net Worth field in person and `VALIDATION_REGEX` calculated in dollars and must be more than 4 digits.**
    * Pros: Standardisation of currency and minimum net worth.
    * Cons: Unable to create a contact without knowing the client's net worth and net worth must be more than a
      minimum amount.

* **Choice 2 : Non-compulsory Net Worth field and `VALIDATION_REGEX` has no currency constraints nor minimum amount.**
    * Pros: Flexibility in creating a contact.
    * Cons: No means of comparison between a contact of different currency.

##### Enhancement 2 : Tag
The person model now contains a `Tag` field.
`Tag` is implemented as a class where it contains a `final string value`, `final static string`
`MESSAGE_CONSTRAINTS` and `final static string` `VALIDATION_REGEX`.
- Tag field's `VALIDATION_REGEX` ensures that only `Tag` values of `SECURED` or `POTENTIAL` are accepted.
- Tag is implemented as a Set which means a Person object can have more than one tag.

**Improvements**
- Due to limitations we were not able to set an ideal 1 tag constraint to the tag field since the logic behind the 2 tags are mutually exclusive.
- A check should be made to ensure that a person only has one tag to prevent one client from having both mutually exclusive tags.

##### Enhancement 3 : FilePath
Find out more information at [FilePath](#opening-of-person-specific-pdf-files).

##### Enhancement 4 : MeetingTimes
Find more information at [MeetingTimes](#meeting-feature).

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagramV2.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Opening of Person specific PDF files

Implementation of the opening PDF file of client feature is built on top of the way that the PDF filepath is stored.

#### Implementation

The mechanism is facilitated by `SetPersonFileCommand` and `FileUtil`.

`SetPersonFileCommand` extends from `Command`. It overwrites the `Command#execute()` method to determine if the given filepath is valid and create an "EditedPerson" `Person` object.

The `FileUtil#checkValidPdfFilePath()` method is used in `SetPersonFileCommand` to check the validity of the file path. The method follows the given filePath to check if the file exists and if the given filepath is of type `.pdf`.

Given below is an example usage scenario and how the set file mechanism behaves at each step.

Step 1. The user launches the application for the first time.

Step 2. The user decides to add a file path to  the 2nd person in the current list of contacts in the address book using the `filepath 2 f/C:\Users\Eugene Tay\Downloads\Tutorial_8_qns.pdf` command.

Step 3. `LogicManager` calls `LogicManager#execute()` method that was implemented from `Logic` interface. Within the method, `AddressBookParser#parseCommand()` is called on the user input `filePath` and a `Command` object is returned.

Step 4. `AddressBookParser::parseCommand` will parse the user input and match the `COMMAND_WORD` to be `filepath` which is `SetPathFileCommand#COMMAND_WORD` `SetPathFileCommandParser` object is instantiated which parses and checks the user arguments.

Step 5. `SetPathFileCommandParser` object parses the given user arguments. If the `PREFIX_FILEPATH` is present and there is no `IllegalValueException`, a `SetPersonFileCommand` object is instantiated with `index` and `filePath` obtained from the user arguments.

Step 6. `SetPathFileCommand#execute()` method is called in `LogicManager`. This method will check if the given PDF filepath is valid with `FileUtil#checkValidPdfFilePath()` method.

Step 7. `SetPathFileCommand#execute()` method will instantiate a new `Person` object with the original `Person` object's attributes and the new filePath. This `Person` object be set in `Model` and updated in `filterPersonList`.

The following sequence diagram shows how the assigning filepath to a client works up till the setPersonFileCommand.
![SetPersonFileSequenceDiagram](images/DeveloperGuide/FilePath/FilePathSequenceDiagram.png)

The next sequence diagram shows how setPersonFileCommand works.
![SetPersonFileSequenceDiagram](images/DeveloperGuide/FilePath/FilePathExecute().png)

The following activity diagram shows how the assigning filepath to a client feature works.
![SetPersonFileActivityDiagram](images/DeveloperGuide/FilePath/FilePathActivityDiagram.png)

#### Design Considerations:
**Choice 1 (Current Choice) : Store PDF's absolute path.**
  * Pros:
    * Absolute path of the PDF would mean that changes to the file location of User's FABook will not affect the ability to open PDF.
  * Cons:
    * Changing file location of PDF will render stored filepath useless.

**Choice 2 : Store PDF files of clients in a folder.**
  * Pros:
    * Users do not need to input absolute path as it is more technical than relative paths.
  * Cons:
    * Changes to the file location of User's FABook will mean that the PDF cannot be opened anymore since it is relative to the filepath of FABook.

### Meeting Feature
`MeetingTime` is used to model a meeting with a client.

#### Implementation
`MeetingTime` stores meetings primarily as a `value`, `displayValue` and `date`.

* `value` of type `String`: Used to for storing `MeetingTime` as a Json Object
* `displayValue` of type `String`: Used for displaying `MeetingTime` on `MeetingCard`, `PersonCard` and `PersonProfile`
* `date` of type `java.time.LocalDateTime`: Used for sorting `MeetingTime`s for `MeetingsWindow` and `SyncCommand`

#### Design Considerations
**Aspect: MeetingTime Object**
* **Choice 1 (current choice): Person object contains its own set of MeetingTime**
  * Pros: Easier to implement given the short amount of time
  * Cons: Harder to sort/filter/manipulate
* **Choice 2 (ideal choice): Bidirectional navigation between MeetingTime and Person**
  * Pros: Better abstraction therefore easier to perform MeetingTime specific tasks(e.g. sort, filter)
  * Cons: More complicated to implement

#### Related Features                                  
* [Sync Meetings feature](#sync-meetings-feature)        
* [Upcoming Meetings feature](#upcoming-meetings-feature)

### Sync Meetings feature

#### Implementation
The Sync Meetings feature is facilitated by the following operation:

* `Person#syncMeetingTimes()` — Iterates through all `MeetingTime`s a `Person` has and removes those that have passed.

This operation is exposed in the Model interface as `Model#syncMeetingTimes()`

#### Example Usage:
Step 1. User executes `sync`. `Model#syncMeetingTimes()` is called by `SyncCommand#execute(Model)`.

Step 2. `Model#syncMeetingTimes()` calls `AddressBook` which calls `UniquePersonList#syncMeetingTimes()`.

Step 3.  `UniquePersonList#syncMeetingTimes()` calls `Person#syncMeetingTimes()` for each person in `UniquePersonList#internalList`

Step 4. In `Person#syncMeetingTimes()`, the predicate `MeetingTimePastPredicate()` is passed into `MeetingTimes#removeIf(Predicate)` which removes meetings that are before the time of execution.

#### Design
<img src="images/MeetingDiagrams/SyncMeetingSequenceDiagram.png" width="900" />

#### Design Considerations
**Aspect: Sync Meeting**
* **Choice 1 (current choice): In place filter of uniquePersonList**
  * Pros: Simple to implement
  * Cons: Hard to introduce undo logic
* **Choice 2: New instance of uniquePersonList when syncing**                       
  * Pros: Easy to introduce undo logic                                                   
  * Cons: Breaks singularity of address book in model or uniquePersonList in address book

### Upcoming Meetings feature

#### Implementation

The mechanism is facilitated by `MeetingsWindow`, `MeetingListPanel` and `MeetingCard`.

`MeetingsWindow` extends `UIPart<Stage>` and is linked to an FXML Menu Item `meetingsMenuItem` in `MainWindow`. This means that `MeetingsWindow` can be accessed from the menu bar as a menu item from the GUI as well.

The construction of `MainWindow` object, instantiates a `MeetingsWindow` object and `MainWindow#setAccelerator()` method is called.

The `MainWindow#setAccelerator()` method sets a shortcut that establishes a shortcut `f2` to the calling of `MainWindow#handleMeetings()` method.

`MainWindow#handleMeetings()` method calls `MeetingsWindow#getMeetings()` method that filters a list with `MeetingsWindow#isWithinOneWeek()` method returning an `ObservableList<Person>` of `Person` objects who have meetings from now till 7 days later.

The `ObservableList<Person>` is used to instantiate `MeetingCard` objects and populate the `MeetingListPanel`. The classes work in conjunction with the `.FXML` files to display the `MeetingCard` on a `MeetingListPanel` in a `MeetingsWindow`.

##### Example Usage

Step 1. User presses `f2` to see upcoming meetings.

Step 2. `f2` is set by `MainWindow#setAccelerator()` to call `MainWindow#handleMeetings()` method which in turn calls `MeetingsWindow#getMeetings()`.

Step 3. `MeetingsWindow#getMeetings()` method filters a list of `Person` objects by calling `MeetingsWindow#isWithinOneWeek()` method on the `Set<MeetingTime>` of each `Person`. The filtered list will contain `Person` objects that have meetings from now till 7 days later.

Step 4. `MeetingsWindow#getMeetings()` instantiates a `MeetingsListPanel` object where `MeetingsListPanel#updateItem()` method creates a `MeetingCard` for each `Person` in the list and the `MeetingsListPanel` is populated with `MeetingCard` objects on the `MeetingsWindow`.

The following sequence diagram shows how upcoming meetings feature works.
![UpcomingMeetingSequenceDiagram](images/DeveloperGuide/UpcomingMeetings/UpcomingMeetingSequenceDiagram.png)

The following activity diagram shows how upcoming meetings feature works.
![UpcomingMeetingActivityDiagram](images/DeveloperGuide/UpcomingMeetings/UpcomingMeetingActivityDiagram.png)

#### Design Considerations:
**Display**
**Choice 1 (Current Choice) : Pop up Meetings window.**
  * Pros:
    * Clear segregation from Main UI.
    * Does not take up real estate on the Main UI.
  * Cons:
    * Extra window to be created and managed.

**Choice 2 : Displayed on a section of Main Window.**
  * Pros:
    * No extra window to be managed.
  * Cons:
    * Cluttered UI.

**Meeting Card Display**
**Choice 1 (Current Choice) : Display Meetings Card by Person.**
  * Pros:
    * Clear distinction by client of the meetings that user will have with.
  * Cons:
    * Not intuitive for consolidated meetings in a day.

**Choice 2 : Display Meetings Card by date.**
  * Pros:
    * Intuitive consolidated meetings per day.
  * Cons:
    * Overhaul of new classes and object where a `MeetingTime` has-a `Person` rather than the current implementation of a `Person` has-a `MeetingTime`.

### Undo/redo feature

#### Implementation

The undo/redo mechanism is facilitated by `UndoableCommands` and `CommandManager`.

`UndoableCommand` extends from `Command` and implements its own undo and redo methods. `Command`s that implement `UndoableCommand` include:
* `CreateCommand` — Deletes and adds saved person object for undo and redo methods respectively.
* `DeleteCommand` — Adds and deletes saved person object for undo and redo methods respectively.
* `UpdateCommand` — Saves the original and edited person objects and sets them accordingly for undo and redo methods.
* `ClearCommand` — Restores the saved original address book and sets address book to new address book for undo and redo methods respectively.

`CommandManager` stores `UndoableCommand`s that have been executed and that have been undone in an `undoStack` and `redoStack` respectively. Additionally, `CommandManager` implements the following operations:

* `CommandManager#pushNewCommand(Command)` — Saves the latest undoable command that was executed by user in its history.
* `CommandManager#undo(UndoableCommand)` — Undoes the last undoable command from the top of the undo-stack.
* `CommandManager#redo(UndoableCommand)` — Redoes the last undoable command from the top of the redo-stack.

These operations are exposed in the `Logic` interface as `Logic#execute()`, `Logic#undo()` and `Logic#redo()` respectively.

#### Example Usage
Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `CommandManger` will be initialized with an empty `undoStack` and `redoStack`.

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. `CommandManager#pushNewCommand()` is called by `Logic#execute()`, saving the `DeleteCommand` in the `undoStack`.

Step 3. The user executes `add n/David …​` to add a new person. `CommandManager#pushNewCommand()` is called by `Logic#execute()`, saving the `AddCommand` in the `undoStack`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `CommandManager#pushNewCommand()`, so the invalid command will not be saved into the `undoStack`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Logic#undo()` which calls `CommandManager#undo()` , which will pop the latest `UndoableCommand` from the `undoStack` and calls the `#UndoableCommand#undo()` method of that command which reverts the addressbook back to its previous state. The command popped is pushed to the `redoStack`.

The following sequence diagram shows how the undo command works up till CommandManager:

<img src="images/UndoImplementationImages/UndoSequenceDiagram.png" width="800" />

The next sequence diagram shows how CommandManager executes undo:
<img src="images/UndoImplementationImages/CommandManagerUndoSequenceDiagram.png" width="800" />

<br>

The `redo` command does the opposite — it calls `Logic#redo()` which calls `CommandManager#redo()`, which pops the latest `UndoableCommand` from the `redostack` and calls the `UndoableCommand#redo()` of that command which reverts the addressbook to the state after the command execution. The command is added back to the `undoStack`.

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not be added to the undoStack by `CommandManger#pushNewCommand()`. Thus, the `undoStack` remains unchanged.

Step 6. The user executes `clear`, which calls `CommandManager#pushNewCommand()`. Since there redoStack is not empty, `CommandManager#pushNewCommand` then calls `CommandManger#clearRedoStack`. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/UndoImplementationImages/PushCommandActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**
* **Choice 1 (current choice) : Individual command knows how to undo/redo by**
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

* **Choice 2 : Saves the entire address book.**
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

<div style="page-break-after: always;"></div>

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

* has a need to contact and liaise with many clients to sell their products
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* split clients according to potential and secured
* has many different types of clients (etc. high/low accident rate, injury-prone)
* has difficulty remembering clients' information
* faces trouble in scheduling client meet-ups for both time and location
* wish to filter based on address

**Value proposition**:
* manage clients faster than a typical mouse/GUI driven app
* categorise client according to status and risk for the user to plan their schedule
* inbuilt calendar to track meetings
* search system via location and client meetup suggestions


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                  | So that I can…​                                                       |
|----------|--------------------------------------------|-------------------------------|-----------------------------------------------------------------------|
| `* * *`  | new user                                   | see usage instructions        | refer to instructions when I forget how to use the App                |
| `* * *`  | user                                       | add a new client              |                                                                       |
| `* * *`  | user                                       | delete a client               | remove entries that I no longer need                                  |
| `* * *`  | user                                       | find a client by name         | locate details of clients without having to go through the entire list|
| `* * *`  | user                                       | save written information      | access previously saved information                                   |
| `* *`    | user                                       | find a client by address      | locate details of clients in a designated area                        |
| `* *`    | user                                       | keep track of planned meetings| know when to meet which clients                                       |
| `* *`    | user                                       | be reminded of meetings       | stop missing important meetings                                       |
| `* *`    | tech-savvy user                            | input shortcut commands       | be more efficient using the app                                       |
| `* *`    | user                                       | update a client's information | make changes whenever clients' information update                     |
| `* *`    | user afraid of making mistakes             | quickly revert my actions     | walk back on decisions or correct errors                              |
| `*`      | user with many persons in the client book  | sort clients by name          | locate a client easily                                                |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `FABook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Requesting for help**

**Main Success Scenario (MSS)**
1. User requests for help.
2. FABook shows an external link for help.
3. User refers to link for help.

Use case ends.

**Use case: UC02 - Listing all clients**

**Main Success Scenario (MSS)**
1. User requests to list clients.
2. FABook shows a list of clients.

Use case ends.

**Extensions**
* 2a. The list is empty.

    Use case ends.

**Use case: UC03 - Creating a client contact**

**Main Success Scenario (MSS)**
1. User requests to create a client contact.
2. FABook adds the client contact to its contents.
3. FABook informs user that input client contact has been successfully added.

Use case ends.

**Extensions**
* 1a. Format of creation is invalid.
    * 1a1. FABook shows an error message with suggested format of client contact creation.

    Use case ends.

* 1b. Input information field is in the wrong format.
    * 1b1. FABook shows an error message with suggested format of the input information field.

    Use case ends.

**Use case: UC04 - Assigning a PDF file to a client**

**Main Success Scenario (MSS)**
1. User prepares the file path to the intended PDF file.
2. User assigns the file to the client.
3. FABook informs user that file path has been successfully added to the chosen client.

Use case ends.

**Extensions**
* 1a. Format of file assignment is invalid.
    * 1a1. FABook shows an error message with suggested format of file assignment.

    Use case ends.

**Use case: UC05 - Finding a client by name**

**Main Success Scenario (MSS)**
1. User requests to find clients by a specific name.
2. FABook shows a list of matching clients.
3. FABook informs the user of number of clients found.

Use case ends.

**Extensions**
* 1a. No client name was provided.
    * 1a1. FABook shows an empty list.

    Use case ends.

* 1b. No such client with the specified name is found.
    * 1b1. FABook shows an error message with suggested format of finding by name.

    Use case ends.

**Use case: UC06 - Finding a client by phone number**

**Main Success Scenario (MSS)**
1. User requests to find clients by a specific phone number.
2. FABook shows a list of matching clients.
3. FABook informs the user of number of clients found.

Use case ends.

**Extensions**
* 1a. No client number was provided.
    * 1a1. FABook shows an error message with suggested format of finding by phone number.

    Use case ends.

* 1b. No such client with the specified number is found.
    * 1b1. FABook shows an empty list.

    Use case ends.

**Use case: UC07 - Finding a client by address**

**Main Success Scenario (MSS)**
1. User requests to find clients by a specific address.
2. FABook shows a list of matching clients.
3. FABook informs the user of number of clients found.

Use case ends.

**Extensions**
* 1a. No client address was provided.
    * 1a1. FABook shows an error message with suggested format of finding by address.

    Use case ends.

* 1b. No such client with the specified address is found.
    * 1b1. FABook shows an empty list.

    Use case ends.

**Use case: UC08 - Finding a client by tag**

**Main Success Scenario (MSS)**
1. User requests to find clients by a specific tag.
2. FABook shows a list of matching clients.
3. FABook informs the user of number of clients found.

Use case ends.

**Extensions**
* 1a. No tag was provided.
    * 1a1. FABook shows an error message with suggested format of finding by address.

  Use case ends.

* 1b. No such client with the specified tag is found.
    * 1b1. FABook shows an empty list.

  Use case ends.

**Use case: UC09 - Opening the PDF file of a client**

**Main Success Scenario (MSS)**
1. User requests to open a specific client's PDF file.
2. FABook informs the user that the PDF file has been successfully opened.
3. Client's PDF file is opened on the user's operating system according to their default PDF file reader.

Use case ends.

**Extensions**
* 1a. No PDF file was assigned to the client beforehand.
  * 1a1. FABook shows an error message that file path of client has not been assigned yet.

  Use case ends.

* 1b. The given index is invalid.
  * 1b1. FABook shows an error message that the index provided is invalid.
  
  Use case ends.

* 1c. FABook detects that the assigned PDF file was moved, edited or deleted.
  * 1c1. FABook shows an error message that the file path has been modified.

  Use case ends.

**Use case: UC10 - Checking for upcoming meetings**

**Main Success Scenario (MSS)**
1. User opens up the upcoming meetings.
2. FABook shows upcoming meetings in a new window.

Use case ends.

**Extensions**
* 1a. There are no upcoming meetings
    * 1a1. FABook shows an empty list in the upcoming meetings window.

  Use case ends.

**Use case: UC11 - Updating a client's information**

Preconditions: There are existing clients in FABook's contents.

**Main Success Scenario (MSS)**
1. User requests to update a specific client's information.
2. FABook updates the given client's information.
3. FABook informs user of updated client's information.

Use case ends.

**Extensions**
* 1a. The given index is invalid.
  * 1a1. FABook shows an error message that the index provided is invalid.

  Use case ends.

* 1b. No input information field was given.
  * 1b1. FABook shows an error message that an information field must be provided.

  Use case ends.

* 1c. Input information field is in the wrong format.
  * 1c1. FABook shows an error message with suggested format of the input information field.

  Use case ends.

**Use case: UC12 - Updating a client's description**

Preconditions: There are existing clients in FABook's contents.

**Main Success Scenario (MSS)**
1. User requests to update the description of a specific client.
2. FABook updates the description of the specified client.
3. FABook informs user that the description has been updated.

**Extensions**
* 1a. The given index is invalid.
    * 1a1. FABook shows an error message that the index provided is invalid.

  Use case ends.

**Use case: UC13 - Adding new meeting(s) to an existing client**

Preconditions: There are existing clients in FABook's contents.

**Main Success Scenario (MSS)**
1. User requests to add new meeting(s) to a specific client.
2. FABook adds new meeting(s) to the specified client.
3. FABook informs user that new meeting(s) have been added to the specified client.

**Extensions**
* 1a. The given index is invalid.
    * 1a1. FABook shows an error message that the index provided is invalid.

  Use case ends.

* 1b. Format of meeting time given is invalid.
  * 1b1. FABook shows an error message with suggested format of meeting time.

  Use case ends.

**Use case: UC14 - Deleting a client**

**Main Success Scenario (MSS)**
1. User requests to <u>find a client by specified name(UC05)</u>.
2. User requests to delete a specific client by name in the list.
3. FABook deletes the client.
4. FABook informs the user that the specified client has been successfully deleted.

Use case ends.

**Extensions**
* 2a. The given index is invalid.
    * 2a1. FABook shows an error message that the index provided is invalid.

  Use case ends.

**Use case: UC15 - Deleting meetings from a client**

**Main Success Scenario (MSS)**
1. User requests to <u>find a client by specified name(UC05)</u>.
2. User requests to delete a specific meeting from the client.
3. FABook deletes the specified meeting from the client.
4. FABook informs the user that the specified meeting has been successfully deleted from the client.

Use case ends.

**Extensions**
* 2a. The given index is invalid.
    * 2a1. FABook shows an error message that the index provided is invalid.

  Use case ends.

* 2b. Format of meeting time given is invalid.
    * 2b1. FABook shows an error message with suggested format of meeting time.

  Use case ends.

**Use case: UC16 - Removing all past meetings**

**MSS**
1. User requests to remove all meetings that have past.
2. FABook deletes all meetings that have past from every client.
3. FABook informs the user that all past meetings have been successfully deleted.

Use case ends.

**Use case: UC17 - Clearing all entries**

**Main Success Scenario (MSS)**
1. User requests to clear all entries.
2. FABook deletes all clients and their information.
3. FABook informs the user that all information has been cleared.

Use case ends.

**Use case: UC18 - Undoing a previous command**

**Main Success Scenario (MSS)**
1. User requests to undo a previous command.
2. FABook undoes the last undoable action and updates the relevant information changed.
3. FABook informs the user of the undoable action that has been executed.

Use case ends.

**Extensions**
* 1a. No previous commands available.
    * 1a1. FABook shows an error message that there are no commands to undo.

  Use case ends.

* 1b. The previous command is not undoable.
    * 1b1. FABook shows an error message that there are no commands to undo.

  Use case ends.

**Use case: UC19 - Redoing an undo command**

**Main Success Scenario (MSS)**
1. User requests to <u>undo a previous command(UC18)</u>.
2. User requests to redo the undo command.
3. FABook redoes the last undo command and updates the relevant information changed.
4. FABook informs the user of the redone action that has been executed.

Use case ends.

**Use case: UC20 - Exiting FABook**

**Main Success Scenario (MSS)**
1. User requests to exit.
2. FABook closes.

Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The system should work on both 32-bit and 64-bit environments.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

<div style="page-break-after: always;"></div>

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


### Creating a person

1. Creating a person with a duplicate already present

   1. Prerequisite: A person with name `Alice Tan` and phone number `12345678` on the list. Other fields may be present with any value.

   1. Test case: `create n/Alice Tan p/12345678`<br>
      Expected: No new person created. Error details shown in the command display.

   1. Test case: `create n/Alice Tan p/87654321`<br>
      Expected: A new person is added to the list. Details of the new person shown in the command display.
      
   1. Other viable create commands to try: `create n/Bob Wang p/12345678`, `create n/alice tan p/12345678`, `create n/alice tan p/12345678`<br>
      Expected: Success. Similar to previous.
      
### Assigning a PDF file to a person

1. Assigning a PDF file to a person without PDF file assigned.
   
   1. Create a PDF file on your computer.
   
   2. Test case: `filepath INDEX f/FILEPATH` where `FILEPATH` is the absolute file path of the PDF file from step 1.<br>
      Expected: PDF file assigned. Client details shown in the command display.
      
      1. Click on the client in the result display.
      
      2. Scroll down the contact information window.<br>
         Expected: `Client Information` button in high-contrast white borders and is clickable.
         
      3. Click on the `Client Information` button<br>
         Expected: The PDF file is opened.
   
   3. Test case: `filepath INDEX f/RELATIVE FILEPATH` where `RELATIVE FILEPATH` is the file path of the PDF relative to the directory of FABook.
      Expected: PDF File is assigned. Client details shown in the command display.
      
      1. Click on the client in the result display.
      
      2. Scroll down the contact information window.<br>
         Expected: `Client Information` button in high-contrast white borders and is clickable.

   4. Other incorrect inputs to try: Incorrect filepaths, filepaths for other file types (e.g. .doc)<br>
      Expected: PDF file not assigned. Error details shown in the command display. See previous.
   
   5. See [Opening PDF file](#opening-pdf-file) below for more related testing.

1. Assigning a PDF file to a person already with PDF file assigned.
   
   1. Assign a PDF file to a person as instructed in the previous tests.
   
   2. Create a new PDF file on your computer.
   
   2. Test case: `filepath INDEX f/NEWFILEPATH` where `NEWFILEPATH` is the absolute file path of the new PDF file from step 2.<br>
      Expected: PDF file assigned. Client details shown in the command display.
      
      1. Click on the client in the result display.
      
      2. Scroll down the contact information window.<br>
         Expected: `Client Information` button in high-contrast white borders and is clickable.
         
      3. Click on the `Client Information` button<br>
         Expected: The new PDF file is opened.
   
   3. Test case: `filepath INDEX f/RELATIVE FILEPATH` where `RELATIVE FILEPATH` is the file path of the PDF relative to the directory of FABook.
      Expected: PDF file assigned. Client details shown in the command display.
      
      1. Click on the client in the result display.
      
      2. Scroll down the contact information window.<br>
         Expected: `Client Information` button in high-contrast white borders and is clickable.
      
      3. Click on the `Client Information` button<br>
         Expected: The new PDF file is opened.

   4. Other incorrect inputs to try: Incorrect filepaths, filepaths for other file types (e.g. .doc)<br>
      Expected: new PDF file not assigned. Error details shown in the command display. See previous.
      
   5. See `Opening PDF file` below for more related testing.

### Finding a person by name

1. Finding person by partial names

   1. Use the `create` command to add to list a person named `Jack` and another person named `Jackson`.
   
   2. Test case: `find n/jack`<br>
      Expected: Both `Jack` and `Jackson` are on the shown list.
   
   3. Test case: `find n/jackson`<br>
      Expected: `Jackson` is on the shown list. `Jack` is not.

1. Finding person using multiple names
   
   1. Use the `create` command to add to list a person named `Amy Lin`, a person named `Bob Wang`, and another person named `Carol Chen`.
   
   2. Test case: `find n/amy bob`<br>
      Expected: Both `Amy Lin` and `Bob Wang` are on the shown list. `Carol Chen` is not shown on the list.
   
   2. Test case: `find n/dave lin`<br>
      Expected: `Amy Lin` is on the shown list. `Bob Wang` and `Carol Chen` are not shown on the list.

### Finding a person by number

1. Finding person using multiple numbers

   1. Use the `create` command to add to list a person named `Alice` with phone `12345678` and another person named `Bob` with phone `87654321`.
   
   2. Test case: `find p/87654321 12345678`<br>
      Expected: Both `Alice` and `Bob` are on the shown list.
   
   3. Test case: `find p/12345678 11111111`<br>
      Expected: `Alice` is on the shown list. `Bob` is not.

1. Finding multiple persons with the same number
   
   1. Use the `create` command to add to list a person named `Alice Tan` with phone `12345678` and another person named `Peter Tan` with phone `12345678`.
   
   2. Test case: `find p/12345678`<br>
      Expected: Both `Alice Tan` and `Peter Tan` are on the shown list. 
      
### Finding a person by address

1. Finding person by address with shortcuts
   
   1. Use the `create` command to add to list a person named `Alice Tan` with address `21 Lower Kent Ridge Rd, Singapore`.
   
   2. Test case: `f a/Kent Ridge`<br>
      Expected: `Alice Tan` is on the shown list in `results display`.


### Finding persons by tag

1. Prerequisites: Multiple clients on the list. Some clients have the tag `POTENTIAL`. Some clients have the tag `SECURED`.

1. Finding persons using one tag
   
   2. Test case: `find t/SECURED`<br>
      Expected: All clients with the tag `SECURED` are shown in `results display`. No other clients are shown.
   
   2. Test case: `find t/secured`<br>
      Expected: Success. Same as previous.
   
   3. Test case: `find t/signed`<br>
      Expected: No change in `results display`. Error details shown in the command display.

1. Finding persons using multiple tags
   
   2. Test case: `find t/SECURED POTENTIAL`<br>
      Expected: All clients with the tag `POTENTIAL` are shown in `results display`. No other clients are shown.
   
   2. Test case: `find t/potential secured`<br>
      Expected: All clients with the tag `SECURED` are shown in `results display`. No other clients are shown.

### Opening PDF file

1. Filepath not assigned

   1. Add a new client using `create` command.
   
   2. Click on the newly added client.
   
   3. Scroll down the `Contact Information` window.<br>
      Expected: A greyed out `No Client Information` button is visible.
   
   4. Try to click on the `No Client Information` button.<br>
      Expected: No file opened. No message shown.
   
   5. Test case: `file INDEX` where INDEX is the index of the new client on the list.<br>
      Expected: No file opened. Error details shown in the command display.

2. Filepath is assigned

   1. Add a new client using `create` command.

   2. Assign a PDF file to a person - See [Assigning a PDF file to a person](#assigning-a-pdf-file-to-a-person) for examples on how to assign PDFs.

   3. Test case: `file INDEX` where INDEX is the index of the new client on the list.<br>
      Expected: The PDF file is opened. 

   4. Test case; Click on the `Client Information` button<br>
      Expected: The PDF file is opened.

1. Dealing with invalid filepaths
   
   1. Create a PDF file on your computer.
   
   2. Use `filepath` command to assign the PDF file to a client in the FABook.
   
   3. Rename or delete the PDF file on your computer.
   
   4. Try to open the file from within FABook using `file` command or `Client Information` button.<br>
      Expected: No file is opened. Error details shown in the command display or below the `Client Information` button.
      
   5. Exit the app.
   
   6. Re-launch the app.<br>
      Expected: The app re-launches with previously stored data intact.

### Get upcoming meetings

1. Getting upcoming meetings

   1. Add multiple meeting to multiple clients in FABook with meeting times within the next 7 days.

   1. Add multiple meeting to multiple clients in FABook with meeting times before the present time.
   
   2. Add multiple meeting to multiple clients in FABook with meeting times later than 7 days from the present time.

   3. Press `F2` or click on `Meetings` button then the `Upcoming Meetings F2` button that appears.<br>
      Expected: A new window with title `Upcoming Meetings` appear. Only the meetings added in step 1 are displayed.

2. Getting upcoming meetings with Upcoming Meetings window open

   1. Perform step 1~4 in `Getting upcoming meetings` above.
   
   2. Switch the current window to one different from the `Upcoming meetings` window without closing the `Upcoming meetings` window.
   
   3. Press `F2` or click on `Meetings` button then the `Upcoming Meetings F2` button that appears.<br>
      Expected: The `Upcoming meetings` window re-appears to the front.

3. Getting upcoming meetings after updating with Upcoming Meetings window open

   1. Perform step 1~4 in `Getting upcoming meetings` above.
   
   2. Swtich back to the main FABook window.
   
   3. Add one meeting to any client with meeting time within the next 7 days.
   
   4. Close the `Upcoming Meetings` window.
   
   3. Press `F2` or click on `Meetings` button then the `Upcoming Meetings F2` button that appears.<br>
      Expected: A `Upcoming Meetings` window appear. The meeting added in step 3 is displayed on the list.

### Update client information:

1. Updating client information though input shortcut:
   
   1. Test case: `u 1 nw/$22000`<br>
      Expected: Networth of first client change to `$22000`. New client detail shown in command display.

### Update descriptions:

1. Adding a description

   2. Test case: `description 2 ds/Eager to invest`<br>
      Expected: Description of the second client changed to `Eager to invest`. 
   
   3. Test case: `description ds/Eager to invest`<br>
      Expected: No change in descriptions made. Error details shown in command display.
  
   3. Other test cases: `description`, `description 0 ds/Eager to invest`<br>
      Expected: No change in descriptions made. Error details shown in command display.
   

2. Removing a description

   2. Test case: `description 2 ds/`<br>
      Expected: Description of the second client removed. "Removed Description: " message shown in command display. 
   
   3. Other test cases: `description`, `description 0 ds/`<br>
      Expected: No change in descriptions made. Error details shown in command display.


### Update meetings:

1. Adding multiple meetings to the same person

   1. Test case: `meeting 2 mt/19-10-2022-10:34 mt/23-11-2022-22:22`<br>
      Expected: Two meetings are successfully added. Details of the updated person shown in the command display.
      
      1. Click on the second client on the `result display`.<br>
         Expected: Two new meeting times are visible with a green background.
   
   2. Test case: `meeting 2 mt/19-10-2022-10:34 23-11-2022-22:22`<br>
      Expected: No meetings added. Error details shown in the command display.
   
   3. Other incorrect inputs: `meeting 0 mt/19-10-2022-10:34`, `meeting 2 mt/31-09-2022-10:34`, `meeting 2 mt/19-10-2022-10:60`

2. Re-adding the same meeting to the same person

   1. Use `meeting 2 mt/19-10-2022-10:34` to add a meeting.
   
   2. Re-enter `meeting 2 mt/19-10-2022-10:34`<br>
      Expected: Success message shown in command display.
      
      1. Click on the second client on the `result display`.<br>
         Expected: Only one `19 October 2022 10:34`. No duplicate meetings.
      
### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the command display.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the command display.
      
   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.
      
1. Deleting a person while a filtered list of persons is being shown

   1. Prerequisites: Find certain persons using the `find` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the command display.
      
      1. Use the `list` command to show all persons.
         Expected: The same contact is deleted from the main list.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the command display.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Delete Meetings

1. Deleting assigned meeting

   1. `Meeting 2 mt/20-12-2012-20:12` to assign meeting time `20-12-2012-20:12` to the second client.
   
   2. Test case `deletemeeting 2 mt/20-12-2012-20:12`<br>
      Expected: Meeting time `20-12-2012-20:12` removed from second client. `Results display` updated.
      
   3. Test case `deletemeeting 2 mt/11-11-2011-11:11`<br>
      Expected: No meeting removed. Error message `Couldn't find meeting time` and details shown in the command display.
      
   4. Other incorrected deletemeeting commands to try: `deletemeeting 2`, `deletemeeting 0 mt/20-12-2012-20:12`
      Expected: No meeting removed. Error details shown in the command display.

### Remove past meetings

1. Remove past meetings

   1. Add multiple meeting to multiple clients in FABook with meeting times after the present time.

   1. Add multiple meeting to multiple clients in FABook with meeting times before the present time.
   
   3. Enter `sync`.<br>
      Expected: Meetings added in step 2 are removed. Success message displayed shown in the command display.
      
      
### Undoing a previous command

1. Undoing create
   
   1. Enter `create n/Amy p/11111111`<br>
      Expected: New person created with `Name`: Amy and `Phone`: 11111111.
      
   2. Enter `undo`<br>
      Expected: Person removed. Removed person detail shown in the command display.
      
2. Undoing clear
   
   1. Use `create` command to add multiple clients to FABook.

   2. Use `clear` command<br>
      Expected: All clients removed from `results display` list.
      
   3. Enter `undo`<br>
      Expected: All clients restored. Success message shown in the command display.

3. Undoing delete

   1. Prerequisite: At least one client on displayed list.
   
   2. Use `delete` command to remove a client.
   
   3. Enter `undo`<br>
      Expected: Removed client restored. Success message shown in the command display.
      
4. Undoing list (non-undoable)

   1. Prerequisite: No previous commands this session. Re-laund the app if there were previous inputs.
   
   2. Use `list` command to view entire list.
   
   3. Enter `undo`<br>
      Expected: No changes made to FABook. Error details shown in the command display.
      
4. Undoing with non-undoable commands in between

   1. Similar to test 1, use `create` command to add a client to list.
      
   2. Use `list` command to view entire list.
   
   3. Use `help` command to view help window.
   
   4. Enter `undo`<br>
      Expected: Successfully removed the client created in step 1.
      
5. Undoing multiple commands
   
   1. Prerequisite: Multiple clients on displayed list.
   
   2. Use `delete` command to remove a client.
   
   3. Repeat step 2 five times.
   
   4. Enter `undo`<br>
      Expected: Most recently removed client is restored.
   
   5. Re-enter `undo` with same number of repetition as step 3<br>
      Expected: All removed clients restored. Clients restored in reverse order of removal.
   
6. Undoing redo

   1. Use `create` command to add a client to list.
   
   2. Enter `undo`<br>
      Expected: Recently added client removed.
   
   3. Enter `redo`<br>
      Expected: Client re-added.
      
   4. Enter `undo`<br>
      Expected: Client re-removed.
   
   5. Enter `redo`<br>
      Expected: Client re-added.
      
   6. Enter `undo`<br>
      Expected: Client re-removed.

### Saving data

1. Dealing with missing data files

   1. Launch the app and exit after any number of actions.
   
   3. In the same directory as the app, find folder `data`.
   
   4. Delete the `data` folder along with the file `FABook.json` inside.
   
   5. Re-launch the app.<br>
      Expected: The app launches with a auto-generated list of sample data containing six sample clients. The first sample client is `Alex Yeoh`.
   
   6. Re-exit the app after any number of actions.<br>
      Expected: The `data` folder is re-created with the file `FABook.json` inside.

1. Dealing with corrupted data files

   1. Launch the app and exit after any number of actions.
   
   3. In the same directory as the app, find folder `data`.
   
   4. Open the file `FABook.json` inside the `data` folder.
  
   5. Edit `FABook.json` so that it no longer follews the usual saved format. Save the changes made.
   
   6. Re-launch the app.<br>
      Expected: The app launches with an empty list.
   
   6. Re-exit the app after any number of actions.<br>
      Expected: The file `FABook.json` is replaced with new data in correct format.

## **Appendix: Effort**

**FABook** was built across 13 weeks, with the first 7 weeks being used to understand the foundation it was built upon,
[**AddressBook Level-3**](https://nus-cs2103-ay2223s1.github.io/tp/), as well as conceptualize, design and plan out the
implementation of **FABook**. The 6 remaining weeks were used to incrementally implement functionalities on a weekly basis.

### Difficulty Level

Given that each of us had to work on a similar individual project(iP) before banding together to work on this project, if the difficulty of the individual project is a 5,
the difficulty of implementing **FABook** as an application is a 9. This is due to the following:
* Given more freedom to explore and create an application based on our team's ideas, this encouraged us to push for more complex logic designs.
* Working in a team environment meant that there was a need to discuss and delegate tasks on a regular basis, which led to administrative challenges.

### Challenges Faced

* Evolving any feature that was implemented in **AB3** often leads to a chain reaction of changes to make such as the following:
  * We have to ensure that other features of the program have not been broken through updating of automated test cases as well as manual testing.
  * User guide and developer guide have to be updated accordingly to reflect any changes made.
  * Dealing with bugs caused by any change may lead to a deep 'rabbit-hole' trying to fix what went wrong.
* Coordinating with the UI of the program after implementing new features.
* Dealing with team conflicts over design choices and time delay between different schedules of team members.

### Effort required

When compared to the effort needed for everyone's iP, it can be estimated ~150% of the effort needed for iP was used for this team project.
Weekly consistent effort was needed by everyone to handle team deliverables and to ensure that a working product was ready at every milestone and iteration.

### Achievements

* Implementation of the ability to assign and open client's PDF document.
* UI redesigned to match a more aesthetically pleasing and purposeful look.
* Addition of new commands.
* Implementation of an upcoming meetings window which involved new UI and logic implementation.
