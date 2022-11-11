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

The *Sequence Diagram* below shows how the components interact with each other for
the scenario where the user issues the command `delC 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API
`interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality
using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given
component through its interface rather than the concrete class (reason: to prevent outside component's being coupled
to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`,
`PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract
`UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files
that are in the `src/main/resources/view` folder. For example, the layout
of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Client` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `RealTimeParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddClientCommand`)
which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delC 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for
`DeleteClientCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline
reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `RealTimeParser` class creates an
`XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddClientCommandParser`) which uses the
other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddClientCommand`) which the
`RealTimeParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddClientCommandParser`, `DeleteOfferCommandParser`, ...) inherit from
the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the data in RealTime i.e., all `Client`, `Offer`, `Listing` and `Meeting` objects (which are contained in a
`UniqueClientList`, `UniqueOfferList`, `UniqueListingList` and `UniqueMeetingList` object respectively).
* stores the currently 'selected' `Client`, `Offer`, `Listing` and `Meeting` objects (e.g., results of a search query)
as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Client>`,
`ObservableList<Offer>`, `ObservableList<Listing>` and `ObservableList<Meeting>` that can be 'observed' e.g. the UI
can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a
`ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain,
they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `RealTime`, which `Person` references. This allows `RealTime` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both RealTime data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `RealTimeStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.realtime.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedRealTime`. It extends `RealTime` with an undo/redo history, stored internally as an `RealTimeStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedRealTime#commit()` — Saves the current address book state in its history.
* `VersionedRealTime#undo()` — Restores the previous address book state from its history.
* `VersionedRealTime#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitRealTime()`, `Model#undoRealTime()` and `Model#redoRealTime()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedRealTime` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitRealTime()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `realTimeStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitRealTime()`, causing another modified address book state to be saved into the `realTimeStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitRealTime()`, so the address book state will not be saved into the `realTimeStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoRealTime()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial RealTime state, then there are no previous RealTime states to restore. The `undo` command uses `Model#canUndoRealTime()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoRealTime()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `realTimeStateList.size() - 1`, pointing to the latest address book state, then there are no undone realTime states to restore. The `redo` command uses `Model#canRedoRealTime()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitRealTime()`, `Model#undoRealTime()` or `Model#redoRealTime()`. Thus, the `realTimeStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitRealTime()`. Since the `currentStatePointer` is not pointing at the end of the `realTimeStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Implemented\] Add Client feature

#### Client
REal-Time manages **_Clients_** of the user.

#### Implementation

To add an `Client` object, the `AddClientCommand` must be executed. This stores the `Client` object into the
`UniqueClientList`. Following the command execution pathway, the implementation of adding clients uses the exposed
`Model#addClient(Client)` method in the `Model` API.

The `AddClientCommand` is parsed by the `AddClientCommandParser`.
`AddClientCommandParser#parse()` parses the user input to return a `AddClientCommand` object that will be executed.

Given below is an example usage scenario and how the add clients mechanism behaves at each step.

Step 1. The user opens up the `REal-Time` application for the first time and observes that there are some sample data
in the `UniqueClientList`.

Step 2. The user keys in the command word for adding a client, `addC`, followed by the compulsory parameters needed to
add a client, namely the `Name` component prefixed by `n/`, `Phone number` component prefixed by `p/`, the `Email address`
component prefixed by `e/`, the `Address` component prefixed by `a/` and the optional `Tag` component prefixed by
`t/`. In this scenario, the user keys in `addC n/John Doe p/12345678 e/john@gmail.com a/123 John Avenue t/friend t/ neighbor`
into the command box which will execute the `AddClientCommandParser` to check through the arguments and ensure that the compulsory fields are
present. It will then create the `Name`, `Phone`, `Email`, `Address` and `Tag` objects needed to instantiate an `Client` object. The
parser returns a `AddClientCommand` object with the `Client` object ready to be added into `Model`

Step 3. The `AddClientCommand` calls the `Model#addClient(Client)` to add the client and its relevant attributes.

Step 4. Depending on whether the `UniqueClientList` already contains data or is an empty list, the added Client will
be sorted automatically by the `ListingId` in lexicographical order. This is done using the `Collections#sort()` method
and implementing a natural order by implementing the `Comparable` interface and overriding the `Comparable#compareTo()`
method.

