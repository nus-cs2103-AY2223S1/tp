<!-- markdownlint-disable-file first-line-h1 -->
**API** : [`Model.java`](/src/main/java/seedu/foodrem/model/Model.java)

![](images/ModelClassDiagram.png)

The `Model` component,

* stores the food rem data i.e., all `Item` and `Tag` objects (which are contained in the respective `UniqueItemList` and `UniqueTagList` objects).
* stores the currently 'selected' `Item` and `Tag` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as unmodifiable `ObservableList<Item>` and `ObservableList<Tag>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)
