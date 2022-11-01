package seedu.address.ui.displaycards;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.model.pet.Pet;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Pet}.
 */
public class PetCard extends UiPart<Region> {

    public static final boolean SHOULD_DISPLAY_SUPPLIER_NAME = true;
    public static final boolean SHOULD_NOT_DISPLAY_SUPPLIER_NAME = false;
    private static final String FXML = "PetListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Pet pet;
    private final int displayedIndex;

    @FXML
    private Label age;

    @FXML
    private Label color;

    @FXML
    private Label colorPattern;

    @FXML
    private Label dateOfBirth;

    @FXML
    private Label height;

    @FXML
    private Label id;

    @FXML
    private Hyperlink petCertificates;

    @FXML
    private Label petName;

    @FXML
    private ImageView petPhoto;

    @FXML
    private Rectangle petPhotoHolder;

    @FXML
    private Label salePrice;

    @FXML
    private Label species;

    @FXML
    private Label supplierName;

    @FXML
    private Hyperlink vaccinationProof;

    @FXML
    private Label weight;

    /**
     * Creates a {@code PetCard} with the given {@code Pet} and index to display.
     * The boolean indicates whether the supplier name should be displayed.
     */
    public PetCard(Pet pet, int displayedIndex, boolean isSupplierNameShown) {
        super(FXML);
        this.pet = pet;
        this.displayedIndex = displayedIndex;
        fillPetCard(isSupplierNameShown);
    }

    /**
     * Fills the relevant fields in the pet card.
     *
     * @param shouldDisplaySupplierName A boolean value indicating whether the suppler name should be displayed.
     */
    public void fillPetCard(boolean shouldDisplaySupplierName) {
        if (shouldDisplaySupplierName) {
            supplierName.setText("from " + pet.getSupplier().getName().fullName);
        }
        id.setText("#" + displayedIndex);
        species.setText(pet.getSpecies().getValue());
        petName.setText(pet.getName().fullName);
        age.setText("Age: " + String.valueOf(pet.getAge()));
        dateOfBirth.setText(pet.getDateOfBirth().toString());
        color.setText(pet.getColor().toString());
        colorPattern.setText(pet.getColorPattern().toString());
        height.setText(pet.getHeight().toString());
        weight.setText(pet.getWeight().toString());
        salePrice.setText(pet.getPrice().toString());

        // Set the pet photo to fill the holder

        // TODO: implement this using storage instead of a dummy image
        Image image = new Image(this.getClass().getResourceAsStream("/images/dummy_pet_image.png"));
        petPhoto.setImage(image);
    }

    @FXML
    void handleCertificatesLink(ActionEvent event) throws URISyntaxException, IOException {
        // TODO: implement this
        System.out.println("Certificates link clicked!");
        Desktop.getDesktop().browse(new URI("http://www.google.com"));
    }

    @FXML
    void handleVaccinationLink(ActionEvent event) throws URISyntaxException, IOException {
        // TODO: implement this
        System.out.println("Vaccination link clicked!");
        Desktop.getDesktop().browse(new URI("http://www.google.com"));
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
