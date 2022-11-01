package seedu.address.ui.displaycards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    public static final boolean SHOULD_DISPLAY_BUYER_NAME = true;
    public static final boolean SHOULD_NOT_DISPLAY_BUYER_NAME = false;
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
    private Label byDate;

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
     * Creates a {@code OrderCard} with the given {@code Order} and index to display.
     * The boolean indicates whether the buyer name should be displayed.
     */
    public OrderCard(Order order, int displayedIndex, boolean isBuyerNameShown) {
        super(FXML);
        this.order = order;
        this.displayedIndex = displayedIndex;
        fillOrderCard(isBuyerNameShown);
    }

    /**
     * Fills the relevant fields in the order card.
     *
     * @param shouldDisplayBuyerName A boolean value indicating whether the buyer name should be displayed.
     */
    public void fillOrderCard(boolean shouldDisplayBuyerName) {
        if (SHOULD_DISPLAY_BUYER_NAME) {
            buyerName.setText("from " + order.getBuyer().getName().fullName);
        }
        id.setText("#" + displayedIndex);
        orderStatus.setText(order.getOrderStatus().getStatus());
        species.setText(order.getRequest().getRequestedSpecies().toString());
        age.setText(order.getRequest().getRequestedAge().toString());
        color.setText(order.getRequest().getRequestedColor().toString());
        colorPattern.setText(order.getRequest().getRequestedColorPattern().toString());
        priceRange.setText(order.getRequestedPriceRange().toString());
        // TODO: debug this
        byDate.setText("Complete order by: " + order.getByDate());
        settledPrice.setText(order.getSettledPrice().toString());
        // TODO: debug this
        // additionalRequestsDescription.setText(order.getAdditionalRequests().toString());
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
