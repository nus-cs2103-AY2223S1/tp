package foodwhere.ui;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import foodwhere.model.review.Review;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Review}.
 * Will be updated to a Review for later iterations.
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
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
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
        name.setText(review.getName().fullName);
        date.setText(review.getDate().value);
        content.setText(review.getContent().value);

        setRatingIcon();

        if (!review.getTags().isEmpty()) {
            String assigneesNames = review.getTags()
                    .stream()
                    .flatMap(rev -> Stream.of(rev.tag))
                    .collect(Collectors.joining(", "));

            tags.setText(assigneesNames);
            tagsLabel.setText("Tag:");
        } else {
            tagsLabel.setText("");
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
