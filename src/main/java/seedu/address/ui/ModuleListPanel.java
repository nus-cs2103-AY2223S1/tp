package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;

/**
 * ModuleListPanel class represents a panel which contains a list of all
 * the module card.
 */
public class ModuleListPanel extends UiPart<Region> {
    //@@author dlimyy-reused
    //Reused with minor modifications from existing AddressBook 3
    private static final String FXML = "ModuleListPanel.fxml";

    @FXML
    private ListView<Module> moduleListView;

    /**
     * The constructor of the ModuleListPanel. Sets the list of modules
     * to the ListView.
     *
     * @param moduleList
     */
    public ModuleListPanel(ObservableList<Module> moduleList) {
        super(FXML);
        moduleListView.setItems(moduleList);
        moduleListView.setCellFactory(listView -> new ModuleListViewCell());
    }

    static class ModuleListViewCell extends ListCell<Module> {
        @Override
        protected void updateItem(Module module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleCard(module, getIndex() + 1).getRoot());
            }
        }
    }

    //@@author
}
