<!-- markdownlint-disable-file first-line-h1 -->
#### General Implementation Details

FoodRem's User Interface or UI (more specifically, it is a [[ graphical-user-interface:GUI ]]) consists of a main window and the Help Window. In this section, we will focus on the main window only, specifically, the Command Output Box component of the main window and its use of the "views", "models" and "view models".

#### General Design Considerations

The main window comprises of three components: the [[ command-input-box:Command Input Box ]] and [[ item-list-box:Item List Box ]] on the left half of the main window, as well as the [[ command-output-box:Command Output Box ]] on the right half. For the Command Input Box, it only needs to render the same component each time in order to provide a text field for the user to type their commands into. Likewise, for the Item List Box, it only needs to render a list each time. While the number of items in the list may be variable, it is still the same component to render the list (specifically, it is JavaFX's `ListView` component). This means that when a command is executed, any UI updates to these first two components can be achieved relatively easily.

However, the Command Output Box needs to be able to have potentially different rendered views to be shown to the user, depending on what command was run. This is where the "views", "models" and "view models" come in. They are clarified as follows:

* "Views" return a JavaFX `Node` object in order to place into the Command Output Box
* "Models" represent data entities used in FoodRem. See the [Model Component](#model-component) section above for more information. The "models" that are concerned in the process of rendering outputs to the GUI are [[ item:items ]] and [[ tag:tags ]].
* "View models" are not always present, and act as a bridge for when the data contained in a single "model" is not enough for the corresponding "view" to be generated. As a "view" can only be generated from a single "view model", when data from one or more "models" need to be processed and/or augmented before a "view" can be generated, the "view model" acts as a bridge to facilitate the process.

#### Rendering Command Output

[`UiView`]({{ page.master_branch }}/{{ page.main_src }}/views/UiView.java) is the main class responsible for rendering the view to the Command Output Box. This is achieved when a caller calls `UiView`'s `viewFrom` method. The method then calls the `from` method of one of nine "views", depending on the type of the object that was requested to be rendered.

The nine "views" are as follows:

| Class Name                                                                                           | Description                                                                                                                                                                   |
|------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`FilterByTagView`]({{ page.master_branch }}/{{ page.main_src }}/views/FilterByTagView.java)         | A view resulting from the `filtertag` command.                                                                                                                                |
| [`ItemView`]({{ page.master_branch }}/{{ page.main_src }}/views/ItemView.java)                       | A view resulting from the `view` command, as well as a generic view for items which may be used elsewhere.                                                                    |
| [`ItemWithMessageView`]({{ page.master_branch }}/{{ page.main_src }}/views/ItemWithMessageView.java) | A generic view of an item with a single message above it. This view results from the  `dec`, `del`, `edit`, `inc`, `new`, `rmk`, `tag` and `untag` commands.                  |
| [`StatsView`]({{ page.master_branch }}/{{ page.main_src }}/views/StatsView.java)                     | A view resulting from the `stats` command.                                                                                                                                    |
| [`StringView`]({{ page.master_branch }}/{{ page.main_src }}/views/StringView.java)                   | A generic view for a string message. This view is used to display command errors, as well as the outputs of the `exit`, `help`, `reset`, `find`, `list`, and `sort` commands. |
| [`TagToRenameView`]({{ page.master_branch }}/{{ page.main_src }}/views/TagToRenameView.java)         | A view resulting from the `renametag` command.                                                                                                                                |
| [`TagView`]({{ page.master_branch }}/{{ page.main_src }}/views/TagView.java)                         | A generic view for a [[ tag:Tag ]]. Used in other views to ensure consistency of how a Tag looks like.                                                                        |
| [`TagsView`]({{ page.master_branch }}/{{ page.main_src }}/views/TagsView.java)                       | A generic view for multiple [[ tag:tags ]], for use in other views.                                                                                                           |
| [`TagsWithMessageView`]({{ page.master_branch }}/{{ page.main_src }}/views/TagsWithMessageView.java) | A view of some tag(s) with a message above them. This view results from the `deletetag`, `listtag` and `newtag` commands.                                                     |

The individual "view" is responsible to generate the JavaFX `Node` structure to be placed into the Command Output Box. This node structure is then placed by `UiView`.

A sequence diagram of this process is as follows:

![](images/UISequenceDiagram.png)

```note
The above sequence diagram assumes the caller requested a `String` to be rendered.
```

##### Future Extensions

Currently, `UiView` uses a very hacky method to determine the type of the object that was requested to be rendered; it uses consecutive `instanceof` expressions. Ideally, there should be a common interface where the necessary operations required can be achieved through dynamic binding. A possible class diagram is as follows:

![](images/UIProposedClassDiagram.png)
