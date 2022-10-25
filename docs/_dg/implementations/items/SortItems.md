### Sorting Items

#### Implementation

The `sort` feature sorts the list of items currently displayed to the user by specified flag(s). It is possible to sort by one or more criteria. A list of available criteria for sorting includes:

* Name
* Quantity
* Type
* Quantity
* Bought Date
* Expiry Date

Currently, sorting is performed ascendingly. A future implementation will allow sorting either ascendingly or descendingly.

#### How the `sort` command works

1. The user can perform other commands that modifies the presented item list, such as the `find` command.
1. The user specifies one or more criteria used for sorting. Available criteria for sorting are listed above.
1. If the displayed item list is empty, then an empty list is displayed.
1. If the item list is not empty, the items are sorted according to the specified criteria. Sorting is performed in order of the criteria specified.

![TagSequenceDiagram](images/SortItemsSequenceDiagram.png)

#### Why is it implemented this way

There is a `SortedList` obtained from an immutable item list. When the `SortCommand` is executed to sort the list of items, one or more comparators are chosen depending on the provided sorting criteria. the comparators are combined into one comparator. The `SortedList` are sorted in the specified order of the Comparators. The UI tracks changes to the `SortedList` and displays the updated sorted item list.

Notably, the `SortedList` wraps around a `FilteredList`, which wraps around an immutable `ObservableList`.

#### Receiving help for a command

The `help` feature provides a user with instructions of how to use certain commands.

#### How the `help` command works

1. The user specifies a specific command that they need help with. This is done using the word needed to execute a particular command in FoodRem.
1. If this is not provided, a general help message will be shown.
1. The provided command is cross-referenced with all available commands in FoodRem. If the command that the user needs help with does not exist, an error would be thrown. This informs the user that the command does not exist. A general help is also shown to the user.
1. The help is shown in a new window that will open upon successful execution of the command.

#### Why it is implemented this way

When the `HelpCommand` is executed we want users to receive help immediately instead of searching for it the User Guide.

![HelpSequenceDiagram](images/HelpSequenceDiagram.png)

Only methods relevant to showing the HelpWindow was shown. Other methods such as `setFeedbackToUser` and `isShowHelp` is not shown.
