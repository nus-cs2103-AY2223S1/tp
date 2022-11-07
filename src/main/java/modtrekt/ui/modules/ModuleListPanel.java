package modtrekt.ui.modules;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import modtrekt.commons.core.LogsCenter;
import modtrekt.model.module.Module;
import modtrekt.ui.UiPart;

/**
 * Panel containing the list of modules.
 */
public class ModuleListPanel extends UiPart<Region> {
    private static final String FXML = "modules/ModuleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModuleListPanel.class);

    @FXML
    private ListView<Module> moduleListView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public ModuleListPanel(ObservableList<Module> moduleList) {
        super(FXML);
        moduleListView.setItems(moduleList);
        moduleListView.setCellFactory(listView -> new ModuleListPanelCell());
        moduleList.addListener((ListChangeListener<? super Module>) this::handleChange);
    }

    /**
     * Handles change in the list of modules by highlighting the ModuleCard addition to the list, if any.
     */
    private void handleChange(ListChangeListener.Change<? extends Module> change) {
        moduleListView.getSelectionModel().clearSelection();
        moduleListView.getFocusModel().focus(-1);
        while (change.next()) {
            logger.fine(change.toString());
            if (!moduleListView.getItems().isEmpty() && change.wasAdded() && change.getAddedSize() == 1) {
                // We only care about additions of size 1 because those are the only kinds of changes we
                // should set selection and focus to (multi-selection doesn't make sense because filtering (e.g. ls -a)
                // affects the entire list).
                // We ignore removals because there's nothing to focus and select after it's removed.
                int changeIndex = change.getFrom();
                moduleListView.scrollTo(changeIndex);
                moduleListView.getSelectionModel().select(changeIndex);
                moduleListView.getFocusModel().focus(changeIndex);
                return;
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Module} using a {@code
     * ModuleCard}.
     */
    private static class ModuleListPanelCell extends ListCell<Module> {
        @Override
        protected void updateItem(Module module, boolean empty) {
            super.updateItem(module, empty);
            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleCard(module).getRoot());
            }
        }
    }
}
