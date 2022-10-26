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

<!-- TODO: ADD TAG CLASS DIAGRAM -->

The tagging functionality is facilitated by the `UniqueTagList` stored in `FoodRem`. Additionally, each `Item` stores its own sets of associated `Tag` objects in an internal `Set<Tag>`.

* Creating, updating and deleting tags will modify the tags within the `UniqueTagList` which contains all existing `Tag` objects.
* Tagging/untagging a `Tag` to an `Item` will add/remove the corresponding `Tag` object to the `Set<Tag>` stored within `Item`.

Given below is an UML diagram of `Tag` and the classes related to it:

![model_diagram](images/BetterModelClassDiagram.png)

#### General Design Considerations

When storing a tag, these are the alternatives to consider.

* **Alternative 1 (current choice):** Store `Tag` in a separate `UniqueTagList` and each `Item` stores its own set of associated `Tag`

  * Pros:
    * Easy to implement
    * Allows users to create and rename `Tag` to suit their own use case instead of just providing a preset list of `Tag` that cannot be modified or extended

  * Cons:
    * Unable to define association/relationship between different `Tag`
    * Additional storage used as compared to below alternatives

* **Alternative 2:** Design a tree-like structure similar to a full type system where users can extend from to create new `Tag`

  * Pros:
    * Able to define association/relationship between different `Tag` (e.g `Apple` can be nested under `Fruits` so tagging an `Item` with `Apple` marks it as a `Fruits` as well)
    * Saves space
      * Only a single tree is needed, no need to store associated `Tag` within `Item`, the classification of `Item` and `Tag` is represented by the relationship represented by this tree
    * Easily extensible (defining a new `Tag` or `Item` can be as simple as creating a new node in the tree)

  * Cons:
    * Example of over engineering, unnecessarily complicates things as compared to alternative 1
    * Unlikely to have deeply nested relationships for `Tag`, adding each `Tag` to an `Item` is much simpler
    * Unlikely to need so many `Tag`, in which case alternative one would result in faster operations by using hash tables instead of a tree

* **Alternative 3:** Use classes and inheritance to represent different classification to achieve similar tagging effect

  * Pros:
    * Less storage used as compared to storing associated `Tag` within `Set` in each `Item` as well as all `Tag` in `UniqueTagList`
    * Possibly more convenient when creating `Item` since users do not have to manually add associated `Tag` after creating the `Item`

  * Cons:
    * Users are unable to extend and edit classes similar to how they can edit `Tag`
    * Not scalable and poor developer experience in creating and maintaining a separate class for every new classification/tag users want to have
    * Java supports only single class inheritance. Limited support for associating `Item` to multiple `Tag`

#### Creating a Tag
<!-- TODO: Fill up -->

#### Tagging an Item

##### Overview

<!-- TODO: ACTIVITY DIAGRAM -->

The `tag item` command tags an item with the provided tag name in FoodRem. If both the item and the tag are valid, the item will be tagged successfully.

##### Feature Details

![TagSequenceDiagram](images/TagSequenceDiagram.png)

1. The user specifies a tag name and the item index (which corresponds to the index of the item displayed on the item list on FoodRem UI).
1. If the tag name or index is not provided, the user will be prompted to enter them correctly via an error message.
1. The tag name is cross-referenced with the current tags in the database and an error is thrown if the tag does not exist in the database.
1. The index is cross-referenced to the list of item displayed and an error would be thrown if the index is out of range of the list. This informs the user that the item does not exist.
1. If the item index and tag name are both valid, the item is checked to determine whether that item contains the specified tag. If the specified tag is already contained in the item, an error would be thrown to inform user about the duplicate tag.
1. If Step 5 completes without any exceptions, a new copy of the item is created and the tag would be added to that item. This new copy of item would replace the original item in the database.

##### Feature Considerations

* When the `TagCommand` is executed to tag a tag to an item, a copy of the item is created and the tag is added to it before replacing this new copy of the item with the original item in the list of items in FoodRem. We chose to replace the original item with the new item because this will allow the UI to detect a change in the `UniqueItemList` and thus update and show the item with their new tag included.

#### Filtering Items by Tag Name
<!-- TODO: Fill up -->
