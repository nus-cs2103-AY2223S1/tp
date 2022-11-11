---
layout: page
title: Developer Guide
---

## **Introduction**

Cobb is a JavaFX application that helps property agents manage their database of buyers and properties using a command-line interface.

### Purpose of Guide

This guide is primarily targeted towards developers looking to understand or extend the functionalities of Cobb,
and software testers looking to test Cobb's features. You are also welcome to read this if you understand UML diagrams
and have a general understanding of how a software application works under the hood.

### Scope of Guide

This guide first gives a high-level architecture overview of Cobb, before explaining the main components that make up
the application. It then explains some notable features and the design considerations behind their implementation. To
better understand what the application does from the user's standpoint, you might want to dive into the
[Use cases](#Use cases) section first, or simply run the application and try it for yourself before reading the rest
of the guide.

--------------------------------------------------------------------------------------------------------------------
## **Table of Contents**
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## **Acknowledgements**

Based off on [AddressBook-Level3](https://github.com/se-edu/addressbook-level3). <br>
Libraries used: [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5), [JavaFX](https://openjfx.io),
[PlantUML](https://plantuml.com).

--------------------------------------------------------------------------------------------------------------------
## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**
[Back to top](#table-of-contents)

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called 
[`Main`](https://github.com/AY2223S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2223S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/MainApp.java).
It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues 
the command `deletebuyer 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API 
* `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality 
using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given 
component through its interface rather than the concrete class (reason: to prevent outside component's being coupled 
to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component
[Back to top](#table-of-contents)


**API**: 
[`Ui.java`](https://github.com/AY2223S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `BuyerListPanel`, `PropertyListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103T-F12-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,
* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Buyer` and `Property` objects residing in `Model`.

### Logic component
[Back to top](#table-of-contents)

**API** : 
[`Logic.java`](https://github.com/AY2223S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `CobbParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddBuyerCommand`) 
which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a buyer).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("deletebuyer 1")` 
API call.

![Interactions Inside the Logic Component for the `deletebuyer 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteBuyerCommandParser`
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `CobbParser` class creates an `XYZCommandParser` (`XYZ` is a 
placeholder for the specific command name e.g. `AddBuyerCommandParser`) which uses the other classes shown above to 
parse the user command and create a `XYZCommand` object (e.g. `AddBuyerCommand`) which the `CobbParser` returns back 
as a `Command` object.
* All `XYZCommandParser` classes (e.g. `AddBuyerCommandParser`, `DeletePropertyCommandParser`, ...) inherit from the 
`Parser` interface so that they can be treated similarly where possible e.g. during testing.

### Model component
[Back to top](#table-of-contents)
<<<<<<< HEAD
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

[!ModelClassDiagram](images/ModelClassDiagram.png)

The `Model` component,

* stores the buyer book data i.e., all `Buyer` objects (which are contained in a `UniqueBuyerList` object).
* stores the property book data i.e, all `Property` objects (which are contained in a `UniquePropertyList` object).
* stores the currently 'selected' `Buyer` and `Property` objects (e.g. results of a search query) as separate
_filtered_ lists which are exposed to outsiders as unmodifiable `ObservableList<Buyer>` and `ObservableList<Property>`
respectively that can be 'observed' e.g. the UI can be bound to these lists so that the UI automatically updates when 
the data in the lists change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a 
`ReadOnlyUserPref` object.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they 
should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) 
model is given below. A generic Cobb class is created as the parent for BuyerBook and PropertyBook since the two 
children classes have a lot of methods with identical purposes.<br>

[!BetterModelClassDiagram](images/BetterModelClassDiagram.png)
</div>


### Storage component
[Back to top](#table-of-contents)

**API** :
[`Storage.java`](https://github.com/AY2223S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="650" />

The `Storage` component,
* can save buyer book data, property book data, and user preference data in JSON format, and read them back into 
corresponding objects.
* inherits from `BuyerBookStorage`, `PropertyBookStorage`, `UserPrefStorage`, which means it can be treated as either 
one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects 
that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**
[Back to top](#table-of-contents)

This section describes some noteworthy details on how certain features are implemented.

### Navigate previous commands with arrow keys

#### Motivation

Currently, once a command is executed successfully, there is no way for the user to get it back easily.
However, a user who frequently uses command line interfaces (CLIs) might expect the use of arrow keys to bring
back previous commands, as a way to quickly input multiple similar commands at once.

#### Implementation
`CommandBox` in the `commandbox` package represents the GUI component where the user enters commands.
`CommandRetriever` is a static nested class within `CommandBox`, used to contain and retrieve the history of
successful commands. It implements the following public methods:
- `CommandRetriever#addCommand(String command, TextField textfield)` - Adds a successful command to the command history,
and clears the TextField
- `CommandRetriever#getPreviousCommand(TextField textfield)` - Displays the previous command in the Textfield, if it
exists in the history
- `CommandRetriever#getNextCommand(TextField textfield)` - Displays the next command in the Textfield, if it
exists in the history

These methods are called by `CommandBox#handleCommandEntered()` and `CommandBox#handleKeyReleased(KeyEvent e)`.

Here is the class diagram for `CommandRetriever`.

![CommandRetrieverClassDiagram](images/CommandRetrieverClassDiagram.png)

`CommandRetriever` only keeps track of the commands executed successfully, as invalid commands are highlighted red
and do not disappear from the TextField. Thus, there is no need to store these invalid commands since the user can
already edit the invalid command in the current implementation without having to retype it. Storing invalid commands
in `CommandRetriever` would only clutter up the history, especially if the user inputted numerous invalid commands.

Given below is an example usage scenario and how the arrow key changes the `CommandBox` text field at each step.
A sequence diagram is also provided below.

![CommandRetrieverSequenceDiagram](images/CommandRetrieverSequenceDiagram.png)

Step 1. The user launches the application for the first time. `commandHistory` is initialised as an empty
`ArrayList<String>`, and index is initialised as 0.

Step 2. The user executes a command, `listbuyers` by pressing the Enter key. `CommandBox#handleCommand()` is fired,
getting the text from the text field. Since it is a valid command, it is executed successfully. `listbuyers` is added
to the `commandHistory` list, and index is set to `commandHistory.size()`.

Step 3. The user types a command halfway, but does not press the Enter key. He/she now wishes to use the previous
command to type the command.

Step 4. The user presses and releases the Up arrow. `CommandBox#handleKeyReleased()` is fired, which sets the text field
to display the `(index - 1)`th element in `commmandHistory`. Because the current command is one that has not been
executed, it is saved in the field `currentCommand`.

Step 5. The user presses and releases the Up arrow again. `CommandBox#handleKeyReleased()` is fired, but since there are
no more previous commands, nothing happens.

Step 6. The user presses and releases the Down arrow. `CommandBox#handleKeyReleased()` is fired. Since this is the last
element in `commandHistory`, the text field is set to display the string `currentCommand`. This would be the user's
unexecuted command from Step 3.

### Indexing existing buyers and properties in the database
[Back to top](#table-of-contents)

Many of the existing features that are currently implemented requires Cobb to index existing entries in the database.
For example, `deletebuyer 1` would perform the `deletebuyer` operation on the buyer at index `1` of the buyer list.

If the index provided is not a positive integer or not a valid number, Cobb will throw an error requesting the user to
provide a valid input. Similarly, if the index provided is valid but exceeds the number of elements currently in the list,
Cobb will be able to identify that there is a bounds mismatch and inform the user to provide a valid input within bounds.

Internally, both of the lists of `Buyers` and `Properties` are stored using an `ObservableList`, which is an array-like
data structure provided by JavaFX which fires off reports about all of its changes to associated listeners. This means that
any changes to the structure or objects in the `ObservableList` will be recorded by its listeners, causing the updated
list to be displayed correctly on the user's screen.

#### Design Considerations
**Aspect: How entries are indexed in a list**
* **Alternative 1 (current choice)**: Entries are indexed by their relative positions in the current `ObservableList`.
  If the list is filtered or sorted, then the entries' relative positions will change according to this new version of the
  list.
    * Pros:
        * Users will be able to quickly ascertain the index of an entry in the list simply by finding the entry in the list.
        * Indices of visible entries in the `ObservableList` will always be in the range `[1,n]` inclusive, where n
          is the number of entries currently visible in the list. This gives the indices order and structure.
        * No index field needs to be created for `Buyer` and `Property` objects.
    * Cons:
        * The relative index of an entry will change depending on the current structure of the `ObservableList`. This 
          means that a property that has index `1` might not have the same index after the property list is filtered.

* **Alternative 2**: Entries in a list are indexed by an internal fixed `uuid` parameter that is automatically generated 
  upon its creation.
    * Pros:
        * Users will still be able to identify the index of the entry by first looking for the entry in the list, and then
          looking at the value of its `uuid` parameter.
        * The index of the entry in the list will not change if the structure of the list is changed, e.g. through filtering
          or sorting operations.
    * Cons:
        * `Buyer` and `Property` class will be bloated with one extra parameter.
        * Care needs to be taken to ensure that no IDs are clashing with each other, which might lead to implementation issues.
        * Users might be able to execute commands on entries in the list that are not currently visible, which might lead
          to confusion.

### Internal implementations of Buyer and Property
[Back to top](#table-of-contents)

Cobb allows functionality that performs operations on two distinct types of entities stored in the database: Potential property
buyers, as well as properties that are for sale. To allow for this functionality, two classes were created to represent each type
of entity: `Buyer` and `Property`, respectively.

The structure of a `Buyer` object can be viewed in the class diagram below.

![BuyerClassDiagram](images/BuyerClassDiagram.png)

From the diagram, it can be seen that a `Buyer` object consists of the following attributes:
- `name`, representing the name of the buyer.
- `phone`, representing the phone number of the buyer.
- `address`, representing the address of the buyer.
- `email`, representing the email address of the buyer.
- `priority`, representing the priority of the buyer - High, Normal or Low.
- `priceRange`, representing the price range of properties that a buyer is willing to consider.
- `characteristics`, representing the characteristics of a property that a buyer is looking for.
- `entryTime`, representing the creation time of the buyer (created and accessed internally).

On the other hand, the structure of a `Property` object can be viewed in the class diagram below.

![PropertyClassDiagram](images/PropertyClassDiagramNew.png)

From the diagram, it can be seen that a `Property` object consists of the following attributes:
- `propertyName`, representing the name of the property.
- `address`, representing the address of the property.
- `description`, representing a short description of the property.
- `owner`, representing the Owner of the property. An owner has an `ownerName` and an `ownerPhone`.
- `price`, representing the price of the property.
- `characteristics`, representing the characteristics that a property possesses.
- `propertyEntryTime`, representing the creation time of the property (created and accessed internally).

#### Design Considerations

**Aspect: How `Buyer` and `Property` objects are stored internally**

* **Alternative 1 (current choice)**: Individual classes are created for each of the attributes related to `Buyer` and `Property`
  objects.
  * Pros:
    * Reduces piling up of functionality within the `Buyer` and `Property` classes by abstracting out behaviour related to each
      individual attribute to its own component class.
    * Adheres more to the [Single Responsibility Principle](https://en.wikipedia.org/wiki/Single-responsibility_principle)
      through aforementioned abstraction of functionality.
    * Makes the code neater and easier to follow.
    * Makes individual components of code easier to test and easier to re-use.
  * Cons:
    * Increases coupling of different classes.
    * Increases the number of classes in the codebase.
    
  
* **Alternative 2**: All individual attributes are stored directly in the `Property` or `Buyer` classes, with all related
  functionality also implemented within them. This would result in `Buyer` and `Property` objects similar to that in the 
  UML diagram below.
  ![Alternative Property and Buyer implementation](images/AlternativeBuyerAndProperty.png)
  * Pros:
    * No extra classes are required, as all attributes are stored directly in the component classes.
    * All functionality is executed by the singular component class, so there is no confusion.
  * Cons:
    * Very bloated component classes with a lot of functionality.
    * Difficult to abstract and test each functionality of the class separately.

### Creating a buyer: `addbuyer`
[Back to top](#table-of-contents)

The structure for executing an `addbuyer` command follows the flow as mentioned in the “Logic component” section of this guide.

The `Buyer` class represents a buyer with buyer-specific fields. `PriceRange`, `Characteristics`, and `Priority`
denote his budget, requirements for the property, and buyer priority respectively.

These three fields are all optional:<br>
- When the user chooses not to indicate a buyer’s price range or desired characteristics, the `priceRange` and `desiredCharacteristics` field of a buyer may be null. Hence, they have both been implemented using `Optional<T>`.<br>
- When the user chooses not to indicate a buyer priority, the buyer's priority will be set to the default priority as `NORMAL`.
- When the user creates a buyer, the time of creation is also automatically stored as an `LocalDateTime`.

This is the class diagram of a `Buyer`.

![BuyerClassDiagram](images/BuyerClassDiagram.png)

Following the execution of this sample `addbuyer` command:
`addbuyer -n Jane -ph 89991237 -e jane@gmail.com -a Bishan Street 12 -r 2000-5000`, the following object diagram represents
the internal state of the newly created `Buyer`.

![BuyerObjectDiagram](images/AddBuyerObjectDiagram-Final_state.png)

Note that the following objects are created by default:
1. A new `LocalDateTime` object.
2. A new `Priority` object, representing *NORMAL* priority.
3. A new `Optional<PriceRange>` object. In this context, since a price range was specified in the command, this `Optional<PriceRange>` contains a `PriceRange` object.
4. A new `Optional<Characteristics>` object containing a null pointer, as no characteristics flag was specified in the command above.

The `Optional<Characteristics>` object has been omitted from the object diagram for brevity.

#### Design Considerations
The buyer list should not allow for creation of duplicate buyers. This means that some condition(s) defining equality
between two separate `Buyer` objects should be specified.

Originally, a buyer's `Name` was used to distinguish a singular buyer from others. However, this was changed
in later iterations as it was realised that there could very well exist two unique buyers with similar names.

Instead, the `Phone` and `Email` of the `Buyer` are used to distinguish a buyer, as there cannot be two buyers that have
the exact same phone numbers and email addresses.

The entry time was added towards later parts of development to help facilitate a more flexible implementation of the `sortbuyers` command.
Early discussions allowed a user to provide the date and time manually using a `-d` flag, but this feature was scrapped as
it was found to provide little to no value.

The activity diagram for the creation of a buyer can be seen below.

![Add buyer activity diagram](images/AddBuyerActivityDiagram.png)

### Creating a property: `addprop`
[Back to top](#table-of-contents)

The structure for executing an `addprop` command follows the flow as mentioned in the "Logic component" section of this guide.

The `Property` class represents a property with property-specific fields. `Price` and `Characteristics` classes denote the price and feature of the property respectively.

The `price` field is mandatory while the `characteristics` field is optional. When the user chooses not to indicate a property's characteristics, the `characteristics` field of a property may be null. Hence, it has been implemented using `Optional<T>`.
When the user creates a property, the entry time is also automatically stored as an `LocalDateTime`.

This is the class diagram of a `Property`.

![PropertyClassDiagram](images/PropertyClassDiagramNew.png)

Following the execution of this sample `addprop` command:
`addprop -n Peak Residences -a 333 Thompson Road -p 1000000 -d long property description -o Bob -ph 91234567`, the following
object diagram represents the internal state of the newly created `Property`.

![PropertyObjectDiagram](images/AddPropertyObjectDiagram-Final_state.png)

Note that the following objects are created by default:
1. A new `LocalDateTime` object.
2. A new `Optional<Characteristics>` object containing a null pointer, if no `-c` flag was specified in the command.

<div markdown="span" class="alert alert-primary">:exclamation: **Note:**
The `Optional<Characteristics>` object has been omitted form the object diagram for brevity.
</div>

#### Design Considerations
The property list should not allow for creation of duplicate properties. This means that some condition(s) defining equality
between two separate `Property` objects should be specified.

Originally, `PropertyName` and `Price` were used to distinguish a singular property from others. However, this was changed
in later iterations as it was realised that there could very well exist two entirely separate properties with identical names and prices.

Instead, the `Address` of the `Property` is used to distinguish a property, as there cannot be two properties existing at the exact
same address.

The entry time was added towards later parts of development to help facilitate a more flexible implementation of the `sortprops` command.
Early discussions allowed a user to provide the date and time manually using a `-d` flag, but this feature was scrapped as
it was found to provide little to no value.

The activity diagram for the creation of a property can be seen below.

![Add property activity diagram](images/AddPropertyActivityDiagram.png)

### Editing of buyers and properties
[Back to top](#table-of-contents)

#### Motivation
The user may want to edit the details of a buyer or property after adding it to the application. For example, the user may want to change the budget range of a buyer after adding it to Cobb. 
Or, the user may want to change the price of a property after adding it to Cobb.

#### Implementation
The `EditBuyerCommand` and `EditPropertyCommand` classes extends the `Command` class. They are used to edit the details of a buyer or property, respectively.
Both commands allow the user to change any of the fields of a buyer or property. The commands expect at least one flag to be edited, otherwise an error message will be displayed.
When the edit command is inputted, the `EditBuyerCommandParser` and `EditPropertyCommandParser` classes are used to parse the user input and create the respective `EditBuyerCommand` and `EditPropertyCommand` objects.
When these created command objects are executed by the `LogicManager`, the `EditBuyerCommand#execute(Model model)` or `EditPropertyCommand#execute(Model model)` methods are called. These methods will edit the buyer or property in the model, and return a `CommandResult` object.

<div markdown="span" class="alert alert-primary">:exclamation: **Note:**
To be more concise, we will be referring to both buyers and properties as entities in this section from here onwards.
</div>

During this execution process, the existing entity is first retrieved from the model. The fields of the entities are then edited according to what flags were passed in by the user during the edit commands. 
A new buyer or property is then created with the edited fields, and any fields that have not been edited will be copied over from the original entity. The new entity is then added to the model, and the original entity is removed from the model.
The new buyer or property is then added into the model, replacing the old one. The new entity will then be displayed to the user, and a success message is displayed.

The following sequence diagram shows how the `EditBuyerCommand` is executed.
<img src="images/EditSequenceDiagram.png" height="400" />

#### Design Considerations
**Aspect: How the edit commands should relate to each other:**

* **Alternative 1 (current choice):** `EditBuyerCommand` and `EditPropertyCommand` are separate, and both inherit from the `Command` class.
    * Pros:
        * Both the `Buyer` and `Property` classes have different fields that are exclusive to each other.
        * This reduces complexity of the system, and unexpected behaviours.
        * The inheritance of the `Command` class allows us to keep to the Command design pattern, to easily add more types of edit commands in the future, without having to change the existing code. 
    * Cons:
        * More boilerplate code for each of the classes, which increases the size of the codebase.
* **Alternative 2:** A single `EditCommand` class is used to edit both buyer and property.
    * Cons:
        * Unnecessary complexity is introduced into the system.

**Aspect: How the edited entities should interact with the model:**
* We also decided for the edit commands to create a new entity, instead of editing the existing one. This allows us to not include any setters in the `Buyer` and `Property` classes, which make the objects immutable, so there is less likelihood of unexpected changes to the object. 
By creating a new entity every time the user edits, we can easily add the new buyer or property into the model, and remove the old one. This also allows us to easily undo the edit command in the future, by simply adding the old entity back into the model.

### Owner specification within a property
[Back to top](#table-of-contents)

#### Motivation
In real estate, a property being listed by a property agent is usually owned by a property owner. 
However, the agent may not be the owner of the property. Hence, we decided to allow the user to specify the owner of a property, 
with essential details such as their name and phone number, and have them represented as part of the `Property` class.

#### Implementation
To identify the owner of the property, we decided to include an `Owner` object within a `Property`. This `Owner` class contains two fields: `name` and `phone`.

The `name` and `phone` fields in the `Owner` class are compulsory, to make sure that each property being sold has a relevant contact buyer.
The fields are also validated the same way as when creating a new `Buyer` object.

To support retrieving the `Owner` of a `Property`, we added the following methods:
- `Property#getOwner()` - Returns the `Owner` object of the property.
- `Property#getOwnerName()` - Retrieves the name of the owner of the property.
- `Property#getOwnerPhone()` - Retrieves the phone number of the owner of the property.

This is the class diagram showing the full `Property` class diagram, with the `Owner` class included:
![FullPropertyClassDiagram](images/OwnerClassDiagram.png)

The `Owner` class enacts the Composition relationship, as the `Property` class contains the `Owner` object. Hence, if the property is deleted, it's associated owner will also be deleted.
The tradeoffs for this approach is examined below:

#### Design Considerations

**Aspect: How the owner class associates with the property class**

* **Alternative 1 (current choice):** Owner class is coupled together with the property class.
    * Pros:
      * The `Owner` class is only used in the `Property` class, so it makes sense to couple them together.
      * You do not need to create an owner object separately using another command.
      * This reduces complexity of the system, and unexpected behaviours.
    * Cons:
      * This creates a 1-to-1 relationship between the owner and the property.
      * Each owner is coupled tightly with the property, and cannot be used for other properties.

* **Alternative 2:** Users will have to create an `Owner` object separately, and link it to the property manually.
    * Pros:
      * This allows for a many-to-many relationship between the owners and properties.
      * This allows for better OOP design, as owners will be treated as a separate, first-class entity, similar to
      `Buyer`.
    * Cons:
      * Increases complexity for a possibly limited use case of linking an owner to multiple properties.
      * This may lead to unexpected behaviours, such as whether properties linked to an owner should be deleted when
      the owner is deleted.

### Filtering buyers and properties
[Back to top](#table-of-contents)

In order to filter `Buyers` and `Properties`, a `Predicate` needs to be passed into the `ObservableList` that stores 
references to these objects and displays them on the user's screen. These predicates can differ in the conditions that are
being tested, consequently, they might give different outputs when applied to a given list.


#### Design Considerations
In order to allow for multiple-condition filtering, that is, the concatenation of multiple filter predicates, an abstract 
`AbstractFilterXYZPredicate` class was created to employ polymorphic behaviour, where XYZ represents the entity type that
we are working with, for example `AbstractFilterBuyersPredicate` or `AbstractFilterPropsPredicate`. 

As `Property` has a single specific `Price`, it is much less useful to filter the list using one price value as it is
unlikely to match any property. Instead, we decided to filter by a price range instead, where any property whose price
falls within this range would be displayed.

Additionally, since users
are only allowed to filter using certain conditions as defined in the behaviour of the `filter` commands, concrete classes
extending this abstract predicate class were implemented for each condition. For example:

Users can filter `Properties` by their `PropertyName`, `Price`, `Characteristics` or `OwnerName`. As a result, the following 
concrete predicate classes were implemented:
1. `PropertyNameContainsSubstringPredicate`
2. `FilterPropsByPricePredicate`
3. `FilterPropsContainingAllCharacteristicsPredicate`
4. `FilterPropsContainingAnyCharacteristicsPredicate`
5. `FilterPropsByOwnerNamePredicate`

Based on command parameters passed in by the user, these predicates are constructed and concatenated together to form a single
`Predicate`, which is then used to filter the `ObservableArrayList` directly. More specifics regarding concatenation behaviour
can be found in the [filter-specific design considerations](#filter-specific-design-considerations) section located below.

The class diagram below represents the overall structure of the predicates for `Buyers` and `Properties`. <br>
[!FilterPredicatesClassDiagram](images/FilterPredicatesClassDiagram.png)

#### Filter-specific design considerations
1. Filtering `Properties` by their prices takes in a `priceRange` instead of just a `Price` as it makes more sense for
   agents to want to identify properties that fit within a certain price range instead of a fixed price.
2. For both `filterBuyers` and `filterProps`, the default concatenated predicate will be a logical **AND** of all individual
   predicates, that is, all predicates need to be satisfied in order for the entry to pass through the filter.
3. For both `filterBuyers` and `filterProps`, passing in the `-fuzzy` flag will change the final concatenated predicate to be
   a logical **OR** of all individual predicates, that is, only one of the predicates needs to be satisfied in order
   for the entry to pass through the filter.
4. If the `-c` flag is specified, that is, desired characteristics are supplied as filter conditions, the default behaviour
   is for Cobb to filter out entries that contain **ALL** of the given characteristics. The `-fuzzy` flag changes this behaviour
   to filter out entries that contain *at least one* of the given characteristics.
5. Filtering entries by name - that is, providing the `-n` flag to the filter command, will filter all entries whose names
   contain the parameter provided to `-n` as a *substring*.

### Sorting buyers and properties
[Back to top](#table-of-contents)

To sort `Buyers` and `Properties`, the `ObservableList` that stores references to these objects and displays them on the user's screen
is modified directly to a sorted version. These changes are propagated directly to the `FilteredList`, enabling users to sort
a previously filtered list. As the `FilteredList` is based on the `ObservableList`, users can also sort the list first,
then filter it. This results in users being able to build sort and filter functions on top of each other to more powerfully
manipulate the list based on their needs.

A `Comparator` is used to sort the `ObservableList`. Different comparators with different conditions are used to sort the
list by different criteria. The following are the `Comparators` used to allow for the corresponding sorting functions:

**`Buyer`: `BuyerComparator`**
1. `BuyerNameComparator`: sort by buyer's name
2. `PriceRangeComparator`: sort by buyer's price range
3. `PriorityComparator`: sort by buyer's priority

**`Property`: `PropertyComparator`**
1. `PropertyNameComparator`: sort by property's name
2. `PriceComparator`: sort by property's price <br>

**Both:**
1. `TimeComparator`: sort by entry's time of creation

A `BuyerComparator` compares two `Buyer` entities by using the `Comparator` stored in it on the corresponding `Buyer` fields.
For example, if a `BuyerComparator` contains a `BuyerNameComparator`, the two `Buyer`s are compared by their `Name`s using the `BuyerNameComparator`.
As we allow sorting only by one criterion at a time, a `BuyerComparator` will only contain one field `Comparator`. 

The UML diagrams below represent the overall structure of the `Comparator`s used.

<img src="images/SortPropComparatorsClassDiagram.png" width="1000" height="200"/>
<img src="images/SortPropComparatorsClassDiagram.png" width="1000" height="200"/>

Below is a Sequence Diagram showing how a `sortbuyer -n ASC` command is executed through the model to modify the original `ObservableList`.

![SortBuyersSequenceDiagram](images/SortSequenceDiagram.png)

#### Design Considerations
Similar to the `FilteredList` abstraction provided by JavaFX, we considered using a `SortedList` to present the list in a
sorted version without modifying the underlying data structure `ObservableList`. This is
to preserve the chronological order in which users enter the entries so that it can still be displayed with the `list` command.

However, this meant that we needed to have both `FilteredList` and `SortedList` stored and vary which one is displayed to users
on entering a command. As such, the last-shown list changes depending on the last command entered. To keep track of this,
we used a flag which would be updated everytime a `filter` or `sort` command was used. All `Command`s that referred to an
entry on the displayed list were adapted to take relative indices from the last-shown list indicated by the flag. The `Model`
component also needed access to the `UI` component's `BuyerPanelList` and `PropertyPanelList` in order to display
the corresponding `FilteredList` or `SortedList` based on changes to the flag. To reduce coupling, we would have had to
apply the Observer pattern.

We decided against this as the complicated design made it more bug-prone. In addition, it did not allow for
stacking of `filter` and `sort` functions, that is, a user is unable to filter on top of a sorted list or vice versa as the
`FilteredList` and `SortedList` are independent and separate from each other.

Hence, the above-mentioned design was used. We included chronological sorting as well using `TimeComparator`
so that the user is able to return to the original state of the list,
since sorting by name, for example, would permanently modify the `ObservableList`.

### Matching properties to a buyer, and vice versa
[Back to top](#table-of-contents)

`matchprop` and `matchbuyer` are convenience features, allowing the users to find suitable matches between buyers
and properties easily. Without this, users would have to manually input the conditions from an identified buyer or 
property and make use of the `filter` command. `matchprop` allows users to filter the buyer list with the conditions 
set by a specific property. `matchbuyer` is similar, filtering the property list with the conditions set by a specific 
buyer. 

#### Specifications

For `matchprop`, a `Buyer` is considered a match if its `PriceRange` contains the property's `Price` (inclusive),
and contains at least 1 common characteristic with the property.
For `matchbuyer`, a `Property` is considered a match if its `Price` is within the buyer's `PriceRange` (inclusive),
and contains at least 1 common characteristic with the buyer.

It is also possible to specify `-strict`, which would require a matching object to have _all_ (instead of at least 1)
the characteristics of the target object. This was added due to the possibility that the user gets too many matches,
and wishes to narrow down the results easily. Without this, the user would have to revert to the `filter` command 
and manually input more specific conditions.

Here is a Sequence Diagram of how `matchbuyer 1` is handled, assuming 1 is a valid index and the `Buyer` at index 1 
contains both a `PriceRange` and `Characteristics`.

<img src="images/MatchBuyerCommandSequenceDiagram.png" height="400 />

We see how `MatchBuyerCommand` only works to create a combined predicate of `PriceRange` and `Characteristic`, which is 
then passed to the constructor of `FilterPropertiesCommand` and executed.

#### Design Considerations

`Buyer` and `Property` have many fields, but there were only a few common fields between them. Both classes
contain `Characteristics`. `Property` contains `Price`, which inherits from `PriceRange` that `Buyer` contains. Thus,
it makes sense that only these fields are used in the match commands, since it would not be possible to filter the list
by a field that is not present in a matching object.

Initially, we also considered allowing a `Buyer` or `Property` to be a match if it just contained at least 1 common
characteristic (i.e. a match did not need to satisfy the pricing condition). However, we decided against this as we
felt that a buyer would almost never consider a property that is not within their budget, and vice versa. Allowing this
to occur would likely give the user more matches that were irrelevant instead of useful ones.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**
[Back to top](#table-of-contents)

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**
[Back to top](#table-of-contents)

### Product scope

**Target user profile**:
* property agent
* needs to manage a significant number of buyers and properties concurrently
* often overwhelmed by the large amount of disorganised information
* wants to keep track of all information neatly categorised in one place
* takes too long to manually go through each property to find one that matches a buyer's requirements
* can type fast and prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: <br>
Property agents can make use of Cobb’s flexible filtering and sorting systems to understand key demographics of their customer base. 
They can also leverage on Cobb’s finding system to quickly locate buyers that they want to retrieve information about. 
Finally, they can make use of Cobb’s matching systems to match-make buyers and properties or vice-versa, boosting sales potential.
These features allow them to manage their database easily and replace tedious manual searching work with a quick command.
Quick text-based inputs also allow them to save time.
Cobb's GUI layout also neatly categorises buyers and properties and their respective details, making it easy to take in at a glance. 

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                        | I want to …​                                                                    | So that I can…​                                            |
|----------|------------------------------------------------|---------------------------------------------------------------------------------|------------------------------------------------------------|
| `* * *`  | property agent                                 | add new buyers                                                                  |                                                            |
| `* * *`  | property agent                                 | add new properties                                                              |                                                            |
| `* *`    | property agent                                 | edit contact and property information offline                                   | keep the information updated                               |
| `* *`    | property agent                                 | delete existing contact and property entries                                    | remove redundant information, to keep dataset neat         |
| `* *`    | property agent with a large number of contacts | sort buyers and properties according to various relevant criteria               | easily find information using their order                  |
| `* *`    | property agent                                 | find and filter for certain characteristics                                     | easily find matches                                        |
| `* *`    | non tech-savvy user                            | be able to make use of the command-line interface without too much difficulty   |                                                            |
| `* *`    | property agent                                 | match potential buyers to a property and vice versa                             | quickly determine which properties to show to which buyers |
| `*`      | property agent                                 | view all existing information at a glance in a clean, visually-appealing manner | easily make sense of information presented                 |
| `*`      | property agent with many clients               | avoid duplicate contacts                                                        | have a neat list of active clients                         |
| `*`      | property agent                                 | prioritise some clients who are desperate to find a place                       | contact them first and close the deal more easily          |

### Use cases

(For all use cases below, the **System** is `Cobb` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Add a buyer

**MSS:**

1. User chooses to add a new buyer.
2. User enters the details of the buyer.
3. User executes add command.
4. Cobb adds the buyer and displays updated list of buyers.

Use case ends.

**Extensions**
* 3a. The buyer already exists.<br>
  3a1. Cobb shows an error message.<br>
  Use case ends.

* 3b. User provides incorrectly formatted information.<br>
  3b1. Cobb shows an error message.<br>
  Use case ends.

#### Use case: Add a property

**MSS:**

1. User chooses to add a new property.
2. User enters the details of the property.
3. User executes add command.
4. Cobb adds the property and displays updated list of properties.

Use case ends.

**Extensions**
* 4a. The property already exists.<br>
  4a1. Cobb shows an error message.<br>
  Use case ends.

* 4b. User provides incorrectly formatted information.<br>
  4b1. Cobb shows an error message. <br>
  Use case ends.

#### Use case: Edit a buyer

**MSS:**

1. User chooses to edit an existing buyer.
2. User finds buyer they want to edit.
3. User executes edit command with relevant information to update.
4. Cobb edits the buyer and displays updated list of buyers.

Use case ends.

**Extensions**
* 2a. The property does not exist.
    * 2a1. Cobb shows an error message.
    * Use case ends.
* 3a. The new details cause the property to be a duplicate of another property.
    * 3a1. Cobb shows an error message.
    * Use case ends.
* 3b. The new details are the same as previous details.
    * 3b1. Property remains the same.
    * Use case ends.
* 3c. The new details are invalid.
    * 3c1. Cobb shows an error message.
    * Use case ends
  
#### Use case: Edit a property

**MSS:**

1. User chooses to edit an existing property.
2. User finds property they want to edit.
3. User executes edit command with relevant information to update. 
4. Cobb edits the property and displays updated list of properties.

Use case ends.

**Extensions**
* 2a. The property does not exist.
    * 2a1. Cobb shows an error message.
    * Use case ends.
* 3a. The new details cause the property to be a duplicate of another property.
    * 3a1. Cobb shows an error message.
    * Use case ends.
* 3b. The new details are the same as previous details.
    * 3b1. Property remains the same.
    * Use case ends.
* 3c. The new details are invalid.
    * 3c1. Cobb shows an error message.
    * Use case ends

#### Use case: List buyers

**MSS:**

1. User chooses to list buyers
2. User executes list buyers command.
3. Cobb displays a list of all buyers saved.

Use case ends.

#### Use case: List properties

**MSS:**

1. User chooses to list properties.
2. User executes list properties command.
3. Cobb displays a list of all properties saved.

Use case ends.

#### Use case: Delete irrelevant buyers

**MSS:**

1. User <u>lists buyers</u> [(Use case: List buyers)](#use-case-list-buyers).
2. User finds the buyer that is no longer relevant (e.g. already bought a house).
3. User executes delete command on these buyers.
4. Cobb deletes the buyer and displays updated list of buyers.

Use case ends.

#### Use case: Delete irrelevant properties

**MSS:**

1. User <u>lists properties</u> [(Use case: List properties)](#use-case-list-properties).
2. User finds properties that are no longer relevant (e.g. already sold).
3. User executes delete command on these properties.
4. Cobb deletes the property and displays updated list of properties.

Use case ends.

#### Use case: Sort buyers

**MSS:**

1. User chooses to sort buyers by a specified field in a specified order.
2. User executes sort buyers command on the currently displayed buyer list.
3. Cobb displays the last-shown buyer list in a sorted order according to the specified criteria.

Use case ends.

#### Use case: Sort properties

**MSS:**

1. User chooses to sort properties by a specified field in a specified order.
2. User executes sort properties command on the currently displayed properties list.
3. Cobb displays the last-shown property list in a sorted order according to the specified criteria.

Use case ends.

#### Use case: Match buyer to properties

**Preconditions**: Prospective buyer has been added.

**MSS:**
1. User <u>lists all buyers</u> [(Use case: List buyers)](#use-case-list-buyers).
2. User executes match command on desired buyer.
3. Cobb displays the matched list of properties.

Use case ends.

#### Use case: Find suitable property for new buyer

**MSS:**
1. User gets a new buyer.
2. User <u>adds the buyer</u> [(Use case: Add buyer)](#use-case-add-a-buyer).
3. User tries to <u>match the buyer to suitable properties</u> [(Use case: Match buyer to property)](#use-case-match-buyer-to-properties).
4. Cobb displays the matched list of properties.

Use case ends.

**Extensions:**
* 2a. Buyer already exists.<br>
  2b. User edits the existing buyer with new requirements, if necessary.<br>
  Use case continues at 3.

#### Use case: Match property to buyers

**Preconditions**: Property exists in database.

**MSS:**
1. User <u>lists all properties</u> [(Use case: List properties)](#use-case-list-properties).
2. User executes match command on desired property.
3. Cobb displays the matched list of buyers.

Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should work on any computer fewer than five years old. 

3. Should be able to hold up to 1000 buyers without noticeable sluggishness in performance for typical usage.
4. Should be able to hold up to 1000 properties without noticeable sluggishness in performance for typical usage.
5. Should be able to respond to any given command within two seconds.
6. Should be downloaded and available to use within one minute.
7. Should work without requiring an installer.
8. Should be fully initialised and ready for use within three seconds of launch.
9. Should not depend on a remote server.
10. Should not require access to an internet connection.
11. Should save data locally in a human editable text file.
12. Should be able to be packaged into a JAR file.
13. Should have a graphical user interface with readable font, at least size 11, and resizable.
14. Should have a graphical user interface which is intuitive and user-friendly.
15. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
16. Should be entirely open-source, so data privacy is ensured.
17. A user should be able to use all features of the app without confusion after reading the User Guide.
18. Should be able to be deleted entirely within 30 seconds.
19. Should not cause performance issues on the machine on which it is running, i.e. become a resource hog.
20. Should not require the user to have any prior technical knowledge or expertise.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Buyer**: A client interested in purchasing or investing in properties.
* **Property**: A real estate unit listed or accessible in the market.
* **Owner**: The person who possess the rights to use/ transfer/ commercialize the real estate unit.
* **Property Agent**: A licensed professional that represents buyer or sellers in real estate transaction. Intended target users for this product.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**
[Back to top](#table-of-contents)

This section gives you some information as to how to test the application manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.
</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a buyer
1. Test case: `addbuyer -n Tim -ph 87321237 -e tim@gmail.com -a S648234`<br>
   Expected: New buyer should be added into the list with relevant details. "New buyer added" message should be displayed
   with details of the buyer that was added.

2. Test case: `addbuyer -n Jane -a Street`<br>
   Expected: "Invalid command format" error message should be displayed, with information regarding the syntax of the `addbuyer` command
   and a correct example of the command.

### Adding a property
1. Test case: `addprop -n Peak Residences -a 333 Thompson Road -p 1000000 -d long property description -o Bob -ph 91234567 -c Toa Payoh; Bright`<br>
   Expected: New property should be added into the list with relevant details. "New property added" message should be displayed
   with details of the property that was added.

2. Test case: `addprop -n Peak Residences -a 333 Street`<br>
   Expected: "Invalid command format" error message should be displayed, with information regarding the syntax of the `addprop` command
   and a correct example of the command.

### Deleting a buyer

1. Test case: `deletebuyer 1`<br>
   Expected: First buyer is deleted from the list. "Deleted Buyer" message should be displayed on the screen
   with details of the deleted contact.

2. Test case: `deletebuyer 0`<br>
   Expected: No buyer is deleted. Error details shown in the status message. 

3. Other incorrect delete commands to try: `deletebuyer`, `deletebuyer x`, `...`, `deletebuyer test` (where x is larger than the list size)<br>
   Expected: Similar to previous.

### Deleting a property

1. Test case: `deleteprop 1`<br>
   Expected: First property is deleted from the list. "Deleted Property" message should be displayed on the screen
   with details of the deleted contact.

2. Test case: `deleteprop 0`<br>
   Expected: No property is deleted. Error details shown in the status message.

3. Other incorrect delete commands to try: `deleteprop`, `deleteprop x`, `...`, `deleteprop test` (where x is larger than the list size)<br>
   Expected: Similar to previous.
   
### Editing a buyer

1. Test case: `editbuyer 1 -n Tommy Jones`
   Expected: First buyer in the list should have their name changed to "Tommy Jones". "Edited Buyer" message should also be
   displayed on the screen with details of the edited buyer.

2. Test case: `editbuyer -n Tommy Jones`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `editbuyer`
   command and a correct example.

3. Test case: `editbuyer 1`
   Expected: "At least one field to edit must be provided" error message should be displayed.

### Editing a property

1. Test case: `editprop 1 -n Minecraft dirt hut`
   Expected: First property in the list should have their name changed to "Minecraft dirt hut". "Edited Property" message should also be
   displayed on the screen with details of the edited property.

2. Test case: `editprop -n Minecraft dirt hut`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `editprop`
   command and a correct example.

3. Test case: `editprop 1`
   Expected: "At least one field to edit must be provided" error message should be displayed.

### Finding a buyer

**Prerequisites**: A buyer that has 'John' in his name must exist in the buyer list.

1. Test case: `findbuyers John`
   Expected: Buyer list should be filtered to contain only buyers that have 'John' as a substring in their name (case-insensitive).
   "x buyers listed" message should be displayed, where x refers to the number of buyers in the new filtered list.

2. Test case: `findbuyers`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `findbuyers`
   command and a correct example.

### Finding a property

**Prerequisites**: A property that has 'House' in its name must exist in the property list.

1. Test case: `findprops house`
   Expected: Property list should be filtered to contain only properties that have 'house' as a substring in their name (case-insensitive).
   "x properties listed" message should be displayed, where x refers to the number of properties in the new filtered list.

2. Test case: `findprops`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `findprops`
   command and a correct example.

### Filtering buyers

**Prerequisites**: A buyer that has normal priority must exist in the buyer list.

1. Test case: `filterbuyers -pr NORMAL`
   Expected: Buyer list should be filtered to contain only buyers that have NORMAL as their priority.
   "x buyers listed" message should be displayed, where x refers to the number of buyers in the new filtered list.

2. Test case: `filterbuyers`, `filterbuyers 1`, `filterbuyers x`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `filterbuyers`
   command and a correct example.

### Filtering properties

**Prerequisites**: A property that has an owner with name containing "Johnny" must exist in the property list.

1. Test case: `filterprops -o Johnny`
   Expected: Buyer list should be filtered to contain only properties whose owners have names containing "Johnny".
   "x properties listed" message should be displayed, where x refers to the number of properties in the new filtered list.

2. Test case: `filterprops`, `filterprops 1`, `filterprops x`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `filterprops`
   command and a correct example.

### Listing all buyers

**Prerequisites**: Buyer list should be filtered to show a subset of the original list.

1. Test case: `listbuyers`
   Expected: Buyer list should return to its original state containing all buyers. "Listed all buyers" message should be displayed
   on the screen.

2. Test case: `listbuyers 1`, `listbuyers x`
   Expected: Same behaviour as above.

### Listing all properties

**Prerequisites**: Property list should be filtered to show a subset of the original list.

1. Test case: `listprops`
   Expected: Property list should return to its original state containing all properties. "Listed all properties" message should be displayed
   on the screen.

2. Test case: `listprops 1`, `listprops x`
   Expected: Same behaviour as above.

### Matching buyers to properties

1. Test case: `matchbuyer 5`
   Expected: Buyer list should be filtered to contain all properties that match a given buyer based on their price range and 
   characteristics. "x matched properties for the buyer" message should be displayed on the screen, with x representing the number
   of matched properties found, along with the buyer's information.

2. Test case: `matchbuyer`, `matchbuyer 5 30`, `matchbuyer x`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `matchbuyer`
   command and a correct example.

### Matching properties to buyers

1. Test case: `matchprop 5`
   Expected: Property list should be filtered to contain all buyers that match a given property based on its price and
   characteristics. "x matched buyers for the property" message should be displayed on the screen, with x representing the number
   of matched buyers found, along with the property's information.

2. Test case: `matchprop`, `matchprop 5 30`, `matchprop x`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `matchprop`
   command and a correct example.


### Sorting buyers

1. Test case: `sortbuyers -n ASC`
   Expected: Buyer list should be sorted in increasing alphabetical order. "Sorted buyers by:" message should be displayed on the screen
   with correct criteria and order.

2. Test case: `sortbuyers`, `sortbuyers x`, `sortbuyers 1`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `sortbuyers`
   command and a correct example.

3. Test case: `sortbuyers -n`, `sortbuyers -n oops`, `sortbuyers -n 1`
   Expected: "Order should be ASC or DESC" error message should be displayed.

### Sorting properties

1. Test case: `sortprops -n ASC`
   Expected: Property list should be sorted in increasing alphabetical order. "Sorted properties by:" message should be displayed on the screen
   with correct criteria and order.

2. Test case: `sortprops`, `sortprops x`, `sortprops 1`
   Expected: "Invalid command format" error message should be displayed, along with information regarding the syntax of the `sortprops`
   command and a correct example.

3. Test case: `sortprops -n`, `sortprops -n oops`, `sortprops -n 1`
   Expected: "Order should be ASC or DESC" error message should be displayed.
