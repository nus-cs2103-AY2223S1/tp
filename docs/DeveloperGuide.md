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
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.)

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

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

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

### Find feature

#### Current Implementation

The find mechanism is facilitated by `FindCommandParser` which implements `Parser`. It parses the user input and 
returns a `FindCommand` object. The `FindCommand` object then calls the `Model#updateFilteredPersonList()` method to 
update the list of persons shown to the user.

The find mechanism has two modes: generic and prefix-based. 
- The generic mode is used when the user does not specify any prefix.
- The prefix-based mode is used when the user specifies at least one prefix.

Both modes use the `DetailsContainKeywordsPredicate` class to filter the person list, which uses the 
`containsKeywordsIgnoreCase` method to check if the person's details contain the keywords.

Given below is an example usage scenario of the generic mode and how the find mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `AddressBook` will be initialized with the initial 
address book state, and the `filteredPersons` will be initialized to show all persons.

Step 2. The user executes `find Betsy` command to find the person named `Betsy` in the address book. The `find` command 
calls `FindCommandParser#parse()` which will parse the command.

Step 3. Since this is a generic find command, `FindCommandParser` trims the user input and creates a 
`DetailsContainsKeywordsPredicate` object with the trimmed user input. The `DetailsContainsKeywordsPredicate` object is
then passed to a newly created `FindCommand` object.

Step 4. The `FindCommand` object calls the `Model#updateFilteredPersonList()` method with the
`DetailsContainsKeywordsPredicate` object as the argument. The `Model#updateFilteredPersonList()` method will then 
update the `filteredPersons` list in `ModelManager` to show only persons that matches the predicate. 
<div markdown="span" class="alert alert-info">:information_source: **Note:** The `DetailsContainsKeywordsPredicate` 
object will check if the person's details contains the keywords using the `containsKeywordsIgnoreCase` method in its 
`test` method.

</div>

Given below is an example usage scenario of the prefix-based mode and how the find mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `AddressBook` will be initialized with the initial
address book state, and the `filteredPersons` will be initialized to show all persons.

Step 2. The user executes `find n/Betsy` command to find the person named `Betsy` in the address book. The `find` 
command calls `FindCommandParser#parse()` which will parse the command.

Step 3. Since this is a prefix-based find command, `FindCommandParser` uses `ArgumentTokenizer` to tokenize the user
input. `ArgumentTokenizer` will then find the prefixes in the user input and put the keywords in the respective
prefixes into a map. `FindCommandParser` will then create a `DetailsContainsKeywordsPredicate` object with the keywords
in the map. The `DetailsContainsKeywordsPredicate` object is then passed to a newly created `FindCommand` object.

Step 4. The `FindCommand` object calls the `Model#updateFilteredPersonList()` method with the
`DetailsContainsKeywordsPredicate` object as the argument. The `Model#updateFilteredPersonList()` method will then
update the `filteredPersons` list in `ModelManager` to show only persons that matches the predicate.

<div markdown="span" class="alert alert-info">:information_source: **Note:** For prefix-based find command, the
`DetailsContainsKeywordsPredicate` object will check if the person's specified details based on the prefixes contains
the keywords using the `containsKeywordsIgnoreCase` method in its `test` method.

</div>

If the user input is invalid, `FindCommandParser#parse()` will throw a `ParseException` with the respective error. This
will give the user the correct syntax to use the find command.

The following sequence diagram shows how the find operation works:

<img src="images/FindSequenceDiagram.png" width="550" />

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes a find command:

![FindActivityDiagram](images/FindActivityDiagram.png)

#### Design Considerations

##### Aspect: How find executes:

* **Alternative 1 (current choice):** One `FindCommand` class and `COMMAND_WORD` that handles both generic and 
prefix-based find.
    * Pros: Less code duplication.
    * Pros: More user-friendly.
    * Cons: More complicated logic in `FindCommandParser` class.
    * Cons: Greater difficulty in implementing (more cases to consider).
