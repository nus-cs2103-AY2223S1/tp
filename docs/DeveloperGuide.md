---
layout: page 
title: Developer Guide

---

- Table of Contents 
{:toc}

---

TruthTable is a **desktop app for managing software engineering teams, optimized for use via a Command Line Interface**
(CLI) while still having the benefits of a Graphical User Interface (GUI). This guide will help you get familiar with TruthTable and understand the implementations of some of our features.

## **Acknowledgements**

- Our application is based on the [AddressBook-Level3](https://se-education.org/addressbook-level3/) project created by the [SE-EDU initiative](https://se-education.org/).
- Our application makes use of the [picocli](https://picocli.info/) library for parsing  commands.
- Our application makes use of [JavaFX](https://openjfx.io/) as the UI framework.
- Our application makes use of [Jackson](https://github.com/FasterXML/jackson) as the JSON parser.
- Our application makes use of [JUnit5](https://junit.org/junit5/) as the testing framework.

---

## **Setting up, getting started**

If this is your first time contributing to our application, please take a look at the guide [_Setting up and getting started_](SettingUp.md).

---

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/AY2223S1-CS2103T-W13-4/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.

</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes
called [`Main`](https://github.com/AY2223S1-CS2103T-W13-4/tp/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2223S1-CS2103T-W13-4/tp/tree/master/src/main/java/seedu/address/MainApp.java). It
is responsible for,

- At app launch: Initializes the components in the correct sequence, and connects them up with each other.
- At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

- [**`UI`**](#ui-component): The UI of the App.
- [**`Logic`**](#logic-component): The command executor.
- [**`Model`**](#model-component): Holds the data of the App in memory.
- [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues
the command `delete person 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

- defines its _API_ in an `interface` with the same name as the component.
- implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside components being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2223S1-CS2103T-W13-4/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

The classes are related to each other as such:

![Structure of the UI Component](images/UiClassDiagram.png)

For the sake of readability and understanding, the classes related to `TeamListPanel` and `TaskDetailsPanel` have been omitted in the above diagram. They have been extracted out in a separate diagram as follows:

![Structure of the Teams Component of UI](images/UiTeamsClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, `StatusBarFooter`, `TeamListPanel`, `TeamDetailsPanel` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2223S1-CS2103T-W13-4/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-W13-4/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

- executes user commands using the `Logic` component.
- listens for changes to `Model` data so that the UI can be updated with the modified data.
- keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
- depends on some classes in the `Model` component. For example, the `PersonListPanel` displays the `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-W13-4/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `TruthTableParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddPersonCommand`) which is
   executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete person 1")` API
call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

Here are the other classes in `Logic` (some of which omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

<div markdown="span" class="alert alert-info">
:information_source: **Note:** The classes in Orange above are provided by PicoCli. For more info, check out the
[picocli documentation page](https://picocli.info/).
</div>

How the parsing works:

- When called upon to parse a user command, the `TruthTableParser` class calls the static method `tokenize` from 
  `ArgumentTokenizer`, which will split a string into a string array of tokens `args` (inspired by the 
  [Shell Command Language](https://pubs.opengroup.org/onlinepubs/9699919799/utilities/V3_chap02.html#tag_18_03), 
  a string is split into space-separated strings, except for quoted strings which will remain as a single token, 
  e.g. `token1 "token 2" token-3` will be read as 3 separate tokens `token1`, `token 2`, `token-3`).
- The `TruthTableParser` class passes `args` to the `CommandLine` class provided by picocli, which will parse the  
  command `XYZComnand` (`XYZ` is a placeholder for the specific command e.g. `DeletePersonCommand`), converting any
  arguments from strings to specific types using `ABCConverter` (`ABC` is a placeholder for the type argument e.g. 
 `IndexConverter` since `DeletePersonCommand` takes in an integer as an `Index`).
- All `ABCConverter` classes (e.g., `IndexConverter`, `NameConverter`, `EmailConverter` ...) extend the 
  `CommandLine.IConverter` interface to be utilized by picocli.

### Model component

**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-W13-4/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The `Model` component,

- stores the TruthTable data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
- stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
- stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
- does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `TruthTable`, which `Person` references. This allows `TruthTable` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-W13-4/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

- can save both TruthTable data and user preference data in json format, and read them back into corresponding
  objects.
- inherits from both `TruthTableStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
- depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

---

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add a Team Feature

#### Implementation

The `add team` feature allows users to add a specific team to their list of teams.

The `TruthTable` object is designed to have a list of teams called `UniqueTeamList`.

The following is an example of how a team is added:

Precondition: Team name is valid (it cannot be empty or begin with a space)
1. User keys in the add team command with the name of the team to be added (e.g. `add_team CS2103`)
2. A team is created added to the team list.

If the team name provided is invalid, an appropriate exception will be thrown and the respective error message will be
shown to the user.

The following activity diagram summarises the action taken when the `AddTeamCommand` is executed.
![AddTeamActivityDiagram](images/AddTeamActivityDiagram.png)

### Add a Task Feature

#### Implementation

The `add task` feature allows users to add a specific task to their team's task list. The following is an example of how
a task is added:

Precondition: Task name is valid (it cannot be empty or have quotation marks).

1. User keys in the add task command with the name of the task to be added (e.g. `add_task Complete Resume`)
2. A task is created and added to the current team's task list.

If the task name provided is invalid, an appropriate exception will be thrown and the respective error message will be
shown to the user.

The following activity diagram summarises the action taken when `AddTaskCommand` is executed:
![AddTaskActivityDiagram](images/AddTaskActivityDiagram.png)

_Activity diagram of adding a task_

#### Design considerations:

- **Alternative 1**: Store a global list of tasks and each task keeps track of which team it was created for through an
  attribute.
    - Pros: A single list of tasks makes it easier to list all the tasks associated with all teams.
    - Cons: Does not accurately model how teams actually work in terms of task distribution.
- **Alternative 2**: Each team stores a list of tasks that are associated with it.
    - Pros: Better modularisation since a team contains all the information related to itelf, including the tasks
      associated with it.
    - Cons: It is slightly more complicated to find the list of all tasks associated with a person if the person belongs
      to multiple teams since there multiple task lists.

We decided to use alternative 2 because it scales better as the number of teams increase.

### Mark Task as Done Feature

#### Implementation

The `mark` feature allows users to mark a specific task as done.

The following is an example usage scenario of how a task is marked as done:

Precondition: Task index provided is valid.

1. User keys in mark command with the specific index of the task. (e.g. `mark 1`)
2. The first task in the task list is marked as done.

If any of the following occurs:

1. Index given is negative
2. Index given is out of range (i.e. There are fewer tasks than the specified index)
3. Task has already been marked as done

Then, an appropriate exception will be thrown and the respective error message will be shown to the user.

The following activity diagram summarizes the action taken when `MarkCommand` is executed:

![MarkActivityDiagram](images/MarkActivityDiagram.png)

_Activity diagram of marking task as done_

In the `Logic` component, once `LogicManager#execute()` is called, `TruthTableParser` and `MarkCommandParser` parses the
index of the task in the user input, and generates a `MarkCommand` object. `LogicManager` then executes the
`MarkCommand` object, which sets which task in the team is to be set as done in the `Model` component. A
`CommandResult` is generated with `isCompleted` boolean value being true.

The sequence diagram of the Mark command is shown below:
![MarkSequenceDiagram](images/MarkSequenceDiagram.png)

_Sequence diagram of marking tasks as done_

### Add Member to Team Feature

#### Implementation

The add member to team feature allows users to add a user to the current team using the person's name.

The following is an example usage scenario of how a member is added to a team:

Precondition: Index provided is valid and the current working team is set to the team that the member should be added to.

1. User keys in `add_member` command along with the person's index.
2. The person at the specified index in the list is added to the team.

If any of the following occurs:

1. The index provided is less than 1
2. The index provided is greater than the number of persons in TruthTable
3. Person at the specified index is already in the team

Then, an appropriate exception will be thrown and the respective error message will be shown to the user.

The following activity diagram summarizes the action taken when the `AddMemberCommand` is executed:

![AddMemberActivityDiagram](images/AddMemberActivityDiagram.png)

_Activity diagram of adding member to team_

In the `Logic` component, once `LogicManager#execute()` is called, `TruthTableParser` and `AddMemberCommandParser`
parses the index of the person in the user input, and generates a `AddMemberCommand` object. `LogicManager` then
executes the `AddMemberCommand` object, which adds the person to the current team in the `Model` component. A
`CommandResult` is generated with a message indicating the person being added to the team.

### List Members Feature

#### Implementation

The list members feature allows users to view the members in their current team.

The list members command works similar to list command, which updates the `PersonListPanel` and shows the members in the
current team.

Currently, `PersonListPanel` displays all persons that satisfy some `Predicate`, which is stored in the
`filteredPersons` in `ModelManager`.

Whenever list members command is called, the `Predicate` for `filteredPersons` is then updated and the corresponding
members of the team is shown.

The following sequence diagram illustrates what happens within the `Logic` component when the list members command is
executed:
![ListMembersSequenceDiagram](images/ListMembersSequenceDiagram.png)

### Randomly Assign Task Feature
#### Implementation

The randomly assign task feature allows users to assign a `Task` (within a particular `Team`) to a random team member (
represented as a `Person` object) within the team, who are not already assigned to that `Task`.

This functionality is exposed to the user through the `assign_task_rand` command, and the logic is executed
in `RandomlyAssignTaskCommand#execute()`.

Given below is an example usage scenario and the state of the `Team` object at each step.

Step 1. The user launches the application and adds multiple users into the current team, as well as at least one task.
The `Team` will contain multiple `Person` objects (representing team members).

![RandomlyAssignTaskState0](images/RandomlyAssignTaskState0.png)

Step 2. The user executes the command `assign_task_rand 1` to assign the first (and only) `Task` randomly to any team
member. As none of the team members have been added, all of them are candidates for random assignment. One of them will
be randomly assigned the task.

![RandomlyAssignTaskState1](images/RandomlyAssignTaskState1.png)

Step 3. The user may want to assign a second team member to the task, hence executing `assign_task_rand 1` again. The
team member who has previously been allocated will not be considered. Similar to above, one more team member will be
randomly allocated the task.

![RandomlyAssignTaskState2](images/RandomlyAssignTaskState2.png)

<div markdown="span" class="alert alert-info">
:information_source: **Note:** If there are no team members left to allocate (e.g. all team members have already been assigned to this task), an error will be thrown.
</div>

The following activity diagram summarizes the flow of `RandomlyAssignTaskCommand#execute()`.

![RandomlyAssignTaskActivityDiagram](images/RandomlyAssignTaskActivityDiagram.png)

_Activity diagram of randomly assigning_

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

## **Appendix: Requirements**

### Product scope

**Target user profile**:

- Tech-savvy university student leading teams in software engineering modules to build software projects
- Having trouble keeping track of the team’s progress and delegating tasks effectively
- Student who prefers CLI to GUI for productivity’s sake
- Desperate for a single source of truth on who is doing what and by when

**Value proposition**:

- Users can collate different project-related information (e.g. Github project PRs, issues, links to Zoom meetings, and
  Google Docs)
- Users can visualise teams’ progress easily
- Users can delegate tasks to their teammates conveniently
- CLI interface to manage project tasks much more quickly than GUI based products

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a ...              | I want to ...                                                                            | So that I can...                                                                                     |
| -------- | --------------------- | ---------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| `* * *`  | New User              | See usage instructions                                                                   | Refer to instructions when I forget how to use the App                                               |
| `* * *`  | Team Leader           | View completed tasks                                                                     | Track the status of the project                                                                      |
| `* * *`  | Team Leader           | Add new tasks                                                                            | Track things my team needs to do                                                                     |
| `* * *`  | Team Leader           | Add team members with their contact information                                          | Keep track of my team members and contact them with their preferred mode of communication            |
| `* * *`  | Team Leader           | View tasks based on contact’s name/email                                                 | Keep track of each person’s tasks                                                                    |
| `* * *`  | Team Leader           | Assign deadlines to tasks                                                                | Track whether we are meeting deadlines for all tasks or not                                          |
| `* * *`  | Team Leader           | Create multiple stages of completion for a task (e.g. in progress, in code review, done) | See the progress of each task at a glance                                                            |
| `* * *`  | Team Leader           | Delete tasks                                                                             | Remove tasks that are no longer required to be completed or have been added on mistake               |
| `* * *`  | Team Leader           | Delete members                                                                           | Remove information of members who are no longer working on my project                                |
| `* *`    | Team Leader           | Modify existing tasks                                                                    | Update project requirements and track things to do                                                   |
| `* *`    | Team Leader           | Assign tasks to team members                                                             | Distribute workload evenly and keep everyone accountable                                             |
| `* *`    | Team Leader           | Edit the contact information of my team members                                          | Correct it if I accidentally added the wrong number/email                                            |
| `* *`    | Team Leader           | Give priority to tasks                                                                   | Better plan which tasks are to be assigned to whom and when                                          |
| `* *`    | Engineering Team Lead | Store links that lead me to an issue on the repo                                         | Easily view the diff, progress, etc.                                                                 |
| `* *`    | Team Leader           | View links to future Zoom meetings                                                       | Avoid opening Zoom separately and can directly join the meeting from the application                 |
| `* *`    | Team Leader           | Add subtasks                                                                             | Break down tasks into manageable parts                                                               |
| `* *`    | Team Leader           | View a summary of how many tasks each member has been assigned                           | Assign tasks to the members more equally, based on how occupied they might be                        |
| `* *`    | Team Leader           | Add recurring tasks such as weekly meetings                                              | Assign a recurring tasks once instead of having to schedule it every occurrence                      |
| `*`      | Team Leader           | View past meeting minutes                                                                | Refer to what has been discussed before                                                              |
| `*`      | Team Leader           | View past meetings                                                                       | Remember which date we completed each meeting                                                        |
| `*`      | Team Leader           | View upcoming meetings                                                                   | Plan for upcoming meetings                                                                           |
| `*`      | Team Leader           | Modify upcoming meetings                                                                 | Reschedule future meetings when the need arises                                                      |
| `*`      | Team Leader           | Copy team member’s email                                                                 | Easily send an email to remind him/her to do their task                                              |
| `*`      | Team Leader           | Have 2 kinds of deadlines - soft and hard                                                | Let my team members finish the task by the soft deadline and I can review/merge by the hard deadline |
| `*`      | Team Leader           | Receive reminders when a deadline is due                                                 | Ensure tasks are completed on time                                                                   |
| `*`      | Team Leader           | Randomly assign a task to any team member                                                | Assign tasks easily if nobody has any preference                                                     |

### Use cases

(For all use cases below, the **System** is the `TruthTable` and the **Actor** is the `user`, unless specified
otherwise)

**Use case: UC01 - Add a member to a team**

Preconditions: The current working team is set to the team that the member should be added to.

**MSS**

1. User requests to add member and provides member name
2. TruthTable adds the member

   Use case ends.

**Extensions**

- 1a. There is no name provided.

    - 1a1. TruthTable shows an error message.

      Use case resumes at step 1.

**Use case: UC02 - Delete a member from a team**

Preconditions: The current working team is set to the team that the member should be deleted from.

**MSS**

1. User requests to list members
2. TruthTable shows a list of members
3. User requests to delete a specific member in the list
4. TruthTable deletes the member

   Use case ends.

**Extensions**

- 2a. The list is empty.

  Use case ends.

- 3a. The given index is invalid.

    - 3a1. TruthTable shows an error message.

      Use case resumes at step 2.

**Use case: UC03 - List all members of a team**

Preconditions: The current working team is set to the team that the members should be listed from.

**MSS**

1. User requests to list members
2. TruthTable shows a list of members belonging to the team

   Use case ends.

**Extensions**

- 2a. The list is empty.

  Use case ends.

**Use case: UC04 - Add a task to a team**

Preconditions: The current working team is set to the team that the task should be added to.

**MSS**

1. User requests to add task member and provides task name and task deadline
2. TruthTable adds the task to the list of tasks

   Use case ends.

**Extensions**

- 1a. There is no task name provided.

    - 1a1. TruthTable shows an error message.

      Use case resumes at step 1.

- 1b. There is no task deadline provided.

    - 1b1. TruthTable shows an error message.

      Use case resumes at step 1.

- 1c. The task deadline is badly formatted.

    - 1c1. TruthTable shows an error message.

      Use case resumes at step 1.

**Use case: UC05 - Delete a task from a team**

Preconditions: The current working team is set to the team that the task should be deleted from.

**MSS**

1. User requests to list tasks
2. TruthTable shows a list of tasks
3. User requests to delete a specific task in the list
4. TruthTable deletes the task

   Use case ends.

**Extensions**

- 2a. The list is empty.

  Use case ends.

- 3a. The given index is invalid.

    - 3a1. TruthTable shows an error message.

      Use case resumes at step 2.

**Use case: UC06 - List all tasks of a team**

Preconditions: The current working team is set to the team that the member should be deleted from.

**MSS**

1. User requests to list tasks
2. TruthTable shows a list of tasks belonging to the team

   Use case ends.

**Extensions**

- 2a. The list is empty.

  Use case ends.

**Use case: UC07 - Add deadline to existing task**

Preconditions: The current working team is set to the team that has the existing task.

**MSS**

1. User requests to list tasks
2. TruthTable shows a list of tasks
3. User requests to add deadline to specific task in the list
4. TruthTable adds deadline to task

   Use case ends.

**Extensions**

- 2a. The list is empty.

  Use case ends.

- 3a. The given index is invalid.

    - 3a1. TruthTable shows an error message.

      Use case resumes at step 2.

- 3b. The given deadline is invalid.

    - 3b1. TruthTable shows an error message.

      Use case resumes at step 2.

**Use case: UC08 - Create new team**

**MSS**

1. User requests create new team and provides new team name
2. TruthTable creates a new team and sets current working team to new team

   Use case ends.

**Extensions**

- 1a. The given team name is used for an existing team already.

    - 1a1. TruthTable shows an error message.

      Use case resumes at step 1.

- 1b. There is no team name given.

    - 1b1. TruthTable shows an error message.

      Use case resumes at step 1.

**Use case: UC09 - Change current working team**

**MSS**

1. User requests to change current working team
2. TruthTable sets current working team to specified team

   Use case ends.

**Extensions**

- 1a. There is no team name given.

    - 1a1. TruthTable shows an error message.

      Use case resumes at step 1.
- 1b. Team provided does not exist.

    - 1b1. TruthTable shows an error message

      Use case resumes at step 1.
- 1c. Team provided already set as current team.

    - 1c1. TruthTable shows an error message.

      Use case resumes at step 1.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
4. Does not require internet connection.
5. Any changes to the data should be saved permanently and automatically.

### Glossary

- **Issue**: Generally refers to an issue created on GitHub that is used to track the progress of a software development
  project.
- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **Member**: A person in the team, working on a project.
- **Private contact detail**: A contact detail that is not meant to be shared with others
- **Repo**: A short-form for "repository" (usually on a platform such as GitHub or GitLab)
- **Task**: Anything that needs to be completed for the project to move forward.
- **Team Leader**: The person in-charge of a project, typically a software engineering project.

_{More to be added along the way}_

---

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the latest _truthTable.jar_ file from [latest release](https://github.com/AY2223S1-CS2103T-W13-4/tp/releases)
   and copy into an empty folder

    2. Double-click the jar file<br>
       Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

## Testing Commands to Manage Persons
### Adding a person
1. Adding a person to TruthTable 

    1. Test case: `add person -n John Doe -p 98765432 -e johnd@example.com -t developer designer` <br>
       Expected: If there is already a person called `John Doe` in TruthTable, then an error message will appear
       at the output box. Otherwise, a new person will be added to the list in the right output box, with name `John Doe`,
       phone number `98765432`, email `johnd@example.com`, and tags `developer` and `designer`.
   
    2. Test case: `add person -n Jane Doe -p 92345678 -e janed@example.com` <br>
           Expected: If there is already a person called `Jane Doe` in TruthTable, then an error message will appear
           at the output box. Otherwise, a new person will be added to the list in the right output box, with name
           `Jane Doe`, phone number `92345678`, email `janed@example.com`, and no tags.

    3. Test case: `add person -n John Doe -p 98765432`<br>
       Expected: No person is added. Error details shown in the message displayed at the output box.

    4. Other incorrect `add person` commands to try: `add person -p 98765432 -e johnd@example.com -t developer`,
       `add person -n John Doe -p 98765432`, `...` (where one or more attributes
       are missing in the command)
       Expected: An error message of `Invalid command format` will be displayed at the output box.

### Editing a person
1. Editing a person while all persons are being shown

    1. Prerequisites: List all persons using the `list persons` command. Person list is not empty.

    2. Test case: `edit person 1 -p 92345678 -e johndoe@example.com`
       Expected: Edits the phone number and email address of the first person to be 92345678 and johndoe@example.com
       respectively.

    3. Test case: `edit person 0 -p 92345678 -e johndoe@example.com`
       Expected: No person is edited. Error details shown in the output box.

    4. Other incorrect delete commands to try: `edit person`, `edit person x -n John`, `...`
     (where x is larger than the list size)<br>
     Expected: Similar to previous.

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list persons` command. Person list is not empty.

    2. Test case: `delete person 1`<br>
       Expected: First person is deleted from the list. Details of the deleted contact shown in the output box.

    3. Test case: `delete person 0`<br>
       Expected: No person is deleted. Error details shown in the output box.

    4. Other incorrect delete commands to try: `delete person`, `delete person x`, `...`
       (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Finding a person

1. Finding all persons whose names contain any of the given keywords

    1. Prerequisites: Person list is not empty.

    2. Test case: `find person John`<br>
       Suppose that there is a person named `John Doe`.<br>
       Expected: Message indicating number of persons found is displayed. Person list on the right updates to only show
       the persons found.

    3. Test case: `find person Jane`<br>
       Suppose that there is no person with a name containing `Jane`.<br>
       Expected: Message indicating that no persons were found is displayed.

    4. Test case: `find person`<br>
       Expected: An error message of `Invalid command format` will be displayed at the output box.

### Listing all persons

1. Listing all persons in TruthTable

   1. Test case: `list persons`<br>
      Expected: All persons stored in TruthTable are displayed.

## Testing Commands to Manage Members
### Adding a member
1. Adding a member to the currently selected team
    1. Prerequisites: List all persons using the `list persons` command. Person list is not empty.
   
    2. Test case: `add member 1` <br>
       Expected: If the first person on the person list is already a member in the team, then an error message will be
       displayed on the output box. Otherwise, the first person is added to the team from the list.
       Details of the added member shown in the output box.
    
    3. Test case: `add member 0` <br>
       Expected: No member is added. Error details shown in the output box.

    4. Other incorrect delete commands to try: `add member`, `add member x`, `...`
       (where x is larger than the person list size)<br>
       Expected: Similar to previous.

### Deleting a member

1. Deleting a member to the currently selected team

    1. Prerequisites: List all members using the `list members` command. Member list is not empty.

    2. Test case: `delete member 1`<br>
       Expected: First member is deleted from the list. Details of the deleted member shown in the output box.

    3. Test case: `delete member 0`<br>
       Expected: No member is deleted. Error details shown in the output box.

    4. Other incorrect delete commands to try: `delete member`, `delete member x`, `...`
       (where x is larger than the member list size)<br>
       Expected: Similar to previous.

### Finding a member

1. Finding all members whose names or emails contain any of the given keywords

    1. Prerequisites: Member list is not empty.

    2. Test case: `find member -n John`<br>
       Suppose that there is a member on the team named `John Doe`.<br>
       Expected: Message indicating number of persons found is displayed. Member list updates to only show
       the persons found.

    3. Test case: `find member -n Jane`<br>
       Suppose that there is no member with a name containing `Jane`.<br>
       Expected: Message indicating that no persons were found is displayed.

    4. Test case: `find member`<br>
       Expected: An error message of `Invalid command format` will be displayed at the output box.

### Listing all members

1. Listing all members in the currently selected team

    1. Test case: `list members`<br>
       Expected: All members in the currently selected team in TruthTable are displayed.

### Sorting members

1. Sorting members in the currently selected team
    1. Test case: `sort members asc`<br>
       Expected: Team members are sorted in ascending order in member list.

    2. Test case: `sort members dsc`<br>
       Expected: Team members are sorted in descending order in member list.

    3. Test case: `sort members res`<br>
       Expected: Order of team members in member list is reset.

    4. Test case: `sort members`<br>
       Expected: An error message of `Invalid command format` will be displayed at the output box.

## Testing Commands to Manage Teams
### Adding a team
1. Creating a team on TruthTable

    1. Test case: `add team CS2102 -d "Database Systems"` <br>
       Expected: If there is already a team called `CS2102` in TruthTable, then an error message will appear
       at the output box. Otherwise, a new team will be added to the team list tabs, with name `CS2102`,
       and team description `Database Systems`.

    2. Test case: `add team CS2103T` <br>
       Expected: If there is already a team called `CS2103T` in TruthTable, then an error message will appear
       at the output box. Otherwise, a new team will be added to the team list tabs, with name
       `CS2103T` and a default team description.

    3. Test case: `add team -d "Software Engineering"`<br>
       Expected: No team is created. Error details shown in the message displayed at the output box.

    4. Test case: `add team`<br>
       Expected: An error message of `Invalid command format` will be displayed at the output box.

### Editing a team
1. Editing the current team on TruthTable

    1. Prerequisites: The current working team is set to the team to be edited

    2. Test case: `edit team -n CS2102 -d "Database Systems`
       Expected: Edits the team name and team description of the current team to be `CS2102` and `Database Systems`
       respectively.

### Deleting a team

1. Deleting an existing team from TruthTable

    1. Prerequisites: The target team is not the only existing team

    2. Test case: `delete team CS2103T`<br>
       Expected: If there is no team named `CS2103T`, an error message is displayed in the output box and no team will
       be deleted. Otherwise, the team with name `CS2103T` will be deleted from TruthTable

### Setting a team

1. Sets the current working team to the target team

     1. Test case: `set team CS2103T`<br>
        Expected: If there is no team named `CS2103T`, an error message is displayed in the output box the current
        working team will not be changed. Otherwise, the team with name `CS2103T` will be set as the current working
        team.

## Testing Commands to Manage Tasks
### Adding a task
1. Adding a task to TruthTable

    1. Test case: `add task "Create PR"` <br>
       Expected: If there is already a task called `Create PR` in TruthTable, then an error message will appear
       at the output box. Otherwise, a new task will be added to the task list, with name `Create PR`.

    2. Test case: `add task "Review PR" -a 1 3 -d 2022-12-02 23:59` <br>
       Expected: If there is already a task called `Review PR` in TruthTable, then an error message will appear
       at the output box. Otherwise, a new task will be added to the task list, with name
       `Review PR`, assigned to the first and third members of your team's members list, and a
       deadline of 2nd Dec 2022 23:59.

    3. Test case: `add task -a 1 3 -d 2022-12-02 23:59`<br>
       Expected: No person is added. Error details shown in the message displayed at the output box.

### Editing a task
1. Editing a task while all tasks are being shown

    1. Prerequisites: List all tasks in the current team using the `list tasks` command. Task list is not empty.

    2. Test case: `edit task 1 -n "Merge PR" -a 1 -d 2022-12-02 23:59 `
       Expected: The first task in the current team's task list is edited, setting the name as
       `Merge PR`, assignees as the first member in the team list, and deadline as 2nd Dec 2022 23:59.

    3. Test case: `edit task 1 -a`
       Expected: The first task in the current team's task list is edited, removing all assignees from the task.
       The name and deadline are not modified in this example.

    4. Other incorrect delete commands to try: `edit task`, `edit task x -n Meeting`, `...`
       (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Deleting a task

1. Deleting a task while all tasks are being shown

    1. Prerequisites: List all tasks using the `list tasks` command. Task list is not empty.

    2. Test case: `delete task 1`<br>
       Expected: First task is deleted from the list. Details of the deleted task shown in the output box.

    3. Test case: `delete task 0`<br>
       Expected: No task is deleted. Error details shown in the output box.

    4. Other incorrect delete commands to try: `delete task`, `delete task x`, `...`
       (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Finding a task

1. Finding all tasks whose names contain any of the given keywords

    1. Prerequisites: Task list is not empty.

    2. Test case: `find task -n User Guide`<br>
       Suppose that there is a task named `User Guide`.<br>
       Expected: Message indicating number of tasks found is displayed. Task list updates to only show
       the tasks found.

    3. Test case: `find task -n Review PR`<br>
       Suppose that there is no task with a name containing `Review` or `PR`.<br>
       Expected: Message indicating that no tasks were found is displayed.

    4. Test case: `find task`<br>
       Expected: An error message of `Invalid command format` will be displayed at the output box.

### Listing all tasks

1. Listing all tasks in the current team

    1. Test case: `list tasks`<br>
       Expected: All tasks in the current team are displayed.

### Mark tasks as done

1. Marking a specified task as done
    1. Prerequisites: Task list is not empty.

    2. Test case: `mark 1`<br>
       Expected: If the first task is already marked as done, an error message is shown in the output box. Otherwise,
       the first task in the team is marked as done.

    3. Test case: `mark 0`<br>
       Expected: No task is marked as done. Error details shown in the output box.

    4. Other incorrect delete commands to try: `mark`, `mark x`, `...`
       (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Unmark tasks as done

1. Undoing the mark command to mark specified task as incomplete
    1. Prerequisites: Task list is not empty.

    2. Test case: `unmark 1`<br>
       Expected: If the first task has not been marked as done, an error message is shown in the output box. Otherwise,
       the first task in the team is marked as incomplete.

    3. Test case: `unmark 0`<br>
       Expected: No task is marked as incomplete. Error details shown in the output box.

    4. Other incorrect delete commands to try: `unmark`, `unmark x`, `...`
       (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Setting Deadline for task

1. Setting a deadline for an existing task
    1. Prerequisites: Task list is not empty.

    2. Test case: `set deadline 1 2023-12-25 23:59`<br>
       Expected: The deadline for the first task on the task list is set as 25 Dec 2023 23:59.

    3. Test case: `set deadline 0 2023-12-25 23:59`<br>
       Expected: No deadline is set for any task. Error details shown in the output box.

    4. Other incorrect set deadline commands to try: `set deadline`, `set deadline x 2023-12-25 23:59`, `...`
       (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Assigning a task to team member

1. Assign an existing task to a team member in the current team.
    1. Prerequisites: Task list is not empty. Multiple tasks in task list. Member list is not empty. Multiple tasks
       in member list.

    2. Test case: `assign task 2 -a 1 2`<br>
       Expected: The second task on the task list is assigned to the first and second member in the team.

    3. Test case: `assign task 1 -a`<br>
       Expected: No assignee is set for any task. Error details shown in the output box.

    4. Test case: `assign task 0 -a 1 2`<br>
       Expected: No assignee is set for any task. Error details shown in the output box.

    5. Other incorrect set deadline commands to try: `assign task`, `assign task x -a x`, `...`
       (where x is larger than the task list and member list size)<br>
       Expected: Similar to previous.

### Assigning a task to random team member

1. Assign an existing task to a random team member in the current team.
    1. Prerequisites: Task list is not empty. Multiple tasks in task list. Member list is not empty. Task is not
       already assigned to all members of the team.

    2. Test case: `assign random 1`<br>
        Expected: The first task on the task list is assigned to a random team member.

    3. Test case: `assign random 0`<br>
       Expected: No assignee is set for any task. Error details shown in the output box.

    4. Other incorrect set deadline commands to try: `assign random`, `assign random x`, `...`
       (where x is larger than the task list size)<br>
       Expected: Similar to previous.

### Filtering tasks by team member

1. Find all tasks that have been assigned to a particular member in the currently selected team.
    1. Prerequisites: Member list is not empty.

    2. Test case: `tasksof 1`<br>
       Expected: All tasks assigned to the first member in your current team's member list is displayed.

    3. Test case: `tasksof 0`<br>
       Expected: Error details shown in the output box.

    4. Other incorrect set deadline commands to try: `tasksof`, `tasksof x`, `...`
       (where x is larger than the member list size)<br>
       Expected: Similar to previous.

### Sorting members

1. Sorting tasks in the currently selected team
    1. Test case: `sort tasks asc`<br>
       Expected: Tasks are sorted in ascending alphabetical order in task list, based on their names.

    2. Test case: `sort tasks dsc`<br>
       Expected: Tasks are sorted in descending alphabetical order in task list, based on their names.

    3. Test case: `sort tasks res`<br>
       Expected: Order of tasks in task list is reset.

    4. Test case: `sort tasks`<br>
       Expected: An error message of `Invalid command format` will be displayed at the output box.

### View summary of task assignments in team

1. Viewing the number of tasks assigned to each member in the team.

    1. Test case: `summary`<br>
       Expected: The number of tasks assigned to each member in the team is displayed in the output box.

## Testing Commands to Manage Links
### Adding a new link
1. Add a new link to the currently selected team

    1. Test case: `add link -n google -l https://google.com` <br>
       Expected: If there is already a link called `google` in TruthTable, then an error message will appear
       at the output box. Otherwise, a new link will be added to the link list, with name `google`,
       and URL `https://google.com`.

    2. Test case: `add link -n google"`<br>
       Expected: No link is created. Error details shown in the message displayed at the output box.

    3. Test case: `add link`<br>
       Expected: An error message of `Invalid command format` will be displayed at the output box.

### Editing a link
1. Editing an existing link in the team

    1. Prerequisites: Task list is not empty.

    2. Test case: `edit link 1 -n facebook -l https://facebook.com`
       Expected: The first link in the current team's link list is edited, setting the name as
       `facebook`, with the URL of "https://facebook.com".

    3. Test case: `edit link 1 -n google`
       Expected: The first link in the current team's link list is edited, setting the name as
       `google`.

    4. Test case: `edit link 0 -n google`
       Expected: No link is edited. Error details shown in the output box.

    5. Other incorrect delete commands to try: `edit link`, `edit link x -n Meeting`, `...`
       (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Deleting a link

1. Deleting an existing link from the team

    1. Prerequisites: Link list is not empty.

    2. Test case: `delete link 1`<br>
       Expected: The first link will be deleted from the link list
