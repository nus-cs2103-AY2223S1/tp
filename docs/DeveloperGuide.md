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

The class diagram below shows a broad overview of the model component. 
The certain details and dependencies between the `Student`, `Tutor` and `Tuition Classes` has been omitted for simplicity.
The class diagram for `Student`, `Tutor` and `Tuition Classes` can be found right after model class diagram.
<img src="images/ModelClassDiagram.png" width="650" />

This is the full class diagram for `Student`, `Tutor` and `Tuition Classes`.

<img src="images/ClassDiagram.png" width="650" />

The `Model` component,

* stores the address book data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object), 
all `Tutor` objects (which are contained in a `UniqueTutorList` object) and all `TuitionClass` objects 
(which are contained in a `UniqueTuitionClassList` object).
* stores the currently 'selected' `Student` objects, `Tutor` objects and `TuitionClass` objects 
(e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an 
unmodifiable `ObservableList<Student>`, `ObservableList<Tutor>` and `ObservableList<TuitionClass>` respectively that 
can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model 
is given below. It has a `Tag` list in the `AddressBook`, which `Student`, `Tutor` and `Tuition Classes` references. 
This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each , `Tutor` and 
`Tuition Classes` needing their own `Tag` objects respectively. Again certain details and dependencies between the 
`Student`, `Tutor` and `Tuition Classes` has been omitted for simplicity. The class diagram between `Student`, `Tutor` 
and `Tuition Classes` can be found above. <br>

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

### \[Implemented\] Adding a student, tutor or class

#### Implementation

The add mechanism is facilitated by `AddCommand`. It extends `Command` with an enum type `Entity` representing the three different types of entities that can be added to the `Model`. Also, it stores the `Person` or `TuitionClass` instances to be added. Additionally, it implements the following operations:

* `AddCommand#of()` — Creates an `AddCommand` instance encapsulating the entity to be added.
* `AddCommand#execute()` — Executes adding of the encapsulated entity to the `Model`.

The `AddCommand#execute()` operation is exposed in the `Logic` interface as `Logic#execute()`.

Given below is an example usage scenario and how the add mechanism behaves at each step.

Step 1. The user launches the application for the first time.

Step 2. The user executes `add student n/David ...` to add a new student. `AddCommand#of()` is called and a new `AddCommand` instance encapsulating a new `Student` instance to be added to `Model` is instantiated. The `AddCommand#execute()` of this instance is then called, adding the `Student` instance to the `Model`.

The following sequence diagram shows how the add operation works:

{diagram to be added}

Step 3. The user executes `list_s` to view the list of students he has added.

The following activity diagram summarizes what happens when a user executes the add command:

{diagram to be added}
#### Design considerations:

**Aspect: How to handle the adding of class and person separating:**

* **Alternative 1 (current choice):** An `AddCommand` instance has both `Person` and `Class` fields but only atmost one can be non-null at a time.
    * Pros: Less cluttered.
    * Cons: Harder to implement.
* **Alternative 2:** Separate classes that extend `Command` for adding of class and person separately.
    * Pros: Easier to implement.
    * Cons: More cluttered.

### \[Implemented\] Assign/unassign feature

#### Implementation

User would be required to input the command `assign` followed by the index of the person that the user want 
to assign a class to. Lastly, user would have to input the exact tuition class name in the following prefix 
and syntax `n/[Class Name]` to specify which tuition class they want to assign to the specified person.

The `assign` and `unassign` features requires the user to be currently be either in student or tutor list.
If the user is currently not in any of the two lists, the feature will not work and user would be prompted
to go to the valid current list through a `command exception`.

During the execution of `assign` command, it would first check for the type of current list the user is in
as mentioned above. If the current list is student list, the index of assign command would be referred to the
student list and same for if the current list is a tutor list. 

After, the index that the user inputted would be checked 
to see if it is within the size of the current list. If it exceeds the size of the list, a `command exception` would be 
thrown and user would be informed of their invalid index. 

Then with the tuition class name that the user inputted and parsed, it would
be searched among the list of tuition class for matching names. If there is no matching tuition class in the list, 
a `command exception` would be thrown and user would be informed that the tuition class they inputted does not exist.

If there is no `command exception` thrown due to the above scenarios, the tuition class would be assigned to the 
specified student/tutor.

The following sequence diagram shows how the `assign` operation works:
![AssignSequenceDiagram](images/AssignSequenceDiagram.png)

The `unassign` command just does the opposite - it calls `Student#unassignClassFromStudent`/ 
`Tutor#unassignClassFromTutor` instead which remove the specified tuition class from the list of
tuition classes in the student/tutor.

#### Design considerations:

**Aspect: How assign & unassign executes:**

* **Alternate 1 (current choice)):** `assign`/`unassign` just involves adding / removing tuition class from 
  a list of tuition classes that every student/tutor has.
  * Pros: Easier to implement and store in Json format.
  * Cons: Not keeping a list of students and tutors for tuition classes may
  result in a more tedious process when searching in the future.
