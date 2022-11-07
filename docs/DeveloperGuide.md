---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* Our Condonery Project is morphed from [SE-Education's AddressBook3](https://se-education.org/addressbook-level3/)

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

**`Main`** has two classes called [`Main`](https://github.com/AY2223S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/condonery/Main.java) and [`MainApp`](https://github.com/AY2223S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/condonery/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete -p 1`.

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

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PropertyListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/condonery/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S1-CS2103-W14-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Property` and `Client` objects residing in the `Model`.
* The UI also depends on UserPrefs to determine where to store images uploaded by the user.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/condonery/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `CondoneryParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddPropertyCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a property).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete -p 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeletePropertyCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `CondoneryParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPropertyCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPropertyCommand`) which the `CondoneryParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPropertyCommandParser`, `DeletePropertyCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/condonery/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Property` and `Client` objects (which are contained in `UniquePropertyList` and `UniqueClientList` objects respectively).
* stores the currently 'selected' `Property` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Property>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `PropertyDirectory`, which `Property` references. This allows `PropertyDirectory` to only require one `Tag` object per unique tag, instead of each `Property` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/condonery/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from `PropertyDirectoryStorage`, `ClientDirectoryStorage`, and `UserPrefStorage`, which means it can be treated as any one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.condonery.commons` package.

-----------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Select property/client feature
#### High-level details
* The select feature is meant to expand on either a `Property` or a `Client` to display its details in the GUI.
* Importantly, a `Property` can hold a list of interested clients and a `Client` can hold a list of properties that the client is interested in.
* Depending on if a `Property` or `Client` is selected, the GUI changes to show just the selected `Property` or `Client` in its respective tab, and its interested clients or intersted properties - respectively - in the other tab.
* The select command is as follows, where `select -p [INDEX]` is used to select a property under the property directory, and `select -c [INDEX]` is used to select a client under the client directory:

  ```
  select -[pc] [INDEX]
  ```

  * The compulsory input `INDEX` would correspond to the current displayed list in the GUi.
* Examples of usage:
  * `select -p 2`
  * `select -c 10`

#### Technical details
* The implementation of the select feature mainly revolves around two classes each for a `Property` and a `Client`: `SelectPropertyCommand`, `SelectPropertyCommandParser`, `SelectClientCommand` and `SelectClientCommandParser`.
  * The `SelectPropertyCommand` and `SelectClientCommand` classes handle the backend execution of the command, such as changing the list of Properties or Clients to be displayed on the GUI (See the UML diagrams below for a detailed breakdown).
  * The `SelectPropertyCommandParser` and `SelectClientCommandParser` classes handle the parsing of the arguments that have been supplied with the `select -[pc]` command (See the UML diagrams below for a detailed breakdown).
    * The user is supposed to only provide a valid `[INDEX]` as an argument. If invalid arguments have been provided, the parser classes throw a `ParseException` and display a message on the GUI to inform the user of the error.

#### UML Diagrams
The diagrams below are for `SelectPropertyCommand` and `SelectPropertyCommandParser`.

This activity diagram models the workflow when a `select -p 1` input is given by the user.

Importantly, errors that might be thrown are modeled in this diagram.

![SelectPropertyActivityDiagram](images/SelectPropertyActivityDiagram.png)

This sequence diagram shows the interactions between the `Logic`, `Model`, and `Ui` classes when a `select -p 1` input is given by the user.

![SelectPropertySequenceDiagram](images/SelectPropertySequenceDiagram.png)

The logic for `SelectClientCommand` and `SelectClientCommandParser` are similar and derivable from the diagrams too.

### Range feature

The range feature allows the user to filter properties by a price range in Condonery.

The feature is activated by the command pattern `range -p l/[lower] u/[upper]`.

**Parsing of command within the `Logic` component**

Much like the other core features, we introduced an intermediate between `CondoneryParser` and the range command parser,
that is the `RangePropertyCommandParser`.

These are the steps that will be taken when parsing a range command:

1. The `CondoneryParser` checks if the user command is a range command. Then it creates a `RangePropertyCommandParser`.
2. The `RangePropertyCommandParser` which implements the `Parser` interface, parses the command via `Parser#parse`.
3. If the user command is valid, the parser creates the corresponding `Command` object for execution.

