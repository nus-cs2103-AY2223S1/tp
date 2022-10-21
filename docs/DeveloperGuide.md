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

**Target user profile**:

* NUS SoC Professors taking on and managing FYP students doing projects using SoC
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: To provide a platform for easier access to SoC professors to their students’ FYP status, rather than via plain e-mail correspondences.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a/an …​                                     | I can …​                                                                                                  | So that I can…​                                                                             |
|----------|------------------------------------------------|-----------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| `* * *`  | responsible SoC professor                      | clear/mark done a project                                                                                 | provide timely updates for my students to track their FYP progress                          |
| `* * *`  | first time user                                | view a list of commands that I can try as well as the details of each command                             | quickly start to manage my students’ FYPs using this system/app                             |
| `* * *`  | no-time-to-waste SoC professor                 | search the projects using specific keywords                                                               | access the progress of projects of interest with just a few clicks                          |
| `* * *`  | organised SoC professor                        | remove students if they dropped a FYP that was under me                                                   | keep the consolidated list of FYPs neat and organised                                       |
| `* * *`  | organised SoC professor                        | remove students if they completed a FYP that was under me, and move them to a “completed” FYP list        | keep the consolidated list of FYPs neat and organised [coming soon]                         |
| `* * *`  | SoC professor                                  | add students if they are taking their FYP under me                                                        | keep track of those FYP projects under me easily                                            |
| `* * *`  | student with many email accounts               | update my e-mail address when I change on the app                                                         | be easily contacted by my professors when they need to find me [coming soon]                |
| `* * *`  | SoC professor                                  | view the list of all students taking their FYP under me                                                   | get to know more information about my own students                                          |
| `* *`    | responsible FYP student                        | check my upcoming deadlines with an option to see yet-to-submit deliverables                              | avoid missing any submission deadlines by accident [coming soon]                            |
| `* *`    | SoC professor                                  | view the progress updates that my students upload                                                         | deliberate about what the next course of action should be [coming soon]                     |
| `* *`    | eager student                                  | receive feedback promptly, i.e. read comments and suggestions from my professor ASAP                      | set a clear direction for my FYP in advance [coming soon]                                   |
| `* *`    | responsible FYP student                        | update my FYP progress through the app                                                                    | inform my professor on my progress [coming soon]                                            |
| `* *`    | critical student                               | provide feedbacks on my FYP guidance and evaluations by my professor                                      | help the FYP management team to further improve [coming soon]                               |
| `* *`    | helpful SoC professor                          | put tags (e.g. “Ahead of time”, “Up to task”, “Behind schedule”) on my student's FYP                      | clearly indicate my student's progress relative to the timeline [coming soon]               |
| `* *`    | non-confrontational (i.e. shy) student         | push certain tags (e.g. “Assistance required!”, “Unable to complete :(”) to the application               | gather my professor’s attention without direct confrontation [coming soon]                  |
| `* *`    | friendly SoC professor   　　　                   | share my availability with my students                                                                    | be booked on consultation slots by my students [coming soon]                                |
| `* *`    | troubled student   　　　                         | request for help from my professors by clicking on a button and indicating it’s urgency                   | alert the professor that I might need some assistance and how urgent it is [coming soon]    |
| `*`      | fun-seeking professor/FYP student              | send “stickers” (similar to Telegram stickers) or emoji                                                   | have a fun experience while using the app [coming soon]                                     |
| `*`      | responsible FYP student                        | update my availability slots on the app                                                                   | let my professor know when I will be available to meet [coming soon]                        |
| `*`      | well-versed SoC professor                      | sort my student’s FYP based on what specialisation they are taking                                        | organise the FYPs better [coming soon]                                                      |
| `*`      | meticulous SoC professor   　　　                 | see student's FYP sorted by research area first, followed by alphabetical order                           | gain quick and easy access to the details required [coming soon]                            |
| `*`      | proactive FYP student   　　　                    | view available consultation slots with my professor on the app                                            | easily plan my consultation sessions with my professor [coming soon]                        |
| `*`      | forgetful FYP student   　　　                    | be updated in real time on any comments tagged with varying urgency levels                                | fix any urgent mistakes for clarification as my Professor goes through my FYP [coming soon] |
| `*`      | organised SoC professor   　　　                  | set a deadline for one or more projects                                                                   | advise my students on what to be prioritized [coming soon]                                  |
| `*`      | proactive student   　　　                        | turn off (or on) the notification email from professors at any time                                       | avoid emails if I have already done the submissions [coming soon]                           |
| `*`      | busy SoC professor   　　　                       | see my students’ project titles and summaries at one glance                                               | save time from clicking into different links to access them [coming soon]                   |
| `*`      | insecure FYP student   　　　                     | ring or notify my professor(s) for help via the platform                                                  | get the immediate help that I require [coming soon]                                         |
| `*`      | clear-minded SoC professor   　　　               | filter the student’s FYP projects based on what specialisation they are doing (e.g. network research, AI) | gain quick access to the relevant FYP projects by categories [coming soon]                  |
| `*`      | organized SoC professor   　　　                  | have a separate tab to store all the files that I send to or receive from my students                     | refer to the related files easily [coming soon]                                             |
| `*`      | punctual SoC professor   　　　                   | be alarmed of the upcoming meetings when it’s almost the time                                             | join them on time. (e.g. 5 minutes before) [coming soon]                                    |
| `*`      | SoC professor who values maintainability   　　　 | integrate some existing SoC-made softwares within                                                         | have more familiarity with the platform [coming soon]                                       |
| `*`      | constructive SoC professor   　　　               | comment and suggest on my students’ current work                                                          | give my feedback in a fast and easy way [coming soon]                                       |
| `*`      | eager student   　　　                            | see the pending task with the next earliest deadline set by my professor                                  | know what I need to do next for my FYP [coming soon]                                        |
| `*`      | automation-loving SoC professor   　　　          | send auto emails to students with upcoming deadlines                                                      | remind them to work on the tasks by the deadline [coming soon]                              |


*{More to be added}*

--------------------------------------------------------------------------------------------------------------------
### Use cases

(For all use cases below, the **System** is the `JerylFypManager` and the **Actor** is the `Professor`, unless specified otherwise)

**1. Use case: UC01 - Adding FYP**

**Preconditions: Professor knows the details of FYP to be added**

**MSS**

1.  Professor requests to add a student FYP into the list.
2.  JerylFypManager adds the FYP details.

    Use case ends.

**Extensions**

* 1a. The request given is invalid.
  * 1a1. JerylFypManager shows an error message.
  * 1a2. Professor enters a new request.

  Steps 1a1-1a2 are repeated until the request entered is correct.

  Use case resumes from step 2.


**2. Use case: UC02 - Searching Keyword**

**Preconditions: Professor can recall part of the project name**

**MSS**

1.  Professor wants to track the status of certain project(s).
2.  Professor searches the project(s) using a specified keyword.
3.  JerylFypManager displays the search results with a brief summary for each project.

    Use case ends.

**Extensions**

* 2a. The keyword given does not match any project name.
    * 2a1. JerylFypManager shows an error message.
    * 2a2. Professor tries again with another keyword.

  Steps 2a1-2a2 are repeated until some projects are matched.

  Use case resumes from step 3.


**3. Use case: UC03 - Marking Project Status**

**Guarantees:**
* Project Status goes from “Yet to Start” (YTS) to “In Progress”  (IP); alternatively, from “In Progress” (IP) to “Done” (DONE).


**Preconditions: Existence of said FYP in database**

**MSS**

1.  Professor wants to modify the status of his student’s project, and he/she specifies the desired status change.
2.  JerylFypManager toggles the status of the project.

    Use case ends.

**Extensions**

* 1a. Professor’s input of Student’s ID / Status Tag is incorrect (e.g. misspell), or Professor is accessing FYP that does not exist.
    * 1a1. JerylFypManager shows an error message.
    * 1a2. Professor keys in his input again.

    Steps 1a1-1a2 are repeated until the request entered is correct.

  Use case resumes from step 2.


**4. Use case: UC04 - Removing FYP**

**Preconditions: Professor can recall the name/ID of the FYP**

**MSS**

1.  Professor requests to remove a student FYP from the list.
2.  JerylFypManager removes the FYP details.

    Use case ends.

**Extensions**

* 1a. The request given is invalid.
    * 1a1. JerylFypManager shows an error message.
    * 1a2. Professor enters a new request.

  Steps 1a1-1a2 are repeated until the request entered is correct.

  Use case resumes from step 2.


**5. Use case: UC05 - Help List**

**Preconditions: Nil**

**MSS**

1.  Professor requests from JerylFypManager to list all possible commands.
2.  JerylFypManager shows the list of possible commands and the format to be entered in.

    Use case ends.

**Extensions**

* Nil


**6. Use case: UC06 - List FYPs**

**Preconditions: There exist FYP(s) in JerylFypManager**

**MSS**

1.  Professor requests for JerylFypManager to show the list of FYPs.
2.  JerylFypManager shows a list of FYPs to the professor.

    Use case ends.


**Extensions**

* 2a. There are currently no FYPs that the professor is overseeing.
    * 2a1. JerylFypManager tells the professor that he is currently not overseeing any FYPs.

  Use case ends.


**7. Use case: UC07 - Exit JerylFypManager**

**Preconditions: Nil**

**MSS**

1.  Professor requests to exit JerylFypManager.
2.  JerylFypManager shows exit message.
3.  JerylFypManager application is closed.

    Use case ends.

**Extensions**

* Nil


*{More to be added}*

--------------------------------------------------------------------------------------------------------------------
### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  User with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3.  The system should respond in at most 1 second.
4.  A user with no coding background should be able to use JerylFypManager.
5.  Notification sent to users upon updates to FYPs.
6.  Personal details of students should only be accessible by supervising professor.
7.  System should be able to support the whole SoC cohort (around 1500 users) at the same time.
8.  JerylFypManager is accessible 24/7.

*{More to be added}*

--------------------------------------------------------------------------------------------------------------------
### Glossary

* **FYP**: Final Year Project, taken by SoC students in their final year of study
* **SoC**: School of Computing, in National University of Singapore
* **JerylFypManager**: A platform used by SoC Professors for supervising student's FYP
* **Professor**: An academic staff of SoC who is supervising a student's FYP
* **Student**: A SoC student who is taking FYP in their final year of study

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
