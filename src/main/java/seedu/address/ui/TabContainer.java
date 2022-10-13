package seedu.address.ui;

import java.util.Arrays;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
}
