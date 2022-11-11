---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Introduction**

PayMeLah is a desktop application that helps users track the debts that they are owed. It uses a Command Line Interface (CLI), and executes commands input by the user accordingly to perform actions such as adding a person/debt to track, sorting the data etc.

This Developer Guide documents the architecture, design choices, and implementations of key features of PayMeLah.

If you are a developer that recently joined the PayMeLah development team, or a developer who is simply interested in the inner workings of PayMeLah, this guide would be able to provide you with the relevant technical details.

--------------------------------------------------------------------------------------------------------------------
## **Acknowledgements**

* JavaFX for providing the API for rendering GUI.
* Jackson for providing the API for parsing JSON files.
* JUnit for providing a unit testing framework.
* [Zephyr](https://stackoverflow.com/users/6485651/zephyr) for providing [inspiration](https://stackoverflow.com/a/52458162) for the right-aligned label in the `PersonListPanel` class.
* Our application is based on the [AddressBook-Level 3 (AB-3)](https://se-education.org/addressbook-level3/) project. All features present in our application are in addition to those already present in AB-3.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S1-CS2103T-W13-3/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

<div style="page-break-after: always;"></div>

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103T-W13-3/tp/blob/master/src/main/java/paymelah/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103T-W13-3/tp/blob/master/src/main/java/paymelah/MainApp.java). It is responsible for,
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

<div style="page-break-after: always;"></div>

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S1-CS2103T-W13-3/tp/blob/master/src/main/java/paymelah/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-W13-3/tp/blob/master/src/main/java/paymelah/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-W13-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

<div style="page-break-after: always;"></div>

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103T-W13-3/tp/blob/master/src/main/java/paymelah/logic/Logic.java)

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

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-W13-3/tp/blob/master/src/main/java/paymelah/model/Model.java)

