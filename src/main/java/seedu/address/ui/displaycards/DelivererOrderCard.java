package seedu.address.ui.displaycards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code Order} relevant to the deliverer.
 */
public class DelivererOrderCard extends UiPart<Region> {

    private static final String FXML = "DelivererOrderListCard.fxml";
    private final Order order;
    private final int displayedIndex;

    @FXML
    private Label age;

    @FXML
    private Label buyerName;

    @FXML
    private Label buyerPhone;

    @FXML
    private Label color;

    @FXML
    private Label colorPattern;

    @FXML
    private Label estimatedArrivalDate;

    @FXML
    private Label id;

    @FXML
    private Label orderStatus;

    @FXML
    private Label species;

    @FXML
    private Label toAddress;

    /**
     * Creates a {@code DelivererOrderCard} with the given {@code Order} and index to display.
     */
    public DelivererOrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        this.displayedIndex = displayedIndex;
        fillBriefOrderCard();
    }

    /**
     * Fills the relevant fields in the brief order card.
     */
    public void fillBriefOrderCard() {
        // Set order information
        id.setText("#" + displayedIndex);
        buyerName.setText("from " + order.getBuyer().getName().fullName);
        orderStatus.setText(order.getOrderStatus().getStatus());
        species.setText(order.getRequest().getRequestedSpecies().toString());
        age.setText(order.getRequest().getRequestedAge().toString());
        color.setText(order.getRequest().getRequestedColor().toString());
        colorPattern.setText(order.getRequest().getRequestedColorPattern().toString());

        // Set buyer information for delivering
        toAddress.setText("To: " + order.getBuyer().getAddress().value);
        buyerPhone.setText("Buyer contact: " + order.getBuyer().getPhone().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DelivererOrderCard)) {
            return false;
        }

        // state check
        DelivererOrderCard card = (DelivererOrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}
