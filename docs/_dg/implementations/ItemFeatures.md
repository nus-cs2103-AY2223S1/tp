<!-- markdownlint-disable-file first-line-h1 -->

{% comment %}
<!-- ===================================================================== -->
<!-- TODO: Copy and paste this template, and add/remove sections as needed -->
<!-- ===================================================================== -->
##### Overview
<!-- ACTIVITY DIAGRAM -->
<!-- Short Description of Command -->
##### Feature Details
<!-- SEQUENCE DIAGRAM -->
<!-- Description of how Command works -->
##### Feature Considerations
<!-- Command Considerations -->
<!-- ===================================================================== -->
{% endcomment %}

#### General Implementation Details

![model_diagram](images/ItemClassDiagram.png)

Items are composed of classes representing the fields available in them. Items are stored in a `UniqueItemList` while tags are stored in a `UniqueTagList`. These lists restrict the maximum number of items and tags in FoodRem.

![model_diagram](images/ItemFieldClassDiagram.png)

Each field in `Item` is dependent on a respective `Validator` and `ItemComparator`. `Validator` is an interface that facilitates validation of fields when `Item` is created. `ItemComparator` is `Comparator` that facilitates sorting of items by its respective fields.

#### Creating an Item
<!-- TODO: Fill up -->

#### Editing an Item
<!-- TODO: Fill up -->

#### Incrementing and Decrementing the quantity of an Item

##### Overview

The `inc` and `dec` feature increments or decrements the quantity of an item. 

##### Feature Details

![HelpActivityDiagram](images/DecrementActivityDiagram.png)

1. The user can specify to increment or decrement the quantity of an item by a specific quantity.
1. The user can also specify to increment or decrement without a provided quantity.

##### Feature Considerations

When implementing this feature, we realised that it is common to increment or decrement just by one unit. We thus decided to provide a default behaviour when incrementing or decrementing an item quantity. 

Another command that have such default behaviour is the `rmk` (remark) feature, where users will delete a current remark if one is not provided. 

#### Sorting an Item

##### Overview

<!-- TODO: ACTIVITY DIAGRAM -->

The `sort` feature sorts the list of items currently displayed to the user by specified [[ flag:flag(s) ]]. It is possible to sort by one or more criteria. A list of available criteria for sorting includes:

* Name
* Quantity
* Unit
* Bought Date
* Expiry Date

Currently, sorting is performed ascendingly. A future implementation will allow sorting either ascendingly or descendingly.

##### Feature Details

1. The user can perform other commands that modifies the presented item list, such as the `find` command.
1. The user specifies one or more criteria used for sorting. Available criteria for sorting are listed above.
1. If the displayed item list is empty, then an empty list is displayed.
1. If the item list is not empty, the items are sorted according to the specified criteria. Sorting is performed in order of the criteria specified.

![TagSequenceDiagram](images/SortItemsSequenceDiagram.png)

##### Feature Considerations

There is a `SortedList` obtained from an immutable item list. When the `SortCommand` is executed to sort the list of items, one or more comparators are chosen depending on the provided sorting criteria. the comparators are combined into one comparator. The `SortedList` are sorted in the specified order of the Comparators. The UI tracks changes to the `SortedList` and displays the updated sorted item list.

Notably, the `SortedList` wraps around a `FilteredList`, which wraps around an immutable `ObservableList`.