* **Alternate 2:** `assign`/`unassign` involves adding / removing tuition class from
  a list of tuition classes that every student/tutor has and as well as the tuition classes
  keeping a list of students and tutors it has. 
  * Pros: Makes searching process easier in the future.
  * Cons: Hard and tedious to implement the storing of information in Json. 


### \[Implemented\] Search by multiple fields feature

#### Implementation

The proposed find by fields mechanism searches the currently displayed list based on multiple fields by taking in a set of prefixes with their respective keywords and updating the respective `FilteredList`.

The following activity diagram shows how the `find` operation works:
![FindActivityDiagram](images/FindActivityDiagram.png)

Given below is an example usage scenario and how the find by fields mechanism behaves at each step.

Step 1. The user launches the application. The `ModelManager` would be initialised and the type is set to the default list type which is `STUDENT_LIST`.

Step 2. The user execute `list tutor` command to list out all tutors by calling `ListTutorCommand`. The `ListTutorCommand` calls `Model#updateCurrentListType()` with `TUTOR_LIST` being the parameter, causing the type in `ModelManager` to update to `TUTOR_LIST`.

Step 3. The user executes `find n/john q/computing i/nus` command to search for all tutors who are named John and have graduated from NUS with computing qualifications. The user's input is first parsed into the `AddressBookParser`, where the `COMMAND_WORD` and the `arguments` are separated, and the `Model.ListType` is determined.

Step 4. After checking that the `COMMAND_WORD` is `find`, a new `FindCommandParser` is returned with the `arguments` parsed into it.

Step 5. In the `FindCommandParser`, the `arguments` are tokenized into an `ArgumentMultimap`, where the respective `prefixes` and `keywords` are extracted from the `arguments` and mapped to each other. Following that, `validateArguments` is executed to ensure that the arguments are valid.

Step 6. Afterwards, the pairs of `prefixes` and `keywords` are put into a `HashMap<Prefix, String>`, and a `FindCommand` is then returned with the `HashMap<Prefix, String>` parsed into it.

Step 7. In the `FindCommand`, a `TutorContainsKeywordsPredicate<Tutor>` is created with the `keywords` as input, which tests if the `keywords` are contained by the respective fields in the tutors.

Step 8. Afterwards, the `filteredList` of tutors is updated with that `TutorContainsKeywordsPredicate<Tutor>` in the `ModelManager`. A new `CommandResult` is then returned and a list of tutors with that predicate is then shown.

Step 9. The user now decides he wants to be more specific with his search, and decides to execute `find n/John Doe q/bachelor of computing i/nus` to find all tutors who are named John Doe, and have graduated from NUS with a bachelor's degree in computing. A more specific list of students is then shown.

The following sequence diagram shows how the `find` operation works:
![FindSequenceDiagram](images/FindSequenceDiagram.png)

#### Proposed implementations / add-ons ####

#### 1. Filter feature ####

The `find` feature works very similarly to a "filter" feature, where the list of items is reduced to fit a set of criteria. Currently, the feature "finds" students, tutors or tuition classes by checking if the respective fields contains a keyword string. 
However, many of the field classes of each entity are implemented as enums, where the values of the enums are constant and predictable. For example, the `Day` class has enum values such as `Monday`, `Tuesday`, `Wednesday` etc. 
In such cases, users can input keywords like "day" that matches every single possible enum value for this class, which ends up fulfilling the predicate for every entity on the list.

