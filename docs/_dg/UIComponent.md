<!-- markdownlint-disable-file first-line-h1 -->
The UI component handles the user-interface portion of the application.

**API** : [Ui.java]({{ page.master_branch }}/{{ page.main_src }}/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts, e.g.`CommandBox`, `ResultDisplay`, `ItemListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible [[ graphical-user-interface:GUI ]].

The `UI` component uses the JavaFX UI framework. The layout of these UI parts is defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [MainWindow]({{ page.master_branch }}/{{ page.main_src }}/ui/MainWindow.java) is specified in [MainWindow.fxml]({{ page.master_branch }}/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user [[ command:commands ]] using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component because the `UI` relies on it to execute commands.
* depends on some classes in the `Model` component, as it displays `Item` objects residing in the `Model`.
