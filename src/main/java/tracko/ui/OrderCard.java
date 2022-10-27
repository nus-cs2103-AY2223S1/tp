package tracko.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import tracko.commons.util.DateTimeUtil;
import tracko.model.order.Order;

/**
 * A UI component that displays information of an {@code Order}.
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

    public final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label timeCreated;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label paidStatus;
    @FXML
    private Label deliveryStatus;
    @FXML
    private Label totalOrderPrice;
    @FXML
    private VBox items;
    @FXML
    private HBox dateContainer;
    @FXML
    private HBox orderStatus;
    @FXML
    private HBox totalOrderPriceContainer;
    @FXML
    private Region totalPriceIcon;
    @FXML
    private Region timeCreatedIcon;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;

        id.setText(Integer.toString(displayedIndex));

        name.setText(order.getName().fullName);
        name.setWrapText(true);
        name.setPadding(new Insets(0, 10, 0, 0));

        phone.setText(order.getPhone().value);
        phone.setWrapText(true);
        phone.setPadding(new Insets(0, 10, 0, 0));

        timeCreated.setText(order.getTimeCreated().format(DateTimeUtil.getFormat()));
        timeCreated.setWrapText(true);
        timeCreated.setPadding(new Insets(0, 10, 0, 0));

        address.setText(order.getAddress().value);
        address.setWrapText(true);
        address.setPadding(new Insets(0, 10, 0, 0));

        email.setText(order.getEmail().value);
        email.setWrapText(true);
        email.setPadding(new Insets(0, 10, 0, 0));

        if (order.getPaidStatus()) {
            paidStatus.setText("Paid");
            paidStatus.setStyle("-fx-background-color: #D9DCFF; -fx-text-fill: #707BE3");
        } else {
            paidStatus.setText("Unpaid");
            paidStatus.setStyle("-fx-background-color: #FF6C64; -fx-text-fill: #FFEDEC");
        }

        if (order.getDeliveryStatus()) {
            deliveryStatus.setText("Delivered");
            deliveryStatus.setStyle("-fx-background-color: #D9DCFF; -fx-text-fill: #707BE3");
        } else {
            deliveryStatus.setText("Undelivered");
            deliveryStatus.setStyle("-fx-background-color: #FF6C64; -fx-text-fill: #FFEDEC");
        }

        totalOrderPrice.setText("$" + String.format("%.2f", order.calculateTotalOrderPrice()));
        totalOrderPrice.setWrapText(true);
        totalOrderPrice.setPadding(new Insets(0, 10, 0, 0));

        if (order.isCompleted()) {
            setCompletedStyle();
        }

        items.setPadding(new Insets(8, 10, 8, 10));
        items.setStyle("-fx-background-insets: 5 0 5 0;");
        items.getStyleClass().add("ordered-items-container");
        order.getItemList().stream()
                .forEach(pair -> items.getChildren().add(
                        constructItemLabel(pair.toString())));
    }

    /**
     * Changes the colors of the order card elements to faded colors after an order is completed.
     */
    public void setCompletedStyle() {
        paidStatus.setText("Completed");
        paidStatus.setStyle("-fx-background-color: #DFDFEB; -fx-text-fill: #7A7CA5");
        deliveryStatus.setText(null);
        deliveryStatus.setStyle("-fx-background-color: #7A7CA5");
        totalOrderPriceContainer.setStyle("-fx-background-color: #C4EAC8 !important;");
        totalOrderPrice.setStyle("-fx-text-fill: #678D6C !important;");
        totalPriceIcon.setStyle("-fx-background-color: #678D6C !important;");
        timeCreated.setStyle("-fx-text-fill: #AA9787 !important;");
        dateContainer.setStyle("-fx-background-color: #FAF4EF !important;");
        timeCreatedIcon.setStyle("-fx-background-color: #AA9787 !important;");
    }

    /**
     * Constructs a new label for each item while assigning
     * a custom style to it.
     */
    public Label constructItemLabel(String text) {
        Label itemLabel = new Label(text);
        itemLabel.getStyleClass().add("ordered-items-content");
        itemLabel.setWrapText(true);
        itemLabel.setPadding(new Insets(0, 10, 0, 10));
        return itemLabel;
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
