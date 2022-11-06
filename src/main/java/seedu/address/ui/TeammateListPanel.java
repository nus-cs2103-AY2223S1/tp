package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.teammate.Teammate;

/**
 * Panel containing the list of teammates.
 */
public class TeammateListPanel extends UiPart<Region> {

    private static final String FXML = "TeammateListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TeammateListPanel.class);

    @FXML
    private ListView<Teammate> teammateListView;

    /**
     * Creates a {@code TeammateListPanel} with the given {@code ObservableList}.
     */
    public TeammateListPanel(ObservableList<Teammate> teammateList) {
        super(FXML);
        teammateListView.setItems(teammateList);
        teammateListView.setCellFactory(listView -> new TeammateListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Teammate} using a {@code TeammateCard}.
     */
    static class TeammateListViewCell extends ListCell<Teammate> {
        @Override
        protected void updateItem(Teammate teammate, boolean empty) {
            super.updateItem(teammate, empty);

            if (empty || teammate == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TeammateCard(teammate, getIndex() + 1).getRoot());
            }
        }
    }

}
