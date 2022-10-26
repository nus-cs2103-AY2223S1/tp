package seedu.taassist.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Panel containing the list of module classes.
 */
public class ModuleClassListPanel extends UiPart<Region> {
    private static final String FXML = "ModuleClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModuleClassListPanel.class);

    @FXML
    private ListView<ModuleClass> moduleClassListView;

    /**
     * Creates a {@code ModuleClassListPanel} with the given {@code ObservableList}.
     */
    public ModuleClassListPanel(ObservableList<ModuleClass> moduleClassList) {
        super(FXML);
        moduleClassListView.setItems(moduleClassList);
        moduleClassListView.setCellFactory(listView -> new ModuleClassListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ModuleClass} using a {@code ModuleClassCard}.
     */
    class ModuleClassListViewCell extends ListCell<ModuleClass> {
        @Override
        protected void updateItem(ModuleClass moduleClass, boolean empty) {
            super.updateItem(moduleClass, empty);

            if (empty || moduleClass == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleClassCard(moduleClass).getRoot());
            }
        }
    }

}
