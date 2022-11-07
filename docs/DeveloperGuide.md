---
layout: page
title: Developer Guide
---
## Table of Contents
- [ConnectNUS Developer Guide](#connectnus-developer-guide)
- [Navigating the Developer Guide](#navigating-the-developer-guide)
- [Glossary](#glossary)
- [Acknowledgements](#acknowledgements)
- [Setting up](#setting-up-getting-started)
- [Design](#design)
  - [Architecture](#architecture)
  - [UI Component](#ui-component)
  - [Logic Component](#logic-component)
  - [Model Component](#model-component)
  - [Storage Component](#storage-component)
  - [Common Classes](#common-classes)
- [Implementation](#implementation)
  - [Edit Modules](#edit-modules)
  - [Check Modules Left](#checking-modules-left)
  - [Refreshing to Next Semester](#refreshing-to-next-semester)
  - [Add Lessons](#adding-lessons)
  - [View Timetable](#viewing-timetable)
  - [Undo / Redo](#undo--redo)
  - [Filter Contacts](#filter-feature)
- [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
- [Appendix: Requirements](#appendix-requirements)
  - [Product Scope](#product-scope)
  - [User Stories](#user-stories)
  - [Use Cases](#use-cases)
  - [Non-functional Requirements](#non-functional-requirements)
- [Appendix: Instructions for Manual Testing](#appendix-instructions-for-manual-testing)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **ConnectNUS Developer Guide**

ConnectNUS is **designed for NUS Computer Science students** by providing a centralised platform for them to keep track
of their and their friends' modules and timetables. ConnectNUS is **optimised for use via a Command Line Interface** (CLI)
while still having the benefits of a **Graphical User Interface** (GUI).

For Computer Science students who are generally fast typers, ConnectNUS is an excellent platform for you to get your CS
related tasks management done quickly, so you can spend less time searching through chats, and spend more time making
meaningful connections and engaging in productive work.

The goal of this Developer Guide is to document and illustrate the underlying architecture of ConnectNUS, and provide
insights on how our product is designed, implemented and tested as well as the design considerations that were involved
in the deciding the implementation of various features offered by ConnectNUS.

You are recommended to read the [Navigating the Developer Guide](#navigating-the-developer-guide) and [Glossary](#glossary)
sections, which will provide the necessary information to allow you to familiarise yourself with the structure of this
Developer Guide and help you navigate it with ease.

--------------------------------------------------------------------------------------------------------------------

## **Navigating the Developer Guide**

The Developer Guide is divided into the following sections :
- [Design](#design)
- [Implementation](#implementation)
- [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
- [Appendix: Requirements](#appendix-requirements)
- [Appendix: Instructions for Manual Testing](#appendix-instructions-for-manual-testing)

The [Design](#design) section gives an overview of the architecture of ConnectNUS.
In this section, you will be provided with:

1. The API (Application Programming Interface) of the major components of ConnectNUS.
2. A Class Diagram to illustrate the internals of each component.
3. An explanation of how each component works.
4. A Sequence Diagram to give an example of the flow of events within the component where applicable.

The [Implementation](#implementation) section outlines how the features offered by ConnectNUS are
implemented. In this section, you will be provided with:

1. A description of the feature.
2. Some example commands to execute to use the feature.
3. A Class Diagram or screenshot to illustrate and explain the implementation of each feature where applicable.
4. A Sequence Diagram to give an example of the implementation flow of the feature where applicable.
5. An Activity Diagram to show all possible behaviours of the feature where applicable.
6. An Object Diagram to model relationships between objects of the same components where applicable.
7. Design considerations and alternatives taken into consideration that justifies our implementation of the feature.

The [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops) section
provides the links to the aforementioned documents. In this section, you will be provided with the links to:

1. Documentation guide.
2. Testing guide.
3. Logging guide.
4. Configuration guide.
5. DevOps guide.

The [Appendix: Requirements](#appendix-requirements) section provides details on the motivation behind creating ConnectNUS
and how we intend for users to use ConnectNUS.
In this section, you will be provided with
1. Product Scope.
2. User Stories.
3. Use Cases.
4. Non-functional requirements.

The [Appendix: Instructions for Manual Testing](#appendix-instructions-for-manual-testing) section gives detailed instructions
on how you can perform testing on the features in ConnectNUS. In this section, you will be provided with:
1. An overview of the command to execute to test the feature.
2. Sample test cases to be executed.
3. The expected behaviour of the test command.

You can jump into the various sections from the Table of Contents to view different sections of the Developer Guide, and
click on [Scroll back to top](#table-of-contents), which can be found at the end of every section, to access the Table
of Contents to quickly jump to another section.

We would recommend you to read the [Glossary](#glossary) section next.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

## **Glossary**

The glossary illustrated in the table below will help you understand the terminologies used in this
Developer Guide.

<table>
  <tr>
    <th><strong>Terminology</strong></th>
    <th><strong>Meaning / Purpose</strong></th>
  </tr>
  <tr>
    <td><strong>Command Line Interface (CLI)</strong></td>
    <td>
      A text-based interface that is used to operate software (such as ConnectNUS) and operating systems. CLI
      allows a user to perform tasks by entering commands. <br>
      Users enter the specific command, press “Enter”, and then wait for a response.
      After receiving the command, the CLI processes it accordingly and shows the output/result on the screen.
    </td>
  </tr>
  <tr>
    <td><strong>Graphical User Interface (GUI)</strong></td>
    <td>
      A system of interactive visual components for computer software. A GUI displays objects that convey information,
      and represent actions that can be taken by the user. The objects change color, size, or visibility when the user
      interacts with them.
    </td>
  </tr>
  <tr>
    <td><strong>Application Programming Interface (API)</strong></td>
    <td>
      A a set of definitions and protocols for building and integrating application software and simplifies how
      developers integrate new application components into an existing architecture.
    </td>
  </tr>
  <tr>
    <td><strong>Mainstream OS</strong></td>
    <td>
      Windows, Linux, Unix, OS-X.
    </td>
  </tr>
  <tr>
    <td><strong>Private Contact Detail</strong></td>
    <td>
      A contact detail that is not meant to be shared with others.
    </td>
  </tr>
  <tr>
    <td><strong>Architecture</strong></td>
    <td>
      Shows the overall organization of the system and can be viewed as a very high-level design.
      Consists of a set of interacting components that fit together to achieve the required functionality . It is a
      simple and technically viable structure that is well-understood and agreed-upon by everyone in the development
      team, and it forms the basis for the implementation..
    </td>
  </tr>
  <tr>
    <td><strong>Class Diagram</strong></td>
    <td>
      Describe the structure but not the behavior of an Object-Oriented Programming (OOP) solution.
    </td>
  </tr>
  <tr>
    <td><strong>Object-Oriented Programming (OOP)</strong></td>
    <td>
      A computer programming model that organizes software design around data, or objects, rather than functions and logic.
      An object can be defined as a data field that has unique attributes and behavior.
    </td>
  </tr>
  <tr>
    <td><strong>Sequence Diagram</strong></td>
    <td>
      Captures the interactions between multiple objects for a given scenario.
    </td>
  </tr>
  <tr>
    <td><strong>Activity Diagram</strong></td>
    <td>
      Models workflows, which define the flow in which a process or a set of tasks is executed.
    </td>
  </tr>
  <tr>
    <td><strong>Object Diagram</strong></td>
    <td>
      Used to complement class diagrams. Object diagrams can be used to model different object
      structures that can result from a design represented by a given class diagram.
    </td>
  </tr>
  <tr>
    <td><strong>User Stories</strong></td>
    <td>
       Short, simple descriptions of a feature told from the perspective of the person who desires the new capability,
       usually a user or customer of the system.
    </td>
  </tr>
  <tr>
    <td><strong>Use Case</strong></td>
    <td>
      Describes an interaction between the user and the system for a specific functionality of the system.
    </td>
  </tr>
  <tr>
    <td><strong>Teaching Assistant</strong></td>
    <td>
      Employed staff by NUS who teach tutorials for specific modules for undergraduates. They will be guided by the module coordinators or instructors.
    </td>
  </tr>
</table>

--------------------------------------------------------------------------------------------------------------------
## **Acknowledgements**

* The code base is adapted from [AddressBook3](https://github.com/nus-cs2103-AY2223S1/tp) (AB3).
* We would like to give credit to all authors who have contributed to AB3.

[Scroll back to top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

[Scroll back to top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

<div style="page-break-after: always;"></div>

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-T14-4/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-T14-4/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

<div style="page-break-after: always;"></div>

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-T14-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter`, `UserProfile` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-T14-4/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-T14-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-T14-4/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.
<div style="page-break-after: always;"></div>
The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>
<div style="page-break-after: always;"></div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

![Parser Classes](images/ParserClasses.png)

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-T14-4/tp/tree/master/src/main/java/seedu/address/model/Model.java)

![Model Class Diagram](images/ModelClassDiagram.png)


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object) as well as the `User` .
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div style="page-break-after: always;"></div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects. Similarly, there is a `CurrentModule`, `PlannedModule` and `PreviousModule` lists and each unique instance has its own object. <br>

![Better Model Class Diagram](images/BetterModelClassDiagram.png)

</div>

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-T14-4/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

![Storage Class Diagram](images/StorageClassDiagram.png)

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

[Scroll back to top](#table-of-contents)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

[Scroll back to top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### **Person Class**
Each `Person` in the AddressBook is implemented in the following way:

![Person Class Diagram](images/PersonClassDiagram.png)

All `Person` have a `Name`, `Email`, `Address` and `Phone` and a set of `Tag`, `CurrentModule`, `PreviousModule`, `PlannedModule` and `Lesson`.
`Person`s can have a `Github` URL to their profile added, and as many `Tags`, `Modules` and `Lessons` as desired.

<div style="page-break-after: always;"></div>

### **User Class**

`User` Class is implemented in a similar way.

![User Class Diagram](images/UserClassDiagram.png)

`User` has a `Name`, `Email`, `Address` and `Phone` and a set of `CurrentModule`, `PreviousModule`, `PlannedModule`, and `Lesson`.
`User` can have a `Github` URL to their profile added, and as many `Modules` and `Lessons` as desired.

<div style="page-break-after: always;"></div>

#### Design Considerations

The `User` class is implemented as an abstract class with 2 inheritors, `EmptyUser` and `ExistingUser`. This is so that `User`
can never be null thus when the user profile is deleted or has yet to be added, `EmptyUser` will act as a placeholder to indicate
to the application that there is no user profile currently stored. Unlike `Person`, `User` does not contain `Tags` as the `Tags`
feature should be used by the user to indicate each `Person`'s relation to the user instead.

Commands that affect both `Person` and `User` (e.g. `edit`, `delete`, `module`, `lesson` and `remove`) have been implemented very similarly as `User` has been implemented in a
similar way to a `Person` thus many commands that affect each individual contact are similarly designed to commands that
affect the user. Additionally, both `Person` and `User` use the same `Module` classes to store modules hence the similarity
in design.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Module Class**
`CurrentModule`, `PlannedModule`, and `PreviousModule` implement the `Module` interface.

![Module Class Diagram](images/ModuleClassDiagram.png)

All implementations of `Module`s have a name.

#### Design Considerations

We chose to split `Module` into `CurrentModule`, `PreviousModule` and `PlannedModule` so that users would be able to track what modules
themselves and their friends have taken/are taking so that they can collaborate with them in projects and schoolwork.
Multiple instances of the same module can be added and stored as iterating through each module list everytime a new module is added
to check for similarity makes the program slower. Additionally, the same module may appear in different lists due to the possibility
of repeating the module. The application does not check the modules added against a list of actual registered modules in NUS
because NUS has over 6000 registered modules and iterating through this list to check if the module is valid would
make the program slow.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Edit Modules**

Editing modules is implemented such that you can directly add modules into each list of current, previous or planned modules.
Users can also use this command to remove all instances of the same module from each list.

The command has the prefix `module` and has the parameters `user / INDEX (must be a positive integer) [curr/CURRENT_MODULE]
[prev/PREVIOUS_MODULE] [plan/PLANNED_MODULE] [rm/MODULE_TO_REMOVE]`

#### Implementation Flow

Given below is a sequence diagram to illustrate how the module lists are updated after the user attempts to edit module list.

![Module Command Sequence Diagram](images/ModuleCommandSequenceDiagram.png)

<div style="page-break-after: always;"></div>

Given below is an activity diagram to illustrate the behaviour of editing Modules within `Logic`.

![Module Activity Diagram](images/ModuleActivityDiagram.png)

#### Design Considerations

Removal of modules has been implemented such that modules are removed from all 3 lists at once as it is simpler to have to use only one
prefix for both the user and the program, and the user does not need to worry about mistyping multiple prefixes when keying in the command.
Removal of modules does not check the module lists to see if the module is present as iterating through all module lists it makes the program slow.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Checking Modules Left**
This feature compares the user's current and previous modules list with a fixed list of CS Core Requirements or focus area
modules. The program then finds the difference between the user's lists and the fixed list and outputs it onto the `ResultDisplay`.

#### Implementation Flow

Below is an activity diagram to illustrate the behaviour of checking Modules left within `Logic`.

![Modules Left Activity Diagram](images/ModulesLeftActivityDiagram.png)

<div style="page-break-after: always;"></div>

#### Design Considerations

Currently, we have tailored this feature towards AY21/22 enrolled Computer Science students as we have yet to implement
features that accept and save a contact's or the user's majors and year of enrolment to check their modules left.
Hence, we have decided that for this version we would limit the feature to only be of use to individuals from our own
batch and major for now.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Refreshing to Next Semester**
ConnectNUS has a feature that helps you to update the previous module lists and clear the current module list of your profile
as well as any persons stored in the contact list at the start of each semester so that it is easy for you to update
yours and your friend's profiles.

#### Implementation Flow

Given below is a sequence diagram to illustrate how the Next Semester feature, `nexsem`, behaves after the user gives the
respective command:

![Next Semester Sequence Diagram](images/NextSemesterSequenceDiagram.png)

`nextsem` moves all `CurrentModule` in the `CurrentModule` set in your profile as well as any persons stored in the contact list
to their respective `PreviousModule` sets. After which, the `CurrentModule` sets in both yours and all persons stored in the
contact list will be cleared.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Adding Lessons**
ConnectNUS has a feature that allows you to add lessons to your profile as well as any persons stored in the contact list,
so that it is easy to keep track of yours and your friend's schedules.

The command has the prefix `lesson` and has the parameters
`user / INDEX (must be a positive integer) [l/TYPE] [m/MODULE] [d/DAY] [start/START TIME] [end/END TIME]`

Given below are some examples of a user command to add a `Lesson`
1. Example 1 : Command to add a `Tutorial` for the module CS2103T that starts at 12pm and ends at 1pm every Thursday to the `User`
- `lesson user l/tut m/CS2103T d/4 start/12:00 end/13:00`

2. Example 2 : Command to add a `Lab` for the module CS2100 that starts at 4pm and ends at 5pm every Wednesday to the first contact.
- `lesson 1 l/lab m/cs2100 d/3 start/16:00 end/17:00`

3. Example 3 : Command to add a `Lecture` for the module CS2109S that starts at 10am and ends at 12pm every Friday to the fifth contact
- `lesson 5 l/lec m/cs2109s d/5 start/10:00 end/12:00`

#### Lesson class
In NUS School of Computing, every module generally has lab, lecture, recitation and tutorial slots.

We implemented an abstract `Lesson` class and the classes `Lab`, `Lecture`, `Recitation` and `Tutorial` that inherits from it.
Each of the class types have the class fields, `type`, `module`, `day`, `startTime`, and `endTime`.

The following class diagram illustrates the class diagram of the `Lesson` class and subclasses.

![LessonClassDiagram](images/LessonClassDiagram-0.png)

Each `Lesson` has a `moduleName:String`, `day:int` (between 1-7 inclusive, where 1 is Monday and 7 is Sunday),
`type:String` (where type is tut / rec / lab / lec), `start:LocalTime` and `end:LocalTime` to in HH:mm format.

<div style="page-break-after: always;"></div>

#### Implementation Flow

Given below is a sequence diagram to illustrate how the timetable mechanism behaves after the user attempts to add a tutorial.

![Timetable](images/Timetable-0.png)

Given below is an activity diagram to illustrate the behaviour of adding a Lesson within `Logic`.

![LessonActivityDiagram](images/LessonActivityDiagram-0.png)

<div style="page-break-after: always;"></div>

#### Design Considerations
**Aspect 1: How to implement the `Lesson` Class:**

* **Alternative 1 (current choice):** No dependency on `CurrentModule` i.e. moduleName is a `String`.
  * Pros:
    * Faster as there is no need to check through set of `CurrentModule`s before adding lesson.
    * More flexible as user is able to add lessons or activities that are not in the list of `CurrentModule` e.g. if contact is a teaching assistant.
  * Cons: Lack of input validation - user may input invalid lessons that he/she is not taking.


* **Alternative 2:** Dependency on `CurrentModule` i.e. moduleName is a `CurrentModule`.
  * Pros: Input validation - user can only input lessons for modules being taken.
  * Cons:
    * Slower due to need to check if `Lesson`'s module is in the list of `CurrentModule`s every time a `Lesson` is added.
    * Unable to accommodate alternative activities being added in the timetable.


* **Decision:** We chose Alternative 1 as speed is important. There are a lot of lessons are expected to be added, since there is about 15 lessons a week per person,
that has to be multiplied by the number of contacts in ConnectNUS.

**Aspect 2: How to implement the day in `Lesson` Class:**

* **Alternative 1 (current choice):** Day as `int` between 1 and 7.
  * Pros: Quick sorting of lessons by day as operations on primitives like `int` is very fast.
  * Cons: Unintuitive for a user to consider day as an integer between 1 and 7.


* **Alternative 2:** Day as `String`.
  * Pros: Easy conversion to String to display.
  * Cons: Difficult to sort lessons by day.

* **Alternative 3:** Day as `DayOfWeek`.
  * Pros:
    * Quick to sort due to numeric value of each `DayOfWeek`.
    * Has function to convert to `String` value of day.
    * Safety: validates input to be only within the week.
  * Cons:
    * Slower sorting as `DayOfWeek` has to be converted to `int` to sort.
    * Does not address issue of unintuitive input for day in command.

* **Decision:** We chose Alternative 1 as speed is important as mentioned in Aspect 1.

<div style="page-break-after: always;"></div>

**Aspect 3: Data Structure to store all `Lesson`s:**

* **Alternative 1 (current choice):** `Hashset`.
  * Pros: Very fast adding of lessons, with removal of duplicates.
  * Cons: Slow sorting due to need to convert to list before sorting.


* **Alternative 2:** `List`.
  * Pros: Easy sorting due to included function.
  * Cons: No removal of duplicates


* **Decision:** We chose Alternative 1 to reduce the chances of bugs occurring due to duplicates that may fall through our checks. As sorting is only done
during `timetable` commands which will be called significantly fewer times than `lesson` commands, speed of sorting is not
as significant of an issue.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Viewing Timetable**
ConnectNUS gives you the ability to view not only your own timetable, but that of your contacts as well, after `Lesson`s
have been added to your profile and their contact. This makes de-conflicting schedules significantly faster and meetings can
be arranged easily.

The command has the prefix `timetable` and has the parameters
`user / INDEX (must be a positive integer)`

When the user executes the timetable command, a window will pop up which will display the timetable of the user or the specified index in the user's contacts, similar to the window shown below.

<img src="images/TimetableWindow.png" width="200">

The timetable will display the lists of all lessons added to the user or user's contacts of the specified index in
chronological order.

Given below are some examples of a user command to show a `Timetable`.
1. Example 1 : Command to show the `User`'s timetable
- `timetable user`

2. Example 2 : Command to show the timetable of the user's first contact.
- `timetable 1`

3. Example 3 : Command to show the timetable of the user's tenth contact,
- `timetable 10`

<div style="page-break-after: always;"></div>

#### Implementation flow

Given below is a sequence diagram to illustrate how the timetable is displayed after the user attempts to show his/her
timetable.

![TimetableSequenceDiagram](images/TimetableSequenceDiagram.png)

`ModelManager`, which implements the `Model` class, stores an attribute `timetable`, which is a `HashSet` of `Lesson`s
that is being shown in the Timetable Window.

Before `UI` shows the `TimetableWindow` to the user, the `timetable:HashSet` is obtained from `Logic` and `Model` and then
sorted and converted to `String` which is displayed in the `TimetableWindow` which is now made visible.

<div style="page-break-after: always;"></div>

#### Design Considerations

**Aspect: How to implement the timetable feature:**

* **Alternative 1:** Displaying in the main window at all times.
  * Pros: User does not have to run a command to see his/her and his/her contact's timetable.
  * Cons: User may have to scroll if there is insufficient space to see the full timetable.

* **Alternative 2 (current choice):** User runs a command to display his/her and his/her contact's timetable which creates a new window.
  * Pros: User will have a larger space to see the timetable.
  * Cons: We must ensure the implementation of the additional commands are correct and the UI displays correctly.

* **Decision:** We chose Alternative 2 to reduce the clutter on the main window since we expect the timetable feature to not be used often but only when arranging meetings or when user needs to view the timetable.

<div markdown="span" class="alert alert-info">:information_source: **Note:** When a new `Lesson` is added, User has to run the command to show the timetable again to view the updated timetable with the newly added `Lesson`.

</div>

#### Possible Extensions

1. Allow User to sort his/her classes from all modules in chronological order or by modules depending on the User's
   preference to view his/her timetable.

2. Improving the GUI of the `Timetable` pop-up window for more visually pleasing user experience.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Undo / Redo**

#### Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

<div style="page-break-after: always;"></div>

Step 2. The user executes `user n/Silas p/98765432 e/silastay@gmail.com a/Kent Ridge Drive g/SilasTSL curr/CS2100 prev/CS1101S plan/CS2109` command to add a new User to the address book. The "Add User" command calls `Model#commitAddressBook()`, causing the modified state of the address book after the "Add User" command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

<div style="page-break-after: always;"></div>

Step 3. The user executes `delete user` to delete the newly created user. The `delete user` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`, and again the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>
<div style="page-break-after: always;"></div>

Step 4. The user now decides that deleting the user was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>
<div style="page-break-after: always;"></div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>
<div style="page-break-after: always;"></div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user now decides that the undo was a mistake, and decides to redo that action by executing the `redo` command. The `redo` command will call `Model#redoAddressBook()`, which will shift the `currentStatePointer` once to the right, pointing it to the forward address book state, and restores the address book to that state.

![UndoRedoState5](images/UndoRedoState5.png)

<div style="page-break-after: always;"></div>

#### State Management

Implementing Undo/Redo commands in this manner requires the developers to strictly follow certain State Management rules. Some important aspects include:
1. Every command (that can be undone/redone) should produce and save a new state of the AddressBook
2. Every AddressBook should not be related to another
   - Every instance in each AddressBook should be completely new with new data
   - For example:
      1. We first create a user with name field "Bob".
     ![StateManagement0](images/StateManagement0.png)
      2. Editing the User's name field to "Tim" should create a new User Object with the desired new name, instead of changing the existing User object's name.
     ![StateManagement1](images/StateManagement1.png)
   - This is because each state of the AddressBook must be unique from one another

Here's what a typical Object Diagram may look like for an Address Book State:
![UndoRedoObjectDiagram](images/UndoRedoObjectDiagram.png)
- In this diagram, `ab0` is the initial AddressBook state that contains two contact, `bob0` and `alice0`.
- After the user makes a change to the contact `bob0`, we have to save a new `AddressBook` into our `VersionedAddressBook`, with a new `bob1` Object that embodies the changed `bob0` Object, instead of just making the change to the `bob0` Object directly.

<div style="page-break-after: always;"></div>

#### New Classes/Methods

Given below are the new Classes implemented:

* `VersionedAddressBook`
  * Extends `AddressBook` with an Undo/Redo history
  * Uses `addressBookStateList` (List of `AddressBook`s) and `currentStatePointer` (int Index of current `AddressBook` state)
  * `VersionedAddressBook#commit()`
    * Adds current `AddressBook` into `addressBookStateList`
    * Called after each change in `AddressBook` state
  * `VersionedAddressBook#undo()`
    * Calls `Model#canUndoAddressBook()` to check if there is a previous state
    * Shifts `currentStatePointer` back by one
    * Called after user inputs `undo` command
  * `VersionedAddressBook#redo()`
    * Calls `Model#canRedoAddressBook()` to check if there is a next state
    * Shifts `currentStatePointer` forward by one
    * Called after user inputs `redo` command

Given below are the new Methods implemented:
* `Model`
  * `Model#canUndoAddressBook()` - Checks if there is a previous `AddressBook` state
  * `Model#canRedoAddressBook()` - Checks if there is a forward `AddressBook` state
  * `Model#undoAddressBook()` - Changes the current Model to read from the previous `AddressBook` state
  * `Model#redoAddressBook()` - Changes the current Model to read from the next `AddressBook` state
  * `Model#commitAddressBook()` - Saves current `AddressBook` state into `addressBookStateList`

<div style="page-break-after: always;"></div>

#### Design Considerations

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Filter Feature**
ConnectNUS has a filter function that allows you to filter all persons in your contact list according to `Tag`, `CurrentModule`,
`PreviousModule` or `PlannedModule`. This allows you to find people who are taking specific mods such that you
are able to reach out to friends for collaboration more quickly.

#### Implementation Flow

The following sequence diagram summarizes what happens when a user executes a `filterTagCommand`:

![FilterSequenceDiagram](images/FilterSequenceDiagram.png)

The `filterCurrModCommand`, `filterPrevModCommand` and `filterPlanModCommand` are executed in the same manner.

`ModelManager`, which implements the `Model` class, stores an attribute `filteredPersons`, which is a `FilteredList` of `Person`s
that is shown in the `MainWindow` class as a `PersonListPanel`. When a `filterTagCommand`, `filterCurrModCommand`,
`filterPrevModCommand` or `filterPlanModCommand` is given by the user, `Model`
updates it's `filteredPersons` attribute to only contain `Person`s with any of the `Tag`s specified by the user.
The `PersonListPanel` in the `MainWindow` UI is then updated accordingly.

<div style="page-break-after: always;"></div>

#### Design Considerations

**Aspect: The implementation of the various filter features i.e. filtering by Tag, filtering by Current Module, filtering by Previous Module and filtering by Planned Module:**

* **Alternative 1:** Creating a common filter parent class with each specific filter function extending from the parent filter class.
  * Pros: Since all filter functions require the same methods, it would be easier to implement filter functions with the different specifications in ConnectNUS. Furthermore, it allows developers to create new filter functions for future features that may be added to the application in future iterations.
  * Cons: the different filtering features would have to access different classes located in different packages to filter the contact list

* **Alternative 2 (current choice):** Abstracting out each filter feature as a different class which directly extends the command class.
  * Pros: Less confusing for developers as each filtering feature would have to access different classes depending on the feature that is being filtered. Furthermore, each filter feature's classes would belong in different packages within the programme due to the fact that it filters different features.
  * Cons: Future implementations of filter functions for new features that may be added to the programme would have to be created from scratch.

* **Decision:** We decided to use Alternative 2 as we do not foresee new features being added to the programme which would require filtering functions in the new feature based on the current purpose of our app. Furthermore, it would not be difficult to implement a parent abstract filter class for the filter function in future iterations should we need to do so.

[Scroll back to top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

[Scroll back to top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Appendix: Requirements**

### Product Scope

**Target User Profile**:

NUS CS Students who wish to keep track of their friends to work with

**Value Proposition**:

We help NUS CS Students to have a collection of fellow NUS CS Students to find people to collaborate with for CS work and projects

[Scroll back to top](#table-of-contents)

### User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                          | I want to …​                                     | So that I can…​                                                                         |
|----------|----------------------------------|--------------------------------------------------|-----------------------------------------------------------------------------------------|
| `* * *`  | new user                         | see usage instructions                           | refer to instructions when I forget how to use the App                                  |
| `* * *`  | user                             | save my own profile with my details and modules  | keep track of my own information                                                        |
| `* * *`  | user                             | edit my own profile's details or modules         | update my own information when there are changes                                        |
| `* * *`  | user                             | delete my own profile                            | remove my information in case of any data breach                                        |
| `* * *`  | user                             | save a new contact with details and modules      | contact them if i wish to collaborate with them                                         |
| `* * *`  | user                             | edit my friends' details or modules              | change and update the contacts in my friend's list to make sure it is always up to date |
| `* * *`  | user                             | delete a person                                  | remove entries that I no longer need                                                    |
| `* * *`  | user                             | tag my contacts                                  | keep track of groups we have in common                                                  |
| `* * *`  | user                             | list out all my friend's information             | look through all my contacts at once                                                    |
| `* * *`  | user with many friends           | find a specific friend's information             | save time from searching that friend's information                                      |
| `* * *`  | user currently studying          | keep track of my modules                         | keep track and plan my academic progress                                                |                                           |
| `* * *`  | user with group projects         | keep track of my friend's current modules        | so that i know which modules i can collaborate with them for                            |
| `* * *`  | user with academic queries       | keep track of my friend's previous modules       | consult my friends on those modules, if i am currently taking them                      |
| `* * *`  | user                             | see what modules my friends are planning to take | plan my modules together with them                                                      |
| `* *`    | user                             | add lessons to my timetable                      | keep track of my schedule easily                                                        |
| `* *`    | user                             | delete lessons from my timetable                 | keep my schedule accurate                                                               |
| `* *`    | user                             | view my timetable                                | be clear on what classes are coming up                                                  |
| `* *`    | user                             | add lessons to my contacts' timetable            | keep track of my friend's schedule easily                                               |
| `* *`    | user                             | delete lessons from my contacts' timetable       | keep my friend's schedule accurate                                                      |
| `* *`    | user with project meetings       | view my contacts' timetable                      | plan for project meetings easily by knowing their free time                             |
| `* *`    | user with many modules           | check what core modules I have left to clear     | plan my following semesters better to accommodate these modules                         |
| `* *`    | user                             | filter by contacts                               | view only those relevant to what I am looking for                                       |
| `*`      | user                             | view my own exam schedule                        | be clear on which exams are coming up                                                   |
| `*`      | user with projects involving Git | copy my contact's GitHub url                     | add them into organisation repos during projects                                        |

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Use Cases

**System: ConnectNUS**

**Use case: UC1 - See usage instructions**

**Actor: CS Students**

**MSS**

1. CS Student chooses to see usage instructions.
2. ConnectNUS displays the usage instructions.
3. Use case ends.
4. Use case ends.

**System: ConnectNUS**

**Use case: UC2 - Save my user profile**

**Actor: CS Students**

**MSS**

1. CS Student chooses to save their own profile.
2. ConnectNUS requests for details of their profile to be saved.
3. CS Student enters the requested details.
4. ConnectNUS saves the user profile and updates the data file.
5. Use case ends.

**Extensions**

* 1a. ConnectNUS detects another user profile that has already been saved

  * 1a1. ConnectNUS informs the user that there already is a user profile saved.
  * Use case ends.

* 3a. ConnectNUS detects an error in the entered data

    * 3a1. ConnectNUS requests for the correct data.
    * 3a2. CS Student enters new data.
    * Steps 3a1-3a2 are repeated until the data entered are correct.
      Use case resumes at step 4.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC3 - Edit user profile**

**Actor: CS Students**

**MSS**

1. CS Student chooses to edit their own profile.
2. ConnectNUS requests for details of their profile to be edited.
3. CS Student enters the requested details.
4. ConnectNUS saves the edited user profile and updates the data file.
5. Use case ends.

**Extensions**

* 1a. ConnectNUS does not detect any user profile to edit

    * 1a1. ConnectNUS informs user that there is no user profile to edit.
    * Use case ends.

* 3a. ConnectNUS detects an error in the entered data

    * 3a1. ConnectNUS requests for the correct data.
    * 3a2. CS Student enters new data.
    * Steps 3a1-3a2 are repeated until the data entered are correct.
      Use case resumes at step 4.

**System: ConnectNUS**

**Use case: UC4 - Delete user profile**

**Actor: CS Students**

**MSS**

1. CS Student chooses to delete their own profile.
2. ConnectNUS deletes the user profile and updates the data file.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS does not detect any user profile to delete

    * 1a1. ConnectNUS informs user that there is no user profile to delete.
    * Use case ends.

* 1b. ConnectNUS detects an error in the command format.
    * 1b1. ConnectNUS requests for the correct format.
    * User enters a new command in the correct format.
      Steps 1a1-1a2 are repeated until the data entered are correct.
      Use case resumes at step 2.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC5 - Save a new contact**

**Actor: CS Students**

**MSS**

1. CS Student chooses to save a new contact.
2. ConnectNUS requests for details of the contact to be saved.
3. CS Student enters the requested details.
4. ConnectNUS saves the contact and updates the data file.
5. Use case ends.

**Extensions**

* 3a. ConnectNUS detects an error in the entered data

    * 3a1. ConnectNUS requests for the correct data.
    * 3a2. CS Student enters new data.
    * Steps 3a2-3a2 are repeated until the data entered are correct.
    Use case resumes at step 4.

**System: ConnectNUS**

**Use case: UC6 - Edit a contact**

**Actor: CS Students**

**MSS**

1. CS Student chooses to edit a specific index of contact.
2. ConnectNUS requests for details of the contact to be edited.
3. CS Student enters the requested details.
4. ConnectNUS saves the edited contact and updates the data file.
5. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format or invalid index.
    * 1a1. ConnectNUS requests for the correct format or index.
    * User enters a new command in the correct format or index.
      Steps 1a1-1a2 are repeated until the data entered are correct.
      Use case resumes at step 2.

* 3a. ConnectNUS detects an error in the entered data

    * 3a1. ConnectNUS requests for the correct data.
    * 3a2. CS Student enters new data.
    * Steps 3a1-3a2 are repeated until the data entered are correct.
      Use case resumes at step 4.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC7 - Delete a contact**

**Actor: CS Students**

**MSS**

1. CS Student requests to delete a specific index of contact.
2. ConnectNUS deletes the contact and updates data file.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format or invalid index.
    * 1a1. ConnectNUS requests for the correct format or index.
    * User enters a new command in the correct format or index.
      Steps 1a1-1a2 are repeated until the data entered are correct.
      Use case resumes at step 2.

**System: ConnectNUS**

**Use case: UC8 - List out all my friend’s contact/information**

**Actor: CS Students**

**MSS**

1. CS Student requests to list all contact information.
2. ConnectNUS shows a list of contacts based on the order that the contacts are added.
3. Use case ends.

**Extensions**

* 2a. The list is empty.
    Use case ends.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC9 - Find a contact**

**Actor: CS Students**

**MSS**

1. CS Student chooses to find a contact.
2. ConnectNUS requests for the keyword to find the contact with.
3. CS Student enters the requested keyword.
4. ConnectNUS filters the current list of contacts to only those that contain the keyword.
5. ConnectNUS displays the filtered list.
6. Use case ends.

**Extensions**

* 3a. ConnectNUS detects an error in the command format

  * 3a1. ConnectNUS requests for the correct format.
  * User enters a new command in the correct format.
    Step 3a1 is repeated until the data entered are correct.
    Use case resumes at step 4.

**System: ConnectNUS**

**Use case: UC10 - Clear all contacts**

**Actor: CS Students**

**MSS**

1. CS Student chooses to clear all contacts.
2. ConnectNUS deletes all contacts from the contact list.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format

  * 1a1. ConnectNUS requests for the correct format.
  * User enters a new command in the correct format.
    Step 1a1 is repeated until the data entered are correct.
    Use case resumes at step 2.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC11 - Edit user's module list**

**Actor: CS Students**

**MSS**

1. CS Student chooses to edit their own module list.
2. ConnectNUS requests for the list of modules to be added and removed.
3. CS Student enters the requested lists.
4. ConnectNUS saves the edited user profile and updates the data file.
5. Use case ends.

**Extensions**

* 1a. ConnectNUS does not detect any user profile to edit

  * 1a1. ConnectNUS informs user that there is no user profile to edit.
  * Use case ends.

* 3a. ConnectNUS detects an error in the entered data

  * 3a1. ConnectNUS requests for the correct data.
  * 3a2. CS Student enters new data.
  * Steps 3a1-3a2 are repeated until the data entered are correct.
    Use case resumes at step 4.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC12 - Edit person's module list**

**Actor: CS Students**

**MSS**

1. CS Student chooses to edit a specific index of contact's module list.
2. ConnectNUS requests for the list of modules to be added and removed.
3. CS Student enters the requested lists.
4. ConnectNUS saves the edited contact and updates the data file.
5. Use case ends.


**Extensions**

* 1a. ConnectNUS detects an error in the command format or invalid index.
  * 1a1. ConnectNUS requests for the correct format or index.
  * User enters a new command in the correct format or index.
    Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes at step 2.

* 3a. ConnectNUS detects an error in the entered data

  * 3a1. ConnectNUS requests for the correct data.
  * 3a2. CS Student enters new data.
  * Steps 3a1-3a2 are repeated until the data entered are correct.
    Use case resumes at step 4.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC13 - Add lesson to user**

**Actor: CS Students**

**MSS**

1. CS Student requests to add a lesson to the user profile.
2. ConnectNUS adds a new lesson with the data to the user profile.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format.
  * 1a1. ConnectNUS requests for the correct format.
  * 1a2. User enters a new command in the correct format.
    Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes at step 2.

* 1b. ConnectNUS detects an existing lesson at the same time or overlapping time.
  * 1b1. ConnectNUS informs user of overlapping lesson.
  * 1b2. User inputs a new lesson with valid timing.
    Steps 1b1 and 1b2 are repeated until the lesson entered does not have an invalid timing.
    Use case resumes at step 2.


**System: ConnectNUS**

**Use case: UC14 - Add lesson to contact**

**Actor: CS Students**

**MSS**

1. CS Student requests to add a lesson to contact at an index.
2. ConnectNUS adds a new lesson with the data to the contact at the index specified.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format or invalid index.
  * 1a1. ConnectNUS requests for the correct format or valid index.
  * 1a2. User enters a new command in the correct format and index.
    Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes at step 2.

* 1b. ConnectNUS detects an existing lesson at the same time or overlapping time.
  * 1b1. ConnectNUS informs user of overlapping lesson.
  * 1b2. User inputs a new lesson with valid timing.
    Steps 1b1 and 1b2 are repeated until the lesson entered does not have an invalid timing.
    Use case resumes at step 2.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC15 - Remove lesson from user**

**Actor: CS Students**

**MSS**

1. CS Student requests to remove a lesson from the user profile.
2. ConnectNUS removes the lesson with the data specified from the user profile.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format.
  * 1a1. ConnectNUS requests for the correct format.
  * 1a2. User enters a new command in the correct format.
    Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes at step 2.

* 1b. ConnectNUS does not find a lesson with the data specified.
  * 1b1. ConnectNUS informs user non-existing lesson.
  * 1b2. User inputs new command with fields of an existing lesson to be removed.
    Steps 1b1 and 1b2 are repeated until the lesson entered exists.
    Use case resumes at step 2.


**System: ConnectNUS**

**Use case: UC16 - Remove lesson from contact**

**Actor: CS Students**

**MSS**

1. CS Student requests to remove a lesson from a contact at an index.
2. ConnectNUS removes the lesson with the data specified from contact at the index specified.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format or invalid index.
  * 1a1. ConnectNUS requests for the correct format or valid index.
  * 1a2. User enters a new command in the correct format and index.
    Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes at step 2.

* 1b. ConnectNUS does not find a lesson with the data specified.
  * 1b1. ConnectNUS informs user non-existing lesson.
  * 1b2. User inputs new command with fields of an existing lesson to be removed.
    Steps 1b1 and 1b2 are repeated until the lesson entered exists.
    Use case resumes at step 2.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC17 - Show User's Timetable**

**Actor: CS Students**

**MSS**

1. CS Student requests to show own Timetable.
2. ConnectNUS shows the Timetable of CS Student's current modules.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format.
    * 1a1. ConnectNUS requests for the correct format.
    * User enters a new command in the correct format.
      Steps 1a1-1a2 are repeated until the data entered are correct.
      Use case resumes at step 2.

* 1b. No user, or no lessons for user.
    * 1b1. ConnectNUS informs user of missing data.
    * Use case ends.


**System: ConnectNUS**

**Use case: UC18 - Show contact's Timetable**

**Actor: CS Students**

**MSS**

1. CS Student requests to show contact's Timetable.
2. ConnectNUS shows the Timetable of CS Student's contact's current modules.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format or index out of bounds.
    * 1a1. ConnectNUS requests for the correct format or index.
    * User enters a new command in the correct format or index.
      Steps 1a1-1a2 are repeated until the data entered are correct.
      Use case resumes at step 2.

* 1b. No lessons added to contact.
    * 1b1. ConnectNUS informs user of missing data.
    * Use case ends.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC19 - Check core modules left that user must take**

**Actor: CS Students**

**MSS**

1. CS Student requests to check modules left.
2. ConnectNUS shows what core modules the user has yet to take.
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format.
    * 1a1. ConnectNUS requests for the correct format.
    * User enters a new command in the correct format.
      Steps 1a1-1a2 are repeated until the data entered are correct.
      Use case resumes at step 2.

* 1b. No user, or no current and previous modules for user.
    * 1b1. ConnectNUS informs user of missing data.
    * Use case ends.


**System: ConnectNUS**

**Use case: UC20 - Refreshing to next semester**

**Actor: CS Students**

**MSS**

1. CS Student requests to refresh user and all persons in contact list data during the start of a new semester.
2. ConnectNUS updates the previous module list and clears the current module list of both the user and all persons in the contact list.
3. Use case ends.

**Extensions**

* 1a. No user and no persons in the contact list.
  * Use case ends.

* 2a. No current modules for user and all persons in the contact list.
  * Use case ends.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC21 - Filtering contacts tags**

**Actor: CS Students**

**MSS**

1. CS Student requests to list all contacts with the tag(s).
2. ConnectNUS shows a lists of persons from the contact list that have the tag(s).
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format.
  * 1a1. ConnectNUS requests for the correct format.
  * User enters a new command in the correct format.
    Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes at step 2.

* 2a. The list is empty.
  Use case ends.


**System: ConnectNUS**

**Use case: UC22 - Filtering contacts current modules**

**Actor: CS Students**

**MSS**

1. CS Student requests to list all contacts taking the current module(s).
2. ConnectNUS shows a lists of persons from the contact list that have the current module(s).
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format.
  * 1a1. ConnectNUS requests for the correct format.
  * User enters a new command in the correct format.
    Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes at step 2.

* 2a. The list is empty.
  Use case ends.

<div style="page-break-after: always;"></div>

**System: ConnectNUS**

**Use case: UC23 - Filtering contacts previous modules**

**Actor: CS Students**

**MSS**

1. CS Student requests to list all contacts that have taken the previous module(s).
2. ConnectNUS shows a lists of persons from the contact list that have taken the previous module(s).
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format.
  * 1a1. ConnectNUS requests for the correct format.
  * User enters a new command in the correct format.
    Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes at step 2.

* 2a. The list is empty.
  Use case ends.


**System: ConnectNUS**

**Use case: UC24 - Filtering contacts planned modules**

**Actor: CS Students**

**MSS**

1. CS Student requests to list all contacts that are planning to take the planned module(s).
2. ConnectNUS shows a lists of persons from the contact list that are planning to take the planned module(s).
3. Use case ends.

**Extensions**

* 1a. ConnectNUS detects an error in the command format.
  * 1a1. ConnectNUS requests for the correct format.
  * User enters a new command in the correct format.
    Steps 1a1-1a2 are repeated until the data entered are correct.
    Use case resumes at step 2.

* 2a. The list is empty.
  Use case ends.

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should be a result of evolving AB3 code base.
5. Should not use any Database Management System.
6. Should be platform independent and work on Windows, Linus and OS-X.
7. Should work without an installer and without approved third-party frameworks/libraries/services.
8. Should be able to package into a single JAR file.
9. JAR file size should not exceed 100MB and Documents should not exceed 15 MB per file.

[Scroll back to top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Appendix: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and Shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. Exit

   1. input `exit` on running ConnectNUS

   Expected: app closes and data is saved in `data/ConnectNUS.json`

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Adding a user

1. Adding a user when there is no existing user profile

   1. Prerequisites: There is currently no user profile stored in the application.

   2. Test case: `user n/John Doe p/98765432 e/johndoe@example.com a/1 John Street g/john-doe`<br>
      Expected: User 'John Doe' is added into the User Profile card at the top of the application with the given details.

   3. Test case: `user n/John-Doe p/98765432 e/johndoe@example.com a/1 John Street g/john-doe`<br>
      Expected: No user is added. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect user commands to try: `user`, `user x/John Doe`, `...` <br>
      Expected: Similar to previous.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Editing a user or person

1. Editing a user when there is an existing user profile

   1. Prerequisites: There is currently a user profile stored in the application.

   2. Test case: `edit user p/92345678 a/5 John Avenue`<br>
      Expected: User 'John Doe' is edited with the given details.

   3. Test case: `edit user p/9ec0sp32 a/5 John Avenue`<br>
      Expected: User is not edited. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect edit commands to try: `edit user curr/CS2100`, `edit user x/John Doe`, `...` <br>
      Expected: Similar to previous.

2. Editing a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `edit 1 e/example@yahoo.com g/bigChungus`<br>
      Expected: First contact is edited with the given details.

   3. Test case: `edit 0 e/example@yahoo.com g/bigChungus`<br>
      Expected: No person is not edited. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect edit commands to try: `edit`, `edit x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Deleting a user or person

1. Deleting an existing user profile

   1. Prerequisites: There is currently a user profile stored in the application.

   2. Test case: `delete user`<br>
      Expected: Current user is deleted.

   3. Test case: `delete user 5`<br>
      Expected: User is not deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete use`, `...` <br>
      Expected: Similar to previous.

2. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   3. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Editing a user's or person's modules

1. Editing a user's modules when there is an existing user profile

   1. Prerequisites: There is currently a user profile stored in the application.

   2. Test case: `module user curr/CS2103T prev/CS1101S plan/CS3230 rm/GESS1025`<br>
      Expected: User's modules are edited with the given details.

   3. Test case: `module user curr/CS21R4`<br>
      Expected: User's modules are not edited. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect module commands to try: `module user`, `module user p/98887777`, `...` <br>
      Expected: Similar to previous.

2. Editing a person's modules while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `module 1 curr/CS2103T prev/CS1101S plan/CS3230 rm/GESS1025`<br>
      Expected: First contact's modules are edited with the given details.

   3. Test case: `module 0 ecurr/CS2103T prev/CS1101S plan/CS3230 rm/GESS1025`<br>
      Expected: No contact's modules are not edited. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect module commands to try: `module`, `module x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Checking modules left

1. Checking modules left when there is an existing user profile

   1. Prerequisites: There is currently a user profile stored in the application.

   2. Test case: `modsleft 1` <br>
      Expected: CS core modules left to clear by the user is listed out.

   3. Test case: `modsleft 0` <br>
      Expected: Error details shown in the status message. Status bar remains the same.

   4. Other incorrect modules left commands to try: `modsleft hi`, `module x`, `...` (where x is larger than 11)<br>
      Expected: Similar to previous.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Adding a lesson, Showing the timetable

1. Add a lesson to user

   1. Prerequisites: User profile has been created. Multiple persons in the list.

   2. Test case: `timetable user` <br>
      Expected: Status message shows "No lessons added to user!".

   3. Test case: `lesson user l/tut m/CS2103T d/1 start/12:00 end/13:00` then run `timetable user` <br>
      Expected: Status message shows that lesson is added to user. Timetable window opens and shows that user has a lesson "CS2103T Tutorial 12:00 to 13:00" on Monday.


2. Add a lesson to contact

   1. Test case: `timetable 1` <br>
      Expected: Status message shows "No lessons added to contact!".

   2. Test case: `lesson 1 l/lab m/CS2100 d/4 start/16:00 end/17:00` then run `timetable 1` <br>
      Expected: Status message shows that lesson is added to contact. Timetable window opens and shows that contact has a lesson "CS2100 Lab 16:00 to 17:00" on Tuesday.

   3. Test case: `lesson 0`<br>
      Expected: No lesson is added. Error details shown in the status message.

   4. Other incorrect delete commands to try: `lesson`, `lesson x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

   5. Test case: `timetable 0`<br>
      Expected: Timetable window does not open.

   6. Other incorrect delete commands to try: `timetable 0`, `timetable x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Removing a lesson, Showing the timetable

1. Remove a lesson from user

   1. Prerequisites: User profile has been created. Multiple persons in the list. User and contacts have existing lesson added.

   2. Test case: `timetable user` <br>
      Expected: Timetable window opens and shows that user has a lesson "CS2103T Tutorial 12:00 to 13:00" on Monday.

   3. Test case: `remove user l/tut m/CS2103T d/1 start/12:00 end/13:00` then run `timetable user` <br>
      Expected: Status message shows that lesson is removed from user. Status message shows "No lessons added to user!"

2. Remove a lesson from contact

   1. Test case: `timetable 1` <br>
      Expected: Timetable window opens and shows that contact has a lesson "CS2100 Lab 16:00 to 17:00" on Tuesday.

   2. Test case: `remove 1 l/lab m/CS2100 d/4 start/16:00 end/17:00` then run `timetable 1` <br>
      Expected: Status message shows that lesson is removed from contact. Status message shows "No lessons added to contact!"

   3. Test case: `remove 0`<br>
      Expected: No lesson is added. Error details shown in the status message.

   4. Other incorrect delete commands to try: `remove`, `remove x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Refreshing to a new semester

1. Updating user and contacts previous modules list and clearing their current modules list when a new semester begins

  1. Prerequisites: There is a user.

  2. Test case: `nextsem` <br>
     Expected: All current modules in both user and all persons in the contact list will be shifted into their previous modules list.

  3. Test case: `delete user` then run `nextsem` <br>
     Expected: Status message shows that the user is deleted. Status message shows "No user created!"

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Filtering contacts tags

1. Listing out all persons in contact list with the tag(s)

   1. Test case: `filtertag friends` <br>
   Expected: All persons in the contact list with the friends tag will be listed.

   2. Test case: `clear` then run `filtertag friends` <br>
   Expected: Status message shows that the contact list has been cleared. Status message shows "0 persons listed!"

   3. Test case: `filtercurrmod CS` <br>
   Expected: Error details shown in message.
   4. Other incorrect filter module commands to try: `filtercurrmod`, `filtercurrmod 1`, `filtercurrmod C1000`,
   `filtercurrmod xxyyyy`, `...` (where any x is not an alphabet and any y is not a number).

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Filtering contacts modules

1. Listing out all persons in contact list with the current/previous/planned module(s)

  1. Test case: `filtercurrmod CS2100` <br>
     Expected: All persons in the contact list with CS2100 in their current module list will be listed.

  2. Test case: `clear` then run `filtercurrmod CS2100` <br>
     Expected: Status message shows that the contact list has been cleared. Status message shows "0 persons listed!"

  3. Test case: `filtercurrmod CS` <br>
     Expected: Error details shown in message.
     [Scroll back to top](#table-of-contents)

[Scroll back to top](#table-of-contents)

<div style="page-break-after: always;"></div>

### Saving data

1. Dealing with missing/corrupted data files

   1. Prerequisites: `data` folder with `ConnectNUS.json` file has been created.

   2. Add invalid data into any contact or user e.g.
      * Email invalid such as missing @example.com
      * " " as address
      * Invalid module name (non-alphanumeric e.g. `@@@`) in `currModules`, `prevModules` or `planModules`.
      * Non-numeric inputs or numeric inputs < 3 digits for phone
      * Invalid data for `lessons`
        * `type` not of `tut`, `lec`, `lab` or `rec`
        * Invalid module name
        * `day` not an integer between 1 and 7
        * `startTime` and `endTime` not of HH:mmm format

      Expected: ConnectNUS starts on blank window

2. Saving data accurately

   1. Run ConnectNUS normally and try `add`, `user`, `module`, `lesson` or `remove` command.

   Expected: `data/ConnectNUS.json` updates and contains correct data.

[Scroll back to top](#table-of-contents)
