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

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `MainPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

`MainPanel` are the container of different panels listed below

- `HelpPanel`: It is a help panel that can only be accessed while user at `PersonListPanel`
- `PersonListPanel`
- `DetailPanel`
- `DetailHelpPanel`: It is a separate help panel that can be accessed while user at `DetailPanel`

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
* stores a seperate _sorted_ list which is expose to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed'.
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

### Set Command

#### Implementation
The Set Command makes use of the following classes:

* `SetCommand` — Updates the `Person` object in the model.
* `SetCommandParser` — Parses the arguments supplied by the user.
* `SetPersonDescriptor` — Contains the updated information from the user.

In particular, `SetPersonDescriptor` contains the following fields

* `name`
* `address`
* `tags`
* `role`
* `timezone`
* `user`
* `contacts`

As well as getters and setters for each.

Given below is an example of a usage scenario and what each class does at each step.

Step 1: The user enters the `ContactDetailPage` of a previously added `Person`.

Step 2: The user executes `set name/Bob role/DevOps`. `SetCommandParser` parses the arguments and
creates a `SetPersonDescriptor` containing the given values for phone and email, if they are valid inputs. A`SetCommand`
instance containing that `SetPersonDescriptor` will then be created. When executed, the currently selected `Person`'s
attributes will be updated.

The sequence diagram below illustrates the process of the Set Command works.
![SetSequenceDiagram](images/SetSequenceDiagram.png)

The below activity diagram highlights what happens when a user uses the `set` command.

![SetActivityDiagram](images/SetActivityDiagram.png)

#### Design considerations:

**Aspect: How data is used to update the person's information:**

* **Alternative 1 (current choice):** Store inside a `SetPersonDescriptor` object.
    * Pros: 
      * Abstracts out the data transfer process, making it easily extensible.
      * Handles optional arguments, allowing users to only input the attributes that they wish to set.
      * Improves cohesion of the codebase.
    * Cons:
      * Slightly more complicated to implement.
      * Possibly more space usage.

* **Alternative 2:** Transfer all data directly.
    * Pros: Easier to implement.
    * Cons: Difficult to extend.


### Find Command

#### Implementation
The Find Command makes use of the following classes:

* `FindCommand` — Finds and lists the `Person` objects in the model whose attributes match the given keyword.
* `FindCommandParser` — Parses the arguments supplied by the user.
* `PersonMatchesKeywordsPredicate` — Tests that a `Person` matches any of the given keywords.

`PersonMatchesKeywordsPredicate` encapsulates the keywords the user enters. 
FindCommand then uses the test method of the Predicate interface to filter contacts one at a time.

`PersonMatchesKeywordsPredicate` tests for the following fields

* `name`
* `address`
* `tags`
* `role`
* `user`

Find command performs an `OR` search for keywords within fields of the users. e.g. `find xyz` can return two persons,
one with the tag `xyz` and one with the role `xyz`.

Given below is an example of a usage scenario and what each class does at each step.

Step 1: The user types and enters the command `find John Doe`.

Step 2: The command will be parsed by the `AddressBook#parseCommand()` which returns a `FindCommandParser`, 
which also creates a `PersonMatchesKeywordsPredicate` object.

Step 3: `FindCommandParser` will parse the `John Doe` using the `parse(args)` method, which trims the keywords
entered by the user and then sets the `keywords` of the `PersonMatchesKeywordsPredicate` object to the 
trimmed keyword arguments.

Step 4: `FindCommandParser` then creates a `FindCommand` by passing the 
`PersonMatchesKeywordsPredicate` to its constructor.

Step 5: The `FindCommand` will then be executed using `execute(model)` method.

Step 6:  The `filterPersonList(predicate)` method will be called and the list of persons 
will be filtered according to the `PersonMatchesKeywordsPredicate`. This list would then be sorted (by name). 
The persons for which any of the tested attributes match `John Doe` will be in the list.

Step 7: A `CommandResult` will be returned.

