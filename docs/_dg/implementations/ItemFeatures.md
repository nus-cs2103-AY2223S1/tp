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

The Item functionality is represented by an internal `UniqueItemList` stored in FoodRem. An item is represented as an `Item` object, which is stored within this `UniqueItemList`. 

Each `Item` can store a number of attributes. The related attributes are:
* `ItemName`: The name of the Item. 
* `ItemQuantity`: The number of units of an Item.
* `ItemBoughtDate`: The date when the Item was bought.
* `ItemExpiryDate`: The date when the Item expires.
* `ItemPrice`: The cost of one unit of the Item.
* `ItemRemark`: Any attached remarks to the Item.

Regarding the attributes, here are a few things to note:
* All attributes except `ItemName` is optional.
* The Item value is the `ItemPrice` multiplied by the `ItemQuantity`. This is used for the [Statistics](#statistics-features) feature.

![model_diagram](images/BetterModelClassDiagram.png)

#### General Design Considerations

The design of Items models very closely to the implementation of a `Person` class in the original AddressBook3 (AB3) codebase, of which FoodRem adapted from. However, there are a few modifications made:
* The `Person` class was modified to fit the business logic of FoodRem. For example, renaming it to `Item`, and including different attributes, as mentioned above. 
* Addition of new helper and getter methods
* Addition of [Tags](#tag-related-features), where Items stores its own sets of associated `Tag` objects in an internal `Set<Tag>`.

It should be noted that the `UniqueItemList` referenced in `ModelManager` is immutable. To interact with Items, `ObservableList` instances and its child classes, `FilteredList` and `SortedList`, are used in the Commands. For example, (filtering an item by its tag name)[#filtering-items-by-tag-name] would modify the `filteredItems` list (which is a `FilteredList`).

#### Creating an Item

##### Overview
The `new` command creates a new `Item` in FoodRem, which forms the core business logic of being able to represent an inventory item. 

The sequence diagram to show the interactions between the different components during a `new` command is as such:

<!-- TODO: UML Diagram -->

##### Feature Details
1. The user specifies an item name for the Item to create. Optionally, the user can specify the item quantity, bought date, expiry date, price, and any remarks.
2. If the item name is not provided, or if a wrong command input is provided, the user will be prompted to enter the command correctly via an error message.
3. The item is cross-referenced in the `Model` to check if it already exists. If it already does, then an error is raised to inform the user.
4. If the item storage of FoodRem is full, an error is thrown to inform the user that the maximum item limit is reached, and that no new items can be added.
5. If step 4 completes without any exceptions, then the new `Item` is successfully created and stored inside the Item.

##### Feature Considerations
It should be noted that when checking for duplicates in the `UniqueItemList` inside the `Model`, Items cannot have the same name. This is because allowing Items with the same name will introduce additional complexity for other commands, and also presents confusingly to the user. This is room for improvement, as items ideally can have the same name. For example, the user should ideally have multiple `Potato` items with different bought and expiry dates. 

When providing multiple arguments with the same delimiter, the last instance of the repeat delimiter is taken during the `parse` command.

#### Editing an Item

##### Overview

The `edit` feature edits the attached attributes of a specified `Item`, which is specified by the one-indexed `itemList` presented to the user. 

Here is the activity diagram showing the process of the `edit` command:

<!-- TODO -->

##### Feature Details
1. The user specifies an item index that represents an `Item` to be edited.  
2. If an invalid index is provided, the user is prompted to enter the command correctly via an error message.
3. If an invalid command input is provided, the user will be prompted to enter the command correctly via an error message.
4. The item is cross-referenced in the `Model` to check if it already exists. If it already does, then an error is raised to inform the user.
5. If step 4 completes without any exceptions, then the new `Item` is successfully edited.

##### Feature Considerations
Similar to the `new` command, it should be noted that when checking for duplicates in the `UniqueItemList` inside the `Model`, Items cannot have the same name. For example, if an `Item` with the name `Potato` already exists inside the inventory, then you cannot edit an existing `Item` to have the name `Potato`.

When providing multiple arguments with the same delimiter, the last instance of the repeat delimiter is taken during the `parse` command.

#### Sorting an Item

##### Overview

<!-- TODO: ACTIVITY DIAGRAM -->

The `sort` feature sorts the list of items currently displayed to the user by specified flag(s). It is possible to sort by one or more criteria. A list of available criteria for sorting includes:

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
