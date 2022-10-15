package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

/**
 * An UI component that displays information of a {@code Supplier}.
 */
public class SupplierCard extends UiPart<Region> {

    private static final String FXML = "SupplierListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Supplier supplier;
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
    private ListView<Pet> petListView;

    @FXML
    private Label phone;

    /**
     * Creates a {@code SupplierCard} with the given {@code Supplier} and index to display.
     */
    public SupplierCard(Supplier supplier, int displayedIndex) {
        super(FXML);
        this.supplier = supplier;
        this.displayedIndex = displayedIndex;
        fillSupplierCard();
    }

    public void fillSupplierCard() {
        // Set the contact details
        id.setText(displayedIndex + ". ");
        name.setText(supplier.getName().fullName);
        phone.setText(supplier.getPhone().value);
        locatedCountry.setText(supplier.getLocation().location);
        address.setText(supplier.getAddress().value);
        email.setText(supplier.getEmail().value);

        // Set the buyer's orders in the list view
        petListView.setItems(supplier.getPetsAsObservableList());
        petListView.setCellFactory(listView -> new SupplierPetsListViewCell());

        /*        supplier.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));*/
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Order} using a {@code OrderCard}.
     */
    private static class SupplierPetsListViewCell extends ListCell<Pet> {
        @Override
        protected void updateItem(Pet pet, boolean empty) {
            super.updateItem(pet, empty);

            if (empty || pet == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PetCard(pet, getIndex() + 1, PetCard.SHOULD_NOT_DISPLAY_SUPPLIER_NAME)
                        .getRoot());
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
        if (!(other instanceof SupplierCard)) {
            return false;
        }

        // state check
        SupplierCard card = (SupplierCard) other;
        return id.getText().equals(card.id.getText())
                && supplier.equals(card.supplier);
    }
}
