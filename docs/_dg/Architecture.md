<!--markdownlint-disable-file first-line-h1 -->
![](images/ArchitectureDiagram.png)

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

#### Main components of the architecture

`Main` has two classes called [Main]({{ page.master_branch }}/{{ page.main_src }}/Main.java) and [MainApp]({{ page.master_branch }}/{{ page.main_src }}/MainApp.java). It is responsible for:

* Initializing the components in the correct sequence during the app launch.
* Connecting the components with up with each other during the app launch.
* Shutting down the components during shut down.
* Invoking cleanup methods where necessary during shut down.

[`Commons`](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [UI](#ui-component): Handles the User Interface of the App.
* [Logic](#logic-component): Handles the execution of commands.
* [Model](#model-component): Holds the data of the App in memory.
* [Storage](#storage-component): Reads data from, and writes data to, the hard disk.

#### How the architecture components interact with each other

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `del 1`. This deletes the first item from the Item List Box.

![](images/ArchitectureSequenceDiagram.png)

Each of the four main components (also shown in the diagram above),

* defines its _API_ in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name} Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (Reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

![](images/ComponentManagers.png)
