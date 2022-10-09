package seedu.rc4hdb.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.resident.Resident;

/**
 * An UI component that displays information of a {@code Resident}.
 */
public class ResidentCard extends UiPart<Region> {

    private static final String FXML = "ResidentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Resident resident;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label room;
    @FXML
    private Label email;
    @FXML
    private Label gender;
    @FXML
    private Label house;
    @FXML
    private Label matricNumber;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ResidentCard} with the given {@code Resident} and index to display.
     */
    public ResidentCard(Resident resident, int displayedIndex) {
        super(FXML);
        this.resident = resident;
        id.setText(displayedIndex + ". ");
        name.setText(resident.getName().fullName);
        phone.setText(resident.getPhone().value);
        room.setText(resident.getRoom().room);
        email.setText(resident.getEmail().value);
        gender.setText(resident.getGender().gender);
        house.setText(resident.getHouse().house);
        matricNumber.setText(resident.getMatricNumber().matricNumber);
        resident.getTags().stream()
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
        if (!(other instanceof ResidentCard)) {
            return false;
        }

        // state check
        ResidentCard card = (ResidentCard) other;
        return id.getText().equals(card.id.getText())
                && resident.equals(card.resident);
    }
}
