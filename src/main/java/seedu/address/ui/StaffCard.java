package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.staff.Staff;

/**
 * An UI component that displays information of a {@code Staff}.
 */
public class StaffCard extends UiPart<Region> {

    private static final String FXML = "StaffListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Staff staff;

    @FXML
    private HBox cardPane;
    @FXML
    private Label staffName;
    @FXML
    private Label staffTitle;
    @FXML
    private Label staffContact;
    @FXML
    private Label id;
    @FXML
    private FlowPane staffInsurance;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code StaffCode} with the given {@code Staff} and index to display.
     */
    public StaffCard(Staff staff, int displayedIndex) {
        super(FXML);
        this.staff = staff;
        id.setText(displayedIndex + ". ");
        staffName.setText(staff.getStaffName().toString());
        staffTitle.setText(staff.getStaffTitle().toString());
        staffContact.setText("Phone : " + staff.getStaffContact());

        staff.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StaffCard)) {
            return false;
        }

        // state check
        StaffCard card = (StaffCard) other;
        return id.getText().equals(card.id.getText())
                && staff.equals(card.staff);
    }
}
