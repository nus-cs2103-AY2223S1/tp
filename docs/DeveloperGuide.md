---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **1. Acknowledgements**

* Adapted `Theme` class to switch between dark and light mode from [here](https://github.com/junlong4321/tp/blob/master/src/main/java/tutorspet/ui/stylesheet/Stylesheet.java).
* Adapted timetable GUI implementation from [here](https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/SchedulePanel.java).

--------------------------------------------------------------------------------------------------------------------

## **2. Introduction**

Made for SOC (School of Computing) Professors, **ProfNUS** is the **easiest way to keep track of your teaching schedule and organize information regarding the students and modules you teach.**
It is optimized for users who prefer Command Line Interface (CLI) so that frequent tasks can be done faster by typing in commands while providing users with a simple and clean Graphical User Interface (GUI).
The main interaction with **ProfNUS** will thus be done through commands.

The features of ProfNUS include:
* Keeping track of teaching schedules.
* Recording module information.
* Recording of student information.
* Viewing module and student details.

The purpose of this Developer Guide is to help you to understand the design and implementation of **ProfNUS**, so that you can contribute to our project.

--------------------------------------------------------------------------------------------------------------------

## **3. Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **4. Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### 4.1. Architecture

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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deletestu 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### 4.2. UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-W11-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-W11-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-W11-2/tp/blob/master/src/main/resources/view/HelpWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### 4.3. Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-W11-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `ProfNUSParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddStuCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delstu 1")` API call.

![Interactions Inside the Logic Component for the `delstu 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteStudentCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ProfNUSParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddStuCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddStuCommand`) which the `ProfNUSParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddStuCommandParser`, `DeleteStuCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### 4.4. Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-W11-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="600" />


The `Model` component,

* stores the data from ProfNUS i.e., all `Student` objects (which are contained in a `UniquePersonList` object) etc.
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `ProfNus`, which `Person` references. This allows `ProfNus` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>
<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

#### 4.4.1. Student

**API** : [Student.java](https://github.com/AY2223S1-CS2103T-W11-2/tp/blob/master/src/main/java/seedu/address/model/person/Student.java)

<img src="images/StudentClassDiagram.png"  width="450" />

The `Student` component

- represents a student in ProfNUS

#### 4.4.2. Module

**API** : [Module.java](https://github.com/AY2223S1-CS2103T-W11-2/tp/blob/master/src/main/java/seedu/address/model/module/Module.java)

<img src="images/ModuleClassDiagram.png"  width="450" />

The `Module` component

- represents a module in ProfNUS

#### 4.4.3. Schedule

**API** : [Schedule.java](https://github.com/AY2223S1-CS2103T-W11-2/tp/tree/master/src/main/java/seedu/address/model/module/schedule)

<img src="images/Schedule.png" alt="ScheduleUML"  width="450" />

The `Schedule` component

- represents a schedule of its corresponding module

### 4.5. Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-W11-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both ProfNUS data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `ProfNUSStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)



### 4.6. Common classes

Classes used by multiple components are in the `seedu.profnus.commons` package.

--------------------------------------------------------------------------------------------------------------------


## **5. Implementation**

This section describes some noteworthy details on how certain features are implemented.

### 5.1. Person Model, Student Class and Student ID

#### Implementation

The class diagram below shows our current implementation of the `Student` class
which extends from the `Person` class.

![StudentClassDiagram](images/StudentClassDiagram.png)

Each `Student` in ProfNUS will contain the basic information in the `Person` class.
Additionally, it will also contain these fields:
- `StudentId`: A unique student id to identify each student.
- `TelegramHandle`: The student's telegram handle.
- `studentModuleInfo`: A set of ModuleCode's that the student is taking.
- `teachingAssistantInfo`: A set of ModuleCode's that the student is a teaching assistant for.
- `classGroups`: A set of String's that represent the class groups the student is in.

#### Design consideration:

##### Aspect 1: Inheritance vs Refactoring of Code

* **Alternative 1 (current choice):** Make `Student` inherit from `Person`
    * Pros:
      * More OOP since `Student` is a `Person`, thus allowing more flexibility through polymorphism.
      * Improves the extensibility of the project since we are not constrained to
      having a single class to represent all the people in the project. Can include different types
      of persons in the future such as other professors.
    * Cons:
      * Harder to implement, have to write more code and test cases.
      * Have to consider Liskov Substitution Principle when writing code.

