#### Feature: Create a Tag

##### Overview

<!-- TODO: ACTIVITY DIAGRAM -->

<!-- TODO: Short Description of Command -->

##### `tag item` Feature Details

<!-- TODO: SEQUENCE DIAGRAM -->

<!-- TODO: Description of how Command works -->

##### Feature Considerations

<!-- TODO: Command Considerations -->

#### Feature: Tag an Item

##### Overview

<!-- TODO: ACTIVITY DIAGRAM -->

The `tag item` command tags an item with the provided tag name in FoodRem. If both the item and the tag are valid, the item will be tagged successfully.

##### `tag item` Feature Details

![TagSequenceDiagram](images/TagSequenceDiagram.png)

1. The user specifies a tag name and the item index (which corresponds to the index of the item displayed on the item list on FoodRem UI).
1. If the tag name or index is not provided, the user will be prompted to enter them correctly via an error message.
1. The tag name is cross-referenced with the current tags in the database and an error is thrown if the tag does not exist in the database.
1. The index is cross-referenced to the list of item displayed and an error would be thrown if the index is out of range of the list. This informs the user that the item does not exist.
1. If the item index and tag name are both valid, the item is checked to determine whether that item contains the specified tag. If the specified tag is already contained in the item, an error would be thrown to inform user about the duplicate tag.
1. If Step 5 completes without any exceptions, a new copy of the item is created and the tag would be added to that item. This new copy of item would replace the original item in the database.

##### Feature Considerations

* When the `TagCommand` is executed to tag a tag to an item, a copy of the item is created and the tag is added to it before replacing this new copy of the item with the original item in the list of items in FoodRem. We chose to replace the original item with the new item because this will allow the UI to detect a change in the `UniqueItemList` and thus update and show the item with their new tag included.

#### Feature: Filter by Tag

##### Overview

<!-- TODO: ACTIVITY DIAGRAM -->

<!-- TODO: Short Description of Command -->

##### `filtertag` Feature Details

<!-- TODO: SEQUENCE DIAGRAM -->

<!-- TODO: Description of how Command works -->

##### Feature Considerations

<!-- TODO: Command Considerations -->
