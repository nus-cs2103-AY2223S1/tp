<!--markdownlint-disable-file first-line-h1 -->

```tip
The `.puml` files used to create diagrams in this document can be found in the [diagrams]({{ page.master_branch }}/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
```

### Architecture

![](images/ArchitectureDiagram.png)

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

#### Main components of the architecture

**`Main`** has two classes called [`Main`]({{ page.master_branch }}/{{ page.main_src }}/Main.java) and [`MainApp`]({{ page.master_branch }}/{{ page.main_src }}/MainApp.java). It is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[`Commons`](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [`UI`](#ui-component): The UI of the App.
* [`Logic`](#logic-component): The command executor.
* [`Model`](#model-component): Holds the data of the App in memory.
* [`Storage`](#storage-component): Reads data from, and writes data to, the hard disk.

#### How the architecture components interact with each other

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `del 1`. This deletes the first item from the Item List Box.

![](images/ArchitectureSequenceDiagram.png)

Each of the four main components (also shown in the diagram above),

* defines its _API_ in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

![](images/ComponentManagers.png)

The sections below give more details of each component.

### UI Component

The **API** of this component is specified in [`Ui.java`]({{ page.master_branch }}/{{ page.main_src }}/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ItemListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`]({{ page.master_branch }}/{{ page.main_src }}/ui/MainWindow.java) is specified in [`MainWindow.fxml`]({{ page.master_branch }}/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Item` object residing in the `Model`.

### Logic Component

**API** : [`Logic.java`]({{ page.master_branch }}/{{ page.main_src }}/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

![](images/LogicClassDiagram.png)

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `FoodRemParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `NewCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add an item).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("del 1")` API call.

![Interactions Inside the Logic Component for the `del 1` Command](images/DeleteSequenceDiagram.png)

```note
The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
```

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

![](images/ParserClasses.png)

How the parsing works:

* When called upon to parse a user command, the `FoodRemParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `NewCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `NewCommand`) which the `FoodRemParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `NewCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model Component

{% include_relative _dg/ModelComponent.md %}

### Storage Component

**API** : [`Storage.java`]({{ page.master_branch }}/{{ page.main_src }}/storage/Storage.java)

![](images/StorageClassDiagram.png)

The `Storage` component,

* can save both FoodRem data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `FoodRemStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common Classes

Classes used by multiple components are in the `seedu.foodrem.commons` package.
