package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.customer.Customer;

/**
 * An UI component that displays information of a {@code Customer}.
 */
public class CustomerDetailsPane extends UiPart<Region> {

    private static final String FXML = "CustomerDetailsPane.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Customer customer;

    @FXML
    private HBox detailsPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code CustomerCode} with the given {@code Customer} and index to display.
     */
    public CustomerDetailsPane(ObservableObject<Customer> customer) {
        super(FXML);
        this.customer = customer.getValue();
        this.updateUI(this.customer);
        customer.addListener((observable, oldValue, newValue) -> {
            this.updateUI(newValue);
        });
    }

    private void updateUI(Customer customer) {
        if (customer == null) {
            name.setText("No customer selected");
            phone.setText("");
            address.setText("");
            email.setText("");
            tags.getChildren().clear();
        } else {
            name.setText(customer.getName().fullName);
            phone.setText(customer.getPhone().value);
            address.setText(customer.getAddress().map(address -> address.value).orElse(""));
            email.setText(customer.getEmail().value);
            tags.getChildren().clear();
            customer.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomerDetailsPane)) {
            return false;
        }

        // state check
        CustomerDetailsPane pane = (CustomerDetailsPane) other;
        return customer.isSameCustomer(pane.customer);
    }
}