Given below is a sequence diagram for interactions inside the Logic component for the `execute(range -p l/<LOWER> u/<UPPER>`
API call. Note that the command (e.g., `range -p l/100,000 u/500,000`)is truncated for brevity.

![RangeSequenceDiagram](images/RangeSequenceDiagram.png)

**Execution of command within the `Logic` component**

When a `RangePropertyCommand` is created by the `RangePropertyCommandParser`, it is executed with `model` passed in
as the parameter.

Firstly, the `updateFilteredPropertyList` is called to get the list of properties within the specified price range.

Next, a `CommandResult` object containing the message to be displayed to the user is returned to `LogicManager`.

![RangeExecuteSequenceDiagram](images/RangeExecuteSequenceDiagram.png)

**Error handling within the `Logic` component**

The below activity diagram shows the overall process of execution of `execute(range -p l/100 u/200)`.

In order to ensure data cleanliness and that the inputs by the users are valid, errors are thrown at various stages if:

- Incorrect command format is used (e.g. missing price as argument, missing prefixes)
- Arguments are invalid (e.g. negative prices)

![RangeActivityDiagram](images/RangeActivityDiagram.png)

**Design consideration**

Aspect: How to filter properties by prices

- **Alternative 1** (current choice): Add a lower and upper prefix to command phrase to indicate lower and upper bound.
    - Pros:
      - Less time-consuming to implement.
      - Easier to parse price range.
    - Cons:
      - Imposes strict requirement on use of lower and upper prefixes.
- **Alternative 2**: Allow user to key in two separate integers in command.
  - Pros:
    - Easy and fairly intuitive user input system
  - Cons:
    - More time-consuming to implement.
    - Need extra checking to correctly parse two separate integers and identify lower and upper bounds.
    - More prone to manual user entry error.

Alternative 1 was chosen to enable more efficient parsing of commands.

### Filter by Tag feature

The filter feature returns a list of properties whose tags contain any of the given keywords.

The feature is activated by the command pattern `filter -p TAG [MORE_TAGS]`.

**Parsing of command within the `Logic` component**

Much like the other core features, we introduced an intermediate between `CondoneryParser` and the filter command parser,
that is the `FilterPropertyCommandParser`.

These are the steps that will be taken when parsing a range command:

1. The `CondoneryParser` checks if the user command is a filter command. Then it creates a `FilterPropertyCommandParser`.
2. The `FilterPropertyCommandParser` which implements the `Parser` interface, parses the command via `Parser#parse`.
3. If the user command is valid, the parser creates the corresponding `Command` object for execution.

