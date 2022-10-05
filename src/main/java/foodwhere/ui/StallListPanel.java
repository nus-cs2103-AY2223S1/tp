package foodwhere.ui;

import java.util.logging.Logger;

import foodwhere.commons.core.LogsCenter;
import foodwhere.model.stall.Stall;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of persons.
 */
public class StallListPanel extends UiPart<Region> {
    private static final String FXML = "StallListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StallListPanel.class);

    @FXML
    private ListView<Stall> stallListView;

    /**
     * Creates a {@code StallListPanel} with the given {@code ObservableList}.
     */
    public StallListPanel(ObservableList<Stall> stallList) {
        super(FXML);
        stallListView.setItems(stallList);
        stallListView.setCellFactory(listView -> new StallListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Stall} using a {@code StallCard}.
     */
    class StallListViewCell extends ListCell<Stall> {
        @Override
        protected void updateItem(Stall stall, boolean empty) {
            super.updateItem(stall, empty);

            if (empty || stall == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StallCard(stall, getIndex() + 1).getRoot());
            }
        }
    }

}