* **Alternative 2:** Refactor `Person` into `Student` class
  * Pros:
    * Easier to implement since it just involves the refactoring of code.
  * Cons:
    * Limits the extensibility of the project to just a student managing application.

##### Aspect 2: How to ensure Student being added is unique

* **Alternative 1 (current choice):** Ensure that the `StudentId` of each student is unique
  * Pros:
    * Convenient and easy to implement since all students have a unique studentId when they
    matriculate into NUS
  * Cons:
    * The `StudentId` field is editable, and we cannot ensure that the `StudentId` that is added is
    a valid Id of a student in NUS.

* **Alternative 2:** Ensure that students do not have the same name/fields
  * Pros:
    * Already implemented in AB3, do not need to change much
  * Cons:
    * NUS has many students, and it is inevitable that some of them will share the exact same name

### 5.2. Editing a student feature

#### Implementation

The edit student mechanism is facilitated by `EditStuCommand`, `EditStuCommandParser` and `EditStudentDescriptor`
classes. The `EditStuCommandParser` is in charge of parsing the user's input which then creates a
`EditStudentDescriptor` and returns a `EditStuCommand`. When the `EditStuCommand` is executed, it modifies the student
at the index provided by the user.

The following sequence diagram shows how the `editstu` command works:
![EditStuCommandSequenceDisgram](./images/EditStuCommandSequenceDiagram.png)

After ProfNUS receives the command to edit a `Student` with the given index, it will find the corresponding
`Student` and edit its details.

During the execution, the following validity checks will be conducted:
- Module existence check - The model will check if it can find the module's indicated as the student's modules
  or, it's teaching modules. If any module specified is not found, then a `CommandException` will be thrown.
- Duplicate Student check - The model will check if the edited student has the same `StudentId` as the
  rest of the students in ProfNUS. If such a duplicate is found, then a `CommandException` will be thrown and
  the student will not be edited.
- Teaching conflict check - The model will check if the edited student is a teaching assistant and a student
  of the same module. If a conflict occurs, then a `CommandException` will be thrown.

The following activity diagram summarizes what happens when a user executes a `editstu` command.

![EditStudentActivityDiagram](./images/EditStudentActivityDiagram.png)

#### Design consideration:

##### Aspect: How editstu executes

|                                                           | Pros                                                                         | Cons                                                                                               |
|-----------------------------------------------------------|------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| **Option 1** <br/> Edit by StudentID                      | Allows convenience if the user has a list of StudentIDs                      | User might not have a list of StudentIDs and it would take a longer time to edit multiple students |
| **Option 2** <br/> Edit by making use of indexing in list | Allows convenience if the user wants to edit multiple students from the list | Have to use other commands such as `find` to search through a large list of students               |

Reason for choosing option 2:

A professor is unlikely to remember a Student's Id and it would also be a hassle to type such a long Id as compared
to just typing the index of the student. Additionally, with other commands to search through the list of students by module
and name, it should be easy for a professor to find the desired student to edit. Therefore, option 2 is preferred.

### 5.3. Module Class

#### Implementation

The class diagram below shows our current implementation of the `Module` class.
![ModuleClassDiagram](images/ModuleClassDiagram.png)

Each `Module` in ProfNUS will contain the following fields:
- `ModuleName`: The name of the module.
- `ModuleCode`: The unique module code used to identify the module.
- `ModuleDescription`: The description of the module.
- `tags`: A set of string tags that describes the module.
- `schedules`: A set of Schedule's that represent the classes and lectures the user has for the module.

#### Design consideration:

##### Aspect: How to ensure Module being added is unique

* **Alternative 1 (current choice):** Ensure that the `ModuleCode` of each module is unique
    * Pros:
        * Convenient and easy to implement since all modules have a unique module code given by NUS.
    * Cons:
        * The `ModuleCode` field is an editable string, and we cannot ensure that the `ModuleCode` that is added is
          a valid code in NUS.

* **Alternative 2:** Ensure that the `ModuleName` of each module is unique
  * Pros:
      * `ModuleCode` would not be a necessary field, less information required.
  * Cons:
      * NUS modules can share the same name. For example, CS2103 and CS2103T have the module name Software Engineering. Thus, with this implementation, you would not be able to add two Software Engineering modules.

### 5.4. Viewing the module list feature

#### Implementation

The proposed view module list functionality is accomplished by `ListModuleCommand` which extends the `Command` class. The `ListModuleCommand` overrides the following method:

