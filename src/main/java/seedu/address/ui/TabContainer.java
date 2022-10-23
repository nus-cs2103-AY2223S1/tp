package seedu.address.ui;

import java.util.Arrays;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

/**
 * Container containing a list of tabs.
 */
public class TabContainer extends UiPart<Region> {

    private static final String FXML = "TabContainer.fxml";

    @FXML
    private TabPane tabContainer;

    SingleSelectionModel<Tab> selectionModel = tabContainer.getSelectionModel();

    /**
     * Creates a {@code TabContainer} with a given {@code Tab}.
     *
     * @param tabs Variable number of tabs
     */
    public TabContainer(Tab... tabs) {
        super(FXML);
        ObservableList<Tab> tabList = tabContainer.getTabs();
        tabList.addAll(Arrays.asList(tabs));
    }

    /**
     * Toggles tabs in tabcontainer. When id is 0, toggle to Contact tab. When id is 1, toggle to Task tab.
     * When id is 2, toggle to Tag tab.
     * @param id the unique id of the tab to be shown.
     */
    public void toggleTabs(int id) {
        if (id >= 0 && id < 4) {
            selectionModel.select(id);
        }
    }
}
