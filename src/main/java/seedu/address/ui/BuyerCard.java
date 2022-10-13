package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;

/**
 * An UI component that displays information of a {@code Buyer}.
 */
public class BuyerCard extends UiPart<Region> {

    private static final String FXML = "BuyerListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Buyer buyer;
    private final int displayedIndex;

    @FXML
    private Label address;

    @FXML
    private Label email;

    @FXML
    private Label id;

    @FXML
    private Label locatedCountry;

    @FXML
    private Label name;

    @FXML
    private ListView<Order> orderListView;

    @FXML
    private Label phone;

    /**
     * Creates a {@code BuyerCode} with the given {@code Buyer} and index to display.
     */
    public BuyerCard(Buyer buyer, int displayedIndex) {
        super(FXML);
        this.buyer = buyer;
        this.displayedIndex = displayedIndex;
        fillBuyerCard();
    }

    /**
     * Fills the relevant fields of the buyer card.
     */
    public void fillBuyerCard() {
        // Set the contact details
        id.setText(displayedIndex + ". ");
        name.setText(buyer.getName().fullName);
        phone.setText(buyer.getPhone().value);
        locatedCountry.setText(buyer.getLocation().location);
        address.setText(buyer.getAddress().value);
        email.setText(buyer.getEmail().value);

        // Set the buyer's orders in the list view
        orderListView.setItems(buyer.getOrdersAsObservableList());
        orderListView.setCellFactory(listView -> new BuyerOrderListViewCell());

        /*buyer.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        orders.setText(buyer.getOrders().toString());*/
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Order} using a {@code OrderCard}.
     */
    private static class BuyerOrderListViewCell extends ListCell<Order> {
        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);

            if (empty || order == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OrderCard(order, getIndex() + 1, false).getRoot());
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BuyerCard)) {
            return false;
        }

        // state check
        BuyerCard card = (BuyerCard) other;
        return id.getText().equals(card.id.getText())
                && buyer.equals(card.buyer);
    }
}
