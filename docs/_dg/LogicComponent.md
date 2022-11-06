<!-- markdownlint-disable-file first-line-h1 -->
The Logic Component handles the execution of commands.

**API** : [Logic.java]({{ page.master_branch }}/{{ page.main_src }}/logic/Logic.java)

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
