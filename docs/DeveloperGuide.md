---
layout: page
title: Developer Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction

CodeConnect is a **desktop app** specially designed for **Computer Science students from NUS**. With this app, not only can you manage your **tasks and contacts** effectively in one unified place, you can also conveniently search for peers to seek help or collaboration on a particular task.

This developer guide serves both as a **guide for new contributors** to navigate and start working on the project and as a reference to various code and feature **design decisions** for existing developers.

--------------------------------------------------------------------------------------------------------------------

## Acknowledgements

We use the following libraries in CodeConnect:

* [JChronic](https://mvnrepository.com/artifact/com.rubiconproject.oss/jchronic)

--------------------------------------------------------------------------------------------------------------------

## Setting up, getting started

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
<div class="page-new"></div>

## Design

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103T-T14-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/codeconnect/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/codeconnect/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delc 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/codeconnect/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `TaskListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/codeconnect/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-T14-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

<div class="page-new"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/codeconnect/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `CodeConnectParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddContactCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delc 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `CodeConnectParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddContactCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddContactCommand`) which the `CodeConnectParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddContactCommandParser`, `DeleteTaskCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/codeconnect/model/Model.java)

![Model component class diagram](images/ModelClassDiagram.png)

The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* similarly stores the task data (all `Task` objects, contained in a `UniqueTaskList` object) and the 'selected' `Task` objects as a filtered list exposed as an unmodifiable `ObservableList<Task>`.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>
<div class="page-new"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/codeconnect/storage/Storage.java)

![Storage component class diagram](images/StorageClassDiagram.png)

The `Storage` component,
* can save address book data, task list data  and user preference data in json format, and read them back into corresponding objects.
* inherits from `AddressBookStorage`, `TaskListStorage` and `UserPrefStorage`, which means it can be treated as any one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.codeconnect.commons` package.

--------------------------------------------------------------------------------------------------------------------
<div class="page-new"></div>

## Implementation

This section describes some noteworthy details on how certain features are implemented.

### Finding Feature

#### About

CodeConnect has features that allow you to search for tasks and contacts. The finding features use the following commands:

* `find n/` - Finds tasks in the TaskList by their description(name)
* `find m/` - Finds tasks in the TaskList by their module
* `findc n/` - Finds contacts in the AddressBook by their name
* `findc m/` - Finds contacts in the AddressBook by their module
* `findc ts/` - Finds contacts in the AddressBook by the module of the task at the given index

Examples of command use:
- `find n/ quiz` - Find all tasks containing the word "quiz"
- `find m/ CS1101S` - Find all tasks belonging to CS1101S
- `findc n/ Tan` - Find all contacts with names containing "Tan"
- `findc m/ CS1101S` - Find all contacts taking CS1101S
- `findc ts/ 3` - Find all contacts taking the module of the task at index 3

![Sample result of a find command](images/findTask.png)

#### Implementation Flow

Only the execution of `findc` command will be shown below as all the commands are implemented in a similar way.

Outline of how components work together when the user enters a find command:

1. User enters `findc m/ CS1101S` into the command prompt box
2. The user input will be sent to `CodeConnectParser`
3. `CodeConnectParser` send the argument `m/CS1101S` to `FindContactCommandParser`
4. `FindContactCommandParser` determines the user's input to be valid
5. `FindContactCommandParser` creates a `ModuleTakenPredicate`
   - This `Predicate` is used by the `Model` to filter for contacts that take the queried module
6. A `FindContactCommand` command is created and executed by the `Model`
7. The result of the find command is displayed to the user

![Activity diagram for execution of a findc command](images/FindContactActivityDiagram.png)
<div style="text-align: center">Activity diagram of findc command execution</div>

![Interactions Inside the Logic Component for the `find n/ Lab 2` Command](images/FindTasksSequenceDiagram.png)
<div style="text-align: center">Sequence diagram of find command execution</div>
<p></p>
<div style="text-align: center">Note: The implementation flow and the activity and sequence diagrams are similar for both the find and findc commands</div>

#### Design Considerations

One design consideration was if the user should be allowed to find contacts matching more than one module. For example, the input `findc m/ CS1101S MA1521` will return all contacts taking CS1101S, MA1521, or both. The reason why we decided to use additive search condition is as follows:

Consider the following situation:
- You have two assignments due tomorrow, from two different modules: MOD_X and MOD_Y.
- Feeling stuck, you decide to use CodeConnect to search for help, to see if there's anybody you might have forgot.
- You enter the command `findc m/ MOD_X MOD_Y`

As a user in this situation, the last thing you would want is for the app to _exclude_ contacts taking both MOD_X and MOD_Y. Those would be the first people you want to ask for help!

Another design consideration was to make both the find commands for task and contacts easy to use and remember. For example, both `find` and `findc` uses the `n/` and `m/` prefixes, when searching by names and modules respectively.

This was done so that it would be easy for the user to remember what command to use when finding either contacts or tasks.
- The only difference when finding contacts is that there is a c after the find for contacts
- Both use the same prefixes

### Adding features

#### About

CodeConnect has features that allow you to add and track your contacts and tasks. The adding features use the following commands:

* `addc n/{name} p/{phone_number} [e/{email}] [a/{address}] [t/{tag}]... [m/{module}...] [gh/{github}] [tele/{telegram}]` - Adds a contact with the given field description
* `add {task_name} by/{deadline} [m/{module_code}]` - Adds a task with the given field description

Examples of command use:
* `add Lab2 by/2022-02-02 23:59 m/CS2030S` - Adds a task with the given field description
* `addc n/Bob Martin p/98765432 tele/bobmartin00 m/CS1101S CS1231S t/friend` - Adds a contact with the given field description

#### Implementation flow

Only the execution of `add` command will be shown below as both commands are implemented in a similar way.

Outline of how components work together when the user enters an add command:

1. User enters `add Finish Lab3 by/tomorrow m/CS2030S` into the command prompt box
2. The user input will be sent to `CodeConnectParser`
3. `CodeConnectParser` send the argument `Finish Lab3 by/tomorrow m/CS2030S` to `AddTaskCommandParser`
4. `AddTaskCommandParser` will call its `parse` method to get the details of the task
5. A new `task` instance is created with the fields which is passed into a new `AddTaskCommand` object
6. The `model` will then update the `taskList` with the new Task

![Interactions Inside the Logic Component for the `add Add error handling by/next thursday m/CS2103T` Command](images/AddTaskCommandSequenceDiagram.png)
<div style="text-align: center">Sequence diagram of add command execution</div>

#### Design Considerations

* A natural date parser is used because it gives the most flexibility possible in the type of date formats that can be entered, including relative dates ("tomorrow") and abbreviations ("2 Jan").
* Uniquely, the LocalDateTime within Deadline is expected to be formatted to and from strings by the class's users. This is because different formats and strictnesses are appropriate in different situations.
  * `ParserUtil.parseDeadline` handles user input, so it uses the NaturalDateParser.
  * The `Storage` component uses a fixed format string so that saved data can be unambiguously restored.
  * Deadline(String) uses the format used before natural date parsing to reduce changes to test code.
* Deadline has a validation function for the input to its string constructor, just like other field classes. The validation is performed by attempting to parse it and checking for errors, as there is no cheaper method in this case.
* When a date range is specified, the end of the range is used; e.g. a task that's due "tomorrow" will be due 23:59 tomorrow. This is the most common interpretation in our experience.
* `Deadline`, as a thin data class, only provides constructors from preparsed LocalDateTimes and its own serialization. Parsing user input is handled by other methods, such as `ParserUtil.parseDeadline`. This improves cohesion of the class.
* The `add` command shares the `m/` prefix for modules with the other commands.
  * The `by/` prefix is chosen for the deadline, as it is a good compromise between brevity and comprehensibility ("do this *by* a certain date").


### Editing feature

#### About

CodeConnect has features that allow you to edit tasks and contacts. The editing feature use the following commands:

* `edit {task_index} {field prefix + field description}` - Edits the task at the specified index
* `editc {contact_index} {field_prefix + field_description}` - Edits the contact at the specified index

Example of command use:
* `edit 1 m/CS1101S` - Changes the module of the task at index 1 to be "CS1101S"
* `editc 2 p/91919100` - Changes the phone number of the contact at index 2 to "91919100"