Therefore, we could let the user use the feature as a "filter", where the user cannot simply input a partial search keyword such as "Mon" and check if the value of the actual `Day` object is a `Monday`. Instead, the user has to input the entire enum value as a filter (e.g. `Monday`) in order for the
function to pass through. Below are some considerations for this approach:

Pros:
- Blocks off unintended use cases, such as giving the input "day" that matches every single entity on the list.
- Reduces unintended errors by limiting the inputs that the user can give. E.g. Can only input `Monday`, `Tuesday`, `Wednesday` etc. for the `Day` field

Cons:
- Much more inflexible as users cannot give inputs that are more lenient and that matches more entities on the list.
- It would be much more troublesome to use on a CLI-centric application as users may have to key in the full spelling of the filter being applied.

Alternatively, the filter feature can be implemented as a separate feature altogether in future iterations.

#### 2. Add more fields for find feature ####

The find feature currently doesn't allow users to search by the following critera:

Student:
1. Details of `NextOfKins`
2. Details of `TuitionClasses` that they are attending

Tutor:
1. Details of `TuitionClasses` that they are teaching

TuitionClass:
1. Details of `Students` that are attending
2. Details of `Tutors` that are teaching

These could be added in future iterations to give users more flexibility in their search.

#### 3. Add interactive UI interactions ####

The find feature is currently a full CLI-centric feature, where users have to key in the command in the correct format to execute the command.
While the application was indeed intended for fast typists, we could consider adding elements of UI interaction to accommodate more user preferences.
For example, we can implement clickable elements to allow users to select a set of "commonly used searches" so that they don't have to re-type predicates that they frequently use.
Furthermore, we can add individual search boxes for each searchable field so that it is much more intuitive for the users to input their predicates.
Therefore, there is a lot of potential for UI to be integrated with the find feature in future iterations.

### \[Implemented\] List type feature

#### Implementation

The list type feature is motivated by the existence of the three different entities that are manipulated by myStudent, namely `Student`, `Tutor` and `TuitionClass`. It is implemented as an enum class `ListType` in `Model` which includes three types - `STUDENT_LIST`, `TUTOR_LIST` and `TUITIONCLASS_LIST` (PERSON_LIST is to be removed in future version). 

The current list type is kept as a `ListType` field `type` in `ModelManager` which implements `Model`. As `Student`, `Tutor` and `TuitionClass` instances are stored in `FilteredList` `filteredStudent`, `filterdTutors` and `filterdTuitionClass` in `ModelManager`, `ListType` `type` would indicate which of the three would be operated on by the `Logic` component. Additionally, to allow access by the `Logic` component, `Model` implements setter and getter methods for the `type`:

* `Model#updateCurrentListType()` - Updates the `type` to the specified list type.
* `Model#getCurrentListType()` - Returns the `ListType` `type` that the `ModelManager` currently stores.
* Model#getCurrentList()` - Returns the current filtered list from `filteredStudents`, `filteredTutors` and `filteredTuitionClass` directly according to the current list type.

The operations are exposed to `Logic` interface as `Logic#updateCurrentListType()`, `Logic#getCurrentListType()` and `Logic#getCurrentList()` respectively. Since `Ui` keeps a reference to `Logic`, these operations can be accessed by `Ui` as well.

`ListType` `type` is referred to by any method that need to access to the current list. Given below is an example usage scenario including `ListTuitionClassCommand` and how the list type mechanism behaves in each step.

Step 1. The user launches the application for the first time. The `ModelManager` would be initialised and the `type` is set to the default list type which is `STUDENT_LIST`.

Step 2. The user execute `list_c` command to list out tuition classes by ccalling `ListTuitionClassCommand`. The `ListTuitionClassCommand` calls `Model#updateCurrentListType()` with `TUITIONCLASS_LIST` being the parameter, causing the type in `ModelManager` to update to `TUITIONCLASS_LIST`. 

Step 3. The command then returns a `commandResult` with its `commandType` field being `LIST`. This will cause calling `commandResult.isList()` to return true. 

Step 4. The `commandResult` is then returned to the `commandResult` in the `executeCommand()` method in `MainWindow`. The `executeCommand()` method then checks that `commandResult.isList()` returns true and calls `MainWindow#handleList()`.

