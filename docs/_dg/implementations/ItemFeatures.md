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

The `Item` object is composed of classes representing the attribute available in them. Items are stored in a `UniqueItemList` while tags are stored in a `UniqueTagList`. These lists restrict the maximum number of items and tags in FoodRem.

The related attributes of an item are:

* `ItemName`: The name of the Item.
* `ItemQuantity`: The number of units of an Item.
* `ItemUnit`: The unit of measurement of an Item.
* `ItemBoughtDate`: The date when the Item was bought.
* `ItemExpiryDate`: The date when the Item expires.
* `ItemPrice`: The cost of one unit of the Item.
* `ItemRemark`: Any attached remarks to the Item.

Regarding the attributes, here are a few things to note:

* The Item value is the `ItemPrice` multiplied by the `ItemQuantity`. This is used for the [Statistics](#statistics-features) feature.
* Each attribute in `Item` is dependent on a respective `Validator` and `ItemComparator`. `Validator` is an interface that facilitates validation of fields when `Item` is created. `ItemComparator` is `Comparator` that facilitates sorting of items by its respective attributes.

![model_diagram](images/ItemFieldClassDiagram.png)

#### General Design Considerations

The design of Items models very closely to the implementation of a `Person` class in the original AddressBook3 (AB3) codebase, of which FoodRem adapted from. However, there are a few modifications made:

* The `Person` class was modified to fit the business logic of FoodRem. For example, renaming it to `Item`, and including different attributes, as mentioned above.
* Addition of new helper and getter methods
* Addition of [Tags](#tag-related-features), where Items stores its own sets of associated `Tag` objects in an internal `Set<Tag>`.

It should be noted that the `UniqueItemList` referenced in `ModelManager` is immutable. To interact with Items, `ObservableList` instances and its child classes, `FilteredList` and `SortedList`, are used in the Commands. For example, [filtering an item by its tag name](#filtering-items-by-tag-name) would modify the `filteredItems` list (which is a `FilteredList`).

#### Creating an Item

##### Overview

The `new` command creates a new `Item` in FoodRem, which forms the core business logic of being able to represent an inventory item.

The activity diagram is as such:

![ItemSequenceDiagram](images/NewItemActivityDiagram.png)

The sequence diagram to show the interactions between the different components during a `new` command is as such:

![ItemSequenceDiagram](images/NewItemSequenceDiagram.png)

This diagram excludes the instantiation of the objects that represents attributes in an Item, e.g. `ItemQuantity`, `ItemUnit`. This is because including all of them would cause the UML diagram to be cluttered and too small to read.

##### Feature Details

1. The user specifies an item name for the Item to create. Optionally, the user can specify the item quantity, bought date, expiry date, price, and any remarks.
1. If the item name is not provided, or if an invalid command arguments are provided, the user will be prompted to enter the command correctly via an error message.
1. The item is cross-referenced in the `Model` to check if it already exists. If it already does, then an error is raised to inform the user.
1. If the item storage of FoodRem is full, an error is thrown to inform the user that the maximum item limit is reached, and that no new items can be added.
1. If step 4 completes without any exceptions, then the new `Item` is successfully created and stored inside the Item.

##### Feature Considerations

It should be noted that when checking for duplicates in the `UniqueItemList` inside the `Model`, Items cannot have the same name. This is because allowing Items with the same name will introduce additional complexity for other commands, and also presents confusingly to the user. This is room for improvement, as items ideally can have the same name. For example, the user should ideally have multiple `Potato` items with different bought and expiry dates.

When providing multiple arguments with the same delimiter, the last instance of the repeat delimiter is taken during the `parse` command.

#### Editing an Item

##### Overview

The `edit` feature edits the attached attributes of a specified `Item`, which is specified by the one-indexed `itemList` presented to the user.

The activity diagram is as such:

![ItemSequenceDiagram](images/EditItemActivityDiagram.png)

Here is the activity diagram showing the process of the `edit` command:

![ItemSequenceDiagram](images/EditItemSequenceDiagram.png)

This diagram excludes the instantiation of the objects that represents attributes in an Item, e.g. `ItemQuantity`, `ItemUnit`, when the `EditCommand` object creates an `editedItem`. This is because including all of them would cause the UML diagram to be cluttered and too small to read.

##### Feature Details

1. The user specifies an item index that represents an `Item` to be edited.
1. If a negative or zero index is provided, an error is thrown and the user is prompted to enter the command correctly via an error message.
1. At least one field to be edited has to be provided. Else, the user will be prompted to enter the command correctly via an error message.
1. The item is cross-referenced in the `Model` to check if it already exists. If it already does, then an error is raised to inform the user.
1. Finally, if an index that is not in the valid range of the Item List is provided, an error is thrown and the user is prompted to enter the command correctly via an error message.
1. If step 4 completes without any exceptions, then the new `Item` is successfully edited.

##### Feature Considerations

Similar to the `new` command, it should be noted that when checking for duplicates in the `UniqueItemList` inside the `Model`, Items cannot have the same name. For example, if an `Item` with the name `Potato` already exists inside the inventory, then you cannot edit an existing `Item` to have the name `Potato`.

When providing multiple arguments with the same delimiter, the last instance of the repeat delimiter is taken during the `parse` command.

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

The `sort` feature sorts the list of items currently displayed to the user by a specified [[ flag ]]. A list of available criteria for sorting includes:

* Name
* Quantity
* Unit
* Bought Date
* Expiry Date
* Price
* Remarks

Currently, sorting is performed in ascending order. A future implementation will allow sorting either ascendingly or descendingly.

##### Feature Details

1. The user can perform other commands that modify the presented item list, such as the `find` command.
1. The user specifies one or more criteria used for sorting. The available criteria for sorting are listed above.
1. If the displayed item list is empty, then an empty list is displayed.
1. If the item list is not empty, the items are sorted according to the specified criteria. Sorting is performed in order of the criteria specified.

![TagSequenceDiagram](images/SortItemsSequenceDiagram.png)

##### Feature Considerations

There is a `SortedList` obtained from an immutable item list. When the `SortCommand` is executed to sort the list of items, depending on the input, the appropriate comparator is chosen and applied. The UI tracks changes to the `SortedList` and displays the updated sorted item list.

It should be noted that if multiple comparators are provided as arguments, only the last argument is considered when selecting comparators.