Step 8: The list of contacts will then be displayed to the user.

The sequence diagram below illustrates the process of how the Find Command works.
![FindCommandSequenceDiagram](images/FindCommandSequenceDiagram.png)

#### Design considerations:

* **Alternative 1 (current choice):** Use a `PersonMatchesKeywordsPredicate` object which tests for each attribute one
 at a time.
    * Pros:
        * Reduces the amount of code we have to write as we build on the previous Predicate class, and test for more
          attributes the same way.
        * Allows us to implement fuzzy search which checks for text similarity, but with the entire value of the 
          attribute only.
        * Easily extendable for future enhancements of find command and when more fields are added to contacts.
           The `Predicate` class just needs to accommodate one more field.
    * Cons:
        * Only one attribute can be tested using a single find search.
        * The fuzzy search does not work if the full value of the attribute (with spelling errors) is not 
           entered for the most part.

* **Alternative 2:** Test for multiple fields using a find command that requires the user to mention the attribute type 
 followed by the keyword(s) for one or many attributes.
    * Pros: Results where multiple fields are tested can be displayed to the user.
    * Cons: The command itself won't be as user-friendly since the user has to mention the type
      for each attribute.

### Sort Command

The Sort Command makes use of the following classes:
* `SortCommand` — Updates the `Comparator` object in model.
* `SortCommandParser` — Parses the arguments supplied by the user, and choose the comparator supplied by `PersonComparators`.
* `PersonComparators` — Defines a set of comparators that can be supplied to model.

SortCommand Parser will determine the field that users want to sort, retrieve the `Comparator` from `PersonComparators` and update in model.

![SortSequenceDiagram](images/SortSequenceDiagram.png)

The below activity diagram shows what happens when a user uses the `sort` command.

![SortActivityDiagram](images/SortActivityDiagram.png)

### Add user using GitHub account

Adding of a GitHub account uses the following classes:

- `seedu.address.logic.commands.AddCommand` - Adds user with GitHub account
- `seedu.address.logic.commands.parser.AddCommandParser` - Parses GitHub username to add
- `seedu.address.github.GithubApi` - Defines error handling and consolidates API calls
- `seedu.address.github.UserInfoRoute.UserInfoRequest` - Calls API to get user information and profile image
- `seedu.address.github.UserInfoWrapper` - Parses API response by `UserInfoRoute`
- `seedu.address.github.UserRepoRoute.UserRepoRequest` - Calls API to get user repositories information
- `seedu.address.github.UserRepoWrapper` - Parses API response by `UserRepoRoute`
- `seedu.address.model.person.github.User` - Holds all parsed information about GitHub user
- `seedu.address.model.person.github.Repo` - Holds all parsed information about a specific GitHub repository

When adding a contact, the user will be able to give a GitHub username to add, from which we pull all relevant data from:

- `Name`
- `Location`
- `Email`
- `Profile Picture`

The updated `Add` command uses the following classes:

- `AddCommand` - Creates and adds `Person` object to list of contacts
- `AddCommandParser` - Parses the arguments supplied by the user for the `Add` command, differentiates between adding local name and GitHub account.
- `GithubApiWrapper` - Retrieves information from GitHub API to populate user detail fields

Flow of adding user:

1. Since the user is unsure of name/location/other info about the contact they wish to add, they wish to pull it straight from GitHub. Say the contact's GitHub account is `@exampleaccount`. The user executes the command `add github/exampleaccount`
2. `AddCommandParser` parses the command, returning an `AddCommand` which will add a contact using their GitHub username.
3. It then creates the `User` object with the username, which uses `GithubApi` to get all relevant information about the user and their repos.
    1. `GithubApi` object will create the `UserInfoWrapper` and `UserRepoWrapper` objects pertaining to the user,
    2. These will create `UserInfoRoute.UserInfoRequest` and `UserRepoRoute.UserRepoRequest` objects to call the API
    3. The wrappers will parse the information and pass it back to the `User` object.
