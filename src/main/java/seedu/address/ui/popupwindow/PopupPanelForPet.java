package seedu.address.ui.popupwindow;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Price;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.Height;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetCertificate;
import seedu.address.model.pet.PetName;
import seedu.address.model.pet.Species;
import seedu.address.model.pet.VaccinationStatus;
import seedu.address.model.pet.Weight;

/**
 * A panel for entering pet information, which can be part of the {@code PopupPanelForSupplier}.
 */
public class PopupPanelForPet extends PopUpPanel {

    private static final String FXML = "PopupPanelForPet.fxml";
    private final Stage stage;

    @FXML
    private TextField colorField;

    @FXML
    private TextField colorPatternField;

    @FXML
    private TextField dateOfBirthField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField petNameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField speciesField;

    @FXML
    private TextField weightField;

    /**
     * Constructs a {@code PopupPanelForPet} by setting input sequence and prompt text style.
     */
    public PopupPanelForPet(Stage stage) {
        super(FXML);
        this.stage = stage;
        petNameField.requestFocus();
        generateInputSequence(petNameField, speciesField, heightField, weightField,
                dateOfBirthField, colorField, colorPatternField, priceField);
        setPromptTextStyle(petNameField, speciesField, heightField, weightField,
                dateOfBirthField, colorField, colorPatternField, priceField);
    }

    @FXML
    void openFileExplorer(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
    }

    @Override
    public Command generateCommand() {
        // TODO: modify AddPetCommand
        return null;
    }

    /**
     * Generates a {@code Pet} from user inputs.
     * @param supplier Supplier of the order.
     * @return A {@code Pet}.
     * @throws ParseException When user inputs cannot be parsed.
     */
    public Pet generatePet(Supplier supplier) throws ParseException {
        PetName name = ParserUtil.parsePetName(petNameField.getText());
        Species species = ParserUtil.parseSpecies(speciesField.getText());
        Height height = ParserUtil.parseHeight(heightField.getText());
        Weight weight = ParserUtil.parseWeight(weightField.getText());
        DateOfBirth dateOfBirth = ParserUtil.parseDateOfBirth(dateOfBirthField.getText());
        Color color = ParserUtil.parseColor(colorField.getText());
        ColorPattern colorPattern = ParserUtil.parseColorPattern(colorPatternField.getText());
        Price price = ParserUtil.parsePrice(priceField.getText());
        // TODO: update these dummy initialisation
        VaccinationStatus vaccinationStatus = new VaccinationStatus(false);
        Set<PetCertificate> certificates = new HashSet<>();
        return new Pet(name, supplier, color, colorPattern, dateOfBirth, species,
                weight, height, vaccinationStatus, price, certificates);
    }

    @Override
    public boolean checkAllPartsFilled() {
        boolean allPartsFilled = checkGivenFieldsAllFilled(petNameField, speciesField, heightField, weightField,
                dateOfBirthField, colorField, colorPatternField, priceField);
        return allPartsFilled;
    }
}