Given below is a sequence diagram for interactions inside the Logic component for the `execute(range -p l/<LOWER> u/<UPPER>`
API call. Note that the command (e.g., `filter -p High-end Luxury` is truncated for brevity.

![FilterSequenceDiagram](images/FilterSequenceDiagram.png)

**Execution of command within the `Logic` component**

When a `FilterPropertyCommand` is created by the `FilterPropertyCommandParser`, it is executed with `model` passed in
as the parameter.

Firstly, the `updateFilteredPropertyList` is called to get the list of properties whose tags match any of the given arguments.

Next, a `CommandResult` object containing the message to be displayed to the user is returned to `LogicManager`.

![FilterExecuteSequenceDiagram](images/FilterExecuteSequenceDiagram.png)

**Error handling within the `Logic` component**

The below activity diagram shows the overall process of execution of `execute(range -p l/100 u/200`.

In order to ensure data cleanliness and that the inputs by the users are valid, errors are thrown at various stages if:

- Incorrect command format is used (e.g. missing tag argument)

![RangeActivityDiagram](images/RangeActivityDiagram.png)

### Filter by Property Status Feature
This feature allows users to filter properties by `PropertyStatusEnum`.

The feature is activated by the command patter `status -p [property_status]`

**Parsing of command within the Logic component**

This command uses the `StatusPropertyCommandParser` to parse the command.

These are the steps that will be taken when parsing a range command:

1. The `CondoneryParser` checks if the user command is a range command. Then it creates a `StatusPropertyCommandParser`.
2. The `StatusPropertyCommandParser` which implements the `Parser` interface, parses the command via `Parser#parse`.
3. If the user `command` is valid, the parser creates the corresponding `Command` object for execution.

![StatusPropertySequenceDiagram](images/StatusPropertySequenceDiagram.png)

**Execution of command within the Logic component**

When a `StatusPropertyCommand` is created by the `StatusPropertyCommandParser`, it is executed with model passed in as the parameter.

Firstly, the `updateFilteredPropertyList` is called to get the list of properties within the specified price range.

Next, a `CommandResult` object containing the message to be displayed to the user is returned to `LogicManager`.

**Error handling within the Logic component**

![StatusPropertyActivityDiagram](images/StatusPropertyActivityDiagram.png)

**Design consideration**

Aspect: Input format for `StatusPropertyCommand`

- **Alternative 1** (current choice): Accept both lower and upper case for `StatusPropertyCommand` argument
(e.g. accept both `status -p available` and `status -p AVAILABLE` inputs)
  - Pros:
    - Less prone to error for the user
    - More intuitive for the user
  - Cons:
    - Format be confusing for user
- **Alternative 2**: Only accept upper case input for `StatusPropertyCommand`
  - Cons:
    - More prone to error
    - User has to follow case, which can be troublesome

- Alternative 1 was chosen because of its ease of use, more intuitive, and less error-prone reasons.


### Commands

#### Parsing of commands within the `Logic` component

The parsing of commands begins once the `LogicManager` receives and tries to execute the user input.

To parse the different commands in our application, we have individual command parsers for the different commands
(e.g. `EditCommandParser`).


### User Uploaded Images

The application allows users to upload their own images for Property and Client models. By default, the images are stored
in `data/images`, or users can specify their custom directory in `preferences.json`.

The feature is activated by the command pattern `add -p -i [OTHER_ARGUMENTS]...`

Here is an activity diagram showing the process and error handling when the user tries to add a property with an image.
![UploadImageActivityDiagram](images/UploadImageActivityDiagram.png)

* The File Chooser ensures that Users can only upload `.png` or `.jpg` files, so we need not handle the case where the image is of the
wrong format.
* By default, the image is saved in `data/images` and renamed with default name `[property/client]-[EXACT_NAME]`. This is to prevent users from uploading images with conflicting names.

The Image object is not initialized until the PropertyCard/ClientCard of the UI is rendered. This is to save memory
consumption and rely on the Lazy Loading of Observable List. We need to inject the UserPrefs into the Property/Client
models in order to determine the location to source for the uploaded images.

### Undo feature

The undo mechanism is facilitated by `CommandQueue`.

`CommandQueue` stores a List of Commands which had been executed during the application lifecycle, and is stored as a field in the `Model` class.
Everytime the `LogicManager` executes a command, it will call `Model#addCommand()`, which adds the `Command` to the `CommandQueue`.
In addition, the initial state of the `PropertyDirectory` and `ClientDirectory` is saved on initialization.

The undo functionality is exposed in the `Model` interface as `Model#undoCommand()`.
This method handles the resetting of `PropertyDirectory` and `ClientDirectory` to its initial states, and then executing all the commands except for the most recent one.

Here is the sequence diagram for when `undo` command is executed.
![UndoSequenceDiagram.png](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Given below is an example usage scenario and how the undo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `ModelManager` will be initialized with the initial `PropertyDirectory` and `ClientDirectory` state, and the `CommandQueue` will be empty.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete -p 5` command to delete the 5th property in the address book. The `LogicManager` calls `Model#addCommand(deleteCommand)`, causing the exact same `DeletePropertyCommand` to be saved in the `CommandQueue`.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add -p n/Oasis …` to add a new person. The `add -p` command also calls `Model#addCommand(addCommand)`, causing another command to be saved into the `CommandQueue`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#addCommand()`, so the command will not be saved into the `CommandQueue`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoCommand()`, which will pop the most recent `Command`(ie. addCommand), and restores the `PropertyDirectory` and `ClientDirectory` to its initial state. It will then proceed to execute all commands in the `CommandQueue`. Since all commands are deterministic, the resultant state of `PropertyDirectory` and `ClientDirectory` will be as though the last command was 'undone'.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `CommandQueue` is empty, then there are no commands to execute. The `CommandQueue` will throw `EmptyQueueException`, which will be handled by `UndoCommand` by returning an error to the user rather
than attempting to perform the undo.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list -p`. Commands that do not modify the `PropertyDirectory` or `ClientDirectory`, such as `list -p`, will not call `Model#addCommand()`. Thus, the `CommandQueue` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear -p`, which calls `Model#addCommand()`. We notice that `addCommand` is no longer in the `CommandQueue`, and is now replaced by `clearCommand`

![UndoRedoState5](images/UndoRedoState5.png)

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1:** Saves the initial PropertyDirectory and ClientDirectory on initialization. Store all commands in
CommandQueue and re-executes _n - 1_ commands on undo.
    * Pros: Easy to implement. Less memory usage
    * Cons: Might make undo command less responsive, depending on time taken to execute commands.
* **Alternative 2:** Saves the entire address book after each Command.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.
* **Alternative 3:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

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

* middle-aged property agent selling new condos to prospective clients
* has a need to manage a significant number of property listings and clients
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage property listings and clients faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                            | I want to …​               | So that I can…​                                                              |
| -------- |----------------------------------------------------|----------------------------|------------------------------------------------------------------------------|
| `* * *`  | new user                                           | see usage instructions     | refer to instructions when I forget how to use the App                       |
| `* * *`  | property agent                                     | add a new property listing |                                                                              |
| `* * *`  | property agent                                     | delete a property listing  | remove entries that I no longer need                                         |
| `* * *`  | property agent                                     | find a listing by name     | locate details of listings without having to go through the entire list      |
| `* *`    | property agent                                     | edit listing details       | update details of listings                                                   |
| `*`      | property agent with many listings in the directory | sort listings by price cap | locate a listing within budget without haveing to go through the entire list |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Condonery` and the **Actor** is the `user`

#### Use Case 1: Listing all Properties

**MSS**

1. User requests to list all properties
2. Condonery shows a list of all the properties

Use case ends.

**Extensions**

2a. The Property List is empty

Use case ends.

#### Use Case 2: Adding a Property

**MSS**

1. User requests to add a property
2. Condonery adds the property into the Property Directory

Use case ends.

**Extensions**

1a. The given input is missing a required argument

- 1a1. Condonery shows an error message with an example of correct input with all the required arguments
Use case resumes at step 1.

1b. The given input is missing a required prefix

- 1b1. Condonery shows an error message with an example of correct input with all the required arguments
Use case resumes at step 1.

1c. The given Property Name already exists in the Property Directory

- 1c1. Condonery shows an error message stating that the property already exists in the Property Directory.
Use case resumes at step 1.

1d. The given Property Price parameter exceeds the MAX_INT

- 1d1. Condonery shows an error message stating that the price is invalid.
Use case resumes at step 1.


#### Use Case 3: Deleting a property

**MSS**

1. User requests to list all properties
2. Condonery shows a list of properties
3. User request to delete a property in the list with a specified index
4. Condonery deletes the specified property

Use case ends.

**Extensions**

3a. The input did not specify a index

- 3a1. Condonery shows an error message stating that an invalid command was provided with an example of correct input.
Use case resumes at step 2.

3b. The input's specified index does not exist in the Property Directory

- 3b1. Condonery shows an error message stating that an invalid command was provided with an example of correct input.
    Use case resumes at step 2.

3c. The input's specified index is in the wrong format.

- 3c1. Condonery shows an error message stating that an invalid command was provided with an example of correct input.
Use case resumes at step 2.

#### Use Case 4: Editing a Property

**MSS**

1. User requests to list properties
2. Condonery lists all properties
3. User requests to change a specific property's fields
4. Condonery updates the property's fields

Use case ends.

**Extensions**

2a. The list of properties is empty.
Use Case ends.

3a. The given input is missing a required parameter

- 3a1. Condonery shows an error message with an example of correct input with all the required arguments
Use case resumes at step 3.

3b. The given input is missing a required prefix

- 3b1. Condonery shows an error message with an example of correct input with all the required arguments
Use case resumes at step 3.

3c. The input did not specify a index

- 3c1. Condonery shows an error message stating that an invalid command was provided with an example of correct input.
Use case resumes at step 3.

3d. The input's specified index does not exist in the Property Directory

- 3d1. Condonery shows an error message stating that an invalid command was provided with an example of correct input.
Use case resumes at step 3.

3e. The input's specified index is in the wrong format.

- 3e1. Condonery shows an error message stating that an invalid command was provided with an example of correct input.
Use case resumes at step 3.

#### Use Case 5: Filtering a property by Property Type

**MSS**

1. User requests to filter property by a specific property type
2. Condonery shows a list of properties with applied user filter

Use case ends

**Extensions**

1a. Specified type is not one of `HDB`, `CONDO`, or `LANDED`

- 1a1. Condonery shows an error message stating that only `HDB`, `CONDO`, or `LANDED` values are accepted.
Use case resumes at step 1.

#### Use Case 6: Filtering a property by Property Status

**MSS**

1. User requests to filter property by a specific property type
2. Condonery shows a list of properties with applied user filter

Use case ends

**Extensions**

1a. Specified type is not one of `AVAILABLE`, `PENDING`, or `SOLD`

- 1a1. Condonery shows an error message stating that only `AVAILABLE`, `PENDING`, or `SOLD` values are accepted.
Use case resumes at step 1.

#### Use Case 7: Linking an Interested Client to a Property

**MSS**

1. User requests to link an Interested Client to a Property
2. Interested Client is successfully linked to the specified Property

**Extensions**

1a. Specified client name does not exist in the Client Directory

- 1a1. Condonery shows an error message

1b. Specified index does not exist in the Property Directory

- 1b1. Condonery shows an error message stating that the property index is invalid

#### Use Case 8: Finding a Property by name

**MSS**

1. User requests to search a property by name
2. Properties that match the user specified name are displayed

#### Use Case 9: Clearing all Properties

**MSS**

1. User requests to clear all properties in the Property List
2. Condonery removes all the properties listen in the Property List and displays a success message

Use case ends.

#### Use Case 10: Help List

**MSS**

1. User requests to list all possible commands of Condonery
2. Condonery shows the list of possible commands and the correct formats

Use case ends.

#### Use Case 11: Selecting a Property

**MSS**

1. User requests to select a particular property to view interested clients
2. Condonery shows the interested clients of the selected property

**Extensions**

1a. Specified index does not exist in the Property Directory

- 1a1. Condonery shows an error message stating that the property index is invalid

1b. The input did not specify a index

- 1b1. Condonery shows an error message stating that an invalid command was provided with an example of correct input.

#### Use Case 12: Filtering properties within a price range

**MSS**

1. User requests to view properties between a certain price range
2. Condonery shows the properties that fulfill the price range criteria

**Extensions**

1a. Specified upper price range is lower than the lower price range

- 1a1. Condonery shows an error message stating that an invalid price range was given

1b. Negative numbers were given

- 1b1. Condonery shows an error message stating that an invalid price range was given


### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should work on 32-bit and 64-bit environments.
5. Should work without an internet connection

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Property**: A listed unit of a property
* **Command**: A text input keyed in by the user, in the command box of the GUI.
  * A command must have a keyword (e.g. `select`, `clear`)
  * A command might require a flag, and/or a parameter(s)
* **Flag**: A flag denoting if a command is for properties or clients, i.e., `-p` and `-c`
* **Parameter**: A combination of a prefix and an argument(s) that functions as inputs to a command
  * Examples: `n/Samuel` is a name parameter; `a/Woodlands` is an address parameter
* **Prefix**: The symbol used in a parameter to indicate which parameter it is for
  * Examples: `n/` is the prefix for the name parameter; `a/` is the prefix for the address parameter
* **Argument**: The user-defined inputs for parameters
  * Examples: "Jaime" could be an argument for the name parameter; "Sembawang" could be an argument for the addresss parameter

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

:information_source: Note: These instructions only provide a starting point for testers to work on; testers are expected to do more exploratory testing.

### Launch and shutdown
Initial launch
Download the Condonery.jar file and copy into an empty folder
Run `java -jar Condonery.jar` in the folder.

Expected: Shows the GUI with a property and client directory. The window size may not be optimum.

### Exit application
Prerequisites: The application must be launched.
Test case: exit

Expected: The application exits and the window closes itself automatically.

### Adding a property

Adding a property to the Property List

Prerequisites: Application must be in main window

- Test cases (specifying compulsory inputs only): `add -p n/PINNACLE@DUXTON a/SG, Cantonment Rd, #1G, 085301 p/1,000,000 h/HDB`
  - Expected: A new property is added to the end of the list of properties. Property list panel will then update to display all the properties in the property directory. The compulsory parameters will be set to what was specified in the command, whereas property status will be set to `AVAILABLE` by default when not stated in the input. Result display will output the message: `New property added: PINNACLE@DUXTON; Address: SG, Cantonment Rd, #1G, 085301; Price: 1000000; Property Type: HDB; Property Status: AVAILABLE`
- Test case (missing a compulsory input (p/PRICE)): `add -p n/NEXT@DUXTON a/SG, Cantonment Rd, #1G, 085301 h/HDB`
  - Expected: No property added to list of properties and input text will turn red to signal an error. Result display will output the invalid command format error message.
- Test case (missing a compulsory input (h/PROPERTY_TYPE)): `add -p n/NEXT@DUXTON a/SG, Cantonment Rd, #1G, 085301 p/1,000,000`
  - Expected: No property added to list of properties and input text will turn red to signal an error. Result display will output the invalid command format error message.

### Deleting a property
Deleting a property while all properties are being shown

Prerequisites: Property List must contain at least one property.

- Test case (Valid index): `delete -p 1`
  - Expected: First property in the list will be deleted from the list of properties. Property list will reflect the status of the deleted property
- Test case (Invalid index): `delete -p 0`
  - Expected: No property will be deleted from the list of properties and input text will turn red to indicate there is an error in the input. Result display will output a correct example command and error message.
- Test case (missing index): `delete-p `
  - Expected: No property will be deleted from the list of properties and input text will turn red to indicate there is an error in the input. Result display will output a correct example command and error message.

### Editing a property
Editing a property while there are properties shown in the Property List
   Prerequisites: Property List must contain at least one Property.
   - Test case (editing one field of a Property): `edit -p 1 n/ Condo@NUS`
     - Expected: The Property Name of the Property with index 1 in the Property List will be modified to become `Condo@NUS`. The remaining fields of this Property will remain the same. Result display will output the modified Property and its details.
   - Test case (Invalid index given): `edit -p 0 n/Condo@NUS`
     - Expected: No property will be edited. Invalid command format error will be displayed due to invalid index input.
   - Test case (`-p` flag not provided): `edit 1 n/Condo@NUS`
     - Expected: No property will be edited. Unknown error will be displayed as the flag was not provided.
   - Test case (Index provided is larger than size of list e.g. size = 5): `edit -p 6 n/ Condo@NUS`
     - Expected: No property will be edited. Error message stating invalid property index provided will be shown in the status message.
   - Test case (No field is provided): `edit -p 1`
     - Expected: No property will be edited. Error message stating at least one field must be provided is shown in the status message.

### Listing all properties
Listing all properties

   Prerequisites: Property List must contain at least one Property.
   - Test case: `list -p`
     - Expected: Property List will be updated to show all properties stored.
     Result display will output a success message: `Listed all properties`

### Clearing all properties
Clearing all properties in the Property List

   Prerequisites: Property List must contain at least one property
   - Test case: `clear -p`
     - Expected: Property List is cleared and the UI is updated to show an empty Property List. Result display will output a success message : `Property directory has been cleared!`

### Finding properties by name
Finding properties in the Property List with name

Prerequisites: Property List must contain at least one Property.
   - Test case (finding property name of `Wall Street Prime` with partial match): `find -p Wall Street`
     - Expected: Property List will display all properties with matching name of `Wall Street`
   - Test case (finding property name of `Wall Street Prime` with exact match): `find -p Wall Street Prime`
     - Expected: Property List will display all properties with matching name of `Wall Street Prime`

### Filtering properties by tags
Finding all properties in the Property List with specified tags

Prerequisites: Property List must contain at least one Property.
   - Test case (finding properties with tag of `Luxury`): `filter -p Luxury`
     - Expected: Property List will display all properties with matching tag of `Luxury`
   - Test case (finding properties with tag of `Luxury` and `City`): `filter -p Luxury City`
     - Expected: Property List will display all properties with matching tags of `Luxury` and `City`

### Filtering properties within price range
Finding all properties in the Property List with specified price range

   Prerequisites: Property List must contain at least one Property.


   - Test case (finding properties with range from 99 to 99999): `range -p l/99 u/99999`
     - Expected: Property List will display all properties with price range from 99 to 99999
   - Test case (Negative number given): `range -p l/-999 u/99999`
     - Expected: Property List will display an error message stating only positive numbers are accepted
   - Test case (Invalid input): `range -p l/abc u/99999`
     - Expected: Property List will display an error message stating that invalid input was given, only positive integers are accepted

### Filtering properties by status
Finding all properties in the Property List with the specified Property Status

   Prerequisites: Property List must contain at least one Property.
   - Test case (finding all properties with status of `AVAILABLE`): `status -p AVAILABLE`
     - Expected: Property List will display all properties with the status of available

### Filtering properties by type
Finding all properties in the Property List with the specified Property Type

   Prerequisites: Property List must contain at least one Property.
   - Test case (finding all properties with type of `HDB`): `type -p HDB`
     - Expected: Property List will display all properties with the type of `HDB`
   - Test case (finding all properties with type of `HOUSE`): `type -p HOUSE`
     - Expected: Condonery will display an error message stating `HOUSE` is not a valid property type

### Select a property to view its interested clients
   Prerequisites: Property List must contain at least one Property.
   - Test case (selecting a property with valid index): `select -p 1`
     - Expected: Property List will display all interested clients of property with index of 1


### Adding a client to the Client List
   Prerequisites: Application must be in main window
   - Test cases (specifying compulsory inputs only): `add -c n/James a/123, Clementi Rd, 1234665`
     - Expected: A new client is added to the Client List. 
     Client List panel will update to display all the clients in the client directory. 
     The compulsory parameters will be set to what was specified in the command. 
     Result display will output the message: 
     `New client added: James; Address: 123, Clementi Rd, 1234665`
   - Test case (missing a compulsory input (n/NAME)): `add -c a/123, Clementi Rd, 1234665`
     - Expected: No client will be added to the Client List and input text will turn red to signal an error. 
     Result display will indicate that an invalid command format has been specified.
   - Test case (Interested Property does not exist): `add -c n/James a/123, Clementi Rd, 1234665 t/friend t/colleague ip/PINNACLE@DUXTON`
     - Expected: No client added to the Client List and input text will turn red to signal an error. 
     Result display will indicate that a property of `PINNACLE@DUXTON` was not found in the property directory.
   
### Deleting a client
   Deleting a client while all clients are being shown

   Prerequisites: Client List must contain at least one client.
   - Test case (Valid index): `delete -c 1`
     - Expected: First client in the list will be deleted from the list of clients. Client list will reflect the status of the deleted client
   - Test case (Invalid index): `delete -c 0`
     - Expected: No client will be deleted from the list of clients and input text will turn red to indicate there is an error in the input. Result display will output a correct example command and error message.
   - Test case (missing index): `delete -c`
     - Expected: No client will be deleted from the list of clients and input text will turn red to indicate there is an error in the input. Result display will output a correct example command and error message.

### Editing a client
Editing a client while there are properties shown in the Client List

Prerequisites: Client List must contain at least one Client .
- Test case (editing one field of a Client ): `edit -c 1 n/ John Lee`
  - Expected: The Client Name of the client with index 1 in the Client List will be modified to become `John Lee`. 
  The remaining fields of this Client will remain the same. Result display will output the modified Client and its details.
- Test case (Invalid index given): `edit -c 0 n/John Lee`
  - Expected: No client will be edited. Invalid command format error will be displayed due to invalid index input.
- Test case (`-c` flag not provided): `edit 1 n/John Lee`
  - Expected: No client will be edited. Unknown error will be displayed as the flag was not provided.
- Test case (Index provided is larger than size of list e.g. size = 5): `edit -c 6 n/ John Lee`
  - Expected: No client will be edited. Error message stating invalid client index provided will be shown in the status message.
- Test case (No field is provided): `edit -c 1`
  - Expected: No client will be edited. Error message stating at least one field must be provided is shown in the status message.

### Listing all clients
Listing all clients

   Prerequisites: Client List must contain at least one Client.

   - Test case: `list -c`
   - Expected: Client List will be updated to show all clients stored. Result display will output a success message: `Listed all clients`

### Clearing all clients
   Clearing all properties in the Property List

   Prerequisites: Client List must contain at least one client

   - Test case: `clear -c`
     - Expected: Property List is cleared and the UI is updated to show an empty Property List. Result display will output a success message : `Property directory has been cleared!`

### Finding clients by name
Finding clients in the Client List with name

   Prerequisites: Client List must contain at least one client.
   - Test case (finding client name of `John` with partial match): `find -c John`
     - Expected: Client List will display all clients with matching name of `John`
   - Test case (finding client name of `John Lee` with exact match): `find -c John Lee`
     - Expected: Client List will display all clients with matching name of `John Lee`

### Filtering clients by tags
Finding all clients in the Client List with specified tags
   Prerequisites: Client List must contain at least one Client.
   - Test case (finding properties with tag of `friend`): `filter -c friend`
     - Expected: Client List will display all clients with matching tag of `friend`
   - Test case (finding properties with tag of `rich` and `friend`): `filter -c friend`
     - Expected: Client List will display all clients with matching tags of `rich` and `friend`

## **Appendix: Effort**
Majority of the complexity faced in building our app came from dealing with multiple entities, namely Property and Client.
Our group faced trouble in implementing a 2-way association between Clients and Properties.

* For example, editing a `Client` with an association should update all references to the `Client`. As previously AB3 relied on an immutable implementation of editing entities, we had to do heavy testing to make sure that the information between all associations are synced.
* Moreover, we had to ensure that storage of the 2-way association is feasible. Currently, our `Property` entities store a list of Interested Clients. Along the way, we realised that we are unable to store a list of Properties in a `Client` entity, as this resulted in our serialised storage being circular. We had to change a lot of our code to fix this, as we realised our previous implementation could not handle the storage requirement.

Our group also tried to implement image storage for user's to upload their own pictures of Properties/Clients
Here are some design considerations we had to deal with:
* Initially, we wanted our `Logic` layer to handle the image uploading command. However, we soon realised this was unfeasible we as needed user input through the `Ui` layer, as we had to show a File Chooser, and only then handle the checking of file type. This problem was made more complicated
as our storage location depended on the `UserPrefs`, which was stored in the `Model` layer. As much as possible, we didn't want the `Ui` layer to directly access the `Model` layer.<br>
* The final solution our group came up with was to store the file name and file directory of each image in its respective entity (Property/Client). This allowed the `Ui` layer to directly source the image without accessing the `Model` layer, and instead accessing the individual entities in the `ObservableList`.
The image uploading was left to the `Ui` layer, and the `Logic` layer used the `CommandResult` to signal to the `Ui` layer to accept an image upload, similar to how the Help command is implemented.

