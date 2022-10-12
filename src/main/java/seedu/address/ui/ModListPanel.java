package seedu.address.ui;

import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class ModListPanel extends UiPart<Region> {
    private static final String FXML = "ModListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModListPanel.class);

    @FXML
    private ListView<Mod> modListView;

    /**
     * Creates a {@code ModListPanel} with the given {@code ObservableList}.
     */
    public ModListPanel() {
        super(FXML);
    }

    public void setPersonModList(Person person) {
        ObservableList<Mod> moduleList = person.getMods();
        modListView.setItems(moduleList);
        modListView.setCellFactory(listView -> new ModListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ModListViewCell extends ListCell<Mod> {
        @Override
        protected void updateItem(Mod module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModCard(module, getIndex() + 1).getRoot());
            }
        }
    }
}
