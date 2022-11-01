---
layout: page
title: Developer Guide
---
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* Libraries used: [JavaFX](https://openjfx.io), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5). <br>
* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).


--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103T-T10-1/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-T10-1/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-T10-1/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each 
other for the scenario where the user issues the command `delete-person 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-T10-1/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-T10-1/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-T10-1/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-T10-1/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` 
component for the `execute("delete-person 1")` API call.

![Interactions Inside the Logic Component for the `delete-person 1` Command](images/DeletePersonSimplifiedSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** 
The lifeline for `DeletePersonCommandParser`  and `DeletePersonCommand` should 
end at the destroy marker (X) but due to a limitation of PlantUML, the 
lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPersonCommandParser`, `DeletePersonCommandParser`, ...) inherit from the `Parser` interface so that   they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-T10-1/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e.:
  * all `Person` objects (which are contained in a `UniquePersonList` object).
  * all `Module` objects (which are contained in a `UniqueModuleList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the currently 'selected' `Module` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Module>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` 
  represents data entities of the domain, they should make sense on their own without depending on other components).

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-T10-1/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

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

### Add link feature

The 'add link' feature allows for the user to add links with corresponding aliases to a `Module` in Plannit.
Links in Plannit are represented by the `Link` class. A `Link` object contains two required fields,
a `String` URL and a `String` alias. For each Plannit `Module`, `Link` objects are stored in a `TreeSet`.

The implementation of the 'delete link' feature is highly similar to this, but without a `String` URL input.

#### Implementation

The add link mechanism is facilitated by `AddLinkCommandParser` and `AddLinkCommand`.
`AddLinkCommandParser` implements the `Parser` interface to validate the `Module` and
pair link URLs with their aliases from the user input.
It then creates a `AddLinkCommand` object with the pairings for the specified `Module`.
`AddLinkCommand` extends the `Command` class to add `Link` objects into a `Module` in Plannit.

Given below is an example usage scenario and how the add link mechanism behaves at each step.

**Step 1**: The user decides to add a link to a current module in Plannit using the following input:
`add-link m/CS1231 l/<link URL> la/<link alias>`.

**Step 2**: The `LogicManager` calls the `LogicManager::execute` method on the user input.
Then, the `LogicManager` calls the `AddressBookParser::parseCommand` method
with the user input `String` to create a `Command` object.

**Step 3**: The `AddressBookParser` finds the command keyword `add-link` in the user input.
Thus, a new `AddLinkCommandParser` object is instantiated to parse the arguments from the user input
to create a new `AddLinkCommand` object.

**Step 4**: Within the new `AddLinkCommandParser` object, the `parse` method is used on the arguments to validate
its module code, link URL, and alias. Also, it forms a new `Link` object with its link URL and alias.
A new `AddLinkCommand` is created with the module code and `Link` object, which is returned to `LogicManager`.

**Step 5**: The `AddLinkCommand::execute` method is then called by the `LogicManager`.
This method will first obtain the `Module` object with the module code indicated by the user.
A copy of the `Module`'s fields is then created and the `Link` object is added to the copied `TreeSet` of links.

**Step 6**: A new `Module` is created with the modified and copied fields, which replaces
the original `Module` object in Plannit using the `Model::setModule` method.

The following sequence diagram shows how the 'add link' feature works:
![AddLinkSequenceDiagram](images/AddLinkFeature/AddLinkSequenceDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: 
**Note:** The lifeline for `AddLinkCommand` and `AddLinkCommandParser` should end at the destroy marker (X) 
but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

The following activity diagram shows how the 'add link' feature works:
![AddLinkActivityDiagram](images/AddLinkFeature/AddLinkActivityDiagram.png)

#### Design considerations:
**Aspect: Link Representation:**
* **Alternative 1 (current choice)**: Link objects require alias and URL.
    * Pros:
        * Fast identification, access, and deletion of links from the user's perspective.
        * Improved sorted display of links (shorter and standardised format).
    * Cons:
        * Longer time for the user to type input.
* **Alternative 2**: Link objects require URL only and its alias is an optional input.
    * Pros:
        * Greater flexibility for different user preferences.
    * Cons:
        * Inconsistent link display format
        * Slow identification, access, and deletion of links from the user's perspective.
* **Alternative 3**: Link objects require URL only and its alias is an optional input with a default alias.
    * Pros:
        * Greater flexibility for different user preferences.
    * Cons:
        * Default alias may be confusing and/or undesirable.
        * Slow identification, access, and deletion of links from the user's perspective.
* **Alternative 4**: Link objects require URL only with no alias.
    * Pros:
        * Easy to implement.
    * Cons:
        * Cluttered and unorganised display of links.
        * Slow identification, access, and deletion of links from the user's perspective.

**Aspect: Link Storage:**
* **Alternative 1 (current choice)**: Link objects stored in a `TreeSet` object within `Module`.
    * Pros:
        * Consistent display order of links in Plannit.
    * Cons:
        * Harder to implement.
* **Alternative 2**: Link objects stored in a `ArrayList` object within `Module`.
    * Pros:
        * Easy to implement.
    * Cons:
        * No standardised display order of links across `Module` objects.
* **Alternative 3**: Link objects stored in a `HashSet` object within `Module`.
    * Pros:
        * Fast performance when accessing and deleting links in terms of time.
    * Cons:
        * Display order of links changes after each modification to the `HashSet` of links for a `Module`.

Rationale behind current choice:

Link Representation: Each link requires an alias and URL such that users are able to make sense of
each URL present in Plannit. By enforcing the user to select their own alias,
they are more likely to remember what each alias means in the long run.
Aliases are also especially useful in differentiating between similar named
URLs, i.e., same domain but the remainder of the url has no semantic meaning to the user (e.g. zoom links).

Link Storage: 
TreeSet was selected as to ensure a consistent sorting order across all modules such that users
will spend the least amount of the time searching for their required alias for each module,
thus boosting their productivity.

### Person component

#### General design
Every contact added into Plannit is represented as a `Person` object.
Every `Person` object has three compulsory attributes:
- `Name`
- `Email`
- `Phone`

The UML class diagram of the `Person`-related parts of the Model component is shown below:

![ModelPersonClassDiagram](images/ModelPersonClassDiagram.png)

With reference to the diagram above, here are the ways in which different classes in the Model component interact with the `Person` class:
- A `UniquePersonList` object holds all the `Person` objects in Plannit.
  - This `UniquePersonList` object is stored in the `AddressBook` class.
- The `ModelManager` class stores a filtered list of `Person` objects, which is the list used to determine which
`Person` objects to display on the GUI.

#### Delete Person Feature
One feature related to contacts is the delete person feature, where a contact is deleted from Plannit. This 
particular feature is highlighted because its implementation involves additional interactions with the `Module` 
component.

#### Implementation of delete person feature
The delete person mechanism is facilitated by `DeletePersonCommand` and `DeletePersonCommandParser`.
`DeletePersonCommandParser` parses the user input in a meaningful way and uses it to create a `DeletePersonCommand`.
`DeletePersonCommand` then calls the `Model#deletePerson()` operation, which in turn calls
`AddressBook#removePerson()` to delete a contact in Plannit.

Given below is an example usage scenario and how the delete person mechanism behaves at each step.

**Step 1**. There currently exists 3 persons in Plannit, with 2 of them already 
added to a module.

![DeletePersonStep1ObjectDiagram](images/DeletePersonStep1ObjectDiagram.png)

**Step 2**. The user executes `delete-person 2` command and this creates a `deletePersonCommand` to delete the 2nd person 
(that is currently displayed on the GUI) in the address book. Upon execution, `UniqueModuleList#removePersonFromModules
()` and `UniquePersonList#remove()` are eventually called. `UniqueModuleList#removePersonFromModules()` removes all 
occurrences of the person in every module. 

![DeletePersonStep2ObjectDiagram](images/DeletePersonStep2ObjectDiagram.png)

**Step 3**. `UniquePersonList#remove()` removes the person from `UniquePersonList`.
Afterwards, there would no longer be any references to the deleted `Person` object, and Java will eventually remove it
from memory.

![DeletePersonStep3ObjectDiagram](images/DeletePersonStep3ObjectDiagram.png)

The following sequence diagram shows how the delete person operation works.

![DeletePersonSequenceDiagram](images/DeletePersonSequenceDiagram.png)

### Add/Delete Module

#### Implementation

A `Module` class is used to represent a module. A `Module` contains a `ModuleCode` and an 
optional `ModuleTitle`. Here are the ways in which different classes in the `Model` component interact
with `Module`:
- A `UniqueModuleList` represents the list of `Module` objects.
- The list of modules is stored in the `AddressBook` class.
- The `ModelManager` class stores the `AddressBook` containing the list of unfiltered `Module` 
  objects, and a separate instance variable storing the list of filtered `Module` objects.

The UML class diagram of the `Module`-related parts of `Model` component is shown below:

![ModelModuleClassDiagram](images/ModelModuleClassDiagram.png)

We have implemented the following `Command` classes:
- `AddModuleCommand` allows the user to add a module to Plannit.
- `DeleteModuleCommand` allows the user to delete a module from Plannit.

The Storage component has been updated for persistent storage of modules. `JsonAdaptedModule` 
has been added to represent a `Module` object in JSON format.

Below shows a description of an example scenario for adding a command. Deletion of a command is 
similar except that the corresponding deletion class is used instead. 

**Step 1**: User enters command `add-module m/CS2105` to add module CS2105 to Plannit.

**Step 2**: `addressBookParser`, the parser for Plannit, will parse the user command to return an 
`AddModuleCommand` object.

**Step 3**: The resulting `AddModuleCommand` object is then executed. The validity of the input 
module code provided is checked. This involves a check of whether the input is a duplicate module 
(case-insensitive, removing leading and trailing whitespaces). 

**Step 4**: After successful checks, the module `CS2105` will be added into Plannit. 

**Step 5**: The `saveAddressBook()` method of `StorageManager` is called to save the newly-updated list
of modules to a JSON file. 

**Step 6**: `JsonAddressBookStorage` is called, which serializes/converts the new list of modules 
into JSON format, so that it can be saved into a file. The file will be read whenever Plannit 
starts up so that it can load saved module and person data.

**Step 7**: Plannit Graphical User Interface (GUI) displays message that the addition of module has 
been successful. 

The following activity diagram summarizes what happens when the user requests to add module to 
Plannit via `add-module` command.

![AddModuleActivityDiagram](images/AddModuleActivityDiagram.png)

#### Alternatives Considered

Aspect: The class-level design of how to integrate the `Module` class to the `Model` component.

* **Alternative 1 (current choice):** Reuse the same `AddressBook` to also store the list of 
  unique `Module` objects.

    * Pros: A duplicate class `AddressBook` for `Model` methods is not necessary.

    * Cons: `AddressBook`, `ModelManager` and other classes would need to be updated to include 
`Module`. Hence, the interfaces `ReadOnlyAddressBook` and `Model` also need to be updated to support
the `Module`-related methods.

* **Alternative 2:** Create another `AddressBook`-like class to store the list of `Module` objects.

    * Pros: Functionality for `Person` and `Module` are now separate. This better adheres to the 
Single Responsibility Principle because `AddressBook` only does operations regarding `Person` 
rather than doing operations regarding both `Person` and `Module`.

    * Cons: More classes to implement. In particular, `AddressBook` and `ReadOnlyAddressBook` 
need to be duplicated into separate classes to support `Module`.

Rationale behind current choice: 
1. Duplicating `AddressBook` will result in duplicating dependent classes such as 
`ReadOnlyAddressBook`, `JsonAddressBookStorage` and `JsonSerializableAddressBook`, hence complicating
the implementation. For example, the storage component will now need to deal with two `AddressBook` 
instances, hence requiring either two separate storage files, or changing the implementation 
to combine the contents of the two different `AddressBook`-like classes into one file. Combining 
`Person` and `Module` into one `AddressBook` would avoid this issue.
2. While dealing with lists of `Person` and `Module` objects are two different functionalities, 
both functionalities deal with a list of user-provided objects. Therefore, the cohesion should not 
significantly decrease.

### \[Coming soon\] Add person to module

A person can take several modules. The relationship between `Person` and `Model` is displayed 
in the Object Oriented Domain Model diagram below:

![ModulePersonObjectOrientedDomainModel](images/ModulePersonObjectOrientedDomainModel.png)

### Task component

#### Implementation

A `Task` class is used to represent a task that is to be completed by the 
user. Tasks are assigned to a specific module. The following `Command` 
classes were implemented to allow users to interact with `Task` objects:
- `AddTaskCommand` allows the user to add a task to a specified `Module` in
  Plannit.
- `DeleteTaskCommand` allows the user to delete a task from a specified
  `Module` in Plannit.
- `SwapTaskCommand` allows the user to swap the order of `Task`s within a
  specified `Module` in Plannit.

Each `Module` within the `UniqueModuleList` stores a `TaskList` 
instance, which in turn stores a list of `Task` objects. This `TaskList` 
object is used to maintain the list of `Task` objects within the module by 
supporting operations to add, delete and swap tasks.

![TaskListClassDiagram](images/AddDeleteSwapTaskFeature/TaskListClassDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: 
**Note:**  All `Module` objects are instantiated with an empty `TaskList`.
</div>

Similar to `UniqueModuleList` and `UniquePersonList`, a `TaskList` contains the following:
* `internaList` - An `ObservableList<>` containing the tasks
  of a module. This list is used for the adding and removing of objects in
  the data structure.
* `internaUnmodifiableList` - An `ObservableList<>` that is
  an **unmodifiable** copy of `internalList`. This list is used when external
  objects requests a copy of the current list of objects.

However, there exists two key differences:
1. `TaskList` is used to store `Task` objects as opposed to the storage of
   `Module` in `UniqueModuleList` and `Person` in `UniquePersonList` 
   respectively.
2. A different `TaskList` instance exists in each `Module` object, meaning
   multiple instances of the `TaskList` object can exist within an
   `AddressBook` instance. On the other hand, there will always be only one
   `UniqueModuleList` instance and one `UniquePersonList` instance in the
   `AddressBook` instance.

Here's a (partial) object diagram of an `AddressBook` instance to 
demonstrate the above point:
![AddressBookObjectDiagram](images/AddDeleteSwapTaskFeature/AddressBookObjectDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: **Note:** 
There is only one instance of `UniquePersonList` (in purple) and 
`UniqueModuleList` (in blue). However, there are two instances of `TaskList`
(in orange), corresponding to the number of modules.
</div>

Given below is an example usage scenario and how the mechanism
behaves when a user adds a new `Task` with the `add-task` command. The behavior 
for the deleting and swapping of tasks is highly similar.

**Step 1**. The user requests to add a task into a module present in Plannit by
inputting the `add-task` command followed by the `m/` flag to indicate the
module code argument and the `td/` flag indicate the task description argument.
<br>
E.g.:
```
add-task m/CS1231 td/Submit the weekly assignment
```

**Step 2**: The `LogicManager` uses the `AddressBookParser`  and 
`AddTaskCommandParser` to parse the user input. After validating the
arguments provided by the user, the `ModuleCode` of the module to add the
task to and the task description of the new task is extracted by the `AddTaskCommandParser`.

**Step 3**: An `AddTaskToModuleDescriptor` object is then instantiated to
contain the extracted `ModuleCode` and a new `Task` with the extracted task 
description.

**Step 4**: This `AddTaskToModuleDescriptor` is used to instantiate an
`AddTaskCommand` object that is returned to the `LogicManager`.

**Step 5**: The `AddTaskCommand::execute` method is then called by the
`LogicManager`. This method will first obtain the `Module` with the
`ModuleCode` indicated by the user by calling `Model::getModule`. A copy of
the `Module`'s fields are then created.

**Step 6**: A new `Task` is then added to the copied `TaskList` field.

**Step 7**: A new `Module` is then created using the copied fields along
with the updated `TaskList` field.

**Step 8**: The `Module` currently existing in the `Model` is then
replaced with this new updated `Module` using the `Model::setModule` method.

The following sequence diagram summarizes what happens when a user executes
the `add-task` command:

![AddTaskSequenceDiagram](images/AddDeleteSwapTaskFeature/AddTaskSequenceDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: 
**Note:** The lifeline for `AddTaskCommand`,  and `AddTaskCommandParser` should 
end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Design considerations:
**Aspect: Data structure to store `Task`:**

* **Alternative 1 (current choice): Define a new `TaskList` class**
    * Pros: Methods handling the adding, deleting and maintaining of `Task`s
      can be abstracted away into the `TaskList`.
    * Cons: Additional complexities brought about by implementing a new class.

* **Alternative 2:** Store `Task` as an `ArrayList` field in `Module`.
    * Pros: `Module` can directly handle the adding, deleting and
      maintaining of its `Task`s.
    * Cons: Poor adherence to [SOLID](https://nus-cs2103-ay2223s1.github.io/website/se-book-adapted/chapters/principles.html#solid-principles)
      design principles.

Rationale behind current choice:
1. Despite the additional complexities, it is good practice to adhere with the 
SOLID design principles. 
2. Furthermore, the additional complexities and potential for new bugs can
   be mitigated by robust unit and integration testing.

**Aspect: Manner of handing arguments to `AddTaskCommand` constructor:**

* **Alternative 1 (current choice):** Pass arguments as a single
  `AddTaskToModuleDescriptor` object.
    * Pros: Neater to store all arguments in a single data structure.
      Will be highly beneficial when more fields are added to a `Task`
      in the future.
    * Cons: Additional complexities brought about by implementing a new class.

* **Alternative 2:** Pass arguments directly into the `AddTaskCommand`
  constructor
    * Pros: Simpler to implement.
    * Cons: Number of arguments taken in by the constructor will
      increase when more fields are added to a `Task` in the future.

Rationale behind current choice:

1. Despite the additional complexities, a `Task` object is likely to have 
additional fields (e.g. `Date`, `Priority`) in future iterations. Should we 
pass fields directly in to the constructor as arguments, the number of 
parameters in the constructor would increase. This would greatly affect the 
readability of the code. 
2. The additional complexities and potential for new bugs can be mitigated 
   by robust unit and integration testing.

### Goto module feature

#### Implementation

Goto module mechanism is facilitated by the `GoToCommand` and `GoToCommandParser`.

It allows users to navigate to a specific module given their respective module code, displaying information
(i.e. tasks, links and contacts) that are associated to that module.<br>

It uses the following methods provided by `ModelManager` which implements the `Model` interface.
* `ModelManager::getModuleUsingModuleCode`: Retrieves a `Module` object using the `ModuleCode` object associated wih that `Module`
* `ModelManager::updateFilteredModuleList`: Update the current module list and filter it according to the given predicate `Predicate<Module> predicate`, reflecting the changes accordingly in the GUI
* `ModelManager::updateFilteredPersonList`: Update the current person list and filter it according to the given predicate `Predicate<Person> predicate`, reflecting the changes accordingly in the GUI
* `ModelManager::setHomeStatus`: Sets the home status of Plannit.

Given below is an example usage scenario and how the mechanism
behaves when a user navigates to a module in Plannit.

**Step 1**. The user requests to navigate to a module present in the Plannit
by inputting the `goto` command followed by the module code of the module.
E.g.:
```
goto CS1231
```

**Step 2**: The `LogicManager` uses the `AddressBookParser` and `GoToCommandParser`
to parse the user input. After validating the arguments provided by the user, the user input
is used to perform the following actions: <br>
* Instantiate a `ModuleCodeMatchesKeywordPredicate` object
* Extract the `ModuleCode` of the module to navigate to in the `GoToCommandParser`

**Step 3**: A `GoToCommand` object is instantiated using the `ModuleCodeMatchesKeywordPredicate` and `ModuleCode`
obtained in **Step 2** which is returned to the `LogicManager`.

**Step 4**: `LogicManager` calls the `GoToCommand::execute` method. This method will first
obtain the `Module` associated with the `ModuleCode` obtained in **Step 2** by calling
`ModelManager::getModuleUsingModuleCode`.

**Step 5**: The module list is then filtered using the `ModelManager::updateFilteredModuleList` method according
to the `ModuleCodeMatchesKeywordPredicate` object instantiated in **Step 2**.

**Step 6**: A `PersonIsInModulePredicate` object is then instantiated with the `Module` object obtained in **Step 4**.

**Step 7**: The person list is then filtered using the `ModelManager::updateFilteredPersonList` method according
to the `PersonIsInModulePredicate` object instantiated in **Step 6**.

**Step 8**: The home status of Plannit is set to false via `ModelManager::SetHomeStatus`.

**Step 9**: A new `CommandResult` object is returned, indicating success.

The following sequence diagram summarizes what happens when a user executes the `goto` command:

![GoToSequenceDiagram](images/GoToSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `GoToCommandParser`
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

<div style="page-break-after: always;"></div>

The following activity diagram summarizes what happens when a user executes a `GoToCommand`:

![GoToActivityDiagram](images/GoToActivityDiagram.png)

#### Design consideration:

##### Aspect: How goto executes

* **Alternative 1 (current choice):** Navigate to a single module by module code and update home status.
    * Pros:
        * Provides an intuitive and more versatile way for users to navigate between modules.
        * Provides an intuitive usage of commands by limiting the scope of possible commands usable
          when out of the home page.
    * Cons: Unable to search for multiple persons with different attributes.

* **Alternative 2:** Navigate to a single module by module code.
    * Pros: Provides an intuitive and more versatile way for users to navigate between modules.
    * Cons: Might result in confusion when some commands are usable out of the home page.

* **Alternative 3:** Navigate to module by index.
    * Pros: Allows for quicker input as compared to typing out the entire module code
    * Cons: Require user to navigate back to home page before going to another module which might be unintuitive for users.

##### Rationale behind current choice:
1. Alternative 1 was chosen as it provides a more intuitive way for prospective users.

2. The following commands `list-module` and `find-module` are disabled as they are not meant to be used in tandem with `goto` command
   as the filtering of modules is not needed when there exist only one module.

3. The following commands `list-person` and `find-person` are disabled as they are not meant to be used in tandem with `goto` command
   as essential person within a module (i.e. professors, tutor assistant and friends) are likely to be limited in numbers.

Hence, to prevent confusion we chose Alternative 1.

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

* Tech-Savvy NUS CS Student
* Reasonably fast typer
* Comfortable using CLI apps
* Forgets which friends has common modules with him
* Takes a lot of modules
* Takes modules where information are spread over multiple websites
* Tends to work at night

**Value proposition**:
* This app aims to be a unified platform that helps the user manage his academic details.
* This application will be the go-to place to access all module links, information and deadlines without needing to tediously navigate through multiple different websites.



### User stories

Priorities: `High` (must have), `Medium` (nice to have), `Low` (unlikely to have)

| Priority | As a … | I want to … | So that I can…|
| - | - | - | - |
| `High` | User | Add a contact | View them in future settings |
| `High` | User | Delete a contact | Trim my contact list to keep it updated |
| `High` | User | Add a module into Plannit | Add details of the module |
| `High` | User | Delete a module into Plannit | Remove unnecessary modules after the semester ends |
| `High` | User | Add tasks for each module into Plannit | Keep track of tasks for each module |
| `High` | User | Delete tasks from module on Plannit | Remove tasks that have been completed |
| `High` | User | Add links | Store the links on Plannit for easy reference
| `High` | User | Delete links | Remove the links when the semester ends |
| `High` | User | Easily navigate through different modules | Quickly view relevant modules details |
| `High` | Busy User | See the overview of tasks on the home page | Get a quick summary of upcoming tasks |
| `Medium` | User | Edit module details | Rectify mistakes and update module details when needed |
| `Medium` | User | Edit a contact | Rectify mistakes and update contact details when needed |
| `Medium` | User | Set different priorities to prioritise my tasks	| I know is important and should be done first |
| `Medium` | Forgetful Student | Organise contacts by module | I can discuss difficult assignment questions with them and delete them once the semester is completed |
| `Medium` | Organised Person | Filter contacts by names | Search for contacts relevant for my case |
| `Medium` | Expert User | Search a keyword	| Go to the module which has the relevant topic I have in mind |
| `Medium` | User | Detect (and delete) duplicate items | Avoid adding a same item twice |
| `Medium` | New potential user | See the app populated with sample data | Easily see how the app will look like when it is in use |
| `Medium` | Forgetful student | Group contacts by tags	| Find out which friends are taking what modules |
| `Medium` | Expert User | Purge existing module data | Save time deleting all the module data when the semester ends |
| `Low` | Expert User | Archive existing data | Restore the data in case I mess up |

### Use cases

(For all use cases below, the **System** is the Plannit application and the **Actor** is the user, unless specified otherwise)

#### Use case: UC01 - Add a module
**Main Success Scenario (MSS)**
1. User requests to add module.
2. Plannit adds the module.
3. Plannit displays to the user that the module addition is successful.

Use case ends

**Extensions**
* 1a. The module already exists.
    * 1a1. Plannit displays an error message.

  Use case ends

#### Use case: UC02 - Delete a module
**Main Success Scenario (MSS)**
1.  User requests to list modules.
2.  Plannit displays a list of modules.
3.  User requests to delete a specific module in the list.
4.  Plannit deletes the module.

Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Plannit displays an error message.

  Use case resumes at step 2.

#### Use case: UC03 - Find module
**Main Success Scenario (MSS)**
1. User chooses to search up a module's information.
2. Plannit requests for module code.
3. User enters the module code.
4. Plannit searches for module and displays module details.

Use case ends.

Extensions:
* 3a. Plannit detects that the specified module does not exist.
    * 3a1. Plannit displays a text, informing that the specified module does not exist.

  Use case ends.

#### Use case: UC04 - Add a task
**Preconditions**:
* User has completed [UC01](#use-case-uc01---add-a-module).
* Module list is not empty.

**Main Success Scenario (MSS)**
1.  User requests to add a task to a specific module in the list.
2.  Plannit adds the task to the module.

    Use case ends.

**Extensions**
* 1a. The given module is invalid.

    * 1a1. Plannit displays an error message.

  Use case ends.

* 1b. The given task is in an invalid format.

    * 1b1. Plannit displays an error message.

  Use case ends.

#### Use case: UC05 - Delete a task
**Preconditions**:
* User has completed [UC01](#use-case-uc01---add-a-module).
* Module list is not empty.
* Task list of module is not empty.

**Main Success Scenario (MSS)**
1.  User requests to delete a specific task from a module in the list.
2.  Plannit deletes the task from the module.

Use case ends.

**Extensions**
* 1a. The given module is invalid.

    * 1a1. Plannit displays an error message.

  Use case ends.

* 1b. The given task number is invalid.

    * 1b1. Plannit displays an error message.

  Use case ends.

#### Use case: UC06 - Swap task
**Precondition**
* User has completed [UC01](#use-case-uc01---add-a-module).
* Module list is not empty.
* Task list of module has at least two tasks.

**Main Success Scenario (MSS)**
1. User requests to swap the order of two tasks belonging to a module in the 
   list.
2. Plannit displays to the user the list of tasks with the order of the two 
   tasks swapped.

**Extensions**
* 1a. The given module is invalid.

    * 1a1. Plannit displays an error message.

  Use case ends.

* 1b. The given task number is invalid.

    * 1b1. Plannit displays an error message.

  Use case ends.

#### Use case: UC07 - Add a Module Link
**Preconditions**:
* User has completed [UC01](#use-case-uc01---add-a-module).
* Module list is not empty.

**Main Success Scenario (MSS)**
1. User requests for the addition of a module-specific link.
2. Plannit adds and associates the entered link to the specific module.

Use case ends.

Extension:
* 1a. Plannit detects the module does not exist.
    * 1a1. Plannit displays an error message.

  Use case ends.

* 1b. Plannit detects the link is already added.
    * 1b1. Plannit displays an error message.

  Use case ends.

#### Use case: UC08 - Delete a Module Link
**Main Success Scenario (MSS)**
1. User requests for the deletion of a module-specific link.
2. Plannit deletes the entered link from the specific module.

Use case ends.

Extension:
* 1a. Plannit detects the module does not exist.
    * 1a1 Plannit displays an error message.

  Use case ends.

* 1b. Plannit detects the link does not exist.
    * 1b1 Plannit displays an error message.

  Use case ends.


#### Use case: UC09 - Add a contact
**Main Success Scenario (MSS)**
1.  User chooses to add a contact.
2.  Plannit requests for the to-be-added contact detail.
3.  User enters the to-be-added contact details.
4.  Plannit adds the contact and notifies the user that the contact has been successfully added.

Use case ends.

**Extensions**
* 3a. The contact is duplicate, i.e. name already exists.
    * 3a1. Plannit displays an error message notifying the user that a
      duplicate contact exists.

  Use case ends.

* 3b. The email address is invalid.
    * 3b1. Plannit displays an error message notifying the user that the
      email address is invalid.

  Use case ends.

* 3c. The phone number is invalid.
    * 3c1. Plannit displays an error message notifying the user that the phone
      number is invalid.

  Use case ends.

#### Use case: UC10 - Delete a contact
**Main Success Scenario (MSS)**
1. User chooses to delete a contact.
2. Plannit requests for the name of the contact.
3. User enters the contact's name.
4. Plannit searches for the contact and notifies user that the contact has been
   deleted.

Use case ends.

Extensions:
* 3a. Plannit detects that the specified contact does not exist.
    * 3a1. Plannit displays a text, informing that the specified contact does not exist.

  Use case ends.

#### Use case: UC11 - Find contact
**Main Success Scenario (MSS)**
1. User chooses to search up his friend's email.
2. Plannit requests for name of friend.
3. User enters the friend's name.
4. Plannit searches for the name and displays the friend's details.

Use case ends.

Extensions:
* 3a. Plannit detects that the specified contact does not exist.
    * 3a1. Plannit displays a text, informing that the specified contact does not exist.

  Use case ends.

#### Use case: UC12 - Navigate to Home Page
**Main Success Scenario (MSS)**
1.  User requests to navigate to Home Page.
2.  Plannit displays the Home Page.

Use case ends.

**Extensions**

* 1a. Already at Home Page.

  Use case ends.

#### Use case: UC13 - Navigate to Module
**Main Success Scenario (MSS)**
1.  User requests to navigate to a specific module.
2.  Plannit displays the module details.

Use case ends.

**Extensions**

* 1a. Module does not exist.

    * 1a1. Plannit displays an error message.

  Use case ends.

#### Use case: UC14 - Exit program
**Main Success Scenario (MSS)**
1.  User requests to exit program.
2.  Plannit closes program.

Use case ends.


### Non-Functional Requirements

#### Constraints
1. Plannit cannot use NUS API to scrap data from LumiNUS/Canvas.
2. Plannit should run independently of remote servers.
3. Plannit cannot use a relational database management system.
4. Plannit executable size should be smaller than 100MB.

#### Technical Environment
1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Plannit should work on 64-bit environments.
3. The GUI should:
    * _work well_ for standard screen resolutions 1920x1080 and higher, and for screen scales 100% and 125%.
    * be _usable_ for screen resolutions 1280x720 and higher, and for screen scales 150%.
4. Plannit is packaged into one `JAR` file.
5. The software should work without an installer.
6. Plannit should work without an internet connection.

#### Performance
1. Plannit should be able to hold up to 1000 _entries_ without a noticeable sluggishness in performance for typical usage.
2. Plannit should be able to save and load up to 1000 _entries_ without a noticeable delay in performance.
3. Plannit should respond to any user requests within 2 seconds as long as there are less than 1000 entries.

#### Quality
1. Plannit should be usable by someone with better-than-average typing speed.
2. Plannit should be beginner-friendly and easily picked up by new users.
3. Font size should be of reasonable size.
4. The colours of the application should not induce eye strain.

#### Process
1. Development of Plannit should adhere to the following deadlines:
    * **v1.1**: 28 September 2022 (Week 7, Wednesday)
    * **v1.2**: 12 October 2022 (Week 9, Wednesday)
    * **v1.3**: 26 October 2022 (Week 11, Wednesday)
    * **v1.4**: 07 November 2022 (Week 13, Monday)
2. The team should meet every Saturday at 9pm until the end of the project.
3. Each team member should follow the forking workflow.
4. At least 2 members need to approve a pull request before it can be successfully merged into the master branch on `GitHub`.
5. Plannit's implementation should follow Object-Oriented Programming paradigm primarily.

#### Project Scope
1. Plannit does not automatically update module details/links.
2. Plannit does not rigorously check for the validity of the email provided by the user.
3. Plannit does not verify the security of links provided by the user
4. Plannit does not ensure the validity of links provided by the user.
5. Plannit does not rigorously check for the existence of phone number provided by the user.
6. Plannit does not remind the user of upcoming tasks with notifications.
7. Plannit does not check for invalid module codes.

#### Other noteworthy points
1. Plannit's code should be easily testable.

### Glossary
* **Entries**: Modules, Contacts, Links, Tasks
* **Mainstream OS**: Windows, Linux, Unix, macOS
* **Usable**: All functions can be used even if the user experience is not optimal
* **Work well**: Should not cause any resolution-related inconveniences to the user

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the `JAR` file and copy into an empty folder

    1. Double-click the `JAR` file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the `JAR` file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases … }_

### Deleting a module

1. Deleting a module while all modules are being shown at home page

    1. Prerequisites: User is at home page. List all modules using the `list-module` command. Module `CS1231S` exist in the list.

    2. Test case: `delete-module m/CS1231S`<br>
       Expected: `CS1231S` is deleted from the list. Details of the deleted module shown in the status message. Timestamp in the status bar is updated.

    3. Test case: `delete-module`<br>
       Expected: No module is deleted. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `delete-module m/` <br>
       Expected: Similar to previous.

1. _{ more test cases … }_

### Deleting a contact

1. Deleting a contact while all contacts are being shown at home page

    1. Prerequisites: User is at home page. List all contacts using the `list-contact` command. Person `Alice` exist in the list.

    1. Test case: `delete-person n/Alice`<br>
       Expected: `Alice` is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `delete-person`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete-person n/` <br>
       Expected: Similar to previous.

1. _{ more test cases … }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases … }_
