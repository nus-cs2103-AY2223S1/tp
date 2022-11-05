package foodwhere.ui;

import java.util.logging.Logger;

import foodwhere.commons.core.LogsCenter;
import foodwhere.model.review.Review;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
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
        logger.info("Populating reviews from storage...");
        reviewListView.setItems(reviewList);
        reviewListView.setCellFactory(listView -> new ReviewListViewCell());
    }

    //@@author hikoya-reused
    /**
     * Handles the activity when the user clicks on the review in the list.
     *
     * Adapted code from https://stackoverflow.com/questions/20635192/how-to-create-popup-menu.
     */
    @FXML
    public void handleMouseClicked() {
        final Review selectedReview = reviewListView.getSelectionModel().getSelectedItem();

        if (selectedReview != null) {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ContextMenu contextMenu = new ContextMenu();
            final ObservableList<MenuItem> contextMenuItems = contextMenu.getItems();

            CopyMenuItem<Review> copyReviewName = new CopyMenuItem<>("Copy Name",
                    selectedReview, clipboard, CopyMenuItem.Action.FIELDS_NAME);
            CopyMenuItem<Review> copyReviewAddress = new CopyMenuItem<>("Copy Address",
                    selectedReview, clipboard, CopyMenuItem.Action.FIELDS_ADDRESS);
            CopyMenuItem<Review> copyReviewDate = new CopyMenuItem<>("Copy Date",
                    selectedReview, clipboard, CopyMenuItem.Action.FIELDS_DATE);
            CopyMenuItem<Review> copyReviewContent = new CopyMenuItem<>("Copy Content",
                    selectedReview, clipboard, CopyMenuItem.Action.FIELDS_CONTENT);
            CopyMenuItem<Review> copyReviewRating = new CopyMenuItem<>("Copy Rating",
                    selectedReview, clipboard, CopyMenuItem.Action.FIELDS_RATING);

            contextMenuItems.addAll(copyReviewName, copyReviewAddress,
                    copyReviewDate, copyReviewContent, copyReviewRating);

            if (!selectedReview.getTags().isEmpty()) {
                CopyMenuItem<Review> copyReviewTag = new CopyMenuItem<>("Copy Tag",
                        selectedReview, clipboard, CopyMenuItem.Action.FIELDS_TAG);
                contextMenuItems.add(copyReviewTag);
            }

            reviewListView.setContextMenu(contextMenu);
        }
    }
    //@@author hikoya

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