* **Alternative 2:** One `FindCommand` class and `COMMAND_WORD` that handles generic find and multiple find classes
that handle prefix-based find e.g. `FindNameCommand`, `FindPhoneCommand`, `FindEmailCommand`.
    * Pros: Easier to implement.
    * Pros: Less complicated logic in `FindCommandParser` class.
    * Cons: More code duplication (have to create new classes for each prefix that are almost identical).
    * Cons: We must ensure that the implementation of each individual command are correct.
    * Cons: Less user-friendly.
    * Cons: User must remember the different commands for each prefix.



### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

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

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### \[Implemented\] Add/Edit/Delete Tags Feature

#### Implementation

The addition, modification and deletion of tags are executed through `AddCommand`, `AddTagCommand`, `DeleteTagCommand`, `EditCommand`, and facilated by `Tag`, `TagType`, `UniqueTagTypeMap` and `UniqueTagList`.

Each candidate in the `UniquePersonList` has a `UniqueTagTypeMap`, which represents a map of the tag types and corresponding tags belonging to the person. `UniqueTagTypeMap` implements the following operations:

* `UniqueTagTypeMap#mergeTagTypeMap()` — Merges a `UniqueTagTypeMap` with the existing `UniqueTagTypeMap` of the candidate.
* `UniqueTagTypeMap#removeTags()` — Removes the tags from the `UniqueTagTypeMap` of the candidate.
* `UniqueTagTypeMap#mergeTag()` — Adds a tag of the given tag type to the `UniqueTagTypeMap` of the candidate.
* `UniqueTagTypeMap#setTagTypeMap()` — Replaces the `UniqueTagTypeMap` of the candidate with the given `UniqueTagTypeMap`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The `UniqueTagTypeMap` internally uses an `ObservableMap`, backed by a `HashMap`, that maps each tag type of the candidate to a `UniqueTagList` of tags.
</div>

Given below is an example usage scenario and how the add/edit/delete Tag mechanism behaves at each step:

Step 1. The user executes `addTag 3 st/Java dt/Bachelors` to add a skill tag `Java` and degree tag `Bachelors` to the 3rd candidate in the displayed list of candidates. After being parsed by the `AddTagCommandParser` to an `AddTagCommand`, the `AddTagCommand` initializes a new `UniqueTagTypeMap` with the existing `UniqueTagTypeMap` of candidate `3` by invoking the `UniqueTagTypeMap#setTagTypeMap()`, and merges the new tags by invoking `UniqueTagTypeMap#mergeTagTypeMap()`. After this, a new `Person` is created with the `updatedTags` and all other attributes same as that of the existing `Person`, and the `ModelManager#setPerson` is invoked to modify the addressBook with the updated candidate.

Step 2. The user executes `edit 3 st/Java-JavaScript` to edit the skill tag `Java` to `JavaScript` of the 3rd candidate in the displayed list of candidates. After being parsed by the `EditCommandParser` to an `EditCommand`, the `EditCommand` initializes a new `UniqueTagTypeMap` with the existing `UniqueTagTypeMap` of candidate `3` by invoking the `UniqueTagTypeMap#setTagTypeMap()`, removes the existing tags to be edited by invoking `UniqueTagTypeMap#removeTags()` and adds the edited tags by invoking the `UniqueTagTypeMap#mergeTagTypeMap()`. After this, a new `Person` is created with the `updatedTags` and all other attributes same as that of the existing `Person`, and the `ModelManager#setPerson` is invoked to modify the addressBook with the updated candidate.

Step 3. The user executes `deleteTag 3 st/JavaScript dt/Bachelors` to delete the skill tag `JavaScript` and degree tag `Bachelors` of the 3rd candidate in the displayed list of candidates. After being parsed by the `DeleteTagCommand` to a `DeleteTagCommand`, the `DeleteTagCommand` initializes a new `UniqueTagTypeMap` with the existing `UniqueTagTypeMap` of candidate `3` by invoking the `UniqueTagTypeMap#setTagTypeMap()` and removes the tags to be deleted by invoking `UniqueTagTypeMap#removeTags()`. After this, a new `Person` is created with the `updatedTags` and all other attributes same as that of the existing `Person`, and the `ModelManager#setPerson` is invoked to modify the addressBook with the updated candidate.

#### Design Considerations:

**Aspect: How the addressBook is updated:**