Step 5. The `handleList()` method checks the `type` in `ModelManager` with `Logic#getCurrentListType()`. Since the `type` is set to `TUITIONCLASS_LIST`, it will change the children of `entityListPanelPlaceholder` to `tuitionClassListPanel`, which holds the list of tuition classes.

Step 6. The `handleList()` method then calls `setLabelStyle()`. Similar to `handleList()`, `setLabelStyle()` calls `Logic#getCurrentListType()` to get the `type` in `ModelManager` and set the style class of the `tuitionClassLabelPanel` to `SELECTED_CLASS_LABEL_STYLE_CLASS`, and the `studentLabelPanel` along with the `tutorLabelPanel` to `UNSELECETED_LABEL_STYLE_CLASS`. 

Another example that makes use of the list type is the `DeleteCommand`. Since the `delete` command deletes the entity with the specified index in the current list, it needs to access to the current list type. Below are the steps of how list type mechanism behaves.

Step 1. The user launches the application for the first time. The `ModelManager` would be initialised and the `type` is set to the default list type which is `STUDENT_LIST`.

Step 2. The user executes `delete 1` command to delete the 1th student in the list. The `delete` command calls `Model#getCurrentListType` and gets `STUDENT_LIST` as the current list type. 

Step 3. The `delete` command then deletes the student by calling `Model#deletePerson` with the student to be deleted being the parameter.

### \[Implemented\] Sort Command
The sort command allows users to sort the respective list from Oldest to the Newest entry, Alphabetically or in Reverse order.  
Sorting by default means sorting by oldest to newest updated entry. Editing an entry is considered updating it.  
*(To be added)*: sort by class timings, level.

#### Implementation
Since the list displayed is directly linked to each `Student`, `Tutor` and `TuitionClass` internal list, we can just sort it and the displayed list will be updated. The list to be sorted will be the list that is currently displayed in the UI. `SortCommand` will know this using `ModelManager::getCurrentListType`.  
Sorting by default and alphabetical order is done using the `.sort(Comparator<? super E>)` method of a list, and sorting in reverse is done using `java.util.Collections`.  
** *TODO: add PlantUML diagram* ** 


| Sort by 	     | methods 	|
|---------------|---	|
| Default 	     | Comparator.compare(Student::getUniqueId) 	|
| Alphabetical 	 | Comparator.compare(Tutor::getName) 	|
| Reverse 	     | Collections.reverse(internalList) 	|

#### Design considerations:

**Aspect: How to save the order of the entries since the time/date an entry was entered is not stored.**

* **Alternative 1:** Store entries in a separate list, `OrderedList`.
    - A new entry will first be added to `OrderedList`.
    - `internalList` will then copy the `OrderedList`.
    - when the user wants to sort the list in alphabetical or reverse order, `internalList` will be sorted accordingly.
    - To sort by default, `internalList` just copies the current `OrderedList`.


* **Alternative 2:** Store the order of entries as a field in their respective objects.
    - Have a static field to count the number of `Student`, `Tutor` and `TuitionClass` instances.
    - When a new entry is added, it'll contain a `uniqueId` field, which is the order the entry was added in.
    - When the user wants to sort by default, the comparator can use this `uniqueId` to compare 2 instances.

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

* needs to manage a large pool of information on students, tutors and classes
* needs to retrieve and update information quickly to handle administrative matters
* prefer desktop apps over other types
* can type decently fast
* prefers typing to mouse interactions
* is comfortable using CLI apps