The sequence diagram below shows the add client process.

![addClientSequenceDiagram](images/AddClientSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for
`AddClientCommandParser` and `RealTimeParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline
reaches the end of diagram.
</div>

#### Design considerations

**Aspect: Client-Listing interaction**

* **Alternative 1 (current choice):** Separate Listings and Client objects
    * Pros: Easier to maintain as they are separate objects, less dependency between each other
    * Cons: No reference between the `Client` and `Listing` objects
* **Alternative 2:** Allow Clients to have a Listing attribute instead
    * Pros: Easier to reference between `Client` and `Listing`
    * Cons: Large dependency between the two.

### \[Implemented\] Delete Client feature

The Delete Client feature allows users to delete the specified `Client` in the `UniqueClientList`.

#### Implementation

To delete an `Client` object, the `DeleteClientCommand` must be executed. The `DeleteClientCommand` extends the `Command`
class.

The `DeleteClientCommand` is parsed by the `DeleteClientCommandParser`. `DeleteClientCommandParser#parse()` parses the user
input to return a `DeleteClientCommand` that will be executed.

Given below is an example usage scenario and how the delete client mechanism behaves at each step.

Step 1. The user enters `delL 1` in the command box.  This calls the `LogicManager#execute()` method which calls the
`RealTimeParser#parse` method to be executed and will determine that the command is a `DeleteClientCommand` after parsing.

Step 2. A `DeleteClientCommandParser` object is instantiated with the necessary arguments. It will return a
`DeleteClientCommand` object with the specified `Index`.

Step 3. The `DeleteClientCommand#execute()` method is called which calls the `Model#deleteClient(Client)` method to delete
the Client at the specified `Index` in `Model`.

The sequence diagram below shows the delete client process.

![deleteClientSequenceDiagram](images/DeleteClientSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for
`DeleteClientCommandParser` and `DeleteClientCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline
reaches the end of diagram.
</div>

#### Design considerations

**Aspect: Using index specific or attribute specific deletion**

* **Alternative 1 (current choice):** Index specific
    * Pros: Easier implementation and more suited for fast-typing
    * Cons: Might accidentally delete the wrong `Client` if `Index` is mistyped
* **Alternative 2:** Attribute specific
    * Pros: User does not have to find for specific `Index`
    * Cons: Many `Client` objects might share same attribute such as `Address` and `Phone Number`

### \[Implemented\] Edit Client feature

The Edit Client feature allows users to edit any attribute of an `Client` in the `UniqueClientList`.

#### Implementation

To edit an `Client`, the `EditClientCommand` is executed. The `EditClientCommand` extends the `Command` class.

The `EditClientCommand` is parsed by the `EditClientCommandParser`. `EditClientCommandParser#parse()` parses the user input
to return a `EditClientCommand` that will be executed.

Given below is an example usage scenario and how the edit client mechanism behaves at each step.

Step 1. The user enters `editC 1 n/Jane Doe p/87654321 a/123 Jane Lane` in the command box. This calls the `LogicManager#execute()`
method which calls the `RealTimeParser#parse` method to be executed and will determine that the command is a
`EditClientCommand` after parsing.

Step 2. An `EditClientCommandParser` object is instantiated with the necessary arguments. It returns a `EditClientCommand`
with the `Index` and `EditClientDescriptor` as parameters. The `EditClientDescriptor` contains the edited information that
the edited `Client` is supposed to be updated to. In this scenario, we are changing the `Name`, `Phone` and `Address` attribute
of the `Client`. The `EditClientDescriptor` is then updated through the `setName()`, `setName()` and `setAddress()` methods by
the `EditClientDescriptor`.

Step 3. The `EditClientCommand#execute()` method is called which creates the edited `Client` using the `createEditedClient`
method which takes in the `Client` to be edited and the `EditClientDescriptor` that was created from the previous step.
The edited `Client` is then checked if it is a duplicate or already exists in the `UniqueClientList` through the
`Client#isSameClient()` and `Model#hasClient()` methods.

An `Client` is the same if it contains the exact same `Name`.

Step 4. The `Model#setClient(Client)` method is called to replace the target `Client` at the specified `Index` from earlier.
The `Model#updateFilteredClientList()` is called to update the `UniqueClientList`.

### \[Implemented\] Add Offer feature

#### Offers

REal-Time manages **_Offers_** made by Clients that are interested when bidding for Listings.
#### Implementation

To add an `Offer` object, the `AddOfferCommand` must be executed. This stores the `Offer` object into the
`UniqueOfferList`. Following the command execution pathway, the implementation of adding clients uses the exposed
`Model#addOffer(Offer)` method in the `Model` API.

The `AddOfferCommand` is parsed by the `AddOfferCommandParser`.
`AddOfferCommandParser#parse()` parses the user input to return a `AddOfferCommand` object that will be executed.

Given below is an example usage scenario and how the add clients mechanism behaves at each step.

Step 1. The user opens up the `REal-Time` application for the first time and observes that there are some sample data
in the `UniqueOfferList`.

Step 2. The user keys in the command word for adding a client, `addO`, followed by the compulsory parameters needed to
add a client, namely the `Name` component prefixed by `n/`, `Price` component prefixed by `o/` and the `ListingId`
component prefixed by `l/`. In this scenario, the user keys in `addO n/John Doe l/BEDOK_SOUTH o/600000` into the command box
which will execute the `AddOfferCommandParser` to check through the arguments and ensure that the compulsory fields are
present. It will then create the `Price`, `ListingId` and `Name` objects needed to instantiate an `Offer` object. The
parser returns a `AddOfferCommand` object with the `Offer` object ready to be added into `Model`

Step 3. The `AddOfferCommand` calls the `Model#addOffer(Offer)` to add the client and its relevant attributes.

Step 4. Depending on whether the `UniqueOfferList` already contains data or is an empty list, the added Offer will
be sorted automatically by the `ListingId` in lexicographical order. This is done using the `Collections#sort()` method
and implementing a natural order by implementing the `Comparable` interface and overriding the `Comparable#compareTo()`
method.

The sequence diagram below shows the add client process.

![addOfferSequenceDiagram](images/AddOfferSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for
`AddOfferCommandParser` and `RealTimeParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline
reaches the end of diagram.
</div>

#### Design considerations

**Aspect: Client-Offer interaction**

* **Alternative 1 (current choice):** Separate Offers and Client objects
  * Pros: Easier to maintain as they are separate objects, less dependency between each other
  * Cons: No reference between the `Client` and `Offer` objects
* **Alternative 2:** Allow Clients to have an Offer attribute instead
  * Pros: Easier to reference between `Client` and `Offer`
  * Cons: Large dependency between the two.

### \[Implemented\] Delete Offer feature

The Delete Offer feature allows users to delete the specified `Offer` in the `UniqueOfferList`.

#### Implementation

To delete an `Offer` object, the `DeleteOfferCommand` must be executed. The `DeleteOfferCommand` extends the `Command`
class.

The `DeleteOfferCommand` is parsed by the `DeleteOfferCommandParser`. `DeleteOfferCommandParser#parse()` parses the user
input to return a `DeleteOfferCommand` that will be executed.

Given below is an example usage scenario and how the delete offer mechanism behaves at each step.

Step 1. The user enters `delO 1` in the command box.  This calls the `LogicManager#execute()` method which calls the
`RealTimeParser#parse` method to be executed and will determine that the command is a `DeleteOfferCommand` after parsing.

Step 2. A `DeleteOfferCommandParser` object is instantiated with the necessary arguments. It will return a
`DeleteOfferCommand` object with the specified `Index`.

Step 3. The `DeleteOfferCommand#execute()` method is called which calls the `Model#deleteOffer(Offer)` method to delete
the Offer at the specified `Index` in `Model`.

The sequence diagram below shows the delete offer process.

![deleteOfferSequenceDiagram](images/DeleteOfferSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for
`DeleteOfferCommandParser` and `DeleteOfferCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline
reaches the end of diagram.
</div>

#### Design considerations

**Aspect: Using index specific or attribute specific deletion**

* **Alternative 1 (current choice):** Index specific
    * Pros: Easier implementation and more suited for fast-typing
    * Cons: Might accidentally delete the wrong `Offer` if `Index` is mistyped
* **Alternative 2:** Attribute specific
    * Pros: User does not have to find for specific `Index`
    * Cons: Many `Offer` objects might share same attribute such as `ListingId` and `Price`

### \[Implemented\] Edit Offer feature

The Edit Offer feature allows users to edit any attribute of an `Offer` in the `UniqueOfferList`.

#### Implementation

To edit an `Offer`, the `EditOfferCommand` is executed. The `EditOfferCommand` extends the `Command` class.

The `EditOfferCommand` is parsed by the `EditOfferCommandParser`. `EditOfferCommandParser#parse()` parses the user input
to return a `EditOfferCommand` that will be executed.

Given below is an example usage scenario and how the edit offer mechanism behaves at each step.

Step 1. The user enters `editO 1 l/Bukit_timah_rd o/1000000` in the command box. This calls the `LogicManager#execute()`
method which calls the `RealTimeParser#parse` method to be executed and will determine that the command is a
`EditOfferCommand` after parsing.

Step 2. An `EditOfferCommandParser` object is instantiated with the necessary arguments. It returns a `EditOfferCommand`
with the `Index` and `EditOfferDescriptor` as parameters. The `EditOfferDescriptor` contains the edited information that
the edited `Offer` is supposed to be updated to. In this scenario, we are changing the `listingId` and `Price` attribute
of the `Offer`. The `EditOfferDescriptor` is then updated through the `setListing()` and `setOfferPrice()` methods by
the `EditOfferDescriptor`.

Step 3. The `EditOfferCommand#execute()` method is called which creates the edited `Offer` using the `createEditedOffer`
method which takes in the `Offer` to be edited and the `EditOfferDescriptor` that was created from the previous step.
The edited `Offer` is then checked if it is a duplicate or already exists in the `UniqueOfferList` through the
`Offer#isSameOffer()` and `Model#hasOffer()` methods.

An `Offer` is the same if it contains the exact same `Name`, `Price` and `ListingId`.

Step 4. The `Model#setOffer(Offer)` method is called to replace the target `Offer` at the specified `Index` from earlier.
The `Model#updateFilteredOfferList()` is called to update the `UniqueOfferList`.


### \[Implemented\] Add Listing feature

#### Listing
REal-Time manages **_Listings_** owned by Clients.

#### Implementation

To add an `Listing` object, the `AddListingCommand` must be executed. This stores the `Listing` object into the
`UniqueListingList`. Following the command execution pathway, the implementation of adding listings uses the exposed
`Model#addListing(Listing)` method in the `Model` API.

The `AddListingCommand` is parsed by the `AddListingCommandParser`.
`AddListingCommandParser#parse()` parses the user input to return a `AddListingCommand` object that will be executed.

Given below is an example usage scenario and how the add listings mechanism behaves at each step.

Step 1. The user opens up the `REal-Time` application for the first time and observes that there are some sample data
in the `UniqueListingList`.

Step 2. The user keys in the command word for adding a listing, `addL`, followed by the compulsory parameters needed to
add a listing, namely the `ListingId` component prefixed by `l/`, `Address` component prefixed by `a/`, the `Owner name`
component prefixed by `n/` and `Asking price` component prefixed by `ap/`. In this scenario, the user keys in
`addL l/007 a/100 Charming avenue n/John Doe ap/800000` into the command box
which will execute the `AddListingCommandParser` to check through the arguments and ensure that the compulsory fields are
present. It will then create the `ListingId`, `Address`, `Name` and `Price` objects needed to instantiate an `Listing` object. The
parser returns a `AddListingCommand` object with the `Listing` object ready to be added into `Model`

Step 3. The `AddListingCommand` calls the `Model#addListing(Listing)` to add the listing and its relevant attributes.

Step 4. Depending on whether the `UniqueListingList` already contains data or is an empty list, the added Listing will
be sorted automatically by the `ListingId` in lexicographical order. This is done using the `Collections#sort()` method
and implementing a natural order by implementing the `Comparable` interface and overriding the `Comparable#compareTo()`
method.

The sequence diagram below shows the add listing process.

![addListingSequenceDiagram](images/AddListingSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for
`AddListingCommandParser` and `RealTimeParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline
reaches the end of diagram.
</div>

#### Design considerations

**Aspect: Listing-Offer interaction**

* **Alternative 1 (current choice):** Separate Listing and Offer objects
    * Pros: Easier to maintain as they are separate objects, less dependency between each other
    * Cons: `Offer` references the `ListingId` instead of `Listing` objects.
* **Alternative 2:** Allow Offers to have a Listing attribute instead
    * Pros: Easier to reference between `Offer` and `Listing`
    * Cons: Large dependency between the two.

### \[Implemented\] Delete Listing feature

The Delete Listing feature allows users to delete the specified `Listing` in the `UniqueListingList`.

#### Implementation

To delete an `Listing` object, the `DeleteListingCommand` must be executed. The `DeleteListingCommand` extends the `Command`
class.

The `DeleteListingCommand` is parsed by the `DeleteListingCommandParser`. `DeleteListingCommandParser#parse()` parses the user
input to return a `DeleteListingCommand` that will be executed.

Given below is an example usage scenario and how the delete listing mechanism behaves at each step.

Step 1. The user enters `delL 1` in the command box.  This calls the `LogicManager#execute()` method which calls the
`RealTimeParser#parse` method to be executed and will determine that the command is a `DeleteListingCommand` after parsing.

Step 2. A `DeleteListingCommandParser` object is instantiated with the necessary arguments. It will return a
`DeleteListingCommand` object with the specified `Index`.

Step 3. The `DeleteListingCommand#execute()` method is called which calls the `Model#deleteListing(Listing)` method to delete
the Listing at the specified `Index` in `Model`.

The sequence diagram below shows the delete listing process.

![deleteListingSequenceDiagram](images/DeleteListingSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for
`DeleteListingCommandParser` and `DeleteListingCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline
reaches the end of diagram.
</div>

#### Design considerations

**Aspect: Using index specific or attribute specific deletion**

* **Alternative 1 (current choice):** Index specific
    * Pros: Easier implementation and more suited for fast-typing
    * Cons: Might accidentally delete the wrong `Listing` if `Index` is mistyped
* **Alternative 2:** Attribute specific
    * Pros: User does not have to find for specific `Index`
    * Cons: Many `Listing` objects might share same attribute such as `Asking Price` and `Owner`.

### \[Implemented\] Edit Listing feature

The Edit Listing feature allows users to edit any attribute of an `Listing` in the `UniqueListingList`.

#### Implementation

To edit an `Listing`, the `EditListingCommand` is executed. The `EditListingCommand` extends the `Command` class.

The `EditListingCommand` is parsed by the `EditListingCommandParser`. `EditListingCommandParser#parse()` parses the user input
to return a `EditListingCommand` that will be executed.

Given below is an example usage scenario and how the edit listing mechanism behaves at each step.

Step 1. The user enters `editL 1 l/Bukit_timah_rd ap/1000000` in the command box. This calls the `LogicManager#execute()`
method which calls the `RealTimeParser#parse` method to be executed and will determine that the command is a
`EditListingCommand` after parsing.

Step 2. An `EditListingCommandParser` object is instantiated with the necessary arguments. It returns a `EditListingCommand`
with the `Index` and `EditListingDescriptor` as parameters. The `EditListingDescriptor` contains the edited information that
the edited `Listing` is supposed to be updated to. In this scenario, we are changing the `listingId` and `Price` attribute
of the `Listing`. The `EditListingDescriptor` is then updated through the `setListing()` and `setListingPrice()` methods by
the `EditListingDescriptor`.

Step 3. The `EditListingCommand#execute()` method is called which creates the edited `Listing` using the `createEditedListing`
method which takes in the `Listing` to be edited and the `EditListingDescriptor` that was created from the previous step.
The edited `Listing` is then checked if it is a duplicate or already exists in the `UniqueListingList` through the
`Listing#isSameListing()` and `Model#hasListing()` methods.

An `Listing` is the same if it contains the exact same `Listing Id` and `Address`.

Step 4. The `Model#setListing(Listing)` method is called to replace the target `Listing` at the specified `Index` from earlier.
The `Model#updateFilteredListingList()` is called to update the `UniqueListingList`.

### \[Implemented\] Add Meeting feature

#### Meeting
REal-Time manages **_Meetings_** with Clients.

#### Implementation

To add an `Meeting` object, the `AddMeetingCommand` must be executed. This stores the `Meeting` object into the
`UniqueMeetingList`. Following the command execution pathway, the implementation of adding listings uses the exposed
`Model#addMeeting(Meeting)` method in the `Model` API.

The `AddMeetingCommand` is parsed by the `AddMeetingCommandParser`.
`AddMeetingCommandParser#parse()` parses the user input to return a `AddMeetingCommand` object that will be executed.

Given below is an example usage scenario and how the add listings mechanism behaves at each step.

Step 1. The user opens up the `REal-Time` application for the first time and observes that there are some sample data
in the `UniqueMeetingList`.

Step 2. The user keys in the command word for adding a listing, `addM`, followed by the compulsory parameters needed to
add a listing, namely the `ListingId` component prefixed by `l/`, `Name` component prefixed by `n/`and the `Date and Time`
component prefixed by `d/`. In this scenario, the user keys in
`addM l/007 n/John Doe d/2022-10-20 12:00` into the command box
which will execute the `AddMeetingCommandParser` to check through the arguments and ensure that the compulsory fields are
present. It will then create the `ListingId`, `Name` and `LocalDateTime` objects needed to instantiate an `Meeting` object. The
parser returns a `AddMeetingCommand` object with the `Meeting` object ready to be added into `Model`

Step 3. The `AddMeetingCommand` calls the `Model#addMeeting(Meeting)` to add the listing and its relevant attributes.

Step 4. Depending on whether the `UniqueMeetingList` already contains data or is an empty list, the added Meeting will
be sorted automatically by the `LocalDateTime` in lexicographical order. This is done using the `Collections#sort()` method
and implementing a natural order by implementing the `Comparable` interface and overriding the `Comparable#compareTo()`
method.

The sequence diagram below shows the add listing process.

![addMeetingSequenceDiagram](images/AddMeetingSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for
`AddMeetingCommandParser` and `RealTimeParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline
reaches the end of diagram.
</div>

#### Design considerations

**Aspect: Meeting-Listing interaction**

* **Alternative 1 (current choice):** Separate Meeting and Listing objects
  * Pros: Easier to maintain as they are separate objects, less dependency between each other
  * Cons: `Offer` references the `ListingId` instead of `Listing` objects.
* **Alternative 2:** Allow Offers to have a Listing attribute instead
  * Pros: Easier to reference between `Listing` and `Meeting`
  * Cons: Large dependency between the two.


### \[Implemented\] Delete Meeting feature

The Delete Meeting feature allows users to delete the specified `Meeting` in the `UniqueMeetingList`.

#### Implementation

To delete an `Meeting` object, the `DeleteMeetingCommand` must be executed. The `DeleteMeetingCommand` extends the `Command`
class.

The `DeleteMeetingCommand` is parsed by the `DeleteMeetingCommandParser`. `DeleteMeetingCommandParser#parse()` parses the user
input to return a `DeleteMeetingCommand` that will be executed.

Given below is an example usage scenario and how the delete listing mechanism behaves at each step.

Step 1. The user enters `delM 1` in the command box.  This calls the `LogicManager#execute()` method which calls the
`RealTimeParser#parse` method to be executed and will determine that the command is a `DeleteMeetingCommand` after parsing.

Step 2. A `DeleteMeetingCommandParser` object is instantiated with the necessary arguments. It will return a
`DeleteMeetingCommand` object with the specified `Index`.

Step 3. The `DeleteMeetingCommand#execute()` method is called which calls the `Model#deleteMeeting(Meeting)` method to delete
the Meeting at the specified `Index` in `Model`.

The sequence diagram below shows the delete listing process.

![deleteMeetingSequenceDiagram](images/DeleteMeetingSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for
`DeleteMeetingCommandParser` and `DeleteMeetingCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline
reaches the end of diagram.
</div>

#### Design considerations

**Aspect: Using index specific or attribute specific deletion**

* **Alternative 1 (current choice):** Index specific
  * Pros: Easier implementation and more suited for fast-typing
  * Cons: Might accidentally delete the wrong `Meeting` if `Index` is mistyped
* **Alternative 2:** Attribute specific
  * Pros: User does not have to find for specific `Index`
  * Cons: Many `Meeting` objects might share same attribute such as `Listing Id` and `Name`.

### \[Implemented\] Edit Meeting feature

The Edit Meeting feature allows users to edit any attribute of an `Meeting` in the `UniqueMeetingList`.

#### Implementation

To edit an `Meeting`, the `EditMeetingCommand` is executed. The `EditMeetingCommand` extends the `Command` class.

The `EditMeetingCommand` is parsed by the `EditMeetingCommandParser`. `EditMeetingCommandParser#parse()` parses the user input
to return a `EditMeetingCommand` that will be executed.

Given below is an example usage scenario and how the edit listing mechanism behaves at each step.

Step 1. The user enters `editM 1 l/Bukit_timah_rd d/2022-10-20 23:59` in the command box. This calls the `LogicManager#execute()`
method which calls the `RealTimeParser#parse` method to be executed and will determine that the command is a
`EditMeetingCommand` after parsing.

Step 2. An `EditMeetingCommandParser` object is instantiated with the necessary arguments. It returns a `EditMeetingCommand`
with the `Index` and `EditMeetingDescriptor` as parameters. The `EditMeetingDescriptor` contains the edited information that
the edited `Meeting` is supposed to be updated to. In this scenario, we are changing the `listingId` and `Time` attribute
of the `Meeting`. The `EditMeetingDescriptor` is then updated through the `setMeeting()` and `setMeetingPrice()` methods by
the `EditMeetingDescriptor`.

Step 3. The `EditMeetingCommand#execute()` method is called which creates the edited `Meeting` using the `createEditedMeeting`
method which takes in the `Meeting` to be edited and the `EditMeetingDescriptor` that was created from the previous step.
The edited `Meeting` is then checked if it is a duplicate or already exists in the `UniqueMeetingList` through the
`Meeting#isSameMeeting()` and `Model#hasMeeting()` methods.

An `Meeting` is the same if it contains the exact same `LocalDateTime`.

Step 4. The `Model#setMeeting(Meeting)` method is called to replace the target `Meeting` at the specified `Index` from earlier.
The `Model#updateFilteredMeetingList()` is called to update the `UniqueMeetingList`.

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

**Target user profile**: Real Estate Agents

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value propositions**:
* manage clients faster than a typical mouse/GUI driven app
* Real-Estate agents with many clients may struggle to juggle their viewings and manage meetings.
* Track properties to their owners as well as potential buyers
* Centralised place to store client information, what they are looking for


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​            | So that I can…​                                                        |
|----------|--------------------------------------------|-------------------------|------------------------------------------------------------------------|
| `* * *`  | new user                                   | see usage instructions  | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new client        | see all my clients in one list                                         |
| `* * *`  | user                                       | add a new listing       | see all my listings in one list                                        |
| `* * *`  | user                                       | add my client's offer   | track all my clients' current offers                                   |
| `* * *`  | user                                       | add my client's meeting | track all my meetings with clients                                     |
| `* * *`  | user                                       | delete a client         | remove entries that I no longer need                                   |
| `* * *`  | user                                       | delete a listing        | remove entries that I no longer need                                   |
| `* * *`  | user                                       | delete an offer         | remove entries that I no longer need                                   |
| `* * *`  | user                                       | delete a meeeting       | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a client by name   | locate details of persons without having to go through the entire list |
| `* * *`  | user                                       | edit an client          | update any changes to a client's details                               |
| `* * *`  | user                                       | edit an listing         | update any changes to a listing's details                              |
| `* * *`  | user                                       | edit an offer           | update any changes to a client's offer                                 |
| `* * *`  | user                                       | edit an meeting         | update any changes to a client's meeting                               |
| `*`      | user with many clients in the address book | sort client by name     | locate a person easily                                                 |
| `*`      | user                                       | save my data            | store my data for future use                                           |


*{More to be added}*

### Use cases


(For all use cases below, the **System** is the `RealTime` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Reorder the list**

**MSS**

1.  User requests to list persons
2.  RealTime shows a list of persons
3.  User requests to switch a specific person in the list with another
4.  RealTime swaps their index

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.
    * 3a1. RealTime shows an error message.

      Use case resumes at step 2.


**Use case: UC02 - Delete a person**

**MSS**

1.  User requests to list persons
2.  RealTime shows a list of persons
3.  User requests to delete a specific person in the list
4.  RealTime deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.
    * 3a1. RealTime shows an error message.

      Use case resumes at step 2.

**Use case: UC03 - Add an offer**

**MSS**
1. User requests to add an offer.
2. RealTime adds the offer.

    Use case ends.

**Extensions**

* 1a. User enters invalid input
  * 1a1. RealTime shows an error message.

    Use case resumes at Step 1.

**Use case: UC04 - Edit an offer**

**MSS**
1. User requests to edit an offer.
2. REal-Time edits the offer.

    Use case ends.

**Extensions**
* 1a. User enters invalid input
  * 1a1. REal-Time shows an error message.
    Use case resumes at Step 1.

**Use case: UC05 - Delete an offer**

**MSS**
1. User requests to delete an offer.
2. REal-Time deletes the offer.

    Use case ends.

**Extensions**
* 1a. User enters invalid input
  * 1a1. REal-Time shows an error message.

    Use case resumes at Step 1

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Application should start in under a few seconds.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Client**: A person that is interested in purchasing or selling a property
* **Offer**: A price that the purchaser is willing to pay for a property
* **Listing**: A property that is being advertised
* **Meeting**: A scheduled appointment between the Real-Estate agent and client

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

### Adding a Listing

1. Adding a listings

    1. Prerequisites: The owner of the Listing must already be added as a Person.
    2. Test case: `listing id/001 a/100 Charming Avenue n/Alex Yeoh ap/100000`<br>
       Expected: First a new Listing is created. Its id will be the contents after the 'id/' prefix.
                 Its Address will be the contents after the 'a/' prefix. Its owner's name
                 will be the contents after the 'n/' prefix. And the asking price of the Listing
                 will be the contents after the 'ap/' prefix.
                 Details of the new listing will be shown in the status message.
    3. Other incorrect delete commands to try: `listing id/... ap/.. n.. a/..`, `listing`, `...` <br>
        Expected: Error message to warn incorrect input format.
2. _{ more test cases …​ }_

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


### Deleting a client

1. Deleting a person while all client are being shown

   1. Prerequisites: List all client using the `list` command. Multiple client in the list.
   2. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
Timestamp in the status bar is updated.
   3. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.
   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.


### Adding an Offer

1. Adding an offer while all offers are being shown
   1. Prerequisites: The Offer being added must not exist in the current list.
   2. Test case: `addO n/John Doe l/BEDOK_SOUTH o/700000`
   
      Expected: An Offer with name John Doe, listing ID BEDOK_SOUTH and offer price of 700000 will be added to the list.

   3. Test case: `addO n/Jackson Ang l/CALIFORNIA o/-10000`

      Expected: No Offer will be added. Error message will appear in the response box.
   4. Other incorrect commands to try: `addO n/Bruce Wayne l/YISHUN o/123p123`

### Deleting an Offer

1. Deleting an offer while all offers are being shown
   1. Prerequisites: At least one Offer has to exist in the list.
   2. Test case: `delO 1`

      Expected: First Offer is deleted from the list. Details of the deleted offer shown in the status message.
   3. Test case: `delO 0`

      Expected: No Offer is deleted. Error details shown in the status message.
   4. Other incorrect commands to try: `delO -1`, `delO a`

### Editing an Offer

1. Editing an offer while all offers are being shown
   1. Prerequisites: At least one Offer must exist to be edited.
   2. Test case: `editO 1 n/John Doe`<br>
      Expected: First Offer has the name changed to John Doe.
   3. Test case: `editO 1 o/-1000`<br>
      Expected: No Offer is edited. Error details shown in the status message.


### Adding a Meeting

1. Adding a meeting while all meeting are being shown
    1. Prerequisites: The Meeting being added must not exist in the current list.
    2. Test case: `addM n/John Doe l/BEDOK_SOUTH d/2022-10-12 23:59`

       Expected: A Meeting with name John Doe, listing ID BEDOK_SOUTH and dateTime 2022-10-12 23:59 will be added to the list.

    3. Test case: `addM n/Jackson Ang l/CALIFORNIA d/23:59 2022-12-3`

       Expected: No Meeting will be added. Error message will appear in the response box.
    4. Other incorrect commands to try: `addM n/Bruce Wayne l/YISHUN d/2022-100-100 27:20`

### Deleting a Meeting

1. Deleting a meeting while all meetings are being shown
    1. Prerequisites: At least one Meeting has to exist in the list.
    2. Test case: `delM 1`

       Expected: First Meeting is deleted from the list. Details of the deleted meeting shown in the status message.
    3. Test case: `delM 0`

       Expected: No Meeting is deleted. Error details shown in the status message.
    4. Other incorrect commands to try: `delM -1`, `delM a`

### Editing a Meeting

1. Editing a meeting while all meetings are being shown
    1. Prerequisites: At least one Meeting must exist to be edited.
    2. Test case: `editM 1 n/John Doe`<br>
       Expected: First Meeting has the name changed to John Doe.
    3. Test case: `editM 1 d/2023-30-20 12:00`<br>
       Expected: No Meeting is edited. Error details shown in the status message.