- `ListModuleCommand#execute(Model model)` — Executes the command and displays all modules

The following sequence diagram shows how view module operation works :

![ListModuleSequence](images/ListModuleSequence.png)

### 5.5. Viewing the students and tutors in a module feature

#### Implementation

The find module details mechanism is facilitated by `ModuleViewCommand` and `ModuleViewCommandParser`. It allows users to search for modules based on module code.
It uses `ModelManager#updateFilteredModuleList(Predicate<Module> predicate)` which is exposed in the Model interface as `Model#updateFilteredModuleList(Predicate<Module> predicate)`.
The method updates the student and tutor list and filters it according to the given predicate which will then be reflected accordingly in the GUI.

The following sequence diagram shows how the find module by module code operation works:
![ViewModuleSequenceDiagram](./images/ViewModuleSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a `mview` command:

![ViewModuleActivityDiagram](./images/ViewModuleActivityDiagram.png)

#### Design consideration:

##### Aspect: How mview executes

|                                             | Pros                                                     | Cons                                    |
| ------------------------------------------- | -------------------------------------------------------- | --------------------------------------- |
| **Option 1** <br/> Searching by module code | Allows module with different codes to have the same name | User might not remember the module code |
| **Option 2** <br/> Searching by module name | Easier for user to search for the module                 | Modules cannot have the same name       |

Reason for choosing option 1:
Modules like CS2103T, CS2103R and CS2103 have the same module name "Software Engineering". If we allow searching by module name, the program would not know which "Software Engineering" module to display.
This would mean that we would need to have unique module names. However, this is not possible if the professor is teaching modules that have the same name but different code.
As module code is the only unique field Module has, we decided to view modules by module code only to avoid any errors.

### 5.6. Editing a module feature

#### Implementation

The proposed edit module functionality is facilitated by `EditModuleCommand`. It extends `Command` and overrides the method `Command#execute(Model model)`.

The following sequence diagram shows how editing a module works:

![EditModuleSequence](images/EditModuleSequenceDiagram.png)

<div style="page-break-after: always;"></div>

The following activity diagram summarizes what happens when a user executes a `medit` command.

![EditModuleActivity](images/EditModuleActivityDiagram.png)

#### Design consideration:

##### Aspect: How medit executes

|                                                           | Pros                                           | Cons                                               |
|-----------------------------------------------------------|------------------------------------------------|----------------------------------------------------|
| **Option 1** <br/> Edit by module code                    | Allows convenience if module code is known     | User might not remember the module code            |
| **Option 2** <br/> Edit by making use of indexing in list | Allows convenience if module code is forgotten | Have to use `mlist` command to obtain the indexing |

Reason for choosing option 1:
A professor is more highly likely to remember the module codes of the modules that he is teaching rather than the index in the list in our application. Hence, an additional step would be required of the professor if option 2 were to be chosen. Therefore, option 1 is preferred.

### 5.7. Viewing more details(Name, Description, Tags, Schedules) of a module feature

#### Implementation

The proposed view more details about a target module functionality is facilitated by `ViewTargetModuleCommand`. It extends `Command` and overrides the method `Command#execute(Model model)`.

The following sequence diagram shows how viewing more details about a module works:

![ViewTargetModuleSequence](images/ViewTargetModuleSequenceDiagram.png)

<div style="page-break-after: always;"></div>

The following activity diagram summarizes what happens when a user executes a `vtarget` command.

![ViewTargetModuleActivity](images/ViewTargetModuleActivityDiagram.png)

#### Design consideration:

##### Aspect: How vtarget executes

|                                                           | Pros                                                                                                 | Cons                                                                                              |
|-----------------------------------------------------------|------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| **Option 1** <br/> View by module code                    | Allows convenience for user since user knows what module he would like to get more information about | User might be confused if similar modules with similar module codes exist, eg. CS2103 and CS2103T |
| **Option 2** <br/> View by making use of indexing in list | Allows convenience if module code is forgotten                                                       | Have to use `mlist` command to obtain the indexing                                                |

Reason for choosing option 2:
As shown below in our [Use Cases](#73-use-cases), the expected behaviour of a Professor is to run the command `mlist` to view
the modules that he has added to ProfNUS first, then choosing to view more details about a module. Hence, the
indexing would be clearly known to the Professor and thus, option 2 is preferred.

###  5.8. Adding a schedule feature

#### Implementation

The proposed add schedule functionality is accomplished by `AddScheduleCommand` which extends the `Command` class. The `AddScheduleCommand` overrides the following method:

- `AddScheduleCommand#execute(Model model)` — Executes the command and add the new schedule to the ProfNUS

The following sequence diagram shows how add schedule operation works

![AddScheduleSequence](images/AddScheduleSequenceDiagram.png)

After the ProfNUS receives the instruction to add a new `Scheudule`, it will find the corresponding `Module` and add the new schedule to its schedule list.

During the execution, the following validity checks will be conducted:

- Module existence check — The model will check if it can find the module indicated by the new schedule. If no module is found, then a `CommandException` will be thrown.
- Schedule conflict check — The model will check if the new schedule conflicts with any existing schedules that the user has. If conflict happens, then a `CommandException` will be thrown.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the command isn't executed successfully and a `CommandException` is thrown, then the new schedule won't be added to the ProfNUS.</div>

### 5.9. Editing a schedule feature

#### Implementation

The proposed edit schedule functionality is accomplished by `EditScheduleCommand` which extends the `Command` class. The `EditScheduleCommand` overrides the following method:

- `EditScheduleCommand#execute(Model model)` — Executes the command and edits the target schedule with new information
- `EditScheduleCommand#createEditedSchedule(Schedule scheduleToEdit, EditScheduleDescriptor descriptor)` — Creates an edited schedule. The edited schedule overwirtes fields given by the `descriptor` and remains other fields in the `scheduleToEdit`.

The following sequence diagram shows how add schedule operation works

![EditScheduleSequence](images/EditScheduleSequenceDiagram.png)

After the ProfNUS receives the instruction to edit a target `Schedule` (indicated by the index in the shown schedule list), it will modify it based on new information given by the user.

During the execution, the following validity checks will be conducted:

- Index validity check — The model will check if the index is valid. More specifically, the index should be within the range of 1 to n where n is the total number of schedules. If the index is invalid, then a `CommandException` will be thrown.
- Schedule conflict check — The model will check if the edited schedule conflicts with any existing schedules that the user has. If conflict happens, then a `CommandException` will be thrown.
- Optional parameters check — The parser will check if at least one of fields of the `Schedule` is edited. If no modification exists, then a `ParserException` will be thrown.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the edit schedule command isn't executed successfully and a `CommandException` or `ParserException` is thrown, then no schedule will be edited.</div>

#### Design consideration:

##### Aspect: How medit executes

|                                                              | Pros                                                | Cons                                                     |
| ------------------------------------------------------------ | --------------------------------------------------- |----------------------------------------------------------|
| **Option 1** <br/> Edit by module code, class type, and class group | Allows convenience if he knows the schedule details | Too much information to type.                            |
| **Option 2** <br/> Edit by making use of indexing in list    | Allows convenience if schedule detail is forgotten  | Have to use `sview` command to obtain the schedule list. |

Reason for choosing Option 2:

To locate a schedule uniquely with schedule, a user needs to know the module code, class type, and class group. For example, `CS2103T tut W11`. However, when there are too many groups, professors can easily forget which group he is looking for. Therefore, using the index is better in this case.

### 5.10. Viewing the schedule list feature


#### Implementation

The proposed ViewSchedule functionality is accomplished by `ViewScheduleCommand` which extends the `Command` class. The `ViewScheduleCommand` overrides the following method:

- `ViewScheduleCommand#execute(Model model)` — Executes the command and displays the selected schedules / all schedules

The following sequence diagram shows how view schedule operation works :

![ViewScheduleSequence](images/sview_Sequence.png)


### 5.11. Viewing the Timetable feature

The proposed ViewTimetable functionality is accomplished by `ViewTimetableCommand` which extends the `Command` class. The `ViewTimetableCommand` overrides the following method:

- `ViewTimetableCommand#execute(Model model)` — Executes the command and displays the timetables in vertical or horizontal verson.

The following sequence diagram shows how view timetable operation works with input command `tview v`.


![ViewTimetableSequence](images/final_tview_v.png)


### 5.12. Clearing Schedules feature

The proposed Clear schedules functionality is accomplished by `ClearScheduleCommand` which extends the `Command` class. The `ClearScheduleCommand` overrides the following method:

- `ClearScheduleCommand#execute(Model model)` — Executes the command and displays the remaining schedules.

The following sequence diagram shows how clear schedules operation works with input command `sclear`.

![ViewTimetableSequence](images/sclear_diagram.png)

--------------------------------------------------------------------------------------------------------------------


## **6. Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **7. Appendix A: Requirements**

### 7.1. Product scope

**Target user profile**:

* NUS SoC Professors
* has a need to view and manage a significant number of students
* has modules to manage
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: The application helps NUS SoC Professors manage their students and TA in the various modules, by providing a quick and easy way to find and contact them.


### 7.2. User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​       | I want to …​                                        | So that I can…​                             |
|----------|---------------|-----------------------------------------------------|---------------------------------------------|
| `* * *`  | SoC Professor | View the contact information of my students         | I can contact them                          |
| `* * *`  | SoC Professor | View the list of modules                            | Better plan my module's timeslots           |
| `* * *`  | SoC Professor | Edit the information of my students                 | Rectify inaccuracies in student information |
| `* * *`  | SoC Professor | Find a student                                      |                                             |
| `* * *`  | SoC Professor | Add new student to a module                         |                                             |
| `* * *`  | SoC Professor | Add a module                                        |                                             |
| `* * *`  | SoC Professor | Delete a module                                     |                                             |
| `* * *`  | SoC Professor | Add a schedule                                      |                                             |
| `* * *`  | SoC Professor | Edit a schedule                                     |                                             |
| `* * * ` | SoC Professor | Delete a schedule                                   |                                             |
| `* *`    | SoC Professor | Clear all the schedules of some modules at one time |                                             |
| `* *`    | SoC Professor | View all the teaching schedules                     | Plan my activities in advance               |
| `* *`    | SoC Professor | View all the teaching schedules of some modules     | Plan my activities in advance               |
| `* *`    | SoC Professor | View all the teaching schedules of some weekdays    | Plan my activities in advance               |
| `* *`    | SoC Professor | View the teaching Timetable directly                | Plan my activities in advance               |
| `*`      | Professor     | Choose the favourite theme of software              |                                             |

*{More to be added}*

### 7.3. Use cases

(For all use cases below, the **System** is the `ProfNUS` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a module**

**MSS**

1. User requests to list modules
2. ProfNUS shows a list of modules
3. User requests to add a module to the list
4. ProfNUS adds the module

   Use case ends.



**Use case: Delete a module**

**MSS**

1. User requests to list modules
2. ProfNUS shows a list of modules
3. User requests to delete a module in the list
4. ProfNUS deletes the module

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ProfNUS shows an error message.

      Use case resumes at step 2.

**Use case: View more details about a module**

**MSS**

1. User requests to list modules
2. ProfNUS shows a list of modules
3. User requests to view more details about a module in the list
4. ProfNUS displays the module name, module code, module description,
   and any tags given to the module, along with the schedules for that module.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ProfNUS shows an error message.

      Use case resumes at step 2.

**Use case: Add a student**

**MSS**

1. User requests to list modules
2. ProfNUS shows a list of modules
3. User request to list students in a particular module
4. ProfNUS shows a list of students
5. User requests to add a specific student to the list
6. ProfNUS adds the student

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.



**Use case: Delete a student**

**MSS**

1. User requests to list modules
2. ProfNUS shows a list of modules
3. User request to list students in a particular module
4. ProfNUS shows a list of students
5. User requests to delete a specific student in the list
6. ProfNUS deletes the student

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 4a. The list is empty.

  Use case ends.

* 5a. The given index is invalid.

    * 5a1. ProfNUS shows an error message.

      Use case resumes at step 2.

**Use case: Add a schedule**

**MSS**

1. User requests to add a schedule.

2. ProfNUS adds the new schedule to schedule list.

3. ProfNUS shows the schedule list.

   Use case ends.

**Extensions**

- 1a. The module of the schedule doesn't exist

  - 1a1. ProfNUS shows an error message.

    Use case ends.

- 1b. The schedule conflicts with existing schedules

  - 1b1. ProfNUS shows an error message.

    Use case ends.



**Use case: Edit a schedule**

**MSS**

1. User requests to edit a schedule.

2. ProfNUS edits the target schedule with new information.

3. ProfNUS shows the schedule list.

   Use case ends.

**Extensions**

- 1a. No field is edited.

  - 1a1. ProfNUS shows an error message.

    Use case ends.

- 1b. The given index is invalid

  - 1b1. ProfNUS shows an error message.

    Use case ends.



**Use case: Delete a schedule**

**MSS**

1. User requests to delete a schedule.

2. ProfNUS deletes the target schedule.

3. ProfNUS shows the schedule list.

   Use case ends.

**Extensions**

- 1a. The given index is invalid

  - 1a1. ProfNUS shows an error message.

  Use case ends.

*{More to be added}*

### 7.4. Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3.  User data are stored in local files.
4.  Should be used only by users authorized as SoC professors.
5.  The probability of critical failure should be lower than 5%.
6.  Should be able to process 500 or more students information without a noticeable lag.

*{More to be added}*

### 7.5. Glossary

1. **Mainstream OS**: Windows, Linux, Unix, OS-X
2. **Private contact detail**: A contact detail that is not meant to be shared with others
3. **SoC**: School of Computing, National University of Singapore
4. **Module**: Courses provided by professors from SoC
5. **Schedule**: Time slot of a module class, containing class name, time, venue class type and class group.
6. **CLI**: Command-Line Interface(CLI), which receives commands from a user in the form of lines of text


--------------------------------------------------------------------------------------------------------------------

## **8. Appendix B: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.
</div>

### 8.1. Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

### 8.2. Add/Delete a module

1. Adding a module to ProfNUS.

    1. Prerequisites: Arguments are valid and all compulsory parameters are provided. No duplicate module is allowed in ProfNUS.

    2. Test case: `madd n/Introduction to SWE c/CS2103T d/Teach students SWE principles and practices t/ModuleCoordinator`<br>
       Expected: Adds a new module with the module name `Introduction to SWE`, unique module code `CS2103T`, module description `Teach students SWE principles and practices` and tag `ModuleCoordinator`.

    3. Test case: `madd n/Programming Methodology I`<br>
       Expected: No module is added. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `madd`, `madd x` and `madd n/Programming Methodology I t/ModuleCooridnator`<br>
       Expected: Similar to previous.

2. Deleting a module

    1. Prerequisites: Arguments are valid and all compulsory parameters are provided. Module code provided exists.

    2. Test case: `mdel c/CS2103T`<br>
       Expected: Module with module code CS2103T is deleted from the list. Details of the deleted module shown in the status message. Timestamp in the status bar is updated.

    3. Test case: `mdel 0`<br>
       Expected: No module is deleted. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `mdel`, `mdel c/x`, `...` (where x module code does not exist)<br>
       Expected: Similar to previous.

### 8.3. Add/Delete a student

1. Adding a student to ProfNUS.

    1. Prerequisites: Arguments are valid and all compulsory parameters are provided. No duplicate student is allowed in ProfNUS.

    2. Test case: `add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 i/A0123456G h/@good_student c/CS2030S`<br>
       Expected: Adds a new student with the name `John Doe`, phone number `98765432`, email address `johnd@example.com`, address `311, Clementi Ave 2, #02-25`, student id `A0123456G`, telegram handle `@good_student`and module code `CS2030S`.

   3. Test case: `add n/John Doe`<br>
      Expected: No student is added. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `add`, `add x` and `add n/John Doe p/98765432`<br>
      Expected: Similar to previous.
2. Deleting a student while all students are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `delstu 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   3. Test case: `delstu 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `del`, `del x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### 8.4 Add a schedule

Adding a schedule to ProfNUS.

1. Prerequisites: Arguments are valid and all compulsory parameters are provided.

2. Test case: `add c/CS2103T w/Friday ct/16:00-18:00 cc/lec cg/L1 cv/COM2 0217`<br>
   Expected: Adds a new schedule with the module `CS2103T`, weekday `Friday`, class time `16:00-18:00`, class type `lec`, class group `L1`, class venue `COM2 0217`.

3. Test case: `add c/CS2000 w/Thursday ct/16:00-18:00 cc/lec cg/L1 cv/COM2 0217`<br>
   Expected: No schedule is added. Error details shown in the status message. Status bar remains the same.

4. Test case: `add c/CS2030S w/Friday ct/15:00-17:00 cc/lab cg/L1 cv/COM1 0210`

   Expected: No schedule is added. Error details shown in the status message. Status bar remains the same.

## **9. Appendix C: Effort**

Creating **ProfNUS** was a challenging but yet fulfilling journey for all of us. This project required much effort from
all team members equally. This can be substantiated by our **15,000** lines of code combined which was written in a span of
less than 10 weeks, placing us within the top 10 in the cohort for total code contribution. Despite our busy schedules,
we managed to maintain a healthy and consistent communication with at least one online Zoom meeting per week. The hard work
and dedication from our team was what made this project a successful and memorable learning experience.
