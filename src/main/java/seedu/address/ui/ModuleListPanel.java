package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Module;

/**
 * Panel containing the list of modules.
 */
public class ModuleListPanel extends UiPart<Region> {
    private static final String FXML = "ModuleListPanel.fxml";
    // Value 0 corresponding to the index of home status in observable list.
    private static final int INDEX_OF_HOME_STATUS = 0;
    private final Logger logger = LogsCenter.getLogger(ModuleListPanel.class);
    private ObservableList<Boolean> isHomeStatus;

    @FXML
    private ListView<Module> moduleListView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}
     * of modules.
     */
    public ModuleListPanel(ObservableList<Module> moduleList,
                           ObservableList<Boolean> isHomeStatus) {
        super(FXML);
        this.isHomeStatus = isHomeStatus;
        moduleListView.setItems(moduleList);
        moduleListView.setCellFactory(listView -> new ModuleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Module}
     * using a {@code ModuleCard}.
     */
    class ModuleListViewCell extends ListCell<Module> {
        @Override
        protected void updateItem(Module module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleCard(module, getIndex() + 1,
                        isHomeStatus).getRoot());
            }
        }
    }
}
