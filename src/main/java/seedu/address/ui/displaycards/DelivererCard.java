package seedu.address.ui.displaycards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.order.Order;
import seedu.address.model.person.Deliverer;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Deliverer}.
 */
public class DelivererCard extends UiPart<Region> {

    private static final String FXML = "DelivererListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Logic logic;
    private final Deliverer deliverer;
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
     * Creates a {@code DelivererCode} with the given {@code Deliverer} and index to display.
     */
    public DelivererCard(Deliverer deliverer, int displayedIndex, Logic logic) {
        super(FXML);
        this.deliverer = deliverer;
        this.displayedIndex = displayedIndex;
        this.logic = logic;
        fillDeliverCard();
    }

    /**
     * Fills the relevant fields of the deliverer card.
     */
    public void fillDeliverCard() {
        // Set the contact details
        id.setText(displayedIndex + ". ");
        name.setText(deliverer.getName().fullName);
        phone.setText(deliverer.getPhone().value);
        locatedCountry.setText(deliverer.getLocation().location);
        address.setText(deliverer.getAddress().value);
        email.setText(deliverer.getEmail().value);

        // Set the buyer's orders in the list view
        orderListView.setItems(logic.getOrderAsObservableListFromDeliverer(deliverer));
        orderListView.setCellFactory(listView -> new DelivererOrdersListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Order} using a {@code BriefOrderCard}.
     */
    private static class DelivererOrdersListViewCell extends ListCell<Order> {
        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);

            if (empty || order == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DelivererOrderCard(order, getIndex() + 1).getRoot());
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
        if (!(other instanceof DelivererCard)) {
            return false;
        }

        // state check
        DelivererCard card = (DelivererCard) other;
        return id.getText().equals(card.id.getText())
                && deliverer.equals(card.deliverer);
    }
}