4. The `User` class initialises all needed information as well as a list of `Repo`s owned by the user.

The UML diagram below shows how the dependencies between the related different classes work:

![PlantUML diagram](images/GithubApiUML.png)


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

### Delete User Attributes Feature (In Contact Detail Page)

When the user navigates to the ContactDetail Page of a specified Person, this feature allows the User to delete specified attributes from the command line.

The Delete Attribute feature will make use of the following classes:
* `DeleteAttributeCommand` — Removes specified attribute from specified `Person` object in the model.
* `DeleteAttributeCommandParser` — Parses the arguments supplied by the user - the `name` of the person whose `attribute` will be deleted.


Step 1: The user enters the `ContactDetailPage` of a previously added `Person`.

Step 2: The user executes `delete role`. `DeleteAttributeCommandParser` parses the arguments 
and creates a Prefix Object with the last valid argument supplied. A DeleteAttributeCommand
object is then constructed with this Prefix. Upon the calling of the `execute()` method
by the LogicManager, a new `Person` Object is created that has all the attributes of 
the Person before deletion except the attribute that is to be deleted.

Step 3: The `execute` method sets the new Person object (edited after deleting attribute)
to the previous Person Object. 

Step 4: Upon successful deletion, a `CommandResult` confirming the successful 
deletion is returned. 

Below are the attributes that can be deleted by the `DeleteAttributeCommand`:

* `role`
* `email`
* `phone`
* `github`
* `slack`
* `telegram`
* `timezone`

Below is the UML Sequence Diagram for the Delete Attribute Command:

![DeleteAttributeSequenceDiagram](images/DeleteAttributeCommand.png)

### [Proposed] Add user using GitHub account

When adding a contact, the user will be able to give a GitHub username to add, from which we pull all relevant data from:

- `Name`
- `Address`
- `Location`
- `Email`
-  `Timezone`

The updated `Add` command will make use of the following classes:

- `AddCommand` - Creates and adds `Person` object to list of contacts
- `AddCommandParser` - Parses the arguments supplied by the user for the `Add` command, differentiates between adding local name and GitHub account.
- `GithubApiWrapper` - Retrieves information from GitHub API to populate user detail fields

Step 1: Since the user is unsure of name/location/other info about the contact they wish to add, they wish to pull it straight from GitHub. Say the contact's GitHub account is `@exampleaccount`. The user executes the command `add github/exampleaccount`, upon which `AddCommandParser` parses the command, returning an `AddCommand` which will add a contact using their GitHub username. It then uses `GithubApiWrapper` to pull relevant information about the person then populates the fields before creating the `Person` object.


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

* has a need to manage a significant number of contacts with multiple ways of contact.
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                     | So that I can…​                                                        |
|----------|--------------------------------------------|------------------------------------------------------------------|------------------------------------------------------------------------|
| `* * *`  | new user                                   | see usage instructions                                           | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new person                                                 |                                                                        |
| `* * *`  | user who uses github                       | add a new person by github username                              | have their details without fill in manually                            |
| `* * *`  | user                                       | add different types of contact (slack, email, etc.) of a person  | keep track of their way of contacts                                    |
| `* * *`  | user                                       | edit different types of contact (slack, email, etc.) of a person |                                                                        |
| `* * *`  | user                                       | delete contact of a person                                       | remove contacts that are in-active                                     |
| `* * *`  | user                                       | set a timezone for a person                                      | contact them during working or available hours                         |
| `* * *`  | user                                       | set a role for a person                                          | contact person in-charge quickly                                       |
| `* * *`  | user                                       | find a person by name                                            | locate details of persons without having to go through the entire list |
| `* * *`  | user                                       | find a person by github username                                 | locate details of persons without having to go through the entire list |
| `* * *`  | user who uses github                       | find repository belongs to a person                              | know which person to find when have queries related to project         |
| `* * *`  | user                                       | store contact details in local                                   | contact a user without internet connection                             |
| `*`      | user with many persons in the address book | sort persons by name                                             | locate a person easily                                                 |

