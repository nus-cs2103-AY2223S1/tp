---
layout: page
title: Developer Guide
show-toc: true
---

{% include toc.md header=true show-in-toc=false %}

---

## Acknowledgements

* Libraries used: [Jackson](https://github.com/FasterXML/jackson), [JavaFX](https://openjfx.io/), [Jekyll](https://jekyllrb.com/), [JUnit5](https://github.com/junit-team/junit5), [PlantUML](https://plantuml.com/)

---

## Setting up, Getting Started

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---

## Design

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams]({{ page.master_branch }}/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`]({{ page.master_branch }}/{{ page.main_src }}/Main.java) and [`MainApp`]({{ page.master_branch }}/{{ page.main_src }}/MainApp.java). It is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `del 1`. This deletes the first item from the List Box. 

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its _API_ in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

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

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `FoodRemParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `NewCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add an item).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("del 1")` API call.

![Interactions Inside the Logic Component for the `del 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `FoodRemParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `NewCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `NewCommand`) which the `FoodRemParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `NewCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model Component

**API** : [`Model.java`]({{ page.master_branch }}/{{ page.main_src }}/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The `Model` component,

* stores the FoodRem data i.e., all `Item` objects (which are contained in a `UniqueItemList` object).
* stores the currently 'selected' `Item` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Item>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `FoodRem`, which `Item` references. This allows `FoodRem` to only require one `Tag` object per unique tag, instead of each `Item` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>
{% include_relative _dg/ModelComponent.md %}

### Storage Component

**API** : [`Storage.java`]({{ page.master_branch }}/{{ page.main_src }}/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

* can save both FoodRem data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `FoodRemStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common Classes

Classes used by multiple components are in the `seedu.foodrem.commons` package.

---

## Implementation

{% include_relative _dg/Implementation.md %}

___

## Documentation, Logging, Testing, Configuration, Dev-Ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

---

## Appendix: Requirements

### Product Scope

**Target user profile**: Purchasing managers who are proficient with typing for small F&B businesses

**Value proposition**: FoodREM empowers small food and beverage (F&B) restaurants to manage inventory and obtain insights from inventory data.

### User Stories

{% include_relative _dg/UserStories.md %}

### Use Cases

{% include_relative _dg/UseCases.md %}

### Non-Functional Requirements

{% include_relative _dg/NFRs.md %}

## Glossary

{% include glossary.md type="dg" %}

---

## Appendix: Instructions for Manual Testing

{% include_relative _dg/InstructionsForManualTesting.md %}


