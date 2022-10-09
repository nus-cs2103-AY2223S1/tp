package seedu.rc4hdb.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.model.resident.Resident;

/**
 * Panel containing the list of residents.
 */
public class ResidentListPanel extends UiPart<Region> {

    private static final String FXML = "ResidentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ResidentListPanel.class);

    @FXML
    private ListView<Resident> residentListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ResidentListPanel(ObservableList<Resident> residentList) {
        super(FXML);
        residentListView.setItems(residentList);
        residentListView.setCellFactory(listView -> new ResidentListPanel.ResidentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ResidentListViewCell extends ListCell<Resident> {
        @Override
        protected void updateItem(Resident resident, boolean empty) {
            super.updateItem(resident, empty);

            if (empty || resident == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ResidentCard(resident, getIndex() + 1).getRoot());
            }
        }
    }

}