#### Implementation flow

Only the execution of `edit` command will be shown below as both commands are implemented in a similar way.

Outline of how components work together when the user enters an edit command:
1. User enters `edit 3 m/CS1101S` into the command prompt box
2. The user input will be sent to `CodeConnectParser`
3. `CodeConnectParser` send the argument `3 m/CS1101S` to `EditTaskCommandParser`
4. `EditTaskCommandParser` will call its `parse` method to get the index and create a `EditTaskDescriptor` instance that stores the edited field
5. A new `EditTaskCommand` will then be created with the parsed index and `EditTaskDescriptor` object
6. That `EditTaskCommand` object will execute and a new `Task` will be created with the new fields
7. The `model` will then be updated accordingly with the new Edited Task.


![Activity Diagram](images/EditTaskActivityDiagram.png)
<div style="text-align: center">Activity diagram of edit command execution</div>

#### Design Considerations

* Initially we felt that being able to edit more than 1 field per edit task command was not as important, as
a task object does not have that many fields to begin with. However, we felt that implementing it will still
make it a lot easier in the event that a user want to have multiple changes to a task.

### Marking features

#### About

CodeConnect has features that allow you to mark and unmark your tasks as complete and incomplete respectively. The marking features use the following commands:
* mark {task_index} - Marks task at specified index as completed
* unmark {task_index} - Unmarks task at specified index as incomplete

Examples of command use:
* `mark 1` -  Marks the task at index 1 as complete
* `unmark 1` - Unmarks the task at index 1 as incomplete

#### Implementation flow

To represent the completion status of `Task`, a new `Task` attribute `status` of type `Status` is added. The boolean field 
`isComplete` in `Status` is `true` when a task is marked as complete and `false` when a task is incomplete.

Only the execution of `mark` command will be shown below as both commands are implemented in a similar way.

Outline of how components work together when the user enters a mark command:
1. User enters `mark 1` into the command prompt box
2. User input `mark 1` is passed to `LogicManager` which then calls `LogicManager#execute()` which then calls 
`CodeConnectParser#parseCommand()`.
3. It is determined the type of command is `mark` and a new `MarkTaskCommandParser` is created and `MarkTaskCommandParser#parse`
is called, parsing and validating the index.
4. If index is valid, a new `MarkTaskCommand` is created and returned to `LogicManager`.
5. `LogicManager` calls `MarkTaskCommand#execute()`. If there exists an unmarked task at index 1, 
`MarkTaskCommand#createMarkedTask()` is called, creating a copy of the task to be marked with its `status` set to `true`.
6. `Model#setTask()` is then called to replace the task to be marked with the marked copy.

![Interactions Inside the Logic Component for the `mark 1` Command](images/MarkTaskSequenceDiagram.png)
<div style="text-align: center">Sequence diagram of mark command execution</div>

The activity diagram below summarizes the implementation of a `mark` command. 

![Activity diagram for execution of a mark command](images/MarkTaskActivityDiagram.png)
 <div style="text-align: center">Activity diagram of mark command execution</div>


#### Design considerations

* One design consideration involved marking/unmarking multiple tasks by adding a space before inputting the index of another task.
However, users could forget to input spaces when inputting indexes of multiple tasks, leading to unintended tasks being
marked/unmarked without the users even realising it. Limiting the number of tasks that can be marked/unmarked to 1 is thus 
considered to be an acceptable trade-off.

### Listing features

#### About

CodeConnect has features that allow you to list out all the contacts and tasks. The listing features use the following commands:
* `list` - Shows a list of all tasks
* `list time` - Shows a list of all tasks sorted by deadline
* `listc` - Shows a list of all contacts

#### Implementation flow

Listing of tasks is facilitated by `ModelManager`. It contains `filteredTaskList` of type `FilteredList`. 
It also contains `sortedTaskList` of type `SortedList` that wraps `filteredTaskList` in it. As any changes to `filteredTaskList`
will be reflected in the `sortedTaskList`, the `Ui` displays `sortedTaskList` in the tasklist panel. `SortedList` contains the
method SortedList#setComparator(Comparator<? super E> comparator). The above method is called in 
`ModelManager#updateSortedTaskList(Comparator<Task> comparator)` that allows for the sorting of `sortedTaskList` with a comparator.