* **Alternative 1 (current choice):** Creates a new person each time a tag is added, edited or deleted.
    * Pros: Ensures that `Person` and all its attributes are immutable.
    * Cons: May be inefficient compared to directly updating the attributes of `Person`.

* **Alternative 2:** Modifies the existing `UniqueTagTypeMap` of the candidate each time a tag is added, edited or deleted.
  itself.
    * Pros: May be more efficient, as a new `Person` instance is not created every time a tag is added, edited or deleted.
    * Cons: Allowing `Person` to be mutated may not be safe and defensive.


### \[Implemented\] Create/Edit/Delete Tag Types feature  

For the ease of classifying tags and storing candidate information in a more organised way, users can now also create Tag Types and assign tags to the relevant Tag Types.

This feature is facilitated by `UniqueTagTypeMap` class. It implements the `Iterable<TagType>` interface.  
Additionally, it implements the following operations:  

* UniqueTagTypeMap#createTagType()  —  Creates a unique Tag Type and adds it to the prefixMap of available Tag Types.
* UniqueTagTypeMap#setExistingTagType()  —  Edits the Tag Type name and Tag Type alias of an existing Tag Type.
* UniqueTagTypeMap#removeExistingTagType()  —  Deletes a Tag Type from the prefixMap and, hence, the Tag Type is no more recognised as a valid Tag Type.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The `prefixMap` in `UniqueTagTypeMap` is a HashMap that maps all the existing Tag Type alias to their respective Tag Types. For user convenience, we have already provided the user with the three basic Tag Types a recruiter might need: Skills Tag Type (alias: st/), Degree Tag Type (alias: dt/), and Job Type Tag Type (alias: jtt/)<br>
</div>

Given below is an example usage scenario and how the create/edit/delete Tag Types mechanism behaves at each step:  

Step 1. The user launches the application for the first time. The `prefixMap` in the `UniqueTagTypeMap` will be initialised with the initial 3 Key — Value pairs: st/ — Skills, dt/ — Degree, and jtt/ — Job Type.

Step 2. The user executes `createTagType Grade grdt` command to add a Grade Tag Type to the available Tag Types so that the recruiter can now add Tags of Grade Tag Type to candidates using the alias grdt/. The `createTagTYpe` command calls the `UniqueTagTypeMap#createTagType()`, causing the addition of grdt/ — Grade key-value pair to the `prefixMap`.

Step 3. The user executes the 'editTagType Grade-Score grdt-scrt' to edit the existing Tag Type Grade and rename it as Score. The `editTagType` command calls the `UniqueTagTypeMap#setExistingTagType()` to remove the grdt/ — Grade key-value pair from the `prefixMap` and add scrt/ — Score key-value pair to the `prefixMap`. Furthermore, the `editTagType` command also calls 'Model#editTagTypeForAllPerson()' to edit the Grade Tag Type name and rename it as Score Tag Type for all person who had Tags of Grade Tag Type.

Step 4. The user executes the `deleteTagType Score` to delete the Score Tag Type and all Tags of Score Tag Type for all person in CLInkedIn. The `deleteTagType` command calls the `UniqueTagTypeMap#removeExistingTagType()` to remove the scrt/ — Score key-value pair from the `prefixMap`. Furthermore, it also calls the `Model#deleteTagTypeForAllPerson()` to delete the Score Tag Type and the Tags assigned to the Score Tag TYpe for each person having Tags of Score Tag Type.

### \[Implemented\] Status feature

#### Implementation 

The proposed `Status` feature is added as an attribute under the `Person` class. 

A `Status` class is created, and is implemented via a `String`. The String can only take in alphanumeric inputs. 

The `Status` attribute is mainly implemented by the following methods: 
- `Status` can be added via the `AddCommand` 
- `Status` can be edited via the `EditCommand`.

It is also additionally facilitated by these methods:
- `AddCommandParser#parse()` - Checks the input for the status prefix, only adds a candidate into CLInkedIn if the entry has a `Status` prefix and a valid `Status` input
- `AddressBookParser#parseCommand()` - Checks the input for `AddCommand` or `EditCommand`

