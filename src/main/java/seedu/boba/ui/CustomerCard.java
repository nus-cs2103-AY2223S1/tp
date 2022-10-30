package seedu.boba.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.tag.Tag;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static seedu.boba.model.customer.Customer.BIRTHDAY_TAG;

/**
 * An UI component that displays information of a {@code Customer}.
 */
public class CustomerCard extends UiPart<Region> {

    private static final String FXML = "CustomerListCard.fxml";

    private static final HashMap<String, String> colourMap = new HashMap<>() {{
        put("DIAMOND", " -fx-background-color: ba68c8; ");
        put("PLATINUM", " -fx-background-color: a5d6a7; ");
        put("GOLD", " -fx-background-color: c9b037; ");
        put("SILVER", " -fx-background-color: c0c0c0; ");
        put("BRONZE", " -fx-background-color: cd7f32; ");
        put("WARNING", " -fx-background-color: ff6600; ");
        put("BANNED", " -fx-background-color: dd0000; ");
        put(BIRTHDAY_TAG.tagName, " -fx-background-color: ff69b4; ");
    }};

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on BobaBot level 4</a>
     */

    public final Customer customer;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label birthdayMonth;
    @FXML
    private Label reward;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Customer} and index to display.
     */
    public CustomerCard(Customer customer, int displayedIndex) {
        super(FXML);
        this.customer = customer;
        id.setText(displayedIndex + ". ");
        name.setText(customer.getName().fullName);
        phone.setText(customer.getPhone().displayValue);
        birthdayMonth.setText(customer.getBirthdayMonth().monthString);
        reward.setText(customer.getReward().displayValue);
        email.setText(customer.getEmail().displayValue);
        List<Tag> listOfTags = customer.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName)).collect(Collectors.toList());
        for (Tag tag : listOfTags) {
            String tagName = tag.tagName;
            Label label = new Label(tagName);
            label.setStyle(colourMap.getOrDefault(tagName, "  -fx-background-color: #3e7b91; "));
            tags.getChildren().add(label);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomerCard)) {
            return false;
        }

        // state check
        CustomerCard card = (CustomerCard) other;
        return id.getText().equals(card.id.getText())
                && customer.equals(card.customer);
    }
}
