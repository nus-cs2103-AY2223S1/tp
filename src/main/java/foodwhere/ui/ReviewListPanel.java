package foodwhere.ui;

import java.util.logging.Logger;

import foodwhere.commons.core.LogsCenter;
import foodwhere.model.review.Review;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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
     * Handles the activity when the user clicks on the review in the list
     *
     * Adapted code from https://stackoverflow.com/questions/20635192/how-to-create-popup-menu
     */
    @FXML
    public void handleMouseClicked() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        final ContextMenu contextMenu = new ContextMenu();
        MenuItem copyReviewName = new MenuItem("Copy Name");
        MenuItem copyReviewDate = new MenuItem("Copy Date");
        MenuItem copyReviewContent = new MenuItem("Copy Content");
        MenuItem copyReviewTag = new MenuItem("Copy Tag");
        contextMenu.getItems().addAll(copyReviewName, copyReviewDate, copyReviewContent, copyReviewTag);

        reviewListView.setContextMenu(contextMenu);

        copyReviewName.setOnAction((ActionEvent actionEvent) -> {
            content.putString(reviewListView.getSelectionModel().getSelectedItem().getName().toString());
            clipboard.setContent(content);
        });

        copyReviewDate.setOnAction((ActionEvent actionEvent) -> {
            content.putString(reviewListView.getSelectionModel().getSelectedItem().getDate().toString());
            clipboard.setContent(content);
        });

        copyReviewContent.setOnAction((ActionEvent actionEvent) -> {
            content.putString(reviewListView.getSelectionModel().getSelectedItem().getContent().toString());
            clipboard.setContent(content);
        });

        copyReviewTag.setOnAction((ActionEvent actionEvent) -> {
            content.putString(reviewListView.getSelectionModel().getSelectedItem().getTags().toString());
            clipboard.setContent(content);
        });
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
