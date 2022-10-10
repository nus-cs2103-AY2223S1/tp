package foodwhere.ui;

import java.util.logging.Logger;

import foodwhere.commons.core.LogsCenter;
import foodwhere.model.review.Review;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of stalls.
 * Will be updated to a Review for later iterations.
 */
public class ReviewListPanel extends UiPart<Region> {
    private static final String FXML = "ReviewListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReviewListPanel.class);

    @FXML
    private ListView<Review> reviewListView;

    /**
     * Creates a {@code ReviewListPanel} with the given {@code ObservableList}.
     */
    public ReviewListPanel(ObservableList<Review> reviewList) {
        super(FXML);
        reviewListView.setItems(reviewList);
        reviewListView.setCellFactory(listView -> new ReviewListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Review} using a {@code ReviewCard}.
     */
    class ReviewListViewCell extends ListCell<Review> {
        @Override
        protected void updateItem(Review review, boolean empty) {
            super.updateItem(review, empty);

            if (empty || review == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReviewCard(review, getIndex() + 1).getRoot());
            }
        }
    }

}