**Value proposition**: To help manage information on students, tutors and classes more efficiently than a typical 
mouse/GUI driven app, and to assist in handling administrative matters in an organised manner.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​            | I want to …​                                             | So that I can…​                                                                                           |
|----------|--------------------|----------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| `* * *`  | sole tuition admin | see a list of commands and their usages                  | refer to the commands when I forgot them                                                                  |
| `* * *`  | sole tuition admin | add a student and his particulars                        |                                                                                                           |
| `* * *`  | sole tuition admin | add a tutor and his particulars                          |                                                                                                           |
| `* * *`  | sole tuition admin | add a class and its data                                 |                                                                                                           |
| `* * *`  | sole tuition admin | delete a student and his particulars                     |                                                                                                           |
| `* * *`  | sole tuition admin | delete a tutor and his particulars                       |                                                                                                           |
| `* * *`  | sole tuition admin | delete a class and its data                              |                                                                                                           |
| `* * *`  | sole tuition admin | display a list of students                               | get an overview of all the students in the tuition center                                                 |
| `* * *`  | sole tuition admin | display a list of tutors                                 | get an overview of all the tutors in the tuition center                                                   |
| `* * *`  | sole tuition admin | display a list of classes                                | get an overview of all the classes in the tuition center                                                  |
| `* * `   | sole tuition admin | find student by field                                    | locate all students with a particular set of criteria without having to go through the entire list        |
| `* * `   | sole tuition admin | find tutor by field                                      | locate all tutors with a particular set of criteria without having to go through the entire list          |
| `* * `   | sole tuition admin | find tuition class by field                              | locate all tuition classes with a particular set of criteria without having to go through the entire list |
| `* * *`  | sole tuition admin | assign a student to a class                              |                                                                                                           |
| `* * *`  | sole tuition admin | unassigned a student from a class                        |                                                                                                           |
| `* * *`  | sole tuition admin | assigned a tutor from a class                            |                                                                                                           |
| `* * *`  | sole tuition admin | unassigned a tutor from a class                          |                                                                                                           |
| `* * *`  | sole tuition admin | see all the particular of a student                      |                                                                                                           |
| `* * *`  | sole tuition admin | see all the particular of a tutor                        |                                                                                                           |
| `* * `   | sole tuition admin | edit a student and his particulars                       |                                                                                                           |
| `* * `   | sole tuition admin | edit a tutor and his particulars                         |                                                                                                           |
| `* * `   | sole tuition admin | edit a tuition class and his particulars                 |                                                                                                           |
| `* * *`  | sole tuition admin | assign next-of-kin to student                            | view the particulars of next-of-kins when I need it the next time                                         |
| `* * `   | sole tuition admin | sort the list of students by different categories        | view the list of students in a more ordered manner                                                        |
| `* * `   | sole tuition admin | sort the list of tutors by different categories          | view the list of tutors in a more ordered manner                                                          |
| `* * `   | sole tuition admin | sort the list of tuition classes by different categories | view the list of tuition classes in a more ordered manner                                                 |
| `* * `   | sole tuition admin | clear the list of students                               | remove all the student entries without deleting one by one                                                |
| `* * `   | sole tuition admin | clear the list of tutors                                 | remove all the tutor entries without deleting one by one                                                  |
| `* * `   | sole tuition admin | clear the list of tuition classes                        | remove all the tuition classes without deleting one by one                                                |
*{More to be added}*

### Use cases

(For all use cases below, the **System** is `myStudent` and the **Actor** is the `tuition admin`, unless specified otherwise)

**Use case: UC1 - Viewing help**

**Guarantees:**

- A list of commands and their usages will be shown.

**MSS**

1.  User requests for help by entering the help command.
2.  myStudent shows a list of commands and their usages.

    Use case ends.

**Use case: UC2 - Add a student**

**Guarantees:**

- A student and his particulars are added to the database if there are no errors.

**MSS**

1.  User adds student by entering the command and student details.
2.  myStudent adds the student.

    Use case ends.

**Extensions**

* 1a. User enter an invalid input for one of the field or has a missing field.

    * 1a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC3 - Add a tutor**

**Guarantees:**

- A tutor and his particulars are added to the database if there are no errors.

**MSS**

1.  User adds tutor by entering the command and tutor details.
2.  myStudent adds the tutor.

    Use case ends.

**Extensions**

* 1a. User enter an invalid input for one of the field or has a missing field.

    * 1a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC4 - Add a tuition class**

**Guarantees:**

- A tuition class and its particulars are added to the database if there are no errors.

**MSS**

1.  User adds tuition class by entering the command and tuition class details.
2.  myStudent adds the tuition class.

    Use case ends.

**Extensions**

* 1a. User enter an invalid input for one of the field or has a missing field.

    * 1a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC5 - Delete a student**

**Guarantees:**

- The student at the specified index will be deleted along with his particulars if it exists.

**MSS**

1. User get <ins>list of students (UC8)<ins>.
2. User requests to delete a specific student in the list.
3. myStudent deletes the student.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.


