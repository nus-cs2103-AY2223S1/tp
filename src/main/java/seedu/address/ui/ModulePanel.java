package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Module;

/**
 * Panel containing more details of the module.
 */
public class ModulePanel extends UiPart<Region> {

    private static final String FXML = "ModulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModulePanel.class);

    @javafx.fxml.FXML
    private ListView<Module> moduleView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public ModulePanel(ObservableList<Module> moduleList) {
        super(FXML);
        moduleView.setItems(moduleList);
        moduleView.setCellFactory(listView -> new ModuleViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Module} using a {@code ModuleCard}.
     */
    static class ModuleViewCell extends ListCell<Module> {
        @Override
        protected void updateItem(Module module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleInfoCard(module).getRoot());
            }
        }
    }
}
