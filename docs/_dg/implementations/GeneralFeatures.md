<!-- markdownlint-disable-file first-line-h1 -->

#### General Implementation Details

<!-- TODO: ADD GENERAL FEATURES CLASS DIAGRAM -->
<!-- NOTE: As this is for general features like Help, add/remove class diagrams as you see fit -->

The `Exit command` and `Reset command` do not contain complex implementations.

Exit command returns a `CommandResult` that returns `true` when calling the method `shouldExit()` on it.

The reset command functions like most other commands.

The `HelpCommand` is worth looking into, as it involves opening a separate window (`HelpWindow`).

#### Displaying Help Dialogs

The `help` feature provides the user with instructions of how to use a specified command.

##### Feature Details

![HelpActivityDiagram](images/HelpActivityDiagram.png)

1. The user specifies a command that they need help with. This is done using the word needed to execute a particular command in FoodRem.
1. If this is not provided, a general help message will be shown.
1. The provided command is cross-referenced with all available commands in FoodRem. If the command that the user needs help with does not exist, an error would be thrown. This informs the user that the command does not exist. A general help is also shown to the user.
1. The help is shown in a new window that will open upon successful execution of the command.

![HelpSequenceDiagram](images/HelpSequenceDiagram.png)

```note
The lifeline for `HelpCommandParser` and `HelpCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of the diagram.
```

In the above diagram, only methods relevant to showing the HelpWindow was shown. Other methods such as `setFeedbackToUser` and `isShowHelp` is not shown.

##### Feature Considerations

When the `HelpCommand` is executed we want users to receive help immediately instead of searching for it the User Guide.