**Use case: UC6 - Delete a tutor**

**Guarantees:**

- The tutor at the specified index will be deleted along with his particulars if it exists.

**MSS**

1. User get <ins>list of tutors (UC9)<ins>.
2. User requests to delete a specific tutor in the list.
3. myStudent deletes the tutor.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC7 - Delete a tuition class**

**Guarantees:**

- The tuition class at the specified index will be deleted along with its data if it exists.

**MSS**

1. User get <ins>list of tuition classes (UC10)<ins>.
2. User requests to delete a specific class in the list
3. myStudent deletes the class

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.


**Use case: UC8 - List all students**

**Guarantees:**

- A list of students will be shown.

**MSS**

1.  User requests to list all students
2.  myStudent shows a list of all students

    Use case ends.

**Use case: UC9 - List all tutors**

**Guarantees:**

- A list of tutors will be shown.

**MSS**

1. User requests to list all tutors
2. myStudent shows a list of all tutors

    Use case ends.

**Use case: UC10 - List all tuition classes**

**Guarantees:**

- A list of tuition classes will be shown.

**MSS**

1. User requests to list all tuition classes
2. myStudent shows a list of all tuition classes

    Use case ends.

**Use case: UC11 - Find students**

**Guarantees:**

- A list of students whose names match the keyword input will be displayed if at least one of such entity exists and 
there is no syntax error.

**MSS**

1. User get <ins>list of Students (UC8)<ins>.
2. User requests to find students based on keyword input.
3. myStudent shows a list of all students whose names match the keyword input.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. User enter the find command without a field.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC12 - Find tutors**

**Guarantees:**

- A list of tutors whose names match the keyword input will be displayed if at least one of such entity exists.

**MSS**

1. User get <ins>list of tutors (UC9)<ins>.
2. User requests to find tutors based on keyword input.
3. myStudent shows a list of all tutors whose names match the keyword input.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. User enter the find command without a field.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC13 - Find tuition classes**

**Guarantees:**

- A list of tuition classes whose names match the keyword input will be displayed if at least one of such entity exists.

**MSS**

1. User get <ins>list of tuition classes (UC10)<ins>.
2. User requests to find tuition classes based on keyword input.
3. myStudent shows a list of all tuition classes whose names match the keyword input.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. User enter the find command without a field.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC14 - Edit a student**

**Guarantees:**

- The student at the specified index will be edited if it exists and there is no error.

**MSS**
1. User get <ins>list of students (UC8)<ins>.
2. User requests to edit a specific student in the list.
3. myStudent will edit the specified field of the specified student.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

* 2a. User enter an invalid input for one of the field or has a missing field.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC15 - Edit a tutor**

**Guarantees:**

- The tutor at the specified index will be edited if it exists and there is no error.

**MSS**
1. User get <ins>list of tutors (UC9)<ins>.
2. User requests to edit a specific tutor in the list.
3. myStudent will edit the specified field of the specified tutor.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

* 2a. User enter an invalid input for one of the field or has a missing field.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC15 - Edit a tuition class**

**Guarantees:**

- The tuition class at the specified index will be edited if it exists and there is no error.

**MSS**
1. User get <ins>list of tuition classes (UC10)<ins>.
2. User requests to edit a specific tuition class in the list.
3. myStudent will edit the specified field of the specified tuition class.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

* 2a. User enter an invalid input for one of the field or has a missing field.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC16 - Assign a tuition class to student**

**Guarantees:**

- The student at the specified index will be assigned with the specified tuition class if the student and tuition class 
exists.

**MSS**

1. User get <ins>list of students (UC8)<ins>.
2. User requests to assign a tuition class to a specific student in the list.
3. myStudent assign the tuition class to the student.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.
  
* 2b. The tuition class to be assigned does not exist.

    * 2b1. myStudent shows an error message.

      Use case resumes from step 1.
  
* 2c. The tuition class has already been assigned to the student.
    * 2c1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC17 - Assign a tuition class to tutor**

**Guarantees:**

- The tutor at the specified index will be assigned with the specified tuition class if the tutor and tuition class
  exists.

**MSS**