Only the execution of `list time` command will be shown below as all listing commands are implemented in a similar way.

Outline of how components work together when the user enters a list command:
1. User enters `list time` into the command prompt box
2. User input `list time` is passed to `LogicManager` which then calls `LogicManager#execute()` which then calls
   `CodeConnectParser#parseCommand()`.
3. It is determined the type of command is `list` and a new `ListTaskCommandParser` is created and `ListTaskCommandParser#parse`
   is called. It is determined that the input is a command for listing tasks by deadline.
4. A new `ListTaskCommand` is created with `DeadlineComparator` passed as parameter. `ListTaskCommand` is returned to `LogicManager`.
5. `LogicManager` calls `ListTaskCommand#execute()`. `Model#updateSortedTaskList()` is then called, setting the comparator 
   in `sortedTaskList` to `DeadlineComparator`. `Ui` then displays the tasks ordered by task with the earliest deadline on top.

![Interactions Inside the Logic Component for the `list time` Command](images/ListTaskSequenceDiagram.png)
<div style="text-align: center">Sequence diagram of list command execution</div>

#### Design considerations

* One design consideration involved deciding how tasks are ordered under default list command `list`.
One choice was for tasks to be ordered with most recently added task on top and the other was for 
tasks to be ordered with least recently added task on top. 
* The former was chosen in the end as under a daily use basis, a tasklist is likely to evolve rather
quickly. It was considered to be more convenient for a user to be able to see more recent and relevant 
tasks at the top without having to scroll towards the bottom of the tasklist.

--------------------------------------------------------------------------------------------------------------------

## Documentation, logging, testing, configuration, dev-ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

<div class="page-new"></div>

## Appendix: Requirements

### Product scope

**Target user profile**:

* an SOC student
* needs support with his academics
* has friends that he is willing to ask for help
* misses deadlines
* cannot remember all the details of his assignments and exams
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

Search for contacts for help with a particular task faster than having to think about who is taking the same module as you.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                          | I want to …​                                                                   | So that I can …​                                                       | Implemented in Current Version? |
|----------|--------------------------------------------------|--------------------------------------------------------------------------------|------------------------------------------------------------------------|---------------------------------|
| `* * *`  | new or forgetful user                            | see usage instructions                                                         | learn how to get started and use the app, if I forget                  | yes                             |
| `* * *`  | user                                             | add a new contact                                                              | track details of people I know                                         | yes                             |
| `* * *`  | user                                             | delete a contact                                                               | remove contacts that I no longer need                                  | yes                             |
| `* *`    | impatient user                                   | delete all contacts                                                            |                                                                        | yes                             |
| `* *`    | user                                             | edit a contact                                                                 |                                                                        | yes                             |
| `* * *`  | user                                             | find persons by name                                                           | locate details of persons without having to go through the entire list | yes                             |
| `* * *`  | user                                             | find persons by module taken                                                   | get a list of people that can help me with a particular module         | yes                             |
| `* * *`  | user stuck on work                               | find persons by task that I need help with                                     | get help for a particular task quickly                                 | yes                             |
| `* * *`  | user                                             | add a new task                                                                 | record important to-do's                                               | yes                             |
| `* *`    | user                                             | be able to enter the deadline of my tasks in multiple formats                  | not need to think about the date format when manipulating tasks        | yes                             |
| `* * *`  | user                                             | delete a task                                                                  | remove contacts that are irrelavant                                    | yes                             |
| `* * *`  | user                                             | mark a task as complete                                                        |                                                                        | yes                             |
| `* * *`  | user                                             | mark a task as not complete                                                    |                                                                        | yes                             |
| `* * *`  | user with multiple deadlines                     | list tasks by deadline                                                         | prioritise what to do first                                            | yes                             |
| `* *`    | future thinking SOC student                      | prioritize my tasks                                                            | plan what I should be working on first                                 | yes                             |
| `* *`    | user                                             | edit a task                                                                    | change deadline if it is updated                                       | yes                             |
| `* * *`  | user with many tasks                             | find a task by name                                                            | locate details of tasks without having to go through the entire list   | yes                             |
| `* * *`  | user with many tasks                             | find a task by module it belongs to                                            | know what tasks I must do for a particular module                      | yes                             |
| `* *`    | impatient user                                   | delete all completed tasks at once                                             | conveniently clean my task list                                        | yes                             |
| `* *`    | SOC student working on a group project           | see all the contacts of those people in my group project                       | easily contact them                                                    | no                              |
| `* *`    | user                                             | hide private contact details                                                   | minimize chance of someone else seeing them by accident                | no                              |
| `*`      | future thinking SOC CS Student                   | list tasks and events for the next 7 days                                      | plan what I want to do better                                          | no                              |
| `*`      | SOC student with many digital files to organize  | link a task to relevant local files (pdf, pptx, etc.)                          | open them quickly                                                      | no                              |
| `*`      | overwhelmed SOC student                          | filter tasks by whether or not they are graded                                 | decide on what to do first                                             | no                              |
| `*`      | SOC student                                      | assign an estimated time to complete for each task                             | realistically estimate how much I can accomplish in a day              | no                              |
| `*`      | forgetful SOC student                            | be greeted (or warned) with a list of urgent/overdue tasks when I open the app | remind myself about them                                               | no                              |
| `*`      | SOC student who has many venues to keep track of | store the venues associated with my tasks                                      |                                                                        | no                              |
| `*`      | user with many persons in the address book       | sort persons by name                                                           | locate a person easily                                                 | no                              |

