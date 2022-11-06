package foodwhere.ui;

import java.util.Objects;

import foodwhere.model.review.Review;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Review}.
 */
public class ReviewCard extends UiPart<Region> {

    private static final String FXML = "ReviewListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Review review;

    @FXML
    private GridPane gridPane;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label address;
    @FXML
    private Label date;
    @FXML
    private Label content;
    @FXML
    private Label rating;
    @FXML
    private ImageView ratingIcon;
    @FXML
    private Label tags;
    @FXML
    private Label tagsLabel;

    /**
     * Creates a {@code ReviewCode} with the given {@code Review} and index to display.
     */
    public ReviewCard(Review review, int displayedIndex) {
        super(FXML);
        this.review = review;
        id.setText(displayedIndex + ". ");
        address.setText(review.getAddress().value);
        name.setText(review.getName().fullName);
        date.setText(review.getDate().value);
        content.setText(review.getContent().value);

        setRatingIcon();

        if (!review.getTags().isEmpty()) {
            tags.setText(review.getTagString());
            tagsLabel.setText("Tags:");
        } else {
            removeRow(gridPane, GridPane.getRowIndex(tagsLabel));
        }
    }

    /**
     * Sets the image of ratingIcon {@code ImageView} and the text of ratings {@code Label}.
     */
    private void setRatingIcon() {
        Image ratingIconImage;

        Integer val = review.getRating().value;
        switch (val) {
        case 0:
            ratingIconImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/stars_0.png")));
            break;
        case 1:
            ratingIconImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/stars_1.png")));
            break;
        case 2:
            ratingIconImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/stars_2.png")));
            break;
        case 3:
            ratingIconImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/stars_3.png")));
            break;
        case 4:
            ratingIconImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/stars_4.png")));
            break;
        case 5:
            ratingIconImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/stars_5.png")));
            break;
        default:
            throw new RuntimeException("Invalid rating");
        }

        ratingIcon.setImage(ratingIconImage);
    }

    //@@author hikoya-reused
    /**
     * Adapted from https://stackoverflow.com/a/70961583.
     * Gets row index constrain for given node, forcefully as integer: 0 as null.
     * @param node Node to look up the constraint for.
     * @return The row index as primitive integer.
     */
    public static int getRowIndexAsInteger(Node node) {
        final var a = GridPane.getRowIndex(node);
        if (a == null) {
            return 0;
        }
        return a;
    }
    //@@author hikoya

    //@@author hikoya-reused
    /**
     * Adapted from https://stackoverflow.com/a/70961583.
     * Removes row from grid pane by index.
     *
     * @param grid Grid pane to be affected
     * @param targetRowIndexIntegerObject Target row index to be removed. Integer object type,
     *                                    because for some reason `getRowIndex` returns null
     *                                    for children at 0th row.
     */
    private void removeRow(GridPane grid, Integer targetRowIndexIntegerObject) {
        int targetRowIndex = targetRowIndexIntegerObject == null ? 3 : targetRowIndexIntegerObject;

        // Remove children from row
        grid.getChildren().removeIf(node -> (getRowIndexAsInteger(node) == targetRowIndex));

        // Update indexes of other rows, i.e., shift rows up
        grid.getChildren().forEach(node -> {
            int rowIndex = getRowIndexAsInteger(node);

            if (targetRowIndex < rowIndex) {
                GridPane.setRowIndex(node, rowIndex - 1);
            }
        });

        // Remove row constraints
        grid.getRowConstraints().remove(targetRowIndex);
    }
    //@@author

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReviewCard)) {
            return false;
        }

        // state check
        ReviewCard card = (ReviewCard) other;
        return id.getText().equals(card.id.getText())
                && review.equals(card.review);
    }
}