Here is an example of what happens when the recruiter attempts to add a candidate to CLInkedIn:
1. Recruiter enters the command `add n/John Doe p/999 e/john@mail/com a/singapore s/Application Received`
2. The command is first parsed by `AddressBookParser#parseCommand()`, which identifies the command word of every command. 
3. Since this is an `AddCommand`, the remaining arguments are passed into `AddCommandParser#parse()`
4. Each of the different arguments of a candidate (name, phone, email, address, status) are parsed by `AddCommandParser#parse()`
5. If any of the compulsory arguments of a candidate (name, phone, email, address, status) are not present, the command will fail its execution and `ParseException` will be thrown. 
6. Next, the `AddCommand#execute()` is called, which triggers the `Model#addPerson(Person)` command and a `CommandResult` is returned 

Here is an example of what happens when the recruiter attempts to edit a candidate's status  CLInkedIn:
1. Recruiter enters the command `edit 1 s/OA In Progress`
2. The command is first parsed by `AddressBookParser#parseCommand()`, which identifies the command word of every command.
3. Since this is an `EditCommand`, the remaining arguments are passed into `EditCommandParser#parse()`
4. Each of the different arguments to be edited are parsed by `EditCommandParser#parse()`.
5. An `EditPersonDescriptor` is created and modified depending on the arguments to be edited.
6. An `EditCommand` object is generated. 
7. Next, the `EditCommand#execute()` is called, which triggers the `Model#setPerson(Person)` and `Model#updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS)` commands 
8. A `CommandResult` is returned.

#### Design Considerations 

It is designed to be a mandatory feature, as every candidate under the recruiting process must be at an application stage.

### \[Implemented\] Note feature

#### Implementation 

The proposed `Note` feature is added as an optional attribute under the `Person` class. 

A `Note` class is created, and is implemented via a `String`. The String can take in any input, including a blank string. 

The `Note` attribute is mainly implemented by the following methods: 
- `Note` can be added via the `AddCommand` 
- `Note` can be edited via the `EditCommand`.

It is also additionally facilitated by these methods:
- `NoteCommandParser#parse()` - Checks the input for the Note prefix, only adds a candidate into CLInkedIn if the entry has a `Note` prefix and a valid `Note` input
- `AddressBookParser#parseCommand()` - Checks the input for `AddCommand` or `EditCommand`

Here is an example of what happens when the recruiter attempts to add a candidate to CLInkedIn:
1. Recruiter enters the command `add n/John Doe p/999 e/john@mail/com a/singapore note/Strong in Python.`
2. The command is first parsed by `AddressBookParser#parseCommand()`, which identifies the command word of every command. 
3. Since this is an `AddCommand`, the remaining arguments are passed into `AddCommandParser#parse()`
4. Each of the different arguments of a candidate (name, phone, email, address, Status) are parsed by `AddCommandParser#parse()`
5. If any of the compulsory arguments of a candidate (name, phone, email, address, Status) are not present, the command will fail its execution and `ParseException` will be thrown. 
6. Next, the `AddCommand#execute()` is called, which triggers the `Model#addPerson(Person)` command and a `CommandResult` is returned 

Here is an example of what happens when the recruiter attempts to edit a candidate's Note  CLInkedIn:
1. Recruiter enters the command `edit 1 note/Missed 2 interviews`
2. The command is first parsed by `AddressBookParser#parseCommand()`, which identifies the command word of every command.
3. Since this is an `EditCommand`, the remaining arguments are passed into `EditCommandParser#parse()`
4. Each of the different arguments to be edited are parsed by `EditCommandParser#parse()`.
5. An `EditPersonDescriptor` is created and modified depending on the arguments to be edited.
6. An `EditCommand` object is generated. 
7. Next, the `EditCommand#execute()` is called, which triggers the `Model#setPerson(Person)` and `Model#updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS)` commands 
8. A `CommandResult` is returned.

#### Design Considerations 

It is designed to be an optional feature, as it is meant to be a supplementary source of notetaking that recruiters can make on candidates saved.

### \[Implementing\] Rating feature

The proposed `Rating` feature is added as an attribute under the `Person` class.

A `Rating` class is created, and is implemented via a `String`. The String can only take in integers from 1 to 10 inclusive.

The `Rating` attribute is mainly implemented by the following methods:
- `Rating` can be added via the `RateCommand`