<div class="page-new"></div>

### Use cases

(For all use cases below, the **System** is the `CodeConnect` and the **Actor** is the `user`, unless specified otherwise)

**Use case 1: Finding contacts by module**

**MSS**

1. User requests to find persons taking a specific module.
2. CodeConnect requests for module code.
3. User types in module code.
4. CodeConnect shows a list of persons taking that module.

   Use case ends. 

**Extensions**

* 3a. The list is empty.

  Use case ends.

* 3b. The given module code is invalid.

    * 3a1. CodeConnect shows an error message.

      Use case resumes at step 3.

****

**Use case 2: Mark a task as complete**

**MSS**

1.  User requests to list tasks
2.  CodeConnect shows a list of tasks
3.  User requests to mark a specific task in the list as complete
4.  CodeConnect marks the task as complete

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. CodeConnect shows an error message.

      Use case resumes at step 2.

****

**Use case 3: Edit a contact**

**MSS**

1.  User requests to list contacts
2.  CodeConnect shows a list of contacts
3.  User inputs index of contact and its updated information
4.  CodeConnect updates the contact according to the input given

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. CodeConnect shows an error message.

      Use case resumes at step 2.

****

**Use case 4: Delete a contact**

**MSS**

1.  User requests to find persons taking a specific module.
2.  CodeConnect requests for module code.
3.  User types in module code.
4.  CodeConnect shows a list of persons taking that module.

    Use case ends.

**Extensions**

* 3a. The list is empty.

  Use case ends.

* 3b. The given module code is invalid.

    * 3a1. CodeConnect shows an error message.

      Use case resumes at step 3.

****

**Use case 5: Add a task**

**MSS**

1. User starts CodeConnect.
2. User requests to add a task through appropriate command.
3. CodeConnect adds the task and shows the updated task list.

   Use case ends.

**Extensions**

* 2a. User uses wrong syntax/format in command.

    * 2a1. CodeConnect shows an error message with the correct format style.
      
      Use case resumes at step 2.

****

**Use case 6: List tasks**

**MSS**

1. User starts CodeConnect.
2. User requests to see current tasks.
3. CodeConnect displays the task list.

   Use case ends.

**Extensions**

* 2a. User uses wrong syntax in command.

    * 2a1. CodeConnect shows an error message.

      Use case resumes at step 2.

****

**Use case 7: List tasks in the order of the earliest deadline**

**MSS**

1. User starts CodeConnect.
2. User requests to see current tasks, where tasks with the earlier datelines appear at the top.
3. CodeConnect displays the updated sorted task list.

   Use case ends.

**Extensions**

* 2a. User uses wrong syntax in command.

    * 2a1. CodeConnect shows an error message.

      Use case resumes at step 2.

****

**Use case 8: Delete tasks that are completed**

