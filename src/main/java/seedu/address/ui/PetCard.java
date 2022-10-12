package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.pet.Pet;

public class PetCard extends UiPart<Region> {
    private static final String FXML = "PetListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Pet pet;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private Label pets;


    /**
     * Creates a {@code BuyerCode} with the given {@code Buyer} and index to display.
     */
    public PetCard(Pet pet, int displayedIndex) {
        super(FXML);
        this.pet = pet;
        id.setText(displayedIndex + ". ");
        name.setText(pet.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PetCard)) {
            return false;
        }

        // state check
        PetCard card = (PetCard) other;
        return id.getText().equals(card.id.getText())
                && pet.equals(card.pet);
    }
}
