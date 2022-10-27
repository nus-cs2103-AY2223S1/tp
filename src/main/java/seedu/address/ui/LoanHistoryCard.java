package seedu.address.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.LoanHistory;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class LoanHistoryCard extends UiPart<Region> {

    private static final String FXML = "LoanHistoryCard.fxml";

    private static final String INCREASE_IMAGE_PATH = "/images/increase_arrow.png";
    private static final String DECREASE_IMAGE_PATH = "/images/decrease_arrow.png";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final LoanHistory history;

    @FXML
    private HBox cardPane;
    @FXML
    private ImageView changeIdentifierImage;
    @FXML
    private Label currentLoanAmount;
    @FXML
    private Label changeInAmount;
    @FXML
    private Label reason;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public LoanHistoryCard(LoanHistory history, String updatedAmountString) {
        super(FXML);
        this.history = history;
        currentLoanAmount.setText(updatedAmountString);

        changeIdentifierImage.setImage(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream(history.getLoanChange().getAmount() > 0
                        ? INCREASE_IMAGE_PATH
                        : DECREASE_IMAGE_PATH))
        ));

        changeInAmount.setText(this.history.getLoanChange().toString(true));

        reason.setText(this.history.getReason().toString());
    }

    /**
     * Binds the width of the contents of this card to the width of the list view
     * @param listView the list view to bind to
     * @param padding the padding applied after the width property
     */
    public <T> void bindWidth(ListView<T> listView, double padding) {
        getRoot().maxWidthProperty().bind(listView.widthProperty().subtract(padding));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LoanHistoryCard)) {
            return false;
        }

        // state check
        LoanHistoryCard card = (LoanHistoryCard) other;
        return history.equals(card.history);
    }
}