**Preconditions**
- User is currently using CodeConnect.
- User has tasks that are done in the task list.

**MSS**

1. User starts CodeConnect.
2. User requests to delete completed tasks.
3. CodeConnect displays the updated task list with only uncompleted tasks left.

   Use case ends.

**Extensions**

* 2a. User uses wrong syntax in command.

    * 2a1. CodeConnect shows an error message.

      Use case resumes at step 2.

****

**Use case 9: Find contacts that take the module of the first task in the current task list**

**MSS**

1. User starts CodeConnect.
2. User requests to find contacts who can help with the current task at the top of the task list.
3. CodeConnect displays the contacts taking the module of the task at the top of the task list.

   Use case ends.

**Extensions**

* 2a. User uses wrong syntax in command.

    * 2a1. CodeConnect shows an error message.

      Use case resumes at step 2.

* 2b. No contacts taking that particular module.

    * 2b1. CodeConnect shows an empty contact list.

      Use case ends.

****

**Use case 10: Clearing contacts**

**Preconditions**
- User is currently using CodeConnect.

**MSS**

1. User enters a command to clear all contacts from CodeConnect.
2. CodeConnect sends a message that all contacts are deleted and saves all changes to disk.

   Use case ends.

****
<div class="page-new"></div>

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons and tasks without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Command should execute in less than a second.
5. Product features must work without internet.
6. Should be able to package into a single JAR file.
7. JAR file should be at most 100mb and documentation should be at most 15mb per file.
8. Documentation should include appropriate UML diagrams.
9. Should be for a single user.
10. Should be usable without initialising a user account.
11. No input should terminate CodeConnect, except the `exit` command.

--------------------------------------------------------------------------------------------------------------------

### Glossary

* **GUI**: Graphical User Interface
* **CLI**: Command Line Interface - Where you can execute commands by typing them
* **JSON**: A file format that uses human-readable text to store and transmit data objects consisting of attribute–value pairs and arrays
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------
<div class="page-new"></div>

## Appendix: Instructions for manual testing

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file

      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.

      Expected: The most recent window size and location is retained.

1. Exiting the program

    1. Enter the `exit` command.

       Expected: The program exits.

    1. Reopen the program. Click the close button on the top right of the window.

       Expected: Similar to previous.

    1. Reopen the program. Select the File → Exit menu entry.

       Expected: Similar to previous.

   *All three should also work when the GUI is showing an invalid command.*

### GUI functionality

1. Using menu items

    1. Select the Help → Help menu entry.

       Expected: A window showing a clickable link to the [User Guide](UserGuide.html) is shown.

       *Note: This window is known to open with zero dimensions on Linux X11.*

    1. Close the help window. Press `F1`.

       Expected: Similar to previous.

1. Switching tabs

    1. Prerequisites: The Contacts tab is selected.

    1. Click on the Tasks tab.

       Expected: The Tasks tab is highlighted. A list of tasks is shown. The data file path at the bottom changes to the task list file (`/data/tasks.json` by default).

    1. Click on the Contacts tab.

       Expected: The Contacts tab is highlighted. A list of contacts is shown. The data file path at the bottom changes to the contact list file (`/data/contacts.json` by default).

### Managing tasks

1. Adding a task

    1. Prerequisites: A task with the same name and module as below does not already exist. (It does not exist in the sample data)

    1. Test case: `add Programming assignment m/CS1234 by/2022-01-02 3:45pm`

       Expected: The app switches to the Task tab. A new task is shown in the GUI and its details are shown in the result box.

    1. Test case: `add Get groceries by/tomorrow`

       Expected: Similar to previous.

    1. Test case: `add Math assignment m/MA4321`

       Expected: The command turns red and the result box shows an error message. No tasks are added.

    1. Test case: `add Math assignment m/MA4321/R by/2022-01-02 3:45pm`

       Expected: Similar to previous.

    1. Test case: `add Math assignment m/ by/2022-01-02 3:45pm`

       Expected: Similar to previous.

    1. Test case: `add Programming assignment m/CS1234 by/2022-01-02 4pm`

       Expected: Similar to previous.

