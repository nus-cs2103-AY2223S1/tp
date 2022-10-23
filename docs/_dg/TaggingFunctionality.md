<!-- markdownlint-disable-file first-line-h1 -->
### Tagging functionality

#### Functionality Implementation

The tagging functionality is facilitated by the `UniqueTagList` stored in `FoodRem`. Additionally, each `Item` stores its own sets of associated `Tag` objects in an internal `Set<Tag>`.

Creating, updating and deleting tags will modify the tags within the `UniqueTagList` which contains all existing `Tag` objects.

Tagging/untagging a `Tag` to an `Item` will add/remove the corresponding `Tag` object to the `Set<Tag>` stored within `Item`.

Given below is an UML diagram of `Tag` and the classes related to it:
![model_diagram](../images/BetterModelClassDiagram.png)

#### Design considerations

**Aspect: Storage of `Tag`**

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