It is also additionally facilitated by these methods:
- `RateCommandParser#parse()` - Checks the input for the rating prefix, only adds the rating to the candidate if the entry has a `Rating` prefix and a valid `Rating` input
- `AddressBookParser#parseCommand()` - Checks the input for `RateCommand`.

Here is an example of what happens when the recruiter attempts to add a rating to a candidate on CLInkedIn:
1. Recruiter enters the command `rate 4 rate/8`
2. The command is first parsed by `AddressBookParser#parseCommand()`, which identifies the command word of every command.
3. Since this is a `RateCommand`, the remaining arguments are passed into `RateCommandParser#parse()`
4. The different arguments (index, rating) are parsed by `RateCommandParser#parse()` and a `RateCommand` object is created.
5. Next, the `RateCommand#execute()` is called, which triggers the `Model#setPerson(Person)` and `Model#updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS)` commands
6. A `CommandResult` is returned.

#### Design Considerations 

#### Aspect: Compulsory vs Non-compulsory
It is designed to be a non-compulsory feature, as the recruiter might not be able to rate every candidate at every stage of the recruiting process.

#### Aspect: Argument type of the `Rating` constructor 
It is designed to take in a String, as Commands are parsed as a String. However, the constructor will parse the String and the Rating is stored as an Integer. 

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
Recruting and hiring managers.

**Value proposition**: 
Manage candidates using a CLI faster than mouse/GUI driven apps. Make use of Skill, Degree, Job Type, or any other custom tags and rating system to filter candidates for the next hire.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority   | As a …​                                     | I can …​                                                                                          | So that I can…​                                                                     |
|------------|---------------------------------------------|---------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|
| `* * *`    | new user                                    | see usage instructions                                                                            | refer to instructions when I forget how to use the App                              |
| `* * *`    | user                                        | add a new person                                                                                  |                                                                                     |
| `* * *`    | user                                        | delete a person                                                                                   | remove entries that I no longer need                                                |
| `* * *`    | user                                        | find a person by name                                                                             | locate details of persons without having to go through the entire list              |
| `* *`      | user                                        | hide private contact details                                                                      | minimize chance of someone else seeing them by accident                             |
| `*`        | user with many persons in the address book  | sort persons by name                                                                              | locate a person easily                                                              |
| `* * *`    | user                                        | use the help command                                                                              | view the command summary                                                            |
| `* * *`    | user                                        | check the total number of candidates                                                              | keep a track of the number of applicants.                                           |                                                      
| `* * *`    | user                                        | add tags to existing or new candidates                                                            | categorise candidates.                                                              |                                                   
| `* * *`    | user                                        | add a Skill type tag                                                                              | keep a track of all the relevant skills of candidates.                              |                                                  
| `* * *`    | user                                        | add a Degree type tag                                                                             | keep track of the highest level of education of candidates. - degree                |                                          
| `* * *`    | user                                        | add a Job Type type tag                                                                           | view which candidates are applying for internships/full-time/part-time/temporary.   |                         
| `* * *`    | user                                        | remove tags                                                                                       | remove attributes that are no more relevant.                                        |                        
| `* * *`    | user                                        | edit tags                                                                                         | update attributes with the most relevant information.                               |                        
| `* * *`    | user                                        | add the application status of candidates                                                          | keep a track of a candidate’s application progress.                                 |                      
| `* * *`    | user                                        | update the application status of candidates                                                       | advance a candidate to another status.                                              |                      
| `**`       | user                                        | add a rating to candidates                                                                        | quantify the merit of a candidate.                                                  |                    
| `**`       | user                                        | delete the rating of candidates                                                                   | remove wrong/unwanted ratings.                                                      |                   
| `**`       | user                                        | edit the rating of candidates                                                                     | update the ratings of candidates.                                                   |                    
| `**`       | user                                        | sort candidates based on their rating                                                             | view candidates based on the order of desirability.                                 |                     
| `* * *`    | user                                        | search for candidates using keywords                                                              | view the candidates that meet a specific criteria.                                  |                   
| `* * *`    | user                                        | search candidates based on tags                                                                   | view candidates based on their skills, level of education, etc.                     |                    
| `* * *`    | user                                        | search candidates based on ratings                                                                | filter candidates based on their suitability.                                       |                     
| `* * *`    | user                                        | search candidates based on their location                                                         | view eligible candidates for a particular region/country.                           |                     
| `* * *`    | user                                        | search candidates based on application status                                                     | view all candidates with the same application status.                               |                   
| `* * *`    | user                                        | add optional notes/comments for candidates                                                        | keep track of additional information.                                               |                  
| `*`        | user                                        | add links to LinkedIn profiles, Github profiles or Personal websites of candidates                | keep track of more information about candidates.                                    |
| `*`        | user                                        | copy links of LinkedIn profiles, Github profiles or Personal websites of candidates to clipboard  | easily open these links.                                                            | 
| `**`       | user                                        | search for candidates based on a combination of multiple criteria                                 | view candidates that meet a particular set of criteria.                             |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `CLInkedIn` and the **Actor** is the `user`, unless specified otherwise)