1. Listing tasks

    1. Run `add Math assignment m/MA4321 by/2022-01-02 2pm`, so that a sort order can be verified.

    1. Test case: `list`

       Expected: The app switches to the Task tab. Tasks are listed in order of most recently added first. The result box displays the matching message.

    1. Test case: `list time`

       Expected: The app switches to the Task tab. Tasks are listed in order of increasing due date. The result box displays the matching message.

    1. Test case: `list foo`

       Expected: The app switches to the Task tab. Tasks are listed in order of most recently added first. The result box displays the matching message.

1. Finding tasks

    1. Prerequisites: A task with "ign" in its name exists (if you're following this guide, there will be one)

    1. Test case: `find n/ign`

       Expected: The app switches to the Task tab. Matching tasks are shown. The result box displays the matching message.

    1. Test case: `find n/ign m/CS`

       Expected: The command turns red and the result box shows an error message. The tasks shown in the tasks tab do not change.

    1. Test case: `find`

       Expected: Similar to previous.

    1. Test case: `find n/asdfghjk`

       Expected: The app switches to the Task tab. No tasks are shown. The result box displays a message about listing tasks.

1. Marking and unmarking tasks

    1. Prerequisites: There are at least two tasks. (if you're following this guide, there will be)

    1. Run `list time` to change the sort order.

    1. Test case: `mark 2`

       Expected: The app switches to the Task tab. The list is still sorted by increasing deadline. The 2nd task in the list is marked. The result box displays the matching message.

    1. Test case: `mark 2`

       Expected: The command turns red and the result box shows an error message. The tasks shown in the tasks tab do not change.

    1. Test case: `mark -1`

       Expected: Similar to previous.

    1. Test case: `mark`

       Expected: Similar to previous.

    1. Repeat the steps above for the `unmark` command.

1. Editing tasks

    1. Prerequisites: A task with the name "Get groceries" exists. (if you're following this guide, there will be one)

    1. Run `find n/Get groceries`.

    1. Test case: `edit 1 m/CS1234`

       Expected: The app switches to the Task tab. The 1st task in the list has its module set to `CS1234`. The result box displays a message about the task being edited.

    1. Test case: `edit 1 m/CS1234`

       Expected: The app switches to the Task tab. The 1st task in the list does not change. The result box displays a message about the task being edited.

    1. Test case: `edit 1 n/Programming assignment`

       Expected: The command turns red and the result box shows an error message. The tasks shown in the tasks tab do not change.

    1. Test case: `edit n/Programming assignment`

       Expected: Similar to previous.

    1. Test case: `edit 1`

       Expected: Similar to previous.

1. Deleting tasks

    1. Run `list time` to change the sort order.

    1. Test case: `del -1`

       Expected: The command turns red and the result box shows an error message. The tasks shown in the tasks tab do not change.

    1. Test case: `del`

       Expected: Similar to previous.

    1. Test case: `del 1 n/Hello`

       Expected: Similar to previous.

    1. Test case: `del 1`

       Expected: The app switches to the Task tab. The list is still sorted by increasing deadline. The 1st task in the list is deleted. The result box displays the matching message.

1. Clearing completed tasks

    1. Prerequisites: At least one, but not all, tasks are marked. (if you're following this guide, `mark 2`)

    1. Test case: `clean now`

       Expected: The app switches to the Task tab. The list is still sorted and filtered like before the command. All marked tasks and none of the unmarked tasks are deleted. The result box displays the matching message.

### Managing contacts

1. Adding a contact

    1. Prerequisites: A contact with the same name as below does not already exist. (It does not exist in the sample data)

    1. Test case: `addc n/John Doe p/98765432 m/CS4321`

       Expected: The app is on the Contacts tab. A new contact is shown in the GUI and its details are shown in the result box.

    1. Test case: `addc n/James Doe p/99887766 e/jamesdoe@example.com`

       Expected: Similar to previous.

    1. Test case: `addc n/David`

       Expected: The app is on the Contacts tab. The command turns red and the result box shows an error message. No contacts are added.

    1. Test case: `addc n/David p/88776655 e/`

       Expected: Similar to previous.


2. Listing contacts

    1. Test case: `listc`

       Expected: The app is on the Contacts tab. Tasks are listed in order of least recently added first. The result box displays the matching message.

    1. Test case: `listc foo`

       Expected: Similar to previous.

3. Finding contacts

    1. Prerequisites: A contact with "do" in its name exists (if you're following this guide, there will be one)

    1. Test case: `findc n/do`

       Expected: The app is on the Contacts tab. Matching contacts are shown. The result box displays the matching message.

    1. Test case: `findc n/do m/CS`

       Expected: The app is on the Contacts tab. The command turns red and the result box shows an error message. The contacts shown in the contacts tab do not change.

    1. Test case: `findc`

       Expected: Similar to previous.

    1. Test case: `findc n/asdfghjk`

       Expected: No contacts are shown. The result box displays a message about listing contacts.

4. Editing contacts

    1. Prerequisites: Contacts with the name "John Doe" and "James Doe" exist. (if you're following this guide, there will be one)

    1. Run `findc n/John Doe`

    1. Test case: `editc 1 m/CS1234`

       Expected: The app is on the Contacts tab. The 1st contact in the list has its module set to `CS1234`. The result box displays a message about the contact being edited.

    1. Test case: `editc 1 m/CS1234`

       Expected: The app switches to the Task tab. The 1st contact in the list does not change. The result box displays a message about the contact being edited.

    1. Test case: `editc 1 n/James Doe`

       Expected: The command turns red and the result box shows an error message. The contacts shown in the contacts tab do not change.

    1. Test case: `editc n/James Doe`

       Expected: Similar to previous.

    1. Test case: `editc 1`

       Expected: Similar to previous.

5. Deleting contacts

    1. Test case: `delc -1`

       Expected: The app is on the Contacts tab. The command turns red and the result box shows an error message. The contacts shown in the contacts tab do not change.

    1. Test case: `delc`

       Expected: Similar to previous.

    1. Test case: `delc 1 n/Hello`

       Expected: Similar to previous.

    1. Test case: `delc 1`

       Expected: The app is on the Contacts tab. The 1st contact in the list is deleted. The result box displays the matching message.

6. Clearing all contacts

    1. Prerequisites: Contacts tab has at least one contact.

    2. Test case: `clear`

       Expected: All contacts are deleted from the contact list.

7. Saveme

    1. Prerequisites: A task with the same name and module as below does not already exist. (It does not exist in the sample data) A contact with the name "John Doe" exists. (if you're following this guide, there will be one)

    2. Run `add Assignment 1 m/CS4321 by/tomorrow 23:59`

    3. Test case: `saveme`

       Expected: The app is on the Contacts tab. The 1st contact in the list has a module tag `CS4321`.

    4. Test case: `saveme foo`

       Expected: Similar to previous.

### Saving data

1. Dealing with missing/corrupted data files

   * Missing data file:
   
    1. Prerequisite: Missing `data/contacts.json` and/or `data/tasks.json
   
    2. Test case: Delete `data/contacts.json` and relaunch CodeConnect.
   
       Expected: CodeConnect will launch with some sample contacts.
   
    3. Test case: Delete `data/tasks.json` and relaunch CodeConnect.
   
       Expected: CodeConnect will launch with some sample tasks.
   
    4. Test case: Delete both `data/contatcs.json` and `data/tasks.json`. Relaunch CodeConnect.
   
       Expected: CodeConnect will launch with some sample contacts and tasks.

    * Corrupted data file:
   
    1. Prerequisite: Corrupted `data/contacts.json` and/or `data/tasks.json`
   
    2. Test case 1: Modify any contact's email to an invalid email in `data/contacts.json` and relaunch CodeConnect.
   
       Expected: CodeConnect will launch with no contacts.
   
    3. Test case 2: Modify any task's deadline to an invalid format (not in YYYY-MM-DD HH:MM format) in `data/tasks.json` 
       and relaunch CodeConnect.
   
       Expected: CodeConnect will launch with some sample tasks.
   
    4. Test case: Perform both the modifications in the above test cases and relaunch CodeConnect.
   
       Expected: CodeConnect will launch with some sample contacts and tasks.

2. Saving of data after running a valid command that modified it

    1. Test case: Run any command that modifies data such as `add`, `addc`, `edit`. Exit CodeConnect and relaunch it.

       Expected: Any modification made to the data is preserved.
