---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the [AddressBook Level-3](https://github.com/nus-cs2103-AY2223S1/tp) project created by
the [SE-EDU initiative](https://se-education.org).

Libraries used:

* [JavaFX](https://openjfx.io/)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103T-F11-1/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-F11-1/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-F11-1/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete -p 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-F11-1/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `InternshipListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-F11-1/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-F11-1/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

<div style="page-break-after: always;"></div>

### Logic component

The **API** of this component is specified in [`Logic.java`](https://github.com/AY2223S1-CS2103T-F11-1/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeletePersonCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete -p 1")` API call.

![Interactions Inside the Logic Component for the `delete -p 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPersonCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPersonCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPersonCommandParser`, `DeletePersonCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<div style="page-break-after: always;"></div>

### Model component
The **API** of this component is specified in [`Model.java`](https://github.com/AY2223S1-CS2103T-F11-1/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div style="page-break-after: always;"></div>
<br>
<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

<div style="page-break-after: always;"></div>

### Storage component

The **API** of this component is specified in [`Storage.java`](https://github.com/AY2223S1-CS2103T-F11-1/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

<div style="page-break-after: always;"></div>

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Edit Internship

#### Implementation

Given below is an example usage scenario.

1. InterNUS shows a list of internships. The user edits the first internship in the list using the command `edit -i 1 s/R`.
2. The command is accepted by the `Logic` component, which passes it to the `AddressBookParser`.
3. The `AddressBookParser` creates the corresponding parser, `EditInternshipCommandParser` to parse the arguments `1 s/R`.
4. The parser stores the details to edit in an `EditInternshipDescriptor`. The parser also creates an `EditInternshipCommand` with the index `1` and the descriptor as arguments.
5. The `EditInternshipCommand` is returned to the `LogicManager`, which then calls its `execute()` method.
6. To retrieve the list of internships, `Model#getFilteredInternshipList()` is called. The target internship (the one at index `1`) is retrieved from the `FilteredList`. An updated `Internship` is also created via a self invocation: `createEditedInternship()`.
7. `Model#setInternship()` is called to replace the target internship with the updated internship.
8. `Model#updateFilteredInternshipList()` is called to update the internship list to display all internships.
9. Finally, `EditInternshipCommand` creates a `CommandResult` to denote that the operation is completed, and returns this `CommandResult` back to `LogicManager`.

The sequence diagram is given below (some details are omitted, such as the instantiation of the `EditInternshipDescriptor`).
![EditInternshipSequence](images/EditInternshipSequence.png)

The following activity diagram summarizes what happens when a user executes an `EditInternshipCommand`:
![EditInternshipActivity](images/EditInternshipActivity.png)

<div style="page-break-after: always;"></div>

### Sort Person list

#### Implementation

1. When the user sorts the person list, the command goes through the `LogicManager`, which will then go through the `AddressBookParser`.
2. The `AddressBookParser` will then create the corresponding parser for the command, which is `SortPersonCommandParser`.
3. After which, it will pass the argument (the full command excluding the command word and flag) to this parser.
4. The command parser will then create a `SortPersonCommand` with the corresponding internal variable (`n/` means sort by name, `c/` means sort by associated company name). This is facilitated by the `Criteria` enumeration.
5. The method then returns all the way back to `LogicManager`, which is then stored as a variable called `command`.
6. Then, the command is executed by calling the `execute()` method of `SortPersonCommand` (the command that was returned earlier) directly.
7. Based on its internal variable, it will call `sortPersonlist()` in the `Model` class on the person list, and passes the sort criterion.
8. The person list will then set the comparator based on the criterion that was passed earlier, and the list is sorted based on that comparator.
9. Afterwards, `SortPersonCommand` creates a `CommandResult` to denote that the operation is completed, and returns this `CommandResult` back to `LogicManager`.

The sequence diagram is given below.
![SortPersonSequence](images/SortPersonSequence.png)

The following activity diagram summarizes what happens when a user attempts to sort the person list:
![SortPersonActivity](images/SortPersonActivity.png)

#### Design considerations

The sorting mechanism is designed in a way to keep all operations to the `SortPersonCommand` object itself, 
which will them prompt the `Model` to set the comparator of the person list. 
This is consistent with the other commands, as they will go through the same process, since each command has their own class and parser (if needed).

<div style="page-break-after: always;"></div>

### Find Person

#### Implementation

1. When the user attempts to find a person or internship, the command goes through the `LogicManager`, which will then go through the `AddressBookParser`.
2. The `AddressBookParser` will then create the corresponding parser for the command, `FindPersonCommandParser`.
3. After which, it will pass the argument (the full command excluding the command word and flag) to this parser.
4. The command parser will then create a `FindPersonCommand` by constructing and storing a `predicate` that checks, 
   for each field of the `Person`, whether it contains any of the specified keywords.
   The determining of which keyword is for which field is done via parsing of prefixes in the command,
   and these prefixes are consistent with the ones used in other commands such as the `Add` command.
5. The method returns a `Command` to the `LogicManager` which is stored as a variable called `command`.
6. This `command` is executed by calling its `execute()` method.
7. This will invoke the `updateFilteredPersonList` method of the `model` with the `predicate` that was constructed earlier.
8. The `predicate` will then be passed to the `filteredPersonList` of the `model` and used to filter the list via `setPredicate`.
9. Afterwards, `FindPersonCommand` creates a `CommandResult` to denote that the operation is completed, and returns this `CommandResult` back to `LogicManager`.

The sequence diagram is given below.
![FindPersonSequence](images/FindPersonSequence.png)

The following activity diagram summarizes what happens when a user attempts to find a person:
![FindPersonActivity](images/FindPersonActivity.png)

#### Design considerations

The finding mechanism is designed in a way to keep all operations to the `FindPersonCommand` object itself,
which will them prompt the `Model` to set the predicate of the person list.
This is consistent with the other commands, as they will go through the same process, since each command has their own class and parser (if needed).

<div style="page-break-after: always;"></div>

### Link Person and Internship

#### Implementation

1. When the user attempts to link a person amd an internship, the command goes through the `LogicManager`, which will then go through the `AddressBookParser`.
2. The `AddressBookParser` will then create the corresponding parser for the command, `LinkCommandParser`.
3. After which, it will pass the argument (the full command excluding the command word and flag) to this parser.
4. The command parser will then create a `LinkCommand` with the specified person index and internship index.
5. The method then returns all the way back to `LogicManager`, which is then stored as a variable called `command`.
6. This `command` is executed by calling its `execute()` method.
7. This will invoke the `getFilteredPersonList` and `getFilteredInternshipList` methods of the `model`
8. Based on this `command` specified person index and internship index, the respective person and internship will be fetched from the internal person list and internship list in InterNUS. New `Person` and `Internship` objects will be created with the fields `internshipId` and `contactPersonId` updated respectively.
9. `setPerson` and `setInternship` methods of the `model` will be invoked to update the person and internship in InterNUS.
11. Afterwards, `LinkCommand` creates a `CommandResult` to denote that the operation is completed, and returns this `CommandResult` back to `LogicManager`.

The sequence diagram is given below.
![LinkSequence](images/LinkSequenceDiagram.png)

The following activity diagram summarizes what happens when a user attempts to link a person and an internship:
![LinkActivity](images/LinkActivityDiagram.png)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Can type fast
* Have some experience with Command Line Interfaces
* Are actively seeking internship opportunities
* Having trouble keeping track of multiple internship applications and their progress (over multiple rounds)

**Value proposition**:

* Keep track of multiple company contacts and applications’ progress simultaneously (the reply rates from companies are very low)
* Keep track of colleagues' information post-internship
* Keep track of internship applications of multiple companies
* Keep track of contact information of hiring managers, and link them to internship openings

<div style="page-break-after: always;"></div>

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …           | I can …                                                                                | So that                                                                                                |
|----------|------------------|----------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| * * *    | First time user  | See a summary of commands                                                              | I can know the functionalities of the app quickly                                                      |
| * * *    | First time user  | Delete internship status                                                               | I know that I have finished my interview process with the company                                      |
| * *      | First time user  | View the user guide                                                                    | I can know all the functions                                                                           |
| * *      | First time user  | See the app populated with sample contacts when launching the app for the first time   | I can easily see how the app will look like when it is in use                                          |
| * *      | First time user  | See a help message when I use a command word incorrectly                               | I can easily learn how to use individual commands without the need to keep referring to the user guide |
| * * *    | Second time user | Pre-load my data from the previous session                                             | I do not need to re-add everyone again                                                                 |
| * * *    | Second time user | Purge all current data                                                                 | I can get rid of sample contacts I used for exploring the app                                          |
| * *      | Second time user | See a summary of the internship information I have keyed in so far on starting the app | I can know where I left off from the last time I used the app                                          |
| * * *    | Lazy user        | Find contacts by name                                                                  | I do not need to manually filter them                                                                  |
| * * *    | Lazy user        | Find internships by company and role                                                   | I do not need to manually filter them                                                                  |
| * * *    | User             | Edit contacts and internships individually                                             | I do not need to delete and re-add to fix a typo error                                                 |
| * * *    | User             | Delete contacts and internships individually                                           | I can remove contacts and internships that I no longer need                                            |
| * * *    | User             | Add the company name to each contact                                                   | I know which company the contact is representing                                                       |
| * * *    | User             | Add internship status to each internship                                               | I know whether I have been accepted, rejected or still awaiting reply                                  |
| * * *    | User             | Sort internships by internship status                                                  | I can know which company interview I can prepare for                                                   |
| * * *    | Forgetful user   | Sort internships by upcoming interview dates                                           | I can see a clear timeline and prevent clashing dates                                                  |
| * *      | User             | Add roles to each internship                                                           | I know which role I am applying for (front-end, back-end etc)                                          |
| * *      | User             | Add a company to each internship                                                       | I know which company I am apply for (Google, Shopee etc)                                               |
| * * *    | User             | Link a contact to an internship as its contact person                                  | I know who to contact for potential updates if I need to                                               |
| * * *    | User             | Unlink a contact and an internship                                                     | I can remove the contact person for an internship once I no longer need it                             |
| * *      | Expert user      | Mass import contacts directly                                                          | I can use the app on another device                                                                    |
| *        | User             | Personalize GUI colors and color themes (light mode, dark mode etc.)                   | I can make the app more visually appealing                                                             |

<div style="page-break-after: always;"></div>

### Use cases

(For all use cases below, the **System** is `InterNUS` and the **Actor** is the `User`, unless specified otherwise)

**Use case: UC1 - Add a person to InterNUS**

**MSS**

1. User requests to add a person to InterNUS.
2. InterNUS adds the new person.

   Use case ends.

**Extensions**

* 1a. The person is missing compulsory details.
    * 1a1. InterNUS notifies the User about the missing details.
    * 1a2. User specifies the details of the person to add.

      Steps 1a1-1a2 are repeated until the required details are entered.<br>
      Use case resumes at step 2.

* 1b. The person's details are of the incorrect format.
    * 1b1. InterNUS notifies the User about the correct format required.
    * 1b2. User corrects the details of the person.

      Steps 1b1-1b2 are repeated until the details entered are of the correct format.<br>
      Use case resumes at step 2.

* 1c. The person is a duplicate.
    * 1c1. InterNUS notifies the User that the person already exists.

      Use case ends.

**Use case: UC2 - Add an internship to InterNUS**

**MSS**

1. User requests to add an internship to InterNUS.
2. InterNUS adds the new internship.

   Use case ends.

**Extensions**

* 1a. The internship is missing compulsory details.
    * 1a1. InterNUS notifies the User about the missing details.
    * 1a2. User specifies the details of the internship to add.

      Steps 1a1-1a2 are repeated until the required details are entered.<br>
      Use case resumes at step 2.

* 1b. The internship's details are of the incorrect format.
    * 1b1. InterNUS notifies the User about the correct format required.
    * 1b2. User corrects the details of the internship.

      Steps 1b1-1b2 are repeated until the details entered are of the correct format.
      Use case resumes at step 2.

* 1c. The internship is a duplicate.
    * 1c1. InterNUS notifies the User that the internship already exists.

      Use case ends.

**Use case: UC3 - Delete a person**

**MSS**

1.  User requests to list persons.
2.  InterNUS shows a list of persons.
3.  User requests to delete a specific person in the list.
4.  InterNUS deletes the person.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. InterNUS shows an error message.

      Use case resumes at step 3.

* 3b. The person found at the index is a contact person for an internship.
    * 3b1. InterNUS looks up the affected internship.

    * 3b2. InterNUS removes the internship's link to that person.

      Use case resumes at step 4.

**Use case: UC4 - Delete an internship**

**MSS**

1.  User requests to list internships.
2.  InterNUS shows a list of internships.
3.  User requests to delete a specific internship in the list.
4.  InterNUS deletes the internship.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. InterNUS shows an error message.

      Use case resumes at step 3.

* 3b. The internship found at the index is associated with a person.

    * 3b1. InterNUS looks up the affected person.

    * 3b2. InterNUS removes the person's link to the internship.

      Use case resumes at step 4.

**Use case: UC5 - Link a contact person to an internship**

**MSS**

1. User adds an internship to InterNUS (UC2).
2. User adds a person to InterNUS (UC1).
3. User requests to set the added person as the contact person for the added internship.
4. InterNUS sets the person as the contact person for the internship.

    Use case ends.

**Extensions**

* 3a. The internship already has a contact person linked to it.<br>
    Use case ends.

* 3b. The person is already the contact person of another internship.<br>
    Use case ends.

**Use case: UC6 - Add an interview date for an internship**

**MSS**

1. User adds an internship to the internship list (UC2).
2. User requests to set the interview date of a specific internship.
3. InterNUS sets the interview date for the internship.

   Use case ends.

**Extensions**

* 2a. The interview date given is invalid.

    * 2a1. InterNUS notifies the user why the given date is invalid.
    * 2a2. User corrects the invalid interview date.

      Steps 2a1-2a2 are repeated until the date given is of the correct format.<br>
      Use case resumes at step 3.

**Use case: UC7 - Sort person list**

**MSS**
1. User requests to sort person list and supplies a criterion prefix.
2. InterNUS sorts person list based on the specified criterion prefix.

   Use case ends.

**Extensions**

* 1a. User submits a blank or invalid criterion prefix.

     * 1a1. InterNUS notifies the user that the criterion prefix is invalid and only 1 criterion prefix can be specified.
     * 1a2. User enters a criterion prefix.

       Steps 1a1-1a2 are repeated until the criterion prefix entered is valid.<br>
       Use case resumes at step 2.

* 1b. User submits more than 1 criterion prefixes.

     * 1b1. InterNUS notifies the user that the criterion prefix is invalid and only 1 criterion prefix can be specified.
     * 1b2. User enters 1 criterion prefix.

       Steps 1b1-1b2 are repeated until 1 valid criterion prefix is entered .<br>
       Use case resumes at step 2.

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons and 1000 internships without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. A user will not be able to communicate with the people in the contact. 
5. A user will not be able to share contacts with others.
6. Should work only for single user.
7. The data should be stored in a human editable text file.
8. The GUI should work well for standard screen resolutions 1920x1080 and higher and for screen scales 100% and 125%. 
9. The GUI should be usable for resolutions 1280x720 and higher and for screen scales 150%.
10. Each contact person can only link to one internship and each internship is only linked to one contact person.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

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

<div style="page-break-after: always;"></div>

### Editing a person

1. Editing a person in a filtered list of persons.

    1. Prerequisites: Find the person of interest using the `find -p [PREFIX/KEYWORD]` command. Assume  a list of at least 1 person is shown.

    1. Test case: `edit -p 1 e/johndoe@gmail.com`<br>
       Expected: First person in the list has his/her email updated. Details of the edited person shown in the status message.

    1. Test case: `edit -p 0 p/98981234`<br>
       Expected: No person is edited. Error details shown in the status message.

    1. Other incorrect edit commands to try: `edit -p 1`, `edit -p x t/colleague`(where x is larger than the list size).<br>
       Expected: Similar to previous.

<div style="page-break-after: always;"></div>

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list -p` command. Multiple persons in the list.

   1. Test case: `delete -p 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.

   1. Test case: `delete -p 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete -p`, `delete -p x`, `...` (where x is larger than the list size).<br>
      Expected: Similar to previous.

<div style="page-break-after: always;"></div>

### Sorting person list

1. Sorts person list while all persons are being shown

   1. Prerequisites: List all persons using the `list -p` command. Multiple persons in the list.

   1. Test case: `sort -p n/`<br>
      Expected: Person list is sorted by the persons' names.

   1. Test case: `sort -p c/`<br>
      Expected: Person list is sorted by the company's names that each person is working at. Persons without an attached company name are listed at the bottom of the list.

   1. Test case: `sort -p n/ c/`<br>
      Expected: Error is thrown to show that only 1 criterion prefix can be used, and it should be recognisable.

   1. Other incorrect sort person commands to try: `sort -p rbivrv`, `sort -p      `, `sort -p n/ krvnkr`, `...`.<br>
      Expected: Similar to previous.

2. Sorts person list while not all persons are being shown

   1. Prerequisites: Filter the persons list with `find -p` command. Multiple persons in the list.

   1. Test case: `sort -p n/`<br>
   Expected: Person list is sorted by the remaining persons' names.

   1. Test case: `sort -p c/`<br>
   Expected: Person list is sorted by the company's names that each remaining person is working at. Remaining persons without an attached company name are listed at the bottom of the list.

   1. Test case: `sort -p n/ c/`<br>
   Expected: Error is thrown to show that only 1 criterion prefix can be used, and it should be recognisable.

   1. Other incorrect sort person commands to try: `sort -p rbivrv`, `sort -p      `, `sort -p n/ krvnkr`, `...`.<br>
   Expected: Similar to previous.

<div style="page-break-after: always;"></div>

### Finding a person

1. Finds a person by each given field.

    1. Prerequisites: List is not empty.

    1. Test case: `find -p n/alex`<br>
       Expected: Finds all persons whose name contains the string "alex".

    1. Test case: `find -p c/google`<br>
       Expected: Finds all persons whose company contains the string "google".

    1. Test case: `find -p`<br>
       Expected: Error is thrown to show that at least 1 valid field prefix should be used.

    1. Other incorrect find person commands to try: `find -p asdf`, `find -p      `, `...`.<br>
       Expected: Similar to previous.

<div style="page-break-after: always;"></div>

### Saving data

1. Dealing with missing/corrupted data files

   1. Prerequisites: Go to the folder `data` and find the file `addressbook.json`. App is not launched.

   1. Test case: Delete the `data` folder <br>
   Expected: On app launch, InterNUS is populated with sample data.

   1. Test case: Delete the `addressbook.json` file <br>
   Expected: On app launch, InterNUS is populated with sample data.

   1. Test case: Delete the second line of `addressbook.json`, which should be `"persons" : [ {`<br>
   Expected: On app launch, InterNUS will have no persons or internships data (i.e. empty lists).