### Use cases

(For all use cases below, the **System** is `GithubContact` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC-01 - Find a person by any given keyword**

**MSS:**
1. User searches for person by any of their attributes (name/email/timezone/slack/twitter/telegram)
2. System shows a list of person with the matching keyword.

Use case ends.

**Extensions:**
* 2a. The list is empty
    * 2a1. System shows an error message
      Use case resumes at step 1.

**Use case: UC-02 - Add a new person**

**MSS**
1. User adds a person to the address book and specifies certain attributes to be added.
2. System is updated to include the newly added person.

Use case ends.

**Extensions:**
* 1a. The given name input has an invalid format
  * 1a1. System shows an error message
         Use case resumes at step 1.

* 1b. One of the specified attributes has an invalid format
  * 1b1. System shows an error message
         Use case resumes at step 1.

**Use case: UC-04 - View a person's Contact Detail Page**

**Preconditions:** Person has been added to the system.

**MSS**
1. User enters a person's contact detail page.
2. System displays all the contacts related to the person.

Use case ends.

**Use case: UC-05 - Add a new Contact to a Person**

**Preconditions:**
* Person has been added to the system.
* User is inside the person's Contact Detail Page.

**MSS:**
1. User adds a new contact to the person.
2. System is updated to reflect the person's new contact.
3. System shows the updated Contact Detail Page.

Use Case ends.

**Extensions**
* 1a. The contact already exists for that person.
    * 1a1. System shows an error message.
    Use case resumes at step 1.

* 1b. The name of the given contact is invalid.
    * 1b1. System shows an error message.
    Use case resumes at step 1.

**Use case: UC-06 - Update a person's Contact**

**Preconditions:**
* Person has been added to the system.
* User is inside the person's Contact Detail Page.

**MSS:**
1. User updates an existing contact of the person.
2. System is updated to reflect the person's new contact.
3. System shows the updated Contact Detail Page.

Use Case ends.

**Extensions**
* 1a. The contact does not exist for that person.
    * 1a1. System shows an error message.
      Use case resumes at step 1.

* 1b. The name of the given contact is invalid.
    * 1b1. System shows an error message.
      Use case resumes at step 1.

**Use case: UC-07 - View a person's Contact**

**Preconditions:**
* Person has been added to the system.
* User is inside the person's Contact Detail Page.

**MSS:**
1. User open the link of the contact.
2. System redirects user to the contact application (ex: Telegram, WhatsApp).

Use Case ends.

**Extensions**
* 1a. The link of contact is broken.
    * 1a. System shows an error message.
      Use case resumes at step 1.

**Use case: UC-08 - Delete a Contact from a person**

**Preconditions:**
* Person has been added to the system.
* User is inside the person's Contact Detail Page.

**MSS:**
1. User deletes an existing contact of the person.
2. System is updated to reflect the removal.
3. System shows the updated Contact Detail Page.

Use Case ends.

**Extensions**
* 1a. The contact does not exist for that person.
    * 1a1. System shows an error message.
      Use case resumes at step 1.

* 1b. The name of the given contact is invalid.
    * 1b1. System shows an error message.
      Use case resumes at step 1.

**Use case: UC-09 - Set timezone of a person**

**Preconditions:**
* Person has been added to the system.
* User is inside the person's Contact Detail Page.

**MSS:**
1. User sets a person's timezone to a certain value.
2. System is updated to reflect the person's new attribute.
3. System shows the updated Contact Detail Page.

Use Case ends.

**Extensions**
* 1a. A value for the timezone already exists for that person.
    * 1a1. System updates the person's timezone to the value given by the user.
      Use case resumes at step 3.

* 1b. A value for the timezone does not exist for that person.
    * 1b1. System adds the timezone attribute to the person using the value given by the user.
      Use case resumes at step 3.

* 1c. The input value given is of the wrong format
    * 1c1. System shows an error.
      Use case resumes at step 1.

**Use case: UC-10 - Delete timezone from a person**

**Preconditions:**
* Person has been added to the system.
* User is inside the person's Contact Detail Page.

**MSS:**
1. User deletes the timezone of the person.
2. System is updated to reflect the removal.
3. System shows the updated Contact Detail Page.

Use Case ends.

**Extensions**
* 1a. That person does not have a timezone attribute.
    * 1a1. System shows an error message.
      Use case resumes at step 1.


**Use case: UC-11 - Set role of a person**

**Preconditions:**
* Person has been added to the system.
* User is inside the person's Contact Detail Page.

**MSS:**
1. User sets a person's role to a certain value.
2. System is updated to reflect the person's new attribute.
3. System shows the updated Contact Detail Page.

Use Case ends.

**Extensions**
* 1a. A value for the role already exists for that person.
    * 1a1. System updates the person's role to the value given by the user.
      Use case resumes at step 3.

* 1b. A value for the role does not exist for that person.
    * 1b1. System adds the role attribute to the person using the value given by the user.
      Use case resumes at step 3.

* 1c. The input value given is of the wrong format
    * 1c1. System shows an error.
      Use case resumes at step 1.

**Use case: UC-12 - Delete role from a person**

**Preconditions:**
* Person has been added to the system.
* User is inside the person's Contact Detail Page.

**MSS:**
1. User deletes the role of the person.
2. System is updated to reflect the removal.
3. System shows the updated Contact Detail Page.

Use Case ends.

**Extensions**
* 1a. That person does not have a role attribute.
    * 1a1. System shows an error message.
      Use case resumes at step 1.

**Use case: UC-13 - Sort the list of person**

**Preconditions:**
* User is inside the Person Listing page

**MSS:**
1. User sets the field that need to be sorted.
2. System is updated to show the sorted list of person.

Use Case ends.

**Extensions**
* 1a. That field is invalid and cannot be sort.
    * 1a1. System shows an error message.
      Use case resumes at step 1.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should be able to load the contact list within 2 seconds.
5. Without noticeable delay, should be able to add and update contact information.
6. Without noticeable delay, should be able to delete contact information.
7. Must be able to cache GitHub's repository list for future use.
8. Must be able to display search results for find function within 3 seconds even for large address books.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Contact**: A contact is a way to approach a person. Example: Slack, Telegram, WhatsApp, etc.

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

   1. Resize the window to an optimum size. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a person

1. Adding a person to the list
   1. Test case: `add name/Mike github/mikelim address/21 Clementi phone/86609831 email/mike@gmail.com slack/mike123 telegram/@mike123 tag/friend timezone/+9 role/Frontend` <br>
      Expected: A person with the following attributes should be added to the list. Note that some attributes are only
      visible by entering the person's Contact Detail Page.
      1. name: Mike
      2. github: @mikelim
      3. address: 21 Clementi
      4. phone number: 86609831
      5. email: mike@gmail.com
      6. slack: mike123
      7. telegram: @mike123
      8. tags: friend
      9. timezone: 1 hour ahead
      10. role: Frontend
   2. Test case:`add name/John Doe address/27 Clementi` <br>
      Expected: A person with the following attributes should be added to the list.
      1. name: John Doe
      2. address: 27 Clementi

### Adding a person by GitHub username

1. Adding a person with only Github username

   1. Test case: `add github/wrewsama` <br>
      Expected: A person with the following attributes should be added to the list. Note that some attributes are only
                visible by entering the person's Contact Detail Page.
      1. name: Andrew Lo
      2. github: @wrewsama
      3. address: Singapore

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: The displayed list of persons is non-empty.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Entering the Contact Detail Page

1. Enter a person's contact detail page from the main page.
   1. Prerequisites: Multiple persons in the list. Execute the following commands first:
      1. `add n/amy`
      2. `add n/bob`
      3. `add name/Mike github/mikelim address/21 Clementi phone/86609831 email/mike@gmail.com slack/mike123 telegram/@mike123 tag/friend timezone/+9 role/Frontend`
   
   2. Test case: Use the arrow keys until Mike is highlighted, then hit the `<ENTER>` key. <br>
      Expected: A page with the following information is displayed:
      1. Profile picture
      2. Name
      3. Github account name
      4. Role
      5. Timezone
      6. Address
      7. Phone number
      8. telegram handle
      9. slack account
      10. email address
      11. repositories

      Use the `back` command or hit the `<ESC>` key to return to the main page.
   
   3. Test case: From the main page, double click on Mike. <br>
      Expected: The same page mentioned above is shown.

### Setting a person's attributes

1. Set a person's attributes.

   1. Prerequisites: Execute the following commands first:
      1. `add n/Bob phone/12345678`
      2. Double-click on Bob in the list of persons to access his contact detail page.
   2. Test case: `set n/Bobby` <br>
      Expected: Name gets changed from Bob to Bobby
   3. Test case: `set telegram/@bobby slack/b0bby` <br>
      Expected: telegram and slack fields are added to the contact detail page.
   4. Test case: `set phone/87654321 email/bob@bob.com` <br>
      Expected: Phone number gets changed from 12345678 to 87654321 and an email field is added.
   
### Deleting a person's attributes

1. Deleting a person's non-compulsory attributes.
   1. Prerequisites: Execute the following commands first:
       1. `add n/Bob phone/12345678 telegram/@bobby`
       2. Double-click on Bob in the list of persons to access his contact detail page.
   2. Test case: `delete telegram` <br>
      Expected: The telegram field is removed.
   3. Test case: `delete phone` <br>
      Expected: The phone number field is removed.

### Sorting the list of persons

1. Sorting the list of persons by role, by name, and by address.
   1. Prerequisites: Execute the following commands first:
      1. `add n/Amy role/DevOps a/Bishan`
      2. `add n/Bob role/Frontend a/Clementi`
      3. `add n/Chad role/Backend a/Ang Mo Kio`
   2. Test case: `sort name/desc` <br>
      Expected: Going from the top of the list to the bottom, Chad should appear first, followed by Bob, then Amy
   3. Test case: `sort role` <br>
      Expected: From top to bottom, Chad should appear first, followed by Amy, then Bob.
   4. Test case `sort address` <br>
      Expected: From top to bottom, Bob should appear first, followed by Amy, then Chad.
   5. Test case: `reset` <br>
      Expected: From top to bottom, Amy appears first, followed by Bob, then Chad.

### Searching

1. Fuzzy search for persons by name, address, github username, tags, and role.
   1. Prerequisites: Execute the following commands first:
       1. `add n/Amy role/DevOps a/Bishan`
       2. `add n/Bob role/Frontend a/Clementi t/lead`
       3. `add n/Chad role/Backend a/Ang Mo Kio`
       4. `add n/Drew github/wrewsama role/Intern a/Khatib`
       5. `add n/Eren role/Backend a/Khatib t/lead`
   2. Test case: `find wrewsama` <br>
      Expected: Only Drew appears in the list.
   3. Test case: `find backend` <br>
      Expected: Both Chad and Eren appear in the list.
   4. Test case: `find lead` <br>
      Expected: Both Bob and Eren appear in the list.
   5. Test case: `find ang mo kio` <br>
      Expected: Both Drew and Eren appear in the list.
   6. Test case: `find ereh` <br>
      Expected: Eren appears in the list.
   7. Test case: `reset` <br>
      Expected: All added persons can be seen in the list.

### Saving data

GithubContact enables advanced users to make direct edits to the data file located at:
`<JAR FILE LOCATION>/data/addressbook.json>`.

1. Dealing with missing/corrupted data files

   1. If an edit to the data file invalidates the information's format, when opened, GithubContact will delete the data
      file and start with a fresh, empty data file.
