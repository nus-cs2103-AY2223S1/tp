---
layout: page
title: Developer Guide
---

# **SoCompiler Developer Guide**


## **Table of Contents**

1. [Introduction](#1-introduction)
2. [Getting Started](#2-getting-started)
3. [Design](#3-design)
   1. [Architecture](#31-architecture)
   2. [UI Component](#32-ui-component)
   3. [Logic Component](#33-logic-component)
   4. [Model Component](#34-model-component)
   5. [Storage Component](#35-storage-component)
   6. [Common Classes](#36-common-classes)
4. [Implementation](#4-implementation)
   1. [Module Class](#41-module-class)
   2. [Add Module Feature](#42-add-module-feature)
   3. [Delete Module Feature](#43-delete-module-feature)
   4. [Find Module Feature](#44-find-module-feature)
5. [Documentation, Logging, Testing, Configuration and Dev-Ops](#5-documentation-logging-testing-configuration-dev-ops)
6. [Acknowledgements: Requirements](#6-acknowledgements)
7. [Appendix A: Requirements](#7-appendix-a-requirements)
   1. [Product Scope](#71-product-scope)
   2. [User Stories](#72-user-stories)
   3. [Use Cases](#73-use-cases)
   4. [Non-Functional Requirements](#74-non-functional-requirements)
   5. [Glossary](#75-glossary)
8. [Appendix B: Instructions for Manual Testing](#8-appendix-b-instructions-for-manual-testing)
   1. [Launch and Shutdown](#81-launch-and-shutdown)
   2. [Deleting a Person](#82-deleting-a-person)
   3. [Saving Data](#83-saving-data)


--------------------------------------------------------------------------------------------------------------------

## 1. **Introduction**

SoCompiler is a **desktop app for managing contacts and module information, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It is built specifically for School of Computing(SOC) students in NUS but can also be used by other students from NUS.

This developer guide will expose the architecture behind SoCompiler and showcase the specifics of how commands are handled by the application.

In order to be a successful SoCompiler Developer, you need a general understanding of:
* Java language
* JavaFx

## 2. **Getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## 3. **Design**

SoCompiler aims to provide features that are intuitive and simple to use. Keeping this in mind, we pursed an iterative approach, adding new features amidst evolving requirements. This gives rise to the following main guiding principles for SoCompiler:

**Maintainability**
This project is built upon an application called [AddressBook Level 3 (AB3)](https://se-education.org/addressbook-level3/), which follows the Model View Controller(MVC) design pattern. Ab3 was developed in a manner that facilitates easy modification. We capitilised on this fact and built upon existing components in AB3, such as [UI](#32-ui-component), [Logic](#33-logic-component), [Model](#34-model-component) and [Storage](#35-storage-component).

**Command Line Interface (CLI) Oriented**
As our target audience is SOC students who usually type fast and are familiar with command line interfaces, we designed SoCompiler to be more efficient at managing contacts and module information using commands compared to other apps in the market.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.
</div>

### 3.1. Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes
called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It
is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### 3.2. UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### 3.3. Logic component

**
API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is
   executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API
call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="800"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### 3.4. Model component

**
API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="1000" />


The `Model` component,

* stores the address book data i.e., all `Person` or `Module` objects (which are contained in a `UniquePersonList` or
  a `UniqueModuleList` object respectively).
* stores the currently 'selected' `Person` or `Module` objects (e.g., results of a search query) as a separate _
  filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` or `ObservableList<Module>`
  that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the
  list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

### 3.5. Storage component

**
API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

* can save both address book data and user preference data in json format, and read them back into corresponding
  objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### 3.6. Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## 4. **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### 4.1. Module Class

The [`Module`](https://github.com/AY2223S1-CS2103T-W12-1/tp/tree/master/src/main/java/seedu/address/model/module) Class
facilitates the storing of various information related to a student's module that he/she is currently taking.

A `Module` Class contains

* A `ModuleCode`
* `TutorialDetails`
* `LectureDetails`
* A lecture `ZoomLink`
* A tutorial `ZoomLink`
* `AssignmentDetails`

All fields except `ModuleCode` are optional since not every `Module` will have details for every field. Empty fields are
represented by empty strings. Users can later modify the fields using the `EditCommand`.

All the commands associated with the `Module` Class would have the keyword "Module" in their class name. For example,
the command to add a `Module` is referred to as a `AddModuleCommand`.

All the `Module` objects are contained in a `UniqueModuleList` object which ensures that no duplicate `Module` objects
can exist in the `UniqueModuleList` object. This is because
in NUS, there are no two [modules](#modules) with the same module code. Thus, the notion of equality is defined by default to be
two `Module` objects containing the same `ModuleCode` object.

All the classes contained within the `Module` Class all have a regex that checks for whether the user input for the
specific field is valid.

For the five classes,

* The `ModuleCode` should only contain alphanumeric characters and spaces, and it should not be blank.
* The `ZoomLink` should be either blank, or a valid URL.
* `AssignmentDetails` should only contain alphanumeric characters and spaces if added.

<img src="images/ModuleClassDiagram.png" width="550" />

### 4.2. Add Module feature

The AddModule commands extends `Command`, and takes in a `moduleCode`, `lectureDetails`, `tutorialDetails`, `zoomLink`
and multiple optional `assignmentDetails` to be added. Additionally, it implements the following operation:

* `AddModuleCommand#execute()`— Adds the corresponding module to the model.

This operation is exposed in the `Model` interface as `Model#addModule()`.

Given below is an example usage scenario, and an object diagram to show the objects created during this command.

Step 1. The user launches the application. The `ReadOnlyAddressBook` will be initialized with the initial address book
state.

Step 2. The user executes `addm m/CS1101S` command to add a module with the corresponding details in the address book.
* The `addm` command calls `AddressBookParser#parseCommand()`, which creates a `AddModuleCommandParser`.
* The `AddModuleCommandParser` then tokenizes the user input string and returns an `ArgumentMultimap` object that maps
  prefixes to their respective argument values.
* Methods in `ParserUtil` is are then called to parse each individual object obtained from the `ArgumentMultimap` using
  their corresponding parsers.
* Then, a new `Module` with the corresponding details is created.
* After creating the `Module`, an `AddModuleCommand` is created, which calls `Model#addModule()`,
  and adds the newly created module to the model object.

The following object diagram illustrates the above example:
![AddModuleObjectDiagram](images/AddModuleObjectDiagram.png)

The following sequence diagram shows how the AddModule operation works:
![AddModuleSequenceDiagram](images/AddModuleSequenceDiagram.png)

### 4.3. Delete Module feature

The DeleteModule commands extends `Command`, and takes in an `Index` to be deleted. Additionally, it implements the following operation:

* `DeleteModuleCommand#execute()` — Deletes the corresponding item in the given model according to the given index.


This operation is exposed in the `Model` interface as `Model#deleteModule()`.

Given below is an example usage scenario, and an object diagram to show the objects created during this command.


Step 1. The user launches the application. The `ReadOnlyAddressBook` will be initialized with the initial address book state.

Step 2. The user executes `deletem 1` command to delete the 1st module in the address book.
* The `deletem` command calls `AddressBookParser#parseCommand()`, which creates a `DeleteModuleCommandParser`.
* The `DeleteModuleCommandParser` gets the `Index` to be deleted, which is 1 in this case, and creates a `DeleteModuleCommand`.
* `DeleteModuleCommand` then calls `Model#deleteModule()`, and deletes the module from the model object corresponding to the number parsed.


The following object diagram illustrates the above example:

![DeleteModuleObjectDiagram](images/DeleteModuleObjectDiagram.png)

The following sequence diagram shows how the DeleteModule operation works:

![DeleteModuleSequenceDiagram](images/DeleteModuleSequenceDiagram.png)


<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteModuleCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

### 4.4. Find Module feature

The FindModule command extends `Command`, and takes in an `ModuleDetailsContainsKeywordsPredicate` to filter the module list by. Additionally, it implements the following operation:
* `FindModuleCommand#execute()`

This operation is exposed in the `Model` interface as `Model#updateFilteredModuleList()`.

Given below is an example usage scenario.

Step 1. The user launches the application. The `ReadOnlyAddressBook` will be initialized with the initial address book state.

Step 2. The user executes `findm CS2100` command to filter the module list by `CS2100`.
* The `findm CS2100` command calls `AddressBookParser#parseCommand()`, which creates a `FindModuleCommandParser`.
* The `FindModuleCommandParser` instantiates a `ModuleDetailsContainsKeywordsPredicate` with the given keyword `CS2100`.
* The `FindModuleCommandParser` then creates a `FindModuleCommand` with the keyword.
* The `FindModuleCommand` then calls `Model#updateFilteredModuleList()` and filter the list to contain only [Modules](#modules) with the given keyword in their module code.

The following sequence diagram shows how the FindModule operation works:

![FindModuleSequenceDiagram](images/FindModuleSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `FindModuleCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

--------------------------------------------------------------------------------------------------------------------

## 5. **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------
## 6. **Acknowledgements**

SoCompiler is built-upon [AddressBook-Level3](https://github.com/se-edu/addressbook-level3/tree/master/docs), a sample project that serves as a base for Computer Science students to work on.

### Credit for code adapted from external sources

Code to read a file from resources folder is adapted from this thread on [mkyong.com](https://mkyong.com/java/java-read-a-file-from-resources-folder/)

--------------------------------------------------------------------------------------------------------------------

## 7. **Appendix A: Requirements**

### 7.1. Product scope

**Target user profile**:

* is from SoC
* has a need to manage a significant number of contacts
* has a need to manage the [modules](#modules) they are taking
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Sole app that SoC students need to streamline their everyday routines

### 7.2. User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​            | I want to …​                                       | So that I can…​                                                 |
|----------|--------------------|----------------------------------------------------|-----------------------------------------------------------------|
| `* * *`  | SoC Student        | add a module                                       |                                                                 |
| `* * *`  | SoC Student        | add a new person                                   |                                                                 |
| `* * *`  | SoC Student        | delete a person                                    | remove entries that I no longer need                            |
| `* * *`  | SoC Student        | delete a module                                    | remove entries that I no longer need                            |
| `* * *`  | SoC Student        | add zoom links for my modules                      |                                                                 |
| `* * *`  | SoC Student        | delete zoom links                                  | remove entries that I no longer need                            |
| `* * *`  | SoC Student        | easily find my zoom links                          | not spend too much time finding them on various websites        |
| `* * *`  | SoC Student        | find a module and the specifics of the module      | not spend too much time finding information on various websites |
| `* * *`  | SoC Student        | add the deadline of my assignments for each module | easily keep track of my deadlines                               |
| `* * *`  | SoC Student        | find a contact easily                              | not spend too much time looking for contacts                    |
| `* *`    | SoC Student        | check my schedule for the day                      | plan ahead                                                      |
| `* *`    | SoC Student        | organise consultations easily                      | not need to search various websites to organise consultations   |
| `* *`    | SoC Student        | label my contacts                                  | keep track of my project groups                                 |
| `* *`    | SoC Student        | organise the deadlines of my assignments           | see which deadline is most pressing                             |
| `* *`    | SoC Student        | see the dates of my exams                          | better prepare for them                                         |
| `*`      | SoC Student        | add miscellaneous events                           | better plan my time                                             |
| `*`      | SoC Student        | archive the current information                    | reset for the new semester                                      |
| `*`      | SoC Student        | keep track of my interview dates                   | make ample preparations                                         |
| `*`      | SoC Student        | keep track of my weekly meetings                   | make preparations for them                                      |
| `*`      | Teaching Assistant | access document links for all my slides            | share with the class I am teaching                              |
| `*`      | Teaching Assistant | manage my student's consultation slots             | easily find the timing for their consultation                   |
| `*`      | Teaching Assistant | access my module website                           | grade my student's submission                                   |
| `*`      | Teaching Assistant | easily group my students' contacts together        | easily find them at once                                        |

### 7.3. Use Cases

(For all use cases below, the **System** is the `SoCompiler` and the **Actor** is the `user`, unless specified
otherwise)

**Use case:UC1 Add a person**

**MSS**

1. User requests to add person
2. SoCompiler adds the person to the list of persons

   Use case ends.

**Extensions**

* 1a. The given person already exists.

    * 1a1. SoCompiler shows an error message

      Use case ends.

* 1b. Necessary fields are empty.

    * 1b1. SoCompiler shows an error message

      Use case ends.

**Use case:UC2 Delete a person**

**MSS**

1. User requests to list persons
2. SoCompiler shows a list of persons
3. User requests to delete a specific person in the list
4. SoCompiler deletes the person

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. SoCompiler shows an error message.

      Use case resumes at step 2.

**Use case:UC3 Add a module**

**MSS**

1. User requests to add a module
2. SoCompiler adds the module to the list of modules

   Use case ends.

**Extensions**

* 1a. The given module already exists.

    * 1a1. SoCompiler shows an error message

      Use case ends.

* 1b. Necessary fields are empty.

    * 1b1. SoCompiler shows an error message

      Use case ends.

**Use case:UC4 Delete a module**

**MSS**

1. User requests to list module
2. SoCompiler shows a list of modules
3. User requests to delete a specific module in the list
4. SoCompiler deletes the module

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. SoCompiler shows an error message.

      Use case resumes at step 2.

**Use case:UC5 Find a person**

**MSS**

1. User requests to find keyword
2. SoCompiler shows a list of persons matching that keyword

   Use case ends.

**Use case:UC6 Find a module**

**MSS**

1. User requests to find keyword
2. SoCompiler shows a list of modules matching that keyword

   Use case ends.

**Use case:UC7 Display list of persons**

**MSS**

1. User requests to list persons
2. SoCompiler shows a list of persons

   Use case ends.

**Use case:UC8 Display list of modules**

**MSS**

1. User requests to list modules
2. SoCompiler shows a list of modules

   Use case ends.

**Use case:UC9 Edit entry in list of persons**

**MSS**

1. User requests to list persons
2. SoCompiler shows a list of persons
3. User requests to edit the fields of a specified person in the list
4. SoCompiler edits the fields of the specified person

   Use case ends.

**Extensions**

* 3a. The given index is invalid.

    * 3a1. SoCompiler shows an error message

      Use case ends.

* 3b. No field is provided.

    * 3b1. SoCompiler shows an error message

      Use case ends.

* 3c. The given name already exists.

    * 3c1. SoCompiler shows an error message

      Use case ends.

**Use case:UC10 Edit entry in list of modules**

**MSS**

1. User requests to list modules
2. SoCompiler shows a list of modules
3. User requests to edit the fields of a specified module in the list
4. SoCompiler edits the fields of the specified module

   Use case ends.

**Extensions**

* 3a. The given index is invalid.

    * 3a1. SoCompiler shows an error message

      Use case ends.

* 3b. No field is provided.

    * 3b1. SoCompiler shows an error message

      Use case ends.

* 3c. The given module code already exists.

    * 3c1. SoCompiler shows an error message

      Use case ends.
    

### 7.4. Non-Functional Requirements

1. Should work on any [mainstream OS](#mainstream-os) as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons and modules without a noticeable sluggishness in performance for typical
   usage.
3. A user with more than 50 words per minute typing speed for regular English text (i.e. not code, not system admin
   commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. The system should be backward compatible with data stored in earlier versions of the system.
5. The product is not required to handle interaction with other users.


### 7.5. Glossary

#### Mainstream OS: 

Operating Systems such as Windows, Linux, Unix, OS-X

#### Modules: 

University modules offered in NUS



--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------

## 8. **Appendix B: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### 8.1. Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
        optimum.

2. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    2. Re-launch the app by double-clicking the jar file.<br>
        Expected: The most recent window size and location is retained.
    

### 8.2. Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    2. Test case: `delete 1`<br>
        Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
        Timestamp in the status bar is updated.

    3. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.
    

### 8.3. Saving data

1. Dealing with missing/corrupted data files

   1. Open the save file `SoCompiler.json` located in the file `data` and add garbage values. For example, add `!` or `-` to a persons' contact number. 
   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: App opens with no person or module loaded. Save file is wiped clean.
   
[Back to Top](#socompiler-developer-guide)
