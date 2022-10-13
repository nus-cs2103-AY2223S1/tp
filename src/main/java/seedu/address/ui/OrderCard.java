package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;

/**
 * An UI component that displays information of a {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Order order;
    private final int displayedIndex;

    @FXML
    private Label additionalRequestsDescription;

    @FXML
    private Label age;

    @FXML
    private Label buyerName;

    @FXML
    private Label color;

    @FXML
    private Label colorPattern;

    @FXML
    private Label id;

    @FXML
    private Label orderStatus;

    @FXML
    private Label priceRange;

    @FXML
    private Label settledPrice;

    @FXML
    private Label species;

    /**
     * Creates a {@code BuyerCode} with the given {@code Buyer} and index to display.
     */
    public OrderCard(Order order, int displayedIndex, boolean shouldDisplayBuyerName) {
        super(FXML);
        this.order = order;
        this.displayedIndex = displayedIndex;
        fillOrderCard(shouldDisplayBuyerName);
    }

    /**
     * Fills the relevant fields in the order card.
     *
     * @param shouldDisplayBuyerName A boolean value indicating whether the buyer name should be displayed.
     */
    public void fillOrderCard(boolean shouldDisplayBuyerName) {
        if (shouldDisplayBuyerName) {
            buyerName.setText(order.getBuyer().getName().fullName);
        }
        id.setText("#" + displayedIndex);
        orderStatus.setText(order.getOrderStatus().getStatus());
        species.setText(order.getRequest().getRequestedSpecies().toString());
        age.setText(order.getRequest().getRequestedAge().toString());
        color.setText(order.getRequest().getRequestedColor().toString());
        colorPattern.setText(order.getRequest().getRequestedColorPattern().toString());
        priceRange.setText(order.getRequestedPriceRange().toString());
        settledPrice.setText(order.getSettledPrice().toString());
        // additionalRequestsDescription.setText("");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCard)) {
            return false;
        }

        // state check
        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}