<img src="images/ModelClassDiagram.png" width="900" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
  * each `Person` object separately stores `Debt` objects (contained in a `DebtList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

<div style="page-break-after: always;"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103T-W13-3/tp/blob/master/src/main/java/paymelah/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add debt feature: `adddebt`

#### Implementation

This feature is facilitated by `AddDebtCommand` and `AddDebtCommandParser` in the `Logic` component, and work as per [described above](#logic-component).

When given a valid user input, the `AddDebtCommandParser` will create a new `Debt` object to add to the `DebtList` of the specified `Person`.

Consider a scenario where the user wishes to add a $10 food debt to multiple people. To speed up adding similar `Debt` objects to the `DebtList` of more than 1 `Person`, the `AddDebtCommand` takes in a `Set` of multiple indices. For each `Person` that an `Index` corresponds to, a new `Debt` object will be added to the `DebtList` of the specified `Person`. Some commands such as marking debts as paid, or possible future extensions such as editing a debt, may require modifying a `Debt` object in the `DebtList` of a `Person`. To ensure that modifying this `Debt` for 1 `Person` does not also erroneously modify the `Debt` of another `Person`, during execution of `adddebt`, each `Debt` object is only added to one `DebtList`, and an `equal` instance of `Debt` is created and added for each other `DebtList`.

To enable the user to retroactively add a `Debt` that is backdated, the `AddDebtCommandParser` can take in optional `<date>` and `<time>` parameters. By making these parameters optional, a default behaviour can be implemented such that when neither parameter is specified, a `Debt` object with the current date and time is created. This will improve the efficiency at which users can input new `Debt` objects for the (expected) most common scenario where they add the `Debt` into PayMeLah on the actual day the debt occurred.

<img src="images/AddDebtObjectDiagram.png" width="750" />

Consider an example of a valid `adddebt` command, `adddebt 1 2 d/food m/10`. The new objects in the final internal state after this example has been parsed is given by the object diagram above. Note that new `DebtDate` and `DebtTime` objects are created even though the user did not specify date and time parameters in their input command.

<div style="page-break-after: always;"></div>

The following activity diagrams detail the behaviour of PayMeLah when a user inputs an `adddebt` command of valid syntax to be executed.

<img src="images/AddDebtActivityDiagram.png" width="450" />

<div style="page-break-after: always;"></div>

<img src="images/AddDebtActivityDiagramRake.png" width="450" />

<div style="page-break-after: always;"></div>

### Split debt feature: `splitdebt`

#### Implementation

This feature is facilitated by `SplitDebtCommand` and `SplitDebtCommandParser` in the `Logic` component, and work as per [described above](#logic-component).

When given a valid user input, the `SplitDebtCommandParser` will create a new `Debt` object to add to the `DebtList` of the multiple specified `Person` objects.
The following implementation is highly similar to the above implementation of [add debt](#add-debt-feature-adddebt), however, it differs in that '0' is a valid position in representing a `Person` to the `SplitDebtCommandParser`, and
the value of the `Money` of the `Debt` added to each `Person` is the amount the command specified divided by the number of specified `Person` objects.
However, '0' is not a valid `Index` internally and is only a placeholder to indicate that the user is included in the number of specified `Person` objects. Thus, no `Debt` is actually added to the `Person` whose position in the
displayed list is '0'. It should also be noted that there is also never such a `Person`. The non-zero `Index` objects of the `Person` objects splitting the `Debt` are contained in a set that `DeleteDebtCommandParser` creates.
The `SplitDebtCommandParser` will construct a `SplitDebtCommand` object with this set of `Index` objects and the `Debt` object created.
The remaining functionality and behaviour of `SplitDebtCommand` and `SplitDebtCommandParser` are identical to that of [add debt](#add-debt-feature-adddebt).

Consider an example of a valid `splitdebt` command, `splitdebt 0 1 2 d/pizza m/30`. The new objects in the final internal state after this example has been parsed is given by the object diagram below. Note that new `DebtDate` and `DebtTime` objects are created even though the user did not specify date and time parameters in their input command.

<img src="images/SplitDebtObjectDiagram.png" width="750" />

<div style="page-break-after: always;"></div>

### Clear debts feature: `cleardebts`

#### Implementation
This feature is facilitated by `ClearDebtsCommand` and `ClearDebtsCommandParser` in the `Logic` component, and work as per [described above](#logic-component).

When given a valid user input, the `ClearDebtsCommandParser` will construct a `ClearDebtsCommand` object with the parsed `Index` representing the position of the `Person` in the `Model` component to have his/her debts cleared.

Receiving the `Index` of the specified `Person` from the `ClearDebtsCommandParser`, the `ClearDebtsCommand` object obtains the `Person` object specified from the `Model` component when executed.
The `ClearDebtsCommand` object will create a new `Person` object with identical fields from the `Person` object previously obtained except for a new empty `DebtList`.
This new `Person` object replaces the original `Person` object in the `Model` component.

Consider an example of a valid `cleardebts` command `cleardebts 1`. The objects in the internal state after this example has been parsed, and after the constructed `ClearDebtsCommand` has been executed, is given by the object diagrams on the next page.

<img src="images/ClearDebtsObjectDiagram.png" width="450" />
<img src="images/ClearDebtsObjectDiagramAfter.png" width="400" />

<div style="page-break-after: always;"></div>

### Delete debt feature: `deletedebt`

#### Implementation
This feature is facilitated by `DeleteDebtCommand` and `DeleteDebtCommandParser` in the `Logic` component, and work as per [described above](#logic-component).

When given a valid user input, the `DeletDebtCommandParser` will create a set with the `Index` object that represents the position of the `Debt` object to be removed from the `DebtList` of the specified `Person`.
The `DeleteDebtCommandParser` will construct a `DeleteDebtCommand` object with this set of `Index` objects and the parsed `Index` representing the position of the `Person` in the `Model` component to have his/her debts cleared.

Consider a scenario where the user wishes to delete multiple debts from a person, such as when multiple debts were added to him/her incorrectly. To speed up deleting multiple `Debt` objects from the `DebtList` of the specified `Person`, the `DeleteDebtCommandParser` can take in multiple indices such that the set with the `Index` object contains multiple `Index` objects that each represent the position of the `Debt` object to be removed.

Receiving this set of `Index` objects, and the `Index` of the specified `Person` from the `DeleteDebtCommandParser`, the `DeleteDebtCommand` object obtains the `Person` object and the `Debt` objects specified by the set from the `Model` component when executed.
The `DeleteDebtCommand` object will create a new `Person` object with identical fields from the `Person` object previously obtained except for a new `DebtList` that does not contain the previously obtained `Debt` objects to be removed.
This new `Person` object replaces the original `Person` object in the `Model` component.

The following sequence diagram details such behaviour of PayMeLah when a user enters `deletedebt 1 debt/2 3` to be executed.

<img src="images/DeleteDebtSequenceDiagram.png" width="1100" />

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteDebtCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Consider an example of a valid `deletedebt` command `deletedebt 1 debt/2 3`. The new objects in the internal state after this example has been parsed, and after the constructed `DeleteDebtCommand` has been executed, is given by the following object diagrams.

<img src="images/DeleteDebtObjectDiagram.png" width="450" />
<img src="images/DeleteDebtAfterObjectDiagram.png" width="280" />

<div style="page-break-after: always;"></div>

### Find-by-anything feature: `find`

#### Implementation

This feature is facilitated by `FindCommand` and `FindCommandParser` in the `Logic` component, and works as per [described above](#logic-component).

`FindCommandParser` retrieves all prefixes supported by the command using `ArgumentTokenizer`.
The resulting `ArgumentMultimap` is converted to a `PersonDescriptor`, which will hold the person-related fields to search for,
and a `DebtsDescriptor`, which will hold the debt-related fields to search for, using `ParserUtil`'s `argumentMultimapToPersonDescriptor` and `argumentMultimapToDebtsDescriptor` respectively.
A check ensures that at least one field to search for is specified.
Then, the `PersonDescriptor` and `DebtsDescriptor` are used to construct a `PersonMatchesDescriptorPredicate`,
which will return `true` if the given `Person` matches all the person-related fields in `PersonDescriptor` and all the debt-related fields in `DebtsDescriptor`.

For example, suppose the user has multiple friends named Gary, and wants to find the one that owes money for a burger.
The command `find n/gary d/burger` can be used to accomplish this. The sequence diagram below shows the basic events that take place.

<img src="images/FindSequenceDiagram.png" width="1100" />

Note that due to the universal nature of find-by-anything, this command is dependent on lots of components, as given in the following page:

<img src="images/FindClassDiagram.png" width="750" />

<div style="page-break-after: always;"></div>

### List debtors feature: `listdebtors`

#### Implementation

This feature is facilitated by `ListDebtorsCommandParser` and `ListDebtorsCommand` in the `Logic` component. It also utilises `DebtGreaterEqualAmountPredicate` which implements Java's in-built `Predicate` interface. The command parser and the command itself work similarly to the others, and will not be explained in detail here. Please refer to the Logic component above for more details.

The `DebtGreaterEqualAmountPredicate` constructor takes in a `Money` object, and returns a `Predicate<Person>` that tests whether a `Person`'s total amount owed is greater than or equal to the `Money` parameter. When a user requests to list debtors who owe over a certain amount of money, `ListDebtorsCommandParser` will create a `DebtGreaterEqualAmountPredicate` using the amount provided. The resulting `ListDebtorsCommand` will use this predicate to communicate to the Model which Persons to display: the ones that pass the predicate's test. Note that this command does not modify the internal list of Persons in the Model, only the displayed list.

As an example, suppose the user requests to list debtors who owe more than $10 using the command `listdebtors m/10`. The sequence diagram below shows the illustrates the events that take place.

<img src="images/ListDebtorsSequence.png" width="1000" />

To cater to a common use case where the user might want to simply list all debtors regardless of the amount they owe, `ListDebtorsCommandParser` can also handle requests without an amount specified. In such a case, it will create a predicate that simply checks whether a Person's DebtList is empty.

The activity diagram below details the behaviour of PayMeLah when a user requests to list debtors. Note the difference in behaviour depending on whether the user specifies an amount.

<img src="images/ListDebtorsActivity.png" width="600" />

* **Alternative for listing all debtors:** use a `DebtGreaterEqualAmountPredicate` with $0.01 as the amount
    * Pros: More consistent behaviour: every `ListDebtorsCommand` will have an associated `DebtGreaterEqualAmountPredicate`.
    * Cons: May not work properly with possible future extensions (e.g. Money modified to use other precisions besides 2 decimal points)

<div style="page-break-after: always;"></div>

### Mark debts as paid/unpaid feature: `mark`/`unmark`

#### Implementation

This feature is facilitated by `MarkCommand`/`UnmarkCommand`, and `MarkCommandParser`/`UnmarkCommandParser` in the `Logic` component. It also utilises `Debt` and `DebtList` in the `Model` component. The command parser and the command itself work similarly to the others, and will not be explained in detail here. Please refer to the Logic component above for more details.

When given a valid user index, the `MarkCommandParser`/`UnmarkCommandParser` will create a new `Debt` object marked as paid/unpaid in the `DebtList` of the specified `Person`.

To speed up marking `Debt` objects as paid (for example, when 1 person has paid for multiple debts in 1 shot) in the `DebtList` of 1 `Person`, the `MarkCommand` can take in multiple indices such that a replaced `Debt` object (only if it is unpaid) will be added to the `DebtList` for each mark operation.

An example of the new objects in the internal state when a valid `mark` command provided by the user, `mark 1 debt/2 3`, has been parsed and then executed is given by the object diagrams below.

<img src="images/MarkObjectDiagram.png" width="450" />
<img src="images/MarkAfterObjectDiagram.png" width="280" />

The following activity diagrams on the next pages detail the behaviour of PayMeLah when a user inputs a `mark` command of valid syntax to be executed.

<img src="images/MarkActivityDiagram.png" width="450" />

<div style="page-break-after: always;"></div>

<img src="images/MarkActivityDiagramRake.png" width="450" />

<div style="page-break-after: always;"></div>

### Undo feature: `undo`

#### Implementation

This feature is achieved by saving `addressBookHistories` in the `ModelManager` as a `LinkedBlockingDeque`. Before PayMeLah executes a command that modifies its AddressBook, a copy of the AddressBook will be pushed into this double ended queue. This deque also enforces a capacity of 10; when the dequeue is full and another AddressBook is about to be pushed into it, the oldest AddressBook will be discarded.

When the user gives an `undo` command, the most recent AddressBook will be popped from the `addressBookHistories` and replace the current one.

#### Design Considerations

**Aspect: How undo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage (currently mediated by limiting the capacity of `addressBookHistories` to 10).

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

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

* is a university student who organizes many bulk purchases
* has a need to manage a significant number of contacts
* has a need to track who has paid them back
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
1. track their debtors
1. view total money owed from all debtors
1. manage contacts faster than a typical mouse/GUI driven app

<div style="page-break-after: always;"></div>

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`


| Priority | As a …  | I can …                                                                                       | So that …                                                                    |
|----------|---------|-----------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| `* * *`  | user    | save persons and their contact details                                                        | I do not need to remember these details                                      |
| `* * *`  | user    | keep track of debts                                                                           | I know who owes me money and for what                                        |
| `* * *`  | user    | add debts of the same type to multiple people at once                                         | I do not have to spend a long time adding debts                              |
| `* * *`  | user    | remove debts                                                                                  | I do not mistakenly think I have not yet been paid                           |
| `* * *`  | user    | see how much I am owed in total                                                               | I know how much I expect to be paid                                          |
| `* * *`  | user    | split a debt fairly among several people                                                      | I do not need to manually divide the amount that each person owes            |
| `* * *`  | user    | mark debts as paid/unpaid                                                                     | I know whether the debts has been paid or not                                |
| `* * *`  | user    | close the application                                                                         |                                                                              |
| `* * *`  | user    | specify if an amount of money in the debt is inclusive or exclusive of GST and service charge | I do not have to manually calculate the final debt amount                    |
| `* *`    | user    | search for a person’s contact                                                                 | I can easily access his contact details                                      |
| `* *`    | user    | search for people who owe me money for a certain event                                        | I can tell at a glance who still hasn't paid me for that event               |
| `* *`    | user    | search for people who owe me money past a certain date                                        | I can ensure that these people do not forget to return my money for too long |
| `* *`    | user    | save my contacts and debts over multiple usage sessions of the app                            | I do not need to key in data again when I exit and re-enter the app          |
| `* *`    | user    | sort the list of contacts by name, amount owed and how long they have owed the debt           | I can quickly decide who to prioritize chasing for debts.                    |
| `* *`    | user    | easily undo any unintentional or wrong changes I made to my address book                      | I do not have to take a long time to revert my changes.                      |

<div style="page-break-after: always;"></div>

### Use cases

(For all use cases below, the **System** is `PayMeLah` and the **Actor** is the `user`, unless specified otherwise)

**UC01: Add a person**

**MSS**

1.  User requests to add a person
1.  PayMeLah adds the person

    Use case ends.

**Extensions:**

* 1a. The given input is invalid.

    * 1a1. PayMeLah shows an error message.

      Use case ends.

**UC02: List all persons**

**MSS**

1.  User requests to list all persons
1.  PayMeLah shows a list of all persons

    Use case ends.

**Extensions**

* 1a. The given input is invalid.

    * 1a1. PayMeLah shows an error message.
  
      Use case ends.

<div style="page-break-after: always;"></div>

**UC03: Add a debt**

**MSS**

1.  User requests to list persons as per <ins>UC02: List persons</ins>
1.  User requests to add a debt to specific persons in the list
1.  PayMeLah displays that the debt is added to the persons

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given input is invalid.

    * 2a1. PayMeLah shows an error message.

      Use case resumes at step 2.

**UC04: Split a debt**

**MSS**

1.  User requests to list persons as per <ins>UC02: List persons</ins>
1.  User requests to split a debt among several persons in the list
1.  PayMeLah displays that the split debt is added to the persons

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given input is invalid.

    * 2a1. PayMeLah shows an error message.

      Use case resumes at step 2.

<div style="page-break-after: always;"></div>

**UC05: Mark debts as paid**

**MSS**

1.  User requests to list persons as per <ins>UC02: List persons</ins>
1.  User requests to mark specific debts from a specific person in the list as paid
1.  PayMeLah displays that the debts are marked as paid

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given input is invalid.

    * 2a1. PayMeLah shows an error message.

      Use case resumes at step 2.

**UC06: Delete a person**

**MSS**

1.  User requests to list persons as per <ins>UC02: List persons</ins>
1.  User requests to delete a specific person in the list
1.  PayMeLah displays that the person is deleted

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given input is invalid.

    * 2a1. PayMeLah shows an error message.

      Use case resumes at step 2.

<div style="page-break-after: always;"></div>

**UC07: Delete debts**

**MSS**

1.  User requests to list persons as per <ins>UC02: List persons</ins>
1.  User requests to delete specific debts from a specific person in the list
1.  PayMeLah displays that the debts are deleted from the person

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given input is invalid.

    * 2a1. PayMeLah shows an error message.

      Use case resumes at step 2.

<div style="page-break-after: always;"></div>

**UC08: List persons with debts**

**MSS**

1. User requests to list persons who owe more than or equal to a certain amount of money
1. PayMeLah shows the list of persons satisfying the condition

    Use case ends.

**Extensions**

* 1a. The user did not specify the amount.

    * 1a1. PayMeLah shows the list of persons who owe any debts to the user 
    
      Use case ends.

* 2a. There are no persons satisfying the condition.
  
    * 2a1. PayMeLah informs user that there are no persons that satisfy the condition.

      Use case ends.

**Extensions**

* 2a. The list is empty (no persons with debts).

  Use case ends.

<div style="page-break-after: always;"></div>

**UC09: Clear debts**

**MSS**

1.  User requests to list debtors as per <ins>UC08: List persons with debts</ins>
1.  User requests to clear the debts of a specific debtor in the list
1.  PayMeLah displays that the debts are cleared from the person

**Extensions**

* 2a. The given input is invalid.

    * 2a1. PayMeLah shows an error message

      Use case resumes at step 2.

**UC10: Find a person by name**

**MSS**

1. User requests to find a person by name.
1. PayMeLah shows a list of people with that name.

    Use case ends.

**Extensions**

* 1a. There is no person with the given name.
  * 1a1. PayMeLah shows an error message.

    Use case ends.

<div style="page-break-after: always;"></div>

**UC11: Find a person by debt description**

**MSS**

1. User requests to find a person by debt description.
1. PayMeLah shows a list of people with a debt matching the debt description.

   Use case ends.

**Extensions**

* 1a. There is no debt matching the given description.
    * 1a1. PayMeLah shows an error message.

      Use case ends.

**UC12: Get debt overview**

**MSS**

1. User requests to get overview of all debts
1. PayMeLah shows the total sum of debts the user is owed

   Use case ends.

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

1. The software should work on any mainstream OS as long as it has Java 11 or above installed.
1. The software will not facilitate actual monetary transactions, nor any communications between persons.
1. The software should be able to hold up to 1000 persons and 10000 debts without a noticeable sluggishness in performance for typical usage.
1. The software should not result in noticeable lag for other applications.
1. The product should be for a single user only; multiple users cannot use the software on the same device simultaneously.
1. The data stored by the software should be stored locally and in a human editable text file, rather than stored using a DBMS.
1. The software should work without requiring an installer.
1. The software should not depend on a remote server.
1. The software should not require the user to create an account on a third-party service.
1. The software should follow the Object-oriented paradigm primarily.
1. The software should avoid implementing hard-to-test features or features that make the product hard-to-test.
1. The software should only make use of third-party frameworks/libraries/services if they are free, open-source, and have permissive license terms.
1. The software should be able to be packaged into a single JAR file.
1. The file sizes of the product and its documents should be reasonable, i.e. the JAR file itself should not exceed 100MB and the documents should not exceed 15MB per file.
1. The GUI should work well for standard screen resolutions 1920x1080 and higher, and, for screen scales 100% and 125%.
1. The GUI should be usable for resolutions 1280x720 and higher, and, for screen scales 150%.
1. The GUI should have a readable font, at least size 12.
1. The GUI should be able to respond to user inputs in 500 milliseconds.
1. The software and documentation should be accessible for users who have a basic command of the English language.
1. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

<div style="page-break-after: always;"></div>

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Debtor**: The person who owes money.
* **Creditor**: The person who is owed money.
* **Debt**: The transaction event (e.g. group dinner, Grab food order) where money is owed between a debtor and a creditor

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

   1. Double-click the jar file<br>
      Expected: Shows the GUI with a set of sample persons and debts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

<div style="page-break-after: always;"></div>

### Adding a person

1. Adding a person with valid inputs

    1. Test cases: refer to UG for examples<br>
       Expected: The corresponding person is added to PayMeLah.

1. Adding a person with invalid inputs

    1. Test cases: `add n/adam tele/a`, `add n/bob p/1`, etc.<br>
       Expected: PayMeLah throws an error and shows which input was invalid

1. Adding a duplicate person

    1. Prerequisites: Have a person named `Alex Yeoh` in the persons list
   
    1. Test case: `add n/Alex Yeoh`<br>
       Expected: PayMeLah throws an error informing that there is already an `Alex Yeoh` in the list

<div style="page-break-after: always;"></div>

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First person is deleted from the list. Details of the deleted contact shown in the status message.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message (invalid index).

   1. Other incorrect `delete` commands to try: `delete`, `delete x`, etc. (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. Deleting a person while only some persons are displayed

    1. Prerequisites: Filter the persons list using commands such as `find` and `listdebtors`. One or more persons remaining.

    1. Test cases and expected behaviours are similar to the ones above.

<div style="page-break-after: always;"></div>

### Adding a debt

1. Adding a debt with valid inputs

    1. Prerequisites: There is at least 1 persons in the list.

    1. Test cases: refer to UG for examples.<br>
       Expected: The corresponding debt is added to the person(s) in the list.

1. Adding a debt with invalid inputs

    1. Test case: `adddebt 0 d/kfc m/$10.95`<br>
       Expected: No debt is added. Error details shown in the status message (invalid index).

    1. Other incorrect `adddebt` commands to try: `adddebt 1 d/ m/5`, `adddebt 1 d/burger m/-2.5`, etc.<br>
       Expected: Similar to previous.

1. Adding a duplicate debt

    1. Prerequisites: The first person in the list has a debt with description `Taxi`, money `$20.00`, date `2022-10-11`, time `00:00`.

    1. Test case: `adddebt 1 d/Taxi m/20 date/2022-10-11`<br>
       Expected: PayMeLah throws an error informing that there is already an identical debt belonging to the first person

<div style="page-break-after: always;"></div>

### Splitting a debt

1. Splitting a debt with valid inputs

    1. Prerequisites: There are at least 3 persons in the list.
   
    1. Test cases: `splitdebt 1 2 3 d/Test m/4`<br>
       Expected: The corresponding debt with the description "Test" and value "$1.34" and  is added to the persons 1, 2, and 3 in the list.

1. Splitting a debt with invalid inputs

    1. Test cases: `splitdebt 0 d/kfc m/$10`, `splitdebt d/kfc m/$10`, `splitdebt 1 m/$10`, `splitdebt 1 d/kfc`<br>
       Expected: No debt is added. Error details shown in the status message (invalid format).
   
    1. Test case: `splitdebt 1 d/kfc m/-10`<br>
       Expected: No debt is added. Error details shown in the status message (invalid money format).

1. Splitting a debt with persons that do not exist

    1. Prerequisites: There are 3 persons in the list.

    1. Test cases: `splitdebt 4 d/kfc m/$10`, `splitdebt 0 1 2 3 4 d/kfc m/$10`, `splitdebt 1 5 d/kfc m/$10`<br>
       Expected: No debt is added. Error details shown in the status message (invalid person index).

<div style="page-break-after: always;"></div>

### Deleting a debt

1. Deleting a debt with valid inputs

    1. Prerequisites: The second person in the list has 5 debts.
    
    1. Test cases: `deletedebt 2 debt/2 4`<br>
       Expected: The second and fourth debts are removed from the second person in the list.

1. Deleting a debt with invalid inputs

    1. Test cases: `deletedebt 1 debt/0`, `deletedebt 0 debt/1`, `deletedebt 1`<br>
       Expected: No debt is deleted. Error details shown in the status message (invalid format).

1. Deleting a debt that does not exist

    1. Prerequisites: The first person in the list has 2 debts.

    1. Test cases: `deletedebt 1 debt/3`, `deletedebt 1 debt/1 2 3`, `deletedebt 1 debt/4`<br>
       Expected: No debt is deleted. Error details shown in the status message (invalid debt index).

<div style="page-break-after: always;"></div>

### Clearing debts

1. Clearing debts with valid inputs

    1. Prerequisites: The first person in the list has 1 or more debts.

    1. Test case: `cleardebts 1`<br>
       Expected: All debts are removed from the first person.
   
    1. Other valid commands include any use of `cleardebts` with a correspondingly valid person index.<br>
       Expected: All debts are removed from the corresponding person in the list.

1. Clearing debts with invalid inputs

    1. Test cases: `cleardebts 0`, `cleardebts 1 2`, `cleardebts`, etc.<br>
       Expected: No debts are removed. Error details shown in the status message (invalid format).

1. Clearing debts from a person that does not exist

    1. Prerequisites: There are 3 persons in the list.

    1. Test cases: `cleardebts 4`, `cleardebts 5`, `cleardebts 6`, etc.<br>
       Expected: No debts are removed. Error details shown in the status message (invalid person index).

<div style="page-break-after: always;"></div>

### Marking debts

1. Marking debts with valid inputs

    1. Prerequisites: The first person in the list has unmarked debts.

    1. Test cases: `mark 1 debt/1`, `mark 1 debt/1 2`<br>
       Expected: The corresponding debt(s) of the first person are marked as paid.

    1. Other valid commands include any use of `mark` with a correspondingly valid person index and debt index.<br>
       Expected: The corresponding debt(s) of the corresponding person are marked as paid.

1. Marking debts with invalid inputs

    1. Test case: `mark 0`, `mark 1 debt/0`, `mark debt/2`, etc.<br>
       Expected: No debts are marked. Error details shown in the status message (invalid format).

1. Marking debts from a person that does not exist, or marking a debt that does not exist.

    1. Prerequisites: There are 3 persons in the list, each having 1 debt.

    1. Test case: `mark 4 debt/1`, `mark 1 debt/3`, `mark 2 debt/1 5`, etc.<br>
       Expected: No debts are marked. Error details shown in the status message (invalid person/debt index).

### Unmarking debts

1. Similar to marking debts, but with persons having debts marked as paid initially, then attempting to mark them as unpaid.

<div style="page-break-after: always;"></div>

### Statement

1. Requesting for statement when all persons are being displayed

    1. Prerequisites: List all persons using the `list` command. At least 1 person in the list and the sum of debts is $100.

    1. Test case: `statement`<br>
       Expected: PayMeLah shows a statement showing you are owed $100 in total.

1. Requesting for statement while only some persons are displayed

    1. Prerequisites: Filter the persons list using commands such as `find` and `listdebtors`. At least 1 person in the list and the sum of debts is $50.

    1. Test case: `statement`<br>
       Expected: PayMeLah shows a statement showing you are owed $50 in total.

### Sorting

1. Sorting when all persons are being displayed

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test cases: `sort n/+`, `sort m/-`, `sort date/+`<br>
       Expected: All persons are sorted accordingly as mentioned in UG.
   
    1. Test cases: `sort t/+`, `sort n/?` and other invalid commands with sort.<br>
       Expected: Persons list is not sorted. Error details shown in the status message.

1. Sorting while only some persons are displayed

    1. Prerequisites: Filter the persons list using commands such as `find` and `listdebtors`. Multiple persons remaining.

    1. Test cases: Similar to positive test cases above.<br>
       Expected: All persons are sorted accordingly, but only previously displayed persons are displayed. Use the `list` command to list all persons and verify all persons are indeed sorted correctly.

    1. Negative test cases and expected outcome similar to above.

<div style="page-break-after: always;"></div>

### List debtors

1. Listing all debtors

    1. Prerequisites: One or more persons in PayMeLah have debt(s).

    1. Test case: `listdebtors`<br>
       Expected: All person(s) who have any debt are listed.

    1. Test case: Clear every person's debts using `cleardebts`, then enter the command `listdebtors`<br>
       Expected: No person listed. PayMeLah shows a message explaining no one has debts.

1. Listing debtors who owe above a certain amount

    1. Prerequisites: One or more persons in PayMeLah have debt(s) whose sum is greater or equal to $10, but none more than or equal to $1000.

    1. Test case: `listdebtors m/10`<br>
       Expected: All person(s) who owe more than or equal to $10 of debt are listed.

    1. Test case: `listdebtors m/1000`<br>
       Expected: Similar to expected outcome of negative test case above.

<div style="page-break-after: always;"></div>

### Find

1. Finding people using single person-related input

   1. Prerequisites: One or more persons has a name containing `david`.

   1. Test cases: `find n/david`, `find n/davi`, `find n/DaVid`<br>
      Expected: All person(s) who have a name containing `david` (ignoring whitespace and capitalisation) are listed.

   1. Test cases: `find david`<br>
      Expected: PayMeLah warns that the command format is incorrect.

1. Finding people using single debt-related input

   1. Prerequisites: One or more persons has a debt whose description is exactly `burger`.

   1. Test cases: `find d/burger`<br>
      Expected: All person(s) who have a debt with exact description `burger` are listed.

   1. Test cases: `find d/Burger`, `find d/burgers`<br>
      Expected: No one is listed, unless they have a debt with exact description `Burger`, `burgers`, etc. (case-sensitive exact matching)
   
1. Finding people using multiple inputs

   1. Prerequisites: One or more people with the exact tag `friends` has a debt whose description is exactly `burger`.

   1. Test cases: `find t/friends d/burger`, `find d/burger t/friends`<br>
      Expected: All person(s) with the exact tag `friends`  and a debt with exact description `burger` are listed.

1. Finding people using a threshold input

   1. Prerequisites: One or more people have a debt with monetary amount above or equal to `$10.00`, one or more people have only debts with monetary amount below `$9.99`.

   2. Test cases: `find above/10`, `find above/$10.00`
      Expected: All person(s) with a debt with a monetary amount above to or equal to 10 dollars are listed, those who do not have such a debt are not.

1. Finding people with multiple threshold inputs

   1. Prerequisites: One or more people have debts only before `2022-10-31`, one or more people have debts only after `2022-12-01`, one or more people have a debt within `2022-11-01` to `2022-11-30`.

   1. Test cases: `find after/2022-11-01 before/2022-11-30`
      Expected: All persons(s) with a debt within the month of November 2022 are listed, but those people with no debt in this month are not.

The expected behaviour of the `find` command is documented in detail in the UG, please refer to it for more example test cases to try.

<div style="page-break-after: always;"></div>

### Find debts

1. Finding debts with single keyword

    1. Prerequisites: One or more persons has a debt whose description contains the word `burger`.

    1. Test cases: `finddebt burger`, `finddebt Burger`, `finddebt BURGER`<br>
       Expected: All person(s) who have a debt whose description contains the word `burger` (ignoring capitalisation) are listed.

    1. Test cases: `finddebt n/burger`, `finddebt debt/Burger`<br>
       Expected: PayMeLah warns that command format is incorrect.

1. Finding debts with multiple keywords

    1. Prerequisites: One or more persons has a debt whose description contains the word `burger`. Same for `chicken`.

    1. Test cases: `finddebt chicken burger`, `finddebt Chicken Burger`, `finddebt CHICKEN BURGER`<br>
       Expected: All person(s) who have a debt whose description contains the word `burger`, or a debt whose description contains the word `chicken` (ignoring capitalisation), or both are listed.

    1. Test cases: `finddebt n/chicken burger`, `finddebt debt/Chicken Burger`<br>
       Expected: PayMeLah warns that command format is incorrect.

### Undo

1. Successful undo of a command

    1. Test case: Enter `add n/newPerson`, then `undo`<br>
       Expected: A person with name `newPerson` is added, then removed after `undo` is entered.

    1. Test cases: Enter `add n/newPerson`, then filter the persons list using commands such as `find` and `listdebtors`, then `undo`<br>
       Expected: Similar to above, but filter on persons list is reset and all persons are displayed.

1. Unsuccessful undo of a command

    1. Prerequisites: PayMeLah has just been launched, no command has been entered yet.

    1. Test case: `undo`<br>
       Expected: PayMeLah warns that there are no commands to undo.

    1. Test case: Enter a command which does not modify PayMeLah's data, such as `find` and `statement`, then `undo`.<br>
       Expected: PayMeLah warns that there are no commands to undo.
   
    1. Test case: Add 11 persons using `add n/newPerson1` ... `add n/newPerson11`. Then enter `undo` 11 times.<br>
       Expected: The addition of `newPerson2` to `newPerson11` are reverted successfully, but PayMeLah warns that there are no commands to undo when attempting to do so the 11th time.

<div style="page-break-after: always;"></div>

### Saving data

1. Dealing with missing/corrupted data files

   1. Prerequisites: PayMeLah has been launched at least once and an `addressbook.json` has been created in the `data` folder.
   
   1. Test case: delete the `addressbook.json` and launch PayMeLah<br>
      Expected: PayMeLah launches with the sample persons and debts again, and the corresponding `addressbook.json` has been created in the `data` folder.
   
   1. Test case: open `addressbook.json` and delete a few random lines to corrupt the data. Then launch PayMeLah.<br>
      Expected: PayMeLah launches with an empty list of persons, and `addressbook.json` is cleared to show an empty person list as well.
   
   1. Test case: while PayMeLah is open, delete or modify `addressbook.json`, then execute any valid command in PayMeLah.<br>
      Expected: PayMeLah functions normally and overwrites any corrupted data


--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Appendix: Effort**

* Summary of new features/enhancements compared to AB3:
  * Functional code
    * Introduction of `model.debt` package inclusive of 8 classes, to track debts in addition to persons. Great effort was expanded in integrating the package to existing AB3.
    * Implementation of 11 new `commands` classes (and corresponding `parser` classes) related to managing debts and searching through the person list.
    * Refactoring and enhancement of `find` command to be significantly more comprehensive and effective
    * Updating of GUI
  * Test code
    * Addition of `testutil` classes related to `model.debt` package
    * Significant effort to consistently maintain coverage around the initial 70% from AB3 by always adding new test code with each new feature in the same iteration.
    * Heavy effort in maintaining and passing existing tests to prevent regressions when adding new fields to existing AB3 code and when enhancing code with new fields across iterations.
  * Documentation
    * Greatly expanded UG with more tutorials, examples and explanations for users
    * Increased user-friendliness of UG significantly
    * Maintenance (inclusive of revisions and updates) of existing DG documentation
    * Inclusion of new features and corresponding insights in DG

* Challenges faced:
  * Learning of regex to ensure continuity with AB3 parse format
  * Learning of JavaFX to update GUI
  * Learning of Jackson to maintain data file
  * Learning of PlantUML to update DG
