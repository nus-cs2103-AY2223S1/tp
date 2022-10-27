---
layout: page
title: Developer guide
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

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

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
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagramNew.png" width="450" />


The `Model` component,

* stores the address book data:
  * all `Person` objects (which are contained in a `UniquePersonList` object).
  * all `Group` objects (which are contained in a `UniqueGroupList` object).
* stores the currently 'selected' `Person` or `Group` objects (e.g., results of a search query) as separate _filtered_ lists which are exposed to outsiders as
unmodifiable `ObservableList<Person>` and `ObservableList<Group>`that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the lists change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

**Person** : [`Person.java`](https://github.com/AY2223S1-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/model/person/Person.java)

The `Person` component in relation to `Addressbook` and `UniquePersonList` is given in further detail here.

<img src="images/BetterModelClassDiagram.png" width="450" />

The `Person` component,

* is composed of `Name`, `Phone`, `Email`, `Address` mandatory attributes
* references any number of `Tags` from the `UniqueTagList` in `Addressbook`. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.
* references any number of `Assignments` stored in a `Hashmap<GroupName, Assignment>`. This enables keeping track of a `Person`'s assignments under a specific `Group`.

**Group** : [`Group.java`](https://github.com/AY2223S1-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/model/group/Group.java)

The `Group` component in relation to `Addressbook` and `UniqueGroupList` is given in further detail here.

<img src="images/GroupClassDiagram.png" width="450" />

The `Group` component,
* is composed of `GroupName` mandatory attribute
* references any number of `Persons` from the `UniquePersonList` in Addressbook. This allows `AddressBook` to only require one `Person` object per unique person, instead of each `Group` needing their own `Person` objects.

**Assignment** : [`Assignment.java`](https://github.com/AY2223S1-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/model/assignment/Assignment.java)

The `Assignment` component,
* is composed of `Task` mandatory attribute, which is a string description of the `Assignment`.
* is composed of `Workload` mandatory attribute, coded as an enum of `High`, `Medium` or `Low`.
* has an optional `Deadline` attribute which is a `LocalDateTime` object.

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagramNew.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------
## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### **\[Developed\] Add/Delete Group feature**

#### **Implementation**
This feature allows groups to be added to and deleted from TABS, facilitated by the `UniqueGroupList`.
It is achieved by the following operations:

- `Model#addGroup(GroupName)` - Adds a group to TABS with the input groupname.
- `Model#deleteGroup(GroupName)` - Deletes the group with the input groupname, and removes any members and assignments associated.

Given below is an example usage scenario and how groups are added/deleted at each stage.

**Step 1.**
The user launches the application for the first time. Presuming they have not made changes to the default persons,
the `AddressBook` model looks like this (some Persons removed for simplicity):

<img src="images/AddDeleteGroupState0.png" width="300" />

**Step 2.**
User executes `addgroup g/CS2103T`. This causes a new group with `GroupName` "CS2103T" and no members to be
added to the `AddressBook` model, reflected below:

<img src="images/AddDeleteGroupState1.png" width="300" />

**Note:** The associated parser `AddGroupCommandParser` checks that the entered group name is valid, following same
conventions as naming a `Person`, and the command itself `AddGroupCommand` checks that a group of the same name does
not already exist in the app.

**Step 3.**
Suppose now the user adds `Alice` and `Bob` as members of the CS2103T, and assigns `Alice` a task under the group.
The `AddressBook` model now looks like this:

<img src="images/AddDeleteGroupState2.png" width="300" />

**Step 4.**
User executes `deletegroup g/CS2103T`. This deletes the group with `GroupName` "CS2103T" and additionally:
- Removes any members of the group e.g. `Alice` and `Bob` in this instance
- Removes any tasks associated with the group e.g. `Alice`'s task.
The `AddressBook` model now looks like this:

<img src="images/AddDeleteGroupState0.png" width="300" />

**Note:** The associated parser `DeleteGroupCommandParser` checks that the entered group name is valid, following same
conventions as naming a `Person`, and the command itself `DeleteGroupCommand` checks that a group with this name exists
in the app. Below is an activity diagram reflecting this:

<img src="images/DeleteGroupActivityDiagram.png" width="550" />

For simplicity, only the `deletegroup` command sequence diagram is shown below. Both commands operate via a similar sequence:

<img src="images/DeleteGroupSequenceDiagram.png" width="800" />
<img src="images/DeleteGroupCommandExecutesSequenceDiagram.png" width="400" />

-----

### **\[Developed\] Add/Delete member feature**

#### **Implementation**
This feature allows members to be added to and deleted from a group. It is
achieved by the following operations:

- `Model#addMember(Name, GroupName)` - Adds the person with the input name to the group with input groupname.
- `Model#deleteMember(Name, GroupName)` - Removes the person with the input name from the group with the input groupname.

Given below is an example usage scenario and how groups are added/deleted at each stage.

**Step 1.**
Starting from the default persons, the user has executed `addgroup g/CS2103T` to add a group with
`GroupName` "CS2103T". The `AddressBook` model is reflected below:

<img src="images/AddDeleteMemberState0.png" width="300" />

**Step 2.**
User executes `addmember g/CS2103T n/Alice` to add `Alice` to the
group `CS2103T`. The associated command `AddGroupMemberCommand` first
constructs an `editedPerson` and `editedGroup` to replace `Alice` and
`CS2103T` respectively, before calling `Model#setPerson()` and
`Model#setGroup()` with the respective edits to change `Alice` and
`CS2103T` in the `AddressBook`.
The `AddressBook` model is reflected below:

<img src="images/AddDeleteMemberState1.png" width="300" />

**Note:** The command itself `AddGroupMemberCommand` checks that both person `Alice` and group
`CS2103T` exist in the app, and that `Alice` is not already a member of `CS2103T`.

**Step 3.**
Suppose the user assigns `Alice` a task under the group. The `AddressBook` model now looks like this:

<img src="images/AddDeleteMemberState2.png" width="300" />

**Step 4.**
User executes `deletemember g/CS2103T n/Alice`. This removes `Alice` from `CS2103T` and removes any tasks
associated with the group. The associated command `DeleteGroupMemberCommand` first
constructs an `editedPerson` and `editedGroup` to replace `Alice` and
`CS2103T` respectively, before calling `Model#setPerson()` and
`Model#setGroup()` with the respective edits to change `Alice` and
`CS2103T`.
The `AddressBook` model now looks like this:

<img src="images/AddDeleteMemberState0.png" width="300" />

**Note:** The command itself `DeleteGroupMemberCommand` checks that both person `Alice` and group
`CS2103T` exist in the app, and that `Alice` is a member of `CS2103T` prior to deletion.

For simplicity, only the `deletemember` command sequence diagram is shown below. Both commands operate via a similar sequence:

<img src="images/DeleteMemberSequenceDiagram.png" width="800" />
<img src="images/DeleteGroupMemberCommandExecutesSequenceDiagram.png" width="400" />


-----

### **\[Developed\] Display/List Group feature**

#### **Implementation**
This feature allows a single group to be listed, or displays all groups. It is facilitated
by the following operations:

- `Model#displayGroup(GroupName)` - Finds and lists the group with the input groupname.
- `Model#listGroups()` - Displays all groups in TABS

Given below is an example usage scenario and how groups can be individually displayed/displayed altogether in TABS.

**Step 1.**
Starting from the default persons, the user has executed `addgroup g/CS2103T` to add a group with
`GroupName` "CS2103T".

**Step 2.**
User executes `displaygroup g/CS2103T`. The associated command `DisplayGroupCommand` calls
`Model#updateFilteredGroupList(predicate)` with the given predicate being the `GroupName` CS2103T
to display just the group with that name.

**Note:**
`DisplayGroupCommand` first checks validity of input against the full group list, obtained as an
`ObservableList<Group>` from `Model#getFilteredGroupList()`. If the input `GroupName` does not correspond
to a group in this list, a `CommandException` will be thrown notifying the user accordingly.

**Step 3.**
User executes `listgroups`. The associated command `ListGroupsCommand` calls
`Model#updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS)`to display all groups in the app.

For simplicity, only the `displaygroup` command sequence diagram is shown below. Both commands operate via a similar sequence:

<img src="images/DisplayGroupSequenceDiagram.png" width="800" />
<img src="images/DisplayGroupCommandExecutesSequenceDiagram.png" width="400" />

----

### **\[Developed\] Assign/Delete Task feature**

#### **Implementation**
This feature allows the user to assign a task to a member, or remove a task from a member. It is facilitated
by the following operations:

- `Model#assignTask(GroupName, Name, Task, Workload, [Deadline])` - Creates an `Assignment` with `Task` (description),
`Workload` and optional `Deadline`. This `Assignment` will be added to the person with the input name, under the group
with the input groupname (a person can hold multiple `Assignments` of the same `Task`, but under different groups).
- `Model#deleteTask(GroupName, Name, Task)`. Deletes the `Assignment` with description `Task` from the person with the input name,
under the group with the input groupname.

Given below is an example usage scenario and how tasks are assigned/deleted at each stage.

**Step 1.**
Starting from the default persons, the user has executed `addgroup g/CS2103T` to add a group with
`GroupName` "CS2103T", then `addmember g/CS2103T n/Alice` to add `Alice` to
the group `CS2103T`. The `AddressBook` model is reflected below:

<img src="images/AddDeleteMemberState1.png" width="300" />

**Step 2.**
User executes `assigntask Alice g/CS2103T task/Task w/High`. This:
- Constructs an Assignment with `Task` "Task" and `Workload` "High".
- Constructs an `editedPerson` to replace `Alice` with a copy which
includes this Assignment, and an `editedGroup` containing the edited
`Alice`.
- Calls `Model#setPerson()` and `Model#setGroup()` with the respective
edits to change `Alice` and `CS2103T` in the `AddressBook`.

The `AddressBook` model is reflected below:

<img src="images/AddDeleteMemberState2.png" width="300" />

**Note:** `AssignTaskCommandParser` will check if the assignment's parameters are parsed properly,
such as `Task` being non-empty, `Deadline` being a valid date, etc. 
`AssignTaskCommand` further checks if both the person and group with the specified name and groupname respectively
exist in the app, that the person is a member of the group, and the person doesn't already have a similar task under
the group.

The user flow of Assign Task can be illustrated in the Activity Diagram as shown below.

<img src="images/AssignTaskActivityDiagram-AssignTaskCommand.png" width="800" />

**Step 3.**
User executes `deletetask Alice g/CS2103T task/Task`. This constructs an `editedPerson`
to replace `Alice` with a copy which removes the Assignment whose `Task` matches input,
and an `editedGroup` containing the edited `Alice`. This also calls `Model#setPerson()` and `Model#setGroup()` with the respective
edits to change `Alice` and `CS2103T` in the `AddressBook`.

The `AddressBook` model is reflected below:

<img src="images/AddDeleteMemberState2.png" width="300" />

**Note:** `DeleteTaskCommand` checks if both the person and group with the specified name and groupname respectively
exist in the app, that the person is a member of the group, and the person has the specified task under
the group.

For simplicity, only the `deletetask` command sequence diagram is shown below. Both commands operate via a similar sequence:

<img src="images/DeleteTaskSequenceDiagram.png" width="1000" />
<img src="images/DeleteTaskCommandExecuteSequenceDiagram.png" width="400" />

----

### **\[Developed\] Bulk Assignment & Deletion of Tasks**

#### **Implementation** 
This feature allows tasks to be assigned to/deleted from all members of a group simultaneously.
It is facilitated by the following operations:

- `Model#assignTaskAll(GroupName, Task, Workload, [Deadline]` - Creates an `Assignment` with `Task` (description),
  `Workload` and optional `Deadline`. This `Assignment` will be added to all members in the group
  with the input groupname. A member who has a duplicate assignment is skipped over.
- `Model#deleteTask(GroupName, Name, Task)`. Deletes the `Assignment` with description `Task` from all members
  in the group with the input groupname. A member who does not have this assignment is skipped over.

Given below is an example usage scenario and how tasks are assigned/deleted in bulk.

**Step 1.**
Starting from the default persons, the user has executed `addgroup g/CS2103T` to add a group with
`GroupName` "CS2103T", then `addmember g/CS2103T n/Alice`, `addmember g/CS2103T n/Alice` to add `Alice` and `Bob` to
the group `CS2103T`. The `AddressBook` model is reflected below:

<img src="images/BulkAssignDeleteTaskState0.png" width="300" />

**Step 2.**
User executes `assigntaskall g/CS2103T task/Task1 w/High`. This:
- Constructs an Assignment with `Task1` "Task" and `Workload` "High".
- For each member `Alice` and `Bob`:
  - Constructs an `editedPerson` to replace the member with a copy which
    includes this Assignment, and an `editedGroup` containing the edited
    member.
  - Calls `Model#setPerson()` and `Model#setGroup()` with the respective
    edits to change member and `CS2103T` in the `AddressBook`.

The `AddressBook` model is reflected below:

<img src="images/BulkAssignDeleteTaskState1.png" width="300" />

**Note:** `AssignTaskAllCommandParser` will check if the assignment's parameters are parsed properly,
such as `Task` being non-empty, `Deadline` being a valid date, etc.
`AssignTaskAllCommand` further checks if group with the specified groupname exists in the app. Each member in the group
is handled in a for loop. Members who already have the assignment enter a `continue` block to the next iteration
and are not edited.

**Step 3.**
User executes `deletetaskall g/CS2103T task/Task`. Similar to above:
- For each member `Alice` and `Bob`:
  - Constructs an `editedPerson` to replace the member with a copy which
    removes this Assignment, and an `editedGroup` containing the edited
    member.
  - Calls `Model#setPerson()` and `Model#setGroup()` with the respective
    edits to change member and `CS2103T` in the `AddressBook`.

The `AddressBook` model is reflected below:

<img src="images/BulkAssignDeleteTaskState0.png" width="300" />

**Note:** `DeleteTaskAllCommand` checks if group with the specified groupname exists in the app. Each member in the group
is handled in a for loop. Members who do not have the assignment enter a `continue` block to the next iteration
and are not edited.

For simplicity, only the `deletetaskall` command sequence diagram is shown below. Both commands operate via a similar sequence:

<img src="images/DeleteTaskAllSequenceDiagram.png" width="800" />
<img src="images/DeleteTaskAllCommandExecuteSequenceDiagram.png" width="400" />

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
Project team leaders with many projects, members and tasks to assign.

**Value proposition**:
* view information of which group members are in their project.
* track which tasks have been assigned to which members.
* see an estimate of how much workload each member has.
* receive information regarding upcoming deadlines.


### User stories

#### Priorities:
- High (must have) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `* * *`
- Medium (nice to have) - `* *`
- Low (unlikely to have) &nbsp;- `*`

| Priority | As a ...          | I want to ...                                                                  | So that I can ...                                                                      |
|:---------|:------------------|:-------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------|
| `* * * ` | beginner user     | see usage instructions                                                         | refer to instructions when I forget how to use the App                                 |
| `* * *`  | beginner user     | add contacts to my app                                                         |                                                                                        |
| `* * *`  | beginner user     | remove existing contacts on my app                                             | remove entries that I no longer need                                                   |
| `* * *`  | beginner user     | create a group                                                                 |                                                                                        |
| `* * *`  | beginner user     | add members to a group                                                         |                                                                                        |
| `* *`    | intermediate user | locate a particular contact                                                    | quickly find a member                                                                  |
| `* *`    | intermediate user | have a quick overview of all my groups with their existing members             |                                                                                        |
| `* * *`  | intermediate user | assign tasks to members                                                        |                                                                                        |
| `* *`    | intermediate user | have a quick overview of tasks assigned to members in the group                |                                                                                        |
| `* *`    | intermediate user | create a tag specific to the group project                                     | quickly assign tags to members                                                         |
| `* *`    | intermediate user | place tags on group members                                                    | better identify their role                                                             |
| `* *`    | intermediate user | assign multiple tags to a user if needed                                       | identify their roles more specifically                                                 |
| `* *`    | intermediate user | filter and search for groups                                                   | quickly identify the one in particular                                                 |
| `* *`    | advanced user     | view deadlines for each project                                                | periodically use this for self-reminder                                                |
| `* *`    | advanced user     | have a rough sense of the workload of every member in the group                | assign future tasks with more confidence                                               |
| `*`      | advanced user     | view a member’s tasks in more detail                                           | assign future tasks to them with more confidence                                       |
| `* * *`  | advanced user     | add more tasks to a member                                                     |                                                                                        |
| `* * *`  | advanced user     | delete tasks from a member                                                     |                                                                                        |
| `*`      | advanced user     | categorise the tasks assigned into different levels of intensity               | not judge workload based solely on the number of tasks per member                      |
| `* * *`  | advanced user     | delete unused groups after the project is completed                            | declutter my app                                                                       |
| `* * *`  | advanced user     | delete existing tags if they are no longer relevant                            | declutter my app                                                                       |
| `*`      | advanced user     | reuse existing tags in groups for future projects                              | establish new projects under my management style                                       |
| `*`      | advanced user     | move tags and assignments from one user to another easily                      | ensure that members can ‘swap’ roles hassle-free                                       |
| `* *`    | expert user       | perform group-wide addition of tags and assignments                            | ensure that repetitive new assignments are made as quickly and accurately as possible. |
| `* *`    | expert user       | perform group-wide removal of tags and assignments                             | ensure that group members’ roles are quickly cleared owing to new demands              |
| `*`      | expert user       | be notified when a member completes his task or when a deadline is approaching | better manage my time                                                                  |
| `*`      | expert user       | create shortcuts and pin most important projects on the top of the app         | access these projects faster                                                           |
| `*`      | expert user       | have the choice of deleting users from the app when a project completes        | quickly declutter my app                                                               |
| `*`      | expert user       | set timers to add/delete groups after a project ends                           | ensure that I do not have too many groups cluttering the database                      |

---
## Use cases 

For all use cases below, the **System** is TABS and the **Actor** is the user, unless specified otherwise.

### UC1: Help

**MSS**

1. User indicates they want help.
2. TABS Displays UG link.

&nbsp;&nbsp;
Use case ends.

### UC2: Add a person

**MSS**

1. User requests add a person.
2. TABS display that person is successfully added.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The contact already exists in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS displays that the person already exists in the program.

&nbsp;&nbsp;
Use case ends.

### UC3: Edit a person’s details

**MSS**

1. User requests to edit an existing contact.
2. TABS displays the modified contact.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user and displays error messages.

&nbsp;&nbsp;
Use case ends.

### UC4: Find a person

**MSS**

1. User requests to find an existing contact.
2. TABS displays the contacts found.

&nbsp;&nbsp;
Use case ends.

**Extensions**

&nbsp;&nbsp;
2a. The list is empty.

&nbsp;&nbsp;
Use case ends.

### UC5: Delete a person

**MSS**

1. User requests to delete an existing contact.
2. TABS displays that the contact is successfully deleted.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user and indicates error messages.

&nbsp;&nbsp;
Use case ends.

### UC6: Add a group

**MSS**

1. User requests add a group.
2. TABS displays the added group.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The group already exists in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS displays that the group already exists inside TABS.

&nbsp;&nbsp;
Use case ends.

### UC7: Display a group

**MSS**

1. User requests to display an existing group.
2. TABS displays the group with its members.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user and displays error messages.

&nbsp;&nbsp;
Use case ends.

### UC8: Find a group

**MSS**

1. User requests to find an existing group.
2. TABS displays the groups found.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
2a. The list is empty.

&nbsp;&nbsp;
Use case ends.

### UC9: Delete a group

**MSS**

1. User requests to delete an existing group.
2. TABS displays that the group is successfully deleted.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

### UC10: Add a person to group

**MSS**

1. User requests to add an existing contact to an existing group.
2. TABS displays that the contact specified is added to the group.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

&nbsp;&nbsp;
1b. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1b1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

### UC11: Remove a person from group

**MSS**

1. User requests to remove an existing contact from an existing group.
2. TABS displays that the contact specified is removed from the group.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

&nbsp;&nbsp;
1b. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1b1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

### UC12: Assign a task to a member

**MSS**

1. User requests to assign a task.
2. TABS displays that the task is tagged to the person specified under the group.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

&nbsp;&nbsp;
1b. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1b1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

### UC13: Remove a task from a person

**MSS**

1. User requests to remove a task from a person.
2. TABS displays that the task is successfully removed.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
2a. The person specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
2a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

&nbsp;&nbsp;
3a. The person does not have the specified task.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
3a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

### UC14: Assign a tag to a person

**MSS**

1. User requests to assign a tag to a person in TABS.
2. TABS displays that the tag is added to the person specified.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The contact specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

### UC15: Remove a tag from a person

**MSS**

1. User requests to remove a tag from a person.
2. TABS displays that the tag is successfully removed.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The person specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

&nbsp;&nbsp;
1a. The person does not have the specified tag.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

### UC16: Group assignment of task

**MSS**

1. User requests to add a task to a group.
2. TABS displays that the task is successfully added.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

### UC17: Group removal of task

**MSS**

1. User requests to remove a task from a group.
2. TABS displays that the task is successfully removed.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

&nbsp;&nbsp;
1a. The person does not have the specified task.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS moves on to the next person in the group.

&nbsp;&nbsp;
Use case ends.

### UC18: Group assignment of tag

**MSS**

1. User requests to add a tag to a group.
2. TABS displays that the tag is successfully added.

&nbsp;&nbsp;
Use case ends.

Extensions:

&nbsp;&nbsp;
1a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

### UC19: Group removal of tag

**MSS**

1. User requests to remove a tag from a group.
2. TABS displays that the tag is successfully removed.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. The group specified is not found in TABS.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS terminates the operation by the user.

&nbsp;&nbsp;
Use case ends.

&nbsp;&nbsp;
1a. The person does not have the specified tag.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
1a1. TABS moves on to the next person in the group.

&nbsp;&nbsp;
Use case ends.

### UC20: Notifications

**MSS**

1. User boots up the application/requests for notifications.
2. TABS provides notifications on upcoming deadlines/completed tasks.

&nbsp;&nbsp;
Use case ends.

**Extensions:**

&nbsp;&nbsp;
1a. List is empty.

&nbsp;&nbsp;
Use case ends.

---
## Non-functional requirements

1. Should work on any **mainstream** OS (Windows, Linux, Unix, OS-X) as long as it has `Java 11` or above installed.
2. Should be able to hold up to 500 persons and/or 10 groups without noticeable sluggishness in performance.
3. Bulk assignments/removals should be able to handle up to 20 persons without noticeable sluggishness in performance.
4. Workload indicators, if using colours, should be easily distinguishable.
5. Content should be saved to a save file that can be opened and edited with mainstream text editors e.g. Notepad.