**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  CLInkedIn shows a list of persons
3.  User requests to delete a specific person in the list
4.  CLInkedIn deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. CLInkedIn shows an error message.

      Use case resumes at step 2.


**Use case: Search for candidates**

**MSS**

1. User requests to search for candidates using keywords like candidate name, location, application status, tags, etc.
2. CLInkedIn shows a list of candidates with details matching the search keywords.

    Use case ends.

**Use case: Modify Candidate details**

**MSS**

1. User requests to list persons.
2. AddressBook shows a list of persons.
3. User requests to edit the name, location, application status, tags, etc. of a specific candidate in the list.
4. CLInkedIn updates the details of the corresponding candidate.  

   Use case ends.

**Extensions**

* 2a. The list is empty.  

   Use case ends.

* 3a. CLInkedIn detects that the candidate does not exist in the list.

    * 3a1. CLInkedIn requests for a valid command.
    * 3a2. User enters new command.  
      Steps 3a1-3a2 are repeated until the command entered is valid.  
      Use case resumes from step 4.

* 3b. User requests to edit an invalid tag of a candidate.

    * 3b1. CLInkedIn requests to enter a valid tag.
    * 3b2. User enters new command.  
      Steps 3b1-3b2 are repeated until the tag entered is valid.  
      Use case resumes from step 4.


**Use case: Create a custom tag type**

**MSS**

1.  User requests to create a custom tag type with a specified tag alias.
2.  CLInkedIn creates the custom tag type with the specified tag alias.

    Use case ends.

**Extensions**

* 1a. CLInkedIn detects that the custom tag already exists.
    * 1a1. CLInkedIn shows an error message and requests user to re-enter the tag type and tag alias.
    * 1a2. User enters new tag type and tag alias.  
      Steps 1a1-1a2 repeats until the user enters a valid tag type.  
      Use case resumes at step 2.
* 1b. CLInkedIn detects that the tag alias already exists.
    * 1b1. CLInkedIn shows an error message and requests user to re-enter the tag alias.
    * 1b2. User enters a new tag alias.  
      Steps 1b1-1b2 repeats until the user enters a valid tag alias.  
      Use case resumes at step 2.


    
*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. User interface should be intuitive for a novice who has never used a command-line address book system. 
5. This product is offered as a free desktop service.
*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Candidate**: An entry in CLInkedIn
* **Personal Information**: Phone number, email, and address of candidate 
* **Status**: Stage of recruiting process. A chronological order would be Pending Application, Application Received, Passed ATS, OA In Progress, OA Submitted, Pending Interview, Interview Completed and the possible outcomes are Hired and Rejected.
* **Skill Tag Type**: A type of tag that comprises of all tags of skills like Java, ReactJS, UI/UX, etc.
* **Degree Tag Type**: A type of tag that comprises of tags of the degrees achieved by the candidate like Bachelors, Masters, PHD, etc.
* **Job Type Tag Type**: A type of tag that comprises of tag of candidates selected job type like Intern, Part-time, Full-time, etc.
* **Rating**: Suitability of candidate from 1 (lowest, does not fit role at all) to 5 (highest, perfect fit for the role)
* **Notes**: Additional information about candidate
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
