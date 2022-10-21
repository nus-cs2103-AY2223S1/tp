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

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103T-T15-3/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-T15-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-T15-3/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-T15-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `TutorListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-T15-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-T15-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Tutor` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-T15-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `TuthubParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a tutor).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `TuthubParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `TuthubParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-T15-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores tuthub data i.e., all `Tutor` objects (which are contained in a `UniqueTutorList` object).
* stores the currently 'selected' `Tutor` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Tutor>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Tuthub`, which `Tutor` references. This allows `Tuthub` to only require one `Tag` object per unique tag, instead of each `Tutor` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-T15-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both tuthub data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `TuthubStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `tuthubbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### View Feature
<ins>Implementation</ins>

Similar to the `help` command, the `view` command involves operations within the UI to display/hide the tutor details 
panel. The communication between the logic and UI classes is facilitated by the `CommandResult` class, where the 
following field has been added:
- `CommandResult#isView` - Indicates if the current command is a `view` command.

Given below is an example usage scenario when the user enters a `view` command in the command box and how the view 
mechanism behaves at each step (omitting the parsing details).

Step 1: The user enters the command `view 1`.

Step 2: Upon parsing, a new `ViewCommand` based on the valid index.

Step 3: When the `ViewCommand` is executed, a new `CommandResult` with `isView` set to `true` is created and 
`ModelManager#tutorToView` is updated with the selected tutor.

Step 4: Upon recognising the `CommandResult` is of `isView` type, `MainWindow` calls `logic#getTutorToView()` to get the 
tutor to be displayed, which is passed into `MainWindow#handleView(Tutor tutor)`.

Step 5: This causes the `TutorDetailsPanel` of the `tutor` to be set as visible, resulting in the side panel being displayed.

The following sequence diagram demonstrates the above operations (excluding the parsing details):

![ViewSequenceDiagram](./images/ViewSequenceDiagram.png)

<ins>Design Considerations</ins>

**Aspect: Method to pass a `Tutor` to UI**
- **Alternative 1:** Store the tutor to be viewed as a field in `Model` **(chosen)**.
  - Pros: Better OOP practice since `Model` handles all the data related to tutors. 
  - Cons: The `tutorToView` may be null if there are no tutors in the list to be displayed, so more checks may be needed.

- **Alternative 2:** Store the tutor in `CommandResult`.
  - Pros: Easier to implement and fewer methods may be needed in `Logic` and `Model` as the tutor can be passed to 
  the `MainWindow` directly through `CommandResult`.
  - Cons: Poor OOP practice as it does not make sense for `CommandResult` to store a `Tutor`, and other commands do not 
  require a `Tutor` object to be stored.

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

* tech-savvy tuition agents
* has to manage a significant number (up to hundreds) of tutor profiles
* seeks a more organised and systematic way of managing profiles as compared to excel sheets
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* _Problem_: Multiple entries of the same tutor information as they have to repeatedly enter the same information when applying for different jobs
* _Solution_: Our tuthub detects duplicate tutor profiles and merges the additional information into the existing profile.
  <br/><br/>
* _Problem_: Too many tutors with no specific way to organise them systematically.
* _Solution_: Our tuthub can categorise the tutors based on different criteria and provides features to search for profiles easily.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                               | I can …​                             | So that I can…​                                                                   |
|--------| --------------------------------------|--------------------------------------|-------------------------------------------------------------------------------------------|
| `* * *` | NUS Computing Professor                                  | list all tutor profiles              | get a quick view of all available tutors                                                  |
| `* * *` | NUS Computing Professor                                  | add a new tutor                      | track their profiles                                                                      |
| `* * *` | NUS Computing Professor                                  | find a specific tutor by name easily | filter tutor names                                                                        |
| `* * *` | NUS Computing Professor                                  | delete a tutor profile               | remove tutors that are no longer available for work                                       |
| `* * *` | NUS Computing Professor                                  | save data                            | there is a local backup on the computer                                                   |
| `* * *` | NUS Computing Professor                                  | exit the program                     |                                                                                           |
| `* *`  | NUS Computing Professor                                  | view a tutor's full profile          | find out more about their performance and contact details to reach out for future TA roles |


### Use cases

(For all use cases below, the **System** is `Tuthub` and the **Actor** is the `NUS Computing Professor`, unless specified otherwise)

**Use case: UC1 - Listing all tutor profiles**

**MSS**

1.  User requests to list all tutors.
2.  Tuthub shows a list of tutors.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case: UC2 - Viewing a specific tutor profile**

**MSS**

1. User requests to list all tutors.
2. User requests to view a specific tutor's details using their displayed index on the list.
3. Tuthub displays the full details of the Tutor on a side panel and shows a success message.

    Use case ends.

**Extensions**

* 2a. The input index is invalid.
    * Tuthub displays an error message.

      Use case resumes from step 2.  
    

**Use case: UC3 - Add a tutor**

**MSS**

1.  User requests to add a tutor profile in the list.
2.  Tuthub shows the list of tutors with the new tutor profile added.

    Use case ends.

**Extensions**

* 1a. Tuthub detects an error in the entered data.
  * 1a1. Tuthub requests for the correct data.
  * 1a2. User enters new data.
  * Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes from step 2.

    Use case ends.

**Use case: UC4 - Delete a tutor**

**MSS**

1.  User requests to list tutors.
2.  Tuthub shows a list of tutors.
3.  User requests to delete a specific tutor in the list.
4.  Tuthub deletes the tutor.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Tuthub shows an error message.

      Use case resumes at step 2.

**Use case: UC5 - Exit the program**

**MSS**

1.  User requests to exit Tuthub.
2.  Tuthub saves list of tutor profiles in hard disk.

    Use case ends.


### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 tutors without a noticeable sluggishness in performance for typical usage.
3. Should work without internet connection.
4. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
5. Performance requirement: The system should respond within a second.
6. Technical requirement: The system should work on both 32-bit and 64-bit environments.
7. Constraints: The product should be for a single user i.e. (not a multi-user product).
8. Constraints: The product needs to be developed in a breadth-first incremental manner over the project duration.
9. Constraints: The data should be stored locally and should be in a human editable text file.
10. Constraints: The file size of the deliverables should be reasonable. Product (i.e. JAR/ZIP file) should not exceed 100 MB. Documents (i.e. PDF files) should not exceed 150 MB.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Tutor Profile**: A profile containing the tutor's details, such as `NAME`, `PHONE_NUMBER`, `GENDER`, `EMAIL`, etc.

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

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. _{ more test cases …​ }_

### Viewing a tutor's full details

1. Viewing a tutor's full details

    1. Prerequisites: List all tutors using the `list` command. Multiple tutors in the list.

    2. Test case: `view 1`<br>
        Expected: Details panel of the first tutor in the list is displayed. Details of the tutor viewed shown in the status message.

    3. Test case: Click on the first tutor card in the list with mouse.<br>
       Expected: Details panel of the first tutor in the list is displayed. Details of the tutor viewed shown in the status message.
   
    4. Test case: `view 0`<br>
       Expected: No tutor panel displayed. Error details shown in the status message.

    5. Other incorrect view commands to try: `view`, `view x` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Deleting a tutor

1. Deleting a tutor while all tutors are being shown

   1. Prerequisites: List all tutors using the `list` command. Multiple tutors in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No tutor is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