1. User get <ins>list of tutors (UC9)<ins>.
2. User requests to assign a tuition class to a specific tutor in the list.
3. myStudent assign the tuition class to the tutor.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

* 2b. The tuition class to be assigned does not exist.

    * 2b1. myStudent shows an error message.

      Use case resumes from step 1.

* 2c. The tuition class has already been assigned to the tutor.
    * 2c1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC18 - Unassign a tuition class from a student**

**Guarantees:**

- The specified tuition class will be unassigned from the student at the specified index if the student and tuition class
  exists.

**MSS**

1. User get <ins>list of students (UC8)<ins>.
2. User requests to unassign a tuition class from a specific student in the list.
3. myStudent unassign the tuition class from the student.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

* 2b. The tuition class to be unassigned does not exist.

    * 2b1. myStudent shows an error message.

      Use case resumes from step 1.

* 2c. The tuition class has not been assigned to the student initially.
    * 2c1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC19 - Unassign a tuition class from a tutor**

**Guarantees:**

- The specified tuition class will be unassigned from the tutor at the specified index if the tutor and tuition class
  exists.

**MSS**

1. User get <ins>list of tutors (UC9)<ins>.
2. User requests to unassign a tuition class from a specific tutor in the list.
3. myStudent unassign the tuition class from the tutor.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

* 2b. The tuition class to be unassigned does not exist.

    * 2b1. myStudent shows an error message.

      Use case resumes from step 1.

* 2c. The tuition class has not been assigned to the tutor initially.
    * 2c1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC20 - See all the particulars of a student**

**Guarantees:**

- All the particular of the student at the specified index will be shown if it exists.

**MSS**

1. User get <ins>list of students (UC8)<ins>.
2. User requests to see all the particulars of a specific student in the list.
3. myStudent shows all the particular of the student.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC21 - See all the particulars of a tutor**

**Guarantees:**

- All the particular of the tutor at the specified index will be shown if it exists.

**MSS**

1. User get <ins>list of tutors (UC9)<ins>.
2. User requests to see all the particulars of a specific tutor in the list.
3. myStudent shows all the particular of the tutor.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.


**Use case: UC21 - Clear the list of students**

**Guarantees:**

- List of students will be cleared.

**MSS**

1. User get <ins>list of students (UC8)<ins>.
2. User requests to clear the list of students.
3. myStudent removes all the students in the list of students.

   Use case ends.

**Use case: UC22 - Clear the list of tutors**

**Guarantees:**

- List of tutors will be cleared.

**MSS**

1. User get <ins>list of tutors (UC9)<ins>.
2. User requests to clear the list of tutors.
3. myStudent removes all the tutors in the list of tutors.

   Use case ends.

**Use case: UC23 - Clear the list of tuition classes**

**Guarantees:**

- List of tuition classes will be cleared.

**MSS**

1. User get <ins>list of tuition classes (UC10)<ins>.
2. User requests to clear the list of tuition classes.
3. myStudent removes all the tuition classes in the list of tuition classes.

   Use case ends.

**Use case: UC24 - Sort the list of students**

**Guarantees:**

- List of students will be sorted by the specified order.

**MSS**

1. User get <ins>list of students (UC8)<ins>.
2. User requests to sort the list of students.
3. myStudent sorts the list of students.

   Use case ends.

**Extensions**

* 2a. The given order to be sorted by is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.

**Use case: UC25 - Sort the list of tutors**

**Guarantees:**

- List of tutors will be sorted by the specified order.

**MSS**

1. User get <ins>list of tutors (UC9)<ins>.
2. User requests to sort the list of tutors.
3. myStudent sorts the list of tutors.

   Use case ends.

**Extensions**

* 2a. The given order to be sorted by is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.


**Use case: UC26 - Sort the list of tuition classes**

**Guarantees:**

- List of tuition classes will be sorted by the specified order.

**MSS**

1. User get <ins>list of tuition classes (UC10)<ins>.
2. User requests to sort the list of tuition classes.
3. myStudent sorts the list of tuition classes.

   Use case ends.

**Extensions**

* 2a. The given order to be sorted by is invalid.

    * 2a1. myStudent shows an error message.

      Use case resumes from step 1.


*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should be able to toggle between themes to suit the user's preferences.
5. Should be able to work without any internet connection.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

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
