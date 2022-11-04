package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.bill.Bill;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class BillCard extends UiPart<Region> {

    private static final String FXML = "BillListCard.fxml";

    public final Bill bill;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/healthcontact-level4/issues/336">The issue on HealthContact level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label billDate;
    @FXML
    private Label amount;
    @FXML
    private Label paymentStatus;
    @FXML
    private CheckBox isPaid;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public BillCard(Bill bill, int displayedIndex) {
        super(FXML);
        this.bill = bill;
        id.setText(displayedIndex + ". ");
        name.setText(bill.getAppointment().getName().toString());
        billDate.setText(bill.getBillDate().toString());
        amount.setText(bill.getAmount().toString());
        isPaid.setSelected(bill.getPaymentStatus().isPaid());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BillCard)) {
            return false;
        }

        // state check
        BillCard card = (BillCard) other;
        return id.getText().equals(card.id.getText())
                && bill.equals(card.bill);
    }
}
