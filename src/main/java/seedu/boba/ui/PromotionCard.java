package seedu.boba.ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * An UI component that displays information of a {@code Promotion}.
 */
public class PromotionCard extends UiPart<Region> {

    private static final String FXML = "PromotionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on BobaBot level 4</a>
     */

    public final Image image;


    @FXML
    private HBox cardPane;
    @FXML
    private StackPane stackPane;

    /**
     * Creates a {@code PromotionCode} with the given {@code Promotion} and index to display.
     */
    public PromotionCard(Image image, int displayedIndex) {
        super(FXML);
        this.image = image;
        ImageView imageView = new ImageView(image);
        //setting the fit height and width of the image view to the ratio of A4
        imageView.setFitHeight(636.4);
        imageView.setFitWidth(450);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);
        stackPane.getChildren().add(imageView);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PromotionCard)) {
            return false;
        }

        // state check
        PromotionCard card = (PromotionCard) other;
        return image.equals(card.image);
    }
}
