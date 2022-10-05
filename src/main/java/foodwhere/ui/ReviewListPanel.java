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
 * Will be updated to a Review for later iterations.
 */
public class ReviewListPanel extends UiPart<Region> {
    private static final String FXML = "ReviewListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReviewListPanel.class);

    @FXML
    private ListView<Stall> reviewListView;

    /**
     * Creates a {@code ReviewListPanel} with the given {@code ObservableList}.
     */
    public ReviewListPanel(ObservableList<Stall> stallList) {
        super(FXML);
        reviewListView.setItems(stallList);
        reviewListView.setCellFactory(listView -> new ReviewListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Stall} using a {@code PersonCard}.
     */
    class ReviewListViewCell extends ListCell<Stall> {
        @Override
        protected void updateItem(Stall stall, boolean empty) {
            super.updateItem(stall, empty);

            if (empty || stall == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReviewCard(stall, getIndex() + 1).getRoot());
            }
        }
    }

}
