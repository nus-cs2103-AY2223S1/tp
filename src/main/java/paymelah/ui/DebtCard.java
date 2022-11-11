package paymelah.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import paymelah.model.debt.Debt;

/**
 * An UI component that displays information of a {@code Debt}.
 */
public class DebtCard extends UiPart<Region> {

    private static final String FXML = "DebtListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Debt debt;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label money;
    @FXML
    private Label dateTime;
    @FXML
    private Label isPaid;

    /**
     * Creates a {@code DebtCard} with the given {@code Debt} and index to display.
     */
    public DebtCard(Debt debt, int displayedIndex) {
        super(FXML);
        this.debt = debt;
        id.setText(displayedIndex + ". ");
        description.setText(debt.getDescription().toString());
        money.setText("$" + debt.getMoney().toString());
        dateTime.setText(debt.getDate().toString() + " " + debt.getTime().toString());
        if (debt.isPaid()) {
            isPaid.setText("PAID");
            isPaid.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, new Insets(1))));
        } else {
            isPaid.setText("UNPAID");
            isPaid.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, new Insets(1))));
        }
        isPaid.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
                new CornerRadii(2), BorderStroke.THIN)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DebtCard)) {
            return false;
        }

        // state check
        DebtCard card = (DebtCard) other;
        return id.getText().equals(card.id.getText())
                && debt.equals(card.debt);
    }
}
