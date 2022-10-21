package seedu.address.ui;

import java.util.HashSet;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.AddPetCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Price;
import seedu.address.model.person.Name;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.Height;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetCertificate;
import seedu.address.model.pet.Species;
import seedu.address.model.pet.VaccinationStatus;
import seedu.address.model.pet.Weight;
import seedu.address.model.tag.Tag;

public class PopupPanelForPet extends UiPart<Region> implements PopUpPanel {

    private static final String FXML = "PopupPanelForPet.fxml";

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

    public PopupPanelForPet() {
        super(FXML);
        petNameField.requestFocus();
        generateInputSequence(petNameField, speciesField, heightField, weightField,
                dateOfBirthField, colorField, colorPatternField, priceField);
    }

    public String generateCommandText() {
        boolean allPartsFilled = checkGivenFieldsAllFilled(colorField, colorPatternField, dateOfBirthField,
                heightField, petNameField, priceField, speciesField, weightField);
        if (!allPartsFilled) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(AddPetCommand.COMMAND_WORD).append(" ")
                    .append(CliSyntax.PREFIX_PET_COLOR).append(colorField.getText())
                    .append(CliSyntax.PREFIX_PET_COLOR_PATTERN).append(colorPatternField.getText())
                    .append(CliSyntax.PREFIX_PET_DATE_OF_BIRTH).append(dateOfBirthField.getText())
                    .append(CliSyntax.PREFIX_PET_HEIGHT).append(heightField.getText())
                    .append(CliSyntax.PREFIX_PET_NAME).append(petNameField.getText())
                    .append(CliSyntax.PREFIX_PET_PRICE).append(priceField.getText())
                    .append(CliSyntax.PREFIX_PET_SPECIES).append(speciesField.getText())
                    .append(CliSyntax.PREFIX_PET_WEIGHT).append(weightField.getText());
            return builder.toString();
        }
    }


    @Override
    public Command generateCommand() {
        // TODO: implement this
        return null;
    }

    public Pet generatePet(Supplier supplier) throws ParseException {
        Name name = ParserUtil.parseName(petNameField.getText());
        Species species = ParserUtil.parseSpecies(speciesField.getText());
        Height height = ParserUtil.parseHeight(heightField.getText());
        Weight weight = ParserUtil.parseWeight(weightField.getText());
        DateOfBirth dateOfBirth = ParserUtil.parseDateOfBirth(dateOfBirthField.getText());
        Color color = ParserUtil.parseColor(colorField.getText());
        ColorPattern colorPattern = ParserUtil.parseColorPattern(colorPatternField.getText());
        Price price = ParserUtil.parsePrice(priceField.getText());
        // TODO: update these dummy initialisation
        VaccinationStatus vaccinationStatus = new VaccinationStatus(false);
        Set<Tag> tags = new HashSet<>();
        Set<PetCertificate> certificates = new HashSet<>();
        return new Pet(name, supplier, color, colorPattern, dateOfBirth, species,
                weight, height, vaccinationStatus, price, tags, certificates);
    }

    @Override
    public boolean checkAllPartsFilled() {
        boolean allPartsFilled = checkGivenFieldsAllFilled(colorField, colorPatternField, dateOfBirthField,
                heightField, petNameField, priceField, speciesField, weightField);
        return allPartsFilled;
    }
}
